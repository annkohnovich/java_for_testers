package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletion extends TestBase {
  @BeforeMethod
  public void ensurePreconditions () {
    app.goTo().homePage();
    if (app.contact().all().size()==0) {
      app.contact().create(new ContactData().withFirstName("Hanna").withLastName("Kakhnovich").withMobilePhone("+375293650345").withEmail("annkohnovich@gmail.com"));
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    Set <ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Set <ContactData> after = app.contact().all();
    Assert.assertEquals (after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }


}
