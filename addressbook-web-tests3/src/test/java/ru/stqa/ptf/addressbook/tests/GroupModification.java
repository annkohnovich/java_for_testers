package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.GroupData;

public class GroupModification extends TestBase {

    @Test
    public void testGroupModification () {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("name", "header", "footer"));
        app.getGroupHelper().submitGroupModofocation();
        app.getGroupHelper().returnToGroupPage();
    }

}
