/*
 Author     		:	Abhishek
 Class Name			: 	Cls_ValidateTaxationConfiguration
 Purpose     		: 	Purpose of this file is :
						1. To Validate Taxation Configuration
						
 Date       		:	06/03/2016
 Version Information:	Version 1.0
 */
package testCases.AriaConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CountriesPageObject;
import pageObjects.IntegrationsPageObject;
import testCases.BaseTestCase;
import utility.*;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_ValidateTaxationConfiguration extends VerificationMethods
{

WebElement webElement;
public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_ValidateTaxationConfiguration";

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
	
		
		@Test(dataProvider="objectTestData", description="Cls_ValidateTaxationConfiguration")
	    public void TaxationConfiguration(String strSheetName) throws Exception
	    {
	    	
			WebDriverWait webWait;
			webWait = new WebDriverWait(driver,1000);
		    Log.startTestCase(strTestCaseName);
		    Log.info("Validate Taxation Configuration");
		    
		    Thread.sleep(1000);
		    IntegrationsPageObject ObjTaxConfig = new IntegrationsPageObject();
		   try
			{
		    	//Clicking on link Configuration
		    	ObjTaxConfig.fn_ConfigurationLink(driver, webWait).click();
		    	Thread.sleep(500);
			    
			    //Clicking on link Billing
			    ObjTaxConfig.fn_IntigrationsLink(driver, webWait).click();
			    Thread.sleep(500);
			    
			    //Clicking on link Chart Of Accounts
			    ObjTaxConfig.fn_TaxConfigLink(driver, webWait).click();
			    Thread.sleep(500);
			    
			    System.out.println("###############Fetching Values from Excel Sheet###############");
			
			//TODO: Read the Client Parameters from Excel file for data comparison and verification.
			String strTaxConfigDataFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
					 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
					 + "TestData.xlsx";
			File fileTaxConfig = new File(strTaxConfigDataFilePath);
			
			//TODO : Read Client Params Sheet
			List<HashMap<String,String>> ExcelFileList = ReadFiles.readExcelFile(fileTaxConfig, strSheetName);
			Map<String, Map<String,String>> ExcelMap = new HashMap<String, Map<String,String>>();
			Map<String,String> ExcelListValues ;
			String SetNameExcelkey = "";
			String SetNumberVal = "";
			String SetDescriptionVal = "";
			String MethodTypeVal = "";
			for(Map<String, String> map : ExcelFileList)
			    {
				ExcelListValues = new HashMap<String,String>();
				SetNameExcelkey = map.get("Set Name");
				
				if(map.get("Set Number")==null){
					SetNumberVal =  "";
					ExcelListValues.put("Set Number", SetNumberVal);
				}
				else{
					SetNumberVal =  map.get("Set Number");
					ExcelListValues.put("Set Number", SetNumberVal);
				}
				if(map.get("Set Description")==null){
					SetDescriptionVal =  "";
					ExcelListValues.put("Set Description", SetDescriptionVal);
				}
				else{
					SetDescriptionVal =  map.get("Set Description");
					ExcelListValues.put("Set Description", SetDescriptionVal);
				}
				
				if(map.get("Method Type")==null){
					MethodTypeVal =  "";
					ExcelListValues.put("Method Type", MethodTypeVal);
				}
				else{
					MethodTypeVal =  map.get("Method Type");
					ExcelListValues.put("Method Type", MethodTypeVal);
				}
				if(SetNameExcelkey != "" )
				{
					ExcelMap.put(SetNameExcelkey, ExcelListValues);
				}
			}
			System.out.println("Number of Client Defined Identifier in Excel : "+ExcelMap.keySet().size());
				
			 Thread.sleep(30000);
			 			 	
			
			System.out.println("###############Fetching Values from Application###############");
			
			WebElement table = driver.findElement(By.id("DataTables_Table_0"));
			BaseTestCase objTable = new BaseTestCase();
			List<HashMap<String,String>> TableValues = objTable.getFullTableHash(table);
			Map<String, Map<String,String>> Tablemap = new HashMap<String, Map<String,String>>();
			Map<String,String> valSetOne;
			String hmKey = "";
			String hmVal = "";
			String SetNameKey="";
			for(int i=0; i<TableValues.size();i++){
			   	Set<String> TableKey = TableValues.get(i).keySet();
				Iterator TableKeyit = TableKey.iterator();	
				valSetOne = new HashMap<String, String>();
				while (TableKeyit.hasNext()){
					hmKey = (String)TableKeyit.next();
					hmVal = (String)TableValues.get(i).get(hmKey);
					if(hmKey.equalsIgnoreCase("Set Name")){
						SetNameKey = hmVal;
					}
					if(hmKey.equalsIgnoreCase("Set Number")){
						valSetOne.put("Set Number", hmVal);
					}
					if(hmKey.equalsIgnoreCase("Set Description"))
					{
						valSetOne.put("Set Description", hmVal);
					}
					if(hmKey.equalsIgnoreCase("Method Type"))
					{
						valSetOne.put("Method Type", hmVal);
					}
				}
				if(SetNameKey != "" )
				{
					Tablemap.put(SetNameKey, valSetOne);
				}
			}
			System.out.println("Number of Client Defined Identifier in Application : "+Tablemap.keySet().size());
			
			int cnt = 1;
			System.out.println("/-**************************Comparing Values**************************-/");
			for (String Comparekey : ExcelMap.keySet()) {
				if (Tablemap.containsKey(Comparekey)) {
			    	System.out.println("######## Set Name : "+cnt+". "+Comparekey+" ########");
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
			    	System.out.println("ERROR : Could Not find Set Name in Application "+Comparekey);
			    }
			    cnt++;
            
			System.out.println("/-*********************************************************-/");
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
	        return objUtils.data1("TestCaseDetails", "Cls_ValidateTaxationConfiguration");
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
