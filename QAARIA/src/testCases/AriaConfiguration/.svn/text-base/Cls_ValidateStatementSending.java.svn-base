/*
 Author     		:	Abhishek
 Class Name			: 	Cls_Validate_StatementSending
 Purpose     		: 	Purpose of this file is :
						1. To Validate Statement Sending.
						
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
import pageObjects.NotificationSettingsPageObjects;
import testCases.BaseTestCase;
import utility.*;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_ValidateStatementSending extends VerificationMethods
{

WebElement webElement;
public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_ValidateStatementSending";

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
		@Test(dataProvider="objectTestData", description="Cls_ValidateStatementSending")
	    public void ValidateStatementSending(String strSheetName) throws Exception
	    {
	    	
			WebDriverWait webWait;
			webWait = new WebDriverWait(driver,1000);
		    Log.startTestCase(strTestCaseName);
		    Log.info("Validate Statement Sending");
		    
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
			    obj.fn_ClickStatementSendingLink(driver, webWait).click();
			    Thread.sleep(500);
			    
			    System.out.println("###############Fetching Values from Excel Sheet###############");
			
			//TODO: Read the Client Parameters from Excel file for data comparison and verification.
			String strStatementSendingDataFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
					 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
					 + "TestData.xlsx";
			File fileStatementSending = new File(strStatementSendingDataFilePath);
			
			//TODO : Read Client Params Sheet
			List<HashMap<String,String>> ExcelFileList = ReadFiles.readExcelFile(fileStatementSending, strSheetName);
			
			Map<String, String> ExcelMap = new HashMap<String, String>();
			//Map<String,String> ExcelListValues ;
			String SSParamExcelkey = "";
			String CheckedVal = "";
			for(Map<String, String> map : ExcelFileList)
			{
				//ExcelListValues = new HashMap<String,String>();
				SSParamExcelkey = map.get("Statement Sending Parameters");
				if(map.get("Checked")==null){
					CheckedVal =  "";
				}
				else{
					CheckedVal =  map.get("Checked");
				}
				if(SSParamExcelkey != "" )
				{
					ExcelMap.put(SSParamExcelkey, CheckedVal);
				}
			}
			
			System.out.println("###############Fetching Values from Application###############");
			Map<String, String> TableMap = new HashMap<String, String>(); 
			WebElement SendAllConfigStatementSend = driver.findElement(By.xpath("//*[@id=\"ajaxcontent\"]/form/div[1]"));
			String Check = "";
			String CheckTrue = "";
			String Label = "";
			// Now get all the li elements from the table
			List<WebElement> TotalLiCount = SendAllConfigStatementSend.findElements(By.tagName("li"));
			for(WebElement Li : TotalLiCount){
				Label = Li.findElement(By.tagName("label")).getText();
				Check = Li.findElement(By.tagName("input")).getAttribute("checked");
				if(Check==null){
					CheckTrue = "";
				}
				else{
					CheckTrue = Li.findElement(By.tagName("input")).getAttribute("checked");
				}
				if(Label != ""){
					TableMap.put(Label, CheckTrue);
				}
			}
			
			String LabelDetails = "";
			String CheckDetails = "";
			String CheckDetailsTrue = "";
			WebElement ConfigStatementSend = driver.findElement(By.xpath("//*[@id=\"configure\"]"));
			List<WebElement> LiCount = ConfigStatementSend.findElements(By.tagName("li"));
			for(WebElement Li : LiCount){
				LabelDetails = Li.findElement(By.tagName("label")).getText();
				CheckDetails = Li.findElement(By.tagName("input")).getAttribute("checked");
				if(CheckDetails==null){
					CheckDetailsTrue = "";
				}
				else{
					CheckDetailsTrue = Li.findElement(By.tagName("input")).getAttribute("checked");
				}
				if(LabelDetails!=""){
					TableMap.put(LabelDetails, CheckDetailsTrue);
				}
			}
			
			String TableLabel = "";
			String TableCheck = "";
			String TableCheckTrue = "";
			WebElement table = driver.findElement(By.xpath("//*[@id=\"trans_type\"]/table/tbody"));
			List<WebElement> TotalRowCount = table.findElements(By.tagName("tr"));
			for(WebElement Row : TotalRowCount){
				List<WebElement> ColumnCount = Row.findElements(By.tagName("td"));
				for(WebElement Col : ColumnCount){
					TableLabel = Col.getText();
					List<WebElement>  inputTag = Col.findElements(By.tagName("input"));
					if(inputTag.size() != 0){
						for(WebElement Tag : inputTag){
							TableCheck = Tag.getAttribute("checked");
							if(TableCheck==null){
								TableCheckTrue = "";
							}
							else{
								TableCheckTrue = Tag.getAttribute("checked");
							}
						}
					}
					if(Col.getText()!=""){
						TableMap.put(TableLabel, TableCheckTrue);
					}
				}
			}
			
			int cnt = 1;
			System.out.println("/-**************************Comparing Values**************************-/");
			for (String Comparekey : ExcelMap.keySet()) {
				if (TableMap.containsKey(Comparekey)) {
			    	System.out.println("######## SS Parameter : "+cnt+". "+Comparekey+" ########");
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
			 
			System.out.println("/-********************************************************************-/");
			
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
	        return objUtils.data1("TestCaseDetails", "Cls_ValidateStatementSending");
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


