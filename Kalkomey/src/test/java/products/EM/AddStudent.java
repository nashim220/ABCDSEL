package products.EM;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.HomePage;
import pageFactory.Courses.RegistrationInfoPage;
import pageFactory.EM.AgencyCreateUserPage;
import pageFactory.EM.AgencyHomePage;
import pageFactory.EM.AgencyInviteUserPage;
import pageFactory.EM.ErrorPage;
import pageFactory.EM.InstructorHomePage;
import pageFactory.EM.LoginPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class AddStudent {

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
	 * Verify  as a Kalkomey user, I can Add comment to registration
	*/ 
	
	@Test
	private void VerifyAddComment() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 1 : Verify  as a Kalkomey user, I can Add comment to registration"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 1 : Verify  as a Kalkomey user, I can Add comment to registration"  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);
		HomePage page = new HomePage(driver);
		RegistrationInfoPage info = new RegistrationInfoPage(driver);


		
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
		Thread.sleep(5000);
		
		System.out.println("Step 2 : Logging in application");
		Reporter.log("Step 2 : Logging in application"); 
				
			
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
			
		
		System.out.println("Step 3 : View an existing student's registration.");
		Reporter.log("Step 3 : View an existing student's registration."); 
			
			page.Click_Registration();
			Thread.sleep(2000);
			page.Enter_ID();
			Thread.sleep(2000);
			page.Click_Search();
			Thread.sleep(2000);
			page.click_Student();
			Thread.sleep(2000);
		
		System.out.println("Step 4 : Verify Comments , Enter a comment area & Add button is present.");
		Reporter.log("Step 4 : Verify Comments , Enter a comment area & Add button is present."); 
		
			String actualtext1 = info.comment.getText();
			String actualtext2 = info.comment_Area.getAttribute("placeholder");
			String actualtext3 = info.Add_Comment.getAttribute("value");
		
			if(actualtext1.contains("Comments") && actualtext2.contains("Enter a comment...") && actualtext3.contains("Add Comment"))
			{
				System.out.println("Sucess !! Comments , Enter a comment area & Add button is present.");
				Reporter.log("Sucess !! Comments , Enter a comment area & Add button is present."); 
				
			}
			else
			{
				System.out.println("Failure !!  Comments , Enter a comment area & Add button is not present."+" Actual text1: "+actualtext1 +" Actual text2: "+actualtext2 + " Actual text3: "+actualtext3);
				Reporter.log("Failure !!  Comments , Enter a comment area & Add button is not present."+" Actual text1: "+actualtext1 +" Actual text2: "+actualtext2 + " Actual text3: "+actualtext3); 
				AssertFailedCount++;
			}
			
		System.out.println("Step 5 : Enter a comment and click Add Comment.");
		Reporter.log("Step 5 : Enter a comment and click Add Comment."); 
			
			info.Enter_comment();
			info.Click_Add_Comment();
			Thread.sleep(5000);
			
			
		System.out.println("Step 6 : Verify You should see your comment with the time/date and your user's name.");
		Reporter.log("Step 6 : Verify You should see your comment with the time/date and your user's name."); 
		
		String actualtext4 = SeleniumFunc.GetElementText("css", "#comment-undefined > div > p > span.comment-author");
		String actualtext5 = SeleniumFunc.GetElementText("css", "#comment-undefined > div > p > span.comment-date");
		
		if(actualtext4.contains("KE Tester") && actualtext5.startsWith("2017"))
		{
			System.out.println("Sucess !! User name and Time details present.");
			Reporter.log("Sucess !! User name and Time details present."); 
			
		}
		else
		{
			System.out.println("Failure !!  User name and Time details not present."+" Actual text1: "+actualtext4 +" Actual text2: "+actualtext5);
			Reporter.log("Failure !!User name and Time details present."+" Actual text1: "+actualtext4 +" Actual text2: "+actualtext5 ); 
			AssertFailedCount++;
		}
		
		System.out.println("Step 7 : Verify after page reload, Comment with the time/date and  user's name is present.");
		Reporter.log("Step 7 :Verify after page reload, Comment with the time/date and  user's name is present."); 
		
			
			Thread.sleep(2000);
			//driver.navigate().refresh();
			driver.get(driver.getCurrentUrl());
			Thread.sleep(3000);
			actualtext4 = SeleniumFunc.GetElementText("css", "#comment-30 > div > p > span.comment-author");
			actualtext5 = SeleniumFunc.GetElementText("css", "#comment-30 > div > p > span.comment-date");
			
			if(actualtext4.contains("KE Tester") && actualtext5.startsWith("2017"))
			{
				System.out.println("Sucess !! previous comment is present.");
				Reporter.log("Sucess !! previous comment present."); 
				
			}
			else
			{
				System.out.println("Failure !! Previous comments not present."+" Actual text1: "+actualtext4 +" Actual text2: "+actualtext5);
				Reporter.log("Failure !!previous comments not present."+" Actual text1: "+actualtext4 +" Actual text2: "+actualtext5 ); 
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

}
