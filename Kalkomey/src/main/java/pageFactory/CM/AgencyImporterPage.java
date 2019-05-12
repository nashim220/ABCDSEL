package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class AgencyImporterPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public AgencyImporterPage(WebDriver driver)
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
	
	
		@FindBy(css=".fa.fa-upload")
    	public WebElement AgencyImporter;
		
		public void ClickOnAgencyImporterTab()
		{
			AgencyImporter.click();
		}
		
		@FindBy(css="#fileupload")
	    public WebElement ChooseFile;
		
		public void SendPath(String path)
		{
			ChooseFile.sendKeys(path);
		}
		@FindBy(css="#upload")
	    public WebElement Upload;
		
		public void ClickOnUploadButton()
		{
			Upload.click();
		}	
		
		@FindBy(css=".alert.alert-danger")
	    public WebElement ErrorMessage;
		
		@FindBy(css=".alert.alert-success")
	    public WebElement SuccessMessage;
		
		@FindBy(css=".alert.alert-warning")
	    public WebElement WarningMessage;
		
		@FindBy(css=".dialog h1")
	    public WebElement NotAuthorize;
		
		@FindBy(css="#agency-importer div:nth-of-type(4) h2")
	    public WebElement DateFormatNote;
		
}
