package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.*;

public class ContactDeletion extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    app.goToHomePage();
    app.selectContact();
    app.deleteSelectedContact();
    app.confirmContactDeletion();
    app.returnToHomePage();
  }
}
