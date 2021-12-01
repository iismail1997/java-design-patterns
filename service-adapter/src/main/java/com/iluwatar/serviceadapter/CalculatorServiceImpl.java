package com.iluwatar.serviceadapter;

public class CalculatorServiceImpl implements CalculatorService {

    /* Funtion to add two integers*/
    public int addIntegers(int A, int B) {
        return A + B ;
    }

    /* Funtion to divide two integers*/
    public int divideIntegers(int A, int B){
        ServiceAdapter serviceAdapter = new ServiceAdapter();
        return serviceAdapter.divideIntegers(A, B);
    }
}
