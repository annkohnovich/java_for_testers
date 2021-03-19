package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactCreation extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Hanna", "Kakhnovich", "+375293650345", "annkohnovich@gmail.com"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomePage();
  }
}
