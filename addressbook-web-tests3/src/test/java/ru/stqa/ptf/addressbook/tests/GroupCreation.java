package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;

public class GroupCreation extends TestBase {

    @Test
  public void testGroupCreation() throws Exception {
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().initGroupCreation();
      app.getGroupHelper().fillGroupForm(new GroupData("name", "header", "footer"));
      app.getGroupHelper().submitGroupCreation();
      app.getGroupHelper().returnToGroupPage();
  }

}
