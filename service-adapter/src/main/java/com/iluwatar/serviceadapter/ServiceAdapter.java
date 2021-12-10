package com.iluwatar.serviceadapter;

public class ServiceAdapter {

    DivideInteger request = new DivideInteger();

    /**
     * Encapsulated class that divides two integers using a SOAP service
     * @param a integer argument 1
     * @param b integer argument 2
     * @return division result of the a and b
     */
    public long divideIntegers(long a, long b){
        request.setArg1(a);
        request.setArg2(b);
        SOAPDemo soapDemoService = new SOAPDemo();
        SOAPDemoSoap soapDemoSoap = soapDemoService.getSOAPDemoSoap(); // Soap client
        return soapDemoSoap.divideInteger(request.getArg1(),request.getArg2()); //
    }
}