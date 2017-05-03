package com.monolith.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by filcasidy on 03.05.17.
 */
class PersonTest {


    @Test
    void testing() {
        Person person = new Person("first", "second");
        System.out.println(person.toString());
    }
}