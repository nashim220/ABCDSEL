/*
 Author     		:	Namrata Akarte
 Class Name			: 	Cls_GenerateInvoiceandValidateTaxes  
 Purpose     		: 	Purpose of this file is :
						To create the invoice and check if the taxes are appearing on invoice as per test data is set.
												
 Date       		:	04/26/2016
 Modified Date		:	05/13/2016
 Version Information:	Version 2.0
 
 Jira #				:	QAABE-541
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "TestCaseDetails" 
 						worksheet for excel "TestData.xlsx" in 'GenerateInvoice' section.
 						3. Data to be populated in newrecord_usage_Ireland.csv and newrecord_usage_updated.csv files.
 						4. Account used to be either stand alone accounts or parent accounts
 						5. Co_Code for the accounts under test to be set in the application

 Test Steps 		:	1. Search an account and verify if the account is set with appropriate Co_code and currency.
 						2. Set the Tax Exempt conditions as mentioned in the test data.
 						3. Load usage for the account and generate the invoice.
 						4. Verify if the taxes are displayed based on the condition set in test data file.
 						5. Reset the values for Tax Exempt Condition.
 						
 Version Changes 2.0:	1. Updated the dependent functions and their calls.
 						
*/

package testCases.AccountTestCases;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;

import atu.ddf.exceptions.DataDrivenFrameworkException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AriaEOM;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Invoices;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;

public class Cls_GenerateInvoiceandValidateTaxes extends VerificationMethods {

	
    public static WebDriver driver;
    public static String strTestCaseName = "Generate Invoice Validate Tax";
	public static WebDriverWait webWait;
    public static WebElement webElement;
    
    @BeforeClass
    public void beforeMethod()throws Exception {

    	//TODO: Initialize the Logger.
    	DOMConfigurator.configure("log4j.xml");	

		//TODO: Set Chrome driver as the driver to launch the test and maximize it
		System.setProperty("webdriver.chrome.driver",chromeDriverPath);
		driver = utility.Utils.fn_SetChromeDriverProperties();	
		
		//TODO: initialize the webWait
		webWait = new WebDriverWait(driver, 120);
		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.info("Login to Aria Application");
		//TODO: This parameters are taken from Exec file and its reference is available in EnvironmentDetails
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
    }

    
    @Test(dataProvider = "objectTestData", description="Generate Invoice Validate Tax")
    public void fn_GenerateInvoice(String strMPI, String strSPI, String strAccountNum, String strAPIURL, String strFileName	
    		, String strIsTaxApplicable, String strTaxExempted,	String strCurrency, String strCo_code
    		, String strInvoiceOption, String strInvoiceType, String strPendingInvoiceDate) throws Exception {
    	
    	//TODO: Create class objects to access the common methods.
    	AriaEOM objAriaEOM = new AriaEOM();
    	Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
    	AccountFunctions objAccountFunc = new AccountFunctions();	// for methods relating to Accounts functionality.
    	AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans(); 
    	AccountFunctions_Invoices objAcctFuncInvoices = new AccountFunctions_Invoices();
    	Utils objUtils = new Utils();
   
    	Boolean blnAdjustBillingDates = false;
    	Boolean blnInvoiceGenerated = false;
    	Boolean blnTaxpayerInfoUpdated = false;
        Boolean blnTaxes = true;
    	String strOptionType = "";
    	String strTaxpayerInfo = "";
    	String[] arrTaxpayerInfoInitial = null;
    	    	
    	//TODO: Mark the beginning of the Test.
       	Log.startTestCase(strTestCaseName);
       	
       	ArrayList<String> arrTaxes = new ArrayList<String>();
       	
       	try {
       		
       		//TODO: For the given Account, validate the 'CO_CODE' & 'BASE_CURRENCY_CD' to proceed with tests.
       		objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
       		objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);
       		String strAcctCurrencyCode = objClientDefAct.fn_GetCustomFieldValue(driver, webWait, strAccountNum
       										, "BASE_CURRENCY_CD", strTestCaseName);
       		Log.info("The BASE_CURRENCY_CD value for the current Account under test is: "+strAcctCurrencyCode);
       		String strAcctCountryCode = objClientDefAct.fn_GetCustomFieldValue(driver, webWait, strAccountNum
						, "CO_CODE", strTestCaseName);
       		Log.info("The CO_CODE value for the current Account under test is: "+strAcctCountryCode);
       		if(strAcctCurrencyCode.equals(strCurrency) && strAcctCountryCode.equals(strCo_code)) {
       			
               	//TODO: Based on the request, need to SET the Taxpayer Information.
       			strTaxpayerInfo = objAccountFunc.fn_SetAccountTaxpayerInfo(driver, webWait, strAccountNum, false, "12345", true, true, strTestCaseName);
       			if(strTaxpayerInfo.contains("#")) { // check if the account is set for tax exemption..
       				
       				arrTaxpayerInfoInitial = strTaxpayerInfo.split("#");       				
       				if(strTaxExempted.equalsIgnoreCase("Y")) {
       					
	   					if(arrTaxpayerInfoInitial[1].equalsIgnoreCase("true") || arrTaxpayerInfoInitial[2].equalsIgnoreCase("true"))
	           				Log.info("The Account under test is already set for Tax exemption, so we can proceed further with test..");
	   					else {
	   					
	   						strTaxpayerInfo = objAccountFunc.fn_SetAccountTaxpayerInfo(driver, webWait, strAccountNum, true, "12345", true, true, strTestCaseName);
	   						blnTaxpayerInfoUpdated = true;
	   					}	   						
       				}
       				else {

       					strTaxpayerInfo = objAccountFunc.fn_SetAccountTaxpayerInfo(driver, webWait, strAccountNum, true, "12345", false, false, strTestCaseName);
       					blnTaxpayerInfoUpdated = true;
       				}       					
       			}
       			else
       				assertTrue(false, "ERROR: There is an error in Reading / Setting Taxpayer Information ! Exiting TC with exception..");

        		//TODO: At the very beginning of the TC, check if the Billing dates can be adjusted, else exit !
        		Log.info("'AdjustAcctBillDates' module is being called for MPI's Billing dates adjustment..");
        		objAriaEOM.fn_clickAnalyticsReporting(driver, webWait).click();
        		blnAdjustBillingDates = objAccountFunc.fn_AdjustAcctBillDates(driver, webWait, strAPIURL, strAccountNum, 
        				strMPI, strInvoiceType, strPendingInvoiceDate, strTestCaseName);
               	if(blnAdjustBillingDates == false)
               		assertTrue(false, "ERROR: Since the Billing Date's can't be adjusted, Invoicing will fail, so exiting TC !");
       			
               	//TODO: Get the MPI's & SPI's Plan & Client Defined ID's.
               	Log.info("Getting the Plan & Client Defined ID's for the MPI & SPI sequentially..");
               	String strMPID = objAcctFuncPlans.fn_GetPlanInstanceID(driver, webWait, strAccountNum, strTestCaseName, strMPI);
               	String[] arrMPID = strMPID.split("#");	// [0] = Plan Instance ID; [1] = Client Defined ID.           	
               	String strSPID = objAcctFuncPlans.fn_GetPlanInstanceID(driver, webWait, strAccountNum, strTestCaseName, strSPI);
               	String[] arrSPID = strSPID.split("#");	// [0] = Plan Instance ID; [1] = Client Defined ID.
               	
        		//TODO: Load Data for specified account for load date.
           		String LOAD_USAGE_FILE_PATH = System.getProperty("user.dir")
                        + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
                        + System.getProperty("file.separator") + strFileName;           	
           		String strLoadDataDate = objUtils.fn_getLoadDataDate();
               	Log.info("Loading Account Usage for the date '"+strLoadDataDate+"'..");
               	ArrayList<String> arrListResponses = objAccountFunc.fn_LoadAccountUsage(driver, webWait, strAPIURL, LOAD_USAGE_FILE_PATH, strAccountNum, strLoadDataDate, arrMPID[1], arrSPID[1]);
               	if(arrListResponses.size() >= 1)
               		assertTrue(false, "ERROR: There was error recorded while Loading Usage, hence exiting the TC !");
          		           	
        		//TODO: Generate Advance Invoice for the Account for which data is currently uploaded & Verify Print Invoice.
               	if(strInvoiceOption.equalsIgnoreCase("MPI"))
               		strOptionType = arrMPID[0]+" - "+strMPI;
               	else if (strInvoiceOption.equalsIgnoreCase("Billing")) {
               		
               		//TODO: give a call to fetch the Billing Group Name and use it here.
               		String strBillingGroupName = null; /* REPLACE NULL WITH THE BILLING GROUP NAME */ 
               		strOptionType = strBillingGroupName;
               	}
               	blnInvoiceGenerated = objAcctFuncInvoices.fn_GenerateInvoice(driver, webWait, strAccountNum, strInvoiceType, "MPI", strOptionType, strTestCaseName);
               	if(blnInvoiceGenerated == true) {
               		
               		objAcctFuncInvoices.fn_ApproveAdvancedInvoices(driver, webWait);
               		Thread.sleep(1000);
               		Utils.takeScreenshot(driver, strTestCaseName);
               		//Since Invoice has been generated and Approved, we generate the Statement.
               		objAriaEOM.fn_clickAccounts(driver, webWait).click(); 
               		objAcctFuncInvoices.fn_GenerateInvoiceStatement(driver, webWait, strOptionType, strTestCaseName);
               		objAriaEOM.fn_clickAccounts(driver, webWait).click();
               	}
               	else {
               		
               		Utils.takeScreenshot(driver, strTestCaseName);
               		assertTrue(false, "ERROR: Invoice couldn't be generated !");
               	}
              	
               	//Select Latest invoice               	
               	Boolean blnLatestInvoiceSelected = objAcctFuncInvoices.fn_SelectLatestInvoice(driver, webWait);
               	if (blnLatestInvoiceSelected == true) {
    	       		
    	       		WebElement webInvoiceContentsTable = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
    	       		List<WebElement>invoiceRows = webInvoiceContentsTable.findElements(By.tagName("tr"));    	       		
    	       		for(WebElement irow : invoiceRows) {
    	       			
    	       			List<WebElement>invoiceCols = irow.findElements(By.tagName("td"));
    	       			
    	       			String strDescription = invoiceCols.get(2).getText();
    	       			Log.info("Description : "+strDescription);
    	       			
    	       			if (strDescription.contains("Taxes")) {
    	       				
    	       				String strTaxAmt = invoiceCols.get(7).getText();
    		       			Log.info("Tax Amount : "+strTaxAmt);
    		       			arrTaxes.add(strTaxAmt);			       			
    	       			}			       	    		
    	       		}       		
    	       		
    	       		//TODO: Validating the read Taxes array for values other than '0.00'; to decide whether TC is passed or otherwise.
    	       		if(strTaxExempted.equalsIgnoreCase("Y"))
	    	       		for (int iTax = 0; iTax < arrTaxes.size(); iTax++)    	       			
	    	       			if (!arrTaxes.get(iTax).toString().equalsIgnoreCase("0.00")) 
	    	       				blnTaxes = false;    	       		
    	       		Utils.takeScreenshot(driver, strTestCaseName);
    	       		
    	       		if ((blnTaxes == false) && (strTaxExempted.equalsIgnoreCase("Y") && (strIsTaxApplicable.equalsIgnoreCase("Y")))) {
    	       			
    	       			Log.error("Error : Taxes are getting displayed on the Invoice even if taxes are exempted for the Account");
    	       			Reporter.log("Error : Taxes are getting displayed on the Invoice even if taxes are exempted for the Account");
    	       			utility.VerificationMethods.assertTrue(false, "ERROR: Taxes are getting displayed on the Invoice even if taxes are exempted for the Account");
    	       		}
    	       		
    	       		if((blnTaxes == true) && (strTaxExempted.equalsIgnoreCase("Y") && (strIsTaxApplicable.equalsIgnoreCase("Y"))))
    	       			Log.info("Pass : Taxes are not getting displayed on the Invoice as taxes are exempted for the Account");
    	       		
    	       		if((blnTaxes == false) && (strTaxExempted.equalsIgnoreCase("N") && (strIsTaxApplicable.equalsIgnoreCase("Y"))))
    	       			Log.info("Pass : Taxes are getting displayed on the Invoice.");
    	       		
    	       		if((blnTaxes == true) && (strTaxExempted.equalsIgnoreCase("N") && (strIsTaxApplicable.equalsIgnoreCase("Y"))))
    	       			Log.info("Taxes are not applied. May be as it is not applicable for the usage applied.");
    	       	}
               	
               	//TODO: Based on the request, need to RESET the Taxpayer Information.
               	if(blnTaxpayerInfoUpdated == true) {
               		
               		Boolean blnState;
               		Boolean blnFed;               		
               		if(arrTaxpayerInfoInitial[1].equalsIgnoreCase("true"))
               			blnState = true;
               		else
               			blnState = false;
               		if(arrTaxpayerInfoInitial[2].equalsIgnoreCase("true"))
               			blnFed = true;
               		else
               			blnFed = false;
               	
               		objAccountFunc.fn_SetAccountTaxpayerInfo(driver, webWait, strAccountNum, true, "12345", blnState, blnFed, strTestCaseName);
               	}               	
       		}
	       	else {
	       		
				Log.error("ERROR: Account Country Code and Curreny did not match.. Exiting the TC...");
				Reporter.log("ERROR: Account Country Code and Curreny did not match.. Exiting the TC...");
				assertTrue(false, "ERROR: Account Country Code and Curreny did not match.. Exiting the TC...!");
			}       		
       	}
       	catch (Exception exception) {
       		
       		Log.error("ERROR: Couldn't Valdate Taxes due to exception: "+exception.toString());
       		Reporter.log("ERROR: Couldn't Valdate Taxes due to exception: "+exception.toString());
       		throw exception;
       	}
       	catch (AssertionError exception) {
       		
       		Log.error("ERROR: Couldn't Valdate Taxes due to exception: "+exception.toString());
       		Reporter.log("ERROR: Couldn't Valdate Taxes due to exception: "+exception.toString());
       		throw exception;
       	}
       	
       	//TODO: Mark the end of the Test.
       	Log.endTestCase(strTestCaseName);
    }    
   
      
	//TODO: Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException {
	
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "GenerateInvoice");
	}
	
	
	@AfterTest
	public void afterMethod() throws Exception {

		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
}