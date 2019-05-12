/*
 Author     		:	Aashish bhutkar
 Class Name			: 	AccountFunctions_Plans 
 Purpose     		: 	Purpose of this file is :
						1. To perform various Account related functions related to Plans.

 Date       		:	09/24/2015
 Modified Date		:	11/23/2015, 11/06/2015, 10/27/2015, 09/25/2015
 Version Information:	Version 3.0

 Version Changes 2.0:	1. Added function "fn_VerifyParentChildAccounts" to verify Parent Child relation
 						and return the account numbers with Parent first, followed by child accounts.
 Version Changes 2.1:	1. Modified the logic for the Supplemental Plans details and there afters.
 Version Changes 3.0:	1. Added function "fn_GetAccountPaymentResponsibility" to get the Payment Responsibility for
 						child account. 

 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package appModules;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import pageObjects.AccountPlansUsage;
import pageObjects.ParentChildDiscounting;
import pageObjects.ProductCatalogRateSheets;
import utility.ApiHandler;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;

public class AccountFunctions_Plans 
{
	public static WebElement webElement;
	
/*
	Function fn_GetPlans: Takes in the Plan Name to be searched and returns the Plan Number for the Plan in current instance.
*/	
	public String fn_GetPlans(WebDriver driver, WebDriverWait webWait, String strPlanName, String strTestCaseName) throws Exception
	{
		//TODO: After login, Navigate to the Products -> Plans and get the Plan Number for Name requested.
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(ProductCatalogRateSheets.fn_clickProductsLeftNav()));
   		Log.info("Clicking the Products Menu in left nav.");   		
   		driver.findElement(ProductCatalogRateSheets.fn_clickProductsLeftNav()).click();	// click Product Menu

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(ProductCatalogRateSheets.fn_clickPlansLeftNav()));
		Log.info("Clicking the Plans Sub-Menu in left nav of Products.");
		driver.findElement(ProductCatalogRateSheets.fn_clickPlansLeftNav()).click();	// Click the Rates sub-menu.
		
		Log.info("Waiting to find the Plans data table.");
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(ProductCatalogRateSheets.fn_getSupplementalPlansDataTable()));
		Thread.sleep(1500);
		Utils.takeScreenshot(driver, strTestCaseName);
		//TODO: Create objects to read data table contents into.
		WebElement tblPlans = driver.findElement(ProductCatalogRateSheets.fn_getSupplementalPlansDataTable()).findElement(By.tagName("tbody"));
		Log.info("Data table for the various Plans available has been read.");
		List<WebElement> lstwebPlansRows = tblPlans.findElements(By.tagName("tr"));
		Log.info("Rows for the Plans data table have been read.");

		//TODO: Loop to traverse through the rows to identify the right Plan requested for. 
		for(WebElement rows: lstwebPlansRows)
		{
			List<WebElement> cols = rows.findElements(By.tagName("td"));
		
			if(cols.size() != 0)	// this is to avoid the header read in the loop.
			{
				// get the boolean status in the variables to proceed further on the action of selecting the correct plan.
				Boolean blnPlanType = cols.get(2).getAttribute("innerText").toString().trim().contentEquals(strPlanName);
				Boolean blnPlanStatus = cols.get(6).getAttribute("innerText").toString().trim().contentEquals("Active");
				if(blnPlanType && blnPlanStatus)
				{
					String strPlanNumber = cols.get(1).getAttribute("innerText").toString().trim();
					Log.info("The Active Plan is found and Plan Number for it will be returned which is: "+strPlanNumber);
					Utils.takeScreenshot(driver, strTestCaseName);
					return strPlanNumber;
				}
			}
			else if(!(cols.iterator().hasNext()))
			{
				Log.error("ERROR: The Plans table is empty or without Active Plan details.");
				Reporter.log("ERROR: The Plans table is empty or without Active Plan details.");
				return "";				
			}
		}
		return "";
	}

	
/*
	Function fn_VerifySupplementalPlans: Takes in the details Account Number to verify if Supplemental Plan has been assigned to it 
										and returns Boolean value based on it's status.
*/    
    public Boolean fn_VerifySupplementalPlans(WebDriver driver, WebDriverWait webWait, String strAccountNum, String strTestCaseName) throws Exception
    {
    	//TODO: Create class objects for the common functions to be used for plans verification.
    	Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
    	AccountPlansUsage objAccountPlans = new AccountPlansUsage();
    	
    	//TODO: Navigate to the Supplemental Plans details for Account Number requested for.
    	try
    	{
        	//TODO: Search the account number for which the Supplemental Plans assignment is to be verified.
    		Log.info("Calling Search Account Number reusable module.");
			objClientDefAct.AccountSearch(driver, webWait, strAccountNum);			
			Log.info("Successfully searched the Account Number: "+strAccountNum.toString());
			objAccountPlans.fn_clickPlansServices(driver, webWait).click();	// navigated to the Plans & Service sub-menu.
			webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id, 'DataTables_Table_')]")));
			objAccountPlans.fn_clickSupplementalPlans(driver, webWait).click();	// navigate to Supplemental Plans tab.
			objAccountPlans.fn_getSupplementalPlansDataTable(driver, webWait);
			Utils.takeScreenshot(driver, strTestCaseName);
			
			//TODO: Read the data table to get the actual status of the Account's Supplemental Plan assignment.
			WebElement tblSupplementalPlans = objAccountPlans.fn_getSupplementalPlansDataTable(driver, webWait).findElement(By.tagName("tbody"));
			List<WebElement> lstwebSupplementalPlansRows = tblSupplementalPlans.findElements(By.tagName("tr"));
    		for(WebElement rows : lstwebSupplementalPlansRows)
    		{
    			List<WebElement> cols = rows.findElements(By.tagName("td"));
    			
    			if(cols.size() != 0)	// this is to avoid the header read in the loop.
    			{
    				String strReadStatus = cols.get(0).getAttribute("innerText").toString().trim();
    				if(strReadStatus.contentEquals("Remove"))
    				{
    					objAccountPlans.fn_clickAnalyticsReporting(driver, webWait).click();
    					Log.info("Correct Supplemental Plan has already been Assigned to the Account: "+strAccountNum);
    					return true;	// Supplemental Plan has been assigned.
    				}
    				else
    				{
    			    	objAccountPlans.fn_clickAnalyticsReporting(driver, webWait).click();
    			    	return false;
    				}
    			}
    			else if(!(cols.iterator().hasNext()))
    			{
    				Log.error("ERROR: The Supplemental Plans table is empty or without Plan details.");
    				Reporter.log("ERROR: The Supplemental Plans table is empty or without Plan details.");
    				objAccountPlans.fn_clickAnalyticsReporting(driver, webWait).click();
    				return false;				
    			}
    		}			
    	}
    	catch (Exception exception)
    	{
    		Log.error("ERROR: The Supplemental Plan couldn't be verified due to exception: "+exception.toString());
    		Reporter.log("ERROR: The Supplemental Plan couldn't be verified due to exception: "+exception.toString());
    		throw exception;
    	}
    	
    	objAccountPlans.fn_clickAnalyticsReporting(driver, webWait).click();
    	return false;
    }
    
	
/*
	Function fn_AssignSuppPlan: Takes in the details Account Number & Plan number to make a Post call for Assigning the Supplemental Plan.
*/  
    
    public void fn_AssignSuppPlan(WebDriver driver, WebDriverWait webWait, String strAccountNum, String strPlanNum, String strAPIURL) throws Exception
    {
        AccountFunctions objAccountFunc = new AccountFunctions();
    	
    	ApiHandler api = new ApiHandler();        
        
        //TODO: Get the Full URL with the authentication details for current instance. 
        String strFullUrl = objAccountFunc.fn_GetFullUrl(driver, webWait, strAPIURL, "assign_supp_plan");
        strFullUrl = strFullUrl+"&acct_no="+strAccountNum+"&supp_plan_no="+strPlanNum+"&assignment_directive=2";
        
        api.makeSimplePostCall(strFullUrl);	// make the Post call to Assign the Supplemental Plan.
    }
    
	
/*
	Function fn_GetSupplementalPlanInstanceID: Takes in the details for Account Number and returns the Instance ID for the Supplemental Plan assigned to it.
*/  
    
    public String fn_GetSupplementalPlanInstanceID(WebDriver driver, WebDriverWait webWait, String strAccountNum, String strTestCaseName) throws Exception
    {
    	//TODO: create class object to access the page objects for accessing the Supplemental Plan. 
    	AccountPlansUsage objAccountPlans = new AccountPlansUsage();
    	String strInstanceID = null;
    	
    	webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Accounts"))).click();
    	objAccountPlans.fn_clickPlansServices(driver, webWait).click();
    	objAccountPlans.fn_getSupplementalPlansDataTable(driver, webWait);
    	objAccountPlans.fn_clickSupplementalPlans(driver, webWait).click();
    	
    	//TODO: Read the Data table and click the Plan Units to note the Plan Instance ID.
    	WebElement tblSupplementalPlans = objAccountPlans.fn_getSupplementalPlansDataTable(driver, webWait).findElement(By.tagName("tbody"));
		List<WebElement> lstwebSupplementalPlansRows = tblSupplementalPlans.findElements(By.tagName("tr"));
		for(WebElement rows : lstwebSupplementalPlansRows)
		{
			List<WebElement> cols = rows.findElements(By.tagName("td"));
			
			if(cols.size() != 0)	// this is to avoid the header read in the loop.
			{
				String strReadStatus = cols.get(0).getAttribute("innerText").toString().trim();
				if(strReadStatus.contentEquals("Remove"))
				{
					String strPlanUnits = cols.get(2).getAttribute("innerText").toString().trim();
					webWait.until(ExpectedConditions.elementToBeClickable(By.linkText(strPlanUnits))).click();
					webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id, 'DataTables_Table_')]")));
					strInstanceID = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@title, 'Link to unit instance Details')]"))).getText().trim();					
					objAccountPlans.fn_clickAnalyticsReporting(driver, webWait).click();
					Log.info("Supplemental Plan's Instance ID has been noted as '"+strInstanceID+"' and has been returned !");
					return strInstanceID;	//instance ID has been returned.
				}
				else
				{
			    	objAccountPlans.fn_clickAnalyticsReporting(driver, webWait).click();
			    	return strInstanceID;
				}
			}
			else if(!(cols.iterator().hasNext()))
			{
				Log.error("ERROR: The Supplemental Plan's Instance ID couldn't be retrieved");
				Reporter.log("ERROR: The Supplemental Plan's Instance ID couldn't be retrieved.");
				objAccountPlans.fn_clickAnalyticsReporting(driver, webWait).click();
				return strInstanceID;				
			}
		}			
    	
    	return strInstanceID;
    }

    
/*
	Function fn_VerifyParentChildAccounts: Takes in the details for Account Number and returns null if no parent child relation, 
											or concatenated string with Parent Account Number First and then Child Accounts.
*/
    
    public String fn_VerifyParentChildAccounts(WebDriver driver, WebDriverWait webWait, String strAccountNum, String strTestCaseName) throws Exception
    {
    	//TODO: Create class objects for the common functions to be used for plans verification.
    	Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
    	ParentChildDiscounting objParentChildDiscount = new ParentChildDiscounting();
    	
    	String strParentChildValues = null;
    	String strParentAccountNum = null;
    	String strChildAccountNum = null;
    	String strSelfAccountNum = null;
    	Boolean blnParentAccount = false;
    	    	
		//TODO: Search the Account number and verify if it has the Parent Child Hierarchy.
    	Boolean blnSearchStatus = objClientDefAct.AccountSearch(driver, webWait, strAccountNum);
    	if(blnSearchStatus == false)
    		VerificationMethods.assertTrue(false, "ERROR: Account Search failed for Account Number: "+strAccountNum);

    	//TODO: Scroll to the end of the page to have a capture of the Account Hierarchy Glance Table.
    	objParentChildDiscount.fn_getAccountHeirarchyGlanceTable(driver, webWait);
    	Actions objActions = new Actions(driver);
    	objActions.sendKeys(Keys.PAGE_DOWN);
    	Thread.sleep(500);
    	objActions.click(objParentChildDiscount.fn_getAccountHeirarchyGlanceTable(driver, webWait)).perform();
    	Thread.sleep(500);
    	objActions.sendKeys(Keys.PAGE_DOWN);
    	Thread.sleep(500);
    	objActions.click(objParentChildDiscount.fn_getAccountHeirarchyGlanceTable(driver, webWait)).perform();
    	Thread.sleep(500);
    	Utils.takeScreenshot(driver, strTestCaseName);
    	
    	//TODO: Read the Account Hierarchy Table and identify the Parent & Child Accounts.
    	WebElement webAccountHeirarchyTable = objParentChildDiscount.fn_getAccountHeirarchyGlanceTable(driver, webWait).findElement(By.tagName("tbody"));
    	List <WebElement> lstAccountHeirarchyTableRows = webAccountHeirarchyTable.findElements(By.tagName("tr"));
    	Integer intForloop = 0;
		for(WebElement row : lstAccountHeirarchyTableRows)
		{
			List <WebElement> cols = row.findElements(By.tagName("td"));
			intForloop = intForloop + 1;
			
			if(cols.size() >= 1)
			{
				String strLevel = cols.get(0).getAttribute("innerText").toString().trim();
				if(strLevel.equalsIgnoreCase("Parent"))	//to identify the Parent account.
				{
					strParentAccountNum = cols.get(1).getAttribute("innerText").toString().trim();
					blnParentAccount = true;
					Log.info("Navigating to the Actual Parent Account... "+strParentAccountNum);
					driver.findElement(By.linkText(strParentAccountNum)).click();
					break;
				}
				else if(strLevel.equalsIgnoreCase("Self"))	//to identify the account which is Self.
				{
					strSelfAccountNum = cols.get(1).getAttribute("innerText").toString().trim();
				}
				else if (strLevel.equalsIgnoreCase("Child"))	//to identify the Child account.
				{
					String strChildAcctNum = cols.get(1).getAttribute("innerText").toString().trim();
					if(strChildAccountNum == null)
						strChildAccountNum = strChildAcctNum;
					else						
						strChildAccountNum = strChildAccountNum+"#"+strChildAcctNum;
				}
			}
			
			//TODO: Define the Parent & Child Accounts and generate the return Accounts string for further usage.
			if(lstAccountHeirarchyTableRows.size() == intForloop)
			{
				if(blnParentAccount == false)
					strParentAccountNum = strSelfAccountNum;
				else
					strChildAccountNum = strSelfAccountNum;
				
				if(strChildAccountNum == null)
				{
					Log.info("The Account Number has no Parent-Child Relationship; returning NULL...");
					strParentChildValues = null;
					return strParentChildValues;
				}
				
				strParentChildValues = strParentAccountNum+"#"+strChildAccountNum ;
				objParentChildDiscount.fn_clickAccounts(driver, webWait).click();
				
				Log.info("The Parent Child Accounts read are (with Parent Account first followed by Child Account): "+strParentChildValues);
		    	return strParentChildValues;
			}
		}
		
		//TODO: Logic to navigate to the parent account first and get child accounts. 
		if(blnParentAccount == true)
		{
			Log.info("Account Number provided initially was of a Child Account, hence navigated to Parent Account to read all it's children accounts...");
			
			objParentChildDiscount.fn_getAccountHeirarchyGlanceTable(driver, webWait);
			objActions.sendKeys(Keys.PAGE_DOWN);
	    	Thread.sleep(500);
	    	objActions.click(objParentChildDiscount.fn_getAccountHeirarchyGlanceTable(driver, webWait)).perform();
	    	Thread.sleep(500);
	    	objActions.sendKeys(Keys.PAGE_DOWN);
	    	Thread.sleep(500);
	    	objActions.click(objParentChildDiscount.fn_getAccountHeirarchyGlanceTable(driver, webWait)).perform();
	    	Thread.sleep(500);
	    	Utils.takeScreenshot(driver, strTestCaseName);			
			
			webAccountHeirarchyTable = objParentChildDiscount.fn_getAccountHeirarchyGlanceTable(driver, webWait).findElement(By.tagName("tbody"));
	    	lstAccountHeirarchyTableRows = webAccountHeirarchyTable.findElements(By.tagName("tr"));
	    	
	    	strParentAccountNum = null;
	    	strChildAccountNum = null;
	    	
	    	intForloop = 0;
	    	for(WebElement row : lstAccountHeirarchyTableRows)
			{
				List <WebElement> cols = row.findElements(By.tagName("td"));
				intForloop = intForloop + 1;
				
				if(cols.size() >= 1)
				{
					String strLevel = cols.get(0).getAttribute("innerText").toString().trim();
					if(strLevel.equalsIgnoreCase("Self"))	//to identify the account which is Self i.e. Parent.
					{
						strParentAccountNum = cols.get(1).getAttribute("innerText").toString().trim();
					}
					else if (strLevel.equalsIgnoreCase("Child"))	//to identify the Child account.
					{
						String strChildAcctNum = cols.get(1).getAttribute("innerText").toString().trim();
						if(strChildAccountNum == null)
							strChildAccountNum = strChildAcctNum;
						else						
							strChildAccountNum = strChildAccountNum+"#"+strChildAcctNum;
					}
				}
				
				//TODO: Define the Parent & Child Accounts and generate the return Accounts string for further usage.
				if(lstAccountHeirarchyTableRows.size() == intForloop)
				{
					strParentChildValues = strParentAccountNum+"#"+strChildAccountNum ;
					objParentChildDiscount.fn_clickAccounts(driver, webWait).click();
					
					Log.info("The Parent Child Accounts read are (with Parent Account first followed by Child Account): "+strParentChildValues);
			    	return strParentChildValues;
				}
			}	    	
		}
		objParentChildDiscount.fn_clickAccounts(driver, webWait).click();
		
		Log.info("There is no Parent Child relationed account !");
    	return strParentChildValues;
    }
    
/*
	Function	: fn_GetAccountPaymentResponsibility	
	Inputs		: Takes in the details for Account Number and the Payment Resp. to be searched for. 
	Output		: Returns the Account # with the requested Payment Resp. in String or else NULL indicating no return.
	
	*** This assumes that the strAccountNum is the output from the fn_VerifyParentChildAccounts which holds list of Account Numbers. ***
	
	***	The entry point is governed by searching the Parent Account Number requested and exit Point is the Account Overview Page. ***
*/
    
    public String fn_GetAccountPaymentResponsibility(WebDriver driver, WebDriverWait webWait, String strAccountNum, String strPaymentResp, String strTestCaseName) throws Exception{
    	
    	//TODO: Create class objects for the common functions to be used for plans verification.
    	Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
    	ParentChildDiscounting objParentChildDiscount = new ParentChildDiscounting();    	
    	
    	String strReturnAccountNum = null;
    	String[] arrAccounts = null;
    	
    	if(strAccountNum == null){
    		Log.error("ERROR: There is no Parent Child Account relationship hence no need to verify Payment Responsibility !");
    		Reporter.log("ERROR: There is no Parent Child Account relationship hence no need to verify Payment Responsibility !");
    		strReturnAccountNum = null;
    		return strReturnAccountNum;
    	}
    	else{    		
    		try{
    			arrAccounts = strAccountNum.split("#");
    		}
    		catch(Exception exception){
    			Log.error("ERROR: There is no Parent Child Account relationship hence no need to verify Payment Responsibility !");
        		Reporter.log("ERROR: There is no Parent Child Account relationship hence no need to verify Payment Responsibility !");
        		strReturnAccountNum = null;
        		return strReturnAccountNum;
    		}
    	}    	
    	
		//TODO: Search the Parent Account number to proceed further to get the Child Account's with requested Payment Responsibility.
    	Boolean blnSearchStatus = objClientDefAct.AccountSearch(driver, webWait, arrAccounts[0]);
    	if(blnSearchStatus == false)
    		VerificationMethods.assertTrue(false, "ERROR: Account Search failed for Account Number: "+strAccountNum);

    	//TODO: Scroll to the end of the page to have a capture of the Account Hierarchy Glance Table.
    	objParentChildDiscount.fn_getAccountHeirarchyGlanceTable(driver, webWait);
    	Actions objActions = new Actions(driver);
    	objActions.sendKeys(Keys.PAGE_DOWN);
    	Thread.sleep(500);
    	objActions.click(objParentChildDiscount.fn_getAccountHeirarchyGlanceTable(driver, webWait)).perform();
    	Thread.sleep(500);
    	objActions.sendKeys(Keys.PAGE_DOWN);
    	Thread.sleep(500);
    	objActions.click(objParentChildDiscount.fn_getAccountHeirarchyGlanceTable(driver, webWait)).perform();
    	Thread.sleep(500);
    	Utils.takeScreenshot(driver, strTestCaseName);
    	
    	//TODO: Read the Account Hierarchy Table and identify the Parent & Child Accounts.
    	WebElement webAccountHeirarchyTable = objParentChildDiscount.fn_getAccountHeirarchyGlanceTable(driver, webWait).findElement(By.tagName("tbody"));
    	List <WebElement> lstAccountHeirarchyTableRows = webAccountHeirarchyTable.findElements(By.tagName("tr"));
    	Integer intForloop = 0;
		for(WebElement row : lstAccountHeirarchyTableRows){
			
			List <WebElement> cols = row.findElements(By.tagName("td"));			
			
			if(cols.size() >= 1){
				
				String strLevel = cols.get(0).getAttribute("innerText").toString().trim();		
				if(strLevel.equalsIgnoreCase("Self")){
					Log.info("Parent Account read, so skip this line and move to next line item...");
				}
				else{
					intForloop = intForloop + 1;
					for(Integer i = 1; i < arrAccounts.length ; i ++){					
						
						String strChildAccountNum = cols.get(1).getAttribute("innerText").toString().trim();
						if(strChildAccountNum.equals(arrAccounts[i]))					
							if(cols.get(5).getAttribute("innerText").toString().trim().contentEquals(strPaymentResp)){						
								
								Log.info("The requested responsibility '"+strPaymentResp+"' is available for Account Number: "+strChildAccountNum);
								strReturnAccountNum = strChildAccountNum;
								break;
							}
					}					
				}				
				
				if (intForloop >= lstAccountHeirarchyTableRows.size()-1){
					
					Log.error("ERROR: There is no child account with intended Payment Responsibility: "+strPaymentResp);
					Reporter.log("ERROR: There is no child account with intended Payment Responsibility: "+strPaymentResp);
					Log.info("intForloop = "+intForloop+"lstAccountHeirarchyTableRows.size = "+lstAccountHeirarchyTableRows.size());
					strReturnAccountNum = null;
				}
			}
		}
    	
		objParentChildDiscount.fn_clickAccounts(driver, webWait).click();
		return strReturnAccountNum;
    }
}