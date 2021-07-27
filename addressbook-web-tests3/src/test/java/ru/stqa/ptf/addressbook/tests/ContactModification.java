package ru.stqa.ptf.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;

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
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Hanna").withLastName("Kakhnovich");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        Assert.assertEquals (after.size(), before.size());

        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(contact)));

    }


}
