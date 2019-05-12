
/*
 Author     		:	Abhishek
 Class Name			: 	Cls_ValidateNotificationSettings  
 Purpose     		: 	Purpose of this file is :
						1. To Capture the data under notification settings and validate with the Base Documentation
												
 Date       		:	05/19/2016
 Version Information:	Version 1.0
 
  PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "Notification settings" sheet for excel "TestData.xlsx".

 Test Steps 		:	1. Login using valid role credentials for verifying the Configuration Payment Settings.
 						2. Navigate to the Configuration -> Notifications -> Notification settings.
 						3. Read the table on the landing page and compare it with the expected data mentioned in the
 						"Notification settings" sheet for excel "TestData.xls".
 						
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AriaConfiguration;
import org.testng.annotations.*;

//import com.sun.java.util.jar.pack.Package.File;

import appModules.AriaLoginLogout;
import appModules.Cls_NotificationSettingsActns;
import appModules.SelectBrowser;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Log;
import utility.ReadFiles;
import utility.Utils;
import utility.EnvironmentDetails;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import testCases.BaseTestCase;

public class Cls_ValidateNotificationSettings extends BaseTestCase{
		
		public WebDriver driver;
		
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
			
		@Test
		public void fn_ValidateNotificationSettings() throws Exception 
		{
			WebDriverWait webWait;
			webWait = new WebDriverWait(driver,1000);
			Log.startTestCase("Validate_Notification_Settings" );	
			
			Cls_NotificationSettingsActns objfnctns = new Cls_NotificationSettingsActns();
			objfnctns.fn_notificationSettings(driver, webWait);
			Thread.sleep(5000);
			
			System.out.println("###############Fetching Values from Excel Sheet###############");
			
			//TODO: Read the Client Parameters from Excel file for data comparison and verification.
			String strNotificationSettingsDataFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
					 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
					 + "TestData.xlsx";
			File fileNotificationSettings = new File(strNotificationSettingsDataFilePath);
			
			//TODO : Read Client Params Sheet
			List<HashMap<String,String>> ExcelFileList = ReadFiles.readExcelFile(fileNotificationSettings, "NotificationSettings");
			Map<String, Map<String,String>> ExcelMap = new HashMap<String, Map<String,String>>();
			Map<String,String> ExcelListValues ;
			String PNameExcelkey = "";
			String GroupNameVal = "";
			String ValueVal = "";
			String DescriptionVal = "";
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
				if(map.get("Value")==null){
					ValueVal =  "";
					ExcelListValues.put("Value", ValueVal);
				}
				else{
					ValueVal =  map.get("Value");
					ExcelListValues.put("Value", ValueVal);
				}
				if(map.get("Description")==null){
					DescriptionVal =  "";
					ExcelListValues.put("Description", DescriptionVal);
				}
				else{
					DescriptionVal =  map.get("Description");
					ExcelListValues.put("Description", DescriptionVal);
				}
				if(PNameExcelkey != "" )
				{
					ExcelMap.put(PNameExcelkey, ExcelListValues);
				}
			}
			System.out.println("Number of Parameters in Excel : "+ExcelMap.keySet().size());
			
			
			System.out.println("###############Fetching Values from Application###############");
			//WebElement Webtable = driver.findElement(By.id("DataTables_Table_0"));
			WebElement table = driver.findElement(By.id("DataTables_Table_0"));
			BaseTestCase objTable = new BaseTestCase();
			List<HashMap<String,String>> TableValues = objTable.getFullTableHash(table);
			Map<String, Map<String,String>> Tablemap = new HashMap<String, Map<String,String>>();
			Map<String,String> valSetOne;
			String hmKey = "";
			String hmVal = "";
			String ParameterNameKey="";
			for(int i=0; i<TableValues.size();i++){
			   	Set<String> TableKey = TableValues.get(i).keySet();
				Iterator TableKeyit = TableKey.iterator();	
				valSetOne = new HashMap<String, String>();
				while (TableKeyit.hasNext()){
					hmKey = (String)TableKeyit.next();
					hmVal = (String)TableValues.get(i).get(hmKey);
					if(hmKey.equalsIgnoreCase("Parameter Name")){
						ParameterNameKey = hmVal;
					}
					if(hmKey.equalsIgnoreCase("Group Name")){
						valSetOne.put("Group Name", hmVal);
					}
					if(hmKey.equalsIgnoreCase("Value"))
					{
						valSetOne.put("Value", hmVal);
					}
					if(hmKey.equalsIgnoreCase("Description"))
					{
						valSetOne.put("Description", hmVal);
					}
				}
				if(ParameterNameKey != "" )
				{
					Tablemap.put(ParameterNameKey, valSetOne);
				}
			}
			System.out.println("Number of Parameters in Application : "+Tablemap.keySet().size());
			
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
			Thread.sleep(8000);
		}

		
		@AfterTest
		public void afterMethod() throws Exception
		 {
			 //TODO: Logs out of the application & quit the driver
			 AriaLoginLogout.appLogout(driver);
			 driver.quit(); 
		 }
		 

	}
	
	

