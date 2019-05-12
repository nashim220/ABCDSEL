/*
 Author     		:	Namrata Akarte
 Class Name			: 	ProductsServices 
 Purpose     		: 	Purpose of this file is :
						1. To capture all the page objects for the Products Services.
 
 Date       		:	04/20/2016
 Modified Date		:	05/04/2016
 Modified By		:	Aashish Bhutkar
 Version Information:	2.0
 
 PreCondition 		:	Role based Login required.
 Test Steps 		:	1) Capture the page objects for the objects that will be used/reused from Products Services.
 
 Version Changes 2.0:	Added page object for the detailed Services landing page. 
 						
 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/


package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsServices {	
		
	private WebElement webElement = null;
	
	//To Identify SerachField 
	public WebElement fn_SearchField(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']")));
		return webElement;
	}
	
	//To Identify Revenue Account GL List
	public WebElement fn_RevAcctGLList(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("Services_clientCoaCode")));
		return webElement;
	}
	
	//To Identify Usage Type List
	public WebElement fn_UsageTypeList(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("Services_usage_type")));
		return webElement;
	}
	
	//To Identify Usage Type List
	public WebElement fn_AcctRecGLList(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("Services_ar_gl_cd")));
		return webElement;
	}
	
	//To identify Taxable list
	public WebElement fn_TaxableList(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("Services_taxable")));
		return webElement;
	}
		
	//To identify Tax Group List
	public WebElement fn_TaxGroupList(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("Services_tax_groups")));
		return webElement;
	}
		
	//To identify Taxable list
	public WebElement fn_IntegrationsLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Integrations")));
		return webElement;
	}
	
	//To identify Taxable list
	public WebElement fn_TaxServiceGrpLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ui-accordion-1-panel-6']/li[8]/ul/li[7]/a/div")));
		return webElement;
	}
	
	//To identify Service Number
	public WebElement fn_ClickServiceNumber(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"ServicesEdit\"]/div[2]/span[1]/h3")));
		return webElement;
	}
	
	//To identify Services Table
	public WebElement fn_ServicesTable(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'DataTables_Table_')]")));
		return webElement;
	}
	
	//To identify Services Table
	public WebElement fn_clickServicesClose(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='button' and @value='Close']")));
		return webElement;
	}	
}
