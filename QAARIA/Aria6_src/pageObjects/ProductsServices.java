package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsServices {
	
	public ProductsServices(){
		
	}
	
private WebElement webElement = null;
	
	//To Identify Products link
	public WebElement fn_ProductsLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Products")));
		return webElement;
	}
	
	//To Identify Services link
	public WebElement fn_ServicesLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Services")));
		return webElement;
	}
	
	//To Identify SerachField 
	public WebElement fn_SearchField(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']")));
		return webElement;
	}
	
	//To Identify Tax Group list
	public WebElement fn_TaxGroupList(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("Services_tax_groups")));
		return webElement;
	}
	
	//To Identify Usage Type List
	public WebElement fn_UsageTypeList(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("Services_usage_type")));
		return webElement;
	}
}
