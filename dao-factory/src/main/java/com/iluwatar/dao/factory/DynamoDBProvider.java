package com.iluwatar.dao.factory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;

import java.util.Arrays;

/**
 * A provider class to generate DynamoDB instance
 */
public final class DynamoDBProvider {

    /**
     * Adding a private constructor to silence PMD
     * warning
     */
    private DynamoDBProvider() {}

    /**
     * Static method to setup a local DynamoDB with a table named "Pet"
     * @return AmazonDynamoDB object
     */
    public static AmazonDynamoDB getDynamoDB() {
        System.setProperty("sqlite4java.library.path", "/target/native-libs");
        String tableName = "Pets";
        AmazonDynamoDB dynamodb = null;
        try {
            // Create an in-memory and in-process instance of DynamoDB Local that skips HTTP
            dynamodb = DynamoDBEmbedded.create().amazonDynamoDB();
            // use the DynamoDB API with DynamoDBEmbedded
            listTables(dynamodb.listTables(), "DynamoDB Embedded");

            try {
                System.out.println("Attempting to create table; please wait...");

                CreateTableResult table = dynamodb.createTable(Arrays.asList(new AttributeDefinition("name", ScalarAttributeType.S)),
                        tableName, Arrays.asList(new KeySchemaElement("name", KeyType.HASH)), new ProvisionedThroughput(10L, 10L)

                );
                System.out.println("Success.  Table status: " + table.getTableDescription().getTableStatus());

            } catch (Exception e) {
                System.err.println("Unable to create table: ");
                System.err.println(e.getMessage());
                // Shutdown the thread pools in DynamoDB Local / Embedded
                dynamodb.shutdown();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return dynamodb;
    }

    private static void listTables(ListTablesResult result, String method) {
        System.out.println("found " + Integer.toString(result.getTableNames().size()) + " tables with " + method);
        for(String table : result.getTableNames()) {
            System.out.println(table);
        }
    }

}
