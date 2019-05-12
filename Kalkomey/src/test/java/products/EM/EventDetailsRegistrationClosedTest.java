package products.EM;

 
 
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.EM.AdminSearchEventPage;
import pageFactory.EM.EMAddStudentPage;
import pageFactory.EM.ErrorPage;
import pageFactory.EM.InstructorCreateEventPage;
import pageFactory.EM.InstructorEventRosterPage;
import pageFactory.EM.InstructorHomePage;
import pageFactory.EM.InstructorLocationPage;
import pageFactory.EM.InstructorSchedulePage;
import pageFactory.EM.LoginPage;
import pageFactory.EM.RegisterEdEventView;



import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class EventDetailsRegistrationClosedTest 
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
	 * Verify the details for the event which is closed
	 */ 
	@Test
	private void UI_EventRegistrationClosed() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify the details for the event which is closed"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 :Verify the details for the event which is closed"  + "\n" +
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
		RegisterEdEventView eventview = new RegisterEdEventView(driver);
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
			
			/*instructorcreateevent.GoToNextMonth();*/
			//instructorcreateevent.SelectDate("5");
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
					
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
			instructorcreateevent.EnterEventCapacity("1");
			instructorcreateevent.EnterWaitListCapacity("0");
			
			instructorcreateevent.EnterSpecialInstruction("Children under the age of 12 must be accompanied by an adult.");

			instructorcreateevent.CreateEvent.click();
			Thread.sleep(5000);
			String URL = driver.getCurrentUrl();
			Thread.sleep(2000);
				
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

			String EventID = instructoreventroster.EventId.getText().trim();
			//String URL = driver.getCurrentUrl();
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
		  	
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
			Thread.sleep(5000);

			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);
			SeleniumFunc.ClickOnElement("id", "search-button");
			//searchevent.ClickOnSearchButton();
			Thread.sleep(5000);

			searchevent.ClickOnSelectEventCheckBox();
			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			Thread.sleep(5000);

			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			
		System.out.println("Step 7 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 7 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
	
			
			instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

			
			
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");

			
			Thread.sleep(2000);
			
			SeleniumFunc.ClickOnElement("linkText", "Add Student");
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

			instructoreventroster.SelectAction("Add Student");
			Thread.sleep(5000);*/

			String username="emstudent"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
			addstud.SelectEyeColor();
			addstud.SelectHairColor();
			addstud.EnterSSNNO("125896336");
			addstud.ClickOnRegisterButton();
			Thread.sleep(5000);

					
	
		System.out.println("Step 8 : Go to register-ed and Verify Event Name and Other Event Page details");
		Reporter.log("Step 8 : Go to register-ed and Verify Event Name and Other Event Page details"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);
			Thread.sleep(5000);
			
			//Verifying Event Name
			expectedtext = "Demo Hunter";
			actualtext = eventview.EventName.getText().trim() ;

			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Event Name is displayed properly. i.e. "+ expectedtext);
				Reporter.log("Success !! Event Name is displayed properly. i.e. "+ expectedtext);
			
			}
			else
			{
					
				System.out.println("Failure !! Event Name is NOT displaying properly" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						"Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event Name is NOT displaying properly" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						"Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
			
			}
			
				
			//Verifying Registration Status
			expectedtext = "Registration Closed";			
			actualtext = eventview.RegistrationStatus.getText().trim() ;
		
			if(actualtext.contains(expectedtext))
			{
				
				System.out.println("Success !! Registration Status is displaying properly. i.e. "+ 
						expectedtext);
				Reporter.log("Success !! Registration Status is displaying properly. i.e. "+ 
						expectedtext);
				
			}
			else
			{
				
				System.out.println("Failure !! Registration Status is NOT displaying properly"
							+ "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Registration Status is NOT displaying properly"
							 + "\n" + "Expected : "  
							 + "\n" + expectedtext + "\n" + 
							"Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
				
			}
						
				
			//Verify Register Closed button is present
			expectedtext = "Registration Closed";
			actualtext = eventview.RegistrationButton.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
				
				System.out.println("Success !! Register Closed button present");
				Reporter.log("Success !! Register Closed button present");
				
			}
			else
			{
				
				System.out.println("Failure !! Register Closed button is NOT displaying"
							+ "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Register Closed button is NOT displaying"
							 + "\n" + "Expected : "  
							 + "\n" + expectedtext + "\n" + 
							"Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
				
			}
				
				
			//Verify Location Section
			expectedtext = "Demo Agency Location" + "\n" +
							   "Street 3" + "\n" + "Ts" + "\n" + "Norfolk, CO 55555" + "\n" +
							   "Room: 5" + "\n" +
                               "Get directions";

			actualtext = eventview.EventLocation.getText().trim();
				
			if(actualtext.equals(expectedtext))
			{
			
				System.out.println("Success !! Seat Status is displaying properly. i.e. "+ expectedtext);
				Reporter.log("Success !! Seat Status is displaying properly. i.e. "+ expectedtext);
				
			}
			else
			{
				
				System.out.println("Failure !! Seat Status is NOT displaying properly"
							+ "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Seat Status is NOT displaying properly"
							 + "\n" + "Expected : "  
							 + "\n" + expectedtext + "\n" + 
							"Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
				
			}
				
			//Verify Number of seats remaining
			expectedtext = "0 of 1 seats remaining";
			actualtext = eventview.SeatStatus.getText().trim() ;
				
			if(actualtext.equals(expectedtext))
			{
				
				System.out.println("Success !! Seat Status is displaying properly. i.e. "+ expectedtext);
				Reporter.log("Success !! Seat Status is displaying properly. i.e. "+ expectedtext);
				
			}
			else
			{
				
				System.out.println("Failure !! Seat Status is NOT displaying properly" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Seat Status is NOT displaying properly" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							"Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
				
			}
				
				
			//Verify Special Instruction Section
			expectedtext = "Special Instructions for All Students" +
							"\n"+ "Children under the age of 12 " +
							"must be accompanied by an adult.";
			actualtext = eventview.SpecialInstruction.getText().trim() ;
				
			if(actualtext.equals(expectedtext))
			{
				
				System.out.println("Success !! Special Instructions are displaying properly. i.e. "+ expectedtext);
				Reporter.log("Success !! Special Instructions are displaying properly. i.e. "+ expectedtext);
				
			}
			else
			{
				
				System.out.println("Failure !! Special Instructions are NOT displaying properly"
							+ "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
     					 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Special Instructions are NOT displaying properly"
     					 	+ "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							"Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
				
			}
				
				
			//Verify Details Section
				
							
				
			//Cancellation Policy
			expectedtext = "Cancellation Policies" +
						"\n" + "You must cancel your registration before";
			actualtext = eventview.CancellationPolicy.getText().trim() ;
				
			if(actualtext.contains(expectedtext))
			{
				
				System.out.println("Success !! Cancellation Policies are displaying properly.");
				Reporter.log("Success !! Cancellation Policies are displaying properly.");
				
			}
			else
			{
			
				System.out.println("Failure !! Cancellation Policies are NOT displaying properly" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Cancellation Policies are NOT displaying properly" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						"Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
			
			}
			
			
			//Minimum Age Policy
			//expectedtext = "Minimum-Age Policy" +	"\n" + "All registrants for this event must be";
			//expectedtext = "New Age Policy"+ "\n" +"New Max Age Policy";
			//String expectedtext1 = "All registrants for this event must be 12-40 years of age"+"\n";
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
			
			/*
						
			try {
				SeleniumFunc.ToGoToUrl(URL);
				Thread.sleep(5000);

				//Deleting event so new event can be added with same test data 
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
				
			}*/
			
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(5000);
			
			SeleniumFunc.ClickOnElement("linkText", "Cancel Event");
			
			Thread.sleep(5000);
			
			
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
			System.out.println("------Test Case failed ,Deleting Event------");
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(5000);
			System.out.println("Event ID : "+EventID);
			
			String actual = SeleniumFunc.GetElementText("css", "#page > div.content.clearfix > div.row-fluid > div.span9 > div.row-fluid > div.span8 > div:nth-child(2) > div.span9 > span");
			if(actual.equals("Canceled"))
			{
			
			System.out.println("Event: "+EventID+" Deleted Successfully!!");
			}
			else if(!actual.equals("Canceled"))
			{
				SeleniumFunc.ClickOnElement("linkText", "Cancel Event");
				
				Thread.sleep(2000);
				
				
				SeleniumFunc.ClickOnElement("linkText", "Unschedule and Remove Event");

				
				Thread.sleep(2000);

				
				SeleniumFunc.ClickOnElement("css", "body > div.stage > div.content > div.clear > a:nth-child(1) > span");
				
				Thread.sleep(2000);

			
				System.out.println("Event: "+EventID+" Deleted Successfully!!");
			//Marking this test as Failed
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
	}
	}

}