/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	ConfigurationAuditLogs
 Purpose     		: 	Purpose of this file is :
						1. To capture all the page objects for the Audit Logs under Configuration Menu in left nav.
 
 Date       		:	05/23/2016
 Modified Date		:	
 Version Information:	1.0
 
 PreCondition 		:	Role based Login required.
 Test Steps 		:	1) Capture the page objects for the objects that will be used/reused from Audit Logs.
 						
 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/

package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfigurationAuditLogs {
		
	WebElement webElement;
	
	/*******************************	Page Objects for Web Service API	*******************************/
	
	//to identify the Request Date/Time: From Date.
	public WebElement fn_clickRequestFromDate(WebDriver driver, WebDriverWait webWait) throws IOException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/input[contains(@name, '[from_date]')]")));
		return webElement;
	}
	
	//to identify the Request Date/Time: To Date.
	public WebElement fn_clickRequestToDate(WebDriver driver, WebDriverWait webWait) throws IOException	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/input[contains(@name, '[to_date]')]")));
		return webElement;
	}

	//to identify the Request Date/Time: From Date Hour.
	public WebElement fn_setRequestDateHour(WebDriver driver, WebDriverWait webWait) throws IOException	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//dd[@class='ui_tpicker_hour']//span/input")));
		return webElement;
	}

	//to identify the Request Date/Time: From Date Minute.
	public WebElement fn_setRequestDateMinute(WebDriver driver, WebDriverWait webWait) throws IOException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//dd[@class='ui_tpicker_minute']//span/input")));
		return webElement;
	}
	
	//to identify the Request Date/Time Done button.
	public WebElement fn_clickRequestDateTimeDone(WebDriver driver, WebDriverWait webWait) throws IOException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//button[text()='Done']")));
		return webElement;
	}
	
	//to identify the Add button.
	public WebElement fn_clickAdd(WebDriver driver, WebDriverWait webWait) throws IOException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='addDiv']/a[text()='Add']")));
		return webElement;
	}
	
	//to identify the drop Down for Search Fields.
	public Select fn_selectFieldDropDown(WebDriver driver, WebDriverWait webWait) throws IOException	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='webservicelog_search_fields']")));
		Select objSelectField = new Select(webElement);	
		
		return objSelectField;
	}
	
	//to identify the text field for the Search Field selected.
	public WebElement fn_getTextField(WebDriver driver, WebDriverWait webWait) throws IOException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='webservicelog_acct_no_clone']")));
		return webElement;
	}
	
	//to identify the Search button on the Web Service API page.
	public WebElement fn_clickSearchButton(WebDriver driver, WebDriverWait webWait) throws IOException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Search']")));
		return webElement;
	}
	
	//to identify the data table for the Web Service API logs.
	public WebElement fn_getDataTableAPILogs(WebDriver driver, WebDriverWait webWait) throws IOException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='webSearchLogTable']")));
		return webElement;
	}
	
	//to identify the data table for the Details tab page.
	public WebElement fn_getDataTableDetailsTab(WebDriver driver, WebDriverWait webWait) throws IOException	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='tabs-3']/table")));
		return webElement;
	}
	
	//to identify the Input Parameters tab for the result set.
	public WebElement fn_clickInputParameters(WebDriver driver, WebDriverWait webWait) throws IOException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li/a[text()='Input Parameters']")));
		return webElement;
	}

	//to identify the data table for the Input Parameters tab page.
	public WebElement fn_getDataTableInputParametersTab(WebDriver driver, WebDriverWait webWait) throws IOException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='tabs-2']/table")));
		return webElement;
	}

	//to identify the Data table with the Plan units values in it.
	public WebElement fn_clickPlansEdit(WebDriver driver, WebDriverWait webWait) throws IOException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='button' and @value='Edit']")));
		return webElement;
	}
	
	//to identify the Data table with the Plan units values in it.
	public WebElement fn_getPlanInstanceFieldsTable(WebDriver driver, WebDriverWait webWait) throws IOException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[contains(@id, 'DataTables_Table_') and contains(@aria-describedby, '_info')]")));
		return webElement;
	}
	
	/*******************************	End of Page Objects for Web Service API	*******************************/
	
}
