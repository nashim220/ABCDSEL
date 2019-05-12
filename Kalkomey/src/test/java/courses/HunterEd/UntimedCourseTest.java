package courses.HunterEd;


import org.openqa.selenium.JavascriptExecutor;
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
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.PracticeExamPage;
import pageFactory.Courses.ProfileNewPage;
import pageFactory.Courses.QuizPage;
import pageFactory.Courses.RegistrationNewPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class UntimedCourseTest 
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
	 * Untimed Course - Verify that Timer is NOT displayed  
	*/ 
	@Test()
	private void Untimed_TimerNotDisplayed() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Untimed Course - Verify that Timer is NOT displayed  "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Untimed Course - Verify that Timer is NOT displayed  "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationNewPage register = new RegistrationNewPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ContentsPage content = new ContentsPage(driver);

		System.out.println("Step 1 : Navigate to untimed course State page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter);
		Reporter.log("Step 1 : Navigate to untimed course State page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
		
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
		
			String username="texas"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username,  "January", "2", "1990", emailaddress, "clarion@123", "clarion@123");
			register.ClickOnCreateAccount();
	
			Thread.sleep(8000);
			
		/*	register.WelcomeAboard.click();
			Thread.sleep(3000);*/
			

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
		System.out.println("Step 3 : Expanding Unit 1, Expanding Topic 1 , clicking on Page1 link");
		Reporter.log("Step 3 : Expanding Unit 1, Expanding Topic 1 , clicking on Page1 link"); 
			
			content.Untimed_ClickOnUnitTopicPageLink(1,1,1);
			Thread.sleep(4000);

		System.out.println("Step 4 : Verifying whether timer is displayed on 'Next' button or not");
		Reporter.log("Step 4 : Verifying whether timer is displayed on 'Next' button or not"); 
		
				String ExpectedText = "Next";
				String ActualText= SeleniumFunc.GetElementText("css", "a.timer").trim();
				System.out.println(ActualText);
				
	
				if(ActualText.contains(ExpectedText))
				{
					System.out.println("Success !! Timer is not displayed" );
					Reporter.log("Success !! Timer is not displayed");		
								
				}
				else
				{
					System.out.println("Failure !! Timer is displayed");
					Reporter.log("Failure !! Timer is displayed");
					AssertFailedCount++;
				}
		
				
		System.out.println("Step 5 : Expanding Unit 1, Expanding Topic 1 , clicking on Page 2 link");
		Reporter.log("Step 5 : Expanding Unit 1, Expanding Topic 1 , clicking on Page 2 link"); 
		
			driver.navigate().back();
			Thread.sleep(4000);

			content.Untimed_ClickOnUnitTopicPageLink(1,1,2);
			Thread.sleep(4000);

			
		System.out.println("Step 6 : Verifying whether timer is displayed on 'Next' button or not");
		Reporter.log("Step 6 : Verifying whether timer is displayed on 'Next' button or not"); 
		
		
			ExpectedText = "Next";
			ActualText= SeleniumFunc.GetElementText("css", "li.next a.timer").trim();
			System.out.println(ActualText);
			
	
			if(ActualText.contains(ExpectedText))
			{
				System.out.println("Success !! Timer is not displayed" );
				Reporter.log("Success !! Timer is not displayed");		
							
			}
			else
			{
				System.out.println("Failure !! Timer is displayed");
				Reporter.log("Failure !! Timer is displayed");
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
	 * Untimed Course - Verify that user can access any Topic randomly
	*/ 
	@Test()
	private void Untimed_AccessTopicRandomly() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Untimed Course - Verify that user can access any Topic randomly"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Untimed Course - Verify that user can access any Topic randomly"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationNewPage register = new RegistrationNewPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ContentsPage content = new ContentsPage(driver);
			
		System.out.println("Step 1 : Navigate to untimed course State page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter);
		Reporter.log("Step 1 : Navigate to untimed course State page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
		
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
		
			String username="texas"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username,  "January", "2", "1990", emailaddress, "clarion@123", "clarion@123");
			register.ClickOnCreateAccount();
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
		System.out.println("Step 3 : Expanding Unit 1, Expanding Topic 1 , clicking on Page 1 link");
		Reporter.log("Step 3 : Expanding Unit 1, Expanding Topic 1 , clicking on Page 1 link"); 
			
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 500);");	
		
			String expectedurl = content.Untimed_GetUnitHrefLink(1, 1, 1)+"/";
			content.Untimed_ClickOnUnitTopicPageLink(1,1,1);
			Thread.sleep(4000);
		
		System.out.println("Step 4 : Verifying whether user is navigated to correct Page or not ");
		Reporter.log("Step 4 : Verifying whether user is navigated to correct Page or not"); 
		
				Thread.sleep(5000);
				String Actualurl= driver.getCurrentUrl();
				System.out.println(Actualurl);
				
	
				if(Actualurl.contains(expectedurl))
				{
					System.out.println("Success !! User is navigated to Unit 1 > Topic 1 > Page 1" );
					Reporter.log("Success !! User is navigated to Unit 1 > Topic 1 > Page 1");		
								
				}
				else
				{
					System.out.println("Failure !! User is NOT navigated to Unit 1 > Topic 1 > Page 1");
					Reporter.log("Failure !! User is NOT navigated to Unit 1 > Topic 1 > Page 1");
					AssertFailedCount++;
				}
		
				
		System.out.println("Step 5 : Expanding Unit 1, Expanding Topic 3 , clicking on Page 1 link");
		Reporter.log("Step 5 : Expanding Unit 1, Expanding Topic 3 , clicking on Page 1 link"); 
			
			driver.navigate().back();
			Thread.sleep(4000);

			jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 500);");	
			
			expectedurl = content.Untimed_GetUnitHrefLink(1, 3, 1)+"/";
			content.Untimed_ClickOnUnitTopicPageLink(1,3,1);
		
		System.out.println("Step 6 : Verifying whether user is navigated to correct Page or not ");
		Reporter.log("Step 6 : Verifying whether user is navigated to correct Page or not"); 
		
				Thread.sleep(5000);
				Actualurl= driver.getCurrentUrl();
				System.out.println(Actualurl);
				
	
				if(Actualurl.contains(expectedurl))
				{
					System.out.println("Success !! User is navigated to Unit 1 > Topic 3 > Page 1" );
					Reporter.log("Success !! User is navigated to Unit 1 > Topic 3 > Page 1");		
								
				}
				else
				{
					System.out.println("Failure !! User is NOT navigated to Unit 1 > Topic 3 > Page 1");
					Reporter.log("Failure !! User is NOT navigated to Unit 1 > Topic 3 > Page 1");
					AssertFailedCount++;
				}
				
				
			System.out.println("Step 7 : Expanding Unit 4, Expanding Topic 4 , clicking on Page 4 link");
			Reporter.log("Step 7 : Expanding Unit 4, Expanding Topic 4 , clicking on Page 4 link"); 
			
				driver.navigate().back();
				Thread.sleep(4000);

				jse = (JavascriptExecutor)driver;
				jse.executeScript("scroll(0, 500);");	
				
				expectedurl = content.Untimed_GetUnitHrefLink(4, 4, 4)+"/";
				content.Untimed_ClickOnUnitTopicPageLink(4,4,4);
				Thread.sleep(4000);
			
			System.out.println("Step 8 : Verifying whether user is navigated to correct Page or not ");
			Reporter.log("Step 8 : Verifying whether user is navigated to correct Page or not"); 
			
					Thread.sleep(5000);
					Actualurl= driver.getCurrentUrl();
					System.out.println(Actualurl);
					
		
					if(Actualurl.contains(expectedurl))
					{
						System.out.println("Success !! User is navigated to Unit 4 > Topic 4 > Page 4" );
						Reporter.log("Success !! User is navigated to Unit 4 > Topic 4 > Page 4");		
									
					}
					else
					{
						System.out.println("Failure !! User is NOT navigated to Unit 4 > Topic 4 > Page 4");
						Reporter.log("Failure !! User is NOT navigated to Unit 4 > Topic 4 > Page 4");
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
	//
	
	/* Test 3: 
	 * Untimed Course - Verify that quizzes must be completed before attempting final exam
	*/ 
	@Test()
	private void Untimed_AtteptFinalExam() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Untimed Course - Verify that quizzes must be completed before attempting final exam"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Untimed Course - Verify that quizzes must be completed before attempting final exam"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationNewPage register = new RegistrationNewPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ContentsPage content = new ContentsPage(driver);
		QuizPage quiz = new QuizPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1 : Navigate to untimed course State page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter);
		Reporter.log("Step 1 : Navigate to untimed course State page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			PageHeader header = new PageHeader(driver);

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
		
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
		
			String username="texas"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username,  "January", "2", "1990", emailaddress, "clarion@123", "clarion@123");
			register.ClickOnCreateAccount();
	
			Thread.sleep(8000);
			
		/*	register.WelcomeAboard.click();
			Thread.sleep(3000);*/

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}

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
			
		System.out.println("Step 3 : Attempting all quizes one by one and verifying whether user is allowed to attemp final exam or not");
		Reporter.log("Step 3 : Attempting all quizes one by one and verifying whether user is allowed to attemp final exam or not"); 
		
			

			for(int i=1;i<9;i++)
			{
				String ContentPageUrl = driver.getCurrentUrl();
				
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("scroll(0, 200);");	
				//Clicking on "Take Quiz button"
				content.TakeQuiz.click();
				Thread.sleep(5000);

				quiz.ClickOnNextButton();
				Thread.sleep(5000);

				
				System.out.println(" -- Attempting Quiz " + i  + " and marking as Pass" );
				Reporter.log(" -- Attempting Quiz " + i  + " and marking as Pass");
				
				SeleniumFunc.ToGoToUrl(Constants.AdminURL);
				keadmin.StudentSearchAndActivity(username, "Hunter", "Quizzes");
				Thread.sleep(5000);

				keadmin.Quizzes_PassAQuiz(i);
				Thread.sleep(5000);

				SeleniumFunc.ToGoToUrl(ContentPageUrl);
				Thread.sleep(5000);
				
				System.out.println(" -- verifying whether user is allowed to attemp final exam or not");
				Reporter.log(" -- verifying whether user is allowed to attemp final exam or not");
				
				String expected = "Please complete all the unit quizzes before attempting the 'Hunter Education Certification Exam'";
				String actual = content.TakeCertificationExam_Disabled.getAttribute("title");
				
				if(actual.equals(expected))
				{
					System.out.println(" ---- Success !! user is NOT allowed to attemp final exam "); 
					Reporter.log(" ---- Success !! user is NOT allowed to attemp final exam "); 
				}
				else
				{
					System.out.println("---- Failure !!  user is allowed to attemp final exam");
					Reporter.log("---- Failure !!  user is allowed to attemp final exam");
					AssertFailedCount++;
					break;
				}
				
			}
		
			
			String ContentPageUrl = driver.getCurrentUrl();
			
			//Clicking on "Take Quiz button"
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 500);");	
			
			content.TakeQuiz.click();
			Thread.sleep(5000);

			quiz.ClickOnNextButton();
			Thread.sleep(5000);			
			
			System.out.println(" -- Attempting Quiz " + 9  + " and marking as Pass" );
			Reporter.log(" -- Attempting Quiz " + 9  + " and marking as Pass");
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Quizzes");
			Thread.sleep(5000);

			keadmin.Quizzes_PassAQuiz(9);
			Thread.sleep(5000);

			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(5000);
			
			System.out.println(" -- verifying whether user is allowed to attemp final exam or not");
			Reporter.log(" -- verifying whether user is allowed to attemp final exam or not");
			
			String expected = "Take the Hunter Education Certification Exam";
			String actual = content.TakeCertificationExam_Enabled.getText();
			
			if(actual.equals(expected))
			{
				System.out.println(" ---- Success !! user is allowed to attemp final exam once all quizes are completed "); 
				Reporter.log(" ---- Success !! user is allowed to attemp final exam once all quizes are completed"); 
			}
			else
			{
				System.out.println("---- Failure !!  user is NOT allowed to attemp final though all quizes are completed");
				Reporter.log("---- Failure !!  user is NOT allowed to attemp final exam though all quizes are completed");
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
	 * Untimed Course - Final Exam : UI element verification for Final Exam page
	*/ 
	@Test()
	private void Untimed_FinalExam_UIVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Untimed Course - Final Exam : UI element verification for Final Exam page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Untimed Course - Final Exam : UI element verification for Final Exam page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationNewPage register = new RegistrationNewPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ContentsPage content = new ContentsPage(driver);
		QuizPage quiz = new QuizPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		PracticeExamPage practiceexam = new PracticeExamPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);

		System.out.println("Step 1 : Navigate to untimed course State page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter);
		Reporter.log("Step 1 : Navigate to untimed course State page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
		
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
		
			String username="texas"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username,  "January", "2", "1990", emailaddress, "clarion@123", "clarion@123");
			register.ClickOnCreateAccount();
			Thread.sleep(8000);
			
	/*		register.WelcomeAboard.click();
			Thread.sleep(3000);*/
			
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
		System.out.println("Step 3 : Attempting all quizes");
		Reporter.log("Step 3 : Attempting all quizes "); 
		
		
			for(int i=1;i<10;i++)
			{
				String ContentPageUrl = driver.getCurrentUrl();
				
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("scroll(0, 200);");	
				
				//Clicking on "Take Quiz button"
				content.TakeQuiz.click();
				Thread.sleep(3000);

				quiz.ClickOnNextButton();
				Thread.sleep(3000);

				System.out.println(" -- Attempting Quiz " + i  + " and marking as Pass" );
				Reporter.log(" -- Attempting Quiz " + i  + " and marking as Pass");
				
				SeleniumFunc.ToGoToUrl(Constants.AdminURL);
				keadmin.StudentSearchAndActivity(username, "Hunter", "Quizzes");
				Thread.sleep(3000);

				keadmin.Quizzes_PassAQuiz(i);
				Thread.sleep(3000);

				SeleniumFunc.ToGoToUrl(ContentPageUrl);
				Thread.sleep(3000);
			}
		
			
		System.out.println("Step 3 : Clicking on 'Take the Hunter Education Certification Exam' button");
		Reporter.log("Step 3 : Clicking on 'Take the Hunter Education Certification Exam' button"); 
		
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 500);");	
		
			content.TakeCertificationExam_Enabled.click();
			Thread.sleep(3000);

			
		System.out.println("Step 4 : Verifying UI elements on Final Exam page" );
		Reporter.log("Step 4 : Verifying UI elements on Final Exam page");
		
			//verifying page header
			if(practiceexam.PageTitle.getText().trim().equals("Hunter Education Certification Exam"))
			{
				System.out.println("-- Page header is correct i.e. Hunter Education Certification Exam"  );
				Reporter.log("-- Page header is correct i.e. Hunter Education Certification Exam");
			}
			else
			{
				System.out.println("-- Page header is incorrect"  );
				Reporter.log("-- Page header is incorrect");
				AssertFailedCount++;
			}
			
		
			//verifying rules
			String expectedtext = "Before taking the Hunter Education Certification Exam, make sure you have reviewed all of the course material." + "\n" + 
								  "75 questions (multiple choice)."+ "\n" +
								  "Choose the best of the four possible answers for each multiple-choice question." + "\n" + 
								  "75% to pass" + "\n" + 
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
			
			//verifying 'Begin the Final Exam' button
			if(finalexam.FinalExam.getText().trim().contains("Begin the Hunter Education Certification Exam"))
			{
				System.out.println("-- 'Begin the Hunter Education Certification Exam' button is present"  );
				Reporter.log("-- 'Begin the Hunter Education Certification Exam' button is present");
			}
			else
			{
				System.out.println("-- 'Begin the Hunter Education Certification Exam' button is not present"  );
				Reporter.log("-- 'Begin the Hunter Education Certification Exam' button is not present ");
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
	 * Untimed Course - Final Exam :  Verify all buttons works as expected i.e. 'Previous', 'Begin the Hunter Education Certification Exam' 
	*/ 
	@Test()
	private void Untimed_FinalExam_UI_Buttons() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Untimed Course - Final Exam :  Verify all buttons works as expected i.e. 'Previous', 'Begin the Hunter Education Certification Exam' "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Untimed Course - Final Exam :  Verify all buttons works as expected i.e. 'Previous', 'Begin the Hunter Education Certification Exam' "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationNewPage register = new RegistrationNewPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ContentsPage content = new ContentsPage(driver);
		QuizPage quiz = new QuizPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1 : Navigate to untimed course State page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter);
		Reporter.log("Step 1 : Navigate to untimed course State page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			PageHeader header = new PageHeader(driver);

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
		
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
		
			String username="texas"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username,  "January", "2", "1990", emailaddress, "clarion@123", "clarion@123");
			register.ClickOnCreateAccount();
	
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}

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

		System.out.println("Step 3 : Attempting all quizes");
		Reporter.log("Step 3 : Attempting all quizes "); 
		
		
			for(int i=1;i<10;i++)
			{
				String ContentPageUrl = driver.getCurrentUrl();
				
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("scroll(0, 200);");	
				
				//Clicking on "Take Quiz button"
				content.TakeQuiz.click();
				Thread.sleep(3000);

				quiz.ClickOnNextButton();
				Thread.sleep(3000);
				
				System.out.println(" -- Attempting Quiz " + i  + " and marking as Pass" );
				Reporter.log(" -- Attempting Quiz " + i  + " and marking as Pass");
				
				SeleniumFunc.ToGoToUrl(Constants.AdminURL);
				keadmin.StudentSearchAndActivity(username, "Hunter", "Quizzes");
				Thread.sleep(3000);

				keadmin.Quizzes_PassAQuiz(i);
				Thread.sleep(3000);

				SeleniumFunc.ToGoToUrl(ContentPageUrl);
				Thread.sleep(3000);
				
			}
		
			
		System.out.println("Step 3 : Clicking on 'Take the Hunter Education Certification Exam' button");
		Reporter.log("Step 3 : Clicking on 'Take the Hunter Education Certification Exam' button"); 
		
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 500);");	
		
			content.TakeCertificationExam_Enabled.click();
			Thread.sleep(3000);

			
		System.out.println("Step 5 : On Final Exam page, clicking 'Previous' button" );
		Reporter.log("Step 5 : On Final Exam page, clicking 'Previous' button");
		
			String expectedurl = finalexam.Previous.getAttribute("href")+"/";
			
			finalexam.Previous.click();
			Thread.sleep(3000);

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
			
			
		System.out.println("Step 6 : On Final Exam page, clicking 'Take the Hunter Education Certification Exam' button" );
		Reporter.log("Step 6 : On Final Exam page, clicking 'Take the Hunter Education Certification Exam' button");
		
			driver.navigate().back();
			Thread.sleep(3000);

			finalexam.FinalExam.click();
			Thread.sleep(3000);

			String actualtext = SeleniumFunc.GetElementText("css", "ul.breadcrumb");
			String expectedtext = "Hunter Education Certification Exam / Question 1 of 75";
					
			//verifying url
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- On click to Begin Final Exam button , user is navigated to Question 1 page"  );
				Reporter.log("-- On click to Begin Final Exam button , user is navigated to Question 1 page");
			}
			else
			{
				System.out.println("-- On click to Begin Final Exam Exam button , user is NOT navigated to Question 1 page and navigated to : " + driver.getCurrentUrl()  );
				Reporter.log("-- On click to Begin Final Exam  Exam button , user is NOT navigated to Question 1 page and navigated to :" + driver.getCurrentUrl());
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
	 * Untimed Course - Final Exam - Verify that user can attend Final Exam (Result = Fail)
	*/ 
	@Test()
	private void Untimed_FinalExam_Fail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Untimed Course - Final Exam - Verify that user can attend Final Exam (Result = Fail)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 :  Untimed Course - Final Exam - Verify that user can attend Final Exam (Result = Fail)"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationNewPage register = new RegistrationNewPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ContentsPage content = new ContentsPage(driver);
		QuizPage quiz = new QuizPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);

		System.out.println("Step 1 : Navigate to untimed course State page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter);
		Reporter.log("Step 1 : Navigate to untimed course State page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
		
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
		
			String username="texas"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username,  "January", "2", "1990", emailaddress, "clarion@123", "clarion@123");
			register.ClickOnCreateAccount();
	
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}

			
		System.out.println("Step 3 : Attempting all quizes");
		Reporter.log("Step 3 : Attempting all quizes "); 
		
		
			for(int i=1;i<10;i++)
			{
				String ContentPageUrl = driver.getCurrentUrl();
				
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("scroll(0, 200);");	
				
				//Clicking on "Take Quiz button"
				content.TakeQuiz.click();
				Thread.sleep(3000);

				quiz.ClickOnNextButton();
				Thread.sleep(3000);

				System.out.println(" -- Attempting Quiz " + i  + " and marking as Pass" );
				Reporter.log(" -- Attempting Quiz " + i  + " and marking as Pass");
				
				SeleniumFunc.ToGoToUrl(Constants.AdminURL);
				keadmin.StudentSearchAndActivity(username, "Hunter", "Quizzes");
				Thread.sleep(3000);

				keadmin.Quizzes_PassAQuiz(i);
				Thread.sleep(3000);

				SeleniumFunc.ToGoToUrl(ContentPageUrl);
				Thread.sleep(3000);
				
			}
		
			
		System.out.println("Step 3 : Clicking on 'Take the Hunter Education Certification Exam' button");
		Reporter.log("Step 3 : Clicking on 'Take the Hunter Education Certification Exam' button"); 
		
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 500);");	
		
			content.TakeCertificationExam_Enabled.click();		
			Thread.sleep(3000);

		System.out.println("Step 4 : On Final Exam page, clicking 'Take the Hunter Education Certification Exam' button and attending exam to get result = Fail" );
		Reporter.log("Step 4 : On Final Exam page, clicking 'Take the Hunter Education Certification Exam' button and attending exam to get result = Fail");
		
			finalexam.FinalExam.click();
			Thread.sleep(3000);

			String actualtext = SeleniumFunc.GetElementText("css", "ul.breadcrumb");
			String expectedtext = "Hunter Education Certification Exam / Question 1 of 75";
					
			//verifying url
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- On click to Begin Final Exam button , user is navigated to Question 1 page"  );
				Reporter.log("-- On click to Begin Final Exam button , user is navigated to Question 1 page");
			}
			else
			{
				System.out.println("-- On click to Begin Final Exam Exam button , user is NOT navigated to Question 1 page and navigated to : " + driver.getCurrentUrl()  );
				Reporter.log("-- On click to Begin Final Exam  Exam button , user is NOT navigated to Question 1 page and navigated to :" + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
			for(int i=1; i<=75;i++)
			{
				//Selecting answer
				finalexam.Answer1.click();
				Thread.sleep(3000);

				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
				Thread.sleep(3000);

			}
			
			
		
		System.out.println("Step 5 : Verifying Final Exam result page when result = Fail" );
		Reporter.log("Step 5 : Verifying Final Exam result page when result = Fail");
			
			//verifying header
			expectedtext = "Hunter Education Certification Exam";
			actualtext = SeleniumFunc.GetElementText("css", "div.page-header h1");
			
			if(actualtext.equals(expectedtext))
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
			expectedtext = "See a Critique of Your Hunter Education Certification Exam Results";
			actualtext = SeleniumFunc.GetElementText("css", "a[class='critique btn']");
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! 'See a Critique of Your Final Exam Results' button displayed ");
				Reporter.log("-- Success !! 'See a Critique of Your Final Exam Results' button displayed ");
			}
			else
			{
				System.out.println("-- Failure !! 'See a Critique of Your Final Exam Results' button is displayed  but button text is : " + actualtext);
				Reporter.log("-- Failure !! 'See a Critique of Your Final Exam Results' button is displayed  but button text is : " + actualtext);
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
	

	/* Test 7: 
	 * Untimed Course - Verify that user can attend Final Exam (Result = PASS)
	 * */ 
	@Test
	private void Untimed_FinalExam_Pass() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Untimed Course - Verify that user can attend Final Exam (Result = PASS)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Untimed Course - Verify that user can attend Final Exam (Result = PASS)"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "exams" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "exam" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username,  "January", "2", "1990", emailaddress, "clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}

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
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
			//Go back to Contents page
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(5000);

			//Click on Continue Exam button
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(5000);

			//Selecting Answer for Question 61 and clicking on Next button
			
				//Selecting answer
				finalexam.Answer1.click();
				Thread.sleep(5000);

				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
				Thread.sleep(5000);			 
			
		System.out.println("Step 4 : Verifying Final Exam result page when result = Pass" );
		Reporter.log("Step 4 : Verifying Final Exam result page when result = Pass");
			
			//verifying header
			String expectedtext = "Hunter Education Certification Exam";
			String actualtext = SeleniumFunc.GetElementText("css", "div.page-header h1");
			
			if(actualtext.equals(expectedtext))
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
			expectedtext = "Score:";
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
			
			
			//verifying "Next, get your Online Course Completion Voucher" message
			
			expectedtext = "Next, get your Temporary Hunter Education Certificate";
			actualtext = SeleniumFunc.GetElementText("css", "div[class='span8 offset2'] h2");
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! 'Next, get your Online Course Completion Voucher' message displayed ");
				Reporter.log("-- Success !! 'Next, get your Online Course Completion Voucher' message displayed ");
			}
			else
			{
				System.out.println("-- Failure !! 'Next, get your Online Course Completion Voucher' message not displayed  and instead displayed - > " + actualtext);
				Reporter.log("-- Failure !! 'Next, get your Online Course Completion Voucher' message not displayed  and instead displayed - > " + actualtext);
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

}
