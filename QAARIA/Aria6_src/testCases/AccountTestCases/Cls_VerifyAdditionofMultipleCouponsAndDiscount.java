
/*
 Author     		:	Madhavi JN
 Class Name			: 	Cls_PromotionalCredit 
 Purpose     		: 	Purpose of this file is :
						1. To Verify that Multiple Coupons are applied to the Account and Validate the discount accordingly.
						2. Test Class for the test case QAABE-466.
 
 Date       		:	12/30/2015
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "VerifyMultipleCoupons" column for excel "TestData.xls".
 						
 Test Steps 		:	1. Login using valid role credentials for accessing an account.
 						2. Navigate to the searched Accounts.
 						3. Verify the Supplemental plan.
 						4. Adjust Billing Date to Generate Invoice
 						5. Apply the required Multiple Coupons.
 						6. Load the Usage and Generate Invoice->Select the Latest Invoice.
 						7. Open the Latest Invoice and verify whether the Coupons are applied correctly.

 						
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AccountTestCases;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;

import appModules.AriaLoginLogout;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.commons.lang3.StringUtils;

import appModules.AccountFunctions;
import appModules.AccountFunctions_Invoices;
import appModules.AccountFunctions_Plans;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_VerifyAdditionofMultipleCouponsAndDiscount extends VerificationMethods
{
	public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_VerifyAdditionofMultipleCouponsAndDiscount";
	
	@BeforeClass
	 public static void beforeMethod()throws Exception
	 {	
			DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
			//TODO: Set Chrome driver as the driver to launch the test.
			driver = utility.Utils.fn_SetChromeDriverProperties();
			EnvironmentDetails objEnv = new EnvironmentDetails();
			AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
	 }	
	

	@Test(dataProvider="objTestData",description="VerifyMultipleCoupons")
	public void VerifyCreditCoupounsandDiscount(String strAcctNum,String strPlanName,String strTestCaseName,String strAPIURL,String strFilename,String DiscCoupon,String UsageParameter) throws Exception
	{
		WebDriverWait webWait;
	    webWait = new WebDriverWait(driver,1000);
	   //Create Objects
	   //Objects for Accessing AccountFunctions_Plans
	    AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
	   //Objects for Accessing Account Functions
		AccountFunctions objfnctns = new AccountFunctions();
		//Objects for Accessing AccountFunctions_Invoices
		AccountFunctions_Invoices ObjInvoices = new AccountFunctions_Invoices();		

		try
		{
			Log.startTestCase("Cls_VerifyAdditionofMultipleCouponsAndDiscount");
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
								
		 
			//TODO: Load Data for specified account for current date.
       		String LOAD_USAGE_FILE_PATH = System.getProperty("user.dir")
                    + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
                    + System.getProperty("file.separator") + strFilename;
       		
   			
				//TODO : Adjust Billing dates for the Account to Genarate Invoice  	
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
				
				int i;
	    		String[] arrCoupon = DiscCoupon.split("#",2);
	    		Log.info("the size of the array is :: "+arrCoupon.length);
	    		for (i=0;i<arrCoupon.length;i++ )
	    		{
		   			//TODO:; Assign Credit Coupon to the Account and verify it further in the Invoice
		    		boolean blnAssignCoupon =  ObjInvoices.fn_AssignCreditCoupon(driver, webWait, strAcctNum, arrCoupon[i], strTestCaseName);
		    		if(blnAssignCoupon == false)
		    			assertTrue(false, "ERROR: Coupon couldn't be assigned to the Account : "+strAcctNum);
		    		else
		    			Log.info("Coupon code has been Assigned successfully"+arrCoupon[i]);
	    		}
	    		
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
 
                Thread.sleep(2000);
				
	            double d=0.0,f=0.0,g=0.0;
	            double finalAmount=0.0;
	            
	          //TODO : //To identify the Invoice Table and capture the Invoice price
				  WebElement InvoiceTable = driver.findElement(By.xpath("//*[contains(@id,'DataTables_Table_')]"));
				  
				  WebElement InvoiceBody = InvoiceTable.findElement(By.tagName("tbody"));
				  
				  List<WebElement>InvoiceRows = InvoiceBody.findElements(By.tagName("tr"));
	
					  for (WebElement row : InvoiceRows)
					  {
						 List<WebElement>InvoiceColumns = row.findElements(By.tagName("td"));
	
						  for (WebElement col : InvoiceColumns)
						  {
							  

										 if (col.getText().contains(UsageParameter))
										 {
				                			  String[] split =  StringUtils.split(col.getText(),"\\$)");
											  String price1 =InvoiceColumns.get(6).getText();
											   f = Double.parseDouble(price1.replace(",",""));		                			  	                			                			  
				                			   double UsagePrice = Double.parseDouble((split[1]));
				                			   d = (UsagePrice*4)/100;
				                			   Log.info("=========Start of Validations for the % revenue discount Coupon=====");
				                			   Log.info("the discount Applied Amount is :: "+UsagePrice); 
				                			   d=UsagePrice-d;
				                			   String UnitAmount = InvoiceColumns.get(5).getText();      			   								 
				                    		   double e= Double.parseDouble(UnitAmount.replace(",",""));
				                			   if (d==e)
				                			   {
				                	           Log.info("the discount is applied correctly :: "+d); 
				                			   }
				                			   else
				                			   {
				                		       Log.error("the discount is not applied correctly :: "+d); 
				                		       Reporter.log("the discount is not applied correctly :: "+d);
				                			   }
				                			   Log.info("=========End of Validations for the % revenue discount Coupon=====");
										 }  
												 
										 if (col.getText().contains("This is a Discount Rule created for testing the TC"))
										  {
												String price =InvoiceColumns.get(6).getText();
												 
												String[] split =  StringUtils.split(price,"(,)");
												 
												g=Double.parseDouble(split[0]);
												
					                			Log.info("=========Start of Validations for the tc 461462 discount Coupon=====");
	
							            		if ((g==125))
						                		{
						                		  Log.info("the Value of the credit coupon is applied and deducted correctly"+InvoiceColumns.get(6).getText());									        
						                		}	
							            		else
						                		{
						                		  Log.error("the Value of the credit coupon is not applied and deducted correctly"+InvoiceColumns.get(6).getText());
					                		      Reporter.log("the Value of the credit coupon is not applied and deducted correctly"+InvoiceColumns.get(6).getText());
						                		}
							            			   
												String colValue = driver.findElement(By.xpath("//table/tbody/tr[4]/td[7]")).getText();
												   
					                			finalAmount=f-g;
					                			String value=colValue.replace(",","");
				 		                		double comAmount= Double.parseDouble(value);
					                		    if (Math.round(comAmount)==Math.round(finalAmount))
					                		    {
					                			  Log.info("the discount is deducted correctly and matched:: "+comAmount);
						                	    }
					                		    else
					                		    {
					                			  Log.error("the discount is not deducted correctly and matched:: "+comAmount);
					                			  Reporter.log("the discount is not deducted correctly and matched:: "+comAmount);
						                	    }
					                		    Log.info("=========End of Validations for the tc 461462 discount Coupon=====");
										}
													 					  
					              	}
											 
						   }
						        

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
    @DataProvider(name="objTestData")
    public Object[][] data() throws DataDrivenFrameworkException 
    {
    	Utils objutils = new Utils();
    	return objutils.data1("TestCaseDetails", "VerifyMultipleCoupons");
    	
    }
    
	@AfterClass
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
}
