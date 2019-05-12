package pageFactory.EM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class InstructorSendMessagePage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public InstructorSendMessagePage(WebDriver driver)
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
	
		@FindBy(css=".page-header.page-header-compressed>h2")
		public WebElement Header;
		
		@FindBy(css=".content.clearfix>p")
		public WebElement MsgSentHeader;
		
		@FindBy(css="#sendmessage_form>div>textarea")
		WebElement Message;
		
		public void EnterMessage(String message)
		{
			Message.clear();
			Message.sendKeys(message);
		}	
		
		@FindBy(css="#page a:nth-of-type(1) span")
	    public WebElement SendMessageLink;
		
		public void ClickOnSendMessageLink()
		{
			SendMessageLink.click();
		}
		
		@FindBy(css=".button>span")
	    public WebElement BackToMenuLink;
		
		public void ClickOnBackToMenuLink()
		{
			BackToMenuLink.click();
		}
}
