package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountSequenceGroup {
	
	WebElement webElement;
	
	//To identify Sequence Group tab
	public WebElement fn_clickSequenceGroupTab(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sequence Group")));
		return webElement;
	}
	
	//To identify Edit Field button
	public WebElement fn_clickEditFieldBtn(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Edit Fields']")));
		return webElement;
	}
	
	//To identify Functional Group list
	public WebElement fn_selectFunctionalGroup(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("inSeqFuncGroupNo")));
		return webElement;
	}
	
	//To identify Save Changes Button
	public WebElement fn_ClickSaveChangesBtn(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Save Changes']")));
		return webElement;
	}
	
	//To identify the header section/ error box which displays succeess/failure message
	public WebElement fn_ReadErrorBox(WebDriver driver, WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='error-box']//p")));
		return webElement;
	}

}
