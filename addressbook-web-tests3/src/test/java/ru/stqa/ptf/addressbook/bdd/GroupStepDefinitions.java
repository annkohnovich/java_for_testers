package ru.stqa.ptf.addressbook.bdd;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.ptf.addressbook.appmanager.ApplicationManager;
import ru.stqa.ptf.addressbook.model.GroupData;
import ru.stqa.ptf.addressbook.model.Groups;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupStepDefinitions {
    private ApplicationManager app;
    private Groups groups;
    private GroupData group;

    @Before
    public void init() throws IOException {
        app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
        app.init();
    }

    @Given("^a set of groups$")
    public void loadGroups(){
        groups = app.db().groups();
    }

    @When("^I create a new group with name (.+), header (.+) and footer (.+)$")
    public void createGroup(String name, String header, String footer){
        app.goTo().groupPage();
        group = new GroupData().withName(name).withHeader(header).withFooter(footer);
        app.group().create(group);
    }

    @Then("^The new set of groups is equal to the old set with the added group$")
    public void verifyGroupCreated(){
        Groups newSet = app.db().groups();
        assertThat(newSet, equalTo(groups.withAdded(group.withId(newSet.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @After
    public void stop(){
        app.stop();
        app = null;
    }
}
