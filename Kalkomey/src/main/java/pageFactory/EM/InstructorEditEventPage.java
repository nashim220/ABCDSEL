package pageFactory.EM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Constants;
import utility.JavaHelpers;

public class InstructorEditEventPage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public InstructorEditEventPage(WebDriver driver)
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
		
		@FindBy(css="div.content p")
	    public WebElement InfoText;
		
		
		//Calender
			
			//Select date
		
			public void SelectDate(String date)
			{
				List<WebElement> dates = driver.findElements(By.cssSelector("a.smallCalendarDayLink"));
				
				for(WebElement temp : dates) 
				 {
					if(temp.getText().equals(date))
					{
						temp.click();
						break;
					}
				 }
			
			}
		

			@FindBy(css="#datePicker div:nth-of-type(3) #datePicker_hour")
		    public WebElement StartDateHour;
			
			@FindBy(css="#datePicker div:nth-of-type(3) #datePicker_minute")
		    public WebElement StartDateMinute;
			
			@FindBy(css="#datePicker div:nth-of-type(3) #datePicker_suffix")
		    public WebElement StartDateSuffix;
			
			
			@FindBy(css="#datePicker div:nth-of-type(4) #datePicker_end_hour")
		    public WebElement EndDateHour;
			
			@FindBy(css="#datePicker div:nth-of-type(4) #datePicker_end_minute")
		    public WebElement EndDateMinute;
			
			@FindBy(css="#datePicker div:nth-of-type(4) #datePicker_end_suffix")
		    public WebElement EndDateSuffix;
			
			
			public void SelectStartDate(String hour, String minute, String suffix)
			{
				new Select(StartDateHour).selectByVisibleText(hour);
				new Select(StartDateMinute).selectByVisibleText(minute);
				new Select(StartDateSuffix).selectByVisibleText(suffix);
			}
			
			public void SelectEndDate(String hour, String minute, String suffix)
			{
				new Select(EndDateHour).selectByVisibleText(hour);
				new Select(EndDateMinute).selectByVisibleText(minute);
				new Select(EndDateSuffix).selectByVisibleText(suffix);
			}
			
			//Current Schedule
			@FindBy(css="label[for='datePicker_display']")
		    public WebElement CurrentScheduleLabel;
			
			public String CurrentScheduleGetDateInfo(int i)
			{
				if(i==1)
				{
					return driver.findElement(By.cssSelector("#datePicker_display span:nth-of-type(1)")).getText();
				}
				else if(i==2)
				{
					return driver.findElement(By.cssSelector("#datePicker_display span:nth-of-type(3)")).getText();
				}
				
				else if(i==3)
				{
					return driver.findElement(By.cssSelector("#datePicker_display span:nth-of-type(5)")).getText();
				}
				else
				{
					return null;
				}	
			 }
			
			public String CurrentScheduleRemoveDate(int i)
			{
				String cssSelector = "#datePicker_display a:nth-of-type(" + i + ")";
				return driver.findElement(By.cssSelector(cssSelector)).getText();

			 }
			
	
		@FindBy(css="div a.button")
	    public WebElement AddEventDate;
		
		
		
		@FindBy(id="event_name_selector")
	    public WebElement EventName;
		
		@FindBy(id="event_name_input")
	    public WebElement EventNameTextbox;
		
		@FindBy(id="location_search")
	    public WebElement Location;
		
		@FindBy(css="#autocomplete_choices ul li")
	    public WebElement Location_FirstChoice;
		
		@FindBy(id="student_capacity")
	    public WebElement EventCapacity;

		@FindBy(id="waitlist_capacity")
	    public WebElement WaitListCapacity;
		
		@FindBy(id="event_instructions")
	    public WebElement SpecialInstructions;
		
		@FindBy(css="div.clear a.button")
	    public WebElement SaveChanges;
		
		
		//Options section
		
			@FindBy(css="label[for='event_options']")
		    public WebElement OptionsLabel;
			
			//This is a public event.
				@FindBy(id="public_event")
			    public WebElement PrivacyLabel;
				
				@FindBy(id="event_privacy_value")
			    public WebElement PrivacyValue;
				
				@FindBy(id="event_select_public")
			    public WebElement PrivacySetAsPublic;
				
				@FindBy(id="event_select_private")
			    public WebElement PrivacySetAsPrivate;
		
			//This event will be visible to students: 5/22/2015 - 5/22/2015.	
				@FindBy(id="event_vis_option_label")
			    public WebElement EventVisibleLabel;
				
				@FindBy(css="#event_vis_option_label a")
			    public WebElement EventVisibleLink;
				
				@FindBy(id="event_vis_select_never")
			    public WebElement EventVisibleSetDoNotDisplay;
				
			//Students can register for this event: 5/22/2015 - 5/21/2015.
				@FindBy(id="event_reg_option_label")
			    public WebElement EventRegisterLabel;
				
				@FindBy(css="#event_reg_option_label a")
			    public WebElement EventRegisterLink;
				
				@FindBy(id="event_reg_select_never")
			    public WebElement EventRegisterSetDoNotRegister;
				
			//Students can cancel registrations: 5/22/2015 - 5/21/2015.
				@FindBy(id="event_cx_option_label")
			    public WebElement EventCancelLabel;
				
				@FindBy(css="#event_cx_option_label a")
			    public WebElement EventCancelLink;
				
				@FindBy(id="event_cx_select_never")
			    public WebElement EventCancelSetDoNotCancel;
				
			//Notifications are disabled.
				@FindBy(id="notify_mode_label")
			    public WebElement EventNotificationLabel;
				
				@FindBy(css="#notify_mode_label a")
			    public WebElement EventNotificationLink;
				
				@FindBy(name="notify_mode")
			    public WebElement EventNotificationNot;
				
			//Cancellation Notifications are disabled.
				@FindBy(id="cancel_notify_mode_label")
			    public WebElement EventCancelNotificationLabel;
				
				@FindBy(css="#cancel_notify_mode_label a")
			    public WebElement EventCancelNotificationLink;
				
				@FindBy(name="cancel_notify_mode")
			    public WebElement EventCancelNotificationNot;
				
			//Wait list is enabled.
				@FindBy(id="wait_list_mode_label")
			    public WebElement EventWaitListLabel;
				
				@FindBy(css="#wait_list_mode_label a")
			    public WebElement EventWaitListLink;
				
				@FindBy(name="event_wait_list_mode")
			    public WebElement EventWaitListNot;
				
				
}


