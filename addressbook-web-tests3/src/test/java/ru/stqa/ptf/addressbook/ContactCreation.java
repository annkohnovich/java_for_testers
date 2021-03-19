package ru.stqa.ptf.addressbook;

import org.testng.annotations.*;

public class ContactCreation extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillContactForm(new ContactData("Hanna", "Kakhnovich", "+375293650345", "annkohnovich@gmail.com"));
    submitCreation();
    returnToHomePage();
  }
}
