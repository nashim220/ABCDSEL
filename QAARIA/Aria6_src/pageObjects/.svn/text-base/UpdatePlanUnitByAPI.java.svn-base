/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	UpdatePlanUnitByAPI 
 Purpose     		: 	Purpose of this file is :
						1. To capture all the page objects required for verification of the Plan Units value
						updated via API for the requested Account Number.
						2. Also the page objects on the API Logs will be identified.
 
 Date       		:	08/17/2015
 Modified Date		:	11/16/2015
 Version Information:	Version 1.1
 
 Version Changes 1.1:	1. Added few more page objects for getting the specific Audit Logs. 
 
 PreCondition 		:	Role based Login required.
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/


package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.VerificationMethods;

public class UpdatePlanUnitByAPI extends VerificationMethods
{	
	public static WebElement webElement = null;
	
	//to identify the Accounts link in the left nav.
	public WebElement fn_clickAccounts(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Accounts")));		
		return webElement;
	}
	
	//to identify the Plans & Services sub-menu link in the left nav for Accounts.
	public WebElement fn_PlansServices(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Plans & Services")));
		return webElement;
	}
	
	//to identify the data table on the given page.
	public WebElement fn_getMasterPlanDataTable(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@id, 'DataTables_Table_')]")));
		return webElement;
	}
		
	//to identify the Supplemental Plan tab on this Plans & Services page.
	public WebElement fn_getSupplementalPlans(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Supplemental Plans")));
		return webElement;
	}
	
	//to identify the Data table with the Plan units values in it.
	public WebElement fn_getProductFieldsTable(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'accountsSectionBottomContainer\']/div/div/form/div[1]/dl/table[2]")));
		return webElement;
	}
	
	//to identify the Configuration menu in the left nav.
	public WebElement fn_clickConfiguration(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Configuration")));
		return webElement;
	}
	
	//to identify the Audit Logs sub-menu for COnfiguration.
	public WebElement fn_clickAuditLogs(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Audit Logs")));
		return webElement;
	}
	
	//to identify the Web Service API link in the sub-menu for Audit Logs.
	public WebElement fn_clickWebServiceAPI(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Web Service API")));
		return webElement;
	}
	
	//to identify the Cross mark on the Web Service API page.
	public WebElement fn_clickCrossWebServiceAPI(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@title, 'Delete')]")));
		return webElement;
	}
	
	//to identify the drop Down for Search Fields.
	public WebElement fn_getSelectFieldDropDown(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'webservicelog_search_fields\']")));
		return webElement;
	}
	
	//to identify the text field for the Search Field selected.
	public WebElement fn_getTextField(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'webservicelog_acct_no_clone\']")));
		return webElement;
	}
	
	//to identify the Search button on the Web Service API page.
	public WebElement fn_clickSearchButton(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'webservicelog\']/div/div[2]/a")));
		return webElement;
	}
	
	//to identify the data table for the Web Service API logs.
	public WebElement fn_getDataTableAPILogs(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'webSearchLogTable\']")));
		return webElement;
	}
	
	//to identify the data table for the Details tab page.
	public WebElement fn_getDataTableDetailsTab(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'tabs-3\']/table")));
		return webElement;
	}
	
	//to identify the Input Parameters tab for the result set.
	public WebElement fn_clickInputParameters(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Input Parameters")));
		return webElement;
	}
	
	//to identify the data table for the Input Parameters tab page.
	public WebElement fn_getDataTableInputParametersTab(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'tabs-2\']/table")));
		return webElement;
	}
	
	//to identify the Close button.
	public WebElement fn_clickCloseButton(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@title=\'Close this section\']")));
		return webElement;
	}
	
	//to identify the Request Date/Time: From Date.
	public WebElement fn_clickRequestFromDate(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@name, '[from_date]')]")));
		return webElement;
	}	

	//to identify the Request Date/Time: From Date Hour.
	public WebElement fn_setRequestFromDateHour(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ui-datepicker-div']/div[2]/dl/dd[2]/div/span/input")));
		return webElement;
	}

	//to identify the Request Date/Time: From Date Minute.
	public WebElement fn_setRequestFromDateMinute(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ui-datepicker-div']/div[2]/dl/dd[3]/div/span/input")));
		return webElement;
	}	
	
	//to identify the Request Date/Time: To Date.
	public WebElement fn_clickRequestToDate(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@name, '[to_date]')]")));
		return webElement;
	}
	
	//to identify the Request Date/Time: To Date Hour.
	public WebElement fn_setRequestToDateHour(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ui-datepicker-div']/div[2]/dl/dd[2]/div/span/input")));
		return webElement;
	}
	
	//to identify the Request Date/Time: To Date Minute.
	public WebElement fn_setRequestToDateMinute(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ui-datepicker-div']/div[2]/dl/dd[3]/div/span/input")));
		return webElement;
	}
	
	//to identify the Request Date/Time Done button.
	public WebElement fn_clickRequestDateTimeDone(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ui-datepicker-div']/div[3]/button[2]")));
		return webElement;
	}
	
	//to identify the Add button.
	public WebElement fn_clickAdd(WebDriver driver, WebDriverWait webWait) throws IOException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='addDiv']/a")));
		return webElement;
	}	
}
