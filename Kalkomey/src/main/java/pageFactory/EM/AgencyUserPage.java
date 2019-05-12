package pageFactory.EM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Constants;
import utility.JavaHelpers;

public class AgencyUserPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public AgencyUserPage(WebDriver driver)
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
	
		@FindBy(css="a[href='http://my-webtest1.register-ed.com/agency/user']")
		WebElement Users;
	
		@FindBy(css=".create-instructor")
	    WebElement CreateUser;
		
		@FindBy(css="#UserType_98")
	    WebElement Course1;
		
		@FindBy(css="#UserType_99")
	    WebElement Course2;
		
		@FindBy(css="#UserType_100")
	    WebElement Course3;
		
		@FindBy(css="#UserType_118")
	    WebElement Course4;
		
		@FindBy(css="#UserType_119")
	    WebElement Course5;
		
		@FindBy(css="#UserType_132")
	    WebElement Course6;
		
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
		
		@FindBy(css="#instructor_number_override_instructor_number_b")
		WebElement InstrNumber_b;
		
		@FindBy(css="#pd_instructor_level")
		WebElement InstrLevel;
		
		@FindBy(css="#pd_instructor_region")
		WebElement Region;
		
		@FindBy(css="#pd_instructor_county")
		WebElement County;
		
		@FindBy(css="#pd_instructor_p_address_1")
		WebElement Address;
	
		@FindBy(css="#pd_instructor_p_city")
		WebElement City;
		
		@FindBy(css="#pd_instructor_p_state")
		WebElement State;
		
		@FindBy(css="#pd_instructor_p_zip")
		WebElement Zip;
		
		@FindBy(css=".btn.btn-success")
		WebElement SaveChanges;
		
		@FindBy(css=".page-header.page-header-compressed>h2")
		WebElement PageHeader;

		@FindBy(css="#pd_instructor_number")
		WebElement InstructorNo;

		@FindBy(css="#pd_instructor_number_b")
		WebElement InstructorNo_b;

		@FindBy(css="#search_type")
		WebElement SearchType;

		@FindBy(css="#search_data")
		WebElement SearchData;
		
		@FindBy(css=".btn.btn-primary")
		WebElement Search;

		@FindBy(css=".btn.btn-danger")
		WebElement Cancel;

		@FindBy(css="#email")
		WebElement Email;

		
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
			

	//Select Role for course 2
		public void SelectCourse2(String course2)
		{
			new Select(Course2).selectByVisibleText(course2);			
		}
		
	//Select Role for course 3
		public void SelectCourse3(String course3)
		{
			new Select(Course3).selectByVisibleText(course3);			
		}

	//Select Role for course 4
		public void SelectCourse4(String course4)
		{
			new Select(Course4).selectByVisibleText(course4);			
		}

	//Select Role for course 5
		public void SelectCourse5(String course5)
		{
			new Select(Course5).selectByVisibleText(course5);			
		}

	//Select Role for course 6
		public void SelectCourse6(String course6)
		{
			new Select(Course6).selectByVisibleText(course6);			
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
		
	//Click on User button
		public void ClickOnSaveChangesButton()
		{
			SaveChanges.click();
		}
		
		
	//Description Edit for Program Config options
	
	@FindBy(name="ConfigOption[config_option_desc]")
	public WebElement Desc;
	
	public void Enter_Config_Desc() throws InterruptedException
	{
		//Thread.sleep(2000);
		
		String desc =  "<p>test - 17th Oct 2016 This will be the title seen on the pages where students select events to register for. It should contain the name of the program and some indication of the action being taken. For example, 'Register for Your Proctored Exam'.</p>";
		Desc.clear();
		Desc.sendKeys(desc);
	}
		
	
	@FindBy(css="#type_event > div:nth-child(5) > div > form > div.fields > div:nth-child(1) > div > textarea")
	WebElement EventDesc;
	
	public void Enter_EventConfig_Desc() throws InterruptedException
	{
		String Event =  "<p>Limited event information for full events can be displayed on the calendar and list views by changing the setting below.</p>";
		EventDesc.clear();
		EventDesc.sendKeys(Event);
	}	
		
	
	@FindBy(css="#type_payment > div:nth-child(2) > div > form > div.fields > div:nth-child(1) > div > textarea")
	WebElement PaymentDesc;
	
	public void Enter_PaymentConfig_Desc() throws InterruptedException
	{
		Thread.sleep(2000);
		String Paymentdesc =  "Payment Mode (Off/On)";
		PaymentDesc.clear();
		PaymentDesc.sendKeys(Paymentdesc);
	}
	
	@FindBy(css="#type_approval > div:nth-child(2) > div > form > div.fields > div:nth-child(1) > div > textarea")
	 WebElement ApprovalDesc;
	
	public void Enter_ApprovalConfig_Desc() throws InterruptedException
	{
		Thread.sleep(2000);
		String Approvaldesc =  "Event Publish Approvals (Off/On)";
		ApprovalDesc.clear();
		ApprovalDesc.sendKeys(Approvaldesc);
	}
	
	@FindBy(css="#type_certificate > div:nth-child(5) > div > form > div.fields > div:nth-child(1) > div > textarea")
	WebElement CertificateDesc;
	
	public void Enter_CertificateConfig_Desc() throws InterruptedException
	{
		Thread.sleep(2000);
		String Certificatedesc =  "Allow administrators and instructors to download student certificates.";
		CertificateDesc.clear();
		CertificateDesc.sendKeys(Certificatedesc);
	}	
	
	@FindBy(css="#type_notification > div:nth-child(3) > div > form > div.fields > div:nth-child(1) > div > textarea")
	WebElement NotificationDesc;
	
	public void Enter_NotificationConfig_Desc() throws InterruptedException
	{
		Thread.sleep(2000);
		String Notedesc =  "How many days after the event?";
		NotificationDesc.clear();
		NotificationDesc.sendKeys(Notedesc);
	}
	
	
	@FindBy(css="#type_studentform > div > div > form > div.fields > div:nth-child(1) > div > textarea")
	WebElement Studentform;
	
	public void Enter_StudentformConfig_Desc() throws InterruptedException
	{
		Thread.sleep(2000);
		String Studentformdesc =  "<p>Enter any special instructions for the student results form here.</p>";
		Studentform.clear();
		Studentform.sendKeys(Studentformdesc);
	}
		
	@FindBy(css="#type_report_detail > div:nth-child(2) > div > form > div.fields > div:nth-child(1) > div > textarea")
	WebElement ReportOptions;
	
	public void Enter_ReportConfig_Desc() throws InterruptedException
	{
		Thread.sleep(2000);
		String ReportOptionsdesc =  "<p>Enter any special instructions for the event form here.</p>";
		ReportOptions.clear();
		ReportOptions.sendKeys(ReportOptionsdesc);
	}
	
	
		
	//Fill New User form
			public void FillCertificationForm(
													String course1,
													String course2,
													String course3,
													String course4,
													String course5,
													String course6,
													String username,
													String password,
													String confirmpassword,
													String passwordhint,
													String secretque,
													String secretans,
													String firstname,
													String lastname,
													String gender,
													String instrlevel,
													String region,
													String county,
													String address,
													String city,
													String state,
													String zip)
					{
						this.SelectCourse1(course1);
						this.SelectCourse2(course2);
						this.SelectCourse3(course3);
						this.SelectCourse4(course4);
						this.SelectCourse5(course5);
						this.SelectCourse6(course6);
						this.EnterUsername(username);
						this.EnterPassword(password);
						this.EnterConfirmPassword(confirmpassword);
						this.EnterPassHint(passwordhint);
						this.EnterSecretQue(secretque);
						this.EnterSecretAns(secretans);
						this.EnterFirstName(firstname);
						this.EnterLastName(lastname);
						this.SelectGender(gender);
						this.SelectInstrLevel(instrlevel);
						this.SelectRegion(region);
						this.SelectCounty(county);
						this.EnterAddress(address);
						this.EnterCity(city);
						this.SelectState(state);
						this.EnterZip(zip);;
						
					}
					
			
			//Create New User
				public void CreateNewUser(
												String course1,
												String course2,
												String course3,
												String course4,
												String course5,
												String course6,
												String username,
												String password,
												String confirmpassword,
												String passwordhint,
												String secretque,	
												String secretans,
												String firstname,
												String lastname,
												String gender,
												String instrlevel,
												String region,
												String county,
												String address,
												String city,
												String state,
												String zip) throws InterruptedException
			        {
						FillCertificationForm(course6, course2, course3, course4, course5, course6, username, password, confirmpassword, passwordhint, secretque, secretans, firstname, lastname, gender, instrlevel, region, county, address, city, state, zip);
					}	
}
