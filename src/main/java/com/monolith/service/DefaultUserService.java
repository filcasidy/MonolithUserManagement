package com.monolith.service;

import java.util.List;

import com.monolith.domain.User;
import com.monolith.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Default implementation of a person.
 */
@Service
public class DefaultUserService implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User addPerson(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User create(User user) {

        if (user.getId() != null) {
            return null;
        }
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setPerson(user.getPerson());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public User update(User user) {
        User persistedUser = userRepository.findOne(user.getId());

        if (persistedUser == null) {
            return null;
        }

        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " does not exist!");
        }
        return new UserPrincipal(user);
    }
}
