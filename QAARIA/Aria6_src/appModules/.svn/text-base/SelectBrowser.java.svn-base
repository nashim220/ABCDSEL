/*
 Author     		: Umesh Kute
 Class Name			: SelectBrowser
 Purpose     		: Purpose of this file is :
						1. To initialize the driver for selected browser
						2. Browser will be selected based on 
							the configuration provided in EnvironmentDetails file
 Date       		: 02/18/2015
 Version Information: Version1.0
 PreCondition 		: Provide the browser to be used for running application in 
 						EnvironmentDetails file
 Test Steps 		:	1) Read the browser type
 						2) Initialize the driver 
 
 Copyright notice	: Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
 */

package appModules;

import java.io.File;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import utility.EnvironmentDetails;
import utility.Log;
import utility.VerificationMethods;

/*
This class is used to initialize the driver for selected browser.
@version: 1.0
@author: Umesh Kute
*/
public class SelectBrowser extends VerificationMethods{
	private static WebDriver driver = null;
	
	//@SuppressWarnings("unused")
	public WebDriver initDriver(String browser) throws Exception{
		//DOMConfigurator.configure("log4j.xml");
//		final String logConfig = new File("Config").getAbsolutePath() + "\\log4j.xml";
//		System.out.println("Log4j xml path is: "+logConfig);
//		DOMConfigurator.configure(logConfig);
		Log.info("Selecting Browser");
		
		String browserType = EnvironmentDetails.browser;
		if(browserType.equals("firefox"))
		{
			Log.info("Browser Selected is: " + browserType);
			driver = new FirefoxDriver();
			Log.info("Firefox driver instantiated..");
			driver.manage().window().maximize();
		}
		else if(browserType.equals("Chrome"))
		{
			Log.info("Browser Selected is: " + browserType);
			
			Log.info(EnvironmentDetails.Path_ChromDriver);

			System.setProperty("webdriver.chrome.driver",
					EnvironmentDetails.Path_ChromDriver);
			driver = new ChromeDriver();
			Log.info("Chrome driver instantiated...");
			driver.manage().window().maximize();
		}
		else if(browserType.equals("IE"))
		{
			Log.info("Browser Selected is: " + browserType);
			
			System.setProperty("webdriver.ie.driver", EnvironmentDetails.Path_IeDriver);
			driver = new InternetExplorerDriver();
			Log.info("IE driver instantiated...");
			driver.manage().window().maximize();
		}
		return driver;
	}
}
