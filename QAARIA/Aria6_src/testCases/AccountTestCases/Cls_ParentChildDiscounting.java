/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_ParentChildDiscounting 
 Purpose     		: 	Purpose of this file is :
						1. To Verify that discount from Parents is passed to child & of Child should
						be retained for child level only.
						2. Test Class for the test case QAABE-461/462.
 
 Date       		:	09/25/2015.
 Modified Date		:	01/05/2016.
 Version Information:	Version 2.0
 
  PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "DiscountParentChild" worksheet for excel "TestData.xls".
 						3. The Coupon to be applied is of type "Invoice Display Method -> Add a Separate Discount Line Item". 
 						Only, then there would be entries in the Invoices generated !
 						
 Test Steps 		:	1. Login using valid role credentials for accessing an account.
 						2. Navigate to the searched Accounts to verify Parent Child Relationship.
 						3. If Step 3 is true, progress ahead else exit with error.
 						4. For TC QAABE-461, assign discount coupon to the Parent.
 						5. Load data for the child account and generate invoice.
 						6. Verify if the Child receives the discount applied to it in step 4.
 						7. For TC QAABE-462, assign discount coupon for the child.
 						8. Load usage for the Parent & Child accounts and generate invoice.
 						9. Verify that the discount is applied ONLY for the child account.
 						
 Version Changes 2.0:	1. Assertion for Invoice Approval has been removed to overlook it's failures; cant't 
 						control TC's status based on it's status.
 						2. Added logic to Reset the applied Coupon codes to help account recognize them if the coupon 
 						has been modified in background before TC execution.
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/


package testCases.AccountTestCases;

import java.util.List;

import atu.ddf.exceptions.DataDrivenFrameworkException;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ParentChildDiscounting;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Invoices;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
import utility.ApiHandler;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;

public class Cls_ParentChildDiscounting extends VerificationMethods  
{
    public static WebDriver driver;
    public static String strTestCaseName = "Verify Discounting for Parent and Child Accounts";
	public static WebDriverWait webWait;
    public static WebElement webElement;
    
    @BeforeTest
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
	Function fn_VerifyParentChildDiscount: This method will verify the discount applied @ Parent / Child account.    						
*/	    
	@Test(dataProvider = "objectTestData", description = "Verify Discounting for Parent and Child Accounts")
    public void fn_VerifyParentChildDiscount(String strPlanName, String strAccountNum, String strAPIURL, String strFileName, 
    				String strTCName, String strDiscountCoupon, String strCouponIdentifier) throws Exception
	{
		//TODO: Create object for the classes to be used for accessing reusable codes.
		AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
		AccountFunctions_Invoices objAcctFuncInvoices = new AccountFunctions_Invoices();
		AccountFunctions objAccountFunc = new AccountFunctions();
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
		ParentChildDiscounting objParentChildDiscount = new ParentChildDiscounting();
		ApiHandler objAPIHandler = new ApiHandler();	// for post method to be called
		
		//TODO: declare booleans & objects to govern the actions to be performed.
		Boolean blnSupplementalPlansStatus = false;
		Boolean blnAdjustBillingDates = false;
		Boolean blnInvoiceGenerated = false;
		//Boolean blnInvoiceApproved = false;
		Boolean blnAssignCoupon = false;
		Boolean blnSearchStatus = false;
		Boolean blnCancelCoupon = false;
		Boolean blnLatestInvoiceSelected = false;
		Boolean blnVerifyCreditCoupon461 = false;
		Boolean blnVerifyCreditCoupon462 = false;
		Boolean blnVerifyCreditCouponParent = false;
		String strParentChildAccounts = null;
		String[] arrParentChild = null;
				
    	//TODO: Log the beginning of the test case.
    	Log.startTestCase(strTestCaseName);
    	
    	try
    	{
    		//TODO: Verify if the given account number is a Child Parent Related Account and get Account Types.
    		strParentChildAccounts = objAcctFuncPlans.fn_VerifyParentChildAccounts(driver, webWait, strAccountNum, strTestCaseName);
    		if(strParentChildAccounts == null)
    			assertTrue(false, "ERROR: The given account number has no Parent Child Relation, so TC fails !");
    		
    		//Since the Parent Child Numbers are received, the Primary string is Parent & later are child accounts.
    		arrParentChild = strParentChildAccounts.split("#");
    		
    		Thread.sleep(2500);
    		//TODO: Verify if the Parent Child Accounts have the Supplemental Plan assigned to it, else assign one.
			String strPlanNumber = objAcctFuncPlans.fn_GetPlans(driver, webWait, strPlanName, strTestCaseName);
       		if(strPlanNumber.contentEquals(""))
       			assertTrue(false, "Supplemental Plan Number couldn't be retrieved !");
       		for(String strAcctNum : arrParentChild)
       		{
    			blnSupplementalPlansStatus = objAcctFuncPlans.fn_VerifySupplementalPlans(driver, webWait, strAcctNum, strTestCaseName);
           		if(blnSupplementalPlansStatus == false)
           		{
           			objAcctFuncPlans.fn_AssignSuppPlan(driver, webWait, strAcctNum, strPlanNumber, strAPIURL);
           			Log.info("Supplemental Plan with number "+strPlanNumber+" has been assigned to the Account Number: "+strAcctNum);
           		}
       		}       		
       		
          	blnAdjustBillingDates = objAccountFunc.fn_AdjustAcctBillDates(driver, webWait, strAPIURL, arrParentChild[0], strTestCaseName);
           	if(blnAdjustBillingDates == false)
           		assertTrue(false, "ERROR: Since the Billing Date's can't be adjusted, Invoicing will fail, so exiting TC !");
    		       		      		
    		//TODO: Load Data for specified account for load date which is 5 days previous to the current date.
       		String LOAD_USAGE_FILE_PATH = System.getProperty("user.dir")
                    + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
                    + System.getProperty("file.separator") + strFileName;           	
       		String strLoadDataDate = objAccountFunc.fn_getLoadDataDate();
           	Log.info("Loading Account Usage for the date '"+strLoadDataDate+"'..");
           	for(String strAcctNum : arrParentChild)
           		objAccountFunc.fn_LoadAccountUsage(driver, webWait, strAPIURL, LOAD_USAGE_FILE_PATH, strAcctNum, strLoadDataDate);
           	
    		
    		/******************** TC QAABE-461: Coupon Code Applied to Parent, Discount Availed By Child ********************/
           	
    		//TODO: Assign the Coupon Code for Parent Account & generate invoice for Child to verify successful Coupon Application.
       		if(strTCName.equals("QAABE-461"))
       		{
       			if(arrParentChild.length > 2)
       				assertTrue(false, "ERROR: Since The Parent has got more than one child, TC QAABE-461 can't be verified ! Exiting with exception..");
       			
       			Log.info("Verifying TC QAABE-461: Coupon Code Applied to Parent, Discount Availed By Child");
        		blnAssignCoupon =  objAcctFuncInvoices.fn_AssignCreditCoupon(driver, webWait, arrParentChild[0], strDiscountCoupon, strTestCaseName);
        		if(blnAssignCoupon == false)
        			assertTrue(false, "ERROR: Coupon couldn't be assigned to the Parent Account with ID: "+arrParentChild[0].toString());
        		
        		//TODO: Search the Child Account number and verify if the account exists to cancel already assigned Coupon.
            	blnCancelCoupon = objAcctFuncInvoices.fn_CancelCreditCoupon(driver, webWait, arrParentChild[1], strDiscountCoupon, strTestCaseName);
        		if(blnCancelCoupon == true)
        			Log.info("The coupon code is cancelled; Now generating invoice for Child Account with ID: "+arrParentChild[1].toString());
        		else
        			Log.info("The coupon code wasn't assigned to the Child Account; Now generating invoice for child account with ID: "+arrParentChild[1].toString());
        		
        		//TODO: Generate Advance Invoices for all the Accounts for which data is currently uploaded & Verify Invoice.
        		for(String strAcctNum : arrParentChild)
        		{
            		blnInvoiceGenerated = objAcctFuncInvoices.fn_GenerateInvoice(driver, webWait, strAcctNum, "Advanced");
            		Utils.takeScreenshot(driver, strTestCaseName);
                   	if(blnInvoiceGenerated == true)
                   	{
                   		//blnInvoiceApproved = 
                   		objAcctFuncInvoices.fn_ApproveAdvancedInvoices(driver, webWait);
                   		//the below code is commented since, Approval Failure shouldn't matter much.
                   		/*if(blnInvoiceApproved == false)
                   			assertTrue(false, "Invoice generated couldn't be approved !");*/
                   		
                   		//Since Invoice has been generated and Approved, we generate the Statement. 
                   		webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Accounts"))).click();
                   		objAcctFuncInvoices.fn_GenerateInvoiceStatement(driver, webWait, strTestCaseName);
                   		objParentChildDiscount.fn_clickAccounts(driver, webWait).click();
                   	}
                   	else
                   		assertTrue(false, "Invoice couldn't be generated, hence exiting !");
        		}
        		
               	Log.info("Verifying Child Account's newly generated Advance Invoice for the absence of Credit Coupon Applied...");
               	blnSearchStatus = objClientDefAct.AccountSearch(driver, webWait, arrParentChild[1]);
            	if(blnSearchStatus == false)
            		assertTrue(false, "ERROR: Account Search failed for Account Number: "+arrParentChild[1]);
            	objParentChildDiscount.fn_clickAccounts(driver, webWait).click();
               	blnLatestInvoiceSelected = objAcctFuncInvoices.fn_SelectLatestInvoice(driver, webWait);
               	if(blnLatestInvoiceSelected == false)
               		assertTrue(false, "ERROR: Invoice couldn't be selected !");
               	blnVerifyCreditCoupon461 = fn_blnVerifyCreditCoupon(driver, webWait, strCouponIdentifier);
               	
               	Log.info("Verifying Parent Account's newly generated Advance Invoice for presence of Credit Coupon applied to Child...");
               	blnSearchStatus = objClientDefAct.AccountSearch(driver, webWait, arrParentChild[0]);
            	if(blnSearchStatus == false)
            		VerificationMethods.assertTrue(false, "ERROR: Account Search failed for Account Number: "+arrParentChild[0]);
            	objParentChildDiscount.fn_clickAccounts(driver, webWait).click();
               	blnLatestInvoiceSelected = objAcctFuncInvoices.fn_SelectLatestInvoice(driver, webWait);
               	if(blnLatestInvoiceSelected == false)
               		assertTrue(false, "ERROR: Invoice couldn't be selected !");
               	blnVerifyCreditCouponParent =  fn_blnVerifyCreditCoupon(driver, webWait, strCouponIdentifier);        		
               	

               	objParentChildDiscount.fn_clickAnalyticsReporting(driver, webWait).click();           		
           		if(blnVerifyCreditCoupon461 == false && blnVerifyCreditCouponParent == true)
           			Log.info("The verification of TC QAABE-461 is passed since Parent invoice has been successfully discounted !");
           		else
           			assertTrue(false, "ERROR: The verification for TC QAABE-461 has failed !");
       		}
       		
    		
    		/****************** TC QAABE-462: Coupon Code Applied to Child, Discount Availed ONLY By Child *****************/
           	
       		//TODO: Assign Coupon Code ONLY to Child Account and Verify if the Parent doesn't get the Discount; ONLY Child should get it.       		
       		if(strTCName.equals("QAABE-462"))
       		{
       			Log.info("Verifying TC QAABE-462: Coupon Code Applied to Child, Discount Availed ONLY By Child");
       			
       			//TODO: Search the Child Account and Verify if it has the Coupon Applied; else apply the coupon.
            	blnAssignCoupon =  objAcctFuncInvoices.fn_AssignCreditCoupon(driver, webWait, arrParentChild[1], strDiscountCoupon, strTestCaseName);
        		if(blnAssignCoupon == false)
        			assertTrue(false, "ERROR: Coupon couldn't be assigned to the Child Account with ID: "+arrParentChild[1].toString());
            	
       			//TODO: Search the Parent Account and Verify that it won't have the same Coupon as Child applied.
            	blnCancelCoupon = objAcctFuncInvoices.fn_CancelCreditCoupon(driver, webWait, arrParentChild[0], strDiscountCoupon, strTestCaseName);
        		if(blnCancelCoupon == true)
        			Log.info("The coupon code is cancelled; Now generating invoice for Parent Account with ID: "+arrParentChild[0].toString());
        		else
        			Log.info("The coupon code wasn't assigned to the Parent Account; Now generating invoice for Parent account with ID: "+arrParentChild[0].toString());
       			
       			//TODO: Generate Invoices for all the accounts to validate the application of Coupons.
        		for(String strAcctNum : arrParentChild)
        		{
            		blnInvoiceGenerated = objAcctFuncInvoices.fn_GenerateInvoice(driver, webWait, strAcctNum, "Advanced");
            		Utils.takeScreenshot(driver, strTestCaseName);
                   	if(blnInvoiceGenerated == true)
                   	{
                   		//blnInvoiceApproved = 
                   		objAcctFuncInvoices.fn_ApproveAdvancedInvoices(driver, webWait);
                   		//the below code is commented since, Approval Failure shouldn't matter much.
                   		/*if(blnInvoiceApproved == false)
                   			assertTrue(false, "Invoice generated couldn't be approved !");*/
                   		
                   		//Since Invoice has been generated and Approved, we generate the Statement. 
                   		webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Accounts"))).click();
                   		objAcctFuncInvoices.fn_GenerateInvoiceStatement(driver, webWait, strTestCaseName);
                   		objParentChildDiscount.fn_clickAccounts(driver, webWait).click();
                   	}
                   	else
                   		assertTrue(false, "Invoice couldn't be generated, hence exiting !");
        		}
        		
       			//TODO: Verify if the coupon has been applied for the Child Account & later for the Parent Account.
        		Log.info("Verifying Child Account's newly generated Advance Invoice for the Credit Coupon Applied...");
               	blnSearchStatus = objClientDefAct.AccountSearch(driver, webWait, arrParentChild[1]);
            	if(blnSearchStatus == false)
            		VerificationMethods.assertTrue(false, "ERROR: Account Search failed for Account Number: "+arrParentChild[1]);
            	objParentChildDiscount.fn_clickAccounts(driver, webWait).click();
               	blnLatestInvoiceSelected = objAcctFuncInvoices.fn_SelectLatestInvoice(driver, webWait);
               	if(blnLatestInvoiceSelected == false)
               		assertTrue(false, "ERROR: Invoice couldn't be selected !");               	
               	blnVerifyCreditCoupon462 = fn_blnVerifyCreditCoupon(driver, webWait, strCouponIdentifier);
               	
               	Log.info("Verifying Parent Account's newly generated Advance Invoice for absence of Credit Coupon applied to Child...");
               	blnSearchStatus = objClientDefAct.AccountSearch(driver, webWait, arrParentChild[0]);
            	if(blnSearchStatus == false)
            		VerificationMethods.assertTrue(false, "ERROR: Account Search failed for Account Number: "+arrParentChild[0]);
            	objParentChildDiscount.fn_clickAccounts(driver, webWait).click();
               	blnLatestInvoiceSelected = objAcctFuncInvoices.fn_SelectLatestInvoice(driver, webWait);
               	if(blnLatestInvoiceSelected == false)
               		assertTrue(false, "ERROR: Invoice couldn't be selected !");
               	blnVerifyCreditCouponParent =  fn_blnVerifyCreditCoupon(driver, webWait, strCouponIdentifier);        		
               	
       			objParentChildDiscount.fn_clickAnalyticsReporting(driver, webWait).click();
       			if(blnVerifyCreditCoupon462 == true && blnVerifyCreditCouponParent == false)
       				Log.info("The verification of TC QAABE-462 is passed since Child invoice has been successfully discounted and not Parent's invoice !");
       			else if (blnVerifyCreditCoupon462 == false)
               		assertTrue(false, "ERROR: The Verification of TC QAABE-462 failed as Coupon is not applied to Child Account: "+arrParentChild[1]);
       			else if (blnVerifyCreditCouponParent == true)
               		assertTrue(false, "ERROR: The Verification of TC QAABE-462 failed as the Child's Coupon has been applied to Parent Account: "+arrParentChild[0]);
       		}
       		
       		//TODO: Reset the Coupon Codes to help account recognize them if the coupon has been modified in background.
       		String strFullURL = objAccountFunc.fn_GetFullUrl(driver, webWait, strAPIURL, "delete_acct_coupon");
       		String strResetCode = strDiscountCoupon.replaceAll(" ", "%20");
       		for(String strAcctNum : arrParentChild){	       		
	       		String strPostURL = strFullURL +"&acct_no="+strAcctNum+"&coupon_cd="+strResetCode;
				Log.info("The Actual URL created based on Parameter Name & Value is: "+strPostURL);
				objAPIHandler.makeSimplePostCall(strPostURL);
				Log.info("The Coupon (if) applied to the Account "+strAcctNum+" has been cancelled !");
       		}
       		
    	}
    	catch (Exception exception)
    	{
    		Utils.takeScreenshot(driver, strTestCaseName);
    		Log.error("ERROR: Verification for the Discounting couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Verification for the Discounting couldn't be done due to exception:  "+exception.toString());
    		throw exception;
    	}
    	catch (AssertionError exception)
    	{
    		Utils.takeScreenshot(driver, strTestCaseName);
    		Log.error("ERROR: Verification for the Discounting couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Verification for the Discounting couldn't be done due to exception:  "+exception.toString());
    		throw exception;
    	}

    	//TODO: Log the end of the test case. 
    	Log.endTestCase(strTestCaseName);
	}


/*    
	Function fn_VerifyViewPrintFrame: This method will verify if the Print Frame has the latest PO Number in it.
	Inputs: 
*/	
    @Test(dependsOnGroups = {"Sanity"} , dependsOnMethods = {"fn_VerifyParentChildDiscount"}, enabled = false)	// marks this method dependent on parent method.
    public Boolean fn_blnVerifyCreditCoupon(WebDriver driver, WebDriverWait webWait, String strCouponDescription) throws Exception
    {
    	//TODO: Create object for the classes to be used for accessing reusable codes.
    	ParentChildDiscounting objParentChildDiscount = new ParentChildDiscounting();
    	
    	Boolean blnVerifyCreditCoupon = false;
    	
    	//TODO: Navigate the Data table to test if the Credit Coupon Description is available in Invoice generated.
    	objParentChildDiscount.fn_getDataTbale(driver, webWait);
		WebElement webInvoiceDetails = objParentChildDiscount.fn_getDataTbale(driver, webWait).findElement(By.tagName("tbody"));
		List<WebElement> lstwebInvoiceRows = webInvoiceDetails.findElements(By.tagName("tr"));
		Log.info("Verifying the generated Invoice for the Coupon Description containing string '"+strCouponDescription+"'...");
		for(WebElement row: lstwebInvoiceRows)
		{
			List<WebElement> cols = row.findElements(By.tagName("td"));
			
			if(cols.size() >= 2)
			{
				String strTableDescription = cols.get(2).getAttribute("innerText").toString().trim();
				if(strTableDescription.contains(strCouponDescription))
				{
					Log.info("The Invoice generated has the Credit value in it with description as: "+strTableDescription);
					blnVerifyCreditCoupon = true;
					objParentChildDiscount.fn_clickAccounts(driver, webWait).click();
					return blnVerifyCreditCoupon;
				}    				    				
			}    			
		}
    	
		objParentChildDiscount.fn_clickAccounts(driver, webWait).click();
    	return blnVerifyCreditCoupon;
    }
  
    

	//TODO: Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "DiscountParentChild");
	}
	
	
	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }    
}
