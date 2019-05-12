package pageFactory.EM;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import utility.Constants;
import utility.JavaHelpers;

public class AgencyHomePage 
{

	WebDriver driver;
	JavaHelpers JH = new JavaHelpers();
	
	public AgencyHomePage(WebDriver driver)
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
	
	
	
		/*@FindBy(css=".stripeTable tr:nth-of-type(2) td:nth-of-type(1) a")
		WebElement Program;*/
		
		@FindBy(css="#page li:nth-of-type(1) div:nth-of-type(1) a")
		public WebElement Program;
		
		//Select Program From List
		public void ClickToSelectProgram()
		{
			Program.click();
		}
	
	
	
		@FindBy(css=".span9>h3")
		WebElement Header;
	
		
		@FindBy(linkText="Search Students")
		public WebElement SearchStudent;
	
		//Search Student Link
		public void ClickOnSearchStudentLink()
		{
			SearchStudent.click();
		}
		
		@FindBy(linkText="Search Instructors")
		WebElement SearchInstructor;
	
		//Search Instructor Link
		public void ClickOnSearchInstructorLink()
		{
			SearchInstructor.click();
		}
		
		
		@FindBy(linkText="Program Settings")
		WebElement ProgramSettings;
	
		//Program Settings Link
		public void ClickOnProgramSettingsLink()
		{
			ProgramSettings.click();
		}
		
		@FindBy(css=".nav.nav-list>li:nth-of-type(7)>a")
		public WebElement EventSearch;
		
		
		
		@FindBy(linkText="Payment")
		WebElement PaymentSettings;
	
		//Payment Link
		public void ClickOnPaymentSettingsLink()
		{
			PaymentSettings.click();
		}

		@FindBy(linkText="Create New Instructor")
		WebElement NewInstructor;
	
		
		//New Instructor Link
		public void ClickOnNewInstructorLink()
		{
			NewInstructor.click();
		}
		
		
		@FindBy(linkText="Event Settings")
		WebElement EventSettings;
	
		//Click on Event Settings link
		public void ClickOnEventSettingsLink()
		{
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", EventSettings);
			EventSettings.click();
		}
		
		//Click on Create Event link		
		@FindBy(linkText="Create")
		WebElement CreateEvent;
	
		public void ClickOnCreateEventLink()
		{
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", CreateEvent);
			CreateEvent.click();
		}
		
		@FindBy(linkText="Manage Program Files")
		WebElement ManageProgramFiles;
	
		//Click on Manage Program Files link
		public void ClickOnManageProgramFilesLink()
		{
			ManageProgramFiles.click();
		}
		
		//Click on Approval link
		
		@FindBy(linkText="Approvals")
		WebElement Approval;
	
		public void ClickOnApprovalLink()
		{
			Approval.click();
		}
		
		//Click on Approval link
		
		@FindBy(linkText="Certificates")
		WebElement Certificate;
			
		public void ClickOnCertificatesLink()
		{
			Certificate.click();
		}
		
		
		@FindBy(linkText="Notifications")
		WebElement Notification;
	
		public void ClickOnNotificationsLink()
		{
			Notification.click();
		}
		
		@FindBy(linkText="Result Sets")
		WebElement Studentform;
	
		public void ClickOnStudentformLink()
		{
			Studentform.click();
		}
		
		@FindBy(linkText="Report Details")
		WebElement ReportDetail;
	
		public void ClickOnReportDetailsLink()
		{
			ReportDetail.click();
		}
		
		//Set Max Wait List in %
		@FindBy(css="#max_wait_lists_for_event")
		WebElement MaxWaitList;
		
		public void EnterMaxWaitList(String maxwaitlist)
		{
			MaxWaitList.clear();
			MaxWaitList.sendKeys(maxwaitlist);
		}
		
		
		//Save Event Settings Changes
		@FindBy(css=".btn.btn-primary.pull-right")
		WebElement SaveChanges;
		
		public void SaveEventSettingChanges()
		{
			SaveChanges.click();
		}
		
		//show / hide Inactive Agencies button
		@FindBy(css="#toggle-agencies")
		public WebElement showOrHideInactiveAgnecies;
		
		@FindBy(css=".span9>h3")
		public WebElement agenciesTitle;
		
		@FindBy(css=" li:nth-child(17) > div.span11 > a")
		public WebElement inactiveMissouri;
}

