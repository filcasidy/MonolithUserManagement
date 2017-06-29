package com.monolith.controller;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest-Controller for login.
 */
@RestController
@Api(value = "LoginController", description = "Rest-Controller for login.")
public class LoginController {

    /**
     * Login route.
     *
     * @param request with authentication
     * @return ResponseEntity with status
     */
    @ApiOperation(value = "Login the user.", response = HttpStatus.class)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "User logged in successfully."),
            @ApiResponse(code = 401, message = "Bad credentials.")
    })
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    ResponseEntity<HttpStatus> loginUser(HttpServletRequest request) {
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }
}
