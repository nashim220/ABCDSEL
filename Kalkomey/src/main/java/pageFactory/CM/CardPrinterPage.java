package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class CardPrinterPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public CardPrinterPage(WebDriver driver)
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
	
	
		@FindBy(css=".fa.fa-print")
    	public WebElement CardPrinterTab;
		
		public void ClickOnCardPrinterTab()
		{
			CardPrinterTab.click();
		}
		
		@FindBy(css=".widget-header>h3")
	    public WebElement Header;
		
		@FindBy(css=".label.label-info")
	    public WebElement PendingLabel;
		
		@FindBy(css=".search-results li:nth-of-type(1) h4 a")
	    public WebElement FirstRecord;
		
		@FindBy(css=".search-results li:nth-of-type(2) h4 a")
	    public WebElement SecondRecord;
				
		@FindBy(css=".page-header h1")
	    public WebElement Name;
		
		@FindBy(css=".btn.btn-small.btn-danger.btn-block.edit-info")
	    public WebElement CancelRequest;
		
		@FindBy(css=".btn.btn-small.btn-primary.btn-block.edit-info")
	    public WebElement ViewInCM;
		
		@FindBy(css="#top-border-needed dd:nth-of-type(2)")
	    public WebElement QTY;
		
		@FindBy(css="#contact-info div:nth-of-type(1) div h4")
	    public WebElement CardPriterSection;
		
		@FindBy(css="#contact-info div:nth-of-type(2) dl")
	    public WebElement PhyAddSection;
		
		@FindBy(css="#contact-info div:nth-of-type(3) dl")
	    public WebElement PhyPhNo;
		
		@FindBy(css="#contact-info div:nth-of-type(4) dl")
	    public WebElement DOB;
		
		@FindBy(css="#contact-info div:nth-of-type(6) dl")
	    public WebElement MailingAddSection;
		
		@FindBy(css="#cert-preview h4")
	    public WebElement CardPreview;
		
		@FindBy(css="#cert-info div header h4")
	    public WebElement CardInfo; 
		
		@FindBy(css=".search-results>ol>li")
	    public WebElement NoOfRecords;
		
		@FindBy(css=".alert.alert-notice")
	    public WebElement DeleteSuccess;
		
		//Cancel Print Request
		public void CancelPrintRequest() throws InterruptedException
		{
				
			SeleniumFunctions seleniumfun = new SeleniumFunctions(driver);
			
				ClickOnCardPrinterTab();
				FirstRecord.click();
				CancelRequest.click();
				seleniumfun.AcceptAlertAndGetText();
		}
}
