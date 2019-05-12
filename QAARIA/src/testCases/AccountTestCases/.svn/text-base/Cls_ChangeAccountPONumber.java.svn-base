/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_ChangeAccountPONumber 
 Purpose     		: 	Purpose of this file is :
						1. To Verify that PO# changed via UI & API is displayed correctly for web Invoice & PDF Invoice.
						2. Test Class for the test case QAABE-360.
 
 Date       		:	05/02/2016
 Modified Date		:	
 Version Information:	Version 1.0
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "ChangeAccountPONumber" worksheet for excel "TestData.xlsx".
 						
 Test Steps 		:	1. Login using valid role credentials for accessing an account.
 						2. Navigate to the tab 'Purchase Orders' on Account Overview page.
 						3. Verify the Value for the PO # based on the active MPI.
 						4. If empty/unassigned, assign a new value.
 						4. Verify this UI modified value is retained.
 						5. Modify this value via API & verify this value on UI.
 						6. Generate an Invoice & check if the current PO # value is displayed on it.
 						7. View this Invoice in the PDF format and validate if the current PO # is displayed their. 						
 
 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AccountTestCases;

import atu.ddf.exceptions.DataDrivenFrameworkException;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JLabel;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AccountsStatementsInvoices;
import pageObjects.AriaEOM;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Invoices;
import appModules.AccountFunctions_Plans;
import appModules.AriaLoginLogout;
import utility.ApiHandler;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;

public class Cls_ChangeAccountPONumber extends VerificationMethods { 

    public static WebDriver driver;
    public static String strTestCaseName = "Verify changed PO number for any Account";
	public static WebDriverWait webWait;
    public static WebElement webElement;
    
    @BeforeClass
    public void beforeMethod()throws Exception {

    	//TODO: Initialize the Logger.
    	DOMConfigurator.configure("log4j.xml");	

		//TODO: Set Chrome driver as the driver to launch the test.
		driver = utility.Utils.fn_SetChromeDriverProperties();
		
		//TODO: initialize the webWait
		webWait = new WebDriverWait(driver, 60);
		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.info("Login to Aria Application");
		//TODO: This parameters are taken from Exec file and its reference is available in EnvironmentDetails
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
    }
    
    
/*    
    Function fn_VerifyChangedPONum: This is the actual Test NG test for Verifying the updated Customer Information.
*/
	@Test(dataProvider = "objectTestData", description = "Verify changed PO number for any Account")
    public void fn_VerifyChangedPONum(String strMPI, String strSPI, String strAccountNum, String strAPIURL, String strFileName
    			, String strCustomFieldDataUI, String strCustomFieldDataAPI, String strCustomPOValue
    			, String strInvoiceOption, String strInvoiceType, String strPendingInvDate) throws Exception {
		
    	//TODO: Create object for the classes to be used for accessing reusable codes.
    	AccountFunctions objAccountFunc = new AccountFunctions();	// for methods relating to Accounts functionality.
    	AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
    	AccountFunctions_Invoices objAcctFuncInvoices = new AccountFunctions_Invoices();
    	Utils objUtils = new Utils();
		ApiHandler objApiHandler = new ApiHandler();
		AriaEOM objAriaEOM = new AriaEOM();
		
		//TODO: declare booleans & objects to govern the actions to be performed.
		Boolean blnInvoiceGenerated = false;
		Boolean blnPONumVerified = false;
		Boolean blnLatestInvoiceSelected = false;
		Boolean blnInvoicePONumVerified = true;
		Boolean blnAdjustBillingDates = false;
		String strRestCall = "update_acct_complete_m";
		String strOptionType = null;
				
    	//TODO: Log the beginning of the test case.
    	Log.startTestCase(strTestCaseName);
    	
    	try {
    		
    		//TODO: At the very beginning of the TC, check if the Billing dates can be adjusted, else exit !
    		Log.info("'AdjustAcctBillDates' module is being called for MPI's Billing dates adjustment..");
    		blnAdjustBillingDates = objAccountFunc.fn_AdjustAcctBillDates(driver, webWait, strAPIURL, strAccountNum, 
    				strMPI, strInvoiceType, strPendingInvDate, strTestCaseName);
           	if(blnAdjustBillingDates == false)
           		assertTrue(false, "ERROR: Since the Billing Date's can't be adjusted, Invoicing will fail, so exiting TC !");
    		
           	//TODO: Get the MPI's & SPI's Plan & Client Defined ID's.
           	Log.info("Getting the Plan & Client Defined ID's for the MPI & SPI sequentially..");
           	String strMPID = objAcctFuncPlans.fn_GetPlanInstanceID(driver, webWait, strAccountNum, strTestCaseName, strMPI);
           	String[] arrMPID = strMPID.split("#");	// [0] = Plan Instance ID; [1] = Client Defined ID.           	
           	String strSPID = objAcctFuncPlans.fn_GetPlanInstanceID(driver, webWait, strAccountNum, strTestCaseName, strSPI);
           	String[] arrSPID = strSPID.split("#");	// [0] = Plan Instance ID; [1] = Client Defined ID.
           	
    		//TODO: For the specified account, set the PO Number for verification on Invoice via UI.
           	objAriaEOM.fn_clickAccounts(driver, webWait).click();
           	objAriaEOM.fn_clickAccountOverview(driver, webWait).click();
           	objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);           	
           	
           	//TODO: Do the UI related modifications for the 
           	String strPONum = objAccountFunc.fn_AccountPONumber(driver, webWait, strAccountNum, arrMPID[0], "", true, strTestCaseName);           	
    		if(strPONum.contentEquals(""))	// set the field value as the strCustomFieldDataUI for test.
    			strPONum = objAccountFunc.fn_AccountPONumber(driver, webWait, strAccountNum, arrMPID[0], strCustomFieldDataUI, false, strTestCaseName);
    		else	// we are updating the value to strCustomFieldDataUI for test.
    			strPONum = objAccountFunc.fn_AccountPONumber(driver, webWait, strAccountNum, arrMPID[0], strCustomFieldDataUI, false, strTestCaseName);

    		//Now, Verify that the UI set PO Number value is retained.
    		Log.info("Verifying if the 'PO #' value is retained after changing it through UI..");
    		strPONum = objAccountFunc.fn_AccountPONumber(driver, webWait, strAccountNum, arrMPID[0], "", true, strTestCaseName);
    		if(strPONum.contentEquals(strCustomFieldDataUI))
    			Log.info("The PO # changed via UI is successfully retained !");    		
    		
    		//TODO: For the specified account, set the PO Number for verification on Invoice via API call.
			Log.info("Making the API call for updating the PO Num value.");
			String strAPICall = objAccountFunc.fn_GetFullUrl(driver, webWait, strAPIURL, strRestCall);
			strAPICall = strAPICall + "&acct_no="+strAccountNum	+ "&client_master_plan_instance_id="+arrMPID[1] 
									+ "&mp_po_num="+strCustomFieldDataAPI;
			List<JSONObject> apiResponse = objApiHandler.makeSimplePostCall(strAPICall);	//make the API call to modify the PO value.
			//TODO: Verify JSON response to validate success.
            for(JSONObject response : apiResponse) {
            	
            	int errorCode = Integer.parseInt(response.get("error_code").toString());	
                if(errorCode != 0) {
                	
                    String errorMsg = response.get("error_msg").toString();
                    Log.error("ERROR: API call failed with message: "+errorMsg);
                    Reporter.log("ERROR: API call failed with message: " + errorMsg);
                 }
                 else {
                	 
                 	 //TODO: Verify that the API set PO Number value is retained.
                	 objAriaEOM.fn_clickAccounts(driver, webWait).click();
                   	 objAriaEOM.fn_clickAccountOverview(driver, webWait).click();
                   	 objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);
                	 strPONum = objAccountFunc.fn_AccountPONumber(driver, webWait, strAccountNum, arrMPID[0], "", true, strTestCaseName);
             		 if(strPONum.contentEquals(strCustomFieldDataAPI))
             			Log.info("The PO # changed via API is successfully retained; verified via UI !");
             		 else {
             			
             			 Log.error("ERROR: Verification for the PO # changed via API failed !");
             			 Reporter.log("ERROR: Verification for the PO # changed via API failed !");             			 
             		 }             			 
                 }
             }			
    		
    		//TODO: Load Data for specified account for load date.
       		String LOAD_USAGE_FILE_PATH = System.getProperty("user.dir")
                    + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
                    + System.getProperty("file.separator") + strFileName;           	
       		String strLoadDataDate = objUtils.fn_getLoadDataDate();
           	Log.info("Loading Account Usage for the date '"+strLoadDataDate+"'..");
           	ArrayList<String> arrListResponses = objAccountFunc.fn_LoadAccountUsage(driver, webWait, strAPIURL, LOAD_USAGE_FILE_PATH, strAccountNum, strLoadDataDate, arrMPID[1], arrSPID[1]);
           	if(arrListResponses.size() >= 1)
           		assertTrue(false, "ERROR: There was error recorded while Loading Usage, hence exiting the TC !");
      		           	
    		//TODO: Generate Advance Invoice for the Account for which data is currently uploaded & Verify Print Invoice.
           	if(strInvoiceOption.equalsIgnoreCase("MPI"))
           		strOptionType = arrMPID[0]+" - "+strMPI;
           	else if (strInvoiceOption.equalsIgnoreCase("Billing")) {
           		
           		//TODO: give a call to fetch the Billing Group Name and use it here.
           		String strBillingGroupName = null; /* REPLACE NULL WITH THE BILLING GROUP NAME */ 
           		strOptionType = strBillingGroupName;
           	}
           	blnInvoiceGenerated = objAcctFuncInvoices.fn_GenerateInvoice(driver, webWait, strAccountNum, strInvoiceType, "MPI", strOptionType, strTestCaseName);
           	if(blnInvoiceGenerated == true) {
           		
           		objAcctFuncInvoices.fn_ApproveAdvancedInvoices(driver, webWait);
           		Thread.sleep(1000);
           		Utils.takeScreenshot(driver, strTestCaseName);
           		//Since Invoice has been generated and Approved, we generate the Statement.
           		objAriaEOM.fn_clickAccounts(driver, webWait).click(); 
           		objAcctFuncInvoices.fn_GenerateInvoiceStatement(driver, webWait, strOptionType, strTestCaseName);
           		objAriaEOM.fn_clickAccounts(driver, webWait).click();
           	}
           	else {
           		
           		Utils.takeScreenshot(driver, strTestCaseName);
           		assertTrue(false, "ERROR: Invoice couldn't be generated !");
           	}
           	
           	Log.info("Verifying the newly generated Advance Invoice for the PO Number value...");           	
           	//TODO: Displaying a Message Box to indicate the user that there is a hard-coded wait for populating data for View & Print.
           	String strPrintFrameMsg = "<html><div align='center'><font size='5' face='SANS_SERIF'>There is a 1 minute wait introduced for Invoice Details to reflect on View Print Frame.</font>"
					+ "<br><br><br><font size='20' face='SANS_SERIF' color='red'>Please do not touch the browser; can cause TC to fail !</font>"
					+ "<br><br><br><br><br><br><br><font size='5' face='SANS_SERIF'>Please Note: This Message Box will be auto closed after 1 minute !</font></div></html>";
           	JDialog jdlgPrintFrame = new JDialog();
           	jdlgPrintFrame.setTitle("WARNING !");
           	jdlgPrintFrame.setModal(false);
           	jdlgPrintFrame.add(new JLabel(strPrintFrameMsg, JLabel.CENTER));
           	jdlgPrintFrame.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
           	jdlgPrintFrame.setAlwaysOnTop(true);
           	jdlgPrintFrame.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
           	jdlgPrintFrame.setResizable(false);
           	jdlgPrintFrame.pack();
           	jdlgPrintFrame.setVisible(true);
           	Thread.sleep(60000);
           	jdlgPrintFrame.dispose();
           	
           	blnLatestInvoiceSelected = objAcctFuncInvoices.fn_SelectLatestInvoice(driver, webWait);
           	if(blnLatestInvoiceSelected == false)
           		assertTrue(false, "ERROR: Invoice couldn't be selected !");
           	Utils.takeScreenshot(driver, strTestCaseName);
           	//TODO: Validate if the PO Number is displayed on the Invoice details Web Page. 
           	WebElement webInvoiceTable = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
           	List<WebElement> lstwebInvoiceRows = webInvoiceTable.findElements(By.tagName("tr"));
           	for(WebElement rows: lstwebInvoiceRows) {
           		
           		List<WebElement> columns = rows.findElements(By.tagName("td"));
           		if(columns.size() != 0) {
           			
           			String strInvoiceDesc = columns.get(2).getAttribute("outerText").toString().trim();
           			if(!strInvoiceDesc.contains("Taxes") && !strInvoiceDesc.equals(" ")) {
           				
           				String strInvoicePONum = columns.get(4).getAttribute("outerText").toString().trim();
           				if(strInvoicePONum.contains(strCustomFieldDataAPI)) 
           					Log.info("For line item '"+strInvoiceDesc+"', correct PO# "+strCustomFieldDataAPI+" has been displayed !");
           				else {
           					
           					Log.error("ERROR: Incorrect PO # is displayed for line item '"+strInvoiceDesc+"'..");
           					Reporter.log("ERROR: Incorrect PO # is displayed for line item '"+strInvoiceDesc+"'..");
           					blnInvoicePONumVerified = false;
           				}
           			}
           		}
           	}
           	
           	blnPONumVerified  = fn_VerifyViewPrintFrame(driver, webWait, strAccountNum, strCustomFieldDataAPI);
           	
           	if(blnInvoicePONumVerified == false)
           		assertTrue(false, "ERROR: The Invoice generated doesn't have the PO Number '"+strCustomFieldDataAPI+"'in it !");
           	if(blnPONumVerified == false)
           		assertTrue(false, "ERROR: The PDF Invoice generated doesn't have the PO Number '"+strCustomFieldDataAPI+"'in it !");
    	}
    	catch (Exception exception) {
    		
    		Utils.takeScreenshot(driver, strTestCaseName);
    		Log.error("ERROR: Verification for the PO Number change couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Verification for the PO Number change couldn't be done due to exception:  "+exception.toString());
    		throw exception;
    	}
    	catch (AssertionError exception) {
    		
    		Utils.takeScreenshot(driver, strTestCaseName);
    		Log.error("ERROR: Verification for the PO Number change couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Verification for the PO Number change couldn't be done due to exception:  "+exception.toString());
    		throw exception;
    	}
    	    	
    	//TODO: Log the end of the test case. 
    	Log.endTestCase(strTestCaseName);		
    }	


/*    
	Function fn_VerifyViewPrintFrame: This method will verify if the Print Frame has the latest PO Number in it.    						
*/	
    @Test(dependsOnGroups = {"Sanity"} , dependsOnMethods = {"fn_VerifyClientDefinedFieldRetained"}, enabled = false)	// marks this method dependent on parent method.
    public Boolean fn_VerifyViewPrintFrame(WebDriver driver, WebDriverWait webWait, String strAccountNum
    		, String strCustomFieldData) throws Exception {

    	//TODO: Create object for the classes to be used for accessing reusable codes.
    	AccountFunctions_Invoices objAcctFuncInvoices = new AccountFunctions_Invoices();
    	AriaEOM objAriaEOM = new AriaEOM();
    	AccountsStatementsInvoices objAcctStmntInv = new AccountsStatementsInvoices();
        PDDocument document = null;
        Robot rbDownloadFile = new Robot();
    	
    	Boolean blnPONumSearchStatus = false;
    	String strParentWindow = driver.getWindowHandle();
    	String strPrintWindow = null;		
    	
    	//Xpath for the PO# text (Firefox) //xhtml:div[11] // This string has been kept as is for any future development in Firefox browser.
    		
		objAriaEOM.fn_getDataTable(driver, webWait);
		Thread.sleep(2500);
		String strInvoiceNum = objAcctStmntInv.fn_getInvoiceNumber(driver, webWait).getAttribute("innerText").toString().trim();
		strInvoiceNum = strInvoiceNum.substring(strInvoiceNum.lastIndexOf(":")+2, strInvoiceNum.length());
		
		objAcctStmntInv.fn_clickViewPrint(driver, webWait).click();
		String strPONum = null;    		
		Thread.sleep(10000);    		

		//TODO: get the frame for the View & Print and verify the PO Number on it.
		Set <String> strWindowHandles = driver.getWindowHandles();    		
		strWindowHandles.remove(strParentWindow);
		strPrintWindow = strWindowHandles.iterator().next();
		
		driver.switchTo().window(strPrintWindow);	// setting the window driver to the newly launched Print Frame Window.
		driver.manage().window().maximize();
		Thread.sleep(10000);
	    		
    	try {
    		
    		WebElement webPageContents = webWait.until(ExpectedConditions.elementToBeClickable(objAcctFuncInvoices.fn_ReadViewPrintPage(driver).findElement(By.xpath("//table[@class = 'inv']"))));
    		WebElement webInvoicePrintDetails = webPageContents.findElement(By.tagName("tbody"));
    		Utils.takeScreenshot(driver, strTestCaseName);
    		List<WebElement> lstwebInvoiceRows = webInvoicePrintDetails.findElements(By.tagName("tr"));
    		Log.info("Verifying the generated Invoice for the PO Number '"+strCustomFieldData+"'...");
    		for(WebElement row: lstwebInvoiceRows) {

    			List<WebElement> cols = row.findElements(By.tagName("td"));	    			
    			if(cols.size() == 1) {

    				strPONum = cols.get(0).getAttribute("innerText").toString().trim();
    				if(strPONum.contains(strCustomFieldData)) {

    					Log.info("The Invoice generated has the latest updated PO Number in it as: "+strPONum);
    					blnPONumSearchStatus = true;
    					break;
    				}
    			}
    		}
    		
    		driver.close();
    		driver.switchTo().window(strParentWindow);
		}
		catch (Exception exception) {
			
			//TODO: Since this is an embedded PDF, generate the PDF file name for downloading it for validations.			
			Date date = new Date() ;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS") ;
	    	String strFileName = strAccountNum+"_Invoice_"+strInvoiceNum+"_"+dateFormat.format(date)+".pdf";
	   		String fileDownloadInvoicePDF = System.getProperty("user.dir")
	                + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
	                + System.getProperty("file.separator") + strFileName;
		
    		//TODO: Once the Print Frame is launched, Save the file and use PDF Stripper to Read it & Validate Data.
    		driver.switchTo().activeElement().sendKeys(Keys.TAB);	// traverse to the Print Control.
    		driver.switchTo().activeElement().sendKeys(Keys.TAB);	// traverse to the Pagination Control.
    		driver.switchTo().activeElement().sendKeys(Keys.TAB);	// traverse to the Rotate View Control.
    		driver.switchTo().activeElement().sendKeys(Keys.TAB);	// traverse to the Download Control.
    		driver.switchTo().activeElement().sendKeys(Keys.RETURN);		
    		Thread.sleep(2500);

    		driver.switchTo().window(strPrintWindow);
    		StringSelection strselDownloadLocation = new StringSelection(fileDownloadInvoicePDF);    		
    		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    	    clipboard.setContents(strselDownloadLocation, strselDownloadLocation);    	    
    	    rbDownloadFile.keyPress(KeyEvent.VK_CONTROL);	//pass on the filename.
    	    rbDownloadFile.keyPress(KeyEvent.VK_V);
    	    rbDownloadFile.keyRelease(KeyEvent.VK_V);
    	    rbDownloadFile.keyRelease(KeyEvent.VK_CONTROL);
    	    Thread.sleep(2500);
    	    rbDownloadFile.keyPress(KeyEvent.VK_ALT);	//click the Save button on the 'Save As' popup. 
    	    rbDownloadFile.keyPress(KeyEvent.VK_S);
    	    rbDownloadFile.keyRelease(KeyEvent.VK_S);
    	    rbDownloadFile.keyRelease(KeyEvent.VK_ALT);
    	    Thread.sleep(15000);	// time given for the file to be copied to the specified location.
    	    driver.close();
    	    driver.switchTo().window(strParentWindow);    	    
    		
    		FileInputStream file = new FileInputStream(new File(fileDownloadInvoicePDF));
            document = PDDocument.load(file);
            PDFTextStripper pdfStrip = new PDFTextStripper();
            String strPDFText = pdfStrip.getText(document);
            Log.info("Reading the Latest Invoice PDF file '"+strFileName+"' for validating the PO # in it...");
            document.close();
            strPONum = "PO# "+strCustomFieldData;
            if(strPDFText.contains(strPONum)) {
            	
            	Log.info("The Invoice generated has the latest updated PO Number in it as: "+strPONum);
            	blnPONumSearchStatus = true;
            }
            else {
            	
            	Log.error("ERROR: The Invoice generated doesn't have the latest updated PO Number in it as: "+strPONum);
            	Reporter.log("ERROR: The Invoice generated doesn't have the latest updated PO Number in it as: "+strPONum);
            	blnPONumSearchStatus = false;
            }            
		}
		Thread.sleep(1000);
    	
    	return blnPONumSearchStatus;
    }     
    

	//TODO: Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException {
	
		Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "ChangeAccountPONumber");
	}
	
	
	@AfterTest
	public void afterMethod() throws Exception {

		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
}