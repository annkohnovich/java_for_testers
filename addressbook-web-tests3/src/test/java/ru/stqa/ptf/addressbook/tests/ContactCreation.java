package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.List;

public class ContactCreation extends TestBase {

  @Test
  public void testContactCreation() {
    List <ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Hanna", "Kakhnovich", "+375293650345", "annkohnovich@gmail.com"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().goToHomePage();
    List <ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals (after.size(), before.size() + 1);
  }
}
