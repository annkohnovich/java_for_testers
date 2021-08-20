package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{
    @Test
    public void testRegistration() {
        String username = "user1";
        String email = "user1@localhost.localdomain";
        app.registration().start(username, email);
    }
}
