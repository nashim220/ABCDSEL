package pageFactory.Courses;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class RegistrationPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public RegistrationPage(WebDriver driver)
	{	 
        this.driver = driver;
 
        //This initElements method will create all public WebElements
        //PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.WebDriverWaitDuration), this);
	}
	
	
	/*
	 * All public WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */ 	
	
		@FindBy(id="StudentModel_uname")
	    public WebElement Username;
	 
		@FindBy(id="StudentModel_password")
	    public WebElement Password;
		
		@FindBy(id="StudentModel_password2")
	    public WebElement RepeatPassword;
		
		@FindBy(id="StudentModel_first_name")
	    public WebElement FirstName;
		
		@FindBy(id="StudentModel_middle_initial")
	    public WebElement MiddleInitial;
		
		@FindBy(id="StudentModel_last_name")
	    public WebElement LastName;
		
		@FindBy(id="StudentModel_suffix")
	    public WebElement Suffix;
		
		@FindBy(id="StudentModel_month")
	    public WebElement DOBMonth;
		
		@FindBy(id="StudentModel_day")
	    public WebElement DOBDay;
		
		@FindBy(id="StudentModel_year")
	    public WebElement DOBYear;
	
		@FindBy(id="StudentModel_email")
	    public WebElement Email;
		
		@FindBy(id="StudentModel_email2")
	    public WebElement RepeatEmail;
		
		@FindBy(css="#create-account-submit")  
	    public WebElement CreateAccount;
		
		//Click on 'Create Account' button
		public void ClickOnCreateAccount() throws InterruptedException
		{
			CreateAccount.sendKeys(Keys.RETURN);
			Thread.sleep(10000);
		}
		
		@FindBy(css="div.page-header a")
	    public WebElement ChooseAnother;
		
		@FindBy(id="StudentModel_residency")
	    public WebElement StateResidencyCheckbox;
		
		@FindBy(id="wait-right-there")
	    public WebElement ReminderModel;
		
		@FindBy(css="div#wait-right-there div.modal-footer a")
	    public WebElement ReminderModelConfirmationButton;
		
		//Pay upfront courses
		
		@FindBy(css="#main > div > div > div > p")
		public WebElement FeeMessageText;
		
			//Florida Violators
			
				//Citation / Ticket #
				@FindBy(id="fl_citation_num")
			    public WebElement Citation_Ticket;
				
				//Gender
				@FindBy(id="StudentModel_gender")
			    public WebElement Gender;
						
		//Mailing Address
				
				@FindBy(id="RegistrationModel_s_address1")
			    public WebElement MailingAddressLine1;
				
				@FindBy(id="RegistrationModel_s_address2")
			    public WebElement MailingAddressLine2;
				
				@FindBy(id="RegistrationModel_s_city")
			    public WebElement MailingAddressCity;
				
				@FindBy(id="RegistrationModel_s_state_cd_dropdown")
			    public WebElement MailingAddressState;
				
				@FindBy(id="RegistrationModel_s_state_cd_textfield")
			    public WebElement MailingAddressRegionOrLocality;
				
				@FindBy(id="RegistrationModel_s_zip")
			    public WebElement MailingAddressZip;
				
				@FindBy(id="RegistrationModel_s_country_id")
			    public WebElement MailingAddressCountry;
				
				@FindBy(id="RegistrationModel_s_phone")
			    public WebElement MailingAddressPhoneNumber;
			
						
	//Choose a Username
		public void ChooseAUsername(String username)
		{
			Username.clear();
			Username.sendKeys(username);
		}
	
	//Enter Password
		public void EnterPassword(String password)
		{
			Password.clear();
			Password.sendKeys(password);
		}
	
	//Enter Repeat Password
		public void EnterRepeatPassword(String repeatpassword)
		{
			RepeatPassword.clear();
			RepeatPassword.sendKeys(repeatpassword);
		}
	
	//Enter First Name
		public void EnterFirstName(String firstname)
		{
			FirstName.clear();
			FirstName.sendKeys(firstname);
		}
	
	//Enter Last Name
		public void EnterLastName(String lastname)
		{
			LastName.clear();
			LastName.sendKeys(lastname);
		}
	
	//Enter Middle Initial
		public void EnterMiddleInitial(String middleinitial)
		{
			MiddleInitial.clear();
			MiddleInitial.sendKeys(middleinitial);
		}
	
	//Select Suffix
		public void SelectSuffix(String suffix)
		{
			new Select(Suffix).selectByVisibleText(suffix);
		}
	
		
	//Select Gender
		public void SelectGender(String gender)
		{
			new Select(Gender).selectByVisibleText(gender);
		}
		
				
	//Select DOB
		public void EnterDOB(String month, String day, String year)
		{
			new Select(DOBMonth).selectByVisibleText(month);
			new Select(DOBDay).selectByVisibleText(day);
			DOBYear.sendKeys(year);
		}
		
	//Enter Email Address
		public void EnterEmailAddress(String emailaddress)
		{
			Email.clear();
			Email.sendKeys(emailaddress);
		}
	
	//Enter Repeat Email Address
		public void EnterRepeatEmailAddress(String repeatemailaddress)
		{
			RepeatEmail.clear();
			RepeatEmail.sendKeys(repeatemailaddress);
		}
	
	
		
	//Click on 'Choose another' link
		public void ClickOnChooseAnother()
		{
			ChooseAnother.click();
		}	
		
		
	//Click on 'State Residency Checkbox
		public void ClickOnStateResidencyCheckbox()
		{
			StateResidencyCheckbox.click();
		}	
		
	//Click on 'I Understand..' button on modal
		public void ClickOnIUnderstandButton()
		{
			ReminderModelConfirmationButton.click();
		}	
	

	
		
	//Fill Registration form
		public void FillRegistrationForm(
										String username,
										String password,
										String repeatpassword,
										String firstname,
										String middleinitial,
										String lastname,
										String suffix,
										String month,
										String day,
										String year,
										String emailaddress,
										String repeatemailaddress)
		{
			this.ChooseAUsername(username);
			this.EnterPassword(password);
			this.EnterRepeatPassword(repeatpassword);
			this.EnterFirstName(firstname);
			this.EnterMiddleInitial(middleinitial);
			this.EnterLastName(lastname);
			this.SelectSuffix(suffix);
			this.EnterDOB(month, day, year);
			this.EnterEmailAddress(emailaddress);
			this.EnterRepeatEmailAddress(repeatemailaddress);			
		}
		
		
		
		//Register as new User
		public void RegisterAsNewUser(
										String username,
										String password,
										String repeatpassword,
										String firstname,
										String middleinitial,
										String lastname,
										String suffix,
										String month,
										String day,
										String year,
										String emailaddress,
										String repeatemailaddress) throws InterruptedException
		{
			
			FillRegistrationForm(username,password,repeatpassword,firstname,middleinitial,lastname,suffix,month,day,year,emailaddress,repeatemailaddress);
			CreateAccount.sendKeys(Keys.RETURN);
					
		}
		
		
		//Enter Mailing Address
		public void EnterMailingAddress(String addressline1,String addressline2,String city,String state,
										String zip, String country,String phonenumber)
		{
			MailingAddressLine1.clear();
			MailingAddressLine1.sendKeys(addressline1);
			MailingAddressLine2.clear();
			MailingAddressLine2.sendKeys(addressline2);
			
			MailingAddressCity.clear();
			MailingAddressCity.sendKeys(city);
			
			new Select(MailingAddressState).selectByVisibleText(state);
			
			MailingAddressZip.clear();
			MailingAddressZip.sendKeys(zip);
			
			new Select(MailingAddressCountry).selectByVisibleText(country);
			
			MailingAddressPhoneNumber.clear();
			MailingAddressPhoneNumber.sendKeys(phonenumber);
			
		}
		
		//Enter Mailing Address for Canada
		public void EnterMailingAddress_Canada(String addressline1,String addressline2,String city,String State,
										String zip, String country,String phonenumber)
		{
			MailingAddressLine1.clear();
			MailingAddressLine1.sendKeys(addressline1);
			MailingAddressLine2.clear();
			MailingAddressLine2.sendKeys(addressline2);
			
			MailingAddressCity.clear();
			MailingAddressCity.sendKeys(city);
			
			new Select(driver.findElement(By.cssSelector("select[class='span3 province']"))).selectByVisibleText(State);
			
			MailingAddressZip.clear();
			MailingAddressZip.sendKeys(zip);
			
			new Select(MailingAddressCountry).selectByVisibleText(country);
			
			
			MailingAddressPhoneNumber.clear();
			MailingAddressPhoneNumber.sendKeys(phonenumber);
			
		}	
		
		
}
