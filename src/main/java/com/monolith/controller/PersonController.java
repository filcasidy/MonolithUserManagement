package com.monolith.controller;

import java.util.Collection;

import com.monolith.domain.Person;
import com.monolith.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest-Controller of the person.
 */
@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    /**
     * Get all persons route.
     * @return the list with all persons
     */
    @RequestMapping(value = "/persons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<Person>> getPersons() {
        Collection<Person> persons = personService.findAll();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    /**
     * Create person route.
     * @param firstName of the person
     * @param lastName of the person
     * @return JSON-Response of the new Person
     */
    @RequestMapping(value = "/create/person", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Person> createPerson(@RequestParam(name = "firstname") String firstName, @RequestParam(name = "lastname") String lastName) {
        Person saved = personService.create(new Person(firstName, lastName));
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
