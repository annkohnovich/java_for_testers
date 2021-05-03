package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;

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

        group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
