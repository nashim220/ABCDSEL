/*
 Author     		:	Aashish bhutkar
 Class Name			: 	AccountFunctions_Plans 
 Purpose     		: 	Purpose of this file is :
						1. To perform various Account related functions related to Plans.

 Date       		:	06/06/2016
 Modified Date		:	
 Version Information:	Version 1.0
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package appModules;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import pageObjects.AccountPlansUsage;
import pageObjects.AccountsPlans;
import pageObjects.AriaEOM;
import utility.ApiHandler;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;

public class AccountFunctions_Plans {

/*
	Function fn_GetPlans: Takes in the Plan Name to be searched and returns the Plan Number for the Plan in current instance.
*/	
	public String fn_GetPlans(WebDriver driver, WebDriverWait webWait, String strPlanName, String strTestCaseName) throws Exception	{
		
		AriaEOM objAriaEOM = new AriaEOM();
		
		//TODO: After login, Navigate to the Products -> Plans and get the Plan Number for Name requested.
		objAriaEOM.fn_clickProducts(driver, webWait).click();
		objAriaEOM.fn_clickProductsPlans(driver, webWait).click();
		objAriaEOM.fn_getDataTable(driver, webWait);
		Thread.sleep(1500);
		Utils.takeScreenshot(driver, strTestCaseName);
		//TODO: Create objects to read data table contents into.
		WebElement tblPlans = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
		List<WebElement> lstwebPlansRows = tblPlans.findElements(By.tagName("tr"));

		//TODO: Loop to traverse through the rows to identify the right Plan requested for. 
		for(WebElement rows: lstwebPlansRows) {
			
			List<WebElement> cols = rows.findElements(By.tagName("td"));
		
			if(cols.size() != 0) {	// this is to avoid the header read in the loop.

				// get the boolean status in the variables to proceed further on the action of selecting the correct plan.
				Boolean blnPlanType = cols.get(3).getAttribute("innerText").toString().trim().contentEquals(strPlanName);
				Boolean blnPlanStatus = cols.get(5).getAttribute("innerText").toString().trim().contentEquals("Active");
				if(blnPlanType == true && blnPlanStatus == true) {
					
					String strPlanNumber = cols.get(1).getAttribute("innerText").toString().trim();
					Log.info("The Active Plan is found and Plan Number for it will be returned which is: "+strPlanNumber);
					return strPlanNumber;
				}
			}
			else if(!(cols.iterator().hasNext())) {
				
				Log.error("ERROR: The Plans table is empty or without Active Plan details.");
				Reporter.log("ERROR: The Plans table is empty or without Active Plan details.");
				return "";				
			}
		}
		
		return "";
	}

	
/*
	Function fn_VerifySupplementalPlans: Takes in the details Account Number to verify if Supplemental Plan has been assigned to it
	
	Output: Returns String type value of the Plan's Instance ID. 
										
*/    
    public String fn_VerifySupplementalPlans(WebDriver driver, WebDriverWait webWait, String strAccountNum
    		, String strSPI, String strTestCaseName) throws Exception {
    	
    	//TODO: Create class objects for the common functions to be used for plans verification.
    	Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
    	AccountPlansUsage objAccountPlans = new AccountPlansUsage();
		AriaEOM objAriaEOM = new AriaEOM();
		
    	//TODO: Navigate to the Supplemental Plans details for Account Number requested for.
    	try {
    		
        	//TODO: Search the account number for which the Supplemental Plans assignment is to be verified.
			objClientDefAct.AccountSearch(driver, webWait, strAccountNum);			
			objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);
			objAriaEOM.fn_clickAccountPlans(driver, webWait).click();	// navigated to the Plans sub-menu.
			objAriaEOM.fn_getDataTable(driver, webWait);
			objAccountPlans.fn_clickPlansTab(driver, webWait).click();	// navigate to Plans tab.
			objAriaEOM.fn_getDataTable(driver, webWait);
			Utils.takeScreenshot(driver, strTestCaseName);
			
			//TODO: Read the data table to get the actual status of the Account's Supplemental Plan assignment.
			WebElement tblPlans = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
			List<WebElement> lstwebPlansRows = tblPlans.findElements(By.tagName("tr"));
    		for(WebElement rows : lstwebPlansRows){
    			
    			List<WebElement> cols = rows.findElements(By.tagName("td"));
    			
    			if(cols.size() != 0) {	// this is to avoid the header read in the loop.

    				String strConfirmSPI = cols.get(0).findElement(By.tagName("span")).getAttribute("innerHTML").toString().trim();
    				
    				if(strConfirmSPI.contains("/ui/images/hierarchy_arrow.png")) {
    					
    					String strSPIName = cols.get(1).getAttribute("innerText").toString().trim();
    					String strSPIStatus = cols.get(4).getAttribute("innerText").toString().trim();
    					String strSPIInstanceID = cols.get(2).getAttribute("innerText").toString().trim();
    					
    					if(strSPIName.contentEquals(strSPI) && strSPIStatus.contentEquals("Active")) {
    						
    						objAriaEOM.fn_clickAnalyticsReporting(driver, webWait).click();
        					Log.info("Correct Supplemental Plan has already been Assigned to the Account: "+strAccountNum
        										+ ", with Instance ID as: "+strSPIInstanceID);
        					
        					return strSPIInstanceID;	// Supplemental Plan has been assigned.
    					}    					
    				}
    			}
    			else if(!(cols.iterator().hasNext())) {
    				
    				Log.error("ERROR: The Supplemental Plans table is empty or without Plan details.");
    				Reporter.log("ERROR: The Supplemental Plans table is empty or without Plan details.");
					objAriaEOM.fn_clickAnalyticsReporting(driver, webWait).click();
					
    				return "";				
    			}
    		}			
    	}
    	catch (Exception exception) {
    		
    		Log.error("ERROR: The Supplemental Plan couldn't be verified due to exception: "+exception.toString());
    		Reporter.log("ERROR: The Supplemental Plan couldn't be verified due to exception: "+exception.toString());
    		throw exception;
    	}
    	
		objAriaEOM.fn_clickAnalyticsReporting(driver, webWait).click();
    	return "";
    }
    
	
/*
	Function fn_AssignSuppPlan: Takes in the details Account Number & Plan number to make a Post call for Assigning the Supplemental Plan.
*/  
    
    public void fn_AssignSuppPlan(WebDriver driver, WebDriverWait webWait, String strAccountNum, 
    		String strPlanNum, String strAPIURL) throws Exception {

        AccountFunctions objAccountFunc = new AccountFunctions();
    	
    	ApiHandler api = new ApiHandler();        
        
        //TODO: Get the Full URL with the authentication details for current instance. 
        String strFullUrl = objAccountFunc.fn_GetFullUrl(driver, webWait, strAPIURL, "assign_supp_plan");
        strFullUrl = strFullUrl+"&acct_no="+strAccountNum+"&supp_plan_no="+strPlanNum+"&assignment_directive=2";
        
        api.makeSimplePostCall(strFullUrl);	// make the Post call to Assign the Supplemental Plan.
    }
    
	
/*
	Function fn_GetPlanInstanceID: Takes in the details for Account Number and returns the Instance ID for the Plan (Master / Supplementary) assigned to it.
	
	*** NOTE:	The Instance ID's sent are: 'Plan Instance ID'#'Client Defined Identifier' .	***
				Both are returned so that they can be used as need be.
*/  
    
    public String fn_GetPlanInstanceID(WebDriver driver, WebDriverWait webWait, String strAccountNum, 
    		String strTestCaseName, String strPlanName) throws Exception {

    	//TODO: Create class objects for the common functions to be used for plans verification.
    	Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
    	AccountPlansUsage objAccountPlans = new AccountPlansUsage();
    	AriaEOM objAriaEOM = new AriaEOM();
    	String strInstanceID = "";
    	
		objClientDefAct.AccountSearch(driver, webWait, strAccountNum);			
		objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);
		objAriaEOM.fn_clickAccountPlans(driver, webWait).click();
		objAriaEOM.fn_getDataTable(driver, webWait);
		objAccountPlans.fn_clickPlansTab(driver, webWait).click();	// navigate to Plans tab.
    	
    	//TODO: Read the Data table and click the Plan Units to note the Plan Instance ID.
    	WebElement tblSupplementalPlans = objAriaEOM.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
		List<WebElement> lstwebSupplementalPlansRows = tblSupplementalPlans.findElements(By.tagName("tr"));
		for(WebElement rows : lstwebSupplementalPlansRows) {

			List<WebElement> cols = rows.findElements(By.tagName("td"));
			
			if(cols.size() != 0) {	// this is to avoid the header read in the loop.

				String strReadPlanName = cols.get(1).getAttribute("innerText").toString().trim();
				String strReadStatus = cols.get(4).getAttribute("innerText").toString().trim();
				
				if(strReadPlanName.contentEquals(strPlanName) && strReadStatus.contentEquals("Active")) {

					// record the Plan Instance ID first.
					strInstanceID = cols.get(0).getAttribute("innerText").toString().trim();
					// add a separator like # to help separate the received ID's
					strInstanceID = strInstanceID + "#";
					// record the Client Defined Identifier now.
					strInstanceID = strInstanceID + cols.get(2).getAttribute("innerText").toString().trim();
					
					Log.info("The "+strPlanName+" Plan ID's have been noted as '"+strInstanceID+"' and have been returned !");
					objAriaEOM.fn_clickAnalyticsReporting(driver, webWait).click();
					return strInstanceID;	//instance ID has been returned.
				}
			}
			else if(!(cols.iterator().hasNext())) {

				Log.error("ERROR: The "+strPlanName+" Plan's Instance ID couldn't be retrieved");
				Reporter.log("ERROR: The "+strPlanName+" Plan's Instance ID couldn't be retrieved.");
				objAriaEOM.fn_clickAnalyticsReporting(driver, webWait).click();
				VerificationMethods.assertTrue(false, "ERROR: The "+strPlanName+" Plan's Instance ID couldn't be retrieved");
			}
		}
		
		objAriaEOM.fn_clickAnalyticsReporting(driver, webWait).click();
    	return strInstanceID;
    }

	
/*
	Function fn_setMPI: Takes in the details for Account Number and MPI ID and sets the MPI for Immediate Billing.
*/  
  
    public Boolean fn_setMPI (WebDriver driver,  WebDriverWait webWait,	String strAPIURL, String strAccountNum, 
    				String strMPIID) throws Exception {
    	
        AccountFunctions objAccountFunc = new AccountFunctions();
    	ApiHandler api = new ApiHandler();
    	
    	Boolean blnMPIUpdated = false;
/*
 * 		client_no=6000076&auth_key=7RKnupEJM8b3u9jx9tMqAF9uBvjBKvEg
 * 		&rest_call=update_acct_plan_status_m&acct_no=212335
 * 		&client_plan_instance_id=54947
 * 		&dunning_state=0
 * 		&status_cd=1
 * 		&force_bill_date_reset=1
 *     	
*/        
        //TODO: Get the Full URL with the authentication details for current instance. 
        String strFullUrl = objAccountFunc.fn_GetFullUrl(driver, webWait, strAPIURL, "update_acct_plan_status_m");
        String strAPICancel = strFullUrl + "&acct_no="+strAccountNum + "&plan_instance_no="+strMPIID 
        							+ "&dunning_state=0" + "&status_cd=-2";        
        List<JSONObject> apiResponse = api.makeSimplePostCall(strAPICancel);	// make the Post call to Cancel the MPI @ first.
        //TODO: Verify JSON response to validate success.
        for(JSONObject response : apiResponse) {
        	
        	int errorCode = Integer.parseInt(response.get("error_code").toString());	
            if(errorCode != 0) {
            	
                String errorMsg = response.get("error_msg").toString();
                Log.error("ERROR: API call failed with message: "+errorMsg);
                Reporter.log("ERROR: API call failed with message: " + errorMsg);
                blnMPIUpdated = false;
             }
            else {
            
            	Log.info("The MPI '"+strMPIID+"' status has been set as Cancelled...");
            	blnMPIUpdated = true;
            }
         }        
        
        String strAPIActive = strFullUrl + "&acct_no="+strAccountNum + "&plan_instance_no="+strMPIID 
									+ "&dunning_state=0" + "&status_cd=1" + "&force_bill_date_reset=1";        
        apiResponse = api.makeSimplePostCall(strAPIActive);	// make the Post call to set MPI Active &  
        												//reset Bill thru Date to Current Date.
        //TODO: Verify JSON response to validate success.
        for(JSONObject response : apiResponse) {
        	
        	int errorCode = Integer.parseInt(response.get("error_code").toString());	
            if(errorCode != 0) {
            	
                String errorMsg = response.get("error_msg").toString();
                Log.error("ERROR: API call failed with message: "+errorMsg);
                Reporter.log("ERROR: API call failed with message: " + errorMsg);
                blnMPIUpdated = false;
             }
            else {
            	
            	Log.info("The MPI '"+strMPIID+"' status has been set as Active...");
            	blnMPIUpdated = true;            	
            }            	
         }
    	
    	return blnMPIUpdated;
    }
    /*
	Author: Nashim Khan
	Function fn_getBillingGroupDetails: Returns the List with Name value pair for the Account Details.
	
	Inputs: strBilling AccountNum -  Billing Account Number for which information is to be captured.			
			strMPIID - MPI Plan Instance ID for which Billing Information is to be collected.
	
	Outputs: List Name Value Pair for the Account details.    
*/
	 public List<NameValuePair> fn_getBillingGroupDetails(WebDriver driver, WebDriverWait webWait, 
			 							String strAccountNum, String strMPIID) throws Exception{
		 
		  
		List<NameValuePair> lstBillingDetails = new ArrayList<NameValuePair>();
		
    	//TODO: Create object for the classes to be used for accessing reusable codes.	
		AriaEOM objAriaEOM = new AriaEOM();		
    	Cls_ChangeDeleteClientDefinedFieldActns objChangeDelClientFd = new Cls_ChangeDeleteClientDefinedFieldActns();    	
    	AccountsPlans objAcctPlans=new AccountsPlans(); 
    	//TODO : Search Account		
    	objChangeDelClientFd.AccountSearch(driver, webWait, strAccountNum);
       	//TODO : Click on Plans   
    	objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);     	
    	objAriaEOM.fn_clickAccountPlans(driver, webWait).click(); 	   			
    	//TODO : Click on Billing Group

    	//TODO: Fetch the Billing Group ID for the MPI specified and proceed for gathering information. 
    	objAcctPlans.fn_clickPlansMPI(driver, webWait, strMPIID).click();
    	Thread.sleep(3000);
    	String strBillingGroupId=objAcctPlans.fn_getBillingGroupId(driver, webWait).getText().toString().trim();    	
    	objAcctPlans.fn_clickBillingGroupsTab(driver, webWait).click();    	
    	objAcctPlans.fn_clickBillingGroupID(driver, webWait, strBillingGroupId).click();    	
	
         //TODO :Get Billing Information	    	    	  	
    	//TODO : Grab the  Billing table  Details     	
    	WebElement webBillDetailTable = driver.findElement(By.xpath("//form[@id='reassignForm']/table[1]/tbody"));     	 
    	List<WebElement> lstwebBillDtlsHeader = webBillDetailTable.findElements(By.xpath("tr/th"));
    	List<WebElement> lstwebBillDtlsValues= webBillDetailTable.findElements(By.xpath("tr/td"));  	 
 	    for(Integer intList = 0; intList < lstwebBillDtlsHeader.size(); intList++)	    	
 	    {       		   
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
		 //TODO : Header Pair with Value   And Logs
 	    lstBillingDetails.add(new BasicNameValuePair(strHeader, strValue));  
 	    }    	
  	    //TODO : Grab the  Notification  table  Details
        WebElement webNotifiDetailTable = driver.findElement(By.xpath("//form[@id='reassignForm']/table[2]/tbody"));     	  
     	List<WebElement> lstwebNotifDtlTblHeader = webNotifiDetailTable.findElements(By.xpath("tr/th"));
    	List<WebElement> lstwebNotifDtlsValues= webNotifiDetailTable.findElements(By.xpath("tr/td"));
    	for(Integer intList = 0; intList < lstwebNotifDtlTblHeader.size(); intList++)	    	
    	{ 
    		String strNotiHeader= lstwebNotifDtlTblHeader.get(intList).getText().toString().trim();
    		String strNotiValue = null;
    		String strActualTagName=null;
    		Integer intValueList = 0;
    		//TODO: Read the InnerHTML to know what Tag we are dealing with for the Value to be read.
    		String strNotiHTMLTagValue = lstwebNotifDtlsValues.get(intList).getAttribute("innerHTML").toString().trim();
    		strNotiHTMLTagValue = strNotiHTMLTagValue.replaceAll("\"", "");
    		strNotiHTMLTagValue = strNotiHTMLTagValue.replaceAll("<br>\n", "");
    		strNotiHTMLTagValue = strNotiHTMLTagValue.replaceAll("\\s+<", "<");    		
    		if(strNotiHTMLTagValue.contains("<") && strNotiHTMLTagValue.contains(">")){   		
    			strActualTagName = strNotiHTMLTagValue.substring(strNotiHTMLTagValue.indexOf("<")+1, strNotiHTMLTagValue.indexOf(" "));    				
    			if(strActualTagName.contentEquals("input") && strNotiHTMLTagValue.contains("type=radio")){
    				String strRadioBtnXpath = "//input[@value='0' and @type='radio']";    				
    				String strRadioOption = driver.findElement(By.xpath(strRadioBtnXpath)).getAttribute("defaultChecked").toString().trim();
    				if(strRadioOption.contentEquals("true")){
    					strRadioBtnXpath = "//input[@value='0' and @type='radio']/following::label[1]";    					
    					strNotiValue = driver.findElement(By.xpath(strRadioBtnXpath)).getText().trim();
    				}
    				strRadioBtnXpath = "//input[@value='1' and @type='radio']/following::label[1]";    								
    				strNotiValue = driver.findElement(By.xpath(strRadioBtnXpath)).getText().trim();
    			} 
    			else if(strActualTagName.contains("select")){
    				intValueList ++;        			
        			String strDropDownValue = lstwebNotifDtlsValues.get(intList).findElement(By.tagName(strActualTagName)).getAttribute("value").toString().trim();    			
    				String strOptionXpath = "//select/option[@value='"+strDropDownValue+"']";
    				strNotiValue = driver.findElement(By.xpath(strOptionXpath)).getText();
    			}
    			else {   
    				strNotiValue = lstwebNotifDtlsValues.get(intList).findElement(By.tagName(strActualTagName)).getAttribute("value").toString().trim();
    			}    			
    		} 		
         //TODO : Header Pair With value
    	lstBillingDetails.add(new BasicNameValuePair(strNotiHeader, strNotiValue));
        }
    	//TODO : Grab Of Choose Payment  Option
    	// TODO : Get  Header Of Payment   Option
    	WebElement webPayOptHeaderVal=driver.findElement(By.xpath("//div[@id='paymentOption']/b"));
    	String strPaymentOptHeader= webPayOptHeaderVal.getText().toString().trim();    			  
    	// TODO : Get Detail  Of Selected Payment Option
    	String strNotiValue =null;
    	String strPaymentOptionsXpath=null; 
    	WebElement webPayOptionXpath=driver.findElement(By.xpath("//div[@id='paymentOption']"));
    	String strPayOptHTMLTagValue = webPayOptionXpath.getAttribute("innerHTML").toString().trim();
    	strPayOptHTMLTagValue = strPayOptHTMLTagValue.replaceAll("\"", "");
    	strPayOptHTMLTagValue = strPayOptHTMLTagValue.replaceAll("<label>\n", "");
    	strPayOptHTMLTagValue = strPayOptHTMLTagValue.replaceAll("\\s+<", "<");        		
    	if(strPayOptHTMLTagValue.contains("<") && strPayOptHTMLTagValue.contains(">")){
    		String strActualTagName = strPayOptHTMLTagValue.substring(strPayOptHTMLTagValue.indexOf("<")+1, strPayOptHTMLTagValue.indexOf(" "));        			
    		if(strActualTagName.contentEquals("input") && strPayOptHTMLTagValue.contains("type=radio"))	{
    			strPaymentOptionsXpath = "//div[@id='paymentOption']/input[@type='radio' and @value='1']";    				
    			String strRadioOption = driver.findElement(By.xpath(strPaymentOptionsXpath)).getAttribute("defaultChecked").toString().trim();
    			if(strRadioOption.contentEquals("true")){ 	
    				strPaymentOptionsXpath ="//div[@id='paymentOption']/input[@type='radio' and @value='1']/following::label[1]";    	    					
    				strNotiValue = driver.findElement(By.xpath(strPaymentOptionsXpath)).getText().trim();   			
    			}
    		}	
    		else {	
    			strPaymentOptionsXpath ="//div[@id='paymentOption']/input[@type='radio' and @value='2']/following::label[1]";    	    				  				
    			strNotiValue = driver.findElement(By.xpath(strPaymentOptionsXpath)).getText().trim();
    		}        			
    	//TODO : Name Pair with Value And Log
    	lstBillingDetails.add(new BasicNameValuePair(strPaymentOptHeader, strNotiValue));    	
    	}	 
    	//TODO : Account  Credential table Details  pair values 
    	String strAccCredTabXpath="//th[text()='Statement Contact']";
    	String strstatementHdValue = driver.findElement(By.xpath(strAccCredTabXpath)).getText().toString().trim();            
    	Select selStmntContacts= new Select(driver.findElement(By.id("inStatementContacts")));    	            		    	
    	List<WebElement> lstwebOptions= selStmntContacts.getAllSelectedOptions();
    	for (WebElement webOption : lstwebOptions)
    	{    	                  
    		String    selOptionValue=webOption.getText();    	            	  	
    		lstBillingDetails.add(new BasicNameValuePair(strstatementHdValue, selOptionValue));      		
    	}
    	try {
    		// TODO : Get  Terms Of Payment Option
    	    //TODO : Get Terms Header Values
        	WebElement webTermOptHeaderVal=driver.findElement(By.xpath("//div[@id='NetTerm']/b"));
        	String strTermHeader= webTermOptHeaderVal.getText().toString().trim();       	
        	//TODO : Get Terms Option Values 
        	String strTermsValue =null;        	
        	WebElement webPayTermXpath=driver.findElement(By.xpath("//div[@id='NetTerm']/table/tbody"));
        	List<WebElement> lstwebPayTermsDtlsValues= webPayTermXpath.findElements(By.xpath("tr/td")); 
        	for(Integer intList = 0; intList < lstwebPayTermsDtlsValues.size(); intList++)	    	
        	{ 
         		String strTermOptHTMLTagValue = lstwebPayTermsDtlsValues.get(intList).findElement(By.tagName("input"))
         														.getAttribute("defaultChecked").toString().trim(); 	
    			
    			if(strTermOptHTMLTagValue.contentEquals("true"))	{        				
    				strTermsValue = lstwebPayTermsDtlsValues.get(intList).findElement(By.tagName("span")).getAttribute("innerText").trim();        				
    				lstBillingDetails.add(new BasicNameValuePair(strTermHeader, strTermsValue));
    			}  
        	}
        	
		    } catch(Exception e){
			
			Log.info("NetTerms option is Not Selected ");			
		   }
    	
    	 try {
    		// TODO : Get  Terms Of Payment Method 
      	    //TODO : Get Payment Method  Details Header Values
          	WebElement webPriPayOptHeaderVal=driver.findElement(By.xpath("//div[@id='paymentOption']/label[1]"));
          	String strTermHeader= webPriPayOptHeaderVal.getText().toString().trim();
          	//TODO : Get Payment Method  Option Values 
          	String strTermsValue =null;        	
          	WebElement webPayTermXpath=driver.findElement(By.xpath("//div[@id='mainPayMethods']/div/table/tbody"));
          	List<WebElement> lstwebPayTermsDtlsValues= webPayTermXpath.findElements(By.xpath("tr/td")); 
          	for(Integer intList = 0; intList < lstwebPayTermsDtlsValues.size(); intList++)	    	
          	{ 
           		strTermsValue = lstwebPayTermsDtlsValues.get(intList).findElement(By.tagName("select"))
           														.getAttribute("Value").toString().trim();   			
   				lstBillingDetails.add(new BasicNameValuePair(strTermHeader, strTermsValue));      			
          	}
		    } catch (Exception e) {
			Log.info("Method  is Not Selected ");
		    }    	 
    		try {
    			//TODO : Grab the  Primary Payment Account credential  table  Details       	
    	    	WebElement webAccCredentialTable = driver.findElement(By.xpath("//div[@id='primaryPayMethod']/div/table/tbody"));     	  
    	     	List<WebElement> lstwebPriAccDtlHeader = webAccCredentialTable.findElements(By.xpath("tr/th"));
    	    	List<WebElement> lstwebPriAccDtlValue= webAccCredentialTable.findElements(By.xpath("tr/td"));
    	    	Integer intValueList = 0;
    	    	for(Integer intList = 0; intList < lstwebPriAccDtlHeader.size(); intList++)
    	    	{    	   				 
    	    		String strAccHeader= "PriPay_"+lstwebPriAccDtlHeader.get(intList).getAttribute("innerHTML").toString().trim();   		
    	    		String strAccValue = null;
    	    		String strActualTagName = null;    		
    	    		//TODO: Read the InnerHTML to know what Tag we are dealing with for the Value to be read.
    	    		String strAccHTMLTagValue = lstwebPriAccDtlValue.get(intValueList).getAttribute("innerHTML").toString().trim();
    	    		strAccHTMLTagValue = strAccHTMLTagValue.replaceAll("\"", "");     	    		
    	    		if(strAccHTMLTagValue.contains("<") && strAccHTMLTagValue.contains(">") ){   	    		
    	    			strActualTagName = strAccHTMLTagValue.substring(strAccHTMLTagValue.indexOf("<")+1, strAccHTMLTagValue.indexOf(" "));    	    			
    	    			strAccValue = lstwebPriAccDtlValue.get(intValueList).findElement(By.tagName(strActualTagName)).getAttribute("value").toString().trim();    	    			
    	    			intValueList ++;
    	    			}    	    		
    	    		else {   	    			
    	    			strAccValue = strAccHTMLTagValue;
    	    			intValueList ++;
    	    		}    
    	    		//TODO : Header Pair  With Header, Value and Logs
    	    		lstBillingDetails.add(new BasicNameValuePair(strAccHeader, strAccValue)); 
    	      	} 
    	      	}     	      
    		catch(Exception e){
    			Log.info(" Primary Payment Method  is Not Selected ! ");    			
    		    }    		
    		try {
    			//TODO : Grab the  Secondary Payment  Account credential  table  Details       	
    	    	WebElement webAccCredentialTable = driver.findElement(By.xpath("//div[@id='secondaryPayMethod']/div/table/tbody"));     	  
    	     	List<WebElement> lstwebSecAccDtlHeader = webAccCredentialTable.findElements(By.xpath("tr/th"));
    	    	List<WebElement> lstwebSecAccDtlValue= webAccCredentialTable.findElements(By.xpath("tr/td"));
    	    	Integer intValueList = 0;
    	    	for(Integer intList = 0; intList < lstwebSecAccDtlHeader.size(); intList++)
    	    	{    	   				 
    	    		String strAccHeader= "SecPay_"+lstwebSecAccDtlHeader.get(intList).getAttribute("innerHTML").toString().trim();   		
    	    		String strAccValue = null;
    	    		String strActualTagName = null;    		
    	    		//TODO: Read the InnerHTML to know what Tag we are dealing with for the Value to be read.
    	    		String strAccHTMLTagValue = lstwebSecAccDtlValue.get(intValueList).getAttribute("innerHTML").toString().trim();
    	    		strAccHTMLTagValue = strAccHTMLTagValue.replaceAll("\"", "");     	    	
    	    		if(strAccHTMLTagValue.contains("<") && strAccHTMLTagValue.contains(">") ){    	    		
    	    			strActualTagName = strAccHTMLTagValue.substring(strAccHTMLTagValue.indexOf("<")+1, strAccHTMLTagValue.indexOf(" "));    	    			
    	    			strAccValue = lstwebSecAccDtlValue.get(intValueList).findElement(By.tagName(strActualTagName)).getAttribute("value").toString().trim();    	    			
    	    			intValueList ++;
    	    			}    	    		
    	    		else {    	    			
    	    			strAccValue = strAccHTMLTagValue;
    	    			intValueList ++;
    	    		}    
    	    		//TODO : Header Pair With Value and Logs
    	    		lstBillingDetails.add(new BasicNameValuePair(strAccHeader, strAccValue));      	    		
      	      	} 
     	      	}     	      
    		catch(Exception e){
    			Log.info(" Secondary  Payment Method  is Not Selected! ");
       		}    	
    	//TODO : Grab the  Account credential  table  Details        	
    	WebElement webAccCredentialTable = driver.findElement(By.xpath("//div[@id='Statement-contact-details-div']/table/tbody"));     	  
     	List<WebElement> lstwebAccDtlHeader = webAccCredentialTable.findElements(By.xpath("tr/th"));
    	List<WebElement> lstwebStmntAccDtlValue= webAccCredentialTable.findElements(By.xpath("tr/td"));
    	Integer intValueList = 0;
    	for(Integer intList = 0; intList < lstwebAccDtlHeader.size(); intList++)
    	{    	   				 
    		String strAccHeader= "Stmnt_"+lstwebAccDtlHeader.get(intList).getText().toString().trim();   		
    		String strAccValue = null;
    		String strActualTagName = null;    		
    		//TODO: Read the InnerHTML to know what Tag we are dealing with for the Value to be read.
    		String strAccHTMLTagValue = lstwebStmntAccDtlValue.get(intValueList).getAttribute("innerHTML").toString().trim();
    		strAccHTMLTagValue = strAccHTMLTagValue.replaceAll("\"", "");    
    		if(strAccHTMLTagValue.contains("<") && strAccHTMLTagValue.contains(">") && !strAccHTMLTagValue.contains("type=hidden")
    				&& !strAccHTMLTagValue.contains("Country")){	    		
    			strActualTagName = strAccHTMLTagValue.substring(strAccHTMLTagValue.indexOf("<")+1, strAccHTMLTagValue.indexOf(" "));    			
    			strAccValue = lstwebStmntAccDtlValue.get(intValueList).findElement(By.tagName(strActualTagName)).getAttribute("value").toString().trim();    			
    			intValueList ++;
    		}
    		//TODO : Read the Hidden  to know what tag is hidden
    		else if(strAccHTMLTagValue.contains("type=hidden")) {    			
    			intValueList ++;
    			strActualTagName = strAccHTMLTagValue.substring(strAccHTMLTagValue.indexOf("<")+1, strAccHTMLTagValue.indexOf(" "));
    			strAccValue = lstwebStmntAccDtlValue.get(intValueList).findElement(By.tagName(strActualTagName)).getAttribute("value").toString().trim();
    			intValueList ++;
    		}    
    		else if( strAccHTMLTagValue.contains("selected=selected" ))
    		{
    			intValueList ++;
    			String strAccCountryXpath ="//div[@id='Statement-contact-details-div']/table/tbody/tr/td/select";    			
    			String strDropDownValue = driver.findElement(By.xpath(strAccCountryXpath)).getAttribute("type").toString().trim();    			
				String strOptionXpath = strAccCountryXpath+"/option[@value='"+strDropDownValue+"']";
				 strAccValue = driver.findElement(By.xpath(strOptionXpath)).getText();
    		}    		
    		else {    			
    			strAccValue = strAccHTMLTagValue;
    			intValueList ++;
    		}    
    		//TODO : Header Pair With Value and Logs
    		lstBillingDetails.add(new BasicNameValuePair(strAccHeader, strAccValue));  	    	   	     
      	} 
      Log.info("Billing information"+lstBillingDetails);  	
     return lstBillingDetails;
	 }
     
}