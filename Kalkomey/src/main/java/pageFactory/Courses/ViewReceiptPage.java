package pageFactory.Courses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


import utility.Constants;
import utility.JavaHelpers;

public class ViewReceiptPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public ViewReceiptPage(WebDriver driver)
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
	
		@FindBy(css="div.page-header h1")
	    public WebElement PageHeader;
	
		@FindBy(css="ul[class='breadcrumb step']")
		public WebElement BreadcrumbText;
		
		@FindBy(css="div[class='alert alert-block alert-success'] p")
		public WebElement ConfirmationMessage;
		
		@FindBy(css="div[class='alert alert-block alert-success'] a")
		public WebElement GetYourProofofHomeStudyVoucher;
		
		@FindBy(css="div.span4 a")
		public WebElement PrintReceipt;
		
		
		//Bill To
		@FindBy(css="div[class='span3 well'] h4")
		public WebElement Username;
		
		@FindBy(css="div[class='span3 well'] p")
		public WebElement MailingAddress;
		
		@FindBy(css="div[class='span3 well'] p:nth-of-type(2)")
		public WebElement PhoneNumber;
		
		@FindBy(css="div[class='span4 well'] p:nth-of-type(1)")
		public WebElement InvoiceNumber;
		
		@FindBy(css="div[class='span4 well'] p:nth-of-type(2)")
		public WebElement OrderDate;
		
		@FindBy(css="div[class='span4 well'] p:nth-of-type(3)")
		public WebElement TotalAmount;
		
		@FindBy(css="div[class='span4 well'] p:nth-of-type(4)")
		public WebElement PaymentMethod;
		
		@FindBy(css="table[class='receipt table'] tbody tr:nth-of-type(1) td:nth-of-type(2)")
		public WebElement CourseFee;
		
		@FindBy(css="table[class='receipt table'] tbody tr:nth-of-type(2) td:nth-of-type(1)")
		public WebElement TotalFee;
		
		@FindBy(css=".alert.alert-info>h4:nth-of-type(1)>strong>a")
		public WebElement GetMyBoatLink;
		
		@FindBy(css=".alert.alert-info>h4:nth-of-type(2)>strong>a")
		public WebElement DockwaLink;
		
}
