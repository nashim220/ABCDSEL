/*
 Author     		:	Abhishek
 Class Name			: 	Cls_Validate_BillLagDays
 Purpose     		: 	Purpose of this file is :
						1. To Validate Bill Lag Days.
						
 Date       		:	05/20/2016
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
import testCases.BaseTestCase;
import utility.*;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_Validate_BillLagDays extends VerificationMethods
{

WebElement webElement;
public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_Validate_BillLagDays";

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
		@Test(dataProvider="objectTestData", description="Cls_ValidateBillLagDays")
	    public void ValidateBillLagDays(String strSheetName, String AccNum) throws Exception
	    {
	    	
			WebDriverWait webWait;
			webWait = new WebDriverWait(driver,1000);
		    Log.startTestCase(strTestCaseName);
		    Log.info("Validating Bill Lag Days");
		    
		    Thread.sleep(1000);
		    CountriesPageObject objCountries = new CountriesPageObject();
		   try
			{
		    	//Clicking on link Configuration
		    	objCountries.fn_ConfigurationLink(driver, webWait).click();
		    	Thread.sleep(500);
			    
			    //Clicking on link Billing
			    objCountries.fn_BillingLink(driver, webWait).click();
			    Thread.sleep(500);
			    
			    //Clicking on link Chart Of Accounts
			    objCountries.fn_BillLagDaysLink(driver, webWait).click();
			    Thread.sleep(500);
			    
			System.out.println("###############Fetching Values from Excel Sheet###############");
			
			//TODO: Read the Client Parameters from Excel file for data comparison and verification.
			String strCountriesDataFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
					 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
					 + "TestData.xlsx";
			File fileCountries = new File(strCountriesDataFilePath);
			
			//TODO : Read Client Params Sheet
			List<HashMap<String,String>> ExcelFileList = ReadFiles.readExcelFile(fileCountries, strSheetName);
			Map<String, String> ExcelMap = new HashMap<String, String>();
			//Map<String,String> ExcelListValues ;
			String RIExcelkey = "";
			String ValueDaysVal = "";
			
			for(Map<String, String> map : ExcelFileList)
			    {
				//ExcelListValues = new HashMap<String,String>();
				RIExcelkey = map.get("Recurring Interval");
				
				if(map.get("Value (Days)")==null){
					ValueDaysVal =  "";
					
				}
				else{
					ValueDaysVal =  map.get("Value (Days)");
				}
				if(RIExcelkey != "" )
				{
					ExcelMap.put(RIExcelkey, ValueDaysVal);
				}
			}
			System.out.println("Number of Parameters in Excel : "+ExcelMap.keySet().size());
			Thread.sleep(5000);
			 			 	
			System.out.println("###############Fetching Values from Application###############");
			//Access the Table in Application
			WebElement table = driver.findElement(By.xpath("//*[@id=\"billLagDays\"]/div[1]/table/tbody"));
			
			//Create Custom Xpath for iterating the rows and get the values
			String RecurringIntervalXpath1 = "//*[@id=\"billLagDays\"]/div[1]/table/tbody/tr[";
			String RecurringIntervalXpath2 = "]/td[1]/label";
			String ValueDaysXpath1 = "//*[@id=\"BillLagDays_";
			String ValueDaysXpath2 = "_BILL_LAG_DAYS\"]";
			
			
			// Now get all the TR elements from the table
			List<WebElement> TotalRowCount = table.findElements(By.tagName("tr"));
			Map<String,String> TableMap = new HashMap<String,String>();
			String strRecurringInterval = "";
			String RIXpath = "";
			String strRIUpperCase = "";
			String ValueDaysXpath = "";
			String ValueDays = "";
			String key = "";
			String value = "";
			for(int i=0; i<TotalRowCount.size();i++){
				if(i==0 ){
					continue;
				}
				else if(i%2==0){
					RIXpath = RecurringIntervalXpath1 + (i+1) + RecurringIntervalXpath2;
					strRecurringInterval = driver.findElement(By.xpath(RIXpath)).getText();
					key = strRecurringInterval;
					strRIUpperCase = strRecurringInterval.replaceAll("-", "_").toUpperCase();
					ValueDaysXpath = ValueDaysXpath1+strRIUpperCase+ValueDaysXpath2;
					ValueDays = driver.findElement(By.xpath(ValueDaysXpath)).getAttribute("value");
					value = ValueDays;
				}
				else {
					continue;
				}
				if(key!=null){
					TableMap.put(key, value);
				}
			}
			
			System.out.println("Number of Parameters in Application : "+TableMap.keySet().size());
			
			int cnt = 1;
			System.out.println("/-**************************Comparing Values**************************-/");
			for (String Comparekey : ExcelMap.keySet()) {
				if (TableMap.containsKey(Comparekey)) {
			    	System.out.println("######## Recurring Interval : "+cnt+". "+Comparekey+" ########");
			    	if(ExcelMap.get(Comparekey).equalsIgnoreCase(TableMap.get(Comparekey))){
			    		System.out.println("MATCHED for : "+Comparekey + "=> Excel Value : "+ExcelMap.get(Comparekey)+"   Application Value : "+TableMap.get(Comparekey));
			    	}
			    	else{
			    		System.out.println("ERROR : Value Did Not Match For : "+Comparekey);
			    		System.out.println("Expected : "+ExcelMap.get(Comparekey)+" Found : "+TableMap.get(Comparekey));
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
	        return objUtils.data1("TestCaseDetails", "Cls_ValidateBillLagDays");
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
