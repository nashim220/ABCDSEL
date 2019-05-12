/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_BanAccount 
 Purpose     		: 	Purpose of this file is :
						1. To Terminate Account for fraudulent use as suggested in QAABE-54.
						2. Verify the success of Terminated account.

 Date       		:	03/29/2016
 Modified Date		:	07/06/2016
 Modified By		:	Aashish Bhutkar
 Version Information:	Version 2.0
 
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
 						
 Version Changes 2.0:	1. Modified the PO file for Account Overview. 						

 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AccountTestCases;

import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;

import atu.ddf.exceptions.DataDrivenFrameworkException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import pageObjects.AccountsAccountOverview;
import pageObjects.AriaEOM;

public class Cls_BanAccount extends VerificationMethods {
	
    public static WebDriver driver;
    public static String strTestCaseName = "Terminate Account for Fraudulent Usage";
	public static WebDriverWait webWait;
    public static WebElement webElement;
    
	@BeforeClass
	 public void beforeMethod()throws Exception {

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
	public void fn_TerminateAccount(String strTerminateAccountNum, String strUserRole, String strAPIURL) throws Exception {

		//TODO: Create class objects to access the common methods.
    	AccountFunctions objAccountFunc = new AccountFunctions();	// for methods relating to Accounts functionality.
    	ApiHandler objAPIHandler = new ApiHandler();	// for post method to be called
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
		AriaEOM objAriaEOM = new AriaEOM();
		AccountsAccountOverview objAcctOvervw = new AccountsAccountOverview();
		
		Log.startTestCase(strTestCaseName);	// Log the beginning of the Test Case.				

		try {

			objClientDefAct.AccountSearch(driver, webWait, strTerminateAccountNum);
			objAriaEOM.fn_clickAccountOverview(driver, webWait).click();
			objAcctOvervw.fn_verifyStatus(driver, webWait);
			utility.Utils.takeScreenshot(driver, strTestCaseName);
			// following if Loop will permit ONLY "ACTIVE" accounts to be terminated.
			if(!objAcctOvervw.fn_verifyStatus(driver, webWait).getAttribute("innerText").toString()
								.contains("TEMPORARY SERVICE BAN")) {

				Log.info("The termination activity is being carried our with user role as : "+strUserRole);
				
				// navigate to the Account Status page.
				objAcctOvervw.fn_clickStatusAccountsOverview(driver, webWait).click();				
	            // this wait is for getting the Status Account Overview page.
				objAcctOvervw.fn_clickChangeAccountStatus(driver, webWait);
				utility.Utils.takeScreenshot(driver, strTestCaseName);				
				// navigate to the actual page for changing Account Status.
				objAcctOvervw.fn_clickChangeAccountStatus(driver, webWait).click();
	            
	            // create a Web element object to read the data table for various Status options available.
	            WebElement tblStatus = objAcctOvervw.fn_getStatusDataTable(driver, webWait).findElement(By.tagName("tbody"));	            
	            List<WebElement> lstwebRowsStatus = tblStatus.findElements(By.tagName("tr"));
	            utility.Utils.takeScreenshot(driver, strTestCaseName);
	            
	            Integer intStatus = 0;
	            for (WebElement rows: lstwebRowsStatus) {

	            	// create a list of web elements for columns to be read.
	            	List<WebElement> cols = rows.findElements(By.tagName("td"));
	            	
	            	intStatus ++;
	            	if(cols.size() != 0) {	// This is to exclude the header which has been read.

	            		if(cols.get(0).getText().toString().contentEquals("Temporary Service Ban")) {

	            			cols.get(0).findElement(By.xpath("//td[text()='Temporary Service Ban']/input")).click();
	            			
	            			// click the value for the Status Change drop down.
	            			objAcctOvervw.fn_clickStatusChange(driver, webWait).click();
	            			objAcctOvervw.fn_clickStatusChange(driver, webWait).sendKeys("None (change immediately)");
	            			// currently Reset Billing Dates is set to "Reset account's billing dates to account status change date".
	            			objAcctOvervw.fn_clickResetBillingDates(driver, webWait, "2").click();	            			
	            			// pass on the CSR Comment as fraudulent as per TC required.
	            			objAcctOvervw.fn_getCSRComments(driver, webWait).clear();
	            			String strComment = "This Account has been marked 'Temporary Service Ban' since it is fraudulent !";
	            			objAcctOvervw.fn_getCSRComments(driver, webWait).sendKeys(strComment);
	            			
	            			utility.Utils.takeScreenshot(driver, strTestCaseName);
	            			break;	// exit the for Loop since, we are done with finding the TERMINATED row.
	            		}
	            	}

	            	if(intStatus >= lstwebRowsStatus.size())	            		
	            		assertTrue(false, "ERROR: The 'Temporary Service Ban' Status code isn't available !");
	            }
	                			
    			// finally click the Change Account Status button.
	            objAcctOvervw.fn_clickChangeAccountStatusButton(driver, webWait).click();
	            // this wait is for getting the table showing the modified Account Status.
	            objAcctOvervw.fn_getAccountStatusHistoryDataTable(driver, webWait);
				utility.Utils.takeScreenshot(driver, strTestCaseName);		            
    			
    			// create a Web element for reading the Account Status History table.
	            WebElement tblAccountStatusHistory = objAcctOvervw.fn_getAccountStatusHistoryDataTable(driver, webWait).findElement(By.tagName("tbody"));
	            List<WebElement> lstwebRowsAccountStatusHistory = tblAccountStatusHistory.findElements(By.tagName("tr"));
	            // this for loop is to verify if Terminated status has reflected or not.
	            for(WebElement rowsASH : lstwebRowsAccountStatusHistory) {

	            	List <WebElement> colsASH = rowsASH.findElements(By.tagName("td"));
	            	if (colsASH.size() != 0) {	// to skip rows read for header.

	            		if(colsASH.get(0).getText().toString().trim().contentEquals("TEMPORARY SERVICE BAN")) {

	            			if(colsASH.get(2).getText().toString().trim().contains("- Present")) {

	            				// The status Terminated has been found and also, the Status From-To is correct.
	            				Log.info("The Account has been successfully Terminated.");		            				
	            				break;	// break from the for loop since we have already got the confirmation ! 
	            			}
	            		}
	            		else if (!colsASH.iterator().hasNext()) {	// Termination failure.

	            			Log.error("ERROR: 'TEMPORARY SERVICE BAN' status not seen in the Account Status History, hence exiting with error !");
	            			Reporter.log("ERROR: 'TEMPORARY SERVICE BAN' status not seen in the Account Status History, hence exiting with error !");
	            			assertFalse(true, "ERROR: 'TEMPORARY SERVICE BAN' status not seen in the Account Status History, hence exiting with error !");
	            		}
	            	}
	            }	            
				Log.info("Account listed to be terminated has been successfully terminated; Account Number is: "+strTerminateAccountNum);
				objAriaEOM.fn_clickAccounts(driver, webWait).click();	//Click account link to reset.
				
				String strFullURL = objAccountFunc.fn_GetFullUrl(driver, webWait, strAPIURL, "update_acct_status");
				String strPostURL = strFullURL +"&account_no="+strTerminateAccountNum+"&status_cd=1";
				Log.info("Making an API call to reset the terminated account's status to active with call: "+strPostURL);
				objAPIHandler.makeSimplePostCall(strPostURL);
				
				Log.endTestCase(strTestCaseName);
			}
			else {

				Log.error("ERROR: The Account Number to be Terminated is already Terminated, hence this scenario fails for account: "+strTerminateAccountNum);
				Reporter.log("ERROR: The Account Number to be Terminated is already Terminated, hence this scenario fails for account: "+strTerminateAccountNum);
				Utils.takeScreenshot(driver, strTestCaseName);
				assertTrue(false, "ERROR: The Account Number to be Terminated is already Terminated, hence this scenario fails !");				
			}
		}
		catch (Exception exception) {

			Log.error("The Test Case Terminate existing Account for fraudulent usage has endded prematurely due to failure: "+exception.toString());
			Reporter.log("EXCEPTION: The Test Case Terminate existing Account for fraudulent usage has endded prematurely due to failure: "+exception.toString());
			Utils.takeScreenshot(driver, strTestCaseName);
			
			throw exception;
		}
		catch (AssertionError exception) {

			Log.error("The Test Case Terminate existing Account for fraudulent usage has endded prematurely due to failure: "+exception.toString());
			Reporter.log("ERROR: The Test Case Terminate existing Account for fraudulent usage has endded prematurely due to failure: "+exception.toString());
			Utils.takeScreenshot(driver, strTestCaseName);
			
			throw exception;
		}
		catch (Error exception) {

			Log.error("The Test Case Terminate existing Account for fraudulent usage has endded prematurely due to failure: "+exception.toString());
			Reporter.log("ERROR: The Test Case Terminate existing Account for fraudulent usage has endded prematurely due to failure: "+exception.toString());
			Utils.takeScreenshot(driver, strTestCaseName);
			
			throw exception;
		}
	}

	
	//Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException {

	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "TerminateAccount");
	}
	
	
	@AfterTest
	public void afterMethod() throws Exception {

		 //Logs out of the application
		 AriaLoginLogout.appLogout(driver); 
		 //Quitting the driver
		 driver.quit(); 
	}
}