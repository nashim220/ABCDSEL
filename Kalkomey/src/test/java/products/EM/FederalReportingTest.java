package products.EM;

 
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

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
import pageFactory.EM.AgencyCreateUserPage;
import pageFactory.EM.AgencyHomePage;
import pageFactory.EM.EMAddStudentPage;
import pageFactory.EM.ErrorPage;
import pageFactory.EM.InstructorCreateEventPage;
import pageFactory.EM.InstructorEventRosterPage;
import pageFactory.EM.InstructorHomePage;
import pageFactory.EM.InstructorLocationPage;
import pageFactory.EM.InstructorSchedulePage;
import pageFactory.EM.LoginPage;
import pageFactory.EM.ProgramSettingsPage;
import pageFactory.EM.StudentResultPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class FederalReportingTest 
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
	 * Verify User can enable and disable Federal Reporting feature
	*/ 
	@Test
	private void EnableDisableFederalReporting() throws Exception
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
		ProgramSettingsPage programsettings = new ProgramSettingsPage(driver);
		
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

		  	agencyhome.ClickOnProgramSettingsLink();
			Thread.sleep(5000);

		  	
		System.out.println("Step 4 : Verify Title for Program Settings");
		Reporter.log("Step 4 : Verify Title for Program Settings");
				
			String Actual = programsettings.Title.getText().trim();
			String Expected = "Program Settings";
					
			if(Actual.equals(Expected))
			{
	
				System.out.println("Success !! Title is correct for Program Settings.");
				Reporter.log("Success !! Title is correct for Program Settings.");
			
			}
			else
			{
			
				System.out.println("Failure !! Title is NOT correct for Program Settings." + "\n" + "Expected : "+ "\n" + Expected + "\n" + 
										 "Actual: " + "\n" +  Actual);
				Reporter.log("Failure !! Title is NOT correct for Program Settings." + "\n" + "Expected : "  
						+ "\n" + Expected + "\n" + 
						 "Actual: " + "\n" +  Actual);
						
				AssertFailedCount++;
			
			}		  		
				
			
					
		System.out.println("Step 5 : Set Federal Reporting to No and Verify Success Message for save changes");
		Reporter.log("Step 5 : Set Federal Reporting to No and Verify Success Message for save changes");
						
						
			programsettings.SelectFederalReportingOption("No");
			
		  	programsettings.ClickOnSaveChanges();
			Thread.sleep(5000);

			Actual = programsettings.Success.getText().trim();
			Expected = "Program settings saved.";
				
			if(Actual.equals(Expected))
			{
			
				System.out.println("Success !! Program Settings Saved.");
				Reporter.log("Success !! Program Settings Saved.");
		
			}
			else
			{
			
				System.out.println("Failure !! Program Settings is NOT Saved." + "\n" + "Expected : " + "\n" + Expected + "\n" + 
										 "Actual: " + "\n" +  Actual);
				Reporter.log("Failure !! Program Settings is NOT Saved." + "\n" + "Expected : "  + "\n" + Expected + "\n" + 
						 "Actual: " + "\n" +  Actual);
					
				AssertFailedCount++;
			
			}		  	
		
				
		System.out.println("Step 6 : Verify Federal Reporting Set To NO");
		Reporter.log("Step 6 : Verify Federal Reporting Set To NO");
							
			Expected = "No";
			Actual = SeleniumFunc.GetSelectedValueFromDropdownList("css", "#federal_reporting");
			
			if(Actual.equals(Expected))
			{
			
				System.out.println("Success !! Federal Reporting Setings saved and Set to NO.");
				Reporter.log("Success !! Federal Reporting Setings saved and Set to NO.");
			
			}
			else
			{
				System.out.println("Failure !! Federal Reporting Setings NOT saved." + "\n" + "Expected : "  
									+ "\n" + Expected );
				Reporter.log("Failure !! Federal Reporting Setings NOT saved." + "\n" + "Expected : "  
							+ "\n" + Expected );
					
				AssertFailedCount++;
			
			}		  	
				
		  	
			programsettings.SelectFederalReportingOption("Yes");
		  	programsettings.ClickOnSaveChanges();
			Thread.sleep(5000);

		  	
		  	Expected = "Yes";
		  	Actual = SeleniumFunc.GetSelectedValueFromDropdownList("css", "#federal_reporting");
			
			if(Actual.equals(Expected))
			{
				
				System.out.println("Success !! Federal Reporting Setings saved and Set to Yes.");
				Reporter.log("Success !! Federal Reporting Setings saved and Set to Yes.");
			
			}
			else
			{
			
				System.out.println("Failure !! Federal Reporting Setings NOT saved." + "\n" + "Expected : "  
									+ "\n" + Expected );
				Reporter.log("Failure !! Federal Reporting Setings NOT saved." + "\n" + "Expected : "  
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
	 * Verify Newly created instructor need to verify his email ID to access different feature
	*/ 
	@Test
	private void NewInstructorNeedEmailVerification() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 2 : Verify Newly created instructor need to verify his email ID to access different feature"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 2 : Verify Newly created instructor need to verify his email ID to access different feature"  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);

		
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

		
		System.out.println("Step 3 : Select a Program and go to Create Instructor");
		Reporter.log("Step 3 : Select a Program and go to Program Settings"); 
			
		
		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);

		  	agencyhome.ClickOnNewInstructorLink();
			Thread.sleep(5000);

		
		System.out.println("Step 4 : Navigation to Create Users Page");
		Reporter.log("Step 4 : Navigation to Create Users Page"); 
			
		
			/*agency.ClickOnCreateUserButton();
			Thread.sleep(5000);*/

		
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

			SeleniumFunc.ClickOnElement("id", "access_type_ke_admin");
			agency.SelectInstrRole("Instructor");
			String username= "emuser" + JH.GenerateRandomNumber();
			String password ="clarion@123";
			
			agency.CreateNewUser( username, password, password,  "automation" , "user","Male" , "Admin Only", 
								"Northeast", "Adams", "Street 3", "city", "CO", "12345","rohit.ware@clariontechnologies.co.in");
			
			//agency.ClickToInstrNo();
			//agency.ClickToInstrNo_b();
			agency.ClickOnSaveChangesButton();

			Thread.sleep(5000);
		
/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
		System.out.println("Step 6 : Logging out of application and login with new user");
		Reporter.log("Step 6 : Logging out of application and login with new user");
		
	
			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			login.EnterUsername(username);
			login.EnterPassword(password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 7 : Verifying whether user is logged successfully or not");
		Reporter.log("Step 7 : Verifying whether user is logged successfully or not"); 
		

			String ActualText=SeleniumFunc.GetElementText("css", ".span5.login-info p").trim();
			String ExpectedText = "Logged in as";
		
			if(ActualText.contains(ExpectedText))
			{
			
				System.out.println("Success !! User logged in successfully");
				Reporter.log("Success !! User logged in successfully"); 
			
			}
			else
			{
			
				System.out.println("Failure !! User is not logged in successfully");
				Reporter.log("Failure !! User is not logged in successfully"); 
				
				AssertFailedCount++;
		
			}
			
		System.out.println("Step 8 : Verifying Alert message");
		Reporter.log("Step 8 : Verifying whether user is logged successfully or not"); 
			
		
			ActualText =SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2").trim();
			ExpectedText = "Verify Your Email Address";
						
			
			if(ActualText.contains(ExpectedText))
			{
	
				System.out.println("Success !! Correct Page header is displayed");
				Reporter.log("Success !! Correct Page header is displayed"); 
				
			}
			else
			{
				
				System.out.println("Failure !! Wrong Page header is displayed");
				Reporter.log("Failure !! Wrong Page header is displayed"); 
					
				AssertFailedCount++;
			
			}
							
			
			//Verify Content
			ActualText =SeleniumFunc.GetElementText("css", ".span5:nth-of-type(1)").trim();
			ExpectedText = "A valid email address is required. Please update your email address using the provided form."+"\n"+
					"After providing your email address, you will be sent a verification email with further instructions."+"\n"+
					"You will not be able to log in to Event Manager until your email address has been verified.";

			if(ActualText.contains(ExpectedText))
			{
	
				System.out.println("Success !! Correct Content is displayed");
				Reporter.log("Success !! Correct Content is displayed"); 
				
			}
			else
			{
				
				System.out.println("Failure !! Wrong Content is displayed");
				Reporter.log("Failure !! Wrong Content is displayed"); 
					
				AssertFailedCount++;
			
			}
			
/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/	 /*
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
	* 	Verify PIN is required for instructor to enter result under event
	*/ 
    @Test
	private void VerifyPINRequiredForInstructor() throws Exception
	{
		System.out.println("====" + "\n" +
						"Test 3 : Verify PIN is required for instructor to enter result under event"  + "\n" +
			 			"====");
		Reporter.log("====" + "\n" +
			 			  "Test 3 : Verify PIN is required for instructor to enter result under event"  + "\n" +
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
			instructorcreateevent.Location.sendKeys("Demo Location For Colorado");
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
						
					
			////instructorschedule.FirstEventName.click();
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

			login.ClickOnLogoutButton();
			Thread.sleep(5000);

					
		System.out.println("Step 7 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 7 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
						
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			/*		
			//Enter Instructor Information
			SeleniumFunc.ClickOnElement("css", ".span7 p a");
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();	
			Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);*/
			
			SeleniumFunc.ToGoToUrl(URL);
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");

			instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);


			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 500);");

			studresult.ClickOnEditInstrInfoButton();
			studresult.EnterPrepHours("10");
			studresult.EnterClassHrs("10");
			studresult.EnterFieldHrs("10");
			studresult.EnterTravelHrs("10");
			studresult.ClickOnSaveButton();
					
/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			
			//Verify Instructor information is completed
			actualtext = SeleniumFunc.GetElementText("css", ".alert-flash.alert.alert-danger");
			expectedtext = "Your valid digital signature is required before submitting these results.";

			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! User need to enter PIN to Enter a Result");
				Reporter.log("Success !! User need to enter PIN to Enter a Result");
			
			}
			else
			{
			
				System.out.println("Failure !! System is not asking to enter PIN" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! System is not asking to enter PIN" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
				AssertFailedCount++;
			
			}
						
					
					
			try {
				//Going to Event Roster page deleting  event so new event can be added with same test data
				studresult.ClickOnCancelLink();		
				Thread.sleep(5000);

				instructoreventroster.ReturnToRosterFromResult.click();
				Thread.sleep(5000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
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
    
    

	/* Test 4: 
	 * Verify Daily Instructor Reporting Functionality
	 */ 
	@Test
	private void VerifyDailyInstructorReporting() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify Daily Instructor Reporting Functionality"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify Daily Instructor Reporting Functionality"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		ProgramSettingsPage programsettings = new ProgramSettingsPage(driver);
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
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			//Navigate to 'My Event Schedule' page and delete all Events
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//instructorhome.GoToNextMonth();
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
			instructorcreateevent.Location.sendKeys("Demo Location For Colorado");
			Thread.sleep(5000);
			instructorcreateevent.Location_FirstChoice.click();
			Thread.sleep(2000);
			
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
			
			instructorcreateevent.AddEventDate.click();
			
			instructorcreateevent.GoToNextMonth();
			instructorcreateevent.SelectDate("5");
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
					
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
			
			//instructorcreateevent.SelectDate("6");
			
			Thread.sleep(2000);
			
			((JavascriptExecutor)driver).executeScript("scroll(1027,987)");	
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


		System.out.println("Step 6 : Select agency from agency listing");
		Reporter.log("Step 6 : Select agency from agency listing"); 
				
						
			SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
			Thread.sleep(5000);

		
		System.out.println("Step 7 : Select a program and go to Program Settings");
		Reporter.log("Step 7 : Select a program and go to Program Settings"); 
			
		
		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);

		  	agencyhome.ClickOnProgramSettingsLink();
			Thread.sleep(5000);

		  	
		System.out.println("Step 8 : Verify Title for Program Settings");
		Reporter.log("Step 8 : Verify Title for Program Settings");
				
			String Actual = programsettings.Title.getText().trim();
			String Expected = "Program Settings";
					
			if(Actual.equals(Expected))
			{
			
				System.out.println("Success !! Title is correct for Program Settings.");
				Reporter.log("Success !! Title is correct for Program Settings.");
			
			}
			else
			{
			
				System.out.println("Failure !! Title is NOT correct for Program Settings." + "\n" + "Expected : "+ "\n" + Expected + "\n" + 
										 "Actual: " + "\n" +  Actual);
				Reporter.log("Failure !! Title is NOT correct for Program Settings." + "\n" + "Expected : "  
						+ "\n" + Expected + "\n" + 
						 "Actual: " + "\n" +  Actual);
						
				AssertFailedCount++;
		
			}		  		
				
			
					
		System.out.println("Step 9 : Set Federal Reporting to No and Verify Success Message for save changes");
		Reporter.log("Step 9 : Set Federal Reporting to No and Verify Success Message for save changes");
						
						
			programsettings.SelectInstructorDailyReportingOption("Off");
		  	programsettings.ClickOnSaveChanges();
			Thread.sleep(5000);

			Actual = programsettings.Success.getText().trim();
			Expected = "Program settings saved.";
				
			if(Actual.equals(Expected))
			{
			
				System.out.println("Success !! Program Settings Saved.");
				Reporter.log("Success !! Program Settings Saved.");
			
			}
			else
			{
			
				System.out.println("Failure !! Program Settings is NOT Saved." + "\n" + "Expected : "+ "\n" + Expected + "\n" + 
										 "Actual: " + "\n" +  Actual);
				Reporter.log("Failure !! Program Settings is NOT Saved." + "\n" + "Expected : "  
						+ "\n" + Expected + "\n" + 
						 "Actual: " + "\n" +  Actual);
					
				AssertFailedCount++;
			
			}		  	
		
				
		System.out.println("Step 10 : Verify Instructor Daily Reporting Set To Off");
		Reporter.log("Step 10 : Verify Instructor Daily Reporting Set To Off");
							
			Expected = "Off";
			actualtext = SeleniumFunc.GetSelectedValueFromDropdownList("css", "#instructor_daily_reporting");		

			if(actualtext.equals(Expected))
			{
			
				System.out.println("Success !! Instructor Daily Reporting Settings saved and Set To Off.");
				Reporter.log("Success !! Instructor Daily Reporting Settings saved and Set To Off.");
			
			}
			else
			{
			
				System.out.println("Failure !! Instructor Daily Reporting Settings NOT saved." + "\n" + "Expected : "  
									+ "\n" + Expected );
				Reporter.log("Failure !! Instructor Daily Reporting Setings NOT saved." + "\n" + "Expected : "  
							+ "\n" + Expected );
					
				AssertFailedCount++;
			
			}		  	
				
		  	
		System.out.println("Step 11 : Search and Activate event");
		Reporter.log("Step 11 : Search and Activate event"); 			
		  	
		
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

			
		System.out.println("Step 12 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 12 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
							

			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			/*			
			//Enter Instructor Information
			SeleniumFunc.ClickOnElement("css", ".span7 p a");
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();	
			Thread.sleep(5000);

			instructorhome.GoToNextMonth();
			Thread.sleep(5000);

			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);*/
			SeleniumFunc.ToGoToUrl(URL);
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");

			instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);

			studresult.ClickOnEditInstrInfoButton();
			Thread.sleep(5000);

			int PreHrs = SeleniumFunc.GetElementCount("css", "input[id$='prep_hrs']");
			int ClassHrs = SeleniumFunc.GetElementCount("css", "input[id$='class_hrs']");
			
			if(PreHrs==1 && ClassHrs==1)
			{
			
				System.out.println("Success !! Instructor Daily Reporting Functionality is working properly.");
				Reporter.log("Success !! Instructor Daily Reporting Functionality is working properly");
			
			}
			else
			{
			
				System.out.println("Failure !! Instructor Daily Reporting Functionality is NOT working properly");
				Reporter.log("Failure !! Instructor Daily Reporting Functionality is NOT working properly");
				
				AssertFailedCount++;
				
			
			}
		
			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			
		System.out.println("Step 13 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 13 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
						
					
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
	
			
		System.out.println("Step 14 : Go to agency and set Instructor daily reporting to ON");
		Reporter.log("Step 14 : Go to agency and set Instructor daily reporting to ON"); 
				
			SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
			Thread.sleep(5000);

		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);

		  	agencyhome.ClickOnProgramSettingsLink();
			Thread.sleep(5000);

			programsettings.SelectInstructorDailyReportingOption("On");
		  	programsettings.ClickOnSaveChanges();
			Thread.sleep(5000);

				
		System.out.println("Step 15 : Verify Instructor Daily Reporting Set To On");
		Reporter.log("Step 15 : Verify Instructor Daily Reporting Set To On");
								
			Expected = "On";
				
			actualtext = SeleniumFunc.GetSelectedValueFromDropdownList("css", "#instructor_daily_reporting");		

			if(actualtext.equals(Expected))
			{
			
				System.out.println("Success !! Instructor Daily Reporting Settings saved and Set To On.");
				Reporter.log("Success !! Instructor Daily Reporting Settings saved and Set To On.");
			
			}
			else
			{
			
				System.out.println("Failure !! Instructor Daily Reporting Settings NOT saved." + "\n" + "Expected : "  
									+ "\n" + Expected );
				Reporter.log("Failure !! Instructor Daily Reporting Setings NOT saved." + "\n" + "Expected : "  
							+ "\n" + Expected );
						
				AssertFailedCount++;
			
			}		  				
			
			login.ClickOnLogoutButton();				
			Thread.sleep(5000);

		System.out.println("Step 16 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 16 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
									

			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			/*					
			//Enter Instructor Information
			SeleniumFunc.ClickOnElement("css", ".span7 p a");
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();
			Thread.sleep(5000);

			instructorhome.GoToNextMonth();
			Thread.sleep(5000);

			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);*/
			
			SeleniumFunc.ToGoToUrl(URL);
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");

			instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);

			studresult.ClickOnEditInstrInfoButton();
			Thread.sleep(5000);
			
			

			PreHrs = SeleniumFunc.GetElementCount("css", "input[id$='prep_hrs']");
			ClassHrs = SeleniumFunc.GetElementCount("css", "input[id$='class_hrs']");
			
			if(PreHrs==2 && ClassHrs==2)
			{
			
				System.out.println("Success !! Instructor Daily Reporting Functionality is working properly.");
				Reporter.log("Success !! Instructor Daily Reporting Functionality is working properly");
			
			}
			else
			{
			
				System.out.println("Failure !! Instructor Daily Reporting Functionality is NOT working properly");
				Reporter.log("Failure !! Instructor Daily Reporting Functionality is NOT working properly");
						
				AssertFailedCount++;
						
			
			} 
				
/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			try {
				//Going to Event Roster page deleting  event so new event can be added with same test data
				studresult.ClickOnCancelLink();		
				Thread.sleep(5000);

				instructoreventroster.ReturnToRosterFromResult.click();
				Thread.sleep(5000);

				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
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
	
	
	/* Test 5: 
	 * Verify admin can reject submitted event 
	*/ 		
	@Test
	private void VerifyAdminRejectEvent() throws Exception
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
				((JavascriptExecutor)driver).executeScript("scroll(1027,987)");				
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

				/*		
				//Add Student for event
				instructorhome.VisitHomePage.click();
				Thread.sleep(5000);

				instructorhome.Action_MyEventSchedule.click();	
				Thread.sleep(5000);

				instructorschedule.FirstEventName.click();	
				Thread.sleep(5000);*/
				SeleniumFunc.ToGoToUrl(URL);
				SeleniumFunc.ClickOnElement("linkText", "Event Roster");
				SeleniumFunc.ClickOnElement("linkText", "Add Student");
				//instructoreventroster.SelectAction("Add Student");
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
				
/*				if(SeleniumFunc.IsElementPresent(errorpage.Error))
				{
					System.out.println("Failure !! Error on page");
					AssertFailedCount++;
				}
*/				
				
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
						
/*				if(SeleniumFunc.IsElementPresent(errorpage.Error))
				{
					System.out.println("Failure !! Error on page");
					AssertFailedCount++;
				}
*/				
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

/*				if(SeleniumFunc.IsElementPresent(errorpage.Error))
				{
					System.out.println("Failure !! Error on page");
					AssertFailedCount++;
				}
*/				
				// Set past date for event to Review and Submit the Results
				
				driver.get("https://my-webtest1.register-ed.com/eventregistration/roster/"+EventID);
				Thread.sleep(2000);
				
				SeleniumFunc.ClickOnElement("linkText", "Edit This Event");
				Thread.sleep(2000);
				WebElement ele = driver.findElement(By.cssSelector(".event-date-remove"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
				SeleniumFunc.ClickOnElement("css", ".event-date-remove");
				Thread.sleep(2000);
				instructorcreateevent.SelectStartDate("1", "00", "AM");
				instructorcreateevent.SelectEndDate("2", "00", "AM");
				
				instructorcreateevent.AddEventDate.click();
				Thread.sleep(2000);
				WebElement ele1 = driver.findElement(By.xpath("//span[contains(.,'Save Changes')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele1);
				SeleniumFunc.ClickOnElement("xpath", "//span[contains(.,'Save Changes')]");
				Thread.sleep(2000);
				
				driver.get("https://my-webtest1.register-ed.com/eventregistration/roster/"+EventID);
				Thread.sleep(2000);
				
				instructoreventroster.SelectAction("Enter Results");
				Thread.sleep(5000);
				
				
				//Review and Submit the Results
				jse = (JavascriptExecutor)driver;
				jse.executeScript("scroll(0, 300);");
				
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
						
		//		studresult.SelectAdmin("Automation Testing");
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

/*				if(SeleniumFunc.IsElementPresent(errorpage.Error))
				{
					System.out.println("Failure !! Error on page");
					AssertFailedCount++;
				}
*/				
			
				/*try {
					//Going to admin and deleting  event so new event can be added with same test data*/
					login.ClickOnLogoutButton();
					Thread.sleep(5000);

					login.EnterUsername(Constants.EM_Admin_Username);
					login.EnterPassword(Constants.EM_Admin_Password);
					login.ClickOnLogInButton();
					Thread.sleep(5000);

					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/admin/"+EventID);
					SeleniumFunc.ClickOnElement("css", ".btn.btn-primary.mtt");
					Thread.sleep(3000);
					WebElement ele2 = driver.findElement(By.cssSelector("#event_results > div > button.btn.btn-danger"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele2);
					SeleniumFunc.ClickOnElement("css", "#event_results > div > button.btn.btn-danger");
					SeleniumFunc.EnterValueInTextbox("name", ".//*[@id='reject_event']/div[2]/div/div/textarea","Ke-Testing");
					//SeleniumFunc.EnterValueInTextbox("name", "event_rejection_reason", "Ke-Testing");
					SeleniumFunc.ClickOnElement("css", "#reject_instructor_form > div > button.btn.btn-danger");
					expectedtext = "The event has been rejected.";
					actualtext =  SeleniumFunc.GetElementText("css", ".alert-block.alert.alert-success");
								
					if(actualtext.equals(expectedtext))
					{
					
						System.out.println("Success !! Event Result Rejected");
						Reporter.log("Success !! Event Result Rejected");
					
					}
					else
					{
					
						System.out.println("Failure !! Error while Rejecting Event Result" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! Error while Rejecting Event Result" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
									
						AssertFailedCount++;
					
					}		
					
					SeleniumFunc.ClickOnElement("linkText", "Cancel Event");
					
					Thread.sleep(5000);
					
					
					SeleniumFunc.ClickOnElement("linkText", "Unscheduled and Remove Event");

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


	/* Test 6: 
	 * 	Verify user is able to upload file (when PIN is disabled)
	 */ 
   
	@Test
	private void UploadFilePINDisable() throws Exception
	{
		System.out.println("====" + "\n" +
						"Test 6 : Verify user is able to upload file (when PIN is disabled)"  + "\n" +
			 			"====");
		Reporter.log("====" + "\n" +
			 			  "Test 6 : Verify user is able to upload file (when PIN is disabled)"  + "\n" +
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
			instructorcreateevent.Location.sendKeys("Demo Location For Colorado");
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
			System.out.println("Url : "+URL);

						
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

					
		System.out.println("Step 6 : Search and Activate event");
		Reporter.log("Step 6 : Search and Activate event"); 
					
			
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
			Thread.sleep(5000);

			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			/*		
			//Enter Instructor Information
			SeleniumFunc.ClickOnElement("css", ".span7 p a");
			Thread.sleep(5000);

			instructorhome.Action_MyEventSchedule.click();	
			Thread.sleep(5000);

			//instructorhome.GoToNextMonth();
			instructorschedule.FirstEventName.click();
			Thread.sleep(5000);*/
			
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(5000);
			SeleniumFunc.ClickOnElement("linkText", "View Results");
			SeleniumFunc.ClickOnElement("linkText", "Go to Roster");
			SeleniumFunc.ClickOnElement("linkText", "Enter Results");
			//instructoreventroster.SelectAction("Enter Results");
			Thread.sleep(5000);

			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 300);");
			
			studresult.ClickOnEditInstrInfoButton();
			Thread.sleep(5000);

			studresult.EnterPrepHours("10");
			studresult.EnterClassHrs("10");
			studresult.EnterFieldHrs("10");
			studresult.EnterTravelHrs("10");
			
			studresult.ClickOnDisablePINCheckBox();					
			Thread.sleep(5000);
			
			
			/*
			File file = new File("src/test/resources/SampleFileUpload.txt");
			studresult.SendPath(file.getAbsolutePath());*/		
			

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			//Verify Instructor information is completed
			SeleniumFunc.AcceptAlertAndGetText();
			SeleniumFunc.ClickOnElement("css", ".file-upload>ol>li>ol>li>input");
			StringSelection sel = new StringSelection("SampleFileUpload.txt");
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel,null);
			
			Robot robot = new Robot();
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			studresult.ClickOnSaveButton();
			Thread.sleep(5000);
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
			
			studresult.ClickOnEditInstrInfoButton();
			Thread.sleep(5000);

			//Verify File is uploaded
			actualtext = SeleniumFunc.GetElementText("css", "#instructor div:nth-of-type(3).well");
			expectedtext = "Instructor Results: SampleFileUpload.txt";

			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! File is uploaded successfully. i.e. "+expectedtext);
				Reporter.log("Success !! File is uploaded successfully. i.e. "+expectedtext);
			}
			else
			{
				System.out.println("Failure !! File is uploaded properly. " + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! File is uploaded properly. " + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									"Actual: " + "\n" +  actualtext);
						
				AssertFailedCount++;
			}
			
			
			
			studresult.ClickOnSaveButton();
			Thread.sleep(5000);


			//Verify File present under Upload OR Delete files
			instructoreventroster.SelectAction("Upload or Delete Files");
			Thread.sleep(5000);

			if(SeleniumFunc.IsElementPresent("linkText", "SampleFileUpload.txt"))
			{
				System.out.println("Success !! Uploaded file present under Upload OR Delete files");
				Reporter.log("Success !! Uploaded file present under Upload OR Delete files");
			}
			else
			{
				System.out.println("Failure !! Uploaded file is NOT present under Upload OR Delete files");
				Reporter.log("Failure !! Uploaded file is NOT present under Upload OR Delete files");
						
				AssertFailedCount++;
			}
			
/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			try {
				//Going to Event Roster page deleting  event so new event can be added with same test data
				SeleniumFunc.ClickOnElement("linkText", "Back");
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

}
