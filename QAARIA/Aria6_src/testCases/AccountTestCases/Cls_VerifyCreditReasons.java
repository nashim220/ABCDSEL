/*
 Author     		:	Namrata Akarte
 Class Name			: 	Cls_VerifyCreditReasons  
 Purpose     		: 	Purpose of this file is :
						1. To validate if the credit reasons are displayed correctly
												
 Date       		:	10/16/2015, 11/06/2015
 Version Information:	Version 2.0
 
 Jire #				:	QAABE-346
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in 'TestCaseDetails' and 'CouponCode' worksheets of "TestData.xlsx" 

 Test Steps 		:	1. Login using valid role credentials.
 						2. Read data from 'CouponCode' worksheet 
 						2. Search any account
 						3. Navigate to Accounts->Payments & Credits->Credit Coupons
 						4. Capture the Credit coupons displayed in the Credit coupons list
 						5. Compare the data captured in step 4 and step 2
 
 
 Version 2.0 		: 	1. Updated chrome driver path
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services 
 						All Rights Reserved 
*/
package testCases.AccountTestCases;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;

import pageObjects.PaymentDetailsFinancialReport;
import utility.EnvironmentDetails;
import utility.Log;
import utility.ReadFiles;
import utility.Utils;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_VerifyCreditReasons {
	public WebDriver driver;
	public WebDriverWait webWait;
	public String strAuthenticationKey;
	
	@BeforeClass
	public void beforeMethod()throws Exception
	{	
		//TODO: Initialize the Logger.
    	DOMConfigurator.configure("log4j.xml");	

    	//TODO: Set Chrome driver as the driver to launch the test.
    	driver = utility.Utils.fn_SetChromeDriverProperties();
		
		//TODO: initialize the webWait
		webWait = new WebDriverWait(driver,5000);
		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		
		Log.startTestCase("Login to application");
		//Logs in to the aria application with the appropriate credentials		 	
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);
	
	}
	
	
	@Test(dataProvider="objectTestData")
	public void fn_VerifyCreditReasons(String strTestCaseName, String strAcctNum)
	{
		//Declare Variables
		String strCreditReason = "";
		List<WebElement> lstCreditReasons;
		int lenExcelData = 0;
		int lenApplData = 0;
		boolean blnTestResult = true;
		List<String> arrLstApplCreditReasons = new ArrayList<>();
		
		//Create Objects
		Cls_ChangeDeleteClientDefinedFieldActns objAcctSearch = new Cls_ChangeDeleteClientDefinedFieldActns();
		PaymentDetailsFinancialReport objPayments = new PaymentDetailsFinancialReport();
		
		
		String strInvoiceSettingsDataFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
				 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
				 + "TestData.xlsx";
		System.out.println("Document Path : "+strInvoiceSettingsDataFilePath);
		File fileInvoiceSettings = new File(strInvoiceSettingsDataFilePath);	
		
		//TODO: Log the Start of the Test Case.
		Log.startTestCase(strTestCaseName);
				
		
		try 
		{
			
			//TODO : Read Test Excel
			ArrayList<String> arrlistFileContents = ReadFiles.readExcelFileAsArray(fileInvoiceSettings, "CouponCodes");
			lenExcelData = arrlistFileContents.size();
			Log.info("Array Size is : " +arrlistFileContents.size());
			
			for (int ele =0; ele<arrlistFileContents.size();ele++){
				Log.info("Excel Value is : " +arrlistFileContents.get(ele));
			}
		
			//TODO : Search Account
			objAcctSearch.AccountSearch(driver, webWait, strAcctNum);
			
			//TODO : Click on Payments and Credits link
			objPayments.fn_clickPaymentsCredits(driver, webWait).click();
			
			Thread.sleep(2000);
			//TODO : Click on Credits Link
			objPayments.fn_clickCredits(driver, webWait).click();		
						
			Thread.sleep(2000);
			//TODO : Click on Create a New Credit Link
			objPayments.fn_clickCreateNewCreditLink(driver, webWait).click();
			
			Thread.sleep(2000);
			
			lstCreditReasons = driver.findElement(By.name("inReasonCode")).findElements(By.tagName("option"));
			lenApplData = lstCreditReasons.size();
			Log.info("Total No of Credit Reasons in the Application dropdown are : "+lstCreditReasons.size());
			
			for (int i = 1; i<lstCreditReasons.size();i++)
			{
				//Read value for the credit reason and store it in the array list
				strCreditReason = lstCreditReasons.get(i).getText().trim();
				arrLstApplCreditReasons.add(strCreditReason);
			}
			
			if (lenExcelData == lenApplData-1)
			{
				Log.info("Application and Test Data credit reason count is same");			
		
				for (int counter = 0; counter<lenExcelData; counter++)
				{
					if(arrLstApplCreditReasons.get(counter).equals(arrlistFileContents.get(counter).trim()))
					{
						Log.info(arrlistFileContents.get(counter) + " is a valid credit reason");
						Log.info("-------------------------------------------------------------");
					}
					
					else
					{
						blnTestResult = false;
						Log.info(arrlistFileContents.get(counter) + " is NOT a valid credit reason");
						Log.info("application Value is : "+arrLstApplCreditReasons.get(counter));
						Log.info("Excel Value is : "+arrlistFileContents.get(counter));
						Log.info("-------------------------------------------------------------");
						Reporter.log("MISMATCH : Data did not mach for Credit Reason :"+arrlistFileContents.get(counter));
					}				
				}
				
			}
			else
			{
				Log.info("Mismatch in the number of credit reasons in application and test data");
				Log.info("-------------------------------------------------------------");
				Reporter.log("MISMATCH : in the number of credit reasons in application and test data");
			}
				
			if (blnTestResult == true)
			{
				Log.info("Test case executed successfully");
			}
			else
			{
				Log.info("Either count of credit reasons did not match or there is the mismach in the contents of credit reason");
				Reporter.log("TEST CASE FAILED :Either count of credit reasons did not match or there is the mismach in the contents of credit reason");
			}
				
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		//TODO: Log the End of the Test Case.
		Log.endTestCase(strTestCaseName);
				
		
	}
	
	//Reading data for the test
	@DataProvider(name = "objectTestData")
	public Object[][] testcasedata() throws DataDrivenFrameworkException
	{
		Utils objUtils=new Utils();
		return objUtils.data("TestCaseDetails", "ValidateCreditReasons");
	}
		
	
	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
	
}
