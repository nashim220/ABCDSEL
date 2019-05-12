package products.CM;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.LoginPage;


import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class LoginTest 
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
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Verifying presence of UI elements : Email Textbox,Password Textbox,Forgot Password Link,Login Button ");
		Reporter.log("Step 2 : Verifying presence of UI elements : Email Textbox,Password Textbox,Forgot Password Link,Login Button "); 	
			
			if(SeleniumFunc.IsElementPresent("css", "#auth_key"))
			{
				
				System.out.println("Success !! Email Textbox is present");
				Reporter.log("Success !! Email Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! Email Textbox is NOT present ");
				Reporter.log("Failure !! Email Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			
			if(SeleniumFunc.IsElementPresent("css", "#password"))
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
			
			
			if(SeleniumFunc.IsElementPresent("css", "input[value='Login']"))
			{
				
				System.out.println("Success !! Login button is present");
				Reporter.log("Success !! Login button is present"); 
			}
			else
			{
				System.out.println("Failure !! Login button is NOT present ");
				Reporter.log("Failure !! Login button is NOT present "); 
				AssertFailedCount++;
			}
			
			
			if(SeleniumFunc.IsElementPresent("css", "a[href='https://kps.staging.kalkomey.com/password/recover']"))
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
					
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(2000);
		
		System.out.println("Step 3 : Verifying whether user is logged successfully or not");
		Reporter.log("Step 3 : Verifying whether user is logged successfully or not"); 
			
				
			
			String ActualUserName=SeleniumFunc.GetElementText("css", ".alert.alert-success").trim();
			System.out.println(ActualUserName);
			Thread.sleep(2000);
			if(ActualUserName.equals("Signed in!"))
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
	 * Verify that user can't login with invalid credentials and proper validation messages are displayed 
		A) Keep both Email /Password blank"
	*/ 
	@Test
	private void Login_LoginFailure_BlankInputs() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify that user can't login with invalid credentials and proper validation messages are displayed  A) Keep both Email/Password blank"  + "\n" + "====");
		Reporter.log("====" + "\n" +
				"Test 3 : Verify that user can't login with invalid credentials and proper validation messages are displayed  A) Keep both Email/Password blank"  + "\n" + "====");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		

		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Clicking on Login button without entering any details");
		Reporter.log("Step 2 : Clicking on Login button without entering any details"); 
		
			login.ClickOnLogInButton();
		
		System.out.println("Step 3 : Verifying whether validation messages are displayed for mandatory fields");
		Reporter.log("Step 3 : Verifying whether validation messages are displayed for mandatory fields"); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "div[class='alert alert-error']");
			System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage="Authentication failed, please try again." ; 
			
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
		B) Enter incorrect values in Email fields
	*/ 
	@Test
	private void Login_LoginFailure_InvalidEmail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify that user can't login with invalid credentials and proper validation messages are displayed  B) Enter incorrect values in Email/Password fields"  + "\n" + "====");
		Reporter.log("====" + "\n" +
				"Test 4 : Verify that user can't login with invalid credentials and proper validation messages are displayed B) Enter incorrect values in Email/Password fields"  + "\n" + "====");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		

		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Entering incorrect data in Email/Password field and Clicking on Login button ");
		Reporter.log("Step 2 : Entering incorrect data in Email/Password field and Clicking on Login button "); 
			
			login.EnterUsername("username");
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
		
		System.out.println("Step 3 : Verifying whether validation messages are displayed");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed "); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "div[class='alert alert-error']");
			System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage= "Authentication failed, please try again.";
			
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
	 * Verify that user can't login with invalid credentials and proper validation messages are displayed 
		B) Enter incorrect values in Password fields
	*/ 
	@Test
	private void Login_LoginFailure_InvalidPass() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify that user can't login with invalid credentials and proper validation messages are displayed  B) Enter incorrect values in Email/Password fields"  + "\n" + "====");
		Reporter.log("====" + "\n" +
				"Test 5 : Verify that user can't login with invalid credentials and proper validation messages are displayed B) Enter incorrect values in Email/Password fields"  + "\n" + "====");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		

		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Entering incorrect data in Email/Password field and Clicking on Login button ");
		Reporter.log("Step 2 : Entering incorrect data in Email/Password field and Clicking on Login button "); 
			
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword("password");
			login.ClickOnLogInButton();
		
		System.out.println("Step 3 : Verifying whether validation messages are displayed");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed "); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "div[class='alert alert-error']");
			System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage= "Authentication failed, please try again.";
			
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
	
	
	
	/* Test 6: 
	 * Verify that user can't access the restricted pages without login
	*/ 
	@Test
	private void Login_RestrictedPages() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify that user can't access the restricted pages without login"  + "\n" + "====");
		Reporter.log("====" + "\n" +
				"Test 6 : Verify that user can't access the restricted pages without login"  + "\n" + "====");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);

		System.out.println("Step 1 : Trying to access Restricted Pages and verifying whether user is navigated to Home page or not");
		Reporter.log("Step 1 : Trying to access Restricted Pages and verifying whether user is navigated to Home page or not"); 
			
			String restricted_pages = Constants.ApplicationURL_CM + "/certifications";
			SeleniumFunc.ToGoToUrl(restricted_pages);
			
				if(SeleniumFunc.ToGetCurrentPageUrl().contains(Constants.ApplicationURL_CM))
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
	
	
	/* Test 7: 
	 * Verify that user can logout successfully
	*/ 
	@Test
	private void Login_Logout() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Verify that user can logout successfully"  + "\n" + "====");
		Reporter.log("====" + "\n" +
				"Test 7 : Verify that user can logout successfully"  + "\n" + "====");
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		
		
		LoginPage login = new LoginPage(driver);
		
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			
		System.out.println("Step 3: Logging out of application");
		Reporter.log("Step 3: Logging out of application"); 
	
			login.ClickForLogout();
			login.ClickOnLogotButton();
			Thread.sleep(1000);

		System.out.println("Step 4 : Verifying whether user is logged out successfully or not");
		Reporter.log("Step 4 : Verifying whether user is logged out successfully or not");
			
				if(SeleniumFunc.ToGetCurrentPageUrl().equals("https://kps.staging.kalkomey.com/logout"))
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
	
	
		
}
