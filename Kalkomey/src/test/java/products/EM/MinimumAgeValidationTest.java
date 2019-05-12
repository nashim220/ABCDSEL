package products.EM;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod; 
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.EM.AdminSearchEventPage;
import pageFactory.EM.AgencyHomePage;
import pageFactory.EM.EMAddStudentPage;
import pageFactory.EM.ErrorPage;
import pageFactory.EM.EventSettingsPage;
import pageFactory.EM.InstructorCreateEventPage;
import pageFactory.EM.InstructorEventRosterPage;
import pageFactory.EM.InstructorHomePage;
import pageFactory.EM.InstructorLocationPage;
import pageFactory.EM.InstructorSchedulePage;
import pageFactory.EM.LoginPage;
import pageFactory.EM.RegisterEdEventView;
import pageFactory.EM.RegisterEdRegisterStudentPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class MinimumAgeValidationTest 
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
	 * Verify User can set minimum age on EM
	*/ 
	@Test
	private void SetMinAge() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify User can set minimum age on EM"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify User can set minimum age on EM"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		EventSettingsPage eventsettings = new EventSettingsPage(driver);
		
		System.out.println("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
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

					
		System.out.println("Step 4 : Set Minimum age to 15 and verify");
		Reporter.log("Step 4 : Set Minimum age to 15 and verify");
						
						
			eventsettings.EnterMinAge("15");
			eventsettings.ClickOnSaveChanges();  	
			Thread.sleep(5000);

				
		System.out.println("Step 5 : Verify Minimum age Set To 15");
		Reporter.log("Step 5 : Verify Minimum age Set To 15");
							
		
			String Expected = "15";
			String Actual = SeleniumFunc.GetAttributeValue("css", "#event_min_age", "value");
			
			if(Actual.equals(Expected))
			{
			
				System.out.println("Success !! Minimum age Set to 15.");
				Reporter.log("Success !! Minimum age Set to 15.");
			
			}
			else
			{
				System.out.println("Failure !! Minimum age NOT saved." + "\n" + "Expected : "  
									+ "\n" + Expected );
				Reporter.log("Failure !! Minimum ages NOT saved." + "\n" + "Expected : "  
							+ "\n" + Expected );
					
				AssertFailedCount++;
			
			}		  	
				
		  	
			eventsettings.EnterMinAge("12");
			eventsettings.ClickOnSaveChanges(); 
		  	 	
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
	

	/* Test 2: 
	 * Verify user can set Minimum Age Date on EM
	*/ 	
	@Test
	private void SetMinAgeDate() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify user can set Minimum Age Date on EM"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify user can set Minimum Age Date on EM"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		EventSettingsPage eventsettings = new EventSettingsPage(driver);
		
		System.out.println("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
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

					
		System.out.println("Step 4 : Set Minimum age date to Event End Date and verify");
		Reporter.log("Step 4 : Set Minimum age date to Event End Date and verify");
						
						
			eventsettings.SelectMinAgeDate("Event End Date");
			eventsettings.ClickOnSaveChanges();  	
			Thread.sleep(5000);

				
		System.out.println("Step 5 : Verify Minimum age date Set To Event End Date");
		Reporter.log("Step 5 : Verify Minimum age date Set To Event End Date");
							
			
			String Expected = "Event End Date";
			String Actual = SeleniumFunc.GetSelectedValueFromDropdownList("name", "ConfigValue[event_min_age_date]").trim();
			
			if(Actual.equals(Expected))
			{
			
				System.out.println("Success !! Minimum age date Set To Event End Date.");
				Reporter.log("Success !! Minimum age date Set To Event End Date.");
			
			}
			else
			{
			
				System.out.println("Failure !! Minimum age date NOT saved." + "\n" + "Expected : "  
									+ "\n" + Expected );
				Reporter.log("Failure !! Minimum ages date NOT saved." + "\n" + "Expected : "  
							+ "\n" + Expected );
					
				AssertFailedCount++;
			
			}		  	
				
  					
		System.out.println("Step 6 : Set Minimum age date to Event Start Date and verify");
		Reporter.log("Step 6 : Set Minimum age date to Event Start Date and verify");
				
				
			eventsettings.SelectMinAgeDate("Event Start Date");
			eventsettings.ClickOnSaveChanges();  	
			Thread.sleep(5000);

		
		System.out.println("Step 7 : Verify Minimum age date Set To Event Start Date");
		Reporter.log("Step 7 : Verify Minimum age date Set To Event Start Date");
					
			
			Expected = "Event Start Date";
			Actual = SeleniumFunc.GetSelectedValueFromDropdownList("name", "ConfigValue[event_min_age_date]");
	
			if(Actual.equals(Expected))
			{
	
				System.out.println("Success !! Minimum age date Set To Event Start Date.");
				Reporter.log("Success !! Minimum age date Set To Event Start Date.");
	
			}
			else
			{
				System.out.println("Failure !! Minimum age date NOT saved." + "\n" + "Expected : "  
							+ "\n" + Expected );
				Reporter.log("Failure !! Minimum ages date NOT saved." + "\n" + "Expected : "  
						+ "\n" + Expected );
			
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
	
	
	/* Test 3: 
	 * Verify minimum age message is displayed under DOB field

	*/ 
	@Test
	private void VerifyMinAgeMessageUnderDOB() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify minimum age message is displayed under DOB field"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify minimum age message is displayed under DOB field"  + "\n" +
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
			Thread.sleep(5000);

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

			//login.ClickOnLogoutButton();
			//Thread.sleep(5000);

			
		System.out.println("Step 7 : Go to Register-Ed and verify Min age message below DOB");
		Reporter.log("Step 7 : Go to Register-Ed and verify Min age message below DOB"); 
		
			
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

			eventview.ClickOnRegisterNowButton();
			Thread.sleep(5000);

			//Verify Min Age Message
			expectedtext = "You must be at least 12 to attend this event.";
			actualtext = SeleniumFunc.GetElementText("css", "#form_students fieldset div p");
		
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
				
				System.out.println("Success !! Min age message below DOB is displayed. i.e. " +expectedtext);
				Reporter.log("Success !! Min age message below DOB is displayed. i.e. " +expectedtext);
			
			}
			else
			{
			
				System.out.println("Failure !! Min age message below DOB is NOT displayed." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Min age message below DOB is NOT displayed." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			/*
			try {
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);
				login.ClickOnLogInButton();			
				Thread.sleep(5000);

				
				//Going to Event Roster page and removing student and deleting  event 
				//so new event can be added with same test data
				instructorhome.VisitHomePage.click();
				Thread.sleep(5000);

				instructorhome.Action_MyEventSchedule.click();
				Thread.sleep(5000);

				//instructorhome.GoToNextMonth();
				instructorschedule.FirstEventName.click();
				Thread.sleep(5000);

				instructorschedule.popupRosterButton.click();
				Thread.sleep(5000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				
				Thread.sleep(5000);
			} catch (Exception e) {
				
			}
			*/
			
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
	 * Verify the corresponding min Age criteria is enforced on registration, Register-Ed
	*/ 
	@Test
	private void AgeValidationRegisterEd() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify the corresponding min Age criteria is enforced on registration, Register-Ed"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify the corresponding min Age criteria is enforced on registration, Register-Ed"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
		RegisterEdEventView eventview = new RegisterEdEventView(driver);
		RegisterEdRegisterStudentPage registerstudent = new RegisterEdRegisterStudentPage(driver);
		InstructorHomePage instructorhome = new InstructorHomePage(driver);

		
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
			Thread.sleep(5000);

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

			//login.ClickOnLogoutButton();
			//Thread.sleep(5000);

			
		System.out.println("Step 7 : Go to Register-Ed and verify UI of first page of registration");
		Reporter.log("Step 7 : Go to Register-Ed and verify functionality of Register Now button"); 
		
			
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

			eventview.ClickOnRegisterNowButton();
			Thread.sleep(5000);

			//Verify Title
			expectedtext = "Student Registration";
			actualtext = registerstudent.Title.getText().trim();
		
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
				
				System.out.println("Success !! User is on Registration page.");
				Reporter.log("Success !! User is on Registration page.");
			
			}
			else
			{
			
				System.out.println("Failure !! User is NOT on Registration page." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! User is NOT on Registration page." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
						
			
			//Enter Valid Details and Go to 2nd Step
			String FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			String UserName="emstudent"+ JH.GenerateRandomNumber();
			String EmailAddress = UserName + "@mailinator.com";
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "2010", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickToSelectGender();
			
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerstudent.Next);			
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);

			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(5000);

			
			//Verify min age validation
			expectedtext = "You must be at least 12 years old on";
			actualtext = SeleniumFunc.GetElementText("css", "#dataRegistrationyear_errorMsg");
		
			if(actualtext.contains(expectedtext))
			{
				
				System.out.println("Success !! Correct Validation Message is displayed for age. i.e. " +actualtext);
				Reporter.log("Success !! Correct Validation Message is displayed for age. i.e. " +actualtext);
			
			}
			else
			{
			
				System.out.println("Failure !! Error while registration." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Error while registration." + "\n" + "Expected : "  
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
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);

				login.ClickOnLogInButton();			
				Thread.sleep(5000);

				//Going to Event Roster page and removing student and deleting  event 
				//so new event can be added with same test data
				instructorhome.VisitHomePage.click();
				Thread.sleep(5000);

				instructorhome.Action_MyEventSchedule.click();
				Thread.sleep(5000);

				//instructorhome.GoToNextMonth();
				instructorschedule.FirstEventName.click();
				Thread.sleep(5000);

				instructorschedule.popupRosterButton.click();
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
		

	/* Test 5: 
	 * Verify validation is enforced if student gets registered from EM
	 */ 	
	@Test
	private void AgeValidationEM() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify validation is enforced if student gets registered from EM"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify validation is enforced if student gets registered from EM"  + "\n" +
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
			Thread.sleep(5000);

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
			instructorcreateevent.EnterEventCapacity("1");
			instructorcreateevent.EnterWaitListCapacity("1");
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

		  	
		System.out.println("Step 6 : Search and Activate event");
		Reporter.log("Step 6 : Search and Activate event"); 			
		  	
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
			Thread.sleep(5000);
			
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

			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			
		System.out.println("Step 7 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 7 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
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

			//instructorhome.GoToNextMonth();
			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);

			instructorschedule.popupRosterButton.click();
			Thread.sleep(5000);

			instructoreventroster.SelectAction("Add Student");
			Thread.sleep(5000);*/
			instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");

			Thread.sleep(2000);
			
			SeleniumFunc.ClickOnElement("linkText", "Add Student");
			

			String username="emstudent"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "2010", "street 3", "Norflok", "9898989898", "12345");
			addstud.SelectEyeColor();
			addstud.SelectHairColor();
			addstud.EnterSSNNO("125896336");
			addstud.ClickOnRegisterButton();
			Thread.sleep(5000);

	
			//Verify min age validation
			expectedtext = "Student must be at least 12 years old on";
			actualtext = SeleniumFunc.GetElementText("css", ".help-block.error");
		
			if(actualtext.contains(expectedtext))
			{
				
				System.out.println("Success !! Correct Validation Message is displayed for age. i.e. " +actualtext);
				Reporter.log("Success !! Correct Validation Message is displayed for age. i.e. " +actualtext);
			
			}
			else
			{
			
				System.out.println("Failure !! Error while registration." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Error while registration." + "\n" + "Expected : "  
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
				//Deleting event so new event can be added with same test data 
				SeleniumFunc.ToGoToUrl(URL);
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
	
	
	/* Test 6: 
	 * Verify min age policy is displayed on Event Details page
	*/ 
	@Test
	private void VerifyMinAgePolicyAtEventDetails() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify minimum age message is displayed under DOB field"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Verify minimum age message is displayed under DOB field"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
		RegisterEdEventView eventview = new RegisterEdEventView(driver);
		InstructorHomePage instructorhome = new InstructorHomePage(driver);

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
			Thread.sleep(5000);

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

			//login.ClickOnLogoutButton();
			
			//Thread.sleep(5000);

		System.out.println("Step 7 : Go to Register-Ed and verify Min age policy");
		Reporter.log("Step 7 : Go to Register-Ed and verify Min age policy"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);
			Thread.sleep(5000);

			//Minimum Age Policy
			expectedtext = "New minimum Age 11 years Policy is implemented";
			actualtext = eventview.MinAgePolicy.getText().trim() ;
			
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Minimum-Age Policy is displaying properly.");
				Reporter.log("Success !! Minimum-Age Policy is displaying properly.");
			
			}
			else
			{
			
				System.out.println("Failure !! Minimum-Age Policy is NOT displaying properly" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Minimum-Age Policy is NOT displaying properly" + "\n" + "Expected : "  
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
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);
				login.ClickOnLogInButton();			
				Thread.sleep(5000);

				
				//Going to Event Roster page and removing student and deleting  event 
				//so new event can be added with same test data
				instructorhome.VisitHomePage.click();
				Thread.sleep(5000);

				instructorhome.Action_MyEventSchedule.click();
				Thread.sleep(5000);

				//instructorhome.GoToNextMonth();
				instructorschedule.FirstEventName.click();
				Thread.sleep(5000);

				instructorschedule.popupRosterButton.click();
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

	
	/* Test 7: 
	 * Verify user can set min age policy on EM
	*/ 
	@Test
	private void MinimumAgePolicy() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Verify user can set min age policy on EM"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Verify user can set min age policy on EM"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		EventSettingsPage eventsettings = new EventSettingsPage(driver);
		AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		InstructorHomePage instructorhome = new InstructorHomePage(driver);
		RegisterEdEventView eventview = new RegisterEdEventView(driver);

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
			Thread.sleep(5000);

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
			instructorcreateevent.EnterEventCapacity("1");
			instructorcreateevent.EnterWaitListCapacity("0");
			
			instructorcreateevent.EnterSpecialInstruction("Children under the age of 12 must be accompanied by an adult.");
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

						
		System.out.println("Step 8 : Set Minimum age policy");
		Reporter.log("Step 8 : Set Minimum age policy");
							
							
			eventsettings.EnterMinAgePolicy("New minimum Age 11 years Policy is implemented");
			eventsettings.ClickOnSaveChanges();  	
			Thread.sleep(5000);

								
		System.out.println("Step 9 : Search and Activate event");
		Reporter.log("Step 9 : Search and Activate event"); 			
		  	
			
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");

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

			//login.ClickOnLogoutButton();
			//Thread.sleep(5000);

		
		System.out.println("Step 10 : Go to Register-Ed and verify Min age policy");
		Reporter.log("Step 10 : Go to Register-Ed and verify Min age policy"); 
			
				
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

				
			//Minimum Age Policy
			expectedtext = "New minimum Age 11 years Policy is implemented";
			actualtext = eventview.AgePolicy.getText().trim() ;
				
			if(actualtext.contains(expectedtext))
			{
				
				System.out.println("Success !! Minimum-Age Policy is displaying properly.");
				Reporter.log("Success !! Minimum-Age Policy is displaying properly.");
				
			}
			else
			{
				
				System.out.println("Failure !! Minimum-Age Policy is NOT displaying properly" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Minimum-Age Policy is NOT displaying properly" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							"Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
				
			}
				
			/*
			try {
				//Login as instructor to delete event
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				Thread.sleep(5000);

				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);
				login.ClickOnLogInButton();			
				Thread.sleep(5000);

					
				//Going to Event Roster page and removing student and deleting  event 
				//so new event can be added with same test data
				instructorhome.VisitHomePage.click();
				Thread.sleep(5000);

				instructorhome.Action_MyEventSchedule.click();
				Thread.sleep(5000);

				//instructorhome.GoToNextMonth();
				instructorschedule.FirstEventName.click();
				Thread.sleep(5000);

				instructorschedule.popupRosterButton.click();
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
}
