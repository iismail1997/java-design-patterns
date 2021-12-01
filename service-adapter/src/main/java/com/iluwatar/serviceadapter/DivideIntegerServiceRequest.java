package com.iluwatar.serviceadapter;

public class DivideIntegerServiceRequest {
    private String url;
    private String soapMethod;
    private String[] arguments;

    /* Model that builds the remote service call request to divide  add two integers*/
    public DivideIntegerServiceRequest() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSoapMethod() {
        return soapMethod;
    }

    public void setSoapMethod(String soapMethod) {
        this.soapMethod = soapMethod;
    }

    public String[] getArguments() {
        return arguments;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

}
