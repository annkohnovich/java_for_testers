package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private WebDriver wd;
    private final Properties properties;
    private String browser;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private LoginHelper loginHelper;
    private AdminHelper adminHelper;
    private DbHelper dbHelper;
    private SoapHelper soapHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (wd != null){
            wd.quit();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public HttpSession newSession(){
        return new HttpSession(this);
    }

    public WebDriver getDriver(){
        if (wd == null){
            if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            }
            wd.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }
    
    public FtpHelper ftp(){
        if (ftp == null){
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public RegistrationHelper registration() {
        return new RegistrationHelper(this);
    }

    public MailHelper mail(){
        if (mailHelper == null){
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public LoginHelper login() {
        if (loginHelper == null){
            loginHelper = new LoginHelper(this);
        }
        return loginHelper;
    }

    public AdminHelper admin(){
        if (adminHelper == null){
            adminHelper = new AdminHelper(this);
        }
        return adminHelper;
    }

    public DbHelper db() {
        if (dbHelper == null){
            dbHelper = new DbHelper(this);
        }
        return dbHelper;
    }

    public SoapHelper soap(){
        if (soapHelper == null){
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }

}

