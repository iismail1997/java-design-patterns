package com.iluwatar.serviceadapter;

public class DivideIntegerServiceResponse {

    public class Envelope {
        public Body Body;
        public String SOAPENV;
        public String xsi;
        public String s;
        public int text;
    }
    public class Body {
        public AddIntegerResponse AddIntegerResponse;
    }
    public class AddIntegerResponse {
        public int AddIntegerResult;
        public String xmlns = "http://tempuri.org";
        public int text;
    }






}
