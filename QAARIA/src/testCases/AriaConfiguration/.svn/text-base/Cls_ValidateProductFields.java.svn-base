/*
 Author     		:	Nashim Khan
 Modified By		:
 
 Class Name			: 	fn_ValidateProductFields  
 Purpose     		: 	Purpose of this file is :
						1. To Capture the data under Product Fields  and validate with the Base Documentation.
												
 Date       		:	07/06/2016
 Modified Date		:
 Version Information:	Version 1.0 
 
 Jira ID #			:	QAABE-
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "VerifyProductFields" 
 					    Excel-- "TestData.xlsx".
 						Worksheet Name-ProductFields 						

 Test Steps 		:	1. Login using valid role credentials for verifying the Configuration Product Fields.
 						2. Navigate to the Configuration -> client setting  -> Product Fields.
 						3. Read the table on the landing page compare it with the expected data mentioned in the "ProductFields " worksheet.	
 						
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
import org.openqa.selenium.support.ui.Select;
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

public class Cls_ValidateProductFields extends VerificationMethods  {
	
		public static WebDriver driver;
		public static WebDriverWait webWait;
		public static WebDriverWait webWait1;
		public static String strTestCaseName = "Verify Product Fields";

		@BeforeClass
		 public static void beforeMethod()throws Exception  {	
			DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
			
			//TODO: Set Chrome driver as the driver to launch the test.
			driver = utility.Utils.fn_SetChromeDriverProperties();
					
			//TODO: initialize the webWait
			webWait = new WebDriverWait(driver, 60);
			webWait1 = new WebDriverWait(driver, 10);
			EnvironmentDetails objEnv = new EnvironmentDetails();
			Log.info("Login to application");
			//This parameters are taken from Exec file and its reference is available in EnvironmentDetails
			AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
		 }	
		
		@Test(dataProvider="objTestData",description="VerifyProductFields")
		public void fn_ValidateProductFields(String strFilename ,String strWorksheet) throws Exception {	
			
			Log.startTestCase(strTestCaseName);
			//TODO: Create objects to read data table contents into.
			AriaEOM objAriaEOM = new AriaEOM();
			ConfigurationClientSettings objConfigClientSettings=new ConfigurationClientSettings();
			ReadFiles objReadFiles=new ReadFiles();
			
			Boolean blnDataVerified = false;
		    Boolean blnDataRowVerified=true;
		    
		    Log.startTestCase(strTestCaseName);
		    
			try {
				
				//TODO : Navigate to the Account fields under Configuration menu.
				objAriaEOM.fn_clickConfiguration(driver, webWait).click();
				objAriaEOM.fn_clickClientSetting(driver, webWait).click();
				objAriaEOM.fn_clickProductFields(driver, webWait).click();
				objAriaEOM.fn_getDataTable(driver, webWait);
				
				// TODO :  Getter size of the Account Fields  displayed in ARIA EOM for further validations
				List<String> lststrProductFieldsData = new ArrayList<String>();
				WebElement webProductFieldsDataTable = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
				List<WebElement> lstwebProductFieldsRows = webProductFieldsDataTable.findElements(By.tagName("tr"));
				String strProductField = "";
				Log.info("Reading Web Table...");
				
				//TODO: Read the Account Fields  displayed in ARIA EOM for further validations.
				for(Integer intList = 0; intList < lstwebProductFieldsRows.size(); intList++) {
					
					objAriaEOM.fn_getDataTable(driver, webWait);
					webProductFieldsDataTable = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
					lstwebProductFieldsRows = webProductFieldsDataTable.findElements(By.tagName("tr"));
					
					String strFieldNameval = lstwebProductFieldsRows.get(intList).findElements(By.tagName("td")).get(0).getText().toString().trim();
					driver.findElement(By.xpath("//*[text()='"+strFieldNameval+"']")).click();
					Thread.sleep(2000);
					
					  //TODO : Read Details Table and Get Visible data 						
					strProductField	= objConfigClientSettings.fn_getProductFieldName(driver, webWait).getAttribute("value");
					if(strProductField.equals(""))
						strProductField="NA";
					lststrProductFieldsData.add(strProductField);				
				
					strProductField = objConfigClientSettings.fn_getProductDescription(driver, webWait).getText();
				    if(strProductField.equals(""))
				    	strProductField="NA";
				    lststrProductFieldsData.add(strProductField);
				    
				    strProductField = objConfigClientSettings.fn_getProductDataType(driver, webWait).getText();
				    if(strProductField.equals(""))
				    	strProductField = "NA";
				    lststrProductFieldsData.add(strProductField);
				    
				    strProductField = objConfigClientSettings.fn_getProductInputType(driver, webWait).getText();
				    if(strProductField.equals(""))
				    	strProductField = "NA";
				    lststrProductFieldsData.add(strProductField);
				    
				    strProductField = objConfigClientSettings.fn_getCloudServiceFieldCategory(driver, webWait).getText();
				    if(strProductField.equals(""))
				    	strProductField = "NA";
				    lststrProductFieldsData.add(strProductField);
				    
				    strProductField = objConfigClientSettings.fn_getProductMinNumOfSelections(driver, webWait).getAttribute("value");
				    if(strProductField.equals(""))
				    	strProductField = "NA";
				    lststrProductFieldsData.add(strProductField);
				    
				    strProductField = objConfigClientSettings.fn_getProductMaxNumSelectionsAllowed(driver, webWait).getAttribute("value");
				    if(strProductField.equals(""))
				    	strProductField = "NA";
				    lststrProductFieldsData.add(strProductField);
				    
				    Thread.sleep(1500);				    
				    objConfigClientSettings.fn_clickProductObjectTypeTab(driver, webWait).click();
				    Thread.sleep(3500);
				    objConfigClientSettings.fn_getProductObjectTypesTable(driver, webWait);
				    
				    //TODO : Read Object Type Table and Get Visible data 
				    WebElement lstObjectTypeDataTable = objConfigClientSettings.fn_getProductObjectTypesTable(driver, webWait);
				    List<WebElement>  lstwebObjectTypesRows = lstObjectTypeDataTable.findElements(By.tagName("tr"));	
				    String strProductFields = "";
				    String strActualTagName = "";				    
				    for(WebElement rows : lstwebObjectTypesRows) {
				    	
				    	List<WebElement> columns = rows.findElements(By.tagName("td"));
				    	for(Integer intColumns = 1; intColumns < columns.size(); intColumns ++) {
				    		
				    		String strProductHTMLTagValue = columns.get(intColumns).getAttribute("innerHTML").toString().trim();	
						    strProductHTMLTagValue = strProductHTMLTagValue.replaceAll("\"", "");
				    		if(strProductHTMLTagValue.contains("<") && strProductHTMLTagValue.contains(">") && !strProductHTMLTagValue.contains("selected=selected" ) 
				    				&&!strProductHTMLTagValue.contains("type=checkbox")) {
				    			
				    			//this try catch has been introduced to catch the tag <b> which has no spaces before the ending ">"...
				    			try {
				    			
				    				strActualTagName = strProductHTMLTagValue.substring(strProductHTMLTagValue.indexOf("<")+1, strProductHTMLTagValue.indexOf(" "));    			
					    			strProductFields = columns.get(intColumns).findElement(By.tagName(strActualTagName)).getText().toString().trim();
				    			}
				    			catch (Exception exception){
				    				
					    			strProductFields = columns.get(intColumns).getAttribute("innerText").toString().trim();
				    			}
				    			 
				    			if(strProductFields.equals(""))
				    				strProductFields = "NA";

				    			lststrProductFieldsData.add(strProductFields);
				    		}			    		    			
				    		else if( strProductHTMLTagValue.contains("selected=selected" )) { 
			    			
				    			WebElement webProductSelXpath= columns.get(intColumns).findElement(By.tagName("select"));
				    			Select objSelect = new Select(webProductSelXpath); 
				    			strProductFields = objSelect.getFirstSelectedOption().getText().toString();
				    			if(strProductFields.equals("") || strProductFields.equals("..."))
				    				strProductFields = "NA";
							    
				    			lststrProductFieldsData.add(strProductFields);			    			
				    		} 
				    		else if( strProductHTMLTagValue.contains("type=checkbox")) {			    			
			    			
				    			strActualTagName = strProductHTMLTagValue.substring(strProductHTMLTagValue.indexOf("<")+1, strProductHTMLTagValue.indexOf(" "));
				    			
				    			Boolean blnCheckBoxSelected = columns.get(intColumns).findElement(By.tagName(strActualTagName)).isSelected();				    							    			
				    			if(blnCheckBoxSelected == false)
				    				strProductFields = "No";
				    			else if(blnCheckBoxSelected == true)
				    				strProductFields = "Yes";
							
				    			lststrProductFieldsData.add(strProductFields);
				    		}
				    	}				    	
				    }				    
				    Thread.sleep(2000);
				    objConfigClientSettings.fn_clickClose(driver, webWait).click();				    
				}
			
				Log.info("Reading of Web Table Completed : "+lststrProductFieldsData);
				
				//TODO : Read  Account Fields Data from Data Sheet .
				String strFilePath=BASE_TESTDATA_PATH+"\\"+strFilename;
				File filePath=new File(strFilePath);
				List<String> lststrAccountFieldsDataFile=objReadFiles.readExcelFileAsArray(filePath, strWorksheet);							
				Thread.sleep(5000);
				Log.info("File Data Reading Completed : "+lststrAccountFieldsDataFile);
				
				//TODO : Compare earlier read Web Data to  DataSheet
				Integer intFileData = 0;			
				for( intFileData = 0; intFileData < lststrAccountFieldsDataFile.size(); intFileData += 27) {
				
					blnDataVerified = false;					
					for(Integer intWebData = 0; intWebData < lststrProductFieldsData.size(); intWebData += 27) {						
					
						if(lststrAccountFieldsDataFile.get(intFileData).equals(lststrProductFieldsData.get(intWebData)) 
							  && lststrAccountFieldsDataFile.get(intFileData+1).equals(lststrProductFieldsData.get(intWebData+1))
								&& lststrAccountFieldsDataFile.get(intFileData+2).equalsIgnoreCase(lststrProductFieldsData.get(intWebData+2))
								 && lststrAccountFieldsDataFile.get(intFileData+3).equalsIgnoreCase(lststrProductFieldsData.get(intWebData+3))
								  && lststrAccountFieldsDataFile.get(intFileData+4).equals(lststrProductFieldsData.get(intWebData+4))
								   && lststrAccountFieldsDataFile.get(intFileData+5).equals(lststrProductFieldsData.get(intWebData+5))
								    && lststrAccountFieldsDataFile.get(intFileData+6).equals(lststrProductFieldsData.get(intWebData+6))
								     && lststrAccountFieldsDataFile.get(intFileData+7).equals(lststrProductFieldsData.get(intWebData+7))
								      && lststrAccountFieldsDataFile.get(intFileData+8).equals(lststrProductFieldsData.get(intWebData+8))
								       && lststrAccountFieldsDataFile.get(intFileData+9).equals(lststrProductFieldsData.get(intWebData+9))
								        && lststrAccountFieldsDataFile.get(intFileData+10).equals(lststrProductFieldsData.get(intWebData+10))
								         && lststrAccountFieldsDataFile.get(intFileData+11).equals(lststrProductFieldsData.get(intWebData+11))
								          && lststrAccountFieldsDataFile.get(intFileData+12).equals(lststrProductFieldsData.get(intWebData+12))
								           && lststrAccountFieldsDataFile.get(intFileData+13).equals(lststrProductFieldsData.get(intWebData+13))
								            && lststrAccountFieldsDataFile.get(intFileData+14).equals(lststrProductFieldsData.get(intWebData+14))
								             && lststrAccountFieldsDataFile.get(intFileData+15).equals(lststrProductFieldsData.get(intWebData+15))
								              && lststrAccountFieldsDataFile.get(intFileData+16).equals(lststrProductFieldsData.get(intWebData+16))
								               && lststrAccountFieldsDataFile.get(intFileData+17).equals(lststrProductFieldsData.get(intWebData+17))
								                && lststrAccountFieldsDataFile.get(intFileData+18).equalsIgnoreCase(lststrProductFieldsData.get(intWebData+18))
								                 && lststrAccountFieldsDataFile.get(intFileData+19).equals(lststrProductFieldsData.get(intWebData+19))
								                  && lststrAccountFieldsDataFile.get(intFileData+20).equals(lststrProductFieldsData.get(intWebData+20))
								                   && lststrAccountFieldsDataFile.get(intFileData+21).equals(lststrProductFieldsData.get(intWebData+21))
								                    && lststrAccountFieldsDataFile.get(intFileData+22).equals(lststrProductFieldsData.get(intWebData+22))
								                     && lststrAccountFieldsDataFile.get(intFileData+23).equals(lststrProductFieldsData.get(intWebData+23))
								                      && lststrAccountFieldsDataFile.get(intFileData+24).equals(lststrProductFieldsData.get(intWebData+24))
								                       && lststrAccountFieldsDataFile.get(intFileData+25).equals(lststrProductFieldsData.get(intWebData+25))
								                        && lststrAccountFieldsDataFile.get(intFileData+26).equals(lststrProductFieldsData.get(intWebData+26))) {	
								
						
							Log.info("The Account Fields values are matching for Field Name ::"+lststrAccountFieldsDataFile.get(intFileData));
							blnDataVerified = true;
							break;
						}
						if((intWebData/27 >= (lststrAccountFieldsDataFile.size()/27)-1) && (blnDataVerified == false)) {
							
							Log.error("ERROR: The Account Fields values aren't matching for Field Name ::"+lststrProductFieldsData.get(intFileData));
							Reporter.log("ERROR: The Account Fields values aren't matching for Field Name ::"+lststrProductFieldsData.get(intFileData));
							blnDataRowVerified = false;
							break;
						}
					}
				}	
			
				//TODO : Compare earlier read Web Data with  DataSheet
				for(Integer intWebData1 = 0; intWebData1 < lststrProductFieldsData.size(); intWebData1 += 27) {
			
			    	blnDataVerified = false;
			    	for( Integer intFileData1 = 0; intFileData1 < lststrAccountFieldsDataFile.size(); intFileData1 += 27) {
						
						if (lststrProductFieldsData.get(intWebData1).equals(lststrAccountFieldsDataFile.get(intFileData1))) 
							break;
						
						if((intFileData1/27 >= (lststrAccountFieldsDataFile.size()/27)-1) && (blnDataVerified == false)) {	
							
							Log.info("WARN: There is extra data listed for Account Fields in EOM instance of Field Name: "+lststrProductFieldsData.get(intWebData1));					    			
						    break;
						}
					}
				}

				//TODO: Govern the status of the TC execution based on the boolean status gathered over various Validation stages. 
				if(blnDataRowVerified == false)			     
		        assertTrue(false, "ERROR: The Account fields aren't matching; exiting with exception !");
			}			
			catch (Exception exception) {
				Log.error("ERROR: Test case for Validating product Fields failed with exception :"+exception.toString());
				Reporter.log("ERROR: Test case for Validating Product Fields failed with exception :"+exception.toString());
				exception.printStackTrace();
				
			}			
		}
		
		@DataProvider(name="objTestData")
		public Object[][] data() throws DataDrivenFrameworkException    {
		   	
	    	Utils objutils = new Utils();
	    	return objutils.data1("TestCaseDetails", "VerifyProductFields");    	
	    }
		
		
		@AfterClass
		public void afterMethod() throws Exception	 {
			
			 //TODO: Logs out of the application & quit the driver
			 AriaLoginLogout.appLogout(driver);
			 driver.quit(); 
		 }
}
