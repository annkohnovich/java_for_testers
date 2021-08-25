package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

public class PasswordChangingTests extends TestBase{

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

//    public void ensurePreconditions(){
//        if (app.db().users().stream().filter(user -> !user.getName().equals("administrator"))
//        .collect(Collectors.toList()).size() == 0) {
//        create user }
//    }

    @Test
    public void testPasswordChanging() throws IOException {
        String userPassword = "new_password";
        app.login().start("administrator", "password");
        String email = app.admin().resetPasswordAndReturnEmail();
        List<MailMessage> mailMessage = app.mail().waitForMail(1, 10000);
        String link = findConfirmationLink(mailMessage, email);
        app.registration().finish(link, userPassword);
        String username = app.db().userWith(email).getUsername();

        Assert.assertTrue(app.newSession().login(username, userPassword));
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
