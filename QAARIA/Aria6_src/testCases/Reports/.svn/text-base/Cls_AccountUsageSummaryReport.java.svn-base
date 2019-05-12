/*
  Author     		:	Madhavi JN
 Class Name			: 	Cls_AccountUsageSummaryReport 
 Purpose     		: 	Purpose of this file is :
						1. To Verify that options of Test/Normal Accounts and ExcludeUsage in Account Usage Summary Report
						2. Test Class for the test case ABE-1116.
 
 Date       		:	09/02/2016 
 Version Information:	Version 1.0
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "AccountUsageSummaryReport" worksheet for excel "TestData.xls".
 						
 Test Steps 		:	1. Login using valid role credentials for accessing an account.
 						2. Search the TestAccount and Loadusage and select the options IncludeTestAccounts->Yes,ExcludeUsage->Yes
 						3. Verify that Unbilled Usage for Test Account is now displayed in "Account Usage Summary Report".
 						4. Generated Invoice for Normal Account and discard it.Set the options TestAccount->No and Exclude Usage->Yes in the Report.
 						5. Verify the Discarded Usage presence in the "Account Usage Summary Report" Report.
 						6. Set the options TestAccount ::Yes and Exclude Usage :: No in the Report.
 						7. Verify that Unbilled Usage for Test Account is now displayed in "Account Usage Summary Report".
 						8. Set the options TestAccount->No and Exclude Usage->No in the Report.
 						9. Verify that account and its corresponding usage for Normal Account should not be displayed in "Account Usage Summary Report".
 										
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.Reports;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.BatchProcessorReportObjects;
import pageObjects.VerifyGLLocationSegment;
import utility.CsvHandler;
import utility.EnvironmentDetails;
import utility.Log;
import utility.ReportUtils;
import utility.Utils;
import utility.VerificationMethods;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Invoices;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;
import appModules.Cls_Billing_Summary_Report;
import atu.ddf.exceptions.DataDrivenFrameworkException;

 public class Cls_AccountUsageSummaryReport extends VerificationMethods{
	
	    public static WebDriver driver;
	    public static String strTestCaseName = "Cls_AccountUsageSummaryReport";
		public static WebDriverWait webWait;
	    public static WebElement webElement;	
		
		@BeforeClass
		 public static void beforeMethod()throws Exception 
		 {	
				DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
				//TODO: Set Chrome driver as the driver to launch the test.
				driver = utility.Utils.fn_SetChromeDriverProperties();
				//TODO: initialize the webWait
				webWait = new WebDriverWait(driver, 100);
				EnvironmentDetails objEnv = new EnvironmentDetails();
				AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);
		 }
		
		@Test(dataProvider="objectTestData", description="AccountUsageSummaryReport")
		public void AccountUsageSummaryReport(String strplanname,String strAccountnum,String strAPIURL,String strFilename,String StrTestAcctNum){
			try{		
			    Log.startTestCase("Cls_AccountUsageSummaryReport");
			    boolean IsTestAcctFlag;
			    boolean IsExcludeUsageFlag;
			    boolean blnResult;
			    
		        int intLoop=0;
			    for(intLoop=1;intLoop<=4;intLoop++){
			    Thread.sleep(3500);
			    Utils.takeScreenshot(driver, strTestCaseName);
				//TODO:: click on Analytics and Reporting Link
				//objPage.fn_clickAnalyticsReporting(driver, webWait).click();
				Thread.sleep(1500);           
                    if (intLoop==1){                                      
                    //TODO:: TestAccount ::Yes and Exclude Usage :: Yes  
                    IsTestAcctFlag=	true;
                    IsExcludeUsageFlag=true;
                    blnResult = fn_ReadAccountUsage(strplanname, strAccountnum, strAPIURL, strFilename,IsTestAcctFlag,IsExcludeUsageFlag,StrTestAcctNum);
                    if(blnResult==true)
                    Log.info("Validations are successfully completed for the combination:: TestAccount ::Yes and Exclude Usage :: Yes");
                    else
                    Log.info("ERROR: Validations are not properly completed for the combination:: TestAccount ::Yes and Exclude Usage :: Yes");}
                    //TODO:: TestAccount ::No and Exclude Usage :: Yes
                    else if(intLoop==2){
                    IsTestAcctFlag=	false;
                    IsExcludeUsageFlag=true;
                    blnResult =fn_ReadAccountUsage(strplanname, strAccountnum, strAPIURL, strFilename,IsTestAcctFlag,IsExcludeUsageFlag,StrTestAcctNum);
                    if(blnResult==true)
                    Log.info("Validations are successfully completed for the combination:: TestAccount ::No and Exclude Usage :: Yes");
                    else
                    Log.info("ERROR: Validations are not properly completed for the combination:: TestAccount ::No and Exclude Usage :: Yes");}
                    //TODO:: TestAccount ::Yes and Exclude Usage :: No
                    else if(intLoop==3){
	                IsTestAcctFlag=true	;
	                IsExcludeUsageFlag=false;	
                    blnResult =fn_ReadAccountUsage(strplanname, strAccountnum, strAPIURL, strFilename,IsTestAcctFlag,IsExcludeUsageFlag,StrTestAcctNum);
                    if(blnResult==true)
                    Log.info("Validations are successfully completed for the combination:: TestAccount ::Yes and Exclude Usage :: No");
                    else
                    Log.info("ERROR: Validations are not properly completed for the combination:: TestAccount ::Yes and Exclude Usage :: No");}
                   //TODO:: TestAccount ::No and Exclude Usage :: No
                    else if(intLoop==4){
		            IsTestAcctFlag=false;
		            IsExcludeUsageFlag=false;
                    blnResult =fn_ReadAccountUsage(strplanname, strAccountnum, strAPIURL, strFilename,IsTestAcctFlag,IsExcludeUsageFlag,StrTestAcctNum);
                    if(blnResult==true)
                    Log.info("Validations are successfully completed for the combination:: TestAccount ::No and Exclude Usage :: No");
                    else
                    Log.info("ERROR: Validations are not properly completed for the combination:: TestAccount ::No and Exclude Usage :: No");}}}
			catch (Exception e){		
			e.printStackTrace();}}			
		
		//Function to Load Usage for the Test Account
		 public Boolean fn_ReadAccountUsage(String strplanname,String strAccountnum,String strAPIURL,String strFilename,boolean isTestAcctFlag,boolean IsExcludeUsageFlag,String StrTestAcctNum) throws Exception{

			    String strCSVUsageTypeCode=null;
			    String strCSVStatus=null;
			    boolean blnResult=false;
			    boolean ISCSVRead=false;
			    WebElement ReportContent=null;
			    
				AccountFunctions_Plans objAcctPlans = new AccountFunctions_Plans();
				AccountFunctions objAcctFns = new AccountFunctions();
			    Cls_Billing_Summary_Report objFunctions = new Cls_Billing_Summary_Report();
			    ReportUtils objReportUtils = new ReportUtils();
				CsvHandler objCsvHandler = new CsvHandler();
				//Objects for Accessing Account Functions
				AccountFunctions objfnctns = new AccountFunctions();
			    BatchProcessorReportObjects objPage = new BatchProcessorReportObjects();
				VerifyGLLocationSegment objGLLocation = new VerifyGLLocationSegment();
				
			    try{
	       		String LOAD_USAGE_FILE_PATH = System.getProperty("user.dir")
	                    + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
	                    + System.getProperty("file.separator") + strFilename;  
	    		String strCurrentDate = Utils.getDateYearFirst();
	    		String strEndDate= objfnctns.fn_getLoadDataDate();
			    
				if(IsExcludeUsageFlag==true && isTestAcctFlag==true){
	           			
				//TODO: Load Data for specified account for current date.
	       		String strPlanNumber = objAcctPlans.fn_GetPlans(driver, webWait, strplanname, strTestCaseName);
	        	if(strPlanNumber.contentEquals(""))
	       			throw new Exception("Supplemental Plan Number couldn't be retrieved !");
				boolean blnSupplementalPlansStatus = objAcctPlans.fn_VerifySupplementalPlans(driver, webWait, StrTestAcctNum, strTestCaseName);
	       		if(blnSupplementalPlansStatus == false){
	       			objAcctPlans.fn_AssignSuppPlan(driver, webWait, strAccountnum, strPlanNumber, strAPIURL);
	       			Log.info("Supplemental Plan with number "+strPlanNumber+" has been assigned to the Account Number: "+StrTestAcctNum);} }       		
	       		
	      		if (ISCSVRead==false){
				List<List<NameValuePair>> testdata = objCsvHandler.readCSV(LOAD_USAGE_FILE_PATH);
	       		
	       		for (List<NameValuePair> list : testdata){
	       			for (NameValuePair val : list){
	       				String strHeader = val.getName();
	       				if (strHeader.equalsIgnoreCase("usage_type_code"))	     					
	       					strCSVUsageTypeCode = val.getValue();
	       				ISCSVRead=true;}}}
	
	       		//Assigning testAccoyn num to acctnum fo rfiring the Usage
	       		if (isTestAcctFlag==true)
	       		objAcctFns.fn_LoadAccountUsage(driver, webWait, strAPIURL, LOAD_USAGE_FILE_PATH, StrTestAcctNum, strCurrentDate);
	       		//TODO:: Selection of specific probabilities for Accounts and Usage
	       		if (isTestAcctFlag==true && IsExcludeUsageFlag==true){
	        	//TODO:: TestAccount ::Yes and Exclude Usage :: Yes
	       		strCSVStatus="Unbilled";
	       		String strParentWindow = driver.getWindowHandle();
	       		ReportContent=objFunctions.fn_AccountUsageSummaryReport(driver, webWait,"test_acct_ind","1","exclude_ind","1",strEndDate,strCurrentDate);
	       		blnResult= fn_ReportValidation(ReportContent,isTestAcctFlag,IsExcludeUsageFlag,strCSVStatus,StrTestAcctNum,strAccountnum,strCSVUsageTypeCode);
	           	objReportUtils.fn_HandleReportWindows(driver, strParentWindow);	
	           	objGLLocation.fn_clickReports(driver, webWait).click();}
	        	//TODO:: TestAccount ::No and Exclude Usage :: Yes
	       		else if (isTestAcctFlag==false && IsExcludeUsageFlag==true){
	       		strCSVStatus="Discarded";  
	       		blnResult=fn_GenerateInvoice(strAPIURL,strAccountnum,LOAD_USAGE_FILE_PATH);
	 		   //TODO:: click on Analytics and Reporting Link
				objPage.fn_clickAnalyticsReporting(driver, webWait).click();
	       		//String strParentWindow = driver.getWindowHandle();
				ReportContent=objFunctions.fn_AccountUsageSummaryReport(driver, webWait,"test_acct_ind","2","exclude_ind","1",strEndDate,strCurrentDate);
				blnResult= fn_ReportValidation(ReportContent,isTestAcctFlag,IsExcludeUsageFlag,strCSVStatus,StrTestAcctNum,strAccountnum,strCSVUsageTypeCode);
	       		Thread.sleep(2000);
	       		driver.findElement(By.xpath("/html/body/div[6]/div[1]/button/span[1]")).click();	
	           	objGLLocation.fn_clickReports(driver, webWait).click();}
	        	//TODO:: TestAccount ::Yes and Exclude Usage :: No
	       		else if (isTestAcctFlag==true && IsExcludeUsageFlag==false){
	       		strCSVStatus="Unbilled";
	       		ReportContent=objFunctions.fn_AccountUsageSummaryReport(driver, webWait,"test_acct_ind","1","exclude_ind","2",strEndDate,strCurrentDate);
	       		Thread.sleep(2000);
	       		blnResult= fn_ReportValidation(ReportContent,isTestAcctFlag,IsExcludeUsageFlag,strCSVStatus,StrTestAcctNum,strAccountnum,strCSVUsageTypeCode);
	       		driver.findElement(By.xpath("/html/body/div[6]/div[1]/button/span[1]")).click();	
	           	objGLLocation.fn_clickReports(driver, webWait).click();}
	        	//TODO:: TestAccount ::No and Exclude Usage :: Yes
	       		else if (isTestAcctFlag==false && IsExcludeUsageFlag==false){
	       		strCSVStatus=null;
	 			Thread.sleep(1500);
	 			ReportContent=objFunctions.fn_AccountUsageSummaryReport(driver, webWait,"test_acct_ind","2","exclude_ind","2",strEndDate,strCurrentDate);
	 			blnResult= fn_ReportValidation(ReportContent,isTestAcctFlag,IsExcludeUsageFlag,strCSVStatus,StrTestAcctNum,strAccountnum,strCSVUsageTypeCode);
	       		driver.findElement(By.xpath("/html/body/div[6]/div[1]/button/span[1]")).click();	
	           	objGLLocation.fn_clickReports(driver, webWait).click();}}
	    		catch (Exception exception)
	    		{
	    			exception.printStackTrace();
	    		  }
				return blnResult;
			    }
				
	   //TODO:: Function to Validate the Results of the Report
	    public boolean fn_ReportValidation(WebElement ReportContent,boolean isTestAcctFlag,boolean IsExcludeUsageFlag,String strCSVStatus,String StrTestAcctNum,String strAccountnum,String strCSVUsageTypeCode) throws Exception{
	    	
	    	boolean blnResult=false;
	    	String strReportUsageTypeCode=null;
	    	String strReportStatus=null;
	    	boolean IsAcctPresent=false;
	    	
	 
			if (isTestAcctFlag==true)
				strAccountnum=StrTestAcctNum;
			
			if (ReportContent.equals(null))
				driver.findElement(By.partialLinkText("Click here to view this large report.")).click();
			
			else{
				Log.info("Report displayed");
				Utils.takeScreenshot(driver, strTestCaseName);
				List<WebElement> rows = ReportContent.findElements(By.tagName("tr"));
				Thread.sleep(2500);
							
				for (WebElement row : rows ){
				
					List<WebElement> cols = row.findElements(By.tagName("td"));
				
					if (cols.size()>0){
											
						String strAccount = cols.get(0).getText().trim();
						
						if (strAccount.equals(strAccountnum)){
							Log.info("Account No is :"+strAccount);
							IsAcctPresent=true;
							strReportUsageTypeCode = cols.get(7).getText().trim();
							strReportStatus = cols.get(9).getText().trim();
							Log.info("Report - UsageTypeCode is displayed as : "+strReportUsageTypeCode);
							Log.info("Report - Status is displayed as : "+strReportStatus);}
						else
							IsAcctPresent=false;;}}
					    }
				        
                Log.info("==============Results for TestAccount ::"+isTestAcctFlag +"and Exclude Usage ::"+IsExcludeUsageFlag);
			if ((isTestAcctFlag==true && IsExcludeUsageFlag==true)||(isTestAcctFlag==false && IsExcludeUsageFlag==true)||(isTestAcctFlag==true && IsExcludeUsageFlag==false)){		
				if (strCSVUsageTypeCode.equalsIgnoreCase(strReportUsageTypeCode) && strCSVStatus.equalsIgnoreCase(strReportStatus)){
	       			Log.info("Data for Account usage summary report for account "+strAccountnum + " is displayed correctly");
	       			Log.info("Usage Code :::	Report Value : "+strReportUsageTypeCode+"	Expected Value : "+strCSVUsageTypeCode);
	       			Log.info("Status :::	Report Value : "+strReportStatus+"	Expected Value : "+strCSVStatus);}				
				else{
	       			Log.info("ERROR :: Data for Account usage summary report for account "+strAccountnum + " is not displayed correctly");
	       			Log.info("Usage Code :::	Report Value : "+strReportUsageTypeCode+"	Expected Value : "+strCSVUsageTypeCode);
	       			Log.info("Status :::	Report Value : "+strReportStatus+"	Expected Value : "+strCSVStatus);
	       			VerificationMethods.assertTrue(false, "Fail : Data for Account usage summary report is not correctly displayed"); }}		
			else{
				if ((strReportStatus==strCSVStatus) && (IsAcctPresent==false)){
	       			Log.info("Data for Account usage summary report for account "+strAccountnum + " is displayed correctly");
	       			Log.info("Status :::	Report Value : "+strReportStatus+"	Expected Value : "+strCSVStatus);}
				else{
	       			Log.info("ERROR :: Data for Account usage summary report for account "+strAccountnum + " is not displayed correctly");
	       			Log.info("Status :::	Report Value : "+strReportStatus+"	Expected Value : "+strCSVStatus); 
	       			VerificationMethods.assertTrue(false, "Fail : Data for Account usage summary report is not correctly displayed");}}	
			Log.info("==============End of Results for TestAccount ::"+isTestAcctFlag +"and Exclude Usage ::"+IsExcludeUsageFlag);
	       
			Thread.sleep(2000);
       		driver.switchTo().defaultContent();
       		Thread.sleep(3000);		
       		//TODO : Close left nav Reports
       		//driver.findElement(By.linkText("Reports")).click();	
       		blnResult=true;
       		return blnResult;
	    }
	       		
	   //TODO:: Function to Load Usage and generate Invoice
		public Boolean fn_GenerateInvoice(String strAPIURL,String strAccountnum,String Loadpath) throws Exception{
			
			//Objects for Accessing Account Functions
			AccountFunctions objfnctns = new AccountFunctions();		
			AccountFunctions_Invoices ObjInvoices = new AccountFunctions_Invoices();
			boolean blnResult=false;
	   		
			try{
       		//TODO : Adjust Billing dates for the Account to Genarate Invoice  	
			boolean blnAdjustDate=objfnctns.fn_AdjustAcctBillDates(driver, webWait, strAPIURL, strAccountnum, strTestCaseName);
			if (blnAdjustDate==true){
				 Log.info("Account Billing Date has been set successfully");}
			else{
				 Log.info("ERROR: Account Billing Date has not been set");
				 assertTrue(false, "ERROR: Since the Billing Date's can't be adjusted, Invoicing will fail, so exiting TC !");}
    	  	//TODO: Fetching the Date for Loading Usage	
       		String strLoadDataDate = objfnctns.fn_getLoadDataDate();
           	Log.info("Loading Account Usage for the date '"+strLoadDataDate+"'..");
           	Thread.sleep(200);
           	//TODO: Loading the Usage
           	objfnctns.fn_LoadAccountUsage(driver, webWait, strAPIURL, Loadpath, strAccountnum, strLoadDataDate);
           	Thread.sleep(2000);
      		           	
    		//TODO: Generate Advance Invoice for the Account for which data is currently uploaded & Verify Print Invoice.
           	boolean blnInvoiceGenerated = ObjInvoices.fn_GenerateInvoice(driver, webWait, strAccountnum, "Advanced");
           	Thread.sleep(2000);
           	if(blnInvoiceGenerated == true){
           		boolean blnInvoiceApproved = ObjInvoices.fn_ApproveAdvancedInvoices(driver, webWait);
           		if(blnInvoiceApproved == false)
           			Log.info("ERROR: Invoice generated couldn't be approved !");
           		
           		//Since Invoice has been generated and Approved, we generate the Statement. 
           		webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Accounts"))).click();
           		ObjInvoices.fn_GenerateInvoiceStatement(driver, webWait, strTestCaseName);}
           	else{
           		Log.info("ERROR: Invoice couldn't be generated !");
           		assertTrue(false, "ERROR: Invoice is not generated, so exiting TC !");}
           	
            //TODO : Select the Latest Invoice
           	boolean blnLatestInvoiceSelected = ObjInvoices.fn_SelectLatestInvoice(driver, webWait);
           	if(blnLatestInvoiceSelected == false){
           		Log.info("ERROR: Latest Invoice couldn't be selected !");
           		assertTrue(false, "ERROR: Latest Invoice is not Selected, so exiting TC !");}
           	else{
           		Log.info("Latest Invoice is selected Successfully");}
           	Thread.sleep(2000);
           	//Discarding the Invoice
           	driver.findElement(By.partialLinkText("Void this invoice and Discard Usage")).click();
           	Thread.sleep(4000);
           	driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/button[2]/span")).click();
           	Thread.sleep(2000);
           	blnResult=true;
		    }
			catch (Exception e)	{
			e.printStackTrace();}
			return blnResult;}
 
		//TODO: Reading data for the test    
		@DataProvider(name = "objectTestData")
		public Object[][] testExcelData() throws DataDrivenFrameworkException{
		
		    Utils objUtils=new Utils();
		    return objUtils.data1("TestCaseDetails", "AccountUsageSummaryReport");}
		
	  	@AfterTest
	  	public void afterMethod() throws Exception{
	  	
	  		 //TODO: Logs out of the application & quit the driver
	  		 AriaLoginLogout.appLogout(driver);
	  		 driver.quit(); 	 }}
	  
		



