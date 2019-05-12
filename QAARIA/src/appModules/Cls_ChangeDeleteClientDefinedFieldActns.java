/*
 Author     		:	Namrata Akarte
 Class Name			: 	Cls_ChangeDeleteClientDefinedFieldActns 
 Purpose     		: 	This class contains functions to update custom defined fields through API
  
 Date       		:	04/22/2016
 Modified By		:	Aashish Bhutkar
 
 Modified Date		:	05/11/2016
 Version Information:	Version 2.0
 
 Modified By		:	Abhishek
 Modified Date		:	05/12/2016
 Version Information:	Version 2.1
 
 Version Changes 2.0:	1. Added missing function "fn_UpdateCustomFields" to Update Custom Defined Fields.
 						2. Updated references for few page objects.
 
 Version Changes 2.1:	1. Added Line to click Client Defined Fields in "Update Field" Function.
   
 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/

package appModules;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.*;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;


public class Cls_ChangeDeleteClientDefinedFieldActns {
	
	static ChangeDeleteClientDefinedFields objClientDefFields = new ChangeDeleteClientDefinedFields();
	
	//This function is used to enter value for the Client Defined Field		
	public  void AddNewField(WebDriver driver,WebDriverWait webWait,String strFieldName, String strFieldVal) throws Exception {

		Log.info("Add New Field Value");
		objClientDefFields.fn_SearchFields(driver, webWait).sendKeys(strFieldName);
		objClientDefFields.fn_EditLink(driver, webWait).click();

		try{
			
			if (objClientDefFields.fn_EditBtn(driver, webWait).isDisplayed()){
				
				objClientDefFields.fn_FieldVal(driver, webWait).sendKeys(strFieldVal);
				objClientDefFields.fn_AddNewBtn(driver, webWait).click();
				Thread.sleep(1000);
				objClientDefFields.fn_ReturnPrevScreen(driver, webWait).click();
			}
			
		}
		
		catch(Exception e){
				
			Select options = new Select(driver.findElement(By.name("inSuppFieldVals")));
			options.selectByVisibleText(strFieldVal);
			objClientDefFields.fn_BtnSubmit(driver, webWait).click();				
		}
		
		Thread.sleep(1000);
		System.out.println("Added data in the field");
		
	}
	
	//This function is used to update existing value of the Client Defined Field
	
	public void UpdateField(WebDriver driver,WebDriverWait webWait,String strFieldName, String strUpdateVal) throws Exception {
			
		//Click Client Defined Fields
		objClientDefFields.fn_ClientDefLink(driver, webWait).click();			
		Thread.sleep(2000);
		
		//Click on Edit button		
		objClientDefFields.fn_SearchFields(driver, webWait).sendKeys(strFieldName);
		objClientDefFields.fn_EditLink(driver, webWait).click();
				
		try {
			
			if (objClientDefFields.fn_AddNewBtn(driver, webWait).isDisplayed()){
				
				webWait.until(ExpectedConditions.elementToBeClickable(By.tagName("OPTION"))).click();
				
				objClientDefFields.fn_EditBtn(driver, webWait).click();
				objClientDefFields.fn_UpdateField(driver, webWait).clear();	// this line has been added by Aashish Bhutkar (08/02/2015) for avoiding appending of value.
				objClientDefFields.fn_UpdateField(driver, webWait).sendKeys(strUpdateVal);
				
				//Click on update button
				objClientDefFields.fn_UpdateBtn(driver, webWait).click();
				
				Thread.sleep(1000);
				//Returns to the previous screen
				objClientDefFields.fn_ReturnPrevScreen(driver, webWait).click();						
				Log.info("Updated value for the Client Defined Field : "+strFieldName);
			}		
		}		
		catch(Exception e){
			
			Select options = new Select(driver.findElement(By.name("inSuppFieldVals")));
			options.selectByVisibleText(strUpdateVal);
			objClientDefFields.fn_BtnSubmit(driver, webWait).click();			
			Log.info("Updated value for the Client Defined Field : "+strFieldName);
		}		
	}
	
	// This function is to be used for Account Search based on the Account Number shared.
	public Boolean AccountSearch(WebDriver driver,WebDriverWait webWait,String strAccNum) throws Exception {

		Boolean blnAccountSearch = false;
		String strClickAcountDetails = "Account "+ strAccNum;
		
		AriaEOM objAriaEOM = new AriaEOM();
		
		Log.info("Account to be searched : "+strAccNum);
		objAriaEOM.fn_clickAccounts(driver, webWait).click();
		objAriaEOM.fn_clickAccountsSearch(driver, webWait).click();

		Thread.sleep(2000);
		try {
			
			driver.findElement(By.partialLinkText(strClickAcountDetails)).click();
			blnAccountSearch = true;
			objAriaEOM.fn_clickAccountOverview(driver, webWait).click();
			objAriaEOM.fn_clickAcctOvrvwResetPassword(driver, webWait);
			Log.info("Account "+strAccNum+ " has been searched successfully");
			
			return blnAccountSearch;
		}
		catch (Exception exception) {

			objAriaEOM.fn_clickAdhocSearchLink(driver, webWait).click();
			objClientDefFields.fn_Search(driver, webWait).clear();
			objClientDefFields.fn_Search(driver, webWait).sendKeys(strAccNum);
			objClientDefFields.fn_SearchBtn(driver, webWait).click();
			Thread.sleep(2000);
		}

		try {
			if(driver.findElement(By.xpath("//*[@id='adhoc_search_results']")).isDisplayed()) {
				
				blnAccountSearch = true;
				objClientDefFields.fn_AcctNumLink(driver, webWait, strAccNum).click();
				Log.info("Account "+strAccNum+ " has been searched successfully");
			}
		}
		catch (Exception exception1) {
			
			blnAccountSearch = false;
			Log.info("Account Search has completed.");
			VerificationMethods.assertTrue(false, "ERROR: Account Search failed for Account Number: "+strAccNum);
		}
		
		return blnAccountSearch;	
	}
	

/*	
	 Author     		:	Aashish Bhutkar
	 Purpose     		: 	Purpose of the method fn_UpdateCustomFields is :
							1. To update ONLY Client Defined Fields.
							2. Use the Custom Data requested for update.
*/	
	public void fn_UpdateCustomFields(WebDriver driver, WebDriverWait webWait, String strCustomField, String strCustomFieldData, String strTestCaseName) throws Exception {

		//TODO: Navigate to the Client Defined Fields and update ONLY the parameter requested for with custom mentioned Data.
		Utils.takeScreenshot(driver, strTestCaseName);
		objClientDefFields.fn_ClientDefLink(driver, webWait).click();		
		Thread.sleep(2000);
		objClientDefFields.fn_SearchFields(driver, webWait).sendKeys(strCustomField);
		webWait.until(ExpectedConditions.elementToBeClickable(objClientDefFields.fn_EditLink(driver, webWait)));
		Thread.sleep(2000);
		objClientDefFields.fn_EditLink(driver, webWait).click();
		webWait.until(ExpectedConditions.elementToBeClickable(objClientDefFields.fn_EditBtn(driver, webWait)));
		Utils.takeScreenshot(driver, strTestCaseName);
		
		webWait.until(ExpectedConditions.elementToBeClickable(By.tagName("OPTION"))).click();
		Log.info("Updating the custom defined field using reusable code.");
		UpdateField(driver, webWait, strCustomField, strCustomFieldData);
		
		Thread.sleep(2000);
		webWait.until(ExpectedConditions.elementToBeClickable(objClientDefFields.fn_EditBtn(driver, webWait)));
		Utils.takeScreenshot(driver, strTestCaseName);
		objClientDefFields.fn_ReturnPrevScreen(driver, webWait).click();
	}

/*	
	 Author     		:	Aashish Bhutkar
	 Purpose     		: 	Purpose of the method fn_GetCustomFieldValue is :
							1. Read the value for the Custom Field and return it.
*/	
	public String fn_GetCustomFieldValue(WebDriver driver,WebDriverWait webWait, String strAccountNum, String strCustomField, String strTestCaseName) throws Exception 	{

		//TODO: Search the account which we need & Navigate to the Client Defined Fields.
		String strCustomFieldValue = null;	
		
		objClientDefFields.fn_ClientDefLink(driver, webWait).click();
		Thread.sleep(2000);
		objClientDefFields.fn_SearchFields(driver, webWait).sendKeys(strCustomField);
		webWait.until(ExpectedConditions.elementToBeClickable(objClientDefFields.fn_EditLink(driver, webWait)));
		Thread.sleep(2000);
		
		String strFieldName = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'DataTables_Table_')]/tbody/tr[1]/td[1]")))
										.getAttribute("innerText").toString().trim();		
		if(!strFieldName.contentEquals(strCustomField))
			VerificationMethods.assertTrue(false, "ERROR: The Custom field searched for doesn't exsist in the list..exiting with assertion for field: "+strCustomField);
	
		objClientDefFields.fn_EditLink(driver, webWait).click();
		webWait.until(ExpectedConditions.elementToBeClickable(objClientDefFields.fn_EditBtn(driver, webWait)));
		Utils.takeScreenshot(driver, strTestCaseName);

		try {
			
			webWait.until(ExpectedConditions.elementToBeClickable(objClientDefFields.fn_EditBtn(driver, webWait)));
	        driver.findElement(By.xpath("//*[@id='select-box']/option")).isEnabled();
	    }
	    catch (Exception exception) {
	    	
			Thread.sleep(2000);
			objClientDefFields.fn_ReturnPrevScreen(driver, webWait).click();
			
			return strCustomFieldValue;
	    }
		
		webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='select-box']/option"))).click();
		strCustomFieldValue = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='select-box']/option"))).getAttribute("value").toString().trim();
		
		Thread.sleep(1500);
		objClientDefFields.fn_ReturnPrevScreen(driver, webWait).click();

		return strCustomFieldValue;
	}	

	
/*	
	 Author     		:	Aashish Bhutkar
	 Purpose     		: 	Purpose of the method fn_DeleteCustomFieldValue is :
							1. To delete the already existing value of a given CustomField.
*/	
	public Boolean fn_DeleteCustomFieldValue(WebDriver driver, WebDriverWait webWait, String strAccountNum, String strCustomField, String strTestCaseName) throws Exception {

		//TODO: Navigate to the Client Defined Fields and delete the value if it exists.
		Boolean blnCustomFieldDeleted = false;
	
		objClientDefFields.fn_ClientDefLink(driver, webWait).click();		
		
		objClientDefFields.fn_SearchFields(driver, webWait).sendKeys(strCustomField);
		webWait.until(ExpectedConditions.elementToBeClickable(objClientDefFields.fn_EditLink(driver, webWait)));
		Thread.sleep(2000);
		
		String strFieldName = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'DataTables_Table_')]/tbody/tr[1]/td[1]")))
				.getAttribute("innerText").toString().trim();		
		if(!strFieldName.contentEquals(strCustomField))
			VerificationMethods.assertTrue(false, "ERROR: The Custom field searched for doesn't exsist in the list..exiting with assertion for field: "+strCustomField);
		
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Delete Field"))).click();
		String strDeleteMesage = webWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='accountsSectionBottomContainer']/div/div/div[3]/p")))
									.getAttribute("innerText").toString().trim();
		if(strDeleteMesage.contains("successfully been deleted.")) {

			blnCustomFieldDeleted = true;
			Log.info(strCustomField + " value has successfully been deleted !");
		}
		
		return blnCustomFieldDeleted;
	}
	
	// This function is used for reading value for a Custom Field suggested.
	public String fn_ReadCustomFieldVal(WebDriver driver, WebDriverWait webWait, String strAccountNum, String strCustomField, String strTestCaseName) throws Exception {
		
		objClientDefFields.fn_ClientDefLink(driver, webWait).click();		
		
		objClientDefFields.fn_SearchFields(driver, webWait).sendKeys(strCustomField);
		WebElement table = driver.findElement(By.xpath("//*[contains(@id,'DataTables_Table_')]")).findElement(By.tagName("tbody"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		
		String strFieldVal="";
		
		List<WebElement> cols = rows.get(0).findElements(By.tagName("td"));
		
		strFieldVal = cols.get(1).getText();			
			
		return strFieldVal;
	}
}