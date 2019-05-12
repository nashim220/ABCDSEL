/*
 Author     		:	Namrata Akarte
 Class Name			: 	Cls_ValidateSequenceGroup  
 Purpose     		: 	Purpose of this file is :
						 To validate if Sequence Group can select Functional Group as IE												
 Date       		:	12/21/2015
 Version Information:	Version 1.0
 
 Jira #				:	QAABE-520
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "TestCaseDetails" 
 						worksheet for excel "TestData.xlsx" in 'SequenceGroup'. 				

 Test Steps 		:	1. Search an existing account
 						2. Navigate to Accounts -> Sequence Group tab
 						3. Set Sequence Function Group field as IE
 						
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/	

	package testCases.AriaConfiguration;	
	
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
	import pageObjects.AccountSequenceGroup;
	import utility.EnvironmentDetails;
	import utility.Log;
	import utility.Utils;
	import appModules.AriaLoginLogout;
	import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
	import atu.ddf.exceptions.DataDrivenFrameworkException;

	public class Cls_ValidateSequenceGroup {
		
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
	    public void fn_ValidateSequenceGroup(String strNewAccountID, String strTestCaseName) {
	    	
	    	Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
	    	AccountSequenceGroup objAccountSequenceGroup = new AccountSequenceGroup(); 	
	    	
	    	
			try {	
				
				Log.startTestCase(strTestCaseName);
								
				objClientDefAct.AccountSearch(driver, webWait, strNewAccountID);
				
				Thread.sleep(3000);
				//TODO : Click on the Sequence Group
				objAccountSequenceGroup.fn_clickSequenceGroupTab(driver, webWait).click();
				
				Thread.sleep(2000);
				//TODO : Click on Edit Fields Button
				objAccountSequenceGroup.fn_clickEditFieldBtn(driver, webWait).click();
								
				//TODO : Select value from the list
				objAccountSequenceGroup.fn_selectFunctionalGroup(driver, webWait).sendKeys("IE");
							
				//TODO : Click on the Save Changes button
				objAccountSequenceGroup.fn_ClickSaveChangesBtn(driver, webWait).click();
								
				Utils.takeScreenshot(driver, strTestCaseName);
				
				String strMsg = driver.findElement(By.xpath("//div[@class='error-box']//p")).getText();
				Utils.takeScreenshot(driver, strTestCaseName);
				
				Log.info("Message is : "+strMsg);
				
				if (strMsg.equalsIgnoreCase("Sequence func group information has been successfully updated.")){
					
					Log.info("Sequence group is updated successfully");
					Reporter.log("Sequence group is not updated successfully");
					
				}
				else{
					
					Reporter.log("Sequence group is not updated successfully");
					throw new AssertionError();
					
				}
				
				//TODO : Resetting the field value for the next run
				//TODO : Click on the Sequence Group tab
				objAccountSequenceGroup.fn_clickSequenceGroupTab(driver, webWait).click();
				
				//TODO : Click on Edit Fields Button
				objAccountSequenceGroup.fn_clickEditFieldBtn(driver, webWait).click();
								
				//TODO : Select value from the list
				objAccountSequenceGroup.fn_selectFunctionalGroup(driver, webWait).sendKeys("{no value}");
							
				//TODO : Click on the Save Changes button
				objAccountSequenceGroup.fn_ClickSaveChangesBtn(driver, webWait).click();
				
				Log.endTestCase(strTestCaseName);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			catch (AssertionError assertionerror)
	    	{
	    		Log.error("Sequence Group is not updated");
	    		Reporter.log("ERROR: Sequence Group is not updated for the account "+strNewAccountID);
	    		
	    	}
	    	
	    }
	    
	 	//Reading data for the test 	
	    @DataProvider(name = "objectTestData1")
	    public Object[][] testExcelData1() throws DataDrivenFrameworkException
		 {
			 Utils objUtils=new Utils();
			 return objUtils.data1("TestCaseDetails", "SequenceGroup");
		 } 
	    
	    
	   @AfterClass
		public void afterMethod() throws Exception
		{
	    	//TODO: Logs out of the application & quit the driver
			AriaLoginLogout.appLogout(driver);
			driver.quit(); 
		}
}
