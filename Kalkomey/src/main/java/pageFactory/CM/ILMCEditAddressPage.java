package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class ILMCEditAddressPage 
{
	WebDriver driver; 
	JavaHelpers JH = new JavaHelpers();
	
	public ILMCEditAddressPage(WebDriver driver)
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
	
		@FindBy(css=".header.cf h1")
	    public WebElement Header;
			
		
		@FindBy(css="#certification_s_country_id")
		public WebElement Country;
		
		public void SelectCountry(String country)
		{
			new Select(Country).selectByVisibleText(country);
		}
		
		
		@FindBy(css="#certification_s_address1")
		public WebElement Address;
		
		public void EnterAddress(String address)
		{
			Address.clear();
			Address.sendKeys(address);
		}
		
		
		@FindBy(css="#certification_s_city")
		public WebElement City;
		
		public void EnterCity(String city)
		{
			City.clear();
			City.sendKeys(city);
		}
		
		
		@FindBy(css="#certification_s_state")
		public WebElement State;
		
		public void SelectState(String state)
		{
			new Select(State).selectByVisibleText(state);
		}
		
		
		@FindBy(css="#certification_s_zip")
		public WebElement Zip;
		
		public void EnterZip(String zip)
		{
			Zip.clear();
			Zip.sendKeys(zip);
		}
		
		@FindBy(css=".btn.btn-large.btn-success")
		public WebElement Continue;
		
		public void ClickOnContinueButton()
		{
			Continue.click();
		}
		
		@FindBy(css="input[name='commit']")
		public WebElement Continueeditpage;
		
		public void ClickOnContinueeditpage()
		{
			Continueeditpage.click();
		}
		
		
		
		@FindBy(css="#address_verify input")
		public WebElement Save;
		
		public void ClickOnSaveButton()
		{
			Save.click();
		}
		
		@FindBy(css="#certification_p_zip")
		public WebElement ZipPhy;
		
		public void EnterZipPhy(String zip)
		{
			ZipPhy.clear();
			ZipPhy.sendKeys(zip);
		}
		
		@FindBy(css=".alert.alert-error")
		public WebElement Error;
		
		@FindBy(css="#certification_s_state + p")
		public WebElement StateError;
		
		@FindBy(css="#certification_s_address1 + span")
		public WebElement AddressError;
		
		@FindBy(css="#certification_s_city + span")
		public WebElement CityError;
		
		@FindBy(css="#certification_s_zip + span")
		public WebElement ZipError;
		
		@FindBy(css="#mailing_address_check div:nth-of-type(1) button")
		public WebElement AddressCheck;
		
		@FindBy(css="#physical_address_check div:nth-of-type(1) button")
		public WebElement AddressPhysicalCheck;
		
		@FindBy(css="#mailing_address_check h4:nth-of-type(3)")
		public WebElement USPSMailingVal;
		
		@FindBy(css="#mailing_address_check h4:nth-of-type(1)")
		public WebElement USPSMailingSuccess;
		
		@FindBy(css="#mailing_address_check div:nth-of-type(2) button")
		public WebElement UseUSPSMailing;
		
		@FindBy(css="#physical_address_check h4:nth-of-type(2)")
		public WebElement USPSPhyVal;
		
		@FindBy(css="#physical_address_check div:nth-of-type(2) button")
		public WebElement UseUSPSPhyl;
		
		@FindBy(css="#physical_address_check h4:nth-of-type(1)")
		public WebElement USPSPhySuccess;
		
		public void ClickOnAddressCheckButton()
		{
			AddressCheck.click();
		}
		//Fill Form
		public void FillAddressDetails(
										String country,
										String address,
										String city,
										String state,
										String zip
										) throws InterruptedException
		{
			this.SelectCountry(country);
			Thread.sleep(2000);
			this.EnterAddress(address);
			this.EnterCity(city);
			this.SelectState(state);
			this.EnterZip(zip);
			
		}
}
