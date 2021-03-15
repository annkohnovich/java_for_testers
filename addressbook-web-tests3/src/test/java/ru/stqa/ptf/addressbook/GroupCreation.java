package ru.stqa.ptf.addressbook;

import org.testng.annotations.*;

public class GroupCreation extends TestBase {


    @Test
  public void testGroupCreation() throws Exception {
      gotoGroupPage();
      initGroupCreation();
      fillGroupForm(new GroupData("name", "header", "footer"));
      submitGroupCreation();
      returnToGroupPage();
  }

}
