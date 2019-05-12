package products.EM;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod; 
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.EM.AdminSearchEventPage;
import pageFactory.EM.AgencyHomePage;
import pageFactory.EM.CreateQuestionPage;
import pageFactory.EM.EMAddStudentPage;
import pageFactory.EM.EditStudentPage;
import pageFactory.EM.ErrorPage;
import pageFactory.EM.InstructorCreateEventPage;
import pageFactory.EM.InstructorEventRosterPage;
import pageFactory.EM.InstructorHomePage;
import pageFactory.EM.InstructorLocationPage;
import pageFactory.EM.InstructorSchedulePage;
import pageFactory.EM.LoginPage;
import pageFactory.EM.StudentResultPage;
import pageFactory.EM.StudentSearchPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;
import org.openqa.selenium.JavascriptExecutor;

public class ManageStudentTest 
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
	 * Verify Register a student via EM

	*/ 
	@Test
	private void EM_RegisterStudent() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify Register a student via EM"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify Register a student via EM"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		InstructorHomePage instructorhome = new InstructorHomePage(driver);
		AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
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
				
				instructorcreateevent.CreateEvent.click();
				Thread.sleep(5000);
				
				String URL = driver.getCurrentUrl();
				String EventID = instructoreventroster.EventId.getText().trim();
				System.out.println("Event Created : "+EventID);
				
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

			
		System.out.println("Step 8 : Verify add student option is present on Event Roster page, and user can add the student");
		Reporter.log("Step 8 : Verify add student option is present on Event Roster page, and user can add the student");
		
			/*
    		instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();
			Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);*/
		
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");
			Thread.sleep(2000);
			
			String Actual = SeleniumFunc.GetElementText("css", ".nav.nav-tabs.nav-stacked.nav-secondary li:nth-of-type(1) a");
			
			if(Actual.equalsIgnoreCase("Add Student"))
			{
			
				System.out.println("Success !! Add Student link is present");
				Reporter.log("Success !! Add Student link is present");
			
			}
			else
			{
			
				System.out.println("Failure !! Add Student link is missing");
				Reporter.log("Failure !! Add Student link is missing");
				
				AssertFailedCount++;
			
			}
			
			Thread.sleep(5000);

			instructoreventroster.SelectAction("Add Student");
			Thread.sleep(5000);
			String username="addstud"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
			addstud.SelectEyeColor();
			addstud.SelectHairColor();
			addstud.EnterSSNNO("125896336");
			addstud.ClickOnRegisterButton();
			Thread.sleep(5000);

			
			//Verify student is added successfully
			expectedtext = "User, Clarion";
			actualtext = SeleniumFunc.GetElementText("css", ".checkbox.checkbox-sidelong>a");
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Student is added with correct Name");
				Reporter.log("Success !! Student is added with correct Name");
			
			}
			else
			{
			
				System.out.println("Failure !! Student is added with incorrect Name" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Student is added with incorrect Name" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
				
		
		//Going to Event Roster page and removing student and deleting  event 
		//so new event can be added with same test data
			
			/*
			try {
				instructoreventroster.ClickOnSelectEventCheckBox();
				instructoreventroster.SelectEventAction();
				instructoreventroster.ClickOnGoButton();
				Thread.sleep(5000);

				instructoreventroster.ConfirmStudentRemove.click();
				Thread.sleep(5000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				
				Thread.sleep(5000);
			} catch (Exception e) {
				//  Auto-generated catch block
				
			}*/
			
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(5000);
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("linkText", "Unschedule & Remove");
			
			Thread.sleep(2000);
		
			SeleniumFunc.ClickOnElement("linkText", "Unschedule");

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
	 * Verify Invite a student via EM
	*/ 
	@Test
	private void EM_InviteStudent() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify Invite a student via EM"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify Invite a student via EM"  + "\n" +
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

			
		System.out.println("Step 8 : Verify Invite Student option is present on Event Roster page, and user can add the student");
		Reporter.log("Step 8 : Verify Invite Student option is present on Event Roster page, and user can add the student");
		
			/*
		    instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();
			Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			instructorschedule.FirstEventName.click();			
			Thread.sleep(5000);*/
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");
			Thread.sleep(2000);
			
			String Actual = SeleniumFunc.GetElementText("css", ".nav.nav-tabs.nav-stacked.nav-secondary li:nth-of-type(2) a");
			
			if(Actual.equalsIgnoreCase("Invite Student"))
			{
			
				System.out.println("Success !! Invite Student link is present");
				Reporter.log("Success !! Invite Student link is present");
			
			}
			else
			{
			
				System.out.println("Failure !! Invite Student link is missing");
				Reporter.log("Failure !! Invite Student link is missing");
				
				AssertFailedCount++;
			
			}
			
			
			instructoreventroster.SelectAction("Invite Student");
			Thread.sleep(5000);

		
			
			//Verify user is on invite student page
			expectedtext = "Send Invitations";
			actualtext = SeleniumFunc.GetElementText("css", "#invite_form > div.form-actions > button");
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! User is on Invite Student page");
				Reporter.log("Success !! User is on Invite Student page");
			
			}
			else
			{
			
				System.out.println("Failure !! User is not redirected to Invite Student page" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! User is not redirected to Invite Student page" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
				
		
		//Going to Event Roster page and deleting  event 
		//so new event can be added with same test data
			
			/*
			try {
				instructoreventroster.ReturnToRosterFromInvitation.click();
				Thread.sleep(5000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				
				Thread.sleep(5000);
			} catch (Exception e) {
				//  Auto-generated catch block
				
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
	 * Verify system is allowing values that are acceptable to duplicate for student result
	*/ 
	
	@Test
	private void ResultAllowDuplicateValue() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify system is allowing values that are acceptable to duplicate for student result"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify system is allowing values that are acceptable to duplicate for student result"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
		EMAddStudentPage addstud = new EMAddStudentPage(driver);
		StudentResultPage studentresult = new StudentResultPage(driver);
		InstructorHomePage instructorhome = new InstructorHomePage(driver);

		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_NYInstructor_Username);
			login.EnterPassword(Constants.EM_NYInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			//Navigate to 'My Event Schedule' page and delete all Events
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//instructorschedule.DeleteAllEvents();
			
		System.out.println("Step 2 : Navigate to Create Event page via Location page ");
		Reporter.log("Step 2 : Navigate to Create Event page via Location page  "); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			instructorlocations.SelectProgram(3);	
			Thread.sleep(5000);

			instructorlocations.ScheduleAnEvent.click();	
			Thread.sleep(5000);
				
		
		System.out.println("Step 3 : On Create Event page , entering all required details and click on 'Create Event' button");
		Reporter.log("Step 3 : On Create Event page , entering all required detailsb and click on 'Create Event' button"); 
		
			instructorcreateevent.SelectProgram();
			Thread.sleep(2000);
				
			instructorcreateevent.Location.clear();
			instructorcreateevent.Location.sendKeys("Demo Location for New York");
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
			System.out.println("Url : "+URL);
			String EventID = instructoreventroster.EventId.getText().trim();
			System.out.println("Event Created : "+EventID);
			
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
			expectedtext = "New York Hunter Education Course";
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
			login.EnterUsername(Constants.EM_NYInstructor_Username);
			login.EnterPassword(Constants.EM_NYInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/panel/proctor");
			Thread.sleep(5000);

			//Add Student for event
			//instructorhome.Action_MyEventSchedule.click();	
			//Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			//instructorschedule.FirstEventName.click();
			
			SeleniumFunc.ToGoToUrl(URL);
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");
			Thread.sleep(5000);
			instructoreventroster.SelectAction("Add Student");
			String username="emstudent"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "New York",  "African American", "January", "1", "1990", "street 3", "Long Island City", "9898989898", "23324");
			//addstud.EnterSSNNO("123456789");
			addstud.SelectCounty("Albany");
			//addstud.SelectEyeColor();
			//addstud.SelectHairColor();
			addstud.ClickOnRegisterButton();
			Thread.sleep(5000);
					
			//Add Result
			instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);

			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");	
			
			SeleniumFunc.ClickOnElement("css", "#result-subnav div:nth-of-type(3) a");
			Thread.sleep(5000);
			
			WebElement ele = driver.findElement(By.cssSelector(".controls label:nth-of-type(4) input:nth-of-type(1)"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);

			//SeleniumFunc.EnterValueInTextbox("css", ".results-t-certificate-id", "No Show")
			//SeleniumFunc.EnterValueInTextbox("css", "input[class*='certificate-id']", "No Show");
			SeleniumFunc.ClickOnElement("css", "input[id$='No Show']");
			SeleniumFunc.ClickOnElement("css", ".btn.btn-primary.pull-right");
			Thread.sleep(5000);

			//Verify Student Result is completed
			
			actualtext = studentresult.StudentResultStatus.getText().trim();
			expectedtext = "Complete!";
			

			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Student result is added and completed");
				Reporter.log("Success !! Student result is added and completed");
			}
			else
			{
				System.out.println("Failure !! Student result is not added and completed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Student result is not added and completed" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
				
		/*	
		//Going to Event Roster page and removing student and deleting  event 
		//so new event can be added with same test data
			try {
				instructoreventroster.ReturnToRosterFromResult.click();
				Thread.sleep(5000);

				instructoreventroster.ClickOnSelectEventCheckBox();
				instructoreventroster.SelectEventAction();
				instructoreventroster.ClickOnGoButton();
				Thread.sleep(5000);

				instructoreventroster.ConfirmStudentRemove.click();
				Thread.sleep(5000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				
				Thread.sleep(5000);
			} catch (Exception e) {
				//  Auto-generated catch block
				e.printStackTrace();
			}*/
			
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(5000);
			
			SeleniumFunc.ClickOnElement("linkText", "Cancel Event");
			
			Thread.sleep(2000);
			
			
			SeleniumFunc.ClickOnElement("linkText", "Unschedule and Remove Event");

			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("scroll(990,774)");
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



	/* Test 4: 
	 * Verify system is not allowing values that are duplicate for student result i.e.H Certificate ID
	*/ 
	
	@Test
	private void ResultPreventDuplicateValue() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify system is not allowing values that are duplicate for student result i.e.H Certificate ID"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify system is not allowing values that are duplicate for student result i.e.H Certificate ID"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
		EMAddStudentPage addstud = new EMAddStudentPage(driver);
		StudentResultPage studentresult = new StudentResultPage(driver);
		InstructorHomePage instructorhome = new InstructorHomePage(driver);

		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_NYInstructor_Username);
			login.EnterPassword(Constants.EM_NYInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			//Navigate to 'My Event Schedule' page and delete all Events
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//instructorschedule.DeleteAllEvents();
			
		System.out.println("Step 2 : Navigate to Create Event page via Location page ");
		Reporter.log("Step 2 : Navigate to Create Event page via Location page  "); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			Thread.sleep(5000);

			instructorlocations.SelectProgram(2);	
			Thread.sleep(5000);

			instructorlocations.ScheduleAnEvent.click();	
			Thread.sleep(5000);
				
		
		System.out.println("Step 3 : On Create Event page , entering all required details and click on 'Create Event' button");
		Reporter.log("Step 3 : On Create Event page , entering all required detailsb and click on 'Create Event' button"); 
		
			instructorcreateevent.SelectProgram();
			Thread.sleep(2000);
			
			instructorcreateevent.Location.clear();
			instructorcreateevent.Location.sendKeys("Demo Location for New York");
			Thread.sleep(5000);
			instructorcreateevent.Location_FirstChoice.click();
			Thread.sleep(2000);
		
			//instructorcreateevent.GoToNextMonth();
			//instructorcreateevent.SelectDate("5");	
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
			
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
			
			instructorcreateevent.CreateEvent.click();
			Thread.sleep(5000);
			
			String URL = driver.getCurrentUrl();
			String EventID = instructoreventroster.EventId.getText().trim();
			System.out.println("Event Created : "+EventID);
			
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
			expectedtext = "New York Hunter Education Course";
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
			login.EnterUsername(Constants.EM_NYInstructor_Username);
			login.EnterPassword(Constants.EM_NYInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/panel/proctor");
			//Add Student for eventdd
			//instructorhome.Action_MyEventSchedule.click();
			//Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			//instructorschedule.FirstEventName.click();
			//Thread.sleep(5000);
			
			//Add Student for event
			
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(4000);
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");
			Thread.sleep(5000);
			
			instructoreventroster.SelectAction("Add Student");
			Thread.sleep(5000);

			String username="emstudent"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "New York",  "African American", "January", "1", "1990", "street 3", "Long Island City", "9898989898", "23324");
			addstud.SelectCounty("Albany");
			addstud.ClickOnRegisterButton();
			Thread.sleep(5000);		
					
			//Add Result
			instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);

			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");	
			
			SeleniumFunc.ClickOnElement("css", "#result-subnav div:nth-of-type(3) a");
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("css", ".controls label:nth-of-type(1) input:nth-of-type(1)");
			Thread.sleep(5000);

			SeleniumFunc.EnterValueInTextbox("css", "input[class*='certificate-id']", "AB000123");
			SeleniumFunc.ClickOnElement("css", ".btn.btn-primary.pull-right");
			Thread.sleep(5000);
			SeleniumFunc.ToGoToUrl(URL);
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");
			//Add Second Student
			instructoreventroster.SelectAction("Add Student");
			Thread.sleep(5000);

			username="emstudent"+ JH.GenerateRandomNumber();
			emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "New York",  "African American", "January", "1", "1990", "street 3", "Long Island City", "9898989898", "23324");
			addstud.SelectCounty("Albany");
			addstud.ClickOnRegisterButton();
			Thread.sleep(5000);

			if(SeleniumFunc.IsElementPresent("css", ".help-block.error"))
			{
				System.out.println("Success !! Duplicate Student is not added");
				Reporter.log("Success !! Duplicate Student is not added");
			}
			else
			{
				System.out.println("Failure !! Duplicate Student is added");
				Reporter.log("Failure !! Duplicate Student is added");
				
				AssertFailedCount++;
			}
			
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(4000);
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");
			Thread.sleep(5000);
			
			//Add Result
			instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);

			jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");	
			
			SeleniumFunc.ClickOnElement("css", "#result-subnav > div:nth-child(2) > div:nth-child(3) > div:nth-child(2) > a");
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("css", ".controls label:nth-of-type(1) input:nth-of-type(1)");
			Thread.sleep(5000);

			SeleniumFunc.EnterValueInTextbox("css", "input[class*='certificate-id']", "AB000123");
			Thread.sleep(5000);
			
			if(SeleniumFunc.IsElementPresent("css", ".help-block.error"))
			{
				System.out.println("Success !! Student result is not added with duplicate H Certificate ID");
				Reporter.log("Success !! Student result is not added with duplicate H Certificate ID");
			}
			else
			{
				System.out.println("Failure !! Student result is added with duplicate H Certificate ID");
				Reporter.log("Failure !! Student result is added with duplicate H Certificate ID");
				
				AssertFailedCount++;
			}

			SeleniumFunc.ClickOnElement("css", ".btn.btn-primary.pull-right");
			//SeleniumFunc.ClickOnElement("css", ".btn.btn-primary.pull-right");
			Thread.sleep(3000);
			
			//Verify Student Result is incomplete
			
			actualtext = studentresult.StudentResultStatus.getText().trim();
			expectedtext = "Incomplete";
			

			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Student result is not added and incomplete");
				Reporter.log("Success !! Student result is not added and incomplete");
			}
			else
			{
				System.out.println("Failure !! Student result is added and completed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Student result is added and completed" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
				

			//Verify validation message for duplicate entry for H Certificate ID
			
			jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");	
			
			SeleniumFunc.ClickOnElement("css", "#result-subnav > div:nth-child(2) > div:nth-child(3) > div:nth-child(2) > a");
			Thread.sleep(5000);

			Thread.sleep(5000);

			
			
			
		/*	
		//Going to Event Roster page and removing student and deleting  event 
		//so new event can be added with same test data
			try {
				SeleniumFunc.ClickOnElement("css", ".btn.btn-primary.pull-right");
				Thread.sleep(5000);

				instructoreventroster.ReturnToRosterFromResult.click();
				Thread.sleep(5000);

				instructoreventroster.ClickOnSelectEventCheckBox();
				instructoreventroster.SelectEventAction();
				instructoreventroster.ClickOnGoButton();
				Thread.sleep(5000);

				instructoreventroster.ConfirmStudentRemove.click();
				Thread.sleep(5000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				
				Thread.sleep(5000);
			} catch (Exception e) {
				//  Auto-generated catch block
				e.printStackTrace();
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
	 * Verify User can search student via EM
	*/ 
	
	@Test
	private void SearchStudentViaEM() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify User can search student via EM"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify User can search student via EM"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		StudentSearchPage studentsearch= new StudentSearchPage(driver);
		EditStudentPage editstudent = new EditStudentPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		InstructorHomePage instructorhome = new InstructorHomePage(driver);
		AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
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
				
			instructorcreateevent.CreateEvent.click();
			Thread.sleep(5000);
			String URL = driver.getCurrentUrl();
			String EventID = instructoreventroster.EventId.getText().trim();
			System.out.println("Event created : "+EventID);
				
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

			
		System.out.println("Step 8 : Verify add student option is present on Event Roster page, and user can add the student");
		Reporter.log("Step 8 : Verify add student option is present on Event Roster page, and user can add the student");
		
			/*
			instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();
			Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);*/
			
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");
			Thread.sleep(2000);
		//	SeleniumFunc.ClickOnElement("css", ".btn.mtt");
			Thread.sleep(2000);

			actualtext = SeleniumFunc.GetElementText("css", ".nav.nav-tabs.nav-stacked.nav-secondary li:nth-of-type(1) a");
			
			if(actualtext.equalsIgnoreCase("Add Student"))
			{
				System.out.println("Success !! Add Student link is present");
				Reporter.log("Success !! Add Student link is present");
			}
			else
			{
				System.out.println("Failure !! Add Student link is missing");
				Reporter.log("Failure !! Add Student link is missing");
				
				AssertFailedCount++;
			}
			
			Thread.sleep(5000);

			instructoreventroster.SelectAction("Add Student");
			Thread.sleep(5000);
			String username="addstud"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
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
			//Verify student is added successfully
			expectedtext = "User, Clarion";
			actualtext = SeleniumFunc.GetElementText("css", ".checkbox.checkbox-sidelong>a");
		
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Student is added with correct Name");
				Reporter.log("Success !! Student is added with correct Name");
			}
			else
			{
				System.out.println("Failure !! Student is added with incorrect Name" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Student is added with incorrect Name" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
	
			
			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			
		System.out.println("Step 9 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 9 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 10 : Select agency from agency listing");
		Reporter.log("Step 10 : Select agency from agency listing"); 
								
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);
		
		System.out.println("Step 11 : Select a program and go to search student");
		Reporter.log("Step 11 : Select a program and go to search student"); 
				
		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);

		  	agencyhome.ClickOnSearchStudentLink();
			Thread.sleep(5000);

		  	
		System.out.println("Step 12 : Verify page title");
		Reporter.log("Step 12 : Verify page title");
		
			actualtext = studentsearch.Title.getText().trim();
			expectedtext = "Student Search";
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Page title is correct");
				Reporter.log("Success !! Page title is correct");
			}
			else
			{
				System.out.println("Failure !! Page title with incorrect text" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Page title with incorrect text" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
		  	
		System.out.println("Step 13 : Search a student with valid details");
		Reporter.log("Step 13 : Search a student with valid details");
		
			Thread.sleep(4000);
			studentsearch.SelectSearchTypeByVisibleText("Email");
			Thread.sleep(4000);

			studentsearch.EnterSearchData(emailaddress);
			Thread.sleep(4000);

			studentsearch.ClickOnSearchButton();
			Thread.sleep(5000);

			
		System.out.println("Step 14 : Edit the student details and verify updated");
		Reporter.log("Step 14 : Edit the student details and verify updated");
		
			studentsearch.EditFirstRecord();
			Thread.sleep(5000);
			SeleniumFunc.ClickOnElement("linkText", "Edit");
			
			String Firstname = "EditStudentDetails";
			String LastName = "UpdatedDetails";
			
			addstud.editFirstName.clear();
			addstud.editFirstName.sendKeys(Firstname);
			Thread.sleep(2000);
			addstud.editLastName.clear();
			addstud.editLastName.sendKeys(LastName);
			//SeleniumFunc.EnterValueInTextbox("id", "Registration_first_name", Firstname);
			//editstudent.EditFirstName(Firstname);
			//SeleniumFunc.EnterValueInTextbox("id", "Registration_last_name", LastName);
			//editstudent.EditLastName(LastName);
			SeleniumFunc.ClickOnElement("css", ".btn.btn-success");
			//editstudent.ClickOnSubmitButton();
			Thread.sleep(4000);

			
		//Verify Updated Details
			SeleniumFunc.ClickOnElement("linkText", "Edit");
			//editstudent.ClickOnEditButton();
			Thread.sleep(4000);

			String ActualFN = editstudent.RegFirstName.getAttribute("value").trim();
			String ActualLN = editstudent.RegLastName.getAttribute("value").trim();
			SeleniumFunc.ClickOnElement("css", ".btn.btn-success");
			//editstudent.ClickOnSubmitButton();
			Thread.sleep(3000);

			if(ActualFN.equals(Firstname) && ActualLN.equals(LastName))
			{
				System.out.println("Success !! Student details updated properly");
				Reporter.log("Success !! Student details updated properly");
			}
			else
			{
				System.out.println("Failure !! Student details not updated properly" + "\n" + "Expected : "  
									+ "\n" + Firstname +" "+ LastName + "\n" + 
									 "Actual: " + "\n" +  ActualFN +" "+ ActualLN);
				Reporter.log("Failure !! Student details not updated properly" + "\n" + "Expected : "  
						+ "\n" + Firstname + " "+ LastName + "\n" + 
						 "Actual: " + "\n" +  ActualFN +" "+ ActualLN);
				
				AssertFailedCount++;
			}
			
			/*
			try {
				login.ClickOnLogoutButton();
				Thread.sleep(5000);

				//Login as Instructor					
				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);
				login.ClickOnLogInButton();		
				Thread.sleep(5000);

				//Deleting event so new event can be added with same test data 
				instructorhome.VisitHomePage.click();
				Thread.sleep(5000);

				instructorhome.Action_MyEventSchedule.click();
				Thread.sleep(5000);

				//instructorhome.GoToNextMonth();
				instructorschedule.FirstEventName.click();
				Thread.sleep(5000);

				instructoreventroster.ClickOnSelectEventCheckBox();
				instructoreventroster.SelectEventAction();
				instructoreventroster.ClickOnGoButton();
				Thread.sleep(5000);

				instructoreventroster.ConfirmStudentRemove.click();
				Thread.sleep(5000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				Thread.sleep(5000);

			} catch (Exception e) {
				//  Auto-generated catch block
				
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
	
	
	
	
	/* Test 6: 
	 * Class Type Run
	*/ 	
	@Test
	private void ClassTypeRun() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 :Class Type Run"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Class Type Run"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		InstructorHomePage instructorhome = new InstructorHomePage(driver);
		CreateQuestionPage createquestion = new CreateQuestionPage(driver);
		StudentResultPage studresult = new StudentResultPage(driver);		
		AgencyHomePage agencyhome= new AgencyHomePage(driver);

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
			instructorcreateevent.EnterEventCapacity("2");
			instructorcreateevent.EnterWaitListCapacity("0");
			
			instructorcreateevent.EnterSpecialInstruction("Children under the age of 12 must be accompanied by an adult.");

			instructorcreateevent.CreateEvent.click();
			Thread.sleep(5000);

			String URL = driver.getCurrentUrl();
			String EventID = instructoreventroster.EventId.getText().trim();
			System.out.println("Event created : "+EventID);
			
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

			
		System.out.println("Step 7 : Select Manage Details and go to Create Question");
		Reporter.log("Step 7 : Select Manage Details and go to Create Question"); 
				
			
		  	//SeleniumFunc.ClickOnElement("css", "#actionDetails");
		  	//changed due to ui changes 
		
			SeleniumFunc.ClickOnElement("linkText", "Detail Questions");
		    //createquestion.ClickOnManageDetails();
			Thread.sleep(5000);

			createquestion.ClickOnCreateQuestionLink();
			Thread.sleep(5000);

			
			//Enter Question Details
			createquestion.SelectTarget("Event Report");
			createquestion.EnterQuestionName("class type");
			createquestion.EnterQuestionKey("class_type");
			createquestion.EnterQuestionDescri("class type");
			createquestion.SelectFieldType("text");
			createquestion.ClickOnCreateQuestionButton();
			Thread.sleep(5000);

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			
			
		System.out.println("Step 8 : Verify question is created");	
		Reporter.log("Step 8 : Verify question is created"); 
				
		
			expectedtext = "https://my-webtest1.register-ed.com/detailquestion";
			actualtext = driver.getCurrentUrl();
		
			if(expectedtext.equals(actualtext))
			{
				
				System.out.println("Success !! Question is created");
				Reporter.log("Success !! Question is created");
				
			}
			else
			{
			
				System.out.println("Failure !! Question is NOT created" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Question is NOT created" + "\n" + "Expected : "  
					+ "\n" + expectedtext + "\n" + 
					 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			
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

		
		System.out.println("Step 10 : Go to Report Details under the Configuration section and add question scope");
		Reporter.log("Step 10 : Go to Report Details under the Configuration section and add question scope"); 			
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");		
			Thread.sleep(5000);

			agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);

			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/result/viewreportdetails");
			Thread.sleep(10000);
			SeleniumFunc.SelectValueFromDropdownList("css", "#question_add_form select", "class type (Event)");
			SeleniumFunc.ClickOnElement("css", "#question_add_form span");
			Thread.sleep(5000);

			login.ClickOnLogoutButton();
			Thread.sleep(5000);

	
		System.out.println("Step 11 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 11 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
			
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
		
			//instructorhome.VisitHomePage.click();	
			//Thread.sleep(5000);

			//instructorhome.Action_MyEventSchedule.click();	
			//Thread.sleep(5000);

			//instructorschedule.FirstEventName.click();		
			//Thread.sleep(5000);
			
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(5000);
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");
			Thread.sleep(2000);
			//Enter Result
			instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);

		System.out.println("Step 12 : Verify fields for new question is available");
		Reporter.log("Step 12 : Verify fields for new question is available"); 
			
				studresult.ClickOnEditEventInfoButton();
				Thread.sleep(5000);

				if(SeleniumFunc.IsElementPresent("css", "#ReportDetail_class_type"))
				{
					System.out.println("Success !! Newly Added question is present");
					Reporter.log("Success !! Newly Added question is present");
				}
				else
				{
					System.out.println("Failure !! Newly Added question is NOT present");
					Reporter.log("Failure !! Newly Added question is NOT present");
					
					AssertFailedCount++;
				}
					
		
			/*
				try {
					//Delete event
					instructoreventroster.ReturnToRosterFromResult.click();
					Thread.sleep(5000);

					instructoreventroster.EventDelete.click();
					Thread.sleep(5000);

					instructoreventroster.Confirm_EventDelete.click();
					Thread.sleep(5000);
					
					
					//Set question to default
					login.ClickOnLogoutButton();
					Thread.sleep(5000);

					login.EnterUsername(Constants.EM_Admin_Username);
					login.EnterPassword(Constants.EM_Admin_Password);
					login.ClickOnLogInButton();
					Thread.sleep(5000);

					SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
					Thread.sleep(5000);

					//SeleniumFunc.ClickOnElement("css", "#actionDetails");
				    createquestion.ClickOnManageDetails();
					Thread.sleep(5000);

					SeleniumFunc.ClickOnElement("xpath", "//td[contains(text(), 'class type')]/parent::tr/td/a[2]");	  	 	
					Thread.sleep(2000);
					SeleniumFunc.AcceptAlertAndGetText();
					Thread.sleep(2000);
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

	
	
	
	/* Test 1: 
	 * EM > Test Zipcode/City/State Autofill
	*/ 
	@Test
	private void CityStateAutofillZipcode() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify Register a student via EM"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify Register a student via EM"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		InstructorHomePage instructorhome = new InstructorHomePage(driver);
		AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
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

			
		System.out.println("Step 8 : Verify add student option is present on Event Roster page, and user can add the student");
		Reporter.log("Step 8 : Verify add student option is present on Event Roster page, and user can add the student");
		
			/*
    		instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();
			Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);

			
			Thread.sleep(5000);

			instructoreventroster.SelectAction("Add Student");*/
		
			instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

		
		
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");

		
			Thread.sleep(2000);
		
			SeleniumFunc.ClickOnElement("linkText", "Add Student");

			Thread.sleep(5000);
			
			addstud.EnterZip("75244");
			addstud.EnterAddress("Test");
			Thread.sleep(2000);
			addstud.EnterPhNo("2345678910");
			//Verify City is auto-filled as per zip code
			expectedtext = "Dallas";
			actualtext = addstud.City.getAttribute("value");
		
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! City is auto-filled as per Zip code");
				Reporter.log("Success !! City is auto-filled as per Zip code");	
			}
			else
			{		
				System.out.println("Failure !! City is NOT auto-filled as per Zip code" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! City is NOT auto-filled as per Zip code" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								"Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			//Verify State is auto-filled as per zip code
			expectedtext = "Texas";
			actualtext = SeleniumFunc.GetSelectedValueFromDropdownList("css", "#Registration_p_state_cd");
					
		
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! State is auto-filled as per Zip code");
				Reporter.log("Success !! State is auto-filled as per Zip code");	
			}
			else
			{		
				System.out.println("Failure !! State is NOT auto-filled as per Zip code" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! State is NOT auto-filled as per Zip code" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								"Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
		//Going to Event Roster page and removing student and deleting  event 
		//so new event can be added with same test data
			/*
			try {
				addstud.Cancel.click();
				Thread.sleep(4000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				
				Thread.sleep(5000);
			} catch (Exception e) {
				//  Auto-generated catch block
				
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
