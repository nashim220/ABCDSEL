package products.EM;

 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
import pageFactory.EM.InstructorProfilePage;
import pageFactory.EM.InstructorSchedulePage;
import pageFactory.EM.LoginPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class MainPagesTest 
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
	 * Instructor > Home : Verify elements on welcome instructor page
	*/ 
	@Test
	private void Instructor_Home_VerifyElements() throws Exception
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
		InstructorHomePage instructorhome = new InstructorHomePage(driver);
	
		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(4000);
			
		System.out.println("Step 2 : Verifying UI elements on home page");
		Reporter.log("Step 2 : Verifying UI elements on home page"); 
			
				instructorhome.VisitHomePage.click();
				Thread.sleep(4000);

				//Verifying Welcome text
				String expectedtext = "Welcome to your instructor control panel. Here you can set up your events, select event locations, view and print event rosters, input event results, and modify your profile." + 
									"Choose one of your upcoming events or choose an item from the menu below to get started.";
				
				String actualtext = instructorhome.WelcomeTextP1.getText().trim() +
									instructorhome.WelcomeTextP2.getText().trim() ; 
			
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! Welcome text is correct");
					Reporter.log("Success !! Welcome text is correct"); 
				}
				else
				{
					System.out.println("Failure !! Welcome text is incorrect" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! Welcome text is incorrect" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying presence of Menu links
				
					//Home
					expectedtext = Constants.ApplicationURL_EM.substring(5) + "/panel/proctor"; 
					actualtext = instructorhome.Menu_Home.getAttribute("href");
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! 'Menu > Home' link is present");
						Reporter.log("Success !! 'Menu > Home' link is present");
					}
					else
					{
						System.out.println("Failure !! 'Menu > Home' link is NOT present" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! 'Menu > Home' link is NOT present" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
					
					//Profile
					expectedtext = Constants.ApplicationURL_EM.substring(5) + "/proctor/editprofile"; 
					actualtext = instructorhome.Menu_Profile.getAttribute("href");
				
					if(actualtext.contains(expectedtext.substring(5, expectedtext.length())))
					{
						System.out.println("Success !! 'Menu > Profile' link is present");
						Reporter.log("Success !! 'Menu > Profile' link is present");
					}
					else
					{
						System.out.println("Failure !! 'Menu > Profile' link is NOT present" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! 'Menu > Profile' link is NOT present" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
	
/*					if(SeleniumFunc.IsElementPresent(errorpage.Error))
					{
						System.out.println("Failure !! Error on page");
						AssertFailedCount++;
					}
*/					
					//Events
					expectedtext = Constants.ApplicationURL_EM.substring(5) + "/event/calendar"; 
					actualtext = instructorhome.Menu_Events.getAttribute("href");
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! 'Menu > Events' link is present");
						Reporter.log("Success !! 'Menu > Events' link is present");
					}
					else
					{
						System.out.println("Failure !! 'Menu > Events' link is NOT present" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! 'Menu > Events' link is NOT present" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}

					
					//Locations
					expectedtext = Constants.ApplicationURL_EM.substring(5) + "/location"; 
					actualtext = instructorhome.Menu_Locations.getAttribute("href");
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! 'Menu > Locations' link is present");
						Reporter.log("Success !! 'Menu > Locations' link is present");
					}
					else
					{
						System.out.println("Failure !! 'Menu > Locations' link is NOT present" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! 'Menu > Locations' link is NOT present" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
/*					if(SeleniumFunc.IsElementPresent(errorpage.Error))
					{
						System.out.println("Failure !! Error on page");
						AssertFailedCount++;
					}
*/					
					//Enrollments
					expectedtext = Constants.ApplicationURL_EM.substring(5) + "/eventregistration/chooseevent"; 
					actualtext = instructorhome.Menu_Enrollments.getAttribute("href");
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! 'Menu > Enrollments' link is present");
						Reporter.log("Success !! 'Menu > Enrollments' link is present");
					}
					else
					{
						System.out.println("Failure !! 'Menu > Enrollments' link is NOT present" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! 'Menu > Enrollments' link is NOT present" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
				
/*					if(SeleniumFunc.IsElementPresent(errorpage.Error))
					{
						System.out.println("Failure !! Error on page");
						AssertFailedCount++;
					}
*/					
					//Results
					expectedtext = Constants.ApplicationURL_EM.substring(5) + "/result"; 
					actualtext = instructorhome.Menu_Results.getAttribute("href");
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! 'Menu > Results' link is present");
						Reporter.log("Success !! 'Menu > Results' link is present");
					}
					else
					{
						System.out.println("Failure !! 'Menu > Results' link is NOT present" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! 'Menu > Results' link is NOT present" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
				
/*					if(SeleniumFunc.IsElementPresent(errorpage.Error))
					{
						System.out.println("Failure !! Error on page");
						AssertFailedCount++;
					}
*/				
				
				//Verifying presence of Action links
					
					//Manage My Profile
					expectedtext = Constants.ApplicationURL_EM.substring(5) + "/proctor/editprofile"; 
					actualtext = instructorhome.Action_ManageMyProfile.getAttribute("href");
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! 'Manage My Profile' link is present");
						Reporter.log("Success !! 'Manage My Profile' link is present");
					}
					else
					{
						System.out.println("Failure !! 'Manage My Profile' link is NOT present" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! 'Manage My Profile' link is NOT present" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
					
					//My Event Schedule
					expectedtext = Constants.ApplicationURL_EM.substring(5) + "/event/calendar"; 
					actualtext = instructorhome.Action_MyEventSchedule.getAttribute("href");
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! 'My Event Schedule' link is present");
						Reporter.log("Success !! 'MMy Event Schedule' link is present");
					}
					else
					{
						System.out.println("Failure !! 'My Event Schedule' link is NOT present" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! 'My Event Schedule' link is NOT present" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
					
					//Program Locations
					expectedtext = Constants.ApplicationURL_EM.substring(5) + "/location"; 
					actualtext = instructorhome.Action_ProgramLocations.getAttribute("href");
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! 'Program Locations' link is present");
						Reporter.log("Success !! 'Program Locations' link is present");
					}
					else
					{
						System.out.println("Failure !! 'Program Locations' link is NOT present" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! 'Program Locations' link is NOT present" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
				
				
					//My Event Enrollments
					expectedtext = Constants.ApplicationURL_EM.substring(5) + "/eventregistration/chooseevent"; 
					actualtext = instructorhome.Action_MyEventEnrollments.getAttribute("href");
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! 'My Event Enrollments' link is present");
						Reporter.log("Success !! 'My Event Enrollments' link is present");
					}
					else
					{
						System.out.println("Failure !! 'My Event Enrollments' link is NOT present" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! 'My Event Enrollments' link is NOT present" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
					
					//My Event Results
					expectedtext = Constants.ApplicationURL_EM.substring(5) + "/result"; 
					actualtext = instructorhome.Action_MyEventResults.getAttribute("href");
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! 'My Event Results' link is present");
						Reporter.log("Success !! 'My Event Results' link is present");
					}
					else
					{
						System.out.println("Failure !! 'My Event Results' link is NOT present" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! 'My Event Results' link is NOT present" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
				
				
				//Verifying 'logged in as' text
				
					expectedtext = "Logged in as"; 
					actualtext = instructorhome.LoggedInAsText.getText().trim();
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! 'Logged in as' text is present");
						Reporter.log("Success !! 'Logged in as' text is present");
					}
					else
					{
						System.out.println("Failure !! 'Logged in as' text is NOT present" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! 'Logged in as' text is NOT present" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
				
				//Verifying presence of 'Log Out' link
					expectedtext = Constants.ApplicationURL_EM.substring(5) + "/login/logout"; 
					actualtext = instructorhome.LogOut.getAttribute("href");
				
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success !! 'Log Out' link is present");
						Reporter.log("Success !! 'Log Out' link is present");
					}
					else
					{
						System.out.println("Failure !! 'Log Out' link is NOT present" + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! 'Log Out' link is NOT present" + "\n" + "Expected : "  
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
	
	
	/* Test 2: 
	 * Instructor > Profile : Verify elements on Profile page
	*/ 
	@Test
	private void Instructor_Profile_VerifyElements() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Instructor > Profile : Verify elements on Profile page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Instructor > Profile : Verify elements on Profile page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorProfilePage instructorprofile = new InstructorProfilePage(driver);
	
		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(4000);

			
		System.out.println("Step 2 : Navigate to profile page and verifying UI elements");
		Reporter.log("Step 2 : Navigate to profile page and verifying UI elements"); 
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/proctor/editprofile");
				Thread.sleep(4000);

				//Verifying page title
				String expectedtext = "Manage Profile";
				
				String actualtext = instructorprofile.PageTitle.getText().trim(); 
			
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! page title is correct");
					Reporter.log("Success !! page title is correct"); 
				}
				else
				{
					System.out.println("Failure !! page title is incorrect" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! page title is incorrect" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying Information text
				expectedtext = "Here you can manage and maintain your profile";
				actualtext = instructorprofile.InfoText.getText().trim(); 
			
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success !! Information text is correct");
					Reporter.log("Success !! Information text is correct"); 
				}
				else
				{
					System.out.println("Failure !! Information text is incorrect" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! Information text is incorrect" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying presence of 'Account Information'
				expectedtext = "Account Information";
				actualtext = instructorprofile.AccountInformationText.getText().trim(); 
			
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success !! 'Account Information' section is present");
					Reporter.log("Success !! 'Account Information' section is present");
				}
				else
				{
					System.out.println("Failure !! 'Account Information' section is NOT present" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! 'Account Information' section is NOT present" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying presence of 'Login Information'
				expectedtext = "Login Information";
				actualtext = instructorprofile.LoginInformationText.getText().trim(); 
			
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success !! 'Login Information' section is present");
					Reporter.log("Success !! 'Login Information' section is present");
				}
				else
				{
					System.out.println("Failure !! 'Login Information' section is NOT present" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! 'Login Information' section is NOT present" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying presence of 'Additional Information'
				expectedtext = "Additional Information";
				actualtext = instructorprofile.AdditionalInformationText.getText().trim(); 
			
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success !! 'Additional Information' section is present");
					Reporter.log("Success !! 'Additional Information' section is present");
				}
				else
				{
					System.out.println("Failure !! 'Additional Information' section is NOT present" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! 'Additional Information' section is NOT present" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying presence of 'How Do You Want Students To Contact You?'
				expectedtext = "How Do You Want Students To Contact You?";
				actualtext = instructorprofile.ContactYouText.getText().trim(); 
			
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success !! 'How Do You Want Students To Contact You?' section is present");
					Reporter.log("Success !! 'How Do You Want Students To Contact You?' section is present");
				}
				else
				{
					System.out.println("Failure !! 'How Do You Want Students To Contact You?' section is NOT present" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! 'How Do You Want Students To Contact You?' section is NOT present" + "\n" + "Expected : "  
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
	 * Instructor > Profile : Verify mandatory field validations on Profile page
	*/ 
	@Test
	private void Instructor_Profile_VerifyMessageForMandatoryFields() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Instructor > Profile : Verify mandatory field validations on Profile page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Instructor > Profile : Verify mandatory field validations on Profile page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorProfilePage instructorprofile = new InstructorProfilePage(driver);
	
		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(4000);

			
		System.out.println("Step 2 : Navigate to profile page and verifying validation message for mandatory fields");
		Reporter.log("Step 2 : Navigate to profile page and verifying validation message for mandatory fields"); 
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/proctor/editprofile");
				Thread.sleep(4000);

				//Clearing First Name, Last Name , Gender , Password Hint , Secret Question , Secret Answer 
				
				instructorprofile.FirstName.clear();
				instructorprofile.LastName.clear();
				instructorprofile.SelectGender("Select a gender...");
				//instructorprofile.PasswordHint.clear();
				//instructorprofile.SecretQuestion.clear();
				//instructorprofile.SecretAnswer.clear();
				
				JavascriptExecutor jse6 = (JavascriptExecutor) driver; 
				jse6.executeScript("window.scrollBy(0,1732)", "");
				

				
				// Clicking on 'Save Changes' button
				instructorprofile.SaveChangesButton.click();
				Thread.sleep(4000);

/*				if(SeleniumFunc.IsElementPresent(errorpage.Error))
				{
					System.out.println("Failure !! Error on page");
					AssertFailedCount++;
				}
*/				
				//Verifying validation message for First Name, Last Name , Gender , Password Hint , Secret Question , Secret Answer fields
			
				String expectedtext = "This field is required.";
				
				String[] value = {"first_name", "last_name", "gender"};
				
				for(int i=0; i < value.length ; i++)
				{
					String actualtext = instructorprofile.GetValidationMessage(value[i]); 
					
					if(actualtext.equals(expectedtext))
					{
						System.out.println("Success !! validation message is correct for - " + value[i] );
						Reporter.log("Success !! validation message is correct for - " + value[i] ); 
					}
					else
					{
						System.out.println("Failure !! validation message is incorrect for " + value[i] + "\n" + "Expected : "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual: " + "\n" +  actualtext);
						Reporter.log("Failure !! validation message is incorrect for " + value[i] + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual: " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
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
	 * Instructor > Profile : Verify that user can edit Profile details successfully i.e.
	 * First Name, Middle Initial , Last Name, Suffix , Gender, Password Hint, Secret Question,Secret Answer,Region, County, Address, City,State,Zipcode
	*/ 
	@Test
	private void Instructor_Profile_Edit() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Instructor > Profile : Verify that user can edit Profile details successfully i.e.First Name, Middle Initial , Last Name, Suffix , Gender, Password Hint, Secret Question,Secret Answer,Region, County, Address, City,State,Zipcode"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 4 : Instructor > Profile : Verify that user can edit Profile details successfully i.e.First Name, Middle Initial , Last Name, Suffix , Gender, Password Hint, Secret Question,Secret Answer,Region, County, Address, City,State,Zipcode"  + "\n" +
		 			"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorProfilePage instructorprofile = new InstructorProfilePage(driver);
	
		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(4000);

			
		System.out.println("Step 2 : Navigate to profile page and Edting profile details for First Name, Middle Initial , Last Name, Suffix , Gender, Password Hint, Secret Question,Secret Answer,Region, County, Address, City,State,Zipcode fields");
		Reporter.log("Step 2 : Navigate to profile page and Edting profile details for First Name, Middle Initial , Last Name, Suffix , Gender, Password Hint, Secret Question,Secret Answer,Region, County, Address, City,State,Zipcode fields");
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/proctor/editprofile");
				Thread.sleep(4000);

			
				//Clearing First Name,Middle Initial, Last Name , Suffix, Gender , Password Hint , Secret Question , Secret Answer , Address, city,Zip code
				
				instructorprofile.FirstName.clear();
				instructorprofile.MiddleName.clear();
				instructorprofile.LastName.clear();
				//instructorprofile.sel
				instructorprofile.SelectGender("Select a gender...");
				//instructorprofile.PasswordHint.clear();
				//instructorprofile.SecretQuestion.clear();
				//instructorprofile.SecretAnswer.clear();
				instructorprofile.Address.clear();
				instructorprofile.City.clear();
				instructorprofile.Zip.clear();
				instructorprofile.SSN.clear();
				
				//Editing values
				
				instructorprofile.FirstName.sendKeys("Testing");
				instructorprofile.MiddleName.sendKeys("B");
				instructorprofile.LastName.sendKeys("QA");
				//instructorprofile.Suffix.sendKeys("Sr.");
				instructorprofile.SelectGender("Female");
				//instructorprofile.PasswordHint.sendKeys("hintedited");
				//instructorprofile.SecretQuestion.sendKeys("secq");
				//instructorprofile.SecretAnswer.sendKeys("seca");
				instructorprofile.Address.sendKeys("D-120, Sthapatya");
				instructorprofile.City.sendKeys("BIRMINGHAM");
				instructorprofile.Zip.sendKeys("11111");
				instructorprofile.SSN.sendKeys("123123000");
				
				JavascriptExecutor jse6 = (JavascriptExecutor) driver; 
				jse6.executeScript("window.scrollBy(0,750)", "");
				
				// Clicking on 'Save Changes' button
				instructorprofile.SaveChangesButton.click();
				Thread.sleep(4000);

				
/*				if(SeleniumFunc.IsElementPresent(errorpage.Error))
				{
					System.out.println("Failure !! Error on page");
					AssertFailedCount++;
				}
*/				
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
		
			//Verifying First name
			actualtext = instructorprofile.FirstName.getAttribute("value"); 
			expectedtext = "Testing";
					
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! First name is edited successfully");
				Reporter.log("Success !! First name is edited successfully");
			}
			else
			{
				System.out.println("Failure !!  First name is NOT edited successfully" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !!  First name is NOT edited successfully" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			
			//Verifying Middle name
			actualtext = instructorprofile.MiddleName.getAttribute("value"); 
			expectedtext = "B";
					
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Middle name is edited successfully");
				Reporter.log("Success !! Middle name is edited successfully");
			}
			else
			{
				System.out.println("Failure !!  Middle name is NOT edited successfully" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !!  Middle name is NOT edited successfully" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			
			//Verifying Last name
			actualtext = instructorprofile.LastName.getAttribute("value"); 
			expectedtext = "QA";
					
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Last name is edited successfully");
				Reporter.log("Success !! Last name is edited successfully");
			}
			else
			{
				System.out.println("Failure !!  Last name is NOT edited successfully" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !!  Last name is NOT edited successfully" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			
	/*		//Verifying Suffix
			actualtext = instructorprofile.Suffix.getAttribute("value"); 
			expectedtext = "Sr.";
					
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Suffix is edited successfully");
				Reporter.log("Success !! Suffix is edited successfully");
			}
			else
			{
				System.out.println("Failure !!  Suffix is NOT edited successfully" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !!  Suffix is NOT edited successfully" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
	*/			
			
			//Verifying Gender
			actualtext = new Select(instructorprofile.Gender).getFirstSelectedOption().getText().trim();
			expectedtext = "Female";
					
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Gender is edited successfully");
				Reporter.log("Success !! Gender is edited successfully");
			}
			else
			{
				System.out.println("Failure !!  Gender is NOT edited successfully" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !!  Gender is NOT edited successfully" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
					
			
		
			/*//Verifying Password hint
			actualtext = instructorprofile.PasswordHint.getAttribute("value"); 
			expectedtext = "hintedited";
					
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Password hint is edited successfully");
				Reporter.log("Success !! Password hint is edited successfully");
			}
			else
			{
				System.out.println("Failure !!  Password hint is NOT edited successfully" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !!  Password hint is NOT edited successfully" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
					
			
			//Verifying Secret Question
			actualtext = instructorprofile.SecretQuestion.getAttribute("value"); 
			expectedtext = "secq";
					
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Secret Question is edited successfully");
				Reporter.log("Success !! Secret Question is edited successfully");
			}
			else
			{
				System.out.println("Failure !!  Secret Question is NOT edited successfully" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !!  Secret Question is NOT edited successfully" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
					
			
			//Verifying Secret Answer
			actualtext = instructorprofile.SecretAnswer.getAttribute("value"); 
			expectedtext = "seca";
					
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Secret Answer is edited successfully");
				Reporter.log("Success !! Secret Answer is edited successfully");
			}
			else
			{
				System.out.println("Failure !!  Secret Answer is NOT edited successfully" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !!  Secret Answer is NOT edited successfully" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			*/
			//Verifying Address
			actualtext = instructorprofile.Address.getAttribute("value"); 
			expectedtext = "D-120, Sthapatya";
					
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Address is edited successfully");
				Reporter.log("Success !! Address is edited successfully");
			}
			else
			{
				System.out.println("Failure !! Address is NOT edited successfully" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !!  Address is NOT edited successfully" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			
			//Verifying City
			actualtext = instructorprofile.City.getAttribute("value"); 
			expectedtext = "BIRMINGHAM";
					
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! City is edited successfully");
				Reporter.log("Success !! City is edited successfully");
			}
			else
			{
				System.out.println("Failure !! City is NOT edited successfully" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !!  City is NOT edited successfully" + "\n" + "Expected : "  
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
			
			
			//Setting defualt values for fields
			instructorprofile.FirstName.clear();
			instructorprofile.MiddleName.clear();
			instructorprofile.LastName.clear();
			//instructorprofile.Suffix.clear();
			instructorprofile.SelectGender("Select a gender...");
			//instructorprofile.PasswordHint.clear();
			//instructorprofile.SecretQuestion.clear();
			//instructorprofile.SecretAnswer.clear();
			instructorprofile.Address.clear();
			instructorprofile.City.clear();
			instructorprofile.Zip.clear();
			instructorprofile.SSN.clear();
			instructorprofile.FirstName.sendKeys("Automation");
			instructorprofile.MiddleName.sendKeys("");
			instructorprofile.LastName.sendKeys("Testing");
			//instructorprofile.Suffix.sendKeys("");
			instructorprofile.SelectGender("Male");
			//instructorprofile.PasswordHint.sendKeys("company");
			//instructorprofile.SecretQuestion.sendKeys("company?");
			//instructorprofile.SecretAnswer.sendKeys("clarion");
			instructorprofile.Address.sendKeys("123 Main Street");
			instructorprofile.City.sendKeys("Norfolk");
			instructorprofile.Zip.sendKeys("55555");
			instructorprofile.SSN.sendKeys("123123000");
			// Clicking on 'Save Changes' button
			
			JavascriptExecutor jse = (JavascriptExecutor) driver; 
			jse.executeScript("window.scrollBy(0,1200)", "");
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
	
	
	/* Test 5: 
	 * Instructor > Locations  : Verify that user can search for location and 'Schedule an Event | More Information' links works as expected
	*/ 
	@Test
	private void Instructor_Locations_SearchAndLinks() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Instructor > Locations  : Verify that user can search for location and 'Schedule an Event | More Information' links works as expected"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Instructor > Locations  : Verify that user can search for location and 'Schedule an Event | More Information' links works as expected"  + "\n" +
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
			Thread.sleep(4000);

			
		System.out.println("Step 2 : Navigate to Locations page and selecting Program");
		Reporter.log("Step 2 : Navigate to Locations page and selecting Program");
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			Thread.sleep(4000);

			instructorlocations.SelectProgram(2);	
			Thread.sleep(4000);

			//verifying page title
			String expectedtext = "Locations";

			String actualtext = instructorlocations.PageTitle.getText().trim(); 
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! page title is correct");
				Reporter.log("Success !! page title is correct"); 
			}
			else
			{
				System.out.println("Failure !! page title is incorrect" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! page title is incorrect" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
		
			
		System.out.println("Step 3 : Clicking on 'Schedule an Event' link for a location");
		Reporter.log("Step 3 : Clicking on 'Schedule an Event' link for a location");
			
			instructorlocations.ScheduleAnEvent.click();	
			Thread.sleep(5000);
			
			expectedtext = Constants.ApplicationURL_EM +"/event/createevent?p=";
			actualtext = driver.getCurrentUrl();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! 'Schedule an Event' link is working as expected");
				Reporter.log("Success !! 'Schedule an Event' link is working as expected"); 
			}
			else
			{
				System.out.println("Failure !! 'Schedule an Event' link is NOT working" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! 'Schedule an Event' link is NOT working" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}	
			
			driver.navigate().back();
			Thread.sleep(4000);

			
		System.out.println("Step 4 : Clicking on 'More Information' link for a location");
		Reporter.log("Step 4 : Clicking on 'More Information' link for a location");
			
			instructorlocations.MoreInformation.click();	
			Thread.sleep(5000);
			
			expectedtext = Constants.ApplicationURL_EM +"/location/view/";
			actualtext = driver.getCurrentUrl();
			
			if(actualtext.contains(expectedtext.substring(6, expectedtext.length())))
			{
				System.out.println("Success !! 'More Information' link is working as expected");
				Reporter.log("Success !! 'More Information' link is working as expected"); 
			}
			else
			{
				System.out.println("Failure !! 'More Information' link is NOT working" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! 'More Information' link is NOT working" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}	
			
			driver.navigate().back();
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
	

	/* Test 6: 
	 * Instructor > Locations : Verify validation messages while adding new location
	*/ 
	@Test
	private void Instructor_Locations_Add_Validation() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Instructor > Locations : Verify validation messages while adding new location"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Instructor > Locations : Verify validation messages while adding new location"  + "\n" +
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
			Thread.sleep(4000);

			
		System.out.println("Step 2 : Navigate to Locations page and selecting Program");
		Reporter.log("Step 2 : Navigate to Locations page and selecting Program");
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			instructorlocations.SelectProgram(2);	
			Thread.sleep(4000);

			
		System.out.println("Step 3 : Clicking on 'Creat Location' link for adding location");
		Reporter.log("Step 3 : Clicking on 'Creat Location' link for adding location");
			
			instructorlocations.CreateLocation.click();	
			Thread.sleep(5000);
			
			String expectedtext = Constants.ApplicationURL_EM +"/location/create/";
			String actualtext = driver.getCurrentUrl();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! 'Create Location' link is working as expected");
				Reporter.log("Success !! 'Create Location' link is working as expected"); 
			}
			else
			{
				System.out.println("Failure !! 'Create Location' link is NOT working" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! 'Create Location' link is NOT working" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}	
			
		System.out.println("Step 4 : Clicking on 'Submit' button without adding any details");
		Reporter.log("Step 4 : Clicking on 'Submit' button without adding any details");
			
			instructorlocations.Submit.click();	
			Thread.sleep(5000);
			
			
			//Verifying validation messages
			
			expectedtext = "Location Name is required.";
			actualtext = instructorlocations.LocationName_Error.getText().trim();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! correct validation message for 'Location Name' field");
				Reporter.log("Success !! correct validation message for 'Location Name' field"); 
			}
			else
			{
				System.out.println("Failure !! Incorrect validation message for 'Location Name' field" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect validation message for 'Location Name' field" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}	
			
			
			expectedtext = "Address Line 1 is required.";
			actualtext = instructorlocations.Address_Error.getText().trim();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! correct validation message for 'Address' field");
				Reporter.log("Success !! correct validation message for 'Address' field"); 
			}
			else
			{
				System.out.println("Failure !! Incorrect validation message for 'Address' field" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect validation message for 'Address' field" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}	
			
			
			expectedtext = "City is required.";
			actualtext = instructorlocations.City_Error.getText().trim();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! correct validation message for 'City' field");
				Reporter.log("Success !! correct validation message for 'City' field"); 
			}
			else
			{
				System.out.println("Failure !! Incorrect validation message for 'City' field" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect validation message for 'City' field" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}	
			
			
			expectedtext = "ZIP Code is required.";
			actualtext = instructorlocations.Zip_Error.getText().trim();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! correct validation message for 'Zip' field");
				Reporter.log("Success !! correct validation message for 'Zip' field"); 
			}
			else
			{
				System.out.println("Failure !! Incorrect validation message for 'Zip' field" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect validation message for 'Zip' field" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}	
			
			
			expectedtext = "Capacity is required.";
			actualtext = instructorlocations.Capacity_Error.getText().trim();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! correct validation message for 'capacity' field");
				Reporter.log("Success !! correct validation message for 'capacity' field"); 
			}
			else
			{
				System.out.println("Failure !! Incorrect validation message for 'capacity' field" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect validation message for 'capacity' field" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}	
			
			
			
			
			/*expectedtext = "Invalid value.";
			actualtext = instructorlocations.Region_Error.getText().trim();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! correct validation message for 'Region' field");
				Reporter.log("Success !! correct validation message for 'Region' field"); 
			}
			else
			{
				System.out.println("Failure !! Incorrect validation message for 'Region' field" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect validation message for 'Region' field" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}	
			
			
			
			expectedtext = "Invalid value.";
			actualtext = instructorlocations.County_Error.getText().trim();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! correct validation message for 'County' field");
				Reporter.log("Success !! correct validation message for 'County' field"); 
			}
			else
			{
				System.out.println("Failure !! Incorrect validation message for 'County' field" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect validation message for 'County' field" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}	*/
			
					
			

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
	 * Instructor > Locations  : Verify that user can add a new Location successfully
	*/ 
	@Test
	private void Instructor_Locations_Add() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Instructor > Locations  : Verify that user can add a new Location successfully"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Instructor > Locations  : Verify that user can add a new Location successfully"  + "\n" +
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
			Thread.sleep(4000);

			
		System.out.println("Step 2 : Navigate to Locations page and selecting Program");
		Reporter.log("Step 2 : Navigate to Locations page and selecting Program");
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			instructorlocations.SelectProgram(2);	
			Thread.sleep(4000);

			
		System.out.println("Step 3 : Clicking on 'Creat Location' link for adding location");
		Reporter.log("Step 3 : Clicking on 'Creat Location' link for adding location");
			
			instructorlocations.CreateLocation.click();	
			Thread.sleep(5000);
			
			String expectedtext = Constants.ApplicationURL_EM +"/location/create/";
			String actualtext = driver.getCurrentUrl();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! 'Creat Location' link is working as expected");
				Reporter.log("Success !! 'Creat Location' link is working as expected"); 
			}
			else
			{
				System.out.println("Failure !! 'Creat Location' link is NOT working" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! 'Creat Location' link is NOT working" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}	
			
		System.out.println("Step 4 : Entering required details and clicking on 'Submit' button");
		Reporter.log("Step 4 : Entering required details and clicking on 'Submit' button");
		
			String locationname = "Clarion" + JH.GenerateRandomNumber();
			instructorlocations.LocationName.sendKeys(locationname);
			instructorlocations.SpecialInstructions.sendKeys("Have fun");
			instructorlocations.Address.sendKeys("277 Cantebury Dr");
			instructorlocations.City.sendKeys("Lemoore");
			instructorlocations.Zip.sendKeys("93245");
			instructorlocations.Phone.sendKeys("9874343334");
			instructorlocations.Room.sendKeys("5");
			instructorlocations.Capacity.sendKeys("20");
			instructorlocations.SelectRegion(2);
			instructorlocations.SelectCounty(2);
			
			instructorlocations.Submit.click();	
			Thread.sleep(10000);
			
/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
		System.out.println("Step 5 : Searching for added location and verifying details");
		Reporter.log("Step 5 : Searching for added location and verifying details");
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location/search/14");
			//instructorlocations.SelectSearchTypeByName();
			Thread.sleep(2000);

			instructorlocations.SearchBox.sendKeys(locationname);
			Thread.sleep(5000);

			instructorlocations.SearchBox.sendKeys(Keys.ENTER);
			Thread.sleep(5000);

			/*System.out.println(instructorlocations.Title.getText());
			System.out.println(instructorlocations.Description.getText());*/
			

			//Verifying Location Title
			expectedtext = locationname;
			actualtext = instructorlocations.Title1.getText().trim();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Location Title is correct");
				Reporter.log("Success !! Location Title is correct"); 
			}
			else
			{
				System.out.println("Failure !! Location Title is incorrect" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Location Title is incorrect" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}	
			
			
			//Verifying Location Info
			/*expectedtext = "277 Cantebury Dr" + "\n" + 
							"Lemoore, TX 93245" + "\n" + 
							"Room: 5" + "\n" + 
							"Phone: 9874343334" + "\n" + 
							"Capacity: 20" + "\n" + 
							"Have fun";*/
			
			expectedtext = "277 Cantebury Dr" + "\n" + 
					"Lemoore, TX 93245";
			
			actualtext = instructorlocations.Description1.getText().trim();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Location Info is correct");
				Reporter.log("Success !! Location Info is correct"); 
			}
			else
			{
				System.out.println("Failure !! Location Info is incorrect" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Location Info is incorrect" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}	
			
			// Deleting the location
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location");
			instructorlocations.SelectProgram(2);
			Thread.sleep(5000);

			//instructorlocations.SelectSearchTypeByName();
			//instructorlocations.SearchBox.sendKeys(locationname);
			//instructorlocations.SearchButton.click();
			SeleniumFunc.EnterValueInTextbox("css", "#search-type-name", locationname);
			SeleniumFunc.ClickOnElement("css", "input[value*='Search']");
			Thread.sleep(5000);
			
			WebElement editlink = driver.findElement(By.cssSelector(".span12 > a:nth-of-type(3)"));
			editlink.click();
			Thread.sleep(4000);
						
			SeleniumFunc.ClickOnElement("css", ".btn.btn-danger");
			Thread.sleep(4000);
						
			SeleniumFunc.ClickOnElement("css", ".btn.btn-primary");
			Thread.sleep(4000);

			System.out.println("Location : "+locationname+" Deleted Successfully!!");
			Reporter.log("Location : "+locationname+" Deleted Successfully!!"); 
						
			

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
	 * Instructor > Schedule  :  Verify elements on event schedule page
	*/ 
	@Test
	private void Instructor_Schedule_VerifyElements() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Instructor > Schedule  :  Verify elements on event schedule page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Instructor > Schedule  :  Verify elements on event schedule page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
	
		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(4000);

			
		System.out.println("Step 2 : Navigate to Schedule page and verifying UI elements");
		Reporter.log("Step 2 : Navigate to Schedule page and verifying UI elements"); 
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
				Thread.sleep(4000);

				//Verifying page title
				/*String expectedtext = "My Event Schedule";
				
				String actualtext = instructorschedule.PageTitle.getText().trim(); 
			
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! page title is correct");
					Reporter.log("Success !! page title is correct"); 
				}
				else
				{
					System.out.println("Failure !! page title is incorrect" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! page title is incorrect" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying Information text
				expectedtext = "Your event schedule is shown below. Click on an event to view detailed information or make changes." + "\n" 
								+ "To create a new event, select a calendar date that you wish to schedule.";
				actualtext = instructorschedule.InfoText.getText().trim(); 
			
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success !! Information text is correct");
					Reporter.log("Success !! Information text is correct"); 
				}
				else
				{
					System.out.println("Failure !! Information text is incorrect" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! Information text is incorrect" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}*/
				
				
				//Verifying presence of 'Calendar View' link
				String expectedtext = "Calendar";
				String actualtext = instructorschedule.CalenderView.getText().trim(); 
			
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success !! 'Calendar View' link is present");
					Reporter.log("Success !! 'Calendar View' link is present");
				}
				else
				{
					System.out.println("Failure !! 'Calendar View' link is NOT present" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! 'Calendar View' link is NOT present" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying presence of 'List View' link
				expectedtext = "List";
				actualtext = instructorschedule.ListView.getText().trim(); 
			
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success !! 'List View' link is present");
					Reporter.log("Success !! 'List View' link is present");
				}
				else
				{
					System.out.println("Failure !! 'List View' link is NOT present" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! 'List View' link is NOT present" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying presence of 'Previous Month' link
				/* expectedtext = "Previous Month";
				* actualtext = instructorschedule.PreviousMonthIcon.getText().trim(); */
				
				if(SeleniumFunc.IsElementPresent(instructorschedule.PreviousMonthIcon))
				{
					System.out.println("Success !! 'Previous Month' link is present");
					Reporter.log("Success !! 'Previous Month' link is present");
				}
				else
				{
					System.out.println("Failure !! 'Previous Month' link is NOT present" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! 'Previous Month' link is NOT present" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying presence of 'Previous Month' link
			/*	expectedtext = "Previous Month";
				actualtext = instructorschedule.PreviousMonthLink.getText().trim(); */
			
				/*if(SeleniumFunc.IsElementPresent(instructorschedule.NextMonthIcon))
				{
					System.out.println("Success !! 'Previous Month' link is present");
					Reporter.log("Success !! 'Previous Month' link is present");
				}
				else
				{
					System.out.println("Failure !! 'Previous Month' link is NOT present" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! 'Previous Month' link is NOT present" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}*/
				
				
				//Verifying presence of 'Next Month' link
				/*expectedtext = "Next Month";
				actualtext = instructorschedule.NextMonthLink.getText().trim(); */
			
				if(SeleniumFunc.IsElementPresent(instructorschedule.NextMonthIcon))
				{
					System.out.println("Success !! 'Next Month' link is present");
					Reporter.log("Success !! 'Next Month' link is present");
				}
				else
				{
					System.out.println("Failure !! 'Next Month' link is NOT present" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! 'Next Month' link is NOT present" + "\n" + "Expected : "  
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
	
	
	/* Test 9: 
	 * Instructor > Schedule  :  Verify user can navigate to New Event page for selected date
	*/ 
	@Test
	private void Instructor_Schedule_NavigateToEventPage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Instructor > Schedule  :  Verify user can navigate to New Event page for selected date"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 : Instructor > Schedule  :  Verify user can navigate to New Event page for selected date"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
	
		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(4000);

			
		System.out.println("Step 2 : Navigate to Schedule page and clicking on Date from Event Calender");
		Reporter.log("Step 2 : Navigate to Schedule page and clicking on Date from Event Calender"); 
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
				instructorschedule.NewEvent.click();
				Thread.sleep(5000);
				
				//Verifying whether user is navigated to 'Create an Event' page
				String expectedtext = Constants.ApplicationURL_EM + "/event/createevent";
				String actualtext = driver.getCurrentUrl(); 
			
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success !! user is navigated to 'Create an Event' page");
					Reporter.log("Success !! user is navigated to 'Create an Event' page");
				}
				else
				{
					System.out.println("Failure !! user is NOT navigated to 'Create an Event' page" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! user is NOT navigated to 'Create an Event' page" + "\n" + "Expected : "  
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
	
	
	/* Test 10: 
	 * Instructor > Event : Verify details on Create an Event page 
	*/ 
	@Test
	private void Instructor_Event_VerifyElements() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : Instructor > Event : Verify details on Create an Event page "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10 : Instructor > Event : Verify details on Create an Event page "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
	
		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(4000);

			
		System.out.println("Step 2 : Navigate to Schedule page and clicking on Date from Event Calender");
		Reporter.log("Step 2 : Navigate to Schedule page and clicking on Date from Event Calender"); 
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
				instructorschedule.NewEvent.click();
				Thread.sleep(5000);
				
				//Verifying whether user is navigated to 'Create an Event' page
				String expectedtext = Constants.ApplicationURL_EM + "/event/createevent";
				String actualtext = driver.getCurrentUrl(); 
			
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success !! user is navigated to 'Create an Event' page");
					Reporter.log("Success !! user is navigated to 'Create an Event' page");
				}
				else
				{
					System.out.println("Failure !! user is NOT navigated to 'Create an Event' page" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! user is NOT navigated to 'Create an Event' page" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
		
		System.out.println("Step 3 : Verify details on Create an Event page ");
		Reporter.log("Step 3 : Verify details on Create an Event page "); 
		
				//Verifying Page title
				expectedtext = "Create an Event";
				actualtext = instructorcreateevent.PageTitle.getText().trim(); 
			
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success !! Page title is correct");
					Reporter.log("Success !! Page title is correct");
				}
				else
				{
					System.out.println("Failure !! Page title is incorrect" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! Page title is incorrect" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying Info text
				expectedtext = "To create an event, fill in any missing data for date, time, program and location. Web Registration Capacity defaults to the capacity that has been assigned to the selected location. However, if you wish to allow more or fewer online registrations for this event, you may edit the Web Registration Capacity.";
				actualtext = instructorcreateevent.InfoText.getText().trim(); 
			
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success !! Info text is correct");
					Reporter.log("Success !! Info text is correct");
				}
				else
				{
					System.out.println("Failure !! Info text is incorrect" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! Info text is incorrect" + "\n" + "Expected : "  
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

	
	/* Test 11: 
	 * Instructor > Event : Verify that mandatory fields validation messages are displayed while creating new Event
	*/ 
	@Test
	private void Instructor_Event_VerifyMandatoryFieldValidations() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 11 : Instructor > Event : Verify that mandatory fields validation messages are displayed while creating new Event"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 11 : Instructor > Event : Verify that mandatory fields validation messages are displayed while creating new Event"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
	
		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(4000);

			
		System.out.println("Step 2 : Navigate to Create Event page ");
		Reporter.log("Step 2 : Navigate to Create Event page "); 
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
				instructorschedule.NewEvent.click();
				Thread.sleep(5000);
				
		
		System.out.println("Step 3 : On Create Event page , selecting Program and click on 'Create Event' button");
		Reporter.log("Step 3 : On Create Event page , selecting Program and click on 'Create Event' button"); 
		
				instructorcreateevent.SelectProgram();
				Thread.sleep(2000);
				
				instructorcreateevent.CreateEvent.click();
				Thread.sleep(4000);

				
				//Verifying validation message for Location field
				String expectedtext = "Please select a valid location or create one";
				String actualtext = SeleniumFunc.AcceptAlertAndGetText(); 
			
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success !! Correct validation message for Location field is displayed");
					Reporter.log("Success !! Correct validation message for Location field is displayed");
				}
				else
				{
					System.out.println("Failure !! Incorrect validation message for Location field is displayed" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! Inorrect validation message for Location field is displayed" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
			
				
		System.out.println("Step 4 : Entering  Location info and click on 'Create Event' button ");
		Reporter.log("Step 4 : Entering  Location info and click on 'Create Event' button ");
		
				instructorcreateevent.Location.sendKeys("Clarion");
				Thread.sleep(5000);
				instructorcreateevent.Location_FirstChoice.click();
				Thread.sleep(2000);
				
				instructorcreateevent.CreateEvent.click();
				Thread.sleep(4000);

				
				//Verifying validation message for Event Date 
				expectedtext = "Please set the date and time";
				actualtext = SeleniumFunc.AcceptAlertAndGetText(); 
			
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success !! Correct validation message for Event Date is displayed");
					Reporter.log("Success !! Correct validation message for Event Date is displayed");
				}
				else
				{
					System.out.println("Failure !! Incorrect validation message for Event Date is displayed" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual: " + "\n" +  actualtext);
					Reporter.log("Failure !! Inorrect validation message for Event Date is displayed" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual: " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
		System.out.println("Step 5 : Selecting Start&End date for Event and clicking on 'Create Event' button ");
		Reporter.log("Step 5 : Selecting Start&End date for Event and clicking on 'Create Event' button ");
		
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
			
			instructorcreateevent.CreateEvent.click();
			Thread.sleep(4000);

			//Verifying validation message for Event Date 
			expectedtext = "Please set the date and time";
			actualtext = SeleniumFunc.AcceptAlertAndGetText(); 
		
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Correct validation message for Event Date is displayed");
				Reporter.log("Success !! Correct validation message for Event Date is displayed");
			}
			else
			{
				System.out.println("Failure !! Incorrect validation message for Event Date is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Inorrect validation message for Event Date is displayed" + "\n" + "Expected : "  
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

	
	/* Test 12: 
	 * Instructor > Event : Verify that user can create Event successfully
	*/ 
	@Test
	private void Instructor_Event_Create() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 12 : Instructor > Event : Verify that user can create Event successfully"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 12 : Instructor > Event : Verify that user can create Event successfully"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
	
		System.out.println("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Instructor at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			login.EnterUsername(Constants.EM_Instructor_Username);
			login.EnterPassword(Constants.EM_Instructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(4000);

			//Navigate to 'My Event Schedule' page and delete all Events
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			//instructorschedule.DeleteAllEvents();
			
		System.out.println("Step 2 : Navigate to Create Event page ");
		Reporter.log("Step 2 : Navigate to Create Event page "); 
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
				instructorschedule.NewEvent.click();
				Thread.sleep(5000);
				
		
		System.out.println("Step 3 : On Create Event page , entering all required details and click on 'Create Event' button");
		Reporter.log("Step 3 : On Create Event page , entering all required detailsb and click on 'Create Event' button"); 
		
				instructorcreateevent.SelectProgram();
				Thread.sleep(2000);
				
				instructorcreateevent.Location.sendKeys("Clarion");
				Thread.sleep(5000);
				instructorcreateevent.Location_FirstChoice.click();
				Thread.sleep(2000);
			
				instructorcreateevent.SelectStartDate("6", "30", "PM");
				instructorcreateevent.SelectEndDate("7", "30", "PM");
				
				instructorcreateevent.AddEventDate.click();
				Thread.sleep(2000);
				
				instructorcreateevent.CreateEvent.click();
				Thread.sleep(4000);

				
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
					
			//Updated due to change in functionality
			//https://kalkomey.tpondemand.com/entity/14661
			/*SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/event/calendar");
			Thread.sleep(4000);*/

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
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Name" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
				
			//Going to Event Roster page and deleting event so new event can be added with same test data
			
			try {
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM+ "/eventregistration/roster/" + eventid);
				instructoreventroster.EventDelete.click();
				Thread.sleep(5000);

				instructoreventroster.Confirm_EventDelete.click();
				System.out.println("Event: "+eventid+" Deleted Successfully!!");
				Thread.sleep(5000);
			} catch (Exception e) {
				
			}
			
		
	 /*
	  * Marking Test Pass or Fail as per the value of AssertFailedCount variable
	  */
	   if(AssertFailedCount>0) 
	   {
	    System.out.println("------Test Case failed ,Deleting Event------");
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
	
	/* Test 13: 
	 * As an instructor/admin want to accurately see which fields are required based on my program's set up
	 * Test Case ID : 21068
	*/ 
	@Test
	private void Asterisks_Fields() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 13 : Verify Asterisks symbol for Mailing and physical phone number"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 13 :  Verify Asterisks symbol for Mailing and physical phone number"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		EMAddStudentPage addstud = new EMAddStudentPage(driver);
		AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
	
		System.out.println("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			
			if(SeleniumFunc.IsElementPresent("linkText", "Log Out"))
			{
				SeleniumFunc.ClickOnElement("linkText", "Log Out");
			}
			
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(4000);
		
		System.out.println("Step 2 : In Event Manager, go to an event roster for New York and click Add Student.");
		Reporter.log("Step 2 : In Event Manager, go to an event roster for New York and click Add Student."); 
		
			SeleniumFunc.ClickOnElement("linkText", "New York Department of Environmental Conservation");
			SeleniumFunc.ClickOnElement("linkText", "New York Trapper Education Course");
			agencyhome.ClickOnCreateEventLink();
			
			instructorcreateevent.SelectProgram();
			Thread.sleep(2000);
			
			instructorcreateevent.Location.clear();
			
			instructorcreateevent.Location.sendKeys("Demo Location for New York");
			Thread.sleep(5000);
			instructorcreateevent.Location_FirstChoice.click();
			Thread.sleep(2000);
			
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
				
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
				
			instructorcreateevent.CreateEvent.click();
			Thread.sleep(5000);
			instructorcreateevent.ActivateEvent.click();
			String URL = driver.getCurrentUrl();
			System.out.println("Url :"+URL);
			
			String EventID = instructoreventroster.EventId.getText().trim();
			System.out.println("Event Created : "+EventID);
			
			SeleniumFunc.ClickOnElement("linkText", "Event Roster");
			SeleniumFunc.ClickOnElement("linkText", "Add Student");
			
		System.out.println("Step 3 : Verify there should not be a red * next to Mailing Phone and Physical Phone.");
		Reporter.log("Step 3 : Verify there should not be a red * next to Mailing Phone and Physical Phone."); 
		
			String ExpectedMailing = "Mailing Phone";
			String ExpectedPhysical= "Physical Phone";
			
			if(SeleniumFunc.GetElementText("css", "#student_edit_form > fieldset:nth-child(8) > div:nth-child(8) > label").equals(ExpectedMailing))
			{
				System.out.println("Success !! Asterisk * next to Mailing Phone is not present");
				Reporter.log("Success !! Asterisk * next to Mailing Phone is not present");
			}
			else
			{
				System.out.println("Failure !! Asterisk * next to Mailing Phone is present");
				Reporter.log("Failure !! Asterisk * next to Mailing Phone is present");
			}
			
				SeleniumFunc.ClickOnElement("css", "#s_addr_is_same");
			
			if(SeleniumFunc.GetElementText("css", "#s_addr > div:nth-child(7) > label").equals(ExpectedPhysical))
			{
				System.out.println("Success !! Asterisk * next to Physical Phone is not present");
				Reporter.log("Success !! Asterisk * next to Physical Phone is not present");
			}
			else
			{
				System.out.println("Failure !! Asterisk * next to Physical Phone is present");
				Reporter.log("Failure !! Asterisk * next to Physical Phone is present");
			}
			
				SeleniumFunc.ClickOnElement("css", "#s_addr_is_same");
			
		System.out.println("Step 4 : Fill out the form, but leave the phone number fields blank.");
		Reporter.log("Step 4 : Fill out the form, but leave the phone number fields blank.");
		
				addstud.FillRegisterStudent("Clarion", "User", "", "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "", "12345");
				addstud.SelectCountryForAdditionalInfo("Albany");
				addstud.ClickOnRegisterButton();
				Thread.sleep(5000);
				
				//Verify student is added successfully
				String expectedtext = "User, Clarion";
				String actualtext = SeleniumFunc.GetElementText("css", ".checkbox.checkbox-sidelong>a");
			
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
				
		System.out.println("Step 5 : In Event Manager, go to an event roster for any agency that is not New York and click Add Student.");
		Reporter.log("Step 5 : In Event Manager, go to an event roster for any agency that is not New York and click Add Student.");
				
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
					Thread.sleep(2000);
					searchevent.ClickOnSearchEventLink();
					Thread.sleep(5000);

					searchevent.SelectSearchTypeByVisibleText("ID");
					searchevent.EnterSearchData("82993");
					searchevent.ClickOnSearchButton();
					Thread.sleep(5000);
					SeleniumFunc.ClickOnElement("linkText", "Standard Course");
					SeleniumFunc.ClickOnElement("linkText", "Event Roster");
					SeleniumFunc.ClickOnElement("linkText", "Add Student");
					
		System.out.println("Step 6 : Verify there should be a red * next to Mailing Phone and Physical Phone.");
		Reporter.log("Step 6 : Verify there should be a red * next to Mailing Phone and Physical Phone.");
		
					String expectedMailing	= "Mailing Phone *";
					String expectedPhysical	= "Physical Phone *";
					
					
			  if(SeleniumFunc.GetElementText("css", "#student_edit_form > fieldset:nth-child(8) > div:nth-child(9) > label").equals(expectedMailing))
			  {
				  	System.out.println("Success !! Asterisk * next to Mailing Phone is present");
					Reporter.log("Success !! Asterisk * next to Mailing Phone is present");
			  }
			  else
			  {
					System.out.println("Failure !! Asterisk * next to Mailing Phone is not present");
					Reporter.log("Failure !! Asterisk * next to Mailing Phone is not present");
					AssertFailedCount++;
			  }
			  
			  		SeleniumFunc.ClickOnElement("id", "s_addr_is_same");
			  		
			  if(SeleniumFunc.GetElementText("css", "#s_addr > div:nth-child(7) > label").equals(expectedPhysical))
			  {
					System.out.println("Success !! Asterisk * next to Mailing Phone is present");
					Reporter.log("Success !! Asterisk * next to Mailing Phone is present");
			  }
			  else
			  {
					System.out.println("Failure !! Asterisk * next to Mailing Phone is present");
					Reporter.log("Failure !! Asterisk * next to Mailing Phone is present");
					AssertFailedCount++;
			  }	  
			  
		System.out.println("Step 7 :Fill out the form, but leave the phone number fields blank. Click Create New Registration.");
		Reporter.log("Step 7 : Fill out the form, but leave the phone number fields blank. Click Create New Registration.");
		
		
					addstud.FillRegisterStudent("Clarion", "User", "", "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "", "12345");
					addstud.FillPhysicalAddress("12345", "Test", "city", "");
					addstud.ClickOnRegisterButton();
					Thread.sleep(5000);
					
		System.out.println("Step 8 : Verify This field is required next to the phone number field is present.");
		Reporter.log("Step 8 : Verify This field is required next to the phone number field is present.");
		
					String ExpectedValidation = "This field is required.";
					
			if(SeleniumFunc.GetElementText("css", "#student_edit_form > fieldset:nth-child(8) > div:nth-child(9) > div > label").equals(ExpectedValidation)
					&&SeleniumFunc.GetElementText("css", "#s_addr > div:nth-child(7) > div > label").equals(ExpectedValidation))
			{
				System.out.println("Success !! Text 'This field is required' next to the phone number fields present.");
				Reporter.log("Success !! Text 'This field is required' next to the phone number fields present.");
			}
			else
			{
				System.out.println("Failure !! Text 'This field is required' is missing. Expected: "+ExpectedValidation);
				Reporter.log("Failure !! Text 'This field is required' is missing. Expected: "+ExpectedValidation);
				AssertFailedCount++;
			}	
			
		// Delete Event
			
				SeleniumFunc.ToGoToUrl(URL);SeleniumFunc.ClickOnElement("linkText", "Event Roster");
				Thread.sleep(2000);
				SeleniumFunc.ClickOnElement("linkText", "Unschedule & Remove");		
				Thread.sleep(2000);			
				SeleniumFunc.ClickOnElement("linkText", "Unschedule");
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