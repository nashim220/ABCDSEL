package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class ILMCOrderPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public ILMCOrderPage(WebDriver driver)
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
	
		@FindBy(css=".brand img")
	    public WebElement Logo;
		
		
		public void ClickOnLogo()
		{
			Logo.click();
		}
		
		@FindBy(css=".ilmcBranding h1")
		public WebElement Header;
		
		@FindBy(css=".sThree.span4 .active")
		public WebElement HighlitedNumber;
		
		
		@FindBy(css="#paymentForm div:nth-of-type(2)")
		public WebElement OrderDetails;
			
		@FindBy(css="#card_number")
		public WebElement CardNumber;
		
		public void EnterCCNumber(String cc)
		{
			CardNumber.clear();
			CardNumber.sendKeys(cc);
		}
		
		@FindBy(css="#card_expy")
		public WebElement Expiry;
		
		public void EnterExpiry(String expiry)
		{
			Expiry.clear();
			Expiry.sendKeys(expiry);
		}
		
		@FindBy(css="#billing_name_first")
		public WebElement FirstName;
		
		public void EnterFirstName(String firstname)
		{
			FirstName.clear();
			FirstName.sendKeys(firstname);
		}
		
		@FindBy(css="#billing_name_last")
		public WebElement LastName;
		
		public void EnterLastName(String lastname)
		{
			LastName.clear();
			LastName.sendKeys(lastname);
		}
		
		@FindBy(css="#same_billing_shipping")
	    public WebElement SameBilling;
		
		
		public void ClickOnSameBillingCheckbox()
		{
			SameBilling.click();
		}
		
		@FindBy(css="#email")
		public WebElement Email;
		
		public void EnterEmail(String email)
		{
			Email.clear();
			Email.sendKeys(email);
		}
		
		@FindBy(css="#emailConfirm")
		public WebElement ConfirmEmail;
		
		public void EnterConfirmEmail(String confirmemail)
		{
			ConfirmEmail.clear();
			ConfirmEmail.sendKeys(confirmemail);
		}
		
		@FindBy(css="#billing_address_country")
		public WebElement Country;
		
		public void SelectCountry(String visibletext)
		{
			new Select(Country).selectByVisibleText(visibletext);
		}
		
		@FindBy(css="#billing_address_1")
		public WebElement Address;
		
		@FindBy(css="#billing_address_city")
		public WebElement City;
		
		@FindBy(css="#billing_address_state")
		public WebElement State;
		
		public void SelectState(String visibletext)
		{
			new Select(State).selectByVisibleText(visibletext);
		}
		
		@FindBy(css="#billing_address_zip")
		public WebElement Zip;
		
		@FindBy(css=".btn.btn-large.btn-success")
	    public WebElement PlaceOrder;
		
		
		public void ClickOnPlaceOrderButton()
		{
			PlaceOrder.click();
		}
		
		
		@FindBy(css=".alert.alert-error")
	    public WebElement Error;
		
		
		
		

		//Fill Billing Details
			public void FillBillingDetails(
													String cc,
													String expiry,
													String firstname,
													String lastname,
													String email,
													String confirmemail
													) throws InterruptedException
					{
						this.EnterCCNumber(cc);
						this.EnterExpiry(expiry);
						this.EnterFirstName(firstname);
						this.EnterLastName(lastname);
						this.EnterEmail(email);
						this.EnterConfirmEmail(confirmemail);
						
					}
					
			
				
}
