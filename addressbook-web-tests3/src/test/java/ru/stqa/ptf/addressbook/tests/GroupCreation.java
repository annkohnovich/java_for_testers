package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;

public class GroupCreation extends TestBase {

    @Test
  public void testGroupCreation() throws Exception {
      app.gotoGroupPage();
      app.initGroupCreation();
      app.fillGroupForm(new GroupData("name", "header", "footer"));
      app.submitGroupCreation();
      app.returnToGroupPage();
  }

}
