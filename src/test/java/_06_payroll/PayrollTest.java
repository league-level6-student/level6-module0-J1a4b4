package _06_payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayrollTest {

    Payroll payroll = new Payroll();

    @Test
    void itShouldCalculatePaycheck() {
        //given
    	double a = 100.2;
    	int b = 20;
    	double expected = 2004;
        //when
    	double actual = payroll.calculatePaycheck(a, b);
        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldCalculateMileageReimbursement() {
        //given
    	int a = 100;
    	double expected = 57.5;
        //when
    	double actual = payroll.calculateMileageReimbursement(a);
        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldCreateOfferLetter() {
        //given
    	String n = "Mr. Anderson";
    	double a = 11.75;
    	String expected = "Hello Mr. Anderson, We are pleased to offer you an hourly wage of 11.75";
        //when
    	String actual = payroll.createOfferLetter(n, a);
        //then
    	assertEquals(expected, actual);
    }

}