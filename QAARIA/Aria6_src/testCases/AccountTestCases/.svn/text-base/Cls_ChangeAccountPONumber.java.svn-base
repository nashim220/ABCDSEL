/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_ChangeAccountPONumber 
 Purpose     		: 	Purpose of this file is :
						1. To Verify that PO# changed via UI & API is displayed correctly on Invoice.
						2. Test Class for the test case QAABE-360.
 
 Date       		:	09/21/2015
 Modified Date		:	11/17/2015, 10/27/2015, 09/28/2015
 Version Information:	Version 3.2
 
  PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "ChangeAccountPONumber" worksheet for excel "TestData.xls".
 						
 Test Steps 		:	1. Login using valid role credentials for accessing an account.
 						2. Navigate to the searched Accounts -> Client Defined Fields.
 						3. Verify the Value for the "CURRENT_PO_NUM".
 						4. If empty/unassigned, assign a new value.
 						4. Verify this UI modified value is retained.
 						5. Modify this value via API & verify this value on UI.
 						6. Generate an Invoice & check if the current "CURRENT_PO_NUM" value is displayed on 
 						the printable version of the Invoice.
 						7. Manually update the PO Num for the generated Invoice and check if this new value is 
 						displayed on the printable version of the Invoice.
 						
 Version Changes 2.0:	1. Moved function "fn_SelectLatestInvoice" to reusable module "AccountFunctions_Invoices".  						
 Version Changes 3.0:	1. Updated Logic for getting the custom field value due to change in reusable function.
 						2. Added logic to check if the Billing Dates can be set for the account and then proceed for Invoicing.
 						3. Added logic to generate the Statement for the newly created Invoice.
 Version Changes 3.1:	1. Updated Logic for waits due to long time intervals required for Print Window to come up. 						
 Version Changes 3.2:	1. Removed dead imports to remove the Warning messages displayed. 
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AccountTestCases;

import atu.ddf.exceptions.DataDrivenFrameworkException;
import org.apache.log4j.xml.DOMConfigurator;

import java.awt.Toolkit;
import java.util.List;
import java.util.Set;
import javax.swing.JDialog;
import javax.swing.JLabel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ChangeAccountPONumber;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Invoices;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
import utility.ApiHandler;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;

public class Cls_ChangeAccountPONumber extends VerificationMethods 
{
    public static WebDriver driver;
    public static String strTestCaseName = "Verify changed PO number for any Account";
	public static WebDriverWait webWait;
    public static WebElement webElement;
    
    @BeforeClass
    public void beforeMethod()throws Exception
    {	
    	//TODO: Initialize the Logger.
    	DOMConfigurator.configure("log4j.xml");	

		//TODO: Set Chrome driver as the driver to launch the test.
		driver = utility.Utils.fn_SetChromeDriverProperties();
		
		//TODO: initialize the webWait
		webWait = new WebDriverWait(driver, 60);
		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.info("Login to Aria Application");
		//TODO: This parameters are taken from Exec file and its reference is available in EnvironmentDetails
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
    }
    
    
/*    
    Function fn_VerifyChangedPONum: This is the actual Test NG test for Verifying the updated Customer Information.
*/
	@Test(dataProvider = "objectTestData", description = "Verify changed PO number for any Account")
    public void fn_VerifyChangedPONum(String strPlanName, String strAccountNum, String strAPIURL, String strFileName
    			, String strCustomFieldDataUI, String strCustomFieldDataAPI, String strCustomPOValue) throws Exception
    {
    	//TODO: Create object for the classes to be used for accessing reusable codes.
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefActions = new Cls_ChangeDeleteClientDefinedFieldActns();
    	AccountFunctions objAccountFunc = new AccountFunctions();	// for methods relating to Accounts functionality.
    	AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
    	AccountFunctions_Invoices objAcctFuncInvoices = new AccountFunctions_Invoices();
		ApiHandler objApiHandler = new ApiHandler();
		ChangeAccountPONumber objChangeAcctPO = new ChangeAccountPONumber();		
		
		//TODO: declare booleans & objects to govern the actions to be performed.
		Boolean blnSupplementalPlansStatus = false;
		Boolean blnInvoiceGenerated = false;
		Boolean blnPONumVerified = false;
		Boolean blnLatestInvoiceSelected = false;
		Boolean blnCustomPONumVerified = false;
		Boolean blnAdjustBillingDates = false;
		String strRestCall = "modify_acct_supp_fields";
				
    	//TODO: Log the beginning of the test case.
    	Log.startTestCase(strTestCaseName);
    	
    	try
    	{
    		//TODO: At the very beginning of the TC, check if the Billing dates can be adjusted, else exit !  
    		blnAdjustBillingDates = objAccountFunc.fn_AdjustAcctBillDates(driver, webWait, strAPIURL, strAccountNum, strTestCaseName);
           	if(blnAdjustBillingDates == false)
           		assertTrue(false, "ERROR: Since the Billing Date's can't be adjusted, Invoicing will fail, so exiting TC !");
    		
    		//TODO: For the specified account, set the PO Number for verification on Invoice via UI.
           	objChangeAcctPO.fn_clickAccounts(driver, webWait).click();
			webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Account Overview"))).click();
    		webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Reset Password")));
    		String strPONum = objClientDefActions.fn_GetCustomFieldValue(driver, webWait, strAccountNum, "CURRENT_PO_NUM", strTestCaseName);
    		Log.info("The current value for the Custom Field 'CURRENT_PO_NUM' is: "+strPONum);
    		if(strPONum == null)
    		{
    			objClientDefActions.AddNewField(driver, webWait, "CURRENT_PO_NUM", strCustomFieldDataUI);
    		}
    		else
    		{
    			objClientDefActions.fn_UpdateCustomFields(driver, webWait, "CURRENT_PO_NUM", strCustomFieldDataUI, strTestCaseName);        		
    		}
    		
    		//TODO: Verify that the UI set PO Number value is retained.
    		Log.info("Verifying if the 'CURRENT_PO_NUM' value is retained after changing it through UI..");
    		fn_VerifyClientDefinedFieldRetained(driver, webWait, strAccountNum, strCustomFieldDataUI);
    		//objChangeAcctPO.fn_clickAccounts(driver, webWait).click();
    		
    		//TODO: For the specified account, set the PO Number for verification on Invoice via API call.
    		Log.info("Deleting already existing value for the Custom Defined Field so that successfull API call can be made..");
    		Boolean blnDeleteCustomFieldValue = objClientDefActions.fn_DeleteCustomFieldValue(driver, webWait, strAccountNum, "CURRENT_PO_NUM", strTestCaseName);
    		if (blnDeleteCustomFieldValue == true)
    		{
           		Log.info("Custom Defined Field is now set for API modification.");
    			String strPlanNumber = objAcctFuncPlans.fn_GetPlans(driver, webWait, strPlanName, strTestCaseName);
           		if(strPlanNumber.contentEquals(""))
           			throw new Exception("Supplemental Plan Number couldn't be retrieved !");
    			blnSupplementalPlansStatus = objAcctFuncPlans.fn_VerifySupplementalPlans(driver, webWait, strAccountNum, strTestCaseName);
           		if(blnSupplementalPlansStatus == false)
           		{
           			objAcctFuncPlans.fn_AssignSuppPlan(driver, webWait, strAccountNum, strPlanNumber, strAPIURL);
           			Log.info("Supplemental Plan with number "+strPlanNumber+" has been assigned to the Account Number: "+strAccountNum);
           		}
           		//TODO: get the full URL for API call.
    			Log.info("Making the API call for updating the PO Num value.");
    			String strAPICall = objAccountFunc.fn_GetFullUrl(driver, webWait, strAPIURL, strRestCall);
    			strAPICall = strAPICall + "&acct_no="+strAccountNum	+ "&supp_field_name=CURRENT_PO_NUM" 
    									+ "&supp_field_value="+strCustomFieldDataAPI;
    			objApiHandler.makeSimplePostCall(strAPICall);	//make the API call to modify the supplementary field.    			    		
    			
        		//TODO: Verify that the API set PO Number value is retained.
    			Log.info("Verifying if the 'CURRENT_PO_NUM' value is retained after changing it through API..");
    			objChangeAcctPO.fn_clickAccounts(driver, webWait).click();
    			webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Account Overview"))).click();
        		webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Reset Password")));
        		fn_VerifyClientDefinedFieldRetained(driver, webWait, strAccountNum, strCustomFieldDataAPI);
    		}
    		else
    		{
    			Log.error("Error: The existing value for the Custom Field Cannot be deleted and hence API verification can't be done...");
    			Reporter.log("Error: The existing value for the Custom Field Cannot be deleted and hence API verification can't be done...");
    		}
    		
    		//TODO: Load Data for specified account for load date.
       		String LOAD_USAGE_FILE_PATH = System.getProperty("user.dir")
                    + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
                    + System.getProperty("file.separator") + strFileName;           	
       		String strLoadDataDate = objAccountFunc.fn_getLoadDataDate();
           	Log.info("Loading Account Usage for the date '"+strLoadDataDate+"'..");
           	objAccountFunc.fn_LoadAccountUsage(driver, webWait, strAPIURL, LOAD_USAGE_FILE_PATH, strAccountNum, strLoadDataDate);
      		           	
    		//TODO: Generate Advance Invoice for the Account for which data is currently uploaded & Verify Print Invoice.
           	blnInvoiceGenerated = objAcctFuncInvoices.fn_GenerateInvoice(driver, webWait, strAccountNum, "Advanced");
           	if(blnInvoiceGenerated == true)
           	{
           		objAcctFuncInvoices.fn_ApproveAdvancedInvoices(driver, webWait);
           		Thread.sleep(1000);
           		Utils.takeScreenshot(driver, strTestCaseName);
           		//Since Invoice has been generated and Approved, we generate the Statement. 
           		webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Accounts"))).click();
           		objAcctFuncInvoices.fn_GenerateInvoiceStatement(driver, webWait, strTestCaseName);
           		objChangeAcctPO.fn_clickAccounts(driver, webWait).click();
           	}
           	else
           	{
           		Utils.takeScreenshot(driver, strTestCaseName);
           		assertTrue(false, "ERROR: Invoice couldn't be generated !");
           	}
           	Log.info("Verifying the newly generated Advance Invoice for the PO Number value...");
           	
           	//TODO: Displaying a Message Box to indicate the user that there is a hard-coded wait for populating data for View & Print.
           	String strPrintFrameMsg = "<html><div align='center'><font size='5' face='SANS_SERIF'>There is a 1 minute wait introduced for Invoice Details to reflect on View Print Frame.</font>"
					+ "<br><br><br><font size='20' face='SANS_SERIF' color='red'>Please do not touch the browser; can cause TC to fail !</font>"
					+ "<br><br><br><br><br><br><br><font size='5' face='SANS_SERIF'>Please Note: This Message Box will be auto closed after 1 minute !</font></div></html>";
           	JDialog jdlgPrintFrame = new JDialog();
           	jdlgPrintFrame.setTitle("WARNING !");
           	jdlgPrintFrame.setModal(false);
           	jdlgPrintFrame.add(new JLabel(strPrintFrameMsg, JLabel.CENTER));
           	jdlgPrintFrame.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
           	jdlgPrintFrame.setAlwaysOnTop(true);
           	jdlgPrintFrame.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
           	jdlgPrintFrame.setResizable(false);
           	jdlgPrintFrame.pack();
           	jdlgPrintFrame.setVisible(true);
           	Thread.sleep(60000);
           	jdlgPrintFrame.dispose();
           	
           	blnLatestInvoiceSelected = objAcctFuncInvoices.fn_SelectLatestInvoice(driver, webWait);
           	if(blnLatestInvoiceSelected == false)
           		assertTrue(false, "ERROR: Invoice couldn't be selected !");
           	Utils.takeScreenshot(driver, strTestCaseName);
           	blnPONumVerified  = fn_VerifyViewPrintFrame(driver, webWait, strCustomFieldDataAPI);
           	if(blnPONumVerified == false)
           		assertTrue(false, "ERROR: PO Number assigned by API couldn't be verified !");
  		        	
        	
        	//TODO: Update PO Number field ONLY for the current Invoice and check if the data is displayed by revisiting View & Print.
           	Log.info("Verifying now if the Custom PO Number entered for a generated Invioce is available on the Printable format of Invoice..");
           	objChangeAcctPO.fn_getInvoicesDataTbale(driver, webWait);
           	objChangeAcctPO.fn_clickCustomInvoiceStatusDropDown(driver, webWait).sendKeys("PO NUM");
           	objChangeAcctPO.fn_getNotesTextField(driver, webWait).clear();
           	objChangeAcctPO.fn_getNotesTextField(driver, webWait).sendKeys(strCustomPOValue);
           	objChangeAcctPO.fn_clickSave(driver, webWait).click();
           	String strStatusMessage = objChangeAcctPO.fn_getStatusMessage(driver, webWait).getAttribute("innerText").toString().trim();
           	if (strStatusMessage.contains("Changes Saved"))
           	{
           		objChangeAcctPO.fn_getInvoicesDataTbale(driver, webWait);
           		Utils.takeScreenshot(driver, strTestCaseName);
           		blnCustomPONumVerified = fn_VerifyViewPrintFrame(driver, webWait, strCustomPOValue);
           	}
           	else
           	{
           		assertTrue(false, "ERROR: The changes made to Invoicing details couldn't be saved, hence exiting with exception !");
           	}
           	
           	if(blnPONumVerified == false)
           		assertTrue(false, "ERROR: The Invoice generated doesn't have the latest API updated PO Number '"+strCustomFieldDataAPI+"'in it !");
           	if(blnCustomPONumVerified == false)
           		assertTrue(false, "ERROR: The Invoice generated doesn't have the latest Custom updated PO Number '"+strCustomPOValue+"'in it !");
    	}
    	catch (Exception exception)
    	{
    		Utils.takeScreenshot(driver, strTestCaseName);
    		Log.error("ERROR: Verification for the PO Number change couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Verification for the PO Number change couldn't be done due to exception:  "+exception.toString());
    		throw exception;
    	}
    	catch (AssertionError exception)
    	{
    		Utils.takeScreenshot(driver, strTestCaseName);
    		Log.error("ERROR: Verification for the PO Number change couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Verification for the PO Number change couldn't be done due to exception:  "+exception.toString());
    		throw exception;
    	}
    	    	
    	//TODO: Log the end of the test case. 
    	Log.endTestCase(strTestCaseName);		
    }
	
/*    
	Function fn_VerifyClientDefinedFieldRetained: This method will verify if the changed Client Defined Field is retained.    						
*/
	
    @Test(dependsOnGroups = {"Sanity"} , dependsOnMethods = {"fn_VerifyChangedPONum"}, enabled = false)	// marks this method dependent on parent method.
    public void fn_VerifyClientDefinedFieldRetained(WebDriver driver, WebDriverWait webWait, String strAccountNum, String strCustomFieldData) throws Exception
    {
    	//TODO: Create object for the classes to be used for accessing reusable codes.
    	Cls_ChangeDeleteClientDefinedFieldActns objClientDefActions = new Cls_ChangeDeleteClientDefinedFieldActns();
    	ChangeAccountPONumber objChangeAcctPO = new ChangeAccountPONumber();
    	
    	String strPONum = null;
    	
    	try
    	{    		
    		strPONum = objClientDefActions.fn_GetCustomFieldValue(driver, webWait, strAccountNum, "CURRENT_PO_NUM", strTestCaseName);
    		if(strPONum.contentEquals(strCustomFieldData))
    		{
    			Log.info("The Updated PO Number is retained which is: "+strPONum);
    			objChangeAcctPO.fn_clickAccounts(driver, webWait).click();
    		}
    		else
    		{
    			assertTrue(false, "ERROR: The Updated PO Number is not retained !");
    		}
    	}
    	catch (Exception exception)
    	{
    		Log.error("ERROR: Verification for the PO Number change couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Verification for the PO Number change couldn't be done due to exception:  "+exception.toString());
       		throw exception;
    	}
    	catch (AssertionError exception)
    	{
    		Log.error("ERROR: Verification for the PO Number change couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Verification for the PO Number change couldn't be done due to exception:  "+exception.toString());
    		throw exception;
    	}		
    }


/*    
	Function fn_VerifyViewPrintFrame: This method will verify if the Print Frame has the latest PO Number in it.    						
*/	
    @Test(dependsOnGroups = {"Sanity"} , dependsOnMethods = {"fn_VerifyClientDefinedFieldRetained"}, enabled = false)	// marks this method dependent on parent method.
    public Boolean fn_VerifyViewPrintFrame(WebDriver driver, WebDriverWait webWait, String strCustomFieldData) throws Exception
    {
    	//TODO: Create object for the classes to be used for accessing reusable codes.
    	ChangeAccountPONumber objChangeAcctPO = new ChangeAccountPONumber();
    	AccountFunctions_Invoices objAcctFuncInvoices = new AccountFunctions_Invoices();
    	
    	Boolean blnPONumSearchStatus = false;
    	String strParentWindow = driver.getWindowHandle();
    	String strPrintWindow = null;
    	    	
    	try
    	{
    		objChangeAcctPO.fn_getInvoicesDataTbale(driver, webWait);
    		objChangeAcctPO.fn_clickViewPrint(driver, webWait).click();
    		String strPONum = null;
    		
    		Thread.sleep(10000);
    		//TODO: get the frame for the View & Print and verify the PO Number on it.
    		Set <String> strWindowHandles = driver.getWindowHandles();    		
    		strWindowHandles.remove(strParentWindow);
    		strPrintWindow = strWindowHandles.iterator().next();
    		
    		driver.switchTo().window(strPrintWindow);	// setting the window driver to the newly launched Print Frame Window.      		
    		WebElement webPageContents = webWait.until(ExpectedConditions.elementToBeClickable(objAcctFuncInvoices.fn_ReadViewPrintPage(driver).findElement(By.xpath("//table[@class = 'inv']"))));
    		WebElement webInvoicePrintDetails = webPageContents.findElement(By.tagName("tbody"));
    		Utils.takeScreenshot(driver, strTestCaseName);
    		List<WebElement> lstwebInvoiceRows = webInvoicePrintDetails.findElements(By.tagName("tr"));
    		Log.info("Verifying the generated Invoice for the PO Number '"+strCustomFieldData+"'...");
    		for(WebElement row: lstwebInvoiceRows)
    		{	
    			List<WebElement> cols = row.findElements(By.tagName("td"));
    			
    			if(cols.size() == 1)
    			{
    				strPONum = cols.get(0).getAttribute("innerText").toString().trim();
    				if(strPONum.contains(strCustomFieldData))
    				{
    					Log.info("The Invoice generated has the latest updated PO Number in it as: "+strPONum);
    					blnPONumSearchStatus = true;
    					break;
    				}    				    				
    			}    			
    		}
    		driver.close();
    		if(blnPONumSearchStatus == false)
			{
				driver.switchTo().window(strParentWindow);
				Log.error("The Invoice generated doesn't have the latest updated PO Number in it which should have been: "+strCustomFieldData);
				Reporter.log("The Invoice generated doesn't have the latest updated PO Number in it which should have been: "+strCustomFieldData);				
			}
    		Thread.sleep(1000);
    		driver.switchTo().window(strParentWindow);
    	}
    	catch (Exception exception)
    	{
    		Log.error("ERROR: Verification for the PO Number on Print Frame couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Verification for the PO Number on Print Frame couldn't be done due to exception:  "+exception.toString());
       		throw exception;
    	}
    	
    	return blnPONumSearchStatus;
    }     
    

	//TODO: Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "ChangeAccountPONumber");
	}
	
	
	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
}
