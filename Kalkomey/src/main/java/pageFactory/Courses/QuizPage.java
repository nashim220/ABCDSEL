package pageFactory.Courses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class QuizPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public QuizPage(WebDriver driver)
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
	
		@FindBy(css="#next_text >a")
	    WebElement NextButton;
		
		
		@FindBy(css="#prev_text >a")
	    WebElement PrevButton;
		
	
	
	//Clicking Next button
		public void ClickOnNextButton()
		{
			NextButton.click();
		}
		
		

	//Clicking Previous button
		public void ClickOnPrevButton()
		{
			PrevButton.click();
		}
}
