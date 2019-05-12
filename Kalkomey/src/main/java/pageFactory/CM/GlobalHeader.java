package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class GlobalHeader 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public GlobalHeader(WebDriver driver)
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
	
		//Admin 
		@FindBy(css="div.nav-collapse li.dropdown:nth-of-type(2) a")
	    public WebElement UsernameDropdown;
		
			@FindBy(css="div.nav-collapse li.dropdown:nth-of-type(2) ul li:nth-of-type(1) a")
		    public WebElement Username_MyProfile;
			
			@FindBy(css="a[href='/logout']")
			public WebElement Logout;
		
		
		@FindBy(css="div.nav-collapse li.dropdown:nth-of-type(1) a")
	    public WebElement AdminDropdown;
		
		
			@FindBy(css="div.nav-collapse li.dropdown:nth-of-type(1) ul li:nth-of-type(1) a")
			public WebElement Admin_ManageAgency;
			
			@FindBy(css="div.nav-collapse li.dropdown:nth-of-type(1) ul li:nth-of-type(2) a")
	    	public WebElement Admin_ManageCollection;
			
			@FindBy(css="div.nav-collapse li.dropdown:nth-of-type(1) ul li:nth-of-type(3) a")
	    	public WebElement Admin_ManageCollectionOrigin;
			
			@FindBy(css="div.nav-collapse li.dropdown:nth-of-type(1) ul li:nth-of-type(4) a")
	    	public WebElement Admin_ManageUsers;
			
			@FindBy(css="div.nav-collapse li.dropdown:nth-of-type(1) ul li:nth-of-type(5) a")
	    	public WebElement Admin_CollectionActivity;
		
			
		//Non Admin
		@FindBy(css="div ul li:nth-of-type(2) a i")
	    public WebElement UsernameDropdown_NonAdmin;
		
			@FindBy(css="div ul li:nth-of-type(2) li:nth-of-type(1) a")
		    public WebElement Username_MyProfile_NonAdmin;
		
		//Forbid Kalmkomey Employee
		@FindBy(css=".dropdown-toggle")
		public WebElement UsernameDropdown_Forbid;
		
		@FindBy(css="body > div.navbar.navbar-inverse.navbar-fixed-top > div > div > div > ul > li > ul > li:nth-child(1) > a")
		public WebElement Username_MyProfile_Forbid;
		
		
		//Alert messages
		@FindBy(css="div.alert-error")
	    public WebElement Error_AlertText;
		
		@FindBy(css=".alert.alert-success")
	    public WebElement Success_AlertText;
		
		@FindBy(css="div.alert-notice")
	    public WebElement Notice_AlertText;
				
		@FindBy(css=".brand img")
	    public WebElement Logo;
				
		@FindBy(css=".alert.alert-notice>a")
	    public WebElement ClickHereLink;
	}
