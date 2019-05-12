/*
 Author     		:	Madhavi JN
 Class Name			: 	Cls_PromotionalCredit 
 Purpose     		: 	Purpose of this file is :
						1. To Verify that Promotional Credit generated is displayed correctly on Invoice.
						2. Test Class for the test case QAABE-411.
 
 Date       		:	11/18/2015
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "PromotionalCredit" column for excel "TestData.xls".
 						
 Test Steps 		:	1. Login using valid role credentials for accessing an account.
 						2. Navigate to the searched Accounts -> Adjust Billing Date to Generate Invoice
 						3. Create a Recurring Promotional Credit.
 						4. Load the Usage and Generate Invoice->Select the Latest Invoice.
 						5. Open the Latest Invoice and verify whether the created promotional credit is appearing.
 						
Version Changes 1.1:	1. Refactored the flow according to the requirement of ABE-1277,added apyament 
                           through API and then changed the credit amount to generate Invoice.

 						
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AccountTestCases;

import java.util.List;
import java.util.Random;

import org.apache.log4j.xml.DOMConfigurator;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.ApiHandler;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Invoices;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_PromotionalCredit extends VerificationMethods
{
	
public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_PromotionalCredit";

		@BeforeTest
		 public static void beforeMethod()throws Exception
		 {	
			DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
			System.setProperty("webdriver.chrome.driver",chromeDriverPath);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			EnvironmentDetails objEnv = new EnvironmentDetails();
			Log.startTestCase("Login to application");
			AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
		 }
		@Test(dataProvider="objectTestData", description="PromotionalCredit")
	    public void ApplyPromotionalCredit(String strTestCaseName,String strAcctNum,String strAPIURL,String strFilename,String strCreditAmnt,String strReason,String strCreditNum,String strMonths,String strPlanName,String strFormat) throws Exception
	    {
		   WebDriverWait webWait;
		   webWait = new WebDriverWait(driver,1000);
		   
		   //Create Objects
		   //Objects for Accessing Account Functions
			AccountFunctions objfnctns = new AccountFunctions();
			//Objects for Accessing AccountFunctions_Plans
			AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
			//Objects for Accessing AccountFunctions_Invoices
			AccountFunctions_Invoices ObjInvoices = new AccountFunctions_Invoices();  
	    	//TODO: Create object for the classes to be used for accessing reusable codes.
	    	Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
	    	
			try
			{
			
			//TODO : Verifying whether supplemental plan is assigned to the account otherwise assign it to the account
			String strPlanNumber = objAcctFuncPlans.fn_GetPlans(driver, webWait, strPlanName, strTestCaseName);
			if(strPlanNumber.contentEquals(""))
			throw new Exception("Supplemental Plan Number couldn't be retrieved !");
			boolean blnSupplementalPlansStatus = objAcctFuncPlans.fn_VerifySupplementalPlans(driver, webWait, strAcctNum, strTestCaseName);
			if(blnSupplementalPlansStatus == false)
			{
				objAcctFuncPlans.fn_AssignSuppPlan(driver, webWait, strAcctNum, strPlanNumber, strAPIURL);
				Log.info("Supplemental Plan with number "+strPlanNumber+" has been assigned to the Account Number: "+strAcctNum);
			}				
					
			 //TODO : Adjust Billind dates for the Account to Genarate Invoice  	
			 boolean blnAdjustDate=objfnctns.fn_AdjustAcctBillDates(driver, webWait, strAPIURL, strAcctNum, strTestCaseName);
			 if (blnAdjustDate==true)
			 {
				 Log.info("Account Billing Date has been set successfully");
			 }
			 else
			 {
				 Log.info("ERROR: Account Billing Date has not been set");
				 assertTrue(false, "ERROR: Since the Billing Date's can't be adjusted, Invoicing will fail, so exiting TC !");
			 }
			 
			 Random t = new Random();
	    	 String strReference = "TestPaymentUnique"+t.nextInt(1000);
	         //Firing the record_external_payment API
	     	 String strURL = AccountFunctions.getFullUrl("record_external_payment");
	     	 ApiHandler objapihandler = new ApiHandler(); 
	     	 String strFinalURL = strURL+"&account_no="+strAcctNum+"&reference_code="+strReference+"&payment_amount="+strCreditAmnt+"&output_format="+strFormat;
	     	 List<JSONObject>Jsonlist = objapihandler.makeSimplePostCall(strFinalURL);
	     	 Thread.sleep(2600);
	     	 //Displaying the JSonList Returned
	     	 Log.info("the list returned is "+Jsonlist);
	     	 
	     	objClientDefAct.AccountSearch(driver, webWait, strAcctNum);
	     	Thread.sleep(200);
			 
			 //TODO : Create a Promotional Credit
			 boolean blnPromotionalCreditGenerated = ObjInvoices.fn_CreatePromotionalCredit(driver, webWait, strTestCaseName, strCreditAmnt, strReason, strCreditNum, strMonths);
			 if (blnPromotionalCreditGenerated==true)
			 {
				 Log.info("PromotionalCredit has been generated successfully");
			 }
			 else
			 {
				 Log.info("ERROR: PromotionalCredit couldn't be generated");
				 assertTrue(false, "ERROR: Since the PromotionalCredit can't be Created, Invoicing will not consist credit, so exiting TC !");
			 }
			
			//TODO: Load Data for specified account for current date.
       		String LOAD_USAGE_FILE_PATH = System.getProperty("user.dir")
                    + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
                    + System.getProperty("file.separator") + strFilename;
       		
        	//TODO: Fetching the Date for Loading Usage	
       		String strLoadDataDate = objfnctns.fn_getLoadDataDate();
           	Log.info("Loading Account Usage for the date '"+strLoadDataDate+"'..");
           	
           	Thread.sleep(200);
           	//TODO: Loading the Usage
           	objfnctns.fn_LoadAccountUsage(driver, webWait, strAPIURL, LOAD_USAGE_FILE_PATH, strAcctNum, strLoadDataDate);
           	Thread.sleep(2000);
      		           	
    		//TODO: Generate Advance Invoice for the Account for which data is currently uploaded & Verify Print Invoice.
           	boolean blnInvoiceGenerated = ObjInvoices.fn_GenerateInvoice(driver, webWait, strAcctNum, "Advanced");
           	Thread.sleep(2000);
           	if(blnInvoiceGenerated == true)
           	{
           		boolean blnInvoiceApproved = ObjInvoices.fn_ApproveAdvancedInvoices(driver, webWait);
           		if(blnInvoiceApproved == false)
           			Log.info("ERROR: Invoice generated couldn't be approved !");
           		
           		//Since Invoice has been generated and Approved, we generate the Statement. 
           		webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Accounts"))).click();
           		ObjInvoices.fn_GenerateInvoiceStatement(driver, webWait, strTestCaseName);
           	}
           	else
           	{
           		Log.info("ERROR: Invoice couldn't be generated !");
           		assertTrue(false, "ERROR: Invoice is not generated, so exiting TC !");
           	}
           	Thread.sleep(60000);
           	
            //TODO : Select the Latest Invoice
           	boolean blnLatestInvoiceSelected = ObjInvoices.fn_SelectLatestInvoice(driver, webWait);
           	if(blnLatestInvoiceSelected == false)
           	{
           		Log.info("ERROR: Latest Invoice couldn't be selected !");
           		assertTrue(false, "ERROR: Latest Invoice is not Selected, so exiting TC !");
           	}
           	else
           	{
           		Log.info("Latest Invoice is selected Successfully");
           	}
           	
           	//TODO : Read the Invoice table for Validating the generation of promotional credit
           	 String strItemAmount=null;
			//TODO : //To identify the Invoice Table and capture the generation of promotional credit
			WebElement InvoiceTable = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[2]/div[3]/div/div/form/div[1]/table"));			  
			WebElement InvoiceBody = InvoiceTable.findElement(By.tagName("tbody"));
			 
			List<WebElement>InvoiceRows = InvoiceBody.findElements(By.tagName("tr"));
			  
			Log.info("the number of rows :: "+InvoiceRows.size());

			for (WebElement row : InvoiceRows)
			{
				 List<WebElement>InvoiceColumns = row.findElements(By.tagName("td"));		 
	
				  for (WebElement col : InvoiceColumns)
				  {
					  
					 if (col.getText().trim().toString().contains("Account Service"))
					 {								 
						  strItemAmount = InvoiceColumns.get(InvoiceColumns.size()-1).getText();	
						  Log.info("Price generated for the credit on the application is"+strItemAmount);
						  break;
					  }					 				 				 
				   }				  				  
			 }
			
			if (strItemAmount!=null)
			{
				Log.info("credit is generated on the application");  
			}
			else
			{
				Log.info("credit is not generated on the application"); 
			}
			
			
			
		}
        catch (Exception e) 
		{
		// TODO Auto-generated catch block
				e.printStackTrace();
		}}
			
	    @DataProvider(name = "objectTestData")
	    public Object[][] data() throws DataDrivenFrameworkException
	    {
	        Utils objUtils=new Utils();
	        return objUtils.data1("TestCaseDetails", "PromotionalCredit");
	    }
	    
		@AfterTest
		public void afterMethod() throws Exception
		 {
			 //TODO: Logs out of the application & quit the driver
			 AriaLoginLogout.appLogout(driver);
			 driver.quit(); 
		 }
}
