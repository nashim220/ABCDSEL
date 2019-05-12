package products.EM;

 

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
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

import pageFactory.EM.AdminSearchEventPage;
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

public class CreateUserTest 
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
	 * Verify create user with Administrator Run
	*/ 
	@Test
	private void VerifyAdminRun() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 1 : Verify that New User with admin role can be created successfully"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 1 : Verify that New User with admin role can be created successfully"  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);

		
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
		Thread.sleep(5000);
		
		System.out.println("Step 2 : Logging in application");
		Reporter.log("Step 2 : Logging in application"); 
				
			
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

/*
			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
		System.out.println("Step 3 : Navigation to Users listing Page");
		Reporter.log("Step 3 : Navigation to Users listing Page"); 
		
		
			agency.ClickOnUserButton();
			Thread.sleep(5000);

		
		System.out.println("Step 4 : Navigation to Create Users Page");
		Reporter.log("Step 4 : Navigation to Create Users Page"); 
			
		
			agency.ClickOnCreateUserButton();
			Thread.sleep(5000);

		
		System.out.println("Step 5 : Verify header of Create Users Page");
		Reporter.log("Step 5 : Verify header of Create Users Page"); 
				
			String Expected = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
		
			if(Expected.equals("Create Agency User"))
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

/*
			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			String username= "emuser" + JH.GenerateRandomNumber();
			String password ="clarion@123";
			
			/*
			agency.CreateNewUser("Administrator", username, password, password, "passwordhint", 
								"Company Name", "clarion", "automation" , "user","Male" , "Admin Only", 
							  "Northeast", "Adams", "Street 3", "city", "CO", "12345","rohit.ware@clariontechnologies.co.in");*/
			
			agency.CreateNewUser2(username, password, password,"automation" , "user","Male" , "Admin Only", 
					"Northeast", "Adams", "Street 3", "city", "CO", "12345","rohit.ware@clariontechnologies.co.in");
			
			//agency.ClickToInstrNo();
			agency.ClickToInstrNo_b();
			agency.ClickOnSaveChangesButton();

			Thread.sleep(5000);
			
/*
			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
		System.out.println("Step 6 : Logging out of application and login with new user");
		Reporter.log("Step 6 : Logging out of application and login with new user");
		
	
			login.ClickOnLogoutButton();
			login.EnterUsername(username);
			login.EnterPassword(password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 7 : Verifying whether user is logged successfully or not");
		Reporter.log("Step 7 : Verifying whether user is logged successfully or not"); 
		

			String ActualUserName=SeleniumFunc.GetElementText("css", ".span5.login-info>p").trim();
			
		
			if(ActualUserName.contains("Logged in as"))
			{
			
				System.out.println("Success !! User logged in successfully");
				Reporter.log("Success !! User logged in successfully"); 
			
			}
			else
			{
			
				System.out.println("Failure !! User is not logged in successfully");
				Reporter.log("Failure !! User is not logged in successfully"); 
				
				AssertFailedCount++;
		
			}
			
/*
			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
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
	 * Verify create user with Instructor Run
	*/ 
	@Test
	private void VerifyInstructorRun() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 2 : Verify that New User with instructor role can be created successfully"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 2 : Verify that New User with instructor can be created successfully"  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);

		
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Logging in application");
		Reporter.log("Step 2 : Logging in application"); 
				
			
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 3 : Navigation to Users listing Page");
		Reporter.log("Step 3 : Navigation to Users listing Page"); 
		
		
			agency.ClickOnUserButton();
			Thread.sleep(5000);

		
		System.out.println("Step 4 : Navigation to Create Users Page");
		Reporter.log("Step 4 : Navigation to Create Users Page"); 
			
		
			agency.ClickOnCreateUserButton();
			Thread.sleep(5000);

		
		System.out.println("Step 5 : Verify header of Create Users Page");
		Reporter.log("Step 5 : Verify header of Create Users Page"); 
					
			String Expected = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
			
			if(Expected.equals("Create Agency User"))
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

			
			String username= "emuser" + JH.GenerateRandomNumber();
			String password ="clarion@123";
			
			/*
			agency.CreateNewUser("Instructor", username, password, password, "passwordhint", 
								"Company Name", "clarion", "automation" , "user","Male" , "Admin Only", 
								"Northeast", "Adams", "Street 3", "city", "CO", "12345","rohit.ware@clariontechnologies.co.in");*/
			
			agency.CreateNewUser2(username, password, password,"automation" , "user","Male" , "Admin Only", 
					"Northeast", "Adams", "Street 3", "city", "CO", "12345","rohit.ware@clariontechnologies.co.in");
	
			//agency.ClickToInstrNo();
			//agency.ClickToInstrNo_b();
			agency.ClickOnSaveChangesButton();

			Thread.sleep(5000);
		
/*
			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
		System.out.println("Step 6 : Logging out of application and login with new user");
		Reporter.log("Step 6 : Logging out of application and login with new user");
		
	
			login.ClickOnLogoutButton();
			login.EnterUsername(username);
			login.EnterPassword(password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 7 : Verifying whether user is logged successfully or not");
		Reporter.log("Step 7 : Verifying whether user is logged successfully or not"); 
		

			String ActualUserName=SeleniumFunc.GetElementText("css", ".span5.login-info>p").trim();
			
		
			if(ActualUserName.contains("Logged in as"))
			{
			
				System.out.println("Success !! User logged in successfully");
				Reporter.log("Success !! User logged in successfully"); 
			
			}
			else
			{
			
				System.out.println("Failure !! User is not logged in successfully");
				Reporter.log("Failure !! User is not logged in successfully"); 
				
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
	 * Verify create user with Limited User Run
	*/	
	@Test
	private void VerifyLimitedUserRun() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 3 : Verify that New User with limited role can be created successfully"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 3 : Verify that New User with limited role can be created successfully"  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);

		
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Logging in application");
		Reporter.log("Step 2 : Logging in application"); 
				
			
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 3 : Navigation to Users Page");
		Reporter.log("Step 3 : Navigation to Users Page"); 
		
		
			agency.ClickOnUserButton();
			Thread.sleep(5000);

		
		System.out.println("Step 4 : Navigation to Create Users Page");
		Reporter.log("Step 4 : Navigation to Create Users Page"); 
			
		
			agency.ClickOnCreateUserButton();
			Thread.sleep(5000);

		
		System.out.println("Step 5 : Verify header of Create Users Page");
		Reporter.log("Step 5 : Verify header of Create Users Page"); 
						
			String Expected = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
				
			if(Expected.equals("Create Agency User"))
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
			
			String username= "emuser" + JH.GenerateRandomNumber();
			String email= username +"@mailinator.com";
			String password ="clarion@123";
			
			/*
			agency.CreateNewUser("Limited", username, password, password, "passwordhint", 
					"Company Name", "clarion", "automation" , "user","Male" , "Admin Only", 
					"Northeast", "Adams", "Street 3", "city", "CO", "12345", "rohit.ware@clariontechnologies.co.in");*/
			
			agency.CreateNewUser2(username, password, password,"automation" , "user","Male" , "Admin Only", 
					"Northeast", "Adams", "Street 3", "city", "CO", "12345","rohit.ware@clariontechnologies.co.in");
	
			agency.EnterEmail(email);
		//	agency.ClickToInstrNo();
		//	agency.ClickToInstrNo_b();
			agency.ClickOnSaveChangesButton();

			Thread.sleep(5000);
		

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
		System.out.println("Step 6 : Logging out of application and login with new user");
		Reporter.log("Step 6 : Logging out of application and login with new user");
		
	
			login.ClickOnLogoutButton();
			login.EnterUsername(username);
			login.EnterPassword(password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 7 : Verifying whether user is logged successfully or not");
		Reporter.log("Step 7 : Verifying whether user is logged successfully or not"); 
		

			String ActualUserName=SeleniumFunc.GetElementText("css", ".span5.login-info p").trim();
			
		
			if(ActualUserName.contains("Logged in as"))
			{
			
				System.out.println("Success !! User logged in successfully");
				Reporter.log("Success !! User logged in successfully"); 
			
			}
			else
			{
			
				System.out.println("Failure !! User is not logged in successfully");
				Reporter.log("Failure !! User is not logged in successfully"); 
				
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
	 * Prevent duplicate instructor number entry on create Run
	*/
	
	@Test()
	private void DuplicateInstrNoOnEdit() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 4 : Verify that system is not allowing to edit instructor with already used Instructor No."  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 4 : Verify that system is not allowing to edit instructor with already used Instructor No."  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);

		
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Logging in application");
		Reporter.log("Step 2 : Logging in application"); 
				
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 3 : Navigation to Users listing Page");
		Reporter.log("Step 3 : Navigation to Users listing Page"); 
		
		
			agency.ClickOnUserButton();
			Thread.sleep(5000);

		
		System.out.println("Step 4 : Navigation to Create Users Page");
		Reporter.log("Step 4 : Navigation to Create Users Page"); 
			
		
			agency.ClickOnCreateUserButton();
			Thread.sleep(5000);

			String username= "emtestuser" + JH.GenerateRandomNumber();
			String password ="clarion@123";
			String instrnumber = RandomStringUtils.randomNumeric(4);
			String instrnumberb = "B" + RandomStringUtils.randomNumeric(4);
			
			
			/*
			agency.CreateNewUser("Instructor", username, password, password, "passwordhint", 
					"Company Name", "clarion", "automation" , "user","Male" , "Admin Only", 
					"Northeast", "Adams", "Street 3", "city", "CO", "12345","rohit.ware@clariontechnologies.co.in");*/
	
			agency.CreateNewUser2(username, password, password,"automation" , "user","Male" , "Admin Only", 
					"Northeast", "Adams", "Street 3", "city", "CO", "12345","rohit.ware@clariontechnologies.co.in");
			
			agency.ClickToInstrNo();
			agency.ClickToInstrNo_b();
			
			agency.EnterInstrNo(instrnumber);
			agency.EnterInstrNoB(instrnumberb);
			System.out.println("Instructor Number : "+instrnumber);
			agency.ClickOnSaveChangesButton();

			Thread.sleep(5000);
		
		
		System.out.println("Step 5 : Search Instructor");
		Reporter.log("Step 5 : Search Instructor"); 
				
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM + "/agency/user/");
			Thread.sleep(4000);
		
			agency.SelectSearchType("Username");
			agency.EnterSearchData(Constants.EM_CLInstructor_Username);
			agency.ClickOnSearchButton();
			Thread.sleep(3000);
			Thread.sleep(5000);

		/*	if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
		*/	
			SeleniumFunc.ClickOnElement("css", "#user_select_form span");
			SeleniumFunc.ClickOnElement("linkText","Edit");
			Thread.sleep(5000);
			

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
		System.out.println("Step 6 : Enter already used instructor number");
		Reporter.log("Step 6 : Enter already used instructor number"); 
				
			Thread.sleep(4000);
			/*JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 600);");*/
		
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,250)", "");
			
			//agency.ClickToInstrNo();
			Thread.sleep(2000);
			//agency.ClickToInstrNo_b();
			agency.EnterInstrNo(instrnumber);
			System.out.println("Instructor Number : "+instrnumber);
			
			//agency.EnterInstrNoB(instrnumberb);
		
		
		System.out.println("Step 7 : Save record with updated details");
		Reporter.log("Step 7 : Save record with updated details");
		
		
			agency.ClickOnSaveChangesButton();
			Thread.sleep(5000);

	
		System.out.println("Step 8 : Verifying validation fired for already used instructor number");
		Reporter.log("Step 8 : Verifying validation fired for already used instructor number"); 
		

			if(SeleniumFunc.IsElementPresent("css", "#error_pd_instructor_number"))
			{
				
				System.out.println("Success !! Validation is fired for already used instructor number");
				Reporter.log("Success !! Validation is fired for already used instructor number");
			
			}
			else
			{
		
				System.out.println("Failure !! Validation is not fired for already used instructor number");
				Reporter.log("Failure !! Validation is not fired for already used instructor number");
				
				AssertFailedCount++;
			
			}
		
		/* Removing this step as instructor number (boating) field is not mandatory
		 
		
		System.out.println("Step 9 : Verifying validation fired for already used instructor number (boating)");
		Reporter.log("Step 9 : Verifying validation fired for already used instructor number (boating)"); 
			

			if(SeleniumFunc.IsElementPresent("css", "#error_pd_instructor_number_b"))
			{
					
				System.out.println("Success !! Validation is fired for already used instructor number (boating)");
				Reporter.log("Success !! Validation is fired for already used instructor number (boating)");
				
			}
			else
			{
					
				System.out.println("Failure !! Validation is not fired for already used instructor number (boating)");
				Reporter.log("Failure !! Validation is not fired for already used instructor number (boating)");
					
				AssertFailedCount++;
				
			}
			
/*
			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
	
	 /** Marking Test Pass or Fail as per the value of AssertFailedCount variable*/
	 
			if(AssertFailedCount>0)	
			{
			
				//Marking this test as Failed
			
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
				Assert.fail();
		
			}
	}
	
	
	/* Test 5: 
	 * Prevent duplicate instructor numbers on edit Run
	*/	
	@Test
	private void DuplicateInstrNoOnCreate() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 5 : Verify that system is not allowing to create instructor with already used Instructor No."  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 5 : Verify that system is not allowing to create instructor with already used Instructor No."  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);

		
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Logging in application");
		Reporter.log("Step 2 : Logging in application"); 
				
			
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 3 : Navigation to Users Page");
		Reporter.log("Step 3 : Navigation to Users Page"); 
		
		
			agency.ClickOnUserButton();
			Thread.sleep(5000);

		
		System.out.println("Step 4 : Navigation to Create Users Page");
		Reporter.log("Step 4 : Navigation to Create Users Page"); 
			
		
			agency.ClickOnCreateUserButton();
			Thread.sleep(5000);

			String username= "emtest" + JH.GenerateRandomNumber();
			String password ="clarion@123";
			String instrnumber = RandomStringUtils.randomNumeric(4);
			String instrnumberb = "B" + RandomStringUtils.randomNumeric(4);
		
			/*
			agency.CreateNewUser("Instructor", username, password, password, "passwordhint", 
					"Company Name", "clarion", "automation" , "user","Male" , "Admin Only", 
					"Northeast", "Adams", "Street 3", "city", "CO", "12345","rohit.ware@clariontechnologies.co.in");*/
			
			agency.CreateNewUser2(username, password, password,"automation" , "user","Male" , "Admin Only", 
					"Northeast", "Adams", "Street 3", "city", "CO", "12345","rohit.ware@clariontechnologies.co.in");
	
			agency.ClickToInstrNo();
			agency.ClickToInstrNo_b();

			agency.EnterInstrNo(instrnumber);
			agency.EnterInstrNoB(instrnumberb);
			agency.ClickOnSaveChangesButton();

			Thread.sleep(10000);
			System.out.println("Instructor Number :" +instrnumber);
			System.out.println("Instructor Number Boat :" +instrnumberb);
		
		System.out.println("Step 5 : Adding User with same Instructor No");
		Reporter.log("Step 5 : Adding User with same Instructor No"); 
				
		/*	SeleniumFunc.ClickOnElement("css", ".content.clearfix div:nth-of-type(2) a");
			Thread.sleep(4000);
			
			agency.ClickOnCreateUserButton();
			Thread.sleep(5000);*/

			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM + "/agency/user/");
		
			agency.ClickOnCreateUserButton();
			Thread.sleep(5000);
			/*
			agency.CreateNewUser("Instructor", username, password, password, "passwordhint", 
					"Company Name", "clarion", "automation" , "user","Male" , "Admin Only", 
					"Northeast", "Adams", "Street 3", "city", "CO", "12345","rohit.ware@clariontechnologies.co.in");*/
			
			String username1= "emtestuser" + JH.GenerateRandomNumber();
			agency.CreateNewUser2(username1, password, password,"automation" , "user","Male" , "Admin Only", 
					"Northeast", "Adams", "Street 3", "city", "CO", "12345","rohit.ware@clariontechnologies.co.in");
			
			agency.ClickToInstrNo();
			agency.ClickToInstrNo_b();
			agency.EnterInstrNo(instrnumber);
			agency.EnterInstrNoB(instrnumberb);
			System.out.println("Instructor Number : " +instrnumber);
			System.out.println("Instructor Number Boat : " +instrnumberb);
			agency.ClickOnSaveChangesButton();
			Thread.sleep(5000);

	
		System.out.println("Step 6 : Verifying validation fired for already used instructor number");
		Reporter.log("Step 6 : Verifying validation fired for already used instructor number"); 
		

			if(SeleniumFunc.IsElementPresent("css", "div#error_pd_instructor_number"))
			{
				
				System.out.println("Success !! Validation is fired for already used instructor number");
				Reporter.log("Success !! Validation is fired for already used instructor number");
				
			}
			else
			{
				
				System.out.println("Failure !! Validation is not fired for already used instructor number");
				Reporter.log("Failure !! Validation is not fired for already used instructor number");
				
				AssertFailedCount++;
		
			}
			
		
		System.out.println("Step 7 : Verifying validation fired for already used instructor number (boating)");
		Reporter.log("Step 7 : Verifying validation fired for already used instructor number (boating)"); 
			

			if(SeleniumFunc.IsElementPresent("css", "div#error_pd_instructor_number_b"))
			{
				
				System.out.println("Success !! Validation is fired for already used instructor number (boating)");
				Reporter.log("Success !! Validation is fired for already used instructor number (boating)");
				
			}
			else
			{
				
				System.out.println("Failure !! Validation is not fired for already used instructor number (boating)");
				Reporter.log("Failure !! Validation is not fired for already used instructor number (boating)");
				
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
	 * Bypass instructor number validation when instructor does not have a number Run
	*/	
	@Test
	private void BypassInstrNumber() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 6 : Verify that New User can be created successfully"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 6 : Verify that New User can be created successfully"  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);

		
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Logging in application");
		Reporter.log("Step 2 : Logging in application"); 
		
		
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(8000);

		
		System.out.println("Step 3 : Navigation to Users Page");
		Reporter.log("Step 3 : Navigation to Users Page"); 
		
		
			agency.ClickOnUserButton();
			Thread.sleep(5000);

		
		System.out.println("Step 4 : Navigation to Create Users Page");
		Reporter.log("Step 4 : Navigation to Create Users Page"); 
			
		
			agency.ClickOnCreateUserButton();
			Thread.sleep(5000);

			String username= "emuser" + JH.GenerateRandomNumber();
			String password ="clarion@123";
			/*
			agency.CreateNewUser( "Instructor" ,username, password, password, "passwordhint", 
					"Company Name", "clarion", "automation" , "user","Male" , "Admin Only", 
					"Northeast", "Adams", "Street 3", "city", "CO", "12345","rohit.ware@clariontechnologies.co.in");*/
			
			agency.CreateNewUser2(username, password, password,"automation" , "user","Male" , "Admin Only", 
					"Northeast", "Adams", "Street 3", "city", "CO", "12345","rohit.ware@clariontechnologies.co.in");
	
			
			//agency.ClickToInstrNo();
			agency.ClickToInstrNo_b();
			agency.ClickOnSaveChangesButton();

			Thread.sleep(5000);
		
	
		System.out.println("Step 5 : Verify that user can bypass instructor number");
		Reporter.log("Step 5 : Verify that user can bypass instructor number");
		
			if(!SeleniumFunc.IsElementPresent("css", "#error_pd_instructor_number"))
			{
				
				System.out.println("Success !! Bypass instructor number functionality is working properly");
				Reporter.log("Success !! Bypass instructor number functionality is working properly");
				
			}
			else
			{
				
				System.out.println("Failure !! Bypass instructor number functionality is not working properly");
				Reporter.log("Failure !! Bypass instructor number functionality is not working properly");
				
				AssertFailedCount++;
				
			}
			
/*
			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
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
	 * Verify validations are done for mandatory fields on Create new user page Run
	*/
	@Test
	private void MandatoryFieldsRun() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 7 : Verify that New User can be created successfully"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 7 : Verify that New User can be created successfully"  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);

		
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Logging in application");
		Reporter.log("Step 2 : Logging in application"); 
				
			
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 3 : Navigation to Users Page");
		Reporter.log("Step 3 : Navigation to Users Page"); 
		
		
			agency.ClickOnUserButton();
		
			Thread.sleep(5000);

		System.out.println("Step 4 : Navigation to Create Users Page");
		Reporter.log("Step 4 : Navigation to Create Users Page"); 
			
		
			agency.ClickOnCreateUserButton();
			Thread.sleep(5000);

		
		System.out.println("Step 5 : Clicking on Save button without entering any details");
		Reporter.log("Step 5 : Clicking on Save button without entering any details"); 

			
			agency.ClickOnSaveChangesButton();
			Thread.sleep(5000);

		
		System.out.println("Step 6 : Verifying whether validation messages are displayed for mandatory fields");
		Reporter.log("Step 6 : Verifying whether validation messages are displayed for mandatory fields"); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#error_uname div") + SeleniumFunc.GetElementText("css", "#error_password div");
			
			String ExpectedValidationMessage = "User name is required."+ "Password is required.";
			

			if(ExpectedValidationMessage.equals(ActualValidationMessage))
			{
				
				System.out.println("Success !! correct validation messages are displayed for mandatory fields");
				Reporter.log("Success !! correct validation messages are displayed for mandatory fields"); 
			
			}
			else
			{
			
				System.out.println("Failure !!  Incorrect validation messages are displayed for mandatory fields" + "\n" + "Expected Validation messages : "  
									+ "\n" + ExpectedValidationMessage + "\n" + 
									 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
				Reporter.log("Failure !!  Incorrect validation messages are displayed for mandatory fields" + "\n" + "Expected Validation messages : "  
						+ "\n" + ExpectedValidationMessage + "\n" + 
						 "Actual Validation messages : " + "\n" + ActualValidationMessage); 
				
				AssertFailedCount++;
			
			}
			
/*
			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/
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
	 * Verify cancel button works on create new user page
	*/
	@Test
	private void VerifyCancelFunc() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 8 : Verify that Cancel button is working properly"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 8 : Verify that Cancel button is working properly"  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);

		
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Logging in application");
		Reporter.log("Step 2 : Logging in application"); 
				
			
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 3 : Navigation to Users Page");
		Reporter.log("Step 3 : Navigation to Users Page"); 
		
		
			agency.ClickOnUserButton();
			Thread.sleep(5000);

		
		System.out.println("Step 4 : Navigation to Create Users Page");
		Reporter.log("Step 4 : Navigation to Create Users Page"); 
			
		
			agency.ClickOnCreateUserButton();
			Thread.sleep(5000);

		
		System.out.println("Step 5 : Clicking on Cancel button without entering any details");
		Reporter.log("Step 5 : Clicking on Cancel button without entering any details"); 
		
		
			agency.ClickOnCancelButton();
			Thread.sleep(5000);

			
		System.out.println("Step 6 : Verify that user is redirected to agency user listing page");
		Reporter.log("Step 6 : Verify that user is redirected to agency user listing page");
		
			String Expected = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
			
			if(Expected.equals("Agency Users"))
			{
				
				System.out.println("Success !! Cancel button functionality is working properly");
				Reporter.log("Success !! Cancel button functionality is working properly");
				
			}
			else
			{
				
				System.out.println("Failure !! Cancel button functionality is not working properly, user not redirected to "+ Expected + " page");
				Reporter.log("Failure !! Cancel button functionality is not working properly, user not redirected to "+ Expected + " page");
				
				AssertFailedCount++;
			
			}


/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
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
	 * Verify user can 'Act as' searched user Run
	*/
	@Test()
	private void VerifyActAsUser() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 9 : Verify that Act As User button functionality is working properly."  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test  : Verify that Act As User button functionality is working properly."  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);
		InstructorHomePage instructorhome = new InstructorHomePage(driver);
		
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Logging in application");
		Reporter.log("Step 2 : Logging in application"); 
				
			
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
		
			Thread.sleep(5000);

		System.out.println("Step 3 : Navigation to Users Page");
		Reporter.log("Step 3 : Navigation to Users Page"); 
				
			agency.ClickOnUserButton();
			Thread.sleep(5000);

		System.out.println("Step 5 : Search Instructor");
		Reporter.log("Step 5 : Search Instructor"); 
				
			agency.SelectSearchType("Username");
			agency.EnterSearchData(Constants.EM_CLInstructor_Username);
			agency.ClickOnSearchButton();
			Thread.sleep(3000);
			SeleniumFunc.ClickOnElement("css", "#user_select_form span");
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("linkText","Act as Automation");
			
			Thread.sleep(5000);

		System.out.println("Step 6 : Verify welcome message ");
		Reporter.log("Step 6 : Verify welcome message "); 
			
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
	
		
		System.out.println("Step 7 : Verify Login Act As Text present");
		Reporter.log("Step 7 : Verify Login Act As Text present"); 

			if(SeleniumFunc.IsElementPresent("css", ".login-act-as"))
			{
				
				System.out.println("Success !! Login act as is present");
				Reporter.log("Success !! Login act as is present");
			
			}
			else
			{
			
				System.out.println("Failure !! 'Act as this user' button is not redircting to instructor home page");
				Reporter.log("Failure !! 'Act as this user' button is not redircting to instructor home page");
				
				AssertFailedCount++;
			
			}


/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/
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
	 * Verify email field is mandatory for Limited user Run
	 * As per recent changes email field is not mandatory in form , verify validation for valid email address
	*/	
	@Test
	private void EmailMadatoryForLimited() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 10 : Verify that Email is mandatory for limited user role"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 10 : Verify that Email is mandatory for limited user role"  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);

		
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Logging in application");
		Reporter.log("Step 2 : Logging in application"); 
				
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 3 : Navigation to Users Page");
		Reporter.log("Step 3 : Navigation to Users Page"); 
		
		
			agency.ClickOnUserButton();
			Thread.sleep(5000);

		
		System.out.println("Step 4 : Navigation to Create Users Page");
		Reporter.log("Step 4 : Navigation to Create Users Page"); 
			
		
			agency.ClickOnCreateUserButton();
			Thread.sleep(5000);

		
		System.out.println("Step 5 : Verify header of Create Users Page");
		Reporter.log("Step 5 : Verify header of Create Users Page"); 
						
			String Expected = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
				
			if(Expected.equals("Create Agency User"))
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
			
			String username= "emuser" + JH.GenerateRandomNumber();
			String password ="clarion@123";
			
			/*
			agency.CreateNewUser("Limited", username, password, password, "passwordhint", 
					"Company Name", "clarion", "automation" , "user","Male" , "Admin Only", 
					"Northeast", "Adams", "Street 3", "city", "CO", "12345","");*/
			
			agency.CreateNewUser2(username, password, password,"automation" , "user","Male" , "Admin Only", 
					"Northeast", "Adams", "Street 3", "city", "CO", "12345","Test@test");
			
			agency.ClickToInstrNo();
			agency.ClickToInstrNo_b();
			agency.ClickOnSaveChangesButton();

			Thread.sleep(5000);
		
		System.out.println("Step 6 : Verifying whether validation message for Email is displayed");
		Reporter.log("Step 6 : Verifying whether validation message for Email is displayed"); 
				
				
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#error_email div");
			
			String ExpectedValidationMessage = "Email address must be in proper format.";
			//String ExpectedValidationMessage = "Email is required";
				

			if(ExpectedValidationMessage.equals(ActualValidationMessage))
			{
					
				System.out.println("Success !! correct validation message is displayed for Email field");
				Reporter.log("Success !! correct validation message is displayed for Email field"); 
				
			}
			else
			{
				
				System.out.println("Failure !!  Incorrect validation message is displayed for Email field" + "\n" + "Expected Validation messages : "  
										+ "\n" + ExpectedValidationMessage + "\n" + 
										 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
				Reporter.log("Failure !!  Incorrect validation message is displayed for Email field" + "\n" + "Expected Validation messages : "  
							+ "\n" + ExpectedValidationMessage + "\n" + 
							 "Actual Validation messages : " + "\n" + ActualValidationMessage); 
					
				AssertFailedCount++;
				
			}


/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
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
	 * Verify invite user for EM account
	*/ 
	@Test
	private void AccountCreateInvite() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 11 : Verify invite user for EM account"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 11 : Verify invite user for EM account"  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);
		AgencyInviteUserPage inviteuser  = new AgencyInviteUserPage(driver);

		
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
		
			Thread.sleep(5000);

		System.out.println("Step 2 : Logging in application");
		Reporter.log("Step 2 : Logging in application"); 
				
			
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 3 : Navigation to Users listing Page");
		Reporter.log("Step 3 : Navigation to Users listing Page"); 
		
		
			agency.ClickOnUserButton();
			Thread.sleep(5000);

		
		System.out.println("Step 4 : Navigation to Create Users Page");
		Reporter.log("Step 4 : Navigation to Create Users Page"); 
			
		
			agency.ClickOnCreateUserButton();
			Thread.sleep(5000);

		
		System.out.println("Step 5 : Go to invite user and verify header");
		Reporter.log("Step 5 : Go to invite user and verify header"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/user/invite");
	
			String Expected = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
		
			if(Expected.equals("Invite Non-certified User"))
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

		
			String username= "emuser" + JH.GenerateRandomNumber();
			String password ="clarion@123";
			String email = username + "@mailinator.com";
			System.out.println("Email ID: "+email);
			
			inviteuser.SelectProgram();
			Thread.sleep(2000);
			//inviteuser.FillInviteUserForm(username, password, password, "Company", "Name", "Clarion", "Invite", "User", "Male", email);
			inviteuser.FillInviteUserForm2(username, password, password,"Invite", "User", "Male", email);
			Thread.sleep(2000);
			inviteuser.ClickOnInviteUserButton();

			Thread.sleep(5000);
			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			
		System.out.println("Step 10 : Navigating to Email Box and verifying whether email is received with correct details or not");
		Reporter.log("Step 10 : Navigating to Email Box and verifying whether email is received with correct details or not"); 
					
				
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=emautomation#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
					
				
			SeleniumFunc.ClickOnElement("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");
			
			Thread.sleep(4000);
			
			driver.switchTo().frame("publicshowmaildivcontent");
			
			
			//Verifying link to manage a/c	
			
			if(SeleniumFunc.IsElementPresent("css", "tbody>tr>td>a"))
			{
					
				System.out.println("Success!! Link to manage a/c is present");
				Reporter.log("Success!! Link to manage a/c is present");
				
			}
			else
			{
					
				System.out.println("Success!! Link to manage a/c is NOT present");
				Reporter.log("Success!! Link to manage a/c is NOT present");
					
			}
					
			String URL = SeleniumFunc.GetElementText("css", "tbody>tr>td>a");	
	
		
		System.out.println("Step 7 : Verifying account verification message and logged into account");
		Reporter.log("Step 7 : Verifying account verification message and logged into account"); 
		
		
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(5000);

			String ActualText=SeleniumFunc.GetElementText("css", ".alert-flash.alert.alert-success ul").trim();
			String ExpectedText = "Your account has been verified."+ "\n"+ 
								  "Please login to complete your profile.";
		
			if(ActualText.contains(ExpectedText))
			{
		
				System.out.println("Success !! Account verified successfully" + ExpectedText);
				Reporter.log("Success !! Account verified successfully" + ExpectedText); 
		
			}
			else
			{
		
				System.out.println("Failure !! Error in account verification. Expected is: "+ ExpectedText
									+ "\n"+ "Actual is: " +ActualText);
				Reporter.log("Failure !! Error in account verification. Expected is: "+ ExpectedText
						+ "\n"+ "Actual is: " +ActualText); 
			
				AssertFailedCount++;
	
			}
	
		
			login.EnterUsername(username);
			login.EnterPassword(password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			String ActualUserName=SeleniumFunc.GetElementText("css", ".span5.login-info>p").trim();
					
			if(ActualUserName.contains("Logged in as"))
			{
			
				System.out.println("Success !! User logged in successfully");
				Reporter.log("Success !! User logged in successfully"); 
			
			}
			else
			{
			
				System.out.println("Failure !! User is not logged in successfully");
				Reporter.log("Failure !! User is not logged in successfully"); 
				
				AssertFailedCount++;
		
			}
			
			
			ActualText=SeleniumFunc.GetElementText("css", ".alert.alert-warning").trim();
			ExpectedText = "You Requested a Password Reset or Your Password Has Expired"+ "\n"+ 
								  "You will need to update your password and security question before proceeding.";
		
			if(ActualText.contains(ExpectedText))
			{
		
				System.out.println("Success !! Showing Update Your Security Information page");
				Reporter.log("Success !! Showing Update Your Security Information page"); 
		
			}
			else
			{
		
				System.out.println("Failure !! NOT Showing Update Your Security Information page. Expected is: "+ ExpectedText
									+ "\n"+ "Actual is: " +ActualText);
				Reporter.log("Failure !! NOT Showing Update Your Security Information page. Expected is: "+ ExpectedText
						+ "\n"+ "Actual is: " +ActualText); 
			
				AssertFailedCount++;
	
			}
			

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/
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
	 * Verify Reset PIN functionality
	 */	
	
	@Test
	private void VerifyPINReset() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 12 : Verify Reset PIN functionality"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 12 : Verify Reset PIN functionality"  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);

		
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
		
			Thread.sleep(5000);

		System.out.println("Step 2 : Logging in application");
		Reporter.log("Step 2 : Logging in application"); 
				
			
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
		
			Thread.sleep(5000);

		System.out.println("Step 3 : Navigation to Users Page");
		Reporter.log("Step 3 : Navigation to Users Page"); 
		
		
			agency.ClickOnUserButton();
			Thread.sleep(5000);

		
		System.out.println("Step 4 : Navigation to Create Users Page");
		Reporter.log("Step 4 : Navigation to Create Users Page"); 
			
		
			agency.ClickOnCreateUserButton();
			Thread.sleep(5000);
		
		System.out.println("Step 5 : Verify header of Create Users Page");
		Reporter.log("Step 5 : Verify header of Create Users Page"); 
						
			String Expected = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
				
			if(Expected.equals("Create Agency User"))
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
			
			String username= "emuser" + JH.GenerateRandomNumber();
			String email= username +"@mailinator.com";
			String password ="clarion@123";
			
			/*
			agency.CreateNewUser("Instructor", username, password, password, "passwordhint", 
					"Company Name", "clarion", "automation" , "user","Male" , "Admin Only", 
					"Northeast", "Adams", "Street 3", "city", "CO", "12345","rohit.ware@clariontechnologies.co.in");*/
			
			agency.CreateNewUser2(username, password, password,"automation" , "user","Male" , "Admin Only", 
					"Northeast", "Adams", "Street 3", "city", "CO", "12345","rohit.ware@clariontechnologies.co.in");
	
			agency.EnterEmail(email);
			agency.ClickToInstrNo();
			agency.ClickToInstrNo_b();
			agency.ClickOnSaveChangesButton();

			Thread.sleep(5000);

			login.ClickOnLogoutButton();
			Thread.sleep(5000);
		/*
		System.out.println("Step 10 : Navigating to Email Box and verifying whether email is received with correct details or not");
		Reporter.log("Step 10 : Navigating to Email Box and verifying whether email is received with correct details or not"); 
						
					
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=emautomation#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
						
					
			SeleniumFunc.ClickOnElement("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");
			
			Thread.sleep(4000);
			
			driver.switchTo().frame("publicshowmaildivcontent");
					
			//Verifying link to manage a/c	
				
			if(SeleniumFunc.IsElementPresent("css", "tbody>tr>td>a"))
			{
						
				System.out.println("Success!! Link to manage a/c is present");
				Reporter.log("Success!! Link to manage a/c is present");
				
			}
			else
			{
						
				System.out.println("Success!! Link to manage a/c is NOT present");
				Reporter.log("Success!! Link to manage a/c is NOT present");
						
			}
			*/
			//String URL = SeleniumFunc.GetElementText("css", "tbody>tr>td>a");	
		
			
		System.out.println("Step 6 : Login with new user");
		Reporter.log("Step 6 : Login with new user");
		
			
			//SeleniumFunc.ToGoToUrl(URL);
			login.EnterUsername(username);
			login.EnterPassword(password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		System.out.println("New User Name : "+username);
		System.out.println("Step 7 : Verifying whether user is logged successfully or not");
		Reporter.log("Step 7 : Verifying whether user is logged successfully or not"); 
		

			String ActualUserName=SeleniumFunc.GetElementText("css", ".span5.login-info p").trim();
			
		
			if(ActualUserName.contains("Logged in as"))
			{
			
				System.out.println("Success !! User logged in successfully");
				Reporter.log("Success !! User logged in successfully"); 
			
			}
			else
			{
			
				System.out.println("Failure !! User is not logged in successfully");
				Reporter.log("Failure !! User is not logged in successfully"); 
				
				AssertFailedCount++;
			
			}
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/user");
			
			Thread.sleep(5000);
			
			SeleniumFunc.ClickOnElement("css", "#page li:nth-of-type(2) a");
			SeleniumFunc.EnterValueInTextbox("css", "#pin", "1234");
			SeleniumFunc.EnterValueInTextbox("css", "#pin_confirm", "1234");
			SeleniumFunc.ClickOnElement("css", ".btn.btn-success");
			Thread.sleep(5000);


/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			//Login as admin and reset the PIN
			login.ClickOnLogoutButton();
			Thread.sleep(5000);

			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
			Thread.sleep(5000);

		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("linkText", "Search Instructors");
			Thread.sleep(5000);

			//Search User
			agency.SelectSearchType("Username");
			agency.EnterSearchData(username);
			agency.ClickOnSearchButton();
			Thread.sleep(5000);


/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			//Edit record and reset pin
			SeleniumFunc.ClickOnElement("css", "#proctor_select_form td:nth-of-type(2) a:nth-of-type(2)");
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("css", "#reset_pin");
			Thread.sleep(5000);

			agency.ClickOnSaveChangesButton();
			Thread.sleep(5000);


/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			//login as instructor and verify PIN is reset
			login.ClickOnLogoutButton();
			login.EnterUsername(username);
			login.EnterPassword(password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("css", "#page li:nth-of-type(2) a");
			Thread.sleep(5000);

			String PIN = SeleniumFunc.GetAttributeValue("css", "#pin", "value"); 
			String ConfirmPIN = SeleniumFunc.GetAttributeValue("css", "#pin_confirm", "value"); 
			if(PIN.equals("") && ConfirmPIN.equals(""))
			{
				System.out.println("Success !! Reset PIN functionality is working properly");
				Reporter.log("Success !! Reset PIN functionality is working properly"); 
				
			}
			else
			{
				System.out.println("Failure !! Reset PIN functionality is NOT working properly");
				Reporter.log("Failure !! Reset PIN functionality is NOT working properly"); 
				
				AssertFailedCount++;
			}
			

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/

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
	

	
	/* Test 1: 
	 * EM > Show Event Name on Action Required Lists
	*/ 
	@Test
	private void EventName_ActionRequired() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 1 : Verify that New User with admin role can be created successfully"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 1 : Verify that New User with admin role can be created successfully"  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
				
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(5000);
		
		System.out.println("Step 2 : Logging in application");
		Reporter.log("Step 2 : Logging in application"); 
						
			login.EnterUsername(Constants.EM_CLInstructor_Username);
			login.EnterPassword(Constants.EM_CLInstructor_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
		
		System.out.println("Step 2 : Select a program and verify sections - Action Required");
		Reporter.log("Step 2 : Select a program and verify sections - Action Required"); 

			SeleniumFunc.ClickOnElement("css", ".nav.nav-list>li:nth-of-type(1)>a");
			Thread.sleep(4000);
			
			//Verify section Action Required: Pending Events
			String actualtext = SeleniumFunc.GetElementText("css", ".span9>h3:nth-of-type(1)");
			String expectedtext = "Action Required: Pending Events";
			
			if(expectedtext.equals(actualtext))
			{
				System.out.println("Success !! Correct header is present i.e. " + actualtext);
				Reporter.log("Success !! Correct header is present i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect Header " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect Header " + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
						
				AssertFailedCount++;
			}
			
			//Verify section Action Required: Submitted Events
			actualtext = SeleniumFunc.GetElementText("css", ".span9>h3:nth-of-type(2)");
			expectedtext = "Action Required: Submitted Events";
			
			if(expectedtext.equals(actualtext))
			{
				System.out.println("Success !! Correct header is present i.e. " + actualtext);
				Reporter.log("Success !! Correct header is present i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect Header " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect Header " + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
						
				AssertFailedCount++;
			}
			
			//Go to first event and verify title
			System.out.println("Step 2 : Select first event and verify control is on administration page");
			Reporter.log("Step 2 : Select first event and verify control is on administration page"); 
			
				SeleniumFunc.ClickOnElement("css", "#page table:nth-of-type(1) tr:nth-of-type(1) td a");
				Thread.sleep(4000);
				
				
				//Verify section Action Required: Submitted Events
				actualtext = SeleniumFunc.GetElementText("css", ".span9>h3");
				expectedtext = "Event Administration";
				
				if(expectedtext.equals(actualtext))
				{
					System.out.println("Success !! Correct header is present i.e. " + actualtext);
					Reporter.log("Success !! Correct header is present i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !!  Incorrect Header " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Incorrect Header " + "\n" + "Expected Text: "  
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
	
	/* Test 4: 
	 * Prevent duplicate instructor number entry on create Run
	*/
	
	@Test()
	private void DuplicateInstrNoOnEditNew() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 4 : Verify that system is not allowing to edit instructor with already used Instructor No."  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 4 : Verify that system is not allowing to edit instructor with already used Instructor No."  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);

		
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

		
		System.out.println("Step 3 : Choose the “New York Department of Environmental Conservation” agency");
		Reporter.log("Step 3 : Choose the “New York Department of Environmental Conservation” agency"); 
		
			SeleniumFunc.ClickOnElement("linkText", "New York Department of Environmental Conservation");
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("linkText", "Users");
			//agency.ClickOnUserButton();
			Thread.sleep(5000);

		
		System.out.println("Step 4 : Click on the first user (Abbott, Daniel C), then on the View Instructor page, click the Edit button");
		Reporter.log("Step 4 : Click on the first user (Abbott, Daniel C), then on the View Instructor page, click the Edit button"); 
			
			SeleniumFunc.ClickOnElement("linkText", "Abbott, Ryan M");
			//agency.ClickOnCreateUserButton();
			Thread.sleep(1000);
			SeleniumFunc.ClickOnElement("linkText", "Edit");
			Thread.sleep(2000);

			
			String instrnumber = "05220029";
			//String instrnumberb = "";
			
			//SeleniumFunc.ClickOnElement("css", "instructor_number_h");
			//agency.ClickToInstrNo();
			//agency.ClickToInstrNo_b();
			
			agency.EnterInstrNoH(instrnumber);
			//agency.EnterInstrNoB(instrnumberb);
			System.out.println("Instructor Number : "+instrnumber);
			
			System.out.println("Step 5 : Save record with updated details");
			Reporter.log("Step 5 : Save record with updated details");
			
			agency.ClickOnSaveChangesButton();

			Thread.sleep(5000);

	
		System.out.println("Step 6 : Verifying validation fired for already used instructor number");
		Reporter.log("Step 6 : Verifying validation fired for already used instructor number"); 
		

			if(SeleniumFunc.IsElementPresent("id", "error_pd_instructor_number_h"))
			{
				
				System.out.println("Success !! Validation is fired for already used instructor number");
				Reporter.log("Success !! Validation is fired for already used instructor number");
			
			}
			else
			{
		
				System.out.println("Failure !! Validation is not fired for already used instructor number");
				Reporter.log("Failure !! Validation is not fired for already used instructor number");
				
				AssertFailedCount++;
			
			}
		
	
	
	 /** Marking Test Pass or Fail as per the value of AssertFailedCount variable*/
	 
			if(AssertFailedCount>0)	
			{
			
				//Marking this test as Failed
			
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
				Assert.fail();
		
			}
	}
	
	/* Test 12: 
	 * Verify Reset PIN functionality
	 */	
	
	@Test
	private void VerifyPINResetNew() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 12 : Verify Reset PIN functionality"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 12 : Verify Reset PIN functionality"  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);

		
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
		
			Thread.sleep(5000);

		System.out.println("Step 2 : Log in to EM as an instructor");
		Reporter.log("Step 2 : Log in to EM as an instructor"); 
				
			
			login.EnterUsername(Constants.EM_ResetPIN_Username);
			login.EnterPassword(Constants.EM_ResetPIN_Password);
			login.ClickOnLogInButton();
		
			Thread.sleep(5000);
			
		System.out.println("Step 3 : Navigate to the instructor profile page and update the PIN");
		Reporter.log("Step 3 : Navigate to the instructor profile page and update the PIN"); 
		
			SeleniumFunc.ClickOnElement("linkText", "Profile");
			Thread.sleep(2000);
			agency.Enter_PIN();
			agency.Enter_CPIN();
			agency.ClickOnSaveChangesButton();
			Thread.sleep(2000);
		
		System.out.println("Step 4 : Log out and log in as a KE admin");
		Reporter.log("Step 4 : Log out and log in as a KE admin"); 
		
			login.ClickOnLogoutButton();
			Thread.sleep(2000);
			
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
		
			Thread.sleep(5000);
			
		System.out.println("Step 5 : Navigate to the agency of the instructor");
		Reporter.log("Step 5 : Navigate to the agency of the instructor");
		
			SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("linkText", "Colorado Hunter Education Course");
			Thread.sleep(2000);
			
		System.out.println("Step 6 : Search for the instructor");
		Reporter.log("Step 6 : Search for the instructor");
			
			SeleniumFunc.ClickOnElement("linkText", "Search Instructors");
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("css","#search_form > div.btn-group.bootstrap-select.span3.col-ignore > button");
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore.open > div > ul > li:nth-child(2) > a");
			Thread.sleep(2000);
			agency.Enter_Instructor();
			agency.ClickOnSearchButton();
			Thread.sleep(2000);
			
		
		System.out.println("Step 6 : Edit the instructor data by checking the box 'Reset PIN', and saving the change");
		Reporter.log("Step 6 : Edit the instructor data by checking the box 'Reset PIN', and saving the change");
			
			SeleniumFunc.ClickOnElement("css", "#proctor_select_form > table > tbody > tr.even > td:nth-child(2) > a:nth-child(2)");
			Thread.sleep(2000);
			
			if(!agency.resetPIN.isSelected())
			{
				agency.resetPIN.click();
			}
		
			agency.ClickOnSaveChangesButton();
			Thread.sleep(2000);
			
		System.out.println("Step 6 : Log out and log back in as the instructor");
		Reporter.log("Step 6 : Log out and log back in as the instructor");
		
			login.ClickOnLogoutButton();
			Thread.sleep(2000);
			
			login.EnterUsername(Constants.EM_ResetPIN_Username);
			login.EnterPassword(Constants.EM_ResetPIN_Password);
			login.ClickOnLogInButton();
		
			Thread.sleep(5000);
		
		System.out.println("Step 6 : Navigate to the instructor profile and verify that the PIN field is empty");
		Reporter.log("Step 6 : Navigate to the instructor profile and verify that the PIN field is empty");
			
			SeleniumFunc.ClickOnElement("linkText", "Profile");
			Thread.sleep(2000);
			
			String actual = SeleniumFunc.GetAttributeValue("id", "pin", "placeholder");
			if(actual.equals(""))
			{
				System.out.println("Success !! PIN field is empty");
				Reporter.log("Success !! PIN field is empty");
			
			}
			else
			{
		
				System.out.println("Failure !! PIN field is not empty");
				Reporter.log("Failure !! PIN field is not empty ");
				
				AssertFailedCount++;
			
			}
		
	
	
	 /** Marking Test Pass or Fail as per the value of AssertFailedCount variable*/
	 
			if(AssertFailedCount>0)	
			{
			
				//Marking this test as Failed
			
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
				Assert.fail();
		
			}
	}
	
	/* Test 13: 
	 * Verify Customer Service Role
	 * Test Case ID : 21065
	 */	
	
	@Test
	private void Verify_CustomerServiceRole() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 13 : Verify Customer Service Role"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 13 : Verify Customer Service Role"  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyCreateUserPage agency  = new AgencyCreateUserPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		AdminSearchEventPage adminsearch = new AdminSearchEventPage(driver);
		

		
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			
			if(SeleniumFunc.IsElementPresent("linkText", "Log Out"))
			{
				SeleniumFunc.ClickOnElement("linkText", "Log Out");
			}
		
			Thread.sleep(5000);

		System.out.println("Step 2 : Log in to EM as Customer Service Role.");
		Reporter.log("Step 2 : Log in to EM as Customer Service Role."); 
				
			
			login.EnterUsername(Constants.EM_CustomerServiceRole_Username);
			login.EnterPassword(Constants.EM_CustomerServiceRole_Password);
			login.ClickOnLogInButton();
			
		System.out.println("Step 3 : Verify User should be on Kalkomey Administration page.");
		Reporter.log("Step 3 : Verify User should be on Kalkomey Administration page."); 
		
			if(SeleniumFunc.GetElementText("css", ".page-header-compressed>h2").equals("Kalkomey Administration"))
			{
				System.out.println("Success !! User is on Kalkomey Administration Page.");
				Reporter.log("Success !! User is on Kalkomey Administration Page.");			
			}
			else
			{		
				System.out.println("Failure !! User redirected to incorrect page.");
				Reporter.log("Failure !! User redirected to incorrect page.");				
				AssertFailedCount++;			
			}
			
		System.out.println("Step 4 : Verify under SYSTEM SEARCHES , Registrations and Event link present.");
		Reporter.log("Step 4 : Verify under SYSTEM SEARCHES , Registrations and Event link present."); 
			
			if(SeleniumFunc.IsElementPresent("linkText", "Registrations")&&
					SeleniumFunc.IsElementPresent("linkText", "Events")&&
					SeleniumFunc.GetElementText("css", "#page > div.content.clearfix > div.row-fluid > div.span3 > div > ul > li:nth-child(2)").equalsIgnoreCase("System Searches"))
			{
				System.out.println("Success !! System Searches section is available with Events and Registrations link.");
				Reporter.log("Success !! System Searches section is available with Events and Registrations link.");			
			}
			else
			{		
				System.out.println("Failure !! SYSTEM SEARCHES section missing.");
				Reporter.log("Failure !! SYSTEM SEARCHES section missing.");				
				AssertFailedCount++;			
			}
			
		System.out.println("Step 5 : Verify You should not see CONFIGURATION with Kalkomey Users, Provider Tokens, and Config Options.");
		Reporter.log("Step 5 : Verify You should not see CONFIGURATION with Kalkomey Users, Provider Tokens, and Config Options.");
			
		 	if(!(SeleniumFunc.IsElementPresent("css", "div.span3 > div > ul > li:nth-child(8)")))
		 	{
		 		System.out.println("Success !! Configuration Section is not present for Customer Service role.");
				Reporter.log("Success !!  Configuration Section is not present for Customer Service role.");			
			}
			else
			{		
				System.out.println("Failure !! Configuration Section is present.");
				Reporter.log("Failure !! Configuration Section is present.");				
				AssertFailedCount++;			
			}
		 	
		System.out.println("Step 6 : Verify list of agencies should be present.");
		Reporter.log("Step 6 : Verify list of agencies should be present.");
		
			if(SeleniumFunc.IsElementPresent("css", ".span11"))
			{
				System.out.println("Success !! List of agencies present.");
				Reporter.log("Success !! List of agencies present.");			
			}
			else
			{		
				System.out.println("Failure !! List of agencies not present.");
				Reporter.log("Failure !! List of agencies not present.");				
				AssertFailedCount++;			
			}
			
		System.out.println("Step 7 : Click on Registrations and verify search form available.");
		Reporter.log("Step 7 : Click on Registrations and verify search form available.");
				
				SeleniumFunc.ClickOnElement("linkText", "Registrations");
				
			if(SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2").contains("Search for Registrations"))
			{
				System.out.println("Success !! Search form available.");
				Reporter.log("Success !! Search form available.");			
			}
			else
			{		
				System.out.println("Failure !! Redirected to invalid page.");
				Reporter.log("Failure !! Redirected to invalid page.");				
				AssertFailedCount++;			
			}
		
		System.out.println("Step 8 : Choose Arkansas, enter John, and click Search button.");
		Reporter.log("Step 8 : Choose Arkansas, enter John, and click Search button.");
		
				adminsearch.Country.click();
				adminsearch.SelectCountryByVisibleText("Arkansas");
				adminsearch.Enter_SearchData();
				SeleniumFunc.ClickOnElement("id", "search-button");
				
		System.out.println("Step 9 : Verify record should display a list of name contains John.");
		Reporter.log("Step 9 : Verify record should display a list of name contains John.");
				
				List<WebElement> li = driver.findElements(By.cssSelector("td:nth-child(2) > a"));
				for(int i=0; i<li.size();i++)
				{
					if(li.get(i).getText().contains("John"))
					//if(li.contains("John"))
					{
						System.out.println("Success !! List of record with name John is present");
						Reporter.log("Success !! List of record with name John is present");	
					}
					else
					{
						System.out.println("Failure !! Invalid Record found.");
						Reporter.log("Failure !! Invalid Record found.");
						AssertFailedCount++;
					}
				}
			
		System.out.println("Step 10 :Click on one of the student's names who is not canceled.");
		Reporter.log("Step 10 : Click on one of the student's names who is not canceled.");
		
					SeleniumFunc.ClickOnElement("linkText", "Johnson, Zach");
				
		System.out.println("Step 11 : Verify Registration information should be present.");
		Reporter.log("Step 11 : Verify Registration information should be present.");
			
			//if(SeleniumFunc.GetElementText("css", ".span10.tooltip-container>h4").equals("Registration Information"))
			if(SeleniumFunc.IsElementPresent("css", ".muted"))
			{
				System.out.println("Success !! Registration information is present.");
				Reporter.log("Success !! Registration information is present.");			
			}
			else
			{		
				System.out.println("Failure !! Registration information is not present.");
				Reporter.log("Failure !! Registration information is not present.");				
				AssertFailedCount++;			
			}
			
		System.out.println("Step 12 : Verify SUPPORT ACTIONS & Comments section should be present.");
		Reporter.log("Step 12 : Verify SUPPORT ACTIONS & Comments section should be present.");
		
			if(SeleniumFunc.IsElementPresent("css", ".nav-header")&&
					SeleniumFunc.IsElementPresent("name", "Comment[message]"))
			{
				System.out.println("Success !! SUPPORT ACTIONS & Comments section is present.");
				Reporter.log("Success !! SUPPORT ACTIONS & Comments section is present.");		
			}
			else
			{
				System.out.println("Failure !! SUPPORT ACTIONS & Comments section is missing.");
				Reporter.log("Failure !! SUPPORT ACTIONS & Comments section is missing.");				
				AssertFailedCount++;		
			}
		
		System.out.println("Step 13 : Go back to the Kalkomey Administration page and click Events on the left.");
		Reporter.log("Step 13 : Go back to the Kalkomey Administration page and click Events on the left.");
			
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
				SeleniumFunc.ClickOnElement("linkText", "Events");
				
		System.out.println("Step 14 : Verify Search page for events should be present.");
		Reporter.log("Step 14 : Verify Search page for events should be present.");
				
			if(SeleniumFunc.GetElementText("css", ".page-header-compressed > h2").equals("Search for Events"))
			{
				System.out.println("Success !! Search page for events is present.");
				Reporter.log("Success !! Search page for events is present.");		
			}
			else
			{
				System.out.println("Failure !! Search page for events is missing.");
				Reporter.log("Failure !! Search page for events is missing.");				
				AssertFailedCount++;	
			}
			
		System.out.println("Step 15 : Choose Upcoming, type data, and hit Search.");
		Reporter.log("Step 15 : Choose Upcoming, type 50, and hit Search.");
				
				SeleniumFunc.ClickOnElement("css", ".filter-option.pull-left");
				adminsearch.SelectSearchTypeByVisibleText("Upcoming");
				SeleniumFunc.EnterValueInTextbox("id", "search-type-upcoming", "4000");
				adminsearch.SearchButton.click();
				
		System.out.println("Step 16 : Verify List of events present.");
		Reporter.log("Step 16 : Verify List of events present.");
				
				
			if(SeleniumFunc.IsElementPresent("css", "td:nth-child(4)"))
			{
				System.out.println("Success !!  List of events Displayed.");
				Reporter.log("Success !!  List of events Displayed.");		
			}
			else
			{
				System.out.println("Failure !! Record not found. List of events missing.");
				Reporter.log("Failure !! Record not found. List of events missing.");				
				AssertFailedCount++;	
			}
			
		System.out.println("Step 17 : Click on the name of an event.");
		Reporter.log("Step 17 : Click on the name of an event.");
		
				SeleniumFunc.ClickOnElement("linkText", "Demo Hunter Education Field Day");
				
			if(SeleniumFunc.GetElementText("css", ".span9>h3").equalsIgnoreCase("Event Administration"))
			{
				System.out.println("Success !!  Redirected to Event Administration page.");
				Reporter.log("Success !!   Redirected to Event Administration page.");		
			}
			else
			{
				System.out.println("Failure !! Redirected to Invalid page.");
				Reporter.log("Failure !! Redirected to Invalid page.");				
				AssertFailedCount++;	
			}
		
		System.out.println("Step 18 : Go back to the Kalkomey Administration page and click on agency's name.");
		Reporter.log("Step 18 : Go back to the Kalkomey Administration page and click on agency's name.");
		
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/agency/kechooseagency");
				SeleniumFunc.ClickOnElement("linkText", "Arkansas Game and Fish Commission");
				
		System.out.println("Step 19 : Verify List of the programs for that agency is present.");
		Reporter.log("Step 19 : Verify List of the programs for that agency is present.");
		
			if(SeleniumFunc.IsElementPresent("css", ".span9>a"))
			{
				System.out.println("Success !! Displayed list of programs.");
				Reporter.log("Success !! Displayed list of programs.");		
			}
			else
			{
				System.out.println("Failure !! List of programs not found.");
				Reporter.log("Failure !! List of programs not found.");				
				AssertFailedCount++;	
			}
				
		System.out.println("Step 20 : Verify List of administrators for that agency should be present.");
		Reporter.log("Step 20 :Verify List of administrators for that agency should be present.");
		
			if(SeleniumFunc.IsElementPresent("css", ".span7>a"))
			{
				System.out.println("Success !! Displayed list of administrators.");
				Reporter.log("Success !! Displayed list of administrators.");		
			}
			else
			{
				System.out.println("Failure !! List of administrators not found.");
				Reporter.log("Failure !! List of administrators not found.");				
				AssertFailedCount++;	
			}
			
		System.out.println("Step 21 : Next to each administrator you should not see a Remove or Delete link.");
		Reporter.log("Step 21 : Next to each administrator you should not see a Remove or Delete link.");
				
			if(!(SeleniumFunc.IsElementPresent("linkText", "Remove")))
			{
				System.out.println("Success !! Remove or Delete link is not available.");
				Reporter.log("Success !! Remove or Delete link is not available.");		
			}
			else
			{
				System.out.println("Failure !! Remove or Delete link is present.");
				Reporter.log("Failure !! Remove or Delete link is present.");				
				AssertFailedCount++;	
			}
			
		System.out.println("Step 22 : Verify On the left AGENCY SEARCHES and Users link should be present.");
		Reporter.log("Step 22: Verify On the left AGENCY SEARCHES and Users link not present.");
		
			if(SeleniumFunc.IsElementPresent("linkText", "Users") &&
					SeleniumFunc.IsElementPresent("css", ".nav-header"))
			{
				System.out.println("Success !! Agency section & Users link available.");
				Reporter.log("Success !! Agency section & Users link available.");		
			}
			else
			{
				System.out.println("Failure !! Agency section & Users link missing.");
				Reporter.log("Failure !! Agency section & Users link missing.");				
				AssertFailedCount++;	
			}
			
		System.out.println("Step 23 : Verify on the left you should not see CONFIGURATION and Agency Settings and Detail Questions.");
		Reporter.log("Step 23 : Verify on the left you should not see CONFIGURATION and Agency Settings and Detail Questions.");
			
			if(!(SeleniumFunc.IsElementPresent("linkText", "Detail Questions"))
					&&!(SeleniumFunc.IsElementPresent("linkText", "Agency Settings"))
					&&!(SeleniumFunc.IsElementPresent("css", "div.span3 > ul > li:nth-child(7)")))
				
			{
				System.out.println("Success !! CONFIGURATION, Agency Settings and Detail Questions not available.");
				Reporter.log("Success !! CONFIGURATION, Agency Settings and Detail Questions nnot available.");		
			}
			else
			{
				System.out.println("Failure !! CONFIGURATION, Agency Settings and Detail Questions displayed.");
				Reporter.log("Failure !! CONFIGURATION, Agency Settings and Detail Questions displayed.");				
				AssertFailedCount++;	
			}
			
		System.out.println("Step 24 : From list of program , Click on a program's name.");
		Reporter.log("Step 24 : From list of program , Click on a program's name.");
				
				List<WebElement> ele = driver.findElements(By.cssSelector(".span9>a"));
				ele.get(1).click();
				
		System.out.println("Step 25 : Verify menu on the left with EVENTS, STUDENTS, INSTRUCTORS, LOCATIONS, PROGRAM SETTINGS should be present.");
		Reporter.log("Step 25 :Verify menu on the left with EVENTS, STUDENTS, INSTRUCTORS, LOCATIONS, PROGRAM SETTINGS should be present.");
				
				List<WebElement> list1 = driver.findElements(By.cssSelector(".nav-header"));
				/*String expected = "EVENTS"+ "\n" +
								  "STUDENTS"+ "\n" +
								  "INSTRUCTORS"+ "\n" +
								  "LOCATIONS"+ "\n" +
								  "PROGRAM SETTINGS";*/
								  
			if(list1.get(0).getText().equalsIgnoreCase("EVENTS") && 
					list1.get(1).getText().equalsIgnoreCase("STUDENTS")&&
					list1.get(2).getText().equalsIgnoreCase("INSTRUCTORS")&&
					list1.get(3).getText().equalsIgnoreCase("LOCATIONS")&& list1.get(4).getText().equalsIgnoreCase("PROGRAM SETTINGS"))
			{
					
				System.out.println("Success !! Menu options present");
				Reporter.log("Success !! Menu options present");	
			}
			else
			{
				System.out.println("Failure !! Menu options missing.");
				Reporter.log("Failure !! Menu options missing.");
				AssertFailedCount++;
			}
		System.out.println("Step 26 : Verify should not see CONFIGURATION on the left.");
		Reporter.log("Step 26: Verify should not see CONFIGURATION on the left.");
				
			if(!(SeleniumFunc.IsElementPresent("css", "div.span3 > ul > li:nth-child(7)")))
			{
				System.out.println("Success !! CONFIGURATION section not available.");
				Reporter.log("Success !! CONFIGURATION section not available.");		
			}
			else
			{
				System.out.println("Failure !! CONFIGURATION section available.");
				Reporter.log("Failure !! CONFIGURATION section available.");				
				AssertFailedCount++;	
			}
			
			/** Marking Test Pass or Fail as per the value of AssertFailedCount variable*/
			 
			if(AssertFailedCount>0)	
			{
			
				//Marking this test as Failed
			
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
				Assert.fail();
		
			}
	}
}