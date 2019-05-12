package testCases.AccountTestCases;

import appModules.AriaDashboard;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.AriaAccountPageObject;
import pageObjects.AriaDashboardPageObject;
import testCases.BaseTestCase;
import utility.Log;
import utility.Utils;

import java.util.List;

/**
 * Created by joseph.gannon on 5/15/2015.
 */
public class AssignChildToParent extends BaseTestCase
{
    @Test(dataProvider="objectTestData", description="Assign child account to parent")
    public void AssignChildToParent(String parentAcct, String childAcct, String username, String password) throws Exception
    {
        Log.startTestCase("Assign Child to Parent");
        //testCases.AriaDashboardTC ad = new testCases.AriaDashboardTC();
        Log.info("Searching for parent account: " + parentAcct);
        AriaDashboard.searchByAcctNum(driver,username,password,parentAcct);
        //ad.searchByAcctNum(username, password, parentAcct);
        Thread.sleep(2000);
        List<WebElement> tabs = driver.findElements(By.className("accountBottomTab"));
        WebElement manageTab = tabs.get(0);
        for(WebElement tab : tabs)
        {
            Log.info("TAB: "+tab.getText());
            if(tab.getText().equals("Manage Child Accounts"))
            {
                Log.info("Found Tab!");
                manageTab = tab;
                tab.click();
                Thread.sleep(500);
                tab.click();
            }
        }
        Thread.sleep(1000);
        driver.findElement(AriaAccountPageObject.getAssignChildAcctLink()).click();
        Thread.sleep(1000);
        driver.findElement(AriaAccountPageObject.getChildAcctAssignField()).sendKeys(childAcct);
        driver.findElement(AriaAccountPageObject.getStdSelfPayBtn()).click();
        driver.findElement(AriaAccountPageObject.getAssignChildAcctBtn()).click();
        manageTab.click();
        Thread.sleep(1000);
        tabs = driver.findElements(By.className("accountBottomTab"));
        for(WebElement tab : tabs)
        {
            Log.info("TAB: "+tab.getText());
            if(tab.getText().equals("Manage Child Accounts"))
            {
                Log.info("Found Tab!");
                manageTab = tab;
                tab.click();
                Thread.sleep(500);
                tab.click();
            }
        }
        verifyTrue(driver.findElement(AriaAccountPageObject.getUnassignLink()).isDisplayed(),"Child Account Assigned Successfully!");
        driver.findElement(AriaDashboardPageObject.getAnalyticsReporting()).click();
        AriaLoginLogout.appLogout(driver);


    }


    @DataProvider(name = "objectTestData")
    public Object[][] data() throws DataDrivenFrameworkException
    {
        Utils objUtils=new Utils();
        return objUtils.data("TestCaseDetails", "AssignChildToParent");
    }
}
