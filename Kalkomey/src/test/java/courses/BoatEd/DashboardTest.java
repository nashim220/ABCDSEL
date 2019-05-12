package courses.BoatEd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.CertificationInformationPage;
import pageFactory.Courses.ContentsPage;
import pageFactory.Courses.CourseCompletePage;
import pageFactory.Courses.FinalExamPage;
import pageFactory.Courses.KECourseAdminPage;
import pageFactory.Courses.LoginPage;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.PaymentInformationPage;
import pageFactory.Courses.QuizPage;
import pageFactory.Courses.RegistrationPage;

import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class DashboardTest 
{
	private WebDriver driver;
	private Browser b = new Browser();
	JavaHelpers JH = new JavaHelpers();
	
	
	@Parameters({ "browser", "environment", "os" })
	@BeforeMethod
	public void setUp(String browser, String environment , String OS) throws Exception 
	{		
		driver= b.setUp(browser, environment, OS);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		keadmin.GoToAdminPage(browser);	
	}

	@AfterMethod
	public void teardown() throws Exception
	{
		b.tearDown();
	}
	
	

	/* Test 1: 
	 * Verify "Welcome Aboard" modal is displayed on first topic 
	*/ 
	@Test
	private void Dashboard_WelComeAboardModel() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 :Verify 'Welcome Aboard' modal is displayed on first topic "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 :Verify 'Welcome Aboard' modal is displayed on first topic"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);	
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "dashboard" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "dashboard" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			Thread.sleep(10000);
			
	/*	System.out.println("Step 2 : Clicking on 'Contents' link from top header");
		Reporter.log("Step 2 : Clicking on 'Contents' link from top header"); 
			
			content.ClickOnContentLink();
			Thread.sleep(4000);

			
		System.out.println("Step 3 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button");
		Reporter.log("Step 3 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button"); 
		
		
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(5000);*/
			
			
		System.out.println("Step 2 : Verifying 'Welcome Aboard' model on Topic 1, Page 1 ");
		Reporter.log("Step 2 : Verifying 'Welcome Aboard' model on Topic 1, Page 1");
			
			//SeleniumFunc.ClickOnElement("css", "#intro-modal-dismiss");
			
			String actualattributevalue = SeleniumFunc.GetAttributeValue("css", "#intro-modal", "style");
			String expectedattributevalue = "display: block;";
			
			if(actualattributevalue.equals(expectedattributevalue))
			{
				System.out.println("Success !!  'Welcome Aboard' model displayed on Topic 1, Page 1");
				Reporter.log("Success !!  'Welcome Aboard' model displayed on Topic 1, Page 1");
				
				//verifying header, text on 'Welcome Aboard' model
				
				String expectedheadertext = "Welcome Aboard, test!";
				String actaulheadertext = SeleniumFunc.GetElementText("css", ".modal-title");
				
				if(actaulheadertext.equals(expectedheadertext))
				{
					System.out.println("Success !!  'Welcome Aboard' model has correct header text i.e. " + actaulheadertext);
					Reporter.log("Success !!  'Welcome Aboard' model has correct header text i.e. " + actaulheadertext);
				}
				else
				{
					System.out.println("Failure !! 'Welcome Aboard' model has incorrect header text i.e. " + actaulheadertext);
					Reporter.log("Failure !!  'Welcome Aboard' model has incorrect header text i.e. " + actaulheadertext);
					AssertFailedCount++;
				}
				
/*				System.out.println("Step 5 : Clicking on 'I Understand' button on 'Welcome Aboard' model");
				Reporter.log("Step 5 : Clicking on 'I Understand' button on 'Welcome Aboard' model");
				
					SeleniumFunc.ClickOnElement("id", "intro-modal-dismiss");
					Thread.sleep(4000);*/

					//Verifying whether "Am I making Progress?" model displayed or not
					
				/*	if(SeleniumFunc.IsElementPresent("css", "div[class='popover fade bottom in']"))
					{
						System.out.println("Success !!  'Am I making progress?' model displayed on Topic 1, Page 1");
						Reporter.log("Success !!  'Am I making progress?' model displayed on Topic 1, Page 1");*/
						
						/*//verifying header, text on 'Am I making progress?' model
						
						expectedheadertext = "Am I making progress?";
						actaulheadertext = SeleniumFunc.GetElementText("css", "div[class='popover fade bottom in'] h3");
						
						if(actaulheadertext.equals(expectedheadertext))
						{
							System.out.println("Success !!  'Am I making progress?' model has correct header text i.e. " + actaulheadertext);
							Reporter.log("Success !!  'Am I making progress?' model has correct header text i.e. " + actaulheadertext);
						}
						else
						{
							System.out.println("Failure !! 'Am I making progress?' model has incorrect header text i.e. " + actaulheadertext);
							Reporter.log("Failure !!  'Am I making progress?' model has incorrect header text i.e. " + actaulheadertext);
							AssertFailedCount++;
						}*/
						
						
					/*	expectedheadertext = "This progress bar and outline will show you where you've been and where you are going.";
						actaulheadertext = SeleniumFunc.GetElementText("css", "div[class='popover fade bottom in'] p");
						
						if(actaulheadertext.equals(expectedheadertext))
						{
							System.out.println("Success !!  'Am I making progress?' model has correct description text i.e. " + actaulheadertext);
							Reporter.log("Success !!  'Am I making progress?' model has correct description text i.e. " + actaulheadertext);
						}
						else
						{
							System.out.println("Failure !! 'Am I making progress?' model has incorrect description text i.e. " + actaulheadertext);
							Reporter.log("Failure !!  'Am I making progress?' model has incorrect description text i.e. " + actaulheadertext);
							AssertFailedCount++;
						}
						*/
					/*	System.out.println("Step 6 : Clicking on 'Next' button on 'Am I making progress?' model");
						Reporter.log("Step 5 : Clicking on 'Next' button on 'Am I making progress?' model");
						
							SeleniumFunc.ClickOnElement("id", "trigger-timer");
							Thread.sleep(4000);

							//Verifying whether "Whoa, you don't have to rush." model displayed or not
							
							if(SeleniumFunc.IsElementPresent("css", "div[class='popover fade top in']"))
							{
								System.out.println("Success !!  'Whoa, you don't have to rush.' model displayed on Topic 1, Page 1");
								Reporter.log("Success !!  'Whoa, you don't have to rush.' model displayed on Topic 1, Page 1");
								
								//verifying header, text on 'Am I making progress?' model
								
								expectedheadertext = "Whoa, you don't have to rush.";
								actaulheadertext = SeleniumFunc.GetElementText("css", "div[class='popover fade top in'] h3");
								
								if(actaulheadertext.equals(expectedheadertext))
								{
									System.out.println("Success !!  'Whoa, you don't have to rush.' model has correct header text i.e. " + actaulheadertext);
									Reporter.log("Success !!  'Whoa, you don't have to rush.' model has correct header text i.e. " + actaulheadertext);
								}
								else
								{
									System.out.println("Failure !! 'Whoa, you don't have to rush.' model has incorrect header text i.e. " + actaulheadertext);
									Reporter.log("Failure !!  'Whoa, you don't have to rush.' model has incorrect header text i.e. " + actaulheadertext);
									AssertFailedCount++;
								}
								
								
								expectedheadertext = "This timer shows you how long you must stay on each page, but feel free to study as long as you need.";
								actaulheadertext = SeleniumFunc.GetElementText("css", "div[class='popover fade top in'] p");
								
								if(actaulheadertext.equals(expectedheadertext))
								{
									System.out.println("Success !!  'Whoa, you don't have to rush.' model has correct description text i.e. " + actaulheadertext);
									Reporter.log("Success !!  'Whoa, you don't have to rush.' model has correct description text i.e. " + actaulheadertext);
								}
								else
								{
									System.out.println("Failure !! 'Whoa, you don't have to rush.' model has incorrect description text i.e. " + actaulheadertext);
									Reporter.log("Failure !!  'Whoa, you don't have to rush.' model has incorrect description text i.e. " + actaulheadertext);
									AssertFailedCount++;
								}
								
								System.out.println("Step 6 : Clicking on 'Got it' button on 'Whoa, you don't have to rush. model");
								Reporter.log("Step 5 : Clicking on 'Got it' button on 'Whoa, you don't have to rush.' model");
								
									SeleniumFunc.ClickOnElement("id", "trigger-go");
									Thread.sleep(4000);

									if(SeleniumFunc.IsElementPresent("css", "div[class='popover fade top in']"))
									{
										System.out.println("Failure !! 'Whoa, you don't have to rush.' model is NOT closed");
										Reporter.log("Failure !! 'Whoa, you don't have to rush.' model is NOT closed");
										AssertFailedCount++;
									}
									else
									{
										System.out.println("Success !! 'Whoa, you don't have to rush.' model is closed");
										Reporter.log("Success !! 'Whoa, you don't have to rush.' model is closed");
										
									}
							}
							else
							{
								System.out.println("Failure !!  'Whoa, you don't have to rush.' model NOT displayed on Topic 1, Page 1");
								Reporter.log("Failure !!  'Whoa, you don't have to rush.' model NOT displayed on Topic 1, Page 1");
								AssertFailedCount++;
								
							}
						}
						else
						{
							System.out.println("Failure !!  'Am I making progress?' model NOT displayed on Topic 1, Page 1");
							Reporter.log("Failure !!  'Am I making progress?' model NOT displayed on Topic 1, Page 1");
							AssertFailedCount++;
							
						}*/
					
			}
			else
			{
				System.out.println("Failure !!  'Welcome Aboard' model NOT displayed on Topic 1, Page 1");
				Reporter.log("Failure !!  'Welcome Aboard' model NOT displayed on Topic 1, Page 1");
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
	 * Verify user is asked to complete Quiz on login if logged out while attempting Quiz
	*/ 
	@Test
	private void Dashboard_ResumeQuiz() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify user is asked to complete Quiz on login if logged out while attempting Quiz"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2: Verify user is asked to complete Quiz on login if logged out while attempting Quiz"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		QuizPage quiz = new QuizPage(driver);
		LoginPage login  = new LoginPage(driver);
		ContentsPage content = new ContentsPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "dashboard" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "dashboard" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			
			Thread.sleep(8000);
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and marking Unit 1 as complete. Navigating back to Contents page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and marking Unit 1 as complete. Navigating back to Contents page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Progress");
			keadmin.Progress_SelectUnitAndMarkAsComplete(1);
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

			
		System.out.println("Step 3 : Clicking on 'Unit 1 Quiz' link");
		Reporter.log("Step 3 : Clicking on 'Unit 1 Quiz' link");
		
			SeleniumFunc.ClickOnElement("css", "li.expanded ul li:nth-child(14) a");
			Thread.sleep(4000);

		
		System.out.println("Step 4 : Clicking Begin Quiz button" );
		Reporter.log("Step 4 : Clicking Begin Quiz button");
		
			quiz.ClickOnNextButton();
			Thread.sleep(4000);

			
		System.out.println("Step 5 : On Quiz Question , selecting answer and clicking on Next button" );
		Reporter.log("Step 5 : On Quiz Question , selecting answer and clicking on Next button");
			
				//Selecting answer
				SeleniumFunc.ClickOnElement("id", "answer_text_a");
				Thread.sleep(4000);

				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
				Thread.sleep(4000);

				
		System.out.println("Step 6 : Logging out of application and login again" );
		Reporter.log("Step 6 : Logging out of application and login again");
			
				SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");
				SeleniumFunc.ToGoToUrl(Constants.LoginPage);
				Thread.sleep(4000);

				login.EnterUsername(username);
				login.EnterPassword(password);
				login.ClickOnLogInButton();
				Thread.sleep(4000);

				
				//verifying whether user is asked to complete Quiz first
				
				String actualtext = SeleniumFunc.GetElementText("css", "div[class='alert alert-info fade in']").trim();
				String expectedtext = "Please finish your quiz currently in progress.";
				
				if(actualtext.contains(expectedtext))
				{
					System.out.println(" - user is asked to continue quiz , message : " + expectedtext);
					Reporter.log("- user is asked to continue quiz , message : " + expectedtext); 
				}
				else
				{
					System.out.println(" - user is NOT asked to continue quiz , message : " + actualtext);
					Reporter.log("- user is NOT asked to continue quiz , message : " + actualtext); 
					AssertFailedCount++;
				}
				
				actualtext = SeleniumFunc.GetElementText("css", "div[class='alert alert-block alert-warning']").trim();
				expectedtext = "You are currently in the middle of the Unit 1 Quiz." + "\n" +
								"Once started, you must complete the quiz. Please continue below.";
				
				if(actualtext.contains(expectedtext))
				{
					System.out.println(" - user is asked to continue quiz , message : " + expectedtext);
					Reporter.log("- user is asked to continue quiz , message : " + expectedtext); 
				}
				else
				{
					System.out.println(" - user is NOT asked to continue quiz , message : " + actualtext);
					Reporter.log("- user is NOT asked to continue quiz , message : " + actualtext); 
					AssertFailedCount++;
				}
				
				//Verifying whether 'Continue Quiz' button displayed or not
				
				if(SeleniumFunc.IsElementPresent("css", "#next_text a"))	
				{
					String actualbtext = SeleniumFunc.GetElementText("css", "#next_text a").trim();
					String expectedbtext = "Continue Quiz";
					
					if(actualbtext.contains(expectedbtext))
					{
						System.out.println(" - Continue Quiz button displayed ");
						Reporter.log("- Continue Quiz button displayed "); 
					}
					else
					{
						System.out.println(" - Continue Quiz button is not displayed ");
						Reporter.log(" - Continue Quiz button is not displayed "); 
						AssertFailedCount++;
					}
					
				}
				
				else
				{
					System.out.println(" - Continue Quiz button is not displayed ");
					Reporter.log(" - Continue Quiz button is not displayed "); 
					AssertFailedCount++;
				}
				
				
				
		System.out.println("Step 7 : Clicking on Continue Quiz button" );
		Reporter.log("Step 7 : Clicking on Continue Quiz button");
			
				SeleniumFunc.ClickOnElement("css", "#next_text a");
				Thread.sleep(4000);

				String actualtitle= SeleniumFunc.GetElementText("css", "div.page-header h1");
				String expectedtitle = "Question 2"; 
				
				System.out.println(actualtitle);
				if(actualtitle.equals(expectedtitle))
				{
					System.out.println("Success !! User is navigated to Quiz Question where he has left. ");
					Reporter.log("Success !! User is navigated to Quiz Question where he has left. ");
				}
				else
				{
					System.out.println("Failure !! User is NOT navigated to Quiz Question where he has left. ");
					Reporter.log("Failure !! User is NOT navigated to Quiz Question where he has left. ");
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
	 * Verify that on re-login, user is landed on Course list page with "Continue Where you left off" button
	*/ 
	@Test
	private void Dashboard_ReLogin() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify that on re-login, user is landed on Course list page with 'Continue Where you left off' button"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify that on re-login, user is landed on Course list page with 'Continue Where you left off' button"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ContentsPage content = new ContentsPage(driver);
		LoginPage login = new LoginPage(driver);
	
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "dashboard" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "dashboard" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			Thread.sleep(8000);
			
		System.out.println("Step 4 : Clicking on 'I Understand' and 'Next','Got it' buttons on Timer pop up ");
		Reporter.log("Step 4 : Clicking on 'I Understand' and 'Next', 'Got it' buttons on Timer pop up"); 
	
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}			
		
		System.out.println("Step 5 : On Unit page, waiting for timer to over and then clicking on Next Button");
		Reporter.log("Step 5 : On Unit page, waiting for timer to over and then clicking on Next Button"); 
			
			Thread.sleep(15000);
			
			SeleniumFunc.ClickOnElement("css", "#course_controls a ");
			
			Thread.sleep(5000);
			
		
		System.out.println("Step 6 : Logout and login again");
		Reporter.log("Step 6 : Logout and login again"); 	
		
			SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");
			SeleniumFunc.ToGoToUrl(Constants.LoginPage);
			Thread.sleep(4000);

			login.EnterUsername(username);
			login.EnterPassword(password);
			login.ClickOnLogInButton();
			Thread.sleep(4000);
					
		System.out.println("Step 7 : Navigating to Contents page and verifying whether Start Again link is displayed and on click to it, user is navigated to correct page");
		Reporter.log("Step 7 : Navigating to Contents page and verifying whether Start Again link is displayed and on click to it, user is navigated to correct page"); 
			
			content.ClickOnContentLink();
			Thread.sleep(4000);

			//Verifying whether link is present or not
			
			if(SeleniumFunc.IsElementPresent("css", "nav > ul > li.expanded > ul > li.expanded > ul > li:nth-child(2) > a"))
			{
				System.out.println("Success !! link is present");
				Reporter.log("Success !! link is present");
				
				SeleniumFunc.ClickOnElement("css", "nav > ul > li.expanded > ul > li.expanded > ul > li:nth-child(2) > a");
				Thread.sleep(4000);

				//Verifying whether user is navigated to correct page or not
				
				String ExpectedText = "Page 2 of 3";
				String ActualText= SeleniumFunc.GetElementText("css", "#course_controls > li.control-center > ul > li:nth-child(3) > b");
				
				if(ActualText.equals(ExpectedText))
				{
					System.out.println("Success !! User is navigated to correct page");
					Reporter.log("Success !! User is navigated to correct page ");

				}
				else
				{
					System.out.println("Failure !! User is navigated to incorrect page");
					Reporter.log("Failure !! User is navigated to incorrect page ");
					AssertFailedCount++;
				}
				
			}
			else
			{
				System.out.println("Failure !! link is NOT present");
				Reporter.log("Failure !! link is NOT present");
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
	
	
	
	/* Test 4: 
	 * Dashboard : Verify dashboard for the student who has finished the exam	 
	 * */ 
	@Test
	private void Dashboard_CompleteApplication() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Dashboard : Verify dashboard for the student who has finished the exam"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Dashboard : Verify dashboard for the student who has finished the exam"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		LoginPage login = new LoginPage(driver);
		ContentsPage content = new ContentsPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "dashboard" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "dashboard" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
		
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			
			Thread.sleep(8000);
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			header.Contents.click();
			Thread.sleep(4000);
	
			String ContentPageUrl = driver.getCurrentUrl();
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Fast Forward Pass");
			
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

			 
		System.out.println("Step 3 : Logout and login again");
		Reporter.log("Step 3 : Logout and login again"); 	
		
			SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");
			SeleniumFunc.ToGoToUrl(Constants.LoginPage);
			Thread.sleep(4000);

			login.EnterUsername(username);
			login.EnterPassword(password);
			login.ClickOnLogInButton();
			Thread.sleep(4000);

			
	
		System.out.println("Step 4 : Verifying whether user is asked to 'Complete Registration and Payment' on login " );
		Reporter.log("Step 4 : Verifying whether user is asked to 'Complete Registration and Payment' on login ");
		
			String expectedtext = "Congratulations, you have successfully completed the Alabama Boat Ed Course.";
			String actualtext = SeleniumFunc.GetElementText("css", "#congrats h3");
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Congratulation message is displayed correctly");
				Reporter.log("-- Success !! Congratulation message is displayed correctly");
			}
			else
			{
				System.out.println("-- Failure !! Congratulation message is incorrect. It is :;'" + actualtext+ "' instead of '" + expectedtext+ "'");
				Reporter.log("-- Failure !! Congratulation message is incorrect. It is :;'" + actualtext+ "' instead of '" + expectedtext+ "'");
				AssertFailedCount++;
			}
			
			
			expectedtext = "In order to receive your Proof of Home Study Voucher you must pay for your registration.";
			actualtext = SeleniumFunc.GetElementText("css", "#congrats p");
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! information message is displayed correctly");
				Reporter.log("-- Success !! information message is displayed correctly");
			}
			else
			{
				System.out.println("-- Failure !! information message is incorrect. It is :;'" + actualtext+ "' instead of '" + expectedtext+ "'");
				Reporter.log("-- Failure !! information message is incorrect. It is :;'" + actualtext+ "' instead of '" + expectedtext+ "'");
				AssertFailedCount++;
			}
			
			
			//Verifying presence of "Complete Your Registration and Payment" button
			expectedtext = "Complete Your Registration and Payment";
			actualtext = SeleniumFunc.GetElementText("css", "#congrats a");
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! 'Complete Your Registration and Payment' button displayed ");
				Reporter.log("-- Success !! 'Complete Your Registration and Payment' button displayed ");
			}
			else
			{
				System.out.println("-- Failure !! 'Complete Your Registration and Payment' button is displayed  but button text is : " + actualtext);
				Reporter.log("-- Failure !! 'Complete Your Registration and Payment' button is displayed  but button text is : " + actualtext);
				AssertFailedCount++;
			}
	
		System.out.println("Step 5 : Clicking on 'Complete Your Registration and Payment' button" );
		Reporter.log("Step 5 : Clicking on 'Complete Your Registration and Payment' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#congrats a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#congrats a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete Your Registration and Payment' button is working as expected ");
				Reporter.log("-- Success !! 'Complete Your Registration and Payment' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete Your Registration and Payment' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete Your Registration and Payment' button , user is navigated to : " + driver.getCurrentUrl());
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



	/* Test 5: 
	 * Verify dashboard for user returning after payment
	 * */ 
	@Test
	private void Dashboard_VerifyAfterPayment() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 :  Verify dashboard for user returning after payment"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 :  Verify dashboard for user returning after payment"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		CourseCompletePage coursecomplete = new CourseCompletePage(driver);
		LoginPage login = new LoginPage(driver);
		ContentsPage content = new ContentsPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "dashboard" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			System.out.println(emailaddress);
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
		
			Thread.sleep(8000);
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			header.Contents.click();
			Thread.sleep(4000);
	
			String ContentPageUrl = driver.getCurrentUrl();
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Fast Forward Pass");
			
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

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			cerinfo.Student_SSN.clear();
			cerinfo.Student_SSN.sendKeys("123-45-6789");
			
			//cerinfo.EnterMailingAddress("28261 N", "NY 10003", "New York", "Alabama", "11111", "United States of America", "234-567-8910");
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
	
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(5000);
		
			
			expectedurl = Constants.ApplicationURL + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
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
			new Select(payinfo.ExpirationYear).selectByVisibleText("2019");
			
			payinfo.BillingContactInfoCheckbox.click();
			payinfo.ConfirmPaymentInformation.click();
			
			Thread.sleep(4000);
			
			payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
			
			Thread.sleep(10000);
			
	
		System.out.println("Step 6 : Logout and login again , verifying dashboard");
		Reporter.log("Step 6 : Logout and login again , verifying dashboard"); 
			
			SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");
			SeleniumFunc.ToGoToUrl(Constants.LoginPage);
			Thread.sleep(4000);

			login.EnterUsername(username);
			login.EnterPassword(password);
			login.ClickOnLogInButton();
			
			Thread.sleep(5000);
		
			//Verifying Url
				String expectedtext = Constants.ApplicationURL + "/course/course_complete/";
				String actualtext = driver.getCurrentUrl();
				
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! user is navigated to Course Compelte page i.e. " + actualtext );
					Reporter.log("-- Success !! user is navigated to Course Compelte page i.e. " + actualtext );
					
					expectedtext = "You Have Completed the Alabama Boat Ed Course";
					actualtext = coursecomplete.PageHeader.getText().trim();
					
					if(actualtext.contains(expectedtext))
					{
						System.out.println("-- Success !! Course Completion message is correct i.e. " + actualtext );
						Reporter.log("-- Success !! Course Completion message is correct i.e. " + actualtext );
					}
					else
					{
						System.out.println("-- Failure !! Course Completion message is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
						Reporter.log("-- Failure !! Course Completion message is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
						AssertFailedCount++;
					}
					
				}
				else
				{
					System.out.println("-- Failure !! user is NOT navigated to Course Compelte page . Expected is : " + expectedtext + " , Actual is : " + actualtext );
					Reporter.log("-- Failure !!user is NOT navigated to Course Compelte page . Expected is : " + expectedtext + " , Actual is : " + actualtext);
					AssertFailedCount++;
				}
			
		
			//Verifying presence of 'Get Your Certificate' section
				expectedtext = "Get Your Certificate";
				actualtext = coursecomplete.GetYourCertificateText.getText().trim();
				
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! 'Get Your Certificate' section is present i.e. " + actualtext );
					Reporter.log("-- Success !! 'Get Your Certificate' section is present i.e. " + actualtext );
					
				}
				else
				{
					System.out.println("-- Failure !! 'Get Your Certificate' section is NOT present . Expected is : " + expectedtext + " , Actual is : " + actualtext );
					Reporter.log("-- Failure !! 'Get Your Certificate' section is NOT present . " + expectedtext + " , Actual is : " + actualtext);
					AssertFailedCount++;
				}
		
			//Verifying presence of 'After This Course' section
		
				expectedtext = "After This Quiz";
				actualtext = coursecomplete.AfterThisCourseText.getText().trim();
				
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! 'After This Course' section is present i.e. " + actualtext );
					Reporter.log("-- Success !! 'After This Course' section is present i.e. " + actualtext );
					
				}
				else
				{
					System.out.println("-- Failure !! 'After This Course' section is NOT present . Expected is : " + expectedtext + " , Actual is : " + actualtext );
					Reporter.log("-- Failure !! 'After This Course' section is NOT present . " + expectedtext + " , Actual is : " + actualtext);
					AssertFailedCount++;
				}
			
				
			//Verifying congratulation message
				
				expectedtext = "Congratulations, you have successfully completed the Alabama Boat Ed Course. You may now download your Alabama Proof of Home Study Voucher or have it sent to your registered e-mail address.";
				actualtext = coursecomplete.CongratulationsMessageText.getText().trim();
				
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! Congratulation message is correct i.e. " + actualtext );
					Reporter.log("-- Success !! Congratulation message is correct i.e. " + actualtext );
					
				}
				else
				{
					System.out.println("-- Failure !! Congratulation message is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
					Reporter.log("-- Failure !! Congratulation message is incorrect. " + expectedtext + " , Actual is : " + actualtext);
					AssertFailedCount++;
				}
		
		
			//Verifying presence of 'Download' button
				
				expectedtext = "Download the Alabama Proof of Home Study Voucher";
				actualtext = coursecomplete.Download.getText().trim();
				
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! Download button is present i.e. " + actualtext );
					Reporter.log("-- Success !! Download button is present i.e. " + actualtext );
					
				}
				else
				{
					System.out.println("-- Failure !! Download button is NOT present . Expected is : " + expectedtext + " , Actual is : " + actualtext );
					Reporter.log("-- Failure !! Download button is NOT present . " + expectedtext + " , Actual is : " + actualtext);
					AssertFailedCount++;
				}
		
			//Verifying presence of 'Email' button
				
				expectedtext = "E-mail the Alabama Proof of Home Study Voucher";
				actualtext = coursecomplete.Email.getText().trim();
				
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! Email button is present i.e. " + actualtext );
					Reporter.log("-- Success !! Email button is present i.e. " + actualtext );
					
				}
				else
				{
					System.out.println("-- Failure !! Email button is NOT present . Expected is : " + expectedtext + " , Actual is : " + actualtext );
					Reporter.log("-- Failure !! Email button is NOT present . " + expectedtext + " , Actual is : " + actualtext);
					AssertFailedCount++;
				}
				
				
			//Verifying presence of 'return to the course contents' links .
				
				expectedtext = "return to the course contents";
				actualtext = coursecomplete.ReturnToCourseContent.getText().trim();
				
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! 'return to the course contents' links is present i.e. " + actualtext );
					Reporter.log("-- Success !!'return to the course contents' links is present i.e. " + actualtext );
					
				}
				else
				{
					System.out.println("-- Failure !! 'return to the course contents' links is NOT present . Expected is : " + expectedtext + " , Actual is : " + actualtext );
					Reporter.log("-- Failure !! 'return to the course contents' links is NOT present . " + expectedtext + " , Actual is : " + actualtext);
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
