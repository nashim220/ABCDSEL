package pageFactory.CM;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class EditCertificationPage {
	
	//edit page 
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public EditCertificationPage(WebDriver driver)
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
	
		//Select Jurisdiction from the list
		@FindBy(id="state")	
		public WebElement Jurisdiction;
		
		// Select Jurisdiction Type  from the list
		@FindBy(id="certification_type_id")
		public WebElement JurisdictionType;
		
		//Select Jurisdiction Card Name  from the list
		@FindBy(id="certification_collection_id")
		public WebElement JurisdictionCardName;	
	
		//StudentInfo for search
		@FindBy(css="input[placeholder='Student Info']")
		public WebElement StudentInfoforSErch; 
		
		public void EnterStudentInfoforSErch(String Name)
		{
			StudentInfoforSErch.clear();
			StudentInfoforSErch.sendKeys(Name);
		}
		
		//Click on Serch 	
		@FindBy(css="button.btn.btn-primary")
		public WebElement  SearchButton;
		
		public void ClickOnSearch()
		{
	
			SearchButton.click();
		}
		
		//click on searched student
		@FindBy(css="li:nth-of-type(1) header div.span11 h4 a")
		public WebElement ClickOnStudentName;
		
		public void ClickOnStudentNametab()
		{
		
			ClickOnStudentName.click();
		}	
		
		// Click On Edit Button 
		@FindBy(css="a.btn.btn-small.pull-right.edit-info")
		public WebElement  EditTab;
		
		public void ClickOnEdittab()
		{
		
			EditTab.click();
		}	
		
		// First name 
		@FindBy(css="input#certification_first_name")
		public WebElement Firstname;
		
		public void EnterFirstname(String FirstNAme)
		{
		Firstname.clear();
		Firstname.sendKeys(FirstNAme);
		}
		
		//Middle name 
		@FindBy(css="input#certification_middle_name")
		public WebElement MiddleName;
		
		public void EnterMiddleName(String MiddleName1)
		{
			MiddleName.clear();
			MiddleName.sendKeys(MiddleName1);
		}
		
		//Lastname
		@FindBy(css="input#certification_last_name")
		public WebElement Lastname;
		
		public void EnterLastname(String Lastname1)
		{
			Lastname.clear();
			Lastname.sendKeys(Lastname1);
		}
		
		// 	Suffix
		@FindBy(css="#certification_suffix")
		public WebElement Suffix;
		
		public void Entersuffix(String saf)
		{
			Suffix.clear();
			Suffix.sendKeys(saf);
		}	
		
	//DateOf Birth
	@FindBy(css="input#certification_dob")
	public WebElement DOB;
	
	public void EnterDOB(String dateofbirth)
	{
		DOB.clear();
		DOB.sendKeys(dateofbirth);
	}	
	
	//Email 
	@FindBy(css="input#certification_email")
	public WebElement Email;
	
	public void EnterEmail(String Emailid)
	{
		Email.clear();
		Email.sendKeys(Emailid);
	}	
	
	// Physical Address	
	//country 
	@FindBy(id="p_country_id")
	public WebElement Country;	
	
	//Street Address1
    @FindBy(id="certification_p_address1")
    public WebElement StreetAddress;
    
    public void EnterStreetAddress(String address)
	{
    	StreetAddress.clear();
		StreetAddress.sendKeys(address);
	}
    
    // certification address2
	@FindBy(id="certification_s_address2")
	public WebElement  caddress;
	
	public void Entercaddress2(String caddress2)
	{
		caddress.clear();
		caddress.sendKeys(caddress2);
	}
	
	
	// certificationcity
	@FindBy(id="certification_p_city")
	public WebElement certificationcity;
	
	public void Entercertificationcity(String city)
	{
		certificationcity.clear();
		certificationcity.sendKeys(city);
	}
	
	//state"
	@FindBy(id="p_state")
    public WebElement State;
	
	//certificationzip
	@FindBy(id="certification_p_zip")
	public WebElement  certificationzip;
	
	public void certificationzip(String zip)
	{
		certificationzip.clear();
		certificationzip.sendKeys(zip);
	}
	
    //phoneno
	@FindBy(id="certification_p_phone")
	public WebElement phoneno;
	
	public void Enterphoneno(String Pno)
	{
		phoneno.clear();
		phoneno.sendKeys(Pno);
	}
	
	//hide HideMailingaddress
	@FindBy(id="has_mailing_address")
	public WebElement HideMailingaddress;
	
	public void  clickOnHideMailingaddress()
	{
		HideMailingaddress.click();
	}	
	
	//Mailing Address	
	//countryid
	@FindBy(css="#s_country_id")
	public WebElement countryid;
	
	
	// streetaddress 
	@FindBy(css="#certification_s_address1")
	public WebElement streetaddress;
	
	public void Enterstreetaddress(String SAddress)
	{
		streetaddress.clear();
		streetaddress.sendKeys(SAddress);
	}	
	
	// address 2
	@FindBy(css="#certification_s_address2")
	public WebElement Address2;
	
	public void EnterAddress2(String Address)
	{
		Address2.clear();
		Address2.sendKeys(Address);
	}
	
	@FindBy(css="#certification_s_city")
	public WebElement Citymailing;
	
	public void EnterCitymailing(String city)
	{
		Citymailing.clear();
		Citymailing.sendKeys(city);
	}
	
	@FindBy(css="#s_state")
	public WebElement SelectMailingstate;	
	
	@FindBy(css="#certification_s_zip")
	public WebElement Zipmailing;
	
	public void EnterZipmailing(String zip1)
	{
		Zipmailing.clear();
		Zipmailing.sendKeys(zip1);
	}	
	
	@FindBy(css="#certification_s_phone")
	public WebElement Phonemailing;
	
	public void EnterPhonemailing(String phone1)
	{
		Phonemailing.clear();
		Phonemailing.sendKeys(phone1);
	}
	
	//Other Info
	//other state 
	@FindBy(css="#certification_status")
	public WebElement OtherStatus;
	
	@FindBy(id="certification_certified_at")
	public WebElement certificationDate;
	
	public void EntercertificationDate(String certiDate)
	{
		certificationDate.clear();
		certificationDate.sendKeys(certiDate);
	}
	
	@FindBy(id="certification_issued_at")
	public WebElement IssueDate;
	
	public void EnterIssureDate(String IssuDate)
	{
		IssueDate.clear();
		IssueDate.sendKeys(IssuDate);
	}
	
	@FindBy(id="certification_gender")
	public WebElement Genderother;	
	
	@FindBy(id="certification_height")
	public WebElement Heightother;
	
	public void GetHeight(String High)
	{
		Heightother.clear();
		Heightother.sendKeys(High);
	}	
	
	@FindBy(id="certification_weight")
	public WebElement Weightother;	
	
	public void GetWeight(String wet)
	{
		Weightother.clear();
		Weightother.sendKeys(wet);
	}
	
	@FindBy(id="certification_eye_color")
	public WebElement EyeColor;	
	
	public void GetEyeColor(String color)
	{
		EyeColor.clear();
		EyeColor.sendKeys(color);
	}	
	
	@FindBy(id="certification_hair_color")
	public WebElement HairColor;
	
	public void GetHaircolor(String hcolor)
	{
		HairColor.clear();
		HairColor.sendKeys(hcolor);
	}	
	
	@FindBy(css="input.btn.btn-primary")
	public WebElement Submitt;
	
	public void Getsubmitt()
	{
		//Submitt.click();
		Submitt.sendKeys(Keys.ENTER);
	}
	
//Physical Address Text is present	
	@FindBy(css="#physical>legend")
	public WebElement TestPresent;
	
	public void TestPresent()
	{
		TestPresent.getText();
	}	
	
	@FindBy(css="#address_edit>fieldset>legend")
	public WebElement OtherInfo;
	
	public void TextOtherinfo()
	{
		OtherInfo.getText();
	}	
	
	@FindBy(css="#physical_address_check div button.btn.btn-default")
	public WebElement useraddress1;	
	
	public void ClickOnuseaddress1()
	{
		useraddress1.click();
	}	
	
	@FindBy(css="#mailing_address_check div button.btn.btn-default")
	public WebElement useraddress2;	
	
	public void ClickOnuseaddress2()
	{
		useraddress2.click();
	}	
	
	@FindBy(css="input.btn.btn-success")
	public WebElement  SaveBtn;
	
	public void ClickOnSaveBtn()
	{
		SaveBtn.click();
	} 
	  
	@FindBy(css="div.alert.alert-success")
	public WebElement successMag;	
	
	@FindBy(css="#certification_certification_collection_id")
	public WebElement certificationcollection;
	
	@FindBy(css="div.span8>h1")
	public WebElement studentnametext;	
	
	public void Getstudentnametext()
	{
		studentnametext.getText();
	}
	
	//dl.span3>dd
	@FindBy(css="dl.span3>dd")
	public WebElement  Studentdata;
		
	@FindBy(css="div.page-header>h1")
	public WebElement StudentInfoPage;
	@FindBy(css="a[href='/certifications']")
	public WebElement 	certificationManager;
	
	public void ClickOncertificationManager()
	{
		certificationManager.click();
	}
	
	// Detail button  located on the right, under the words 'Activitiy in the Last Month'
	@FindBy(css="#activity-stream ul li:nth-of-type(1) div:nth-of-type(2) p button")
	public WebElement 	DetailsLink;
	
	public void ClickOnDetailsLink()
	{
		DetailsLink.click();
	}
	
	//  Name and/or city and/or birthdate (mm/dd/yyyy)

	@FindBy(css=".span4>input")
	public WebElement SearchCM;
	
	public void SearchCM(String value)
	{
		SearchCM.clear();
		SearchCM.sendKeys(value);
	}	
	
	
}
