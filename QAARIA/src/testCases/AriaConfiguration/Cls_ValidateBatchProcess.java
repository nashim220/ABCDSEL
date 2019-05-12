/*
 Author     		:	Abhishek
 Class Name			: 	Cls_Validate_BatchProcess
 Purpose     		: 	Purpose of this file is :
						1. To Validate BatchProcess.
						
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

public class Cls_ValidateBatchProcess extends VerificationMethods
{

WebElement webElement;
public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_Validate_BatchProcess";

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
		@Test(dataProvider="objectTestData", description="Cls_ValidateBatchProcess")
	    public void ValidateBatchProcess(String strSheetName, String AccNum) throws Exception
	    {
	    	
			WebDriverWait webWait;
			webWait = new WebDriverWait(driver,1000);
		    Log.startTestCase(strTestCaseName);
		    Log.info("Validating Batch Process");
		    
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
			    objCountries.fn_BatchProcessLink(driver, webWait).click();
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
			String NameExcelkey = "";
			String JobTypeVal = "";
			String DescriptionVal = "";
			String FrequencyVal = "";
			String StatusVal = "";
			for(Map<String, String> map : ExcelFileList)
			    {
				ExcelListValues = new HashMap<String,String>();
				NameExcelkey = map.get("Name");
				
				if(map.get("Job Type")==null){
					JobTypeVal =  "";
					ExcelListValues.put("Job Type",JobTypeVal);
				}
				else{
					JobTypeVal =  map.get("Job Type");
					ExcelListValues.put("Job Type",JobTypeVal);
				}
				
				if(map.get("Description")==null){
					DescriptionVal =  "";
					ExcelListValues.put("Description",DescriptionVal);
				}
				else{
					DescriptionVal =  map.get("Description");
					ExcelListValues.put("Description",DescriptionVal);
				}
				
				if(map.get("Frequency")==null){
					FrequencyVal =  "";
					ExcelListValues.put("Frequency",FrequencyVal);
				}
				else{
					FrequencyVal =  map.get("Frequency");
					ExcelListValues.put("Frequency",FrequencyVal);
				}
				
				if(map.get("Status")==null){
					StatusVal =  "";
					ExcelListValues.put("Status",StatusVal);
				}
				else{
					StatusVal =  map.get("Status");
					ExcelListValues.put("Status",StatusVal);
				}
				if(NameExcelkey != "" )
				{
					ExcelMap.put(NameExcelkey, ExcelListValues);
				}
			}
			System.out.println("Number of Parameters in Excel : "+ExcelMap.keySet().size());
			Thread.sleep(5000);
			/*System.out.println("/-******************Print Excel Values**********************-/");
			for (String name: ExcelMap.keySet()){

	            String key =name.toString();
	            String value = ExcelMap.get(name).toString();  
	            System.out.println(key + " " + value);  
			}
			System.out.println("/-*****************************************************-/");*/

			 			 	
			System.out.println("###############Fetching Values from Application###############");
			//Access the Table in Application
			WebElement table = driver.findElement(By.xpath("//*[@id=\"BatchProcessingManage\"]/div[3]/form/table/tbody"));
			Map<String, Map<String,String>> Tablemap = new HashMap<String, Map<String,String>>();
			Map<String,String> valSetOne;
			String JobTypeAppVal = "";
			String NameAppVal = "";
			String DescAppVal = "";
			String FreqAppVal = "";
			String StatusAppVal = "";
			// Now get all the TR elements from the table
			List<WebElement> TotalRowCount = table.findElements(By.tagName("tr"));
			for(WebElement Row : TotalRowCount){
				// Now get all the TD elements from the Rows
				List<WebElement> ColumnCount = Row.findElements(By.tagName("td"));
				valSetOne = new HashMap<String,String>();
				int i=0;
				for(WebElement col : ColumnCount){
					if(i==0){
						JobTypeAppVal = col.getText();
						valSetOne.put("Job Type", JobTypeAppVal);
					}
					if(i==1){
						NameAppVal = col.getText();
					}
					if(i==2){
						DescAppVal = col.getText();
						valSetOne.put("Description", DescAppVal);
					}
					if(i==3){
						FreqAppVal = col.getText();
						valSetOne.put("Frequency", FreqAppVal);
					}
					if(i==4){
						// Now get all the Select elements from the Columns
						List<WebElement> SelectCount = col.findElements(By.tagName("select"));
						if(SelectCount.size()==0){
							StatusAppVal ="";
						}
						else{
							for(WebElement select : SelectCount){
								// Now get all the Option elements from the Select
								List<WebElement> OptionCount = select.findElements(By.tagName("option"));
								for(WebElement Option : OptionCount){
									String value = Option.getAttribute("selected");
									if(value==null){
										continue;
									}
									else{
										StatusAppVal = Option.getText();
									}
								}
							}
						}
						valSetOne.put("Status", StatusAppVal);
					}
					i++;
				}
				if(NameAppVal != ""){
					Tablemap.put(NameAppVal, valSetOne);
				}
			}
			System.out.println("Number of Parameters in Application : "+Tablemap.keySet().size());
			/*System.out.println("/-******************Print App Values**********************-/");
			for (String name: Tablemap.keySet()){

	            String key =name.toString();
	            String value = Tablemap.get(name).toString();  
	            System.out.println(key + " " + value);  
			}
			System.out.println("/-*****************************************************-/");*/
			
			int cnt = 1;
			System.out.println("/-**************************Comparing Values**************************-/");
			for (String Comparekey : ExcelMap.keySet()) {
				if (Tablemap.containsKey(Comparekey)) {
			    	System.out.println("######## Name : "+cnt+". "+Comparekey+" ########");
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
			    	System.out.println("ERROR : Could Not find Name in Application "+Comparekey);
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
	        return objUtils.data1("TestCaseDetails", "Cls_ValidateBatchProcess");
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
