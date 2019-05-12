/*
 Author     		:	Madhavi JN
 Class Name			: 	Cls_Validate_EUR_Currency 
 Purpose     		: 	Purpose of this file is :
						1. To Validate VAT tax settings for Ireland
						2. Test Class for the test case QAABE-526.
 
 Date       		:	12/29/2015
 

 PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "VAT_Tax_Settings_For_Ireland" column for excel "TestData.xls".
 						
 Test Steps 		:	1. Login using valid role credentials for accessing an account.
                        2. Go to Configuration -Billing- Taxation settings and validate VAT tax settings for Ireland


 						
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/
package testCases.AriaConfiguration;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ConfigurationInvoiceSettings;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_VAT_Tax_Settings_For_Ireland extends VerificationMethods{
	
	public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_VAT_Tax_Settings_For_Ireland";
	
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
	 

	@Test(dataProvider="objTestData",description="VATTaxSettingsForIreland")
	public void VAT_Tax_Settings_For_Ireland(String status,String SetNameDesc,String Description,String Jurisdiction,String IrelandTaxSettings,String Rate) throws InterruptedException
	{ 
		
		 WebDriverWait webWait;
		 webWait = new WebDriverWait(driver,1000);
		 //Using objects related to configuration
		 ConfigurationInvoiceSettings objConfigure = new ConfigurationInvoiceSettings();
		 
		try
		{  
		    //TODO:: click on configuration Link
			objConfigure.fn_ConfigurationLink(driver, webWait).click();
			//TODO :: click on Integration Link
			objConfigure.fn_IntegrationLink(driver, webWait).click();
			//TODO:: click on Taxation Configuration Link
			objConfigure.fn_TaxationConfigurationLink(driver, webWait).click();
			Thread.sleep(1000);
			String text =driver.findElement(By.xpath("//tr[@id='planGroupsIndex_row_50']/td[3]")).getText();
		    if (text.equalsIgnoreCase("Aria Internal Engine"))
		    	Log.info("the Set description of Tax configuration for Ireland appears :: "+text);
		    else
		        Log.info("the Set description of Tax configuration for Ireland appears :: "+text);
			//TODO:: click on Taxation Configuration Link
		    driver.findElement(By.xpath("//tr[@id='planGroupsIndex_row_50']/td[3]")).click();
		    Thread.sleep(3000);
		    //TODO:: Capture the status of the setting
			 String Status = new Select(driver.findElement(By.id("TaxSet_is_active_ind"))).getFirstSelectedOption().getText();
			 if (Status.equalsIgnoreCase(status))
				 Log.info("Status Value for IE is set to Active under Details tab::"+Status);	
			 else
			      Log.info("Status Value for IE is not set to Active under Details tab::"+Status);
		    //TODO:: Capture the SetName of the setting
			 String SetName = driver.findElement(By.id("TaxSet_set_name")).getAttribute("value");
			 if (SetName.equalsIgnoreCase(SetNameDesc))
				 Log.info("SetName Value for IE is set to Aria Internal Engine under Details tab::"+SetName);	
			 else
			      Log.info("SetName Value for IE is not set to Aria Internal Engine under Details tab::"+SetName);	
		    //TODO:: Capture the SetDescription of the setting
			 String SetDescription = driver.findElement(By.id("TaxSet_set_description")).getAttribute("value");
			 if (SetDescription.equalsIgnoreCase(SetNameDesc))
				 Log.info("SetDescription Value for IE is set to Aria Internal Engine under Details tab::"+SetDescription);	
			 else
			      Log.info("SetDescription Value for IE is not set to Aria Internal Engine under Details tab::"+SetDescription);	
			 //TODO:: click on InternalValues Tab
			 objConfigure.fn_InternalValuesLink(driver, webWait).click();
			 Thread.sleep(1000);
			 //TODO:: click on Tax Group Header
			 driver.findElement(By.id("tax_group_header")).click();
			 Thread.sleep(3000);
		    //TODO:: Capture the Description of IrelandVat Configuration Under Internal Values Tab
			 String VatDescription = driver.findElement(By.id("tax_group_header")).getText();
			 if (VatDescription.equalsIgnoreCase(Description))
				 Log.info("VatDescription Value for IrelandVat is set to IrelandVATConf under InternalValues tab::"+VatDescription);	
			 else
			     Log.info("VatDescription Value for IrelandVat is not set to Jurisdiction under Details tab::"+VatDescription);
		    //TODO:: Capture the Jurisdiction Value of the IrelandVat Configuration
			 String jurisdiction = new Select(driver.findElement(By.id("TaxSet_TaxNames_0_jurisdictionId"))).getFirstSelectedOption().getText();
			 if (jurisdiction.equalsIgnoreCase(Jurisdiction))
				 Log.info("Status Value for IE is set to Active under Details tab::"+Jurisdiction);	
			 else
			      Log.info("Status Value for IE is not set to Active under Details tab::"+Jurisdiction);
			//TODO:: Capture the Value for Calculate Tax After Credits Applied 
			 boolean CalcTax = driver.findElement(By.id("TaxSet_TaxNames_0_creditReduces_1")).isSelected();
			 if (CalcTax==true)
				 Log.info("Value for Calculate Tax After Credits Applied is True::"+CalcTax);	
			 else
			      Log.info("Value for Calculate Tax After Credits Applied is not True::"+CalcTax);
			 //TODO :: capture the tax information from the table and validate the settings
			 //Validation of VAT Tax settings
			 String strTax = driver.findElement(By.id("ms-TaxSet_TaxNames_0_TaxForServiceGroupss_0_taxGroupNos")).getText();
			 String[] arrTaxSettings = IrelandTaxSettings.split("#");
			 for(int i=0;i<arrTaxSettings.length;i++)
			 {
				 if (strTax.contains(arrTaxSettings[i]))
					 Log.info("Tax settings for this product are displayed under the Ireland Tax settings :: "+arrTaxSettings[i]);
				 else
					 Log.info("Tax settings for this product are not displayed under the Ireland Tax settings :: "+arrTaxSettings[i]);
			 }

			 //TODO:: click on Close button
			 driver.findElement(By.id("test-close")).click();
			 Thread.sleep(4000);
			 driver.findElement(By.xpath("/html/body/div[5]/div[3]/div/button[2]/span")).click();
			 Thread.sleep(1000);
		}   
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
    @DataProvider(name="objTestData")
    public Object[][] data() throws DataDrivenFrameworkException 
    {
    	Utils objutils = new Utils();
    	return objutils.data1("TestCaseDetails", "VATTaxSettingsForIreland");
    	
    }
	
	@AfterClass
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
}
