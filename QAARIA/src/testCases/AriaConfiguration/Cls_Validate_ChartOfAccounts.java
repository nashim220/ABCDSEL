/*
 Author     		:	Abhishek
 Class Name			: 	Cls_Validate_ChartOfAccounts
 Purpose     		: 	Purpose of this file is :
						1. To Validate Chart of Accounts.
						
 Date       		:	05/20/2016
 Version Information:	Version 1.0
 */
package testCases.AriaConfiguration;

import java.io.File;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AriaEOM;
//import pageObjects.CountriesPageObject;
import testCases.BaseTestCase;
import utility.*;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_Validate_ChartOfAccounts extends VerificationMethods
{

WebElement webElement;
public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_Validate_ChartOfAccounts";

		@BeforeClass
		 public static void beforeMethod()throws Exception
		 {	
			DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
			driver = Utils.fn_SetChromeDriverProperties();
			System.setProperty("webdriver.chrome.driver",chromeDriverPath);
			//driver = new ChromeDriver();
			EnvironmentDetails objEnv = new EnvironmentDetails();
			Log.startTestCase("Login to application");
			AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
		 }
	
		//@Test(dataProvider="objectTestData", description="Cls_Validate_Countries")
		@Test(dataProvider="objectTestData", description="Cls_ValidateChartOfAccounts")
	    public void ValidateCountries(String strSheetName, String AccNum) throws Exception
	    {
	    	
			WebDriverWait webWait;
			webWait = new WebDriverWait(driver,1000);
		    Log.startTestCase(strTestCaseName);
		    Log.info("Validating Chart Of Accounts");
		    
		    Thread.sleep(1000);
		    
		   try
			{
			   AriaEOM objChartOfAcc = new AriaEOM();
		    	//Clicking on link Configuration
			   objChartOfAcc.fn_clickConfiguration(driver, webWait).click();
		    	Thread.sleep(500);
			    
			    //Clicking on link Billing
		    	objChartOfAcc.fn_clickBilling(driver, webWait).click();
			    Thread.sleep(500);
			    
			    //Clicking on link Chart Of Accounts
			    objChartOfAcc.fn_clickChartOfAccounts(driver, webWait).click();
			    Thread.sleep(500);
			    
			    Log.info("###############Fetching Values from Excel Sheet###############");
			
			//TODO: Read the Client Parameters from Excel file for data comparison and verification.
			String strCountriesDataFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
					 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
					 + "TestData.xlsx";
			File fileCountries = new File(strCountriesDataFilePath);
			
			//TODO : Read Client Params Sheet
			List<HashMap<String,String>> ExcelFileList = ReadFiles.readExcelFile(fileCountries, strSheetName);
			Map<String, Map<String,String>> ExcelMap = new HashMap<String, Map<String,String>>();
			Map<String,String> ExcelListValues ;
			String COACodeExcelkey = "";
			String COAIDVal = "";
			String DescriptionVal = "";
			//String CurrencyVal = "";
			for(Map<String, String> map : ExcelFileList)
			    {
				ExcelListValues = new HashMap<String,String>();
				COACodeExcelkey = map.get("COA Code");
				
				if(map.get("COA ID")==null){
					COAIDVal =  "";
					ExcelListValues.put("COA ID", COAIDVal);
				}
				else{
					COAIDVal =  map.get("COA ID");
					ExcelListValues.put("COA ID", COAIDVal);
				}
				if(map.get("Description")==null){
					DescriptionVal =  "";
					ExcelListValues.put("Description", DescriptionVal);
				}
				else{
					DescriptionVal =  map.get("Description");
					ExcelListValues.put("Description", DescriptionVal);
				}
				
				if(COACodeExcelkey != "" )
				{
					ExcelMap.put(COACodeExcelkey, ExcelListValues);
				}
			}
			Log.info("Number of Parameters in Excel : "+ExcelMap.keySet().size());
			
				
			 Thread.sleep(30000);
			 			 	
			 Log.info("###############Fetching Values from Application###############");
			
			
			 WebElement table = objChartOfAcc.fn_getDataTable(driver, webWait);
			 
			BaseTestCase objTable = new BaseTestCase();
			List<HashMap<String,String>> TableValues = objTable.getFullTableHash(table);
			Map<String, Map<String,String>> Tablemap = new HashMap<String, Map<String,String>>();
			Map<String,String> valSetOne;
			String hmKey = "";
			String hmVal = "";
			String COACodeKey="";
			for(int i=0; i<TableValues.size();i++){
			   	Set<String> TableKey = TableValues.get(i).keySet();
				Iterator<String> TableKeyit = TableKey.iterator();	
				valSetOne = new HashMap<String, String>();
				while (TableKeyit.hasNext()){
					hmKey = (String)TableKeyit.next();
					hmVal = (String)TableValues.get(i).get(hmKey);
					if(hmKey.equalsIgnoreCase("COA Code")){
						COACodeKey = hmVal;
					}
					if(hmKey.equalsIgnoreCase("COA ID")){
						valSetOne.put("COA ID", hmVal);
					}
					if(hmKey.equalsIgnoreCase("Description"))
					{
						valSetOne.put("Description", hmVal);
					}
				}
				if(COACodeKey != "" )
				{
					Tablemap.put(COACodeKey, valSetOne);
				}
			}
			Log.info("Number of Parameters in Application : "+Tablemap.keySet().size());
			
			
			int cnt = 1;
			Log.info("/-**************************Comparing Values**************************-/");
			
			for (String Comparekey : ExcelMap.keySet()) {
				if (Tablemap.containsKey(Comparekey)) {
					Log.info("######## COA Code : "+cnt+". "+Comparekey+" ########");
			    	Map<String,String> TableValueMap = new HashMap<String,String>();
			    	Map<String,String> ExcelValueMap = new HashMap<String,String>();
			    	TableValueMap = Tablemap.get(Comparekey);
			    	ExcelValueMap = ExcelMap.get(Comparekey);
			    	for(String CompareValues : ExcelValueMap.keySet()){
			    		if(TableValueMap.containsKey(CompareValues)){
			    			if(ExcelValueMap.get(CompareValues).equalsIgnoreCase(TableValueMap.get(CompareValues))){
			    				Log.info("MATCHED for : "+CompareValues + "=> Excel Value : "+ExcelValueMap.get(CompareValues)+"   Application Value : "+TableValueMap.get(CompareValues));
			    			}
			    			else{
			    				Log.info("ERROR : Value Did Not Match For : "+CompareValues);
			    				Log.info("Expected : "+ExcelValueMap.get(CompareValues)+" Found : "+TableValueMap.get(CompareValues));
			    			}
			    		}
			    	}
			    }
			    else{
			    	Log.info("ERROR : Could Not find COA in Application "+Comparekey);
			    }
			    cnt++;
			    Log.info("/-*********************************************************-/");
			
			}  }
			catch (Exception exception)
			{
				Log.error("Error : "+exception);
			}
			Thread.sleep(5000);
		}
		
	    @DataProvider(name = "objectTestData")
	    public Object[][] data() throws DataDrivenFrameworkException
	    {
	        Utils objUtils=new Utils();
	        return objUtils.data1("TestCaseDetails", "Cls_ValidateChartOfAccounts");
	    }
	    
		
		@AfterTest
		public void afterMethod() throws Exception
		 {
			 //Logs out of the application
			 AriaLoginLogout.appLogout(driver); 
			 //Quitting the driver
			 driver.quit(); 
		 }
}
