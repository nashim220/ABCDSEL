package testCases.AccountTestCases;

//import org.testng.annotations.Test;
import org.testng.annotations.*;

import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
import atu.ddf.exceptions.DataDrivenFrameworkException;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;

import pageObjects.*;
import utility.Log;
import utility.Utils;
import utility.EnvironmentDetails;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.VerificationMethods;

import java.util.List;
import java.util.Map;
/*
Author     		:	Namrata Akarte
Class Name			: 	Cls_API_ChangeCustomAccountFields 
Purpose     		: 	To update custom defined fields through API
Date       			:	07/14/2015	
Version Information	:	Version 1.0
					:	Version 2.0		08/18/2015 Updated to pass run time field names

PreCondition 		:   1. Data to be populated in the "TestCaseDetails" " worksheet for excel "TestData.xls".
						
Test Steps 			:	1. Login using valid role credentials for creating New account.
						2. Search Account
						3. Navigate to Customer Defined fields
						4. Enter the values in the respective fields							
 
Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
						All Rights Reserved 
*/

public class Cls_ChangeCustomAccountFields extends VerificationMethods{
	public WebDriver driver;
	
	@BeforeClass
	 public void beforeMethod()throws Exception
	 {	
		DOMConfigurator.configure("log4j.xml");
		System.setProperty("webdriver.chrome.driver",chromeDriverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.startTestCase("Login to application");
		//Logs in to the aria application with the appropriate credentials		 	
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);
	
	 }
		
	@Test(dataProvider="objectTestData1", description="Change Custom Account Fields")
	public void fn_ChangeCustomAccountFields(String acct_num, String searchFields, String TestString_200, String TestString_79)  {
		Log.startTestCase("ChangeCustomAccountFields");
			
		WebDriverWait webWait;
		//TODO : Initialize WebWait
		webWait = new WebDriverWait(driver,1000);
		
		Log.startTestCase("Change Custom Account Fields" );
		
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
		ChangeDeleteClientDefinedFields objClientDef = new ChangeDeleteClientDefinedFields();
		
		//TODO : Read array mentioned in the test data file
		String[] fields = searchFields.split("#");
		String[] ApplFields = new String[30];
			
		try{
			//TODO : Search account mentioned in the test data file
			
			objClientDefAct.AccountSearch(driver, webWait, acct_num);
			
			//if (isSearched == 1){
				Thread.sleep(500);
				//TODO : Wait till the page loads 
				webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Reset Password")));
				
				//TODO : click link client defined fields
				objClientDef.fn_ClientDefLink(driver, webWait).click();
					
				int intlen = fields.length;//Compute test array length
				Thread.sleep(1000);
				Utils.takeScreenshot(driver, "Change Custom Account Fields");
				//TODO : Identify the application test data table
				WebElement table = driver.findElement(By.tagName("table"));
					
				WebElement tableCustDefinedFields = table.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[2]/div[3]/div/div/div[4]/table"));
			  
				List<WebElement>rows = tableCustDefinedFields.findElements(By.tagName("tr"));
				
				int i=0;
				
				//TODO : Read run time field names and store in the array ApplFields[]
				for (WebElement row : rows){
				 
					Thread.sleep(1000);
					List<WebElement>cols = row.findElements(By.tagName("td"));
					int intcol =0;
					for (WebElement col:cols){
						intcol++;
						if (intcol == 1){
							 String strFieldName = col.getText();
							 ApplFields[i] = strFieldName;
							 Log.info("Field Name : "+ApplFields[i]);
						}	 
					}
					i++;
				 }
				 
				 int intlenApplFields = i;	//To Resize ApplFields[]
				 Log.info("No of Customer Defined Fields available :"+i);
				 int isFound = 0;	//Initiate flag
				 
				 //TODO : Access each Field name
				 for(int intCnt = 1;intCnt<intlenApplFields;intCnt++){
					 Log.info("Field to be Accessed "+ApplFields[intCnt]);
					 
					//TODO : Call to Function to add values to the fields					
					objClientDefAct.AddNewField(driver, webWait, ApplFields[intCnt], TestString_200);
					
					Thread.sleep(1000);
					List<WebElement> strEditField = driver.findElement(By.name("select-box")).findElements(By.tagName("option"));
					Log.info("List Size : "+strEditField.size());
					
					strEditField.get(0).click();
					Thread.sleep(1000);
					//TODO : Call to Function to edit values to the fields	
					objClientDefAct.UpdateField(driver, webWait, TestString_79);
				
					Thread.sleep(1000);
					//Returns to the previous screen
					objClientDef.fn_ReturnPrevScreen(driver, webWait).click();
						
					for(int j=0;j<intlen;j++){  
									
						 if (ApplFields[intCnt] == fields[j]) {
							 isFound = 1;
							 	
						 }
						 
					}
					
					if (isFound == 0){
						Log.info("New Field added : "+ApplFields[intCnt]);
					}
				 }
				 
			/*}
			else{
				Log.error("ERROR : Account Not Found");
			}*/
		}
		 catch(InterruptedException exception){
			 Log.error("ERROR : WebTable not Found "+exception.toString());
			  exception.printStackTrace();
			 
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
		 
		
	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //Logs out of the application
		 AriaLoginLogout.appLogout(driver); 
		 //Quitting the driver
		 driver.quit(); 
	 }
	 
	 //Reading data for the test
	@DataProvider(name = "objectTestData1")
	 public Object[][] testcasedata() throws DataDrivenFrameworkException
	 {
	      Utils objUtils=new Utils();
	      return objUtils.data("TestCaseDetails", "ChangeCustomAccountFields");
	 }	

}
	
