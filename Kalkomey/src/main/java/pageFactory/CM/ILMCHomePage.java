package pageFactory.CM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class ILMCHomePage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public ILMCHomePage(WebDriver driver)
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
	
		@FindBy(css=".brand img")
	    public WebElement Logo;
		
		
		public void ClickOnLogo()
		{
			Logo.click();
		}
		
		@FindBy(css=".ilmcBranding h1")
		public WebElement Header;
		
		@FindBy(css=".lead")
		public WebElement SubHeader;
		
		@FindBy(css=".findRec")
		public WebElement Step1;
		
		@FindBy(css=".verifyRec")
		public WebElement Step2;
	
		@FindBy(css=".cardPurch")
		public WebElement Step3;
		
		@FindBy(css="#searchState")
		public WebElement State;
		
		public void SelectState(String visibletext)
		{
			new Select(State).selectByVisibleText(visibletext);
		}
		
		@FindBy(css="#searchCollection")
		public WebElement Collection;
		
		public void SelectCollection(String visibletext)
		{
			new Select(Collection).selectByVisibleText(visibletext);
		}
		
		@FindBy(css="#stateProgForm input")
		public WebElement Continue;
		
		public void ClickOnContinueButton()
		{
			Continue.click();
		}
		
		@FindBy(css=".row-fluid.instructions")
		public WebElement NeedHelp;
		
		@FindBy(css=".url span")
		public WebElement KalkomeyURLText;
		
		@FindBy(css=".url")
		public WebElement KalkomeyURL;
			
		@FindBy(css=".kPrograms p a")
		public WebElement PressRelease;
		
		@FindBy(css=".company p:nth-of-type(2) a")
		public WebElement Privacy;
		
		@FindBy(css=".kPrograms>a:nth-of-type(1)")
		public WebElement FB;
		
		@FindBy(css=".kPrograms>a:nth-of-type(2)")
		public WebElement YouTube;
		
		@FindBy(css="footer div.row-fluid")
		public WebElement Footer;
		
		
		@FindBy(css=".container-narrow+div")
		public WebElement Help;
		
		public String GetAllSisterLinksName()
		{
			String sociallinks ="";
			for(int i=1; i<=5; i++)
			{
				String css = ".kPrograms p:nth-of-type(3) a:nth-of-type(" + i + ")";
				sociallinks = sociallinks + driver.findElement(By.cssSelector(css)).getText(); 
			}
			
			return sociallinks;
		}
		
		
		public String GetAllSisterLinks()
		{
			String sociallinks ="";
			for(int i=1; i<=5; i++)
			{
				String css = ".kPrograms p:nth-of-type(3) a:nth-of-type(" + i + ")";
				sociallinks = sociallinks + driver.findElement(By.cssSelector(css)).getAttribute("href"); 
			}
			
			return sociallinks;
		}
}
