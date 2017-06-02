package com.monolith.controller;

import com.monolith.domain.User;
import com.monolith.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Rest-Controller of the user.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

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

    @RequestMapping(value = "/create/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> createUser(HttpServletRequest request) {
        User user = new User();
        //todo Daten müssen aus dem Requestbody geholt werden
        user.setUsername(request.getHeader("username"));
        user.setPassword(passwordEncoder.encode(request.getHeader("password")));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    void createUserd(@RequestBody MultiValueMap<String, String> formData) {
        System.out.println("bekommen");
        System.out.println(formData);
    }

}
