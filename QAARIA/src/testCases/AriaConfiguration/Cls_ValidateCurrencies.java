/*
 Author     		:	Abhishek
 Class Name			: 	Cls_ValidateCurrencies
 Purpose     		: 	Purpose of this file is :
						1. To Validate Currencies.
						
 Date       		:	05/26/2016
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

public class Cls_ValidateCurrencies extends VerificationMethods
{

WebElement webElement;
public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_ValidateCurrencies";

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
	
		
		@Test(dataProvider="objectTestData", description="Cls_ValidateCurrencies")
	    public void ValidateCurrencies(String strSheetName) throws Exception
	    {
	    	
			WebDriverWait webWait;
			webWait = new WebDriverWait(driver,1000);
		    Log.startTestCase(strTestCaseName);
		    Log.info("Validate Currencies");
		    
		    Thread.sleep(1000);
		    //CountriesPageObject objCountries = new CountriesPageObject();
		    AriaEOM objCurrencies = new AriaEOM();
		   try
			{
		    	//Clicking on link Configuration
			 //Clicking on link Configuration
			   objCurrencies.fn_clickConfiguration(driver, webWait).click();
		    	Thread.sleep(500);
			    
			    //Clicking on link Billing
		    	objCurrencies.fn_clickBilling(driver, webWait).click();
			    Thread.sleep(500);
			    
			    //Clicking on link Countries
			    objCurrencies.fn_clickBillingCurrencies(driver, webWait).click();
			    Thread.sleep(500);
			    
			    Log.info("###############Fetching Values from Excel Sheet###############");
			
			//TODO: Read the Client Parameters from Excel file for data comparison and verification.
			String strCurrenciesDataFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
					 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
					 + "TestData.xlsx";
			File fileCurrencies = new File(strCurrenciesDataFilePath);
			
			//TODO : Read Client Params Sheet
			List<HashMap<String,String>> ExcelFileList = ReadFiles.readExcelFile(fileCurrencies, strSheetName);
			Map<String, Map<String,String>> ExcelMap = new HashMap<String, Map<String,String>>();
			Map<String,String> ExcelListValues ;
			String NameExcelkey = "";
			String CodeVal = "";
			String DefaultVal = "";
			for(Map<String, String> map : ExcelFileList)
			    {
				ExcelListValues = new HashMap<String,String>();
				NameExcelkey = map.get("Name");
				
				if(map.get("Code")==null){
					CodeVal =  "";
					ExcelListValues.put("Code", CodeVal);
				}
				else{
					CodeVal =  map.get("Code");
					ExcelListValues.put("Code", CodeVal);
				}
				if(map.get("Is Default?")==null){
					DefaultVal =  "";
					ExcelListValues.put("Is Default?", DefaultVal);
				}
				else{
					DefaultVal =  map.get("Is Default?");
					ExcelListValues.put("Is Default?", DefaultVal);
				}
				
				if(NameExcelkey != "" )
				{
					ExcelMap.put(NameExcelkey, ExcelListValues);
				}
			}
			Log.info("Number of Currencies in Excel : "+ExcelMap.keySet().size());
			//System.out.println("Number of Currencies in Excel : "+ExcelMap.keySet().size());
				
			 Thread.sleep(30000);
			 			 	
			 Log.info("###############Fetching Values from Application###############");
			//System.out.println("###############Fetching Values from Application###############");
			
			 WebElement table = objCurrencies.fn_getDataTable(driver, webWait);
			BaseTestCase objTable = new BaseTestCase();
			List<HashMap<String,String>> TableValues = objTable.getFullTableHash(table);
			Map<String, Map<String,String>> Tablemap = new HashMap<String, Map<String,String>>();
			Map<String,String> valSetOne;
			String hmKey = "";
			String hmVal = "";
			String NameKey="";
			for(int i=0; i<TableValues.size();i++){
			   	Set<String> TableKey = TableValues.get(i).keySet();
				Iterator<String> TableKeyit = TableKey.iterator();	
				valSetOne = new HashMap<String, String>();
				while (TableKeyit.hasNext()){
					hmKey = (String)TableKeyit.next();
					hmVal = (String)TableValues.get(i).get(hmKey);
					if(hmKey.equalsIgnoreCase("Name")){
						NameKey = hmVal;
					}
					if(hmKey.equalsIgnoreCase("Code")){
						valSetOne.put("Code", hmVal);
					}
					if(hmKey.equalsIgnoreCase("Is Default?"))
					{
						valSetOne.put("Is Default?", hmVal);
					}
				}
				if(NameKey != "" )
				{
					Tablemap.put(NameKey, valSetOne);
				}
			}
			Log.info("Number of Currencies in Application : "+Tablemap.keySet().size());
			//System.out.println("Number of Currencies in Application : "+Tablemap.keySet().size());
			
			int cnt = 1;
			Log.info("/-**************************Comparing Values**************************-/");
			//System.out.println("/-**************************Comparing Values**************************-/");
			for (String Comparekey : ExcelMap.keySet()) {
				if (Tablemap.containsKey(Comparekey)) {
					Log.info("######## Currency Name : "+cnt+". "+Comparekey+" ########");
			    	//System.out.println("######## Currency Name : "+cnt+". "+Comparekey+" ########");
			    	Map<String,String> TableValueMap = new HashMap<String,String>();
			    	Map<String,String> ExcelValueMap = new HashMap<String,String>();
			    	TableValueMap = Tablemap.get(Comparekey);
			    	ExcelValueMap = ExcelMap.get(Comparekey);
			    	for(String CompareValues : ExcelValueMap.keySet()){
			    		if(TableValueMap.containsKey(CompareValues)){
			    			if(ExcelValueMap.get(CompareValues).equalsIgnoreCase(TableValueMap.get(CompareValues))){
			    				Log.info("MATCHED for : "+CompareValues + "=> Excel Value : "+ExcelValueMap.get(CompareValues)+"   Application Value : "+TableValueMap.get(CompareValues));
			    				//System.out.println("MATCHED for : "+CompareValues + "=> Excel Value : "+ExcelValueMap.get(CompareValues)+"   Application Value : "+TableValueMap.get(CompareValues));
			    			}
			    			else{
			    				Log.info("ERROR : Value Did Not Match For : "+CompareValues);
			    				//System.out.println("ERROR : Value Did Not Match For : "+CompareValues);
			    				Log.info("Expected : "+ExcelValueMap.get(CompareValues)+" Found : "+TableValueMap.get(CompareValues));
			    				//System.out.println("Expected : "+ExcelValueMap.get(CompareValues)+" Found : "+TableValueMap.get(CompareValues));
			    			}
			    		}
			    	}
			    }
			    else{
			    	Log.info("ERROR : Could Not find Currency Name in Application "+Comparekey);
			    	//System.out.println("ERROR : Could Not find Currency Name in Application "+Comparekey);
			    }
			    cnt++;
			    Log.info("/-*********************************************************-/");
			//System.out.println("/-*********************************************************-/");
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
	        return objUtils.data1("TestCaseDetails", "Cls_ValidateCurrencies");
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
