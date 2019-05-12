/*
 Author     		:	Madhavi JN
 Class Name			: 	Cls_Validate_EUR_Currency 
 Purpose     		: 	Purpose of this file is :
						1. To Verify the IE seetings Under Country and Currencies tabs
						2. Test Class for the test case QAABE-518.
 
 Date       		:	12/22/2015
 
 PreCondition 		:	1. Role based Login required.
 						
 Test Steps 		:	1. Login using valid role credentials for accessing an account.
                        2. Navigate to Configuration->Billing->Countries tab and check the validations of IE there.
                        3. Navigate to Currencies and check the validations of IE 

 						
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AriaConfiguration;

import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.ConfigurationInvoiceSettings;
import utility.EnvironmentDetails;
import utility.Log;
import utility.VerificationMethods;
import appModules.AriaLoginLogout;

public class Cls_Validate_EUR_Currency extends VerificationMethods{
	
	public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_Validate_EUR_Currency";
	
	@BeforeClass
	 public static void beforeMethod()throws Exception
	 {	
			DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
			//TODO: Set Chrome driver as the driver to launch the test.
			driver = utility.Utils.fn_SetChromeDriverProperties();
			EnvironmentDetails objEnv = new EnvironmentDetails();
			Log.startTestCase("Login to application");
			AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
	 }	
	 
	@Test
	public void ValidateEURCurrency() throws InterruptedException
	{   
		 WebDriverWait webWait;
		 webWait = new WebDriverWait(driver,1000);
		 //Using objects related to configuration
		 ConfigurationInvoiceSettings objConfigure = new ConfigurationInvoiceSettings();
		 //TODO click on configuration Link
		 objConfigure.fn_ConfigurationLink(driver, webWait).click();
		 //TODO click on Billing Link
		 objConfigure.fn_BillingLink(driver, webWait).click();
		 //TODO click on Countries Link
		 objConfigure.fn_CountriesLink(driver, webWait).click();
		 Thread.sleep(1000);
		 try
		 {
		 WebElement CountriesTable = driver.findElement(By.id("DataTables_Table_0"));
		 WebElement CountriesBody = CountriesTable.findElement(By.tagName("tbody"));
		 List<WebElement>CountryRows = CountriesBody.findElements(By.tagName("tr"));
		 l1 : for(WebElement row : CountryRows)
		 {
			 List<WebElement>CountryCols = row.findElements(By.tagName("td"));
			 
			 for (WebElement col :CountryCols)
			 {
				 if ("IE".equalsIgnoreCase(col.getText()))
				 {   
					 Log.info("=======Start of Validations of IE under Countries Tab===========================");
					 //Validating Country Code Under Countries tab
					 if (CountryCols.get(0).getText().equalsIgnoreCase("IE"))			 
		             Log.info("Country Code ::IE is properly configured under Country Tab :: "+CountryCols.get(0).getText());
					 else
					 Log.info("Country Code :: IE is not configured under Country Tab :: "+CountryCols.get(0).getText()); 
					//Validating Country Name Under Countries tab
					 if (CountryCols.get(1).getText().equalsIgnoreCase("Ireland"))
		             Log.info("Country Name :: Ireland is properly configured under Country Tab :: "+CountryCols.get(1).getText());
					 else
					 Log.info("Country Name :: Ireland is not configured under Country Tab :: "+CountryCols.get(1).getText()); 
					 //Validating Plan Set Under Countries tab
					 if (CountryCols.get(2).getText().equalsIgnoreCase("Main Group"))
		             Log.info("Plan Set :: Main Group is properly configured under Country Tab :: "+CountryCols.get(2).getText());
					 else
					 Log.info("Plan Set  :: Main Group is not configured under Country Tab :: "+CountryCols.get(2).getText());
					 //Validating CurrencyUnder Countries tab
					 if (CountryCols.get(3).getText().equalsIgnoreCase("Euro"))
			         Log.info("Currency :: Main Group is properly configured under Country Tab :: "+CountryCols.get(3).getText());
					 else
					 Log.info("Currency  :: Main Group is not configured under Country Tab :: "+CountryCols.get(3).getText()); 
					 Log.info("=======End of Validations of IE under Countries Tab===========================");
					 break l1;
					 }
			   }
		 }	 
		 Thread.sleep(2000);
	     //TODO click on Currencies Link
		 objConfigure.fn_CurrenciesLink(driver, webWait).click();
		 Thread.sleep(5000);

		 WebElement CurrenciesTable = driver.findElement(By.id("DataTables_Table_1"));
		 WebElement CurrenciesBody = CurrenciesTable.findElement(By.tagName("tbody"));
		 List<WebElement>CurrencyRows = CurrenciesBody.findElements(By.tagName("tr"));

		 
		 l1	: for(WebElement row1 : CurrencyRows)
		 {
			 List<WebElement>CurrencyCols = row1.findElements(By.tagName("td"));
			 
			 for (WebElement col :CurrencyCols)
			 {
				 if ("EUR".equalsIgnoreCase(col.getText()))
				 {						 
					 Log.info("=======Start of Validations of IE under Currencies Tab===========================");
					 //TODO :: Validating the Value for Currency
					 if (CurrencyCols.get(1).getText().equalsIgnoreCase("Euro"))
						 Log.info("Name for IE is set to Value Euro ::" +CurrencyCols.get(1).getText());
					 else
						 Log.info("Name for IE is not set to Value Euro ::" +CurrencyCols.get(1).getText());
					 //TODO :: Validating the Value for IsDefault				 
					 if (CurrencyCols.get(2).getText().equalsIgnoreCase("False"))
						 Log.info("IsDefault for IE is set to Value False ::" +CurrencyCols.get(2).getText());
					 else
						 Log.info("IsDefault for IE is not set to Value False ::" +CurrencyCols.get(2).getText());
					 Thread.sleep(2000);
					 col.click();
					 Thread.sleep(5000);
					 String currency = new Select(driver.findElement(By.id("Currency_default_ind"))).getFirstSelectedOption().getText();
					 if (currency.equalsIgnoreCase("No"))
						 Log.info("Default Currency Value for IE is set to NO ::"+currency);	
					 else
					      Log.info("Default Currency Value for IE is not set to NO ::"+currency);
                     //TODO:: check the Accepted Processors settings
					 objConfigure.fn_AcceptedProcessorsTab(driver, webWait).click();
					 Thread.sleep(1000);
					 String Processortext = driver.findElement(By.id("tabs-2")).getText();
					 if (Processortext.contains("Processors that accept this currency for payment. (Currently configured processors are listed in bold."))
						 Log.info("validation of the message of the Processor Listed in Bold is appeared :: "+Processortext);
					 else
						 Log.info("validation of the message of the Processor Listed in Bold is not appeared :: "+Processortext); 
					 Log.info("=======End of Validations of IE under Currencies Tab===========================");
					 //TODO :: click on close button
					 objConfigure.fn_CloseButton(driver, webWait).click();
					 Log.info("=======End of Validations of IE under Currencies Tab11===========================");
					 Thread.sleep(10000);
					 break l1;
				   }
				
	           }
		  }
		
		 }
		 catch (Exception e)
		 {
			e.printStackTrace();
		 }
	    }	
	
		@AfterClass
		public void afterMethod() throws Exception
		 {
			 //TODO: Logs out of the application & quit the driver
			 AriaLoginLogout.appLogout(driver);
			 driver.quit(); 
		 }
		
   }


	


