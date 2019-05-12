/*
 Author     		:	Namrata Akarte
 Class Name			: 	Cls_API_ChangeCustomAccountFields  
 Purpose     		: 	Purpose of this file is :
						1. To update Client defined field values through API
												
 Date       		:	04/22/2015
 
 Jira #				:	QAABE-167
 
 Version Information:	Version 1.0 
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in 'TestCaseDetails' worksheet of "TestData.xlsx" 

 Test Steps 		:	1. Login using valid role credentials.
 						2. Navigate to Configurations->Integrations->Web Services API and read Client Number and authentication Key
 						3. Upload value for the individual client defined field as mentioned in TestData.xlsx
						4. Verify if correct values are reflecting in GUI
 
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
	public void fn_APICustAcctFields(String strFieldName, String strFieldValue, String strAcctNum, String strInstanceURL) throws Exception
	 {
	  
		  //Declare Variables		 
		  String strFullURL = ""; 
		  String strAPIResult = "";
		  String strTestCaseName = "Test Case - API client Defined Fields Updations";
		  String strValue="";
		  boolean flag_API = true;
		  boolean flag_UI = true;
		  		  
		  List<JSONObject> listAPIResult; 
		  
		  //Declare Objects
		  ApiHandler objApi = new ApiHandler();
		  AccountFunctions objAcctFns = new AccountFunctions();		 	
		  Cls_ChangeDeleteClientDefinedFieldActns objFields = new Cls_ChangeDeleteClientDefinedFieldActns();		
		
		  
		  String[] fieldName = strFieldName.split("#");
		  String[] UIfieldName = strFieldName.split("#");
		  String[] fieldValue = strFieldValue.split("#");
		  		    
		  Log.startTestCase(strTestCaseName);
		  
		  //Reset the test account client defined fields
		  objFields.AccountSearch(driver, webWait, strAcctNum);
		  
		  Thread.sleep(2000);
	 
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
			  strValue = fieldValue[i];
			  if (strValue.contains(" "))
				  strValue = strValue.replaceAll(" ", "+");
			 
			  //Generate String for Rest Call  
			  String strRestCall = "update_acct_complete&acct_no="+strAcctNum+"&supp_field_names="+fieldName[i]+"&supp_field_values="+strValue+"&supp_field_directives=1";
			  Log.info ("Rest Call Is:::"+strRestCall);
			  
			  //Get Full URL
			  strFullURL = objAcctFns.fn_GetFullUrl(driver, webWait, strInstanceURL, strRestCall);
			  Log.info("Full URL generated is : "+strFullURL);
			  			  
			  Thread.sleep(2000);
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
		  
		  Thread.sleep(3000);
		  
		  for (int i = 0;i<fieldName.length;i++){
			  
			  try{
				  String strApplFieldValue = objFields.fn_ReadCustomFieldVal(driver, webWait, strAcctNum, fieldName[i], strTestCaseName);
				  
				  if (fieldName[i].equalsIgnoreCase("CO_CODE")){
					  if (fieldValue[i].equalsIgnoreCase("01"))
						  fieldValue[i] = "US";
					  else if (fieldValue[i].equalsIgnoreCase("05"))
						  fieldValue[i] = "CA";
					  else if (fieldValue[i].equalsIgnoreCase("18"))
						  fieldValue[i] = "IE";
					  else if (fieldValue[i].equalsIgnoreCase("20"))
						  fieldValue[i] = "UK";
					  else if (fieldValue[i].equalsIgnoreCase("76"))
						  fieldValue[i] = "SE";
					  
					  
				  }
				  Log.info("Field Value is : " +strApplFieldValue);
				  
				  Thread.sleep(1000);
				  
				  if (!strApplFieldValue.equals(null)){
					  if (strApplFieldValue.equalsIgnoreCase(fieldValue[i])){
						  Log.info("Data is Uploaded successfully for the field "+UIfieldName[i]);
						  Log.info("--------------------------------------------------------------------------------");
					  }
					  else{
						  Log.info("Data is  NOT Uploaded successfully for the field "+UIfieldName[i]);
						  Reporter.log("Data is  NOT Uploaded successfully for the field "+UIfieldName[i]);
						  VerificationMethods.assertTrue(false, "Data is  NOT Uploaded successfully for the field "+UIfieldName[i]);
						  Log.info("--------------------------------------------------------------------------------");
						  flag_UI = false;
					  }
				  }
			  }
			  catch(Exception e){
				  Log.info("Data is  NOT Uploaded successfully for the field "+UIfieldName[i]+"through API. Hence, displayed as "+e.getMessage());
				  Reporter.log("Data is  NOT Uploaded successfully for the field "+UIfieldName[i]+"through API. Hence, displayed as "+e.getMessage());
				  Log.info("--------------------------------------------------------------------------------");
				 		
			  }
			  
			  
		  }
		  
		  if (flag_API == false || flag_UI == false){
			  Log.info("Data is NOT updated for one or more Client Defined Fields");
			  VerificationMethods.assertTrue(false, "ERROR: Data is NOT updated for one or more Client Defined Fields");
		  }
		  else{
			  Log.info("Data is updated for all Client Defined Fields successfully");
			  Log.info("--------------------------------------------------------------------------------");
			  Reporter.log("Data is updated for all Client Defined Fields successfully");
			  Utils.takeScreenshot(driver, strTestCaseName);
		  } 
		  
		  Log.endTestCase(strTestCaseName);
		  
		  
	  }  
	  
	  
	  //Reading data for the test
	  @DataProvider(name = "objectTestData1")
	  public Object[][] testcasedata() throws DataDrivenFrameworkException
	  {
	      Utils objUtils=new Utils();
	      return objUtils.data1("TestCaseDetails", "API_AddCustomAccountFields");
	  }	
	  
	  
	  @AfterTest
	  public void afterMethod() throws Exception
	  {
		  //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	  }

}
