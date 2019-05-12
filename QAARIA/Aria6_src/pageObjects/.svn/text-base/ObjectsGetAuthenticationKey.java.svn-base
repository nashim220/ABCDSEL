
/*
 Author     		:	Namrata Akarte
 Class Name			: 	ObjectsGetAuthenticationKey 
 Purpose     		: 	To locate page objects for handling update custom account fields API
 Date       		:	07/20/2015
 Version Information:	Version 1.0
 
 PreCondition 		:   1. Data to be populated in the "TestCaseDetails" " worksheet for excel "TestData.xls".

 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/
package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Created by Namrata Akarte to handle Client Defined fields
 */
public class ObjectsGetAuthenticationKey {
	
private WebElement webElement = null;
	
	//Identify Configuration Link
	public WebElement fn_ConfigurationLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Configuration")));
		return webElement;
	}
	
	//Identify Integrations Link
	public WebElement fn_IntegrationsLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Integrations")));
		return webElement;
	}
	
	//Identify Web Service API Link
	public WebElement fn_WebServiceAPILink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Web Service API")));
		return webElement;
	}
	
	//Identify Generate Key link
	/*public WebElement fn_GenearetKeyLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Generate Key")));
		return webElement;
	}
	
	//Click Ok button on Warnung message window
	public WebElement fn_OkBtn(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		//webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button'])[3]")));
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Yes']")));
		return webElement;
	}*/
	//Read Client Number
	public WebElement fn_ClientNumber(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("web_service_creds")));
		return webElement;
	}
	
	//Read Authentication Key
	public WebElement fn_AuthenticationKey(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("authkey")));
		return webElement;
	}
	
	//Identify Link API Live
	public WebElement fn_APILink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("API Live")));
		return webElement;
	}
	
	//Identify Client No field
	public WebElement fn_ClientNo(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name("client_no")));
		return webElement;
	}
	
	//Identify Account No field
	public WebElement fn_AccountNo(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name("acct_no")));
		return webElement;
	}
		
	
	//Identify Auth key field
	public WebElement fn_AuthKey(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name("auth_key")));
		return webElement;
	}
	
	//Identify supp_field_names field
	public WebElement fn_SuppFieldNames(WebDriver driver,WebDriverWait webWait,String strxpathFieldName) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name(strxpathFieldName)));
		return webElement;
	}
	
	//Identify supp_field_names add button
	public WebElement fn_SuppFieldNamesAddRowBtn(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[49]/td[3]/button")));
		return webElement;
	}
	
	//Identify supp_field_values field
	public WebElement fn_SuppFieldValues(WebDriver driver,WebDriverWait webWait,String strxpathFieldValue) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name(strxpathFieldValue)));
		return webElement;
	}
	
	//Identify supp_field_values add button
	public WebElement fn_SuppFieldValuesAddRowBtn(WebDriver driver,WebDriverWait webWait,String strXpathFieldVal) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strXpathFieldVal)));
		return webElement;
	}
	
	//Identify supp_field_directives add button
	/*public WebElement fn_SuppFieldDirectives(WebDriver driver,WebDriverWait webWait,String intDirectiveVal) throws InterruptedException
	{
		Select listDericatives = new Select(driver.findElement(By.name("supp_field_directives[0]")));
		return webElement;
	}*/
	
	//Identify Agree and Execute Button
	public WebElement fn_AgreeandExecuteBtn(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name("submit")));
		return webElement;	
	}
	
	//Identify Agree and Execute Button
	public WebElement fn_SelectPredefinedURL(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("env_select_1")));
		return webElement;	
	}
	//Identify the frame
	/*public WebDriver fn_MainFrame(WebDriver driver,WebDriverWait webWait)
	{
		webElement =webWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("mainFrame")));
		return webElement;
	}*/
}
