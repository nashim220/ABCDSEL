package courses.BoatEd;

import org.openqa.selenium.JavascriptExecutor;
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
import pageFactory.Courses.FinalExamPage;
import pageFactory.Courses.HomePage;
import pageFactory.Courses.KECourseAdminPage;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.PaymentInformationPage;
import pageFactory.Courses.PracticeExamPage;
import pageFactory.Courses.QuizPage;
import pageFactory.Courses.RegistrationPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class CanadaTest 
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
	 * Canada - Verify State Agency name on /canada page
	 * */ 
	@Test
	private void Canada_VerifyAgencyName() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Canada - Verify State Agency name on /canada page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Canada - Verify State Agency name on /canada page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HomePage home = new HomePage(driver);
		PageHeader header = new PageHeader(driver);

		
		System.out.println("Step 1: Navigated to /canada page and verifying State Agency name");
		Reporter.log("Step 1: Navigated to /canada page and verifying State Agency name"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/canada");
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/canada");
				Thread.sleep(4000);
			}
		
			//verifying State Agency name
			String expectedtext = "Transport Canada, Marine Safety and Security";
			String actualtext = home.AgencyName_New.getText();
			System.out.println(actualtext);

			if(actualtext.contains(expectedtext) )
			{
				System.out.println("Success!! State Agency name is correct i.e. " + actualtext);
				Reporter.log("Success!! State Agency name is correct  i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! State Agency name is incorrect " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! State Agency name is incorrect " + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
				
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
	 * Canada - Verify that /canada is a timed course & user need to move in a sequence to take a course
	 * */ 
	@Test
	private void Canada_TimedCourseVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Canada - Verify that /canada is a timed course & user need to move in a sequence to take a course"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Canada - Verify that /canada is a timed course & user need to move in a sequence to take a course"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ContentsPage content = new ContentsPage(driver);
		
		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/canada");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/canada"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/canada");
		
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/canada");
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
			Thread.sleep(8000);
				
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
			
			String username="canada"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			System.out.println(username);
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1980", emailaddress, emailaddress);
			register.SelectGender("Male");
		
			register.EnterMailingAddress_Canada("Mailing Address Line 1", "Line2", "New York", "British Columbia", "X0X 0X0", "Canada", "234-567-8910");
			register.ClickOnCreateAccount();
			
			Thread.sleep(8000);
															
		System.out.println("Step 4 : Clicking on 'I Understand' and 'Next','Got it' buttons on Timer pop up ");
		Reporter.log("Step 4 : Clicking on 'I Understand' and 'Next', 'Got it' buttons on Timer pop up"); 
	
			Thread.sleep(8000);
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}			
			
		System.out.println("Step 5 : Verifying whether timer is displayed on 'Next' button");
		Reporter.log("Step 5 : Verifying whether timer is displayed on 'Next' button"); 
		
				String ExpectedText = "Remaining";
				String ActualText= SeleniumFunc.GetElementText("css", "a.timer span").trim();
				System.out.println(ActualText);
				
	
				if(ActualText.contains(ExpectedText))
				{
					System.out.println("Success !! Timer is displayed");
					Reporter.log("Success !! Timer is displayed");
			
				}
				else
				{
					System.out.println("Failure !! Timer is not displayed \n Expected :"+ExpectedText+"\n"+ActualText );
					Reporter.log("Failure !! Timer is not displayed \n Expected :"+ExpectedText+"\n"+ActualText );
	
					AssertFailedCount++;				
				}				
				
		System.out.println("Step 6 : Navigating back to Contents page and Expanding Unit 1, Expanding Topic 3 , clicking on Page 1 link");
		Reporter.log("Step 6 : Navigating back to Contents page and Expanding Unit 1, Expanding Topic 3 , clicking on Page 1 link"); 
			
		   if(SeleniumFunc.IsElementPresent(content.IntroModal))
		   {
			content.IntroModal.click();
			Thread.sleep(4000);
		   }
		
			header.Contents.click();
			Thread.sleep(5000);
 
			try
			{
			content.Untimed_GetUnitHrefLink(1, 3, 1);
			content.Untimed_ClickOnUnitTopicPageLink(1,3,1);
			Thread.sleep(5000);
			
			System.out.println("Failure !! User is navigated to Unit 1 > Topic 3 > Page 1");
			Reporter.log("Failure !! User is navigated to Unit 1 > Topic 3 > Page 1");
			AssertFailedCount++;
			
			}
			catch(Exception e)
			{
				System.out.println("Success !! User is NOT navigated to Unit 1 > Topic 3 > Page 1" );
				Reporter.log("Success !! User is NOT navigated to Unit 1 > Topic 3 > Page 1");		
						
			}
			
		System.out.println("Step 7 : Navigating back to Contents page and Expanding Unit 1, Expanding Topic 1 , clicking on Page 1 link");
		Reporter.log("Step 7 : Navigating back to Contents page and Expanding Unit 1, Expanding Topic 1 , clicking on Page 1 link"); 
			
		
		    if(SeleniumFunc.IsElementPresent(content.IntroModal))
		   {
			content.IntroModal.click();
			Thread.sleep(4000);
		   }
		    
			header.Contents.click();
			Thread.sleep(5000);

			try
			{
				String expectedurl = content.Timed_GetUnitHrefLink(1, 1, 1)+"/";
				content.ContinueWhereYouLeftOff.click();
				Thread.sleep(5000);
				
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
			

			}
			catch(Exception e)
			{
				System.out.println("Failure !! User is NOT navigated to Unit 1 > Topic 1 > Page 1");
				Reporter.log("Failure !! User is NOT navigated to Unit 1 > Topic 1 > Page 1");
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
	 * Canada - Verify that study guide is accessible without registration
	 * */ 
	@Test
	private void Canada_Navigate_To_StudyGuide_Page() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Canada - Verify that study guide is accessible without registration"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Canada - Verify that study guide is accessible without registration"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		
		System.out.println("Step 1: Navigated to /canada page ");
		Reporter.log("Step 1: Navigated to /canada page"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/canada");
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/canada");
				Thread.sleep(4000);
			}
	
		System.out.println("Step 2 : Verifying whether 'Study Guild' link is present on top header");
		Reporter.log("Step 2 : Verifying whether 'Study Guild' link is present on top header");
		
			try
			{
				String actualtext = header.StudyGuide_New.getText();
				String expectedtext = "Study Guide";
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! 'Study Guide' link is present on page header");
					Reporter.log("-- Success !! 'Study Guide' link is present on page header");
					
					System.out.println("Step 3 : Click on 'Study Guide' link");
					Reporter.log("Step 3 : Click on 'Study Guide' link");
						
						header.StudyGuide_New.click();
						Thread.sleep(3000);
						
						actualtext = driver.getCurrentUrl();
						expectedtext = Constants.ApplicationURL.substring(5) + "/canada" + "/studyGuide/";
						System.out.println(expectedtext);
						
						if(actualtext.contains(expectedtext))
						{
							System.out.println("-- Success !! User is navigated to Study Guide page successfully");
							Reporter.log("-- Success !! User is navigated to Study Guide page successfully");
							
						}
						else
						{
							System.out.println("-- Failure !! User is NOT navigated to Study Guide page and navigated to :" +actualtext);
							Reporter.log("-- Failure !! User is NOT navigated to Study Guide page and navigated to :" +actualtext);
							AssertFailedCount++;
						}
						
				}
				else
				{
					System.out.println("-- Failure !! 'Study Guide' link is NOT present on page header");
					Reporter.log("-- Failure !! 'Study Guide' link is NOT present on page header");
					AssertFailedCount++;
				}
				
			}
			catch(Exception e)
			{
				System.out.println("--  Failure !! 'Study Guide' link is NOT present on page header");
				Reporter.log("--  Failure !! 'Study Guide' link is NOT present on page header");
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
	 * Canada -  Student Requirements - Verify that user can sign up with International Address
	 * */ 
	@Test
	private void Canada_Stds_Req_International_Address() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Canada -  Student Requirements - Verify that user can sign up with International Address"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Canada -  Student Requirements - Verify that user can sign up with International Address"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		RegistrationPage register = new RegistrationPage(driver);
		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/canada");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/canada"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/canada");
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/canada");
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
				
		System.out.println("Step 2 : Register as a New user with Internation Address");
		Reporter.log("Step 2 : Register as a New user with Internation Address"); 
			
			String username="canada"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1980", emailaddress, emailaddress);
			register.SelectGender("Male");
		
			register.MailingAddressLine1.sendKeys("Mailing Address Line 1");
			register.MailingAddressLine2.sendKeys("Line2");
			register.MailingAddressCity.sendKeys("Pune");
		
			new Select(register.MailingAddressCountry).selectByVisibleText("India");
			register.MailingAddressRegionOrLocality.sendKeys("Maharastra");
			register.MailingAddressZip.sendKeys("12345");
			register.MailingAddressPhoneNumber.sendKeys("234-567-8910");
		
			register.ClickOnCreateAccount();
			
			Thread.sleep(10000);
			
		System.out.println("Step 3 : Verifying whether user is registered successfully or not");
		Reporter.log("Step 3 : Verifying whether user is registered successfully or not"); 
				
			if(SeleniumFunc.IsElementPresent("css", "a[href='/site/logout']"))
				{
					
					System.out.println("Success !! 'Save & Log Out' link is present ");
					Reporter.log("Success !! 'Save & Log Out' link is present"); 
				}
				else
				{
					System.out.println("Failure !! 'Save & Log Out' link is NOT present ");
					Reporter.log("Failure !! 'Save & Log Out' link is NOT present"); 
					AssertFailedCount++;
				}
						
			String ActualUserName=SeleniumFunc.GetElementText("css", "a[href='/register/edit']").trim();
			System.out.println(ActualUserName);
			
			if(ActualUserName.equals(username))
			{
				
				System.out.println("Success !! Username is correct");
				Reporter.log("Success !! Username is correct"); 
			}
			else
			{
				System.out.println("Failure !!Username is incorrect");
				Reporter.log("Failure !! Username is incorrect"); 
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
	 * Canada -  Student Requirements - Verify that no age limit for sign up 
	 * */ 
	@Test
	private void Canada_Stds_Req_NoAgeLimit() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Canada -  Student Requirements - Verify that no age limit for sign up "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Canada -  Student Requirements - Verify that no age limit for sign up "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		RegistrationPage register = new RegistrationPage(driver);
		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/canada");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/canada"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/canada");
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/canada");
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
				
		System.out.println("Step 2 : Register as a New user with DOB=1 Jan 2014");
		Reporter.log("Step 2 : Register as a New user with DOB=1 Jan 2014"); 
			
			String username="canada"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "2014", emailaddress, emailaddress);
			register.SelectGender("Male");
			register.EnterMailingAddress_Canada("Mailing Address Line 1", "Line2", "New York", "British Columbia", "X0X 0X0", "Canada", "234-567-8910");
			register.ClickOnCreateAccount();
			
			Thread.sleep(10000);
			
		System.out.println("Step 3 : Verifying whether user is registered successfully or not");
		Reporter.log("Step 3 : Verifying whether user is registered successfully or not"); 
			
			
			if(SeleniumFunc.IsElementPresent("css", "a[href='/site/logout']"))
				{
					
					System.out.println("Success !! 'Save & Log Out' link is present ");
					Reporter.log("Success !! 'Save & Log Out' link is present"); 
				}
				else
				{
					System.out.println("Failure !! 'Save & Log Out' link is NOT present ");
					Reporter.log("Failure !! 'Save & Log Out' link is NOT present"); 
					AssertFailedCount++;
				}
				
			
			String ActualUserName=SeleniumFunc.GetElementText("css", "a[href='/register/edit']").trim();
			System.out.println(ActualUserName);
			
			if(ActualUserName.equals(username))
			{
				
				System.out.println("Success !! Username is correct");
				Reporter.log("Success !! Username is correct"); 
			}
			else
			{
				System.out.println("Failure !!Username is incorrect");
				Reporter.log("Failure !! Username is incorrect"); 
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
	 * Canada -  Verify that student is asked for payment only once user has passed the online exam
	 * */ 
	@Test
	private void Canada_AskedForPaymentAfterPassingExam() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Canada -  Verify that student is asked for payment only once user has passed the online exam"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Canada -  Verify that student is asked for payment only once user has passed the online exam"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		ContentsPage content = new ContentsPage(driver);
		QuizPage quiz = new QuizPage(driver);
		PracticeExamPage practiceexam = new PracticeExamPage(driver);
		
		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/canada");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/canada"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/canada");
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/canada");
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
				
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user "); 
			
			String username="canada"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1990", emailaddress, emailaddress);
			register.SelectGender("Male");
			register.EnterMailingAddress_Canada("Mailing Address Line 1", "Line2", "New York", "British Columbia", "X0X 0X0", "Canada", "234-567-8910");
			register.ClickOnCreateAccount();
			
			Thread.sleep(10000);
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			   {
				content.IntroModal.click();
				Thread.sleep(4000);
			   }		
			Thread.sleep(4000);
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();			
			
		System.out.println("Step 3 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page");
		Reporter.log("Step 3 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			Thread.sleep(4000);

			keadmin.StudentSearchAndActivity(username, "Boat", "Progress");
			keadmin.Progress_SelectAllUnitAndMarkAsComplete();
			Thread.sleep(4000);

			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

				
		System.out.println("Step 4 : Marking all Units/Quiz as completed" );
		Reporter.log("Step 4 : Marking all Units/Quiz as completed");
					
			for(int i=1;i<7;i++)
			{
				content.ContinueWhereYouLeftOff.click();
				Thread.sleep(4000);

				quiz.ClickOnNextButton();
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(Constants.AdminURL);
				Thread.sleep(4000);

				keadmin.StudentSearchAndActivity(username, "Boat", "Quizzes");
				keadmin.Quizzes_PassAQuiz(i);
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(ContentPageUrl);
				Thread.sleep(4000);

			}
		
	
		System.out.println("Step 5 : Navigating to Final Exam page" );
		Reporter.log("Step 5 : Navigating to Final Exam page");	
			
			ContentPageUrl = driver.getCurrentUrl();
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(8000);

			practiceexam.FinalExam.click();
			Thread.sleep(4000);

			finalexam.FinalExam.click();
			Thread.sleep(4000);
			
			
		System.out.println("Step 5 : Go to Admin , and passing Final Exam" );
		Reporter.log("Step 5 : Go to Admin , and passing Final Exam");
		
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			Thread.sleep(4000);

			keadmin.StudentSearchAndActivity(username, "Boat", "Final Exam");
			keadmin.FinalExam_Pass_Canada();
			Thread.sleep(4000);

			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			
			Thread.sleep(4000);

	
		System.out.println("Step 6 : Verifying whether user is asked to 'Complete Registration and Payment' on login " );
		Reporter.log("Step 6 : Verifying whether user is asked to 'Complete Registration and Payment' on login ");
		
			String expectedtext = "Congratulations, you have successfully completed the Canada Boat Ed Course.";
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
			
			
			expectedtext = "In order to receive your Temporary Pleasure Craft Operator Card you must pay for your registration.";
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
	
			
		System.out.println("Step 7 : Clicking on 'Complete Your Registration and Payment' button" );
		Reporter.log("Step 7 : Clicking on 'Complete Your Registration and Payment' button");
		
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
	
	
	/* Test 7: 
	 * Canada -  Verify the course fee and taxes
	 * */ 
	@Test
	private void Canada_VerifyFeeAndTaxes() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Canada -  Verify the course fee and taxes"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Canada -  Verify the course fee and taxes"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		ContentsPage content = new ContentsPage(driver);
		QuizPage quiz = new QuizPage(driver);
		PracticeExamPage practiceexam = new PracticeExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		
		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/canada");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/canada"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/canada");
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/canada");
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
				
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user "); 
			
			String username="canada"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			System.out.println(username);
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1990", emailaddress, emailaddress);
			register.SelectGender("Male");
			
			register.EnterMailingAddress_Canada("Mailing Address Line 1", "Line2", "New York", "British Columbia", "X0X 0X0", "Canada", "234-567-8910");
			register.ClickOnCreateAccount();
			Thread.sleep(4000);

			Thread.sleep(8000);
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			 {
				content.IntroModal.click();
				Thread.sleep(4000);
			 }			
			Thread.sleep(4000);
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();
			
		System.out.println("Step 3 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page");
		Reporter.log("Step 3 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			Thread.sleep(4000);

			keadmin.StudentSearchAndActivity(username, "Boat", "Progress");
			keadmin.Progress_SelectAllUnitAndMarkAsComplete();
			Thread.sleep(4000);

			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

		System.out.println("Step 4 : Marking all Units/Quiz as completed" );
		Reporter.log("Step 4 : Marking all Units/Quiz as completed");
					
			for(int i=1;i<7;i++)
			{
				content.ContinueWhereYouLeftOff.click();
				Thread.sleep(4000);

				quiz.ClickOnNextButton();
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(Constants.AdminURL);
				Thread.sleep(4000);

				keadmin.StudentSearchAndActivity(username, "Boat", "Quizzes");
				keadmin.Quizzes_PassAQuiz(i);
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(ContentPageUrl);
				Thread.sleep(4000);

			}
		
	
		System.out.println("Step 5 : Navigating to Fianl Exam page" );
		Reporter.log("Step 5 : Navigating to FianlExam page");	
		
			ContentPageUrl = driver.getCurrentUrl();
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

			practiceexam.FinalExam.click();
			Thread.sleep(4000);

			finalexam.FinalExam.click();
			Thread.sleep(4000);

			
			
		System.out.println("Step 5 : Go to Admin , and passing Final Exam" );
		Reporter.log("Step 5 : Go to Admin , and passing Final Exam");
		
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			Thread.sleep(4000);

			keadmin.StudentSearchAndActivity(username, "Boat", "Final Exam");
			keadmin.FinalExam_Pass_Canada();
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

	
		System.out.println("Step 6 : Verifying whether user is asked to 'Complete Registration and Payment' on login " );
		Reporter.log("Step 6 : Verifying whether user is asked to 'Complete Registration and Payment' on login ");
		
			String expectedtext = "Congratulations, you have successfully completed the Canada Boat Ed Course.";
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
			
			
			
		System.out.println("Step 7 : Clicking on 'Complete Your Registration and Payment' button" );
		Reporter.log("Step 7 : Clicking on 'Complete Your Registration and Payment' button");
		
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
			
			
		System.out.println("Step 8 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 8 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
		
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
			
			
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
		
		System.out.println("Step 8 : On Payment Information page - Stage 2 , , verfiying course fee and taxes" );
		Reporter.log("Step 8 : On Payment Information page - Stage 2 , , verfiying course fee and taxes");
		
			//Verifying page header
			
			expectedtext = "Your Payment for the Pleasure Craft Operator Card";
			actualtext = payinfo.PageHeader.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Page header is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Page header is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Page header is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Page header is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
		
			
			
			
			//Verifying Course Fee Header
			expectedtext = "Pleasure Craft Operator Card Fee";
			actualtext = payinfo.CourseFee_Header.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Course Fee Header is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Course Fee Header is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Course Fee Header is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Course Fee Header is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
		
			
			//Verifying Course Fee
			expectedtext = "$39.95 CAD";
			actualtext = payinfo.CourseFee_Fee.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Course Fee  is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Course Fee is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Course Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Course Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
		
			
			//Verifying Course Total Fee
			expectedtext = "$39.95 CAD";
			actualtext = payinfo.Total_Fee.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Course Total Fee  is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Course Total Fee is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Course Total Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Course Total Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
	
	
	/* Test 8: 
	 * Canada - Verify the course has a Practice Exam
	 * */ 
	@Test
	private void Canada_VerifyPraticeExam() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Canada - Verify the course has a Practice Exam"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Canada - Verify the course has a Practice Exam"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		ContentsPage content = new ContentsPage(driver);
		QuizPage quiz = new QuizPage(driver);
		PracticeExamPage practiceexam = new PracticeExamPage(driver);
		
		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/canada");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/canada"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/canada");
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/canada");
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
				
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user "); 
			
			String username="canada"+ JH.GenerateRandomNumber();
			System.out.println(username);
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1990", emailaddress, emailaddress);
			register.SelectGender("Male");
			register.EnterMailingAddress_Canada("Mailing Address Line 1", "Line2", "New York", "British Columbia", "X0X 0X0", "Canada", "234-567-8910");
			register.ClickOnCreateAccount();
			
			Thread.sleep(8000);
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			 {
				content.IntroModal.click();
				Thread.sleep(4000);
			 }		
			Thread.sleep(4000);
			header.Contents.click();
			Thread.sleep(4000);
	
			String ContentPageUrl = driver.getCurrentUrl();			
			
		System.out.println("Step 3 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page");
		Reporter.log("Step 3 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			Thread.sleep(4000);

			keadmin.StudentSearchAndActivity(username, "Boat", "Progress");
			keadmin.Progress_SelectAllUnitAndMarkAsComplete();
			Thread.sleep(4000);

			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

				
		System.out.println("Step 4 : Marking all Units/Quiz as completed" );
		Reporter.log("Step 4 : Marking all Units/Quiz as completed");
					
			for(int i=1;i<7;i++)
			{
				content.ContinueWhereYouLeftOff.click();
				Thread.sleep(4000);

				quiz.ClickOnNextButton();
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(Constants.AdminURL);
				Thread.sleep(4000);

				keadmin.StudentSearchAndActivity(username, "Boat", "Quizzes");
				keadmin.Quizzes_PassAQuiz(i);
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(ContentPageUrl);
				Thread.sleep(4000);

			}
		
	
		System.out.println("Step 5 : Navigating to Practice Exam page" );
		Reporter.log("Step 5 : Navigating to Practice Exam page");	
			
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(8000);

			
		System.out.println("Step 6 : Verifying UI elements on Practice Exam page" );
		Reporter.log("Step 6 : Verifying UI elements on Practice Exam page");
		
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
			
			
			//verifying 'Take the Practice Exam' button
			if(practiceexam.TakePracticeExam.getText().trim().contains("Begin the Practice Exam →"))
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
	
	
	/* Test 9: 
	 * Canada - Viewing the coupon on the Canada Discount page
	 * */ 
	@Test
	private void Canada_ViewingCouponDiscount() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Canada - Viewing the coupon on the Canada Discount page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 : Canada - Viewing the coupon on the Canada Discount page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		
	
		System.out.println("Step 1 : Navigate to KE admin > Payment Codes and Editing payment code with name = 'Canada discount page coupon code'");
		Reporter.log("Step 1 : Navigate to KE admin > Payment Codes and Editing payment code with name = 'Canada discount page coupon code'");
		
			keadmin.PaymentCodes_Search_Edit("Canada discount page coupon code", "TEST123", "PERCENT_DISCOUNT", "25", "04/08/2015", "09/15/2022");
			
			Thread.sleep(4000);

		System.out.println("Step 2 : Navigate to  https://qa1.boat-ed.com/canada/e2dc6c48c56de466f6d13781796abf3d and verifying Discount details");
		Reporter.log("Step 2 : Navigate to  https://qa1.boat-ed.com/canada/e2dc6c48c56de466f6d13781796abf3d and verifying Discount details");
		
			SeleniumFunc.ToGoToUrl("https://qa1.boat-ed.com/canada/e2dc6c48c56de466f6d13781796abf3d");
			Thread.sleep(4000);

			String expectedtext = 
					"$39.95 CAD" + "\n" + 
					"Now $29.96 CAD" + "\n" +
					"25% discount off the regular price" + "\n" +
					"when you use code TEST123" + "\n" +
					"pay only when you pass" ;
					
			String actualtext = SeleniumFunc.GetElementText("css", "div.sign-up h2").trim() + "\n" + SeleniumFunc.GetElementText("css", "div.sign-up h3").trim();	
			
		
			if(actualtext.contains(expectedtext))
			{
				System.out.println("--  Discount details are correct i.e. " +  actualtext );
				Reporter.log("-- Discount details are correct i.e. " +  actualtext );
			}
			else
			{
				System.out.println("--  Discount details are incorrect . Actual Text is = " +  actualtext  + "\n" + "Expected Text is = " + expectedtext);
				Reporter.log("--  Discount details are incorrect . Actual Text is = " +  actualtext  + "\n" + "Expected Text is = " + expectedtext);
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
	 * Canada - Disabling the coupon on the Canada Discount page
	 * */ 
	@Test
	private void Canada_DisablingCouponDiscount() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : Canada - Disabling the coupon on the Canada Discount page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10 : Canada - Disabling the coupon on the Canada Discount page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		
	
		System.out.println("Step 1 : Navigate to KE admin > Payment Codes and Editing payment code with name = 'Canada discount page coupon code'");
		Reporter.log("Step 1 : Navigate to KE admin > Payment Codes and Editing payment code with name = 'Canada discount page coupon code'");
		
			keadmin.PaymentCodes_Search_Edit("Canada discount page coupon code", "TEST123", "PERCENT_DISCOUNT", "25", "04/08/2014", "04/08/2014");
			Thread.sleep(4000);

		
		System.out.println("Step 2 : Navigate to  https://qa1.boat-ed.com/canada/e2dc6c48c56de466f6d13781796abf3d and verifying Discount details");
		Reporter.log("Step 2 : Navigate to  https://qa1.boat-ed.com/canada/e2dc6c48c56de466f6d13781796abf3d and verifying Discount details");
		
			SeleniumFunc.ToGoToUrl("https://qa1.boat-ed.com/canada/e2dc6c48c56de466f6d13781796abf3d");
			Thread.sleep(4000);

			String expectedtext = 
					"everyday low price" + "\n" + 
					"no coupon needed" + "\n" +
					"pay only when you pass" ;
					
			String actualtext = SeleniumFunc.GetElementText("css", "div.sign-up h3").trim();	
			
		
			if(actualtext.contains(expectedtext))
			{
				System.out.println("--  Discount details are correct i.e. " +  actualtext );
				Reporter.log("-- Discount details are correct i.e. " +  actualtext );
			}
			else
			{
				System.out.println("--  Discount details are incorrect . Actual Text is = " +  actualtext  + "\n" + "Expected Text is = " + expectedtext);
				Reporter.log("--  Discount details are incorrect . Actual Text is = " +  actualtext  + "\n" + "Expected Text is = " + expectedtext);
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


//test



/* Test 11: 
 * Canada -  Verify the Apply code is not displayed on Canada payment step 2 
 * */ 
@Test
private void Canada_VerifyApplyCodeNotDisplayed() throws Exception
{
	System.out.println("====" + "\n" +
				"Test 7 : Canada -  Verify Apply code is not displayed on Canada payment step 2 "  + "\n" +
	 			"====");
	Reporter.log("====" + "\n" +
	 			  "Test 7 : Canada -  Verify Apply code is not displayed on Canada payment step 2 "  + "\n" +
			 	  "====");	
	 
	int AssertFailedCount=0 ;
	SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	PageHeader header = new PageHeader(driver);
	RegistrationPage register = new RegistrationPage(driver);
	KECourseAdminPage keadmin = new KECourseAdminPage(driver);
	FinalExamPage finalexam = new FinalExamPage(driver);
	ContentsPage content = new ContentsPage(driver);
	QuizPage quiz = new QuizPage(driver);
	PracticeExamPage practiceexam = new PracticeExamPage(driver);
	CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
	PaymentInformationPage payinfo = new PaymentInformationPage(driver);
	
	
	System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/canada");
	Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/canada"); 
	
		SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/canada");
		Thread.sleep(4000);
		if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
		{
			header.Logout_Handgun.click();
			Thread.sleep(4000);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/canada");
			Thread.sleep(4000);
		}
		
		header.Register_New.click();
	
		Thread.sleep(8000);
			
	System.out.println("Step 2 : Register as a New user");
	Reporter.log("Step 2 : Register as a New user "); 
		
		String username="canada"+ JH.GenerateRandomNumber();
		String emailaddress = username + "@mailinator.com";
		System.out.println(username);
		register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1990", emailaddress, emailaddress);
		register.SelectGender("Male");
		
		register.EnterMailingAddress_Canada("Mailing Address Line 1", "Line2", "New York", "British Columbia", "X0X 0X0", "Canada", "234-567-8910");
		register.ClickOnCreateAccount();
		Thread.sleep(4000);

		Thread.sleep(10000);
		
		if(SeleniumFunc.IsElementPresent(content.IntroModal))
		 {
			content.IntroModal.click();
			Thread.sleep(4000);
		 }			
		Thread.sleep(4000);
		header.Contents.click();
		Thread.sleep(4000);

		String ContentPageUrl = driver.getCurrentUrl();
		
	System.out.println("Step 3 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page");
	Reporter.log("Step 3 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page"); 
		
		SeleniumFunc.ToGoToUrl(Constants.AdminURL);
		Thread.sleep(4000);

		keadmin.StudentSearchAndActivity(username, "Boat", "Progress");
		keadmin.Progress_SelectAllUnitAndMarkAsComplete();
		Thread.sleep(4000);

		SeleniumFunc.ToGoToUrl(ContentPageUrl);
		Thread.sleep(4000);

	System.out.println("Step 4 : Marking all Units/Quiz as completed" );
	Reporter.log("Step 4 : Marking all Units/Quiz as completed");
				
		for(int i=1;i<7;i++)
		{
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

			quiz.ClickOnNextButton();
			Thread.sleep(4000);

			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			Thread.sleep(4000);

			keadmin.StudentSearchAndActivity(username, "Boat", "Quizzes");
			keadmin.Quizzes_PassAQuiz(i);
			Thread.sleep(4000);

			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

		}
	

	System.out.println("Step 5 : Navigating to Fianl Exam page" );
	Reporter.log("Step 5 : Navigating to FianlExam page");	
	
		ContentPageUrl = driver.getCurrentUrl();
		content.ContinueWhereYouLeftOff.click();
		Thread.sleep(4000);

		practiceexam.FinalExam.click();
		Thread.sleep(4000);

		finalexam.FinalExam.click();
		Thread.sleep(4000);

		
		
	System.out.println("Step 5 : Go to Admin , and passing Final Exam" );
	Reporter.log("Step 5 : Go to Admin , and passing Final Exam");
	
	
		SeleniumFunc.ToGoToUrl(Constants.AdminURL);
		Thread.sleep(4000);

		keadmin.StudentSearchAndActivity(username, "Boat", "Final Exam");
		keadmin.FinalExam_Pass_Canada();
		SeleniumFunc.ToGoToUrl(ContentPageUrl);
		Thread.sleep(4000);


	System.out.println("Step 6 : Verifying whether user is asked to 'Complete Registration and Payment' on login " );
	Reporter.log("Step 6 : Verifying whether user is asked to 'Complete Registration and Payment' on login ");
	
		String expectedtext = "Congratulations, you have successfully completed the Canada Boat Ed Course.";
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
		
		
		
	System.out.println("Step 7 : Clicking on 'Complete Your Registration and Payment' button" );
	Reporter.log("Step 7 : Clicking on 'Complete Your Registration and Payment' button");
	
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
		
		
	System.out.println("Step 8 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
	Reporter.log("Step 8 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
	
	
		cerinfo.ConfirmCertificateInformation.click();
		Thread.sleep(4000);
		
		
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
	
	System.out.println("Step 9 : On Payment Information page - Stage 2 , , verfiying Apply code not displayed" );
	Reporter.log("Step 9 : On Payment Information page - Stage 2 , , verfiying Apply code not displayed");
	
		//Verifying Apply coupon 
	if (SeleniumFunc.IsElementPresent(payinfo.ApplyCode))
	{
		System.out.println("Failure!!! There is Apply Code displayed on Canada Payment Information page - Stage 2");
		Reporter.log("Failure!!! There is Apply Code displayed on Canada Payment Information page - Stage 2");
	}
	else
	{
		System.out.println("Success!!! There is no Apply Code displayed on Canada Payment Information page - Stage 2");
		Reporter.log("Success!!! There is no Apply Code displayed on Canada Payment Information page - Stage 2");
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
 * Boat > Canada > Province pages > Presence of Trustpilot testimonials
 * */ 
@Test
private void CanadaProvinceTrustPilot() throws Exception
{
	System.out.println("====" + "\n" +
				"Test 3 : Boat > Canada > Province pages > Presence of Trustpilot testimonials"  + "\n" +
	 			"====");
	Reporter.log("====" + "\n" +
	 			  "Test 3 : Boat > Canada > Province pages > Presence of Trustpilot testimonials"  + "\n" +
			 	  "====");	
	
	int AssertFailedCount=0 ;
	SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	PageHeader header = new PageHeader(driver);

	String url = "/canada/";
	
	System.out.println("Step 1: Navigating to /canada/ and verifying 'Province' specific links");
	Reporter.log("Step 1: Navigating to /canada/ and verifying 'Province' specific links"); 
		
		
		SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + url);
		Thread.sleep(4000);
		if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
		{
			header.Logout_Handgun.click();
			Thread.sleep(4000);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + url);
			Thread.sleep(4000);
		}
		//Verifying whether 'Boating in Your Province' section is present or not
		
		String actualtext = SeleniumFunc.GetElementText("css", "#steps div:nth-of-type(1) nav h2");
		System.out.println(actualtext);
		String expectedtext ="Boating in Your Province";

		if(actualtext.contains(expectedtext) )
		{
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 500);");
			
			System.out.println("Success!! 'Boating in Your Province' section is present i.e. " + actualtext);
			Reporter.log("Success!! 'Boating in Your Province' section is present i.e. " + actualtext); 
			
			String[] provincenames = {"Alberta", "British Columbia", "Manitoba", "New Brunswick", "Nova Scotia", "Ontario", "Saskatchewan"} ; 
			String[] provinceurls = {"/canada/alberta", "/canada/britishcolumbia", "/canada/manitoba","/canada/newbrunswick", "/canada/novascotia", "/canada/ontario", "/canada/saskatchewan"} ; 
			
			
			for(int i=0; i<provincenames.length ;i++)
			{						Thread.sleep(4000);

				//Verifying Province and it's link
				int j=i+1;
				Thread.sleep(3000);
							
				//Clicking on Province link
								
				Thread.sleep(4000);
				
				String cssselector = ".quick-links.list-inline li:nth-of-type(" + j + ") a";
				SeleniumFunc.ClickOnElement("css", cssselector);
				//System.out.println(cssselector);
				Thread.sleep(3000);
				 
				actualtext = driver.getCurrentUrl();
				/*System.out.println(actualtext);*/
				expectedtext = Constants.ApplicationURL + provinceurls[i];

				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! Province -> " + provincenames[i] + " has correct URL");
					Reporter.log("Success!! Province -> " + provincenames[i] + " has correct URL"); 
				}
				else
				{
					System.out.println("Failure !! Province -> " + provincenames[i] + " has incorrect URL" + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Province -> " + provincenames[i] + " has incorrect URL" + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
		System.out.println("Step 1: Verify Trust Pilot section is present at state landing page");
		Reporter.log("Step 1: Verify Trust Pilot section is present at state landing page"); 
								
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
					
				if(SeleniumFunc.IsElementPresent("css", "#marketing-trustpilot p"))
				{
					System.out.println("Success!! Trust Pilot section is present");
					Reporter.log("Success!! Trust Pilot section is present"); 
							
						//verifying Billboard text
						expectedtext = "Hunter Ed has a TrustPilot rating of";
						actualtext = SeleniumFunc.GetElementText("css", "#marketing-trustpilot p");
									
						if(actualtext.contains(expectedtext))
						{
							System.out.println("Success!! Trust Pilot section has correct text. i.e." + actualtext);
							Reporter.log("Success!! Trust Pilot section has correct text. i.e." + actualtext); 
						}
						else
						{
							System.out.println("Failure !! Trust Pilot section has incorrect text. i.e. " + "\n" + "Expected Text: "  
												+ "\n" + expectedtext + "\n" + 
												"Actual Text : " + "\n" +  actualtext);
							Reporter.log("Failure !! Trust Pilot section has incorrect text. i.e. " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									"Actual Text : " + "\n" +  actualtext);
					
							AssertFailedCount++;
						}
				}
				else
				{
					System.out.println("Failure !! Trust Pilot section is NOT present at state landing page");
					Reporter.log("Failure !! Trust Pilot section is NOT present at state landing page");
					
					AssertFailedCount++;
				}
					
				driver.navigate().back();
				Thread.sleep(4000);

			}	
		}
		else
		{
			System.out.println("Failure !! 'Boating in Your Province' section is NOT present " + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
			Reporter.log("Failure !! 'Boating in Your Province' section is NOT present" + "\n" + "Expected Text: "  
					+ "\n" + expectedtext + "\n" + 
					 "Actual Text : " + "\n" +  actualtext);
			
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