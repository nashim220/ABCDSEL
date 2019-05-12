package pageFactory.Courses;

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
	
		@FindBy(id="ForgetPasswordForm_username")
	    WebElement Username;
		
		@FindBy(id="ForgetPasswordForm_email")
	    WebElement EmailAddress;
		
		@FindBy(css="#loginForm input[value='Send Me the Instructions']")
	    WebElement SendMeInstruction;
	
		
	
	//Enter Username
		public void EnterUsername(String username)
		{
			Username.clear();
			Username.sendKeys(username);
		}
		
	//Enter Email Address
		public void EnterEmail(String email)
		{
			EmailAddress.clear();
			EmailAddress.sendKeys(email);
		}
		
	//Click on SendMeInstruction button
		public void ClickOnSendMeInstructionButton()
		{
			SendMeInstruction.click();
		}
		
		@FindBy(css="div.mailview>ul>li>a")
		WebElement UsernameLink;
		public void ClickOnUsernameLink()
		{
			UsernameLink.click();
		}
		
		@FindBy(css="div.alert.alert-info.fade.in")
		 public WebElement  ForgotPasswaordMessage;
	
		
		
}
