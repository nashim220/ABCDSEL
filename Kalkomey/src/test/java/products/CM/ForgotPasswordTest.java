package products.CM;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.ForgotPasswordPage;
import pageFactory.CM.LoginPage;
import pageFactory.CM.ResetPasswordPage;


import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class ForgotPasswordTest 
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
	 * Verify UI elements on Reset Password page
	*/ 
	@Test
	private void ForgotPassword_UIElementVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify UI elements on Reset Password page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify UI elements on Reset Password page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ForgotPasswordPage forgotpassword = new ForgotPasswordPage(driver);
		
		System.out.println("Step 1 : Navigate to Reset Password page : " + Constants.ApplicationURL_CM + "/password/recover");
		Reporter.log("Step 1 : Navigate to Reset Password page : " + Constants.ApplicationURL_CM + "/password/recover");
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM + "/password/recover");
			
			
		System.out.println("Step 2 : Verifying  page title &  text");
		Reporter.log("Step 2 : Verifying page title &  text"); 
			
			if(forgotpassword.PageTitle.getText().trim().equals("Reset Password"))
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
			Thread.sleep(2000);
			if(forgotpassword.PageTitleText.getText().trim().equals("To reset your password, please enter the email associated with your account below. We will use this information to send you further instructions."))
			{
				System.out.println("Success !! Page title text is correct");
				Reporter.log("Success !! Page title text is correct"); 
			}
			else
			{
				System.out.println("Failure !!  Page title text is incorrect ");
				Reporter.log("Failure !!  Page title text is incorrect "); 
				AssertFailedCount++;
			}
			
		System.out.println("Step 3 : Verifying presence of UI elements : Email Address textbox, Send button ");
		Reporter.log("Step 3 : Verifying presence of UI elements : Email Address textbox, Send button ");
		
			if(SeleniumFunc.IsElementPresent("id", "address"))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Email Address Textbox is present");
				Reporter.log("Success !! Email Address Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! Email Address Textbox is NOT present ");
				Reporter.log("Failure !! Email Address Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			
			if(SeleniumFunc.IsElementPresent("name", "commit"))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Send button is present");
				Reporter.log("Success !! Send button is present"); 
			}
			else
			{
				System.out.println("Failure !! Send button is NOT present ");
				Reporter.log("Failure !! Send button is NOT present "); 
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
	 * Verify validation message for Email Address field
	*/ 
	@Test
	private void ForgotPassword_VerifyValidationMessageForEmail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify validation message for Email Address field"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify validation message for Email Address field"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ForgotPasswordPage forgotpassword = new ForgotPasswordPage(driver);
		
		System.out.println("Step 1 : Navigate to Reset Password page : " + Constants.ApplicationURL_CM + "/password/recover");
		Reporter.log("Step 1 : Navigate to Reset Password page : " + Constants.ApplicationURL_CM + "/password/recover");
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM + "/password/recover");
			
			
		System.out.println("Step 2 : Clicking on 'Send' button without entering Email Address");
		Reporter.log("Step 2 : Clicking on 'Send' button without entering Email Address"); 
			
		
			forgotpassword.SendButton.click();
			
			
			//verifying validation message
			String expectedtext = "You need to enter an email address in order to receive password reset instructions.";
			String actualtext = forgotpassword.ErrorMessagesText.getText().trim();
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Validation message is correct i.e. " + expectedtext);
				Reporter.log("Success !! Validation message is correct i.e. " + expectedtext);
			}
			else
			{
				System.out.println("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				Reporter.log("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				AssertFailedCount++;
			}
		
			
		System.out.println("Step 3 : Entering invalid email address and click on 'Send' button");
		Reporter.log("Step 3 : Entering invalid email address and click on 'Send' button");
		
		Thread.sleep(2000);
		forgotpassword.EmailAddress.sendKeys("hk");
		forgotpassword.SendButton.click();
		
		
		//verifying validation message
		expectedtext = "We were unable to locate an account associated with that email.";
		actualtext = forgotpassword.ErrorMessagesText.getText().trim();
		if(actualtext.equals(expectedtext))
		{
			Thread.sleep(1000);
			System.out.println("Success !! Validation message is correct i.e. " + expectedtext);
			Reporter.log("Success !! Validation message is correct i.e. " + expectedtext);
		}
		else
		{
			System.out.println("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
			Reporter.log("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
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
	 * Verify that Reset Password email is sent to user successfully
	*/ 
	@Test
	private void ForgotPassword_ResetEmail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify validation message for Email Address field"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify validation message for Email Address field"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ForgotPasswordPage forgotpassword = new ForgotPasswordPage(driver);
		LoginPage login  = new LoginPage(driver);
		
		System.out.println("Step 1 : Navigate to Reset Password page : " + Constants.ApplicationURL_CM + "/password/recover");
		Reporter.log("Step 1 : Navigate to Reset Password page : " + Constants.ApplicationURL_CM + "/password/recover");
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM + "/password/recover");
			
			
		System.out.println("Step 2 : Entering Email Address and clicking on 'Send' button ");
		Reporter.log("Step 2 : Entering Email Address and clicking on 'Send' button ");
			
			forgotpassword.EmailAddress.sendKeys(Constants.CM_TestUserKeAdmin);
			forgotpassword.SendButton.click();
			
			
			//verifying validation message
			String expectedtext = "We've sent an email to " + Constants.CM_TestUserKeAdmin + " containing a temporary link that will allow you to reset your password for the next 24 hours.";		
			String actualtext = login.SuccessMessagesText.getText().trim();
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Validation message is correct i.e. " + expectedtext);
				Reporter.log("Success !! Validation message is correct i.e. " + expectedtext);
			}
			else
			{
				System.out.println("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				Reporter.log("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				AssertFailedCount++;
			}
		
			
			System.out.println("Step 4 : Navigating to Email Box and verifying whether email is received with correct details or not");
			Reporter.log("Step 4 : Navigating to Email Box and verifying whether email is received with correct details or not"); 
				
				String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=" + Constants.CM_TestUserKeAdmin.split("@")[0] + "#/#public_maildirdiv";
				System.out.println(EmailBoxUrl);
				
				SeleniumFunc.ToGoToUrl(EmailBoxUrl);
				Thread.sleep(Constants.ThreadWaitInmiliseconds);
				
				String ActualMessageTitle= SeleniumFunc.GetElementText("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div").trim();
				//System.out.println(ActualMessageTitle);
												
				String ExpectedMessageTitle="Kalkomey Product Suite: Please Reset Your Password"; 
				//System.out.println(ExpectedMessageTitle);
				
				if(ActualMessageTitle.equalsIgnoreCase(ExpectedMessageTitle))
				{
					System.out.println("Success!! Email is received with correct Title i.e. " + ExpectedMessageTitle);
					Reporter.log("Success!! Email is received with correct Title ie. " + ExpectedMessageTitle); 
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
							"Hi Selenium," + "\n" +
							"We've recieved a request to allow you to reset your password." + "\n" + 
							"Please use the following link to do so within 24 hours:";
					
					
					if(ActualMessageBody.contains(ExpectedMessageBody))
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
					//Nothing :)
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
	 * Verify Reset Password functionality
	*/ 
	@Test
	private void ForgotPassword_PasswordReset() throws Exception
	{
		
		System.out.println("====" + "\n" +
					"Test 4 : Verify Reset Password functionality"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify Reset Password functionality"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ForgotPasswordPage forgotpassword = new ForgotPasswordPage(driver);
		LoginPage login  = new LoginPage(driver);
		ResetPasswordPage resetpassword = new ResetPasswordPage(driver);
		
		System.out.println("Step 1 : Navigate to Reset Password page : " + Constants.ApplicationURL_CM + "/password/recover");
		Reporter.log("Step 1 : Navigate to Reset Password page : " + Constants.ApplicationURL_CM + "/password/recover");
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM + "/password/recover");
			Thread.sleep(1000);
			
		System.out.println("Step 2 : Entering Email Address and clicking on 'Send' button ");
		Reporter.log("Step 2 : Entering Email Address and clicking on 'Send' button ");
			
			forgotpassword.EmailAddress.sendKeys(Constants.CM_TestUserKeAdmin);
			forgotpassword.SendButton.click();
			
			
			//verifying validation message
			String expectedtext = "We've sent an email to " + Constants.CM_TestUserKeAdmin + " containing a temporary link that will allow you to reset your password for the next 24 hours.";		
			String actualtext = login.SuccessMessagesText.getText().trim();
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Validation message is correct i.e. " + expectedtext);
				Reporter.log("Success !! Validation message is correct i.e. " + expectedtext);
			}
			else
			{
				System.out.println("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				Reporter.log("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				AssertFailedCount++;
			}
		
			
			System.out.println("Step 4 : Navigating to Email Box and clicking on Password Reset link");
			Reporter.log("Step 4 : Navigating to Email Box and clicking on Password Reset link"); 
				
				String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=" + Constants.CM_TestUserKeAdmin.split("@")[0] + "#/#public_maildirdiv";

				System.out.println(EmailBoxUrl);
				
				SeleniumFunc.ToGoToUrl(EmailBoxUrl);
				Thread.sleep(Constants.ThreadWaitInmiliseconds);
				
				
				try
				{
					//Opening email body and verifying content
					
					SeleniumFunc.ClickOnElement("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");
				
					Thread.sleep(4000);
					
					//div.mailview
					driver.switchTo().frame("publicshowmaildivcontent");
					
					Thread.sleep(2000);
					String url = SeleniumFunc.GetAttributeValue("css", "tbody>tr>td>span>a", "href");
					
					//Navigating to Password Reset page
					SeleniumFunc.ToGoToUrl(url);
				}
				catch(Exception e)
				{
					//Nothing :)
				}
				
				
			System.out.println("Step 5 : Entering New Password / Password Confirmation and click on 'Update Password' button");
			Reporter.log("Step 5 : Entering New Password / Password Confirmation and click on 'Update Password' button");
			
				Thread.sleep(1000);	
				String password = "clarion" + JH.GenerateRandomNumber(); 
				resetpassword.NewPassword.sendKeys(password);
				resetpassword.PasswordConfirmation.sendKeys(password);
				resetpassword.UpdatePasswordButton.click();
				Thread.sleep(5000);
						
				//verifying validation message
				expectedtext = "Your password has been successfully updated.";		
				actualtext = login.SuccessMessagesText.getText().trim();
				if(actualtext.equals(expectedtext))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Validation message is correct i.e. " + expectedtext);
					Reporter.log("Success !! Validation message is correct i.e. " + expectedtext);
				}
				else
				{
					System.out.println("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
					Reporter.log("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
					AssertFailedCount++;
				}
				
				//verifying page url
				expectedtext = Constants.ApplicationURL_CM + "/";		
				actualtext = driver.getCurrentUrl();
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! url is correct i.e. " + expectedtext);
					Reporter.log("Success !! url is correct i.e. " + expectedtext);
				}
				else
				{
					System.out.println("Failure !! url is incorrect. Expected is :" + expectedtext + " , Actual  is :" + actualtext  );
					Reporter.log("Failure !! url is incorrect. Expected is :" + expectedtext + " , Actual is :" + actualtext  );
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
	 * Verify UI elements on Reset password page
	*/ 
	@Test
	private void ForgotPassword_ResetPasswordUI() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify UI elements on Reset password page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify UI elements on Reset password page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ForgotPasswordPage forgotpassword = new ForgotPasswordPage(driver);
		LoginPage login  = new LoginPage(driver);
		ResetPasswordPage resetpassword = new ResetPasswordPage(driver);
		
		System.out.println("Step 1 : Navigate to Reset Password page : " + Constants.ApplicationURL_CM + "/password/recover");
		Reporter.log("Step 1 : Navigate to Reset Password page : " + Constants.ApplicationURL_CM + "/password/recover");
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM + "/password/recover");
			
			
		System.out.println("Step 2 : Entering Email Address and clicking on 'Send' button ");
		Reporter.log("Step 2 : Entering Email Address and clicking on 'Send' button ");
			
			forgotpassword.EmailAddress.sendKeys(Constants.CM_TestUserKeAdmin);
			forgotpassword.SendButton.click();
			
			
			//verifying validation message
			String expectedtext = "We've sent an email to " + Constants.CM_TestUserKeAdmin + " containing a temporary link that will allow you to reset your password for the next 24 hours.";		
			String actualtext = login.SuccessMessagesText.getText().trim();
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Validation message is correct i.e. " + expectedtext);
				Reporter.log("Success !! Validation message is correct i.e. " + expectedtext);
			}
			else
			{
				System.out.println("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				Reporter.log("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				AssertFailedCount++;
			}
		
			
			System.out.println("Step 4 : Navigating to Email Box and clicking on Password Reset link");
			Reporter.log("Step 4 : Navigating to Email Box and clicking on Password Reset link"); 
				
				String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=" + Constants.CM_TestUserKeAdmin.split("@")[0] + "#/#public_maildirdiv";
				System.out.println(EmailBoxUrl);
				
				SeleniumFunc.ToGoToUrl(EmailBoxUrl);
				Thread.sleep(Constants.ThreadWaitInmiliseconds);
				
				
				try
				{
					//Opening email body and verifying content
					
					SeleniumFunc.ClickOnElement("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");
					
					Thread.sleep(4000);
					//div.mailview
					driver.switchTo().frame("publicshowmaildivcontent");
					
					String url = SeleniumFunc.GetAttributeValue("css", "tbody>tr>td>span>a", "href");
					
					//Navigating to Password Reset page
					SeleniumFunc.ToGoToUrl(url);
				}
				catch(Exception e)
				{
					//Nothing :)
				}
				
			
			System.out.println("Step 5 : Verifying  page title &  text");
			Reporter.log("Step 5 : Verifying page title &  text"); 
				
				if(resetpassword.PageTitle.getText().trim().equals("Reset Password"))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Page title is correct");
					Reporter.log("Success !! Page title is correct"); 
				}
				else
				{
					System.out.println("Failure !!  Page title is incorrect ");
					Reporter.log("Failure !!  Page title is incorrect "); 
					AssertFailedCount++;
				}
				
				if(forgotpassword.PageTitleText.getText().trim().equals("You may now reset the password for " + Constants.CM_TestUserKeAdmin + "."))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Page title text is correct");
					Reporter.log("Success !! Page title text is correct"); 
				}
				else
				{
					System.out.println("Failure !!  Page title text is incorrect ");
					Reporter.log("Failure !!  Page title text is incorrect "); 
					AssertFailedCount++;
				}
				
			System.out.println("Step 6 : Verifying presence of UI elements : New Password, Password Confirmation, Update Password button ");
			Reporter.log("Step 6 : Verifying presence of UI elements : New Password, Password Confirmation, Update Password button ");
			
				if(SeleniumFunc.IsElementPresent("id", "password"))
				{
					Thread.sleep(1000);
					System.out.println("Success !! New Password Textbox is present");
					Reporter.log("Success !! New Password Textbox is present"); 
				}
				else
				{
					System.out.println("Failure !! New Password Textbox is NOT present ");
					Reporter.log("Failure !! New Password Textbox is NOT present "); 
					AssertFailedCount++;
				}
				
				
				if(SeleniumFunc.IsElementPresent("id", "password_confirmation"))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Password_confirmation Textbox is present");
					Reporter.log("Success !! Password_confirmation Textbox is present"); 
				}
				else
				{
					System.out.println("Failure !! Password_confirmation Textbox is NOT present ");
					Reporter.log("Failure !! Password_confirmation Textbox is NOT present "); 
					AssertFailedCount++;
				}
				
				
				if(SeleniumFunc.IsElementPresent("name", "commit"))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Update Password button is present");
					Reporter.log("Success !! Update Password button is present"); 
				}
				else
				{
					System.out.println("Failure !! Update Password button is NOT present ");
					Reporter.log("Failure !! Update Password button is NOT present "); 
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
	 * Verify validation message on Reset Password page
	*/ 
	@Test
	private void ForgotPassword_ResetPasswordVerifyValidationMessage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify validation message on Reset Password page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6: Verify validation message on Reset Password page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ForgotPasswordPage forgotpassword = new ForgotPasswordPage(driver);
		LoginPage login  = new LoginPage(driver);
		ResetPasswordPage resetpassword = new ResetPasswordPage(driver);
		
		System.out.println("Step 1 : Navigate to Reset Password page : " + Constants.ApplicationURL_CM + "/password/recover");
		Reporter.log("Step 1 : Navigate to Reset Password page : " + Constants.ApplicationURL_CM + "/password/recover");
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM + "/password/recover");
			
			
		System.out.println("Step 2 : Entering Email Address and clicking on 'Send' button ");
		Reporter.log("Step 2 : Entering Email Address and clicking on 'Send' button ");
			
			forgotpassword.EmailAddress.sendKeys(Constants.CM_TestUserKeAdmin);
			forgotpassword.SendButton.click();
			
			
			//verifying validation message
			String expectedtext = "We've sent an email to " + Constants.CM_TestUserKeAdmin + " containing a temporary link that will allow you to reset your password for the next 24 hours.";		
			String actualtext = login.SuccessMessagesText.getText().trim();
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Validation message is correct i.e. " + expectedtext);
				Reporter.log("Success !! Validation message is correct i.e. " + expectedtext);
			}
			else
			{
				System.out.println("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				Reporter.log("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				AssertFailedCount++;
			}
		
			
			System.out.println("Step 4 : Navigating to Email Box and clicking on Password Reset link");
			Reporter.log("Step 4 : Navigating to Email Box and clicking on Password Reset link"); 
				
				String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=" + Constants.CM_TestUserKeAdmin.split("@")[0] + "#/#public_maildirdiv";
				System.out.println(EmailBoxUrl);
				
				SeleniumFunc.ToGoToUrl(EmailBoxUrl);
				Thread.sleep(Constants.ThreadWaitInmiliseconds);
				
				
				try
				{
					//Opening email body and verifying content
					
					SeleniumFunc.ClickOnElement("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");
					
					Thread.sleep(4000);
					//div.mailview
					driver.switchTo().frame("publicshowmaildivcontent");
										
					String url = SeleniumFunc.GetAttributeValue("css", "tbody>tr>td>span>a", "href");
					
					//Navigating to Password Reset page
					SeleniumFunc.ToGoToUrl(url);
				}
				catch(Exception e)
				{
					//Nothing :)
				}
			
				
			System.out.println("Step 5 : Clicking on 'Update Password' button without entering any details");
			Reporter.log("Step 5 : Clicking on 'Update Password' button without entering any details"); 
				
				resetpassword.UpdatePasswordButton.click();
				//verifying validation message
				expectedtext = "1 error prohibited this user from being saved:" + "\n" + "Password can't be blank";
				actualtext = resetpassword.ErrorMessagesText.getText().trim();
				if(actualtext.equals(expectedtext))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Validation message is correct i.e. " + expectedtext);
					Reporter.log("Success !! Validation message is correct i.e. " + expectedtext);
				}
				else
				{
					System.out.println("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
					Reporter.log("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
					AssertFailedCount++;
				}
			
			System.out.println("Step 6 : Entering different value for 'New Password' & 'Password confirmation' field and Clicking on 'Update Password' button ");
			Reporter.log("Step 6 : Entering different value for 'New Password' & 'Password confirmation' field and Clicking on 'Update Password' button "); 
				
				resetpassword.NewPassword.sendKeys("dffdff");
				resetpassword.PasswordConfirmation.sendKeys("454454dfddd");
				resetpassword.UpdatePasswordButton.click();
				
				//verifying validation message
				expectedtext = "1 error prohibited this user from being saved:" + "\n" + "Password confirmation doesn't match Password";
				actualtext = resetpassword.ErrorMessagesText.getText().trim();
				if(actualtext.equals(expectedtext))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Validation message is correct i.e. " + expectedtext);
					Reporter.log("Success !! Validation message is correct i.e. " + expectedtext);
				}
				else
				{
					System.out.println("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
					Reporter.log("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
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
	 * Verify that user can relogin after password reset
	*/ 
	@Test
	private void ForgotPassword_ResetPasswordReLogin() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Verify that user can relogin after password reset"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Verify that user can relogin after password reset"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ForgotPasswordPage forgotpassword = new ForgotPasswordPage(driver);
		LoginPage login  = new LoginPage(driver);
		ResetPasswordPage resetpassword = new ResetPasswordPage(driver);
		
		System.out.println("Step 1 : Navigate to Reset Password page : " + Constants.ApplicationURL_CM + "/password/recover");
		Reporter.log("Step 1 : Navigate to Reset Password page : " + Constants.ApplicationURL_CM + "/password/recover");
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM + "/password/recover");
			
			
		System.out.println("Step 2 : Entering Email Address and clicking on 'Send' button ");
		Reporter.log("Step 2 : Entering Email Address and clicking on 'Send' button ");
			
			forgotpassword.EmailAddress.sendKeys(Constants.CM_TestUserKeAdmin);
			forgotpassword.SendButton.click();
			
			
			//verifying validation message
			String expectedtext = "We've sent an email to " + Constants.CM_TestUserKeAdmin + " containing a temporary link that will allow you to reset your password for the next 24 hours.";		
			String actualtext = login.SuccessMessagesText.getText().trim();
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Validation message is correct i.e. " + expectedtext);
				Reporter.log("Success !! Validation message is correct i.e. " + expectedtext);
			}
			else
			{
				System.out.println("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				Reporter.log("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				AssertFailedCount++;
			}
		
			
		System.out.println("Step 4 : Navigating to Email Box and clicking on Password Reset link");
		Reporter.log("Step 4 : Navigating to Email Box and clicking on Password Reset link"); 
			
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=" + Constants.CM_TestUserKeAdmin.split("@")[0] + "#/#public_maildirdiv";
			System.out.println(EmailBoxUrl);
			
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
			
			
			try
			{
				//Opening email body and verifying content
				
				SeleniumFunc.ClickOnElement("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");
				
				Thread.sleep(4000);
				//div.mailview
				driver.switchTo().frame("publicshowmaildivcontent");
									
				String url = SeleniumFunc.GetAttributeValue("css", "tbody>tr>td>span>a", "href");
				
				//Navigating to Password Reset page
				SeleniumFunc.ToGoToUrl(url);
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				//Nothing :)
			}
		
			
		System.out.println("Step 5 : Entering New Password / Password Confirmation and click on 'Update Password' button");
		Reporter.log("Step 5 : Entering New Password / Password Confirmation and click on 'Update Password' button");
		
		    Thread.sleep(1000);
			String password = "clarion" + JH.GenerateRandomNumber(); 
			resetpassword.NewPassword.sendKeys(password);
			resetpassword.PasswordConfirmation.sendKeys(password);
			resetpassword.UpdatePasswordButton.click();
			Thread.sleep(5000);
					
			//verifying validation message
			expectedtext = "Your password has been successfully updated.";		
			actualtext = login.SuccessMessagesText.getText().trim();
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Validation message is correct i.e. " + expectedtext);
				Reporter.log("Success !! Validation message is correct i.e. " + expectedtext);
			}
			else
			{
				System.out.println("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				Reporter.log("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				AssertFailedCount++;
			}
			
			//verifying page url
			expectedtext = Constants.ApplicationURL_CM + "/";		
			actualtext = driver.getCurrentUrl();
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(1000);
				System.out.println("Success !! url is correct i.e. " + expectedtext);
				Reporter.log("Success !! url is correct i.e. " + expectedtext);
			}
			else
			{
				System.out.println("Failure !! url is incorrect. Expected is :" + expectedtext + " , Actual  is :" + actualtext  );
				Reporter.log("Failure !! url is incorrect. Expected is :" + expectedtext + " , Actual is :" + actualtext  );
				AssertFailedCount++;
			}
		
		System.out.println("Step 6 : Loging out of application and try to login with new password");
		Reporter.log("Step 6 : Loging out of application and try to login with new password");
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM + "/logout");
			login.EnterUsername(Constants.CM_TestUserKeAdmin);
			login.EnterPassword(password);
			login.ClickOnLogInButton();
			
			Thread.sleep(5000);
			
			//verifying validation message
			expectedtext = "Signed in!";		
			actualtext = login.SuccessMessagesText.getText().trim();
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Validation message is correct i.e. " + expectedtext);
				Reporter.log("Success !! Validation message is correct i.e. " + expectedtext);
			}
			else
			{
				System.out.println("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				Reporter.log("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				AssertFailedCount++;
			}
			
			//verifying page url
			expectedtext = Constants.ApplicationURL_CM + "/";		
			actualtext = driver.getCurrentUrl();
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(1000);
				System.out.println("Success !! url is correct i.e. " + expectedtext);
				Reporter.log("Success !! url is correct i.e. " + expectedtext);
			}
			else
			{
				System.out.println("Failure !! url is incorrect. Expected is :" + expectedtext + " , Actual  is :" + actualtext  );
				Reporter.log("Failure !! url is incorrect. Expected is :" + expectedtext + " , Actual is :" + actualtext  );
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
	 * Verify that user is unable to use reset password link twice
	*/ 
	@Test
	private void ForgotPassword_ResetPasswordLinkReUse() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Verify that user is unable to use reset password link twice"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Verify that user is unable to use reset password link twice"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ForgotPasswordPage forgotpassword = new ForgotPasswordPage(driver);
		LoginPage login  = new LoginPage(driver);
		ResetPasswordPage resetpassword = new ResetPasswordPage(driver);
		
		System.out.println("Step 1 : Navigate to Reset Password page : " + Constants.ApplicationURL_CM + "/password/recover");
		Reporter.log("Step 1 : Navigate to Reset Password page : " + Constants.ApplicationURL_CM + "/password/recover");
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM + "/password/recover");
			
			
		System.out.println("Step 2 : Entering Email Address and clicking on 'Send' button ");
		Reporter.log("Step 2 : Entering Email Address and clicking on 'Send' button ");
			
			forgotpassword.EmailAddress.sendKeys(Constants.CM_TestUserKeAdmin);
			forgotpassword.SendButton.click();
			
			
			//verifying validation message
			String expectedtext = "We've sent an email to " + Constants.CM_TestUserKeAdmin + " containing a temporary link that will allow you to reset your password for the next 24 hours.";		
			String actualtext = login.SuccessMessagesText.getText().trim();
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Validation message is correct i.e. " + expectedtext);
				Reporter.log("Success !! Validation message is correct i.e. " + expectedtext);
			}
			else
			{
				System.out.println("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				Reporter.log("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				AssertFailedCount++;
			}
		
			
		System.out.println("Step 4 : Navigating to Email Box and clicking on Password Reset link");
		Reporter.log("Step 4 : Navigating to Email Box and clicking on Password Reset link"); 
			
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=" + Constants.CM_TestUserKeAdmin.split("@")[0] + "#/#public_maildirdiv";
			System.out.println(EmailBoxUrl);
			
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
			
			String url = null ;
			try
			{
				//Opening email body and verifying content
				
				SeleniumFunc.ClickOnElement("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");
				
				Thread.sleep(4000);
				//div.mailview
				driver.switchTo().frame("publicshowmaildivcontent");
									
				url = SeleniumFunc.GetAttributeValue("css", "tbody>tr>td>span>a", "href");
				
				//Navigating to Password Reset page
				SeleniumFunc.ToGoToUrl(url);
			}
			catch(Exception e)
			{
				//Nothing :)
			}
		
			
		System.out.println("Step 5 : Entering New Password / Password Confirmation and click on 'Update Password' button");
		Reporter.log("Step 5 : Entering New Password / Password Confirmation and click on 'Update Password' button");
		
		    Thread.sleep(1000);
			String password = "clarion" + JH.GenerateRandomNumber(); 
			resetpassword.NewPassword.sendKeys(password);
			resetpassword.PasswordConfirmation.sendKeys(password);
			resetpassword.UpdatePasswordButton.click();
			Thread.sleep(5000);
					
			//verifying validation message
			expectedtext = "Your password has been successfully updated.";		
			actualtext = login.SuccessMessagesText.getText().trim();
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Validation message is correct i.e. " + expectedtext);
				Reporter.log("Success !! Validation message is correct i.e. " + expectedtext);
			}
			else
			{
				System.out.println("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				Reporter.log("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				AssertFailedCount++;
			}
			
			//verifying page url
			expectedtext = Constants.ApplicationURL_CM + "/";		
			actualtext = driver.getCurrentUrl();
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(1000);
				System.out.println("Success !! url is correct i.e. " + expectedtext);
				Reporter.log("Success !! url is correct i.e. " + expectedtext);
			}
			else
			{
				System.out.println("Failure !! url is incorrect. Expected is :" + expectedtext + " , Actual  is :" + actualtext  );
				Reporter.log("Failure !! url is incorrect. Expected is :" + expectedtext + " , Actual is :" + actualtext  );
				AssertFailedCount++;
			}
		
		System.out.println("Step 6 : Loging out of application and try to reset password again using same reset link");
		Reporter.log("Step 6 : Loging out of application and try to reset password again using same reset link");
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM + "/logout");
			//Navigating to Password Reset page
			SeleniumFunc.ToGoToUrl(url);
			
			Thread.sleep(5000);
			
			//verifying validation message
			expectedtext = "The reset link you used has either expired or is invalid. Please try again.";		
			actualtext = forgotpassword.ErrorMessagesText.getText().trim();
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Validation message is correct i.e. " + expectedtext);
				Reporter.log("Success !! Validation message is correct i.e. " + expectedtext);
			}
			else
			{
				System.out.println("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				Reporter.log("Failure !! Validation message is incorrect. Expected is :" + expectedtext + " , Actual text is :" + actualtext  );
				AssertFailedCount++;
			}
			
			//verifying page url
			expectedtext = Constants.ApplicationURL_CM + "/password/recover";		
			actualtext = driver.getCurrentUrl();
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(1000);
				System.out.println("Success !! url is correct i.e. " + expectedtext);
				Reporter.log("Success !! url is correct i.e. " + expectedtext);
			}
			else
			{
				System.out.println("Failure !! url is incorrect. Expected is :" + expectedtext + " , Actual  is :" + actualtext  );
				Reporter.log("Failure !! url is incorrect. Expected is :" + expectedtext + " , Actual is :" + actualtext  );
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
