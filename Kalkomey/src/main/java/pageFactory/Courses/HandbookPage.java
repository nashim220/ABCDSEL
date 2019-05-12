package pageFactory.Courses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class HandbookPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public HandbookPage(WebDriver driver)
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
	
		@FindBy(css="div.container h1")
	    public WebElement ApplicationName;
		
		@FindBy(css="#overview h1")
	    public WebElement PageHeader;
		
		@FindBy(css=".lead")
	    public WebElement PageHeader_SubText;
		
		/*@FindBy(css="#primaryActionBtn")
	    public WebElement BuyTheEbook;*/
		
		@FindBy(css=".shopify-buy__btn")
		public WebElement BuyTheEbook;
		
		@FindBy(css="#main section:nth-of-type(2) div:nth-of-type(2) p")
	    public WebElement HandbookFormatInfo;
		
		@FindBy(css="p.download-info a:nth-of-type(1)")
	    public WebElement HandbookFormatInfo_Apple;
		
		@FindBy(css="p.download-info a:nth-of-type(2)")
	    public WebElement HandbookFormatInfo_NookBook;
		
		@FindBy(css="p.download-info a:nth-of-type(3)")
	    public WebElement HandbookFormatInfo_WebVersion;
		
		
		// Boater's Guide  section
		@FindBy(css="section#content-promo h1")
	    public WebElement BoatersGuideHeader;
		
		
		//Get it on your Phone, eReader, and Desktop section
	
		@FindBy(css="section#screen-shots h1")
	    public WebElement GetItHeader;
		
			//iBook Version Section
			@FindBy(css="section#versions div div:nth-of-type(1) h3")
		    public WebElement iBookVesionHeader;
			
			@FindBy(css="section#versions div div:nth-of-type(1) p")
		    public WebElement iBookVesionInfoText;
			
			@FindBy(css="section#versions div div:nth-of-type(1) a")
		    public WebElement iBookVesionInfoLink;
			
			
			//Nook Version Section
			@FindBy(css="section#versions div div:nth-of-type(2) h3")
		    public WebElement NookVesionHeader;
			
			@FindBy(css="section#versions div div:nth-of-type(2) p")
		    public WebElement NookVesionInfoText;
			
			@FindBy(css="section#versions div div:nth-of-type(2) a")
		    public WebElement NookVesionInfoLink;
			
			
			//Free Version Section
			@FindBy(css="section#versions div div:nth-of-type(3) h3")
		    public WebElement FreeVesionHeader;
			
			@FindBy(css="section#versions div div:nth-of-type(3) p")
		    public WebElement FreeVesionInfoText;
			
			@FindBy(css="section#versions div div:nth-of-type(3) a")
		    public WebElement FreeVesionInfoLink;
			
			
		//Footer
			
			//Contact Us
			@FindBy(css="footer div div:nth-of-type(1)")
		    public WebElement Footer_ContactUs;
			
			//HOW DO I GET MY STATE BOATING LICENSE?
			@FindBy(css="footer div div:nth-of-type(2)")
		    public WebElement Footer_GetMyBoatingLicence;
			
			//About Us
			@FindBy(css="footer div div:nth-of-type(3)")
		    public WebElement Footer_AboutUs;
			
			//About Us
			@FindBy(css="footer div div:nth-of-type(4) img")
		    public WebElement Footer_BoatEdLogo;
			
			@FindBy(css="footer div div:nth-of-type(3) img")
		    public WebElement Footer_BoatEdLogo_Massachusetts;
			
			//KalKomey info
			@FindBy(css="section#legal div > div:nth-of-type(1)")
		    public WebElement Footer_KalKomeyInfo;
			
			//ABOUT BOAT ED
			@FindBy(css="section#legal div > div:nth-of-type(2)")
		    public WebElement Footer_AboutBoatEd;
			
			//Rights info
			@FindBy(css="section#legal div > div:nth-of-type(3)")
		    public WebElement Footer_RightsInfo;
			
			//Products info
			@FindBy(css="section#products div > div")
		    public WebElement Footer_ProductsInfo;
			
			
			
		// book.html
			
			@FindBy(css="div.page-header h1")
		    public WebElement Book_PageHeader;
			
			@FindBy(css="div.page-header p")
		    public WebElement Book_PageHeader_Text;
			
			@FindBy(css="div.page-header h3")
		    public WebElement Book_PageHeader_WebVersionText;
			
			@FindBy(css=".col-md-4.toc>h4")
		    public WebElement Book_Index_Title;
			
			//Click on Link from Contents & get text
			
			public String ClickOnContentAndGetText(int i) throws InterruptedException
			{
				String cssselector = "#main nav ul li:nth-of-type(" + i + ") a";
				driver.findElement(By.cssSelector(cssselector)).click();
				Thread.sleep(4000);
				return driver.findElement(By.cssSelector(cssselector)).getText();
				
			}
			
			//Content page Preview
				
				@FindBy(css="#main ul:nth-of-type(1) li.previous a")
			    public WebElement PreviousButton_Top;
				
				@FindBy(css="#main ul:nth-of-type(2) li.previous a")
			    public WebElement PreviousButton_Bottom;
				
				@FindBy(css="#main ul:nth-of-type(1) li.next a")
			    public WebElement NextButton_Top;
				
				@FindBy(css="#main ul:nth-of-type(2) li.next a")
			    public WebElement NextButton_Bottom;
				
				@FindBy(css="#main ul:nth-of-type(1) li.page-count")
			    public WebElement PageCount;
			
				@FindBy(css="div.ebook h1")
			    public WebElement PageTitle;
				
				
			//Quick links
			public String ClickOnQuickLinksAndGetText(int i) throws InterruptedException
			{
				String cssselector = ".nav.nav-list li:nth-of-type(" + i + ") a";
				String temp = driver.findElement(By.cssSelector(cssselector)).getText();
				driver.findElement(By.cssSelector(cssselector)).click();
				Thread.sleep(5000);
				
				return temp;
				
			}		
			
			
			//Download the PDF Version
			@FindBy(css="#main div:nth-of-type(2) div p a")
		    public WebElement DownloadPDFVersionButton;
			
			//Apple iBook button 
			@FindBy(css="div.book-content p a[rel='external']")
		    public WebElement AppleiBookButton ;
			
			//B&N Nook Book button 
			@FindBy(css="div.book-content p a:nth-of-type(2)")
		    public WebElement BNNookBookButton ;
}

