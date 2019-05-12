package testCases.AriaConfiguration;

/*
 Author     		:	Abhishek
 Class Name			: 	Cls_ValidateClientParams
 Purpose     		: 	Purpose of this file is :
						1. To Validate the Client Parameters
												
 Date       		:	08/14/2015

 Version Information:	Version 1.0
 
 Jire #				:	
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "ClientParams" 
 						worksheet for excel "TestData.xlsx".

 Test Steps 		:	1. Login using valid role credentials for verifying the Configuration Payment Settings.
 						2. Navigate to the Admin Query ->Client Parameters ->Click Go->Validate the Parameters.
 						3. Read the table on the landing page and compare it with the expected data mentioned in the
 						"ClientParams" worksheet for excel "TestData.xlsx".
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/
//package testCases.AriaConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import appModules.SelectBrowser;
import atu.ddf.exceptions.DataDrivenFrameworkException;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;

import pageObjects.ClientParamsPageObject;
import pageObjects.ConfigurationInvoiceSettings;
import testCases.BaseTestCase;
import utility.*;
import appModules.AriaLoginLogout;

public class Cls_ValidateClientParams extends VerificationMethods{

public static WebDriver driver;
public WebDriverWait webWait;
public String strAuthenticationKey;
	
@BeforeTest
public void beforeMethod()throws Exception{	
	//TODO: Initialize the Logger.
   	DOMConfigurator.configure("log4j.xml");	

	//TODO : Set Chrome Driver
	SelectBrowser objBrowser = new SelectBrowser();
	driver = objBrowser.initDriver("Chrome");
	Log.info("initializing driver...");
		
	EnvironmentDetails objEnv = new EnvironmentDetails();
	Log.startTestCase("Login to application");
	//Logs in to the aria application with the appropriate credentials		 	
	AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);
	
}
	
@Test(dataProvider="objectTestData", description="Validate Client Parameters")
public void fn_verifyClientParams(String strClientParameters){
		
	String strTestCaseName = "Verify Client Parameters";
	webWait = new WebDriverWait(driver,1000);
		
	//Read POM Client Parameters	
	ClientParamsPageObject objClientParams = new ClientParamsPageObject();
		
	try {
		//TODO: Click on Admin Query link in the left navigation:
		objClientParams.fn_AdminQueryLink(driver, webWait).click();
		Thread.sleep(5000);
						
		//Read the Table from Application
		WebElement table = driver.findElement(By.id("DataTables_Table_0"));
		Thread.sleep(3000);
		
		//TODO : Enter the value in Edit Field
		objClientParams.fn_SearchField(driver, webWait).clear();
		objClientParams.fn_SearchField(driver, webWait).sendKeys(strClientParameters);
		objClientParams.fn_SearchField(driver, webWait).sendKeys(Keys.TAB);
		Thread.sleep(5000);
		
		// And iterate over them, getting the cells
		WebElement tableContents = table.findElement(By.tagName("tbody"));
		List<WebElement>rows = tableContents.findElements(By.tagName("tr"));
		List<WebElement>columns = rows.get(0).findElements(By.tagName("td"));
		
		String ValidateField ="";
		if(columns.get(1).getAttribute("innerText").toString().trim().equalsIgnoreCase("Client Parameters")){
			ValidateField = columns.get(1).getAttribute("innerText").toString().trim();
			System.out.println(ValidateField+" Clicked");
			
			//Click on Client Parameters
			columns.get(1).click();
			Thread.sleep(3000);
			
			//Click Go Link
			objClientParams.fn_GoLink(driver, webWait).click();
			
			//Function Call to Verify Fields
			fn_verifyFields();
			Thread.sleep(5000);
			
			//Close the Client Parameters Page
			objClientParams.fn_CloseParamLink(driver, webWait).click();
			Thread.sleep(10000);
		}
	}
	catch (Exception exception) {
		// TODO Auto-generated catch block
		Log.error("The Verification for Client Parameters couldn't be done due to the exception thrown as: "+exception.toString());
    	Reporter.log("ERROR: The Verification for Client Parameters couldn't be done due to the exception thrown as: "+exception.toString());
    	exception.printStackTrace();
	}
	//TODO: Log the End of the Test Case.
	Log.endTestCase(strTestCaseName);
	}
	
	public void fn_verifyFields() throws Exception{
		//Read POM Client Parameters	
		ClientParamsPageObject objClientParams = new ClientParamsPageObject();
		System.out.println("###############Fetching Values from Excel Sheet###############");
		
		//TODO: Read the Client Parameters from Excel file for data comparison and verification.
		String strClientParamsDataFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
				 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
				 + "TestData.xlsx";
		File fileClientParams = new File(strClientParamsDataFilePath);
		
		//TODO : Read Client Params Sheet
		List<HashMap<String,String>> ExcelFileList = ReadFiles.readExcelFile(fileClientParams, "ClientParams");
		Map<String, Map<String,String>> ExcelMap = new HashMap<String, Map<String,String>>();
		Map<String,String> ExcelListValues ;
		String PNameExcelkey = "";
		String DisplayNameVal = "";
		String DisplayIndVal = "";
		String CreateDateVal = "";
		String DescriptionVal = "";
		String DefaultValVal = "";
		String SetForClientVal = "";
		String CreateUserVal = "";
		String UpdateUserVal = "";
		String UpdateDateVal = "";
		String ParamValVal = "";

		for(Map<String, String> map : ExcelFileList)
		    {
			ExcelListValues = new HashMap<String,String>();
			PNameExcelkey = map.get("Param Name");
			
			if(map.get("Display Name")==null){
				DisplayNameVal =  "";
				ExcelListValues.put("Display Name", DisplayNameVal);
			}
			else{
				DisplayNameVal =  map.get("Display Name");
				ExcelListValues.put("Display Name", DisplayNameVal);
			}
			if(map.get("Display Ind")==null){
				DisplayIndVal =  "";
				ExcelListValues.put("Display Ind", DisplayIndVal);
			}
			else{
				DisplayIndVal =  map.get("Display Ind");
				ExcelListValues.put("Display Ind", DisplayIndVal);
			}
			if(map.get("Create Date")==null){
				CreateDateVal =  "";
				ExcelListValues.put("Create Date", CreateDateVal);
			}
			else{
				CreateDateVal =  map.get("Create Date");
				ExcelListValues.put("Create Date", CreateDateVal);
			}
			if(map.get("Description")==null){
				DescriptionVal =  "";
				ExcelListValues.put("Description", DescriptionVal);
			}
			else{
				DescriptionVal =  map.get("Description");
				ExcelListValues.put("Description", DescriptionVal);
			}
			if(map.get("Default Val")==null){
				DefaultValVal =  "";
				ExcelListValues.put("Default Val", DefaultValVal);
			}
			else{
				DefaultValVal =  map.get("Default Val");
				ExcelListValues.put("Default Val", DefaultValVal);
			}
			if(map.get("Set For Client")==null){
				SetForClientVal =  "";
				ExcelListValues.put("Set For Client", SetForClientVal);
			}
			else{
				SetForClientVal =  map.get("Set For Client");
				ExcelListValues.put("Set For Client", SetForClientVal);
			}
			if(map.get("Create User")==null){
				CreateUserVal =  "";
				ExcelListValues.put("Create User", CreateUserVal);
			}
			else{
				CreateUserVal =  map.get("Create User");
				ExcelListValues.put("Create User", CreateUserVal);
			}
			if(map.get("Update User")==null){
				UpdateUserVal =  "";
				ExcelListValues.put("Update User", UpdateUserVal);
			}
			else{
				UpdateUserVal =  map.get("Update User");
				ExcelListValues.put("Update User", UpdateUserVal);
			}
			if(map.get("Update Date")==null){
				UpdateDateVal =  "";
				ExcelListValues.put("Update Date", UpdateDateVal);
			}
			else{
				UpdateDateVal =  map.get("Update Date");
				ExcelListValues.put("Update Date", UpdateDateVal);
			}
			if(map.get("Param Val")==null){
				ParamValVal =  "";
				ExcelListValues.put("Param Val", ParamValVal);
			}
			else{
				ParamValVal =  map.get("Param Val");
				ExcelListValues.put("Param Val", ParamValVal);
			}
			if(PNameExcelkey != "" )
			{
				ExcelMap.put(PNameExcelkey, ExcelListValues);
			}
		}
		System.out.println("Number of Parameters in Excel : "+ExcelMap.keySet().size());
		
		//Find the Total Number of Pages
		WebElement tableinfo = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_1_info\"]"));
		String info = tableinfo.getText();
		int totalNumOfPages = Integer.parseInt(info.substring(info.length() - 3));
		int numOfEntriesPerPage = 50;
		int numOfPages = totalNumOfPages/numOfEntriesPerPage; 
		System.out.println("###############Fetching Values from Application###############");
		
		//Read the Table from Application
		Map<String, Map<String,String>> Tablemap = new HashMap<String, Map<String,String>>();
		Map<String,String> valSetOne;
		String hmKey = "";
		String hmVal = "";
		String ParameterNameKey="";
		for(int k=0;k<=numOfPages;k++){
			System.out.println("Fetching Values from Page : "+(k+1));
			
			//Read the Table from Application
			WebElement table = driver.findElement(By.id("DataTables_Table_1"));
			BaseTestCase objTable = new BaseTestCase();
			List<HashMap<String,String>> TableValues = objTable.getFullTableHash(table);
			for(int i=0; i<TableValues.size();i++){
	        	Set<String> TableKey = TableValues.get(i).keySet();
				Iterator TableKeyit = TableKey.iterator();	
				valSetOne = new HashMap<String, String>();
				while (TableKeyit.hasNext()){
					hmKey = (String)TableKeyit.next();
					hmVal = (String)TableValues.get(i).get(hmKey);
					if(hmKey.equalsIgnoreCase("Param Name")){
						ParameterNameKey = hmVal;
					}
					if(hmKey.equalsIgnoreCase("Display Name")){
						valSetOne.put("Display Name", hmVal);
					}
					if(hmKey.equalsIgnoreCase("Display Ind"))
					{
						valSetOne.put("Display Ind", hmVal);
					}
					if(hmKey.equalsIgnoreCase("Create Date"))
					{
						valSetOne.put("Create Date", hmVal);
					}
					if(hmKey.equalsIgnoreCase("Description"))
					{
						valSetOne.put("Description", hmVal);
					}
					if(hmKey.equalsIgnoreCase("Default Val"))
					{
						valSetOne.put("Default Val", hmVal);
					}
					if(hmKey.equalsIgnoreCase("Set For Client"))
					{
						valSetOne.put("Set For Client", hmVal);
					}
					if(hmKey.equalsIgnoreCase("Create User"))
					{
						valSetOne.put("Create User", hmVal);
					}
					if(hmKey.equalsIgnoreCase("Update User"))
					{
						valSetOne.put("Update User", hmVal);
					}
					if(hmKey.equalsIgnoreCase("Update Date"))
					{
						valSetOne.put("Update Date", hmVal);
					}
					if(hmKey.equalsIgnoreCase("Param Val"))
					{
						valSetOne.put("Param Val", hmVal);
					}
				}
				if(ParameterNameKey != "" )
				{
					Tablemap.put(ParameterNameKey, valSetOne);
				}
			}
        	objClientParams.fn_NextLink(driver, webWait).click();
			Thread.sleep(2000);
		} 
		
		int cnt = 1;
		System.out.println("/-**************************Comparing Values**************************-/");
		for (String Comparekey : ExcelMap.keySet()) {
		    if (Tablemap.containsKey(Comparekey)) {
		    	System.out.println("######## Parameter : "+cnt+". "+Comparekey+" ########");
		    	Map<String,String> TableValueMap = new HashMap<String,String>();
		    	Map<String,String> ExcelValueMap = new HashMap<String,String>();
		    	TableValueMap = Tablemap.get(Comparekey);
		    	ExcelValueMap = ExcelMap.get(Comparekey);
		    	for(String CompareValues : ExcelValueMap.keySet()){
		    		if(TableValueMap.containsKey(CompareValues)){
		    			if(ExcelValueMap.get(CompareValues).equalsIgnoreCase(TableValueMap.get(CompareValues))){
		    				System.out.println("MATCHED for : "+CompareValues + "=> Excel Value : "+ExcelValueMap.get(CompareValues)+"   Application Value : "+TableValueMap.get(CompareValues));
		    			}
		    			else{
		    				System.out.println("ERROR : Value Did Not Match For : "+CompareValues);
		    				System.out.println("Expected : "+ExcelValueMap.get(CompareValues)+" Found : "+TableValueMap.get(CompareValues));
		    			}
		    		}
		    	}
		    }
		    else{
		    	System.out.println("ERROR : Could Not find Parameter in Application "+Comparekey);
		    }
		    cnt++;
		}
		System.out.println("/-********************************************************************-/");
		
	}
	
	//TODO: Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
		Utils objUtils=new Utils();
		return objUtils.data1("TestCaseDetails", "Cls_ValidateClientParams");
	}

	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
	
}
