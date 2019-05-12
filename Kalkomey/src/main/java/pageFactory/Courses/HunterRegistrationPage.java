package pageFactory.Courses;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class HunterRegistrationPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public HunterRegistrationPage(WebDriver driver)
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
		
		@FindBy(id="StudentModel_month")
	    public WebElement DOBMonth;
		
		@FindBy(id="StudentModel_day")
	    public WebElement DOBDay;
		
		@FindBy(id="StudentModel_year")
	    public WebElement DOBYear;
	
		@FindBy(id="StudentModel_email")
	    public WebElement Email;
		
		@FindBy(css="#main button")  
	    public WebElement CreateAccount;
		
		//Click on 'Create Account' button
		public void ClickOnCreateAccount() throws InterruptedException
		{
			CreateAccount.sendKeys(Keys.RETURN);
			Thread.sleep(10000);
		}
		
		@FindBy(css="#customer_id")
		public WebElement DNRNumber;
		
		@FindBy(css=".checkbox>label>input")
	    public WebElement StateResidencyCheckbox;
		
		@FindBy(id="wait-right-there")
	    public WebElement ReminderModel;
		
		@FindBy(css="div#wait-right-there div.modal-footer a")
	    public WebElement ReminderModelConfirmationButton;
		
		@FindBy(css="#intro-modal-dismiss")
	    public WebElement WelcomeAboard;
				
				
			
	//Choose a Username
		public void ChooseAUsername(String username)
		{
			Username.clear();
			Username.sendKeys(username);
		}
	
		@FindBy(id="StudentModel_gender")
	    public WebElement Gender;
		
	//Select Gender
		public void SelectGender(String gender)
		{
			new Select(Gender).selectByVisibleText(gender);
		}
		
		
		@FindBy(id="race")
	    public WebElement Etinicity;
		
	//Select Gender
		public void SelectEtinicity(String ethnicity)
		{
			new Select(Etinicity).selectByVisibleText(ethnicity);
		}
			
		@FindBy(css="#eye_color")
		public WebElement EyeColor;
		//Select Eye color
		public void SelectEye(String eyeColor)
		{
			new Select(EyeColor).selectByVisibleText(eyeColor);
		}
		
		@FindBy(css="#hair_color")
		public WebElement HairColor;
		//Select Hair color
		public void SelectHairColor(String hairColor)
		{
			new Select(HairColor).selectByVisibleText(hairColor);
		}
		
		@FindBy(css="#weight")
		public WebElement Weight;
		//Enter Weight
		public void EnterWeight(String weight)
		{
			Weight.clear();
			Weight.sendKeys(weight);
		}
		
		@FindBy(css="#foot")
		public WebElement HeightFoot;
		//Enter Height Foot
		public void EnterHeightFoot(String heightfoot)
		{
			HeightFoot.clear();
			HeightFoot.sendKeys(heightfoot);
		}
		
		@FindBy(css="#inch")
		public WebElement HeightInch;
		//Enter Height Inch
		public void EnterHeightInch(String heightinch)
		{
			HeightInch.clear();
			HeightInch.sendKeys(heightinch);
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
	
		@FindBy(css="#student_ssn")
	    public WebElement EnterSSN;
	
		//Enter Email Address
		public void EnterSSNNumber(String ssnnumber)
		{
			EnterSSN.clear();
			EnterSSN.sendKeys(ssnnumber);
		}
	
		@FindBy(css="#ssn_last4")
	    public WebElement Enter4SSN;
	
		//Enter Email Address
		public void Enter4SSN(String ssn)
		{
			Enter4SSN.clear();
			Enter4SSN.sendKeys(ssn);
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
	

		@FindBy(css="#StudentModel_password")
	    public WebElement Password;
		
		//Enter Password
		public void EnterPassword(String password)
		{
			Password.clear();
			Password.sendKeys(password);
		}
		
		@FindBy(css="#StudentModel_password2")
	    public WebElement ConfirmPassword;
		
		//Enter Confirm Password
		public void EnterConfirmPassword(String confirmpassword)
		{
			ConfirmPassword.clear();
			ConfirmPassword.sendKeys(confirmpassword);
		}
	
		
	//Fill Registration form
		public void FillRegistrationForm(
										String username,
										String month,
										String day,
										String year,
										String emailaddress,
										String password,
										String confirmpassword
								)
		{
			this.ChooseAUsername(username);			
			this.EnterDOB(month, day, year);
			this.EnterEmailAddress(emailaddress);
			this.EnterPassword(password);
			this.EnterConfirmPassword(confirmpassword);
		}
		
		
		
		//Register as new User
		public void RegisterAsNewUser(
										String username,
										String month,
										String day,
										String year,
										String emailaddress,
										String password,
										String confirmpassword
										) throws InterruptedException
		{
			
			FillRegistrationForm(username,month,day,year,emailaddress, password, confirmpassword);
			CreateAccount.sendKeys(Keys.RETURN);
					
		}
		
		
	
		
		
}
