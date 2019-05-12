/*
 Author     		:	Nashim Khan
 Modified By		:	

 Class Name			: 	fn_ValidateAccountFields 
 Purpose     		: 	Purpose of this file is :
						1. To Capture the data under AccountFields  and validate with the Base Documentation.
												
 Date       		:	06/06/2016
 Modified Date		:
 Version Information:	Version 1.0 
 
 Jira ID #			:	QAABE-
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "VerifyAccountFields" 
 					    Excel-- "TestData.xlsx".
 						Worksheet Name-AccountFields 						

 Test Steps 		:	1. Login using valid role credentials for verifying the Configuration Account Fields.
 						2. Navigate to the Configuration -> client setting  -> Account Fields
 						3. Read the table on the landing page compare it with the expected data mentioned in the "AccountFields" worksheet.	
 						
 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/


package testCases.AriaConfiguration;

import java.io.File;
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
import pageObjects.ConfigurationClientSettings;
import utility.EnvironmentDetails;
import utility.Log;
import utility.ReadFiles;
import utility.Utils;
import utility.VerificationMethods;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_ValidateAccountFields extends VerificationMethods {
	
	public static WebDriver driver;
	public static WebDriverWait webWait;
	
	public static String strTestCaseName = "Verify Account Fields";

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
	
	@Test(dataProvider="objTestData",description="VerifyAccountFields")
	public void fn_ValidateAccountFields(String strFilename ,String strWorksheet) throws Exception {	
		
		//TODO: Create objects to read data table contents into.
		AriaEOM objAriaEOM = new AriaEOM();
		ConfigurationClientSettings objConfigClientSetting=new ConfigurationClientSettings();
		ReadFiles objReadFiles=new ReadFiles();
	
		
		Boolean blnDataVerified = false;
	    Boolean blnDataRowVerified=true;
		Log.startTestCase(strTestCaseName);
		
		try {
			
			//TODO : Navigate to the Account fields under Configuration menu.
			objAriaEOM.fn_clickConfiguration(driver, webWait).click();
			objAriaEOM.fn_clickClientSetting(driver, webWait).click();
			objAriaEOM.fn_clickAccountFields(driver, webWait).click();
			objConfigClientSetting.fn_getAccountFieldsDataTable(driver, webWait);	
			
		    // TODO :  Getter size of the Account Fields  displayed in ARIA EOM for further validations
			List<String> lststrAccountFieldsData = new ArrayList<String>();
			WebElement webAccountFieldsDataTable = objConfigClientSetting.fn_getAccountFieldsDataTable(driver, webWait).findElement(By.tagName("tbody"));
			List<WebElement> lstwebAccountFieldsRows = webAccountFieldsDataTable.findElements(By.tagName("tr"));
			String strAccountField = "";
			Log.info("Reading Web Data...");
			
			//TODO: Read the Account Fields  displayed in ARIA EOM for further validations.
			for(Integer intList = 0; intList < lstwebAccountFieldsRows.size(); intList++)	    	
	 	    {      
				webAccountFieldsDataTable = objConfigClientSetting.fn_getAccountFieldsDataTable(driver, webWait).findElement(By.tagName("tbody"));
				lstwebAccountFieldsRows = webAccountFieldsDataTable.findElements(By.tagName("tr"));
				
				objConfigClientSetting.fn_getAccountFieldsDataTable(driver, webWait);
				String strFieldNameval = lstwebAccountFieldsRows.get(intList).findElements(By.tagName("td")).get(0).getText().toString().trim();
				driver.findElement(By.xpath("//*[text()='"+strFieldNameval+"']")).click();	
				Thread.sleep(2000);	
				
				strAccountField =  objConfigClientSetting.fn_getAccountFieldVal(driver, webWait,1).getText();
				if(strAccountField.equals(""))
					strAccountField="NA";
				lststrAccountFieldsData.add(strAccountField);
				
				strAccountField =  objConfigClientSetting.fn_getDescription(driver, webWait).getText();
				if(strAccountField.equals(""))
					strAccountField="NA";
				lststrAccountFieldsData.add(strAccountField);	
				
				strAccountField = objConfigClientSetting.fn_getAccountFieldVal(driver, webWait,3).getText();
				if(strAccountField.equals(""))
					strAccountField="NA";
				lststrAccountFieldsData.add(strAccountField);
				
				strAccountField = objConfigClientSetting.fn_getRequired(driver, webWait).getText();
				if(strAccountField.equals(""))
					strAccountField="NA";
				lststrAccountFieldsData.add(strAccountField);
				
				strAccountField = objConfigClientSetting.fn_getAccountFieldVal(driver, webWait,5).getText();
				if(strAccountField.equals(""))
					strAccountField="NA";
				lststrAccountFieldsData.add(strAccountField);
				
				strAccountField = objConfigClientSetting.fn_getAccountFieldVal(driver, webWait ,6).getText();
				if(strAccountField.equals(""))
					strAccountField="NA";
				lststrAccountFieldsData.add(strAccountField);
				
				strAccountField =  objConfigClientSetting.fn_getMinSelection(driver, webWait).getAttribute("value");
				if(strAccountField.equals(""))
					strAccountField="NA";
				lststrAccountFieldsData.add(strAccountField);	
				
				strAccountField =  objConfigClientSetting.fn_getMaxSelection(driver, webWait).getAttribute("value");
				if(strAccountField.equals(""))
					strAccountField="NA";
				lststrAccountFieldsData.add(strAccountField);
				
				objConfigClientSetting.fn_clickClose(driver, webWait).click();
				objConfigClientSetting.fn_getAccountFieldsDataTable(driver, webWait);
			}
			Log.info("Reading Of Web data completed...: "+lststrAccountFieldsData);
			
			//TODO : Read  Account Fields Data from Data Sheet .
			String strFilePath=BASE_TESTDATA_PATH+"\\"+strFilename;
			File filePath=new File(strFilePath);
			List<String> lststrAccountFieldsDataFile=objReadFiles.readExcelFileAsArray(filePath, strWorksheet);							
			Thread.sleep(5000);

			//TODO : Compare earlier read DataSheet wit Web Data  
			Integer intFileData = 0;			
			for( intFileData = 0; intFileData < lststrAccountFieldsDataFile.size(); intFileData += 8) {
			
				blnDataVerified = false;					
				for(Integer intWebData = 0; intWebData < lststrAccountFieldsData.size(); intWebData += 8) {						
				
					if(lststrAccountFieldsDataFile.get(intFileData).equals(lststrAccountFieldsData.get(intWebData)) 
						  && lststrAccountFieldsDataFile.get(intFileData+1).equals(lststrAccountFieldsData.get(intWebData+1))
							&& lststrAccountFieldsDataFile.get(intFileData+2).equals(lststrAccountFieldsData.get(intWebData+2))
							 && lststrAccountFieldsDataFile.get(intFileData+3).equals(lststrAccountFieldsData.get(intWebData+3))
							  && lststrAccountFieldsDataFile.get(intFileData+4).equals(lststrAccountFieldsData.get(intWebData+4))
							   && lststrAccountFieldsDataFile.get(intFileData+5).equals(lststrAccountFieldsData.get(intWebData+5))
							    && lststrAccountFieldsDataFile.get(intFileData+6).equals(lststrAccountFieldsData.get(intWebData+6))
							     && lststrAccountFieldsDataFile.get(intFileData+7).equals(lststrAccountFieldsData.get(intWebData+7))) {
					
						Log.info("The Account Fields values are matching for Field Name : "+lststrAccountFieldsDataFile.get(intFileData));
						blnDataVerified = true;
						break;
					}
					if((intWebData/8 >= (lststrAccountFieldsDataFile.size()/8)-1) && (blnDataVerified == false)) {
						
						Log.error("ERROR: The Account Fields values aren't matching for Field Name : "+lststrAccountFieldsData.get(intFileData));
						Reporter.log("ERROR: The Account Fields values aren't matching for Field Name : "+lststrAccountFieldsData.get(intFileData));
						blnDataRowVerified = false;
						break;
					}
				}
			}	

			//TODO : Compare earlier read Web Data with Data Sheet
			for(Integer intWebData1 = 0; intWebData1 < lststrAccountFieldsData.size(); intWebData1 += 8) {
		
		    	blnDataVerified = false;
		    	for( Integer intFileData1 = 0; intFileData1 < lststrAccountFieldsDataFile.size(); intFileData1 += 8) {
					
					if (lststrAccountFieldsData.get(intWebData1).equals(lststrAccountFieldsDataFile.get(intFileData1))) 
						break;
					
					if((intFileData1/8 >= (lststrAccountFieldsDataFile.size()/8)-1) && (blnDataVerified == false)) {	
						
						Log.info("WARN: There is extra data listed for Account Fields in EOM instance of Field Name : "+lststrAccountFieldsData.get(intWebData1));					    			
					    break;
					}
				}
			}

			//TODO: Govern the status of the TC execution based on the boolean status gathered over various Validation stages. 
			if(blnDataRowVerified == false)			     
	        assertTrue(false, "ERROR: The Account fields aren't matching; exiting with exception !");
		
		}catch (Exception exception) {
			
			Log.error("ERROR: Test case for Validating Account Fields failed with exception :"+exception.toString());
			Reporter.log("ERROR: Test case for Validating Account Fields failed with exception :"+exception.toString());
			exception.printStackTrace();
		}
		
		Log.endTestCase(strTestCaseName);
	}
	
	
	 @DataProvider(name="objTestData")
	 public Object[][] data() throws DataDrivenFrameworkException    {
	   	
    	Utils objutils = new Utils();
    	return objutils.data1("TestCaseDetails", "VerifyAccountFields");    	
    }
	
	
	@AfterClass
	public void afterMethod() throws Exception	 {
		
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
	
	

}
