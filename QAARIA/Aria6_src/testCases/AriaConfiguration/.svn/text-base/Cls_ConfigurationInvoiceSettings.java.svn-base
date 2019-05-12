/*
 Author     		:	Namrata Akarte
 Class Name			: 	Cls_ConfigurationInvoiceSettings  
 Purpose     		: 	Purpose of this file is :
						1. To identify the page objects for the Payment Settings in Configuration.
												
 Date       		:	08/14/2015
 Version Information:	Version 1.0
 
 Jire #				:	QAABE-217
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "InvoiceSettings" 
 						worksheet for excel "TestData.xlsx".

 Test Steps 		:	1. Login using valid role credentials for verifying the Configuration Payment Settings.
 						2. Navigate to the Configuration -> Billing -> Invoice Settings.
 						3. Read the table on the landing page and compare it with the expected data mentioned in the
 						"InvoiceSettings" worksheet for excel "TestData.xls".
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/
package testCases.AriaConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import appModules.SelectBrowser;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;

import pageObjects.ConfigurationInvoiceSettings;
import utility.*;
import appModules.AriaLoginLogout;

public class Cls_ConfigurationInvoiceSettings extends VerificationMethods
{
	public static WebDriver driver;
	public WebDriverWait webWait;
	public String strAuthenticationKey;
	
	@BeforeTest
	public void beforeMethod()throws Exception
	 {	
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
	
	@Test/*(dataProvider="objectTestData", description="Configuration Invoice Settings")*/
	public void fn_ConfigurationInvoiceSettings(){
		
		String strTestCaseName = "Verify Configuration Settings";
		webWait = new WebDriverWait(driver,1000);
		//Read POM ConfigurationInvoiceSettings	
		
		
		ConfigurationInvoiceSettings objConfigurationInvoiceSettings = new ConfigurationInvoiceSettings();
		
		try {
						
			
			//TODO: Read the Payment Settings file for data comparison and verification.
			String strInvoiceSettingsDataFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
					 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
					 + "TestData.xlsx";
			File fileInvoiceSettings = new File(strInvoiceSettingsDataFilePath);
			
			//TODO : Read Test Excel
			ArrayList<String> arrlistFileContents = ReadFiles.readExcelFileAsArray(fileInvoiceSettings, "InvoiceSettings");
					
			//TODO: Click on Configuration link in the left navigation:
			objConfigurationInvoiceSettings.fn_ConfigurationLink(driver, webWait).click();
			
			//TODO: Click on Billing Link in the left navigation:
			objConfigurationInvoiceSettings.fn_BillingLink(driver, webWait).click();
			
			//TODO: Click on Invoice Settings link in the left navigation:
			objConfigurationInvoiceSettings.fn_InvoiceSettingsLink(driver, webWait).click();
			
			Thread.sleep(5000);
			
			//Read Table
			WebElement table = driver.findElement(By.tagName("table"));
			Utils.takeScreenshot(driver, "InvoiceSettings");
			WebElement tableContentsInvoiceSettings = table.findElement(By.tagName("tbody"));
			List<WebElement>rows = tableContentsInvoiceSettings.findElements(By.tagName("tr"));
			
					
			int intTotalRows = rows.size();//To Compute total number of rows in application table
			
			int intCurrentRow =0; // To initialize row index
			int brkFlag = 0; // To initialize flag
			int arrayRow = 0; // to initialize excel row
			for (WebElement row : rows){
				arrayRow++ ;
				
				List<WebElement>columns = row.findElements(By.tagName("td"));//To get collection of columns
				int intTotalColumns = columns.size();	// To get total columns
				int FlagIsSuccess = 0; //To initialize the flag
				
				if (intTotalColumns!=0){
					//TODO : Iterate through array list
					for (int i = intCurrentRow;i<arrlistFileContents.size();i = (i+intTotalColumns)){
							
						Thread.sleep(1000);
							
						String strParam = "";
						
						//Get application table
						for (int col = 0;col<intTotalColumns;col++){
												
							String strExcelData = arrlistFileContents.get(i+col).toString().trim();
							
							if (col == 0){
								strParam = strExcelData; //To get parameter name
							}
							
							String strApplParamName = columns.get(col).getText().toString().trim();				
							if (strExcelData.equalsIgnoreCase(strApplParamName)){
								
								FlagIsSuccess++;
								Log.info("Excel Data : "+strExcelData +" and Application Data :"+strApplParamName+ "Array Row : "+arrayRow+" Array list index : "+(i+col));
							}
							if(col == intTotalColumns-1){
								if (FlagIsSuccess==4){
									//System.out.println("Flag Success : "+FlagIsSuccess+" Parameter : "+strParam);
									Log.info("Data matched for parameter "+strExcelData);
									Log.info("-------------------------------------------------------------------------------------------------------------");
								}
								else{
									
									Log.error("Data mismatch for parameter "+strExcelData);
									Reporter.log("Data mismatch for parameter"+strExcelData);
									Log.info("-------------------------------------------------------------------------------------------------------------");
								}
								FlagIsSuccess = 0;	//Reinitialize for the next iteration
							
								brkFlag = 1;
								break; // Move to next row in Application table breaking inner loop
								
							}
						}
					
						if (brkFlag == 1){
							break; // Move to next row in Application table breaking condition
						}				
							
					}
					
					intCurrentRow = intCurrentRow + intTotalColumns;	//Setting value to arraylist index for beginning next iteration				
						
				}
				
			}				
				
		}
		catch (Exception exception) {
			// TODO Auto-generated catch block
			Log.error("The Verification for Payment Settings couldn't be done due to the exception thrown as: "+exception.toString());
    		Reporter.log("ERROR: The Verification for Payment Settings couldn't be done due to the exception thrown as: "+exception.toString());
    		exception.printStackTrace();
		}
		//TODO: Log the End of the Test Case.
		Log.endTestCase(strTestCaseName);
	}
	
	
	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
	
}
