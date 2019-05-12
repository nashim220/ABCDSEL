package testCases.AccountTestCases;

import appModules.AriaDashboard;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import javafx.util.Pair;
import org.testng.annotations.*;
import pageObjects.*;
import testCases.*;
import utility.Log;
import utility.Utils;

/**
 * Created by joseph.gannon on 4/24/2015.
 */
public class ChangeAccountBillingContact extends BaseTestCase
{
    @Test (dataProvider="objectTestData", description="Change Billing Contact Info")
    public void changeBillingContact(String acctNum, String username,String password) throws Exception
    {

        Log.startTestCase("Change Billing Contact Info");
        //AriaLoginLogout.appLogin(driver,username,password);
        AriaDashboard.searchByAcctNum(driver, username, password, acctNum);
        Log.info("Getting current billing information");

        driver.findElement(AriaAccountPageObject.getBillingContactLink()).click();
        Thread.sleep(1500);

        //Current billing contact info
        String currentAddress = driver.findElement(AriaAccountPageObject.getBillingAddress()).getAttribute("value");
        String currentFName = driver.findElement(AriaAccountPageObject.getBillingFName()).getAttribute("value");
        String currentPhone = driver.findElement(AriaAccountPageObject.getBillingPhone()).getAttribute("value");
        if(currentAddress.contains("changed"))
        {

            Log.info("Reverting Billing Info");
            currentAddress =currentAddress.replace("changed", "");
            currentFName = currentFName.replace("changed","");
            currentPhone = "4065551234";

            driver.findElement(AriaAccountPageObject.getBillingAddress()).clear();
            driver.findElement(AriaAccountPageObject.getBillingPhone()).clear();
            driver.findElement(AriaAccountPageObject.getBillingFName()).clear();
            driver.findElement(AriaAccountPageObject.getBillingAddress()).sendKeys(currentAddress);
            driver.findElement(AriaAccountPageObject.getBillingPhone()).sendKeys(currentPhone);
            driver.findElement(AriaAccountPageObject.getBillingFName()).sendKeys(currentFName);

        } else
        {
            Log.info("Changing Billing Info");
            driver.findElement(AriaAccountPageObject.getBillingAddress()).clear();
            driver.findElement(AriaAccountPageObject.getBillingAddress()).sendKeys(currentAddress + " changed");
            driver.findElement(AriaAccountPageObject.getBillingPhone()).clear();
            driver.findElement(AriaAccountPageObject.getBillingFName()).clear();
            driver.findElement(AriaAccountPageObject.getBillingPhone()).sendKeys("1112223333");
            driver.findElement(AriaAccountPageObject.getBillingFName()).sendKeys(currentFName+"changed");
        }


        //click save and assert
        driver.findElement(AriaAccountPageObject.getBillingSaveBtn()).click();
        Thread.sleep(1000);
        verifyFalse(driver.findElement(AriaAccountPageObject.getBillingAddress()).getAttribute("value").equals(currentAddress), "Address changed successfully!");
        verifyFalse(driver.findElement(AriaAccountPageObject.getBillingFName()).getAttribute("value").equals(currentFName), "Name changed successfully!");
        verifyFalse(driver.findElement(AriaAccountPageObject.getBillingPhone()).getAttribute("value").equals(currentPhone), "Address changed successfully!");
        Log.endTestCase("Change Billing Contact Info");


        //Start test case for event log
        Log.startTestCase("Audit Log: Event");
        //select configuration
        driver.findElement(AriaDashboardPageObject.getConfiguration()).click();
        Thread.sleep(1000);
        //select events
        driver.findElement(AriaDashboardPageObject.getAuditLogs()).click();
        driver.findElement(AriaDashboardPageObject.getEvents()).click();
        //this.getCustomElement(driver, AriaDashboardPageObject.getEventsPair());
        //select search
        Thread.sleep(3000);
        //driver.findElement(AriaDashboardPageObject.getAuditSearchBtn()).click();
        driver.findElement(AriaDashboardPageObject.getEventSearchBtn()).click();

        //verify event exists
        verifyTrue(this.getCustomElement(driver,new Pair<String,String>("td",acctNum)).isDisplayed());
        Thread.sleep(10000);
        Log.endTestCase("Audit Log: Event");
    }


    @DataProvider(name = "objectTestData")
    public Object[][] data() throws DataDrivenFrameworkException
    {
        Utils objUtils=new Utils();
        return objUtils.data("TestCaseDetails", "ChangeAccountBillingContact");
    }

}
