package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        click(By.linkText("groups"));
    }

    public void homePage() {
      click(By.linkText("home"));
    }

    public void homePageForGroup(String name) {
        click(By.linkText("group page \"" + name + "\""));
    }

    public void returnToAllContactsList(){
        Select element = new Select(wd.findElement(By.name("group")));
        element.selectByValue("[all]");
    }
}
