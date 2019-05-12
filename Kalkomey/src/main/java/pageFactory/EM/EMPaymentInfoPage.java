package pageFactory.EM;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;


import utility.Constants;
import utility.JavaHelpers;

public class EMPaymentInfoPage {
	
	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public EMPaymentInfoPage(WebDriver driver)
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
	
	@FindBy(id="cc_first_name")
	public WebElement cardFName;
	
	@FindBy(id="cc_last_name")
	public WebElement cardLName;
	
	@FindBy(id="cc_number")
	public WebElement cardNum;
	
	@FindBy(css="#student_edit_form > button")
	public WebElement Submit;
	
	@FindBy(id="card_expiry_month")
	public WebElement ExpMonth;	
	
	@FindBy(id="card_expiry_year")
	public WebElement Expyear;	
	
	//Select Expire Month
	public void SelectExpireMonthByVisibleText(String VisbleText)
	{
		new Select(ExpMonth).selectByVisibleText(VisbleText);
	}
	
	//Select Expire Year
	public void SelectExpireYearByVisibleText(String VisbleText)
	{
		new Select(Expyear).selectByVisibleText(VisbleText);
	}
	
	
	
}
