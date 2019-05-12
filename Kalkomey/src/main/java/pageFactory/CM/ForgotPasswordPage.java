package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class ForgotPasswordPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public ForgotPasswordPage(WebDriver driver)
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
	
		@FindBy(css="div.main-inner h1")
	    public WebElement PageTitle;
		
		@FindBy(css="div.main-inner p")
		public WebElement PageTitleText;
		
		@FindBy(id="address")
		public WebElement EmailAddress;
		
		@FindBy(name="commit")
		public WebElement SendButton;
		
		//Error messages
		@FindBy(css="div[class='alert alert-error']")
		public WebElement ErrorMessagesText;
	
		
}
