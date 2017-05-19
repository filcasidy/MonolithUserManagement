package com.monolith.service;

import com.monolith.domain.User;

import java.util.List;

/**
 * The user interface with different interactions.
 */
public interface UserService {

    /**
     * Find all users.
     *
     * @return the list with all found users
     */
    List<User> findAll();


    /**
     * Find the specific user.
     *
     * @param id of the searched user
     * @return the searched user
     */
    User findOne(Long id);

    /**
     * Find the person by the username.
     *
     * @param userName the name of the user
     * @return the person of this user
     */
    User findUserByUsername(String userName);

    /**
     * Creates a person.
     *
     * @param user to be created
     * @return the created user
     */
    User create(User user);

    /**
     * Updates the user.
     *
     * @param user to be updated
     * @return the updated person
     */
    User update(User user);

    /**
     * Delete specific user
     *
     * @param id of the user which should be deleted
     */
    void delete(long id);
}
