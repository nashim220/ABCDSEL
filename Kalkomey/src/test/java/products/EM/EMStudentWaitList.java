package products.EM;

 
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
import pageFactory.EM.EMAddStudentPage;
import pageFactory.EM.ErrorPage;
import pageFactory.EM.InstructorCreateEventPage;
import pageFactory.EM.InstructorEventRosterPage;
import pageFactory.EM.InstructorHomePage;
import pageFactory.EM.InstructorLocationPage;
import pageFactory.EM.InstructorSchedulePage;
import pageFactory.EM.LoginPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class EMStudentWaitList 
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
	 * Verify that Max no. of Wait List Requests For An Event can be set
	*/ 
	@Test
	private void VerifyWaitListCapacity() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify that Max no. of Wait List Requests For An Event can be set"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify that Max no. of Wait List Requests For An Event can be set"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome = new AgencyHomePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);

		System.out.println("Step 2 : Logging in application as Super Admin: " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 2 : Logging in application as Super Admin: " + Constants.ApplicationURL_EM +"/login/login"); 
				
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(5000);
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 3 : Verify page header");
		Reporter.log("Step 3 : Verify page header"); 
		
				
			String Expected = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
		
			if(Expected.equals("Kalkomey Administration"))
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

		System.out.println("Step 4 : Select agency from agency listing");
		Reporter.log("Step 4 : Select agency from agency listing"); 
			
					
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

	
		System.out.println("Step 5 : Verify agency name");
		Reporter.log("Step 5 : Verify agency name");
		
			String ActualName = SeleniumFunc.GetElementText("css", ".span9>h3>strong");
			
			if(ActualName.equals("Demo Agency"))
			{
				
				System.out.println("Success !! Control is redirected to correct agency page");
				Reporter.log("Success !! Control is redirected to correct agency page");
				
			}
			else
			{
				
				System.out.println("Failure !! Control is not redirected to correct agency page : "+ Expected);
				Reporter.log("Failure !! Control is redirected to correct agency page :"+ Expected);
				
				AssertFailedCount++;
				
			}
					
			agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);

		System.out.println("Step 5 : Go to Event Settings and set Max Wait List");
		Reporter.log("Step 5 : Go to Event Settings and set Max Wait List");
				
			agencyhome.ClickOnEventSettingsLink();
			Thread.sleep(5000);

			agencyhome.EnterMaxWaitList("100");
			agencyhome.SaveEventSettingChanges();
			Thread.sleep(5000);

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			
		System.out.println("Step 5 : Go to Create Event and check for wait list value");
		Reporter.log("Step 5 : Go to Create Event and check for wait list value");	
		
		
			agencyhome.ClickOnCreateEventLink();
			Thread.sleep(5000);

			instructorcreateevent.SelectProgram2();
			Thread.sleep(2000);
		
			instructorcreateevent.Location.clear();
			instructorcreateevent.Location.sendKeys("Demo Agency Location");
			Thread.sleep(5000);
			instructorcreateevent.Location_FirstChoice.click();
			Thread.sleep(2000);
		
		//Get Event Capacity and Wait List Capacity values
			String EventCapacity = instructorcreateevent.EventCapacity.getAttribute("value");
			String WaitListCapacity = instructorcreateevent.WaitListCapacity.getAttribute("value");
			if(WaitListCapacity.equals(EventCapacity))	
			{
					
				System.out.println("Success !! Wait List capacity is set correctly");
				Reporter.log("Success !! Wait List capacity is set correctly");
					
			}
			else
			{
					
				System.out.println("Failure !! Wait List capacity is set correctly. Expected Wait List Capacity : "+ EventCapacity 
									+ "Actual Wait List Capacity : " + WaitListCapacity );
				Reporter.log("Failure !! Wait List capacity is set correctly. Expected Wait List Capacity : "+ EventCapacity 
									+ "Actual Wait List Capacity : " + WaitListCapacity );
					
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
	* Verify user can enter the result and update student details
	*/ 		
   @Test
   private void VerifyWaitListStatus() throws Exception
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
		

		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
				
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(5000);

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
			
			/*instructorcreateevent.GoToNextMonth();*/
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
					
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
			
			instructorcreateevent.EnterEventCapacity("2");
			instructorcreateevent.EnterWaitListCapacity("1");
					
			instructorcreateevent.CreateEvent.click();
			//driver.getCurrentUrl();
			Thread.sleep(5000);

					
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

			String EventID = instructoreventroster.EventId.getText().trim();
			String URL = driver.getCurrentUrl();
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
			SeleniumFunc.ClickOnElement("id", "search-button");
			//instructorhome.Click_Search();
			//searchevent.ClickOnSearchButton();
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

				
	
			instructorhome.VisitHomePage.click();
			Thread.sleep(5000);

			
			
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");

			
			Thread.sleep(2000);
			
			SeleniumFunc.ClickOnElement("linkText", "Add Student");
			Thread.sleep(2000);
			String username="emstudent"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
			addstud.SelectEyeColor();
			addstud.SelectHairColor();
			addstud.EnterSSNNO("125896336");
			addstud.ClickOnRegisterButton();
			Thread.sleep(34000);

					
			SeleniumFunc.ClickOnElement("linkText", "Add Student");
			Thread.sleep(2000);

			username="emstudent"+ JH.GenerateRandomNumber();
			emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("User", "Clarion", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "9898989898", "12345");
			addstud.SelectEyeColor();
			addstud.SelectHairColor();
			addstud.EnterSSNNO("125896336");
			addstud.ClickOnRegisterButton();
			Thread.sleep(34000);

			
		
		System.out.println("Step 8 : Go to register-ed and Verify status of Event");
		Reporter.log("Step 8 : Go to register-ed and Verify status of Event"); 		

			
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
			expectedtext = "Full Event with Wait List";
			actualtext = instructoreventroster.EventStatus.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Event status is correct for wait list. i.e. " +expectedtext);
				Reporter.log("Success !! Event status is correct for wait list . i.e. " +expectedtext);
			
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
			
			
				SeleniumFunc.ToGoToUrl(URL);
				Thread.sleep(5000);
				
				SeleniumFunc.ClickOnElement("linkText", "Cancel Event");
				
				Thread.sleep(2000);
				
				
				SeleniumFunc.ClickOnElement("linkText", "Unschedule and Remove Event");

				Thread.sleep(2000);

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
	
}
