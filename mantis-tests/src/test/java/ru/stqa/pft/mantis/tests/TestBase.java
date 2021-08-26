package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;


public class TestBase {

    public static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"),
                "config_inc.php", "config_inc.php.back");
    }

    public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = new MantisConnectLocator()
                .getMantisConnectPort(new URL(app.getProperty("web.soapUrl")));
        IssueData issueData = mc.mc_issue_get(app.getProperty("web.adminLogin"),
                app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));
        if (issueData.getStatus().getName().equals("closed")){
            return false;
        } else return true;
    }

    public boolean isIssueExist(int issueId) throws Exception {
        MantisConnectPortType mc = new MantisConnectLocator()
                .getMantisConnectPort(new URL(app.getProperty("web.soapUrl")));
        if (mc.mc_issue_exists(app.getProperty("web.adminLogin"),
                app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId))){
            return true;
        } else throw new Exception("Failed because the issue isn't exist");
    }

    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.back", "config_inc.php");
        app.stop();
    }
}

