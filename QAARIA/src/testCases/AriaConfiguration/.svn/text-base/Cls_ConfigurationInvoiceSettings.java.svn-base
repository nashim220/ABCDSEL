/*
 Author     		:	Namrata Akarte
 Edited				:	Abhishek
 Class Name			: 	Cls_ConfigurationInvoiceSettings  
 Purpose     		: 	Purpose of this file is :
						1. To identify the page objects for the Payment Settings in Configuration.
												
 Date       		:	08/14/2015
 Edited Date		:	05/09/2016
 Edited Date		:	05/26/2016
 Version Information:	Version 1.0
 
 Jire #				:	QAABE-217
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "InvoiceSettings" 
 						worksheet for excel "TestData.xlsx".

 Test Steps 		:	1. Login using valid role credentials for verifying the Configuration Payment Settings.
 						2. Navigate to the Configuration -> Billing -> Invoice Settings.
 						3. Read the table on the landing page and compare it with the expected data mentioned in the
 						"InvoiceSettings" worksheet for excel "TestData.xls".
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/
package testCases.AriaConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import appModules.SelectBrowser;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;

import pageObjects.ConfigurationInvoiceSettings;
import testCases.BaseTestCase;
import utility.*;
import appModules.AriaLoginLogout;

public class Cls_ConfigurationInvoiceSettings extends VerificationMethods
{
	public static WebDriver driver;
	public WebDriverWait webWait;
	public String strAuthenticationKey;
	
	@BeforeTest
	public void beforeMethod()throws Exception
	 {	
		//TODO: Initialize the Logger.
    	DOMConfigurator.configure("log4j.xml");	

		//TODO : Set Chrome Driver
		 SelectBrowser objBrowser = new SelectBrowser();
		 driver = objBrowser.initDriver("Chrome");
		 Log.info("initializing driver...");
		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.startTestCase("Login to application");
		//Logs in to the aria application with the appropriate credentials		 	
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);
	
	 }
	
	@Test/*(dataProvider="objectTestData", description="Configuration Invoice Settings")*/
	public void fn_ConfigurationInvoiceSettings(){
		
		String strTestCaseName = "Verify Configuration Settings";
		webWait = new WebDriverWait(driver,1000);
		//Read POM ConfigurationInvoiceSettings	
		
		
		ConfigurationInvoiceSettings objConfigurationInvoiceSettings = new ConfigurationInvoiceSettings();
		
		try {
						
			
			//TODO: Read the Payment Settings file for data comparison and verification.
			String strInvoiceSettingsDataFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
					 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
					 + "TestData.xlsx";
			File fileInvoiceSettings = new File(strInvoiceSettingsDataFilePath);
			
			//TODO : Read Test Excel
			List<HashMap<String,String>> ExcelFileList = ReadFiles.readExcelFile(fileInvoiceSettings, "InvoiceSettings");
			Map<String, List<String>> ExcelMap = new HashMap<String, List<String>>();
			List<String> ExcelListValues ;
			String PNameExcelkey = "";
			String GNameExcelkey = "";
			String ValueExcelkey = "";
			String DescExcelkey = "";
			for(Map<String, String> map : ExcelFileList)
		    {
				ExcelListValues = new ArrayList<String>();
				PNameExcelkey = map.get("Parameter Name");
		        GNameExcelkey = map.get("Group Name");
		        ExcelListValues.add(GNameExcelkey);
		        ValueExcelkey = map.get("Value");
		        ExcelListValues.add(ValueExcelkey);
		        DescExcelkey = map.get("Description");
		        ExcelListValues.add(DescExcelkey);
		        if(PNameExcelkey != "" )
				{
		        	ExcelMap.put(PNameExcelkey, ExcelListValues);
				}
		    }
			System.out.println("Number of Parameters in Excel : "+ExcelMap.keySet().size());
			
			//TODO: Click on Configuration link in the left navigation:
			objConfigurationInvoiceSettings.fn_ConfigurationLink(driver, webWait).click();
			
			//TODO: Click on Billing Link in the left navigation:
			objConfigurationInvoiceSettings.fn_BillingLink(driver, webWait).click();
			
			//TODO: Click on Invoice Settings link in the left navigation:
			objConfigurationInvoiceSettings.fn_InvoiceSettingsLink(driver, webWait).click();
			
			Thread.sleep(5000);
			//Read the Table from Application
			//Find the Total Number of Pages
			WebElement tableinfo = driver.findElement(By.xpath("//*[@id=\"DataTables_Table_0_info\"]"));
			String info = tableinfo.getText();
			int totalNumOfPages = Integer.parseInt(info.substring(info.length() - 2));
			int numOfEntriesPerPage = 50;
			int numOfPages = totalNumOfPages/numOfEntriesPerPage; 
			
			Map<String, List<String>> Tablemap = new HashMap<String, List<String>>();
			List<String> valSetOne ;
			String hmKey = "";
			String hmVal = "";
			String ParameterNameKey="";
			for(int k=0;k<=numOfPages;k++){
				System.out.println("Fetching Values from Page : "+(k+1));
					
				WebElement table = driver.findElement(By.id("DataTables_Table_0"));
				BaseTestCase objTable = new BaseTestCase();
				List<HashMap<String,String>> TableValues = objTable.getFullTableHash(table);
				for(int i=0; i<TableValues.size();i++){
		        	Set<String> TableKey = TableValues.get(i).keySet();
					Iterator TableKeyit = TableKey.iterator();	
					valSetOne = new ArrayList<String>();
					while (TableKeyit.hasNext()){
							hmKey = (String)TableKeyit.next();
							hmVal = (String)TableValues.get(i).get(hmKey);
							if(hmKey.equalsIgnoreCase("Parameter Name")){
								ParameterNameKey = hmVal;
							}
							if(hmKey.equalsIgnoreCase("Description"))
							{
								valSetOne.add(hmVal);
							}
							if(hmKey.equalsIgnoreCase("Group Name"))
							{
								valSetOne.add(hmVal);
							}
							if(hmKey.equalsIgnoreCase("Value"))
							{
								valSetOne.add(hmVal);
							}
							
						}
					if(ParameterNameKey != "" )
					{
						Tablemap.put(ParameterNameKey, valSetOne);
					}
				}
				objConfigurationInvoiceSettings.fn_NextLink(driver, webWait).click();
				Thread.sleep(2000);
			}
			System.out.println("Number of Parameters in Application : "+Tablemap.keySet().size());
					
			System.out.println("/-************************Compared Values**********************************-/");
			//Compare the two hash maps
			for (String Comparekey : ExcelMap.keySet()) {
			    if (Tablemap.containsKey(Comparekey)) {
			    	System.out.println("#### Parameter Name : "+Comparekey+" ####");
			    	List<String> TableValueList = new ArrayList<>();
			    	List<String> ExcelValueList = new ArrayList<>();
			    	TableValueList = Tablemap.get(Comparekey);
			    	ExcelValueList = ExcelMap.get(Comparekey);
			    	int ctr = 0;
			    	for(String Tablevalue : TableValueList){
			    		for(String ExcelValue : ExcelValueList){
			    			try{
				    			if(ExcelValue.equalsIgnoreCase(Tablevalue)){
									System.out.println("Value Matched : "+ExcelValue);
									ctr++;
				    			}
				    		}
			    			catch(Exception e){
			    				System.out.println("Value Matched : ");
			    			}
			    		}
			    		
			    	}
			    	if(ctr!=3){
			    		System.out.println("*****************************************************************");
			    		System.out.println("All Values Did Not Match for Parameter : "+Comparekey);
			    		System.out.println("*****************************************************************");
			    	}
			    }
			    else{
			    	System.out.println("*************PARAMETER NOT FOUND IN APPLICATION******************");
			    	System.out.println("*************** "+Comparekey+" ****************");
			    	System.out.println("*****************************************************************");
			    }
			}
			System.out.println("/-***************************************************************************-/");
						
				
		}
		catch (Exception exception) {
			// TODO Auto-generated catch block
			Log.error("The Verification for Payment Settings couldn't be done due to the exception thrown as: "+exception.toString());
    		Reporter.log("ERROR: The Verification for Payment Settings couldn't be done due to the exception thrown as: "+exception.toString());
    		exception.printStackTrace();
		}
		//TODO: Log the End of the Test Case.
		Log.endTestCase(strTestCaseName);
	}
	
	
	

	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
	
}
