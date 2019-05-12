/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_VerifyZeroRatedUsageRetained 
 Purpose     		: 	Purpose of this file is :
						1. To Verify that Zero rated usage within & outside Aria is retained.
						2. This is automation of TC: QAABE-200.

 Date       		:	31/03/2016 
 Modified Date		:	
 Version Information:	Version 1.0
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "ZeroRatedUsage" worksheet 
 						for excel "TestData.xls".
 						3. Account(s) with no existing usage exists for verifying zero rated usage.

 Test Steps 		:	1. Login using valid role credentials for adding usage to the customer.
 						2. Search the customer account to updates it's usage.
 						3. Verify if the user has logical Supplemental Plan assigned, else do so. 
 						4. Using API insert zero rated usage.
 						5. Verify if the Invoice still displays zero rated usage.
 
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
import pageObjects.AriaEOM;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;

public class Cls_VerifyZeroRatedUsageRetained extends VerificationMethods {
	
    public static WebDriver driver;
    public static String strTestCaseName = "Verify Zero Rated Usage";
	public static WebDriverWait webWait;
    public static WebElement webElement;
    
    @BeforeClass
    public void beforeMethod()throws Exception {
    	
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
    public void fn_ValidateZeroRatedUsage(String strMPI, String strSPI, String strAccountNum, String strAPIURL
    					, String strFileName) throws Exception {
    	
    	//TODO: Create class objects to access the common methods.
    	AccountFunctions objAccountFunc = new AccountFunctions();	// for methods relating to Accounts functionality.
    	AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();    	    	
    	AccountPlansUsage objPlansUsage = new AccountPlansUsage();	// for objects to be identified.
    	Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
    	CsvHandler csv = new CsvHandler();	//CSV file to be read for data comparison.
    	Utils objUtils = new Utils();	//object created to access the methods for utility class.
    	AriaEOM objAriaEOM = new AriaEOM();
    	
    	List<List<NameValuePair>> lstpairReadCSV;
    	Boolean blnExecutionStatus = true;

    	//TODO: Mark the beginning of the Test.
       	Log.startTestCase(strTestCaseName);
       	
       	try {       	
       		
       		//TODO: Get the Supplemental Plan Number for the Plan name requested.
       		String strPlanNumber = objAcctFuncPlans.fn_GetPlans(driver, webWait, strSPI, strTestCaseName);

       		//TODO: Verify if the Supplemental Plans has been assigned to the Account Number, else assign it.
			String strSPIID = objAcctFuncPlans.fn_VerifySupplementalPlans(driver, webWait, strAccountNum, 
											strSPI, strTestCaseName);
       		if(strSPIID.contentEquals("")) {
       			
       			objAcctFuncPlans.fn_AssignSuppPlan(driver, webWait, strAccountNum, strPlanNumber, strAPIURL);
       			Log.info("Supplemental Plan with number "+strPlanNumber+" has been assigned to the Account Number: "+strAccountNum);
       		}			
       		
       		//TODO: Get the Master Plan Instance ID for loading the usage.
       		driver.findElement(By.partialLinkText("Analytics and Reporting")).click();
       		String strMPIID = objAcctFuncPlans.fn_GetPlanInstanceID(driver, webWait, strAccountNum, strSPIID, strMPI);
       		
       		//TODO: Now, load the usage to the account number.
       		String LOAD_USAGE_FILE_PATH = System.getProperty("user.dir")
                    + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
                    + System.getProperty("file.separator") + strFileName;           	
       		String strLoadDataDate = objUtils.fn_getLoadDataDate();
           	Log.info("Loading Account Usage for the date '"+strLoadDataDate+"'..");
           	objAccountFunc.fn_LoadAccountUsage(driver, webWait, strAPIURL, LOAD_USAGE_FILE_PATH, strAccountNum
           						, strLoadDataDate, strMPIID, strSPIID);
           	
           	//TODO: Verify the Usage data generated for currently added data; navigating to Usage sub-menu for Account.           	
           	objAriaEOM.fn_clickAnalyticsReporting(driver, webWait).click();
           	objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
           	driver.findElement(By.partialLinkText("Reset Password"));
           	objAriaEOM.fn_clickUsage(driver, webWait).click();
           	objPlansUsage.fn_clickRetrieveButton(driver, webWait);
           	objPlansUsage.fn_setMPI(driver, webWait).selectByVisibleText("All");
           	objPlansUsage.fn_getFromDateTextBox(driver, webWait).clear();
           	objPlansUsage.fn_getFromDateTextBox(driver, webWait).sendKeys(strLoadDataDate);
           	objPlansUsage.fn_getUsageTypeDropDown(driver, webWait).selectByVisibleText("*** All Usage Types***");
           	objPlansUsage.fn_clickRetrieveButton(driver, webWait).click();
           	
           	//TODO: Download the usage file ?
           	
           	//TODO: Verify the data that has been displayed for Invoice data generated.
           	lstpairReadCSV = csv.readCSV(LOAD_USAGE_FILE_PATH);	// read the CSV file for data comparison.
           	WebElement tblInvoiceUsage = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
           	List<WebElement> lstwebInvoiceUsageRows = tblInvoiceUsage.findElements(By.tagName("tr"));
           	Utils.takeScreenshot(driver, strTestCaseName);
           	for(List<NameValuePair> lstCSV : lstpairReadCSV) {	// traverse through read CSv to compare data displayed.
           		
           		String strUsageTypeValue = null;
           		String strAmmountValue = null;
           		String strRateValue = null;
           		
           		for(NameValuePair nvp: lstCSV) {
           			
           			if(nvp.getName().contains("usage_type_code"))
           				strUsageTypeValue = nvp.getValue().toString().trim();
           			
           			if(nvp.getName().contains("amount"))
           				strAmmountValue = nvp.getValue().toString().trim();
           				
           			if(nvp.getName().contains("rate"))
           				strRateValue = nvp.getValue().toString().trim();
           		}           		

           		strAmmountValue = objUtils.fn_ConvertStringToBigDecimal(strAmmountValue, 2); 
           		strRateValue = objUtils.fn_ConvertStringToBigDecimal(strRateValue, 2);
           		
           		Boolean blnCSVItemVerified = false;
           		for(WebElement rows: lstwebInvoiceUsageRows) {

           			List<WebElement> cols = rows.findElements(By.tagName("td"));	//read the rows of the table           			
           			if(cols.size() != 0){	// this is to avoid the header read in the loop.

           				String strUsageType = cols.get(8).getAttribute("innerText").toString().trim();
           				//TODO: Verify @ the beginning if the rows with 'Total'ing have started. If yes, skip further process.
           				if(strUsageType.contains("Total"))
           					break;
           				
           				String strUnitRate = cols.get(11).getAttribute("innerText").toString().trim();
           					strUnitRate = objUtils.fn_ConvertStringToBigDecimal(strUnitRate, 2);
           				String strCharged = cols.get(12).getAttribute("innerText").toString().trim();
           					strCharged = objUtils.fn_ConvertStringToBigDecimal(strCharged, 2);           				
           				
           				if(strUsageTypeValue.equalsIgnoreCase(strUsageType) 
           						&& strAmmountValue.equalsIgnoreCase(strCharged) 
           							&& strRateValue.equalsIgnoreCase(strUnitRate)) {

           					Log.info("Zero Usage Data Loaded externally has been recorded successfully for the Usage Type: "+strUsageTypeValue.toString()+" with rate: "
    							+strRateValue.toString()+" and amount: "+strAmmountValue.toString());
           					blnCSVItemVerified = true;
           					
           					break;
           				}
           			}
           			else if(!(cols.iterator().hasNext())) {
           				
           				Log.error("ERROR: There is no data generated for the Invoice !");
           				Reporter.log("ERROR: There is no data generated for the Invoice !");
        			}
           		}           		
       			
       			//TODO: If entire table for the usage has been compared for the CSV file's read line, exit with comments.
       			if(blnCSVItemVerified == false) {

    				Log.error("ERROR: The Invoice Usage table has no data for the Usage Type: "+strUsageTypeValue.toString()+" with rate: "
							+strRateValue.toString()+" and amount: "+strAmmountValue.toString());
    				Reporter.log("ERROR: The Invoice Usage table has no data for the Usage Type: "+strUsageTypeValue.toString()+" with rate: "
							+strRateValue.toString()+" and amount: "+strAmmountValue.toString());
    				blnCSVItemVerified = true;
    				
    				blnExecutionStatus = false;
       			}
           	}
           	
           	//TODO: Based on the errors recorded, fail the test script.
           	if(blnExecutionStatus == false) {

           		Reporter.log("ERROR: There was an error recorded for missing uploaded data; test case failed !");
           		assertTrue(false, "ERROR: There was an error recorded for missing uploaded data; test case failed !");           		
           	}           	
       	}
       	catch (Exception exception) {

       		Log.error("ERROR: Couldn't Verify zero rated usage added within & oustide Aria is retained due to exception: "+exception.toString());
       		Reporter.log("ERROR: Couldn't Verify zero rated usage added within & oustide Aria is retained due to exception: "+exception.toString());
       		throw exception;
       	}
       	catch (AssertionError exception) {

       		Log.error("ERROR: Couldn't Verify zero rated usage added within & oustide Aria is retained due to exception: "+exception.toString());
       		Reporter.log("ERROR: Couldn't Verify zero rated usage added within & oustide Aria is retained due to exception: "+exception.toString());
       		throw exception;
       	}
       	
       	//TODO: Mark the end of the Test.
       	Log.endTestCase(strTestCaseName);
    }    
   
      
	//TODO: Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException {
	
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "ZeroUsageLoad");
	}
	
	
	@AfterTest
	public void afterMethod() throws Exception {
	
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	}
}