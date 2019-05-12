package products.EM;

 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Sleeper;
import org.sikuli.script.Location;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.EM.AdminSearchEventPage;
import pageFactory.EM.AgencyLocationPage;
import pageFactory.EM.EMAddStudentPage;
import pageFactory.EM.ErrorPage;
import pageFactory.EM.InstructorCreateEventPage;
import pageFactory.EM.InstructorEditEventPage;
import pageFactory.EM.InstructorEnrollmentsPage;
import pageFactory.EM.InstructorEventRosterPage;
import pageFactory.EM.InstructorLocationPage;
import pageFactory.EM.InstructorManagePage;
import pageFactory.EM.InstructorSchedulePage;
import pageFactory.EM.LoginPage;
import pageFactory.EM.PaymentSettingsPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class EventsTest 
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
	 * Event > Instructor : Verify that mandatory fields validation messages are displayed while creating new Event
	*/ 
	@Test
	private void Event_Create_VerifyMandatoryFieldValidations() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Event > Instructor : Verify that mandatory fields validation messages are displayed while creating new Event"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Event > Instructor : Verify that mandatory fields validation messages are displayed while creating new Event"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
	
		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			
		System.out.println("Step 2 : Navigate to Create Event page via Location page ");
		Reporter.log("Step 2 : Navigate to Create Event page via Location page  "); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			instructorlocations.SelectProgram(2);	
			Thread.sleep(5000);

			instructorlocations.ScheduleAnEvent.click();	
			Thread.sleep(5000);
				
		
		System.out.println("Step 3 : On Create Event page , selecting Program and click on 'Create Event' button");
		Reporter.log("Step 3 : On Create Event page , selecting Program and click on 'Create Event' button"); 
		
				instructorcreateevent.SelectProgram();
				Thread.sleep(2000);
				
				instructorcreateevent.Location.clear();
				instructorcreateevent.Location.sendKeys("Clarion");
				Thread.sleep(2000);
				instructorcreateevent.Location_FirstChoice.click();
				Thread.sleep(2000);
				
				instructorcreateevent.CreateEvent.click();
				Thread.sleep(2000);

				
				//Verifying validation message for Event Date 
				String expectedtext = "Please set the date and time";
				String actualtext = SeleniumFunc.AcceptAlertAndGetText(); 
			
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success !! Correct validation message for Event Date is displayed");
					Reporter.log("Success !! Correct validation message for Event Date is displayed");
				}
				else
				{
					System.out.println("Failure !! Incorrect validation message for Event Date is displayed" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! Inorrect validation message for Event Date is displayed" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
		System.out.println("Step 5 : Selecting Start&End date for Event and clicking on 'Create Event' button ");
		Reporter.log("Step 5 : Selecting Start&End date for Event and clicking on 'Create Event' button ");
		
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
			
			instructorcreateevent.CreateEvent.click();
			Thread.sleep(5000);

			//Verifying validation message for Event Date 
			expectedtext = "Please set the date and time";
			actualtext = SeleniumFunc.AcceptAlertAndGetText(); 
		
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Correct validation message for Event Date is displayed");
				Reporter.log("Success !! Correct validation message for Event Date is displayed");
			}
			else
			{
				System.out.println("Failure !! Incorrect validation message for Event Date is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Inorrect validation message for Event Date is displayed" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
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

	
	/* Test 2: 
	 * Event > Instructor : Verify that user can create Event successfully (Navigate to Create an Event page from Location page)
	*/ 
	@Test
	private void Event_Create() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Event > Instructor : Verify that user can create Event successfully (Navigate to Create an Event page from Location page)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Event > Instructor : Verify that user can create Event successfully (Navigate to Create an Event page from Location page)"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		PaymentSettingsPage pay = new PaymentSettingsPage(driver);
		
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
				instructorcreateevent.Location.sendKeys("Clarion");
				Thread.sleep(5000);
				instructorcreateevent.Location_FirstChoice.click();
				Thread.sleep(2000);
					
				instructorcreateevent.SelectStartDate("6", "30", "PM");
				instructorcreateevent.SelectEndDate("7", "30", "PM");
				
				instructorcreateevent.AddEventDate.click();
				Thread.sleep(2000);
				
				//JavascriptExecutor jse = (JavascriptExecutor)driver;
				//jse.executeScript("window.scrollBy(0,250)", "");
				
				((JavascriptExecutor)driver).executeScript("scroll(856,797)");
				
				
				instructorcreateevent.CreateEvent.click();
				Thread.sleep(5000);
				
				//SeleniumFunc.AcceptAlertAndGetText();

				
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
					
				//Updated due to change in functionality
				//https://kalkomey.tpondemand.com/entity/14661
				/*SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
				Thread.sleep(5000);*/
			
			
				//Verifying Event Time
				String expectedtext = "06:30 PM";
				String actualtext = instructorschedule.FirstEventTime.getText().trim();
				String eventid = instructoreventroster.EventId.getText().trim();
				
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
				
				//Going to Event Roster page and deleting event so new event can be added with same test data
				
				try {
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
					instructoreventroster.EventDelete.click();
					Thread.sleep(5000);

					instructoreventroster.Confirm_EventDelete.click();
					System.out.println("Event: "+eventid+" Deleted Successfully!!");
					Thread.sleep(5000);
				} catch (Exception e) {
					
				}
				
			
		 /*
		  * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		  */
		   if(AssertFailedCount>0) 
		   {
		    System.out.println("------Test Case failed ,Deleting Event------");
		    SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
			instructoreventroster.EventDelete.click();
			Thread.sleep(5000);

			instructoreventroster.Confirm_EventDelete.click();
			System.out.println("Event: "+eventid+" Deleted Successfully!!");
			Thread.sleep(5000);
		    //Marking this test as Failed
		    
		    System.out.println("---- Test Failed. Please check the console or TestNg report for details");
		    Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
		    
		    Assert.fail();
		   }
		
	}


	/* Test 3: 
	 * Event > Instructor  : Verify that user can delete Event successfully (if no user registered)
	*/ 
	@Test
	private void Event_Delete() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Event > Instructor  : Verify that user can delete Event successfully (if no user registered)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Event > Instructor  : Verify that user can delete Event successfully (if no user registered)"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
	
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
				instructorcreateevent.Location.sendKeys("Clarion");
				Thread.sleep(5000);
				instructorcreateevent.Location_FirstChoice.click();
				Thread.sleep(2000);
			
				instructorcreateevent.SelectStartDate("6", "30", "PM");
				instructorcreateevent.SelectEndDate("7", "30", "PM");
				
				instructorcreateevent.AddEventDate.click();
				Thread.sleep(2000);
				
				instructorcreateevent.CreateEvent.click();
				Thread.sleep(5000);

				
		System.out.println("Step 4 : On Event Roster page, clicking on 'Delete' link ");
		Reporter.log("Step 4 : On Event Roster page, clicking on 'Delete' link "); 
			
				//Updated due to change in functionality
				//https://kalkomey.tpondemand.com/entity/14661
			/*SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			Thread.sleep(5000);*/

			/*instructorschedule.FirstEventName.click();
			Thread.sleep(5000);*/
			
			SeleniumFunc.ClickOnElement("css", ".nav.nav-tabs.nav-stacked>li:nth-of-type(2)>a");
			Thread.sleep(5000);

			
			//Verifying Confirmation message
			String expectedtext = "Are you sure you wish to delete the event Demo Hunter";
			String actualtext = instructoreventroster.Confirm_EventDelete_Message.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Delete Confirmation message is correct");
				Reporter.log("Success !! Delete Confirmation message is correct");
			}
			else
			{
				System.out.println("Failure !! Delete Confirmation message is incorrect" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Delete Confirmation message is incorrect" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			SeleniumFunc.ClickOnElement("css", ".button:nth-of-type(1)>span");
			Thread.sleep(4000);
			/*instructoreventroster.Confirm_EventDelete.click();*/
			
			Thread.sleep(5000);
			
/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/		
			
			
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
	 * Event > Instructor  : Verify that user can create Multi Day Event successfully
	*/ 
	@Test
	private void Event_MultiDayCreate() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Event > Instructor  : Verify that user can create Multi Day Event successfully"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Event > Instructor  : Verify that user can create Multi Day Event successfully"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
	
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
				
		
		System.out.println("Step 3 : On Create Event page , selecting 2 different dates and time for an event");
		Reporter.log("Step 3 : On Create Event page , selecting 2 different dates and time for an event");
		
				instructorcreateevent.SelectProgram();
				Thread.sleep(2000);
				
				instructorcreateevent.Location.clear();
				instructorcreateevent.Location.sendKeys("Clarion");
				Thread.sleep(5000);
				instructorcreateevent.Location_FirstChoice.click();
				Thread.sleep(2000);
			
				//Selecting first date as Today's date and time as below
				instructorcreateevent.SelectStartDate("6", "30", "PM");
				instructorcreateevent.SelectEndDate("7", "30", "PM");
				
				instructorcreateevent.AddEventDate.click();
				Thread.sleep(2000);
				
				
				//Selecting second date as 30th and time as below
				instructorcreateevent.SelectDate("30");
				instructorcreateevent.SelectStartDate("1", "15", "PM");
				instructorcreateevent.SelectEndDate("2", "15", "PM");
				
				instructorcreateevent.AddEventDate.click();
				Thread.sleep(2000);
				
				
				//Verifying whether added dates are displayed under 'Current Schedule' or not
				// Note: We're verifying only Time only
					
					//Verifying first date time
					
					String expectedtext = "6:30 PM to 7:30 PM";
					String actualtext = instructorcreateevent.CurrentScheduleGetDateInfo(1);
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! Current Schedule has correct Date & Time");
						Reporter.log("Success !! Current Schedule has correct Date & Time");
					}
					else
					{
						System.out.println("Failure !! Current Schedule has incorrect Date & Time" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! Current Schedule has incorrect Date & Time" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
					
					//Verifying second date time
					
					expectedtext = "1:15 PM to 2:15 PM";
					actualtext = instructorcreateevent.CurrentScheduleGetDateInfo(2);
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! Current Schedule has correct Date & Time");
						Reporter.log("Success !! Current Schedule has correct Date & Time");
					}
					else
					{
						System.out.println("Failure !! Current Schedule has incorrect Date & Time" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! Current Schedule has incorrect Date & Time" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
				
				
				
				
					
				
		System.out.println("Step 4 : Clicking on Create Event button and verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Clicking on Create Event button and verifying whether Event is added successfully or not"); 
					
			instructorcreateevent.CreateEvent.click();
			Thread.sleep(5000);

			
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			/*SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			Thread.sleep(5000);*/

			
			//Verifying Event Time
			expectedtext = "06:30 PM";
			actualtext = instructorschedule.FirstEventTime.getText().trim();
			String eventid= instructorschedule.EventId.getText().trim();
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
				
			//Going to Event Roster page and deleting event so new event can be added with same test data
			
			try {
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				System.out.println("Event: "+eventid+" Deleted Successfully!!");
				Thread.sleep(5000);
			} catch (Exception e) {
				
			}
			
		
	 /*
	  * Marking Test Pass or Fail as per the value of AssertFailedCount variable
	  */
	   if(AssertFailedCount>0) 
	   {
	    System.out.println("------Test Case failed ,Deleting Event------");
	    SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
		instructoreventroster.EventDelete.click();
		Thread.sleep(5000);

		instructoreventroster.Confirm_EventDelete.click();
		System.out.println("Event: "+eventid+" Deleted Successfully!!");
		Thread.sleep(5000);
	    //Marking this test as Failed
	    
	    System.out.println("---- Test Failed. Please check the console or TestNg report for details");
	    Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
	    
	    Assert.fail();
	   }
		
	}

	
	/* Test 5: 
	 * Event > Instructor  : Verify that user can edit Event successfully [Event Name, Location, Current Schedule]
	*/ 
	@Test
	private void Event_Edit1() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Event > Instructor  : Verify that user can edit Event successfully [Event Name, Location, Current Schedule]"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Event > Instructor  : Verify that user can edit Event successfully [Event Name, Location, Current Schedule]"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		InstructorEditEventPage instructoreditevent = new InstructorEditEventPage(driver);
		
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
				instructorcreateevent.Location.sendKeys("Clarion");
				Thread.sleep(5000);
				instructorcreateevent.Location_FirstChoice.click();
				Thread.sleep(2000);
			
				instructorcreateevent.SelectStartDate("6", "30", "PM");
				instructorcreateevent.SelectEndDate("7", "30", "PM");
				
				instructorcreateevent.AddEventDate.click();
				Thread.sleep(2000);
				((JavascriptExecutor)driver).executeScript("scroll(1027,987)");
				instructorcreateevent.CreateEvent.click();
				Thread.sleep(5000);

				
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
					
			
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			/*SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			Thread.sleep(5000);*/

		
			//Verifying Event Time
			String expectedtext = "06:30 PM";
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
				
		System.out.println("Step 5 : Going to Event Roster page and Editing event for [Event Name, Location, Current Schedule]");
		Reporter.log("Step 5 : Going to Event Roster page and Editing event for [Event Name, Location, Current Schedule]");
		
			/*instructorschedule.FirstEventName.click();
			Thread.sleep(5000);*/

			instructoreventroster.EventEdit.click();
			Thread.sleep(5000);
			
			//Editing Event name
				//new Select(instructoreditevent.EventName).selectByVisibleText("Youth Only");
				instructoreditevent.EventNameTextbox.sendKeys("Youth Only");
			
			//Editing Location
				instructoreditevent.Location.clear();
				instructoreditevent.Location.sendKeys("Demo Agency Location (Room: 5)");
				Thread.sleep(5000);
				instructoreditevent.Location_FirstChoice.click();
				Thread.sleep(2000);
				String newlocation = instructoreditevent.Location.getAttribute("value");
				
			//Editing current schedule
				instructoreditevent.CurrentScheduleRemoveDate(1);
				instructoreditevent.SelectStartDate("5", "30", "PM");
				instructoreditevent.SelectEndDate("6", "30", "PM");
				
				instructoreditevent.AddEventDate.click();
				Thread.sleep(5000);

			
			
		System.out.println("Step 6 : Click on 'Save Changes' button and verifying whether Event is edited successfully or not");
		Reporter.log("Step 6 : Click on 'Save Changes' button and verifying whether Event is edited successfully or not"); 
		
			((JavascriptExecutor)driver).executeScript("scroll(1022,1060)");
			instructoreditevent.SaveChanges.click();
			Thread.sleep(5000);

			
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			/*SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			Thread.sleep(5000);*/

			
			//Verifying Event Time
			expectedtext = "05:30 PM";
			actualtext = instructorschedule.FirstEventTime.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Event is edited with correct Time");
				Reporter.log("Success !! Event is edited with correct Time");
			}
			else
			{
				System.out.println("Failure !! Event is edited with incorrect Time" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is edited with incorrect Time" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			//Verifying Event Name
			expectedtext = "Youth Only";
			actualtext = instructorschedule.FirstEventName.getText().trim();
			String eventid=instructorschedule.EventId.getText().trim();
			System.out.println("Event ID: "+eventid);
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Event is edited with correct Name");
				Reporter.log("Success !! Event is edited with correct Name");
			}
			else
			{
				System.out.println("Failure !! Event is edited with incorrect Name" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is edited with incorrect Name" + "\n" + "Expected : "  
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
			
			
		System.out.println("Step 7 : Going to Event Roster page and verifying Location");
		Reporter.log("Step 7 : Going to Event Roster page and verifying Location");
		
			/*instructorschedule.FirstEventName.click();
			Thread.sleep(5000);*/

			//Verifying Location name
			expectedtext = newlocation;
			actualtext = SeleniumFunc.GetElementText("css", ".span9>div>a>span");
		
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Event is edited with correct Location");
				Reporter.log("Success !! Event is edited with correct Location");
			}
			else
			{
				System.out.println("Failure !! Event is edited with incorrect Location" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is edited with incorrect Location" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
		
			//Going to Event Roster page and deleting event so new event can be added with same test data
			
			try {
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				System.out.println("Event: "+eventid+" Deleted Successfully!!");
				Thread.sleep(5000);
			} catch (Exception e) {
				
			}
			
		
	 /*
	  * Marking Test Pass or Fail as per the value of AssertFailedCount variable
	  */
	   if(AssertFailedCount>0) 
	   {
	    System.out.println("------Test Case failed ,Deleting Event------");
	    SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
		instructoreventroster.EventDelete.click();
		Thread.sleep(5000);

		instructoreventroster.Confirm_EventDelete.click();
		System.out.println("Event: "+eventid+" Deleted Successfully!!");
		Thread.sleep(5000);
	    //Marking this test as Failed
	    
	    System.out.println("---- Test Failed. Please check the console or TestNg report for details");
	    Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
	    
	    Assert.fail();
	   }
		
	}

	
	/* Test 6: 
	 * Event > Instructor : Verify that user can change the capacity for event created
	*/ 
	@Test
	private void Event_Edit2() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Event > Instructor : Verify that user can change the capacity for event created"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Event > Instructor : Verify that user can change the capacity for event created"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		InstructorEditEventPage instructoreditevent = new InstructorEditEventPage(driver);
		
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
				instructorcreateevent.Location.sendKeys("Clarion");
				Thread.sleep(5000);
				instructorcreateevent.Location_FirstChoice.click();
				Thread.sleep(2000);
			
				instructorcreateevent.SelectStartDate("6", "30", "PM");
				instructorcreateevent.SelectEndDate("7", "30", "PM");
				
				instructorcreateevent.AddEventDate.click();
				Thread.sleep(2000);
				
				instructorcreateevent.CreateEvent.click();
				Thread.sleep(5000);

				
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
					
		
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			/*SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			Thread.sleep(5000);*/

		
			//Verifying Event Time
			String expectedtext = "06:30 PM";
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
			String eventid=instructorschedule.EventId.getText().trim();
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
				
		System.out.println("Step 5 : Going to Event Roster page and Editing event for Capicity");
		Reporter.log("Step 5 : Going to Event Roster page and Editing event for Capicity");
		
			/*instructorschedule.FirstEventName.click();
			Thread.sleep(5000);*/

			String URL = driver.getCurrentUrl();
			
			instructoreventroster.EventEdit.click();
			
			//Editing Capacity 
				instructoreditevent.EventCapacity.clear();
				
				instructoreditevent.EventCapacity.sendKeys("10");
			
		System.out.println("Step 6 : Click on 'Save Changes' button");
		Reporter.log("Step 6 : Click on 'Save Changes' button"); 
		
			instructoreditevent.SaveChanges.click();
			Thread.sleep(5000);
			
		System.out.println("Step 7 : Going to Event Roster page and verifying Capicity");
		Reporter.log("Step 7 : Going to Event Roster page and verifying Capicity");
		
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(5000);

			//instructorschedule.FirstEventName.click();
			
			//Verifying Capacity
			expectedtext = "0 of 10";
			actualtext = instructoreventroster.EventCapacity.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Event is edited with correct Capacity");
				Reporter.log("Success !! Event is edited with correct Capacity");
			}
			else
			{
				System.out.println("Failure !! Event is edited with incorrect Capacity" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is edited with incorrect Capacity" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
		
	//Going to Event Roster page and deleting event so new event can be added with same test data
			
			try {
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				System.out.println("Event: "+eventid+" Deleted Successfully!!");
				Thread.sleep(5000);
			} catch (Exception e) {
				
			}
			
		
	 /*
	  * Marking Test Pass or Fail as per the value of AssertFailedCount variable
	  */
	   if(AssertFailedCount>0) 
	   {
	    System.out.println("------Test Case failed ,Deleting Event------");
	    SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
		instructoreventroster.EventDelete.click();
		Thread.sleep(5000);

		instructoreventroster.Confirm_EventDelete.click();
		System.out.println("Event: "+eventid+" Deleted Successfully!!");
		Thread.sleep(5000);
	    //Marking this test as Failed
	    
	    System.out.println("---- Test Failed. Please check the console or TestNg report for details");
	    Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
	    
	    Assert.fail();
	   }
		
	}

	
	/* Test 7: 
	 * Event > Instructor :  While creating an Event, user should be able to override event default options
	*/ 
	@Test
	private void Event_Create_OverrideDefaults() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Event > Instructor :  While creating an Event, user should be able to override event default options"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Event > Instructor :  While creating an Event, user should be able to override event default options"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
	
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
				
		
		System.out.println("Step 3 : On Create Event page , entering all required details");
		Reporter.log("Step 3 : On Create Event page , entering all required details");
		
				instructorcreateevent.SelectProgram();
				Thread.sleep(2000);
				
				instructorcreateevent.Location.clear();
				instructorcreateevent.Location.sendKeys("Clarion");
				Thread.sleep(5000);
				instructorcreateevent.Location_FirstChoice.click();
				Thread.sleep(2000);
			
				instructorcreateevent.SelectStartDate("6", "30", "PM");
				instructorcreateevent.SelectEndDate("7", "30", "PM");
				
				instructorcreateevent.AddEventDate.click();
				Thread.sleep(2000);
				
				
		System.out.println("Step 4 : On Create Event page , chagning default values for all options");
		Reporter.log("Step 4 : On Create Event page , chagning default values for all options");	
		
				//Privacy set to Private
				instructorcreateevent.PrivacyValue.click();
				Thread.sleep(5000);

				instructorcreateevent.PrivacySetAsPrivate.click();
				Thread.sleep(5000);

					//Verify whether text is changed or not
					String expectedtext = "This is a private event.";
					String actualtext = instructorcreateevent.PrivacyLabel.getText().trim();
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! Option - Privacy lable is correct");
						Reporter.log("Success !! Option - Privacy lable is correct");
					}
					else
					{
						System.out.println("Failure !! Option - Privacy lable is incorrect" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! Option - Privacy lable is incorrect" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
		
				//Visibility = Do not display this event.
				instructorcreateevent.EventVisibleLink.click();
				Thread.sleep(5000);

				instructorcreateevent.EventVisibleSetDoNotDisplay.click();
				Thread.sleep(5000);

					//Verify whether text is changed or not
					expectedtext = "This event will not be visible to students.";
					actualtext = instructorcreateevent.EventVisibleLabel.getText().trim();
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! Option - Visible lable is correct");
						Reporter.log("Success !! Option - Visible lable is correct");
					}
					else
					{
						System.out.println("Failure !! Option -  Visible is incorrect" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! Option -  Visible is incorrect" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
				
				//Registration = Do not allow open registration.
				instructorcreateevent.EventRegisterLink.click();
				Thread.sleep(5000);

				instructorcreateevent.EventRegisterSetDoNotRegister.click();
				Thread.sleep(5000);

					//Verify whether text is changed or not
					expectedtext = "Students will not be able to register for this event.";
					actualtext = instructorcreateevent.EventRegisterLabel.getText().trim();
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! Option - Registration lable is correct");
						Reporter.log("Success !! Option - Registration lable is correct");
					}
					else
					{
						System.out.println("Failure !! Option -  Registration is incorrect" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! Option -  Registration is incorrect" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
		
				//Cancel Registration = Do not allow cancellations.
				instructorcreateevent.EventCancelLink.click();
				Thread.sleep(5000);

				instructorcreateevent.EventCancelSetDoNotCancel.click();
				Thread.sleep(5000);

					//Verify whether text is changed or not
					expectedtext = "Students will not be able to cancel registrations for this event.";
					actualtext = instructorcreateevent.EventCancelLabel.getText().trim();
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! Option - Cancel Registration lable is correct");
						Reporter.log("Success !! Option - Cancel Registration lable is correct");
					}
					else
					{
						System.out.println("Failure !! Option - Cancel Registration is incorrect" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! Option - Cancel Registration is incorrect" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
		
				//Notifications  = disable notifications.
				instructorcreateevent.EventNotificationLink.click();
				Thread.sleep(5000);

				instructorcreateevent.EventNotificationNot.click();
				Thread.sleep(5000);

					//Verify whether text is changed or not
					expectedtext = "Notifications are enabled.";
					actualtext = instructorcreateevent.EventNotificationLabel.getText().trim();
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! Option - Notifications lable is correct");
						Reporter.log("Success !! Option - Notifications lable is correct");
					}
					else
					{
						System.out.println("Failure !! Option - Notifications is incorrect" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! Option - Notifications is incorrect" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
		
				//Cancellation Notification  = disable notifications.
					
				instructorcreateevent.EventCancelNotificationLink.click();	
				Thread.sleep(5000);

				instructorcreateevent.EventCancelNotificationNot.click();
				Thread.sleep(5000);

					//Verify whether text is changed or not
					expectedtext = "Cancellation notifications are enabled.";
					actualtext = instructorcreateevent.EventCancelNotificationLabel.getText().trim();
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! Option - Cancellation Notifications lable is correct");
						Reporter.log("Success !! Option - Notifications lable is correct");
					}
					else
					{
						System.out.println("Failure !! Option - Cancellation Notifications is incorrect" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! Option - Cancellation Notifications is incorrect" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
		
				//Wait List =disable wait list.
				instructorcreateevent.EventWaitListLink.click();
				Thread.sleep(5000);

				instructorcreateevent.EventWaitListNot.click();
				Thread.sleep(5000);

					//Verify whether text is changed or not
					expectedtext = "Wait list is disabled.";
					actualtext = instructorcreateevent.EventWaitListLabel.getText().trim();
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! Option - Wait List lable is correct");
						Reporter.log("Success !! Option - Wait List lable is correct");
					}
					else
					{
						System.out.println("Failure !! Option - Wait List is incorrect" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! Option - Wait List is incorrect" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
		
				instructorcreateevent.CreateEvent.click();
				Thread.sleep(5000);				
				
				
		System.out.println("Step 5 : Click on 'Create Event' button and Verifying whether Event is added successfully or not");
		Reporter.log("Step 5 : Click on 'Create Event' button and Verifying whether Event is added successfully or not"); 
					
		
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			Thread.sleep(5000);

			//Verifying Event Time
			expectedtext = "6:30 pm";
			actualtext = instructorschedule.FirstEventTime.getText().trim();
		
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
			expectedtext = "Demo Hunter Education Field Day";
			actualtext = instructorschedule.FirstEventName.getText().trim();
			String eventid = instructorschedule.EventId.getText().trim();
			
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
			
			
		System.out.println("Step 6 : Going to Event Roster page and verify that Event is marked as Private");
		Reporter.log("Step 6 : Going to Event Roster page and verify that Event is marked as Private"); 
		
			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);

			//Verifying Event is marked as Private
			expectedtext = "Private";
			actualtext = instructoreventroster.PrivateLabel.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Event is marked as Private");
				Reporter.log("Success !! Event is marked as Private");
			}
			else
			{
				System.out.println("Failure !! Event is NOT marked as Private" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is NOT marked as Private" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
		
			//Going to Event Roster page and deleting event so new event can be added with same test data
			
			try {
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				System.out.println("Event: "+eventid+" Deleted Successfully!!");
				Thread.sleep(5000);
			} catch (Exception e) {
				
			}
			
		
	 /*
	  * Marking Test Pass or Fail as per the value of AssertFailedCount variable
	  */
	   if(AssertFailedCount>0) 
	   {
	    System.out.println("------Test Case failed ,Deleting Event------");
	    SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
		instructoreventroster.EventDelete.click();
		Thread.sleep(5000);

		instructoreventroster.Confirm_EventDelete.click();
		System.out.println("Event: "+eventid+" Deleted Successfully!!");
		Thread.sleep(5000);
	    //Marking this test as Failed
	    
	    System.out.println("---- Test Failed. Please check the console or TestNg report for details");
	    Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
	    
	    Assert.fail();
	   }
		
	}


	/* Test 8: 
	 * Event > Instructor : Verify that Admin approval is required to publish event
	*/ 
	@Test
	private void Event_AdminApprovalRequired() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Event > Instructor : Verify that Admin approval is required to publish event"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Event > Instructor : Verify that Admin approval is required to publish event"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		InstructorEnrollmentsPage instructorenrolpage = new InstructorEnrollmentsPage(driver);
	
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
					instructorcreateevent.Location.sendKeys("Clarion");
					Thread.sleep(5000);
					instructorcreateevent.Location_FirstChoice.click();
					Thread.sleep(2000);
				
					instructorcreateevent.SelectStartDate("6", "30", "PM");
					instructorcreateevent.SelectEndDate("7", "30", "PM");
					
					instructorcreateevent.AddEventDate.click();
					Thread.sleep(2000);
					
					instructorcreateevent.CreateEvent.click();
					Thread.sleep(5000);

					
			System.out.println("Step 4 : Verifying whether Event is added successfully or not");
			Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
						

				//Verifying Event Time
				String expectedtext = "06:30 PM";
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
				
			System.out.println("Step 5 : Navigating to Event Enrollment page and verifying Event status ");
			Reporter.log("Step 5 : Navigating to Event Enrollment page and verifying Event status"); 	
				
				instructorschedule.FirstEventName.click();
				Thread.sleep(5000);

				String eventid = instructoreventroster.EventId.getText().trim();
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/chooseevent");
				Thread.sleep(5000);

				//Verifying Event Status
				expectedtext = "Activation Pending";
				actualtext = instructorenrolpage.SearchEventByIDAndReturnStatus(eventid);
			
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success !! Event has status = 'Activation Pending' and needs admin approval required to publish");
					Reporter.log("Success !! Event has status = 'Activation Pending' and needs admin approval required to publish");
				}
				else
				{
					System.out.println("Failure !! Event has incorrect status" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! Event has incorrect status" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}

			//Going to Event Roster page and deleting event so new event can be added with same test data
				
				try {
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
					instructoreventroster.EventDelete.click();
					Thread.sleep(5000);

					instructoreventroster.Confirm_EventDelete.click();
					System.out.println("Event: "+eventid+" Deleted Successfully!!");
					Thread.sleep(5000);
				} catch (Exception e) {
					
				}
				
			
		 /*
		  * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		  */
		   if(AssertFailedCount>0) 
		   {
		    System.out.println("------Test Case failed ,Deleting Event------");
		    SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
			instructoreventroster.EventDelete.click();
			Thread.sleep(5000);

			instructoreventroster.Confirm_EventDelete.click();
			System.out.println("Event: "+eventid+" Deleted Successfully!!");
			Thread.sleep(5000);
		    //Marking this test as Failed
		    
		    System.out.println("---- Test Failed. Please check the console or TestNg report for details");
		    Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
		    
		    Assert.fail();
		   }
		
	}


	/* Test 9: 
	 * Event > Admin  : Verify that user can Activate Event and status is correctly displayed on Event Enrollments page
	*/ 
	@Test
	private void Event_Admin_ActivateEvent() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Event > Admin  : Verify that user can Activate Event and status is correctly displayed on Event Enrollments page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 : Event > Admin  : Verify that user can Activate Event and status is correctly displayed on Event Enrollments page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		InstructorEnrollmentsPage instructorenrolpage = new InstructorEnrollmentsPage(driver);
		AdminSearchEventPage adminsearchevent = new AdminSearchEventPage(driver);
	
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
				instructorcreateevent.Location.sendKeys("Clarion");
				Thread.sleep(5000);
				instructorcreateevent.Location_FirstChoice.click();
				Thread.sleep(2000);
			
				instructorcreateevent.SelectStartDate("6", "30", "PM");
				instructorcreateevent.SelectEndDate("7", "30", "PM");
				
				instructorcreateevent.AddEventDate.click();
				Thread.sleep(2000);
				
				instructorcreateevent.CreateEvent.click();
				Thread.sleep(5000);

				//Updated due to change in functionality
				//https://kalkomey.tpondemand.com/entity/14661
			/*	SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
				Thread.sleep(5000);*/

				
				//Verifying Event Time
				String expectedtext = "06:30 PM";
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
				String EventID = instructoreventroster.EventId.getText().trim();
				Thread.sleep(5000);
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
			
		System.out.println("Step 4 : Navigating to Event Enrollment page and verifying Event status ");
		Reporter.log("Step 4 : Navigating to Event Enrollment page and verifying Event status"); 	
			
			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);

			String eventid = instructoreventroster.EventId.getText().trim();
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/chooseevent");
			Thread.sleep(5000);

			//Verifying Event Status
			expectedtext = "Activation Pending";
			actualtext = instructorenrolpage.SearchEventByIDAndReturnStatus(eventid);
		
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Event has status = 'Activation Pending' and needs admin approval required to publish");
				Reporter.log("Success !! Event has status = 'Activation Pending' and needs admin approval required to publish");
			}
			else
			{
				System.out.println("Failure !! Event has incorrect status" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event has incorrect status" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}

		
			
			
		System.out.println("Step 5 : Login as Admin user and activating Event");
		Reporter.log("Step 5 : Login as Admin user and activating Event"); 	
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM + "/login/logout");
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			
			//Navigating to http://my-webtest1.register-ed.com/admin/events
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM + "/admin/events");
			Thread.sleep(5000);

			//Searching for Event with id & activating it
			adminsearchevent.SelectSearchTypeByVisibleText("ID");
			adminsearchevent.EnterSearchData(EventID);
			adminsearchevent.SearchButton.click();
			Thread.sleep(5000);
			adminsearchevent.SelectEvent.click();
			Thread.sleep(5000);

			adminsearchevent.SelectEventActionByVisibleText("Set Status: Active");
			adminsearchevent.GoButton.click();
			Thread.sleep(5000);

		System.out.println("Step 6 : Login as Instructor user and verifying Event status");
		Reporter.log("Step 6 : Login as Instructor user and verifying Event status"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM + "/login/logout");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/chooseevent");
			Thread.sleep(5000);

			//Verifying Event Status
			expectedtext = "Active";
			actualtext = instructorenrolpage.SearchEventByIDAndReturnStatus(eventid);
		
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Event has status = 'Active'");
				Reporter.log("Success !! Event has status = 'Active'");
			}
			else
			{
				System.out.println("Failure !! Event has incorrect status" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event has incorrect status" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			
			
			//Going to Event Roster page and deleting event so new event can be added with same test data
			
			try {
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				System.out.println("Event: "+eventid+" Deleted Successfully!!");
				Thread.sleep(5000);
			} catch (Exception e) {
				
			}
			
		
	 /*
	  * Marking Test Pass or Fail as per the value of AssertFailedCount variable
	  */
	   if(AssertFailedCount>0) 
	   {
	    System.out.println("------Test Case failed ,Deleting Event------");
	    SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
		instructoreventroster.EventDelete.click();
		Thread.sleep(5000);

		instructoreventroster.Confirm_EventDelete.click();
		System.out.println("Event: "+eventid+" Deleted Successfully!!");
		Thread.sleep(5000);
	    //Marking this test as Failed
	    
	    System.out.println("---- Test Failed. Please check the console or TestNg report for details");
	    Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
	    
	    Assert.fail();
	   }
	}

	
	/* Test 10: 
	 * Event > Assistant Instructor -  Verify that user can't delete an event
	*/ 
	@Test
	private void Event_Assistant_Instructor_DeleteEvent() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : Event > Assistant Instructor -  Verify that user can't delete an event"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10 : Event > Assistant Instructor -  Verify that user can't delete an event"  + "\n" +
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
				instructorcreateevent.Location.sendKeys("Clarion");
				Thread.sleep(5000);
				instructorcreateevent.Location_FirstChoice.click();
				Thread.sleep(2000);
			
				instructorcreateevent.SelectStartDate("6", "30", "PM");
				instructorcreateevent.SelectEndDate("7", "30", "PM");
				
				instructorcreateevent.AddEventDate.click();
				Thread.sleep(2000);
				
				instructorcreateevent.CreateEvent.click();
				Thread.sleep(5000);

				
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
				
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			/*SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			Thread.sleep(5000);*/

			//Verifying Event Time
			String expectedtext = "06:30 PM";
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
			String eventid = instructoreventroster.EventId.getText().trim();
		
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
			
		System.out.println("Step 5 : Navigating to Event Roster page and adding new Assistant Instructor");
		Reporter.log("Step 5 : Navigating to Event Roster page and adding new Assistant Instructor"); 	
			
			//instructorschedule.FirstEventName.click();
		   // SeleniumFunc.ClickOnElement("css", "#page > div.content.clearfix > div.row-fluid > div.span9 > div.row-fluid > div.span8 > div:nth-child(3) > div.span9");
			

			//Click on 'Add or Remove Instructors' link
			
			/*instructoreventroster.SelectAction("Add or Remove Instructors");
			Thread.sleep(5000);

			//On Manage Instructor page, Searching for 'Available Instructors' and adding as Assistant Instructor
			instructormanage.SelectSearchTypeByUsername();
			instructormanage.SearchBox.sendKeys(Constants.EM_Assistant_Instructor_Username);
			instructormanage.SearchButton.click();
			Thread.sleep(5000);
			*/
			//instructormanage.Search_FirstResultCheckbox.click();
		   SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
		   SeleniumFunc.ClickOnElement("linkText", "Add or Remove Instructors");
		   Thread.sleep(2000);
		   SeleniumFunc.EnterValueInTextbox("css", "#search-type-name", "test ke-testing");
		   SeleniumFunc.ClickOnElement("xpath", ".//*[@id='search_form']/input[15]");
		   Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("css", "#selected_2245543");
			Thread.sleep(5000);

			instructormanage.SelectActionAndGO("Assign Instructors");
			Thread.sleep(5000);

		System.out.println("Step 6 : Logout as a Instructor and logging in as Assistant Instructor");
		Reporter.log("Step 6 : Logout as a Instructor and logging in as Assistant Instructor");
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM + "/login/logout");
			login.EnterUsername(Constants.EM_Assistant_Instructor_Username);
			login.EnterPassword(Constants.EM_Assistant_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		System.out.println("Step 7 : Navigating Event roster page and trying to Delete Event");
		Reporter.log("Step 7 : Navigating Event roster page and trying to Delete Event");
		
			//Going to Event Roster page and deleting event so new event can be added with same test data
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
			instructoreventroster.EventDelete.click();
			Thread.sleep(5000);

			//Verifying Confirmation message
			expectedtext = "Sorry, you can't delete this event because it does not belong to you.";
			actualtext = instructoreventroster.Confirm_EventDelete_Message.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Delete Confirmation message is correct. Assistant Instructor can't delete Event");
				Reporter.log("Success !! Delete Confirmation message is correct. Assistant Instructor can't delete Event");
			}
			else
			{
				System.out.println("Failure !! Delete Confirmation message is incorrect" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Delete Confirmation message is incorrect" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 8 : Logout as a Assistant Instructor and logging in as Instructor to delete added event");
		Reporter.log("Step 8 : Logout as a Assistant Instructor and logging in as  Instructor to delete added event");
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM + "/login/logout");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
			instructoreventroster.EventDelete.click();
			Thread.sleep(5000);

			instructoreventroster.Confirm_EventDelete.click();
			Thread.sleep(5000);

			
			
			
			
//Going to Event Roster page and deleting event so new event can be added with same test data
			
			try {
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				System.out.println("Event: "+eventid+" Deleted Successfully!!");
				Thread.sleep(5000);
			} catch (Exception e) {
				
			}
			
		
	 /*
	  * Marking Test Pass or Fail as per the value of AssertFailedCount variable
	  */
	   if(AssertFailedCount>0) 
	   {
	    System.out.println("------Test Case failed ,Deleting Event------");
	    SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
		instructoreventroster.EventDelete.click();
		Thread.sleep(5000);

		instructoreventroster.Confirm_EventDelete.click();
		System.out.println("Event: "+eventid+" Deleted Successfully!!");
		Thread.sleep(5000);
	    //Marking this test as Failed
	    
	    System.out.println("---- Test Failed. Please check the console or TestNg report for details");
	    Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
	    
	    Assert.fail();
	   }
		
	}

	
	/* Test 11: 
	 * Event > Instructor : Verify that only Admin can edit event location for activated events
	*/ 
	@Test
	private void Event_Location_Edit() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 11 : Event > Instructor : Verify that only Admin can edit event location for activated events"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 11 : Event > Instructor : Verify that only Admin can edit event location for activated events"  + "\n" +
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
		AgencyLocationPage agencylocations = new AgencyLocationPage(driver);
	
		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			//Navigate to 'My Event Schedule' page and delete all Events
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//instructorschedule.DeleteAllEvents();
			
			
		System.out.println("Step 2 : Creating new location");
		Reporter.log("Step 2 : Creating new location");
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			instructorlocations.SelectProgram(6);	
			Thread.sleep(2000);
	
			//Clicking on 'Creat Location' link for adding location");
			instructorlocations.CreateLocation.click();	
			Thread.sleep(2000);
			
			String expectedtext = Constants.ApplicationURL_EM +"/location/create/";
			String actualtext = driver.getCurrentUrl();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! 'Creat Location' link is working as expected");
				Reporter.log("Success !! 'Creat Location' link is working as expected"); 
			}
			else
			{
				System.out.println("Failure !! 'Creat Location' link is NOT working" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! 'Creat Location' link is NOT working" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}	
			
			//Entering required details and clicking on 'Submit' button"
		
			String locationname = "Clarion" + JH.GenerateRandomNumber();
			instructorlocations.LocationName.sendKeys(locationname);
			instructorlocations.SpecialInstructions.sendKeys("Have fun");
			instructorlocations.Address.sendKeys("277 Cantebury Dr");
			instructorlocations.City.sendKeys("Lemoore");
			instructorlocations.Zip.sendKeys("93245");
			instructorlocations.Phone.sendKeys("9874343334");
			instructorlocations.Room.sendKeys("5");
			instructorlocations.Capacity.sendKeys("20");
			Thread.sleep(2000);
			//instructorlocations.SelectRegion(2);
			SeleniumFunc.SelectValueFromDropdownList("css", "#region","Northeast");
			/*SeleniumFunc.ClickOnElement("id", "region");
			Actions act=new Actions(driver);
			WebElement region =driver.findElement(By.xpath("//option[@value='Northeast']"));;
			act.moveToElement(region).click();*/
			Thread.sleep(4000);
			//instructorlocations.SelectCounty(2); 
			SeleniumFunc.SelectValueFromDropdownList("css", "#county","Adams");
			/*SeleniumFunc.ClickOnElement("id", "county");
			Thread.sleep(2000);
			WebElement County =driver.findElement(By.xpath("//option[@value='Adams']"));
			act.moveToElement(County).click();*/
			
			Thread.sleep(2000);
			instructorlocations.Submit.click();	
			Thread.sleep(5000);
			System.out.println("Created new Location : "+locationname);
			
		System.out.println("Step 3 : Creating new Event with newly created location and adding student");
		Reporter.log("Step 3 : Creating new Event with newly created location and adding student"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			instructorlocations.SelectProgram(6);	
			Thread.sleep(5000);
			
			SeleniumFunc.EnterValueInTextbox("css", "#search-type-name", locationname);
			SeleniumFunc.ClickOnElement("css", "input[value*='Search']");
			Thread.sleep(5000);
			
			instructorlocations.ScheduleAnEvent.click();	
			Thread.sleep(5000);

			//instructorcreateevent.SelectProgram();
			//Thread.sleep(2000);
			
			/*instructorcreateevent.Location.clear();
			instructorcreateevent.Location.sendKeys(locationname);
			Thread.sleep(5000);
			SeleniumFunc.ClickOnElement("css", ".span12>a:nth-of-type(1)");;
			Thread.sleep(2000);*/
		
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
			
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
			
			instructorcreateevent.CreateEvent.click();
			Thread.sleep(5000);

			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);

			String EventID = instructoreventroster.EventId.getText().trim();
			//login.ClickOnLogoutButton();
			SeleniumFunc.ClickOnElement("css", ".inline>li:nth-of-type(2)>a");
			Thread.sleep(5000);

			
			//Login to EM as Admim

			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

				
			//Search and Activate event
				
			searchevent.ClickOnSearchEventLink();
			Thread.sleep(5000);

			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);
			//searchevent.ClickOnSearchButton();
			SeleniumFunc.ClickOnElement("css", "#search-button");
			Thread.sleep(5000);
			/*SeleniumFunc.ClickOnElement("css", ".tooltip-container.even.ruled>td>a");
			Thread.sleep(5000);
			SeleniumFunc.ClickOnElement("css", ".btn.mtt");
			Thread.sleep(5000);*/
			searchevent.ClickOnSelectEventCheckBox();
			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			Thread.sleep(5000);

			
			login.ClickOnLogoutButton();
			Thread.sleep(5000);
	
			
		System.out.println("Step 4 : Navigate to location page and verifying that Edit link is not present location");
		Reporter.log("Step 4 : Navigate to location page and verifying that Edit link is not present location");
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			instructorlocations.SelectProgram(6);
			Thread.sleep(5000);

			//instructorlocations.SelectSearchTypeByName();
			//instructorlocations.SearchBox.sendKeys(locationname);
			//instructorlocations.SearchButton.click();
			SeleniumFunc.EnterValueInTextbox("css", "#search-type-name", locationname);
			SeleniumFunc.ClickOnElement("css", "input[value*='Search']");
			Thread.sleep(5000);

			//Verifying whether Edit link is present or not
			
			if(!SeleniumFunc.IsElementPresent(instructorlocations.Edit))
			{
				System.out.println("Success !! Edit link is not present for location");
				Reporter.log("Success !! Edit link is not present for location"); 
			}
			else
			{
				System.out.println("Failure !! Edit link is present for location");
				Reporter.log("Failure !! Edit link is present for location");
				
				AssertFailedCount++;
			}	
			
			
		System.out.println("Step 5 : Login as KE Admin user and verifying whether admin can edit location or not");
		Reporter.log("Step 5 : Login as KE Admin user and verifying whether admin can edit location or not");
		
		
			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM + "/location");
			Thread.sleep(5000);

			/*SeleniumFunc.ClickOnElement("css", "ul.nav-list li a");
			Thread.sleep(5000);

			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM + "/eventtemplate/locations");
			Thread.sleep(5000);

			agencylocations.SelectSearchTypeByName();
			agencylocations.SearchBox.sendKeys(locationname);
			agencylocations.SearchButton.click();*/
			instructorlocations.SelectProgram(6);
			Thread.sleep(5000);
			SeleniumFunc.EnterValueInTextbox("css", "#search-type-name", locationname);
			SeleniumFunc.ClickOnElement("css", "input[value*='Search']");
			Thread.sleep(5000);

			//Verifying whether Edit link is present or not
			
			WebElement editlink = driver.findElement(By.cssSelector(".span12 > a:nth-of-type(3)"));
			
			if(SeleniumFunc.IsElementPresent(editlink))
			{
				System.out.println("Success !! Edit link is present for location");
				Reporter.log("Success !! Edit link is present for location"); 
			}
			else
			{
				System.out.println("Failure !! Edit link is NOT present for location");
				Reporter.log("Failure !! Edit link is NOT present for location");
				
				AssertFailedCount++;
			}	
			
			// Deleting the location
			
			editlink.click();
			Thread.sleep(4000);
			
			SeleniumFunc.ClickOnElement("css", ".btn.btn-danger");
			Thread.sleep(4000);
			
			SeleniumFunc.ClickOnElement("css", ".btn.btn-primary");
			Thread.sleep(4000);

			System.out.println("Location : "+locationname+" Deleted Successfully!!");
			Reporter.log("Location : "+locationname+" Deleted Successfully!!"); 
			
			
			//Going to Event Roster page and deleting event so new event can be added with same test data
			
			try {
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + EventID);
				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				System.out.println("Event: "+EventID+" Deleted Successfully!!");
				Thread.sleep(5000);
			} catch (Exception e) {
				
			}
			
		
	 /*
	  * Marking Test Pass or Fail as per the value of AssertFailedCount variable
	  */
	   if(AssertFailedCount>0) 
	   {
	    System.out.println("------Test Case failed ,Deleting Event------");
	    SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + EventID);
		instructoreventroster.EventDelete.click();
		Thread.sleep(5000);

		instructoreventroster.Confirm_EventDelete.click();
		System.out.println("Event: "+EventID+" Deleted Successfully!!");
		Thread.sleep(5000);
	    //Marking this test as Failed
	    
	    System.out.println("---- Test Failed. Please check the console or TestNg report for details");
	    Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
	    
	    Assert.fail();
	   }
		
	}

	
	
	/* Test 12: 
	 * Event > Instructor : Verify Instructor can see helpful status message on roster
	 * Test Case ID : 21064
	*/ 
	@Test
	private void Event_Roster_Messages() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 11 : Event > Instructor : Verify Instructor can see helpful status message on roster"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 11 : Event > Instructor : Verify Instructor can see helpful status message on roster"  + "\n" +
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
		AgencyLocationPage agencylocations = new AgencyLocationPage(driver);
	
		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			if(SeleniumFunc.IsElementPresent("linkText", "Log Out"))
			{
				SeleniumFunc.ClickOnElement("linkText", "Log Out");
			}
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(2000);
			
		System.out.println("Step 2 : Go to the roster for an event that is Activation Pending.");
		Reporter.log("Step 2 : Go to the roster for an event that is Activation Pending."); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/82984");
			
			if(SeleniumFunc.GetElementText("css", ".tooltip-container > div > div > dl > dd:nth-child(2)").equalsIgnoreCase("Waiting for Administrator to Activate Event"))
			{
				System.out.println("Success !! Next to Status displayed text - Waiting for Administrator to Activate Event");
				Reporter.log("Success !! Next to Status displayed text - Waiting for Administrator to Activate Event"); 
			}
			else
			{
				System.out.println("Failure !! Text Waiting for Administrator to Activate Event is missing.");
				Reporter.log("Failure !! Text Waiting for Administrator to Activate Event is missing.");				
				AssertFailedCount++;
			}	
			
		System.out.println("Step 3 : Go to the roster for a future event that is Active with the registration window in the future.");
		Reporter.log("Step 3 :  Go to the roster for a future event that is Active with the registration window in the future."); 
		
		
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/83176");
		
			if(SeleniumFunc.GetElementText("css", ".tooltip-container > div > div > dl > dd:nth-child(2)").equalsIgnoreCase("Registration Opens on 01/01/2025"))
			{
				System.out.println("Success !! Next to Status displayed text - Registration Opens on XX/XX/XXXX");
				Reporter.log("Success !! Next to Status displayed text - Registration Opens on XX/XX/XXXX"); 
			}
			else
			{
				System.out.println("Failure !! Text- Registration Opens on XX/XX/XXXX is missing.");
				Reporter.log("Failure !! Text- Registration Opens on XX/XX/XXXX is missing.");				
				AssertFailedCount++;
			}	
			
			
		System.out.println("Step 4 : Go to the roster for a future event that is Active with an open registration window.");
		Reporter.log("Step 4 :  Go to the roster for a future event that is Active with an open registration window."); 
		
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/83177");
			if(SeleniumFunc.GetElementText("css", ".tooltip-container > div > div > dl > dd:nth-child(2)").equalsIgnoreCase("Registration Open"))
			{
				System.out.println("Success !! Next to Status displayed - Registration Open");
				Reporter.log("Success !! Next to Status displayed - Registration Open"); 
			}
			else
			{
				System.out.println("Failure !! Text- Registration Open is missing.");
				Reporter.log("Failure !! Text- Registration Open is missing.");				
				AssertFailedCount++;
			}
			
		/* Registration Closed not found on Roster
		 	
		System.out.println("Step 5 : Go to the roster for a future event that is Active with a past registration window.");
		Reporter.log("Step 5 :  Go to the roster for a future event that is Active with a past registration window."); 
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/83177");
				
			if(SeleniumFunc.GetElementText("css", ".tooltip-container > div > div > dl > dd:nth-child(2)").equalsIgnoreCase("Registration Open"))
			{
				System.out.println("Success !! Next to Status displayed - Registration Closed");
				Reporter.log("Success !! Next to Status displayed - Registration Closed"); 
			}
			else
			{
				System.out.println("Failure !! Text- Registration Closed is missing.");
				Reporter.log("Failure !! Text- Registration Closed is missing.");				
				AssertFailedCount++;
			}	*/
			
			
		System.out.println("Step 6 : Go to the roster for a future event that is Active with special registration.");
		Reporter.log("Step 6 :  Go to the roster for a future event that is Active with special registration."); 
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/83178");
			if(SeleniumFunc.GetElementText("css", ".tooltip-container > div > div > dl > dd:nth-child(2)").equalsIgnoreCase("Special Registration"))
			{
				System.out.println("Success !! Next to Status displayed - Special Registration");
				Reporter.log("Success !! Next to Status displayed - Special Registration"); 
			}
			else
			{
				System.out.println("Failure !! Text- Special Registration is missing.");
				Reporter.log("Failure !! Text- Special Registration is missing.");				
				AssertFailedCount++;
			}
			
		/*	Wait list activated is not present on Roster page
		 * 
		System.out.println("Step 7 : Go to the roster for a future event that is Active with the wait list activated");
		Reporter.log("Step 7 :  Go to the roster for a future event that is Active with the wait list activated"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/83177");
			if(SeleniumFunc.GetElementText("css", ".tooltip-container > div > div > dl > dd:nth-child(2)").equalsIgnoreCase("Wait List Activated"))
			{
				System.out.println("Success !! Next to Status displayed - Wait List Activated");
				Reporter.log("Success !! Next to Status displayed - Wait List Activated"); 
			}
			else
			{
				System.out.println("Failure !! Text- Wait List Activated is missing.");
				Reporter.log("Failure !! Text- Wait List Activated is missing.");				
				AssertFailedCount++;
			}*/
			
			
		/*	Event Started is not present on Roster page
		 * 
		System.out.println("Step 8 : Go to the roster for an event that is Active where the event has started but not yet ended.");
		Reporter.log("Step 8 :  Go to the roster for an event that is Active where the event has started but not yet ended."); 
					
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/83177");
			if(SeleniumFunc.GetElementText("css", ".tooltip-container > div > div > dl > dd:nth-child(2)").equalsIgnoreCase("Event Started"))
			{
				System.out.println("Success !! Next to Status displayed - Event Started");
				Reporter.log("Success !! Next to Status displayed - Event Started"); 
			}
			else
			{
				System.out.println("Failure !! Text- Event Started is missing.");
				Reporter.log("Failure !! Text- Event Started is missing.");				
				AssertFailedCount++;
			}*/
			
			
			
		System.out.println("Step 9 : Go to the roster for a past event that is Active.");
		Reporter.log("Step 9 :  Go to the roster for a past event that is Active."); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/83135");
			if(SeleniumFunc.GetElementText("css", ".tooltip-container > div > div > dl > dd:nth-child(2)").equalsIgnoreCase("Waiting for Instructor to Submit Results"))
			{
				System.out.println("Success !! Next to Status displayed - Waiting for Instructor to Submit Results");
				Reporter.log("Success !! Next to Status displayed - Waiting for Instructor to Submit Results"); 
			}
			else
			{
				System.out.println("Failure !! Text- Waiting for Instructor to Submit Results is missing.");
				Reporter.log("Failure !! Text- Waiting for Instructor to Submit Results is missing.");				
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 10 : Go to the roster for an event that is Results Submitted.");
		Reporter.log("Step10 :  Go to the roster for an event that is Results Submitted."); 
					
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/81348");
			if(SeleniumFunc.GetElementText("css", ".tooltip-container > div > div > dl > dd:nth-child(2)").equalsIgnoreCase("Waiting for Administrator to Approve Results"))
			{
				System.out.println("Success !! Next to Status displayed - Waiting for Administrator to Approve Results ");
				Reporter.log("Success !! Next to Status displayed -  Waiting for Administrator to Approve Results "); 
			}
			else
			{
				System.out.println("Failure !! Text- Waiting for Administrator to Approve Results is missing.");
				Reporter.log("Failure !! Text- Waiting for Administrator to Approve Results is missing.");				
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 11 : Go to the roster for an event that is Approved.");
		Reporter.log("Step 11 :  Go to the roster for an event that is Approved."); 
						
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/80577");
			if(SeleniumFunc.GetElementText("css", ".tooltip-container > div > div > dl > dd:nth-child(2)").equalsIgnoreCase("Approved"))
			{
				System.out.println("Success !! Next to Status displayed - Approved ");
				Reporter.log("Success !! Next to Status displayed -  Approved"); 
			}
			else
			{
				System.out.println("Failure !! Text- Approved is missing.");
				Reporter.log("Failure !! Text - Approved is missing.");				
				AssertFailedCount++;
			}
			
		System.out.println("Step 12 : Go to the roster for an event that is Transfer Complete.");
		Reporter.log("Step 12 :  Go to the roster for an event that is Transfer Complete."); 
							
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/83116");
			if(SeleniumFunc.GetElementText("css", ".tooltip-container > div > div > dl > dd:nth-child(2)").equalsIgnoreCase("Transfer Complete"))
			{
				System.out.println("Success !! Next to Status displayed - Transfer Complete ");
				Reporter.log("Success !! Next to Status displayed -  Transfer Complete"); 
			}
			else
			{
				System.out.println("Failure !! Text- Transfer Complete is missing.");
				Reporter.log("Failure !! Text - Transfer Complete is missing.");				
				AssertFailedCount++;
			}
			
			
			/** Marking Test Pass or Fail as per the value of AssertFailedCount variable*/
			 
			if(AssertFailedCount>0)	
			{
			
				//Marking this test as Failed
			
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
				Assert.fail();
		
			}
	}
	
	
	/* Test 13: 
	 * Event > : Verify Auto-Update Map When Creating location
	*/ 
	@Test
	private void Map_AutoUpdate() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 13 : Verify Auto-Update Map When Creating location"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 13 : Verify Auto-Update Map When Creating location"  + "\n" +
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
		AgencyLocationPage agencylocations = new AgencyLocationPage(driver);
	
		System.out.println("Step 1 : Log into Event Manager as a Colorado agency admin : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Log into Event Manager as a Colorado agency admin : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			
			if(SeleniumFunc.IsElementPresent("linkText", "Log Out"))
			{
				SeleniumFunc.ClickOnElement("linkText", "Log Out");
			}
			
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			//Navigate to 'My Event Schedule' page and delete all Events
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//instructorschedule.DeleteAllEvents();
			
			
		System.out.println("Step 2 : Navigate to create location page");
		Reporter.log("Step 2 : Navigate to create location page");
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			instructorlocations.SelectProgram(6);	
			Thread.sleep(2000);
	
			//Clicking on 'Creat Location' link for adding location");
			instructorlocations.CreateLocation.click();	
			Thread.sleep(2000);
			
			String expectedtext = Constants.ApplicationURL_EM +"/location/create/";
			String actualtext = driver.getCurrentUrl();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! 'Creat Location' link is working as expected");
				Reporter.log("Success !! 'Creat Location' link is working as expected"); 
			}
			else
			{
				System.out.println("Failure !! 'Creat Location' link is NOT working" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! 'Creat Location' link is NOT working" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}	
			
			//Pattern image1 = new Pattern("/src/test/resources/Sikuli-Images/Image1.PNG");
			Pattern image2a = new Pattern("/src/test/resources/Sikuli-Images/Image2a.PNG");
			Pattern image3 = new Pattern("/src/test/resources/Sikuli-Images/Image3.PNG");
			Pattern image4 = new Pattern("/src/test/resources/Sikuli-Images/Image4.PNG");
			Screen screen = new Screen();
			
			//Entering required details and clicking on 'Submit' button"
			
		System.out.println("Step 3 : Enter a location name, e.g. Test Map Update.");
		Reporter.log("Step 3 : Enter a location name, e.g. Test Map Update.");
		
			String locationname = "Test Map Update" + JH.GenerateRandomNumber();
			instructorlocations.LocationName.sendKeys(locationname);
			instructorlocations.SpecialInstructions.sendKeys("Have fun");
						
		System.out.println("Step 4 : For address, enter 100 Main Street, then click somewhere else on the page.");
		Reporter.log("Step 4 : For address, enter 100 Main Street, then click somewhere else on the page.");
						
			instructorlocations.Address.sendKeys("100 Main Street Colorado");
			SeleniumFunc.ClickOnElement("css", ".page-header.span12>h3");
			Thread.sleep(4000);
			if(screen.exists(image2a) != null)
			{
				System.out.println("Success !! The map marker moved and  map marker at Longmont, CO.");
				Reporter.log("Success !! The map marker moved and  map marker at Longmont, CO.");
			}
			else
			{
				System.out.println("Failure !! The map marker not moved.");
				Reporter.log("Failure !! The map marker not moved.");
				AssertFailedCount++;
				
			}
			
			
		System.out.println("Step 5 : For city, enter Elizabeth, then click somewhere else on the page.");
		Reporter.log("Step 5 : For city, enter Elizabeth, then click somewhere else on the page.");
			
			instructorlocations.City.sendKeys("Elizabeth");
			SeleniumFunc.ClickOnElement("css", ".page-header.span12>h3");
			
			if(screen.exists(image3) != null)
			{
				System.out.println("Success !! The map marker moved and  map marker at Elizabeth, CO.");
				Reporter.log("Success !! The map marker moved and  map marker at Elizabeth, CO.");
			}
			else
			{
				System.out.println("Failure !! The map marker not moved.");
				Reporter.log("Failure !! The map marker not moved.");
				AssertFailedCount++;
				
			}
			
			
		System.out.println("Step 6 : For ZIP code, enter 80107, then click somewhere else on the page.");
		Reporter.log("Step 6 : For ZIP code, enter 80107, then click somewhere else on the page.");
			
			instructorlocations.Zip.sendKeys("80107");
			SeleniumFunc.ClickOnElement("css", ".page-header.span12>h3");
			
			if(screen.exists(image3) != null)
			{
				System.out.println("Success !! The map marker not moved.");
				Reporter.log("Success !! The map marker not moved..");
			}
			else
			{
				System.out.println("Failure !! The map marker moved.");
				Reporter.log("Failure !! The map marker moved.");
				AssertFailedCount++;
				
			}
			
		System.out.println("Step 7 : Enter in a capacity, region, and county. Then click Create New Location.");
		Reporter.log("Step 7 : Enter in a capacity, region, and county. Then click Create New Location.");
			
			instructorlocations.Capacity.sendKeys("10");
			Thread.sleep(2000);
			SeleniumFunc.SelectValueFromDropdownList("css", "#region","Northeast");
			Thread.sleep(4000);
			//instructorlocations.SelectCounty(2); 
			SeleniumFunc.SelectValueFromDropdownList("css", "#county","Adams");
			Thread.sleep(2000);
			instructorlocations.Submit.click();	
			Thread.sleep(5000);
			System.out.println("Created new Location : "+locationname);
		
		System.out.println("Step 8 : Verify the location should have been created.");
		Reporter.log("Step 8 : Verify the location should have been created.");
		
			if(SeleniumFunc.GetElementText("css", ".alert-flash.alert.alert-success").equals("Your location was successfully created."))
			{
				System.out.println("Success !! Location Created.");
				Reporter.log("Success !! Location Created.");
			}
			else
			{
				System.out.println("Failure !! Fail to create location.");
				Reporter.log("Failure !! Fail to create location.");
				AssertFailedCount++;		
			}
			
			
		System.out.println("Step 9 : View the location created & Verify map marker should be in Elizabeth, CO.");
		Reporter.log("Step 9 : View the location created & Verify map marker should be in Elizabeth, CO.");
		
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("scroll(0,420);");
				
			if(screen.exists(image4) != null)
			{
				System.out.println("Success !! Map marker at Elizabeth, CO.");
				Reporter.log("Success !! Map marker at Elizabeth, CO.");
			}
			else
			{
				System.out.println("Failure !! The map marker showing wrong direction.");
				Reporter.log("Failure !! The map marker showing wrong direction.");
				AssertFailedCount++;
			}
		
			// Delete Location
			
				SeleniumFunc.ClickOnElement("linkText", "Edit");
				SeleniumFunc.ClickOnElement("linkText", "Delete This Location");
				System.out.println("Location : "+locationname +" Deleted Successfully");
				SeleniumFunc.ClickOnElement("css", "#confirm-delete-modal > div.modal-footer > button.btn.btn-primary");
				SeleniumFunc.ClickOnElement("linkText", "Log Out");
			
				
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