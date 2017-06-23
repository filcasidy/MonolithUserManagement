package com.monolith.controller;

import javax.servlet.http.HttpServletRequest;

import com.monolith.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    SecurityService securityService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    ResponseEntity<HttpStatus> loginUser(HttpServletRequest request) {
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }
}
