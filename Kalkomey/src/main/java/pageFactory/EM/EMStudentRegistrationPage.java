package pageFactory.EM;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class EMStudentRegistrationPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public EMStudentRegistrationPage(WebDriver driver)
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
	
	//View Registration Page
	
		//Send Message
		@FindBy(css="div.content.clearfix li:nth-of-type(3) a")
		public WebElement SendAMessage;			
		
		@FindBy(css="#send-message input")
		public WebElement Subject;	
		
		@FindBy(css="#send-message textarea")
		public WebElement Body;	
		
		@FindBy(css=".checkbox.inline>input")
		public WebElement SendMeCopyCheckBox;
		
		@FindBy(css="#send-message-btn")
		public WebElement SendMessageButton;
		
		@FindBy(css="#send-message-modal a")
		public WebElement Close;
		
		@FindBy(css=".span12.alert.alert-success")
		public WebElement SuccessMessage;
		
		@FindBy(css=".close")
		public WebElement SuccessMessageClose;
		
		
		
		//Cancel Registration
		@FindBy(css="div.content.clearfix li:nth-of-type(4) a")
		public WebElement CancelRegistration;	
		
		@FindBy(css=".btn.btn-danger")
		public WebElement Cancel;	
		
		@FindBy(css="#cancel")
		public WebElement CancelModal;	
		
		@FindBy(css="#cancel a:nth-of-type(1)")
		public WebElement CloseCancel;	
		
		@FindBy(css=".alert-flash.alert.alert-success")
		public WebElement CancelSuccess;
		
	
		
		//Send Certificate
		@FindBy(css="div.content.clearfix li:nth-of-type(4) a")
		public WebElement SendCertificate;
		
		@FindBy(css="#send-certificate-btn")
		public WebElement SendCertificateButton;
		
		@FindBy(css="#send-certificate-modal a:nth-of-type(1)")
		public WebElement CancelSendCertificate;
		
		
		//    Resend Confirmation Email
		@FindBy(css="div.content.clearfix li:nth-of-type(8) a")
		public WebElement ResendConfirmationEmail;
		
		@FindBy(css="#send-registration-btn")
		public WebElement ResendConfirmationButton;
		
		@FindBy(css="#send-registration-modal a:nth-of-type(1)")
		public WebElement CancelResendConfirmation;
		
		
		//Resend Receipt
			@FindBy(css="div.content.clearfix li:nth-of-type(8) a")
			public WebElement ResendReceipt;
			
			@FindBy(css="#send-receipt-btn")
			public WebElement ResendReceiptButton;
			
			@FindBy(css="#send-receipt-modal a")
			public WebElement CancelResendReceipt;
			
			
		@FindBy(css="#page div:nth-of-type(6) a:nth-of-type(2)")
		public WebElement Edit;
		
		@FindBy(css="#page div:nth-of-type(6) a:nth-of-type(1)")
		public WebElement BackToRoster;
		
		
		//Move to New Event
		@FindBy(css="div.content.clearfix li:nth-of-type(5) a")
		public WebElement MoveToNewEvent;
		
		@FindBy(css="#sendmessage_form>div>div>span")
		  public WebElement Studentinfo;
		  
		  @FindBy(id="event_id")
		  public WebElement SelectNewEvent;
		  
		  @FindBy(css="#sendmessage_form>div>textarea")
		  public WebElement Message;
		  
		  @FindBy(css=".button:nth-of-type(1)>span")
		  public WebElement SendMessage;
		  
		  @FindBy(css=".button:nth-of-type(2)>span")
		  public WebElement CancelMessage;
		  
		  @FindBy(css=".content>p")
		  public WebElement SuccessMessageMoveTo;
		  
		// Verify Click Here links
		  
		  @FindBy(css="#zip_code")
		  public WebElement ZipCode_RegisterAStudent;
		  
		  @FindBy(name="distance")
		  public WebElement Max_Distance;
		  
		  public void select_Miles()
		  {
			  Max_Distance.click();
			  Select dis = new Select(driver.findElement(By.name("distance")));
			  dis.selectByValue("250");
		  }
		  
		  public void EnterZip_RegisterAStudent(String zip)
		  {   ZipCode_RegisterAStudent.clear();
			  ZipCode_RegisterAStudent.sendKeys(zip);
		  }
		  
		  @FindBy(css=".btn.btn-primary")
		  public WebElement Next_RegisterAStudent;
		 
		  @FindBy(css=".vevent.group:nth-of-type(1) .add_student")
		  public WebElement FirstAddStudentBtn;
		  
		  @FindBy(css=".span12>ul>li:nth-of-type(2) >a")
		  public WebElement ClickHereLink;
		  
		  @FindBy(css=".page-header>h3")
		  public WebElement Header_RegisterStudent;
		  
		  // upload XML file
		  
		  // Student Registration Information page
		  @FindBy(css=".icon-plus")
		  public WebElement plusIcon;
		  
		  public void clickOnPlus()
		  {
			  plusIcon.click();
		  }
		  
		  @FindBy(css=".icon-minus")
		  public WebElement minusIcon;
		  
		  public void clickOnminus()
		  {
			  minusIcon.click();
		  }
		  
		  //  Collapsible Registration Sections
		  @FindBy(xpath=".//*[contains(@data-target,'additional')]")
		  public WebElement additionalInformation;
		  
		  @FindBy(xpath=".//*[contains(@data-target,'system')]")
		  public WebElement systemInformation;
		  
		  @FindBy(xpath=".//*[contains(@data-target,'comment')]")
		  public WebElement comment;
		  
		  @FindBy(xpath=".//*[@id='create-comment']/textarea")
		  public WebElement commentTextBox;
		  
		  @FindBy(xpath=".//*[@id='create-comment-btn']")
		  public WebElement addComment;
		  
		  @FindBy(css=".comment-message")
		  public WebElement commentMessage;
		  
		  @FindBy(xpath=".//*[@id='system-information']/div[2]/dl[2]/dd")
		  public WebElement TextMessagePreference;
		  
		  @FindBy(xpath=".//*[@id='payment-information']/div[4]/dl[2]/dd")
		  public WebElement cardDigit;
		  
		  
		  
}


