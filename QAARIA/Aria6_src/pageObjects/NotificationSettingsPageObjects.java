package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSettingsPageObjects {
	
	
	private WebElement webElement = null;
		
	  //To identify  Configuration Link
	  public WebElement fn_ClickConfiguration(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Configuration")));
		  return webElement;
		}
	  
	  //To identify  Notifications Link
	  public WebElement fn_ClickNotifications(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Notifications")));
		  return webElement;
		}
	  
	  //To identify  NotificationSettings Link
	  public WebElement fn_ClickNotifSettingsLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Notification Settings")));
		  return webElement;
		}
	  
}
