package com.iluwatar.dao.factory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;

import java.util.Objects;

/**
 * This is a DAO Factory implementation using Factory Method Pattern
 * The factory is to create ReadOnlyDao and ReadWriteDao
 */
public final class DaoFactory {
    private DaoFactory(){}

    /**
     * Create a dao object based on param
     * @param type The type of Dao to create
     * @param dynamodb DynamoDB object
     * @return Dao object created based on type
     */
    public static DAO getDAO(DaoType type, AmazonDynamoDB dynamodb){
        if (Objects.equals(type, DaoType.ReadOnly)){
            return new ReadOnlyDAOImpl(dynamodb);
        }else if (Objects.equals(type, DaoType.ReadWrite)){
            return new ReadWriteDAOImpl(dynamodb);
        }else {
            return null;
        }

    }

}
