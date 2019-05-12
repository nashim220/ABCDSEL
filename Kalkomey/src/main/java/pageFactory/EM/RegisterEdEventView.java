package pageFactory.EM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class RegisterEdEventView 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public RegisterEdEventView(WebDriver driver)
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
	
		@FindBy(css=".status-description")
		public WebElement RegistrationStatus;
	
		@FindBy(css="#registrationBegins")
		public WebElement RegistrationStatusFuture;
		
		@FindBy(css=".btn.btn-success.btn-large")
		public WebElement RegisterNow;
		
		public void ClickOnRegisterNowButton()
		{
			RegisterNow.click();
		}
		
		@FindBy(css="span[class='btn btn-large disabled']")
		public WebElement RegisterClosed;
		
		@FindBy(css=".btn.btn-large.disabled")
		public WebElement RegistrationButton;
		
		@FindBy(css=".status-seats")
		public WebElement SeatStatus;
		
		@FindBy(css=".program-name.summary")
		public WebElement EventName;
		
		@FindBy(css=".l-shadowbox.l-contentbox.l-contentbox-small.l-highlight.mbm")
		public WebElement SpecialInstruction;
		
		
		
		@FindBy(css=".cancellation-policies.mbm")
		public WebElement CancellationPolicy;
		
		@FindBy(css=".minimum-age.mbm")
		public WebElement AgePolicy;
		
		@FindBy(css=".minimum-age.mbm > p:nth-child(2)")
		public WebElement MinAgePolicy;
		

		@FindBy(css="#message_trigger > button")
		public WebElement SendMessage;
		
		public void ClickOnSendMessageLink()
		{
			SendMessage.click();
		}
		
		@FindBy(css="#emailInstructorEmail")
		public WebElement Email;
		
		public void EnterInstructorEmail(String email)
		{
			Email.clear();
			Email.sendKeys(email);
		}
		
		@FindBy(css="#emailInstructorMessage")
		public WebElement Message;
		
		public void EnterMessage(String message)
		{
			Message.clear();
			Message.sendKeys(message);
		}
		
		@FindBy(css="#form_email_instructor_btn")
		public WebElement SendMsg;
		
		public void ClickOnSendMessageButton()
		{
			SendMsg.click();
		}
		
		@FindBy(css="#form_email_instructor_cancel")
		public WebElement DontSend;
		
		public void ClickOnDontSendLink()
		{
			DontSend.click();
		}
		
		@FindBy(css="#email_instructor_success")
		public WebElement SuccessMessage;
		
		
		@FindBy(css="#primary_content div div div:nth-of-type(2) a")
		public WebElement ViewEvent;
		
		public void ClickOnViewEventButton()
		{
			ViewEvent.click();
		}
		
		@FindBy(css="#primary_content div:nth-of-type(1) div div:nth-of-type(2) a")
		public WebElement ViewRegistration;
		
		public void ClickOnViewRegistrationButton()
		{
			ViewRegistration.click();
		}
		
		@FindBy(css="#next_step p a")
		public WebElement CancelRegistration;
		
		public void ClickOnCancelRegistrationButton()
		{
			CancelRegistration.click();
		}
		
		@FindBy(css="#add_student button")
		public WebElement AddStudent;
		
		public void ClickOnAddStudentButton()
		{
			AddStudent.click();
		}
		
		//Check Description of file
			@FindBy(css=".unstyled.file-list.mts>li>a")
			public WebElement CheckDescription;
				
			@FindBy(css="#event_location")
			 public WebElement EventLocation;
}
