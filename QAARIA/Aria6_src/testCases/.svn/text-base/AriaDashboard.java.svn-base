package testCases;

import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import com.sun.xml.internal.bind.v2.TODO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.AriaAccountPageObject;
import pageObjects.AriaAdHocSearch;
import pageObjects.AriaLogin;
import utility.Log;
import utility.Utils;

import java.util.List;

/**
 * Created by joseph.gannon on 4/23/2015.
 */
public class AriaDashboard extends BaseTestCase
{

 //   @Test(dataProvider="objectTestData", description="Launch the Aria Site")
    public void searchByAcctNum(String username, String password, String acctNum) throws Exception
    {
        //super.launchSite(username, password);
        AriaLoginLogout.appLogin(driver,username,password);
        driver.findElement(pageObjects.AriaDashboard.clickNavBarAccounts()).click();
        Thread.sleep(1000);
        Log.info("Navigating to Ad-Hoc Search");
        driver.findElement(pageObjects.AriaDashboard.clickSearch()).click();
        driver.findElement(pageObjects.AriaDashboard.clickAdHocSearch()).click();

        Log.info("Searching by account number");
        driver.findElement(AriaAdHocSearch.searchByField()).click();
        driver.findElement(AriaAdHocSearch.searchByAcctNum()).click();

        Log.info("Verifying Search results for account#: " + acctNum);
        driver.findElement(AriaAdHocSearch.searchValue()).clear();
        driver.findElement(AriaAdHocSearch.searchValue()).sendKeys(acctNum);
        driver.findElement(AriaAdHocSearch.clickSearchButton()).submit();
        assertEquals(driver.findElement(AriaAdHocSearch.searchResult(acctNum)).getText(), acctNum);
        Log.info("Account number found successfully!");

        Log.info("Loading account#: " + acctNum + " details...");
        driver.findElement(AriaAdHocSearch.searchResult(acctNum)).click();


    }

    @DataProvider(name = "objectTestData")
    public Object[][] data() throws DataDrivenFrameworkException
    {
        Utils objUtils=new Utils();
        return objUtils.data("Aria", "AriaDashboard");
    }
}
