package products.EM;

 
import java.io.File;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.EM.AdminSearchEventPage;
import pageFactory.EM.AgencyHomePage;
import pageFactory.EM.AgencyUserPage;
import pageFactory.EM.EMAddStudentPage;
import pageFactory.EM.ErrorPage;
import pageFactory.EM.EventSettingsPage;
import pageFactory.EM.InstructorCreateEventPage;
import pageFactory.EM.InstructorEventRosterPage;
import pageFactory.EM.InstructorHomePage;
import pageFactory.EM.InstructorLocationPage;
import pageFactory.EM.InstructorSchedulePage;
import pageFactory.EM.LoginPage;
import pageFactory.EM.ManageProgramFilesPage;
import pageFactory.EM.RegisterEdEventView;



import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class EMConfigurationTest 
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
	 * Verify Token Required Functionality
	 */ 
	@Test
	private void VerifyTokenRequiredFunctionality() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify Token Required Functionality"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify Token Required Functionality"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
		RegisterEdEventView eventview = new RegisterEdEventView(driver);
		InstructorHomePage instructorhome = new InstructorHomePage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		EventSettingsPage eventsettings = new EventSettingsPage(driver);

		
		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			//Navigate to 'My Event Schedule' page and delete all Events
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//instructorschedule.DeleteAllEvents();
			
		System.out.println("Step 2 : Navigate to Create Event page via Location page ");
		Reporter.log("Step 2 : Navigate to Create Event page via Location page  "); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			instructorlocations.SelectProgram(2);	
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
			instructorcreateevent.SelectDate("5");
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
				
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
				
			instructorcreateevent.CreateEvent.click();
			Thread.sleep(5000);
			
			String URL = driver.getCurrentUrl();

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
					
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//Thread.sleep(5000);

			//Verifying Event Time
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
				
			//instructorschedule.FirstEventName.click();
			String EventID = instructoreventroster.EventId.getText().trim();
			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			
		System.out.println("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		System.out.println("Step 6 : Select agency from agency listing");
		Reporter.log("Step 6 : Select agency from agency listing"); 
						
								
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

				
		System.out.println("Step 7 : Select a program and go to Event Settings");
		Reporter.log("Step 7 : Select a program and go to Event Settings"); 
					
				
		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);

		  	agencyhome.ClickOnEventSettingsLink();
			Thread.sleep(5000);
 			
							
		System.out.println("Step 8 : Set  Token Required to Yes");
		Reporter.log("Step 8 : Set  Token Required to Yes");
					
		
			eventsettings.SelectTokenRequired("Yes");
			eventsettings.ClickOnSaveChanges();  	
			Thread.sleep(5000);

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
		System.out.println("Step 9 : Search and Activate event");
		Reporter.log("Step 9 : Search and Activate event"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
			Thread.sleep(2000);

			searchevent.ClickOnSearchEventLink();
			Thread.sleep(5000);
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);
			searchevent.ClickOnSearchButton();
			Thread.sleep(5000);

			searchevent.ClickOnSelectEventCheckBox();
			Thread.sleep(5000);

			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			Thread.sleep(5000);

			
/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
		System.out.println("Step 10 : Go to Register-Ed and verify Token is required");
		Reporter.log("Step 10 : Go to Register-Ed and verify Token is required"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);
			Thread.sleep(5000);
			
			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			String browserName = cap.getBrowserName().toLowerCase();


			if(browserName.equals("internet explorer"))
			try{
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
				Thread.sleep(5000);
			}catch (Exception e) {
			}	
			
			
			//Verify Token Required
			expectedtext = "To register for this event, you must have completed all prerequisite courses." + "\n"+
							"If you have already completed the prerequisite courses, " +
							"you should have received an email containing a link to access this event.";
			
			actualtext = SeleniumFunc.GetElementText("css", ".alert.alert-info");
		
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
				
				System.out.println("Success !! Token Required functionlity is working properly. i.e. " +expectedtext);
				Reporter.log("Success !! Token Required functionlity is working properly. i.e. " +expectedtext);
			
			}
			else
			{
			
				System.out.println("Failure !! Token Required functionlity is NOT working properly." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Token Required functionlity is NOT working properly." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			
			//Verifying Status of Button 
			expectedtext = "Token Required";
			actualtext = eventview.RegistrationButton.getText().trim();
	
			if(actualtext.contains(expectedtext))
			{
		
				System.out.println("Success !! Event status is correct for registration. i.e. " +expectedtext);
				Reporter.log("Success !! Event status is correct for registration . i.e. " +expectedtext);
		
			}
			else
			{
		
				System.out.println("Failure !! NOT showing proper event status" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! NOT showing proper event status" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						"Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
	
			}	
					
			
			//Set token required to NO
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/registration");
			Thread.sleep(5000);

			eventsettings.SelectTokenRequired("No");
			eventsettings.ClickOnSaveChanges();  
			Thread.sleep(5000);

			//login.ClickOnLogoutButton();
			//Thread.sleep(5000);

			/*
			//try {

				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);
				login.ClickOnLogInButton();			
				Thread.sleep(5000);

				
				//Going to Event Roster page and removing student and deleting  event 
				//so new event can be added with same test data
				instructorhome.Action_MyEventSchedule.click();
				Thread.sleep(5000);

				//instructorhome.GoToNextMonth();
				instructorschedule.FirstEventName.click();
				Thread.sleep(5000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

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
	
	
	/* Test 2: 
	 * Verify display-only events should not allow online registrations
	 */ 	
	@Test
	private void VerifyDisplayOnlyEvents() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify display-only events should not allow online registrations"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify display-only events should not allow online registrations"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
		RegisterEdEventView eventview = new RegisterEdEventView(driver);
		InstructorHomePage instructorhome = new InstructorHomePage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		EventSettingsPage eventsettings = new EventSettingsPage(driver);

		
		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			//Navigate to 'My Event Schedule' page and delete all Events
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//instructorschedule.DeleteAllEvents();
			
		System.out.println("Step 2 : Navigate to Create Event page via Location page ");
		Reporter.log("Step 2 : Navigate to Create Event page via Location page  "); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			instructorlocations.SelectProgram(2);	
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
			//instructorcreateevent.SelectDate("5");
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
				
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("scroll(1027,987)");		
			instructorcreateevent.CreateEvent.click();
			Thread.sleep(5000);
			String URL = driver.getCurrentUrl();
				
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
					
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//Thread.sleep(5000);

			//Verifying Event Time
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
				
			//instructorschedule.FirstEventName.click();
			String EventID = instructoreventroster.EventId.getText().trim();
			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			
		System.out.println("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		System.out.println("Step 6 : Select agency from agency listing");
		Reporter.log("Step 6 : Select agency from agency listing"); 
						
			((JavascriptExecutor)driver).executeScript("scroll(1022,1060)");					
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);
	
				
		System.out.println("Step 7 : Select a program and go to Event Settings");
		Reporter.log("Step 7 : Select a program and go to Event Settings"); 
					
				
		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);
			
			((JavascriptExecutor)driver).executeScript("scroll(133,620)");
		  	agencyhome.ClickOnEventSettingsLink();
			Thread.sleep(5000);

							
		System.out.println("Step 8 : Set Event Display Only to On");
		Reporter.log("Step 8 : Set Event Display Only to On");
			
		
			SeleniumFunc.EnterValueInTextbox("id", "event_display_only", "ON");
			eventsettings.ClickOnSaveChanges();  	
			Thread.sleep(5000);

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
		System.out.println("Step 9 : Search and Activate event");
		Reporter.log("Step 9 : Search and Activate event"); 
			
		
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
			Thread.sleep(2000);

			searchevent.ClickOnSearchEventLink();
			Thread.sleep(5000);
			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);
			searchevent.ClickOnSearchButton();
			Thread.sleep(5000);

			searchevent.ClickOnSelectEventCheckBox();
			
			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			Thread.sleep(5000);

			
		System.out.println("Step 10 : Go to Register-Ed and Event is view only");
		Reporter.log("Step 10 : Go to Register-Ed and Event is view only"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);
			Thread.sleep(5000);

			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			String browserName = cap.getBrowserName().toLowerCase();


			if(browserName.equals("internet explorer"))
			try{
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
				Thread.sleep(5000);
			}catch (Exception e) {
			}	
						
			//Verifying Status of Button 
			//expectedtext = "To register, follow the special instructions below.";
			expectedtext = "ON";
			actualtext = eventview.RegistrationButton.getText().trim();
	
			if(actualtext.contains(expectedtext))
			{
		
				System.out.println("Success !! Event Display Only functionality is working properly. i.e. " +expectedtext);
				Reporter.log("Success !! Event Display Only functionality is working properly. i.e. " +expectedtext);
		
			}
			else
			{
		
				System.out.println("Failure !! NOT showing proper event status" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! NOT showing proper event status" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						"Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
	
			}	
					
			
			//Set token required to NO
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/registration");
			//SeleniumFunc.EnterValueInTextbox("id", "event_display_only", "");
			SeleniumFunc.DeleteValueFromTextbox("id", "event_display_only");
			
			eventsettings.ClickOnSaveChanges();  	
			Thread.sleep(5000);
			//eventsettings.SelectDisplayOnly("Off");
			//eventsettings.ClickOnSaveChanges(); 
			//Thread.sleep(5000);

			//login.ClickOnLogoutButton();
			//Thread.sleep(5000);

			/*
			try {

				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);
				login.ClickOnLogInButton();			

				Thread.sleep(5000);

				//Going to Event Roster page and removing student and deleting  event 
				//so new event can be added with same test data
				instructorhome.Action_MyEventSchedule.click();
				Thread.sleep(5000);

				//instructorhome.GoToNextMonth();
				instructorschedule.FirstEventName.click();
				Thread.sleep(5000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

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
	 * Verify Cancellation Policy Text can be set
	 */ 
	@Test
	private void VerifyCancellationPolicy() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify Cancellation Policy Text can be set"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify Cancellation Policy Text can be set"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
		InstructorHomePage instructorhome = new InstructorHomePage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		EventSettingsPage eventsettings = new EventSettingsPage(driver);

		
		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			//Navigate to 'My Event Schedule' page and delete all Events
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//instructorschedule.DeleteAllEvents();
			
		System.out.println("Step 2 : Navigate to Create Event page via Location page ");
		Reporter.log("Step 2 : Navigate to Create Event page via Location page  "); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			instructorlocations.SelectProgram(2);	
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
		//	instructorcreateevent.SelectDate("5");
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
				
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
				
			instructorcreateevent.CreateEvent.click();
			Thread.sleep(5000);
			String URL = driver.getCurrentUrl();
				
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
					
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//Thread.sleep(5000);

			//Verifying Event Time
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
				
			//instructorschedule.FirstEventName.click();
			//Thread.sleep(5000);

			//instructorschedule.popupRosterButton.click();
			//Thread.sleep(5000);
			
			//String URL = driver.getCurrentUrl();
			String EventID = instructoreventroster.EventId.getText().trim();
			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			
		System.out.println("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		System.out.println("Step 6 : Select agency from agency listing");
		Reporter.log("Step 6 : Select agency from agency listing"); 
						
								
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

				
		System.out.println("Step 7 : Select a program and go to Event Settings");
		Reporter.log("Step 7 : Select a program and go to Event Settings"); 
					
				
		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);

		  	agencyhome.ClickOnEventSettingsLink();
				  			
			Thread.sleep(5000);

		System.out.println("Step 8 : Enter Cancellations Policy");
		Reporter.log("Step 8 : Enter Cancellations Policy");
					
			String Original = SeleniumFunc.GetAttributeValue("css", "#cancel_policy", "value");
			eventsettings.EnterCancellationsPolicy("Cancel Registration policy");
			eventsettings.ClickOnSaveChanges();  	
			Thread.sleep(5000);

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
		System.out.println("Step 9 : Search and Activate event");
		Reporter.log("Step 9 : Search and Activate event"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
			Thread.sleep(2000);
			searchevent.ClickOnSearchEventLink();
			Thread.sleep(5000);
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);
			searchevent.ClickOnSearchButton();
			Thread.sleep(5000);

			searchevent.ClickOnSelectEventCheckBox();
			Thread.sleep(5000);

			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			Thread.sleep(5000);

			
		System.out.println("Step 10 : Go to Register-Ed and verify cancellation policy is set");
		Reporter.log("Step 10 : Go to Register-Ed and verify cancellation policy is set"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);
			Thread.sleep(5000);

			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			String browserName = cap.getBrowserName().toLowerCase();


			if(browserName.equals("internet explorer"))
			try{
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
				Thread.sleep(5000);
			}catch (Exception e) {
			}	
			
			//Verifying Cancellation Policy
			expectedtext = "Cancel Registration policy";
			actualtext = SeleniumFunc.GetElementText("css", "#primary_content div:nth-of-type(3) p:nth-of-type(2)");
	
			if(actualtext.contains(expectedtext))
			{
		
				System.out.println("Success !! Cancellation Policy properly set. i.e. " +expectedtext);
				Reporter.log("Success !! Cancellation Policy properly set. i.e. " +expectedtext);
		
			}
			else
			{
		
				System.out.println("Failure !! Cancellation Policy NOT set properly" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Cancellation Policy NOT set properly" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
	
			}	
					
			
			//Set token required to NO
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/registration");
			Thread.sleep(10000);

			eventsettings.EnterCancellationsPolicy(Original);
			eventsettings.ClickOnSaveChanges();  	
			Thread.sleep(5000);

			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			
			//try {

				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);
				login.ClickOnLogInButton();			
				Thread.sleep(5000);

				/*
				//Going to Event Roster page and removing student and deleting  event 
				//so new event can be added with same test data
				instructorhome.Action_MyEventSchedule.click();
				Thread.sleep(5000);

				//instructorhome.GoToNextMonth();
				instructorschedule.FirstEventName.click();
				Thread.sleep(5000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

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
	
		
	/* Test 4: 
	 * Verify Preventing a duplicate registration by an admin
	 */ 
	@Test
	private void PreventDuplicateRegistration() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify Preventing a duplicate registration by an admin"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify Preventing a duplicate registration by an admin"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
		InstructorHomePage instructorhome = new InstructorHomePage(driver);
		EMAddStudentPage addstud = new EMAddStudentPage(driver);

		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
		
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			//Navigate to 'My Event Schedule' page and delete all Events
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//instructorschedule.DeleteAllEvents();
			
			
		System.out.println("Step 2 : Navigate to Create Event page via Location page ");
		Reporter.log("Step 2 : Navigate to Create Event page via Location page  "); 
		
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			instructorlocations.SelectProgram(2);	
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
			
			/*instructorcreateevent.GoToNextMonth();*/
		//	instructorcreateevent.SelectDate("5");
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
					
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
			
			instructorcreateevent.EnterSpecialInstruction("Children under the age of 12 must be accompanied by an adult.");

			instructorcreateevent.CreateEvent.click();
			Thread.sleep(5000);

			String URL = driver.getCurrentUrl();	
			
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
					
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//Thread.sleep(5000);

			
			//Verifying Event Time
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
				
			//instructorschedule.FirstEventName.click();
			//Thread.sleep(5000);

			//instructorschedule.popupRosterButton.click();
			//Thread.sleep(5000);
			
			String EventID = instructoreventroster.EventId.getText().trim();
			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			
		System.out.println("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		  	
		System.out.println("Step 6 : Search and Activate event");
		Reporter.log("Step 6 : Search and Activate event"); 			
		  	
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
			Thread.sleep(2000);

			searchevent.ClickOnSearchEventLink();
			Thread.sleep(5000);
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);
			searchevent.ClickOnSearchButton();
			Thread.sleep(5000);

			searchevent.ClickOnSelectEventCheckBox();
			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			Thread.sleep(5000);

			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			
		System.out.println("Step 7 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 7 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(5000);

			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			/*			
			//Enter Instructor Information
			instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();	
			Thread.sleep(5000);

			/*instructorhome.GoToNextMonth();*/
			/*
			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);*/
			
			instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

			
			
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");

			
			Thread.sleep(2000);

			instructoreventroster.SelectAction("Add Student");
			Thread.sleep(5000);

			addstud.FillRegisterStudent("Clarion", "User", "", "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
			addstud.SelectEyeColor();
			addstud.SelectHairColor();
			addstud.EnterSSNNO("125896336");
			addstud.ClickOnRegisterButton();
			Thread.sleep(5000);

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			
		System.out.println("Step 8 : Add another user with same name and DOB");
		Reporter.log("Step 8 : Add another user with same name and DOB"); 
			
			instructoreventroster.SelectAction("Add Student");
			Thread.sleep(5000);

			addstud.FillRegisterStudent("Clarion", "User", "", "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
			addstud.SelectEyeColor();
			addstud.SelectHairColor();
			addstud.EnterSSNNO("125896336");
			addstud.ClickOnRegisterButton();
			Thread.sleep(5000);

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			//Verifying Event Name
			expectedtext = "A student with the same First Name, Last Name, and Date of Birth already registered for this event.";
			actualtext = SeleniumFunc.GetElementText("css", ".help-block.error");

			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Duplicate Registration is prevented. i.e. "+ expectedtext);
				Reporter.log("Success !! Duplicate Registration is prevented. i.e. "+ expectedtext);
			
			}
			else
			{
					
				System.out.println("Failure !! Allowing duplicate registration");
				Reporter.log("Failure !! Allowing duplicate registration");
			
				AssertFailedCount++;
			
			}
			
				
			/*			
			try {
				SeleniumFunc.ToGoToUrl(URL);

				//Deleting event so new event can be added with same test data 
				instructoreventroster.ClickOnSelectEventCheckBox();
				instructoreventroster.SelectEventAction();
				Thread.sleep(5000);

				instructoreventroster.ClickOnGoButton();
				Thread.sleep(5000);

				instructoreventroster.ConfirmStudentRemove.click();
				Thread.sleep(5000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				
				Thread.sleep(5000);
			} catch (Exception e) {
				
			}*/
			
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(5000);
			
			SeleniumFunc.ClickOnElement("linkText", "Cancel Event");
			
			Thread.sleep(2000);
			
			
			SeleniumFunc.ClickOnElement("linkText", "Unschedule and Remove Event");

			Thread.sleep(2000);

			SeleniumFunc.ClickOnElement("css", "body > div.stage > div.content > div.clear > a:nth-child(1) > span");
			
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
	
	
	/* Test 5: 
	 * Verify events can be visible to the student till a certain number of days prior to the event
	 */ 
	@Test
	private void EventVisibilityCondition() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify events can be visible to the student till a certain number of days prior to the event"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify events can be visible to the student till a certain number of days prior to the event"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		EventSettingsPage eventsettings = new EventSettingsPage(driver);

		
		System.out.println("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

	
		System.out.println("Step 2 : Select agency from agency listing");
		Reporter.log("Step 2 : Select agency from agency listing"); 
							
									
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

					
		System.out.println("Step 3 : Select a program and go to Event Settings");
		Reporter.log("Step 3 : Select a program and go to Event Settings"); 
						
					
		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);

		  	agencyhome.ClickOnEventSettingsLink();
			Thread.sleep(5000);

								
		System.out.println("Step 4 : Set Events First Visible to 1 day before event");
		Reporter.log("Step 4 : Set Events First Visible to 1 day before event");
					
			
			eventsettings.EnterEventFirstVisible("2");
			eventsettings.ClickOnSaveChanges();  
			Thread.sleep(5000);

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			
		System.out.println("Step 5 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 5 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
			
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

	
		System.out.println("Step 6 : Navigate to Create Event page via Location page ");
		Reporter.log("Step 6 : Navigate to Create Event page via Location page  "); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			Thread.sleep(5000);

			instructorlocations.SelectProgram(2);	
			Thread.sleep(5000);

			instructorlocations.ScheduleAnEvent.click();	
			Thread.sleep(5000);
					
			
		System.out.println("Step 7 : On Create Event page , entering all required details and click on 'Create Event' button");
		Reporter.log("Step 7 : On Create Event page , entering all required detailsb and click on 'Create Event' button"); 
			
				
			instructorcreateevent.SelectProgram();
			Thread.sleep(2000);
					
			instructorcreateevent.Location.clear();
			instructorcreateevent.Location.sendKeys("Demo Agency Location");
			Thread.sleep(5000);
			instructorcreateevent.Location_FirstChoice.click();
			Thread.sleep(2000);
				
		
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
						
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(5000);

		
		System.out.println("Step 8 : Verify event is visible 2 Days before the event.");
		Reporter.log("Step 8 : Verify event is visible 2 Days before the event."); 
					
	
			String Date = SeleniumFunc.GetElementText("css", ".event-date");
			String[] parts = Date.split("-");
			Date = parts[0];
			Date = Date.replace("/5/", "/3/");

			String VisibleDate = SeleniumFunc.GetElementText("css", "#event_vis_option_label a");
			String[] part = VisibleDate.split("-");
			VisibleDate = part[1];
			
			if((VisibleDate.trim()).equals(Date.trim()))
			{
				
				System.out.println("Success !! Events First Visible functionality is working properly");
				Reporter.log("Success !! Events First Visible functionality is working properly");
			
			}
			else
			{
					
				System.out.println("Failure !! Events First Visible functionality is NOT working properly");
				Reporter.log("Failure !! Events First Visible functionality is NOT working properly");
			
				AssertFailedCount++;
			
			}
		
			
			try {
				//Set to default
				login.ClickOnLogoutButton();
				Thread.sleep(5000);

				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);

				SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
				Thread.sleep(5000);

				agencyhome.ClickToSelectProgram();
				Thread.sleep(5000);

				agencyhome.ClickOnEventSettingsLink();
				Thread.sleep(5000);

				eventsettings.EnterEventFirstVisible("0");
				eventsettings.ClickOnSaveChanges();
				Thread.sleep(5000);

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
	
		
	/* Test 6: 
	 * Verify registration for events can be set to close a certain number of days prior to event
	 */ 
	@Test
	private void RegistrationCondition() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify registration for events can be set to close a certain number of days prior to event"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Verify registration for events can be set to close a certain number of days prior to event"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		EventSettingsPage eventsettings = new EventSettingsPage(driver);

		
		System.out.println("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

	
		System.out.println("Step 2 : Select agency from agency listing");
		Reporter.log("Step 2 : Select agency from agency listing"); 
							
									
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
								
			Thread.sleep(5000);

		System.out.println("Step 3 : Select a program and go to Event Settings");
		Reporter.log("Step 3 : Select a program and go to Event Settings"); 
						
					
		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);

		  	agencyhome.ClickOnEventSettingsLink();
					  			
			Thread.sleep(8000);
		
		System.out.println("Step 4 : Set Registration Window Closes to 1 day before event");
		Reporter.log("Step 4 : Set Registration Window Closes to 1 day before event");
					
			
			eventsettings.EnterRegistrationWindowCloses("2");
			eventsettings.ClickOnSaveChanges();  
			Thread.sleep(8000);
			
			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			
		System.out.println("Step 5 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 5 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
			
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

	
		System.out.println("Step 6 : Navigate to Create Event page via Location page ");
		Reporter.log("Step 6 : Navigate to Create Event page via Location page  "); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			Thread.sleep(5000);

			instructorlocations.SelectProgram(2);	
			Thread.sleep(5000);

			instructorlocations.ScheduleAnEvent.click();	
			Thread.sleep(5000);
					
			
		System.out.println("Step 7 : On Create Event page , entering all required details and click on 'Create Event' button");
		Reporter.log("Step 7 : On Create Event page , entering all required detailsb and click on 'Create Event' button"); 
			
				
			instructorcreateevent.SelectProgram();
			Thread.sleep(2000);
					
			instructorcreateevent.Location.clear();
			instructorcreateevent.Location.sendKeys("Demo Agency Location");
			Thread.sleep(5000);
			instructorcreateevent.Location_FirstChoice.click();
			Thread.sleep(2000);
			
			instructorcreateevent.GoToNextMonth();
			instructorcreateevent.SelectDate("5");
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
						
			instructorcreateevent.AddEventDate.click();
		
			Thread.sleep(5000);

		System.out.println("Step 8 :  Registration Window Closes 2 Days before the event.");
		Reporter.log("Step 8 :  Registration Window Closes 2 Days before the event."); 
					
	
			String Date = SeleniumFunc.GetElementText("css", ".event-date");
			System.out.println(Date);

			String[] parts = Date.split("-");
			Date = parts[0];
			Date = Date.replace("/5/", "/3/");

			String VisibleDate = SeleniumFunc.GetElementText("css", "#event_reg_option_label a");
			String[] part = VisibleDate.split("-");
			VisibleDate = part[1];
			
			System.out.println(VisibleDate.trim());
			System.out.println(Date.trim());
			
			if((VisibleDate.trim()).equals(Date.trim()))
			{
				
				System.out.println("Success !! Close Registration Window functionality is working properly");
				Reporter.log("Success !! Close Registration Window functionality is working properly");
			
			}
			else
			{
					
				System.out.println("Failure !! Close Registration Window functionality is NOT working properly");
				Reporter.log("Failure !! Close Registration Window functionality is NOT working properly");
			
				AssertFailedCount++;
			
			}
		
			
			try {
				//Set to default
				login.ClickOnLogoutButton();
				Thread.sleep(5000);

				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);

				SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
				Thread.sleep(5000);

				agencyhome.ClickToSelectProgram();
				Thread.sleep(5000);

				agencyhome.ClickOnEventSettingsLink();
				Thread.sleep(5000);

				eventsettings.EnterRegistrationWindowCloses("0");
				eventsettings.ClickOnSaveChanges();
				Thread.sleep(5000);

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
	
	
	/* Test 7: 
	 * Verify Cancellations window closes date can be set for a program
	 */ 
	@Test
	private void RegistrationCloseCondition() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Verify Cancellations window closes date can be set for a program"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Verify Cancellations window closes date can be set for a program"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		EventSettingsPage eventsettings = new EventSettingsPage(driver);

		
		System.out.println("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

	
		System.out.println("Step 2 : Select agency from agency listing");
		Reporter.log("Step 2 : Select agency from agency listing"); 
							
									
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);
				
					
		System.out.println("Step 3 : Select a program and go to Event Settings");
		Reporter.log("Step 3 : Select a program and go to Event Settings"); 
						
					
		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);

		  	agencyhome.ClickOnEventSettingsLink();
			Thread.sleep(5000);
		  			
								
		System.out.println("Step 4 : Set Cancel Window Closes to 1 day before event");
		Reporter.log("Step 4 : Set Cancel Window Closes to 1 day before event");
					
			
			eventsettings.EnterCancellationWindow("2");
			eventsettings.ClickOnSaveChanges();  
			Thread.sleep(5000);

			
/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			
		System.out.println("Step 5 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 5 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
			
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

	
		System.out.println("Step 6 : Navigate to Create Event page via Location page ");
		Reporter.log("Step 6 : Navigate to Create Event page via Location page  "); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			Thread.sleep(5000);

			instructorlocations.SelectProgram(2);	
			Thread.sleep(5000);

			instructorlocations.ScheduleAnEvent.click();	
			Thread.sleep(5000);
					
			
		System.out.println("Step 7 : On Create Event page , entering all required details and click on 'Create Event' button");
		Reporter.log("Step 7 : On Create Event page , entering all required detailsb and click on 'Create Event' button"); 
			
				
			instructorcreateevent.SelectProgram();
			Thread.sleep(2000);
					
			instructorcreateevent.Location.clear();
			instructorcreateevent.Location.sendKeys("Demo Agency Location");
			Thread.sleep(5000);
			instructorcreateevent.Location_FirstChoice.click();
			Thread.sleep(2000);
				
			instructorcreateevent.GoToNextMonth();
			instructorcreateevent.SelectDate("5");
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
						
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(5000);

		
		System.out.println("Step 8 :  Cancel Window Closes 2 Days before the event.");
		Reporter.log("Step 8 :  Cancel Window Closes 2 Days before the event."); 
					
	
			String Date = SeleniumFunc.GetElementText("css", ".event-date");
			String[] parts = Date.split("-");
			Date = parts[0];
			Date = Date.replace("/5/", "/3/");

			String VisibleDate = SeleniumFunc.GetElementText("css", "#event_cx_option_label a");
			String[] part = VisibleDate.split("-");
			VisibleDate = part[1];
			
			if((VisibleDate.trim()).equals(Date.trim()))
			{
				
				System.out.println("Success !! Cancel Registration Window functionality is working properly");
				Reporter.log("Success !! Cancel Registration Window functionality is working properly");
			
			}
			else
			{
					
				System.out.println("Failure !! Cancel Registration Window functionality is NOT working properly");
				Reporter.log("Failure !! Cancel Registration Window functionality is NOT working properly");
			
				AssertFailedCount++;
			
			}
		
			
			try {
				//Set to default
				login.ClickOnLogoutButton();
				Thread.sleep(5000);

				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);

				SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
				Thread.sleep(5000);

				agencyhome.ClickToSelectProgram();
				Thread.sleep(5000);

				agencyhome.ClickOnEventSettingsLink();
				Thread.sleep(5000);

				eventsettings.EnterCancellationWindow("0");
				eventsettings.ClickOnSaveChanges();
				Thread.sleep(5000);

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
	
	
	/* Test 8: 
	 * Verify User can upload files using file manager
	 */ 
	@Test
	private void UploadFileViaFileManager() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Verify User can upload files using file manager"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Verify User can upload files using file manager"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		ManageProgramFilesPage managefiles = new ManageProgramFilesPage(driver);
		
		System.out.println("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

	
		System.out.println("Step 2 : Select agency from agency listing");
		Reporter.log("Step 2 : Select agency from agency listing"); 
							
									
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

					
		System.out.println("Step 3 : Select a program and go to Event Settings");
		Reporter.log("Step 3 : Select a program and go to Event Settings"); 
						
					
		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);

		  	agencyhome.ClickOnManageProgramFilesLink();
			Thread.sleep(5000);
	
								
		System.out.println("Step 4 : Verify user can upload file successfully");
		Reporter.log("Step 4 : Verify user can upload file successfully");
					
			
			managefiles.SelectFileVisibility("No");
			managefiles.EnterDescription("Automation Verification");
			File file = new File("src/test/resources/SampleFileUpload.txt");
			managefiles.SendPath(file.getAbsolutePath());
			managefiles.ClickOnUploadButton();
			Thread.sleep(2000);
			
/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			//Verify file is uploaded successfully
			String expectedtext = "The file was uploaded successfully.";
			
			String actualtext = managefiles.Success.getText().trim();
		
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
				
				System.out.println("Success !! File upload functionality is working properly. i.e. " +expectedtext);
				Reporter.log("Success !! File upload functionality is working properly. i.e. " +expectedtext);
			
			}
			else
			{
			
				System.out.println("Failure !! File upload functionality is NOT working properly." + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! File upload functionality is NOT working properly." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}			
	
			managefiles.EditDescription("Edit Description");
			managefiles.UpdateDescription();
			Thread.sleep(5000);

			//Verify Description is updated
			expectedtext = "Edit Description";
			
			actualtext = managefiles.EditDescription.getAttribute("value");
		
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
				
				System.out.println("Success !! Description Updated. i.e. " +expectedtext);
				Reporter.log("Success !! Description Updated. i.e. " +expectedtext);
			
			}
			else
			{
			
				System.out.println("Failure !! Description NOT Updated." + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Description NOT Updated." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}	
			
			managefiles.ClickOnDelete();
			Thread.sleep(5000);


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
	
	/* Test 9: 
	 * Verify KE Super Admin able to configure a program description
	 */ 
	@Test
	private void Manage_Config_Options() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 :Verify KE Super Admin able to configure a program description"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Verify KE Super Admin able to configure a program description"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		ManageProgramFilesPage managefiles = new ManageProgramFilesPage(driver);
		AgencyUserPage agencyuser = new AgencyUserPage(driver);
		
		System.out.println("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			
			if(SeleniumFunc.IsElementPresent("linkText", "Log Out"))
			{
				SeleniumFunc.ClickOnElement("linkText", "Log Out");
			}
			
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
		
		System.out.println("Step 2 : Click on Config Options under the Configuration menu.");
		Reporter.log("Step 2 : Click on Config Options under the Configuration menu."); 
		
			SeleniumFunc.ClickOnElement("linkText", "Config Options");
			String expected = "Program Options";
			
			
		System.out.println("Step 3 : Verify Program Options visible.");
		Reporter.log("Step 3 : Verify Program Options visible."); 
			
			if(SeleniumFunc.GetElementText("css", "#type_program > h3").equals(expected))
			{
				System.out.println("Success !! Program Options displayed. Expected: " +expected);
				Reporter.log("Success !! Program Options displayed. Expected " +expected);
			}
			else
			{
				System.out.println("Failure !! Program Options not displayed. Expected: " +expected);
				Reporter.log("Failure !! Program Options not displayed. Expected " +expected);
				AssertFailedCount++;
			}
			
		System.out.println("Step 4 : Click on the description of one of the options" + "\n" +
		                            "& And Verify The description should turn into a textarea that you can edit.");
		Reporter.log("Step 4 : Click on the description of one of the options" + "\n" +
		                            "& And Verify The description should turn into a textarea that you can edit."); 
		
		
			SeleniumFunc.ClickOnElement("css", "div[id='display-desc-2'][class='display-field']");
			
			
			if(SeleniumFunc.IsElementPresent("css", "button[class='btn btn-primary'][data-id='2']"))
			{
				System.out.println("Success !! Default value should turn into a textarea");
				Reporter.log("Success !! Default value should turn into a textarea");
			}
			else
			{
				System.out.println("Failure !! Default value not turned into a textarea");
				Reporter.log("Failure !! Default value not turned into a textarea");
				AssertFailedCount++;
			}
	
		System.out.println("Step 4 : Change the description and click Save Changes.");
		Reporter.log("Step 4 : Change the description and click Save Changes.");
		
				agencyuser.Enter_Config_Desc();
				//((JavascriptExecutor)driver).executeScript("scroll(601,686)");
				JavascriptExecutor jse6 = (JavascriptExecutor) driver; 
				jse6.executeScript("window.scrollBy(0,250)", "");
				SeleniumFunc.ClickOnElement("css", "button[class='btn btn-primary'][data-id='2']");
				Thread.sleep(8000);
			
			
		System.out.println("Step 5 : Go to a program in Event Manager and click on Program Settings.");
		Reporter.log("Step 5 : Go to a program in Event Manager and click on Program Settings.");
		
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
				SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
				agencyhome.ClickToSelectProgram();
				((JavascriptExecutor)driver).executeScript("scroll(254,845)");
				agencyhome.ClickOnProgramSettingsLink();
				
		System.out.println("Step 6 : Verify the change you made to the description should be visible.");
		Reporter.log("Step 6 : Verify the change you made to the description should be visible.");
		
				String ExpectedDesc = "test - 17th Oct 2016 This will be the title seen on the pages where students select events to register for. It should contain the name of the program and some indication of the action being taken. For example, 'Register for Your Proctored Exam'.";
			
			if(SeleniumFunc.GetElementText("xpath", "//*[contains(text(),'Event Selection Calendar Page Title')]/following-sibling::div").contains(ExpectedDesc))
			{

				System.out.println("Success !! Change you made to the description is visible.");
				Reporter.log("Success !! Change you made to the description is visible.");
			}
			else
			{
				System.out.println("Failure !! Change you made to the description is not visible.");
				Reporter.log("Failure !!  Change you made to the description is not visible.");
				AssertFailedCount++;
			}
		
		
		System.out.println("-----Repeat the above steps for Event------");
		Reporter.log("-----Repeat the above steps for Event------");
		
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
		
		System.out.println("Step 1 : Click on Config Options under the Configuration menu.");
		Reporter.log("Step 1 : Click on Config Options under the Configuration menu."); 
				
					SeleniumFunc.ClickOnElement("linkText", "Config Options");
					String expected1 = "Event Options";
					
					
		System.out.println("Step 2 : Verify Event Options visible.");
		Reporter.log("Step 2 : Verify Event Options visible."); 
					
					if(SeleniumFunc.GetElementText("css", "#type_event > h3").equals(expected1))
					{
						System.out.println("Success !! Event Options displayed. Expected: " +expected1);
						Reporter.log("Success !! Event Options displayed. Expected " +expected1);
					}
					else
					{
						System.out.println("Failure !! Event Options not displayed. Expected: " +expected1);
						Reporter.log("Failure !! Event Options not displayed. Expected " +expected1);
						AssertFailedCount++;
					}
					
			System.out.println("Step 4 : Click on the description of one of the options" + "\n" +
                            "& And Verify The description should turn into a textarea that you can edit.");
		    Reporter.log("Step 4 : Click on the description of one of the options" + "\n" +
                            "& And Verify The description should turn into a textarea that you can edit."); 
		
		    		SeleniumFunc.ClickOnElement("xpath", ".//*[@id='types']/li[2]/a");
		    		Thread.sleep(8000);
					SeleniumFunc.ClickOnElement("css", "#display-desc-27>p");
					Thread.sleep(2000);
					
					if(SeleniumFunc.IsElementPresent("css", "button[class='btn btn-primary'][data-id='27']"))
					{
						System.out.println("Success !! Default value should turn into a textarea");
						Reporter.log("Success !! Default value should turn into a textarea");
					}
					else
					{
						System.out.println("Failure !! Default value not turned into a textarea");
						Reporter.log("Failure !! Default value not turned into a textarea");
						AssertFailedCount++;
					}
					
			System.out.println("Step 4 : Change the description and click Save Changes.");
			Reporter.log("Step 4 : Change the description and click Save Changes.");
					
			
						agencyuser.Enter_EventConfig_Desc();
						SeleniumFunc.ClickOnElement("css", "button[class='btn btn-primary'][data-id='27']");
						Thread.sleep(8000);
						
			System.out.println("Step 5 : Go to a program in Event Manager and click on Program Settings.");
			Reporter.log("Step 5 : Go to a program in Event Manager and click on Program Settings.");
						
						SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
						SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
						agencyhome.ClickToSelectProgram();
						agencyhome.ClickOnEventSettingsLink();
					
			System.out.println("Step 6 : Verify the change you made to the Event description should be visible.");
			Reporter.log("Step 6 : Verify the change you made to the Event description should be visible.");
			
						String EventExpected = "Limited event information for full events can be displayed on the calendar and list views by changing the setting below.";
						
					if(SeleniumFunc.GetElementText("css", "#reg_reqts > div:nth-child(2) > table:nth-child(3) > tbody > tr > td:nth-child(1) > div > p").contains(EventExpected))
					{

						System.out.println("Success !! Change you made to the event description is visible.");
						Reporter.log("Success !! Change you made to the event description is visible.");
					}
					else
					{
						System.out.println("Failure !! Change you made to the description is not visible.");
						Reporter.log("Failure !!  Change you made to the description is not visible.");
						AssertFailedCount++;
					}
					
			System.out.println("-----Repeat the above steps for Payment------");
			Reporter.log("-----Repeat the above steps for Payment------");
			
						SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
						
						
			System.out.println("Step 1 : Click on Config Options under the Configuration menu.");
			Reporter.log("Step 1 : Click on Config Options under the Configuration menu."); 
								
						SeleniumFunc.ClickOnElement("linkText", "Config Options");
						String PaymentText = "Payment Options";
									
									
						System.out.println("Step 2 : Verify Payment Options visible.");
						Reporter.log("Step 2 : Verify Event Payment visible."); 
									
					if(SeleniumFunc.GetElementText("css", "#type_payment > h3").equals(PaymentText))
					{
						System.out.println("Success !! Payment Options displayed. Expected: " +PaymentText);
						Reporter.log("Success !! Payment Options displayed. Expected " +PaymentText);
					}
					else
					{
						System.out.println("Failure !! Event Options not displayed. Expected: " +PaymentText);
						Reporter.log("Failure !! Event Options not displayed. Expected " +PaymentText);
						AssertFailedCount++;
					}
									
						System.out.println("Step 4 : Click on the description of one of the options" + "\n" +
				                            "& And Verify The description should turn into a textarea that you can edit.");
						Reporter.log("Step 4 : Click on the description of one of the options" + "\n" +
				                            "& And Verify The description should turn into a textarea that you can edit."); 
						
						SeleniumFunc.ClickOnElement("xpath", ".//*[@id='types']/li[3]/a");
						SeleniumFunc.ClickOnElement("css", "div[id='display-desc-12'][class='display-field']");
						Thread.sleep(2000);			
									
					if(SeleniumFunc.IsElementPresent("css", "button[class='btn btn-primary'][data-id='12']"))
					{
						System.out.println("Success !! Default value should turn into a textarea");
						Reporter.log("Success !! Default value should turn into a textarea");
					}
					else
					{
						System.out.println("Failure !! Default value not turned into a textarea");
						Reporter.log("Failure !! Default value not turned into a textarea");
						AssertFailedCount++;
					}
									
						System.out.println("Step 4 : Change the description and click Save Changes.");
						Reporter.log("Step 4 : Change the description and click Save Changes.");
									
						agencyuser.Enter_PaymentConfig_Desc();
						SeleniumFunc.ClickOnElement("css", "button[class='btn btn-primary'][data-id='12']");
						Thread.sleep(8000);
										
			System.out.println("Step 5 : Go to a program in Event Manager and click on Payment Settings.");
			Reporter.log("Step 5 : Go to a program in Event Manager and click on Payment Settings.");
										
						SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
						SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
						agencyhome.ClickToSelectProgram();
						
						agencyhome.ClickOnPaymentSettingsLink();
									
			System.out.println("Step 6 : Verify the change you made to the Payment description should be visible.");
			Reporter.log("Step 6 : Verify the change you made to the Payment description should be visible.");
							
						String PaymentExpected = "Payment Mode (Off/On)";
										
					if(SeleniumFunc.GetElementText("css", "#set_payment_config > div:nth-child(1) > label").contains(PaymentExpected))
					{

						System.out.println("Success !! Change you made to the Payment description is visible.");
						Reporter.log("Success !! Change you made to the Payment description is visible.");
					}
					else
					{
						System.out.println("Failure !! Change you made to the description is not visible.");
						Reporter.log("Failure !!  Change you made to the description is not visible.");
						AssertFailedCount++;
					}
			
			System.out.println("-----Repeat the above steps for Approval------");
			Reporter.log("-----Repeat the above steps for Approval------");
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
			
			
			System.out.println("Step 1 : Click on Config Options under the Configuration menu.");
			Reporter.log("Step 1 : Click on Config Options under the Configuration menu."); 
								
						SeleniumFunc.ClickOnElement("linkText", "Config Options");
						String ApprovalText = "Approval Options";
									
									
						System.out.println("Step 2 : Verify Approval Options visible.");
						Reporter.log("Step 2 : Verify Approval Options visible."); 
									
					if(SeleniumFunc.GetElementText("css", "#type_approval > h3").equals(ApprovalText))
					{
						System.out.println("Success !! Approval Options displayed. Expected: " +ApprovalText);
						Reporter.log("Success !! Approval Options displayed. Expected " +ApprovalText);
					}
					else
					{
						System.out.println("Failure !! Approval Options not displayed. Expected: " +ApprovalText);
						Reporter.log("Failure !! Approval Options not displayed. Expected " +ApprovalText);
						AssertFailedCount++;
					}
									
						System.out.println("Step 4 : Click on the description of one of the options" + "\n" +
				                            "& And Verify The description should turn into a textarea that you can edit.");
						Reporter.log("Step 4 : Click on the description of one of the options" + "\n" +
				                            "& And Verify The description should turn into a textarea that you can edit."); 
						
						SeleniumFunc.ClickOnElement("xpath", ".//*[@id='types']/li[4]/a");
						SeleniumFunc.ClickOnElement("css", "#display-desc-17");
						Thread.sleep(2000);			
									
					if(SeleniumFunc.IsElementPresent("css", "button[class='btn btn-primary'][data-id='17']"))
					{
						System.out.println("Success !! Default value should turn into a textarea");
						Reporter.log("Success !! Default value should turn into a textarea");
					}
					else
					{
						System.out.println("Failure !! Default value not turned into a textarea");
						Reporter.log("Failure !! Default value not turned into a textarea");
						AssertFailedCount++;
					}
									
						System.out.println("Step 4 : Change the description and click Save Changes.");
						Reporter.log("Step 4 : Change the description and click Save Changes.");
									
						agencyuser.Enter_ApprovalConfig_Desc();
						SeleniumFunc.ClickOnElement("css", "button[class='btn btn-primary'][data-id='17']");
						Thread.sleep(8000);
										
			System.out.println("Step 5 : Go to a program in Event Manager and click on Approval link.");
			Reporter.log("Step 5 : Go to a program in Event Manager and click on Approval link.");
										
						SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
						SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
						agencyhome.ClickToSelectProgram();
						agencyhome.ClickOnApprovalLink();
									
			System.out.println("Step 6 : Verify the change you made to the Event description should be visible.");
			Reporter.log("Step 6 : Verify the change you made to the Event description should be visible.");
							
						String ApprovalExpected = "Event Publish Approvals (Off/On)";
										
					if(SeleniumFunc.GetElementText("xpath", "//*[contains(text(),'Publish Events')]/following-sibling::div").contains(ApprovalExpected))
					{

						System.out.println("Success !! Change you made to the Approval description is visible.");
						Reporter.log("Success !! Change you made to the Approval description is visible.");
					}
					else
					{
						System.out.println("Failure !! Change you made to the description is not visible.");
						Reporter.log("Failure !!  Change you made to the description is not visible.");
						AssertFailedCount++;
					}
			
					
			System.out.println("-----Repeat the above steps for Certificate------");
			Reporter.log("-----Repeat the above steps for Certificate------");
					
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
					
					
					System.out.println("Step 1 : Click on Config Options under the Configuration menu.");
					Reporter.log("Step 1 : Click on Config Options under the Configuration menu."); 
										
								SeleniumFunc.ClickOnElement("linkText", "Config Options");
								String CertificateText = "Certificate Options";
											
											
								System.out.println("Step 2 : Verify Certificate Options visible.");
								Reporter.log("Step 2 : Verify Certificate Options visible."); 
											
							if(SeleniumFunc.GetElementText("css", "#type_certificate > h3").equals(CertificateText))
							{
								System.out.println("Success !! Certificate Options displayed. Expected: " +CertificateText);
								Reporter.log("Success !! Certificate Options displayed. Expected " +CertificateText);
							}
							else
							{
								System.out.println("Failure !! Certificate Options not displayed. Expected: " +CertificateText);
								Reporter.log("Failure !! Certificate Options not displayed. Expected " +CertificateText);
								AssertFailedCount++;
							}
											
								System.out.println("Step 4 : Click on the description of one of the options" + "\n" +
						                            "& And Verify The description should turn into a textarea that you can edit.");
								Reporter.log("Step 4 : Click on the description of one of the options" + "\n" +
						                            "& And Verify The description should turn into a textarea that you can edit."); 
								
								
								//JavascriptExecutor js = (JavascriptExecutor)driver;
								//js.executeScript("scroll(0, 250)");
								
								SeleniumFunc.ClickOnElement("linkText", "Certificate");
																
								SeleniumFunc.ClickOnElement("css", "#display-desc-121");
								Thread.sleep(2000);			
											
							if(SeleniumFunc.IsElementPresent("css", "button[class='btn btn-primary'][data-id='121']"))
							{
								System.out.println("Success !! Default value should turn into a textarea");
								Reporter.log("Success !! Default value should turn into a textarea");
							}
							else
							{
								System.out.println("Failure !! Default value not turned into a textarea");
								Reporter.log("Failure !! Default value not turned into a textarea");
								AssertFailedCount++;
							}
											
								System.out.println("Step 4 : Change the description and click Save Changes.");
								Reporter.log("Step 4 : Change the description and click Save Changes.");
											
								agencyuser.Enter_CertificateConfig_Desc();
								SeleniumFunc.ClickOnElement("css", "button[class='btn btn-primary'][data-id='121']");
								Thread.sleep(8000);
												
					System.out.println("Step 5 : Go to a program in Event Manager and click on Approval link.");
					Reporter.log("Step 5 : Go to a program in Event Manager and click on Approval link.");
												
								SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
								SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
								agencyhome.ClickToSelectProgram();
								agencyhome.ClickOnCertificatesLink();
											
					System.out.println("Step 6 : Verify the change you made to the Certificate description should be visible.");
					Reporter.log("Step 6 : Verify the change you made to the Certificate description should be visible.");
									
								String CertExpected = "Download: Allow administrators and instructors to download student certificates.";
												
							if(SeleniumFunc.GetElementText("css", "div:nth-child(2) > div > label:nth-child(3)").contains(CertExpected))
							{

								System.out.println("Success !! Change you made to the Certificate description is visible.");
								Reporter.log("Success !! Change you made to the Certificate description is visible.");
							}
							else
							{
								System.out.println("Failure !! Change you made to the Certificate description is not visible.");
								Reporter.log("Failure !!  Change you made to the Certificate description is not visible.");
								AssertFailedCount++;
							}
			
							
		System.out.println("-----Repeat the above steps for Notification ------");
		Reporter.log("-----Repeat the above steps for Notification ------");
									
						SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
									
									
						System.out.println("Step 1 : Click on Config Options under the Configuration menu.");
						Reporter.log("Step 1 : Click on Config Options under the Configuration menu."); 
														
						SeleniumFunc.ClickOnElement("linkText", "Config Options");
						String NotificationText = "Notification Options";
															
															
						System.out.println("Step 2 : Verify Notification Options visible.");
						Reporter.log("Step 2 : Verify Notification Options visible."); 
															
					if(SeleniumFunc.GetElementText("css", "#type_notification > h3").equals(NotificationText))
					{
						System.out.println("Success !! Notification Options displayed. Expected: " +NotificationText);
						Reporter.log("Success !! Notification Options displayed. Expected " +NotificationText);
					}
					else
					{
						System.out.println("Failure !! Notification Options not displayed. Expected: " +NotificationText);
						Reporter.log("Failure !! Notification Options not displayed. Expected " +CertificateText);
						AssertFailedCount++;
					}
															
						System.out.println("Step 4 : Click on the description of one of the options" + "\n" +
							 "& And Verify The description should turn into a textarea that you can edit.");
						Reporter.log("Step 4 : Click on the description of one of the options" + "\n" +
							 "& And Verify The description should turn into a textarea that you can edit."); 
												
						SeleniumFunc.ClickOnElement("xpath", ".//*[@id='types']/li[6]/a");						
						SeleniumFunc.ClickOnElement("css", "#display-desc-48");
						Thread.sleep(2000);									
															
					if(SeleniumFunc.IsElementPresent("css", "button[class='btn btn-primary'][data-id='48']"))
					{
						System.out.println("Success !! Default value should turn into a textarea");
						Reporter.log("Success !! Default value should turn into a textarea");
					}
					else
					{
						System.out.println("Failure !! Default value not turned into a textarea");
						Reporter.log("Failure !! Default value not turned into a textarea");
						AssertFailedCount++;
					}
															
						System.out.println("Step 4 : Change the description and click Save Changes.");
						Reporter.log("Step 4 : Change the description and click Save Changes.");
															
						agencyuser.Enter_NotificationConfig_Desc();
						SeleniumFunc.ClickOnElement("css", "button[class='btn btn-primary'][data-id='48']");
						Thread.sleep(8000);
																
						System.out.println("Step 5 : Go to a program in Event Manager and click on Notification link.");
						Reporter.log("Step 5 : Go to a program in Event Manager and click on Notification link.");
														
						SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
						SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
						agencyhome.ClickToSelectProgram();
						agencyhome.ClickOnNotificationsLink();
															
						System.out.println("Step 6 : Verify the change you made to the Notification description should be visible.");
						Reporter.log("Step 6 : Verify the change you made to the Notification description should be visible.");
													
						String NotificationExpected = "How many days after the event?";
																
					if(SeleniumFunc.GetElementText("css", "#rpt_due_grace_period > span").contains(NotificationExpected))
					{
						System.out.println("Success !! Change you made to the Notification description is visible.");
						Reporter.log("Success !! Change you made to the Notification description is visible.");
					}
					else
					{
						System.out.println("Failure !! Change you made to the Notification description is not visible.");
						Reporter.log("Failure !!  Change you made to the Notification description is not visible.");
						AssertFailedCount++;
					}
					
					
		System.out.println("-----Repeat the above steps for Studentform ------");
		Reporter.log("-----Repeat the above steps for Studentform ------");
												
						SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
												
												
						System.out.println("Step 1 : Click on Config Options under the Configuration menu.");
						Reporter.log("Step 1 : Click on Config Options under the Configuration menu."); 
																	
						SeleniumFunc.ClickOnElement("linkText", "Config Options");
						String StudentText = "Studentform Options";
																		
																		
						System.out.println("Step 2 : Verify Studentform Options visible.");
						Reporter.log("Step 2 : Verify Studentform Options visible."); 
																		
					if(SeleniumFunc.GetElementText("css", "#type_studentform > h3").equals(StudentText))
					{
						System.out.println("Success !! Studentform Options displayed. Expected: " +StudentText);
						Reporter.log("Success !! Studentform Options displayed. Expected " +StudentText);
					}
					else
					{
						System.out.println("Failure !! Studentform Options not displayed. Expected: " +StudentText);
						Reporter.log("Failure !! Studentform Options not displayed. Expected " +StudentText);
						AssertFailedCount++;
					}
																		
					System.out.println("Step 4 : Click on the description of one of the options" + "\n" +
										 "& And Verify The description should turn into a textarea that you can edit.");
					Reporter.log("Step 4 : Click on the description of one of the options" + "\n" +
										 "& And Verify The description should turn into a textarea that you can edit."); 
															
						SeleniumFunc.ClickOnElement("xpath", ".//*[@id='types']/li[7]/a");									
						SeleniumFunc.ClickOnElement("css", "#display-desc-65>p");
						Thread.sleep(2000);													
																		
					if(SeleniumFunc.IsElementPresent("css", "button[class='btn btn-primary'][data-id='65']"))
					{
						System.out.println("Success !! Default value should turn into a textarea");
						Reporter.log("Success !! Default value should turn into a textarea");
					}
					else
					{
						System.out.println("Failure !! Default value not turned into a textarea");
						Reporter.log("Failure !! Default value not turned into a textarea");
						AssertFailedCount++;
					}
																		
						System.out.println("Step 4 : Change the description and click Save Changes.");
						Reporter.log("Step 4 : Change the description and click Save Changes.");
																		
						agencyuser.Enter_StudentformConfig_Desc();
						SeleniumFunc.ClickOnElement("css", "button[class='btn btn-primary'][data-id='65']");
						Thread.sleep(8000);
																			
						System.out.println("Step 5 : Go to a program in Event Manager and click on Student Details link.");
						Reporter.log("Step 5 : Go to a program in Event Manager and click on Student Details link.");
																	
						SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
						SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
						agencyhome.ClickToSelectProgram();
						agencyhome.ClickOnStudentformLink();
																		
						System.out.println("Step 6 : Verify the change you made to the Studentform description should be visible.");
						Reporter.log("Step 6 : Verify the change you made to the Studentform description should be visible.");
																
						String StudentformExpected = "Enter any special instructions for the student results form here.";
																			
					if(SeleniumFunc.GetElementText("css", "#update_studentform_instructions > table > tbody > tr.even > td > div > p").contains(StudentformExpected))
					{
						System.out.println("Success !! Change you made to the Studentform description is visible.");
						Reporter.log("Success !! Change you made to the Studentform description is visible.");
					}
					else
					{
						System.out.println("Failure !! Change you made to the description is not visible.");
						Reporter.log("Failure !!  Change you made to the description is not visible.");
						AssertFailedCount++;
					}	
					
					System.out.println("-----Repeat the above steps for Report Detail ------");
					Reporter.log("-----Repeat the above steps for Report Detail ------");
															
									SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
															
															
									System.out.println("Step 1 : Click on Config Options under the Configuration menu.");
									Reporter.log("Step 1 : Click on Config Options under the Configuration menu."); 
																				
									SeleniumFunc.ClickOnElement("linkText", "Config Options");
									String ReportText = "Report Detail Options";
																					
																					
									System.out.println("Step 2 : Verify Report Detail Options visible.");
									Reporter.log("Step 2 : Verify Report Detail Options visible."); 
																					
								if(SeleniumFunc.GetElementText("css", "#type_report_detail > h3").equals(ReportText))
								{
									System.out.println("Success !! Report Detail Options displayed. Expected: " +ReportText);
									Reporter.log("Success !! Report Detail Options displayed. Expected " +ReportText);
								}
								else
								{
									System.out.println("Failure !! Report Detail Options not displayed. Expected: " +ReportText);
									Reporter.log("Failure !! Report Detail Options not displayed. Expected " +ReportText);
									AssertFailedCount++;
								}
																					
								System.out.println("Step 4 : Click on the description of one of the options" + "\n" +
													 "& And Verify The description should turn into a textarea that you can edit.");
								Reporter.log("Step 4 : Click on the description of one of the options" + "\n" +
													 "& And Verify The description should turn into a textarea that you can edit."); 
																		
								SeleniumFunc.ClickOnElement("xpath", ".//*[@id='types']/li[8]/a");										
								SeleniumFunc.ClickOnElement("css", "#display-desc-118>p");
								Thread.sleep(2000);													
																					
								if(SeleniumFunc.IsElementPresent("css", "button[class='btn btn-primary'][data-id='118']"))
								{
									System.out.println("Success !! Default value should turn into a textarea");
									Reporter.log("Success !! Default value should turn into a textarea");
								}
								else
								{
									System.out.println("Failure !! Default value not turned into a textarea");
									Reporter.log("Failure !! Default value not turned into a textarea");
									AssertFailedCount++;
								}
																					
									System.out.println("Step 4 : Change the description and click Save Changes.");
									Reporter.log("Step 4 : Change the description and click Save Changes.");
																					
									agencyuser.Enter_ReportConfig_Desc();
									SeleniumFunc.ClickOnElement("css", "button[class='btn btn-primary'][data-id='118']");
									Thread.sleep(8000);
																						
									System.out.println("Step 5 : Go to a program in Event Manager and click on Report Detail link.");
									Reporter.log("Step 5 : Go to a program in Event Manager and click on Report Detail link.");
																				
									SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
									SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
									agencyhome.ClickToSelectProgram();
									agencyhome.ClickOnReportDetailsLink();
																					
									System.out.println("Step 6 : Verify the change you made to the Report Detail description should be visible.");
									Reporter.log("Step 6 : Verify the change you made to the Report Detail description should be visible.");
																			
									//String ReportExpected = "Enter any special instructions for the student results form here.";
									String ReportExpected = "EEnter any special instructions for the event form here.";	
									WebElement ele = driver.findElement(By.cssSelector("#report_details > div:nth-child(1) > div > p"));
									((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);	
								if(SeleniumFunc.GetElementText("css", "#report_details > div:nth-child(1) > div > p").contains(ReportExpected))
								{
									System.out.println("Success !! Change you made to the Report Detail description is visible.");
									Reporter.log("Success !! Change you made to the Report Detail description is visible.");
								}
								else
								{
									System.out.println("Failure !! Change you made to the description is not visible.");
									Reporter.log("Failure !!  Change you made to the description is not visible.");
									AssertFailedCount++;
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