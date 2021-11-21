package com.iluwater.dao.factory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.iluwatar.dao.factory.DAO;
import com.iluwatar.dao.factory.DaoFactory;
import com.iluwatar.dao.factory.DaoType;
import com.iluwatar.dao.factory.DynamoDBProvider;
import com.iluwatar.dao.factory.Pet;
import com.iluwatar.dao.factory.PetNotFoundException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ReadWriteDAOImplTest {
    private static final AmazonDynamoDB dynamoDB = DynamoDBProvider.getDynamoDB();
    private final DAO readWriteDAO = DaoFactory.getDAO(DaoType.ReadWrite, dynamoDB);

    @BeforeEach
    public void setup() {
        String petName = "DefaultPet";
        Pet pet = new Pet();
        pet.setName(petName);
        pet.setAge(5);
        pet.setType("Dog");
        pet.setSoftDelete(false);
        readWriteDAO.add(pet);
    }

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
        Pet retrievedPet = readWriteDAO.get(petName);

        // THEN
        assertEquals(petName, retrievedPet.getName(), "We got the wrong pet!");
    }

    @Test
    public void getPetNonExistPetReturnNull() {
        // GIVEN
        String petName = "Fake Pet";

        // WHEN & THEN
        Pet pet = readWriteDAO.get(petName);

        // THEN
        assertNull(pet);

    }

    @Test
    @SneakyThrows
    public void updatePetReturnUpdatedPet() {
        // GIVEN
        String petName = "Blacky";
        Pet pet = new Pet();
        pet.setName(petName);
        pet.setAge(3);
        pet.setType("Deer");
        pet.setSoftDelete(false);
        readWriteDAO.add(pet);

        // WHEN
        pet.setAge(4);
        readWriteDAO.update(pet);

        Pet updatedPet = readWriteDAO.get(petName);

        // THEN
        assertEquals(petName, updatedPet.getName());  // expected name to be the same
        assertEquals(4, updatedPet.getAge());  // expected age to be 4 now
        assertEquals("Deer", updatedPet.getType());  // expected pet type to stay the same

    }


    @Test
    public void updatePetNonExistPetThrowsException() throws PetNotFoundException {
        // GIVEN
        String petName = "Fake Pet";
        Pet pet = new Pet();
        pet.setName(petName);
        pet.setAge(3);
        pet.setType("Deer");
        pet.setSoftDelete(false);

        // WHEN & THEN
        assertThrows(PetNotFoundException.class, () ->
                readWriteDAO.update(pet), "PetNotFoundException was expected.");

    }

    @Test
    @SneakyThrows
    public void deletePetSuccessfullyDeletedPet() {
        // GIVEN
        String petName = "DefaultPet";
        Pet pet = new Pet();
        pet.setName(petName);
        pet.setAge(5);
        pet.setType("Dog");
        pet.setSoftDelete(false);
        System.out.println("Existing: " + readWriteDAO.get(petName).getName());

        // WHEN
        readWriteDAO.delete(pet);

        // THEN - after delete, should not find DefaultPet anymore
        Pet retrievedPet = readWriteDAO.get(petName);
        assertNull(retrievedPet);

    }

    @Test
    public void deletePetNonExistPetDoNothing() {
        // GIVEN
        String petName = "Non-exist Pet";
        Pet pet = new Pet();
        pet.setName(petName);
        pet.setAge(5);
        pet.setType("Dog");
        pet.setSoftDelete(false);
        Pet nonExistPet = readWriteDAO.get(petName);

        // WHEN
        readWriteDAO.delete(pet);

        // THEN - the pet does not exist before or after the delete operation
        assertNull(nonExistPet);
        assertNull(readWriteDAO.get(petName));

    }

    @Test
    @SneakyThrows
    public void softDeletePetSuccessfullySoftDeletedPet() {
        // GIVEN
        String petName = "DefaultPet";
        Pet pet = new Pet();
        pet.setName(petName);
        System.out.println("Name: " + readWriteDAO.get(petName).getName() + " Soft deleted? " + readWriteDAO.get(petName).isSoftDelete());

        // WHEN
        readWriteDAO.sofeDelete(pet);

        // THEN
        System.out.println("Name: " + readWriteDAO.get(petName).getName() + " Soft deleted? " + readWriteDAO.get(petName).isSoftDelete());
        Pet retrievedPet = readWriteDAO.get(petName);
        assertTrue(retrievedPet.isSoftDelete());

    }

    @Test
    public void softDeletePetNonExistPetThrowsException() {
        // GIVEN
        String petName = "FAKE PET";
        Pet pet = new Pet();
        pet.setName(petName);

        // WHEN & THEN
        assertThrows(PetNotFoundException.class, () ->
                readWriteDAO.sofeDelete(pet), "PetNotFoundException was expected.");

    }


}
