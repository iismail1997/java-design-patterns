package com.iluwatar.serviceadapter;

public class ServiceAdapter {

    DivideInteger request = new DivideInteger();

    /* Function that actually calls the external remote service to divide two integers*/
    public long divideIntegers(long a, long b){
        request.setArg1(a);
        request.setArg2(b);
        SOAPDemo soapDemoService = new SOAPDemo();
        SOAPDemoSoap soapDemoSoap = soapDemoService.getSOAPDemoSoap();
        return soapDemoSoap.divideInteger(request.getArg1(),request.getArg2());
    }
}