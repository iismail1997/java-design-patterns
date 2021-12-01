package com.iluwatar.serviceadapter;

public class DivideIntegerServiceRequest {
    private String url;
    private String soapMethod;
    private String[] arguments;

    private String getUrl() {
        return url;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    private String getSoapMethod() {
        return soapMethod;
    }

    private void setSoapMethod(String soapMethod) {
        this.soapMethod = soapMethod;
    }

    private String[] getArguments() {
        return arguments;
    }

    private void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

}
