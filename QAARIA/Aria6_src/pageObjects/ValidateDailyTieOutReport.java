/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	ValidateDailyTieOutReport
 Purpose     		: 	Purpose of this file is :
						1. To identify the various Page Objects required for Cls_ValidateDailyTieOutReport.

 Date       		:	12/09/2015, 10/21/2015
 Modified By		:	Aashish Bhutkar
 Version Information:	Version 2.0
 
 PreCondition 		:	1. Role based Login required. 
 
 Version Changes 2.0:	1. Added page object to identify the 'Analytics and Reporting' link.	
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/


package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ValidateDailyTieOutReport 
{
	
	WebElement webElement;
	
	//to identify the Accounts link in the left nav bar.
	public WebElement fn_clickAccounts(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Accounts")));
		return webElement;
	}
	
	//to identify the Select Dates by Calendar Link. 
	public By fn_clickSelectDateByCalendar(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		return By.xpath("/html/body/table[2]/tbody/tr/td[1]/a");		 
	}
	
	//to identify the Start Date Calendar Control.
	public By fn_clickStartDateCalendar(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		return By.xpath("//input[@name='report_start_dt']");
	}

	//to identify the End Date Calendar Control.
	public By fn_clickEndDateCalendar(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		return By.xpath("//input[@name='report_end_dt']");		
	}
	
	//to identify the Month Label on the Calendar Control.
	public WebElement fn_getCalendarCurrentMonth(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/table[1]/tbody/tr/td/table/tbody/tr[1]/td/table/tbody/tr/td[2]")));
		return webElement;
	}
	
	//to identify the Previous Month changing controls on the Calendar Control.
	public WebElement fn_clickCalendarPrevMonth(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='previous month']")));
		return webElement;
	}
	
	//to identify the Next Month changing controls on the Calendar Control.
	public WebElement fn_clickCalendarNextMonth(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='next month']")));
		return webElement;
	}
	
	//to identify the Previous Year changing controls on the Calendar Control.
	public WebElement fn_clickCalendarPrevYear(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='previous year']")));
		return webElement;
	}
	
	//to identify the Previous Year changing controls on the Calendar Control.
	public WebElement fn_clickCalendarNextYear(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='next year']")));
		return webElement;
	}
	
	//to identify the Click View Large Report link.
	public WebElement fn_clickViewLargeReport(WebDriver driver) throws InterruptedException
	{
		WebDriverWait webWait;
		webWait = new WebDriverWait(driver, 60);
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/center/a")));
		return webElement;			
	}
	
	//to identify the Analytics and Reporting link in the left nav bar.
	public WebElement fn_clickAnalyticsReporting(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Analytics and Reporting")));
		return webElement;
	}
}
