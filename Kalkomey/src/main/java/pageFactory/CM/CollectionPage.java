package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class CollectionPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public CollectionPage(WebDriver driver)
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
	
		@FindBy(css="#state")
    	public WebElement Jurisdiction;
		
		public void SelectJurisdiction(String jurisdiction)
		{
			new Select(Jurisdiction).selectByVisibleText(jurisdiction);
		}
		
		@FindBy(css="#certification_type_id")
    	public WebElement Type;
		
		public void SelectType(String type)
		{
			new Select(Type).selectByVisibleText(type);
		}
		
		@FindBy(css="#search-form input")
    	public WebElement Criteria;
		
		public void EnterCriteria(String criteria)
		{
			Criteria.clear();
			Criteria.sendKeys(criteria);
		}
		
		@FindBy(css="#search-form button")
    	public WebElement Search;
		
		public void ClickOnSearchButton()
		{
			Search.click();
		}
		
		
		
		@FindBy(css=".pagination ul")
    	public WebElement Pagination;
		
		@FindBy(css=".alert.alert-notice")
    	public WebElement NoRecord;
		
		@FindBy(css="li:nth-of-type(1) header div h4 a")
    	public WebElement ValidSearch;
		
		public void ClickOnFirstRecord()
		{
			ValidSearch.click();
		}
		
		@FindBy(css=".next>a")
    	public WebElement Next;
		
		public void ClickOnNext()
		{
			Next.click();
		}
		
		@FindBy(css=".search-results ol li")
    	public WebElement SearchResult;
		
		@FindBy(css=".search-results ol li")
    	public WebElement NoOfRecords;
		
	   @FindBy(css=".label.label-information")
   		public WebElement PrintableCard;
	   
	   @FindBy(css=".span6.certification-collection-info")
  		public WebElement CollectionInfo;
	   
	   @FindBy(css=".span3 .certification-collection-info p:nth-of-type(1)")
 		public WebElement NameOnCard;
	   
	   @FindBy(css=".span6.certification-collection-info p:nth-of-type(1)")
		public WebElement Agency;
	   
	   @FindBy(css=".span3.certification-collection-info p")
		public WebElement TotalNoOfCard;
	   
}
