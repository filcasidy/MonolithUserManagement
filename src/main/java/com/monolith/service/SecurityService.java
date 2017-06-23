package com.monolith.service;

import com.monolith.domain.User;
import com.monolith.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    //    public User getAuthenticatedUser() {
    //        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //        if (principal instanceof UserDetails) {
    //            String username = ((UserDetails) principal).getUsername();
    //            return userService.findUserByUsername(username);
    //        } else {
    //            String username = principal.toString();
    //            return userService.findUserByUsername(username);
    //        }
    //    }

    //    public boolean loginUser(String username, String password) {
    //        try {
    //            Authentication token = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    //            SecurityContextHolder.getContext().setAuthentication(token);
    //            return true;
    //        } catch (AuthenticationException e) {
    //            return false;
    //        }
    //    }

    public boolean usernameAndPasswordCorrect(String username, String password) {
        User user = userService.findUserByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return true;
        } else {
            return false;
        }

    }
}
