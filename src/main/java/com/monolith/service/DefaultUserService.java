package com.monolith.service;

import com.monolith.domain.User;
import com.monolith.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Default implementation of a person.
 */
@Service
public class DefaultUserService implements UserService {

    @Autowired
    UserRepository userRepository;

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
    public User create(User user) {

        if (user.getId() != null) {
            return null;
        }
        return userRepository.save(user);
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
}
