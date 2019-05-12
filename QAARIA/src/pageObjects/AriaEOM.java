/*
 Author     		:	Aashish Bhutkar
 Modified By		: 	Aashish Bhutkar, Abhishek.
 
 Class Name			: 	AriaEOM 
 Purpose     		: 	Purpose of this file is :
						1. To capture all the page objects for the Aria EOM Left Nav.
 
 Date       		:	04/06/2016
 Modified Date		:	06/17/2016, 06/16/2016, 06/07/2016, 05/11/2016.
 Version Information:	5.0
 
 PreCondition 		:	Role based Login required.
 Test Steps 		:	1) Capture the page objects for the objects that will be used/reused from Aria EOM Left Nav.

 Version Changes 2.0:	1. Added more Page Object for improved recognition.
 Version Changes 3.0:	1. Added more Page Object for improved recognition & remodeled few.
 Version Changes 4.0:	1. Added more Page Object for Configurations.
 Version Changes 5.0:	1. Added more Page Object for Configurations -> Billing
 						
 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/


package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AriaEOM {	
	
	WebElement webElement;
	
	/*******************************	Page Objects for Analytics and Reporting	*******************************/
	
	//to identify the Analytics and Reporting menu link in the left nav. 
	public WebElement fn_clickAnalyticsReporting(WebDriver driver, WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Analytics and Reporting")));
		return webElement;
	}
	
	//to identify the Reports sub-menu for Analytics and Reporting.
	public WebElement fn_clickReports(WebDriver driver, WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Reports")));
		return webElement;
	}	
	
	//to identify the Reports sub-menu for CSR Activity.
	public WebElement fn_clickCSRActivity(WebDriver driver, WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("CSR Activity")));
		return webElement;
	}	
	
	//to identify the Run Reports under the Reports sub-menu.
	public WebElement fn_clickRunReports(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Run Reports")));
		return webElement;
	}
	
	//to identify the Analytic Dashboard menu link in the left nav. 
	public WebElement fn_clickAnalyticDashboard(WebDriver driver, WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Analytic Dashboard")));
		return webElement;
	}
	
	/*******************************	End of Page Objects for Analytics and Reporting	*******************************/
	
	/*******************************	Page Objects for Accounts	*******************************/
	
	//to identify the Accounts menu link in the left nav.
	public WebElement fn_clickAccounts(WebDriver driver,WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Accounts")));
		return webElement;
	}
	
	//to identify the Search for Accounts menu link in the left nav.	
	public WebElement fn_clickAccountsSearch(WebDriver driver,WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Search")));
		return webElement;
	}
	
	//to identify the Ad-Hoc Search for Accounts menu link in the left nav.
	public WebElement fn_clickAdhocSearchLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Ad-Hoc Search")));
		return webElement;
	}
	
	//to identify the Create New Account for Accounts menu link in the left nav.	
	public WebElement fn_clickAccountsCreateNew(WebDriver driver,WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Create New Account")));
		return webElement;
	}
	
	//To identify the Account Overview in Accounts sub menu.
	public WebElement fn_clickAccountOverview(WebDriver driver, WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a/div[text()='Account Overview']")));
		return webElement;
	}
	
	//To identify the Reset Password on Accounts Overview Page.
	public WebElement fn_clickAcctOvrvwResetPassword(WebDriver driver, WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Reset Password")));
		return webElement;
	}
	
	//To identify the Plans in Accounts sub menu.
	public WebElement fn_clickAccountPlans(WebDriver driver, WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='acctNoList']//a/div[text()='Plans']")));
		return webElement;
	}
	
	//To identify the Statements & Invoices in Accounts sub menu.
	public WebElement fn_clickStatementsInvoices(WebDriver driver, WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Statements & Invoices")));
		return webElement;
	}
	
	//To identify the Payments & Credits in Accounts sub menu.
	public WebElement fn_clickPaymentsCredits(WebDriver driver, WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Payments & Credits")));
		return webElement;
	}
	
	//To identify the Usage sub-menu link.
	public WebElement fn_clickUsage(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Usage")));
		return webElement;
	}
	
	/*******************************	End of Page Objects for Accounts	*******************************/	
	
	/*******************************	Page Objects for Products	*******************************/
	
	//to identify the Products menu link in the left nav.	
	public WebElement fn_clickProducts(WebDriver driver,WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Products")));
		//By.xpath("//*[@id=\'ui-accordion-1-header-3\']/a/div");
		return webElement;
	}
	
	// this is to identify the Plans sub-menu of Products in left nav.
	public WebElement fn_clickProductsPlans(WebDriver driver,WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/ui/app.php/Plans']")));
		return webElement;
	}
	
	//To Identify Services sub-menu of Product left nav
	public WebElement fn_clickProductsServices(WebDriver driver,WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Services")));
		return webElement;
	}			
	
	/*******************************	End of Page Objects for Products	*******************************/
	
	/*******************************	Page Objects for Configuration	*******************************/
	
	//to identify the Configuration menu in the left nav.
	public WebElement fn_clickConfiguration(WebDriver driver, WebDriverWait webWait) throws IOException	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3/a/div[text()='Configuration']")));
		return webElement;
	}
	
	//To identify Billing Under Configuration Menu
	public WebElement fn_clickBilling(WebDriver driver,WebDriverWait webWait) throws InterruptedException  {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Billing")));
		return webElement;
	}
	
	//To identify Countries Under Billing Menu
	public WebElement fn_clickBillingCountries(WebDriver driver,WebDriverWait webWait) throws InterruptedException  {
			
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Countries")));
		return webElement;
	}		
	
	//To identify Currencies Under Billing Menu
	public WebElement fn_clickBillingCurrencies(WebDriver driver,WebDriverWait webWait) throws InterruptedException  {
				
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Currencies")));
		return webElement;
	}
	
	//To identify Chart of Accounts Under Billing Menu
	public WebElement fn_clickChartOfAccounts(WebDriver driver,WebDriverWait webWait) throws InterruptedException  {
					
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Chart of Accounts")));
		return webElement;
	}
		
	//to identify the Audit Logs sub-menu for COnfiguration.
	public WebElement fn_clickAuditLogs(WebDriver driver, WebDriverWait webWait) throws IOException	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Audit Logs")));
		return webElement;
	}	
	
	//Identify Integrations Link
	public WebElement fn_clickIntegrations(WebDriver driver,WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Integrations")));
		return webElement;
	}
	
	//to identify the Web Service API link in the sub-menu for Audit Logs.
	public WebElement fn_clickWebServiceAPI(WebDriver driver, WebDriverWait webWait) throws IOException	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Web Service API")));
		return webElement;
	}	
	
	//To identify Payment Settings Menu
	public WebElement fn_clickConfigPaymentSetting(WebDriver driver,WebDriverWait webWait) throws InterruptedException  {
			 
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Payment Settings")));
		return webElement;
	}
	
	//To identify Notifications Menu
	public WebElement fn_clickConfigNotifications(WebDriver driver,WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Notifications")));
		return webElement;
	}
	
	//To identify NotificationSettings Menu
	public WebElement fn_clickConfigNotifcationSettings(WebDriver driver,WebDriverWait webWait) throws InterruptedException  { 
		 
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Notification Settings")));
		return webElement;
	}	 
	 
	//To identify Payments Menu
	public WebElement fn_clickConfigPayments(WebDriver driver,WebDriverWait webWait) throws InterruptedException {
		 
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Payments")));
		return webElement;
	}
	 
	//To identify Payment Methods Menu
	public WebElement fn_clickConfigPaymentMethods(WebDriver driver,WebDriverWait webWait) throws InterruptedException  {
		 
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Payment Methods")));
		return webElement;
	}
	
	//To identify Client Settings Menu
	public WebElement fn_clickClientSetting(WebDriver driver,WebDriverWait webWait) throws InterruptedException  {
				 
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Client Settings")));
		return webElement;
	}
	
	//To identify Company Profile Under Client Settings Menu
	public WebElement fn_clickCompanyProfile(WebDriver driver,WebDriverWait webWait) throws InterruptedException  {
	
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Company Profile")));
		return webElement;
	}
	
	//To identify Account Fields Menu
	public WebElement fn_clickAccountFields(WebDriver driver,WebDriverWait webWait) throws InterruptedException  {
					 
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Account Fields")));
		return webElement;
	}
	
	//To identify Product Fields Menu
	public WebElement fn_clickProductFields(WebDriver driver,WebDriverWait webWait) throws InterruptedException  {
						 
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Product Fields")));
		return webElement;
	}
	
	//To identify Product Field Categories Menu
	public WebElement fn_clickProductFieldCatagories(WebDriver driver,WebDriverWait webWait) throws InterruptedException  {
							 
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Product Field Categories")));
		return webElement;
	}	
	
	/*******************************	End of Page Objects for Configuration	*******************************/
	
	/*******************************	Generic Page Objects	*******************************/
	
	// this is to identify the Supplemental Plans data table.
	public WebElement fn_getDataTable(WebDriver driver, WebDriverWait webWait) throws IOException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[contains(@id, 'DataTables_Table_')]")));
		return webElement;
	}
	
	
	//to identify the message show after Saving the changes done to the Custom Invoice Status field.
	public WebElement fn_getStatusMessage(WebDriver driver,WebDriverWait webWait) throws InterruptedException {
	//*[@id="accountsSectionBottomContainer"]/div/div/div[3]/p
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'error-box')]")));
		return webElement;
	}
	
	/*******************************	End of Generic Page Objects	*******************************/
}
