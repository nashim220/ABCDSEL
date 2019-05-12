/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_PaymentDetailsFinancialReport 
 Purpose     		: 	Purpose of this file is :
						1. To verify if the Payment details in various Financial Reports.
						2. This is automation of TC: QAABE-94.

 Date       		:	08/31/2015
 Modified Date		:	11/06/2015, 10/14/2015, 09/24/2015, 09/14/2015.
 Version Information:	Version 3.2
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "PaymentDetails" worksheet 
 						for excel "TestData.xls" should be reset after EVERY RUN; basically "RefCode" ! 						

 Test Steps 		:	1. Login using valid role credentials for updating existing customer account.
 						2. Search the customer account and verify it's outstanding invoice.
 						3. Make Payments for the outstanding invoice.
 						4. Make a refund for the payment done in step 3.
 						5. Void Payments done in Step 3.
 						6. Navigate to the Analytics & reporting -> Reports -> Run Reports.
 						7. Verify the "Cash Application by Date - SGAS" & "Payment Transactions by Account"
 							for the invoice data and the refund data.
 							
 Version Changes 2.0:	1. Using reusable methods for report generation.
 Version Changes 3.0:	1. Using new Appmodules file AccountFunctions_Invoices for reusable code.
 Version Changes 3.1:	1. Using new Report Utils file reports related utility's reusable code.
 Version Changes 3.2:	1. Specifically mentioned in the PreCodition to reset value for "RefCode"; else TC fails.  
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.Reports;

import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;

import atu.ddf.exceptions.DataDrivenFrameworkException;

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

import pageObjects.PaymentDetailsFinancialReport;
import pageObjects.VerifyGLLocationSegment;
import utility.EnvironmentDetails;
import utility.Log;
import utility.ReportUtils;
import utility.Utils;
import utility.VerificationMethods;
import appModules.AccountFunctions_Invoices;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;

public class Cls_PaymentDetailsFinancialReport extends VerificationMethods
{
    public static WebDriver driver;
    public static String strTestCaseName = "Verify Payment details on Finance Reports";
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
    Function fn_VerifyPaymentDetails: This is the actual Test NG test for Verifying the payment details on various reports.
*/
	@Test(dataProvider = "objectTestData", description = "Verify Payment details on Fiannce Reports")
    public void fn_VerifyPaymentDetails(String strAccountNum, String strRefCode, String strRefundAmount) throws Exception
    {
    	//TODO: Create object for the classes to be used for accessing reusable codes.
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
    	AccountFunctions_Invoices objAcctFuncInvoices = new AccountFunctions_Invoices();		
    	VerifyGLLocationSegment objGLLocation = new VerifyGLLocationSegment();
    	PaymentDetailsFinancialReport objPayDetails = new PaymentDetailsFinancialReport();
    	ReportUtils objReportUtils = new ReportUtils();
		Utils objUtils = new Utils();
		
		//TODO: declare booleans to govern the actions to be performed.
		Boolean blnFullBalance = false;
		Boolean blnCashApplicationByDate = false;
		Boolean blnPaymentTxbByAccount = false;
		Boolean blnVerifyRefundsReport = false;
		Boolean blnVerifyPaymentsReport = false;		
		Boolean blnVoidingPaymentsReport = false;
		
		String[] arrPaymentsDetails = null;
		String strPaymentsTxnID = null;
		String strActualAmount = null;
		
    	//TODO: Log the beginning of the test case.
    	Log.startTestCase(strTestCaseName);    	

		try
		{     		           	
    		//TODO: Exit the Test Case when Refunds Amount is set to be "0".
			if(strRefundAmount.contentEquals("0"))
    			throw new Exception("ERROR: The Refunds Ammount cannot be ZERO ! The test case is exiting with exception 'ZERO Refunds Amount Requested'...");
			
			//TODO: For the Searched account, verify if there is any outstanding invoice.
           	objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
           	webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Reset Password")));
           	String strFullBalance = objPayDetails.fn_getFullBalance(driver, webWait).getAttribute("innerText").toString().trim();
           	if(!strFullBalance.equals("$ 0.00 USD"))           	
           		blnFullBalance = true;
           	
           	//TODO: If Invoice generation is successful, make full Payments and also give some refund.
           	if(blnFullBalance == true)
           	{
           		Log.info("Calling Generate Payments reusable module to generate payments for the account: "+strAccountNum);
           		strPaymentsTxnID = objAcctFuncInvoices.fn_GeneratePayments(driver, webWait, strAccountNum, strRefCode, true, "", true, "", strTestCaseName);
           		
           		arrPaymentsDetails = strPaymentsTxnID.split("#");           		
           		strPaymentsTxnID = arrPaymentsDetails[0].toString();
           		strActualAmount =  arrPaymentsDetails[1].toString();
           		
           		if(strPaymentsTxnID != null)
           		{
           			Log.info("For Reporting Purpose, we are booking Refunds for the recently created Payments Transaction..");
           			Boolean blnBookRefunds = objAcctFuncInvoices.fn_BookingRefunds(driver, webWait, strAccountNum, strPaymentsTxnID,
           																		false, strRefundAmount, "Good Will", strTestCaseName);
           			if(blnBookRefunds == false)
               		{
               			Log.error("ERROR: Refunds functionality couldn't be completed !");
               			Reporter.log("ERROR: Refunds functionality couldn't be completed !");           			
               		}
           			
           			Log.info("For Reporting Purpose, we are Voiding Payment for the recently created Payments Transaction..");
           			Boolean blnVoidPayments = objAcctFuncInvoices.fn_VoidPayments(driver, webWait, strAccountNum, strPaymentsTxnID, strTestCaseName);
           			if(blnVoidPayments == false)
               		{
               			Log.error("ERROR: Void Payments functionality couldn't be completed !");
               			Reporter.log("ERROR: Void Payments functionality couldn't be completed !");           			
               		}           			
           			           			
           			//TODO: Logic for verifying the couple of Finance Reports for Payments & Refunds Data.
           			objGLLocation.fn_clickAnalyticsReporting(driver, webWait).click();
            		
            		//TODO: Verify the contents of the 'Cash Application by Date - SGAS' Report.
           			objReportUtils.fn_GenerateReport(driver, "SGAS", "Cash Application by Date - SGAS", "Current Day", "Current Day");
           			Thread.sleep(3500);
            		Utils.takeScreenshot(driver, strTestCaseName);

            		Log.info("Verifying data for the 'Cash Application by Date - SGAS' Report..");
            		WebElement webReadContents = objReportUtils.fn_ReadGeneratedReport(driver);
            		List<WebElement> lstwebReadContentsRow = webReadContents.findElements(By.tagName("tr"));
            		for(WebElement row: lstwebReadContentsRow)
            		{
            			List<WebElement> cols = row.findElements(By.tagName("td"));
            			
            			if(cols.size() >= 2)
            			{            				
            				String strReportAccountNum = cols.get(1).getAttribute("innerText").toString().trim();
            				String strReportTxnType = cols.get(6).getAttribute("innerText").toString().trim();
            				String strReportAppliedAmount = cols.get(7).getAttribute("innerText").toString().trim();
            				String strReportRefCode = cols.get(10).getAttribute("innerText").toString().trim();
            				String strBigDeciRefundAmount = objUtils.fn_ConvertStringToBigDecimal(strRefundAmount);

            				//TODO: Verify if the Payments activity is reflected in the 'Cash Application by Date - SGAS' Report. 
            				if(strReportAccountNum.equals(strAccountNum) && strReportRefCode.equals(strRefCode) 
            						&& strReportAppliedAmount.equals(strActualAmount) && !strReportTxnType.contains("Voided"))
            				{
            					blnVerifyPaymentsReport = true;
            					Log.info("Data in Report successfully verified for the Payments posted with value: "+strActualAmount);
            				}
            				//TODO: Verify if the Refunds activity is reflected in the 'Cash Application by Date - SGAS' Report.
            				if(blnBookRefunds == true && strReportTxnType.contains("Refund") 
            						&& strReportAccountNum.equals(strAccountNum) && strReportAppliedAmount.equals(strBigDeciRefundAmount))
            				{
            					blnVerifyRefundsReport = true;
            					Log.info("Data in Report successfully verified for the Refunds booked with value: "+strRefundAmount);
            				}
            				//TODO: Verify if the Void Payments activity is reflected in the 'Cash Application by Date - SGAS' Report.
            				if(strReportTxnType.contains("Voided") && strReportAccountNum.equals(strAccountNum) 
            							&& strReportRefCode.equals(strRefCode) && strReportAppliedAmount.contains(strActualAmount))
            				{
            					blnVoidingPaymentsReport = true;
            					Log.info("Data in Report successfully verified for the Voiding of Payments posted with value: "+strActualAmount);
            				}
            				
            				//TODO: exit loop after verifying report on availability of test data. 
            				if((blnVerifyPaymentsReport == true) && (blnBookRefunds == false) && (blnVoidPayments == false))
            				{
            					blnCashApplicationByDate = true;
            					break;
            				}
            				else if((blnVerifyPaymentsReport == true) && (blnVerifyRefundsReport == true) && (blnVoidPayments == false))
            				{
            					blnCashApplicationByDate = true;
            					break;
            				}
            				else if((blnVerifyPaymentsReport == true) && (blnBookRefunds == false) && (blnVoidingPaymentsReport == true))
            				{
            					blnCashApplicationByDate = true;
            					break;
            				}
            				else if((blnVerifyPaymentsReport == true) && (blnVerifyRefundsReport == true) && (blnVoidingPaymentsReport == true))
            				{
            					blnCashApplicationByDate = true;
            					break;
            				}
            			}
            		}            		
            		
            		//TODO: Verify the contents of the 'Payment Transactions by Account' Report.
            		blnVerifyRefundsReport = false;
            		blnVerifyPaymentsReport = false;		
            		blnVoidingPaymentsReport = false;
            		driver.switchTo().defaultContent();
            		Thread.sleep(3500);
            		driver.findElement(objGLLocation.fn_clickReportFrameCloseButton()).click();	//click close on the report frame.
            		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reports"))).click();
            		objReportUtils.fn_GenerateReport(driver, "Standard", "Payment Transactions by Account", "Current Day", "Current Day");
            		Thread.sleep(2000);
            		Utils.takeScreenshot(driver, strTestCaseName);
            		
            		Log.info("Verifying data for the 'Payment Transactions by Account' Report..");
            		webReadContents = objReportUtils.fn_ReadGeneratedReport(driver);
            		lstwebReadContentsRow = webReadContents.findElements(By.tagName("tr"));
            		for(WebElement row: lstwebReadContentsRow)
            		{
            			List<WebElement> cols = row.findElements(By.tagName("td"));
            			
            			if(cols.size() >= 2)
            			{            				
            				String strReportAccountNum = cols.get(0).getAttribute("innerText").toString().trim();
            				String strReportTxnType = cols.get(2).getAttribute("innerText").toString().trim();
            				String strReportAppliedAmount = cols.get(4).getAttribute("innerText").toString().trim();
            				
            				//TODO: Verify if the Payments activity is reflected in the 'Payment Transactions by Account' Report. 
            				if(strReportAccountNum.equals(strAccountNum) && strReportAppliedAmount.equals(strActualAmount) && !strReportTxnType.contains("Voided"))
            				{
            					blnVerifyPaymentsReport = true;
            					Log.info("Data in Report successfully verified for the Payments posted with value: "+strActualAmount);
            				}
               				//TODO: Verify if the Void Payments activity is reflected in the 'Payment Transactions by Account' Report.
            				if(strReportTxnType.contains("Voided") && strReportAccountNum.equals(strAccountNum) && strReportAppliedAmount.contains(strActualAmount))
            				{
            					blnVoidingPaymentsReport = true;
            					Log.info("Data in Report successfully verified for the Voiding of Payments posted with value: "+strActualAmount);
            				}
            				
            				//TODO: exit loop after verifying report on availability of test data.
            				if((blnVerifyPaymentsReport == true) && (blnVoidPayments == false))
            				{
            					blnPaymentTxbByAccount = true;
            					break;
            				}
            				else if((blnVerifyPaymentsReport == true) && (blnVoidingPaymentsReport == true))
            				{
            					blnPaymentTxbByAccount = true;
            					break;
            				}
            			}
            		}            		
           		}
           		driver.switchTo().defaultContent();
        		Thread.sleep(3500);
        		driver.findElement(objGLLocation.fn_clickReportFrameCloseButton()).click();	//click close on the report frame.
           		
           		if((blnCashApplicationByDate == true) && (blnPaymentTxbByAccount == true))
           		{
           			Log.info("Reports Verification has successfully completed !");
           		}
           		else if(blnCashApplicationByDate == false)
           		{
           			Reporter.log("ERROR: 'Cash Application by Date - SGAS' Report Verification has Failed !");
           			assertTrue(false, "ERROR: 'Cash Application by Date - SGAS' Report Verification has Failed !");
           		}
           		else if(blnPaymentTxbByAccount == false)
           		{
           			Reporter.log("ERROR: 'Payment Transactions by Account' Report Verification has Failed !");
           			assertTrue(false, "ERROR: 'Payment Transactions by Account' Report Verification has Failed !");
           		}           		
           	}
           	else
           	{
           		Reporter.log("ERROR: Since Invoice isn't generated, we can't verify the Finance Reports !");
           		assertTrue(false, "ERROR: Since Invoice isn't generated, we can't verify the Finance Reports !");
           	}
           	
		}
    	catch (Exception exception)
    	{
    		Log.error("ERROR: Verification for the Payment details on reports couldn't be done due to exception: "+exception.toString());
       		Reporter.log("ERROR: Verification for the Payment details on reports couldn't be done due to exception: "+exception.toString());
    		throw exception;
    	}
    	catch (AssertionError exception)
    	{
    		Log.error("ERROR: Verification for the Payment details on reports couldn't be done due to exception: "+exception.toString());
       		Reporter.log("ERROR: Verification for the Payment details on reports couldn't be done due to exception: "+exception.toString());
    		throw exception;
    	}
    	    	
    	//TODO: Log the end of the test case. 
    	Log.endTestCase(strTestCaseName);		
    }   
    

	//TODO: Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "PaymentDetails");
	}
	
	
	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
}
