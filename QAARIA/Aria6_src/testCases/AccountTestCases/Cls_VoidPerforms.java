/*
 Author     		:	Namrata Akarte
 Class Name			: 	Cls_VoidPerforms  
 Purpose     		: 	Purpose of this file is :
						 To validate the reports after voids/ credit performs for parent child account
												
 Date       		:	12/04/2015
 Version Information:	Version 1.0
 Version Changes 1.1: 	Added After Class
 
 Jira #				:	QAABE-481
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "TestCaseDetails" 
 						worksheet for excel "TestData.xlsx".
 						3. Parent and Child accounts to be created and updated in the test data sheet.

 Test Steps 		:	1. Find or create an account with existing usage, invoices, & where the parent has made a payment 
						2. Void a payment 
						3. Apply a Service Credit 
						4. Apply a Cash Credit 
						5. Void an Invoice
						6. Validate correct balances reflected after voids/credits  
						7. Run & Validate Payment Reports (Payment Txns by Account & Payment Txns by Date) reflect voided payment				
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/
package testCases.AccountTestCases;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ChangeDeleteClientDefinedFields;
import pageObjects.PaymentDetailsFinancialReport;
import pageObjects.UsagePageObjects;
import pageObjects.VerifyGLLocationSegment;
import utility.EnvironmentDetails;
import utility.Log;
import utility.ReportUtils;
import utility.Utils;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Invoices;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
import atu.ddf.exceptions.DataDrivenFrameworkException;



public class Cls_VoidPerforms {
	
	
	public static WebDriver driver;
    public static String strTestCaseName = "New Account with Parent Child Relation @ Runtime";
    public static String[] arrSearchFields = null;	// this string array will hold the Fields names for the Client Defined Fields.
    public static String strFieldValue = null; 	// this string will hold the subsequent field value for the Client Defined Fields.
	public static WebDriverWait webWait;
    public static WebElement webElement;
    

    
	@BeforeClass
	 public void beforeMethod()throws Exception
	 {	
		DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.		
		
		//TODO: Set Chrome driver as the driver to launch the test.
		driver = utility.Utils.fn_SetChromeDriverProperties();
				
		//TODO: initialize the webWait
		webWait = new WebDriverWait(driver, 240);
		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.info("Login to application");
		//This parameters are taken from Exec file and its reference is available in EnvironmentDetails
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
	 }	
	
	  @Test(dataProvider = "objectTestData1")
	  public void fn_VoidPerforms(String strParentAcctNum,String strChildAcctNum,String strAPIURL, String strFilename, String strPlanName, String strCashCreditAmt, String strServiceCreditAmt) {
		  
		  String strAcctNum = "";
		  String strGeneratedPayment = "";
		  String [] arrTrx = null;
		  String strInvoiceNum = "";
		  String strInvoiceAmt = "";
		  String [] arrInv = null;
		  Boolean blnInvFlag = false;
		  String strTranxDetails = "";
		  String [] reportDetails = new String[2];
		
		  ChangeDeleteClientDefinedFields obj = new ChangeDeleteClientDefinedFields();
		  Cls_ChangeDeleteClientDefinedFieldActns objFields = new Cls_ChangeDeleteClientDefinedFieldActns();
		  AccountFunctions_Invoices    objAccInv = new AccountFunctions_Invoices();
		  VerifyGLLocationSegment objGL = new VerifyGLLocationSegment(); 
		  AccountFunctions objAcct = new AccountFunctions(); 
		  UsagePageObjects objPO = new UsagePageObjects();
		  ReportUtils objReportUtils = new ReportUtils();
		  PaymentDetailsFinancialReport objPayDetails = new PaymentDetailsFinancialReport();
		  try {
			 
			  
			  //Call function to assign child to parent  
			  objAcct.fn_AssignChildToParent(driver, webWait, strParentAcctNum, strChildAcctNum);
			  
			  //driver.findElement(AriaDashboardPageObject.getAnalyticsReporting()).click();
			  strAcctNum = strParentAcctNum+"#"+strChildAcctNum;
			  //Call function to load and generate invoice		  
			  objAccInv.fn_E2EGenerateInvoice(driver, webWait, strAPIURL, strFilename, strAcctNum,"Generate Invoice", "Advanced",strPlanName);
			  objFields.AccountSearch(driver, webWait, strChildAcctNum);
			  
			  driver.findElement(By.linkText("Statements & Invoices")).click();
			  Thread.sleep(2000);
			  //Click on Invoices link
			  objPO.fn_ClickInvoiceslnk(driver, webWait).click();
			  Thread.sleep(2000);
			  WebElement tableInvoices = driver.findElement(By.xpath("//*[contains(@id,'DataTables_Table_')]"));
			  WebElement tbodyInv = tableInvoices.findElement(By.tagName("tbody"));
			  List<WebElement>invoices = tbodyInv.findElements(By.tagName("tr"));
			  for (WebElement invoice : invoices){
				  
				  List<WebElement>invoicecols = invoice.findElements(By.tagName("td"));
				  strInvoiceNum = invoicecols.get(1).getText().toString().trim();
				  strInvoiceAmt = invoicecols.get(5).getText().toString().trim();
				  strInvoiceNum = strInvoiceNum+"#"+strInvoiceAmt;
				  Log.info("strInvoiceNum" + strInvoiceNum);
				  break;
			  }
			  
			 	  
			  //TODO : Generate payment
			  objFields.AccountSearch(driver, webWait, strParentAcctNum);
			  obj.fn_AcctLink(driver, webWait).click();
			  strGeneratedPayment = objAccInv.fn_GeneratePayments(driver, webWait, strParentAcctNum, "123", false, "100", true,"", "Generate Payment for Parent Account");
			  
			  arrTrx = strGeneratedPayment.split("#");
			  //TODO : void payment
			  objAccInv.fn_VoidPayments(driver, webWait, strParentAcctNum, arrTrx[0], "Void Payments");
			
			 
			  //*********** Apply Credits*******************
			  //apply service credit	  
				 
			  objFields.AccountSearch(driver, webWait, strChildAcctNum);
			  obj.fn_AcctLink(driver, webWait).click();
			  objAccInv.fn_ApplyCredit(driver, webWait, strFilename,strServiceCreditAmt, "Addend- Good Faith Credit", "Service");	  
			
			  
			  //apply cash credit			
			  objFields.AccountSearch(driver, webWait, strChildAcctNum);
			  objAccInv.fn_ApplyCredit(driver, webWait, strFilename,strCashCreditAmt, "Addend- Good Faith Credit", "Cash");			  
			  objFields.AccountSearch(driver, webWait, strParentAcctNum);
			  //Click on Statements and Invoice link
			  driver.findElement(By.linkText("Statements & Invoices")).click();
			  driver.findElement(By.linkText("Transactions")).click();
			  
			  int intTotalRows = 0;
			  
			  WebElement table2 = driver.findElement(By.xpath("//*[contains(@id,'DataTables_Table_')]"));
			
			  WebElement tbody2 = table2.findElement(By.tagName("tbody"));
			  List<WebElement>rows2 = tbody2.findElements(By.tagName("tr"));
			  
			  for (WebElement row : rows2){
				  if (intTotalRows==rows2.size()-2){
					  
					  List<WebElement>cols = row.findElements(By.tagName("td"));
					   
					  String strDescription = cols.get(2).getText().toString().trim();
					  Log.info("Transaction Description :"+strDescription);
					  
					  if (strDescription.equalsIgnoreCase("Balance Transfer To Account #"+"strParentAcctNum") && cols.get(5).getText().toString().trim().contains(strCashCreditAmt))
						  Log.info("Cash Credits information is displayed correctly for parent account transaction");
					  else
						  Log.info("Cash Credits information is NOT displayed correctly for parent account transaction");
					  
				  }
				  
				  intTotalRows++;
				 
			  }
			 
			  
			  //*********** void invoices*********** 
			  //Search Account
			  objFields.AccountSearch(driver, webWait, strChildAcctNum);
			  //Click on Accounts link
			  obj.fn_AcctLink(driver, webWait).click();
			  //TODO : Select latest invoice
			  objAccInv.fn_SelectLatestInvoice(driver, webWait);
			  //TODO : Void invoices
			  driver.findElement(By.cssSelector("a[title=\"Void this invoice\"] > span")).click();
			  driver.switchTo().activeElement().sendKeys(Keys.TAB);
			  driver.switchTo().activeElement().sendKeys(Keys.TAB);
			  driver.switchTo().activeElement().sendKeys(Keys.RETURN);
			  Thread.sleep(2000);					
			  driver.switchTo().defaultContent();
			  String strWindowHandle = driver.getWindowHandle();
			  driver.switchTo().window(strWindowHandle);
			  
			  String strVoidStatusMessage = objPayDetails.fn_getPaymentsStatusMessage(driver, webWait).getAttribute("innerText").toString().trim();
			  Log.info("strVoidStatusMessage");
			  if(strVoidStatusMessage.contains("successfully voided"))
			  {
					objPayDetails.fn_getDataTable(driver, webWait);
					Utils.takeScreenshot(driver, strTestCaseName);
				
					Log.info("The voiding process has been successfully completed !");
				
			  }
			  else{
				  Log.info("The voiding process has failed!");
				  Reporter.log("The voiding process has failed !");
			  }
			
			  
			  //TODO : Verify transaction on the child account
			  //Search Account
			
			  obj.fn_AcctLink(driver, webWait).click();	  
			  objFields.AccountSearch(driver, webWait, strChildAcctNum); 
			  
			  //Click on Statements and Invoice link
			  driver.findElement(By.linkText("Statements & Invoices")).click();
			  driver.findElement(By.linkText("Transactions")).click();
			  
			  WebElement table = driver.findElement(By.xpath("//*[contains(@id,'DataTables_Table_')]"));
				
			  //WebElement tableCustDefinedFields = table.findElement(By.xpath("//*[@id=\"DataTables_Table_1\"]"));
			  WebElement tbody = table.findElement(By.tagName("tbody"));
			  List<WebElement>rows = tbody.findElements(By.tagName("tr"));
			  arrInv = strInvoiceNum.split("#");
			  for (WebElement row : rows){
				  List<WebElement>cols = row.findElements(By.tagName("td"));
				  
				  if (cols.get(6).getText().toString().trim().contains(arrInv[1])){
					  String strDescription = cols.get(2).getText().toString().trim();
					  Log.info("Transaction Description :"+strDescription);
					  if (strDescription.contains(arrInv[0])){
						  Log.info("Invoice/ Void invoice details are displayed correctly");
						  blnInvFlag = true;
					  }
					  
				  }	
					  
				 
			  }
			
			  
			  //TODO : Verify transaction on the parent account
			  objFields.AccountSearch(driver, webWait, strParentAcctNum); 
			  
			  //Click on Accounts link
			  //obj.fn_AcctLink(driver, webWait).click();
			  
			  driver.findElement(By.linkText("Statements & Invoices")).click();
			  driver.findElement(By.linkText("Transactions")).click();
			  
			  WebElement tableTxn = driver.findElement(By.xpath("//*[contains(@id,'DataTables_Table_')]"));
				
			  //WebElement tableCustDefinedFields = table.findElement(By.xpath("//*[@id=\"DataTables_Table_1\"]"));
			  WebElement tbodytxn = tableTxn.findElement(By.tagName("tbody"));
			  List<WebElement>rowstxn = tbodytxn.findElements(By.tagName("tr"));
			  
			  Date curDate = new Date();
			  SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
			  String strDate = format.format(curDate);
			  int reportCounter = 0;
			 
			  for (WebElement row : rowstxn){
				 
				  	String strTranxType = "";
				  	List<WebElement>cols = row.findElements(By.tagName("td"));
			  
				  	if (cols.get(2).getText().toString().trim().contains("Check")&&cols.get(1).getText().toString().trim().equals(strDate)){
				 //	if (cols.get(2).getText().toString().trim().contains("Check")&&cols.get(1).getText().toString().trim().equals("03-Dec-2015")){ 
				 		if (cols.get(2).getText().toString().trim().contains("Voided Check"))
						  strTranxType = "Voided Check";					  
				 		else
						  strTranxType = "Check";
					  
				 	String strTransaction = cols.get(0).getText().toString().trim();
					  
					String strTranxChargeAmt = cols.get(4).getText().toString().trim();
					if (!strTranxChargeAmt.equalsIgnoreCase("")){
						strTranxDetails = strTransaction+"#"+strTranxChargeAmt;
					}
					String strTranxCreditAmt = cols.get(5).getText().toString().trim();
					if (!strTranxCreditAmt.equalsIgnoreCase("")){
						strTranxCreditAmt = strTranxCreditAmt.replace("(", "");
						strTranxCreditAmt = strTranxCreditAmt.replace(")", "");
						strTranxDetails = strTransaction+"#"+strTranxCreditAmt;
						  
					 }
					  strTranxDetails = strTranxDetails + "#" +strTranxType;
					  reportDetails[reportCounter] = strTranxDetails;
					  Log.info("Transaction Details :"+reportDetails[reportCounter]);
					  reportCounter++;
				 }	
			  
				
			  }
			  
			  String strParentWindow = driver.getWindowHandle();
			  //Click on Analytics and Reporting
			  objGL.fn_clickAnalyticsReporting(driver, webWait).click();
			  
			  //TODO: Verify the contents of the 'Payment Transactions by Date' Report.
			  objReportUtils.fn_GenerateReport(driver, "Standard", "Payment Transactions by Date", "Current Day", "Current Day");
			  Thread.sleep(2000);
			  Utils.takeScreenshot(driver, strTestCaseName);
			  String strcurrentWindow = driver.getWindowHandle();
			  Log.info("Verifying data for the 'Payment Transactions by Account' Report..");
				
			  for (int ireport =0; ireport < reportDetails.length; ireport++)	{
				  String tempDetails = reportDetails[ireport];
				  String [] tempReport = tempDetails.split("#");
	  				WebElement webReadContents = objReportUtils.fn_ReadGeneratedReport(driver);
	  			
	  			
	  				List<WebElement> rowswebReadContents = webReadContents.findElements(By.tagName("tr"));
	  			
	  				for (WebElement r : rowswebReadContents){
		  				List<WebElement> colswebReadContents = r.findElements(By.tagName("td"));
		  				Log.info("Total Columns : " +colswebReadContents.size());
		  				if (colswebReadContents.size()>2){
		  				
		  	  				String strAriaEventNo = colswebReadContents.get(7).getText().toString().trim();
		  	  				Log.info("Aria event number : "+strAriaEventNo);
		  	  				if (tempReport[0].equalsIgnoreCase(strAriaEventNo)){
		  	  					Log.info("Transaction displayed in the report");
		  	  					if (tempReport[1].equalsIgnoreCase(colswebReadContents.get(5).getText().toString().trim())&&tempReport[2].equalsIgnoreCase(colswebReadContents.get(1).getText().toString().trim())){
		  	  						Log.info("Transaction displayed  with the valid details");
		  	  					}
		  	  					else
		  	  						Log.info("Error : Transaction details displayed are INVALID");
		  	  						Reporter.log("Error : Transaction details displayed are INVALID");
		  	  				}
	  					
		  				}
	  				}				
				
			  }
			  driver.switchTo().window(strcurrentWindow);
			  driver.findElement(By.xpath("//button[@type='button']")).click();
			  driver.switchTo().window(strParentWindow);
			
			
			  //TODO: Verify the contents of the 'Payment Transactions by Date' Report.
			  objGL.fn_clickAnalyticsReporting(driver, webWait).click();
			  Thread.sleep(2000);
			  objReportUtils.fn_GenerateReport(driver, "Standard", "Payment Transactions by Account", "Current Day", "Current Day");
			  Thread.sleep(2000);
			  Utils.takeScreenshot(driver, strTestCaseName);
			
			  Log.info("Verifying data for the 'Payment Transactions by Account' Report..");	
			
			  for (int ireport =0; ireport < reportDetails.length; ireport++)	{
				  String tempDetails = reportDetails[ireport];
				  String [] tempReport = tempDetails.split("#");
	  			  WebElement webReadContents = objReportUtils.fn_ReadGeneratedReport(driver);
	  			
	  			
	  			  List<WebElement> rowswebReadContents = webReadContents.findElements(By.tagName("tr"));
	  			
	  			  for (WebElement r : rowswebReadContents){
	  				  List<WebElement> colswebReadContents = r.findElements(By.tagName("td"));
	  				  Log.info("Total Columns : " +colswebReadContents.size());
	  				  if (colswebReadContents.size()>2){
	  				
	  	  					String strAriaEventNo = colswebReadContents.get(7).getText().toString().trim();
	  	  					Log.info("Aria event number : "+strAriaEventNo);
	  	  					if (tempReport[0].equalsIgnoreCase(strAriaEventNo)){
	  	  						Log.info("Transaction displayed in the report");
	  	  						if (tempReport[1].equalsIgnoreCase(colswebReadContents.get(5).getText().toString().trim())&&tempReport[2].equalsIgnoreCase(colswebReadContents.get(1).getText().toString().trim())){
	  	  						Log.info("Transaction displayed  with the valid details");
	  	  						}
	  	  					else
	  	  						Log.info("Error : Transaction details displayed are INVALID");
	  	  						Reporter.log("Error : Transaction details displayed are INVALID");
	  	  					}
	  					
	  				  }
	  			  }				
				
			  }
			 
			  
			  
		  	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	 
	  
	  }
  
	  //Reading data for the test    
	
	  @DataProvider(name = "objectTestData1")
	  public Object[][] testExcelData1() throws DataDrivenFrameworkException
	  {
		  Utils objUtils=new Utils();
		  return objUtils.data1("TestCaseDetails", "VoidPerforms");
	  }
	  
	  
	  @AfterClass
	  public void afterMethod() throws Exception
	  {
		  //TODO: Logs out of the application & quit the driver
		  AriaLoginLogout.appLogout(driver);
		  driver.quit(); 
	  }
}
