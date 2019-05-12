/*
 Author     		:	Aashish Bhutkar
 Edited				: 	Abhishek
 Class Name			: 	Cls_RetrieveByAPIUpdatedCustomerInfo 
 Purpose     		: 	Purpose of this file is :
						1. To update the Customer Information via UI.
						2. Verify the updated information using retrieval by API.
						3. This is automation of TC: QAABE-166.

 Date       		:	11/04/2015, 08/03/2015
 Edited Date		: 	05/10/2016, 05/12/2016 
 Version Information:	Version 1.1
 Version Information:	Version 1.2
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "RetrieveByAPICustomerInfo" worksheet 
 						for excel "TestData.xls".
 						3. The Custom Field mentioned in the "RetrieveByAPICustomerInfo" is assumed to be existing
 						as the Custom Defined Fields.

 Test Steps 		:	1. Login using valid role credentials for updating existing customer account.
 						2. Search the customer account to updates it's information.
 						3. Update and save the customer information.
 						4. Using API retrieval, verify the updated information in step 3.
 						
 Version Changes 1.1:	1. Changing the Before Class details to suit the generalisation agreed upon.  						
 Version Changes 1.2:	1. Changed the function to update value from using "fn_UpdateCustomFields" to "UpdateField" 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AccountTestCases;

import org.apache.log4j.xml.DOMConfigurator;
import atu.ddf.exceptions.DataDrivenFrameworkException;

import org.json.JSONArray;
import org.json.JSONObject;

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
import appModules.AccountFunctions;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;


public class Cls_RetrieveByAPIUpdatedCustomerInfo extends VerificationMethods
{
    public static WebDriver driver;
    public static String strTestCaseName = "Verify updated Customer Information using API retrieval.";
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
	@Test(dataProvider = "objectTestData", description = "Verify updated Customer Information using API retrieval.")
    public void fn_VerifyCustomerInformation (String strAccountNum, String strCustomField, String strCustomFieldData, String strAPIInstanceURL) throws Exception
    {
    	//TODO: Create object for the classes to be used for accessing reusable codes.  
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
		AccountFunctions objAccountFuncs = new AccountFunctions();
		ApiHandler objApiHandler = new ApiHandler();
		
		String strRestCall = "get_acct_supp_fields";
		JSONObject objJSONGetResponse;	// JSon Object created to read the "GET" response for the rest call.
		Boolean blnVerifyResponse = false;
    	
    	//TODO: Log the beginning of the test case.
    	Log.startTestCase(strTestCaseName);
    	
    	try
    	{
        	//TODO: Search the account number for which the customer information is to be updated.
    		Log.info("Calling Search Account Number reusable module.");
			objClientDefAct.AccountSearch(driver, webWait, strAccountNum);			
			Log.info("Successfully searched the Account Number: "+strAccountNum.toString());
			
			//Obtain Current CustomValue in Application
			Log.info("Obtaining the Current Custom Field Data");
			webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Reset Password")));
			String RevertToValue = objClientDefAct.fn_GetCustomFieldValue(driver, webWait, strAccountNum, strCustomField, strTestCaseName);
			Log.info("Current Custom Field Data is : "+RevertToValue);
			
			driver.findElement(By.xpath("//*[@id=\"bottomPaneTabBar\"]/ul/li[1]/a")).click();
			
			//TODO: Navigate to the Client Defined Fields and update the user defined field.			
			webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Reset Password")));			
			objClientDefAct.UpdateField(driver, webWait, strCustomField, strCustomFieldData);
			
			
			//TODO: get the full URL for API call.
			String strAPIUrl = objAccountFuncs.fn_GetFullUrl(driver, webWait, strAPIInstanceURL, strRestCall);
			strAPIUrl = strAPIUrl+"&acct_no="+strAccountNum;	// Here we need JSON response.
			

			//TODO: Get the response from the URL in JSON and then parse response to verify the data received. 
			objJSONGetResponse = objApiHandler.makeGetCall(strAPIUrl);
			blnVerifyResponse = fn_VerifyResponse(objJSONGetResponse, "supp_fields", "field_name", "field_value", strCustomField, strCustomFieldData);
			if(blnVerifyResponse == true)
			{
				Log.info("The API has retrieved the change successfully for "+strCustomField+" with value as: "+strCustomFieldData);
			}
			else
			{
				Log.error("ERROR: The change made couldn't be retrieved by the API !");
				Reporter.log("ERROR: The change made couldn't be retrieved by the API !");
				assertEquals(false, true);
			}
			
			//Reverting the CustomField
			Log.info("/-********Reverting the Custom Field to the previous value********-/");
			Log.info("Reverting the Custom Field : "+RevertToValue);
			objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
			webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Reset Password")));
			objClientDefAct.UpdateField(driver, webWait, strCustomField, RevertToValue);
			
			//TODO: Get the response from the URL in JSON and then parse response to verify the data received. 
			objJSONGetResponse = objApiHandler.makeGetCall(strAPIUrl);
			blnVerifyResponse = fn_VerifyResponse(objJSONGetResponse, "supp_fields", "field_name", "field_value", strCustomField, RevertToValue);
			if(blnVerifyResponse == true)
			{
				Log.info("The API has retrieved the reverted change successfully for "+strCustomField+" with value as: "+RevertToValue);
			}
			else
			{
				Log.error("ERROR: The reverted change made couldn't be retrieved by the API !");
				Reporter.log("ERROR: The reverted change made couldn't be retrieved by the API !");
				assertEquals(false, true);
			}

    	}
    	catch (Exception exception)
    	{
    		Log.error("ERROR: The API retrieval for updated Customer Information failed with execption :"+exception.toString());    		
    		Reporter.log("ERROR: The API retrieval for updated Customer Information failed with execption :"+exception.toString());    		
    		throw exception;    		
    	}
    	catch (AssertionError exception)
    	{
    		Log.error("ERROR: The API retrieval for updated Customer Information failed with execption :"+exception.toString());    		
    		Reporter.log("ERROR: The API retrieval for updated Customer Information failed with execption :"+exception.toString());    		
    		throw exception;    		
    	}
    	
    	//TODO: Log the end of the test case. 
    	Log.endTestCase(strTestCaseName);
    }


/*    
    Function fn_VerifyResponse: This is the JSON response taken in and based on comparison, boolean value returned.
    Parameters : objJSON - JSON object with actual API call response.
    			 strFields - Actual fields from JSON response for which verification is to be done. eg: "suppl_fields".
    			 strFieldName - The key to traverse the JSON array. eg: "field_name".
    			 strFieldValue - The value for the key searched in string strFieldName. eg: "field_value".
    			 strCustomField - This is the field for which you want to search the response; corresponds to strFieldName.
    			 strCustomFieldData - This is the field value which is associated to the strCustomField in response; corresponds to strFieldValue.    
*/	
	
	@Test(dependsOnGroups = {"Sanity"} , dependsOnMethods = {"fn_VerifyCustomerInformation"}, enabled = false)	// marks this method dependent on parent method.
	public Boolean fn_VerifyResponse(JSONObject objJSON, String strFields, String strFieldName, String strFieldValue, String strCustomField, String strCustomFieldData) throws Exception
	{
		//TODO: Read the JSON response in JSON Array based on the actual field response.
		JSONArray arrJSONFields = objJSON.getJSONArray(strFields);
				
		//TODO: Read the JSON array and verify if the response is as expected.			
		for (Integer i = 0; i < arrJSONFields.length(); i ++)
		{
			String strParamName = arrJSONFields.getJSONObject(i).getString(strFieldName);
			String strParamValue = arrJSONFields.getJSONObject(i).getString(strFieldValue);
			
			if(strParamName.equalsIgnoreCase(strCustomField) && strParamValue.equalsIgnoreCase(strCustomFieldData))
			{
				Log.info("Got the expected field '"+strParamName+"' with it's expected field value as '"+strParamValue+"'");
				return true;	// returning true since the response is successfully verified.
			}
		}
		return false;
	}
    

	//TODO: Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "RetrieveByAPICustomerInfo");
	}
	
	
	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }    
}