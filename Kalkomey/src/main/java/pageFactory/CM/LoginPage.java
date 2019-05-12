package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class LoginPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public LoginPage(WebDriver driver)
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
	
		@FindBy(css="#auth_key")
	    WebElement Username;
		
		@FindBy(css="#password")
	    WebElement Password;
		
		@FindBy(css="input[value='Login']")
	    WebElement Login;
		
		@FindBy(css="a[href='https://kps.staging.kalkomey.com/password/recover']")
	    WebElement ForgotPassword;
		
		//Success messages
		@FindBy(css="div[class='alert alert-success']")
		public WebElement SuccessMessagesText;

		@FindBy(css="ul[class='nav pull-right'] li:nth-of-type(2) a.dropdown-toggle")
		WebElement UsrHdr;
		
		@FindBy(css="a[href='/logout']")
		WebElement Logout;
	  
		
	  
	//Enter Username
		public void EnterUsername(String username)
		{
			Username.clear();
			Username.sendKeys(username);
		}
		
	//Enter Password
		public void EnterPassword(String password)
		{
			Password.clear();
			Password.sendKeys(password);
		}
		
	//Click on Login button
		public void ClickOnLogInButton()
		{
			Login.click();
		}
		
	//Click on Forgot Password link
		public void ClickOnForgotPasswordLink()
		{
			ForgotPassword.click();
		}
		
	//Click on Login button
		public void ClickOnLogotButton()
		{
			Logout.click();
		}
					
		public void ClickForLogout()
		{
			UsrHdr.click();
		}
}
