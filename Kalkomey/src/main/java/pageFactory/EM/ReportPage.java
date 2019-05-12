package pageFactory.EM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class ReportPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public ReportPage(WebDriver driver)
	{	 
        this.driver = driver;
 
        //This initElements method will create all WebElements
        //PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.WebDriverWaitDuration), this);
	}
	
	
	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */ 	
	
	
		@FindBy(css=".page-header.page-header-compressed h2")
		public WebElement Title;	
		
		
		@FindBy(css="#sdate")
		public WebElement StartDate;	
		
		public void EnterStartDate(String sdate)
		{
			StartDate.clear();
			StartDate.sendKeys(sdate);
		}
		
		@FindBy(css="#edate")
		public WebElement EndDate;	
		
		public void EnterEndDateDate(String edate)
		{
			EndDate.clear();
			EndDate.sendKeys(edate);
		}
		
		
		@FindBy(css="#filterapply")
		public WebElement Apply;
		
		public void ClickOnApply()
		{
			JavascriptExecutor js =(JavascriptExecutor)driver;
			js.executeScript("scroll(250, 0)");
			Apply.click();
		}
		
		
		public String GetAllColumnInstructorAudit()
		{
			String sociallinks="";
			for(int i=1; i<= 8; i++)
			{
				String css = ".ui-jqgrid-htable th:nth-of-type(" + i + ") div";
				sociallinks = sociallinks + driver.findElement(By.cssSelector(css)).getText(); 
			}
			
			return sociallinks;
		}
		
}
