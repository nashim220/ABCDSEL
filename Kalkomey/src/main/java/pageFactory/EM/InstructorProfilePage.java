package pageFactory.EM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class InstructorProfilePage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public InstructorProfilePage(WebDriver driver)
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
	
		@FindBy(css="div.page-header h3")
	    public WebElement PageTitle;
		
		@FindBy(css="div.span4 p")
	    public WebElement InfoText;
		
		@FindBy(css="button[class='btn btn-success']")
	    public WebElement SaveChangesButton;
		
		@FindBy(css="a[class='btn btn-danger']")
	    public WebElement CancelButton;
		
		@FindBy(css="div[class='alert-flash  alert alert-success']")
	    public WebElement SuccessAlertMessage;
		

		//Account Information section
		
			@FindBy(css="#edit_profile_form fieldset:nth-of-type(1) legend")
		    public WebElement AccountInformationText;

			@FindBy(id="first_name")
		    public WebElement FirstName;
			
			@FindBy(id="middle_initial")
		    public WebElement MiddleName;
			
			@FindBy(id="last_name")
		    public WebElement LastName;
			
			@FindBy(id="suffix")
		    public WebElement Suffix;
			
			@FindBy(id="email")
		    public WebElement Email;
			
			@FindBy(id="gender")
		    public WebElement Gender;
			
			//Select Gender
			public void SelectGender(String gender)
			{
				new Select(Gender).selectByVisibleText(gender);
			}
			
			
		//Login Information section
		
			@FindBy(css="#edit_profile_form fieldset:nth-of-type(2) legend")
		    public WebElement LoginInformationText;
	
			@FindBy(id="password")
		    public WebElement Password;
			
			@FindBy(id="password_confirm")
		    public WebElement ConfirmPassword;
			
			@FindBy(id="pin")
		    public WebElement Pin;
			
			@FindBy(id="pin_confirm")
		    public WebElement ConfirmPin;
			
			@FindBy(id="password_hint")
		    public WebElement PasswordHint;
			
			@FindBy(id="secret_question")
		    public WebElement SecretQuestion;
			
			@FindBy(id="secret_answer")
		    public WebElement SecretAnswer;
			
			
		//Additional Information section
		
			@FindBy(css="#edit_profile_form fieldset:nth-of-type(3) legend")
		    public WebElement AdditionalInformationText;
	
			@FindBy(id="instructor_p_address_1")
		    public WebElement Address;
			
			@FindBy(id="instructor_p_city")
		    public WebElement City;
			
			@FindBy(id="instructor_p_zip")
		    public WebElement Zip;
			
			@FindBy(css="#instructor_ssn")
		    public WebElement SSN;
			
			@FindBy(id="instructor_region")
		    public WebElement Region;
			
			//Select Region
			public void SelectRegion(String region)
			{
				new Select(Region).selectByVisibleText(region);
			}
			
			
			@FindBy(id="instructor_county")
		    public WebElement County;
			
			//Select County
			public void SelectCounty(String county)
			{
				new Select(County).selectByVisibleText(county);
			}
			
			
			@FindBy(id="instructor_p_state")
		    public WebElement State;
			
			//Select State
			public void SelectState(String state)
			{
				new Select(State).selectByVisibleText(state);
			}

			
		//Contact You section
		
			@FindBy(css="#edit_profile_form fieldset:nth-of-type(4) legend")
		    public WebElement ContactYouText;		
			
			
		//Get validation message
		public String GetValidationMessage(String fieldvalue)
		{
			String cssSelector = "label[for='" + fieldvalue + "'][class='error help-block']";
			return driver.findElement(By.cssSelector(cssSelector)).getText().trim();
		}
			
			
}


