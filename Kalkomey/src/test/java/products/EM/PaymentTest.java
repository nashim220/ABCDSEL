package products.EM;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
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
import pageFactory.EM.EMPaymentInfoPage;
import pageFactory.EM.EMStudentRegistrationPage;
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
import pageFactory.EM.StudentResultPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

import org.openqa.selenium.JavascriptExecutor;
public class PaymentTest 
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
	 * Verify User can configure payment settings
	*/ 
	@Test
	private void ConfigurePaymentSettings() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify User can configure payment settings"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify User can configure payment settings"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		PaymentSettingsPage paymentsettings = new PaymentSettingsPage(driver);
		
		System.out.println("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");				
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Select agency from agency listing");
		Reporter.log("Step 2 : Select agency from agency listing"); 
				
		((JavascriptExecutor)driver).executeScript("scroll(990,774)");			
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

		
		System.out.println("Step 3 : Select a program and go to Program Settings");
		Reporter.log("Step 3 : Select a program and go to Program Settings"); 
			
		
		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/payment/13");
		  	//agencyhome.ClickOnPaymentSettingsLink();
			Thread.sleep(5000);

		  	
		System.out.println("Step 4 : Verify Title for Payment Settings");
		Reporter.log("Step 4 : Verify Title for Payment Settings");
				
			String Actual = paymentsettings.Title.getText().trim();
			String Expected = "Payment";
					
			if(Actual.equals(Expected))
			{
	
				System.out.println("Success !! Title is correct for Payment Settings.");
				Reporter.log("Success !! Title is correct for Payment Settings.");
			
			}
			else
			{
			
				System.out.println("Failure !! Title is NOT correct for Payment Settings." + "\n" + "Expected : "  		+ "\n" + Expected + "\n" + 
										 "Actual: " + "\n" +  Actual);
				Reporter.log("Failure !! Title is NOT correct for Payment Settings." + "\n" + "Expected : "  
						+ "\n" + Expected + "\n" + 
						 "Actual: " + "\n" +  Actual);
						
				AssertFailedCount++;
			
			}		  		
				
			
					
	/*	System.out.println("Step 5 : Select Payment Mode as Global, but overridable and enter invalid price");
		Reporter.log("Step 5 : Select Payment Mode as Global, but overridable and enter invalid price");
						
						
			paymentsettings.SelectPaymentMode("Global, but overridable");
			paymentsettings.EnterPrice("test");
			paymentsettings.ClickOnSaveChanges();
			Thread.sleep(5000);

			//Verify Validation for invalid price
			Actual = paymentsettings.Validation.getText().trim();
			Expected = "Please, enter a valid dollar amount for the event price.";
				
			if(Actual.equals(Expected))
			{
			
				System.out.println("Success !! Validation is displayed for invalid price. i.e. " +Expected);
				Reporter.log("Success !! Validation is displayed for invalid price. i.e. " +Expected);
		
			}
			else
			{
			
				System.out.println("Failure !! No Validation." + "\n" + "Expected : "
									+ "\n" + Expected + "\n" + 
									 "Actual: " + "\n" +  Actual);
				Reporter.log("Failure !! No Validation." + "\n" + "Expected : "
								+ "\n" + Expected + "\n" + 
									"Actual: " + "\n" +  Actual);
					
				AssertFailedCount++;
			
			}		  	
		*/
				
		System.out.println("Step 6 : Enter valid details for Payment");
		Reporter.log("Step 6 : Enter valid details for Payment");
				
				paymentsettings.SelectPaymentMode("On (fixed price)");
				paymentsettings.SelectProductCode("EME57265 - Demo Hunter Education Field Day");
		/*	paymentsettings.EnterPrice("1");
			paymentsettings.SelectFeeCode("discount");
			paymentsettings.SelectDepositeAccount("24000 State Card Fees Payable");*/
			paymentsettings.ClickOnSaveChanges();
			Thread.sleep(5000);

			
			//Verify Payment Settings Saved
			Actual = paymentsettings.Success.getText().trim();
			Expected = "Payment settings saved.";
				
			if(Actual.equals(Expected))
			{
			
				System.out.println("Success !! Payment Settings Saved.");
				Reporter.log("Success !! Payment Settings Saved.");
		
			}
			else
			{
			
				System.out.println("Failure !! Payment Settings NOT Saved." + "\n" + "Expected : "
									+ "\n" + Expected + "\n" + 
									 "Actual: " + "\n" +  Actual);
				Reporter.log("Failure !! Payment Settings NOT Saved." + "\n" + "Expected : "
						+ "\n" + Expected + "\n" + 
						 "Actual: " + "\n" +  Actual);
					
				AssertFailedCount++;
			
			}			  	

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
		  	//Set to default
			paymentsettings.SelectPaymentMode("Off");
			paymentsettings.ClickOnSaveChanges();
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
	
	
	/* Test 2: 
	 * Make a Registration With Payment and verify student is added to Roster
	 */
	@Test
	private void RegistrationWithPayment() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Make a Registration With Payment and verify student is added to Roster"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Make a Registration With Payment and verify student is added to Roster"  + "\n" +
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
			((JavascriptExecutor)driver).executeScript("scroll(990,774)");
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
			((JavascriptExecutor)driver).executeScript("scroll(990,774)");
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

			
		System.out.println("Step 6 : Select agency from agency listing");
		Reporter.log("Step 6 : Select agency from agency listing"); 
		((JavascriptExecutor)driver).executeScript("scroll(990,774)");							
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

		System.out.println("Step 7 : Go to Payment settings and set payment ON");
		Reporter.log("Step 7 : Go to Payment settings and set payment ON");   	
			
				
			agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/payment/13");
			//agencyhome.ClickOnPaymentSettingsLink();
			Thread.sleep(5000);

			paymentsettings.SelectPaymentMode("On (fixed price)");
			paymentsettings.SelectProductCode("EME57265 - Demo Hunter Education Field Day");
		/*	paymentsettings.EnterPrice("1");
			paymentsettings.SelectFeeCode("discount");
			paymentsettings.SelectDepositeAccount("24000 State Card Fees Payable");*/
			paymentsettings.ClickOnSaveChanges();
				
			Thread.sleep(5000);

		System.out.println("Step 8 : Search and Activate event");
		Reporter.log("Step 8 : Search and Activate event"); 
			
		
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
			//Thread.sleep(5000);
			
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

			
		System.out.println("Step 9 : Go to Register-Ed and verify UI of first page of registration");
		Reporter.log("Step 9 : Go to Register-Ed and verify functionality of Register Now button"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);
			Thread.sleep(5000);

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
			
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);
			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(10000);
			//registerstudent.ClickOnIUnderstand();
			//Enter Valid Details for Step 2
			registerstudent.EnterCardNumber("4111111111111111");
			registerstudent.EnterExpDate("2025");
			registerstudent.EnterFirstNamePayment("Automation");
			registerstudent.EnterSurName("Testing");
			registerstudent.ClickOnSameAddressCheckbox();
			Thread.sleep(2000);
			registerstudent.ClickOnAgreePolicyCheckBox();
			registerstudent.ClickOnCompleteRegistrationButton();
			Thread.sleep(5000);

		 /*    SeleniumFunc.AcceptAlertAndGetText();
			Thread.sleep(5000);*/

			//Verify Success Message
			//Well done! You successfully completed registering for this event. 
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
			
			//Go to instructor and verify student is added to roster
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
		
			login.ClickOnLogInButton();	
			Thread.sleep(5000);	
			
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(5000);
			
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");
			Thread.sleep(5000);
			
			
			//instructorhome.Action_MyEventSchedule.click();
			//Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			
			
			//instructorschedule.FirstEventName.click();
			//Thread.sleep(5000);
			
			//Verify student is added successfully
			expectedtext = "Testing, "+FirstName;
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
							
			instructoreventroster.ClickOnUnscheduleRemoveLink();
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("css", "#cancel-reminder-modal div:nth-of-type(3) a");
			Thread.sleep(5000);

			//Verify Recipients
			
			expectedtext = "Testing " +"("+EmailAddress+")";
			actualtext = SeleniumFunc.GetElementText("css", "#cancel_event_form ul li");
			
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Recipient is present with correct Name and Email");
				Reporter.log("Success !! Recipient is present with correct Name and Email");
			
			}
			else
			{
			
				System.out.println("Failure !! No Recipient Found." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! No Recipient Found." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			((JavascriptExecutor)driver).executeScript("scroll(828,774)");
			instructoreventroster.Confirm_EventDelete.click();
			Thread.sleep(5000);
			
			//Set Payment Settings to default
			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
			((JavascriptExecutor)driver).executeScript("scroll(990,774)");
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);
			
			agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/payment/13");
		  	//agencyhome.ClickOnPaymentSettingsLink();
			Thread.sleep(5000);

			paymentsettings.SelectPaymentMode("Off");
			paymentsettings.ClickOnSaveChanges();
			Thread.sleep(5000);
			System.out.println("Changes for payment has been updated to default settings");
		
			
		
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
	 * Set Configuration After Event Activation
	*/ 
	@Test
	private void SetConfigurationAfterEventActivation() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Set Configuration After Event Activation"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Set Configuration After Event Activation"  + "\n" +
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
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
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
			//instructorhome.GoToNextMonth();
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
			((JavascriptExecutor)driver).executeScript("scroll(1022,1060)");	
			instructorcreateevent.CreateEvent.click();
			Thread.sleep(5000);
			
			String URL = driver.getCurrentUrl();
				
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
					
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//Thread.sleep(5000);

			Thread.sleep(4000);
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

			
		System.out.println("Step 6 : Select agency from agency listing");
		Reporter.log("Step 6 : Select agency from agency listing"); 
					
		((JavascriptExecutor)driver).executeScript("scroll(990,774)");			
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

			
		System.out.println("Step 7 : Go to Payment settings and set payment Off");
		Reporter.log("Step 7 : Go to Payment settings and set payment Off");   	
			
				
			agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/payment/13");
			
			//agencyhome.ClickOnPaymentSettingsLink();
			Thread.sleep(5000);

			paymentsettings.SelectPaymentMode("Off");
			paymentsettings.ClickOnSaveChanges();
			Thread.sleep(5000);

				
		System.out.println("Step 8 : Search and Activate event");
		Reporter.log("Step 8 : Search and Activate event"); 
			
		
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

			
		
		System.out.println("Step 9 : Go to Payment settings and set payment Global, but overridable");
		Reporter.log("Step 9 : Go to Payment settings and set payment Global, but overridable");  	
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/payment/13");
			Thread.sleep(5000);

			paymentsettings.SelectPaymentMode("On (fixed price)");
			paymentsettings.SelectProductCode("EME57265 - Demo Hunter Education Field Day");
			paymentsettings.ClickOnSaveChanges();
			Thread.sleep(5000);


		System.out.println("Step 10 : Go to event admin and verify there is no references to price");
		Reporter.log("Step 10 : Go to event admin and verify there is no references to price"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/admin/" + EventID);
			Thread.sleep(5000);

					
			if(!SeleniumFunc.IsElementPresent("css", "#price"))
			{
				
				System.out.println("Success !! No reference to price.");
				Reporter.log("Success !! No reference to price.");
			
			}
			else
			{
			
				System.out.println("Failure !! Reference to price is present.");
				Reporter.log("Failure !! Reference to price is present.");
				
				AssertFailedCount++;
			
			}
			
			//Set Payment Settings to default
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/payment/13");
			paymentsettings.SelectPaymentMode("Off");
			paymentsettings.ClickOnSaveChanges();
			Thread.sleep(5000);
			System.out.println("Payment Settings updated to default!");
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(5000);
			
			SeleniumFunc.ClickOnElement("linkText", "Delete Event");
			
			Thread.sleep(2000);
		
			SeleniumFunc.ClickOnElement("linkText", "Delete Event");

			Thread.sleep(2000);

			System.out.println("Event: "+EventID+" Deleted Successfully!!");

			login.ClickOnLogoutButton();
			Thread.sleep(5000);
		
			
			
			/*
			//Go to instructor and verify student is added to roster
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
		
			login.ClickOnLogInButton();	
			Thread.sleep(5000);

			instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();
			Thread.sleep(5000);

			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);

			instructoreventroster.EventDelete.click();
			Thread.sleep(5000);

			instructoreventroster.Confirm_EventDelete.click();
			Thread.sleep(5000);

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
	 * Verify registration process with payment mode off
	*/ 	
	@Test
	private void RegistrationWithPaymentModeOff() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify registration process with payment mode off"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify registration process with payment mode off"  + "\n" +
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
			//instructorhome.GoToNextMonth();
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
			instructorcreateevent.SelectDate("5");
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
				
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("scroll(1022,1060)");	
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
									+ "\n" + expectedtext + "\n" + "Actual: " + "\n" +  actualtext);
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

			
		System.out.println("Step 6 : Select agency from agency listing");
		Reporter.log("Step 6 : Select agency from agency listing"); 
					
		((JavascriptExecutor)driver).executeScript("scroll(990,774)");				
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

			
		System.out.println("Step 7 : Go to Payment settings and set payment Off");
		Reporter.log("Step 7 : Go to Payment settings and set payment Off");   	
			
				
			agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/payment/13");
			//agencyhome.ClickOnPaymentSettingsLink();
			Thread.sleep(5000);

			paymentsettings.SelectPaymentMode("Off");
			paymentsettings.ClickOnSaveChanges();
			Thread.sleep(5000);

				
		System.out.println("Step 8 : Search and Activate event");
		Reporter.log("Step 8 : Search and Activate event"); 
			
		
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

			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			
		System.out.println("Step 9 : Go to Register-Ed and verify UI of first page of registration");
		Reporter.log("Step 9 : Go to Register-Ed and verify functionality of Register Now button"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);
			Thread.sleep(5000);
			
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
			
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);
			
		//	registerstudent.ClickOnIUnderstand();

			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(9000);
			
			//registerstudent.ClickOnIUnderstand();
			//Thread.sleep(5000);
			
			//Enter Valid Details for Step 2
			registerstudent.ClickOnAgreePolicyCheckBox();
			registerstudent.ClickOnCompleteRegistrationButton();
			Thread.sleep(5000);
			//Verify Success Message
			
			expectedtext = "Well done! You successfully completed registering for this event.";
			actualtext = registerstudent.SuccessMessage.getText().trim();
	
			if(actualtext.contains(expectedtext))
			{
		
				System.out.println("Success !! User is not charged for the event.");
				Reporter.log("Success !! User is not charged for the event.");
		
			}
			else
			{
		
				System.out.println("Failure !! Event asking for Payment" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event asking for Payment" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
	
			}	
			
			//Go to instructor and verify student is added to roster
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
		
			login.ClickOnLogInButton();	
			Thread.sleep(5000);
			
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(2000);
			
			/*instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

		
			instructorhome.Action_MyEventSchedule.click();
			Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);*/

			SeleniumFunc.ClickOnElement("linkText", "Event Roster");
			Thread.sleep(2000);
			
			
			//Verify student is added successfully
			expectedtext = "Testing, "+FirstName;
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
				
			
			//Remove Student and Delete Event
			instructoreventroster.ClickOnSelectEventCheckBox();
			instructoreventroster.SelectEventAction();
			instructoreventroster.ClickOnGoButton();
			Thread.sleep(5000);

			instructoreventroster.ConfirmStudentRemove.click();
			Thread.sleep(5000);

			instructoreventroster.EventDelete.click();
			Thread.sleep(5000);
			((JavascriptExecutor)driver).executeScript("scroll(828,774)");
			instructoreventroster.Confirm_EventDelete.click();
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
		
	/*
	 Test 5: 
	 * Verify Registration process with Global Using Inventory payment mode
	  
	@Test
	private void RegistrationWithGlobalUsingInventory() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify Registration process with Global  payment mode"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify Registration process with Global payment mode"  + "\n" +
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
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		PaymentSettingsPage paymentsettings = new PaymentSettingsPage(driver);
		EMAddStudentPage addstud = new EMAddStudentPage(driver);

		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			
			//Navigate to 'My Event Schedule' page and delete all Events
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/viewevents");
			instructorhome.GoToNextMonth();
			instructorschedule.DeleteAllEvents();
			
			
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
			instructorcreateevent.SelectDate("7");
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
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/viewevents");
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

		System.out.println("Step 6 : Select agency from agency listing");
		Reporter.log("Step 6 : Select agency from agency listing"); 
					
							
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

			
		System.out.println("Step 7 : Go to Payment settings and set payment Global using inventory");
		Reporter.log("Step 7 : Go to Payment settings and set payment Global using inventory");   	
			
				
			agencyhome.ClickToSelectProgram(); 
			Thread.sleep(5000);

			agencyhome.ClickOnPaymentSettingsLink();
			Thread.sleep(5000);

			paymentsettings.SelectPaymentMode("Global, but overridable");
			paymentsettings.SelectProductCode("EME57265 - Demo Hunter Education Field Day");
			paymentsettings.ClickOnSaveChanges();
			Thread.sleep(5000);

				
		System.out.println("Step 8 : Search and Activate event");
		Reporter.log("Step 8 : Search and Activate event"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
			searchevent.SelectSearchTypeByVisibleText("Location");
			searchevent.EnterSearchData("Demo Agency Location");
			searchevent.ClickOnSearchButton();
			Thread.sleep(5000);

			searchevent.ClickOnSelectEventCheckBox();
			searchevent.SelectEventActionByVisibleText("Set Status: Active");
			searchevent.ClickOnGoButton();
			Thread.sleep(5000);

			login.ClickOnLogoutButton();
			
			Thread.sleep(5000);

			
		System.out.println("Step 9 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 9 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
	
		
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

				
			//Add Student
			instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();	
			Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			instructorschedule.FirstEventName.click();
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

			
			
			//Verify Payment (Price) details is not present
			
			if(!SeleniumFunc.IsElementPresent("css", ".table") && SeleniumFunc.IsElementPresent("css", "#cc_first_name"))
			{
				
				System.out.println("Success !! User is prompted to enter payment, but price is not shown.");
				Reporter.log("Success !! User is prompted to enter payment, but price is not shown.");
				
			}
			else
			{
				
				System.out.println("Failure !! User is prompted to enter payment, but price is shown.");
				Reporter.log("Failure !! User is prompted to enter payment, but price is shown.");
				
				AssertFailedCount++;

			}
			
		
		System.out.println("Step 10 : Go to Register-Ed and verify UI of first page of registration");
		Reporter.log("Step 10 : Go to Register-Ed and verify functionality of Register Now button"); 
			
				
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

			
			//Verify box that displays pricing information that corresponds to the inventory items 
			//chosen on the payment configuration screen is present
			
			if(SeleniumFunc.IsElementPresent("css", ".payments.mbm"))
			{
				
				System.out.println("Success !! Pricing information box is present.");
				Reporter.log("Success !! Pricing information box is present.");
				
			}
			else
			{
				
				System.out.println("Failure !! Pricing information box is NOT present.");
				Reporter.log("Failure !! Pricing information box is NOT present.");
				
				AssertFailedCount++;

			}		
		
			
			//Delete event
			try {
				
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM + "/eventregistration/roster/"+ EventID);
					instructoreventroster.EventDelete.click();
					Thread.sleep(5000);

					instructoreventroster.Confirm_EventDelete.click();
					Thread.sleep(5000);

					//Set Payment Settings to default
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

					paymentsettings.SelectPaymentMode("Off");
					paymentsettings.ClickOnSaveChanges();
					login.ClickOnLogoutButton();
					Thread.sleep(5000);

			} catch (Exception e) {
				
			}
		
		
		 * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 
	 	if(AssertFailedCount>0)	
		{
			
			//Marking this test as Failed
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
		
	}
	
	*/
	/* Test 6: 
	 * Make a Registration With Payment and verify student is added to Roster
	 */
	@Test
	private void VerifyRollUpFunctionality() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Make a Registration With Payment and verify student is added to Roster"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Make a Registration With Payment and verify student is added to Roster"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
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
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
			
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("scroll(1022,1060)");
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

			
		System.out.println("Step 6 : Select agency from agency listing");
		Reporter.log("Step 6 : Select agency from agency listing"); 
			
		((JavascriptExecutor)driver).executeScript("scroll(990,774)");
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

		System.out.println("Step 7 : Go to Payment settings and set payment ON");
		Reporter.log("Step 7 : Go to Payment settings and set payment ON");   	
			
				
			agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/payment/13");
			//agencyhome.ClickOnPaymentSettingsLink();
			Thread.sleep(5000);

			paymentsettings.SelectPaymentMode("On (fixed price)");
			Thread.sleep(4000);

			paymentsettings.SelectProductCode("EME57265 - Demo Hunter Education Field Day");
			Thread.sleep(4000);
			WebElement ele = driver.findElement(By.cssSelector("#rollup"));
			if(!(ele.isSelected()))
			{
			SeleniumFunc.ClickOnElement("css", "#rollup");
			Thread.sleep(4000);
			SeleniumFunc.EnterValueInTextbox("css", "#payment_rollup_desc", "Registration Fee");
			Thread.sleep(2000);
			}

			paymentsettings.ClickOnSaveChanges();
				
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

		
			//Verify Title
			expectedtext = "Registration Fee";
			actualtext = SeleniumFunc.GetElementText("css", "#primary_content tbody th:nth-of-type(1)");
		
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
				
				System.out.println("Success !! Roll Up functionality is working properly.");
				Reporter.log("Success !! Roll Up functionality is working properly.");
			
			}
			else
			{
			
				System.out.println("Failure !! Error in Roll Up functionality." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Error in Roll Up functionality." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			try{
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");

			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
			

			SeleniumFunc.ToGoToUrl("https://my-webtest1.register-ed.com/eventregistration/roster/"+EventID);
			Thread.sleep(5000);

			instructoreventroster.ClickOnUnscheduleRemoveLink();
			Thread.sleep(5000);

			((JavascriptExecutor)driver).executeScript("scroll(828,774)");
			instructoreventroster.Confirm_EventDelete.click();
			Thread.sleep(5000);
			
			//Set Payment Settings to default
			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
			((JavascriptExecutor)driver).executeScript("scroll(990,774)");

			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);
			
			agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/payment/13");
		  	//agencyhome.ClickOnPaymentSettingsLink();
			Thread.sleep(5000);

			//SeleniumFunc.ClickOnElement("css", "#rollup");
			//Thread.sleep(4000);
			//paymentsettings.ClickOnSaveChanges();

			paymentsettings.SelectPaymentMode("Off");
			paymentsettings.ClickOnSaveChanges();
			Thread.sleep(5000);

			login.ClickOnLogoutButton();
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
	

	/*Test Case 7:
	 * Event Manager viewing a payment record, verify the correct number of card digits
	 */
	
		@Test
		private void EM_NumberOfDigitsInCards() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 2 : Event Manager viewing a payment record, verify the correct number of card digits"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 2 : Event Manager viewing a payment record, verify the correct number of card digits"  + "\n" +
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
			PaymentSettingsPage paymentsettings = new PaymentSettingsPage(driver);
			EMStudentRegistrationPage regStud =new EMStudentRegistrationPage(driver);
			
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
				instructorcreateevent.SelectDate("5");	
				instructorcreateevent.SelectStartDate("6", "30", "PM");
				instructorcreateevent.SelectEndDate("7", "30", "PM");
				
				instructorcreateevent.AddEventDate.click();
				Thread.sleep(2000);
				((JavascriptExecutor)driver).executeScript("scroll(1022,1060)");
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
									+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! Event is added with incorrect Time" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
				
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
									+ "\n" + expectedtext + "\n" + 	 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! Event is added with incorrect Name" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 	 "Actual: " + "\n" +  actualtext);
				
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

				
			System.out.println("Step 6 : Select agency from agency listing");
			Reporter.log("Step 6 : Select agency from agency listing"); 
				
			((JavascriptExecutor)driver).executeScript("scroll(990,774)");
				SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
				Thread.sleep(5000);

			System.out.println("Step 7 : Go to Payment settings and set payment ON");
			Reporter.log("Step 7 : Go to Payment settings and set payment ON");   	
				
					
				agencyhome.ClickToSelectProgram();
				Thread.sleep(5000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/payment/13");
				//agencyhome.ClickOnPaymentSettingsLink();
				Thread.sleep(5000);

				paymentsettings.SelectPaymentMode("On (fixed price)");
				paymentsettings.SelectProductCode("EME57265 - Demo Hunter Education Field Day");
				Thread.sleep(3000);
				paymentsettings.ClickOnSaveChanges();			
				Thread.sleep(5000);

			System.out.println("Step 8 : Search and Activate event");
			Reporter.log("Step 8 : Search and Activate event"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
				Thread.sleep(2000);

				searchevent.ClickOnSearchEventLink();
				Thread.sleep(5000);
				//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/admin/events");
				//Thread.sleep(5000);

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

				
			System.out.println("Step 9 : Go to Register-Ed and verify UI of first page of registration");
			Reporter.log("Step 9 : Go to Register-Ed and verify functionality of Register Now button"); 
			
				
				SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);
				Thread.sleep(5000);

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
				
				registerstudent.ClickOnNextButton();
				Thread.sleep(5000);
				
				registerstudent.ClickOnReviewAndConfirmButton();
				Thread.sleep(10000);
				//registerstudent.ClickOnIUnderstand();
				Thread.sleep(5000);
				//Enter Valid Details for Step 2
				registerstudent.EnterCardNumber("4111111111111111");
				registerstudent.EnterExpDate("2025");
				registerstudent.EnterFirstNamePayment("Automation");
				registerstudent.EnterSurName("Testing");
				registerstudent.ClickOnSameAddressCheckbox();
				Thread.sleep(2000);
				registerstudent.ClickOnAgreePolicyCheckBox();
				registerstudent.ClickOnCompleteRegistrationButton();
				Thread.sleep(5000);

			 /*    SeleniumFunc.AcceptAlertAndGetText();
				Thread.sleep(5000);*/

				//Verify Success Message
				//Well done! You successfully completed registering for this event. 
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
				
			System.out.println("Step 10 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
			Reporter.log("Step 10 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 	
				
			/*
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login");
				Thread.sleep(5000);
				
				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);*/
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(4000);
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");
			Thread.sleep(4000);
			
			
			
			/*
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
			Thread.sleep(2000);

				
			System.out.println("Step 11 : Select agency from agency listing");
			Reporter.log("Step 11 : Select agency from agency listing"); 
												
				SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
				Thread.sleep(5000);
				
			System.out.println("Step 12 : Select a program and goto Event Roster");
			Reporter.log("Step 12 : Select a program and goto Event Roster"); 
								
				agencyhome.ClickToSelectProgram();
				Thread.sleep(5000);
				//click on search event
				SeleniumFunc.ClickOnElement("linkText", "Search");
				Thread.sleep(4000);
				// click on 1st event
				SeleniumFunc.ClickOnElement("css", ".tooltip-container.even.ruled>td>a");
				Thread.sleep(4000);
				
				instructoreventroster.ReturnToRosterEventAdmin.click();
				Thread.sleep(4000);	
				//ROSTER page URL
				String eventRosterURL=driver.getCurrentUrl();*/
			
			
				
			System.out.println("Step 11 : Select student in View");
			Reporter.log("Step 11 : Select student in View");
					
				instructoreventroster.ClickOnSelectFirstStudent();
				instructoreventroster.ClickOnViewRegistrationButton();
						
			System.out.println("Step 12 : Verify Credit card digits");
			Reporter.log("Step 12 : Verify Credit card digits");
				
				String cardDigitText = regStud.cardDigit.getText().trim();
				String expected = "xxxxxxxxxxxx1111";
				
				if(cardDigitText.equalsIgnoreCase(expected))
				{
					System.out.println("Success!!! Card digit is matched :"+expected);
					Reporter.log("Success!!! Card digit is matched :"+expected);
				}
				else
				{
					System.out.println("Failure!!! Card digit is not matched Expected:"+expected+" Actual :"+cardDigitText);
					Reporter.log("Failure!!! Card digit is not matched Expected:"+expected+" Actual:"+cardDigitText);
					AssertFailedCount++;
				}
			
				System.out.println("No of digits are : "+cardDigitText.length());
				Reporter.log("No of digits are : "+cardDigitText.length());
				
				/*
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
				*/
				//Set Payment Settings to default
				login.ClickOnLogoutButton();
				Thread.sleep(5000);

				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);
				((JavascriptExecutor)driver).executeScript("scroll(990,774)");
				SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
				Thread.sleep(5000);
				
				agencyhome.ClickToSelectProgram();
				Thread.sleep(5000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/payment/13");
			  	//agencyhome.ClickOnPaymentSettingsLink();
				Thread.sleep(5000);

				paymentsettings.SelectPaymentMode("Off");
				paymentsettings.ClickOnSaveChanges();
				Thread.sleep(5000);
				
				SeleniumFunc.ToGoToUrl(URL);
				Thread.sleep(5000);
				
				SeleniumFunc.ClickOnElement("linkText", "Cancel Event");
				
				Thread.sleep(5000);
				
				
				SeleniumFunc.ClickOnElement("linkText", "Unschedule and Remove Event");

				Thread.sleep(2000);
				((JavascriptExecutor)driver).executeScript("scroll(990,774)");
				SeleniumFunc.ClickOnElement("css", "body > div.stage > div.content > div.clear > a:nth-child(1) > span");
				
				Thread.sleep(2000);

				System.out.println("Event: "+EventID+" Deleted Successfully!!");
				

				login.ClickOnLogoutButton();
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

		/* Test 8: 
		 *Verify  Event Manager and Register-ed accept payments with MasterCard's new range of numbers
		 *Test Case ID : 21609
		*/ 
		
		@Test
		private void MasterCards_Range() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 8 : Verify Event Manager and Register-ed to accept payments with MasterCard's new range of numbers."  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 8 :Verify Event Manager and Register-ed to accept payments with MasterCard's new range of numbers."  + "\n" +
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
			AgencyHomePage agencyhome= new AgencyHomePage(driver);
			PaymentSettingsPage paymentsettings = new PaymentSettingsPage(driver);
			EMAddStudentPage addstud = new EMAddStudentPage(driver);
			EMPaymentInfoPage payment = new EMPaymentInfoPage(driver);
			RegisterEdRegisterStudentPage registerstudent = new RegisterEdRegisterStudentPage(driver);
			RegisterEdEventView eventview = new RegisterEdEventView(driver);

			System.out.println("Step 1 : Log in to Event Manager as an agency admin for a program that has payments set up : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 1 : Log in to Event Manager as an agency admin for a program that has payments set up : " + Constants.ApplicationURL_EM +"/login/login"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				
				if(SeleniumFunc.IsElementPresent("linkText", "Log Out"))
				{
					SeleniumFunc.ClickOnElement("linkText", "Log Out");
				}
				
				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);
				
			System.out.println("Step 2 : Select agency from agency listing");
			Reporter.log("Step 2 : Select agency from agency listing"); 
				
				((JavascriptExecutor)driver).executeScript("scroll(690,574)");
				SeleniumFunc.ClickOnElement("linkText", "New Hampshire Marine Patrol");
				SeleniumFunc.ClickOnElement("linkText", "New Hampshire Classroom Course");
				
				
			System.out.println("Step 3 : Go to Payment settings and set payment ON");
			Reporter.log("Step 3 : Go to Payment settings and set payment ON");   	

			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/payment/174");
					//agencyhome.ClickOnPaymentSettingsLink();
					Thread.sleep(5000);

					paymentsettings.SelectPaymentMode("On (fixed price)");
					//paymentsettings.SelectProductCode("EME57265 - Demo Hunter Education Field Day");
					Thread.sleep(3000);
					paymentsettings.ClickOnSaveChanges();			
					Thread.sleep(5000);
				
				
				
			System.out.println("Step 3 : On Create Event page , entering all required details and click on 'Create Event' button");
			Reporter.log("Step 3 : On Create Event page , entering all required detailsb and click on 'Create Event' button"); 
					
					agencyhome.ClickOnCreateEventLink();
					instructorcreateevent.SelectProgram();
					Thread.sleep(2000);
						
					instructorcreateevent.Location.clear();
					instructorcreateevent.Location.sendKeys("Demo Clarion Test Location");
					Thread.sleep(5000);
					instructorcreateevent.Location_FirstChoice.click();
					Thread.sleep(2000);
					
					//instructorcreateevent.GoToNextMonth();
					//instructorcreateevent.SelectDate("5");	
					instructorcreateevent.SelectStartDate("6", "30", "PM");
					instructorcreateevent.SelectEndDate("7", "30", "PM");
						
					instructorcreateevent.AddEventDate.click();
					Thread.sleep(2000);
					((JavascriptExecutor)driver).executeScript("scroll(990,774)");
					instructorcreateevent.CreateEvent.click();
					Thread.sleep(5000);
					String URL = driver.getCurrentUrl();
					System.out.println("Url :"+URL);
						
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
											+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! Event is added with incorrect Time" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
						
					AssertFailedCount++;
					
				}
					
					//Verifying Event Name
					expectedtext = "New Hampshire Classroom Course";
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
						
					String EventID = instructoreventroster.EventId.getText().trim();
					//login.ClickOnLogoutButton();
					//Thread.sleep(5000);
					
			System.out.println("Step 6 : Go to the roster for an active event in a program that has registration fees.");
			Reporter.log("Step 6 : Go to the roster for an active event in a program that has registration fees.");   
					
					//SeleniumFunc.ToGoToUrl(URL);
					SeleniumFunc.ClickOnElement("linkText", "Event Roster");
					SeleniumFunc.ClickOnElement("linkText", "Add Student");
					
					Thread.sleep(5000);
					
			System.out.println("Step 7 : Fill out the form and click Create New Registration.");
			Reporter.log("Step 7 : Fill out the form and click Create New Registration.");  					
					
					String username="emstudent"+ JH.GenerateRandomNumber();
					String emailaddress = username + "@mailinator.com";
					addstud.FillRegisterStudentNY("Clarion", "User", emailaddress, "Male", "United States of America", "Colorado", "January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
					addstud.SelectEyeColor();
					addstud.SelectHairColor();
					SeleniumFunc.EnterValueInTextbox("id", "registration_height", "6-4");
					//addstud.EnterSSNNO("125896336");
					addstud.ClickOnRegisterButton();
					Thread.sleep(5000);
					
			System.out.println("Step 8 : User Should be on Payment Page.");
			Reporter.log("Step 8 : User Should be on Payment Page.");  
			
					if(SeleniumFunc.GetElementText("css", "#student_edit_form > fieldset > legend").equals("Payment Information"))
					{
						System.out.println("Success !! User is on Payment page.");
						Reporter.log("Success !! User is on Payment page.");				
					}
					else
					{
						System.out.println("Failure !! Redirected to invalid page.");
						Reporter.log("Failure !! Redirected to invalid page.");
						AssertFailedCount++;
					}
					
			System.out.println("Step 9 : Fill out and submit the form using the credit card number 2221111111111111.");
			Reporter.log("Step 9 : Fill out and submit the form using the credit card number 2221111111111111.");  					
					
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", payment.cardFName);
						payment.cardFName.sendKeys("Test");
						payment.cardLName.sendKeys("Ke-Teting");
						payment.cardNum.sendKeys("2221111111111111");
						payment.ExpMonth.click();
						payment.SelectExpireMonthByVisibleText("January");
						payment.SelectExpireYearByVisibleText("2030");
						payment.Submit.click();
						Thread.sleep(4000);
						
						
			System.out.println("Step 10 : Should display Validation message saying - The value for the field credit card number is invalid. Please verify and re-enter the data.");
			Reporter.log("Step 10 : Should display Validation message saying - The value for the field credit card number is invalid. Please verify and re-enter the data.");  											
						
						String ExpectedText = "The value for the field credit card number is invalid. Please verify and re-enter the data.";
						
					if(SeleniumFunc.GetElementText("css", ".alert-block.alert.alert-success").equals(ExpectedText))
					{
						System.out.println("Success !! Validation message displayed.");
						Reporter.log("Success !! Validation message displayed.");				
					}
					else
					{
						System.out.println("Failure !! Validation message not found.");
						Reporter.log("Failure !! Validation message not found.");
						AssertFailedCount++;
					}
					
			System.out.println("Step 11 : Fill out and submit the form using the credit card number 2720981111111111.");
			Reporter.log("Step 11 : Fill out and submit the form using the credit card number 2720981111111111.");  										
					
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", payment.cardNum);
						payment.cardNum.sendKeys("2720981111111111");
						payment.ExpMonth.click();
						payment.SelectExpireMonthByVisibleText("January");
						payment.SelectExpireYearByVisibleText("2030");
						payment.Submit.click();
						Thread.sleep(4000);
						
					if(SeleniumFunc.GetElementText("css", ".alert-block.alert.alert-success").equals(ExpectedText))
					{
						System.out.println("Success !! Validation message displayed.");
						Reporter.log("Success !! Validation message displayed.");				
					}
					else
					{
						System.out.println("Failure !! Validation message not found.");
						Reporter.log("Failure !! Validation message not found.");
						AssertFailedCount++;
					}
					
			System.out.println("Step 12 : Go to an event on Register-ed that requires a payment.");
			Reporter.log("Step 12 : Go to an event on Register-ed that requires a payment."); 
			
				
				SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/events/view/" + EventID);
				Thread.sleep(5000);

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
				
				
		System.out.println("Step 13 : Fill out and submit the form using the credit card number 2121111111111111.");
		Reporter.log("Step 13 : Fill out and submit the form using the credit card number 2121111111111111."); 
			   
				
				//Enter Valid Details and Go to 2nd Step
				
				String FirstName ="emstudent"+ RandomStringUtils.random(3,new char[]{'A','b','C','d'});
				
				String UserName="emstudent"+ JH.GenerateRandomNumber();
				String EmailAddress = UserName + "@mailinator.com";
				
				registerstudent.RegisterNewStudentNY(FirstName, "Testing", EmailAddress, EmailAddress, "January", "2", "1990", "Street No - 123", "Norflok", "9898989898", "25825");
				registerstudent.ClickToSelectGender();
				registerstudent.SelectEyeColor1();
				registerstudent.SelectHairColor1();
				SeleniumFunc.EnterValueInTextbox("css", "#RegistrationDetail2Height", "6-4");
				
				registerstudent.ClickOnNextButton();
				Thread.sleep(5000);
				
				registerstudent.ClickOnReviewAndConfirmButton();
				Thread.sleep(10000);
				//Enter Valid Details for Step 2
				registerstudent.EnterCardNumber("2121111111111111");
				registerstudent.EnterExpDate("2025");
				registerstudent.EnterFirstNamePayment("Automation");
				registerstudent.EnterSurName("Testing");
				registerstudent.ClickOnSameAddressCheckbox();
				Thread.sleep(2000);
				registerstudent.ClickOnAgreePolicyCheckBox();
				registerstudent.ClickOnCompleteRegistrationButton();
				Thread.sleep(5000);
			
				
	System.out.println("Step 14 : Should display Validation message saying - The value for the field credit card number is invalid. Please verify and re-enter the data.");
	Reporter.log("Step 14 : Should display Validation message saying - The value for the field credit card number is invalid. Please verify and re-enter the data.");  											
				
				//String ExpectedText = "The value for the field credit card number is invalid. Please verify and re-enter the data.";
				
			if(SeleniumFunc.GetElementText("css", "#content > div > div.alert.alert-error").equals(ExpectedText))
			{
				System.out.println("Success !! Validation message displayed.");
				Reporter.log("Success !! Validation message displayed.");				
			}
			else
			{
				System.out.println("Failure !! Validation message not found.");
				Reporter.log("Failure !! Validation message not found.");
				AssertFailedCount++;
			}
			
	
	System.out.println("Step 15 : Fill out and submit the form using the credit card number 2272098111111111.");
	Reporter.log("Step 15 : Fill out and submit the form using the credit card number 2272098111111111."); 			
			

				registerstudent.EnterCardNumber("2272098111111111");
				registerstudent.EnterExpDate("2025");
				registerstudent.EnterFirstNamePayment("Automation");
				registerstudent.EnterSurName("Testing");
				registerstudent.ClickOnSameAddressCheckbox();
				Thread.sleep(2000);
				registerstudent.ClickOnAgreePolicyCheckBox();
				registerstudent.ClickOnCompleteRegistrationButton();
				Thread.sleep(5000);
				
			if(SeleniumFunc.GetElementText("css", "#content > div > div.alert.alert-error").equals(ExpectedText))
			{
				System.out.println("Success !! Validation message displayed.");
				Reporter.log("Success !! Validation message displayed.");				
			}
			else
			{
				System.out.println("Failure !! Validation message not found.");
				Reporter.log("Failure !! Validation message not found.");
				AssertFailedCount++;
			}
			
	System.out.println("Step 16 : Fill out and submit the form using the credit card number 1121111111111111.");
	Reporter.log("Step 16 : Fill out and submit the form using the credit card number 1121111111111111."); 			
					

				registerstudent.EnterCardNumber("1121111111111111");
				registerstudent.EnterExpDate("2025");
				registerstudent.EnterFirstNamePayment("Automation");
				registerstudent.EnterSurName("Testing");
				registerstudent.ClickOnSameAddressCheckbox();
				Thread.sleep(2000);
				registerstudent.ClickOnAgreePolicyCheckBox();
				registerstudent.ClickOnCompleteRegistrationButton();
				Thread.sleep(5000);
				String expected1 = "The following issues were found:";
				String expected2 = "Card Number";
				
			if(SeleniumFunc.GetElementText("css", "#alert_validation > p > strong").equals(expected1)
					&& SeleniumFunc.GetElementText("css", "#alert_validation > ul > li").contains(expected2)
					&& SeleniumFunc.GetElementText("id", "card_number_errorMsg").equals("Invalid input."))
			{
				System.out.println("Success !! Validation message displayed.");
				Reporter.log("Success !! Validation message displayed.");				
			}
			else
			{
				System.out.println("Failure !! Validation message not found.");
				Reporter.log("Failure !! Validation message not found.");
				AssertFailedCount++;
			}			
			
			
	System.out.println("Step 17 : Fill out and submit the form using the credit card number 7121111111111111.");
	Reporter.log("Step 17 : Fill out and submit the form using the credit card number 7121111111111111."); 			
					

				registerstudent.EnterCardNumber("7121111111111111");
				registerstudent.EnterExpDate("2025");
				registerstudent.EnterFirstNamePayment("Automation");
				registerstudent.EnterSurName("Testing");
				registerstudent.ClickOnSameAddressCheckbox();
				Thread.sleep(2000);
				registerstudent.ClickOnAgreePolicyCheckBox();
				registerstudent.ClickOnCompleteRegistrationButton();
				Thread.sleep(5000);
						
			if(SeleniumFunc.GetElementText("css", "#alert_validation > p > strong").equals(expected1)
						&& SeleniumFunc.GetElementText("css", "#alert_validation > ul > li").contains(expected2)
						&& SeleniumFunc.GetElementText("id", "card_number_errorMsg").equals("Invalid input."))
			{
				System.out.println("Success !! Validation message displayed.");
				Reporter.log("Success !! Validation message displayed.");				
			}
			else
			{
				System.out.println("Failure !! Validation message not found.");
				Reporter.log("Failure !! Validation message not found.");
				AssertFailedCount++;
			}			
			
			//Delete Event
			
			SeleniumFunc.ToGoToUrl(URL);
			SeleniumFunc.AcceptAlertAndGetText();
			Thread.sleep(5000);	
			SeleniumFunc.ClickOnElement("linkText", "Delete Event");			
			Thread.sleep(2000);		
			SeleniumFunc.ClickOnElement("linkText", "Delete Event");
			Thread.sleep(2000);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/payment/174");
			//agencyhome.ClickOnPaymentSettingsLink();
			Thread.sleep(5000);

			paymentsettings.SelectPaymentMode("Off");
			//paymentsettings.SelectProductCode("EME57265 - Demo Hunter Education Field Day");
			Thread.sleep(3000);
			paymentsettings.ClickOnSaveChanges();			
			Thread.sleep(5000);
			SeleniumFunc.ClickOnElement("linkText", "Log Out");
			System.out.println("Event: "+EventID+" Deleted Successfully!!");
			
			// Marking Test Pass or Fail as per the value of AssertFailedCount variable
			 
		 	if(AssertFailedCount>0)	
			{
				
				//Marking this test as Failed
				
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
				
				Assert.fail();
			}
			
		}
	}
