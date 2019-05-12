package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class OrderReplacementPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public OrderReplacementPage(WebDriver driver)
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
	
	
		@FindBy(css=".span8 h1")
		public WebElement Header;
	
		@FindBy(css="#cert-info header div a")
    	public WebElement OrderReplacement;
		
		public void ClickOnOrderReplacementButton()
		{
			OrderReplacement.click();
		}
		
		@FindBy(css="#payment_form_bill_to_forename")
    	public WebElement FN;
		
		@FindBy(css="#payment_form_bill_to_surname")
    	public WebElement LN;
		
		@FindBy(css="#cc-number")
    	public WebElement CC;
		
		public void EnterCCNO(String cc)
		{
			CC.clear();
			CC.sendKeys(cc);
		}
		
		@FindBy(css="#cc-number + p + p")
    	public WebElement ErrorCC;
		
		@FindBy(css="#cc-exp-date")
    	public WebElement Expiry;
		
		public void EnterExpiry(String expiry)
		{
			Expiry.clear();
			Expiry.sendKeys(expiry);
		}
		
		@FindBy(css="#cc-exp-date + p")
    	public WebElement ErrorExpiry;
		
		@FindBy(css="#cc-street1")
    	public WebElement Street;
		
		public void EnterStreet(String street)
		{
			Street.clear();
			Street.sendKeys(street);
		}
		
		@FindBy(css="#cc-city")
    	public WebElement City;
		
		public void EnterCity(String city)
		{
			City.clear();
			City.sendKeys(city);
		}
		
		@FindBy(css="#cc-zip")
    	public WebElement Zip;
		
		public void EnterZip(String zip)
		{
			Zip.clear();
			Zip.sendKeys(zip);
		}
		
		@FindBy(css="#cc-zip + p")
    	public WebElement ErrorZip;
		
		@FindBy(css="#cc-phone")
    	public WebElement Phone;
		
		public void EnterPhone(String phone)
		{
			Phone.clear();
			Phone.sendKeys(phone);
		}
		
		@FindBy(css="#btn-submit-payment")
    	public WebElement Submit;
		
		public void ClickOnSubmitButton()
		{
			Submit.click();
		}
		
		@FindBy(css="#billing_address_country")
    	public WebElement Country;
		
		public void SelectCountry(String country)
		{
			new Select(Country).selectByVisibleText(country);
		}
		
		@FindBy(css="#billing_address_state")
    	public WebElement State;
		
		public void SelectState(String state)
		{
			new Select(State).selectByVisibleText(state);
		}
		
		@FindBy(css="#quantity")
    	public WebElement QTY;
		
		public void SelectQTY(String qty)
		{
			new Select(QTY).selectByVisibleText(qty);
		}
		
		@FindBy(css="#per-card-price")
    	public WebElement Price;
		
		@FindBy(css=".alert.alert-error.alert-block")
    	public WebElement Error;
		
		@FindBy(css=".alert.alert-success")
    	public WebElement Success;
		
		//Fill Billing Details
		public void FillBillingDetails(	
										String cc, String expiry,String phone
									  ) throws InterruptedException
				{
					this.EnterCCNO(cc);
					this.EnterExpiry(expiry);
					this.EnterPhone(phone);
					
				}
				
}
