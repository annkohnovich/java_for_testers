package ru.stqa.ptf.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class GroupDeletion extends TestBase {
  @BeforeMethod
  public void ensurePreconditions () {
    app.goTo().groupPage();
    if (app.group().all().size()==0) {
      app.group().create(new GroupData().withName("test2"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Groups after = app.group().all();
    assertEquals(after.size(), before.size() - 1);

    assertThat(after, equalTo(before.without(deletedGroup)));
  }



}
