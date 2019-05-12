package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class ResetPasswordPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public ResetPasswordPage(WebDriver driver)
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
		
		@FindBy(id="password")
		public WebElement NewPassword;
		
		@FindBy(id="password_confirmation")
		public WebElement PasswordConfirmation;
		
		@FindBy(name="commit")
		public WebElement UpdatePasswordButton;
		
		
		//Error messages
		@FindBy(css="div.error_messages")
		public WebElement ErrorMessagesText;
	
	
		
}
