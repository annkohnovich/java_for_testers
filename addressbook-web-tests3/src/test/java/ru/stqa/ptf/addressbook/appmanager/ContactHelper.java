package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        wd.findElement(By.cssSelector("div.msgbox"));
    }

    public void deleteSelectedContact() {
        click (By.xpath("//input[@value='Delete']"));
    }

    public void selectContactById (int id) {
        wd.findElement (By.cssSelector("input[value='" + id + "']")).click();
    }

    private void initContactModificationById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    private void goToHomePage() {
        click(By.linkText("home"));
    }

    public boolean isThereAContact() {
        return isElementPresent (By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> rows = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name=\"entry\"]"));

        for (WebElement r : rows) {
            List<WebElement> cells = r.findElements(By.tagName("td"));
            String first_name = cells.get(2).getText();
            String last_name = cells.get(1).getText();
            int id = Integer.parseInt(r.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstName(first_name).withLastName(last_name);
            contacts.add(contact);
        }
        return contacts;
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        goToHomePage();
    }
    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact);
        submitContactModification();
        goToHomePage();
    }

    public void delete(ContactData deletedContact) {
        selectContactById (deletedContact.getId());
        deleteSelectedContact();
        confirmContactDeletion();
        goToHomePage();
    }
}
