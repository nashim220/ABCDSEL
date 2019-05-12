/*
 Author     		:	Namrata Akarte
 Class Name			: 	Cls_ChangeDeleteClientDefinedFieldActns 
 Purpose     		: 	This class contains functions to update custom defined fields through API
 
 Date       		:	07/20/2015
 Modified Date		:	01/20/2016, 11/23/2015, 10/31/2015, 09/24/2015, 09/03/2015, 10/26/2015
 Modified By		:	Aashish Bhutkar/ Namrata Akarte
 Version Information:	Version 5.4
 
 Version Changes 2.0:	1. Added new custom values update method fn_UpdateCustomFields.
 						2. Some rudimentary changes to avoid any warnings while compiling. 						
 Version Changes 3.0:	1. Added new custom values method "fn_GetCustomFieldValue".
 Version Changes 4.0:	1. Updated logic for the method "fn_GetCustomFieldValue".
 						2. Added new custom values method for deleting existing value as "fn_DeleteCustomFieldValue".
 Version Changes 5.0:	1. Updated "fn_GetCustomFieldValue". Removed call to 'Account Search' function
						to avoid repetitive search when called this function in loop
 Version Changes 5.1:	1. Added Webwaits to mitigate the system timeout issues.
 						2. Modified the function for deleting the custom filed value.
 Version Changes 5.2:	1. Modified logic to avoid account search if the account is already searched. 						
 Version Changes 5.3:	1. Modified logic to avoid account search if the account is already searched.
 Version Changes 5.4:	1. Modified logic to mark Account Search is completed in case of Exception Handling. 
  
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/
package appModules;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.*;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;


/*
 * Create by Namrata to perform actions for Custom account fields
 */
public class Cls_ChangeDeleteClientDefinedFieldActns
{
	static ChangeDeleteClientDefinedFields objClientDefFields = new ChangeDeleteClientDefinedFields();
		
	public  void AddNewField(WebDriver driver,WebDriverWait webWait,String strFieldName, String strFieldVal) throws Exception
	{
		Log.info("Add New Field Value");
		objClientDefFields.fn_SearchFields(driver, webWait).sendKeys(strFieldName);
		//driver.findElement(By.xpath("//input[@type='search']")).sendKeys(fields[1]);
		objClientDefFields.fn_EditLink(driver, webWait).click();
		//driver.findElement(By.partialLinkText("Edit")).click();
		objClientDefFields.fn_FieldVal(driver, webWait).sendKeys(strFieldVal);
		//driver.findElement(By.name("field-add-input")).sendKeys(TestString_200);
		objClientDefFields.fn_AddNewBtn(driver, webWait).click();
		//driver.findElement(By.name("field-add-button")).click();
		Thread.sleep(1000);
		System.out.println("Added data in the field");
		
	}
	
	public void UpdateField(WebDriver driver,WebDriverWait webWait,String strUpdateVal) throws Exception
	{
		
		//objClientDefAct.AddNewField(driver, webWait, fields[0], TestString_200);		
		Log.info("Update Field value");
		//Click on Edit button
		objClientDefFields.fn_EditBtn(driver, webWait).click();
		//driver.findElement(By.name("field-edit-button")).click();
		//Enter updated data
		objClientDefFields.fn_UpdateField(driver, webWait).clear();	// this line has been added by Aashish Bhutkar (08/02/2015) for avoiding appending of value.
		objClientDefFields.fn_UpdateField(driver, webWait).sendKeys(strUpdateVal);
		//driver.findElement(By.name("field-edit-input")).sendKeys(TestString_79);
		//Click on update button
		objClientDefFields.fn_UpdateBtn(driver, webWait).click();
		//driver.findElement(By.name("field-update-button")).click();
		
	}
	
	public Boolean AccountSearch(WebDriver driver,WebDriverWait webWait,String strAccNum) throws Exception
	{
		Boolean blnAccountSearch = false;
		String strClickAcountDetails = "Account "+ strAccNum;
		
		Log.info("Account Search begins here");
		//Click account link
		objClientDefFields.fn_AcctLink(driver, webWait).click();
		//Click Search Link		
		objClientDefFields.fn_SearchLink(driver, webWait).click();

		//Code changed by Aashish Bhutkar to minimize time required for frequent searches.
		Thread.sleep(2000);
		try{
			driver.findElement(By.partialLinkText(strClickAcountDetails)).click();
			blnAccountSearch = true;
			webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Account Overview"))).click();;
			webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reset Password")));
			
			return blnAccountSearch;
		}
		catch (Exception exception){
			//Click Adhoc Search link
			objClientDefFields.fn_AdhocSearchLink(driver, webWait).click();
			objClientDefFields.fn_Search(driver, webWait).clear();
			objClientDefFields.fn_Search(driver, webWait).sendKeys(strAccNum);
			//click on Search button
			objClientDefFields.fn_SearchBtn(driver, webWait).click();
			Thread.sleep(2000);
			Log.info("Account Search has completed.");	// Aashish - Changed the log statement.
		}

		//Code changed by Aashish Bhutkar to handle the Failed searches & Accounts Menu in left nav.
		try{
			if(driver.findElement(By.xpath("//*[@id='adhoc_search_results']")).isDisplayed())
			{
				blnAccountSearch = true;
				//Click on Account Number link
				objClientDefFields.fn_AcctNumLink(driver, webWait, strAccNum).click();
				Log.info("Account Search has completed.");	// Aashish - Changed the log statement.
			}
		}
		catch (Exception exception1){
			blnAccountSearch = false;
			Log.info("Account Search has completed.");	// Aashish - Changed the log statement.
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
	public void fn_UpdateCustomFields(WebDriver driver, WebDriverWait webWait, String strCustomField, String strCustomFieldData, String strTestCaseName) throws Exception
	{
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
		UpdateField(driver, webWait, strCustomFieldData);
		
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
	public String fn_GetCustomFieldValue(WebDriver driver,WebDriverWait webWait, String strAccountNum, String strCustomField, String strTestCaseName) throws Exception
	{
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
		{
			VerificationMethods.assertTrue(false, "ERROR: The Custom field searched for doesn't exsist in the list..exiting with assertion for field: "+strCustomField);
		}
		
		objClientDefFields.fn_EditLink(driver, webWait).click();
		webWait.until(ExpectedConditions.elementToBeClickable(objClientDefFields.fn_EditBtn(driver, webWait)));
		Utils.takeScreenshot(driver, strTestCaseName);

		try
	    {
			webWait.until(ExpectedConditions.elementToBeClickable(objClientDefFields.fn_EditBtn(driver, webWait)));
	        driver.findElement(By.xpath("//*[@id='select-box']/option")).isEnabled();
	    }
	    catch (Exception exception)
	    {
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
	public Boolean fn_DeleteCustomFieldValue(WebDriver driver, WebDriverWait webWait, String strAccountNum, String strCustomField, String strTestCaseName) throws Exception
	{		
		//TODO: Navigate to the Client Defined Fields and delete the value if it exists.
		Boolean blnCustomFieldDeleted = false;

		objClientDefFields.fn_ClientDefLink(driver, webWait).click();		
		Thread.sleep(2000);
		objClientDefFields.fn_SearchFields(driver, webWait).sendKeys(strCustomField);
		webWait.until(ExpectedConditions.elementToBeClickable(objClientDefFields.fn_EditLink(driver, webWait)));
		Thread.sleep(2000);
		
		String strFieldName = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id,'DataTables_Table_')]/tbody/tr[1]/td[1]")))
				.getAttribute("innerText").toString().trim();		
		if(!strFieldName.contentEquals(strCustomField))
		{
			VerificationMethods.assertTrue(false, "ERROR: The Custom field searched for doesn't exsist in the list..exiting with assertion for field: "+strCustomField);
		}
		
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Delete Field"))).click();
		String strDeleteMesage = webWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='accountsSectionBottomContainer']/div/div/div[3]/p")))
									.getAttribute("innerText").toString().trim();
		if(strDeleteMesage.contains("successfully been deleted."))
		{
			blnCustomFieldDeleted = true;
			Log.info("The requested Custom Defined field's value has successfully been deleted !");
		}
		
		return blnCustomFieldDeleted;
	}		
}
