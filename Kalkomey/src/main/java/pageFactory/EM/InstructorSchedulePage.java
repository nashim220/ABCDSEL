package pageFactory.EM;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class InstructorSchedulePage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	
	public InstructorSchedulePage(WebDriver driver)
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
	
		@FindBy(css="div.stage h2")
	    public WebElement PageTitle;
		
		@FindBy(css="div.stage p")
	    public WebElement InfoText;
		
		@FindBy(css="#calendar-view>a")
	    public WebElement CalenderView;
		
		@FindBy(css="#list-view>a")
	    public WebElement ListView;
		
		@FindBy(css=".icon.icon-chevron-left")
	    public WebElement PreviousMonthIcon;
		
		@FindBy(css=".icon.icon-chevron-right")
	    public WebElement NextMonthIcon;
		
		@FindBy(css="a[title*='Create Event']")
	    public WebElement NewEvent;
		
		
		@FindBy(css="#page > div.content.clearfix > div.row-fluid > div.span9 > div.row-fluid > div.span8 > div:nth-child(3) > div.span9")
		//@FindBy(xpath=".//*[@id='calendar']/div[2]/div[1]/div[6]/div/div[2]/span/a")
	    public WebElement FirstEventName;
	
		/*
		@FindBy(xpath=".//*[@id='calendar']/div[2]/div[1]/div[6]/div/div[2]/div/div[2]")
	    public WebElement FirstEventTime;*/
		
		@FindBy(css="#page > div.content.clearfix > div.row-fluid > div.span9 > div.row-fluid > div.span8 > div:nth-child(4) > div.span9 > div")
		public WebElement FirstEventTime;
		

		@FindBy(xpath="//a[contains(@id,'modal-roster-button')]")
		public WebElement popupRosterButton;
		
		//Event Administration page
		@FindBy(css=".row-fluid:nth-of-type(4) .span9")
	    public WebElement ScheduleTime;
		
		@FindBy(css=".row-fluid:nth-of-type(3) .row-fluid.event-detail:nth-of-type(3) .span9")
	    public WebElement EventName;
		
		@FindBy(css=".span9.tooltip-container")
		public WebElement EventId;
		
		//Delete all Events 
	    public void DeleteAllEvents() throws InterruptedException
	    {
	    	
	    	SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	    	InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
	    	
	    	try 
	    	{
				//Get all Events
				List<WebElement> allEvents = driver.findElements(By.cssSelector("a.kalendar_day_event"));
				ArrayList<String> rosterpages = new ArrayList<String>();
				Thread.sleep(5000);

				for(WebElement tdElement : allEvents) 
				 {
					String temp = tdElement.getAttribute("href");
					rosterpages.add(temp);
				 }
				
				for(int i=0;i<rosterpages.size();i++)
				{
					SeleniumFunc.ToGoToUrl(rosterpages.get(i));
					Thread.sleep(5000);

					if(SeleniumFunc.IsElementPresent("css", "#student-toggler"))
					{
						instructoreventroster.ClickOnSelectEventCheckBox();
						instructoreventroster.SelectEventAction();
						instructoreventroster.ClickOnGoButton();
						Thread.sleep(5000);

						instructoreventroster.ConfirmStudentRemove.click();
						Thread.sleep(5000);

					}
					
					instructoreventroster.EventDelete.click();
					Thread.sleep(5000);

					instructoreventroster.Confirm_EventDelete.click();
					Thread.sleep(5000);
				}
				
			} 
	    	catch (Exception e) 
	    	{
				// TODO Auto-generated catch block
				
			}
	    	
	    }
		
		
	   
	    
	    
		
			
}


