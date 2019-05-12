package pageFactory.EM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class PaymentSettingsPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public PaymentSettingsPage(WebDriver driver)
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
	
	
		@FindBy(css=".span9 h3")
		public WebElement Title;		
		
		@FindBy(css="#payment-mode-select")
		public WebElement PaymentMode;		
	
		public void SelectPaymentMode(String visibletext)
		{
			new Select(PaymentMode).selectByVisibleText(visibletext);
		}
		
		
		@FindBy(id="event_product_selector")
		public WebElement Select_Price;
		
		public void SelectPaymentPrice(String visibletext)
		{
			new Select(Select_Price).selectByVisibleText(visibletext);
		}
			
		
		@FindBy(id="product_fee[price]")
		public WebElement Price;		
		
		public void EnterPrice(String price)
		{
			Price.clear();
			Price.sendKeys(price);
		}
				
		
		@FindBy(id="product_fee[fee_id]")
		public WebElement FeeCode;		
	
		public void SelectFeeCode(String visibletext)
		{
			new Select(FeeCode).selectByVisibleText(visibletext);
		}
		
		
		@FindBy(id="product_fee[quickbooks_account_id]")
		public WebElement DepositeAccount;		
	
		public void SelectDepositeAccount(String visibletext)
		{
			new Select(DepositeAccount).selectByVisibleText(visibletext);
		}		
		
		@FindBy(css=".btn.btn-primary")
		public WebElement SaveChanges;
		
		public void ClickOnSaveChanges() throws InterruptedException
		{
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", SaveChanges);	
			//Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("javascript:window.scrollBy(250,350)");
			SaveChanges.click();
		}
		
		@FindBy(css=".alert-flash.alert.alert-success")
		public WebElement Success;
		
		
		@FindBy(css=".alert-flash.alert.alert-danger")
		public WebElement Validation;
		
		@FindBy(id="payment_product_code")
		public WebElement ProductCode;		
	
		public void SelectProductCode(String visibletext)
		{
			new Select(ProductCode).selectByVisibleText(visibletext);
		}
}
