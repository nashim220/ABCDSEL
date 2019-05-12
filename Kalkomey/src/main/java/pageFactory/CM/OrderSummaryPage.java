 package pageFactory.CM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class OrderSummaryPage 
{
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public OrderSummaryPage(WebDriver driver)
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
	
	@FindBy(css=".page-header hgroup h1")
	public WebElement Header;
	
	@FindBy(css=".alert.alert-success")
	public WebElement Success;
	
	@FindBy(css=".btn.btn-small.btn-success.pull-right")
	public WebElement PDF;
	
	
	@FindBy(css=".widget-content li:nth-of-type(1) a")
	public WebElement ReturnToRecord;
	
	@FindBy(css=".widget-content li:nth-of-type(2) a")
	public WebElement SearchAnother;
	
	@FindBy(css="#order-details .widget-content")
	public WebElement OrderDetails;
}
