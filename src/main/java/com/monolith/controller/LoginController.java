package com.monolith.controller;

import javax.servlet.http.HttpServletRequest;

import com.monolith.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    SecurityService securityService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    boolean loginUser(HttpServletRequest request) {
        return securityService.loginUser(request.getParameter("username"), request.getParameter("password"));
    }
}
