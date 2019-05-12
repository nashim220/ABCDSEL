package products.EM;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
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
import pageFactory.EM.RegisterEdHomePage;
import pageFactory.EM.RegisterEdRegisterStudentPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class EmailTest 
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
	 * Verify email is generated when an event is activated by Admin to instructor

	*/ 
	@Test
	private void EventActivatedEmail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify email is generated when an event is activated by Admin to instructor"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify email is generated when an event is activated by Admin to instructor"  + "\n" +
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
	
		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			
			//Navigate to 'My Event Schedule' page and delete all Events
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
		//	instructorschedule.DeleteAllEvents();
			 
			
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
					
				
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
					
			//Verifying Event Time
			String expectedtext = "6:30 pm";
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
			expectedtext = "Demo Hunter Education Field Day";
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
				
			login.ClickOnLogoutButton();
		
			
		System.out.println("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();

			
		System.out.println("Step 6 : Search and Activate event");
		Reporter.log("Step 6 : Search and Activate event"); 
			
		
			searchevent.ClickOnSearchEventLink();
			searchevent.SelectSearchTypeByVisibleText("Location");
			searchevent.EnterSearchData("Demo Agency Location");
			searchevent.ClickOnSearchButton();
			searchevent.ClickOnSelectEventCheckBox();
			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			login.ClickOnLogoutButton();
			
			
		System.out.println("Step 7 : Navigating to Email Box and verifying whether email is received with correct details or not");
		Reporter.log("Step 7 : Navigating to Email Box and verifying whether email is received with correct details or not"); 
						
			Thread.sleep(5000);
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=emautomation#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
						
					
			actualtext= SeleniumFunc.GetElementText("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");						
			expectedtext="Event Activated"; 
				
			if(actualtext.contains(expectedtext))
			{
				
				System.out.println("Success!! Email is received with correct Title. i.e. " +  expectedtext);
				Reporter.log("Success!! Email is received with correct Title. i.e. " +  expectedtext);
			
			}
			else
			{
				
				System.out.println("Failure !!  Email is received with incorrect Title" + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !!Email is received with incorrect Title" + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
					
				AssertFailedCount++;
			
			}					
		

			//Delete Event		
			try {
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);

				login.ClickOnLogInButton();			

				//Going to Event Roster page and removing student and deleting  event 
				//so new event can be added with same test data
				instructorhome.VisitHomePage.click();
				instructorhome.Action_MyEventSchedule.click();
				//instructorhome.GoToNextMonth();
				instructorschedule.FirstEventName.click();
				instructoreventroster.EventDelete.click();
				instructoreventroster.Confirm_EventDelete.click();
				
				Thread.sleep(5000);
			} catch (Exception e) {
				//  Auto-generated catch block
				
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
	 * Verify email "Event Activation Required" [sent to administrator]
	*/
	@Test
	private void EventActivationRequiredEmail() throws Exception
	{
			System.out.println("====" + "\n" +
						"Test 2 : Verify email Event Activation Required [sent to administrator]"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 2 : Verify email Event Activation Required [sent to administrator]"  + "\n" +
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
						
					
			System.out.println("Step 4 : Verifying whether Event is added successfully or not");
			Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
						
				//Verifying Event Time
				String expectedtext = "6:30 pm";
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
				expectedtext = "Demo Hunter Education Field Day";
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
				String URL = SeleniumFunc.ToGetCurrentPageUrl();
				
				
			System.out.println("Step 5 : Navigating to Email Box and verifying whether email is received with correct details or not");
			Reporter.log("Step 5 : Navigating to Email Box and verifying whether email is received with correct details or not"); 
							
				Thread.sleep(5000);
				String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=emautomation#/#public_maildirdiv";
				SeleniumFunc.ToGoToUrl(EmailBoxUrl);
				Thread.sleep(Constants.ThreadWaitInmiliseconds);
							
						
				actualtext= SeleniumFunc.GetElementText("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");						
				expectedtext="Event Activation Required"; 
					
				if(actualtext.contains(expectedtext))
				{
					
					System.out.println("Success!! Email is received with correct Title. i.e. " +  expectedtext);
					Reporter.log("Success!! Email is received with correct Title. i.e. " +  expectedtext);
				
				}
				else
				{
					
					System.out.println("Failure !!  Email is received with incorrect Title" + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !!Email is received with incorrect Title" + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
						
					AssertFailedCount++;
				
				}	
			
		
				//Delete Event		
				try {
					SeleniumFunc.ToGoToUrl(URL);
				
					//Going to Event Roster page and removing student and deleting  event 
					//so new event can be added with same test data
					
					instructoreventroster.EventDelete.click();
					instructoreventroster.Confirm_EventDelete.click();
					
					Thread.sleep(5000);
				} catch (Exception e) {
					//  Auto-generated catch block
					
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
	 * Verify Cancel Registration Email Functionality
	 */ 
	@Test
	private void CancelRegistrationEmail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify Cancel Registration Email Functionality"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify Cancel Registration Email Functionality"  + "\n" +
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
		RegisterEdHomePage homepage = new RegisterEdHomePage(driver);
	
		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
		
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			
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
					
				
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
					
			
			//Verifying Event Time
			String expectedtext = "6:30 pm";
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
			expectedtext = "Demo Hunter Education Field Day";
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
			String EventID = instructoreventroster.EventId.getText().trim();
			login.ClickOnLogoutButton();
		
			
		System.out.println("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
	
		  	
		System.out.println("Step 6 : Search and Activate event");
		Reporter.log("Step 6 : Search and Activate event"); 			
		  	
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
			searchevent.SelectSearchTypeByVisibleText("Location");
			searchevent.EnterSearchData("Demo Agency Location");
			searchevent.ClickOnSearchButton();
			searchevent.ClickOnSelectEventCheckBox();
			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			login.ClickOnLogoutButton();
			
		
		System.out.println("Step 7 : Go to register-ed and make registration");
		Reporter.log("Step 7 : Go to register-ed and make registration"); 
					
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);
			
			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			String browserName = cap.getBrowserName().toLowerCase();


			if(browserName.equals("internet explorer"))
			try{
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
				Thread.sleep(5000);
			}catch (Exception e) {
			}	
		
			//Register First Student
			eventview.ClickOnRegisterNowButton();
			
			String FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			String UserName="emstudent"+ JH.GenerateRandomNumber();
			String EmailAddress = UserName + "@mailinator.com";
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");			
			registerstudent.ClickToSelectGender();
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickOnNextButton();
			
			registerstudent.ClickOnReviewAndConfirmButton();
			registerstudent.ClickOnAgreePolicyCheckBox();
			registerstudent.ClickOnCompleteRegistrationButton();
			
					
			//Go to update registration
			homepage.ClickOnUpdateRegiButton();
			
				
			//Enter details to update registration
			registerstudent.EnterEmailAddress(EmailAddress);
			registerstudent.ClickOnGoButton();				
			
			
		System.out.println("Step 8 : Navigating to Email Box and verifying whether email is received with correct details or not");
		Reporter.log("Step 8 : Navigating to Email Box and verifying whether email is received with correct details or not"); 
				
			Thread.sleep(5000);
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=emautomation#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
				
			//Opening email body and fetching verification URL
					
			SeleniumFunc.ClickOnElement("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");
			Thread.sleep(4000);
			
			driver.switchTo().frame("publicshowmaildivcontent");
			String UpdateRegi = SeleniumFunc.GetElementText("css", "tbody>tr>td>a");
	
			SeleniumFunc.ToGoToUrl(UpdateRegi);
				
		
		System.out.println("Step 9 : Add valid values for identity verification.");
		Reporter.log("Step 9 : Add valid values for identity verification."); 
									
			
			registerstudent.EnterZipCode("25825");
			registerstudent.EnterPhoneNo("9898989898");
			registerstudent.ClickOnVerifyButton();			
			
			
			actualtext= SeleniumFunc.GetElementText("css", ".login-global-hd.login-global-hd-user>a").trim();						
			expectedtext=EmailAddress; 
					
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
				
				System.out.println("Success!! User is logged in. i.e. " + expectedtext);
				Reporter.log("Success!! User is logged in. i.e. " + expectedtext); 
				
			}
			else
			{
				
				System.out.println("Failure !! Error in identity verification." + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Error in identity verification." + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
					
				AssertFailedCount++;
			
			}			
			
			//Click on Cancel Registration
			eventview.ClickOnCancelRegistrationButton();
			
			
			//Verify UI cancellation Pop-up
			actualtext= SeleniumFunc.GetElementText("css", "#cancel_modal div:nth-of-type(2)").trim();						
			expectedtext="Do you want to remove all students and cancel registration for Demo Hunter Education Field Day, starting on"; 
					
			if(actualtext.contains(expectedtext))
			{
				
				System.out.println("Success!! Proper text is displayed on cancellation Pop-up. i.e. " + expectedtext);
				Reporter.log("Success!! Proper text is displayed on cancellation Pop-up. i.e. " + expectedtext); 
				
			}
			else
			{
				
				System.out.println("Failure !! Incorrect text is displayed on cancellation Pop-up." + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect text is displayed on cancellation Pop-up." + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
					
				AssertFailedCount++;
			
			}			
	
			
			//Cancel Registration
			//eventview.ClickOnCancelRegistrationButton();
			SeleniumFunc.ClickOnElement("css", "#cancel_confirm");
			
			actualtext= SeleniumFunc.GetElementText("css", ".alert.alert-error").trim();						
			expectedtext="Registration canceled. You are no longer scheduled to attend this event. A confirmation email has been sent."; 
					
			if(actualtext.contains(expectedtext))
			{
				
				System.out.println("Success!! Proper success message is displayed on cancellation. i.e. " + expectedtext);
				Reporter.log("Success!! Proper success message is displayed on cancellation. i.e. " + expectedtext); 
				
			}
			else
			{
				
				System.out.println("Failure !! Incorrect message is displayed on cancellation." + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect message is displayed on cancellation." + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
					
				AssertFailedCount++;
			
			}			
			
			
			Thread.sleep(5000);
			EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=emautomation#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
					
				
			actualtext= SeleniumFunc.GetElementText("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");	
			expectedtext="Registration Canceled"; 
				
			if(actualtext.contains(expectedtext))
			{
				
				System.out.println("Success!! Email is received with correct Title. i.e. " +  expectedtext);
				Reporter.log("Success!! Email is received with correct Title. i.e. " +  expectedtext);
			
			}
			else
			{
				
				System.out.println("Failure !!  Email is received with incorrect Title" + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !!Email is received with incorrect Title" + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
					
				AssertFailedCount++;
			
			}	
			
			
			try {
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);
				login.ClickOnLogInButton();	
				
				
				//Deleting event so new event can be added with same test data 
				instructorhome.VisitHomePage.click();
				instructorhome.Action_MyEventSchedule.click();
				//instructorhome.GoToNextMonth();
				instructorschedule.FirstEventName.click();
				instructoreventroster.ClickOnSelectEventCheckBox();
				instructoreventroster.SelectEventAction();
				instructoreventroster.ClickOnGoButton();
				instructoreventroster.ConfirmStudentRemove.click();
				instructoreventroster.EventDelete.click();
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
	 * Verify cancel event email
	 */ 
	@Test
	private void CancelEventEmail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify cancel event email"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify cancel event email"  + "\n" +
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
					
				
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
					
			//Verifying Event Time
			String expectedtext = "6:30 pm";
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
			expectedtext = "Demo Hunter Education Field Day";
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
			String EventID = instructoreventroster.EventId.getText().trim();
			login.ClickOnLogoutButton();
		
			
		System.out.println("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
	
			
		System.out.println("Step 6 : Search and Activate event");
		Reporter.log("Step 6 : Search and Activate event"); 
			
		
			searchevent.ClickOnSearchEventLink();
			searchevent.SelectSearchTypeByVisibleText("Location");
			searchevent.EnterSearchData("Demo Agency Location");
			searchevent.ClickOnSearchButton();
			searchevent.ClickOnSelectEventCheckBox();
			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			login.ClickOnLogoutButton();
			
			
		System.out.println("Step 7 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 7 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
				
			
		System.out.println("Step 8 : Verify add student option is present on Event Roster page, and user can add the student");
		Reporter.log("Step 8 : Verify add student option is present on Event Roster page, and user can add the student");
		

			instructorhome.VisitHomePage.click();
			instructorhome.Action_MyEventSchedule.click();
			//instructorhome.GoToNextMonth();
			instructorschedule.FirstEventName.click();
			
		
			instructoreventroster.SelectAction("Add Student");
			Thread.sleep(5000);
			String username="addstud"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
			addstud.SelectEyeColor();
			addstud.SelectHairColor();
			addstud.EnterSSNNO("125896336");
			addstud.ClickOnRegisterButton();
			
			
			//Go to EM Admin and cancel event
			login.ClickOnLogoutButton();
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/admin/" + EventID);
			SeleniumFunc.ClickOnElement("css", ".cancel");
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("css", ".btn.btn-danger");
			SeleniumFunc.ClickOnElement("css", ".button span");
			

			Thread.sleep(5000);

			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=emautomation#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
						
					
			actualtext= SeleniumFunc.GetElementText("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");					
			expectedtext="Your Event Has Been Cancelled"; 
				
			if(actualtext.contains(expectedtext))
			{
				
				System.out.println("Success!! Email is received with correct Title. i.e. " +  expectedtext);
				Reporter.log("Success!! Email is received with correct Title. i.e. " +  expectedtext);
			
			}
			else
			{
				
				System.out.println("Failure !!  Email is received with incorrect Title" + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !!Email is received with incorrect Title" + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
					
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
