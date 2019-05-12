/*
 Author     		:	Namrata Akarte
 Class Name			: 	Cls_MappingTaxGrpToTaxCategory  
 Purpose     		: 	Purpose of this file is :
						1. To identify mapping for Tax Group to Tax Category
												
 Date       		:	08/26/2015
 Version Information:	Version 1.0
 
 Jira #				:	QAABE-268
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "TestCaseDetails" and "Mapping_TaxGrpToTaxCategory"
 						worksheets for excel "TestData.xlsx" 
 									 

 Test Steps 		:	1. Login using valid role credentials
 						2. Navigate to Products->Service
 						4. Enter Service Number in the search field
 						5. Click on the service number
 						6. Verify Client Defined identifier, usage type and tax group
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/
package testCases.AriaProducts;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import pageObjects.ConfigurationInvoiceSettings;
import pageObjects.ProductsServices;
import utility.*;

public class Cls_MappingTaxGrpToTaxCategory extends VerificationMethods{
	
	public WebDriver driver;
	public WebDriverWait webWait;
	public String strAuthenticationKey;
	
	@BeforeClass
	public void beforeMethod()throws Exception
	 {	
		//TODO: Initialize the Logger.
    	DOMConfigurator.configure("log4j.xml");	

		//TODO : Set Chrome Driver
		System.setProperty("webdriver.chrome.driver",chromeDriverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.startTestCase("Login to application");
		//Logs in to the aria application with the appropriate credentials		 	
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);
	
	 }
	
	@Test/*(dataProvider="objectTestData", description="Tax Group to Tax Category mapping verification")*/
	public void Cls_ConfigurationInvoiceSettings(){
		
		String strTestCaseName = "Tax Group to Tax Category mapping verification";
		
		int intFlag = 0; 
		
		webWait = new WebDriverWait(driver,1000);
		//TODO: Log the End of the Test Case.
		Log.startTestCase(strTestCaseName);
		ProductsServices objProductsServices = new ProductsServices();
		try {
			Thread.sleep(1000);
			
			//TODO : Click on Products link
			//driver.findElement(By.partialLinkText("Products")).click();
			objProductsServices.fn_ProductsLink(driver, webWait).click();
			
			//TODO : Click on Services link
			//driver.findElement(By.partialLinkText("Services")).click();
			objProductsServices.fn_ServicesLink(driver, webWait).click();
			
			//TODO: Read the Payment Settings file for data comparison and verification.
			String strInvoiceSettingsDataFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
					 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
					 + "TestData.xlsx";
			System.out.println("Document Path : "+strInvoiceSettingsDataFilePath);
			File fileInvoiceSettings = new File(strInvoiceSettingsDataFilePath);
			
			//TODO : Read Test Excel
			ArrayList<String> arrlistFileContents = ReadFiles.readExcelFileAsArray(fileInvoiceSettings, "Mapping_TaxGrpToTaxCategory");
		
			int intRowtraverser = 0;
			for (int intCnt = 0 ; intCnt < arrlistFileContents.size(); intCnt+=4){
				
				intRowtraverser = intCnt;
				Thread.sleep(1000);
				String strServiceNum = arrlistFileContents.get(intCnt);
				Log.info("Service Number is : "+strServiceNum);
				
				if (objProductsServices.fn_SearchField(driver, webWait).isEnabled() && !strServiceNum.equals(" ")){	
					//TODO : Enter the value in Edit Field
					objProductsServices.fn_SearchField(driver, webWait).clear();
					objProductsServices.fn_SearchField(driver, webWait).sendKeys(strServiceNum);
					objProductsServices.fn_SearchField(driver, webWait).sendKeys(Keys.TAB);
					
					Thread.sleep(2000);
					//System.out.println("Size : "+driver.findElements(By.cssSelector("#serviceNumberIndex_row_"+strServiceNum+" > td")).size());
					
					WebElement table = driver.findElement(By.tagName("table"));					
					WebElement tableContents = table.findElement(By.tagName("tbody"));
					//List<WebElement>rows = tableContents.findElements(By.tagName("tr"));
					List<WebElement>columns = tableContents.findElements(By.tagName("td"));
					System.out.println("Columns : "+columns.size());
					if (columns.size() > 1){//check if the entry exist
//						
						//TODO : Click on Service Number link					
						driver.findElement(By.cssSelector("#serviceNumberIndex_row_"+strServiceNum+" > td")).click();
						
						//if (arrlistFileContents.get(intRowtraverser+3)!=" " ){
						if (driver.findElement(By.id("Services_client_service_id")).isDisplayed() ){	
							
							//TODO : Read Value from Client Defined Identifier Field
							String strCleintDfndIdentifier = driver.findElement(By.id("Services_client_service_id")).getAttribute("value").trim();
							//String strCleintDfndIdentifier = objProductsServices.fn_SearchField(driver, webWait).getAttribute("value").trim();
							if (arrlistFileContents.get(intRowtraverser+3).trim().equalsIgnoreCase(strCleintDfndIdentifier)){
								Log.info("Match Found for strCleintDfndIdentifier : "+strCleintDfndIdentifier);
								Log.info("Application ClientDefined Identifier : "+strCleintDfndIdentifier);
								Log.info("Excel Data for Client Defined Identifier : "+arrlistFileContents.get(intRowtraverser+3));
								Log.info("-----------------------------------------------------------------------------------");
							}
							else{
								System.out.println("Mismatch for strCleintDfndIdentifier : "+strCleintDfndIdentifier);
								Log.info("Match Not Found for strCleintDfndIdentifier : "+strCleintDfndIdentifier);
								Log.info("Application ClientDefined Identifier : "+strCleintDfndIdentifier);
								Log.info("Excel Data for Client Defined Identifier : "+arrlistFileContents.get(intRowtraverser+3));
								Log.info("-----------------------------------------------------------------------------------");
								intFlag++;
							}
						
						}
						else{
							
							Log.info("Service Number is not displayed");
						}
						
						//if (arrlistFileContents.get(intRowtraverser+1)!=" "){
						if (driver.findElement(By.id("Services_tax_groups")).isDisplayed()){
							//TODO : Read Value from Tax Group Field
							String strTaxGroup = new Select(objProductsServices.fn_TaxGroupList(driver, webWait)).getFirstSelectedOption().getText().trim();
							
							
							if (arrlistFileContents.get(intRowtraverser+1).trim().equalsIgnoreCase(strTaxGroup)){
								
								Log.info("Match Found for strCleintDfndIdentifier : "+strTaxGroup);
								Log.info("Application ClientDefined Identifier : "+strTaxGroup);
								Log.info("Excel Data for Client Defined Identifier : "+arrlistFileContents.get(intRowtraverser+1));
								Log.info("-----------------------------------------------------------------------------------");
							}
							else{
								System.out.println("Mismatch for strTaxGroup : "+strTaxGroup);
								
								Log.info("Match Not Found for strCleintDfndIdentifier : "+strTaxGroup);
								Log.info("Application ClientDefined Identifier : "+strTaxGroup);
								Log.info("Excel Data for Client Defined Identifier : "+arrlistFileContents.get(intRowtraverser+1));
								Log.info("-----------------------------------------------------------------------------------");
								intFlag++;
							}
						}
						else{
							
							Log.info("Tax Group is not displayed");
						}
						
						//if (arrlistFileContents.get(intRowtraverser+2)!=" "){
						if (driver.findElement(By.id("Services_usage_type")).isDisplayed()){
							//TODO : Read Value from Usage Type Field
							String strUsageType = new Select(objProductsServices.fn_UsageTypeList(driver, webWait)).getFirstSelectedOption().getText().trim();
							
							if (arrlistFileContents.get(intRowtraverser+2).trim().equalsIgnoreCase(strUsageType)){
								Log.info("Match Found for strCleintDfndIdentifier : "+strUsageType);
								Log.info("Application ClientDefined Identifier : "+strUsageType);
								Log.info("Excel Data for Client Defined Identifier : "+arrlistFileContents.get(intRowtraverser+2));
								Log.info("-----------------------------------------------------------------------------------");
							}
							else{
								System.out.println("Mismatch for strUsageType : "+strUsageType);
								Log.info("Match Not Found for strCleintDfndIdentifier : "+strUsageType);
								Log.info("Application ClientDefined Identifier : "+strUsageType);
								Log.info("Excel Data for Client Defined Identifier : "+arrlistFileContents.get(intRowtraverser+2));
								Log.info("-----------------------------------------------------------------------------------");
								intFlag++;
							}
						}
						else{
							
							Log.info("Usage Type is not displayed");
						}
						//TODO : Click on Services Link
						driver.findElement(By.partialLinkText("Services")).click();
					
					}
				}
				else{
					Log.info("Service Number entry is blank ");
				}
				
				Log.info("------------------------------------------------------------------------------------------");
					
			}
			
			if (intFlag == 0){
				Log.info("Test Passed : Tax Group to Tax Category mapping is correct");
				Reporter.log("Test Passed : Tax Group to Tax Category mapping is correct");
				
			}
			else {
				Log.info("Test Failed : Tax Group to Tax Category mapping is not correct");
				Reporter.log("Test Failed : Tax Group to Tax Category mapping is not correct");
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();/// for sleep
		} catch (FileNotFoundException exception) {
			// TODO Auto-generated catch block///Base file not found
			Log.error("ERROR : Test Data File cannot be acccessed as: "+exception.toString());
    		Reporter.log("ERROR : Test Data File cannot be acccessed as: "+exception.toString());
    			
			exception.printStackTrace();
		}	
		
		//TODO: Log the End of the Test Case.
		Log.endTestCase(strTestCaseName);
	}
	
	 //Reading data for the test
		@DataProvider(name = "objectTestData")
		 public Object[][] testcasedata() throws DataDrivenFrameworkException
		 {
		      Utils objUtils=new Utils();
		      return objUtils.data("TestCaseDetails", "TaxGrouptoTaxCategoryMapping");
		 }
	
	
/*	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }*/
	
 
}

