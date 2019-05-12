
/*
 Author     		:	Namrata Akarte
 Class Name			: 	ClsGetAuthenticationKey 
 Purpose     		: 	This class contains functions to update custom defined fields through API
 Date       		:	04/25/2015
 Version Information:	Version 1.0
  
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/
package appModules;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import pageObjects.AriaEOM;
import pageObjects.ConfigurationIntegrations;
import utility.Log;

public class Cls_GetAuthenticationKey {
	
	static ConfigurationIntegrations objConfigIntegrations = new ConfigurationIntegrations();
	
	/*
	 Author     		:	Namrata Akarte
	 Function Name		: 	fn_GetAuthenticationKey 
	 Purpose     		: 	This function reads auth key
	 Date       		:	07/20/2015
	 Version Information:	Version 1.0*/	
	
	public String fn_GetAuthenticationKey(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		AriaEOM objAriaEOM = new AriaEOM();
		String strClientNoAndAuthKey = "";
		
		try {
			
			//click on configuration link
			objAriaEOM.fn_clickConfiguration(driver, webWait).click();
			//click on Integrations link		
			objAriaEOM.fn_clickIntegrations(driver, webWait).click();
			//click on web services api link
			objAriaEOM.fn_clickWebServiceAPI(driver, webWait).click();
			//Read value for Client Number
			String strClientNo = objConfigIntegrations.fn_getClientNumber(driver, webWait).getText(); 
			String strGenratedClientNo = strClientNo.substring(14, 21);
			//Read value of auth key and store in the variable
			String strAuthenticationKey = objConfigIntegrations.fn_getAuthenticationKey(driver, webWait).getText();
					
			strClientNoAndAuthKey = strGenratedClientNo+"#"+strAuthenticationKey;			
		}
		catch (Exception exception) {
			
			Log.error("Exception while getting the Authentication Key for API Call's with exception as: "+exception.toString());
			Reporter.log("Exception while getting the Authentication Key for API Call's with exception as: "+exception.toString());
		}
		
		return strClientNoAndAuthKey;
	}
/*	
	
	 Author     		:	Namrata Akarte
	 Function Name		: 	fn_WriteClientDetailsAuthKey 
	 Purpose     		: 	This function enters the details as Client Num, Auth Key, Account Num on API
	 Date       		:	07/20/2015
	 Version Information:	Version 1.0
		
	public void fn_WriteClientDetailsAuthKey(WebDriver driver, WebDriverWait webWait, String strAuthKey, String strClientNo, String strAcctNum) throws InterruptedException	{
		
		//click on API link
		objTest.fn_APILink(driver, webWait).click();
		//Switch frame
		webWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
		//Enter Client number value
		objTest.fn_ClientNo(driver, webWait).sendKeys(strClientNo);
		//Enter Auth Key value
		objTest.fn_AuthKey(driver, webWait).sendKeys(strAuthKey);
		//Enter Account Number value
		objTest.fn_AccountNo(driver, webWait).sendKeys(strAcctNum);
	}
	
	
	 Author     		:	Namrata Akarte
	 Function Name		: 	fn_APIAddFields 
	 Purpose     		: 	This function enters the details as Supp Field Name, its corresponding value and directive to be used
	 Date       		:	07/20/2015
	 Version Information:	Version 1.0
		
	public void fn_APIAddFields(WebDriver driver, WebDriverWait webWait,String strFieldName, String strValue, String strXpathFieldVal, int intcnt) throws InterruptedException{
		
		//Generate Xpath for Field Name
		String strxpathFieldName1 = "supp_field_names[";
		String strxpathFieldName2 = "]";
		String strxpathFieldName = strxpathFieldName1+intcnt+strxpathFieldName2;
		System.out.println("Field Name PAth : "+strxpathFieldName);
		
		//Enter Supplier Field Name
		objTest.fn_SuppFieldNames(driver, webWait, strxpathFieldName).clear();
		objTest.fn_SuppFieldNames(driver, webWait, strxpathFieldName).sendKeys(strFieldName);
		
		
		Thread.sleep(1000);
		objTest.fn_SuppFieldNamesAddRowBtn(driver, webWait).click();
		System.out.println("Field Name is updated");
		//Generate Xpath for Field Value
		String strxpathFieldValue1 = "supp_field_values[";
		String strxpathFieldValue2 = "]";
		String strxpathFieldValue = strxpathFieldValue1+intcnt+strxpathFieldValue2;
		System.out.println("Field Value PAth : "+strxpathFieldValue);
		//Enter Supplier Field Value
		objTest.fn_SuppFieldValues(driver, webWait,strxpathFieldValue).clear();
		objTest.fn_SuppFieldValues(driver, webWait,strxpathFieldValue).sendKeys(strValue);
		objTest.fn_SuppFieldValues(driver, webWait,strxpathFieldValue).sendKeys(Keys.TAB);
		Thread.sleep(1000);
		System.out.println("From block :"+strXpathFieldVal);
		objTest.fn_SuppFieldValuesAddRowBtn(driver, webWait,strXpathFieldVal).click();
		System.out.println("Field Value is updated");
		//Select directive
		Select listDericatives = new Select(driver.findElement(By.name("supp_field_directives[0]")));
		listDericatives.selectByVisibleText("1");
	}

	
	 Author     		:	Namrata Akarte
	 Function Name		: 	fn_SubmitDetails 
	 Purpose     		: 	This function submits API form
	 Date       		:	07/20/2015
	 Version Information:	Version 1.0
		
	public void fn_SubmitDetails(WebDriver driver, WebDriverWait webWait) throws InterruptedException{
		//Select predefined url
		objTest.fn_SelectPredefinedURL(driver, webWait).click();
		Thread.sleep(1000);
		//click on Agree and Execute button
		objTest.fn_AgreeandExecuteBtn(driver, webWait).click();
	}*/
}
