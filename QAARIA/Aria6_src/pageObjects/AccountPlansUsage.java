/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	AccountPlansUsage 
 Purpose     		: 	Purpose of this file is :
						1. To capture all the page objects required for verification of the Status of Plans associated
						to the Account number in question.
						2. Also the page objects on the Usage sub-menu for Accounts will be identified.
 
 Date       		:	11/06/2015, 08/08/2015
 Version Information:	Version 1.1
 
 Version Changes 1.1:	1. Updated the Object identification for the Supplemental Plans Table.
 
 PreCondition 		:	Role based Login required.
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/


package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPlansUsage {
	
	public static WebElement webElement = null;
	
	// To identify the Analytics & Reporting menu in left nav.
	public WebElement fn_clickAnalyticsReporting(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Analytics and Reporting")));
		return webElement;
	}
	
	// To identify the Integrations sub-menu in left nav.
	public WebElement fn_clickIntegrations(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Integrations")));
		return webElement;
	}
	
	
	//To identify the Plans & Services in Accounts sub menu.
	public WebElement fn_clickPlansServices(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Plans & Services")));
		return webElement;
	}
	
	//To identify the Supplemental Plans tab.
	public WebElement fn_clickSupplementalPlans(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Supplemental Plans")));
		return webElement;
	}
	
	//To identify the Supplemental Plans tab page data table for it's current status.
	public WebElement fn_getSupplementalPlansDataTable(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class, 'data-table ')]")));
		return webElement;
	}
	
	//To identify the Usage sub-menu link.
	public WebElement fn_clickUsage(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Usage")));
		return webElement;
	}
	
	//To identify the From Date textbox.
	public WebElement fn_getFromDateTextBox(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name = 'inStartDateString']")));
		return webElement;
	}

	//To identify the Usage Type Drop down field.
	public WebElement fn_getUsageTypeDropDown(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name = 'inUsageType']")));
		return webElement;
	}
	
	//To identify the Retrieve Usage button.
	public WebElement fn_clickRetrieveButton(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id, 'submit-button')]")));
		return webElement;		
	}

	//To identify the link for downloading usage.
	public WebElement fn_clickDownloadUsage(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Download Usage To Spreadsheet")));
		return webElement;		
	}
	
	//To identify the Usage table.
	public WebElement fn_getUsageDetails(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id, 'DataTables_Table_')]")));
		return webElement;		
	}	
}
