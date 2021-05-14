package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.Set;

public class ContactModification extends TestBase {
    @BeforeMethod
    public void ensurePreconditions () {
        app.goTo().homePage();
        if (app.contact().all().size()==0) {
            app.contact().create(new ContactData().withFirstName("Hanna").withLastName("Kakhnovich").withMobilePhone("+375293650345").withEmail("annkohnovich@gmail.com"));
        }
    }

    @Test
    public void testContactModification () {
        Set <ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Hanna").withLastName("Kakhnovich");
        app.contact().modify(contact);
        Set <ContactData> after = app.contact().all();
        Assert.assertEquals (after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);

    }


}
