/*
 Author     		:	Namrata Akarte
 
 Modify By          :   Nashim Khan
 Class Name			: 	Cls_ValidateVoidPerforms  
 Purpose     		: 	Purpose of this file is :
						To validate the reports after voids/ credit performs for parent child account
												
 Date       		:	12/04/2015
 Modify Date        :   27/05/2016
 Version Information:	Version 1.0
 Version Changes 1.1: 	Added After Class
 
 Jira #				:	QAABE-481
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "TestCaseDetails" 
 						worksheet for excel "TestData.xlsx".
 						3. Parent and Child accounts to be created and updated in the test data sheet.

 Test Steps 		:	1. Find or create an account with existing usage, invoices, & where the parent has made a payment 
						2. Void a payment 
						3. Apply a Service Credit 
						4. Apply a Cash Credit 
						5. Void an Invoice
						6. Validate correct balances reflected after voids/credits  
						7. Run & Validate Payment Reports (Payment Txns by Account & Payment Txns by Date) reflect voided payment				
 
 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/
package testCases.AccountTestCases;


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

import pageObjects.AccountsAccountOverview;
import pageObjects.AccountsPaymentsCredits;
import pageObjects.AccountsStatementsInvoices;
import pageObjects.AriaEOM;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Invoices;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;

public class Cls_ValidateVoidPerforms extends VerificationMethods {
	
	public static WebDriver driver;
    public static String strTestCaseName = "ValidateVoidPerforms";    
	public static WebDriverWait webWait;
    public static WebElement webElement;
    
    @BeforeClass
	 public static void beforeMethod()throws Exception  {	
    	
		DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
		
		//TODO: Set Chrome driver as the driver to launch the test.
		driver = utility.Utils.fn_SetChromeDriverProperties();
		
		//TODO: initialize the webWait
		webWait = new WebDriverWait(driver, 60);		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.info("Login to application");
		//This parameters are taken from Exec file and its reference is available in EnvironmentDetails
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
	 }	
    
    @Test(dataProvider="objTestData", description="VerifyVoidPerforms")
    public void fn_ValidateVoidPerforms(String strAcctNum ,String strMPI,String strSPI,String strFilename, String strAPIURL,String strCreditAmt,
    		String strInvoiceType , String strPendingInvDate,String strRefCode, String strReasonCode ,
    		/*String strCreditReason */ String strServiceCode, String strAlternativeCode) {
    	
    	//TODO: Create objects to contents into.
    	AccountFunctions objAcctFnctns = new AccountFunctions();
		AccountFunctions_Invoices objAcctFuncInvoices = new AccountFunctions_Invoices();	
		AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
		AccountsStatementsInvoices  objAccountStatInvoice=new AccountsStatementsInvoices();
		AccountsPaymentsCredits  objAccountPayCredits=new AccountsPaymentsCredits();
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();		
		AccountsAccountOverview  objAccountOverview= new AccountsAccountOverview();		
		AriaEOM objAriaEOM = new AriaEOM();
		Utils objUtils=new Utils();
		
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAcct = new Cls_ChangeDeleteClientDefinedFieldActns();
		
		Log.startTestCase(strTestCaseName);
		
		try {
			
			//TODO: Load Data for specified account for current date.
       		String LOAD_USAGE_FILE_PATH = System.getProperty("user.dir")
                    + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
                    + System.getProperty("file.separator") + strFilename;
       
       		//TODO : Adjust Billing dates for the Account to Generate Invoice  	
       		boolean blnAdjustDate=objAcctFnctns.fn_AdjustAcctBillDates(driver, webWait, strAPIURL, strAcctNum,strMPI, 
       											strInvoiceType, strPendingInvDate,strTestCaseName);				
			if (blnAdjustDate==true)
				 Log.info("Account Billing Date has been set successfully");			
			else {
				
				Log.info("ERROR: Account Billing Date has not been set");
			
			}
			//TODO : Read Full Balance 
			objClientDefAcct.AccountSearch(driver, webWait, strAcctNum);
			objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);	
			String strFullBanace=objAccountOverview.fn_getAcctOvrvwFullBalance(driver, webWait).getText();			
			Log.info("Full BAlance : "+strFullBanace);
			objAriaEOM.fn_clickAccounts(driver, webWait).click();
			
			String strReturnPayment=objAcctFuncInvoices.fn_GeneratePayments_NetTerms(driver, webWait, strAcctNum, strPendingInvDate, strRefCode, 1, false,  strCreditAmt, strTestCaseName);
			String[] strPaymentId = strReturnPayment.split("#");
			objAriaEOM.fn_clickAccounts(driver, webWait).click();
			
			
			//TODO : Read Full Balance 
			objClientDefAcct.AccountSearch(driver, webWait, strAcctNum);
			objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);	
			String strFullBanaceAfterPay=objAccountOverview.fn_getAcctOvrvwFullBalance(driver, webWait).getText();
			Log.info("Full BAlance : "+strFullBanaceAfterPay);
			objAriaEOM.fn_clickAccounts(driver, webWait).click();
			objAcctFuncInvoices.fn_VoidPayments(driver, webWait, strAcctNum, strPaymentId[0], strFullBanaceAfterPay);
			objAriaEOM.fn_clickAccounts(driver, webWait).click();
			//TODO : Read Full Balance 
			
			objClientDefAcct.AccountSearch(driver, webWait, strAcctNum);
			objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);	
			String strFullBanaceAfterVoid=objAccountOverview.fn_getAcctOvrvwFullBalance(driver, webWait).getText();
			Log.info("Full BAlance : "+strFullBanace);
			objAriaEOM.fn_clickAccounts(driver, webWait).click();
			if (strFullBanaceAfterVoid.equals(strFullBanace)) {
				Log.info("Full Balance is Match after Void Payment "+strFullBanaceAfterVoid);
				
			}
			
			//TODO : Apply  Cash Credit 					
			//objAcctFuncInvoices.fn_AssignCashCredits(driver, webWait, strAcctNum, strReasonCode, 1, strCreditAmt, strFullBanaceAfterVoid);      

			//TODO : Apply Service Credit
			//objAcctFuncInvoices.fn_AssignServiceCredits(driver, webWait, strAcctNum, strCreditAmt, strReasonCode, strServiceCode, strAlternativeCode, 0, 0, strFullBanaceAfterVoid);
		
			//TODO : Appy Usage			
			//TODO: Fetching the Date for Loading Usage	
	    	String strLoadDataDate=objUtils.fn_getLoadDataDate();
	    	Log.info("Loading Account Usage for the date '"+strLoadDataDate+"'..");	           	
	    	Thread.sleep(200);
	    	
	    	//TODO: Generate MPID and SPID
	    	String strMPInstance = objAcctFuncPlans.fn_GetPlanInstanceID(driver, webWait, strAcctNum, strTestCaseName, strMPI);
	    	String[] arrMPID = strMPInstance.split("#");
	    	String strSPInstance = objAcctFuncPlans.fn_GetPlanInstanceID(driver, webWait, strAcctNum, strTestCaseName, strSPI);	    	
	    	String[] arrSPID = strSPInstance.split("#"); 
	   		    
	    	//TODO: Loading the Usage
	    	objAcctFnctns.fn_LoadAccountUsage(driver, webWait, strAPIURL,LOAD_USAGE_FILE_PATH, strAcctNum, strLoadDataDate,arrMPID[1], arrSPID[1]);
	    	String strOptionType=arrMPID[0]+" - "+strMPI;	
	    	
	    	//TODO: Advance Invoice for the Account for which data is currently uploaded & Verify Print Invoice.	           
	    	boolean blnInvoiceGenerated = objAcctFuncInvoices.fn_GenerateInvoice(driver, webWait, strAcctNum,strInvoiceType,"MPI",strOptionType, strTestCaseName);
           	if(blnInvoiceGenerated == true) {
           		
	   		    if(strInvoiceType.equalsIgnoreCase("Immediate")) {
	   		    	Log.info("Invoice type 'Immediate' is generated; approval not required !"); 
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
           	objClientDefAct.AccountSearch(driver, webWait, strAcctNum);
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
           	}
           	
           	// TODO :Validate correct balances reflected After void Credits
           	objAcctFuncInvoices.fn_SelectLatestInvoice(driver, webWait);  
		  	WebElement webInvoiceTable = objAriaEOM.fn_getDataTable(driver, webWait);			  
	        WebElement webInvoiceBody = webInvoiceTable.findElement(By.tagName("tbody"));			  
	        List<WebElement> lstwebInvoiceRows = webInvoiceBody.findElements(By.tagName("tr"));
	        for (WebElement row : lstwebInvoiceRows) {
					  
	        	List<WebElement> col = row.findElements(By.tagName("td"));  
	        	
	        	String strTableBalenceDesc=col.get(9).getText().toString().trim();
	        	
	        	//TODO :Read the text to know what Tag we are dealing with for the Value to be read.        	       	
	    		if(strTableBalenceDesc.equals("0.00"))
	    			
	    			Log.info("The voiding process has been successfully Veifyed For Selected Invoice:");
	    	}
	        
	        
	        //TODO : Balance In Aria Reflects Properly.		
	        objClientDefAcct.AccountSearch(driver, webWait, strAcctNum);
			objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);	
	        strFullBanace=objAccountOverview.fn_getAcctOvrvwFullBalance(driver, webWait).getText();
			Log.info("Full BAlance : "+strFullBanace);
		
		} catch (Exception exception) {
			Log.error("ERROR: Test case for Void Performs failed with exception :"+exception.toString());
			Reporter.log("ERROR: Test case for Void Performs failed with exception :"+exception.toString());
			exception.printStackTrace();
		}
    }
    
    @DataProvider(name="objTestData")
	public Object[][] data() throws DataDrivenFrameworkException    {
	   	
    	Utils objutils = new Utils();
    return objutils.data1("TestCaseDetails", "VerifyVoidPerforms");    	
   }
	
	
	@AfterClass
	public void afterMethod() throws Exception	 {
		
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 } 
    
}

		
	
	 