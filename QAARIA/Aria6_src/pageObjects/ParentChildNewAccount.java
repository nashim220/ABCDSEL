/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	ParentChildNewAccount 
 Purpose     		: 	Purpose of this file is :
						1. To capture all the page objects for the Creation of New Account 
						with Parent Child Relationship.
 
 Date       		:	06/16/2015
 Modified Date		:	07/10/2015
 Version Information:	Version 2.0
 
 PreCondition 		:	Role based Login required.
 Test Steps 		:	1) Capture the page objects for the objects that will be used for 
 						Verifying the Parent Child Relationship.
  
 Version Changes 2.0:	1. Introduced page object based Web wait.
 						2. Changes to the parameters, class and method names as per the standards. 
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/


package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ParentChildNewAccount  
{	
	private static WebElement webElement = null;
	
	public static By fn_clickParentAccount()	// This is the identification of Parent Account Hyper linked label.
	{
		return By.xpath("//*[@id=\'content-wrapper\']/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[9]/td[3]/a");
	}
	
	public static By fn_getAccountHierarchyGlanceDataTable()	// This is the identification of the Account Hierarchy															 
	{														// Glance Data Table.
		return By.xpath("//*[@id=\'content-wrapper\']/table[8]");
	}
/*	
	public static By getAccountHierarchyGlanceDataTableRows()	// This is the identification of the Account Hierarchy															 
	{															// Glance Data Table's row items.
		return By.xpath("//*[@id=\'content-wrapper\']/table[8]/tbody/tr");
	}
*/
	public static By fn_clickManageChildAccounts()	// This is the identification of Manage Child Accounts tab.
	{
		return By.partialLinkText("Manage Child Accounts"); 
		// By.xpath("//*[@id=\'bottomPaneTabBar\']/ul/li[10]/a");
	}
	
	public static By fn_getManageChildAccountsDataTable()	// This is the identification of the data table holding parent child lists.
	{
		return By.xpath("//*[contains(@id, 'DataTables_Table_')]");
	}

	// This is to identify the Account Overview tab.
	public static WebElement fn_clickAccountOverview(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		//element = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'bottomPaneTabBar\']/ul/li[1]/a")));
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Account Overview")));
		return webElement;
	}
	
	// This is to identify the Manage Child Accounts tab.
	public static WebElement fn_clickManageChidlAccounts(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Manage Child Accounts")));
		return webElement;
	}
	
	// This is to identify the Assign Child Account link
	public static WebElement fn_clickAssignChildAccount(WebDriver driver, WebDriverWait webWait)  throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Assign Child Account")));
		return webElement;
	}
	
	// This is to identify the Child Accounts Data table
	public static WebElement fn_getChildAccountsDataTable(WebDriver driver, WebDriverWait webWait)  throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id, 'DataTables_Table_')]")));
		return webElement;
	}

	// This is to identify the text box for child account # to be entered.
	public static WebElement fn_getChildAccountNo(WebDriver driver, WebDriverWait webWait)  throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name("inAcctNo")));				
		return webElement;
	}
	
	// This is to identify the responsibility level to be passed on for the child and parent account relationship.
	public static WebElement fn_clickResponsibilityLevel(WebDriver driver, WebDriverWait webWait, String strResponsibilityLevel) throws InterruptedException
	{	
		String strPath = "//*[@id=\'accountsSectionBottomContainer\']/div/div/form/table/tbody/tr[2]/td[2]/input["+strResponsibilityLevel+"]";
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strPath)));
		return webElement;
	}
	
	// This is to identify the Child Account Button to be clicked for completing addition of child account.
	public static WebElement fn_clickAssignChildAccountButton(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'accountsSectionBottomContainer\']/div/div/form/div/p/input")));
		return webElement;
	}
	
}
