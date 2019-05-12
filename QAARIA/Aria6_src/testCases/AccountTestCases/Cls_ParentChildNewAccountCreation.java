/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_ParentChildNewAccountCreation 
 Purpose     		: 	Purpose of this file is :
						1. To capture all the page objects for the Creation of New Account 
						with Parent Child Relationship.
 
 Date       		:	06/16/2015
 Modified Date		:	11/04/2015, 07/10/2015
 Version Information:	Version 2.1
 
  PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "NewParentChildAccountCreation" worksheet for excel "TestData.xls".

 						2. The account to be added as Child to newly created account should have no Parent account.
 Test Steps 		:	1. Login using valid role credentials for creating New account.
 						2. Navigate to the Accounts -> Create New Accounts left Nav menu.
 						3. On Create New Account page, add details for new account.
 						4. Provide a Valid Parent ID for Parent Child relation.
 						4. Click Save and verify the success of added Account.
 						5. Navigate to the newly created account by clicking the Hyper link and then Verify data.
 						6. Verify Parent Child on the Account Overview Page with "Account Hierarchy Glance" section.
 						6. Click on the Parent Account Hyperlink and Navigate to the Parent's Account Page.
 						7. Click on the Manage Child Accounts tab.
 						8. Verify the existence of Account No for newly added Child Account.  	
 
 Version Changes 2.0:	1. Introduced page object based Web wait.
 						2. Changes to the parameters, class and method names as per the standards.
 Version Changes 2.1:	1. Changing the Before Class details to suit the generalisation agreed upon.  						
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/


package testCases.AccountTestCases;

import atu.ddf.exceptions.DataDrivenFrameworkException;

import org.testng.Reporter;
import org.testng.annotations.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import utility.*;
import appModules.AriaLoginLogout;
import appModules.AriaNewAccountCreation;
import pageObjects.NewAccountCreation;
import pageObjects.ParentChildNewAccount;

public class Cls_ParentChildNewAccountCreation extends VerificationMethods
{
	
    public static WebDriver driver;
    public static String strTestCaseName = "New Account with Parent Child Relation";
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
    Function fn_CreateParentChildAccount: This is the actual Test NG test for calling the functions to create parent Child new Account.    						
*/
	
	@Test(dataProvider = "objectTestData", description = "New Account with Parent Child Relation")
	public static void fn_CreateParentChildAccount(String strdFileName, String strdWorksheet, String strFieldNames, String strFieldValues) throws Exception
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
			
			// Call for the method to add data to the Create Account Page / Form.
			AriaNewAccountCreation.fn_CreateAccount(driver, lsthmapFileContents, strTestCaseName, arrSearchFields, strFieldValue);
			
			Log.info("New account created successfully with Parent Child Relationship.");
			Log.endTestCase(strTestCaseName);
		}
		catch (Exception exception)
		{
			Log.error("The Test Case New Account with Parent Child Relation has endded prematurely due to failure: "+exception.toString());
			Reporter.log("ERROR: The Test Case New Account with Parent Child Relation has endded prematurely due to failure: "+exception.toString());
        	utility.Utils.takeScreenshot(driver, strTestCaseName);
			assertFalse(false);
		}				
	}
    
/*    
    Function fn_CreateParentChildAccount: This is the the dependent method which is called for the creation and  
    									  verification of Parent Child Account Creation.    						
*/
	
    @Test(dependsOnGroups = {"Sanity"} , dependsOnMethods = {"fn_CreateParentChildAccount"}, enabled = false)	// marks this method dependent on parent method.
    public void fn_VerifyParentChildRelation(String strChildAccountID)
    {    	
		Log.info("Verify Parent Child Relation Called.");
    	String strChildID = null;

		// wait for the Account Overview page to come up.
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(NewAccountCreation.fn_clickAccountOverview()));
		driver.findElement(NewAccountCreation.fn_clickAccountOverview()).click();// click the Account Overview tab for setting focus.
		
		try
		{
			// this wait is for finding the Account Hierarchy Glance table.
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(ParentChildNewAccount.fn_getAccountHierarchyGlanceDataTable()));
			// navigate to the Manage Child Accounts Tab Page.
			ParentChildNewAccount.fn_clickManageChidlAccounts(driver, webWait);			
			ParentChildNewAccount.fn_clickManageChidlAccounts(driver, webWait).click();
			Log.info("Managed Child Accounts Tab has been clicked.");
			
			// navigate to the Assign child Account sub-page.
			ParentChildNewAccount.fn_clickAssignChildAccount(driver, webWait).click();
			
			// pass on the child key which we want to enter.			
			ParentChildNewAccount.fn_getChildAccountNo(driver, webWait).sendKeys(strChildAccountID);
			
			// select the value for Responsibility level; currently selected value is Standard Self Pay (This too can be parameterized in future)
			ParentChildNewAccount.fn_clickResponsibilityLevel(driver, webWait, "1").click();
			
			Log.info("All data values required for the Child Account assignment have been added.");
			utility.Utils.takeScreenshot(driver, strTestCaseName);
			
			// now save the changes by clicking the Assign Child Account button.
			ParentChildNewAccount.fn_clickAssignChildAccountButton(driver, webWait).click();
			
			// waiting for this link to come up.
			ParentChildNewAccount.fn_clickAssignChildAccount(driver, webWait);
			
			assertTrue(ParentChildNewAccount.fn_clickAssignChildAccount(driver, webWait).isDisplayed(), "Child Account not Assigned successfuly.");
			utility.Utils.takeScreenshot(driver, strTestCaseName);

			// read the entire table for the child accounts.
			WebElement tblChildAccounts = ParentChildNewAccount.fn_getChildAccountsDataTable(driver, webWait).findElement(By.tagName("tbody"));
			// now read the rows in the table.
			List <WebElement> lstwebRowsChildAccount = tblChildAccounts.findElements(By.tagName("tr"));
			// Now the following for loop will traverse the list for verifying the rows value.
			for (WebElement rows: lstwebRowsChildAccount)
			{
				List <WebElement> cols = rows.findElements(By.tagName("td"));	// read the various columns in the rows.
				if(cols.size() != 0)	// avoid the columns which are Headers for the table.
				{
					if(cols.get(1).getText().trim().toString().contentEquals(strChildAccountID))
					{
						assertTrue(cols.get(1).getText().trim().toString().contentEquals(strChildAccountID), "The assigned account is successfully made Child Account.");
						Log.info("The assigned account is successfully made Child Account.");
						break;	// to exit the for loop since the purpose is served.
					}					
				}
				else
				{
					if(!cols.iterator().hasNext())
					{
						Log.info("Parent Child Relation failed at first level, so exiting with error !");
						assertFalse(cols.iterator().hasNext(), "Child Account isn't Assigned successfully !");							
					}
				}
			}
			
			/************ Second Level Verification for the Newly added Parent Child Account ************/
			
			// now click the Account Overview tab for further verifications.
			ParentChildNewAccount.fn_clickAccountOverview(driver, webWait).click();
			// this wait is for finding the Account Hierarchy Glance table.
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(ParentChildNewAccount.fn_getAccountHierarchyGlanceDataTable()));
			utility.Utils.takeScreenshot(driver, strTestCaseName);
			
			//	defined a web element to read the entire table for Account Hierarchy Glance.
			WebElement tblAccountHierarchyGlance = driver.findElement(ParentChildNewAccount.fn_getAccountHierarchyGlanceDataTable()).findElement(By.tagName("tbody"));
			Log.info("Account Hierarchy Glance Data Table read successfully !");
			//	defined a list of web element to read the rows for Account Hierarchy Glance data table.			
			List<WebElement> lstwebRowsAccountHierarchyGlance = tblAccountHierarchyGlance.findElements(By.tagName("tr"));
			Log.info("Account Hierarchy Glance Data Table rows read successfully! Number of rows read: "+lstwebRowsAccountHierarchyGlance.size());
			
			for(WebElement rows : lstwebRowsAccountHierarchyGlance)	// for loop to search for the child entry.
			{
				List<WebElement> cols = rows.findElements(By.tagName("td"));	// read the Web element list for columns.
				if (cols.size() != 0)	// skip comparison for "th" tag, which is header and move ahead.
				{
					if(cols.get(0).getText().trim().contentEquals("Child"))
					{
						strChildID = cols.get(1).getText().trim().toString() ;	// converting the data to string for assert comparison.
						verifyTrue(strChildID.contentEquals(strChildAccountID), "Newly added Account is a successfull Parent Account of an existing Account!");
																						// assertion for New Account found in Account Hierarchy Glance table. 
						Log.info("Parent Child Relation verified @ second level.");
						break;	// called, so that we exit For loop and move ahead. 
					}
					else
					{
						if(!cols.iterator().hasNext())
						{
							Reporter.log("Parent Child Relation failed at second level, so exiting with error !");	// Report this to the Testng reporter.
							Log.error("Parent Child Relation failed at first level, so exiting with error !");
							assertFalse(cols.iterator().hasNext(), "Newly added Account has no child account");							
						}
					}
				}
			}
		}
		catch (Exception exception)
		{
			Log.error("Verification for Child Parent account could not be done due to an exception thrown as: "+exception);
			Reporter.log("ERROR: Verification for Child Parent account could not be done due to an exception thrown as: "+exception);
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
	    return objUtils.data1("TestCaseDetails", "NewParentChildAccountCreation");
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
