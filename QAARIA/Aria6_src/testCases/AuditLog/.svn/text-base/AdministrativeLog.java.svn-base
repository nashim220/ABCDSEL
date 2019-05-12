package testCases.AuditLog;

import appModules.AriaDashboard;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import javafx.util.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import pageObjects.AriaDashboardPageObject;
import testCases.BaseTestCase;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;

import java.util.List;

/**
 * Created by joseph.gannon on 6/1/2015.
 */
public class AdministrativeLog extends BaseTestCase
{

    @Parameters("browser")
    @BeforeClass()
    public void beforeClass(@Optional String browser) throws Exception
    {
        super.beforeClass("chrome");
        EnvironmentDetails env = new EnvironmentDetails();
        appModules.AriaLoginLogout.appLogin(driver, env.STRUSERNAME,env.STRPASSWORD);
    }
    @Test(dataProvider="objectTestData", description="Audit Log: Administrative Log ")
    public void validateAdminstrativeLog(String serviceNumber) throws Exception
    {
        Log.startTestCase("Audit Log: Administrative Log");
//        AriaLoginLogout.appLogin(driver, username, password);

        Log.info("Opening service Number: " + serviceNumber);
        driver.findElement(AriaDashboardPageObject.getProducts()).click();
        driver.findElement(AriaDashboardPageObject.getServices()).click();
        Thread.sleep(5000);
        WebElement serviceRow = driver.findElement(By.id("serviceNumberIndex_row_"+serviceNumber));
        serviceRow.click();
        Thread.sleep(3000);
        //get current time here?

        driver.findElement(AriaDashboardPageObject.getConfiguration()).click();
        Thread.sleep(2000);
        driver.findElement(AriaDashboardPageObject.getAuditLogs()).click();
        this.getCustomElement(driver, AriaDashboardPageObject.getAdminPair()).click();
        Thread.sleep(2000);
        driver.findElement(AriaDashboardPageObject.getAuditSearchBtn()).click();
        Thread.sleep(2000);
        driver.findElement(AriaDashboardPageObject.getAuditLogSearchResults()).click();
        Thread.sleep(5000);
        Log.info("Service event found?: "+this.getCustomElement(driver,new Pair<String,String>("td",serviceNumber)).isDisplayed());
        verifyTrue(this.getCustomElement(driver, new Pair<String, String>("td", serviceNumber)).isDisplayed());



        Thread.sleep(20000);
    }
    @DataProvider(name = "objectTestData")
    public Object[][] data() throws DataDrivenFrameworkException
    {
        Utils objUtils=new Utils();
        return objUtils.data("TestCaseDetails", "auditAdministrativeLog");
    }
}
