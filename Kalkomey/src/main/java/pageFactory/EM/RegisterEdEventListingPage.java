package pageFactory.EM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class RegisterEdEventListingPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public RegisterEdEventListingPage(WebDriver driver)
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
	
	
	
		//@FindBy(css=".btn.btn-large.pull-right.hidden-phone")
		@FindBy(css="li:nth-of-type(1) div div a.btn.btn-large.pull-right.hidden-phone")
		WebElement ViewEvent;

	
		//Click on View Event
		public void ClickOnViewEventButton()
		{
			ViewEvent.click();
		}
		
		
		@FindBy(css="#zip_toggle")
		WebElement ZipCode;
	
		
		//Click on Search Event Link
		public void ClickOnZipCodeButton()
		{
			ZipCode.click();
		}
			
		
		
		@FindBy(css="#eventDistance")
		WebElement EventDistance;
		
	
		public void EventDistance(String visibletext)
		{
			new Select(EventDistance).selectByVisibleText(visibletext);
		}
		
		
		@FindBy(css="#eventZip")
		public WebElement Zip;
		
		//Enter Search Data
		public void EnterSearchData(String zip)
		{
			Zip.clear();
			Zip.sendKeys(zip);
		}	
		
		@FindBy(css="a.button")
	    public WebElement SearchButton;
		
		//Click on Search button
		public void ClickOnSearchButton()
		{
			SearchButton.click();
		}
		
		
		@FindBy(css="#remove_ctrl")
	    public WebElement RemoveLocation;
		
		public void ClickOnRemoveLocation()
		{
			RemoveLocation.click();
		}
		
		
		
		@FindBy(css="#geolocation_ctrl")
		WebElement GeoLocation;
		
		//Click on Search button
		public void ClickOnGeoLocationButton()
		{
			GeoLocation.click();
		}
	
		
}
