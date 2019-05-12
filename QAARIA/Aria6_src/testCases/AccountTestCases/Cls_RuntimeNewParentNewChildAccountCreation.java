/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_RuntimeNewParentNewChildAccountCreation 
 Purpose     		: 	Purpose of this file is :
						1. To create New Account with Parent Child relation as stated in QAABE-49.
						2. verify the newly created account.

 Date       		:	06/17/2015
 Modified Date		:	11/04/2015, 07/10/2015
 Version Information:	Version 2.1
 
  PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "RuntimeNewParentChildAccountCreation" worksheet for excel "TestData.xls".

 Test Steps 		:	1. Login using valid role credentials for creating New account.
 						2. Verify the created Account in Step1.
 						3. Create a new account and add the Account ID of Step1 as Parent Account ID here.
 						4. Verify the Child Parent relationship. 						  						

 Version Changes 2.0:	1. Introduced page object based Web wait.
 						2. Changes to the parameters, class and method names as per the standards.
 Version Changes 2.1:	1. Changing the Before Class details to suit the generalisation agreed upon.  						
  
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/


package testCases.AccountTestCases;


import atu.ddf.exceptions.DataDrivenFrameworkException;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import utility.*;
import pageObjects.NewAccountCreation;
import pageObjects.ParentChildNewAccount;
import appModules.AriaLoginLogout;
import appModules.AriaNewAccountCreation;


public class Cls_RuntimeNewParentNewChildAccountCreation extends VerificationMethods
{
    public static WebDriver driver;
    public static String strTestCaseName = "New Account with Parent Child Relation @ Runtime";
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
    Function fn_CreateParentChildAccount: This is the actual Test NG test for calling the functions to create Parent & Child relational New Account.    						
*/
    
    @Test(dataProvider = "objectTestData", description = "New Account with Parent Child Relation @ Runtime")
	public static void fn_CreateRuntimeParentChildAccount(String strdFileName, String strdWorksheet, String strFieldNames, String strFieldValues) throws Exception
	{
		arrSearchFields = strFieldNames.split("#");	// read the field names in this list to be referred latter.
		strFieldValue = strFieldValues;	// read the field values which needs to be passed @ runtime.		
    	    	
    	Log.startTestCase(strTestCaseName);	// Log the beginning of the Test Case.				
		
		try
		{
			String strNewAccountDataExcelFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
					 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
					 + strdFileName;	// specify path of the excel file with details to add new account.
			File newAccountDataExcelFile = new File(strNewAccountDataExcelFilePath);	// object for the excel file.
			List<HashMap<String,String>> lsthmapFileContents = utility.ReadFiles.readExcelFile(newAccountDataExcelFile, strdWorksheet);
			
			Integer intExcelRows = lsthmapFileContents.size();
			if (intExcelRows < 2)	// Header + 2 rows for Parent Child Account creation.
			{
				// log the errors and exit the TC. 
				Log.error("Minimum 2 data rows required for executing this test case; currently, there are: "+intExcelRows);
				Reporter.log("Minimum 2 data rows required for executing this test case; currently, there are: "+intExcelRows);
				verifyFalse(false);
				throw new Exception("Number of rows in excel file less than intended !");	// throw exception and exit the TC.
			}
			
			// Call for the method to add data to the Create Account Page / Form.
			AriaNewAccountCreation.fn_CreateAccount(driver, lsthmapFileContents, strTestCaseName, arrSearchFields, strFieldValue);
					
			Log.endTestCase(strTestCaseName);
			assertTrue(true);
		}
		catch (Exception exception)
		{
			Log.error("The Test Case Runtime New Parent Child Relational Account has endded prematurely due to failure: "+exception.toString());
			throw exception;
		}	
	}
    
/*    
    Function fn_VerifyRelationalAccount: This is the the dependent method which is called for the verification of Parent Child Account Creation.    						
*/
	    
    @Test(dependsOnGroups = {"Sanity"} , dependsOnMethods = {"fn_CreateRuntimeParentChildAccount"}, enabled = false)	// marks this method dependent on parent method.
    public void fn_VerifyRelationalAccount(String strDummyParentID, String strDummyNewAccountID)
    {    	
		Log.info("Verify New Parent Child Relational Called.");
    	String strParentID = null;
		String strParentIDMCA = null;
		
		// wait for the Account Overview page to come up.
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(NewAccountCreation.fn_clickAccountOverview()));
		driver.findElement(NewAccountCreation.fn_clickAccountOverview()).click();// click the Account Overview tab for setting focus.		
		
		try
		{
			/************ First Level Verification for the Newly added Parent Child Account ************/
			utility.Utils.takeScreenshot(driver, strTestCaseName);	// take snapshot for Account Overview Page after navigation. 
			
			// this wait is for finding the Account Hierarchy Glance table.
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(ParentChildNewAccount.fn_getAccountHierarchyGlanceDataTable()));
			
			//	defined a web element to read the entire table for Account Hierarchy Glance.
			WebElement tblAccountHierarchyGlance = driver.findElement(ParentChildNewAccount.fn_getAccountHierarchyGlanceDataTable()).findElement(By.tagName("tbody"));
			Log.info("Account Hierarchy Glance Data Table read successfully !");
			//	defined a list of web element to read the rows for Account Hierarchy Glance data table.			
			List<WebElement> lstwebRowsAccountHierarchyGlance = tblAccountHierarchyGlance.findElements(By.tagName("tr"));
			Log.info("Account Hierarchy Glance Data Table rows read successfully! Number of rows read: "+lstwebRowsAccountHierarchyGlance.size());
			// this for loop verifies the parent child relationship on the Child's Account Overview tab.
			for(WebElement rows : lstwebRowsAccountHierarchyGlance)
			{
				List<WebElement> cols = rows.findElements(By.tagName("td"));	// read the Web element list for columns.
				if (cols.size() != 0)	// skip comparison for "th" tag, which is header and move ahead.
				{
					if(cols.get(0).getText().trim().contentEquals("Parent"))
					{
						strParentID = cols.get(1).getText().trim().toString() ;	// converting the data to string for assert comparison.
						verifyTrue(strParentID.contentEquals(strDummyParentID), "Newly added Account is a successfull Child Account of an existing Parent Account!");
																						// assertion for New Account found in Account Hierarchy Glance table. 
						Log.info("Parent Child Relation verified @ first level.");
						break;	// called, so that we exit For loop and move ahead. 
					}
					else
					{
						if(!cols.iterator().hasNext())
						{
							Log.info("Parent Child Relation failed at first level, so exiting with error !");
							assertFalse(cols.iterator().hasNext(), "Newly added Account isn't child to any account");							
						}			
							
					}
				}				
			}			
			
			/************ Second Level Verification for the Newly added Parent Child Account ************/
			
			driver.findElement(ParentChildNewAccount.fn_clickParentAccount()).click();
										// clicking the Parent ID to navigate to the Parent Account page.
			
			// this wait is for finding the Account Hierarchy Glance table.
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(ParentChildNewAccount.fn_getAccountHierarchyGlanceDataTable()));
			
			// this wait is for finding the Manage Child Account Link.
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(ParentChildNewAccount.fn_clickManageChildAccounts()));
			// navigate to the Manage Child Accounts tab for verification.
			driver.findElement(ParentChildNewAccount.fn_clickManageChildAccounts()).click();
			driver.findElement(ParentChildNewAccount.fn_clickManageChildAccounts()).click();										
			
			// this wait is for finding the Manage Child Account Link.
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(ParentChildNewAccount.fn_getManageChildAccountsDataTable()));
			utility.Utils.takeScreenshot(driver, strTestCaseName);
			
			//	defined a web element to read the entire table for Manage Child Accounts Page.
			WebElement tblManageChildAccounts = driver.findElement(ParentChildNewAccount.fn_getManageChildAccountsDataTable()).findElement(By.tagName("tbody"));
			Log.info("Manage Child Accounts Page's Data Table read successfully !");
			//	defined a list of web element to read the rows for Manage Child Accounts Page's data table.			
			List<WebElement> lstwebRowsManageChildAccounts = tblManageChildAccounts.findElements(By.tagName("tr"));
			Log.info("Manage Child Accounts Page's Data Table rows read successfully! Number of rows read: "+lstwebRowsManageChildAccounts.size());
			// this for loop verifies the parent child relationship on the Parent's Account Overview tab.
			for(WebElement rows : lstwebRowsManageChildAccounts)
			{
				List<WebElement> cols = rows.findElements(By.tagName("td"));	// read the Web element list for columns.
				if (cols.size() != 0)	// skip comparison for "th" tag, which is header and move ahead.
				{					
					strParentIDMCA = cols.get(1).getText().trim().toString() ;	// converting the data to string for assert comparison.
					if(strParentIDMCA.contentEquals(strDummyNewAccountID))
					{
						Log.info("Parent Child Relation verified @ second level as well.");
						assertTrue(strParentIDMCA.contentEquals(strDummyNewAccountID), "Parent Child Relation verified @ second level as well.");
						break;	// called, so that we exit For loop and move ahead. 
					}
					else
					{
						if(!cols.iterator().hasNext())
						{
							Reporter.log("Parent Child Relation failed at second level, so exiting with error ONLY on Manage Child Page !");
																		// Report this to the Testng reporter.
							assertFalse(cols.iterator().hasNext(), "Newly added Account not found on the Manage Child Accounts page !");							
						}			
							
					}
				}				
			}			
		}
		catch (Exception exception)
		{
			Log.error("The call to method VerifyParentChildRelation for Verifying Parent Child Relation failed due to error: "+exception.toString());
			Reporter.log("ERROR: The call to method VerifyParentChildRelation for Verifying Parent Child Relation failed due to error: "+exception.toString());
			try
			{
				Utils.takeScreenshot(driver, "Error: "+strTestCaseName);
				throw exception;
			}
			catch (Exception exceptionf)
			{
				Log.error("Error occured while capturing with exception thrown as: "+exceptionf);
				Reporter.log("ERROR: Error occured while capturing with exception thrown as: "+exceptionf);				
			}			
		}		
	}
	

    //Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "RuntimeNewParentChildAccountCreation");
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
