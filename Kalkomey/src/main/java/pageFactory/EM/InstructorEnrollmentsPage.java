package pageFactory.EM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class InstructorEnrollmentsPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public InstructorEnrollmentsPage(WebDriver driver)
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

		@FindBy(css="div.content h2")
	    public WebElement PageTitle;
	
		public String GetTableData(int row, int column)
		{
			String cssSelector = "table.stripeTable tbody tr:nth-of-type(" + (row + 1)+ ") td:nth-of-type(" + column + ")";
			return driver.findElement(By.cssSelector(cssSelector)).getText().trim();
		}
		
		public String SearchEventByIDAndReturnStatus(String eventid)
		{
			String cssSelector;
			String status = null; 
			
			for (int i=1; i<11;i++)
			{
				cssSelector = ".table.table-striped tbody tr:nth-of-type(" + (i)+ ") td:nth-of-type(1)";
				if(driver.findElement(By.cssSelector(cssSelector)).getText().trim().equals(eventid))
				{
					cssSelector = ".table.table-striped tbody tr:nth-of-type(" + (i)+ ") td:nth-of-type(3)";
					status = driver.findElement(By.cssSelector(cssSelector)).getText().trim();
					break;
				}
			}
			
			return status;
			
		}
		
}


