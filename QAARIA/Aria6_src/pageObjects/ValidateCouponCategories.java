/*
 Author     		:	Aashish Bhutkar
 
 Class Name			: 	ValidateCouponCategories
 
 Purpose     		: 	Purpose of this file is :
						1. To identify the various Page Objects required for Cls_ValidateCouponCategories.

 Date       		:	12/22/2015
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

public class ValidateCouponCategories {
	
	WebElement webElement;
	
	//to identify the Marketing menu link in the left nav. 
	public WebElement fn_clickMarketing(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Marketing")));
		return webElement;
	}
	
	//to identify the Coupon Groups menu link in the left nav. 
	public WebElement fn_clickCouponGroups(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Coupon Groups")));
		return webElement;
	}
	
	//to identify the Coupon Groups / Discount Bundles table. 
	public WebElement fn_getTable(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'DataTables_Table_')]")));
		return webElement;
	}
	
	//to identify the Group Name input field. 
	public WebElement fn_getGroupName(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@name,'CouponGroup[group_name]')]")));
		return webElement;
	}
	
	//to identify the Coupons Table. 
	public WebElement fn_getCouponsListTable(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='coupons']")));
		return webElement;
	}
	
	//to identify the Close button on Coupon Groups Details. 
	public WebElement fn_clickClose(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Close']")));
		return webElement;
	}
	
	//to identify the Discount Bundles menu link in the left nav. 
	public WebElement fn_clickDiscountBundles(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Discount Bundles")));
		return webElement;
	}
	
	//to identify the Discount Bundle Name input field. 
	public WebElement fn_getBundleName(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='DiscountBundlesEdit']/div[2]/span[1]/h3")));
		return webElement;
	}
	
	//to identify the Rules tab for Discount Bundle. 
	public WebElement fn_clickRulesTab(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Rules']")));
		return webElement;
	}
	
	//to identify the Rules table for Discount Bundle. 
	public WebElement fn_getRulesTable(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='simple']")));
		return webElement;
	}
	
	//to identify the Close button on Discount Bundle's Rules page. 
	public WebElement fn_clickRulesClose(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@title='Close this section']")));
		return webElement;
	}
	
	//to identify the Percentage Dialog Box. 
	public WebElement fn_getPercentageDialog(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@aria-describedby, 'dialog_p_')]")));
		return webElement;
	}	
	
	//to identify the Percentage Dialog Box's value. 
	public WebElement fn_getPercentageValue(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@id, 'dialog_p_')]")));
		return webElement;
	}
	
	//to identify the OK button. 
	public WebElement fn_clickOK(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Ok']")));
		return webElement;
	}
}
