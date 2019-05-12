package pageFactory.EM;

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
	
		@FindBy(css="#UserUsername")
	    WebElement Username;
		
		@FindBy(css="#UserPassword")
	    WebElement Password;
		
		@FindBy(css="button[type='Submit']")
	    WebElement Login;
		
		@FindBy(css="a[href='https://my-webtest1.register-ed.com/login/lostpassword/normal']")
	    WebElement ForgotPassword;
		
		@FindBy(css=".inline>li:nth-of-type(2)>a")
		public WebElement Logout;
	  
		@FindBy(css="#email-form")
		public WebElement ContactSupport;
	  
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
	
	//Click on Login button
		public void ClickOnLogoutButton()
		{
			Logout.click();
		}
			
	//Click on Forgot Password link
		public void ClickOnForgotPasswordLink()
		{
			ForgotPassword.click();
		}
		
}
