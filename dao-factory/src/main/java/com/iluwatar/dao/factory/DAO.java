package com.iluwatar.dao.factory;

/**
 * DAO interface
 * Allows concrete dao classes to implement CRUD operations
 */
public interface DAO {

    Pet get(String name);

    void add(Pet pet);

    void update(Pet pet) throws PetNotFoundException;

    void delete(Pet pet);

    void sofeDelete(Pet pet) throws PetNotFoundException;
}
