/*
 Author     		:	Nashim Khan / Aashish BHutkar
 Class Name			: 	fn_ValidateProductFieldCategories  
 Purpose     		: 	Purpose of this file is :
						1. To Capture the data under Product Field Categories  and validate with the Base Documentation.
												
 Date       		:	08/06/2016

 Version Information:	Version 1.0 
 
 Jira ID #			:	QAABE-
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "VerifyProductFieldCategories" 
 					    Excel-- "TestData.xlsx".
 						Worksheet Name-ProductFieldCategories						

 Test Steps 		:	1. Login using valid role credentials for verifying the Configuration Product Field Categories.
 						2. Navigate to the Configuration -> Client settings  -> ProductFieldCategories.
 						3. Read the table on the landing page compare it with the expected data mentioned in the "ProductFieldCategories" worksheet.	
 						
 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AriaConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AriaEOM;
import pageObjects.ConfigurationClientSettings;
import utility.EnvironmentDetails;
import utility.Log;
import utility.ReadFiles;
import utility.Utils;
import utility.VerificationMethods;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_ValidateProductFieldCatagories extends VerificationMethods {
	
	public static WebDriver driver;
	public static WebDriverWait webWait;
	
	public static String strTestCaseName = "Verify Product Field Catagories";

	@BeforeClass
	 public static void beforeMethod()throws Exception  {	
		DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
		
		//TODO: Set Chrome driver as the driver to launch the test.
		driver = utility.Utils.fn_SetChromeDriverProperties();
				
		//TODO: initialize the webWait
		webWait = new WebDriverWait(driver, 60);
		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.info("Login to application");
		//This parameters are taken from Exec file and its reference is available in EnvironmentDetails
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
	 }	
	
	@Test(dataProvider="objTestData",description="VerifyProductFieldCatagories")
	public void fn_ValidateProductFieldCategories(String strFilename ,String strWorksheet) throws Exception {	
		
		Log.startTestCase(strTestCaseName);
		//TODO: Create objects to read data table contents into.
		AriaEOM objAriaEOM = new AriaEOM();
		ConfigurationClientSettings objConfigClientSettings = new ConfigurationClientSettings();
		ReadFiles objReadFiles=new ReadFiles();
	
		Boolean blnDataVerified = true;
		String strValue = "";
		String strValueType = "";
		
		//log the beginning of the test case.
		Log.startTestCase(strTestCaseName);
	    
	    try {
		    	//TODO : Navigate to the Account fields under Configuration menu.
				objAriaEOM.fn_clickConfiguration(driver, webWait).click();
				objAriaEOM.fn_clickClientSetting(driver, webWait).click();
		    	objAriaEOM.fn_clickProductFieldCatagories(driver, webWait).click();
		    	
		    	objAriaEOM.fn_getDataTable(driver, webWait);
		    	WebElement webProdFieldCategoriesDataTable = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
				List<WebElement> lstwebProdFieldCategoriesTblRows = webProdFieldCategoriesDataTable.findElements(By.tagName("tr"));
				String strProdFieldCatValues = "";
				Log.info("Reading Product Field Categories from Aria EOM...");
				List<NameValuePair> lstnvpProdFieldCategoriesWeb = new ArrayList<NameValuePair>();
				//TODO: Read the Account Fields  displayed in ARIA EOM for further validations.
				for(Integer intTable = 0; intTable < lstwebProdFieldCategoriesTblRows.size(); intTable ++) {
					
					//TODO: Click on the Field Category Name to navigate to it's details page.
					objAriaEOM.fn_getDataTable(driver, webWait);
					lstwebProdFieldCategoriesTblRows = webProdFieldCategoriesDataTable.findElements(By.tagName("tr"));
					String strFieldCatName = lstwebProdFieldCategoriesTblRows.get(intTable).findElements(By.tagName("td")).get(0).getText().toString().trim();
					String strCatNameXpath = "//td[text()='"+strFieldCatName+"']";
					driver.findElement(By.xpath(strCatNameXpath)).click();
					objConfigClientSettings.fn_getCategoryName(driver, webWait);
					
					//TODO: now on the Product Field Categories details page, start reading the details.
					String strCategoryName = objConfigClientSettings.fn_getCategoryName(driver, webWait).getAttribute("value");
					if(strCategoryName.equals(""))
						assertTrue(false, "ERROR: There is no Category Name specified...exiting TC with exception !");
					
					//read details.
					strProdFieldCatValues = objConfigClientSettings.fn_getCategoryDescription(driver, webWait).getText();
			    	if(strProdFieldCatValues.equals(""))
			    		strProdFieldCatValues = "NA";
			    	lstnvpProdFieldCategoriesWeb.add(new BasicNameValuePair(strCategoryName, strProdFieldCatValues));
			    	
			    	//read ONLY Selected Product Fields.
			    	List<WebElement> lstwebProductFields = objConfigClientSettings.fn_getProductFieldsSelected(driver, webWait)
							.findElements(By.tagName("li"));
					for(WebElement webList : lstwebProductFields) {
					
						strProdFieldCatValues = "PF_"+webList.getText().toString().trim();
						lstnvpProdFieldCategoriesWeb.add(new BasicNameValuePair(strCategoryName, strProdFieldCatValues));
					}
					
					//read ONLY Selected Plan Instance Fields.
					List<WebElement> lstwebPlanInstanceFields = objConfigClientSettings.fn_getPlanInstanceFieldsSelected(driver, webWait)
							.findElements(By.tagName("li"));
					for(WebElement webList : lstwebPlanInstanceFields) {
					
						strProdFieldCatValues = "PIF_"+webList.getText().toString().trim();
						lstnvpProdFieldCategoriesWeb.add(new BasicNameValuePair(strCategoryName, strProdFieldCatValues));
					}
					
					//clicking close button to navigate back to the landing page for Product Field Categories listing various Category Names.
					objConfigClientSettings.fn_clickClose(driver, webWait).click();
					Thread.sleep(2500);
				}
				Log.info("Read Product Field Categories from Aria EOM as :"+lstnvpProdFieldCategoriesWeb);
				
				//TODO : Read  Account Fields Data from Data Sheet .
				String strFilePath=BASE_TESTDATA_PATH+"\\"+strFilename;
				File filePath=new File(strFilePath);
				List<String> lststrProductFieldCategoriesDataFile=objReadFiles.readExcelFileAsArray(filePath, strWorksheet);
				List<NameValuePair> lstnvpProdFieldCategoriesFile = new ArrayList<NameValuePair>();
				
				//TODO: Get the NVP List created from the file data read...
				if(lststrProductFieldCategoriesDataFile.size() >= 4 ) {
					
					for(Integer intList = 0; intList < lststrProductFieldCategoriesDataFile.size(); intList += 4) {
						
						//Initially get the Category Name & Description...
						if(!lstnvpProdFieldCategoriesFile.contains(new BasicNameValuePair(lststrProductFieldCategoriesDataFile.get(intList), lststrProductFieldCategoriesDataFile.get(intList+1))))
							lstnvpProdFieldCategoriesFile.add(new BasicNameValuePair(lststrProductFieldCategoriesDataFile.get(intList), lststrProductFieldCategoriesDataFile.get(intList+1)));
					}
					
					for(Integer intList = 0; intList < lststrProductFieldCategoriesDataFile.size(); intList += 4) {
						
						//..then the Category Name & Product Fields...
						if(!lstnvpProdFieldCategoriesFile.contains(new BasicNameValuePair(lststrProductFieldCategoriesDataFile.get(intList), "PF_"+lststrProductFieldCategoriesDataFile.get(intList+2))))
							lstnvpProdFieldCategoriesFile.add(new BasicNameValuePair(lststrProductFieldCategoriesDataFile.get(intList), "PF_"+lststrProductFieldCategoriesDataFile.get(intList+2)));       			
					}    	       		
						
					for(Integer intList = 0; intList < lststrProductFieldCategoriesDataFile.size(); intList += 4) {
				   	
						//..then the Category Name & Plan Instance ID...
						if(!lstnvpProdFieldCategoriesFile.contains(new BasicNameValuePair(lststrProductFieldCategoriesDataFile.get(intList), "PIF_"+lststrProductFieldCategoriesDataFile.get(intList+3))))
							lstnvpProdFieldCategoriesFile.add(new BasicNameValuePair(lststrProductFieldCategoriesDataFile.get(intList), "PIF_"+lststrProductFieldCategoriesDataFile.get(intList+3)));
					}
				}
				//sorting it to match it to the read web data.
				Comparator<NameValuePair> comp = new Comparator<NameValuePair>() {
				
				@Override
				public int compare(NameValuePair o1, NameValuePair o2) {
					// TODO Auto-generated method stub
					return o1.getName().compareTo(o2.getName());
				}
			};				
			Collections.sort(lstnvpProdFieldCategoriesFile, comp);			
			
			//TODO: Create copies of the List's to carry out various comparisons.
			List<NameValuePair>	lstnvpProdFieldCategoriesFileCompare = new ArrayList<NameValuePair>(lstnvpProdFieldCategoriesFile);
			List<NameValuePair> lstnvpProdFieldCategoriesWebCompare = new ArrayList<NameValuePair>(lstnvpProdFieldCategoriesWeb);
				
			//TODO : Compare Data sheet data with Web Data.
			lstnvpProdFieldCategoriesFileCompare.removeAll(lstnvpProdFieldCategoriesWeb);
			if(lstnvpProdFieldCategoriesFileCompare.size() != 0) {
				
				for(NameValuePair nvpDataFile : lstnvpProdFieldCategoriesFileCompare) {
					
					strValue = "";					
					if(nvpDataFile.getValue().contains("PF_")) {
						
						strValue = nvpDataFile.getValue().replaceAll("PF_", "");
						strValueType = "'Product Fields'";
					}						
					else if(nvpDataFile.getValue().contains("PIF_")) {
						
						strValue = nvpDataFile.getValue().replaceAll("PIF_", "");
						strValueType = "'Plan Instance Fields'";
					}
					else
						strValueType = "'Description'";
					Log.info("ERROR: The data in Aria EOM didn't match with Excel file for Category Name: "+nvpDataFile.getName()
								+" of "+strValueType+" with value: "+strValue);
				}
				blnDataVerified = false;
			}
			
			//TODO : Compare Web Data with Data sheet.
			lstnvpProdFieldCategoriesWebCompare.removeAll(lstnvpProdFieldCategoriesFile);
			if(lstnvpProdFieldCategoriesWebCompare.size() != 0) {
				
				for(NameValuePair nvpDataWeb : lstnvpProdFieldCategoriesWebCompare) {
					
					strValue = "";
					if(nvpDataWeb.getValue().contains("PF_")) {
						
						strValue = nvpDataWeb.getValue().replaceAll("PF_", "");
						strValueType = "'Product Fields'";
					}						
					else if(nvpDataWeb.getValue().contains("PIF_")) {
						
						strValue = nvpDataWeb.getValue().replaceAll("PIF_", "");
						strValueType = "'Plan Instance Fields'";
					}
					else
						strValueType = "'Description'";
					Log.info("WARN: In Aria EOM, there is extra data compared to Excel File for Category Name: "+nvpDataWeb.getName()
								+" of "+strValueType+" with value: "+strValue);
				}
			}
        
			//TODO: Govern the status of the TC execution based on the boolean status gathered over various Validation stages. 
			if(blnDataVerified == false)			     
				assertTrue(false, "ERROR: The Product Field Categories aren't matching; exiting with exception !"); 
		 	    
		} catch (Exception exception) {
			
			Log.error("ERROR: Test case for Validating Product Field Categories failed with exception :"+exception.toString());
			Reporter.log("ERROR: Test case for Validating Product Field Categories failed with exception :"+exception.toString());
			exception.printStackTrace();
		}
	    
	    //log the end of test case.
	    Log.endTestCase(strTestCaseName);
	}
	
	
	@DataProvider(name="objTestData")
	public Object[][] data() throws DataDrivenFrameworkException   {
	   	
	   	Utils objutils = new Utils();
	   	return objutils.data1("TestCaseDetails", "VerifyProductFieldCatagories");    	
	}
	
	
	@AfterClass
	public void afterMethod() throws Exception	 {
		
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	}
}
