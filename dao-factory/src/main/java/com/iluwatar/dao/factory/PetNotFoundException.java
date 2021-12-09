package com.iluwatar.dao.factory;

public class PetNotFoundException extends Exception {
    private static final long serialVersionUID = 10L;

    /**
     * Exception for when pet is not found in
     * DynamoDB Pets table
     * @param e
     */
    public PetNotFoundException(String e) {
        super(e);
    }

    /**
     * Exception for when pet is not found in
     * DynamoDB Pets table
     * @param e
     * @param throwable
     */
    public PetNotFoundException(String e, Throwable throwable) {
        super(e, throwable);
    }

}
