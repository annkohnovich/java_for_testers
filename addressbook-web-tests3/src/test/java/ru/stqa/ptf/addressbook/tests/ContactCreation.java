package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreation extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    List <ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withFirstName("Hanna").withLastName("Kakhnovich").withMobilePhone("+375293650345").withEmail("annkohnovich@gmail.com");
    app.contact().create(contact);
    List <ContactData> after = app.contact().list();
    Assert.assertEquals (after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
