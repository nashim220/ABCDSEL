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
import pageFactory.EM.PaymentSettingsPage;
import pageFactory.EM.RegisterEdEventView;
import pageFactory.EM.RegisterEdRegisterStudentPage;
import utility.Browser; 
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;


public class MaximumNumberOfRegistrationsTest 
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
	 * Verify Active, concurrent student registrations can be set to (based on email) to a maximum amount at EM
	*/ 
	@Test
	private void SetMaxNoOfRegistrationEM() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify Active, concurrent student registrations can be set to (based on email) to a maximum amount at EM"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify Active, concurrent student registrations can be set to (based on email) to a maximum amount at EM"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
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

						
		System.out.println("Step 4 : Set Maximum Number of Registrations to 2");
		Reporter.log("Step 4 : Set Maximum Number of Registrations to 2");
							
							
			eventsettings.AllowRegistration("2");
			eventsettings.ClickOnSaveChanges();  	
			Thread.sleep(5000);

								
		System.out.println("Step 5 : Verify Maximum Number of Registrations set to 2");
		Reporter.log("Step 5 : Verify Maximum Number of Registrations set to 2");
								
			String Expected = "2";
			String Actual = SeleniumFunc.GetSelectedValueFromDropdownList("name", "ConfigValue[max_allowed_registrations]");
				
			if(Actual.equals(Expected))
			{
				
				System.out.println("Success !! Maximum Number of Registrations set to 2.");
				Reporter.log("Success !! Maximum Number of Registrations set to 2.");
				
			}
			else
			{
				System.out.println("Failure !! Maximum Number of Registrations NOT saved." + "\n" + "Expected : "  
									+ "\n" + Expected );
				Reporter.log("Failure !! Maximum Number of Registrations NOT saved." + "\n" + "Expected : "  
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
	

	/* Test 2: 
	 * Verify validation message is displayed on Register-ed if the limit exceeds for max registration with same email id (NOT GROUP REG.)
	*/ 	
	@Test
	private void VerifyLimitExceedsForMaxRegistration() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify validation message is displayed on Register-ed if the limit exceeds for max registration with same email id (NOT GROUP REG.)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify validation message is displayed on Register-ed if the limit exceeds for max registration with same email id (NOT GROUP REG.)"  + "\n" +
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
		RegisterEdRegisterStudentPage registerstudent = new RegisterEdRegisterStudentPage(driver);

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
			Thread.sleep(5000);

			
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
			((JavascriptExecutor)driver).executeScript("scroll(990,774)");

			instructorcreateevent.CreateEvent.click();
			Thread.sleep(5000);
			String URL = driver.getCurrentUrl();
			System.out.println("URL :"+URL);
				
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
			login.ClickOnLogoutButton();
		
			
		System.out.println("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			//Go to EM and set Payment Off
			PaymentSettingsPage payment = new PaymentSettingsPage(driver);	
			((JavascriptExecutor)driver).executeScript("scroll(990,774)");
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

			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency/?");
			Thread.sleep(5000);

		System.out.println("Step 6 : Select agency from agency listing");
		Reporter.log("Step 6 : Select agency from agency listing"); 
					
		((JavascriptExecutor)driver).executeScript("scroll(990,774)");
				
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

			
		System.out.println("Step 7 : Select a program and go to Event Settings");
		Reporter.log("Step 7 : Select a program and go to Event Settings"); 
				
			
		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);
			((JavascriptExecutor)driver).executeScript("scroll(790,574)");
		  	agencyhome.ClickOnEventSettingsLink();
			Thread.sleep(5000);

						
		System.out.println("Step 8 : Set Maximum Number of Registrations to 1");
		Reporter.log("Step 8 : Set Maximum Number of Registrations to 1");
							
							
			eventsettings.AllowRegistration("1");
			eventsettings.ClickOnSaveChanges();  	
			
			Thread.sleep(5000);

		System.out.println("Step 9 : Search and Activate event");
		Reporter.log("Step 9 : Search and Activate event"); 			
		  	
			
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
			searchevent.ClickOnSearchEventLink();
			Thread.sleep(5000);
			searchevent.SelectSearchTypeByVisibleText("ID");
			Thread.sleep(2000);
			searchevent.EnterSearchData(EventID);
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("id", "search-button");
			Thread.sleep(5000);

			searchevent.ClickOnSelectEventCheckBox();
			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			Thread.sleep(5000);

			//login.ClickOnLogoutButton();
			//Thread.sleep(5000);

		
		System.out.println("Step 10 : Go to Register-Ed and Maximum Number of Registrations");
		Reporter.log("Step 10 : Go to Register-Ed and Maximum Number of Registrations"); 
			
				
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

			//Register First Student
			String FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			String UserName="emstudent"+ JH.GenerateRandomNumber();
			String EmailAddress = UserName + "@mailinator.com";
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickToSelectGender();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerstudent.Next);								
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);

			registerstudent.ClickOnReviewAndConfirmButton();	
			Thread.sleep(5000);
			//registerstudent.ClickOnIUnderstand();
			//Thread.sleep(2000);
			registerstudent.ClickOnAgreePolicyCheckBox();
			registerstudent.ClickOnCompleteRegistrationButton();
			Thread.sleep(5000);

			
/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/
			//Register Second Student with Same email address
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);
			Thread.sleep(5000);


			eventview.ClickOnRegisterNowButton();
			Thread.sleep(5000);

			FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
		
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickToSelectGender();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerstudent.Next);								
			registerstudent.ClickOnNextButton();
			
			Thread.sleep(5000);

		System.out.println("Step 11 : Verify validation for Maximum Number of Registrations");
		Reporter.log("Step 11 : Verify validation for Maximum Number of Registrations"); 
					
			
			expectedtext= "Oops! Looks like "+EmailAddress+" is currently registered for this course. " +
						  "To add students to your existing registration, log into your account. " +
						  "If you’re trying to register for a different event, first log into your account " +
						  "and cancel your existing registration. Once your registration is canceled, " +
						  "you may register for any event with seats available.";
			
			String expectedtextStarts= "Oops! Looks like "+EmailAddress+" is currently registered for this course. " +
					  "To add students to your existing registration, log into your account. " +
					  "If you";
			String expectedtextEnds= "re trying to register for a different event, first log into your account " +
					  "and cancel your existing registration. Once your registration is canceled, " +
					  "you may register for any event with seats available.";
			
			actualtext = SeleniumFunc.GetElementText("css", "#content div:nth-of-type(2)");
			
			//if(actualtext.contains(expectedtext))
			if(actualtext.startsWith(expectedtextStarts)&&actualtext.endsWith(expectedtextEnds))
			{
				
				System.out.println("Success !! Maximum Number of Registrations functionality is working properly");
				Reporter.log("Success !! Maximum Number of Registrations functionality is working properly");
			
			}
			else
			{
			
				System.out.println("Failure !! Maximum Number of Registrations functionality is NOT working properly" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Maximum Number of Registrations functionality is NOT working properly" + "\n" + "Expected : "  
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
				//Login as instructor to delete event
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


	/* Test 3: 
	 * Verify Max number registration config is enforced only on Register-ed
	 */ 
	@Test
	private void VerifyNoValidationAtEM() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify Max number registration config is enforced only on Register-ed"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify Max number registration config is enforced only on Register-ed"  + "\n" +
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
			instructorcreateevent.EnterWaitListCapacity("0");
			
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
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
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
			
		
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
			/*
			instructorhome.VisitHomePage.click();		
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();	
			Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			instructorschedule.FirstEventName.click();
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
			Thread.sleep(2000);

			//Register First Student
			String username="emstudent"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
			addstud.SelectEyeColor();
			addstud.SelectHairColor();
			addstud.EnterSSNNO("125896336");
			addstud.ClickOnRegisterButton();
			Thread.sleep(5000);

			
			//Register Second Student
			instructoreventroster.SelectAction("Add Student");
			Thread.sleep(5000);

			addstud.FillRegisterStudent("Automation", "Testing", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
			addstud.SelectEyeColor();
			addstud.SelectHairColor();
			addstud.EnterSSNNO("125896336");
			addstud.ClickOnRegisterButton();
			Thread.sleep(5000);

			int NoOfStudent = SeleniumFunc.GetElementCount("css", ".checkbox.checkbox-sidelong a");
			
			if(NoOfStudent == 2)
			{
				
				System.out.println("Success !! EM can register multiple user with same email.");
				Reporter.log("Success !! EM can register multiple user with same email.");
			
			}
			else
			{
			
				System.out.println("Failure !! Not able to register multiple user with same email.");
				Reporter.log("Failure !! Not able to register multiple user with same email.");
				
				AssertFailedCount++;
			
			}
			
			/*
			try {
				//Remove student and Delete event
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

					((JavascriptExecutor)driver).executeScript("scroll(990,774)");
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
	 
	/* Test 4: 
	 * Verify this option of Max registrations in not applicable for Group registration functionality
	 */
	@Test
	private void GroupRegistrationWithSameEmail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify this option of Max registrations in not applicable for Group registration functionality"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify this option of Max registrations in not applicable for Group registration functionality"  + "\n" +
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
		RegisterEdRegisterStudentPage registerstudent = new RegisterEdRegisterStudentPage(driver);
		
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
			((JavascriptExecutor)driver).executeScript("scroll(990,774)");

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

			/*
			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}*/
			
		System.out.println("Step 6 : Search and Activate event");
		Reporter.log("Step 6 : Search and Activate event"); 			
		  	
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
		
			searchevent.ClickOnSearchEventLink();
			Thread.sleep(5000);
			searchevent.SelectSearchTypeByVisibleText("ID");
			Thread.sleep(2000);
			searchevent.EnterSearchData(EventID);
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("id", "search-button");
			Thread.sleep(5000);

			searchevent.ClickOnSelectEventCheckBox();
			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			Thread.sleep(5000);

			//login.ClickOnLogoutButton();
			//Thread.sleep(5000);


		System.out.println("Step 8 : Go to register-ed and make group registration");
		Reporter.log("Step 8 : Go to register-ed and make group registration"); 
		

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

			//Verifying Status of Event 
			expectedtext = "Registration Open";
			actualtext = eventview.RegistrationStatus.getText().trim();
	
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
					
			
			//Register First Student
			Thread.sleep(5000);

			eventview.ClickOnRegisterNowButton();
			Thread.sleep(5000);

			String FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			String UserName="emstudent"+ JH.GenerateRandomNumber();
			String EmailAddress = UserName + "@mailinator.com";
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickToSelectGender();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerstudent.Next);								
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);

			//Register Second Student
			registerstudent.ClickOnAddAnotherStudentButton();
			Thread.sleep(5000);

			FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896356");
			registerstudent.ClickToSelectGender();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerstudent.Next);								
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);

			
			//Register Third Student
			registerstudent.ClickOnAddAnotherStudentButton();
			Thread.sleep(8000);

			FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896332");
			registerstudent.ClickToSelectGender();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerstudent.Next);								
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);

			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(5000);
			//registerstudent.ClickOnIUnderstand();
			//Thread.sleep(2000);
			registerstudent.ClickOnAgreePolicyCheckBox();
			registerstudent.ClickOnCompleteRegistrationButton();
			Thread.sleep(5000);

			
/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			//Verify Success Message
			
			expectedtext = "Well done! You successfully completed registering for this event.";
			actualtext = registerstudent.SuccessMessage.getText().trim();
	
			if(actualtext.contains(expectedtext))
			{
		
				System.out.println("Success !! Group Registration is working properly with same email." +expectedtext);
				Reporter.log("Success !! Group Registration is working properly with same email." +expectedtext);
		
			}
			else
			{
		
				System.out.println("Failure !! Error while group registration" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Error while group registration" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						"Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
	
			}	
			
			/*
			try {
				//Login as Instructor					
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
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
}
