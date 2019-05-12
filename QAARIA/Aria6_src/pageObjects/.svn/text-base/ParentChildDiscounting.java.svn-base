/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	ParentChildDiscounting 
 Purpose     		: 	Purpose of this file is :
						1. To capture all the page objects required for Discounting to be done 
						for Parent & Child Account.
 
 Date       		:	09/25/2015 
 Version Information:	Version 1.0
 
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

public class ParentChildDiscounting 
{
	WebElement webElement;
	
	//to identify the Analytics & Reporting Left Nav.
	public WebElement fn_clickAnalyticsReporting(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Analytics and Reporting")));
		return webElement;
	}
	
	//to identify the Accounts sub-menu.
	public WebElement fn_clickAccounts(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Accounts")));
		return webElement;
	}

	//to identify the Account Hierarchy Glance Table.
	public WebElement fn_getAccountHeirarchyGlanceTable(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='content-wrapper']/table[8]")));
		return webElement;
	}
	
	//to identify the Statements & Invoices Link in Account sub-menu.
	public WebElement fn_clickStatementsInvoice(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Statements & Invoices")));
		return webElement;
	}
	
	//to identify the Data Table for the Statements & Invoices.
	public WebElement fn_getDataTbale(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'DataTables_Table_')]")));
		return webElement;
	}
	
	//To identify Invoices Tab.
	public WebElement fn_clickInvoices(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Invoices')]")));
		return webElement;
	}	
	
	//to identify the Payments & Credits sub menu link for searched Account.
	public WebElement fn_clickPaymentsCredits(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Payments & Credits")));
		return webElement;
	}
		
	//to identify the Coupons Tab.
	public WebElement fn_clickCoupons(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Coupons")));
		return webElement;
	}
	
	//to identify the Assign Coupon linked text.
	public WebElement fn_clickAssignCoupon(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Assign Coupon")));
		return webElement;
	}
	
	//to identify the Validate button for the Coupons.
	public WebElement fn_clickValidate(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@value, 'Validate')]")));
		return webElement;
	}
	
	//to identify the text field for validating the Coupons.
	public WebElement fn_getValidateText(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@name, 'inCouponCd')]")));
		return webElement;
	}
	
	//to identify the Validate button for the Coupons.
	public WebElement fn_clickAssignCouponConfirm(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@value, 'Assign')]")));
		return webElement;
	}
	
	//to identify the Success message after assigning the Coupon.
	public WebElement fn_getCouponAssignMessage(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='accountsSectionBottomContainer']/div/div/div[3]/p")));
		return webElement;
	}
	
	//to identify the OK button on the Confirmation Dialog Box.
	public WebElement fn_clickOKCancelingCoupon(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'uiSaveBtn')]")));
		return webElement;
	}	
}
