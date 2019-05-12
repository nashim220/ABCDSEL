package appModules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.AriaDashboardPageObject;
import pageObjects.AriaSearchPageObject;
import utility.Log;
import utility.VerificationMethods;

import java.util.List;

/**
 * Created by joseph.gannon on 5/20/2015.
 */
public class AriaDashboard extends VerificationMethods
{
    public static void searchByAcctNum(WebDriver driver, String username, String password, String acctNum) throws Exception
    {
        //super.launchSite(username, password);
        AriaLoginLogout.appLogin(driver, username, password);
        driver.findElement(AriaDashboardPageObject.clickNavBarAccounts()).click();
        driver.findElement(AriaDashboardPageObject.clickSearch()).click();

        try
        {
            if (!(driver.findElement(AriaDashboardPageObject.clickAdHocSearch()).isDisplayed()))
            {
                driver.findElement(AriaDashboardPageObject.clickNavBarAccounts()).click();
                Thread.sleep(1000);
                Log.info("Navigating to Ad-Hoc Search");
                driver.findElement(AriaDashboardPageObject.clickSearch()).click();
                driver.findElement(AriaDashboardPageObject.clickAdHocSearch()).click();
            } else
            {
                driver.findElement(AriaDashboardPageObject.clickAdHocSearch()).click();
            }
        }catch(Exception e)
        {

        }
        Log.info("Searching by account number");
        driver.findElement(AriaSearchPageObject.searchByField()).click();
        //driver.findElement(AriaSearchPageObject.searchByAcctNum()).click();

        Log.info("Verifying Search results for account#: " + acctNum);
        driver.findElement(AriaSearchPageObject.searchValue()).clear();
        driver.findElement(AriaSearchPageObject.searchValue()).sendKeys(acctNum);
        driver.findElement(AriaSearchPageObject.clickSearchButton()).submit();
        Thread.sleep(1000);
        assertEquals(driver.findElement(AriaSearchPageObject.searchResult(acctNum)).getText(), acctNum);
        Log.info("Account number found successfully!");

        Log.info("Loading account#: " + acctNum + " details...");
        driver.findElement(AriaSearchPageObject.searchResult(acctNum)).click();

    }

}
