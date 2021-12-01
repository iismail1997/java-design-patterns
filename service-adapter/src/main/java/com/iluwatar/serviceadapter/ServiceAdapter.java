package com.iluwatar.serviceadapter;

public class ServiceAdapter {

    DivideIntegerServiceRequest service = new DivideIntegerServiceRequest();
    /* Function that actually calls the external remote service to divide two integers*/
    public int divideIntegers(int a, int b){
        return a/b; // replace this with remote soap service utility call
    }



}
