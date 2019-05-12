package pageFactory.EM;

import java.util.jar.Attributes.Name;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class InstructorHomePage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public InstructorHomePage(WebDriver driver)
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
	
		@FindBy(css="div[class='content clearfix'] div.row-fluid p:nth-of-type(1)")
	    public WebElement WelcomeTextP1;
		
		@FindBy(css="div[class='content clearfix'] div.row-fluid p:nth-of-type(2)")
	    public WebElement WelcomeTextP2;

		@FindBy(css="div.header p")
	    public WebElement LoggedInAsText;
		
		@FindBy(css="div.header li:nth-of-type(2) a")
	    public WebElement LogOut;
		
		
		//Menu
		
		@FindBy(css="div[class='nav-collapse collapse'] ul li:nth-of-type(1) a")
	    public WebElement Menu_Home;
		
		@FindBy(css="div[class='nav-collapse collapse'] ul li:nth-of-type(2) a")
	    public WebElement Menu_Profile;
		
		@FindBy(css="div[class='nav-collapse collapse'] ul li:nth-of-type(3) a")
	    public WebElement Menu_Events;
		
		@FindBy(css="div[class='nav-collapse collapse'] ul li:nth-of-type(4) a")
	    public WebElement Menu_Locations;
		
		@FindBy(css="div[class='nav-collapse collapse'] ul li:nth-of-type(5) a")
	    public WebElement Menu_Enrollments;
		
		@FindBy(css="div[class='nav-collapse collapse'] ul li:nth-of-type(6) a")
	    public WebElement Menu_Results;
		
		@FindBy(css=".smallNextMonthLink")
		WebElement SelectNextMonth;
		
		@FindBy(linkText = "Demo Agency")
		//@FindBy(css="#page > div.content.clearfix > div.row-fluid > div.span9 > ol > li:nth-child(35) > div.span11 > a")
		WebElement DemoAgency;
		public void Click_DemoAgency()
		{
			DemoAgency.click();
		}
		
		@FindBy(linkText = "Users")
		//@FindBy(css="#page > div.content.clearfix > div.row-fluid > div.span3 > ul > li:nth-child(5) > a")
		WebElement User;
		public void Click_User()
		{
			User.click();
		}
		
		@FindBy(id="search-type-name")
		WebElement Enter_Search_data;
		
		public void Enter_Search_Input()
		{
			Enter_Search_data.clear();
			Enter_Search_data.sendKeys("testing");
		}
		
		public void Enter_Search_Input2()
		{
			Enter_Search_data.clear();
			Enter_Search_data.sendKeys("sarfaraz");
		}
		
		public void Enter_Search_Input_LeadingSpace()
		{
			Enter_Search_data.clear();
			Enter_Search_data.sendKeys(" sarfaraz");
		}
		
		public void Enter_Search_Input_TrailingSpace()
		{
			Enter_Search_data.clear();
			Enter_Search_data.sendKeys("sarfaraz ");
		}
		
		public void Enter_Search_Student()
		{
			Enter_Search_data.clear();
			Enter_Search_data.sendKeys("blah, blah");
		}
		
		@FindBy(css="#search-button")
		WebElement Search2;
		public void Click_SearchNew()
		{
			Search2.click();
		}
		
		@FindBy(css="#search_form > input.btn")
		WebElement search;
		public void Click_Search()
		{
			search.click();
		}
		
		
		@FindBy(partialLinkText = "Demo Hunter Education Field Day")
		//@FindBy(css="#page > div.content.clearfix > div.row-fluid > div.span9 > ol:nth-child(4) > li:nth-child(1) > div.span9 > a")
		WebElement DemoHunterEdu;
		public void Click_Demo_Hunter()
		{
			DemoHunterEdu.click();
		}
		
		
		@FindBy(linkText = "Search Instructors")
		
		WebElement Instructor;
		public void Click_Search_Instructor()
		{
			Instructor.click();
		}
		
		
		@FindBy(linkText = "Search Students")
		
		WebElement SearchStudent;
		public void Click_Search_Student()
		{
			SearchStudent.click();
		}
		
		
		
		public void GoToNextMonth()
		{
			SelectNextMonth.click();
		
		}
		
		
		@FindBy(css="li.active>a")
		public WebElement SentInvitationsLink;
		
		public void GoToSentInvitationsLink()
		{
			SentInvitationsLink.click();
		
		}
		
		//Instructor Actions 
		
		@FindBy(css="#instructor-actions li:nth-of-type(1) a")
	    public WebElement Action_ManageMyProfile;
		
		@FindBy(css="#instructor-actions li:nth-of-type(2) a")
	    public WebElement Action_MyEventSchedule;
		
		@FindBy(css=".span7 p a")
	    public WebElement VisitHomePage;
				
		@FindBy(css="#instructor-actions li:nth-of-type(3) a")
	    public WebElement Action_ProgramLocations;
		
		@FindBy(css="#instructor-actions li:nth-of-type(4) a")
	    public WebElement Action_MyEventEnrollments;
		
		@FindBy(css="#instructor-actions li:nth-of-type(5) a")
	    public WebElement Action_MyEventResults;
}
