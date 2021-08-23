package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class AdminHelper extends HelperBase{

    public AdminHelper(ApplicationManager app){
        super(app);
    }

    public String resetPasswordAndReturnEmail(){
        wd.findElement(By.linkText("Manage Users")).click();
        wd.findElement(By.xpath("//*[contains(text(), 'reporter')]"));
        wd.findElement(By.partialLinkText("user")).click();
        String email = wd.findElement(By.cssSelector("input#email-field")).getAttribute("value");
        wd.findElement(By.cssSelector("input[value='Reset Password']")).click();
        return email;
    }
}
