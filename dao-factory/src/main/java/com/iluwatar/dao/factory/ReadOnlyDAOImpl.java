package com.iluwatar.dao.factory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * The implementation of ReadOnly Dao
 * The Dao should only be able to perform read only operation
 */
@Slf4j
public class ReadOnlyDAOImpl implements DAO {

    private final DynamoDBMapper mapper;

    /**
     * Constructor
     * @param dynamodb
     */
    public ReadOnlyDAOImpl(AmazonDynamoDB dynamodb) {
        this.mapper = new DynamoDBMapper(dynamodb);
    }

    /**
     * Get method to retrieve record from DB based on name
     * @param name The name of pet to obtain from the DB
     * @return pet retrived from DB
     */
    @Override
    public Pet get(String name) {
        return mapper.load(Pet.class, name);
    }

    /**
     * Read only Dao should do nothing when add method is called.
     * @param pet
     */
    @Override
    public void add(Pet pet) {
        LOGGER.info("READ ONLY DAO DO NOTHING IN ADD METHOD");
    }

    /**
     * Read only Dao should do nothing when update method is called.
     * @param pet
     * @throws PetNotFoundException
     */
    @Override
    public void update(Pet pet) throws PetNotFoundException {
        LOGGER.info("READ ONLY DAO DO NOTHING IN UPDATE");

    }

    /**
     * Read only Dao should do nothing when delete method is called.
     * @param pet
     */
    @Override
    public void delete(Pet pet) {
        LOGGER.info("READ ONLY DAO DO NOTHING IN DELETE");
    }

    /**
     * Read only Dao should do nothing when soft delete method is called.
     * @param pet
     * @throws PetNotFoundException
     */
    @Override
    public void sofeDelete(Pet pet) throws PetNotFoundException {
        LOGGER.info("READ ONLY DAO DO NOTHING IN SOFT DELETE");
    }

}
