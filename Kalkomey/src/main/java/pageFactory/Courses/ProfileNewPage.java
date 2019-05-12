package pageFactory.Courses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class ProfileNewPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public ProfileNewPage(WebDriver driver)
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
	
		@FindBy(css="a[class='btn reset password']")
	    WebElement ResetPassword;
		
		
		@FindBy(id="StudentModel_gender")
	    WebElement Gender;
		
		
		@FindBy(id="race")
	    WebElement Ethnicity;
		
		@FindBy(id="student_ssn")
	    WebElement StudentSSN;
		
		@FindBy(id="StudentModel_email")
	    WebElement EmailAddress;
		
		
		@FindBy(id="RegistrationModel_s_address1")
	    public WebElement MailingAddressLine1;
		
		@FindBy(id="RegistrationModel_s_address2")
	    WebElement MailingAddressLine2;
		
		@FindBy(id="RegistrationModel_s_city")
		public  WebElement MailingAddressCity;
		
		@FindBy(id="RegistrationModel_s_state_cd_dropdown")
		public WebElement MailingAddressState;
		
		@FindBy(id="RegistrationModel_s_zip")
		public WebElement MailingAddressZip;
		
		@FindBy(id="RegistrationModel_s_country_id")
	    public WebElement MailingAddressCountry;
		
		@FindBy(id="RegistrationModel_s_phone")
		public  WebElement MailingAddressPhoneNumber;
	
		
		@FindBy(id="differentaddress")
	    public WebElement PhysicalAddressCheckbox;
		
		
		@FindBy(id="RegistrationModel_p_address1")
	    WebElement PhysicalAddressLine1;
		
		@FindBy(id="RegistrationModel_p_address2")
	    WebElement PhysicalAddressLine2;
		
		@FindBy(id="RegistrationModel_p_city")
	    WebElement PhysicalAddressCity;
		
		@FindBy(id="RegistrationModel_p_state_cd_dropdown")
	    WebElement PhysicalAddressState;
		
		@FindBy(id="RegistrationModel_p_zip")
	    WebElement PhysicalAddressZip;
		
		@FindBy(id="RegistrationModel_p_country_id")
	    WebElement PhysicalAddressCountry;
		
		@FindBy(id="RegistrationModel_p_phone")
	    WebElement PhysicalAddressPhoneNumber;
		
		
		@FindBy(css="input[value='Update Profile']")
	    WebElement UpdateProfile;
		
		@FindBy(id="StudentModel_first_name")
	    WebElement FirstName;
		
		public void EnterFirstName(String firstname)
		{
			FirstName.clear();
			FirstName.sendKeys(firstname);
		}
		
		@FindBy(id="StudentModel_last_name")
	    WebElement LastName;
		
		public void EnterLastName(String lastname)
		{
			LastName.clear();
			LastName.sendKeys(lastname);
		}
		
	//Click on Reset Password button
		public void ClickOnResetPasswordButton()
		{
			ResetPassword.click();
		}
		
	//Select Gender
		public void SelectGender(String gender)
		{
			new Select(Gender).selectByVisibleText(gender);
		}
		
	//Select Gender
		public void SelectEthnicity(String ethnicity)
		{
			new Select(Ethnicity).selectByVisibleText(ethnicity);
		}
	
	//Enter SSN
		public void EnterSSN(String ssn)
		{
			StudentSSN.clear();
			StudentSSN.sendKeys(ssn);
		}
		
	//Enter Email Address
		public void EnterEmailAddress(String email)
		{
			EmailAddress.clear();
			EmailAddress.sendKeys(email);
		}
		
		
	//Enter Mailing Address
		public void EnterMailingAddress(String addressline1,String addressline2,String city,String state,
										String zip, String country,String phonenumber) throws InterruptedException
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
			Thread.sleep(4000);
		}	
		
		
	//Enter Mailing Address
		public void EnterPhysicalAddress(String addressline1,String addressline2,String city,String state,
										String zip, String country,String phonenumber)
		{
			PhysicalAddressLine1.clear();
			PhysicalAddressLine1.sendKeys(addressline1);
			PhysicalAddressLine2.clear();
			PhysicalAddressLine2.sendKeys(addressline2);
			
			PhysicalAddressCity.clear();
			PhysicalAddressCity.sendKeys(city);
			
			new Select(PhysicalAddressState).selectByVisibleText(state);
			
			PhysicalAddressZip.clear();
			PhysicalAddressZip.sendKeys(zip);
			
			new Select(PhysicalAddressCountry).selectByVisibleText(country);
			
			PhysicalAddressPhoneNumber.clear();
			PhysicalAddressPhoneNumber.sendKeys(phonenumber);
			
		}	
		
	
		//Click on Reset Password button
		public void ClickOnPhysicalAddressCheckbox()
		{
			PhysicalAddressCheckbox.click();
		}	
		
		
		//Click on Update Profile button
			public void ClickOnUpdateProfileButton()
			{
				UpdateProfile.click();
			}	
		

}
