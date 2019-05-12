package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class EditCollectionPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public EditCollectionPage(WebDriver driver)
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
	
	
		@FindBy(css=".page-header h1")
		public WebElement Header;
	
		@FindBy(css="#certification_collection_name")
    	public WebElement CollectionName;
		
		@FindBy(css="#certification_collection_state_cd")
    	public WebElement State;
		
		public void SelectState(String state)
		{
			new Select(State).selectByVisibleText(state);
		}
		
		@FindBy(css="#certification_collection_agency_id")
    	public WebElement Agency;
		
		public void SelectAgency(String agency)
		{
			new Select(Agency).selectByVisibleText(agency);
		}
		
		@FindBy(css="#certification_collection_certification_type_id")
    	public WebElement Type;
		
		public void SelectType(String type)
		{
			new Select(Type).selectByVisibleText(type);
		}
		
		@FindBy(css="#certification_collection_state_restricted_true")
    	public WebElement ResidencyTrue;
		
		public void ClickOnResidencyTrue()
		{
			ResidencyTrue.click();
		}
		
		@FindBy(css="#certification_collection_state_restricted_false")
    	public WebElement ResidencyFalse;
		
		public void ClickOnResidencyFalse()
		{
			ResidencyFalse.click();
		}
		
		
		@FindBy(css="#certification_collection_name_on_card")
    	public WebElement NameOnCard;
		
		public void EnterNameOnCard(String name)
		{
			NameOnCard.clear();
			NameOnCard.sendKeys(name);
		}
		
		@FindBy(css="#certification_collection_permanent_card_price")
    	public WebElement CardPrice;
		
		public void EnterPrice(String price)
		{
			CardPrice.clear();
			CardPrice.sendKeys(price);
		}
		
		@FindBy(css=".btn.btn-primary")
    	public WebElement Save;
		
		public void ClickOnSaveButton()
		{
			Save.click();
		}

		@FindBy(css=".alert.alert-success")
    	public WebElement Success;
		
		@FindBy(css=".alert.alert-error.fade.in")
    	public WebElement Error;
		
}
