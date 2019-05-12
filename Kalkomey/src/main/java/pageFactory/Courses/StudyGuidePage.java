package pageFactory.Courses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class StudyGuidePage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public StudyGuidePage(WebDriver driver)
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
		
		// On Study Guild page e.g. http://qa1.boat-ed.com/texas/studyGuide/10104501
	
		@FindBy(css="div[class='study-guide alert alert-block alert-info'] h3")
	    public WebElement About_PageHeader;
		
		@FindBy(css="div[class='study-guide alert alert-block alert-info'] p")
	    public WebElement About_PageHeaderText;
		
		@FindBy(css="div[class='study-guide alert alert-block alert-info'] p:nth-of-type(2) a")
	    public WebElement About_LearnMore;
		
		@FindBy(css="div[class='study-guide alert alert-block alert-info'] p:nth-of-type(2) a:nth-child(2)")
	    public WebElement About_RegisterForTheCourse;
		
		@FindBy(css="div.page-header")
	    public WebElement StudySectionHeaderText;
		
		
		//Click on Unit, Topic, SubTopic links
		
		public void ClickOn_Unit_Topic_SubTopic(int unit, int topic, int subtopic) throws InterruptedException
		{
			//for Unit 
			String unit_css = "#main > div > div.row > div > nav > ul > li:nth-child(" + unit + ") > a";
			
			//for topic
			String topic_css = "#main > div > div.row > div > nav > ul > li.expanded > ul > li:nth-child(" + topic+ ") > a";
			
			//for topic
			String subtopic_css = "#main > div > div.row > div > nav > ul > li.expanded > ul > li.expanded > ul > li:nth-child(" + subtopic + ") > a";
			
			
			//click on Unit
			driver.findElement(By.cssSelector(unit_css)).click();
			Thread.sleep(5000);

			//click on Topic
			driver.findElement(By.cssSelector(topic_css)).click();
			
			Thread.sleep(4000);
			//click on SubTopic
			driver.findElement(By.cssSelector(subtopic_css)).click();
			Thread.sleep(5000);

		}
		
		

		//Get href value for on Unit, Topic, SubTopic links
		
		public String Get_href_for_Unit_Topic_SubTopic(int unit, int topic, int subtopic) throws InterruptedException
		{
			//for Unit 
			String unit_css = "#main > div > div.row > div > nav > ul > li:nth-child(" + unit + ") > a";
			
			//for topic
			String topic_css = "#main > div > div.row > div > nav > ul > li.expanded > ul > li:nth-child(" + topic+ ") > a";
			
			//for topic
			String subtopic_css = "#main > div > div.row > div > nav > ul > li.expanded > ul > li.expanded > ul > li:nth-child(" + subtopic + ") > a";
			
			
			//click on Unit
			driver.findElement(By.cssSelector(unit_css)).click();
			Thread.sleep(4000);
			
			//click on Topic
			driver.findElement(By.cssSelector(topic_css)).click();
			Thread.sleep(4000);
			
			String href = driver.findElement(By.cssSelector(subtopic_css)).getAttribute("href");
			Thread.sleep(4000);
			
			//click on Topic
			driver.findElement(By.cssSelector(topic_css)).click();
			Thread.sleep(4000);

			//click on Unit
			driver.findElement(By.cssSelector(unit_css)).click();
			Thread.sleep(4000);

			return href;
			
		}
		
}
