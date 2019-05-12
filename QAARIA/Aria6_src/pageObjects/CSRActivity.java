package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CSRActivity {
	public CSRActivity(){
		
	}
	
	private static WebElement webElement = null;

	//Identify link analytics and reporting
	public WebElement fn_AnalyticsReportingLink(WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Analytics and Reporting")));
		return webElement;
	}
	
	//Identify link Reports
	public WebElement fn_ReportsLink(WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reports")));
		return webElement;
	}	
	
	//Identify link RunReports
	public WebElement fn_RunReportsLink(WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Run Reports")));
		return webElement;
	}	
		
	//Identify link CSR Activity
	public WebElement fn_CSRActivityLink(WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("CSR Activity")));
		return webElement;
	}	
	
	//Identify Default button
	public WebElement fn_DefaultBtn(WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("default_view_icon")));
		return webElement;
	}	
	
	//Identify chose a CSR weblist
	public Select fn_CSRList(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		Select webList = new Select(driver.findElement(By.name("inUserID"))) ;
		return webList;
	}		
	
	//Identify from date calender icon
	public WebElement fn_FromDate(WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("img[alt=\"Click Here to select a start date.\"]")));
		return webElement;
	}
	
	//Identify Date To calender icon
	public WebElement fn_ToDate(WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("img[alt=\"Click Here to select a end date.\"]")));
		return webElement;
	}
	
	//Identify Activity Details
	public Select fn_ActivityDetailsList(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		Select webList = new Select(driver.findElement(By.name("inSearchScope"))) ;
		return webList;
	}
	
	//Identify Search button
	public WebElement fn_SearchBtn(WebDriverWait webWait) throws InterruptedException
	{
		
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type=\"submit\"]")));
		return webElement;
	}	
	
	
}
