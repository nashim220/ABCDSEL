package pageFactory.Courses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class CertificationInformationPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public CertificationInformationPage(WebDriver driver)
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
		
		@FindBy(css="div.span4 h2")
		public WebElement CertificationInfoText;
		
		@FindBy(css="#main > div > div:nth-child(8) > div.span4 > h2")
		public WebElement StateRequiredInformationText;
		
		
	
		@FindBy(css="div[class='well info'] h3")
		public WebElement Student_Fullname;
		
		@FindBy(css="div[class='well info'] p")
		public WebElement Student_Birthdate;
		
		@FindBy(id="StudentModel_gender")
		public WebElement Student_Gender;
		
		@FindBy(id="eye_color")
		public WebElement Student_EyeColor;
		
		@FindBy(id="hair_color")
		public WebElement Student_HairColor;
		
		@FindBy(id="race")
		public WebElement Student_Ethinicity;

		@FindBy(id="student_ssn")
		public WebElement Student_SSN;
		
		@FindBy(id="weight")
		public WebElement Student_Weight;
		
		@FindBy(id="foot")
		public WebElement Student_Height;
		
		@FindBy(id="inch")
		public WebElement Student_Inch;
		
		
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
	
		
		@FindBy(id="differentaddress")
		public WebElement PhysicalAddressCheckbox;
		
		
		@FindBy(id="RegistrationModel_p_address1")
		public WebElement PhysicalAddressLine1;
		
		@FindBy(id="RegistrationModel_p_address2")
		public WebElement PhysicalAddressLine2;
		
		@FindBy(id="RegistrationModel_p_city")
		public WebElement PhysicalAddressCity;
		
		@FindBy(id="RegistrationModel_p_state_cd_dropdown")
		public WebElement PhysicalAddressState;
		
		
		@FindBy(id="RegistrationModel_p_zip")
		public WebElement PhysicalAddressZip;
		
		@FindBy(id="RegistrationModel_p_country_id")
		public WebElement PhysicalAddressCountry;
		
		@FindBy(id="RegistrationModel_p_phone")
		public WebElement PhysicalAddressPhoneNumber;
		
		
		@FindBy(id="buttonConfirmCertInfo")
		public WebElement ConfirmCertificateInformation;
		
		
		//Confirm Your Certificate Information Dialog
		
		@FindBy(id="confirm-info")
		public WebElement ConfirmCertificateInformationModal;
		
		@FindBy(css="#confirm-info h3")
		public WebElement ConfirmCertificateInformationModal_Fullname;
		
		@FindBy(css="#confirm-info p")
		public WebElement ConfirmCertificateInformationModal_DOB;
		
		@FindBy(css="#confirm-info p:nth-of-type(2)")
		public WebElement ConfirmCertificateInformationModal_Gender;
		
		@FindBy(css="#confirm-info p:nth-of-type(3)")
		public WebElement ConfirmCertificateInformationModal_SSN;
		
		@FindBy(css="div.addresses p")
		public WebElement ConfirmCertificateInformationModal_MailingAddress;
		
		@FindBy(css="div.addresses p:nth-of-type(2)")
		public WebElement ConfirmCertificateInformationModal_PhysicalAddress;
		
		@FindBy(css="button[class='btn cancel']")
		public WebElement ConfirmCertificateInformationModal_MakeAChangeButton;
		
		/*
		@FindBy(css="input[type='submit']")
		public WebElement ConfirmCertificateInformationModal_ContinuePayInfo;*/
		
		@FindBy(css=".btn.btn-primary.submit")
		public WebElement ConfirmCertificateInformationModal_ContinuePayInfo;
		
		@FindBy(css="div.alert")
		public WebElement Address_Alert;
		
		
		
	//Select Gender
		public void SelectGender(String gender)
		{
			new Select(Student_Gender).selectByVisibleText(gender);
		}
		
	//Select Height
		public void SelectHeight()
		{
			new Select(Student_Height).selectByIndex(2);
		}
		
	//Select Inch
		public void SelectInch()
		{
			new Select(Student_Inch).selectByIndex(5);
		}
		
	//Enter SSN
		public void EnterSSN(String ssn)
		{
			Student_SSN.clear();
			Student_SSN.sendKeys(ssn);
		}
		
	//Select Eye Color
		public void SelectEyeColor(String color)
		{
			new Select(Student_EyeColor).selectByVisibleText(color);
		}
				
		
	//Select Hair Color
		public void SelectHairColor(String color)
		{
			new Select(Student_HairColor).selectByVisibleText(color);
		}
		
	//Select Hair Color
		public void SelectEthinicity(String Ethinicity)
		{
			new Select(Student_Ethinicity).selectByVisibleText(Ethinicity);
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
		
	
	//Click on Physical Address checkbox
		public void ClickOnPhysicalAddressCheckbox()
		{
			PhysicalAddressCheckbox.click();
		}	
		
		
	//Click on Confirm Certificate Information button
		public void ClickOnConfirmCertificateInformationButton()
		{
			ConfirmCertificateInformation.click();
		}	
			
	// Shopping feedback form after payment
		@FindBy(css="#Overall4")	
		WebElement overallRating;
		
		public void clickOnOverallRating()
		{
			overallRating.click();
		}
		
		@FindBy(css="#Recommend4")	
		WebElement recommendRating;
		
		public void clickOnRecommendRating()
		{
			recommendRating.click();
		}
		
		@FindBy(css="#Rebuy4")	
		WebElement reBuyRating;
		
		public void clickOnReBuyRating()
		{
			reBuyRating.click();
		}
		
		@FindBy(css="#comments")	
		WebElement comment;
		
		public void enterComment()
		{
			comment.sendKeys("Just a test.");
		}
		
		@FindBy(css="#sa_tooptin>label:nth-of-type(1) >input")	
		WebElement satisfied;
		
		public void clickOnSatisfiedRadioButton()
		{
			satisfied.click();
		}
		
		@FindBy(id="shopper_submit")	
		WebElement submit;
		
		public void clickOnSubmitButton()
		{
			submit.click();
		}
		
		
		//close the popup
		
		@FindBy(css="#sa_close")	
		WebElement closeRating;
		
		public void clickOncloseRating()
		{
			closeRating.click();
		}
}
