/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	PaymentDetailsFinancialReport 
 Purpose     		: 	Purpose of this file is :
						1. To capture all the page objects required for verification of the Payment Details.
						2. Also the page objects for the Reports Cash Application By Date & 
						Payment Transaction By Amount will be identified.
 
 Date       		:	08/31/2015
 Modified Date		:	11/23/2015
 Version Information:	Version 1.3
 
 PreCondition 		:	Role based Login required.
 
 Version Changes 1.1:	1. Modified the identifiers for couple of objects.
				 1.2:   1. Added identifiers for 'Credits' and 'Create New Credit' Links
 				 1.3:   1. Added identifiers for 'Credits' and 'Create new Recurring Credit' Links
 				 1.4    1. Changed the Xpath for 'Credits' Link
 				 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/


package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentDetailsFinancialReport
{
	public static WebElement webElement = null;
	
	//to identify Cash Application by Date - SGAS report label in left nav tree.
	public By fn_clickCashApplicationDateSGAS() throws InterruptedException
	{
		return By.partialLinkText("Cash Application by Date - SGAS");
	}
	
	//to identify Standard report section locator.
	public By fn_clickStandard() throws InterruptedException
	{
		return By.partialLinkText("Standard");
	}
	
	//to identify the Standard report folder.
	public By fn_clickSGAS() throws InterruptedException
	{		
		return By.partialLinkText("SGAS");
	}
	
	//to identify the Cash Application by Date report label in left nav tree.
	public By fn_clickPaymentTransactionsAccountStandard() throws InterruptedException
	{
		return By.partialLinkText("Payment Transactions by Account");
	}
	
	//to identify the Full Balance Amount for the account.
	public WebElement fn_getFullBalance(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='content-wrapper']/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td[4]")));
		return webElement;
	}
	
	//to identify the Payments & Credits sub menu link for searched Account.
	public WebElement fn_clickPaymentsCredits(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Payments & Credits")));
		return webElement;
	}
	
	//to identify the Payments tab from Payments & Credits.
	public WebElement fn_clickPayments(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Payments")));
		return webElement;
	}	
	
	//to identify the data table for Payments & Refunds Tab.
	public WebElement fn_getDataTable(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id, 'DataTables_Table_')]")));
		return webElement;
	}
	
	//to identify the Record Payment Received link on Payments page.
	public WebElement fn_clickRecordPaymentReceived(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Record Payment Received")));
		return webElement;
	}
	
	//to identify the CSR Comments on Payment Information & Refund Information page.
	public WebElement fn_getCSRComments(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@name, 'inComments')]")));
		return webElement;
	}
		
	//to identify the Reference Code text field of Payment Information page.
	public WebElement fn_getRefCode(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='inExternalRefCode']")));
		return webElement;
	}

	//to identify the Payment Amount type of payment to be done.
	public WebElement fn_getPaymentAmount(WebDriver driver, WebDriverWait webWait, int intAmountType) throws InterruptedException
	{		
		String strXpath = null;
		
		if(intAmountType == 1)
		{	
			strXpath = "//input[@id='doFull'][2]";
		}
		else
		{
			strXpath =  "//input[@id='doFull'][1]";
		}
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strXpath)));
		return webElement;
	}
	
	//to identify the Other Amount text field.
	public WebElement fn_getOtherAmount(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='inAmount']")));
		return webElement;
	}
	
	//to identify the Application Method FIFO.
	public WebElement fn_getFIFOApplicationMethod(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='FIFO']")));
		return webElement;
	}
	
	//to identify the Application Method Specific payment.
	public WebElement fn_getSpecificApplicationMethod(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='SPECIFIED']")));
		return webElement;
	}
	
	//to identify the Post Payment button on Payments Page as well as the Payments Confirmation page.
	public WebElement fn_clickPostPayment(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Post Payment']")));
		return webElement;
	}
	
	//to identify the Message for the Payments done.
	public WebElement fn_getPaymentsStatusMessage(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='accountsSectionBottomContainer']/div/div/div[3]/p")));
		return webElement;
	}
	
	//to identify the message box after payments is to be Voided.
	public WebElement fn_getVoidPaymentsConfirmationBox(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[7]")));
		return webElement;	
	}
	
	//to identify the OK in message after payments is approved.
	public WebElement fn_clickOkVoidPaymentsConfirmationBox(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[7]/div[3]/div/button[2]/span")));
		return webElement;
	}	
	
	//to identify the Refunds tab from Payments & Credits.
	public WebElement fn_clickRefunds(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Refunds")));
		return webElement;
	}
	
	//to identify the Create a New Refund linked text on Refunds page.
	public WebElement fn_clickCreateNewRefund(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Create a New Refund")));
		return webElement;
	}
	
	//to identify the refund line item reversal options.
	public WebElement fn_clickNoLineItemReversal(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='0']")));
		return webElement;
	}
	
	//to identify the Amount to be Refund text field.	
	public WebElement fn_getRefundAmount(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='enter_refund_amt']/dd/input")));
		return webElement;
	}
	
	//to identify the Reason for Refund.	//Good Will
	public WebElement fn_getRefundReasonDropDown(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='inReasonCode']")));
		return webElement;
	}
	
	//to identify the Generate Refund button on Refunds Page and Confirmation page.
	public WebElement fn_clickGenerateRefund(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Generate Refund']")));
		return webElement;
	}
	
	//to identify the Process Refund button on Refunds Page and Confirmation page.
	public WebElement fn_clickProcessRefund(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Process Refund']")));
		return webElement;
	}
	
	//to identify the status message after refund is approved.
	public WebElement fn_getRefundStatusMessage(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='accountsSectionBottomContainer']/div/div/div[3]")));
		return webElement;
	}
	
	//to identify the message box after refund is to be Voided.
	public WebElement fn_getVoidRefundConfirmationBox(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]")));
		return webElement;
	}
		
	//to identify the OK in message after refund is approved.
	public WebElement fn_clickOkVoidRefundConfirmationBox(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div[3]/div/button[2]/span")));
		return webElement;
	}
	
	//to identify the Credits link
	public WebElement fn_clickCredits(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"bottomPaneTabBar\"]/ul/li[5]/a")));
		return webElement;
	}
	
	//to identify Create a New Credit Link
	public WebElement fn_clickCreateNewCreditLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Create a New Credit")));
		return webElement;
	}
	
	//to identify View Recurring Credits Link
	public WebElement fn_clickViewRecurringCreditsLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("View Recurring Credits")));
		return webElement;
	}
	
	//to identify  Create a New Recurring Credit Link
	public WebElement fn_CreateNewRecurringCreditLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Create a New Recurring Credit")));
		return webElement;
	}
	
	//to enter credit amount
	public WebElement fn_EnterCreditAmount(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name("inAmount")));
		return webElement;
	}
	
	//to Select Multiple Option for GenerationFrequency Label
	public WebElement fn_SelectMultiple(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("frequency_0")));
		return webElement;
	}
	
	//to Click on CreateCredit Button
	public WebElement fn_CreateCredit(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("submit-button")));
		return webElement;
	}
	
	//Select Credit Reason
	public Select fn_SelectCreditReason(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		Select webList = new Select(driver.findElement(By.name("inReasonCode"))) ;
		return webList;
	}
	
	//Select Number of credits:
	public Select fn_NumberofCredits(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		Select webList = new Select(driver.findElement(By.name("inFrequencyNo"))) ;
		return webList;
	}
	
	//Select Months between credits
	public Select fn_MonthsBetweenCredits(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		Select webList = new Select(driver.findElement(By.name("inFrequencyInterval"))) ;
		return webList;
	}
	
}
