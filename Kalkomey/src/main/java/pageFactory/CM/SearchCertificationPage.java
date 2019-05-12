package pageFactory.CM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class SearchCertificationPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public SearchCertificationPage(WebDriver driver)
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
	
		@FindBy(css="a[href='/certifications']")
	    public WebElement ManageCerti;
		
		@FindBy(css="i.fa-certificate")
		public WebElement ManageCerti_Icon;
		
		@FindBy(css="input[value='Login']")
		public WebElement Login;
		
		@FindBy(css=".widget-header>h3")
		public WebElement CertiHeader;
		
		@FindBy(css="a[href='/certifications/new']")
		public WebElement NewCerti;
		
		@FindBy(css="#state")
		public WebElement State;
		
		@FindBy(css="#certification_type_id")
		public WebElement CertiType;
		
		@FindBy(css="input[name='query']")
		public WebElement SearchField;
		
		@FindBy(css="button.btn-primary")
		public WebElement Search;
		
		
	//Enter Search Criteria
		public void EnterSeachCriteria(String SearchCr)
		{
			SearchField.clear();
			SearchField.sendKeys(SearchCr);
		}
				
		
	//Click on Manage Certification button
		public void ClickOnManageCerification()
		{
			ManageCerti.click();
		}
		
	//Click on Manage Certification button
		public void ClickOnNewCertificate()
		{
			NewCerti.click();
		}
		
	//Click on Search button
		public void ClickOnSearchButton()
		{
			Search.click();
		}
			
	//Select State
		public void SelectSearchState(String state)
		{
			new Select(State).selectByVisibleText(state);			
		}
		
	//Select Type
		public void SelectSearchType(String type)
		{
			new Select(CertiType).selectByVisibleText(type);			
		}


//Search Results
	
		
		//Getting total count for result returned
		public int Results_GetCount()
		{
			List<WebElement> e = driver.findElements(By.cssSelector("section.search-results li"));
			return e.size();
		}
		
		//Get name
		public String Results_GetName(int i)
		{
			String cssSelector = "section.search-results li:nth-of-type(" + i + ") h4";
			return driver.findElement(By.cssSelector(cssSelector)).getText().trim();
		}
		
		//Get State/Type info e.g. State: Michigan Type: Boating
		public String Results_GetStateTypeInfo(int i)
		{
			String cssSelector = "section.search-results li:nth-of-type(" + i + ") div.certification-info p.subtle-text";
			return driver.findElement(By.cssSelector(cssSelector)).getText().trim();
		}
		
		//Get Birth date info e.g. Born 12/28/1989
		public String Results_GetBirthDateInfo(int i)
		{
			String cssSelector = "section.search-results li:nth-of-type(" + i + ") div.student-info p.subtle-text";
			return driver.findElement(By.cssSelector(cssSelector)).getText().trim();
		}
		
		//Get Address info e.g. 14086 Proton Rd , Dallas TX 75244
		public String Results_GetAddressInfo(int i)
		{
			String cssSelector = "section.search-results li:nth-of-type(" + i + ") div.student-info p";
			return driver.findElement(By.cssSelector(cssSelector)).getText().trim();
		}
		
		///Verify whether icon displayed for 'Printable cards'
		public boolean Results_IsPrintableCardIconDisplayed(int i)
		{

			String cssSelector = "section.search-results li:nth-of-type(" + i + ") div.certification-status img[src='/assets/card.png']";
			try
			{
				driver.findElement(By.cssSelector(cssSelector));
				return true;
			}
			catch(Exception e)
			{
				return false;
			}
		}

		
		//Pagination
		@FindBy(css="div.pagination li.next a")
		public WebElement Pagination_Next;
		
		@FindBy(css="div.pagination li.prev a")
		public WebElement Pagination_Prev;
		
		@FindBy(css="div.pagination li.active")
		public WebElement Pagination_ActivePage;
}
