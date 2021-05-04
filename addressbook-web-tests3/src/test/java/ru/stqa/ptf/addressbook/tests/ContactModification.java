package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModification extends TestBase {
    @Test
    public void testContactModification () {
        app.goTo().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createAContact(new ContactData("Hanna", "Kakhnovich", "+375293650345", "annkohnovich@gmail.com"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() -1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Hanna", "Kakhnovich", "+375293650345", "annkohnovich@gmail.com");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitContactModification();
        app.goTo().goToHomePage();
        List <ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals (after.size(), before.size());

        before.remove(before.size() -1);
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
