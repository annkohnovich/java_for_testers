package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletion extends TestBase {
  @BeforeMethod
  public void ensurePreconditions () {
    app.goTo().homePage();
    if (app.contact().list().size()==0) {
      app.contact().create(new ContactData(
              "Hanna",
              "Kakhnovich",
              "+375293650345",
              "annkohnovich@gmail.com"));
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    List<ContactData> before = app.contact().list();
    int index = before.size() -1;
    app.contact().delete(index);
    List <ContactData> after = app.contact().list();
    Assert.assertEquals (after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }


}
