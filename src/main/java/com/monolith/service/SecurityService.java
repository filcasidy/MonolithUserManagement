package com.monolith.service;

import com.monolith.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Checks if the username is available and if the password fits to the user.
     *
     * @param username Username
     * @param password Password
     * @return Boolean if the username and password are correct.
     */
    public boolean usernameAndPasswordCorrect(String username, String password) {
        User user = userService.findUserByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return true;
        } else {
            return false;
        }

    }
}
