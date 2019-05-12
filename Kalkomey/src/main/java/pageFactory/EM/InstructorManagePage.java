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

public class InstructorManagePage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public InstructorManagePage(WebDriver driver)
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

		@FindBy(css="div.content h2")
	    public WebElement PageTitle;
	
		
		//Search
		@FindBy(id="search_types")
	    public WebElement SearchType;
		
		//Select Search Type
			public void SelectSearchTypeByUsername()
			{
				new Select(SearchType).selectByVisibleText("Username");
			}

		@FindBy(id="search-type-uname")
		public WebElement SearchBox;
			
		@FindBy(css="#search_form > input.btn")
		//@FindBy(css="#search_form input:nth-of-type(14)")
	    public WebElement SearchButton;
		
		@FindBy(css="#instructor_associate_form table tr:nth-of-type(1) td input")
	    public WebElement Search_FirstResultCheckbox;
		
		@FindBy(css="form#instructor_associate_form select")
	    public WebElement WithSelectedInstructorsDropdown;
		
		@FindBy(css=".content div:nth-of-type(2) h3")
	    public WebElement CurrentInstructors;
		
		@FindBy(css=".content div:nth-of-type(4) h3")
	    public WebElement MyFavorites;
		
		@FindBy(css=".content div:nth-of-type(6) h3")
	    public WebElement AvailableInstructors;
		
		@FindBy(css="div:nth-of-type(7) a:nth-of-type(1) span")
	    WebElement Back;
		
		public void ClickOnBackButton()
		{
			Back.click();
		}
		
		
		public void SelectActionAndGO(String selectaction) throws InterruptedException
		{
			
			new Select(WithSelectedInstructorsDropdown).selectByVisibleText(selectaction);
			/*new Select(WithSelectedInstructorsDropdown).selectByValue("2");
			new Select(WithSelectedInstructorsDropdown).selectByIndex(2);*/
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("#instructor_associate_form button")).click();
		}
		
		
		
}


