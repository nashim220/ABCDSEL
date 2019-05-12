package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class ProfilePage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public ProfilePage(WebDriver driver)
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
	
		@FindBy(css="a.edit-info")
	    public WebElement EditInfo;
		
		@FindBy(css="#profile-header h1")
	    public WebElement Name;
		
		@FindBy(css="#profile-general dl dd")
	    public WebElement Agency;
		
		@FindBy(css="#profile-roles dl dt")
	    public WebElement Roles;
		
		@FindBy(css="#activity-stream header")
	    public WebElement Activity;
		
		//Edit page
		@FindBy(css="div.page-header h1")
	    public WebElement Edit_Header;
		
		@FindBy(id="user_name")
	    public WebElement Edit_Name;
		
			//Error
			@FindBy(css="div.user_name")
		    public WebElement Edit_Name_Error;
		
		@FindBy(id="user_email")
	    public WebElement Edit_Email;
		
		
		
			//Error
			@FindBy(css="div.user_email")
		    public WebElement Edit_Email_Error;
		
		@FindBy(id="user_password")
	    public WebElement Edit_Password;
		
		@FindBy(id="user_password_confirmation")
	    public WebElement Edit_Password_Confirmation;
		
			//Error
			@FindBy(css="div.user_password_confirmation")
		    public WebElement Edit_Password_Confirmation_Error;

		@FindBy(id="user_active")
	    public WebElement Edit_Active;
		
		@FindBy(id="permissions-wrap")
	    public WebElement Edit_Role_Permission_section;
			
			@FindBy(css="#has-permissions a")
		    public WebElement Edit_Role_Permission_RemoveAllLink;
			
			//Can manage users.
			@FindBy(id="user_roles_mask_user_administrator")
		    public WebElement Edit_Role_Permission_CanManageUser;
			
			//Can bypass payments.
			@FindBy(id="user_roles_mask_payment_bypasser")
		    public WebElement Edit_Role_Permission_CanBypassPayments;
			
			//Can edit certifications.
			@FindBy(id="user_roles_mask_certification_editor")
		    public WebElement Edit_Role_Permission_CanEditCertifications;
			
			//Can view reports.
			@FindBy(id="user_roles_mask_report_viewer")
		    public WebElement Edit_Role_Permission_CanViewReports;
			
			//Can create certifications.
			@FindBy(id="user_roles_mask_certification_creator")
		    public WebElement Edit_Role_Permission_CanCreateCertification;
			
			//Can edit collections.
			@FindBy(id="user_roles_mask_collection_editor")
		    public WebElement Edit_Role_Permission_CanEditCollection;
			
			//Can add notes.
			@FindBy(id="user_roles_mask_notetaker")
		    public WebElement Edit_Role_Permission_CanAddNotes;
			
			//Can order permanent certification cards.
			@FindBy(id="user_roles_mask_card_orderer")
		    public WebElement Edit_Role_Permission_CanOrderPermanetCertificationCards;
			
			//Can send course completion documents.
			@FindBy(id="user_roles_mask_course_completion_sender")
		    public WebElement Edit_Role_Permission_CanSendCourseCompletionDocuments;
			
			//Can import certifications for their agency
			@FindBy(id="user_roles_mask_agency_importer")
		    public WebElement Edit_Role_Permission_CanImportCertificate;
			
		
		@FindBy(css="input[type='submit']")
	    public WebElement Edit_Submit;
		
		
		//Alerts
		@FindBy(css="div.alert-error")
	    public WebElement Error_AlertText;
		
		@FindBy(css="div.alert-success")
	    public WebElement Success_AlertText;
		
		//Street Address1
	    @FindBy(id="certification_p_address1")
	    public WebElement StreetAddress;
	    
	    public void EnterStreetAddress(String address)
		{
	    	StreetAddress.clear();
			StreetAddress.sendKeys(address);
		}
		
	    // certification address2
		@FindBy(id="certification_s_address1")
		public WebElement  caddress;
		
		public void Entercaddress2(String caddress2)
		{
			caddress.clear();
			caddress.sendKeys(caddress2);
		}	
		
		
			
	
	
	
		
}
