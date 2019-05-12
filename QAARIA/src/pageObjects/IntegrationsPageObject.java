	/*
	 Author     		:	Abhishek
	 Class Name			: 	CountriesPageObject
	 Purpose     		: 	Purpose of this file is :
							1. Page Objects to Validate the Countries
													
	 Date       		:	05/20/2016

	 Version Information:	Version 1.0
	 */
package pageObjects;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	
	public class IntegrationsPageObject {
		
		private static WebElement webElement = null;
			
		//TODO: This is to get the Configuration Link in left navigation.
		public WebElement fn_ConfigurationLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Configuration")));
			return webElement;
		}
		
		//TODO: This is to get the Integrations Link in left navigation.
		public WebElement fn_IntigrationsLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Integrations")));
			return webElement;
		}
		
		//TODO: This is to get the Tax Service Groups Link in left navigation.
		public WebElement fn_TSGroupLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Tax Service Groups")));
			return webElement;
		}
		
		//TODO: This is to get the Tax Config Link Link in left navigation.
		public WebElement fn_TaxConfigLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Taxation Configuration")));
			return webElement;
		}
	}