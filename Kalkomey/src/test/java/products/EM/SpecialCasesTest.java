package products.EM;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod; 
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.CertificationInformationPage;
import pageFactory.Courses.ContentsPage;
import pageFactory.Courses.FinalExamPage;
import pageFactory.Courses.KECourseAdminPage;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.PaymentInformationPage;
import pageFactory.Courses.ProfileNewPage;
import pageFactory.Courses.RegistrationNewPage;
import pageFactory.Courses.ViewReceiptPage;
import pageFactory.EM.AdminSearchEventPage;
import pageFactory.EM.AgencyCreateUserPage;
import pageFactory.EM.AgencyHomePage;
import pageFactory.EM.EMAddStudentPage;
import pageFactory.EM.EMSendAMessagePage;
import pageFactory.EM.EMStudentRegistrationPage;
import pageFactory.EM.ErrorPage;
import pageFactory.EM.InstructorCreateEventPage;
import pageFactory.EM.InstructorEventRosterPage;
import pageFactory.EM.InstructorHomePage;
import pageFactory.EM.InstructorLocationPage;
import pageFactory.EM.InstructorProfilePage;
import pageFactory.EM.InstructorSchedulePage;
import pageFactory.EM.InstructorSearchPage;
import pageFactory.EM.LoginPage;
import pageFactory.EM.ManageProgramFilesPage;
import pageFactory.EM.PaymentSettingsPage;
import pageFactory.EM.RegisterEdEventView;
import pageFactory.EM.RegisterEdRegisterStudentPage;
import pageFactory.EM.StudentResultPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;
import org.openqa.selenium.JavascriptExecutor;

public class SpecialCasesTest 
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
	
	  /*
	 Test 1: 
	 * Verify Send Message Registration Action
	 */
	@Test
	private void SendAMessage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify Send Message Registration Action"  + "\n" +"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify Send Message Registration Action"  + "\n" +  "====");	
		
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
		EMStudentRegistrationPage registration = new EMStudentRegistrationPage(driver);
		AdminSearchEventPage adminsearchevent = new AdminSearchEventPage(driver);

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

			
		System.out.println("Step 7 : Go to Register-Ed and register as student");
		Reporter.log("Step 7 : Go to Register-Ed and register as student"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);
			Thread.sleep(5000);

			eventview.ClickOnRegisterNowButton();
			Thread.sleep(5000);	
							
			
			//Enter Valid Details and Go to 2nd Step
			String FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			String UserName="emstudent"+ JH.GenerateRandomNumber();
			String EmailAddress = UserName + "@mailinator.com";
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEthnicity("Asian");
		
			registerstudent.ClickToSelectGender();
			
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);

			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("css", ".btn.cancel");
			Thread.sleep(4000);			
			
			//Verify user is redirected to second step of registration
			//expectedtext = "You’re almost done with registration!";
			//actualtext = SeleniumFunc.GetElementText("css", ".alert-heading");
		
			//if(actualtext.equalsIgnoreCase(expectedtext))
			  String expectedtext1 = "You";
			  String expectedtext2 = "re almost done with registration!";
			  actualtext = SeleniumFunc.GetElementText("css", ".alert-heading");

			 if(actualtext.startsWith(expectedtext1) && actualtext.endsWith(expectedtext2) )
			{
				
				System.out.println("Success !! User is redirected to second step of registration.");
				Reporter.log("Success !! User is redirected to second step of registration.");
			
			}
			else
			{
			
				System.out.println("Failure !! Error while registration." + "\n" + "Expected : "  
									+ "\n" + expectedtext1+expectedtext2 + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Error while registration." + "\n" + "Expected : "  
						+ "\n" + expectedtext1+expectedtext2 + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
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
			
		System.out.println("Step 8 : Login to EM and go to Student Registration");
		Reporter.log("Step 8 : Login to EM and go to Student Registration"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);

			login.ClickOnLogInButton();			
			Thread.sleep(5000);
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/" + EventID);
			Thread.sleep(5000);

			//Go to Student Registration
			instructoreventroster.ClickOnSelectFirstStudent();
			Thread.sleep(4000);
			
			instructoreventroster.ClickOnViewRegistrationButton();
			Thread.sleep(4000);
			
		System.out.println("Step 9 :Verify Send Message Functionality");
		Reporter.log("Step 9 :Verify Send Message Functionality"); 
			
			registration.SendAMessage.click();
			Thread.sleep(4000);

			//Verify Send A Message modal is opened
			
			if(SeleniumFunc.IsElementPresent("css","#send-message-modal[style='display: block;']"))
			{
				System.out.println("Success !! Send a Message modal is opened.");
				Reporter.log("Success !! Send a Message modal is opened."); 
			}
			else
			{
				System.out.println("Failure !! Send a Message modal is NOT opened.");
				Reporter.log("Failure !! Send a Message modal is NOT opened.");			
				AssertFailedCount++;
			}
			
			//Verify Send A Message modal is closed
			registration.Close.click();
			Thread.sleep(4000);
			
			if(!SeleniumFunc.IsElementPresent("css","#send-message-modal[style='display: block;']"))
			{
				System.out.println("Success !! Send a Message modal is Closed.");
				Reporter.log("Success !! Send a Message modal is Closed."); 
			}
			else
			{
				System.out.println("Failure !! Send a Message modal is NOT Closed.");
				Reporter.log("Failure !! Send a Message modal is NOT Closed.");			
				AssertFailedCount++;
			}
			
			//Verify Subject is pre-filled
			registration.SendAMessage.click();
			Thread.sleep(4000);

			expectedtext = "Information about your Standard Course";
			actualtext = registration.Subject.getAttribute("value");
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Subject is prefilled with proper text. i.e. -" +actualtext);
				Reporter.log("Success !! Subject is prefilled with proper text. i.e. -" +actualtext);
			
			}
			else
			{
			
				System.out.println("Failure !! Subject is not prefilled or subject is incorrect" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Subject is not prefilled or subject is incorrect" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			//Send Message with valid details
			registration.Body.sendKeys("This is Test Message. Ke-Testing!!");
			registration.SendMessageButton.click();
			Thread.sleep(4000);
			
			if(SeleniumFunc.IsElementPresent(registration.SuccessMessage))
			{
				System.out.println("Success !! Success message is present.");
				Reporter.log("Success !! Success message is present."); 
				
				//expectedtext = "×" + "\n" + "Message sent!";
				expectedtext = "Message sent!";
				actualtext = registration.SuccessMessage.getText().trim();
				
				if(actualtext.endsWith(expectedtext))
				{
					System.out.println("Success !! Proper success message is displayed. i.e. -" +actualtext);
					Reporter.log("Success !! Proper success message is displayed. i.e. -" +actualtext);
				
				}
				else
				{
				
					System.out.println("Failure !! Success Message is incorrect." + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !!  Success Message is incorrect." + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				
				}
			}
			else
			{
				System.out.println("Success !! Success message is NOT present.");
				Reporter.log("Success !! Success message is NOT present."); 
			}
			
			
			try {
				//Go to instructor and delete the event
				

				//Going to Event Roster page and removing student and deleting  event 
				//so new event can be added with same test data
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/" + EventID);
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
			
		
		
		 // Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 
	 	if(AssertFailedCount>0)	
		{
			
			//Marking this test as Failed
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
		
	}
	
	
	
	  /*
	 Test 2: 
	 * Verify Cancel Registration Action
	 */
	@Test
	private void CancelRegistration() throws Exception
	{

		System.out.println("====" + "\n" +
					"Test 2: Verify Cancel Registration Action"  + "\n" +"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify Cancel Registration Action"  + "\n" +  "====");	
		
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
		EMStudentRegistrationPage registration = new EMStudentRegistrationPage(driver);
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
		//	instructorschedule.DeleteAllEvents();
			 
			
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
			System.out.println("Url :"+URL);
				
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
			
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency/?");
			//Thread.sleep(5000);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
			Thread.sleep(2000);
			
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

		System.out.println("Step 7 : Go to Register-Ed and register as student");
		Reporter.log("Step 7 : Go to Register-Ed and register as student"); 
				
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);
			Thread.sleep(5000);

			eventview.ClickOnRegisterNowButton();
			Thread.sleep(5000);	
							
			
			//Enter Valid Details and Go to 2nd Step
			String FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			String UserName="emstudent"+ JH.GenerateRandomNumber();
			String EmailAddress = UserName + "@mailinator.com";
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickToSelectGender();
			Thread.sleep(2000);
			
			registerstudent.ClickOnNextButton();
			Thread.sleep(8000);

			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(8000);

			//Verify user is redirected to second step of registration
			//expectedtext = "You’re almost done with registration!";
			String expectedtext1 = "You";
			String expectedtext2 = "re almost done with registration!";
			actualtext = SeleniumFunc.GetElementText("css", ".alert-heading");

			if(actualtext.startsWith(expectedtext1) && actualtext.endsWith(expectedtext2) )
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
			Thread.sleep(5000);
			registerstudent.ClickOnIUnderstand();
			registerstudent.ClickOnAgreePolicyCheckBox();
			Thread.sleep(5000);
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
			
		System.out.println("Step 9 : Login to EM and go to Student Registration");
		Reporter.log("Step 9 : Login to EM and go to Student Registration"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);

			login.ClickOnLogInButton();			
			Thread.sleep(5000);
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/" + EventID);
			Thread.sleep(5000);
			
			//SeleniumFunc.ClickOnElement("linkText", "Event Roster");

			//Go to Student Registration 
			instructoreventroster.ClickOnSelectFirstStudent();
			Thread.sleep(4000);
			
			instructoreventroster.ClickOnViewRegistrationButton();
			Thread.sleep(4000);
			
		System.out.println("Step 10 : Verify Cancel Registration Functionality");
		Reporter.log("Step 10 : Verify Cancel Registration Functionality"); 
			
			registration.CancelRegistration.click();
			Thread.sleep(4000);

			//Verify Cancel Registration modal is opened
			
			if(SeleniumFunc.IsElementPresent("css","#cancel[style='display: block;']"))
			{
				System.out.println("Success !! Cancel Registration modal is opened.");
				Reporter.log("Success !! Cancel Registration modal is opened."); 
			}
			else
			{
				System.out.println("Failure !! Cancel Registration modal is NOT opened.");
				Reporter.log("Failure !! Cancel Registration modal is NOT opened.");			
				AssertFailedCount++;
			}
			
			//Verify Cancel Registration modal is closed
			registration.CloseCancel.click();
			Thread.sleep(4000);
			
			if(!SeleniumFunc.IsElementPresent("css","#cancel[style='display: block;']"))
			{
				System.out.println("Success !! Cancel Registration modal is Closed.");
				Reporter.log("Success !! Cancel Registration modal is Closed."); 
			}
			else
			{
				System.out.println("Failure !! Cancel Registration modal is NOT Closed.");
				Reporter.log("Failure !! Cancel Registration modal is NOT Closed.");			
				AssertFailedCount++;
			}
			
			//Cancel Registration
			registration.CancelRegistration.click();
			Thread.sleep(4000);
			
			registration.Cancel.click();
			Thread.sleep(4000);

			if(SeleniumFunc.IsElementPresent(registration.CancelSuccess))
			{
				System.out.println("Success !! Success message is present.");
				Reporter.log("Success !! Success message is present."); 
				
				expectedtext = FirstName + " Testing has been removed from the event.";
				actualtext = registration.CancelSuccess.getText().trim();
				
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! Proper success message is displayed. i.e. -" +actualtext);
					Reporter.log("Success !! Proper success message is displayed. i.e. -" +actualtext);
				
				}
				else
				{
				
					System.out.println("Failure !! Success Message is incorrect." + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !!  Success Message is incorrect." + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				
				}
			}
			else
			{
				System.out.println("Success !! Success message is NOT present.");
				Reporter.log("Success !! Success message is NOT present."); 
			}
			
			
			try {
				//Go to instructor and delete the event
				
				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				
				Thread.sleep(5000);
			} catch (Exception e) {
				
			}
			
		
		
		 // Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 
	 	if(AssertFailedCount>0)	
		{
			
			//Marking this test as Failed
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
		
	}
	
	
	
	  /*
		 Test 3: 
		 * Verify Send Certificate Registration Action
		 */
		@Test
		private void SendCertificate() throws Exception
		{

			System.out.println("====" + "\n" +
						"Test 3 : Verify Send Certificate Registration Action"  + "\n" +"====");
			Reporter.log("====" + "\n" +
			 			  "Test 3 : Verify Send Certificate Registration Action"  + "\n" +  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			EMStudentRegistrationPage registration = new EMStudentRegistrationPage(driver);
	
			System.out.println("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			
				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);
		
				//Go to Student Registration
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM + "/eventregistration/view/8331669");
				Thread.sleep(5000);

			System.out.println("Step 2 :Verify Send Certificate Functionality");
			Reporter.log("Step 2 :Verify Send Certificate Functionality"); 
				
				registration.SendCertificate.click();
				Thread.sleep(4000);

				//Verify Send A Message modal is opened
				
				if(SeleniumFunc.IsElementPresent("css","#send-certificate-modal[style='display: block;']"))
				{
					System.out.println("Success !! Send a Certificate modal is opened.");
					Reporter.log("Success !! Send a Certificate modal is opened."); 
				}
				else
				{
					System.out.println("Failure !! Send a Certificate modal is NOT opened.");
					Reporter.log("Failure !! Send a Certificate modal is NOT opened.");			
					AssertFailedCount++;
				}
				
				//Verify Send A Message modal is closed
				registration.CancelSendCertificate.click();
				Thread.sleep(4000);
				
				if(!SeleniumFunc.IsElementPresent("css","#send-certificate-modal[style='display: block;']"))
				{
					System.out.println("Success !! Send a Certificate modal is Closed.");
					Reporter.log("Success !! Send a Certificate modal is Closed."); 
				}
				else
				{
					System.out.println("Failure !! Send a Certificate modal is NOT Closed.");
					Reporter.log("Failure !! Send a Certificate modal is NOT Closed.");			
					AssertFailedCount++;
				}
				
				//Verify Subject is pre-filled
				registration.SendCertificate.click();
				Thread.sleep(4000);

				registration.SendCertificateButton.click();
				Thread.sleep(4000);
				
				if(SeleniumFunc.IsElementPresent(registration.SuccessMessage))
				{
					System.out.println("Success !! Success message is present.");
					Reporter.log("Success !! Success message is present."); 
					
					String expectedtext = "×" + "\n" + "Certificate sent!";
					String actualtext = registration.SuccessMessage.getText().trim();
					
					if(actualtext.equals(expectedtext))
					{
						System.out.println("Success !! Proper success message is displayed. i.e. -" +actualtext);
						Reporter.log("Success !! Proper success message is displayed. i.e. -" +actualtext);
					
					}
					else
					{
					
						System.out.println("Failure !! Success Message is incorrect." + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !!  Success Message is incorrect." + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					
					}
				}
				else
				{
					System.out.println("Success !! Success message is NOT present.");
					Reporter.log("Success !! Success message is NOT present."); 
				}
				
				
				
			
			 // Marking Test Pass or Fail as per the value of AssertFailedCount variable
			 
		 	if(AssertFailedCount>0)	
			{
				
				//Marking this test as Failed
				
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
				
				Assert.fail();
			}
			
		}
		
		
		
		  /*
		 Test 4: 
		 * Verify Resend Confirmation Email Registration Action
		 */
		@Test
		private void ResendConfirmationEmail() throws Exception
		{

			System.out.println("====" + "\n" +
						"Test 4 : Verify Resend Confirmation Email Registration Action"  + "\n" +"====");
			Reporter.log("====" + "\n" +
			 			  "Test 4 : Verify Resend Confirmation Email Registration Action"  + "\n" +  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			EMStudentRegistrationPage registration = new EMStudentRegistrationPage(driver);
	
			System.out.println("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");

				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);
		
				//Go to Student Registration
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM + "/eventregistration/view/8331669");
				Thread.sleep(5000);

			System.out.println("Step 2 :Verify  Resend Confirmation Email Functionality");
			Reporter.log("Step 2 :Verify Resend Confirmation Email Functionality"); 
				
				registration.ResendConfirmationEmail.click();
				Thread.sleep(4000);

				//Verify Send A Message modal is opened
				
				if(SeleniumFunc.IsElementPresent("css","#send-registration-modal[style='display: block;']"))
				{
					System.out.println("Success !! Resend Confirmation Email modal is opened.");
					Reporter.log("Success !! Resend Confirmation Email modal is opened."); 
				}
				else
				{
					System.out.println("Failure !! Resend Confirmation Email modal is NOT opened.");
					Reporter.log("Failure !! Resend Confirmation Email modal is NOT opened.");			
					AssertFailedCount++;
				}
				
				//Verify Send A Message modal is closed
				registration.CancelResendConfirmation.click();
				Thread.sleep(4000);
				
				if(!SeleniumFunc.IsElementPresent("css","#send-registration-modal[style='display: block;']"))
				{
					System.out.println("Success !! Resend Confirmation Email modal is Closed.");
					Reporter.log("Success !! Resend Confirmation Email modal is Closed."); 
				}
				else
				{
					System.out.println("Failure !! Resend Confirmation Email modal is NOT Closed.");
					Reporter.log("Failure !! Resend Confirmation Email modal is NOT Closed.");			
					AssertFailedCount++;
				}
				
				//Verify Subject is pre-filled
				registration.ResendConfirmationEmail.click();
				Thread.sleep(4000);

				registration.ResendConfirmationButton.click();
				Thread.sleep(4000);
				
				if(SeleniumFunc.IsElementPresent(registration.SuccessMessage))
				{
					System.out.println("Success !! Success message is present.");
					Reporter.log("Success !! Success message is present."); 
					
					String expectedtext = "×" + "\n" + "Confirmation sent!";
					String actualtext = registration.SuccessMessage.getText().trim();
					
					if(actualtext.equals(expectedtext))
					{
						System.out.println("Success !! Proper success message is displayed. i.e. -" +actualtext);
						Reporter.log("Success !! Proper success message is displayed. i.e. -" +actualtext);
					
					}
					else
					{
					
						System.out.println("Failure !! Success Message is incorrect." + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !!  Success Message is incorrect." + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					
					}
				}
				else
				{
					System.out.println("Success !! Success message is NOT present.");
					Reporter.log("Success !! Success message is NOT present."); 
				}
				
				
				
			
			 // Marking Test Pass or Fail as per the value of AssertFailedCount variable
			 
		 	if(AssertFailedCount>0)	
			{
				
				//Marking this test as Failed
				
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
				
				Assert.fail();
			}
			
		}
		

		  /*
		 Test 5: 
		 * Verify Resend Receipt Registration Action
		 */
		@Test
		private void ResendReceipt() throws Exception
		{

			System.out.println("====" + "\n" +
						"Test 5 : Verify Resend Receipt Registration Action"  + "\n" +"====");
			Reporter.log("====" + "\n" +
			 			  "Test 5 : Verify Resend Receipt Registration Action"  + "\n" +  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			EMStudentRegistrationPage registration = new EMStudentRegistrationPage(driver);
	
			System.out.println("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");

				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);
		
				//Go to Student Registration
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM + "/eventregistration/view/8446667");
				Thread.sleep(5000);

			System.out.println("Step 2 :Verify Resend Receipt Functionality");
			Reporter.log("Step 2 :Verify Resend Receipt Functionality"); 
				
				registration.ResendReceipt.click();
				Thread.sleep(4000);

				//Verify Send A Message modal is opened
				
				if(SeleniumFunc.IsElementPresent("css","#send-receipt-modal[style='display: block;']"))
				{
					System.out.println("Success !! Resend Receipt modal is opened.");
					Reporter.log("Success !! Resend Receipt modal is opened."); 
				}
				else
				{
					System.out.println("Failure !! Resend Receipt modal is NOT opened.");
					Reporter.log("Failure !! Resend Receipt modal is NOT opened.");			
					AssertFailedCount++;
				}
				
				//Verify Send A Message modal is closed
				registration.CancelResendReceipt.click();
				Thread.sleep(4000);
				
				if(!SeleniumFunc.IsElementPresent("css","#send-receipt-modal[style='display: block;']"))
				{
					System.out.println("Success !! Resend Receipt modal is Closed.");
					Reporter.log("Success !! Resend Receipt modal is Closed."); 
				}
				else
				{
					System.out.println("Failure !! Resend Receipt modal is NOT Closed.");
					Reporter.log("Failure !! Resend Receipt modal is NOT Closed.");			
					AssertFailedCount++;
				}
				
				//Verify Subject is pre-filled
				registration.ResendReceipt.click();
				Thread.sleep(4000);

				registration.ResendReceiptButton.click();
				Thread.sleep(4000);
				
				if(SeleniumFunc.IsElementPresent(registration.SuccessMessage))
				{
					System.out.println("Success !! Success message is present.");
					Reporter.log("Success !! Success message is present."); 
					
					String expectedtext = "×" + "\n" + "Receipt sent!";
					String actualtext = registration.SuccessMessage.getText().trim();
					
					if(actualtext.equals(expectedtext))
					{
						System.out.println("Success !! Proper success message is displayed. i.e. -" +actualtext);
						Reporter.log("Success !! Proper success message is displayed. i.e. -" +actualtext);
					
					}
					else
					{
					
						System.out.println("Failure !! Success Message is incorrect." + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !!  Success Message is incorrect." + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					
					}
				}
				else
				{
					System.out.println("Success !! Success message is NOT present.");
					Reporter.log("Success !! Success message is NOT present."); 
				}
				
				
				
			
			 // Marking Test Pass or Fail as per the value of AssertFailedCount variable
			 
		 	if(AssertFailedCount>0)	
			{
				
				//Marking this test as Failed
				
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
				
				Assert.fail();
			}
			
		}
		
		
		/*
		 Test 6: 
		 * Verify Move to New Event Registration Action
		 */
		@Test
		private void MoveToNewEventAction() throws Exception
		{

			System.out.println("====" + "\n" +
						"Test 6 : Verify Move to New Event Registration Action"  + "\n" +"====");
			Reporter.log("====" + "\n" +
			 			  "Test 6 : Verify Move to New Event Registration Action"  + "\n" +  "====");	
			
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
			EMStudentRegistrationPage registration = new EMStudentRegistrationPage(driver);
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
				instructorlocations.SelectProgram(3);	
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
				String URL1 = driver.getCurrentUrl();
				System.out.println("Url :" +URL1);
				String EventID1 = SeleniumFunc.GetElementText("css", ".span9.tooltip-container");
				System.out.println("Event ID1 :" +EventID1);
				//add another event
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
				instructorlocations.SelectProgram(3);	
				Thread.sleep(5000);

				instructorlocations.ScheduleAnEvent.click();	
				Thread.sleep(5000);
				
				instructorcreateevent.SelectProgram();
				Thread.sleep(2000);
				
				instructorcreateevent.Location.clear();
				instructorcreateevent.Location.sendKeys("Demo Agency Location");
				Thread.sleep(5000);
				instructorcreateevent.Location_FirstChoice.click();
				Thread.sleep(2000);
				
				//instructorcreateevent.GoToNextMonth();
				//instructorcreateevent.SelectDate("5");				

				instructorcreateevent.SelectStartDate("7", "30", "PM");
				instructorcreateevent.SelectEndDate("10", "30", "PM");
					
				instructorcreateevent.AddEventDate.click();
				Thread.sleep(2000);
					
				instructorcreateevent.CreateEvent.click();
				Thread.sleep(5000);
				String URL2 = driver.getCurrentUrl();
				System.out.println("Url 2 :" +URL2);
				String EventID2 = SeleniumFunc.GetElementText("css", ".span9.tooltip-container");
				System.out.println("Event ID1 :" +EventID2);
			System.out.println("Step 4 : Verifying whether Event is added successfully or not");
			Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
						
				//Updated due to change in functionality
				//https://kalkomey.tpondemand.com/entity/14661
				//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
				//Thread.sleep(5000);

				//Verifying Event Time
				String expectedtext = "07:30 PM to 10:30 PM";
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
					/*
				//instructorschedule.FirstEventName.click();
				//Thread.sleep(5000);

				//String EventID = instructoreventroster.EventId.getText().trim();
				//Thread.sleep(3000);
				//System.out.println(EventID);
				
				//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
				//Thread.sleep(5000);

				//instructorcreateevent.SelectNextMonth();
				Thread.sleep(5000);

				instructorschedule.FirstEventName.click();
				Thread.sleep(5000);

				String EventID1 = instructoreventroster.EventId.getText().trim();
				Thread.sleep(3000);
				System.out.println(EventID1);*/
				
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

				adminsearchevent.SelectSearchTypeByVisibleText("ID");
				adminsearchevent.SearchData.sendKeys(EventID1);
				adminsearchevent.SearchButton.click();
				Thread.sleep(5000);

				searchevent.ClickOnSelectEventCheckBox();
				searchevent.SelectEventActionByVisibleText("Set Status: Active");
				searchevent.ClickOnGoButton();
				Thread.sleep(5000);

				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
				Thread.sleep(5000);

				searchevent.ClickOnSearchEventLink();
				Thread.sleep(5000);
				
				adminsearchevent.SelectSearchTypeByVisibleText("ID");
				adminsearchevent.SearchData.sendKeys(EventID2);
				adminsearchevent.SearchButton.click();
				Thread.sleep(5000);
				
				searchevent.ClickOnSelectEventCheckBox();
				searchevent.SelectEventActionByVisibleText("Set Status: Active");
				searchevent.ClickOnGoButton();
				Thread.sleep(5000);
				
				login.ClickOnLogoutButton();
				Thread.sleep(5000);

				
			System.out.println("Step 7 : Go to Register-Ed and register as student");
			Reporter.log("Step 7 : Go to Register-Ed and register as student"); 
			
				
				SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID1);
				Thread.sleep(5000);

				eventview.ClickOnRegisterNowButton();
				Thread.sleep(5000);	
								
				
				//Enter Valid Details and Go to 2nd Step
				String FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
				
				String UserName="emstudent"+ JH.GenerateRandomNumber();
				String EmailAddress = UserName + "@mailinator.com";
				
				registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
				registerstudent.SelectEyeColor();
				registerstudent.SelectHairColor();
				registerstudent.EnterSSNNO("125896336");
				registerstudent.ClickToSelectGender();
				Thread.sleep(2000);
				
				registerstudent.ClickOnNextButton();
				Thread.sleep(5000);

				registerstudent.ClickOnReviewAndConfirmButton();
				Thread.sleep(5000);

				//Verify user is redirected to second step of registration
				//expectedtext = "You’re almost done with registration!";
				//actualtext = SeleniumFunc.GetElementText("css", ".alert-heading");
			
				//if(actualtext.equalsIgnoreCase(expectedtext))
				String expectedtext1 = "You";
				String expectedtext2 = "re almost done with registration!";
				actualtext = SeleniumFunc.GetElementText("css", ".alert-heading");

				if(actualtext.startsWith(expectedtext1) && actualtext.endsWith(expectedtext2) )
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
				
			System.out.println("Step 8 : Login to EM and go to Student Registration");
			Reporter.log("Step 8 : Login to EM and go to Student Registration"); 
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);

				login.ClickOnLogInButton();			
				Thread.sleep(5000);
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/" + EventID1);
				Thread.sleep(5000);

				//Go to Student Registration 
				instructoreventroster.ClickOnSelectFirstStudent();
				Thread.sleep(4000);
				
				instructoreventroster.ClickOnViewRegistrationButton();
				Thread.sleep(4000);
				
			System.out.println("Step 9 : Verify Move To New Event is present or Not");
			Reporter.log("Step 9 : Verify Move To New Event is present or Not"); 
			
				if (SeleniumFunc.IsElementPresent(registration.MoveToNewEvent) )
				{
					System.out.println("Success !! Move To New Event is present");
					Reporter.log("Success !! Move To New Event is present");
				}
				else
				{
					System.out.println("Failure !! Move To New Event is Not present");
					Reporter.log("Failure !! Move To New Event is Not present");
					
					AssertFailedCount++;

				}
				
				registration.MoveToNewEvent.click();
				Thread.sleep(4000);
				
			System.out.println("Step 10 : Verify Student Information under Student");
			Reporter.log("Step 10 : Verify Student Information under Student"); 	
			
				expectedtext = FirstName +" Testing <"+ EmailAddress + ">";
				expectedtext = expectedtext.toLowerCase();
				actualtext = registration.Studentinfo.getText().toLowerCase();
			
				if (expectedtext.trim().contains(actualtext.trim()))
				{
					System.out.println("Success !! Student Information is correct.");
					Reporter.log("Success !! Student Information is correct.");
		
				}
				else
				{
		
					System.out.println("Failure !! Student Information is Not correct" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !!  Student Information is Not correct" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						"Actual: " + "\n" +  actualtext);
			
					AssertFailedCount++;
	
				}	
				
			System.out.println("Step 11 : Selecting New Event & Adding Message & Move the student to a different event.");
			Reporter.log("Step 11 : Selecting New Event & Adding Message & Move the student to a different event."); 
				
				SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "event_id", 1);
				Thread.sleep(4000);
				
				registration.Message.sendKeys("This is just a test.Please ignore.");
				
				registration.SendMessage.click();
				Thread.sleep(4000);
				
			System.out.println("Step 12 : Verify Success Message is received or not");
			Reporter.log("Step 12 : Verify Success Message is received or not");	
				
				expectedtext = "The message was sent successfully.";
				actualtext = registration.SuccessMessageMoveTo.getText();
		
				if (expectedtext.trim().contains(actualtext.trim()))
				{
					System.out.println("Success !! Move to Event functionality is working properly.");
					Reporter.log("Success !! Move to Event functionality is working properly.");
	
				}
				else
				{
	
					System.out.println("Failure !! Move to Event functionality is NOT working properly." + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !!  Move to Event functionality is NOT working properly." + "\n" + "Expected : "  
					+ "\n" + expectedtext + "\n" + 
					"Actual: " + "\n" +  actualtext);
		
					AssertFailedCount++;

				}	
				
				
			
				try {
					/*
					//Go to instructor and delete the event
					Thread.sleep(4000);
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/" + EventID);
					Thread.sleep(5000);
				    
					instructoreventroster.EventDelete.click();
					Thread.sleep(5000);

					instructoreventroster.Confirm_EventDelete.click();
					
					Thread.sleep(5000);
					
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/" + EventID1);
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
					
					Thread.sleep(5000);*/
					SeleniumFunc.ToGoToUrl(URL1);
					Thread.sleep(5000);
					
					SeleniumFunc.ClickOnElement("linkText", "Cancel Event");
					
					Thread.sleep(5000);
					
					
					SeleniumFunc.ClickOnElement("linkText", "Unschedule and Remove Event");

					Thread.sleep(2000);

					SeleniumFunc.ClickOnElement("css", "body > div.stage > div.content > div.clear > a:nth-child(1) > span");
					
					Thread.sleep(2000);

					System.out.println("Event: "+EventID1+" Deleted Successfully!!");
					
					SeleniumFunc.ToGoToUrl(URL2);
					Thread.sleep(5000);
					
					SeleniumFunc.ClickOnElement("linkText", "Delete Event");
					
					Thread.sleep(2000);
				
					SeleniumFunc.ClickOnElement("linkText", "Delete Event");

					Thread.sleep(2000);

					System.out.println("Event: "+EventID2+" Deleted Successfully!!");
					
				} catch (Exception e) {
					
				}
				
			
			
			 // Marking Test Pass or Fail as per the value of AssertFailedCount variable
			 
		 	if(AssertFailedCount>0)	
			{
				
				//Marking this test as Failed
				
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
				
				Assert.fail();
			}
			
		}
		
		
		 /*
		 Test 1: 
		 * EM > Help Text on Event Detail Fields
		 */
		@Test
		private void HelpTextEventDetails() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 1 : Verify Send Message Registration Action"  + "\n" +"====");
			Reporter.log("====" + "\n" +
			 			  "Test 1 : Verify Send Message Registration Action"  + "\n" +  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);

			System.out.println("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");

				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);

			System.out.println("Step 5 : Go to Arizona boating course create event and verify help text");
			Reporter.log("Step 5 : Go to Arizona boating course create event and verify help text"); 	
		
				AgencyHomePage agencyhome= new AgencyHomePage(driver);
				SeleniumFunc.ClickOnElement("linkText", "Arizona GFD Boating & OHV Education");
				Thread.sleep(5000);

				agencyhome.ClickToSelectProgram();
				Thread.sleep(5000);

				agencyhome.ClickOnCreateEventLink();
				Thread.sleep(5000);
				
				instructorcreateevent.SelectProgram();
				Thread.sleep(4000);
				
				//Verify Material Request Field is present
				if(SeleniumFunc.IsElementPresent(instructorcreateevent.MaterialRequest))
				{
					System.out.println("Success !! Material Request is present");
					Reporter.log("Success !! Material Request is present");
				}
				else
				{
					System.out.println("Failure !! Material Request is NOT  present");
					Reporter.log("Failure !! Material Request is NOT  present");
					
					AssertFailedCount++;
				}
				
				//Verify Help Text is present
				if(SeleniumFunc.IsElementPresent(instructorcreateevent.HelpText))
				{
					System.out.println("Success !! Help Text is present");
					Reporter.log("Success !! Help Text  is present");
				}
				else
				{
					System.out.println("Failure !! Help Text is NOT present");
					Reporter.log("Failure !! Help Text is NOT present");
					
					AssertFailedCount++;
				}
				
				//Verify link is present for form
				
				if(SeleniumFunc.IsElementPresent(instructorcreateevent.LinkToForm))
				{
					System.out.println("Success !! Link to form is present");
					Reporter.log("Success !! Link to form is present");
					
					String Expected = "http://bit.ly/1zXuMg1";
					String Actual = instructorcreateevent.LinkToForm.getAttribute("href");
					
					if(Actual.equals(Expected))
					{
						System.out.println("Success !! Proper link is present");
						Reporter.log("Success !! Move to Event functionality is working properly.");
					}
					else
					{
	
							System.out.println("Failure !! Move to Event functionality is NOT working properly." + "\n" + "Expected : "  
															+ "\n" + Expected + "\n" + 
															"Actual: " + "\n" +  Actual);
							Reporter.log("Failure !!  Move to Event functionality is NOT working properly." + "\n" + "Expected : "  
															+ "\n" + Expected + "\n" + 
															"Actual: " + "\n" +  Actual);
		
							AssertFailedCount++;
					}	
				}
				else
				{
					System.out.println("Failure !! Link to form is NOT present");
					Reporter.log("Failure !! Link to form is NOT present");
					
					AssertFailedCount++;
				}
				
				
				
	 // Marking Test Pass or Fail as per the value of AssertFailedCount variable
	
		 	if(AssertFailedCount>0)	
			{
				
				//Marking this test as Failed
				
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
				
				Assert.fail();
			}
			
		}



		 /*
		 Test 1: 
		 * EM > Create Instructor Link Leads to Create Page
		 */
		@Test
		private void CreateNewInstructorRedirection() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 1 : EM > Create Instructor Link Leads to Create Page"  + "\n" +"====");
			Reporter.log("====" + "\n" +
			 			  "Test 1 : EM > Create Instructor Link Leads to Create Page"  + "\n" +  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);

			System.out.println("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");

				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);

			System.out.println("Step 5 : Go to any program and verify Create New Instructor leads to create page");
			Reporter.log("Step 5 : Go to any program and verify Create New Instructor leads to create page"); 	
		
				AgencyHomePage agencyhome= new AgencyHomePage(driver);
				SeleniumFunc.ClickOnElement("linkText", "Arizona GFD Boating & OHV Education");
				Thread.sleep(5000);

				agencyhome.ClickToSelectProgram();
				Thread.sleep(5000);

				agencyhome.ClickOnNewInstructorLink();
				Thread.sleep(5000);
				
			
				
				//Verify Create new instructor leads to create page
				String Expected = Constants.ApplicationURL_EM + "/agency/user/create";
				String Actual = driver.getCurrentUrl();
				
				if(Actual.equals(Expected))
				{
					System.out.println("Success !! Control is redirected to Create Instructor page");
					Reporter.log("Success !! Control is redirected to Create Instructor page");
				}
				else
				{
							System.out.println("Failure !! Control is NOT redirected to Create Instructor page." + "\n" + "Expected : "  
													+ "\n" + Expected + "\n" + 
														"Actual: " + "\n" +  Actual);
						Reporter.log("Failure !! Control is NOT redirected to Create Instructor page." + "\n" + "Expected : "  
													+ "\n" + Expected + "\n" + 
													"Actual: " + "\n" +  Actual);
	
						AssertFailedCount++;
				}	
			
				
				
	 // Marking Test Pass or Fail as per the value of AssertFailedCount variable
	
		 	if(AssertFailedCount>0)	
			{
				
				//Marking this test as Failed
				
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
				
				Assert.fail();
			}
			
		}


		
		/* Test 2: 
		 * Verify EM > Create User Dumps You on User's Page.
		*/ 
		@Test
		private void CreateUserRedirection() throws Exception
		{
			System.out.println("====" + "\n" +
					"Test 2 : Verify EM > Create User Dumps You on User's Page."  + "\n" +
		 			"====");
			Reporter.log("====" + "\n" +
		 			 "Test 2 : Verify EM > Create User Dumps You on User's Page."  + "\n" +
				 	 "====");	
		
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);

			
			System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
			Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");

				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);

			System.out.println("Step 5 : Go to any program and verify Create New Instructor leads to create page");
			Reporter.log("Step 5 : Go to any program and verify Create New Instructor leads to create page"); 	
	
				AgencyHomePage agencyhome= new AgencyHomePage(driver);
				SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
				Thread.sleep(5000);

				agencyhome.ClickToSelectProgram();
				Thread.sleep(5000);

				agencyhome.ClickOnNewInstructorLink();
				Thread.sleep(5000);
			
			System.out.println("Step 5 : Verify header of Create Users Page");
			Reporter.log("Step 5 : Verify header of Create Users Page"); 
						
				String Expected = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
				
				if(Expected.equals("Create Agency User"))
				{
					
					System.out.println("Success !! Header is present with correct text");
					Reporter.log("Success !! Header is present with correct text");
					
				}
				else
				{
			
					System.out.println("Failure !! Header is present but with incorrect text : "+ Expected);
					Reporter.log("Failure !! Header is present but with incorrect text : "+ Expected);
						
					AssertFailedCount++;
				}
			
				String username= "emuser" + JH.GenerateRandomNumber();
				String password ="clarion@123";
				

				//agency.CreateNewUser(username, password, password, "automation" , "user","Male" , "Admin Only", 
					//	"Northeast", "Adams", "Street 3", "city", "CO", "12345","kiran.hole@clariontechnologies.co.in");


				agency.CreateNewUser( username, password, password,  "automation" , "user","Male" , "Admin Only", 
									"Northeast", "Adams", "Street 3", "city", "CO", "12345","kalkomeytest@clariontechnologies.co.in");

		
				agency.SelectMonth("January");
				agency.SelectDay("1");
				agency.SelectYear("1989");
				agency.ClickOnSaveChangesButton();

				Thread.sleep(6000);
			
				//Verify Success Message is displayed
				if(SeleniumFunc.IsElementPresent(agency.SuccessMessage))
				{
					System.out.println("Success !! Success message is present");
					Reporter.log("Success !! Success message is present");
					
					 Expected = "This user was successfully created.";
					String Actual = agency.SuccessMessage.getText().trim();
					
					if(Actual.equals(Expected))
					{
						System.out.println("Success !! Proper Success Message is present. i.e. " + Actual);
						Reporter.log("Success !! Proper Success Message is present. i.e. " + Actual);
					}
					else
					{
	
							System.out.println("Failure !! Success Message is not proper." + "\n" + "Expected : "  
															+ "\n" + Expected + "\n" + 
															"Actual: " + "\n" +  Actual);
							Reporter.log("Failure !! Success Message is not proper." + "\n" + "Expected : "  
														+ "\n" + Expected + "\n" + 
														"Actual: " + "\n" +  Actual);
		
							AssertFailedCount++;
					}	
				}
				else
				{
					System.out.println("Failure !! Error while adding instructor.");
					Reporter.log("Failure !! Error while adding instructor.");
					
					AssertFailedCount++;
				}
				
				//SeleniumFunc.ClickOnElement("linkText", "Account");
				String userID = SeleniumFunc.GetElementText("css", "#account > div:nth-child(2) > dl:nth-child(2) > dd");
				//Verify Control is on User Details page
				//Expected = Constants.ApplicationURL_EM + "/agency/user/view/";
				Expected = Constants.ApplicationURL_EM + "/proctor/view/"+userID;
				String Actual = driver.getCurrentUrl();
				
				if(Actual.contains(Expected))
				{
					System.out.println("Success !! Control is on User Details page.");
					Reporter.log("Success !! Control is on User Details page.");
				}
				else
				{
							System.out.println("Failure !! Control is NOT on User Details page." + "\n" + "Expected : "  
													+ "\n" + Expected + "\n" + 
														"Actual: " + "\n" +  Actual);
						Reporter.log("Failure !! Control is NOT on User Details page." + "\n" + "Expected : "  
													+ "\n" + Expected + "\n" + 
													"Actual: " + "\n" +  Actual);
	
						AssertFailedCount++;
				}	
				
				Thread.sleep(4000);
				
				//Verify Birth Date Field
				Expected = "01/01/1989";
				Actual =  SeleniumFunc.GetElementText("css", "#profile > div:nth-child(3) > dl:nth-child(1) > dd").trim();
				
				if(Actual.contains(Expected))
				{
					System.out.println("Success !! DOB is displayed.");
					Reporter.log("Success !! DOB is displayed.");
				}
				else
				{
							System.out.println("Failure !! DOB is NOT displayed." + "\n" + "Expected : "  
													+ "\n" + Expected + "\n" + 
														"Actual: " + "\n" +  Actual);
						Reporter.log("Failure !! DOB is NOT displayed." + "\n" + "Expected : "  
								+ "\n" + Expected + "\n" + 
								"Actual: " + "\n" +  Actual);
	
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
		 * EM > Instructor Date of Birth Display
		*/ 
		@Test
		private void InstructorDOBDisplay() throws Exception
		{
			System.out.println("====" + "\n" +
					"Test 2 : Verify EM > Instructor Date of Birth Display"  + "\n" +
		 			"====");
			Reporter.log("====" + "\n" +
		 			 "Test 2 : Verify EM > Instructor Date of Birth Displays"  + "\n" +
				 	 "====");	
		
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);

			
			System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
			Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");

				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);

			System.out.println("Step 2 : Go to any program and verify Create New Instructor leads to create page");
			Reporter.log("Step 2 : Go to any program and verify Create New Instructor leads to create page"); 	
	
				AgencyHomePage agencyhome= new AgencyHomePage(driver);
				SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
				Thread.sleep(5000);

				agencyhome.ClickToSelectProgram();
				Thread.sleep(5000);

				agencyhome.ClickOnNewInstructorLink();
				Thread.sleep(5000);
			
			System.out.println("Step 3 : Verify header of Create Users Page");
			Reporter.log("Step 3 : Verify header of Create Users Page"); 
						
				String Expected = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
				
				if(Expected.equals("Create Agency User"))
				{
					
					System.out.println("Success !! Header is present with correct text");
					Reporter.log("Success !! Header is present with correct text");
					
				}
				else
				{
			
					System.out.println("Failure !! Header is present but with incorrect text : "+ Expected);
					Reporter.log("Failure !! Header is present but with incorrect text : "+ Expected);
						
					AssertFailedCount++;
				}
			
				String username= "emuser" + JH.GenerateRandomNumber();
				String password ="clarion@123";
				

				agency.CreateNewUser(username, password, password, "automation" , "user","Male" , "Admin Only", 
									"Northeast", "Adams", "Street 3", "city", "CO", "12345","kiran.hole@clariontechnologies.co.in");

				agency.CreateNewUser( username, password, password,  "automation" , "user","Male" , "Admin Only", 
									"Northeast", "Adams", "Street 3", "city", "CO", "12345","rohit.ware@clariontechnologies.co.in");

		
				agency.ClickOnSaveChangesButton();

				Thread.sleep(6000);
			
				//Verify Birth Date Field
				Expected = "Not Provided";
				String Actual =  SeleniumFunc.GetElementText("css", "#page > div.content.clearfix > div:nth-child(5) > div.span9 > div:nth-child(3) > div > table > tbody > tr:nth-child(7) > td:nth-child(2)").trim();
				
				if(Actual.contains(Expected))
				{
					System.out.println("Success !! Proper Text is displayed when no DOB is provided.");
					Reporter.log("Success !! Proper Text is displayed when no DOB is provided.");
				}
				else
				{
							System.out.println("Failure !! Incorrect text when no DOB is provided." + "\n" + "Expected : "  
													+ "\n" + Expected + "\n" + 
														"Actual: " + "\n" +  Actual);
						Reporter.log("Failure !! Incorrect text when no DOB is provided." + "\n" + "Expected : "  
								+ "\n" + Expected + "\n" + 
								"Actual: " + "\n" +  Actual);
	
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
		
		
		
			  /*
		 Test 4: 
		 * EM > Registraion complete > Verify presence of Click Here links to register another student 
		 */
		@Test
		private void VerifyPresenceOfClickHere() throws Exception
		{
			

			System.out.println("====" + "\n" +
						"Test 4 : Verify presence of Click Here links to register another student "  + "\n" +"====");
			Reporter.log("====" + "\n" +
			 			  "Test 4 : Verify presence of Click Here links to register another student "  + "\n" +  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
			InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
			InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
			InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
			AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
			//RegisterEdEventView eventview = new RegisterEdEventView(driver);
			//RegisterEdRegisterStudentPage registerstudent = new RegisterEdRegisterStudentPage(driver);
			EMStudentRegistrationPage registration = new EMStudentRegistrationPage(driver);
			AdminSearchEventPage adminsearchevent = new AdminSearchEventPage(driver);
			EMAddStudentPage addstud = new EMAddStudentPage(driver);
			
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
				Thread.sleep(2000);
				
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

			
			System.out.println("Step 7 : Login to EM and Enter Zip & select student to Registration");
			Reporter.log("Step 7 : Login to EM and Enter Zip  & select student to Registration"); 
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_CLInstructor_Username);
				login.EnterPassword(Constants.EM_CLInstructor_Password);

				login.ClickOnLogInButton();			
				Thread.sleep(5000);
				
				registration.EnterZip_RegisterAStudent("80921");
				
				registration.select_Miles();
				
				registration.Next_RegisterAStudent.click();
				Thread.sleep(4000);
				
				registration.FirstAddStudentBtn.click();
				Thread.sleep(4000);
				
			System.out.println("Step 8 : Register Student for an Event");
			Reporter.log("Step 8 : Register Student for an Event");
			
				String username="emstudent"+ JH.GenerateRandomNumber();
				String emailaddress = username + "@mailinator.com";
				addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
				addstud.ClickOnRegisterButton();
				Thread.sleep(6000);
				
			System.out.println("Step 9 : Verify Click here link to Register another student");
			Reporter.log("Step 9 : Verify Click here link to Register another student");
				
				if(SeleniumFunc.IsElementPresent(registration.ClickHereLink))
				{
					
					System.out.println("Success!! Click Here link is available");
					Reporter.log("Success!! Click Here link is available");
					
					registration.ClickHereLink.click();
					Thread.sleep(5000);
					
					expectedtext = "Create New Student for Colorado Hunter Education Course";
					actualtext = registration.Header_RegisterStudent.getText().toLowerCase();
					
					if(actualtext.contains(expectedtext.toLowerCase()))
					{
						System.out.println("Success!! Click Here linked to New Student Registration page.");
						Reporter.log("Success!! Click Here linked to New Student Registration page.");
					}
					else
					{
						System.out.println("Failure!! Click Here is Not linked to New Student Registration page.");
						Reporter.log("Failure!! Click Here is Not linked to New Student Registration page.");
					}
					
				}
				else
				{
					System.out.println("Failure!! Click Here link Not available");
					Reporter.log("Failure!! Click Here link is Not available");
					AssertFailedCount++;
				}
					

				
				try {
					//Go to instructor and delete the event
					

					//Going to Event Roster page and removing student and deleting  event 
					//so new event can be added with same test data
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventregistration/roster/" + EventID);
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
					System.out.println("Event: "+EventID+" Deleted Successfully!!");
					
					Thread.sleep(5000);
				} catch (Exception e) {
					
				}
				
			
			
			 // Marking Test Pass or Fail as per the value of AssertFailedCount variable
			 
		 	if(AssertFailedCount>0)	
			{
				
				//Marking this test as Failed
				
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
				
				Assert.fail();
			}
			
		}
		
		

		/* Test 5: 
		 * Verify successfully uploaded XSLS file is displayed
		 */ 
		@Test
		private void UploadFileXLSX_VerifyFile() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 5 : Verify Newly uploaded XSLS file is displayed"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 5 : Verify Newly uploaded XSLS file is displayed"  + "\n" +
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
				SeleniumFunc.ClickOnElement("name", "file");
				/*
				File file = new File("src/test/resources/SampleFileUpload.xlsx");
				managefiles.SendPath(file.getAbsolutePath());*/
				
				StringSelection sel = new StringSelection("SampleFileUpload");
				 //Copy to clipboard
				 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel,null);
				
				// Create object of Robot class
				 Robot robot = new Robot();
				 Thread.sleep(4000);
				      
				  // Press Enter
				// robot.keyPress(KeyEvent.VK_ENTER);
				 
				// Release Enter
				 //robot.keyRelease(KeyEvent.VK_ENTER);
				 
				  // Press CTRL+V
				 robot.keyPress(KeyEvent.VK_CONTROL);
				 robot.keyPress(KeyEvent.VK_V);
				 
				// Release CTRL+V
				 robot.keyRelease(KeyEvent.VK_CONTROL);
				 robot.keyRelease(KeyEvent.VK_V);
				 Thread.sleep(1000);
				        
				         //Press Enter 
				 robot.keyPress(KeyEvent.VK_ENTER);
				 robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(8000);
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
					
					System.out.println("Success !! Success message displayed, File upload functionality is working properly. i.e. " +expectedtext);
					Reporter.log("Success !! Success message displayed, File upload functionality is working properly. i.e. " +expectedtext);
				
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
		
				

				//Verify Uploaded File
				expectedtext = "SampleFileUpload.txt";			
				actualtext = managefiles.CheckUploadedFile.getText().trim();
			
				if(actualtext.equalsIgnoreCase(expectedtext))
				{
					
					System.out.println("Success !! File checked :  " +expectedtext);
					Reporter.log("Success !! File checked : " +expectedtext);
				
				}
				else
				{
				
					System.out.println("Failure !! File Not Matched :" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! File Not Matched :" + "\n" + "Expected : "  
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
		
		
		/* Test 6: 
		 * Verify Description of successfully uploaded XSLS file on register-ed Event View page
		 */ 
		@Test
		private void UploadFileXLSX_VerifyDescription() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 6 :  Verify Description of successfully uploaded XSLS file"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 6 :  Verify Description of successfully uploaded XSLS file"  + "\n" +
					 	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			AgencyHomePage agencyhome= new AgencyHomePage(driver);
			ManageProgramFilesPage managefiles = new ManageProgramFilesPage(driver);
			InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
			InstructorEventRosterPage roster=new InstructorEventRosterPage(driver);
			RegisterEdEventView RegED= new RegisterEdEventView(driver);
			InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
			InstructorHomePage instructorhome = new InstructorHomePage(driver);
			
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
				SeleniumFunc.ClickOnElement("name", "file");
				/*
				File file = new File("src/test/resources/SampleFileUpload.xlsx");
				managefiles.SendPath(file.getAbsolutePath());*/
				
				 StringSelection sel = new StringSelection("SampleFileUpload.txt");
				 //Copy to clipboard
				 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel,null);
				
				// Create object of Robot class
				 Robot robot = new Robot();
				 Thread.sleep(1000);
				      
				  // Press Enter
				 robot.keyPress(KeyEvent.VK_ENTER);
				 
				// Release Enter
				 robot.keyRelease(KeyEvent.VK_ENTER);
				 
				  // Press CTRL+V
				 robot.keyPress(KeyEvent.VK_CONTROL);
				 robot.keyPress(KeyEvent.VK_V);
				 
				// Release CTRL+V
				 robot.keyRelease(KeyEvent.VK_CONTROL);
				 robot.keyRelease(KeyEvent.VK_V);
				 Thread.sleep(1000);
				        
				         //Press Enter 
				 robot.keyPress(KeyEvent.VK_ENTER);
				 robot.keyRelease(KeyEvent.VK_ENTER);

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
					
					System.out.println("Success !! Success message displayed, File upload functionality is working properly. i.e. " +expectedtext);
					Reporter.log("Success !! Success message displayed, File upload functionality is working properly. i.e. " +expectedtext);
				
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
			

				//Verify Uploaded File
				expectedtext = "Automation Verification";
				
				actualtext = managefiles.CheckUploadedFileDescription.getAttribute("value");
			
				if(actualtext.contains(expectedtext.trim()))
				{
					
					System.out.println("Success !! File Description matched :  " +expectedtext);
					Reporter.log("Success !! File Description matched : " +expectedtext);
				
				}
				else
				{
				
					System.out.println("Failure !! File Description Not matched :" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! File Description Not matched :" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				
				}	
				
				String FilepageURL = driver.getCurrentUrl();

				System.out.println("Step 5 : Create Event to verify Description");
				Reporter.log("Step 5 : Create Event to verify Description"); 
					
				 	agencyhome.ClickOnCreateEventLink();
				 	Thread.sleep(4000);
				 	
					instructorcreateevent.SelectProgram2();
					Thread.sleep(4000);
					
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
					
					
					// Activate Event
					managefiles.ActiveEvent.click();
					String EventId =roster.EventId.getText().trim();
					Thread.sleep(5000);
					String URL = driver.getCurrentUrl();
					System.out.println("Url :" +URL);

				System.out.println("Step 6 : Verify Description on register-ed Event View page." );
				Reporter.log("Step 6 : Verify Description on register-ed Event View page.");
				
					roster.ReturnToRosterEventAdmin.click();
					Thread.sleep(5000);
					
					SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd+"/events/view/"+EventId);
					Thread.sleep(5000);
					
					actualtext = RegED.CheckDescription.getText().trim();
					
					if(actualtext.contains(expectedtext.trim()))
					{
						
						System.out.println("Success !! File Description matched on register-ed Event View page:  " +expectedtext);
						Reporter.log("Success !! File Description matched on register-ed Event View page: " +expectedtext);
					
					}
					else
					{
					
						System.out.println("Failure !! File Description Not matched on register-ed Event View page:" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! File Description Not matched on register-ed Event View page:" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					
					}	
					
				System.out.println("Step 7 : Navigate to Manage Program Files page & Update Description");
				Reporter.log("Step 7 : Navigate to Manage Program Files page & Update Description");
				
					driver.get(FilepageURL);
					Thread.sleep(5000);
					managefiles.EditDescription("Edit Description");
					managefiles.UpdateDescription();
					Thread.sleep(5000);

					//Verify Description is updated
					expectedtext = "Edit Description";
					
					actualtext = managefiles.EditDescription.getAttribute("value");
				
					if(actualtext.equalsIgnoreCase(expectedtext))
					{
						
						System.out.println("Success !! Description Updated on Manage Program Files page. i.e. " +expectedtext);
						Reporter.log("Success !! Description Updated on Manage Program Files. i.e. " +expectedtext);
					
					}
					else
					{
					
						System.out.println("Failure !! Description NOT Updated on Manage Program Files." + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! Description NOT Updated on Manage Program Files." + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					
					}	
						
				System.out.println("Step 8 : Navigate to register-ed Event View page & Verify Updated Description");
				Reporter.log("Step 8 : Navigate to register-ed Event View page & Verify Updated Description");
				
					SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd+"/events/view/"+EventId);
					Thread.sleep(5000);
					
					actualtext = RegED.CheckDescription.getText().trim();
					
					if(actualtext.contains(expectedtext.trim()))
					{
						
						System.out.println("Success !! File Description Updated on register-ed Event View page:  " +expectedtext);
						Reporter.log("Success !! File Description Updated on register-ed Event View page: " +expectedtext);
					
					}
					else
					{
					
						System.out.println("Failure !! File Description Not Updated on register-ed Event View page:" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! File Description Not Updated on register-ed Event View page:" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					
					}	
					
							
					driver.get(FilepageURL);
					Thread.sleep(5000);				
					
					//Delete File
					managefiles.ClickOnDelete();
					Thread.sleep(5000);	
					
					 try {
						  /*
					     login.ClickOnLogoutButton();
					     Thread.sleep(5000);

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

					     roster.EventDelete.click();
					     Thread.sleep(5000);

					     roster.Confirm_EventDelete.click();
					     
					     Thread.sleep(5000);*/
						 	SeleniumFunc.ToGoToUrl(URL);
							Thread.sleep(5000);
							
							SeleniumFunc.ClickOnElement("linkText", "Delete Event");
							
							Thread.sleep(2000);
						
							SeleniumFunc.ClickOnElement("linkText", "Delete Event");

							Thread.sleep(2000);

							System.out.println("Event: "+EventId+" Deleted Successfully!!");

						 
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
		 * EM > Null Values for Date Windows Show Up Correctly
		 */ 
		@Test
		private void NullValueDateWindow() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 7 :  Verify Description of successfully uploaded XSLS file"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 7 :  Verify Description of successfully uploaded XSLS file"  + "\n" +
					 	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			AgencyHomePage agencyhome= new AgencyHomePage(driver);
			ManageProgramFilesPage managefiles = new ManageProgramFilesPage(driver);
			InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
			InstructorEventRosterPage roster=new InstructorEventRosterPage(driver);
			InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
			InstructorHomePage instructorhome = new InstructorHomePage(driver);
			InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
			
			System.out.println("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);

		
			System.out.println("Step 2 : Select agency from agency listing");
			Reporter.log("Step 2 : Select agency from agency listing"); 
								
				((JavascriptExecutor)driver).executeScript("scroll(1022,1060)");						
				SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
				Thread.sleep(5000);

						
			System.out.println("Step 3 : Select a program and Create Event with Cancellation & Visibility Windows set to Never");
			Reporter.log("Step 3 : Select a program and Create Event with Cancellation & Visibility Windows set to Never"); 
							
					
			  	agencyhome.ClickToSelectProgram();
				Thread.sleep(5000);

					
				 agencyhome.ClickOnCreateEventLink();
				 Thread.sleep(4000);
				 	
				instructorcreateevent.SelectProgram();
				Thread.sleep(4000);
					
				instructorcreateevent.Location.clear();
				instructorcreateevent.Location.sendKeys("Clarion156353365");
				Thread.sleep(5000);
				instructorcreateevent.Location_FirstChoice.click();
				Thread.sleep(2000);
					
				instructorcreateevent.SelectStartDate("6", "30", "PM");
				instructorcreateevent.SelectEndDate("7", "30", "PM");
				
				
				instructorcreateevent.AddEventDate.click();
				Thread.sleep(2000);
				((JavascriptExecutor)driver).executeScript("scroll(1022,1060)");		
				instructorcreateevent.EventVisibleLink.click();
				Thread.sleep(3000);			
				instructorcreateevent.EventVisibleSetDoNotDisplay.click();
				Thread.sleep(3000);
				
				instructorcreateevent.EventCancelLink.click();
				Thread.sleep(3000);
				instructorcreateevent.EventCancelSetDoNotCancel.click();
				Thread.sleep(3000);
				
				instructorcreateevent.CreateEvent.click();
				Thread.sleep(5000);
				String URL = driver.getCurrentUrl();
				System.out.println("Url :" +URL);
				
				String EventID = instructoreventroster.EventId.getText().trim();
				// Activate Event
				managefiles.ActiveEvent.click();
				Thread.sleep(5000);

			System.out.println("Step 4 : Verify Cancellation & Visibility Windows on Event Administration Page");
			Reporter.log("Step 4 : Verify Cancellation & Visibility Windows on Event Administration Page");	
				
				String actualtext = SeleniumFunc.GetElementText("css", "#page > div.content.clearfix div:nth-child(14) > div.span9").trim();
				String expectedtext = "This event does not appear on the public calendar.";
				
				if(actualtext.contains(expectedtext.trim()))
				{
					System.out.println("Success!! Visibility Windows instruction is correct i.e. : "+actualtext);
					Reporter.log("Success!! Visibility Windows instruction is correct i.e. : "+actualtext);
				}
				else
				{
					System.out.println("Failure !! Visibility Windows instruction is Incorrect:" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! Visibility Windows instruction is Incorrect:" + "\n" + "Expected : "  
					+ "\n" + expectedtext + "\n" + 
					 "Actual: " + "\n" +  actualtext);
			
					AssertFailedCount++;
				}
				
				actualtext = SeleniumFunc.GetElementText("css", "#page > div.content.clearfix div:nth-child(16) > div.span9").trim();
				expectedtext = "Students cannot cancel registrations for this event.";
				
				if(actualtext.contains(expectedtext.trim()))
				{
					System.out.println("Success!! Cancellation Windows instruction is correct i.e. : "+actualtext);
					Reporter.log("Success!! Cancellation Windows instruction is correct i.e. : "+actualtext);
				}
				else
				{
					System.out.println("Failure !! Cancellation Windows instruction is Incorrect:" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! Cancellation Windows instruction is Incorrect:" + "\n" + "Expected : "  
					+ "\n" + expectedtext + "\n" + 
					 "Actual: " + "\n" +  actualtext);
			
					AssertFailedCount++;
				}
				
				try {
					/*  
					   login.ClickOnLogoutButton();
					   Thread.sleep(5000);

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

					   roster.EventDelete.click();
					   Thread.sleep(5000);

					   roster.Confirm_EventDelete.click();
					     
					   Thread.sleep(5000);*/
					SeleniumFunc.ToGoToUrl(URL);
					Thread.sleep(5000);
					
					SeleniumFunc.ClickOnElement("linkText", "Delete Event");
					
					Thread.sleep(2000);
				
					SeleniumFunc.ClickOnElement("linkText", "Delete Event");

					Thread.sleep(2000);

					System.out.println("Event: "+EventID+" Deleted Successfully!!");
					
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
		 * EM > Back Button Works for Searches
		 */ 
		@Test
		private void Back_Search() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 7 :  Verify Back Button Functionality Works for Searches"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 7 :  Verify Back Button Functionality Works for Searches"  + "\n" +
					 	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			AgencyHomePage agencyhome= new AgencyHomePage(driver);
			InstructorEventRosterPage roster=new InstructorEventRosterPage(driver);
			InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
			InstructorHomePage instructorhome = new InstructorHomePage(driver);
			InstructorSearchPage search=new InstructorSearchPage(driver);
			
			System.out.println("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);

		
			System.out.println("Step 2 : Select agency from agency listing");
			Reporter.log("Step 2 : Select agency from agency listing"); 
								
			((JavascriptExecutor)driver).executeScript("scroll(1022,1060)");						
				SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
				Thread.sleep(5000);

						
			System.out.println("Step 3 : Select a program and Search");
			Reporter.log("Step 3 : Select a program and Search"); 
							
					
			  	agencyhome.ClickToSelectProgram();
				Thread.sleep(5000);
			
				agencyhome.EventSearch.click();
				Thread.sleep(5000);
				
			System.out.println("Step 4 : Search by Search Type & Data");
			Reporter.log("Step 4 : Search by Search Type & Data"); 
				
				search.SelectSearchTypeByVisibleText("Upcoming");
				Thread.sleep(5000);
				SeleniumFunc.EnterValueInTextbox("css", "#search-type-upcoming", "4000");
				search.ClickOnSearchButton();
				Thread.sleep(5000);
				
			System.out.println("Step 5 : Click on first Event & Navigate Back to Search page");
			Reporter.log("Step 5 : Click on first Event & Navigate Back to Search page"); 	
			
				SeleniumFunc.ClickOnElement("css", "#page > div.content.clearfix div:nth-child(5) > table > tbody > tr:nth-child(1) > td:nth-child(2) > a");
				Thread.sleep(5000);
				
				driver.navigate().back();
				Thread.sleep(5000);
			
			System.out.println("Step 6 : Verify Both Search Type & Data Same as Input");
			Reporter.log("Step 6 : Verify Both Search Type & Data are Same as Input"); 	
			
				//String actualtext = SeleniumFunc.GetSelectedValueFromDropdownList("css", "div.btn-group.bootstrap-select.span3.col-ignore > button > span.filter-option.pull-left");
				String actualtext=SeleniumFunc.GetElementText("css", "div.btn-group.bootstrap-select.span3.col-ignore > button > span.filter-option.pull-left");
			String actualtext2 = SeleniumFunc.GetAttributeValue("css", "#search-type-upcoming", "value");
				
				if(actualtext.equalsIgnoreCase("Upcoming") && actualtext2.equalsIgnoreCase("4000"))
				{
					System.out.println("Success!! Both Search Type & Data are correct i.e. Search Type: "+actualtext+" Data Input: "+actualtext2);
					Reporter.log("Success!! Both Search Type & Data are correct i.e. Search Type: "+actualtext+" Data Input: "+actualtext2);
				}
				else
				{
					System.out.println("Failure!! Either Search Type or Data are correct i.e. Search Type: "+actualtext+" Data Input: "+actualtext2);
					Reporter.log("Failure!! Either Search Type or Data are correct i.e. Search Type: "+actualtext+" Data Input: "+actualtext2);
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
		
		
		
		/* Test 1: 
		 * Verify Student can see Location's Room
		*/ 
		@Test
		private void LocationRoomView() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 1 : Verify Student can see Location's Room"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 1 : Verify Student can see Location's Room"  + "\n" +
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
				instructorlocations.SelectProgram(3);	
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
				System.out.println("Url :" +URL);
					
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

				
			System.out.println("Step 7 : Go to Register-Ed and verify Student can see location's Room");
			Reporter.log("Step 7 : Go to Register-Ed and verify Student can see location's Room"); 
			
				
				SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);
				Thread.sleep(5000);
			
				//Verify Title
				expectedtext = "Room: 5";
				actualtext = eventview.EventLocation.getText().trim();
			
				if(actualtext.contains(expectedtext))
				{
					
					System.out.println("Success !! Student can see location's Room");
					Reporter.log("Success !! Student can see location's Room");
				
				}
				else
				{
				
					System.out.println("Failure !! Student can NOT see location's Room" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! Student can NOT see location's Room" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				
				}
							
						
				try {
					/*
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

					instructoreventroster.EventDelete.click();
					Thread.sleep(5000);

					instructoreventroster.Confirm_EventDelete.click();
					
					Thread.sleep(5000);*/
					SeleniumFunc.ToGoToUrl(URL);
					Thread.sleep(5000);
					
					SeleniumFunc.ClickOnElement("linkText", "Delete Event");
					
					Thread.sleep(2000);
				
					SeleniumFunc.ClickOnElement("linkText", "Delete Event");

					Thread.sleep(2000);

					System.out.println("Event: "+EventID+" Deleted Successfully!!");
					
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
		
		
		
		/* Test 1: 
		 * Verify Event Status History is Correct for event with admin approval
		*/ 
		@Test
		private void EventStatusHistoryAdminApproval() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 1 :  Verify Event Status History is Correct for event with admin approval"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 1 :  Verify Event Status History is Correct for event with admin approval"  + "\n" +
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
			//	instructorschedule.DeleteAllEvents();
				 
				
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
				System.out.println("Url :" +URL);
				
					
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

				searchevent.SelectSearchTypeByVisibleText("ID");
				searchevent.EnterSearchData(EventID);
				searchevent.ClickOnSearchButton();
				Thread.sleep(5000);

				searchevent.ClickOnSelectEventCheckBox();
				searchevent.SelectEventActionByVisibleText("Set Status: Active");
				searchevent.ClickOnGoButton();
				Thread.sleep(5000);
				
			System.out.println("Step 7 : Go to Event Admin Page and verify Event Status History (For event which needs admin approval)");
			Reporter.log("Step 7 : Go to Event Admin Page and verify Event Status History (For event which needs admin approval)"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/admin/" + EventID);
				Thread.sleep(5000);
			
				//Verify Event history
			
				if(SeleniumFunc.GetElementCount("css", "#event_data_section + div table tr")== 3)
				{	
					System.out.println("Success !! Event history is displayed properly.");
					Reporter.log("Success !! Event history is displayed properly.");			
				}
				else
				{
					
					System.out.println("Failure !! Event histroy is NOT proper.");
					Reporter.log("Failure !! Event histroy is NOT proper.");
					
					AssertFailedCount++;
				
				}
				
			
							
						
				try {
					login.ClickOnLogoutButton();
					Thread.sleep(5000);

					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
					login.EnterUsername(Constants.EM_Instructor_Username);
					login.EnterPassword(Constants.EM_Instructor_Password);
					login.ClickOnLogInButton();			
					Thread.sleep(5000);

					//Going to Event Roster page and removing student and deleting  event 
					//so new event can be added with same test data
					/*instructorhome.VisitHomePage.click();
					Thread.sleep(5000);

					instructorhome.Action_MyEventSchedule.click();
					Thread.sleep(5000);

					//instructorhome.GoToNextMonth();
					instructorschedule.FirstEventName.click();
					Thread.sleep(5000);

					instructoreventroster.EventDelete.click();
					Thread.sleep(5000);

					instructoreventroster.Confirm_EventDelete.click();*/
					
					SeleniumFunc.ToGoToUrl(URL);
					Thread.sleep(5000);
					
					SeleniumFunc.ClickOnElement("linkText", "Delete Event");
					
					Thread.sleep(2000);
				
					SeleniumFunc.ClickOnElement("linkText", "Delete Event");

					Thread.sleep(2000);

					System.out.println("Event: "+EventID+" Deleted Successfully!!");
					
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
		
		
		/* Test 1: 
		 * Verify Event Status History is Correct for event without admin approval
		*/ 
		@Test
		private void EventStatusHistoryNoAdminApproval() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 1 :  Verify Event Status History is Correct for event with admin approval"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 1 :  Verify Event Status History is Correct for event with admin approval"  + "\n" +
					 	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
			InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
			InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
			InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
			InstructorHomePage instructorhome = new InstructorHomePage(driver);
			
			System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_MOInstructor_Username);
				login.EnterPassword(Constants.EM_MOInstructor_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);

				//Navigate to 'My Event Schedule' page and delete all Events
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
				//instructorschedule.DeleteAllEvents();
				 
				
			System.out.println("Step 2 : Navigate to Create Event page via Location page ");
			Reporter.log("Step 2 : Navigate to Create Event page via Location page  "); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
				instructorlocations.SelectProgram(1);	
				Thread.sleep(5000);

				instructorlocations.ScheduleAnEvent.click();	
				Thread.sleep(5000);
					
			
			System.out.println("Step 3 : On Create Event page , entering all required details and click on 'Create Event' button");
			Reporter.log("Step 3 : On Create Event page , entering all required detailsb and click on 'Create Event' button"); 
			
				instructorcreateevent.SelectProgram();
				Thread.sleep(2000);
					
				instructorcreateevent.Location.clear();
				instructorcreateevent.Location.sendKeys("AMERICAN LEGION HALL");
				Thread.sleep(8000);
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
				System.out.println("Url :" +URL);

					
			System.out.println("Step 4 : Verifying whether Event is added successfully or not");
			Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
						
				//Updated due to change in functionality
				//https://kalkomey.tpondemand.com/entity/14661
				//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
				//Thread.sleep(5000);

				//Verifying Event Time
				String expectedtext = "6:30pm - 7:30pm";
				String actualtext = SeleniumFunc.GetElementText("css", ".table.table-condensed.table-flush-left.table-headless.table-mobile>tbody>tr>td");
			
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
				expectedtext = "Missouri Hunter Education Skills Session";
				actualtext = SeleniumFunc.GetElementText("css", ".mvn.h3");
			
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

				//String EventID = instructoreventroster.EventId.getText().trim();
				String EventID = SeleniumFunc.GetElementText("css", "div.span8.event-info.tooltip-container > p");

				SeleniumFunc.ClickOnElement("linkText", "Log Out");
				Thread.sleep(5000);

				
			System.out.println("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
			
				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);
				
			System.out.println("Step 7 : Go to Event Admin Page and verify Event Status History (For event which needs admin approval)");
			Reporter.log("Step 7 : Go to Event Admin Page and verify Event Status History (For event which needs admin approval)"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/admin/" + EventID);
				Thread.sleep(5000);
			
				//Verify Event history
			
				if(SeleniumFunc.GetElementCount("css", "#event_data_section + div table tr")== 2)
				{	
					System.out.println("Success !! Event history is displayed properly.");
					Reporter.log("Success !! Event history is displayed properly.");			
				}
				else
				{
					
					System.out.println("Failure !! Event histroy is NOT proper.");
					Reporter.log("Failure !! Event histroy is NOT proper.");
					
					AssertFailedCount++;
				
				}
				
			
							
						
				try {
					/*
					//login.ClickOnLogoutButton();
				//	Thread.sleep(5000);

					//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
					login.EnterUsername(Constants.EM_MOInstructor_Username);
					login.EnterPassword(Constants.EM_MOInstructor_Password);
					login.ClickOnLogInButton();			
					Thread.sleep(5000);
					*/
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

					instructoreventroster.Confirm_EventDelete.click();*/
					//SeleniumFunc.ToGoToUrl(URL);
					//Thread.sleep(5000);
					
					SeleniumFunc.ClickOnElement("linkText", "Delete Event");
					
					Thread.sleep(2000);
				
					SeleniumFunc.ClickOnElement("linkText", "Delete Event");

					Thread.sleep(2000);

					System.out.println("Event: "+EventID+" Deleted Successfully!!");
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
		
		
		
		/* Test 1: 
		 * Verify Event Status History is Correct for event with admin approval
		*/ 
		@Test
		private void EditLocation_Instructor() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 1 :  Verify Event Status History is Correct for event with admin approval"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 1 :  Verify Event Status History is Correct for event with admin approval"  + "\n" +
					 	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
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
				instructorlocations.SelectProgram(3);	
				Thread.sleep(5000);
				
				//instructorlocations.SelectSearchTypeByName();
				instructorlocations.EnterSearchData("Clarion303292481");
				instructorlocations.ClickOnSearchButton();
				
				Thread.sleep(8000);
				
			System.out.println("Step 3 : Click on More Information and verify details");
			Reporter.log("Step 3 : Click on More Information and verify details"); 		
				
			
				instructorlocations.MoreInformation.click();
				Thread.sleep(4000);
				
				//Verify Location Details are present
				
				//Name
				if(SeleniumFunc.IsElementPresent(instructorlocations.LocationTitle))
				{
					System.out.println("Success !! Location Name is present");
					Reporter.log("Success !! Location Name is present"); 
				}
				else
				{
					System.out.println("Failure !! Location Name is missing");
					Reporter.log("Failure !! Location Name is missing");			
					AssertFailedCount++;
				}
				
				//Description
				if(SeleniumFunc.IsElementPresent(instructorlocations.LocationDetails))
				{
					System.out.println("Success !! Location Details are present");
					Reporter.log("Success !! Location Details are present"); 
				}
				else
				{
					System.out.println("Failure !! Location Details are missing");
					Reporter.log("Failure !! Location Details are missing");			
					AssertFailedCount++;
				}
				
				//Map
				/*if(SeleniumFunc.IsElementPresent(instructorlocations.Map))
				{
					System.out.println("Success !! Map is present");
					Reporter.log("Success !! Map is present"); 
				}
				else
				{
					System.out.println("Failure !! Map is missing");
					Reporter.log("Failure !! Map is missing");			
					AssertFailedCount++;
				}*/
				
			System.out.println("Step 4 : Edit location details and save");
			Reporter.log("Step 4 : Edit location details and save"); 			
			
				SeleniumFunc.ClickOnElement("xpath", ".//*[@id='page']/div[2]/div[2]/div[2]/div[4]/div[1]/a");
				Thread.sleep(4000);
				
				String Address = instructorlocations.Address.getAttribute("value");
				
				instructorlocations.Address.clear();
				instructorlocations.Address.sendKeys("KeTesting");
				
				instructorlocations.SaveChanges.click();
				Thread.sleep(4000);
				
				//instructorlocations.SelectSearchTypeByName();
				/*instructorlocations.EnterSearchData("Clarion303292481");
				instructorlocations.ClickOnSearchButton();*/
			
			System.out.println("Step 5 : Verify details updated");
			Reporter.log("Step 5 :  Verify details updated"); 	
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
				instructorlocations.SelectProgram(3);	
				Thread.sleep(5000);
				
				//instructorlocations.SelectSearchTypeByName();
				instructorlocations.EnterSearchData("Clarion303292481");
				
				instructorlocations.ClickOnSearchButton();
				Thread.sleep(14000);
				instructorlocations.MoreInformation.click();
				Thread.sleep(4000);
				
				SeleniumFunc.ClickOnElement("css", "#page div:nth-of-type(4) a.btn");
				Thread.sleep(4000);
				
				String Actual = instructorlocations.Address.getAttribute("value");
				String Expected = "KeTesting";
				
				if(Actual.equals(Expected))
				{	
					System.out.println("Success !! Edit Location is working properly.");
					Reporter.log("Success !! Edit Location is working properly.");			
				}
				else
				{
					
					System.out.println("Failure !! Edit Location is NOT working properly." + "\n" + "Expected : "  
										+ "\n" + Expected + "\n" + 
										 "Actual: " + "\n" +  Actual);
					Reporter.log("Failure !! Edit Location is NOT working properly." + "\n" + "Expected : "  
							+ "\n" + Expected + "\n" + 
							 "Actual: " + "\n" +  Actual);
					
					AssertFailedCount++;
				
				}
				
				instructorlocations.Address.clear();

				instructorlocations.Address.sendKeys(Address);
				
				instructorlocations.SaveChanges.click();
				Thread.sleep(4000);
				
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
		
		
		/* Test : 
		 * Verify Instructor's Name & Email on Event Administration page
		*/ 
		@Test
		private void VerifyInstructorNameAndEmail() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test  : Verify Instructor's name & Email on Event Administration page"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test  : Verify Instructor's name & Email on Event Administration page"  + "\n" +
					 	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
			InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
			InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
			InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
			InstructorHomePage instructorhome = new InstructorHomePage(driver);
			ManageProgramFilesPage managefiles = new ManageProgramFilesPage(driver);
			EMSendAMessagePage message=new EMSendAMessagePage(driver);
			InstructorEventRosterPage roster=new InstructorEventRosterPage(driver);

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
					
			
			System.out.println("Step 3 : On Create Event page , entering all required details and click on 'Create Event' & 'Activate Event' button");
			Reporter.log("Step 3 : On Create Event page , entering all required detailsb and click on 'Create Event' & 'Activate Event' button"); 
			
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
				String EventId =roster.EventId.getText().trim();
				
				String URL = driver.getCurrentUrl();
				System.out.println("Url :" +URL);
				
			System.out.println("Step 4: Hover & Verify Instructor name is correct");
			Reporter.log("Step 4: Hover & Verify Instructor name is correct");
			
				SeleniumFunc.ToMouseHover("css", "#page div:nth-of-type(7) div:nth-of-type(1) a");
				Thread.sleep(4000);
				if( SeleniumFunc.IsElementPresent("css","div [class='tooltip fade top in']"))
				{
					String ActualText = SeleniumFunc.GetElementText("css", "div [class='tooltip fade top in']").trim();
					String ExpectedText = "Act as Automation";
					Thread.sleep(4000);
					if (ActualText.equals(ExpectedText.trim()))
					{	
						System.out.println("Success !! Instructor Name is correct: "+ActualText);
						Reporter.log("Success!! Instructor Name is correct: "+ActualText);			
					}
					else
					{
						
						System.out.println("Failure !!Instructor Name is Not correct." + "\n" + "Expected : "  
											+ "\n" + ExpectedText + "\n" + 
											 "Actual: " + "\n" +  ActualText);
						Reporter.log("Failure !!Instructor Name is Not correct." + "\n" + "Expected : "  
								+ "\n" + ExpectedText + "\n" + 
								 "Actual: " + "\n" +  ActualText);
						
						AssertFailedCount++;
					
					}
				}

				managefiles.ActiveEvent.click();
				Thread.sleep(5000);
				
			System.out.println("Step 5: Hover & Verify Instructor Email is correct");
			Reporter.log("Step 5: Hover & Verify Instructor Email is correct");
				
					SeleniumFunc.ToMouseHover("css", "#send-message-link");
					Thread.sleep(4000);
					if( SeleniumFunc.IsElementPresent("css","div [class='tooltip fade top in']"))
					{
						String ActualText = SeleniumFunc.GetElementText("css", "div [class='tooltip fade top in']").trim();
						String ExpectedText = "Send Automation a Message";
						Thread.sleep(4000);
						if (ActualText.equals(ExpectedText.trim()))
						{	
							System.out.println("Success !! Instructor Email is correct: "+ActualText);
							Reporter.log("Success!! Instructor Email is correct: "+ActualText);			
						}
						else
						{
							
							System.out.println("Failure !!Instructor Email is Not correct." + "\n" + "Expected : "  
												+ "\n" + ExpectedText + "\n" + 
												 "Actual: " + "\n" +  ActualText);
							Reporter.log("Failure !!Instructor Email is Not correct." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual: " + "\n" +  ActualText);
							
							AssertFailedCount++;
						
						}
					}
						
				System.out.println("Step 6: Click on Instructor & Verify 'Send a Message' popup will open");		
				Reporter.log("Step 6: Click on Instructor & Verify 'Send a Message' popup will open");
				
					SeleniumFunc.ClickOnElement("css", "#send-message-link");
					Thread.sleep(5000);
					if(message.SendUsAnEmailPopUp.getAttribute("style").contains("display: block"))
					{
					String actualText = message.Sincere_InstructorName.getText();
					String expected = "Automation Testing";
					
					  if (actualText.contains(expected.trim()))
					  {	
						System.out.println("Success !! Instructor Name is correct: "+actualText);
						Reporter.log("Success!! Instructor Name is correct: "+actualText);			
					  }
					  else
					  {
						
						System.out.println("Failure !!Instructor Name is Not correct." + "\n" + "Expected : "  
											+ "\n" + expected + "\n" + 
											 "Actual: " + "\n" +  actualText);
						Reporter.log("Failure !!Instructor Name is Not correct." + "\n" + "Expected : "  
								+ "\n" + expected + "\n" + 
								 "Actual: " + "\n" +  actualText);
						
						AssertFailedCount++;
					
					  }
					  
					   actualText = message.Email.getText();
					   expected = "rohit.ware@clariontechnologies.co.in";
						
					   if (actualText.contains(expected.trim()))
						{	
							System.out.println("Success !! Instructor Email is correct: "+actualText);
							Reporter.log("Success!! Instructor Email is correct: "+actualText);			
						}
						else
						{
							
							System.out.println("Failure !!Instructor Email is Not correct." + "\n" + "Expected : "  
												+ "\n" + expected + "\n" + 
												 "Actual: " + "\n" +  actualText);
							Reporter.log("Failure !!Instructor Email is Not correct." + "\n" + "Expected : "  
									+ "\n" + expected + "\n" + 
									 "Actual: " + "\n" +  actualText);
							
							AssertFailedCount++;
						
						}
			
					}
					else
					{
						System.out.println("Failure!!! Pop up not found.");
						Reporter.log("Failure!!! Pop up not found.");
					}					
						
				try {
					/*
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

					instructoreventroster.EventDelete.click();
					Thread.sleep(5000);

					instructoreventroster.Confirm_EventDelete.click();
					
					Thread.sleep(5000);*/
					SeleniumFunc.ToGoToUrl(URL);
					Thread.sleep(5000);
					
					SeleniumFunc.ClickOnElement("linkText", "Delete Event");
					
					Thread.sleep(2000);
				
					SeleniumFunc.ClickOnElement("linkText", "Delete Event");

					Thread.sleep(2000);

					System.out.println("Event: "+EventId+" Deleted Successfully!!");
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
		
		
		/* Test : 
		 * Verify Contact Event Manager Support form submission on Login Page
		*/ 
		@Test
		private void ContactSupport_LoginPage() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test  : Verify Contact Event Manager Support Form submission on Login Page"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test  : Verify Contact Event Manager Support Form submission on Login Page"  + "\n" +
					 	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			EMSendAMessagePage message=new EMSendAMessagePage(driver);

			System.out.println("Step 1 : Goto Login page  : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 1 : Goto Login page : " + Constants.ApplicationURL_EM +"/login/login"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				
			System.out.println("Step 2 : Click on Contact Event Manager Support & Verify Email Subject");
			Reporter.log("Step 2 : Click on Contact Event Manager Support & Verify Email Subject"); 
			
				login.ContactSupport.click();
				Thread.sleep(4000);
				//Verify Subject
				String expected ="Log In Inquiry";
				String actual =message.Subject.getAttribute("value").trim();
				
				if(actual.contains(expected.trim()))
				{
					System.out.println("Success!!! Subject matches the expectation : "+actual);
					Reporter.log("Success!!! Subject matches the expectation : "+actual);
				}
				else
				{
					System.out.println("Failure !!Subject does not match expectation." + "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							 "Actual: " + "\n" +  actual);
					Reporter.log("Failure !!Subject does not match expectation." + "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							"Actual: " + "\n" +  actual);
		
					AssertFailedCount++;
				}
								
			System.out.println("Step 3 : Fill up Send Us An Email Form & Submit");
			Reporter.log("Step 3 : Fill up Send Us An Email Form & Submit"); 					
				
				message.EnterName("KE Tester");
				message.EnterEmail("rohit.ware@clariontechnologies.co.in");
				SeleniumFunc.SelectValueFromDropdownList("css", "#state", "Alabama");
				Thread.sleep(2000);
				message.EnterReason("Just a test. Please ignore.");
				
				message.Submit.click();
				Thread.sleep(2000);
				
			System.out.println("Step 4 : Verify Success message");
			Reporter.log("Step 4 : Verify Success message"); 	
				
				expected = "KE Tester, thanks for contacting us."+
						"\n"+ "Your message has been sent to the Agency Support Team. You should hear back from them shortly."+
						"\n" + "\n" + "\n" + "Sincerely,"+
						"\n"+ "The Register-Ed.com Team";
				
				actual = message.SuccessMessage_Contact.getText();
				
				if(actual.contains(expected))
				{
					System.out.println("Success !! Mail sent successfully.");
					Reporter.log("Success !! Mail sent successfully.");
				}
				else
				{
					System.out.println("Failure !! There is problem while sending mail."+ "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							 "Actual: " + "\n" +  actual);
					Reporter.log("Failure !! There is problem while sending mail."+ "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							 "Actual: " + "\n" +  actual);
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
		
		/* Test : 
		 * Verify Contact Event Manager Support Form submission on Kalkomey Administration page
		*/ 
		@Test
		private void ContactSupport_KalkomeyAdministrationPage() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test  : Verify Contact Event Manager Support Form submission on Kalkomey Administration Page"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test  : Verify Contact Event Manager Support Form submission on Kalkomey Administration Page"  + "\n" +
					 	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			EMSendAMessagePage message=new EMSendAMessagePage(driver);

			System.out.println("Step 1 : Login to EM as admin at : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 1 : Login to EM as admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();			
				Thread.sleep(5000);
				
			System.out.println("Step 2 : Click on Contact Event Manager Support & Verify Name, Email & Subject");
			Reporter.log("Step 2 : Click on Contact Event Manager Support & Verify Name, Email & Subject"); 
			
				login.ContactSupport.click();
				Thread.sleep(4000);
				
				//Verify Name
				String expected ="KE Tester";
				String actual =message.Name.getAttribute("value");
				
				if(actual.contains(expected.trim()))
				{
					System.out.println("Success!!! Name matches the expectation : "+actual);
					Reporter.log("Success!!! Name matches the expectation : "+actual);
				}
				else
				{
					System.out.println("Failure !! Name does not match expectation." + "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							 "Actual: " + "\n" +  actual);
					Reporter.log("Failure !! Name does not match expectation." + "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							"Actual: " + "\n" +  actual);
		
					AssertFailedCount++;
				}
				
				//Verify Email
				//expected ="kiran.hole@clariontechnologies.co.in";
				expected ="rohit.ware@clariontechnologies.co.in";
				actual =message.Email_contact.getAttribute("value").trim();
				
				if(actual.contains(expected.trim()))
				{
					System.out.println("Success!!! Email matches the expectation : "+actual);
					Reporter.log("Success!!! Email matches the expectation : "+actual);
				}
				else
				{
					System.out.println("Failure !! Email does not match expectation." + "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							 "Actual: " + "\n" +  actual);
					Reporter.log("Failure !! Email does not match expectation." + "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							"Actual: " + "\n" +  actual);
		
					AssertFailedCount++;
				}
				
				//Verify Subject
				expected ="Kalkomey Administration Inquiry";
				actual =message.Subject.getAttribute("value").trim();
				
				if(actual.contains(expected.trim()))
				{
					System.out.println("Success!!! Subject matches the expectation : "+actual);
					Reporter.log("Success!!! Subject matches the expectation : "+actual);
				}
				else
				{
					System.out.println("Failure !!Subject does not match expectation." + "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							 "Actual: " + "\n" +  actual);
					Reporter.log("Failure !!Subject does not match expectation." + "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							"Actual: " + "\n" +  actual);
		
					AssertFailedCount++;
				}
				
						
			System.out.println("Step 3 : Fill up Send Us An Email Form & Submit");
			Reporter.log("Step 3 : Fill up Send Us An Email Form & Submit"); 				
				
				message.EnterReason("Just a test. Please ignore.");
				message.Submit.click();
				Thread.sleep(4000);
				
			System.out.println("Step 4 : Verify Success message");
			Reporter.log("Step 4 : Verify Success message"); 	
				
				expected = "KE Tester, thanks for contacting us."+
						"\n"+ "Your message has been sent to the Agency Support Team. You should hear back from them shortly."+
						"\n" + "\n" + "\n" + "Sincerely,"+
						"\n"+ "The Register-Ed.com Team";
				
				actual = message.SuccessMessage_Contact.getText();
				
				if(actual.contains(expected))
				{
					System.out.println("Success !! Mail sent successfully.");
					Reporter.log("Success !! Mail sent successfully.");
				}
				else
				{
					System.out.println("Failure !! There is problem while sending mail."+ "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							 "Actual: " + "\n" +  actual);
					Reporter.log("Failure !! There is problem while sending mail."+ "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							 "Actual: " + "\n" +  actual);
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
		
		

		/* Test : 
		 * Verify Agency User Support Form submission on Agency Setting page
		*/ 
		@Test
		private void ContactSupport_AgencySettingPage() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test  : Verify Contact Event Manager Support Form submission on  Agency Setting page"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test  : Verify Contact Event Manager Support Form submission on  Agency Setting page"  + "\n" +
					 	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			EMSendAMessagePage message=new EMSendAMessagePage(driver);
			AgencyHomePage agencyhome= new AgencyHomePage(driver);

			System.out.println("Step 1 : Login to EM as ADMIN at : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 1 : Login to EM as ADMIN at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();			
				Thread.sleep(5000);
				
			System.out.println("Step 2 : Goto Agency Setting page & Click on Contact Event Manager Support & Verify Name, Email & Subject");
			Reporter.log("Step 2 : Goto Agency Setting page & Click on Contact Event Manager Support & Verify Name, Email & Subject"); 
			
				SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
				Thread.sleep(5000);
				agencyhome.ClickToSelectProgram();
				Thread.sleep(5000);
				
				login.ContactSupport.click();
				Thread.sleep(4000);
				
				//Verify Name
				String expected ="KE Tester";
				String actual =message.Name.getAttribute("value");
				
				if(actual.contains(expected.trim()))
				{
					System.out.println("Success!!! Name matches the expectation : "+actual);
					Reporter.log("Success!!! Name matches the expectation : "+actual);
				}
				else
				{
					System.out.println("Failure !! Name does not match expectation." + "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							 "Actual: " + "\n" +  actual);
					Reporter.log("Failure !! Name does not match expectation." + "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							"Actual: " + "\n" +  actual);
		
					AssertFailedCount++;
				}
				
				//Verify Email
				//expected ="kiran.hole@clariontechnologies.co.in";
				expected ="rohit.ware@clariontechnologies.co.in";
				actual =message.Email_contact.getAttribute("value").trim();
				
				if(actual.contains(expected.trim()))
				{
					System.out.println("Success!!! Email matches the expectation : "+actual);
					Reporter.log("Success!!! Email matches the expectation : "+actual);
				}
				else
				{
					System.out.println("Failure !! Email does not match expectation." + "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							 "Actual: " + "\n" +  actual);
					Reporter.log("Failure !! Email does not match expectation." + "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							"Actual: " + "\n" +  actual);
		
					AssertFailedCount++;
				}
				
				//Verify Subject
				expected ="Demo Hunter Education Field Day Inquiry";
				actual =message.Subject.getAttribute("value").trim();
				
				if(actual.contains(expected.trim()))
				{
					System.out.println("Success!!! Subject matches the expectation : "+actual);
					Reporter.log("Success!!! Subject matches the expectation : "+actual);
				}
				else
				{
					System.out.println("Failure !!Subject does not match expectation." + "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							 "Actual: " + "\n" +  actual);
					Reporter.log("Failure !!Subject does not match expectation." + "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							"Actual: " + "\n" +  actual);
		
					AssertFailedCount++;
				}
				
						
			System.out.println("Step 3 : Fill up Send Us An Email Form & Submit");
			Reporter.log("Step 3 : Fill up Send Us An Email Form & Submit"); 				
				
				message.EnterReason("Just a test. Please ignore.");
				message.Submit.click();
				Thread.sleep(2000);
				
			System.out.println("Step 4 : Verify Success message");
			Reporter.log("Step 4 : Verify Success message"); 	
				
				expected = "KE Tester, thanks for contacting us."+
						"\n"+ "Your message has been sent to the Agency Support Team. You should hear back from them shortly."+
						"\n" + "\n" + "\n" + "Sincerely,"+
						"\n"+ "The Register-Ed.com Team";
				
				actual = message.SuccessMessage_Contact.getText();
				
				if(actual.contains(expected))
				{
					System.out.println("Success !! Mail sent successfully.");
					Reporter.log("Success !! Mail sent successfully.");
				}
				else
				{
					System.out.println("Failure !! There is problem while sending mail."+ "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							 "Actual: " + "\n" +  actual);
					Reporter.log("Failure !! There is problem while sending mail."+ "\n" + "Expected : "  
							+ "\n" + expected + "\n" + 
							 "Actual: " + "\n" +  actual);
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
		
		
		
		/* Test 1: 
		 * Verify Event Status History is Correct for event with admin approval
		*/ 
		@Test
		private void DisplayZipCode() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 1 :  Verify Event Status History is Correct for event with admin approval"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 1 :  Verify Event Status History is Correct for event with admin approval"  + "\n" +
					 	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			
			System.out.println("Step 1 : Go to Register-Ed at : " + Constants.APPLICATIONURL_RegisterEd);
			Reporter.log("Step 1 : Go to Register-Ed at : " + Constants.APPLICATIONURL_RegisterEd); 
				
				SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd);
			
			System.out.println("Step 1 : Go to URL with zip code and range");
			Reporter.log("Step 1 : Go to URL with zip code and range");
			
				SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd + "/programs/arkansas/36-arkansas-hunter-education-classroom?zip=71913&distance=50");
				
			System.out.println("Step 1 : Verify Zip Code and Range is already  selected ");
			Reporter.log("Step 1 : Verify Zip Code and Range is already  selected ");			
		
				//Zip Code
				String Actual = SeleniumFunc.GetElementText("css", "#eventZipOuter strong");
				String Expected = "71913";
								
				if(Actual.equals(Expected))
				{	
					System.out.println("Success !! Correct Zip code is displayed. i.e." + Actual);
					Reporter.log("Success !! Correct Zip code is displayed. i.e." + Actual);			
				}
				else
				{			
					System.out.println("Failure !! Wrong or No Zip code is displayed." + "\n" + "Expected : "  
										+ "\n" + Expected + "\n" + 
										 "Actual: " + "\n" +  Actual);
					Reporter.log("Failure !! Wrong or No Zip code is displayed." + "\n" + "Expected : "  
										+ "\n" + Expected + "\n" + 
										"Actual: " + "\n" +  Actual);
					
					AssertFailedCount++;				
				}
				
				
				//Miles away
				Actual = SeleniumFunc.GetSelectedValueFromDropdownList("css", "#eventDistance");
				Expected = "within 50 miles";
								
				if(Actual.equals(Expected))
				{	
					System.out.println("Success !! Correct value is selected for within miles. i.e." + Actual);
					Reporter.log("Success !! Correct value is selected for within miles. i.e." + Actual);			
				}
				else
				{			
					System.out.println("Failure !! Wrong or No value selected for within miles." + "\n" + "Expected : "  
										+ "\n" + Expected + "\n" + 
										 "Actual: " + "\n" +  Actual);
					Reporter.log("Failure !! Wrong or No value selected for within miles." + "\n" + "Expected : "  
										+ "\n" + Expected + "\n" + 
										"Actual: " + "\n" +  Actual);
					
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

		
		
		/* Test 1: 
		 * Verify Event Status History is Correct for event with admin approval
		*/ 
		@Test
		private void NonAuthorizedAdmin_NoUserView() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 1 :  Verify Event Status History is Correct for event with admin approval"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 1 :  Verify Event Status History is Correct for event with admin approval"  + "\n" +
					 	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);

			System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);
				
		    System.out.println("Step 2 : Go to Users and search for user, store URL");
	    	Reporter.log("Step 2 : Go to Users and search for user, store URL"); 

			SeleniumFunc.ClickOnElement("css", ".span7 h4:nth-of-type(2) + ul li:nth-of-type(3)");
			Thread.sleep(4000);
						
			agency.SelectSearchType("Username");
			agency.EnterSearchData(Constants.EM_Instructor_Username);
			agency.ClickOnSearchButton();
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("css", "#user_select_form span");
			Thread.sleep(4000);

			String URL = driver.getCurrentUrl();
			login.ClickOnLogoutButton();
			Thread.sleep(5000);
			
		System.out.println("Step 3 : Logout and Login as other agency Admin");
		Reporter.log("Step 3 : Logout and Login as other agency Admin"); 

			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
		
		System.out.println("Step 4 : Try to visit User with other agency.");
		Reporter.log("Step 4 : Try to visit User with other agency."); 			
			
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(8000);
			
			//Verify User details are not displayed
			
			String Actual = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed + p");
			String Expected = "You are not authorized to see this page.";
							
			if(Actual.equals(Expected))
			{	
				System.out.println("Success !! Correct message is displayed for Unathorized access. i.e." + Actual);
				Reporter.log("Success !! Correct message is displayed for Unathorized access. i.e." + Actual);			
			}
			else
			{			
				System.out.println("Failure !! Wrong or No message for unauthorized access." + "\n" + "Expected : "  
									+ "\n" + Expected + "\n" + 
									 "Actual: " + "\n" +  Actual);
				Reporter.log("Failure !! Wrong or No message for unauthorized access." + "\n" + "Expected : "  
									+ "\n" + Expected + "\n" + 
									"Actual: " + "\n" +  Actual);
				
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
		
		
		/* Test 1: 
		 * Verify User can enable and disable Federal Reporting feature
		*/ 
		@Test
		private void SortEventByDate() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 1 : Verify User can enable and disable Federal Reporting feature"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 1 : Verify User can enable and disable Federal Reporting feature"  + "\n" +
					 	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			AgencyHomePage agencyhome= new AgencyHomePage(driver);
			AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);

			System.out.println("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");				
				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);

			
			System.out.println("Step 2 : Select agency from agency listing");
			Reporter.log("Step 2 : Select agency from agency listing"); 
					
							
				SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
				Thread.sleep(5000);

			
			System.out.println("Step 3 : Select a program and go to Program Settings");
			Reporter.log("Step 3 : Select a program and go to Program Settings"); 
				
			
			  	agencyhome.ClickToSelectProgram();
				Thread.sleep(5000);

			System.out.println("Step 4 : Go to Search event and verify sord by date");
			Reporter.log("Step 4 : Go to Search event and verify sord by date"); 			
				
				agencyhome.EventSearch.click();
				Thread.sleep(4000);
				
				searchevent.SelectSearchTypeByVisibleText("Date");
				Thread.sleep(2000);
				searchevent.EnterDateSearchData("02/02/2014-02/02/2017");
				SeleniumFunc.ClickOnElement("css", "input[value*='Search']");
				Thread.sleep(5000);
			
			System.out.println("Step 5 : Click on date column and verify arrow direction");
			Reporter.log("Step 5 : Click on date column and verify arrow direction"); 			
			
				SeleniumFunc.ClickOnElement("css", "#page th:nth-of-type(2) a:nth-of-type(2)");
				Thread.sleep(5000);
				
				//Verify Arrow directing changed, sorting working properly
				if(SeleniumFunc.IsElementPresent("css", ".icon-chevron-up.align-top"))
				{
					System.out.println("Success !! Arrow direction is changed to Up.");
					Reporter.log("Success !! Arrow direction is changed to down."); 
				}
				else
				{
					System.out.println("Failure !! Arrow direction is NOT changed to Up.");
					Reporter.log("Failure !! Arrow direction is NOT changed to Up.");			
					AssertFailedCount++;
				}
				
				//Again click to sort and verify direction
				SeleniumFunc.ClickOnElement("css", "#page th:nth-of-type(2) a:nth-of-type(2)");
				Thread.sleep(5000);
			
				if(SeleniumFunc.IsElementPresent("css", ".icon-chevron-down.align-bottom"))
				{
					System.out.println("Success !! Arrow direction is changed to down.");
					Reporter.log("Success !! Arrow direction is changed to down."); 
				}
				else
				{
					System.out.println("Failure !! Arrow direction is NOT changed to down.");
					Reporter.log("Failure !! Arrow direction is NOT changed to down.");			
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

		
		
		 /*
		 Test 2: 
		 * Verify Cancel Registration Action
		 */
		@Test
		private void DisplaySystemDetails() throws Exception
		{

			System.out.println("====" + "\n" +
						"Test 2: Verify Cancel Registration Action"  + "\n" +"====");
			Reporter.log("====" + "\n" +
			 			  "Test 2 : Verify Cancel Registration Action"  + "\n" +  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
			InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
			InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
			InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
			AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
			EMStudentRegistrationPage registration = new EMStudentRegistrationPage(driver);
			AdminSearchEventPage adminsearchevent = new AdminSearchEventPage(driver);
			EMAddStudentPage addstud = new EMAddStudentPage(driver);
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
				System.out.println("Url :" +URL);
					
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

				
				//Go to EM and set Payment Off
				PaymentSettingsPage payment = new PaymentSettingsPage(driver);	
				AgencyHomePage agencyhome= new AgencyHomePage(driver);
				SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
				Thread.sleep(5000);

				agencyhome.ClickToSelectProgram();
				Thread.sleep(5000);

				agencyhome.ClickOnPaymentSettingsLink();
				Thread.sleep(5000);

				payment.SelectPaymentMode("Global");
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
	
			System.out.println("Step 9 : Login to EM and go to Student Registration");
			Reporter.log("Step 9 : Login to EM and go to Student Registration"); 
			
				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);
	
				SeleniumFunc.ToGoToUrl(URL);
				
				SeleniumFunc.ClickOnElement("linkText", "Event Roster");
				Thread.sleep(5000);
				
				/*
				//Enter Instructor Information
				instructorhome.VisitHomePage.click();
				Thread.sleep(5000);
				
			

				instructorhome.Action_MyEventSchedule.click();	
				Thread.sleep(5000);

				instructorhome.GoToNextMonth();
				instructorschedule.FirstEventName.click();
				Thread.sleep(5000);
	*/
			//	instructoreventroster.SelectAction("Add Student");
				SeleniumFunc.ClickOnElement("linkText", "Add Student");
				Thread.sleep(5000);

				String username="emstudent"+ JH.GenerateRandomNumber();
				String emailaddress = username + "@mailinator.com";
				addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
				addstud.SelectEyeColor();
				addstud.SelectHairColor();
				addstud.EnterSSNNO("125896336");
				
				SeleniumFunc.ClickOnElement("css", "#offline_payment");
				Thread.sleep(3000);

				addstud.ClickOnRegisterButton();
				Thread.sleep(5000);

				String Roster = driver.getCurrentUrl();
				//Go to Student Registration 
				instructoreventroster.ClickOnSelectFirstStudent();
				Thread.sleep(4000);
				
				instructoreventroster.ClickOnViewRegistrationButton();
				Thread.sleep(4000);
				
			System.out.println("Step 10 : Verify Online Payment skipped with Yes is present");
			Reporter.log("Step 10 : Verify Online Payment skipped with Yes is present"); 
				
			SeleniumFunc.ClickOnElement("css", ".icon-plus");	
			expectedtext = "Online Payment Skipped" + "\n" + "Yes";
				actualtext = SeleniumFunc.GetElementText("css", "#system-information > div:nth-child(2) > dl:nth-child(2) > dt") + "\n" +SeleniumFunc.GetElementText("css", "#system-information > div:nth-child(2) > dl:nth-child(2) > dd");
					
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! Online payment skipped with status Yes is present.. i.e. -" +actualtext);
					Reporter.log("Success !! Online payment skipped with status Yes is present.. i.e. -" +actualtext);
				}
				else
				{
					System.out.println("Failure !! Online payment skipped message is missing." + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! Online payment skipped message is missing." + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
						
					AssertFailedCount++;	
				}
				
				//String URL = driver.getCurrentUrl();
				
				registration.CancelRegistration.click();
				Thread.sleep(4000);
						
				registration.Cancel.click();
				Thread.sleep(4000);
				
			System.out.println("Step 10 : Verify Cancel Registration details are present");
			Reporter.log("Step 10 : Verify Cancel Registration details are present"); 
					
				SeleniumFunc.ToGoToUrl(URL);
				Thread.sleep(4000);
				
				expectedtext = "Canceled By" + "\n"+  "Automation Testing";
				actualtext = SeleniumFunc.GetElementText("css", "#page div:nth-of-type(10) dl:nth-of-type(1)");
						
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! Calceled by information is present. i.e. -" +actualtext);
					Reporter.log("Success !! Calceled by information is present. i.e. -" +actualtext);
				}	
				else
				{			
						System.out.println("Failure !! Calceled by information is missing." + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! Calceled by information is missing." + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											"Actual: " + "\n" +  actualtext);
							
						AssertFailedCount++;	
				}			
				
				
				//Canceled on information
				expectedtext = "Canceled On";
				actualtext = SeleniumFunc.GetElementText("css", "#page div:nth-of-type(10) dl:nth-of-type(2)");
						
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success !! Canceled On information is present. i.e. -" +actualtext);
					Reporter.log("Success !! Canceled On information is present. i.e. -" +actualtext);
				}	
				else
				{			
						System.out.println("Failure !! Canceled On information is missing." + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! Canceled On information is missing." + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											"Actual: " + "\n" +  actualtext);
							
						AssertFailedCount++;	
				}			
				
				
				try {
					//Go to instructor and delete the event
					SeleniumFunc.ToGoToUrl(Roster);
					instructoreventroster.EventDelete.click();
					Thread.sleep(5000);

					instructoreventroster.Confirm_EventDelete.click();
					
					Thread.sleep(5000);
					
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

					agencyhome.ClickOnPaymentSettingsLink();
					Thread.sleep(5000);

					payment.SelectPaymentMode("Off");
					payment.ClickOnSaveChanges();	
					Thread.sleep(5000);
				} catch (Exception e) {
					
				}
				
			
			
			 // Marking Test Pass or Fail as per the value of AssertFailedCount variable
			 
		 	if(AssertFailedCount>0)	
			{
				
				//Marking this test as Failed
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
				
				Assert.fail();
			}
			
		}
		
		/*
		@Test
		private void InPersonRedirectToRegisterEd() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 1 : Verify user is able to email certificate and certificate email is received by user with correct details"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 1 : Verify user is able to email certificate and certificate email is received by user with correct details"  + "\n" +
					 	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			RegistrationNewPage register = new RegistrationNewPage(driver);
			KECourseAdminPage keadmin = new KECourseAdminPage(driver);
			FinalExamPage finalexam = new FinalExamPage(driver);
			CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
			PaymentInformationPage payinfo = new PaymentInformationPage(driver);
			ViewReceiptPage receipt = new ViewReceiptPage(driver);
			ProfileNewPage profile = new ProfileNewPage(driver);
			ContentsPage content = new ContentsPage(driver);
			

			System.out.println("Step 1: Signing up as new User");
			Reporter.log("Step 1: Signing up as new User"); 
				
				String username= "payment" + JH.GenerateRandomNumber();
				System.out.println(username);
				String emailprefix = "payment" + JH.GenerateRandomNumber();
				String emailaddress= emailprefix + "@mailinator.com";
				System.out.println(emailaddress);
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				PageHeader header = new PageHeader(driver);

				Thread.sleep(4000);
				if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
				{
					header.Logout_Handgun.click();
					Thread.sleep(4000);
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
					Thread.sleep(4000);
				}
						
				header.Register_New.click();
					
				Thread.sleep(8000);
						
				if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{					
					register.ClickOnIUnderstandButton();			
				}
				
				register.RegisterAsNewUser(username,  "January", "2", "1990", emailaddress, "clarion@123", "clarion@123");
				Thread.sleep(10000);
				
				if(SeleniumFunc.IsElementPresent(content.IntroModal))
				 {
					content.IntroModal.click();
					Thread.sleep(4000);
				 }		

				header.Contents.click();

				String ContentPageUrl = driver.getCurrentUrl();
		
				header.Profile.click();
				Thread.sleep(5000);
	
			System.out.println("Step 3 : Editing all fields (i.e. Gender, , Email Address, Mailing Address) and clicking on 'Update Profile' button");
			Reporter.log("Step 3 : Editing all fields (i.e. Gender, , Email Address, Mailing Address) and clicking on 'Update Profile' button");
					
				String gender= "Male";
				String email = "temp@testing.com";
				String addressline1 = "14086 Proton Rd";
				String addressline2 = "";
				String city= "Dallas";
				String state="Texas";
				String zip="75244";
				String country="United States of America";
				String phonenumber="234-567-8910";
				
				profile.EnterFirstName("Test");
				profile.EnterLastName("Ke-Testing");
				profile.SelectGender(gender);
				profile.EnterEmailAddress(email);
				profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
				profile.ClickOnUpdateProfileButton();			
		
				Thread.sleep(8000);
				
			System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
			Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
			
				SeleniumFunc.ToGoToUrl("https://piyush:piyush@admintools-qa1.kalkomey.com/");
				Runtime.getRuntime().exec("/src/test/resources/AutoIt/Courses_admin_auth_GC.exe");
				Thread.sleep(5000);
				
				keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
				
				//Go back to Contents page
				SeleniumFunc.ToGoToUrl(ContentPageUrl);
				Thread.sleep(4000);

				//Click on Continue Exam button
				SeleniumFunc.ClickOnElement("css", "#next_text a");
				Thread.sleep(4000);

				//Selecting Answer for Question 61 and clicking on Next button
				
					//Selecting answer
					finalexam.Answer1.click();
					Thread.sleep(4000);

					//Clicking next button
					SeleniumFunc.ClickOnElement("id", "submit");
					Thread.sleep(4000);

					SeleniumFunc.ClickOnElement("css", "#next_text a");
					Thread.sleep(4000);				
				
			System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
			Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
			
				cerinfo.SelectGender("Female");
				
				cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
				
				cerinfo.ConfirmCertificateInformation.click();
				Thread.sleep(4000);
			
				
				String expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
				cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
				Thread.sleep(4000);
				String actualurl = driver.getCurrentUrl();
				
				if(actualurl.contains(expectedurl))
				{
					System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
					Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				}
				else
				{
					System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
					Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
					AssertFailedCount++;
				}
			
			System.out.println("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button" );
			Reporter.log("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button");
			
				payinfo.FirstName.sendKeys("test");
				payinfo.LastName.sendKeys("Patel");
				payinfo.CCNumber.sendKeys("4111111111111111");
				
				new Select(payinfo.ExpirationMonth).selectByVisibleText("03");
				new Select(payinfo.ExpirationYear).selectByVisibleText("2017");
				
				payinfo.BillingContactInfoCheckbox.click();
				Thread.sleep(4000);

				payinfo.ConfirmPaymentInformation.click();
				
				Thread.sleep(4000);
				
				payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
				
				Thread.sleep(10000);
							
				receipt.GetYourProofofHomeStudyVoucher.click();
				Thread.sleep(4000);
					
			System.out.println("Step 5 : Click on in-person button and verify control is redirected to Register-Ed with zip and range selected" );
			Reporter.log("Step 5 : Click on in-person button and verify control is redirected to Register-Ed with zip and range selected" );
		
				SeleniumFunc.ClickOnElement("css", "#main section:nth-of-type(2) div a");
				Thread.sleep(4000);
				
				for (String winHandle : driver.getWindowHandles()) 
				{
				    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
				}
				
				//Verify URL 
				expectedurl = Constants.APPLICATIONURL_RegisterEd + "/programs/arkansas/35-arkansas-hunter-education-online-in-person-test?zip=75244&distance=75";
				actualurl = driver.getCurrentUrl();
				
				if(actualurl.equals(expectedurl))
				{
					System.out.println("-- Success !! user is navigated to Register-Ed");
					Reporter.log("-- Success !! user is navigated to Register-Ed");
					
					
				}
				else
				{
					System.out.println("-- Failure !! User is navigated to : " + actualurl);
					Reporter.log("-- Failure !! User is navigated to : " + actualurl);
					AssertFailedCount++;
				}
			
				//Zip Code
				String Actual = SeleniumFunc.GetElementText("css", "#eventZipOuter strong");
				String Expected = "75244";
								
				if(Actual.equals(Expected))
				{	
					System.out.println("Success !! Correct Zip code is displayed. i.e." + Actual);
					Reporter.log("Success !! Correct Zip code is displayed. i.e." + Actual);			
				}
				else
				{			
					System.out.println("Failure !! Wrong or No Zip code is displayed." + "\n" + "Expected : "  
										+ "\n" + Expected + "\n" + 
										 "Actual: " + "\n" +  Actual);
					Reporter.log("Failure !! Wrong or No Zip code is displayed." + "\n" + "Expected : "  
										+ "\n" + Expected + "\n" + 
										"Actual: " + "\n" +  Actual);
					
					AssertFailedCount++;				
				}
				
				
				//Miles away
				Actual = SeleniumFunc.GetSelectedValueFromDropdownList("css", "#eventDistance");
				Expected = "within 75 miles";
								
				if(Actual.equals(Expected))
				{	
					System.out.println("Success !! Correct value is selected for within miles. i.e." + Actual);
					Reporter.log("Success !! Correct value is selected for within miles. i.e." + Actual);			
				}
				else
				{			
					System.out.println("Failure !! Wrong or No value selected for within miles." + "\n" + "Expected : "  
										+ "\n" + Expected + "\n" + 
										 "Actual: " + "\n" +  Actual);
					Reporter.log("Failure !! Wrong or No value selected for within miles." + "\n" + "Expected : "  
										+ "\n" + Expected + "\n" + 
										"Actual: " + "\n" +  Actual);
					
					AssertFailedCount++;			
				}
				
					
			/*
			// * Marking Test Pass or Fail as per the value of AssertFailedCount variable
			 
		 	if(AssertFailedCount>0)	
			{
				
				//Marking this test as Failed
				
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
				
				Assert.fail();
			}
		}	
		
		*/
		
		@Test
		private void ProctorChangesProfile() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 1 : Instructor > Home : Verify elements on welcome instructor page"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 1 : Instructor > Home : Verify elements on welcome instructor page"  + "\n" +
					 	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			InstructorProfilePage instructorprofile = new InstructorProfilePage(driver);

			System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_CLInstructor_Username);
				login.EnterPassword(Constants.EM_CLInstructor_Password);
				login.ClickOnLogInButton();
				Thread.sleep(4000);
				
			System.out.println("Step 2 : Navigate to profile page and Edting profile details for First Name, Middle Initial , Last Name, Suffix , Gender, Password Hint, Secret Question,Secret Answer,Region, County, Address, City,State,Zipcode fields");
			Reporter.log("Step 2 : Navigate to profile page and Edting profile details for First Name, Middle Initial , Last Name, Suffix , Gender, Password Hint, Secret Question,Secret Answer,Region, County, Address, City,State,Zipcode fields");
					
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/proctor/editprofile");
				Thread.sleep(4000);
				
				instructorprofile.Zip.clear();
				instructorprofile.Zip.sendKeys("11111");

				instructorprofile.SaveChangesButton.click();
				Thread.sleep(4000);
				
			System.out.println("Step 3 : Verifying whether fields are edited successsfully or not");
			Reporter.log("Step 3 : Verifying whether fields are edited successsfully or not");
			
				//Verifying alert message
				String actualtext = instructorprofile.SuccessAlertMessage.getText().trim(); 
				String expectedtext = "Your profile has been updated.";
						
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! alert message is correct");
					Reporter.log("Success !! alert message is correct ");
				}
				else
				{
					System.out.println("Failure !! alert message is incorrect" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !!  alert message is incorrect" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}			
				
				//Verifying Zip
				actualtext = instructorprofile.Zip.getAttribute("value"); 
				expectedtext = "11111";
						
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! Zip is edited successfully");
					Reporter.log("Success !! Zip is edited successfully");
				}
				else
				{
					System.out.println("Failure !! Zip is NOT edited successfully" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !!  Zip is NOT edited successfully" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				instructorprofile.Zip.clear();
				instructorprofile.Zip.sendKeys("25848");
				
				// Clicking on 'Save Changes' button
				instructorprofile.SaveChangesButton.click();
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
					
		
		
		@Test
		private void EventSearchUI() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 1 : Verify the details for Full Event with Wait list"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 1 : Verify the details for Full Event with Wait list"  + "\n" +
					 	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);

			System.out.println("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 5 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 			
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");

				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);
				
			System.out.println("Step 6 : Go to Search Event page and verify columns provided");
			Reporter.log("Step 6 : Go to Search Event page and verify columns provided"); 			
			  			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
				Thread.sleep(5000);		
				
				String Expected  = "IDStatusName / Date & TimeLocation / InstructorCap";
				String Actual = searchevent.GetEventSearchColumns();

				if(Actual.equals(Expected))
				{
					System.out.println("Success !! All columns are provided for search result.");
					Reporter.log("Success !! All columns are provided for search result.");
				}
				else
				{
					System.out.println("Failure !! All columns are NOT provided" + "\n" + "Expected : "  
										+ "\n" + Expected + "\n" + 
										 "Actual: " + "\n" +  Actual);
					Reporter.log("Failure !! All columns are NOT provided" + "\n" + "Expected : "  
							+ "\n" + Expected + "\n" + 
							 "Actual: " + "\n" +  Actual);
					
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
		
		
		 /*Test 6: 
			 * EM > Search events by name
			 */
			@Test
			private void SearchEventByName() throws Exception
			{

				System.out.println("====" + "\n" +
							"Test 6 : Search events by name"  + "\n" +"====");
				Reporter.log("====" + "\n" +
				 			  "Test 6 : Search events by name"  + "\n" +  "====");	
				
				int AssertFailedCount=0 ;
				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				LoginPage login = new LoginPage(driver);
				AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);			
				AgencyHomePage agencyhome= new AgencyHomePage(driver);

				System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
				Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
					
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
					
					login.EnterUsername(Constants.EM_Admin_Username);
					login.EnterPassword(Constants.EM_Admin_Password);
					login.ClickOnLogInButton();
					Thread.sleep(5000);
			
					//Go to EM and set Payment Off
					
					SeleniumFunc.ClickOnElement("linkText", "PGC Outreach");
					Thread.sleep(5000);
					agencyhome.ClickToSelectProgram();
					Thread.sleep(5000);
					
				System.out.println("Step 2 : Go to Search event and Search for Event Name 'Family' ");
				Reporter.log("Step 2 : Go to Search event and Search for Event Name 'Family' "); 			
						
					agencyhome.EventSearch.click();
					Thread.sleep(4000);
						
					searchevent.SelectSearchTypeByVisibleText("Name");
					searchevent.EnterNameSearchData("family");
					SeleniumFunc.ClickOnElement("css", "input[value*='Search']");
					Thread.sleep(5000);	
					
				System.out.println("Step 3 : Verify search results for 'Family' ");
				Reporter.log("Step 3 : Verify search results for 'Family' ");
					
					String actualText = searchevent.FirstElement.getText().trim().toLowerCase();
					String expectedText = "Family Field Day";
					
					if(actualText.contains(expectedText.trim().toLowerCase()))
					{
						System.out.println("Success!! Correct search found for 'Family' : "+actualText);
						Reporter.log("Success!! Correct search found for 'Family' : "+actualText);
					}
					else
					{
						System.out.println("Failure!! search Not found for 'Family' actual: "+actualText+"\n"+"Expected: "+expectedText);
						Reporter.log("Failure!! search Not found for 'Family' actual: "+actualText+"\n"+"Expected: "+expectedText);
						AssertFailedCount++;
					}
					
				System.out.println("Step 4 : Search for Event Name 'Youth' ");
				Reporter.log("Step 4 : Search for Event Name 'Youth' "); 			
		
						searchevent.SelectSearchTypeByVisibleText("Name");
						searchevent.EnterNameSearchData("youth");
						SeleniumFunc.ClickOnElement("css", "input[value*='Search']");
						Thread.sleep(5000);
							
				System.out.println("Step 5 : Verify search results for 'Youth' ");
				Reporter.log("Step 5 : Verify search results for 'Youth' ");
						
						actualText = searchevent.FirstElement.getText().trim().toLowerCase();
						expectedText = "Youth Field Day";
						
						if(actualText.contains(expectedText.trim().toLowerCase()))
						{
							System.out.println("Success!! Correct search found for 'Youth' : "+actualText);
							Reporter.log("Success!! Correct search found for 'Youth' : "+actualText);
						}
						else
						{
							System.out.println("Failure!! search Not found for 'Youth' actual: "+actualText+"\n"+"Expected: "+expectedText);
							Reporter.log("Failure!! search Not found for 'Youth' actual: "+actualText+"\n"+"Expected: "+expectedText);
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
		
			
			
			/*Test 6: 
			 * EM > Verify show / hide Inactive Agencies button working
			 */
			@Test
			private void VerifyInactiveAgencies() throws Exception
			{

				System.out.println("====" + "\n" +
							"Test  : Verify show / hide Inactive Agencies button working"  + "\n" +"====");
				Reporter.log("====" + "\n" +
				 			  "Test  : Verify show / hide Inactive Agencies button working"  + "\n" +  "====");	
				
				int AssertFailedCount=0 ;
				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				LoginPage login = new LoginPage(driver);
				
				AgencyHomePage agencyhome= new AgencyHomePage(driver);

				System.out.println("Step 1 : Login to EM as Kalkomey Administration at : " + Constants.ApplicationURL_EM +"/login/login");
				Reporter.log("Step 1 : Login to EM as Kalkomey Administration at : " + Constants.ApplicationURL_EM +"/login/login"); 
					
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
					
					login.EnterUsername(Constants.EM_Admin_Username);
					login.EnterPassword(Constants.EM_Admin_Password);
					login.ClickOnLogInButton();
					Thread.sleep(5000);
					
				System.out.println("Step 2 : Verify Agencies are displayed");
				Reporter.log("Step 2 : Verify Agencies are displayed");
					
					if(agencyhome.agenciesTitle.isDisplayed())
					{
						System.out.println("Success !!! Agencies are displayed on Home page ");
						Reporter.log("Success !!! Agencies are displayed on Home page ");
					}
					else
					{
						System.out.println("Failure !!! Agencies are not displayed on Home page  ");
						Reporter.log("Failure !!! Agencies are not displayed on Home page ");
						AssertFailedCount++;
					}
				
					
				System.out.println("Step 3 : Verify Missouri Department of Conservation is not displayed before clicking show Inactive Agencies button");
				Reporter.log("Step 3 : Verify Missouri Department of Conservation is not displayed before clicking show Inactive Agencies button");
				
					String actual= agencyhome.showOrHideInactiveAgnecies.getText();
					String expected = "Show Inactive Agencies";
					if (actual != expected)
					{
						agencyhome.showOrHideInactiveAgnecies.click();
						Thread.sleep(4000);
					}
					
					if(SeleniumFunc.IsElementPresent(agencyhome.inactiveMissouri))
					{
						System.out.println("Success !!! Missouri Department of Conservation is not present before clicking show Inactive Agencies button ");
						Reporter.log("Success !!! Missouri Department of Conservation is not present before clicking show Inactive Agencies button");
					}
					else
					{
						System.out.println("Failure !!! Missouri Department of Conservation is present before clicking show Inactive Agencies button ");
						Reporter.log("Failure !!! Missouri Department of Conservation is present before clicking show Inactive Agencies button");
						AssertFailedCount++;
					}
			
				System.out.println("Step 4 : Click on show Inactive Agencies button & Verify Missouri Department of Conservation is displayed or not");
				Reporter.log("Step 4 : Click on show Inactive Agencies button & Verify Missouri Department of Conservation is displayed or not");
					
					agencyhome.showOrHideInactiveAgnecies.click();
					Thread.sleep(4000);
				
					if(SeleniumFunc.IsElementPresent(agencyhome.inactiveMissouri))
					{
						System.out.println("Failure !!! Missouri Department of Conservation is not present after clicking show Inactive Agencies button ");
						Reporter.log("Failure !!! Missouri Department of Conservation is not present before clicking show Inactive Agencies button");
						AssertFailedCount++;
					}
					else
					{
						System.out.println("Success !!! Missouri Department of Conservation is present before clicking show Inactive Agencies button ");
						Reporter.log("Success !!! Missouri Department of Conservation is present before clicking show Inactive Agencies button");
					}
					
				System.out.println("Step 5 : Click on Hide Inactive Agencies button & Verify Missouri Department of Conservation is displayed or not");
				Reporter.log("Step 5 : Click on Hide Inactive Agencies button & Verify Missouri Department of Conservation is displayed or not");
					
					agencyhome.showOrHideInactiveAgnecies.click();
					Thread.sleep(4000);
				
					if(SeleniumFunc.IsElementPresent(agencyhome.inactiveMissouri))
					{
						System.out.println("Success !!! Missouri Department of Conservation is not present before clicking show Inactive Agencies button ");
						Reporter.log("Success !!! Missouri Department of Conservation is not present before clicking show Inactive Agencies button");
					}
					else
					{
						System.out.println("Failure !!! Missouri Department of Conservation is not present before clicking show Inactive Agencies button ");
						Reporter.log("Failure !!! Missouri Department of Conservation is not present before clicking show Inactive Agencies button");
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
			
			
			
			 /*Test : EM > In Search events verify there is no Agency dropdown option
			 */
			@Test
			private void SearchEvent_NoAgencyOption() throws Exception
			{

				System.out.println("====" + "\n" +
							"Test 6 : EM > In Search events verify there is no Agency dropdown option"  + "\n" +"====");
				Reporter.log("====" + "\n" +
				 			  "Test 6 : EM > In Search events verify there is no Agency dropdown option"  + "\n" +  "====");	
				
				int AssertFailedCount=0 ;
				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				LoginPage login = new LoginPage(driver);
				AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);			
				AgencyHomePage agencyhome= new AgencyHomePage(driver);

				System.out.println("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
				Reporter.log("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
					
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
					
					login.EnterUsername(Constants.EM_Admin_Username);
					login.EnterPassword(Constants.EM_Admin_Password);
					login.ClickOnLogInButton();
					Thread.sleep(5000);
			
					//Go to EM and set Payment Off
					
					SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
					Thread.sleep(5000);
					agencyhome.ClickToSelectProgram();
					Thread.sleep(5000);
					
				System.out.println("Step 2 : Go to Search event page");
				Reporter.log("Step 2 : Go to Search event page"); 			
						
					agencyhome.EventSearch.click();
					Thread.sleep(4000);
						
				System.out.println("Step 3 : verify there is no Agency dropdown option");
				Reporter.log("Step 3 : verify there is no Agency dropdown option"); 									
					
					Select dropdownvalues=new Select(searchevent.SearchOption);
					List<WebElement> options = dropdownvalues.getOptions();
				    
					int flag=0;
					
					for(int i=0;i<options.size();i++)
					{				
						if(options.get(i).getText().trim().equalsIgnoreCase("agency"))
						{   
							System.out.println("Failure!!! Agency option is available in Options");
							Reporter.log("Failure!!! Agency option is available in Options");
							AssertFailedCount++;
							break;
						}
						else
						{
							flag=1;					
						}
					}
				
					if (flag==1)
					{
						System.out.println("Success!!! Agency option is Not available in Options");
						Reporter.log("Success!!! Agency option is Not available in Options");
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

				
			
			
			/* Test 1: 
			 * Send a message modal on Event Admin Page.
			 */ 
			@Test
			private void SendMessageEventAdmin() throws Exception
			{
				System.out.println("====" + "\n" +
							"Test 1 : Send a message modal on Event Admin Page."  + "\n" +
				 			"====");
				Reporter.log("====" + "\n" +
				 			  "Test 1 : Send a message modal on Event Admin Page."  + "\n" +
						 	  "====");	
				
				int AssertFailedCount=0 ;
				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				LoginPage login = new LoginPage(driver);
				InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
				InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
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
					
					instructorcreateevent.EnterSpecialInstruction("Children under the age of 12 must be accompanied by an adult.");

					instructorcreateevent.CreateEvent.click();
					Thread.sleep(5000);

					System.out.println("Step 4 : Click on instructor email ID & Subject of Send a message");
					Reporter.log("Step 4 : Click on instructor email ID & Subject of Send a message"); 
					
						SeleniumFunc.ClickOnElement("css", "#send-message-link");
						Thread.sleep(4000);
						
												
						//Verify Subject
						String expected ="Information about Demo Hunter";
						String actual =SeleniumFunc.GetAttributeValue("css", "#send-message div:nth-of-type(2) input", "value");
						
						if(actual.contains(expected.trim()))
						{
							System.out.println("Success!!! Subject matches the expectation : "+actual);
							Reporter.log("Success!!! Subject matches the expectation : "+actual);
						}
						else
						{
							System.out.println("Failure !!Subject does not match expectation." + "\n" + "Expected : "  
									+ "\n" + expected + "\n" + 
									 "Actual: " + "\n" +  actual);
							Reporter.log("Failure !!Subject does not match expectation." + "\n" + "Expected : "  
									+ "\n" + expected + "\n" + 
									"Actual: " + "\n" +  actual);
				
							AssertFailedCount++;
						}
						
								
					System.out.println("Step 5 : Fill up Send Us An Email Form & Submit");
					Reporter.log("Step 5 : Fill up Send Us An Email Form & Submit"); 				
						
						SeleniumFunc.EnterValueInTextbox("css", ".span12 textarea[name*='message']", "Automation Data");
						SeleniumFunc.ClickOnElement("css", "#send-message-btn");
						Thread.sleep(2000);
						
					System.out.println("Step 6 : Verify Success message");
					Reporter.log("Step 6 : Verify Success message"); 	
						
						expected = "Message sent!";
						
						actual = SeleniumFunc.GetElementText("css", ".span12.alert.alert-success");     
						
						if(actual.contains(expected))
						{
							System.out.println("Success !! Mail sent successfully.");
							Reporter.log("Success !! Mail sent successfully.");
						}
						else
						{
							System.out.println("Failure !! There is problem while sending mail."+ "\n" + "Expected : "  
									+ "\n" + expected + "\n" + 
									 "Actual: " + "\n" +  actual);
							Reporter.log("Failure !! There is problem while sending mail."+ "\n" + "Expected : "  
									+ "\n" + expected + "\n" + 
									 "Actual: " + "\n" +  actual);
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
				




	/* Test 1: 
			 * EM > Placeholders Disappear
			*/ 
			@Test
			private void PlaceHolderDisappears() throws Exception
			{
				System.out.println("====" + "\n" +
						"Test 1 : EM > Placeholders Disappear"  + "\n" +
			 			"====");
				Reporter.log("====" + "\n" +
			 			 "Test 1 : EM > Placeholders Disappear"  + "\n" +
					 	 "====");	
			
				int AssertFailedCount=0 ;
				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				LoginPage login = new LoginPage(driver);
				AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);
				AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
				
				System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
				Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
				
					
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
					Thread.sleep(5000);
				
				System.out.println("Step 2 : Logging in application");
				Reporter.log("Step 2 : Logging in application"); 
									
					login.EnterUsername(Constants.EM_CLInstructor_Username);
					login.EnterPassword(Constants.EM_CLInstructor_Password);
					login.ClickOnLogInButton();
					Thread.sleep(8000);
				
				System.out.println("Step 3 : Navigation to Users listing Page");
				Reporter.log("Step 3 : Navigation to Users listing Page"); 
				
					agency.ClickOnUserButton();
					Thread.sleep(5000);
				
				System.out.println("Step 4 : Verify placeholder value for Name option of user search");
				Reporter.log("Step 4 : Select different options for search and verify placeholder value"); 	
				
					//Verified placeholder for name
					searchevent.SelectSearchTypeByVisibleText("Name");

					String expectedtext = "Enter the user's name.";
					String actualtext = SeleniumFunc.GetAttributeValue("css", "#search-type-name", "placeholder");
					
					if(actualtext.contains(expectedtext))
					{		
						System.out.println("Success !! Placeholder is having correct value for name option");
						Reporter.log("Success !! Placeholder is having correct value for name option");

					}
					else
					{
						System.out.println("Failure !! Placeholder is having incorrect value for name option" + "\n" + "Expected : "  
												+ "\n" + expectedtext + "\n" + 
												"Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! Placeholder is having incorrect value for name option" + "\n" + "Expected : "  
												+ "\n" + expectedtext + "\n" + 
												"Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
									
					driver.navigate().back();
					Thread.sleep(5000);
					
					SeleniumFunc.ClickOnElement("css", ".nav.nav-list li:nth-of-type(1) a");
					Thread.sleep(5000);

					SeleniumFunc.ClickOnElement("linkText", "Search");
					Thread.sleep(5000);
					
				System.out.println("Step 5 : Verify placeholder value for Name option of event search");
				Reporter.log("Step 5 : Verify placeholder value for Name option of event search"); 			
				
					//Verified placeholder for name
					searchevent.SelectSearchTypeByVisibleText("Name");

					expectedtext = "Enter the event name.";
					actualtext = SeleniumFunc.GetAttributeValue("css", "#search-type-name", "placeholder");
						
					if(actualtext.contains(expectedtext))
					{		
						System.out.println("Success !! Placeholder is having correct value for name option");
						Reporter.log("Success !! Placeholder is having correct value for name option");
					}
					else
					{
							System.out.println("Failure !! Placeholder is having incorrect value for name option" + "\n" + "Expected : "  
													+ "\n" + expectedtext + "\n" + 
													"Actual: " + "\n" +  actualtext);
							Reporter.log("Failure !! Placeholder is having incorrect value for name option" + "\n" + "Expected : "  
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
				
			
			// On viewing a student's registration,For valid Phone number Student should see 'Pending' in Text Message Preference
			
			 @Test
			   private void EM_SMSPreferencesForPending() throws Exception
			   {
			       System.out.println("====" + "\n" + "Test  : On viewing a student's registration,For Valid Phone number Student should see 'Pending' in Text Message Preference");
				   Reporter.log("====" + "\n" + "Test  : On viewing a student's registration,For Valid Phone number Student should see 'Pending' in Text Message Preference");	
						
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
					AgencyHomePage agencyhome= new AgencyHomePage(driver);
					EMStudentRegistrationPage regStud =new EMStudentRegistrationPage(driver);
					
					System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
					Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
							
						SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");				
						login.EnterUsername(Constants.EM_Admin_Username);
						login.EnterPassword(Constants.EM_Admin_Password);
						login.ClickOnLogInButton();
						Thread.sleep(5000);

					
					System.out.println("Step 2 : Select agency from agency listing");
					Reporter.log("Step 2 : Select agency from agency listing"); 
							
									
						SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
						Thread.sleep(5000);
							
					System.out.println("Step 3 : Select a program and click on create event");
					Reporter.log("Step 3 : Select a program and click on create event"); 
									
					  	agencyhome.ClickToSelectProgram();
						Thread.sleep(5000);

						SeleniumFunc.ClickOnElement("css", ".nav.nav-list>li:nth-of-type(6)>a");
						Thread.sleep(4000);
							
						
					System.out.println("Step 4 : On Create Event page , entering all required details and click on 'Create Event' button");
					Reporter.log("Step 4 : On Create Event page , entering all required detailsb and click on 'Create Event' button"); 
						
						
						instructorcreateevent.SelectProgram2();
						Thread.sleep(2000);
								
						instructorcreateevent.Location.clear();
						instructorcreateevent.Location.sendKeys("Demo Agency Location");
						Thread.sleep(5000);
						instructorcreateevent.Location_FirstChoice.click();
						Thread.sleep(2000);
						
						/*instructorcreateevent.GoToNextMonth();*/
						instructorcreateevent.SelectStartDate("6", "30", "PM");
						instructorcreateevent.SelectEndDate("7", "30", "PM");
								
						instructorcreateevent.AddEventDate.click();
						Thread.sleep(2000);
						
						instructorcreateevent.EnterEventCapacity("2");
						instructorcreateevent.EnterWaitListCapacity("1");
								
						instructorcreateevent.CreateEvent.click();
						Thread.sleep(5000);

						String URL = driver.getCurrentUrl();
						System.out.println("Url :" +URL);
						
				System.out.println("Step 5 : Activate event & goto Event roster page");
				Reporter.log("Step 5 : Activate event & goto Event roster page");
						
						SeleniumFunc.ClickOnElement("css", ".btn.mtt");
						Thread.sleep(4000);
						// Event admin URL
						String eventAdminURL = driver.getCurrentUrl();
						
						instructoreventroster.ReturnToRosterEventAdmin.click();
						Thread.sleep(4000);						
						//ROSTER page URL
						String eventRosterURL=driver.getCurrentUrl();
							
				System.out.println("Step 6 : Add student for the event");
				Reporter.log("Step 6 : Add student for the event"); 							

						instructoreventroster.SelectAction("Add Student");
						Thread.sleep(5000);

						String username="emstudent"+ JH.GenerateRandomNumber();
						String emailaddress = username + "@mailinator.com";
						addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "236-787-7777", "12345");
						addstud.SelectEyeColor();
						addstud.SelectHairColor();
						addstud.EnterSSNNO("125896336");
						addstud.ClickOnRegisterButton();
						Thread.sleep(5000);	
					
						String SearchEventPage = driver.getCurrentUrl();
						
				System.out.println("Step 7 : Select student in View");
				Reporter.log("Step 7 : Select student in View");
					
						instructoreventroster.ClickOnSelectFirstStudent();
						Thread.sleep(4000);
						instructoreventroster.ClickOnViewRegistrationButton();
						Thread.sleep(4000);
						
				System.out.println("Step 8 : Verify Text Message Preference for valid Number");
				Reporter.log("Step 8 : Verify Text Message Preference for valid Number");
				
						for(int i=0;i<2;i++)
						{
							regStud.clickOnPlus();
							Thread.sleep(4000);
						}
						
						
						String textMessagePreference = regStud.TextMessagePreference.getText().trim();
						String expected = "Pending";
						if(textMessagePreference.equalsIgnoreCase(expected))
						{
							System.out.println("Success!!! Text Message Preference is matched :"+expected);
							Reporter.log("Success!!! Text Message Preference is matched :"+expected);
						}
						else
						{
							System.out.println("Failure!!! Text Message Preference is not matched Expected:"+expected+" Actual :"+textMessagePreference);
							Reporter.log("Failure!!! Text Message Preference is not matched Expected:"+expected+" Actual:"+textMessagePreference);
							AssertFailedCount++;
						}
						
						
						try {
							SeleniumFunc.ToGoToUrl(eventRosterURL);
							Thread.sleep(5000);

							//Going to Event Roster page and removing student and deleting event 
							//so new event can be added with same test data
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

							
							Thread.sleep(5000);
						} catch (Exception e) {
							
						}
						
						if(AssertFailedCount>0)	
						{
						
						//Marking this test as Failed
						
							System.out.println("---- Test Failed. Please check the console or TestNg report for details");
							Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
						
							Assert.fail();
						}
			   }
			 
			 
			// On viewing a student's registration,For invalid Phone number Student should display validation Message 
				
			 @Test
			   private void EM_SMSPreferencesForNA() throws Exception
			   {
			       System.out.println("====" + "\n" + "Test  : On viewing a student's registration,For invalid Phone number Student should see 'N/A' in Text Message Preference");
				   Reporter.log("====" + "\n" + "Test  : On viewing a student's registration,For invalid Phone number Student should see 'N/A' in Text Message Preference");	
						
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
					AgencyHomePage agencyhome= new AgencyHomePage(driver);
					EMStudentRegistrationPage regStud =new EMStudentRegistrationPage(driver);
					
					System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
					Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
							
						SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");				
						login.EnterUsername(Constants.EM_Admin_Username);
						login.EnterPassword(Constants.EM_Admin_Password);
						login.ClickOnLogInButton();
						Thread.sleep(5000);

					
					System.out.println("Step 2 : Select agency from agency listing");
					Reporter.log("Step 2 : Select agency from agency listing"); 
							
									
						SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
						Thread.sleep(5000);
							
					System.out.println("Step 3 : Select a program and click on create event");
					Reporter.log("Step 3 : Select a program and click on create event"); 
									
					  	agencyhome.ClickToSelectProgram();
						Thread.sleep(5000);

						SeleniumFunc.ClickOnElement("css", ".nav.nav-list>li:nth-of-type(6)>a");
						Thread.sleep(4000);
							
						
					System.out.println("Step 4 : On Create Event page , entering all required details and click on 'Create Event' button");
					Reporter.log("Step 4 : On Create Event page , entering all required detailsb and click on 'Create Event' button"); 
						
						
						instructorcreateevent.SelectProgram2();
						Thread.sleep(2000);
								
						instructorcreateevent.Location.clear();
						instructorcreateevent.Location.sendKeys("Demo Agency Location");
						Thread.sleep(5000);
						instructorcreateevent.Location_FirstChoice.click();
						Thread.sleep(8000);
						
						/*instructorcreateevent.GoToNextMonth();*/
						instructorcreateevent.SelectStartDate("6", "30", "PM");
						instructorcreateevent.SelectEndDate("7", "30", "PM");
								
						instructorcreateevent.AddEventDate.click();
						Thread.sleep(2000);
						
						instructorcreateevent.EnterEventCapacity("2");
						instructorcreateevent.EnterWaitListCapacity("1");
								
						instructorcreateevent.CreateEvent.click();
						Thread.sleep(5000);

						String URL = driver.getCurrentUrl();
						System.out.println("Url :" +URL);
						
				System.out.println("Step 5 : Activate event & goto Event roster page");
				Reporter.log("Step 5 : Activate event & goto Event roster page");
						
						SeleniumFunc.ClickOnElement("css", ".btn.mtt");
						Thread.sleep(4000);
						// Event admin URL
						String eventAdminURL = driver.getCurrentUrl();
						
						instructoreventroster.ReturnToRosterEventAdmin.click();
						Thread.sleep(4000);						
						//ROSTER page URL
						String eventRosterURL=driver.getCurrentUrl();
							
				System.out.println("Step 6 : Add student for the event");
				Reporter.log("Step 6 : Add student for the event"); 							
						
						
						instructoreventroster.SelectAction("Add Student");
						Thread.sleep(5000);

						String username="emstudent"+ JH.GenerateRandomNumber();
						String emailaddress = username + "@mailinator.com";
						addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "123", "12345");
						addstud.SelectEyeColor();
						addstud.SelectHairColor();
						addstud.EnterSSNNO("125896336");
						//SeleniumFunc.ClickOnElement("id", "offline_payment");
						addstud.ClickOnRegisterButton();
						Thread.sleep(5000);	
						/*
						String SearchEventPage = driver.getCurrentUrl();
						
				System.out.println("Step 7 : Select student in View");
				Reporter.log("Step 7 : Select student in View");
					
						instructoreventroster.ClickOnSelectFirstStudent();
						Thread.sleep(4000);
						instructoreventroster.ClickOnViewRegistrationButton();
						Thread.sleep(4000);*/
						
				System.out.println("Step 8 : Verify Text Message Preference for In-valid Number");
				Reporter.log("Step 8 : Verify Text Message Preference for In-valid Number");
				
						String expected = "Phone number must be ten numbers, and cannot start with 0 or 1.";
						String textMessagePreference = SeleniumFunc.GetElementText("css", ".help-block.error");
				/*
						for(int i=0;i<2;i++)
						{
							regStud.clickOnPlus();
							Thread.sleep(4000);
						}
						
						
						String textMessagePreference = regStud.TextMessagePreference.getText().trim();
						String expected = "N/A";
						if(textMessagePreference.equalsIgnoreCase(expected))*/
						if(textMessagePreference.equalsIgnoreCase(expected))
						{
							System.out.println("Success!!! Text Message Preference is matched :"+expected);
							Reporter.log("Success!!! Text Message Preference is matched :"+expected);
						}
						else
						{
							System.out.println("Failure!!! Text Message Preference is not matched Expected:"+expected+" Actual :"+textMessagePreference);
							Reporter.log("Failure!!! Text Message Preference is not matched Expected:"+expected+" Actual:"+textMessagePreference);
							AssertFailedCount++;
						}
						
						System.out.println("Step 9 : Enter valid phone Number");
						Reporter.log("Step 9 : Enter valid Phone Number");
						
						SeleniumFunc.DeleteValueFromTextbox("css", "#Registration_p_phone");
						SeleniumFunc.EnterValueInTextbox("css", "#Registration_p_phone", "789-789-6789");
						
						addstud.ClickOnRegisterButton();
						Thread.sleep(5000);
						
						System.out.println("Step 10 : Verify User able to register with valid phone Number");
						Reporter.log("Step 8 : Verify User able to register with valid phone Number");
						
						instructoreventroster.ClickOnSelectFirstStudent();
						Thread.sleep(4000);
						instructoreventroster.ClickOnViewRegistrationButton();
						Thread.sleep(4000);
						
						String expectedPhone = "(789) 789-6789";
						String actual = SeleniumFunc.GetElementText("css", "#page > div.content.clearfix > div:nth-child(3) > div.span8 > div.row-fluid.tooltip-container > dl:nth-child(1) > dd");
						
						if(actual.equalsIgnoreCase(expectedPhone))
						{
							System.out.println("Success!!! User able to register with valid phone number: "+expectedPhone);
							Reporter.log("Success!!! User able to register with valid phone number: "+expectedPhone);
						}
						else
						{
							System.out.println("Failure!!! Invalid Phone Number format :"+expectedPhone+" Actual :"+actual);
							Reporter.log("Failure!!! Invalid Phone Number format : "+expectedPhone+" Actual:"+actual);
							AssertFailedCount++;
						}
						
						try {
							SeleniumFunc.ToGoToUrl(eventRosterURL);
							Thread.sleep(5000);

							//Going to Event Roster page and removing student and deleting event 
							//so new event can be added with same test data
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

							
							Thread.sleep(5000);
						} catch (Exception e) {
							
						}
						
						if(AssertFailedCount>0)	
						{
						
						//Marking this test as Failed
						
							System.out.println("---- Test Failed. Please check the console or TestNg report for details");
							Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
						
							Assert.fail();
						}
			   }
						
			 
			// EM > On viewing a student's registration, Test Collapsible Registration Sections
			 
			 @Test
			   private void EM_StudentRegistrationCollapsibleSections() throws Exception
			   {
			       System.out.println("====" + "\n" + "Test  : EM > On viewing a student's registration, Test Collapsible Registration Sections");
				   Reporter.log("====" + "\n" + "Test  : EM > On viewing a student's registration, Test Collapsible Registration Sections");	
						
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
					AgencyHomePage agencyhome= new AgencyHomePage(driver);
					EMStudentRegistrationPage regStud =new EMStudentRegistrationPage(driver);
					
					System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
					Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
							
						SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");				
						login.EnterUsername(Constants.EM_Admin_Username);
						login.EnterPassword(Constants.EM_Admin_Password);
						login.ClickOnLogInButton();
						Thread.sleep(5000);

					
					System.out.println("Step 2 : Select agency from agency listing");
					Reporter.log("Step 2 : Select agency from agency listing"); 
							
									
						SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
						Thread.sleep(5000);
							
					System.out.println("Step 3 : Select a program and click on create event");
					Reporter.log("Step 3 : Select a program and click on create event"); 
									
					  	agencyhome.ClickToSelectProgram();
						Thread.sleep(5000);

						SeleniumFunc.ClickOnElement("css", ".nav.nav-list>li:nth-of-type(6)>a");
						Thread.sleep(4000);
							
						
					System.out.println("Step 4 : On Create Event page , entering all required details and click on 'Create Event' button");
					Reporter.log("Step 4 : On Create Event page , entering all required detailsb and click on 'Create Event' button"); 
						
						
						instructorcreateevent.SelectProgram2();
						Thread.sleep(2000);
								
						instructorcreateevent.Location.clear();
						instructorcreateevent.Location.sendKeys("Demo Agency Location");
						Thread.sleep(5000);
						instructorcreateevent.Location_FirstChoice.click();
						Thread.sleep(2000);
						
						/*instructorcreateevent.GoToNextMonth();*/
						instructorcreateevent.SelectStartDate("6", "30", "PM");
						instructorcreateevent.SelectEndDate("7", "30", "PM");
								
						instructorcreateevent.AddEventDate.click();
						Thread.sleep(2000);
						
						instructorcreateevent.EnterEventCapacity("2");
						instructorcreateevent.EnterWaitListCapacity("1");
								
						instructorcreateevent.CreateEvent.click();
						Thread.sleep(5000);
						
						String URL = driver.getCurrentUrl();
						System.out.println("Url :" +URL);
						
				System.out.println("Step 5 : Activate event & goto Event roster page");
				Reporter.log("Step 5 : Activate event & goto Event roster page");
						
						SeleniumFunc.ClickOnElement("css", ".btn.mtt");
						Thread.sleep(4000);
						// Event admin URL
						String eventAdminURL = driver.getCurrentUrl();
						
						instructoreventroster.ReturnToRosterEventAdmin.click();
						Thread.sleep(4000);						
						//ROSTER page URL
						String eventRosterURL=driver.getCurrentUrl();
							
				System.out.println("Step 6 : Add student for the event");
				Reporter.log("Step 6 : Add student for the event"); 							

						instructoreventroster.SelectAction("Add Student");
						Thread.sleep(5000);

						String username="emstudent"+ JH.GenerateRandomNumber();
						String emailaddress = username + "@mailinator.com";
						addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "919-999-9999", "12345");
						addstud.SelectEyeColor();
						addstud.SelectHairColor();
						addstud.EnterSSNNO("125896336");
						
						addstud.ClickOnRegisterButton();
						Thread.sleep(5000);	
					
						String SearchEventPage = driver.getCurrentUrl();
						
				System.out.println("Step 7 : Select student in View");
				Reporter.log("Step 7 : Select student in View");
					
						instructoreventroster.ClickOnSelectFirstStudent();
						Thread.sleep(4000);
						instructoreventroster.ClickOnViewRegistrationButton();
						Thread.sleep(4000);
						
				System.out.println("Step 8 : Verify sections are present & Collapsible");
				Reporter.log("Step 8 : Verify sections are present & Collapsible ");
								
							if(SeleniumFunc.IsElementPresent(regStud.additionalInformation))
							{
								System.out.println("Success!!! Additional Information section is present");
								Reporter.log("Success!!! Additional Information section is present");
								regStud.clickOnPlus();
								Thread.sleep(4000);
								System.out.println("Success!!! Additional Information is collapsible after clicking Plus icon");
								Reporter.log("Success!!! Additional Information is collapsible after clicking Plus icon");		
							}
							else
							{
								System.out.println("Failure!!! Additional Information section is not present");
								Reporter.log("Failure!!! Additional Information section not present");
								System.out.println("Failure!!! Additional Information is not collapsible after clicking Plus icon");
								Reporter.log("Failure!!! Additional Information is not collapsible after clicking Plus icon");
								AssertFailedCount++;
							}
							
							if(SeleniumFunc.IsElementPresent(regStud.systemInformation))
							{
								System.out.println("Success!!! System Information section is present");
								Reporter.log("Success!!! System Information section is present");
								regStud.clickOnPlus();
								Thread.sleep(4000);
								System.out.println("Success!!! System Information is collapsible after clicking Plus icon");
								Reporter.log("Success!!! System Information is collapsible after clicking Plus icon");
							}
							else
							{
								System.out.println("Failure!!! System Information section is not present");
								Reporter.log("Failure!!! System Information section is not present");
								System.out.println("Failure!!! System Information is not collapsible after clicking Plus icon");
								Reporter.log("Failure!!! System Information is not collapsible after clicking Plus icon");
								AssertFailedCount++;
							}

						
						if(SeleniumFunc.IsElementPresent(regStud.comment))
						{
							System.out.println("Success!!! Comment section is present");
							Reporter.log("Success!!! Comment section is present");
							System.out.println("Success!!! Comment is collapsible after clicking Plus icon");
							Reporter.log("Success!!! Comment is collapsible after clicking Plus icon");
						}
						else
						{
							System.out.println("Faliure!!! Comment section is not present");
							Reporter.log("Faliure!!! Comment section is not present");
							System.out.println("Faliure!!! Comment is not collapsible after clicking Plus icon");
							Reporter.log("Faliure!!! Comment is not collapsible after clicking Plus icon");
							AssertFailedCount++;
						}
						
						for(int i=0;i<3;i++)
						{
							regStud.clickOnminus();
							Thread.sleep(4000);
						}
						
						System.out.println("Success!!! All plus signs are changed to minus & sections hidden");
						Reporter.log("Success!!! All plus signs are are changed to minus & sections hidden");
							
						
						try {
							SeleniumFunc.ToGoToUrl(eventRosterURL);
							Thread.sleep(5000);

							//Going to Event Roster page and removing student and deleting event 
							//so new event can be added with same test data
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

							
							Thread.sleep(5000);
						} catch (Exception e) {
							
						}
						
						if(AssertFailedCount>0)	
						{
						
						//Marking this test as Failed
						
							System.out.println("---- Test Failed. Please check the console or TestNg report for details");
							Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
						
							Assert.fail();
						}
			   }
			 
			 
			 /*Test : 
				 * EM > Show Race/Ethnicity in view of student Results
				 */
				@Test
				private void VerifyRaceAndEthnicityInStudentResults() throws Exception
				{

					System.out.println("====" + "\n" +
								"Test  : Verify wheather Shows Race/Ethnicity in view Results"  + "\n" +"====");
					Reporter.log("====" + "\n" +
					 			  "Test  : Verify wheather Shows Race/Ethnicity in view Results"  + "\n" +  "====");	
					
					int AssertFailedCount=0 ;
					SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
					AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
					LoginPage login = new LoginPage(driver);
					InstructorEventRosterPage roster=new InstructorEventRosterPage(driver);
					StudentResultPage result = new StudentResultPage(driver);
					InstructorSearchPage search=new InstructorSearchPage(driver);
					AgencyHomePage agencyhome= new AgencyHomePage(driver);

					System.out.println("Step 1 : Login to EM as Kalkomey Administration at : " + Constants.ApplicationURL_EM +"/login/login");
					Reporter.log("Step 1 : Login to EM as Kalkomey Administration at : " + Constants.ApplicationURL_EM +"/login/login"); 
						
						SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
						
						login.EnterUsername(Constants.EM_Admin_Username);
						login.EnterPassword(Constants.EM_Admin_Password);
						login.ClickOnLogInButton();
						Thread.sleep(5000);
						
					System.out.println("Step 2 : Select Program 1 in Arizona GFD Outdoor Skills Network Agency");
					Reporter.log("Step 2 : Select Program 1 in Arizona GFD Outdoor Skills Network Agency");
							
						SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
						Thread.sleep(5000);
						SeleniumFunc.ClickOnElement("linkText", "Demo Hunter Education Field Day");
						Thread.sleep(5000);
					
					System.out.println("Step 3 : Select Search & search for All option");
					Reporter.log("Step 3 :  Select Search & search for All option");
					
						agencyhome.EventSearch.click();
						Thread.sleep(4000);
						//searchevent.SelectSearchTypeByVisibleText("All");
						//SeleniumFunc.SelectValueFromDropdownList("css", "div.btn-group.bootstrap-select.span3.col-ignore > button > span.filter-option.pull-left", "Upcoming");
						search.SelectSearchTypeByVisibleText("ID");
						Thread.sleep(3000);
						SeleniumFunc.EnterValueInTextbox("css", "#search-type-id", "54278");
						searchevent.SearchButton2.click();
						Thread.sleep(4000);
						
					System.out.println("Step 4 : Select a student registered for program. Click the event's name.");
					Reporter.log("Step 4 : Select a student registered for program. Click the event's name.");
						
						SeleniumFunc.ClickOnElement("css", " td:nth-child(2) > a");
						Thread.sleep(4000);
					
						roster.ViewResult.click();
						Thread.sleep(4000);
						
				    System.out.println("Step 5 : Verify Demographic Information section displayed or not.");
					Reporter.log("Step 5 : Verify Demographic Information section displayed or not.");
						
						if (result.DemographicInformation.isDisplayed())
							{
								System.out.println("Success !!! Demographic Information is displayed. ");
								Reporter.log("Success !!! Demographic Information is displayed.");
								
							}
							else
							{
								System.out.println("Failure !!! Demographic Information is not displayed. ");
								Reporter.log("Failure !!! Demographic Information is not displayed.");
								AssertFailedCount++;
							}
						
					System.out.println("Step 6 : Verify Race/Ethnicity section displayed or not.");
					Reporter.log("Step 6 : Verify Race/Ethnicity section displayed or not.");
							
						if (result.option1.isDisplayed())
							{
									System.out.println("Success !!! option1 is displayed. ");
									Reporter.log("Success !!! option1 is displayed.");
									
							}
							else
							{
									System.out.println("Failure !!! option1 is not displayed. ");
									Reporter.log("Failure !!! option1 is not displayed.");
									AssertFailedCount++;
							}
						
						if (result.option2.isDisplayed())
						{
							System.out.println("Success !!! option2 is displayed. ");
							Reporter.log("Success !!! option2 is displayed.");
							
						}
						else
						{
							System.out.println("Failure !!! option2 is not displayed. ");
							Reporter.log("Failure !!! option2 is not displayed.");
							AssertFailedCount++;
						}
						
						if (result.option3.isDisplayed())
						{
							System.out.println("Success !!! option3 is displayed. ");
							Reporter.log("Success !!! option3 is displayed.");
							
						}
						else
						{
							System.out.println("Failure !!! option3 is not displayed. ");
							Reporter.log("Failure !!! option3 is not displayed.");
							AssertFailedCount++;
						}
						
					System.out.println("Step 7 : Verify Gender section displayed or not.");
					Reporter.log("Step 7 : Verify Gender section displayed or not.");
								
						if (result.male.isDisplayed())
						{
							System.out.println("Success !!! male is displayed. ");
							Reporter.log("Success !!! male is displayed.");
										
						}
						else
						{
							System.out.println("Failure !!! male is not displayed. ");
							Reporter.log("Failure !!! male is not displayed.");
							AssertFailedCount++;
						}
						
						if (result.female.isDisplayed())
						{
							System.out.println("Success !!! female is displayed. ");
							Reporter.log("Success !!! female is displayed.");
								
						}
						else
						{
							System.out.println("Failure !!! female is not displayed. ");
							Reporter.log("Failure !!! female is not displayed.");
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
				
				
				
				/*Test : 
				 * Verify Inactive programs hidden on user view/edit page for NY Admin
				 */
				@Test
				private void NYAdminInactivePrograms() throws Exception
				{

					System.out.println("====" + "\n" +
								"Test  : Verify Inactive programs hidden on user view/edit page for NY Admin"  + "\n" +"====");
					Reporter.log("====" + "\n" +
					 			  "Test  : Verify Inactive programs hidden on user view/edit page for NY Admin"  + "\n" +  "====");	
					
					int AssertFailedCount=0 ;
					SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
					AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
					LoginPage login = new LoginPage(driver);
					InstructorEventRosterPage roster=new InstructorEventRosterPage(driver);
					StudentResultPage result = new StudentResultPage(driver);
					
					AgencyHomePage agencyhome= new AgencyHomePage(driver);

					System.out.println("Step 1 : Login to EM as NY Administration at : " + Constants.ApplicationURL_EM +"/login/login");
					Reporter.log("Step 1 : Login to EM as NY Administration at : " + Constants.ApplicationURL_EM +"/login/login"); 
						
						SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
						
						login.EnterUsername(Constants.EM_NYAdmin_Username);
						login.EnterPassword(Constants.EM_NYAdmin_Password);
						login.ClickOnLogInButton();
						Thread.sleep(5000);
						
					System.out.println("Step 2 : Select User from the Agency Users ");
					Reporter.log("Step 2 : Select User from the Agency Users ");
							
						SeleniumFunc.ClickOnElement("linkText", "Users");
						Thread.sleep(5000);
						SeleniumFunc.ClickOnElement("xpath", ".//*[@id='user_select_form']/table/tbody/tr[1]/td[2]/a/span");
						Thread.sleep(5000);
					
										
					System.out.println("Step 4 : Verify 'Show Inactive Programs' before clicking on Button");
					Reporter.log("Step 4 : Verify 'Show Inactive Programs' before clicking on Button");
						
						if (SeleniumFunc.GetElementText("css", "#inactive-program-btn").equalsIgnoreCase("Show Inactive Programs"))
						{
							System.out.println("Success !!! 'Show Inactive Programs' is displayed. ");
							Reporter.log("Success !!! 'Show Inactive Programs' is displayed.");
							SeleniumFunc.ClickOnElement("css", "#inactive-program-btn");
							Thread.sleep(4000);
						}
						else
						{
							System.out.println("Failure !!! 'Show Inactive Programs' is not displayed. ");
							Reporter.log("Failure !!! 'Show Inactive Programs' is not displayed.");
							AssertFailedCount++;
						}
						
				    System.out.println("Step 5 : Verify 'Hide Inactive Programs' after clicking on Button");
					Reporter.log("Step 5 : Verify 'Hide Inactive Programs' after clicking on Button");
						
						if (SeleniumFunc.GetElementText("css", "#inactive-program-btn").equalsIgnoreCase("Hide Inactive Programs"))
						{
							System.out.println("Success !!! 'Hide Inactive Programs' is displayed. ");
							Reporter.log("Success !!! 'Hide Inactive Programs' is displayed.");
							
						}
						else
						{
							System.out.println("Failure !!! 'Hide Inactive Programs' is not displayed. ");
							Reporter.log("Failure !!! 'Hide Inactive Programs' is not displayed.");
							AssertFailedCount++;
						}
						
					System.out.println("Step 6 : Edit the User");
					Reporter.log("Step 6 : Edit the User");
							
						SeleniumFunc.ClickOnElement("linkText", "Edit");
						Thread.sleep(5000);
						
						
					System.out.println("Step 7 : Verify 'Show Inactive Programs' before clicking on Button on Edit page");
					Reporter.log("Step 7 : Verify 'Show Inactive Programs' before clicking on Button on Edit page");
							
						if (SeleniumFunc.GetElementText("css", "#inactive-program-btn").equalsIgnoreCase("Show Inactive Programs"))
						{
							System.out.println("Success !!! 'Show Inactive Programs' is displayed. ");
							Reporter.log("Success !!! 'Show Inactive Programs' is displayed.");
							SeleniumFunc.ClickOnElement("css", "#inactive-program-btn");
							Thread.sleep(4000);
						}
						else
						{
							System.out.println("Failure !!! 'Show Inactive Programs' is not displayed. ");
							Reporter.log("Failure !!! 'Show Inactive Programs' is not displayed.");
							AssertFailedCount++;
						}
							
					System.out.println("Step 8 : Verify 'Hide Inactive Programs' after clicking on Button on Edit page");
					Reporter.log("Step 8 : Verify 'Hide Inactive Programs' after clicking on Button on Edit page");
							
							if (SeleniumFunc.GetElementText("css", "#inactive-program-btn").equalsIgnoreCase("Hide Inactive Programs"))
							{
								System.out.println("Success !!! 'Hide Inactive Programs' is displayed. ");
								Reporter.log("Success !!! 'Hide Inactive Programs' is displayed.");
								
							}
							else
							{
								System.out.println("Failure !!! 'Hide Inactive Programs' is not displayed. ");
								Reporter.log("Failure !!! 'Hide Inactive Programs' is not displayed.");
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
				
				
				// Add comment to registered Student
				
				 @Test
				   private void EM_StudentAddComment() throws Exception
				   {
				       System.out.println("====" + "\n" + "Test  : EM > Add comment to registered Student");
					   Reporter.log("====" + "\n" + "Test  : EM > Add comment to registered Student");	
							
						int AssertFailedCount=0 ;
						SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
						LoginPage login = new LoginPage(driver);
						
						InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
						InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
						AgencyHomePage agencyhome= new AgencyHomePage(driver);
						EMStudentRegistrationPage regStud =new EMStudentRegistrationPage(driver);
						EMAddStudentPage addstud = new EMAddStudentPage(driver);
						
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
								
						System.out.println("Step 3 : Select a program and click on create event");
						Reporter.log("Step 3 : Select a program and click on create event"); 
										
						  	agencyhome.ClickToSelectProgram();
							Thread.sleep(5000);

							SeleniumFunc.ClickOnElement("css", ".nav.nav-list>li:nth-of-type(6)>a");
							Thread.sleep(4000);
								
							
						System.out.println("Step 4 : On Create Event page , entering all required details and click on 'Create Event' button");
						Reporter.log("Step 4 : On Create Event page , entering all required detailsb and click on 'Create Event' button"); 
							
							
							instructorcreateevent.SelectProgram();
							Thread.sleep(2000);
									
							instructorcreateevent.Location.clear();
							instructorcreateevent.Location.sendKeys("Demo Agency Location");
							Thread.sleep(5000);
							instructorcreateevent.Location_FirstChoice.click();
							Thread.sleep(2000);
							
							/*instructorcreateevent.GoToNextMonth();*/
							instructorcreateevent.SelectStartDate("6", "30", "PM");
							instructorcreateevent.SelectEndDate("7", "30", "PM");
									
							instructorcreateevent.AddEventDate.click();
							Thread.sleep(2000);
							
							instructorcreateevent.EnterEventCapacity("2");
							
									
							instructorcreateevent.CreateEvent.click();
							Thread.sleep(5000);
							
					System.out.println("Step 5 : Activate event & goto Event roster page");
					Reporter.log("Step 5 : Activate event & goto Event roster page");
							
							SeleniumFunc.ClickOnElement("css", ".btn.mtt");
							Thread.sleep(4000);
							// Event admin URL
							String eventAdminURL = driver.getCurrentUrl();
							
							instructoreventroster.ReturnToRosterEventAdmin.click();
							Thread.sleep(4000);						
							//ROSTER page URL
							String eventRosterURL=driver.getCurrentUrl();
								
					System.out.println("Step 6 : Add student for the event");
					Reporter.log("Step 6 : Add student for the event"); 							

							instructoreventroster.SelectAction("Add Student");
							Thread.sleep(5000);

							String username="emstudent"+ JH.GenerateRandomNumber();
							String emailaddress = username + "@mailinator.com";
							addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "919-999-9999", "12345");
							addstud.SelectEyeColor();
							addstud.SelectHairColor();
							addstud.EnterSSNNO("125896336");
							addstud.ClickOnRegisterButton();
							Thread.sleep(5000);	
						
						
							
					System.out.println("Step 7 : Select student in View");
					Reporter.log("Step 7 : Select student in View");
						
							instructoreventroster.ClickOnSelectFirstStudent();
							Thread.sleep(4000);
							instructoreventroster.ClickOnViewRegistrationButton();
							Thread.sleep(4000);
							
					System.out.println("Step 8 : Verify sections are present & Collapsible");
					Reporter.log("Step 8 : Verify sections are present & Collapsible ");
								
							
						if(SeleniumFunc.IsElementPresent(regStud.comment))
							{
								System.out.println("Success!!! Comment section is present");
								Reporter.log("Success!!! Comment section is present");
								System.out.println("Success!!! Comment is collapsible after clicking Plus icon");
								Reporter.log("Success!!! Comment is collapsible after clicking Plus icon");
							}
						else
							{
								System.out.println("Faliure!!! Comment section is not present");
								Reporter.log("Faliure!!! Comment section is not present");
								System.out.println("Faliure!!! Comment is not collapsible after clicking Plus icon");
								Reporter.log("Faliure!!! Comment is not collapsible after clicking Plus icon");
								AssertFailedCount++;
							}
							
							
					System.out.println("Step 9 : Add Comment in comment box");
					Reporter.log("Step 9 : Add Comment in comment box");	
							
					
						regStud.commentTextBox.sendKeys("test");
						regStud.addComment.click();
						Thread.sleep(6000);
					
						
					System.out.println("Step 10 : Verify wether comment is added successfully or not");
					Reporter.log("Step 10 : Verify wether comment is added successfully or not");
					
					
						if(regStud.commentMessage.getText().equalsIgnoreCase("test"))		
						{
							System.out.println("Success!!! Comment is added successfully");
							Reporter.log("Success!!! Comment is added successfully");
							
						}
						else
						{
							System.out.println("Faliure!!! Comment is not added successfully");
							Reporter.log("Faliure!!! Comment is not added successfully");
						
							AssertFailedCount++;
						}	
							
							
							
							
							try {
								SeleniumFunc.ToGoToUrl(eventRosterURL);
								Thread.sleep(5000);

								//Going to Event Roster page and removing student and deleting event 
								//so new event can be added with same test data
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

								
								Thread.sleep(5000);
							} catch (Exception e) {
								
							}
							
							if(AssertFailedCount>0)	
							{
							
							//Marking this test as Failed
							
								System.out.println("---- Test Failed. Please check the console or TestNg report for details");
								Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
							
								Assert.fail();
							}
				   }
				 
				 
}
