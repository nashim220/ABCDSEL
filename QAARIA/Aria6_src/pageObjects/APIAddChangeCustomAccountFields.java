package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class APIAddChangeCustomAccountFields {

private WebElement webElement = null;

	//Identify API Live link
	public WebElement fn_APILiveLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("API Live")));
		return webElement;
	}
	
	//Identify client No field
	public WebElement fn_ClientNo(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("client_no")));
		return webElement;
	}
	
	//Identify Authentication Key field
	public WebElement fn_AuthKey(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("auth_key")));
		return webElement;
	}
	
	
}
