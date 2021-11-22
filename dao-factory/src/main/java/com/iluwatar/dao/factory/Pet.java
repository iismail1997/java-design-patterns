package com.iluwatar.dao.factory;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * POJO for DynamoDB table Pets
 */
@DynamoDBTable(tableName = "Pets")
public class Pet {
    private String name;
    private String type;
    private int age;
    private boolean softDelete;

    /**
     * Getter for name
     * @return pet name
     */
    @DynamoDBHashKey(attributeName = "name")
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for pet type
     * @return  type
     */
    @DynamoDBAttribute(attributeName = "type")
    public String getType() {
        return type;
    }

    /**
     * Setter for pet type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for age
     * @return age
     */
    @DynamoDBAttribute(attributeName = "age")
    public int getAge() {
        return age;
    }

    /**
     * Setter for age
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * getter for soft delete flag
     * @return flag
     */
    @DynamoDBAttribute(attributeName = "softDelete")
    public boolean isSoftDelete() {
        return softDelete;
    }

    /**
     * Setter for soft delelte flag
     * @param softDelete
     */
    public void setSoftDelete(boolean softDelete) {
        this.softDelete = softDelete;
    }
}
