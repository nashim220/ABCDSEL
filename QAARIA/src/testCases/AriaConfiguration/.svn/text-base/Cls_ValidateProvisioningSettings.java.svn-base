/*
 Author     		:	Abhishek
 Class Name			: 	Cls_Validate_ProvisioningSettings
 Purpose     		: 	Purpose of this file is :
						1. To Validate Provisioning Settings.
						
 Date       		:	05/23/2016
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
import pageObjects.NotificationSettingsPageObjects;
import testCases.BaseTestCase;
import utility.*;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_ValidateProvisioningSettings extends VerificationMethods
{

WebElement webElement;
public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_Validate_ProvisioningSettings";

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
		@Test(dataProvider="objectTestData", description="Cls_ValidateProvisioningSettings")
	    public void ValidateCountries(String strSheetName, String AccNum) throws Exception
	    {
	    	
			WebDriverWait webWait;
			webWait = new WebDriverWait(driver,1000);
		    Log.startTestCase(strTestCaseName);
		    Log.info("Validate Provisioning Settings");
		    
		    Thread.sleep(1000);
		    NotificationSettingsPageObjects objCountries = new NotificationSettingsPageObjects();
		   try
			{
		    	//Clicking on link Configuration
		    	objCountries.fn_ClickConfiguration(driver, webWait).click();
		    	Thread.sleep(500);
			    
			    //Clicking on link Notifications
			    objCountries.fn_ClickNotifications(driver, webWait).click();
			    Thread.sleep(500);
			    
			    //Clicking on link Provisioning Settings
			    objCountries.fn_ClickProvisioningSettingsLink(driver, webWait).click();
			    Thread.sleep(500);
			    
			    System.out.println("###############Fetching Values from Excel Sheet###############");
			
			//TODO: Read the Client Parameters from Excel file for data comparison and verification.
			String strCountriesDataFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
					 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
					 + "TestData.xlsx";
			File fileCountries = new File(strCountriesDataFilePath);
			
			//TODO : Read Client Params Sheet
			List<HashMap<String,String>> ExcelFileList = ReadFiles.readExcelFile(fileCountries, strSheetName);
			Map<String, Map<String,String>> ExcelMap = new HashMap<String, Map<String,String>>();
			Map<String,String> ExcelListValues ;
			String PNameExcelkey = "";
			String GroupNameVal = "";
			String DescriptionVal = "";
			String ValueVal = "";
			for(Map<String, String> map : ExcelFileList)
			    {
				ExcelListValues = new HashMap<String,String>();
				PNameExcelkey = map.get("Parameter Name");
				
				if(map.get("Group Name")==null){
					GroupNameVal =  "";
					ExcelListValues.put("Group Name", GroupNameVal);
				}
				else{
					GroupNameVal =  map.get("Group Name");
					ExcelListValues.put("Group Name", GroupNameVal);
				}
				if(map.get("Description")==null){
					DescriptionVal =  "";
					ExcelListValues.put("Description", DescriptionVal);
				}
				else{
					DescriptionVal =  map.get("Description");
					ExcelListValues.put("Description", DescriptionVal);
				}
				
				if(map.get("Value")==null){
					ValueVal =  "";
					ExcelListValues.put("Value", ValueVal);
				}
				else{
					ValueVal =  map.get("Value");
					ExcelListValues.put("Value", ValueVal);
				}
				
				if(PNameExcelkey != "" )
				{
					ExcelMap.put(PNameExcelkey, ExcelListValues);
				}
			}
			System.out.println("Number of Parameters in Excel : "+ExcelMap.keySet().size());
				
			 Thread.sleep(30000);
			 			 	
			
			System.out.println("###############Fetching Values from Application###############");
			
			WebElement table = driver.findElement(By.id("DataTables_Table_0"));
			BaseTestCase objTable = new BaseTestCase();
			List<HashMap<String,String>> TableValues = objTable.getFullTableHash(table);
			Map<String, Map<String,String>> Tablemap = new HashMap<String, Map<String,String>>();
			Map<String,String> valSetOne;
			String hmKey = "";
			String hmVal = "";
			String PNameKey="";
			for(int i=0; i<TableValues.size();i++){
			   	Set<String> TableKey = TableValues.get(i).keySet();
				Iterator TableKeyit = TableKey.iterator();	
				valSetOne = new HashMap<String, String>();
				while (TableKeyit.hasNext()){
					hmKey = (String)TableKeyit.next();
					hmVal = (String)TableValues.get(i).get(hmKey);
					if(hmKey.equalsIgnoreCase("Parameter Name")){
						PNameKey = hmVal;
					}
					if(hmKey.equalsIgnoreCase("Group Name")){
						valSetOne.put("Group Name", hmVal);
					}
					if(hmKey.equalsIgnoreCase("Description"))
					{
						valSetOne.put("Description", hmVal);
					}
					if(hmKey.equalsIgnoreCase("Value"))
					{
						valSetOne.put("Value", hmVal);
					}
				}
				if(PNameKey != "" )
				{
					Tablemap.put(PNameKey, valSetOne);
				}
			}
			System.out.println("Number of Parameters in Application : "+Tablemap.keySet().size());
			
			int cnt = 1;
			System.out.println("/-**************************Comparing Values**************************-/");
			for (String Comparekey : ExcelMap.keySet()) {
				if (Tablemap.containsKey(Comparekey)) {
			    	System.out.println("######## Parameter Name : "+cnt+". "+Comparekey+" ########");
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
	        return objUtils.data1("TestCaseDetails", "Cls_ValidateProvisioningSettings");
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
