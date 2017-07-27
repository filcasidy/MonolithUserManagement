package com.monolith.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository of a person.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
