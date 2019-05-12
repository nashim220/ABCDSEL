
/*
 Author     		:	Madhavi JN
 Class Name			: 	Cls_NotificationSettings  
 Purpose     		: 	Purpose of this file is :
						1. To Capture the data under notification settings and validate with the Base Documentation
												
 Date       		:	26/06/2015
 Version Information:	Version 1.0
 
 Jira ID #				:	QAABE-218
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "Notification settings" 
 						worksheet for excel "TestData.xlsx".
 						3.Excel with application data is captured at run time is placed under TestData folder C:\\QAARIA\\src\\testData\\NotificationSettings.xlsx

 Test Steps 		:	1. Login using valid role credentials for verifying the Configuration Payment Settings.
 						2. Navigate to the Configuration -> Billing -> Notification settingss.
 						3. Read the table on the landing page and write it to in C:\\QAARIA\\src\\testData\\NotificationSettings.xlsx 
 						compare it with the expected data mentioned in the
 						"InvoiceSettings" worksheet for excel "TestData.xls".
 						
Output :- Boolean values are written as false/True in C:\\QAARIA\\src\\testData\\TestData.xlsx in sheet NotificationSettings. 						
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AriaConfiguration;
import org.testng.annotations.*;

import appModules.AriaLoginLogout;
import appModules.Cls_NotificationSettingsActns;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Log;
import utility.Utils;
import utility.EnvironmentDetails;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import testCases.BaseTestCase;

public class Cls_NotificationSettings extends BaseTestCase{
		
		public WebDriver driver;
		
		@BeforeClass
		 public void beforeMethod()throws Exception
		 {	
     		System.setProperty("webdriver.chrome.driver",chromeDriverPath);
			driver = new ChromeDriver();
			EnvironmentDetails objEnv = new EnvironmentDetails();
			Log.startTestCase("Login to application");
			AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);		
		 }
			
		@Test
		public void fn_CreateNotificationSettings() throws Exception 
		{
			WebDriverWait webWait;
			webWait = new WebDriverWait(driver,1000);
			Log.startTestCase("Create_Usage_Invoice" );	
			
			Cls_NotificationSettingsActns objfnctns = new Cls_NotificationSettingsActns();
			objfnctns.fn_notificationSettings(driver, webWait);
			Thread.sleep(5000);
			WebElement Webtable = driver.findElement(By.id("DataTables_Table_0"));
			// Now get all the TR elements from the table
			List<WebElement> TotalRowCount = Webtable.findElements(By.tagName("tr"));
			String[][] members = new String[15][4];
			// And iterate over them, getting the cells
			int RowIndex=0;
			for (WebElement rowElement: TotalRowCount) 
			{
					List<WebElement> TotalColumnCount=rowElement.findElements(By.xpath("td"));
					int ColumnIndex=0;
					for(WebElement colElement:TotalColumnCount)
					{
					members[RowIndex][ColumnIndex] = colElement.getText();
					ColumnIndex=ColumnIndex+1;
					}
				RowIndex=RowIndex+1;  
			}
			
			Utils objInternalData = new Utils();
			//Excel to be passed for Storing the application data further to be compared by Base Excel
			objInternalData.writeexcel_InternalData(members,BASE_TESTDATA_PATH+"NotificationSettings.xlsx","Sheet1",3);
			Thread.sleep(8000);
			//Base Excel path and application data excel path to be passed for comparision
			objInternalData.CompareExcelSheets(BASE_TESTDATA_PATH+"NotificationSettings.xlsx",BASE_TESTDATA_PATH+"TestData.xlsx");
		}

		
		 @AfterTest
		public void afterMethod() throws Exception
		 {
			 //Logs out of the application
			 AriaLoginLogout.appLogout(driver); 	
			 driver.quit(); 
		 }
		 

	}
	
	

