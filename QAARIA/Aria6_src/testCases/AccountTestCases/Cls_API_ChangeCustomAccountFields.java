/*
 Author     		:	Namrata Akarte
 Class Name			: 	Cls_API_ChangeCustomAccountFields  
 Purpose     		: 	Purpose of this file is :
						1. To update Client defined field values through API
												
 Date       		:	10/19/2015, 11/03/2015, 11/26/2015
 
 Jira #				:	QAABE-167
 
 Version Information:	Version 5.0 
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in 'TestCaseDetails' worksheet of "TestData.xlsx" 

 Test Steps 		:	1. Login using valid role credentials.
 						2. Navigate to Configurations->Integrations->Web Services API and read Client Number and authentication Key
 						3. Upload value for the individual client defined field as mentioned in TestData.xlsx
						4. Verify if correct values are reflecting in GUI
 
 Version Changes 2.0: 	1. GUI verification is included
 Version Changes 3.0:	1. updated the condition to verify the API value with UI
 Version Changes 4.0: 	1. Updated Chrome Driver Path
 Version Changes 5.0: 	1. Added code to reset the custom account fields before entering their values through API
 Version Changes 5.1:	1. Added hard coded wait and screenshot captures.
 Version Changes 5.2:	1. Added hard coded wait before moving to Client Defined fields tab
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services 
 						All Rights Reserved 
*/

package testCases.AccountTestCases;

import java.util.List;
import org.apache.log4j.xml.DOMConfigurator;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import appModules.AccountFunctions;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import utility.ApiHandler;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;

public class Cls_API_ChangeCustomAccountFields extends VerificationMethods{
	
	public WebDriver driver;
	public WebDriverWait webWait;
	public String strAuthenticationKey;
	
	@BeforeClass
	public void beforeMethod()throws Exception
	{	
		//TODO: Initialize the Logger.
    	DOMConfigurator.configure("log4j.xml");	

    	//TODO: Set Chrome driver as the driver to launch the test.
    	driver = utility.Utils.fn_SetChromeDriverProperties();
    	
		//TODO: initialize the webWait
		webWait = new WebDriverWait(driver,60);
		EnvironmentDetails objEnv = new EnvironmentDetails();
		
		Log.startTestCase("Login to application");
		//Logs in to the aria application with the appropriate credentials		 	
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);
	
	}
	  @Test(dataProvider="objectTestData1")
	public void fn_APICustAcctFields(String strFieldName, String strValue, String strAcctNum, String strInstanceURL) throws Exception
	 {
	  
		  //Declare Variables		 
		  String strFullURL = ""; 
		  String strAPIResult = "";
		  String strTestCaseName = "Test Case - API client Defined Fields Updations";
		  boolean flag_API = true;
		  boolean flag_UI = true;
		  		  
		  List<JSONObject> listAPIResult; 
		  
		  //Declare Objects
		  ApiHandler objApi = new ApiHandler();
		  AccountFunctions objAcctFns = new AccountFunctions();		 	
		  Cls_ChangeDeleteClientDefinedFieldActns objFields = new Cls_ChangeDeleteClientDefinedFieldActns();		
		
		  
		  String[] fieldName = strFieldName.split("#");
		  String[] UIfieldName = strFieldName.split("#");
		  
		  String strTestValue = strValue;		  
		  
		  //Reset the test account client defined fields
		  objFields.AccountSearch(driver, webWait, strAcctNum);
		  
		  Thread.sleep(2000);;
		  
		  for (int i = 0;i<fieldName.length;i++){
			  Thread.sleep(5000);
			  objFields.fn_DeleteCustomFieldValue(driver, webWait, strAcctNum, fieldName[i], strTestCaseName);
		  }
		  
		  Log.info("All Fields are reset successfully");
		  Utils.takeScreenshot(driver, strTestCaseName);
		  
		  for (int i = 0;i<fieldName.length;i++){
			  
			  System.out.println(fieldName[i]);
			  
			  if (fieldName[i].contains(" "))
				  fieldName[i] = fieldName[i].replaceAll(" ", "%20");	
			  
			  if (strValue.contains(" "))
				  strValue = strValue.replaceAll(" ", "+");
			  
			  //Generate String for Rest Call  
			  String strRestCall = "update_acct_complete&acct_no="+strAcctNum+"&supp_field_names="+fieldName[i]+"&supp_field_values="+strValue+"&supp_field_directives=1";
			  Log.info ("Rest Call Is:::"+strRestCall);
			  
			  //Get Full URL
			  strFullURL = objAcctFns.fn_GetFullUrl(driver, webWait, strInstanceURL, strRestCall);
			  Log.info("Full URL generated is : "+strFullURL);
			  
			  //Post API request
			  listAPIResult = objApi.makeSimplePostCall(strFullURL);
			  
			  strAPIResult = listAPIResult.toString();
			  
			  if (strAPIResult.contains("\"error_msg\":\"OK\"")){
				  Log.info("Value for supplemental field "+fieldName[i]+ " uploaded successufully");
			  }
			  else{
				  Log.info("Value for supplemental field "+fieldName[i]+ " NOT uploaded successufully");
				  Reporter.log("Value for supplemental field "+fieldName[i]+ " NOT uploaded successufully");
				  flag_API = false;
			  }		  
			  		  
		  }		
		
		  objFields.AccountSearch(driver, webWait, strAcctNum);
		  
		  Thread.sleep(1500);
		  
		  for (int i = 0;i<fieldName.length;i++){
			  
			  String strFieldValue = objFields.fn_GetCustomFieldValue(driver, webWait, strAcctNum, UIfieldName[i], strTestCaseName);
			  
			  
			  Log.info("Field Value is : " +strFieldValue);
			  
			  Thread.sleep(1000);
			  
			  if (strFieldValue.equalsIgnoreCase(strTestValue)){
				  Log.info("Data is Uploaded successfully for the field "+UIfieldName[i]);
				  Log.info("--------------------------------------------------------------------------------");
			  }
			  else{
				  Log.info("Data is  NOT Uploaded successfully for the field "+UIfieldName[i]);
				  Reporter.log("Data is  NOT Uploaded successfully for the field "+UIfieldName[i]);
				  Log.info("--------------------------------------------------------------------------------");
				  flag_UI = false;
			  }  				  
		  }
		  
		  if (flag_API == false || flag_UI == false){
			  Log.info("Data is NOT updated for one or more Client Defined Fields");
		  }
		  else{
			  Log.info("Data is updated for all Client Defined Fields successfully");
			  Log.info("--------------------------------------------------------------------------------");
			  Reporter.log("Data is updated for all Client Defined Fields successfully");
			  Utils.takeScreenshot(driver, strTestCaseName);
		  }   
		  
		  
	  }  
	  
	  
	  //Reading data for the test
	  @DataProvider(name = "objectTestData1")
	  public Object[][] testcasedata() throws DataDrivenFrameworkException
	  {
	      Utils objUtils=new Utils();
	      return objUtils.data1("TestCaseDetails", "API_AddCustomAccountFields");
	  }	
	  
	  
	 // @AfterTest
	  public void afterMethod() throws Exception
	  {
		 
		  //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	  }

}
