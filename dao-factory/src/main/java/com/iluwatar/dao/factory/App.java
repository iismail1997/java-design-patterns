package com.iluwatar.dao.factory;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;

/**
 * According to https://www.oreilly.com/library/view/j2ee-design-patterns/0596004273/re15.html,
 * A DAO Factory is to hide the process of selecting an appropriate persistence layer or set
 * DAO objects from the business tier, and to allow configuration of DAO features at runtime
 * in a centralized manner.
 * A DAO Factory Determines how to create the DAO object.
 * The following class demonstrates how different DAOs are generated via the Dao Factory,
 * and how each dao behaves differently. The Daos interact with DB to perform basic CRUD operations.
 */
public final class App {
    private App(){}

    /**
     * Entry point of the dao factory program.
     * @param args
     * @throws PetNotFoundException
     */
    public static void main(final String[] args) throws PetNotFoundException {
        AmazonDynamoDB dynamoDB = DynamoDBProvider.getDynamoDB();
        DAO readonlyDao = DaoFactory.getDAO(DaoType.ReadOnly, dynamoDB);
        DAO readWriteDao = DaoFactory.getDAO(DaoType.ReadWrite, dynamoDB);
        Pet pet = generatePet();
        readWriteDao.add(pet);
        System.out.println("Pet " + pet.getName() + " is added to DB. It's a " + pet.getAge() + "" +
                " year old " + pet.getType());

        pet.setAge(3);
        readWriteDao.update(pet);
        readonlyDao.get(pet.getName());
        System.out.println("Pet " + pet.getName() + " is now " +readonlyDao.get(pet.getName()).getAge()+ " year old!");

        readWriteDao.delete(pet);
        System.out.println("Pet " + pet.getName() + " is now deleted.");

        dynamoDB.shutdown();
        System.out.println("Shutting down DB..");

    }
    private static Pet generatePet() {
        Pet pet = new Pet();
        pet.setName("Kitty");
        pet.setAge(1);
        pet.setType("Cat");
        pet.setSoftDelete(false);

        return pet;
    }

}
