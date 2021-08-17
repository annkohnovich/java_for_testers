package ru.stqa.ptf.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ContactAddedToGroupTest extends TestBase{


    @Test
    public void testContactAddedToGroup(){
        List<ContactData> allContacts = app.db().contactsList();
        ContactData contact = allContacts.iterator().next();
        int id = contact.getId();
        int index = allContacts.indexOf(contact);

        Groups allGroups = app.db().groups();
        Groups userGroupsBefore = contact.getGroups();
        allGroups.removeAll(userGroupsBefore);

        GroupData groupToAdd;

        if (allGroups.size() == 0) {
            GroupData newGroup = new GroupData().withName("test2");
            app.goTo().groupPage();
            app.group().create(newGroup);
            groupToAdd = app.db().groupWithMaxId();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            groupToAdd = allGroups.iterator().next();
        }

        int groupId = groupToAdd.getId();
        app.goTo().homePage();
        app.contact().selectContactById(id);
        app.contact().selectGroupByIdToAdd(groupId);
        app.contact().submitAddingGroup();
        app.goTo().homePageForGroup(groupToAdd.getName());

        assertTrue(app.contact().isThereAContact(id));

        List<ContactData> refreshedContacts = app.db().contactsList();
        ContactData selectedContact = refreshedContacts.get(index);
        Groups userGroupsAfter = selectedContact.getGroups();
        assertEquals (userGroupsAfter.size(), userGroupsBefore.size() + 1);

        assertThat(userGroupsAfter, equalTo(userGroupsBefore.withAdded(groupToAdd)));





    }
}
