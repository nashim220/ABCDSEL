package pageFactory.CM;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class RolesPermissionsPage {
	
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public RolesPermissionsPage(WebDriver driver)
	{	 
        this.driver = driver;        
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.WebDriverWaitDuration), this);
	}

	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */ 
	
	public void clickonAllpremission() throws InterruptedException
	{
		 Thread.sleep(1000);
		ClickOnManageUser();
		ClickOnByPassPament();
		ClickOnEditCertifications();
		ClickOnViewReports();
		Thread.sleep(1000);
		ClickOnCertificationCreater();
		ClickOnEditCollections();
		ClickOnAddNotes();
		ClickOnCanOrderpermanentCertificationCards();
		ClickOnSendCourceCompletionDoc();
		Thread.sleep(1000);
		ClickOnCanImportCertificationsForAgency();
		ClickOnCanManageAgencies();
	}
	@FindBy(css="header.widget-header>h3")
	public WebElement SeacrhuserHeader;
	
	@FindBy(id="agency_id")
	public WebElement AgencyName;
	
	@FindBy(css="fieldset.span4>input")
	public WebElement UserInfo;
	
	public void EnterUserInfo(String Name)
	{
		UserInfo.sendKeys(Name);
	}
	
	@FindBy(css=".page-header>h1")
	public WebElement EditcollectionHeader;
	

	@FindBy(css="button.btn.btn-primary")
	public WebElement SearchButton;
		
	public void ClickOnSeachButton()
	{
		SearchButton.click();
	}
	
	@FindBy(css="li:nth-of-type(1) header div.span12 h4 a ")
	public WebElement  Firstuser;
	
	public void ClickOnFirstUser()
	{
		Firstuser.click();
	}
	@FindBy(css="a.btn.btn-small.pull-right.edit-info")
	public WebElement EditInfoLink;
	
	public void ClickOnEditInfo()
	{
		EditInfoLink.click();
	}
	
	
	
	@FindBy(css="#user-form>fieldset>legend")
	public WebElement RolePermissions;	
	
	
	@FindBy(id="user_roles_mask_user_administrator")
	public WebElement ManageUser;
	
	public void ClickOnManageUser()
	{
		ManageUser.click();
	}
	
	
	
	@FindBy(css="#physical_address_check div:nth-of-type(1) button")
	public WebElement Physicalpopup;
	
	public void ClickOnPhysicalpopup()
	{
		Physicalpopup.sendKeys(Keys.ENTER);
	}
	@FindBy(css="div.page-header h1")
    public WebElement PageHeader;	

	@FindBy(css="#certification_first_name")
    public WebElement FirstName;
		
		@FindBy(css="div.certification_first_name p.help-block")
	    public WebElement FirstName_Error;
	
	@FindBy(id="certification_middle_name")
	public WebElement MiddleName;
	
	@FindBy(css="#certification_last_name")
	public WebElement LastName;
		
		@FindBy(css="div.certification_last_name p.help-block")
	    public WebElement LastName_Error;
	
	@FindBy(css="#certification_suffix")
	public WebElement Suffix;
	
	@FindBy(css="#certification_dob")
	public WebElement DOB;
	
		@FindBy(css="div.certification_dob p.help-block")
	    public WebElement DOB_Error;
	
	@FindBy(css="#certification_email")
	public WebElement EmailID;
	
	
	
		//Physical Address
	
		@FindBy(css="#p_country_id")
		public WebElement CountryID;
		
			@FindBy(css="div.certification_p_country_id p.help-block")
		    public WebElement CountryID_Error;
		
		@FindBy(css="#certification_p_address1")
		public WebElement Address1;
		
			@FindBy(css="div.certification_p_address1 p.help-block")
		    public WebElement Address1_Error;
	  
		@FindBy(css="#certification_p_city")
		public WebElement City;
		
			@FindBy(css="div.certification_p_city p.help-block")
		    public WebElement City_Error;
		
		@FindBy(css="#p_state")
		public WebElement State;
		
		@FindBy(css="#certification_p_zip")
		public WebElement Zip;
		
			@FindBy(css="div.certification_p_zip p.help-block")
		    public WebElement Zip_Error;
		
		@FindBy(css="#certification_p_phone")
		public WebElement Phone;
		
		
		//Mailing Address
		
		@FindBy(css="#s_country_id")
		public WebElement MCountryID;
		
		@FindBy(css="#certification_s_address1")
		public WebElement MAddress1;
	  
		@FindBy(css="#certification_s_city")
		public WebElement MCity;
		
		@FindBy(css="#s_state")
		public WebElement MState;
		
		@FindBy(css="#certification_s_zip")
		public WebElement MZip;
		
		@FindBy(css="#certification_s_phone")
		public WebElement MPhone;
		
	
	@FindBy(css="#has_mailing_address")
	public WebElement AddressCheckbox;
	
	@FindBy(css="#jurisdictions")
	public WebElement Jurisdictions;
	
	@FindBy(css="#certification_certification_collection_id")
	public WebElement CollectionID;
	
		@FindBy(css="div.certification_certification_collection_id p.help-block")
	    public WebElement CollectionID_Error;
	
	@FindBy(css="#certification_status")
	public WebElement Status;
	
		@FindBy(css="div.certification_status p.help-block")
	    public WebElement Status_Error;
	
	@FindBy(css="#certification_certified_at")
	public WebElement CertiDate;
	
		@FindBy(css="div.certification_certified_at p.help-block")
	    public WebElement CertiDate_Error;
	
	@FindBy(css="#certification_issued_at")
	public WebElement LastIssued;
	
	@FindBy(id="certification_gender")
	public WebElement Gender;
	
	@FindBy(id="certification_height")
	public WebElement Height;

	@FindBy(id="certification_weight")
	public WebElement Weight;
	
	@FindBy(id="certification_eye_color")
	public WebElement EyeColor;
	
	@FindBy(id="certification_hair_color")
	public WebElement HairColor;
	
	@FindBy(css="input[value='Continue']")
	public WebElement Continue;
	
	
//Enter FirstName
	public void EnterFirstName(String firstname)
	{
		FirstName.clear();
		FirstName.sendKeys(firstname);
	}
	
//Enter MiddleName
	public void EnterMiddleName(String middlename)
	{
		MiddleName.clear();
		MiddleName.sendKeys(middlename);
	}
	
//Enter LastName
	public void EnterLastName(String lastname)
	{
		LastName.clear();
		LastName.sendKeys(lastname);
	}
	
	
//Enter Suffix
	public void EnterSuffix(String suffix)
	{
		Suffix.clear();
		Suffix.sendKeys(suffix);
	}

//Select DOB
	public void EnterDOB(String dateofbirth)
	{
		DOB.clear();
		DOB.sendKeys(dateofbirth);
	}
	
//Enter Email Address
	public void EnterEmailAddress(String emailaddress)
	{
		EmailID.clear();
		EmailID.sendKeys(emailaddress);
	}

//Select Country
	public void SelectCoutry(String country)
	{
		new Select(CountryID).selectByVisibleText(country);			
	}


//Enter Street Address
	public void EnterStreetAddress(String streetadd)
	{
		Address1.clear();
		Address1.sendKeys(streetadd);
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
	
//Enter Phone
	public void EnterPhone(String phone)
	{
		Phone.clear();
		Phone.sendKeys(phone);
	}

//Select State
	public void SelectState(String state)
	{
		new Select(State).selectByVisibleText(state);			
	}
	
		//Mailing Address
			//Select Country
			public void SelectCoutryM(String country)
			{
				new Select(MCountryID).selectByVisibleText(country);			
			}

		
		//Enter Street Address
			public void EnterStreetAddressM(String streetadd)
			{
				MAddress1.clear();
				MAddress1.sendKeys(streetadd);
			}
					
				
		//Enter City
			public void EnterCityM(String city)
			{
				MCity.clear();
				MCity.sendKeys(city);
			}
					
					
		//Enter Zip
			public void EnterZipM(String zip)
			{
				MZip.clear();
				MZip.sendKeys(zip);
			}
			
		//Enter Phone
			public void EnterPhoneM(String phone)
			{
				MPhone.clear();
				MPhone.sendKeys(phone);
			}
		
		//Select State
			public void SelectStateM(String state)
			{
				new Select(MState).selectByVisibleText(state);			
			}
			
			
			
			

//Select Jurisdiction
	public void SelectJurisdiction(String jurisdiction)
	{
		new Select(Jurisdictions).selectByVisibleText(jurisdiction);			
	}		
	
//Select Collection
	public void SelectCollection(String collection)
	{
		new Select(CollectionID).selectByVisibleText(collection);			
	}	
	
//Select Status
	public void SelectStatus(String status)
	{
		new Select(Status).selectByVisibleText(status);			
	}			
	
//Enter Certificate date
	public void EnterCeritiDate(String certidate)
	{
		CertiDate.clear();
		CertiDate.sendKeys(certidate);
	}		
	
//Enter Issued date
	public void EnterIssuedDate(String issueddate)
	{
		LastIssued.clear();
		LastIssued.sendKeys(issueddate);
	}		
	
	
	
//Click on Continue button
	public void ClickOnContinueButton()
	{
		Continue.click();
	}

	@FindBy(css="#mailing_address_check h4:nth-of-type(2)")
	public WebElement USPSMailingVal;
	
	@FindBy(css="#mailing_address_check h4:nth-of-type(1)")
	public WebElement USPSMailingSuccess;
	
	@FindBy(css="#mailing_address_check div:nth-of-type(2) button")
	public WebElement UseUSPSMailing;
	
	@FindBy(css="#physical_address_check h4:nth-of-type(2)")
	public WebElement USPSPhyVal;
	
	@FindBy(css="#physical_address_check div:nth-of-type(2) button")
	public WebElement UseUSPSPhyl;
	
	
	@FindBy(css="#address_verify_back")
	public WebElement Back;
	
	public void ClickOnBackButton()
	{
		Back.click();
	}
	
	@FindBy(css="#address_verify input")
	public WebElement Save;
	
	public void ClickOnSaveButton()
	{
		Save.click();
	}
	
	@FindBy(css="#physical_address_check h4:nth-of-type(1)")
	public WebElement USPSPhySuccess;
	
//Fill New Certification form
	public void FillCertificationForm(
											String firstname,
											String lastname,
											String dateofbirth,
											String emailaddress,
											String country,
											String streetadd,
											String city,
											String zip,
											String state,
											String jurisdiction,
											String collection,
											String status,
											String certidate,
											String issueddate) throws InterruptedException
			{
				this.EnterFirstName(firstname);
				this.EnterLastName(lastname);
				this.EnterDOB(dateofbirth);
				this.EnterEmailAddress(emailaddress);
				this.SelectCoutry(country);
				Thread.sleep(5000);
				this.EnterStreetAddress(streetadd);
				this.EnterCity(city);
				this.EnterZip(zip);
				this.SelectState(state);
				this.SelectJurisdiction(jurisdiction);
				this.SelectCollection(collection);
				this.SelectStatus(status);
				this.EnterCeritiDate(certidate);
				this.EnterIssuedDate(issueddate);
			}
			
	
	//Create New Certificate
		public void CreateNewCertificate(
										   String firstname,
										   String lastname,
										   String dateofbirth,
										   String emailaddress,
										   String country,
										   String streetadd,
										   String city,
										   String zip,
										   String state,
										   String jurisdiction,
										   String collection,
										   String status,
										   String certidate,
										   String issueddate) throws InterruptedException
	        {
				FillCertificationForm(firstname, lastname, dateofbirth, emailaddress, country, streetadd, city, zip, state, jurisdiction, collection, status, certidate, issueddate);
				/*Thread.sleep(1000);
				ClickOnPhysicalpopup();
				Thread.sleep(1000);*/
				ClickOnContinueButton();
			}
		
		
		
	//Fill Mailing Address
		public void FillMailingAddress(			String country,
												String streetadd,
												String city,
												String zip,
												String state,
												String phone
											) throws InterruptedException
			{
			
				this.SelectCoutryM(country);
				Thread.sleep(5000);
				this.EnterStreetAddressM(streetadd);
				this.EnterCityM(city);
				this.EnterZipM(zip);
				this.SelectStateM(state);
				this.EnterPhoneM(phone);
			}
			
		
		@FindBy(css="li:nth-of-type(1) > div.span10 > h1 > a")
		public WebElement Firstuserentry;
		
		public void ClickOnFirstuserentry()
		{
			Firstuserentry.click();
		}
	
	@FindBy(css=".table.table-striped>tbody>tr:nth-of-type(2) td:nth-of-type(2)")
	public WebElement Rolepremission;
	
	@FindBy(id="user_roles_mask_payment_bypasser")
	public WebElement ByPassPament;
	
	public void ClickOnByPassPament()
	{
		ByPassPament.click();
	}
	
	@FindBy(id="user_roles_mask_certification_editor")
	public WebElement EditCertifications;
	
	public void ClickOnEditCertifications()
	{
		EditCertifications.click();
	}
	
	@FindBy( id="user_roles_mask_report_viewer")
	public WebElement  ViewReports;	
	
	public void ClickOnViewReports()
	{
		ViewReports.click();
	}
	
	@FindBy(id="user_roles_mask_certification_creator")
	public WebElement CertificationCreater;	
	
	public void ClickOnCertificationCreater()
	{
		CertificationCreater.click();
	}
	
	@FindBy(id="user_roles_mask_collection_editor")
	public WebElement EditCollections;	
	
	public void ClickOnEditCollections()
	{
		EditCollections.click();
	}
	
	@FindBy(id="user_roles_mask_notetaker")
	public WebElement AddNotes;	
	
	public void ClickOnAddNotes()
	{
		AddNotes.click();
	}
	
	@FindBy(css="#user_roles_mask_card_orderer")
	public WebElement CanOrderpermanentCertificationCards;
	
	public void ClickOnCanOrderpermanentCertificationCards()
	{
		CanOrderpermanentCertificationCards.click();
	}	
		
	
	@FindBy(id="user_roles_mask_course_completion_sender")
	public WebElement SendCourceCompletionDoc;
	
	public void ClickOnSendCourceCompletionDoc()
	{
		SendCourceCompletionDoc.click();
	}
	
	@FindBy(css="#user_roles_mask_agency_administrator")
	public WebElement CanManageAgencies;
	
	@FindBy(css="#user_roles_mask_agency_importer")
	public WebElement ImportCertificationsForAgency;	
	
	public void ClickOnCanImportCertificationsForAgency()
	{
		ImportCertificationsForAgency.click();
	}
	
	public void ClickOnCanManageAgencies()
	{
		CanManageAgencies.click();
	}
	
	@FindBy(id="user_roles_mask_agency_importer")
	public WebElement Importcertifications;
	
	public void ClickOnImportcertifications()
	{
		Importcertifications.click();
	}
	
	@FindBy(css="a[href='/certifications/activity']")
	public WebElement CertificationActivity;

	
	@FindBy(css="a[href='/certifications'] span.nav-text-container>span>p")
	public WebElement CertificationManager;
	
	public void ClickOnCertificationManager()
	{
		CertificationManager.click();
	}
	
	@FindBy(css="li:nth-of-type(1) header div:nth-of-type(2) h4 a")
	public WebElement FirstUserLink;
	
	public void ClickOnFirstUserLink()
	{
		FirstUserLink.click();
	}
	
	@FindBy(css="div.page-header>h1")
	public WebElement EditStudentInformation;
	
	
	@FindBy(css="a.index-header-action")
	public WebElement NewCertificationLink;
	
	public void ClickOnNewCertificationLink()
	{
		NewCertificationLink.click();
	}
	
	
	@FindBy(css="a[href='/certification_collections']")
	public WebElement ManageCollection;
	
	public void ClickOnManageCollection()
	{
		ManageCollection.click();
	}
	
	@FindBy(css=".search-results li:nth-of-type(1) h4 a ")
	public WebElement FirstCollection;
	
	public void ClickOnFirstCollection()
	{
		FirstCollection.click();
	}
	
	
	@FindBy(css="button.btn.pull-right ")
	public WebElement AddNoteBtn;
	
	public void ClickOnAddNoteBtn()
	{
		AddNoteBtn.click();
	}
	@FindBy(css="#note_note_text")
	public WebElement AddTheNote;
	
	public void EnterAddTheNote(String Name)
	{
		AddTheNote.clear();
		AddTheNote.sendKeys(Name);
	}
	
	@FindBy(css="ol li:nth-of-type(1) p:nth-of-type(2)")
	public WebElement NoteText;
	
	
	@FindBy(css="a.btn.btn-small.order-permcert.pull-right")
	public WebElement OrderRplacementCertificate;
	
	public void ClickOnOrderRplacementCertificate()
	{
		OrderRplacementCertificate.click();
	}
	
	@FindBy(css="h4.span8")
	public WebElement Studentinformationtext;
	
	@FindBy(css="a.btn.btn-small.resend-tempcert.pull-right")
	public WebElement  ResendCourseCompletionLink;
	
	public void ClickOnResendCourseCompletionLink()
	{
		ResendCourseCompletionLink.click();
	}
	
	@FindBy(css="div.alert.alert-info")
	public WebElement AlartMesg;
	
	@FindBy(css="a[href='/reports']")
	public  WebElement ReportPara;	
	public void ClickOnReportPara()
	{
		ReportPara.click();
	}
	
	
	@FindBy(css="#showHide>span:nth-of-type(1)")
	public WebElement ToggleReportsMenu;
	
	
	@FindBy(css="b.caret")
	public WebElement Mycontact;
	public void ClickOnMyContact()
	{
		Mycontact.click();
	}
	@FindBy(css="li.dropdown.open ul.dropdown-menu>li:nth-of-type(1)>a:nth-of-type(1)")
	public WebElement MyProfile;
	public void ClickOnMyProfile()
	{
		MyProfile.click();
	}
	
	@FindBy(css="#has-no-permissions>p")
	public WebElement WithoutPermissions;
	
	@FindBy(css="div ul li:nth-of-type(2) b.caret")
	public WebElement AdminProfile;
	
	public void ClickOnAdminProfile()
	{
		AdminProfile.click();
	}
	
	@FindBy(css="ul.dropdown-menu>li:nth-of-type(1)>a")
	public WebElement AdminProfileview;
	
	public void ClickOnAdminProfileview()
	{
		AdminProfileview.click();
	}
	
	@FindBy(css="#certification_first_name")
	public WebElement EditStudentInformation1;
	
	@FindBy(css="div.page-header>h1")
	public WebElement CreateNewCertification;
	
	@FindBy(css="p.note-text")
	public WebElement NewAddnote;
	
	@FindBy(css="div.page-header>h1")
	public WebElement EditAccount;

	@FindBy(css="fieldset.span10>input")
    public WebElement UserInfo1;
	
	public void EnterUserInfo1(String Name)
	{
		UserInfo1.sendKeys(Name);
	}
	
	@FindBy(css="table.table.table-striped")
	public WebElement RolePermissionAvilabe;
	
	@FindBy(css="div.alert.alert-notice")
	public WebElement NoFound;
	
	
	@FindBy(css="h4.user-email.span4>a")
	public WebElement UserEmailID;
	
	
	
	@FindBy(css="div.page-header>hgroup>h1")
	public WebElement EditBypasspayment;
	
	@FindBy(css="a.btn.btn-small.btn-warning.bypass-payment.pull-right")
	public WebElement ByPassPaymentLink;
	
	public void ClickOnByPassPaymentLink()
	{
		ByPassPaymentLink.click();
	}
	
	@FindBy(css="a[href='/certification_collection_origins']")
	public WebElement ManageCollectionOrigins;
	
	public void ClickOnManageCollectionOrigins()
	{
		ManageCollectionOrigins.click();
	}
	
	@FindBy(css="div.dialog>h1")
	public WebElement YouAreNotAuthorized;
	
	@FindBy(css="#has-permissions>a")
	public WebElement  RemoveAllPermissions;
	
	public void ClickOnRemoveAllPermissions()
	{
		RemoveAllPermissions.click();
	}
	@FindBy(css="#has-no-permissions>p")
	public WebElement WithoutPermissionsThisUser;
	
	public void ClickOnWithoutPermissionsThisUser()
	{
		WithoutPermissionsThisUser.click();
	}
	
	
}
