/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_VerifyGLLocationSegment 
 Purpose     		: 	Purpose of this file is :
						1. To Validate GL Base codes for Ireland.
						2. This is automation of TC: QAABE-524.

 Date       		:	12/21/2015
 Version Information:	Version 1.0
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" worksheet & "IrelandBaseGLCodes"  
 						for excel "TestData.xls".

 Test Steps 		:	1. Login using valid role credentials for Navigating to Configuration.
 						2. Navigate to Configuration -> Billing -> Chart of Accounts.
 						3. Validate the Values for the COA ID, COA Code & Description. 						
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AriaConfiguration;

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

import pageObjects.ValidateBaseGLCodesIreland;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;

public class Cls_ValidateBaseGLCodesIreland extends VerificationMethods{
	
    public static WebDriver driver;
    public static String strTestCaseName = "Validate Base GL Codes for Ireland";
	public static WebDriverWait webWait;
    public static WebElement webElement;
    
    @BeforeClass
    public void beforeMethod()throws Exception
    {	
    	//TODO: Initialize the Logger.
    	DOMConfigurator.configure("log4j.xml");	

		//TODO: Set Chrome driver as the driver to launch the test.
		driver = utility.Utils.fn_SetChromeDriverProperties();
		
		//TODO: initialize the webWait
		webWait = new WebDriverWait(driver, 60);
		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.info("Login to Aria Application");
		//TODO: This parameters are taken from Exec file and its reference is available in EnvironmentDetails
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
    }
    

/*    
    Function fn_ValidateGLCodes: This is the actual Test NG test for Validating the Base GL Codes for Ireland.
*/
	@Test(dataProvider = "objectTestData", description = "Validate Base GL Codes for Ireland")    
    private void fn_ValidateGLCodes(String strAccountsReceivable, String strAROtherRefunds, String strPublicCloudRevenue, 
    		String	strUnearnedRevenue, String strVAT) throws Exception{
		
		//TODO: Create object for the classes to be used for accessing reusable codes.
		ValidateBaseGLCodesIreland objGLCodeIreland = new ValidateBaseGLCodesIreland();
		Boolean blnAccountsReceivable = false;
		Boolean blnAROtherRefunds = false;
		Boolean blnPublicCloudRevenue = false;
		Boolean blnUnearnedRevenue = false;
		Boolean blnVAT = false;

    	//TODO: Log the beginning of the test case.
    	Log.startTestCase(strTestCaseName);
    	
    	try{
    		
    		objGLCodeIreland.fn_clickConfiguration(driver, webWait).click();
    		objGLCodeIreland.fn_clickBilling(driver, webWait).click();
    		objGLCodeIreland.fn_clickChartOfAccounts(driver, webWait).click();
    		objGLCodeIreland.fn_getChartOfAccountsTable(driver, webWait);                                         
    		
    		//TODO: Read the Chart of Accounts data table and validate the preset values for Ireland.
    		Thread.sleep(2500);
    		WebElement webChartofAccountsTable = objGLCodeIreland.fn_getChartOfAccountsTable(driver, webWait).findElement(By.tagName("tbody"));
    		List<WebElement> lstwebChartOfAccountsRows = webChartofAccountsTable.findElements(By.tagName("tr"));
    		Utils.takeScreenshot(driver, strTestCaseName);
    		for(WebElement rows : lstwebChartOfAccountsRows){
    			
    			List<WebElement> cols = rows.findElements(By.tagName("td"));
    			
    			String strCOACode = cols.get(1).getAttribute("innerText").toString().trim();
    			String strDescription = cols.get(2).getAttribute("innerText").toString().trim();
    			
    			switch (strDescription){
    			
	    			case "Accounts Receivable":
	    				if(strCOACode.contentEquals(strAccountsReceivable)){
	    					
	    					Log.info("Successfully validated the base GL Code for Ireland for '"+strDescription
	    							+"' with Code value as: "+strCOACode+" !");
	    					blnAccountsReceivable = true;
	    				}
	    				
	    			case "AR Other - used for Refunds":
	    				if(strCOACode.contentEquals(strAROtherRefunds)){
	    					
	    					Log.info("Successfully validated the base GL Code for Ireland for '"+strDescription
	    							+"' with Code value as: "+strCOACode+" !");
	    					blnAROtherRefunds = true;
	    				}
	    				
	    			case "Public Cloud Revenue":
	    				if(strCOACode.contentEquals(strPublicCloudRevenue)){
	    					
	    					Log.info("Successfully validated the base GL Code for Ireland for '"+strDescription
	    							+"' with Code value as: "+strCOACode+" !");
	    					blnPublicCloudRevenue = true;
	    				}
	    				
	    			case "Unearned Revenue":
	    				if(strCOACode.contentEquals(strUnearnedRevenue)){
	    					
	    					Log.info("Successfully validated the base GL Code for Ireland for '"+strDescription
	    							+"' with Code value as: "+strCOACode+" !");
	    					blnUnearnedRevenue = true;
	    				}
	    				
	    			case "VAT":
	    				if(strCOACode.contentEquals(strVAT)){
	    					
	    					Log.info("Successfully validated the base GL Code for Ireland for '"+strDescription
	    							+"' with Code value as: "+strCOACode+" !");
	    					blnVAT = true;
	    				}
    			}
    		}
    		
    		if(blnAccountsReceivable == false) 
    			assertTrue(false, "ERROR: Failed to validate the Base GL Codes of Accounts Receivable for Ireland.");
    		if(blnAROtherRefunds == false) 
    			assertTrue(false, "ERROR: Failed to validate the Base GL Codes of AR Other - used for Refunds for Ireland.");
    		if(blnPublicCloudRevenue == false)
    			assertTrue(false, "ERROR: Failed to validate the Base GL Codes of Public Cloud Revenue for Ireland.");
    		if(blnUnearnedRevenue == false)
    			assertTrue(false, "ERROR: Failed to validate the Base GL Codes of Unearned Revenue for Ireland.");
    		if(blnVAT == false)
    			assertTrue(false, "ERROR: Failed to validate the Base GL Codes of VAT for Ireland.");
    	}
    	catch (Exception exception){
    		
    		Utils.takeScreenshot(driver, strTestCaseName);
    		Log.error("ERROR: Validaation for the Base GL Codes for Ireland couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Validaation for the Base GL Codes for Ireland couldn't be done due to exception:  "+exception.toString());
    		throw exception;
    	}
    	catch (AssertionError exception){
    		
    		Utils.takeScreenshot(driver, strTestCaseName);
    		Log.error("ERROR: Validaation for the Base GL Codes for Ireland couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Validaation for the Base GL Codes for Ireland couldn't be done due to exception:  "+exception.toString());
    		throw exception;
    	}

    	//TODO: Log the end of the test case. 
    	Log.endTestCase(strTestCaseName);
	}

	
	//TODO: Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "IrelandBaseGLCodes");
	}
	
	
	@AfterClass
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
}
