package pageFactory.Courses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class CourseCompletePage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public CourseCompletePage(WebDriver driver)
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
	
		@FindBy(css="div.page-header h1")
		public WebElement PageHeader;

		@FindBy(css="#main > div > section:nth-child(4) > div.span4 > h2")
		public WebElement GetYourCertificateText;
		
		@FindBy(css="#main > div > section:nth-child(6) > div.span4 > h2")
		public WebElement AfterThisCourseText;
		
		@FindBy(css="#main > div > section:nth-child(4) > div.span8 > p:nth-child(1)")
		public WebElement CongratulationsMessageText;
		
		@FindBy(css="#main > div > section:nth-child(4) > div.span8 > p:nth-child(2) > a:nth-child(1)")
		public WebElement Download;
		
		@FindBy(css="#main > div > section:nth-child(4) > div.span8 > p:nth-child(2) > a:nth-child(2)")
		public WebElement Email;
		
		@FindBy(css="#main > div > section:nth-child(6) > div.span8 > p.well.well-small > a")
		public WebElement ReturnToCourseContent;
		
		@FindBy(css=".span8 > p:nth-child(3) > a:nth-child(1)")
		public WebElement ReturnToCourseContent_Handgun;
		
		@FindBy(css="#main > div > section:nth-child(4) > div.span8 > p:nth-of-type(3) > a")
		public WebElement ReturnToCourseContent_Offroad;
		
		
		@FindBy(css=".btn.btn-large.btn-facebook")
		public WebElement ShareOnFacebook;
		
}
