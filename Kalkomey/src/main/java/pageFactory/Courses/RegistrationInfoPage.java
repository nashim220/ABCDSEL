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

public class RegistrationInfoPage {
	

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public RegistrationInfoPage(WebDriver driver)
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
		@FindBy(css="#page > div.content.clearfix > div:nth-child(3) > div.span8 > div:nth-child(10) > div > h4")
		public WebElement comment;
		
		@FindBy(css="#create-comment > textarea")
		public WebElement comment_Area;
		
		public void Enter_comment()
		{
			comment_Area.clear();
			comment_Area.sendKeys("ke-Testing");
		}
		
		@FindBy(css="#create-comment-btn")
		public WebElement Add_Comment;
		
		public void Click_Add_Comment()
		{
			Add_Comment.click();
			
		}

}
