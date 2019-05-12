/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	NewAccountCreation 
 Purpose     		: 	Purpose of this file is :
						1. To capture all the page objects for the Manual Creation of New Account.
						

 Date       		:	06/11/2015
 Modified Date		:	10/23/2015, 07/10/2015
 Version Information:	Version 2.1
 
 PreCondition 		:	Role based Login required.
 Test Steps 		:	1) Capture the page objects for the objects that will be used for 
 						Manual Creation of New Account.
 
 Version Changes 2.0:	1. Introduced page object based Web wait.
 						2. Changes to the parameters, class and method names as per the standards.
 Version Changes 2.1:	1. Adding more Page Objects for Account Billing Dates. 
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewAccountCreation
{
	WebElement webElement;
	
	public static By fn_clickAccountsLeftNav()	// This is the identification of Accounts Menu in Left Nav. 
	{
		return By.partialLinkText("Accounts");
	}
	
	public static By fn_clickCreateNewAccount()	// This is the identification of Create New Account link in Left Nav. 
	{
		return By.partialLinkText("Create New Account");				
	}
	
	public static By fn_getTemplateDropDown()	// This is the template drop-down identification.
	{
		return By.xpath("//*[@id=\'accountcreation_set_no\']");
	}
	
	
	public static By fn_getClientAccountID()	// This is the Client Account ID text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_client_acct_id\']");
	}
	
	
	public static By fn_getParentAccount()	// This is the PArent Account ID (used for child-parent)text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_senior_acct_no\']");
	}
	
	
	public static By fn_clickAccountStatus()	// This is the Account Status drop-down identification.
	{
		return By.xpath("//*[@id=\'accountcreation_status_cd\']");//*[@id="accountcreation_status_cd"]
	}
	
	
	public static By fn_clickNotificationMethod()	// This is Notification Method drop-down identification.
	{
		return By.xpath("//*[@id=\'accountcreation_notify_method\']");
	}
	
	
	public static By fn_getFirstName()	// This is the First Name text field identification. 
	{
		return By.xpath("//*[@id=\'accountcreation_first_name\']");
	}
	
	
	public static By fn_getLastName()	// This is the Last Name text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_last_name\']");
	}
	
	
	public static By fn_getCompanyName()	// This is the Company Name text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_company_name\']");
	}
	
	
	public static By fn_getAddress1()	// This is the Address 1 text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_address1\']");
	}
	
	
	public static By fn_getAddress2()	// This is the Address 2 text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_address2\']");
	}
	
	
	public static By fn_getCity()	// This is the City text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_city\']");
	}
	
	
	public static By fn_getState()	// This is the State text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_state_prov\']");
	}
	

	public static By fn_getLocality()	// This is the Locality text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_locality\']");
	}
	
	
	public static By fn_clickCountry()	// This is the Country drop-down identification. 
	{
		return By.xpath("//*[@id=\'accountcreation_country\']");
	}
	
	
	public static By fn_getZipCode()	// This is the Zip Code text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_postal_cd\']");
	}
	
	
	public static By fn_getWorkPhone()	// This is the Work Phone text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_work_phone\']");
	}
	
	
	public static By fn_getWorkPhoneExt()	// This is the Work Phone Ext text field identification. 
	{
		return By.xpath("//*[@id=\'accountcreation_work_phone_ext\']");
	}
	
	
	public static By fn_getCellPhone()	// This is the Cell Phone text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_cell_phone\']");
	}
	
	
	public static By fn_getEmailAddress()	// This is the Email Address text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_email\']");
	}
	
		
	public static By fn_getBillingFirstName()	// This is the Billing First Name text field identification. 
	{
		return By.xpath("//*[@id=\'accountcreation_bill_first_name\']");
	}
	
	
	public static By fn_getBillingLastName()	// This is the Billing Last Name text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_bill_last_name\']");
	}
	
	
	public static By fn_getBillingCompanyName()	// This is the Billing Company Name text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_bill_company_name\']");
	}
	
	
	public static By fn_getBillingAddress1()	// This is the Billing Address 1 text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_bill_address1\']");
	}
	
	
	public static By fn_getBillingAddress2()	// This is the Billing Address 2 text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_bill_address2\']");
	}
	
	
	public static By fn_getBillingCity()	// This is the Billing City text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_bill_city\']");
	}
	
	
	public static By fn_getBillingState()	// This is the Billing State text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_bill_state_prov\']");
	}
	

	public static By fn_getBillingLocality()	// This is the Billing Locality text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_bill_locality\']");
	}
	
	
	public static By fn_clickBillingCountry()	// This is the Billing Country drop-down identification. 
	{
		return By.xpath("//*[@id=\'accountcreation_bill_country\']");
	}
	
	
	public static By fn_getBillingZipCode()	// This is the Billing Zip Code text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_bill_postal_cd\']");
	}
	
	
	public static By fn_getBillingWorkPhone()	// This is the Billing Work Phone text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_bill_work_phone\']");
	}
	
	
	public static By fn_getBillingWorkPhoneExt()	// This is the Billing Work Phone Ext. text field identification. 
	{
		return By.xpath("//*[@id=\'accountcreation_bill_work_phone_ext\']");
	}
	
	
	public static By fn_getBillingCellPhone()	// This is the Billing Cell Phone text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_bill_cell_phone\']");
	}
	
	
	public static By fn_getBillingEmailAddress()	// This is the Billing Email Address text field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_bill_email\']");
	}	
	
	
	public static By fn_clickMasterPlan()	// This is the Master Plan drop-down field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_master_plan_no\']");
	}
	
	
	// This Supplemental Field is driven by Master Plan drop-down field value selection. 
	public static By fn_clickSupplementalPlanNo()	// This is the Supplemental Plan No. drop-down field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_supp_plans_1_supp_plans\']");//*[@id="accountcreation_supp_plans_1_supp_plans"]
	}
	
	public static By fn_clickPaymentMethod()	// This is the Payment Method drop-down field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_pay_method\']");
	}
	
	
	public static By fn_clickPaymentCurrency()	// This is the Payment Currency drop-down field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_currency_cd\']");
	}
	
	
	public static By fn_clickRetroactiveBillingStartDate()	// This is the Retroactive Billing Start date Calendar 
	{													// 							control field identification.
		return By.xpath("//*[@id=\'accountcreation_retroactive_start_date\']");
	}
	
	
	public static By fn_clickAlternateBillingStartDate()	// This is the Alternate Billing Start date Calendar 
	{												// 							control field identification.
		return By.xpath("//*[@id=\'accountcreation_alt_start_date\']");
	}
	
	
	public static By fn_clickAlternateBillingDay()	// This is the Alternate Billing Day drop-down field identification. 
	{
		return By.xpath("//*[@id=\'accountcreation_alt_bill_day\']");
	}
	
	
	public static By fn_clickPerformFullInvoicing()	// This is the Perform Full Invoicing drop-down field identification.
	{
		return By.xpath("//*[@id=\'accountcreation_do_full_invoicing\']");
	}
		
	// This Save button will complete the New Account Creation Procedure. 
	public static By fn_clickSaveButton()	// This is the Save button identification.
	{
		return By.xpath("//*[@id=\'tabs\']/div/div[13]/div[2]/input");
	}

	
	public static By fn_dlgboxCompleted()
	{
		return By.xpath("//*[@id=\'ui-id-1\']");
	}
	
	
	public static By fn_getStatusMessage()
	{
		return By.xpath("//*[@id=\'ariaGlobalModalPopup\']/div/div");
	}
	
	public static By fn_clickCloseCompleted()	// This is the Close Button identification for 
	{										// 							Successfully Completed Account Creation.
		return By.xpath("/html/body/div[6]/div[3]/div/button/span");
	}

	public static By fn_getErrorMessage()	// this is to check if error message is displayed.
	{
		return By.xpath("//*[contains(@class, 'error')]");
	}
	
	public static By fn_getAccountSuccessMessage()	// This is the Account Success Message identification.
	{
		return By.xpath("//*[@id=\'AccountCreationNew\']/div[3]");
	}	
	
	public static By fn_clickNewAccountNum()	// As per TC, This is to identify the Account No. & click on it.
	{
		return By.xpath("//*[@id=\'AccountCreationNew\']/div[3]/a");
	}
	
	
	public static By fn_clickAccountOverview()	// This is for the Account Overview tab identification.  
	{
		return By.xpath("//*[@id=\'bottomPaneTabBar\']/ul/li[1]/a");
	}
	
	
	public static By fn_getAccountNum()	// This is to identify the actual Account # for verification.
	{
		return By.xpath("//*[@id=\'content-wrapper\']/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[3]/td[2]");
	}
	
	
	public static By fn_getCreatedAccountStatus()	// This is to identify the actual Account Status after creation.
	{
		return By.xpath("//*[@id=\'content-wrapper\']/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[3]/td[4]");
	}
	
	
	public static By fn_getBillingInformation()	// This is to scroll the page before snapshot.
	{
		return By.xpath("//*[@id=/'section_3/']");
	}
	
	
	public static By fn_getInvoicingPayments()	// This is to scroll the page before snapshot.
	{
		return By.xpath("//*[@id=/'section_5/']");
	}
	
	//to identify the tab Account Billing Dates.
	public WebElement fn_clickAccountBillingDates(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Account Billing Dates")));
		return webElement;
	}
	
	//to identify the Current Paid Thru date on Account Billing Dates tab page. 
	public WebElement fn_getCurrentPaidThruDate(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='accountsSectionBottomContainer']/div/div/div[4]/dl/dd[2]")));
		return webElement;
	}
		
	//to identify the left nav menu item Account Overview.
	public WebElement fn_clickAccountOverview(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Account Overview")));
		return webElement;
	}
	
	//to identify the Reset Password button.
	public WebElement fn_clickPasswordReset(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Reset Password")));
		return webElement;
	}
}