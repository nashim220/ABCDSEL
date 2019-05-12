
/*
 Author     		:	Namrata Akarte
 Class Name			: 	Cls_ConfigurationInvoiceSettings  
 Purpose     		: 	Purpose of this file is :
						1. To identify the page objects for the Payment Settings in Configuration.
												
 Date       		:	08/14/2015
 Modified By		:	Aashish Bhutkar
 Modified Date		:	09/24/2015
 Version Information:	Version 1.1
 
 
 Jira #				:	QAABE-241,QAABE-242,QAABE-243,QAABE-244
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "TestCaseDetails" 
 						worksheet for excel "TestData.xlsx".
 						Details :
 							QAABE-241 - TestCaseName - "SECustomer_InsideSE"
 							QAABE-242 - TestCaseName - "SECustomer_OutSideSE_InsideEurope"
 							QAABE-243 - TestCaseName - "SECustomer_OutsideEuropee"
 							QAABE-244 - TestCaseName - "SECustomer_withTaxPayerID"
 						3. Mention TaxPayerID as "Yes"/"No"/"NA"
 						4. Load usage on Retroactive Billing date + 15 days 
 						 

 Test Steps 		:	1. Login using valid role credentials
 						2. Search test account
 						3. Navigate to Accounts->Statements and Invoice
 						4. Click on Generate Invoice link
 						5. Generate immediate invoice
 						6. Verify if the taxes are displayed on the invoice
 						
Version Changes 1.1:	1. Changed the return type of Account Search to Boolean from Integer. 						
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/
package testCases.AccountTestCases;

import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.UsagePageObjects;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import utility.VerificationMethods;

public class Cls_GenerateInvoice extends VerificationMethods{
public WebDriver driver;
	
	@BeforeClass
	public void beforeMethod()throws Exception
	 {	
		
		DOMConfigurator.configure("log4j.xml");
		System.setProperty("webdriver.chrome.driver",chromeDriverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.startTestCase("Login to application");
		//Logs in to the aria application with the appropriate credentials		 	
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);
	
	 }
	
	  @Test(dataProvider="objectTestData", description="Generate Invoice - SECustomer_OutsideEurope")
	  public void fn_GenerateInvoice(String strAccNum, String strTaxPayerID) {
	  
		  String strTestCaseName = "Generate Invoice";
		  WebDriverWait webWait;
		  webWait = new WebDriverWait(driver,5000);
		  UsagePageObjects objUsage = new UsagePageObjects(); 
		  
	
		  try {
			//Code to generate invoice
			  //TODO : Search Account
			  Cls_ChangeDeleteClientDefinedFieldActns obj = new Cls_ChangeDeleteClientDefinedFieldActns();
			  Boolean blnIsAcctFound = obj.AccountSearch(driver, webWait, strAccNum);
			  if (blnIsAcctFound == true){			 
			  
				  if (strTaxPayerID.equals("Yes")){
					  
					  //TODO: Click on Tax Payer Info link
					  objUsage.fn_ClickTaxPayerInfmtnlnk(driver, webWait).click();
					  //TODO : Click on Edit button
					  objUsage.fn_EditFields(driver, webWait).click();
					  Thread.sleep(2000);
					  //TODO : Select State Tax
					  objUsage.fn_ClickStateTaxChkbx(driver, webWait).click();
					  Thread.sleep(2000);
					  //TODO : Select Nation Tax
					  objUsage.fn_ClickNationTaxChkbx(driver, webWait).click();
					  //TODO : Click on Save Button
					  objUsage.fn_SaveChanges(driver, webWait).click();
				  }
				 
				  //TODO : Click link Statements and Invoices
				  objUsage.fn_ClickStmtsInvoiceslnk(driver, webWait).click();
				
				  Thread.sleep(5000);
				  //TODO : Click on Invoices link
				  objUsage.fn_ClickInvoiceslnk(driver, webWait).click();
				 
				  //TODO : Click on Generate Invoice link
				  objUsage.fn_ClickGenInvoiceslnk(driver, webWait).click();
				  Thread.sleep(5000);
				  Utils.takeScreenshot(driver, strTestCaseName);		  
				  //TODO : Select Invoice Type to be generated
				  objUsage.fn_ImmediateInvoice(driver, webWait).click();
				  Thread.sleep(5000);
				  //TODO : Click button Generate invoice
				  objUsage.fn_GenerateInvoiceButton(driver, webWait).click();
				  Thread.sleep(5000);
				  
				  //TODO : Verify Tax field
				  WebElement table = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[2]/div[3]/div/div/div[5]/table"));
				
				  WebElement tableInvoice = table.findElement(By.tagName("tbody"));
				  
				  List<WebElement>rows = tableInvoice.findElements(By.tagName("tr"));
				  Thread.sleep(1000);		  
				  for (WebElement row : rows){
					  List<WebElement>cols = row.findElements(By.tagName("td"));
					 			  
					  String strInvoiceNum1 = cols.get(1).getText();
					  driver.findElement(By.partialLinkText(strInvoiceNum1)).click();
					  break;
				  }
				
				  Thread.sleep(5000);
				  
				  WebElement InvoiceTable = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[2]/div[3]/div/div/form/div[1]/table"));
				  
				  WebElement InvoiceBody = InvoiceTable.findElement(By.tagName("tbody"));
				  
				  List<WebElement>InvoiceRows = InvoiceBody.findElements(By.tagName("tr"));
				  			  
				  Boolean isCFlag = false;
				  Boolean isDFlag = false;
				  Boolean isSFlag = false;
				  Utils.takeScreenshot(driver, strTestCaseName);
				  for (WebElement row : InvoiceRows){
					
					  List<WebElement>InvoiceColumns = row.findElements(By.tagName("td"));
					  for (WebElement col : InvoiceColumns){
						  
						 String strCellData =col.getText();
						 if ("State Taxes".equals(strCellData)){						 
							 isSFlag = true;					
						 }
						 
						 if ("City Taxes".equals(strCellData)){					 						
							 isCFlag = true;
						 }
						 
						 if ("District Taxes".equals(strCellData)){						 
							 isDFlag = true;
						 }								 
						 
					  }
					  
					  
				  }
					
				  if (isSFlag == true || isCFlag == true || isDFlag == true){
					 
					  Log.info("Test Case Failed");
					  Log.info("State Tax : "+isSFlag);
					  Log.info("City Tax : "+isCFlag);
					  Log.info("Disctrict Tax : "+isDFlag);
				  }
				  else{
					  
					  Log.info("Test Case Passed");
				  }
			  }
			  else {
				  
				  Log.error("Account "+strAccNum +"is not found");
				  Reporter.log("Account is not found");
			  }
		  }  catch (Exception exception) {
			  Log.error("ERROR : Invoice cannot be generated as "+exception.toString());
			  exception.printStackTrace();
			
		}
			    
	  }
	  
	  @AfterTest
	public void afterMethod() throws Exception
	 {
		 //Logs out of the application
		 AriaLoginLogout.appLogout(driver); 
		 //Quitting the driver
		 driver.quit(); 
	 }
	 
	 //Reading data for the test
	@DataProvider(name = "objectTestData")
	 public Object[][] testcasedata() throws DataDrivenFrameworkException
	 {
	      Utils objUtils=new Utils();
	      return objUtils.data("TestCaseDetails", "SECustomer_OutsideEurope");
	 }	

}
