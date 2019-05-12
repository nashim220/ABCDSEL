//Author:- Abhishek, Date :- 04-15-2016
//Purpose:- To Validate the Data for all the Fields Under CompanyProfileInformation Section 
package testCases.AriaConfiguration;
//import java.io.File;
//import java.util.HashMap;
//import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testCases.BaseTestCase;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
//import appModules.AriaCompanyProfileInformation;
import appModules.AriaLoginLogout;
import appModules.ValidateAriaCompanyProfileInformation;
import atu.ddf.exceptions.DataDrivenFrameworkException;
//import pageObjects.*;

public class ValidateCompanyProfileInformation extends BaseTestCase
{

	public static String strTestCaseName = "ValidateCompanyProfileInformation";
	@BeforeClass
	 public static void beforeMethod()throws Exception
	 {	
		DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
		System.setProperty("webdriver.chrome.driver",chromeDriverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.startTestCase(strTestCaseName);
		Reporter.log("Logging Into Aria");
		//Aria Login
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);
	 }
	
	@Test(dataProvider="objectTestData", description="Company Profile Information Fields")
	public void fn_CompanyProfileInformation(String strdFileName, String strdWorksheet) throws Exception 
	{
		//,String CompanyInformation
		Log.startTestCase("CompanyProfileInformationFields");
        
		//Initializing wait variable
		//WebDriverWait webWait;
		//webWait = new WebDriverWait(driver,2000);
		
		//String[] fields = CompanyInformation.split("#");
        ValidateAriaCompanyProfileInformation ObjInformation = new ValidateAriaCompanyProfileInformation();
        ObjInformation.fn_AccessCompanyProfileObjects(strdWorksheet);
	}
  
 
@DataProvider(name = "objectTestData")
  public Object[][] data() throws DataDrivenFrameworkException
  {
      Utils objUtils=new Utils();
      return objUtils.data("TestCaseDetails","CompanyProfileInformationFields");
  }

}