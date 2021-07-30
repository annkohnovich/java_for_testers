package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactCreation extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/image.png");
    ContactData contact = new ContactData()
            .withFirstName("Hanna").withLastName("Kakhnovich").withMobilePhone("+375293650345")
            .withEmail("annkohnovich@gmail.com")
            .withPhoto(photo);
    app.contact().create(contact);
    assertEquals (app.contact().count(), before.size() + 1);
    Contacts after = app.contact().all();

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
