package pageFactory.EM;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class EditInstructorPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public EditInstructorPage(WebDriver driver)
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
	
		@FindBy(css="#first_name")
		public WebElement FirstName;
	
		//Edit First Name
		public void EditFirstName(String firstname)
		{
			FirstName.clear();
			FirstName.sendKeys(firstname);
		}	
		
		@FindBy(css="#last_name")
	    public WebElement LastName;
	
		//Edit First Name
		public void EditLastName(String lastname)
		{
			LastName.clear();
			LastName.sendKeys(lastname);
		}	
			
		
	
		@FindBy(css=".btn.btn-success")
		WebElement SaveChanges;
		
		public void ClickOnSaveChangesButton()
		{
			SaveChanges.click();
		}
		
		
		@FindBy(css="#page div:nth-of-type(2) a:nth-of-type(2)")
		WebElement Edit;
	
	
	//Click on Edit First Record
		public void ClickOnEditButton()
		{
			Edit.click();
		}
		
		@FindBy(css=".nav.nav-list>li:nth-of-type(2)>a")
		WebElement EditLink;
	
	
	//Click on Edit Link
		public void ClickOnEditLink()
		{
			EditLink.click();
		}
}


