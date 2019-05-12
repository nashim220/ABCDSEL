/*
 Author     		:	 Nashim Khan

 Class Name			: 	Cls_ValidatePaymentMethod  
 Purpose     		: 	Purpose of this file is :
						1. To Capture the data under Payment Methods and validate with the Base Documentation.
												
 Date       		:	23/05/2016
 Modified Date		:	07/06/2016
 Version Information:	Version 2.0 
 
 Jira ID #			:	QAABE-
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "VerifyPaymentsMethods" test case name with 
 					    Excel-- "TestData.xlsx".
 						Worksheet Name-PaymentMethods 						

 Test Steps 		:	1. Login using valid role credentials for verifying the Configuration Payment Settings.
 						2. Navigate to the Configuration -> Payments -> Payment Methods.
 						3. Read the table on the landing page compare it with the expected data mentioned in the "PaymentMethods" worksheet.
 						
 Version Changes 2.0:	1. Updated the reference to the Account Overview Page Objects file. 							
 						
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
import pageObjects.ConfigurationPayments;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import utility.EnvironmentDetails;
import utility.Log;
import utility.ReadFiles;
import utility.Utils;
import utility.VerificationMethods;

public class Cls_ValidatePaymentMethods extends VerificationMethods {
	
	public static WebDriver driver;
	public static WebDriverWait webWait;
	
	public static String strTestCaseName = "Verify Payment Methods";

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
	
	 @Test(dataProvider="objTestData",description="VerifyPaymentsMethods")
	 public void fn_ValidatePaymentMethods(String strFilename, String strWorksheet ) throws IOException {
		 
	 	//TODO : Create Object For Page object
		AriaEOM objAriaEOM = new AriaEOM();
		ConfigurationPayments objConfigPayments = new ConfigurationPayments();
		
		Boolean blnDataVerified = false;
		Boolean blnDataRowVerified= true;
		try {
			
			//TODO : Navigate to the Payment Methods under Configuration menu.
			objAriaEOM.fn_clickConfiguration(driver, webWait).click();
			objAriaEOM.fn_clickConfigPayments(driver, webWait).click();
			objAriaEOM.fn_clickConfigPaymentMethods(driver, webWait).click();
			objConfigPayments.fn_getConfigPaymentMethodDataTable(driver, webWait);
			
			//TODO : Read and store  the Data from Web Table 
			List<String> lststrPaymentMethods = new ArrayList<String>();
			String strWebPaymentMethods = "";
			WebElement webPayMethodsDataTable = objConfigPayments.fn_getConfigPaymentMethodDataTable(driver, webWait).findElement(By.tagName("tbody"));
			List<WebElement> lstwebPayMethodsRows = webPayMethodsDataTable.findElements(By.tagName("tr"));
			
			//TODO: Read the Payment Method displayed in ARIA EOM for further validations.
			for(WebElement rows: lstwebPayMethodsRows) {
			
				List<WebElement> cols = rows.findElements(By.tagName("td"));		
				for(Integer colElement = 1; colElement < cols.size()-1; colElement++) {
				
					strWebPaymentMethods = cols.get(colElement).getAttribute("innerHTML").toString().trim();				   

					//to handle the columns marked Ticked in the Payment Methods table.
					if(strWebPaymentMethods.contains("check.gif"))
						strWebPaymentMethods="Yes";
	    		
					//to handle the empty columns in the Payment Methods table.
					if(strWebPaymentMethods.equals(""))
						strWebPaymentMethods = "NA";

					lststrPaymentMethods.add(strWebPaymentMethods);				  
				}
			}		
			Log.info("Web details read are: "+lststrPaymentMethods);
			
			//TODO : Read the Payment Method  displayed in  Test DataSheet for further validations.
			String strFilePath=BASE_TESTDATA_PATH+"\\"+strFilename;
			File filePath=new File(strFilePath);
			List <String> lststrPayMethodsDataFile = ReadFiles.readExcelFileAsArray(filePath, strWorksheet);			
			Log.info("WorkSheet details read are: "+lststrPayMethodsDataFile);
			Thread.sleep(5000);
			
			//TODO : Compare earlier read web Data to worksheet data .						
			Integer intFileData = 0;			
			for(intFileData = 0; intFileData < lststrPayMethodsDataFile.size(); intFileData += 7) {
				
				blnDataVerified = false;				
				for(Integer intWebData = 0; intWebData < lststrPaymentMethods.size(); intWebData += 7) {
					
					if(lststrPayMethodsDataFile.get(intFileData).equals(lststrPaymentMethods.get(intWebData))
							&& lststrPayMethodsDataFile.get(intFileData+1).equals(lststrPaymentMethods.get(intWebData+1))
							 && lststrPayMethodsDataFile.get(intFileData+2).equals(lststrPaymentMethods.get(intWebData+2))
							    && lststrPayMethodsDataFile.get(intFileData+3).equals(lststrPaymentMethods.get(intWebData+3))
								  && lststrPayMethodsDataFile.get(intFileData+4).equals(lststrPaymentMethods.get(intWebData+4))
								   && lststrPayMethodsDataFile.get(intFileData+5).equals(lststrPaymentMethods.get(intWebData+5))
								     && lststrPayMethodsDataFile.get(intFileData+6).equals(lststrPaymentMethods.get(intWebData+6))) {
						
										Log.info("The Payment Methods is matching for  Name ::"+lststrPayMethodsDataFile.get(intFileData));				
										blnDataVerified = true;
										break;
						}
							
					if((intWebData/7 >= (lststrPayMethodsDataFile.size()/7)-1) && (blnDataVerified == false)) {
						
						Log.error("ERROR: The Payment Methods aren't matching for  Name ::"+lststrPayMethodsDataFile.get(intFileData));
						Reporter.log("ERROR: The Payment Methods aren't matching for  Name ::"+lststrPayMethodsDataFile.get(intFileData));
						blnDataRowVerified = false;
						break;
					}					
				}						
			}
			
			//TODO : Compare earlier read web Data with  DataSheet
			for(Integer intWebData1 = 0; intWebData1 < lststrPaymentMethods.size(); intWebData1 += 7) {
				
				blnDataVerified = false;
				for( Integer intFileData1= 0; intFileData1 < lststrPayMethodsDataFile.size(); intFileData1 += 7) {
					
					if(lststrPaymentMethods.get(intWebData1).equals(lststrPayMethodsDataFile.get(intFileData1)))
						break;
					
					if((intFileData1/7 >= (lststrPaymentMethods.size()/7 -1)) && (blnDataVerified == false)) {						
						
						Log.info("WARN: There is extra data listed for Payment Methods in EOM instance of Parameter Name: "+lststrPaymentMethods.get(intWebData1));
					    break;
					}
				}			
			}
			  
			//TODO: Govern the status of the TC execution based on the boolean status gathered over various Validation stages. 
			if(blnDataRowVerified == false)			     
		        assertTrue(false, "ERROR: The Payment Methods values aren't matching for DataSheet items, exiting with exception !");
			
			
		} catch (Exception exception) {
			
			Log.error("ERROR: Test case for Validating Payment Methods failed with exception :"+exception.toString());
			Reporter.log("ERROR: Test case for Validating Payment Methods failed with exception :"+exception.toString());
			exception.printStackTrace();
		}
   }
	 
	@DataProvider(name="objTestData")
	 public Object[][] data() throws DataDrivenFrameworkException    {
	   	
    	Utils objutils = new Utils();
    	return objutils.data1("TestCaseDetails", "VerifyPaymentsMethods");    	
    }
			
	@AfterClass
	public void afterMethod() throws Exception	 {
		
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
 }