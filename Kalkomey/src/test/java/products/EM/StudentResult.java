package products.EM;



import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import pageFactory.EM.InstructorCreateEventPage;
import pageFactory.EM.InstructorEventRosterPage;
import pageFactory.EM.InstructorHomePage;
import pageFactory.EM.InstructorLocationPage;
import pageFactory.EM.InstructorSchedulePage;
import pageFactory.EM.LoginPage;
import pageFactory.EM.PaymentSettingsPage;
import pageFactory.EM.StudentResultPage;



import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class StudentResult 
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
	 * Verify instructor can navigate to results form  and also verify UI
	*/ 
	
	@Test
	private void NavigateToResult_UIVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify instructor can navigate to results form  and also verify UI"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify instructor can navigate to results form  and also verify UI"  + "\n" +
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
		StudentResultPage studresult = new StudentResultPage(driver);
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
				
			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);
			
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
			
		
			searchevent.ClickOnSearchEventLink();
			Thread.sleep(4000);
			
			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);			searchevent.EnterSearchData(EventID);
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

			
			//Add Student for event
			instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();
			Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			//instructorschedule.FirstEventName.click();
			driver.get("https://my-webtest1.register-ed.com/eventregistration/roster/"+EventID);
			Thread.sleep(5000);

			instructoreventroster.SelectAction("Add Student");
			Thread.sleep(5000);

			String username="emstudent"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
			addstud.SelectEyeColor();
			addstud.SelectHairColor();
			addstud.EnterSSNNO("125896336");
			addstud.ClickOnRegisterButton();
			Thread.sleep(5000);

					
			//Enter Result
			instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);

		System.out.println("Step 8 : Verify user is redirected to results page");
		Reporter.log("Step 8 : Verify user is redirected to results page"); 
				
			expectedtext = "Results";
			actualtext = studresult.Title.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Page title is with correct text");
				Reporter.log("Success !! Page title is with correct text");
			}
			else
			{
				System.out.println("Failure !! Page title is with incorrect text" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Page title is with incorrect text" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}

			
			//Going to Event Roster page and deleting  event 
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
				//Auto-generated catch block
			
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
	 * Verify instructor can navigate to Event Information form and can update the details 
	*/ 
    	
	@Test
	private void NavigateToEventInfoAndUpdate() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify instructor can navigate to Event Information form and can update the details"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify instructor can navigate to Event Information form and can update the details"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
		StudentResultPage studresult = new StudentResultPage(driver);		
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
				
			
			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);
			
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
			
		
			searchevent.ClickOnSearchEventLink();
			Thread.sleep(5000);

			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);			searchevent.EnterSearchData(EventID);
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

			
			//Enter Result
			instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();
			Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			//instructorschedule.FirstEventName.click();
			driver.get("https://my-webtest1.register-ed.com/eventregistration/roster/"+EventID);
			Thread.sleep(5000);

			instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);


		System.out.println("Step 8 : Verify user is redirected to results page");
		Reporter.log("Step 8 : Verify user is redirected to results page"); 
				
			expectedtext = "Results";
			actualtext = studresult.Title.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Page title is with correct text");
				Reporter.log("Success !! Page title is with correct text");
			}
			else
			{
				System.out.println("Failure !! Page title is with incorrect text" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Page title is with incorrect text" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
		
		System.out.println("Step 9 : Verify fields available for Edit Event Information page");
		Reporter.log("Step 9 : Verify fields available for Edit Event Information page"); 
		
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");	
		
			studresult.ClickOnEditEventInfoButton();
			Thread.sleep(5000);

			//Verify End Date DatePicker
			
			if(SeleniumFunc.IsElementPresent("css", "#ReportDetail_end_date"))
			{
			
				System.out.println("Success !! End Date DatePicker is present");
				Reporter.log("Success !! End Date DatePicker is present"); 
			}
			else
			{
				System.out.println("Failure !! End Date DatePicker is NOT present");
				Reporter.log("Failure !! End Date DatePicker is NOT present "); 
				AssertFailedCount++;
			}
			
			
			//Verify Start Date DatePicker
			if(SeleniumFunc.IsElementPresent("css", "#ReportDetail_start_date"))
			{
			
				System.out.println("Success !! Start Date DatePicker is present");
				Reporter.log("Success !! Start Date DatePicker is present"); 
			}
			else
			{
				System.out.println("Failure !! Start Date DatePicker is NOT present");
				Reporter.log("Failure !! Start Date DatePicker is NOT present "); 
				AssertFailedCount++;
			}
		
			
			//Verify Total Class Hours
			if(SeleniumFunc.IsElementPresent("css", "#ReportDetail_class_hours"))
			{
			
				System.out.println("Success !! Total Class Hours field is present");
				Reporter.log("Success !! Total Class Hours field is present"); 
			}
			else
			{
				System.out.println("Failure !! Total Class Hours field is NOT present");
				Reporter.log("Failure !! Total Class Hours field is NOT present "); 
				AssertFailedCount++;
			}
			
			//Verify Total Range Hours
			if(SeleniumFunc.IsElementPresent("css", "#ReportDetail_field_hours"))
			{
			
				System.out.println("Success !! Total Range Hours field is present");
				Reporter.log("Success !! Total Range Hours field is present"); 
			}
			else
			{
				System.out.println("Failure !! Total Range Hours field is NOT present");
				Reporter.log("Failure !! Total Range Hours field is NOT present "); 
				AssertFailedCount++;
			}
		
			
			//Verify Comments
			if(SeleniumFunc.IsElementPresent("css", "#ReportDetail_comments"))
			{
			
				System.out.println("Success !! Comments field is present");
				Reporter.log("Success !! Comments field is present"); 
			}
			else
			{
				System.out.println("Failure !! Comments field is NOT present");
				Reporter.log("Failure !! Comments field is NOT present "); 
				AssertFailedCount++;
			}
		
		System.out.println("Step 10 : Go to Event Information and Update the details");
		Reporter.log("Step 10 : Go to Event Information and Update the details"); 
						
			Thread.sleep(5000);

			studresult.EnterClassHours("12");
			studresult.EnterRangeHours("10");
			studresult.SelectTaughtBy();
			studresult.EnterComments("Event Completed");
			studresult.ClickOnSaveButton();
			Thread.sleep(5000);

       
		System.out.println("Step 11 : Verify that Event Information is completed");
		Reporter.log("Step 11 : Verify that Event Information is completed"); 
		
		
			expectedtext = "Complete!";
			actualtext = studresult.EventInfoStatus.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Event information is completed");
				Reporter.log("Success !! Event information is completed");
			}
			else
			{
				System.out.println("Failure !! Event information is not completed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event information is not completed" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}

			
			
			//Going to Event Roster page and deleting  event 
			//so new event can be added with same test data
				
			try {
				instructoreventroster.ReturnToRosterFromResult.click();
				Thread.sleep(5000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				
				
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
	
	
	/* Test 3: 
	* Verify user can enter the result and update student details
	*/ 
       		
   @Test
   private void NavigateToStudentResultAndUpdateStudent() throws Exception
   {
       System.out.println("====" + "\n" +
  						"Test 3 : Verify user can enter the result and update student details"  + "\n" +
			 			"====");
	   Reporter.log("====" + "\n" +
			 			  "Test 3 : Verify user can enter the result and update student details"  + "\n" +
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
		InstructorHomePage instructorhome = new InstructorHomePage(driver);
		StudentResultPage studresult = new StudentResultPage(driver);		

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

					
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
						
		
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			/*SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			Thread.sleep(5000);
*/
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
				
			
			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);
			
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
				
			
			searchevent.ClickOnSearchEventLink();
			Thread.sleep(5000);

			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);			searchevent.EnterSearchData(EventID);
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

				
			//Add Student for event
			instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();
			Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			//instructorschedule.FirstEventName.click();
			driver.get("https://my-webtest1.register-ed.com/eventregistration/roster/"+EventID);
			Thread.sleep(5000);

			instructoreventroster.SelectAction("Add Student");
			Thread.sleep(5000);

			String username="emstudent"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
			addstud.SelectEyeColor();
			addstud.SelectHairColor();
			addstud.EnterSSNNO("125896336");
			addstud.ClickOnRegisterButton();
			Thread.sleep(5000);

					
			//Add Result
			instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);

			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");	
			
			SeleniumFunc.ClickOnElement("css", "#result-subnav div:nth-of-type(3) a");
			Thread.sleep(5000);

			studresult.SelectFinalGrade();
			SeleniumFunc.ClickOnElement("css", ".btn.btn-primary.pull-right");
			Thread.sleep(5000);
				
			//Verify Student Result is completed
				
			actualtext = studresult.StudentResultStatus.getText().trim();
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
					
				
			instructoreventroster.ReturnToRosterFromResult.click();
			Thread.sleep(5000);

				
			//Verify User can go to student edit page
				
			instructoreventroster.ClickOnSelectFirstStudent();
			Thread.sleep(5000);

			instructoreventroster.ClickOnEditStudentButton();
			Thread.sleep(5000);
				
				
			actualtext = SeleniumFunc.GetElementText("css", ".page-title");
			expectedtext = "Student Registration Information";
				

			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! User is navigated to Student Edit page");
				Reporter.log("Success !! User is navigated to Student Edit page");
			}
			else
			{
				System.out.println("Failure !! User is NOT navigated to Student Edit page" + "\n" + "Expected Page Title: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! User is NOT navigated to Student Edit page" + "\n" + "Expected Page Title: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
				AssertFailedCount++;
			}

				
			//Going to Event Roster page and removing student and deleting  event 
			//so new event can be added with same test data
			try {
				SeleniumFunc.ClickOnElement("css", ".btn.btn-danger");	
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
		
		
	/* Test 4: 
	* Verify user can update instructor information
	*/ 

    @Test
	private void InstructorInfoUpdate() throws Exception
	{
		System.out.println("====" + "\n" +
						"Test 4 : Verify user can update instructor information"  + "\n" +
			 			"====");
		Reporter.log("====" + "\n" +
			 			  "Test 4 : Verify user can update instructor information"  + "\n" +
					 	  "====");	
				
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
		StudentResultPage studresult = new StudentResultPage(driver);
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
						
					
			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);
			
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
					
				
			searchevent.ClickOnSearchEventLink();
			Thread.sleep(5000);

			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);			searchevent.EnterSearchData(EventID);
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

					
			//Enter Instructor Information
			instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();
			Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			//instructorschedule.FirstEventName.click();
			driver.get("https://my-webtest1.register-ed.com/eventregistration/roster/"+EventID);
			Thread.sleep(5000);

			instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);
			
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");	
			
			studresult.ClickOnEditInstrInfoButton();
			Thread.sleep(5000);

			studresult.EnterPrepHours("10");
			studresult.EnterClassHrs("10");
			studresult.EnterFieldHrs("10");
			studresult.EnterTravelHrs("10");
			studresult.EnterAdminHrs("10");
			studresult.EnterTrainingHours("10");
			studresult.ClickOnSaveButton();
			Thread.sleep(5000);

					
			//Verify Instructor information is completed
			actualtext = studresult.InstructorInfoStatus.getText().trim();
			expectedtext = "Complete!";

			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Instructor Information is added and completed");
				Reporter.log("Success !! Instructor Information is added and completed");
			}
			else
			{
				System.out.println("Failure !! Instructor Information is not added and completed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Instructor Information" +
							" is not added and completed" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
				AssertFailedCount++;
			}
						
					
					
			//Going to Event Roster page deleting  event so new event can be added with same test data
					
			try {
				instructoreventroster.ReturnToRosterFromResult.click();
				Thread.sleep(5000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				
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
		

	/* Test 5: 
	 * Verify user can view duplicate NY student certificate when trying to enter duplicate certificate ID
	*/ 
	
	@Test
	private void NYViewDuplicateStudCertificate() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify user can view duplicate NY student certificate when trying to enter duplicate certificate ID"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify user can view duplicate NY student certificate when trying to enter duplicate certificate ID"  + "\n" +
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
			instructorlocations.SelectProgram(2);	
			Thread.sleep(5000);

			instructorlocations.ScheduleAnEvent.click();	
			Thread.sleep(5000);
				
		
		System.out.println("Step 3 : On Create Event page , entering all required details and click on 'Create Event' button");
		Reporter.log("Step 3 : On Create Event page , entering all required detailsb and click on 'Create Event' button"); 
		
			/*instructorcreateevent.SelectProgram();
			Thread.sleep(2000);
				
			instructorcreateevent.Location.clear();
			instructorcreateevent.Location.sendKeys("Demo Location for New York");
			Thread.sleep(5000);
			instructorcreateevent.Location_FirstChoice.click();
			Thread.sleep(2000);*/
			
			//instructorcreateevent.GoToNextMonth();
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
				
			
			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);
			
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
			
		
			searchevent.ClickOnSearchEventLink();
			Thread.sleep(5000);

			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);			searchevent.EnterSearchData(EventID);
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

			
			//Add Student for event
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/panel/proctor");
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();	
			Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			//instructorschedule.FirstEventName.click();
			driver.get("https://my-webtest1.register-ed.com/eventregistration/roster/"+EventID);
			Thread.sleep(5000);
			instructoreventroster.SelectAction("Add Student");
			Thread.sleep(5000);

			String username="emstudent"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "New York", "African American", "January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
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

			SeleniumFunc.EnterValueInTextbox("css", ".results-h-certificate-id", "AB000123");
			SeleniumFunc.ClickOnElement("css", ".btn.btn-primary.pull-right");
			Thread.sleep(5000);
			
			//Add Second Student
			instructoreventroster.SelectAction("Add Student");
			username="emstudent"+ JH.GenerateRandomNumber();
			emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("User", "Clarion", emailaddress, "Male", "United States of America", "New York","African American", "January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
			addstud.SelectCounty("Albany");
			addstud.ClickOnRegisterButton();
			Thread.sleep(5000);

			//Add Result
			instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);

			jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");	
			
			SeleniumFunc.ClickOnElement("css", "#result-subnav div:nth-of-type(3) a");
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("css", ".controls label:nth-of-type(1) input:nth-of-type(1)");
			Thread.sleep(5000);

			SeleniumFunc.EnterValueInTextbox("css", ".results-h-certificate-id", "AB000123");
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("css", ".btn.btn-primary.pull-right");
			SeleniumFunc.ClickOnElement("css", ".btn.btn-primary.pull-right");
			Thread.sleep(5000);
			
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
			
			SeleniumFunc.ClickOnElement("css", "#result-subnav div:nth-of-type(3) a");
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
				
			
		// Go to student registration page having same H Certificate ID 
			
			SeleniumFunc.ClickOnElement("css", ".help-block.error a");
			Thread.sleep(5000);

			/*// Store the current window handle
			
			String winHandleBefore = driver.getWindowHandle();

			// Perform the click operation that opens new window

			// Switch to new window opened
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
			}*/
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs2.get(1));

			actualtext = SeleniumFunc.GetElementText("css", ".span10>h4");
			expectedtext = "Registration Information";
			

			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Control is on Student Registration page");
				Reporter.log("Success !! Control is on Student Registration page");
			}
			else
			{
				System.out.println("Failure !! Control is NOT redirected to Student Registration page" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Control NOT redirected to Student Registration page" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			
		/*	// Close the new window, if that window no more required
			driver.close();

			// Switch back to original browser (first window)
			driver.switchTo().window(winHandleBefore);*/

			driver.close();
		    driver.switchTo().window(tabs2.get(0));
		    
			//Remove student from result page
			Thread.sleep(5000);
			SeleniumFunc.ClickOnElement("css", "div:nth-of-type(2) a:nth-of-type(2) span");
			
			Thread.sleep(5000);
			SeleniumFunc.ClickOnElement("css", ".btn.btn-danger.js-proceed");
			Thread.sleep(5000);

			//Go to Roster and delete the event
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
	* Verify user can clear the instructor information which is entered
	*/ 
				
    @Test
	private void ClearInstructorInformation() throws Exception
	{
		System.out.println("====" + "\n" +
						"Test 6 : Verify user can clear the instructor information which is entered"  + "\n" +
			 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Verify user can clear the instructor information which is entered"  + "\n" +
				 	  "====");	
					
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
		StudentResultPage studresult = new StudentResultPage(driver);
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
							
						
			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);
			
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
						
					
			searchevent.ClickOnSearchEventLink();
			Thread.sleep(5000);

			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);			searchevent.EnterSearchData(EventID);
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

						
			//Enter Instructor Information
			instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();	
			Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			//instructorschedule.FirstEventName.click();	
			driver.get("https://my-webtest1.register-ed.com/eventregistration/roster/"+EventID);
			
			Thread.sleep(5000);

			instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);

			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");	
			
			studresult.ClickOnEditInstrInfoButton();
			Thread.sleep(5000);

			studresult.EnterPrepHours("10");
			studresult.EnterClassHrs("10");
			studresult.EnterFieldHrs("10");
			studresult.EnterTravelHrs("10");
			studresult.EnterAdminHrs("10");
			studresult.EnterTrainingHours("10");
			studresult.ClickOnSaveButton();
			Thread.sleep(5000);

						
			//Verify Clear Information link functionality
			jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");	
			
			studresult.ClickOnEditInstrInfoButton();
			Thread.sleep(5000);

			studresult.ClickOnClearFormLink();
			
			Thread.sleep(5000);
			
			if(studresult.PrepHours.getAttribute("value").equals("") && studresult.ClassHrs.getAttribute("value").equals(""))
			{
				System.out.println("Success !! Clear Instructor Information functionality is working properly");
				Reporter.log("Success !! Clear Instructor Information functionality is working properly");
			}
			else
			{
				System.out.println("Failure !! Clear Instructor Information functionality is working properly");
				Reporter.log("Clear Instructor Information functionality is working properly");
							
				AssertFailedCount++;
			}
						
						
			//Going to Event Roster page deleting  event so new event can be added with same test data
			try {
				SeleniumFunc.ClickOnElement("css", ".pull-left.text.text-error");
				Thread.sleep(5000);

				instructoreventroster.ReturnToRosterFromResult.click();
				Thread.sleep(5000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
						
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
	 * Verify user can enter a result for event and submit for approval
	*/ 
			
    @Test
	private void ReviewAndSubmitResult() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Verify user can enter a result for event and submit for approval"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Verify user can enter a result for event and submit for approval"  + "\n" +
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
		StudentResultPage studresult = new StudentResultPage(driver);
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
					
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
				
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
						
			instructorcreateevent.CreateEvent.click();
			Thread.sleep(5000);

			String EventID = instructoreventroster.EventId.getText().trim();  ;
			System.out.println("Event created :"+EventID);
						
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
						
					
			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);
			
			
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
					
				
			searchevent.ClickOnSearchEventLink();
			Thread.sleep(5000);

			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);			searchevent.EnterSearchData(EventID);
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

					
			//Add Student for event
			instructorhome.VisitHomePage.click();	
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();
			Thread.sleep(5000);

			driver.get("https://my-webtest1.register-ed.com/eventregistration/roster/"+EventID);
			//instructorschedule.FirstEventName.click();
			Thread.sleep(5000);

			instructoreventroster.SelectAction("Add Student");
			Thread.sleep(5000);

			String username="emstudent"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
			addstud.SelectEyeColor();
			addstud.SelectHairColor();
			addstud.EnterSSNNO("125896336");
			addstud.ClickOnRegisterButton();
			Thread.sleep(5000);

							
			//Add Result
			instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);

			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");	
			
			SeleniumFunc.ClickOnElement("css", "#result-subnav div:nth-of-type(3) a");
			Thread.sleep(5000);

			studresult.SelectFinalGrade();
			SeleniumFunc.ClickOnElement("css", ".btn.btn-primary.pull-right");
			Thread.sleep(5000);
					
			//Verify Student Result is completed
				
			jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");	
			
			actualtext = studresult.StudentResultStatus.getText().trim();
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
					
					
			//Enter Instructor Information
			jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");	
			
			studresult.ClickOnEditInstrInfoButton();
			Thread.sleep(5000);

			studresult.EnterPrepHours("10");
			studresult.EnterClassHrs("10");
			studresult.EnterFieldHrs("10");
			studresult.EnterTravelHrs("10");
			studresult.EnterAdminHrs("10");
			studresult.EnterTrainingHours("10");
			studresult.ClickOnSaveButton();
			Thread.sleep(5000);

					
			//Verify Instructor information is completed
			actualtext = studresult.InstructorInfoStatus.getText().trim();
			expectedtext = "Complete!";

			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Instructor Information is added and completed");
				Reporter.log("Success !! Instructor Information is added and completed");
			}
			else
			{
				System.out.println("Failure !! Instructor Information is not added and completed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Instructor Information" +
							" is not added and completed" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
						
				AssertFailedCount++;
			}
					
					
			jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");	
			
			studresult.ClickOnEditEventInfoButton();
			
			Thread.sleep(5000);

			studresult.EnterClassHours("12");
			studresult.EnterRangeHours("10");
			studresult.SelectTaughtBy();
			studresult.EnterComments("Event Completed");
			studresult.ClickOnSaveButton();
			Thread.sleep(5000);


		System.out.println("Step 8 : Set past date & Verify that Event Information is completed");
		Reporter.log("Step 8 : Set past date & Verify that Event Information is completed"); 
				
				
			expectedtext = "Complete!";
			actualtext = studresult.EventInfoStatus.getText().trim();
					
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Event information is completed");
				Reporter.log("Success !! Event information is completed");
			}
			else
			{
				System.out.println("Failure !! Event information is not completed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event information is not completed" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
						
				AssertFailedCount++;
			}


			// Set past date for event to Review and Submit the Results
			
			driver.get("https://my-webtest1.register-ed.com/eventregistration/roster/"+EventID);
			Thread.sleep(2000);
			
			SeleniumFunc.ClickOnElement("linkText", "Edit This Event");
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("css", ".event-date-remove");
			Thread.sleep(2000);
			instructorcreateevent.SelectStartDate("1", "00", "AM");
			instructorcreateevent.SelectEndDate("2", "00", "AM");
			
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
			
			SeleniumFunc.ClickOnElement("xpath", "//span[contains(.,'Save Changes')]");
			Thread.sleep(2000);
			
			driver.get("https://my-webtest1.register-ed.com/eventregistration/roster/"+EventID);
			Thread.sleep(2000);
			
			instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);
					
			//Review and Submit the Results
			
			studresult.ClickOnReviewAndSubmitButton();
			Thread.sleep(5000);

						
			expectedtext = "Review and Submit Results";
			actualtext = studresult.ReviewAndSubmitPageHeader.getText().trim();
					
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Review and Submit Page Header is correct");
				Reporter.log("Success !! Review and Submit Page Header is correct");
			}
			else
			{
				System.out.println("Failure !! Review and Submit Page Header is NOT correct" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Review and Submit Page Header is NOT correct" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
						
				AssertFailedCount++;
			}
					
			//Select admin to approve the result
					
			//studresult.SelectAdmin("Automation Testing");
			studresult.ClickOnFinalSubmitButton();
			Thread.sleep(5000);

			expectedtext = "Event results have been submitted.";
			actualtext = studresult.ResultSubmitSuccess.getText().trim();
					
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Event Result submitted");
				Reporter.log("Success !! Event Result submitted");
			}
			else
			{
				System.out.println("Failure !! Error while submitting Event Result" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Error while submitting Event Result" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
						
				AssertFailedCount++;
			}

			
			//Verify Control is on Event Admin Page
			
			expectedtext = "Event Administration";
			actualtext = SeleniumFunc.GetElementText("css", ".span9>h3");
					
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Control is redirected to Event Administration page");
				Reporter.log("Success !! Control is redirected to Event Administration page");
			}
			else
			{
				System.out.println("Failure !! Control is NOT redirected to Event Administration page" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Control is NOT redirected to Event Administration page" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									"Actual: " + "\n" +  actualtext);
						
				AssertFailedCount++;
			}
		
			//Going to admin and deleting  event so new event can be added with same test data
			try {
				

				SeleniumFunc.ClickOnElement("css", ".btn.btn-primary.mtt");
				Thread.sleep(5000);


				SeleniumFunc.ClickOnElement("css", ".btn.btn-danger");
				Thread.sleep(5000);

				SeleniumFunc.ClickOnElement("css", "#reject_event button:nth-of-type(2)");
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
	 * Verify Admin can approve result for an event
	*/ 
			
    @Test
	private void AdminEventResultApproval() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Verify Admin can approve result for an event"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Verify Admin can approve result for an event"  + "\n" +
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
		StudentResultPage studresult = new StudentResultPage(driver);
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
			//String expectedtext = "6:30 pm";
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

					
			//Add Student for event
			instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");

			Thread.sleep(2000);
			
			SeleniumFunc.ClickOnElement("linkText", "Add Student");
			Thread.sleep(5000);

			String username="emstudent"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
			addstud.SelectEyeColor();
			addstud.SelectHairColor();
			addstud.EnterSSNNO("125896336");
			addstud.ClickOnRegisterButton();
			Thread.sleep(5000);

							
			//Add Result
			instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);

			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");	
			
			SeleniumFunc.ClickOnElement("css", "#result-subnav div:nth-of-type(3) a");
			Thread.sleep(5000);

			studresult.SelectFinalGrade();
			SeleniumFunc.ClickOnElement("css", ".btn.btn-primary.pull-right");
			Thread.sleep(5000);

			//Verify Student Result is completed
				
			actualtext = studresult.StudentResultStatus.getText().trim();
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
					
					
			//Enter Instructor Information
			studresult.ClickOnEditInstrInfoButton();
			Thread.sleep(5000);

			studresult.EnterPrepHours("10");
			studresult.EnterClassHrs("10");
			studresult.EnterFieldHrs("10");
			studresult.EnterTravelHrs("10");
			studresult.EnterAdminHrs("10");
			studresult.EnterTrainingHours("10");
			studresult.ClickOnSaveButton();
			Thread.sleep(5000);

					
			//Verify Instructor information is completed
			actualtext = studresult.InstructorInfoStatus.getText().trim();
			expectedtext = "Complete!";

			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Instructor Information is added and completed");
				Reporter.log("Success !! Instructor Information is added and completed");
			}
			else
			{
				System.out.println("Failure !! Instructor Information is not added and completed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Instructor Information" +
							" is not added and completed" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
						
				AssertFailedCount++;
			}
					
					
			studresult.ClickOnEditEventInfoButton();
			Thread.sleep(5000);

			studresult.EnterClassHours("12");
			studresult.EnterRangeHours("10");
			studresult.SelectTaughtBy();
			studresult.EnterComments("Event Completed");
			studresult.ClickOnSaveButton();
			Thread.sleep(5000);


		System.out.println("Step 8 : Verify that Event Information is completed");
		Reporter.log("Step 8 : Verify that Event Information is completed"); 
				
				
			expectedtext = "Complete!";
			actualtext = studresult.EventInfoStatus.getText().trim();
					
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Event information is completed");
				Reporter.log("Success !! Event information is completed");
			}
			else
			{
				System.out.println("Failure !! Event information is not completed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event information is not completed" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
						
				AssertFailedCount++;
			}


			// Set past date for event to Review and Submit the Results
			
			driver.get("https://my-webtest1.register-ed.com/eventregistration/roster/"+EventID);
			Thread.sleep(2000);
						
			SeleniumFunc.ClickOnElement("linkText", "Edit This Event");
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("css", ".event-date-remove");
			Thread.sleep(2000);
			instructorcreateevent.SelectStartDate("1", "00", "AM");
			instructorcreateevent.SelectEndDate("2", "00", "AM");
						
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
						
			SeleniumFunc.ClickOnElement("xpath", "//span[contains(.,'Save Changes')]");
			Thread.sleep(2000);
						
			driver.get("https://my-webtest1.register-ed.com/eventregistration/roster/"+EventID);
			Thread.sleep(2000);
						
			instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);
						
			//Review and Submit the Results
					
			studresult.ClickOnReviewAndSubmitButton();
			Thread.sleep(5000);

			expectedtext = "Review and Submit Results";
			actualtext = studresult.ReviewAndSubmitPageHeader.getText().trim();
					
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Review and Submit Page Header is correct");
				Reporter.log("Success !! Review and Submit Page Header is correct");
			}
			else
			{
				System.out.println("Failure !! Review and Submit Page Header is NOT correct" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Review and Submit Page Header is NOT correct" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
						
				AssertFailedCount++;
			}
					
			//Select admin to approve the result
					
			//studresult.SelectAdmin("Automation Testing");
			studresult.ClickOnFinalSubmitButton();
			Thread.sleep(5000);

			expectedtext = "Event results have been submitted.";
			actualtext = studresult.ResultSubmitSuccess.getText().trim();
					
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Event Result submitted");
				Reporter.log("Success !! Event Result submitted");
			}
			else
			{
				System.out.println("Failure !! Error while submitting Event Result" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Error while submitting Event Result" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
						
				AssertFailedCount++;
			}

			/*	login.ClickOnLogoutButton();
			Thread.sleep(5000);

					
		System.out.println("Step 9 : Login to Admin for result approval : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 9 : Login to Admin for result approval : " + Constants.ApplicationURL_EM +"/login/login"); 
						
					
			login.EnterUsername(Constants.EM_DemoAdmin_Username);
			login.EnterPassword(Constants.EM_DemoAdmin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);*/

		System.out.println("Step 9 : Go to event and approve the result");
		Reporter.log("Step 9 : Go to event and approve the result"); 
							
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/admin/"+EventID);
			studresult.ClickOnReviewAndApproveButton();
			Thread.sleep(2000);

			SeleniumFunc.ClickOnElement("css", ".btn.btn-success");
			Thread.sleep(2000);
			
			expectedtext = "Status" +"\n"+ "Approved";
			actualtext =  SeleniumFunc.GetElementText("css", ".row-fluid.event-detail:nth-of-type(2)");
						
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Event Result Approved");
				Reporter.log("Success !! Event Result Approved");
			}
			else
			{
				System.out.println("Failure !! Error while Approving Event Result" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Error while Approving Event Result" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
							
				AssertFailedCount++;
			}						
		
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(5000);
			
			SeleniumFunc.ClickOnElement("css", ".cancel");			
			Thread.sleep(2000);
			
			SeleniumFunc.ClickOnElement("css", ".btn.btn-danger");	
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

	
	/* Test 9: 
	 *Verify students are listed alphabetically by last name
	*/ 
	
	@Test
	private void VerifyStudentListingOrder() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Verify students are listed alphabetically by last name"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 : Verify students are listed alphabetically by last name"  + "\n" +
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
			instructorlocations.SelectProgram(2);	
			Thread.sleep(5000);

			instructorlocations.ScheduleAnEvent.click();	
			Thread.sleep(5000);
				
		
		System.out.println("Step 3 : On Create Event page , entering all required details and click on 'Create Event' button");
		Reporter.log("Step 3 : On Create Event page , entering all required detailsb and click on 'Create Event' button"); 
		
			/*instructorcreateevent.SelectProgram();
			Thread.sleep(2000);
				
			instructorcreateevent.Location.clear();
			instructorcreateevent.Location.sendKeys("Demo Location for New York");
			Thread.sleep(5000);
			instructorcreateevent.Location_FirstChoice.click();
			Thread.sleep(2000);*/
			
			//instructorcreateevent.GoToNextMonth();
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
				
			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);
			
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
			
		
			searchevent.ClickOnSearchEventLink();
			Thread.sleep(5000);

			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);			searchevent.EnterSearchData(EventID);
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

			
			//Add Student for event
			instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();	
			Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			//instructorschedule.FirstEventName.click();
			driver.get("https://my-webtest1.register-ed.com/eventregistration/roster/"+EventID);
			Thread.sleep(5000);
			instructoreventroster.SelectAction("Add Student");
			Thread.sleep(5000);

			String username="emstudent"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "New York", "African American","January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
			addstud.SelectCounty("Albany");
			addstud.ClickOnRegisterButton();
			Thread.sleep(5000);

			//Add second student
			instructoreventroster.SelectAction("Add Student");
			Thread.sleep(5000);

			username="emstudent"+ JH.GenerateRandomNumber();
			emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("User", "Clarion", emailaddress, "Male", "United States of America", "New York", "African American","January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
			addstud.SelectCounty("Albany");
			addstud.ClickOnRegisterButton();
			Thread.sleep(5000);

			
			//Verify students listed alphabetically by last name
			String FirstStud = SeleniumFunc.GetElementText("css", "#student_select_form tr:nth-of-type(1) a");
			String SecondStud = SeleniumFunc.GetElementText("css", "#student_select_form tr:nth-of-type(2) a");
			if(FirstStud.equals("Clarion, User") && SecondStud.equals("User, Clarion"))
			{
				System.out.println("Success !! Students are listed alphabetically by last name");
				Reporter.log("Success !! Students are listed alphabetically by last name");
			}
			else
			{
				System.out.println("Failure !! Students are NOT listed alphabetically by last name");
				Reporter.log("Failure !! Students are NOT listed alphabetically by last name");
							
				AssertFailedCount++;
			}
			
			//Delete event
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
	

	/* Test 10: 
	 *Verify system is allowing to enter quarter hours for instructor information and not allowing values like 0.36
	*/ 
	
	@Test
	private void TNAllowQuarterHoursForInstructor() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : Verify system is allowing to enter quarter hours for instructor information and not allowing values like 0.36"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10 : Verify system is allowing to enter quarter hours for instructor information and not allowing values like 0.36"  + "\n" +
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
		StudentResultPage studresult = new StudentResultPage(driver);

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
			instructorlocations.SelectProgram(3);	
			Thread.sleep(5000);

			instructorlocations.ScheduleAnEvent.click();	
			Thread.sleep(5000);
				
		
		System.out.println("Step 3 : On Create Event page , entering all required details and click on 'Create Event' button");
		Reporter.log("Step 3 : On Create Event page , entering all required detailsb and click on 'Create Event' button"); 
		
			instructorcreateevent.SelectProgram();
			Thread.sleep(2000);
				
			instructorcreateevent.Location.clear();
			instructorcreateevent.Location.sendKeys("Demo");
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
			String EventID = instructoreventroster.EventId.getText().trim();
			System.out.println("Event Created : "+EventID);
				
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
					
		
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
		//	SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//Thread.sleep(5000);

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
			expectedtext = "Demo";
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
				
			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);
			
			SeleniumFunc.ClickOnElement("css", ".btn.mtt");
			Thread.sleep(2000);

			/*login.ClickOnLogoutButton();
			Thread.sleep(5000);
			
		System.out.println("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			
		System.out.println("Step 6 : Search and Activate event");
		Reporter.log("Step 6 : Search and Activate event"); 
			
		
			searchevent.ClickOnSearchEventLink();
			Thread.sleep(5000);

			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);			searchevent.EnterSearchData(EventID);
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
*/
			System.out.println("Step 7 : Verify Instructor Information with invalid values");
			Reporter.log("Step 7 : Verify Instructor Information with invalid values"); 
					
			//Add Student for event
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/panel/proctor");
			instructorhome.Action_MyEventSchedule.click();
			Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			//instructorschedule.FirstEventName.click();
			driver.get("https://my-webtest1.register-ed.com/eventregistration/roster/"+EventID);
			Thread.sleep(5000);

			instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);

			//Enter Instructor Information with quarter hours
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");	
			
			studresult.ClickOnEditInstrInfoButton();
			Thread.sleep(5000);

			studresult.EnterPrepHours("0.25");
			studresult.EnterClassHrs("0.75");
			studresult.EnterFieldHrs("0.50");
			studresult.EnterAdminHrs("0.25");
			studresult.EnterTrainingHours("0.25");
			studresult.EnterTravelHrs("0.25");
			studresult.ClickOnSaveButton();
			Thread.sleep(5000);

			jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");	
			
			Thread.sleep(2000);
			
			//Verify Instructor information is completed
			actualtext = studresult.InstructorInfoStatus.getText().trim();
			expectedtext = "Complete!";

			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Instructor Information is added and completed");
				Reporter.log("Success !! Instructor Information is added and completed");
			}
			else
			{
				System.out.println("Failure !! Instructor Information is not added and completed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Instructor Information" +
							" is not added and completed" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
						
				AssertFailedCount++;
			}
			
			
			//Enter Instructor Information with invalid values for hours as 0.26
			studresult.ClickOnEditInstrInfoButton();
			Thread.sleep(5000);

			studresult.EnterPrepHours("0.26");
			studresult.EnterClassHrs("0.25");
			studresult.EnterFieldHrs("0.25");
			studresult.EnterAdminHrs("0.25");
			studresult.EnterTrainingHours("0.25");
			studresult.EnterTravelHrs("0.25");
			studresult.ClickOnSaveButton();
			Thread.sleep(5000);

			//Verify validation
			
			if(SeleniumFunc.IsElementPresent("css", ".help-block.error") && SeleniumFunc.IsElementPresent("css", ".alert-flash.alert.alert-danger"))
			{
				System.out.println("Success !! Error message for invalid values is displayed");
				Reporter.log("Success !! Error message for invalid values is displayed");
			}
			else
			{
				System.out.println("Failure !! NO Error message for invalid values is displayed");
				Reporter.log("Failure !! NO Error message for invalid values is displayed");
						
				AssertFailedCount++;
			}
			
			//Deleting Event
			try {
				instructoreventroster.ReturnToRosterFromResult.click();
				Thread.sleep(5000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
						
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
		
	
	

	/* Test 10: 
	 *Verify EM > Standard validations for Instructor hours
	*/ 
	
	@Test
	private void ValidationInstructorHrs() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : Verify EM > Standard validations for Instructor hours"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10 : Verify EM > Standard validations for Instructor hours"  + "\n" +
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
		StudentResultPage studresult = new StudentResultPage(driver);

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
		
			//instructorcreateevent.SelectProgram();
			//Thread.sleep(2000);
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

			String EventID = instructoreventroster.EventId.getText().trim();;
			System.out.println("Event Created : "+EventID);
				
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
					
		
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			Thread.sleep(5000);

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
			expectedtext = "Demo ";
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
				
			instructorschedule.FirstEventName.click();
			Thread.sleep(2000);
			
			SeleniumFunc.ClickOnElement("css", ".btn.mtt");
			Thread.sleep(2000);
			
			login.ClickOnLogoutButton();
			Thread.sleep(5000);
			
		/*System.out.println("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			
		System.out.println("Step 6 : Search and Activate event");
		Reporter.log("Step 6 : Search and Activate event"); 
			
		
			searchevent.ClickOnSearchEventLink();
			Thread.sleep(5000);

			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);		//	searchevent.EnterSearchData(EventID);
			searchevent.ClickOnSearchButton();
			Thread.sleep(5000);

			searchevent.ClickOnSelectEventCheckBox();
			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			Thread.sleep(5000);

			login.ClickOnLogoutButton();
			Thread.sleep(5000);*/

			
		System.out.println("Step 7 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 7 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			
				
			
			//Add Student for event
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/panel/proctor");
			instructorhome.Action_MyEventSchedule.click();
			Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			//instructorschedule.FirstEventName.click();
			driver.get("https://my-webtest1.register-ed.com/eventregistration/roster/"+EventID);
			Thread.sleep(5000);

			instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);

			//Enter Instructor Information with quarter hours
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");	
			
			studresult.ClickOnEditInstrInfoButton();
			Thread.sleep(5000);

			studresult.EnterPrepHours("11.0000");
			studresult.EnterClassHrs("10");
			studresult.EnterFieldHrs("10");
			studresult.EnterAdminHrs("10");
			studresult.EnterTrainingHours("5");
			studresult.EnterTravelHrs("2");
			studresult.ClickOnSaveButton();
			Thread.sleep(5000);

			jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");	
			
			Thread.sleep(2000);
			
			//Verify Instructor information is completed
			actualtext = studresult.InstructorInfoStatus.getText().trim();
			expectedtext = "Complete!";

			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Instructor Information is added and completed");
				Reporter.log("Success !! Instructor Information is added and completed");
			}
			else
			{
				System.out.println("Failure !! Instructor Information is not added and completed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Instructor Information" +
							" is not added and completed" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
						
				AssertFailedCount++;
			}
			
			
			studresult.ClickOnEditInstrInfoButton();
			Thread.sleep(5000);

			actualtext = studresult.PrepHours.getAttribute("value");
			expectedtext = "11";
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Hrs 11.000 converted to 11");
				Reporter.log("Success !! Hrs 11.000 converted to 11");
			}
			else
			{
				System.out.println("Failure !! Hrs 11.000 NOT converted to 11");
				Reporter.log("Failure !! Hrs 11.000 NOT converted to 11");
						
				AssertFailedCount++;
			}
			

			
			studresult.EnterPrepHours("");
			studresult.EnterClassHrs("10");
			studresult.EnterFieldHrs("10");
			studresult.EnterAdminHrs("10");
			
			studresult.ClickOnSaveButton();
			Thread.sleep(5000);

			
			//Verify validation is displayed
			if(SeleniumFunc.IsElementPresent("css", ".help-block.error"))
			{
				System.out.println("Success !! Error message for blank values is displayed");
				Reporter.log("Success !! Error message for blank values is displayed");
				
				actualtext = SeleniumFunc.GetElementText("css", ".help-block.error").trim();
				expectedtext = "Preparation hours is required. Enter 0 if applicable.";
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! Correct validation message is displayed. i.e." + actualtext);
					Reporter.log("Success !! Correct validation message is displayed. i.e." + actualtext);
				}
				else
				{
					System.out.println("Failure !! Wrong validation message is displayed. Expected : " + expectedtext + 
													"Actual :" +  actualtext);
					Reporter.log("Failure !! Wrong validation message is displayed. Expected : " + expectedtext + 
													"Actual :" +  actualtext);
							
					AssertFailedCount++;
				}
			}
			else
			{
				System.out.println("Failure !! NO Error message for blank values");
				Reporter.log("Failure !! NO Error message for blank values");
						
				AssertFailedCount++;
			}
			
			
			studresult.EnterPrepHours("999");
			studresult.EnterClassHrs("10");
			studresult.EnterFieldHrs("10");
			studresult.EnterAdminHrs("10");
			studresult.EnterTrainingHours("5");
			studresult.EnterTravelHrs("2");
			
			studresult.ClickOnSaveButton();
			Thread.sleep(5000);

			
			//Verify validation is displayed
			if(SeleniumFunc.IsElementPresent("css", ".help-block.error"))
			{
				System.out.println("Success !! Error message for exceed limit is displayed");
				Reporter.log("Success !! Error message for exceed limit is displayed");
				
				actualtext = SeleniumFunc.GetElementText("css", ".help-block.error").trim();
				expectedtext = "Instructor prep hours cannot exceed 99.";
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! Correct validation message is displayed. i.e." + actualtext);
					Reporter.log("Success !! Correct validation message is displayed. i.e." + actualtext);
				}
				else
				{
					System.out.println("Failure !! Wrong validation message is displayed. Expected : " + expectedtext + 
													"Actual :" +  actualtext);
					Reporter.log("Failure !! Wrong validation message is displayed. Expected : " + expectedtext + 
													"Actual :" +  actualtext);
							
					AssertFailedCount++;
				}
			}
			else
			{
				System.out.println("Failure !! NO Error message for blank values");
				Reporter.log("Failure !! NO Error message for blank values");
						
				AssertFailedCount++;
			}
			
			
			//Deleting Event
			try {
				instructoreventroster.ReturnToRosterFromResult.click();
				Thread.sleep(5000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
						
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
	
	//Test : 11
	// EM > Student Result Report > Observe capacity is displayed so that an instuctor can guage that how full events are.
	@Test
	private void StudentResultReportCapacity() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test  : Student Result Report > Observe capacity is displayed TO Guess how full events are."  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test  : Student Result Report > Observe capacity is displayed TO Guess how full events are."  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		StudentResultPage studresult = new StudentResultPage(driver);
		
		System.out.println("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
			
		System.out.println("Step 2 : Goto Students results summary page");
		Reporter.log("Step 2 :  Goto Students results summary page"); 	
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/report/view/student_results");
			Thread.sleep(5000);
		
			String expected = "Student Results Summary for Specified Events";
			String actual = studresult.summaryPageTitle.getText().trim();
		
			if(expected.equalsIgnoreCase(actual))
			{
				System.out.println("Success!!! User is on Student Results Summary page");
				Reporter.log("Success!!! User is on Students results summary page");
			}
			else
			{
				System.out.println("Failure!!! User is not on Student Results Summary page");
				Reporter.log("Failure!!! User is not on Students results summary page");
				AssertFailedCount++;
			}
			
		System.out.println("Step 3 : Select Date range for results");
		Reporter.log("Step 3 : Select Date range for results"); 			
			
			studresult.clickOnStartDate();
			Thread.sleep(4000);
			
			SeleniumFunc.SelectValueFromDropdownList("css", ".ui-datepicker-year", "2014");		
			studresult.clickOnDate();
			Thread.sleep(4000);
			
			studresult.clickOnApplyButton();
			Thread.sleep(4000);
			
			
			
		System.out.println("Step 4 : Verify Event Capacity coulumn");
		Reporter.log("Step 4 : Verify Event Capacity coulumn"); 		
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true)", studresult.eventCapacity);
			
			if(SeleniumFunc.IsElementPresent(studresult.eventCapacity))
			{
				System.out.println("Success!!! Event Capacity is displayed");
				Reporter.log("Success!!! Event Capacity is displayed");
			}
			else
			{
				System.out.println("Failure!!! Event Capacity is not displayed");
				Reporter.log("Failure!!! Event Capacity is not displayed");
				AssertFailedCount++;
			}
			
		System.out.println("Step 5 : Verify Registered count is displayed so that an instuctor can guage that how full events are.");
		Reporter.log("Step 5 : Registered count is displayed so that an instuctor can guage that how full events are."); 		
		
			
			js.executeScript("arguments[0].scrollIntoView(true)", studresult.registered);
			
			if(studresult.registered.isDisplayed())
			{
				System.out.println("Success!!! Registered count is displayed so that an instuctor can guage that how full events are.");
				Reporter.log("Success!!! Registered count is displayed so that an instuctor can guage that how full events are.");
			}
			else
			{
				System.out.println("Failure!!! Registered count is not displayed.");
				Reporter.log("Failure!!! Registered count is not displayed");
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
	
	/* Test 12: 
	 *Verify while filling instructor hours for incorrect hours Error message should display at top & next to the field you left blank.
	*/ 
	
	@Test
	private void Error_Message() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 12 : Verify Error message should display at top & next to the field."  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 12 : Verify Error message should display at top & next to the field."  + "\n" +
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
		StudentResultPage studresult = new StudentResultPage(driver);
		AdminSearchEventPage adminsearchevent = new AdminSearchEventPage(driver);
		StudentResultPage studentedit = new StudentResultPage(driver);

		System.out.println("Step 1 : Log in as an instructor for Colorado : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Log in as an instructor for Colorado : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			if(SeleniumFunc.IsElementPresent("linkText", "Log Out"))
			{
				SeleniumFunc.ClickOnElement("linkText", "Log Out");
			}
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			
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
				instructorcreateevent.Location.sendKeys("Demo Location for Colorado");
				Thread.sleep(5000);
				instructorcreateevent.Location_FirstChoice.click();
				Thread.sleep(5000);
				
				instructorcreateevent.SelectStartDate("6", "30", "PM");
				instructorcreateevent.SelectEndDate("7", "30", "PM");
					
				instructorcreateevent.AddEventDate.click();
				Thread.sleep(2000);
					
				instructorcreateevent.CreateEvent.click();
				Thread.sleep(5000);
				String URL = driver.getCurrentUrl();
				System.out.println("Url :" +URL);
					
			System.out.println("Step 4 : Verifying whether Event is added successfully or not");
			Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
			
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
				expectedtext = "Standard Course";
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
				login.ClickOnLogoutButton();
				Thread.sleep(5000);

				
			System.out.println("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
			
				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);
				
				//Go to EM and set Payment Off
				PaymentSettingsPage payment = new PaymentSettingsPage(driver);	
				AgencyHomePage agencyhome= new AgencyHomePage(driver);
				SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
				Thread.sleep(5000);

				agencyhome.ClickToSelectProgram();
				Thread.sleep(5000);

				agencyhome.ClickOnPaymentSettingsLink();
				Thread.sleep(5000);

				payment.SelectPaymentMode("Off");
				payment.ClickOnSaveChanges();	
				Thread.sleep(5000);

				
			System.out.println("Step 6 : Search and Activate event");
			Reporter.log("Step 6 : Search and Activate event"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
				Thread.sleep(5000);

				searchevent.ClickOnSearchEventLink();
				Thread.sleep(5000);

				adminsearchevent.SelectSearchTypeByVisibleText("ID");
				adminsearchevent.SearchData.sendKeys(EventID);
				adminsearchevent.SearchButton.click();
				Thread.sleep(5000);
				Thread.sleep(5000);

				searchevent.ClickOnSelectEventCheckBox();
				searchevent.SelectEventActionByVisibleText("Set Status: Active");
				searchevent.ClickOnGoButton();
				Thread.sleep(5000);

				login.ClickOnLogoutButton();
				Thread.sleep(5000);
				
			System.out.println("Step 7 : Go to an active event's roster and click on Enter Results");
			Reporter.log("Step 7 : Go to an active event's roster and click on Enter Results"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_CLInstructor_Username);
				login.EnterPassword(Constants.EM_CLInstructor_Password);

				login.ClickOnLogInButton();			
				Thread.sleep(5000);
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/" + EventID);
				Thread.sleep(5000);
				SeleniumFunc.ClickOnElement("linkText", "Enter Results");
				studentedit.ClickOnEditInstrInfoButton();
				Thread.sleep(5000);

				studresult.EnterRangeHours2("10");
				studresult.EnterPrepHours("8");
				studresult.EnterTravelHrs("8");
				studresult.EnterInstrPin("1234");
				studresult.ClickOnSaveButton();
				Thread.sleep(5000);
				
			System.out.println("Step 8 : After Save the changes!! Verify validation message should present at top");
			Reporter.log("Step 8 : After Save the changes!! Verify validation message should present at top");
				
				String ExpectedValidation = "Please correct the highlighted fields.";
				String ActualValidation = SeleniumFunc.GetElementText("css", ".alert-flash.alert.alert-danger");
				if(ActualValidation.equals(ExpectedValidation))
				{
					System.out.println("Success !! Red box at the top that says, Please correct the highlighted fields is present. Validation Message: "+ExpectedValidation);
					Reporter.log("Success !! Red box at the top that says, Please correct the highlighted fields is present. Validation Message: "+ExpectedValidation);
					
				}
				else
				{
					System.out.println("Failure !!  Red validation box at the top is missing. Actual: "+ActualValidation + " Expected: "+ExpectedValidation);
					Reporter.log("Failure !! Red validation box at the top is missing. Actual: "+ActualValidation + " Expected: "+ExpectedValidation);
					AssertFailedCount++;
				}
				
			System.out.println("Step 9 : Scroll Down and verify valdidation error messages should be present next to the field you left blank.");
			Reporter.log("Step 9 : Scroll Down and verify valdidation error messages should be present next to the field you left blank.");
				
				String Expected = "Class hours is required. Enter 0 if applicable.";
				String actual = SeleniumFunc.GetElementText("css", ".help-block.error");
				
				if(ActualValidation.equals(ExpectedValidation))
				{
					System.out.println("Success !! Validation message present next to the field. Validation Message Saying: "+Expected);
					Reporter.log("Success !! Validation message present next to the field. Validation Message Saying: "+Expected);
				}
				else
				{
					System.out.println("Failure !!  Validation message missing next to the field. Actual: "+actual + " Expected: "+Expected);
					Reporter.log("Failure !! Validation message missing next to the field. Actual: "+actual + " Expected: "+Expected);
					AssertFailedCount++;
				}
				
				//Delete Event
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
	
	/* Test 13: 
	 *Verify instructor able to add an additional date that is not a class date
	*/ 
	
	@Test
	private void Add_AnotherDate() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 13 : Verify instructor able to add an additional date that is not a class date."  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 13 :Verify instructor able to add an additional date that is not a class date."  + "\n" +
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
		StudentResultPage studresult = new StudentResultPage(driver);
		AdminSearchEventPage adminsearchevent = new AdminSearchEventPage(driver);
		StudentResultPage studentedit = new StudentResultPage(driver);

		System.out.println("Step 1 : Log in as an instructor for Colorado : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Log in as an instructor for Colorado : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			if(SeleniumFunc.IsElementPresent("linkText", "Log Out"))
			{
				SeleniumFunc.ClickOnElement("linkText", "Log Out");
			}
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			
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
				instructorcreateevent.Location.sendKeys("Demo Location for Colorado");
				Thread.sleep(5000);
				instructorcreateevent.Location_FirstChoice.click();
				Thread.sleep(5000);
				
				instructorcreateevent.SelectStartDate("6", "30", "PM");
				instructorcreateevent.SelectEndDate("7", "30", "PM");
					
				instructorcreateevent.AddEventDate.click();
				Thread.sleep(2000);
					
				instructorcreateevent.CreateEvent.click();
				Thread.sleep(5000);
				String URL = driver.getCurrentUrl();
				System.out.println("Url :" +URL);
					
			System.out.println("Step 4 : Verifying whether Event is added successfully or not");
			Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
			
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
				expectedtext = "Standard Course";
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
				login.ClickOnLogoutButton();
				Thread.sleep(5000);

				
			System.out.println("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
			
				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);
				
				//Go to EM and set Payment Off
				PaymentSettingsPage payment = new PaymentSettingsPage(driver);	
				AgencyHomePage agencyhome= new AgencyHomePage(driver);
				SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
				Thread.sleep(5000);

				agencyhome.ClickToSelectProgram();
				Thread.sleep(5000);

				agencyhome.ClickOnPaymentSettingsLink();
				Thread.sleep(5000);

				payment.SelectPaymentMode("Off");
				payment.ClickOnSaveChanges();	
				Thread.sleep(5000);

				
			System.out.println("Step 6 : Search and Activate event");
			Reporter.log("Step 6 : Search and Activate event"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
				Thread.sleep(5000);

				searchevent.ClickOnSearchEventLink();
				Thread.sleep(5000);

				adminsearchevent.SelectSearchTypeByVisibleText("ID");
				adminsearchevent.SearchData.sendKeys(EventID);
				adminsearchevent.SearchButton.click();
				Thread.sleep(5000);
				Thread.sleep(5000);

				searchevent.ClickOnSelectEventCheckBox();
				searchevent.SelectEventActionByVisibleText("Set Status: Active");
				searchevent.ClickOnGoButton();
				Thread.sleep(5000);

				login.ClickOnLogoutButton();
				Thread.sleep(5000);
				
			System.out.println("Step 7 : Go to an active event's roster and click on Enter Results");
			Reporter.log("Step 7 : Go to an active event's roster and click on Enter Results"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_CLInstructor_Username);
				login.EnterPassword(Constants.EM_CLInstructor_Password);

				login.ClickOnLogInButton();			
				Thread.sleep(5000);
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/" + EventID);
				Thread.sleep(5000);
				SeleniumFunc.ClickOnElement("linkText", "Enter Results");
				studentedit.ClickOnEditInstrInfoButton();
				Thread.sleep(5000);
				
			System.out.println("Step 8 :Click on Add Another Date.");
			Reporter.log("Step 8 : Click on Add Another Date."); 
				
				SeleniumFunc.ClickOnElement("linkText", "+ Add Another Date");
				String expected = "Date";
				String actual = SeleniumFunc.GetElementText("css", "div.span10 > ul > div:nth-child(1) > label > strong");
				
			System.out.println("Step 9 :Verify The bold text should say Date. It should not say Class Date.");
			Reporter.log("Step 9 : Verify The bold text should say Date. It should not say Class Date."); 
				
				if(actual.equals(expected))
				{
					System.out.println("Success !! The bold text 'Date' is present");
					Reporter.log("Success !! The bold text 'Date' is present");
				}
				else
				{
					System.out.println("Failure !! The bold text 'Date' is missing. Actual: "+actual + " Expected: "+expected);
					Reporter.log("Failure !! The bold text 'Date' is missing. Actual: "+actual + " Expected: "+expected);
					AssertFailedCount++;
					
				}
				
					//Delete Event					
					SeleniumFunc.ToGoToUrl(URL);
					Thread.sleep(2000);
					SeleniumFunc.ClickOnElement("linkText", "Delete Event");					
					Thread.sleep(2000);				
					SeleniumFunc.ClickOnElement("linkText", "Delete Event");
					Thread.sleep(2000);
					SeleniumFunc.ClickOnElement("linkText", "Log Out");
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
	
	
	/* Test 14: 
	 *Verify  event information hours to be collected to the nearest half hour
	 *Test Case ID : 21606
	*/ 
	
	@Test
	private void HalfHours_ReportField() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 14 : Verify  event information hours to be collected to the nearest half hour."  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 14 :Verify  event information hours to be collected to the nearest half hour."  + "\n" +
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
		StudentResultPage studresult = new StudentResultPage(driver);
		AdminSearchEventPage adminsearchevent = new AdminSearchEventPage(driver);
		StudentResultPage studentedit = new StudentResultPage(driver);

		System.out.println("Step 1 : Log into Event Manager as a Montana Fish Wildlife and Parks instructor who has an Active event ready for results submission. : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 :Log into Event Manager as a Montana Fish Wildlife and Parks instructor who has an Active event ready for results submission. " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			
			if(SeleniumFunc.IsElementPresent("linkText", "Log Out"))
			{
				SeleniumFunc.ClickOnElement("linkText", "Log Out");
			}
			
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
			
		System.out.println("Step 2 : Go to the event's roster & Click Enter Results.");
		Reporter.log("Step 2 : Go to the event's roster & Click Enter Results."); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/admin/82968");
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");
			SeleniumFunc.ClickOnElement("linkText", "Enter Results");
			
		System.out.println("Step 3 : Go to the event's roster & Click Enter Results.");
		Reporter.log("Step 3 : Go to the event's roster & Click Enter Results."); 
		
			studresult.ClickOnEditEventInfoButton();
						
		System.out.println("Step 4 : For the hours fields, enter a variety of numbers with half-hour, quarter-hour, and whole hour precision (e.g. 1.5, 1.25, 2).");
		Reporter.log("Step 4 : For the hours fields, enter a variety of numbers with half-hour, quarter-hour, and whole hour precision (e.g. 1.5, 1.25, 2)."); 
			
			studresult.EnterVarietyHours();
			studresult.ClickOnSaveButton();
			studresult.ClickOnEditEventInfoButton();
			
		System.out.println("Step 5 : Verify error message should display next to that field.");
		Reporter.log("Step 5 : Verify error message should display next to that field.");
		
			List<WebElement> li = driver.findElements(By.cssSelector(".help-block.error"));
			
			if(SeleniumFunc.IsElementPresent("css", ".help-block.error"))
			{
				System.out.println("Success !! Validation messages displayed next to the fields.");
				Reporter.log("Success !! Validation messages displayed next to the fields.");
				System.out.println("---Validation Messages---");
			for(int i=0 ; i<li.size() ; i++ )
			{
				String errorMessage = li.get(i).getText();
				System.out.println(""+errorMessage);
				
			}
			}
			else
			{
				System.out.println("Failure !! Validation messages are missing.");
				Reporter.log("Failure !!  Validation messages are missing.");
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