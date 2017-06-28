package com.monolith.service;

import com.monolith.domain.User;
import com.monolith.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User persistedUser = userRepository.findOne(user.getId());

        if (persistedUser == null) {
            return null;
        }
        if (user.getPassword() == null) {
            user.setPassword(persistedUser.getPassword());
        }
        if (user.getPerson() == null) {
            user.setPerson(persistedUser.getPerson());
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
