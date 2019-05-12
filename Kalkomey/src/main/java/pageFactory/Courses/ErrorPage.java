package pageFactory.Courses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


import utility.Constants;
import utility.JavaHelpers;

public class ErrorPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public ErrorPage(WebDriver driver)
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
		
		// On 500,404 error page e.g. http://qa1.boat-ed.com/exam/finishedtcexam/registration_id/444
	
		@FindBy(css="div.error h1")
	    public WebElement ErrorMessageHeader;

		@FindBy(css="div.error h2")
	    public WebElement ErrorMessageTitle;
		
		@FindBy(css="div.error p")
	    public WebElement ErrorMessageText;
		
		
		@FindBy(css="div.error > div div:nth-of-type(3) a:nth-of-type(1)")
	    public WebElement BackToHomeButton;
		
		@FindBy(css="div.error > div div:nth-of-type(3) a:nth-of-type(2)")
	    public WebElement  ContactCustomerServiceButton;
		
		
}
