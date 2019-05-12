package pageFactory.EM;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class RegisterEdRegisterStudentPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public RegisterEdRegisterStudentPage(WebDriver driver)
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
	
		@FindBy(css="#RegistrationFirstName")
		WebElement FirstName;
	
		@FindBy(css="#RegistrationLastName")
	    WebElement LastName;
		
		@FindBy(css="#RegistrationMonth")
	    WebElement Month;
		
		@FindBy(css="#RegistrationDay")
	    WebElement Day;
		
		@FindBy(css="#RegistrationYear")
	    WebElement Year;
		
		@FindBy(css="#RegistrationDetail1Race")
	    WebElement Ethnicity;
		
		@FindBy(css="#RegistrationPCountryId")
	    WebElement Country;
		
		@FindBy(css="#RegistrationPAddress1")
	    WebElement Address;
		
		@FindBy(css="#RegistrationPCity")
	    WebElement City;
		
		@FindBy(css="#RegistrationPStateCd")
	    WebElement State;
		
		@FindBy(css="#RegistrationPZip")
	    WebElement Zip;
		
		@FindBy(css="#RegistrationEmail")
		WebElement Email;
	  
		@FindBy(css="#registrationEmailConfirm")
		WebElement ConfirmEmail;
		
		@FindBy(css="#RegistrationPPhone")
		WebElement PhNo;
		
		@FindBy(css="#continue_regular")
		public WebElement Next;
		
		@FindBy(css="#RegistrationGenderM")
		WebElement GenderM;
		
		public void ClickToSelectGender()
		{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", GenderM);
			GenderM.click();
		}
		
		
		@FindBy(css=".btn.btn-default.btn-small")
		WebElement AddAnotherStudent;
		
		public void ClickOnAddAnotherStudentButton()
		{
			AddAnotherStudent.click();
		}
		
		@FindBy(css="#add_student button")
		public WebElement AddStudent;
		
		public void ClickOnAddStudentButton()
		{
			AddStudent.click();
		}
		
		@FindBy(css="#new_student_btn")
		WebElement AddThisStudent;
		
		public void ClickOnAddThisStudentButton()
		{
			AddThisStudent.click();
		}
		
		@FindBy(css="#add_student_check > button[name='continue']")
		WebElement ReviewAndConfirm;
		
		public void ClickOnReviewAndConfirmButton()
		{
			ReviewAndConfirm.click();
		}
		
		@FindBy(css="#agreements input")
		WebElement AgreePolicy;
	
		public void ClickOnAgreePolicyCheckBox()
		{
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", AgreePolicy);
			AgreePolicy.click();
		}
		

		@FindBy(css="#filesModal > div.modal-footer > button")
		public WebElement IUnderstand;
		public void ClickOnIUnderstand()
		{
			IUnderstand.click();
		}
		
		
		@FindBy(css="#complete_registration")
		public WebElement CompleteRegistration;
	
		public void ClickOnCompleteRegistrationButton()
		{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", CompleteRegistration);
			CompleteRegistration.click();
		}
		
		@FindBy(css=".alert.alert-success")
		public WebElement SuccessMessage;
		
		
		@FindBy(css="#primary_content h2 strong")
		public WebElement Title;
		
		
		//Update or Cancel Registration
		@FindBy(css="#email")
		WebElement EmailAddress;
		
		public void EnterEmailAddress(String emailaddress)
		{
			EmailAddress.clear();
			EmailAddress.sendKeys(emailaddress);
		}
		
		@FindBy(css="#emailSubmit")
		WebElement GoButton;
	
		public void ClickOnGoButton()
		{
			GoButton.click();
		}
		
		
		@FindBy(css="#zip")
		WebElement ZipCode;
		
		public void EnterZipCode(String zipcode)
		{
			ZipCode.clear();
			ZipCode.sendKeys(zipcode);
		}
		
		@FindBy(css="#phone")
		WebElement PhoneNo;
		
		public void EnterPhoneNo(String phonenumber)
		{
			PhoneNo.clear();
			PhoneNo.sendKeys(phonenumber);
		}
		
		
		@FindBy(css="#alert_validation")
		public WebElement Validation;
		
		@FindBy(css=".btn.btn-primary")
		WebElement Verify;
	
		public void ClickOnVerifyButton()
		{
			Verify.click();
		}
	//Select Month
		public void SelectMonth(String month)
		{
			new Select(Month).selectByVisibleText(month);			
		}
				
	//Select Month
		public void SelectDay(String day)
		{
			new Select(Day).selectByVisibleText(day);			
		}
					
		
	//Enter Year
		public void EnterYear(String year)
		{
			Year.clear();
			Year.sendKeys(year);
		}
		
	//Select Month
		public void SelectEthnicity(String ethnicity)
		{
			new Select(Ethnicity).selectByVisibleText(ethnicity);			
		}

		//Click on Search button
		public void ClickOnNextButton()
		{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Next);
			
			Next.click();
		}
		
		
	
		
	//Enter Email
		public void EnterEmail(String email)
		{
			Email.clear();
			Email.sendKeys(email);
		}
	
	//Enter Confirm Password
		public void EnterConfirmEmail(String confirmemail)
		{
			ConfirmEmail.clear();
			ConfirmEmail.sendKeys(confirmemail);
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
		
		
	//Enter Ph No
		public void EnterPhNo(String phno)
		{
			PhNo.clear();
			PhNo.sendKeys(phno);
		}

	//Enter Address
		public void EnterAddress(String address)
		{
			Address.clear();
			Address.sendKeys(address);
		}
			

	//Enter City
		public void EnterCity(String city)
		{
			City.clear();
			City.sendKeys(city);
		}
			
	//Enter Zip
		public void EnterZip(String zip)
		{
			Zip.clear();
			Zip.sendKeys(zip);
		}

		@FindBy(css="#RegistrationSCountryId")
		WebElement CountryPhyAdd;

		
	//Select Country Mailing Address
		public void SelectCountryPhyAdd(String countryphy)
		{
			new Select(CountryPhyAdd).selectByVisibleText(countryphy);			
		}
		
		
					
	//Select Country Mailing Address
		public void SelectCountry(String country)
		{
			new Select(Country).selectByVisibleText(country);			
		}
		
		

	//Select State
		public void SelectState(String state)
		{
			new Select(State).selectByVisibleText(state);			
		}
		
		
		@FindBy(css="#RegistrationDetail2HairColor")
		WebElement SelectHairColor;
		
		public void SelectHairColor()
		{
			new Select(SelectHairColor).selectByVisibleText("Black");			
		}
		
		@FindBy(css="#RegistrationDetail3EyeColor")
		WebElement SelectEyeColor;
		
		public void SelectEyeColor()
		{
			new Select(SelectEyeColor).selectByVisibleText("Brown");			
		}
		
		//NY or New Hampshire Hair color
		@FindBy(css="#RegistrationDetail0HairColor")
		WebElement SelectHairColor1;
		
		public void SelectHairColor1()
		{
			new Select(SelectHairColor1).selectByVisibleText("Brown");			
		}

		//NY or New Hampshire Eye color
		@FindBy(css="#RegistrationDetail1EyeColor")
		WebElement SelectEyeColorNH;
		
		public void SelectEyeColor1()
		{
			new Select(SelectEyeColorNH).selectByVisibleText("Brown");			
		}
		
			
		
		
		
		@FindBy(css="#RegistrationDetail0StudentSsn")
		WebElement SSNNO;
		
		public void EnterSSNNO(String ssn)
		{
			SSNNO.clear();
			SSNNO.sendKeys(ssn);
		}
		
		
		
		//Registration Physical Address
		@FindBy(css="#RegistrationSAddress1")
		WebElement StrAdd;
		
		public void EnterStrAdd(String stradd)
		{
			StrAdd.clear();
			StrAdd.sendKeys(stradd);
		}
		
		@FindBy(css="#RegistrationSCity")
		WebElement CityPhysical;
		
		public void EnterCityPhysical(String cityphy)
		{
			CityPhysical.clear();
			CityPhysical.sendKeys(cityphy);
		}
		
		@FindBy(css="#RegistrationSZip")
		WebElement ZipPhysical;
		
		public void EnterZipPhysical(String zipphy)
		{
			ZipPhysical.clear();
			ZipPhysical.sendKeys(zipphy);
		}
		
		
		//Billing Detais
		
		@FindBy(css="#PaymentCardNumber")
		WebElement CardNumber;
		
		public void EnterCardNumber(String card)
		{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", CardNumber);
			CardNumber.clear();
			CardNumber.sendKeys(card);
		}
		
		
		@FindBy(id="expiration-year")
		WebElement ExpDate_year;
		
		public void EnterExpDate(String year)
		{
			new Select(ExpDate_year).selectByVisibleText(year);
			
		}
		
		@FindBy(css="#bill_to_forename")
		WebElement FirstNamePayment;
		
		public void EnterFirstNamePayment(String firstname)
		{
			FirstNamePayment.clear();
			FirstNamePayment.sendKeys(firstname);
		}
		
		@FindBy(css="#bill_to_surname")
		WebElement SurName;
		
		public void EnterSurName(String surname)
		{
			SurName.clear();
			SurName.sendKeys(surname);
		}
		
		
		@FindBy(css="#billing_mailing_same")
		WebElement SameAddress;	
		
		public void ClickOnSameAddressCheckbox()
		{
			SameAddress.click();
		}
		
		@FindBy(css=".l-cancel-action>a")
		public WebElement CancelRegistration;	
		
		@FindBy(id="card_number_errorMsg")
		public WebElement InvalidCC;	
		
		@FindBy(css=".btn.btn-danger.delete")
		public WebElement Remove;	
		
	//Fill Register Student Form
			public void FillRegisterStudent(
													String firstname,
													String lastname,
													String email,
													String confirmemail,
													String ethnicity,
													String month,
													String day,
													String year,
													String address,
													String city,
													String phno,
													String zip
													)
					{
						this.EnterFirstName(firstname);
						this.EnterLastName(lastname);
						this.EnterEmail(email);
						this.EnterConfirmEmail(confirmemail);
						this.SelectEthnicity(ethnicity);
						this.SelectMonth(month);
						this.SelectDay(day);
						this.EnterYear(year);
						this.EnterAddress(address);
						this.EnterCity(city);
						this.EnterPhNo(phno);
						this.EnterZip(zip);
						;
						
					}
					
			
			//Register New Student
				public void RegisterNewStudent(
											String firstname,
											String lastname,
											String email,
											String confirmemail,
											String ethnicity,
											String month,	
											String day,
											String year,
											String address,
											String city,
											String phno,
											String zip
											) throws InterruptedException
			        {
						FillRegisterStudent(firstname, lastname, email, confirmemail, ethnicity, month, day, year, address, city, phno, zip);
					}	
				
				
				//Fill Register Student Form
				public void FillRegisterStudentNY(
														String firstname,
														String lastname,
														String email,
														String confirmemail,
														//String ethnicity,
														String month,
														String day,
														String year,
														String address,
														String city,
														String phno,
														String zip
														)
						{
							this.EnterFirstName(firstname);
							this.EnterLastName(lastname);
							this.EnterEmail(email);
							this.EnterConfirmEmail(confirmemail);
							//this.SelectEthnicity(ethnicity);
							this.SelectMonth(month);
							this.SelectDay(day);
							this.EnterYear(year);
							this.EnterAddress(address);
							this.EnterCity(city);
							this.EnterPhNo(phno);
							this.EnterZip(zip);
							;
							
						}
						
				
				//Register New Student
					public void RegisterNewStudentNY(
												String firstname,
												String lastname,
												String email,
												String confirmemail,
												//String ethnicity,
												String month,	
												String day,
												String year,
												String address,
												String city,
												String phno,
												String zip
												) throws InterruptedException
				        {
							FillRegisterStudentNY(firstname, lastname, email, confirmemail, month, day, year, address, city, phno, zip);
						}					
				
}
