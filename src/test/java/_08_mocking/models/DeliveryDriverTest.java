package _08_mocking.models;

import _07_intro_to_mocking.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliveryDriverTest {

    DeliveryDriver deliveryDriver;
    
    @Mock
    Car car;
    
    @Mock
    CellPhone cellPhone;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	deliveryDriver = new DeliveryDriver("Joe", car, cellPhone);
    }

    @Test
    void itShouldWasteTime() {
        //given
    	boolean expectedWasteTime = true;
    	when(cellPhone.browseCatMemes()).thenReturn(true);
        //when
    	boolean actualWasteTime = deliveryDriver.wasteTime();
        //then
    	assertEquals(expectedWasteTime, actualWasteTime);
    }

    @Test
    void itShouldRefuel() {
        //given
    	boolean expectedRefuel = true;
    	int octane = 85;
    	when(car.fillTank(octane)).thenReturn(true);
        //when
    	boolean actualRefuel = deliveryDriver.refuel(octane);
        //then
    	assertEquals(expectedRefuel, actualRefuel);
    }

    @Test
    void itShouldContactCustomer() {
        //given
    	boolean expectedContactCustomer = true;
    	String phoneNumber = "1-800-TOILETS";
    	when(cellPhone.call(phoneNumber)).thenReturn(true);
        //when
    	boolean actualContactCustomer = deliveryDriver.contactCustomer(phoneNumber);
        //then
    	assertEquals(expectedContactCustomer, actualContactCustomer);
    }

}