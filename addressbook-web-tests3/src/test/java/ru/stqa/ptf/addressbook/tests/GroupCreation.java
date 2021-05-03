package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreation extends TestBase {

    @Test
  public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        List <GroupData> before = app.getGroupHelper().getGroupList ();
        app.getGroupHelper().initGroupCreation();
        GroupData group = new GroupData("name", "header", "footer");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupCreation();
        app.getGroupHelper().returnToGroupPage();
        List <GroupData> after = app.getGroupHelper().getGroupList ();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
  }
}
