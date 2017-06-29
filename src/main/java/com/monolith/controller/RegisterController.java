package com.monolith.controller;

import java.util.List;

import com.monolith.domain.Person;
import com.monolith.domain.User;
import com.monolith.service.PersonService;
import com.monolith.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest-Controller for registration.
 */
@RestController
@Api(value = "RegisterController", description = "Rest-Controller for registration.")
public class RegisterController {

    private static final String NAME = "name";
    private static final String LASTNAME = "lastname";
    private static final String EMAIL = "email";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @Autowired
    PersonService personService;

    @Autowired
    UserService userService;

    /**
     * Registration of a user route.
     *
     * @param userInformationMap contains name, lastname, email, username and password
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<HttpStatus> registerUser(@RequestBody MultiValueMap<String, String> userInformationMap) {
        Person person = null;
        List<String> name = userInformationMap.get(NAME);
        List<String> lastname = userInformationMap.get(LASTNAME);
        List<String> email = userInformationMap.get(EMAIL);
        List<String> username = userInformationMap.get(USERNAME);
        List<String> password = userInformationMap.get(PASSWORD);
        if (!name.isEmpty() || !lastname.isEmpty() || !email.isEmpty()) {
            person = personService.create(new Person(name.get(0), lastname.get(0), email.get(0)));
        }
        userService.create(new User(username.get(0), password.get(0), person));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
