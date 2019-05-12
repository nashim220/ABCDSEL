/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_VerifyGLLocationSegment 
 Purpose     		: 	Purpose of this file is :
						1. To verify if the GL segment code is passed in report.
						2. This is automation of TC: QAABE-277.

 Date       		:	08/26/2015
 Modified Date		:	10/27/2015, 09/24/2015
 Version Information:	Version 3.0
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "GLSegmentCode" worksheet 
 						for excel "TestData.xls". 

 Test Steps 		:	1. Login using valid role credentials for updating existing customer account.
 						2. Search the customer account to view it's Invoice value.
 						3. Navigate to the Analytics & reporting -> Reports -> Run Reports.
 						4. Check the Revenue by GL Code - SGAS report under SGAS.
 						5. Verify if the GL Segment code has been displayed. 	
 						
Version Changes 2.0:	1. Revamped the logic for the Verification.
 						2. Using new Utils file ReportUtils for reports specific changes.
Version Changes 3.0:	1. Adding logic to automate setting the Account Billing Dates for smooth Invoice generation.
						2. After successful Invoice generation, statement generation has been called now.
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.Reports;

import atu.ddf.exceptions.DataDrivenFrameworkException;

import org.apache.log4j.xml.DOMConfigurator;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import utility.EnvironmentDetails;
import utility.Log;
import utility.ReportUtils;
import utility.Utils;
import utility.VerificationMethods;
import pageObjects.VerifyGLLocationSegment;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Invoices;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;

public class Cls_VerifyGLLocationSegment extends VerificationMethods
{
    public static WebDriver driver;
    public static String strTestCaseName = "Verify GL Location Segment in Reports";
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
    Function fn_VerifyGLLocation: This is the actual Test NG test for Verifying the updated GL Customer Information.
*/
	@Test(dataProvider = "objectTestData", description = "Verify GL Location Segment in Reports")
    public void fn_VerifyGLLocation(String strPlanName, String strAccountNum, String strAPIURL, String strFileName, String strCustomField, String strCustomFieldData) throws Exception
    {
    	//TODO: Create object for the classes to be used for accessing reusable codes.
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefActions = new Cls_ChangeDeleteClientDefinedFieldActns();
    	AccountFunctions objAccountFunc = new AccountFunctions();	// for methods relating to Accounts functionality.
    	AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
    	AccountFunctions_Invoices objAcctFuncInvoices = new AccountFunctions_Invoices();
		VerifyGLLocationSegment objGLLocation = new VerifyGLLocationSegment();
		ReportUtils objReportUtils = new ReportUtils();
		
		//TODO: declare booleans & objects to govern the actions to be performed.
		Boolean blnSupplementalPlansStatus = false;
		Boolean blnInvoiceGenerated = false;
		Boolean blnApprovedInvoice = false;
		Boolean blnReportVerified = false;
		Boolean blnAdjustBillingDates = false;
		Pattern regex = null;
		Matcher matcher = null;
		String strInvoicesAmount = null;
		
    	//TODO: Log the beginning of the test case.
    	Log.startTestCase(strTestCaseName);
    	
    	try
    	{
    		//TODO: For the specified account, get the Location Code for verification on Report; else assign one.
    		objClientDefActions.AccountSearch(driver, webWait, strAccountNum);
    		webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Reset Password")));
    		String strLocCode = objClientDefActions.fn_GetCustomFieldValue(driver, webWait, strAccountNum, strCustomField, strTestCaseName);
    		Log.info("The Value for the Custom Field Loc_Code is: "+strLocCode);
    		if(strLocCode == null)
    		{
    			objClientDefActions.AddNewField(driver, webWait, strCustomField, strCustomFieldData);
    			strLocCode = strCustomFieldData;
    		}
    		else
    		{
        		regex = Pattern.compile("[$&+,:;=?@#|]");
        		matcher = regex.matcher(strLocCode);
        		if (matcher.find())
        		{
        			objClientDefActions.fn_UpdateCustomFields(driver, webWait, strCustomField, strCustomFieldData, strTestCaseName);
        			strLocCode = strCustomFieldData;
        		}
    		}
    		
    		//TODO: Load Data for specified account for calculated load date.
       		String strPlanNumber = objAcctFuncPlans.fn_GetPlans(driver, webWait, strPlanName, strTestCaseName);
       		if(strPlanNumber.contentEquals(""))
       			assertTrue(false, "Supplemental Plan Number couldn't be retrieved !");
			blnSupplementalPlansStatus = objAcctFuncPlans.fn_VerifySupplementalPlans(driver, webWait, strAccountNum, strTestCaseName);
       		if(blnSupplementalPlansStatus == false)
       		{
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
           	if(blnInvoiceGenerated == true)
           	{
           		//TODO: Once the Advanced Invoice is generated, get the amount for which Invoice is generated.
           		objGLLocation.fn_getInvoicesDataTable(driver, webWait);
           		blnApprovedInvoice = objAcctFuncInvoices.fn_ApproveAdvancedInvoices(driver, webWait);
           		if(blnApprovedInvoice == false)
           			assertTrue(false, "ERROR: Generated Advance Invoice couldn't be approved !");
           		
           		//Since Invoice has been generated and Approved, we generate the Statement. 
           		webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Accounts"))).click();
           		objAcctFuncInvoices.fn_GenerateInvoiceStatement(driver, webWait, strTestCaseName);

           		//now get the amount of the latest invoice generated.           		
           		objGLLocation.fn_clickStatementsTab(driver, webWait).click();
           		objGLLocation.fn_getInvoicesDataTable(driver, webWait);
           		webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Statements & Invoices"))).click();
           		objGLLocation.fn_getInvoicesDataTable(driver, webWait);
           		objGLLocation.fn_clickInvoices(driver, webWait).click();
           		objGLLocation.fn_getInvoicesDataTable(driver, webWait);
           		Thread.sleep(2000);           		
        		WebElement webInvoicesDetails = objGLLocation.fn_getInvoicesDataTable(driver, webWait).findElement(By.tagName("tbody"));
        		List<WebElement> lstwebReadInvoicesRow = webInvoicesDetails.findElements(By.tagName("tr"));
        		for(WebElement row: lstwebReadInvoicesRow)
        		{
        			List<WebElement> cols = row.findElements(By.tagName("td"));
        			
        			if(cols.size() >= 2)
        			{
        				strInvoicesAmount = cols.get(5).getAttribute("innerText").toString().trim();
        				Log.info("The Invoiced Amount is: "+strInvoicesAmount);        				
        				break;
        			}
        		}
           		
        		//TODO: Navigate to the Revenue by GL Code - SGAS report.
        		objGLLocation.fn_clickAnalyticsReporting(driver, webWait).click();
        		objReportUtils.fn_GenerateReport(driver, "SGAS", "Revenue by GL Code - SGAS", "Current Day", "Current Day");
        		
        		WebElement webReadContents = objReportUtils.fn_ReadGeneratedReport(driver);
        		List<WebElement> lstwebReadContentsRow = webReadContents.findElements(By.tagName("tr"));
        		List<WebElement> lstwebReadContentsRowSL = lstwebReadContentsRow.subList(2, lstwebReadContentsRow.size());
        		for(WebElement row: lstwebReadContentsRowSL)
        		{
        			List<WebElement> cols = row.findElements(By.tagName("td"));
        			
        			if(cols.size() >= 2)
        			{
        				String strReportGLCode = cols.get(2).getAttribute("innerText").toString().trim();
        				String strReportAmount = cols.get(3).getAttribute("innerText").toString().trim();        					
        				
        				BigDecimal bigdInvoicesAmount = new BigDecimal(strInvoicesAmount.replaceAll(",", ""));
        				BigDecimal bigdReportAmount = new BigDecimal(strReportAmount.replaceAll(",", ""));
        				Integer intResult = bigdReportAmount.compareTo(bigdInvoicesAmount);
        				
        				if(strReportGLCode.contains(strLocCode) && (intResult==0 || intResult==1))
        				{
        					Log.info("GL Code has been confirmed for the report generated with value '"
        							+strReportGLCode+"' having '"+strLocCode+"' reported in it.");
        					blnReportVerified = true;
        					break;
        				}
        			}
        		}
        		driver.switchTo().defaultContent();
        		Thread.sleep(3500);
        		driver.findElement(objGLLocation.fn_clickReportFrameCloseButton()).click();	//click close on the report frame.
        		
        		//TODO: decide if the 'Revenue by GL Code - SGAS' report is verified. 
        		if(blnReportVerified == false)
        		{
        			Reporter.log("Error: The 'Revenue by GL Code - SGAS' report couldn't be  verified for LOC Code '"+strLocCode+"' with Amount: "+strInvoicesAmount+".");
        			assertTrue(false, "Error: The 'Revenue by GL Code - SGAS' report couldn't be  verified for LOC Code '"+strLocCode+"' with Amount: "+strInvoicesAmount+".");
        		}
           	}
           	else
           	{
           		Utils.takeScreenshot(driver, strTestCaseName);
           		Reporter.log("ERROR: Since Invoice isn't generated, we can't verify the GL code on the 'Revenue by GL Code - SGAS' Report !");
           		assertTrue(false, "ERROR: Since Invoice isn't generated, we can't verify the GL code on the 'Revenue by GL Code - SGAS' Report !");
           	}     		
    	}
    	catch (Exception exception)
    	{
    		Utils.takeScreenshot(driver, strTestCaseName);
    		Log.error("ERROR: Verification for the GL code on the 'Revenue by GL Code - SGAS' Report couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Verification for the GL code on the 'Revenue by GL Code - SGAS' Report couldn't be done due to exception:  "+exception.toString());
    		throw exception;
    	}
    	catch (AssertionError exception)
    	{
    		Utils.takeScreenshot(driver, strTestCaseName);
    		Log.error("ERROR: Verification for the GL code on the 'Revenue by GL Code - SGAS' Report couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Verification for the GL code on the 'Revenue by GL Code - SGAS' Report couldn't be done due to exception:  "+exception.toString());
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
	    return objUtils.data1("TestCaseDetails", "GLSegmentCode");
	}
	
	
	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }	
}