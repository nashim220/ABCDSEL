/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_NewAccountCreation 
 Purpose     		: 	Purpose of this file is :
						1. To create New Account as stated in QAABE-45.
						2. verify the newly created account.

 Date       		:	06/12/2015
 Modified Date		:	11/04/2015, 07/10/2015
 Version Information:	Version 2.1
 
  PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "ManuallyCreateNewAccount" worksheet for excel "TestData.xls".
 						
 Test Steps 		:	1. Login using valid role credentials for creating New account.
 						2. Navigate to the Accounts -> Create New Accounts left Nav menu.
 						3. On Create New Account page, add details for new account.
 						4. Click Save and verify the success of added Account.
 						5. Navigate to the newly created account by clicking the Hyper link and then Verify data.  						
 
 Version Changes 2.0:	1. Introduced page object based Web wait.
 						2. Changes to the parameters, class and method names as per the standards.
 Version Changes 2.1:	1. Changing the Before Class details to suit the generalisation agreed upon. 						
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/


package testCases.AccountTestCases;

import org.apache.log4j.xml.DOMConfigurator;

import atu.ddf.exceptions.DataDrivenFrameworkException;

import org.testng.Reporter;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import testCases.BaseTestCase;
import utility.*;
import appModules.AriaLoginLogout;
import appModules.AriaNewAccountCreation;

public class Cls_NewAccountCreation extends BaseTestCase
{

    public static WebDriver driver;
    public static String strTestCaseName = "Create New Account";
    public static String[] arrSearchFields = null;	// this string array will hold the Fields names for the Client Defined Fields.
    public static String strFieldValue = null; 	// this string will hold the subsequent field value for the Client Defined Fields.
    public static WebDriverWait webWait;
    public static WebElement webElement;


	@BeforeClass
	 public void beforeMethod()throws Exception
	 {
		DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
		
		//TODO: Set Chrome driver as the driver to launch the test.
		driver = utility.Utils.fn_SetChromeDriverProperties();
				
		//TODO: initialize the webWait
		webWait = new WebDriverWait(driver, 60);
		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.info("Login to application");
		//This parameters are taken from Exec file and its reference is available in EnvironmentDetails
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);
	 }
	 
/*    
    Function fn_CreateNewAccount: This is the actual Test NG test for calling the functions to create account.    						
*/
	
    @Test(dataProvider = "objectTestData", description = "Create New Account")
	public static void fn_CreateNewAccount(String strdFileName, String strdWorksheet, String strFieldNames, String strFieldValues) throws Exception
	{
		arrSearchFields = strFieldNames.split("#");	// read the field names in this list to be referred latter.
		strFieldValue = strFieldValues;	// read the field values which needs to be passed @ runtime.		
    	
    	Log.startTestCase(strTestCaseName);	// Log the beginning of the Test Case.
		
		try	// Actual functional call.
		{
			String strNewAccountDataExcelFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
					 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
					 + strdFileName;	// specify path of the excel file with details to add new account.
			File newAccountDataExcelFile = new File(strNewAccountDataExcelFilePath);	// object for the excel file.
			List<HashMap<String,String>> lsthmapFileContents = utility.ReadFiles.readExcelFile(newAccountDataExcelFile, strdWorksheet);
			
			// Call for the method to add data to the Create Account Page / Form.
			AriaNewAccountCreation.fn_CreateAccount(driver, lsthmapFileContents, strTestCaseName, arrSearchFields, strFieldValue);
			TimeUnit.SECONDS.sleep(5);
					
			Log.endTestCase(strTestCaseName);
			assertTrue(true);
		}
		catch (Exception exception)
		{
			Log.error("The Test Case Create New Account has endded prematurely due to failure: "+exception.toString());
			Reporter.log("ERROR: The Test Case Create New Account has endded prematurely due to failure: "+exception.toString());
    		utility.Utils.takeScreenshot(driver, strTestCaseName);	// take snapshot of Account Overview page after Navigation.
			assertFalse(false);
		}
		catch (AssertionError exception)
		{
			Log.error("The Test Case Create New Account has endded prematurely due to failure: "+exception.toString());
			Reporter.log("ERROR: The Test Case Create New Account has endded prematurely due to failure: "+exception.toString());
    		utility.Utils.takeScreenshot(driver, strTestCaseName);	// take snapshot of Account Overview page after Navigation.
			assertFalse(false);
		}	
	}
    

    //Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "ManuallyCreateNewAccount");
	}
	
	
	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //Logs out of the application
		 AriaLoginLogout.appLogout(driver); 
		 //Quitting the driver
		 driver.quit(); 
	 }

}
