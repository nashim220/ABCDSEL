package products.EM;

 
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.EM.ErrorPage;




import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class ForgotPasswordTest 
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
	 * Verify UI of Forgot Password Page
	*/ 
	@Test
	private void ForgotPasswordUIElementVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify UI of Forgot Password page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify UI of Forgot Password page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
	
		
		System.out.println("Step 2 : Navigate to Forgot Password Page" + Constants.ApplicationURL_EM +"/login/lostpassword/normal");
		Reporter.log("Step 2 : Navigate to Forgot Password Page" + Constants.ApplicationURL_EM +"/login/lostpassword/normal");
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/lostpassword/normal");
			
		
		System.out.println("Step 3 : Verify header of forgot password page");
		Reporter.log("Step 3 : Verify header of forgot password page");
		
		
			String ActualHeader = SeleniumFunc.GetElementText("css", "#forgot_password_form>h1");
			
			if(ActualHeader.equals("Forgot your password?"))
			{
				
				System.out.println("Success !! Correct Header on forgot password page is present");
				Reporter.log("Success !! Correct Header on forgot password page is present"); 
			
			}
			else
			{
				
				System.out.println("Failure !! Header for forgot password page is incorrect");
				Reporter.log("Failure !! Header for forgot password page is incorrect"); 
				
				AssertFailedCount++;
				
			}
			
			
		System.out.println("Step 4 : Verify message on forgot password password page");
		Reporter.log("Step 3 : Verify message on forgot password password page");
			
			
			String ActualMessage = SeleniumFunc.GetElementText("css", "#forgot_password_form>p");
			String ExpectedMessage = "Enter your username below.";
				
			if(ExpectedMessage.equals(ActualMessage))
			{
					
				System.out.println("Success !! Correct message is present on forgot password page");
				Reporter.log("Success !! Correct message is present on forgot password page"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Incorrect message is displayed on forgot password page");
				Reporter.log("Failure !! Incorrect message is displayed on forgot password page"); 
					
				AssertFailedCount++;
					
			}			
			
			
		System.out.println("Step 4 : Verifying presence of UI elements : Username Field, Show my hint button, Cancel Link");
		Reporter.log("Step 3 : Verifying presence of UI elements : Username Field, Show my hint button, Cancel Link");
				
				
			//UserName Field		
			if(SeleniumFunc.IsElementPresent("css", "#uname"))
			{
						
				System.out.println("Success !! UserName field is present");
				Reporter.log("Success !! UserName field is present"); 
					
			}
			else
			{
						
				System.out.println("Failure !! UserName field is not present");
				Reporter.log("Failure !! UserName field is not present"); 
						
				AssertFailedCount++;
						
			}			
				
			
			
			//Show my hint button		
			if(SeleniumFunc.IsElementPresent("css", "#forgot_password_form .btn"))
			{
						
				System.out.println("Success !! 'Next' button is present");
				Reporter.log("Success !! 'Next' button is present"); 
					
			}
			else
			{
						
				System.out.println("Failure !! 'Next' button is not present");
				Reporter.log("Failure !! 'Next' button is not present"); 
						
				AssertFailedCount++;
						
			}			
			
			
			
			//Cancel Link
			if(SeleniumFunc.IsElementPresent("css", "#forgot_password_form>a"))
			{
						
				System.out.println("Success !! Cancel Link is present");
				Reporter.log("Success !! Cancel Link is present"); 
					
			}
			else
			{
						
				System.out.println("Failure !! Show my hint button is not present");
				Reporter.log("Failure !! Show my hint button is not present"); 
						
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
	
	
	/* Test 2: 
	 * Verify mandatory field validation
	*/ 
	@Test
	private void VerifyValidation() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify mandatory field validation"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify mandatory field validation"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
	
		
		System.out.println("Step 2 : Navigate to Forgot Password Page" + Constants.ApplicationURL_EM +"/login/lostpassword/normal");
		Reporter.log("Step 2 : Navigate to Forgot Password Page" + Constants.ApplicationURL_EM +"/login/lostpassword/normal");
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/lostpassword/normal");
			
		
		System.out.println("Step 3 : Click on 'Next' button by keepin username field blank");
		Reporter.log("Step 3 : Click on 'Next' button by keepin username field blank");
		
			
			SeleniumFunc.ClickOnElement("css", "#forgot_password_form .btn");
			
		
		System.out.println("Step 4 : Verify whether validation message is displayed or not");
		Reporter.log("Step 4 : Verify whether validation message is displayed or not");
		
		
			String ActualValidation = SeleniumFunc.GetElementText("css", ".error.help-block");
			String ExpectedValidation = "This field is required.";
			
			if(ActualValidation.equals(ExpectedValidation))
			{
				
				System.out.println("Success !! correct validation messages are displayed for mandatory fields");
				Reporter.log("Success !! correct validation messages are displayed for mandatory fields"); 
			
			}
			else
			{
	
				System.out.println("Failure !!  Incorrect validation messages are displayed for mandatory fields" + "\n" + "Expected Validation messages : "  
									+ "\n" + ExpectedValidation + "\n" + 
									 "Actual Validation messages : " + "\n" +  ActualValidation);
				Reporter.log("Failure !!  Incorrect validation messages are displayed for mandatory fields" + "\n" + "Expected Validation messages : "  
						+ "\n" + ExpectedValidation + "\n" + 
						 "Actual Validation messages : " + "\n" + ActualValidation); 
				
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
	
	
	/* Test 3: 
	 * Verify cancel link functionality
	*/ 
	@Test
	private void VerifyCancelLinkFunctionality() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify mandatory field validation"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify mandatory field validation"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
	
		
		System.out.println("Step 2 : Navigate to Forgot Password Page" + Constants.ApplicationURL_EM +"/login/lostpassword/normal");
		Reporter.log("Step 2 : Navigate to Forgot Password Page" + Constants.ApplicationURL_EM +"/login/lostpassword/normal");
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/lostpassword/normal");
		
			
		System.out.println("Step 3 : Verify Cancel Link is present on page");
		Reporter.log("Step 3 : Verify Cancel Link is present on page");	
		
		
			if(SeleniumFunc.IsElementPresent("css", "#forgot_password_form>a"))
			{
						
				System.out.println("Success !! Cancel Link is present");
				Reporter.log("Success !! Cancel Link is present"); 
					
			}
			else
			{
						
				System.out.println("Failure !! Show my hint button is not present");
				Reporter.log("Failure !! Show my hint button is not present"); 
						
				AssertFailedCount++;
						
			}		
		
		System.out.println("Step 4 : Click on Cancel link");
		Reporter.log("Step 4 : Click on Cancel link");
		
			
			SeleniumFunc.ClickOnElement("css", "#forgot_password_form>a");
			Thread.sleep(5000);
		
		System.out.println("Step 5 : Verify control redirected to login page");
		Reporter.log("Step 5 : Verify control redirected to login page");
		
		
			String ActualHeader = SeleniumFunc.GetElementText("css", ".login.span6.offset3>h1");
			String ExpectedHeader = "Please log in to continue.";
			
			if(ActualHeader.equals(ExpectedHeader))
			{
				
				System.out.println("Success !! Cancel link is working properly, control redirected to login page");
				Reporter.log("Success !! Cancel link is working properly, control redirected to login page"); 
			
			}
			else
			{
			
				System.out.println("Failure !!  Cancel link is not working properly, control not redirected to login page");
				Reporter.log("Failure !!  Cancel link is not working properly, control not redirected to login page"); 
				
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


	/* Test 4: 
	 * Forgot Password functionality with valid input
	*/ 
	@Test
	private void ForgotPasswordValidInput() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Forgot Password functionality with valid input"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Forgot Password functionality with valid input"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
	
		
		System.out.println("Step 2 : Navigate to Forgot Password Page" + Constants.ApplicationURL_EM +"/login/lostpassword/normal");
		Reporter.log("Step 2 : Navigate to Forgot Password Page" + Constants.ApplicationURL_EM +"/login/lostpassword/normal");
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/lostpassword/normal");
			
		
		System.out.println("Step 3 : Enter valid input for username field and click on Next button");
		Reporter.log("Step 3 : Enter valid input for username field and click on Next button");
		
			
			SeleniumFunc.EnterValueInTextbox("css", "#uname", Constants.EM_DemoAdmin_Username);
			SeleniumFunc.ClickOnElement("css", "#forgot_password_form .btn");
			
		

		System.out.println("Step 4 : Verify congratualation message received");
		Reporter.log("Step 4 : Verify congratualation message received");
			
			
			String ActualCongratulation = SeleniumFunc.GetElementText("css", ".alert-flash.alert.alert-success");
			String ExpectedCongratulation = "An email has been sent with further instructions.";

				
			if(ActualCongratulation.equals(ExpectedCongratulation))
			{
					
				System.out.println("Success !! Congratualation message is displayed with correct text");
				Reporter.log("Success !! Congratualation message is displayed with correct text"); 
				
			}
			else
			{
				
				System.out.println("Failure !!  error in password hint retrival");
				Reporter.log("Failure !!  error in password hint retrival"); 
					
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

	
	/* Test 5: 
	 * Verify user use 'Secret question' screen for password retrieval
	*/ 
	/*@Test
	private void VerifySecurityQueAns() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify user use 'Secret question' screen for password retrieval"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify user use 'Secret question' screen for password retrieval"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
	
		
		System.out.println("Step 2 : Navigate to Forgot Password Page" + Constants.ApplicationURL_EM +"/login/lostpassword/normal");
		Reporter.log("Step 2 : Navigate to Forgot Password Page" + Constants.ApplicationURL_EM +"/login/lostpassword/normal");
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/lostpassword/normal");
			
		
		System.out.println("Step 3 : Go to Security answer page");
		Reporter.log("Step 3 : Go to Security answer page");
		
			
			SeleniumFunc.EnterValueInTextbox("css", "#uname", Constants.EM_DemoAdmin_Username);
			SeleniumFunc.ClickOnElement("css", ".btn");
			
		
		System.out.println("Step 3 : Verify control redirected to hint retrived page");
		Reporter.log("Step 3 : Verify control redirected to hint retrived page");
		
		
			String ActualPageHeader = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
			String ExpectedPageHeader = "Password Hint Retrieved";

			
			if(ActualPageHeader.equals(ExpectedPageHeader))
			{
				
				System.out.println("Success !! Password hint retrived successfully");
				Reporter.log("Success !! Password hint retrived successfully"); 
			
			}
			else
			{
			
				System.out.println("Failure !!  Password hint not retrived successfully");
				Reporter.log("Failure !!  Password hint not retrived successfully"); 
				
				AssertFailedCount++;
			
			}


			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
			
		System.out.println("Step 3 : Verify functionality of still can't remember");
		Reporter.log("Step 3 : Verify functionality of still can't remember");
			
			
			SeleniumFunc.ClickOnElement("css", ".login.span6.offset3>p>a");
			Thread.sleep(5000);
		
		System.out.println("Step 4 : Verify control is redirected to Secret question screen");
		Reporter.log("Step 4 : Verify control is redirected to Secret question screen");
		
		
			//Verify Header
			String ActualHdr = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
			String ExpectedHdr = "Please Answer Your Secret Question";
			
			
			if(ActualHdr.equals(ExpectedHdr))
			{
				
				System.out.println("Success !! Control redirected to Secret Question page");
				Reporter.log("Success !! Control redirected to Secret Question page"); 	
			
			}
			else
			{
			
				System.out.println("Failure !! Control is not redirected to Secret Question page");
				Reporter.log("Failure !! Control is not redirected to Secret Question page"); 
				
				AssertFailedCount++;
			
			}	
			
			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
			
		System.out.println("Step 5 : Enter correct security question answer");
		Reporter.log("Step 5 : Enter correct security question answer");
		
			
			SeleniumFunc.EnterValueInTextbox("css", "#question_answer", "clarion");
			SeleniumFunc.ClickOnElement("css", ".btn");
			Thread.sleep(5000);

		
		System.out.println("Step 6 : Verify success message for email sent to user ");
		Reporter.log("Step 6 : Verify success message for email sent to user");
		
			
			//Verify Header
			String ActualPageHdr = SeleniumFunc.GetElementText("css", ".content>h2");
			String ExpectedPageHdr = "It's In the Mail";
			
			if(ActualPageHdr.equals(ExpectedPageHdr))
			{
					
				System.out.println("Success !! Correct header is present");
				Reporter.log("Success !! Correct header is present"); 	
			
			}
			else
			{
			
				System.out.println("Failure !! Incorrect header");
				Reporter.log("Failure !! Incorrect header"); 
				
				AssertFailedCount++;
			
			}
			
			//Verify Success Message
			String ActualSuccessMsg = SeleniumFunc.GetElementText("css", ".content>p");
			String ExpectedSuccessMsg = "You answered correctly. An e-mail has been sent to the address you provided. " +
					"Follow the instructions in the e-mail to finish resetting your password.";
			
			if(ActualSuccessMsg.equals(ExpectedSuccessMsg))
			{
					
				System.out.println("Success !! Correct success message is present");
				Reporter.log("Success !! Correct success message is present"); 	
			
			}
			else
			{
			
				System.out.println("Failure !! Incorrect success message");
				Reporter.log("Failure !! Incorrect success message"); 
				
				AssertFailedCount++;
			
			}
			
			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
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
		
	}	*/
	
	
	// Test : EM > Forgot password > Validation message for Username with  registered Email
	@Test
	private void VerifyValidationForUsernameWithoutRegisteredEmail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test  : Verify Validation message for Username without registered Email"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test  : Verify Validation message for Username without registered Email"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
	
		
		System.out.println("Step 2 : Navigate to Forgot Password Page" + Constants.ApplicationURL_EM +"/login/lostpassword/normal");
		Reporter.log("Step 2 : Navigate to Forgot Password Page" + Constants.ApplicationURL_EM +"/login/lostpassword/normal");
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/lostpassword/normal");
			
		
		System.out.println("Step 3 : Click on 'Next' button by using username without Registered Email");
		Reporter.log("Step 3 : Click on 'Next' button by using username without Registered Email");
		
			SeleniumFunc.EnterValueInTextbox("css", "#uname", Constants.UsernameWithoutRegisteredEmail);
			SeleniumFunc.ClickOnElement("css", "#forgot_password_form .btn");
			
		
		System.out.println("Step 4 : Verify whether Correct validation message is displayed or not for username without Registered Email");
		Reporter.log("Step 4 : Verify whether validation message is displayed or not for username without Registered Email");
		
		
			String ActualValidation = SeleniumFunc.GetElementText("css", ".alert-flash.alert.alert-danger");
			//String ExpectedValidation = "You don't have an email address on file that we can use to reset your password. Please click here to contact our support team.";
			String ExpectedValidation = "That username doesn't exist in our records. If you forgot your username, click here to contact our support team.";
			if(ActualValidation.equals(ExpectedValidation))
			{
				
				System.out.println("Success !! correct validation messages are displayed for username without Registered Email");
				Reporter.log("Success !! correct validation messages are displayed for username without Registered Email"); 
			
			}
			else
			{
	
				System.out.println("Failure !!  Incorrect validation messages are displayed for username without Registered Email" + "\n" + "Expected Validation messages : "  
									+ "\n" + ExpectedValidation + "\n" + 
									 "Actual Validation messages : " + "\n" +  ActualValidation);
				Reporter.log("Failure !!  Incorrect validation messages are displayed for username without Registered Email" + "\n" + "Expected Validation messages : "  
						+ "\n" + ExpectedValidation + "\n" + 
						 "Actual Validation messages : " + "\n" + ActualValidation); 
				
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
	
	
	
	// Test : EM > Forgot password > Validation message for Invalid Username
	@Test
	private void VerifyValidationForInvalidUsername() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test  : Verify Validation message for Invalid Username"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test  : Verify Validation message for Invalid Username"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
	
		
		System.out.println("Step 2 : Navigate to Forgot Password Page" + Constants.ApplicationURL_EM +"/login/lostpassword/normal");
		Reporter.log("Step 2 : Navigate to Forgot Password Page" + Constants.ApplicationURL_EM +"/login/lostpassword/normal");
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/lostpassword/normal");
			
		
		System.out.println("Step 3 : Click on 'Next' button by using Invalid Username");
		Reporter.log("Step 3 : Click on 'Next' button by using Invalid Username");
		
			SeleniumFunc.EnterValueInTextbox("css", "#uname", Constants.InvalidUsername);
			SeleniumFunc.ClickOnElement("css", "#forgot_password_form .btn");
			
		
		System.out.println("Step 4 : Verify whether Correct validation message is displayed or not for Invalid Username");
		Reporter.log("Step 4 : Verify whether validation message is displayed or not for Invalid Username");
		
		
			String ActualValidation = SeleniumFunc.GetElementText("css", ".alert-flash.alert.alert-danger");
			String ExpectedValidation = "That username doesn't exist in our records. If you forgot your username, click here to contact our support team.";
			
			if(ActualValidation.equals(ExpectedValidation))
			{
				
				System.out.println("Success !! correct validation messages are displayed for Invalid Username");
				Reporter.log("Success !! correct validation messages are displayed for Invalid Username"); 
			
			}
			else
			{
	
				System.out.println("Failure !!  Incorrect validation messages are displayed for Invalid Username" + "\n" + "Expected Validation messages : "  
									+ "\n" + ExpectedValidation + "\n" + 
									 "Actual Validation messages : " + "\n" +  ActualValidation);
				Reporter.log("Failure !!  Incorrect validation messages are displayed for Invalid Username" + "\n" + "Expected Validation messages : "  
						+ "\n" + ExpectedValidation + "\n" + 
						 "Actual Validation messages : " + "\n" + ActualValidation); 
				
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
}
