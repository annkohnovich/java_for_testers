package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.GroupData;

public class ContactModification extends TestBase {
    @Test
    public void testContactModification () {
        app.getNavigationHelper().goToHomePage();
        int before = app.getContactHelper().getContactCount();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createAContact(new ContactData("Hanna", "Kakhnovich", "+375293650345", "annkohnovich@gmail.com"));
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Hanna", "Kakhnovich", "+375293650345", "annkohnovich@gmail.com"));
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals (after, before);
    }
}
