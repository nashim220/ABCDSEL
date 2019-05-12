/*
 Author     		:	Namrata Akarte
 Class Name			: 	Cls_ClientPlansBasic  
 Purpose     		: 	Purpose of this file is :
						1. To  verify if plan details through API and GUI are displayed correctly
												
 Date       		:	12/11/2015
 Version Information:	Version 2.0
 
 Jira #				:	QAABE-374
 
 PreCondition 		:	1. Role based Login required. 				

 Test Steps 		:	1. Login using valid role credentials.
 						2. Hit API and read json response
 						3. Navigate to Product->Plans->Supplemental Plan 14709 and select it.
 						4. Read Values of 'Plan Name', 'Client Defined Identifiers', 'Description' from the Details tab
 						5. Compare the values obtained in Step 2 and Step 4.
 						
Version Changes	2.0	: 	1. Updated the Chrome driver path
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services 
 						All Rights Reserved 
*/
package testCases.AriaProducts;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ProductCatalogRateSheets;
import appModules.AccountFunctions;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import utility.ApiHandler;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;

public class Cls_ClientPlansBasic {
	
	public WebDriver driver;
	public WebDriverWait webWait;	
	
	@BeforeClass
	 public void beforeMethod()throws Exception
	 {	
		DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.		
		
		//TODO: Set Chrome driver as the driver to launch the test.
		driver = utility.Utils.fn_SetChromeDriverProperties();
				
		//TODO: initialize the webWait
		webWait = new WebDriverWait(driver, 120);
		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.info("Login to application");
		//This parameters are taken from Exec file and its reference is available in EnvironmentDetails
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
	 }	
	
	 @Test(dataProvider="objectTestData", description="ClientBasic")
	 public void fn_ClientPlansBasic(String strInstanceURL) throws Exception
	  {
	  
		  ApiHandler objApi = new ApiHandler();
		  AccountFunctions objAcctFns = new AccountFunctions();
		  ProductCatalogRateSheets objProduct = new ProductCatalogRateSheets();
		  
		  List<JSONObject> listAPIResult;
		  int strPlanTypeCode = 0;
		  
		  String strFullURL = "";
		  
		  String strSearchStr = "";
		  String strPlanType = "";
		  String strPlanName = "";
		  String strClientPlanID = "";
		  String strPlanDesc = "";
		  String strAcctStatus = "";
		  String strInvoiceTiming = "";
		  String strCurrency = "";		  
		  
		  String [] strArray ;
		  String [] strArrElement;
		  String strElement = "";
		  
		  //Hashmap to store API values
		  HashMap hm = new HashMap();
		  //Hashmap to store Application UI values
		  HashMap hmAppValues = new HashMap();		 
		  
		  
		//Generate String for Rest Call  
		  String strRestCall = "get_client_plans_basic&plan_no=14709";;
		  Log.info ("Rest Call Is:::"+strRestCall);
		  
		  //Get Full URL
		  strFullURL = objAcctFns.fn_GetFullUrl(driver, webWait, strInstanceURL, strRestCall);
		  Log.info("Full URL generated is : "+strFullURL);
		  
		  //Post API request
		  listAPIResult = objApi.makeSimplePostCall(strFullURL);   
		  
		  
		  		  
		  strSearchStr = listAPIResult.toString();		  
		 		  
		  if (strSearchStr.contains("\"plans_basic\":"))
		  {
			  strSearchStr = strSearchStr.replace("\"plans_basic\":", "");
			  strSearchStr = strSearchStr.replace("[{", "");
			  strSearchStr = strSearchStr.replace("}]", "");
			  strSearchStr = strSearchStr.replace("\"", "");
			  Log.info("String Generated  ::"+strSearchStr);
		  }
		  
		  strArray = strSearchStr.split(",");	
		  
		  //TODO : Store values in hashmap	  
		  for (int i=0; i<strArray.length;i++){
			  strElement = strArray[i];
			  strArrElement = strElement.split(":");
			  if (!strArrElement[0].contains("error") && !strArrElement[1].equalsIgnoreCase("null")){
				  
				  hm.put(strArrElement[0], strArrElement[1]);
				  
			  }		  
			  
		  }
		  
		  //TODO : Iterate over hashmap to see if the values are stored properly
		  Set set = hm.entrySet();		  		  
		  Iterator itr = set.iterator();		  
		  while (itr.hasNext())
		  {
			  Map.Entry mentryReport = (Map.Entry)itr.next();
			  Log.info("Key is :"+mentryReport.getKey());
			  Log.info("Value is :"+mentryReport.getValue());
		  }
		  
		  Thread.sleep(5000);
		  //Click on Products link
		  driver.findElement(objProduct.fn_clickProductsLeftNav()).click();
		  Thread.sleep(5000);	
		  //Click on Plans link
		  driver.findElement(objProduct.fn_clickPlansLeftNav()).click();
		  Thread.sleep(5000);
		  //Parse the table
		  WebElement tableSuppPlans = driver.findElement(By.xpath("//*[contains(@id,'DataTables_Table_')]"));
		  WebElement tableSuppPlansContents = tableSuppPlans.findElement(By.tagName("tbody"));
		  
		  List<WebElement>rows =  tableSuppPlansContents.findElements(By.tagName("tr"));
		
		  loop :for (WebElement row : rows){
			  
			  List<WebElement>columns = row.findElements(By.tagName("td"));//To get collection of columns
			  int intTotalCols = columns.size();
			  
			  String strPlanNum = columns.get(1).getText().toString().trim();
			  Log.info("Plan Number is "+strPlanNum);			  
			  
			  if (strPlanNum.equals("14709")){
				  
				  strPlanType = columns.get(0).getText().toString().trim();
				  Log.info("Plan Type is : "+strPlanType);
				  
				  if (strPlanType.equalsIgnoreCase("S"))
				  {
					  strPlanTypeCode = 1;
				  }
				  else
				  {
					  strPlanTypeCode = 0;
				  }
				  
				  Thread.sleep(2000);
				  columns.get(0).click();
				  break loop;
			  }	 
			  
		  }
		  
		  Thread.sleep(10000);
		  //Click on Details tab
		  driver.findElement(By.partialLinkText("Details")).click();
		  
		  strPlanName = driver.findElement(By.id("plan_planName")).getAttribute("value");
		  Log.info("Plan Name : "+strPlanName);		  
		  
		  strClientPlanID = driver.findElement(By.id("plan_clientPlanId")).getAttribute("value");
		  Log.info("Client Plan ID : "+strClientPlanID);
		  		  
		  strPlanDesc = driver.findElement(By.id("plan_euHtmlLong")).getAttribute("value");
		  Log.info("Plan Description : "+strPlanDesc);
		  
		  strAcctStatus = driver.findElement(By.id("plan_noDisplayInd")).getAttribute("value");
		  Log.info("Account Status : "+strAcctStatus);
		  		  
		  //Click on Billing & Invoicing tab
		  driver.findElement(By.partialLinkText("Billing & Invoicing")).click();
		  
		  //Read value from the list : Proration Invoice Timing
		  strInvoiceTiming = driver.findElement(By.id("plan_prorationInvoiceTimingCd")).getAttribute("value");
		  Log.info("Proration Invoice Timings : "+strInvoiceTiming);
		  
		  //Click on Rates Tab
		  driver.findElement(By.partialLinkText("Rates")).click();
		  strCurrency = driver.findElement(By.id("plan_currencyCd")).getAttribute("value");
		  Log.info("Currency : "+strCurrency);		  
		  
		  //TODO : Store the values from Application UI to hashmap
		  hmAppValues.put("supp_plan_ind", strPlanTypeCode);
		  hmAppValues.put("plan_name", strPlanName);
		  hmAppValues.put("client_plan_id", strClientPlanID);
		  hmAppValues.put("plan_desc", strPlanDesc);
		  hmAppValues.put("new_acct_status", strAcctStatus);
		  hmAppValues.put("proration_invoice_timing_cd", strInvoiceTiming);
		  hmAppValues.put("currency_cd", strCurrency);
		  
		  Set sethmApp = hmAppValues.entrySet();		  
		  Iterator itrhmApp = sethmApp.iterator();
		  
		 // Set sethmAPI = hm.entrySet();		  		  
		//  Iterator itrhmAPI = sethmAPI.iterator();		  
		 
		  
		  while (itrhmApp.hasNext())
		  {
			  Map.Entry mentryAppl = (Map.Entry)itrhmApp.next();
			
			  Object objAppKey = mentryAppl.getKey();	
			  String strAppKey = objAppKey.toString();
			  
			  if (hm.containsKey(objAppKey)){
			
				  	if (hm.get(objAppKey).toString().trim().equalsIgnoreCase(mentryAppl.getValue().toString().trim())){
				  		Log.info("HashMap Key and Value of Application are matching with HashMapAPI :: "+"Key="+mentryAppl.getKey()+"::"+"Value="+mentryAppl.getValue());
						Log.info("=========================================================================");
				  	}
				  	else
		            {
		            	Log.info("HashMap Key and Value of Application are not matching with HashMapAPI :: "+"Key="+mentryAppl.getKey());
		            	Log.info("Application Value :"+"Value="+mentryAppl.getValue());
		            	Log.info("API Value :"+"Value="+hm.get(objAppKey));
		                Log.info("============================================================================");
		            }
			  }
			  else{
				  Log.info("Application Hashmap key does not exist in API hashmap ::"+ strAppKey);
				  Reporter.log("Application Hashmap key does not exist in API hashmap ::"+ strAppKey);
			  }
				 
		  }  	  
		  
	  }
	  
	  
	 @AfterTest
	public void afterMethod() throws Exception
	 {
		 //Logs out of the application
		 AriaLoginLogout.appLogout(driver); 
		 //Quitting the driver
		 driver.quit(); 
	 }
		 
	//Reading data for the test
	@DataProvider(name = "objectTestData")
	 public Object[][] testcasedata() throws DataDrivenFrameworkException
	 {
	      Utils objUtils=new Utils();
	      return objUtils.data("TestCaseDetails", "ClientBasic");
	 }	
}
