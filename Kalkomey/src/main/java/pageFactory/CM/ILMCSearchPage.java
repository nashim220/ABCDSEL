package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class ILMCSearchPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public ILMCSearchPage(WebDriver driver)
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
	
		@FindBy(css=".header.cf h1")
	    public WebElement Header;
		
		@FindBy(css="#physical_address_check div:nth-of-type(1) button")
	    public WebElement Physicaladdress;
		
		
		@FindBy(css="#address_verify div div input")
	    public WebElement Continue;
		
				public void ClickOnContinueButton()
				{
					Continue.click();
				}
		
		@FindBy(css=".state-info")
		public WebElement Agency;
		
		@FindBy(css="#search_firstname")
		public WebElement FirstName;
		
		public void EnterFirstName(String firstname)
		{
			FirstName.clear();
			FirstName.sendKeys(firstname);
		}
		
		@FindBy(css="#search_lastname")
		public WebElement LastName;

		public void EnterLastName(String lastname)
		{
			LastName.clear();
			LastName.sendKeys(lastname);
		}

		@FindBy(css="#student_info_dob_month")
		public WebElement Month;
		
		public void SelectMonth(String visibletext)
		{
			new Select(Month).selectByVisibleText(visibletext);
		}
		
		@FindBy(css="#student_info_dob_day")
		public WebElement Day;
		
		public void EnterDay(String day)
		{
			Day.clear();
			Day.sendKeys(day);
		}

		
		@FindBy(css="#student_info_dob_year")
		public WebElement Year;
		
		public void EnterYear(String year)
		{
			Year.clear();
			Year.sendKeys(year);
		}
		
	
		
		@FindBy(css="#search_button")
		public WebElement Search;
		
		public void ClickOnSearchButton()
		{
			Search.click();
		}
		
		@FindBy(css=".help2")
		public WebElement Help2;
		
		
		@FindBy(css=".alert.alert-error")
		public WebElement ErrorMessage;
		
		@FindBy(css=".row-fluid.sequencing")
		public WebElement NumberLine;
		
		@FindBy(css=".state-info b")
		public WebElement AgencyLogo;
		
		@FindBy(css=".sOne.span4 .active")
		public WebElement HighlitedNumber;
		
		@FindBy(css=".sThree.span4 .active")
		public WebElement HighlitedNumber3;
		
		@FindBy(css=".header.cf h1")
		public WebElement RecordNotFound;
		
		@FindBy(css=".container-narrow p a")
		public WebElement SearchAgain;

		public void ClickOnSearchAgainLink()
		{
			SearchAgain.click();
		}
		
		@FindBy(css=".breadcrumb.span12")
		public WebElement Breadcrumb;
		
		@FindBy(css="#student_info_dob_day + p")
		public WebElement ErrorDay;
		
		@FindBy(css="#student_info_dob_year + p")
		public WebElement ErrorYear;
		
		@FindBy(css=".noteHeading")
		public WebElement Success;
		
		@FindBy(css=".certRecord")
		public WebElement Certification;
		

		
	}
