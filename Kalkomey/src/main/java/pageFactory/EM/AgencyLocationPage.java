package pageFactory.EM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class AgencyLocationPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public AgencyLocationPage(WebDriver driver)
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
	
	
	
		@FindBy(id="search_data")
	    public WebElement SearchBox;
		
		//Enter Search Data
		public void EnterSearchData(String searchdata)
		{
			SearchBox.clear();
			SearchBox.sendKeys(searchdata);
		}		
		
		@FindBy(id="search_type")
	    public WebElement SearchType;
		
		//Select Search Type
			public void SelectSearchTypeByName()
			{
				new Select(SearchType).selectByVisibleText("Name");
			}

			
		@FindBy(css="a.btn")
	    public WebElement SearchButton;
		
		//Click on Search button
			public void ClickOnSearchButton()
			{
				SearchButton.click();
			}
}

