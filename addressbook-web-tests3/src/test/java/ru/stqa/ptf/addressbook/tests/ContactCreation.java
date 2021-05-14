package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreation extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Set <ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstName("Hanna").withLastName("Kakhnovich").withMobilePhone("+375293650345").withEmail("annkohnovich@gmail.com");
    app.contact().create(contact);
    Set <ContactData> after = app.contact().all();
    Assert.assertEquals (after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
