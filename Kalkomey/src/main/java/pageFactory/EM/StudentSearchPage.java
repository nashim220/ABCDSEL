package pageFactory.EM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class StudentSearchPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public StudentSearchPage(WebDriver driver)
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
		
		
		@FindBy(css="#search-type-email")
		public WebElement SearchData;
		
		//Enter Search Data
		public void EnterSearchData(String searchdata)
		{
			SearchData.clear();
			SearchData.sendKeys(searchdata);
		}			
		
		@FindBy(id="search-button")
		//@FindBy(css="#search_form input:nth-of-type(8)")
		WebElement Search;
	
	
	//Click on Search Button
		public void ClickOnSearchButton()
		{
			Search.click();
		}
		
		@FindBy(css="#student_select_form tr:nth-of-type(1) td a:nth-of-type(2)")
		WebElement Edit;
	
		
	//Click on Edit First Record
		public void ClickOnEditFirstRecord()
		{
			Edit.click();
		}
		
		
		@FindBy(css="#registrations > tbody > tr > td:nth-child(3) > a")
		WebElement Edit2;
		public void EditFirstRecord()
		{
			Edit2.click();
		}
		
		
}
