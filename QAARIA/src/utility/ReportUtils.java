/* 

 Author     		:	Namrata Akarte
 Modified By		: 	Aashish Bhutkar
 Class Name		 	: 	ReportUtils
 
 Purpose     		: 	This class contains reusable functions for report handling
 						
 Date       		:	09/16/2015
 Modified Date		:	02/05/2016, 01/11/2016, 12/09/2015, 10/27/2015, 10/21/2015, 10/15/2015, 09/24/2015
 Modified By		:	Aashish Bhutkar
 Version Information:	Version 2.3
 
 PreCondition 		:	None
 Test Steps 		:	None
 
 Version Changes 1.1:	1. updated references to the correct class i.e; ReportUtils on 24-Sep-2015
 Version Changes 1.2:	1. Changed the thread sleep for "fn_GenerateReport" to 2000ms to handle slow application.
 Version Changes 2.0:	1. Added couple of new methods "fn_ReportsCalendarControl" & "fn_GenerateReportCalendarDate" to handle reports which require Calendar Dates.
   						2. Modified the method "fn_ReadGeneratedReport" to accommodate large reports.
   						3. Added new function "fn_GetReportDateFormat" for generating date in suggested date format.
   						4. Added new function "fn_HandleReportWindows" for handling windows/frame launched for Reports.
Version Changes 2.1:	1. Updated "fn_ReportsCalendarControl" to reflect the dates as integer.
						2. Updated "fn_ReportsCalendarControl" by adding wait for the calendar control to be completely displayed.
Version Changes 2.2:	1. Updated all methods to remove the "work_frm" frame element identification since it is modified in Report Frame of UI .
Version Changes 2.3:	1. Modified the "fn_ReportsCalendarControl" to handle calendar dates based on the color coding due to repetitive values.
						 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
 
*/


package utility;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import pageObjects.ValidateDailyTieOutReport;
import pageObjects.VerifyGLLocationSegment;

public class ReportUtils {
	/*
	Function for clicking element in the sidebar of the reports window.
 */
/*public void fn_clickSideBarElement(WebDriver driver, By locator)
{fn_clickSideBarElement
	driver.switchTo().defaultContent();
	WebElement rootTree = driver.findElement(By.tagName("iframe"));
	driver.switchTo().frame(rootTree);
	List<WebElement> iFrames = driver.findElements(By.tagName("iframe"));
	WebElement parentFrame = driver.findElement(By.name("work_frm"));
	driver.switchTo().frame(parentFrame);
	driver.switchTo().frame(driver.findElement(By.name("innerModFrame")));
	driver.findElement(locator).click();
	driver.switchTo().defaultContent();
}*/
	public void fn_clickSideBarElement(WebDriver driver, By locator) throws InterruptedException
	{
		driver.switchTo().defaultContent();
		WebElement rootTree = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(rootTree);
		WebElement parentFrame = driver.findElement(By.name("work_frm"));
		driver.switchTo().frame(parentFrame);
		driver.switchTo().frame(driver.findElement(By.name("innerModFrame")));
		driver.findElement(By.linkText("Sungard AS")).click();
		Thread.sleep(1000);
		driver.findElement(locator).click();
	}

	public void fn_clickReportElement(WebDriver driver, By locator) throws InterruptedException
	{
		driver.switchTo().defaultContent();
		WebElement workAreaParentFrame = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(workAreaParentFrame);
		WebElement workAreaChildFrame = driver.findElement(By.name("work_frm"));
		driver.switchTo().frame(workAreaChildFrame);
		WebElement workArea = driver.findElement(By.name("innerWorkArea"));
		driver.switchTo().frame(workArea);
		Thread.sleep(1000);
		driver.findElement(locator).click();
	}
	
	public void fn_clickViewReport(WebDriver driver, By locator) throws InterruptedException
	{
		driver.switchTo().defaultContent();
		WebElement workAreaParentFrame = driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(workAreaParentFrame);
		WebElement workAreaChildFrame = driver.findElement(By.name("work_frm"));
		driver.switchTo().frame(workAreaChildFrame);
		WebElement workArea = driver.findElement(By.name("innerWorkArea"));
		driver.switchTo().frame(workArea);
		WebElement postedReportDisplay = driver.findElement(By.name("postedReportDisplay"));
		driver.switchTo().frame(postedReportDisplay);
		Thread.sleep(1000);
		driver.findElement(locator).click();	
		
		
	}
/*
	Function for clicking an element in the main window of the Report Menu
 */

/*public void fn_clickReportElement(WebDriver driver, By locator)
{
	driver.switchTo().defaultContent();
	WebElement rootTree = driver.findElement(By.tagName("iframe"));
	driver.switchTo().frame(rootTree);
	WebElement parentFrame = driver.findElement(By.name("work_frm"));
	driver.switchTo().frame(parentFrame);
	driver.switchTo().frame(driver.findElement(By.name("innerWorkArea")));
	driver.findElement(locator).click();
	driver.switchTo().defaultContent();
}*/


/*
	Function for sending a value to the main window of the Report Menu
 */

public void fn_SetValueReportElement(WebDriver driver, By locator, String strValue) throws InterruptedException
{
	/*driver.switchTo().defaultContent();
	WebElement rootTree = driver.findElement(By.tagName("iframe"));
	driver.switchTo().frame(rootTree);
	WebElement parentFrame = driver.findElement(By.name("work_frm"));
	driver.switchTo().frame(parentFrame);
	driver.switchTo().frame(driver.findElement(By.name("innerWorkArea")));//Updated By Aashish B: changed the locator to "name" instead of "tagname".
	driver.findElement(locator).sendKeys(strValue);
	driver.switchTo().defaultContent();*/
	driver.switchTo().defaultContent();
	WebElement workAreaParentFrame = driver.findElement(By.tagName("iframe"));
	driver.switchTo().frame(workAreaParentFrame);
	WebElement workAreaChildFrame = driver.findElement(By.name("work_frm"));
	driver.switchTo().frame(workAreaChildFrame);
	WebElement workArea = driver.findElement(By.name("innerWorkArea"));
	driver.switchTo().frame(workArea);
	Thread.sleep(1000);
	driver.findElement(locator).click();
	
	
	
}




/*
 	Author     		:	Aashish Bhutkar
 	Purpose     	: 	Function for clicking the multiple options on ribbon of report element.	
	
*/

public void fn_clickRibbonReportElement(WebDriver driver, By locator)
{
	driver.switchTo().defaultContent();
	WebElement rootTree = driver.findElement(By.tagName("iframe"));
	driver.switchTo().frame(rootTree);
/*	WebElement parentFrame = driver.findElement(By.name("work_frm"));
	driver.switchTo().frame(parentFrame);*/
	driver.switchTo().frame(driver.findElement(By.name("innerWorkArea")));
	driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='postReportActions']")));
	driver.findElement(locator).click();
	driver.switchTo().defaultContent();
}

/*
	Author     		:	Namrata Akarte
	Purpose     	:	Function for generating the Report of category SGAS/Standard/Sales
*/	
public void fn_GenerateReport(WebDriver driver,String strFolderName, String strReportName, String strStartDate, String strEndDate) throws InterruptedException
{	
	VerifyGLLocationSegment objVerifyGLLocationSegment = new VerifyGLLocationSegment();
	ReportUtils objUtils = new ReportUtils();
	driver.findElement(By.linkText("Reports")).click();// Click Reports link
	Thread.sleep(2000);
	driver.findElement(By.linkText("Run Reports")).click();//Click Run Reports link		
	Thread.sleep(2000);
	objUtils.fn_clickSideBarElement(driver, By.partialLinkText(strFolderName));// //Click folder link
	Thread.sleep(2000);
	objUtils.fn_clickSideBarElement(driver, By.linkText(strReportName));//Click Report link		
	Thread.sleep(2000);		
	objUtils.fn_clickReportElement(driver, objVerifyGLLocationSegment.fn_clickSwitchReportPreviewMode());//Switch to Report Preview Mode
	Thread.sleep(2000);
	objUtils.fn_SetValueReportElement(driver, objVerifyGLLocationSegment.fn_getStartDateDropDown(), strStartDate);//Select Start Date as "Current Day"
	Thread.sleep(2000);
	objUtils.fn_SetValueReportElement(driver, objVerifyGLLocationSegment.fn_getEndDateDropDown(), strEndDate);//Select End Date as "current Day"
	Thread.sleep(2000);
	objUtils.fn_clickReportElement(driver, objVerifyGLLocationSegment.fn_clickPreviewReport());//click on Preview Report button
	
}	

/*
 	Author     		:	Aashish Bhutkar
 	Purpose     	:	Function for Reading the data on the Generated Report; Returns Webelement which can be later used for reading data.
*/

public WebElement fn_ReadGeneratedReport(WebDriver driver)
{	
	//TODO: Create objects to classes for page objects to access the items under it.
	ValidateDailyTieOutReport objDailyTieOut = new ValidateDailyTieOutReport();
	
	WebElement webReportContents = null;	
	String strParentWindow = null;
	String strGeneratedReportWindow = null;
	Set <String> strWindowHandles = null;
	
	driver.switchTo().defaultContent();
	WebElement rootTree = driver.findElement(By.tagName("iframe"));
	driver.switchTo().frame(rootTree);
/*	WebElement parentFrame = driver.findElement(By.name("work_frm"));
	driver.switchTo().frame(parentFrame);*/
	driver.switchTo().frame(driver.findElement(By.name("innerWorkArea")));
	WebElement webReportDisplay = driver.findElement(By.xpath("//frame[@name='postedReportDisplay']"));
	driver.switchTo().frame(webReportDisplay);

	//TODO: This change has been done to suit the reports which do not have data table once report is generated !
	try	
	{		
		webReportContents = driver.findElement(By.xpath("/html/body/table[2]")).findElement(By.tagName("tbody"));
	}
	catch (Exception exception)
	{
		Log.info("There is no immediate data table for this report generated, hence click View Large Report Link !");
		try
		{
			objDailyTieOut.fn_clickViewLargeReport(driver).click();
			Thread.sleep(2000);
			
			strParentWindow = driver.getWindowHandle();
			strWindowHandles = driver.getWindowHandles();
			strWindowHandles.remove(strParentWindow);		
			strGeneratedReportWindow = strWindowHandles.iterator().next();
			driver.switchTo().window(strGeneratedReportWindow);
			
			webReportDisplay = driver.findElement(By.xpath("//frame[@name='postedReportDisplay']"));		
			driver.switchTo().frame(webReportDisplay);
			webReportContents = driver.findElement(By.xpath("/html/body/table[2]")).findElement(By.tagName("tbody"));
		}
		catch (Exception exception1)
		{
			Log.error("ERROR: There is some issue with the View Large Report link to be clicked !");
			Reporter.log("ERROR: There is some issue with the View Large Report link to be clicked !");
		}	
	}
	
	return webReportContents;
}

//To click Preview report 
public WebElement fn_clickPreviewReport(WebDriver driver,WebDriverWait webWait) throws InterruptedException  {
							 
	WebElement webElement = driver.findElement(By.xpath("/html/body/form[1]/table[2]/tbody/tr/td/input"));
	return webElement;
}

//To get report table 
public WebElement fn_getReportTable(WebDriver driver,WebDriverWait webWait) throws InterruptedException  {
							 
	WebElement webElement = driver.findElement(By.xpath("/html/body/table[2]"));
	return webElement;
}

//To click Close Report Generation 
public WebElement fn_clickCloseReportGeneration(WebDriver driver,WebDriverWait webWait) throws InterruptedException  {
							 
	WebElement webElement = driver.findElement(By.xpath("/html/body/div[5]/div[1]/button"));
	return webElement;
}
/*
	Author     		:	Aashish Bhutkar
	Purpose     	:	Function for downloading file at the defined location.
	Inputs			: 	driver - webdriver for the current window.
	 					strFullyQualifiedFileName - Is the Disk Location & filename for the csv file to be downloaded.
*/

public void fn_ReportsFileDownload(WebDriver driver, String strFullyQualifiedFileName)
{
	VerifyGLLocationSegment objGLLocation = new VerifyGLLocationSegment();
	ReportUtils objUtils = new ReportUtils();
	
	try
	{
		//TODO: Create a Robot object to handle the window's 'Save As' popup for saving file.
		Robot rbDownloadFile = new Robot();
		objUtils.fn_clickRibbonReportElement(driver, objGLLocation.fn_clickDownload());
		Thread.sleep(15000);
		driver.switchTo().activeElement();	//set focus on the save as dialog box.        		
	    
		StringSelection strselDownloadLocation = new StringSelection(strFullyQualifiedFileName);        		
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard.setContents(strselDownloadLocation, strselDownloadLocation);
	    
	    rbDownloadFile.keyPress(KeyEvent.VK_CONTROL);	//pass on the filename.
	    rbDownloadFile.keyPress(KeyEvent.VK_V);
	    rbDownloadFile.keyRelease(KeyEvent.VK_V);
	    rbDownloadFile.keyRelease(KeyEvent.VK_CONTROL);
	    
	    Thread.sleep(5000);
	    
	    rbDownloadFile.keyPress(KeyEvent.VK_ALT);	//click the Save button on the 'Save As' popup. 
	    rbDownloadFile.keyPress(KeyEvent.VK_S);
	    rbDownloadFile.keyRelease(KeyEvent.VK_S);
	    rbDownloadFile.keyRelease(KeyEvent.VK_ALT);
	    
	    Thread.sleep(15000);
	    driver.switchTo().defaultContent();			
	}
	catch (Exception exception)
	{
		Log.error("ERROR: Exception thrown while downloading file with details: "+exception.toString());
		Reporter.log("ERROR: Exception thrown while downloading file with details: "+exception.toString());
	}
}


/*
Author     		:	Aashish Bhutkar
Purpose     	:	Function for handling the calendar controls for Reports.
Inputs			: 	driver - Web driver for the Calendar Control window.
					webWait - Web driver wait.
					strClickDate - Click date in format "yyyy-MM-dd".
*/

	public void fn_ReportsCalendarControl(WebDriver driver, WebDriverWait webWait, String strClickDate) throws Exception
	{
		//TODO: Create objects to classes for page objects to access the items under it.
		ValidateDailyTieOutReport objDailyTieOut = new ValidateDailyTieOutReport();
		
		String[] arrMonthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		
		try
		{
			//TODO: Read the Month & Year displayed in the Calendar Control.
			String strCurrentCalendarMonth = objDailyTieOut.fn_getCalendarCurrentMonth(driver, webWait).getAttribute("innerText").toString().trim();
			String[] arrCalendarMonth = strCurrentCalendarMonth.split(" ");
			String strCalendarMonth = arrCalendarMonth[0].toString().trim();
			Integer intCalendarYear = Integer.parseInt(arrCalendarMonth[1].toString().trim());
			
			//TODO: Get the actual year, month & date from the date to be passed to Calendar Control.			
			String[] arrDate = strClickDate.split("-");			
			Integer intYear = Integer.parseInt(arrDate[0].toString().trim());
			Integer intMonth = Integer.parseInt(arrDate[1].toString().trim());
			String strDate = arrDate[2].toString().trim();
			
			//TODO: Logic for handling the year on Calendar Control.
			Integer intYearDifference = intYear - intCalendarYear;
			if(intYearDifference > 0)	//Suggested date is for next year(s).
			{
				for(int i = 0; i < intYearDifference; i++)
				{
					objDailyTieOut.fn_clickCalendarNextYear(driver, webWait).click();
					Thread.sleep(1500);
				}
			}
			else if (intYearDifference < 0)	//Suggested date is of previous year(s).
			{
				intYearDifference = intYearDifference * (-1);
				for(int i = 0; i < intYearDifference; i++)
				{
					objDailyTieOut.fn_clickCalendarPrevYear(driver, webWait).click();
					Thread.sleep(1500);
				}
			}
			
			//TODO: Logic for handling the month on Calendar Control.
			Integer intCalendarMonthIndex = Arrays.asList(arrMonthNames).indexOf(strCalendarMonth) + 1;			
			Integer intMonthDifference = intMonth - intCalendarMonthIndex;
			if(intMonthDifference > 0)	//Suggested Month is of future.
			{
				for(int i = 0; i < intMonthDifference; i++)
				{
					objDailyTieOut.fn_clickCalendarNextMonth(driver, webWait).click();
					Thread.sleep(1500);
				}
			}
			else if (intMonthDifference < 0)	//Suggested Month is of past.
			{
				intMonthDifference = intMonthDifference * (-1);
				for(int i = 0; i < intMonthDifference; i++)
				{
					objDailyTieOut.fn_clickCalendarPrevMonth(driver, webWait).click();
					Thread.sleep(1500);
				}
			}
			
			//TODO: Since the Year and Month have been set, lets click the Date on Calendar now.
			Integer intDate = Integer.parseInt(strDate);
			String strCalDateXPath = "//td[@bgcolor='#ffffff' or @bgcolor='#DADADA' or @bgcolor='#ffb6c1']//a//font[text()='"+intDate+"']";
			driver.findElement(By.xpath(strCalDateXPath)).click();
		}
		catch (Exception exception)
		{
			Log.error("ERROR: Exception thrown while getting Calendar Control details: "+exception.toString());
			Reporter.log("ERROR: Exception thrown while getting Calendar Control details: "+exception.toString());
		}		
	}
	
	
/*
	Author     		:	Aashish Bhutkar
	Purpose     	:	Function for generating the Report of category SGAS/Standard/Sales based on Calendar Control
*/	
	public void fn_GenerateReportCalendarDate(WebDriver driver, WebDriverWait webWait, String strFolderName, String strReportName
			, String strStartDate, String strEndDate) throws Exception
	{	
		//TODO: Create objects to classes for page objects to access the items under it.
		VerifyGLLocationSegment objVerifyGLLocationSegment = new VerifyGLLocationSegment();
		ValidateDailyTieOutReport objDailyTieOut = new ValidateDailyTieOutReport();
		ReportUtils objReportUtils = new ReportUtils();
		
    	String strParentWindow = null;
    	String strCalendarControlWindow = null;
    	Set <String> strWindowHandles = null;
		
    	webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reports"))).click();
		webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Run Reports"))).click();
		Thread.sleep(2500);
		objReportUtils.fn_clickSideBarElement(driver, By.linkText(strFolderName));// //Click folder link
		Thread.sleep(2500);
		objReportUtils.fn_clickSideBarElement(driver, By.linkText(strReportName));//Click Report link		
		Thread.sleep(2500);
		objReportUtils.fn_clickReportElement(driver, objVerifyGLLocationSegment.fn_clickSwitchReportPreviewMode());//Switch to Report Preview Mode
		Thread.sleep(2500);
		objReportUtils.fn_clickReportElement(driver, objDailyTieOut.fn_clickSelectDateByCalendar(driver, webWait));		
		Thread.sleep(2500);
		
		strParentWindow = driver.getWindowHandle();
		
		try
		{
			//TODO: Click the Start Date Calendar Control and proceed to set Start date.
			objReportUtils.fn_clickReportElement(driver, objDailyTieOut.fn_clickStartDateCalendar(driver, webWait));
			Thread.sleep(5000);
    		strWindowHandles = driver.getWindowHandles();    		
    		strWindowHandles.remove(strParentWindow);
    		strCalendarControlWindow = strWindowHandles.iterator().next();
    		driver.switchTo().window(strCalendarControlWindow);			
    		objReportUtils.fn_ReportsCalendarControl(driver, webWait, strStartDate);		
			Thread.sleep(2000);
			driver.switchTo().window(strParentWindow);
			
			//TODO: Click the End Date Calendar Control and proceed to set End date.
			objReportUtils.fn_clickReportElement(driver, objDailyTieOut.fn_clickEndDateCalendar(driver, webWait));
			Thread.sleep(5000);
			strParentWindow = driver.getWindowHandle();
    		strWindowHandles = driver.getWindowHandles();    		
    		strWindowHandles.remove(strParentWindow);
    		strCalendarControlWindow = strWindowHandles.iterator().next();
    		driver.switchTo().window(strCalendarControlWindow);			
			objReportUtils.fn_ReportsCalendarControl(driver, webWait, strEndDate);
			Thread.sleep(2000);			
			driver.switchTo().window(strParentWindow);
		}
		catch (Exception exception)
		{
			Log.error("ERROR: Exception thrown while accessing Calendar Controls : "+exception.toString());
			Reporter.log("ERROR: Exception thrown while accessing Calendar Controls: "+exception.toString());
		}
		
		objReportUtils.fn_clickReportElement(driver, objVerifyGLLocationSegment.fn_clickPreviewReport());//click on Preview Report button				
	}
	

/*
	Author     		:	Aashish Bhutkar
	Purpose     	:	Function for generating the strings for validating data on Reports.
	
	Inputs			:	Takes the Date in String format which is "yyyy-mm-dd" ONLY.
	Output			:	Generates a string in the date format which will have month ONLY as mm format.	
*/	
	public String fn_GetReportDateFormat(String strDateString, String strDateFormat)
	{
		String strFormattedDate = null;
		String[] arrDateStringRcvd = null;
		
		//TODO: Created a switch case so that the format can added as case in near future as per requirements.
		switch(strDateFormat)
		{
			case "mm-dd-yyyy":
				arrDateStringRcvd = strDateString.split("-");
				arrDateStringRcvd[1].replace("0", "");
				strFormattedDate = arrDateStringRcvd[1]+"-"+arrDateStringRcvd[2]+"-"+arrDateStringRcvd[0];
				break;				
		}
		
		return strFormattedDate;
	}
	

/*
 	Author     		:	Aashish Bhutkar
	Function: fn_HandleReportWindows - To Handle various windows/frame launched during Reports verification.
	Inputs: driver and the Parent Window String. 
*/	
	public void fn_HandleReportWindows (WebDriver driver, String strParentWindow) throws Exception
	{
		//TODO: Create objects to classes for page objects to access the items under it.
		VerifyGLLocationSegment objGLLocation = new VerifyGLLocationSegment();
		
		Set <String> strWindowHandles = null;
		String strGeneratedReportWindow = null;
		
       	//TODO: logic to handle the window created due to large contents of the Report.
		try
		{
			strWindowHandles = driver.getWindowHandles();
			strWindowHandles.remove(strParentWindow);
				if(strWindowHandles.iterator().hasNext())
					strGeneratedReportWindow = strWindowHandles.iterator().next();
				else
					strGeneratedReportWindow = strParentWindow;			
			if(strGeneratedReportWindow.contentEquals(strParentWindow))
			{
	           	driver.switchTo().defaultContent();
	           	Thread.sleep(2000);
	    		driver.findElement(objGLLocation.fn_clickReportFrameCloseButton()).click();	//click close on the report frame.
	           	driver.switchTo().window(strParentWindow);
			}
			else
			{
				driver.switchTo().window(strGeneratedReportWindow);
				driver.close();	//close the window/tab which was launched for generating large report.
				driver.switchTo().window(strParentWindow);
				driver.switchTo().defaultContent();
	           	Thread.sleep(2000);
	    		driver.findElement(objGLLocation.fn_clickReportFrameCloseButton()).click();	//click close on the report frame.			
			}			
		}
		catch (Exception exception)
		{
			Log.error("ERROR: Issue in handling the Reports windows !");
			Reporter.log("ERROR: Issue in handling the Reports windows !");
			throw exception;
		}
	}	
}
