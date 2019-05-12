package pageFactory.EM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class RegisterEdWaitListPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public RegisterEdWaitListPage(WebDriver driver)
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
	
		@FindBy(css="#first_name")
		WebElement FirstName;
	
		public void EnterFirstName(String firstname)
		{
			FirstName.clear();
			FirstName.sendKeys(firstname);
		}
		
		
		@FindBy(css="#last_name")
		WebElement LastName;
	
		public void EnterLastName(String lastname)
		{
			LastName.clear();
			LastName.sendKeys(lastname);
		}
		
		@FindBy(css="#email")
		WebElement Email;
	
		public void EnterEmail(String email)
		{
			Email.clear();
			Email.sendKeys(email);
		}
		
		
		@FindBy(css="#phone")
		WebElement Phone;
	
		public void EnterPhone(String phone)
		{
			Phone.clear();
			Phone.sendKeys(phone);
		}
		
		
		@FindBy(css="#city_residence")
		WebElement City;
	
		public void EnterCity(String city)
		{
			City.clear();
			City.sendKeys(city);
		}	
		
		@FindBy(css=".btn.btn-warning.btn-large")
		WebElement JoinWaitList;
	
		//Click on Search Event Link
		public void ClickOnJoinWaitListButton()
		{
			JoinWaitList.click();
		}
				
		
		@FindBy(css="#waitlist_success")
		public WebElement WaitListSuccess;
		
		@FindBy(css="#alert_validation")
		public WebElement MandatoryValidation;
		
		@FindBy(css="#phone_errorMsg")
		public WebElement InvalidPhoneNo;
		
		@FindBy(css="#email_errorMsg")
		public WebElement InvalidEmail;
		
		
		
		
		
		
		public void FillWaitListForm(
												String firstname,
												String lastname,
												String email,
												String city,
												String phone
												)
				{
					this.EnterFirstName(firstname);
					this.EnterLastName(lastname);
					this.EnterEmail(email);
					this.EnterCity(city);
					this.EnterPhone(phone);
					
					
				}
				
		
		
			public void RegisterForWaitList(
										String firstname,
										String lastname,
										String email,
										String city,
										String phone
										) throws InterruptedException
		        {
					FillWaitListForm(firstname, lastname, email, city, phone);
				}	

		
}
