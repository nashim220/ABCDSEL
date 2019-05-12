package products.CM;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.EditCertificationPage;
import pageFactory.CM.GlobalHeader;
import pageFactory.CM.LoginPage;
import pageFactory.CM.ProfilePage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class EditProfileTest 
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
	 * Edit Profile - Verify the elements on Edit Account Page
	*/ 
	@Test
	private void EditProfile_UIElementVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Edit Profile - Verify the elements on Edit Account Page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Edit Profile - Verify the elements on Edit Account Page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		ProfilePage profile = new ProfilePage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to User Profile > Edit page");
		Reporter.log("Step 2 : Login to application and navigating to User Profile > Edit page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);	
			gheader.UsernameDropdown.click();
			Thread.sleep(3000);
			gheader.Username_MyProfile.click();
			Thread.sleep(2000);	
			profile.EditInfo.click();
			Thread.sleep(2000);	
			
		System.out.println("Step 3 : Verifying elements on Edit Account page");
		Reporter.log("Step 3 : Verifying elements on Edit Account page"); 		
		
		
			//Verifying presence of Name field
			if(SeleniumFunc.IsElementPresent(profile.Edit_Name))
			{
				
				System.out.println("Success !! Name Textbox is present");
				Reporter.log("Success !! Name Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! Name Textbox is not present ");
				Reporter.log("Failure !! Name Textbox is not present "); 
				AssertFailedCount++;
			}
			
			//Verifying presence of Email field
			if(SeleniumFunc.IsElementPresent(profile.Edit_Email))
			{
				Thread.sleep(2000);	
				System.out.println("Success !! Email Textbox is present");
				Reporter.log("Success !! Email Textbox is present"); 
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! Email Textbox is not present ");
				Reporter.log("Failure !! Email Textbox is not present "); 
				AssertFailedCount++;
			}
			
			
			//Verifying presence of Password field
			if(SeleniumFunc.IsElementPresent(profile.Edit_Password))
			{
				
				System.out.println("Success !! Password Textbox is present");
				Reporter.log("Success !! Password Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! Password Textbox is not present ");
				Reporter.log("Failure !! Password Textbox is not present "); 
				AssertFailedCount++;
			}
			
			//Verifying presence of Password Confirmation field
			if(SeleniumFunc.IsElementPresent(profile.Edit_Password_Confirmation))
			{
				Thread.sleep(2000);	
				System.out.println("Success !! Password Confirmation Textbox is present");
				Reporter.log("Success !! Password Confirmation Textbox is present"); 
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! Password Confirmation Textbox is not present ");
				Reporter.log("Failure !! Password Confirmation Textbox is not present "); 
				AssertFailedCount++;
			}
			
			
			//Verifying presence of 'Active' checkbox
			if(SeleniumFunc.IsElementPresent(profile.Edit_Active))
			{
				
				System.out.println("Success !! 'Active' checkbox is present");
				Reporter.log("Success !! 'Active' checkbox is present"); 
			}
			else
			{
				System.out.println("Failure !! 'Active' checkbox is not present ");
				Reporter.log("Failure !! 'Active' checkbox is not present "); 
				AssertFailedCount++;
			}
			
			
			//Verifying presence of Role Permissions section
			if(SeleniumFunc.IsElementPresent(profile.Edit_Role_Permission_section))
			{
				
				System.out.println("Success !! Role Permissions section is present");
				Reporter.log("Success !! Role Permissions section is present"); 
			}
			else
			{
				System.out.println("Failure !! Role Permissions section is not present ");
				Reporter.log("Failure !! Role Permissions section is not present "); 
				AssertFailedCount++;
			}
			
			
			//Verifying presence of 'Submit' button
			if(SeleniumFunc.IsElementPresent(profile.Edit_Submit))
			{
				
				System.out.println("Success !! 'Submit' button is present");
				Reporter.log("Success !! 'Submit' button is present"); 
			}
			else
			{
				System.out.println("Failure !! 'Submit' button is not present ");
				Reporter.log("Failure !! 'Submit' button is not present "); 
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
	 * Edit Profile - Verify that user can edit details
	*/ 
	@Test
	private void EditProfile_Edit() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Edit Profile - Verify that user can edit details"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Edit Profile - Verify that user can edit details"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		ProfilePage profile = new ProfilePage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to User Profile > Edit page");
		Reporter.log("Step 2 : Login to application and navigating to User Profile > Edit page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);	
			
			gheader.UsernameDropdown.click();
			Thread.sleep(3000);
			gheader.Username_MyProfile.click();
			Thread.sleep(1000);
		
			profile.EditInfo.click();
			Thread.sleep(3000);
			
		System.out.println("Step 3 : On Edit page, editing Name");
		Reporter.log("Step 3 : On Edit page, editing Name"); 		
		
		   String name = "selenium" + JH.GenerateRandomNumber();
		   profile.Edit_Name.clear();
		   profile.Edit_Name.sendKeys(name);
		   Thread.sleep(1000);
		   profile.Edit_Submit.click();
		   Thread.sleep(2000);
		
		   //Verifying Name on Profile page
		   String actualtext= profile.Name.getText().trim();
		   String expectedtext= name + "\n" + "Edit Info";
		
		   Thread.sleep(2000);
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Name is updated on Profile page");
				Reporter.log("Success !! Name is updated on Profile page"); 
			}
			else
			{
				Thread.sleep(2000);
				System.out.println("Failure !!  Name is not updated on Profile page" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  Name is not updated on Profile page" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			  
			
			//Reset the details
				Thread.sleep(2000);
			   profile.EditInfo.click();
			   profile.Edit_Name.clear();
			   profile.Edit_Name.sendKeys("Sanjeet");
			   Thread.sleep(1000);
			   profile.Edit_Submit.click();
			   Thread.sleep(2000);
			
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
	 * Edit Profile - Verify that user needs to enter same password for both fields
	*/ 
	@Test
	private void EditProfile_VerifyConfirmPasswordValidation() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Edit Profile - Verify that user needs to enter same password for both fields"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Edit Profile - Verify that user needs to enter same password for both fields"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		ProfilePage profile = new ProfilePage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to User Profile > Edit page");
		Reporter.log("Step 2 : Login to application and navigating to User Profile > Edit page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);	
			gheader.UsernameDropdown.click();
			Thread.sleep(3000);
			gheader.Username_MyProfile.click();
			
			profile.EditInfo.click();
			
		System.out.println("Step 3 : On Edit page, entering diffrent passwords for both password, confirm password fields (say clariona  and clarion@123)");
		Reporter.log("Step 3 : On Edit page, entering diffrent passwords for both password, confirm password fields (say clariona  and clarion@123)");		
		
		   Thread.sleep(2000);	
			profile.Edit_Password.sendKeys("clarion");
			profile.Edit_Password_Confirmation.sendKeys("clarion@123");
			profile.Edit_Submit.click();
			
			//Verifying alert message
			String actualtext= profile.Error_AlertText.getText().trim();
			String expectedtext= "There was a problem updating this user."; 
			
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(2000);	
				System.out.println("Success !! Alert message is displayed i.e. " + actualtext);
				Reporter.log("Success !! Alert message is displayed i.e. " + actualtext); 
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! Incorrect alert message is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  Incorrect alert message is displayed" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			
			//Verifying in line validation message
			actualtext= profile.Edit_Password_Confirmation_Error.getText().trim();
			expectedtext= "Password Confirmation" + "\n" + "doesn't match Password"; 
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Inline validation message displayed i.e. " + actualtext);
				Reporter.log("Success !! Inline validation message displayed i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Incorrect inline validation message displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  Inline validation message displayed" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
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
	 * Edit Profile - Verify that user can edit his role permissions e.g. Can bypass payments
	*/ 
	@Test
	private void EditProfile_EditRolePermissions() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Edit Profile - Verify that user can edit his role permissions e.g. Can bypass payments"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Edit Profile - Verify that user can edit his role permissions e.g. Can bypass payments"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		ProfilePage profile = new ProfilePage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to User Profile > Edit page");
		Reporter.log("Step 2 : Login to application and navigating to User Profile > Edit page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);	
			gheader.UsernameDropdown.click();
			Thread.sleep(3000);
			gheader.Username_MyProfile.click();
			
			profile.EditInfo.click();
			
		System.out.println("Step 3 : On Edit page, assigning/revoking role permission for 'Can bypass payments'");
		Reporter.log("Step 3 : On Edit page, assigning/revoking role permission for 'Can bypass payments'");		
		
			
			boolean isSelected =  profile.Edit_Role_Permission_CanBypassPayments.isSelected();
			Thread.sleep(2000);	
			profile.Edit_Role_Permission_CanBypassPayments.click();
			Thread.sleep(2000);	
			profile.Edit_Submit.click();
			Thread.sleep(2000);	
			//Verifying alert message
			String actualtext= profile.Success_AlertText.getText().trim();
			String expectedtext= "User updated."; 
			Thread.sleep(2000);	
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Alert message is displayed i.e. " + actualtext);
				Reporter.log("Success !! Alert message is displayed i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Incorrect alert message is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  Incorrect alert message is displayed" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			profile.EditInfo.click();
			Thread.sleep(2000);	
			//Verifying 'Can bypass payments' checkbox
			
			boolean actual = profile.Edit_Role_Permission_CanBypassPayments.isSelected();;
			boolean expected = !isSelected; 
			Thread.sleep(2000);	
			if(actual == expected)
			{
				System.out.println("Success !! Role permission is changed successfully ");
				Reporter.log("Success !! Role permission is changed successfully" );
			}
			else
			{
				System.out.println("Failure !! Role permission is not changed ");
				Reporter.log("Failure !! Role permission is not changed ");
				
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
	 * Edit Profile - Verify that user can not leave name/email field blank
	*/ 
	@Test
	private void EditProfile_VerifyNameEmailValidation() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Edit Profile - Verify that user can not leave name/email field blank"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Edit Profile - Verify that user can not leave name/email field blank"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		ProfilePage profile = new ProfilePage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to User Profile > Edit page");
		Reporter.log("Step 2 : Login to application and navigating to User Profile > Edit page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);	
			gheader.UsernameDropdown.click();
			Thread.sleep(3000);
			gheader.Username_MyProfile.click();
			Thread.sleep(2000);	
			profile.EditInfo.click();
			Thread.sleep(2000);	
			
		System.out.println("Step 3 : On Edit page, keeping Name/Email blank and click on Submit button");
		Reporter.log("Step 3 : On Edit page, keeping Name/Email blank and click on Submit button");		
		
			
			profile.Edit_Name.clear();
			profile.Edit_Email.clear();
			profile.Edit_Submit.click();
			Thread.sleep(2000);	
			
			//Verifying alert message
			String actualtext= profile.Error_AlertText.getText().trim();
			String expectedtext= "There was a problem updating this user."; 
			Thread.sleep(2000);	
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(2000);	
				System.out.println("Success !! Alert message is displayed i.e. " + actualtext);
				Reporter.log("Success !! Alert message is displayed i.e. " + actualtext); 
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! Incorrect alert message is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  Incorrect alert message is displayed" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			
			//Verifying inline validation message for Name field
			actualtext= profile.Edit_Name_Error.getText().trim();
			expectedtext= "* Name" + "\n" + "can't be blank"; 
			Thread.sleep(2000);	
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! inline validation message displayed for Name field i.e. " + actualtext);
				Reporter.log("Success !! inline validation message displayed for Name field i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! incorrect inline validation message displayed for Name field" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  inline validation message displayed for Name field" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			
			//Verifying inline validation message for Email field
			actualtext= profile.Edit_Email_Error.getText().trim();
			expectedtext= "* Email" + "\n" + "can't be blank"; 
			Thread.sleep(2000);	
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! inline validation message displayed for Email field i.e. " + actualtext);
				Reporter.log("Success !! inline validation message displayed for Email field i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! incorrect inline validation message displayed for Email field" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  inline validation message displayed for Email field" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
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
	 * Edit Profile - Verify user can activate/deactivate the account and once user deactivated can't login again 
	*/ 
	@Test
	private void EditProfile_AccountActiveDeactivate() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Edit Profile - Verify user can activate/deactivate the account and once user deactivated can't login again"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Edit Profile - Verify user can activate/deactivate the account and once user deactivated can't login again"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		ProfilePage profile = new ProfilePage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to User Profile > Edit page");
		Reporter.log("Step 2 : Login to application and navigating to User Profile > Edit page"); 	
		
			login.EnterUsername(Constants.CM_Username_Normal);
			login.EnterPassword(Constants.CM_Password_Normal);
			login.ClickOnLogInButton();
			Thread.sleep(5000);	
			gheader.UsernameDropdown_NonAdmin.click();
			Thread.sleep(3000);
			gheader.Username_MyProfile_NonAdmin.click();
			Thread.sleep(3000);	
		
			profile.EditInfo.click();
			Thread.sleep(2000);	
			
		System.out.println("Step 3 : On Edit page, de-select 'Active' checkbox and log out");
		Reporter.log("Step 3 : On Edit page, de-select 'Active' checkbox and log out"); 		
		
			profile.Edit_Active.click();
			profile.Edit_Submit.click();
			Thread.sleep(1000);
			String usereditpage = SeleniumFunc.ToGetCurrentPageUrl();
			Thread.sleep(2000);	
			
			//Verifying alert message
			String actualtext= profile.Success_AlertText.getText().trim();
			String expectedtext= "User updated."; 
			
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(2000);	
				System.out.println("Success !! alert message is displayed i.e. " + actualtext);
				Reporter.log("Success !! alert message is displayed i.e. " + actualtext); 
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			//Verifying Name on Profile page
			actualtext= profile.Name.getText().trim();
			Thread.sleep(2000);	
			if(actualtext.contains("DISABLED"))
			{
				Thread.sleep(1000);
				System.out.println("Success !! DISABLED text is displayed with account name");
				Reporter.log("Success !! DISABLED text is displayed with account name"); 
			}
			else
			{
				System.out.println("Failure !! DISABLED text is not displayed with account name");
				Reporter.log("Failure !! DISABLED text is not displayed with account name");
				
				AssertFailedCount++;
			}
		
			//Sign out
			gheader.UsernameDropdown_NonAdmin.click();
			Thread.sleep(1000);
			gheader.Logout.click();
			Thread.sleep(2000);	
		
		System.out.println("Step 4 : Trying to login with deactivated account");
		Reporter.log("Step 4 : Trying to login with deactivated account"); 		
		
			login.EnterUsername(Constants.CM_Username_Normal);
			login.EnterPassword(Constants.CM_Password_Normal);
			login.ClickOnLogInButton();
			
			//Verifying alert message
			actualtext= gheader.Error_AlertText.getText().trim();
			expectedtext= "Authentication failed, please try again."; 
			Thread.sleep(2000);	
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(1000);
				System.out.println("Success !! alert message is displayed. User is not allowed to log in  i.e. " + actualtext);
				Reporter.log("Success !! alert message is displayed. User is not allowed to log in i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! incorrect alert message is displayed. User is not allowed to log in " + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  incorrect alert message is displayed. User is not allowed to log in" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 5 : Login wiht KalKomey Agency Admin user and activating user");
		Reporter.log("Step 5 : Login wiht KalKomey Agency Admin user and activating user");
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
			
			SeleniumFunc.ToGoToUrl(usereditpage);
			Thread.sleep(3000);
			profile.EditInfo.click();
			Thread.sleep(1000);
			profile.Edit_Active.click();
			//profile.Edit_Active.sendKeys(Keys.ENTER);
			profile.Edit_Submit.click();
			Thread.sleep(2000);	
			//Sign out
			Thread.sleep(2000);
			gheader.UsernameDropdown.click();
			Thread.sleep(1000);
			 gheader.Logout.click();
			
		System.out.println("Step 6 : Try to login with activated user");
		Reporter.log("Step 6 : Try to login with activated user");	
		
			login.EnterUsername(Constants.CM_Username_Normal);
			login.EnterPassword(Constants.CM_Password_Normal);
			login.ClickOnLogInButton();
			Thread.sleep(1000);
			
			//Verifying alert message
			actualtext= gheader.Success_AlertText.getText().trim();
			expectedtext= "Signed in!"; 
			Thread.sleep(2000);	
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! alert message is displayed. User is log in  i.e. " + actualtext);
				Reporter.log("Success !! alert message is displayed. User is log in i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! incorrect alert message is displayed. User is logged in. " + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  incorrect alert message is displayed. User is logged in" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
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
	 * View Cert update on your profile. 
	*/ 
	@Test
	private void EditProfile_ViewCertUpdateOnYourProfile() throws Exception
	{
			System.out.println("====" + "\n" +
					"Test 7 : Edit Profile - Verify View Cert update on your profile."  + "\n" +
		 			"====");
			Reporter.log("====" + "\n" +
		 			  "Test 7 : Edit Profile - Verify View Cert update on your profile."  + "\n" +
				 	  "====");	
		
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			GlobalHeader gheader = new GlobalHeader(driver);
			EditCertificationPage editcer = new EditCertificationPage(driver);
			ProfilePage profile=new ProfilePage(driver);
		
			System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
			Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
					
 			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
				
 			System.out.println("Step 2 : Verify  Login to application");
 			Reporter.log("Step 2 : Verify Login to application"); 	
				
 				login.EnterUsername(Constants.CM_Username);
 				login.EnterPassword(Constants.CM_Password);
 				login.ClickOnLogInButton();
 				Thread.sleep(5000);	
 				// navigate to the certifications page.
 				
 			System.out.println("Step 3 : Verify and navigate to the certifications page.");
		 	Reporter.log("Step 3 : Verify and navigate to the certifications page."); 		
 				
		 	 	editcer.ClickOncertificationManager();
 			    Thread.sleep(1000);
 				if(SeleniumFunc.IsElementPresent("css","a[href='/certifications']"))
				{
 					Thread.sleep(2000);	
					System.out.println("Success !! User has navigated to the certifications page.");
					Reporter.log("Success !! User has navigated to the certifications page."); 
				}
				else
				{
					Thread.sleep(2000);	
					System.out.println("Failure !! User is not navigated to the certifications page.");
					Reporter.log("Failure !! User is not navigated to the certifications page."); 
					AssertFailedCount++;
				}
 			System.out.println("Step 4 : Enter the valid data in mandatory field for Search Certifications functionality and click on Search button.");
 			Reporter.log("Step 4 : Enter the valid data in mandatory field for Search Certifications functionality and click on Search button."); 
 		 		
 						Thread.sleep(1000);   
				    	editcer.EnterStudentInfoforSErch("Automation ");
				    	editcer.ClickOnSearch();
				    	Thread.sleep(3000);   
				    	
 				
 			System.out.println("Step 5 : Click on the first certification record that shows up");
 			Reporter.log("Step 5 : Click on the first certification record that shows up"); 
 				editcer.ClickOnStudentNametab();
 				Thread.sleep(2000);
 					     
 			System.out.println("Step 6 : Verify user has navigated to an individual certification record in KPS.");
 			Reporter.log("Step 6 : Verify user has navigated to an individual certification record in KPS."); 
 						
	     		//Verifying presence of Edit Tab
	     		if(SeleniumFunc.IsElementPresent(editcer.EditTab))
	     		{
	     			Thread.sleep(2000);	
	     			System.out.println("Success !! user has navigated to an individual certification record in KPS.");
	     			Reporter.log("Success !! user has navigated to an individual certification record in KPS."); 
	     		}
	     		else
	     		{
	     			Thread.sleep(2000);	
	     			System.out.println("Failure !! user is not navigated to an individual certification record in KPS.");
	     			Reporter.log("Failure !! user is not navigated to an individual certification record in KPS."); 
	     			AssertFailedCount++;
	     		}			
 					     	
	     	System.out.println("Step 7 : Click on Edit Link");
	     	Reporter.log("Step 7 : Click on Edit Link");
 							   
	     		editcer.ClickOnEdittab();
	     		System.out.println("Step 8 : Verify this will take you to the edit form for this certification record.");
		     	Reporter.log("Step 8 : Verify this will take you to the edit form for this certification record. "); 	
		     	
	     		//Verifying presence of Student name page
	     		if(SeleniumFunc.IsElementPresent(editcer.StudentInfoPage))
	     		{
	     			Thread.sleep(2000);	
	     			System.out.println("Success !! user has succesfully navigated to edit form.");
	     			Reporter.log("Success !! user has succesfully navigated to edit form."); 
	     		}
	     		else
	     		{
	     			Thread.sleep(2000);	
	     			System.out.println("Failure !! user is not navigated to edit form.");
	     			Reporter.log("Failure !! user is not navigated to edit form."); 
	     			AssertFailedCount++;
	     		}		
	     							 		
 			System.out.println("Step 9 :  Change the information of any address field to the desired input.");
 			Reporter.log("Step 9 :  Change the information of any address field to the desired input.");
	     		
 				Thread.sleep(1000);	
 				 String address1="14086 Proton Road";
 				 //String address1="Test"+JH.GenerateRandomNumber();;
 		       profile.EnterStreetAddress(address1);
	     	   Thread.sleep(1000);	
	     	   
	     	   String address2="14087 Proton Road";
	     	   //String address2="Adress"+JH.GenerateRandomNumber();;
	     	   profile.Entercaddress2(address2);	
	     		Thread.sleep(2000);	
				 	   	        
	     	System.out.println("Step 10 :  Click on Continue button.");
			Reporter.log("Step 10:  Click on Continue button.");
				 			      
	     		editcer.Getsubmitt();
	     		Thread.sleep(2000);	
	     		
	     		SeleniumFunc.WebDriverWaitMethod("css", "#physical_address_check div button.btn.btn-default");	
	     		editcer.ClickOnuseaddress1();     
	     		Thread.sleep(2000);	
	     		editcer.ClickOnuseaddress2();
	     		Thread.sleep(2000);	
	     		editcer.ClickOnSaveBtn();
	     		Thread.sleep(2000);	
	     		
	     		
	     	System.out.println("Step 11 : Verifying changes has updated successfully or not");
	     	Reporter.log("Step 11 : Verifying changes has updated successfully or not"); 
	     		
	     		
	     		String ActualUserName=SeleniumFunc.GetElementText("css", "div.alert.alert-success").trim();
	     		System.out.println(ActualUserName);
	     		Thread.sleep(2000);	
	     		if(ActualUserName.equals("Certification was successfully updated."))
	     		{
	     			Thread.sleep(2000);	
	     			System.out.println("Success !!  changes has updated successfully");
	     			Reporter.log("Success !! changes has updated successfully"); 
	     		}
	     		else
	     		{
	     			Thread.sleep(2000);	
	     			System.out.println("Failure !!  changes is not updated successfully");
	     			Reporter.log("Failure !! changes is not updated successfully"); 
	     			AssertFailedCount++;
	     		}   
	     		
	     		System.out.println("Step 12 : Navigate to User Profile > Edit page");
	    		Reporter.log("Step 12 : Navigate to User Profile > Edit page"); 	
	    		
	    			gheader.UsernameDropdown.click();
	    			Thread.sleep(1000);
	    			gheader.Username_MyProfile.click();
	    			
	    			System.out.println("Step 13 : Verify user has navigated back to profile page.");
		    		Reporter.log("Step 13 : Verify user has navigated back to profile page."); 	
	     		
		    		Thread.sleep(2000);
		    		String ActivityInTheLastMonth=SeleniumFunc.GetElementText("css", "#activity-stream>header").trim();
		     		System.out.println(ActivityInTheLastMonth);
		     		
		     		if(ActivityInTheLastMonth.equals("Activity in the Last Month") )
		     		{
		     			System.out.println("Success !!  user has navigated back to profile page");
		     			Reporter.log("Success !! user has navigated back to profile page"); 
		     		}
		     		else
		     		{
		     			System.out.println("Failure !! user is not navigated back to profile page");
		     			Reporter.log("Failure !! user is not navigated back to profile page"); 
		     			AssertFailedCount++;
		     		}   
		     		
		     		System.out.println("Step 14 : Verify the changes that you made to the Certification is same as profile page, located on the right, under the words 'Activitiy in the Last Month'");
		    		Reporter.log("Step 14 : Verify the changes that you made to the Certification is same as profile page, located on the right, under the words 'Activitiy in the Last Month'"); 	
		    		Thread.sleep(4000);
		    		editcer.ClickOnDetailsLink();
		     		Thread.sleep(1000);
		     		String MailingAddress1=SeleniumFunc.GetElementText("css", "#details-0 dl dd:nth-of-type(1) span:nth-of-type(2)").trim();
		     		System.out.println(MailingAddress1);
		     		
		     		String MailingAddress2=SeleniumFunc.GetElementText("css", "#details-0 dl dd:nth-of-type(2) span:nth-of-type(2)").trim();
		     		System.out.println(MailingAddress2);
		     		
		     		if(MailingAddress1.equals(address1) &&  MailingAddress2.equals(address2))
		     		{
		     			System.out.println("Success !!  The changes that you made to the Certification is same as profile page.");
		     			Reporter.log("Success !!  The changes that you made to the Certification is same as profile page."); 
		     		}
		     		else
		     		{
		     			System.out.println("Failure !! The changes that you made to the Certification is not same as profile page.");
		     			Reporter.log("Failure !! The changes that you made to the Certification is not same as profile page."); 
		     			AssertFailedCount++;
		     		}   
		     		Thread.sleep(1000);
	     		
	     		
		 		 		
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