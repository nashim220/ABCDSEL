package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

		public class CreateNewUserPage {
	
		WebDriver driver;
		JavaHelpers JH = new JavaHelpers();
	
		public CreateNewUserPage(WebDriver driver)
		{	 
			this.driver = driver; 
			
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.WebDriverWaitDuration), this);
		}
	
	
		/*
		 * All WebElements are identified by @FindBy annotation
		 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
		 */ 
		@FindBy(css="i.fa.fa-cog")
		public WebElement ClickonAdmin;	
		
		@FindBy(css="a[href='/users']")
		public WebElement Manageusers;	
		
		@FindBy(css="a.index-header-action")
		public WebElement ClickOnNewUser;
		
		@FindBy(css="#user_agency_id")
		public WebElement SelectAgencyName;	
		
		@FindBy(css="#user_name")
		public WebElement EnterUsername;
		
		@FindBy(css="#user_email")
		public WebElement EnterUserEmailID;
		
		@FindBy(css="#user_password")
		public WebElement  EnterUserPassward;	
		
		@FindBy( css="#user_password_confirmation")
		public WebElement EnterConfirmPassward;	
		
		@FindBy( css="#user_active")	
		public WebElement ClickOnActive;
		
		@FindBy(css="#user-form>fieldset>legend")
		public WebElement RolePermissions;	
		
		@FindBy(css="#user_roles_mask_user_administrator")
		public WebElement ClickOnManageUser;
		
		@FindBy(css="#user_roles_mask_payment_bypasser")
		public WebElement ClickOnByPassPament;
		
		@FindBy(css="#user_roles_mask_certification_editor")
		public WebElement ClickOnEditCertifications;
		
		@FindBy( css="#user_roles_mask_report_viewer")
		public WebElement  ClickOnViewReports;	
		
		@FindBy(css="#user_roles_mask_certification_creator")
		public WebElement ClickOnCertificationCreater;	
		
		@FindBy(css="#user_roles_mask_collection_editor")
		public WebElement ClickOnEditCollections;	
		
		@FindBy(css="#user_roles_mask_notetaker")
		public WebElement ClickOnAddNotes;	
		
		@FindBy(css="#user_roles_mask_card_orderer")
		public WebElement clickonOrderpermanentcertificate;	
		
		@FindBy(css="#user_roles_mask_course_completion_sender")
		public WebElement clickOnsendcourceCompletionDoc;	
		
		@FindBy(css="#user_roles_mask_agency_importer")
		public WebElement clickOnimportcertifications;
		
		@FindBy(css="#user_roles_mask_agency_administrator")
		public WebElement clickOnmanageagencies;
		
		@FindBy(css="input[type='submit']")   
		public WebElement SubmitBtn;	
		
		@FindBy(css="a[href='/certifications'] span")
		public WebElement CM;
		
		public void ClickOnCM()
		{
			CM.click();
		}
		
		
		
		//div.page-header>h1
		@FindBy(css="div.page-header>h1")
		public WebElement newaccounttext;  
		
       //click on admin tab
  		public void clickonadmintab()
  		{
  			ClickonAdmin.click();  			
  		}
  		
  	    //click on manage user tab
  		public void ClickOnManageusers()
  		{
  			Manageusers.click();  			
  		} 
  		
  		//click on newuser tab
  		public void ClickOnNewUserTab()
  		{
  			ClickOnNewUser.click();  			
  		}
  		
        //select agency from the list
  		public void SelectAgencyNamefromList()
  		{
  			SelectAgencyName.click();  			
  		}
  		
  		//enter user name 
  		public void EnterUsernametext(String username)
  		{
  			EnterUsername.clear();
  			EnterUsername.sendKeys(username);
  		}
     
  		public void EnterUserEmail(String username)
  		{
  			EnterUserEmailID.clear();
  			EnterUserEmailID.sendKeys(username);
  		} 
  		
        //enter password for user 
  		public void EnterUserPasswardtext(String passward)
  		{
  			EnterUserPassward.clear();
  			EnterUserPassward.sendKeys(passward);
  		}
  		
  	    //enter confirm password for user 
  		public void EnterConfirmPasswardtext(String cpassward)
  		{
  			EnterConfirmPassward.clear();
  			EnterConfirmPassward.sendKeys(cpassward);
  		}
  		
  	    // click on active check box
  		public void ClickOnActivecheckbox()
  		{   
  			
  			ClickOnActive.click();  			
  		}
  		
  	    // click onRole permission Manage User
  		public void ClickOnManageUsercheckbox()
  		{   
  			
  			ClickOnManageUser.click();  			
  		}
  		
  	    // click onRole permission bypass payments
  		public void ClickOnByPassPamentcheckbox()
  		{   
  			
  			ClickOnByPassPament.click();  			
  		}
  		
  	    // click onRole permission Edit Certifications
  		public void ClickOnEditCertificationscheckbox()
  		{   
  			
  			ClickOnEditCertifications.click();  			
  		}
  		
  	    // click onRole permission ViewReports
  		public void ClickOnViewReportscheckbox()
  		{   
  			
  			ClickOnViewReports.click(); 			
  		}
  		
  	    // click onRole permission Certification Creater
  		public void ClickOnCertificationCreatercheckbox()
  		{   
  			
  			ClickOnCertificationCreater.click(); 			
  		}
  		
  	    // click onRole permission Edit Collections
  		public void ClickOnEditCollectionscheckbox()
  		{   
  			
  			ClickOnEditCollections.click(); 			
  		}
  	    
  		// click onRole permission Add Notes
  		public void ClickOnAddNotescheckbox()
  		{   
  			
  			ClickOnAddNotes.click(); 			
  		}
  		
  	    // click onRole permission Order permanent certificate
  		public void ClickonOrderpermanentCertificatecheckbox()
  		{   

  			clickonOrderpermanentcertificate.click(); 			
  		}  		
  	
  	   // click onRole permission Order sendcourceCompletionDoc
  		public void ClickOnsendcourceCompletionDoccheckbox()
  		{   
  			
  			clickOnsendcourceCompletionDoc.click();  			
  		}
  		
  	    // click onRole permission Order import certifications
  		public void ClickOnimportCertificationscheckbox()
  		{   
  			
  			clickOnimportcertifications.click();  			
  		}
  	  	
  		// click onRole permission Order Submit Btn
  		public void ClickOnSubmitBtnButton()
  		{   
  		      SubmitBtn.click();			
  			
  		} 
  		
  	    @FindBy(css="input[placeholder='User Info']")
  	    public WebElement UserInfotoSerach;
  	    
  	    public void EnterSeachUserInfotoSerach()
		{
  		     UserInfotoSerach.clear();
  		     UserInfotoSerach.sendKeys();
		}   
  	    
 	    @FindBy(css="button[class='btn btn-primary']")
  	    public WebElement UserSerach;  
 	    
  	    public void ClickUserSerach()
		{   
  		UserSerach.click();  					
		}   
  	    
  	    @FindBy(css="a[class='btn btn-small pull-right edit-info']")
	    public WebElement EditInfo;	
  	    
	    public void ClickEditInfo()
		{   
	    	EditInfo.click();					
		}
	   
        
  		@FindBy(id="agency_id")
  		public WebElement Agencyname;
  		
  		@FindBy(css="fieldset.span4>input")
  		public WebElement userinfo;
  		
  		public void EnterUserinfo(String name)
  		{
  			userinfo.clear();
  			userinfo.sendKeys(name);
  		}
  		
  		@FindBy(css="button.btn.btn-primary")
  		public WebElement Searchuser;
  		
  		public void ClcikOnSearch()
  		{
  			Searchuser.click();
  		}	
  		
  		@FindBy(css="section>ol>li:nth-of-type(1)>header>div>h4>a")
  		public WebElement clickonuser;
  		
  		public void Cliconusertoedit()
  		{
  			clickonuser.click();
  		}
  		
  		@FindBy(css="a.btn.btn-small.pull-right.edit-info")
  		public WebElement edittab;
  		
  		public void Clickonedittab()
  		{
  		
  			edittab.click();
  		} 
  		
  		//There was a problem saving this new user.
  		@FindBy(css="div.alert.alert-error")
  		public WebElement ErrorMsg;
  		
  		//has already been taken
  		@FindBy(css="span.help-inline")
  		public WebElement ErrorMsgEmail;
  		
  		@FindBy(css="a[class='btn btn-navbar']")
  		public WebElement Forgotpass; 
  		
  		public void ClickOnForgotpa()
  		{
  			Forgotpass.click();
  		}
  		
  		@FindBy(css="div.actions>input")
  		public WebElement SendBtn;  
  		
  		public void SendBtnClick()
  		{
  			SendBtn.click();
  		}  
  		
  		@FindBy(css="div.alert.alert-error")
  		public WebElement ActiveErrorMsg;
  		
  		// For Log out 
  		 
	 	@FindBy(css="i[class='fa fa-user']")
	 	public WebElement username;
	 	
	 	public void ClickOnusername()
  		{
  			 username.click();
  		}
	 	 
  		@FindBy(css="a[href='/logout']")
  		public WebElement Logout;
  		
  		 public void ClickonLogout()
  		 {  
  			 Logout.click();
  		 }
  		
  		 //disable test 
  		 @FindBy(css="#profile-header>hgroup>h1>span")
  		 public WebElement Gettext;
  		 
  		 //Fill New User form
 		public void FillUserForm(String Name,String emailaddress,String Password,String conPassword)											
 									{
 					this.EnterUsernametext(Name);
 					this.EnterUserEmail(emailaddress);
 					this.EnterUserPasswardtext(Password);
 					this.EnterConfirmPasswardtext(conPassword);
 					
 				}
 	//Forget password
 		@FindBy(css="div.main-inner > div.container>a")
  		public WebElement Forgtepassword;
  		
  		 public void ClickonForgtepassword()
  		 {  
  			Forgtepassword.click();
  		 }
  		//Reset Password email address
  		@FindBy(css="#address")
   		public WebElement forgtemail;
   		
   		 public void Forgetemail(String email)
   		 {  
   			forgtemail.sendKeys(email);
   		 }
   		 	 
   }
