package products.EM;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod; 
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.EM.AdminSearchEventPage;
import pageFactory.EM.AgencyHomePage;
import pageFactory.EM.ErrorPage;
import pageFactory.EM.InstructorCreateEventPage;
import pageFactory.EM.InstructorEventRosterPage;
import pageFactory.EM.InstructorHomePage;
import pageFactory.EM.InstructorLocationPage;
import pageFactory.EM.InstructorSchedulePage;
import pageFactory.EM.LoginPage;
import pageFactory.EM.PaymentSettingsPage;
import pageFactory.EM.RegisterEdEventView;
import pageFactory.EM.RegisterEdRegisterStudentPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class RegistrationSecondStepTest 
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
	 * Verify user can cancel registration by clicking on Cancel Registration link
	*/ 
	@Test
	private void VerifyCancelLinkFunctionalityStep2() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify user can cancel registration by clicking on Cancel Registration link"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify user can cancel registration by clicking on Cancel Registration link"  + "\n" +
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

				
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
					
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
			String eventid= instructorschedule.EventId.getText().trim();

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
			Thread.sleep(10000);

			//Go to EM and set Payment Off
			PaymentSettingsPage payment = new PaymentSettingsPage(driver);	
			AgencyHomePage agencyhome= new AgencyHomePage(driver);
			
			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("scroll(477,1126)");
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

			agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/payment/13");
			//agencyhome.ClickOnPaymentSettingsLink();
			Thread.sleep(5000);

			payment.SelectPaymentMode("Off");
			payment.ClickOnSaveChanges();	
			Thread.sleep(5000);

		System.out.println("Step 6 : Search and Activate event");
		Reporter.log("Step 6 : Search and Activate event"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency/?");
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

			
		System.out.println("Step 7 : Go to Register-Ed and verify UI of first page of registration");
		Reporter.log("Step 7 : Go to Register-Ed and verify functionality of Register Now button"); 
		
			
			driver.get(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);
			Thread.sleep(8000);

		

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
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickToSelectGender();
			((JavascriptExecutor)driver).executeScript("scroll(584,1165)");
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);

			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(5000);

		System.out.println("Step 6 : Click on Cancel Registration link and verify whether user redirects to event details page");
		Reporter.log("Step 6 : Click on Cancel Registration link and verify whether user redirects to event details page"); 
			
			//SeleniumFunc.ClickOnElement("css", ".btn.cancel:nth-of-type(1)");
			//Thread.sleep(4000);
			((JavascriptExecutor)driver).executeScript("scroll(696,1724)");
			SeleniumFunc.ClickOnElement("css", ".l-cancel-action a");
			Thread.sleep(5000);
			SeleniumFunc.AcceptAlertAndGetText();			
			
			//Verify user is redirected to event details page
			
			expectedtext = "Register Now";
			actualtext = eventview.RegisterNow.getText().trim();
	
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! User is redirected to Event Details page.");
				Reporter.log("Success !! User is redirected to Event Details page.");
			
			}
			else
			{
			
				System.out.println("Failure !! Success !! User is NOT redirected to Event Details page." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Success !! User is NOT redirected to Event Details page." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						"Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
			
			}
				
			
			
				//Login as admin and delete event
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				Thread.sleep(4000);
				//SeleniumFunc.AcceptAlertAndGetText();
				Thread.sleep(5000);

				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);

				login.ClickOnLogInButton();			
				Thread.sleep(5000);

				//Going to Event Roster page and removing student and deleting  event 
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				System.out.println("Event: "+eventid+" Deleted Successfully!!");
				Thread.sleep(5000);
		
			
		
		/*
		 * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 */
	 	if(AssertFailedCount>0)	
		{
	 		//Login as admin and delete event
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(4000);
			//SeleniumFunc.AcceptAlertAndGetText();
			Thread.sleep(5000);

			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);

			login.ClickOnLogInButton();			
			Thread.sleep(5000);

			//Going to Event Roster page and removing student and deleting  event 
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
	
	
	/* Test 2: 
	 * Verify presence of 'Add another student' link - group registration step 2
	*/ 
	@Test
	private void AddAnotherStudentLinkStep2() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify presence of 'Add another student' link - group registration step 2"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify presence of 'Add another student' link - group registration step 2"  + "\n" +
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
			String eventid= instructorschedule.EventId.getText().trim();
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

			//Go to EM and set Payment Off
			PaymentSettingsPage payment = new PaymentSettingsPage(driver);	
			AgencyHomePage agencyhome= new AgencyHomePage(driver);
			((JavascriptExecutor)driver).executeScript("scroll(477,1126)");
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

			agencyhome.ClickToSelectProgram();
			Thread.sleep(8000);
			//((JavascriptExecutor)driver).executeScript("scroll(297,0)");
			//agencyhome.ClickOnPaymentSettingsLink();
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/payment/13");
			Thread.sleep(5000);

			payment.SelectPaymentMode("Off");
			payment.ClickOnSaveChanges();	
			Thread.sleep(5000);

		System.out.println("Step 6 : Search and Activate event");
		Reporter.log("Step 6 : Search and Activate event"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency/?");
			searchevent.ClickOnSearchEventLink();
			Thread.sleep(5000);

			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);	
			//searchevent.EnterSearchData(EventID);
			searchevent.ClickOnSearchButton();
			Thread.sleep(5000);

			searchevent.ClickOnSelectEventCheckBox();
			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			Thread.sleep(5000);

			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			
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
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickToSelectGender();
			((JavascriptExecutor)driver).executeScript("scroll(584,1165)");
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);

			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(5000);

		System.out.println("Step 6 : Verify Add Student Link is present at step 2, and can redirect to add student page");
		Reporter.log("Step 6 : Verify Add Student Link is present at step 2, and can redirect to add student page"); 
			
		
			
			//Verify Add Student Link is present on step 2
			
				
			if(SeleniumFunc.IsElementPresent("css", "#add_student button"))
			{
			
				System.out.println("Success !! Add Student Link is present at step 2.");
				Reporter.log("Success !! Add Student Link is present at step 2.");
			
			}
			else
			{
			
				System.out.println("Failure !! Success !! Add Student Link is NOT present at step 2." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Success !! Add Student Link is NOT present at step 2." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						"Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
			
			}
				
		
			
		
				//Login as admin and delete event
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				Thread.sleep(4000);
				
				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);

				login.ClickOnLogInButton();			
				Thread.sleep(5000);

				//Going to Event Roster page and removing student and deleting  event 
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				System.out.println("Event: "+eventid+" Deleted Successfully!!");
				Thread.sleep(5000);
			
			
		
		/*
		 * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 */
	 	if(AssertFailedCount>0)	
		{
	 		//Login as admin and delete event
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(4000);
			SeleniumFunc.AcceptAlertAndGetText();
			Thread.sleep(5000);

			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);

			login.ClickOnLogInButton();			
			Thread.sleep(5000);

			//Going to Event Roster page and removing student and deleting  event 
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
	 * Verify functionality of Edit Student Details
	*/ 
	@Test
	private void EditStudentDetails() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify functionality of Edit Student Details"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify functionality of Edit Student Details"  + "\n" +
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

			
			//Go to EM and set Payment Off
			PaymentSettingsPage payment = new PaymentSettingsPage(driver);	
			AgencyHomePage agencyhome= new AgencyHomePage(driver);
			((JavascriptExecutor)driver).executeScript("scroll(477,1126)");
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

			agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/payment/13");
			//agencyhome.ClickOnPaymentSettingsLink();
			Thread.sleep(5000);

			payment.SelectPaymentMode("Off");
			payment.ClickOnSaveChanges();	
          
			Thread.sleep(5000);

		System.out.println("Step 6 : Search and Activate event");
		Reporter.log("Step 6 : Search and Activate event"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency/?");
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
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickToSelectGender();
			((JavascriptExecutor)driver).executeScript("scroll(584,1165)");
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);

			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(5000);

		System.out.println("Step 6 : Click on Edit and update the sudents details");
		Reporter.log("Step 6 : Click on Edit and update the sudents details"); 
			
			//SeleniumFunc.ClickOnElement("css", ".btn.cancel:nth-of-type(1)");
			//Thread.sleep(2000);
			
			SeleniumFunc.ClickOnElement("css", ".btn.edit");
			Thread.sleep(10000);

			//Update student details
			registerstudent.RegisterNewStudent("Rohit", "Ware", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.ClickOnAddThisStudentButton();
			Thread.sleep(20000);
			
			
			//Verify updated details
			expectedtext = "Rohit Ware";
			actualtext = SeleniumFunc.GetElementText("css", ".student-summary strong");
		
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
				
				System.out.println("Success !! User is updated.");
				Reporter.log("Success !! User is updated.");
			
			}
			else
			{
			
				System.out.println("Failure !! User details is NOT updated." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! User details is NOT updated." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}	
		
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				Thread.sleep(4000);
				SeleniumFunc.AcceptAlertAndGetText();
				Thread.sleep(5000);

				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);

				login.ClickOnLogInButton();			
				Thread.sleep(5000);

				//Going to Event Roster page and removing student and deleting  event 
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				System.out.println("Event: "+eventid+" Deleted Successfully!!");
				Thread.sleep(5000);
			
			
		
		/*
		 * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 */
	 	if(AssertFailedCount>0)	
		{
	 		//Login as admin and delete event
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(4000);
			SeleniumFunc.AcceptAlertAndGetText();
			Thread.sleep(5000);

			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);

			login.ClickOnLogInButton();			
			Thread.sleep(5000);

			//Going to Event Roster page and removing student and deleting  event 
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
	
	
	/* Test 4: 
	 * Verify Elements on step 2 for an event for which payment is not required
	*/ 
	@Test
	private void UI_Step2WithoutPayment() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify Elements on step 2 for an event for which payment is not required"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify Elements on step 2 for an event for which payment is not required"  + "\n" +
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
			String eventid= instructorschedule.EventId.getText().trim();
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

			
			//Go to EM and set Payment Off
			PaymentSettingsPage payment = new PaymentSettingsPage(driver);	
			AgencyHomePage agencyhome= new AgencyHomePage(driver);
			((JavascriptExecutor)driver).executeScript("scroll(477,1126)");
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

			agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/payment/13");
			//agencyhome.ClickOnPaymentSettingsLink();
			Thread.sleep(5000);

			payment.SelectPaymentMode("Off");
			payment.ClickOnSaveChanges();	
			Thread.sleep(5000);

		System.out.println("Step 6 : Search and Activate event");
		Reporter.log("Step 6 : Search and Activate event"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency/?");
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
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickToSelectGender();
			((JavascriptExecutor)driver).executeScript("scroll(584,1165)");
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);
			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(5000);
			
		System.out.println("Step 6 : Verify UI for Step 2");
		Reporter.log("Step 6 : Verify UI for Step 2"); 
			
			
			//Verify breadcrumb 
		
			expectedtext = "Add Students";
			actualtext = SeleniumFunc.GetElementText("css", "#content div ol").trim();
	
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Breadcrumb is displayed with proper text. i.e.  " + actualtext);
				Reporter.log("Success !! Breadcrumb is displayed with proper text. i.e.  " + actualtext);
		
			}
			else
			{
		
				System.out.println("Failure !! Breadcrumb is NOT proper." + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Breadcrumb is NOT proper." + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							"Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
		
			}
			
			//Header Message
			expectedtext = "almost done with registration";
			actualtext = SeleniumFunc.GetElementText("css", ".alert-heading");
		
			if(actualtext.contains(expectedtext))
			{
				
				System.out.println("Success !! User is redirected to second step of registration.");
				Reporter.log("Success !! User is redirected to second step of registration.");
			
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
			
			
			//Edit Button
			if(SeleniumFunc.IsElementPresent("css", ".btn.edit"))
			{
				
				System.out.println("Success !! Edit Buton is present.");
				Reporter.log("Success !! Edit Buton is present.");
			
			}
			else
			{
			
				System.out.println("Failure !! Edit Buton is NOT present.");
				Reporter.log("Failure !! Edit Buton is NOT present.");
				
				AssertFailedCount++;
			
			}
			
			// Add Student Link
			if(SeleniumFunc.IsElementPresent("css", "#add_student"))
			{
				
				System.out.println("Success !!  Add Student Link is present.");
				Reporter.log("Success !!  Add Student Link is present.");
			
			}
			else
			{
			
				System.out.println("Failure !!  Add Student Link is NOT present.");
				Reporter.log("Failure !!  Add Student Link is NOT present.");
				
				AssertFailedCount++;
			
			}
			
			//Policy Alert
			if(SeleniumFunc.IsElementPresent("css", ".alert.alert-info"))
			{
				
				System.out.println("Success !! Policy Alert is present.");
				Reporter.log("Success !! Policy Alert is present.");
			
			}
			else
			{
			
				System.out.println("Failure !! Policy Alert is NOT present.");
				Reporter.log("Failure !!  Policy Alert is NOT present.");
				
				AssertFailedCount++;
			
			}
			
			
			//Complete Registration Button
			if(SeleniumFunc.IsElementPresent("css", "#complete_registration"))
			{
				
				System.out.println("Success !! Complete Registration Button is present.");
				Reporter.log("Success !! Complete Registration Button is present.");
			
			}
			else
			{
			
				System.out.println("Failure !! Complete Registration Button is NOT present.");
				Reporter.log("Failure !! Complete Registration Button is NOT present.");
				
				AssertFailedCount++;
			
			}
			
			
			//Cancel Registration Link
			if(SeleniumFunc.IsElementPresent("css", ".l-cancel-action a"))
			{
				
				System.out.println("Success !! Cancel Registration Link is present.");
				Reporter.log("Success !! Cancel Registration Link is present.");
			
			}
			else
			{
			
				System.out.println("Failure !! Cancel Registration Link is NOT present.");
				Reporter.log("Failure !! Cancel Registration Link is NOT present.");
				
				AssertFailedCount++;
			
			}
			
	
				//Login as admin and delete event
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				Thread.sleep(4000);
				//SeleniumFunc.AcceptAlertAndGetText();
				//Thread.sleep(5000);

				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);

				login.ClickOnLogInButton();			
				Thread.sleep(5000);

				//Going to Event Roster page and removing student and deleting  event 
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				System.out.println("Event: "+eventid+" Deleted Successfully!!");
				Thread.sleep(5000);
		
			
		
		/*
		 * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 */
	 	if(AssertFailedCount>0)	
		{
	 		//Login as admin and delete event
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(4000);
			SeleniumFunc.AcceptAlertAndGetText();
			Thread.sleep(5000);

			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);

			login.ClickOnLogInButton();			
			Thread.sleep(5000);

			//Going to Event Roster page and removing student and deleting  event 
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
	 * Verify Elements on step 2 for an event for which payment is not required
	*/ 
	@Test
	private void UI_Step2WithPayment() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify Elements on step 2 for an event for which payment is not required"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify Elements on step 2 for an event for which payment is not required"  + "\n" +
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
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		PaymentSettingsPage payment = new PaymentSettingsPage(driver);
		PaymentSettingsPage paymentsettings = new PaymentSettingsPage(driver);

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
			//instructorcreateevent.SelectDate("8");
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
									+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Name" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
				
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

			
		System.out.println("Step 6 : Select agency from agency listing");
		Reporter.log("Step 6 : Select agency from agency listing"); 
					
			((JavascriptExecutor)driver).executeScript("scroll(477,1126)");				
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

			
		System.out.println("Step 7 : Go to Payment settings and set paymeny ON");
		Reporter.log("Step 7 : Go to Payment settings and set paymeny ON");   	
			
				agencyhome.ClickToSelectProgram();
				Thread.sleep(5000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/payment/13");
				//agencyhome.ClickOnPaymentSettingsLink();
				Thread.sleep(5000);

				paymentsettings.SelectPaymentMode("On (fixed price)");
				paymentsettings.SelectProductCode("EME57265 - Demo Hunter Education Field Day");
				Thread.sleep(500); 
				payment.ClickOnSaveChanges();
				Thread.sleep(5000);

		System.out.println("Step 8 : Search and Activate event");
		Reporter.log("Step 8 : Search and Activate event"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
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

			
		System.out.println("Step 9 : Go to Register-Ed and verify UI of first page of registration");
		Reporter.log("Step 9 : Go to Register-Ed and verify functionality of Register Now button"); 
		
			
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
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickToSelectGender();
			((JavascriptExecutor)driver).executeScript("scroll(584,1165)");
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);
			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(5000);
			
		System.out.println("Step 10 : Verify UI for Step 2");
		Reporter.log("Step 10 : Verify UI for Step 2"); 
			
			
			//Verify breadcrumb 
		
		
			expectedtext = "Add Students";
			actualtext = SeleniumFunc.GetElementText("css", "#content div ol").trim();
	
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Breadcrumb is displayed with proper text. i.e.  " + actualtext);
				Reporter.log("Success !! Breadcrumb is displayed with proper text. i.e.  " + actualtext);
		
			}
			else
			{
		
				System.out.println("Failure !! Breadcrumb is NOT proper." + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Breadcrumb is NOT proper." + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							"Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
		
			}
			
			Thread.sleep(2000);
			//Fee Section
			if(SeleniumFunc.IsElementPresent("css", "#fee_wrap div"))
			{
				
				System.out.println("Success !! Fee Section is present.");
				Reporter.log("Success !! Fee Section is present.");
			
			}
			else
			{
			
				System.out.println("Failure !! Fee Section is NOT present.");
				Reporter.log("Failure !! Fee Section is NOT present.");
				
				AssertFailedCount++;
			
			}
			
			// Payment Form
			if(SeleniumFunc.IsElementPresent("css", "#form_payment"))
			{
				
				System.out.println("Success !! Payment Form is present.");
				Reporter.log("Success !! Payment Form is present.");
			
			}
			else
			{
			
				System.out.println("Failure !! Payment Form is NOT present.");
				Reporter.log("Failure !! Payment Form is NOT present.");
				
				AssertFailedCount++;
			
			}
			Thread.sleep(1000);
			//Verify Button 
			
			expectedtext = "Pay & complete registration";
			actualtext = SeleniumFunc.GetAttributeValue("css", "#complete_registration", "value");
	
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
			
				System.out.println("Success !! Button is displayed with proper text. i.e.  " + expectedtext);
				Reporter.log("Success !! Button is displayed with proper text. i.e.  " + expectedtext);
		
			}
			else
			{
		
				System.out.println("Failure !! Button is NOT proper." + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Button is NOT proper." + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							"Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
		
			}
			
			
				//Login as admin and delete event
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				Thread.sleep(4000);
				//SeleniumFunc.AcceptAlertAndGetText();
				Thread.sleep(5000);

				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);

				login.ClickOnLogInButton();			
				Thread.sleep(5000);
				
				//Make Payment mode- off
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/payment/13");
				//agencyhome.ClickOnPaymentSettingsLink();
				Thread.sleep(5000);

				paymentsettings.SelectPaymentMode("Off");
				payment.ClickOnSaveChanges();
				Thread.sleep(5000);
				

				//Going to Event Roster page and removing student and deleting  event 
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				System.out.println("Event: "+eventid+" Deleted Successfully!!");
				Thread.sleep(5000);
			
		
		/*
		 * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 */
	 	if(AssertFailedCount>0)	
		{
	 		//Login as admin and delete event
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(4000);
			SeleniumFunc.AcceptAlertAndGetText();
			Thread.sleep(5000);

			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);

			login.ClickOnLogInButton();			
			Thread.sleep(5000);

			//Going to Event Roster page and removing student and deleting  event 
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


}
