/*
 Author     		:	Madhavi JN
 Class Name			: 	Cls_BillingSummary 
 Purpose     		: 	Purpose of this file is :
						1. To verify whether the voided payment is present in the after report
						2. This is automation of TC: QAABE-355
 Date       		:	29/11/2015
 Version Information:	Version 1.0
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "PaymentTransactionByDateReport" worksheet 
 						for excel "TestData.xls". 

 Test Steps 		:	1. Login using valid credentials 
 						2. Search account,check the assignment of supplemental plan,Adjust the billing date,Load usage,
 						Generate Invoice,approve it and generate a statement.
 						3. Do payment by External API->record_external_payment,split and get the transaction ID.
 						4. Download the Before report(Payment Transaction by Date Report) will be placed under C:\\QAARIA\\src\\testData\\BeforeReport with timestamp
 						5. Check the payment type and Transaction ID in the before Report and Print them.
 						6. Void the earlier payment using API-> void_transaction using earlier payment Transaction ID
 						7. Check the payment type(Void check) and Transaction ID in the After Report(Payment Transaction by Date Report) and Print them.
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/
package testCases.Reports;

import java.util.List;
import java.util.Random;

import org.apache.log4j.xml.DOMConfigurator;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
import appModules.Cls_Billing_Summary_Report;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_PaymentTransactionByDateReport extends VerificationMethods{
	
public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_PaymentTransactionByDateReport";

		@BeforeTest
		 public static void beforeMethod()throws Exception
		 {	
				DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
				driver = Utils.fn_SetChromeDriverProperties();
				System.setProperty("webdriver.chrome.driver",chromeDriverPath);
				driver.manage().window().maximize();
				EnvironmentDetails objEnv = new EnvironmentDetails();
				Log.startTestCase("Login to application");
				AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
		 }
		
		@Test(dataProvider="objectTestdata",description="PaymentTransactionByDateReport")
		public void PaymentTransactionByDateReport(String strTestCaseName,String strAcctNum,String strAPIURL,String strFilename,String strPlanName,String strformat,String strAmount,String strReason)
		{
			    WebDriverWait webWait;
			    webWait = new WebDriverWait(driver,1000);
			    Log.startTestCase("Cls_PaymentTransactionByDateReport");
			   //Create Objects
			   //Objects for Accessing AccountFunctions_Plans
			    AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
			   //Objects for Accessing Account Functions
				AccountFunctions objfnctns = new AccountFunctions();
				//Objects for Accessing AccountFunctions_Invoices
				AccountFunctions_Invoices ObjInvoices = new AccountFunctions_Invoices();
				//Objects for Accessing Billing Report
				Cls_Billing_Summary_Report objReport = new Cls_Billing_Summary_Report();
				
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
	           Thread.sleep(6000);
			   Random t = new Random();
	    	   String strReference = "TestPaymentUnique"+t.nextInt(1000);
	           //Firing the record_external_payment API
	     	   String strURL = AccountFunctions.getFullUrl("record_external_payment");
	     	   ApiHandler objapihandler = new ApiHandler(); 
	     	   String strFinalURL = strURL+"&account_no="+strAcctNum+"&reference_code="+strReference+"&payment_amount="+strAmount+"&output_format="+strformat;
	     	   List<JSONObject>Jsonlist = objapihandler.makeSimplePostCall(strFinalURL);
	     	   Thread.sleep(600);
	     	   //Displaying the JSonList Returned
	     	   Log.info("the list returned is "+Jsonlist);
	     	   String strCombined =Jsonlist.toString();
	     	   Log.info("the combined string is "+strCombined);
	     	   String[] split = strCombined.split("[{:,}]");
	    	   String[] arrayBeforeElements = new String[6];
	    	   int m=0;
	    	   boolean blnIspresent = false;
	    	   
	    	   for(int l=0; l<split.length; l++) 
	    	   {
	    		   if (!split[l].contains("["))
	    			 {
		  		     		  
		    			 if (split[l].contains("]"))
			       		  {
		    				  blnIspresent = true;
		    				  break;
			       		  }
	    			       	    
				          if (!blnIspresent==true)
						  { 
				        	 //Log.info("the stored array elements are "+split[i]);
				        	  arrayBeforeElements[m]=split[l];
				        	   m++;		
						   }
	    			 }
	    	   }
	    	   Thread.sleep(600);
	    	   for (m=0;m<arrayBeforeElements.length;m++)
	    	   {
	    		   
	    		   Log.info("the array elements present are :: "+arrayBeforeElements[m]);
	    	   }
	    	   Thread.sleep(600);
	    	   String strBeforeTransactionID =arrayBeforeElements[5];
	     	   
	     	  //Downloading the Before Report
	     	  objReport.fn_PaymentTransactionByDate(driver, webWait, strAcctNum, strBeforeTransactionID,"BeforeReport");    	  
	     	  String strRecieptID = "TestPaymentUnique"+t.nextInt(1000);
	     	  String strURL1 = AccountFunctions.getFullUrl("void_transaction");
	     	  ApiHandler objapihandler1 = new ApiHandler(); 
	     	  String strFinalURL1 = strURL1+"&account_no="+strAcctNum+"&transaction_id="+strBeforeTransactionID+"&reason_code="+strReason+"&comments="+"void"+"&client_receipt_id="+strRecieptID+"&discard_invoice_usage="+true+"&output_format="+strformat;
	     	  List<JSONObject>Jsonlist1 = objapihandler1.makeSimplePostCall(strFinalURL1);
	     	  Thread.sleep(600);
	     	  //Displaying the JSonList Returned
	     	  Log.info("the list returned is "+Jsonlist1);
	     	  String strCombined1 =Jsonlist1.toString();
	     	  Log.info("the combined string is "+strCombined1);
	     	  String[] split1 = strCombined1.split("[{:,}]");
	    	  String[] arrayAfterElements = new String[6];
	    	  int n=0;
	    	  boolean blnIspresent1 = false;    	  
		   	  for(int i=0; i<split1.length; i++) 
	    	  {
	    		   if (!split1[i].contains("["))
	    			 {
		  		     		  
		    			 if (split1[i].contains("]"))
			       		  {
		    				 blnIspresent1 = true;
		    				  break;
			       		  }
	    			       	    
				          if (!blnIspresent1==true)
						  { 
				        	 //Log.info("the stored array elements are "+split[i]);
				        	  arrayAfterElements[n]=split1[i];
				        	   n++;		
						   }
	    			 }
	    	   }    	   
	    	   for (n=0;n<arrayAfterElements.length;n++)
	    	   {
	    		   
	    		   Log.info("the array elements present are :: "+arrayAfterElements[n]);
	    	   }	    	  
	    	   Thread.sleep(600);
	    	   String strAfterTransactionID =arrayAfterElements[3];    	  
	     	   //Downloading the After Report
	     	   objReport.fn_PaymentTransactionByDate(driver, webWait, strAcctNum, strAfterTransactionID,"AfterReport");
	     	   Thread.sleep(600);
				
		}
        catch (Exception e) 
		{
		// TODO Auto-generated catch block
				e.printStackTrace();
		}}
			
		
		@DataProvider(name="objectTestdata")
		public Object[][] data() throws DataDrivenFrameworkException
		{
			  Utils objUtils = new Utils();
			  return objUtils.data1("TestCaseDetails", "PaymentTransactionByDateReport");
		}
		
		
		@AfterTest
		public void afterMethod() throws Exception
		 {
			 //Logs out of the application
			 AriaLoginLogout.appLogout(driver); 
			 //Quitting the driver
			 driver.quit(); 
		 }

}
