package com.iluwatar.serviceadapter;

public class CalculatorServiceImpl implements CalculatorService {

    public int addIntegers(int A, int B) {
        return A + B ;
    }

    public int divideIntegers(int A, int B){
        ServiceAdapter serviceAdapter = new ServiceAdapter();
        return serviceAdapter.divideIntegers(A, B);
    }
}
