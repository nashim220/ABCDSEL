package testCases.AccountTestCases;

import appModules.AriaDashboard;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import javafx.util.Pair;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import pageObjects.*;
import testCases.*;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;

/**
 * Created by joseph.gannon on 4/24/2015.
 * Edited By Abhishek on 4/28/2016
 */
public class ChangeAccountBillingContact extends BaseTestCase
{
	public static String strTestCaseName = "Change Billing Contact Info";
	EnvironmentDetails objEnv = new EnvironmentDetails();

	@BeforeClass
	 public static void beforeMethod()throws Exception
	 {	
		DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
		driver = Utils.fn_SetChromeDriverProperties();
		System.setProperty("webdriver.chrome.driver",chromeDriverPath);
		//driver = new ChromeDriver();
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.startTestCase("Login to application");
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
	 }
	
	@Test (dataProvider="objectTestData", description="Change Billing Contact Info")
    public void changeBillingContact(String acctNum)  throws Exception
    {

        EnvironmentDetails objEnv = new EnvironmentDetails();
		AriaDashboard.searchByAcctNum(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD, acctNum);
        Log.info("Getting current billing information");
        Thread.sleep(2000);
        
        //Find Contacts Link
        driver.findElement(AriaBillingContactValidationPageObject.getContactsLink()).click();
        Thread.sleep(5000);
                
		//Clicking on the Plans table
	    WebElement Webtable = driver.findElement(By.id("DataTables_Table_0"));
	    Thread.sleep(5000);
	    
        //Edit Contacts Link
        driver.findElement(AriaBillingContactValidationPageObject.getContactsEditLink(Webtable)).click();
        Thread.sleep(5000);

        //Current billing contact info
        String currentAddress = driver.findElement(AriaBillingContactValidationPageObject.getBillingAddress1()).getAttribute("value");
        String currentAddress2 = driver.findElement(AriaBillingContactValidationPageObject.getBillingAddress2()).getAttribute("value");
        String currentFName = driver.findElement(AriaBillingContactValidationPageObject.getBillingFName()).getAttribute("value");
        String currentLName = driver.findElement(AriaBillingContactValidationPageObject.getBillingLName()).getAttribute("value");
        String currentPhone = driver.findElement(AriaBillingContactValidationPageObject.getBillingPhone()).getAttribute("value");
        
        if(currentAddress.contains("changed"))
        {

            Log.info("Reverting Billing Info");
            String curAdd =currentAddress.replace("changed","");
            String curAdd2 =currentAddress2.replace("changed","");
            String curFName = currentFName.replace("changed","");
            String curPhone = "4065551234";
            
            driver.findElement(AriaBillingContactValidationPageObject.getBillingAddress1()).clear();
            driver.findElement(AriaBillingContactValidationPageObject.getBillingAddress2()).clear();
            driver.findElement(AriaBillingContactValidationPageObject.getBillingPhone()).clear();
            driver.findElement(AriaBillingContactValidationPageObject.getBillingFName()).clear();
            driver.findElement(AriaBillingContactValidationPageObject.getBillingAddress1()).sendKeys(curAdd);
            driver.findElement(AriaBillingContactValidationPageObject.getBillingAddress2()).sendKeys(curAdd2);
            driver.findElement(AriaBillingContactValidationPageObject.getBillingPhone()).sendKeys(curPhone);
            driver.findElement(AriaBillingContactValidationPageObject.getBillingFName()).sendKeys(curFName);
            
        } else
        {
            Log.info("Changing Billing Info");
            driver.findElement(AriaBillingContactValidationPageObject.getBillingAddress1()).clear();
            driver.findElement(AriaBillingContactValidationPageObject.getBillingAddress1()).sendKeys(currentAddress + " changed");
            driver.findElement(AriaBillingContactValidationPageObject.getBillingAddress2()).clear();
            driver.findElement(AriaBillingContactValidationPageObject.getBillingAddress2()).sendKeys(currentAddress2 + " changed");
            driver.findElement(AriaBillingContactValidationPageObject.getBillingPhone()).clear();
            driver.findElement(AriaBillingContactValidationPageObject.getBillingFName()).clear();
            driver.findElement(AriaBillingContactValidationPageObject.getBillingPhone()).sendKeys("1112223333");
            driver.findElement(AriaBillingContactValidationPageObject.getBillingFName()).sendKeys(currentFName+"changed");
        }

        Thread.sleep(5000);
        //click save and assert
        driver.findElement(AriaBillingContactValidationPageObject.getBillingSaveBtn()).click();
        Thread.sleep(5000);
        
        driver.findElement(AriaBillingContactValidationPageObject.getBillingSaveConfirmBtn()).click();;
        Thread.sleep(5000);

        //Click on Edit Contacts Link
        WebElement Webtable1 = driver.findElement(By.id("DataTables_Table_1"));
        driver.findElement(AriaBillingContactValidationPageObject.getContactsEditLink(Webtable1)).click();
        Thread.sleep(5000);
        System.out.println("/*******************************************************************************************/");
        if(driver.findElement(AriaBillingContactValidationPageObject.getBillingAddress1()).getAttribute("value").equals(currentAddress)){
        	System.out.println("Error : Address1 change was not Successfull");
        }
        else{
        	System.out.println("Success : Address1 Changed From : "+currentAddress+ " : to : " +driver.findElement(AriaBillingContactValidationPageObject.getBillingAddress1()).getAttribute("value") );
        }
        if(driver.findElement(AriaBillingContactValidationPageObject.getBillingAddress2()).getAttribute("value").equals(currentAddress2) ){
        	System.out.println("Error : Address2 change was not Successfull");
        }
        else{
        	System.out.println("Success : Address2 Changed From : "+currentAddress2+ " : to : " +driver.findElement(AriaBillingContactValidationPageObject.getBillingAddress2()).getAttribute("value") );
        }
        if(driver.findElement(AriaBillingContactValidationPageObject.getBillingFName()).getAttribute("value").equals(currentFName)){
        	System.out.println("Error : First Name change was not Successfull");
        }
        else{
        	System.out.println("First Name Changed From : "+currentFName+ " : to : " +driver.findElement(AriaBillingContactValidationPageObject.getBillingFName()).getAttribute("value") );
        }
        if(driver.findElement(AriaBillingContactValidationPageObject.getBillingPhone()).getAttribute("value").equals(currentPhone)){
        	System.out.println("Error : Phone No. change was not Successfull");
        }
        else{
        System.out.println("Phone No. Changed From : "+currentPhone+ " : to : " +driver.findElement(AriaBillingContactValidationPageObject.getBillingPhone()).getAttribute("value") );
        }
        System.out.println("/*******************************************************************************************/");
        
        //Assert if Any of the values are Equal.
       	assertFalse(driver.findElement(AriaBillingContactValidationPageObject.getBillingAddress1()).getAttribute("value").equals(currentAddress), "Address changed successfully!");
      	assertFalse(driver.findElement(AriaBillingContactValidationPageObject.getBillingAddress2()).getAttribute("value").equals(currentAddress2), "Address changed successfully!");
      	assertFalse(driver.findElement(AriaBillingContactValidationPageObject.getBillingFName()).getAttribute("value").equals(currentFName), "Name changed successfully!");
      	assertFalse(driver.findElement(AriaBillingContactValidationPageObject.getBillingPhone()).getAttribute("value").equals(currentPhone), "Address changed successfully!");
        Log.endTestCase("Change Billing Contact Info");
        Thread.sleep(2000);

       //Start test case for event log
       Log.startTestCase("Audit Log: Event");
        
       //select configuration
        driver.findElement(AriaDashboardPageObject.getConfiguration()).click();
        Thread.sleep(1000);
        
        //select events
        driver.findElement(AriaDashboardPageObject.getAuditLogs()).click();
        driver.findElement(AriaDashboardPageObject.getEvents()).click();
        
        //select search
        Thread.sleep(3000);
        
        //Update the From Date of Event Log
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date dateBeforetoday = DateUtils.addDays(new Date(),-1);
        String dateFrom = dateFormat.format(dateBeforetoday);
        String dateSelected = dateFrom.substring(0, 17);
        driver.findElement(AriaDashboardPageObject.geteventnotificationlog_from_date()).clear();
        driver.findElement(AriaDashboardPageObject.geteventnotificationlog_from_date()).sendKeys(dateSelected);
        Thread.sleep(3000);
                
        driver.findElement(AriaDashboardPageObject.getEventSearchBtn()).click();

        //verify event exists
        verifyTrue(this.getCustomElement(driver,new Pair<String,String>("td",acctNum)).isDisplayed());
        Thread.sleep(10000);
        Log.endTestCase("Audit Log: Event");
        
        /********************************************************************************/
        
    }


    @DataProvider(name = "objectTestData")
    public Object[][] data() throws DataDrivenFrameworkException
    {
        Utils objUtils=new Utils();
        return objUtils.data("TestCaseDetails", "ChangeAccountBillingContact");
    }

}
