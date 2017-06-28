package com.monolith.service;

import com.monolith.domain.Person;
import com.monolith.domain.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default implementation of a person.
 */
@Service
public class DefaultPersonService implements PersonService {

    @Autowired
    PersonRepository personRepository;

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findOne(Long id) {
        return personRepository.findOne(id);
    }

    @Override
    public Person create(Person person) {
        if (person.getId() != null) {
            return null;
        }
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        Person persistedPerson = personRepository.findOne(person.getId());

        if (persistedPerson == null) {
            return null;
        }
        return personRepository.save(person);
    }

    @Override
    public void delete(long id) {
        personRepository.delete(id);
    }
}
