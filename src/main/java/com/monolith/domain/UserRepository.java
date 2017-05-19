package com.monolith.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository of a user.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
