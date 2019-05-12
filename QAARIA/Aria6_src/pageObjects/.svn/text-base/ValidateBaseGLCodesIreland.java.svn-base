/*
 Author     		:	Aashish Bhutkar
 
 Class Name			: 	ValidateBaseGLCodesIreland
 
 Purpose     		: 	Purpose of this file is :
						1. To identify the various Page Objects required for Cls_ValidateBaseGLCodesIreland.

 Date       		:	12/21/2015
 Version Information:	Version 1.0
 
 PreCondition 		:	1. Role based Login required. 
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ValidateBaseGLCodesIreland {
	
	WebElement webElement;
	
	//to identify the Configuration menu link in the left nav. 
	public WebElement fn_clickConfiguration(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Configuration")));
		return webElement;
	}
	
	//to identify the Billing sub-menu link in the left nav. 
	public WebElement fn_clickBilling(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Billing")));
		return webElement;
	}
	
	//to identify the Billing sub-menu link in the left nav. 
	public WebElement fn_clickChartOfAccounts(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Chart of Accounts")));
		return webElement;
	}
	
	//to identify the Chart Of Accounts Table. 
	public WebElement fn_getChartOfAccountsTable(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'DataTables_Table_')]")));
		return webElement;
	}
}