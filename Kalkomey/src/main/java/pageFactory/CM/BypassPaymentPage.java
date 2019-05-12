package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class BypassPaymentPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public BypassPaymentPage(WebDriver driver)
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
	
	
		@FindBy(css=".btn.btn-small.btn-warning.bypass-payment.pull-right")
    	public WebElement ByPassPayment;
		
		public void ClickOnByPassPaymentButton()
		{
			ByPassPayment.click();
		}
		
		@FindBy(css=".page-header hgroup h1")
    	public WebElement Header;
		
		
		@FindBy(css="#historical_action_type_id")
	    public WebElement Reason;
		
		public void SelectReason(String reason)
		{
			new Select(Reason).selectByVisibleText(reason);
		}
		
		@FindBy(css="#bypass-reason")
    	public WebElement DetailReason;
		
		public void EnterDetailReason(String details)
		{
			DetailReason.clear();
			DetailReason.sendKeys(details);
		}
		
		@FindBy(css="#quantity")
	    public WebElement QTY;
		
		public void SelectQTY(String qty)
		{
			new Select(QTY).selectByVisibleText(qty);
		}
		
		@FindBy(css="#continue_button")
    	public WebElement Submit;
		
		public void ClickOnSubmitButton()
		{
			Submit.click();
		}
		
		
		
}
