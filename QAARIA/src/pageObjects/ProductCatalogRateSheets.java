/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	ProductCatalogRateSheets 
 Modified			: 	Abhishek
 Purpose     		: 	Purpose of this file is :
						1. To capture all the page objects for the Product Catalog rate sheets changes.
						
 Date       		:	06/26/2015
 Modified Date		:	06/17/2016, 11/25/2015, 09/06/2015
 Version Information:	Version 3.0
 Version Information:	Version 4.0
 
 PreCondition 		:	Role based Login required.
 Test Steps 		:	1) Capture the page objects for the objects that will be used for 
 						changing the rates for the plans of Product Catalog.
 
 Version Changes 2.0:	1. Changes to the parameters, class name and method names as per the standards.
 						2. Change the references to the page objects.
 Version Changes 3.0:	1. Added Page object for the Krona Rates collapsible.
  						2. Changed the identifier for the Close button on the Successful Completion Message Box.
 Version Changes 4.0:	1. Changed Rates table function
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package pageObjects;

import org.openqa.selenium.By;	
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductCatalogRateSheets 
{
	
	public static By fn_clickProductsLeftNav()	// this is to identify the Products menu in left nav.
	{
		return By.partialLinkText("Products"); 
		//By.xpath("//*[@id=\'ui-accordion-1-header-3\']/a/div");
	}
	
	public static By fn_clickPlansLeftNav()	// this is to identify the Plans sub-menu in left nav.
	{
		return By.xpath("//*[contains(@href, '/ui/app.php/Plans')]");
	}
	
	public static By fn_getSupplementalPlansDataTable(int i)	// this is to identify the Supplemental Plans data table.
	{
		return By.xpath("//*[contains(@id, 'DataTables_Table_"+i+"')]");
	}
	
	public static By fn_clickRatesTab()	// this is to identify the Rates Tab on the Plan details page.
	{
		return By.partialLinkText("Rates");
	}
	
	public static By fn_RatesTable(int i)	// this is to identify the Rates Tab on the Plan details page.
	{
		String strtblXpath1 = "//*[@id=\"planRateSchedules\"]/div[4]/div[";
		String strtblXpath2 = strtblXpath1+i+"]/div/div[2]/div[5]/div/table";
		return By.xpath(strtblXpath2);
	}
	
	public static By fn_clicRatestTab2()	// this is to identify the Rates Tab after revisiting on the Plan details page.
	{
		return By.xpath("//*[@id=\'ui-id-13\']");
	}
	
	public static By fn_getUSDRAM()	// this is to identify the RAM field which is to be updated. 
	{
		return By.xpath("//*[@id=\'plan_newRateSchedules_0_newRateScheduleRates_0_ratePerUnit\']");
	}
 	 
	
	public static By fn_getSEKWin2012R2()	// this is to identify the Cent OS price filed for SEK.
	{
		return By.xpath("//*[@id=\'plan_newRateSchedules_4_newRateScheduleRates_3_ratePerUnit\']");
	}
	
	public static By fn_clickSaveButton()	// this is to identify the Save Button on Rates Tab.
	{
		return By.xpath("//*[contains(@value, 'Save')]");
		// By.xpath("//*[@id=\'submit_button\']");
	}
	
	public static By fn_clickCloseCompletedMessage()	// this is to identify the Close button on Completed Message box.
	{
		return By.xpath("//span[contains(text(), 'Close')]");
	}
	
	//to identify the Krona Rates Collapsible.
	public WebElement fn_clickKronaRates(WebDriver driver, WebDriverWait webWait) throws Exception{
		
		WebElement webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='planRateSchedules']/div[4]/div[5]")));
		return webElement;
	}
}
