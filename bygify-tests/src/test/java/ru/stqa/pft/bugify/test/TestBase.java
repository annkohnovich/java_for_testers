package ru.stqa.pft.bugify.test;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.bugify.manage.ApplicationManager;
import java.io.IOException;



public class TestBase {

    public static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    public boolean isIssueOpen(int issueId) {
        String json = RestAssured.get("https://bugify.stqa.ru/api/issues/" + issueId + ".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        JsonElement obj = issues.getAsJsonArray().get(0); //массив из 1-го объекта берем его (индекс = 0)
        String issueStatus = obj.getAsJsonObject().get("state_name").getAsString();

        if (issueStatus.equals("closed")){
            return false;
        } else return true;
    }


    
    public boolean isIssueExist(int issueId) throws Exception {
        Response response = RestAssured.get("https://bugify.stqa.ru/api/issues/" + issueId + ".json");
        if (!(response.statusCode() == 404)) {
            return true;
        } else throw new Exception("Failed because the issue isn't exist");
    }

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.stop();
    }
}

