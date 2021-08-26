package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class AdminHelper extends HelperBase{

    public AdminHelper(ApplicationManager app){
        super(app);
    }

    public void goToManagePage(){
        click(By.xpath("//span[contains(text(), 'Manage')]"));
    }

    public void goToManageUsersPage(){
        click(By.linkText("Manage Users"));
    }

    public void selectUser(){
        click(By.partialLinkText("user"));
    }

    public String returnEmail(){
        return wd.findElement(By.cssSelector("input#email-field")).getAttribute("value");
    }

    public void resetPassword(){
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public String resetPasswordAndReturnEmail(){
        goToManagePage();
        goToManageUsersPage();
        selectUser();
        String email = returnEmail();
        resetPassword();
        return email;
    }
}
