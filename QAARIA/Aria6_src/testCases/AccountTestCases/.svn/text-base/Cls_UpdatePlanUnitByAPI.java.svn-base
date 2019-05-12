/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_UpdatePlanUnitByAPI 
 Purpose     		: 	Purpose of this file is :
						1. To update the Plan Units By API.
						2. Verify the updated information using API Logs on UI.
						3. This is automation of TC: QAABE-179.

 Date       		:	08/17/2015 
 Modified Date		:	11/16/2015, 11/04/2015, 09/24/2015
 Version Information:	Version 3.0
 
 PreCondition 		:	1. Role based Login required for calling API's.
 						2. Data to be populated in the "TestCaseDetails" & "UpdatePlanUnitAPI" worksheet 
 						for excel "TestData.xls".

 Test Steps 		:	1. Login using valid role credentials for updating plan units for existing customer account.
 						2. Search the customer account to updates it's information.
 						3. Call API for updating the plan units for the plan associated with the user account.
 						4. Verify these details under the API Logs and on UI.

 Version Changes 2.0:	1. Refactored code to point to the correct reusable methods of AccountFucntions new files.
 Version Changes 2.1:	1. Changing the Before Class details to suit the generalisation agreed upon.
 Version Changes 3.0:	1. Changing logic to access the Audit Logs due to change in business logic.
 
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.ApiHandler;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;
import pageObjects.AccountPlansUsage;
import pageObjects.UpdatePlanUnitByAPI;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;

public class Cls_UpdatePlanUnitByAPI extends VerificationMethods
{
    public static WebDriver driver;
    public static String strTestCaseName = "Verify Plan Units are updated using the API call.";
	public static WebDriverWait webWait;
    public static WebElement webElement;
    
    @BeforeClass
    public void beforeMethod()throws Exception
    {	
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
	public void fn_UpdatePlanUnitsByAPI(String strPlanName, String strAccountNum, String strAPIURL, String strPlanUnitFieldName, String strPlanUnitFieldValue) throws Exception
	{
    	//TODO: Create class objects to access the common methods.
    	AccountFunctions objAccountFunc = new AccountFunctions();	// for methods relating to Accounts functionality.
    	AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
    	ApiHandler objAPIHandler = new ApiHandler();	// for post method to be called
    	    	
    	String strRestCall = "update_acct_plan_unit_instance";	//specify the rest call to be made.

    	//TODO: Mark the beginning of the Test.
       	Log.startTestCase(strTestCaseName);
       	
       	try
       	{
       		//TODO: Get the Supplemental Plan Number for the Plan name requested.
       		String strPlanNumber = objAcctFuncPlans.fn_GetPlans(driver, webWait, strPlanName, strTestCaseName);       		

       		//TODO: Verify if the Supplemental Plans has been assigned to the Account Number, else assign it.
			Boolean blnSupplementalPlansStatus = false; 
			blnSupplementalPlansStatus = objAcctFuncPlans.fn_VerifySupplementalPlans(driver, webWait, strAccountNum, strTestCaseName);
       		if(blnSupplementalPlansStatus == false)
       		{
       			Log.info("WARN: No Supplemental Plan Assigned for the Account, hence calling API for assigning the Supplemental Plan.");
       			objAcctFuncPlans.fn_AssignSuppPlan(driver, webWait, strAccountNum, strPlanNumber, strAPIURL);
       			Log.info("Supplemental Plan with number "+strPlanNumber+" has been assigned to the Account Number: "+strAccountNum);
       		}
       		
       		//TODO: Get the Plan Unit Instance ID for the plan assigned to the account under test.
       		String strInstanceID = objAcctFuncPlans.fn_GetSupplementalPlanInstanceID(driver, webWait, strAccountNum, strPlanNumber);
       		
       		//TODO: Get the various Plan Unit Field Names & Values and give the post call.
       		String[] arrPlanUnitFieldName = strPlanUnitFieldName.split("#");	//get various plan unit field names here.
       		String[] arrPlanUnitFieldValue = strPlanUnitFieldValue.split("#");	//get field values for the field names here.
       		Integer intArrayLength = arrPlanUnitFieldName.length;
       		
       		//TODO: design the API call's to add data & make a POST call & then verify it in API Logs.
       		String strPostURL = "";
       		String strFullURL = objAccountFunc.fn_GetFullUrl(driver, webWait, strAPIURL, strRestCall);       		
       		for(Integer i = 0; i < intArrayLength; i ++)	//get the field names first.
       		{
       			strPostURL = strFullURL +"&acct_no="+strAccountNum +"&plan_unit_inst_no="+strInstanceID +"&fulfillment_directive=1" 
       							+"&field_name="+arrPlanUnitFieldName[i].toString() +"&field_value="+arrPlanUnitFieldValue[i];
           		Log.info("The Actual URL created based on Parameter Name & Value is: "+strPostURL);
           		objAPIHandler.makeSimplePostCall(strPostURL);
           	
           		/************************* First Level Verification: Web Service API Audit Logs *************************/
           		//TODO: Verify the Web Service API Audit Logs for the success of API call
        		Boolean blnVerifyAPILogs = fn_VerifyAuditLogs(driver, webWait, strRestCall, strAccountNum, arrPlanUnitFieldName[i]
        																				, arrPlanUnitFieldValue[i], strTestCaseName);
        		if(blnVerifyAPILogs == true)
        		{
        			Log.info("The Verification for the API call in API Logs is successfully done for field name '"+arrPlanUnitFieldName[i]
        					+"' with value: "+arrPlanUnitFieldValue[i]);
        		}
        		else
        		{
        			Log.error("The Verification for the API call in API Logs is unsuccessful !");
        			Reporter.log("The Verification for the API call in API Logs is unsuccessful !");
        			assertEquals(false, true);    			
        		}           		
       		}
    		
    		/************************* Second Level Verification: Product Field Details *************************/
       		//TODO: Verify the Product Fields under the Supplemental Plan on UI for update details done by API.
    		Boolean blnVerifyPlanUnits = fn_VerifySupplementalPlansProdFields(driver, webWait, strPlanNumber, strAccountNum, 
    										arrPlanUnitFieldName, arrPlanUnitFieldValue, strTestCaseName);
    		if(blnVerifyPlanUnits == true)
    		{
    			Log.info("The Verification for the API update call on UI is successfully done !");
    		}
    		else
    		{
    			Log.error("The Verification for the API update call on UI is unsuccessful !");
    			Reporter.log("The Verification for the API update call on UI is unsuccessful !");
    			assertEquals(false, true);    			
    		}       		   		
       	}
       	catch (Exception exception)
       	{
       		Log.error("ERROR: Couldn't Verify data for the Plan Units added by API due to exception: "+exception.toString());
       		Reporter.log("ERROR: Couldn't Verify data for the Plan Units added by API due to exception: "+exception.toString());
       		throw exception;
       	}
       	catch (AssertionError exception)
       	{
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
					String strParamNames, String strParamValues, String strTestCaseName) throws Exception
	{
		//TODO: Create class objects to access the common methods.
		UpdatePlanUnitByAPI objPlanUnit = new UpdatePlanUnitByAPI();	// for page objects required for Web Service API & Audit Logs.
		
		Boolean blnVerified = false;

		String strLogParamNames = null;	//string array to store the field name read from logs.
		String strLogParamValues = null;	//string array to store the field values read from logs.
		String strCurrentDate = Utils.getDateYearFirst();
		
		//TODO: Verify the data updated via API on UI @ Web Service API in Audit Logs.
		Thread.sleep(1500);
		objPlanUnit.fn_clickConfiguration(driver, webWait).click();
		objPlanUnit.fn_clickAuditLogs(driver, webWait).click();
		objPlanUnit.fn_clickWebServiceAPI(driver, webWait).click();		
		//objPlanUnit.fn_clickCrossWebServiceAPI(driver, webWait).click();
		
		objPlanUnit.fn_clickRequestFromDate(driver, webWait).clear();
		objPlanUnit.fn_clickRequestFromDate(driver, webWait).sendKeys(strCurrentDate);
		objPlanUnit.fn_clickRequestToDate(driver, webWait).clear();
		objPlanUnit.fn_clickRequestToDate(driver, webWait).sendKeys(strCurrentDate);
		objPlanUnit.fn_clickRequestFromDate(driver, webWait).click();
		Thread.sleep(1500);
		objPlanUnit.fn_setRequestFromDateHour(driver, webWait).clear();
		objPlanUnit.fn_setRequestFromDateHour(driver, webWait).sendKeys("0");
		objPlanUnit.fn_setRequestFromDateMinute(driver, webWait).clear();
		objPlanUnit.fn_setRequestFromDateMinute(driver, webWait).sendKeys("0");
		objPlanUnit.fn_clickRequestDateTimeDone(driver, webWait).click();
		objPlanUnit.fn_clickRequestToDate(driver, webWait).click();
		Thread.sleep(1500);
		objPlanUnit.fn_setRequestToDateHour(driver, webWait).clear();
		objPlanUnit.fn_setRequestToDateHour(driver, webWait).sendKeys("23");
		objPlanUnit.fn_setRequestToDateMinute(driver, webWait).clear();
		objPlanUnit.fn_setRequestToDateMinute(driver, webWait).sendKeys("59");
		objPlanUnit.fn_clickRequestDateTimeDone(driver, webWait).click();
		
		objPlanUnit.fn_clickAdd(driver, webWait).click();
		objPlanUnit.fn_getSelectFieldDropDown(driver, webWait).sendKeys("Account Number");		
		WebElement webSearchTextField = objPlanUnit.fn_getTextField(driver, webWait); 
		webSearchTextField.sendKeys(strAccountNum);
		objPlanUnit.fn_clickSearchButton(driver, webWait).click();
		objPlanUnit.fn_getDataTableAPILogs(driver, webWait);
		Utils.takeScreenshot(driver, strTestCaseName);
		
		//TODO: Read the Logs and navigate to inner details of Logs for collecting values.
    	WebElement tblAPISearchLogs = objPlanUnit.fn_getDataTableAPILogs(driver, webWait).findElement(By.tagName("tbody"));
		List<WebElement> lstwebAPISearchLogsRows = tblAPISearchLogs.findElements(By.tagName("tr"));
		for(WebElement rows : lstwebAPISearchLogsRows)
		{
			List<WebElement> cols = rows.findElements(By.tagName("td"));
			
			if(cols.size() != 0)	// this is to avoid the header read in the loop.
			{
				String strAPIName = cols.get(0).getAttribute("innerText").toString().trim();
				String strErrorMessage = cols.get(6).getAttribute("innerText").toString().trim();
				if(strAPIName.equalsIgnoreCase(strRestCall) && strErrorMessage.equalsIgnoreCase("OK"))
				{
					cols.get(0).click();	//navigate to the detail logs for the API call.
					objPlanUnit.fn_getDataTableDetailsTab(driver, webWait);
					Utils.takeScreenshot(driver, strTestCaseName);
					
					objPlanUnit.fn_clickInputParameters(driver, webWait).click();					
					//TODO: Read the Input Parameters data table for final verification.
					Thread.sleep(2500);
			    	WebElement tblInputParameters = objPlanUnit.fn_getDataTableInputParametersTab(driver, webWait).findElement(By.tagName("tbody"));
					List<WebElement> lstwebInputParametersRows = tblInputParameters.findElements(By.tagName("tr"));
					Utils.takeScreenshot(driver, strTestCaseName);
					Log.info("Verifying the API Logs for the Input Parameter values...");
					for(WebElement rowsIPTable : lstwebInputParametersRows)
					{
						List<WebElement> colsIPTable = rowsIPTable.findElements(By.tagName("td"));
						
						if(colsIPTable.size() != 0)	// this is to avoid the header read in the loop.
						{
							//TODO: get the Param Name and then it's Values as per the expected fields. 
							strLogParamNames = colsIPTable.get(0).getAttribute("innerText").toString().trim();
							if(strLogParamNames.equalsIgnoreCase("field_name"))
							{
								strLogParamValues = colsIPTable.get(1).getAttribute("innerText").toString().trim();;
								if(strLogParamValues.equalsIgnoreCase(strParamNames))
								{
									Log.info("Data verified for the parameter name: "+strLogParamValues);
									blnVerified = true;
								}
							}

							if(strLogParamNames.equalsIgnoreCase("field_value"))
							{
								strLogParamValues = colsIPTable.get(1).getAttribute("innerText").toString().trim();;
								if(strLogParamValues.equalsIgnoreCase(strParamValues) && blnVerified == true)
								{
									Log.info("Data verified for the parameter value: "+strLogParamValues);
									blnVerified = true;
									objPlanUnit.fn_clickAuditLogs(driver, webWait).click();
									Thread.sleep(1500);
									objPlanUnit.fn_clickConfiguration(driver, webWait).click();
									
									return blnVerified;									
								}
							}
						}
					}
					break;	//exit the for loop since we have achieve the comparison by now.
				}				
			}
			else if(!(cols.iterator().hasNext()))
			{
				Log.error("ERROR: There are no logs generated for the Account Number Searched !");
				Reporter.log("ERROR: There are no logs generated for the Account Number Searched !");
				return blnVerified;
			}
		}		
		objPlanUnit.fn_clickAuditLogs(driver, webWait).click();
		return blnVerified;
	}
    
	
/*
	Function fn_VerifyAuditLogs: Takes in the Account details and the array for actual Parameter Names and Values for Verification on UI.
								 Based on the verifications, returns Boolean result.
*/
	public Boolean fn_VerifySupplementalPlansProdFields(WebDriver driver, WebDriverWait webWait, String strPlanNumber, String strAccountNum,
														String[] arrPlanUnitFieldName, String[] arrPlanUnitFieldValue, String strTestCaseName) throws Exception
	{
		//TODO: Create class objects to access the common methods.
    	AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();    	
    	UpdatePlanUnitByAPI objPlanUnit = new UpdatePlanUnitByAPI();	// for page objects required for Web Service API & Audit Logs.
    	AccountPlansUsage objAccountPlans = new AccountPlansUsage();	// for object for Supplemental Plans link. 
		
		Boolean blnFieldsVerified = false;
		String strFieldNames = null;
		String strFieldValues = null;
				
		//TODO: Verify the data updated via API on UI @ Supplemental Field Details page.
		String strInstanceID = objAcctFuncPlans.fn_GetSupplementalPlanInstanceID(driver, webWait, strAccountNum, strPlanNumber);
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText(strInstanceID))).click();	//navigate to the Supplemental Plan's Product Fields page.
		//TODO: Read the Product Fields and verify for the actual values passed.
		Thread.sleep(2500);
		Log.info("Verifying data for the Product Fields...");		
    	WebElement tblProductFields = objPlanUnit.fn_getProductFieldsTable(driver, webWait).findElement(By.tagName("tbody"));
		List<WebElement> lstwebProductFieldsRows = tblProductFields.findElements(By.tagName("tr"));
		Utils.takeScreenshot(driver, strTestCaseName);
		for(WebElement rows : lstwebProductFieldsRows)
		{
			List<WebElement> cols = rows.findElements(By.tagName("td"));
			
			if(cols.size() != 0)	// this is to avoid the header read in the loop.
			{
				//TODO: get the Param Name and then it's Values as per the expected fields. 
				strFieldNames = cols.get(0).getAttribute("innerText").toString().trim();
				for(Integer i = 0; i < arrPlanUnitFieldName.length; i ++)
				{
					if(strFieldNames.equalsIgnoreCase(arrPlanUnitFieldName[i]))
					{
						strFieldValues = cols.get(2).findElement(By.id("val2")).getAttribute("value").toString().trim();								
						if(strFieldValues.equalsIgnoreCase(arrPlanUnitFieldValue[i]))
						{
							Log.info("The data updated via API has been verified on UI for field name '"+strFieldNames+"' with value: "+strFieldValues);
							blnFieldsVerified = true;							
						}
						else
						{
							Log.error("ERROR: The data updated via API couldn't be verified on UI for field name '"+strFieldNames+"' with actual value on UI : "
										+strFieldValues+", instead of expected value: "+arrPlanUnitFieldValue[i]);
							Reporter.log("ERROR: The data updated via API couldn't be verified on UI for field name '"+strFieldNames+"' with actual value on UI : "
										+strFieldValues+", instead of expected value: "+arrPlanUnitFieldValue[i]);
							blnFieldsVerified = false;
							assertEquals(false, true);
						}
					}
				}				
			}			
		}
		objAccountPlans.fn_clickSupplementalPlans(driver, webWait).click();
		objAccountPlans.fn_clickAnalyticsReporting(driver, webWait).click();
		return blnFieldsVerified;	//return the result.
	}
	
	
	//TODO: Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "UpdatePlanUnitAPI");
	}
	
	
	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }      
}
