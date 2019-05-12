/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_BanAccount 
 Purpose     		: 	Purpose of this file is :
						1. To Terminate Account for fraudulent use as suggested in QAABE-54.
						2. Verify the success of Terminated account.

 Date       		:	06/24/2015
 Modified Date		:	11/02/2015, 07/01/2015
 Modified By		:	Aashish Bhutkar
 Version Information:	Version 3.0
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" worksheet for excel "TestData.xls".
 						
 Test Steps 		:	1. Login using valid role credentials for terminating an account.
 						2. Navigate to the Accounts -> Search --> Ad-Hoc Search in left Nav menu.
 						3. Search the Account # which is to be banned.
 						4. Navigate to the Account Details page.
 						5. Click on the Status hyperlinked text on Accounts Overview Tab page. (OR: Navigate to the Ban Account Tab.)
 						6. Clicking on the Account Status Maintenance, navigate to the Account closure page.
 						7. Select the Termination Radio Button, fill in the billing related details and then Change Status.
 						8. Verify the Termination Status.
 						9. Reset the Active status for the Account which was recently Terminated.

Version Changes 2.0:	1. Introduced page object based Web wait.
						2. Changes to the parameters, class and method names as per the standards.
Version Changes 3.0:	1. Updated Logic to accept all accounts other than those in Terminated state.
						2. API call to reset the status of account to Active once it has been successfully Terminated.
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AccountTestCases;

import java.util.List;

import atu.ddf.exceptions.DataDrivenFrameworkException;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;

import utility.ApiHandler;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;
import appModules.AccountFunctions;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
import pageObjects.BanAccount;
import pageObjects.ChangeDeleteClientDefinedFields;


public class Cls_BanAccount extends VerificationMethods 
{
	
    public static WebDriver driver;
    public static String strTestCaseName = "Terminate existing Account for fraudulent usage.";
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
    Function fn_TerminateAccount: This is the actual Test NG test for changing the account status.    						
*/

	@Test(dataProvider = "objectTestData", description = "Terminate existing Account for fraudulent usage.")
	public void fn_TerminateAccount(String strTerminateAccountNum, String strUserRole, String strAPIURL) throws Exception
	{
    	//TODO: Create class objects to access the common methods.
    	AccountFunctions objAccountFunc = new AccountFunctions();	// for methods relating to Accounts functionality.
    	ApiHandler objAPIHandler = new ApiHandler();	// for post method to be called
		ChangeDeleteClientDefinedFields objClientDefFields = new ChangeDeleteClientDefinedFields();
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
		
		Log.startTestCase(strTestCaseName);	// Log the beginning of the Test Case.				

		try
		{
			Log.info("Calling Search Account Number reusable module.");
			objClientDefAct.AccountSearch(driver, webWait, strTerminateAccountNum);

			Log.info("Successfully searched the Account Number: "+strTerminateAccountNum.toString());
			
			// this wait is for finding the Account Overview tab.
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(BanAccount.fn_clickAccountOverview()));
			driver.findElement(BanAccount.fn_clickAccountOverview()).click();

			// this wait is for finding the Account Overview tab.
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(BanAccount.fn_verifyStatus()));
			utility.Utils.takeScreenshot(driver, strTestCaseName);
			// following if Loop will permit ONLY "ACTIVE" accounts to be terminated.
			if(!driver.findElement(BanAccount.fn_verifyStatus()).getAttribute("innerText").toString().contains("TERMINATED"))
			{
				Log.info("The termination activity is being carried our with user role as : "+strUserRole);
				
/*		        driver.findElement(AriaBanAccountPageObjects.clickBanAccount()).click();	// navigate to ban Account Tab.
	            TimeUnit.SECONDS.sleep(5);
*/
	            driver.findElement(BanAccount.fn_clickStatusAccountsOverview()).click();	// navigate to the Account Status page.
				
	            // this wait is for getting the Status Account Overview page.
				webElement = webWait.until(ExpectedConditions.elementToBeClickable(BanAccount.fn_clickChangeAccountStatusLink()));
				utility.Utils.takeScreenshot(driver, strTestCaseName);
				Log.info("Status Account Overview Link has been clicked.");
				
	            driver.findElement(BanAccount.fn_clickChangeAccountStatusLink()).click();	// navigate to the actual page for changing Account Status.
	            
	            // this wait is for getting the Status Options on page.
				webElement = webWait.until(ExpectedConditions.elementToBeClickable(BanAccount.fn_getStatusDataTable()));
				utility.Utils.takeScreenshot(driver, strTestCaseName);
				Log.info("Change Status Account Link has been clicked.");
	            
	            // create a Web element object to read the data table for various Status options available.
	            WebElement tblStatus = driver.findElement(BanAccount.fn_getStatusDataTable()).findElement(By.tagName("tbody"));
	            Log.info("Status Data Table for various statuses has been read.");
	            //create a list of Web elements to read the various rows in the above read data table.
	            List<WebElement> lstwebRowsStatus = tblStatus.findElements(By.tagName("tr"));
	            Log.info("Status Data Table rows have now been read.");		            
	            // now traverse the rows for various column values to search for the Terminated Status.
	            for (WebElement rows: lstwebRowsStatus)
	            {
	            	// create a list of web elements for columns to be read.
	            	List<WebElement> cols = rows.findElements(By.tagName("td"));
	            	
	            	if(cols.size() != 0)	// This is to exclude the header which has been read.
	            	{
	            		if(cols.get(1).getText().toString().contentEquals("TERMINATED"))
	            		{
	            			cols.get(0).click();	// this is to select the Radio Button for Status TERMINATED.
	            			Log.info("TERMINATED Radio button has been selected.");
	            			
	            			// click the value for the Status Change drop down.
	            			driver.findElement(BanAccount.fn_clickStatusChange()).click();
	            			driver.findElement(BanAccount.fn_clickStatusChange()).sendKeys("None (change immediately)");
	            			Log.info("Value for Status Change drop down has been selected.");

	            			// currently Reset Billing Dates is set to "Reset account's billing dates to account status change date".
	            			driver.findElement(BanAccount.fn_clickResetBillingDates("2")).click();
	            			Log.info("Value for Reset Billing Dates is set to 'Reset account's billing dates to account status change date'.");
	            			
	            			// currently Suppress Dunning Process is set to No.
	            			driver.findElement(BanAccount.fn_clickSuppressDunningProcess("N")).click();
	            			Log.info("Value for Suppress Dunning Process is set to 'No' .");
	            			
	            			// pass on the CSR Comment as fraudulent as per TC required.
	            			driver.findElement(BanAccount.fn_getCSRComments()).clear();
	            			String strComment = "This Account has been marked Terminated since it is fraudulent !";
	            			driver.findElement(BanAccount.fn_getCSRComments()).sendKeys(strComment);
	            			Log.info("Logical CSR comments for closure has been passed on.");
	            			
	            			utility.Utils.takeScreenshot(driver, strTestCaseName);
	            			break;	// exit the for Loop since, we are done with finding the TERMINATED row.
	            		}
	            	}		            			            	
	            }
	                			
    			// finally click the Change Account Status button.
    			driver.findElement(BanAccount.fn_clickChangeAccountStatusButton()).click();
    			Log.info("Save Status Change button has been clicked.");
	            // this wait is for getting the Cancel link displayed.
    			webElement = webWait.until(ExpectedConditions.elementToBeClickable(BanAccount.fn_clickCancel()));
	            // this wait is for getting the Save Status Change button.
				webElement = webWait.until(ExpectedConditions.elementToBeClickable(BanAccount.fn_clickSaveStatusChangeButton()));
	            // click the Save Status Change		            
	            driver.findElement(BanAccount.fn_clickSaveStatusChangeButton()).click();

	            // this wait is for getting the table showing the modified Account Status.
				webElement = webWait.until(ExpectedConditions.elementToBeClickable(BanAccount.fn_getAccountStatusHistoryDataTable()));

				utility.Utils.takeScreenshot(driver, strTestCaseName);		            
    			Log.info("The Save Status change button has been clicked for confirmation of changes done.");
    			
    			// create a Web element for reading the Account Status History table.
	            WebElement tblAccountStatusHistory = driver.findElement(BanAccount.fn_getAccountStatusHistoryDataTable()).findElement(By.tagName("tbody"));
	            Log.info("Account Status History Data Table for verification of actual statuses has been read.");
	            //create a list of Web element to read the rows of above read table.
	            List<WebElement> lstwebRowsAccountStatusHistory = tblAccountStatusHistory.findElements(By.tagName("tr"));
	            Log.info("Rows from the Account Status History Data Table are read.");
	            // this for loop is to verify if Terminated status has reflected or not.
	            for(WebElement rowsASH : lstwebRowsAccountStatusHistory)
	            {
	            	List <WebElement> colsASH = rowsASH.findElements(By.tagName("td"));
	            	if (colsASH.size() != 0)	// to skip rows read for header.
	            	{
	            		if(colsASH.get(0).getText().toString().trim().contentEquals("TERMINATED"))
	            		{
	            			if(colsASH.get(6).getText().toString().trim().contains("- Present"))
	            			{
	            				// The status Terminated has been found and also, the Status From-To is correct.
	            				Log.info("The Account has been successfully Terminated.");		            				
	            				break;	// break from the for loop since we have already got the confirmation ! 
	            			}
	            		}
	            		else if (!colsASH.iterator().hasNext())	// Termination failure.
	            		{
	            			Log.error("TERMINATED STATUS not seen in the list, hence exiting with error !");
	            			Reporter.log("ERROR: TERMINATED STATUS not seen in the list, hence exiting with error !");
	            			//throw new Exception ("TERMINATED STATUS not seen in the list, hence exiting with error !");
	            			assertFalse(true, "TERMINATED STATUS not seen in the list, hence exiting with error !");
	            		}
	            	}
	            }	            
				Log.info("Account listed to be terminated has been successfully terminated; Account Number is: "+strTerminateAccountNum);
				objClientDefFields.fn_AcctLink(driver, webWait).click();	//Click account link to reset.
				
				String strFullURL = objAccountFunc.fn_GetFullUrl(driver, webWait, strAPIURL, "update_acct_status");
				String strPostURL = strFullURL +"&account_no="+strTerminateAccountNum+"&status_cd=1";
				Log.info("Making an API call to reset the terminated account's status to active with call: "+strPostURL);
				objAPIHandler.makeSimplePostCall(strPostURL);
				
				Log.endTestCase(strTestCaseName);
			}
			else
			{
				Log.error("ERROR: The Account Number to be Terminated is already Terminated, hence this scenario fails for account: "+strTerminateAccountNum);
				Reporter.log("ERROR: The Account Number to be Terminated is already Terminated, hence this scenario fails for account: "+strTerminateAccountNum);
				Utils.takeScreenshot(driver, strTestCaseName);
				objClientDefFields.fn_AcctLink(driver, webWait).click();	//Click account link to reset.
				assertTrue(false, "ERROR: The Account Number to be Terminated is already Terminated, hence this scenario fails !");				
			}
		}
		catch (Exception exception)
		{
			addVerificationFailure(exception);
			Log.error("The Test Case Terminate existing Account for fraudulent usage has endded prematurely due to failure: "+exception.toString());
			Reporter.log("EXCEPTION: The Test Case Terminate existing Account for fraudulent usage has endded prematurely due to failure: "+exception.toString());
			Utils.takeScreenshot(driver, strTestCaseName);
			
			throw exception;
		}
		catch (AssertionError exception)
		{
			addVerificationFailure(exception);
			Log.error("The Test Case Terminate existing Account for fraudulent usage has endded prematurely due to failure: "+exception.toString());
			Reporter.log("ERROR: The Test Case Terminate existing Account for fraudulent usage has endded prematurely due to failure: "+exception.toString());
			Utils.takeScreenshot(driver, strTestCaseName);
			
			throw exception;
		}
		catch (Error exception)
		{
			addVerificationFailure(exception);
			Log.error("The Test Case Terminate existing Account for fraudulent usage has endded prematurely due to failure: "+exception.toString());
			Reporter.log("ERROR: The Test Case Terminate existing Account for fraudulent usage has endded prematurely due to failure: "+exception.toString());
			Utils.takeScreenshot(driver, strTestCaseName);
			
			throw exception;
		}
	}

	
	//Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "TerminateAccount");
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