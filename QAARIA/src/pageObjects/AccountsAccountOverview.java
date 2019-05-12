/*
 Author     		:	Aashish Bhutkar
 Modified By		:	Aashish Bhutkar
 
 Class Name			: 	AccountsAccountOverview
 Purpose     		: 	Purpose of this file is :
						1. To identify the various Page Objects required for Account Overview Page & various Tabs.

 Date       		:	05/12/2016
 Modified Date		:	06/16/2016
 Version Information:	Version 3.0
 
 PreCondition 		:	1. Role based Login required.
 
 Version Changes 2.0:	1. Added more objects for the Transactions & Statements tabs of Statement & Invoices.
 Version Changes 3.0:	1. Modifying the structure of the file.

 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/


package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountsAccountOverview {
	
	WebElement webElement;	
	
	/*******************************	Page Objects for Account Overview Tab	*******************************/

	// this is to identify the Status Hyperlinking on the Accounts Overview tab page.
	public WebElement fn_clickStatusAccountsOverview(WebDriver driver,WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Status:")));
		return webElement;
	}	
		
	// this is to identify the Status cell value.
	public WebElement fn_verifyStatus(WebDriver driver,WebDriverWait webWait) throws InterruptedException {
	
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//font[text()='Status:']/ancestor::a/following-sibling::b")));
		return webElement;
	}
	
	// to Click on Account Contact Link.
	public WebElement fn_ClickonAcountContactLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement= webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//font[text()='Account Contact:']")));
		return webElement;
	}

	// to identify the Contact Table.
	public WebElement fn_AccountcontactTableLoad(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
	
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='Account Contact']")));
		return webElement;
	}
	
	// to identify the DOB Header.
	public WebElement fn_geUserDataofBiirthHeader(WebDriver driver, WebDriverWait webWait) throws InterruptedException 	{

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//th[text()='Birthdate']")));
		return webElement;
	}

	// to identify the DOB Value.
	public WebElement fn_geUserDataofBiirthVal(WebDriver driver, WebDriverWait webWait) throws InterruptedException 	{

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='inBirthdate']")));
		return webElement;
	}
	
	//To identify the Full Balance  on Accounts Overview Page.
	public WebElement fn_getAcctOvrvwFullBalance(WebDriver driver, WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='content-wrapper']//tr[2]/td/a[contains(text(),'USD')]")));
		return webElement;
	}
	
	/*******************************	End of Page Objects for Account Overview Tab	*******************************/
	
	/*******************************	Page Objects for Account's Status Change	*******************************/

	// this is to identify the Change Account Status link.
	public WebElement fn_clickChangeAccountStatus(WebDriver driver,WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Change Account Status")));
		return webElement;
	}
	
 	// this is to identify the Reset Billing Dates, flexible to identify the type.
	public WebElement fn_clickResetBillingDates(WebDriver driver,WebDriverWait webWait, String strValue) throws InterruptedException {

		String strPath = "//*[@id=\'accountsSectionBottomContainer\']/div/div/form/fieldset/dl/dd[2]/input["+strValue+"]";
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strPath)));
		return webElement;		
	}
	
	// this is to identify the Change Account Status button.
	public WebElement fn_clickChangeAccountStatusButton(WebDriver driver,WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@value, 'Change Account Status')]")));
		return webElement;		
	}

	// this is to identify the Account Status History Data table.
	public WebElement fn_getAccountStatusHistoryDataTable(WebDriver driver,WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='data-table clear-both']")));
		return webElement;
	}
	
	// this is the identification of CSR Comments section.
	public WebElement fn_getCSRComments(WebDriver driver,WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name("inComments")));
		return webElement;
		//By.xpath("//*[@id=\'accountsSectionBottomContainer\']/div/div/form/fieldset/dl/dd[3]/textarea");
	}	
	
	// this is to identify the Status Change drop-down.
	public WebElement fn_clickStatusChange(WebDriver driver,WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("inQueueDays")));
		return webElement;
	}
	
	// this is to identify the Status Table.
	public WebElement fn_getStatusDataTable(WebDriver driver,WebDriverWait webWait) throws InterruptedException {
	
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='simple']")));
		return webElement;
	}
	
	/*******************************	End of Page Objects for Account's Status Change	*******************************/	

	/*******************************	Page Objects for Taxpayer Information Tab	*******************************/
	
	//to identify the Taxpayer Information tab on the TaxPayer Information page.
	public WebElement fn_clickTaxpayerInformation(WebDriver driver,WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Taxpayer Information']")));
		return webElement;
	}
	
	//to identify the Edit Fields on the TaxPayer Information page.
	public WebElement fn_clickTaxInfoEditFields(WebDriver driver,WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='button' and @value='Edit Fields']")));
		return webElement;
	}
	
	//to identify the Taxpayer ID text field.
	public WebElement fn_setTaxpayerID(WebDriver driver,WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name("inTaxpayerId")));
		return webElement;
	}

	//to identify the Tax Exemption Status Fields
	//intExemptTaxFor = 1 --> 'Exempt from State/Province taxes'
	//intExemptTaxFor = 2 --> 'Exempt from Federal/National taxes'
	public WebElement fn_clickTaxExemptStatus(WebDriver driver,WebDriverWait webWait, Integer intExemptTaxFor) throws InterruptedException {

		if(intExemptTaxFor == 1)
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name("inStateProvTaxExempt")));
		else
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name("inFederalTaxExempt")));
		return webElement;
	}
	
	//to identify the Save Changes on the TaxPayer Information page.
	public WebElement fn_clickTaxInfoSaveChanges(WebDriver driver,WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='button' and @value='Save Changes']")));
		return webElement;
	}	
	
	/*****************************	End of Page Objects for Taxpayer Information Tab	*****************************/
	
	/*******************************	Page Objects for Purchase  Orders Tab	*******************************/
	
	//to identify the Purchase Orders tab on the Account Overview page.
	public WebElement fn_clickPurchaseOrders(WebDriver driver,WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Purchase Orders']")));
		return webElement;
	}
	
	//to identify the Save Button on the Purchase Orders landing page.
	public WebElement fn_clickPurchaseOrdersSave(WebDriver driver,WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='button' and @value='Save']")));
		return webElement;
	}	
	
	/*******************************	End of Page Objects for Purchase  Orders Tab	*******************************/

}
