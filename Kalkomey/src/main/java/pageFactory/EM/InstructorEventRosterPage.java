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

public class InstructorEventRosterPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public InstructorEventRosterPage(WebDriver driver)
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
	
		@FindBy(css="div[class='page-header page-header-compressed'] h1")
	    public WebElement PageTitle;
		
		//Event info
			
			@FindBy(linkText = "Event Roster")
			public WebElement EventRoster;
			
			@FindBy(linkText = "Enter Results")
			public WebElement EnterResults;
			
			@FindBy(css=".row-fluid.event-detail:nth-of-type(6) .span9")
		    public WebElement EventCapacity;
		
			@FindBy(css="#page > div.content.clearfix > div.row-fluid > div.span9 > div.row-fluid > div.span8 > div:nth-child(1) > div.span9.tooltip-container")
			//@FindBy(css="div[class='span8 event-info tooltip-container'] p")
		    public WebElement EventId;
			
			@FindBy(css="p span")
		    public WebElement PrivateLabel;
		
			@FindBy(css="div[class='span8 event-info tooltip-container'] h2")
		    public WebElement EventName;
			
			
			
			@FindBy(css="i.icon-edit")
		    public WebElement EventEdit;
			
			@FindBy(css="i.icon-trash")
		    public WebElement EventDelete;
			
			@FindBy(css="#page dd:nth-of-type(2) a")
		    public WebElement EventLocation;
			
			@FindBy(css="div.span9 tbody tr td")
		    public WebElement EventTime;
			
			@FindBy(css="#student-toggler")
			WebElement DeleteStudent;
			
					
			
			//Select Student Checkbox
			public void ClickOnSelectEventCheckBox()
			{
				DeleteStudent.click();
			}
			
			
			//Click on first student 
			@FindBy(css=".checkbox.checkbox-sidelong>a")
			WebElement StudentList;			
			
			//Click on first student 
			public void ClickOnSelectFirstStudent()
			{
				StudentList.click();
			}
			
			@FindBy(css=".btn.btn-small.btn-primary.mlt")
			WebElement EditStudent;			
			
			
			public void ClickOnEditStudentButton()
			{
				EditStudent.click();
			}
			
			//View Registration
			@FindBy(css=".popover-title a:nth-of-type(1)")
			WebElement ViewRegistration;			
			
			
			public void ClickOnViewRegistrationButton()
			{
				ViewRegistration.click();
			}
			
			@FindBy(css="#student_action_type")
			WebElement StudentAction;
			
			public void SelectEventAction()
			{
				new Select(StudentAction).selectByVisibleText("Remove from Event");
			}
			
			@FindBy(css="button.btn")
		    public WebElement GoButton;
			
			//Click on Search button
			public void ClickOnGoButton()
			{
				GoButton.click();
			}
			
			//Go to Student View
			@FindBy(css=".inline.actions-page.tooltip-container li:nth-of-type(1) a")
		    WebElement StudentView;
			
			//Click on Search button
			public void ClickOnStudentViewLink()
			{
				StudentView.click();
			}
			
			//Event Status
			@FindBy(css=".status-description")
		    public WebElement EventStatus;
			
			//Unschedule & Remove
			@FindBy(css=".text-error")
		    public WebElement UnscheduleRemove;
			
			public void ClickOnUnscheduleRemoveLink()
			{
				UnscheduleRemove.click();
			}
			
			//Unschedule Button
			@FindBy(css=".btn.btn-danger")
		    public WebElement Unschedule;
			
			public void ClickOnUnscheduleButton()
			{
				Unschedule.click();
			}
			
		//Actions
			
			/*Add Student
			Invite Student
			Add or Remove Instructors
			Upload or Delete Files
			Enter Results*/

			public void SelectAction(String actionname)
			{
				for(int i=1;i<6;i++)
				{
					String cssSelector = "ul[class='nav nav-tabs nav-stacked nav-secondary'] li:nth-of-type(" + i + ") a";
					if(driver.findElement(By.cssSelector(cssSelector)).getText().equals(actionname))
					{
						
						driver.findElement(By.cssSelector(cssSelector)).click();
						break;
					}
				}
			}
			
			
	 // Confirm Delete Event Page
			
			@FindBy(css="a.button")
		    public WebElement Confirm_EventDelete;
			

			@FindBy(css="div.content p")
		    public WebElement Confirm_EventDelete_Message;
			
			
	// Confirm Remove Student
			
			@FindBy(css=".clear a:nth-of-type(1)")
		    public WebElement ConfirmStudentRemove;	
			
	// Return to roster page from invite student
			
			@FindBy(css=".clear a:nth-of-type(2)")
		    public WebElement ReturnToRosterFromInvitation;				

	// Return to roster from result page
			@FindBy(css=".nav.nav-tabs.nav-stacked.nav-secondary li:nth-of-type(4) a")
			public WebElement ReturnToRosterFromResult;			
			
	// return to Roster from Event Admin page			
			@FindBy(css=".nav.nav-tabs.nav-stacked>li:nth-of-type(3)>a")
			public WebElement ReturnToRosterEventAdmin;	
			
	// View Result
			@FindBy(css=".span4.event-admin-quicklinks > ul > li:nth-child(6) > a")
			public WebElement ViewResult;
}


