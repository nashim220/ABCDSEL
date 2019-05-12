
package testCases;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import appModules.AccountFunctions;
import appModules.AriaLoginLogout;
import appModules.AriaNewAccountCreation;
import atu.ddf.exceptions.DataDrivenFrameworkException;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;
import pageObjects.NewAccountCreation;
import testCases.AccountTestCases.Cls_ParentChildNewAccountCreation;
import testCases.AccountTestCases.Cls_RuntimeNewParentNewChildAccountCreation;

public class Cls_SECustomerCreation extends VerificationMethods{
	
	public WebDriver driver;

    public static String strTestCaseName = "New Account with Parent Child Relation";
    public static String[] arrSearchFields = null;	// this string array will hold the Fields names for the Client Defined Fields.
    public static String strFieldValue = null; 	// this string will hold the subsequent field value for the Client Defined Fields.
	public static WebDriverWait webWait;
    public static WebElement webElement;

    
	@BeforeClass
	 public void beforeMethod()throws Exception
	 {	
		DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
		System.setProperty("webdriver.chrome.driver",chromeDriverPath);
		driver = new ChromeDriver();
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.startTestCase("Login to application");
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
	 }
	
	@Test(dataProvider = "objectTestData", description = "NewSECustomerAccountCreation")
	public void fn_newacctcreation(String strdFileName, String strdWorksheet, String strFieldNames, String strFieldValues) throws Exception
	{
		arrSearchFields = strFieldNames.split("#");	// read the field names in this list to be referred latter.
		strFieldValue = strFieldValues;	// read the field values which needs to be passed @ runtime.		
    			
		Log.startTestCase(strTestCaseName);	// Log the beginning of the Test Case.
		
		try
		{
			String strNewAccountDataExcelFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
					 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
					 + strdFileName;	// specify path of the excel file with details to add new account.
			File newAccountDataExcelFile = new File(strNewAccountDataExcelFilePath);	// object for the excel file.
			List<HashMap<String,String>> lsthmapFileContents = utility.ReadFiles.readExcelFile(newAccountDataExcelFile, strdWorksheet);
			
			
			// Call for the method to add data to the Create Account Page / Form.
			AriaNewAccountCreation.fn_CreateAccount(driver, lsthmapFileContents, strTestCaseName, arrSearchFields, strFieldValue);
		
			Log.info("New account created successfully with Parent Child Relationship.");
			Log.endTestCase(strTestCaseName);
		}
		catch (Exception exception)
		{
			Log.error("The Test Case New Account with Parent Child Relation has endded prematurely due to failure: "+exception.toString());
			Reporter.log("ERROR: The Test Case New Account with Parent Child Relation has endded prematurely due to failure: "+exception.toString());
        	utility.Utils.takeScreenshot(driver, strTestCaseName);

		}	
		
		AccountFunctions Objacctfnctns = new AccountFunctions();
		//Objacctfnctns.fn_LoadAccountUsage(driver, webWait, strAPIURL, strFilePath, strAcctNum, strDate);
	}
	
    //Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "NewSECustomerAccountCreation");
	}
	
	
	@AfterTest
	public void afterMethod() throws Exception
	 {
		 //Logs out of the application
		 AriaLoginLogout.appLogout(driver); 
		 //Quitting the driver
		 driver.quit(); 
	 }

	}


