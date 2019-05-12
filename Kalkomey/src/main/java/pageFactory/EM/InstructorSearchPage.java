package pageFactory.EM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class InstructorSearchPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public InstructorSearchPage(WebDriver driver)
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
	
		@FindBy(css=".span9>h3")
		public WebElement Title;

	
		@FindBy(css="#search_types")
		WebElement SearchType;

		public void SelectSearchTypeByVisibleText(String visibletext)
		{
			new Select(SearchType).selectByVisibleText(visibletext);
		}
		
		
		@FindBy(css="#search-type-uname")
		public WebElement SearchData;
		
		//Enter Search Data
		public void EnterSearchData(String searchdata)
		{
			SearchData.clear();
			SearchData.sendKeys(searchdata);
		}			
		
		@FindBy(css="#search_form > input.btn")
		//@FindBy(xpath=".//*[@id='search_form']/input[9]")
		WebElement Search;
	
	
	//Click on Search Button
		public void ClickOnSearchButton()
		{
			Search.click();
		}
		
		@FindBy(css="#proctor_select_form tr:nth-of-type(1) td:nth-of-type(2) a:nth-of-type(2)")
		WebElement Edit;
	
	
	//Click on Edit First Record
		public void ClickOnEditFirstRecord()
		{
			Edit.click();
		}
		
	
}
