package pageFactory.EM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class AgencyCreateUserPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public AgencyCreateUserPage(WebDriver driver)
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
	
		@FindBy(css="a[href='https://my-webtest1.register-ed.com/agency/user']")
		WebElement Users;
	
		@FindBy(css=".create-instructor")
	    WebElement CreateUser;
		
		@FindBy(css="select[id^='UserType']")
	    WebElement Course1;
		
		
		@FindBy(css="#uname")
	    WebElement UserName;
		
		@FindBy(css="#password")
	    WebElement Password;
		
		@FindBy(css="#password_confirm")
	    WebElement ConfirmPassword;
		
		@FindBy(css="#password_hint")
		WebElement PasswordHint;
	  
		@FindBy(css="#secret_question")
		WebElement SecretQue;
		
		@FindBy(css="#secret_answer")
		WebElement SecretAns;
		
		@FindBy(css="#first_name")
		WebElement FirstName;
		
		@FindBy(css="#last_name")
		WebElement LastName;
		
		@FindBy(css="#gender")
		WebElement Gender;
		
		@FindBy(css="#instructor_number_override_instructor_number")
		WebElement InstrNumber;
		
		@FindBy(css="instructor_number_override_instructor_number_h")
		WebElement InstrNumberH;
		
		@FindBy(css="#instructor_number_override_instructor_number_b")
		WebElement InstrNumber_b;
		
		@FindBy(css="#instructor_level")
		WebElement InstrLevel;
		
		@FindBy(id="UserType_98")
		WebElement InstrRole;
		
		@FindBy(css="#instructor_region")
		WebElement Region;
		
		@FindBy(css="#instructor_county")
		WebElement County;
		
		@FindBy(css="#instructor_p_address_1")
		WebElement Address;
	
		@FindBy(css="#instructor_p_city")
		WebElement City;
		
		@FindBy(css="#instructor_p_state")
		WebElement State;
		
		@FindBy(css="#instructor_p_zip")
		WebElement Zip;
		
		@FindBy(css=".btn.btn-success")
		WebElement SaveChanges;
		
		@FindBy(css=".page-header.page-header-compressed>h2")
		WebElement PageHeader;

		@FindBy(css="#instructor_number")
		WebElement InstructorNo;

		@FindBy(css="#instructor_number_h")
		WebElement InstructorNoH;
		
		@FindBy(css="#instructor_number_b")
		WebElement InstructorNo_b;

		@FindBy(css="#search_types")
		WebElement SearchType;

		@FindBy(css="#search-type-uname")
		WebElement SearchData;
		
		
		@FindBy(css="#search_form > input.btn")
		//@FindBy(css="#search_form input:nth-of-type(5)")
		WebElement Search;

		@FindBy(css=".btn.btn-danger")
		WebElement Cancel;

		@FindBy(css="#email")
		WebElement Email;

		@FindBy(id="pin")
		public WebElement PIN;
		
		@FindBy(id="pin_confirm")
		public WebElement CPIN;
		
		@FindBy(id="search-type-uname")
		public WebElement InstructorSearch;
		
		@FindBy(id="reset_pin")
		public WebElement resetPIN;
		
	//Enter Instructor	
		public void Enter_Instructor()
		{
			InstructorSearch.clear();
			InstructorSearch.sendKeys("limited_co");
		}
		
	//Enter PIN 
		public void Enter_PIN()
		{
			PIN.clear();
			PIN.sendKeys("1234");
		}
	
	//Enter CPIN 
		public void Enter_CPIN()
		{
			CPIN.clear();
			CPIN.sendKeys("1234");
		}
		
	//Select Search Type
		public void SelectSearchType(String searchtype)
		{
			new Select(SearchType).selectByVisibleText(searchtype);			
		}
				

	//Click on Search button
		public void ClickOnCancelButton()
		{
			Cancel.click();
		}
		
		
	//Click on Search button
		public void ClickOnSearchButton()
		{
			Search.click();
		}
		

	//Enter Instructor Number
		public void EnterSearchData(String searchdata)
		{
			SearchData.clear();
			SearchData.sendKeys(searchdata);
		}


	//Enter Instructor Number
		public void EnterEmail(String email)
		{
			Email.clear();
			Email.sendKeys(email);
		}

	//Enter Instructor Number
		public void EnterInstrNo(String instrnumber)
		{
			InstructorNo.clear();
			InstructorNo.sendKeys(instrnumber);
		}
		
		
	//Enter Instructor Number
		public void EnterInstrNoH(String instrnumber)
		{
			InstructorNoH.clear();
			InstructorNoH.sendKeys(instrnumber);
				}
	//Enter Instructor Number Boating
		public void EnterInstrNoB(String instrnumberb)
		{
			InstructorNo_b.clear();
			InstructorNo_b.sendKeys(instrnumberb);
		}
		
	//Enter Username
		public void EnterUsername(String username)
		{
			UserName.clear();
			UserName.sendKeys(username);
		}
		
	//Enter Password
		public void EnterPassword(String password)
		{
			Password.clear();
			Password.sendKeys(password);
		}
	
	//Enter Confirm Password
		public void EnterConfirmPassword(String confirmpassword)
		{
			ConfirmPassword.clear();
			ConfirmPassword.sendKeys(confirmpassword);
		}
			
	//Enter Password Hint
		public void EnterPassHint(String passwordhint)
		{
			PasswordHint.clear();
			PasswordHint.sendKeys(passwordhint);
		}

	//Enter Secret Question
		public void EnterSecretQue(String secretque)
		{
			SecretQue.clear();
			SecretQue.sendKeys(secretque);
		}

	//Enter Secret Question
		public void EnterSecretAns(String secretans)
		{
			SecretAns.clear();
			SecretAns.sendKeys(secretans);
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
			
	//Enter City
		public void EnterZip(String zip)
		{
			Zip.clear();
			Zip.sendKeys(zip);
		}

		
	//Select Role for course 1
		public void SelectCourse1(String course1)
		{
			new Select(Course1).selectByVisibleText(course1);			
		}
			


	//Select Gender
		public void SelectGender(String gender)
		{
			new Select(Gender).selectByVisibleText(gender);			
		}
		

	//Select Instructor Level
		public void SelectInstrLevel(String instrlevel)
		{
			new Select(InstrLevel).selectByVisibleText(instrlevel);			
		}
	
		//Select Instructor Level
				public void SelectInstrRole(String instrrole)
				{
					new Select(InstrRole).selectByVisibleText(instrrole);			
				}	
		
	
	//Select Region
		public void SelectRegion(String region)
		{
			new Select(Region).selectByVisibleText(region);			
		}
					
	//Select Region
		public void SelectCounty(String county)
		{
			new Select(County).selectByVisibleText(county);			
		}
		
	//Select Region
		public void SelectState(String state)
		{
			new Select(State).selectByVisibleText(state);			
		}
		

	//Click on User button
		public void ClickOnUserButton()
		{
			Users.click();
		}

	
	//Click on Create User button
		public void ClickOnCreateUserButton()
		{
			CreateUser.click();
		}
		
	//Click for instructor no
		public void ClickToInstrNo()
		{
			
			InstrNumber.click();
		}
		
	//Click for instructor no_n
		public void ClickToInstrNo_b()
		{
			InstrNumber_b.click();
		}
		
		public void ClickToInstrNoH()
		{
			//if(InstrNumberH.isSelected())
			InstrNumberH.click();
		}
		
	//Click on User button
		public void ClickOnSaveChangesButton()
		{
			SaveChanges.click();
		}
		

		//DOB
		
		//Select Month
		@FindBy(css="#dob_month")
		WebElement Month;
		
		public void SelectMonth(String month)
		{
			new Select(Month).selectByVisibleText(month);			
		}
		
		//Select Day
		@FindBy(css="#dob_day")
		WebElement Day;
				
		public void SelectDay(String day)
		{
			new Select(Day).selectByVisibleText(day);			
		}
		
		//Select Year
		@FindBy(css="#dob_year")
		WebElement Year;
				
		public void SelectYear(String year)
		{
			new Select(Year).selectByVisibleText(year);			
		}

				
		@FindBy(css=".alert-flash.alert.alert-success")
		public WebElement SuccessMessage;
		
		
		//Fill New User form
		public void FillCertificationForm(
												String username,
												String password,
												String confirmpassword,
												String firstname,
												String lastname,
												String gender,
												String instrlevel,
												String region,
												String county,
												String address,
												String city,
												String state,
												String zip,
												String email)
				{
					this.EnterUsername(username);
					this.EnterPassword(password);
					this.EnterConfirmPassword(confirmpassword);
					this.EnterFirstName(firstname);
					this.EnterLastName(lastname);
					this.SelectGender(gender);
					this.SelectInstrLevel(instrlevel);
					this.SelectRegion(region);
					this.SelectCounty(county);
					this.EnterAddress(address);
					this.EnterCity(city);
					this.SelectState(state);
					this.EnterZip(zip);
					this.EnterEmail(email);
				}
				
		
		//Create New User
			public void CreateNewUser(
											String username,
											String password,
											String confirmpassword,
											String firstname,
											String lastname,
											String gender,
											String instrlevel,
											String region,
											String county,
											String address,
											String city,
											String state,
											String zip,
											String email) throws InterruptedException
		        {
					FillCertificationForm(username, password, confirmpassword,  firstname, lastname, gender, instrlevel, region, county, address, city, state, zip, email);
				}	
			
			
	// New Changes By SD
			public void CreateNewUser2(
					String username,
					String password,
					String confirmpassword,
					String firstname,
					String lastname,
					String gender,
					String instrlevel,
					String region,
					String county,
					String address,
					String city,
					String state,
					String zip,
					String email) throws InterruptedException
			{
				FillCertificationForm2(username, password, confirmpassword, firstname, lastname, gender, instrlevel, region, county, address, city, state, zip, email);
			}	
	
	
			public void FillCertificationForm2(
					String username,
					String password,
					String confirmpassword,
					String firstname,
					String lastname,
					String gender,
					String instrlevel,
					String region,
					String county,
					String address,
					String city,
					String state,
					String zip,
					String email)
			{
					this.EnterUsername(username);
					this.EnterPassword(password);
					this.EnterConfirmPassword(confirmpassword);
					this.EnterFirstName(firstname);
					this.EnterLastName(lastname);
					this.SelectGender(gender);
					this.SelectInstrLevel(instrlevel);
					this.SelectRegion(region);
					this.SelectCounty(county);
					this.EnterAddress(address);
					this.EnterCity(city);
					this.SelectState(state);
					this.EnterZip(zip);
					this.EnterEmail(email);
}
}
