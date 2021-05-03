package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.GroupData;

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

/*    int max =0;
    for (ContactData c : after) {
      if (c.getId()>max) {
        max = c.getId();
      }
    }*/

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
