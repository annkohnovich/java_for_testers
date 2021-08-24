package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class AdminHelper extends HelperBase{

    public AdminHelper(ApplicationManager app){
        super(app);
    }

    public String resetPasswordAndReturnEmail(){
        click(By.linkText("Manage Users"));
        click(By.partialLinkText("user"));
        String email = wd.findElement(By.cssSelector("input#email-field")).getAttribute("value");
        click(By.cssSelector("input[value='Reset Password']"));
        return email;
    }
}
