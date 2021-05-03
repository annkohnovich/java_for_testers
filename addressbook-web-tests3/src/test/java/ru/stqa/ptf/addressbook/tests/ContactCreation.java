package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreation extends TestBase {

  @Test
  public void testContactCreation() {
    List <ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactCreation();
    ContactData contact = new ContactData("Hanna", "Kakhnovich", "+375293650345", "annkohnovich@gmail.com");
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().goToHomePage();
    List <ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals (after.size(), before.size() + 1);
    
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
