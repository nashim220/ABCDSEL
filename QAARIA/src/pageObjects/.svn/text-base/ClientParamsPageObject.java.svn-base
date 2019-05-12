	/*
	 Author     		:	Abhishek
	 Class Name			: 	ClientParamsPageObject
	 Purpose     		: 	Purpose of this file is :
							1. Page Objects to Validate the Client Parameters
													
	 Date       		:	05/19/2016

	 Version Information:	Version 1.0
	 */
package pageObjects;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	
	public class ClientParamsPageObject {
		
		private static WebElement webElement = null;
			
		//TODO: This is to get the Configuration Link in left navigation.
		public WebElement fn_AdminQueryLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Admin Query")));
			return webElement;
		}
		
		public WebElement fn_GoLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"adminQueryForm\"]/div/div/div[2]/input")));
			return webElement;
		}
		public WebElement fn_CloseParamLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"AdminQueryRun\"]/div[2]/span[2]/input")));
			return webElement;
		}
		public WebElement fn_SearchField(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"DataTables_Table_0_filter\"]/label/input")));
			return webElement;
		}
		public WebElement fn_NextLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"DataTables_Table_1_next\"]")));
			return webElement;
		}
		public WebElement fn_NoRecordsFound(WebDriver driver, WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"adminQueryForm\"]/div/table/tbody/tr/td")));
			return webElement;
		}
	}
