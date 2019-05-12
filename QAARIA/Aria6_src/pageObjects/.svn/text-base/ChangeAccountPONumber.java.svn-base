/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	ChangeAccountPONumber
 Purpose     		: 	Purpose of this file is :
						1. To identify the various Page Objects required for Cls_ChangeAccountPONumber.

 Date       		:	09/21/2015
 Modified Date		:	01/06/2015
 Version Information:	Version 2.0
 
 PreCondition 		:	1. Role based Login required.
 
 Version Changes 2.0:	1. Added more objects for the Transactions & Statements tabs of Statement & Invoices.

 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/


package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChangeAccountPONumber 
{
	
	WebElement webElement;
	
	//to identify the Accounts sub-menu.
	public WebElement fn_clickAccounts(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Accounts")));
		return webElement;
	}
	
	//to identify the Statements & Invoices Link in Account sub-menu.
	public WebElement fn_clickStatementsInvoice(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Statements & Invoices")));
		return webElement;
	}
	
	//to identify the Data Table for the Statements & Invoices.
	public WebElement fn_getInvoicesDataTbale(WebDriver driver, WebDriverWait webWait) throws InterruptedException
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

	//to identify the View & Print link on the Invoice's details page.
	public WebElement fn_clickViewPrint(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@title,'View & Print this Invoice')]")));
		return webElement;
	}
	
	//to identify the Custom Invoice Status drop down on the Invoice Tab page.
	public WebElement fn_clickCustomInvoiceStatusDropDown(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[contains(@name,'inCustInvStatus')]")));
		return webElement;
	}
	
	//to identify the Notes text field on the Invoice Tab page.
	public WebElement fn_getNotesTextField(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[contains(@name,'inClientNotes')]")));
		return webElement;
	}	
	
	//to identify the Save button on the Invoice Tab page.
	public WebElement fn_clickSave(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@value,'Save')]")));
		return webElement;
	}
	
	//to identify the message show after Saving the changes done to the Custom Invoice Status field.
	public WebElement fn_getStatusMessage(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{//*[@id="accountsSectionBottomContainer"]/div/div/div[3]/p
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'error-box')]")));
		return webElement;
	}	
	
	//To identify Transactions Tab.
	public WebElement fn_clickTransactionsTab(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Transactions')]")));
		return webElement;
	}
	
	//to identify the Data Table for the Statements & Transactions.
	public WebElement fn_getDataTbale(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'DataTables_Table_')]")));
		return webElement;
	}
	
	//to identify Statements Tab
	public WebElement fn_clickStatementsTab(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Statements')]")));
		return webElement;
	}
	
	//to identify the search bar on the Statements Page: //*[@id="DataTables_Table_8_filter"]/label/input
	public WebElement fn_getStatementSearch(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']")));
		return webElement;
	}
}