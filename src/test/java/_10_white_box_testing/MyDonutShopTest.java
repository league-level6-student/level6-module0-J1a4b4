package _10_white_box_testing;

import _09_intro_to_white_box_testing.models.DeliveryService;
import _09_intro_to_white_box_testing.models.Order;
import _10_white_box_testing.models.BakeryService;
import _10_white_box_testing.models.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

class MyDonutShopTest {

    MyDonutShop myDonutShop;
    
    @Mock
    PaymentService paymentService;
    
    @Mock
    DeliveryService deliveryService;
    
    @Mock
    BakeryService bakeryService;
    
    @Mock
    Order order;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	List<Order> orders = Collections.singletonList(order);
    	myDonutShop = new MyDonutShop(paymentService, deliveryService, bakeryService);
    }

    @Test
    void itShouldTakeDeliveryOrder() throws Exception {
        //given
    	Order order = new Order("CUSTOMER_NAME", "CUSTOMER_PHONE_NUMBER", 1, 5.00, "CREDIT_CARD_NUMBER", true);
    	when(paymentService.charge(order)).thenReturn(true);
    	when(bakeryService.getDonutsRemaining()).thenReturn(50);
        //when
    	myDonutShop.openForTheDay();
    	myDonutShop.takeOrder(order);
        //then
    	verify(deliveryService, times(1)).scheduleDelivery(order);
    }

    @Test
    void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() {
        //given
    	Order order = new Order("CUSTOMER_NAME", "CUSTOMER_PHONE_NUMBER", 10, 5.00, "CREDIT_CARD_NUMBER", true);
    	when(paymentService.charge(order)).thenReturn(true);
    	when(bakeryService.getDonutsRemaining()).thenReturn(8);
        //when
    	myDonutShop.openForTheDay();
        //then
    	Throwable e = assertThrows(IllegalArgumentException.class, ()->{myDonutShop.takeOrder(order);});
    	assertEquals(e.getMessage(), "Insufficient donuts remaining");
    }

    @Test
    void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException(){
        //given
    	Order order = new Order("CUSTOMER_NAME", "CUSTOMER_PHONE_NUMBER", 1, 5.00, "CREDIT_CARD_NUMBER", true);
        //when
    	myDonutShop.closeForTheDay();
        //then
    	Throwable e = assertThrows(IllegalStateException.class, ()->{myDonutShop.takeOrder(order);});
    	assertEquals(e.getMessage(), "Sorry we're currently closed");
    }

}