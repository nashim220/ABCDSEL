/*
 Author     		:	Namrata Akarte
 Class Name			: 	Cls_GenerateInvoiceandValidateTaxes  
 Purpose     		: 	Purpose of this file is :
						To create the invoice and check if the taxes are appearing on invoice as per test data is set.
												
 Date       		:	02/03/2015
 
 Version Information:	Version 1.0
 
 Jira #				:	QAABE-541
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "TestCaseDetails" 
 						worksheet for excel "TestData.xlsx" in 'GenerateInvoice' section.
 						3. Data to be populated in newrecord_usage_Ireland.csv and newrecord_usage_updated.csv files.
 						4. Account used to be either standalone accounts or parent accounts
 						5. Co_Code for the accounts under test to be set in the application

 Test Steps 		:	1. Search an account and verify if the account is set with appropriate Co_code and currency.
 						2. Set the Tax Exempt conditions as mentioned in the test data.
 						3. Load usage for the account and generate the invoice.
 						4. Verify if the taxes are displayed based on the condition set in test data file.
 						5. Reset the values for Tax Exempt Condition.
 						
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/
package testCases.AccountTestCases;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ChangeDeleteClientDefinedFields;
import pageObjects.UsagePageObjects;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Invoices;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_GenerateInvoiceandValidateTaxes{
	
	public WebDriver driver;
	public WebDriverWait webWait;
	public String strAuthenticationKey;
	public String strTestCaseName = "Generate Invoice";
	public Boolean blnTaxes = true;
	
	@BeforeClass
	public void beforeMethod()throws Exception
	{	
		DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.		
		
		//TODO: Set Chrome driver as the driver to launch the test.
		driver = utility.Utils.fn_SetChromeDriverProperties();
				
		//TODO: initialize the webWait
		webWait = new WebDriverWait(driver, 120);
		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.info("Login to application");
		//This parameters are taken from Exec file and its reference is available in EnvironmentDetails
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);
	
	}
	
	@Test(dataProvider="objectTestData", description="GenerateInvoice")
	public void fn_GenerateInvoice(String strAPIUrl, String strFileName, String strPlanName, String strAcctNum, String strTaxesAppl, String strTaxExempted, String strCurrency, String strCoCode) {
		
		UsagePageObjects objUsage = new UsagePageObjects();	
		AccountFunctions_Invoices ObjInvoices = new AccountFunctions_Invoices();
		AccountFunctions objfnctns = new AccountFunctions();
		AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
		Cls_ChangeDeleteClientDefinedFieldActns obj = new Cls_ChangeDeleteClientDefinedFieldActns();
		ChangeDeleteClientDefinedFields objClientDefFields = new ChangeDeleteClientDefinedFields();
		ArrayList<String> arrListResponses = new ArrayList<String>();
		String strPlanNumber = "";
		Boolean blnLatestInvoiceSelected = false;
		
		ArrayList<String> arrTaxes = new ArrayList<String>();
		
				
		//TODO: To Load Data for specified account for current date, set the test data file path
 		String LOAD_USAGE_FILE_PATH = System.getProperty("user.dir")
              + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
              + System.getProperty("file.separator") + strFileName; 
			
		try {	
			
			//TODO : Search Account Number
			obj.AccountSearch(driver, webWait, strAcctNum);
			
			String strCurr = driver.findElement(By.xpath(".//*[@id='content-wrapper']/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td[4]")).getText();
			Log.info("Currency is :"+strCurr);
						
			String strFieldVal = obj.fn_GetCustomFieldValue(driver, webWait, strAcctNum, "Co_Code", strTestCaseName);		
			
			if (strFieldVal.equalsIgnoreCase(strFieldVal))
				Log.info("Custom defined fields is valid");
			
			if (strCurr.contains(strCurrency) && strFieldVal.equalsIgnoreCase(strFieldVal)){
				
				Log.info("Good to go ahead as Account Country Code and Curreny matched");
			
			
				if ((strTaxesAppl.equalsIgnoreCase("Y")) && (strTaxExempted.equalsIgnoreCase("Y"))){
					
					//TODO : Search Account Number
					Thread.sleep(3000);
					//TODO: Click on Tax Payer Info link
					objUsage.fn_ClickTaxPayerInfmtnlnk(driver, webWait).click();			
					//TODO : Click on Edit button
					objUsage.fn_EditFields(driver, webWait).click();
					Thread.sleep(2000);
				 	//TODO : Select State Tax
					objUsage.fn_ClickStateTaxChkbx(driver, webWait).click();
					Thread.sleep(2000);
					//TODO : Select Nation Tax
					objUsage.fn_ClickNationTaxChkbx(driver, webWait).click();
					//TODO : Click on Save Button
					objUsage.fn_SaveChanges(driver, webWait).click();
					Thread.sleep(500);
					Utils.takeScreenshot(driver,strTestCaseName);
					
				}
					
				//TODO : Load the usage to the account
				strPlanNumber = objAcctFuncPlans.fn_GetPlans(driver, webWait, strPlanName, strTestCaseName);
				
				if(strPlanNumber.contentEquals(""))
					throw new Exception("Supplemental Plan Number couldn't be retrieved !");				
					
				boolean blnSupplementalPlansStatus = objAcctFuncPlans.fn_VerifySupplementalPlans(driver, webWait, strAcctNum, strTestCaseName);
				if(blnSupplementalPlansStatus == false)
				{
					objAcctFuncPlans.fn_AssignSuppPlan(driver, webWait, strAcctNum, strPlanNumber, strAPIUrl);
					Log.info("Supplemental Plan with number "+strPlanNumber+" has been assigned to the Account Number: "+strAcctNum);
				}		
		 			 		
				//TODO : Adjust Billing dates for the Account to Generate Invoice  	
				boolean blnAdjustDate=objfnctns.fn_AdjustAcctBillDates(driver, webWait, strAPIUrl, strAcctNum, strTestCaseName);
				if (blnAdjustDate==true)
				{
					Log.info("Account Billing Date has been set successfully");
				}
				else
				{
					 Log.info("ERROR: Account Billing Date has not been set");
					 utility.VerificationMethods.assertTrue(false, "ERROR: Since the Billing Date's can't be adjusted, Invoicing will fail, so exiting TC !");
				}	
			
				//TODO: Fetching the Date for Loading Usage	
		   		String strLoadDataDate = objfnctns.fn_getLoadDataDate();
		       	Log.info("Loading Account Usage for the date '"+strLoadDataDate+"'..");	       	
		            	
		       	Thread.sleep(200);
		       	//TODO: Loading the Usage
		       	arrListResponses = objfnctns.fn_LoadAccountUsage(driver, webWait, strAPIUrl, LOAD_USAGE_FILE_PATH, strAcctNum, strLoadDataDate);
		       	Thread.sleep(2000);
		       	
		            	
		     	for (String strResponse : arrListResponses){
		     		if (strResponse.equalsIgnoreCase("usage code or type not on active plan"))
		     		{
		     			Log.info("ERROR : Usage is not loaded for the account : "+strAcctNum);
		     			utility.VerificationMethods.assertTrue(false, "ERROR : Usage is not loaded for the account : "+strAcctNum);		     			
		     		}	     		
		     		
		     	}
		  		           	
				//TODO: Generate Advance Invoice for the Account for which data is currently uploaded & Verify Print Invoice.
		       	boolean blnInvoiceGenerated = ObjInvoices.fn_GenerateInvoice(driver, webWait, strAcctNum, "Advanced");	       	
		       	
		       	Thread.sleep(2000);
		       	if(blnInvoiceGenerated == true)
		       	{
		       		boolean blnInvoiceApproved = ObjInvoices.fn_ApproveAdvancedInvoices(driver, webWait);
		       		if(blnInvoiceApproved == false)
		       			Log.info("ERROR: Invoice generated couldn't be approved !");
		       		
		       		//Since Invoice has been generated and Approved, we generate the Statement. 
		       		webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Accounts"))).click();
		       		ObjInvoices.fn_GenerateInvoiceStatement(driver, webWait, strTestCaseName);
		       	}
		       	else
		       	{
		       		Log.info("ERROR: Invoice couldn't be generated !");
		       		utility.VerificationMethods.assertTrue(false, "ERROR: Invoice is not generated, so exiting TC !");
		       	} 	   	
		       		       	
		       	
		       	if(strTaxesAppl.equalsIgnoreCase("Y")){	       		
		       		
			       	Thread.sleep(3000);			
					//TODO : Select the latest invoice
			       	blnLatestInvoiceSelected = ObjInvoices.fn_SelectLatestInvoice(driver, webWait);
			       	if (blnLatestInvoiceSelected == true){
			       		
			       		WebElement tabInvoices = driver.findElement((By.xpath("//*[contains(@id,'DataTables_Table_')]")));
			       		WebElement tabInvoiceContents = tabInvoices.findElement(By.tagName("tbody"));
			       		
			       		List<WebElement>invoiceRows = tabInvoiceContents.findElements(By.tagName("tr"));
			       		
			       		for(WebElement irow : invoiceRows){
			       			
			       			List<WebElement>invoiceCols = irow.findElements(By.tagName("td"));
			       			
			       			String strDescription = invoiceCols.get(2).getText();
			       			Log.info("Description : "+strDescription);
			       			
			       			if (strDescription.contains("Taxes")){
			       				
			       				String strTaxAmt = invoiceCols.get(6).getText();
				       			Log.info("Tax Amount : "+strTaxAmt);
				       			arrTaxes.add(strTaxAmt);			       			
			       			}			       	    		
			       		}       		
			       		
			       		for (int iTax = 0; iTax < arrTaxes.size(); iTax++){
			       			
			       			if (!arrTaxes.get(iTax).toString().equalsIgnoreCase("0.00")){
			       				
			       				blnTaxes = false;
			       				
			       			}
			       		
			       		}
			       		
			       		Utils.takeScreenshot(driver, strTestCaseName);
			       		
			       		if ((blnTaxes == false) && (strTaxExempted.equalsIgnoreCase("Y"))){
			       			
			       			Log.error("Error : Taxes are getting displayed on the invoice even if taxes are exempted for the account");
			       			Reporter.log("Error : Taxes are getting displayed on the invoice even if taxes are exempted for the account");
			       			utility.VerificationMethods.assertTrue(false, "ERROR: Taxes are getting displayed on the invoice even if taxes are exempted for the account");
			       		}
			       		
			       		if((blnTaxes == true) && (strTaxExempted.equalsIgnoreCase("Y"))){
			       			
			       			Log.info("Pass : Taxes are not getting displayed on the invoice as taxes are exempted for the account");
			       		}
			       		
			       		if((blnTaxes == false) && (strTaxExempted.equalsIgnoreCase("N"))){
			       			
			       			Log.info("Pass : Taxes are getting displayed on the invoice.");
			       		}	
			       		
			       		if((blnTaxes == true) && (strTaxExempted.equalsIgnoreCase("N"))){
			       			
			       			Log.info("Taxes are not applied. May be as it is not applicable for the usage applied.");
			       		}			       		
			       	}
			       	
			       	
		       	}	
		       	
				//TODO : Resetting the values for Tax exmp 
		       	if ((strTaxesAppl.equalsIgnoreCase("Y")) && (strTaxExempted.equalsIgnoreCase("Y"))){	
		       		
		       		//TODO : Click on the Accounts link
		       		objClientDefFields.fn_AcctLink(driver, webWait).click();
			       	Boolean blnIsAcctFound = obj.AccountSearch(driver, webWait, strAcctNum);
			       	Thread.sleep(1000);
			       	if (blnIsAcctFound == true){
						//TODO: Click on Tax Payer Info link
						objUsage.fn_ClickTaxPayerInfmtnlnk(driver, webWait).click();			
						//TODO : Click on Edit button
						objUsage.fn_EditFields(driver, webWait).click();
						Thread.sleep(2000);
					 	//TODO : De-select State Tax
						objUsage.fn_ClickStateTaxChkbx(driver, webWait).click();
						Thread.sleep(2000);
						//TODO : De-Select Nation Tax
						objUsage.fn_ClickNationTaxChkbx(driver, webWait).click();
						//TODO : Click on Save Button
						objUsage.fn_SaveChanges(driver, webWait).click();
						Thread.sleep(500);
						Utils.takeScreenshot(driver,strTestCaseName);
			       	}
		       	}	
		       	
		       	if (strTaxesAppl.equalsIgnoreCase("N")){
		       		//TODO : click on the Accounts link
		       		objClientDefFields.fn_AcctLink(driver, webWait).click();
		       	}
			}
	       	else{
				Log.info("Account Country Code and Curreny did not match.. Exiting the TC...");
				utility.VerificationMethods.assertTrue(false, "ERROR: Account Country Code and Curreny did not match.. Exiting the TC...!");
			}
			
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (Exception e) {
			
			Log.error("Account does not exist "+e.toString());
			Reporter.log("Account Not Found "+e.toString());
		}
		
			
		
	}
	
	//TODO: Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "GenerateInvoice");
	}
		
	//TODO : Logout of the Application and Close the driver
	@AfterClass
	public void afterClass() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
}
