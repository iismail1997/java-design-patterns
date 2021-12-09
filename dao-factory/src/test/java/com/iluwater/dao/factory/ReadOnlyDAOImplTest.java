package com.iluwater.dao.factory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;

import com.iluwatar.dao.factory.DAO;
import com.iluwatar.dao.factory.DaoFactory;
import com.iluwatar.dao.factory.DaoType;
import com.iluwatar.dao.factory.DynamoDBProvider;
import com.iluwatar.dao.factory.Pet;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ReadOnlyDAOImplTest {
    private static final AmazonDynamoDB dynamoDB = DynamoDBProvider.getDynamoDB();
    private final DAO readOnlyDAO = DaoFactory.getDAO(DaoType.ReadOnly, dynamoDB);
    private final DAO readWriteDAO = DaoFactory.getDAO(DaoType.ReadWrite, dynamoDB);

    @AfterAll
    static void shutdownDB() {
        dynamoDB.shutdown();
        System.out.println("Shutting down DB..");
    }

    @Test
    public void getPetReturnCorrectPet() {
        // GIVEN
        String petName = "Mimi";
        Pet pet = new Pet();
        pet.setName(petName);
        pet.setAge(1);
        pet.setType("Cat");
        pet.setSoftDelete(false);
        readWriteDAO.add(pet);

        // WHEN
        Pet retrievedPet = readOnlyDAO.get(petName);

        // THEN
        assertEquals(petName, retrievedPet.getName(), "We got the wrong pet!");
    }

    @Test
    public void getPetNonExistPetReturnNull() {
        // GIVEN
        String petName = "Non-exist";

        // WHEN
        Pet retrievedPet = readOnlyDAO.get(petName);

        // THEN
        assertNull(retrievedPet, "The pet should not exist in DB");
    }

    @Test
    public void addPetDoNothing() {
        // GIVEN
        String petName = "Daisy";
        Pet pet = new Pet();
        pet.setName(petName);
        pet.setAge(8);
        pet.setType("Dog");
        pet.setSoftDelete(false);
        readOnlyDAO.add(pet);

        // WHEN
        Pet retrievedPet = readOnlyDAO.get(petName);

        // THEN
        assertNull(retrievedPet, "Read only Dao should not be able to add!");
    }

    @Test
    @SneakyThrows
    public void updatePetDoNothing() {
        // GIVEN
        String petName = "Daisy";
        Pet pet = new Pet();
        pet.setName(petName);
        pet.setAge(8);
        pet.setType("Dog");
        pet.setSoftDelete(false);
        readWriteDAO.add(pet);

        // WHEN
        pet.setAge(1);
        readOnlyDAO.update(pet);

        // THEN
        assertEquals(8, readOnlyDAO.get(petName).getAge(), "Pet Daisy's age should still be 8.");

    }

    @Test
    public void deletePetDoNothing() {
        // GIVEN
        String petName = "New Pet";
        Pet pet = new Pet();
        pet.setName(petName);
        pet.setAge(2);
        pet.setType("Cat");
        pet.setSoftDelete(false);
        readWriteDAO.add(pet);

        // WHEN
        readOnlyDAO.delete(pet);

        // THEN
        assertEquals(petName, readOnlyDAO.get(petName).getName(), "The pet should not have been deleted.");

    }

    @Test
    @SneakyThrows
    public void softDeletePetDoNothing() {
        // GIVEN
        String petName = "DefaultPet";
        Pet pet = new Pet();
        pet.setName(petName);
        pet.setSoftDelete(false);
        readWriteDAO.add(pet);
        System.out.println("Name: " + readOnlyDAO.get(petName).getName() + " Soft deleted? " + readOnlyDAO.get(petName).isSoftDelete());

        // WHEN
        readOnlyDAO.sofeDelete(pet);

        // THEN
        assertFalse(readOnlyDAO.get(petName).isSoftDelete() );

    }

}
