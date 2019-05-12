/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	AccountPlansUsage 
 Purpose     		: 	Purpose of this file is :
						1. To capture all the page objects required for verification of the Status of Plans associated
						to the Account number in question.
						2. Also the page objects on the Usage sub-menu for Accounts will be identified.
 
 Date       		:	04/01/2016
 Version Information:	Version 1.0
 
 Version Changes 1.1:	1. Updated the Object identification for the Supplemental Plans Table.
 
 PreCondition 		:	Role based Login required.
 
 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/


package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPlansUsage {
	
	public static WebElement webElement = null;
	
	// To identify the Integrations sub-menu in left nav.
	public WebElement fn_clickIntegrations(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Integrations")));
		return webElement;
	}
	
	//To identify the Plans tab.
	public WebElement fn_clickPlansTab(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Plans']")));
		return webElement;
	}
	
	//To identify the From Date textbox.
	public WebElement fn_getFromDateTextBox(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name = 'inStartDateString']")));
		return webElement;
	}

	//To identify the Usage Type Drop down field.
	public Select fn_getUsageTypeDropDown(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name = 'inUsageType']")));
		Select selUsageType = new Select(webElement);
		
		return selUsageType;
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
	
	//to identify the Master Plan Instance Drop Down.
	public Select fn_setMPI(WebDriver driver, WebDriverWait webWait) throws InterruptedException{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name = 'inplanInstance']")));		
		Select selMPI = new Select(webElement);
		
		return selMPI;
	}
}
