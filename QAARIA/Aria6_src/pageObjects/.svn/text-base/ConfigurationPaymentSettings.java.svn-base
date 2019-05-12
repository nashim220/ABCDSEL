/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	ConfigurationPaymentSettings  
 Purpose     		: 	Purpose of this file is :
						1. To identify the page objects for the Payment Settings in Configuration.
												
 Date       		:	07/27/2015
 Version Information:	Version 1.0
 
 PreCondition 		:	1. Role based Login required.

 Test Steps 		:	1) Capture the page objects for the objects that will be used for Verifying the
 						Payment Settings in Configuration.
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfigurationPaymentSettings
{
	private static WebElement webElement = null;
	
	//TODO: This is to get the Configuration Link in left navigation.
	public WebElement fn_clickConfiguration(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Configuration")));
		return webElement;
	}
	
	//TODO: This is to get the Payments sub-menu link in the left navigation.
	public WebElement fn_clickPayments(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Payments")));
		return webElement;
	}
	
	//TODO: This is to find the the Payment Settings sub-menu under Payments in left navigation.
	public WebElement fn_clickPaymentSettings(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@href, '/ui/app.php/PaymentSettings/index')]")));
		return webElement;
	}
		
	//TODO: This is to identify the data table on the landing page of Payment Settings with its values.
	public WebElement fn_getPaymentSettingsDataTable(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id, 'DataTables_Table_')]")));
		return webElement;//*[@id=\'DataTables_Table_0\']
	}
	
	//TODO: This is to identify the data table's filter  on the landing page of Payment Settings.
	public WebElement fn_getDataTableFilter(WebDriver driver, WebDriverWait webWait)
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id, '_filter')]/label/input")));
		return webElement;//*[contains(@type, 'search'] -- //*[@id=\'DataTables_Table_0_filter\']/label/input
	}
}