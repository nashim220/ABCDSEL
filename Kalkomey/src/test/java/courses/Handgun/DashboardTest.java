package courses.Handgun;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
	 * Verify user is asked to complete Quiz on login if logged out while attempting Quiz
	*/ 
	@Test
	private void Dashboard_ResumeQuiz() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify user is asked to complete Quiz on login if logged out while attempting Quiz"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1: Verify user is asked to complete Quiz on login if logged out while attempting Quiz"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);

		QuizPage quiz = new QuizPage(driver);
		LoginPage login  = new LoginPage(driver);
		ContentsPage content = new ContentsPage(driver);
	
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Handgun_national);
			PageHeader header = new PageHeader(driver); 
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Handgun_national);
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(10000);
			
			register.ClickOnIUnderstandButton();
			
			
			String gender= "Male";
			String addressline1 = "14086 Proton Rd";
			String addressline2 = "";
			String city= "Dallas";
			String state="Texas";
			String zip="75244";
			String country="United States of America";
			String phonenumber="234-567-8910";
			
			register.SelectGender(gender);
			register.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			Thread.sleep(10000);

			payinfo.EnterCCAndBillingDetails("test", "Patel", 
					"4111111111111111", "01", "2019", 
					"14086 Proton Rd.", "Peoria", "California", "75244", 
					 "United States of America", "410-477-3898");
			
			payinfo.ConfirmPaymentInformation.click();
			Thread.sleep(4000);
			payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
			Thread.sleep(4000);
			content.ClickOnContentLink();
			Thread.sleep(4000);
			String ContentPageUrl = SeleniumFunc.GetAttributeValue("css", "ul[class='nav pull-right'] li a", "href")+"/";
			Thread.sleep(10000);
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and marking Unit 1 as complete. Navigating back to Contents page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and marking Unit 1 as complete. Navigating back to Contents page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Handgun", "Progress");
			keadmin.Progress_SelectUnitAndMarkAsComplete(1);
			Thread.sleep(4000);

			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			
			Thread.sleep(5000);
			
		System.out.println("Step 3 : Clicking on 'Unit 1 Quiz' link");
		Reporter.log("Step 3 : Clicking on 'Unit 1 Quiz' link");
		
			SeleniumFunc.ClickOnElement("css", ".page.continue");
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
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_Handgun);
				
				Thread.sleep(4000);
				
				login.EnterUsername(username);
				login.EnterPassword(password);
				login.ClickOnLogInButton();
				
				//verifying whether user is asked to complete Quiz first
				
				Thread.sleep(4000);
				
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
	

	/* Test 2: 
	 * Verify that on re-login, user is landed on Course list page with "Continue Where you left off" button
	*/ 
	@Test
	private void Dashboard_ReLogin() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify that on re-login, user is landed on Course list page with 'Continue Where you left off' button"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify that on re-login, user is landed on Course list page with 'Continue Where you left off' button"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ContentsPage content = new ContentsPage(driver);
		LoginPage login = new LoginPage(driver);
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);

		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Handgun_national);
			PageHeader header = new PageHeader(driver);Thread.sleep(4000);
		
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Handgun_national);
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(10000);
			
			if(SeleniumFunc.IsElementPresent(register.ReminderModelConfirmationButton))
			register.ClickOnIUnderstandButton();
			
			String gender= "Male";
			String addressline1 = "14086 Proton Rd";
			String addressline2 = "";
			String city= "Dallas";
			String state="Texas";
			String zip="75244";
			String country="United States of America";
			String phonenumber="234-567-8910";
			
			register.SelectGender(gender);
			register.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			Thread.sleep(10000);

			payinfo.EnterCCAndBillingDetails("test", "Patel", 
					"4111111111111111", "01", "2019", 
					"14086 Proton Rd.", "Peoria", "California", "75244", 
					 "United States of America", "410-477-3898");
			
			payinfo.ConfirmPaymentInformation.click();
			Thread.sleep(4000);
			payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
			Thread.sleep(4000);
			
			
			
			content.ClickOnContentLink();
			Thread.sleep(4000);
			
			
		System.out.println("Step 2 : Clicking on 'Contents' link from top header");
		Reporter.log("Step 2 : Clicking on 'Contents' link from top header"); 
			
			
		System.out.println("Step 3 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button");
		Reporter.log("Step 3 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button"); 
			
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
		System.out.println("Step 5 : On Unit page, waiting for timer to over and then clicking on Next Button");
		Reporter.log("Step 5 : On Unit page, waiting for timer to over and then clicking on Next Button"); 
			
			Thread.sleep(35000);
			
			SeleniumFunc.ClickOnElement("css", "#course_controls a ");
			
			Thread.sleep(3000);
			
		
		System.out.println("Step 6 : Logout and login again");
		Reporter.log("Step 6 : Logout and login again"); 	
		
			SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_Handgun);
			
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
				
				//Verifying whether user is navigated to correct page or not
				
				String ExpectedText = "Page 2 of 2";
				String ActualText= SeleniumFunc.GetElementText("css", "#course_controls > li.control-center > ul > li:nth-child(3) > b");
				System.out.println(ActualText);
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
	
	
	
	/* Test 3: 
	 * Dashboard : Verify dashboard for the student who has finished the exam	 
	 * */ 
	@Test
	private void Dashboard_CompleteApplication() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Dashboard : Verify dashboard for the student who has finished the exam"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Dashboard : Verify dashboard for the student who has finished the exam"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		ContentsPage content = new ContentsPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		LoginPage login = new LoginPage(driver);
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);

		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Handgun_national);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
		
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Handgun_national);
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(4000);
			
			register.ClickOnIUnderstandButton();
			
			String gender= "Male";
			String addressline1 = "14086 Proton Rd";
			String addressline2 = "";
			String city= "Dallas";
			String state="Texas";
			String zip="75244";
			String country="United States of America";
			String phonenumber="234-567-8910";
			
			register.SelectGender(gender);
			register.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			Thread.sleep(10000);

			payinfo.EnterCCAndBillingDetails("test", "Patel", 
					"4111111111111111", "01", "2017", 
					"14086 Proton Rd.", "Peoria", "California", "75244", 
					 "United States of America", "410-477-3898");
			
			payinfo.ConfirmPaymentInformation.click();
			Thread.sleep(4000);
			payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
			Thread.sleep(4000);
			content.ClickOnContentLink();
			Thread.sleep(4000);
			String ContentPageUrl = SeleniumFunc.GetAttributeValue("css", "ul[class='nav pull-right'] li a", "href")+"/";
			Thread.sleep(10000);
			
		
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Handgun", "Fast Forward Pass");
			
		
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
			Thread.sleep(4000);

			SeleniumFunc.ToGoToUrl(Constants.LoginPage_Handgun);
			
			Thread.sleep(4000);
			
			login.EnterUsername(username);
			login.EnterPassword(password);
			login.ClickOnLogInButton();
				
			Thread.sleep(5000);
	
		
			String expectedtext = "You Have Completed the National Handgun Safety Course";
			String actualtext = SeleniumFunc.GetElementText("css", ".page-header>h1");
			
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
	 * Verify dashboard for user returning after payment
	 * */ 
	@Test
	private void Dashboard_VerifyAfterPayment() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 :  Verify dashboard after completing course"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 :  Verify dashboard after completing course"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		CourseCompletePage coursecomplete = new CourseCompletePage(driver);
		LoginPage login = new LoginPage(driver);
		ContentsPage content = new ContentsPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Handgun_national);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
		
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(6000);

				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Handgun_national);
				Thread.sleep(6000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(6000);
			
			register.ClickOnIUnderstandButton();
			String gender= "Male";
			String addressline1 = "14086 Proton Rd";
			String addressline2 = "";
			String city= "Dallas";
			String state="Texas";
			String zip="75244";
			String country="United States of America";
			String phonenumber="234-567-8910";
			
			register.SelectGender(gender);
			register.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			Thread.sleep(10000);

			payinfo.EnterCCAndBillingDetails("test", "Patel", 
					"4111111111111111", "01", "2017", 
					"14086 Proton Rd.", "Peoria", "California", "75244", 
					 "United States of America", "410-477-3898");
			
			payinfo.ConfirmPaymentInformation.click();
			Thread.sleep(4000);
			payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
			Thread.sleep(4000);
			content.ClickOnContentLink();
			Thread.sleep(4000);
			
			String ContentPageUrl = SeleniumFunc.GetAttributeValue("css", "ul[class='nav pull-right'] li a", "href")+"/";
			
	
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Handgun", "Fast Forward Pass");
			
			//Go back to Contents page
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);
			
			//Click on Continue Exam button
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(6000);

			//Selecting Answer for Question  and clicking on Next button
			
				//Selecting answer
				finalexam.Answer1.click();
				Thread.sleep(6000);

				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
				Thread.sleep(6000);

				
		System.out.println("Step 3 : Clicking on 'Get Voucher' button" );
		Reporter.log("Step 3 : Clicking on 'Get Voucher' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(5000);
			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Get Voucher' button is working as expected ");
				Reporter.log("-- Success !! 'Get Voucher' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Get Voucher' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Get Voucher' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 6 : Logout and login again , verifying dashboard");
		Reporter.log("Step 6 : Logout and login again , verifying dashboard"); 
			
			SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_Handgun);
			Thread.sleep(4000);
			
			login.EnterUsername(username);
			login.EnterPassword(password);
			login.ClickOnLogInButton();
			
			Thread.sleep(5000);
			
			//Verifying Url
				String expectedtext = Constants.ApplicationURL_Handgun + "/course/course_complete/601099/";
				String actualtext = driver.getCurrentUrl();
				
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! user is navigated to Course Compelte page i.e. " + actualtext );
					Reporter.log("-- Success !! user is navigated to Course Compelte page i.e. " + actualtext );
					
					expectedtext = "You Have Completed the National Handgun Safety Course";
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
		
			/*//Verifying presence of 'After This Course' section
		
				expectedtext = "After This Course";
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
				}*/
			
				
			//Verifying congratulation message
				
				expectedtext = "Congratulations, you have successfully completed the National Handgun Safety Course. You may now download your National Course Completion Certificate or have it sent to your registered e-mail address.";
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
				
				expectedtext = "Download the National Course Completion Certificate";
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
				
				expectedtext = "E-mail the National Course Completion Certificate";
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
				actualtext = coursecomplete.ReturnToCourseContent_Handgun.getText().trim();
				
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
