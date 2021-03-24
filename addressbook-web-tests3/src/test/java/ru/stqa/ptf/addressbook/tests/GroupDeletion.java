package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletion extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("name", "header", "footer"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList ();
    app.getGroupHelper().selectGroup(0);
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returnToGroupPage();
    List <GroupData> after = app.getGroupHelper().getGroupList ();
    Assert.assertEquals(after.size(), before.size() - 1);
  }

}
