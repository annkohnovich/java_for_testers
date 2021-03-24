package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.List;

public class GroupCreation extends TestBase {

    @Test
  public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        List <GroupData> before = app.getGroupHelper().getGroupList ();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("name", "header", "footer"));
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
        List <GroupData> after = app.getGroupHelper().getGroupList ();
        Assert.assertEquals(after.size(), before.size() + 1);
  }

}
