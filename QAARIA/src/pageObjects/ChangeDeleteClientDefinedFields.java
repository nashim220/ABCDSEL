package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChangeDeleteClientDefinedFields {

		public static WebElement webElement = null;		
	
		public WebElement fn_Search(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name("searchValue1")));
			return webElement;
		}
		
		public WebElement fn_SearchBtn(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Search']")));
			return webElement;
		}
		
		public WebElement fn_AcctNumLink(WebDriver driver,WebDriverWait webWait,String acct_num) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText(acct_num)));
			return webElement;
		}
		
		public WebElement fn_ClientDefLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Client-Defined Fields")));
			return webElement;
		}
		
		public WebElement fn_SearchFields(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search']")));
			return webElement;
		}
		
		public WebElement fn_EditLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Edit")));
			return webElement;
		}
						
		public WebElement fn_FieldVal(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name("field-add-input")));
			return webElement;
		}
		
		public WebElement fn_AddNewBtn(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name("field-add-button")));
			return webElement;
		}
		
		public WebElement fn_EditBtn(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name("field-edit-button")));
			return webElement;
		}
		
		public WebElement fn_UpdateField(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name("field-edit-input")));
			return webElement;
		}
		
		public WebElement fn_UpdateBtn(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name("field-update-button")));
			return webElement;
		}
		public WebElement fn_ReturnPrevScreen(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Return to Previous Screen")));
			return webElement;
		}
		public WebElement fn_ClickByRecentRegistration(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("By Recent Registration")));
			return webElement;
		}
		public WebElement fn_SelectPastDaysToSearch(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"SearchByCriteriaIndex\"]/div/form/table/tbody/tr/td[2]/select")));
			return webElement;
		}
		public WebElement fn_BtnSubmit(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("submit-button")));
			return webElement;
		}
		
		public WebElement fn_DeleteFieldLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Delete Field")));
			return webElement;
		}
		
}
