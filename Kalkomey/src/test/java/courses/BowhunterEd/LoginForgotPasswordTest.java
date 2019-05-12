package courses.BowhunterEd;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.ContentsPage;
import pageFactory.Courses.ForgotPasswordPage;
import pageFactory.Courses.LoginPage;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.RegistrationPage;
import pageFactory.Courses.ResetPasswordPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class LoginForgotPasswordTest 
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
	
	
	/* Test 1: 
	 * Verify UI of Login page
	*/ 
	@Test
	private void Login_UIElementVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify UI of Login page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify UI of Login page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);

		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_BowHunter + "/site/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_BowHunter + "/site/login"); 
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
			}
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
	
		System.out.println("Step 2 : Verifying whether page title is correctly displayed on not");
		Reporter.log("Step 2 : Verifying whether page title is correctly displayed on not"); 
			
			if(SeleniumFunc.GetElementText("css", "div.page-header h1").equals("Log In"))
			{
				System.out.println("Success !! Page title is correct");
				Reporter.log("Success !! Page title is correct"); 
			}
			else
			{
				System.out.println("Failure !!  Page title is incorrect ");
				Reporter.log("Failure !!  Page title is incorrect "); 
				AssertFailedCount++;
			}
			
		System.out.println("Step 3 : Verifying presence of UI elements : Username Textbox,Password Textbox,Forgot Password Link,Log In Button ");
		Reporter.log("Step 3 : Verifying presence of UI elements : Username Textbox,Password Textbox,Forgot Password Link,Log In Button "); 	
			
			if(SeleniumFunc.IsElementPresent("id", "LoginForm_username"))
			{
				
				System.out.println("Success !! Username Textbox is present");
				Reporter.log("Success !! Username Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! Username Textbox is NOT present ");
				Reporter.log("Failure !! Username Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			
			if(SeleniumFunc.IsElementPresent("id", "LoginForm_password"))
			{
				
				System.out.println("Success !! Password Textbox is present");
				Reporter.log("Success !! Password Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! Password Textbox is NOT present ");
				Reporter.log("Failure !! Password Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			
			if(SeleniumFunc.IsElementPresent("css", "#loginForm input[value='Log In']"))
			{
				
				System.out.println("Success !! Log In button is present");
				Reporter.log("Success !! Log In button is present"); 
			}
			else
			{
				System.out.println("Failure !! Log In button is NOT present ");
				Reporter.log("Failure !! Log In button is NOT present "); 
				AssertFailedCount++;
			}
			
			
			if(SeleniumFunc.IsElementPresent("css", "a[href='/site/forgetPassword']"))
			{
				
				System.out.println("Success !! Forgot Password link is present");
				Reporter.log("Success !! Forgot Password link is present"); 
			}
			else
			{
				System.out.println("Failure !! Forgot Password link is NOT present");
				Reporter.log("Failure !! Forgot Password link is NOT present"); 
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
	 * Verify that user can login with valid credentials
	*/ 
	@Test
	private void Login_LoginSuccessful() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify that user can login with valid credentials"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify that user can login with valid credentials"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		RegistrationPage register = new RegistrationPage(driver);
		PageHeader header = new PageHeader(driver);

		System.out.println("Pre-requisite : Signing up as new User");
		Reporter.log("Pre-requisite : Signing up as new User"); 
			
			String username= "login" + JH.GenerateRandomNumber();
			String emailprefix = "login" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			Thread.sleep(4000);
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
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "5", "1990", emailaddress, emailaddress);
			
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}		
			
			//Logging out of application
			SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");
			Thread.sleep(4000);

	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_BowHunter + "/site/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_BowHunter + "/site/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
			Thread.sleep(4000);

		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername(username);
			login.EnterPassword(password);
			login.ClickOnLogInButton();
			Thread.sleep(4000);

		
		System.out.println("Step 3 : Verifying whether user is logged successfully or not");
		Reporter.log("Step 4 : Verifying whether user is logged successfully or not"); 
			
			
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
	

	/* Test 3: 
	 * Verify that user can't login with invalid credentials and proper validation messages are displayed 
		A) Keep both Username/Password blank"
	*/ 
	@Test
	private void Login_LoginFailure_BlankInputs() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify that user can't login with invalid credentials and proper validation messages are displayed  A) Keep both Username/Password blank"  + "\n" + "====");
		Reporter.log("====" + "\n" +
				"Test 3 : Verify that user can't login with invalid credentials and proper validation messages are displayed  A) Keep both Username/Password blank"  + "\n" + "====");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		PageHeader header = new PageHeader(driver);

		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_BowHunter + "/site/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_BowHunter + "/site/login"); 
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
			header.Logout_Handgun.click();
			Thread.sleep(4000);
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);

		}
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
	
		System.out.println("Step 2 : Clicking on Login button without entering any details");
		Reporter.log("Step 2 : Clicking on Login button without entering any details"); 
		
			login.ClickOnLogInButton();
			Thread.sleep(4000);

		System.out.println("Step 3 : Verifying whether validation messages are displayed for mandatory fields");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for mandatory fields"); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#loginForm div:nth-of-type(2) p") + 
					SeleniumFunc.GetElementText("css", "#loginForm div:nth-of-type(3) p");
			System.out.println(ActualValidationMessage);

			String ExpectedValidationMessage= 
			"Please complete this field." +
			"Please complete this field."; 
			
			
			if(ActualValidationMessage.equals(ExpectedValidationMessage))
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
	 * Verify that user can't login with invalid credentials and proper validation messages are displayed 
		B) Enter incorrect values in Username/Password fields
	*/ 
	@Test
	private void Login_LoginFailure_InvalidInputs() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify that user can't login with invalid credentials and proper validation messages are displayed  B) Enter incorrect values in Username/Password fields"  + "\n" + "====");
		Reporter.log("====" + "\n" +
				"Test 4 : Verify that user can't login with invalid credentials and proper validation messages are displayed B) Enter incorrect values in Username/Password fields"  + "\n" + "====");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		PageHeader header = new PageHeader(driver);


		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_BowHunter + "/site/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_BowHunter + "/site/login"); 
		Thread.sleep(4000);
		if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
		{
			header.Logout_Handgun.click();
			Thread.sleep(4000);
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);

		}
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
	
		System.out.println("Step 2 : Entering incorrect data in Username/Password field and Clicking on Login button ");
		Reporter.log("Step 2 : Entering incorrect data in Username/Password field and Clicking on Login button "); 
			
			login.EnterUsername("username");
			login.EnterPassword("password");
			login.ClickOnLogInButton();
			Thread.sleep(4000);

		System.out.println("Step 3 : Verifying whether validation messages are displayed");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed "); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#loginForm div[class='alert alert-block alert-error'] p") + "\n" + 
					SeleniumFunc.GetElementText("css", "#loginForm div[class='alert alert-block alert-error'] li");
			System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage= 
					"Please review the following:" + "\n" + 
					"That username and password wasn't valid. Please check them and try again."; 
			
			if(ActualValidationMessage.equals(ExpectedValidationMessage))
			{
				System.out.println("Success !! correct validation messages are displayed ");
				Reporter.log("Success !! correct validation messages are displayed "); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
									+ "\n" + ExpectedValidationMessage + "\n" + 
									 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
				Reporter.log("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
						+ "\n" + ExpectedValidationMessage + "\n" + 
						 "Actual Validation messages : " + "\n" + ActualValidationMessage); 
				
				AssertFailedCount++;
			}
			
			
			ActualValidationMessage= SeleniumFunc.GetElementText("css", "#loginForm div[class='control-group error'] div div");
			System.out.println(ActualValidationMessage);
			
			ExpectedValidationMessage= 
					"That username and password wasn't valid. Please check them and try again."; 
			
			if(ActualValidationMessage.equals(ExpectedValidationMessage))
			{
				System.out.println("Success !! correct validation messages are displayed ");
				Reporter.log("Success !! correct validation messages are displayed "); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
									+ "\n" + ExpectedValidationMessage + "\n" + 
									 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
				Reporter.log("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
						+ "\n" + ExpectedValidationMessage + "\n" + 
						 "Actual Validation messages : " + "\n" + ActualValidationMessage); 
				
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
	 * Verify that user can't access the restricted pages without login
	*/ 
	@Test(dataProvider="Login_RestrictedPages_bowhunter",dataProviderClass=utility.TestNG.class)
	private void Login_RestrictedPages(String restricted_pages) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify that user can't access the restricted pages without login"  + "\n" + "====");
		Reporter.log("====" + "\n" +
				"Test 5 : Verify that user can't access the restricted pages without login"  + "\n" + "====");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);

		System.out.println("Step 1 : Trying to access Restricted Pages and verifying whether user is navigated to Home page or not");
		Reporter.log("Step 1 : Trying to access Restricted Pages and verifying whether user is navigated to Home page or not"); 
			
			restricted_pages = Constants.ApplicationURL_BowHunter + restricted_pages;
			SeleniumFunc.ToGoToUrl(restricted_pages);
			Thread.sleep(4000);

				if(SeleniumFunc.ToGetCurrentPageUrl().contains(Constants.ApplicationURL_BowHunter.subSequence(5,Constants.ApplicationURL_BowHunter.length())))
				{
					System.out.println("Success !! User is navigated to Home page");
					Reporter.log("Success !! User is navigated to Home page"); 
				}
				else
				{
					System.out.println("Failure !!  User is NOT navigated to Home page , instead navigated to: " + SeleniumFunc.ToGetCurrentPageUrl());
					Reporter.log("Failure !!  User is NOT navigated to Home page , instead navigated to: " + SeleniumFunc.ToGetCurrentPageUrl()); 
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
	 * Verify that user can logout successfully
	*/ 
	@Test
	private void Login_Logout() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify that user can logout successfully"  + "\n" + "====");
		Reporter.log("====" + "\n" +
				"Test 6 : Verify that user can logout successfully"  + "\n" + "====");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		PageHeader header = new PageHeader(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "login" + JH.GenerateRandomNumber();
			String emailprefix = "login" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			Thread.sleep(4000);
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
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "5", "1990", emailaddress, emailaddress);
			
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}		
			
			
		System.out.println("Step 2: Logging out of application");
		Reporter.log("Step 2: Logging out of application"); 
				
			SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");
			Thread.sleep(4000);

			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter+ "/site/logout'");
	
		System.out.println("Step 3 : Verifying whether user is logged out successfully or not");
		Reporter.log("Step 4 : Verifying whether user is logged out successfully or not"); 
			
				if(SeleniumFunc.ToGetCurrentPageUrl().contains(Constants.ApplicationURL_BowHunter.subSequence(5,Constants.ApplicationURL_BowHunter.length())+ "/site/logout"))
				{
					System.out.println("Success !! User is navigated to Logout page");
					Reporter.log("Success !! User is navigated to Logout page"); 
				}
				else
				{
					System.out.println("Failure !!  User is NOT navigated to Logout page , instead navigated to: " + SeleniumFunc.ToGetCurrentPageUrl());
					Reporter.log("Failure !!  User is NOT navigated to Logout page , instead navigated to: " + SeleniumFunc.ToGetCurrentPageUrl()); 
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
	 * To verify user can generate password retrieval email
	 
	@Test
	private void Login_PasswordResetEmail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : To verify user can generate password retrieval email"  + "\n" + "====");
		Reporter.log("====" + "\n" +
				"Test 7 : To verify user can generate password retrieval email"  + "\n" + "====");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		LoginPage login = new LoginPage(driver);
		ForgotPasswordPage forgotpassword = new ForgotPasswordPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "loginhunter" + JH.GenerateRandomNumber();
			String emailprefix = "login" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + "/register/create");
			//For few States , closing modal dialog present on Registration page
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "5", "1990", emailaddress, emailaddress);
			
			
		System.out.println("Step 2: Logging out of application");
		Reporter.log("Step 2: Logging out of application"); 
				
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter+ "/site/logout");
			
	
		System.out.println("Step 3 : Navigating to Login page and clicking on 'Forgot Password' link");
		Reporter.log("Step 3 : Navigating to Login page and clicking on 'Forgot Password' link"); 
			
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
				login.ClickOnForgotPasswordLink();
				
		System.out.println("Step 4 : Entereing Username/Email address and clicking on 'Send Me the Instruction' button ");
		Reporter.log("Step 4 : Entereing Username/Email address and clicking on 'Send Me the Instruction' button ");
				
				forgotpassword.EnterUsername(username);
				forgotpassword.EnterEmail(emailaddress);
				forgotpassword.ClickOnSendMeInstructionButton();
		
	
		System.out.println("Step 4 : Verifying whether confirmation messages are displayed");
		Reporter.log("Step 4 : Verifying whether confirmation messages are displayed "); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#main div[class='alert alert-success fade in']");
			//System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage= 
					"An email has been sent to you with the instructions to reset your password. Thank you."; 
			
			if(ActualValidationMessage.contains(ExpectedValidationMessage))
			{
				System.out.println("Success !! correct validation messages are displayed ");
				Reporter.log("Success !! correct validation messages are displayed "); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
									+ "\n" + ExpectedValidationMessage + "\n" + 
									 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
				Reporter.log("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
						+ "\n" + ExpectedValidationMessage + "\n" + 
						 "Actual Validation messages : " + "\n" + ActualValidationMessage); 
				
				AssertFailedCount++;
			}
				
			
	
		System.out.println("Step 5 : Verifying whether Password Reset Email sent to user or not");
		Reporter.log("Step 5 : Verifying whether Password Reset Email sent to user or not"); 
		
			String EmailBoxUrl = "http://mailinator.com/inbox.jsp?to=" + emailprefix;
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
			
			String ActualMessageTitle= SeleniumFunc.GetElementText("css", "div#InboxCtrl div[class='subject ng-binding']").trim();
			//System.out.println(ActualMessageTitle);
											
			String ExpectedMessageTitle="Reset your password"; 
			//System.out.println(ExpectedMessageTitle);
			
			if(ActualMessageTitle.equalsIgnoreCase(ExpectedMessageTitle))
			{
				System.out.println("Success!! Email is received with correct Title");
				Reporter.log("Success!! Email is received with correct Title"); 
			}
			else
			{
				System.out.println("Failure !!  Email is received with incorrect Title" + "\n" + "Expected Text: "  
									+ "\n" + ExpectedMessageTitle + "\n" + 
									 "Actual Text : " + "\n" +  ActualMessageTitle);
				Reporter.log("Failure !!Email is received with incorrect Title" + "\n" + "Expected Text: "  
						+ "\n" + ExpectedMessageTitle + "\n" + 
						 "Actual Text : " + "\n" +  ActualMessageTitle);
				
				AssertFailedCount++;
			}
		
			try
			{
			//Opening email body and verifying content
			
			SeleniumFunc.ClickOnElement("css", "div[class='subject ng-binding']");
			//div.mailview
			driver.switchTo().frame(1);
			
			String ActualMessageBody = SeleniumFunc.GetElementText("css", "div.mailview").trim();
			//System.out.println(ActualMessageBody);
			
			String ExpectedMessageBody =
					"Dear test" + "\n" + 
					"This e-mail contains instructions on resetting your password for the online course at Bowhunter Ed.com."  + "\n" +

					"If you did not request your password to be reset, please simply ignore this message." + "\n" + 
					"To reset your password, please go to the link below" +"\n" + 
					"reset your password" + "\n" +  "\n" +

					"Thanks and safe hunting!" + "\n" +

					"Bowhunter Ed Support";
			
			
			if(ActualMessageBody.equalsIgnoreCase(ExpectedMessageBody))
			{
				System.out.println("Success!! Email Message body has correct details");
				Reporter.log("Success!! Email Message body has correct details"); 
			}
			else
			{
				System.out.println("Failure !!  Email Message body has incorrect details" + "\n" + "Expected Body: "  
									+ "\n" + ExpectedMessageBody + "\n" + 
									 "Actual Body : " + "\n" +  ActualMessageBody);
				Reporter.log("Failure !!  Email Message body has incorrect details" + "\n" + "Expected Body: "  
						+ "\n" + ExpectedMessageBody + "\n" + 
						 "Actual Body : " + "\n" +  ActualMessageBody);
				
				AssertFailedCount++;
			}
		
			driver.switchTo().defaultContent();
			}
			
			catch(Exception e)
			{
				//nothing
			}
	
			
		
		 * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 
	 	if(AssertFailedCount>0)	
		{
			
			//Marking this test as Failed
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
		
	}
	*/
	
	/* Test 7: 
	 * To verify user can generate password retrieval email
	*/ 
	@Test
	private void Login_PasswordResetEmail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : To verify user can generate password retrieval email"  + "\n" + "====");
		Reporter.log("====" + "\n" +
				"Test 7 : To verify user can generate password retrieval email"  + "\n" + "====");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		LoginPage login = new LoginPage(driver);
		ForgotPasswordPage forgotpassword = new ForgotPasswordPage(driver);
		PageHeader header = new PageHeader(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "loginhunter" + JH.GenerateRandomNumber();
			String emailprefix = "login" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "5", "1990", emailaddress, emailaddress);
			
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}		
			
		System.out.println("Step 2: Logging out of application");
		Reporter.log("Step 2: Logging out of application"); 
				
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter+ "/site/logout");
			Thread.sleep(4000);

	
		System.out.println("Step 3 : Navigating to Login page and clicking on 'Forgot Password' link");
		Reporter.log("Step 3 : Navigating to Login page and clicking on 'Forgot Password' link"); 
			
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
				Thread.sleep(4000);

				login.ClickOnForgotPasswordLink();
				Thread.sleep(4000);

		System.out.println("Step 4 : Entereing Email address and clicking on 'Send Me the Instruction' button ");
		Reporter.log("Step 4 : Entereing Email address and clicking on 'Send Me the Instruction' button ");
				
			
				forgotpassword.EnterEmail(emailaddress);
				forgotpassword.ClickOnSendMeInstructionButton();
		
				Thread.sleep(4000);

		System.out.println("Step 4 : Verifying whether confirmation messages are displayed");
		Reporter.log("Step 4 : Verifying whether confirmation messages are displayed "); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#main div[class='alert alert-success fade in']");
			//System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage= 
					"An email has been sent to you with the instructions to reset your password. Thank you."; 
			
			if(ActualValidationMessage.contains(ExpectedValidationMessage))
			{
				System.out.println("Success !! correct validation messages are displayed ");
				Reporter.log("Success !! correct validation messages are displayed "); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
									+ "\n" + ExpectedValidationMessage + "\n" + 
									 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
				Reporter.log("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
						+ "\n" + ExpectedValidationMessage + "\n" + 
						 "Actual Validation messages : " + "\n" + ActualValidationMessage); 
				
				AssertFailedCount++;
			}
				
			
	
		System.out.println("Step 5 : Verifying whether Password Reset Email sent to user or not");
		Reporter.log("Step 5 : Verifying whether Password Reset Email sent to user or not"); 
		
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=" + emailprefix + "#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
			
			String ActualMessageTitle= SeleniumFunc.GetElementText("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div").trim();
			//System.out.println(ActualMessageTitle);
											
			String ExpectedMessageTitle="Reset your password"; 
			//System.out.println(ExpectedMessageTitle);
			
			if(ActualMessageTitle.equalsIgnoreCase(ExpectedMessageTitle))
			{
				System.out.println("Success!! Email is received with correct Title");
				Reporter.log("Success!! Email is received with correct Title"); 
			}
			else
			{
				System.out.println("Failure !!  Email is received with incorrect Title" + "\n" + "Expected Text: "  
									+ "\n" + ExpectedMessageTitle + "\n" + 
									 "Actual Text : " + "\n" +  ActualMessageTitle);
				Reporter.log("Failure !!Email is received with incorrect Title" + "\n" + "Expected Text: "  
						+ "\n" + ExpectedMessageTitle + "\n" + 
						 "Actual Text : " + "\n" +  ActualMessageTitle);
				
				AssertFailedCount++;
			}
		
			try
			{
			//Opening email body and verifying content
			
			SeleniumFunc.ClickOnElement("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");
			
			Thread.sleep(4000);
			//div.mailview
			driver.switchTo().frame("publicshowmaildivcontent");
			
			String ActualMessageBody = SeleniumFunc.GetElementText("css", "html>body").trim();
			//System.out.println(ActualMessageBody);
			
			String ExpectedMessageBody =
					"Dear test," + "\n" + 		
			
					"The following instructions will help you reset your password for your online course at bowhunter-ed.com."  + "\n" +	
					
					"If you did not request your password to be reset, please simply ignore this message." + "\n" + 	
					
					"We found 1 username linked to this e-mail address. Click on a username below to reset the password for that username:" +"\n" + 
					
							username + "\n" +  
							
					"Thanks and safe hunting!" + "\n" +
					"Bowhunter Ed Support";
			
			
			if(ActualMessageBody.equalsIgnoreCase(ExpectedMessageBody))
			{
				System.out.println("Success!! Email Message body has correct details");
				Reporter.log("Success!! Email Message body has correct details"); 
			}
			else
			{
				System.out.println("Failure !!  Email Message body has incorrect details" + "\n" + "Expected Body: "  
									+ "\n" + ExpectedMessageBody + "\n" + 
									 "Actual Body : " + "\n" +  ActualMessageBody);
				Reporter.log("Failure !!  Email Message body has incorrect details" + "\n" + "Expected Body: "  
						+ "\n" + ExpectedMessageBody + "\n" + 
						 "Actual Body : " + "\n" +  ActualMessageBody);
				
				AssertFailedCount++;
			}
		
			driver.switchTo().defaultContent();
			}
			
			catch(Exception e)
			{
				//nothing
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
	 * Verify that user can reset password successfully
	*/ 
	/*@Test
	private void Login_PasswordReset() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Verify that user can reset password successfully"  + "\n" + "====");
		Reporter.log("====" + "\n" +
				"Test 8 : Verify that user can reset password successfully"  + "\n" + "====");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		LoginPage login = new LoginPage(driver);
		ForgotPasswordPage forgotpassword = new ForgotPasswordPage(driver);
		ResetPasswordPage resetpassword = new ResetPasswordPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "loginhunter" + JH.GenerateRandomNumber();
			String emailprefix = "login" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + "/register/create");
			//For few States , closing modal dialog present on Registration page
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "5", "1990", emailaddress, emailaddress);
			
			
		System.out.println("Step 2: Logging out of application");
		Reporter.log("Step 2: Logging out of application"); 
				
			//SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter+ "/site/logout");
				
	
		System.out.println("Step 3 : Navigating to Login page and clicking on 'Forgot Password' link");
		Reporter.log("Step 3 : Navigating to Login page and clicking on 'Forgot Password' link"); 
			
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
				login.ClickOnForgotPasswordLink();
				
		System.out.println("Step 4 : Entereing Username/Email address and clicking on 'Send Me the Instruction' button ");
		Reporter.log("Step 4 : Entereing Username/Email address and clicking on 'Send Me the Instruction' button ");
				
				forgotpassword.EnterUsername(username);
				forgotpassword.EnterEmail(emailaddress);
				forgotpassword.ClickOnSendMeInstructionButton();
		
	
		System.out.println("Step 5 : Navigating to Email box and clicking on 'reset your password' link from Password Reset Email");
		Reporter.log("Step 5 : Navigating to Email box and clicking on 'reset your password' link from Password Reset Email"); 
		
			String EmailBoxUrl = "http://mailinator.com/inbox.jsp?to=" + emailprefix;
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
			
			//Opening email body
			SeleniumFunc.ClickOnElement("css", "div[class='subject ng-binding']");
			//div.mailview
			driver.switchTo().frame(1);
			
			//Clicking on  'reset your password' link
			SeleniumFunc.ClickOnElement("css", "div.mailview >a");
			
			//driver.switchTo().defaultContent();
			for(String winHandle : driver.getWindowHandles())
			{
			    driver.switchTo().window(winHandle);
			}
	
		System.out.println("Step 6 : Entering values in New Password / Re-enter New Password fields");
		Reporter.log("Step 6 : Entering values in New Password / Re-enter New Password fields");
		
			String newpassword ="me@12345";
			resetpassword.EnterNewPassword(newpassword);
			resetpassword.EnterReEnterNewPassword(newpassword);
			resetpassword.ClickOnSubmitButton();
			
		
		System.out.println("Step 7 : Verifying user is navigated to '/register/edit' page and proper confirmation message is dispalyed on that page");
		Reporter.log("Step 7 : Verifying user is navigated to '/register/edit' page and proper confirmation message is dispalyed on that page");
				
			if(SeleniumFunc.ToGetCurrentPageUrl().contains(Constants.ApplicationURL_BowHunter.subSequence(5,Constants.ApplicationURL_BowHunter.length())+ "/register/edit"))
			{
				System.out.println("Success !! User is navigated to '/register/edit' page ");
				Reporter.log("Success !! User is navigated to '/register/edit' page "); 
			}
			else
			{
				System.out.println("Failure !!  User is NOT navigated to '/register/edit' page , instead navigated to: " + SeleniumFunc.ToGetCurrentPageUrl());
				Reporter.log("Failure !!  User is NOT navigated to '/register/edit' page , instead navigated to: " + SeleniumFunc.ToGetCurrentPageUrl()); 
				AssertFailedCount++;
			}
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#main div[class='alert alert-sucess fade in']");
			//System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage= 
					"Password updated successfully"; 
			
			if(ActualValidationMessage.contains(ExpectedValidationMessage))
			{
				System.out.println("Success !! correct confirmation messages are displayed ");
				Reporter.log("Success !! correct confirmation messages are displayed "); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect confirmation messages are displayed " + "\n" + "Expected confirmation messages : "  
									+ "\n" + ExpectedValidationMessage + "\n" + 
									 "Actual confirmation messages : " + "\n" +  ActualValidationMessage);
				Reporter.log("Failure !!  Incorrect confirmation messages are displayed " + "\n" + "Expected confirmation messages : "  
						+ "\n" + ExpectedValidationMessage + "\n" + 
						 "Actual confirmation messages : " + "\n" + ActualValidationMessage); 
				
				AssertFailedCount++;
			}
			
			
	
		System.out.println("Step 8:  Trying login with new password and verifying whether user is logged in or not");
		Reporter.log("Step 8:  Trying login with new password and verifying whether user is logged in or not");
		
			SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
			login.EnterPassword(newpassword);
			login.EnterUsername(username);
			login.ClickOnLogInButton();
			
			
		System.out.println("Step 9 : Verifying whether user is logged successfully or not");
		Reporter.log("Step 9 : Verifying whether user is logged successfully or not"); 
			
			
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
			//System.out.println(ActualUserName);
			
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
				
			
			
		
		 * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 
	 	if(AssertFailedCount>0)	
		{
			
			//Marking this test as Failed
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
		
	}
	*/
	/* Test 8: 
	 * Verify that user can reset password successfully
	*/ 
	@Test
	private void Login_PasswordReset() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Verify that user can reset password successfully"  + "\n" + "====");
		Reporter.log("====" + "\n" +
				"Test 8 : Verify that user can reset password successfully"  + "\n" + "====");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		LoginPage login = new LoginPage(driver);
		ForgotPasswordPage forgotpassword = new ForgotPasswordPage(driver);
		ResetPasswordPage resetpassword = new ResetPasswordPage(driver);
		PageHeader header = new PageHeader(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "loginhunter" + JH.GenerateRandomNumber();
			String emailprefix = "login" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			Thread.sleep(4000);
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
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "5", "1990", emailaddress, emailaddress);
			
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}		
			
			
		System.out.println("Step 2: Logging out of application");
		Reporter.log("Step 2: Logging out of application"); 
				
			//SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter+ "/site/logout");
			Thread.sleep(4000);

	
		System.out.println("Step 3 : Navigating to Login page and clicking on 'Forgot Password' link");
		Reporter.log("Step 3 : Navigating to Login page and clicking on 'Forgot Password' link"); 
			
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
				Thread.sleep(4000);

				login.ClickOnForgotPasswordLink();
				Thread.sleep(4000);
				
		System.out.println("Step 4 : Entereing Email address and clicking on 'Send Me the Instruction' button ");
		Reporter.log("Step 4 : Entereing Email address and clicking on 'Send Me the Instruction' button ");
				
				
				forgotpassword.EnterEmail(emailaddress);
				forgotpassword.ClickOnSendMeInstructionButton();
				Thread.sleep(4000);

	
		System.out.println("Step 5 : Navigating to Email box and clicking on 'reset your password' link from Password Reset Email");
		Reporter.log("Step 5 : Navigating to Email box and clicking on 'reset your password' link from Password Reset Email"); 
		
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=" + emailprefix + "#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
			
			//Opening email body
			SeleniumFunc.ClickOnElement("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");
			
			Thread.sleep(4000);
			
			//div.mailview
			driver.switchTo().frame("publicshowmaildivcontent");
			
			//Clicking on  'reset your password' link
			SeleniumFunc.ClickOnElement("css", "html>body>ul>li>a");
			Thread.sleep(4000);

			//driver.switchTo().defaultContent();
			for(String winHandle : driver.getWindowHandles())
			{
			    driver.switchTo().window(winHandle);
			}
	
		System.out.println("Step 6 : Entering values in New Password / Re-enter New Password fields");
		Reporter.log("Step 6 : Entering values in New Password / Re-enter New Password fields");
		  
			Thread.sleep(2000);
			if( SeleniumFunc.IsElementPresent(forgotpassword.ForgotPasswaordMessage))
			{				
				System.out.println("Success !! Correct Validation is displayed  i.e.You are updating the password for user: ");
				Reporter.log("Success !! Correct Validation is displayed i.e.You are updating the password for user: ");			
			}
			else
			{			
				System.out.println("Failure !! No Validation for invalid i.e.You are updating the password for user:");
				Reporter.log("Failure !! No Validation for invalid "); 			
				AssertFailedCount++;			
			}		
			
			Thread.sleep(2000);
			String newpassword ="me@12345";
			resetpassword.EnterNewPassword(newpassword);
			resetpassword.EnterReEnterNewPassword(newpassword);
			resetpassword.ClickOnSubmitButton();
			Thread.sleep(4000);

		
		System.out.println("Step 7 : Verifying user is navigated to '/register/edit' page and proper confirmation message is dispalyed on that page");
		Reporter.log("Step 7 : Verifying user is navigated to '/register/edit' page and proper confirmation message is dispalyed on that page");
				
			if(SeleniumFunc.ToGetCurrentPageUrl().contains(Constants.ApplicationURL_BowHunter.subSequence(5,Constants.ApplicationURL_BowHunter.length())+ "/register/edit"))
			{
				System.out.println("Success !! User is navigated to '/register/edit' page ");
				Reporter.log("Success !! User is navigated to '/register/edit' page "); 
			}
			else
			{
				System.out.println("Failure !!  User is NOT navigated to '/register/edit' page , instead navigated to: " + SeleniumFunc.ToGetCurrentPageUrl());
				Reporter.log("Failure !!  User is NOT navigated to '/register/edit' page , instead navigated to: " + SeleniumFunc.ToGetCurrentPageUrl()); 
				AssertFailedCount++;
			}
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#main div[class='alert alert-sucess fade in']");
			//System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage= 
					"Password updated successfully"; 
			
			if(ActualValidationMessage.contains(ExpectedValidationMessage))
			{
				System.out.println("Success !! correct confirmation messages are displayed ");
				Reporter.log("Success !! correct confirmation messages are displayed "); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect confirmation messages are displayed " + "\n" + "Expected confirmation messages : "  
									+ "\n" + ExpectedValidationMessage + "\n" + 
									 "Actual confirmation messages : " + "\n" +  ActualValidationMessage);
				Reporter.log("Failure !!  Incorrect confirmation messages are displayed " + "\n" + "Expected confirmation messages : "  
						+ "\n" + ExpectedValidationMessage + "\n" + 
						 "Actual confirmation messages : " + "\n" + ActualValidationMessage); 
				
				AssertFailedCount++;
			}
			
		System.out.println("Step 8:  Trying login with new password and verifying whether user is logged in or not");
		Reporter.log("Step 8:  Trying login with new password and verifying whether user is logged in or not");
		
			SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
			login.EnterPassword(newpassword);
			login.EnterUsername(username);
			login.ClickOnLogInButton();
			
			
		System.out.println("Step 9 : Verifying whether user is logged successfully or not");
		Reporter.log("Step 9 : Verifying whether user is logged successfully or not"); 
			
			
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
			//System.out.println(ActualUserName);
			
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
	
	/* Test 9: 
	 * Check for validation on reset password page  A) keep Username/Email Address blank 
	 
	@Test
	private void Login_ForgotPasswordValidationCheck1() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Check for validation on reset password page  A) keep Username/Email Address blank "  + "\n" + "====");
		Reporter.log("====" + "\n" +
				"Test 9 : Check for validation on reset password page  A) keep Username/Email Address blank "  + "\n" + "====");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		ForgotPasswordPage forgotpassword = new ForgotPasswordPage(driver);
		
		System.out.println("Step 1 : Navigating to Login page and clicking on 'Forgot Password' link");
		Reporter.log("Step 1 : Navigating to Login page and clicking on 'Forgot Password' link"); 
			
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
				login.ClickOnForgotPasswordLink();
				
		System.out.println("Step 2 : Keeping Username/Email Address blank and clicking on 'Send Me the Instruction' button ");
		Reporter.log("Step 2 : Keeping Username/Email Address blank and clicking on 'Send Me the Instruction' button ");
				
				forgotpassword.ClickOnSendMeInstructionButton();
		
		System.out.println("Step 4 : Verifying whether validation messages are displayed");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed "); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#loginForm div:nth-of-type(2) p") + 
					SeleniumFunc.GetElementText("css", "#loginForm div:nth-of-type(3) p");
			System.out.println(ActualValidationMessage);

			String ExpectedValidationMessage= 
			"Please complete this field." +
			"Please complete this field."; 
			
			if(ActualValidationMessage.contains(ExpectedValidationMessage))
			{
				System.out.println("Success !! correct validation messages are displayed ");
				Reporter.log("Success !! correct validation messages are displayed "); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
									+ "\n" + ExpectedValidationMessage + "\n" + 
									 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
				Reporter.log("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
						+ "\n" + ExpectedValidationMessage + "\n" + 
						 "Actual Validation messages : " + "\n" + ActualValidationMessage); 
				
				AssertFailedCount++;
			}
				
		
		
		 * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 
	 	if(AssertFailedCount>0)	
		{
			
			//Marking this test as Failed
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
		
	}
	*/
	
	/* Test 9: 
	 * Check for validation on reset password page  A) keepEmail Address blank 
	*/ 
	@Test
	private void Login_ForgotPasswordValidationCheck1() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Check for validation on reset password page  A) Email Address blank "  + "\n" + "====");
		Reporter.log("====" + "\n" +
				"Test 9 : Check for validation on reset password page  A) Email Address blank "  + "\n" + "====");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		ForgotPasswordPage forgotpassword = new ForgotPasswordPage(driver);
		PageHeader header = new PageHeader(driver);

		System.out.println("Step 1 : Navigating to Login page and clicking on 'Forgot Password' link");
		Reporter.log("Step 1 : Navigating to Login page and clicking on 'Forgot Password' link"); 
		Thread.sleep(4000);
		if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
		{
			header.Logout_Handgun.click();
			Thread.sleep(4000);
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
			Thread.sleep(4000);

		}
		
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
				Thread.sleep(4000);

				login.ClickOnForgotPasswordLink();
				Thread.sleep(4000);

		System.out.println("Step 2 : Keeping Email Address blank and clicking on 'Send Me the Instruction' button ");
		Reporter.log("Step 2 : Keeping Email Address blank and clicking on 'Send Me the Instruction' button ");
				
				forgotpassword.ClickOnSendMeInstructionButton();
				Thread.sleep(4000);

		System.out.println("Step 4 : Verifying whether validation messages are displayed");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed "); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#loginForm div:nth-of-type(2) p") ;
					
			System.out.println(ActualValidationMessage);

			String ExpectedValidationMessage= 	
			"Please complete this field."; 
			
			if(ActualValidationMessage.contains(ExpectedValidationMessage))
			{
				System.out.println("Success !! correct validation messages are displayed ");
				Reporter.log("Success !! correct validation messages are displayed "); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
									+ "\n" + ExpectedValidationMessage + "\n" + 
									 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
				Reporter.log("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
						+ "\n" + ExpectedValidationMessage + "\n" + 
						 "Actual Validation messages : " + "\n" + ActualValidationMessage); 
				
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
	 * Check for validation on reset password page  A) Enter incorrect Username/Email Address
	 
	@Test
	private void Login_ForgotPasswordValidationCheck2() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : Check for validation on reset password page  A) Enter incorrect Username/Email Address"  + "\n" + "====");
		Reporter.log("====" + "\n" +
				"Test 10 : Check for validation on reset password page  A) Enter incorrect Username/Email Address"  + "\n" + "====");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		ForgotPasswordPage forgotpassword = new ForgotPasswordPage(driver);
		
		System.out.println("Step 1 : Navigating to Login page and clicking on 'Forgot Password' link");
		Reporter.log("Step 1 : Navigating to Login page and clicking on 'Forgot Password' link"); 
			
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
				login.ClickOnForgotPasswordLink();
				
		System.out.println("Step 2 : Entering incorrect Username/Email Address and clicking on 'Send Me the Instruction' button ");
		Reporter.log("Step 2 : Entering incorrect Username/Email Address and clicking on 'Send Me the Instruction' button ");
				
				forgotpassword.EnterUsername("sssdfdffdfdf");
				forgotpassword.EnterEmail("fsdsdsdfs@fsdfsfs.com");
				forgotpassword.ClickOnSendMeInstructionButton();
		
		System.out.println("Step 4 : Verifying whether validation messages are displayed");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed "); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#loginForm div[class='alert alert-block alert-error']");
			//System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage= 
					"Please fix the following input errors:" + "\n" +
					"username and email don't match"; 
			
			if(ActualValidationMessage.contains(ExpectedValidationMessage))
			{
				System.out.println("Success !! correct validation messages are displayed ");
				Reporter.log("Success !! correct validation messages are displayed "); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
									+ "\n" + ExpectedValidationMessage + "\n" + 
									 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
				Reporter.log("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
						+ "\n" + ExpectedValidationMessage + "\n" + 
						 "Actual Validation messages : " + "\n" + ActualValidationMessage); 
				
				AssertFailedCount++;
			}
				
		
		
		 * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 
	 	if(AssertFailedCount>0)	
		{
			
			//Marking this test as Failed
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
		
	}
	*/
	/* Test 10: 
	 * Check for validation on reset password page  A) Enter incorrect Email Address
	*/ 
	@Test
	private void Login_ForgotPasswordValidationCheck2() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : Check for validation on reset password page  A)Email Address"  + "\n" + "====");
		Reporter.log("====" + "\n" +
				"Test 10 : Check for validation on reset password page  A) Email Address"  + "\n" + "====");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		ForgotPasswordPage forgotpassword = new ForgotPasswordPage(driver);
		PageHeader header = new PageHeader(driver);

		System.out.println("Step 1 : Navigating to Login page and clicking on 'Forgot Password' link");
		Reporter.log("Step 1 : Navigating to Login page and clicking on 'Forgot Password' link"); 
			
		Thread.sleep(4000);
		if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
		{
			header.Logout_Handgun.click();
			Thread.sleep(4000);
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);

		}
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
				login.ClickOnForgotPasswordLink();
				Thread.sleep(4000);

		System.out.println("Step 2 : Entering incorrect Email Address and clicking on 'Send Me the Instruction' button ");
		Reporter.log("Step 2 : Entering incorrect Email Address and clicking on 'Send Me the Instruction' button ");
				
				
				forgotpassword.EnterEmail("fsdsdsdfs@fsdfsfs.com");
				forgotpassword.ClickOnSendMeInstructionButton();
				Thread.sleep(4000);

		System.out.println("Step 4 : Verifying whether validation messages are displayed");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed "); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#loginForm div[class='alert alert-block alert-error']");
			//System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage= 
					"Please fix the following input errors:" + "\n" +
					"There are no usernames associated with fsdsdsdfs@fsdfsfs.com."; 
			
			if(ActualValidationMessage.contains(ExpectedValidationMessage))
			{
				System.out.println("Success !! correct validation messages are displayed ");
				Reporter.log("Success !! correct validation messages are displayed "); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
									+ "\n" + ExpectedValidationMessage + "\n" + 
									 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
				Reporter.log("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
						+ "\n" + ExpectedValidationMessage + "\n" + 
						 "Actual Validation messages : " + "\n" + ActualValidationMessage); 
				
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
	 * Verify UI of forgot password page
	 
	@Test
	private void Login_ForgotPassword_UIElementVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 11 : Verify UI of forgot password page"  + "\n" + "====");
		Reporter.log("====" + "\n" +
				"Test 11 : Verify UI of forgot password page"  + "\n" + "====");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		
		System.out.println("Step 1 : Navigating to Login page and clicking on 'Forgot Password' link");
		Reporter.log("Step 1 : Navigating to Login page and clicking on 'Forgot Password' link"); 
			
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
				login.ClickOnForgotPasswordLink();
		
		System.out.println("Step 2 : Verifying whether page title is correctly displayed on not");
		Reporter.log("Step 2 : Verifying whether page title is correctly displayed on not"); 
			
			if(SeleniumFunc.GetElementText("css", "div.page-header h1").equals("Forgot Your Password?"))
			{
				System.out.println("Success !! Page title is correct");
				Reporter.log("Success !! Page title is correct"); 
			}
			else
			{
				System.out.println("Failure !!  Page title is incorrect ");
				Reporter.log("Failure !!  Page title is incorrect "); 
				AssertFailedCount++;
			}
			
		System.out.println("Step 3 : Verifying presence of UI elements : Username Textbox,Email Address Textbox, SendMeInstruction button");
		Reporter.log("Step 3 : Verifying presence of UI elements : Username Textbox,Email Address Textbox, SendMeInstruction button"); 	
			
			if(SeleniumFunc.IsElementPresent("id", "ForgetPasswordForm_username"))
			{
				
				System.out.println("Success !! Username Textbox is present");
				Reporter.log("Success !! Username Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! Username Textbox is NOT present ");
				Reporter.log("Failure !! Username Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			
			if(SeleniumFunc.IsElementPresent("id", "ForgetPasswordForm_email"))
			{
				
				System.out.println("Success !! Email Address Textbox is present");
				Reporter.log("Success !! Email Address Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! Email Address Textbox is NOT present ");
				Reporter.log("Failure !! Email Address Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			
			if(SeleniumFunc.IsElementPresent("css", "#loginForm input[value='Send Me the Instructions']"))
			{
				
				System.out.println("Success !! SendMeInstruction button is present");
				Reporter.log("Success !!SendMeInstruction button is present"); 
			}
			else
			{
				System.out.println("Failure !! SendMeInstruction button is NOT present ");
				Reporter.log("Failure !! SendMeInstruction button is NOT present "); 
				AssertFailedCount++;
			}
			
		
		
		
		 * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 
	 	if(AssertFailedCount>0)	
		{
			
			//Marking this test as Failed
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
		
	}
	*/
	/* Test 11: 
	 * Verify UI of forgot password page
	*/ 
	@Test
	private void Login_ForgotPassword_UIElementVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 11 : Verify UI of forgot password page"  + "\n" + "====");
		Reporter.log("====" + "\n" +
				"Test 11 : Verify UI of forgot password page"  + "\n" + "====");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		PageHeader header = new PageHeader(driver);

		System.out.println("Step 1 : Navigating to Login page and clicking on 'Forgot Password' link");
		Reporter.log("Step 1 : Navigating to Login page and clicking on 'Forgot Password' link"); 
		Thread.sleep(4000);
		if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
		{
			header.Logout_Handgun.click();
			Thread.sleep(4000);
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);

		}
		
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
				login.ClickOnForgotPasswordLink();
				Thread.sleep(4000);

		System.out.println("Step 2 : Verifying whether page title is correctly displayed on not");
		Reporter.log("Step 2 : Verifying whether page title is correctly displayed on not"); 
			
			if(SeleniumFunc.GetElementText("css", "div.page-header h1").equals("Forgot Your Password?"))
			{
				System.out.println("Success !! Page title is correct");
				Reporter.log("Success !! Page title is correct"); 
			}
			else
			{
				System.out.println("Failure !!  Page title is incorrect ");
				Reporter.log("Failure !!  Page title is incorrect "); 
				AssertFailedCount++;
			}
			
		System.out.println("Step 3 : Verifying presence of UI elements : Email Address Textbox, SendMeInstruction button");
		Reporter.log("Step 3 : Verifying presence of UI elements : Email Address Textbox, SendMeInstruction button"); 	
		
			if(SeleniumFunc.IsElementPresent("id", "ForgetPasswordForm_email"))
			{
				
				System.out.println("Success !! Email Address Textbox is present");
				Reporter.log("Success !! Email Address Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! Email Address Textbox is NOT present ");
				Reporter.log("Failure !! Email Address Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			
			if(SeleniumFunc.IsElementPresent("css", "#loginForm input[value='Send Me the Instructions']"))
			{
				
				System.out.println("Success !! SendMeInstruction button is present");
				Reporter.log("Success !!SendMeInstruction button is present"); 
			}
			else
			{
				System.out.println("Failure !! SendMeInstruction button is NOT present ");
				Reporter.log("Failure !! SendMeInstruction button is NOT present "); 
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
	 * Verify the session timeout functionality
	*/ 
	@Test
	private void Login_SessionTimeout() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 12 : Verify the session timeout functionality"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 12 : Verify the session timeout functionality"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		
		System.out.println("Pre-requisite : Signing up as new User");
		Reporter.log("Pre-requisite : Signing up as new User"); 
			
			String username= "login" + JH.GenerateRandomNumber();
			String emailprefix = "login" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
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
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "5", "1990", emailaddress, emailaddress);
			
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}		
			
			
		System.out.println("Step 1 : Do not do any activity for 25 mins and refreshing page after 25 mins");
		Reporter.log("Step 1 : Do not do any activity for 25 mins and refreshing page after 25 mins"); 
			
			driver.manage().timeouts().pageLoadTimeout(1800, TimeUnit.SECONDS);
			Thread.sleep(1500000);

			//Refreshing page
			driver.navigate().refresh();
			Thread.sleep(4000);

			
			String Actualurl=driver.getCurrentUrl();
			System.out.println(Actualurl);
			
			String expectedurl = Constants.ApplicationURL_BowHunter + "/site/login/";
			
			if(Actualurl.equals(expectedurl))
			{
				
				System.out.println("Success !! user is redirected to login page");
				Reporter.log("Success !! user is redirected to login page"); 
			}
			else
			{
				System.out.println("Failure !! user is NOT redirected to login page");
				Reporter.log("Failure !! user is NOT redirected to login page"); 
				AssertFailedCount++;
			}
			
			
			
			String Actual=SeleniumFunc.GetElementText("css", "div[class='alert alert-info fade in']");
			System.out.println(Actual);
			
			String expected = "Your session has expired due to inactivity.";
			
			if(Actual.contains(expected))
			{
				
				System.out.println("Success !! correct info message displayed i.e. " + Actual);
				Reporter.log("Success !! correct info message displayed i.e. " + Actual);
			}
			else
			{
				System.out.println("Failure !! incorrect info message displayed");
				Reporter.log("Failure !! incorrect info message displayed "); 
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
	 * Verify validation Red block dismissed completely!!
	*/ 
	@Test
	private void Error_Dismiss() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify validation Red block dismissed completely");
		Reporter.log("====" + "\n" +
				"Test 4 : Verify validation Red block dismissed completely");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		PageHeader header = new PageHeader(driver);


		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL + "/site/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL + "/site/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_BowHunter);
				Thread.sleep(4000);
			}
		System.out.println("Step 2 : Entering incorrect data in Username/Password field and Clicking on Login button ");
		Reporter.log("Step 2 : Entering incorrect data in Username/Password field and Clicking on Login button "); 
			
			login.EnterUsername("username");
			login.EnterPassword("password");
			login.ClickOnLogInButton();
			Thread.sleep(4000);

		System.out.println("Step 3 : Verifying whether validation messages are displayed");
		Reporter.log("Step 3 : Verifying whether validation messages are displayed "); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#loginForm div[class='alert alert-block alert-error'] p") + "\n" + 
					SeleniumFunc.GetElementText("css", "#loginForm div[class='alert alert-block alert-error'] li");
			System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage= 
					"Please review the following:" + "\n" + 
					"That username and password wasn't valid. Please check them and try again."; 
			
			if(ActualValidationMessage.equals(ExpectedValidationMessage))
			{
				System.out.println("Success !! correct validation messages are displayed ");
				Reporter.log("Success !! correct validation messages are displayed "); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
									+ "\n" + ExpectedValidationMessage + "\n" + 
									 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
				Reporter.log("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
						+ "\n" + ExpectedValidationMessage + "\n" + 
						 "Actual Validation messages : " + "\n" + ActualValidationMessage); 
				
				AssertFailedCount++;
			}
			
			
			ActualValidationMessage= SeleniumFunc.GetElementText("css", "#loginForm div[class='control-group error'] div div");
			System.out.println(ActualValidationMessage);
			
			ExpectedValidationMessage= 
					"That username and password wasn't valid. Please check them and try again."; 
			
			if(ActualValidationMessage.equals(ExpectedValidationMessage))
			{
				System.out.println("Success !! correct validation messages are displayed ");
				Reporter.log("Success !! correct validation messages are displayed "); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
									+ "\n" + ExpectedValidationMessage + "\n" + 
									 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
				Reporter.log("Failure !!  Incorrect validation messages are displayed " + "\n" + "Expected Validation messages : "  
						+ "\n" + ExpectedValidationMessage + "\n" + 
						 "Actual Validation messages : " + "\n" + ActualValidationMessage); 
				
				AssertFailedCount++;
			}
			
	    System.out.println("Step 4 : Now close validation message box with 'x' icon");
		Reporter.log("Step 4 : Now close validation message box with 'x' icon"); 
		
				SeleniumFunc.ClickOnElement("css", "#loginForm > div.form-alerts > div > div > a");
				String Expected = "";
				String Actual = SeleniumFunc.GetElementText("css", ".alert.alert-block.alert-error");
				
			if(!SeleniumFunc.IsElementPresent("css", "#loginForm div[class='alert alert-block alert-error'] p") && Actual.equals(Expected))
			{
				System.out.println("Failure !!  Rred block should not dismissed !! ");
				Reporter.log("Failure !!  Rred block should not dismissed !!"); 
				AssertFailedCount++;
			}
			else
			{
				
				System.out.println("Success !! Red validation block dismissed completely");
				Reporter.log("Success !! Red validation block dismissed completely"); 
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
