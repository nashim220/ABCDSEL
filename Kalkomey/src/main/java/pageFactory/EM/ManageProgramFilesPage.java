package pageFactory.EM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class ManageProgramFilesPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public ManageProgramFilesPage(WebDriver driver)
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
	
	
		@FindBy(css=".span9 h3")
		public WebElement Title;	
		
		
		@FindBy(css="#page div:nth-of-type(2) tr:nth-of-type(1) select")
		public WebElement FileVisibility;		
	
		public void SelectFileVisibility(String visibletext)
		{
			new Select(FileVisibility).selectByVisibleText(visibletext);
		}
		
			
			
		@FindBy(css="#description")
		public WebElement Description;	
		
		public void EnterDescription(String description)
		{
			Description.clear();
			Description.sendKeys(description);
		}
		
		
		@FindBy(css="#page div:nth-of-type(2) tr:nth-of-type(4) input")
		public WebElement Browse;
		
		public void SendPath(String path)
		{
			Browse.sendKeys(path);
		}
		
		
		@FindBy(css="input[name='submitBtn']")
		public WebElement Upload;
		
		public void ClickOnUploadButton()
		{
			Upload.click();
		}		
		
		
		@FindBy(css="#edit_description")
		public WebElement EditDescription;	
		
		public void EditDescription(String editdescription)
		{
			EditDescription.clear();
			EditDescription.sendKeys(editdescription);
		}
		
		
		@FindBy(css="a span")
		public WebElement UpdateDescription;	
		
		public void UpdateDescription()
		{
			UpdateDescription.click();
		}
		
		@FindBy(css="#page a img")
		public WebElement Delete;	
		
		public void ClickOnDelete()
		{
			Delete.click();
		}
		
		@FindBy(css=".btn.btn-primary.pull-right")
		public WebElement SaveChanges;
		
		public void ClickOnSaveChanges()
		{
			SaveChanges.click();
		}
		
		@FindBy(css=".msg")
		public WebElement Success;
		
		
		//@FindBy(css=".even.ruled>td>div>a")
		@FindBy(xpath=".//*[@id='page']/div[2]/div[2]/div[2]/table[1]/tbody/tr[1]/td[2]/div/a")
		public WebElement CheckUploadedFile;
		
		@FindBy(css="#edit_description")
		public WebElement CheckUploadedFileDescription;
		
		// Create Event activation
		@FindBy(css=".btn.mtt")
		public WebElement ActiveEvent;
}
