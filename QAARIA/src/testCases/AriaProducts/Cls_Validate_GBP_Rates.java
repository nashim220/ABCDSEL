/*
 Author     		:	Abhishek
 Class Name			: 	Cls_Validate_GBP_Rates 
 Purpose     		: 	Purpose of this file is :
						1. To Validate GBP Rates.
						
 Date       		:	04/22/2016
 Version Information:	Version 1.0
 */
package testCases.AriaProducts;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AriaEOM;
import pageObjects.ProductCatalogRateSheets;
import utility.*;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_Validate_GBP_Rates extends VerificationMethods
{

WebElement webElement;
public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_Validate_GBP_Rates";

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
	
		@Test(dataProvider="objectTestData", description="Validate_GBP_Rates")
	    public void ValidateGBPRates(String strAccNum,String strplanName) throws Exception
	    {
	    	
			WebDriverWait webWait;
			webWait = new WebDriverWait(driver,1000);
		    Log.startTestCase("Cls_Validate_GBP_Rates");
		    Log.info("the plan name is "+strplanName);
		    
		    
		   try
			{
			   AriaEOM ObjGBPRates = new AriaEOM();
			   
			   //Clicking on link products
			   ObjGBPRates.fn_clickProducts(driver, webWait).click();
			   Thread.sleep(500);
			   
			   //Clicking on link plans
			   ObjGBPRates.fn_clickProductsPlans(driver, webWait).click();
			   Thread.sleep(15000);
			   
			    //ProductCatalogRateSheets PCRSObj = new ProductCatalogRateSheets(); 
			    //Clicking on the Plans table
			    WebElement Webtable =  driver.findElement(ProductCatalogRateSheets.fn_getSupplementalPlansDataTable(0));
				
			    // Now get all the TR elements from the table
				List<WebElement> TotalRowCount = Webtable.findElements(By.tagName("tr"));
				
				// And iterate over them, getting the cells
				int RowIndex=0;
				Log.info("row size for plans table "+TotalRowCount.size());
				for (WebElement rowElement: TotalRowCount) 
				{
						List<WebElement> TotalColumnCount=rowElement.findElements(By.xpath("td"));					
						int ColumnIndex=0;					
						for(WebElement colElement:TotalColumnCount)
						{
							if (colElement.getText().equals(strplanName))
							{
								colElement.click();
								Log.info("the requested plan name is found : "+strplanName);
							}
						
						ColumnIndex=ColumnIndex+1;
						}
				RowIndex=RowIndex+1;  
				}
				
			 Thread.sleep(30000);
			 
			 	//Click on Rates tab
			 	driver.findElement(ProductCatalogRateSheets.fn_clickRatesTab()).click();
			 	Log.info("Clicked on Rates");
			 	Thread.sleep(30000);	
			 	Log.info("Navigated to rates page");

			 	//TODO: Read the GBP Rates  file for data comparison and verification.
				String strRatesFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
						 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
						 + "TestData.xlsx";
				Log.info("Document Path : "+strRatesFilePath);
				File fileRates = new File(strRatesFilePath);
				
				//TODO : Read Test Excel
				ArrayList<String> arrlistFileContents = ReadFiles.readExcelFileAsArray(fileRates, "GBP_Rates");
				
				Log.info("/-******************Service Names and Rates Retrieved From Excel******************-/");
				
				//Hash Map to hold service name and corresponding rate retrieved from excel
				Map<String,String> ExcelDatamap = new HashMap<String,String>();
				int xcelDataCnt = 0;
				String XcelServiceName = "";
				String XcelRate = "";
				for( String o : arrlistFileContents ) {
					if(xcelDataCnt == 0){
						XcelServiceName = o;
						xcelDataCnt++;
					}
					else{
						XcelRate = o;
						xcelDataCnt = 0;
					}
					if(XcelServiceName != "" && XcelRate != "")
					{
					ExcelDatamap.put(XcelServiceName,XcelRate);
					}
				}
				for(String key : ExcelDatamap.keySet())
				{
					Log.info(key + " : " +ExcelDatamap.get(key)); 
					
				}
						
			int Counter=2;
					         
			//For fetching the table Xpath of GBP Currency
            Log.info("/-*******************************************************************-/");
            
            Log.info("/-*********Service Names and GBP Rates Retrieved From Application**********-/");
            
            //Fetching the Rates table to read the pricing components
            WebElement Webtable1 = driver.findElement(ProductCatalogRateSheets.fn_RatesTable(Counter));
            
			//Iterate through the Rows
			List<WebElement> rows = Webtable1.findElements(By.tagName("tr"));
            String ServiceName = "";
            String GBPRate = "";
            Map<String,String> map = new HashMap<String,String>();
			for(WebElement row: rows)
			{
					//Iterate Through 'td' inside each row
					List<WebElement> cells = row.findElements(By.tagName("td"));
					if(cells.size()	== 1){
						for(WebElement SName : cells){
							if(SName.getAttribute("class").equals("serviceName")){
								ServiceName = SName.getAttribute("innerHTML").trim();
							}
						}
					}
					if(cells.size()	> 1){
						for(WebElement Outercol : cells){
							WebElement Ratecells = Outercol.findElement(By.tagName("input"));
							if(Ratecells.getAttribute("class").equals("ratePerUnit")){
								GBPRate = Ratecells.getAttribute("value");
							}
						}
						
					}
					if(ServiceName != "" && GBPRate !="")
					{	
						map.put(ServiceName,GBPRate);
					}		 
			}
			for(String key : map.keySet())
			{
				Log.info(key + " : " +map.get(key)); 
			
			}
			Log.info("HashMap Compared, GBP Rates match : " + ExcelDatamap.equals(map));
			
			Log.info("/-*********************************************************-/");
			
			}  
			catch (Exception exception)
			{
				Log.error("There is some error in Validation of GBP Rates");
			}
			
		}
		
	    @DataProvider(name = "objectTestData")
	    public Object[][] data() throws DataDrivenFrameworkException
	    {
	        Utils objUtils=new Utils();
	        return objUtils.data1("TestCaseDetails", "Validate_GBP_Rates");
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
