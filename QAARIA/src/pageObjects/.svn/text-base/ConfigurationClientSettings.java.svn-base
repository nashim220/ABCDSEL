/*
 Author     		:	Nashim Khan
 
 Class Name			: 	ConfigurationClientSettings 
 Purpose     		: 	Purpose of this file is :
						1. To capture all the page objects for the Configuration > ClientSettings Functionality of an Account.
 
 Date       		:	06/06/2016
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

public class ConfigurationClientSettings {

	
	WebElement webElement;
	
	/*******************************	Page Objects for Account Fields	*******************************/
	
	// this is to identify the Payment Method  data table.
	public WebElement fn_getAccountFieldsDataTable(WebDriver driver, WebDriverWait webWait) throws IOException {
					
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='suppfieldsTable']")));
		return webElement;
	}

	// this is to identify the Field values  for Account fields
	public WebElement fn_getAccountFieldVal(WebDriver driver, WebDriverWait webWait , Integer intDiv) throws IOException {
					
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tabs-1']/div["+intDiv+"]/span[@class='textblock']")));		
		return webElement;
	}
	
	// this is to identify Description 
	public WebElement fn_getDescription(WebDriver driver, WebDriverWait webWait) throws IOException {
						
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='supplementalfield_field_supp_text']")));
		return webElement;
	}	

	// this is to identify Required.
	public WebElement fn_getRequired(WebDriver driver, WebDriverWait webWait) throws IOException {
							
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='supplementalfield_required_ind']//option[@selected='selected']")));
		return webElement;
	}	

	// this is to identify Minimum Selection
	public WebElement fn_getMinSelection(WebDriver driver, WebDriverWait webWait) throws IOException {
							
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='supplementalfield_min_num_vals']")));
		return webElement;
	}

	// this is to identify Maximum Selection
	public WebElement fn_getMaxSelection(WebDriver driver, WebDriverWait webWait) throws IOException {
								
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='supplementalfield_max_num_vals']")));
		return webElement;
	}		
	
	// this is to identify the Close Button 
	public WebElement fn_clickClose(WebDriver driver, WebDriverWait webWait) throws IOException {
					
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='button' and	@value='Close']")));
		return webElement;
	}	
	
	/*******************************	End of Page Objects for Account Fields	*******************************/
		
	/*******************************	Page Objects for Product Fields	*******************************/
	
	// this is to identify Field Name For Product 
	public WebElement fn_getProductFieldName(WebDriver driver, WebDriverWait webWait) throws IOException {
									
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='supplementObjectFields_fieldName']")));
		return webElement;
	}	
	
	// this is to identify Field Description For Product 
	public WebElement fn_getProductDescription(WebDriver driver, WebDriverWait webWait) throws IOException {
										
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='supplementObjectFields_description']")));
		return webElement;
	}
	
	// this is to identify Input Type For Product 
	public WebElement fn_getProductInputType(WebDriver driver, WebDriverWait webWait) throws IOException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='supplementObjectFields_formInputType']//option[@selected='selected']")));
		return webElement;
	}
	
	// this is to identify Field Category For  All Product 
	public WebElement fn_getProductFieldCategory(WebDriver driver, WebDriverWait webWait) throws IOException {
										
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='ms-elem-selectable']")));
		return webElement;
	}	
	
	// this is to identify Field Category For cloud Service
	public WebElement fn_getCloudServiceFieldCategory(WebDriver driver, WebDriverWait webWait) throws IOException {
											
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='ms-list']//li[@class='ms-elem-selected']")));
		return webElement;
	}	
	
	// this is to identify Data Type  For Product 
	public WebElement fn_getProductDataType(WebDriver driver, WebDriverWait webWait) throws IOException {
												
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='supplementObjectFields_datatype']//option[@selected='selected']")));
		return webElement;
	}	
	
	// this is to identify Minimum Number of Selections  For Product 
	public WebElement fn_getProductMinNumOfSelections(WebDriver driver, WebDriverWait webWait) throws IOException {
													
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='supplementObjectFields_minNumVals']")));
		return webElement;
	}		
	
	// this is to identify Maximum Number Selections Allowed  For Product 
	public WebElement fn_getProductMaxNumSelectionsAllowed(WebDriver driver, WebDriverWait webWait) throws IOException {
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='supplementObjectFields_maxNumVals']")));
		return webElement;
	}	
	
	// this is to identify Object Type Tab For Product 
	public WebElement fn_clickProductObjectTypeTab(WebDriver driver, WebDriverWait webWait) throws IOException {
			
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Object Types' and contains(@href,'tabs')]")));
		return webElement;
	}

	// this is to identify Object Type  Table  For Product 
	public WebElement fn_getProductObjectTypesTable(WebDriver driver, WebDriverWait webWait) throws IOException {
				
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='tabs-3']/table/tbody")));		
		return webElement;
	}
	
	/*******************************	End of Page Objects for Product Fields	*******************************/	
			
	/*******************************	Page Objects for Product Field Categories	*******************************/	
	
	// this is to identify Category Name on All Products.
	public WebElement fn_getCategoryName(WebDriver driver, WebDriverWait webWait) throws IOException {
						
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='ProductFieldCategory_field_category_name']")));		
		return webElement;
	}
	
	// this is to identify Category Name on All Products.
	public WebElement fn_getCategoryDescription(WebDriver driver, WebDriverWait webWait) throws IOException {
			
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='ProductFieldCategory_field_category_description']")));		
		return webElement;
	}
	
	// this is to identify Product Fields which are Selected. 
	public WebElement fn_getProductFieldsSelected(WebDriver driver, WebDriverWait webWait) throws IOException {
							
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='ms-ProductFieldCategory_fieldMaps']/div[@class='ms-selection']/ul")));		
		return webElement;
	}	
	
	// this is to identify Plan Instance Fields which are Selected.
	public WebElement fn_getPlanInstanceFieldsSelected(WebDriver driver, WebDriverWait webWait) throws IOException {
								
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='ms-ProductFieldCategory_planFieldMaps']/div[@class='ms-selection']/ul")));		
		return webElement;
	}
	
	/*******************************	End of Page Objects for Product Field Categories	*******************************/
	
}