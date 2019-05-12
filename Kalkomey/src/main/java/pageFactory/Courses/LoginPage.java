package pageFactory.Courses;

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
	
		@FindBy(id="LoginForm_username")
	    WebElement Username;
		
		@FindBy(id="LoginForm_password")
	    WebElement Password;
		
		@FindBy(css="#loginForm input[value='Log In']")
	    WebElement Login;
		
		@FindBy(css="a[href='/site/forgetPassword']")
	    WebElement ForgotPassword;
		
		
	
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
		
}
