/*
  Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_ValidateDailyTieOutReport 
 Purpose     		: 	Purpose of this file is :
						1. To Verify that unbilled / billed usages are correctly displayed in Reports.
						2. Test Class for the test case QAABE-441.
 
 Date       		:	10/20/2015
 Modified Date		:	12/11/2015, 12/02/2015
 Version Information:	Version 2.1
 
  PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "DailyTieOut" worksheet for excel "TestData.xls".
 						
 Test Steps 		:	1. Login using valid role credentials for accessing an account.
 						2. Navigate to the searched Accounts to add usage to it.
 						3. Verify that Unbilled Usage is displayed in "All Unbilled Usage Report - SGAS".
 						4. Verify that Unbilled Usage is not displayed in "Revenue Recognition Management Detail Report by Accounting Period - SGAS".
 						5. Verify that Unbilled Usage is not displayed in "All Usage Report - SGAS".
 						6. Generate Invoices for data loaded in Step 2.
 						7. Verify that billed Usage is not displayed in "All Unbilled Usage Report - SGAS".
 						8. Verify that billed Usage is now displayed in "All Usage Report - SGAS"..
 
 Version Changes 2.0:	1. Removing Report Report 'Revenue Recognition Management Detail Report by Accounting Period - SGAS'
 						and adding it to TC with name Cls_ValidateDailyTieOutReport_Batch.
 						2. This TC will be run next day after the Invoice has been generated for Current Day. 
 						3. Currently generated Invoice information will be stored in an excel file and then used next day
 						by Cls_ValidateDailyTieOutReport_Batch.
 						
 Version Changes 2.1:	1. Updated the BeforeTest to BeforeClass. 						
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/


package testCases.Reports;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

import pageObjects.ValidateDailyTieOutReport;
import pageObjects.VerifyGLLocationSegment;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Invoices;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import utility.CsvHandler;
import utility.EnvironmentDetails;
import utility.Log;
import utility.ReportUtils;
import utility.Utils;
import utility.VerificationMethods;
import utility.WriteFiles;

public class Cls_ValidateDailyTieOutReport extends VerificationMethods 
{
    public static WebDriver driver;
    public static String strTestCaseName = "Verify Daily TieOut Report";
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
	@Test(dataProvider = "objectTestData", description = "Verify Daily TieOut Report")
    public void fn_VerifyReports(String strPlanName, String strAccountNum, String strAPIURL, String strFileName,
    		String strInvoiceDetailsFileName,	String strInvoiceDetailsWorksheet) throws Exception
	{
		//TODO: Create objects to classes for page objects to access the items under it.
		AccountFunctions objAccountFunc = new AccountFunctions();	// for methods relating to Accounts functionality.
		AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
		CsvHandler objCSV = new CsvHandler();	//CSV file to be read for data comparison.
		AccountFunctions_Invoices objAcctFuncInvoices = new AccountFunctions_Invoices();
		VerifyGLLocationSegment objGLLocation = new VerifyGLLocationSegment();
		ValidateDailyTieOutReport objDailyTieOut = new ValidateDailyTieOutReport();
		ReportUtils objReportUtils = new ReportUtils();
		WriteFiles objWriteFiles = new WriteFiles();
		Utils objUtils = new Utils();
		
		Boolean blnAdjustBillingDates = false;
		Boolean blnInvoiceGenerated = false;		
		Boolean blnValidateAllUnbilledBI = false;
		Boolean blnValidateRevRecogAcctPeriodBI = false;
		Boolean blnValidateAllUsageRerportBI = false;		
		Boolean blnValidateAllUnbilledAI = false;
		Boolean blnValidateAllUsageRerportAI = false;
		Boolean blnReportsValidated = false;
		
		List<List<NameValuePair>> lstpairReadCSV = new ArrayList<List<NameValuePair>>();
		List<List<NameValuePair>> lstpairReadInvoice = new ArrayList<List<NameValuePair>>();
		WebElement webReportContents = null;
		String strInvoiceNumber = null;
		String strUsagePeriod = null;
		String strTermStartDate = null;
		String strTermEndDate = null;
		String strParentWindow = null;
		String[] arrUsagePeriod = null;
		String[] arrMonthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		
		//TODO: Log the beginning of the test case.
    	Log.startTestCase(strTestCaseName);
    	
    	try
    	{    		  		
    		//TODO: Get the Supplemental Plan Number for the Plan name requested.
       		String strPlanNumber = objAcctFuncPlans.fn_GetPlans(driver, webWait, strPlanName, strTestCaseName);       		

       		//TODO: Verify if the Supplemental Plans has been assigned to the Account Number, else assign it.
			Boolean blnSupplementalPlansStatus = false; 
			blnSupplementalPlansStatus = objAcctFuncPlans.fn_VerifySupplementalPlans(driver, webWait, strAccountNum, strTestCaseName);
       		if(blnSupplementalPlansStatus == false)
       		{
       			objAcctFuncPlans.fn_AssignSuppPlan(driver, webWait, strAccountNum, strPlanNumber, strAPIURL);
       			Log.info("Supplemental Plan with number "+strPlanNumber+" has been assigned to the Account Number: "+strAccountNum);
       		}
    		
       		blnAdjustBillingDates = objAccountFunc.fn_AdjustAcctBillDates(driver, webWait, strAPIURL, strAccountNum, strTestCaseName);
       		if(blnAdjustBillingDates == false)
           		assertTrue(false, "ERROR: Since the Billing Date's can't be adjusted, Invoicing will fail, so exiting TC !");
       		
    		//TODO: Load the usage to the account number.
       		String LOAD_USAGE_FILE_PATH = System.getProperty("user.dir")
                    + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
                    + System.getProperty("file.separator") + strFileName;           	
       		String strLoadDataDate = objAccountFunc.fn_getLoadDataDate();
           	Log.info("Loading Account Usage for the date '"+strLoadDataDate+"' from file path: "+LOAD_USAGE_FILE_PATH.toString());
         	objAccountFunc.fn_LoadAccountUsage(driver, webWait, strAPIURL, LOAD_USAGE_FILE_PATH, strAccountNum, strLoadDataDate);
           	
           	//Read the data from CSV which has been used to load datas.
           	lstpairReadCSV = objCSV.readCSV(LOAD_USAGE_FILE_PATH);	// read the CSV file for data comparison.
           	           	
    		//TODO: Validate the 'All Unbilled Usage Report - SGAS' for existence of the unbilled data.
           	strParentWindow = driver.getWindowHandle();
           	objReportUtils.fn_GenerateReportCalendarDate(driver, webWait, "SGAS", "All Unbilled Usage Report - SGAS", strLoadDataDate, strLoadDataDate);
           	Thread.sleep(5000);
    		webReportContents = objReportUtils.fn_ReadGeneratedReport(driver);//table body has been received here.
           	Thread.sleep(2000);
    		Utils.takeScreenshot(driver, strTestCaseName);
    		blnValidateAllUnbilledBI = fn_ValidateAllUnbilledUsage(webReportContents, strAccountNum, lstpairReadCSV, strLoadDataDate);
           	objReportUtils.fn_HandleReportWindows(driver, strParentWindow);	// call window handler for closing Report windows/frame.
           	objGLLocation.fn_clickReports(driver, webWait).click();
           	Thread.sleep(3500);           	
           	if(blnValidateAllUnbilledBI == true)
           	{
           		blnReportsValidated = true;
           		Log.info("The verification for 'All Unbilled Usage Report - SGAS' for existence of the unbilled data is successfully completed !");
           	}           		
           	else
           	{
           		blnReportsValidated = false;
           		Log.error("ERROR: The verification failed for 'All Unbilled Usage Report - SGAS' due to non-existence of the unbilled data !");
           		Reporter.log("ERROR: The verification failed for 'All Unbilled Usage Report - SGAS' due to non-existence of the unbilled data !");
           	}

           	//TODO: Validate the Report 'Revenue Recognition Management Detail Report by Accounting Period - SGAS' for non-existence of the unbilled data.
           	strParentWindow = driver.getWindowHandle();
           	objReportUtils.fn_GenerateReportCalendarDate(driver, webWait, "SGAS", "Revenue Recognition Management Detail Report by Accounting Period - SGAS", strLoadDataDate, strLoadDataDate);
           	Thread.sleep(10000);
    		webReportContents = objReportUtils.fn_ReadGeneratedReport(driver);
           	strTermStartDate = objUtils.fn_getPreviousDate(1, 1);
           	strTermEndDate = Utils.getDateYearFirst();
           	Thread.sleep(2000);           	
           	Utils.takeScreenshot(driver, strTestCaseName);
           	blnValidateRevRecogAcctPeriodBI = fn_ValidateRevRecogMgmtDetail(webReportContents, strAccountNum, lstpairReadInvoice, "", strLoadDataDate, strLoadDataDate);
           	objReportUtils.fn_HandleReportWindows(driver, strParentWindow);	// call window handler for closing Report windows/frame.
           	objGLLocation.fn_clickReports(driver, webWait).click();
           	Thread.sleep(3500);
           	if(blnValidateRevRecogAcctPeriodBI == false)
           	{
           		blnReportsValidated = true;
               	Log.info("The verification for 'Revenue Recognition Management Detail Report by Accounting Period - SGAS' for non-existence of the unbilled data is successfully completed !");
           	}           		
           	else
           	{
           		blnReportsValidated = false;
           		Log.error("ERROR: The verification failed for 'Revenue Recognition Management Detail Report by Accounting Period - SGAS' for existence of the unbilled data !");
           		Reporter.log("ERROR: The verification failed for 'Revenue Recognition Management Detail Report by Accounting Period - SGAS' for existence of the unbilled data !");
           	}

    		//TODO: Validate the Report 'All Usage Report - SGAS' for non-existence of the unbilled data.
    		objReportUtils.fn_GenerateReportCalendarDate(driver, webWait, "SGAS", "All Usage Report - SGAS", strLoadDataDate, strLoadDataDate);
           	Thread.sleep(5000);
    		webReportContents = objReportUtils.fn_ReadGeneratedReport(driver);
           	Thread.sleep(2000);
           	Utils.takeScreenshot(driver, strTestCaseName);
    		blnValidateAllUsageRerportBI = fn_ValidateAllUsage(webReportContents, strAccountNum, strInvoiceNumber, lstpairReadCSV, strLoadDataDate);
    		objReportUtils.fn_HandleReportWindows(driver, strParentWindow);	// call window handler for closing Report windows/frame.
    		objGLLocation.fn_clickReports(driver, webWait).click();
    		Thread.sleep(3500);
    		if(blnValidateAllUsageRerportBI == true)
    		{
    			blnReportsValidated = true;
    			Log.info("The verification for 'All Usage Report - SGAS' for non-existence of the unbilled data is successfully completed !");    			
    		}
           	else
           	{
           		blnReportsValidated = false;
           		Log.error("ERROR: The verification failed for 'All Usage Report - SGAS' for existence of the unbilled data !");
           		Reporter.log("ERROR: The verification failed for 'All Usage Report - SGAS' for existence of the unbilled data !");           		
           	}


    		//TODO: Now account the Unbilled usage by generating Invoice, Approving it & generating Statement for the Account under test. 
    		blnInvoiceGenerated = objAcctFuncInvoices.fn_GenerateInvoice(driver, webWait, strAccountNum, "Advanced");
    		Utils.takeScreenshot(driver, strTestCaseName);
           	if(blnInvoiceGenerated == true)
           	{
        		WebElement webInvoicesDetails = objGLLocation.fn_getInvoicesDataTable(driver, webWait).findElement(By.tagName("tbody"));
        		List<WebElement> lstwebReadInvoicesRow = webInvoicesDetails.findElements(By.tagName("tr"));
        		for(WebElement row: lstwebReadInvoicesRow)// logic to get the Invoice Number.
        		{
        			List<WebElement> cols = row.findElements(By.tagName("td"));
        			
        			if(cols.size() >= 2)
        			{
        				strInvoiceNumber = cols.get(2).getAttribute("innerText").toString().trim();
        				strUsagePeriod = cols.get(5).getAttribute("innerText").toString().trim();
        				break;
        			}
        		}           		
           		if(strInvoiceNumber == null)
           			assertTrue(false, "ERROR: Invoice was generated, but the Invoice Number couldn't be retrieved !");

           		objAcctFuncInvoices.fn_ApproveAdvancedInvoices(driver, webWait);
           		Thread.sleep(1500);
           		Utils.takeScreenshot(driver, strTestCaseName);
           		objDailyTieOut.fn_clickAccounts(driver, webWait).click();
           		objAcctFuncInvoices.fn_GenerateInvoiceStatement(driver, webWait, strTestCaseName);
           		objDailyTieOut.fn_clickAccounts(driver, webWait).click();
           	}
           	else
           		assertTrue(false, "Invoice couldn't be generated, hence exiting as second part of validations cannot be done !");   		
           		
    		objAcctFuncInvoices.fn_SelectLatestInvoice(driver, webWait);
    		lstpairReadInvoice = objAcctFuncInvoices.fn_ReadInvoiceDetails(driver, webWait);
    		Log.info("Contents of Invoice are: "+lstpairReadInvoice.toString());
           	
    		//TODO: Prepare the String list of items to be written into the file later referred by Batch TC.
           	//Get the Usage Period dates in "yyyy-mm-dd" for which the report is to be generated.    		
           	arrUsagePeriod = strUsagePeriod.split("-");
           	strTermStartDate = arrUsagePeriod[2].toString().replace(" ", "") + "-" 
           						+ (Arrays.asList(arrMonthNames).indexOf(arrUsagePeriod[1].toString().replace(" ", ""))+1) 
           							+"-" + arrUsagePeriod[0].toString().replace(" ", "");
           	strTermEndDate = arrUsagePeriod[5].toString().replace(" ", "") + "-" 
    							+ (Arrays.asList(arrMonthNames).indexOf(arrUsagePeriod[4].toString().replace(" ", ""))+1) 
    								+"-" + arrUsagePeriod[3].toString().replace(" ", "");
           	String strInvoiceDate = Utils.getDateYearFirst();
           	
           	List<String> lstFileContents = new ArrayList<>();           	
           	lstFileContents.add(strAccountNum);
           	lstFileContents.add(strInvoiceNumber);
           	lstFileContents.add(strInvoiceDate);
           	lstFileContents.add(strTermStartDate);
           	lstFileContents.add(strTermEndDate);
           	
           	String strExcelFile = System.getProperty("user.dir")
					+ System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
					+ System.getProperty("file.separator") + strInvoiceDetailsFileName;
           	
           	objWriteFiles.fn_AppendExcelFile(strExcelFile, strInvoiceDetailsWorksheet, lstFileContents);           	

    		//TODO: Validate the 'All Unbilled Usage Report - SGAS' for non-existence of the billed data.
           	strParentWindow = driver.getWindowHandle();
           	webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Analytics and Reporting"))).click();
           	objReportUtils.fn_GenerateReportCalendarDate(driver, webWait, "SGAS", "All Unbilled Usage Report - SGAS", strLoadDataDate, strLoadDataDate);
           	Thread.sleep(5000);
    		webReportContents = objReportUtils.fn_ReadGeneratedReport(driver);//table body has been received here.
           	Thread.sleep(2000);
           	Utils.takeScreenshot(driver, strTestCaseName);
    		blnValidateAllUnbilledAI = fn_ValidateAllUnbilledUsage(webReportContents, strAccountNum, lstpairReadCSV, strLoadDataDate);
           	objReportUtils.fn_HandleReportWindows(driver, strParentWindow);	// call window handler for closing Report windows/frame.
           	objGLLocation.fn_clickReports(driver, webWait).click();           	
           	Thread.sleep(3500);
           	if(blnValidateAllUnbilledAI == false)
           	{
           		blnReportsValidated = true;
           		Log.info("The verification for 'All Unbilled Usage Report - SGAS' for non-existence of the billed data is successfully completed !");
           	}    			
           	else
           	{
           		blnReportsValidated = false;
           		Log.error("ERROR: The verification failed for 'All Unbilled Usage Report - SGAS' for existence of the billed data !");
           		Reporter.log("ERROR: The verification failed for 'All Unbilled Usage Report - SGAS' for existence of the billed data !");
           	}           	
           	
    		//TODO: Validate the Report 'All Usage Report - SGAS' for existence of the billed data.
    		objReportUtils.fn_GenerateReportCalendarDate(driver, webWait, "SGAS", "All Usage Report - SGAS", strLoadDataDate, strLoadDataDate);
           	Thread.sleep(5000);
    		webReportContents = objReportUtils.fn_ReadGeneratedReport(driver);
           	Thread.sleep(2000);
           	Utils.takeScreenshot(driver, strTestCaseName);
    		blnValidateAllUsageRerportAI = fn_ValidateAllUsage(webReportContents, strAccountNum, strInvoiceNumber, lstpairReadCSV, strLoadDataDate);
    		objReportUtils.fn_HandleReportWindows(driver, strParentWindow);	// call window handler for closing Report windows/frame.
           	objGLLocation.fn_clickReports(driver, webWait).click();
    		Thread.sleep(3500);
    		if(blnValidateAllUsageRerportAI == true)
    		{
    			blnReportsValidated = true;
    			Log.info("The verification for 'All Usage Report - SGAS' for existence of the billed data is successfully completed !");
    		}
           	else
           	{
           		blnReportsValidated = false;
           		Log.error("ERROR: The verification failed for 'All Usage Report - SGAS' for non-existence of the billed data !");
           		Reporter.log("ERROR: The verification failed for 'All Usage Report - SGAS' for non-existence of the billed data !");           		
           	}
    		
    		//TODO: Set the final status of the TC.
    		if(blnReportsValidated == false)
    		{
    			Log.error("ERROR: This Daily Tie Out Reports TC failed due to a failure in Report verification..");
    			Reporter.log("ERROR: This Daily Tie Out Reports TC failed due to a failure in Report verification..");
    			assertTrue(false, "ERROR: This Daily Tie Out Reports TC failed due to a failure in Report verification..");
    		}
    	}
    	catch (Exception exception)
    	{
    		Utils.takeScreenshot(driver, strTestCaseName);
    		Log.error("ERROR: Verification for the Daily Tie-Out Report's couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Verification for the Daily Tie-Out Report's couldn't be done due to exception:  "+exception.toString());
    		throw exception;
    	}
    	catch (AssertionError exception)
    	{
    		Utils.takeScreenshot(driver, strTestCaseName);
    		Log.error("ERROR: Verification for the Daily Tie-Out Report's couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Verification for the Daily Tie-Out Report's couldn't be done due to exception:  "+exception.toString());
    		throw exception;
    	}

    	//TODO: Log the end of the test case. 
    	Log.endTestCase(strTestCaseName);		
	}


/*
	Function: fn_ValidateAllUnbilledUsage - To Validate the 'All Unbilled Usage Report - SGAS' returning boolean status.
	Inputs: Report Table as a Webelement, the CSV data used for generating usage & Load date as String.
	Output: Boolean Status after validation.
	
	Validated on Basis of: AccountNo, UsageTypeCode, UsageUnits, UsageDate-DataLoaddate, Updatedate - DateWhen TC exec,  
*/	
	
	public Boolean fn_ValidateAllUnbilledUsage(WebElement webReportContents, String strAccountNum, 
			List<List<NameValuePair>> lstpairReadCSV, String strLoadDataDate) throws Exception
	{
		//TODO: Create objects to classes for page objects to access the items under it.
		Utils objUtils = new Utils();
		
		Boolean blnUnbilledUsageValidated = false;
		String strCurrentDate = Utils.getDateYearFirst();
		
		//TODO: Now compare each line item in the report for specific Account with the loaded CSV for usage data.
		List<WebElement> lstwebReportContentsRows = webReportContents.findElements(By.tagName("tr"));
		List<WebElement> lstwebReportContentsRowsSL = lstwebReportContentsRows.subList(2, lstwebReportContentsRows.size());
       	for(List<NameValuePair> lstCSV : lstpairReadCSV)	// traverse through read CSv to compare data displayed.
       	{
       		String[] strUsageTypeValue = lstCSV.get(0).toString().split("=");
       		String[] strBillableUnitsValue = lstCSV.get(5).toString().split("=");
       			strBillableUnitsValue[1] = objUtils.fn_ConvertStringToBigDecimal(strBillableUnitsValue[1]);
       	   	Integer intRowsIterator = 0;
   			
       		for(WebElement rows: lstwebReportContentsRowsSL)
       		{
       			List<WebElement> cols = rows.findElements(By.tagName("td"));	//read the rows of the table
       			intRowsIterator += 1;

       			//comparison logic starts now...
       			if(cols.size() >= 2)	// this is to avoid the header read in the loop.
       			{
       				String strReportUsageType = cols.get(10).getAttribute("innerText").toString().trim();
       				String strReportUsageUnits = cols.get(12).getAttribute("innerText").toString().trim();
       					strReportUsageUnits = objUtils.fn_ConvertStringToBigDecimal(strReportUsageUnits);
       				String strReportAcctNum = cols.get(5).getAttribute("innerText").toString().trim();
       				String strReportUsageDate = cols.get(13).getAttribute("innerText").toString().trim();
       				String strReportUpdateDate = cols.get(14).getAttribute("innerText").toString().trim();       				

       				if(strAccountNum.contentEquals(strReportAcctNum))
       					if(strUsageTypeValue[1].contentEquals(strReportUsageType) && strBillableUnitsValue[1].contentEquals(strReportUsageUnits))
       						if(strReportUsageDate.contains(strLoadDataDate) && strReportUpdateDate.contains(strCurrentDate))
			       				{
			       					Log.info("The All Unbilled Usage Report is verified for data of Usage Type: "+strUsageTypeValue[1].toString()+" with rate: "
										+strBillableUnitsValue[1].toString()+" for Account: "+strAccountNum);
			       					blnUnbilledUsageValidated = true;
			       					break;
			       				}
       			}
       			
       			if(!(cols.iterator().hasNext()))
    			{
       				Log.error("ERROR: There is no data generated in the Report !");
       				Reporter.log("ERROR: There is no data generated in the Report !");
       				blnUnbilledUsageValidated = false;
    			}
       			
       			//TODO: If entire table for the Unbilled Report has been compared for the CSV file's read line, exit with comments.
       			if(lstwebReportContentsRowsSL.size() == intRowsIterator)
       			{
    				Log.error("ERROR: The All Unbilled Usage Report has no data for the Usage Type: "+strUsageTypeValue[1].toString()+" with Billable Units: "
    						+strBillableUnitsValue[1].toString()+" for Account: "+strAccountNum);
    				Reporter.log("ERROR: The All Unbilled Usage Report has no data for the Usage Type: "+strUsageTypeValue[1].toString()+" with Billable Units: "
    						+strBillableUnitsValue[1].toString()+" for Account: "+strAccountNum);
    				blnUnbilledUsageValidated = false;
       			}
       		}
       	}
		
		return blnUnbilledUsageValidated;
	}
	

/*
	Function: fn_ValidateRevRecogMgmtDetail - To Validate the 'Revenue Recognition Management Detail Report by Accounting Period - SGAS' returning boolean status.
	Inputs: Report Table as a Webelement, 
	Output: Boolean Status after validation.
	
	Validated on Basis of: AccountNo, Invoice Details, Booking Date & Usage Period.  
*/	
	
	public Boolean fn_ValidateRevRecogMgmtDetail(WebElement webReportContents, String strAccountNum, List<List<NameValuePair>> lstpairReadInvoice
					, String strInvoiceNum, String strStartDate, String strEndDate) throws Exception
	{		
		Boolean blnRevRecogMgmtDtlValidated = false;
		String strCurrentDate = Utils.getDateYearFirst();
		
		//TODO: Now compare each line item in the report for specific Account's latest generated Invoice.
		List<WebElement> lstwebReportContentsRows = webReportContents.findElements(By.tagName("tr"));		
		List<WebElement> lstwebReportContentsRowsSL = lstwebReportContentsRows.subList(2, lstwebReportContentsRows.size());
		
		if(strInvoiceNum.contentEquals(""))	// Initial case for verifying Report before Invoice generation...
		{		
       		for(WebElement rows: lstwebReportContentsRowsSL)
       		{
       			List<WebElement> cols = rows.findElements(By.tagName("td"));	//read the rows of the table

       			//comparison logic starts now...
       			if(cols.size() >= 2)	// this is to avoid the header read in the loop.
       			{
       				String strReportAccountNum = cols.get(10).getAttribute("innerText").toString().trim();
       				String strReportItemNum = cols.get(13).getAttribute("innerText").toString().trim();
       				String strReportBookingDate = cols.get(15).getAttribute("innerText").toString().trim();
       				String strReportTermStartDate = cols.get(16).getAttribute("innerText").toString().trim();
       				String strReportTermEndDate = cols.get(17).getAttribute("innerText").toString().trim();
       				
       				if(strAccountNum.contentEquals(strReportAccountNum))
       					if(strReportBookingDate.contains(strCurrentDate) && strReportTermStartDate.contains(strStartDate) && strReportTermEndDate.contains(strEndDate))
       						if(strReportItemNum.contentEquals(""))
       							{
       								blnRevRecogMgmtDtlValidated = true;
       								return blnRevRecogMgmtDtlValidated;
       							}
       			}
       			
       			if(!(cols.iterator().hasNext()))
    			{       				
       				Log.info("There is no data generated in the Report !");
       				blnRevRecogMgmtDtlValidated = false;
       				return blnRevRecogMgmtDtlValidated;
    			}
       		}
       		//TODO: If entire table for the Report has been traversed w/o data, so report test successful.
       		Log.info("The Revenue Recognition Management Details Report has no data for the Account: "+strAccountNum+" with Booking Date as: "
					+strCurrentDate+" for Usage Period: "+strStartDate+" - "+strEndDate);
			blnRevRecogMgmtDtlValidated = false;
			return blnRevRecogMgmtDtlValidated;
		}
			
		return blnRevRecogMgmtDtlValidated;
	}	


/*
	Function: fn_ValidateAllUsage - To Validate the 'All Usage Report - SGAS' returning boolean status.
	Inputs: Report Table as a Webelement, the CSV data used for generating usage & Load date as String.
	Output: Boolean Status after validation.
	
	Validated on Basis of: AccountNo, InvoiceNum, UsageTypeCode, UsageUnits, UsageDate-DataLoaddate, Updatedate - DateWhen TC exec,  
*/	
	
	public Boolean fn_ValidateAllUsage(WebElement webReportContents, String strAccountNum, String strInvoiceNum,
			List<List<NameValuePair>> lstpairReadCSV, String strLoadDataDate) throws Exception
	{
		//TODO: Create objects to classes for page objects to access the items under it.
		Utils objUtils = new Utils();	
		
		Boolean blnBilledUsageValidated = false;
		String strCurrentDate = Utils.getDateYearFirst();
		
		//TODO: Now compare each line item in the report for specific Account with the loaded CSV for usage data.
		List<WebElement> lstwebReportContentsRows = webReportContents.findElements(By.tagName("tr"));
		List<WebElement> lstwebReportContentsRowsSL = lstwebReportContentsRows.subList(2, lstwebReportContentsRows.size());
       	for(List<NameValuePair> lstCSV : lstpairReadCSV)	// traverse through read CSv to compare data displayed.
       	{
       		String[] strUsageTypeValue = lstCSV.get(0).toString().split("=");
       		String[] strBillableUnitsValue = lstCSV.get(5).toString().split("=");
       			strBillableUnitsValue[1] = objUtils.fn_ConvertStringToBigDecimal(strBillableUnitsValue[1]);
       	   	Integer intRowsIterator = 0;       			
   			
       	   	if(strInvoiceNum == null)
       	   		strInvoiceNum = "";
       	   	
       		for(WebElement rows: lstwebReportContentsRowsSL)
       		{
       			List<WebElement> cols = rows.findElements(By.tagName("td"));	//read the rows of the table
       			intRowsIterator += 1;

       			//comparison logic starts now...
       			if(cols.size() >= 2)	// this is to avoid the header read in the loop.
       			{
       				String strReportInvoiceNum = cols.get(3).getAttribute("innerText").toString().trim();
       				String strReportAcctNum = cols.get(5).getAttribute("innerText").toString().trim();       				
       				String strReportUsageType = cols.get(10).getAttribute("innerText").toString().trim();
       				String strReportUsageUnits = cols.get(12).getAttribute("innerText").toString().trim();
       					strReportUsageUnits = objUtils.fn_ConvertStringToBigDecimal(strReportUsageUnits);
       				String strReportUsageDate = cols.get(13).getAttribute("innerText").toString().trim();
       				String strReportUpdateDate = cols.get(14).getAttribute("innerText").toString().trim();

       				if(strAccountNum.contentEquals(strReportAcctNum) && strInvoiceNum.contentEquals(strReportInvoiceNum))
       					if(strUsageTypeValue[1].contentEquals(strReportUsageType) && strBillableUnitsValue[1].contentEquals(strReportUsageUnits))
       						if(strReportUsageDate.contains(strLoadDataDate) && strReportUpdateDate.contains(strCurrentDate))
			       				{
			       					if(strInvoiceNum.equals(""))
			       						strInvoiceNum = "Empty";
       								Log.info("The All Usage Report is verified for data of Usage Type: "+strUsageTypeValue[1].toString()+" with rate: "
										+strBillableUnitsValue[1].toString()+" for Account: "+strAccountNum+" in Invoice Number: "+strInvoiceNum);
			       					blnBilledUsageValidated = true;
			       					break;
			       				}
       			}
       			
       			if(!(cols.iterator().hasNext()))
    			{
       				Log.error("ERROR: There is no data generated in the Report !");
       				Reporter.log("ERROR: There is no data generated in the Report !");
       				blnBilledUsageValidated = false;
    			}
       			
       			//TODO: If entire table for the All Billed Usage Report has been compared for the CSV file's read line, exit with comments.
       			if(lstwebReportContentsRowsSL.size() == intRowsIterator)
       			{
       				if(strInvoiceNum.equals(""))
   						strInvoiceNum = "Empty";
    				Log.error("ERROR: The All Usage Report has no data for the Usage Type: "+strUsageTypeValue[1].toString()+" with Billable Units: "
    						+strBillableUnitsValue[1].toString()+" for Account: "+strAccountNum+"'s Invoice Number: "+strInvoiceNum);
    				Reporter.log("ERROR: The All Usage Report has no data for the Usage Type: "+strUsageTypeValue[1].toString()+" with Billable Units: "
    						+strBillableUnitsValue[1].toString()+" for Account: "+strAccountNum+"'s Invoice Number: "+strInvoiceNum);
    				blnBilledUsageValidated = false;
       			}
       		}
       	}
		
		return blnBilledUsageValidated;
	}	
	
  	//TODO: Reading data for the test    
  	@DataProvider(name = "objectTestData")
  	public Object[][] testExcelData() throws DataDrivenFrameworkException
  	{
  	    Utils objUtils=new Utils();
  	    return objUtils.data1("TestCaseDetails", "DailyTieOut");
  	}
  	
  	
  	@AfterTest
  	public void afterMethod() throws Exception
  	 {
  		 //TODO: Logs out of the application & quit the driver
  		 AriaLoginLogout.appLogout(driver);
  		 driver.quit(); 
  	 }    
}