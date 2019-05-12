package pageFactory.EM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class RegisterEdHomePage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public RegisterEdHomePage(WebDriver driver)
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
	
		@FindBy(css=".btn.btn-primary.login-link")
	    WebElement UpdateRegi;
		
		@FindBy(css=".btn.btn-danger.login-link")
	    WebElement CancelRegi;
		
		@FindBy(css=".hero-unit-logo")
	    WebElement Logo;
		
		@FindBy(css=".page-header.page-header-dark.page-header-compressed>h1")
		public WebElement Header;
		
		@FindBy(css=".l-shadowbox.l-contentbox.mbm>p")
		public WebElement Message;
		
		@FindBy(css="div.hero-unit-bd")
		public WebElement HeroArea;
		
		@FindBy(css=".btn.btn-inverse")
		WebElement Menu;
		
		@FindBy(css="div[class='row kalkomey-info'] address:nth-child(2) a:nth-child(1)")
		public WebElement AboutKalKomeySectionHeader;
		
		
		@FindBy(css=".navlist-help.clearfix dd:nth-of-type(2) a")
		public WebElement ContactUsSection;
		
		@FindBy(css=".navlist-help.clearfix dd:nth-of-type(1) a")
		public WebElement FAQSection;
	  
		@FindBy(css=".login>a")
		public WebElement AgencyLogin;
		
		@FindBy(css=".mtm>a")
		public WebElement Terms;
		
		
		
		public String ClickAndGetValueOnStatesLink(int i) throws InterruptedException
		{
			Thread.sleep(5000);
			//String css = "#homeForm ul#navDropdown li:nth-child(" + i + ") a";
			String css = "#navDropdown li:nth-child(" + i + ") a";
			String href = driver.findElement(By.cssSelector(css)).getAttribute("href");
			Thread.sleep(5000);
			driver.findElement(By.cssSelector(css)).click();
			Thread.sleep(5000);
			return href; 
		}
		
		public String GetAllSisterLinksName()
		{
			String sociallinks ="";
			for(int i=1; i<=5; i++)
			{
				String css = ".navlist-horizontal li:nth-of-type(" + i + ") a";
				sociallinks = sociallinks + driver.findElement(By.cssSelector(css)).getText(); 
			}
			
			return sociallinks;
		}
		
		
		public String GetAllSisterLinks()
		{
			String sociallinks ="";
			for(int i=1; i<=5; i++)
			{
				String css = ".navlist-horizontal li:nth-of-type(" + i + ") a";
				sociallinks = sociallinks + driver.findElement(By.cssSelector(css)).getAttribute("href"); 
			}
			
			return sociallinks;
		}
		
		
	//Click on Update Registration Button
		public void ClickOnUpdateRegiButton()
		{
			UpdateRegi.click();
		}
	

	//Click on Cancel Registration Button
		public void ClickOnCancelRegiButton()
		{
			CancelRegi.click();
		}

		public void ClickOnMenu()
		{
			Menu.click();
		}

}
