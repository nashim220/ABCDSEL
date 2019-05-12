package products.EM;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod; 
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.EM.AgencyHomePage;
import pageFactory.EM.EditInstructorPage;
import pageFactory.EM.ErrorPage;
import pageFactory.EM.InstructorCreateEventPage;
import pageFactory.EM.InstructorEventRosterPage;
import pageFactory.EM.InstructorLocationPage;
import pageFactory.EM.InstructorManagePage;
import pageFactory.EM.InstructorSchedulePage;
import pageFactory.EM.InstructorSearchPage;
import pageFactory.EM.InstructorSendMessagePage;
import pageFactory.EM.LoginPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class ManageInstructorTest 
{
	private WebDriver driver;
	private Browser b = new Browser();
	JavaHelpers JH = new JavaHelpers();
	ErrorPage errorpage = new ErrorPage(driver);

	
	@Parameters({ "browser", "environment", "os" })
	@BeforeMethod
	public void setUp(String browser, String environment , String OS) throws Exception 
	{		
		driver= b.setUp(browser, environment, OS);
 		
	}  

	@AfterMethod
	public void teardown() throws Exception
	{
		b.tearDown();
	}
	
	
	
	/* Test 1: 
	 * Verify User can assign instructor for an event
	*/ 
	
	@Test
	private void AssignInstructorToEvent() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 :  Verify User can assign instructor for an event"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 :  Verify User can assign instructor for an event"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		InstructorManagePage instructormanage = new InstructorManagePage(driver);
		
		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(4000);

			//Navigate to 'My Event Schedule' page and delete all Events
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//instructorschedule.DeleteAllEvents();
			 
			
		System.out.println("Step 2 : Navigate to Create Event page via Location page ");
		Reporter.log("Step 2 : Navigate to Create Event page via Location page  "); 
		
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			instructorlocations.SelectProgram(2);	
			Thread.sleep(4000);

			instructorlocations.ScheduleAnEvent.click();	
			Thread.sleep(5000);
				
		
		System.out.println("Step 3 : On Create Event page , entering all required details and click on 'Create Event' button");
		Reporter.log("Step 3 : On Create Event page , entering all required detailsb and click on 'Create Event' button"); 
		
		
			instructorcreateevent.SelectProgram();
			Thread.sleep(2000);
				
			instructorcreateevent.Location.clear();
			instructorcreateevent.Location.sendKeys("Demo Agency Location");
			Thread.sleep(5000);
			instructorcreateevent.Location_FirstChoice.click();
			Thread.sleep(2000);
			
			//instructorcreateevent.GoToNextMonth();
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
				
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
				
			instructorcreateevent.CreateEvent.click();
			Thread.sleep(4000);
			
			String URL = driver.getCurrentUrl();
				
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
					
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//Thread.sleep(4000);

			//Verifying Event Time
			//String expectedtext = "6:30 pm";
			String expectedtext = "06:30 PM to 07:30 PM";
			String actualtext = instructorschedule.FirstEventTime.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Event is added with correct Time");
				Reporter.log("Success !! Event is added with correct Time");
			
			}
			else
			{
				
				System.out.println("Failure !! Event is added with incorrect Time" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Time" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			//Verifying Event Name
			expectedtext = "Demo Hunter";
			actualtext = instructorschedule.FirstEventName.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Event is added with correct Name");
				Reporter.log("Success !! Event is added with correct Name");
			
			}
			else
			{
			
				System.out.println("Failure !! Event is added with incorrect Name" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Name" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
				
			String EventID = instructoreventroster.EventId.getText().trim();
			
			//instructorschedule.FirstEventName.click();
			//Thread.sleep(4000);
			
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");
			Thread.sleep(2000);
			
			instructoreventroster.SelectAction("Add or Remove Instructors");
			Thread.sleep(5000);
			
			
			//Verify Manage Instructor Header
			expectedtext = "Manage Instructors";
			actualtext = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Page header is correct");
				Reporter.log("Success !! Page header is correct");
			
			}
			else
			{
			
				System.out.println("Failure !! Page header is NOT correct" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Page header is NOT correct" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
				
				
			
			//Verify Presence of Current Instructor Section
			expectedtext = "Current Instructors";
			actualtext = instructormanage.CurrentInstructors.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Current Instructors section is present with correct header");
				Reporter.log("Success !! Current Instructors section is present with correct header");
			
			}
			else
			{
			
				System.out.println("Failure !! Current Instructors section is NOT present" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Current Instructors section is NOT present" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
		
			}
			
			
			//Verify Presence of Favorite Instructor Section
			expectedtext = "My Favorites";
			actualtext = instructormanage.MyFavorites.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! My Favorites Instructors section is present with correct header");
				Reporter.log("Success !! My Favorites Instructors section is present with correct header");
			
			}
			else
			{
			
				System.out.println("Failure !! My Favorites Instructors section is NOT present" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! My Favorites Instructors section is NOT present" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			
			//Verify Presence of Available Instructors Section
			expectedtext = "Available Instructors";
			actualtext = instructormanage.AvailableInstructors.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Available Instructors section is present with correct header");
				Reporter.log("Success !! Available Instructors section is present with correct header");
			
			}
			else
			{
			
				System.out.println("Failure !! Available Instructors section is NOT present" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Available Instructors section is NOT present" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			
			//On Manage Instructor page, Searching for 'Available Instructors' and adding as Assistant Instructor
			instructormanage.SelectSearchTypeByUsername();
			instructormanage.SearchBox.sendKeys(Constants.EM_DemoAdmin_Username);
			instructormanage.SearchButton.click();
			Thread.sleep(5000);
			instructormanage.Search_FirstResultCheckbox.click();
			Thread.sleep(4000);

			instructormanage.SelectActionAndGO("Assign Instructors");
			
			Thread.sleep(5000);

			//Verify Instructor is assigned
			
			expectedtext = "UpdatedDetails, EditInstructorDetails";
			actualtext = SeleniumFunc.GetElementText("css", "#instructor_dissociate_form tr:nth-of-type(2) td span");
			
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Instructor is assigned properly");
				Reporter.log("Success !! Instructor is assigned properly");
			
			}
			else
			{
			
				System.out.println("Failure !! Instructor is NOT assigned properly" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Instructor NOT is assigned properly" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
		
			}
			
			
			/*
			try {
				//Going to Event Roster page and removing student and deleting  event 
				//so new event can be added with same test data
				Thread.sleep(2000);
				instructormanage.ClickOnBackButton();
				Thread.sleep(4000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(4000);

				instructoreventroster.Confirm_EventDelete.click();
				Thread.sleep(5000);
			} catch (Exception e) {
				
			}*/
			
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(4000);
			
			SeleniumFunc.ClickOnElement("linkText", "Delete Event");
			
			Thread.sleep(5000);
			
			
			SeleniumFunc.ClickOnElement("linkText", "Delete Event");

			Thread.sleep(2000);

			System.out.println("Event: "+EventID+" Deleted Successfully!!");
		
		/*
		 * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 */
	 	if(AssertFailedCount>0)	
		{
			
			//Marking this test as Failed
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
		
	}


	
	/* Test 2: 
	 * Verify user can add instructor under favorite
	*/ 
	
	@Test
	private void AddInstructorToFavorite() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify user can add instructor under favorite"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify user can add instructor under favorite"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		InstructorManagePage instructormanage = new InstructorManagePage(driver);
		
		
		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(4000);

			//Navigate to 'My Event Schedule' page and delete all Events
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//instructorschedule.DeleteAllEvents();
			 
			
		System.out.println("Step 2 : Navigate to Create Event page via Location page ");
		Reporter.log("Step 2 : Navigate to Create Event page via Location page  "); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			instructorlocations.SelectProgram(2);	
			Thread.sleep(4000);

			instructorlocations.ScheduleAnEvent.click();	
			Thread.sleep(5000);
				
		
		System.out.println("Step 3 : On Create Event page , entering all required details and click on 'Create Event' button");
		Reporter.log("Step 3 : On Create Event page , entering all required detailsb and click on 'Create Event' button"); 
		
			instructorcreateevent.SelectProgram();
			Thread.sleep(2000);
				
			instructorcreateevent.Location.clear();
			instructorcreateevent.Location.sendKeys("Demo Agency Location");
			Thread.sleep(5000);
			instructorcreateevent.Location_FirstChoice.click();
			Thread.sleep(2000);
			
			//instructorcreateevent.GoToNextMonth();
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
				
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
				
			instructorcreateevent.CreateEvent.click();
			Thread.sleep(4000);
			String URL = driver.getCurrentUrl();
				
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
					
		
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//Thread.sleep(4000);

			//Verifying Event Time
			//String expectedtext = "6:30 pm";
			String expectedtext = "06:30 PM to 07:30 PM";
			String actualtext = instructorschedule.FirstEventTime.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Event is added with correct Time");
				Reporter.log("Success !! Event is added with correct Time");
		
			}
			else
			{
			
				System.out.println("Failure !! Event is added with incorrect Time" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Time" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
		
			}
			
			//Verifying Event Name
			expectedtext = "Demo Hunter";
			actualtext = instructorschedule.FirstEventName.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Event is added with correct Name");
				Reporter.log("Success !! Event is added with correct Name");
			
			}
			else
			{
			
				System.out.println("Failure !! Event is added with incorrect Name" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Name" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
				
			String EventID = instructoreventroster.EventId.getText().trim();
			Thread.sleep(4000);
			//instructorschedule.FirstEventName.click();
			//Thread.sleep(4000);

			SeleniumFunc.ClickOnElement("linkText", "Event Roster");
			Thread.sleep(2000);
			instructoreventroster.SelectAction("Add or Remove Instructors");
			Thread.sleep(5000);
			
			
			//Verify Manage Instructor Header
			expectedtext = "Manage Instructors";
			actualtext = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Page header is correct");
				Reporter.log("Success !! Page header is correct");
			
			}
			else
			{
			
				System.out.println("Failure !! Page header is NOT correct" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Page header is NOT correct" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
				
				
			
			//Verify Presence of Current Instructor Section
			expectedtext = "Current Instructors";
			actualtext = instructormanage.CurrentInstructors.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Current Instructors section is present with correct header");
				Reporter.log("Success !! Current Instructors section is present with correct header");
			
			}
			else
			{
			
				System.out.println("Failure !! Current Instructors section is NOT present" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Current Instructors section is NOT present" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			
			//Verify Presence of Favorite Instructor Section
			expectedtext = "My Favorites";
			actualtext = instructormanage.MyFavorites.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! My Favorites Instructors section is present with correct header");
				Reporter.log("Success !! My Favorites Instructors section is present with correct header");
			
			}
			else
			{
			
				System.out.println("Failure !! My Favorites Instructors section is NOT present" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! My Favorites Instructors section is NOT present" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			
			//Verify Presence of Available Instructors Section
			expectedtext = "Available Instructors";
			actualtext = instructormanage.AvailableInstructors.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Available Instructors section is present with correct header");
				Reporter.log("Success !! Available Instructors section is present with correct header");
			
			}
			else
			{
			
				System.out.println("Failure !! Available Instructors section is NOT present" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Available Instructors section is NOT present" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			
			//On Manage Instructor page, Searching for 'Available Instructors' and adding as Assistant Instructor
			instructormanage.SelectSearchTypeByUsername();
			instructormanage.SearchBox.sendKeys(Constants.EM_DemoAdmin_Username);
			instructormanage.SearchButton.click();
			Thread.sleep(5000);
			instructormanage.Search_FirstResultCheckbox.click();
			Thread.sleep(4000);

			instructormanage.SelectActionAndGO("Add to Favorites");
			Thread.sleep(4000);

			
			//Verify Instructor is assigned
			expectedtext = "Testing, Automation";
			actualtext = SeleniumFunc.GetElementText("css", "#instructor_favorites_form span:nth-of-type(1)");
			
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Instructor is added to favorite");
				Reporter.log("Success !! Instructor is added to favorite");
			
			}
			else
			{
			
				System.out.println("Failure !! Instructor is NOT added to favorite" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Instructor is NOT added to favorite" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
		
			}

			
	
			
			/*
			try {
				//Going to Event Roster page and removing student and deleting  event 
				//so new event can be added with same test data
				Thread.sleep(2000);
				instructormanage.ClickOnBackButton();
				Thread.sleep(4000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(4000);

				instructoreventroster.Confirm_EventDelete.click();
				Thread.sleep(5000);
			} catch (Exception e) {
				
			}*/
			
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(5000);
			
			SeleniumFunc.ClickOnElement("linkText", "Delete Event");
			
			Thread.sleep(2000);
			
			
			SeleniumFunc.ClickOnElement("linkText", "Delete Event");

			Thread.sleep(2000);

			System.out.println("Event: "+EventID+" Deleted Successfully!!");
		
		/*
		 * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 */
	 	if(AssertFailedCount>0)	
		{
	 		
			//Marking this test as Failed
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
		
	}


	
	/* Test 3: 
	 * Verify user can Send an email to instructor
	*/ 
	
	@Test
	private void SendEmailToInstructor() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify user can Send an email to instructor"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify user can Send an email to instructor"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		InstructorManagePage instructormanage = new InstructorManagePage(driver);
		InstructorSendMessagePage instructorsendmsg = new InstructorSendMessagePage(driver);

		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(4000);

			//Navigate to 'My Event Schedule' page and delete all Events
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//instructorschedule.DeleteAllEvents();
			
		System.out.println("Step 2 : Navigate to Create Event page via Location page ");
		Reporter.log("Step 2 : Navigate to Create Event page via Location page  "); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			instructorlocations.SelectProgram(2);	
			Thread.sleep(4000);

			instructorlocations.ScheduleAnEvent.click();	
			Thread.sleep(5000);
				
		
		System.out.println("Step 3 : On Create Event page , entering all required details and click on 'Create Event' button");
		Reporter.log("Step 3 : On Create Event page , entering all required detailsb and click on 'Create Event' button"); 
		
			instructorcreateevent.SelectProgram();
			Thread.sleep(2000);
				
			instructorcreateevent.Location.clear();
			instructorcreateevent.Location.sendKeys("Demo Agency Location");
			Thread.sleep(5000);
			instructorcreateevent.Location_FirstChoice.click();
			Thread.sleep(2000);
			
			//instructorcreateevent.GoToNextMonth();
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
				
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
				
			instructorcreateevent.CreateEvent.click();
			Thread.sleep(4000);
			String URL = driver.getCurrentUrl();
				
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
					
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
		
			//Verifying Event Time
			//String expectedtext = "6:30 pm";
			String expectedtext = "06:30 PM to 07:30 PM";
			String actualtext = instructorschedule.FirstEventTime.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Event is added with correct Time");
				Reporter.log("Success !! Event is added with correct Time");
			
			}
			else
			{
			
				System.out.println("Failure !! Event is added with incorrect Time" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Time" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			//Verifying Event Name
			expectedtext = "Demo Hunter";
			actualtext = instructorschedule.FirstEventName.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Event is added with correct Name");
				Reporter.log("Success !! Event is added with correct Name");
			
			}
			else
			{
			
				System.out.println("Failure !! Event is added with incorrect Name" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Name" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
				
			String EventID = instructoreventroster.EventId.getText().trim();
			//instructorschedule.FirstEventName.click();
			//Thread.sleep(4000);
			
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");
			Thread.sleep(2000);

			instructoreventroster.SelectAction("Add or Remove Instructors");
			Thread.sleep(5000);
			
			
			//Verify Manage Instructor Header
			expectedtext = "Manage Instructors";
			actualtext = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Page header is correct");
				Reporter.log("Success !! Page header is correct");
			
			}
			else
			{
			
				System.out.println("Failure !! Page header is NOT correct" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Page header is NOT correct" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
				
				
			
			//Verify Presence of Current Instructor Section
			expectedtext = "Current Instructors";
			actualtext = instructormanage.CurrentInstructors.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Current Instructors section is present with correct header");
				Reporter.log("Success !! Current Instructors section is present with correct header");
			
			}
			else
			{
			
				System.out.println("Failure !! Current Instructors section is NOT present" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Current Instructors section is NOT present" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
		
			}
			
			
			//Verify Presence of Favorite Instructor Section
			expectedtext = "My Favorites";
			actualtext = instructormanage.MyFavorites.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! My Favorites Instructors section is present with correct header");
				Reporter.log("Success !! My Favorites Instructors section is present with correct header");
			
			}
			else
			{
			
				System.out.println("Failure !! My Favorites Instructors section is NOT present" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! My Favorites Instructors section is NOT present" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			
			//Verify Presence of Available Instructors Section
			expectedtext = "Available Instructors";
			actualtext = instructormanage.AvailableInstructors.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Available Instructors section is present with correct header");
				Reporter.log("Success !! Available Instructors section is present with correct header");
			
			}
			else
			{
			
				System.out.println("Failure !! Available Instructors section is NOT present" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Available Instructors section is NOT present" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			
			//On Manage Instructor page, Searching for 'Available Instructors' and adding as Assistant Instructor
			instructormanage.SelectSearchTypeByUsername();
			instructormanage.SearchBox.sendKeys(Constants.EM_DemoAdmin_Username);
			instructormanage.SearchButton.click();
			Thread.sleep(5000);
			instructormanage.Search_FirstResultCheckbox.click();
			Thread.sleep(4000);

			instructormanage.SelectActionAndGO("Send E-mail");
			Thread.sleep(4000);

			//Verify Instructor is assigned
			
		System.out.println("Step 4 : Verifying page header for sent email");
		Reporter.log("Step 4 : Verifying page header for sent email"); 		
		
			expectedtext = "Send Message";
			actualtext = instructorsendmsg.Header.getText().trim();
			
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! User is on Send Message Page");
				Reporter.log("Success !! User is on Send Message Page");
			
			}
			else
			{
			
				System.out.println("Failure !! User is NOT redirected to Send Message Page" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! User is NOT redirected to Send Message Page" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			//Add Message and Send Email
			
			instructorsendmsg.EnterMessage("Testing Send Message");
			instructorsendmsg.ClickOnSendMessageLink();
			Thread.sleep(4000);
			
		System.out.println("Step 4 : Verifying message for message sent");
		Reporter.log("Step 4 : Verifying message for message sent"); 		
			
			
			expectedtext = "The message was sent successfully.";
			actualtext = instructorsendmsg.MsgSentHeader.getText().trim();
			
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Message sent to instructor");
				Reporter.log("Success !! Message sent to instructor");
			
			}
			else
			{
			
				System.out.println("Failure !! Message is NOT sent to instructor" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Message is NOT sent to instructor" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}		
				

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/		
			/*
			try {
				//Going to Event Roster page and removing student and deleting  event 
				//so new event can be added with same test data
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
				//instructorhome.GoToNextMonth();
				instructorschedule.FirstEventName.click();
				Thread.sleep(4000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(4000);

				instructoreventroster.Confirm_EventDelete.click();
				Thread.sleep(5000);
			} catch (Exception e) {
			
			}*/
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(4000);
			
			SeleniumFunc.ClickOnElement("linkText", "Delete Event");
			
			Thread.sleep(5000);
			
			
			SeleniumFunc.ClickOnElement("linkText", "Delete Event");

			Thread.sleep(2000);

			System.out.println("Event: "+EventID+" Deleted Successfully!!");
		
		
		/*
		 * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 */
	 	if(AssertFailedCount>0)	
		{
			
			//Marking this test as Failed
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
		
	}
	
	

	/* Test 4: 
	 * Verify User can search And Update Instructor
	*/ 
	
	@Test
	private void SearchAndUpdateInstructor() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify User can search And Update Instructor"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify User can search And Update Instructor"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		InstructorSearchPage instrsearch = new InstructorSearchPage(driver);
		EditInstructorPage editinstructor = new EditInstructorPage(driver);

		System.out.println("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");				
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(4000);

		
		System.out.println("Step 2 : Select agency from agency listing");
		Reporter.log("Step 2 : Select agency from agency listing"); 
				
						
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(4000);

		
		System.out.println("Step 3 : Select a program and go to Search Instructor");
		Reporter.log("Step 3 : Select a program and go to Search Instructor"); 
			
		
		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(4000);

		  	agencyhome.ClickOnSearchInstructorLink();
			Thread.sleep(4000);

		  	
		System.out.println("Step 4 : Verify page title");
		Reporter.log("Step 4 : Verify page title");
		
			String Actual = instrsearch.Title.getText().trim();
			String Expected = "Instructors";
			
			if(Actual.equals(Expected))
			{
		
				System.out.println("Success !! Page title is correct");
				Reporter.log("Success !! Page title is correct");
			
			}
			else
			{
			
				System.out.println("Failure !! Page title with incorrect text" + "\n" + "Expected : "  
									+ "\n" + Expected + "\n" + 
									 "Actual: " + "\n" +  Actual);
				Reporter.log("Failure !! Page title with incorrect text" + "\n" + "Expected : "  
						+ "\n" + Expected + "\n" + 
						 "Actual: " + "\n" +  Actual);
				
				AssertFailedCount++;
			
			}
			
		  	
		System.out.println("Step 5 : Search Instructor with valid details");
		Reporter.log("Step 5 : Search Instructor with valid details");
		
			SeleniumFunc.ClickOnElement("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore > button");
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore.open > div > ul > li:nth-child(2) > a > span.text");
			//instrsearch.SelectSearchTypeByVisibleText("Username");
			SeleniumFunc.ClickOnElement("css", "#search-type-dob");
			Thread.sleep(4000);
			instrsearch.ClickOnSearchButton();
			Thread.sleep(4000);

			
		System.out.println("Step 6 : Edit the Instructor details and verify updated");
		Reporter.log("Step 6 : Edit the Instructor details and verify updated");
		
			instrsearch.ClickOnEditFirstRecord();
			Thread.sleep(4000);

			String Firstname = "EditInstructorDetails";
			String LastName = "UpdatedDetails";
			
			editinstructor.EditFirstName(Firstname);
			editinstructor.EditLastName(LastName);
			editinstructor.ClickOnSaveChangesButton();
			Thread.sleep(4000);

			
			//Verify Updated Details
		
			editinstructor.ClickOnEditLink();
			String ActualFN = editinstructor.FirstName.getAttribute("value").trim();
			String ActualLN = editinstructor.LastName.getAttribute("value").trim();
			editinstructor.ClickOnSaveChangesButton();
			Thread.sleep(4000);

			if(ActualFN.equals(Firstname) && ActualLN.equals(LastName))
			{
			
				System.out.println("Success !! Instructor details updated properly");
				Reporter.log("Success !! Instructor details updated properly");
		
			}
			else
			{
			
				System.out.println("Failure !! Instructor details not updated properly" + "\n" + "Expected : "  
									+ "\n" + Firstname + LastName + "\n" + 
									 "Actual: " + "\n" +  ActualFN + ActualLN);
				Reporter.log("Failure !! Instructor details not updated properly" + "\n" + "Expected : "  
						+ "\n" + Firstname + LastName + "\n" + 
						 "Actual: " + "\n" +  ActualFN + ActualLN);
				
				AssertFailedCount++;
			
			}
			
			
			try {
				//Reverting updated changes to run script properly for next iteration
				
				editinstructor.ClickOnEditButton();
				Thread.sleep(4000);

				editinstructor.EditFirstName("Automation");
				editinstructor.EditLastName("Testing");
				editinstructor.ClickOnSaveChangesButton();
				Thread.sleep(4000);

			} catch (Exception e) {
				
			}
			
			
		/*
		 * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 */
		if(AssertFailedCount>0)	
		{
			
			//Marking this test as Failed
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
	}
}
