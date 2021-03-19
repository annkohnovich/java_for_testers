package ru.stqa.ptf.addressbook;

import org.testng.annotations.*;

public class ContactDeletion extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    goToHomePage();
    selectContact();
    deleteSelectedContact();
    confirmContactDeletion();
    returnToHomePage();
  }
}
