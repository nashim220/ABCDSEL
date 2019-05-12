/*
 Author     		:	Namrata Akarte
 Class Name			: 	Cls_TerminatePlanAccount  
 Purpose     		: 	Purpose of this file is :
						 1.If Invoice can be generated for the account for which supplemental plan is terminated 
						 2.To validate if the usage can be loaded on the terminated account.
												
 Date       		:	12/21/2015
 
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
 						
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/
	package testCases.AccountTestCases;
	
	import java.util.ArrayList;
	import java.util.List;
	
	import org.apache.log4j.xml.DOMConfigurator;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Reporter;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;
	
	import pageObjects.AccountPlansUsage;
	import pageObjects.BanAccount;
	import pageObjects.ChangeDeleteClientDefinedFields;
	import utility.ApiHandler;
	import utility.EnvironmentDetails;
	import utility.Log;
	import utility.Utils;
	import appModules.AccountFunctions;
	import appModules.AccountFunctions_Invoices;
	import appModules.AccountFunctions_Plans;
	import appModules.AriaLoginLogout;
	import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
	import atu.ddf.exceptions.DataDrivenFrameworkException;

	public class Cls_TerminatePlanAccount {
	
		public static WebDriver driver;
	    public static String[] arrSearchFields = null;	// this string array will hold the Fields names for the Client Defined Fields.
	    public static String strFieldValue = null; 	// this string will hold the subsequent field value for the Client Defined Fields.
		public static WebDriverWait webWait;
	    public static WebElement webElement;
	    
	    @BeforeClass
		 public void beforeMethod()throws Exception
		 {	
			DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.		
			
			//TODO: Set Chrome driver as the driver to launch the test.
			driver = utility.Utils.fn_SetChromeDriverProperties();
					
			//TODO: initialize the webWait
			webWait = new WebDriverWait(driver, 240);
			
			EnvironmentDetails objEnv = new EnvironmentDetails();
			Log.info("Login to application");
			//This parameters are taken from Exec file and its reference is available in EnvironmentDetails
			AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
		 }	
		
	    @Test(dataProvider = "objectTestData1")
	    public void fn_TerminatePlanAccount(String strdFileName,String strdWorksheet,String strAPIURL, String strFilename, String strPlanName, String strNewAccountID, String strTestCaseName) {
		  
	    	AccountFunctions_Invoices ObjInvoices = new AccountFunctions_Invoices();
			AccountFunctions objfnctns = new AccountFunctions();
			AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
			AccountPlansUsage objAccountPlans = new AccountPlansUsage();
			Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
			ChangeDeleteClientDefinedFields objClientDefFields = new ChangeDeleteClientDefinedFields();
			ApiHandler objApiHandler = new ApiHandler();
						
			String strPlanNumber = "";
			ArrayList<String> arrListResponses = new ArrayList();
			
			//TODO: To Load Data for specified account for current date, set the test data file path
	 		String LOAD_USAGE_FILE_PATH = System.getProperty("user.dir")
	              + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
	              + System.getProperty("file.separator") + strFilename;   		
	 			
			try {
				
				Log.startTestCase(strTestCaseName);
							
				strPlanNumber = objAcctFuncPlans.fn_GetPlans(driver, webWait, strPlanName, strTestCaseName);
			
				if(strPlanNumber.contentEquals(""))
					throw new Exception("Supplemental Plan Number couldn't be retrieved !");				
					
				boolean blnSupplementalPlansStatus = objAcctFuncPlans.fn_VerifySupplementalPlans(driver, webWait, strNewAccountID, strTestCaseName);
				if(blnSupplementalPlansStatus == false)
				{
					objAcctFuncPlans.fn_AssignSuppPlan(driver, webWait, strNewAccountID, strPlanNumber, strAPIURL);
					Log.info("Supplemental Plan with number "+strPlanNumber+" has been assigned to the Account Number: "+strNewAccountID);
				}		
		 			 		
				 //TODO : Adjust Billing dates for the Account to Generate Invoice  	
				 boolean blnAdjustDate=objfnctns.fn_AdjustAcctBillDates(driver, webWait, strAPIURL, strNewAccountID, strTestCaseName);
				 if (blnAdjustDate==true)
				 {
					 Log.info("Account Billing Date has been set successfully");
				 }
				 else
				 {
					 Log.info("ERROR: Account Billing Date has not been set");
					 utility.VerificationMethods.assertTrue(false, "ERROR: Since the Billing Date's can't be adjusted, Invoicing will fail, so exiting TC !");
				 }
				 
				//TODO: Fetching the Date for Loading Usage	
		 		String strLoadDataDate = objfnctns.fn_getLoadDataDate();
		     	Log.info("Loading Account Usage for the date '"+strLoadDataDate+"'..");
		     	
		     	Thread.sleep(200);
		     	//TODO: Loading the Usage
		     	arrListResponses = objfnctns.fn_LoadAccountUsage(driver, webWait, strAPIURL, LOAD_USAGE_FILE_PATH, strNewAccountID, strLoadDataDate);
		     	Thread.sleep(2000); 	     	
		     	
		     	//Terminate Supplemental Plan
		     	//TODO : Click on Plans & Services link	     	
		     	webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Accounts"))).click();
		    	objAccountPlans.fn_clickPlansServices(driver, webWait).click();
		    	
		    	//TODO : Click Supplemental plans tab
		    	objAccountPlans.fn_getSupplementalPlansDataTable(driver, webWait);
		    	objAccountPlans.fn_clickSupplementalPlans(driver, webWait).click();
		    	
		    	//TODO: Read the Data table and click the Plan Units to note the Plan Instance ID.
		    	WebElement tblSupplementalPlans = objAccountPlans.fn_getSupplementalPlansDataTable(driver, webWait).findElement(By.tagName("tbody"));
				List<WebElement> lstwebSupplementalPlansRows = tblSupplementalPlans.findElements(By.tagName("tr"));
				
				tableSupplPlan : for(WebElement rows : lstwebSupplementalPlansRows)
				{
					List<WebElement> cols = rows.findElements(By.tagName("td"));
					String strTempPlanName = strPlanName.replaceAll("_", " ");
					if(cols.size() != 0)	// this is to avoid the header read in the loop.
					{
						String strReadStatus = cols.get(0).getAttribute("innerText").toString().trim();
						if(strReadStatus.contentEquals("Remove"))
						{
							String strSuppPlanName = cols.get(1).getAttribute("innerText").toString().trim();
							if (strSuppPlanName.contains(strTempPlanName))
							{
								driver.findElement(By.linkText("Remove")).click();
								Thread.sleep(2000);
								
								WebElement webEleAssignmentScope = driver.findElement(By.name("inAssignOptionsIgnore"));
								Select lstAssignmentScope = new Select(webEleAssignmentScope);
								lstAssignmentScope.selectByVisibleText("De-assign Immediately");
								
								Thread.sleep(2000);
								driver.findElement(By.id("submit-button")).click();
								
								Thread.sleep(2000);
								driver.findElement(By.xpath("//input[@value='Save Changes']")).click();
								
								break tableSupplPlan;
							}
						}					
	
					}				
				}	     	
		     	
		     	//Generate Invoice				
				//TODO : Click on Accounts link
		     	objClientDefFields.fn_AcctLink(driver, webWait).click();
		     	
		     	//TODO: Generate Advance Invoice for the Account for which data is currently uploaded & Verify Print Invoice.
		     	boolean blnInvoiceGenerated = ObjInvoices.fn_GenerateInvoice(driver, webWait, strNewAccountID, "Advanced");	     	
		     	
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
		     		utility.VerificationMethods.assertTrue(false, "ERROR: Invoice is not generated, so exiting TC !");
		     	}
		     		     	
		     	// Terminate Account
		     	//TODO : Click on Accounts link
		     	objClientDefFields.fn_AcctLink(driver, webWait).click();
		     	
				objClientDefAct.AccountSearch(driver, webWait, strNewAccountID);				
							
				//TODO : Click on the Account Overview tab
				driver.findElement(By.linkText("Account Overview")).click();
				
				//TODO : Verify Account status
				webElement = webWait.until(ExpectedConditions.elementToBeClickable(BanAccount.fn_verifyStatus()));
				utility.Utils.takeScreenshot(driver, strTestCaseName);			
				if(!driver.findElement(BanAccount.fn_verifyStatus()).getAttribute("innerText").toString().contains("TERMINATED"))
				{
					//TODO : Click on the Status link
					driver.findElement(BanAccount.fn_clickStatusAccountsOverview()).click();
					
					//TODO : Click on the Change Account Status button
					webElement = webWait.until(ExpectedConditions.elementToBeClickable(BanAccount.fn_clickChangeAccountStatusLink()));
					driver.findElement(BanAccount.fn_clickChangeAccountStatusLink()).click();
					
					//TODO : Select status code as Terminated
					WebElement tableStatus = driver.findElement(BanAccount.fn_getStatusDataTable()).findElement(By.tagName("tbody"));
					List<WebElement>rowsStatus = tableStatus.findElements(By.tagName("tr"));
					
					for (WebElement row : rowsStatus){
						List<WebElement>colsStatus = row.findElements(By.tagName("td"));
						
						if (colsStatus.size()!=0)
						{
							String strDescription = colsStatus.get(1).getText();
							Log.info("Status Description is "+strDescription);
							if (strDescription.equalsIgnoreCase("Terminated")){
								//TODO : Select the radio button
								colsStatus.get(0).click();
								Log.info("Account Status is selected as Terminated");
								
								//TODO : Select Days Until Change Days value as "None(change immediately)"
								driver.findElement(BanAccount.fn_clickStatusChange()).click();
								driver.findElement(BanAccount.fn_clickStatusChange()).sendKeys("None (change immediately)");
								
								//TODO : Enter CSR Comments
								driver.findElement(BanAccount.fn_getCSRComments()).clear();
								driver.findElement(BanAccount.fn_getCSRComments()).sendKeys("QAABE-362_Test_CSR_Comments");
								
								//TODO : Click Change Account Status button
								driver.findElement(BanAccount.fn_clickChangeAccountStatusButton()).click();	
								
								//TODO : Click the Save Status Change		            
					            driver.findElement(BanAccount.fn_clickSaveStatusChangeButton()).click();
	
					            break;
							}												
						}
					}
					
					// this wait is for getting the table showing the modified Account Status.
					webElement = webWait.until(ExpectedConditions.elementToBeClickable(BanAccount.fn_getAccountStatusHistoryDataTable()));
	
					utility.Utils.takeScreenshot(driver, strTestCaseName);		            
	    			Log.info("The Save Status change button has been clicked for confirmation of changes done.");
	    			
	    			//TODO : Read Account Status History table.
		            WebElement tblAccountStatusHistory = driver.findElement(BanAccount.fn_getAccountStatusHistoryDataTable()).findElement(By.tagName("tbody"));
		            	            
		            List<WebElement> rowsAccountStatusHistory = tblAccountStatusHistory.findElements(By.tagName("tr"));
		           
		            // TODO : Verify if Terminated status has reflected or not.
		            for(WebElement row : rowsAccountStatusHistory)
		            {
		            	List <WebElement> colsAccountStatusHistory = row.findElements(By.tagName("td"));
		            	if (colsAccountStatusHistory.size() != 0)	// to skip rows read for header.
		            	{
		            		if(colsAccountStatusHistory.get(0).getText().toString().trim().contentEquals("TERMINATED"))
		            		{
		            			if(colsAccountStatusHistory.get(6).getText().toString().trim().contains("- Present"))
		            			{
		            				Utils.takeScreenshot(driver, strTestCaseName);
		            				Log.info("The Account has been successfully Terminated.");		            				
		            				break;	
		            			}
		            		}
		            		else if (!colsAccountStatusHistory.iterator().hasNext())	// Termination failure.
		            		{
		            			Log.error("TERMINATED STATUS not seen in the list, hence exiting with error !");
		            			Reporter.log("ERROR: TERMINATED STATUS not seen in the list, hence exiting with error !");
		            			
		            		}
		            	}
		            }    				
	
				}
				
		     	//TODO : Load usage on terminated account
		     	Thread.sleep(200);
		     	//TODO: Loading the Usage		     	
		     	arrListResponses = objfnctns.fn_LoadAccountUsage(driver, webWait, strAPIURL, LOAD_USAGE_FILE_PATH, strNewAccountID, strLoadDataDate);
		     	Thread.sleep(2000);
		     	
		     	for (String strResponse : arrListResponses){
		     		if (strResponse.equalsIgnoreCase("usage code or type not on active plan"))
		     		{
		     			Log.info("Error message is displayed as usage is loaded on the terminated account with which no supplimental plan is attached");
		     			throw new AssertionError();
		     		}
		     		else
		     		{
		     			Log.error("Data is not loaded on terminated account with which no supplimental plan is associated");
		                Reporter.log("Data is not loaded on terminated account with which no supplimental plan is associated");
		                
		     		}
		     		
		     	}
		     			     	
		     	String strFullURL = objfnctns.fn_GetFullUrl(driver, webWait, strAPIURL, "update_acct_status");
				String strPostURL = strFullURL +"&account_no="+strNewAccountID+"&status_cd=1";
				Log.info("Making an API call to reset the terminated account's status to active with call: "+strPostURL);
				objApiHandler.makeSimplePostCall(strPostURL);				        	
				
			}
	     	
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			catch (AssertionError Exception){
				
     			Log.info("Error message is displayed as usage is loaded on the terminated account with which no supplimental plan is attached");
                
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
			 return objUtils.data1("TestCaseDetails", "LoadUsageAfterTerminatingAccount");
		 } 
	}
