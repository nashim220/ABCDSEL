/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	ConfigurationIntegrations
 Purpose     		: 	Purpose of this file is :
						1. To capture all the page objects for the Integrations under Configuration Menu in left nav.
 
 Date       		:	05/23/2016
 Modified Date		:	
 Version Information:	1.0
 
 PreCondition 		:	Role based Login required.
 Test Steps 		:	1) Capture the page objects for the objects that will be used/reused from Audit Logs.
 						
 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/

package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfigurationIntegrations {
		
	WebElement webElement;
		
	//Read Client Number
	public WebElement fn_getClientNumber(WebDriver driver,WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("web_service_creds")));
		return webElement;
	}
	
	//Read Authentication Key
	public WebElement fn_getAuthenticationKey(WebDriver driver,WebDriverWait webWait) throws InterruptedException {

		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("authkey")));
		return webElement;
	}	

}
