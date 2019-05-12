/*
 Author     		:	Abhishek
 Class Name			: 	Cls_ValidateSwedishInvoiceExtractVoidedInvoices
 Purpose     		: 	Purpose of this file is :
						1. To Validate Swedish Invoice Extract Voided Invoices.
						
 Date       		:	06/15/2016
 Version Information:	Version 1.0
 */
package testCases.Reports;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CountriesPageObject;
import pageObjects.NotificationSettingsPageObjects;
import pageObjects.VerifyGLLocationSegment;
import testCases.BaseTestCase;
import utility.*;
import appModules.AccountFunctions_Invoices;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_ValidateSwedishInvoiceExtractVoidedInvoices extends VerificationMethods
{

WebElement webElement;
public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_ValidateSwedishInvoiceExtractVoidedInvoices";

		@BeforeClass
		 public static void beforeMethod()throws Exception
		 {	
			DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
			driver = Utils.fn_SetChromeDriverProperties();
			System.setProperty("webdriver.chrome.driver",chromeDriverPath);
			//driver = new ChromeDriver();
			EnvironmentDetails objEnv = new EnvironmentDetails();
			Log.startTestCase("Login to application");
			AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
		 }
	
		
		@Test(dataProvider="objectTestData", description="Cls_ValidateSwedishInvoiceExtractVoidedInvoices")
	    //public void ValidateSwedishInvoiceExtract(String strAccountNum,String strInvoiceType,String OptionType, String strGenerateOption) throws Exception
		public void Cls_ValidateSwedishInvoiceExtractVoidedInvoices(String AccountNum) throws Exception
	    {
	    	
			WebDriverWait webWait;
			webWait = new WebDriverWait(driver,1000);
		    Log.startTestCase(strTestCaseName);
		    Log.info("Validate Swedish Invoice Extract Voided Invoices");
		    Thread.sleep (1000);
		    			
		    Cls_ChangeDeleteClientDefinedFieldActns objFields = new Cls_ChangeDeleteClientDefinedFieldActns();
		    //Reset the test account client defined fields
			objFields.AccountSearch(driver, webWait, AccountNum);
			Thread.sleep(1000);
			webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Statements & Invoices"))).click();
			Thread.sleep(3000);
			driver.findElement(By.linkText("Invoices")).click();
			Thread.sleep(2000);
			WebElement voidtable = driver.findElement(By.id("DataTables_Table_1"));
			List<String> arrayList = new ArrayList<String>();
			List<WebElement> tableRows = voidtable.findElements(By.tagName("tr"));
			for(WebElement rows : tableRows){
				List<WebElement> tableCols = rows.findElements(By.tagName("td"));
				for(WebElement cols : tableCols){
					String colClass = cols.getAttribute("class");
					if(colClass.equalsIgnoreCase("dataCellPinkStrikeNoAlign")){
						String InvoiceNumber = cols.getText();
						arrayList.add(InvoiceNumber);
						break;
					}
				}
			}
			
			System.out.println("########### Voided Invoices in Account : "+AccountNum+"  are : ###########");
			for(int i = 0; i < arrayList.size(); i++) {
				System.out.println((arrayList.get(i)).toString());
			}
			System.out.println("##################################################################");
		    
			//TODO: Generate the Report and then proceed further for Validation.
			Thread.sleep(3000);
			Log.info("Analytics and Reporting");
			webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Analytics and Reporting"))).click();
			Log.info("Click on Reports");
			webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reports"))).click();// Click Reports link
			Thread.sleep (5000);
			Log.info("Click on Run Reports");
			webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Run Reports"))).click();// Click Reports link
			Thread.sleep (5000);
			
			//Select Left Frame
			Log.info("Switch to Left Frame to Select : Swedish Invoice Extract - SGAS A7");
			fn_clickSideBarElement(driver,By.linkText("Swedish Invoice Extract - SGAS A7"));
			Thread.sleep(1000);
			
			//Select Work Area to generate report.
			Log.info("Switch to Report Priview Mode");
			fn_clickReportElement(driver,By.linkText("Switch to Report Preview Mode"));
			Thread.sleep(1000);
				 		
	 		//Click Preview Report
			Log.info("Click on Priview Report");
			driver.findElement(By.xpath("/html/body/form[1]/table[2]/tbody/tr/td/input")).click();
	 		Thread.sleep (5000);
	 		
	 		Log.info("Get Main Window Handle");
	 		String Tab = driver.getWindowHandle();
	 		Log.info("Main tab : "+Tab);
	 			 		
	 		//Click on View Large Report
	 		Log.info("Click on View Large Report");
	 		fn_clickViewReport(driver,By.linkText("Click here to view this large report."));
			Thread.sleep(1000);
			
			//Get the list of window handles
			ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			System.out.println("tabs Size : "+ tabs.size());
						
			//Use the list of window handles to switch between windows
			Log.info("Switch to report tab");
			driver.switchTo().window(tabs.get(1));
			
			
			//Switch to postedReportDisplay frame
			Log.info("Switch to postedReportDisplay frame");
			System.out.println(driver.findElement(By.name("postedReportDisplay")).getAttribute("src"));
			driver.get(driver.findElement(By.name("postedReportDisplay")).getAttribute("src"));
			Thread.sleep(3000);
				
			List<WebElement>tables = driver.findElements(By.tagName("table"));
			System.out.println("Number of Tables : "+tables.size());
			
			//Iterate through Rows to validate Company codes
			Log.info("Iterating through the table rows to get Invoice Number, Component Type and Void Date");
			WebElement table = driver.findElement(By.xpath("/html/body/table[2]"));
			int i = 3;
			String AccountNumXpath1 =  "/html/body/table[2]/tbody/tr[";
			String AccountNumXpath2 =  "]/td[1]";
			String InvoiceNumXpath1 = "/html/body/table[2]/tbody/tr[";
			String InvoiceNumXpath2 = "]/td[8]";
			String ComponentTypeXpath1 = "/html/body/table[2]/tbody/tr[";
			String ComponentTypeXpath2 = "]/td[13]";
			String VoidDateXpath1 =  "/html/body/table[2]/tbody/tr[";
			String VoidDateXpath2 =  "]/td[18]";
			Map<String,String> TableMap = new HashMap<String,String>();
			List<WebElement> Rows = table.findElements(By.tagName("tr"));
			System.out.println("Number of Rows"+Rows.size());
			int k=0;
			for(WebElement r : Rows){
				if(k < Rows.size()-2){
					String AccountNumFinalXpath = AccountNumXpath1+ i +AccountNumXpath2;
					String VoidDateFinalXpath = VoidDateXpath1+ i +VoidDateXpath2;
					String InvoiceNumFinalXpath = InvoiceNumXpath1+ i +InvoiceNumXpath2;
					String ComponentTypeFinalXpath = ComponentTypeXpath1+ i +ComponentTypeXpath2;
					String AccountNumber = driver.findElement(By.xpath(AccountNumFinalXpath)).getText();
					String VoidDate = driver.findElement(By.xpath(VoidDateFinalXpath)).getText();
					String InvoiceNum = driver.findElement(By.xpath(InvoiceNumFinalXpath)).getText();
					String ComponentType = driver.findElement(By.xpath(ComponentTypeFinalXpath)).getText();;
					if(AccountNumber.equalsIgnoreCase(AccountNum)){
						if(VoidDate.length()==0){
							//Do Nothing
						}
						else{
							System.out.println("Invoice "+InvoiceNum+" for Account : "+AccountNumber+" for Component "+ComponentType+" was voided on : "+VoidDate);
						}
					}
					k++;
				}
				else{
					break;
				}
				i++;
			}
			
			//Use the list of window handles to switch between windows
			driver.switchTo().window(tabs.get(0));
			Thread.sleep(1000);
			driver.findElement(By.xpath("/html/body/div[5]/div[1]/button")).click();
			
			Thread.sleep(10000);
		}
		
		public void fn_clickSideBarElement(WebDriver driver, By locator) throws InterruptedException
		{
			driver.switchTo().defaultContent();
			WebElement rootTree = driver.findElement(By.tagName("iframe"));
			driver.switchTo().frame(rootTree);
			WebElement parentFrame = driver.findElement(By.name("work_frm"));
			driver.switchTo().frame(parentFrame);
			driver.switchTo().frame(driver.findElement(By.name("innerModFrame")));
			driver.findElement(By.linkText("Sungard AS")).click();
			Thread.sleep(1000);
			driver.findElement(locator).click();
		}
		
		public void fn_clickReportElement(WebDriver driver, By locator) throws InterruptedException
		{
			driver.switchTo().defaultContent();
			WebElement workAreaParentFrame = driver.findElement(By.tagName("iframe"));
			driver.switchTo().frame(workAreaParentFrame);
			WebElement workAreaChildFrame = driver.findElement(By.name("work_frm"));
			driver.switchTo().frame(workAreaChildFrame);
			WebElement workArea = driver.findElement(By.name("innerWorkArea"));
			driver.switchTo().frame(workArea);
			Thread.sleep(1000);
			driver.findElement(locator).click();
		}
		
		public void fn_clickViewReport(WebDriver driver, By locator) throws InterruptedException
		{
			driver.switchTo().defaultContent();
			WebElement workAreaParentFrame = driver.findElement(By.tagName("iframe"));
			driver.switchTo().frame(workAreaParentFrame);
			WebElement workAreaChildFrame = driver.findElement(By.name("work_frm"));
			driver.switchTo().frame(workAreaChildFrame);
			WebElement workArea = driver.findElement(By.name("innerWorkArea"));
			driver.switchTo().frame(workArea);
			WebElement postedReportDisplay = driver.findElement(By.name("postedReportDisplay"));
			driver.switchTo().frame(postedReportDisplay);
			Thread.sleep(1000);
			driver.findElement(locator).click();
		}
		
	    @DataProvider(name = "objectTestData")
	    public Object[][] data() throws DataDrivenFrameworkException
	    {
	        Utils objUtils=new Utils();
	        return objUtils.data1("TestCaseDetails", "Cls_ValidateSwedishInvoiceExtractVoidedInvoices");
	    }
	    
		
		@AfterClass//@AfterTest
		public void afterMethod() throws Exception
		 {
			 //Logs out of the application
			 AriaLoginLogout.appLogout(driver); 
			 //Quitting the driver
			 driver.quit(); 
		 }
}


