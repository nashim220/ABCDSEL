package pageFactory.Courses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


import utility.Constants;
import utility.JavaHelpers;

public class PageFooter 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public PageFooter(WebDriver driver)
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
		
		// On Course page e.g. http://qa1.boat-ed.com/alabama/
	
			//About Boat Ed
			
			@FindBy(css="section#about-program h3")
		    public WebElement AboutBoatEdHeader;

			@FindBy(css="#about-program>p")
		    public WebElement AboutBoatEdText;
			
			@FindBy(css="#social")
		    public WebElement FollowUs; 
			
			@FindBy(css="div#ebook-info h3")
		    public WebElement BoatingSafetyHandbooksHeader;
			
			@FindBy(css="div#ebook-info p")
		    public WebElement BoatingSafetyHandbooksText;
			
			@FindBy(css="div#buy-ebook a")
		    public WebElement BuyYourBoatingHandbookButton;
			
			@FindBy(css="div#buy-ebook span")
		    public WebElement BuyYourBoatingHandbookPrice;
			
			
			@FindBy(css="div[class='kalkomey-info row'] div:nth-of-type(1) h3")
		    public WebElement SisterCoursesSectionHeader;
			
			@FindBy(css="div[class='kalkomey-info row'] section:nth-of-type(1) h4")
		    public WebElement SisterCoursesSectionHeaderOther;
			
			@FindBy(css="div[class='kalkomey-info row'] div:nth-of-type(3) h3")
		    public WebElement AboutKalKomeySectionHeader;
			
			@FindBy(css="div[class='kalkomey-info row'] section:nth-of-type(2) h4")
		    public WebElement AboutKalKomeySectionHeaderOther;
			
			@FindBy(css="div[class='kalkomey-info row'] div:nth-of-type(2) h3")
		    public WebElement CustomerSupportSectionHeader;
			
			@FindBy(css="div[class='kalkomey-info row'] section:nth-of-type(3) h4")
		    public WebElement CustomerSupportSectionHeaderOther;
			
			@FindBy(id="copyright")
		    public WebElement CopyrightSection;
			
			
			//After login
			@FindBy(css="[class='container in-course'] div:nth-of-type(1) ul")
		    public WebElement SocialLinks;
			
			public String GetAllSocialLinksName()
			{
				String sociallinks="";
				for(int i=1; i<=6; i++)
				{
					String css = "footer.container ul:nth-of-type(1) li:nth-of-type(" + i + ") a";
					sociallinks = sociallinks + driver.findElement(By.cssSelector(css)).getText(); 
				}
				
				return sociallinks;
			}
			
			
			public String GetAllSocialLinks()
			{
				String sociallinks ="";
				for(int i=1; i<=6; i++)
				{
					String css = "footer.container ul:nth-of-type(1) li:nth-of-type(" + i + ") a";
					sociallinks = sociallinks + driver.findElement(By.cssSelector(css)).getAttribute("href"); 
				}
				
				return sociallinks;
			}
			
			
			public String GetAllSisterLinksName()
			{
				String sociallinks ="";
				for(int i=1; i<=6; i++)
				{
					String css = "footer.container ul:nth-of-type(2) li:nth-of-type(" + i + ") a";
					sociallinks = sociallinks + driver.findElement(By.cssSelector(css)).getText(); 
				}
				
				return sociallinks;
			}
			
			
			public String GetAllSisterLinks()
			{
				String sociallinks ="";
				for(int i=1; i<=6; i++)
				{
					String css = "footer.container ul:nth-of-type(2) li:nth-of-type(" + i + ") a";
					sociallinks = sociallinks + driver.findElement(By.cssSelector(css)).getAttribute("href"); 
				}
				
				return sociallinks;
			}
			
			
			public String GetAllSocialLinksName_Bowhunter()
			{
				String sociallinks="";
				for(int i=1; i<=4; i++)
				{
					String css = "footer.container ul:nth-of-type(1) li:nth-of-type(" + i + ") a";
					sociallinks = sociallinks + driver.findElement(By.cssSelector(css)).getText(); 
				}
				
				return sociallinks;
			}
			
			
			public String GetAllSocialLinks_Bowhunter()
			{
				String sociallinks ="";
				for(int i=1; i<=4; i++)
				{
					String css = "footer.container ul:nth-of-type(1) li:nth-of-type(" + i + ") a";
					sociallinks = sociallinks + driver.findElement(By.cssSelector(css)).getAttribute("href"); 
				}
				
				return sociallinks;
			}
			
			
			public String GetAllSisterLinksName_Bowhunter()
			{
				String sociallinks ="";
				for(int i=1; i<=6; i++)
				{
					String css = "footer.container ul:nth-of-type(2) li:nth-of-type(" + i + ") a";
					sociallinks = sociallinks + driver.findElement(By.cssSelector(css)).getText(); 
				}
				
				return sociallinks;
			}
			
			
			public String GetAllSisterLinks_Bowhunter()
			{
				String sociallinks ="";
				for(int i=1; i<=6; i++)
				{
					String css = "footer.container ul:nth-of-type(2) li:nth-of-type(" + i + ") a";
					sociallinks = sociallinks + driver.findElement(By.cssSelector(css)).getAttribute("href"); 
				}
				
				return sociallinks;
			}
			
			
			public String GetAllSocialLinksName_Snowmobile()
			{
				String sociallinks="";
				for(int i=1; i<=2; i++)
				{
					String css = "footer.container ul:nth-of-type(1) li:nth-of-type(" + i + ") a";
					sociallinks = sociallinks + driver.findElement(By.cssSelector(css)).getText(); 
				}
				
				return sociallinks;
			}
			
			
			public String GetAllSocialLinks_Snowmobile()
			{
				String sociallinks ="";
				for(int i=1; i<=2; i++)
				{
					String css = "footer.container ul:nth-of-type(1) li:nth-of-type(" + i + ") a";
					sociallinks = sociallinks + driver.findElement(By.cssSelector(css)).getAttribute("href"); 
				}
				
				return sociallinks;
			}
			

			
			
			@FindBy(css="[class='container in-course'] div:nth-of-type(2) ul")
		    public WebElement SisterSitesLinks;
			
			@FindBy(css="[class='container in-course'] div:nth-of-type(2) div")
		    public WebElement TextBelowLogo;
			
			@FindBy(css="div[class='kalkomey-info row'] div:nth-of-type(1) h3")
		    public WebElement SisteLink_Header;
			
}
