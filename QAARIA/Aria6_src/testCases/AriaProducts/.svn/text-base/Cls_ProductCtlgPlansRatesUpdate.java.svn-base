/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_ProductCtlgPlansRatesUpdate  
 Purpose     		: 	Purpose of this file is :
						1. To Update Supplemental Plan Rates and verify if they are retained. (QAABE-193)
						2. Verify the Rates changes done are retained.
						
 Date       		:	06/25/2015
 Modified Date		:	11/25/2015, 11/04/2015, 09/24/2015, 09/11/2015, 08/12/2015, 07/06/2015
 Version Information:	Version 5.0
 
  PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "ProductCtlgUpdateRates" worksheet for excel "TestData.xls".

 Test Steps 		:	1. Login using valid role credentials for updating the Supplemental Plan Rates.
 						2. Navigate to the Products -> Plans.
 						3. Click the Supplemental Plan.
 						4. Navigate to the Rates Tab.
 						5. Update any Rates for this Plan.
 						6. Click Save and save the updates made.
 						7. Navigate back to the Rates tab for Supplemental Plan and verify the changed rates are retained.

 Version Changes 2.0:	1. Introduced page object based Web wait.
						2. Changes to the parameters, class and method names as per the standards.						
 Version Changes 3.0:	1. Using re-usable method fn_GetPlans to identify the plan to be updated.					
 Version Changes 3.1:	1. Updated the logic for revisiting the Supplementary Plans for verifying modified data.
 Version Changes 4.0:	1. Updated the reusable modules functions like AccountFunctions_Plans.
 Version Changes 4.1:	1. Changing the Before Class details to suit the generalisation agreed upon.
 Version Changes 5.0:	1. Modified the TC to suit the new Rates Collapsibles.
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AriaProducts;

import org.apache.log4j.xml.DOMConfigurator;

import atu.ddf.exceptions.DataDrivenFrameworkException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;

import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;
import pageObjects.ProductCatalogRateSheets;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;

public class Cls_ProductCtlgPlansRatesUpdate extends VerificationMethods
{

    public static WebDriver driver;
    public static String strTestCaseName = "Update rates for Supplemental Plan and verify these rates are retained.";
	public static WebDriverWait webWait;
    public static WebElement webElement;
    
    
	@BeforeClass
	 public void beforeMethod()throws Exception
	 {	
		DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
		
		//TODO: Set Chrome driver as the driver to launch the test.
		driver = utility.Utils.fn_SetChromeDriverProperties();
				
		//TODO: initialize the webWait
		webWait = new WebDriverWait(driver, 120);
		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.info("Login to application");
		//This parameters are taken from Exec file and its reference is available in EnvironmentDetails
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
	 }
	    
    
/*    
    Function fn_UpdateSupplementalPlanRates: This is the actual Test NG test for changing the Product Catalog Rate.
*/
    
    @Test(dataProvider = "objectTestData", description = "Update rates for Supplemental Plan and verify these rates are retained.")
	public void fn_UpdateSupplementalPlanRates(String strPlanName, String strNewUSDRAM, String strNewSEKWin2012R2)  throws Exception
	{
    	AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
    	ProductCatalogRateSheets objProdCtlgRates = new ProductCatalogRateSheets(); 
    	
		String strActualUSDRAM = null;	// this variable stores the actual value for the USD RAM s/w.
		String strActualSEKWin2012R2 = null;	// this variable stores the actual value for the SEK Win2012R2 s/w.
		Boolean blnUSDRAM, blnSEKWin2012R2;	// Booleans referred for making judgment to reset the values.
				
		String strXpath = null;
		
		//TODO: Log the beginning of the Test Case.
		Log.startTestCase(strTestCaseName);	
		
		try
		{
			//TODO: Calling the generic function to search for the Plan Name expected.			
			
			String strPlanNumber = objAcctFuncPlans.fn_GetPlans(driver, webWait, strPlanName, strTestCaseName);
			if (strPlanNumber != null)
			{
				strXpath = "//*[contains(@id, '_"+strPlanNumber+"')]";
				webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strXpath))).click();
			}				
			else
				throw new Exception("ERROR: There is no Plan Number returned, which is:  !"+strPlanNumber);
						
			// conditional wait for the Rates page to be displayed.
			webWait.until(ExpectedConditions.elementToBeClickable(ProductCatalogRateSheets.fn_clickRatesTab()));
			Thread.sleep(1000);
			Utils.takeScreenshot(driver, strTestCaseName);
			driver.findElement(ProductCatalogRateSheets.fn_clickRatesTab()).click();
			webWait.until(ExpectedConditions.elementToBeClickable(ProductCatalogRateSheets.fn_clickRatesTab()));
			
			// saving the actual USD RAM software pricing value for comparison.
			strActualUSDRAM = driver.findElement(ProductCatalogRateSheets.fn_getUSDRAM()).getAttribute("value").toString();
			Log.info("The actual value for USD RAM software pricing is: "+strActualUSDRAM);
			// updating the USD RAM software pricing value with preset values. 
			driver.findElement(ProductCatalogRateSheets.fn_getUSDRAM()).clear();
			driver.findElement(ProductCatalogRateSheets.fn_getUSDRAM()).sendKeys(strNewUSDRAM);
			Log.info("The updated value for USD RAM software pricing is: "+strNewUSDRAM);
			Thread.sleep(1000);
			Utils.takeScreenshot(driver, strTestCaseName);
			
			// saving the actual SEK Win2012R2 software pricing value for comparison.
			objProdCtlgRates.fn_clickKronaRates(driver, webWait).click();
			Thread.sleep(2000);
			strActualSEKWin2012R2 = driver.findElement(ProductCatalogRateSheets.fn_getSEKWin2012R2()).getAttribute("value").toString();
			Log.info("The actual value for SEK Win2012R2 software pricing is: "+strActualSEKWin2012R2);
			// updating the SEK Win2012R2 software pricing value with preset values. 
			driver.findElement(ProductCatalogRateSheets.fn_getSEKWin2012R2()).clear();
			driver.findElement(ProductCatalogRateSheets.fn_getSEKWin2012R2()).sendKeys(strNewSEKWin2012R2);
			Log.info("The updated value for SEK Win2012R2 software pricing is: "+strNewSEKWin2012R2);
			Thread.sleep(1000);
			Utils.takeScreenshot(driver, strTestCaseName);
			
			// click the Save button to save the updated changes.
			driver.findElement(ProductCatalogRateSheets.fn_clickSaveButton()).click();
			Thread.sleep(45000);	//requires about 30 seconds to prompt the success message box.
			webWait.until(ExpectedConditions.elementToBeClickable(ProductCatalogRateSheets.fn_clickCloseCompletedMessage()));
			driver.findElement(ProductCatalogRateSheets.fn_clickCloseCompletedMessage()).click();
			Thread.sleep(1000);
			Utils.takeScreenshot(driver, strTestCaseName);

			//TODO: Verification of the rates after the save has been successfully done.
			Log.info("The Active Supplemental Plan is being revisited for verification.");
			webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strXpath))).click();			
			// conditional if loop to wait for the Rates page to be displayed.
			webWait.until(ExpectedConditions.elementToBeClickable(ProductCatalogRateSheets.fn_clickRatesTab()));
			Thread.sleep(1000);
			Utils.takeScreenshot(driver, strTestCaseName);
			driver.findElement(ProductCatalogRateSheets.fn_clickRatesTab()).click();			
			
			// verify if the updated values are retained & then reset them.
			String strReadUSDValue = driver.findElement(ProductCatalogRateSheets.fn_getUSDRAM()).getAttribute("value").toString(); 
			if(strReadUSDValue.contentEquals(strNewUSDRAM))
			{
				Log.info("The Updated Value for USD RAM Software has been retained.");
				
				driver.findElement(ProductCatalogRateSheets.fn_getUSDRAM()).clear();
				driver.findElement(ProductCatalogRateSheets.fn_getUSDRAM()).sendKeys(strActualUSDRAM);
				Log.info("Reseting the value for USD RAM Software after verification.");
				Utils.takeScreenshot(driver, strTestCaseName);

				blnUSDRAM = true;	// will govern if the values have been reset.
			}
			else
			{
				blnUSDRAM = false;
				Utils.takeScreenshot(driver, strTestCaseName);
				// click Product Menu to reset the left nav. 
				driver.findElement(ProductCatalogRateSheets.fn_clickProductsLeftNav()).click();
				assertTrue(false, "The Updated Value for USD RAM Software is not retained.");		
			}
			
			// verify if the updated values are retained & then reset them.
			objProdCtlgRates.fn_clickKronaRates(driver, webWait).click();
			Thread.sleep(2000);
			String strReadSEKValue = driver.findElement(ProductCatalogRateSheets.fn_getSEKWin2012R2()).getAttribute("value").toString();
			if(strReadSEKValue.contentEquals(strNewSEKWin2012R2))
			{
				Log.info("The Updated Value for SEK Win2012R2 Software has been retained.");
				
				driver.findElement(ProductCatalogRateSheets.fn_getSEKWin2012R2()).clear();
				driver.findElement(ProductCatalogRateSheets.fn_getSEKWin2012R2()).sendKeys(strActualSEKWin2012R2);
				Log.info("Reseting the value for SEK Win2012R2 Software after verification.");
				Utils.takeScreenshot(driver, strTestCaseName);

				blnSEKWin2012R2 = true;	// will govern if the values have been reset.
			}
			else
			{
				blnSEKWin2012R2 = false;
				Utils.takeScreenshot(driver, strTestCaseName);
				// click Product Menu to reset the left nav. 
				driver.findElement(ProductCatalogRateSheets.fn_clickProductsLeftNav()).click();
				assertTrue(false, "The Updated Value for SEK Win2012R2 Software is not retained.");				
			}

			// this if decides whether the Save button needs to be clicked after reseting the values.
			if (blnUSDRAM && blnSEKWin2012R2) 
			{
				// click the Save button to save the reset changes.
				driver.findElement(ProductCatalogRateSheets.fn_clickSaveButton()).click();
				Thread.sleep(45000);	//requires about 30 seconds to prompt the success message box.
				webWait.until(ExpectedConditions.elementToBeClickable(ProductCatalogRateSheets.fn_clickCloseCompletedMessage()));
				driver.findElement(ProductCatalogRateSheets.fn_clickCloseCompletedMessage()).click();
				Thread.sleep(1000);
				
				Log.info("The values have been reset...exiting the TC !");

				// click Product Menu to reset the left nav. 
				driver.findElement(ProductCatalogRateSheets.fn_clickProductsLeftNav()).click();
			}
		}
		catch (Exception exception)
		{
			Utils.takeScreenshot(driver, strTestCaseName);
			Log.error("The Rates for Supplemental plan couldn't be changed / verified due to exception: "+exception.toString());
			Reporter.log("ERROR: The Rates for Supplemental plan couldn't be changed / verified due to exception: "+exception.toString());
		}
		catch (AssertionError exception)
		{
			Utils.takeScreenshot(driver, strTestCaseName);
			Log.error("The Rates for Supplemental plan couldn't be changed / verified due to exception: "+exception.toString());
			Reporter.log("ERROR: The Rates for Supplemental plan couldn't be changed / verified due to exception: "+exception.toString());
		}
		
		//TODO: Ending off the TC.
		Log.endTestCase(strTestCaseName);
	}

	
	//Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "ProductCtlgUpdateRates");
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
