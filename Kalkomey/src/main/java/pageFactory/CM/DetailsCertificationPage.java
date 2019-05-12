package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class DetailsCertificationPage {
	
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public DetailsCertificationPage(WebDriver driver)
	{	 
        this.driver = driver;        
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.WebDriverWaitDuration), this);
	}

	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */ 
	
	@FindBy(css="a[href='/certifications']")
	public WebElement 	certificationManager;
	
	public void ClickOncertificationManager()
	{
		certificationManager.click();
	}
	
	@FindBy(css="header.widget-header>h3")
	public WebElement Searchcertificate;
	
	@FindBy(css="select#state")
	public WebElement State;
	
	@FindBy(css="select#certification_type_id")
	public WebElement CertType;
	
	@FindBy(css="select#certification_collection_id")
	public  WebElement Cardname;
	
	@FindBy(css="input[placeholder='Student Info']")
	public WebElement StudentDetails;
	
	@FindBy(css="button.btn.btn-primary")
	public WebElement Search;
	
	@FindBy(css="li:nth-of-type(2)>header>div:nth-of-type(2)>h4>a")
	public WebElement Studentnamelink;
	
	public void ClickOnStudentnamelink()
	{
		Studentnamelink.click();
	}
	
	@FindBy(css=".span11>h4>a")
	public WebElement firstcertificate;
	
	public void clickonfirstcertificate()
	{
		firstcertificate.click();
	}
	
	
	@FindBy(css=".action-data")
	public WebElement Studentname1;
	
	@FindBy(css=".action-data>time")
	public WebElement Time;
	
	@FindBy(css="#contact-info div:nth-of-type(1) dl:nth-of-type(1) dt")
	public WebElement PhysicalAddress;
	
	@FindBy(css="#contact-info div:nth-of-type(1) dl[class='span3']:nth-of-type(2) dt")
	public WebElement PhysicalPhoneNumber;
	
	@FindBy(css="#contact-info div:nth-of-type(1) dl[class='span3']:nth-of-type(3) dt")
	public WebElement DOB;
	
	@FindBy(css="#contact-info div:nth-of-type(1) dl[class='span3']:nth-of-type(4) dt")
	public WebElement Gender;
	
	@FindBy(css="#contact-info div:nth-of-type(2) dl[class='span3']:nth-of-type(1) dt")
	public WebElement MaillingAddress;	
	
	@FindBy(css="#contact-info div:nth-of-type(2) dl:nth-of-type(2) dt")
	public WebElement MailingPhoneNumber;
	
	@FindBy(css="#contact-info div:nth-of-type(2) dl[class='span3']:nth-of-type(3) dt")
	public WebElement EmailAddress;
	
	@FindBy(css="a.collapsible-toggler")
	public WebElement ShowmoreInfo;
	
	public void ClickOnShowMoreInfo()
	{
		ShowmoreInfo.click();
	}
	
	@FindBy(css="#additional-student-info>h4")
	public WebElement AdditionalStudentInformation;
	
	
	@FindBy(css="a.btn.btn-small.order-permcert.pull-right")
	public  WebElement OrderReplacementCerti;
	
	public void ClickOnOrderReplacementCerti()
	{
		OrderReplacementCerti.click();
	}
	
	@FindBy(css="hgroup.span8>h1")
	public WebElement OrderReplacementCertificatePge;
	
	
	@FindBy(css="a.btn.btn-small.btn-warning.bypass-payment.pull-right")
	public WebElement BypassPayment;
	
	public void ClickOnBypassPayment()
	{
		BypassPayment.click();
	}
	
	@FindBy(css="#cc-info>legend")
	public WebElement PaymentBypassText;
	
	@FindBy(css="#historical_action_type_id")
	public WebElement ReasonSelect;
	
	
	@FindBy(css="#bypass-reason")
	public  WebElement ReasonSelectText;
	
	public void EnterReasonSelectText(String reason)
	{
		ReasonSelectText.sendKeys(reason);
	}
	
   @FindBy(css="#quantity")
   public WebElement NoOfCard;
   
   @FindBy(css="#continue_button")
   public WebElement ContinueButton;
   
   public void ClickOnContinueButton()
   {
	   ContinueButton.click();
   }
   
   @FindBy(css="div.alert.alert-success>h4")
   public WebElement ByPassPaymenttext;
   
   @FindBy(css="div.page-header>h1")
   public WebElement EditStudentInformation;
   
   @FindBy(css=".action-content")
   public WebElement Activity;
   
   @FindBy(css="#activity-stream>ul[class='span6']>li:nth-of-type(2) >div>h1")
   public WebElement Paymentbypassed;
   
   
   @FindBy(css="#cert-info>dl.row-fluid > dd > a")
   public WebElement AgencyName;
   
   @FindBy(css="#cert-info>dl:nth-of-type(2) dd")
   public WebElement CertificateCollectionName;
   
   @FindBy(css="#cert-info div:nth-of-type(1) dl[class='span3']:nth-of-type(1) dd")
   public WebElement CertificationOrigin;
   
   @FindBy(css="#cert-info div:nth-of-type(2) dl[class='span3']:nth-of-type(1) dd")
   public WebElement CertificateStatus;
   
   
   @FindBy(css="#cert-info div:nth-of-type(2) dl[class='span3']:nth-of-type(2) dd")
   public WebElement CertificationDate;
   
   @FindBy(css="#cert-info div:nth-of-type(2) dl[class='span3']:nth-of-type(3) dd")
   public WebElement LastIssuedAt;
   
   
   @FindBy(css="#cert-info div:nth-of-type(2) dl[class='span3']:nth-of-type(4) dd")
   public WebElement CertificateID;
   
   @FindBy(css="#cert-info div:nth-of-type(3) dl dt")
   public WebElement CardPreview;
   
   //@FindBy(css="#activity-stream > ul > li:nth-child(10) > div.span10 > p > button")
   @FindBy(css="#activity-stream li:nth-of-type(1) button")
   public WebElement  ActivityDetail;
   
   public void ClickOnActivityDetail()
   {
	   ActivityDetail.click();
   }
   
   
   @FindBy(css="#details-11 > dl > dt:nth-child(1)")
  // @FindBy(css="#details-0>dl>dt")
   public WebElement  ActivityDetailShow;
   
   @FindBy(css="#certification_certification_collection_id")
   public WebElement Course;
   
   public void ClickOnCourse()
   {
	   Course.click();
   }
   
   @FindBy(css="#note_note_text")
   public WebElement AddnoteSection;
   
   public void EnterAddnote(String note)
   {
	   AddnoteSection.sendKeys(note);
   }
   
   
   @FindBy(css="#new_note>button.btn.pull-right")
   public WebElement AddnoteBtn;
   
   public void ClickOnAddNote()  
   {
	   AddnoteBtn.click();
   }
   
   //div.alert.alert-success
   @FindBy(css="div.alert.alert-success")
   public WebElement AddNoteMessage;
   
   //Add note is present or not.
 //div.alert.alert-success
   @FindBy(css="#notes div ol ")
   public WebElement AddNotePresent;
   
  @FindBy(css="div.alert.alert-error")
  public WebElement ErrorMessageDisplay;
   
  
  @FindBy(css=".fa.fa-edit")
  public WebElement  PencilIcon;
  
  @FindBy(css=".fa.fa-refresh")
  public WebElement RecycleIcon;
  
  @FindBy(css=".fa.fa-comment")
  public WebElement NoteIcon;
  
  @FindBy(css=".notes-list.row-fluid a")
  public WebElement UserNameLinkOnNoteAdd;
  
  public void ClickOnUserNameLinkOnNoteAdd()
  {
	  UserNameLinkOnNoteAdd.click();
  }
   @FindBy(css="h4.user-email.span4>a")
   public WebElement UsernameMailId;
   
   @FindBy(css="fieldset.span4>input[placeholder='Student Info']")
   public WebElement StudentInfoText;
   public void EnterStudentInfoText(String Name)
   {
	   StudentInfoText.sendKeys(Name);
   }
   
   @FindBy(css="button.btn.btn-primary")
   public WebElement SearchBtn;
   
   public void ClickOnSearchBtn()
   {
	   SearchBtn.click();
   }
   
  //section[class="search-results"].search-results>ol>li
   
   @FindBy(css="section[class='search-results'].search-results>ol>li")
   public WebElement  TwoElement;
   
   @FindBy(css="li:nth-of-type(1)>header>div:nth-of-type(2)>h4>a")
   public WebElement FirstElement;
   
   public void ClickOnFirstElement()
   {
	   FirstElement.click();
   }
   
   @FindBy(css="li:nth-of-type(2)>header>div:nth-of-type(2)>h4>a")
   public WebElement SecondElement;
   
   public void ClickOnSecondElement()
   {
	   SecondElement.click();
   }
   
   
   @FindBy(css="div.alert.alert-notice")
   public WebElement CertificationWithMessage; 
   
   @FindBy(css=".widget-content>nav>ul>li:nth-of-type(1)>a")
   public WebElement ReturnToCertificate; 
   
   public void ReturnToCertificate()
   {
	   ReturnToCertificate.click();
   }
   
   
   @FindBy(css="li:nth-of-type(3) header div:nth-of-type(2) h4 a")
   public WebElement  StudentName;
   
   public void ClickOnStudentName()
   {
	   StudentName.click();
   }
  
   @FindBy(css="a.btn.btn-small.order-permcert.pull-right")
   public WebElement OrderReplacementCertificate;
   
   public void ClickOnOrderReplacementCertificate()
   {
	   OrderReplacementCertificate.click();
   }
   
   @FindBy(css="#activity-stream ul li:nth-of-type(1) div:nth-of-type(2) p time")
   public WebElement CurrntDate;
   
   @FindBy(css="#cc-number")
   public WebElement CreditCardNo;
   
   public void EnterCreditCardNo(String No)
   {
	   CreditCardNo.sendKeys(No);
   }
   
   @FindBy(css="#cc-exp-date")
   public WebElement CreditCardExpDAte;
   
   public void EnterCreditCardExpDAte(String No)
   {
	   CreditCardExpDAte.sendKeys(No);
   }
   
   @FindBy(css="#cc-phone")
   public WebElement CreditCardPhoneNo;
   
   public void EnterCreditCardPhoneNo(String No)
   {
	   CreditCardPhoneNo.clear();
	   CreditCardPhoneNo.sendKeys(No);
   }
   
   @FindBy(css="#btn-submit-payment")
   public WebElement SubmitPayment;
   
   public void ClickSubmitPayment()
   {
	   SubmitPayment.click();
   }
   
   @FindBy(css="#details-1>dl")
   public WebElement QuantityCount;
   
   @FindBy(css="#activity-stream ul li:nth-of-type(1) div:nth-of-type(2) p button")
   public WebElement detail;
   public void ClickOndetail()
   {
	   detail.click();
   }
   
   @FindBy(css="#activity-stream ul li:nth-of-type(1) div:nth-of-type(2) p button")
   public WebElement replacementDetail;
   public void ClickOnReplacementDetail()
   {
	   detail.click();
   }
   
   @FindBy(css="#activity-stream ul li:nth-of-type(2) div:nth-of-type(2) p button")
   public WebElement detail1;
   public void ClickOndetail1()
   {
	   detail1.click();
   }
   
   @FindBy(css="#activity-stream ul li:nth-of-type(1) div:nth-of-type(2)")
   public WebElement  ActivityLatestPage;
   
  
   
   @FindBy(css="ul.dropdown-menu>li>a[href='/certifications/activity']")   
   public WebElement CertificationActivity;
   
   public void ClickOnCertificationActivity()
   {
	   CertificationActivity.click();
   }
   
   @FindBy(css="div ul li:nth-of-type(1) a i")   
   public WebElement Admin;
   
   public void ClickOnAdmin()
   {
	   Admin.click();
   }
   
   
   @FindBy(css="#activity-page ul li:nth-of-type(5) div h1")
   public WebElement ActivityOnReplacement;
   
   @FindBy(css="#details-4>dl>dd")
   public WebElement  ActivityQuantity;
   
   @FindBy(css="#activity-stream ul li:nth-of-type(5) div p a")
   public WebElement ActivityuserLink;
   
   @FindBy(css="li:nth-of-type(2) b.caret")
   public WebElement UserProfile;
   
   public void ClickOnUserProfile()
   {
	   UserProfile.click();
   }
   
   @FindBy(css="div ul li:nth-of-type(2) li:nth-of-type(1) a")
   public WebElement MyProfile;
     
   public void ClickMyProfile()
   {
	   MyProfile.click();
   } 
   
   @FindBy(css="#activity-stream>header")
   public WebElement ActivityHeader;
   
   
   @FindBy(css="#activity-stream ul li:nth-of-type(5) div h1 ")
   public WebElement ActivityForProfile;
   
   
   @FindBy(css="#activity-stream ul li:nth-of-type(2) div p button")
   public WebElement DetailsOnProfilePage1;
   
   public void ClickOnDetailsOnProfilePage1()
   {
	   DetailsOnProfilePage1.click();
   }
   
   @FindBy(css="#activity-stream ul li:nth-of-type(5) div p button")
   public WebElement DetailsOnProfilePage;
   
   public void ClickOnDetailsOnProfilePage()
   {
	   DetailsOnProfilePage.click();
   }
   
   @FindBy(css="#details-4>dl>dd")
   public WebElement ProfileQuantity;
   
   @FindBy(css="#activity-stream ul li:nth-of-type(1) div:nth-of-type(2) p time")
   public  WebElement Todaydate;
   
}

