/*
 Author     		:	Abhishek
 Class Name			: 	Cls_Validate_CustomInvoiceStatus
 Purpose     		: 	Purpose of this file is :
						1. To Validate Custom Invoice Status
						
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
import testCases.BaseTestCase;
import utility.*;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_ValidateCustomInvoiceStatus extends VerificationMethods
{

WebElement webElement;
public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_Validate_CustomInvoiceStatus";

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
		@Test(dataProvider="objectTestData", description="Cls_ValidateCustomInvoiceStatus")
	    public void ValidateCustomInvoiceStatus(String strData) throws Exception
	    {
	    	
			WebDriverWait webWait;
			webWait = new WebDriverWait(driver,1000);
		    Log.startTestCase(strTestCaseName);
		    Log.info("Validating Custom Invoice Status");
		    
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
			    objCountries.fn_CustomInvoiceStatus(driver, webWait).click();
			    Thread.sleep(500);
			    
			    //Fetching Data from Excel
			    //System.out.println("Data from Excel : "+strData);
			    String AppData = "";
			    //Fetching data from Application
			    WebElement table = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody"));
			    List<WebElement> TotalRowCount = table.findElements(By.tagName("tr"));
				for(WebElement Row : TotalRowCount){
					// Now get all the TD elements from the Rows
					List<WebElement> ColumnCount = Row.findElements(By.tagName("td"));
					for(WebElement col : ColumnCount){
						if(col.getText().equalsIgnoreCase(strData)){
							System.out.println("Matched : Excel Data => "+strData+", Application Data => "+col.getText());
						}
						else{
							System.out.println("Error : Found Data in Table" +col.getText());
						}
					}
				}
			    
				
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
	        return objUtils.data1("TestCaseDetails", "Cls_ValidateCustomInvoiceStatus");
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
