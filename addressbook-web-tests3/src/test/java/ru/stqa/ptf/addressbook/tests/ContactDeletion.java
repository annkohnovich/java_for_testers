package ru.stqa.ptf.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

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
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.contact().all();
    assertEquals (after.size(), before.size() - 1);

    assertThat(after, equalTo(before.without(deletedContact)));
  }


}
