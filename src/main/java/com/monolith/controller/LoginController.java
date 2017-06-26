package com.monolith.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * The controller for login.
 */
@RestController
public class LoginController {

    /**
     * Login route.
     *
     * @param request with authentication
     * @return ResponseEntity with status
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    ResponseEntity<HttpStatus> loginUser(HttpServletRequest request) {
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }
}
