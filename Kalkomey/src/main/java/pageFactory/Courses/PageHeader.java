package pageFactory.Courses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


import utility.Constants;
import utility.JavaHelpers;

public class PageHeader 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public PageHeader(WebDriver driver)
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
		
		
		//Old UI
		@FindBy(css="ul[class='nav pull-right'] li:nth-of-type(1) a")
	    public WebElement StudyGuide;
		
		@FindBy(css="ul[class='nav pull-right'] li:nth-of-type(2) a")
	    public WebElement LostYourCard;
		
		@FindBy(css="ul[class='nav pull-right'] li:nth-of-type(3) a")
	    public WebElement Before_FAQs;
		
		@FindBy(css="ul[class='nav pull-right'] li:nth-of-type(4) a")
	    public WebElement Register;
		
		
		@FindBy(css="ul[class='nav pull-right'] li:nth-of-type(5) a")
	    public WebElement Login;
		
		
		@FindBy(css="ul[class='nav pull-right'] li:nth-of-type(1) a")
	    public WebElement Contents;
		
		
		@FindBy(css="ul[class='nav pull-right'] li:nth-of-type(2) a")
	    public WebElement After_FAQs;
		
		@FindBy(css="ul[class='nav pull-right'] li:nth-of-type(4) a")
	    public WebElement Profile;
		
		@FindBy(css="ul[class='nav pull-right'] li:nth-of-type(5) a")
	    public WebElement SaveAndLogOut;
		
		
		@FindBy(css="header[class='navbar navbar-fixed-top'] div.container h1")
	    public WebElement ApplicationName;
		
		
		//New UI
		@FindBy(css="div.navbar-header > a span")
	    public WebElement ApplicationName_New;
		

		@FindBy(css="#header-nav-items > ul > li:nth-child(1) > a")
	    public WebElement StudyGuide_New;
		
		@FindBy(css="#header-nav-items > ul > li:nth-child(2) > a")
	    public WebElement LostYourCard_New;
		
		@FindBy(css="#header-nav-items > ul > li:nth-child(3) > a")
	    public WebElement Before_FAQs_New;
		
		@FindBy(css="#header-nav-items > ul > li:nth-child(4) > p > a")
	    public WebElement Register_New;
		
		@FindBy(css="#header-nav-items > ul > li:nth-child(5) > p > a")
	    public WebElement Login_New;
		
		@FindBy(css="a[href='/site/logout']")
	    public WebElement Logout_Handgun;
		
		@FindBy(css="#header-nav-items > ul > li:nth-child(2) > p > a")
	    public WebElement Register_Florida;
		
		
}
