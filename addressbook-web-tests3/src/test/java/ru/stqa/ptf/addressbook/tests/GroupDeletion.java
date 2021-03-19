package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.*;

public class GroupDeletion extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.gotoGroupPage();
    app.selectGroup();
    app.deleteSelectedGroup();
    app.returnToGroupPage();
  }

}
