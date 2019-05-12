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


public class UpdateCancelRegistrationTest 
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
	 * Verify UI of Update/Cancel Registration page
	 */
	@Test
	private void UI_UpdateCancelRegistration() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify UI of Update/Cancel Registration page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify UI of Update/Cancel Registration page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegisterEdHomePage homepage = new RegisterEdHomePage(driver);
		
		
		System.out.println("Step 1 : Go to Register-Ed home page at : " + Constants.APPLICATIONURL_RegisterEd);
		Reporter.log("Step 1 : Go to Register-Ed home page at : " + Constants.APPLICATIONURL_RegisterEd); 
		

			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd);
			Thread.sleep(5000);

			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			String browserName = cap.getBrowserName().toLowerCase();


			if(browserName.equals("internet explorer"))
			try{
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
				Thread.sleep(5000);
			}catch (Exception e) {
			}	

			
		System.out.println("Step 2 : Click on Update / Cancel Registration and Verify UI");
		Reporter.log("Step 2 : Click on Update / Cancel Registration and Verify UI"); 
		
			
			homepage.ClickOnUpdateRegiButton();
			Thread.sleep(5000);

			//Verify UI Elements
			
			//Verify Email
			
			if(SeleniumFunc.IsElementPresent("css", "#email"))
			{
		
				System.out.println("Success !! Email Textbox is present");
				Reporter.log("Success !! Email Textbox is present"); 
			}
			else
			{
			
				System.out.println("Failure !! Email Textbox is NOT present ");
				Reporter.log("Failure !! Email Textbox is NOT present "); 
			
				AssertFailedCount++;
			}
		
			//Verify Go Button
		
			if(SeleniumFunc.IsElementPresent("css", "#emailSubmit"))
			{
		
			System.out.println("Success !! Go Button is present");
			Reporter.log("Success !! Go Button is present"); 
			
			}
			else
			{
			
				System.out.println("Failure !! Go Button is NOT present ");
				Reporter.log("Failure !! Go Button is NOT present "); 
			
				AssertFailedCount++;
			}
		
			//Verify Register-Ed Link
			String expectedtext = "Register Ed";
			String actualtext = SeleniumFunc.GetElementText("css", ".back-nav.back-nav-global>a>span");
			
			if(actualtext.equals(expectedtext))
			{
			
				System.out.println("Success !! Register Ed link is present at header.");
				Reporter.log("Success !! Register Ed link is present at header.");
			
			}
			else
			{
			
				System.out.println("Failure !! Register Ed link is NOT present at header.");
				Reporter.log("Failure !! Register Ed link is NOT present at header.");
			
				AssertFailedCount++;
			
			}
			
			//Verify Header
			expectedtext = "Update or Cancel Registration";
			actualtext = SeleniumFunc.GetElementText("css", "#primary_content div h1");
			
			if(actualtext.equals(expectedtext))
			{
			
				System.out.println("Success !! Proper header is displayed. i.e." + expectedtext);
				Reporter.log("Success !! Proper header is displayed. i.e." + expectedtext);
			
			}
			else
			{
			
				System.out.println("Failure !! Proper header is displayed. i.e. " + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Proper header is displayed. i.e." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			
			//Verify Footer
			
			if(SeleniumFunc.IsElementPresent("css", ".row.kalkomey-info"))
			{
		
			System.out.println("Success !! Footer is present");
			Reporter.log("Success !! Footer is present"); 
			
			}
			else
			{
			
				System.out.println("Failure !! Footer is NOT present ");
				Reporter.log("Failure !! Footer is NOT present "); 
			
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
	 * Verify different validations are done on email id
	 */ 
	@Test
	private void VerifyValidationForEmail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify different validations are done on email id"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify different validations are done on email id"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegisterEdHomePage homepage = new RegisterEdHomePage(driver);
		RegisterEdRegisterStudentPage registerstudent = new RegisterEdRegisterStudentPage(driver);
		
		
		System.out.println("Step 1 : Go to Register-Ed home page at : " + Constants.APPLICATIONURL_RegisterEd);
		Reporter.log("Step 1 : Go to Register-Ed home page at : " + Constants.APPLICATIONURL_RegisterEd); 
		

			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd);
			Thread.sleep(5000);

			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			String browserName = cap.getBrowserName().toLowerCase();


			if(browserName.equals("internet explorer"))
			try{
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
				Thread.sleep(5000);
			}catch (Exception e) {
			}	

			
		System.out.println("Step 2 : Click on Update / Cancel Registration");
		Reporter.log("Step 2 : Click on Update / Cancel Registration"); 
		
			
			homepage.ClickOnUpdateRegiButton();
			Thread.sleep(5000);

		System.out.println("Step 3 : Verify validation for invalid email.");
		Reporter.log("Step 3 : Verify validation for invalid email."); 	
				
			registerstudent.EnterEmailAddress("rohit");
			registerstudent.ClickOnGoButton();
			Thread.sleep(5000);


			//Verify Validation
			String expectedtext = "Please enter an email address.";
			String actualtext = SeleniumFunc.GetElementText("css", "#email_errorMsg");
				
			if(actualtext.equals(expectedtext))
			{
				
				System.out.println("Success !! Proper validation message is displayed. i.e." + expectedtext);
				Reporter.log("Success !! Proper validation message is displayed. i.e." + expectedtext);
				
			}
			else
			{
				
				System.out.println("Failure !! Validation message is NOT displayed. i.e. " + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Validation message is NOT displayed. i.e." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
					
				AssertFailedCount++;
				
			}
					
			
		System.out.println("Step 4 : Enter Non-registered email address and verify validation");
		Reporter.log("Step 4 : Enter Non-registered email address and verify validation"); 	
			

			registerstudent.EnterEmailAddress("Test@testingRE.com");
			registerstudent.ClickOnGoButton();
			Thread.sleep(5000);


			//Verify Validation
			expectedtext = "Email not found. You may have entered your email incorrectly." +
								  " Please retype your email and try again.";
			actualtext = SeleniumFunc.GetElementText("css", ".alert.alert-error");
			
			if(actualtext.equals(expectedtext))
			{
			
				System.out.println("Success !! Proper validation message is displayed. i.e." + expectedtext);
				Reporter.log("Success !! Proper validation message is displayed. i.e." + expectedtext);
			
			}
			else
			{
			
				System.out.println("Failure !! Validation message is NOT displayed. i.e. " + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Validation message is NOT displayed. i.e." + "\n" + "Expected : "  
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
	
	
	/* Test 3: 
	 * Verify confirmation email for Update /Cancel registration
	 */ 
	@Test
	private void VerifyConfirmationEmail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify confirmation email for Update /Cancel registration"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify confirmation email for Update /Cancel registration"  + "\n" +
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
			//instructorcreateevent.SelectDate("5");
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
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			Thread.sleep(5000);

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
		  	
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
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

				
		System.out.println("Step 8 : Go to register-ed and make registration");
		Reporter.log("Step 8 : Go to register-ed and make registration"); 
		
			
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

			//Register First Student
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
			
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);

			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(5000);

			registerstudent.ClickOnAgreePolicyCheckBox();
			registerstudent.ClickOnCompleteRegistrationButton();
			Thread.sleep(5000);

			
			//Verify Success Message
			
			expectedtext = "Well done! You successfully completed registering for this event.";
			actualtext = registerstudent.SuccessMessage.getText().trim();
	
			if(actualtext.contains(expectedtext))
			{
		
				System.out.println("Success !! Registration is working properly.");
				Reporter.log("Success !! Registration is working properly.");
		
			}
			else
			{
		
				System.out.println("Failure !! Error while registration" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Error while registration" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						"Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
	
			}	
			
					
			//Go to update registration
			homepage.ClickOnUpdateRegiButton();
			Thread.sleep(5000);

				
			//Enter details to update registration
			registerstudent.EnterEmailAddress(EmailAddress);
			registerstudent.ClickOnGoButton();
			Thread.sleep(5000);

			
		System.out.println("Step 9 : Verify Success Message");
		Reporter.log("Step 9 : Verify Success Message");
				
				
			actualtext = registerstudent.SuccessMessage.getText().trim();
			expectedtext = "Great! We found you. Check your email for information on how to change or cancel your registration.";
					
			if(actualtext.equals(expectedtext))
			{
						
				System.out.println("Success !! Success message is present with correct text. i.e." + "\n"+ expectedtext);
				Reporter.log("Success !! Success message is present with correct text. i.e." + "\n"+ expectedtext);
							
			}
			else
			{
								
				System.out.println("Failure !! Incorrect Message" + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect Message" + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
								
				AssertFailedCount++;
							
			}		

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
		System.out.println("Step 10 : Navigating to Email Box and verifying whether email is received with correct details or not");
		Reporter.log("Step 10 : Navigating to Email Box and verifying whether email is received with correct details or not"); 
				
			
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=emautomation#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
				
			
			actualtext= SeleniumFunc.GetElementText("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div").trim();						
			expectedtext="Your Account Information"; 
				
			if(actualtext.equalsIgnoreCase(expectedtext))
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
				
			
			SeleniumFunc.ClickOnElement("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");
			Thread.sleep(5000);
			
			driver.switchTo().frame("publicshowmaildivcontent");
			
			//Verifying link to manage a/c	
		
			if(SeleniumFunc.IsElementPresent("css", "html>body>a"))
			{
				
				System.out.println("Success!! Link to manage a/c is present");
				Reporter.log("Success!! Link to manage a/c is present");
				
			}
			else
			{
				
				System.out.println("Success!! Link to manage a/c is NOT present");
				Reporter.log("Success!! Link to manage a/c is NOT present");
				
			}
				
			
			actualtext= SeleniumFunc.GetElementText("css", "tbody>tr>td>a:nth-of-type(2)").trim();						
			expectedtext="http://register-ed.com"; 
				
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
				
				System.out.println("Success!! Link to application is present. i.e. " +  expectedtext);
				Reporter.log("Success!! Link to application is present. i.e. " +  expectedtext);
			
			}
			else
			{
				
				System.out.println("Failure !!  Link to application is absent.");
				Reporter.log("Failure !!  Link to application is absent.");
					
				AssertFailedCount++;
			
			}
			
			
			
			try {
				// GO to instructor-Ed		
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
	 * Verify UI of Identity Verification page
	 */ 	
	@Test
	private void UI_IdentityVerificationPage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify UI of Identity Verification page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify UI of Identity Verification page"  + "\n" +
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

		  	
		System.out.println("Step 6 : Search and Activate event");
		Reporter.log("Step 6 : Search and Activate event"); 			
		  	
			
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

			//login.ClickOnLogoutButton();
			//Thread.sleep(5000);

				
		System.out.println("Step 8 : Go to register-ed and make registration");
		Reporter.log("Step 8 : Go to register-ed and make registration"); 
			
			
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

			
			//Register First Student
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
			
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);

			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(5000);
			registerstudent.ClickOnIUnderstand();
			Thread.sleep(5000);
			registerstudent.ClickOnAgreePolicyCheckBox();
			registerstudent.ClickOnCompleteRegistrationButton();
			Thread.sleep(5000);

		
			//Verify Success Message
			
			expectedtext = "Well done! You successfully completed registering for this event.";
			actualtext = registerstudent.SuccessMessage.getText().trim();
	
			if(actualtext.contains(expectedtext))
			{
		
				System.out.println("Success !! Registration is working properly.");
				Reporter.log("Success !! Registration is working properly.");
		
			}
			else
			{
		
				System.out.println("Failure !! Error while registration" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Error while registration" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						"Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
	
			}	
			
					
			//Go to update registration
			homepage.ClickOnUpdateRegiButton();
			
			Thread.sleep(5000);

			//Enter details to update registration
			registerstudent.EnterEmailAddress(EmailAddress);
			registerstudent.ClickOnGoButton();
			Thread.sleep(5000);

		System.out.println("Step 9 : Verify Success Message");
		Reporter.log("Step 9 : Verify Success Message");
				
				
			actualtext = registerstudent.SuccessMessage.getText().trim();
			expectedtext = "Great! We found you. Check your email for information on how to change or cancel your registration.";
					
			if(actualtext.equals(expectedtext))
			{
						
				System.out.println("Success !! Success message is present with correct text. i.e." + "\n"+ expectedtext);
				Reporter.log("Success !! Success message is present with correct text. i.e." + "\n"+ expectedtext);
							
			}
			else
			{
								
				System.out.println("Failure !! Incorrect Message" + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect Message" + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
								
				AssertFailedCount++;
							
			}		
			
		System.out.println("Step 10 : Navigating to Email Box and verifying whether email is received with correct details or not");
		Reporter.log("Step 10 : Navigating to Email Box and verifying whether email is received with correct details or not"); 
				
			
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=emautomation#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
				
			//Opening email body and fetching verification URL
					
			SeleniumFunc.ClickOnElement("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");
			Thread.sleep(4000);
			
			driver.switchTo().frame("publicshowmaildivcontent");
			String UpdateRegi = SeleniumFunc.GetElementText("css", "html>body>a");
	
			SeleniumFunc.ToGoToUrl(UpdateRegi);
			Thread.sleep(5000);

			
		System.out.println("Step 11 : Verifying UI elements on identity verification page.");
		Reporter.log("Step 11 :  Verifying UI elements on identity verification page."); 
						
		
			//Verify Header
			actualtext= SeleniumFunc.GetElementText("css", "#primary_content div h1").trim();						
			expectedtext="Identity Verification"; 
				
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
			
				System.out.println("Success!! Correct Header is displayed. i.e. " + expectedtext);
				Reporter.log("Success!! Correct Header is displayed. i.e. " + expectedtext); 
			
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
			
	
			//Verify Zip Code
			
			if(SeleniumFunc.IsElementPresent("css", "#zip"))
			{
		
				System.out.println("Success !! Zip Code Textbox is present");
				Reporter.log("Success !! Zip Code Textbox is present"); 
			}
			else
			{
			
				System.out.println("Failure !! Zip Code Textbox is NOT present ");
				Reporter.log("Failure !! Zip Code Textbox is NOT present "); 
			
				AssertFailedCount++;
			}
		
			//Verify Phone Number
		
			if(SeleniumFunc.IsElementPresent("css", "#phone"))
			{
		
			System.out.println("Success !! Phone Number Textbox is present");
			Reporter.log("Success !! Phone Number Textbox is present"); 
			
			}
			else
			{
			
				System.out.println("Failure !! Phone Number Textbox is NOT present ");
				Reporter.log("Failure !! Phone Number Textbox is NOT present "); 
			
				AssertFailedCount++;
			}
		
			
			//Verify Button
			
			if(SeleniumFunc.IsElementPresent("css", "#form_login_challenge div:nth-of-type(4) button"))
			{
		
			System.out.println("Success !! Verify Button is present");
			Reporter.log("Success !! Verify Button is present"); 
			
			}
			else
			{
			
				System.out.println("Failure !! Verify Button is NOT present ");
				Reporter.log("Failure !! Verify Button is NOT present "); 
			
				AssertFailedCount++;
			}
		

			//Verify Note
			actualtext= SeleniumFunc.GetElementText("css", ".alert.alert-info").trim();						
			expectedtext="For your security, please provide the ZIP Code and phone number you" +
						 " used when registering." + "\n" +
						 "If you registered a group, use details from the first student."; 
				
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
			
				System.out.println("Success!! Correct Note is displayed. i.e. " + expectedtext);
				Reporter.log("Success!! Correct Note is displayed. i.e. " + expectedtext); 
			
			}
			else
			{
			
				System.out.println("Failure !! Incorrect Note is displayed." + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect Note is displayed." + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
					
				AssertFailedCount++;
		
			}	
			
			//Verify Customer Service Section
			
			actualtext= SeleniumFunc.GetElementText("css", "#primary_content>p").trim();						
			expectedtext="If you need assistance, you can contact our Customer Service Team."; 
				
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
			
				System.out.println("Success!! Correct text for customer care is displayed. i.e. " + expectedtext);
				Reporter.log("Success!! Correct text for customer care is displayed. i.e. " + expectedtext); 
			
			}
			else
			{
			
				System.out.println("Failure !! Incorrect text for customer care is displayed." + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect text for customer care is displayed." + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
					
				AssertFailedCount++;
		
			}	
			
			
		
/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			try {
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
	 * Verify Identity Verification Functionality
	 */ 
	@Test
	private void VerifyIdentityVerificationFunctionality() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify Identity Verification Functionality"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify Identity Verification Functionality"  + "\n" +
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
		//	instructorcreateevent.SelectDate("5");
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
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			Thread.sleep(5000);

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
		  	
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
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

		System.out.println("Step 8 : Go to register-ed and make registration");
		Reporter.log("Step 8 : Go to register-ed and make registration"); 
					
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);
			Thread.sleep(5000);

			//Register First Student
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
			
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);

			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(5000);

			registerstudent.ClickOnAgreePolicyCheckBox();
			registerstudent.ClickOnCompleteRegistrationButton();
			Thread.sleep(5000);

			
					
			//Go to update registration
			homepage.ClickOnUpdateRegiButton();
			Thread.sleep(5000);

				
			//Enter details to update registration
			registerstudent.EnterEmailAddress(EmailAddress);
			registerstudent.ClickOnGoButton();
			Thread.sleep(5000);

			
		System.out.println("Step 9 : Verify Success Message");
		Reporter.log("Step 9 : Verify Success Message");
				
				
			actualtext = registerstudent.SuccessMessage.getText().trim();
			expectedtext = "Great! We found you. Check your email for information on how to change or cancel your registration.";
					
			if(actualtext.equals(expectedtext))
			{
						
				System.out.println("Success !! Success message is present with correct text. i.e." + "\n"+ expectedtext);
				Reporter.log("Success !! Success message is present with correct text. i.e." + "\n"+ expectedtext);
							
			}
			else
			{
								
				System.out.println("Failure !! Incorrect Message" + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect Message" + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
								
				AssertFailedCount++;
							
			}		
			
		System.out.println("Step 10 : Navigating to Email Box and verifying whether email is received with correct details or not");
		Reporter.log("Step 10 : Navigating to Email Box and verifying whether email is received with correct details or not"); 
				
			
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=emautomation#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
				
			//Opening email body and fetching verification URL
					
			SeleniumFunc.ClickOnElement("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");
			Thread.sleep(4000);
			
			driver.switchTo().frame("publicshowmaildivcontent");
			String UpdateRegi = SeleniumFunc.GetElementText("css", "html>body>a");
	
			SeleniumFunc.ToGoToUrl(UpdateRegi);	
			Thread.sleep(5000);

		System.out.println("Step 11 : Verifying validation with invalid values.");
		Reporter.log("Step 11 : Verifying validation with invalid values."); 
						
		
			registerstudent.EnterZipCode("55");
			registerstudent.EnterPhoneNo("55");
			registerstudent.ClickOnVerifyButton();
		
			Thread.sleep(4000);
			//Verify Zip Validation
			actualtext= SeleniumFunc.GetElementText("css", "#zip_errorMsg").trim();						
			expectedtext="A 5 digit number."; 
				
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
			
				System.out.println("Success!! Correct validation is displayed for zip. i.e. " + expectedtext);
				Reporter.log("Success!! Correct validation is displayed for zip. i.e. " + expectedtext); 
			
			}
			else
			{
			
				System.out.println("Failure !! Incorrect validation is displayed for zip." + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect validation is displayed for zip." + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
					
				AssertFailedCount++;
		
			}	
			
			//Verify Phone Validation
		
			actualtext= SeleniumFunc.GetElementText("css", "#phone_errorMsg").trim();						
			expectedtext="Please enter a valid phone number."; 
				
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
			
				System.out.println("Success!! Correct validation is displayed for Phone. i.e. " + expectedtext);
				Reporter.log("Success!! Correct validation is displayed for Phone. i.e. " + expectedtext); 
			
			}
			else
			{
			
				System.out.println("Failure !! Incorrect validation is displayed for Phone." + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect validation is displayed for Phone." + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
					
				AssertFailedCount++;
		
			}	
			
		
		System.out.println("Step 12 : Add invalid values for identity verification. (not entered while registration).");
		Reporter.log("Step 12 : Add invalid values for identity verification.(not entered while registration)."); 
							
		
			registerstudent.EnterZipCode("25826");
			registerstudent.EnterPhoneNo("9898989123");
			registerstudent.ClickOnVerifyButton();
			Thread.sleep(5000);

			//Verify Phone Validation
			actualtext= SeleniumFunc.GetElementText("css", ".alert.alert-error").trim();						
			expectedtext="The phone number and zip code did not match any that we have on record for you."; 
					
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
				
				System.out.println("Success!! Correct validation is displayed for invalid combination. i.e. " + expectedtext);
				Reporter.log("Success!! Correct validation is displayed for invalid combination. i.e. " + expectedtext); 
				
			}
			else
			{
				
				System.out.println("Failure !! Incorrect validation is displayed for invalid combination." + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect validation is displayed for invalid combination." + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
					
				AssertFailedCount++;
			
			}	
			
		
/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
		System.out.println("Step 13 : Add valid values for identity verification.");
		Reporter.log("Step 13 : Add valid values for identity verification."); 
									
			
			registerstudent.EnterZipCode("25825");
			registerstudent.EnterPhoneNo("9898989898");
			registerstudent.ClickOnVerifyButton();			
			Thread.sleep(5000);

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			//Verify Phone Validation
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
			
			
			try {
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
	 * Verify Manage Registration functionality
	 */ 
	@Test
	private void VerifyManageRegistration() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify Manage Registration functionality"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Verify Manage Registration functionality"  + "\n" +
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
			//instructorcreateevent.SelectDate("5");
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
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			Thread.sleep(5000);

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
		  	
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
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

			
		System.out.println("Step 8 : Go to register-ed and make registration");
		Reporter.log("Step 8 : Go to register-ed and make registration"); 
					
			
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

			//Register First Student
			eventview.ClickOnRegisterNowButton();
			Thread.sleep(5000);

			String FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			String UserName="emstudent"+ JH.GenerateRandomNumber();
			String EmailAddress = UserName + "@mailinator.com";
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");			
			registerstudent.ClickToSelectGender();
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);

			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(5000);

			registerstudent.ClickOnAgreePolicyCheckBox();
			Thread.sleep(2000);
			registerstudent.ClickOnCompleteRegistrationButton();
			
			Thread.sleep(5000);
			//Go to update registration
			homepage.ClickOnUpdateRegiButton();
			
			Thread.sleep(5000);

			//Enter details to update registration
			registerstudent.EnterEmailAddress(EmailAddress);
			registerstudent.ClickOnGoButton();
			
			Thread.sleep(5000);

		System.out.println("Step 9 : Verify Success Message");
		Reporter.log("Step 9 : Verify Success Message");
				
				
			actualtext = registerstudent.SuccessMessage.getText().trim();
			expectedtext = "Great! We found you. Check your email for information on how to change or cancel your registration.";
					
			if(actualtext.equals(expectedtext))
			{
						
				System.out.println("Success !! Success message is present with correct text. i.e." + "\n"+ expectedtext);
				Reporter.log("Success !! Success message is present with correct text. i.e." + "\n"+ expectedtext);
							
			}
			else
			{
								
				System.out.println("Failure !! Incorrect Message" + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect Message" + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
								
				AssertFailedCount++;
							
			}		
			
			
		System.out.println("Step 10 : Navigating to Email Box and verifying whether email is received with correct details or not");
		Reporter.log("Step 10 : Navigating to Email Box and verifying whether email is received with correct details or not"); 
				
			
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=emautomation#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
				
			//Opening email body and fetching verification URL
					
			SeleniumFunc.ClickOnElement("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");
			Thread.sleep(4000);
			
			driver.switchTo().frame("publicshowmaildivcontent");
			String UpdateRegi = SeleniumFunc.GetElementText("css", "html>body>a");
	
			SeleniumFunc.ToGoToUrl(UpdateRegi);
				
		
		System.out.println("Step 11 : Add valid values for identity verification.");
		Reporter.log("Step 11 : Add valid values for identity verification."); 
									
			
			registerstudent.EnterZipCode("25825");
			registerstudent.EnterPhoneNo("9898989898");
			registerstudent.ClickOnVerifyButton();			
			Thread.sleep(5000);

			
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
			
			
			//Go to view event
			
			eventview.ClickOnViewEventButton();
			Thread.sleep(5000);

			actualtext= SeleniumFunc.GetElementText("css", ".alert.alert-warning").trim();						
			expectedtext="You are currently registered for this event."; 
					
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
				
				System.out.println("Success!! User is redirected to event view page. i.e. " + expectedtext);
				Reporter.log("Success!! User is redirected to event view page. i.e. " + expectedtext); 
				
			}
			else
			{
				
				System.out.println("Failure !! User is NOT redirected to event view page." + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! User is NOT redirected to event view page." + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
					
				AssertFailedCount++;
			
			}
			
			
			actualtext= eventview.ViewEvent.getText().trim();				
			expectedtext="View Your Registration"; 
			
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
				
				System.out.println("Success!! View Your Registration button is present");
				Reporter.log("Success!! View Your Registration button is present"); 
				
			}
			else
			{
				
				System.out.println("Failure !! View Your Registration button is NOT present");
				Reporter.log("Failure !! View Your Registration button is NOT present");
					
				AssertFailedCount++;
			
			}
			
			
			
			
			//Click on View Your Registration
			eventview.ClickOnViewRegistrationButton();
			Thread.sleep(5000);

			actualtext= SeleniumFunc.GetElementText("css", ".pull-left");			
			expectedtext="Manage Registration"; 
			
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
									
				System.out.println("Success!! User is redirected to Manage Registration. i.e.Title: " + expectedtext);
				Reporter.log("Success!! User is redirected to Manage Registration. i.e.Title: " + expectedtext); 
					
			}
			else
			{
				
				System.out.println("Failure !! User is NOT redirected to Manage Registration." + "\n" + "Expected Title: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! User is NOT redirected to Manage Registration." + "\n" + "Expected Title: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
					
				AssertFailedCount++;
			
			}
			
/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			try {
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
	 * Verify Cancel Registration Functionality
	 */ 
	@Test
	private void VerifyCancelRegistration() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Verify Cancel Registration Functionality"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Verify Cancel Registration Functionality"  + "\n" +
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
			//instructorcreateevent.SelectDate("5");
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
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
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

		
		System.out.println("Step 8 : Go to register-ed and make registration");
		Reporter.log("Step 8 : Go to register-ed and make registration"); 
					
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);
			Thread.sleep(5000);
		
			//Register First Student
			eventview.ClickOnRegisterNowButton();
			Thread.sleep(5000);

			String FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			String UserName="emstudent"+ JH.GenerateRandomNumber();
			String EmailAddress = UserName + "@mailinator.com";
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");			
			registerstudent.ClickToSelectGender();
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);

			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(5000);

			registerstudent.ClickOnAgreePolicyCheckBox();
			registerstudent.ClickOnCompleteRegistrationButton();
			Thread.sleep(5000);			
					
			//Go to update registration
			homepage.ClickOnUpdateRegiButton();
			Thread.sleep(5000);

				
			//Enter details to update registration
			registerstudent.EnterEmailAddress(EmailAddress);
			registerstudent.ClickOnGoButton();				
			
			Thread.sleep(10000);
			
		System.out.println("Step 9 : Navigating to Email Box and verifying whether email is received with correct details or not");
		Reporter.log("Step 9 : Navigating to Email Box and verifying whether email is received with correct details or not"); 
				
		
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=emautomation#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
				
			//Opening email body and fetching verification URL
					
			SeleniumFunc.ClickOnElement("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");
			Thread.sleep(4000);
			
			driver.switchTo().frame("publicshowmaildivcontent");
			String UpdateRegi = SeleniumFunc.GetElementText("css", "html>body>a");
	
			SeleniumFunc.ToGoToUrl(UpdateRegi);
			Thread.sleep(5000);

		System.out.println("Step 10 : Add valid values for identity verification.");
		Reporter.log("Step 10 : Add valid values for identity verification."); 
									
			
			registerstudent.EnterZipCode("25825");
			registerstudent.EnterPhoneNo("9898989898");
			registerstudent.ClickOnVerifyButton();			
			Thread.sleep(5000);

			
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
			Thread.sleep(5000);

			
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

			//Verify Cancel Confirm Button
			
			if(SeleniumFunc.IsElementPresent("css", "#cancel_confirm"))
			{
		
			System.out.println("Success !! Cancel Confirm Button is present");
			Reporter.log("Success !! Cancel Confirm Button is present"); 
			
			}
			else
			{
			
				System.out.println("Failure !! Cancel Confirm Button is NOT present ");
				Reporter.log("Failure !! Cancel Confirm Button is NOT present "); 
			
				AssertFailedCount++;
			}
			
			//Verify Don't Cancel Button
			
			if(SeleniumFunc.IsElementPresent("css", "#cancel_modal div:nth-of-type(3) a:nth-of-type(2)"))
			{
		
			System.out.println("Success !! Don't Cancel Button is present");
			Reporter.log("Success !! Don't Cancel Button is present"); 
			
			}
			else
			{
			
				System.out.println("Failure !! Don't Cancel Button is NOT present ");
				Reporter.log("Failure !! Don't Cancel Button is NOT present "); 
			
				AssertFailedCount++;
			}
		
			
			//Cancel Registration
			//eventview.ClickOnCancelRegistrationButton();
			SeleniumFunc.ClickOnElement("css", "#cancel_confirm");
			Thread.sleep(5000);

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
			

			try {
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
	
	
	/* Test 8: 
	 * Verify Update Registration Functionality
	 */ 
	@Test
	private void VerifyUpdateRegistration() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Verify Update Registration Functionality"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Verify Update Registration Functionality"  + "\n" +
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
			//instructorcreateevent.SelectDate("5");
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
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			Thread.sleep(5000);

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
		  	
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
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

			
		System.out.println("Step 8 : Go to register-ed and make registration");
		Reporter.log("Step 8 : Go to register-ed and make registration"); 
					
			
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

			
			//Register First Student
			eventview.ClickOnRegisterNowButton();
			Thread.sleep(5000);

			String FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			String UserName="emstudent"+ JH.GenerateRandomNumber();
			String EmailAddress = UserName + "@mailinator.com";
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");			
			registerstudent.ClickToSelectGender();
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);

			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(5000);

			registerstudent.ClickOnAgreePolicyCheckBox();
			Thread.sleep(2000);
			registerstudent.ClickOnCompleteRegistrationButton();
			Thread.sleep(5000);

					
			//Go to update registration
			homepage.ClickOnUpdateRegiButton();
			Thread.sleep(5000);

				
			//Enter details to update registration
			registerstudent.EnterEmailAddress(EmailAddress);
			registerstudent.ClickOnGoButton();				
			
			Thread.sleep(10000);
			
		System.out.println("Step 9 : Navigating to Email Box and verifying whether email is received with correct details or not");
		Reporter.log("Step 9 : Navigating to Email Box and verifying whether email is received with correct details or not"); 
				
			
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=emautomation#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
				
			//Opening email body and fetching verification URL
					
			SeleniumFunc.ClickOnElement("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");
			Thread.sleep(4000);
			
			driver.switchTo().frame("publicshowmaildivcontent");
			String UpdateRegi = SeleniumFunc.GetElementText("css", "html>body>a");
	
			SeleniumFunc.ToGoToUrl(UpdateRegi);
			Thread.sleep(5000);

		System.out.println("Step 10 : Add valid values for identity verification.");
		Reporter.log("Step 10 : Add valid values for identity verification."); 
												
			registerstudent.EnterZipCode("25825");
			registerstudent.EnterPhoneNo("9898989898");
			registerstudent.ClickOnVerifyButton();			
			Thread.sleep(5000);

			
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
			
			//Click on Add Student Button
			eventview.ClickOnAddStudentButton();
			Thread.sleep(5000);

			FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
						
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.ClickToSelectGender();
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896346");
			registerstudent.ClickOnAddThisStudentButton();
			Thread.sleep(5000);
			
			//Verify Student is added
			expectedtext=FirstName+ " Testing"; 
			actualtext = SeleniumFunc.GetElementText("css", "#student_list li:nth-of-type(2) strong");
						
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
			
				System.out.println("Success!! Second Student Added via Update Registration");
				Reporter.log("Success!! Second Student Added via Update Registration"); 
			
			}
			else
			{
			
				System.out.println("Failure !!  Error While Registration" + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Error While Registration" + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
					
				AssertFailedCount++;
			
			}		

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			try {
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
	 * Verify user can not edit cancelled registrations
	 */ 
	@Test
	private void NoEditCancelRegistration() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Verify user can not edit cancelled registrations"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Verify user can not edit cancelled registrations"  + "\n" +
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
			//instructorcreateevent.SelectDate("5");
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

			//login.ClickOnLogoutButton();
			//Thread.sleep(5000);

		
		System.out.println("Step 8 : Go to register-ed and make registration");
		Reporter.log("Step 8 : Go to register-ed and make registration"); 
					
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);
			Thread.sleep(5000);
		
			//Register First Student
			eventview.ClickOnRegisterNowButton();
			Thread.sleep(5000);

			String FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			String UserName="emstudent"+ JH.GenerateRandomNumber();
			String EmailAddress = UserName + "@mailinator.com";
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");			
			registerstudent.ClickToSelectGender();
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);

			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(5000);
			registerstudent.ClickOnIUnderstand();
			Thread.sleep(2000);
			registerstudent.ClickOnAgreePolicyCheckBox();
			registerstudent.ClickOnCompleteRegistrationButton();
			Thread.sleep(5000);			
					
			//Go to update registration
			homepage.ClickOnUpdateRegiButton();
			Thread.sleep(5000);

				
			//Enter details to update registration
			registerstudent.EnterEmailAddress(EmailAddress);
			registerstudent.ClickOnGoButton();				
			
			Thread.sleep(10000);
			
		System.out.println("Step 9 : Navigating to Email Box and verifying whether email is received with correct details or not");
		Reporter.log("Step 9 : Navigating to Email Box and verifying whether email is received with correct details or not"); 
				
		
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=emautomation#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
				
			//Opening email body and fetching verification URL
					
			SeleniumFunc.ClickOnElement("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");
			Thread.sleep(4000);
			
			driver.switchTo().frame("publicshowmaildivcontent");
			String UpdateRegi = SeleniumFunc.GetElementText("css", "html>body>a");
	
			SeleniumFunc.ToGoToUrl(UpdateRegi);
			Thread.sleep(5000);

		System.out.println("Step 10 : Add valid values for identity verification.");
		Reporter.log("Step 10 : Add valid values for identity verification."); 
									
			
			registerstudent.EnterZipCode("25825");
			registerstudent.EnterPhoneNo("9898989898");
			registerstudent.ClickOnVerifyButton();			
			Thread.sleep(5000);

			
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
			Thread.sleep(5000);
			
			//Cancel Registration
			//eventview.ClickOnCancelRegistrationButton();
			SeleniumFunc.ClickOnElement("css", "#cancel_confirm");
			Thread.sleep(5000);

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
			
			//

		System.out.println("Step 10 : Verify User can not edit Cancel Registration");
		Reporter.log("Step 10 : Verify User can not edit Cancel Registration"); 
		
			if(!SeleniumFunc.IsElementPresent("css", ".btn.edit"))
			{
				System.out.println("Success !! User can not edit Cancel Registration");
				Reporter.log("Success !! User can not edit Cancel Registration"); 
			}
			else
			{
				System.out.println("Failure !! User can edit Cancel Registration");
				Reporter.log("Failure !! User can edit Cancel Registration"); 
		
				AssertFailedCount++;
			}
	
			/*
			try {
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
}
