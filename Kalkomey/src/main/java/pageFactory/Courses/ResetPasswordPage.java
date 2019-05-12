package pageFactory.Courses;

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
	
		@FindBy(id="PasswordResetForm_password1")
	    WebElement NewPassword;
		
		@FindBy(id="PasswordResetForm_password2")
	    WebElement ReEnterNewPassword;
		
		@FindBy(css="#loginForm input[value='Submit']")
	    WebElement Submit;
	
		@FindBy(css=".alert.alert-info.fade.in")
	    public WebElement Info_ResetPassword;
		
	
	//Enter New Password
		public void EnterNewPassword(String password)
		{
			NewPassword.clear();
			NewPassword.sendKeys(password);
		}
		
	//Re Enter New Password
		public void EnterReEnterNewPassword(String password)
		{
			ReEnterNewPassword.clear();
			ReEnterNewPassword.sendKeys(password);
		}
		
	//Click on Submit button
		public void ClickOnSubmitButton()
		{
			Submit.click();
		}
		
		
}
