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

public class AdminSearchEventPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public AdminSearchEventPage(WebDriver driver)
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
	
		@FindBy(css=".nav.nav-list li:nth-of-type(4) a")
		WebElement SearchEvent;
	
		
		//Click on Search Event Link
		public void ClickOnSearchEventLink()
		{
			SearchEvent.click();
		}
				
		@FindBy(css="#search_types")
		WebElement SearchType;
		
	
		public void SelectSearchTypeByVisibleText(String visibletext)
		{
			new Select(SearchType).selectByVisibleText(visibletext);
		}
		
		
		@FindBy(css="#search-type-id")
		public WebElement SearchData;
		
		
		// Event Search Option
		@FindBy(css="#search_types")
		public WebElement SearchOption;
		
		//Enter Search Data
		public void EnterSearchData(String location)
		{
			SearchData.clear();
			SearchData.sendKeys(location);
		}	
		
		@FindBy(css="#search-type-name")
		public WebElement SearchNameData;
		
		
		//Enter Search Data
		public void EnterNameSearchData(String location)
		{
			SearchNameData.clear();
			SearchNameData.sendKeys(location);
		}	
		
		@FindBy(css="#search-type-upcoming")
		public WebElement SearchUpcomingData;
		
		@FindBy(css="#search-type-date")
		public WebElement SearchDate;
		
		//Enter Search Data
		public void EnterUpcomingSearchData(String location)
		{
			SearchUpcomingData.clear();
			SearchUpcomingData.sendKeys(location);
		}	
		
		
		//Enter Search Data
		public void EnterDateSearchData(String date)
		{
			SearchDate.clear();
			SearchDate.sendKeys(date);
		}
		
		@FindBy(css="#search-button")
	    public WebElement SearchButton;
		
		//Click on Search button
		public void ClickOnSearchButton() throws InterruptedException
		{
			Thread.sleep(4000);
			SearchButton.click();
		}
		
		@FindBy(css="#search_form > input.btn")
	    public WebElement SearchButton2;
		
		//Click on Search button
		public void ClickOnSearchButton2() throws InterruptedException
		{
			Thread.sleep(4000);
			SearchButton2.click();
		}
		
		
		@FindBy(css="#toggler")
	    public WebElement SelectEvent;
		
		//Select Event Checkbox
		public void ClickOnSelectEventCheckBox() throws InterruptedException
		{
			Thread.sleep(2000);
			SelectEvent.click();
			Thread.sleep(2000);
		}
		
		
		@FindBy(css=".filter-option.pull-left")
		public WebElement EventType;
		
		//Search Type Event	
		public void SelectEventByVisibleText(String visibletext)
		{
			new Select(EventType).selectByVisibleText(visibletext);
		}
		
		//Search for registration select country
		@FindBy(name="state_cd")
		public WebElement Country;
		
		public void SelectCountryByVisibleText(String visibletext)
		{
			new Select(Country).selectByVisibleText(visibletext);
		}
		// Search Data 
		@FindBy(id="search_data")
		public WebElement data;
		
		public void Enter_SearchData()
		{
			data.clear();
			data.sendKeys("John");
		}
		
		@FindBy(css="#event_action_type")
		WebElement EventAction;
		
			
		public void SelectEventActionByVisibleText(String visibletext)
		{
			new Select(EventAction).selectByVisibleText(visibletext);
		}
		
		@FindBy(css="#event_action_type + a")
	    public WebElement GoButton;
		
		//Click on Search button
		public void ClickOnGoButton()
		{
			GoButton.click();
		}
		
		public String GetEventSearchColumns()
		{
			String searchcolumn ="";
			for(int i=2; i<=6; i++)
			{
				String css = ".table.table-striped th:nth-of-type(" + i + ")";
				searchcolumn = searchcolumn + driver.findElement(By.cssSelector(css)).getText();
			}
			
			return searchcolumn;
		}
		
		// First option of search
		@FindBy(css="#page tr:nth-of-type(1) td:nth-of-type(2) a")
		public WebElement FirstElement;
		
		public void ClickonFirstSearchOption()
		{
			FirstElement.click();
		}
		
		
}

