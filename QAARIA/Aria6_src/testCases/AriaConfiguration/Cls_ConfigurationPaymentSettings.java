/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_ConfigurationPaymentSettings  
 Purpose     		: 	Purpose of this file is :
						1. To identify the page objects for the Payment Settings in Configuration.
												
 Date       		:	06/27/2015
 Modified Date		:	11/04/2015
 Version Information:	Version 1.1
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in the "TestCaseDetails" & "PaymentSettings" 
 						worksheet for excel "TestData.xls".

 Test Steps 		:	1. Login using valid role credentials for verifying the Configuration Payment Settings.
 						2. Navigate to the Configuration -> Payments -> Payments Settings.
 						3. Read the table on the landing page and compare it with the expected data mentioned in the
 						"PaymentSettings" worksheet for excel "TestData.xls".
 						
 Version Changes 1.1:	1. Changing the Before Class details to suit the generalisation agreed upon. 						
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AriaConfiguration;

import org.apache.log4j.xml.DOMConfigurator;

import atu.ddf.exceptions.DataDrivenFrameworkException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;
import utility.ReadFiles;
import appModules.AriaLoginLogout;
import pageObjects.ConfigurationPaymentSettings;

public class Cls_ConfigurationPaymentSettings extends VerificationMethods
{
	public static WebDriver driver;
    public static String strTestCaseName = "Verify Payment Settings in Configuration.";
	public static WebDriverWait webWait;
    public static WebElement webElement;
    
    @BeforeClass
    public void beforeMethod()throws Exception
    {	
		DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
		
		//TODO: Set Chrome driver as the driver to launch the test.
		driver = utility.Utils.fn_SetChromeDriverProperties();
				
		//TODO: initialize the webWait
		webWait = new WebDriverWait(driver, 60);
		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.info("Login to application");
		//This parameters are taken from Exec file and its reference is available in EnvironmentDetails
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);		
    }
    
    
/*    
    Function fn_VerifyPaymentSettings: This is the actual Test NG test for Verifying the Payment Settings Values.
*/
    @Test(dataProvider = "objectTestData", description = "Verify Payment Settings in Configuration.")
    public void fn_VerifyPaymentSettings (String strdFileName, String strdWorksheet) throws Exception
    {
    	// TODO: Create an object for the page object class ConfigurationPaymentSettings 
    	ConfigurationPaymentSettings objPaymentSettings = new ConfigurationPaymentSettings();
    	
		//TODO: Read the Payment Settings data table from the Payment Settings landing page.
    	WebElement tblPaymentSettings;
    	List<WebElement> lstwebPaymentSettingsRows;
    	Integer intDataMatched = 0;	// variable to read the times data in a row has matched.    	
    	Integer intColumnLength = 0;	// variable to store the Actual Column length of Data for Payment Settings Page.
    	
    	Integer intCountErrors = 0;	// this variable governs the Assertion call to decide the state of the TC.
    	
    	//TODO: Log the beginning of the Test Case.
    	Log.startTestCase(strTestCaseName);
    	
    	try
    	{
    		//TODO: Read the Payment Settings file for data comparison and verification.
			String strPaymentSettingsDataExcelFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
					 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
					 + strdFileName;
			File defaultPaymentSettings = new File(strPaymentSettingsDataExcelFilePath);
			ArrayList<String> arrlstFileContents = ReadFiles.readExcelFileAsArray(defaultPaymentSettings, strdWorksheet);
			
			//TODO: Get the array of the Array list for the file contents read.
			Object[] arrFileContents = arrlstFileContents.toArray();

    		//TODO: Navigate to the Configuration --> Payments --> Payments Settings.
    		objPaymentSettings.fn_clickConfiguration(driver, webWait).click();
    		objPaymentSettings.fn_clickPayments(driver, webWait).click();
    		objPaymentSettings.fn_clickPaymentSettings(driver, webWait).click();
    		objPaymentSettings.fn_getPaymentSettingsDataTable(driver, webWait);
    		Utils.takeScreenshot(driver, strTestCaseName);
    		
			tblPaymentSettings = objPaymentSettings.fn_getPaymentSettingsDataTable(driver, webWait).findElement(By.tagName("tbody"));   			
			Utils.takeScreenshot(driver, strTestCaseName);
    		lstwebPaymentSettingsRows = tblPaymentSettings.findElements(By.tagName("tr"));

    		//TODO: This is to traverse through the Actual Payment Settings read and compare with values displayed.
    		for(WebElement rows : lstwebPaymentSettingsRows)
    		{
    			//TODO: Create list of WebElements to read the columns data.
    			List<WebElement> cols = rows.findElements(By.tagName("td"));
    			//TODO: Traverse the entire row read to find the match in the read excel sheet.
    			if(cols.size() != 0)
    			{
    				intColumnLength = cols.size();

    				//TODO: Now Traverse the ArrayList for Excel File Read to find the item from data table.
    				for(Integer i = 0 ; i < arrlstFileContents.size(); i = (i + intColumnLength))
    				{
    					String strArrayContent = arrFileContents[i].toString();
    					String strColsContent = cols.get(0).getAttribute("innerText").toString().trim();
    					
    					//TODO: If the Parameter Name on Payment Settings Page matches the excel data, compare other values.
    					if(strArrayContent.equalsIgnoreCase(strColsContent))
    					{
        					intDataMatched += 1;
        					Log.info("Data is being matched for Parameter Name: "+arrFileContents[i].toString());
    						for(Integer col = 1; col < intColumnLength; col++)
    						{
    	    					strArrayContent = arrFileContents[i+col].toString();
    	    					strColsContent = cols.get(col).getAttribute("innerText").toString().trim();
    	    					
    	    					if(strArrayContent.equalsIgnoreCase(strColsContent))
    	    					{
    	    						intDataMatched += 1;
    	    						Log.info("Data has been matched for value: "+arrFileContents[i+col].toString());    	    						
    	    					}
    	    					else
    	    					{
    	    						Log.error("ERROR: Data didn't match for value: "+arrFileContents[i+col].toString());
    	    						Reporter.log("ERROR: Data didn't match for value: "+arrFileContents[i+col].toString());
    	    						intCountErrors += 1;    	        					
    	    					}
    						}    						
    						
    						if(intDataMatched == 4)
    						{
    							Log.info("The Payment Settings data matched for Parameter Name: "+arrFileContents[i].toString());
    							Log.info("-------------------------------------------------------------------------------------------------------------");
    							intDataMatched = 0;	// Reinitialize it to used for the next instance.
    							break;
    						}
    						else
    						{
    							Log.error("ERROR: The Payment Settings data didn't matched with Excel sheet for Parameter Name: "+arrFileContents[i].toString());
    							Reporter.log("ERROR: The Payment Settings data didn't matched with Excel sheet for Parameter Name: "+arrFileContents[i].toString());
    							Log.info("-------------------------------------------------------------------------------------------------------------");
    							intDataMatched = 0;
    							break;
    						}
    					}
    					else
    					{
    						if ((i / intColumnLength) == ((arrlstFileContents.size() / intColumnLength) - 1))
    						{
    							Log.error("ERROR: The Parameter Name from Excel Sheet isn't listed on Payment Settings page with value: "+strColsContent.toString());
    							Reporter.log("ERROR: The Parameter Name from Excel Sheet isn't listed on Payment Settings page with value: "+strColsContent.toString());
    							Log.info("-------------------------------------------------------------------------------------------------------------");
    							intCountErrors += 1;
    						}
    						intDataMatched = 0;
    					}    					
    				}
    			}
    			else
    			{
					Log.error("The Payment Settings landing page has an empty table without details.");
					Reporter.log("ERROR: The Payment Settings landing page has an empty table without details.");
					throw new Exception("The Payment Settings landing page has an empty table without details.");
    			}    			
    		}
    		
    		//TODO: Based on the # of errors recorded, throw Assertion to Fail the TC.
    		if (intCountErrors != 0)
    			throw new AssertionError();
    	}
    	catch (Exception exception)
    	{
    		Log.error("The Verification for Payment Settings couldn't be done due to the exception thrown as: "+exception.toString());
    		Reporter.log("ERROR: The Verification for Payment Settings couldn't be done due to the exception thrown as: "+exception.toString());
    		throw exception;
    	}
    	catch (AssertionError exception)
    	{
    		Log.error("The Verification for Payment Settings couldn't be done due to the data missmatch with the actual's (excel data).");
    		Reporter.log("ERROR: The Verification for Payment Settings couldn't be done due to the data missmatch with the actual's (excel data).");
    		throw exception;
    	}
    	
    	//TODO: Log the End of the Test Case.
    	Log.endTestCase(strTestCaseName);    	
    }

	
	//TODO: Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "VerifyPaymentSettings");
	}
	
	
	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
}
