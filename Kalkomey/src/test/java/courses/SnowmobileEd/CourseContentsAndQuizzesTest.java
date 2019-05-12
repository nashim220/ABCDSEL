package courses.SnowmobileEd;

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

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pageFactory.Courses.ContentsPage;
import pageFactory.Courses.KECourseAdminPage;
import pageFactory.Courses.LoginPage;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.QuizPage;
import pageFactory.Courses.RegistrationPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class CourseContentsAndQuizzesTest 
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
	 * Verify clicking on 'Contents', user is navigated to Contents page 
	*/ 
	@Test
	private void Contents_Navigation() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 :Verify clicking on 'Contents', user is navigated to Contents page "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify clicking on 'Contents', user is navigated to Contents page "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ContentsPage content = new ContentsPage(driver);
		
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
		
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
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
					
		System.out.println("Step 2 : Clicking on 'Contents' link from top header and verifying whether user is navigated to Contents page or not");
		Reporter.log("Step 2 : Clicking on 'Contents' link from top header and verifying whether user is navigated to Contents page or not"); 
			
			Thread.sleep(7000);
			String ExpectedUrl = driver.getCurrentUrl();
			System.out.println(ExpectedUrl);
			content.ClickOnContentLink();
			Thread.sleep(4000);

			String ActualUrl = driver.getCurrentUrl();
			System.out.println(ActualUrl);
			

			if(ActualUrl.equals(ExpectedUrl))
			{
				System.out.println("Success !! User is navigated to Contents page"); 
				Reporter.log("Success !! User is navigated to Contents page"); 
			}
			else
			{
				System.out.println("Failure !! Success !! User is NOT navigated to Contents page and navigated to : " + ActualUrl);
				Reporter.log("Failure !! Success !! User is NOT navigated to Contents page and navigated to : " + ActualUrl);
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
	 * Verify that the link where user has to start again is displayed and working as expected
	*/ 
	@Test
	private void Contents_ResumeLinkWorkingAsExpected() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 :Verify that the link where user has to start again is displayed and working as expected "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify that the link where user has to start again is displayed and working as expected "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ContentsPage content = new ContentsPage(driver);
	
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
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

		System.out.println("Step 2 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button");
		Reporter.log("Step 2 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button"); 
					
			content.ContinueWhereYouLeftOff.click();	
			Thread.sleep(4000);

		System.out.println("Step 3 : On Unit page, waiting for timer to over and then clicking on Next Button");
		Reporter.log("Step 3 : On Unit page, waiting for timer to over and then clicking on Next Button"); 
			
			Thread.sleep(35000);
			
			SeleniumFunc.ClickOnElement("css", "#course_controls a ");
			Thread.sleep(4000);

		System.out.println("Step 4 : Navigating to Contents page and verifying whether Start Again link is displayed and on click to it, user is navigated to correct page");
		Reporter.log("Step 4 : Navigating to Contents page and verifying whether Start Again link is displayed and on click to it, user is navigated to correct page"); 
			
			content.ClickOnContentLink();
			Thread.sleep(4000);

			//Verifying whether link is present or not
			
			if(SeleniumFunc.IsElementPresent("css", "nav > ul > li.expanded > ul > li.expanded > ul > li:nth-child(2) > a"))
			{
				System.out.println("Success !! link is present");
				Reporter.log("Success !! link is present");
				
				SeleniumFunc.ClickOnElement("css", "nav > ul > li.expanded > ul > li.expanded > ul > li:nth-child(2) > a");
				
				//Verifying whether user is navigated to correct page or not
				
				String ExpectedText = "Page 2 of 7";
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
	
	
	/* Test 3: 
	 * Verify that 'Continue where you left off' button is displayed and working as expected
	*/ 
	@Test
	private void Contents_ContinueLeftOffButtonWorkingAsExpected() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 :Verify that 'Continue where you left off' button is displayed and working as expected "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify that 'Continue where you left off' button is displayed and working as expected"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ContentsPage content = new ContentsPage(driver);
	
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
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

		System.out.println("Step 3 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button");
		Reporter.log("Step 3 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button"); 
			
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

			
		System.out.println("Step 4 : On Unit page, waiting for timer to over and then clicking on Next Button");
		Reporter.log("Step 4 : On Unit page, waiting for timer to over and then clicking on Next Button"); 
			
			Thread.sleep(35000);
			
			SeleniumFunc.ClickOnElement("css", "#course_controls a ");
			Thread.sleep(6000);

		
		System.out.println("Step 5 : Navigating to Contents page and verifying whether  'Continue where you left off' button is displayed and on click to it, user is navigated to correct page");
		Reporter.log("Step 5 : Navigating to Contents page and verifying whether 'Continue where you left off' button is displayed and on click to it, user is navigated to correct page"); 
			
			content.Contents.click();
			Thread.sleep(6000);

			//Verifying whether link is present or not
			
			if(SeleniumFunc.IsElementPresent("css", ".page.continue span"))
			{
				System.out.println("Success !! Button is present");
				Reporter.log("Success !! Button is present");
				
				String ExpectedText = "Continue where you left off";
				String ActualText= SeleniumFunc.GetElementText("css", ".page.continue span").trim();
				
				if(ActualText.equals(ExpectedText))
				{
					System.out.println("Success !! Button text is correct");
					Reporter.log("Success !! Button text is correct");

				}
				else
				{
					System.out.println("Failure !! Button text is incorrect : " + ActualText);
					Reporter.log("Failure !! Button text is incorrect " + ActualText);
					AssertFailedCount++;
				}
				
				SeleniumFunc.ClickOnElement("css", ".page.continue span");
				Thread.sleep(4000);

				//Verifying whether user is navigated to correct page or not
				
				ExpectedText = "Page 3 of 7";
				ActualText= SeleniumFunc.GetElementText("css", "#course_controls > li.control-center > ul > li:nth-child(3) > b");
				
				if(ActualText.equals(ExpectedText))
				{
					System.out.println("Success !! User is navigated to correct page");
					Reporter.log("Success !! User is navigated to correct page ");

				}
				else
				{
					System.out.println("Failure !! User is navigated to incorrect page" + ActualText);
					Reporter.log("Failure !! User is navigated to incorrect page" + ActualText);
					AssertFailedCount++;
				}
				
			}
			else
			{
				System.out.println("Failure !! Button is NOT present");
				Reporter.log("Failure !! Button is NOT present");
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
	 * Verify that Unit/Topic/Page number correctly displayed on contents page
	*/ 
	@Test
	private void Contents_VerifyUnitTopicPageNumber() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify that Unit/Topic/Page number correctly displayed on contents page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify that Unit/Topic/Page number correctly displayed on contents page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ContentsPage content = new ContentsPage(driver);
	
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
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
			
		System.out.println("Step 2 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button");
		Reporter.log("Step 2 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button"); 
					
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

		System.out.println("Step 3 : On Contnets page, verify that Unit/Topic/Page number correctly displayed on contents page");
		Reporter.log("Step 3 : On Contnets page, verify that Unit/Topic/Page number correctly displayed on contents page"); 
			
			for (int i=2;i<3;i++)
			{						
				Thread.sleep(4000);

				String ExpectedText = "Unit 1 of 6";
				String ActualText= SeleniumFunc.GetElementText("css", "#course_controls > li.control-center > ul > li:nth-child(1) > b");
				
				if(ActualText.equals(ExpectedText))
				{
					System.out.println("Success !! Unit number is correct" + ActualText );
					Reporter.log("Success !! Unit number is correct" + ActualText );
		
				}
				else
				{
					System.out.println("Failure !! Unit number is incorrect " + ActualText );
					Reporter.log("Failure !! Unit number is incorrect " + ActualText );
					AssertFailedCount++;
				}
								
				ExpectedText = "Topic 1 of 5";
				ActualText= SeleniumFunc.GetElementText("css", "#course_controls > li.control-center > ul > li:nth-child(2) > b");
				
				if(ActualText.equals(ExpectedText))
				{
					System.out.println("Success !! Topic number is correct" + ActualText );
					Reporter.log("Success !! Topic number is correct" + ActualText );
		
				}
				else
				{
					System.out.println("Failure !! Topic number is incorrect " + ActualText );
					Reporter.log("Failure !! Topic number is incorrect " + ActualText );
					AssertFailedCount++;
				}
				
				ExpectedText = "Page " + i + " of 7";
				ActualText= SeleniumFunc.GetElementText("css", "#course_controls > li.control-center > ul > li:nth-child(3) > b");
				
				if(ActualText.equals(ExpectedText))
				{
					System.out.println("Success !! Page number is correct" + ActualText );
					Reporter.log("Success !! Page number is correct" + ActualText );
		
				}
				else
				{
					System.out.println("Failure !! Page number is incorrect " + ActualText );
					Reporter.log("Failure !! Page number is incorrect " + ActualText );
					AssertFailedCount++;
				}
				
				Thread.sleep(35000);
				
				if(i==1)
				{
					SeleniumFunc.ClickOnElement("css", "#course_controls li:nth-of-type(3) a");						
					Thread.sleep(4000);

				}
				else
				{
					SeleniumFunc.ClickOnElement("css", "#course_controls > li.next > a");						
					Thread.sleep(4000);

				}
				
			}
		
			
			//Verifying whether link is present or not
			
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
	 * Verify that user can navigate to 'Next' & 'Previous' pages
	*/ 
	@Test
	private void Contents_NavigateNextPreviousPages() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify that user can navigate to 'Next' & 'Previous' pages"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify that user can navigate to 'Next' & 'Previous' pages"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ContentsPage content = new ContentsPage(driver);
	
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			PageHeader header = new PageHeader(driver);	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
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
			Thread.sleep(6000);
			
		System.out.println("Step 2 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button");
		Reporter.log("Step 2 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button"); 
			
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(6000);

		System.out.println("Step 3 : On Contents page, verify that user can navigate to 'Next' & 'Previous' pages");
		Reporter.log("Step 3 : On Contents page, verify that user can navigate to 'Next' & 'Previous' pages"); 
			
			Thread.sleep(40000);
			SeleniumFunc.ClickOnElement("css", "#course_controls li:nth-of-type(3) a");
			Thread.sleep(8000);

				String ExpectedText = "Unit 1 of 6";
				String ActualText= SeleniumFunc.GetElementText("css", "#course_controls > li.control-center > ul > li:nth-child(1) > b");
				

				String ExpectedText2 = "Topic 1 of 5";
				String ActualText2= SeleniumFunc.GetElementText("css", "#course_controls > li.control-center > ul > li:nth-child(2) > b");
				
				String ExpectedText3 = "Page 2 of 7";
				String ActualText3= SeleniumFunc.GetElementText("css", "#course_controls > li.control-center > ul > li:nth-child(3) > b");
				
				
				
				if(ActualText.equals(ExpectedText) && ActualText2.equals(ExpectedText2) && ActualText3.equals(ExpectedText3) )
				{
					System.out.println("Success !! Unit number is correct" + ActualText );
					Reporter.log("Success !! Unit number is correct" + ActualText );
					
					System.out.println("Success !! Topic number is correct" + ActualText2 );
					Reporter.log("Success !! Topic number is correct" + ActualText2 );
					
					System.out.println("Success !! Page number is correct" + ActualText3 );
					Reporter.log("Success !! Page number is correct" + ActualText3 );
					
					System.out.println(" Success !!  'Next' button is working as expected");

				}
				else
				{
					System.out.println("Failure !! Unit number is incorrect " + ActualText );
					Reporter.log("Failure !! Unit number is incorrect " + ActualText );
					
					System.out.println("Failure !! Topic number is incorrect " + ActualText2 );
					Reporter.log("Failure !! Topic number is incorrect " + ActualText2);
					
					System.out.println("Failure !! Page number is incorrect " + ActualText3 );
					Reporter.log("Failure !! Page number is incorrect " + ActualText3 );
					
					System.out.println(" Failure !!  'Next' button is NOT working as expected");
					
					AssertFailedCount++;
					
				}
				
		
			//Clicking 'Previous' button
				SeleniumFunc.ClickOnElement("css", "#prev_text > a");
				Thread.sleep(4000);

				ExpectedText = "Unit 1 of 6";
				ActualText= SeleniumFunc.GetElementText("css", "#course_controls > li.control-center > ul > li:nth-child(1) > b");
				

				ExpectedText2 = "Topic 1 of 5";
				ActualText2= SeleniumFunc.GetElementText("css", "#course_controls > li.control-center > ul > li:nth-child(2) > b");
				
				ExpectedText3 = "Page 1 of 7";
				ActualText3= SeleniumFunc.GetElementText("css", "#course_controls > li.control-center > ul > li:nth-child(3) > b");
				
				
				
				if(ActualText.equals(ExpectedText) && ActualText2.equals(ExpectedText2) && ActualText3.equals(ExpectedText3) )
				{
					System.out.println("Success !! Unit number is correct" + ActualText );
					Reporter.log("Success !! Unit number is correct" + ActualText );
					
					System.out.println("Success !! Topic number is correct" + ActualText2 );
					Reporter.log("Success !! Topic number is correct" + ActualText2 );
					
					System.out.println("Success !! Page number is correct" + ActualText3 );
					Reporter.log("Success !! Page number is correct" + ActualText3 );
					
					System.out.println(" Success !!  'Previous' button is working as expected");

				}
				else
				{
					System.out.println("Failure !! Unit number is incorrect " + ActualText );
					Reporter.log("Failure !! Unit number is incorrect " + ActualText );
					
					System.out.println("Failure !! Topic number is incorrect " + ActualText2 );
					Reporter.log("Failure !! Topic number is incorrect " + ActualText2);
					
					System.out.println("Failure !! Page number is incorrect " + ActualText3 );
					Reporter.log("Failure !! Page number is incorrect " + ActualText3 );
					
					System.out.println(" Failure !!  'Previous' button is NOT working as expected");
					
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
	 * Verify that Timer/Tooltip is displayed on 'Next' button on each page
	*/ 
	@Test
	private void Contents_NextButton_Timer_Tooltip() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify that Timer/Tooltip is displayed on 'Next' button on each page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Verify that Timer/Tooltip is displayed on 'Next' button on each page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
	
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
		
			//Thread.sleep(8000);
			//header.Contents.click();
			Thread.sleep(8000);

			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
		System.out.println("Step 2 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button");
		Reporter.log("Step 2 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button"); 
			
			//content.ContinueWhereYouLeftOff.click();				
			//driver.manage().window().maximize();
			
			//Thread.sleep(35000);
			
			//SeleniumFunc.ClickOnElement("css", "#course_controls a ");
			
		
		System.out.println("Step 3 : Verifying whether timer is displayed on 'Next' button");
		Reporter.log("Step 3 : Verifying whether timer is displayed on 'Next' button"); 
		
	
				if(SeleniumFunc.IsElementPresent("css", ".timer"))
				{
					System.out.println("Success !! Timer is displayed");
					Reporter.log("Success !! Timer is displayed");
			
				}
				else
				{
					System.out.println("Failure !! Timer is not displayed" );
					Reporter.log("Failure !! Timer is not displayed");
	
					AssertFailedCount++;				
				}
		
				
		System.out.println("Step 4 : Verifying whether toolip is displayed on 'Next' button");
		Reporter.log("Step 4 : Verifying whether tooltip is displayed on 'Next' button"); 
		
				SeleniumFunc.ClickOnElement("css", "#timer_span_next");
				Thread.sleep(1000);
		
				//SeleniumFunc.ToMouseHover("css", "span[class='timer-label']");
				
				String javaScript = "var evObj = document.createEvent('MouseEvents');" +
		                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
		                "arguments[0].dispatchEvent(evObj);";

				WebElement element = driver.findElement(By.cssSelector("span[class='timer-label']"));
				((JavascriptExecutor)driver).executeScript(javaScript, element);
				
	
				if(SeleniumFunc.IsElementPresent("css", "div [class='popover fade top in']"))
				{
					System.out.println("Success !! Tooltip is displayed");
					Reporter.log("Success !! Tooltip is displayed");
			
				}
				else
				{
					System.out.println("Failure !! Tooltip is not displayed" );
					Reporter.log("Failure !! Tooltip is not displayed");
	
					AssertFailedCount++;				
				}
		
				Thread.sleep(35000);
				
		System.out.println("Step 5 : Verifying whether button text changed to 'Next'");
		Reporter.log("Step 5 : Verifying whether button text changed to 'Next'");
			
			String ExpectedText = "Next";
			String ActualText= SeleniumFunc.GetElementText("css", "#course_controls li.next a").trim();
			
			System.out.println(ActualText);
			
	
			if(ActualText.contains(ExpectedText))
			{
				System.out.println("Success !! button text changed to 'Next'");
				Reporter.log("Success !! button text changed to 'Next'");
		
			}
			else
			{
				System.out.println("Failure !! button text is not changed, it is : " + ActualText );
				Reporter.log("Failure !! button text is not changed, it is : " + ActualText );
	
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
	 * Verify Quiz introduction page
	*/ 
	@Test
	private void Quiz_IntroductionPage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 :Verify Quiz introduction page "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 :Verify Quiz introduction page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		ContentsPage content = new ContentsPage(driver);
		QuizPage quiz = new QuizPage(driver);
	
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			PageHeader header = new PageHeader(driver);	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
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
			keadmin.StudentSearchAndActivity(username, "Snowmobile", "Progress");
			keadmin.Progress_SelectUnitAndMarkAsComplete(1);
			Thread.sleep(4000);

			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

			
		System.out.println("Step 3 : Clicking on 'Unit 1 Quiz' link");
		Reporter.log("Step 3 : Clicking on 'Unit 1 Quiz' link");
		
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

		
		System.out.println("Step 4 : Verifying Title on Quiz page");
		Reporter.log("Step 4 : Verifying Title on Quiz page");
				
			if(SeleniumFunc.GetElementText("css", "div.page-header h1").equals("Unit 1 Quiz"))
			{
				System.out.println("Success !! Page title is correct : Unit 1 Quiz");
				Reporter.log("Success !! Page title is correct: Unit 1 Quiz"); 
			}
			else
			{
				System.out.println("Failure !!  Page title is incorrect ");
				Reporter.log("Failure !!  Page title is incorrect "); 
				AssertFailedCount++;
			}
		
	
		System.out.println("Step 5 : Verifying presence of Quiz into text, Previouls, Begin Quiz buttons  " );
		Reporter.log("Step 5 : Verifying presence of Quiz into text, Previouls, Begin Quiz buttons");
				
			if(SeleniumFunc.IsElementPresent("css", "div [class='span8 offset2'] p"))
			{
				System.out.println("Success !! Quiz into text is present");
				Reporter.log("Success !! Quiz into text is present"); 
			}
			else
			{
				System.out.println("Failure !!  Quiz into text is not present ");
				Reporter.log("Failure !! Quiz into text is NOT present");  
				AssertFailedCount++;
			}
			
			if(SeleniumFunc.IsElementPresent("id", "prev_text"))
			{
				System.out.println("Success !! Previous button is present");
				Reporter.log("Success !! Previous button is present"); 
			}
			else
			{
				System.out.println("Failure !! Previous button is not present ");
				Reporter.log("Failure !! Previous button  is NOT present");  
				AssertFailedCount++;
			}
			
			if(SeleniumFunc.IsElementPresent("id", "next_text"))
			{
				System.out.println("Success !! Next button is present");
				Reporter.log("Success !! Next button is present"); 
			}
			else
			{
				System.out.println("Failure !! Next button is not present ");
				Reporter.log("Failure !! Next button  is NOT present");  
				AssertFailedCount++;
			}
		
		
		System.out.println("Step 6 : Clicking Previous button" );
		Reporter.log("Step 6 : Clicking Previous button");
		
			String ExpectedUrl = SeleniumFunc.GetAttributeValue("css","#prev_text > a","href")+"/";
			
			//SeleniumFunc.ClickOnElement("css", "#prev_text >a");
			quiz.ClickOnPrevButton();
			Thread.sleep(4000);

			if(driver.getCurrentUrl().equals(ExpectedUrl))
			{
				System.out.println("Success !! User is reditected to contents page");
				Reporter.log("Success !! User is reditected to contents page"); 
			}
			else
			{
				System.out.println("Failure !! User is NOT reditected to contents page");
				Reporter.log("Failure !!User is NOT reditected to contents page");  
				AssertFailedCount++;
			}
			
			//SeleniumFunc.ClickOnElement("css", "#course_controls > li.next > a");
			content.ClickOnNextButton();
			Thread.sleep(4000);

	
		System.out.println("Step 7 : Clicking Begin Quiz button" );
		Reporter.log("Step 7 : Clicking Begin Quiz button");
		
			ExpectedUrl = SeleniumFunc.GetAttributeValue("css","#next_text > a","href")+"/";
			
			//SeleniumFunc.ClickOnElement("css", "#next_text >a");
			quiz.ClickOnNextButton();
			Thread.sleep(4000);

			if(driver.getCurrentUrl().equals(ExpectedUrl))
			{
				System.out.println("Success !! User is reditected to Quiz 1 page");
				Reporter.log("Success !! User is reditected to Quiz 1 page"); 
				
				String actualtitle= SeleniumFunc.GetElementText("css", "div.page-header h1");
				if(actualtitle.equals("Question 1"))
				{
					System.out.println("Success !! User is on Question 1 page");
					Reporter.log("Success !! User is on Question 1 page"); 
				}
				else
				{
					System.out.println("Failure !! User is NOT on Question 1 page");
					Reporter.log("Failure !! User is NOT on Question 1 page"); 
					AssertFailedCount++;
				}
				
			}
			else
			{
				System.out.println("Failure !! User is NOT reditected to contents page");
				Reporter.log("Failure !!User is NOT reditected to contents page");  
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
	 * Verify quiz questionnaire  - Alert pop up if user doesn't select any answer and click on Next button
	*/ 
	@Test
	private void Quiz_Questionnaire1() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 :Verify quiz questionnaire  - Alert pop up if user doesn't select any answer and click on Next button "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8: Verify quiz questionnaire  - Alert pop up if user doesn't select any answer and click on Next button"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		QuizPage quiz = new QuizPage(driver);
		ContentsPage content = new ContentsPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
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
			keadmin.StudentSearchAndActivity(username, "Snowmobile", "Progress");
			keadmin.Progress_SelectUnitAndMarkAsComplete(1);
			Thread.sleep(4000);

			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

			
		System.out.println("Step 3 : Clicking on 'Unit 1 Quiz' link");
		Reporter.log("Step 3 : Clicking on 'Unit 1 Quiz' link");
		
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

		
		System.out.println("Step 4 : Clicking Begin Quiz button" );
		Reporter.log("Step 4 : Clicking Begin Quiz button");
		
			//SeleniumFunc.ClickOnElement("css", "#next_text >a");
			quiz.ClickOnNextButton();
			Thread.sleep(4000);

			
		System.out.println("Step 5 : On Quiz Question , Clicking on Next button without selecting any answer" );
		Reporter.log("Step 5 : On Quiz Question , Clicking on Next button without selecting any answer");
			
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
				
				System.out.println(ExpectedText);
				
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
	
	
	/* Test 9: 
	 * Verify quiz questionnaire  - User can give answers and each page has correct Titles
	*/ 
	@Test
	private void Quiz_Questionnaire2() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 :Verify quiz questionnaire  - User can give answers and each page has correct Titles"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9: Verify quiz questionnaire  - User can give answers and each page has correct Titles"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		QuizPage quiz = new QuizPage(driver);
		ContentsPage content = new ContentsPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(5000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
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
			Thread.sleep(4000);

			keadmin.StudentSearchAndActivity(username, "Snowmobile", "Progress");
			keadmin.Progress_SelectUnitAndMarkAsComplete(1);
			Thread.sleep(4000);

			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

		System.out.println("Step 3 : Clicking on 'Unit 1 Quiz' link");
		Reporter.log("Step 3 : Clicking on 'Unit 1 Quiz' link");
		
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

		System.out.println("Step 4 : Clicking Begin Quiz button" );
		Reporter.log("Step 4 : Clicking Begin Quiz button");
		
			//SeleniumFunc.ClickOnElement("css", "#next_text >a");
			quiz.ClickOnNextButton();
			Thread.sleep(4000);

			
		System.out.println("Step 5 : On Quiz Question , verifying text /selecting answer and moving to next questions" );
		Reporter.log("Step 5 : On Quiz Question , verifying text /selecting answer and moving to next questions");
			 
			for(int i=0;i<10;i++)
			{
				
				String actualtitle= SeleniumFunc.GetElementText("css", "div.page-header h1");
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
				SeleniumFunc.ClickOnElement("id", "answer_text_a");
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
	
	
	
	/* Test 10: 
	 * Verify that user is asked to complete Quiz first if logout in middle of taking Quiz
	*/ 
	@Test
	private void Quiz_ResumeQuiz() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 :Verify that user is asked to complete Quiz first if logout in middle of taking Quiz"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10: Verify that user is asked to complete Quiz first if logout in middle of taking Quiz"  + "\n" +
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
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
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
			keadmin.StudentSearchAndActivity(username, "Snowmobile", "Progress");
			keadmin.Progress_SelectUnitAndMarkAsComplete(1);
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

		System.out.println("Step 3 : Clicking on 'Unit 1 Quiz' link");
		Reporter.log("Step 3 : Clicking on 'Unit 1 Quiz' link");
		
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

		System.out.println("Step 4 : Clicking Begin Quiz button" );
		Reporter.log("Step 4 : Clicking Begin Quiz button");
		
			//SeleniumFunc.ClickOnElement("css", "#next_text >a");
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
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_Snowmobile);
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
	
	
	
	/* Test 11: 
	 * Verify quiz results page - a) Quiz result = Fail
	*/ 
	@Test
	private void Quiz_QuizResultFail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 11 :Verify quiz results page - a) Quiz result = Fail"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 11: Verify quiz results page - a) Quiz result = Fail"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		QuizPage quiz = new QuizPage(driver);
		ContentsPage content = new ContentsPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
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
			Thread.sleep(4000);

			keadmin.StudentSearchAndActivity(username, "Snowmobile", "Progress");
			keadmin.Progress_SelectUnitAndMarkAsComplete(1);
			Thread.sleep(4000);

			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

			
		System.out.println("Step 3 : Clicking on 'Unit 1 Quiz' link");
		Reporter.log("Step 3 : Clicking on 'Unit 1 Quiz' link");
		
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

		
		System.out.println("Step 4 : Clicking Begin Quiz button" );
		Reporter.log("Step 4 : Clicking Begin Quiz button");
		
			//SeleniumFunc.ClickOnElement("css", "#next_text >a");
			quiz.ClickOnNextButton();
			Thread.sleep(4000);

			
		System.out.println("Step 5 : Attempting Quiz and marking as Failed" );
		Reporter.log("Step 5 : Attempting Quiz and marking as Failed");
			
		 
			for(int i=0;i<10;i++)
			{		
				//Selecting answer
				SeleniumFunc.ClickOnElement("id", "answer_text_a");
				Thread.sleep(4000);

				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
				Thread.sleep(4000);

			}
			
			
		System.out.println("Step 6 : Verifying Quiz result page when Quiz result = Fail" );
		Reporter.log("Step 6 : Verifying Quiz result page when Quiz result = Fail");
			
			//verifying header
			String expectedtext = "Unit 1 Quiz";
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
			
			expectedtext = "Sorry, you did not pass the quiz.";
			actualtext = SeleniumFunc.GetElementText("css", "div[class='span8 offset2'] h2");
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! 'Sorry, you did not pass the quiz.' message displayed ");
				Reporter.log("-- Success !! 'Sorry, you did not pass the quiz.' message displayed ");
			}
			else
			{
				System.out.println("-- Failure !! 'Sorry, you did not pass the quiz' message not displayed  and instead displayed - > " + actualtext);
				Reporter.log("-- Failure !! 'Sorry, you did not pass the quiz' message not displayed  and instead displayed - > " + actualtext);
				AssertFailedCount++;
			}
		
			//Verifying presence of "See a Critique of Your Quiz Results" button
			

			expectedtext = "See a Critique of Your Quiz Results";
			actualtext = SeleniumFunc.GetElementText("css", "a[class='critique btn']");
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! 'See a Critique of Your Quiz Results' button displayed ");
				Reporter.log("-- Success !! 'See a Critique of Your Quiz Results' button displayed ");
			}
			else
			{
				System.out.println("-- Failure !! 'See a Critique of Your Quiz Results' button is displayed  but button text is : " + actualtext);
				Reporter.log("-- Failure !! 'See a Critique of Your Quiz Results' button is displayed  but button text is : " + actualtext);
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
	
	
	
	/* Test 12: 
	 * Verify quiz results page - b) Quiz result = Pass
	*/ 
	@Test
	private void Quiz_QuizResultPass() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 12 :Verify quiz results page - b) Quiz result = Pass"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 12: Verify quiz results page - b) Quiz result = Pass"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		QuizPage quiz = new QuizPage(driver);
		ContentsPage content = new ContentsPage(driver);
	
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
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
			keadmin.StudentSearchAndActivity(username, "Snowmobile", "Progress");
			keadmin.Progress_SelectUnitAndMarkAsComplete(1);
			Thread.sleep(4000);

			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

			
		System.out.println("Step 3 : Clicking on 'Unit 1 Quiz' link");
		Reporter.log("Step 3 : Clicking on 'Unit 1 Quiz' link");
		
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

		
		System.out.println("Step 4 : Clicking Begin Quiz button" );
		Reporter.log("Step 4 : Clicking Begin Quiz button");
		
			//SeleniumFunc.ClickOnElement("css", "#next_text >a");
			quiz.ClickOnNextButton();
			Thread.sleep(4000);

			ContentPageUrl = driver.getCurrentUrl();
			
		System.out.println("Step 5 : Attempting Quiz and marking as Pass" );
		Reporter.log("Step 5 : Attempting Quiz and marking as Pass");
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Snowmobile", "Quizzes");
			keadmin.Quizzes_PassAQuiz(1);
			Thread.sleep(4000);

			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

			
		System.out.println("Step 6 : Verifying Quiz result page when Quiz result = Pass" );
		Reporter.log("Step 6 : Verifying Quiz result page when Quiz result = Pass");
			
			//verifying header
			String expectedtext = "Unit 1 Quiz";
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
			expectedtext = "Score: 100%";
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
			
			
			//verifying "You have successfully completed the quiz." message
			
			expectedtext = "You have successfully completed the quiz.";
			actualtext = SeleniumFunc.GetElementText("css", "div[class='span8 offset2'] h2");
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! 'You have successfully completed the quiz.' message displayed ");
				Reporter.log("-- Success !! 'You have successfully completed the quiz.' message displayed ");
			}
			else
			{
				System.out.println("-- Failure !! 'You have successfully completed the quiz.' message not displayed  and instead displayed - > " + actualtext);
				Reporter.log("-- Failure !! 'You have successfully completed the quiz.' message not displayed  and instead displayed - > " + actualtext);
				AssertFailedCount++;
			}
		
			//Verifying presence of "Next" button
			

			expectedtext = "Next";
			actualtext = SeleniumFunc.GetElementText("css", "#next_text a");
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! 'Next' button displayed ");
				Reporter.log("-- Success !! 'Next' button displayed ");
			}
			else
			{
				System.out.println("-- Failure !! 'Next' button is displayed  but button text is : " + actualtext);
				Reporter.log("-- Failure !! 'Next' button is displayed  but button text is : " + actualtext);
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
	
	
	/* Test 13: 
	 * Verify quiz results page - c) For  Quiz result = Fail , "See a Critique of Your Quiz Results" button functinality
	*/ 
	@Test
	private void Quiz_QuizResultFail_SeeCritiquebutton() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 13 :Verify quiz results page - c) For  Quiz result = Fail , 'See a Critique of Your Quiz Results' button functinality"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 13: Verify quiz results page - c) For  Quiz result = Fail , 'See a Critique of Your Quiz Results' button functinality"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		QuizPage quiz = new QuizPage(driver);
		ContentsPage content = new ContentsPage(driver);
	
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
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
			keadmin.StudentSearchAndActivity(username, "Snowmobile", "Progress");
			keadmin.Progress_SelectUnitAndMarkAsComplete(1);
			Thread.sleep(4000);

			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

			
		System.out.println("Step 3 : Clicking on 'Unit 1 Quiz' link");
		Reporter.log("Step 3 : Clicking on 'Unit 1 Quiz' link");
		
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

		
		System.out.println("Step 4 : Clicking Begin Quiz button" );
		Reporter.log("Step 4 : Clicking Begin Quiz button");
		
			//SeleniumFunc.ClickOnElement("css", "#next_text >a");
			quiz.ClickOnNextButton();
			Thread.sleep(4000);

			ContentPageUrl = driver.getCurrentUrl();
			
		System.out.println("Step 5 : Attempting Quiz and marking as Fail" );
		Reporter.log("Step 5 : Attempting Quiz and marking as Fail");
			
			for(int i=0;i<10;i++)
			{		
				//Selecting answer
				SeleniumFunc.ClickOnElement("id", "answer_text_a");
				Thread.sleep(4000);

				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
				Thread.sleep(4000);

			}
		
		
	System.out.println("Step 6 : Click on 'See a Critique of Your Quiz Results' button" );
	Reporter.log("Step 6 : Click on 'See a Critique of Your Quiz Results' button");
		
		String tempurl = "https://qa1.snowmobile-ed.com/exam/critique/700078267/";
		SeleniumFunc.ClickOnElement("css", "a[class='critique btn']");
		Thread.sleep(8000);
		String tempurl2 = driver.getCurrentUrl();
		System.out.println(tempurl2);
		
	System.out.println("Step 7 : Verifying Quiz Critique page" );
	Reporter.log("Step 7 : Verifying Quiz Critique page");
		
		//verifying page url
		if(driver.getCurrentUrl().equals(tempurl))
		{
			System.out.println(" --- User is naviagated to Quiz Critique page i.e. " + tempurl );
			Reporter.log("--- User is naviagated to Quiz Critique page i.e. " + tempurl);
		}
		else
		{
			System.out.println(" --- User is NOT naviagated to Quiz Critique page i.e. " + tempurl );
			Reporter.log("--- User is NOT naviagated to Quiz Critique page i.e. " + tempurl);
			AssertFailedCount++;
		}
	
		
		//verifying page header
		String actualtext = SeleniumFunc.GetElementText("css", "div.page-header h1");
		String expectedtext = "Unit 1 Quiz Critique";
		
		if(actualtext.equals(expectedtext))
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
			
	System.out.println("Step 8 : Clicking 'Back to the Quiz Results' button" );
	Reporter.log("Step 8 : Clicking 'Back to the Quiz Results' button");
		
		
		tempurl2="https://qa1.snowmobile-ed.com/exam/quiz/700078267/?override_reset=1";
		SeleniumFunc.ClickOnElement("css", "#prev_text a");
		Thread.sleep(8000);
		System.out.println(driver.getCurrentUrl());
		//verifying page url
			if(driver.getCurrentUrl().contains(tempurl2))
			{
				System.out.println(" --- User is naviagated to Quiz page i.e. " + tempurl2 );
				Reporter.log("--- User is naviagated to Quiz page i.e. " + tempurl2);
			}
			else
			{
				System.out.println(" --- User is NOT naviagated to Quiz page i.e. " + tempurl2 );
				Reporter.log("--- User is NOT naviagated to Quiz page i.e. " + tempurl2);
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
	
	
	
	/* Test 14: 
	 * Verify quiz results page - c) For  Quiz result = Fail , "Previous" button functionality
	*/ 
	@Test
	private void Quiz_QuizResultFail_Previousbutton() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 14 : Verify quiz results page - c) For  Quiz result = Fail , 'Previous' button functionality"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 14: Verify quiz results page - c) For  Quiz result = Fail , 'Previous' button functionality"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		QuizPage quiz = new QuizPage(driver);
		ContentsPage content = new ContentsPage(driver);
	
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
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
			keadmin.StudentSearchAndActivity(username, "Snowmobile", "Progress");
			keadmin.Progress_SelectUnitAndMarkAsComplete(1);
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

			
		System.out.println("Step 3 : Clicking on 'Unit 1 Quiz' link");
		Reporter.log("Step 3 : Clicking on 'Unit 1 Quiz' link");
		
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

		
		System.out.println("Step 4 : Clicking Begin Quiz button" );
		Reporter.log("Step 4 : Clicking Begin Quiz button");
		
			//SeleniumFunc.ClickOnElement("css", "#next_text >a");
			quiz.ClickOnNextButton();
			Thread.sleep(4000);

			ContentPageUrl = driver.getCurrentUrl();
			
		System.out.println("Step 5 : Attempting Quiz and marking as Fail" );
		Reporter.log("Step 5 : Attempting Quiz and marking as Fail");
			
			for(int i=0;i<10;i++)
			{		
				//Selecting answer
				SeleniumFunc.ClickOnElement("id", "answer_text_a");
				Thread.sleep(4000);

				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
				Thread.sleep(4000);

			}
		
		
	System.out.println("Step 6 : Click on 'Previous' button" );
	Reporter.log("Step 6 : Click on 'Previous' button");
		
		String tempurl = SeleniumFunc.GetAttributeValue("css", "#prev_text a", "href")+"/";
		SeleniumFunc.ClickOnElement("css", "#prev_text a");
		Thread.sleep(4000);

		//verifying page url
		if(driver.getCurrentUrl().equals(tempurl))
		{
			System.out.println(" --- User is naviagated to Unit's beginning page i.e. " + tempurl );
			Reporter.log("--- User is naviagated to Unit's beginning page i.e." + tempurl);
		}
		else
		{
			System.out.println(" --- User is NOT naviagated to Unit's beginning page i.e. " + tempurl );
			Reporter.log("--- User is NOT naviagated to Unit's beginning page i.e. " + tempurl);
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


	/* Test 15: 
	 * Verify quiz results page - c) For  Quiz result = Pass , "Previous"/ "Next" buttons functionality
	*/ 
	@Test
	private void Quiz_QuizResultPass_NextPreviousButtons() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 15 :Verify quiz results page - c) For  Quiz result = Pass , 'Previous'/ 'Next' buttons functionality"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 15 : Verify quiz results page - c) For  Quiz result = Pass , 'Previous'/ 'Next' buttons functionality"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		QuizPage quiz = new QuizPage(driver);
		ContentsPage content = new ContentsPage(driver);
	
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
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
			keadmin.StudentSearchAndActivity(username, "Snowmobile", "Progress");
			keadmin.Progress_SelectUnitAndMarkAsComplete(1);
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

		System.out.println("Step 3 : Clicking on 'Unit 1 Quiz' link");
		Reporter.log("Step 3 : Clicking on 'Unit 1 Quiz' link");
		
			content.ContinueWhereYouLeftOff.click();		
			Thread.sleep(4000);

		System.out.println("Step 4 : Clicking Begin Quiz button" );
		Reporter.log("Step 4 : Clicking Begin Quiz button");
		
			//SeleniumFunc.ClickOnElement("css", "#next_text >a");
			quiz.ClickOnNextButton();
			Thread.sleep(4000);

			ContentPageUrl = driver.getCurrentUrl();
			
		System.out.println("Step 5 : Attempting Quiz and marking as Pass" );
		Reporter.log("Step 5 : Attempting Quiz and marking as Pass");
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Snowmobile", "Quizzes");
			keadmin.Quizzes_PassAQuiz(1);
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

		System.out.println("Step 6 : Clicking on 'Previous' button" );
		Reporter.log("Step 6 : Clicking on 'Previous' button");
						
			String tempurl = SeleniumFunc.GetAttributeValue("css", "#prev_text a","href")+"/";
			SeleniumFunc.ClickOnElement("css", "#prev_text a");
			Thread.sleep(4000);

			//verifying page url
			if(driver.getCurrentUrl().equals(tempurl))
			{
				System.out.println(" --- User is naviagated to Unit's last topic i.e. " + tempurl );
				Reporter.log("--- User is naviagated to Unit's last topic i.e." + tempurl);
			}
			else
			{
				System.out.println(" --- User is NOT naviagated to Unit's last topic i.e. " + tempurl );
				Reporter.log("--- User is NOT naviagated to Unit's last topic i.e. " + tempurl);
				AssertFailedCount++;
			}
		
		System.out.println("Step 7: Clicking on 'Next' button" );
		Reporter.log("Step 7: Clicking on 'Next' button");
			
			
			driver.navigate().back();
			Thread.sleep(4000);

			tempurl = SeleniumFunc.GetAttributeValue("css", "#next_text a","href");
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			//verifying page url
			if(driver.getCurrentUrl().equals(tempurl))
			{
				System.out.println(" --- User is naviagated to next Unit's first topic i.e. " + tempurl );
				Reporter.log("--- User is naviagated to next Unit's first topic i.e." + tempurl);
			}
			else
			{
				System.out.println(" --- User is NOT naviagated to next Unit's first topic i.e. " + tempurl );
				Reporter.log("--- User is NOT naviagated to next Unit's first topic i.e. " + tempurl);
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
	
	
	
	/* Test 16: 
	 * Verify user can see hide/show course outline and see his progress
	*/ 
	@Test
	private void Contents_course_outline() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 16 :Verify user can see hide/show course outline and see his progress"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 16 : Verify user can see hide/show course outline and see his progress"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ContentsPage content = new ContentsPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
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

		System.out.println("Step 3 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button");
		Reporter.log("Step 3 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button"); 
			
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(8000);
			
		System.out.println("Step 4 : Clicking on 'View Entire Course Outline' link");
		Reporter.log("Step 4 : Clicking on 'View Entire Course Outline' link"); 
		
			content.ViewEntireCourseOutline.click();
			Thread.sleep(5000);
			
			//Verifying whether Course outline pop up displayed or not
			
			String actualattributevalue = SeleniumFunc.GetAttributeValue("css", "div#contents-toggle-container", "class");
			String expectedattributevalue = "opened";
			System.out.println(actualattributevalue);
			
			String actualattributevalue1 = SeleniumFunc.GetAttributeValue("css", "div#contents-list-container", "style");
			String expectedattributevalue1 = "height: 0px;";
			System.out.println(actualattributevalue1);
		
			if(actualattributevalue.equals(expectedattributevalue))
					{
						if(actualattributevalue1.equals(expectedattributevalue1))
						{
							System.out.println(" -- Failure, Course outline pop is NOT opened ");
							Reporter.log("-- Failure, Course outline pop is NOT opened "); 
							AssertFailedCount++;
						}
						else
						{
							System.out.println(" -- Course outline pop is opened ");
							Reporter.log("-- Course outline pop is opened ");
						}
				
					}
			  
		
		System.out.println("Step 5 : Clicking on 'Hide Entire Course Outline' link");
		Reporter.log("Step 5 : Clicking on 'Hide Entire Course Outline' link"); 
			
			content.HideEntireCourseOutline.click();
			Thread.sleep(5000);
			
			//Verifying whether Course outline pop up is hid or not
			
			actualattributevalue1 = SeleniumFunc.GetAttributeValue("css", "div#contents-list-container", "style");
			expectedattributevalue1 = "height: 0px;";
			System.out.println(actualattributevalue1);
		
			if(actualattributevalue1.equals(expectedattributevalue1))
			{
				System.out.println(" -- Course outline pop is closed ");
				Reporter.log("-- Course outline pop is closed "); 
				
			}
			else
			{
				System.out.println(" -- Failure, Course outline pop is NOT closed ");
				Reporter.log("-- Failure, Course outline pop is NOT closed"); 
				AssertFailedCount++;
			}
		
		
		System.out.println("Step 6 : Verifying whether current topic is clickable on Course outline pop up");
		Reporter.log("Step 6 : Verifying whether current topic is clickable on Course outline pop up"); 
			
			content.ViewEntireCourseOutline.click();
			Thread.sleep(8000);

			
			actualattributevalue1 = SeleniumFunc.GetAttributeValue("css", "#contents-list-container > nav > ul > li.expanded > ul > li.expanded > ul > li:nth-child(2) > a", "href")+"/";
			expectedattributevalue1 = driver.getCurrentUrl();
		
			if(expectedattributevalue1.contains(actualattributevalue1))
			{
				System.out.println(" -- Topic is clickable on Course outline pop up ");
				Reporter.log(" -- Topic is clickable on Course outline pop up "); 
				
			}
			else
			{
				System.out.println(" -- Failure, Topic is NOT clickable on Course outline pop up, actual :"+actualattributevalue1 +" Expected : "+expectedattributevalue1);
				Reporter.log(" -- Failure, Topic is NOT clickable on Course outline pop up, actual :"+actualattributevalue1 +" Expected : "+expectedattributevalue1); 
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



	/* Test 17: 
	 * Verify Course content is properly displayed
	*/ 
	@Test
	private void Contents_ContentVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 17 :Verify Course content is properly displayed"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 17 : Verify Course content is properly displayed"  + "\n" +
				 	  "====");	
		
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ContentsPage content = new ContentsPage(driver);
		
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			PageHeader header = new PageHeader(driver); 	
			Thread.sleep(8000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
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
						
		System.out.println("Step 2 : On Contents page, verifying Page Header, Introduction Text, Units, Exams related text ");
		Reporter.log("Step 2 : On Contents page, verifying Page Header, Introduction Text, Units, Exams related text"); 
		
			
			//Verifying Page header
				String actual= content.PageHeader.getText().trim();			
				String expected = "Indiana Snowmobile Ed Course";
				
				if(actual.contains(expected))
				{
					
					System.out.println("Success !! Page header is correct");
					Reporter.log("Success !! Page header is correct"); 
					
				}
				else
				{
					System.out.println("Failure !! Page header is incorrect . Expected is : " + expected + "\n" + "Actual is : " + actual);
					Reporter.log("Failure !! Page header is incorrect . Expected is : " + expected + "\n" + "Actual is : " + actual); 
					AssertFailedCount++;
				}
		
			
			//Verifying Introduction text
				actual= content.CourseIntroTextPara1_Hunter.getText().trim() + content.CourseIntroTextPara2_Hunter.getText().trim() ;	
				String expectedS = "If you are here for the first time, you'll see the first unit and topic already";
				String expectedC1 ="for you to click to begin. The next page in your Course Contents progress list will become available as you complete each page. To move to the next page, simply click on the Next button.";
				//expected = "If you are here for the first time, you'll see the first unit and topic already “opened” for you to click to begin. The next page in your Course Contents progress list will become available as you complete each page. To move to the next page, simply click on the Next button." ; 
				//String expectedE = "If you are returning to the course, the unit/topic where you need to resume study is already “opened” for you to click. You also may click on a unit/topic in the list below to access a page you've already completed.";
				String expectedE = "for you to click. You also may click on a unit/topic in the list below to access a page you've already completed.";
				
				if(actual.startsWith(expectedS) && actual.contains(expectedC1) && actual.endsWith(expectedE))
				{
					
					System.out.println("Success !! Introduction text is correct");
					Reporter.log("Success !! Introduction text is correct"); 
				}
				else
				{
					System.out.println("Failure !! Introduction text is incorrect");
					Reporter.log("Failure !! Introduction text is incorrect"); 
					AssertFailedCount++;
				}	
				
				
			//Verifying Units text	
				
				for(int i=1;i<6;i++)
				{					
					Thread.sleep(4000);

					actual= content.GetUnitText(i);			
					expected = "Unit " + i;
					
					if(actual.contains(expected))
					{
						
						System.out.println("Success !! " + expected + " is present");
						Reporter.log("Success !! " + expected + " is present");
					}
					else
					{
						System.out.println("Failure !! " + expected + " is present . Expected is : " + expected + "\n" + "Actual is : " + actual);
						Reporter.log("Failure !! " + expected + " is present . Expected is : " + expected + "\n" + "Actual is : " + actual); 
						AssertFailedCount++;
						break;
					}
			
				}
				
			//Verifying Exam text	
				
				actual= content.GetExamsText();			
				expected = "Practice Exam" + "Final Exam";
					
				if(actual.contains(expected))
				{
					
					System.out.println("Success !! 'Final Exam' and 'Practice Exam' links are present");
					Reporter.log("Success !! 'Final Exam' and 'Practice Exam' links are present");
				}
				else
				{
					System.out.println("Failure !!  'Final Exam' and 'Practice Exam' links are NOT present. Expected is : " + expected + "\n" + "Actual is : " + actual);
					Reporter.log("Failure !! 'Final Exam' and 'Practice Exam' links are NOT present. Expected is : " + expected + "\n" + "Actual is : " + actual); 
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
	
	
	/* Test 18: 
	 * Verify that user has to move in a particular sequence page wise (at user's pace) for a timed course
	 * */ 
	@Test
	private void Contents_TimedCourseVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 18 : Verify that user has to move in a particular sequence page wise (at user's pace) for a timed course"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 18 : Verify that user has to move in a particular sequence page wise (at user's pace) for a timed course"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ContentsPage content = new ContentsPage(driver);
		
		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL_Snowmobile +Constants.State_Snowmobile);
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL_Snowmobile +Constants.State_Snowmobile); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
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
			
			String username="state"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1980", emailaddress, emailaddress);
			register.ClickOnCreateAccount();
			
			Thread.sleep(8000);
			//header.Contents.click();
			
		System.out.println("Step 3 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button");
		Reporter.log("Step 3 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button"); 
			
			//content.ContinueWhereYouLeftOff.click();
			//Thread.sleep(5000);
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(1000);
			}
		
		System.out.println("Step 4 : Verifying whether timer is displayed on 'Next' button");
		Reporter.log("Step 4 : Verifying whether timer is displayed on 'Next' button"); 		
				/*
				String ExpectedText = "Remaining";
				String ActualText= SeleniumFunc.GetElementText("css", "#timer_space_remaining").trim();
				System.out.println(ActualText);*/
					
				if(SeleniumFunc.IsElementPresent("css", "#timer_space_remaining"))
				{
					System.out.println("Success !! Timer is displayed");
					Reporter.log("Success !! Timer is displayed");
			
				}
				else
				{
					System.out.println("Failure !! Timer is not displayed" );
					Reporter.log("Failure !! Timer is not displayed");
	
					AssertFailedCount++;				
				}			
				
		System.out.println("Step 5 : Navigating back to Contents page and Expanding Unit 1, Expanding Topic 3 , clicking on Page 1 link");
		Reporter.log("Step 5 : Navigating back to Contents page and Expanding Unit 1, Expanding Topic 3 , clicking on Page 1 link"); 
			
		
		
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
			
		System.out.println("Step 6 : Navigating back to Contents page and Expanding Unit 1, Expanding Topic 1 , clicking on Page 2 link");
		Reporter.log("Step 6 : Navigating back to Contents page and Expanding Unit 1, Expanding Topic 1 , clicking on Page 2 link"); 
			
			header.Contents.click();			
			Thread.sleep(5000);

			try
			{
				String expectedurl = content.Timed_GetUnitHrefLink(1, 1, 2)+"/";
				content.ContinueWhereYouLeftOff.click();
				Thread.sleep(5000);

				Thread.sleep(5000);
				String Actualurl= driver.getCurrentUrl();
				System.out.println(Actualurl);
				
	
				if(Actualurl.contains(expectedurl))
				{
					System.out.println("Success !! User is navigated to Unit 1 > Topic 1 > Page 2" );
					Reporter.log("Success !! User is navigated to Unit 1 > Topic 1 > Page 2");		
								
				}
				else
				{
					System.out.println("Failure !! User is NOT navigated to Unit 1 > Topic 1 > Page 2");
					Reporter.log("Failure !! User is NOT navigated to Unit 1 > Topic 1 > Page 2");
					AssertFailedCount++;
				}
			

			}
			catch(Exception e)
			{
				System.out.println("Failure !! User is NOT navigated to Unit 1 > Topic 1 > Page 2");
				Reporter.log("Failure !! User is NOT navigated to Unit 1 > Topic 1 > Page 2");
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


	
	/* Test 19: 
	 * Verify that video transcript is displayed along with video
	 * */ 
	@Test
	private void Video_Transcript_SM() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 19 : Verfiy that video transcript is displayed along with video"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 19 : Verfiy that video transcript is displayed along with video"  + "\n" +
				 	  "====");	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
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
			
			header.Contents.click();			
			Thread.sleep(4000);

			System.out.println("Step 4 : Go to Video Transcript page" );
			Reporter.log("Step 4 : Go to Video Transcript page" );
			
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile + "/course_content/course/501016/section/700095623");
				Thread.sleep(4000);

				//Verifying Video Transcript button is present
				
				String expectedtext = "Video Transcript";
				String actualtext = SeleniumFunc.GetElementText("css", ".transcript summary");
				
				if(actualtext.equals(expectedtext))
				{
					System.out.println("-- Success !! Video transcript button is present" );
					Reporter.log("-- Success !! Video transcript button is present" );
				}
				else
				{
					System.out.println("-- Failure !! Video transcript is NOT present . Expected is : " + expectedtext + " , Actual is : " + actualtext );
					Reporter.log("-- Failure !! Video transcript is NOT present . Expected is : " + expectedtext + " , Actual is : " + actualtext);
					AssertFailedCount++;
				}
				
				SeleniumFunc.ClickOnElement("css", ".transcript summary");
				//Verifying Video Transcript text is present
				Thread.sleep(4000);
				
				if(SeleniumFunc.IsElementPresent("css", ".transcript div"))
				{
					System.out.println("-- Success !! Video transcript text is present" );
					Reporter.log("-- Success !! Video transcript text is present" );
				}
				else
				{
					System.out.println("-- Failure !! Video transcript text is NOT present.");
					Reporter.log("-- Failure !! Video transcript text is NOT present.");
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


