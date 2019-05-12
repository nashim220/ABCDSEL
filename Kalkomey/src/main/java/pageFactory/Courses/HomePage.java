package pageFactory.Courses;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;


import utility.Constants;
import utility.JavaHelpers;

public class HomePage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public HomePage(WebDriver driver)
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
		
		// On home page e.g. http://qa1.boat-ed.com/
	
		@FindBy(css=".navbar-brand>span")
	    public WebElement Header_ApplicationName;

		@FindBy(css="div.navbar-header > a > span")
	    public WebElement Header_ApplicationName_New;
		
		@FindBy(css="li[class='select-state dropdown']>a")
	    public WebElement Header_SelectYourStateCountryDropdown;
		
		@FindBy(css="div#header-nav-items ul a")
	    public WebElement Header_SelectYourStateCountryDropdown_New;
		
		@FindBy(css="ul[class='nav pull-right'] a[class='btn btn-primary']")
	    public WebElement Header_Login;
		
		@FindBy(css="ul[class='nav navbar-nav navbar-right'] a[class='btn btn-primary']")
	    public WebElement Header_Login_New;
	
		@FindBy(css="div[class='home hero'] div[class='span12'] h1")
	    public WebElement Billboard_text;
		
		
		@FindBy(css="#hero h1")
	    public WebElement Billboard_textBoat;
		
		@FindBy(css="div[class='home hero'] span[class='select-state dropdown']>a")
	    public WebElement Billboard_ChooseYourStateCountryDropdown;
		
		
		@FindBy(css=".dropdown-toggle")
	    public WebElement Billboard_ChooseYourStateCountryDropdown_Hunter;
		
		@FindBy(css=".dropdown-toggle")
	    public WebElement BillboardBoat_ChooseYourStateCountryDropdown;
		
		@FindBy(css="div#hero div[class='select-state dropdown']>a")
	    public WebElement Billboard_ChooseYourStateCountryDropdown_New;
		
		@FindBy(css="#states ul[class='states-list clearfix']")
	    public WebElement States_section;
				
		@FindBy(css="#states ul[class='states-list']")
	    public WebElement StatesBoat_section;
				
		@FindBy(css="section#testimonials")
	    public WebElement Testimonials_section;
		
		@FindBy(css="section.testimonials")
	    public WebElement Testimonials_section_New;
		
		@FindBy(css="section#testimonials div div:nth-of-type(3) div>span>a>b")
	    public WebElement Testimonials_GetSafeGetCertifiedDropdown;
		
		@FindBy(css="#testimonials div span a")
	    public WebElement Testimonials_GetSafeGetCertifiedDropdownBoat;
		
		@FindBy(css="section.testimonials > div > div > div > div > a")
	    public WebElement Testimonials_GetSafeGetCertifiedDropdown_New;
		
		//To click on State links from Get Safe Get Certified dropdown
		public String Testmonials_ClickAndGetValueOnStatesLinkUnderGetSafeGetCertifiedDropdown(int i) throws InterruptedException
		{
			Thread.sleep(3000);
			String css = "section#testimonials ul#state-listing li:nth-child(" + i + ") a";
			String href = driver.findElement(By.cssSelector(css)).getAttribute("href");
			Thread.sleep(3000);
			driver.findElement(By.cssSelector(css)).click();
			return href; 
		}
		
		//To click on State links from Get Safe Get Certified dropdown
		public String Testmonials_ClickAndGetValueOnStatesLinkUnderGetSafeGetCertifiedDropdown_New(int i) throws InterruptedException
		{
			Thread.sleep(3000);
			String css = "section.testimonials ul#state-listing li:nth-child(" + i + ") a";
			String href = driver.findElement(By.cssSelector(css)).getAttribute("href");
			Thread.sleep(3000);
			driver.findElement(By.cssSelector(css)).click();
			return href; 
		}
		
		
		
		// On State page e.g. http://qa1.boat-ed.com/canada
		
		@FindBy(css="a[href='approved_boating_course.html']")
	    public WebElement StateAgencyName;
	
		@FindBy(css="small")
	    public WebElement StateAgencyName_FLV;
		
			//Footer
			@FindBy(css="footer li:nth-of-type(7) button")
		    public WebElement SendUsAnEmail;
			
			@FindBy(css="footer li:nth-of-type(6)")
		    public WebElement SendUsAnEmail_New;
			
			@FindBy(css="footer li:nth-of-type(8)")
		    public WebElement SendUsAnEmail_Footer;
			
			@FindBy(css="li[data-action='email_customer_service']")
		    public WebElement SendUsAnEmailSnow;
			
				//Send Us An Email pop up
				@FindBy(id="contact_us_modal")
			    public WebElement SendUsAnEmailPopUp;
				
				@FindBy(css="#contact_us_modal h3")
			    public WebElement SendUsAnEmailPopUp_Title;
				
				@FindBy(id="name")
			    public WebElement SendUsAnEmailPopUp_Name;
				
				@FindBy(id="email")
			    public WebElement SendUsAnEmailPopUp_Email;

				@FindBy(name="ContactUs[issue]")
			    public WebElement SendUsAnEmailPopUp_Reason;
				
				public void SendUsAnEmailPopUp_Reason_select(String text)
				{
					new Select(SendUsAnEmailPopUp_Reason).selectByVisibleText(text);
				}
				
				@FindBy(id="subject")
			    public WebElement SendUsAnEmailPopUp_Subject;
				
				@FindBy(id="message")
			    public WebElement SendUsAnEmailPopUp_Message;
				
				@FindBy(css="button[type='submit']")
			    public WebElement SendUsAnEmailPopUp_Submit;
				
				@FindBy(css="div.modal-body")
			    public WebElement SendUsAnEmailPopUp_ConfirmationMessage;
				
				@FindBy(css="#contact_us_modal div.modal-body")
			    public WebElement SendUsAnEmailPopUp_ConfirmationMessage_New;
				
				
		//State Specific page i.e. http://qa1.boat-ed.com/alabama/
		
		
				
		@FindBy(css="div.hero h1")
	    public WebElement State_Billboard_text1;
		
		@FindBy(css="div.hero p")
	    public WebElement State_Billboard_text2;
		
		@FindBy(css="section#hero h1")
	    public WebElement State_Billboard_text1_New;
		
		@FindBy(css="section#hero p")
	    public WebElement State_Billboard_text2_New;
		
		@FindBy(css="#steps h2")
	    public WebElement State_CompletionSteps_Section;
		
		@FindBy(css="#steps h2:nth-of-type(1)")
	    public WebElement State_CompletionSteps_Section_Boat;
		
		@FindBy(css="#main-steps h1")
	    public WebElement State_CompletionSteps_Section_New;
		
		@FindBy(css="#steps h2")
	    public WebElement Bowhunter_State_CompletionSteps_Section;
		
		@FindBy(css="#steps h2")
	    public WebElement Snowmobile_State_CompletionSteps_Section;
		
		@FindBy(css="#overview h2")
	    public WebElement State_Comprehensive_Section;
			
		@FindBy(css="#marketing-print h3")
	    public WebElement Snowmobile_State_Comprehensive_Section;
		
		@FindBy(css="#overview h1")
	    public WebElement State_Curriculum_Section;
		
		@FindBy(css="section#marketing-video h2")
	    public WebElement WhatToExpectSection_HeaderText_New;
		
		@FindBy(css="#marketing-print h3")
	    public WebElement Bowhunter_State_Comprehensive_Section;
				
		//What to Expect Section
			@FindBy(css="section#what-to-expect")
		    public WebElement WhatToExpectSection;
			
			@FindBy(css="section#what-to-expect h2")
		    public WebElement WhatToExpectSection_HeaderText;
			
			@FindBy(css="section#what-to-expect > div > div > div:nth-of-type(1) h3")
		    public WebElement WhatToExpectSection_LearningSection;
	
			@FindBy(css="section#what-to-expect > div > div > div:nth-of-type(2) h3")
		    public WebElement WhatToExpectSection_ReinforcementSection;
			
			@FindBy(css="section#what-to-expect > div > div > div:nth-of-type(3) h3")
		    public WebElement WhatToExpectSection_MasterySection;
					
		
		// Coupon Banner
			
			@FindBy(css="#coupon-promo h2")
		    public WebElement CouponBannerText ;
			
			@FindBy(id="coupon_conditions")
		    public WebElement CouponBannerTermsConditionLink;
			
			@FindBy(css="#coupon-promo button")
		    public WebElement CouponBannerTermsConditionLink_Boat;
			
			@FindBy(id="coupon-terms")
		    public WebElement CouponBannerTermsConditionBox;
				
			@FindBy(css=".twitter")
		    public WebElement Twitter;
			
			public void ClickOnTwitterButton()
			{
				Twitter.sendKeys(Keys.RETURN);
			}
		
			
			@FindBy(css=".brand")
		    public WebElement ApplicationName_Course;
		
		//Boat New
			
			
			@FindBy(css="#expectation-headline")
		    public WebElement WhatToExpect_Boat;
			
			@FindBy(css="#marketing-video h3")
		    public WebElement WhatToExpectLiveAction_Boat;
			
			@FindBy(css=".agency-link")
		    public WebElement AgencyName_New;
			
			@FindBy(css=".agency")
			public WebElement AgencyInformation;
			
			@FindBy(css="#expectation-headline")
		    public WebElement WhatToExpet_Offroad;
			
			@FindBy(css="#page > div.content.clearfix > div.row-fluid > div.span3 > div > ul > li:nth-child(3) > a")
			public WebElement Registration;
			
			public void Click_Registration()
			{
				Registration.click();
			}

			@FindBy(css="#search_data")
			public WebElement ID;
			
			public void Enter_ID()
			{
				ID.clear();
				ID.sendKeys("8456034");
			}
			
			@FindBy(css="#search-button")
			public WebElement Search;
			
			public void Click_Search()
			{
				Search.click();
			}

			@FindBy(css="#page > div.content.clearfix > div:nth-child(4) > div > div > div > table > tbody > tr > td:nth-child(2) > a")
			public WebElement student;

			public void click_Student()
			{
				student.click();
			}
			
}