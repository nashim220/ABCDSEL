package courses.BoatEdAllStates;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.ContentsPage;
import pageFactory.Courses.FinalExamPage;
import pageFactory.Courses.KECourseAdminPage;
import pageFactory.Courses.LoginPage;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.PracticeExamPage;
import pageFactory.Courses.QuizPage;
import pageFactory.Courses.RegistrationPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class ExamAllStatesTest 
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
	 * Practice Exam : UI element verification for Practice Exam page
	*/ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void PracticeExam_UIElementVerification(String State) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Practice Exam : UI element verification for Practice Exam page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Practice Exam : UI element verification for Practice Exam page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		ContentsPage content = new ContentsPage(driver);
		QuizPage quiz = new QuizPage(driver);
		PracticeExamPage practiceexam = new PracticeExamPage(driver);
		
	
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "exams" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "exam" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); 
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
			Thread.sleep(6000);
			
			
			//Model Reminder
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{		
				register.ClickOnIUnderstandButton();	
				Thread.sleep(4000);
			}
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
					register.ClickOnStateResidencyCheckbox();
					Thread.sleep(4000);
			}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "5", "1990", emailaddress, emailaddress);
		
			Thread.sleep(10000);
			
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();

		System.out.println("Step 2 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Progress");
			keadmin.Progress_SelectAllUnitAndMarkAsComplete();
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			
		System.out.println("Step 3 : Marking all Units/Quiz as completed" );
		Reporter.log("Step 3 : Marking all Units/Quiz as completed");
					
			for(int i=1;i<7;i++)
			{
				content.ContinueWhereYouLeftOff.click();
				Thread.sleep(4000);
				
				quiz.ClickOnNextButton();
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(Constants.AdminURL);
				keadmin.StudentSearchAndActivity(username, "Boat", "Quizzes");
				keadmin.Quizzes_PassAQuiz(i);
				SeleniumFunc.ToGoToUrl(ContentPageUrl);
				Thread.sleep(4000);

			}
		
		System.out.println("Step 4 : Navigating to Practice Exam page" );
		Reporter.log("Step 4 : Navigating to Practice Exam page");	
			
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

		System.out.println("Step 5 : Verifying UI elements on Practice Exam page" );
		Reporter.log("Step 5 : Verifying UI elements on Practice Exam page");
		
			//verifying page header
			if(practiceexam.PageTitle.getText().trim().equals("Practice Exam"))
			{
				System.out.println("-- Page header is correct i.e. Practice Exam"  );
				Reporter.log("-- Page header is correct i.e. Practice Exam");
			}
			else
			{
				System.out.println("-- Page header is incorrect"  );
				Reporter.log("-- Page header is incorrect");
				AssertFailedCount++;
			}
			
			
			//verifying rules
			/*String expectedtext = "Before taking the Boating License Certification Exam, you should take a Practice Exam to gauge your knowledge of the material presented. You may take a Practice Exam as many times as you wish. If you prefer, you may skip to the Boating License Certification Exam." + "\n" + 
								  "60 questions (multiple choice)."+ "\n" + 
								  "80% to pass." +  "\n" + 
								  "Practice Exam is graded as soon as you finish." + "\n" + 
								  "After taking the Practice Exam, you will be given the option to see a critique of your exam. By clicking on a link to the critique, you can review the topics you missed. If you miss one or more questions, it is suggested that  you go back and study a topic again to better prepare yourself for the Boating License Certification Exam.";
			*/
			String expectedtext = "Before taking the Certification Exam, you should take a Practice Exam to gauge your knowledge of the material presented. You may take a Practice Exam as many times as you wish. If you prefer, you may skip to the Certification Exam.";
			
			/*String actaultext = practiceexam.ExamRuleTitle.getText().trim() + "\n" + 
								practiceexam.ExamRules.getText().trim();*/
			
			String actaultext = practiceexam.ExamRuleTitle.getText().trim() ; 
			
			/*System.out.println(expectedtext);
			System.out.println(actaultext);
			*/
			
			if(actaultext.contains(expectedtext))
			{
				System.out.println("-- Rules text matched"  );
				Reporter.log("Rules text matched");
			}
			else
			{
				System.out.println("-- Rules text doesn't match. Expected is: " + "\n" +  expectedtext + "\n" + "Actual is :" + "\n" + actaultext );
				Reporter.log("-- Rules text doesn't match. Expected is: " + "\n" +  expectedtext + "\n" + "Actual is :" + "\n" + actaultext );
				AssertFailedCount++;
			}
			
			//verifying 'Previous' button
			if(practiceexam.Previous.getText().trim().contains("Previous"))
			{
				System.out.println("-- 'Previous' button is present"  );
				Reporter.log("-- 'Previous' button is present");
			}
			else
			{
				System.out.println("-- 'Previous' button is not present"  );
				Reporter.log("-- 'Previous' button is not present ");
				AssertFailedCount++;
			}
			
			//verifying 'Go to the Boating License Certification Exam' button
			if(practiceexam.FinalExam.getText().trim().contains("Go to the Boating License Certification Exam"))
			{
				System.out.println("-- 'Go to the Boating License Certification Exam' button is present"  );
				Reporter.log("-- 'Go to the Boating License Certification Exam' button is present");
			}
			else
			{
				System.out.println("-- 'Go to the Boating License Certification Exam' button is not present"  );
				Reporter.log("-- 'Go to the Boating License Certification Exam' button is not present ");
				AssertFailedCount++;
			}
			
			
			//verifying 'Take the Practice Exam' button
			if(practiceexam.TakePracticeExam.getText().trim().contains("Take the Practice Exam"))
			{
				System.out.println("-- 'Take the Practice Exam' button is present"  );
				Reporter.log("-- 'Take the Practice Examm' button is present");
			}
			else
			{
				System.out.println("-- 'Take the Practice Exam' button is not present"  );
				Reporter.log("-- 'Take the Practice Exam' button is not present ");
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
	 * Practice Exam : Verify all buttons works as expected i.e. 'Previous', 'Go to Final Exam' , 'Take a Practice Exam', 'Quit Practice'
	*/ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void PracticeExam_UI_Buttons(String State) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Practice Exam : Verify all buttons works as expected i.e. 'Previous', 'Go to Final Exam' , 'Take a Practice Exam', 'Quit Practice'"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Practice Exam : Verify all buttons works as expected i.e. 'Previous', 'Go to Final Exam' , 'Take a Practice Exam', 'Quit Practice'"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		ContentsPage content = new ContentsPage(driver);
		QuizPage quiz = new QuizPage(driver);
		PracticeExamPage practiceexam = new PracticeExamPage(driver);
		/*LoginPage login = new LoginPage(driver);*/
		
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "exams" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "exam" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); 
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
			Thread.sleep(6000);
			
			//Model Reminder
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{		
				register.ClickOnIUnderstandButton();	
				Thread.sleep(4000);
			}
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
					register.ClickOnStateResidencyCheckbox();
					Thread.sleep(4000);
			}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "5", "1990", emailaddress, emailaddress);
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Progress");
			keadmin.Progress_SelectAllUnitAndMarkAsComplete();
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			
				
		System.out.println("Step 3 : Marking all Units/Quiz as completed" );
		Reporter.log("Step 3 : Marking all Units/Quiz as completed");
					
			for(int i=1;i<7;i++)
			{
				content.ContinueWhereYouLeftOff.click();
				Thread.sleep(4000);

				quiz.ClickOnNextButton();
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(Constants.AdminURL);
				keadmin.StudentSearchAndActivity(username, "Boat", "Quizzes");
				keadmin.Quizzes_PassAQuiz(i);
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(ContentPageUrl);
				Thread.sleep(4000);

			}
		
		
		/*	SeleniumFunc.ToGoToUrl(Constants.LoginPage);
			login.EnterUsername("contents805820857");
			login.EnterPassword("clarion@123");
			login.ClickOnLogInButton();*/
			
		System.out.println("Step 4 : Navigating to Practice Exam page" );
		Reporter.log("Step 4 : Navigating to Practice Exam page");	
			
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);
			
		System.out.println("Step 5 : On Pracice Exam page, clicking 'Previous' button" );
		Reporter.log("Step 5 : On Pracice Exam page, clicking 'Previous' button");
		
			String expectedurl = practiceexam.Previous.getAttribute("href")+"/";
			
			practiceexam.Previous.click();
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			//verifying url
			if(actualurl.equals(expectedurl))
			{
				System.out.println("-- On click to Previous button , user is navigated to previous page"  );
				Reporter.log("-- On click to Previous button , user is navigated to previous page");
			}
			else
			{
				System.out.println("-- On click to Previous button , user is NOT navigated to previous page and navigated to :" + driver.getCurrentUrl()  );
				Reporter.log("-- On click to Previous button , user is NOT navigated to previous page and navigated to :" + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 5 : On Pracice Exam page, clicking 'Take Practical Exam' button" );
		Reporter.log("Step 5 : On Pracice Exam page, clicking 'Take Practical Exam' button");
		
			driver.navigate().back();
			Thread.sleep(4000);

			practiceexam.TakePracticeExam.click();
			Thread.sleep(4000);

			String actualtext = SeleniumFunc.GetElementText("css", "ul.breadcrumb");
			String expectedtext = "Practice Exam / Question 1 of 60";
					
			//verifying url
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- On click to Take Practical Exam button , user is navigated to Question 1 page"  );
				Reporter.log("-- On click to Take Practical Exam button , user is navigated to Question 1 page");
			}
			else
			{
				System.out.println("-- On click to Take Practical Exam button , user is navigated to Question 1 page and navigated to : " + driver.getCurrentUrl()  );
				Reporter.log("-- On click to Take Practical Exam button , user is navigated to Question 1 page and navigated to :" + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 6 : Clicking on 'Quit Practice' button & dismissing alert displayed" );
		Reporter.log("Step 6 : Clicking on 'Quit Practice' button & dismissing alert displayed");
		
			
			practiceexam.QuitPractice.click();
			Thread.sleep(4000);

			try
			{
				String expectedalerttext = "Are you sure you want to quit the Practice Exam?";
				String actualalerttext = SeleniumFunc.DismisAlertAndGetText();
				
				if(actualalerttext.equals(expectedalerttext))
				{
					System.out.println("-- Alert text is correct" );
					Reporter.log("-- Alert text is correct");
				}
				else
				{
					System.out.println("-- Alert text is incorrect" );
					Reporter.log("-- Alert text is incorrect");
					AssertFailedCount++;
				}
			}
			catch(Exception e)
			{
				System.out.println("-- Alert not displayed" );
				Reporter.log("-- Alert not displayed");
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 7 : Clicking on 'Quit Practice' button & accepting alert displayed" );
		Reporter.log("Step 6 : Clicking on 'Quit Practice' button & accepting alert displayed");
		
			
			practiceexam.QuitPractice.click();
			Thread.sleep(4000);

			try
			{
				String expectedalerttext = "Are you sure you want to quit the Practice Exam?";
				String actualalerttext = SeleniumFunc.AcceptAlertAndGetText();
				
				if(actualalerttext.equals(expectedalerttext))
				{
					System.out.println("-- Alert text is correct" );
					Reporter.log("-- Alert text is correct");
				}
				else
				{
					System.out.println("-- Alert text is incorrect" );
					Reporter.log("-- Alert text is incorrect");
					AssertFailedCount++;
				}
			}
			catch(Exception e)
			{
				System.out.println("-- Alert not displayed" );
				Reporter.log("-- Alert not displayed");
				AssertFailedCount++;
			}
		
			
			//verifying page header
			if(practiceexam.PageTitle.getText().trim().equals("Practice Exam"))
			{
				System.out.println("-- User is navigated to Practice Exam"  );
				Reporter.log("-- User is navigated to Practice Exam");
			}
			else
			{
				System.out.println("-- User is NOT navigated to Practice Exam and navigated to : " + driver.getCurrentUrl()  );
				Reporter.log("-- User is NOT navigated to Practice Exam and navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
	
			
		System.out.println("Step 8 : Clicking on 'Final Exam' button " );
		Reporter.log("Step 8 : Clicking on 'Final Exam' button");
		
			practiceexam.FinalExam.click();
			Thread.sleep(4000);

			//verifying page header
			if(driver.getCurrentUrl().contains("https://qa1.boat-ed.com/exam/final_exam/"))
			{
				System.out.println("-- User is navigated to Final Exam"  );
				Reporter.log("-- User is navigated to Final Exam");
			}
			else
			{
				System.out.println("-- User is NOT navigated to Final Exam and navigated to : " + driver.getCurrentUrl()  );
				Reporter.log("-- User is NOT navigated to Final Exam and navigated to : " + driver.getCurrentUrl());
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
	 * Practical Exam:  Verify that user is asked to complete exam if logout in middle of taking exam
	 * */ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void PracticeExam_ResumeExam(String State) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Practical Exam:  Verify that user is asked to complete exam if logout in middle of taking exam "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Practical Exam:  Verify that user is asked to complete exam if logout in middle of taking exam"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		ContentsPage content = new ContentsPage(driver);
		QuizPage quiz = new QuizPage(driver);
		PracticeExamPage practiceexam = new PracticeExamPage(driver);
		LoginPage login = new LoginPage(driver);
		
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "exams" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "exam" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); 	
			Thread.sleep(4000);
		
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" +  State);
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
			Thread.sleep(8000);

			//For few States , closing modal dialog present on Registration page
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{	
				register.ClickOnIUnderstandButton();		
				Thread.sleep(4000);
			}
			
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
					register.ClickOnStateResidencyCheckbox();
					Thread.sleep(4000);
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
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Progress");
			keadmin.Progress_SelectAllUnitAndMarkAsComplete();
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			
				
		System.out.println("Step 3 : Marking all Units/Quiz as completed" );
		Reporter.log("Step 3 : Marking all Units/Quiz as completed");
					
			for(int i=1;i<7;i++)
			{
				content.ContinueWhereYouLeftOff.click();
				Thread.sleep(4000);

				quiz.ClickOnNextButton();
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(Constants.AdminURL);
				keadmin.StudentSearchAndActivity(username, "Boat", "Quizzes");
				keadmin.Quizzes_PassAQuiz(i);
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(ContentPageUrl);
				Thread.sleep(4000);

			}
		
	
		System.out.println("Step 4 : Navigating to Practice Exam page" );
		Reporter.log("Step 4 : Navigating to Practice Exam page");	
			
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);
			
		System.out.println("Step 4 : On Pracice Exam page, clicking 'Take Practical Exam' button" );
		Reporter.log("Step 4 : On Pracice Exam page, clicking 'Take Practical Exam' button");
		
			practiceexam.TakePracticeExam.click();
			Thread.sleep(4000);
			
		System.out.println("Step 5 : Log out and login again to application" );
		Reporter.log("Step 5 : Log out and login again to application");
			
			SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");

			SeleniumFunc.ToGoToUrl(Constants.LoginPage);
			login.EnterUsername(username);
			login.EnterPassword("clarion@123");
			login.ClickOnLogInButton();
			Thread.sleep(4000);

			
			//verifying page header
			if(practiceexam.PageTitle.getText().trim().equals("Practice Exam") )
			{
				System.out.println("-- User is navigated to Practice Exam"  );
				Reporter.log("-- User is navigated to Practice Exam");
			}
			else
			{
				System.out.println("-- User is NOT navigated to Practice Exam and navigated to : " + driver.getCurrentUrl()  );
				Reporter.log("-- User is NOT navigated to Practice Exam and navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			//Verifying text
			String expectedtext = "You are currently in the middle of the Practice Exam" + "\n" +
									"Once started, you must finish or click \"Quit Practice Exam\" below before you can return to the course material. Otherwise, please continue the Practice Exam.";
			
			String actualtext = practiceexam.ResumeExamPageText.getText().trim();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- User is navigated to Practice Exam and asked to resume exam"  );
				Reporter.log("-- User is navigated to Practice Exam and asked to resume exam");
			}
			else
			{
				System.out.println("-- Text incorrect. Expected is : " + expectedtext + "  - Actual is : " +  actualtext);
				Reporter.log("-- Text incorrect. Expected is : " + expectedtext + "  - Actual is : " +  actualtext);
				AssertFailedCount++;
			}
			
			
			//Verifying Quit Practice Exam
			expectedtext = "Quit Practice Exam";
			
			if(practiceexam.Previous.getText().trim().contains(expectedtext))
			{
				System.out.println("-- 'Quit Practice Exam' button is present"  );
				Reporter.log("-- 'Quit Practice Exam' button is present");
			}
			else
			{
				System.out.println("-- 'Quit Practice Exam' button is NOT present" );
				Reporter.log("-- 'Quit Practice Exam' button is NOT present");
				AssertFailedCount++;
			}
			
			
			//Verifying Continue the Practice Exam
			expectedtext = "Continue the Practice Exam";
			
			if(practiceexam.Next.getText().trim().contains(expectedtext))
			{
				System.out.println("-- 'Continue the Practice Exam' button is present"  );
				Reporter.log("-- 'Continue the Practice Exam' button is present");
			}
			else
			{
				System.out.println("-- 'Continue the Practice Exam' button is NOT present" );
				Reporter.log("-- 'Continue the Practice Exam' button is NOT present");
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 6 : On Pracice Exam page, clicking 'Continue the Practice Exam'' button" );
		Reporter.log("Step 5 : On Pracice Exam page, clicking 'Continue the Practice Exam'' button");
		
			
			practiceexam.TakePracticeExam.click();
		
			actualtext = SeleniumFunc.GetElementText("css", "ul.breadcrumb");
			expectedtext = "Practice Exam / Question 1 of 60";
					
			//verifying url
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- On click to Continue the Practice Exam button , user is navigated to Question 1 page"  );
				Reporter.log("-- On click to Continue the Practice Exam button , user is navigated to Question 1 page");
			}
			else
			{
				System.out.println("-- On click to Continue the Practice Exam button , user is navigated to Question 1 page and navigated to : " + driver.getCurrentUrl()  );
				Reporter.log("-- On click to Continue the Practice Exam button , user is navigated to Question 1 page and navigated to :" + driver.getCurrentUrl());
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
	 * Practice Exam : Verify Quit Practice button functionality
	 * */ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void PracticeExam_QuitExam(String State) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Practice Exam : Verify Quit Practice button functionality"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Practice Exam : Verify Quit Practice button functionality"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		ContentsPage content = new ContentsPage(driver);
		QuizPage quiz = new QuizPage(driver);
		PracticeExamPage practiceexam = new PracticeExamPage(driver);
		LoginPage login = new LoginPage(driver);
		
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "exams" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "exam" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
		
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
			Thread.sleep(8000);

			//For few States , closing modal dialog present on Registration page
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{	
				register.ClickOnIUnderstandButton();		
				Thread.sleep(4000);
			}
			
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
					register.ClickOnStateResidencyCheckbox();
					Thread.sleep(4000);
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
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Progress");
			keadmin.Progress_SelectAllUnitAndMarkAsComplete();
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			
		System.out.println("Step 3 : Marking all Units/Quiz as completed" );
		Reporter.log("Step 3 : Marking all Units/Quiz as completed");
					
			for(int i=1;i<7;i++)
			{
				content.ContinueWhereYouLeftOff.click();
				Thread.sleep(4000);

				quiz.ClickOnNextButton();
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(Constants.AdminURL);
				keadmin.StudentSearchAndActivity(username, "Boat", "Quizzes");
				keadmin.Quizzes_PassAQuiz(i);
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(ContentPageUrl);
				Thread.sleep(4000);

			}
	
		System.out.println("Step 4 : Navigating to Practice Exam page" );
		Reporter.log("Step 4 : Navigating to Practice Exam page");	
			
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);
			
		System.out.println("Step 4 : On Pracice Exam page, clicking 'Take Practical Exam' button" );
		Reporter.log("Step 4 : On Pracice Exam page, clicking 'Take Practical Exam' button");
		
			practiceexam.TakePracticeExam.click();
			Thread.sleep(4000);

		System.out.println("Step 5 : Log out and login again to application" );
		Reporter.log("Step 5 : Log out and login again to application");
			
			SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");
			Thread.sleep(4000);

			SeleniumFunc.ToGoToUrl(Constants.LoginPage);
			login.EnterUsername(username);
			login.EnterPassword("clarion@123");
			login.ClickOnLogInButton();
			Thread.sleep(4000);

	
		System.out.println("Step 6 : Clicking on 'Quit Practice' button & dismissing alert displayed" );
		Reporter.log("Step 6 : Clicking on 'Quit Practice' button & dismissing alert displayed");
					
			practiceexam.QuitPractice.click();
			Thread.sleep(4000);

			try
			{
				String expectedalerttext = "Are you sure you want to quit the Practice Exam?";
				String actualalerttext = SeleniumFunc.DismisAlertAndGetText();
				
				if(actualalerttext.equals(expectedalerttext))
				{
					System.out.println("-- Alert text is correct" );
					Reporter.log("-- Alert text is correct");
				}
				else
				{
					System.out.println("-- Alert text is incorrect" );
					Reporter.log("-- Alert text is incorrect");
					AssertFailedCount++;
				}
			}
			catch(Exception e)
			{
				System.out.println("-- Alert not displayed" );
				Reporter.log("-- Alert not displayed");
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 7 : Clicking on 'Quit Practice' button & accepting alert displayed" );
		Reporter.log("Step 6 : Clicking on 'Quit Practice' button & accepting alert displayed");
		
			
			practiceexam.QuitPractice.click();
			Thread.sleep(4000);

			try
			{
				String expectedalerttext = "Are you sure you want to quit the Practice Exam?";
				String actualalerttext = SeleniumFunc.AcceptAlertAndGetText();
				
				if(actualalerttext.equals(expectedalerttext))
				{
					System.out.println("-- Alert text is correct" );
					Reporter.log("-- Alert text is correct");
				}
				else
				{
					System.out.println("-- Alert text is incorrect" );
					Reporter.log("-- Alert text is incorrect");
					AssertFailedCount++;
				}
			}
			catch(Exception e)
			{
				System.out.println("-- Alert not displayed" );
				Reporter.log("-- Alert not displayed");
				AssertFailedCount++;
			}
		
			
			//verifying page header
			if(practiceexam.PageTitle.getText().trim().equals("Practice Exam"))
			{
				System.out.println("-- User is navigated to Practice Exam"  );
				Reporter.log("-- User is navigated to Practice Exam");
			}
			else
			{
				System.out.println("-- User is NOT navigated to Practice Exam and navigated to : " + driver.getCurrentUrl()  );
				Reporter.log("-- User is NOT navigated to Practice Exam and navigated to : " + driver.getCurrentUrl());
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
	 * Final Exam : UI element verification for Final Exam page
	 * */ 
	@Test
	private void FinalExam_UIElementVerification(String State) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Final Exam : UI element verification for Final Exam page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 :Final Exam : UI element verification for Final Exam page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		ContentsPage content = new ContentsPage(driver);
		QuizPage quiz = new QuizPage(driver);
		PracticeExamPage practiceexam = new PracticeExamPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "exams" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "exam" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver);  Thread.sleep(4000);
		
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
			Thread.sleep(8000);

			//For few States , closing modal dialog present on Registration page
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{	
				register.ClickOnIUnderstandButton();		
				Thread.sleep(4000);
			}
			
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
					register.ClickOnStateResidencyCheckbox();
					Thread.sleep(4000);
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

		System.out.println("Step 2 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Progress");
			keadmin.Progress_SelectAllUnitAndMarkAsComplete();
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);
				
		System.out.println("Step 3 : Marking all Units/Quiz as completed" );
		Reporter.log("Step 3 : Marking all Units/Quiz as completed");
					
			for(int i=1;i<7;i++)
			{
				content.ContinueWhereYouLeftOff.click();
				Thread.sleep(4000);

				quiz.ClickOnNextButton();
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(Constants.AdminURL);
				keadmin.StudentSearchAndActivity(username, "Boat", "Quizzes");
				keadmin.Quizzes_PassAQuiz(i);
				SeleniumFunc.ToGoToUrl(ContentPageUrl);
			}
		

		
		System.out.println("Step 4 : Navigating to Fianl Exam page" );
		Reporter.log("Step 4 : Navigating to FianlExam page");	
			
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

			practiceexam.FinalExam.click();
			Thread.sleep(4000);

			
		System.out.println("Step 5 : Verifying UI elements on Final Exam page" );
		Reporter.log("Step 5 : Verifying UI elements on Final Exam page");
		
			//verifying page header
			if(practiceexam.PageTitle.getText().trim().equals("Boating License Certification Exam"))
			{
				System.out.println("-- Page header is correct i.e. Boating License Certification Exam"  );
				Reporter.log("-- Page header is correct i.e. Boating License Certification Exam");
			}
			else
			{
				System.out.println("-- Page header is incorrect"  );
				Reporter.log("-- Page header is incorrect");
				AssertFailedCount++;
			}
			
		
			//verifying rules
			String expectedtext = "Before taking the Boating License Certification Exam, make sure you have reviewed all of the course material." + "\n" + 
								  "60 questions (multiple choice)."+ "\n" + 
								  "Choose the best of the four possible answers for each multiple-choice question." +  "\n" + 
								  "80% to pass" + "\n" + 
								  "Exam is graded as soon as you finish. If you do not pass, you will be required to take a new exam." + "\n" + 
								  "Pay for course AFTER you pass. No pass? No pay!";
			
			
			String actaultext = finalexam.ExamRuleTitle.getText().trim() + "\n" + 
								SeleniumFunc.GetElementText("css", "div[class='span8 offset2'] ul li:nth-of-type(1)").trim() + "\n" + 
								SeleniumFunc.GetElementText("css", "div[class='span8 offset2'] ul li:nth-of-type(2)").trim() + "\n" + 
								SeleniumFunc.GetElementText("css", "div[class='span8 offset2'] ul li:nth-of-type(3)").trim()  + "\n" + 
								SeleniumFunc.GetElementText("css", "div[class='span8 offset2'] ul li:nth-of-type(4)").trim()  + "\n" + 
								SeleniumFunc.GetElementText("css", "div[class='span8 offset2'] ul li:nth-of-type(5)").trim();
			
			System.out.println(expectedtext.length());
			System.out.println(actaultext.length());
			
			if(actaultext.equals(expectedtext))
			{
				System.out.println("-- Rules text matched"  );
				Reporter.log("Rules text matched");
			}
			else
			{
				System.out.println("-- Rules text doesn't match. Expected is: " + "\n" +  expectedtext + "\n" + "Actual is :" + "\n" + actaultext );
				Reporter.log("-- Rules text doesn't match. Expected is: " + "\n" +  expectedtext + "\n" + "Actual is :" + "\n" + actaultext );
				AssertFailedCount++;
			}
			
			//verifying 'Previous' button
			if(finalexam.Previous.getText().trim().contains("Previous"))
			{
				System.out.println("-- 'Previous' button is present"  );
				Reporter.log("-- 'Previous' button is present");
			}
			else
			{
				System.out.println("-- 'Previous' button is not present"  );
				Reporter.log("-- 'Previous' button is not present ");
				AssertFailedCount++;
			}
			
			//verifying 'Begin the Boating License Certification Exam' button
			if(finalexam.FinalExam.getText().trim().contains("Begin the Boating License Certification Exam"))
			{
				System.out.println("-- 'Begin the Boating License Certification Exam' button is present"  );
				Reporter.log("-- 'Begin the Boating License Certification Exam' button is present");
			}
			else
			{
				System.out.println("-- 'Begin the Boating License Certification Exam' button is not present"  );
				Reporter.log("-- 'Begin the Boating License Certification Exam' button is not present ");
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

	
	
	
	/* Test 6: 
	 * Final Exam :  Verify all buttons works as expected i.e. 'Previous', 'Begin Final Exam' 
	 * */ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void FinalExam_UI_Buttons(String State) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 :  Final Exam :  Verify all buttons works as expected i.e. 'Previous', 'Begin Final Exam' "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Final Exam :  Verify all buttons works as expected i.e. 'Previous', 'Begin Final Exam' "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		ContentsPage content = new ContentsPage(driver);
		QuizPage quiz = new QuizPage(driver);
		PracticeExamPage practiceexam = new PracticeExamPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "exams" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "exam" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
		
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
			Thread.sleep(8000);

			//For few States , closing modal dialog present on Registration page
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{	
				register.ClickOnIUnderstandButton();		
				Thread.sleep(4000);
			}
			
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
					register.ClickOnStateResidencyCheckbox();
					Thread.sleep(4000);
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
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Progress");
			keadmin.Progress_SelectAllUnitAndMarkAsComplete();
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			
				
		System.out.println("Step 3 : Marking all Units/Quiz as completed" );
		Reporter.log("Step 3 : Marking all Units/Quiz as completed");
					
			for(int i=1;i<7;i++)
			{
				content.ContinueWhereYouLeftOff.click();
				Thread.sleep(4000);

				quiz.ClickOnNextButton();
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(Constants.AdminURL);
				keadmin.StudentSearchAndActivity(username, "Boat", "Quizzes");
				keadmin.Quizzes_PassAQuiz(i);
				SeleniumFunc.ToGoToUrl(ContentPageUrl);
			}
		
	
		System.out.println("Step 4 : Navigating to Fianl Exam page" );
		Reporter.log("Step 4 : Navigating to FianlExam page");	
			
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

			practiceexam.FinalExam.click();
			Thread.sleep(4000);
			
		System.out.println("Step 5 : On Final Exam page, clicking 'Previous' button" );
		Reporter.log("Step 5 : On Final Exam page, clicking 'Previous' button");
		
			String expectedurl = finalexam.Previous.getAttribute("href")+"/";
			
			finalexam.Previous.click();
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			//verifying url
			if(actualurl.equals(expectedurl))
			{
				System.out.println("-- On click to Previous button , user is navigated to previous page"  );
				Reporter.log("-- On click to Previous button , user is navigated to previous page");
			}
			else
			{
				System.out.println("-- On click to Previous button , user is NOT navigated to previous page and navigated to :" + driver.getCurrentUrl()  );
				Reporter.log("-- On click to Previous button , user is NOT navigated to previous page and navigated to :" + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 6 : On Final Exam page, clicking 'Begin Final Exam' button" );
		Reporter.log("Step 6 : On Final Exam page, clicking 'Begin Final Exam' button");
		
			driver.navigate().back();
			Thread.sleep(4000);

			finalexam.FinalExam.click();
			Thread.sleep(4000);

			String actualtext = SeleniumFunc.GetElementText("css", "ul.breadcrumb");
			String expectedtext = "Question 1 of 100";
					
			//verifying url
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- On click to Begin Final Exam button , user is navigated to Question 1 page"  );
				Reporter.log("-- On click to Begin Final Exam button , user is navigated to Question 1 page");
			}
			else
			{
				System.out.println("-- On click to Begin Final Exam Exam button , user is navigated to Question 1 page and navigated to : " + driver.getCurrentUrl()  );
				Reporter.log("-- On click to Begin Final Exam  Exam button , user is navigated to Question 1 page and navigated to :" + driver.getCurrentUrl());
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
	
	
	
	
	/* Test 7: 
	 * Final Exam : To verify UI of final exam question page - Alert pop up if user doesn't select any answer and click on Next button
	 * */ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void FinalExam_QuestionPage_UIVerification_1(String State) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Final Exam : To verify UI of final exam question page - Alert pop up if user doesn't select any answer and click on Next button "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Final Exam : To verify UI of final exam question page -Alert pop up if user doesn't select any answer and click on Next button"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		ContentsPage content = new ContentsPage(driver);
		QuizPage quiz = new QuizPage(driver);
		PracticeExamPage practiceexam = new PracticeExamPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
	

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "exams" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "exam" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
		
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
			Thread.sleep(8000);

			//For few States , closing modal dialog present on Registration page
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{	
				register.ClickOnIUnderstandButton();		
				Thread.sleep(4000);
			}
			
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
					register.ClickOnStateResidencyCheckbox();
					Thread.sleep(4000);
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
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Progress");
			keadmin.Progress_SelectAllUnitAndMarkAsComplete();
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			
				
		System.out.println("Step 3 : Marking all Units/Quiz as completed" );
		Reporter.log("Step 3 : Marking all Units/Quiz as completed");
					
			for(int i=1;i<7;i++)
			{
				content.ContinueWhereYouLeftOff.click();
				Thread.sleep(4000);

				quiz.ClickOnNextButton();
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(Constants.AdminURL);
				keadmin.StudentSearchAndActivity(username, "Boat", "Quizzes");
				keadmin.Quizzes_PassAQuiz(i);
				SeleniumFunc.ToGoToUrl(ContentPageUrl);
			}
		
	
		System.out.println("Step 4 : Navigating to Fianl Exam page" );
		Reporter.log("Step 4 : Navigating to FianlExam page");	
			
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

			practiceexam.FinalExam.click();
			Thread.sleep(4000);

			finalexam.FinalExam.click();
			Thread.sleep(4000);
		
		System.out.println("Step 5 : On Final exam page , Clicking on Next button without selecting any answer" );
		Reporter.log("Step 5 : On Final exam page , Clicking on Next button without selecting any answer");
			
			SeleniumFunc.ClickOnElement("id", "submit");
			Thread.sleep(4000);
			
			if(SeleniumFunc.IsElementPresent("css", "div [class='modal hide fade in']"))
			{
				System.out.println("Success !! Alert pop is displayed");
				Reporter.log("Success !! Alert pop is displayed");
				
				Thread.sleep(4000);
				
				String ExpectedText = "Please Select an Answer" + "\n" + "You did not select an answer to this question. Please select one before continuing.";
				String ActualText = SeleniumFunc.GetElementText("css", "div.modal-header h2") + "\n" +
						SeleniumFunc.GetElementText("css", "div.modal-body p") ;
				
				if(ActualText.equals(ExpectedText))
				{
					System.out.println("Success !! Alert pop has correct text");
					Reporter.log("Success !! Alert pop has correct text");
				}
				else
				{
					System.out.println("Failure !! Alert pop has incorrect text");
					Reporter.log("Failure !! Alert pop has incorrect text");
					AssertFailedCount++;
				}
			}
		
			
		System.out.println("Step 6 : Clicking on OK button on pop up" );
		Reporter.log("Step 6 : Clicking on OK button on pop up");
			
			SeleniumFunc.ClickOnElement("css", "div.modal-footer a");
			Thread.sleep(4000);

			if(SeleniumFunc.IsElementPresent("css", "div [class='modal hide fade in']"))
			{
				System.out.println("Failure !! Alert pop is not closed");
				Reporter.log("Failure !! Alert pop is not closed");
				AssertFailedCount++;
			}
			else
			{
				System.out.println("Success !! Alert pop is closed");
				Reporter.log("Success !! Alert pop is closed");
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
	 * Final Exam : To verify UI of final exam question page - User can give answers and each page has correct Titles
	 * */ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void FinalExam_QuestionPage_UIVerification_2(String State) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Final Exam : To verify UI of final exam question page - User can give answers and each page has correct Titles"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Final Exam : To verify UI of final exam question page - User can give answers and each page has correct Titles"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		ContentsPage content = new ContentsPage(driver);
		QuizPage quiz = new QuizPage(driver);
		PracticeExamPage practiceexam = new PracticeExamPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "exams" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "exam" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
		
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
			Thread.sleep(8000);

			//For few States , closing modal dialog present on Registration page
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{	
				register.ClickOnIUnderstandButton();		
				Thread.sleep(4000);
			}
			
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
					register.ClickOnStateResidencyCheckbox();
					Thread.sleep(4000);
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

		System.out.println("Step 2 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Progress");
			keadmin.Progress_SelectAllUnitAndMarkAsComplete();
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			
				
		System.out.println("Step 3 : Marking all Units/Quiz as completed" );
		Reporter.log("Step 3 : Marking all Units/Quiz as completed");
					
			for(int i=1;i<7;i++)
			{
				content.ContinueWhereYouLeftOff.click();
				Thread.sleep(4000);

				quiz.ClickOnNextButton();
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(Constants.AdminURL);
				keadmin.StudentSearchAndActivity(username, "Boat", "Quizzes");
				keadmin.Quizzes_PassAQuiz(i);
				SeleniumFunc.ToGoToUrl(ContentPageUrl);
			}
			
		System.out.println("Step 4 : Navigating to Fianl Exam page" );
		Reporter.log("Step 4 : Navigating to FianlExam page");	
			
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

			practiceexam.FinalExam.click();
			Thread.sleep(4000);

			finalexam.FinalExam.click();
			Thread.sleep(4000);

			
		System.out.println("Step 5 : On Final exam question page , verifying text /selecting answer and moving to next questions" );
		Reporter.log("Step 5 : On Final exam question page , verifying text /selecting answer and moving to next questions");
			 						
			for(int i=0;i<59;i++)
			{
				
				String actualtitle= SeleniumFunc.GetElementText("css", "div.page-header h1").trim();;
				String expectedtitle = "Question " + (i+1); 
				
				if(actualtitle.equals(expectedtitle))
				{
					System.out.println("Success !! Question page has correct text");
					Reporter.log("Success !! Question page has correct text");
				}
				else
				{
					System.out.println("Failure !! Question page has incorrect text");
					Reporter.log("Failure !! Question page has incorrect text");
					AssertFailedCount++;
				}
			
				//Selecting answer
				finalexam.Answer1.click();
				Thread.sleep(4000);

				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
				Thread.sleep(4000);

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
	 * Final Exam:  Verify that user is asked to complete exam if logout in middle of taking exam
	 * */ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void FinalExam_ResumeExam(String State) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Final Exam:  Verify that user is asked to complete exam if logout in middle of taking exam"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 : Final Exam:  Verify that user is asked to complete exam if logout in middle of taking exam"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		ContentsPage content = new ContentsPage(driver);
		QuizPage quiz = new QuizPage(driver);
		PracticeExamPage practiceexam = new PracticeExamPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		LoginPage login = new LoginPage(driver);
		
	
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "exams" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "exam" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
		
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
			Thread.sleep(8000);

			//For few States , closing modal dialog present on Registration page
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{	
				register.ClickOnIUnderstandButton();		
				Thread.sleep(4000);
			}
			
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
					register.ClickOnStateResidencyCheckbox();
					Thread.sleep(4000);
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
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Progress");
			keadmin.Progress_SelectAllUnitAndMarkAsComplete();
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			
				
		System.out.println("Step 3 : Marking all Units/Quiz as completed" );
		Reporter.log("Step 3 : Marking all Units/Quiz as completed");
					
			for(int i=1;i<7;i++)
			{
				content.ContinueWhereYouLeftOff.click();
				Thread.sleep(4000);

				quiz.ClickOnNextButton();
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(Constants.AdminURL);
				keadmin.StudentSearchAndActivity(username, "Boat", "Quizzes");
				keadmin.Quizzes_PassAQuiz(i);
				SeleniumFunc.ToGoToUrl(ContentPageUrl);
			}
			
		System.out.println("Step 4 : Navigating to Fianl Exam page" );
		Reporter.log("Step 4 : Navigating to FianlExam page");	
			
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

			practiceexam.FinalExam.click();
			Thread.sleep(4000);

			finalexam.FinalExam.click();
			Thread.sleep(4000);
			
		System.out.println("Step 5 : On Final exam question page ,selecting answer and moving to next questions" );
		Reporter.log("Step 5 : On Final exam question page , selecting answer and moving to next questions");
			 
				//Selecting answer
				finalexam.Answer1.click();
				Thread.sleep(4000);

				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
				Thread.sleep(4000);
		
		System.out.println("Step 6 : Log out and login again to application");
		Reporter.log("Step 6 : Log out and login again to application");
		
			SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");
			Thread.sleep(4000);

			SeleniumFunc.ToGoToUrl(Constants.LoginPage);
			login.EnterUsername(username);
			login.EnterPassword("clarion@123");
			login.ClickOnLogInButton();
			Thread.sleep(4000);

			
			//Verifying text
			String expectedtext = "You are currently in the middle of this exam. Please continue to finish the exam.";
			
			if(finalexam.ResumeExamPageText.getText().trim().contains(expectedtext))
			{
				System.out.println("-- User is navigated to Final Exam and asked to resume exam"  );
				Reporter.log("-- User is navigated to Final Exam and asked to resume exam");
			}
			else
			{
				System.out.println("-- Text incorrect" );
				Reporter.log("-- Text incorrect");
				AssertFailedCount++;
			}
					
		System.out.println("Step 7 : On Final Exam page, clicking 'Continue Boating License Certification Exam' button" );
		Reporter.log("Step 7 : On Final Exam page, clicking 'Continue Boating License Certification Exam' button");
		
			
			finalexam.Next.click();
			Thread.sleep(4000);

			String actualtext = SeleniumFunc.GetElementText("css", "ul.breadcrumb");
			expectedtext = "Question 2 of 100";
					
			//verifying url
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- On click to the 'Continue Boating License Certification Exam' button, user is navigated to Question 1 page"  );
				Reporter.log("-- On click to  the 'Continue Boating License Certification Exam' button , user is navigated to Question 1 page");
			}
			else
			{
				System.out.println("-- On click to the 'Continue Boating License Certification Exam' button, user is navigated to Question 1 page and navigated to : " + driver.getCurrentUrl()  );
				Reporter.log("-- On click to the 'Continue Boating License Certification Exam' button , user is navigated to Question 1 page and navigated to :" + driver.getCurrentUrl());
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
	 * Final Exam : Verify results page - a) Result = Fail
	 * */ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void FinalExam_ResultsFail(String State) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : Final Exam : Verify results page - a) Result = Fail"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10 : Final Exam : Verify results page - a) Result = Fail"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "exams" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "exam" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
			Thread.sleep(6000);
			
			
			//Model Reminder
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{		
				register.ClickOnIUnderstandButton();	
				Thread.sleep(4000);
			}
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
					register.ClickOnStateResidencyCheckbox();
					Thread.sleep(4000);
			}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "5", "1990", emailaddress, emailaddress);
		
			Thread.sleep(10000);
			
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();

				
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Fail' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Fail' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Fast Forward Fail");
			Thread.sleep(4000);

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
			
		System.out.println("Step 3 : Verifying Final Exam result page when result = Fail" );
		Reporter.log("Step 3 : Verifying Final Exam result page when result = Fail");
			
			//verifying header
			String expectedtext = "Exam";
			String actualtext = SeleniumFunc.GetElementText("css", "div.page-header h1");
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! Correct page header i.e. " + actualtext);
				Reporter.log("-- Success !! Correct page header i.e. " + actualtext);
			}
			else
			{
				System.out.println("-- Failure !! incorrect page header i.e. " + actualtext);
				Reporter.log("-- Failure !! incorrect page header i.e. " + actualtext);
				AssertFailedCount++;
			}
		
			
			//verifying score
			expectedtext = "Score: ";
			actualtext = SeleniumFunc.GetElementText("css", "#score h3");
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! Score displayed i.e. " + actualtext);
				Reporter.log("-- Success !! Score displayed i.e. " + actualtext);
			}
			else
			{
				System.out.println("-- Failure !! Score not displayed i.e. " + actualtext);
				Reporter.log("-- Failure !! Score not displayed i.e. " + actualtext);
				AssertFailedCount++;
			}
			
			
			//verifying "Sorry, you did not pass the quiz." message
			
			expectedtext = "Sorry, you did not pass the exam.";
			actualtext = SeleniumFunc.GetElementText("css", "div[class='span8 offset2'] h2");
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! 'Sorry, you did not pass the exam.' message displayed ");
				Reporter.log("-- Success !! 'Sorry, you did not pass the exam.' message displayed ");
			}
			else
			{
				System.out.println("-- Failure !! 'Sorry, you did not pass the exam.' message not displayed  and instead displayed - > " + actualtext);
				Reporter.log("-- Failure !! 'Sorry, you did not pass the exam.' message not displayed  and instead displayed - > " + actualtext);
				AssertFailedCount++;
			}
		
			
			//Verifying presence of "See a Critique of Your Boating License Certification Exam Results" button
			expectedtext = "See a Critique";
			actualtext = SeleniumFunc.GetElementText("css", "a[class='critique btn']");
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! 'See a Critique of Your Boating License Certification Exam Results' button displayed ");
				Reporter.log("-- Success !! 'See a Critique of Your Boating License Certification Exam Results' button displayed ");
			}
			else
			{
				System.out.println("-- Failure !! 'See a Critique of Your Boating License Certification Exam Results' button is displayed  but button text is : " + actualtext);
				Reporter.log("-- Failure !! 'See a Critique of Your Boating License Certification Exam Results' button is displayed  but button text is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			expectedtext = "Previous";
			actualtext = SeleniumFunc.GetElementText("css", "#prev_text a");
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! 'Previous' button displayed ");
				Reporter.log("-- Success !! 'Previous' button displayed ");
			}
			else
			{
				System.out.println("-- Failure !! 'Previous' button is displayed  but button text is : " + actualtext);
				Reporter.log("-- Failure !! 'Previous' button is displayed  but button text is : " + actualtext);
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
	 * Final Exam : Verify results page - a) Result = Fail, "See a Critique of Your Boating License Certification Exam Results" button functionality
	 * */ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void FinalExam_ResultsFail_SeeCritique(String State) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 11 : Final Exam : Verify results page - a) Result = Fail, 'See a Critique of Your Boating License Certification Exam Results', 'Study Section Again' buttons functionality"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 11 : Final Exam : Verify results page - a) Result = Fail, 'See a Critique of Your Boating License Certification Exam Results' ,'Study Section Again' buttons functionality"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "exams" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "exam" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
			Thread.sleep(6000);
			
			
			//Model Reminder
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{		
				register.ClickOnIUnderstandButton();	
				Thread.sleep(4000);
			}
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
					register.ClickOnStateResidencyCheckbox();
					Thread.sleep(4000);
			}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "5", "1990", emailaddress, emailaddress);
		
			Thread.sleep(10000);

			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();

	
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Fail' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Fail' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Fast Forward Fail");
			Thread.sleep(4000);

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
				
		System.out.println("Step 3 : Click on 'See a Critique of Your Boating License Certification Exam Results' button" );
		Reporter.log("Step 3 : Click on 'See a Critique of Your Boating License Certification Exam Results' button");
			
			String tempurl2 = driver.getCurrentUrl();
			String tempurl = SeleniumFunc.GetAttributeValue("css", "a[class='critique btn']", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "a[class='critique btn']");
			Thread.sleep(4000);
			
		System.out.println("Step 4 : Verifying Critique page" );
		Reporter.log("Step 4 : Verifying Critique page");
			
			//verifying page url
			if(driver.getCurrentUrl().equals(tempurl))
			{
				System.out.println(" --- User is naviagated to Critique page i.e. " + tempurl );
				Reporter.log("--- User is naviagated to Critique page i.e. " + tempurl);
			}
			else
			{
				System.out.println(" --- User is NOT naviagated to Critique page i.e. " + tempurl );
				Reporter.log("--- User is NOT naviagated to Critique page i.e. " + tempurl);
				AssertFailedCount++;
			}
		
			
			//verifying page header
			String actualtext = SeleniumFunc.GetElementText("css", "div.page-header h1");
			String expectedtext = "Critique";
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println(" --- page title is correct i.e. " + actualtext );
				Reporter.log(" --- page title is correct i.e. " + actualtext);
			}
			else
			{
				System.out.println(" --- page title is incorrect i.e. " + actualtext );
				Reporter.log(" --- page title is incorrect i.e. " + actualtext);
				AssertFailedCount++;
			}
				
		System.out.println("Step 5 : Clicking 'Back to the Exam Results' button" );
		Reporter.log("Step 5 : Clicking 'Back to the Exam Results' button");
			
			SeleniumFunc.ClickOnElement("css", "#prev_text a");
			Thread.sleep(4000);

				//verifying header
				expectedtext = "Exam";
				actualtext = SeleniumFunc.GetElementText("css", "div.page-header h1");
				
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! user is navigated to Exam result page ");
					Reporter.log("-- Success !! user is navigated to Exam result page ");
				}
				else
				{
					System.out.println("-- Failure !! user is NOT navigated to Exam result page");
					Reporter.log("-- Failure !! user is NOT navigated to Exam result page");
					AssertFailedCount++;
				}
					
		
		System.out.println("Step 6 : On Critique page, clicking on 'Study This Section Again' button" );
		Reporter.log("Step 6 : On Critique page, clicking on 'Study This Section Again' button");
			
			SeleniumFunc.ClickOnElement("css", "a[class='critique btn']");	
			Thread.sleep(4000);

			tempurl2 = driver.getCurrentUrl();
			tempurl = SeleniumFunc.GetAttributeValue("css", "table[class='table critique'] a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "table[class='table critique'] a");
			Thread.sleep(4000);

			//verifying page url
				if(driver.getCurrentUrl().contains(tempurl))
				{
					System.out.println(" --- User is naviagated to Section page i.e. " + tempurl );
					Reporter.log("--- User is naviagated to Section page i.e. " + tempurl);
				}
				else
				{
					System.out.println(" --- User is NOT naviagated to Section page i.e. " + tempurl );
					Reporter.log("--- User is NOT naviagated to Section page i.e. " + tempurl);
					AssertFailedCount++;
				}
				
					
		System.out.println("Step 7 : On Section page, , clicking on 'Return to Critique' button" );
		Reporter.log("Step 7 : On Section page, , clicking on 'Return to Critique' button");
			
		
			SeleniumFunc.ClickOnElement("css", "li.control-center a");	
			Thread.sleep(4000);

			//verifying page url
				if(driver.getCurrentUrl().contains(tempurl2))
				{
					System.out.println(" --- User is naviagated to Critique page i.e. " + tempurl2 );
					Reporter.log("--- User is naviagated to Critique page i.e. " + tempurl2);
				}
				else
				{
					System.out.println(" --- User is NOT naviagated to Critique page i.e. " + tempurl2 );
					Reporter.log("--- User is NOT naviagated to Critique page i.e." + tempurl2);
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
	 * Final Exam : Verify results page - a) Result = Pass
	 * */ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void FinalExam_ResultsPass(String State) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 12 : Final Exam : Verify results page - a) Result = Pass"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 12 : Final Exam : Verify results page - a) Result = Pass"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "exams" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "exam" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
			Thread.sleep(6000);
			
			
			//Model Reminder
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{		
				register.ClickOnIUnderstandButton();	
				Thread.sleep(4000);
			}
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
					register.ClickOnStateResidencyCheckbox();
					Thread.sleep(4000);
			}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "5", "1990", emailaddress, emailaddress);
		
			Thread.sleep(10000);
			
			ContentsPage content = new ContentsPage(driver);

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
			Thread.sleep(4000);

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
					
			
			//verifying score
			String expectedtext = "Score:";
			String actualtext = SeleniumFunc.GetElementText("css", "#score h3");
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! Score displayed i.e. " + actualtext);
				Reporter.log("-- Success !! Score displayed i.e. " + actualtext);
			}
			else
			{
				System.out.println("-- Failure !! Score not displayed i.e. " + actualtext);
				Reporter.log("-- Failure !! Score not displayed i.e. " + actualtext);
				AssertFailedCount++;
			}
				
			
			//Verifying presence of "Complete Application" button
			expectedtext = "Complete the Application";
			actualtext = SeleniumFunc.GetElementText("css", "#next_text a");
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! 'Complete the Application' button displayed ");
				Reporter.log("-- Success !! 'Complete the Application' button displayed ");
			}
			else
			{
				System.out.println("-- Failure !! 'Complete the Application' button is displayed  but button text is : " + actualtext);
				Reporter.log("-- Failure !! 'Complete the Application' button is displayed  but button text is : " + actualtext);
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
	
	

	/* Test 13: 
	 * Final Exam : Verify results page - a) Result = Pass , " Complete the Application " button functionality
	 * */ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void FinalExam_ResultsPass_CompleteApplicationButton(String State) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 13 : Final Exam : Verify results page - a) Result = Pass , 'Complete the Application' button functionality"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 13 : Final Exam : Verify results page - a) Result = Pass , 'Complete the Application' button functionality"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		//ContentsPage content = new ContentsPage(driver);
		//QuizPage quiz = new QuizPage(driver);
		//PracticeExamPage practiceexam = new PracticeExamPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "exams" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "exams" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
			Thread.sleep(6000);
			
			
			//Model Reminder
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{		
				register.ClickOnIUnderstandButton();	
				Thread.sleep(4000);
			}
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
					register.ClickOnStateResidencyCheckbox();
					Thread.sleep(4000);
			}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "5", "1990", emailaddress, emailaddress);
		
			Thread.sleep(10000);
			
			ContentsPage content = new ContentsPage(driver);

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
			Thread.sleep(4000);

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
