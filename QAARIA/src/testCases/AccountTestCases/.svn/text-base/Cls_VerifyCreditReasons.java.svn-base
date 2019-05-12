/*
 Author     		:	Namrata Akarte
 Edited				: 	Abhishek
 Class Name			: 	Cls_VerifyCreditReasons  
 Purpose     		: 	Purpose of this file is :
						1. To validate if the credit reasons are displayed correctly
												
 Date       		:	04/18/2016
 Date				:   05/06/2016
 Version Information:	Version 1.0
 
 Jira #				:	QAABE-346/ABE-2071/ABE-2371
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in 'TestCaseDetails' and 'CouponCode' worksheets of "TestData.xlsx" 

 Test Steps 		:	1. Login using valid role credentials.
 						2. Read data from 'CouponCode' worksheet 
 						2. Search any account
 						3. Navigate to Accounts->Payments & Credits->Credit Coupons
 						4. Capture the Credit coupons displayed in the Credit coupons list
 						5. Compare the data captured in step 4 and step 2
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services 
 						All Rights Reserved 
*/
package testCases.AccountTestCases;

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
import org.testng.Reporter;
import org.testng.annotations.*;

import pageObjects.AriaEOM;
import pageObjects.PaymentDetailsFinancialReport;
import utility.EnvironmentDetails;
import utility.Log;
import utility.ReadFiles;
import utility.Utils;
import utility.VerificationMethods;
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
		String strServiceCreditReason = "";
		String strCashCreditReason = "";
		List<WebElement> lstServiceCreditReasons;
		List<WebElement> lstCashCreditReasons;
		int lenExcelServiceData = 0;
		boolean blnIsServiceCreditReasonCodeFound = false;
		boolean blnIsCashCreditReasonCodeFound = false;
		boolean blnIsSCRLoaded = true;
		boolean blnIsCCRLoaded = true;
		boolean blnSCRCount = true;
		boolean blnCCRCount = true;
		List<String> arrLstApplServiceCreditReasons = new ArrayList<>();
		List<String> arrLstApplCashCreditReasons = new ArrayList<>();
		Map<String,String> arrlistFileServiceCreditReasons = new HashMap<String,String>();
		Map<String,String> arrlistFileCashCreditReasons = new HashMap<String,String>();
		
		List<HashMap<String,String>> cellValues = new ArrayList<HashMap<String,String>>();
		
		//Create Objects
		Cls_ChangeDeleteClientDefinedFieldActns objAcctSearch = new Cls_ChangeDeleteClientDefinedFieldActns();
		PaymentDetailsFinancialReport objPayments = new PaymentDetailsFinancialReport();
		AriaEOM objAriaEOM = new AriaEOM();
		
		String strInvoiceSettingsDataFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
				 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
				 + "TestData.xlsx";
		System.out.println("Document Path : "+strInvoiceSettingsDataFilePath);
		File fileInvoiceSettings = new File(strInvoiceSettingsDataFilePath);	
		
		//TODO: Log the Start of the Test Case.
		Log.startTestCase(strTestCaseName);				
		
		try 
		{		
			
			cellValues = ReadFiles.readExcelFile(fileInvoiceSettings, "CouponCodes");
			String ServiceCreditReasonsValue = "";
			String CashCreditReasonsValue = "";
			String ServiceCreditReasonsNumber = "";
			String CashCreditReasonsNumber = "";
			for (int a =0 ; a< cellValues.size();a++){
				HashMap<String, String> tmpData = (HashMap<String, String>)cellValues.get(a);
				Set<String> key = tmpData.keySet();
				Iterator it = key.iterator();				
				while (it.hasNext()){
					String hmKey = (String)it.next();
					String hmVal = (String)tmpData.get(hmKey);
					if (hmKey.equalsIgnoreCase("Number")){
						ServiceCreditReasonsNumber = hmVal;
						CashCreditReasonsNumber = hmVal;//arrlistFileServiceCreditReasons.put(hmVal);
					}
					if (hmKey.equalsIgnoreCase("Code")){
						ServiceCreditReasonsValue = hmVal;
						CashCreditReasonsValue = hmVal;
					}
					if(ServiceCreditReasonsNumber != "" && ServiceCreditReasonsValue != "")
					{
						arrlistFileServiceCreditReasons.put(ServiceCreditReasonsNumber,ServiceCreditReasonsValue);
					}
					if(CashCreditReasonsNumber != "" && CashCreditReasonsValue != "")
					{
						arrlistFileCashCreditReasons.put(CashCreditReasonsNumber,CashCreditReasonsValue);
					}
				}
			}
			
			lenExcelServiceData = arrlistFileServiceCreditReasons.size();
			System.out.println("/-*************ServiceCredit***************-/");
			Set<String> Servicekey = arrlistFileServiceCreditReasons.keySet();
			
			Iterator Serviceit = Servicekey.iterator();				
			while (Serviceit.hasNext()){
			
				String hmKey = (String)Serviceit.next();
				String hmVal = (String)arrlistFileServiceCreditReasons.get(hmKey);
				Log.info("Service Credit Reason : "+ hmVal);
			}
			System.out.println("/-*****************************************-/");
			System.out.println("/-***************CashCredit****************-/");
			Set<String> Cashkey = arrlistFileCashCreditReasons.keySet();
			
			Iterator Cashit = Cashkey.iterator();				
			while (Cashit.hasNext()){
			
				String hmKey = (String)Cashit.next();
				String hmVal = (String)arrlistFileServiceCreditReasons.get(hmKey);
				Log.info("Cash Credit Reason : "+ hmVal);
			}
			System.out.println("/-*****************************************-/");
			
			//TODO : Search Account
			objAcctSearch.AccountSearch(driver, webWait, strAcctNum);
			
			//TODO : Click on Payments and Credits link ---- to be chasnged
			objAriaEOM.fn_clickPaymentsCredits(driver, webWait).click();
			
			Thread.sleep(5000);
			//TODO : Click on Credits Link
			objPayments.fn_clickServiceCredits(driver, webWait).click();		
						
			Thread.sleep(2000);
			//TODO : Click on Create a New button
			objPayments.fn_clickNewBtn(driver, webWait).click();
			
			Thread.sleep(2000);
			//TODO : Read data for Service Credit reasons
			lstServiceCreditReasons = driver.findElement(By.name("inReasonCode")).findElements(By.tagName("option"));
			
			for (int i = 1; i<lstServiceCreditReasons.size();i++)
			{
				strServiceCreditReason = lstServiceCreditReasons.get(i).getText().trim();
				arrLstApplServiceCreditReasons.add(strServiceCreditReason);
			}
			
			Thread.sleep(2000);
			//TODO : Read data for Cash Credit Reasons
			//TODO : Click on Cash Credit link
			objPayments.fn_clickCashCredits(driver, webWait).click();
			
			//TODO : Click on New Button
			objPayments.fn_clickNewBtn(driver, webWait).click();
			
			lstCashCreditReasons = driver.findElement(By.name("inReasonCode")).findElements(By.tagName("option"));
			
			for (int i = 1; i<lstCashCreditReasons.size();i++)
			{
				//Read value for the credit reason and store it in the array list
				strCashCreditReason = lstCashCreditReasons.get(i).getText().trim();
				arrLstApplCashCreditReasons.add(strCashCreditReason);
			}
			
			if (arrlistFileServiceCreditReasons.size() == lstServiceCreditReasons.size()-1)
			{
				Log.info("Application and Test Data Service credit reason count is same");	
				
			}
			else
			{
				blnSCRCount = false;
				Log.info("Mismatch in the number of Service credit reasons in application and test data");
				Log.info("Number of Service Credit Reason Codes displayed in the application is "+ lstServiceCreditReasons.size() 
						+ " against "+arrlistFileServiceCreditReasons.size() +" that in the test data file");
				Log.info("-------------------------------------------------------------");				
				Reporter.log("MISMATCH : in the number of Service credit reasons in application and test data.");				
			}
			
			Log.info("*****************Verifying Service Credit Codes******************");
			
			Set<String> ValidateServicekey = arrlistFileServiceCreditReasons.keySet();
			Iterator ValidateServiceit = ValidateServicekey.iterator();				
			while (ValidateServiceit.hasNext()){
					String hmKey = (String)ValidateServiceit.next();
					String hmVal = (String)arrlistFileServiceCreditReasons.get(hmKey);
							
					loop1: for (int iAppCtrSCR = 0 ; iAppCtrSCR<arrLstApplServiceCreditReasons.size();iAppCtrSCR++){
								if(arrLstApplServiceCreditReasons.get(iAppCtrSCR).equalsIgnoreCase(hmVal.trim())){
									Log.info(hmVal + " is a valid Service credit reason");
									Log.info("-------------------------------------------------------------");
									blnIsServiceCreditReasonCodeFound = true;
									break loop1;
								}
							}
						
							if (blnIsServiceCreditReasonCodeFound == false){
								blnIsSCRLoaded = false;
								Log.info(hmVal + " : This service credit reason code is NOT displayed in the application dropdown");
								Log.info("-------------------------------------------------------------");
								
							}
							
							blnIsServiceCreditReasonCodeFound = false;
								
						}
					
								
						if (arrlistFileCashCreditReasons.size() == lstCashCreditReasons.size()-1)
						{
							Log.info("Application and Test Data Cash credit reason count is same");	
							
						}
						else
						{
							blnCCRCount = false;
							Log.info("Mismatch in the number of Cash credit reasons in application and test data");
							Log.info("Number of Cash Credit Reason Codes displayed in the application is "+ lstCashCreditReasons.size() 
									+ " against "+arrlistFileCashCreditReasons.size() +" that in the test data file");
							
							Log.info("-------------------------------------------------------------");
							Reporter.log("MISMATCH : in the number of Cash credit reasons in application and test data");
						}
			
			Log.info("*****************Verifying Cash Credit Codes******************");
			System.out.println("");
			Set<String> ValidateCashkey = arrlistFileCashCreditReasons.keySet();
			Iterator ValidateCashit = ValidateCashkey.iterator();				
			while (ValidateCashit.hasNext()){
				String hmKey = (String)ValidateCashit.next();
				String hmVal = (String)arrlistFileServiceCreditReasons.get(hmKey);
				loop2: for (int iAppCtrCCR = 0 ; iAppCtrCCR<arrLstApplCashCreditReasons.size();iAppCtrCCR++){
							if(arrLstApplCashCreditReasons.get(iAppCtrCCR).equalsIgnoreCase(hmVal.trim())){
								Log.info(hmVal + " is a valid Cash credit reason");
								Log.info("-------------------------------------------------------------");
								blnIsCashCreditReasonCodeFound = true;
								break loop2;
							}
						}
		
				if (blnIsCashCreditReasonCodeFound == false){
					blnIsCCRLoaded = false;
					Log.info(hmVal + " : This Cash credit reason code is NOT displayed in the application dropdown");
					Log.info("-------------------------------------------------------------");
					
				}
				
				blnIsCashCreditReasonCodeFound = false;
					
			}
				
			
				
			if (blnIsSCRLoaded  == false || blnIsCCRLoaded  == false)
			{
				Log.info("Service Credit Reason Codes AND/OR Cash Credit Reason Codes are not displayed as expected");
				//VerificationMethods.assertTrue(false, "Service Creit Reson Codes AND/OR Cash Credit Reason Codes are not displayed as expected");
				Reporter.log("FAIL : Service Credit Reason Codes AND/OR Cash Credit Reason Codes are not displayed as expected");
			}
			else if(blnSCRCount == false || blnCCRCount == false)
			{
				//VerificationMethods.assertTrue(false, "Either count of service credit reasons or cash credit reason codes did not match.");
				Log.info("Either count of service credit reasons or cash credit reason codes did not match.");
				Reporter.log("FAIL:Either count of service credit reasons or cash credit reason codes did not match.");
			}
			else{
				Log.info("Both Service Credit Reason Codes and Cash Credit Reason Codes are displayed as Expected.");
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
