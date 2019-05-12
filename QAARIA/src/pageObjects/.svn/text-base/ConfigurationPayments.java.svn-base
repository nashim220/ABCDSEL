/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	ConfigurationPayments 
 Purpose     		: 	Purpose of this file is :
						1. To capture all the page objects for the Payments under Configuration Menu in left nav.
 
 Date       		:	05/23/2016
 Modified Date		:	
 Version Information:	1.0
 
 PreCondition 		:	Role based Login required.
 Test Steps 		:	1) Capture the page objects for the objects that will be used/reused from Aria EOM Left Nav.
 						
 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/

package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfigurationPayments {
	
	WebElement webElement;
	
	// this is to identify the Payment Method  data table.
	public WebElement fn_getConfigPaymentMethodDataTable(WebDriver driver, WebDriverWait webWait) throws IOException {
			
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='paymentMethodTable']")));
		return webElement;
	}
}
