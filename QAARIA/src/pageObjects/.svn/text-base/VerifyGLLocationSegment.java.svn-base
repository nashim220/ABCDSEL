/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	VerifyGLLocationSegment
 Purpose     		: 	Purpose of this file is :
						1. To identify the various Page Objects required for Cls_VerifyGLLocationSegment.

 Date       		:	10/27/2015, 09/14/2015, 08/26/2015
 Version Information:	Version 3.0
 
 PreCondition 		:	1. Role based Login required.
 
 Version Changes 2.0:	1. Added few more object identifiers.
 Version Changes 3.0:	1. Added few more object identifiers for generation of Statements after Invoice is created.
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/


package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerifyGLLocationSegment 
{
	
	WebElement webElement;
	
	//to identify the Analytics and Reporting menu link in the left nav. 
	public WebElement fn_clickAnalyticsReporting(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Analytics and Reporting")));
		return webElement;
	}
	
	//to identify the Reports sub-menu for Analytics and Reporting.
	public WebElement fn_clickReports(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Reports")));
		return webElement;
	}
	
	//to identify the Run Reports under the Reports sub-menu.
	public WebElement fn_clickRunReports(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Run Reports")));
		return webElement;
	}
	
	//to identify the frame for the Run Reports.
	public WebElement fn_getRunReportsFrame(WebDriver driver, WebDriverWait webWait)throws InterruptedException
	{		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'ariaGlobalModalPopup\']/div/iframe")));
		return webElement;
	}
	
	//to identify the SGAS folder.
	public By fn_clickSGAS() throws InterruptedException
	{		
		return By.partialLinkText("SGAS");
	}
	
	//to identify the Revenue by GL Code - SGAS report link.
	public By fn_clickRevenueGLCodeSGAS() throws InterruptedException
	{
		return By.partialLinkText("Revenue by GL Code - SGAS");		
	}
	
	//to identify the Switch to Report Preview Mode link.
	public By fn_clickSwitchReportPreviewMode() throws InterruptedException
	{
		return By.cssSelector("body > table:nth-child(7) > tbody > tr > td:nth-child(2) > a");
	}
	
	//to identify the Start Date on report frame.
	public By fn_getStartDateDropDown() throws InterruptedException
	{
		return By.name("report_start_dt");
	}
	
	//to identify the End Date on report frame.
	public By fn_getEndDateDropDown() throws InterruptedException
	{
		return By.name("report_end_dt");
	}
	
	//to identify the Preview Report! button
	public By fn_clickPreviewReport() throws InterruptedException
	{
		return By.xpath("//input[@value='Preview Report!']");
	}
	
	//to identify the Download button if needed.
	public By fn_clickDownload() throws InterruptedException
	{
		return By.xpath("/html/body/table/tbody/tr/td[3]/a");
	}
	
	//to identify the View Full Window linked text.
	public By fn_clickViewFullWindow() throws InterruptedException
	{
		return By.xpath("/html/body/table/tbody/tr/td[2]/a");
	}
	
	//to identify the Close button for the Report frame.
	public By fn_clickReportFrameCloseButton() throws InterruptedException
	{
		return By.xpath("//button[@title='close']");
	}	
    
	//To identify Statements and Invoices LInk
	public WebElement fn_clickStatementsInvoices(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Statements & Invoices")));
		return webElement;
	}	
	
	//To identify Invoices Link
	public WebElement fn_clickInvoices(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Invoices')]")));
		return webElement;
	}
	  	
	//to identify the Invoices table.
	public WebElement fn_getInvoicesDataTable(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'DataTables_Table_')]")));
		return webElement;
	}
	
	//to identify the Approve link.
	public WebElement fn_clickApprove(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Approve")));
		return webElement;
	}
	
	//to identify the Approve Button.
	public WebElement fn_clickApproveButton(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@value,'Approve')]")));
		return webElement;
	}
    
    //to identify the message delivered after Generate Invoice button is clicked.
    public WebElement fn_getInvoiceGnerationMessage(WebDriver driver,WebDriverWait webWait) throws InterruptedException
    {
 	   webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='accountsSectionBottomContainer']/div/div/div[3]/p")));
 	   return webElement;
    }
    
	//to identify Statements Tab
	public WebElement fn_clickStatementsTab(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Statements')]")));
		return webElement;
	}
	
	//to identify Generate Statement link on Statements page.
	public WebElement fn_clickGenerateStatement(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Generate Statement")));
		return webElement;
	}
	
	//to identify Preview Next Statement link on Statements page.
	public WebElement fn_clickPreviewNextStatement(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Preview Next Statement")));
		return webElement;
	}	
	
	//to identify if Statement needs to be email'ed or not radio buttons.
	//0 Option = Do not send email.
	//1 Option = Send Email.
	public WebElement fn_selectEmailOptions(WebDriver driver,WebDriverWait webWait, String strOption) throws InterruptedException
	{
		String strOnClickAction = null;
		
		if(strOption.contentEquals("0"))
			strOnClickAction = "//input[@onclick='toggleAssignOptions(0);']";
		else if (strOption.contentEquals("1"))
			strOnClickAction = "//input[@onclick='toggleAssignOptions(1);']";
		else
			strOnClickAction = "//input[@onclick='toggleAssignOptions(0);']";
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strOnClickAction)));
		return webElement;
	}
		
	//to identify the Generate Statement button for the generating statement.
	public WebElement fn_clickGenerateStatementButton(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Generate Statement']")));
		return webElement;
	}
	
	//to identify the Message after clicking the Generate Statement Button. 
	public WebElement fn_getGenerateStatementMessage(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='accountsSectionBottomContainer']/div/div/div[3]")));
		return webElement;
	}	
}