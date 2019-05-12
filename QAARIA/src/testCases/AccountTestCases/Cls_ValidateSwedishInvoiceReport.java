/*
 Author     		:	Nashim Khan
 
 Class Name			: 	Cls_ValidateSwedishInvoiceReport  
 Purpose     		: 	Purpose of this file is :
						To validate the reports after void an Invoice performs for Swedish account.
												
 Date       		:	29/06/2016

 Version Information:	Version 1.0
 
 Jira #				:	QAABE-629
 
 PreCondition 		:	1. Swedish account must exist 
						2. An invoice for a Swedish account must be voided
 						worksheet for excel "TestData.xlsx".
 						3. Accounts to be created and updated in the test data sheet.

 Test Steps 		:	1. Search for a Swedish Account 
						2. Void an invoice 
						3. Run a report with parameters that contains a voided invoice 
						4. Observe the columns from the report
						
 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AccountTestCases;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AccountsPaymentsCredits;
import pageObjects.AccountsStatementsInvoices;
import pageObjects.AriaEOM;
import pageObjects.VerifyGLLocationSegment;
import utility.EnvironmentDetails;
import utility.Log;
import utility.ReadFiles;
import utility.ReportUtils;
import utility.Utils;
import utility.VerificationMethods;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Invoices;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_ValidateSwedishInvoiceReport extends VerificationMethods{
	
	public static WebDriver driver;
    public static String[] arrSearchFields = null;	// this string array will hold the Fields names for the Client Defined Fields.
    public static String strFieldValue = null; 	// this string will hold the subsequent field value for the Client Defined Fields.
	public static WebDriverWait webWait;
    public static WebElement webElement;
    
    String strTestCaseName ="ValidateSwedishInvoiceReport";
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
	
    @Test(dataProvider = "objectTestData1" ,description="ValidateSwedishInvoiceReport")
    public void fn_ValidateSwedishInvoiceReport(String strFileName ,String strWorksheet,String strAccountNumber, String strMPI , String strSPI, 
    		String strAPIURL, String strUsageFilename, String strInvoiceType) {
	  
    	//TODO : Create Objects 
    	AccountFunctions objAcctFnctns = new AccountFunctions();
		AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
		AccountFunctions_Invoices objAcctFuncInvoices = new AccountFunctions_Invoices();
		Utils objUtils=new Utils();
		AriaEOM objAriaEOM = new AriaEOM();
		AccountsPaymentsCredits  objAccountPayCredits=new AccountsPaymentsCredits();
		AccountsStatementsInvoices  objAccountStatInvoice=new AccountsStatementsInvoices();
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAcct = new Cls_ChangeDeleteClientDefinedFieldActns();
		ReportUtils objReportUtils=new ReportUtils();
		ReadFiles objReadFiles=new ReadFiles();
		VerifyGLLocationSegment objVerifyGLLocationSegment=new VerifyGLLocationSegment();
		
		Boolean blnDataVerified = false;
		 Boolean blnDataRowVerified=true;
		String strPendingInvDate ="";
		Log.startTestCase(strTestCaseName);
		try {
			
			/*//TODO: Load Data for specified account for current date.
			String LOAD_USAGE_FILE_PATH = System.getProperty("user.dir")
	             + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
	             + System.getProperty("file.separator") + strFilename;
			
			//TODO : Adjust Billing dates for the Account to Generate Invoice  	
	   		boolean blnAdjustDate=objAcctFnctns.fn_AdjustAcctBillDates(driver, webWait, strAPIURL, strAccountNumber,strMPI, 
	   											strInvoiceType, strPendingInvDate,strTestCaseName);				
			if (blnAdjustDate==true)
				 Log.info("Account Billing Date has been set successfully");			
			else {
				
				Log.info("ERROR: Account Billing Date has not been set");
			
			}
			
			//TODO: Fetching the Date for Loading Usage	
	    	String strLoadDataDate=objUtils.fn_getLoadDataDate();
	    	Log.info("Loading Account Usage for the date '"+strLoadDataDate+"'..");	           	
	    	Thread.sleep(200);
	           	
	    	//TODO: Generate MPID and SPID
	    	String strMPInstance = objAcctFuncPlans.fn_GetPlanInstanceID(driver, webWait, strAccountNumber, strTestCaseName, strMPI);
	    	String[] arrMPID = strMPInstance.split("#");
	    	String strSPInstance = objAcctFuncPlans.fn_GetPlanInstanceID(driver, webWait, strAccountNumber, strTestCaseName, strSPI);
	    	String[] arrSPID = strSPInstance.split("#"); 
	    	String strOptionType=arrMPID[0]+" - "+strMPI; 
	    	
	    	//TODO: Loading the Usage		    			    	
	    	objAcctFnctns.fn_LoadAccountUsage(driver, webWait, strAPIURL,LOAD_USAGE_FILE_PATH, strAccountNumber, strLoadDataDate,arrMPID[1], arrSPID[1]);
	    	
	    	//TODO: Advance Invoice for the Account for which data is currently uploaded & Verify Print Invoice.	           
	    	boolean blnInvoiceGenerated = objAcctFuncInvoices.fn_GenerateInvoice(driver, webWait, strAccountNumber,strInvoiceType,"MPI",strOptionType, strTestCaseName);
	       	if(blnInvoiceGenerated == true) {
	       		
	   		    if(strInvoiceType.equalsIgnoreCase("Immediate")) {	
	   		    	Log.info("Invoice type 'Immediate' is generated; approval not required !"); 
	   		    	//Since Invoice has been generated and Approved, we generate the Statement.
	   		    	objAriaEOM.fn_clickAccounts(driver, webWait).click();
	   		    	objAcctFuncInvoices.fn_GenerateInvoiceStatement(driver, webWait, strOptionType, strTestCaseName);
	   		    }
	   		    else 
	   		    {
	   		    	boolean blnInvoiceApproved = objAcctFuncInvoices.fn_ApproveAdvancedInvoices(driver, webWait);
	   		    	Utils.takeScreenshot(driver, strTestCaseName);
	   		    	if(blnInvoiceApproved == false)
	   		    		Log.info("ERROR: Invoice generated couldn't be approved !");
	   		
	   		    	//Since Invoice has been generated and Approved, we generate the Statement.
	   		    	objAriaEOM.fn_clickAccounts(driver, webWait).click();
	   		    	objAcctFuncInvoices.fn_GenerateInvoiceStatement(driver, webWait, strOptionType, strTestCaseName);
	   		   }
	       	}
	       	
	       	//TODO : Select the Latest Invoice
	       	boolean blnLatestInvoiceSelected = objAcctFuncInvoices.fn_SelectLatestInvoice(driver, webWait);           	
	       	if(blnLatestInvoiceSelected == false) {
	
	       		Log.info("ERROR: Latest Invoice couldn't be selected !");
	   		
	       	}    	
	       	else       		
	       		Log.info("Latest Invoice is selected Successfully");       	
	       	Thread.sleep(2000);
	    
	       	// TODO : Void Invoice 
	       	objAriaEOM.fn_clickAccounts(driver, webWait).click();
	       	objClientDefAcct.AccountSearch(driver, webWait, strAccountNumber);
	       	//TODO : Select the Latest Invoice
	       	blnLatestInvoiceSelected = objAcctFuncInvoices.fn_SelectLatestInvoice(driver, webWait);           	
	       	if(blnLatestInvoiceSelected == false) {
	
	       		Log.info("ERROR: Latest Invoice couldn't be selected !");
	   		
	       	}    	
	       	else       		
	       		Log.info("Latest Invoice is selected Successfully");       	
	       	Thread.sleep(2000); 
	       	objAccountStatInvoice. fn_clickVoidInvoice(driver, webWait).click();
	       	Thread.sleep(5000);
	       	objAccountPayCredits.fn_clickConfirmationOk(driver, webWait).click();
	       	Thread.sleep(3000);
	       	String strVoidStatusMessage=objAccountPayCredits.fn_getStatusMessage(driver, webWait).getText();
	    
	       	Log.info("strVoidStatusMessage");
	       	if(strVoidStatusMessage.contains("successfully voided"))
	       	{
				objAriaEOM.fn_getDataTable(driver, webWait);
				Utils.takeScreenshot(driver, strTestCaseName);
			
				Log.info("The voiding process has been successfully completed  For Invoice Number!");
			
	       	}
	       	else{
	       		
	       		Log.info("The voiding process has failed!");
			 	Reporter.log("The voiding process has failed !");
	       	}*/
	       	Thread.sleep (1000);
	       	
	       	//TODO: Generate the Report and then proceed further for Validation.
	       	objReportUtils.fn_GenerateReport(driver, "Sungard AS", "Swedish Invoice Extract - SGAS A7", "Current Day", "Current Day");
	       	
	       	//TODO :Click on View Large Report Link
	       	objReportUtils.fn_clickViewReport(driver, objVerifyGLLocationSegment.fn_clickHereToViewLargeReport());
	       	
            //TODO : Navigate to new Window  and handle window
	 		Log.info("Get Main Window Handle");
	 		String Tab = driver.getWindowHandle();
	 		Log.info("Main tab : "+Tab);
	 		
	 		//TODO : Get the list of window handles
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
	       	
			//TODO : Switch to postedReportDisplay frame
			Log.info("Switch to postedReportDisplay frame");			
			driver.get(driver.findElement(By.name("postedReportDisplay")).getAttribute("src"));
			Thread.sleep(3000);
			
			//TODO : Read the web data and Grab data  to list 
	       	objVerifyGLLocationSegment.fn_geTableDetails(driver, webWait);
	       	List<String> lststrReportHeaderFieldsData = new ArrayList<String>();
	        WebElement webReportDetailTable = objVerifyGLLocationSegment.fn_geTableDetails(driver, webWait); 
	        WebElement webReportDetailTableDtl = objVerifyGLLocationSegment.fn_geTableDetail(driver, webWait); 
	     	List<WebElement> lstwebReportDtlTblHeader = webReportDetailTable.findElements(By.xpath("td"));
	     	List<WebElement> lstwebReportDtlTblVal = webReportDetailTableDtl.findElements(By.xpath("tr/td"));
	     	String strTodayDate = Utils.getCurrentDate();
	    	for(Integer intList = 0; intList < lstwebReportDtlTblHeader.size(); intList++)	    	
	    	{ 
	    		String strReportHeader= lstwebReportDtlTblHeader.get(intList).getText().toString().trim();
	    		lststrReportHeaderFieldsData.add(strReportHeader);
	    		
	    		String strVoidDate = lstwebReportDtlTblVal.get(17).getText().toString().trim();  
	    		if(strVoidDate.contains(strTodayDate)) 
	    		
	    			Log.info("Void Date is "+strVoidDate);
	    			    		
	    	}
	    	Log.info("Web  Data "+lststrReportHeaderFieldsData);	    	
	    
	    	//TODO : Read  Account Fields Data from Data Sheet .
			String strFilePath=BASE_TESTDATA_PATH+"\\"+strFileName;
			File filePath=new File(strFilePath);				
			List <String> lststrReportFieldsDataFile = ReadFiles.readExcelFileAsArray(filePath, strWorksheet);
			Thread.sleep(5000);
			Log.info("File   Data "+lststrReportFieldsDataFile);
			
			//TODO : Compare earlier read Web Data to  DataSheet
			Integer intFileData = 0;			
			for( intFileData = 0; intFileData < lststrReportFieldsDataFile.size(); intFileData ++) {							 		
				
					if(lststrReportFieldsDataFile.get(intFileData).equals(lststrReportHeaderFieldsData.get(intFileData))) {						 
					
						  Log.info("The Account Fields values are matching for Field Name ::"+lststrReportFieldsDataFile.get(intFileData));						 
					blnDataVerified = true;
					
				}
				if((blnDataVerified == false)) {
					
					Log.error("ERROR: The Account Fields values aren't matching for Field Name ::"+lststrReportFieldsDataFile.get(intFileData));
					Reporter.log("ERROR: The Account Fields values aren't matching for Field Name ::"+lststrReportFieldsDataFile.get(intFileData));
					blnDataRowVerified = false;
				
				}
			}
		} catch (Exception exception) {
			Log.error("ERROR: Test case for Terminate Plan Account   Fields failed with exception :"+exception.toString());
			Reporter.log("ERROR: Test case for Terminate Plan Account Fields failed with exception :"+exception.toString());
			exception.printStackTrace();
		}
    }
    @AfterClass
	public void afterMethod() throws Exception
	{
    	//TODO: Logs out of the application & quit the driver
		AriaLoginLogout.appLogout(driver);
		driver.quit(); 
	}


    //Reading data for the test 	
    @DataProvider(name = "objectTestData1")
    public Object[][] testExcelData1() throws DataDrivenFrameworkException
	 {
		 Utils objUtils=new Utils();
		 return objUtils.data1("TestCaseDetails", "ValidateSwedishInvoiceReport");
	 } 
}
