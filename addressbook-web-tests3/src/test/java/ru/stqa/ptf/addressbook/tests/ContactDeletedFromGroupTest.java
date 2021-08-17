package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ContactDeletedFromGroupTest extends TestBase{

    @Test
    
    public void testContactDeletedFromGroup(){

        List<ContactData> allContacts = app.db().contactsList();
        ContactData contact = app.db().contacts().iterator().next();
        int contactId = contact.getId();
        int index = allContacts.indexOf(contact);
        GroupData groupToDelete = null;

        if (contact.getGroups().isEmpty()){
            GroupData groupToAdd;
            if (app.db().groups().isEmpty()) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("test3"));
                groupToAdd = app.db().groupWithMaxId();
            } else {
                groupToAdd = app.db().groups().iterator().next();
            }
            app.goTo().homePage();
            app.contact().selectContactById(contactId);
            app.contact().selectGroupByIdToAdd(groupToAdd.getId());
            app.contact().submitAddingGroup();
            groupToDelete = groupToAdd;

        } else {
            groupToDelete = contact.getGroups().iterator().next();
        }

        int groupId = groupToDelete.getId();
        Groups userGroupsBefore = contact.getGroups();
        app.goTo().homePage();
        app.contact().selectGroupById(groupId);
        app.contact().selectContactById(contactId);
        app.contact().submitDeletingGroup();
        app.goTo().homePageForGroup(groupToDelete.getName());

        assertFalse(app.contact().isThereAContact(contactId));


    }
}
