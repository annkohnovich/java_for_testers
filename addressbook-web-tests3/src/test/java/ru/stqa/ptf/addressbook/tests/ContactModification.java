package ru.stqa.ptf.addressbook.tests;

import com.google.gson.Gson;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.json.TypeToken;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactModification extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.db().contacts().size() ==0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName("Hanna")
                    .withLastName("Kakhnovich").withMobilePhone("+375293650345").withEmail("annkohnovich@gmail.com"));
        }
    }

    @Test
    public void testContactModification () {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Hanna")
                .withLastName("Kakhnovich").withEmail("annkohnovich@gmail.com").withAddress("adress")
                .withHomePhone("333-55-88").withMobilePhone("222-88-96").withWorkPhone("1114477");
        app.goTo().homePage();
        app.contact().modify(contact);
        Assert.assertEquals (app.contact().count(), before.size());
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();

    }


}
