/*
 Author     		:	Nashim Khan
 Modified By		:	Aashish Bhutkar

 Class Name			: 	AccountsPaymentsCredits 
 Purpose     		: 	Purpose of this file is :
						1. To capture all the page objects for the Payments Functionality of an Account.
 
 Date       		:	05/05/2016
 Modified Date		:	06/16/2016
 Version Information:	2.0
 
 PreCondition 		:	Role based Login required.
 Test Steps 		:	1) Capture the page objects for the objects that will be used/reused from Aria EOM Left Nav.
 						
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

public class AccountsPaymentsCredits {
	
	WebElement webElement;
	
	/*******************************	Page Objects for Payments Tab	*******************************/
	
	//to identify the Record Payment Received link on Payments page.
	public WebElement fn_clickRecordPaymentReceived(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Record Payment Received")));
		return webElement;
	}
	
	//to identify the Make Payment button on Payments Page 
	public WebElement fn_clickMakePayment(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("payment-button")));
		return webElement;
	}
	
	//to identify the Payment Options type of payment to be done.
	public WebElement fn_clickPaymentOptions(WebDriver driver, WebDriverWait webWait, int intPaymentOptions) throws InterruptedException {
		
		String strXpath = null;		
		strXpath = "//input[@id='inPymntType']["+intPaymentOptions+"]";		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strXpath)));
		return webElement;
	}
	
	//to identify the Reference Code text field of Payment Information page.
	public WebElement fn_setPayRecivedDate(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("inPymntRcvdDate")));				
		return webElement;
	}
	
	//to identify the Reference Code text field of Payment Information page.
	public WebElement fn_setRefCode(WebDriver driver, WebDriverWait webWait) throws InterruptedException{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("inExternalRefCode")));				
		return webElement;
	}
	
	//to identify the CSR Comments on Payment Information & Refund Information page.
	public WebElement fn_setCSRComments(WebDriver driver, WebDriverWait webWait) throws InterruptedException	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("inComments")));
		return webElement;
	}
	
	//to identify the Payment Amount type of payment to be done.
	public WebElement fn_clickPaymentAmount(WebDriver driver, WebDriverWait webWait, int intAmountType) throws InterruptedException	{		
		
		String strXpath = null;
		
		if(intAmountType == 1)	
			strXpath = "//input[@id='doFull']";
		else
			strXpath =  "//input[@id='doOtherAmount']";
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strXpath)));
	 	return webElement;
	}
	
	//to identify the Other Amount text field.
	public WebElement fn_setOtherAmount(WebDriver driver, WebDriverWait webWait) throws InterruptedException 	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='inAmount']")));
		return webElement;
	}
		
	//to identify the Application Method FIFO.
	public WebElement fn_clickApplyAgainstOptions(WebDriver driver, WebDriverWait webWait, int intApplyAgainstOptions ) throws InterruptedException	{
			
		String strAgainstOptionsXpath=null;
		strAgainstOptionsXpath = "//input[@id='inApplicationLevel']["+intApplyAgainstOptions+"]";			
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strAgainstOptionsXpath)));
		return webElement;
	}
	
	//to identify the Invoices and other account-level charges.
	public WebElement fn_setAmountAgainstAccountLevel(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='unpaid_acct_level_chrg']/tbody/tr[1]/td[5]/input")));
		return webElement;
	}
	
	//to identify the Amount Against Services account-level charges. 	
	public WebElement fn_setAmountAgainstServices(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='unpaid_inv_level_chrg']/thead[2]/tr[1]/td[8]/input")));
		return webElement;
	}
			
	//to identify the Payment Amount type of payment to be done.
	public WebElement fn_getAmount(WebDriver driver, WebDriverWait webWait, int intAmountType) throws InterruptedException {		
			
		String strXpath = null;
			
		if(intAmountType == 1)
			strXpath = "//input[@id='doOtherAmount']";
		else			
			strXpath =  "//input[@id='doFull']";			
			
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strXpath)));
		return webElement;
	}
	
	//to identify the Other Amount text field.
	public WebElement fn_getOtherAmount(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='otherAmount']")));
		return webElement;
	}
	
	//to identify the Confirmation Pop Up for OK Button
	public WebElement fn_clickConfirmationDlgOk(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button']/span[text()='OK']")));
		return webElement;
	}
	//to identify the Confirmation Pop Up for OK Button
	public WebElement fn_clickConfirmationOk(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
			
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button']/span[text()='Ok']")));
		return webElement;
	}
	//to identify the Confirmation Pop Up  For Cancel
	public WebElement fn_clickConfirmationDlgCancel(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
			
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Cancel']")));
		return webElement;
	}
		
	//to identify the Post Payment button on Payments Page as well as the Payments Confirmation page.
	public WebElement fn_clickPostPayment(WebDriver driver, WebDriverWait webWait) throws InterruptedException	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Post Payment']")));
		return webElement;
	}
		
	//to identify the Payment Amount type of payment to be done.
	public WebElement fn_clickPaymentAmountType(WebDriver driver, WebDriverWait webWait, int intAmountType) throws InterruptedException {
			
		String strXpath = null;
		
		if(intAmountType == 1)			
			strXpath = "//input[@id='doFull'][1]";			
		else
			strXpath =  "//input[@id='doFull'][2]";			
					
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strXpath)));
		return webElement;
	}
		
	//to identify the Other Amount text field.
	public WebElement fn_setOtherAmountMin(WebDriver driver, WebDriverWait webWait) throws InterruptedException	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='inAmount']")));
		return webElement;
	}
	
	//to identify Application Method  for Payment
	public WebElement fn_clickApplicationMethod(WebDriver driver, WebDriverWait webWait, int intAmountType) throws InterruptedException	{		
		
		String strXpath = null;
					
		if(intAmountType == 1)				
				strXpath = "//input[@id='inApplicationLevel'][1]";			
		else
				strXpath =  "//input[@id='inApplicationLevel'][2]";
			
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strXpath)));
		return webElement;
	}
	
	//to identify Payment Method  
	public Select fn_selectPaymentMethod(WebDriver driver, WebDriverWait webWait) throws InterruptedException 	{
	
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='inPymntMethod']")));
		Select selPayMethod	= new Select(webElement);
		return selPayMethod;
	}
	
	//to identify Alternate Billing Type  for  payment to be done.
	public Select fn_selectBillingType(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
			
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='inAltPayMethod']")));
		Select selBillingType	= new Select(webElement);
		return selBillingType;
	}
	
	//to identify theCredit Card Details  for  payment to be done.
	public WebElement fn_setCreditCardDetail(WebDriver driver, WebDriverWait webWait, int intPaymentOptions) throws InterruptedException	{		
			
		String strXpath = null;			
		strXpath = "//span[@id='credit-card-wrapper']/table/tbody/tr[2]/td["+intPaymentOptions+"]";		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strXpath)));
		return webElement;
	}
			
	//to identify the Payment Options type of payment to be done.
	public WebElement fn_selectCreditCardExpDate(WebDriver driver, WebDriverWait webWait, int intPaymentOptions) throws InterruptedException	{		
		
		String strXpath = null;			
		strXpath = "//span[@id='credit-card-wrapper']/table/tbody/tr[2]/td[2]/select["+intPaymentOptions+"]";		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strXpath)));
		return webElement;
	}
		
	//to identify the Routing Number for Billing Type.
	public WebElement fn_setRoutingNumber(WebDriver driver, WebDriverWait webWait) throws InterruptedException {				
			
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='inRoutingNumber']")));
		return webElement;
	}
		
	//to identify the Routing Number for Billing Type.
	public WebElement fn_setCheckingAcctNumber(WebDriver driver, WebDriverWait webWait) throws InterruptedException {					
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='inCheckingAcctNumber']")));
		return webElement;
	}
	
	//to identify Contact details
	public WebElement fn_setContactDetails(WebDriver driver, WebDriverWait webWait, int intPaymentOptions) throws InterruptedException {		
			
		String strXpath = null;		
		strXpath = "//span[@id='alt-payment-wrapper']/dd/table/tbody/tr["+intPaymentOptions+"]/td[2]/input";		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strXpath)));
		return webElement;
	}
		
	//to identify User Country 
	public Select fn_selectUserCountry(WebDriver driver, WebDriverWait webWait) throws InterruptedException {					
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='inCountry']")));
		Select objSelectUserCountry=new Select(webElement);
		return objSelectUserCountry;
	}
		
	//to identify User State  
	public Select fn_selectUserState(WebDriver driver, WebDriverWait webWait) throws InterruptedException	{					
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='inState']")));
		Select objSelectUserState=new Select(webElement);
		return objSelectUserState;
	}
		
	//to identify Confirmation Box Ok
	public WebElement fn_clickConfirmationDljPayments(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
						
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Ok']")));
		return webElement;
	}	
	
	//to identify Confirmation Box Ok
	public WebElement fn_clickCollectFromAccount(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
							
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='submit-button']")));
		return webElement;
	}
	
	//to identify the Error  message after refund is not  approved.
	public WebElement fn_getErrorMessage(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
			
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='accountsSectionBottomContainer']/div/div/div[3]")));
		return webElement;
	}
		
	/*******************************	End of Page Objects for Payments Tab	*******************************/
	
	/*******************************	Page Objects for Refunds Tab	*******************************/
	
	//to identify the Refunds tab from Payments & Credits.
	public WebElement fn_clickRefunds(WebDriver driver, WebDriverWait webWait) throws InterruptedException 	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Refunds")));
		return webElement;
	}
	
	//to identify the Create a New Refund linked text on Refunds page.
	public WebElement fn_clickCreateNewRefund(WebDriver driver, WebDriverWait webWait) throws InterruptedException	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='change-button']")));
		return webElement;
	}
	
	//to identify the refund line item reversal options.
	public WebElement fn_clickNoLineItemReversal(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='0']")));
		return webElement;
	}
	
	//to identify the Does this refund include  options.
	public WebElement fn_clickRefundInclude(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='1']")));
		return webElement;
	}
	
	//to identify the Amount to be Refund text field.	
	public WebElement fn_getRefundAmount(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='enter_refund_amt']/dd/input")));
		return webElement;
	}
	
	//to identify the Reason for Refund.	
	public Select fn_getRefundReasonDropDown(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='inReasonCode']")));
		Select objSelectRefundReason=new Select(webElement);
		return objSelectRefundReason;
	}
	
	//to identify Include Invoice Line Item Reversal 
	public WebElement fn_setInvoiceLineItem(WebDriver driver, WebDriverWait webWait, Integer intOptions) throws InterruptedException {		
							
		String strXpath = null;		
		strXpath = "//input[@id='inIncludeReversal' and @value='"+intOptions+"']";
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strXpath)));
		return webElement;
	}
	
	//to identify the Generate Refund button on Refunds Page and Confirmation page.
	public WebElement fn_setAmountRefunds(WebDriver driver, WebDriverWait webWait) throws InterruptedException  {
				
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("inTotalRefundAmount")));
		return webElement;			
	}
	
	//to identify the Generate Refund button on Refunds Page and Confirmation page.
	public WebElement fn_clickGenerateRefund(WebDriver driver, WebDriverWait webWait) throws InterruptedException  {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Generate Refund']")));
		return webElement;
	}
	
	//to identify Next Btn
	public WebElement fn_clickNextSubmitBtn(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
									
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Next']")));
		return webElement;
	}
	
	//to identify the Process Refund button on Refunds Page and Confirmation page.
	public WebElement fn_clickProcessRefund(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Process Refund']")));
		return webElement;
	}
	
	//to identify the status message after refund is approved.
	public WebElement fn_getStatusMessage(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='accountsSectionBottomContainer']/div/div/div[3]")));
		return webElement;
	}
	
	//to identify the CSR Comments on Payment Information & Refund Information page.
	public WebElement fn_setCSRCommentsForRefund(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='accountsSectionBottomContainer']//textarea")));
		return webElement;
	}
	
	//to identify the status message after refund is approved.
	public WebElement fn_getConfimMessage(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='page-instructions']/p[2]")));
		return webElement;
	}
	
	//to identify Payment Method For check number
	public WebElement fn_setCheckNumber(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
				
		webElement = webWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='inRefundCheckNumber']")));
		return webElement;
	}
	
	//to identify the status message after refund is approved.
	public WebElement fn_getRefundProcessMessage(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
				
		webElement = webWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='page-instructions']/p")));
		return webElement;
	}
	
	//to identify the status message after refund is approved.
	public WebElement fn_clickCreateRefunds(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
						
		webElement = webWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='create-refund-button']")));
		return webElement;
	}
	
	//to identify the status message after refund is approved.
	public WebElement fn_getRefundProcessError(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
								
		webElement = webWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='accountsSectionBottomContainer']/div/div/div[3]/p")));
		return webElement;
	}
		
	/*******************************	End of Page Objects for Refunds Tab	*******************************/
	
	/*******************************	Page Objects for Cash Credits Tab	*******************************/	
	
	//To identify the Cash Credits on Payment & credits Page.
	public WebElement fn_clickCashCredits(WebDriver driver, WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Cash Credits")));					
		return webElement;
	}
	
	//To identify the  new  to create cash credit on Cash Credits Page.	
	public WebElement fn_clickNewBtn(WebDriver driver, WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("change-button")));
		return webElement;
	}
	
	//to identify Reason Code on Cash Credits Page.
	public Select fn_selectReasonCode(WebDriver driver, WebDriverWait webWait) throws InterruptedException 	{
	
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='inReasonCode']")));
		Select selPayMethod	= new Select(webElement);
		return selPayMethod;
	}

	//to identify Apply Against details on Cash Credits Page.
	public WebElement fn_setApplyAgainstDetails(WebDriver driver, WebDriverWait webWait, int intApplyAgainstOptions) throws InterruptedException {		
			
		String strXpath = null;		
		strXpath = "//input[@id='inApplicationLevel' And @value='"+intApplyAgainstOptions+"']";		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strXpath)));
		return webElement;
	}
	
	//To identify the Credits Amount on Cash Credits Page.
	public WebElement fn_setCreditAmount(WebDriver driver, WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("inCreditAmount")));
		return webElement;
	}
	
	//To identify the cash-credit-button on Cash Credits Page.
	public WebElement fn_clickCreateCredit(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
			
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("cash-credit-button")));
		return webElement;
	}
	
	//To identify the Ok  window pop-up  on Cash Credits Page.
	public WebElement fn_clickWindowOKBtn(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
			
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='OK']")));
		return webElement;
	}
	
	/*******************************	End of Page Objects for Cash Credits Tab	*******************************/
	
	/*******************************	Page Objects for Service Credits Tab	*******************************/
	
	//To identify the  Service Credits 	
	public WebElement fn_clickServiceCredit(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Service Credits")));
		return webElement;
	}
	
	//to identifyCredit  Reason  on Service credit Page.
	public Select fn_selectCreditReason(WebDriver driver, WebDriverWait webWait) throws InterruptedException 	{
			
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='inReasonCode']")));
		Select selPayMethod	= new Select(webElement);
		return selPayMethod;
	}
	
	//Frequency
	//to identify the Coupon Assignment Options.
	public WebElement fn_getFrequency(WebDriver driver, WebDriverWait webWait, int intCoupensAssignOptions) throws InterruptedException  	{
				
		String strXpath = null;
		
		if(intCoupensAssignOptions == 1)
			strXpath = "//input[@id='frequency_1']";
		else			
			strXpath =  "//input[@id='frequency_0']";			
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strXpath)));
		return webElement;
		
	}	
	
	//to identify Apply Against details on Cash Credits Page.
	public WebElement fn_setApplicationScope(WebDriver driver, WebDriverWait webWait, int intApplyAgainstOptions) throws InterruptedException {		
					
		String strXpath = null;		
		strXpath = "//input[@id='inApplicationScope' And @value='"+intApplyAgainstOptions+"']";		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strXpath)));
		return webElement;
	}
	
	//to identify Confirmation Box Ok
	public WebElement fn_clickCreateCreditFinal(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
							
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='submit-button']")));
		return webElement;
	}
	
	//to identifyCredit  Reason  on Service credit Page.
	public Select fn_selectServiceCode(WebDriver driver, WebDriverWait webWait) throws InterruptedException 	{
					
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='inAltServNoDirective']")));
		Select selPayMethod	= new Select(webElement);
		return selPayMethod;
	}
	
	//to identifyCredit  Reason  on Service credit Page.
	public Select fn_selectAlternativeCode(WebDriver driver, WebDriverWait webWait) throws InterruptedException 	{
							
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@name='inAltServNo2Apply']")));
		Select selPayMethod	= new Select(webElement);
		return selPayMethod;
	}	
	
	/*******************************	End of Page Objects for Service Credits Tab	*******************************/
	
	/*******************************	Page Objects for Coupons Tab	*******************************/
	
	//to identify the Coupons Tab.
	public WebElement fn_clickCoupons(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Coupons')]")));
		return webElement;
	}
	
	//to identify the Account Hierarchy Glance Table.
	public WebElement fn_getAccountHeirarchyGlanceTable(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='content-wrapper']/table")));
		return webElement;
	}
	
	//to identify the Assign Coupon linked text.
	public WebElement fn_clickAssignCoupon(WebDriver driver, WebDriverWait webWait) throws InterruptedException	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Assign Coupon")));
		return webElement;
	}
	
	//to identify the Validate button for the Coupons.
	public WebElement fn_clickValidate(WebDriver driver, WebDriverWait webWait) throws InterruptedException	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@value, 'Validate')]")));
		return webElement;
	}
	
	//to identify the text field for validating the Coupons.
	public WebElement fn_setCouponCodeText(WebDriver driver, WebDriverWait webWait) throws InterruptedException 	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@name, 'inCouponCd')]")));
		return webElement;
	}
	
	//to identify the Validate button for the Coupons.
	public WebElement fn_clickAssign(WebDriver driver, WebDriverWait webWait) throws InterruptedException  	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@value, 'Assign')]")));
		return webElement;
	}	

	//to identify the Coupon Assignment Options.
	public WebElement fn_getCouponAssignment(WebDriver driver, WebDriverWait webWait, int intCoupensAssignOptions) throws InterruptedException  	{
		
		String strXpath = null;
		
		if(intCoupensAssignOptions == 1)
			strXpath = "//input[@id='inAssignment1']";
		else			
			strXpath =  "//input[@id='inAssignment2']";			
			
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strXpath)));
		return webElement;
		
	}	

	//to identify the Success message after assigning the Coupon.
	public WebElement fn_clickCancelLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException 	{ 
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a/span[text()='Cancel']")));
		return webElement;
	}
	
	//to identify the OK button on the Confirmation Dialog Box.
	public WebElement fn_clickOKCancelingCoupon(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'uiSaveBtn')]")));
		return webElement;
	}	
	
	//to identify the OK button on the Confirmation Dialog Box.
	public WebElement fn_getCouponCode(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/div[text()='Coupon Code']")));
		return webElement;
	}
	
	/*******************************	End of Page Objects for Coupons Tab	*******************************/

}