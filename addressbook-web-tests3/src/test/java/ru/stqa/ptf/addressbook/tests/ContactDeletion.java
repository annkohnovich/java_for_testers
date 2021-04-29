package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactDeletion extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationHelper().goToHomePage();
    int before = app.getContactHelper().getContactCount();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createAContact(new ContactData("Hanna",
              "Kakhnovich",
              "+375293650345",
              "annkohnovich@gmail.com"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().confirmContactDeletion();
    app.getNavigationHelper().goToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals (after, before-1);
  }
}
