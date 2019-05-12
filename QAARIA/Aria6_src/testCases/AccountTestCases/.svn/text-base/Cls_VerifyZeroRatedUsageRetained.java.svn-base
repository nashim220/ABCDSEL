/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_VerifyZeroRatedUsageRetained 
 Purpose     		: 	Purpose of this file is :
						1. To Verify that Zero rated usage within & outside Aria is retained.
						2. This is automation of TC: QAABE-200.

 Date       		:	08/05/2015 
 Modified Date		:	10/24/2015, 09/24/2015
 Version Information:	Version 2.1
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "ZeroRatedUsage" worksheet 
 						for excel "TestData.xls".
 						3. Account(s) with no existing usage exists for verifying zero rated usage.

 Test Steps 		:	1. Login using valid role credentials for adding usage to the customer.
 						2. Search the customer account to updates it's usage.
 						3. Verify if the user has logical Supplemental Plan assigned, else do so. 
 						4. Using API insert zero rated usage.
 						5. Verify if the Invoice still displays zero rated usage.

Version Changes 2.0:	1. Refactored to point to the correct AccountFucntions reusable modules.
Version Changes 2.1:	1. Modified the variables and the assert calls.
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AccountTestCases;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.log4j.xml.DOMConfigurator;

import atu.ddf.exceptions.DataDrivenFrameworkException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.CsvHandler;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;
import pageObjects.AccountPlansUsage;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;


public class Cls_VerifyZeroRatedUsageRetained extends VerificationMethods
{
	
    public static WebDriver driver;
    public static String strTestCaseName = "Verify zero rated usage added within & oustide Aria is retained.";
	public static WebDriverWait webWait;
    public static WebElement webElement;
    
    @BeforeClass
    public void beforeMethod()throws Exception
    {	
    	//TODO: Initialize the Logger.
    	DOMConfigurator.configure("log4j.xml");	

		//TODO: Set Chrome driver as the driver to launch the test and maximize it
		System.setProperty("webdriver.chrome.driver",chromeDriverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();		
		
		//TODO: initialize the webWait
		webWait = new WebDriverWait(driver, 60);
		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.info("Login to Aria Application");
		//TODO: This parameters are taken from Exec file and its reference is available in EnvironmentDetails
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
    }

    
    @Test(dataProvider = "objectTestData", description="Verify zero rated usage added within & oustide Aria is retained.")
    public void fn_ValidateZeroRatedUsage(String strPlanName, String strAccountNum, String strAPIURL, String strFileName) throws Exception
    {
    	//TODO: Create class objects to access the common methods.
    	AccountFunctions objAccountFunc = new AccountFunctions();	// for methods relating to Accounts functionality.
    	AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();    	    	
    	AccountPlansUsage objPlansUsage = new AccountPlansUsage();	// for objects to be identified.
    	Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
    	CsvHandler csv = new CsvHandler();	//CSV file to be read for data comparison.
    	Utils objUtils = new Utils();	//object created to access the methods for utility class.
    	
    	List<List<NameValuePair>> lstpairReadCSV;
    	Boolean blnExecutionStatus = true;

    	//TODO: Mark the beginning of the Test.
       	Log.startTestCase(strTestCaseName);
       	
       	try
       	{       	
       		//TODO: Get the Supplemental Plan Number for the Plan name requested.
       		String strPlanNumber = objAcctFuncPlans.fn_GetPlans(driver, webWait, strPlanName, strTestCaseName);       		

       		//TODO: Verify if the Supplemental Plans has been assigned to the Account Number, else assign it.
			Boolean blnSupplementalPlansStatus = false; 
			blnSupplementalPlansStatus = objAcctFuncPlans.fn_VerifySupplementalPlans(driver, webWait, strAccountNum, strTestCaseName);
       		if(blnSupplementalPlansStatus == false)
       		{
       			objAcctFuncPlans.fn_AssignSuppPlan(driver, webWait, strAccountNum, strPlanNumber, strAPIURL);
       			Log.info("Supplemental Plan with number "+strPlanNumber+" has been assigned to the Account Number: "+strAccountNum);
       		}
			
       		//TODO: Now, load the usage to the account number.
       		String LOAD_USAGE_FILE_PATH = System.getProperty("user.dir")
                    + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
                    + System.getProperty("file.separator") + strFileName;           	
       		String strLoadDataDate = objAccountFunc.fn_getLoadDataDate();
           	Log.info("Loading Account Usage for the date '"+strLoadDataDate+"'..");
           	objAccountFunc.fn_LoadAccountUsage(driver, webWait, strAPIURL, LOAD_USAGE_FILE_PATH, strAccountNum, strLoadDataDate);
           	
           	//TODO: Verify the Usage data generated for currently added data.
           	objPlansUsage.fn_clickAnalyticsReporting(driver, webWait).click();
           	objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
           	objPlansUsage.fn_clickUsage(driver, webWait).click();
           	objPlansUsage.fn_getFromDateTextBox(driver, webWait).clear();
           	objPlansUsage.fn_getFromDateTextBox(driver, webWait).sendKeys(strLoadDataDate);
           	objPlansUsage.fn_getUsageTypeDropDown(driver, webWait).sendKeys("*** All Usage Types***");
           	objPlansUsage.fn_clickRetrieveButton(driver, webWait).click();
           	
           	//TODO: Download the usage file ?
           	
           	//TODO: Verify the data that has been displayed for Invoice data generated.
           	lstpairReadCSV = csv.readCSV(LOAD_USAGE_FILE_PATH);	// read the CSV file for data comparison.
           	WebElement tblInvoiceUsage = objPlansUsage.fn_getUsageDetails(driver, webWait).findElement(By.tagName("tbody"));
           	List<WebElement> lstwebInvoiceUsageRows = tblInvoiceUsage.findElements(By.tagName("tr"));
           	for(List<NameValuePair> lstCSV : lstpairReadCSV)	// traverse through read CSv to compare data displayed.
           	{
           		String[] strUsageTypeValue = lstCSV.get(0).toString().split("=");
           		String[] strAmmountValue = lstCSV.get(3).toString().split("=");
           			strAmmountValue[1] = objUtils.fn_ConvertStringToBigDecimal(strAmmountValue[1]); 
           		String[] strRateValue = lstCSV.get(4).toString().split("=");
           			strRateValue[1] = objUtils.fn_ConvertStringToBigDecimal(strRateValue[1]);
           		Integer intRowsIterator = 0;
           		for(WebElement rows: lstwebInvoiceUsageRows)
           		{
           			List<WebElement> cols = rows.findElements(By.tagName("td"));	//read the rows of the table           			
           			if(cols.size() != 0)	// this is to avoid the header read in the loop.
           			{
           				String strUsageType = cols.get(5).getAttribute("innerText").toString().trim();
           				//TODO: Verify @ the beginning if the rows with 'Total'ing have started. If yes, skip further process.
           				if(strUsageType.contains("Total"))
           				{
           					intRowsIterator = lstwebInvoiceUsageRows.size();
           					break;
           				}
           				String strUnitRate = cols.get(8).getAttribute("innerText").toString().trim();
           					strUnitRate = objUtils.fn_ConvertStringToBigDecimal(strUnitRate);
           				String strCharged = cols.get(9).getAttribute("innerText").toString().trim();
           					strCharged = objUtils.fn_ConvertStringToBigDecimal(strCharged);
           				
           				intRowsIterator += 1;
           				if(strUsageTypeValue[1].equalsIgnoreCase(strUsageType) && strAmmountValue[1].equalsIgnoreCase(strCharged) && strRateValue[1].equalsIgnoreCase(strUnitRate))
           				{
           					Log.info("Zero Usage Data Loaded externally has been recorded successfully for the Usage Type: "+strUsageTypeValue[1].toString()+" with rate: "
    							+strRateValue[1].toString()+" and amount: "+strAmmountValue[1].toString());
           					break;
           				}
           			}
           			else if(!(cols.iterator().hasNext()))
        			{
           				Log.error("ERROR: There is no data generated for the Invoice !");
           				Reporter.log("ERROR: There is no data generated for the Invoice !");
        			}           			
           			
           			//TODO: If entire table for the usage has been compared for the CSV file's read line, exit with comments.
           			if(lstwebInvoiceUsageRows.size() == intRowsIterator)
           			{
        				Log.error("ERROR: The Invoice Usage table has no data for the Usage Type: "+strUsageTypeValue[1].toString()+" with rate: "
    							+strRateValue[1].toString()+" and amount: "+strAmmountValue[1].toString());
        				Reporter.log("ERROR: The Invoice Usage table has no data for the Usage Type: "+strUsageTypeValue[1].toString()+" with rate: "
								+strRateValue[1].toString()+" and amount: "+strAmmountValue[1].toString());
        				blnExecutionStatus = false;
           			}
           		}           		
           	}
           	
           	//TODO: Based on the errors recorded, fail the test script.
           	if(blnExecutionStatus == false)
           	{
           		Reporter.log("ERROR: There was an error recorded for missing uploaded data; test case failed !");
           		assertTrue(false, "ERROR: There was an error recorded for missing uploaded data; test case failed !");           		
           	}           	
       	}
       	catch (Exception exception)
       	{
       		Log.error("ERROR: Couldn't Verify zero rated usage added within & oustide Aria is retained due to exception: "+exception.toString());
       		Reporter.log("ERROR: Couldn't Verify zero rated usage added within & oustide Aria is retained due to exception: "+exception.toString());
       		throw exception;
       	}
       	catch (AssertionError exception)
       	{
       		Log.error("ERROR: Couldn't Verify zero rated usage added within & oustide Aria is retained due to exception: "+exception.toString());
       		Reporter.log("ERROR: Couldn't Verify zero rated usage added within & oustide Aria is retained due to exception: "+exception.toString());
       		throw exception;
       	}
       	
       	//TODO: Mark the end of the Test.
       	Log.endTestCase(strTestCaseName);
    }    
   
      
	//TODO: Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "ZeroUsageLoad");
	}
	
	
	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
}
