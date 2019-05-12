
/*
 Author     		:	Madhavi JN
 Class Name			: 	Cls_VerifyAdditionofMultipleCouponsAndDiscount 
 Purpose     		: 	Purpose of this file is :
						1. To Verify that Multiple Coupons are applied to the Account and Validate the discount accordingly.
						2. Test Class for the test case QAABE-466.
 
 Date       		:	12/30/2015 
 Modify Date        :   10/05/2016 
 Modify By          :   Nashim Khan
 
 Version Information:	1.0
 
 PreCondition 		:	1. Role based Login required.	
 						2. Data to be populated in the "TestCaseDetails" & "VerifyMultipleCoupons" column for excel "TestData.xlsx".
 						
 Test Steps 		:	1. Login using valid role credentials for accessing an account.
 						2. Navigate to the searched Accounts. 						
 						3. Adjust Billing Date to Generate Invoice
 						4. Apply the required Multiple Coupons.
 						5. Load the Usage and Generate Invoice->Select the Latest Invoice.
 						6. Open the Latest Invoice and verify whether the Coupons are applied correctly.

 						
 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AccountTestCases;

import org.apache.log4j.xml.DOMConfigurator;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import appModules.AriaLoginLogout;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.util.NameValuePair;

import pageObjects.AriaEOM;
import appModules.AccountFunctions;
import appModules.AccountFunctions_Invoices;
import appModules.AccountFunctions_Plans;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_VerifyAdditionofMultipleCouponsAndDiscount extends VerificationMethods {
	
	public static WebDriver driver;
	public static WebDriverWait webWait;
	
	public static String strTestCaseName = "Verify Applying Multiple Coupons";
	
	@BeforeClass
	 public static void beforeMethod()throws Exception  {	
		DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
		
		//TODO: Set Chrome driver as the driver to launch the test.
		driver = utility.Utils.fn_SetChromeDriverProperties();
				
		//TODO: initialize the webWait
		webWait = new WebDriverWait(driver, 60);
		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.info("Login to application");
		//This parameters are taken from Exec file and its reference is available in EnvironmentDetails
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
	 }	
	

	@Test(dataProvider="objTestData",description="VerifyMultipleCoupons") 
	public void VerifyCreditCoupounsandDiscount(String strAcctNum, String strMPI,  String strAPIURL, 
			String strFilename,  String DiscCoupon,	String strSPI, 
			String strInvoiceType, String strPendingInvDate ) throws Exception {

		AccountFunctions objAcctFnctns = new AccountFunctions();
		AccountFunctions_Invoices objInvoices = new AccountFunctions_Invoices();	
		AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
		AriaEOM objAriaEOM = new AriaEOM();
		Utils objUtils=new Utils();
		
		Boolean blnCouponsValidated = false;
		Boolean blnCreditDiscountsValidated = true;
				
		Log.startTestCase(strTestCaseName);		

		try {
		 
			//TODO: Load Data for specified account for current date.
       		String LOAD_USAGE_FILE_PATH = System.getProperty("user.dir")
                    + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
                    + System.getProperty("file.separator") + strFilename;
       		
			//TODO : Adjust Billing dates for the Account to Generate Invoice  	
       		boolean blnAdjustDate=objAcctFnctns.fn_AdjustAcctBillDates(driver, webWait, strAPIURL, strAcctNum,strMPI, 
       											strInvoiceType, strPendingInvDate,strTestCaseName);				
			if (blnAdjustDate==true)
				 Log.info("Account Billing Date has been set successfully");			
			else {
				
				Log.info("ERROR: Account Billing Date has not been set");
				assertTrue(false, "ERROR: Since the Billing Date's can't be adjusted, Invoicing will fail, so exiting TC !");
			 }
			
    		String[] arrCoupon = DiscCoupon.split("#",2);
    		for (int i=0; i<arrCoupon.length; i++ ){
    			
	   			//TODO:; Assign Credit Coupon to the Account and verify it further in the Invoice
	    		boolean blnAssignCoupon =  objInvoices.fn_AssignCreditCoupon(driver, webWait, strAcctNum, arrCoupon[i], strTestCaseName);
	    		if(blnAssignCoupon == false)
	    			assertTrue(false, "ERROR: Coupon couldn't be assigned to the Account : "+strAcctNum);
	    		else
	    			Log.info("Coupon '"+arrCoupon[i]+"' has been successfully assigned !");
    		}   	
	    		
	    	//TODO: Fetching the Date for Loading Usage	
	    	String strLoadDataDate=objUtils.fn_getLoadDataDate();
	    	Log.info("Loading Account Usage for the date '"+strLoadDataDate+"'..");	           	
	    	Thread.sleep(200);
	           	
	    	//TODO: Generate MPID and SPID
	    	String strMPInstance = objAcctFuncPlans.fn_GetPlanInstanceID(driver, webWait, strAcctNum, strTestCaseName, strMPI);
	    	String[] arrMPID = strMPInstance.split("#");
	    	String strSPInstance = objAcctFuncPlans.fn_GetPlanInstanceID(driver, webWait, strAcctNum, strTestCaseName, strSPI);
	    	String[] arrSPID = strSPInstance.split("#"); 
	   		    
	    	//TODO: Loading the Usage
	    	List<String> lststrApiResponse =objAcctFnctns.fn_LoadAccountUsage(driver, webWait, strAPIURL,LOAD_USAGE_FILE_PATH, strAcctNum, strLoadDataDate,arrMPID[1], arrSPID[1]);
	    	
	    	//TODO: Verify Usage uploaded  success.
	    	if(lststrApiResponse.size() == 0) {
            	
	    		Log.info("ERROR: Loading Record Usage is failing with "+lststrApiResponse.size()+"   strack trace as printed...");
            	assertTrue(false, "ERROR: Loading Record Usage is failing..");            	
            }
	    	else
	    		Log.info("Loading Record Usage is successfully with "+lststrApiResponse.size()+" strack trace as printed...");
	    	
	    	String strOptionType=arrMPID[0]+" - "+strMPI;	
	    	//TODO: Advance Invoice for the Account for which data is currently uploaded & Verify Print Invoice.	           
	    	boolean blnInvoiceGenerated = objInvoices.fn_GenerateInvoice(driver, webWait, strAcctNum,strInvoiceType,"MPI",strOptionType, strTestCaseName);
	    	Thread.sleep(2000);	    	
           	if(blnInvoiceGenerated == true) {
           		
           		boolean blnInvoiceApproved = objInvoices.fn_ApproveAdvancedInvoices(driver, webWait);
           		Utils.takeScreenshot(driver, strTestCaseName);
           		if(blnInvoiceApproved == false)
           			Log.info("ERROR: Invoice generated couldn't be approved !");
           		
           		//Since Invoice has been generated and Approved, we generate the Statement.
           		objAriaEOM.fn_clickAccounts(driver, webWait).click();
           		objInvoices.fn_GenerateInvoiceStatement(driver, webWait, strOptionType, strTestCaseName);
           	}
           	else {
           		
           		Log.info("ERROR: Invoice couldn't be generated !");
           		assertTrue(false, "ERROR: Invoice is not generated, so exiting TC !");	           		
           	}
           
	        //TODO : Select the Latest Invoice
	        boolean blnLatestInvoiceSelected = objInvoices.fn_SelectLatestInvoice(driver, webWait);           	
	    	if(blnLatestInvoiceSelected == false) {
	
	       		Log.info("ERROR: Latest Invoice couldn't be selected !");
	       		assertTrue(false, "ERROR: Latest Invoice is not Selected, so exiting TC !");
	       	}    	
	       	else       		
	       		Log.info("Latest Invoice is selected Successfully");
	       	
	        Thread.sleep(2000);				
	        
	        //TODO : To identify the Invoice Table and capture the Invoice price.
	        List<NameValuePair> lstNVPInvoice = new ArrayList<NameValuePair>();
	    	List<String> lstCoupon1PerUnitDisc = new ArrayList<String>();
	    	List<String> lstCoupon2PerUnitDisc = new ArrayList<String>();
	    	
	        WebElement webInvoiceTable = objAriaEOM.fn_getDataTable(driver, webWait);			  
	        WebElement webInvoiceBody = webInvoiceTable.findElement(By.tagName("tbody"));			  
	        List<WebElement> lstwebInvoiceRows = webInvoiceBody.findElements(By.tagName("tr"));
	        for (WebElement row : lstwebInvoiceRows) {
					  
	        	List<WebElement> col = row.findElements(By.tagName("td"));        	
	        	String strTableCouponDesc=col.get(2).getAttribute("innerText").toString().trim();
	        	String strTblInvoiceTotal = col.get(8).getAttribute("innerText").toString().trim();
	        	
	        	//TODO :Read the text to know what Tag we are dealing with for the Value to be read.        	       	
	    		if(!strTableCouponDesc.contains("4% Revenue Commit Discount") && !strTableCouponDesc.contains("10% FOR 36 MONTHS") 
	    				&& !strTableCouponDesc.contains("Taxes") && !strTblInvoiceTotal.contains("TOTAL")){
	    		
	    			String strDescription = col.get(2).getText().toString().trim();
	    			String strOvwItemPerUnitPrice = col.get(6).getText().toString().trim();
	    			strOvwItemPerUnitPrice = objUtils.fn_ConvertStringToBigDecimal(strOvwItemPerUnitPrice, 4);
	    			lstNVPInvoice.add(new NameValuePair(strDescription, strOvwItemPerUnitPrice));    			
	    		}
	    		
	    		//TODO :Read the First Coupon Discount  text to know what Tag we are dealing with for the Value to be read.    	
	        	String strCoupon1DicountPrice = null;
	        	if (strTableCouponDesc.contains("10% FOR 36 MONTHS")) {
	        		
	        		strCoupon1DicountPrice = col.get(6).getText().toString().trim();
	        		strCoupon1DicountPrice = strCoupon1DicountPrice.replaceAll("-", "");
	        		//double dblFirstCoupenPerUnitDissePrice = Double.parseDouble(strFirstCoupon);
	        		strCoupon1DicountPrice = objUtils.fn_ConvertStringToBigDecimal(strCoupon1DicountPrice, 4);
	        		lstCoupon1PerUnitDisc.add(strCoupon1DicountPrice);
	        		
	    			}
	        	
	        	//TODO :Read the Second Coupon Discount  text to know what Tag we are dealing with for the Value to be read.           	
	        	String strCoupon2DicountPrice=null;
	        	if (strTableCouponDesc.contains("4% Revenue Commit Discount")) {
	        		
	        		strCoupon2DicountPrice = col.get(6).getText().toString().trim();
	        		strCoupon2DicountPrice = strCoupon2DicountPrice.replaceAll("-", "");
	        		//double dblFirstCoupenPerUnitDissePrice = Double.parseDouble(strSecCoupon);
	        		strCoupon2DicountPrice = objUtils.fn_ConvertStringToBigDecimal(strCoupon2DicountPrice, 4);
	        		lstCoupon2PerUnitDisc.add(strCoupon2DicountPrice);
	    		}        	
	        }
	        
	        //TODO :Read the Values and Apply 10% Code  Coupon On Unit Price Value.
	        List<NameValuePair> lstPerUnitPriceAfter10Per = new ArrayList<NameValuePair>();
	        if(lstNVPInvoice.size()== lstCoupon1PerUnitDisc.size()) {
	        	
		    	for(Integer intList = 0; intList < lstNVPInvoice.size(); intList++)	{
				  
		        	String strDescription = lstNVPInvoice.get(intList).getName().toString().trim();
		        	String strProdUnitPriceValue = lstNVPInvoice.get(intList).getValue().toString().trim();        	
		        	BigDecimal bdValue = new BigDecimal(strProdUnitPriceValue.replace(",", ""));
		        	bdValue = bdValue.multiply(new BigDecimal(0.1));
		        	String strProdUnitPriceValueLocal = objUtils.fn_ConvertStringToBigDecimal(bdValue.toString(), 4);
		        	blnCouponsValidated = false;
		        	for(Integer intFirst = 0; intFirst < lstCoupon1PerUnitDisc.size(); intFirst++) {
		        		
		        		String strCoupon1UnitPrice = lstCoupon1PerUnitDisc.get(intFirst).toString().trim();        		 
			        	if (strProdUnitPriceValueLocal.contentEquals(strCoupon1UnitPrice)) {
			        		
							Log.info("The 10% Discount Coupon is correctly applied for :: "+strDescription+" with Discount Value as :: "+strCoupon1UnitPrice);
							blnCouponsValidated = true;
							BigDecimal   bdecProdUnitPriceValue= new BigDecimal(strProdUnitPriceValue.replace(",", ""));
							BigDecimal   bdecCoupon1UnitPrice= new BigDecimal(strCoupon1UnitPrice.replace(",", ""));    		        	
							BigDecimal bdValueAfter10Per = bdecProdUnitPriceValue.subtract(bdecCoupon1UnitPrice);    					
						    String strProdUnitPriceValueAfter10Per = objUtils.fn_ConvertStringToBigDecimal(bdValueAfter10Per.toString(), 4);		
						    lstPerUnitPriceAfter10Per.add(new NameValuePair(strDescription, strProdUnitPriceValueAfter10Per));
			                break;
			        	}
			        	
			        	if (intFirst >= lstCoupon1PerUnitDisc.size() - 1 && blnCouponsValidated == false){
			        		
			        		Log.error("The 10% Discount is not applied correctly for :: "+strDescription
			        					+" with expected Discount Value as :: "+strProdUnitPriceValueLocal); 
							Reporter.log("The 10% Discount is not applied correctly for :: "+strDescription
		        					+" with expected Discount Value as :: "+strProdUnitPriceValueLocal);
							blnCreditDiscountsValidated = false;
			        	}    	        	
		        	}
		    	} 
	        }
	        else {
	        	
	            Log.error("ERROR: Since the 10% Discounted Rates aren't applied for all line items, exiting w/o comparing the actual values !");
	        	Reporter.log("ERROR: Since the 10% Discounted Rates aren't applied for all line items, exiting w/o comparing the actual values!");
	        	blnCreditDiscountsValidated = false;
	        }
	        //TODO :Read the Values and Apply 4% Code  Coupon On Unit Price Value.
	        if(lstPerUnitPriceAfter10Per.size() == lstCoupon2PerUnitDisc.size()) {
	        	
		        for(Integer intList = 0; intList < lstPerUnitPriceAfter10Per.size(); intList++)	{			  
		        	
		        	String strDescription = lstPerUnitPriceAfter10Per.get(intList).getName().toString().trim();
		        	String strProdUnitPriceValue = lstPerUnitPriceAfter10Per.get(intList).getValue().toString().trim();
		        	BigDecimal bdValue = new BigDecimal(strProdUnitPriceValue.replace(",", ""));
		        	bdValue = bdValue.multiply(new BigDecimal(0.04));
		        	strProdUnitPriceValue = objUtils.fn_ConvertStringToBigDecimal(bdValue.toString(), 4);
		        	blnCouponsValidated = false;
		        	for(Integer intSecond = 0; intSecond < lstCoupon2PerUnitDisc.size(); intSecond ++) {
		        		 
		        		String strCoupon2UnitPrice = lstCoupon2PerUnitDisc.get(intSecond).toString().trim();	        	
		        		if (strProdUnitPriceValue.contentEquals(strCoupon2UnitPrice)) {
			        		
							Log.info("The 4% Discount Coupon is correctly applied for :: "+strDescription+" with Discount Value as :: "+strCoupon2UnitPrice);
							blnCouponsValidated = true;
			                break;
			        	}
		        		
		        		if (intSecond >= lstCoupon2PerUnitDisc.size()-1 && blnCouponsValidated == false){
			        		
			        		Log.error("The 4% Discount is not applied correctly for :: "+strDescription
			        					+" with expected Discount Value as :: "+strProdUnitPriceValue); 
							Reporter.log("The 4% Discount is not applied correctly for :: "+strDescription
		        					+" with expected Discount Value as :: "+strProdUnitPriceValue);
							blnCreditDiscountsValidated = false;
			        	}              	
		        	}        
		        }            
	        }
	        else {
	        	
	        	Log.error("ERROR: Since the 4% Discounted Rates aren't applied for all line items, exiting w/o comparing the actual values !");
	        	Reporter.log("ERROR: Since the 4% Discounted Rates aren't applied for all line items, exiting w/o comparing the actual values!");
	        	blnCreditDiscountsValidated = false;
	        } 
	       
	        //TODO: Govern the status of the TC execution based on the boolean status gathered over various Validation stages. 
	        if(blnCreditDiscountsValidated == false)
	        	assertTrue(false, "ERROR: Since the  Discounted Rates aren't applied for all line items, exiting w/o comparing the actual values !");
    
		}
		catch(Exception e){
			
	    	Log.error("ERROR: There is an issue while validating the Credit Coupons Applied, TC failed & hence exiting with exception !");
	    	Reporter.log("ERROR: There is an issue while validating the Credit Coupons Applied, TC failed & hence exiting with exception !");
			e.printStackTrace();
		}
		catch (AssertionError errorAssert) {
			
	    	Log.error("ERROR: There is an issue while validating the Credit Coupons Applied, TC failed & hence exiting with exception !");
	    	Reporter.log("ERROR: There is an issue while validating the Credit Coupons Applied, TC failed & hence exiting with exception !");
			errorAssert.printStackTrace();
		}
		
		Log.endTestCase(strTestCaseName);	    
	}
	
    @DataProvider(name="objTestData")
    public Object[][] data() throws DataDrivenFrameworkException {
    	
    	Utils objutils = new Utils();
    	return objutils.data1("TestCaseDetails", "VerifyMultipleCoupons");
    	
    }
    
	@AfterClass
	public void afterMethod() throws Exception {
		
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
}