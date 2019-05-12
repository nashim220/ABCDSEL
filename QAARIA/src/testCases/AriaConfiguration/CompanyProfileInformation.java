//Author:- Madhavi JN, Date :- 26-06-2015
//Purpose:- To Enter the Data for all the Fields Under CompanyProfileInformation Section and Validate 
package testCases.AriaConfiguration;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testCases.BaseTestCase;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import appModules.AriaCompanyProfileInformation;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import pageObjects.*;

public class CompanyProfileInformation extends BaseTestCase
{

	public static String strTestCaseName = "Company Profile";
	@BeforeClass
	 public static void beforeMethod()throws Exception
	 {	
		DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
		System.setProperty("webdriver.chrome.driver",chromeDriverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.startTestCase("Login to application");
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);
		
	 }
	
	@Test(dataProvider="objectTestData", description="Company Profile Information Fields")
	//public void fn_CompanyProfileInformation(String username, String password,String CompanyInformation) throws Exception 
	public void fn_CompanyProfileInformation(String strdFileName, String strdWorksheet,String CompanyInformation) throws Exception 
	{
		
		Log.startTestCase("CompanyProfileInformationFields");
        
		//Initializing wait variable
		WebDriverWait webWait;
		webWait = new WebDriverWait(driver,2000);
		
		//Logging into aria application	
        //AriaLoginLogout.appLogin(driver, username, password);	
        
		String[] fields = CompanyInformation.split("#");
        AriaCompanyProfileInformation ObjInformation = new AriaCompanyProfileInformation();
        ObjInformation.fn_AccessCompanyProfileObjects(CompanyInformation);
		
  }
  
 
@DataProvider(name = "objectTestData")
  public Object[][] data() throws DataDrivenFrameworkException
  {
      Utils objUtils=new Utils();
      return objUtils.data("TestCaseDetails","CompanyProfileInformationFields");
  }

}
