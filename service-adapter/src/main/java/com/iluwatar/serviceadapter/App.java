package com.iluwatar.serviceadapter;


/**
 * The Service Adapter pattern is a design pattern used in software development to encapsulate the
 * interactions with a remote service, hiding the details of the implementation when they aren't
 * central to fulfilling the business needs.
 *
 * <p>In this example we use the Service adapter pattern to ......
 * <br>
 *
 * @author mahendher
 */
public class App {

    /**
     * Program entry point.
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        CalculatorServiceImpl calculatorService = new CalculatorServiceImpl();
        System.out.println("Result of Integers Addition: " + calculatorService.addIntegers(2, 3));
        System.out.println("Result of Integers Division: " + calculatorService.divideIntegers(6,3));
    }
}
