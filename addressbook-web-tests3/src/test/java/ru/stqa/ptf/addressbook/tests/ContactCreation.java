package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactCreation extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Hanna", "Kakhnovich", "+375293650345", "annkohnovich@gmail.com"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().goToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals (after, before+1);
  }
}
