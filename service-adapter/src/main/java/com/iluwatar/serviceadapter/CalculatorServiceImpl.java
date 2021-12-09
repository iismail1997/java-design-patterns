package com.iluwatar.serviceadapter;

public class CalculatorServiceImpl implements CalculatorService {

    /* Funtion to add two integers*/
    public long addIntegers(long a, long b) {
        return a + b ;
    }

    /* Function to divide two integers using a service adapter*/
    public long divideIntegers(long a, long b) {
        ServiceAdapter serviceAdapter = new ServiceAdapter();
        return serviceAdapter.divideIntegers(a, b);
    }
}
