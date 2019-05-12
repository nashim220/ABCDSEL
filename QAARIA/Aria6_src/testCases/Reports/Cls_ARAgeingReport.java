/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_ARAgeingReport 
 Purpose     		: 	Purpose of this file is :
						1. To verify if the GL segment code is passed in report.
						2. This is automation of TC: QAABE-95.

 Date       		:	12/30/2015
 Modified Date      :	01/18/2015
 Version Information:	Version 1.1
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "ARAgeing" worksheet 
 						for excel "TestData.xls". 

 Test Steps 		:	1. Login using valid role credentials for updating existing customer account.
 						2. Search the customer account to view the payment details.
 						3. Navigate to the Analytics & reporting -> Reports -> Run Reports.
 						4. Check the "Aging Report with Statement – SGAS", "Aging by Account" & "Aging by Account w/Name" for Payment Details.
 						5. Generate Partial / Full Payments, Void Payments as well as Refunds to reverify these reports.
 						6. Void Payments and revisit the Step-4 reports for validation.
 						
Version Changes 1.1:	1. Modified the Logic for selecting such a transaction which is related to the currently generated
						Invoice; Invoice generated during TC execution. 						
 						 						
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.Reports;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.log4j.xml.DOMConfigurator;
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

import appModules.AccountFunctions;
import appModules.AccountFunctions_Invoices;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import utility.ApiHandler;
import utility.CsvHandler;
import utility.EnvironmentDetails;
import utility.Log;
import utility.ReportUtils;
import utility.Utils;
import utility.VerificationMethods;

public class Cls_ARAgeingReport extends VerificationMethods{
	
    public static WebDriver driver;
    public static String strTestCaseName = "Verify AR Ageing Reports";
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
    Function fn_VerifyARAgeing: This is the actual Test NG test for Verifying the AR Ageing Reports.
    
    Inputs:	blnBatch =	true: This test execution for Account # has to be done for Batch Execution; generate new Invoice.
						false: This test execution for Account # has to be done just for Report Validation; don't generate Invoice. 
*/
	@Test(dataProvider = "objectTestData", description = "Verify AR Ageing Reports")
    public void fn_VerifyARAgeing(String strPlanName, String strAccountNum, String strAPIURL, String strFileName
    		, String strBatch, String strRefCode) throws Exception{
		
		//TODO: Create object for the classes to be used for accessing reusable codes.
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
		AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();		
		AccountFunctions objAccountFunc = new AccountFunctions();
		AccountFunctions_Invoices objAcctFuncInvoices = new AccountFunctions_Invoices();
		ApiHandler objAPIHandler = new ApiHandler();
		
		Boolean blnSupplementalPlansStatus = false;
		Boolean blnAdjustBillingDates = false;
		Boolean blnInvoiceGenerated = false;
		Boolean blnGenericReportValidation = false;
		Boolean blnFullPayReportValidation = false;
		Boolean blnVoidPayReportValidation = false;
		Boolean blnPartialPayReportValidation = false;
		Boolean blnRefundsValidation = false;
		Boolean blnValidateARAging = true;
				
		List<List<NameValuePair>> lstpairStatements = new ArrayList<List<NameValuePair>>();		
		
		String strInvoiceNumber = null;
		String strPayments = null;
		String[] arrPayments = null;
		String strStatementNum = null;
		String strTxnNumber = null;
		String strTxnDescription = null;
		String strAmmount = null;
		Random rnRefCode = new Random();
		
    	//TODO: Log the beginning of the test case.
    	Log.startTestCase(strTestCaseName);
    	
		try{
			
			if(strBatch.equalsIgnoreCase("false")){
				
				//TODO: Search the account and then get it's Form of Payment Value, All Account Details & Statement-Transaction details.
				lstpairStatements = objAcctFuncInvoices.fn_getStatementTransactions(driver, webWait, strAccountNum, strAPIURL);
				Log.info("Statements and it's related Transaction(s) data has been captured.");
				webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Analytics and Reporting"))).click();

				blnValidateARAging = false;
				blnGenericReportValidation = fn_ValidateReports(driver, webWait, strAccountNum, strAPIURL, lstpairStatements, "General Validations");
				if (blnGenericReportValidation == true)
					blnValidateARAging = true;
			}
			//TODO: If Batch test, generate Invoice for it.
			else if(strBatch.equalsIgnoreCase("true")){
				
				String strPlanNumber = objAcctFuncPlans.fn_GetPlans(driver, webWait, strPlanName, strTestCaseName);
	       		if(strPlanNumber.contentEquals(""))
	       			assertTrue(false, "Supplemental Plan Number couldn't be retrieved !");
				blnSupplementalPlansStatus = objAcctFuncPlans.fn_VerifySupplementalPlans(driver, webWait, strAccountNum, strTestCaseName);
	       		if(blnSupplementalPlansStatus == false){
	       			
	       			objAcctFuncPlans.fn_AssignSuppPlan(driver, webWait, strAccountNum, strPlanNumber, strAPIURL);
	       			Log.info("Supplemental Plan with number "+strPlanNumber+" has been assigned to the Account Number: "+strAccountNum);
	       		}
	       		
	          	blnAdjustBillingDates = objAccountFunc.fn_AdjustAcctBillDates(driver, webWait, strAPIURL, strAccountNum, strTestCaseName);
	           	if(blnAdjustBillingDates == false)
	           		assertTrue(false, "ERROR: Since the Billing Date's can't be adjusted, Invoicing will fail, so exiting TC !");
	       		
	       		String LOAD_USAGE_FILE_PATH = System.getProperty("user.dir")
	                    + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
	                    + System.getProperty("file.separator") + strFileName;           	
	       		String strLoadDataDate = objAccountFunc.fn_getLoadDataDate();
	       		Log.info("Loading Account Usage for the date '"+strLoadDataDate+"'..");
	           	objAccountFunc.fn_LoadAccountUsage(driver, webWait, strAPIURL, LOAD_USAGE_FILE_PATH, strAccountNum, strLoadDataDate);
	      		           	
	    		//TODO: Generate Advance Invoice for the Account for which data is currently uploaded.
	           	blnInvoiceGenerated = objAcctFuncInvoices.fn_GenerateInvoice(driver, webWait, strAccountNum, "Advanced");
	           	if(blnInvoiceGenerated == true){
	           		
	           		Thread.sleep(3500);
	           		strInvoiceNumber = driver.findElement(By.xpath("//td/a[text()='Approve/View']/following::td/a")).getText();
	           		Log.info("The Invoice Number for newly generated Invoice is: "+strInvoiceNumber);
	           		if(strInvoiceNumber == null)
		           		assertTrue(false, "ERROR: Invoice was generated, but the Invoice Number couldn't be retrieved !");	           		

	           		objAcctFuncInvoices.fn_ApproveAdvancedInvoices(driver, webWait);
	           		
	           		//Since Invoice has been generated and Approved, we generate the Statement. 
	           		webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Accounts"))).click();
	           		objAcctFuncInvoices.fn_GenerateInvoiceStatement(driver, webWait, strTestCaseName);
	        	}	           	

	           	//TODO: Recapture the Transaction details after Invoice has been generated.	           	
				lstpairStatements = objAcctFuncInvoices.fn_getStatementTransactions(driver, webWait, strAccountNum, strAPIURL);
				Log.info("Statements and it's related Transaction(s) data has been captured. \n");
				webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Analytics and Reporting"))).click();
				blnGenericReportValidation = fn_ValidateReports(driver, webWait, strAccountNum, strAPIURL, lstpairStatements, "General_Validations");
	           	
				//Full Payments and then Validations...
	           	Thread.sleep(2500);
	           	objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
	           	strPayments = objAcctFuncInvoices.fn_GeneratePayments(driver, webWait, strAccountNum, strRefCode, true, "", true, "", strTestCaseName);
	           	arrPayments = strPayments.split("#");// get the Transaction ID & the amount for it.
	           	webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Analytics and Reporting"))).click();
	           	blnFullPayReportValidation = fn_ValidateReports(driver, webWait, strAccountNum, strAPIURL, lstpairStatements, "Full_Payment");

				//Void Payments and then Validations...	           	
	           	objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
	           	blnVoidPayReportValidation = objAcctFuncInvoices.fn_VoidPayments(driver, webWait, strAccountNum, arrPayments[0], strTestCaseName);
	           	webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Analytics and Reporting"))).click();
	           	if (blnVoidPayReportValidation == true)
	           		blnVoidPayReportValidation = fn_ValidateReports(driver, webWait, strAccountNum, strAPIURL, lstpairStatements, "Void_Payment");
	           	
				//Partial Payments and then Validations...
	           	//TODO: Take such a Transaction & Amount which is created for the currently generated Invoice.
	    		for(List<NameValuePair> lstpairTxnStmt : lstpairStatements){
	    			
	    			for(NameValuePair nvpTxnStatements : lstpairTxnStmt){
	    				
	    				if(nvpTxnStatements.getName().equals("Statement #")){
	    					strStatementNum = nvpTxnStatements.getValue().toString();
	    				}
	    				else if(nvpTxnStatements.getName().equals("Transaction #")){
	    					strTxnNumber = nvpTxnStatements.getValue().toString();
	    				}
	    				else if(nvpTxnStatements.getName().equals("Amount")){
	    					strAmmount = nvpTxnStatements.getValue().toString();
	    				}
	    				else if(nvpTxnStatements.getName().equals("Transaction Description")){
	    					strTxnDescription = nvpTxnStatements.getValue().toString();
	    				}	    					    				
	    			}
	    			if(strTxnDescription.contains(strInvoiceNumber))
	    				break;
	    		}
	           	strRefCode = strRefCode + rnRefCode.nextInt(99);	           	
	           	Log.info("Partial Payment will be done for the Transaction # "+strTxnNumber+" of Statement # "+strStatementNum+" and amount: '"+strAmmount+"'.");
	           	objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
	           	strPayments = objAcctFuncInvoices.fn_GeneratePayments(driver, webWait, strAccountNum, strRefCode, false, strAmmount, true, "", strTestCaseName);
	           	arrPayments = strPayments.split("#");// get the Transaction ID & the amount for it.
	           	webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Analytics and Reporting"))).click();
	           	blnPartialPayReportValidation = fn_ValidateReports(driver, webWait, strAccountNum, strAPIURL, lstpairStatements, "Partial_Payment");
	           	
	           	//Refunds for Partial Payments and then Validations...
	           	Log.info("Refunding Payment for the Partial Payment with Transaction # "+arrPayments[0]+" which was done recently...");
	           	objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
	           	blnRefundsValidation = objAcctFuncInvoices.fn_BookingRefunds(driver, webWait, strAccountNum, arrPayments[0], false, "500", "Refunds for Ageing Report", strTestCaseName);
	           	webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Analytics and Reporting"))).click();
	           	if(blnRefundsValidation == true)
	           		blnRefundsValidation = fn_ValidateReports(driver, webWait, strAccountNum, strAPIURL, lstpairStatements, "Refunds_Partial_Payment");
	           	
	           	//TODO: Get the Full URL with the authentication details for current instance & make a POST call for voiding Partial Payment. 
	            String strFullUrl = objAccountFunc.fn_GetFullUrl(driver, webWait, strAPIURL, "void_transaction");
	            String strReceiptID = "6666"+rnRefCode.nextInt(999);
	            strFullUrl = strFullUrl + "&account_no="+strAccountNum + "&transaction_id="+arrPayments[0] + "&reason_code=7&comments=Test%20Void&client_receipt_id="+strReceiptID;
	            objAPIHandler.makeSimplePostCall(strFullUrl);
	            Log.info("Voiding of the Refunds has been done successfully...");
	           		           	
	           	if(!blnGenericReportValidation){
	    			
	    			Log.error("ERROR: Validation for the AR Ageing Reports failed for basic validations !");
	    			blnValidateARAging = false;
	    		}
	    		if(!blnFullPayReportValidation){
	    			
	    			Log.error("ERROR: Validation for the AR Ageing Reports failed after Full Payments !");
	    			blnValidateARAging = false;
	    		}
	    		if(!blnVoidPayReportValidation){
	    			
	    			Log.error("ERROR: Validation for the AR Ageing Reports failed after Voiding Payments !");
	    			blnValidateARAging = false;
	    		}
	    		if(!blnPartialPayReportValidation){
	    			
	    			Log.error("ERROR: Validation for the AR Ageing Reports failed after Partial Payments !");
	    			blnValidateARAging = false;
	    		}
	    		if(!blnRefundsValidation){
	    			
	    			Log.error("ERROR: Validation for the AR Ageing Reports failed after Refunds done for Partial Payments !");
	    			blnValidateARAging = false;
	    		}	    		
			}
		}
    	catch (Exception exception){
    		
    		Utils.takeScreenshot(driver, strTestCaseName);
    		Log.error("ERROR: Validation for the AR Ageing Reports couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Validation for the AR Ageing Reports couldn't be done due to exception:  "+exception.toString());
    		throw exception;
    	}
    	catch (AssertionError exception){
    		
    		Utils.takeScreenshot(driver, strTestCaseName);
    		Log.error("ERROR: Validation for the AR Ageing Reports couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Validation for the AR Ageing Reports couldn't be done due to exception:  "+exception.toString());
    		throw exception;
    	}		
		
		if(!blnValidateARAging)
			assertTrue(false, "Validation of AR Ageing Reports failed due to earlier reported failures...");
		
		//TODO: Log the end of the test case. 
		Log.endTestCase(strTestCaseName);			
	}

	
/*    
    Function fn_ValidateReports: This is collection of calls to Validate Reports; was designed to enable Batch Testing.
    
 	Outputs: Boolean value for the various reports validated.
*/	
	public Boolean fn_ValidateReports(WebDriver driver, WebDriverWait webWait, String strAccountNum, String strAPIURL,
			List<List<NameValuePair>> lstpairStatements, String strExecutionType) throws Exception{
		
		//TODO: Create object for the classes to be used for accessing reusable codes.
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
		AccountFunctions objAccountFunc = new AccountFunctions();
		ReportUtils objReportUtils = new ReportUtils();
		
		Boolean blnAgeingRptStatement = false;
		Boolean blnAgeingByAcctName = false;
		Boolean blnAgeingByAccount = false;
		Boolean blnReportsValidated = false;
		
		String strPaymentForm = null;
		String strDaysPastDue = null;
		List<NameValuePair> lstpairAccountInfo = new ArrayList<NameValuePair>();		
		
		String strParentWindow = null;
		
		//TODO: Search the account and then get it's Form of Payment Value, All Account Details.
		objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
		webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Reset Password")));
		strPaymentForm = driver.findElement(By.xpath("//a[text()='Form of Payment']/following::td[1]")).getText();
		Log.info("Data for Payment Information has been collected; Form of Payment is: "+strPaymentForm);
		strDaysPastDue = driver.findElement(By.xpath("//td[text()='Days Past Due']/following-sibling::td")).getText();
		Log.info("Data for Days Past Due has been collected; it is: "+strDaysPastDue);
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Analytics and Reporting"))).click();
		
		//always have to take billing information.
		lstpairAccountInfo = objAccountFunc.fn_getAccountDetails(driver, webWait, strAccountNum, false);
		Log.info("Biller's Information for Account "+strAccountNum+" has been captured.");
		
		//TODO: Validation for Aging Report with Statement - SGAS.
		strParentWindow = driver.getWindowHandle();
		Log.info("\n Validation for the Aging Report with Statement - SGAS for "+strExecutionType+" will commence...");
		blnAgeingRptStatement = fn_ValidateAgeingRptStatementSGAS(driver, strAccountNum, strPaymentForm, lstpairAccountInfo, lstpairStatements, strExecutionType);
		Utils.takeScreenshot(driver, strTestCaseName);
		objReportUtils.fn_HandleReportWindows(driver, strParentWindow);
		if(blnAgeingRptStatement == true || 
			(blnAgeingRptStatement == false && (strExecutionType.contains("Full") || strExecutionType.contains("Partial") || strExecutionType.contains("Refund")))){				

			Log.info("Validation for Aging Report with Statement - SGAS has passed !");
			blnAgeingRptStatement = true;
		}

		//TODO: Validation for Aging Account with Name.
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reports"))).click();// Click Reports link
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Analytics and Reporting"))).click();
		strParentWindow = driver.getWindowHandle();
		Log.info("\n Validation for the Aging Account with Name for "+strExecutionType+" will commence...");
		blnAgeingByAcctName = fn_ValidateAgeingByAccountName(driver, strAccountNum, strPaymentForm, lstpairAccountInfo, lstpairStatements, strExecutionType);
		Utils.takeScreenshot(driver, strTestCaseName);
		objReportUtils.fn_HandleReportWindows(driver, strParentWindow);
		if((blnAgeingByAcctName == true) || 
				(blnAgeingByAcctName == false && (strExecutionType.contains("Full") || strExecutionType.contains("Partial") || strExecutionType.contains("Refund")))){
		
			Log.info("Validation for Aging Account with Name has passed !");
			blnAgeingByAcctName = true;
		}			

		//TODO: Validation for Aging Account.
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reports"))).click();// Click Reports link
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Analytics and Reporting"))).click();
		strParentWindow = driver.getWindowHandle();
		Log.info("\n Validation for the Aging Account for "+strExecutionType+" will commence...");		
		blnAgeingByAccount = fn_ValidateAgeingByAccount(driver, strAccountNum, strPaymentForm, lstpairStatements, strExecutionType);
		Utils.takeScreenshot(driver, strTestCaseName);
		objReportUtils.fn_HandleReportWindows(driver, strParentWindow);
		if(blnAgeingByAccount == true || (strDaysPastDue.contentEquals("0") && blnAgeingByAccount == false) ||
				(blnAgeingByAccount == false && (strExecutionType.contains("Full") || strExecutionType.contains("Partial") || strExecutionType.contains("Refund")))){				
			
			Log.info("Validation for Aging Account has passed !");
			blnAgeingByAccount = true;
		}		
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reports"))).click();// Click Reports link
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Analytics and Reporting"))).click();
		
		//TODO: Set the status of the function execution
		if(blnAgeingByAcctName == false || blnAgeingByAccount == false || blnAgeingRptStatement == false)
			blnReportsValidated = false;
		else
			blnReportsValidated = true;
		
		return blnReportsValidated;
	}

	
/*    
    Function fn_ValidateAgeingRptStatementSGAS: This method validates the Aging Report with Statement - SGAS Report.
    
 	Outputs: Boolean value for the Report validated.
*/		
	public Boolean fn_ValidateAgeingRptStatementSGAS(WebDriver driver, String strAccountNum, String strPaymentForm, 
			List<NameValuePair> lstAccountInfo, List<List<NameValuePair>> lstTxnStatements, String strExecutionType) throws Exception{
		
    	//TODO: Create object for the classes to be used for accessing reusable codes.
		ReportUtils objReportUtils = new ReportUtils();
		
		Boolean blnAgeingRptStatement = false;
		Boolean blnValidatePastDays = false;
		
		Long lngPastDueDateDays = null; 
		String strCurrentDate = Utils.getDateYearFirst();	//returns current date in "yyyy-MM-dd".
		String strCustomerName = null;
		String strCompanyName = null;
		String strStatementNum = null;
		String strDueDate = null;
		String strDueDateReportForm = null;
		String strTxnDate = null;
		String strTxnNumber = null;
		String strAmmount = null;
		
		//TODO: Generate the Report and then proceed further for Validation.
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Analytics and Reporting"))).click();
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reports"))).click();// Click Reports link
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Run Reports"))).click();// Click Reports link
		Thread.sleep(3500);
		objReportUtils.fn_clickSideBarElement(driver, By.linkText("SGAS"));//Click folder link
		Thread.sleep(2000);
		objReportUtils.fn_clickSideBarElement(driver, By.linkText("Aging Report with Statement - SGAS"));//Click Report link		
		Thread.sleep(2000);		
		objReportUtils.fn_clickReportElement(driver, By.cssSelector("body > table:nth-child(7) > tbody > tr > td:nth-child(2) > a"));//Switch to Report Preview Mode
		Thread.sleep(2000);
		objReportUtils.fn_SetValueReportElement(driver, By.xpath("//td[text()='Acct No:']/following-sibling::td/input[@type='text']"), strAccountNum);//Select Start Date as "Current Day"
		Thread.sleep(1000);
		objReportUtils.fn_clickReportElement(driver, By.xpath("//input[@value='Preview Report!']"));//click on Preview Report button
		Thread.sleep(10000);
		
		//TODO: read the details of the account information to be used for Validation.
		for(NameValuePair nvpAccountInfo : lstAccountInfo){
			
			if(nvpAccountInfo.getName().equals("First Name:")){
				strCustomerName = nvpAccountInfo.getValue().toString();
			}
			else if(nvpAccountInfo.getName().equals("Last Name:")){
				strCustomerName = strCustomerName+" "+nvpAccountInfo.getValue().toString();
			}
			else if(nvpAccountInfo.getName().equals("Company Name:")){
				strCompanyName = nvpAccountInfo.getValue().toString();
			}
		}
		
		//TODO: For the mentioned Account, Validate the entries in the report.
		for(List<NameValuePair> lstpairTxnStmt : lstTxnStatements){
			
			for(NameValuePair nvpTxnStatements : lstpairTxnStmt){
				
				if(nvpTxnStatements.getName().equals("Statement #")){
					strStatementNum = nvpTxnStatements.getValue().toString();
				}
				else if(nvpTxnStatements.getName().equals("Due Date")){
					strDueDate = nvpTxnStatements.getValue().toString();
					strDueDateReportForm = objReportUtils.fn_GetReportDateFormat(strDueDate, "mm-dd-yyyy");
				}
				else if(nvpTxnStatements.getName().equals("Transaction Date")){
					strTxnDate = nvpTxnStatements.getValue().toString();
				}
				else if(nvpTxnStatements.getName().equals("Transaction #")){
					strTxnNumber = nvpTxnStatements.getValue().toString();
				}
				else if(nvpTxnStatements.getName().equals("Amount")){
					strAmmount = nvpTxnStatements.getValue().toString();
				}
			}
			
			WebElement webReadContents = objReportUtils.fn_ReadGeneratedReport(driver);
			List<WebElement> lstwebReadContentsRow = webReadContents.findElements(By.tagName("tr"));
			List<WebElement> lstwebReadContentsRowSL = lstwebReadContentsRow.subList(2, lstwebReadContentsRow.size());
			Integer intForReport = 0;	//iterator for the report validation to flag failure.
			for(WebElement rows : lstwebReadContentsRowSL){
				
				List<WebElement> cols = rows.findElements(By.tagName("td"));
				if(cols.size() >= 2){
					
					intForReport ++;
					//TODO: Read the contents of the row in the Report and proceed for validation.
					String strReportAcctNum = cols.get(0).getAttribute("innerText").toString().trim();
					String strReportCustName = cols.get(1).getAttribute("innerText").toString().trim();
					String strReportCompName = cols.get(2).getAttribute("innerText").toString().trim();
					String strReportPayMethod = cols.get(3).getAttribute("innerText").toString().trim();
					String strReportTxnNum = cols.get(4).getAttribute("innerText").toString().trim();
					String strReportTxnDate = cols.get(5).getAttribute("innerText").toString().trim();
					String strReportDueDate = cols.get(6).getAttribute("innerText").toString().trim();
					String strReportStmtNum = cols.get(7).getAttribute("innerText").toString().trim();
					String strReportAmount = cols.get(9).getAttribute("innerText").toString().trim();
					String strReportCurrent = cols.get(10).getAttribute("innerText").toString().trim();
					String strReportDaysValue = null;
					
					SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd");
					Date dtCurrentDate = objSDF.parse(strCurrentDate);
					Date dtTxnDate = objSDF.parse(strDueDate);
					
					if(strReportCurrent.contentEquals("0.00")){						
						
						//calculate the Past Due Dates for the generated statement to chose the data column to validate value of.
						lngPastDueDateDays = Math.round((dtCurrentDate.getTime() - dtTxnDate.getTime()) / (double) 86400000);
						blnValidatePastDays = true;
						if(lngPastDueDateDays >= 0 && lngPastDueDateDays <= 30){
							
							strReportDaysValue = cols.get(11).getAttribute("innerText").toString().trim();
						}
						else if(lngPastDueDateDays >= 31 && lngPastDueDateDays <= 60){
							
							strReportDaysValue = cols.get(12).getAttribute("innerText").toString().trim();
						}
						else if(lngPastDueDateDays >= 61 && lngPastDueDateDays <= 90){
							
							strReportDaysValue = cols.get(13).getAttribute("innerText").toString().trim();
						}
						else if(lngPastDueDateDays >= 91 && lngPastDueDateDays <= 120){
							
							strReportDaysValue = cols.get(14).getAttribute("innerText").toString().trim();
						}
						else if(lngPastDueDateDays >= 121){
							
							strReportDaysValue = cols.get(15).getAttribute("innerText").toString().trim();
						}

					}
					else{
						
						blnValidatePastDays = false;
					}					
					
					//TODOD: Actual Validations for the entries on the Report.
					if(strAccountNum.contentEquals(strReportAcctNum) && strPaymentForm.contentEquals(strReportPayMethod))
						if(strCustomerName.contentEquals(strReportCustName) && strCompanyName.contentEquals(strReportCompName))
							if(strTxnNumber.contentEquals(strReportTxnNum) && strStatementNum.contentEquals(strReportStmtNum))
								if(strAmmount.contentEquals(strReportAmount) && strTxnDate.contentEquals(strReportTxnDate))
									if(strDueDateReportForm.contentEquals(strReportDueDate) && blnValidatePastDays == true){
										
										if(strAmmount.contentEquals(strReportDaysValue)){
																						
											Log.info("The Aging Report with Statement - SGAS has been validated for Account "+strReportAcctNum
													+" with Transaction # as '"+strReportTxnNum+"' of Statement # "+strReportStmtNum
													+" with Amount '"+strReportAmount+"' and Past Due date by '"+lngPastDueDateDays+"' !");
											
											blnAgeingRptStatement = true;
											blnValidatePastDays = false;
											break;
										}
									}
									else{									
										
										Log.info("The Aging Report with Statement - SGAS has been validated for Account "+strReportAcctNum
												+" with Transaction # as '"+strReportTxnNum+"' of Statement # "+strReportStmtNum
												+" with Amount '"+strReportAmount+"' !");
										
										blnAgeingRptStatement = true;
										break;										
									}
				}
				}
			
				if(intForReport >= lstwebReadContentsRowSL.size()-6 && blnAgeingRptStatement == false){
					
					Log.info("ERROR: The Aging Report with Statement - SGAS couldn't be validated for Account "+strAccountNum
							+" with Transaction # as '"+strTxnNumber+"' of Statement # "+strStatementNum
							+" with Amount '"+strAmmount+"' !");
					
					blnAgeingRptStatement = false;
				}		
		}
		
		return blnAgeingRptStatement;
	}

	
/*    
    Function fn_ValidateAgeingByAccountName: This method validates the Aging by Account w/Name Report.
    
 	Outputs: Boolean value for the Report validated.
*/
	public Boolean fn_ValidateAgeingByAccountName(WebDriver driver, String strAccountNum, String strPaymentForm, 
			List<NameValuePair> lstAccountInfo, List<List<NameValuePair>> lstTxnStatements, String strExecutionType) throws Exception{
		
    	//TODO: Create object for the classes to be used for accessing reusable codes.
		ReportUtils objReportUtils = new ReportUtils();
		CsvHandler objCSVHandler = new CsvHandler();
		
		Boolean blnAgeingByAcctName = false;
		Boolean blnValidatePastDays = false;
		
		Long lngPastDueDateDays = null; 
		String strCurrentDate = Utils.getDateYearFirst();	//returns current date in "yyyy-MM-dd".
		String strCustomerName = null;
		String strCompanyName = null;
		String strStatementNum = null;		
		String strDueDate = null;
		String strDueDateReportForm = null;
		String strAmmount = null;
		
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS") ;
		String strFileName = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
				+ System.getProperty("file.separator") +"AgingByAccountWithName_"+strExecutionType+"_"+dateFormat.format(date);
	    Log.info("The filename generated is "+strFileName);
	    String strCSVFileName = strFileName+".csv";
	    
		
		//TODO: Generate the Report and then proceed further for Validation.
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Analytics and Reporting"))).click();
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reports"))).click();// Click Reports link
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Run Reports"))).click();// Click Reports link
		Thread.sleep(3500);
		objReportUtils.fn_clickSideBarElement(driver, By.linkText("Standard"));// //Click folder link
		Thread.sleep(2000);
		objReportUtils.fn_clickSideBarElement(driver, By.linkText("Aging by Account w/Name"));//Click Report link		
		Thread.sleep(2000);		
		objReportUtils.fn_clickReportElement(driver, By.cssSelector("body > table:nth-child(7) > tbody > tr > td:nth-child(2) > a"));//Switch to Report Preview Mode
		Thread.sleep(2000);
		objReportUtils.fn_SetValueReportElement(driver, By.xpath("//td[text()='File Transfer Destination:']/following-sibling::td/select"), "-- No automated file transfer -- ");//Select Start Date as "Current Day"
		Thread.sleep(1000);
		objReportUtils.fn_clickReportElement(driver, By.xpath("//input[@value='Preview Report!']"));//click on Preview Report button
		Thread.sleep(10000);
		
		objReportUtils.fn_ReportsFileDownload(driver, strFileName);// save the CSV to the location specified.
		Thread.sleep(15000);
		List<List<NameValuePair>> lstReportRead = objCSVHandler.readCSV(strCSVFileName);
		
		//TODO: read the details of the account information to be used for Validation.
		for(NameValuePair nvpAccountInfo : lstAccountInfo){
			
			if(nvpAccountInfo.getName().equals("First Name:")){
				strCustomerName = nvpAccountInfo.getValue().toString();
			}
			else if(nvpAccountInfo.getName().equals("Last Name:")){
				strCustomerName = strCustomerName+" "+nvpAccountInfo.getValue().toString();
			}
			else if(nvpAccountInfo.getName().equals("Company Name:")){
				strCompanyName = nvpAccountInfo.getValue().toString();
			}
		}
		
		//TODO: For the mentioned Account, Validate the entries in the report.
		for(List<NameValuePair> lstpairTxnStmt : lstTxnStatements){
			
			for(NameValuePair nvpTxnStatements : lstpairTxnStmt){
				
				if(nvpTxnStatements.getName().equals("Statement #")){
					strStatementNum = nvpTxnStatements.getValue().toString();
				}
				else if(nvpTxnStatements.getName().equals("Due Date")){
					strDueDate = nvpTxnStatements.getValue().toString();
					strDueDateReportForm = objReportUtils.fn_GetReportDateFormat(strDueDate, "mm-dd-yyyy");
				}
				else if(nvpTxnStatements.getName().equals("Amount")){
					strAmmount = nvpTxnStatements.getValue().toString();
				}
			}			
			
			Integer intForReport = 0;	//iterator for the report validation to flag failure.
			for(List<NameValuePair> row : lstReportRead){
			
				//TODO: Read the contents of the row in the Report and proceed for validation.					
				String strReportAcctNum = null;
				String strReportCustName = null;
				String strReportCompName = null;
				String strReportPayMethod = null;
				String strReportDueDate = null;
				String strReportStmtNum = null;
				String strReportAmount = null;
				String strReportCurrent = "";
				String strReportDaysValue = null;
				
				for(NameValuePair nvpRowsData : row){
				
					if(nvpRowsData.getName().contains("Account"))
						strReportAcctNum = nvpRowsData.getValue();
					
					if(strReportAcctNum.contentEquals(strAccountNum)){
					
						if(nvpRowsData.getName().contains("Customer Name"))
							strReportCustName = nvpRowsData.getValue();						
						if(nvpRowsData.getName().contains("Company Name"))
							strReportCompName = nvpRowsData.getValue();					
						if(nvpRowsData.getName().contains("Pay Method"))
							strReportPayMethod = nvpRowsData.getValue();						
						if(nvpRowsData.getName().contains("Due Date"))
							strReportDueDate = nvpRowsData.getValue();
						if(nvpRowsData.getName().contains("Statement #"))
							strReportStmtNum = nvpRowsData.getValue();
						if(nvpRowsData.getName().contains("Amount"))
							strReportAmount = nvpRowsData.getValue();
						if(nvpRowsData.getName().contains("Current"))
							strReportCurrent = nvpRowsData.getValue();						
					
						if(!strReportCurrent.contentEquals(""))
							if(strReportCurrent.contentEquals("0.00")){
							
								//calculate the Past Due Dates for the generated statement.
								SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd");
								Date dtCurrentDate = objSDF.parse(strCurrentDate);
								Date dtTxnDate = objSDF.parse(strDueDate);
								
								lngPastDueDateDays = Math.round((dtCurrentDate.getTime() - dtTxnDate.getTime()) / (double) 86400000);
								blnValidatePastDays = true;
								if(lngPastDueDateDays >= 0 && lngPastDueDateDays <= 30){
									
									if(nvpRowsData.getName().contains("0-30 Days"))
										strReportDaysValue = nvpRowsData.getValue();
								}
								else if(lngPastDueDateDays >= 31 && lngPastDueDateDays <= 60){
							
									if(nvpRowsData.getName().contains("31-60 Days"))
										strReportDaysValue = nvpRowsData.getValue();
								}
								else if(lngPastDueDateDays >= 61 && lngPastDueDateDays <= 90){
							
									if(nvpRowsData.getName().contains("61-90 Days"))
										strReportDaysValue = nvpRowsData.getValue();
								}
								else if(lngPastDueDateDays >= 91){
							
									if(nvpRowsData.getName().contains("91+ Days"))
										strReportDaysValue = nvpRowsData.getValue();
								}						
							}
							else
								blnValidatePastDays = false;						
					}
				}
				
				intForReport ++;
				//TODOD: Actual Validations for the entries on the Report.
				if(strAccountNum.contentEquals(strReportAcctNum) && strPaymentForm.contentEquals(strReportPayMethod))
					if(strCustomerName.contentEquals(strReportCustName) && strCompanyName.contentEquals(strReportCompName))
						if(strStatementNum.contentEquals(strReportStmtNum) && strDueDateReportForm.contentEquals(strReportDueDate))
							if(strAmmount.contentEquals(strReportAmount) && blnValidatePastDays == true){
								
								if(strAmmount.contentEquals(strReportDaysValue)){
									Log.info("The Aging by Account w/name Report has been validated for Account "+strReportAcctNum
											+" with Statement # "+strReportStmtNum+" with Amount '"+strReportAmount
											+"' and Past Due date by '"+lngPastDueDateDays+"' !");
									
									blnAgeingByAcctName = true;
									blnValidatePastDays = false;
									break;
								}
								else{
									
									Log.info("ERROR: The Aging by Account w/name Report failed validation for Account "+strReportAcctNum
											+" with Statement # "+strReportStmtNum+" with Amount '"+strReportAmount
											+"' and Past Due date by '"+lngPastDueDateDays+"' !");
									
									blnAgeingByAcctName = true;
									blnValidatePastDays = false;
									break;
								}
							}
							else{
								
								Log.info("The Aging by Account w/name Report has been validated for Account "+strReportAcctNum
										+" with Statement # "+strReportStmtNum+" with Amount '"+strReportAmount+"' !");
								
								blnAgeingByAcctName = true;
								break;
							}					
			}
			
			if(intForReport >= lstReportRead.size() && blnAgeingByAcctName == false){
				
				Log.info("ERROR: The Aging by Account w/name Report couldn't be validated for Account "+strAccountNum
						+" with Statement # "+strStatementNum+" with Amount '"+strAmmount+"' !");
				
				blnAgeingByAcctName = false;
			}
		}		
		
		return blnAgeingByAcctName;
	}	

	
/*    
    Function fn_ValidateAgeingByAccount: This method validates the Aging by Account Report.
    
 	Outputs: Boolean value for the Report validated.
*/	
	public Boolean fn_ValidateAgeingByAccount(WebDriver driver, String strAccountNum, String strPaymentForm, List<List<NameValuePair>> lstTxnStatements, String strExecutionType) throws Exception{
		
    	//TODO: Create object for the classes to be used for accessing reusable codes.
		ReportUtils objReportUtils = new ReportUtils();
		CsvHandler objCSVHandler = new CsvHandler();
		Utils objUtils = new Utils();
		
		Boolean blnAgeingByAccount = false;
		Boolean blnValidatePastDays = false;
		
		Long lngPastDueDateDays = null; 
		String strCurrentDate = Utils.getDateYearFirst();	//returns current date in "yyyy-MM-dd".
		String strStatementNum = null;
		String strDueDate = null;
		String strDueDateReportForm = null;
		String strAmmount = null;
		
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS") ;
		String strFileName = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
				+ System.getProperty("file.separator") +"AgingByAccount_"+strExecutionType+"_"+dateFormat.format(date);
	    Log.info("The filename generated is "+strFileName);
	    String strCSVFileName = strFileName+".csv";
		
		//TODO: Generate the Report and then proceed further for Validation.
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Analytics and Reporting"))).click();
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reports"))).click();// Click Reports link
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Run Reports"))).click();// Click Reports link
		Thread.sleep(3500);
		objReportUtils.fn_clickSideBarElement(driver, By.linkText("Standard"));// //Click folder link
		Thread.sleep(2000);
		objReportUtils.fn_clickSideBarElement(driver, By.linkText("Aging by Account"));//Click Report link		
		Thread.sleep(2000);		
		objReportUtils.fn_clickReportElement(driver, By.cssSelector("body > table:nth-child(7) > tbody > tr > td:nth-child(2) > a"));//Switch to Report Preview Mode
		Thread.sleep(2000);
		objReportUtils.fn_SetValueReportElement(driver, By.xpath("//td[text()='File Transfer Destination:']/following-sibling::td/select"), "-- No automated file transfer -- ");//Select Start Date as "Current Day"
		Thread.sleep(1000);
		objReportUtils.fn_clickReportElement(driver, By.xpath("//input[@value='Preview Report!']"));//click on Preview Report button
		Thread.sleep(10000);
		
		objReportUtils.fn_ReportsFileDownload(driver, strFileName);// save the CSV to the location specified.
		Thread.sleep(15000);
		List<List<NameValuePair>> lstReportRead = objCSVHandler.readCSV(strCSVFileName);
		
		//TODO: For the mentioned Account, Validate the entries in the report.
		for(List<NameValuePair> lstpairTxnStmt : lstTxnStatements){
			
			for(NameValuePair nvpTxnStatements : lstpairTxnStmt){
				
				if(nvpTxnStatements.getName().equals("Statement #")){
					strStatementNum = nvpTxnStatements.getValue().toString();
				}
				else if(nvpTxnStatements.getName().equals("Due Date")){
					strDueDate = nvpTxnStatements.getValue().toString();
					strDueDateReportForm = objReportUtils.fn_GetReportDateFormat(strDueDate, "mm-dd-yyyy");
				}
				else if(nvpTxnStatements.getName().equals("Amount")){
					strAmmount = nvpTxnStatements.getValue().toString();
				}
			}			
			
			Integer intForReport = 0;	//iterator for the report validation to flag failure.
			for(List<NameValuePair> row : lstReportRead){
			
				//TODO: Read the contents of the row in the Report and proceed for validation.					
				String strReportAcctNum = null;
				String strReportPayMethod = null;
				String strReportDueDate = null;
				String strReportStmtNum = null;
				String strReportAmount = null;
				String strReportCurrent = "";
				String strReportDaysValue = null;
				
				for(NameValuePair nvpRowsData : row){
				
					if(nvpRowsData.getName().contains("Account"))
						strReportAcctNum = nvpRowsData.getValue();
					
					if(strReportAcctNum.contentEquals(strAccountNum)){

						if(nvpRowsData.getName().contains("Pay Method"))
							strReportPayMethod = nvpRowsData.getValue();						
						if(nvpRowsData.getName().contains("Due Date")){
							
							strReportDueDate = nvpRowsData.getValue();
							strReportDueDate = objUtils.fn_getDateFormatted(strReportDueDate, "mm-dd-yyyy");
						}
							
						if(nvpRowsData.getName().contains("Statement #"))
							strReportStmtNum = nvpRowsData.getValue();
						if(nvpRowsData.getName().contains("Amount"))
							strReportAmount = nvpRowsData.getValue();
						if(nvpRowsData.getName().contains("Current"))
							strReportCurrent = nvpRowsData.getValue();
					
						if(!strReportCurrent.contentEquals(""))
							if(strReportCurrent.contentEquals("0.00")){
							
								//calculate the Past Due Dates for the generated statement.
								SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd");
								Date dtCurrentDate = objSDF.parse(strCurrentDate);
								Date dtTxnDate = objSDF.parse(strDueDate);
								
								lngPastDueDateDays = Math.round((dtCurrentDate.getTime() - dtTxnDate.getTime()) / (double) 86400000);
								blnValidatePastDays = true;
								if(lngPastDueDateDays >= 0 && lngPastDueDateDays <= 30){
									
									if(nvpRowsData.getName().contains("0-30 Days"))
										strReportDaysValue = nvpRowsData.getValue();
								}
								else if(lngPastDueDateDays >= 31 && lngPastDueDateDays <= 60){
							
									if(nvpRowsData.getName().contains("31-60 Days"))
										strReportDaysValue = nvpRowsData.getValue();
								}
								else if(lngPastDueDateDays >= 61 && lngPastDueDateDays <= 90){
							
									if(nvpRowsData.getName().contains("61-90 Days"))
										strReportDaysValue = nvpRowsData.getValue();
								}
								else if(lngPastDueDateDays >= 91){
							
									if(nvpRowsData.getName().contains("91+ Days"))
										strReportDaysValue = nvpRowsData.getValue();
								}						
							}
							else
								blnValidatePastDays = false;						
					}
				}
				
				intForReport ++;
				//TODOD: Actual Validations for the entries on the Report.
				if(strAccountNum.contentEquals(strReportAcctNum) && strPaymentForm.contentEquals(strReportPayMethod))
					if(strStatementNum.contentEquals(strReportStmtNum) && strDueDateReportForm.contentEquals(strReportDueDate))
						if(strAmmount.contentEquals(strReportAmount) && blnValidatePastDays == true){
							
							if(strAmmount.contentEquals(strReportDaysValue)){
								
								Log.info("The Aging by Account Report has been validated for Account "+strReportAcctNum
										+" with Statement # "+strReportStmtNum+" with Amount '"+strReportAmount
										+"' and Past Due date by '"+lngPastDueDateDays+"' !");
								
								blnAgeingByAccount = true;
								blnValidatePastDays = false;
								break;
							}
							else{
								
								Log.info("ERROR: The Aging by Account Report failed validation for Account "+strReportAcctNum
										+" with Statement # "+strReportStmtNum+" with Amount '"+strReportAmount
										+"' and Past Due date by '"+lngPastDueDateDays+"' !");
								
								blnAgeingByAccount = true;
								blnValidatePastDays = false;
								break;
							}
						}
						else{
							
							Log.info("The Aging by Account Report has been validated for Account "+strReportAcctNum
									+" with Statement # "+strReportStmtNum+" with Amount '"+strReportAmount+"' !");
							
							blnAgeingByAccount = true;
							break;
						}					
			}
			
			if(intForReport >= lstReportRead.size() && blnAgeingByAccount == false){
				
				Log.info("ERROR: The Aging by Account Report couldn't be validated for Account "+strAccountNum
						+" with Statement # "+strStatementNum+" with Amount '"+strAmmount+"' !");
				
				blnAgeingByAccount = false;
			}
		}		
		
		return blnAgeingByAccount;
	}

	
	//TODO: Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "ARAgeingReports");
	}
	
	
	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
}