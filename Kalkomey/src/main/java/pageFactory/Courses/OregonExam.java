package pageFactory.Courses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class OregonExam 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public OregonExam(WebDriver driver)
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
	
		@FindBy(css=".span5>h2")
	    public WebElement ORExamTitle;
		
		@FindBy(css="div.span5 > p")
		public WebElement ExamInfo;
	
		@FindBy(css="p.lead")
	    public WebElement PgTitle;
		
		@FindBy(css=".btn.btn-large.btn-block.btn-success")
	    public WebElement StartChallengeExamButton;
 
		@FindBy(css=".page-header>h1")
	    public WebElement PageHeader;
		
		@FindBy(css="#next_text>a")
	    public WebElement BeginExam;
		
		@FindBy(id="answer_text_a")
		public WebElement Answer11;
		
		
		@FindBy(css="div[class='span8 offset2'] p")
		public WebElement ResumeExamPageTxt;
		
		@FindBy(css="#next_text a")
		public WebElement Conti;
		
		
		public void ClickOnStartExamButton()
		{
			StartChallengeExamButton.click();
		}	
		
		public void ClickOnBeginExamButton()
		{
			BeginExam.click();
		}	
				
}
