package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class ManageUsersPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public ManageUsersPage(WebDriver driver)
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
	
	
	
		@FindBy(css=".widget-header h3")
		public WebElement Title;
		
		
		@FindBy(css="#agency_id")
		public WebElement Agency;
		
		public void SelectAgency(String agency)
		{
			new Select(Agency).selectByVisibleText(agency);
		}
		
		@FindBy(css="#search-form button")
    	public WebElement Search;
		
		public void ClickOnSearchButton()
		{
			Search.click();
		}
		
		@FindBy(css=".span4 input")
	    public WebElement SearchText;
		
		public void EnterSearchText(String text)
		{
			SearchText.clear();
			SearchText.sendKeys(text);
		}
		@FindBy(css=".index-header-action")
	    public WebElement NewUser;
		
		public void ClickOnNewUserLink()
		{
			NewUser.click();
		}	
		
		@FindBy(css=".span5 p")
	    public WebElement Email;
		
		@FindBy(css=".span7.user-info p")
	    public WebElement AgencyName;
		
		@FindBy(css=".alert.alert-notice")
	    public WebElement ErrorMessage;
		
		@FindBy(css=".search-results ol li")
	    public WebElement SearchRecord;
		
		@FindBy(css=".span12 h4 a")
	    public WebElement FirstRecord;
		
		@FindBy(css=".next a")
	    public WebElement Next;
		
		public void ClickOnNext()
		{
			Next.click();
		}	
		
		@FindBy(css=".pagination.pagination li:nth-of-type(4) a")
	    public WebElement SpecificPage;
		
		public void ClickOnSpecificPage()
		{
			SpecificPage.click();
		}
		
		@FindBy(css=".pagination ul")
	    public WebElement Pagination;
		
		
}
