/*
 Author     		:	Madhavi JN
 Class Name			: 	Cls_SupplPlanAnniversaryChange  
 Purpose     		: 	Purpose of this file is :
						1. To identify the page objects for the Invoice generation for Anniversary Change
												
 Date       		:	24-08-2015
 Version Information:	Version 1.0
 
 Jira #				:	QAABE-168
 
 PreCondition 		:	
 						1. Data to be used should be populated in "TestCaseDetails" 
 						worksheet for excel "TestData.xlsx".
 						Details :
 						1.QAABE-168 - TestCaseName - "SupplPlanAnniversaryChange"
 						2.Retroactive Billing date(One year back date)	
 					    3.Load usage on Retroactive Billing date + 15 days 		
 						4.Assign supplemental plan to the account and edit the plan and update the unitID's to Usage excel
 						make sure that Assignment scope should be->Assign on Anniversary			 

 Test Steps 		:	1. Login using valid role credentials
 						2. Search test account
 						3. Navigate to Accounts->Usage->Capture the Usage Price
 						4. Navigate to Accounts->Statements and Invoice
 						5. Click on Generate Invoice link
 						6. Generate immediate invoice
 						7. Verify if the Usage prices are displayed on the invoice
 						8. Compare the Usage and Invoice prices are matching.
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/
package testCases.AccountTestCases;

import java.util.List;

import appModules.AriaLoginLogout;
import appModules.ClsCreateUsageInvoice;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
import atu.ddf.exceptions.DataDrivenFrameworkException;

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

import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;


import utility.VerificationMethods;
import pageObjects.UsagePageObjects;


public class Cls_SupplPlanAnniversaryChange extends VerificationMethods
{
	
	public WebDriver driver;

    public static String strTestCaseName = "Cls_SupplPlanAnniversaryChange";

    
	@BeforeClass
	 public void beforeMethod()throws Exception
	 {	
		DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
		System.setProperty("webdriver.chrome.driver",chromeDriverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();	// this code for maximizing the size of launched window.
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.startTestCase("Login to application");
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
	 }
	

	@Test(dataProvider = "objectTestData", description = "SupplPlanAnniversaryChange")
	public void fn_InvoiceValidation(String strAccNum,String strFromdate) throws Exception
	{
		WebDriverWait webWait;
		webWait = new WebDriverWait(driver,1000);
	    UsagePageObjects objUsage = new UsagePageObjects();
	    ClsCreateUsageInvoice objusgfnctns = new ClsCreateUsageInvoice();
	    
    	   
     try {
			//Code to generate invoice
			  //TODO : Search Account
			  Cls_ChangeDeleteClientDefinedFieldActns obj = new Cls_ChangeDeleteClientDefinedFieldActns();
			  obj.AccountSearch(driver, webWait, strAccNum);
			  /*   if (isAcctFound ==1)
			     {*/
			    	 //objusgfnctns.EditSupplementalplan(driver, webWait); 
			    	 objusgfnctns.fn_Usage(driver, webWait, strAccNum, strFromdate);
			    	 //To identify the Usage Table and capture the total price
			    	 WebElement Webtable = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[2]/div[3]/div/div/form/span/div/table"));
			    	 List<WebElement> TotalRowCount = Webtable.findElements(By.tagName("tr"));

					  int RowIndex=0;
					  String strUsageprice = "";
					  String strInvoicePrice= "";

						for (WebElement rowElement: TotalRowCount)
						{
								List<WebElement> TotalColumnCount=rowElement.findElements(By.tagName("td"));
						    	 
					    	 for (int ColumnIndex=0;ColumnIndex<TotalColumnCount.size();ColumnIndex++)
					    	 {			    		 
						    		   if (TotalColumnCount.get(ColumnIndex).getText().equals("Total:"))
						    		   {		    			   
						    			  strUsageprice = TotalColumnCount.get(TotalColumnCount.size()-1).getText();				    			  
										  Log.info("Usage Price generated on the application is"+strUsageprice);
						    			  break;
						    		   }
								 }	
		
							}
						 RowIndex=RowIndex+1;  		    	 
			     
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
					  objUsage.fn_GenerateInvoiceBtn(driver, webWait).click();
					  Thread.sleep(5000);
					  //TODO : //To identify the Invoice Table and click on the invoice link
					  WebElement table = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[2]/div[3]/div/div/div[5]/table"));
					
					  WebElement tableInvoice = table.findElement(By.tagName("tbody"));
					  
					  List<WebElement>rows = tableInvoice.findElements(By.tagName("tr"));
					  Thread.sleep(1000);		  
						  for (WebElement row : rows)
						  {
							  List<WebElement>cols = row.findElements(By.tagName("td"));
							 			  
							  String strInvoiceNum1 = cols.get(1).getText();
							  driver.findElement(By.partialLinkText(strInvoiceNum1)).click();
							  break;
						  }
					
					  Thread.sleep(5000);
					//TODO : //To identify the Invoice Table and capture the Invoice price
					  WebElement InvoiceTable = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[2]/div[3]/div/div/form/div[1]/table"));
					  
					  WebElement InvoiceBody = InvoiceTable.findElement(By.tagName("tbody"));
					  
					  List<WebElement>InvoiceRows = InvoiceBody.findElements(By.tagName("tr"));
		
					  Utils.takeScreenshot(driver, strTestCaseName);
						  for (WebElement row : InvoiceRows)
						  {
							 List<WebElement>InvoiceColumns = row.findElements(By.tagName("td"));
		
							  for (WebElement col : InvoiceColumns)
							  {
								 if (col.getText().equals("TOTAL:"))
								 {								 
									  strInvoicePrice = InvoiceColumns.get(InvoiceColumns.size()-1).getText();	
									  Log.info("Invoice Price generated on the application is"+strInvoicePrice);
									  break;
								  }					 				 				 
							   }				  				  
						   }
						  
                       if (strInvoicePrice.equals(strUsageprice))
                       {
                    	 Log.info("Usage price and invoice prices are matching"); 
                       }
                      else
                       {
                    	  Log.info("Usage price and invoice prices are not matching"); 
                    	}

			//  }
				  

			  } catch (Exception exception) {
				  Log.error("ERROR : Invoice cannot be generated as "+exception.toString());
				  exception.printStackTrace();}
	  } 

    //Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "SupplPlanAnniversaryChange");
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


