package appModules;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.NotificationSettingsPageObjects;

public class Cls_NotificationSettingsActns {
	
	public void fn_notificationSettings(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
	
	NotificationSettingsPageObjects NSPObjcts = new NotificationSettingsPageObjects();
	
	NSPObjcts.fn_ClickConfiguration(driver, webWait).click();
	NSPObjcts.fn_ClickNotifications(driver, webWait).click(); 
	NSPObjcts.fn_ClickNotifSettingsLink(driver, webWait).click();

	}                         

}
