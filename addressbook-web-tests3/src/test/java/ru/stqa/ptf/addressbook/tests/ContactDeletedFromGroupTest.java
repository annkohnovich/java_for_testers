package ru.stqa.ptf.addressbook.tests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class ContactDeletedFromGroupTest extends TestBase{

    @Test
    public void testContactDeletedFromGroup() {
        List<ContactData> allContacts = app.db().contactsList();
        ContactData contact = app.db().contacts().iterator().next();
        int index = allContacts.indexOf(contact);
        GroupData groupToDelete = getGroupToDelete(contact);
        int groupId = groupToDelete.getId();

        ContactData updatedContactBefore = updateContact(index);
        Groups userGroupsBefore = updatedContactBefore.getGroups();

        app.goTo().homePage();
        app.contact().selectGroupById(groupId);
        app.contact().selectContactById(contact.getId());
        app.contact().submitDeletingGroup();
        app.goTo().homePageForGroup(groupToDelete.getName());
        app.goTo().returnToAllContactsList();

        assertFalse(app.contact().isThereAContact(contact.getId()));

        ContactData updatedContactAfter = updateContact(index);
        Groups userGroupsAfter = updatedContactAfter.getGroups();
        assertEquals (userGroupsAfter.size(), userGroupsBefore.size() - 1);
        assertThat(userGroupsAfter, equalTo(userGroupsBefore.without(groupToDelete)));

    }

    private ContactData updateContact (int index){
        List<ContactData> refreshedContacts = app.db().contactsList();
        return refreshedContacts.get(index);
    }

    private GroupData getGroupToDelete(ContactData contact) {
        if (contact.getGroups().isEmpty()) {
            GroupData groupToAdd;
            if (app.db().groups().isEmpty()) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("test3"));
                groupToAdd = app.db().groupWithMaxId();
            } else {
                groupToAdd = app.db().groups().iterator().next();
            }
            app.goTo().homePage();
            app.contact().selectContactById(contact.getId());
            app.contact().selectGroupByIdToAdd(groupToAdd.getId());
            app.contact().submitAddingGroup();
            return groupToAdd;
        } else {
            return contact.getGroups().iterator().next();
        }
    }


}
