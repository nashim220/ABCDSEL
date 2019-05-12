/*
 Author     		:	Joe Ganon
 Class Name			: 	AccountFunctions 
 Purpose     		: 	Purpose of this file is :
						1. To perform various Account related functions.

 Date       		:	 
 Modified Date		:	12/09/2015, 10/23/2015, 09/24/2015, 03/09/2015, 18/06/2015, 12/06/2015, 12/04/2015, 12/30/2015
 Modified By		: 	Aashish Bhutkar, Namrata Akarte
 Version Information:	Version 10.0

 Version Changes 2.0:	1. Added custom methods to get generic data as per instance instead of hard coded values.
 Version Changes 3.0:	1. Added couple more generalized methods like "fn_VerifySupplementalPlans" & "fn_AssignSuppPlan".
 Version Changes 4.0:	1. Added one more reusable method like "fn_GetSupplementalPlanInstanceID".
 Version Changes 5.0:	1. Added more reusable method like "fn_GenerateInvoice", "fn_GeneratePayments", "fn_VoidPayments", "fn_BookingRefunds".
 Version Changes 5.1:	1. Updated methods for Payments & Refunds with some more logical log statements.
 Version Changes 6.0:	1. Moved methods related to Invoices to "AccountFunctions_Invoices" class file.
 						2. Moved methods related to Plans to "AccountFunctions_Plans" class file.
 Version Changes 7.0:	1. Added new method "fn_AdjustAcctBillDates" for adjusting Accounts's Billing Dates. 	
 Version Changes 8.0:	1. Added function fn_AssignChildToParent to assign child to parent
 Version Changes 9.0:	1. Added new method "fn_getPreviousDate" for getting a previous date in format "yyyy-mm-dd".
 Version Changes 9.1:	1. Changed return type of function fn_LoadAccountUsage from 'void' to 'ArrayList<String>'
 Version Changes 10.0:	1. Added new method "fn_getAccountDetails" for capturing the Accounts Owner / Biller Information.
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package appModules;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import pageObjects.AccountPlansUsage;
import pageObjects.AriaAccountPageObject;
import pageObjects.NewAccountCreation;
import utility.ApiHandler;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by joseph.gannon
 */
public class AccountFunctions
{
	public static WebElement webElement;
	
/*
	Author: Aashish Bhutkar
	Function fn_GetFullUrl: Takes in the details of current instance and passes the Client Number & Authentication Key. Gets JSON Response ONLY.
*/
	
	public String fn_GetFullUrl(WebDriver driver, WebDriverWait webWait, String strAPIURL, String strRestCall) throws Exception
	{
		//TODO: Get the Client ID & authentication key from the current instance.
		Cls_GetAuthenticationKey objGetAuthKey = new Cls_GetAuthenticationKey();
		AccountPlansUsage objPlansUsage = new AccountPlansUsage();
		
		Thread.sleep(2500);	//wait added for the issue reported for the Integrations sub-menu not found.
		String strAuthdetails = objGetAuthKey.fn_GetAuthenticationKey(driver, webWait);
		String[] arrAuthDetails = strAuthdetails.split("#");
		String strClientNum = arrAuthDetails[0];
		String strAuthKey = arrAuthDetails[1];		

		//TODO: reinitialize the left nav.
		objPlansUsage.fn_clickIntegrations(driver, webWait).click();
		objPlansUsage.fn_clickAnalyticsReporting(driver, webWait).click();
		
        String strFullUrl = strAPIURL + "client_no=" + strClientNum + "&auth_key=" + strAuthKey + "&rest_call=" + strRestCall + "&output_format=json";        
        return strFullUrl;
	}
	
	
	public static String getFullUrl(String restCall,boolean isDev)
    {
		if(isDev)
		{
			String DEV_URL = "https://secure.future.stage.ariasystems.net/api/ws/api_ws_class_dispatcher.php?";
			String CLIENT_ID = "6000033";
			String AUTH_KEY = "fWSTQQB934p6AVh7Fnb5gm6wCcGyCCmM";
			String fullUrl = DEV_URL + "client_no=" +CLIENT_ID+ "&auth_key=" + AUTH_KEY + "&rest_call="+restCall;
			return fullUrl;
		}
		else
		{
			String DEV_URL = "https://secure.current.stage.ariasystems.net/api/ws/api_ws_class_dispatcher.php?";
			String CLIENT_ID = "3760701";
			String AUTH_KEY = "tx38GSY9gMPUSNUfJdH6nX474Y97NEYG";
			String fullUrl = DEV_URL + "client_no=" +CLIENT_ID+ "&auth_key=" + AUTH_KEY + "&rest_call="+restCall;
			return fullUrl;
		}

    }
	public static String getFullUrl(String restCall)
	{
		return getFullUrl(restCall,true);

	}
	
/*
	Author: Aashish Bhutkar
	Function fn_LoadAccountUsage: Takes in the details API URl, CSV File and date to load usage for the specified Account.
	Change done in Return Type from Void to ArrayList<String> so as to read the error code while loading the usage if any.
*/
		
    public ArrayList<String> fn_LoadAccountUsage(WebDriver driver, WebDriverWait webWait, String strAPIURL, String strFilePath, String strAcctNum, String strDate) throws Exception
    {
    	String strRestCall = "record_usage";
        String strFullUrl = fn_GetFullUrl(driver, webWait, strAPIURL, strRestCall);
        ArrayList<String> arrListResponses = new ArrayList();
        int index = 0;
      
        
        ApiHandler api = new ApiHandler();

        //TODO: Make POST API call (will also read in the CSV data).
        List<JSONObject> allResponses = api.makePostCall(strFullUrl, strFilePath, strRestCall, strAcctNum, strDate);

        //TODO: Verify JSON response to validate success.
        for(JSONObject response : allResponses)
        {
            int errorCode = Integer.parseInt(response.get("error_code").toString());

            if(errorCode !=0)
            {
                String errorMsg = response.get("error_msg").toString();
                arrListResponses.add(errorMsg);
                Log.error("ERROR: Loading record usage failed with message: "+errorMsg);
                Reporter.log("ERROR: Loading record usage failed with message: " + errorMsg);
            }
            else
            {
                Log.info("Loading record usage succeeded!");
            }
        }  
        
        return arrListResponses;
    }

    /*
        Input: File path to the usage data, account number to apply usage to.
        Output: This Method will load in usage data to a supplied account number. A Supplemental plan will need to be applied and
        assigned in order for any usage records to be loaded successfully.
     */

    public static void loadAccountUsage(String filePath, String acctNum, String date,boolean isDev) throws JSONException
    {
        String restCall = "record_usage";
        String fullUrl = getFullUrl(restCall,false);
        ApiHandler api = new ApiHandler();

        //TODO: Make POST API call (will also read in the CSV data)
        List<JSONObject> allResponses = api.makePostCall(fullUrl, filePath, restCall,acctNum,date);

        //TODO: Verify JSON response to vailidate success
        for(JSONObject response : allResponses)
        {
            int errorCode = Integer.parseInt(response.get("error_code").toString());

            if(errorCode !=0)
            {
                String errorMsg = response.get("error_msg").toString();
                System.out.println("Loading record usage failed with message: "+errorMsg);
            }
            else
            {
                System.out.println("Loading record usage succeeded!");
            }
        }

    }
	public static void loadAccountUsage(String filePath, String acctNum, String date) throws JSONException
	{
		loadAccountUsage(filePath,acctNum,date,true);
	}

	
    
    public static void assignSuppPlan(String acctNum,String planNum) throws Exception
    {
        ApiHandler api = new ApiHandler();
        String fullUrl = getFullUrl("assign_supp_plan");
        fullUrl = fullUrl+"&acct_no="+acctNum+"&supp_plan_no="+planNum+"&assignment_directive=2";
        api.makeSimplePostCall(fullUrl);
    }
    

	public static void updateAccount(String acctNum, String addlOptions) throws Exception
	{
		ApiHandler api = new ApiHandler();
		String fullUrl = getFullUrl("update_acct_complete");
		fullUrl = fullUrl + "&acct_no="+acctNum+"&"+addlOptions;
		api.makeSimplePostCall(fullUrl);

	}

    public static JSONObject createAccount(String filePath) throws Exception
    {
        ApiHandler api = new ApiHandler();
        JSONObject response = new JSONObject();

//        api.makePostCall(getFullUrl("create_acct_complete"),)
        return response;
    }

    

/*
	Author: Aashish Bhutkar
	Function fn_getLoadDataDate: Returns the date 5 days prior to current date in "yyyy-MM-dd" format for loading data.
	Outputs: formated date string to be used for loading data.    
*/
	 public String fn_getLoadDataDate() throws Exception
	 {	
			String PATTERN = "yyyy-MM-dd";
			SimpleDateFormat dateFormat = new SimpleDateFormat();
			dateFormat.applyPattern(PATTERN);
			//TODO: Get current date and then trace it back by 5 days for loading data.
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -5);
			String date1 = dateFormat.format(cal.getTime());
			return date1;
	 }
	 

 /*
	Author: Aashish Bhutkar
	Function fn_AdjustAcctBillDates: Returns Boolean if the Account Billing Date has been set.    
*/
	 
	 public Boolean fn_AdjustAcctBillDates(WebDriver driver, WebDriverWait webWait, String strAPIURL, String strAccountNum, String strTestCaseName) throws Exception
	 {
		 //TODO: Create objects to classes for page objects to access the items under it.
		 AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
		 NewAccountCreation objNewAcctCreate = new NewAccountCreation();
		 AccountFunctions objAccountFunc = new AccountFunctions();
		 ApiHandler objAPIHandler = new ApiHandler();	// for post method to be called
		 Utils objUtils = new Utils();
		 
		 Boolean blnAdjustDate = false;
		 String strSearchedAccounts = null;	//string for saving the returned accounts string by fn_VerifyParentChildAccounts.		 
		 String[] arrAccounts = null;
		 String strLoadDate = null;
		 String[] arrLoadDdate = null;
		 Integer intLoadMonth = 0;
		 Integer intLoadYear = 0;
		 String strModifiedPaidThruDate = null;
		 String strCurrentDate = null;		 
		 //String strFormatCurrentDate = null;
		 Long lngDaysBetween;
		 String strPostURL = null;
		 Integer intIterationNum = 0;
		 Integer intRemainderDays = 0;
		 
		 Log.info("Processing the Account Number '"+strAccountNum+"' to check if it's Billing Dates can be adjusted...");
		 strSearchedAccounts = objAcctFuncPlans.fn_VerifyParentChildAccounts(driver, webWait, strAccountNum, strTestCaseName);
		 if (strSearchedAccounts == null)
			 Log.info("The given account is not a child account, so good to go ahead with Adjusting Account Billing Dates..");
		 else
		 {
			arrAccounts = strSearchedAccounts.split("#");
			if(strAccountNum.contentEquals(arrAccounts[0].toString()))
				Log.info("The given account is for a parent account, hence good to go ahead with Adjusting Account Billing Dates..");
			else
				VerificationMethods.assertTrue(false, "ERROR: The given account number is for child, hence can't adjust it's Billing Dates !");			 
		 } 
		 
		 //TODO: Navigate now to the Account Billing Dates tab page to adjust the bill date.
		 driver.findElement(NewAccountCreation.fn_clickAccountsLeftNav()).click();
		 objNewAcctCreate.fn_clickAccountBillingDates(driver, webWait).click();
		 objNewAcctCreate.fn_getCurrentPaidThruDate(driver, webWait);
		 
		 strCurrentDate = objNewAcctCreate.fn_getCurrentPaidThruDate(driver, webWait).getAttribute("innerText").toString().trim();
		 strCurrentDate = objUtils.fn_getDateFormatted(strCurrentDate, "yyyy-mm-dd");
		
		 Log.info("The Current Paid Thru Date for the given account is: "+strCurrentDate);		 
		 
		 strLoadDate = objAccountFunc.fn_getLoadDataDate();
		 arrLoadDdate = strLoadDate.split("-");
		 intLoadMonth = Integer.parseInt(arrLoadDdate[1]);
		 intLoadYear = Integer.parseInt(arrLoadDdate[0]);
		 intLoadMonth = intLoadMonth + 1;	//modifying it for advance period invoice generation.
		 if(intLoadMonth == 13)	// if the load month was December, the next month would be January.
		 {
			 intLoadMonth = 1;
			 intLoadYear = intLoadYear + 1;
		 }
		 strModifiedPaidThruDate = intLoadYear+"-"+intLoadMonth+"-"+arrLoadDdate[2];
		 Log.info("The Data Load date for the account is: "+strLoadDate);
		 Log.info("The New Paid Through Date for the given account should be : "+strModifiedPaidThruDate);
		 
		 SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd");
		 Date dtCurrentDate = objSDF.parse(strCurrentDate);
		 Date dtNewPaidThruDate = objSDF.parse(strModifiedPaidThruDate);
		 
		 lngDaysBetween = Math.round((dtCurrentDate.getTime() - dtNewPaidThruDate.getTime()) / (double) 86400000);		 
		 String strFullURL = objAccountFunc.fn_GetFullUrl(driver, webWait, strAPIURL, "adjust_billing_dates");
		 if(lngDaysBetween > 0)	//since it is positive number, we will have to go backwards for date adjustment. 
		 {
			 Log.info("The days for which the adjustment has to be done backwards is: "+lngDaysBetween.toString());
			 if(lngDaysBetween > 27)
			 {
				 intIterationNum = Integer.valueOf(lngDaysBetween.intValue()) / 27;
				 intRemainderDays = Integer.valueOf(lngDaysBetween.intValue()) % 27;
				 
				 for(int i = 0; i < intIterationNum; i ++)// first iterate as many times possible for months. 
				 {
					 strPostURL = strFullURL +"&acct_no="+strAccountNum+"&action_directive=2"+"&adjustment_days=27";
					 Log.info("The Actual URL created based on Parameter Name & Value is: "+strPostURL);
					 objAPIHandler.makeSimplePostCall(strPostURL); 
				 }
				 //now the remainder days will be adjusted.
				 strPostURL = strFullURL +"&acct_no="+strAccountNum+"&action_directive=2"+"&adjustment_days="+intRemainderDays;
				 Log.info("The Actual URL created based on Parameter Name & Value is: "+strPostURL);
				 objAPIHandler.makeSimplePostCall(strPostURL); 
			 }
			 else if (lngDaysBetween <= 27)
			 {
				 intIterationNum = 0;
				 intRemainderDays = Integer.valueOf(lngDaysBetween.intValue());
				 
				 strPostURL = strFullURL +"&acct_no="+strAccountNum+"&action_directive=2"+"&adjustment_days="+intRemainderDays;
				 Log.info("The Actual URL created based on Parameter Name & Value is: "+strPostURL);
				 objAPIHandler.makeSimplePostCall(strPostURL);
			 }
			 else if (lngDaysBetween == 0)
			 {
				 Log.info("The Current Paid Thru Date is as required, no changes required !");
				 intIterationNum = 0;
				 intRemainderDays = 0;
			 }
			 
			 blnAdjustDate = true;			 
		 }
		 else if(lngDaysBetween < 0)	//since it is negative number, we will have to go forward for date adjustment.
		 {
			 lngDaysBetween = lngDaysBetween * (-1);
			 Log.info("The days for which the adjustment has to be done forwards is: "+lngDaysBetween.toString());			 
			 
			 if(lngDaysBetween > 27)
			 {
				 intIterationNum = Integer.valueOf(lngDaysBetween.intValue()) / 27;
				 intRemainderDays = Integer.valueOf(lngDaysBetween.intValue()) % 27;
				 
				 for(int i = 0; i < intIterationNum; i ++)// first iterate as many times possible for months. 
				 {
					 strPostURL = strFullURL +"&acct_no="+strAccountNum+"&action_directive=1"+"&adjustment_days=27";
					 Log.info("The Actual URL created based on Parameter Name & Value is: "+strPostURL);
					 objAPIHandler.makeSimplePostCall(strPostURL); 
				 }
				 //now the remainder days will be adjusted.
				 strPostURL = strFullURL +"&acct_no="+strAccountNum+"&action_directive=1"+"&adjustment_days="+intRemainderDays;
				 Log.info("The Actual URL created based on Parameter Name & Value is: "+strPostURL);
				 objAPIHandler.makeSimplePostCall(strPostURL); 

			 }
			 else if (lngDaysBetween <= 27)
			 {
				 intIterationNum = 0;
				 intRemainderDays = Integer.valueOf(lngDaysBetween.intValue());
				 
				 strPostURL = strFullURL +"&acct_no="+strAccountNum+"&action_directive=1"+"&adjustment_days="+intRemainderDays;
				 Log.info("The Actual URL created based on Parameter Name & Value is: "+strPostURL);
				 objAPIHandler.makeSimplePostCall(strPostURL); 

			 }
			 else if (lngDaysBetween == 0)
			 {
				 Log.info("The Current Paid Thru Date is as required, no changes required !");
				 intIterationNum = 0;
				 intRemainderDays = 0;
			 }

			 blnAdjustDate = true;
		 }
		 else
			 blnAdjustDate = true;

		 if (blnAdjustDate == true)
		 {
			 driver.findElement(NewAccountCreation.fn_clickAccountsLeftNav()).click();
			 objNewAcctCreate.fn_clickAccountOverview(driver, webWait);
			 objNewAcctCreate.fn_clickAccountOverview(driver, webWait).click();
			 objNewAcctCreate.fn_clickPasswordReset(driver, webWait);
			 objNewAcctCreate.fn_clickAccountBillingDates(driver, webWait).click();
			 objNewAcctCreate.fn_getCurrentPaidThruDate(driver, webWait);		 
			 strCurrentDate = objNewAcctCreate.fn_getCurrentPaidThruDate(driver, webWait).getAttribute("innerText").toString().trim();
			 Log.info("After Adjusting the Dates, the new Current Paid Thru Date now is: "+strCurrentDate);
			 
			 driver.findElement(NewAccountCreation.fn_clickAccountsLeftNav()).click();
			 return blnAdjustDate;
		 }

		 driver.findElement(NewAccountCreation.fn_clickAccountsLeftNav()).click();
		 return blnAdjustDate;		 
	 }
	 
	 
	 /*This Function assigns child account to parent account */
	 
	 public void fn_AssignChildToParent(WebDriver driver, WebDriverWait webWait, String strParentAcctNum, String strChildAcctNum) throws Exception
	 {
		 Cls_ChangeDeleteClientDefinedFieldActns objFields = new Cls_ChangeDeleteClientDefinedFieldActns();
		
		  objFields.AccountSearch(driver, webWait, strParentAcctNum);
	      List<WebElement> tabs = driver.findElements(By.className("accountBottomTab"));
	      WebElement manageTab = tabs.get(0);
	      for(WebElement tab : tabs)
	      {
	          Log.info("TAB: "+tab.getText());
	          if(tab.getText().equals("Manage Child Accounts"))
	          {
	              Log.info("Found Tab!");
	              manageTab = tab;
	              tab.click();
	              Thread.sleep(500);
	              tab.click();
	          }
	      }
	      
	      Thread.sleep(2000);
	       driver.findElement(AriaAccountPageObject.getAssignChildAcctLink()).click();
	       Thread.sleep(1000);
	       driver.findElement(AriaAccountPageObject.getChildAcctAssignField()).sendKeys(strChildAcctNum);
	       driver.findElement(AriaAccountPageObject.getParentPayBtn()).click();
	       driver.findElement(AriaAccountPageObject.getAssignChildAcctBtn()).click();
	       manageTab.click();
	       Thread.sleep(1000);
	       tabs = driver.findElements(By.className("accountBottomTab"));
	       for(WebElement tab : tabs)
	       {
	            Log.info("TAB: "+tab.getText());
	            if(tab.getText().equals("Manage Child Accounts"))
	            {
	                Log.info("Found Tab!");
	                manageTab = tab;
	                tab.click();
	                Thread.sleep(500);
	                tab.click();
	            }
	       }
	       
	       utility.VerificationMethods.verifyTrue(driver.findElement(AriaAccountPageObject.getUnassignLink()).isDisplayed(),"Child Account Assigned Successfully!");
	 }
	 
	 
/*
	Author: Aashish Bhutkar
	Function fn_getPreviousDate: Returns the date 'intBackDays' days prior to current date 
								 in "yyyy-MM-dd" format for any reference.
	Outputs: formated date string to be used for loading data.    
*/
	 public String fn_getPreviousDate(Integer intBackDays) throws Exception
	 {	
			String PATTERN = "yyyy-MM-dd";
			SimpleDateFormat dateFormat = new SimpleDateFormat();
			dateFormat.applyPattern(PATTERN);
			//TODO: Get current date and then trace it back by 5 days for loading data.
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -intBackDays);
			String date1 = dateFormat.format(cal.getTime());
			return date1;
	 }


/*
	Author: Aashish Bhutkar
	Function fn_getAccountDetails: Returns the List with Name value pair for the Account Details.
	
	Inputs: strAccountNum - Account Number for which information is to be captured.
			blnInformationType - true : Account Owner Information.
								 false: Billing Contact Information.
	
	Outputs: List Name Value Pair for the Account details.    
*/
	 public List<NameValuePair> fn_getAccountDetails(WebDriver driver, WebDriverWait webWait, 
			 							String strAccountNum, Boolean blnInformationType) throws Exception{
		 
    	//TODO: Create object for the classes to be used for accessing reusable codes.
    	Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
    	NewAccountCreation objNewAcctCreate = new NewAccountCreation();
		 
    	List<NameValuePair> lstpairAccountDetails = new ArrayList<NameValuePair>();
    	
    	String strXpathInfoType = null;

    	//decide which information is to be captured.
    	if(blnInformationType == true)
    		strXpathInfoType = "//td/a[text()='Account Owner']";
    	else
    		strXpathInfoType = "//td/a[text()='Billing Contact']";

    	//TODO: search the account for which the Information is to be captured & navigate to the requested info form.
    	objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
    	objNewAcctCreate.fn_clickPasswordReset(driver, webWait);
    	driver.findElement(By.xpath(strXpathInfoType)).click();
    	Thread.sleep(2000);
    	
    	//read the Field Sets available on the form and scan each for details.
    	List<WebElement> lstwebNumFieldSets = driver.findElements(By.xpath("//fieldset/dl"));
    	for(Integer intFS = 1; intFS < lstwebNumFieldSets.size()+1; intFS ++ ){
    		
    		String strFSXpath = "//fieldset["+intFS+"]/dl";
    		WebElement webFieldSet = driver.findElement(By.xpath(strFSXpath));

    		List<WebElement> lstLabels = webFieldSet.findElements(By.tagName("dt"));    		
    		for(Integer intList = 1; intList < lstLabels.size()+1; intList ++){

    			String strLabelName = lstLabels.get(intList-1).getText();
    			if(strLabelName.contains("Country") || strLabelName.contains("State / Province")){
    				
    				String strDDXpath = strFSXpath+"//dd["+intList+"]/select";
    				String strDropDownValue = driver.findElement(By.xpath(strDDXpath)).getAttribute("value").toString().trim();
    				String strOptionXpath = strDDXpath+"/option[@value='"+strDropDownValue+"']";
    				String strLabelValue = driver.findElement(By.xpath(strOptionXpath)).getText();
    				
        			lstpairAccountDetails.add(new BasicNameValuePair(strLabelName, strLabelValue));
    			}
    			else{
    				
    				String strDDXpath = strFSXpath+"//dd["+intList+"]/input";
    				String strLabelValue = driver.findElement(By.xpath(strDDXpath)).getAttribute("value").toString().trim();
    				
        			lstpairAccountDetails.add(new BasicNameValuePair(strLabelName, strLabelValue));
    			}    			
    		}
    	}
		 
		return lstpairAccountDetails;
	 }
}
