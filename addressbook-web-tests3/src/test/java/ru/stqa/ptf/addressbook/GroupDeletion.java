package ru.stqa.ptf.addressbook;

import org.testng.annotations.*;

public class GroupDeletion extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    gotoGroupPage();
    selectGroup();
    deleteSelectedGroup();
    returnToGroupPage();
  }

}
