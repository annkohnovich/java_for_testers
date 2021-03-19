package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.*;

public class ContactDeletion extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().confirmContactDeletion();
    app.getNavigationHelper().returnToHomePage();
  }
}
