package pageFactory.EM;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class EditStudentPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public EditStudentPage(WebDriver driver)
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
	
		@FindBy(css="#student_first_name")
		public WebElement FirstName;
	
		//Edit First Name
		public void EditFirstName(String firstname)
		{
			FirstName.clear();
			FirstName.sendKeys(firstname);
		}	
		
		@FindBy(css="#student_last_name")
	    public WebElement LastName;
	
		//Edit First Name
		public void EditLastName(String lastname)
		{
			LastName.clear();
			LastName.sendKeys(lastname);
		}	
			
		
		@FindBy(css="#next_anchor")
		WebElement Submit;
	
	
	//Click on Edit First Record
		public void ClickOnSubmitButton()
		{
			Submit.click();
		}
		
		
		@FindBy(css="#page div:nth-of-type(3) a:nth-of-type(2)")
		WebElement Edit;
	
	
	//Click on Edit First Record
		public void ClickOnEditButton()
		{
			Edit.click();
		}
		
		@FindBy(id="Registration_first_name")
		public WebElement RegFirstName;
	
		//Edit First Name
		public void UpdateFirstName(String firstname)
		{
			RegFirstName.clear();
			RegFirstName.sendKeys(firstname);
		}	
		
		@FindBy(id="Registration_last_name")
	    public WebElement RegLastName;
	
		//Edit First Name
		public void UpdateLastName(String lastname)
		{
			RegLastName.clear();
			RegLastName.sendKeys(lastname);
		}	
	
		
}


