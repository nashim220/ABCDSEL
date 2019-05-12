package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class CertificationDetailsPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public CertificationDetailsPage(WebDriver driver)
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
	
		@FindBy(css="div.student h1")
	    public WebElement Name;
		
		//Student Information
		
			@FindBy(css="#contact-info a")
		    public WebElement Edit;
			
			public void ClickOnEditButton()
			{
				Edit.click();
			}
		
			@FindBy(css="#contact-info > div:nth-of-type(1) dl:nth-of-type(1)")
		    public WebElement PhysicalAddress;
			
			@FindBy(css="#contact-info > div:nth-of-type(1) dl:nth-of-type(2)")
		    public WebElement PhysicalPhoneNumber;
			
			@FindBy(css="#contact-info > div:nth-of-type(1) dl:nth-of-type(3)")
		    public WebElement DateOfBirth;
			
			@FindBy(css="#contact-info > div:nth-of-type(1) dl:nth-of-type(4)")
		    public WebElement Gender;
			
			@FindBy(css="#contact-info > div:nth-of-type(2) dl:nth-of-type(1)")
		    public WebElement MailingAddress;
			
			@FindBy(css="#contact-info > div:nth-of-type(2) dl:nth-of-type(2)")
		    public WebElement MailingPhoneNumber;
			
			@FindBy(css="#contact-info > div:nth-of-type(2) dl:nth-of-type(3)")
		    public WebElement EmailAddress;
			
		
		//Certification Information
		
			@FindBy(css="#cert-info a.order-permcert")
		    public WebElement OrderReplacementCertificate;
			
			@FindBy(css="#cert-info a.resend-tempcert")
		    public WebElement ResendCourseCompletion;
			
			@FindBy(css="#cert-info dl:nth-of-type(1)")
		    public WebElement Agency;
			
			@FindBy(css="#cert-info dl:nth-of-type(2)")
		    public WebElement CertificationCollectionName;
			
			@FindBy(css="#cert-info div:nth-of-type(1) dl:nth-of-type(1)")
		    public WebElement CertificationOrigin;
		
			@FindBy(css="#cert-info div:nth-of-type(1) dl:nth-of-type(2)")
		    public WebElement ClassType;
			
			@FindBy(css="#cert-info div:nth-of-type(2) dl:nth-of-type(1)")
		    public WebElement CertificateStatus;
			
			@FindBy(css="#cert-info div:nth-of-type(2) dl:nth-of-type(2)")
		    public WebElement CertificateDate;
			
			@FindBy(css="#cert-info div:nth-of-type(2) dl:nth-of-type(3)")
		    public WebElement LastIssuedAt;
			
			@FindBy(css="#cert-info div:nth-of-type(2) dl:nth-of-type(4)")
		    public WebElement CertificateID;
			
			@FindBy(id="card_body")
		    public WebElement CertificatePreview;
			
		
		//Notes
			
			@FindBy(id="note_note_text")
		    public WebElement Notes_Textbox;
			
			@FindBy(css="#new_note button")
		    public WebElement Notes_Add;
			
	
	
		
}
