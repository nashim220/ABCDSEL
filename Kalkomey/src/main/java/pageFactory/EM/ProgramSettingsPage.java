package pageFactory.EM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class ProgramSettingsPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public ProgramSettingsPage(WebDriver driver)
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
	
	
		@FindBy(css=".span9 h3")
		public WebElement Title;	
		
		
		@FindBy(css="#federal_reporting")
		public WebElement FederalReporting;		
	
		public void SelectFederalReportingOption(String visibletext)
		{
			new Select(FederalReporting).selectByVisibleText(visibletext);
		}
		
		@FindBy(css="#disable_rss")
		public WebElement RSSFeed;		
	
		public void SelectRSSFeedOption(String visibletext)
		{
			new Select(RSSFeed).selectByVisibleText(visibletext);
		}	
		 
		@FindBy(css="#disable_ical")
		public WebElement iCalFeed;		
	
		public void SelectiCalFeedOption(String visibletext)
		{
			new Select(iCalFeed).selectByVisibleText(visibletext);
		}	
		
		@FindBy(css="#instructor_daily_reporting")
		public WebElement InstructorDailyReporting;		
	
		public void SelectInstructorDailyReportingOption(String visibletext)
		{
			new Select(InstructorDailyReporting).selectByVisibleText(visibletext);
		}
		
		
		@FindBy(css="#ebook_cross_sell")
		public WebElement EBookCrossSell;		
	
		public void SelectEBookCrossSell(String visibletext)
		{
			new Select(EBookCrossSell).selectByVisibleText(visibletext);
		}
		
		@FindBy(css="#introtext")
		public WebElement StateSpecificMessage;	
		
		public void EnterStateSpecificMessage(String msg)
		{
			StateSpecificMessage.clear();
			StateSpecificMessage.sendKeys(msg);
		}
		
		@FindBy(css=".btn.btn-primary.pull-right")
		public WebElement SaveChanges;
		
		public void ClickOnSaveChanges()
		{
			SaveChanges.click();
		}
		
		@FindBy(css=".alert-flash.alert.alert-success")
		public WebElement Success;
		
		
}
