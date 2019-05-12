package products.EM;


import java.io.File;
import java.util.concurrent.TimeUnit;

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
import pageFactory.EM.ErrorPage;
import pageFactory.EM.InstructorCreateEventPage;
import pageFactory.EM.InstructorEventRosterPage;
import pageFactory.EM.InstructorHomePage;
import pageFactory.EM.InstructorLocationPage;
import pageFactory.EM.InstructorSchedulePage;
import pageFactory.EM.LoginPage;
import pageFactory.EM.ManageProgramFilesPage;
import pageFactory.EM.PaymentSettingsPage;
import pageFactory.EM.RegisterEdEventView;
import pageFactory.EM.RegisterEdRegisterStudentPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class RegistrationThirdStepTest 
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
	 * Verify user is redirected to Registration step 3
	 */
	@Test
	private void VerifyUserRedirectedToStep3() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		System.out.println("====" + "\n" +
					"Test 1 : Verify user is redirected to Registration step 3"  + "\n" +"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify user is redirected to Registration step 3"  + "\n" +  "====");	
		
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
			//instructorcreateevent.SelectDate("5");
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

			//login.ClickOnLogoutButton();
			//Thread.sleep(5000);

			
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
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerstudent.Next);
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);

			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(8000);
			//registerstudent.ClickOnIUnderstand();
			//Thread.sleep(5000);
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
			
				System.out.println("Failure !! Error while registration.");
				Reporter.log("Failure !! Error while registration.");
				
				AssertFailedCount++;
			
			}
			Thread.sleep(8000);
			((JavascriptExecutor)driver).executeScript("scroll(244,785)");
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
			
/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			/*
			try {
				//Go to instructor and delete the event
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
			
			Thread.sleep(5000);
			
			
			SeleniumFunc.ClickOnElement("linkText", "Unschedule and Remove Event");

			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("scroll(990,774)");
			SeleniumFunc.ClickOnElement("css", "body > div.stage > div.content > div.clear > a:nth-child(1) > span");
			
			Thread.sleep(2000);

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
	

	/* 
	Test 2: 
	 Verify that step number preview 1/2/3 is displayed
	 */
	@Test
	private void StepPreview() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify user can add valid details and go to step 2"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify user can add valid details and go to step 2"  + "\n" +
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
        //WebDriverWait wait = new WebDriverWait(driver, 20);

        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS); 
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
			/*SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			Thread.sleep(5000);*/

			String eventid= instructorschedule.EventId.getText().trim();
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

			
			//Go to EM and set Payment Off
			PaymentSettingsPage payment = new PaymentSettingsPage(driver);	
			AgencyHomePage agencyhome= new AgencyHomePage(driver);
			((JavascriptExecutor)driver).executeScript("scroll(477,1126)");
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

			//Thread.sleep(10000);
			//wait.until(ExpectedConditions.elementToBeClickable(agencyhome.Program));

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
			searchevent.EnterSearchData(EventID);			searchevent.EnterSearchData(EventID);
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
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerstudent.Next);
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);
			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(5000);

			
			//Verify user is redirected to second step of registration
			expectedtext = "almost done with registration!";
			actualtext = SeleniumFunc.GetElementText("css", ".alert-heading");
		
			if(actualtext.contains(expectedtext))
			{
				
				System.out.println("Success !! User is redirected to second step of registration.");
				Reporter.log("Success !! User is redirected to second step of registration.");
			
			}
			else
			{
			
				System.out.println("Failure !! Error while registration." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Error while registration." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			//SeleniumFunc.ClickOnElement("xpath", ".//*[@id='filesModal']/div[3]/button");
			Thread.sleep(5000);
			((JavascriptExecutor)driver).executeScript("scroll(244,785)");
			registerstudent.ClickOnAgreePolicyCheckBox();
			Thread.sleep(2000);
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
			
			
			//Verify that step number preview 1/2/3 is displayed
			
			//expectedtext = "You’re almost done with registration!";
			//expectedtext ="Add Students ⟩ Review & Confirm ⟩ 3 Review & Confirm";
			actualtext = SeleniumFunc.GetElementText("css", "#content div ol");
	
			if(actualtext.contains("Add Students") &&actualtext.contains("Review & Confirm") && actualtext.contains("Review & Confirm"))
			{
			
				System.out.println("Success !! Breadcrumb is displayed with proper text. i.e.  " + expectedtext);
				Reporter.log("Success !! Breadcrumb is displayed with proper text. i.e.  " + expectedtext);
		
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
			
			
				//Go to instructor and delete the event
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);

				login.ClickOnLogInButton();			
				Thread.sleep(5000);

				//Going to Event Roster page and removing student and deleting  event 
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/event/admin/" + eventid);
				SeleniumFunc.ClickOnElement("linkText", "Cancel Event");
				Thread.sleep(2000);
				SeleniumFunc.ClickOnElement("linkText", "Unschedule and Remove Event");
				Thread.sleep(2000);
				((JavascriptExecutor)driver).executeScript("scroll(990,774)");
				SeleniumFunc.ClickOnElement("css", "body > div.stage > div.content > div.clear > a:nth-child(1) > span");
				
				/*instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();*/
				System.out.println("Event: "+eventid+" Deleted Successfully!!");
				Thread.sleep(5000);
			
		
		
		 // Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 
	 	if(AssertFailedCount>0)	
		{
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
	
	
	
	
	/*
	   Test 3: 
	   Verify Event details are displayed on Registration step 3
	 */
	@Test
	private void EventDetailsStep3() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify Event details are displayed on Registration step 3"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify Event details are displayed on Registration step 3"  + "\n" +
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
			((JavascriptExecutor)driver).executeScript("scroll(1027,987)");	
			instructorcreateevent.CreateEvent.click();
			Thread.sleep(5000);

			String URL = driver.getCurrentUrl();

		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
					
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			/*SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			Thread.sleep(5000);*/

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
			
			String eventid= instructorschedule.EventId.getText().trim();
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
					
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency/"); 
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
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerstudent.Next);
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);
			
			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(5000);

			//Verify user is redirected to second step of registration
			expectedtext = "almost done with registration!";
			actualtext = SeleniumFunc.GetElementText("css", ".alert-heading");
		
			if(actualtext.endsWith(expectedtext))
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
			
			//SeleniumFunc.ClickOnElement("xpath", ".//*[@id='filesModal']/div[3]/button");
			Thread.sleep(5000);
			((JavascriptExecutor)driver).executeScript("scroll(244,785)");
			registerstudent.ClickOnAgreePolicyCheckBox();
			Thread.sleep(2000);
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
								+ "\n" + expectedtext + "\n" + "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Error while registration" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + "Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
	
			}				
			
			
			//Verify Event Details
			
			//Name
			expectedtext = "Demo Hunter Education Field Day";
			actualtext = SeleniumFunc.GetElementText("css", ".program-name.summary");
	
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
			
				System.out.println("Success !! Event Name is displayed with proper text. i.e.  " + expectedtext);
				Reporter.log("Success !! Event Name is displayed with proper text. i.e.  " + expectedtext);
		
			}
			else
			{
		
				System.out.println("Failure !! Event Name is NOT proper." + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event Name is NOT proper." + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + "Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
		
			}
			
			
			//Location
			String expected = "Starting on";
			expectedtext = "at 6:30pm at Demo Agency Location in Norfolk, CO.";
			actualtext = SeleniumFunc.GetElementText("css", ".program-event-details");
	
			if(actualtext.startsWith(expected) && actualtext.endsWith(expectedtext))
			{
			
				System.out.println("Success !! Event Location is displayed with proper text. i.e.  " + expectedtext);
				Reporter.log("Success !! Event Location is displayed with proper text. i.e.  " + expectedtext);
		
			}
			else
			{
		
				System.out.println("Failure !! Event Location is NOT proper." + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event Location is NOT proper." + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + "Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
		
			}
			
			
			
			
			/*
			try {
				//Go to instructor and delete the event
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				login.EnterUsername(Constants.EM_Instructor_Username);
				login.EnterPassword(Constants.EM_Instructor_Password);

				login.ClickOnLogInButton();			
				Thread.sleep(5000);

				//Going to Event Roster page and removing student and deleting  event 
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				System.out.println("Event: "+eventid+" Deleted Successfully!!");
				Thread.sleep(5000);
			} catch (Exception e) {
				
			}*/
			
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
			
		
		
		 // Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 
	 	if(AssertFailedCount>0)	
		{
	 		/*
	 		SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
			instructoreventroster.EventDelete.click();
			Thread.sleep(5000);

			instructoreventroster.Confirm_EventDelete.click();
			System.out.println("Event: "+eventid+" Deleted Successfully!!");
			Thread.sleep(5000);
			
			//Marking this test as Failed*/
	 		
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
		
	}	
	

	

	 /*Test 4: 
	  Verify title on Registration step 3
	 */
	@Test
	private void VerifyTitleStep3() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify title on Registration step 3"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify title on Registration step 3"  + "\n" +
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

			//login.ClickOnLogoutButton();
			//Thread.sleep(5000);

			
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
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerstudent.Next);
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);
			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(8000);
			//registerstudent.ClickOnIUnderstand();
			//Thread.sleep(8000);
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
			
				System.out.println("Failure !! Error while registration.");
				Reporter.log("Failure !! Error while registration.");
				
				AssertFailedCount++;
			
			}
			((JavascriptExecutor)driver).executeScript("scroll(244,785)");
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
			
			
			//Verify Title
			
			expectedtext = "Registration Complete";
			actualtext = SeleniumFunc.GetElementText("css", ".no-print h2");
	
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
			
				System.out.println("Success !! Correct title is displayed with proper text. i.e.  " + expectedtext);
				Reporter.log("Success !! Correct title is displayed with proper text. i.e.  " + expectedtext);
		
			}
			else
			{
		
				System.out.println("Failure !! Correct title is NOT proper." + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Correct title is NOT proper." + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							"Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
		
			}
			
			/*
			try {
				//Go to instructor and delete the event
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
			
			Thread.sleep(5000);
			
			
			SeleniumFunc.ClickOnElement("linkText", "Unschedule and Remove Event");

			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("scroll(990,774)");
			SeleniumFunc.ClickOnElement("css", "body > div.stage > div.content > div.clear > a:nth-child(1) > span");
			
			Thread.sleep(2000);

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
	
	
	 /*Test 5: 
	     Verify Print option is present at step 3
	 */
	@Test
	private void VerifyPrintOptionStep3() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify Print option is prenset at step 3"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify Print option is prenset at step 3"  + "\n" +
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
									+ "\n" + expectedtext + "\n" + 		 "Actual: " + "\n" +  actualtext);
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
									+ "\n" + expectedtext + "\n" + "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Name" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + "Actual: " + "\n" +  actualtext);
				
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

			//login.ClickOnLogoutButton();
			//Thread.sleep(5000);

			
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
									+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! User is NOT on Registration page." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
				
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
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerstudent.Next);
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);
		
			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(8000);

			//registerstudent.ClickOnIUnderstand();
			//Thread.sleep(5000);
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
									+ "\n" + expectedtext + "\n" +  "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Error while registration." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			((JavascriptExecutor)driver).executeScript("scroll(244,785)");
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
			
			
			//Verify Print Option
			
			expectedtext = "print this page";
			actualtext = SeleniumFunc.GetElementText("css", ".print");
	
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
			
				System.out.println("Success !! Print Option is displayed with proper text. i.e.  " + expectedtext);
				Reporter.log("Success !! Print Option is displayed with proper text. i.e.  " + expectedtext);
		
			}
			else
			{
		
				System.out.println("Failure !! Print Option is NOT proper." + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Print Option is NOT proper." + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							"Actual: " + "\n" +  actualtext);
			
				AssertFailedCount++;
		
			}
			
			/*
			
		try {
			//Go to instructor and delete the event
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
			
			Thread.sleep(5000);
			
			
			SeleniumFunc.ClickOnElement("linkText", "Unschedule and Remove Event");

			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("scroll(990,774)");
			SeleniumFunc.ClickOnElement("css", "body > div.stage > div.content > div.clear > a:nth-child(1) > span");
			
			Thread.sleep(2000);

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

	
	 /*Test 6: 
    Verify Event policies and instructions section on step 3
*/
@Test
private void VerifyEventPoliciesInstructions() throws Exception
{
	System.out.println("====" + "\n" +
				"Test 6 : Verify Event policies and instructions section on step 3"  + "\n" +
	 			"====");
	Reporter.log("====" + "\n" +
	 			  "Test 6 : Verify Event policies and instructions section on step 3"  + "\n" +
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

		String eventid= instructorschedule.EventId.getText().trim();
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
		searchevent.EnterSearchData(EventID);			searchevent.EnterSearchData(EventID);
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
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerstudent.Next);
		registerstudent.ClickOnNextButton();
		Thread.sleep(5000);
		registerstudent.ClickOnReviewAndConfirmButton();
		Thread.sleep(5000);

		
		//Verify user is redirected to second step of registration
		expectedtext = "almost done with registration!";
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
		
		//SeleniumFunc.ClickOnElement("xpath", ".//*[@id='filesModal']/div[3]/button");
		Thread.sleep(5000);
		((JavascriptExecutor)driver).executeScript("scroll(244,785)");
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
		
		
		//Verify Event Policies and Instructions
		
		expectedtext = "Event Policies and Instructions";
		actualtext = SeleniumFunc.GetElementText("css", "#primary_content div h3:nth-of-type(2)");

		if(actualtext.equalsIgnoreCase(expectedtext))
		{
		
			System.out.println("Success !! Event Policies and Instructions section is displayed with proper text. i.e.  " + expectedtext);
			Reporter.log("Success !! Event Policies and Instructions section is displayed with proper text. i.e.  " + expectedtext);
	
		}
		else
		{
	
			System.out.println("Failure !! Event Policies and Instructions section is NOT proper." + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
			Reporter.log("Failure !! Event Policies and Instructions section is NOT proper." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						"Actual: " + "\n" +  actualtext);
		
			AssertFailedCount++;
	
		}
		
		
		//Agreement Section 
		if(SeleniumFunc.IsElementPresent("css", ".alert.alert-block.alert-info.alert-agreements"))
		{
			
			System.out.println("Success !! Agreement details is present");
			Reporter.log("Success !! Agreement details is present");
	
		}
		else
		{
			
			System.out.println("Success !! Agreement details is NOT present");
			Reporter.log("Success !! Agreement details is NOT present");
			
		}
		
	
			//Go to instructor and delete the event
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);

			login.ClickOnLogInButton();			
			Thread.sleep(5000);

			//Going to Event Roster page and removing student and deleting  event 
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/event/admin/" + eventid);
			
			SeleniumFunc.ClickOnElement("linkText", "Cancel Event");
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("linkText", "Unschedule and Remove Event");
			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("scroll(990,774)");
			SeleniumFunc.ClickOnElement("css", "body > div.stage > div.content > div.clear > a:nth-child(1) > span");
			System.out.println("Event: "+eventid+" Deleted Successfully!!");
			
			/*instructoreventroster.EventDelete.click();
			Thread.sleep(5000);

			instructoreventroster.Confirm_EventDelete.click();
			
			Thread.sleep(5000);*/
			

		
	
	
	 // Marking Test Pass or Fail as per the value of AssertFailedCount variable
	 
	if(AssertFailedCount>0)	
	{
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

	
	 /*Test 7: 
	 *Verify presence of 'what's Next' section
	  */
	@Test
	private void VerifyWhatNextSection() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		System.out.println("====" + "\n" +
					"Test 7 : Verify presence of 'what's Next' section"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Verify presence of 'what's Next' section"  + "\n" +
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

			//login.ClickOnLogoutButton();
			//Thread.sleep(5000);

			
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
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerstudent.Next);
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);
			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(8000);
			//registerstudent.ClickOnIUnderstand();
			
			//Verify user is redirected to second step of registration
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
			
				System.out.println("Failure !! Error while registration." + "\n");
				Reporter.log("Failure !! Error while registration.");
				
				AssertFailedCount++;
			
			}
			Thread.sleep(8000);
			((JavascriptExecutor)driver).executeScript("scroll(244,785)");
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
			
			
			//Verify What Next Section
			/*
			expectedtext = "What’s next?"  + "\n" + 
					"Your registration for Demo Hunter Education Field Day is complete. Registration was easy, but don’t forget to attend! Add the event to your calendar as a reminder. You may also:"  +"\n"+
					"Visit Demo Hunter Education Field Day to review the event or share it with your friends." +"\n"+
					"Visit Demo Agency to learn more about this provider." +"\n"+
					"Visit Register-Ed to discover other Events in your area." +"\n"+
					"Visit the Kalkomey Bookstore to get the official handbooks, available in eBook formats.";*/
			
			String expectedtextS = "What"; 
			String expectedtextE =	"Your registration for Demo Hunter Education Field Day is complete. Registration was easy, but don�t forget to attend! Add the event to your calendar as a reminder. You may also:"  +"\n"+
					"Visit Demo Hunter Education Field Day to review the event or share it with your friends." +"\n"+
					"Visit Demo Agency to learn more about this provider." +"\n"+
					"Visit Register-Ed to discover other Events in your area." +"\n"+
					"Visit the Kalkomey Bookstore to get the official handbooks, available in eBook formats.";
			
			
			actualtext = SeleniumFunc.GetElementText("xpath", ".//*[@id='primary_content']/div[2]");
	
			if(actualtext.startsWith(expectedtextS) && actualtext.contains(expectedtextE) )
			{
			
				System.out.println("Success !! What's next section is displayed with proper text");
				Reporter.log("Success !! What's next section is displayed with proper text. ");
		
			}
			else
			{
		
				System.out.println("Failure !! What's next section is NOT proper." );
				Reporter.log("Failure !! What's next section is NOT proper." );
			
				AssertFailedCount++;
		
			}
			
			
			/*
			try {
				//Go to instructor and delete the event
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
			
			Thread.sleep(5000);
			
			
			SeleniumFunc.ClickOnElement("linkText", "Unschedule and Remove Event");

			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("scroll(990,774)");
			SeleniumFunc.ClickOnElement("css", "body > div.stage > div.content > div.clear > a:nth-child(1) > span");
			
			Thread.sleep(2000);

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
	

	 /*Test 8: 
	 * Verify confirmation email is received for registration
	  */
	@Test
	private void ConfirmationEmailReceived() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Verify confirmation email is recieved for registration"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Verify confirmation email is recieved for registration"  + "\n" +
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
			searchevent.EnterSearchData(EventID);			searchevent.EnterSearchData(EventID);
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
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerstudent.Next);
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);
			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(5000);

			
			//Verify user is redirected to second step of registration
			expectedtext = "You’re almost done with registration!";
			actualtext = SeleniumFunc.GetElementText("css", ".alert-heading");
		
			if(actualtext.equalsIgnoreCase(expectedtext))
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
			((JavascriptExecutor)driver).executeScript("scroll(244,785)");
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
			
			
		System.out.println("Step 10 : Navigating to Email Box and verifying whether email is received with correct details or not");
		Reporter.log("Step 10 : Navigating to Email Box and verifying whether email is received with correct details or not"); 
					
				
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=emautomation#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
					
				
			actualtext= SeleniumFunc.GetElementText("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div").trim();						
			expectedtext="Thanks for registering"; 
					
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
			
			
			
			try {
				//Go to instructor and delete the event
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
	

	 /*Test 9: 
	 * Verify content of email
	  */
	
	@Test
	private void VerifyContentEmail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Verify content of email"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 : Verify content of email"  + "\n" +
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
			searchevent.EnterSearchData(EventID);			searchevent.EnterSearchData(EventID);
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
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerstudent.Next);
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);
			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(5000);

			//registerstudent.ClickOnIUnderstand();
			//Thread.sleep(8000);
			//Verify user is redirected to second step of registration
			expectedtext = "You’re almost done with registration!";
			actualtext = SeleniumFunc.GetElementText("css", ".alert-heading");
		
			if(actualtext.equalsIgnoreCase(expectedtext))
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
			((JavascriptExecutor)driver).executeScript("scroll(244,785)");
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
			
			
		System.out.println("Step 10 : Navigating to Email Box and verifying whether email is received with correct details or not");
		Reporter.log("Step 10 : Navigating to Email Box and verifying whether email is received with correct details or not"); 
					
				
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=emautomation#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
					
			
			//Verify Title
			actualtext= SeleniumFunc.GetElementText("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div").trim();						
			expectedtext="Thanks for registering"; 
					
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
					
				System.out.println("Success!! Email is received with correct Title. i.e. " +  expectedtext);
				Reporter.log("Success!! Email is received with correct Title. i.e. " +  expectedtext);
				
			}
			else
			{
					
				System.out.println("Failure !!  Email is received with incorrect Title" + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" +  "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !!Email is received with incorrect Title" + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" +  "Actual Text : " + "\n" +  actualtext);
						
				AssertFailedCount++;
				
			}
			
			
			//Verify Content
			SeleniumFunc.ClickOnElement("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");
			Thread.sleep(5000);
			
			driver.switchTo().frame("publicshowmaildivcontent");
			
			//Verifying link to Login	
		
			if(SeleniumFunc.IsElementPresent("css", "tbody>tr>td>a"))
			{
				
				System.out.println("Success!! Link to Login a/c is present");
				Reporter.log("Success!! Link to Login a/c is present");
				
			}
			else
			{
				
				System.out.println("Success!! Link to Login a/c is NOT present");
				Reporter.log("Success!! Link to Login a/c is NOT present");
				
			}
			
	
			String UpdateRegi = SeleniumFunc.GetElementText("css", "tbody>tr>td>a");
			
			SeleniumFunc.ToGoToUrl(UpdateRegi);
			Thread.sleep(5000);

			
		System.out.println("Step 11 : Go to link provided in email and verify control redirected to identity verification page.");
		Reporter.log("Step 11 : Go to link provided in email and verify control redirected to identity verification page."); 
						
		
			SeleniumFunc.ToGoToUrl(UpdateRegi);
			Thread.sleep(5000);

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
			
				System.out.println("Failure !! Incorrect Header is displayed" + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect Header is displayed" + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + "Actual Text : " + "\n" +  actualtext);
					
				AssertFailedCount++;
		
			}	
	
			
			try {
				//Go to instructor and delete the event
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
	
	
	 /*Test 10: 
	 * Verify that step number preview 1/2/3 is displayed
	  */
	@Test
	private void VerifyHeaderFooter() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : Verify user can add valid details and go to step 2"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10 : Verify user can add valid details and go to step 2"  + "\n" +
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

			//login.ClickOnLogoutButton();
			//Thread.sleep(5000);

			
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
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerstudent.Next);
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);
			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(8000);
			//registerstudent.ClickOnIUnderstand();
			//Thread.sleep(8000);
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
									+ "\n" + expectedtext + "\n" + "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Error while registration." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			((JavascriptExecutor)driver).executeScript("scroll(244,785)");
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
			
			
					
			//Verify Footer is present
		
			if(SeleniumFunc.IsElementPresent("css", ".row.kalkomey-info"))
			{
				
				System.out.println("Success!! Footer is present");
				Reporter.log("Success!! Footer is present");
				
			}
			else
			{
				
				System.out.println("Success!! Footer is NOT present");
				Reporter.log("Success!! Footer is NOT present");
				
			}
			
	
			//Verify Header is present
			
			if(SeleniumFunc.IsElementPresent("css", ".division"))
			{
				
				System.out.println("Success!! Header is present");
				Reporter.log("Success!! Header is present");
				
			}
			else
			{
				
				System.out.println("Success!! Header is NOT present");
				Reporter.log("Success!! Header is NOT present");
				
			}
			
			
			/*
			try {
				//Go to instructor and delete the event
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
			
			Thread.sleep(5000);
			
			
			SeleniumFunc.ClickOnElement("linkText", "Unschedule and Remove Event");

			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("scroll(990,774)");
			SeleniumFunc.ClickOnElement("css", "body > div.stage > div.content > div.clear > a:nth-child(1) > span");
			
			Thread.sleep(2000);

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


	 /*Test 11: 
	 * Verify user can hide/unhide student details
	 */
	@Test
	private void VerifyHideUnhideStudentDetails() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 11 : Verify user can hide/unhide student details"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 11 : Verify user can hide/unhide student details"  + "\n" +
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

			//login.ClickOnLogoutButton();
			//Thread.sleep(5000);

			
		System.out.println("Step 7 : Go to Register-Ed and Registring for a student - reaching to Step 3 = 'Complete the Registration'");
		Reporter.log("Step 7 : Go to Register-Ed and Registring for a student - reaching to Step 3 = 'Complete the Registration'"); 
		
			
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
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerstudent.Next);
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);
			registerstudent.ClickOnReviewAndConfirmButton();			
			Thread.sleep(4000);
			//registerstudent.ClickOnIUnderstand();
			Thread.sleep(8000);
			((JavascriptExecutor)driver).executeScript("scroll(244,785)");
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
			
		System.out.println("Step 8 : Click on 'show student details' link and Verifying that 'student details' section is shown" );
		Reporter.log("Step 8 : Click on 'show student details' link and Verifying that 'student details' section is shown" );
		
			//Click on 'show student details' link
			SeleniumFunc.ClickOnElement("css", "i.icon-plus");
			Thread.sleep(4000);
		
			//Verifying that 'student details' section is shown
			expectedtext = "height: auto;";
			actualtext = SeleniumFunc.GetAttributeValue("css", "div.student-details", "style").trim();
			
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
				
				System.out.println("Success !! 'student details' section is shown");
				Reporter.log("Success !! 'student details' section is shown");
			
			}
			else
			{
			
				System.out.println("Failure !!'student details' section is not shown" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! 'student details' section is not shown" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			
			
		System.out.println("Step 9 : Click on 'show student details' link again and Verifying that 'student details' section is hidden" );
		Reporter.log("Step 9 : Click on 'show student details' link again and Verifying that 'student details' section is hidden");

			
			//Click on 'show student details' link
			SeleniumFunc.ClickOnElement("css", "i.icon-minus");
			Thread.sleep(4000);
	
			//Verifying that 'physical address' section is hidden
			expectedtext = "height: 0px;";
			actualtext = SeleniumFunc.GetAttributeValue("css", "div.student-details", "style").trim();
			
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
				
				System.out.println("Success !! 'student details' section is hidden");
				Reporter.log("Success !! 'student details' section is hidden");
			
			}
			else
			{
			
				System.out.println("Failure !! 'student details' section is not hidden" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! 'student details' section is not hidden" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
		
			/*
			try {
				//Go to instructor and delete the event
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
				
			}
			*/
			
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
		
		
		 // Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 
	 	if(AssertFailedCount>0)	
		{
			
			//Marking this test as Failed
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
		
	}
	
	
	
	 /*Test 12: 
	 * Verify User Download File
	  */
	
	@Test
	private void VerifyUserDownloadFile() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 12 : Verify User Download File"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 12 : Verify User Download File"  + "\n" +
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
		ManageProgramFilesPage managefiles = new ManageProgramFilesPage(driver);
		
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
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");
			Thread.sleep(5000);
			//Upload a File
			//instructoreventroster.SelectAction("Upload or Delete Files");
			//Thread.sleep(5000);
			SeleniumFunc.ClickOnElement("linkText", "Upload or Delete Files");
			Thread.sleep(5000);
			File file = new File("src/test/resources/SampleFileUpload.txt");
			SeleniumFunc.SelectValueFromDropdownList("css", "select[name='private']", "No");
			SeleniumFunc.EnterValueInTextbox("css", "input[name='file']", file.getAbsolutePath());
			managefiles.EnterDescription("Automation Verification");
			managefiles.ClickOnUploadButton();
			Thread.sleep(5000);
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

			//login.ClickOnLogoutButton();
			//Thread.sleep(5000);
		
		System.out.println("Step 7 : Go to Register-Ed and verify UI of first page of registration");
		Reporter.log("Step 7 : Go to Register-Ed and verify functionality of Register Now button"); 
				
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
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerstudent.Next);
			registerstudent.ClickOnNextButton();
			Thread.sleep(5000);
			registerstudent.ClickOnReviewAndConfirmButton();
			Thread.sleep(8000);
			registerstudent.ClickOnIUnderstand();
			Thread.sleep(5000);
			//SeleniumFunc.ClickOnElement("css", ".btn.cancel");
			//Thread.sleep(5000);

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
			
				System.out.println("Failure !! Error while registration.");
				Reporter.log("Failure !! Error while registration.");
				
				AssertFailedCount++;
			
			}
			
			Thread.sleep(8000);
			((JavascriptExecutor)driver).executeScript("scroll(244,785)");
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
			
			
		/*	
			// Store the current window handle
			String winHandleBefore = driver.getWindowHandle();

			// Switch to new window opened
			SeleniumFunc.ClickOnElement("css", ".unstyled.file-list.mts li a");
			Thread.sleep(5000);

			
			for(String winHandle : driver.getWindowHandles()){
				    driver.switchTo().window(winHandle);
				}
			
			
			//Verify File is download
			actualtext = SeleniumFunc.GetElementText("css", "html body pre");
			expectedtext = "Sample File Upload";
			if(expectedtext.equalsIgnoreCase(actualtext))
			{
				
				System.out.println("Success !! File is downloaded successfully");
				Reporter.log("Success !! File is downloaded successfully");
			
			}
			else
			{
			
				System.out.println("Failure !! File is NOT downloaded." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! File is NOT downloaded." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			
			// Close the new window, if that window no more required
			driver.close();

			// Switch back to original browser (first window)
			driver.switchTo().window(winHandleBefore);
			Thread.sleep(5000);
*/
			/*			
			try {
				//Go to instructor and delete the event
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
			
			Thread.sleep(5000);
			
			
			SeleniumFunc.ClickOnElement("linkText", "Unschedule and Remove Event");

			Thread.sleep(2000);
			((JavascriptExecutor)driver).executeScript("scroll(990,774)");
			SeleniumFunc.ClickOnElement("css", "body > div.stage > div.content > div.clear > a:nth-child(1) > span");
			
			Thread.sleep(2000);

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
