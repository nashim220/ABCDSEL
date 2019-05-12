/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_UpdatePlanUnitByAPI 
 Purpose     		: 	Purpose of this file is :
						1. To update the Plan Units By API.
						2. Verify the updated information using API Logs on UI.
						3. This is automation of TC: QAABE-179.

 Date       		:	04/05/2016 
 Modified Date		:	
 Version Information:	Version 1.0
 
 PreCondition 		:	1. Role based Login required for calling API's.
 						2. Data to be populated in the "TestCaseDetails" & "UpdatePlanUnitAPI" worksheet 
 						for excel "TestData.xls".

 Test Steps 		:	1. Login using valid role credentials for updating plan units for existing customer account.
 						2. Search the customer account to updates it's information.
 						3. Call API for updating the plan units for the plan associated with the user account.
 						4. Verify these details under the API Logs and on UI.
 
 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AccountTestCases;

import java.util.List;

import atu.ddf.exceptions.DataDrivenFrameworkException;

import org.apache.log4j.xml.DOMConfigurator;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.ApiHandler;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;
import pageObjects.AriaEOM;
import pageObjects.ConfigurationAuditLogs;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;

public class Cls_UpdatePlanUnitByAPI extends VerificationMethods {
    
	public static WebDriver driver;
    public static String strTestCaseName = "Verify Plan Units Updated By API.";
	public static WebDriverWait webWait;
    public static WebElement webElement;
    
    @BeforeClass
    public void beforeMethod()throws Exception {
    	
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
    
    
/*    
    Function fn_VerifyCustomerInformation: This is the actual Test NG test for Verifying the updated Customer Information.
*/
	@Test(dataProvider = "objectTestData", description = "Verify Plan Units are updated using the API call.")
	public void fn_UpdatePlanUnitsByAPI(String strMPI, String strSPI, String strAccountNum, String strAPIURL, 
								String strPlanUnitFieldName, String strPlanUnitFieldValue) throws Exception {
		
    	//TODO: Create class objects to access the common methods.
    	AccountFunctions objAccountFunc = new AccountFunctions();	// for methods relating to Accounts functionality.
    	AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
    	ApiHandler objAPIHandler = new ApiHandler();	// for post method to be called
    	AriaEOM objAriaEOM = new AriaEOM();
    	    	
    	String strRestCall = "update_acct_plan_m";	//specify the rest call to be made.
    	Boolean blnVerifyAPILogs = false;

    	//TODO: Mark the beginning of the Test.
       	Log.startTestCase(strTestCaseName);
       	
       	try {
       		
       		//TODO: Get the Supplemental Plan Number for the Plan name requested.
       		String strPlanNumber = objAcctFuncPlans.fn_GetPlans(driver, webWait, strSPI, strTestCaseName);       		

       		//TODO: Verify if the Supplemental Plans has been assigned to the Account Number, else assign it.
       		String strSPIID = objAcctFuncPlans.fn_VerifySupplementalPlans(driver, webWait, strAccountNum, 
					strSPI, strTestCaseName);
       		if(strSPIID.contentEquals("")){
       			
       			objAcctFuncPlans.fn_AssignSuppPlan(driver, webWait, strAccountNum, strPlanNumber, strAPIURL);
       			Log.info("Supplemental Plan with number "+strPlanNumber+" has been assigned to the Account Number: "+strAccountNum);
       		}
/*       		
       		//TODO: Get the Plan Unit Instance ID for the plan assigned to the account under test.
       		objAriaEOM.fn_clickAnalyticsReporting(driver, webWait).click();
       		String strInstanceID = objAcctFuncPlans.fn_GetPlanInstanceID(driver, webWait, strAccountNum, strTestCaseName, strSPI);
*/       		
       		//TODO: Get the various Plan Unit Field Names & Values and give the post call.
       		String[] arrPlanUnitFieldName = strPlanUnitFieldName.split("#");	//get various plan unit field names here.
       		String[] arrPlanUnitFieldValue = strPlanUnitFieldValue.split("#");	//get field values for the field names here.
       		Integer intArrayLength = arrPlanUnitFieldName.length;
       		
       		//TODO: design the API call's to add data & make a POST call & then verify it in API Logs.
       		String strPostURL = "";
       		String strFullURL = objAccountFunc.fn_GetFullUrl(driver, webWait, strAPIURL, strRestCall);
       		Integer intAPIValidations = 0; 
       		for(Integer i = 0; i < intArrayLength; i ++) {	//get the field names first.
       			
/*       		
 * 				URL used for generating the response:
 * 				client_no=6000076&auth_key=7RKnupEJM8b3u9jx9tMqAF9uBvjBKvEg&rest_call=update_acct_plan_m&acct_no=212335
       			&client_plan_instance_id=54948
       			&plan_instance_field_name[0]=WORKPLACE_BUDGET
       			&plan_instance_field_value[0]=132
       			&plan_instance_field_directive[0]=2
       			&output_format=json&usage_threshold_applicability=UT
       			
*/       			
       			strPostURL = strFullURL +"&acct_no="+strAccountNum +"&client_plan_instance_id="+strSPIID 
       							+"&plan_instance_field_name[0]="+arrPlanUnitFieldName[i].toString() 
       							+"&plan_instance_field_value[0]="+arrPlanUnitFieldValue[i] 
       							+"&plan_instance_field_directive[0]=2" + "&usage_threshold_applicability=UT";
           		Log.info("The Actual URL created based on Parameter Name & Value is: "+strPostURL);           		
	           	List<JSONObject> apiResponse = objAPIHandler.makeSimplePostCall(strPostURL);
	            //TODO: Verify JSON response to validate success.
	            for(JSONObject response : apiResponse) {
	            	
	            	int errorCode = Integer.parseInt(response.get("error_code").toString());	
	                if(errorCode != 0) {
	                	
	                    String errorMsg = response.get("error_msg").toString();
	                    Log.error("ERROR: API call failed with message: "+errorMsg);
	                    Reporter.log("ERROR: API call failed with message: " + errorMsg);
	                 }
	                 else {
	                	 
	                	/************************* First Level Verification: Web Service API Audit Logs *************************/
	                	//TODO: Verify the Web Service API Audit Logs for the success of API call
	             		blnVerifyAPILogs = fn_VerifyAuditLogs(driver, webWait, strRestCall, strAccountNum, arrPlanUnitFieldName[i]
	             																				, arrPlanUnitFieldValue[i], strTestCaseName);
	             		if(blnVerifyAPILogs == true) {
	             			
	             			Log.info("The Verification for the API call in API Logs is successfully done for field name '"+arrPlanUnitFieldName[i]
	             					+"' with value: "+arrPlanUnitFieldValue[i]);
	             			intAPIValidations ++;
	             		}	             		
	                 }
	             }
       		}
       		if(intAPIValidations == 0 || intAPIValidations < arrPlanUnitFieldName.length) {
       			
       			Log.error("EROR: The Verification for the API call in API Logs is unsuccessful & hence exiting with exception !");
     			Reporter.log("EROR: The Verification for the API call in API Logs is unsuccessful & hence exiting with exception !");
     			assertEquals(false, true);
       		}
    		
    		/************************* Second Level Verification: Product Field Details *************************/
       		//TODO: Verify the Product Fields under the Supplemental Plan on UI for update details done by API.
       		objAriaEOM.fn_clickAnalyticsReporting(driver, webWait).click();
       		Thread.sleep(1500);
    		Boolean blnVerifyPlanUnits = fn_VerifySupplementalPlansProdFields(driver, webWait, strSPIID, strAccountNum, 
    										arrPlanUnitFieldName, arrPlanUnitFieldValue, strTestCaseName);
    		if(blnVerifyPlanUnits == true)
    			Log.info("The Verification for the API update call on UI is successfully done !");
    		else {
    			
    			Log.error("The Verification for the API update call on UI is unsuccessful !");
    			Reporter.log("The Verification for the API update call on UI is unsuccessful !");
    			assertEquals(false, true);    			
    		}
       	}
       	catch (Exception exception) {
       		
       		Log.error("ERROR: Couldn't Verify data for the Plan Units added by API due to exception: "+exception.toString());
       		Reporter.log("ERROR: Couldn't Verify data for the Plan Units added by API due to exception: "+exception.toString());
       		throw exception;
       	}
       	catch (AssertionError exception){
       		
       		Log.error("ERROR: Couldn't Verify data for the Plan Units added by API due to exception: "+exception.toString());
       		Reporter.log("ERROR: Couldn't Verify data for the Plan Units added by API due to exception: "+exception.toString());
       		throw exception;
       	}
       	
       	//TODO: Mark the end of the Test.
       	Log.endTestCase(strTestCaseName);    	
	}
    
	
/*
	Function fn_VerifyAuditLogs: Takes in the Account details and the actual Parameter Names and Values for Verification in API Logs.
								 Based on the verifications, returns Boolean result.
*/	
	public Boolean fn_VerifyAuditLogs(WebDriver driver, WebDriverWait webWait, String strRestCall, String strAccountNum, 
					String strParamNames, String strParamValues, String strTestCaseName) throws Exception {
		
		//TODO: Create class objects to access the common methods.
		AriaEOM objAriaEOM = new AriaEOM();
		ConfigurationAuditLogs objAuditLogs = new ConfigurationAuditLogs();
		Utils objUtils = new Utils();
		
		Boolean blnVerified = false;

		String strLogParamNames = null;	//string array to store the field name read from logs.
		String strLogParamValues = null;	//string array to store the field values read from logs.
		String strCurrentDate = objUtils.getDateYearFirst();
		
		//TODO: Verify the data updated via API on UI @ Web Service API in Audit Logs.
		Thread.sleep(1500);
		objAriaEOM.fn_clickConfiguration(driver, webWait).click();
		objAriaEOM.fn_clickAuditLogs(driver, webWait).click();
		objAriaEOM.fn_clickWebServiceAPI(driver, webWait).click();
		
		objAuditLogs.fn_clickRequestFromDate(driver, webWait).clear();
		objAuditLogs.fn_clickRequestFromDate(driver, webWait).sendKeys(strCurrentDate);
		objAuditLogs.fn_clickRequestToDate(driver, webWait).clear();
		objAuditLogs.fn_clickRequestToDate(driver, webWait).sendKeys(strCurrentDate);
		objAuditLogs.fn_clickRequestFromDate(driver, webWait).click();
		Thread.sleep(1500);
		objAuditLogs.fn_setRequestDateHour(driver, webWait).clear();
		objAuditLogs.fn_setRequestDateHour(driver, webWait).sendKeys("0");
		objAuditLogs.fn_setRequestDateMinute(driver, webWait).clear();
		objAuditLogs.fn_setRequestDateMinute(driver, webWait).sendKeys("0");
		objAuditLogs.fn_clickRequestDateTimeDone(driver, webWait).click();
		objAuditLogs.fn_clickRequestToDate(driver, webWait).click();
		Thread.sleep(1500);
		objAuditLogs.fn_setRequestDateHour(driver, webWait).clear();
		objAuditLogs.fn_setRequestDateHour(driver, webWait).sendKeys("23");
		objAuditLogs.fn_setRequestDateMinute(driver, webWait).clear();
		objAuditLogs.fn_setRequestDateMinute(driver, webWait).sendKeys("59");
		objAuditLogs.fn_clickRequestDateTimeDone(driver, webWait).click();
		
		objAuditLogs.fn_clickAdd(driver, webWait).click();
		objAuditLogs.fn_selectFieldDropDown(driver, webWait).selectByVisibleText("Account Number");		
		WebElement webSearchTextField = objAuditLogs.fn_getTextField(driver, webWait); 
		webSearchTextField.sendKeys(strAccountNum);
		objAuditLogs.fn_clickSearchButton(driver, webWait).click();
		objAuditLogs.fn_getDataTableAPILogs(driver, webWait);
		Thread.sleep(3500);
		Utils.takeScreenshot(driver, strTestCaseName);
		
		//TODO: Read the Logs and navigate to inner details of Logs for collecting values.
    	WebElement tblAPISearchLogs = objAuditLogs.fn_getDataTableAPILogs(driver, webWait).findElement(By.tagName("tbody"));
		List<WebElement> lstwebAPISearchLogsRows = tblAPISearchLogs.findElements(By.tagName("tr"));
		for(WebElement rows : lstwebAPISearchLogsRows) {

			List<WebElement> cols = rows.findElements(By.tagName("td"));
			
			if(cols.size() != 0) {	// this is to avoid the header read in the loop.

				String strAPIName = cols.get(0).getAttribute("innerText").toString().trim();
				String strErrorMessage = cols.get(6).getAttribute("innerText").toString().trim();
				if(strAPIName.equalsIgnoreCase(strRestCall) && strErrorMessage.equalsIgnoreCase("OK")) {

					cols.get(0).click();	//navigate to the detail logs for the API call.
					objAuditLogs.fn_getDataTableDetailsTab(driver, webWait);
					Utils.takeScreenshot(driver, strTestCaseName);
					
					objAuditLogs.fn_clickInputParameters(driver, webWait).click();					
					//TODO: Read the Input Parameters data table for final verification.
					Thread.sleep(2500);
			    	WebElement tblInputParameters = objAuditLogs.fn_getDataTableInputParametersTab(driver, webWait).findElement(By.tagName("tbody"));
					List<WebElement> lstwebInputParametersRows = tblInputParameters.findElements(By.tagName("tr"));
					Utils.takeScreenshot(driver, strTestCaseName);
					Log.info("Verifying the API Logs for the Input Parameter values...");
					for(WebElement rowsIPTable : lstwebInputParametersRows) {

						List<WebElement> colsIPTable = rowsIPTable.findElements(By.tagName("td"));
						
						if(colsIPTable.size() != 0) {	// this is to avoid the header read in the loop.

							//TODO: get the Param Name and then it's Values as per the expected fields. 
							strLogParamNames = colsIPTable.get(0).getAttribute("innerText").toString().trim();
							if(strLogParamNames.contains("field_name[")) {

								strLogParamValues = colsIPTable.get(1).getAttribute("innerText").toString().trim();;
								if(strLogParamValues.equalsIgnoreCase(strParamNames)) {

									Log.info("Data verified for the parameter name: "+strLogParamValues);
									blnVerified = true;
								}
								else {
									
									Log.error("ERROR: Data verification failed for the parameter name: "+strLogParamValues);
									Reporter.log("ERROR: Data verification failed for the parameter name: "+strLogParamValues);
									blnVerified = false;
								}
							}

							if(strLogParamNames.contains("field_value[")) {

								strLogParamValues = colsIPTable.get(1).getAttribute("innerText").toString().trim();;
								if(strLogParamValues.equalsIgnoreCase(strParamValues) && blnVerified == true) {

									Log.info("Data verified for the parameter value: "+strLogParamValues);
									blnVerified = true;
									objAriaEOM.fn_clickAuditLogs(driver, webWait).click();
									Thread.sleep(1500);
									objAriaEOM.fn_clickConfiguration(driver, webWait).click();
									
									return blnVerified;									
								}
								else {
									
									Log.error("ERROR: Data verification failed for the parameter value: "+strLogParamValues);
									Reporter.log("ERROR: Data verification failed for the parameter value: "+strLogParamValues);
									blnVerified = false;
									objAriaEOM.fn_clickAuditLogs(driver, webWait).click();
									Thread.sleep(1500);
									objAriaEOM.fn_clickConfiguration(driver, webWait).click();
									
									return blnVerified;
								}
							}
						}
					}
					break;	//exit the for loop since we have achieved the verifications by now.
				}				
			}
			else if(!(cols.iterator().hasNext())) {
				
				Log.error("ERROR: There are no logs generated for the Account Number Searched !");
				Reporter.log("ERROR: There are no logs generated for the Account Number Searched !");
				return blnVerified;
			}
		}
		
		objAriaEOM.fn_clickAuditLogs(driver, webWait).click();
		return blnVerified;
	}
    
	
/*
	Function fn_VerifyAuditLogs: Takes in the Account details and the array for actual Parameter Names and Values for Verification on UI.
								 Based on the verifications, returns Boolean result.
*/
	public Boolean fn_VerifySupplementalPlansProdFields(WebDriver driver, WebDriverWait webWait, String strSPIID, String strAccountNum,
						String[] arrPlanUnitFieldName, String[] arrPlanUnitFieldValue, String strTestCaseName) throws Exception	{
		
		//TODO: Create class objects to access the common methods.
		ConfigurationAuditLogs objAuditLogs = new ConfigurationAuditLogs();	// for page objects required for Web Service API & Audit Logs.
    	Cls_ChangeDeleteClientDefinedFieldActns objClientDefFields = new Cls_ChangeDeleteClientDefinedFieldActns();
    	AriaEOM objAriaEOM = new AriaEOM();
		
		Boolean blnFieldsVerified = false;
		String strFieldNames = null;
		String strFieldValues = null;				
		
		//TODO: Verify the data updated via API on UI @ Supplemental Field Details page.
		objAriaEOM.fn_clickAnalyticsReporting(driver, webWait).click();
		objClientDefFields.AccountSearch(driver, webWait, strAccountNum);
		objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);
		objAriaEOM.fn_clickAccountPlans(driver, webWait).click();
		objAuditLogs.fn_clickPlansEdit(driver, webWait);
		Thread.sleep(3500);
		String xpathSPI = "//td/span/a[text()="+strSPIID+"]";
		driver.findElement(By.xpath(xpathSPI)).click();
		
		//TODO: Read the Product Fields and verify for the actual values passed.
    	Actions objActions = new Actions(driver);
    	objActions.sendKeys(Keys.PAGE_DOWN);
    	Thread.sleep(500);
    	objActions.click(objAuditLogs.fn_getPlanInstanceFieldsTable(driver, webWait)).perform();
    	Thread.sleep(500);
    	objActions.sendKeys(Keys.PAGE_DOWN);
    	Thread.sleep(500);
    	objActions.click(objAuditLogs.fn_getPlanInstanceFieldsTable(driver, webWait)).perform();
    	Thread.sleep(500);
		Log.info("Verifying data for the Product Fields...");		
    	WebElement tblProductFields = objAuditLogs.fn_getPlanInstanceFieldsTable(driver, webWait).findElement(By.tagName("tbody"));
		List<WebElement> lstwebProductFieldsRows = tblProductFields.findElements(By.tagName("tr"));
		Utils.takeScreenshot(driver, strTestCaseName);
		for(WebElement rows : lstwebProductFieldsRows) {
			
			List<WebElement> cols = rows.findElements(By.tagName("td"));
			
			if(cols.size() != 0) {	// this is to avoid the header read in the loop.

				//TODO: get the Param Name and then it's Values as per the expected fields. 
				strFieldNames = cols.get(0).getAttribute("innerText").toString().trim();
				for(Integer i = 0; i < arrPlanUnitFieldName.length; i ++) {
					
					if(strFieldNames.equalsIgnoreCase(arrPlanUnitFieldName[i])) {
						
						strFieldValues = cols.get(2).getAttribute("innerText").toString().trim();								
						if(strFieldValues.equalsIgnoreCase(arrPlanUnitFieldValue[i])) {
							
							Log.info("The data updated via API has been verified on UI for field name '"+strFieldNames+"' with value: "+strFieldValues);
							blnFieldsVerified = true;							
						}
						else {
							
							Log.error("ERROR: The data updated via API couldn't be verified on UI for field name '"+strFieldNames+"' with actual value on UI : "
										+strFieldValues+", instead of expected value: "+arrPlanUnitFieldValue[i]);
							Reporter.log("ERROR: The data updated via API couldn't be verified on UI for field name '"+strFieldNames+"' with actual value on UI : "
										+strFieldValues+", instead of expected value: "+arrPlanUnitFieldValue[i]);
							blnFieldsVerified = false;
							assertTrue(false, "ERROR: The data updated via API couldn't be verified on UI for field name '"+strFieldNames+"' with actual value on UI : "
									+strFieldValues+", instead of expected value: "+arrPlanUnitFieldValue[i]);
						}
					}
				}				
			}			
		}
		
		objAriaEOM.fn_clickAccountPlans(driver, webWait).click();
		objAriaEOM.fn_clickAnalyticsReporting(driver, webWait).click();
		return blnFieldsVerified;	//return the result.
	}
	
	
	//TODO: Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException {
		
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "UpdatePlanUnitAPI");
	}
	
	
	@AfterTest
	public void afterMethod() throws Exception {
		//TODO: Logs out of the application & quit the driver
		AriaLoginLogout.appLogout(driver);
		driver.quit(); 
	}      
}
