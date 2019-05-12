package pageFactory.EM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class EMSendAMessagePage {

	WebDriver driver;
JavaHelpers JH = new JavaHelpers();
	
	public EMSendAMessagePage(WebDriver driver)
	{	 
        this.driver = driver;
 
        //This initElements method will create all WebElements
        //PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.WebDriverWaitDuration), this);
	}
	
	@FindBy(css="#send-message-modal")
	public WebElement SendUsAnEmailPopUp;
	
	@FindBy(css=".checkbox.inline")
	public WebElement Email;
	
	@FindBy (css="#send-message div:nth-of-type(5)")
	public WebElement Sincere_InstructorName;
	
	@FindBy(css="#name")
	public WebElement Name;
	
	public void EnterName(String name)
	{
		Name.clear();
		Name.sendKeys(name);
	}
	
	@FindBy (css="#email")
	public WebElement Email_contact;
	
	public void EnterEmail(String email)
	{
		Email_contact.clear();
		Email_contact.sendKeys(email);
	}
	
	@FindBy (css="#subject")
	public WebElement Subject;
	
	@FindBy(css="#message")
	WebElement Reason;
	
	public void EnterReason(String reason)
	{
		Reason.clear();
		Reason.sendKeys(reason);
	}
	
	@FindBy(css=".btn.btn-default")
	public WebElement Submit;
	
	@FindBy(css="#email-modal-body")
	public WebElement SuccessMessage_Contact;
}
