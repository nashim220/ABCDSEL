/*
  Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_ValidateDailyTieOutReport_Batch 
 Purpose     		: 	Purpose of this file is :
						1. To Verify that unbilled / billed usages are correctly displayed in Reports.
						2. Test Class for the test case QAABE-441.
 
 Date       		:	12/02/2015 
 Version Information:	Version 1.1
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "DailyTieOutBatch" worksheet for excel "TestData.xls".
 						
 Test Steps 		:	1. Login using valid role credentials for accessing an account.
 						2. Search the account(s) and read the invoice mentioned in the worksheet.
 						3. Verify that billed Usage is now displayed in "Revenue Recognition Management Detail Report by Accounting Period - SGAS".
 						
Version Changes 1.1:	1. Updated the BeforeClass and a logic for the data for verification of reports. 						
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/


package testCases.Reports;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ValidateDailyTieOutReport;
import utility.EnvironmentDetails;
import utility.Log;
import utility.ReadFiles;
import utility.ReportUtils;
import utility.Utils;
import utility.VerificationMethods;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Invoices;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_ValidateDailyTieOutReport_Batch extends VerificationMethods  {

    public static WebDriver driver;
    public static String strTestCaseName = "Verify Daily TieOut Report Batch Exec";
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
	Function fn_VerifyReports: This method will verify various Reports for Billed / Unbilled Usage for an Account.    						
*/	    
	@Test(dataProvider = "objectTestData", description = "Verify Daily TieOut Report Batch Exec")
    public void fn_VerifyReports(String strFileName, String strWorksheetName) throws Exception
	{		
		//TODO: Create objects to classes for page objects to access the items under it.
		ValidateDailyTieOutReport objDailyTieOut = new ValidateDailyTieOutReport(); 
		AccountFunctions_Invoices objAcctFuncInvoices = new AccountFunctions_Invoices();
		AccountFunctions objAccountFunc = new AccountFunctions();
		ReportUtils objReportUtils = new ReportUtils();
		
		Boolean blnInvoiceSelected = false;
		Boolean blnNoInvoiceDetailsInFile = false;
		Boolean blnValidateRevRecogAcctPeriodAI = false;
		WebElement webReportContents = null;
		String strParentWindow = null;
		
		//TODO: Log the beginning of the test case.
    	Log.startTestCase(strTestCaseName);
		
		String strExcelFile = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
				+ System.getProperty("file.separator") + strFileName;
		File fileDailyTieOut = new File(strExcelFile);		
		ArrayList<String> arrlstFileContents = ReadFiles.readExcelFileAsArray(fileDailyTieOut, strWorksheetName);
		if(arrlstFileContents.size()%5 != 0)
			assertTrue(false, "ERROR: There is error in data read from the excel file, exiting the TC with exception !");
		
		//get the date for which this report is to be verified; Usually will be Previous Day's Report ONLY. 
		String strActualInvoiceDate = objAccountFunc.fn_getPreviousDate(1);
		
		//TODO: Validate the Report 'Revenue Recognition Management Detail Report by Accounting Period - SGAS' for existence of the billed data.
		for(Integer intForLoop = 2; intForLoop < arrlstFileContents.size(); intForLoop =  intForLoop+5){
			//check ONLY for yesterday's generated Invoices...
			if(strActualInvoiceDate.contentEquals(arrlstFileContents.get(intForLoop))){
				
				List<List<NameValuePair>> lstpairReadInvoice = new ArrayList<List<NameValuePair>>();
				blnInvoiceSelected = objAcctFuncInvoices.fn_SelectInvoice(driver, webWait, arrlstFileContents.get(intForLoop-2), arrlstFileContents.get(intForLoop-1));
				if(blnInvoiceSelected == false)
					assertTrue(false, "ERROR: The Invoice requested to search isn't found, can't proceed further. Exiting TC with exception !");
				lstpairReadInvoice = objAcctFuncInvoices.fn_ReadInvoiceDetails(driver, webWait);	
				Utils.takeScreenshot(driver, strTestCaseName);
				
				objDailyTieOut.fn_clickAnalyticsReporting(driver, webWait).click();
				
				blnNoInvoiceDetailsInFile = true;
		       	strParentWindow = driver.getWindowHandle();           	
		       	objReportUtils.fn_GenerateReportCalendarDate(driver, webWait, "SGAS", "Revenue Recognition Management Detail Report by Accounting Period - SGAS"
		       			, arrlstFileContents.get(intForLoop), arrlstFileContents.get(intForLoop));
		       	Thread.sleep(10000);
				webReportContents = objReportUtils.fn_ReadGeneratedReport(driver);//table body has been received here.
				Thread.sleep(2000);
		     	Utils.takeScreenshot(driver, strTestCaseName);
		     	List<String> arrlstValidateContents = arrlstFileContents.subList((intForLoop-2), arrlstFileContents.size());
				blnValidateRevRecogAcctPeriodAI = fn_ValidateRevRecogMgmtDetail(webReportContents, arrlstValidateContents, lstpairReadInvoice);
				objReportUtils.fn_HandleReportWindows(driver, strParentWindow);	// call window handler for closing Report windows/frame.
				Thread.sleep(3500);
			}			
			//flag that there is no Invoice Data for yesterday's execution.
			if((intForLoop == arrlstFileContents.size()-1) && (blnNoInvoiceDetailsInFile == false)){
				
				Log.error("ERROR: The '"+strWorksheetName+"' sheet has no data for yesterday's generated Invoice.");
				Reporter.log("ERROR: The '"+strWorksheetName+"' sheet has no data for yesterday's generated Invoice.");
			}
		}			
		
		//final marker for deciding the status of the execution.
		if(blnValidateRevRecogAcctPeriodAI == false)
			assertTrue(false, "Error: The verification of 'Revenue Recognition Management Detail Report by Accounting Period - SGAS' failed !");
		else
			Log.info("The verification of 'Revenue Recognition Management Detail Report by Accounting Period - SGAS' is passed!");
		
		//TODO: Log the end of the test case. 
    	Log.endTestCase(strTestCaseName);
	}

	
/*
	Function: fn_ValidateRevRecogMgmtDetail - To Validate the 'Revenue Recognition Management Detail Report by Accounting Period - SGAS' returning boolean status.
	Inputs: Report Table as a Webelement, 
	Output: Boolean Status after validation.
	
	Validated on Basis of: AccountNo, Invoice Details, Booking Date & Usage Period.  
*/	
	
	public Boolean fn_ValidateRevRecogMgmtDetail(WebElement webReportContents, List<String> arrlstValidateContents
			, List<List<NameValuePair>> lstpairReadInvoice) throws Exception
	{
		//TODO: Create objects to classes for page objects to access the items under it.
		Utils objUtils = new Utils();
		
		Boolean blnRevRecogMgmtDtlValidated = false;
		Integer intRowsIterator = 0;		
		
		String strInvoiceDate = arrlstValidateContents.get(2);
		String strStartDate = arrlstValidateContents.get(3);
		String strEndDate = arrlstValidateContents.get(4);
		
		//TODO: Now compare each line item in the report for specific Account's latest generated Invoice.
		List<WebElement> lstwebReportContentsRows = webReportContents.findElements(By.tagName("tr"));		
		List<WebElement> lstwebReportContentsRowsSL = lstwebReportContentsRows.subList(2, lstwebReportContentsRows.size());	

       	for(List<NameValuePair> lstReadInvoice : lstpairReadInvoice)	//Compare generated Invoice Data.
       	{
       		String[] arrReadInvoiceNum = lstReadInvoice.get(0).toString().split("=");
       		String[] arrReadItemNum = lstReadInvoice.get(1).toString().split("=");
       		String[] arrReadItemAmount = lstReadInvoice.get(6).toString().split("=");
       			arrReadItemAmount[1] = objUtils.fn_ConvertStringToBigDecimal(arrReadItemAmount[1]);
       		
       	   	intRowsIterator = 0;   			
       		for(WebElement rows: lstwebReportContentsRowsSL)
       		{
       			List<WebElement> cols = rows.findElements(By.tagName("td"));	//read the rows of the table
       			intRowsIterator += 1;

       			//comparison logic starts now...
       			if(cols.size() >= 2)	// this is to avoid the header read in the loop.
       			{
       				String strGLAcctDesc = cols.get(3).getAttribute("innerText").toString().trim();
       				String strReportAccountNum = cols.get(10).getAttribute("innerText").toString().trim();
       				String strReportInvoiceNum = cols.get(12).getAttribute("innerText").toString().trim();
       				String strReportItemNum = cols.get(13).getAttribute("innerText").toString().trim();
       				String strReportItemAmount = cols.get(14).getAttribute("innerText").toString().trim();
       					strReportItemAmount = objUtils.fn_ConvertStringToBigDecimal(strReportItemAmount);
       				String strReportBookingDate = cols.get(15).getAttribute("innerText").toString().trim();
       				String strReportTermStartDate = cols.get(16).getAttribute("innerText").toString().trim();
       				String strReportTermEndDate = cols.get(17).getAttribute("innerText").toString().trim();
       				
       				if(strGLAcctDesc.contentEquals("Accounts Receivable"))
       					if(arrlstValidateContents.get(0).contentEquals(strReportAccountNum) && arrReadInvoiceNum[1].contentEquals(strReportInvoiceNum))
       						if(strReportBookingDate.contains(strInvoiceDate) && strReportTermStartDate.contains(strStartDate) && strReportTermEndDate.contains(strEndDate))
       							if(strReportItemNum.contentEquals(arrReadItemNum[1]) && strReportItemAmount.contentEquals(arrReadItemAmount[1])){
       								
		       						Log.info("The Revenue Recognition Management Details Report has data for the Invoice Num: "+arrReadInvoiceNum[1].toString()+" with Item Amount: "
		       										+arrReadItemAmount[1].toString()+" for Account: "+arrlstValidateContents.get(0));
		       						blnRevRecogMgmtDtlValidated = true;
		       						break;
		       					}
       			}
       			else if(!(cols.iterator().hasNext()))
    			{
       				Log.error("ERROR: There is no data generated in the Report !");
       				Reporter.log("ERROR: There is no data generated in the Report !");
    			}
       			
       			//TODO: If entire table for the Report has been compared for the read Invoice, exit with comments.
       			if((lstwebReportContentsRowsSL.size() == intRowsIterator))
       			{
    				Log.error("ERROR: The Revenue Recognition Management Details Report has no data for the Invoice Num: "+arrReadInvoiceNum[1].toString()+" with Item Amount: "
    						+arrReadItemAmount[1].toString()+" for Account: "+arrlstValidateContents.get(0));
    				Reporter.log("ERROR: The Revenue Recognition Management Details Report has no data for the Invoice Num: "+arrReadInvoiceNum[1].toString()+" with Item Amount: "
    						+arrReadItemAmount[1].toString()+" for Account: "+arrlstValidateContents.get(0));
       			}
       		}
       	}
		
		return blnRevRecogMgmtDtlValidated;
	}
	
  	//TODO: Reading data for the test    
  	@DataProvider(name = "objectTestData")
  	public Object[][] testExcelData() throws DataDrivenFrameworkException
  	{
  	    Utils objUtils=new Utils();
  	    return objUtils.data1("TestCaseDetails", "DailyTieOutBatch");
  	}	
 	
  	@AfterTest
  	public void afterMethod() throws Exception
  	 {
  		 //TODO: Logs out of the application & quit the driver
  		 AriaLoginLogout.appLogout(driver);
  		 driver.quit(); 
  	 }	
}