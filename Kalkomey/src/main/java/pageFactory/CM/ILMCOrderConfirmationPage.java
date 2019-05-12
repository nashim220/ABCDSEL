package pageFactory.CM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class ILMCOrderConfirmationPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public ILMCOrderConfirmationPage(WebDriver driver)
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
	
		@FindBy(css=".alert.alert-success")
	    public WebElement Success;
		
	
		
		@FindBy(css=".container-narrow.topContainer div p:nth-of-type(2)")
		public WebElement BillingDetails;
		
		@FindBy(css=".container-narrow.topContainer div p:nth-of-type(3)")
		public WebElement AuthorizationCode;
		
		
		@FindBy(css=".container-narrow.topContainer div p:nth-of-type(5)")
		public WebElement SupportText;
			
		@FindBy(css=".kPrograms h5 + a")
		public WebElement FB;
		
		@FindBy(css=".kPrograms a + a")
		public WebElement YouTube;
		
		
		
		public String GetAllSisterLinksName()
		{
			String sociallinks ="";
			for(int i=1; i<=5; i++)
			{
				
				String css = ".well.topSpace ul li:nth-of-type(" + i + ") a";
				sociallinks = sociallinks + driver.findElement(By.cssSelector(css)).getText(); 
			}
			
			return sociallinks;
		}
		
		
		public String GetAllSisterLinks()
		{
			String sociallinks ="";
			for(int i=1; i<=5; i++)
			{
				String css = ".well.topSpace ul li:nth-of-type(" + i + ") a";
				sociallinks = sociallinks + driver.findElement(By.cssSelector(css)).getAttribute("href"); 
			}
			
			return sociallinks;
		}
			
				
}
