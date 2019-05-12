/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_ValidatePaymentDetails 
 Purpose     		: 	Purpose of this file is :
						1. To validate multiple reports for Payment Details.
						2. This is automation of TC: QAABE-344.

 Date       		:	11/23/2015
 Version Information:	Version 1.0
 
 PreCondition 		:	1. Role based Login required. 
 						2. Data to be populated in the "TestCaseDetails" & "PaymentDetails" worksheet 
 						for excel "TestData.xls".
 						3. There should be Balance (already unpaid invoices) for the Parent & Child Accounts; to
 						facilitate the Payments generation, else TC will exit with failure.

 Test Steps 		:	1. Login using valid role credentials for viewing Reports and generating Payments.
 						2. Search the customer account to view it's Parent-Child Relationships.
 						3. If the Child is Parent Pay, then execute first part of test case; generate payments ONLY for Parent Account.
 						4. If the Child is Self-Pay, then execute second part of test case; generate payments ONLY for Child Account. 
 						5. Navigate to the reports "Cash Receipt Register - SGAS" & "Payment Transactions by Date".
 						6. Verify if the Payment details are reported correctly. 						
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.Reports;

import java.util.List;
import java.util.Random;
import atu.ddf.exceptions.DataDrivenFrameworkException;

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

import utility.ApiHandler;
import utility.EnvironmentDetails;
import utility.Log;
import utility.ReportUtils;
import utility.Utils;
import utility.VerificationMethods;
import pageObjects.PaymentDetailsFinancialReport;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Invoices;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;


public class Cls_ValidatePaymentDetails extends VerificationMethods{
	
    public static WebDriver driver;
    public static String strTestCaseName = "Verify Payment Details in Reports";
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
    Function fn_ValidateDetails: This is the actual Test NG test for Verifying the Reports for AR Details.
*/
	@Test(dataProvider = "objectTestData", description = "Verify Payment Details in Reports")
    public void fn_ValidateDetails(String strAccountNum, String	strRefCode, String strOtherPaymentAmount, String strAPIInstanceURL) throws Exception{
		
		//TODO: Create object for the classes to be used for accessing reusable codes.
		AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
		AccountFunctions_Invoices objAcctFuncInvoices = new AccountFunctions_Invoices();
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
		PaymentDetailsFinancialReport objPayDetails = new PaymentDetailsFinancialReport();
		AccountFunctions objAccountFuncs = new AccountFunctions();
		ApiHandler objAPIHandler = new ApiHandler(); 
		
		String strAccountNumber = null;
		String strReturnedAccountNum = null;
		String[] arrAccountNumbers = null;
		String strPaymentsDetailsOther = null;
		String[] arrPaymentsDetailsOther = null;
		String strPaymentsDetailsFull = null;
		String[] arrPaymentsDetailsFull = null;
		String strCoupledPaymentDetails = null;
		String[] arrCoupledPaymentDetails = null;
		String strReferenceCodes = null;
		String[] arrReferenceCodes = null;
		Boolean blnParentPay = false;
		Boolean blnStandardSelfPay = false;
		Boolean blnValidatedCashAplctnDate = false;
		Boolean blnValidatedPaymentTxnDate = false;
		Random rnRefCode = new Random();
		
		//TODO: Log the beginning of the test case.
    	Log.startTestCase(strTestCaseName);
    	
    	try{
    		//TODO: Verify if the given account number is a Child Parent Related Account and get Account Types.
    		String strParentChildAccounts = objAcctFuncPlans.fn_VerifyParentChildAccounts(driver, webWait, strAccountNum, strTestCaseName);
    		if(strParentChildAccounts == null)
    			assertTrue(false, "ERROR: The given account number has no Parent Child Relation, so TC fails !");
    		else
        		arrAccountNumbers = strParentChildAccounts.split("#");
    		
    		
    		/******	Generate Payments for Parent Pay Responsibility (Parent Account) & Verify Reports ******/
    		//TODO: Search for the Payment Responsibility "Parent Pay" and decide whether the Validation can be done.
    		strReturnedAccountNum = objAcctFuncPlans.fn_GetAccountPaymentResponsibility(driver, webWait, strParentChildAccounts, "Parent Pay", strTestCaseName);
    		if(strReturnedAccountNum == null){
    			blnParentPay = false;
    			Log.error("There are no Child Accounts with 'Parent Pay' Payments Responsibility for Parent Account #: "+arrAccountNumbers[0]);
    			Reporter.log("There are no Child Accounts with 'Parent Pay' Payments Responsibility for Parent Account #: "+arrAccountNumbers[0]);
    		}
    		else
    			blnParentPay = true;    		
    		
    		if(blnParentPay == true){
    			//TODO: Generate Payments (Partial & then Full) for Parent and validate the reports.
    			strAccountNumber = arrAccountNumbers[0];	// Parent account number to be used.
    			objClientDefAct.AccountSearch(driver, webWait, strAccountNumber);
    			webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reset Password")));
    			String strFullBalance = objPayDetails.fn_getFullBalance(driver, webWait).getAttribute("innerText").toString().trim();
               	if(strFullBalance.contains("0.00"))
               		assertTrue(false, "ERROR: Since there is no Balance Ammount for this Account, Payments can't be generated. Hence exiting with error.");
    			
               	//generating partial payment using Other Amount option.
               	Log.info("Generating Partial Payment...");
    			strPaymentsDetailsOther = objAcctFuncInvoices.fn_GeneratePayments(driver, webWait, strAccountNumber, strRefCode, false, strOtherPaymentAmount, true, "", strTestCaseName);
    			arrPaymentsDetailsOther = strPaymentsDetailsOther.split("#") ;
    			
    			//generating Full payment using Full Amount option.
    			Thread.sleep(2000);
    			Log.info("Generating Full Payment...");
    			String strRefCodeFull = strRefCode + rnRefCode.nextInt(9) ;	// generating a random number such that RefCode can be reused.
    			strPaymentsDetailsFull = objAcctFuncInvoices.fn_GeneratePayments(driver, webWait, strAccountNumber, strRefCodeFull, true, "", true, "", strTestCaseName);
    			arrPaymentsDetailsFull = strPaymentsDetailsFull.split("#");
    			
    			//TODO: Verify the Cash Receipt Register - SGAS Report for details.
    			Thread.sleep(2000);
    			strCoupledPaymentDetails = arrPaymentsDetailsOther[1].toString()+"#"+arrPaymentsDetailsFull[1].toString();
    			arrCoupledPaymentDetails = strCoupledPaymentDetails.split("#");    			
    			strReferenceCodes = strRefCode +"#"+ strRefCodeFull;
    			arrReferenceCodes = strReferenceCodes.split("#");    			
    			blnValidatedCashAplctnDate = fn_VerifyCashApplicationDate(driver, webWait, strAccountNumber, arrCoupledPaymentDetails, arrReferenceCodes, strTestCaseName);    			
    			
    			//TODO: Verify the Payment Transactions by Date Report for details.
    			Thread.sleep(2000);
    			strCoupledPaymentDetails = strPaymentsDetailsOther +"#"+ strPaymentsDetailsFull;
    			arrCoupledPaymentDetails = strCoupledPaymentDetails.split("#");
    			blnValidatedPaymentTxnDate = fn_VerifyPaymentTxnDate(driver, webWait, strAccountNumber, arrCoupledPaymentDetails, strTestCaseName);
    		}
    		
    		
    		/******	Generate Payments for Standard Self Pay Payment Responsibility (Child Account) & Verify Reports ******/
    		if(blnParentPay == false){
	    		//TODO: Search for the Payment Responsibility "Standard Self Pay" and decide whether the Validation can be done.
	    		strReturnedAccountNum = objAcctFuncPlans.fn_GetAccountPaymentResponsibility(driver, webWait, strParentChildAccounts, "Standard Self Pay", strTestCaseName);
	    		if((strReturnedAccountNum == null) || (strReturnedAccountNum.contentEquals(arrAccountNumbers[0]))){
	    			blnStandardSelfPay = false;
	    			Log.error("There are no Child Accounts with 'Standard Self Pay' Payments Responsibility for Parent Account #: "+arrAccountNumbers[0]);
	    			Reporter.log("There are no Child Accounts with 'Standard Self Pay' Payments Responsibility for Parent Account #: "+arrAccountNumbers[0]);
	    		}
	    		else
	    			blnStandardSelfPay = true;
	    		
	    		if(blnStandardSelfPay == true){
	    			
	    			//TODO: Generate Payments (Partial & then Full) for Parent and validate the reports.
	    			strAccountNumber = strReturnedAccountNum;	// the child account number returned is to be used here.
	    			objClientDefAct.AccountSearch(driver, webWait, strAccountNumber);
	    			webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reset Password")));
	    			String strFullBalance = objPayDetails.fn_getFullBalance(driver, webWait).getAttribute("innerText").toString().trim();
	               	if(strFullBalance.contains("0.00"))
	               		assertTrue(false, "ERROR: Since there is no Balance Ammount for this Account, Payments can't be generated. Hence exiting with error.");
	    			
	               	//generating partial payment using Other Amount option.
	               	Log.info("Generating Partial Payment...");
	    			strPaymentsDetailsOther = objAcctFuncInvoices.fn_GeneratePayments(driver, webWait, strAccountNumber, strRefCode, false, strOtherPaymentAmount, true, "", strTestCaseName);
	    			arrPaymentsDetailsOther = strPaymentsDetailsOther.split("#") ;
	    			
	    			//generating Full payment using Full Amount option.
	    			Thread.sleep(2000);
	    			Log.info("Generating Full Payment...");
	    			String strRefCodeFull = strRefCode + rnRefCode.nextInt(9) ;	// generating a random number such that RefCode can be reused.
	    			strPaymentsDetailsFull = objAcctFuncInvoices.fn_GeneratePayments(driver, webWait, strAccountNumber, strRefCodeFull, true, "", true, "", strTestCaseName);
	    			arrPaymentsDetailsFull = strPaymentsDetailsFull.split("#");
	    			
	    			//TODO: Verify the Cash Receipt Register - SGAS Report for details.
	    			Thread.sleep(2000);
	    			strCoupledPaymentDetails = arrPaymentsDetailsOther[1].toString()+"#"+arrPaymentsDetailsFull[1].toString();
	    			arrCoupledPaymentDetails = strCoupledPaymentDetails.split("#");    			
	    			strReferenceCodes = strRefCode +"#"+ strRefCodeFull;
	    			arrReferenceCodes = strReferenceCodes.split("#");    			
	    			blnValidatedCashAplctnDate = fn_VerifyCashApplicationDate(driver, webWait, strAccountNumber, arrCoupledPaymentDetails, arrReferenceCodes, strTestCaseName);    			
	    			
	    			//TODO: Verify the Payment Transactions by Date Report for details.
	    			Thread.sleep(2000);
	    			strCoupledPaymentDetails = strPaymentsDetailsOther +"#"+ strPaymentsDetailsFull;
	    			arrCoupledPaymentDetails = strCoupledPaymentDetails.split("#");
	    			blnValidatedPaymentTxnDate = fn_VerifyPaymentTxnDate(driver, webWait, strAccountNumber, arrCoupledPaymentDetails, strTestCaseName);
	    		}
    		}
    	}
    	catch(Exception exception){
    		Utils.takeScreenshot(driver, strTestCaseName);
    		Log.error("ERROR: Verification for the AR Payment Details couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Verification for the AR Payment Details couldn't be done due to exception:  "+exception.toString());     		
    		throw exception;
    	}
    	catch(AssertionError exception){
    		Utils.takeScreenshot(driver, strTestCaseName);
    		Log.error("ERROR: Verification for the AR Payment Details couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Verification for the AR Payment Details couldn't be done due to exception:  "+exception.toString());    		
       		throw exception;
    	}
    	
    	//TODO: Refund the created payments so that the same data can be reused for future executions.
    	if((blnParentPay == true) || (blnStandardSelfPay == true)){
    		
    		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reports"))).click();
    		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Analytics and Reporting"))).click();
    		Thread.sleep(2000);    		
			//get the full URL for API call.
			String strAPIUrl = objAccountFuncs.fn_GetFullUrl(driver, webWait, strAPIInstanceURL, "issue_refund_to_acct");
			
			for(Integer i = 0; i < arrCoupledPaymentDetails.length; i = i + 2){
				
				strAPIUrl = strAPIUrl+"&acct_no="+strAccountNumber+"&payment_transaction_id="+arrCoupledPaymentDetails[i]
							+"&reason_code=5"+"&total_refund_amount="+arrCoupledPaymentDetails[i+1];
				objAPIHandler.makeSimplePostCall(strAPIUrl);	//make a POST call for Refunds.
			}
    	}
		
    	//TODO: Set the status of the TC execution.
		if((blnValidatedCashAplctnDate == false) || (blnValidatedPaymentTxnDate == false)){
			
			assertTrue(false, "ERROR: Verification for the AR Payment Details failed for the AR Payment Details Report !");
		}		

    	//TODO: Log the end of the test case. 
    	Log.endTestCase(strTestCaseName);    	
	}	

	
/*
	Function fn_VerifyCashReciptRegister: This method validates the report for data.
	Inputs: strAccountNum - account number for which Report is to be tested.
			arrPaymentDetails - Array with details of the Amount Paid.	
			arrReferenceCodes - Array with the Reference Codes used to generate the payments.		 
	
	Outputs: Boolean status if the report has been verified or not.
*/	
	public Boolean fn_VerifyCashApplicationDate(WebDriver driver, WebDriverWait webWait, String strAccountNum
			, String[] arrPaymentDetails, String[] arrReferenceCodes, String strTestCaseName) throws Exception{		

		//TODO: Create object for the classes to be used for accessing reusable codes.
		ReportUtils objReportUtils = new ReportUtils();
		
		Boolean blnValidatedCashAplctnDate = false;
		WebElement webCashAplctnDate = null;
		String strParentWindow = driver.getWindowHandle();		
		/*String strCurrentDate = Utils.getDateYearFirst();	***** Overlooking Date for Report due to Current Day report generation *****
		strCurrentDate = objReportUtils.fn_GetReportDateFormat(strCurrentDate, "mm/dd/yyyy");*/
		
		//TODO: Generate the Report and then validate the details.
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Analytics and Reporting"))).click();
		objReportUtils.fn_GenerateReport(driver, "SGAS", "Cash Application by Date - SGAS", "Current Day", "Current Day");
		Thread.sleep(5000);	// sleep added to let the generated report be displayed.
		webCashAplctnDate = objReportUtils.fn_ReadGeneratedReport(driver);
		Utils.takeScreenshot(driver, strTestCaseName);
		List<WebElement> webCashAplctnDateRows = webCashAplctnDate.findElements(By.tagName("tr"));
		List<WebElement> webCashAplctnDateRowsSL = webCashAplctnDateRows.subList(2, webCashAplctnDateRows.size());	//avoid the first two rows read.
		Integer intForLoop = 0;
		for(WebElement rows : webCashAplctnDateRowsSL){
			
			List<WebElement> cols = rows.findElements(By.tagName("td"));
			if(cols.size() >= 2){
				//String strAppliedDateReport = cols.get(0).getAttribute("innerText").toString().trim();	***** Overlooking Date for Report due to Current Day report generation *****				
				String strAccountNumReport = cols.get(1).getAttribute("innerText").toString().trim();
				String strTxnTypeReport = cols.get(6).getAttribute("innerText").toString().trim();
				String strTxnAmountReport = cols.get(7).getAttribute("innerText").toString().trim();
				String strRefCodeReport = cols.get(10).getAttribute("innerText").toString().trim();
								
				intForLoop ++;
				for(Integer i = 0; i < arrPaymentDetails.length; i ++)	// search the report for the Transaction Details shared.				
				if(/*strAppliedDateReport.contentEquals(strCurrentDate) && */strAccountNumReport.contentEquals(strAccountNum))
					if(strTxnAmountReport.contentEquals(arrPaymentDetails[i]) && strTxnTypeReport.contentEquals("Check"))
						if(strRefCodeReport.contentEquals(arrReferenceCodes[i])){
							
							Log.info("The Cash Application by Date Report has been Validated for Account "+strAccountNum+
									" with Reference Code "+arrReferenceCodes[i]+" and Amount "+arrPaymentDetails[i]);
							blnValidatedCashAplctnDate = true;
						}				
			}
		}
		if(blnValidatedCashAplctnDate == false){
			
			Log.error("The Cash Application by Date Report couldn't be Validated for Account "+strAccountNum);
			Reporter.log("The Cash Application by Date Report couldn't be Validated for Account "+strAccountNum);
		}
		
		objReportUtils.fn_HandleReportWindows(driver, strParentWindow);		
		return blnValidatedCashAplctnDate;
	}
	
/*
	Function fn_VerifyPaymentTxnDate: This method validates the report for data.
	Inputs: strAccountNum - account number for which Report is to be tested.
			arrPaymentDetails - Array with details like the Transaction Number and the Amount Paid.			 
	
	Outputs: Boolean status if the report has been verified or not.
*/  		
	public Boolean fn_VerifyPaymentTxnDate(WebDriver driver, WebDriverWait webWait, String strAccountNum
			, String[] arrPaymentDetails, String strTestCaseName) throws Exception{		

		//TODO: Create object for the classes to be used for accessing reusable codes.
		ReportUtils objReportUtils = new ReportUtils();
		
		Boolean blnValidatedPaymentTxnDate = false;
		WebElement webPaymentTxnDate = null;
		String strParentWindow = driver.getWindowHandle();
		/*String strCurrentDate = Utils.getDateYearFirst();	***** Overlooking Date for Report due to Current Day report generation *****
		strCurrentDate = objReportUtils.fn_GetReportDateFormat(strCurrentDate, "mm/dd/yyyy");*/
		
		//TODO: Generate the Report and then validate the details.
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reports"))).click();
		objReportUtils.fn_GenerateReport(driver, "Standard", "Payment Transactions by Date", "Current Day", "Current Day");
		Thread.sleep(5000);	// sleep added to let the generated report be displayed.
		webPaymentTxnDate = objReportUtils.fn_ReadGeneratedReport(driver);
		Utils.takeScreenshot(driver, strTestCaseName);
		List<WebElement> webPaymentTxnDateRows = webPaymentTxnDate.findElements(By.tagName("tr"));
		List<WebElement> webPaymentTxnDateRowsSL = webPaymentTxnDateRows.subList(2, webPaymentTxnDateRows.size());	//avoid the first two rows read.
		Integer intForLoop = 0;
		for(WebElement rows : webPaymentTxnDateRowsSL){
			
			List<WebElement> cols = rows.findElements(By.tagName("td"));
			if(cols.size() >= 2){
				//String strTxnDateReport = cols.get(0).getAttribute("innerText").toString().trim();	***** Overlooking Date for Report due to Current Day report generation *****
				String strTxnTypeReport = cols.get(1).getAttribute("innerText").toString().trim();
				String strAccountNumReport = cols.get(3).getAttribute("innerText").toString().trim();
				String strTxnAmountReport = cols.get(5).getAttribute("innerText").toString().trim();
				String strTxnIDReport = cols.get(7).getAttribute("innerText").toString().trim();
				
				intForLoop ++;
				for(Integer i = 0; i < arrPaymentDetails.length; i = i+2)	// search the report for the Transaction Details shared.				
				if(/*strTxnDateReport.contentEquals(strCurrentDate) && */strAccountNumReport.contentEquals(strAccountNum))
					if(strTxnAmountReport.contentEquals(arrPaymentDetails[i+1]) && strTxnIDReport.contentEquals(arrPaymentDetails[i]))
						if(strTxnTypeReport.contentEquals("Check")){
							
							Log.info("The Payment Transaction Date Report has been Validated for Account "+strAccountNum+
									" with Transaction ID "+arrPaymentDetails[i]+" and Amount "+arrPaymentDetails[i+1]);
							blnValidatedPaymentTxnDate = true;
						}				
			}
		}
		if(blnValidatedPaymentTxnDate == false){
			
			Log.error("The Payment Transaction Date Report couldn't be Validated for Account "+strAccountNum);
			Reporter.log("The Payment Transaction Date Report couldn't be Validated for Account "+strAccountNum);
		}
		
		objReportUtils.fn_HandleReportWindows(driver, strParentWindow);		
		return blnValidatedPaymentTxnDate;
	}	
	
	//TODO: Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "PaymentDetailsReports");
	}
	
	
	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
}