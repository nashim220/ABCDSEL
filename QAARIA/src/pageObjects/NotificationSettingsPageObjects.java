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
	  
	//To identify  Provisioning  Settings Link
	  public WebElement fn_ClickProvisioningSettingsLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Provisioning Settings")));
		  return webElement;
		}
	  
	//To identify  Events Link
	  public WebElement fn_ClickEventsLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Events")));
		  return webElement;
		}
	  
	//To identify  Email Templates Link
	  public WebElement fn_ClickEmailTemplatesLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Email Templates")));
		  return webElement;
		}
	  
	//To identify  Notification Template Group Link
	  public WebElement fn_ClickNotificationTemplateGroupLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Notification Template Group")));
		  return webElement;
		}
	  
	//To identify  Notification Template Group Link
	  public WebElement fn_ClickStatementSendingLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Statement Sending")));
		  return webElement;
		}
}
