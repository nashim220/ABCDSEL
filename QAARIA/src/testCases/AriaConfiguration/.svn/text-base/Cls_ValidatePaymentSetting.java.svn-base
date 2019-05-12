/*
 Author     		:	Nashim Khan
 Class Name			: 	fn_ValidatePaymentSetting  
 Purpose     		: 	Purpose of this file is :
						1. To Capture the data under Payment Setting  and validate with the Base Documentation.
												
 Date       		:	25/05/2016

 Version Information:	Version 1.0 
 
 Jira ID #			:	QAABE-
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "VerifyPaymentSetting" 
 					    Excel-- "TestData.xlsx".
 						Worksheet Name-PaymentSettings 						

 Test Steps 		:	1. Login using valid role credentials for verifying the Configuration Payment Settings.
 						2. Navigate to the Configuration -> Payments -> Payment Setting.
 						3. Read the table on the landing page compare it with the expected data mentioned in the "PaymentSettings" worksheet.	
 						
 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AriaConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import utility.EnvironmentDetails;
import utility.Log;
import utility.ReadFiles;
import utility.Utils;
import utility.VerificationMethods;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_ValidatePaymentSetting  extends VerificationMethods {

	public static WebDriver driver;
	public static WebDriverWait webWait;
	
	public static String strTestCaseName = "Verify Payment Settings";

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
	
	@Test(dataProvider="objTestData",description="VerifyPaymentSetting")
	 public void fn_ValidatePaymentSetting(String strFilename, String strWorksheet) throws IOException {
		 
	 	//TODO : Create Object For Page object
		AriaEOM objAriaEOM = new AriaEOM();
		
		Boolean blnDataRowVerified= true;
		Boolean blnDataVerified = false;
		try {

			//TODO : Navigate to the Payment Setting under Configuration menu.
			objAriaEOM.fn_clickConfiguration(driver, webWait).click();
			objAriaEOM.fn_clickConfigPayments(driver, webWait).click();
		    objAriaEOM.fn_clickConfigPaymentSetting(driver, webWait).click();
		    objAriaEOM.fn_getDataTable(driver, webWait);
		    
		    //TODO : Read and store  the Data from Web Table 
		    List<String> lststrPaymentSetting = new ArrayList<String>();
		    String strWebPaymentMethods = "";
		    
			WebElement webPaySettingsDataTable = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
			List<WebElement> lstwebPaySettingsRows = webPaySettingsDataTable.findElements(By.tagName("tr"));			
			//TODO: Read the Payment Setting displayed in ARIA EOM for further validations.
			for(WebElement rows: lstwebPaySettingsRows) {
				
				List<WebElement> cols = rows.findElements(By.tagName("td"));
				
				for(WebElement colElement : cols ) {
					
				   strWebPaymentMethods = colElement.getText().toString().trim();			   				   
				   lststrPaymentSetting.add(strWebPaymentMethods);		
				}
			}
			
			//TODO : Read Data from Data Sheet.
			String strFilePath=BASE_TESTDATA_PATH+"\\"+strFilename;
			File filePath=new File(strFilePath);
			List <String> lststrPaySettingDataFile = ReadFiles.readExcelFileAsArray(filePath, strWorksheet);
			Log.info("WorkSheet details read are: "+lststrPaySettingDataFile);
			Thread.sleep(5000);
			
			//TODO : Compare earlier read  DataSheet with Web Data   
			for(Integer intFileData = 0; intFileData < lststrPaySettingDataFile.size(); intFileData+= 4) {
				
				blnDataVerified = false;
				for(Integer intWebData = 0; intWebData < lststrPaymentSetting.size(); intWebData += 4) {				
				
					if(lststrPaySettingDataFile.get(intFileData).equals(lststrPaymentSetting.get(intWebData)) 
						&& lststrPaySettingDataFile.get(intFileData+1).equals(lststrPaymentSetting.get(intWebData+1))
							&& lststrPaySettingDataFile.get(intFileData+2).equalsIgnoreCase(lststrPaymentSetting.get(intWebData+2))
								&& lststrPaySettingDataFile.get(intFileData+3).equals(lststrPaymentSetting.get(intWebData+3))) {
					
						Log.info("The Payment Setting value are matching for Parameter Name :: "+lststrPaySettingDataFile.get(intFileData));
						break;
					}
					
					if((intWebData/4 >= (lststrPaySettingDataFile.size()/4)-1) && (blnDataVerified == false)) {
						
						Log.error("ERROR: The Payment Setting aren't matching with EOM instance for Parameter Name :: "+lststrPaymentSetting.get(intFileData));
						Reporter.log("ERROR: The Payment Setting aren't matching with EOM instance for Parameter Name :: "+lststrPaymentSetting.get(intFileData));
						blnDataRowVerified = false;
						break;
					}
				}
			}			
			
			//TODO : Compare earlier read web Data with  DataSheet
            for(Integer intWebData1 = 0; intWebData1 < lststrPaymentSetting.size(); intWebData1 += 4) {

            	blnDataVerified = false;
				for( Integer intFileData1= 0; intFileData1 < lststrPaySettingDataFile.size(); intFileData1 += 4) {
					
					if (lststrPaymentSetting.get(intWebData1).equals(lststrPaySettingDataFile.get(intFileData1))) 
						break;
					
					if((intFileData1/4 >= (lststrPaySettingDataFile.size()/4 -1)) && (blnDataVerified == false)) {					
						
						Log.info("WARN: There is extra data listed for Payment Setting in EOM instance of Parameter Name: "+lststrPaymentSetting.get(intWebData1));					    			
					    break;
					}
				}
			}
            
			//TODO: Govern the status of the TC execution based on the boolean status gathered over various Validation stages. 
			if(blnDataRowVerified == false)			     
		        assertTrue(false, "ERROR: The Payment Setting aren't matching; exiting with exception !");
		
		} catch (Exception exception) {
			Log.error("ERROR: Test case for Validating Payment Settings failed with exception :"+exception.toString());
			Reporter.log("ERROR: Test case for Validating Payment Settings failed with exception :"+exception.toString());
			exception.printStackTrace();
		}
	}
		
	@DataProvider(name="objTestData")
	 public Object[][] data() throws DataDrivenFrameworkException    {
	   	
   	Utils objutils = new Utils();
   	return objutils.data1("TestCaseDetails", "VerifyPaymentSetting");    	
   }
		
	@AfterClass
	public void afterMethod() throws Exception	 {
		
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
}
