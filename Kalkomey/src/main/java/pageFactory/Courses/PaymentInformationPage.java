package pageFactory.Courses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class PaymentInformationPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public PaymentInformationPage(WebDriver driver)
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
	
		@FindBy(css="div.page-header h1")
		public WebElement PageHeader;
		
		@FindBy(css="ul[class='breadcrumb step']")
		public WebElement breadcrumbtext;
		
		@FindBy(css="div[class='alert alert-warning fade in']")
		public WebElement PaymentAlertMessageForPay;
		
		@FindBy(css="div.span4 h2")
		public WebElement ItemstoPurchaseText;
		
		@FindBy(css="#main > div > section:nth-child(6) > div.span4 > h2")
		public WebElement CreditCardInformationText;
		
		@FindBy(css="div.alert")
		public WebElement CreditCardAlertText;
		
		@FindBy(css="ul.checkout-items h3")
		public WebElement CourseFee_Header;
		
		@FindBy(css="ul.checkout-items p:nth-of-type(2)")
		public WebElement CourseFee_Fee;
		
		@FindBy(css="ul.checkout-items li:nth-of-type(2) p:nth-of-type(2)")
		public WebElement CourseCard_Fee;
		
		@FindBy(css="ul.checkout-items p:nth-of-type(1)")
		public WebElement CourseFee_Fee_Hunter;
		
		@FindBy(id="PaymentModel_paycode")
		public WebElement CouponCode;
		
		@FindBy(css="p.help-block")
		public WebElement CouponCodeAlertText;
		
		@FindBy(css="input[type='submit']")
		public WebElement ApplyCode;
		
		@FindBy(css="div[class='well total clearfix'] p")
		public WebElement Total_Fee;
		
		@FindBy(css="div.tax h4")
		public WebElement Sales_Tax_Percentage;
		
		@FindBy(css="div.tax p")
		public WebElement Sales_Tax_Price;
		
		
		
		//Credit Card
		
		@FindBy(id="PaymentModel_card_first_name")
		public WebElement FirstName;
		
		@FindBy(id="PaymentModel_card_last_name")
		public WebElement LastName;
		
		@FindBy(id="PaymentModel_card_number")
		public WebElement CCNumber;
		
		@FindBy(id="expiration-month")
		public WebElement ExpirationMonth;
		
		@FindBy(id="expiration-year")
		public WebElement ExpirationYear;
		
		// Mastercard Logo
		@FindBy(css=".mastercard.logo")
		public WebElement MastercardLogo;
		
		//Billing Contact
		
		@FindBy(id="sameasmailing")
		public WebElement BillingContactInfoCheckbox;
		
		@FindBy(css="div[class='mailingaddress well']")
		public WebElement BillingAddressWhileSelectedAsMailingAddress;
			
		@FindBy(id="PaymentModel_b_address1")
		public WebElement BillingStreetAddress;
		
		@FindBy(id="PaymentModel_b_city")
		public WebElement BillingCity;
			
		@FindBy(id="PaymentModel_b_state_cd_dropdown")
		public WebElement BillingState;
		
		@FindBy(id="PaymentModel_b_zip")
		public WebElement BillingZipCode;
		
		@FindBy(id="PaymentModel_b_country_id")
		public WebElement BillingCountry;
		
		@FindBy(id="PaymentModel_b_phone")
		public WebElement BillingPhoneNumber;
		

		
		@FindBy(id="buttonConfirmPaymentInfo")
		public WebElement ConfirmPaymentInformation;
		
		
		//Confirm Your Payment Information Dialog
		
		@FindBy(id="confirm-info")
		public WebElement ConfirmPaymentInformationModal;
		
		@FindBy(css="#confirm-info p")
		public WebElement ConfirmPaymentInformationModal_Fullname;
		
		@FindBy(css="#confirm-info p:nth-of-type(2)")
		public WebElement ConfirmPaymentInformationCard_Number;
		
		@FindBy(css="#confirm-info p:nth-of-type(3)")
		public WebElement ConfirmPaymentInformationCard_Expiration_Number;
		
		@FindBy(css="div.addresses")
		public WebElement ConfirmPaymentInformationBilling_Address;
		
		@FindBy(css="#confirm-info > div > div.acceptance > h3")
		public WebElement ConfirmPaymentInformationAcceptanceOfTerms_Header;
		
		@FindBy(css="#confirm-info > div > div.acceptance > p")
		public WebElement ConfirmPaymentInformationAcceptanceOfTerms_Text;
		
		@FindBy(css="button[class='btn cancel']")
		public WebElement ConfirmPaymentInformationModal_MakeAChangeButton;
		
		@FindBy(css="input[form='formPayment'][type='submit']")
		public WebElement ConfirmPaymentInformationModal_ProcessMyPayment;
		
		
		
		//Enter CC & Billing details
		public void EnterCCAndBillingDetails(	String firstname,
												String lastname,
												String card_number,
												String expirationmonth,
												String expirationyear, 
												String billingstreetaddress,
												String billingcity, 
												String billingstate, 
												String billingzipcode,
												String billingcountry, 
												String billingphonenumber )
		{
			FirstName.clear();
			FirstName.sendKeys(firstname);
			LastName.clear();
			LastName.sendKeys(lastname);
			
			CCNumber.clear();
			CCNumber.sendKeys(card_number);
			
			new Select(ExpirationMonth).selectByVisibleText(expirationmonth);
			new Select(ExpirationYear).selectByVisibleText(expirationyear);
			
			BillingStreetAddress.clear();
			BillingStreetAddress.sendKeys(billingstreetaddress);
			
			BillingCity.clear();
			BillingCity.sendKeys(billingcity);
			
			new Select(BillingState).selectByVisibleText(billingstate);
			
			BillingZipCode.clear();
			BillingZipCode.sendKeys(billingzipcode);
			
			new Select(BillingCountry).selectByVisibleText(billingcountry);
			
			BillingPhoneNumber.clear();
			BillingPhoneNumber.sendKeys(billingphonenumber);
			
		}	
}

