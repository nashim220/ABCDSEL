package testCases.AuditLog;

import appModules.AriaDashboard;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.AriaDashboardPageObject;
import pageObjects.AriaReportsPage;
import testCases.BaseTestCase;
import utility.Log;
import utility.Utils;

import java.util.List;

/**
 * Created by joseph.gannon
 */
public class CsrUserReport extends BaseTestCase
{
    @Test(dataProvider="objectTestData", description="Audit Log: CSR User ")
    public void auditCsrUserReport(String username, String password, String creationDate) throws Exception
    {
        Log.startTestCase("Audit Log: CSR User Report");
        AriaLoginLogout.appLogin(driver, username, password);

        Log.info("Clicking Reports");
        this.getCustomElement(driver, AriaDashboardPageObject.getReports()).click();

        Log.info("Clicking Run Reports");
        driver.findElement(AriaDashboardPageObject.getRunReports()).click();
        Log.info("Sleeping for SGAS");
        Thread.sleep(5000);

        //Switching driver to iframe context
        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
        WebElement parentFrame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(parentFrame);  //work_frm
        List<WebElement> iFrames = driver.findElements(By.tagName("iframe"));
        driver.switchTo().frame(iFrames.get(0));  //innerModFrame

        this.getCustomElement(driver, AriaReportsPage.getSGAS()).click();
        this.getCustomElement(driver, AriaReportsPage.getCSRUsersReport()).click();

        //switch back to original context and then to the other frame
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
        driver.switchTo().frame(parentFrame);  //work_frm
        driver.switchTo().frame(iFrames.get(1));  //innerWorkFrame
        driver.findElement(AriaReportsPage.getSwitchToPreview()).click();
        driver.findElement(AriaReportsPage.getPreviewBtn()).click();
        Thread.sleep(5000);

        List<WebElement> frames = driver.findElements(By.tagName("frame"));
        List<WebElement> frameSet = driver.findElements(By.tagName("frameset"));

        Log.info("Switching to report output context");
        driver.switchTo().frame(driver.findElement(AriaReportsPage.getReportOutput()));
        String createDate =driver.findElement(AriaReportsPage.getCreationDate()).getText();
        verifyTrue(creationDate.equals(createDate));
        Thread.sleep(20000);

    }
    @DataProvider(name = "objectTestData")
    public Object[][] data() throws DataDrivenFrameworkException
    {
        Utils objUtils=new Utils();
        return objUtils.data("TestCaseDetails", "auditCsrUserReport");
    }

}
