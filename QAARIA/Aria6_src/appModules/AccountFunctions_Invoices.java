/*
 Author     		:	Aashish Bhutkar

 Class Name			: 	AccountFunctions_Invoices 
 Purpose     		: 	Purpose of this file is :
						1. To perform various Account related functions related to Invoices.

 Date       		:	09/24/2015
 Modified Date		:	01/29/2015, 01/27/2015, 01/18/2015, 01/06/2015, 11/24/2015, 11/17/2015, 11/06/2015, 10/26/2015, 10/14/2015,
 						09/28/2015, 11/23/2015, 12/04/2015, 12/16/2015
 Modified By		: 	Aashish Bhutkar, Madhavi JN., Namrata Akarte
 Version Information:	Version 7.3

 Version Changes 2.0:	1. Added function "fn_AssignCreditCoupon" to assign the coupon to Account Number suggested.
 						2. Added function "fn_SelectLatestInvoice" to select the latest invoice from the list of Invoices
 						for the Account under test.
 Version Changes 3.0:	1. Added three more functions "fn_SelectLatestInvoice", "fn_AssignCreditCoupon" & "fn_CancelCreditCoupon"  						
 Version Changes 3.1:	1. Updated the "fn_GeneratePayments" to update the Other Amount fields.
 Version Changes 4.0:	1. Added a new function "fn_ReadInvoiceDetails" to read the invoice details requested / launched.
 						2. Added a new function "fn_GenerateInvoiceStatement" for immediate statement generation after 
 Version Changes 4.1:	1. Modified the condition to check the error generated if any during Payment Generation.
 Version Changes 4.2:	1. Modified the "fn_ReadViewPrintPage" function to handle waits for the popped-up Print Frame to be displayed.
 Version Changes 5.0:	1. Added a new "fn_CreatePromotionalCredit" function to Create a promotional Credit.
 Version Changes 5.1:	1. Modified the "fn_GeneratePayments" by adding wait for Payments Tab data table to get populated.
 Version Changes 6.0:	1. Added functions fn_ApplyCredit to create credit and fn_E2EGenerateInvoice
 Version Changes 6.1:	1. Modified the "fn_GeneratePayments" by adding wait for Payments Tab data table to get populated.
 Version Changes 6.2:	1. Updated object references 
 Version Changes 6.3: 	1. Commented statement generation code in fn_E2EGenerateInvoice
 Version Changes 7.0:	1. Added "fn_GetStatementTransactions" method which will navigate to Statements & create a 
 						Pair of values for Statement & it's co-related Transaction details.
 						2. Updated all functions with waits so that the data tables to be read are loaded.
 						3. Changed the clicking logic for the Transaction ID of "fn_BookingRefunds" while booking refunds.
 						4. Changed the clicking logic for "fn_CancelCreditCoupon" used by the Cancellation of already assigned Coupons.
 Version Changes 7.1:	1. Modified "fn_GetStatementTransactions" to add more Transactional Data - Description.
 						2. Modified "fn_GetStatementTransactions" to get maximum 5 different Statements Data; 4 oldest & 1 latest.
 Version Changes 7.2:	1. Modified "fn_GenerateInvoiceStatement" to send the Statement email whenever generated.
 Version Changes 7.3:	1. Added missing Imports.
  
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package appModules;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import pageObjects.ChangeAccountPONumber;
import pageObjects.ParentChildDiscounting;
import pageObjects.PaymentDetailsFinancialReport;
import pageObjects.SupplimentalPlans;
import pageObjects.UsagePageObjects;
import pageObjects.VerifyGLLocationSegment;
import utility.ApiHandler;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;

public class AccountFunctions_Invoices
{

/*
	Function fn_ApproveAdvancedInvoices: Method to approved generated Invoice; Has to be called immediately ONLY after generating invoice.
	Outputs: Boolean status of the approval for generated invoice.   
	
	  
*/	 		
	public Boolean fn_ApproveAdvancedInvoices(WebDriver driver, WebDriverWait webWait) throws Exception
	{	
		//TODO: Create object for the classes to be used for accessing reusable codes.
		VerifyGLLocationSegment objGLLocation = new VerifyGLLocationSegment();
		
		Boolean blnApprovedInvoice = false;
		
		WebElement webInvoicesDetails = objGLLocation.fn_getInvoicesDataTable(driver, webWait).findElement(By.tagName("tbody"));
		List<WebElement> lstwebReadInvoicesRow = webInvoicesDetails.findElements(By.tagName("tr"));
		for(WebElement row: lstwebReadInvoicesRow)
		{
			List<WebElement> cols = row.findElements(By.tagName("td"));
			
			if(cols.size() >= 2)
			{
				cols.get(0).click();	// Clicking the hyperlinked text for Approve/View link.				
				break;
			}
		}
		
		webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='accountsSectionBottomContainer']/div/div/form/table")));
		objGLLocation.fn_clickApprove(driver, webWait).click();		
		objGLLocation.fn_clickApproveButton(driver, webWait).click();
		objGLLocation.fn_getInvoiceGnerationMessage(driver, webWait);
		
		String strApprovalMessage = objGLLocation.fn_getInvoiceGnerationMessage(driver, webWait).getAttribute("innerText").toString().trim();
		if(strApprovalMessage.contains(" approved."))
		{
			blnApprovedInvoice = true;
			Log.info("Generated Advance Invoice has been successfully approved !");
		}		
		
		return blnApprovedInvoice;
	}	

/*
 	Purpose     	:	Function for Reading the data on the View & Print Frame and return it as a WebElement.
*/

	public WebElement fn_ReadViewPrintPage(WebDriver driver) throws Exception 
	{	
		WebElement rootTree = null;
		try{
			rootTree = driver.findElement(By.tagName("frameset"));
		}
		catch (Exception exception){
			Thread.sleep(10000);
			rootTree = driver.findElement(By.tagName("frameset"));
			Log.info("!!! PLEASE NOTE, MORE THAN 20 SECONDS ARE REQUIRED FOR PRINT FRAME TO POP-UP. MAY CAUSE TC FAILURE !!!");
		}		
		WebElement parentFrame = rootTree.findElement(By.name("msgBodyFrame"));
		driver.switchTo().frame(parentFrame);
		
		WebElement webHTML = driver.findElement(By.tagName("html"));
		WebElement webBody = webHTML.findElement(By.tagName("body"));
		
		return webBody;
	}
	
	
/*
	Author: Namrata Akartes
	Function fn_GenerateInvoice: Takes in the details for Account Number and creates Invoice for Type requested.
*/  
   	
	public Boolean fn_GenerateInvoice(WebDriver driver,WebDriverWait webWait,String strAccNum,String strInvoiceType) throws Exception
	{
		//TODO: Create object for the classes to be used for accessing reusable codes.
		UsagePageObjects objUsage = new UsagePageObjects();
		Cls_ChangeDeleteClientDefinedFieldActns obj = new Cls_ChangeDeleteClientDefinedFieldActns();
		
		Boolean blnInvoiceGenerated = false;
		
		//TODO : Search Account		
		obj.AccountSearch(driver, webWait, strAccNum);
		
		//TODO : Click link Statements and Invoices
		objUsage.fn_ClickStmtsInvoiceslnk(driver, webWait).click();
		
		Thread.sleep(5000);
		//TODO : Click on Invoices link
		objUsage.fn_ClickInvoiceslnk(driver, webWait).click();
	 
		//TODO : Click on Generate Invoice link
		objUsage.fn_ClickGenInvoiceslnk(driver, webWait).click();
		Thread.sleep(5000);
	 	if (strInvoiceType.equals("Immediate")){
	 		//TODO : Select Invoice Type to be generated - here Immediate
			objUsage.fn_ImmediateInvoice(driver, webWait).click();			
	 	}
	 	else if(strInvoiceType.equals("Pending")) {
	 		//TODO : Select Invoice Type to be generated - here Pending
			objUsage.fn_PendingInvoice(driver, webWait).click();	 		
	 	}
	 	else if(strInvoiceType.equals("Advanced")) {
	 		//TODO : Select Invoice Type to be generated - here Advanced
			objUsage.fn_AdvancedInvoice(driver, webWait).click();	 		
	 	}
	 		
		Thread.sleep(5000);
		//TODO : Click button Generate invoice
		objUsage.fn_GenerateInvoiceButton(driver, webWait).click();
		Thread.sleep(10000);
		
		objUsage.fn_getInvoiceGnerationMessage(driver, webWait);
		String strInvoiceMessage = objUsage.fn_getInvoiceGnerationMessage(driver, webWait).getAttribute("innerText").toString().trim();
		if(strInvoiceMessage.contains("successfully generated"))
		{
			blnInvoiceGenerated = true;
			Log.info("Invoice has been successfully genereated !");
			Thread.sleep(2000);
		}
		
		webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Accounts"))).click();		
		return blnInvoiceGenerated;
	}
	
	
/*
	Function fn_GeneratePayments: Takes in the details for Account Number for which payments are to be processed.
	Inputs: strAccountNum - account number for which Payments is to be made.
			strRefCode - the cheque # value to be later used in reports verification.
			blnPaymentAmount - Boolean to decide whether to make Full Payment or Other Amount.
			strOtherAmount - Value to be used for the Other amount for Payments.
			blnApplicationMethod - Boolean to decide the Application method for Other Amount payments.
			strApplicationMethods - Future ready value for deciding the various 
	
	Outputs: String value which is concatenated string of Transaction ID & the Applied Amount for Payments generated.
*/  
	
	public String fn_GeneratePayments (WebDriver driver, WebDriverWait webWait, String strAccountNum, String strRefCode, Boolean blnPaymentAmount, 
							String strOtherAmount, Boolean blnApplicationMethod, String strApplicationMethods, String strTestCaseName) throws Exception
	{
    	//TODO: create class object to access the page objects for accessing the Payments.
		//Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
		PaymentDetailsFinancialReport objPayDetails = new PaymentDetailsFinancialReport();
		
		String strReturnPayments = null;
		String strPaymentsTxnID = null;
		String strPaymentsAmount = null;
				
		//TODO: Search the account for generating payments and then navigate to the Payments & Credits sub-menu.
/*		objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
		webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Reset Password")));*/
		objPayDetails.fn_clickPaymentsCredits(driver, webWait).click();
		objPayDetails.fn_clickPayments(driver, webWait).click();
		Thread.sleep(1500);
		objPayDetails.fn_getDataTable(driver, webWait);
		objPayDetails.fn_clickRecordPaymentReceived(driver, webWait).click();
		objPayDetails.fn_clickPostPayment(driver, webWait);
		objPayDetails.fn_getCSRComments(driver, webWait).sendKeys("This is Payments for automation test purpose !");
		objPayDetails.fn_getRefCode(driver, webWait).clear();
		objPayDetails.fn_getRefCode(driver, webWait).sendKeys(strRefCode);
		
		if(blnPaymentAmount == true)	//to set Full Payment, we send number 0.
		{
			objPayDetails.fn_getPaymentAmount(driver, webWait, 0).click();						
		}
		else	//to set Other Amount, we send number 1.
		{
			objPayDetails.fn_getPaymentAmount(driver, webWait, 1).click();
			objPayDetails.fn_getOtherAmount(driver, webWait).clear();
			objPayDetails.fn_getOtherAmount(driver, webWait).sendKeys(strOtherAmount);
			//TODO: If Other Payment, set the value and proceed further for Application Method to be set. (future ready code)
			if(blnApplicationMethod == true)
			{
				objPayDetails.fn_getFIFOApplicationMethod(driver, webWait).click();
			}
			else
			{
				objPayDetails.fn_getSpecificApplicationMethod(driver, webWait).click();
				//TODO: call a method to define which values are to be selected from the basis of strApplicationMethods governing it.
			}
		}
		
		Utils.takeScreenshot(driver, strTestCaseName);
		objPayDetails.fn_clickPostPayment(driver, webWait).click();
		webWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='main']/fieldset")));		
		objPayDetails.fn_clickPostPayment(driver, webWait).click();
			try	//try catch introduced to check if there was any error generating the payments. 
			{
				objPayDetails.fn_getDataTable(driver, webWait);
			}
			catch (Exception exception)
			{
				Thread.sleep(1500);
				Utils.takeScreenshot(driver, strTestCaseName);
				String strErrorMessage = driver.findElement(By.xpath("//*[@id='accountsSectionBottomContainer']/div/div/div[3]/p")).getAttribute("innerText").toString().trim();
				Log.error("ERROR: Payment Generation failed with error: "+strErrorMessage);
				Reporter.log("ERROR: Payment Generation failed with error: "+strErrorMessage);
				VerificationMethods.assertTrue(false, "ERROR: There is error generating the Payments. Please refer to the Snapshot for details...");				
			}		
		Utils.takeScreenshot(driver, strTestCaseName);
		String strPaymentsMessage = objPayDetails.fn_getPaymentsStatusMessage(driver, webWait).getAttribute("innerText").toString().trim();
		if(strPaymentsMessage.contains("successfully posted"))
		{
			//TODO: Read the Payments Data table to get the transaction ID for recently added Payments.
			WebElement tblPayments = objPayDetails.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
			List<WebElement> lstwebRowsPayments = tblPayments.findElements(By.tagName("tr"));
			for(WebElement rows: lstwebRowsPayments)
			{
				List<WebElement> cols = rows.findElements(By.tagName("td"));
				if(cols.size() != 0)
				{
					String strColsVoid = cols.get(0).getAttribute("textContent").toString().trim();				
					if(strColsVoid.contentEquals("Void"))
					{
						strPaymentsTxnID = cols.get(1).getAttribute("innerText").toString().trim();
						strPaymentsAmount = cols.get(6).getAttribute("innerText").toString().trim();
						strPaymentsAmount = strPaymentsAmount.replace(",", "");
						Log.info("The Payments is succesfully created and the Transaction ID for it is : "+strPaymentsTxnID);
						Log.info("The Actual Amount for the Transaction is : "+strPaymentsAmount);
						break;
					}					
				}
			}			
		}
		else
		{
			Log.error("ERROR: The Payments couldn't be successfully posted !");
			Reporter.log("ERROR: The Payments couldn't be successfully posted !");			
		}
		
		//TODOD: Since Transaction ID & Applied Amount for payments is necessary, concatenate it and return the value.
		strReturnPayments = strPaymentsTxnID+"#"+strPaymentsAmount;
		return strReturnPayments;
	}
	
	
/*
	Function fn_VoidPayments: Takes in the details for Account Number for which payments are to be processed.
	Inputs: strAccountNum - account number for which Payments are to be Voided.
			strPaymenTxnID - the Payments Transaction ID which is to be voided.
	
	Outputs: Boolean status for the payment being voided.
*/
	public Boolean fn_VoidPayments (WebDriver driver, WebDriverWait webWait, String strAccountNum, String strPaymentsTxnID, String strTestCaseName) throws Exception
	{
    	//TODO: create class object to access the page objects for accessing the Payments.
		//Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
		PaymentDetailsFinancialReport objPayDetails = new PaymentDetailsFinancialReport();
		
		Boolean blnValidateVoid = false;
		
		//TODO: Search the account for voiding payments and then navigate to the Payments & Credits sub-menu.
		/*objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
		webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Reset Password")));*/
		objPayDetails.fn_clickPaymentsCredits(driver, webWait).click();
		objPayDetails.fn_getDataTable(driver, webWait);
				
		//TODO: Read the Payments Data table to select the row of Payments done to be voided.
		WebElement tblPayments = objPayDetails.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
		List<WebElement> lstwebRowsPayments = tblPayments.findElements(By.tagName("tr"));
		for(WebElement rows: lstwebRowsPayments)
		{
			List<WebElement> cols = rows.findElements(By.tagName("td"));
			if(cols.size() != 0)
			{
				String strColsVoid = cols.get(0).getAttribute("textContent").toString().trim();
				String strColsTxnID = cols.get(1).getAttribute("innerText").toString().trim();				
				if(strColsTxnID.contentEquals(strPaymentsTxnID) && strColsVoid.contentEquals("Void"))
				{
					Log.info("The Transaction ID '"+strPaymentsTxnID+"' has been found and the voiding process has been initiated..");
					cols.get(0).click();
					Thread.sleep(3000);
					//objPayDetails.fn_getVoidPaymentsConfirmationBox(driver, webWait);
					//objPayDetails.fn_clickOkVoidPaymentsConfirmationBox(driver, webWait).click();
									
					driver.switchTo().activeElement().sendKeys(Keys.TAB);
					driver.switchTo().activeElement().sendKeys(Keys.TAB);
					driver.switchTo().activeElement().sendKeys(Keys.RETURN);
					Thread.sleep(2000);					
					driver.switchTo().defaultContent();
					String strWindowHandle = driver.getWindowHandle();
					driver.switchTo().window(strWindowHandle);
										
					String strVoidStatusMessage = objPayDetails.fn_getPaymentsStatusMessage(driver, webWait).getAttribute("innerText").toString().trim();
					if(strVoidStatusMessage.contains("successfully voided"))
					{
						objPayDetails.fn_getDataTable(driver, webWait);
						Utils.takeScreenshot(driver, strTestCaseName);
						blnValidateVoid = true;
						Log.info("The voiding process has been successfully completed !");
						break;
					}					
				}
				else
				{
					Log.error("ERROR: The Voiding of Payments for Payments Transaction ID: "+strPaymentsTxnID+" couldn't be successfully done !");
					Reporter.log("ERROR: The Voiding of Payments for Payments Transaction ID: "+strPaymentsTxnID+" couldn't be successfully done !");
					blnValidateVoid = false;
				}
			}
		}
		return blnValidateVoid;
	}
	
	
/*
	Function fn_BookingRefunds: Takes in the details for Account Number for which Refunds are to be initiated.
	Inputs: strAccountNum - account number for which Refunds are to be booked.
			strPaymenTxnID - the Payments Transaction ID which is to be voided.
	
	Outputs: Boolean status for the Refunds being initiated.
*/
	public Boolean fn_BookingRefunds(WebDriver driver, WebDriverWait webWait, String strAccountNum, String strPaymentsTxnID, Boolean blnItemReversal,  
										String strRefundAmount, String strRefundsReason, String strTestCaseName) throws Exception
	{
    	//TODO: create class object to access the page objects for accessing the Payments.
		//Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
		PaymentDetailsFinancialReport objPayDetails = new PaymentDetailsFinancialReport();
		
		Boolean blnVerifyRefundInitiation = false;
		//TODO: Search the account for voiding payments and then navigate to the Payments & Credits sub-menu.
/*		objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
		webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Reset Password")));*/
		objPayDetails.fn_clickPaymentsCredits(driver, webWait).click();
		objPayDetails.fn_getDataTable(driver, webWait);
		objPayDetails.fn_clickRefunds(driver, webWait).click();
		objPayDetails.fn_getDataTable(driver, webWait);
		objPayDetails.fn_clickCreateNewRefund(driver, webWait).click();

		//TODO: Read the Refunds Data table to select the row of Payments Transaction ID to add a refund.
		Thread.sleep(3500);		
		WebElement tblRefunds = objPayDetails.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
		List<WebElement> lstwebRowsRefunds = tblRefunds.findElements(By.tagName("tr"));
		for(WebElement rows: lstwebRowsRefunds)
		{
			List<WebElement> cols = rows.findElements(By.tagName("td"));
			if(cols.size() != 0)
			{
				String strColsTxnID = cols.get(0).getAttribute("innerText").toString().trim();
				if(strColsTxnID.contains(strPaymentsTxnID))
				{
					Log.info("Transaction ID has been found and the Refunds for this ID will be initiated..");
					
					driver.findElement(By.partialLinkText(strPaymentsTxnID)).click();
					//TODO: Select the logic for Refunds as per line reversible or otherwise.
					if(blnItemReversal == false)	
					{
						objPayDetails.fn_clickNoLineItemReversal(driver, webWait).click();
						objPayDetails.fn_getRefundAmount(driver, webWait).clear();
						objPayDetails.fn_getRefundAmount(driver, webWait).sendKeys(strRefundAmount);						
					}
					else
					{
						//TODO: Write a logic to select various items for reversible invoice line items.
					}
					objPayDetails.fn_getRefundReasonDropDown(driver, webWait).sendKeys(strRefundsReason);
					objPayDetails.fn_getCSRComments(driver, webWait).sendKeys("This is Refunds for automation test purpose !");
					Utils.takeScreenshot(driver, strTestCaseName);
					objPayDetails.fn_clickGenerateRefund(driver, webWait).click();
					webWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='page-instructions']/p[2]")));
					objPayDetails.fn_clickProcessRefund(driver, webWait).click();
					
					//TODO: For Refunds, alerts are flagged; so accept them for refunds to be successfully booked.
					Thread.sleep(3500);
					String strHandle = driver.getWindowHandle();					
					Alert alertRefundsAcceptance = driver.switchTo().alert();					
					alertRefundsAcceptance.accept();
					Thread.sleep(3500);
					driver.switchTo().window(strHandle);
					driver.switchTo().defaultContent();
					Utils.takeScreenshot(driver, strTestCaseName);
					
					String strRefundsMessage = objPayDetails.fn_getRefundStatusMessage(driver, webWait).getAttribute("innerText").toString().trim();
					if(strRefundsMessage.contains("successfully booked"))
					{
						Log.info("Refunds Booking has been successfully done for amount: "+strRefundAmount);
						blnVerifyRefundInitiation = true;
					}
					else
					{
						Log.error("ERROR: The Booking of Refunds for Payments Transaction ID: "+strPaymentsTxnID+" couldn't be done successfully!");
						Reporter.log("ERROR: The Booking of Refunds for Payments Transaction ID: "+strPaymentsTxnID+" couldn't be  done successfully!");
					}
					break;
				}
				else
				{
					Log.error("ERROR: The Booking of Refunds for Payments Transaction ID: "+strPaymentsTxnID+" couldn't be successfully done !");
					Reporter.log("ERROR: The Booking of Refunds for Payments Transaction ID: "+strPaymentsTxnID+" couldn't be successfully done !");				
				}
			}
		}
		return blnVerifyRefundInitiation;
	}
	

/*    
	Function fn_SelectLatestInvoice: This method will verify if the changed Client Defined Field is retained.
	Inputs: Assumes that the Invoice has been generated.
	
	Outputs: Boolean status for the Invoice selection success or failure.	   						
*/	
    public Boolean fn_SelectLatestInvoice(WebDriver driver, WebDriverWait webWait) throws Exception
    {
    	//TODO: Create object for the classes to be used for accessing reusable codes.
    	ChangeAccountPONumber objChangeAcctPO = new ChangeAccountPONumber();
    	Boolean blnLatestInvoiceSelected = false;
    	
    	//TODO: Navigate to the Invoices Tab for the account requested and select latest generated Invoice.
    	try
    	{
    		objChangeAcctPO.fn_clickAccounts(driver, webWait).click();
           	objChangeAcctPO.fn_clickStatementsInvoice(driver, webWait).click();
           	objChangeAcctPO.fn_getInvoicesDataTbale(driver, webWait);
           	objChangeAcctPO.fn_clickInvoices(driver, webWait).click();
           	objChangeAcctPO.fn_getInvoicesDataTbale(driver, webWait);
       		Thread.sleep(3500);       		
    		WebElement webInvoicesDetails = objChangeAcctPO.fn_getInvoicesDataTbale(driver, webWait).findElement(By.tagName("tbody"));
    		List<WebElement> lstwebReadInvoicesRow = webInvoicesDetails.findElements(By.tagName("tr"));
    		for(WebElement row: lstwebReadInvoicesRow)
    		{
    			List<WebElement> cols = row.findElements(By.tagName("td"));
    			
    			if(cols.size() >= 2)
    			{
    				String strInvoiceNumber = cols.get(1).getAttribute("innerText").toString().trim();
    				Log.info("The Invoiced Number selected for verifications is: "+strInvoiceNumber);
    				cols.get(1).click();
    				blnLatestInvoiceSelected = true;
    				break;
    			}
    		}
    		objChangeAcctPO.fn_getInvoicesDataTbale(driver, webWait);    		
    	}   	
    	catch (Exception exception)
    	{
    		Log.error("ERROR: Invoice selection couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Invoice selection couldn't be done due to exception:  "+exception.toString());
       		throw exception;
    	}
    	
    	return blnLatestInvoiceSelected;
    }
    	

/*
	Function fn_AssignCreditCoupon: Takes in the details for Account Number for which Coupons are to be Applied.
	Inputs: strAccountNum - account number for which Coupon is to be applied.
			Coupon Text - The Coupon Name which is to be applied.
	
	Outputs: Boolean status for the success or failure of applying Coupon.
*/	
	public Boolean fn_AssignCreditCoupon(WebDriver driver, WebDriverWait webWait, String strAccountNum, String strCoupon
			, String strTestCaseName) throws Exception
	{
    	//TODO: create class object to access the page objects for accessing the Payments.
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
		ParentChildDiscounting objParentChildDiscount = new ParentChildDiscounting();
		
		Boolean blnCouponAssigned = false;
		String strCouponMessage = null;
		
		//TODO: Search the Account number and verify if the account exists to assign coupon to it.
    	Boolean blnSearchStatus = objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
    	if(blnSearchStatus == false)
    		VerificationMethods.assertTrue(false, "ERROR: Account Search failed for Account Number: "+strAccountNum);
		
    	//TODO: Navigate to Payments & Credits -> Coupons Tab to assign Coupon to the Account.
    	objParentChildDiscount.fn_getAccountHeirarchyGlanceTable(driver, webWait);
    	objParentChildDiscount.fn_clickPaymentsCredits(driver, webWait).click();
    	objParentChildDiscount.fn_getDataTbale(driver, webWait);
    	objParentChildDiscount.fn_clickCoupons(driver, webWait).click();
    	objParentChildDiscount.fn_getDataTbale(driver, webWait);
    	   	//TODO: Verify Coupon Assignment to avoid any further re-assignment of Coupon.
    		Thread.sleep(3500);
			WebElement webCouponsListTable = objParentChildDiscount.fn_getDataTbale(driver, webWait).findElement(By.tagName("tbody"));
			List<WebElement> lstwebCouponsListTableRows = webCouponsListTable.findElements(By.tagName("tr"));
			for(WebElement row : lstwebCouponsListTableRows)
			{
				List<WebElement> cols = row.findElements(By.tagName("td"));
				
				if(cols.size() >= 4)
				{
					String strTableCouponStatus = cols.get(0).getAttribute("innerText").toString().trim();
					String strTableCouponCode = cols.get(1).getAttribute("innerText").toString().trim();
					if(strTableCouponCode.contains(strCoupon) && strTableCouponStatus.contains("Cancel"))
					{
						blnCouponAssigned = true;
						Log.info("The Coupon Code '"+strCoupon+"' is already assigned to the Account ID: "+strAccountNum);
						
						objParentChildDiscount.fn_clickAccounts(driver, webWait).click();
						return blnCouponAssigned;
					}
				}
			}
    	objParentChildDiscount.fn_clickAssignCoupon(driver, webWait).click();
    	objParentChildDiscount.fn_clickValidate(driver, webWait);
    	objParentChildDiscount.fn_getValidateText(driver, webWait).clear();
    	objParentChildDiscount.fn_getValidateText(driver, webWait).sendKeys(strCoupon);
    	objParentChildDiscount.fn_clickValidate(driver, webWait).click();
    	if(objParentChildDiscount.fn_getCouponAssignMessage(driver, webWait).isDisplayed())
    	{
    		strCouponMessage =  objParentChildDiscount.fn_getCouponAssignMessage(driver, webWait).getAttribute("innerText").toString().trim();
    		if(strCouponMessage.contains("Invalid Coupon."))
    			VerificationMethods.assertTrue(false, "ERROR: An Invalid Coupon Code has been entered, TC can't proceed further !");
    	}
    	objParentChildDiscount.fn_clickAssignCouponConfirm(driver, webWait).click();
    	strCouponMessage =  objParentChildDiscount.fn_getCouponAssignMessage(driver, webWait).getAttribute("innerText").toString().trim();
		if(strCouponMessage.contains("successfully assigned"))
			blnCouponAssigned = true;
		else
			VerificationMethods.assertTrue(false, "ERROR: An error has occurred while assigning Coupon, TC can't proceed further !");
		
		//TODO: Verify Coupon Assignment again in Assigned Coupons Table although we have verified the status message above.
		webCouponsListTable = objParentChildDiscount.fn_getDataTbale(driver, webWait).findElement(By.tagName("tbody"));
		lstwebCouponsListTableRows = webCouponsListTable.findElements(By.tagName("tr"));
		for(WebElement row : lstwebCouponsListTableRows)
		{
			List<WebElement> cols = row.findElements(By.tagName("td"));
			
			if(cols.size() >= 1)
			{
				String strTableCouponCode = cols.get(1).getAttribute("innerText").toString().trim();
				if(strTableCouponCode.contains(strCoupon))
				{
					blnCouponAssigned = true;
					Log.info("The Coupon Code '"+strCoupon+"' is successfully assigned to the Account ID: "+strAccountNum);
					break;
				}
			}
		}	
		objParentChildDiscount.fn_clickAccounts(driver, webWait).click();
		
		return blnCouponAssigned;
	}
	
/*
	Function fn_CancelCreditCoupon: Takes in the details for Account Number for which Already Assigned Coupons are to be Cancelled.
	Inputs: strAccountNum - account number for which Assigned Coupons are to be Cancelled.
			Coupon Text - The Coupon Name which is to be applied.
	
	** This method assumes that the Coupons Tab is navigated before it is being called for canceling the already Assigned Coupon **- 
	
	Outputs: Boolean status for the success or failure of Canceling Applied Coupon.
*/	
	public Boolean fn_CancelCreditCoupon(WebDriver driver, WebDriverWait webWait, String strAccountNum, String strCoupon
			, String strTestCaseName) throws Exception	
	{
		//TODO: Create object for the classes to be used for accessing reusable codes.
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
		ParentChildDiscounting objParentChildDiscount = new ParentChildDiscounting();
		
		Boolean blnCouponCancelled = false;
		Boolean blnSearchStatus = false;
		String strCouponMessage = null;
		
		//TODO: Search the Account number and verify if the account exists to assign coupon to it.
    	blnSearchStatus = objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
    	if(blnSearchStatus == false)
    		VerificationMethods.assertTrue(false, "ERROR: Account Search failed for Account Number: "+strAccountNum);		
		
	   	//TODO: Verify Coupon is already assigned and then cancel it.
		objParentChildDiscount.fn_clickPaymentsCredits(driver, webWait).click();
		objParentChildDiscount.fn_getDataTbale(driver, webWait);
		objParentChildDiscount.fn_clickCoupons(driver, webWait).click();
		objParentChildDiscount.fn_getDataTbale(driver, webWait);
		Log.info("Verifying if coupon '"+strCoupon+"' has already been assigned to the account; if yes, cancel it !");
		Thread.sleep(3500);
		WebElement webCouponsListTable = objParentChildDiscount.fn_getDataTbale(driver, webWait).findElement(By.tagName("tbody"));
		List<WebElement> lstwebCouponsListTableRows = webCouponsListTable.findElements(By.tagName("tr"));
		for(WebElement row : lstwebCouponsListTableRows)
		{
			List<WebElement> cols = row.findElements(By.tagName("td"));
			
			if(cols.size() >= 2)
			{
				String strTableCouponStatus = cols.get(0).getAttribute("innerText").toString().trim();
				String strTableCouponCode = cols.get(1).getAttribute("innerText").toString().trim();
				if(strTableCouponCode.contains(strCoupon) && strTableCouponStatus.contains("Cancel"))
				{
					driver.findElement(By.xpath("//a/span[text()='Cancel']")).click();
					Thread.sleep(2000);
					objParentChildDiscount.fn_clickOKCancelingCoupon(driver, webWait).click();
					Thread.sleep(2000);
					strCouponMessage = objParentChildDiscount.fn_getCouponAssignMessage(driver, webWait).getAttribute("innerText").toString().trim();
					
					if(strCouponMessage.contains("successfully cancelled"))
					{					
						blnCouponCancelled = true;
						Log.info("The Coupon Code '"+strCoupon+"' is successfully cancelled for the Account ID: "+strAccountNum);
						break;
					}
					else
						VerificationMethods.assertTrue(false, "ERROR: There was an error while cancelling the already assigned coupon '"+strCoupon+"' !");
				}
			}
		}
		objParentChildDiscount.fn_clickAccounts(driver, webWait).click();
		
		return blnCouponCancelled;
	}


/*
	Function fn_ReadInvoiceDetails: Takes in the details for Account Number for which Already Assigned Coupons are to be Cancelled.
	Inputs: strAccountNum - account number for which Assigned Coupons are to be Cancelled.			
	
	** This method assumes that the latest Invoice has already been navigated ** 
	
	Outputs: List of List pair for the details of the Invoice generated.
*/	
	public List<List<NameValuePair>> fn_ReadInvoiceDetails (WebDriver driver, WebDriverWait webWait) throws Exception
	{
		//TODO: Create object for the classes to be used for accessing reusable codes.
		ChangeAccountPONumber objChangeAcctPO = new ChangeAccountPONumber();		
		
		List<List<NameValuePair>> lstpairReadInvoice = new ArrayList<List<NameValuePair>>();
				
		try
		{
			//TODO: Read the Headers for the Invoice Table into an array.
			WebElement webInvoiceHeaders = objChangeAcctPO.fn_getInvoicesDataTbale(driver, webWait).findElement(By.tagName("thead"));
			String strHeaders = webInvoiceHeaders.getAttribute("outerText").toString().trim();
			String[] arrHeaders = strHeaders.split("\n");
			
			//TODO: Now use the Headers to generate pair of header and it's corresponding row value.
			Thread.sleep(3500);
			WebElement webInvoiceBody = objChangeAcctPO.fn_getInvoicesDataTbale(driver, webWait).findElement(By.tagName("tbody"));
			List<WebElement> lstwebInvoiceRows = webInvoiceBody.findElements(By.tagName("tr"));
			for(WebElement rows: lstwebInvoiceRows)
			{
				List<NameValuePair> lstReadInvoice = new ArrayList<NameValuePair>();
				
				List<WebElement> cols = rows.findElements(By.tagName("td"));				
				if (cols.get(2).getAttribute("innerText").toString().trim().contains(" Taxes"))	// indicates Invoice table is read.
					break;
				if(cols.size() >= 2)// creating a list pair of header and each row.
				{
					for (int i = 0 ; i < cols.size(); i++)
						lstReadInvoice.add(new BasicNameValuePair(arrHeaders[i], cols.get(i).getAttribute("innerText").toString().trim()));
				}
				
				lstpairReadInvoice.add(lstReadInvoice);	//creating the list of list pairs with headers and it's values.
			}			
		}
		catch (Exception exception)
		{
			Log.error("ERROR: There is an error captured while reading the Invoice details as: "+exception);
			Reporter.log("ERROR: There is an error captured while reading the Invoice details as: "+exception);			
		}	
		
		return lstpairReadInvoice;
	}
	
/*
	Function fn_GenerateInvoiceStatement: Generates Statement for the newly created Invoice after it is approved.
	
	** This method assumes that the Account has already been searched. **- 
	
	Outputs: Boolean status of the Statement generation.
*/	
	public Boolean fn_GenerateInvoiceStatement (WebDriver driver, WebDriverWait webWait, String strTestCaseName) throws Exception
	{
		//TODO: Create object for the classes to be used for accessing reusable codes.
		VerifyGLLocationSegment objGLLocation = new VerifyGLLocationSegment();				
		
		Boolean blnStatementGenerated = false;
		
		//Navigate to the Statements Tab, generate a statement and validate it's status.
		try
		{
			objGLLocation.fn_clickStatementsInvoices(driver, webWait).click();			
			objGLLocation.fn_getInvoicesDataTable(driver, webWait);
			Thread.sleep(1500);
			objGLLocation.fn_clickStatementsTab(driver, webWait).click();
			objGLLocation.fn_getInvoicesDataTable(driver, webWait);
			objGLLocation.fn_clickGenerateStatement(driver, webWait).click();
			objGLLocation.fn_clickGenerateStatementButton(driver, webWait);
			Thread.sleep(1500);
			objGLLocation.fn_selectEmailOptions(driver, webWait, "1").click();
			objGLLocation.fn_clickGenerateStatementButton(driver, webWait).click();
			Thread.sleep(1500);
			
			objGLLocation.fn_getGenerateStatementMessage(driver, webWait);			
			Utils.takeScreenshot(driver, strTestCaseName);
			String strStatementGenerationMsg = objGLLocation.fn_getGenerateStatementMessage(driver, webWait).getAttribute("innerText").toString().trim();
			if(strStatementGenerationMsg.contains("successfully generated."))
			{
				blnStatementGenerated = true;
				Log.info("The Statement has been successfully generated for the Invoice.");
			}
		}
		catch (Exception exception)
		{
			Log.error("ERROR: There is an error captured while generating Statement for the Invoice created as: "+exception);
			Reporter.log("ERROR: There is an error captured while generating Statement for the Invoice created as: "+exception);		
		}
		
		return blnStatementGenerated;
	}
	
	/*
	Function fn_CreatePromotionalCredit: Creates a promotional Credit.
	
	** This method assumes that the Account has already been searched. **- 
	
	Outputs: Boolean status of Promotional credit generation
*/
	public Boolean fn_CreatePromotionalCredit (WebDriver driver, WebDriverWait webWait, String strTestCaseName,String strCreditAmnt,String strReason,String strCreditNum,String strMonths) throws Exception
	{
		Boolean blnPromotionalCreditGenerated = false;
		PaymentDetailsFinancialReport objPayments = new PaymentDetailsFinancialReport();
		
		//TODO : Click on Payments and Credits link
		objPayments.fn_clickPaymentsCredits(driver, webWait).click();
		
		Thread.sleep(2000);
		//TODO : Click on Credits Link
		objPayments.fn_clickCredits(driver, webWait).click();		
					
		Thread.sleep(2000);
		//TODO : Click on ViewRecurringCreditsLink
		objPayments.fn_clickViewRecurringCreditsLink(driver, webWait).click();
		
		Thread.sleep(2000);
		//TODO : Click on CreateNewRecurringCreditLink
		objPayments.fn_CreateNewRecurringCreditLink(driver, webWait).click();
		
		Thread.sleep(2000);
		//TODO : Enter Credit Amount
		objPayments.fn_EnterCreditAmount(driver, webWait).sendKeys(strCreditAmnt);
		
		Thread.sleep(2000);
		//TODO : Enter Credit Reason
		objPayments.fn_SelectCreditReason(driver, webWait).selectByVisibleText(strReason);
		
		Thread.sleep(2000);
		
		//TODO : Enter CSR Comments
		objPayments.fn_getCSRComments(driver, webWait).sendKeys("Adding_PromotionalCredit");
		Thread.sleep(2000);
		
		//Select Generation Frequency
		objPayments.fn_SelectMultiple(driver, webWait).click();
		
		Thread.sleep(2000);
		//Select Number of credits:
		objPayments.fn_NumberofCredits(driver, webWait).selectByVisibleText(strCreditNum);
		
		Thread.sleep(2000);
		////Select Months between credits
		objPayments.fn_MonthsBetweenCredits(driver, webWait).selectByVisibleText(strMonths);
		
		Thread.sleep(2000);
		//Create Credit
		objPayments.fn_CreateCredit(driver, webWait).click();
		
		Thread.sleep(2500);
		
		//Create Credit
		objPayments.fn_CreateCredit(driver, webWait).click();
		
		blnPromotionalCreditGenerated=true;
		
		Thread.sleep(2000);
		
		return blnPromotionalCreditGenerated;
	}
	
	
	
	/*
	Function fn_ApplyCredit: Creates Cash/Service Credit
	
	** This method assumes that the Account has already been searched. **- 
	
	Outputs: Boolean status if the credit is generated
*/
	public Boolean fn_ApplyCredit (WebDriver driver, WebDriverWait webWait, String strTestCaseName,String strCreditAmt,String strReason, String strCreditType) throws Exception
	{
		Boolean blnCreditGenerated = false;
		PaymentDetailsFinancialReport objPayments = new PaymentDetailsFinancialReport();
		
		//TODO : Click on Payments and Credits link
		objPayments.fn_clickPaymentsCredits(driver, webWait).click();
		
		Thread.sleep(2000);
		//TODO : Click on Credits Link
		objPayments.fn_clickCredits(driver, webWait).click();		
					
		Thread.sleep(2000);
		//TODO : Click on Create a New Credit Link
		objPayments.fn_clickCreateNewCreditLink(driver, webWait).click();				
		
		Thread.sleep(2000);
		//TODO : Enter Credit Amount
		objPayments.fn_EnterCreditAmount(driver, webWait).sendKeys(strCreditAmt);
		
		List<WebElement>CreditType = driver.findElements(By.name("inCreditType"));
		
		if (strCreditType.equalsIgnoreCase("Cash")){
			//TODO : Select Credit Type
			CreditType.get(1).click();
		}
		if (strCreditType.equalsIgnoreCase("Service")){
			//TODO : Select Credit Type
			CreditType.get(0).click();
		}		
		
		Thread.sleep(2000);
		//TODO : Enter Credit Reason
		objPayments.fn_SelectCreditReason(driver, webWait).selectByVisibleText(strReason);
		
		Thread.sleep(2000);
		
		//TODO : Enter CSR Comments
		objPayments.fn_getCSRComments(driver, webWait).sendKeys("Adding "+strCreditType);
		Thread.sleep(2000);		
		
		//Create Credit
		objPayments.fn_CreateCredit(driver, webWait).click();
		
		//Click on create credit button
		objPayments.fn_CreateCredit(driver, webWait).click();
		
		blnCreditGenerated=true;
		
		Thread.sleep(2000);
		
		return blnCreditGenerated;
	}
	
	
	
	 /* This function adjust the usage date, loads usage and generate invoice*/
    
 	public void fn_E2EGenerateInvoice(WebDriver driver,WebDriverWait webWait, String strAPIURL, String strFilename, String strAcctNum, String strTestCaseName, String strInvoiceType, String strPlanName) throws Exception
 	{
 		
 		SupplimentalPlans Objplan = new SupplimentalPlans();
 		AccountFunctions_Invoices ObjInvoices = new AccountFunctions_Invoices();
 		AccountFunctions objfnctns = new AccountFunctions();
 		AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
 		String[] arrParentChild = null;
 		//TODO: Load Data for specified account for current date.
   		String LOAD_USAGE_FILE_PATH = System.getProperty("user.dir")
                + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
                + System.getProperty("file.separator") + strFilename;
   		
   		arrParentChild = strAcctNum.split("#");        		
   			
			//TODO : Verifying whether supplemental plan is assigned to the account otherwise assign it to the account
		String strPlanNumber = objAcctFuncPlans.fn_GetPlans(driver, webWait, strPlanName, strTestCaseName);
		if(strPlanNumber.contentEquals(""))
			throw new Exception("Supplemental Plan Number couldn't be retrieved !");
		
		for (String strAcct : arrParentChild){	
			boolean blnSupplementalPlansStatus = objAcctFuncPlans.fn_VerifySupplementalPlans(driver, webWait, strAcct, strTestCaseName);
			if(blnSupplementalPlansStatus == false)
			{
				objAcctFuncPlans.fn_AssignSuppPlan(driver, webWait, strAcct, strPlanNumber, strAPIURL);
				Log.info("Supplemental Plan with number "+strPlanNumber+" has been assigned to the Account Number: "+strAcctNum);
			}	
   			
   		}    		
   		
   		
		 //TODO : Adjust Billing dates for the Account to Generate Invoice  	
		 boolean blnAdjustDate=objfnctns.fn_AdjustAcctBillDates(driver, webWait, strAPIURL, arrParentChild[0], strTestCaseName);
		 if (blnAdjustDate==true)
		 {
			 Log.info("Account Billing Date has been set successfully");
		 }
		 else
		 {
			 Log.info("ERROR: Account Billing Date has not been set");
			 utility.VerificationMethods.assertTrue(false, "ERROR: Since the Billing Date's can't be adjusted, Invoicing will fail, so exiting TC !");
		 }
    	//TODO: Fetching the Date for Loading Usage	
   		String strLoadDataDate = objfnctns.fn_getLoadDataDate();
       	Log.info("Loading Account Usage for the date '"+strLoadDataDate+"'..");
       	
       	Thread.sleep(200);
       	//TODO: Loading the Usage
       	objfnctns.fn_LoadAccountUsage(driver, webWait, strAPIURL, LOAD_USAGE_FILE_PATH, arrParentChild[1], strLoadDataDate);
       	Thread.sleep(2000);
  		           	
		//TODO: Generate Advance Invoice for the Account for which data is currently uploaded & Verify Print Invoice.
       	boolean blnInvoiceGenerated = ObjInvoices.fn_GenerateInvoice(driver, webWait, arrParentChild[1], strInvoiceType);
       	
       	
       	Thread.sleep(2000);
       	if(blnInvoiceGenerated == true)
       	{
       		boolean blnInvoiceApproved = ObjInvoices.fn_ApproveAdvancedInvoices(driver, webWait);
       		if(blnInvoiceApproved == false)
       			Log.info("ERROR: Invoice generated couldn't be approved !");
       		
       		//Since Invoice has been generated and Approved, we generate the Statement. 
       		webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Accounts"))).click();
       		//ObjInvoices.fn_GenerateInvoiceStatement(driver, webWait, strTestCaseName);
       	}
       	else
       	{
       		Log.info("ERROR: Invoice couldn't be generated !");
       		utility.VerificationMethods.assertTrue(false, "ERROR: Invoice is not generated, so exiting TC !");
       	}
 	}

	
/*    
	Function fn_SelectLatestInvoice: This method will verify if the changed Client Defined Field is retained.
	Inputs: Assumes that the Invoice has been generated.
	
	Outputs: Boolean status for the Invoice selection success or failure.	   						
*/	
    public Boolean fn_SelectInvoice(WebDriver driver, WebDriverWait webWait, String strAccountNum, String strInvoiceNum) throws Exception
    {
    	//TODO: Create object for the classes to be used for accessing reusable codes.
    	Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
    	ChangeAccountPONumber objChangeAcctPO = new ChangeAccountPONumber();
    	    	
    	Boolean blnInvoiceSelected = false;
    	
    	//TODO: Navigate to the Invoices Tab for the account requested and select generated Invoice as requested.
    	try
    	{
    		//searching account to navigate to the account for which Invoice is to be searched.
    		objClientDefAct.AccountSearch(driver, webWait, strAccountNum);    		
           	objChangeAcctPO.fn_clickStatementsInvoice(driver, webWait).click();
           	objChangeAcctPO.fn_getInvoicesDataTbale(driver, webWait);
           	objChangeAcctPO.fn_clickInvoices(driver, webWait).click();
           	objChangeAcctPO.fn_getInvoicesDataTbale(driver, webWait);
       		Thread.sleep(3500);       		
    		
       		WebElement webInvoicesDetails = objChangeAcctPO.fn_getInvoicesDataTbale(driver, webWait).findElement(By.tagName("tbody"));
    		List<WebElement> lstwebReadInvoicesRow = webInvoicesDetails.findElements(By.tagName("tr"));
    		Integer intForLoop = 0;
    		for(WebElement row: lstwebReadInvoicesRow)
    		{
    			List<WebElement> cols = row.findElements(By.tagName("td"));
    			intForLoop ++;
    			
    			if(cols.size() >= 2)
    			{
    				String strInvoiceNumber = cols.get(1).getAttribute("innerText").toString().trim();
    				if(strInvoiceNum.contentEquals(strInvoiceNumber)){
    					
    					Log.info("The Invoiced Number selected for verifications is: "+strInvoiceNumber);
        				cols.get(1).click();
        				blnInvoiceSelected = true;
        				break;
    				}
    			}
    			
    			if(intForLoop == lstwebReadInvoicesRow.size()){
    				
    				Log.error("ERROR: The requested Invoice Number '"+strInvoiceNum+"' isn't available for the Account: "+strAccountNum);
    				Reporter.log("ERROR: The requested Invoice Number '"+strInvoiceNum+"' isn't available for the Account: "+strAccountNum);
    				blnInvoiceSelected = false;
    			}
    		}
    		objChangeAcctPO.fn_getInvoicesDataTbale(driver, webWait);    		
    	}   	
    	catch (Exception exception)
    	{
    		Log.error("ERROR: Invoice selection couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Invoice selection couldn't be done due to exception:  "+exception.toString());
       		throw exception;
    	}
    	
    	return blnInvoiceSelected;
    }
    
    
    
/*    
	Function fn_getStatementTransactions: This method will navigate to Statements & create a Pair of values 
											for Statement & it's co-related Transaction details.
	Inputs: Takes in the Account Number for which the Statements details are to be captured.
	
	Outputs: Returns the List of Name Value Pairs for the Statement(s) and its corresponding Transactions.	   						
*/	
    public List<List<NameValuePair>> fn_getStatementTransactions(WebDriver driver, WebDriverWait webWait, String strAccountNum, String strAPIURL) throws Exception {
    	
    	//TODO: Create object for the classes to be used for accessing reusable codes.
    	ApiHandler objAPIHandler = new ApiHandler();
    	AccountFunctions objAccountFunc = new AccountFunctions();
    	Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
    	ChangeAccountPONumber objChangeAcctPO = new ChangeAccountPONumber();    	
    	Utils objUtils = new Utils();

    	JSONObject jsonStatementTxn = new JSONObject();
    	List<List<NameValuePair>> lstpairReadStatements = new ArrayList<List<NameValuePair>>();
    	String strPrevStatementNum = "";
    	Integer intNumOfStmnt = 0;
    	
    	//TODO: Get the Full URL with the authentication details for current instance & make a Get call with JSOn Response. 
        String strFullUrl = objAccountFunc.fn_GetFullUrl(driver, webWait, strAPIURL, "get_acct_statement_history");
        strFullUrl = strFullUrl + "&acct_no="+strAccountNum + "&output_format=json";        
        jsonStatementTxn = objAPIHandler.makeGetCall(strFullUrl);
    	JSONArray arrayStatementTxn = jsonStatementTxn.getJSONArray("statement_history");
    	
    	//TODO: Navigate to the Statements Tab for the searched Account and create list of Statement(s) with Transaction details.
    	objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
    	Thread.sleep(1500);
    	objChangeAcctPO.fn_clickStatementsInvoice(driver, webWait).click();
    	objChangeAcctPO.fn_getDataTbale(driver, webWait);
    	objChangeAcctPO.fn_clickStatementsTab(driver, webWait).click();
    	Thread.sleep(2500);
    	
/*    	//create the list of Statement(s) so it can be navigated to generate the pair list using Transactions.
    	WebElement webStatementsTable = objChangeAcctPO.fn_getDataTbale(driver, webWait).findElement(By.tagName("tbody"));
    	List<WebElement> lstwebStatementsTableRows = webStatementsTable.findElements(By.tagName("tr"));
    	ArrayList<String> arrStatementNum = new ArrayList<>(lstwebStatementsTableRows.size());
    	for(WebElement rows : lstwebStatementsTableRows){
    		
    		List<WebElement> cols = rows.findElements(By.tagName("td"));    		
    		
    		if(cols.size() != 0){
    			arrStatementNum.add(cols.get(0).getAttribute("innerText").toString().trim());
    		}    		
    	}
*/    	
    	//Traverse the read list of Statement Numbers and then navigate to Transactions for details.
    	for(Integer intJSON = 0 ; intJSON < arrayStatementTxn.length(); intJSON ++){
    		
    		JSONObject jsonStatement = arrayStatementTxn.getJSONObject(intJSON);    				
    		String strStatementNum = jsonStatement.getString("statement_no");
    		
    		//build the random Xpath to navigate to the Statement # for navigating to its corresponding Transactions.
    		if(!strStatementNum.contains("Next")){
    			
    			//TODO: Govern only 4 OLD Statements & 1 Latest Statement are considered for verification.
    			if(intNumOfStmnt == 3){
        			
    				intJSON = arrayStatementTxn.length() - 2;	//select the latest statement as final entry.
    			}
    			
    			objChangeAcctPO.fn_getStatementSearch(driver, webWait).sendKeys(strStatementNum);
    			Thread.sleep(3000);
    			
	    		String strXpath = "//*[contains(@id, 'DataTables_Table_')]/tbody/tr/td/a/font[text()='"+strStatementNum+"']";
	    		driver.findElement(By.xpath(strXpath)).click();
	    		objChangeAcctPO.fn_getDataTbale(driver, webWait);
	    		Thread.sleep(3500);
	    		
	    		WebElement webTransactionsTable = objChangeAcctPO.fn_getDataTbale(driver, webWait).findElement(By.tagName("tbody"));
	    		List<WebElement> lstwebTransactionsTableRows = webTransactionsTable.findElements(By.tagName("tr"));
	    		for(WebElement txnRows : lstwebTransactionsTableRows){
	    			
	    			List<NameValuePair> pairTxnDetails = new ArrayList<NameValuePair>();
	    			
	    			List<WebElement> txnCols = txnRows.findElements(By.tagName("td"));
	    			if(txnCols.size() != 0){
	    				
	    				String strTransactionNum = txnCols.get(0).getText();
	    				String strTransactionDate = txnCols.get(1).getText();
	    				String strTxnDescription = txnCols.get(2).getText();
	    				String strChargeAmount = txnCols.get(4).getText();
	    				
	    				//generate the Name Value Pair for the Statements and Transaction details.
	    				if(!strChargeAmount.contentEquals("0.00") && !strChargeAmount.contentEquals(" ")){
	    					
	    					pairTxnDetails.add(new BasicNameValuePair("Statement #", strStatementNum));
	    					pairTxnDetails.add(new BasicNameValuePair("Due Date", jsonStatement.getString("due_date")));
	    					strTransactionDate = objUtils.fn_getDateFormatted(strTransactionDate, "mm-dd-yyyy");
	    					pairTxnDetails.add(new BasicNameValuePair("Transaction Date", strTransactionDate));
	    					pairTxnDetails.add(new BasicNameValuePair("Transaction #", strTransactionNum));
	    					pairTxnDetails.add(new BasicNameValuePair("Transaction Description", strTxnDescription));
	    					strChargeAmount = strChargeAmount.replaceAll(",", "");
	    					pairTxnDetails.add(new BasicNameValuePair("Amount", strChargeAmount));
	    					
	    					lstpairReadStatements.add(pairTxnDetails); //add the Name Value Pair to final List.
	    				}
	    			}    			
	    		}
	    		//navigate back to the Statements Tab for getting the next Statement information.
	    		objChangeAcctPO.fn_clickStatementsTab(driver, webWait).click();
	    		Thread.sleep(2500);
    		}
    		//TODO: Govern only 4 OLD Statements & 1 Latest Statement are considered for verification.
    		if(!strPrevStatementNum.contentEquals(strStatementNum) && lstpairReadStatements.size() > 0){
    			
    			intNumOfStmnt ++;
    		}
    		strPrevStatementNum = strStatementNum;
    	}
    	
    	return lstpairReadStatements;    	
    }    
}