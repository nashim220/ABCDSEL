/*
 Author     		:	Aashish Bhutkar
 Modified By		:	Aashish Bhutkar
 
 Class Name			: 	AccountsStatementsInvoices 
 Purpose     		: 	Purpose of this file is :
						1. To capture all the page objects for the Statements & Invoices Functionality of an Account.
 
 Date       		:	05/02/2016
 Modified Date		:	06/16/2016
 Version Information:	2.0
 
 PreCondition 		:	Role based Login required.
 Test Steps 		:	1. Capture the page objects for the objects that will be used/reused from Statements & Invoices for Accounts.

 Version Changes 2.0:	1. Modifying the structure of the file.   						
   						
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

public class AccountsStatementsInvoices {
	
	WebElement webElement;	
	
	/*******************************	End of Page Objects for Transactions Tab	*******************************/
	
	//To identify Transactions Tab.
	public WebElement fn_clickTransactionsTab(WebDriver driver,WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Transactions')]")));
		return webElement;
	}	
	
	//to identify the search bar on the Statements Page: //*[@id="DataTables_Table_8_filter"]/label/input
	public WebElement fn_getStatementSearch(WebDriver driver,WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']")));
		return webElement;
	}	
	
	//to identify the Void Invoice Link on Invoice details landing page.
	public WebElement fn_clickVoidInvoice(WebDriver driver,WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Void invoice']")));
		return webElement;
	}
	
	/*******************************	End of Page Objects for Transactions Tab	*******************************/
	
	/*******************************	Page Objects for Statements Tab	*******************************/
	
	//to identify Statements Tab
	public WebElement fn_clickStatementsTab(WebDriver driver,WebDriverWait webWait) throws InterruptedException	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Statements')]")));
		return webElement;
	}
	
	//to identify Generate Statement link on Statements page.
	public WebElement fn_clickGenerateStatement(WebDriver driver,WebDriverWait webWait) throws InterruptedException	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Generate Statement")));
		return webElement;
	}	
	
	//to identify the Generate Statement button for the generating statement.
	public WebElement fn_clickGenerateStatementButton(WebDriver driver, WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='button' and @value='Generate Statement']")));
		return webElement;
	}
	
	public Select fn_clickSelectMPI (WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='inMPINo']")));
		Select objSelectField = new Select(webElement);	
		
		return objSelectField;
	}	
	
	//to identify if Statement needs to be email'ed or not radio buttons.
	//1 Option = Do not send email.
	//2 Option = Send Email.
	public WebElement fn_selectEmailOptions(WebDriver driver,WebDriverWait webWait, String strOption) throws InterruptedException {
	
		String strOnClickAction = "//input[@id='inSendEmailInd']["+strOption+"]";		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strOnClickAction)));
		return webElement;
	}

	//to identify the Message after clicking the Generate Statement Button. 
	public WebElement fn_getFlaggedMessage(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='accountsSectionBottomContainer']/div/div/div[3]")));
		return webElement;
	}
	
	/*******************************	End of Page Objects for Statements Tab	*******************************/	
	
	/*******************************	Page Objects for Invoices Tab	*******************************/
    
	//To identify Invoices Tab.
	public WebElement fn_clickInvoicesTab(WebDriver driver,WebDriverWait webWait) throws InterruptedException {
		
	  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Invoices')]")));
	  return webElement;
	}

	//To identify GenerateInvoices Link.
	public WebElement fn_clickGenerateInvoicesLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException {
		
	  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Generate Invoice']")));
	  return webElement;
	}   	
    
    //To Identify Generate Invoice Button.
    public WebElement fn_GenerateInvoiceButton(WebDriver driver,WebDriverWait webWait) throws InterruptedException {
    	
 	   webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Generate Invoice']")));
 	   return webElement;
    } 
	
	//to identify the View & Print link on the Invoice's details page.
	public WebElement fn_clickViewPrint(WebDriver driver,WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@title,'View & Print this Invoice')]")));
		return webElement;
	}
	
	//to identify the Invoice Number of the current Invoice.
	public WebElement fn_getInvoiceNumber(WebDriver driver,WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='invoiceNo']")));
		return webElement;
	}	
	
	//to identify the Approve link.
	public WebElement fn_clickApprove(WebDriver driver, WebDriverWait webWait) throws InterruptedException	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Approve")));
		return webElement;
	}
	
	//to identify the Approve Button.
	public WebElement fn_clickApproveButton(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@value,'Approve')]")));
		return webElement;
	}	
	
	/*******************************	End of Page Objects for Invoices Tab	*******************************/	
	
}