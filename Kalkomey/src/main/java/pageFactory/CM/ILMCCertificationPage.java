package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class ILMCCertificationPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public ILMCCertificationPage(WebDriver driver)
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
	
		@FindBy(css=".prominent-link")
	    public WebElement EditAddress;
		
		
		public void ClickOnEditAddressButton()
		{
			EditAddress.click();
		}
		
		
		
		
		@FindBy(css="#review_confirm")
		public WebElement Confirm;
		
		public void ClickOnConfirmCheckbox()
		{
			Confirm.click();
		}
		
		@FindBy(css="#order_submit")
		public WebElement Order;
		
		public void ClickOnOrderButton()
		{
			Order.click();
		}
		
		@FindBy(css=".sTwo.span4 .active")
		public WebElement HighlitedNumber2;
		
		@FindBy(css=".certRecord dl")
		public WebElement CertificationRecord;
		
		@FindBy(css=".certRecord ul")
		public WebElement SupportText;
		
		@FindBy(css=".btn.btn-large.btn-success")
		public WebElement Continue;
		
		public void ClickOnContinueButton()
		{
			Continue.click();
		}
		
		
		@FindBy(css=".alert.alert-success")
		public WebElement Success;
		
		
		@FindBy(css=".alert.alert-info")
		public WebElement ResidencyRestriction;
		
		@FindBy(css=".container-narrow.topContainer p:nth-of-type(1)")
		public WebElement NonPAMultiRecordError;
		
		
		
}
