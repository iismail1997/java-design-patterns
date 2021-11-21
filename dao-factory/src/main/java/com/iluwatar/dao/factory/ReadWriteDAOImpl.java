package com.iluwatar.dao.factory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

/**
 * The implementation of ReadWrite Dao
 * The Dao should only be able to perform both read and write operations
 */
public class ReadWriteDAOImpl implements DAO {
    private final DynamoDBMapper mapper;

    /**
     * Constructor
     * @param dynamodb
     */
    public ReadWriteDAOImpl(AmazonDynamoDB dynamodb) {
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
     * Add a new pet record to DB
     * @param pet The pet to be saved to DB
     */
    @Override
    public void add(Pet pet) {
        mapper.save(pet);
    }

    /**
     * Update an existing pet in DB, throws exception if no pet matches
     * the param found in DB
     * @param pet The pet to be updated with the updated attribute values
     * @throws PetNotFoundException if any record with the same name cannot
     * be found in DB
     */
    @Override
    public void update(Pet pet) throws PetNotFoundException {
        Pet petToUpdate = mapper.load(Pet.class, pet.getName());
        if (petToUpdate == null) {
            throw new PetNotFoundException("Pet " + pet.getName() + " does not exist in the DB.");
        }
        petToUpdate.setAge(pet.getAge());
        petToUpdate.setType(pet.getType());
        mapper.save(petToUpdate);
    }

    /**
     * Delete a pet from DB
     * @param pet The pet record to be deleted from the DB
     *            If the pet record does not exist, will do
     *            nothing.
     */
    @Override
    public void delete(Pet pet) {
        mapper.delete(pet);
    }

    /**
     * Soft delete does not remove a record from DB, it simply set the flag to true
     * @param pet The pet to be soft deleted
     * @throws PetNotFoundException if any record with the same name cannot
     * be found in DB
     */
    @Override
    public void sofeDelete(Pet pet) throws PetNotFoundException {
        Pet petToSoftDelete = mapper.load(Pet.class, pet.getName());
        if (petToSoftDelete == null) {
            throw new PetNotFoundException("Pet " + pet.getName() + " does not exist in the DB.");
        }
        petToSoftDelete.setSoftDelete(true);
        mapper.save(petToSoftDelete);
    }
}
