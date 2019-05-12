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

public class RegistrationPaidEventTest 
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
	 * Verify group registration on Paid event
	 */ 
	@Test
	private void GroupRegistrationPaidEvent() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify group registration on Paid event"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify group registration on Paid event"  + "\n" +
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
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		PaymentSettingsPage paymentsettings = new PaymentSettingsPage(driver);
		
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
		
		
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
			
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
			
			instructorcreateevent.CreateEvent.click();
						
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
				
	
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
		
		
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
								+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Time" + "\n" + "Expected : "  
					+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
			
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
								+ "\n" + expectedtext + "\n" + 	 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Name" + "\n" + "Expected : "  
					+ "\n" + expectedtext + "\n" + 	 "Actual: " + "\n" +  actualtext);
			
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
			
		System.out.println("Step 6 : Select agency from agency listing");
		Reporter.log("Step 6 : Select agency from agency listing"); 
											
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			
		System.out.println("Step 7 : Go to Payment settings and set payment ON");
		Reporter.log("Step 7 : Go to Payment settings and set payment ON");   	
							
			agencyhome.ClickToSelectProgram();
			agencyhome.ClickOnPaymentSettingsLink();
			paymentsettings.SelectPaymentMode("Global");
			paymentsettings.SelectProductCode("EME57265 - Demo Hunter Education Field Day");
			/*paymentsettings.EnterPrice("1");
			paymentsettings.SelectFeeCode("discount");
			paymentsettings.SelectDepositeAccount("24000 State Card Fees Payable");*/
			paymentsettings.ClickOnSaveChanges();
							
		System.out.println("Step 8 : Search and Activate event");
		Reporter.log("Step 8 : Search and Activate event"); 
					
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);			searchevent.EnterSearchData(EventID);
			searchevent.ClickOnSearchButton();
			searchevent.ClickOnSelectEventCheckBox();
			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			login.ClickOnLogoutButton();
	
		System.out.println("Step 8 : Go to register-ed and make group registration");
		Reporter.log("Step 8 : Go to register-ed and make group registration"); 
		
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);

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
			eventview.ClickOnRegisterNowButton();
			
			String FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			String UserName="emstudent"+ JH.GenerateRandomNumber();
			String EmailAddress = UserName + "@mailinator.com";
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickToSelectGender();
			
			registerstudent.ClickOnNextButton();
			Thread.sleep(2000);
			
			//Register Second Student
			registerstudent.ClickOnAddAnotherStudentButton();
			
			FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896356");
			registerstudent.ClickToSelectGender();
			
			registerstudent.ClickOnNextButton();

			
			//Register Third Student
			registerstudent.ClickOnAddAnotherStudentButton();
			
			FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896332");
			registerstudent.ClickToSelectGender();
			
			registerstudent.ClickOnNextButton();
           Thread.sleep(2000);
			registerstudent.ClickOnReviewAndConfirmButton();

			//Enter Valid Details for Step 2
			registerstudent.EnterCardNumber("4111111111111111");
			registerstudent.EnterExpDate("2025");
			registerstudent.EnterFirstNamePayment("Automation");
			registerstudent.EnterSurName("Testing");
			registerstudent.ClickOnSameAddressCheckbox();
			Thread.sleep(2000);
			registerstudent.ClickOnAgreePolicyCheckBox();
			registerstudent.ClickOnCompleteRegistrationButton();
			
			Thread.sleep(4000);
		     SeleniumFunc.AcceptAlertAndGetText();
			
			//Verify Success Message
			Thread.sleep(5000);
			expectedtext = "Well done! You successfully completed registering for this event.";
			actualtext = registerstudent.SuccessMessage.getText().trim();
	
			if(actualtext.contains(expectedtext))
			{
		
				System.out.println("Success !! Group Registration is working properly." +expectedtext);
				Reporter.log("Success !! Group Registration is working properly." +expectedtext);
		
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
			
			
			try {
				//Login as Instructor					
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
				
				//Set Payment Settings to default
				login.ClickOnLogoutButton();
				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
				Thread.sleep(5000);
				agencyhome.ClickToSelectProgram();
			  	agencyhome.ClickOnPaymentSettingsLink();
				paymentsettings.SelectPaymentMode("Off");
				paymentsettings.ClickOnSaveChanges();
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

	
	
	/* Test 2: 
	 * Verify Add student is not seen on step 2 for Paid event
	 */ 
	@Test
	private void NoAddStudentStep2() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify Add student is not seen on step 2 for Paid event"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify Add student is not seen on step 2 for Paid event"  + "\n" +
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
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		PaymentSettingsPage paymentsettings = new PaymentSettingsPage(driver);
		
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
			
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
			
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
			
			instructorcreateevent.CreateEvent.click();
						
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
				
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
		
		
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
								+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Time" + "\n" + "Expected : "  
					+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
			
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
								+ "\n" + expectedtext + "\n" + 	 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Name" + "\n" + "Expected : "  
					+ "\n" + expectedtext + "\n" + 	 "Actual: " + "\n" +  actualtext);
			
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
			
		System.out.println("Step 6 : Select agency from agency listing");
		Reporter.log("Step 6 : Select agency from agency listing"); 
											
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			
		System.out.println("Step 7 : Go to Payment settings and set payment ON");
		Reporter.log("Step 7 : Go to Payment settings and set payment ON");   	
							
			agencyhome.ClickToSelectProgram();
			agencyhome.ClickOnPaymentSettingsLink();
			paymentsettings.SelectPaymentMode("Global");
			paymentsettings.SelectProductCode("EME57265 - Demo Hunter Education Field Day");
			paymentsettings.ClickOnSaveChanges();
							
		System.out.println("Step 8 : Search and Activate event");
		Reporter.log("Step 8 : Search and Activate event"); 
					
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);
			searchevent.ClickOnSearchButton();
			searchevent.ClickOnSelectEventCheckBox();
			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			login.ClickOnLogoutButton();
	
		System.out.println("Step 8 : Go to register-ed and make group registration");
		Reporter.log("Step 8 : Go to register-ed and make group registration"); 
		
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);

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
			eventview.ClickOnRegisterNowButton();
			
			String FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			String UserName="emstudent"+ JH.GenerateRandomNumber();
			String EmailAddress = UserName + "@mailinator.com";
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickToSelectGender();
			
			registerstudent.ClickOnNextButton();
			Thread.sleep(2000);
			
			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(2000);
			
			//Verify add student link is missing
			if(!SeleniumFunc.IsElementPresent(registerstudent.AddStudent))
			{
				
				System.out.println("Success !! Add student is missing on step 2 for Paid event.");
				Reporter.log("Success !! Add student is missing on step 2 for Paid event.");
			
			}
			else
			{
			
				System.out.println("Failure !! Add student link is present.");
				Reporter.log("Failure !! Add student link is present.");
				
				AssertFailedCount++;
			
			}
			
			
			try {	    
			    registerstudent.CancelRegistration.click();
			    
			    Thread.sleep(2000);
			    SeleniumFunc.AcceptAlertAndGetText();
				
			    //Login as Instructor					
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);
				login.ClickOnLogInButton();		
				
				//Deleting event so new event can be added with same test data 
				instructorhome.VisitHomePage.click();
				instructorhome.Action_MyEventSchedule.click();
				//instructorhome.GoToNextMonth();
				instructorschedule.FirstEventName.click();
				instructoreventroster.EventDelete.click();
				instructoreventroster.Confirm_EventDelete.click();
				
				Thread.sleep(5000);
				
				//Set Payment Settings to default
				login.ClickOnLogoutButton();
				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
				Thread.sleep(5000);
				agencyhome.ClickToSelectProgram();
			  	agencyhome.ClickOnPaymentSettingsLink();
				paymentsettings.SelectPaymentMode("Off");
				paymentsettings.ClickOnSaveChanges();
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
	 * Verify Credit card validations for paid event
	 */ 
	@Test
	private void CCValidationPaidEvent() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify Credit card validations for paid event"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify Credit card validations for paid event"  + "\n" +
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
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		PaymentSettingsPage paymentsettings = new PaymentSettingsPage(driver);
		
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
			
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
			
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
			
			instructorcreateevent.CreateEvent.click();
						
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
				
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
		
		
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
								+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Time" + "\n" + "Expected : "  
					+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
			
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
								+ "\n" + expectedtext + "\n" + 	 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Name" + "\n" + "Expected : "  
					+ "\n" + expectedtext + "\n" + 	 "Actual: " + "\n" +  actualtext);
			
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
			
		System.out.println("Step 6 : Select agency from agency listing");
		Reporter.log("Step 6 : Select agency from agency listing"); 
											
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			
		System.out.println("Step 7 : Go to Payment settings and set payment ON");
		Reporter.log("Step 7 : Go to Payment settings and set payment ON");   	
							
			agencyhome.ClickToSelectProgram();
			agencyhome.ClickOnPaymentSettingsLink();
			paymentsettings.SelectPaymentMode("Global");
			paymentsettings.SelectProductCode("EME57265 - Demo Hunter Education Field Day");
			paymentsettings.ClickOnSaveChanges();
							
		System.out.println("Step 8 : Search and Activate event");
		Reporter.log("Step 8 : Search and Activate event"); 
					
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);
			searchevent.ClickOnSearchButton();
			searchevent.ClickOnSelectEventCheckBox();
			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			login.ClickOnLogoutButton();
	
		System.out.println("Step 8 : Go to register-ed and make group registration");
		Reporter.log("Step 8 : Go to register-ed and make group registration"); 
		
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);

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
			eventview.ClickOnRegisterNowButton();
			
			String FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			String UserName="emstudent"+ JH.GenerateRandomNumber();
			String EmailAddress = UserName + "@mailinator.com";
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickToSelectGender();
			
			registerstudent.ClickOnNextButton();
			Thread.sleep(2000);
			
			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(2000);
			
			registerstudent.EnterFirstNamePayment("Automation");
			registerstudent.EnterSurName("Testing");
			registerstudent.ClickOnSameAddressCheckbox();
			registerstudent.ClickOnAgreePolicyCheckBox();
			registerstudent.ClickOnCompleteRegistrationButton();
			
			Thread.sleep(2000);
			
			//Blank CC
			expectedtext = "The following issues were found:" + "\n"+ "Card Number";
			actualtext = registerstudent.Validation.getText().trim();
	
			if(actualtext.contains(expectedtext))
			{
		
				System.out.println("Success !! Correct validation is displayed for card number");
				Reporter.log("Success !! Correct validation is displayed for card number");
		
			}
			else
			{
		
				System.out.println("Failure !! Validation is missing" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Validation is missing" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
	
			}	
			
			//Invalid CC
			registerstudent.EnterCardNumber("2342342423432");
			registerstudent.ClickOnCompleteRegistrationButton();

			expectedtext = "Invalid input.";
			actualtext = registerstudent.InvalidCC.getText().trim();
	
			if(actualtext.contains(expectedtext))
			{
		
				System.out.println("Success !! Correct validation is displayed for invalid card number");
				Reporter.log("Success !! Correct validation is displayed for invalid card number");
		
			}
			else
			{
		
				System.out.println("Failure !! Validation is missing" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Validation is missing" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
	
			}
			
			try {	    
			    registerstudent.CancelRegistration.click();
			    
			    Thread.sleep(2000);
			    SeleniumFunc.AcceptAlertAndGetText();
				
			    //Login as Instructor					
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);
				login.ClickOnLogInButton();		
				
				//Deleting event so new event can be added with same test data 
				instructorhome.VisitHomePage.click();
				instructorhome.Action_MyEventSchedule.click();
				//instructorhome.GoToNextMonth();
				instructorschedule.FirstEventName.click();
				instructoreventroster.EventDelete.click();
				instructoreventroster.Confirm_EventDelete.click();
				
				Thread.sleep(5000);
				
				//Set Payment Settings to default
				login.ClickOnLogoutButton();
				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
				Thread.sleep(5000);
				agencyhome.ClickToSelectProgram();
			  	agencyhome.ClickOnPaymentSettingsLink();
				paymentsettings.SelectPaymentMode("Off");
				paymentsettings.ClickOnSaveChanges();
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
	 * Verify user can cancel registration on step 1 
	 */ 
	@Test
	private void CancelRegistrationStep1() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify user can cancel registration on step 1 "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify user can cancel registration on step 1 "  + "\n" +
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
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		PaymentSettingsPage paymentsettings = new PaymentSettingsPage(driver);
		
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
			
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
			
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
			
			instructorcreateevent.CreateEvent.click();
						
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
				
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
		
		
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
								+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Time" + "\n" + "Expected : "  
					+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
			
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
								+ "\n" + expectedtext + "\n" + 	 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Name" + "\n" + "Expected : "  
					+ "\n" + expectedtext + "\n" + 	 "Actual: " + "\n" +  actualtext);
			
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
			
		System.out.println("Step 6 : Select agency from agency listing");
		Reporter.log("Step 6 : Select agency from agency listing"); 
											
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			
		System.out.println("Step 7 : Go to Payment settings and set payment ON");
		Reporter.log("Step 7 : Go to Payment settings and set payment ON");   	
							
			agencyhome.ClickToSelectProgram();
			agencyhome.ClickOnPaymentSettingsLink();
			paymentsettings.SelectPaymentMode("Global");
			paymentsettings.SelectProductCode("EME57265 - Demo Hunter Education Field Day");
			paymentsettings.ClickOnSaveChanges();
							
		System.out.println("Step 8 : Search and Activate event");
		Reporter.log("Step 8 : Search and Activate event"); 
					
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);
			searchevent.ClickOnSearchButton();
			searchevent.ClickOnSelectEventCheckBox();
			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			login.ClickOnLogoutButton();
	
		System.out.println("Step 8 : Go to register-ed and go to registration");
		Reporter.log("Step 8 : Go to register-ed and go to registration"); 
		
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);

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
			eventview.ClickOnRegisterNowButton();
			
			registerstudent.CancelRegistration.click();		
			Thread.sleep(4000);
			
			if(SeleniumFunc.IsElementPresent(eventview.RegisterNow))
			{				
				System.out.println("Success !! Cancel registration functionality is working properly.");
				Reporter.log("Success !! Cancel registration functionality is working properly.");		
			}
			else
			{	
				System.out.println("Failure !! Cancel registration functionality is NOT working.");
				Reporter.log("Failure !! Cancel registration functionality is NOT working.");
				
				AssertFailedCount++;	
			}
			
			try {	    
			  
			    //Login as Instructor					
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);
				login.ClickOnLogInButton();		
				
				//Deleting event so new event can be added with same test data 
				instructorhome.VisitHomePage.click();
				instructorhome.Action_MyEventSchedule.click();
				//instructorhome.GoToNextMonth();
				instructorschedule.FirstEventName.click();
				instructoreventroster.EventDelete.click();
				instructoreventroster.Confirm_EventDelete.click();
				
				Thread.sleep(5000);
				
				//Set Payment Settings to default
				login.ClickOnLogoutButton();
				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
				Thread.sleep(5000);
				agencyhome.ClickToSelectProgram();
			  	agencyhome.ClickOnPaymentSettingsLink();
				paymentsettings.SelectPaymentMode("Off");
				paymentsettings.ClickOnSaveChanges();
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
	 * Verify user can cancel registration on step 2 
	 */ 
	@Test
	private void CancelRegistrationStep2() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify user can cancel registration on step 2 "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify user can cancel registration on step 2 "  + "\n" +
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
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		PaymentSettingsPage paymentsettings = new PaymentSettingsPage(driver);
		
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
			
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
			
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
			
			instructorcreateevent.CreateEvent.click();
						
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
				
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
		
		
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
								+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Time" + "\n" + "Expected : "  
					+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
			
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
								+ "\n" + expectedtext + "\n" + 	 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Name" + "\n" + "Expected : "  
					+ "\n" + expectedtext + "\n" + 	 "Actual: " + "\n" +  actualtext);
			
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
			
		System.out.println("Step 6 : Select agency from agency listing");
		Reporter.log("Step 6 : Select agency from agency listing"); 
											
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			
		System.out.println("Step 7 : Go to Payment settings and set payment ON");
		Reporter.log("Step 7 : Go to Payment settings and set payment ON");   	
							
			agencyhome.ClickToSelectProgram();
			agencyhome.ClickOnPaymentSettingsLink();
			paymentsettings.SelectPaymentMode("Global");
			paymentsettings.SelectProductCode("EME57265 - Demo Hunter Education Field Day");
			paymentsettings.ClickOnSaveChanges();
							
		System.out.println("Step 8 : Search and Activate event");
		Reporter.log("Step 8 : Search and Activate event"); 
					
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);
			searchevent.ClickOnSearchButton();
			searchevent.ClickOnSelectEventCheckBox();
			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			login.ClickOnLogoutButton();
	
		System.out.println("Step 8 : Go to register-ed and make group registration");
		Reporter.log("Step 8 : Go to register-ed and make group registration"); 
		
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);

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
			eventview.ClickOnRegisterNowButton();
			
			String FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			String UserName="emstudent"+ JH.GenerateRandomNumber();
			String EmailAddress = UserName + "@mailinator.com";
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickToSelectGender();
			
			registerstudent.ClickOnNextButton();
			Thread.sleep(2000);
			
			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(2000);
			
			registerstudent.CancelRegistration.click();		
			
			Thread.sleep(2000);
			 SeleniumFunc.AcceptAlertAndGetText();
			
			if(SeleniumFunc.IsElementPresent(eventview.RegisterNow))
			{				
				System.out.println("Success !! Cancel registration functionality is working properly.");
				Reporter.log("Success !! Cancel registration functionality is working properly.");		
			}
			else
			{	
				System.out.println("Failure !! Cancel registration functionality is NOT working.");
				Reporter.log("Failure !! Cancel registration functionality is NOT working.");
				
				AssertFailedCount++;	
			}
			
			try {	    
			    
			    //Login as Instructor					
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);
				login.ClickOnLogInButton();		
				
				//Deleting event so new event can be added with same test data 
				instructorhome.VisitHomePage.click();
				instructorhome.Action_MyEventSchedule.click();
				//instructorhome.GoToNextMonth();
				instructorschedule.FirstEventName.click();
				instructoreventroster.EventDelete.click();
				instructoreventroster.Confirm_EventDelete.click();
				
				Thread.sleep(5000);
				
				//Set Payment Settings to default
				login.ClickOnLogoutButton();
				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
				Thread.sleep(5000);
				agencyhome.ClickToSelectProgram();
			  	agencyhome.ClickOnPaymentSettingsLink();
				paymentsettings.SelectPaymentMode("Off");
				paymentsettings.ClickOnSaveChanges();
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
	 * Verify Remove button should not display on Payment page of Register-ed
	 */ 
	@Test
	private void NoRemoveButtonPaidEvent() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify Remove button should not display on Payment page of Register-ed"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Verify Remove button should not display on Payment page of Register-ed"  + "\n" +
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
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		PaymentSettingsPage paymentsettings = new PaymentSettingsPage(driver);
		
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
		
		
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
			
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
			
			instructorcreateevent.CreateEvent.click();
						
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
				
	
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
		
		
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
								+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Time" + "\n" + "Expected : "  
					+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
			
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
								+ "\n" + expectedtext + "\n" + 	 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Name" + "\n" + "Expected : "  
					+ "\n" + expectedtext + "\n" + 	 "Actual: " + "\n" +  actualtext);
			
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
			
		System.out.println("Step 6 : Select agency from agency listing");
		Reporter.log("Step 6 : Select agency from agency listing"); 
											
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			
		System.out.println("Step 7 : Go to Payment settings and set payment ON");
		Reporter.log("Step 7 : Go to Payment settings and set payment ON");   	
							
			agencyhome.ClickToSelectProgram();
			agencyhome.ClickOnPaymentSettingsLink();
			paymentsettings.SelectPaymentMode("Global");
			paymentsettings.SelectProductCode("EME57265 - Demo Hunter Education Field Day");
			/*paymentsettings.EnterPrice("1");
			paymentsettings.SelectFeeCode("discount");
			paymentsettings.SelectDepositeAccount("24000 State Card Fees Payable");*/
			paymentsettings.ClickOnSaveChanges();
							
		System.out.println("Step 8 : Search and Activate event");
		Reporter.log("Step 8 : Search and Activate event"); 
					
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
			searchevent.SelectSearchTypeByVisibleText("ID");
			searchevent.EnterSearchData(EventID);
			searchevent.ClickOnSearchButton();
			searchevent.ClickOnSelectEventCheckBox();
			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			login.ClickOnLogoutButton();
	
		System.out.println("Step 8 : Go to register-ed and make group registration");
		Reporter.log("Step 8 : Go to register-ed and make group registration"); 
		
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);

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
			eventview.ClickOnRegisterNowButton();
			
			String FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			String UserName="emstudent"+ JH.GenerateRandomNumber();
			String EmailAddress = UserName + "@mailinator.com";
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896336");
			registerstudent.ClickToSelectGender();
			
			registerstudent.ClickOnNextButton();
			Thread.sleep(2000);
			
			//Register Second Student
			registerstudent.ClickOnAddAnotherStudentButton();
			
			FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
			
			
			registerstudent.RegisterNewStudent(FirstName, "Testing", EmailAddress, EmailAddress, "Asian", "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
			registerstudent.SelectEyeColor();
			registerstudent.SelectHairColor();
			registerstudent.EnterSSNNO("125896356");
			registerstudent.ClickToSelectGender();
			
			registerstudent.ClickOnNextButton();
			
           Thread.sleep(2000);
			registerstudent.ClickOnReviewAndConfirmButton();

			Thread.sleep(4000);
			
			if(!SeleniumFunc.IsElementPresent(registerstudent.Remove))
			{				
				System.out.println("Success !! No remove button on payment page for paid event.");
				Reporter.log("Success !! No remove button on payment page for paid event.");		
			}
			else
			{	
				System.out.println("Failure !! Remove button is present.");
				Reporter.log("Failure !! Remove button is present.");
				
				AssertFailedCount++;	
			}
			
			try {
				
				registerstudent.CancelRegistration.click();		
				
				Thread.sleep(2000);
				 SeleniumFunc.AcceptAlertAndGetText();
				 
				//Login as Instructor					
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);
				login.ClickOnLogInButton();		
				
				//Deleting event so new event can be added with same test data 
				instructorhome.VisitHomePage.click();
				instructorhome.Action_MyEventSchedule.click();
				//instructorhome.GoToNextMonth();
				instructorschedule.FirstEventName.click();
				instructoreventroster.EventDelete.click();
				instructoreventroster.Confirm_EventDelete.click();
				
				Thread.sleep(5000);
				
				//Set Payment Settings to default
				login.ClickOnLogoutButton();
				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
				Thread.sleep(5000);
				agencyhome.ClickToSelectProgram();
			  	agencyhome.ClickOnPaymentSettingsLink();
				paymentsettings.SelectPaymentMode("Off");
				paymentsettings.ClickOnSaveChanges();
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

}