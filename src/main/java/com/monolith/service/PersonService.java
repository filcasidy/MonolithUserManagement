package com.monolith.service;

import com.monolith.domain.Person;

import java.util.List;

/**
 * The person interface with different interactions.
 */
public interface PersonService {

    /**
     * Find all persons.
     *
     * @return the list with all found persons
     */
    List<Person> findAll();


    /**
     * Find the specific person.
     *
     * @param id of the searched person
     * @return the searched person
     */
    Person findOne(Long id);

    /**
     * Creates a person.
     *
     * @param person to be created
     * @return the created person
     */
    Person create(Person person);

    /**
     * Updates the person.
     *
     * @param person to be updated
     * @return the updated person
     */
    Person update(Person person);

    /**
     * Delete specific person
     *
     * @param id of the person which should be deleted
     */
    void delete(long id);
}
