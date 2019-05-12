package testCases.AccountTestCases;

import atu.ddf.exceptions.DataDrivenFrameworkException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.*;
import pageObjects.*;
import pageObjects.AriaDashboard;
import testCases.*;
import utility.Log;
import utility.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joseph.gannon on 4/24/2015.
 */
public class AriaAccount extends BaseTestCase
{
    @Test (dataProvider="objectTestData", description="Change Billing Contact Info")
    public void changeBillingContact(String username,String password, String acctNum, String parentAcct) throws Exception
    {

        Log.startTestCase("Change Billing Contact Info");
        testCases.AriaDashboard ad = new testCases.AriaDashboard();
        ad.searchByAcctNum(username, password, acctNum);
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
        verifyFalse(driver.findElement(AriaAccountPageObject.getBillingFName()).getAttribute("value").equals(currentFName),"Name changed successfully!");
        verifyFalse(driver.findElement(AriaAccountPageObject.getBillingPhone()).getAttribute("value").equals(currentPhone),"Address changed successfully!");
    }

    //@Test (dataProvider="objectTestData", description="Assign Child to Parent")
    public void assignChildAccount(String username,String password, String acctNum, String parentAcct)
    {
        try
        {
            Log.startTestCase("Assign child account");
            testCases.AriaDashboard ad = new testCases.AriaDashboard();
            ad.searchByAcctNum(username, password, parentAcct);

            //Assign account num to parent account



        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @DataProvider(name = "objectTestData")
    public Object[][] data() throws DataDrivenFrameworkException
    {
        Utils objUtils=new Utils();
        return objUtils.data("TestCaseDetails", "AriaAccount");
    }

}
