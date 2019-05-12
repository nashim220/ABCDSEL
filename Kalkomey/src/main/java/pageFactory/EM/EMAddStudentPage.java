package pageFactory.EM;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class EMAddStudentPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public EMAddStudentPage(WebDriver driver)
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
	
		@FindBy(css="#Student_first_name")
		WebElement FirstName;
	
		@FindBy(css="#Student_last_name")
	    WebElement LastName;
		
		@FindBy(css="#student_dob_month")
	    WebElement Month;
		
		@FindBy(css="#student_dob_day")
	    WebElement Day;
		
		@FindBy(css="#student_dob_year")
	    WebElement Year;
		
		@FindBy(css="#Student_gender")
		WebElement Gender;
		
		@FindBy(css="#registration_race")
	    WebElement Ethnicity;
		
		@FindBy(css="#Registration_p_country_id")
	    WebElement Country;
		
		@FindBy(id="registration_county")
	    WebElement AdditionalIfnoCountry;
		
		
		@FindBy(css="#Registration_p_address1")
	    WebElement Address;
		
		@FindBy(css="#Registration_p_city")
		public WebElement City;
		
		@FindBy(css="#Registration_p_state_cd")
	    public WebElement State;
		
		@FindBy(css="#Registration_p_zip")
	    WebElement Zip;
		
		@FindBy(css="#Student_email")
		WebElement Email;
	  
		@FindBy(css="#Registration_p_phone")
		WebElement PhNo;
		
		@FindBy(css=".btn.btn-success")
		WebElement Register;
		
		@FindBy(css="#registration_county")
		WebElement County;
		
		@FindBy(css="#registration_origin2")
		WebElement Origin;
		
		@FindBy(css="#registration_heritage")
		WebElement Heritage;
		
		@FindBy(css="#registration_hunted")
		WebElement Hunted;
		
		@FindBy(css="#registration_accompanied_hunter2")
		WebElement AccompaniedHunter;
		
		@FindBy(css="#registration_prepared_game2")
		WebElement PreparedGame;
		
		@FindBy(css="#registration_consumed_game")
		WebElement ConsumedGame;
		
		@FindBy(css=".btn.btn-danger>span")
		public WebElement Cancel;
		
		@FindBy(css="#Registration_first_name")
		public WebElement editFirstName;
		
		@FindBy(css="#Registration_last_name")
		public WebElement editLastName;
		
	//Select Month
		public void SelectConsumedGame(String consumedgame)
		{
			new Select(ConsumedGame).selectByVisibleText(consumedgame);
			
		}

	//Select Month
		public void SelectPreparedGame(String preparedgame)
		{
			new Select(PreparedGame).selectByVisibleText(preparedgame);
			
		}
		
	//Select Month
		public void SelectAccompaniedHunter(String accompaniedhunter)
		{
			new Select(AccompaniedHunter).selectByVisibleText(accompaniedhunter);			
		}

	//Select Month
		public void SelectHunted(String hunted)
		{
			new Select(Hunted).selectByVisibleText(hunted);			
		}
		
	//Select Month
		public void SelectHeritage(String heritage)
		{
			new Select(Heritage).selectByVisibleText(heritage);			
		}

	//Select Month
		public void SelectOrigin(String origin)
		{
			new Select(Origin).selectByVisibleText(origin);			
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
					
	//Select Month
		public void SelectYear(String year)
		{
			new Select(Year).selectByVisibleText(year);			
		}
				

	//Select Month
		public void SelectGender(String gender)
		{
			new Select(Gender).selectByVisibleText(gender);			
		}
		
	//Select Month
		public void SelectEthnicity(String ethnicity)
		{
			new Select(Ethnicity).selectByVisibleText(ethnicity);			
		}

	//Select Month
		public void SelectCounty(String county)
		{
			new Select(County).selectByVisibleText(county);			
		}
		
		//Click on Search button
		public void ClickOnRegisterButton()
		{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Register);
			Register.click();
		}
		
		
	
		
	//Enter Email
		public void EnterEmail(String email)
		{
			Email.clear();
			Email.sendKeys(email);
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

		
					
	//Select Country
		public void SelectCountry(String country)
		{
			new Select(Country).selectByVisibleText(country);			
		}
		
	//Select Country for additional Info
		
		public void SelectCountryForAdditionalInfo(String country)
		{
			new Select(AdditionalIfnoCountry).selectByVisibleText(country);			
		}
		
	//Select State
		public void SelectState(String state)
		{
			new Select(State).selectByVisibleText(state);			
		}
		

		@FindBy(css="#registration_hair_color")
		WebElement SelectHairColor;
		
		public void SelectHairColor()
		{
			new Select(SelectHairColor).selectByVisibleText("Black");			
		}
		
		@FindBy(css="#registration_eye_color")
		WebElement SelectEyeColor;
		
		public void SelectEyeColor()
		{
			new Select(SelectEyeColor).selectByVisibleText("Brown");			
		}
		
		@FindBy(css="#registration_student_ssn")
		WebElement SSNNO;
		
		public void EnterSSNNO(String ssn)
		{
			SSNNO.clear();
			SSNNO.sendKeys(ssn);	
		
		}
		
	//Fill Physical Address
			public void FillPhysicalAddress(
				
												String PhysicalZIP ,
												String PhysicalAddress,
												String PhysicalCity,
												String PhysicalPhone
												)
					{
						this.EnterFirstName(PhysicalZIP);
						this.EnterFirstName(PhysicalAddress);
						this.EnterFirstName(PhysicalCity);
						this.EnterFirstName(PhysicalPhone);
				
												}
		
	//Fill Register Student Form
			public void FillRegisterStudent(
													String firstname,
													String lastname,
													String email,
													String gender,
													String country,
													String state,
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
						this.SelectGender(gender);
						this.SelectCountry(country);
						this.SelectState(state);
						this.SelectEthnicity(ethnicity);
						this.SelectMonth(month);
						this.SelectDay(day);
						this.SelectYear(year);
						this.EnterAddress(address);
						this.EnterCity(city);
						this.EnterPhNo(phno);
						this.EnterZip(zip);
						
						
					}
					
			
			//Register New Student
				public void RegisterNewStudent(
													String firstname,
													String lastname,
													String email,
													String gender,
													String country,
													String state,
													String ethnicity,
													String month,
													String day,
													String year,
													String address,
													String city,
													String phno,
													String zip) throws InterruptedException
			        {
						FillRegisterStudent(firstname, lastname, email, gender, country, state, ethnicity, month, day, year, address, city, phno, zip);
					}	
				
				
				
				public void FillRegisterStudentNY(
																						String firstname,
																						String lastname,
																						String email,
																						String gender,
																						String country,
																						String state,
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
								this.SelectGender(gender);
								this.SelectCountry(country);
								this.SelectState(state);
								this.SelectMonth(month);
								this.SelectDay(day);
								this.SelectYear(year);
								this.EnterAddress(address);
								this.EnterCity(city);
								this.EnterPhNo(phno);
								this.EnterZip(zip);
					}

}
