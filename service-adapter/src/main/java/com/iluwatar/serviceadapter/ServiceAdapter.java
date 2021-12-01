package com.iluwatar.serviceadapter;

public class ServiceAdapter {

    DivideIntegerServiceRequest service = new DivideIntegerServiceRequest();

    public int divideIntegers(int A, int B){
        return A/B; // replace this with remote soap service utility call
    }



}
