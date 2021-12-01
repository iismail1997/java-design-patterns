package com.iluwatar.serviceadapter;

public class ServiceAdapter {

    DivideIntegerServiceRequest service = new DivideIntegerServiceRequest();
    /* Function that actually calls the external remote service to divide two integers*/
    public int divideIntegers(int A, int B){
        return A/B; // replace this with remote soap service utility call
    }



}
