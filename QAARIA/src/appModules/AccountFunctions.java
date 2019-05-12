/*
 Author     		:	Joe Ganon, Aashish Bhutkar, Namrata Akarte.
 Class Name			: 	AccountFunctions 
 Purpose     		: 	Purpose of this file is :
						1. To perform various Account related functions.

 Date       		:	04/01/2016 
 Modified Date		:	06/28/2016	
 Modified By		: 	Aashish Bhutkar
 Version Information:	Version 2.0
 
 Version Changes 2.0:	1. Modified the logic for Immediate Invoice.
  
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import pageObjects.AccountPlansUsage;
import pageObjects.AccountsAccountOverview;
import pageObjects.AccountsPlans;
import pageObjects.AriaEOM;
import utility.ApiHandler;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		AriaEOM objAriaEOM = new AriaEOM();
		
		Thread.sleep(2500);	//wait added for the issue reported for the Integrations sub-menu not found.
		String strAuthdetails = objGetAuthKey.fn_GetAuthenticationKey(driver, webWait);
		String[] arrAuthDetails = strAuthdetails.split("#");
		String strClientNum = arrAuthDetails[0];
		String strAuthKey = arrAuthDetails[1];		

		//TODO: reinitialize the left nav.
		objPlansUsage.fn_clickIntegrations(driver, webWait).click();
		objAriaEOM.fn_clickAnalyticsReporting(driver, webWait).click();		
		
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
									Also needs now the SPI ID for the account to load usage to.
	The API to be used is : " record_usage_m ".
	Change done in Return Type from Void to ArrayList<String> so as to read the error code while loading the usage if any.
*/
		
    public ArrayList<String> fn_LoadAccountUsage(WebDriver driver, WebDriverWait webWait, String strAPIURL, String strFilePath
    		, String strAcctNum, String strDate, String strMPIID, String strSPIID) throws Exception {
    	String strRestCall = "record_usage_m";
        String strFullUrl = fn_GetFullUrl(driver, webWait, strAPIURL, strRestCall);
        List<JSONObject> allResponses = null;
/*
 * 		client_no=6000076&auth_key=7RKnupEJM8b3u9jx9tMqAF9uBvjBKvEg&rest_call=record_usage_m
 * 		&usage_units=52520.1998&acct_no=212335
 * 		&client_master_plan_instance_id=54947
 * 		&client_plan_instance_id=54948
 * 		&usage_type_code=GB_RAM
 * 		&usage_date=2016-04-08
 *         
 */
        strFullUrl = strFullUrl + "&client_master_plan_instance_id="+strMPIID + "&client_plan_instance_id="+strSPIID;
        
        ArrayList<String> arrListResponses = new ArrayList<String>();
        ApiHandler api = new ApiHandler();

        try {
        	
        	//TODO: Make POST API call (will also read in the CSV data).
            allResponses = api.makePostCall(strFullUrl, strFilePath, strRestCall, strAcctNum, strDate);
            if(allResponses.size() == 0) {
            	
            	arrListResponses.add("ERROR: Loading Record Usage is failing with strack trace as printed...");
            	return arrListResponses;
            }
        }
        catch (Exception exception) {
        	
        	exception.printStackTrace();
        	arrListResponses.add("ERROR: Loading Record Usage is failing with strack trace as printed...");
        	return arrListResponses;
        }         
        //TODO: Verify JSON response to validate success.
        for(JSONObject response : allResponses) {
        	
            int errorCode = Integer.parseInt(response.get("error_code").toString());

            if(errorCode !=0) {
            	
                String errorMsg = response.get("error_msg").toString();
                arrListResponses.add(errorMsg);
                Log.error("ERROR: Loading record usage failed with message: "+errorMsg);
                Reporter.log("ERROR: Loading record usage failed with message: " + errorMsg);
            }
            else
                Log.info("Loading record usage succeeded!");
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
	Function fn_AdjustAcctBillDates: Returns Boolean if the Account Billing Date has been set.
	
	Inputs: strAPIURL - API URL to be used for calling the API.
			strAccountNum - Account Number for which the Billing Dates are to be adjusted.
			strMPI - MPI name string to help get it's instance ID's.
			strInvoiceType - This string states how dates are to be adjusted;
				if strInvoiceType = 'Immediate', Bill Through Date should be Current Date.
				if strInvoiceType = 'Advanced', Bill Through Date should be Current Date + 27 days.
				if strInvoiceType = 'Pending', Bill Through Date should be value mentioned for 'strPendingInvDate'.
			strPendingInvDate - Will be used for generating the Pending Invoice; has to be in format 'yyyy-MM-dd'.
			
	Outputs: Boolean Value suggesting if the Date Asjustment was successfull or otherwise. 
*/
	 
	 public Boolean fn_AdjustAcctBillDates(WebDriver driver, WebDriverWait webWait, String strAPIURL, 
			 String strAccountNum, String strMPI, String strInvoiceType, String strPendingInvDate, String strTestCaseName) throws Exception {

		 //TODO: Create objects to classes for page objects to access the items under it.
		 Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
		 AriaEOM objAriaEOM = new AriaEOM();
		 AccountFunctions_Plans objAcctFuncPlans = new AccountFunctions_Plans();
		 AccountPlansUsage objAccountPlans = new AccountPlansUsage();
		 AccountFunctions objAccountFunc = new AccountFunctions();
		 ApiHandler objAPIHandler = new ApiHandler();	// for post method to be called
		 Utils objUtils = new Utils();
		 AccountsPlans objAcctPlans = new AccountsPlans();
		 
		 String strRestCall = "adjust_acct_plan_billing_dates_m";
		 Boolean blnAdjustDate = true;
		 String strLoadDate = null;
		 String[] arrLoadDdate = null;
		 Integer intLoadMonth = 0;
		 Integer intLoadYear = 0;
		 String strModifiedBillThruDate = "";
		 String strCurrentBillThruDate = "";		 
		 Long lngDaysBetween;
		 String strPostURL = null;
		 Integer intIterationNum = 0;
		 Integer intRemainderDays = 0;
		 List<JSONObject> apiResponse = null;
		 Boolean blnAPIRespVerified = false;
		 
		 //TODO: Navigate now to the Plans Page for Identifying the MPI for which the Billing Dates are to be adjusted.
		 String strMPIID = objAcctFuncPlans.fn_GetPlanInstanceID(driver, webWait, strAccountNum, strTestCaseName, strMPI);
		 String[] arrMPI = strMPIID.split("#");

		 //TODO: For the selected MPI, get it's Bill Through Date for analyzing the Date change to be done as per Invocie Type.
		 objAriaEOM.fn_clickAnalyticsReporting(driver, webWait).click();
		 objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
		 objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);
		 objAriaEOM.fn_clickAccountPlans(driver, webWait).click();
		 objAccountPlans.fn_clickPlansTab(driver, webWait).click();
		 objAriaEOM.fn_getDataTable(driver, webWait);
		 Thread.sleep(3000);
		 Utils.takeScreenshot(driver, strTestCaseName);
		 driver.findElement(By.linkText(arrMPI[0])).click();	// clicking the Plan Instance ID for MPI details.
		 objAcctPlans.fn_clickMakePayment(driver, webWait);
		 String strSpan = objAcctPlans.fn_getBillThroughDate(driver, webWait).getAttribute("innerText").toString().trim();
		 strCurrentBillThruDate = strSpan.substring(strSpan.indexOf("Bill Through Date\n")+18, strSpan.indexOf("\nStatus"));
		 strCurrentBillThruDate = objUtils.fn_getDateFormatted(strCurrentBillThruDate, "yyyy-mm-dd");		
		 Log.info("The Current Paid Thru Date for the given account is: "+strCurrentBillThruDate);		 
		 
		 //TODO: Decide the modified Bill Through Date on basis of Invoice to be generated. 
		 strLoadDate = objUtils.fn_getLoadDataDate();		 
		 if(strInvoiceType.contentEquals("Immediate"))
			 strModifiedBillThruDate = objUtils.fn_getPreviousDate(1, "yyyy-MM-dd");
		 else if (strInvoiceType.contentEquals("Pending"))
			 strModifiedBillThruDate = strPendingInvDate;
		 else if (strInvoiceType.contentEquals("Advanced")) {
			
			 arrLoadDdate = strLoadDate.split("-");
			 intLoadMonth = Integer.parseInt(arrLoadDdate[1]);
			 intLoadYear = Integer.parseInt(arrLoadDdate[0]);
			 intLoadMonth = intLoadMonth + 1;
			 if(intLoadMonth == 13) {	// if the load month was December, the next month would be January followed by next year

				 intLoadMonth = 1;
				 intLoadYear = intLoadYear + 1;
			 }
			 strModifiedBillThruDate = intLoadYear+"-"+intLoadMonth+"-"+arrLoadDdate[2];
		 }
		 if(strModifiedBillThruDate.equals(""))
			 VerificationMethods.assertTrue(false, "ERROR: Since Incorrect Invoice Type was mentioned, Billing date couldn't be adjusted; exiting with exception !");
		 Log.info("The Data Load date for the account is: "+strLoadDate);
		 Log.info("The New Paid Through Date for the given account should be : "+strModifiedBillThruDate);
		 
		 //TODO: Based on the Current / Modified Bill Through dates, call the API for date adjustments.
		 SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd");
		 Date dtCurrentDate = objSDF.parse(strCurrentBillThruDate);
		 Date dtNewPaidThruDate = objSDF.parse(strModifiedBillThruDate);		 
		 lngDaysBetween = Math.round((dtCurrentDate.getTime() - dtNewPaidThruDate.getTime()) / (double) 86400000);		 
		 String strFullURL = objAccountFunc.fn_GetFullUrl(driver, webWait, strAPIURL, strRestCall);
		 if(lngDaysBetween > 0) {	//since it is positive number, we will have to go backwards for date adjustment.

			 Log.info("The days for which the adjustment has to be done backwards is: "+lngDaysBetween.toString());
			 if(lngDaysBetween > 27) {

				 intIterationNum = Integer.valueOf(lngDaysBetween.intValue()) / 27;
				 intRemainderDays = Integer.valueOf(lngDaysBetween.intValue()) % 27;
				 
				 for(int i = 0; i < intIterationNum; i ++) {	// first iterate as many times possible for months. 

					 strPostURL = strFullURL +"&acct_no="+strAccountNum+"&client_master_plan_instance_id="+arrMPI[1]+"&action_directive=2"+"&adjustment_days=27";
					 Log.info("The Actual URL created based on Parameter Name & Value is: "+strPostURL);
					 apiResponse = objAPIHandler.makeSimplePostCall(strPostURL);
					 blnAPIRespVerified = fn_VerifyAPIRepsonse(apiResponse);
					 if(blnAPIRespVerified == false && blnAdjustDate == true)
						 blnAdjustDate = false;
				 }
				 //now the remainder days will be adjusted.
				 strPostURL = strFullURL +"&acct_no="+strAccountNum+"&client_master_plan_instance_id="+arrMPI[1]+"&action_directive=2"+"&adjustment_days="+intRemainderDays;
				 Log.info("The Actual URL created based on Parameter Name & Value is: "+strPostURL);
				 apiResponse = objAPIHandler.makeSimplePostCall(strPostURL);
				 blnAPIRespVerified = fn_VerifyAPIRepsonse(apiResponse);
				 if(blnAPIRespVerified == false && blnAdjustDate == true)
					 blnAdjustDate = false; 
			 }
			 else if (lngDaysBetween <= 27) {

				 intIterationNum = 0;
				 intRemainderDays = Integer.valueOf(lngDaysBetween.intValue());
				 
				 strPostURL = strFullURL +"&acct_no="+strAccountNum+"&client_master_plan_instance_id="+arrMPI[1]+"&action_directive=2"+"&adjustment_days="+intRemainderDays;
				 Log.info("The Actual URL created based on Parameter Name & Value is: "+strPostURL);
				 apiResponse = objAPIHandler.makeSimplePostCall(strPostURL);
				 blnAPIRespVerified = fn_VerifyAPIRepsonse(apiResponse);
				 if(blnAPIRespVerified == false && blnAdjustDate == true)
					 blnAdjustDate = false;
			 }
			 else if (lngDaysBetween == 0) {

				 Log.info("The Current Paid Thru Date is as required, no changes required !");
				 intIterationNum = 0;
				 intRemainderDays = 0;
			 }		 
		 }
		 else if(lngDaysBetween < 0) {	//since it is negative number, we will have to go forward for date adjustment.

			 lngDaysBetween = lngDaysBetween * (-1);
			 Log.info("The days for which the adjustment has to be done forwards is: "+lngDaysBetween.toString());			 
			 
			 if(lngDaysBetween > 27) {

				 intIterationNum = Integer.valueOf(lngDaysBetween.intValue()) / 27;
				 intRemainderDays = Integer.valueOf(lngDaysBetween.intValue()) % 27;
				 
				 for(int i = 0; i < intIterationNum; i ++) {	// first iterate as many times possible for months. 

					 strPostURL = strFullURL +"&acct_no="+strAccountNum+"&client_master_plan_instance_id="+arrMPI[1]+"&action_directive=1"+"&adjustment_days=27";
					 Log.info("The Actual URL created based on Parameter Name & Value is: "+strPostURL);
					 objAPIHandler.makeSimplePostCall(strPostURL); 
				 }
				 //now the remainder days will be adjusted.
				 strPostURL = strFullURL +"&acct_no="+strAccountNum+"&client_master_plan_instance_id="+arrMPI[1]+"&action_directive=1"+"&adjustment_days="+intRemainderDays;
				 Log.info("The Actual URL created based on Parameter Name & Value is: "+strPostURL);
				 apiResponse = objAPIHandler.makeSimplePostCall(strPostURL);
				 blnAPIRespVerified = fn_VerifyAPIRepsonse(apiResponse);
				 if(blnAPIRespVerified == false && blnAdjustDate == true)
					 blnAdjustDate = false;

			 }
			 else if (lngDaysBetween <= 27) {

				 intIterationNum = 0;
				 intRemainderDays = Integer.valueOf(lngDaysBetween.intValue());
				 
				 strPostURL = strFullURL +"&acct_no="+strAccountNum+"&client_master_plan_instance_id="+arrMPI[1]+"&action_directive=1"+"&adjustment_days="+intRemainderDays;
				 Log.info("The Actual URL created based on Parameter Name & Value is: "+strPostURL);
				 apiResponse = objAPIHandler.makeSimplePostCall(strPostURL);
				 blnAPIRespVerified = fn_VerifyAPIRepsonse(apiResponse);
				 if(blnAPIRespVerified == false && blnAdjustDate == true)
					 blnAdjustDate = false; 

			 }
			 else if (lngDaysBetween == 0) {

				 Log.info("The Current Paid Thru Date is as required, no changes required !");
				 intIterationNum = 0;
				 intRemainderDays = 0;
			 }
		 }
		 
		 //TODO: If the Bills Adjustment is flagged successful via API, do the UI validation for it.
		 if (blnAdjustDate == true) {

			 objAriaEOM.fn_clickAnalyticsReporting(driver, webWait).click();
			 objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
			 objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);
			 objAriaEOM.fn_clickAccountPlans(driver, webWait).click();
			 objAccountPlans.fn_clickPlansTab(driver, webWait).click();
			 objAriaEOM.fn_getDataTable(driver, webWait);
			 Thread.sleep(3000);
			 driver.findElement(By.linkText(arrMPI[0])).click();	// clicking the Plan Instance ID for MPI details.
			 objAcctPlans.fn_clickMakePayment(driver, webWait); 
			 strSpan = objAcctPlans.fn_getBillThroughDate(driver, webWait).getAttribute("innerText").toString().trim();
			 strCurrentBillThruDate = strSpan.substring(strSpan.indexOf("Bill Through Date\n")+18, strSpan.indexOf("\nStatus"));
			 Log.info("After Adjusting the Dates, the new Current Paid Thru Date now is: "+strCurrentBillThruDate);
			 
			 objAriaEOM.fn_clickAccounts(driver, webWait).click();
			 return blnAdjustDate;
		 }

		 objAriaEOM.fn_clickAccounts(driver, webWait).click();
		 return blnAdjustDate;		 
	 }
	 
/*
 *	Function: fn_VerifyAPIRepsonse - This function defines whether the API call was successfull or not.
 *
 *	Input:  apiResponse: A List<JSONObject> which is returned as part of the API call.
 *
 *	Output:  blnAPIRespVerified: Boolean value for the status of the API response after reading it.
 * 	 
*/	 
	 public Boolean fn_VerifyAPIRepsonse(List<JSONObject> apiResponse) throws Exception {
		 
		 Boolean blnAPIRespVerified = false;	 
			
		 //TODO: Verify JSON response to validate success.
         for(JSONObject response : apiResponse) {
         	
         	int errorCode = Integer.parseInt(response.get("error_code").toString());	
             if(errorCode != 0) {
             	
                 String errorMsg = response.get("error_msg").toString();
                 Log.error("ERROR: API call failed with message: "+errorMsg);
                 Reporter.log("ERROR: API call failed with message: " + errorMsg);
                 
                 blnAPIRespVerified = false;
              }
              else 
            	  blnAPIRespVerified = true;
          }
		 
		 return blnAPIRespVerified;
	 }

/*
	Author: Nashim Khan
	Function fn_getBillingGroupDetails: Returns the List with Name value pair for the Account Details.
		
	Inputs: strBilling AccountNum -  Billing Account Number for which information is to be captured.			
				strMPIID - MPI Plan Instance ID for which Billing Information is to be collected.
		
	Outputs: List Name Value Pair for the Account details.    
*/
	 public List<NameValuePair> fn_getBillingGroupDetails(WebDriver driver, WebDriverWait webWait, 
				 							String strAccountNum, String strMPIID) throws Exception {
		
    	//TODO: Create object for the classes to be used for accessing reusable codes.	
		AriaEOM objAriaEOM = new AriaEOM();		
    	Cls_ChangeDeleteClientDefinedFieldActns objChangeDelClientFd = new Cls_ChangeDeleteClientDefinedFieldActns();    	
    	AccountsPlans objAcctPlans=new AccountsPlans();
    	
    	List<NameValuePair> lstBillingDetails = new ArrayList<NameValuePair>();

    	//TODO: Search the Account and start collecting the Billing Contact Information.
    	objChangeDelClientFd.AccountSearch(driver, webWait, strAccountNum);
    	objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);
    	objAriaEOM.fn_clickAccountPlans(driver, webWait).click();

    	//TODO: Fetch the Billing Group ID for the MPI specified and proceed for gathering information. 
    	objAcctPlans.fn_clickPlansMPI(driver, webWait, strMPIID).click();
    	Thread.sleep(3000);
    	String strBillingGroupId=objAcctPlans.fn_getBillingGroupId(driver, webWait).getText().toString().trim();    	
    	objAcctPlans.fn_clickBillingGroupsTab(driver, webWait).click();    	
    	objAcctPlans.fn_clickBillingGroupID(driver, webWait, strBillingGroupId).click();    	
	
        //TODO :Get Billing Information	    	    	  	
    	//TODO : Read the  Billing table  Details     	
    	WebElement webBillDetailTable = driver.findElement(By.xpath("//form[@id='reassignForm']/table[1]/tbody"));     	 
    	List<WebElement> lstwebBillDtlsHeader = webBillDetailTable.findElements(By.xpath("tr/th"));
    	List<WebElement> lstwebBillDtlsValues= webBillDetailTable.findElements(By.xpath("tr/td"));  	 
 	    for(Integer intList = 0; intList < lstwebBillDtlsHeader.size(); intList++) {
 	    	
 	    	String strHeader = lstwebBillDtlsHeader.get(intList).getText().toString().trim();
 	    	String strValue = null;    		   
 	    	//TODO: Read the InnerHTML to know what Tag we are dealing with for the Value to be read.
 	    	String strHTMLTagValue = lstwebBillDtlsValues.get(intList).getAttribute("innerHTML").toString().trim();
 	    	if(strHTMLTagValue.contains("<") && strHTMLTagValue.contains(">")) {
 	    		String strActualTagName = strHTMLTagValue.substring(strHTMLTagValue.indexOf("<")+1, strHTMLTagValue.indexOf(" "));
 	    		strValue = lstwebBillDtlsValues.get(intList).findElement(By.tagName(strActualTagName)).getAttribute("value").toString().trim();    			   
 	    	}
 	    	else
 	    		strValue = strHTMLTagValue;
 	    	
 	    	//TODO : Create List now for Header Pair with its Value.
 	    	lstBillingDetails.add(new BasicNameValuePair(strHeader, strValue));  
 	    }    	

 	    //TODO : Read the  Notification  table  Details
        WebElement webNotifDetailTable = driver.findElement(By.xpath("//form[@id='reassignForm']/table[2]/tbody"));     	  
     	List<WebElement> lstwebNotifDtlTblHeader = webNotifDetailTable.findElements(By.xpath("tr/th"));
    	List<WebElement> lstwebNotifDtlsValues= webNotifDetailTable.findElements(By.xpath("tr/td"));
    	for(Integer intList = 0; intList < lstwebNotifDtlTblHeader.size(); intList++) {	    	

    		String strNotifHeader= lstwebNotifDtlTblHeader.get(intList).getText().toString().trim();
    		String strNotifValue = null;
    		String strActualTagName=null;
    		Integer intValueList = 0;
    		
    		//TODO: Read the InnerHTML to know what Tag we are dealing with for the Value to be read.
    		String strNotifHTMLTagValue = lstwebNotifDtlsValues.get(intList).getAttribute("innerHTML").toString().trim();
    		strNotifHTMLTagValue = strNotifHTMLTagValue.replaceAll("\"", "");
    		strNotifHTMLTagValue = strNotifHTMLTagValue.replaceAll("<br>\n", "");
    		strNotifHTMLTagValue = strNotifHTMLTagValue.replaceAll("\\s+<", "<");    		
    		if(strNotifHTMLTagValue.contains("<") && strNotifHTMLTagValue.contains(">")) {   		
    			
    			strActualTagName = strNotifHTMLTagValue.substring(strNotifHTMLTagValue.indexOf("<")+1, strNotifHTMLTagValue.indexOf(" "));    				
    			if(strActualTagName.contentEquals("input") && strNotifHTMLTagValue.contains("type=radio")){
    				String strRadioBtnXpath = "//input[@value='0' and @type='radio']";    				
    				String strRadioOption = driver.findElement(By.xpath(strRadioBtnXpath)).getAttribute("defaultChecked").toString().trim();
    				if(strRadioOption.contentEquals("true")){
    					strRadioBtnXpath = "//input[@value='0' and @type='radio']/following::label[1]";    					
    					strNotifValue = driver.findElement(By.xpath(strRadioBtnXpath)).getText().trim();
    				}
    				strRadioBtnXpath = "//input[@value='1' and @type='radio']/following::label[1]";    								
    				strNotifValue = driver.findElement(By.xpath(strRadioBtnXpath)).getText().trim();
    			} 
    			else if(strActualTagName.contains("select")){
    				intValueList ++;        			
        			String strDropDownValue = lstwebNotifDtlsValues.get(intList).findElement(By.tagName(strActualTagName)).getAttribute("value").toString().trim();    			
    				String strOptionXpath = "//select/option[@value='"+strDropDownValue+"']";
    				strNotifValue = driver.findElement(By.xpath(strOptionXpath)).getText();
    			}
    			else {   
    				strNotifValue = lstwebNotifDtlsValues.get(intList).findElement(By.tagName(strActualTagName)).getAttribute("value").toString().trim();
    			}    			
    		} 		
    		
    		//TODO : Create List now for Header Pair with its Value.
    		lstBillingDetails.add(new BasicNameValuePair(strNotifHeader, strNotifValue));
        }
    	
    	// TODO : Get  Header Of Payment Option.
    	WebElement webPayOptHeaderVal=driver.findElement(By.xpath("//div[@id='paymentOption']/b"));
    	String strPaymentOptHeader= webPayOptHeaderVal.getText().toString().trim();
    	// TODO : Get Detail  Of Selected Payment Option.
    	String strNotiValue =null;
    	String strPaymentOptionsXpath=null; 
    	WebElement webPayOptionXpath=driver.findElement(By.xpath("//div[@id='paymentOption']"));
    	String strPayOptHTMLTagValue = webPayOptionXpath.getAttribute("innerHTML").toString().trim();
    	strPayOptHTMLTagValue = strPayOptHTMLTagValue.replaceAll("\"", "");
    	strPayOptHTMLTagValue = strPayOptHTMLTagValue.replaceAll("<label>\n", "");
    	strPayOptHTMLTagValue = strPayOptHTMLTagValue.replaceAll("\\s+<", "<");        		
    	if(strPayOptHTMLTagValue.contains("<") && strPayOptHTMLTagValue.contains(">")) {
    		
    		String strActualTagName = strPayOptHTMLTagValue.substring(strPayOptHTMLTagValue.indexOf("<")+1, strPayOptHTMLTagValue.indexOf(" "));        			
    		if(strActualTagName.contentEquals("input") && strPayOptHTMLTagValue.contains("type=radio"))	{
    			strPaymentOptionsXpath = "//div[@id='paymentOption']/input[@type='radio' and @value='1']";    				
    			String strRadioOption = driver.findElement(By.xpath(strPaymentOptionsXpath)).getAttribute("defaultChecked").toString().trim();
    			if(strRadioOption.contentEquals("true")) { 	
    				
    				strPaymentOptionsXpath ="//div[@id='paymentOption']/input[@type='radio' and @value='1']/following::label[1]";    	    					
    				strNotiValue = driver.findElement(By.xpath(strPaymentOptionsXpath)).getText().trim();   			
    			}
    		}
    		else {	
    		
    			strPaymentOptionsXpath ="//div[@id='paymentOption']/input[@type='radio' and @value='2']/following::label[1]";    	    				  				
    			strNotiValue = driver.findElement(By.xpath(strPaymentOptionsXpath)).getText().trim();
    		}        			
    		
    		//TODO : Create List now for Header Pair with its Value.
    		lstBillingDetails.add(new BasicNameValuePair(strPaymentOptHeader, strNotiValue));    	
    	}
    	
    	//TODO : Account  Credential table Details  pair values. 
    	String strAcctCredsTabXPath="//th[text()='Statement Contact']";
    	String strStmntHeaderValue = driver.findElement(By.xpath(strAcctCredsTabXPath)).getText().toString().trim();            
    	Select selStmntContacts= new Select(driver.findElement(By.id("inStatementContacts")));    	            		    	
    	List<WebElement> lstwebOptions= selStmntContacts.getAllSelectedOptions();
    	for (WebElement webOption : lstwebOptions) {
    		
    		String selOptionValue = webOption.getText();    	            	  	
    		lstBillingDetails.add(new BasicNameValuePair(strStmntHeaderValue, selOptionValue));      		
    	}
    	
    	//TODO: Based on the Payment Options, the following code will read those details.
    	try {
    		
    		// TODO : Get  Terms Of Payment Option
    	    //TODO : Get Terms Header Values
        	WebElement webTermOptHeaderVal=driver.findElement(By.xpath("//div[@id='NetTerm']/b"));
        	String strTermHeader= webTermOptHeaderVal.getText().toString().trim();       	
        	//TODO : Get Terms Option Values 
        	String strTermsValue =null;        	
        	WebElement webPayTermXpath=driver.findElement(By.xpath("//div[@id='NetTerm']/table/tbody"));
        	List<WebElement> lstwebPayTermsDtlsValues= webPayTermXpath.findElements(By.xpath("tr/td")); 
        	for(Integer intList = 0; intList < lstwebPayTermsDtlsValues.size(); intList++) {
        		
         		String strTermOptHTMLTagValue = lstwebPayTermsDtlsValues.get(intList).findElement(By.tagName("input"))
         														.getAttribute("defaultChecked").toString().trim(); 	
    			
    			if(strTermOptHTMLTagValue.contentEquals("true")) {        				
    				
    				strTermsValue = lstwebPayTermsDtlsValues.get(intList).findElement(By.tagName("span")).getAttribute("innerText").trim();        				
    				lstBillingDetails.add(new BasicNameValuePair(strTermHeader, strTermsValue));
    			}  
        	}
        	
    	} catch(Exception e) {
			
			Log.info("NetTerms option is Not Selected ");			
		}
    	
    	try {
    		// TODO : Get  Terms Of Payment Method 
      	    //TODO : Get Payment Method  Details Header Values
          	WebElement webPriPayOptHeaderVal=driver.findElement(By.xpath("//div[@id='paymentOption']/label[1]"));
          	String strTermHeader= webPriPayOptHeaderVal.getText().toString().trim();
          	//TODO : Get Payment Method  Option Values 
          	String strTermsValue =null;        	
          	WebElement webPayTermXPath=driver.findElement(By.xpath("//div[@id='mainPayMethods']/div/table/tbody"));
          	List<WebElement> lstwebPayTermsDtlsValues= webPayTermXPath.findElements(By.xpath("tr/td")); 
          	for(Integer intList = 0; intList < lstwebPayTermsDtlsValues.size(); intList++) {

           		strTermsValue = lstwebPayTermsDtlsValues.get(intList).findElement(By.tagName("select"))
           														.getAttribute("Value").toString().trim();   			
   				lstBillingDetails.add(new BasicNameValuePair(strTermHeader, strTermsValue));      			
          	}
    	} catch (Exception e) {
		
    		Log.info("Method  is Not Selected ");
    	}    	 

    	try {
    		
    		//TODO : Read the  Primary Payment Account credential  table  Details       	
    	    WebElement webAcctCredentialTable = driver.findElement(By.xpath("//div[@id='primaryPayMethod']/div/table/tbody"));     	  
    	    List<WebElement> lstwebPrimaryAcctDtlHeader = webAcctCredentialTable.findElements(By.xpath("tr/th"));
    	    List<WebElement> lstwebPrimAcctDtlValue= webAcctCredentialTable.findElements(By.xpath("tr/td"));
	    	Integer intValueList = 0;
	    	for(Integer intList = 0; intList < lstwebPrimaryAcctDtlHeader.size(); intList++) {

	    		String strAcctHeader= "PriPay_"+lstwebPrimaryAcctDtlHeader.get(intList).getAttribute("innerHTML").toString().trim();   		
	    		String strAcctValue = null;
	    		String strActualTagName = null;    		
	    		//TODO: Read the InnerHTML to know what Tag we are dealing with for the Value to be read.
	    		String strAcctHTMLTagValue = lstwebPrimAcctDtlValue.get(intValueList).getAttribute("innerHTML").toString().trim();
	    		strAcctHTMLTagValue = strAcctHTMLTagValue.replaceAll("\"", "");     	    		
	    		if(strAcctHTMLTagValue.contains("<") && strAcctHTMLTagValue.contains(">") ){   	    		
	    			
	    			strActualTagName = strAcctHTMLTagValue.substring(strAcctHTMLTagValue.indexOf("<")+1, strAcctHTMLTagValue.indexOf(" "));    	    			
	    			strAcctValue = lstwebPrimAcctDtlValue.get(intValueList).findElement(By.tagName(strActualTagName)).getAttribute("value").toString().trim();    	    			
	    			intValueList ++;
	    		}    	    		
	    		else {
	    			
	    			strAcctValue = strAcctHTMLTagValue;
	    			intValueList ++;
	    		}    
	    		
	    		//TODO : Header Pair  With Header, Value and Logs.
	    		lstBillingDetails.add(new BasicNameValuePair(strAcctHeader, strAcctValue)); 
	      	}
    	} catch(Exception e){
			
    		Log.info(" Primary Payment Method  is Not Selected ! ");    			
    	}    		

    	try {
			
    		//TODO : Read the  Secondary Payment  Account credential  table  Details       	
	    	WebElement webAcctCredentialTable = driver.findElement(By.xpath("//div[@id='secondaryPayMethod']/div/table/tbody"));     	  
	     	List<WebElement> lstwebSecAcctDtlHeader = webAcctCredentialTable.findElements(By.xpath("tr/th"));
	    	List<WebElement> lstwebSecAcctDtlValue= webAcctCredentialTable.findElements(By.xpath("tr/td"));
	    	Integer intValueList = 0;
	    	for(Integer intList = 0; intList < lstwebSecAcctDtlHeader.size(); intList++) {
	    	    	   				 
	    		String strAcctHeader= "SecPay_"+lstwebSecAcctDtlHeader.get(intList).getAttribute("innerHTML").toString().trim();   		
	    		String strAcctValue = null;
	    		String strActualTagName = null;    		
	    		//TODO: Read the InnerHTML to know what Tag we are dealing with for the Value to be read.
	    		String strAcctHTMLTagValue = lstwebSecAcctDtlValue.get(intValueList).getAttribute("innerHTML").toString().trim();
	    		strAcctHTMLTagValue = strAcctHTMLTagValue.replaceAll("\"", "");     	    	
	    		if(strAcctHTMLTagValue.contains("<") && strAcctHTMLTagValue.contains(">")) {    	    		
	    			
	    			strActualTagName = strAcctHTMLTagValue.substring(strAcctHTMLTagValue.indexOf("<")+1, strAcctHTMLTagValue.indexOf(" "));    	    			
	    			strAcctValue = lstwebSecAcctDtlValue.get(intValueList).findElement(By.tagName(strActualTagName)).getAttribute("value").toString().trim();    	    			
	    			intValueList ++;
	    		}    	    		
	    		else {    	    			
	    		
	    			strAcctValue = strAcctHTMLTagValue;
	    			intValueList ++;
	    		}    
	    		//TODO : Header Pair With Value and Logs
	    		lstBillingDetails.add(new BasicNameValuePair(strAcctHeader, strAcctValue));      	    		
  	      	}
    	} catch(Exception e){
			
    		Log.info(" Secondary  Payment Method  is Not Selected! ");
   		}
    	
    	//TODO : Read the  Account credential  table  Details        	
    	WebElement webAcctCredentialTable = driver.findElement(By.xpath("//div[@id='Statement-contact-details-div']/table/tbody"));     	  
     	List<WebElement> lstwebAcctDtlHeader = webAcctCredentialTable.findElements(By.xpath("tr/th"));
    	List<WebElement> lstwebStmntAcctDtlValue= webAcctCredentialTable.findElements(By.xpath("tr/td"));
    	Integer intValueList = 0;
    	for(Integer intList = 0; intList < lstwebAcctDtlHeader.size(); intList++) {

    		String strAcctHeader= "Stmnt_"+lstwebAcctDtlHeader.get(intList).getText().toString().trim();   		
    		String strAcctValue = null;
    		String strActualTagName = null;    		
    		//TODO: Read the InnerHTML to know what Tag we are dealing with for the Value to be read.
    		String strAcctHTMLTagValue = lstwebStmntAcctDtlValue.get(intValueList).getAttribute("innerHTML").toString().trim();
    		strAcctHTMLTagValue = strAcctHTMLTagValue.replaceAll("\"", "");    
    		if(strAcctHTMLTagValue.contains("<") && strAcctHTMLTagValue.contains(">") && !strAcctHTMLTagValue.contains("type=hidden")
    				&& !strAcctHTMLTagValue.contains("Country")) {
    			
    			strActualTagName = strAcctHTMLTagValue.substring(strAcctHTMLTagValue.indexOf("<")+1, strAcctHTMLTagValue.indexOf(" "));    			
    			strAcctValue = lstwebStmntAcctDtlValue.get(intValueList).findElement(By.tagName(strActualTagName)).getAttribute("value").toString().trim();    			
    			intValueList ++;
    		}//TODO : Read the Hidden fields to know what tag is hidden
    		else if(strAcctHTMLTagValue.contains("type=hidden")) {    			
    		
    			intValueList ++;
    			strActualTagName = strAcctHTMLTagValue.substring(strAcctHTMLTagValue.indexOf("<")+1, strAcctHTMLTagValue.indexOf(" "));
    			strAcctValue = lstwebStmntAcctDtlValue.get(intValueList).findElement(By.tagName(strActualTagName)).getAttribute("value").toString().trim();
    			intValueList ++;
    		}
    		else if( strAcctHTMLTagValue.contains("selected=selected" )) {

    			intValueList ++;
    			String strAcctCountryXPath ="//div[@id='Statement-contact-details-div']/table/tbody/tr/td/select";    			
    			String strDropDownValue = driver.findElement(By.xpath(strAcctCountryXPath)).getAttribute("type").toString().trim();    			
				String strOptionXPath = strAcctCountryXPath+"/option[@value='"+strDropDownValue+"']";
				strAcctValue = driver.findElement(By.xpath(strOptionXPath)).getText();
    		}    		
    		else {    			
    		
    			strAcctValue = strAcctHTMLTagValue;
    			intValueList ++;
    		}
    		
    		//TODO : Header Pair With Value and Logs
    		lstBillingDetails.add(new BasicNameValuePair(strAcctHeader, strAcctValue));  	    	   	     
      	} 
    	
    	Log.info("Billing information"+lstBillingDetails);  	
      	return lstBillingDetails;
	 }	     
	    
/*
		Author: Nashim Khan
		Function fn_getAccountContactDetails: Returns the List with Name value pair for the Account Details.
		
		Inputs: strBilling AccountNum -  Billing Account Number for which information is to be captured.
		
		Outputs: List Name Value Pair for the Account Contact details.
*/
		 public List<NameValuePair> fn_getAccountContactDetails(WebDriver driver, WebDriverWait webWait, 
				 							String strAccountNum) throws Exception {
			 
     		 //TODO: Create object for the classes to be used for accessing reusable codes.	
			 AccountsAccountOverview objAccountOverview=new AccountsAccountOverview();
			 AriaEOM objAriaEOM = new AriaEOM();		
			 Cls_ChangeDeleteClientDefinedFieldActns objChangeDelClientFd = new Cls_ChangeDeleteClientDefinedFieldActns(); 
			 
			 List<NameValuePair> lstAccountOverviewDetails = new ArrayList<NameValuePair>();		 
 
			 //TODO : Search Account		
			 objChangeDelClientFd.AccountSearch(driver, webWait, strAccountNum);
			 
			 //TODO : Click on Account OverView 
			 objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);     	
			 objAccountOverview.fn_ClickonAcountContactLink(driver, webWait).click();
			 objAccountOverview.fn_AccountcontactTableLoad(driver, webWait);
		    
			 //TODO : Read the  Account credential table  Details 	
			 WebElement webAcctContactTable = driver.findElement(By.xpath("//div[@id='accountsSectionBottomContainer']/div/div/form/div[1]/table/tbody"));     	  
			 List<WebElement> lstwebAcctContactHeader = webAcctContactTable.findElements(By.xpath("tr/th"));
			 List<WebElement> lstwebAcctContactValue= webAcctContactTable.findElements(By.xpath("tr/td"));
		    
			 List<String> lstAcctOvwHeaderText = new ArrayList<String>();
			 for(Integer intlist=0;intlist< lstwebAcctContactHeader.size();intlist++) {

				 String strAcctContactTabHeader=null;
				 strAcctContactTabHeader = lstwebAcctContactHeader.get(intlist).getText().toString().trim();			   
				 if(!strAcctContactTabHeader.contentEquals(""))					 
		    		lstAcctOvwHeaderText.add(strAcctContactTabHeader);		
			 }
		    
			 //TODO: Read the InnerHTML to know what Tag we are dealing with for the Value to be read.	    
			 List<String> lstAcctContactValues = new ArrayList<String>();		   
			 String strAcctContactActualTagName=null;
			 String strAcctContactHTMLTagValue=null;
			 String strAccContactVal=null;	
			 for(Integer intlistVal=0; intlistVal< lstwebAcctContactValue.size();intlistVal++) {
		    	
			     strAcctContactHTMLTagValue = lstwebAcctContactValue.get(intlistVal).getAttribute("innerHTML").toString().trim();
			     strAcctContactHTMLTagValue = strAcctContactHTMLTagValue.replaceAll("\"", "");
			     
			     if(strAcctContactHTMLTagValue.contains("<") && strAcctContactHTMLTagValue.contains(">") && !strAcctContactHTMLTagValue.contains("type=hidden")) {
			    	 
			    	 if(!strAcctContactHTMLTagValue.contains("select")) {
			    		 
			    		 strAcctContactActualTagName = strAcctContactHTMLTagValue.substring(strAcctContactHTMLTagValue.indexOf("<")+1, strAcctContactHTMLTagValue.indexOf(" ")); 
				    	 strAccContactVal=lstwebAcctContactValue.get(intlistVal).findElement(By.tagName(strAcctContactActualTagName)).getAttribute("value").toString().trim();	
				    	 intlistVal++;
				    	 
				    	 lstAcctContactValues.add(strAccContactVal);		    		 
			    	 }
			    	 else if(strAcctContactHTMLTagValue.contains("select")) {
			    		 
			    		 strAcctContactActualTagName = strAcctContactHTMLTagValue.substring(strAcctContactHTMLTagValue.indexOf("<")+1, strAcctContactHTMLTagValue.indexOf(" ")); 
			    		 String strDropDownValue = lstwebAcctContactValue.get(intlistVal).findElement(By.tagName(strAcctContactActualTagName)).getAttribute("value").toString().trim();    			
						
				    	 String strOptionXpath = "//option[@value='" +strDropDownValue+ "' and @selected='selected']";			    	 
				    	 strAccContactVal = driver.findElement(By.xpath(strOptionXpath)).getText();					
				    	 intlistVal++;
						
				    	 lstAcctContactValues.add(strAccContactVal);
			    	 }
			     }
			     else
			    	 intlistVal++;		     
			 }	     
		    
			 //TODO : Generate the final pair with headers and  values. 
			 for(Integer i=0;i<lstAcctOvwHeaderText.size();i++)		    	
				 lstAccountOverviewDetails.add(new BasicNameValuePair(lstAcctOvwHeaderText.get(i), lstAcctContactValues.get(i)));

			 //TODO : Read the DOB  Details.
			 String StrUserDOBHeader=objAccountOverview.fn_geUserDataofBiirthHeader(driver, webWait).getText().toString().trim();
			 String StrUserDOBVal=objAccountOverview.fn_geUserDataofBiirthVal(driver, webWait).getAttribute("value").toString().trim();
			 lstAccountOverviewDetails.add(new BasicNameValuePair(StrUserDOBHeader, StrUserDOBVal)); 
			 
			 //TODO :  Get Log of AcctContact Details
			 Log.info("Account Overview Details"+lstAccountOverviewDetails);			 
			 return lstAccountOverviewDetails;			 	 
		 }

/*    
 	Function fn_AccountPONumber: This method will be used for Reading / Updating PO Number.
 	
 	Inputs:	strAccountNum : to facilitate the account search for which the PO Number is to be verified.
 			strMPIID : Plan Instance ID, to facilitate the MPI for which PO Number test is to be done.
 			blnReadOrUpdate : Boolean to decide whether to just Read / Update the PO Number.
 			strPONumber : PO Number to set in case of UI Update.
 	
 	Output: Returns the PO Number as a string if existing, else null.
 	
 	** ASSUMES THAT THE ACCOUNT IS ALREADY SEARCHED AND CONTROL IS ON ACCOUNT OVERVIEW PAGE ** 
*/
	 public String fn_AccountPONumber(WebDriver driver, WebDriverWait webWait, String strAccountNum, String strMPIID,  
			 String strPONumber, Boolean blnReadOrUpdate, String strTestCaseName) throws Exception {
		 
	 	//TODO: Create object for the classes to be used for accessing reusable codes.
     	AccountsAccountOverview objAcctOverview = new AccountsAccountOverview();
     	AriaEOM objAriaEOM = new AriaEOM();
     	
     	String strPONum = "";
     	String strPONumXPath = "//input[@id='plan_instance_name"+strMPIID+"' and @type='text']";
     	
     	objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);
     	objAcctOverview.fn_clickPurchaseOrders(driver, webWait).click();
     	Thread.sleep(3500);
     	Utils.takeScreenshot(driver, strTestCaseName);
     	
     	if(blnReadOrUpdate == true) {	// since true, we have to READ the value for the PO Number and return.
     		
     		strPONum = driver.findElement(By.xpath(strPONumXPath)).getAttribute("value").toString().trim();
     		Log.info("The PO Number retrieved is: "+strPONum);
     		objAriaEOM.fn_clickAccountOverview(driver, webWait).click();
     		return strPONum;
     	}
     	else {	// since false, we have to SET the value for the PO Number and return.
     		
     		driver.findElement(By.xpath(strPONumXPath)).clear();
     		driver.findElement(By.xpath(strPONumXPath)).sendKeys(strPONumber);
     		Log.info("The PO Number to be set via UI is: "+strPONumber);
     		
     		objAcctOverview.fn_clickPurchaseOrdersSave(driver, webWait).click();
     		String strStatusMsg = objAriaEOM.fn_getStatusMessage(driver, webWait).getAttribute("textContent").toString().trim();
     		Utils.takeScreenshot(driver, strTestCaseName);
     		if(strStatusMsg.contains("Purchase Order Number for Plan Instance "+strMPIID+" has been updated."))    			
     			Log.info("PO Number value has been successfully updated !");
     		else {
     			
     			Log.error("ERROR: PO Number value couldn't be updated !");
     			Reporter.log("ERROR: PO Number value couldn't be updated !");
     		}
     		
     		strPONum = driver.findElement(By.xpath(strPONumXPath)).getAttribute("value").toString().trim();
     		Log.info("The PO Number retrieved after Update is: "+strPONum);
     		objAriaEOM.fn_clickAccountOverview(driver, webWait).click();
     		return strPONum;
     	}
	 }
	 
/*
 	Function fn_SetAccountTaxpayerInfo: This method will be used for Reading / Updating PO Number.
 	
 	Inputs:	strAccountNum : to facilitate the account search for which the PO Number is to be verified.
 			strMPIID : Plan Instance ID, to facilitate the MPI for which PO Number test is to be done.
 			blnReadOrSet : Boolean to decide whether to just Read (false)/ Set (true) the Taxpayer Information.
 			blnStateProvTaxExempt : Boolean to decide to click the State/Province Tax Exemption.
 			blnFederalTaxExempt : Boolean to decide to click the Federal/National Tax Exemption.
 	
 	Output: Returns the string "TaxpayerID#Boolean StateProvinceTaxExempt#Boolean FederalTaxExempt".
 	
 	** ASSUMES THAT THE ACCOUNT IS ALREADY SEARCHED AND CONTROL IS ON ACCOUNT OVERVIEW PAGE **  	
*/
	 public String fn_SetAccountTaxpayerInfo(WebDriver driver, WebDriverWait webWait, String strAccountNum 
			 , Boolean blnReadOrSet, String strTaxpayerID
			 , Boolean blnStateProvTaxExempt, Boolean blnFederalTaxExempt, String strTestCaseName) throws Exception {
		 
	 	//TODO: Create object for the classes to be used for accessing reusable codes.
     	AccountsAccountOverview objAcctOverview = new AccountsAccountOverview();
     	
     	Boolean blnCheckboxStatus;
     	String strTaxPayerInfo = " ";
     	try {
     		
     		if(blnReadOrSet == true) {	// set the Taxpayer Information for the Account requested & read the values for usage.
         		
         		//TODO: Navigate to Taxpayer Info tab, edit the details and save them.
         		objAcctOverview.fn_clickTaxpayerInformation(driver, webWait).click();
         		objAcctOverview.fn_clickTaxInfoEditFields(driver, webWait);
         		Utils.takeScreenshot(driver, strTestCaseName);
         		objAcctOverview.fn_clickTaxInfoEditFields(driver, webWait).click();
         		objAcctOverview.fn_clickTaxInfoSaveChanges(driver, webWait);
         		objAcctOverview.fn_setTaxpayerID(driver, webWait).clear();
         		objAcctOverview.fn_setTaxpayerID(driver, webWait).sendKeys(strTaxpayerID);
         		if(blnStateProvTaxExempt == true)
         			objAcctOverview.fn_clickTaxExemptStatus(driver, webWait, 1).click();
         		if(blnFederalTaxExempt == true)
         			objAcctOverview.fn_clickTaxExemptStatus(driver, webWait, 2).click();
         		objAcctOverview.fn_clickTaxInfoSaveChanges(driver, webWait).click();
         		objAcctOverview.fn_clickTaxInfoEditFields(driver, webWait);
         		Utils.takeScreenshot(driver, strTestCaseName);
         		objAcctOverview.fn_clickTaxInfoEditFields(driver, webWait).click();
         		objAcctOverview.fn_clickTaxInfoSaveChanges(driver, webWait);
         		strTaxPayerInfo = objAcctOverview.fn_setTaxpayerID(driver, webWait).getAttribute("value").toString().trim();
         		if(strTaxPayerInfo.contentEquals(""))
         			strTaxPayerInfo = " ";         			
         		strTaxPayerInfo = strTaxPayerInfo + "#";
         		blnCheckboxStatus = objAcctOverview.fn_clickTaxExemptStatus(driver, webWait, 1).isSelected();
         		strTaxPayerInfo = strTaxPayerInfo + blnCheckboxStatus.toString();
         		strTaxPayerInfo = strTaxPayerInfo + "#";
         		blnCheckboxStatus = objAcctOverview.fn_clickTaxExemptStatus(driver, webWait, 2).isSelected();
         		strTaxPayerInfo = strTaxPayerInfo + blnCheckboxStatus.toString();
         		objAcctOverview.fn_clickTaxpayerInformation(driver, webWait).click();
         		objAcctOverview.fn_clickTaxInfoEditFields(driver, webWait);
         	}
         	else {	// read the current settings to be used later for reseting.
         		
         		//TODO: Navigate to Taxpayer Info tab, edit the details and save them.
         		objAcctOverview.fn_clickTaxpayerInformation(driver, webWait).click();
         		objAcctOverview.fn_clickTaxInfoEditFields(driver, webWait);
         		Utils.takeScreenshot(driver, strTestCaseName);
         		objAcctOverview.fn_clickTaxInfoEditFields(driver, webWait).click();
         		objAcctOverview.fn_clickTaxInfoSaveChanges(driver, webWait);
         		if(strTaxPayerInfo.contentEquals(""))
         			strTaxPayerInfo = " ";         			
         		strTaxPayerInfo = strTaxPayerInfo + "#";
         		blnCheckboxStatus = objAcctOverview.fn_clickTaxExemptStatus(driver, webWait, 1).isSelected();
         		strTaxPayerInfo = strTaxPayerInfo + blnCheckboxStatus.toString();
         		strTaxPayerInfo = strTaxPayerInfo + "#";
         		blnCheckboxStatus = objAcctOverview.fn_clickTaxExemptStatus(driver, webWait, 2).isSelected();
         		strTaxPayerInfo = strTaxPayerInfo + blnCheckboxStatus.toString();
         		objAcctOverview.fn_clickTaxpayerInformation(driver, webWait).click();
         		objAcctOverview.fn_clickTaxInfoEditFields(driver, webWait);
         	}     		
     	}
     	catch (Exception exception) {

     		Log.error("ERROR: There was error in setting data for Account's Taxpayer Information !");
     		Reporter.log("ERROR: There was error in setting data for Account's Taxpayer Information !");
     		exception.printStackTrace();
     	}   	
     	
     	return strTaxPayerInfo;
	 }
}