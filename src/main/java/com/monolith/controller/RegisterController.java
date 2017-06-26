package com.monolith.controller;

import com.monolith.domain.Person;
import com.monolith.domain.User;
import com.monolith.service.PersonService;
import com.monolith.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for registration.
 */
@RestController
public class RegisterController {

    @Autowired
    PersonService personService;

    @Autowired
    UserService userService;

    /**
     * Registration of a user route.
     *
     * @param userInformationMap contains name, lastname, email, username and password
     */
    @RequestMapping(value = "/register-user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<HttpStatus> registerUser(@RequestBody MultiValueMap<String, String> userInformationMap) {
        Person person = null;
        List<String> name = userInformationMap.get("name");
        List<String> lastname = userInformationMap.get("lastname");
        List<String> email = userInformationMap.get("email");
        List<String> username = userInformationMap.get("username");
        List<String> password = userInformationMap.get("password");
        if (!name.isEmpty() || !lastname.isEmpty() || !email.isEmpty()) {
            person = personService.create(new Person(name.get(0), lastname.get(0), email.get(0)));
        }
        userService.create(new User(username.get(0), password.get(0), person));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
