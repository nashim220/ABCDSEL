/*
 Author     		:	Aashish Bhutkar

 Class Name			: 	AccountFunctions_Invoices 
 Purpose     		: 	Purpose of this file is :
						1. To perform various Account related functions related to Invoices.

 Date       		:	06/06/2016
 Modified By		: 	Aashish Bhutkar, Madhavi JN., Namrata Akarte
 Version Information:	Version 1.0
  
 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - All Rights Reserved. 
*/

package appModules;

import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import pageObjects.AccountsPaymentsCredits;
import pageObjects.AccountsStatementsInvoices;
import pageObjects.AriaEOM;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;

public class AccountFunctions_Invoices {

/*
	Function fn_ApproveAdvancedInvoices: Method to approved generated Invoice; Has to be called immediately ONLY after generating invoice.
	Outputs: Boolean status of the approval for generated invoice.   
	
	  
*/	 		
	public Boolean fn_ApproveAdvancedInvoices(WebDriver driver, WebDriverWait webWait) throws Exception {
		
		//TODO: Create object for the classes to be used for accessing reusable codes.
		AriaEOM objAriaEOM = new AriaEOM();
		AccountsStatementsInvoices objAcctStmntInv = new AccountsStatementsInvoices();
		
		Boolean blnApprovedInvoice = false;
		
		WebElement webInvoicesDetails = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody")); 
		List<WebElement> lstwebReadInvoicesRow = webInvoicesDetails.findElements(By.tagName("tr"));
		for(WebElement row: lstwebReadInvoicesRow) {
		
			List<WebElement> cols = row.findElements(By.tagName("td"));
			
			if(cols.size() >= 2) {
				
				cols.get(0).click();	// Clicking the hyperlinked text for Approve/View link.		
				break;
			}
		}
		
		webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='accountsSectionBottomContainer']/div/div/form/table")));
		objAcctStmntInv.fn_clickApprove(driver, webWait).click();		
		objAcctStmntInv.fn_clickApproveButton(driver, webWait).click();
		Thread.sleep(5000);
		
		String strApprovalMessage = objAcctStmntInv.fn_getFlaggedMessage(driver, webWait).getAttribute("innerText").toString().trim();
		if(strApprovalMessage.contains(" approved.")) {
			
			blnApprovedInvoice = true;
			Log.info("Generated Advance Invoice has been successfully approved !");
		}		
		
		return blnApprovedInvoice;
	}	

/*
 	Purpose     	:	Function for Reading the data on the View & Print Frame and return it as a WebElement.
*/

	public WebElement fn_ReadViewPrintPage(WebDriver driver) throws Exception {

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
	Inputs: strAccNum - Account number for which Invoice is to be Generated.
			strInvoiceType - Accepts Values "Immediate", "Pending" or "Advanced" ONLY.
			strGenerateOption - Accepts Values "Account", "BillingGroup" or "MPI" ONLY.
			strOptionType - Accepts string values for Billing Group designed for Account OR MPI string based on "strGenerateOption" selected.
							e.g: Billing group : "21146" ; MPI - "54947 - Cloud Services"
*/  
	public Boolean fn_GenerateInvoice(WebDriver driver, WebDriverWait webWait, String strAccNum, String strInvoiceType, 
			String strGenerateOption, String strOptionType, String strTestCaseName) throws Exception {

		//TODO: Create object for the classes to be used for accessing reusable codes.
		AriaEOM objAriaEOM = new AriaEOM();
		AccountsStatementsInvoices objAcctStmntInv = new AccountsStatementsInvoices();
		Cls_ChangeDeleteClientDefinedFieldActns obj = new Cls_ChangeDeleteClientDefinedFieldActns();
		
		Boolean blnInvoiceGenerated = false;
		
		//TODO : Search Account		
		boolean blnAccountSearch = obj.AccountSearch(driver, webWait, strAccNum);
		objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);
		
		if (blnAccountSearch==true){	

			//Click link Statements and Invoices
			objAriaEOM.fn_clickStatementsInvoices(driver, webWait).click();			
			Thread.sleep(2500);
			//Click on Invoices Tab
			objAcctStmntInv.fn_clickInvoicesTab(driver, webWait).click();
			objAriaEOM.fn_getDataTable(driver, webWait);
			Thread.sleep(1500);			
			//Click on Generate Invoice link
			objAcctStmntInv.fn_clickGenerateInvoicesLink(driver, webWait).click();
			Thread.sleep(2500);
			
			List<WebElement> options = driver.findElements(By.name("inGenerateOption"));			
			//TODO : Select Generate Option
			if (strGenerateOption.equals("Account")){
		 		//Select Generate Option - here Account Invoice
				options.get(0).click();		
				Log.info("Generate Option is selected as Account.");
		 	}
		 	else if(strGenerateOption.equals("BillingGroup")) {
		 		//Select Generate Option - here Billing Group
		 		options.get(1).click();
		 		Thread.sleep(2500);
				Select lstBillingGrp = new Select (driver.findElement(By.id("inBillingGroupNo")));
				lstBillingGrp.selectByVisibleText(strOptionType);
				Log.info("Generate Option is selected as Billing Group.");
		 	}
		 	else if(strGenerateOption.equals("MPI")) {
		 		//Select Generate Option - here Master Plan Instance
		 		options.get(2).click();
		 		Thread.sleep(2500);
		 		Select lstMPI = new Select (driver.findElement(By.id("inMasterPlanInstNo")));
		 		lstMPI.selectByVisibleText(strOptionType);
		 		Log.info("Generate Option is selected as Master Plan Instance.");
		 	}
			 
			List<WebElement> invoiceTypes = driver.findElements(By.name("inInvoiceType"));
			Thread.sleep(5000);
		 	if (strInvoiceType.equals("Immediate")){
		 		//Select Invoice Type to be generated - here Immediate
		 		invoiceTypes.get(0).click();	
		 		Log.info("Immediate invoice type is selected.");
		 	}
		 	else if(strInvoiceType.equals("Pending")) {
		 		//Select Invoice Type to be generated - here Pending
		 		invoiceTypes.get(1).click();
		 		Log.info("Pending Invoice type is selected");
		 	}
		 	else if(strInvoiceType.equals("Advanced")) {
		 		//Select Invoice Type to be generated - here Advanced
		 		invoiceTypes.get(2).click();
		 		Log.info("Advanced Invoice type is selected");
		 	}
		 		
			Thread.sleep(5000);
			
			Utils.takeScreenshot(driver, strTestCaseName);
			//Click button Generate invoice
			objAcctStmntInv.fn_GenerateInvoiceButton(driver, webWait).click();
			Thread.sleep(10000);
			
			String strInvoiceMessage = objAcctStmntInv.fn_getFlaggedMessage(driver, webWait).getAttribute("innerText").toString().trim();
			if(strInvoiceMessage.contains("successfully generated")) {
				
				blnInvoiceGenerated = true;
				Utils.takeScreenshot(driver, strTestCaseName);
				Log.info("Invoice has been successfully genereated !");
				Thread.sleep(2000);
			}
			
			webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Accounts"))).click();
		}
		
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
	
	public String fn_GeneratePayments_NetTerms (WebDriver driver, WebDriverWait webWait, String strAccountNum, String strPayDate, String strRefCode, 
				Integer intApplyAgainstOptions,Boolean blnPaymentAmount, String strOtherAmount, String strTestCaseName) throws Exception {

    	//TODO: create class object to access the page objects for accessing the Payments.
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAcct = new Cls_ChangeDeleteClientDefinedFieldActns();
		AriaEOM objAriaEOM = new AriaEOM();		
		AccountsPaymentsCredits objPaymentsCredits = new AccountsPaymentsCredits();
		
		String strReturnPayments = null;
		String strPaymentsTxnID = null;
		String strPaymentsAmount = null;
				
		//TODO: Search the account for generating payments and then navigate to the Payments & Credits sub-menu.
		objClientDefAcct.AccountSearch(driver, webWait, strAccountNum);
		objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);		
		objAriaEOM.fn_clickPaymentsCredits(driver, webWait).click();	
		objAriaEOM.fn_getDataTable(driver, webWait);		
		objPaymentsCredits.fn_clickRecordPaymentReceived(driver, webWait).click();
		objPaymentsCredits.fn_clickMakePayment(driver, webWait);		
		objPaymentsCredits.fn_clickPaymentOptions(driver, webWait, 2);		
		objPaymentsCredits.fn_setPayRecivedDate(driver, webWait).clear();
		objPaymentsCredits.fn_setPayRecivedDate(driver, webWait).sendKeys(strPayDate);
		objPaymentsCredits.fn_setRefCode(driver, webWait).clear();
		objPaymentsCredits.fn_setRefCode(driver, webWait).sendKeys(strRefCode);
		objPaymentsCredits.fn_setCSRComments(driver, webWait).clear();
		objPaymentsCredits.fn_setCSRComments(driver, webWait).sendKeys("This is Payments for automation test purpose !");
		
		//TODO : Select options based or user demands
		  switch (intApplyAgainstOptions) {
			  
	          case 1: 
	        	  	objPaymentsCredits.fn_clickApplyAgainstOptions(driver, webWait,1).click();// 1 for Charges per standard FIFO method  option
	        	  	if(blnPaymentAmount == true)	//to set Full Payment, we send number 0.
	        	  		objPaymentsCredits.fn_clickPaymentAmount(driver, webWait, 1).click();						
	        	  	else {	//to set Other Amount, we send number 1.
	
		      			objPaymentsCredits.fn_clickPaymentAmount(driver, webWait, 2).click(); 
		      			objPaymentsCredits.fn_getOtherAmount(driver, webWait).clear();
		      			objPaymentsCredits.fn_getOtherAmount(driver, webWait).sendKeys(strOtherAmount);
	      			}
	        	  	break;
	        	  	
	          case 2: 
	        	  objPaymentsCredits.fn_clickApplyAgainstOptions(driver, webWait,2).click(); // 2 for  Invoices and other account-level charges option 
	        	    //TODO: call a method to define which values are to be selected from the basis of strApplicationMethods governing it.
	        	  	//objPaymentsCredits.fn_enterAmountAgainstAccountLevel(driver, webWait).sendKeys("2");
	        	  	break;
	        	  	
	          case 3: 
	        	  objPaymentsCredits.fn_clickApplyAgainstOptions(driver, webWait,3).click();  // 3 for Services (Invoice line-items) option
	        	    //TODO: call a method to define which values are to be selected from the basis of strApplicationMethods governing it.
	        	  	//objPaymentsCredits.fn_enterAmountAgainstServices(driver, webWait).sendKeys("2");
	        	  	break;
	        	  	
	          default :
	        	  VerificationMethods.assertTrue(false, "Suggested Apply Against Options is not available.");	        	  	        	  
		  }
		
		Utils.takeScreenshot(driver, strTestCaseName);
		objPaymentsCredits.fn_clickMakePayment(driver, webWait).click();
		Thread.sleep(5000);
		String strConfirmWinHandle = driver.getWindowHandle();
		driver.switchTo().window(strConfirmWinHandle);
		objPaymentsCredits.fn_clickConfirmationDlgOk(driver, webWait).click();
		objPaymentsCredits.fn_clickPostPayment(driver, webWait);
		Thread.sleep(1000);
		Utils.takeScreenshot(driver, strTestCaseName);
		objPaymentsCredits.fn_clickPostPayment(driver, webWait).click();
		
		try	{ //try catch introduced to check if there was any error generating the payments.
			objAriaEOM.fn_getDataTable(driver, webWait);
		}
		catch (Exception exception) {

			Thread.sleep(1500);
			Utils.takeScreenshot(driver, strTestCaseName);
			String strErrorMessage = driver.findElement(By.xpath("//*[@id='accountsSectionBottomContainer']/div/div/div[2]/p")).getAttribute("innerText").toString().trim();
			Log.error("ERROR: Payment Generation failed with error: "+strErrorMessage);
			Reporter.log("ERROR: Payment Generation failed with error: "+strErrorMessage);
			Utils.takeScreenshot(driver, strTestCaseName);
			VerificationMethods.assertTrue(false, "ERROR: There is error generating the Payments. Please refer to the Snapshot for details...");				
		}		
		
		String strPaymentsMessage = objPaymentsCredits.fn_getStatusMessage(driver, webWait).getAttribute("innerText").toString().trim();
		if(strPaymentsMessage.contains("successfully posted")) {

			//TODO: Read the Payments Data table to get the transaction ID for recently added Payments.
			WebElement tblPayments = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
			List<WebElement> lstwebRowsPayments = tblPayments.findElements(By.tagName("tr"));
			for(WebElement rows: lstwebRowsPayments) {

				List<WebElement> cols = rows.findElements(By.tagName("td"));
				if(cols.size() != 0) {

					String strColsVoid = cols.get(0).getAttribute("textContent").toString().trim();				
					if(strColsVoid.contentEquals("Void")) {

						strPaymentsTxnID = cols.get(1).getAttribute("innerText").toString().trim();
						strPaymentsAmount = cols.get(6).getAttribute("innerText").toString().trim();
						strPaymentsAmount = strPaymentsAmount.replace(",", "");
						Log.info("The Payments is succesfully created and the Transaction ID for it is : "+strPaymentsTxnID);
						Log.info("The Actual Amount for the Transaction is : "+strPaymentsAmount);
						Utils.takeScreenshot(driver, strTestCaseName);
						break;
					}					
				}
			}			
		}
		else {

			Log.error("ERROR: The Payments couldn't be successfully posted !");
			Reporter.log("ERROR: The Payments couldn't be successfully posted !");			
		}
		
		//TODOD: Since Transaction ID & Applied Amount for payments is necessary, concatenate it and return the value.
		strReturnPayments = strPaymentsTxnID+"#"+strPaymentsAmount;
		return strReturnPayments;
	}
	
	/*public String fn_GeneratePayments_CollectPaymentMethod(WebDriver driver, WebDriverWait webWait, String strAccountNum,Boolean blnPaymentAmount,Integer intApplicationMethod, 
		Integer intAlternateBillingTypeIndex, String strApplicationMethods, String strOtherAmount, String strTestCaseName) throws Exception
		{
	
		//TODO: create class object to access the page objects for accessing the Payments.
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAcct = new Cls_ChangeDeleteClientDefinedFieldActns();
		AriaEOM objAriaEOM = new AriaEOM();		
		AccountsPaymentsCredits objPaymentsCredits = new AccountsPaymentsCredits();
		String strCollectOptionPaymentsAmount = null;
		String strPaymentsTxnID = null;
		String strPaymentsAmount = null;
		String strCreditCardNo="4111111111111111";
		String strRoutingNumber="789632";
		String strCheckingAccountNumber="8523697412";
		
		//TODO: Search the account for generating payments and then navigate to the Payments & Credits sub-menu.
		objClientDefAcct.AccountSearch(driver, webWait, strAccountNum);
		objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);		
		objAriaEOM.fn_clickPaymentsCredits(driver, webWait).click();	
		objAriaEOM.fn_getDataTable(driver, webWait);		
		objPaymentsCredits.fn_clickRecordPaymentReceived(driver, webWait).click();
		objPaymentsCredits.fn_clickMakePayment(driver, webWait);		
		objPaymentsCredits.fn_clickPaymentOptions(driver, webWait, 1).click();
		
			if(blnPaymentAmount == true){	//to set Full Payment, we send number 0.
				objPaymentsCredits.fn_clickPaymentAmountType(driver, webWait, 0).click();	
				}
			else {	//to set Other Amount, we send number 1.
				objPaymentsCredits.fn_clickPaymentAmountType(driver, webWait, 1).click(); 
			
				if(intApplicationMethod==1){
					objPaymentsCredits.fn_clickApplicationMethod(driver, webWait, 1).click();  //to set Apply to charges per standard FIFO method, we send number 1.
					objPaymentsCredits.fn_enterOtherAmountMin(driver, webWait).clear();
					objPaymentsCredits.fn_enterOtherAmountMin(driver, webWait).sendKeys(strOtherAmount);
					}
				else{
					objPaymentsCredits.fn_clickApplicationMethod(driver, webWait, 2).click();  //to set Apply to specific unpaid charges, we send number 2.
					//TODO: call a method to define which values are to be selected from the basis of strApplicationMethods governing it.
					}
			
			}
		
		WebElement webPaymentMethod=objPaymentsCredits.fn_selectPaymentMethod(driver, webWait);
		Select selPaymentMethod=new Select(webPaymentMethod);
		selPaymentMethod.selectByIndex(1);
		
		if(intAlternateBillingTypeIndex==1){
			
			WebElement webAltBillingType=objPaymentsCredits.fn_selectBillingType(driver, webWait);
			Select selAltBillingType=new Select(webAltBillingType);
			selAltBillingType.selectByIndex(1);                                                   //1 for select credit card 
			objPaymentsCredits.fn_enterCreditCardDetail(driver, webWait, 1).sendKeys(strCreditCardNo);  //1 for  Credit card no
				WebElement ExpDateMonth=objPaymentsCredits.fn_selectCreditCardExpDate(driver, webWait, 1); //1 for Expiration Month
			Select selExpDateMonth=new Select(ExpDateMonth);
			selExpDateMonth.selectByIndex(2);			                                              //2 for Index value
			WebElement ExpDateYear=objPaymentsCredits.fn_selectCreditCardExpDate(driver, webWait, 2);//2 for  Expiration year
			Select selExpDateYear=new Select(ExpDateYear);
			selExpDateYear.selectByIndex(3);                                                        //3 for Index value
			objPaymentsCredits.fn_enterCreditCardDetail(driver, webWait, 2).sendKeys("231");         //2 for Cedit card CVV  No. option and 231for cvv no 
			}
		
		else{
			WebElement webAltBillingType=objPaymentsCredits.fn_selectBillingType(driver, webWait);
			Select selAltBillingType=new Select(webAltBillingType);
			selAltBillingType.selectByIndex(2);                                              // 2 for select Checking account 
			objPaymentsCredits.fn_enterRoutingNumber(driver, webWait).sendKeys(strRoutingNumber);
			objPaymentsCredits.fn_enterCheckingAcctNumber(driver, webWait).sendKeys(strCheckingAccountNumber);			
			}
		//TODO: Enter User Details 
		objPaymentsCredits.fn_enterContactDetails(driver, webWait, 1).sendKeys("QAAuto_FName");
		objPaymentsCredits.fn_enterContactDetails(driver, webWait, 2).sendKeys("QAAuto_LName");
		objPaymentsCredits.fn_enterContactDetails(driver, webWait, 3).sendKeys("QAAuto_ComName");
		objPaymentsCredits.fn_enterContactDetails(driver, webWait, 4).sendKeys("680 E. Swedesford Road");
		objPaymentsCredits.fn_enterContactDetails(driver, webWait, 5).sendKeys("Wayne1");
		objPaymentsCredits.fn_enterContactDetails(driver, webWait, 6).sendKeys("Wayne2");
		objPaymentsCredits.fn_enterContactDetails(driver, webWait, 7).sendKeys("Wayne3");
		objPaymentsCredits.fn_enterContactDetails(driver, webWait, 8).sendKeys("philadelphia");
		objPaymentsCredits.fn_enterContactDetails(driver, webWait, 11).sendKeys("19087");
		objPaymentsCredits.fn_enterContactDetails(driver, webWait, 12).sendKeys("9632581");
		objPaymentsCredits.fn_enterContactDetails(driver, webWait, 13).sendKeys("7539513584");
		objPaymentsCredits.fn_enterContactDetails(driver, webWait, 14).sendKeys("8523697");
		objPaymentsCredits.fn_enterContactDetails(driver, webWait, 15).sendKeys("Nashim.khan@sungardas.com");
		WebElement webUserCountry=objPaymentsCredits.fn_selectUserCountry(driver, webWait);
		Select selUserCountry=new Select(webUserCountry);
		selUserCountry.selectByIndex(2);
		WebElement webUserState=objPaymentsCredits.fn_selectUserState(driver, webWait);
		Select selUserCity=new Select(webUserState);
		selUserCity.selectByIndex(2);		
		objPaymentsCredits.fn_enterCSRComments(driver, webWait).sendKeys("This is Payments for automation test purpose !");
		//TODO : Submit Details and confirm Details
		objPaymentsCredits.fn_clickCollectFromAccountBtn(driver, webWait).click();	    
		objPaymentsCredits.fn_clickPostPayment(driver, webWait).click();
		
		try	{ //try catch introduced to check if there was any error generating the payments.
			objAriaEOM.fn_getDataTable(driver, webWait);
		}
		catch (Exception exception) {
		
			Thread.sleep(1500);
			Utils.takeScreenshot(driver, strTestCaseName);
			String strErrorMessage = driver.findElement(By.xpath("//*[@id='accountsSectionBottomContainer']/div/div/div[2]/p")).getAttribute("innerText").toString().trim();
			Log.error("ERROR: Payment Generation failed with error: "+strErrorMessage);
			Reporter.log("ERROR: Payment Generation failed with error: "+strErrorMessage);
			Utils.takeScreenshot(driver, strTestCaseName);
			VerificationMethods.assertTrue(false, "ERROR: There is error generating the Payments. Please refer to the Snapshot for details...");				
		}		
		
		String strPaymentsMessage = objPaymentsCredits.fn_getPaymentsStatusMessage(driver, webWait).getAttribute("innerText").toString().trim();
		if(strPaymentsMessage.contains("successfully posted")) {
		
			//TODO: Read the Payments Data table to get the transaction ID for recently added Payments.
			WebElement tblPayments = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
			List<WebElement> lstwebRowsPayments = tblPayments.findElements(By.tagName("tr"));
			for(WebElement rows: lstwebRowsPayments) {
		
				List<WebElement> cols = rows.findElements(By.tagName("td"));
				if(cols.size() != 0) {
		
					String strColsVoid = cols.get(0).getAttribute("textContent").toString().trim();				
					if(strColsVoid.contentEquals("Void")) {
		
						strPaymentsTxnID = cols.get(1).getAttribute("innerText").toString().trim();
						strPaymentsAmount = cols.get(6).getAttribute("innerText").toString().trim();
						strPaymentsAmount = strPaymentsAmount.replace(",", "");
						Log.info("The Payments is succesfully created and the Transaction ID for it is : "+strPaymentsTxnID);
						Log.info("The Actual Amount for the Transaction is : "+strPaymentsAmount);
						Utils.takeScreenshot(driver, strTestCaseName);
						break;
					}					
				}
			}			
		}
		else {
		
			Log.error("ERROR: The Payments couldn't be successfully posted !");
			Reporter.log("ERROR: The Payments couldn't be successfully posted !");			
		}
		
		//TODOD: Since Transaction ID & Applied Amount for payments is necessary, concatenate it and return the value.
		strCollectOptionPaymentsAmount = strPaymentsTxnID+"#"+strPaymentsAmount;
		//return strReturnPayments;
		
		return strCollectOptionPaymentsAmount;	
	}	
*/

/*	
	Function fn_VoidPayments: Takes in the details for Account Number for which payments are to be processed.
	Inputs: strAccountNum - account number for which Payments are to be Voided.
			strPaymenTxnID - the Payments Transaction ID which is to be voided.
	
	Outputs: Boolean status for the payment being voided.
*/
	
	public Boolean fn_VoidPayments (WebDriver driver, WebDriverWait webWait, String strAccountNum,
    		   String strPaymentsTxnID, String strTestCaseName) throws Exception {		

        //TODO: create class object to access the page objects for accessing the Payments.
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAcct = new Cls_ChangeDeleteClientDefinedFieldActns();
		AriaEOM objAriaEOM = new AriaEOM();		
		AccountsPaymentsCredits objPaymentsCredits = new AccountsPaymentsCredits();
	
		Boolean blnValidateVoid = false;
				
		//TODO: Search the account for generating payments and then navigate to the Payments & Credits sub-menu.
		objClientDefAcct.AccountSearch(driver, webWait, strAccountNum);
		objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);		
		objAriaEOM.fn_clickPaymentsCredits(driver, webWait).click();	
		objAriaEOM.fn_getDataTable(driver, webWait);		
    	
		//TODO: Read the Payments Data table to select the row of Payments done to be voided.
		WebElement tblPayments = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
		List<WebElement> lstwebRowsPayments = tblPayments.findElements(By.tagName("tr"));
		for(WebElement rows: lstwebRowsPayments) {
			
			List<WebElement> cols = rows.findElements(By.tagName("td"));
			if(cols.size() != 0) {
				
				String strColsVoid = cols.get(0).getAttribute("textContent").toString().trim();
				String strColsTxnID = cols.get(1).getAttribute("innerText").toString().trim();				
				if(strColsTxnID.contentEquals(strPaymentsTxnID) && strColsVoid.contentEquals("Void")) {
				
					Log.info("The Transaction ID '"+strPaymentsTxnID+"' has been found and the voiding process has been initiated..");
					cols.get(0).click();
					Thread.sleep(3000);
					objPaymentsCredits.fn_clickConfirmationDljPayments(driver, webWait).click();
					Thread.sleep(4000);
					String strVoidStatusMessage = objPaymentsCredits.fn_getStatusMessage(driver, webWait).getAttribute("innerText").toString().trim();
					Thread.sleep(4000);
					if(strVoidStatusMessage.contains("successfully voided")) {

						objAriaEOM.fn_getDataTable(driver, webWait);
						Utils.takeScreenshot(driver, strTestCaseName);
						blnValidateVoid = true;
						Log.info("The voiding process has been successfully completed !");
						break;
					}					
				}
				else {
					
					Log.error("ERROR: The Voiding of Payments for Payments Transaction ID: "+strPaymentsTxnID+" couldn't be successfully done !");
					Reporter.log("ERROR: The Voiding of Payments for Payments Transaction ID: "+strPaymentsTxnID+" couldn't be successfully done !");
					blnValidateVoid = false;
				}
			}
		}
		
		return blnValidateVoid;	
	}
 
/*
    Modify By : Nashim Khan
   	Function fn_BookingRefunds: Takes in the details for Account Number for which Refunds are to be initiated.
   	Inputs: strAccountNum - account number for which Refunds are to be booked.
   			strPaymenTxnID - the Payments Transaction ID which is to be voided.
   			intLineItemReversal - This integer parameter defines whether the Line Item Reversal is opted or Not.
   				0 = No.
   				1 = Yes.
   	
   	Outputs: Boolean status for the Refunds being initiated.
*/
	public Boolean fn_BookingRefunds(WebDriver driver, WebDriverWait webWait, String strAccountNum, String strPaymentsTxnID,  
   									String strRefundAmount, String strRefundsReason, Integer intLineItemReversal, 
   									String strCheckNo, String strTestCaseName) throws Exception {
	       
   		//TODO: Create object for the classes to be used for accessing reusable codes.
 		AccountsPaymentsCredits  objAcctPaymentsCredits=new AccountsPaymentsCredits();
 		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
 		AriaEOM objAriaEOM = new AriaEOM();
 		Utils objUtils = new Utils();
 		
 		Boolean blnRefundsAssigned = false;
 		Boolean blnItemReversal = false;
 		
 		//TODO : Search a account and Apply Cash Credit 
 		objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
 		Thread.sleep(3000);
 		objAriaEOM.fn_clickPaymentsCredits(driver, webWait).click();
 		Thread.sleep(2000);
 		objAriaEOM.fn_getDataTable(driver, webWait);
 		objAcctPaymentsCredits.fn_clickRefunds(driver, webWait).click();	 		
 		objAcctPaymentsCredits.fn_clickCreateNewRefund(driver, webWait).click();

   		//TODO: Read the Refunds Data table to select the row of Payments Transaction ID to add a refund.
   		Thread.sleep(3500);
   		WebElement webRefundsTable = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
   		List<WebElement> lstwebRowsRefunds = webRefundsTable.findElements(By.tagName("tr"));
   		for(WebElement rows: lstwebRowsRefunds)	{
   			
   			List<WebElement> cols = rows.findElements(By.tagName("td"));
   			if(cols.size() != 0) {
   				
   				String strColsTxnID = cols.get(0).getAttribute("innerText").toString().trim();
   				if(strColsTxnID.contains(strPaymentsTxnID)) {
   					
   					Log.info("Transaction ID has been found and the Refunds for this ID will be initiated..");
   					
   					driver.findElement(By.partialLinkText(strPaymentsTxnID)).click();
   					blnItemReversal=true;
   					break;
   				}   				
   			}
   		}	
   		//TODO: Select the logic for Refunds as per line reversible or otherwise.
		if(blnItemReversal == false)				  
			VerificationMethods.assertTrue(false, "ERROR: An Invalid Payments TxnID has been entered, TC can't proceed further !");	
		
		//TODO :  Enter Details for Refund amount 		
		Thread.sleep(3000);
   		objAcctPaymentsCredits.fn_getRefundReasonDropDown(driver, webWait).selectByVisibleText(strRefundsReason);
   		objAcctPaymentsCredits.fn_setInvoiceLineItem(driver, webWait, intLineItemReversal).click();
   		if(intLineItemReversal == 0)
   			objAcctPaymentsCredits.fn_setAmountRefunds(driver, webWait).sendKeys(strRefundAmount);
   		else{
   			
   			//TOOD: Code for Line Item Reversal Options.
   		}
		objAcctPaymentsCredits.fn_setCSRCommentsForRefund(driver, webWait).sendKeys("This is Refunds for automation test purpose !");
		Thread.sleep(1500);
		Utils.takeScreenshot(driver, strTestCaseName);
		objAcctPaymentsCredits.fn_clickNextSubmitBtn(driver, webWait).click();
		
		try {
			
			objAcctPaymentsCredits.fn_setCheckNumber(driver, webWait).sendKeys(strCheckNo);			
		} catch (Exception exception) {
			
			String strRefundProcessError=objAcctPaymentsCredits.fn_getRefundProcessError(driver, webWait).getText();
			Log.error("Message :"+strRefundProcessError);
			VerificationMethods.assertTrue(false,"ERROR: The refund amount  is invalid !"+exception.toString());			
		}
	
		objAcctPaymentsCredits.fn_clickCreateRefunds(driver, webWait).click();
		Thread.sleep(5000);
		objAcctPaymentsCredits.fn_clickWindowOKBtn(driver, webWait).click();		
		Thread.sleep(500);
		Utils.takeScreenshot(driver, strTestCaseName);
		Thread.sleep(3500);
		// verify the successful refund process done above.
		webRefundsTable = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
   		lstwebRowsRefunds = webRefundsTable.findElements(By.tagName("tr"));
   		for(WebElement rows: lstwebRowsRefunds)	{
   			
   			List<WebElement> cols = rows.findElements(By.tagName("td"));
   			if(cols.size() != 0) {
   				
   				String strRefundMethod = cols.get(4).getText().toString().trim();
   				String strAmount = cols.get(5).getText().toString().trim();
   				strRefundAmount = objUtils.fn_ConvertStringToBigDecimal(strRefundAmount, 2);
   				if(strRefundMethod.equals("Check: "+strCheckNo) && strAmount.equals(strRefundAmount)) {
   					
   					blnRefundsAssigned = true;
					Log.info("The Amount  '"+strCheckNo+"' is successfully assigned to the Account ID: "+strPaymentsTxnID);
					break;
   				}
   			}
   		}
   		if(blnRefundsAssigned == false) {
   			
   			Log.error("ERROR: The refunds booking failed; no listed in the succesfully booked refunds list !");
   			Reporter.log("ERROR: The refunds booking failed; no listed in the succesfully booked refunds list !");
   		}
   		
	return blnRefundsAssigned;
}
/*    
	Function fn_SelectLatestInvoice: This method will verify if the changed Client Defined Field is retained.
	Inputs: Assumes that the Invoice has been generated.
	
	Outputs: Boolean status for the Invoice selection success or failure.	   						
*/	
    public Boolean fn_SelectLatestInvoice(WebDriver driver, WebDriverWait webWait) throws Exception {
    	
    	//TODO: Create object for the classes to be used for accessing reusable codes.
		AriaEOM objAriaEOM = new AriaEOM();
		AccountsStatementsInvoices objAcctStmntInv = new AccountsStatementsInvoices();
		
    	Boolean blnLatestInvoiceSelected = false;
    	
    	//TODO: Navigate to the Invoices Tab for the account requested and select latest generated Invoice.
    	try	{
    		
    		objAriaEOM.fn_clickAccounts(driver, webWait).click();
    		objAriaEOM.fn_clickStatementsInvoices(driver, webWait).click();
    		objAriaEOM.fn_getDataTable(driver, webWait);
    		objAcctStmntInv.fn_clickInvoicesTab(driver, webWait).click();
    		objAriaEOM.fn_getDataTable(driver, webWait);           	
       		Thread.sleep(3500);       		
    		WebElement webInvoicesDetails = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
    		List<WebElement> lstwebReadInvoicesRow = webInvoicesDetails.findElements(By.tagName("tr"));
    		for(WebElement row: lstwebReadInvoicesRow) {
    			
    			List<WebElement> cols = row.findElements(By.tagName("td"));
    			
    			if(cols.size() >= 2) {
    				
    				String strInvoiceNumber = cols.get(0).getAttribute("innerText").toString().trim();
    				Log.info("The Invoiced Number selected for verifications is: "+strInvoiceNumber);
    				driver.findElement(By.partialLinkText(strInvoiceNumber)).click();    				
    				if(objAcctStmntInv.fn_clickViewPrint(driver, webWait).isDisplayed())
    					blnLatestInvoiceSelected = true;
    				else
    					blnLatestInvoiceSelected = false;
    				
    				break;
    			}
    		}
    		objAriaEOM.fn_getDataTable(driver, webWait);    		
    	}
    	catch (Exception exception) {
    		
    		Log.error("ERROR: Invoice selection couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Invoice selection couldn't be done due to exception:  "+exception.toString());
       		throw exception;
    	}
    	
    	return blnLatestInvoiceSelected;
    }
    	
    /*
    Modify By : Nashim Khan 
	Function fn_AssignCreditCoupon: Takes in the details for Account Number for which Coupons are to be Applied.
	Inputs: strAccountNum - account number for which Coupon is to be applied.
			Coupon Text - The Coupon Name which is to be applied.
	
	Outputs: Boolean status for the success or failure of applying Coupon.
*/	
	public Boolean fn_AssignCreditCoupon(WebDriver driver, WebDriverWait webWait, String strAccountNum, String strCoupon
			, String strTestCaseName) throws Exception 	{
		
    	//TODO: create class object to access the page objects for accessing the Payments.
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
		AriaEOM objAriaEOM = new AriaEOM();		
		AccountsPaymentsCredits objPaymentsCredits = new AccountsPaymentsCredits();
		
		Boolean blnCouponAssigned = false;
		String strCouponMessage = null;
		
    	//TODO: Navigate to Payments & Credits -> Coupons Tab to assign Coupon to the Account.
		objClientDefAct.AccountSearch(driver, webWait, strAccountNum);    	
    	objAriaEOM.fn_clickPaymentsCredits(driver, webWait).click();
    	objAriaEOM.fn_getDataTable(driver, webWait);
    	objPaymentsCredits.fn_clickCoupons(driver, webWait).click();
    	objAriaEOM.fn_getDataTable(driver, webWait);
    	   	//TODO: Verify Coupon Assignment to avoid any further re-assignment of Coupon.
    		Thread.sleep(3500);
			WebElement webCouponsListTable = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
			List<WebElement> lstwebCouponsListTableRows = webCouponsListTable.findElements(By.tagName("tr"));
			for(WebElement row : lstwebCouponsListTableRows) {

				List<WebElement> cols = row.findElements(By.tagName("td"));
				
				if(cols.size() >= 4) {
					
					String strTableCouponStatus = cols.get(0).getAttribute("innerText").toString().trim();
					String strTableCouponCode = cols.get(1).getAttribute("innerText").toString().trim();
					if(strTableCouponCode.contains(strCoupon) && strTableCouponStatus.contains("Cancel")){
						
						blnCouponAssigned = true;
						Log.info("The Coupon Code '"+strCoupon+"' is already assigned to the Account ID: "+strAccountNum);
						
						objAriaEOM.fn_clickAccounts(driver, webWait).click();
						return blnCouponAssigned;
					}
				}
			}

		objPaymentsCredits.fn_clickAssignCoupon(driver, webWait).click();
		objPaymentsCredits.fn_clickValidate(driver, webWait);
		objPaymentsCredits.fn_setCouponCodeText(driver, webWait).clear();
		objPaymentsCredits.fn_setCouponCodeText(driver, webWait).sendKeys(strCoupon);
		objPaymentsCredits.fn_clickValidate(driver, webWait).click();
    	if(objPaymentsCredits.fn_getStatusMessage(driver, webWait).isDisplayed()) {
    		
    		strCouponMessage =  objPaymentsCredits.fn_getStatusMessage(driver, webWait).getAttribute("innerText").toString().trim();
    		if(strCouponMessage.contains("Invalid Coupon."))
    			VerificationMethods.assertTrue(false, "ERROR: An Invalid Coupon Code has been entered, TC can't proceed further !");
    	} 
    	
    	try {
    		
    		objPaymentsCredits.fn_getCouponAssignment(driver, webWait, 1); //  Options--> 1 for  Account Level And 2 for Master Plan Instance Level        	
		}
    	catch (Exception e) {
			
    		Log.info(" There is no Coupon Assignment Option Avilable : "+strCoupon);
		} 
       	Thread.sleep(3000); 
    	objPaymentsCredits.fn_clickAssign(driver, webWait).click();
    	Thread.sleep(2000);    	
    	strCouponMessage =  objPaymentsCredits.fn_getStatusMessage(driver, webWait).getAttribute("innerText").toString().trim();
		if(strCouponMessage.contains("successfully assigned"))
			blnCouponAssigned = true;
		else
			VerificationMethods.assertTrue(false, "ERROR: An error has occurred while assigning Coupon, TC can't proceed further !");
		
		//TODO: Verify Coupon Assignment again in Assigned Coupons Table although we have verified the status message above.
		webCouponsListTable = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
		lstwebCouponsListTableRows = webCouponsListTable.findElements(By.tagName("tr"));
		for(WebElement row : lstwebCouponsListTableRows) {
			
			List<WebElement> cols = row.findElements(By.tagName("td"));			
			if(cols.size() >= 1) {
				 
				String strTableCouponCode = cols.get(1).getAttribute("innerText").toString().trim();
				if(strTableCouponCode.contains(strCoupon)) { 
					
					blnCouponAssigned = true;
					Log.info("The Coupon Code '"+strCoupon+"' is successfully assigned to the Account ID: "+strAccountNum);
					break;
				}
			}
		}	
		objAriaEOM.fn_clickAccounts(driver, webWait).click();
		
		return blnCouponAssigned;
	}

/*
 *  Modify By : Nashim Khan
	Function fn_CancelCreditCoupon: Takes in the details for Account Number for which Already Assigned Coupons are to be Cancelled.
	Inputs: strAccountNum - account number for which Assigned Coupons are to be Cancelled.
			Coupon Text - The Coupon Name which is to be applied.
	
	** This method assumes that the Coupons Tab is navigated before it is being called for canceling the already Assigned Coupon **- 
	
	Outputs: Boolean status for the success or failure of Canceling Applied Coupon.
*/	
	public Boolean fn_CancelCreditCoupon(WebDriver driver, WebDriverWait webWait, String strAccountNum, String strCoupon
			, String strTestCaseName) throws Exception	 {
		
		//TODO: Create object for the classes to be used for accessing reusable codes.
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();		
		AriaEOM objAriaEOM = new AriaEOM();		
		AccountsPaymentsCredits objPaymentsCredits = new AccountsPaymentsCredits();
		
		Boolean blnCouponCancelled = false;
		String strCouponMessage = null;
		
	   	//TODO: Verify Coupon is already assigned and then cancel it.
    	objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
    	objAriaEOM.fn_clickPaymentsCredits(driver, webWait).click();
    	objAriaEOM.fn_getDataTable(driver, webWait);
    	objPaymentsCredits.fn_clickCoupons(driver, webWait).click();
    	objAriaEOM.fn_getDataTable(driver, webWait);
		Log.info("Verifying if coupon '"+strCoupon+"' has already been assigned to the account; if yes, cancel it !");
		Thread.sleep(3500);
		WebElement webCouponsListTable = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
		List<WebElement> lstwebCouponsListTableRows = webCouponsListTable.findElements(By.tagName("tr"));
		for(WebElement row : lstwebCouponsListTableRows) {

			List<WebElement> cols = row.findElements(By.tagName("td"));			
			if(cols.size() >= 2) {
				
				String strTableCouponStatus = cols.get(0).getAttribute("innerText").toString().trim();
				String strTableCouponCode = cols.get(1).getAttribute("innerText").toString().trim();
				if(strTableCouponCode.contains(strCoupon) && strTableCouponStatus.contains("Cancel")) {

					cols.get(0).findElement(By.tagName("a")).click();
					Thread.sleep(2000);
					objPaymentsCredits.fn_clickOKCancelingCoupon(driver, webWait).click();
					Thread.sleep(2000);
					strCouponMessage = objPaymentsCredits.fn_getStatusMessage(driver, webWait).getAttribute("innerText").toString().trim();
					
					if(strCouponMessage.contains("successfully cancelled")) {
						
						blnCouponCancelled = true;
						Log.info("The Coupon Code '"+strCoupon+"' is successfully cancelled for the Account ID: "+strAccountNum);
						break;
					}
					else
						VerificationMethods.assertTrue(false, "ERROR: There was an error while cancelling the already assigned coupon '"+strCoupon+"' !");
				}
			}
		}
		objAriaEOM.fn_clickAccounts(driver, webWait).click();
		
		return blnCouponCancelled;
	}


/*
	Function fn_GenerateInvoiceStatement: Generates Statement for the newly created Invoice after it is approved.
	
	** This method assumes that the Account has already been searched. **- 
	
	Outputs: Boolean status of the Statement generation.
*/	
	public Boolean fn_GenerateInvoiceStatement (WebDriver driver, WebDriverWait webWait, String strMPIName, 
			String strTestCaseName) throws Exception {

		//TODO: Create object for the classes to be used for accessing reusable codes.
		AriaEOM objAriaEOM = new AriaEOM();
		AccountsStatementsInvoices objAcctStmntInv = new AccountsStatementsInvoices();
		
		Boolean blnStatementGenerated = false;
		
		//Navigate to the Statements Tab, generate a statement and validate it's status.
		try {
			
			objAriaEOM.fn_clickStatementsInvoices(driver, webWait).click();
			objAriaEOM.fn_getDataTable(driver, webWait);
			Thread.sleep(1500);
			objAcctStmntInv.fn_clickStatementsTab(driver, webWait).click();
			objAriaEOM.fn_getDataTable(driver, webWait);
			Thread.sleep(1500);
			objAcctStmntInv.fn_clickGenerateStatement(driver, webWait).click();
			objAcctStmntInv.fn_clickGenerateStatementButton(driver, webWait);
			Thread.sleep(1500);
			objAcctStmntInv.fn_clickSelectMPI(driver, webWait).selectByVisibleText(strMPIName);
			objAcctStmntInv.fn_selectEmailOptions(driver, webWait, "2").click();
			objAcctStmntInv.fn_clickGenerateStatementButton(driver, webWait).click();
			Thread.sleep(10000);
			Utils.takeScreenshot(driver, strTestCaseName);
			
			String strStatementGenerationMsg = objAcctStmntInv.fn_getFlaggedMessage(driver, webWait).getAttribute("innerText").toString().trim();
			if(strStatementGenerationMsg.contains("successfully generated.")) {
			
				blnStatementGenerated = true;
				Log.info("The Statement has been successfully generated for the Invoice.");
			}
		}
		catch (Exception exception)	{
			
			Log.error("ERROR: There is an error captured while generating Statement for the Invoice created as: "+exception);
			Reporter.log("ERROR: There is an error captured while generating Statement for the Invoice created as: "+exception);		
		}
		
		return blnStatementGenerated;
	}
	
/*
	Created By : Nashim Khan
	Function fn_CashCredit: Creates CashCredit	
	Inputs: strAccountNum - account number for which Assigned Cash Credits are to be Assign.
			strReasonCode - Select ReasonCode which is to be applied. 
			intApplyAgainstOptions- The Apply Against Options Like.
				1-For Charges per standard FIFO method options
				2-For Invoices and other account-level charges
				3-For Services (invoice line-items)
			strCreditAmt- The Credit Amount  which is to be applied. 
	
	Outputs: Boolean status if the Cash Credit is generated
*/
	public Boolean fn_AssignCashCredits (WebDriver driver, WebDriverWait webWait, String strAccountNum, String strReasonCode,
			Integer intApplyAgainstOptions, String strCreditAmt, String strTestCaseName) throws Exception {
	
		//TODO: Create object for the classes to be used for accessing reusable codes.
		AccountsPaymentsCredits  objAcctPaymentsCredits=new AccountsPaymentsCredits();
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
		AriaEOM objAriaEOM = new AriaEOM();
		Utils objUtils = new Utils();
		
		Boolean blnAmountAssigned = false;
		Boolean blnCreditGenerated = false;
		
		//TODO : Search a account and Apply Cash Credit 
		objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
		Thread.sleep(3000);
		objAriaEOM.fn_clickPaymentsCredits(driver, webWait).click();
		Thread.sleep(2500);
		objAcctPaymentsCredits.fn_clickCashCredits(driver, webWait).click();
		objAriaEOM.fn_getDataTable(driver, webWait);		
		
		//TODO: Verify Cash Credit Assignment to a void any further re-assignment of Cash Credit.
		Thread.sleep(3500);		
		WebElement webCashCreditsListTable = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
		List<WebElement> lstwebCashCreditsListTableRows = webCashCreditsListTable.findElements(By.tagName("tr"));
		for(WebElement row : lstwebCashCreditsListTableRows) {

			List<WebElement> cols = row.findElements(By.tagName("td"));
			//TODO : Verify Existing Cash Credit  if not void 
			if(cols.size() >= 4) {
								
				String strTableAmount = cols.get(4).getAttribute("innerText").toString().trim();
				String strTableActionStatus = cols.get(6).getAttribute("innerText").toString().trim();
				strTableAmount = strTableAmount.replaceAll("[$|�|�|kr]", "");
				strTableAmount = objUtils.fn_ConvertStringToBigDecimal(strTableAmount, 2);
				if(strTableAmount.equals(strCreditAmt) && strTableActionStatus.equals("Void")){
					
					blnAmountAssigned = true;
					Log.info("The Cash Credits Amount '"+strCreditAmt +"' is already assigned to the Account ID: "+strAccountNum);
				}
				if(strTableActionStatus.equals("Void") && blnAmountAssigned == false){
					
					cols.get(6).findElement(By.tagName("a")).click();
					Thread.sleep(4000);
					Alert alertCashCredit = driver.switchTo().alert();
					alertCashCredit.accept();					
					Log.info("Existing / Already assigned Cash Credits of '"+strTableAmount+"' is Voided successfully !");
				}
			}
		}
		Thread.sleep(3000);
		//TODO : Create New Cash Credits for assignment.  
		if( blnAmountAssigned == false) {
				
			objAcctPaymentsCredits.fn_clickNewBtn(driver, webWait).click();
			objAcctPaymentsCredits.fn_setCSRComments(driver, webWait);
			objAcctPaymentsCredits.fn_selectReasonCode(driver, webWait).selectByVisibleText(strReasonCode);  
			objAcctPaymentsCredits.fn_setCSRComments(driver, webWait).clear();
			objAcctPaymentsCredits.fn_setCSRComments(driver, webWait).sendKeys("This is Cash Credits for automation test purpose !");
			
			//TODO : Select options based or user requests.
			switch (intApplyAgainstOptions) {
				  
				case 1:					
					objAcctPaymentsCredits.fn_clickApplyAgainstOptions(driver, webWait,1); //To set 1 for Charges per standard FIFO method options
					objAcctPaymentsCredits.fn_setCreditAmount(driver, webWait).clear();
					objAcctPaymentsCredits.fn_setCreditAmount(driver, webWait).sendKeys(strCreditAmt);	
					break;
				  	
				case 2: 
					objAcctPaymentsCredits.fn_clickApplyAgainstOptions(driver, webWait,2); //To set 2 for Invoices and other account-level charges
					//TODO: call a method to define which values are to be selected from the basis of strApplicationMethods governing it.
					//objPaymentsCredits.fn_enterAmountAgainstAccountLevel(driver, webWait).sendKeys("2");
				  	break;
				  	
				case 3: 
					objAcctPaymentsCredits.fn_clickApplyAgainstOptions(driver, webWait,3); //To set 3 for Services (invoice line-items)
					//TODO: call a method to define which values are to be selected from the basis of strApplicationMethods governing it.
					//objPaymentsCredits.fn_enterAmountAgainstServices(driver, webWait).sendKeys("2");
				  	break;
				  	
				default :
					VerificationMethods.assertTrue(false, "Requested 'Apply Against Options' is not available.");	        	  	        	  
			}
			Utils.takeScreenshot(driver, strTestCaseName);
		    objAcctPaymentsCredits.fn_clickCreateCredit(driver, webWait).click();
		    Thread.sleep(5000);					    
		    objAcctPaymentsCredits.fn_clickConfirmationDlgOk(driver, webWait).click();
			blnCreditGenerated=true;
		}
		Thread.sleep(2000);
		Utils.takeScreenshot(driver, strTestCaseName);
		
		//TODO: Verify Cash Credits  Assignment again in Assigned Cash Credits Table.
		webCashCreditsListTable = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
		lstwebCashCreditsListTableRows = webCashCreditsListTable.findElements(By.tagName("tr"));
		for(WebElement row : lstwebCashCreditsListTableRows) {
			
			List<WebElement> cols = row.findElements(By.tagName("td"));				
			if(cols.size() >= 1) {
				 
				String strTableAmount = cols.get(4).getAttribute("innerText").toString().trim();
				String strTableActionStatus = cols.get(6).getAttribute("innerText").toString().trim();
				if(strTableAmount.contains(strCreditAmt) && strTableActionStatus.equals("Void")) { 
					
					blnAmountAssigned = true;
					Log.info("The Cash Credits Amount '"+strCreditAmt+"' is successfully assigned to the Account ID: "+strAccountNum);
					break;
				}
			}
		}
		objAriaEOM.fn_clickAnalyticsReporting(driver, webWait).click();
		
		return blnCreditGenerated;
	}
	
/*
	 Created By : Nashim Khan
	Function fn_ServiceCredits: Creates ServiceCredits
	Inputs: strAccountNum  - account number for which Assigned Cash Credits are to be Assign.
		   strCreditAmt    - The Credit Amount  which is to be applied. 
		   strCreditReason - SelectCredit Reason which is to be applied. 
	       strServiceCode  - Select Service Code which is to be applied.
	       strAlternativeCode -Select Alternative Code which is to be applied.
	     
	Outputs: Boolean status if the Service Credits is Created.
*/
	public Boolean fn_AssignServiceCredits (WebDriver driver, WebDriverWait webWait, String strAccountNum , String strCreditAmt,
			String strCreditReason, String strServiceCode, String strAlternativeCode, Integer intCreditsFrequency,
			Integer intCreditsApplicationScope,  String strTestCaseName) throws Exception {
		
		//TODO: Create object for the classes to be used for accessing reusable codes.
		AccountsPaymentsCredits  objAcctPaymentsCredits=new AccountsPaymentsCredits();
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
		AriaEOM objAriaEOM = new AriaEOM();
		Utils objUtils = new Utils();
		
		Boolean blnAmountAssigned = false;
		Boolean blnCreditGenerated = false;
		
		//TODO : Search a account and Apply Service Credit
		objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
		Thread.sleep(3000);
		objAriaEOM.fn_clickPaymentsCredits(driver, webWait).click();
		Thread.sleep(2500);
		objAcctPaymentsCredits.fn_clickServiceCredit(driver, webWait).click();
		objAriaEOM.fn_getDataTable(driver, webWait);		
		
		//TODO: Verify Service Credit Assignment to avoid any further re-assignment of Service Credits.
		Thread.sleep(3500);		
		WebElement webServiceCreditsListTable = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
		List<WebElement> lstwebServiceCreditsListTableRows = webServiceCreditsListTable.findElements(By.tagName("tr"));
		for(WebElement row : lstwebServiceCreditsListTableRows) {

			List<WebElement> cols = row.findElements(By.tagName("td"));
			if(cols.size() >= 4) {				
				
				String strTableAmount = cols.get(3).getAttribute("innerText").toString().trim();
				String strTableActionStatus = cols.get(6).getAttribute("innerText").toString().trim();
				strTableAmount = strTableAmount.replaceAll("[$|�|�|kr]", "");			
				strTableAmount = objUtils.fn_ConvertStringToBigDecimal(strTableAmount, 2);
				if(strTableAmount.equals(strCreditAmt) && strTableActionStatus.equals("Cancel")) {
					
					blnAmountAssigned = true;
					Log.info("The Service Credits with amount '"+strCreditAmt +"' is already assigned to the Account ID: "+strAccountNum);				
				}
				// Cancel all other already assigned Service Credits.
				if(strTableActionStatus.equals("Cancel") && blnAmountAssigned == false) {
					
					cols.get(6).findElement(By.tagName("a")).click();
					Thread.sleep(4000);
					objAcctPaymentsCredits.fn_clickConfirmationDljPayments(driver, webWait).click();
					Log.info("Existing / Already assigned Service Credits of '"+strTableAmount+"' is canceled successfully !");
				}
			}
		}
		Thread.sleep(3000);
		//TODO : Assign Service Credits Amount.
		if(blnAmountAssigned == false){	
			
			objAcctPaymentsCredits.fn_clickNewBtn(driver, webWait).click();
			objAcctPaymentsCredits.fn_setCreditAmount(driver, webWait).clear();
			objAcctPaymentsCredits.fn_setCreditAmount(driver, webWait).sendKeys(strCreditAmt);
		    objAcctPaymentsCredits.fn_selectCreditReason(driver, webWait).selectByVisibleText(strCreditReason);
		    objAcctPaymentsCredits.fn_selectServiceCode(driver, webWait).selectByVisibleText(strServiceCode);
		    Thread.sleep(3500);	   
		    try { 	    	
				
				objAcctPaymentsCredits.fn_selectAlternativeCode(driver, webWait).selectByVisibleText(strAlternativeCode);
			} catch (Exception e) {
				
				Log.info("Alternative code option is not aplicable for it.");
			}
/*		    objAcctPaymentsCredits.fn_getFrequency(driver, webWait, intCreditsFrequency).click(); // 1 for Once and 2 for Multiple
		    objAcctPaymentsCredits.fn_setApplicationScope(driver, webWait, intCreditsApplicationScope).click(); //1 For All Plan ,2 for Specific plan and 3 for Service type
*/		    objAcctPaymentsCredits.fn_setCSRComments(driver, webWait).clear();
		    objAcctPaymentsCredits.fn_setCSRComments(driver, webWait).sendKeys("This is Service Credits for automation test purpose !");
		    Utils.takeScreenshot(driver, strTestCaseName);
		    objAcctPaymentsCredits.fn_clickCreateCredit(driver, webWait).click();
		    Thread.sleep(5000);
		    Utils.takeScreenshot(driver, strTestCaseName);
		    objAcctPaymentsCredits.fn_clickCreateCreditFinal(driver, webWait).click(); 
			blnCreditGenerated=true;
		}
		Thread.sleep(2000);
		
		//TODO: Verify Service Credits Assignment again in Assigned Service Cedits Table.
		webServiceCreditsListTable = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
		lstwebServiceCreditsListTableRows = webServiceCreditsListTable.findElements(By.tagName("tr"));
		for(WebElement row : lstwebServiceCreditsListTableRows) {
			
			List<WebElement> cols = row.findElements(By.tagName("td"));
			if(cols.size() >= 1) {
				 
				String strTableAmount = cols.get(3).getAttribute("innerText").toString().trim();
				String strTableActionStatus = cols.get(6).getAttribute("innerText").toString().trim();
				if(strTableAmount.contains(strCreditAmt) && strTableActionStatus.equals("Cancel")) {
					
					blnAmountAssigned = true;
					Log.info("The Service Credits with amount '"+strCreditAmt+"' is successfully assigned to the Account ID: "+strAccountNum);
					break;
				}
			}
		}
		objAriaEOM.fn_clickAnalyticsReporting(driver, webWait).click();
		
		return blnCreditGenerated;
	}
}