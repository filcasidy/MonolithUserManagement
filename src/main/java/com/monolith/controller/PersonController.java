package com.monolith.controller;

import com.monolith.domain.Person;
import com.monolith.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Rest-Controller of the person.
 */
@RestController
@Api(value = "PersonController", description = "Rest-Controller of the person.")
public class PersonController {

    @Autowired
    PersonService personService;

    /**
     * Get all persons route.
     *
     * @return the list with all persons
     */
    @ApiOperation(value = "Get a list with all persons.", response = Collection.class)
    @RequestMapping(value = "/persons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<Person>> getPersons() {
        Collection<Person> persons = personService.findAll();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    /**
     * Create person route.
     *
     * @param person the {@link Person} object
     * @return JSON-Response of the new Person
     */
    @ApiOperation(value = "Create a person object and persist it.", response = Person.class)
    @RequestMapping(value = "/person/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person saved = personService.create(person);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /**
     * Edit person route.
     *
     * @param person the edited {@link Person} object
     * @return JSON-Response of the edited Person
     */
    @ApiOperation(value = "Update a person in the database.", response = Person.class)
    @RequestMapping(value = "/person/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Person> editPerson(@RequestBody Person person) {
        Person updated = personService.update(person);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
