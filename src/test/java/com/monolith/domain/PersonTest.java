package com.monolith.domain;

import org.junit.jupiter.api.Test;

/**
 * Created by filcasidy on 03.05.17.
 */
class PersonTest {


    @Test
    void testing() {
        Person person = new Person("first", "second", "email@bla.com");
        System.out.println(person.toString());
    }
}