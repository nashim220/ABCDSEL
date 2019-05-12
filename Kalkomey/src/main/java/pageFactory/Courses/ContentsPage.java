package pageFactory.Courses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class ContentsPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public ContentsPage(WebDriver driver)
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
	
		
		@FindBy(linkText="Contents")
	    WebElement ContentsLink;
		
		@FindBy(css="#course_controls > li.next > a")
	    WebElement NextButton;
		
		@FindBy(css="#contents-toggle-text-container")
	    public WebElement ViewEntireCourseOutline;
		
		@FindBy(css="#contents-toggle-text-container a:nth-of-type(2)")
	    public WebElement HideEntireCourseOutline;
		
		@FindBy(css=".btn.btn-small")
	    public WebElement ContinueWhereYouLeftOff;
		
		@FindBy(css="div.page-header h1")
	    public WebElement PageHeader;
		
		@FindBy(css="div.section_index p:nth-child(1)")
	    public WebElement CourseIntroTextPara1;
		
		@FindBy(css="div.section_index p:nth-child(2)")
	    public WebElement CourseIntroTextPara2;
		
		@FindBy(css="div[class='span8 offset2'] p:nth-of-type(1)")
	    public WebElement CourseIntroTextPara1_Hunter;
		
		@FindBy(css="div[class='span8 offset2'] p:nth-of-type(2)")
	    public WebElement CourseIntroTextPara2_Hunter;
		
		@FindBy(css="ul[class='nav pull-right'] li a")
	    public WebElement Contents;
		
		@FindBy(css="#timer_space_remaining")
		public WebElement timer;
		
		@FindBy(css="#intro-modal-dismiss")
	    public WebElement IntroModal;
		
		//To get Units text from page
		public String GetUnitText(int unit) throws InterruptedException
		{
			String cssSelector = "nav ul.nav-course > li:nth-of-type(" + unit + ") > a";
			return driver.findElement(By.cssSelector(cssSelector)).getText();
			
		}
		
		//To get Exams text from page
		public String GetExamsText() throws InterruptedException
		{
			String cssSelector = "nav ul.nav-course > li > span";
			
			List<WebElement> examlinks = driver.findElements(By.cssSelector(cssSelector));
			String text = "";
			for(WebElement e:examlinks)
			{
				text = text + e.getText().trim();
			}
			
			return text;
		}
		
		
		// Untimed course
		
		public void Untimed_ClickOnUnitTopicPageLink(int unit, int topic, int page) throws InterruptedException
		{
			Thread.sleep(3000);
			String cssSelector = "nav ul.nav-course > li:nth-of-type(" + unit + ") > a";
			driver.findElement(By.cssSelector(cssSelector)).click();
			Thread.sleep(4000);
			
			cssSelector = "nav ul.nav-course > li:nth-of-type(" + unit + ") > ul > li:nth-of-type(" + topic + ")  > a";
			driver.findElement(By.cssSelector(cssSelector)).click();
			Thread.sleep(4000);
			
			cssSelector = "nav ul.nav-course > li:nth-of-type(" + unit + ") > ul > li:nth-of-type(" + topic + ") > ul > li:nth-of-type(" + page + ") > a";
			driver.findElement(By.cssSelector(cssSelector)).click();
			Thread.sleep(4000);
		}
		
		public String Untimed_GetUnitHrefLink(int unit, int topic, int page) throws InterruptedException
		{
			String cssSelector = "nav ul.nav-course > li:nth-of-type(" + unit + ") > a";
			driver.findElement(By.cssSelector(cssSelector)).click();
			Thread.sleep(2000);
			
			cssSelector = "nav ul.nav-course > li:nth-of-type(" + unit + ") > ul > li:nth-of-type(" + topic + ")  > a";
			driver.findElement(By.cssSelector(cssSelector)).click();
			Thread.sleep(2000);
			
			cssSelector = "nav ul.nav-course > li:nth-of-type(" + unit + ") > ul > li:nth-of-type(" + topic + ") > ul > li:nth-of-type(" + page + ") > a";
			String href = driver.findElement(By.cssSelector(cssSelector)).getAttribute("href");
			
			cssSelector = "nav ul.nav-course > li:nth-of-type(" + unit + ") > ul > li:nth-of-type(" + topic + ")  > a";
			driver.findElement(By.cssSelector(cssSelector)).click();
			Thread.sleep(2000);
			
			cssSelector = "nav ul.nav-course > li:nth-of-type(" + unit + ") > a";
			driver.findElement(By.cssSelector(cssSelector)).click();
			Thread.sleep(2000);

			return href;
			
			
		}
		
		
		public String Timed_GetUnitHrefLink(int unit, int topic, int page) throws InterruptedException
		{
			
			String cssSelector = "nav ul.nav-course > li:nth-of-type(" + unit + ") > ul > li:nth-of-type(" + topic + ") > ul > li:nth-of-type(" + page + ") > a";
			String href = driver.findElement(By.cssSelector(cssSelector)).getAttribute("href");
			
			
			return href;
			
			
		}
		
		@FindBy(css="a[class='btn btn-mini btn-success']")
	    public WebElement TakeQuiz;
		
		@FindBy(css="span[class='btn btn-mini disabled']")
	    public WebElement TakeCertificationExam_Disabled;
		
		@FindBy(css="a[class='btn btn-small btn-success']")
	    public WebElement TakeCertificationExam_Enabled;
		
	
		//Clicking Contents link from top header
		public void ClickOnContentLink()
		{
			ContentsLink.click();
		}
		
		
		//Clicking Contents link from top header
		public void ClickOnNextButton()
		{
			NextButton.click();
		}
			
		
		
		
}
