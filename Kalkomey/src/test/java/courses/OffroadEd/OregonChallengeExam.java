package courses.OffroadEd;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.ContentsPage;
import pageFactory.Courses.LoginPage;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.RegistrationPage;
import pageFactory.Courses.OregonExam;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class OregonChallengeExam 
{
	private WebDriver driver;
	private Browser b = new Browser();
	JavaHelpers JH = new JavaHelpers();
	
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


	/*Test 1
	Verify user is displayed info on challenge exam on landing page after registration Run
	*/
	
	@Test
	public void ChallengeExamForOregon() throws InterruptedException
	{
		System.out.println("====" + "\n" +
				"Test 1 : Challenge Exam : Verify Challenge Exam is there for Oregon"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			  "Test 1 : Challenge Exam : Verify Challenge Exam is there for Oregon"  + "\n" +
			 	  "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		OregonExam orexam = new OregonExam(driver);	
	
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
		
			String username= "exams" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "exam" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Offroad + Constants.ChallengeExam_Offroad);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Offroad + Constants.ChallengeExam_Offroad);
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
						
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}		
			
			if(orexam.ORExamTitle.getText().trim().equals("Take the Oregon Challenge Exam"))
			{
				System.out.println("-- Exam title is correct i.e. Take the Oregon Challenge Exam"  );
				Reporter.log("-- Exam title is correct i.e. Take the Oregon Challenge Exam");
			}
			else
			{
				System.out.println("-- Exam title is incorrect"  );
				Reporter.log("-- Exam title is incorrect");
				AssertFailedCount++;
			}
		
		System.out.println("Step 2 : Verifying presence of challenge exam information" );
		Reporter.log("Step 2 : Verifying presence of challenge exam information");

			String expectedtext = "Do you feel that you are an experienced and safe ATV operator? If so, you can skip the course and immediately take an exam covering the course material.";
			String actaultext = orexam.ExamInfo.getText().trim() ; 
		
			if(actaultext.contains(expectedtext))
			{
				System.out.println("-- Info text matched"  );
				Reporter.log("Info text matched");
			}
			else
			{
				System.out.println("-- Info text doesn't match. Expected is: " + "\n" +  expectedtext + "\n" + "Actual is :" + "\n" + actaultext );
				Reporter.log("-- Info text doesn't match. Expected is: " + "\n" +  expectedtext + "\n" + "Actual is :" + "\n" + actaultext );
				AssertFailedCount++;
			}
		
			if(AssertFailedCount>0)	
			{
				//Marking this test as Failed
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
				Assert.fail();
			}
	}
	
	
	/*	Test 2
	Verify it is only for age 16 years and above Run
	*/

	@Test
	public void VerifyAgeLimit() throws InterruptedException
	{
		System.out.println("====" + "\n" +
				"Test 2 : Challenge Exam : Verify exam is only for student with age 16 years and above"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			  "Test 2 : Challenge Exam : Verify exam is only for student with age 16 years and above"  + "\n" +
			"====");	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
	
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
		
			String username= "exams" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "exam" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Offroad + Constants.ChallengeExam_Offroad);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Offroad + Constants.ChallengeExam_Offroad);
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "2010", emailaddress, emailaddress);
			Thread.sleep(8000);
					
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}		
			
			boolean prs = SeleniumFunc.IsElementPresent("css",".span5>h2" );
			if(prs)
			{
				System.out.println("--Failed -  User is able to take Oregon Challenge Exam with age less than 16"  );
				Reporter.log("--Failed -  User is able to take Oregon Challenge Exam with age less than 16");
				AssertFailedCount++;
			}
			else
			{
				System.out.println("-- Age limit functionality is working properly"  );
				Reporter.log("-- Age limit functionality is working properly");
			}
		
			if(AssertFailedCount>0)	
			{
			
				//Marking this test as Failed
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
				Assert.fail();
			}
	}
	
	
	/*	Test 3
	Verify Logging out on the choice page (course vs. Challenge Exam) takes user to where he left off on relogin. Run
	*/
	
	@Test
	public void VerifyReloginOnChoicePage() throws InterruptedException
	{
		System.out.println("====" + "\n" +
				"Test 3 : Verify User is redirected to choice page when logged out from same"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			  "Test 3 : Verify User is redirected to choice page when logged out from same"  + "\n" +
			 	  "====");	
	
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			RegistrationPage register = new RegistrationPage(driver);
			OregonExam orexam = new OregonExam(driver);	
			LoginPage login = new LoginPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
		
			String username= "exams" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "exam" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
		
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Offroad + Constants.ChallengeExam_Offroad);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Offroad + Constants.ChallengeExam_Offroad);
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

			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}		
			
			if(orexam.ORExamTitle.getText().trim().equals("Take the Oregon Challenge Exam"))
			{
				System.out.println("-- Exam title is correct i.e. Take the Oregon Challenge Exam"  );
				Reporter.log("-- Exam title is correct i.e. Take the Oregon Challenge Exam");
			}
			else
			{
				System.out.println("-- Exam title is incorrect"  );
				Reporter.log("-- Exam title is incorrect");
				AssertFailedCount++;
			}	

		System.out.println("Step 5 : Log out and login again to application" );
		Reporter.log("Step 5 : Log out and login again to application");
			
			SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");

			SeleniumFunc.ToGoToUrl(Constants.LoginPage_Offroad);
			Thread.sleep(4000);

			login.EnterUsername(username);
			login.EnterPassword("clarion@123");
			login.ClickOnLogInButton();
			Thread.sleep(4000);

			if(orexam.ORExamTitle.getText().trim().equals("Take the Oregon Challenge Exam"))
			{
				System.out.println("-- Exam title is correct i.e. Take the Oregon Challenge Exam"  );
				Reporter.log("-- Exam title is correct i.e. Take the Oregon Challenge Exam");
			}
			else
			{
				System.out.println("-- Exam title is incorrect"  );
				Reporter.log("-- Exam title is incorrect");
				AssertFailedCount++;
			}
			
			Thread.sleep(5000);

			String expectedtext = Constants.ApplicationURL_Offroad  + "/course/choose_path/";
			String actaultext =  driver.getCurrentUrl(); 
			
			
			if(actaultext.contains(expectedtext))
			{
				System.out.println("-- User navigate to choice page"  );
				Reporter.log("User navigate to choice page");
			}
			else
			{
				System.out.println("-- User is not navigate to choice page. Expected is: " +  driver.getCurrentUrl() );
				Reporter.log("-- User is not navigate to choice page. Expected is: "  + driver.getCurrentUrl() );
				AssertFailedCount++;
			}

			if(AssertFailedCount>0)	
			{
				
				//Marking this test as Failed
				
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
				
				Assert.fail();
			}

	}
	
	
	/*	Test 4
	Verify user lands on same page where he left in the middle of the Challenge Exam on relogin Run
	*/
	
	@Test
	public void ExamPage_Resume() throws InterruptedException
	{
		System.out.println("====" + "\n" +
				"Test 4 : Verify User is redirected to choice page when logged out from same"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			  "Test 4 : Verify User is redirected to choice page when logged out from same"  + "\n" +
			 	  "====");	
	
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			RegistrationPage register = new RegistrationPage(driver);
			OregonExam orexam = new OregonExam(driver);	
			LoginPage login = new LoginPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
		
			String username= "exams" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "exam" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
		
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Offroad + Constants.ChallengeExam_Offroad);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Offroad + Constants.ChallengeExam_Offroad);
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
					
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}		
			
		System.out.println("Step 5 : Start Challenge Exam" );
		Reporter.log("Step 5 : Start Challenge Exam");
		
			orexam.ClickOnStartExamButton();
			Thread.sleep(10000);  
		
		System.out.println("Step 4 : On Challenge Exam page, clicking start exam button" );
		Reporter.log("Step 4 : On Challenge Exam page, clicking start exam  button");
	
			orexam.ClickOnBeginExamButton();
			Thread.sleep(4000);

		System.out.println("Step 5 : On Challenge exam question page ,selecting answer and moving to next questions" );
		Reporter.log("Step 5 : On Challenge exam question page , selecting answer and moving to next questions");
		
			//Selecting answer
			orexam.Answer11.click();
			Thread.sleep(4000);

			//Clicking next button
			SeleniumFunc.ClickOnElement("id", "submit");	
			Thread.sleep(4000);

		System.out.println("Step 5 : Log out and login again to application" );
		Reporter.log("Step 5 : Log out and login again to application");
			
			SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");
			Thread.sleep(4000);

			SeleniumFunc.ToGoToUrl(Constants.LoginPage_Offroad);
			login.EnterUsername(username);
			login.EnterPassword("clarion@123");
			login.ClickOnLogInButton();
			Thread.sleep(4000);

			//verifying page header
			if(orexam.PageHeader.getText().trim().equals("Oregon Challenge Exam") )
			{
				System.out.println("-- User is navigated to Oregon Challenge Exam"  );
				Reporter.log("-- User is navigated to Oregon Challenge Exam");
			}
			else
			{
				System.out.println("-- User is NOT navigated to Oregon Challenge Exam and navigated to : " + driver.getCurrentUrl()  );
				Reporter.log("-- User is NOT navigated to Oregon Challenge Exam and navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			//Verifying text
			String expectedtext = "You are currently in the middle of this exam. Please continue to finish the exam.";
			
			if(orexam.ResumeExamPageTxt.getText().trim().contains(expectedtext))
			{
				System.out.println("-- User is navigated to Oregon Challenge Exam and asked to resume exam"  );
				Reporter.log("-- User is navigated to Oregon Challenge Exam and asked to resume exam");
			}
			else
			{
				System.out.println("-- Text incorrect" );
				Reporter.log("-- Text incorrect");
				AssertFailedCount++;
			}
			
			
			//Verifying Continue the Final Exam
			expectedtext = "Continue";
			
			if(orexam.Conti.getText().trim().contains(expectedtext))
			{
				System.out.println("-- 'Continue Oregon Challenge Exam ' button is present"  );
				Reporter.log("-- 'Continue Oregon Challenge Exam ' button is present");
			}
			else
			{
				System.out.println("-- 'Continue Oregon Challenge Exam ' button is NOT present" );
				Reporter.log("-- 'Continue Oregon Challenge Exam ' button is NOT present");
				AssertFailedCount++;
			}
		
		
		System.out.println("Step 7 : On Oregon Challenge Exam page, clicking 'Continue Oregon Challenge Exam' button" );
		Reporter.log("Step 7 : On Oregon Challenge page, clicking 'Continue Oregon Challenge Exam' button");
		
				orexam.Conti.click();
				Thread.sleep(4000);

				String actualtext = SeleniumFunc.GetElementText("css", "ul.breadcrumb");
				expectedtext = "Oregon Challenge Exam / Question 2 of 60";
					
				//verifying url
				if(actualtext.equals(expectedtext))
				{
					System.out.println("-- On click to the 'Continue Oregon Challenge Exam ' button, user is navigated to Question 2 page"  );
					Reporter.log("-- On click to  the 'Continue Oregon Challenge Exam ' button , user is navigated to Question 2 page");
				}
				else
				{
					System.out.println("-- On click to the 'Continue Oregon Challenge Exam ' button, user is not navigated to Question 2 page and navigated to : " + driver.getCurrentUrl()  );
					Reporter.log("-- On click to the 'Continue Oregon Challenge Exam ' button , user is not navigated to Question 2 page and navigated to :" + driver.getCurrentUrl());
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
	
	/*	Test 5
	Verify Oregon challenge exam is only for Oregon offroad Run
	*/
	
	@Test
	public void ChallengeOnlyForOregon() throws InterruptedException
	{
		System.out.println("====" + "\n" +
				"Test 5 : Challenge Exam : Verify that challenge exam is only for Oregon state"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			  "Test 5 : Challenge Exam : Verify that challenge exam is only for Oregon state"  + "\n" +
			 	  "====");	
	
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			RegistrationPage register = new RegistrationPage(driver);
	
	
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
		
			String username= "exams" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "exam" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
		
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Offroad + Constants.State_Offroad);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Offroad + Constants.State_Offroad);
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
						
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}		
			
			if(SeleniumFunc.IsElementPresent("css", ".span5>h2"))
			{
				System.out.println("-- Challenge exam is also there for other states"  );
				Reporter.log("-- Challenge exam is also there for other states");
				AssertFailedCount++;
			}
			else
			{
				System.out.println("-- Challenge exam is only for oregon state"  );
				Reporter.log("-- Challenge exam is only for oregon state");
			 
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
