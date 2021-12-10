package com.iluwatar.serviceadapter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Service adapter test.
 */
public class ServiceAdapterTest {

    /**
     * Test adding integers.
     */
    @Test
    void testAddingIntegers(){
        assertEquals(3,new CalculatorServiceImpl().addIntegers(2,1));
    }

    /**
     * Test dividing integers.
     */
    @Test
    void testDividingIntegers(){
        assertEquals(2,new CalculatorServiceImpl().divideIntegers(6,3));
    }
}
