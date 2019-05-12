/*
 Author     		:	Namrata Akarte
 Class Name			: 	Cls_MappingTaxGrpToTaxCategory  
 Purpose     		: 	Purpose of this file is :
						1. To identify mapping for Tax Group to Tax Category
												
 Date       		:	04/26/2016
 Modified Date		:	05/04/2016
 Modified By		:	Aashish Bhutkar
 Version Information:	Version 2.0
 
 Jira #				:	QAABE-268
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "TestCaseDetails" and "Mapping_TaxGrpToTaxCategory"
 						worksheets for excel "TestData.xlsx" 
 									 

 Test Steps 		:	1. Login using valid role credentials
						2. Navigate to Configuration-> Integrations-> Tax Groups and read the value of tax group descriptions
 						2. Navigate to Products->Service
 						4. Enter Service Name in the search field and read corresponding service number
 						5. Click on the service number
 						6. Verify Client Defined identifier, usage type, tax group, revenue account GL Code, Account Receivable GL Code
 						
Version Changes 2.0:	Modified the script to handle data in a separate format. 						
 
Copyright Notice   :	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/
package testCases.AriaProducts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import pageObjects.AriaEOM;
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
	
	@Test(dataProvider = "objectTestData", description="Tax Group to Tax Category mapping verification.")
	public void Cls_ConfigurationInvoiceSettings(String strFileName, String strWorksheet, String strTestCaseName) throws Exception{	
		
		Boolean blnTestResult;
		
		int intFlag = 0; 
		
		webWait = new WebDriverWait(driver,1000);
		//TODO: Log the End of the Test Case.
		Log.startTestCase(strTestCaseName);
		ProductsServices objProductsServices = new ProductsServices();
		ArrayList<String>arrLstTaxConfigDesc = new ArrayList<String>();
		AriaEOM objAriaEOM = new AriaEOM();
		try {
			
			Thread.sleep(1000);			
			//TODO : Click on Configuration Link
			objAriaEOM.fn_clickConfiguration(driver, webWait).click();			
			//TODO : Click on Integrations Link
			objProductsServices.fn_IntegrationsLink(driver, webWait).click();			
			//TODO : Click on Tax Service Groups
			objProductsServices.fn_TaxServiceGrpLink(driver, webWait).click();
			Thread.sleep(2000);
			
			//TODO : Get values for Tax Group Descriptions
			WebElement tableTaxConfig = driver.findElement(By.xpath("//*[contains(@id, 'DataTables_Table_')]")).findElement(By.tagName("tbody"));
			Thread.sleep(2000);
			List<WebElement>rowstableTaxConfig = tableTaxConfig.findElements(By.tagName("tr"));
			Thread.sleep(1000);
			for (int rtableTaxConfig = 0; rtableTaxConfig <rowstableTaxConfig.size();rtableTaxConfig++) {
				
				Thread.sleep(1000);
				List<WebElement>colstableTaxConfig = rowstableTaxConfig.get(rtableTaxConfig).findElements(By.tagName("td"));
				
				String strDescription = colstableTaxConfig.get(2).getText();
				arrLstTaxConfigDesc.add(strDescription);
			}
						
			//TODO : Click on Products link
			objAriaEOM.fn_clickProducts(driver, webWait).click();			
			Thread.sleep(1000);						
			//TODO : Click on Services link
			objAriaEOM.fn_clickProductsServices(driver, webWait).click();
			
			//TODO: Read the Payment Settings file for data comparison and verification.
			String strInvoiceSettingsDataFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
					 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
					 + strFileName;
			
			File fileInvoiceSettings = new File(strInvoiceSettingsDataFilePath);
			
			//TODO : Read Test Excel
			ArrayList<String> arrlistFileContents = ReadFiles.readExcelFileAsArray(fileInvoiceSettings, strWorksheet);
			for (int intFileContentsCounter = 0 ; intFileContentsCounter < arrlistFileContents.size(); intFileContentsCounter += 5) {
				
				Thread.sleep(1000);
				objProductsServices.fn_ServicesTable(driver, webWait);
				String strServiceName = arrlistFileContents.get(intFileContentsCounter);
				if (objProductsServices.fn_SearchField(driver, webWait).isEnabled() && !strServiceName.equals(" ")){
					
					//TODO : Enter the value in Edit Field
					objProductsServices.fn_SearchField(driver, webWait).clear();
					objProductsServices.fn_SearchField(driver, webWait).sendKeys(strServiceName);
					objProductsServices.fn_SearchField(driver, webWait).sendKeys(Keys.TAB);
				}			
				Thread.sleep(5000);

				//TODO : To identify Services Table
				WebElement table = objProductsServices.fn_ServicesTable(driver, webWait);
				WebElement tableContents = table.findElement(By.tagName("tbody"));
				List<WebElement>rows = tableContents.findElements(By.tagName("tr"));				
				if(rows.size() >= 2)								
					for (int intRow = 0; intRow < rows.size() ; intRow ++) {
						
						List<WebElement>columns = rows.get(intRow).findElements(By.tagName("td"));
						
						if (columns.size()>1) {
							
							String strTblServiceName = columns.get(2).getAttribute("innerText").toString().trim();
							if(strTblServiceName.equalsIgnoreCase(strServiceName))
								columns.get(2).click();
						}						
					}
				else {
					
					List<WebElement>columns = rows.get(0).findElements(By.tagName("td"));
					columns.get(2).click();
				}
	
				objProductsServices.fn_clickServicesClose(driver, webWait);
				Thread.sleep(1000);
				Utils.takeScreenshot(driver, strTestCaseName);
				Log.info("\n############ Validating Service '"+strServiceName+"' ############");							
				
				String strServiceNumber = objProductsServices.fn_ClickServiceNumber(driver, webWait).getText();						
				Log.info("Service Number : " +strServiceNumber);
				
				//TODO: Validate the Client Defined Identifier String.
				if (driver.findElement(By.id("Services_client_service_id")).isDisplayed()) {	
					
					//TODO : Read Value from Client Defined Identifier Field
					String strClientDfndIdentifier = driver.findElement(By.id("Services_client_service_id")).getAttribute("value").trim();
					
					if (strClientDfndIdentifier.equalsIgnoreCase(arrlistFileContents.get(intFileContentsCounter+2)))
						Log.info("Match Found for Client Defined Identifier : "+strClientDfndIdentifier);
					else{
						
						Log.error("ERROR: Match Not Found for Client Defined Identifier : "+arrlistFileContents.get(intFileContentsCounter));
						Reporter.log("ERROR: Match Not Found for Client Defined Identifier : "+arrlistFileContents.get(intFileContentsCounter));								
						intFlag++;
					}						
				}
				else 
					Log.info("Service Number is not displayed");						
				
				//TODO: Validate the Tax Group Text Value & Codes.						
				if (objProductsServices.fn_TaxableList(driver, webWait).isDisplayed()){
					
					//TODO : Read value from Taxable field
					String strIsTaxable = new Select(objProductsServices.fn_TaxableList(driver, webWait)).getFirstSelectedOption().getText().trim();;
					
					if (strIsTaxable.equalsIgnoreCase("Yes")){
						
						//TODO : Read value from Tax Group Field
						String strTaxGroupText = new Select(objProductsServices.fn_TaxGroupList(driver, webWait)).getFirstSelectedOption().getText().trim();
						String strTaxGrpValue = new Select(objProductsServices.fn_TaxGroupList(driver, webWait)).getFirstSelectedOption().getAttribute("value").toString().trim();
						
						//TODO : To check if the Tax Group is the valid group or not
						Boolean blnIsValidTaxGrp = false;
						lblIsValidTaxGrp : for (int iarrLstTaxConfigDesc = 0; iarrLstTaxConfigDesc < arrLstTaxConfigDesc.size(); iarrLstTaxConfigDesc++){
							if (strTaxGroupText.equalsIgnoreCase(arrLstTaxConfigDesc.get(iarrLstTaxConfigDesc))){
								
								blnIsValidTaxGrp = true;
								break lblIsValidTaxGrp;										
							}																			
						}
						
						if (blnIsValidTaxGrp == true) {
							
							Log.info("Tax Group is a valid Tax Group");
							if(strTaxGrpValue.contentEquals(arrlistFileContents.get(intFileContentsCounter+1)))
								Log.info("The Tax Group Code for the Service Type : "+arrlistFileContents.get(intFileContentsCounter)+" matched !");
							else {
								
								Log.error("ERROR: The Tax Group Code for the Service Type : "+arrlistFileContents.get(intFileContentsCounter)+" mismatched !");
								Reporter.log("ERROR: The Tax Group Code for the Service Type : "+arrlistFileContents.get(intFileContentsCounter)+" mismatched !");
								intFlag++;
							}
						}
						else {
															
							Log.error("ERROR: Tax Group is not a valid Tax Group !");
							Reporter.log("ERROR: Tax Group is not a valid Tax Group !");
							intFlag++;
						}
					}
				}	
				
				//TODO: Validate the Revenue Account GL Field.
				if (driver.findElement(By.id("Services_clientCoaCode")).isDisplayed()){
					//TODO : Read Value from Revenue Account GL Field
					String strRevAcctGL = new Select(objProductsServices.fn_RevAcctGLList(driver, webWait)).getFirstSelectedOption().getText().trim();
												
					if (arrlistFileContents.get(intFileContentsCounter+3).toString().trim().equalsIgnoreCase(strRevAcctGL)){
						
						Log.info("Match Found for Revenue Account GL : "+strRevAcctGL);
						
					}
					else{
														
						Log.error("ERROR: Match Not Found for Revenue Account GL : "+arrlistFileContents.get(intFileContentsCounter+3));
						Reporter.log("ERROR: Match Not Found for Revenue Account GL : "+arrlistFileContents.get(intFileContentsCounter+3));
						intFlag++;
					}
				}
				else{
					
					Log.error("ERROR: Revenue Account GL is not displayed !");
					Reporter.log("ERROR: Revenue Account GL is not displayed !");							
				}
				
				//TODO: Validate the Account Receivables GL field.						
				if (driver.findElement(By.id("Services_ar_gl_cd")).isDisplayed()){
					//TODO : Read Value from Account Receivables GL field
					String strAcctRecGL = new Select(objProductsServices.fn_AcctRecGLList(driver, webWait)).getFirstSelectedOption().getText().trim();
					
					if (arrlistFileContents.get(intFileContentsCounter+4).toString().trim().equalsIgnoreCase(strAcctRecGL)){
						Log.info("Match Found for Account Receivables GL field : "+strAcctRecGL);
					}
					else{
						
						Log.error("ERROR: Match Not Found for Account Receivables GL field : "+arrlistFileContents.get(intFileContentsCounter+4));
						Reporter.log("ERROR: Match Not Found for Account Receivables GL field : "+arrlistFileContents.get(intFileContentsCounter+4));
						intFlag++;
					}
				}
				else							
					Log.info("Account Receivables GL field is not displayed");						
				
				objProductsServices.fn_clickServicesClose(driver, webWait).click();			
			}
			
			if (intFlag == 0)
				blnTestResult = true;
			else 
				blnTestResult = false;
			
			//TODO: Based on the validation results, decide the status of the TC.
			if (blnTestResult == false){
				
				Log.error("ERROR: Tax Group to Tax Category mapping is incorrect! Test fails..");
				Reporter.log("ERROR: Tax Group to Tax Category mapping is incorrect! Test fails..");
				assertTrue(false, "ERROR: Tax Group to Tax Category mapping is incorrect! Test fails..");				
			}
			else				
				Log.info("Test Passed : Tax Group to Tax Category mapping is correct");				
					
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();/// for sleep
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
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
		      return objUtils.data("TestCaseDetails", "TaxGrouptoTaxCategoryMapping");
		 }
	
	
	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
	
 
}

