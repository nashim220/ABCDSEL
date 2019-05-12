package com.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.sun.media.sound.InvalidFormatException;



public class SeleniumFunctions 
{

	WebDriver driver; 
	
	public SeleniumFunctions(WebDriver driver) 
	{
			this.driver=driver;
	}
	String path = "C:\\TestData\\Test_Data.xlsx";
	public String getExcelData(String sheetName , int rowNum , int colNum) throws InvalidFormatException, IOException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException{
		   
		  FileInputStream fis = new FileInputStream(path);
		  Workbook wb = WorkbookFactory.create(fis);
		  Sheet sh = wb.getSheet(sheetName);
		  Row row = sh.getRow(rowNum);
		   String data = row.getCell(colNum).getStringCellValue();

	     return data;
	 
	}
	
	public static String getCurrentDate()
	{
		String PATTERN="dd-MMM-yyyy";
		SimpleDateFormat dateFormat=new SimpleDateFormat();
		dateFormat.applyPattern(PATTERN);
		String date1 = dateFormat.format(Calendar.getInstance().getTime());
		return date1;
	}
	
	public static String getdiffCurrentDate(String strdate)
	{
		String PATTERN="dd-MMM-yy";
		SimpleDateFormat dateFormat=new SimpleDateFormat();
		dateFormat.applyPattern(PATTERN);
		String date1 = dateFormat.format(strdate);
		return date1;
	}
	 public static void captureScreenShot(WebDriver driver){

		  // Take screenshot and store as a file format
		  File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
		  // now copy the  screenshot to desired location using copyFile method

		 FileUtils.copyFile(src, new File("C:/TestData/"+System.currentTimeMillis()+".png"));
		       }

		catch (IOException e)

		{

		System.out.println(e.getMessage());

		    }

		}
	
	/*
	To verify whether element is present on page or not
	*/
	public boolean IsElementPresent(String locator, String locatorvalue) 
	{
	    try 
	    { 	
	    	if(locator.equalsIgnoreCase("id"))
			{
	    		driver.findElement(By.id(locatorvalue));
	    		
			}
	    	

	    	if(locator.equalsIgnoreCase("css"))
			{
	    		driver.findElement(By.cssSelector(locatorvalue));
	    		
			}
	    	

	    	if(locator.equalsIgnoreCase("xpath"))
			{
	    		driver.findElement(By.xpath(locatorvalue));
	    		
			}
	    	

	    	if(locator.equalsIgnoreCase("name"))
			{
	    		driver.findElement(By.name(locatorvalue));
	    		
			}
	    	
	    	
	    	if(locator.equalsIgnoreCase("linkText"))
			{
	    		driver.findElement(By.linkText(locatorvalue));
	    		
			}
	    	
	    	return true;
	    	
	    }
	    catch (NoSuchElementException e) 
	    {
	      return false;
	    }
	}

	
	/*
	For getting element's text from page
	*/
	public String GetElementText(String locator, String locatorvalue)
	{
		//WebDriverWaitMethod(locator,locatorvalue);
		
		String temptext=null;
		if(locator.equalsIgnoreCase("id"))
		{
			temptext= driver.findElement(By.id(locatorvalue)).getText();
		}
		
		if(locator.equalsIgnoreCase("name"))
		{
			temptext= driver.findElement(By.name(locatorvalue)).getText();
		}
		
		if(locator.equalsIgnoreCase("css"))
		{
			temptext= driver.findElement(By.cssSelector(locatorvalue)).getText();
		}
		
		
		if(locator.equalsIgnoreCase("xpath"))
		{
			temptext= driver.findElement(By.xpath(locatorvalue)).getText();
		}
		
		
		if(locator.equalsIgnoreCase("linkText"))
		{
			temptext= driver.findElement(By.linkText(locatorvalue)).getText();
		}
		
		return temptext;
	}
	

	
	
	/*
	For clicking on specified item from dropdown list
	*/
	public void ToClickOnSpecifiedItemFromDropdown(String divid, String ItemToClick)
	{
		String tempcss= "div#" + divid+ " li";
		int count = driver.findElements(By.cssSelector(tempcss)).size();
		
		
		for (int i = 2; i < count; i++)
		{
			
			String locatorvalue = "div#" + divid+ " li:nth-of-type(" + i+ ")";
			String temp= driver.findElement(By.cssSelector(locatorvalue)).getText();
			if(temp.equals(ItemToClick))
				{
					//System.out.println("Got required element");
					driver.findElement(By.cssSelector(locatorvalue)).click();
					break;
				}
				
		}
	}

	
	/*
	To navigate given Url
	*/
	public void ToGoToUrl(String Url) throws InterruptedException
	{
		Thread.sleep(10000);
		driver.get(Url);
	}
	
	
	/*
	To select value from drop down list using Visible text
	 */
	public void SelectValueFromDropdownList(String locatortype, String locatorvalue, String visibletext)
	{
		if(locatortype.equalsIgnoreCase("id"))
		{
			new Select(driver.findElement(By.id(locatorvalue))).selectByVisibleText(visibletext);
		}
		
		else if(locatortype.equalsIgnoreCase("css"))
		{
			new Select(driver.findElement(By.cssSelector(locatorvalue))).selectByVisibleText(visibletext);
		}
		
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			new Select(driver.findElement(By.xpath(locatorvalue))).selectByVisibleText(visibletext);
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			new Select(driver.findElement(By.name(locatorvalue))).selectByVisibleText(visibletext);
		}
		
	}
	
	
	/*
	To select value from drop down list using Index
	 */
	public void SelectValueFromDropdownListUsingIndex(String locatortype, String locatorvalue,int index)
	{
		if(locatortype.equalsIgnoreCase("id"))
		{
			new Select(driver.findElement(By.id(locatorvalue))).selectByIndex(index);
		}
		
	}
	
	
	/*
	To get selected value from drop down list
	 */
	public String GetSelectedValueFromDropdownList(String locatortype, String locatorvalue)
	{
		String selectedtext = null;
		if(locatortype.equalsIgnoreCase("id"))
		{
			WebElement element = new Select(driver.findElement(By.id(locatorvalue))).getFirstSelectedOption();
			selectedtext = element.getText();
			
		}
		if(locatortype.equalsIgnoreCase("css"))
		{
			WebElement element = new Select(driver.findElement(By.cssSelector(locatorvalue))).getFirstSelectedOption();
			selectedtext = element.getText();
			
		}
		
		if(locatortype.equalsIgnoreCase("name"))
		{
			WebElement element = new Select(driver.findElement(By.name(locatorvalue))).getFirstSelectedOption();
			selectedtext = element.getText();
			
		}
		
		return selectedtext;
		
		
	}	
	
	/*
	Accept JS alert and get it's text
	*/
	public String AcceptAlertAndGetText() throws InterruptedException
	{
		Alert alert = driver.switchTo().alert();
        String AlertText = alert.getText();
        alert.accept();
        Thread.sleep(4000);
        
        return AlertText;
	}
	
	
	/*
	Dismiss JS alert and get it's text
	*/
	public String DismisAlertAndGetText() throws InterruptedException
	{
		Alert alert = driver.switchTo().alert();
        String AlertText = alert.getText();
        alert.dismiss();
        
        Thread.sleep(4000);
        return AlertText;
	}
	
	
	/*
	To get current page url
	*/
	public String ToGetCurrentPageUrl()
	{
		return driver.getCurrentUrl();
		
	}
	//getText()
	public String ToGetCurretText(String Locater)
	{
		return  driver.findElement(By.cssSelector(Locater)).getText();
		
	}	

	/*To mouse hover on element*/
	public void ToMouseHover(String locator, String locatorvalue ) throws InterruptedException
	{
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.cssSelector(locatorvalue));
		action.moveToElement(element).build().perform();
		Thread.sleep(5000);
		
		
		String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                "arguments[0].dispatchEvent(evObj);";

		WebElement element1 = driver.findElement(By.cssSelector(locatorvalue));
		((JavascriptExecutor)driver).executeScript(javaScript, element1);
		
		Thread.sleep(5000);
		
		/*if(locator.equalsIgnoreCase("id"))
		{
			element = driver.findElement(By.id(locatorvalue));
		}
		
		if(locator.equalsIgnoreCase("name"))
		{
			element = driver.findElement(By.name(locatorvalue));
		}
		
		if(locator.equalsIgnoreCase("css"))
		{
			element =driver.findElement(By.cssSelector(locatorvalue));
		}
		
		if(locator.equalsIgnoreCase("xpath"))
		{
			element =driver.findElement(By.xpath(locatorvalue));
		}
		*/
		
		
	}
	
	public void VerifyDropDownOptions(String locator, String locatorvalue, String[] dropdownvalues)
	{
		WebElement dropdown = null;
		int count = 0;

		 
		if(locator.equals("css"))
		{
			dropdown = driver.findElement(By.cssSelector(locatorvalue));
		}
		
		if(locator.equals("id"))
		{
			dropdown = driver.findElement(By.id(locatorvalue));
		}
		
		if(locator.equals("name"))
		{
			dropdown = driver.findElement(By.name(locatorvalue));
		}
		
		Select select = new Select(dropdown);

		List<WebElement> options = select.getOptions();
		for (WebElement we : options) {
		    for (int i = 0; i < dropdownvalues.length; i++) {
		         if (we.getText().equals(dropdownvalues[i])) {
		             count++;
		              System.out.println(dropdownvalues[i]);
		          }
		      }
		}
		if (count == dropdownvalues.length) 
		 {
		   	System.out.println("Success !! Correct Dropdown values present");
			Reporter.log("Success !! Correct Dropdown values present"); 
		} 
		else 
		{
		   	System.out.println("Failure !! Not displaying Correct values for dropdown");
			Reporter.log("Failure !! Not displaying Correct values for dropdown"); 
					
			Assert.fail();
		}
	}	
	/*
	 To verify whether element is Jump  on Page  or not
	*/
	
	public void jumpToPage(String locator ){	
	    List<WebElement> PageNo = driver.findElements(By.cssSelector(locator));
	    if (PageNo.size() == 0){
	        List<WebElement> nextPage = driver.findElements(By.xpath(locator));
	        if(nextPage.size() >= 1){
	            nextPage.get(0).click();
	        }
	    }
	    else
	    	PageNo.get(0).click();
	    
	}

	/*
	 To verify whether element is present on page or not
	*/
	public boolean IsElementPresent(WebElement element) 
	{
		boolean b= false;
	    try 
	    { 	
	    	if (element.isDisplayed())
	    		{
	    		b=true;
	    		}
	    	
	    	
	    }
	    catch (NoSuchElementException e) 
	    {
	      b=false;
	    }
	    
	    return b;
	}
	
}