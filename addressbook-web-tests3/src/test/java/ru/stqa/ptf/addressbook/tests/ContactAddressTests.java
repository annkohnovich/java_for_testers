package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

    @Test
    public void testContactAddress() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactDataFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactDataFromEditForm.getAddress()));
    }
}
