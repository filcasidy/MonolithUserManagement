package com.monolith.controller;

import com.monolith.domain.User;
import com.monolith.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Rest-Controller of the user.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Get all users route.
     *
     * @return the list with all users
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<User>> getUsers() {
        Collection<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Get the specific user.
     *
     * @param username the name of the user
     * @return the user with all information
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> getUser(@RequestParam("username") String username) {
        return new ResponseEntity<>(userService.findUserByUsername(username), HttpStatus.OK);
    }

}
