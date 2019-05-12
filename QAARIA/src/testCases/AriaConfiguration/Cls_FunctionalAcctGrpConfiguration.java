/*
 Author     		:	Namrata Akarte
 Class Name			: 	Cls_ValidateIEBillingCountries  
 Purpose     		: 	Purpose of this file is :
						 To validate configuration for IE Functional Account Group									
 Date       		:	12/28/2015
 Version Information:	Version 1.0
 
 Jira #				:	QAABE-514
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "TestCaseDetails" 
 						worksheet for excel "TestData.xlsx" in 'IEFunctionalAccountGroup'. 				

 Test Steps 		:	1. Search an existing account
 						2. Navigate to Configurations -> Client Settings -> Functional Account Groups
 						3. Verify Group Name, Client Group ID, Starting Sequence Number and Notification Template Group fields
 						
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/	
package testCases.AriaConfiguration;

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

import pageObjects.ConfigurationInvoiceSettings;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_FunctionalAcctGrpConfiguration {
	
	public static WebDriver driver;
	public static WebDriverWait webWait;
    public static WebElement webElement;
    
    @BeforeClass
    public void beforeMethod()throws Exception
    {	
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
    
	@Test(dataProvider = "objectTestData1")
	public void fn_FunctionalAcctGrpConfiguration(String  sTestCaseName, String strTestGrpName, String strTestClientGrpID, String strTestNotificationTempGrp, String strTestGrpPrefix) {

		String strSeqNumPath = "//label[text()='Starting Sequence Number']/following::input";
		Log.startTestCase(sTestCaseName);
		ConfigurationInvoiceSettings objconfigInvice = new ConfigurationInvoiceSettings();		
		try {
					
			//TODO : Click on Configurations tab
			objconfigInvice.fn_ConfigurationLink(driver, webWait).click();
			
			//TODO : Click on Client Settings Link
			objconfigInvice.fn_ClientSettingsLink(driver, webWait).click();
			
			//TODO : Click on Functional Account Groups link
			objconfigInvice.fn_FunAcctGroupLink(driver, webWait).click();
			
			Thread.sleep(15000);
			//TODO : Click on link from Functional Account Groups table corresponding to IE
			//objconfigInvice.fn_IEtableLink(driver, webWait).click();
			
			System.out.println("/-*******************************************-/");
			
			//Clicking on the Functional Account Groups table corresponding to IE
		    WebElement Webtable = driver.findElement(By.id("DataTables_Table_0"));
					    
			// Now get all the TR elements from the table
			List<WebElement> TotalRowCount = Webtable.findElements(By.tagName("tr"));
			
			// And iterate over them, getting the cells
			int RowIndex=0;
			Log.info("row size for Functional Accounts Group table : "+TotalRowCount.size());
			for (WebElement rowElement: TotalRowCount) 
			{
					List<WebElement> TotalColumnCount=rowElement.findElements(By.xpath("td"));					
					int ColumnIndex=0;					
					for(WebElement colElement:TotalColumnCount)
					{
						
						if (colElement.getText().equals(strTestGrpName))
						{
							
							colElement.click();
							Log.info("the requested Functional Account Groups table corresponding to IE is found : "+strTestGrpName);
						}
					
					ColumnIndex=ColumnIndex+1;
					}
			RowIndex=RowIndex+1;  
			}
			
			System.out.println("/-******Validation of Functional Account Group configured for IE******-/");
			//TODO : Read value from Group Name
			String strGrpName = objconfigInvice.fn_GroupNameField(driver, webWait).getAttribute("value");
			Log.info("Group Name is : "+strGrpName);
			
			//TODO : Read value from Client Group ID
			String strClientGrpID = objconfigInvice.fn_ClientGrpIDField(driver, webWait).getAttribute("value");
			Log.info("Client Group ID is : "+strClientGrpID);
						
			//TODO : Read value from Starting Sequence Number
			String strSeqNum = driver.findElement(By.xpath(strSeqNumPath)).getAttribute("value");
			Log.info("Starting Sequnce Number : "+strSeqNum);
			
			//TODO : Read value from Notification Template Group
			String strNotificationTempGrp = new Select(objconfigInvice.fn_NotificationTempGrpField(driver, webWait)).getFirstSelectedOption().getText();
			Log.info("Notification Template Group : "+strNotificationTempGrp);
			
			//TODO : Read value from Group Prefix
			String strGrpPrefix = objconfigInvice.fn_GrpPrefix(driver, webWait).getAttribute("value");;
			Log.info("Group Prefix : "+strGrpPrefix);
			System.out.println("/-********************************************************************-/");
			
			Utils.takeScreenshot(driver, sTestCaseName);
			
			if (strGrpName.equalsIgnoreCase(strTestGrpName) && strClientGrpID.equalsIgnoreCase(strTestClientGrpID) && strSeqNum.contains("300000") && strNotificationTempGrp.equalsIgnoreCase(strTestNotificationTempGrp) && strGrpPrefix.equalsIgnoreCase(strTestGrpPrefix) ){
				Log.info("PASS : All field values are displayed correctly.");
			}
			else{
				Reporter.log("FAIL : One or more field values are NOT displayed correctly.");
				Log.info("FAIL : One or more field values are NOT displayed correctly.");
				Log.info("Group Name # Expected Value : IE   Application Value : "+strGrpName);
				Log.info("Group Prefix Value # Expected Value : IE   Application Value : "+strGrpPrefix);
				Log.info("Client Group ID # Expected Value : IE   Application Value : "+strClientGrpID); 
				Log.info("Starting Sequence Number # Expected Value : Should contain 30000   Application Value : "+strSeqNum); 
				Log.info("Notification Template Group # Expected Value : 'Sungard AS IE Credit Note and Statement Template'   Application Value : "+strNotificationTempGrp); 
			}
				
			Log.endTestCase(sTestCaseName);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@AfterClass
	public void afterMethod() throws Exception
	{
    	//TODO: Logs out of the application & quit the driver
		AriaLoginLogout.appLogout(driver);
		driver.quit(); 
	}
	
	//Reading data for the test 	
    @DataProvider(name = "objectTestData1")
    public Object[][] testExcelData1() throws DataDrivenFrameworkException
	 {
		 Utils objUtils=new Utils();
		 return objUtils.data1("TestCaseDetails", "IEFunctionalAccountGroup");
	 } 
}
