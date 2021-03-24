package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.List;

public class GroupModification extends TestBase {

    @Test
    public void testGroupModification () {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("name", "header", "footer"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList ();
        app.getGroupHelper().selectGroup(0);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("name", "header", "footer"));
        app.getGroupHelper().submitGroupModofocation();
        app.getGroupHelper().returnToGroupPage();
        List <GroupData> after = app.getGroupHelper().getGroupList ();
        Assert.assertEquals(after.size(), before.size());
    }

}
