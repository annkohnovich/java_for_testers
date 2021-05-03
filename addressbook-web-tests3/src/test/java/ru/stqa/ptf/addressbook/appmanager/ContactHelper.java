package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contactData) {
      type(By.name("firstname"), contactData.getFirstName());
      type(By.name("lastname"), contactData.getLastName());
      type(By.name("mobile"), contactData.getMobilePhone());
      type(By.name("email"),contactData.getEmail());
    }

    public void initContactCreation() {

        click(By.linkText("add new"));
    }

    public void confirmContactDeletion() {

        wd.switchTo().alert().accept();
    }

    public void deleteSelectedContact() {
      click (By.xpath("//input[@value='Delete']"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }


    public void createAContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        goToHomePage();
    }

    private void goToHomePage() {
        click(By.linkText("home page"));
    }

    public boolean isThereAContact() {
        return isElementPresent (By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> rows = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name=\"entry\"]"));

        for (WebElement r : rows) {
            List<WebElement> cells = r.findElements(By.tagName("td"));
            String first_name = cells.get(2).getText();
            String last_name = cells.get(1).getText();
            int id = Integer.parseInt(r.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id, first_name, last_name, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
