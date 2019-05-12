package pageFactory.EM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class InstructorLocationPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public InstructorLocationPage(WebDriver driver)
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

		@FindBy(css=".page-header>h3")
	    public WebElement PageTitle;
	
		@FindBy(id="courseSelect")
	    public WebElement Program;
		
		//Select Gender
		public void SelectProgram(int index)
		{
			new Select(Program).selectByIndex(index);
		}
			
		
		@FindBy(css="#search-type-name")
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

			
		@FindBy(css="#search_form > input.btn")
	    public WebElement SearchButton;
		
		//Click on Search button
			public void ClickOnSearchButton()
			{
				SearchButton.click();
			}
				
		
		@FindBy(css="#create-location>a")
	    public WebElement CreateLocation;
		
		//Location
		@FindBy(css="div.row-fluid.row-striped div div:nth-child(2) div a:nth-child(3)")
	    public WebElement ScheduleAnEvent;
		
		@FindBy(css="div.row-fluid.row-striped div div:nth-child(2) div a:nth-child(4)")
	    public WebElement MoreInformation;
		
		@FindBy(css=".page-header>h3")
	    public WebElement LocationTitle;
		
		@FindBy(css=".span5")
	    public WebElement LocationDetails;
		
		@FindBy(css="#map")
	    public WebElement Map;
		
		@FindBy(css="p.location_nametag a:nth-of-type(3)")
	    public WebElement Edit;
		
			//Location title
			@FindBy(css=".location-name")
		    public WebElement Title;
			
			@FindBy(css="#locations > tbody > tr > td:nth-child(2) > a")
			public WebElement Title1;
			
			//Location description
			@FindBy(css=".location-details")
		    public WebElement Description;
			
			@FindBy(css="#locations > tbody > tr > td:nth-child(3)")
		    public WebElement Description1;
		
		//New Location page
		@FindBy(id="location_name")
	    public WebElement LocationName;
		
		@FindBy(id="location_instructions")
	    public WebElement SpecialInstructions;
		
		@FindBy(id="location_addr1")
	    public WebElement Address;
			
		@FindBy(id="location_city")
	    public WebElement City;
		
		@FindBy(id="location_state")
	    public WebElement State;
		
		@FindBy(id="location_zip")
	    public WebElement Zip;	
		
		@FindBy(id="location_phone")
	    public WebElement Phone;
		
		@FindBy(id="location_room")
	    public WebElement Room;
		
		@FindBy(id="location_student_capacity")
	    public WebElement Capacity;
		
		@FindBy(id="location_region")
	    public WebElement Region;
		
		@FindBy(id="location_county")
	    public WebElement County;
		
		@FindBy(css=".btn.btn-success")
	    public WebElement Submit;
		
		
		//Select State
		public void SelectState(int index)
		{
			new Select(State).selectByIndex(index);
		}
		
		//Select Region
		public void SelectRegion(int index)
		{
			new Select(Region).selectByIndex(index);
		}
				
		//Select County
		public void SelectCounty(int index)
		{
			new Select(County).selectByIndex(index);
		}
		
		
		//validation error messages
		@FindBy(xpath=".//*[@id='location_form']/div[1]/div[1]/span[1]")
	    public WebElement LocationName_Error;
		
		@FindBy(xpath=".//*[@id='location_form']/div[1]/div[1]/span[2]")
	    public WebElement Address_Error;
			
		@FindBy(xpath=".//*[@id='location_form']/div[1]/div[1]/span[3]")
	    public WebElement City_Error;
		
		@FindBy(xpath=".//*[@id='location_form']/div[1]/div[1]/div/div[2]/span")
	    public WebElement Zip_Error;	
		
		
		@FindBy(xpath=".//*[@id='location_form']/div[2]/div/div[1]/div[1]/span")
	    public WebElement Capacity_Error;
		
		@FindBy(id="location_region_errorText")
	    public WebElement Region_Error;
		
		@FindBy(id="location_county_errorText")
	    public WebElement County_Error;
		
		@FindBy(css=".btn.btn-success")
	    public WebElement SaveChanges;
		
}


