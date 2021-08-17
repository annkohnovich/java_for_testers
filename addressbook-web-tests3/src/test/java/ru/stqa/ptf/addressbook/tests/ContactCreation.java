package ru.stqa.ptf.addressbook.tests;

import com.google.gson.Gson;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import javax.swing.plaf.basic.BasicButtonUI;
import java.io.*;
import java.nio.Buffer;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactCreation extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"));
    String json = "";
    String line = reader.readLine();
    while (line != null){
      json = json + line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
    return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
  }

  @Test (dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    if (app.db().groups().size() ==0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test2"));
    }
//    Groups groups = app.db().groups();
//    contact.inGroup(groups.iterator().next());
    Contacts before = app.db().contacts();
    //File photo = new File("src/test/resources/image.png");
    app.contact().create(contact);
    assertEquals (app.contact().count(), before.size() + 1);
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((с) -> с.getId()).max().getAsInt()))));
    verifyContactListInUI();
  }
}
