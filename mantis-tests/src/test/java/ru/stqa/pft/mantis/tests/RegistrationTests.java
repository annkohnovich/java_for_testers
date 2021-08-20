package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.MailHelper;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException {
        long now = System.currentTimeMillis();
        String username = String.format("user%s", now);
        String email = String.format("user%s@localhost.localdomain", now);
        String password = "password";
        app.registration().start(username, email);
        List<MailMessage> mailMessage = app.mail().waitForMail(2, 10000);
        String link = findConfirmationLink(mailMessage, email);
        app.registration().finish(link, password);
        Assert.assertTrue(app.newSession().login(username, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessage, String email) {
        MailMessage message = mailMessage.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(message.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
