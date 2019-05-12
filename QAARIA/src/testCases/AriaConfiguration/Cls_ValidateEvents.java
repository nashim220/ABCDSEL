/*
 Author     		:	Abhishek
 Class Name			: 	Cls_Validate_Events
 Purpose     		: 	Purpose of this file is :
						1. To Validate Events.
						
 Date       		:	05/24/2016
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

public class Cls_ValidateEvents extends VerificationMethods
{

WebElement webElement;
public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_ValidateEvents";

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
		@Test(dataProvider="objectTestData", description="Cls_ValidateEvents")
	    public void ValidateEvents(String strSheetName) throws Exception
	    {
	    	
			WebDriverWait webWait;
			webWait = new WebDriverWait(driver,1000);
		    Log.startTestCase(strTestCaseName);
		    Log.info("Validating Events");
		    
		    Thread.sleep(1000);
		    NotificationSettingsPageObjects obj = new NotificationSettingsPageObjects();
		   try
			{
		    	//Clicking on link Configuration
		    	obj.fn_ClickConfiguration(driver, webWait).click();
		    	Thread.sleep(500);
			    
			    //Clicking on link Notifications
			    obj.fn_ClickNotifications(driver, webWait).click();
			    Thread.sleep(500);
			    
			    //Clicking on link Events
			    obj.fn_ClickEventsLink(driver, webWait).click();
			    Thread.sleep(500);
			    
			System.out.println("###############Fetching Values from Excel Sheet###############");
			
			//TODO: Read the Client Parameters from Excel file for data comparison and verification.
			String strEventsDataFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
					 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
					 + "TestData.xlsx";
			File fileEvents = new File(strEventsDataFilePath);
			
			//TODO : Read Event Groups Sheet
			List<HashMap<String,String>> ExcelFileList = ReadFiles.readExcelFile(fileEvents, strSheetName);
			Map<String, String> ExcelMap = new HashMap<String, String>();
			//Map<String,String> ExcelListValues ;
			String EGNExcelkey = "";
			String ActiveVal = "";
			
			for(Map<String, String> map : ExcelFileList)
			    {
				//ExcelListValues = new HashMap<String,String>();
				EGNExcelkey = map.get("Event Group Name");
				
				if(map.get("Active")==null){
					ActiveVal =  "";
					
				}
				else{
					ActiveVal =  map.get("Active");
				}
				if(EGNExcelkey != "" )
				{
					ExcelMap.put(EGNExcelkey, ActiveVal);
				}
			}
			System.out.println("Number of Event Groups in Excel : "+ExcelMap.keySet().size());
			Thread.sleep(5000);
			 			 	
			System.out.println("###############Fetching Values from Application###############");
			//Access the Table in Application
			WebElement table = driver.findElement(By.id("DataTables_Table_0"));
			BaseTestCase objTable = new BaseTestCase();
			List<HashMap<String,String>> TableValues = objTable.getFullTableHash(table);
			Map<String,String> Tablemap = new HashMap<String, String>();
			String valSetOne = "";
			String hmKey = "";
			String hmVal = "";
			String EGNameKey="";
			for(int i=0; i<TableValues.size();i++){
			   	Set<String> TableKey = TableValues.get(i).keySet();
				Iterator TableKeyit = TableKey.iterator();	
				//valSetOne = new HashMap<String, String>();
				while (TableKeyit.hasNext()){
					hmKey = (String)TableKeyit.next();
					hmVal = (String)TableValues.get(i).get(hmKey);
					if(hmKey.equalsIgnoreCase("Event Group Name")){
						EGNameKey = hmVal;
					}
					if(hmKey.equalsIgnoreCase("Active")){
						valSetOne = hmVal;
					}
					
				}
				if(EGNameKey != "" )
				{
					Tablemap.put(EGNameKey, valSetOne);
				}
			}
			
		
			System.out.println("Number of Event Groups in Application : "+Tablemap.keySet().size());
			
			int cnt = 1;
			System.out.println("/-**************************Comparing Values**************************-/");
			for (String Comparekey : ExcelMap.keySet()) {
				if (Tablemap.containsKey(Comparekey)) {
			    	System.out.println("######## Event Group : "+cnt+". "+Comparekey+" ########");
			    	if(ExcelMap.get(Comparekey).equalsIgnoreCase(Tablemap.get(Comparekey))){
			    		System.out.println("MATCHED for : "+Comparekey + "=> Excel Value : "+ExcelMap.get(Comparekey)+"   Application Value : "+Tablemap.get(Comparekey));
			    	}
			    	else{
			    		System.out.println("ERROR : Value Did Not Match For : "+Comparekey);
			    		System.out.println("Expected : "+ExcelMap.get(Comparekey)+" Found : "+Tablemap.get(Comparekey));
			    	}
			    }
				cnt++; 
			}
			 
			System.out.println("/-*********************************************************-/");
		}
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
	        return objUtils.data1("TestCaseDetails", "Cls_ValidateEvents");
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
