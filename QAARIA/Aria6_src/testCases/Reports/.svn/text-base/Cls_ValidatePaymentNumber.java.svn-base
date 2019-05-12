/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_ValidatePaymentNumber 
 Purpose     		: 	Purpose of this file is :
						1. To Verify that Payment Number generated is displayed correctly on the
						'Payment Transactions by Date'.
						2. Test Class for the test case QAABE-356.
 
 Date       		:	10/14/2015
 Modified Date		:	11/06/2015 
 Version Information:	Version 1.1
 
  PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "PaymentNumDetails" worksheet 
 						for excel "TestData.xlsx" should be reset after EVERY RUN; basically "RefCode" !
 						
 Test Steps 		:	1. Login using valid role credentials for updating existing customer account.
 						2. Search the customer account and verify it's outstanding invoice.
 						3. Make Payments (full / partial) for the outstanding invoice.
 						4. Navigate to the Analytics & reporting -> Reports -> Run Reports.
 						5. Verify the "Payment Transactions by Date" for the Payment Number.
 						
 Version Changes 1.1:	1. Specifically mentioned in the PreCodition to reset value for "RefCode"; else TC fails. 						
 
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
import appModules.AccountFunctions_Invoices;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
import utility.EnvironmentDetails;
import utility.Log;
import utility.ReportUtils;
import utility.Utils;
import utility.VerificationMethods;

public class Cls_ValidatePaymentNumber extends VerificationMethods 
{

    public static WebDriver driver;
    public static String strTestCaseName = "Verify Payment Number on Finance Report";
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
    Function fn_PaymentNumber: This is the actual Test NG test for Verifying the Payment Number on reports.
*/
	@Test(dataProvider = "objectTestData", description = "Verify Payment details on Fiannce Reports")
    public void fn_PaymentNumber(String strAccountNum, String strRefCode, String strOtherPaymentAmount) throws Exception
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
		Boolean blnVerifyPaymentsReport = false;		
		
		String[] arrPaymentsDetails = null;
		String strPaymentsTxnID = null;
		String strActualAmount = null;
		
    	//TODO: Log the beginning of the test case.
    	Log.startTestCase(strTestCaseName);    	

		try
		{     		           	
    		//TODO: Exit the Test Case when Payment Amount is set to be "0".
			if(strOtherPaymentAmount.contentEquals("0"))
				assertTrue(false, "ERROR: The Payment Ammount cannot be ZERO ! The test case is exiting with exception 'ZERO Payment Amount Requested'...");
			
			//TODO: For the Searched account, verify if there is any outstanding balance.
           	objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
           	webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Reset Password")));
           	String strFullBalance = objPayDetails.fn_getFullBalance(driver, webWait).getAttribute("innerText").toString().trim();
           	if(!strFullBalance.equals("$ 0.00 USD"))           	
           		blnFullBalance = true;
           	
           	//TODO: If Balance is mentioned, then make Payments and verify it in the report.
           	if(blnFullBalance == true)
           	{
           		Log.info("Calling Generate Payments reusable module to generate payments for the account: "+strAccountNum);
           		strPaymentsTxnID = objAcctFuncInvoices.fn_GeneratePayments(driver, webWait, strAccountNum, strRefCode, false, strOtherPaymentAmount, true, "", strTestCaseName);
           		
           		arrPaymentsDetails = strPaymentsTxnID.split("#");           		
           		strPaymentsTxnID = arrPaymentsDetails[0].toString();
           		strActualAmount =  arrPaymentsDetails[1].toString();
           		
           		//convert the string to big decimal type data so that report displaying decimal value is mitigated.
           		strActualAmount =  objUtils.fn_ConvertStringToBigDecimal(strActualAmount); 
           		
           		if(strPaymentsTxnID != null)
           		{
           			//TODO: Logic for verifying the couple of Finance Reports for Payments & Refunds Data.
           			objGLLocation.fn_clickAnalyticsReporting(driver, webWait).click();
            		
            		//TODO: Verify the contents of the 'Payment Transactions by Date' Report.
           			objReportUtils.fn_GenerateReport(driver, "Standard", "Payment Transactions by Date", "Current Day", "Current Day");
            		Thread.sleep(2000);
            		Utils.takeScreenshot(driver, strTestCaseName);
            		
            		Log.info("Verifying data for the 'Payment Transactions by Date' Report..");
            		WebElement webReadContents = objReportUtils.fn_ReadGeneratedReport(driver);
            		List<WebElement> lstwebReadContentsRow = webReadContents.findElements(By.tagName("tr"));
            		for(WebElement row: lstwebReadContentsRow)
            		{
            			List<WebElement> cols = row.findElements(By.tagName("td"));
            			
            			if(cols.size() >= 2)
            			{            				
            				String strReportAccountNum = cols.get(3).getAttribute("innerText").toString().trim();
            				String strReportAppliedAmount = cols.get(5).getAttribute("innerText").toString().trim();
            				String strReportAriaEventNum = cols.get(7).getAttribute("innerText").toString().trim();
            				
            				//TODO: Verify if the Payments activity is reflected in the 'Payment Transactions by Date' Report. 
            				if(strReportAccountNum.equals(strAccountNum))
            					if(strReportAppliedAmount.equals(strActualAmount))
            						if(strReportAriaEventNum.contentEquals(strPaymentsTxnID))            							
			            				{			            					
			            					blnVerifyPaymentsReport = true;
			            					Log.info("Data in Report successfully verified for the Payments posted with value: "+strActualAmount
			            							+" & Payments Transacation ID as: "+strReportAriaEventNum);
			            					break;
			            				}
            			}
            		}            		
           		}
           		driver.switchTo().defaultContent();
        		Thread.sleep(3500);
        		driver.findElement(objGLLocation.fn_clickReportFrameCloseButton()).click();	//click close on the report frame.
           		
           		if(blnVerifyPaymentsReport == true)
           		{
           			Log.info("Reports Verification has successfully completed !");
           		}
           		else 
           		{
           			Reporter.log("ERROR: 'Cash Application by Date - SGAS' Report Verification has Failed !");
           			assertTrue(false, "ERROR: 'Cash Application by Date - SGAS' Report Verification has Failed !");
           		}
           	}
           	else
           	{
           		Reporter.log("ERROR: Since there is no Balance Amount, we can't make Payments and Verify Reports !");
           		assertTrue(false, "ERROR: Since there is no Balance Amount, we can't make Payments and Verify Reports !");
           	}           	
		}
    	catch (Exception exception)
    	{
    		Log.error("ERROR: Verification for the Payment details on report couldn't be done due to exception: "+exception.toString());
       		Reporter.log("ERROR: Verification for the Payment details on report couldn't be done due to exception: "+exception.toString());
    		throw exception;
    	}
    	catch (AssertionError exception)
    	{
    		Log.error("ERROR: Verification for the Payment details on report couldn't be done due to exception: "+exception.toString());
       		Reporter.log("ERROR: Verification for the Payment details on report couldn't be done due to exception: "+exception.toString());
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
	    return objUtils.data1("TestCaseDetails", "PaymentNumDetails");
	}
	
	
	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }	
}
