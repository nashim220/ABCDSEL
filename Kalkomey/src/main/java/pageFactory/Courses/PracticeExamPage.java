package pageFactory.Courses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class PracticeExamPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public PracticeExamPage(WebDriver driver)
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
	
		@FindBy(css="div.page-header h1")
	    public WebElement PageTitle;
		
		@FindBy(css="div[class='span8 offset2'] p")
		public WebElement ExamRuleTitle;
	
		@FindBy(css="div[class='span8 offset2'] ul")
		public WebElement ExamRules;
		
		@FindBy(css="#prev_text a")
		public WebElement Previous;
		
		@FindBy(css="#main div:nth-of-type(2) div div:nth-of-type(2) a")
		public WebElement TakePracticeExam;
		
		@FindBy(css="#middle_text>a")
		public WebElement FinalExam;
			
		@FindBy(css="#prev_text a")
		public WebElement QuitPractice;
			
		@FindBy(css="#next_text a")
		public WebElement Next;
		
		@FindBy(id="answer_text_a")
		public WebElement Answer1;
		
		@FindBy(css="div[class='alert alert-block alert-info']")
		public WebElement ResumeExamPageText;
		
}
