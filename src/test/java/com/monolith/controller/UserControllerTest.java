package com.monolith.controller;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.runner.RunWith;

@RunWith(PactRunner.class) // Say JUnit to run tests with custom Runner
@Provider("user_service_provider") // Set up name of tested provider
@PactFolder("pacts")
public class UserControllerTest {

    @TestTarget // Annotation denotes Target that will be used for tests
    public final Target target = new HttpTarget(8083); // Out-of-the-box implementation of Target (for more information take a look at Test Target section)
}
