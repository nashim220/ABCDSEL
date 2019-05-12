/*
 Author     		:	Namrata Akarte
 Class Name			: 	Cls_TerminatePlanAccount  
 Purpose     		: 	Purpose of this file is :
						 1.If Invoice can be generated for the account for which supplemental plan is terminated 
						 2.To validate if the usage can be loaded on the terminated account.
												
 Date       		:	12/21/2015
 Modify Date        :   06/09/2016
 Modify By          :   Nashim Khan
 Version Information:	Version 1.0
 
 Jira #				:	QAABE-362
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "TestCaseDetails"
 						worksheets for excel "TestData.xlsx" in 'LoadUsageAfterTerminatingAccount'.
 						3. Data to be used to load usage should be populated in newrecord_usage_updated.csv
 						
 Test Steps 		:	1. Assign Supplemental Plan and upload the usage.
 						2. Terminate the Supplemental Plan for the account created in Step1.
 						3. Generate an Invoice.
 						4. Terminate the account
 						5. Load the usage on the terminated account
 						
 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/
	package testCases.AccountTestCases;
	
	
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

import pageObjects.AccountPlansUsage;		
import pageObjects.AccountsPaymentsCredits;
import pageObjects.AccountsPlans;
import pageObjects.AriaEOM;
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

	public class Cls_TerminatePlanAccount  extends VerificationMethods{
	
		public static WebDriver driver;
	    public static String[] arrSearchFields = null;	// this string array will hold the Fields names for the Client Defined Fields.
	    public static String strFieldValue = null; 	// this string will hold the subsequent field value for the Client Defined Fields.
		public static WebDriverWait webWait;
	    public static WebElement webElement;
	    
	    String strTestCaseName ="TerminatePlanAccount";
	    @BeforeClass
		 public void beforeMethod()throws Exception
		 {	
			DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.		
			
			//TODO: Set Chrome driver as the driver to launch the test.
			driver = utility.Utils.fn_SetChromeDriverProperties();
					
			//TODO: initialize the webWait
			webWait = new WebDriverWait(driver, 180);
			
			EnvironmentDetails objEnv = new EnvironmentDetails();
			Log.info("Login to application");
			//This parameters are taken from Exec file and its reference is available in EnvironmentDetails
			AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
		 }	
		
	    @Test(dataProvider = "objectTestData1" ,description="TerminatePlanAccount")
	    public void fn_TerminatePlanAccount(String strAccountNumber, String strMPI , String strSPI, 
	    		String strAPIURL, String strFilename, String strInvoiceType) {
		  
	    	//TODO : Create Objects 
	    	
	    	AccountFunctions_Invoices ObjInvoices = new AccountFunctions_Invoices();
			AccountFunctions objAcctFnctns = new AccountFunctions();
			AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
			AccountPlansUsage objAccountPlans = new AccountPlansUsage();
			Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();			
			ApiHandler objApiHandler = new ApiHandler();
			Utils objUtils=new Utils();
			AriaEOM objAriaEOM = new AriaEOM();
			AccountsPlans objAcctplans=new AccountsPlans();
			AccountsPaymentsCredits  objPaymentsCredits =new AccountsPaymentsCredits();
			String strPendingInvDate ="";
			Boolean blnSpiRemoved = false;
			String strPlanNumber = "";
			ArrayList<String> arrListResponses = new ArrayList();
	 			
			try {
				
				Log.startTestCase(strTestCaseName);
				
				//TODO: Load Data for specified account for current date.
	       		String LOAD_USAGE_FILE_PATH = System.getProperty("user.dir")
	                    + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
	                    + System.getProperty("file.separator") + strFilename;	       
		 		
				//TODO : Navigate to supplemental plan and find it 
				String strSupplementalPlansID = objAcctFuncPlans.fn_VerifySupplementalPlans(driver, webWait, strAccountNumber, strSPI, strTestCaseName);
				
				if(strSupplementalPlansID =="") {
				
					objAcctFuncPlans.fn_AssignSuppPlan(driver, webWait, strAccountNumber, strPlanNumber, strAPIURL);
					Log.info("Supplemental Plan with number "+strPlanNumber+" has been assigned to the Account Number: "+strAccountNumber);
				}
				
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
		    			    	
		     	//TODO :Terminate Supplemental Plan
		     	//TODO : Click on Plans link 
		    	objClientDefAct.AccountSearch(driver, webWait, strAccountNumber);			
				objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);
				objAriaEOM.fn_clickAccountPlans(driver, webWait).click();
				objAriaEOM.fn_getDataTable(driver, webWait);
				objAccountPlans.fn_clickPlansTab(driver, webWait).click();	// navigate to Plans tab.
				
		    	//TODO: Read the Data table and click the Plan Units to note the Plan Instance ID.
		    	WebElement tblSupplementalPlans = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
				List<WebElement> lstwebSupplementalPlansRows = tblSupplementalPlans.findElements(By.tagName("tr"));
				
				for(WebElement rows : lstwebSupplementalPlansRows) {
					
					List<WebElement> cols = rows.findElements(By.tagName("td"));
					
					if(cols.size() != 0) {	// this is to avoid the header read in the loop.
					
						String strReadStatus = cols.get(2).getAttribute("innerText").toString().trim();
						if(strReadStatus.contentEquals(strSupplementalPlansID)) {
							
							objAcctplans.fn_clickPlaninstanceId(driver, webWait, arrSPID[0]).click();
							 Log.info(" Supp plan Id has clicked !" +strReadStatus);
							 Utils.takeScreenshot(driver, strTestCaseName);
							 break;
						}
						
					} 
				}
				//TODO : Navigate to Remove and get Message. 
				Thread.sleep(2000);
				objAcctplans.fn_clickRemovePlan(driver, webWait).click();
				objAcctplans.fn_clickRemoveBtn(driver, webWait);
				objAcctplans.fn_clickRemoveBtn(driver, webWait).click();
				Thread.sleep(2000);    	
		    	String strRemoveMessage =  objPaymentsCredits.fn_getStatusMessage(driver, webWait).getAttribute("innerText").toString().trim();
				if(strRemoveMessage.contains("Plan De-assignment Saved.")) {
					blnSpiRemoved = true;
					Log.info("Spi removed sucessfully :"+strRemoveMessage);
				}
				else
					VerificationMethods.assertTrue(false, "ERROR: An error has occurred while assigning Coupon, TC can't proceed further !");
	     	
		     	
				//TODO: Generate Advance Invoice for the Account for which data is currently uploaded & Verify Print Invoice.		
				//TODO : Click on Accounts link
				objAriaEOM.fn_clickAccounts(driver, webWait).click();
		     	//TODO: Advance Invoice for the Account for which data is currently uploaded & Verify Print Invoice.	           
		    	boolean blnInvoiceGenerated = ObjInvoices.fn_GenerateInvoice(driver, webWait, strAccountNumber,strInvoiceType,"MPI",strOptionType, strTestCaseName);
		    	if(blnInvoiceGenerated == true) {
	           		
		   		    if(strInvoiceType.equalsIgnoreCase("Immediate")) {
		   		    	Log.info("Invoice type 'Immediate' is generated; approval not required !");
		   		    	ObjInvoices.fn_GenerateInvoiceStatement(driver, webWait, strOptionType, strTestCaseName);
		   		    }
		   		    else 
		   		    {
		   		    	boolean blnInvoiceApproved = ObjInvoices.fn_ApproveAdvancedInvoices(driver, webWait);
		   		    	Utils.takeScreenshot(driver, strTestCaseName);
		   		    	if(blnInvoiceApproved == false)
		   		    		Log.info("ERROR: Invoice generated couldn't be approved !");
		   		
		   		    	//Since Invoice has been generated and Approved, we generate the Statement.
		   		    	objAriaEOM.fn_clickAccounts(driver, webWait).click();
		   		    	ObjInvoices.fn_GenerateInvoiceStatement(driver, webWait, strOptionType, strTestCaseName);
		   		   }
	           	}
	           	//TODO : Validate  status of terminated account
	           	objClientDefAct.AccountSearch(driver, webWait, strAccountNumber);			
	    		objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);
	    		objAriaEOM.fn_clickAccountPlans(driver, webWait).click();
	    		objAriaEOM.fn_getDataTable(driver, webWait);
	    		objAccountPlans.fn_clickPlansTab(driver, webWait).click();	// navigate to Plans tab.
	    		
	        	//TODO: Read the Data table and click the Plan Units to note the Plan Instance ID.
	    		tblSupplementalPlans = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
	    		lstwebSupplementalPlansRows = tblSupplementalPlans.findElements(By.tagName("tr"));
	    		for(WebElement rows : lstwebSupplementalPlansRows) {
	    			
	    			List<WebElement> cols = rows.findElements(By.tagName("td"));
					
					if(cols.size() != 0) {	// this is to avoid the header read in the loop.
					
						String strReadPlanInstanceId = cols.get(0).getAttribute("innerText").toString().trim();
						String strReadStatus = cols.get(4).getAttribute("innerText").toString().trim();
						if(strReadPlanInstanceId.contentEquals(arrSPID[0]) && strReadStatus.equals("Pending Cancellation")) {
							
							 objAcctplans.fn_clickPlaninstanceId(driver, webWait, arrSPID[0]).click();
							 Log.info(" Supp plan Id has clicked !" +strReadStatus);
							 Utils.takeScreenshot(driver, strTestCaseName);
							 break;
						}						
					} 	    			
	    		}
	    		//TODO : Navigate to Plan status
	           	Thread.sleep(2000);
	           	objAcctplans.fn_clickPlanStatus(driver, webWait).click();	           	
	           	objAriaEOM.fn_getDataTable(driver, webWait);
	           	objAcctplans.fn_clickChangePlanStatus(driver, webWait).click();
	           	Thread.sleep(2500);
	           	objAcctplans.fn_clickTermination(driver, webWait).click();
	           	objAcctplans.fn_clickSave(driver, webWait).click();
	           	Thread.sleep(2500);
	           	objAcctplans.fn_clickMessaageDlgYes(driver, webWait).click();
	           	String strStatusMessage =  objPaymentsCredits.fn_getStatusMessage(driver, webWait).getAttribute("innerText").toString().trim();
				if(strStatusMessage.contains("successfully")) {
					
					blnSpiRemoved = true;
					Log.info("Plan Status Changed successfully i.e. Terminated successfully  :"+strStatusMessage);
				}
				else
					VerificationMethods.assertTrue(false, "ERROR: An error has occurred while Apply Termination , TC can't proceed further !");	  
				
		     	//TODO : Load usage on terminated account		     	     	
		     	//TODO: Loading the Usage
		     	arrListResponses = objAcctFnctns.fn_LoadAccountUsage(driver, webWait, strAPIURL,LOAD_USAGE_FILE_PATH, strAccountNumber, strLoadDataDate,arrMPID[1], arrSPID[1]);
		     	Thread.sleep(2000);		     	
		     	for (String strResponse : arrListResponses) {
		     		
		     		if (strResponse.equalsIgnoreCase("usage code or type not on active plan")) {
		     		
		     			Log.info("Error message is displayed as usage is loaded on the terminated account with which no supplimental plan is attached");
		     			throw new AssertionError();
		     		}
		     		else {
		     		
		     			Log.error("Data is not loaded on terminated account with which no supplimental plan is associated");
		                Reporter.log("Data is not loaded on terminated account with which no supplimental plan is associated");
		                
		     		}
		     		
		     	}
		     	String strFullURL = objAcctFnctns.fn_GetFullUrl(driver, webWait, strAPIURL, "update_acct_status");
				String strPostURL = strFullURL +"&account_no="+strAccountNumber+"&status_cd=1";
				Log.info("Making an API call to reset the terminated account's status to active with call: "+strPostURL);
				objApiHandler.makeSimplePostCall(strPostURL);
				
				
			}
	     	
			catch (Exception exception) {
				Log.error("ERROR: Test case for Terminate Plan Account   Fields failed with exception :"+exception.toString());
				Reporter.log("ERROR: Test case for Terminate Plan Account Fields failed with exception :"+exception.toString());
				exception.printStackTrace();
			}
			
			catch (AssertionError Exception){
				
     			Log.info("Error message is displayed as usage is loaded on the terminated account with which no supplimental plan is attached"+Exception.toString());
     			Exception.printStackTrace();
			}
			
			Log.endTestCase(strTestCaseName);
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
			 return objUtils.data1("TestCaseDetails", "TerminatePlanAccount");
		 } 
	}
