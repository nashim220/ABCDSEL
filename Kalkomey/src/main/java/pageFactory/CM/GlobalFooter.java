package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class GlobalFooter 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public GlobalFooter(WebDriver driver)
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
	
	
		@FindBy(css=".span6 h4")
	    public WebElement AboutUs;
		
		@FindBy(css=".span6")
		 public WebElement AboutUsDetails;
			
		@FindBy(css=".span3 h4")
		public WebElement CustomerSupport;
		
		@FindBy(css=".span3")
	    public WebElement CustomerSupportDetails;
		
		@FindBy(css="div.nav-collapse li.dropdown:nth-of-type(1) ul li:nth-of-type(1) a")
	    public WebElement Admin_ManageCollection;
			
		@FindBy(css="div.nav-collapse li.dropdown:nth-of-type(1) ul li:nth-of-type(2) a")
	    public WebElement Admin_ManageCollectionOrigin;
			
		@FindBy(css="div.nav-collapse li.dropdown:nth-of-type(1) ul li:nth-of-type(3) a")
	    public WebElement Admin_ManageUsers;
			
		@FindBy(css="div.nav-collapse li.dropdown:nth-of-type(1) ul li:nth-of-type(4) a")
	    public WebElement Admin_CollectionActivity;
		
				
}
