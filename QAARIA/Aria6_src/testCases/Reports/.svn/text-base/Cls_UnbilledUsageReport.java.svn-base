/*
 Author     		:	Namrata Akarte
 Class Name			: 	Cls_UnbilledUsageReport  
 Purpose     		: 	Purpose of this file is :
						1. To validate if unbilled usage entries for the specific account displayed in the report and 
						Displayed against it in UI is matching
												
 Date       		:	09/16/2015  
 Modified Date 		:   01/21/2016, 11/26/2015, 10/28/2015
 
 Version Information:	Version 4.0
 
 Jira #				:	QAABE-98/ABE-655
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in 'TestCaseDetails' worksheet of "TestData.xlsx" 
 						under 'UnbilledUsageReports' section. .

 Test Steps 		:	1. Login using valid role credentials.
 						2. Load Usage using API for the test account.
 						3. Navigate to Analytics and Reporting->Reports->SGAS->All Unbilled Usage Reports.
 						4. Generate Report for the current day.
 						5. Entries corresponding to the usage loaded in step 2 should reflect in the report. 
 						Also, verify if the Parent account (if the usage is loaded for the child account) and Company name are reflected
 						in the report 
 						
 Version Changes2.0	:	1. Refactored the code to point correct appModule (AccountFunctions_Plans, ReportUtils)
 						2. Updated the code as there were changes in the report
 Version Changes3.0	:	1. Updated chrome driver path	
 Version Changes4.0	: 	1. Added code for verification of the fields i. Sr Account Number ii. Company Name
 						2. Set the calendar picked values while generating the report 
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/
package testCases.Reports;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.NameValuePair;
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

import utility.CsvHandler;
import utility.EnvironmentDetails;
import utility.Log;
import utility.VerificationMethods;
import utility.ReportUtils;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import utility.Utils;

public class Cls_UnbilledUsageReport {
	
	public WebDriver driver;
	public WebDriverWait webWait;
	public String strAuthenticationKey;
	public String strTCName = "Verify unbilled usage report";
	
	@BeforeClass
	public void beforeMethod()throws Exception
	{	
		//TODO: Initialize the Logger.
    	DOMConfigurator.configure("log4j.xml");	

    	//TODO: Set Chrome driver as the driver to launch the test.
    	driver = utility.Utils.fn_SetChromeDriverProperties();
		//TODO: initialize the webWait
		webWait = new WebDriverWait(driver, 1000);
		EnvironmentDetails objEnv = new EnvironmentDetails();
		
		Log.startTestCase("Login to application");
		String dataSheetPath = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "testData"+ System.getProperty("file.separator") + "TestData.xlsx";
		System.out.println("TestData : "+dataSheetPath);
		//Logs in to the aria application with the appropriate credentials		 	
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);
	
	}
	
	@Test(dataProvider="objectTestData", description="UnbilledUsageReports")
	public void fn_VerifyUsageReports(String strPlanName,String strAcctNum,String strAPIURL, String strFileName ){
		
		//TODO : Initialize variables		
		String strTestCaseName = "Verify Unbilled Usage Report";
		Boolean blnSupplementalPlansStatus = false;
		String strUsageCode = "";
		String strUsageUnits = "";
		String strApplUsageCode = "";
		String strApplUsageUnits = "";
		String strApplTimeStamp = "";
		String strAcct = "";
		String strApplCompanyName = "";
		String strApplSeniorAcctNo = "";
		String strSrAcctNo = "";
   		String strCompanyName = "";
   		
   		String strRepUsageCode = "";
   		String strRepUsageUnits = "";
   		String strRepCompName = "";
   		String strRepSrAcctNo = ""; 
	
		List<NameValuePair> lstpairAcctDetails = new ArrayList<NameValuePair>();
		//TODO : Create Objects to respective classes
		AccountFunctions objAcctFns = new AccountFunctions();
		AccountFunctions_Plans objAcctPlans = new AccountFunctions_Plans();
		ReportUtils objReport = new ReportUtils();
		CsvHandler objCsvHandler = new CsvHandler();
		AccountFunctions_Plans objAccountFunctions_Plans = new AccountFunctions_Plans();
		
		TreeMap<String, String> tmapUnbilledUsage =new TreeMap<String,String>();//creating new generic arraylist  
		
		Log.startTestCase(strTestCaseName);
		
		try {
			//TODO : Read the value of Company name for the account
			lstpairAcctDetails = objAcctFns.fn_getAccountDetails(driver, webWait, strAcctNum, true);
			
			breakloop : 
			for (NameValuePair p : lstpairAcctDetails){
				
				//Get label name
				String strLabelName = p.getName();				
				if (strLabelName.contains("Company Name")){
					strCompanyName = p.getValue();
					break breakloop;
				}
			}
			Log.info("Company Name : " +strCompanyName);
			
			Thread.sleep(1000);
			//TODO : Click on Account Overview
			driver.findElement(By.linkText("Account Overview")).click();
			
			Thread.sleep(1000);
			
	    	//TODO : Read if the parent exists for the account
			if (driver.findElement(By.xpath("//*[@id=\"content-wrapper\"]/table[8]/tbody")).isDisplayed()){
				Log.info("Table Exist");
				
				List<WebElement> rows = driver.findElement(By.xpath("//*[@id=\"content-wrapper\"]/table[8]/tbody")).findElements(By.tagName("tr"));
								
				if (rows.size()>1){
					driver.findElement(By.linkText("Accounts")).click();
					String strParentChild = objAccountFunctions_Plans.fn_VerifyParentChildAccounts(driver, webWait, strAcctNum, strTestCaseName);
					String strAccounts[] = strParentChild.split("#"); 
					if (!strAccounts[0].equals(strAcctNum)){
						strSrAcctNo = strAccounts[0];
						Log.info("Parent Account Number is : " + strSrAcctNo);
					}
					else
						strSrAcctNo = "Its not a child account";
				}
				else
					strSrAcctNo = "Its not a child account";
			}
				
			
			//TODO: Load Data for specified account for current date.
       		String strPlanNumber = objAcctPlans.fn_GetPlans(driver, webWait, strPlanName, strTestCaseName);
       		
       		if(strPlanNumber.contentEquals(""))
       			throw new Exception("Supplemental Plan Number couldn't be retrieved !");
			blnSupplementalPlansStatus = objAcctPlans.fn_VerifySupplementalPlans(driver, webWait, strAcctNum, strTestCaseName);
       		if(blnSupplementalPlansStatus == false)
       		{
       			objAcctPlans.fn_AssignSuppPlan(driver, webWait, strAcctNum, strPlanNumber, strAPIURL);
       			Log.info("Supplemental Plan with number "+strPlanNumber+" has been assigned to the Account Number: "+strAcctNum);
       		}			
       		
       		String LOAD_USAGE_FILE_PATH = System.getProperty("user.dir")
                    + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
                    + System.getProperty("file.separator") + strFileName;           	
       		String strCurrentDate = Utils.getDateYearFirst();
           	Log.info("Loading Account Usage");
          	objAcctFns.fn_LoadAccountUsage(driver, webWait, strAPIURL, LOAD_USAGE_FILE_PATH, strAcctNum, strCurrentDate);
			
          	String strParentWindow = driver.getWindowHandle();
          	Thread.sleep(500);
          	
			//Navigate to "All Unbilled Usage Report - SGAS" report		
          	objReport.fn_GenerateReportCalendarDate(driver, webWait, "SGAS", "All Unbilled Usage Report - SGAS", strCurrentDate, strCurrentDate);
          	//objReport.fn_GenerateReport(driver, "SGAS", "All Unbilled Usage Report - SGAS", "Yesterday", "Yesterday");
					
			//TODO : Read the contents of the generated report
			WebElement elementReportContent = objReport.fn_ReadGeneratedReport(driver);
			
			if (elementReportContent.equals(null))
				driver.findElement(By.partialLinkText("Click here to view this large report.")).click();
			
			else{
				Log.info("Report displayed");
				Utils.takeScreenshot(driver, strTestCaseName);
				WebElement table = driver.findElement(By.xpath("/html/body/table[2]"));
				WebElement tableBody = table.findElement(By.tagName("tbody"));
				List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
							
				for (WebElement row : rows ){
					List<WebElement> cols = row.findElements(By.tagName("td"));
										
					if (cols.size() == 30){						
					
						String strAccount = cols.get(5).getText().trim();
						Log.info("Account No is :"+strAccount);
						if (strAccount.equals(strAcctNum)){
							
							strApplUsageCode = cols.get(10).getText().trim();
							Log.info("Report - Usage Code is displayed as : "+strApplUsageCode);
							strApplUsageUnits = cols.get(12).getText().trim();
							Log.info("Report - Usage Units is displayed as : "+strApplUsageUnits);
							strApplTimeStamp = cols.get(14).getText().trim();
							strApplCompanyName = cols.get(6).getText().trim();
							Log.info("Report - Company Name is displayed as : "+strApplCompanyName);
							strApplSeniorAcctNo = cols.get(7).getText().trim();
							if (strApplSeniorAcctNo.equals(""))
								strApplSeniorAcctNo = "Its not a child account";
							Log.info("Report - Sr Account No is displayed as : "+strApplSeniorAcctNo);
							tmapUnbilledUsage.put(strApplTimeStamp, strApplUsageCode+"#"+strApplUsageUnits+"#"+strApplCompanyName+"#"+strApplSeniorAcctNo);//Enter the details for the account to treemap with timestamp
							
						}
						
					}		
					
				}
				
			}	
			
			//TODO : Parse the tree map and get the latest usage details
			for(Map.Entry<String,String> entry : tmapUnbilledUsage.entrySet()) {
				
				  //Log.info("Key is "+ entry.getKey() + "and value is :"+ entry.getValue());				
				  strAcct =entry.getValue();  
				 
			}
				
			//TODO : Read test data csv file
       		List<List<NameValuePair>> testdata = objCsvHandler.readCSV(LOAD_USAGE_FILE_PATH);
       		
       		for (List<NameValuePair> list : testdata){
       			for (NameValuePair val : list){
       				String strHeader = val.getName();
       				if (strHeader.equalsIgnoreCase("usage_type_code"))	     					
       					strUsageCode = val.getValue();
       					
       				if (strHeader.equalsIgnoreCase("usage_units"))  
       					strUsageUnits = val.getValue();       				
       			}
       		}
       		
       		//TODO : to get the latest uploaded usage
       		String[] parts = strAcct.split("#");
       		strRepUsageCode = parts[0];
       		strRepUsageUnits = parts[1];
       		strRepCompName = parts[2];
       		strRepSrAcctNo = parts[3];
			
       		if ((strRepUsageCode.equalsIgnoreCase(strUsageCode)) && strRepUsageUnits.equalsIgnoreCase(strUsageUnits) && strRepCompName.equalsIgnoreCase(strCompanyName) && strRepSrAcctNo.equalsIgnoreCase(strSrAcctNo) ){
       			       		
       			Log.info("Data for unbilled usage report for account "+strAcctNum + " is displayed correctly");
       			Log.info("Usage Code :::	Report Value : "+strRepUsageCode+"	Expected Value : "+strUsageCode);
       			Log.info("Usage Units :::	Report Value : "+strRepUsageUnits+"	Expected Value : "+strUsageUnits);
       			Log.info("Company Name :::	Report Value : "+strRepCompName+"	Expected Value : "+strCompanyName);
       			Log.info("Senior Account Number :::	Report Value : "+strRepSrAcctNo+"	Expected Value : "+strSrAcctNo);
       			
       		}
       		else
       		{
       			
       			Reporter.log("Error : Data for unbilled usage report for account "+strAcctNum + "is not correctly displayed");        			
       			Log.info("Usage Code :::	Report Value : "+strRepUsageCode+"	Expected Value : "+strUsageCode);
       			Log.info("Usage Units :::	Report Value : "+strRepUsageUnits+"	Expected Value : "+strUsageUnits);
       			Log.info("Company Name :::	Report Value : "+strRepCompName+"	Expected Value : "+strCompanyName);
       			Log.info("Senior Account Number :::	Report Value : "+strRepSrAcctNo+"	Expected Value : "+strSrAcctNo);
       			VerificationMethods.assertTrue(false, "Fail : Data for unbilled usage report is not correctly displayed");
       		}
       			      		
       		
       		driver.switchTo().defaultContent();
       		driver.findElement(By.xpath("//button[@title='close']")).click();
       		driver.switchTo().window(strParentWindow);
       		
       		//TODO : Close left nav Reports
       		driver.findElement(By.linkText("Reports")).click();
       		
       		//TODO : Click on Configurations link
       		driver.findElement(By.linkText("Configuration")).click();       		
			
		} 
		catch (InterruptedException exception) {
			// TODO Auto-generated catch block	
			Log.error("Verification of All Unbilled usage report cannot be completed as : "+exception.toString() );
			Reporter.log("ERROR : Verification of All Unbilled usage report cannot be completed as : "+exception.toString());
			exception.printStackTrace();
		} catch (Exception exception) {
			// TODO Auto-generated catch block 
			exception.printStackTrace();
				
		}
		
		Log.endTestCase(strTestCaseName);
		
	}
	
	//TODO: Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "UnbilledUsageReports");
	}
	
	//TODO : Logout of the Application and Close the driver
	@AfterClass
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
	 
}
