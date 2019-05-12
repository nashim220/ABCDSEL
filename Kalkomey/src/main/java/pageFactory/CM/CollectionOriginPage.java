package pageFactory.CM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class CollectionOriginPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public CollectionOriginPage(WebDriver driver)
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
	
	
		@FindBy(css=".span10 h2")
		public WebElement Header;
	
		@FindBy(css=".btn.btn-primary")
    	public WebElement AddRecord;
		
		public void ClickOnAddRecordButton()
		{
			AddRecord.click();
		}
		
		public String GetAllColumnName()
		{
			String column ="";
			for(int i=1; i<=3; i++)
			{
				String css = ".table.table-striped tr th:nth-of-type(" + i + ")";
				column = column + driver.findElement(By.cssSelector(css)).getText(); 
			}
			
			return column;
		}
		
		@FindBy(linkText="Edit")
    	public WebElement EditRecord;
		
		public void ClickOnEditRecordLink()
		{
			EditRecord.click();
		}
		
		@FindBy(linkText="Delete")
    	public WebElement DeleteRecord;
		
		public void ClickOnDeleteRecordLink()
		{
			DeleteRecord.click();
		}
		
		//Add COllection Origin
		
		@FindBy(css="#certification_collection_origin_certification_origin_id")
    	public WebElement Origin;
		
		public void SelectOrigin(String origin)
		{
			new Select(Origin).selectByVisibleText(origin);
		}
		
		@FindBy(css="#certification_collection_origin_certification_collection_id")
    	public WebElement Collection;
		
		public void SelectCollection(String collection)
		{
			new Select(Collection).selectByVisibleText(collection);
		}
		
		@FindBy(css="#certification_collection_origin_certification_collection_key")
    	public WebElement CollectionKey;
		
		public void EnterCollectionKey(String collectionkey)
		{
			CollectionKey.clear();
			CollectionKey.sendKeys(collectionkey);
		}
		
		@FindBy(css="input[name='commit'] ")
    	public WebElement Save;
		
		public void ClickOnSaveButton()
		{
			Save.click();
		}
		
		@FindBy(css="#new_certification_collection_origin a")
    	public WebElement Cancel;
		
		public void ClickOnCancelLink()
		{
			Cancel.click();
		}
		
		@FindBy(css=".alert.alert-error")
    	public WebElement Error;
		
		@FindBy(css="#certification_collection_origin_certification_collection_key + span")
    	public WebElement ErrorDuplicateKey;
		
		@FindBy(css=".alert.alert-success")
    	public WebElement Success;
}
