package com.iluwatar.serviceadapter;

/**
 * The type Calculator service.
 */
public class CalculatorServiceImpl implements CalculatorService {
    public long addIntegers(long a, long b) {
        /**
         * Method to add two integers
         * @param a and b refers to the two integers that needs to be added
         * @return addition result of the two integers
         */
        return a + b ;
    }

    public long divideIntegers(long a, long b) {
        /**
         * Method to divide two integers
         * @param a and b refers to the two integers that needs to be divided
         * @return division result of the two integers.
         */
        ServiceAdapter serviceAdapter = new ServiceAdapter();
        return serviceAdapter.divideIntegers(a, b);
    }
}
