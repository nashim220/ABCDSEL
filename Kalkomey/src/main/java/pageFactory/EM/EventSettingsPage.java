package pageFactory.EM;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class EventSettingsPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public EventSettingsPage(WebDriver driver)
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
	
	
		@FindBy(css="#event_min_age")
		public WebElement MinAge;	
		
		public void EnterMinAge(String minage)
		{
			MinAge.clear();
			MinAge.sendKeys(minage);
		}
		
		@FindBy(css="#event_max_age")
		public WebElement MaxAge;	
		
		public void EnterMaxAge(String maxage)
		{
			MaxAge.clear();
			MaxAge.sendKeys(maxage);
		}
		
		@FindBy(css="#event_min_age_text")
		public WebElement MinAgePolicy;	
		
		public void EnterMinAgePolicy(String minagepolicy)
		{
			MinAgePolicy.clear();
			MinAgePolicy.sendKeys(minagepolicy);
		}
		
		@FindBy(id="event_max_age_text")
		public WebElement MaxAgePolicy;	
		
		public void EnterMaxAgePolicy(String maxagepolicy)
		{
			MaxAgePolicy.clear();
			MaxAgePolicy.sendKeys(maxagepolicy);
		}
		
		@FindBy(css="#reg_reqts table:nth-of-type(4) tr:nth-of-type(2) select")
		public WebElement MinAgeDate;	
		
		public void SelectMinAgeDate(String visibletext)
		{
			new Select(MinAgeDate).selectByVisibleText(visibletext);
		}
		
		@FindBy(css="#reg_reqts table:nth-of-type(5) tr:nth-of-type(2) select")
		public WebElement MaxAgeDate;	
		
		public void SelectMaxAgeDate(String visibletext)
		{
			new Select(MaxAgeDate).selectByVisibleText(visibletext);
		}		
				
		@FindBy(css=".btn.btn-primary.pull-right")
		public WebElement SaveChanges;
		
		public void ClickOnSaveChanges()
		{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", SaveChanges);
			SaveChanges.click();
		}
		
		@FindBy(css=".alert-flash.alert.alert-success")
		public WebElement Success;
		
		@FindBy(css="select[name='ConfigValue[max_allowed_registrations]']")
		public WebElement AllowRegistration;
		
		public void AllowRegistration(String visibletext)
		{
			new Select(AllowRegistration).selectByVisibleText(visibletext);
		}
		
		
		@FindBy(css="select[name='ConfigValue[reg_token_reqd]']")
		public WebElement TokenRequired;
		
		public void SelectTokenRequired(String visibletext)
		{
			new Select(TokenRequired).selectByVisibleText(visibletext);
		}
		
		@FindBy(css="select[name='ConfigValue[display_full]']")
		public WebElement DisplayWhenFull;
		
		public void SelectDisplayWhenFull(String visibletext)
		{
			new Select(DisplayWhenFull).selectByVisibleText(visibletext);
		}
		
		
		@FindBy(css="select[name='ConfigValue[event_display_only]']")
		public WebElement DisplayOnly;
		
		public void SelectDisplayOnly(String visibletext)
		{
			new Select(DisplayOnly).selectByVisibleText(visibletext);
		}
		
		@FindBy(css="#cancel_policy")
		public WebElement CancellationsPolicy;	
		
		public void EnterCancellationsPolicy(String cancelpolicy)
		{
			CancellationsPolicy.clear();
			CancellationsPolicy.sendKeys(cancelpolicy);
		}
		
		@FindBy(css="#event_vis_buffer")
		public WebElement EventFirstVisible;	
		
		public void EnterEventFirstVisible(String eventfirstvisible)
		{
			EventFirstVisible.clear();
			EventFirstVisible.sendKeys(eventfirstvisible);
		}
		
		@FindBy(css="#event_reg_buffer")
		public WebElement RegistrationWindowCloses;	
		
		public void EnterRegistrationWindowCloses(String registrationcloses)
		{
			RegistrationWindowCloses.clear();
			RegistrationWindowCloses.sendKeys(registrationcloses);
		}
		
		@FindBy(css="#event_cx_buffer")
		public WebElement CancellationWindow;	
		
		public void EnterCancellationWindow(String cancellationcloses)
		{
			CancellationWindow.clear();
			CancellationWindow.sendKeys(cancellationcloses);
		}
		
		@FindBy(css="#page > div.content.clearfix > div.row-fluid > div.span3 > div > ul > li:nth-child(7) > a")
		public WebElement SearchEvent;
		
		
		@FindBy(css="#search-type-name")
		WebElement EnterEvent;
		public void Enter_Event_Name()
		{
			EnterEvent.clear();
			EnterEvent.sendKeys("Demo Hunter Education Field Day");
		}
}
