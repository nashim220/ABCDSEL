package utility;

import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Browser 
{
	private WebDriver driver;
	public static final String BS_URL = "http://" + Constants.BS_USERNAME + ":" + Constants.BS_AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
	public static final String SL_URL = "http://" + Constants.SL_USERNAME + ":" + Constants.SL_AUTOMATE_KEY + "@ondemand.saucelabs.com:80/wd/hub";
	
	
	/*
	Set up method to lunch browser
	*/
	public WebDriver setUp(String browser, String environment, String OS) throws Exception 
	 {	    
		
		
		//Adding random wait between each thread
		Random t = new Random();
	    Thread.sleep(t.nextInt(15)*1000);
		
		if(environment.equalsIgnoreCase("Local") )
		{
			if(browser.equalsIgnoreCase("firefox"))
			{
				FirefoxProfile profile = new FirefoxProfile();
				profile.setEnableNativeEvents(true);
				driver = new FirefoxDriver(profile);
				 driver.manage().window().maximize();	
			} 
			
			else if(browser.equalsIgnoreCase("ie"))
			{  
				  System.setProperty("webdriver.ie.driver", "src/test/resources/BrowserDrivers/IEDriverServerLatest.exe");
				   driver = new InternetExplorerDriver();
				   driver.manage().window().maximize();
			}  
			
			else if(browser.equalsIgnoreCase("chrome"))
			{
				  System.setProperty("webdriver.chrome.driver", "src/test/resources/BrowserDrivers/chromedriver_2.29.exe");
				   ChromeOptions options = new ChromeOptions();
				   options.addArguments("user-data-dir=C:/New User");
				   options.addArguments("--start-maximized");
				   driver = new ChromeDriver(options);
			}
			else if(browser.equalsIgnoreCase("chrome_EM"))
			{
				  System.setProperty("webdriver.chrome.driver", "src/test/resources/BrowserDrivers/chromedriver_2.30.exe");
				   driver = new ChromeDriver();
					driver.manage().window().maximize();	

			}
		}
		
		else if(environment.equalsIgnoreCase("Grid"))
		{
			if(browser.equalsIgnoreCase("firefox"))
			{
				if(OS.equalsIgnoreCase("Windows 7"))
				{
					DesiredCapabilities capability = DesiredCapabilities.firefox();
					capability.setBrowserName("firefox");
					capability.setPlatform(Platform.WINDOWS);
					driver = new RemoteWebDriver(new URL(Constants.nodeURL1),capability);
				}
				
			}
			
			if(browser.equalsIgnoreCase("firefox_node"))
			{
				if(OS.equalsIgnoreCase("Windows 7"))
				{
					DesiredCapabilities capability = DesiredCapabilities.firefox();
					capability.setBrowserName("firefox");
					capability.setPlatform(Platform.WINDOWS);
					driver = new RemoteWebDriver(new URL(Constants.nodeURL2),capability);
				}
				
			}
			else if(browser.equalsIgnoreCase("ie"))
			{  
				if(OS.equalsIgnoreCase("Windows 7"))
				{
					//System.setProperty("webdriver.ie.driver", "C:/Piyush/IEDriverServer.exe");
					DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
					capability.setBrowserName("safari");
					capability.setPlatform(Platform.WINDOWS);
					driver = new RemoteWebDriver(new URL(Constants.nodeURL1),capability);
				}
			   
			}
			else if(browser.equalsIgnoreCase("chrome"))
			{
			   
			   if(OS.equalsIgnoreCase("Windows 7"))
				{
				   System.setProperty("webdriver.chrome.driver", "-Dwebdriver.chrome.driver=src/test/resources/BrowserDrivers/chromedriver_old.exe");
				//   driver = new ChromeDriver();
					// driver.manage().window().maximize();	
					DesiredCapabilities capability = DesiredCapabilities.chrome();
					capability.setBrowserName("chrome");
					capability.setPlatform(Platform.WINDOWS);
					driver = new RemoteWebDriver(new URL(Constants.nodeURL2),capability);
				}
			   
			   
			}
			
		}
		
		else if(environment.equalsIgnoreCase("BrowserStack"))
		{
			//https://www.browserstack.com/automate/java
			
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("browser", "chrome");
			caps.setCapability("browser_version", "60");
			caps.setCapability("os", "OS X");
			caps.setCapability("os_version", "El Capitan");
			caps.setCapability("resolution", "1024x768");
		    caps.setCapability("browserstack.debug", "true");
		    driver = new RemoteWebDriver(new URL(BS_URL), caps);
		   // driver.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
			
		}
		
		
		else if(environment.equalsIgnoreCase("Saucelabs"))
		{
			//https://docs.saucelabs.com/reference/platforms-configurator/?_ga=1.149449526.1441791307.1424257692#/
		
			DesiredCapabilities caps = DesiredCapabilities.chrome();
			caps.setCapability("platform", "Windows 7");
			caps.setCapability("version", "43.0");
			
			
			/*DesiredCapabilities caps = DesiredCapabilities.firefox();
			caps.setCapability("platform", "Windows 8");
			caps.setCapability("version", "23.0");
			
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability("platform", "Windows 8");
			caps.setCapability("version", "10.0");*/
			
			driver = new RemoteWebDriver(new URL(SL_URL), caps);
			
		}
			
		
		
		
			
		

		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(90, TimeUnit.SECONDS);

		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	   
	 }
	
	/*
	Tear down method to quit browser after test execution
	*/
	public void tearDown() throws Exception 
	  {  
		    try
	        {
		    	Thread.sleep(5000);
		    	driver.close();
	            Thread.sleep(5000);
	            driver.quit();
	        }
	        catch(Exception e)
	        {
	        }
		   
	  }

	
	/*public void intialization()
	{
		
		
	}*/
	
}
