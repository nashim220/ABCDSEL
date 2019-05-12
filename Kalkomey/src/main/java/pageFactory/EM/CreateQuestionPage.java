package pageFactory.EM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class CreateQuestionPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public CreateQuestionPage(WebDriver driver)
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
	
	
	
          @FindBy(css="div.span3 > ul > li:nth-child(1) > a")	
          public WebElement ManageDetails;
          
          public void ClickOnManageDetails()
          {
        	  ManageDetails.click();
          }
	
		@FindBy(css=".content p a")
		WebElement CreateQuestionLink;
	
		//Click on Search button
		public void ClickOnCreateQuestionLink()
		{
			CreateQuestionLink.click();
		}
		
		
		@FindBy(css="#target")
		WebElement Target;

		
		public void SelectTarget(String visibletext)
		{
			new Select(Target).selectByVisibleText(visibletext);			
		}
		
			
		@FindBy(css="#question_name")
		WebElement QuestionName;

		
		public void EnterQuestionName(String quename)
		{
			QuestionName.clear();
			QuestionName.sendKeys(quename);
		}
		
		@FindBy(css="#question_key")
		WebElement QuestionKey;

		
		public void EnterQuestionKey(String quekey)
		{
			QuestionKey.clear();
			QuestionKey.sendKeys(quekey);
		}
		
		@FindBy(css="#question_desc")
		WebElement QuestionDescri;

		
		public void EnterQuestionDescri(String quedescri)
		{
			QuestionDescri.clear();
			QuestionDescri.sendKeys(quedescri);
		}
		
		@FindBy(css="#field_type")
		WebElement FieldType;

		
		public void SelectFieldType(String visibletext)
		{
			new Select(FieldType).selectByVisibleText(visibletext);			
		}
		
		@FindBy(css=".btn.btn-success.pull-right")
		//@FindBy(css=".button span")
		WebElement CreateQuestion;
	
		//Click on Search button
		public void ClickOnCreateQuestionButton()
		{
			CreateQuestion.click();
		}
		
}
