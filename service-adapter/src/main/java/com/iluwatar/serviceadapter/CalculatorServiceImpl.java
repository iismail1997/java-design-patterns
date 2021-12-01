package com.iluwatar.serviceadapter;

public class CalculatorServiceImpl implements CalculatorService {

    /* Funtion to add two integers*/
    public int addIntegers(int a, int b) {
        return a + b ;
    }

    /* Funtion to divide two integers*/
    public int divideIntegers(int a, int b){
        ServiceAdapter serviceAdapter = new ServiceAdapter();
        return serviceAdapter.divideIntegers(a, b);
    }
}
