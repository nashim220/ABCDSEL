package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SupplimentalPlans {
	
	public SupplimentalPlans(){
		
	}
	
	public WebElement webElement = null;
	
	public WebElement fn_PlansServices(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.active > a.panel_link > div")));
		return webElement;
	}
	
	public WebElement fn_SupplementalPlans(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Supplemental Plans")));
		return webElement;
	} 
	
	public WebElement fn_EditPlanUnits(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		//webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='DataTables_Table_4']/tbody/tr/td[9]/a/span")));
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Edit Plan Units")));
		return webElement;
	} 
	
	public WebElement fn_NewPlanUnits(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name("inNumUnits")));
		return webElement;
	} 
	
	public WebElement fn_AssignmentScope(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name("inNumUnits")));
		return webElement;
	}
	
	//Identify Savechanges button
	public WebElement fn_SaveChanges(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"submit-button\"]")));
		return webElement;

	}
	
	//Identify Saveplan button button
	public WebElement fn_SavePlan(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"submit-button\"]")));
		return webElement;

	}

}

