package courses.BowhunterEd;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.ContentsPage;
import pageFactory.Courses.LoginPage;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.ProfilePage;
import pageFactory.Courses.RegistrationPage;
import pageFactory.Courses.ResetPasswordPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class ProfileTest 
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
	 * Verify UI elements on Profile page
	*/ 
	@Test
	private void Profile_UIElementVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify UI of Profile page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify UI of Profile page"  + "\n" +
				 	  "====");	
	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
	
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "profilehunter" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "profilehunter" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
		
	
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "5", "1990", emailaddress, emailaddress);
			Thread.sleep(8000);
			
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}		
			
		System.out.println("Step 2 : Navigate to Profile page : " + Constants.ProfilePage_BowHunter);
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ProfilePage_BowHunter); 
			
			SeleniumFunc.ToGoToUrl(Constants.ProfilePage_BowHunter);
			Thread.sleep(4000);

	
		System.out.println("Step 3 : Verifying whether page title is correctly displayed on not");
		Reporter.log("Step 3 : Verifying whether page title is correctly displayed on not"); 
			
			if(SeleniumFunc.GetElementText("css", "div.page-header h1").equals("Your Profile"))
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
			
		System.out.println("Step 4 : Verifying presence sections : 'Your Bowhunter Ed Account' and 'Account Information' ");
		Reporter.log("Step 4 : Verifying presence sections : 'Your Bowhunter Ed Account' and 'Account Information' "); 	
			
			if(SeleniumFunc.GetElementText("css", "div#main section:nth-of-type(1) h2").equals("Your Bowhunter Ed Account"))
			{
				
				System.out.println("Success !! 'Your Bowhunter Ed Account' section is present");
				Reporter.log("Success !! 'Your Bowhunter Ed Account' section is present"); 
			}
			else
			{
				System.out.println("Failure !! 'Your Bowhunter Ed Account' section is NOT present");
				Reporter.log("Failure !! 'Your Bowhunter Ed Account' section is NOT present"); 
				AssertFailedCount++;
			}
			
			
			if(SeleniumFunc.GetElementText("css", "div#main section:nth-of-type(2) h2").equals("Account Information"))
			{
				
				System.out.println("Success !! 'Account Information' section is present");
				Reporter.log("Success !! 'Account Information' section is present"); 
			}
			else
			{
				System.out.println("Failure !! 'Account Information' section is NOT present");
				Reporter.log("Failure !! 'Account Information' section is NOT present"); 
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
	 * Verify that 'Your Bowhunter Ed Account' section has correct details i.e. Student Name, DOB, Username, Email Address
	*/ 
	@Test
	private void Profile_YourHunterEdAccountSection() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify that 'Your Bowhunter Ed Account' section has correct details i.e. Student Name, DOB, Username, Email Address"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify that 'Your Bowhunter Ed Account' section has correct details i.e. Student Name, DOB, Username, Email Address"  + "\n" +
				 	  "====");	
	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
	
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "profile" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "profile" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
			
		System.out.println("Step 2 : Navigate to Profile page : " + Constants.ProfilePage_BowHunter);
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ProfilePage_BowHunter); 
			
			SeleniumFunc.ToGoToUrl(Constants.ProfilePage_BowHunter);
			Thread.sleep(4000);

	
		System.out.println("Step 3 : Verifying 'Your Bowhunter Ed Account' section has correct details i.e. Student Name, DOB, Username, Email Address");
		Reporter.log("Step 3 : Verifying 'Your Bowhunter Ed Account' section has correct details i.e. Student Name, DOB, Username, Email Address"); 
			
			Thread.sleep(10000);
			if(SeleniumFunc.GetElementText("css", "div#main section:nth-of-type(1) h3").trim().equals("Test B Ke-testing Jr."))
			{
				System.out.println("Success !! Student Name is correct");
				Reporter.log("Success !! Student Name is correct"); 
			}
			else
			{
				System.out.println("Failure !! Student Name is incorrect ");
				Reporter.log("Failure !!  Student Name is incorrect "); 
				AssertFailedCount++;
			}
			
			if(SeleniumFunc.GetElementText("css", "div#main section:nth-of-type(1) p").trim().equals("January 02, 1990"))
			{
				System.out.println("Success !! DOB is correct");
				Reporter.log("Success !! DOB is correct"); 
			}
			else
			{
				System.out.println("Failure !! DOB is incorrect ");
				Reporter.log("Failure !!  DOB is incorrect "); 
				AssertFailedCount++;
			}
		
			
			if(SeleniumFunc.GetElementText("css", "div#main section:nth-of-type(1) div:nth-of-type(3) p").trim().equals(username))
			{
				System.out.println("Success !! Username is correct");
				Reporter.log("Success !! Username is correct"); 
			}
			else
			{
				System.out.println("Failure !! Username is incorrect ");
				Reporter.log("Failure !!  Username is incorrect "); 
				AssertFailedCount++;
			}
			
			
			if(SeleniumFunc.GetAttributeValue("id", "StudentModel_email", "value").trim().equals(emailaddress))
			{
				System.out.println("Success !! Email is correct");
				Reporter.log("Success !! Email is correct"); 
			}
			else
			{
				System.out.println("Failure !! Email is incorrect ");
				Reporter.log("Failure !!  Email is incorrect "); 
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
	 * Verify that user can Reset Password successfully
	*/ 
	@Test
	private void Profile_ResetPassword() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify that user can Reset Password successfully"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify that user can Reset Password successfully"  + "\n" +
				 	  "====");	
	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ResetPasswordPage resetpassword = new ResetPasswordPage(driver);
		ProfilePage profile = new ProfilePage(driver);
		LoginPage login = new LoginPage(driver);
	
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "profile" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "profile" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
			
		System.out.println("Step 2 : Navigate to Profile page : " + Constants.ProfilePage_BowHunter);
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ProfilePage_BowHunter); 
			
			SeleniumFunc.ToGoToUrl(Constants.ProfilePage_BowHunter);	
			Thread.sleep(4000);

		System.out.println("Step 3 : Clicking 'Reset Password' button");
		Reporter.log("Step 3 : Clicking 'Reset Password' button");
			
			profile.ClickOnResetPasswordButton();
			Thread.sleep(4000);

		System.out.println("Step 4 : Entering values in New Password / Re-enter New Password fields , clicking on Submit button");
		Reporter.log("Step 4 : Entering values in New Password / Re-enter New Password fields, clicking on Submit button");
		
			String newpassword ="me@12345";
			resetpassword.EnterNewPassword(newpassword);
			resetpassword.EnterReEnterNewPassword(newpassword);
			resetpassword.ClickOnSubmitButton();
			Thread.sleep(4000);

		
		System.out.println("Step 5 : Verifying user is navigated to '/register/edit' page and proper confirmation message is dispalyed on that page");
		Reporter.log("Step 5 : Verifying user is navigated to '/register/edit' page and proper confirmation message is dispalyed on that page");
				
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
			Thread.sleep(4000);

			login.EnterPassword(newpassword);
			login.EnterUsername(username);
			login.ClickOnLogInButton();
			Thread.sleep(4000);

			
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
	
	
	/* Test 4: 
	 * Verify user edit profile information successfully - editing Gender, , Email Address, Mailing Address fields
	*/ 
	@Test
	private void Profile_Edit1() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify user edit profile information successfully - editing Gender, , Email Address, Mailing Address fields"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify user edit profile information successfully - editing Gender, , Email Address, Mailing Address fields"  + "\n" +
				 	  "====");	
	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ProfilePage profile = new ProfilePage(driver);
	

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "profile" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "profile" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
			
		System.out.println("Step 2 : Navigate to Profile page : " + Constants.ProfilePage_BowHunter);
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ProfilePage_BowHunter); 
			
			SeleniumFunc.ToGoToUrl(Constants.ProfilePage_BowHunter);
			Thread.sleep(4000);
	
		System.out.println("Step 3 : Editing all fields (i.e. Gender, , Email Address, Mailing Address) and clicking on 'Update Profile' button");
		Reporter.log("Step 3 : Editing all fields (i.e. Gender, , Email Address, Mailing Address) and clicking on 'Update Profile' button");
			
			String gender= "Male";
			String email = "temp@testing.com";
			String addressline1 = "14086 Proton Rd";
			String addressline2 = "";
			String city= "Dallas";
			String state="Texas";
			String zip="75244";
			String country="United States of America";
			String phonenumber="234-567-8910";
			
			profile.SelectGender(gender);
			profile.SelectEthnicity("Asian");
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();
			Thread.sleep(4000);

		System.out.println("Step 5 : Navigating to Profile page and verifying edited details");
		Reporter.log("Step 5 : Navigating to Profile page and verifying edited details");
		
			SeleniumFunc.ToGoToUrl(Constants.ProfilePage_BowHunter);
			Thread.sleep(4000);

			//Verifying Gender
			if(SeleniumFunc.GetSelectedValueFromDropdownList("id", "StudentModel_gender").equals(gender))
			{
				
				System.out.println("Success !! Gender is correct");
				Reporter.log("Success !! Gender is correct"); 
			}
			else
			{
				System.out.println("Failure !! Gender is incorrect");
				Reporter.log("Failure !! Gender is incorrect"); 
				AssertFailedCount++;
			}
			
			
			//Verifying Email Address
			if(SeleniumFunc.GetAttributeValue("id", "StudentModel_email", "value").equals(email))
			{
				
				System.out.println("Success !! Email is correct");
				Reporter.log("Success !! Email is correct"); 
			}
			else
			{
				System.out.println("Failure !! Email is incorrect");
				Reporter.log("Failure !! Email is incorrect"); 
				AssertFailedCount++;
			}
			
			
			//Verifying Mailing Address - Address Line 1
			if(SeleniumFunc.GetAttributeValue("id", "RegistrationModel_s_address1", "value").equals(addressline1))
			{
				
				System.out.println("Success !! Address Line 1 is correct");
				Reporter.log("Success !! Address Line 1 is correct"); 
			}
			else
			{
				System.out.println("Failure !! Address Line 1 is incorrect");
				Reporter.log("Failure !! Address Line 1 is incorrect"); 
				AssertFailedCount++;
			}
				
			
			//Verifying Mailing Address - Address Line 2
			if(SeleniumFunc.GetAttributeValue("id", "RegistrationModel_s_address2", "value").equals(addressline2))
			{
				
				System.out.println("Success !! Address Line 2 is correct");
				Reporter.log("Success !! Address Line 2 is correct"); 
			}
			else
			{
				System.out.println("Failure !! Address Line 2 is incorrect");
				Reporter.log("Failure !! Address Line 2 is incorrect"); 
				AssertFailedCount++;
			}
			
			
			//Verifying Mailing Address - City
			if(SeleniumFunc.GetAttributeValue("id", "RegistrationModel_s_city", "value").equals(city))
			{
				
				System.out.println("Success !! City is correct");
				Reporter.log("Success !! City is correct"); 
			}
			else
			{
				System.out.println("Failure !! City is incorrect");
				Reporter.log("Failure !! City is incorrect"); 
				AssertFailedCount++;
			}
			
			
			//Verifying Mailing Address - State/District
			if(SeleniumFunc.GetSelectedValueFromDropdownList("id", "RegistrationModel_s_state_cd_dropdown").equals(state))
			{
				
				System.out.println("Success !! State is correct");
				Reporter.log("Success !! State is correct"); 
			}
			else
			{
				System.out.println("Failure !! State is incorrect");
				Reporter.log("Failure !! State is incorrect"); 
				AssertFailedCount++;
			}
			
			
			//Verifying Mailing Address - Zip
			if(SeleniumFunc.GetAttributeValue("id", "RegistrationModel_s_zip", "value").equals(zip))
			{
				
				System.out.println("Success !! Zip is correct");
				Reporter.log("Success !! Zip is correct"); 
			}
			else
			{
				System.out.println("Failure !! Zip is incorrect");
				Reporter.log("Failure !! Zip is incorrect"); 
				AssertFailedCount++;
			}
			
			
			//Verifying Mailing Address - County
			if(SeleniumFunc.GetSelectedValueFromDropdownList("id", "RegistrationModel_s_country_id").equals(country))
			{
				
				System.out.println("Success !! Country is correct");
				Reporter.log("Success !! Country is correct"); 
			}
			else
			{
				System.out.println("Failure !! Country is incorrect");
				Reporter.log("Failure !! Country is incorrect"); 
				AssertFailedCount++;
			}
				
			
			//Verifying Mailing Address - PhoneNumber
			if(SeleniumFunc.GetAttributeValue("id", "RegistrationModel_s_phone", "value").equals(phonenumber))
			{
				
				System.out.println("Success !! Phone Number is correct");
				Reporter.log("Success !! Phone Number is correct"); 
			}
			else
			{
				System.out.println("Failure !! Phone Number is incorrect");
				Reporter.log("Failure !! Phone Number is incorrect"); 
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
	 * Verify user edit profile information successfully - editing Physial Address fields
	*/ 
	@Test
	private void Profile_Edit2() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify user edit profile information successfully - editing Physial Address fields"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5: Verify user edit profile information successfully - editing Physial Address fields"  + "\n" +
				 	  "====");	
	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ProfilePage profile = new ProfilePage(driver);
	

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "profile" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "profile" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
			
		System.out.println("Step 2 : Navigate to Profile page : " + Constants.ProfilePage_BowHunter);
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ProfilePage_BowHunter); 
			
			SeleniumFunc.ToGoToUrl(Constants.ProfilePage_BowHunter);
			Thread.sleep(4000);

	
		System.out.println("Step 3 : Editing all fields (i.e. Gender,Email Address, Mailing Address) and clicking on 'Update Profile' button");
		Reporter.log("Step 3 : Editing all fields (i.e. Gender, Email Address, Mailing Address) and clicking on 'Update Profile' button");
			
			String gender= "Male";
			String email = "temp@testing.com";
			String addressline1 = "14086 Proton Rd";
			String addressline2 = "";
			String city= "Dallas";
			String state="Texas";
			String zip="75244";
			String country="United States of America";
			String phonenumber="234-567-8910";
			
			profile.SelectGender(gender);
			profile.SelectEthnicity("Asian");
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			
		System.out.println("Step 4 : Editing Physical address and clicking on 'Update Profile' button");
		Reporter.log("Step 4 : Editing Physical address and clicking on 'Update Profile' button");
					
			String paddressline1 = "pp770 Broadway New York";
			String paddressline2 = "ppSthapatya";
			String pcity= "ppNew York";
			String pstate="Alaska";
			String pzip="11111";
			String pcountry="United States of America";
			String pphonenumber="234-567-1111";
			
			profile.ClickOnPhysicalAddressCheckbox();
			Thread.sleep(4000);
			profile.EnterPhysicalAddress(paddressline1, paddressline2, pcity, pstate, pzip, pcountry, pphonenumber);
			profile.ClickOnUpdateProfileButton();
			Thread.sleep(4000);

		System.out.println("Step 5 : Navigating to Profile page and verifying edited details");
		Reporter.log("Step 5 : Navigating to Profile page and verifying edited details");
		
			SeleniumFunc.ToGoToUrl(Constants.ProfilePage_BowHunter);
			Thread.sleep(4000);

			//Verifying Gender
			if(SeleniumFunc.GetSelectedValueFromDropdownList("id", "StudentModel_gender").equals(gender))
			{
				
				System.out.println("Success !! Gender is correct");
				Reporter.log("Success !! Gender is correct"); 
			}
			else
			{
				System.out.println("Failure !! Gender is incorrect");
				Reporter.log("Failure !! Gender is incorrect"); 
				AssertFailedCount++;
			}
				
			//Verifying Email Address
			if(SeleniumFunc.GetAttributeValue("id", "StudentModel_email", "value").equals(email))
			{
				
				System.out.println("Success !! Email is correct");
				Reporter.log("Success !! Email is correct"); 
			}
			else
			{
				System.out.println("Failure !! Email is incorrect");
				Reporter.log("Failure !! Email is incorrect"); 
				AssertFailedCount++;
			}
			
			
			//Verifying Mailing Address - Address Line 1
			if(SeleniumFunc.GetAttributeValue("id", "RegistrationModel_s_address1", "value").equals(addressline1))
			{
				
				System.out.println("Success !! Mailing Address Address Line 1 is correct");
				Reporter.log("Success !! Mailing Address Address Line 1 is correct"); 
			}
			else
			{
				System.out.println("Failure !! Mailing Address Address Line 1 is incorrect");
				Reporter.log("Failure !! Mailing Address Address Line 1 is incorrect"); 
				AssertFailedCount++;
			}
				
			
			//Verifying Mailing Address - Address Line 2
			if(SeleniumFunc.GetAttributeValue("id", "RegistrationModel_s_address2", "value").equals(addressline2))
			{
				
				System.out.println("Success !! Mailing Address Address Line 2 is correct");
				Reporter.log("Success !! Mailing Address Address Line 2 is correct"); 
			}
			else
			{
				System.out.println("Failure !! Mailing Address Address Line 2 is incorrect");
				Reporter.log("Failure !! Mailing Address Address Line 2 is incorrect"); 
				AssertFailedCount++;
			}
			
			
			//Verifying Mailing Address - City
			if(SeleniumFunc.GetAttributeValue("id", "RegistrationModel_s_city", "value").equals(city))
			{
				
				System.out.println("Success !! Mailing Address City is correct");
				Reporter.log("Success !! Mailing Address City is correct"); 
			}
			else
			{
				System.out.println("Failure !! Mailing Address City is incorrect");
				Reporter.log("Failure !! Mailing Address City is incorrect"); 
				AssertFailedCount++;
			}
			
			
			//Verifying Mailing Address - State/District
			if(SeleniumFunc.GetSelectedValueFromDropdownList("id", "RegistrationModel_s_state_cd_dropdown").equals(state))
			{
				
				System.out.println("Success !! Mailing Address State is correct");
				Reporter.log("Success !! Mailing Address State is correct"); 
			}
			else
			{
				System.out.println("Failure !! Mailing Address State is incorrect");
				Reporter.log("Failure !! Mailing Address State is incorrect"); 
				AssertFailedCount++;
			}
			
			
			//Verifying Mailing Address - Zip
			if(SeleniumFunc.GetAttributeValue("id", "RegistrationModel_s_zip", "value").equals(zip))
			{
				
				System.out.println("Success !! Mailing Address Zip is correct");
				Reporter.log("Success !! Mailing Address Zip is correct"); 
			}
			else
			{
				System.out.println("Failure !! Mailing Address Zip is incorrect");
				Reporter.log("Failure !! Mailing Address Zip is incorrect"); 
				AssertFailedCount++;
			}
			
			
			//Verifying Mailing Address - County
			if(SeleniumFunc.GetSelectedValueFromDropdownList("id", "RegistrationModel_s_country_id").equals(country))
			{
				
				System.out.println("Success !! Mailing Address Country is correct");
				Reporter.log("Success !! Mailing Address Country is correct"); 
			}
			else
			{
				System.out.println("Failure !! Mailing Address Country is incorrect");
				Reporter.log("Failure !! Mailing Address Country is incorrect"); 
				AssertFailedCount++;
			}
				
			
			//Verifying Mailing Address - PhoneNumber
			if(SeleniumFunc.GetAttributeValue("id", "RegistrationModel_s_phone", "value").equals(phonenumber))
			{
				
				System.out.println("Success !! Mailing Address Phone Number is correct");
				Reporter.log("Success !! Mailing Address Phone Number is correct"); 
			}
			else
			{
				System.out.println("Failure !! Mailing Address Phone Number is incorrect");
				Reporter.log("Failure !! Mailing Address Phone Number is incorrect"); 
				AssertFailedCount++;
			}
				
		
			
			//Verifying Physical Address - Address Line 1
			if(SeleniumFunc.GetAttributeValue("id", "RegistrationModel_p_address1", "value").equals(paddressline1))
			{
				
				System.out.println("Success !! Physical Address Address Line 1 is correct");
				Reporter.log("Success !! Physical Address Address Line 1 is correct"); 
			}
			else
			{
				System.out.println("Failure !! Physical Address Address Line 1 is incorrect");
				Reporter.log("Failure !! Physical Address Address Line 1 is incorrect"); 
				AssertFailedCount++;
			}
				
			
			//Verifying Physical Address - Address Line 2
			if(SeleniumFunc.GetAttributeValue("id", "RegistrationModel_p_address2", "value").equals(paddressline2))
			{
				
				System.out.println("Success !! Physical Address Address Line 2 is correct");
				Reporter.log("Success !! Physical Address Address Line 2 is correct"); 
			}
			else
			{
				System.out.println("Failure !! Physical Address Address Line 2 is incorrect");
				Reporter.log("Failure !! Physical Address Address Line 2 is incorrect"); 
				AssertFailedCount++;
			}
			
			
			//Verifying Physical Address - City
			if(SeleniumFunc.GetAttributeValue("id", "RegistrationModel_p_city", "value").equals(pcity))
			{
				
				System.out.println("Success !! Physical Address City is correct");
				Reporter.log("Success !! Physical Address City is correct"); 
			}
			else
			{
				System.out.println("Failure !! Physical Address City is incorrect");
				Reporter.log("Failure !! Physical Address City is incorrect"); 
				AssertFailedCount++;
			}
			
			
			//Verifying Physical Address - State/District
			if(SeleniumFunc.GetSelectedValueFromDropdownList("id", "RegistrationModel_p_state_cd_dropdown").equals(pstate))
			{
				
				System.out.println("Success !! Physical Address State is correct");
				Reporter.log("Success !! Physical Address State is correct"); 
			}
			else
			{
				System.out.println("Failure !! Physical Address State is incorrect");
				Reporter.log("Failure !! Physical Address State is incorrect"); 
				AssertFailedCount++;
			}
			
			
			//Verifying Physical Address - Zip
			if(SeleniumFunc.GetAttributeValue("id", "RegistrationModel_p_zip", "value").equals(pzip))
			{
				
				System.out.println("Success !! Physical Address Zip is correct");
				Reporter.log("Success !! Physical Address Zip is correct"); 
			}
			else
			{
				System.out.println("Failure !! Physical Address Zip is incorrect");
				Reporter.log("Failure !! Physical Address Zip is incorrect"); 
				AssertFailedCount++;
			}
			
			
			//Verifying Physical Address - County
			if(SeleniumFunc.GetSelectedValueFromDropdownList("id", "RegistrationModel_p_country_id").equals(pcountry))
			{
				
				System.out.println("Success !! Physical Address Country is correct");
				Reporter.log("Success !! Physical Address Country is correct"); 
			}
			else
			{
				System.out.println("Failure !! Physical Address Country is incorrect");
				Reporter.log("Failure !! Physical Address Country is incorrect"); 
				AssertFailedCount++;
			}
				
			
			//Verifying Physical Address - PhoneNumber
			if(SeleniumFunc.GetAttributeValue("id", "RegistrationModel_p_phone", "value").equals(pphonenumber))
			{
				
				System.out.println("Success !! Physical Address Phone Number is correct");
				Reporter.log("Success !! Physical Address Phone Number is correct"); 
			}
			else
			{
				System.out.println("Failure !! Physical Address Phone Number is incorrect");
				Reporter.log("Failure !! Physical Address Phone Number is incorrect"); 
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
	 * Verify default values for dropdowns fields Gender, County, State 
	*/ 
	@Test
	private void Profile_DefaultValues() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify default values for dropdowns fields Gender, County, State "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6: Verify default values for dropdowns fields Gender, County, State "  + "\n" +
				 	  "====");	
	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
	
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "profile" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "profile" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
			
		System.out.println("Step 2 : Navigate to Profile page : " + Constants.ProfilePage_BowHunter);
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ProfilePage_BowHunter); 
			
			SeleniumFunc.ToGoToUrl(Constants.ProfilePage_BowHunter);
			Thread.sleep(4000);

	
		System.out.println("Step 3 : Verifying default values for dropdowns fields Gender, County, State  ");
		Reporter.log("Step 3 : Verifying default values for dropdowns fields Gender, County, State  ");
			
			
			//Verifying Gender
			if(SeleniumFunc.GetSelectedValueFromDropdownList("id", "StudentModel_gender").equals("Please Select"))
			{
				
				System.out.println("Success !! Gender Default value is correct");
				Reporter.log("Success !! Gender Default value is correct"); 
			}
			else
			{
				System.out.println("Failure !! Gender Default value is incorrect");
				Reporter.log("Failure !! Gender Default value is incorrect"); 
				AssertFailedCount++;
			}
			
			
			//Verifying State
			if(SeleniumFunc.GetSelectedValueFromDropdownList("id", "RegistrationModel_s_state_cd_dropdown").equals(""))
			{
				
				System.out.println("Success !! State Default value is correct");
				Reporter.log("Success !! State Default value is correct"); 
			}
			else
			{
				System.out.println("Failure !! State Default value is incorrect");
				Reporter.log("Failure !! State Default value is incorrect"); 
				AssertFailedCount++;
			}
			
			
			//Verifying Country
			if(SeleniumFunc.GetSelectedValueFromDropdownList("id", "RegistrationModel_s_country_id").equals("United States of America"))
			{
				
				System.out.println("Success !! Country Default value is correct");
				Reporter.log("Success !! Country Default value is correct"); 
			}
			else
			{
				System.out.println("Failure !! Country Default value is incorrect");
				Reporter.log("Failure !! Country Default value is incorrect"); 
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
	 * Verify validation message is displayed if user enters invalid values 'Email Address' field
	*/ 
	@Test
	private void Profile_ValidationMessageForInvalidEmail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Verify validation message is displayed if user enters invalid values 'Email Address' field"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7: Verify validation message is displayed if user enters invalid values 'Email Address' field"  + "\n" +
				 	  "====");	
	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ProfilePage profile = new ProfilePage(driver);
	
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "profile" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "profile" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
			
		System.out.println("Step 2 : Navigate to Profile page : " + Constants.ProfilePage_BowHunter);
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ProfilePage_BowHunter); 
			
			SeleniumFunc.ToGoToUrl(Constants.ProfilePage_BowHunter);
			Thread.sleep(4000);

	
		System.out.println("Step 3 : Entering invalid values to Email field");
		Reporter.log("Entering invalid values to Email field");
			
			profile.EnterEmailAddress("invalidemail@");
			profile.ClickOnUpdateProfileButton();
			Thread.sleep(4000);

		System.out.println("Step 4 : Verifying whether correct validaiton message is displayed or not");
		Reporter.log("Step 4 : Verifying whether correct validaiton message is displayed or not");
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#contact-info div p");
			//System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage= 
					"This email address is invalid. Please use the format: name@company.com" ; 
			
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
	
	
	
	/* Test 8: 
	 * Verify validation message is displayed if user enters invalid values 'Zip' field
	*/ 
	@Test
	private void Profile_ValidationMessageForInvalidZip() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Verify validation message is displayed if user enters invalid values 'Zip' field"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8: Verify validation message is displayed if user enters invalid values 'Zip' field"  + "\n" +
				 	  "====");	
	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ProfilePage profile = new ProfilePage(driver);
	
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "profile" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "profile" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
			
		System.out.println("Step 2 : Navigate to Profile page : " + Constants.ProfilePage_BowHunter);
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ProfilePage_BowHunter); 
			
			SeleniumFunc.ToGoToUrl(Constants.ProfilePage_BowHunter);
			Thread.sleep(4000);

	
		System.out.println("Step 3 : Entering invalid values to Zip field");
		Reporter.log("Entering invalid values to Zip field");
			
			profile.EnterMailingAddress("addressline1", "addressline2", "city", "Alaska", "111122", "United States of America", "234-567-8910");
			profile.ClickOnUpdateProfileButton();
			Thread.sleep(4000);

	
		System.out.println("Step 4 : Verifying whether correct validaiton message is displayed or not");
		Reporter.log("Step 4 : Verifying whether correct validaiton message is displayed or not");
			
		
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#mailing-address div:nth-of-type(5) p");
			System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage= 
					"This zip code is invalid. Please use the format: 12435 or 12345-6789" ; 
			
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
	
	
	
	/* Test 9: 
	 * Verify validation message is displayed if user enters invalid values 'Phone Number' field
	*/ 
	@Test
	private void Profile_ValidationMessageForInvalidPhone() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Verify validation message is displayed if user enters invalid values 'Phone Number' field"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9: Verify validation message is displayed if user enters invalid values 'Phone Number' field"  + "\n" +
				 	  "====");	
	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ProfilePage profile = new ProfilePage(driver);
	
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "profile" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "profile" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
			
		System.out.println("Step 2 : Navigate to Profile page : " + Constants.ProfilePage_BowHunter);
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ProfilePage_BowHunter); 
			
			SeleniumFunc.ToGoToUrl(Constants.ProfilePage_BowHunter);
			Thread.sleep(4000);

	
		System.out.println("Step 3 : Entering invalid values to Phone Number field");
		Reporter.log("Entering invalid values to Phone Number field");
			
			profile.SelectEthnicity("Asian");
			profile.EnterMailingAddress("addressline1", "addressline2", "city", "Alaska", "11111", "United States of America", "234-567-891011");
			profile.ClickOnUpdateProfileButton();
			Thread.sleep(4000);

	
		System.out.println("Step 4 : Verifying whether correct validaiton message is displayed or not");
		Reporter.log("Step 4 : Verifying whether correct validaiton message is displayed or not");
			
		
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#mailing-address div:nth-of-type(7) p");
			System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage= 
					"This phone number is invalid. Please use the format 234-567-8910, 2345678910 or 234 567 8910. Country code is not required." ; 
			
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
	
	
	
	/* Test 10: 
	 * Verify validation message are displayed for mandatory fields
	*/ 
	@Test
	private void Profile_ValidationMessageForMandatoryFields() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : Verify validation message are displayed for mandatory fields"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10: Verify validation message are displayed for mandatory fields"  + "\n" +
				 	  "====");	
	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ProfilePage profile = new ProfilePage(driver);
	
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "profile" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "profile" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
			
		System.out.println("Step 2 : Navigate to Profile page : " + Constants.ProfilePage_BowHunter);
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ProfilePage_BowHunter); 
			
			SeleniumFunc.ToGoToUrl(Constants.ProfilePage_BowHunter);
			Thread.sleep(4000);

	
		System.out.println("Step 3 : Clicking on 'Update Profile' button without entering any detials on page");
		Reporter.log("Step 3 : Clicking on 'Update Profile' button without entering any detials on page");
			
			profile.ClickOnUpdateProfileButton();
			Thread.sleep(4000);

	
		System.out.println("Step 4 : Verifying whether correct validaiton message is displayed or not");
		Reporter.log("Step 4 : Verifying whether correct validaiton message is displayed or not");
			
		
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "div[class='alert alert-error alert-block']");
			System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage= 
					"Uh oh. Something's wrong. Please review and correct the following fields:" + "\n" +
					"Gender" + "\n" +
					"Student's Ethnicity" + "\n" +
					"Address"+  "\n" +
					"City"+ "\n" +
					"State or District" + "\n" +
					"Zip Code"+ "\n" +
					"Phone Number for Mailing Address";  
			
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
	
	
	
	/* Test 11: 
	 * Verify user can hide/unhide physical address
	*/ 
	@Test
	private void Profile_HideUnhidePhysicalAddress() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 11 : Verify user can hide/unhide physical address"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 11: Verify user can hide/unhide physical address"  + "\n" +
				 	  "====");	
	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ProfilePage profile = new ProfilePage(driver);
	
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "profile" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "profile" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
			
		System.out.println("Step 2 : Navigate to Profile page : " + Constants.ProfilePage_BowHunter);
		Reporter.log("Step 2 : Navigate to Profile page  : " + Constants.ProfilePage_BowHunter); 
			
			SeleniumFunc.ToGoToUrl(Constants.ProfilePage_BowHunter);


		System.out.println("Step 3 : Verifying whether Physical address is displayed or not");
		Reporter.log("Step 3 : Verifying whether Physical address is displayed or not");
			
			Thread.sleep(5000);
			
			String ActualAttributeValue= SeleniumFunc.GetAttributeValue("id", "physicaladdress", "style");
			//System.out.println(ActualAttributeValue);
			
			String ExpectedAttributeValue= 
					"display: none;";  
			
			if(ActualAttributeValue.equals(ExpectedAttributeValue))
			{
				System.out.println("Success !! Physical address is hidden");
				Reporter.log("Success !! Physical address is hidden"); 
			}
			else
			{
				System.out.println("Failure !! Physical address is not hidden");
				Reporter.log("Failure!! Physical address is not hidden"); 
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : Selecting Physical Address checkbox and verifying whether Physical Address section is displayed or not");
		Reporter.log("Step 4 : Selecting Physical Address checkbox and verifying whether Physical Address section is displayed or not");
						
		
			profile.ClickOnPhysicalAddressCheckbox();
			Thread.sleep(5000);
			
			
			ActualAttributeValue= SeleniumFunc.GetAttributeValue("id", "physicaladdress", "style");
			System.out.println(ActualAttributeValue);
			
			ExpectedAttributeValue= 
					"display: block;";  
			
			if(ActualAttributeValue.equals(ExpectedAttributeValue))
			{
				System.out.println("Success !! Physical address is displayed");
				Reporter.log("Success !! Physical address is displayed"); 
			}
			else
			{
				System.out.println("Failure !! Physical address is hidden");
				Reporter.log("Failure!! Physical address is hidden"); 
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 5 : Unselecting Physical Address checkbox and verifying whether Physical Address section is displayed or not");
		Reporter.log("Step 5 : Unselecting Physical Address checkbox and verifying whether Physical Address section is displayed or not");
			
			profile.ClickOnPhysicalAddressCheckbox();
			
			Thread.sleep(5000);
			
			ActualAttributeValue= SeleniumFunc.GetAttributeValue("id", "physicaladdress", "style");
			//System.out.println(ActualAttributeValue);
			
			ExpectedAttributeValue= 
					"display: none;";  
			
			if(ActualAttributeValue.equals(ExpectedAttributeValue))
			{
				System.out.println("Success !! Physical address is hidden");
				Reporter.log("Success !! Physical address is hidden"); 
			}
			else
			{
				System.out.println("Failure !! Physical address is not hidden");
				Reporter.log("Failure!! Physical address is not hidden"); 
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
	 * Verify that for international country (e.g India), 'State or District' field should be changed to 'Region /Locality'
	*/ 
	@Test
	private void Profile_RegionLocalityFieldLabelChange() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 12 : Verify that for international country (e.g India), 'State or District' field should be changed to 'Region /Locality'"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 12: Verify that for international country (e.g India), 'State or District' field should be changed to 'Region /Locality'"  + "\n" +
				 	  "====");	
	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "profile" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "profile" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
			
		System.out.println("Step 2 : Navigate to Profile page : " + Constants.ProfilePage_BowHunter);
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ProfilePage_BowHunter); 
			
			SeleniumFunc.ToGoToUrl(Constants.ProfilePage_BowHunter);
			Thread.sleep(4000);


		System.out.println("Step 3 : Changing County to International county e.g India");
		Reporter.log("Step 3 : Changing County to International county e.g India");
			
			SeleniumFunc.SelectValueFromDropdownList("id", "RegistrationModel_s_country_id", "India");
			Thread.sleep(4000);

		
		System.out.println("Step 4 : Verfiying whether 'State or District' field label changed to 'Region /Locality'");
		Reporter.log("Step 4 : Verfiying whether 'State or District' field label changed to 'Region /Locality'");
		
			
		
			String ActualLabel= SeleniumFunc.GetElementText("css", "section#mailing-address label[for='RegistrationModel_s_state_cd']");
			System.out.println(ActualLabel);
			
			String ExpectedLabel = "Region or Locality"; 
			
			if(ActualLabel.equals(ExpectedLabel))
			{
				System.out.println("Success !! 'State or District' field label changed to 'Region /Locality'");
				Reporter.log("Success !! 'State or District' field label changed to 'Region /Locality"); 
			}
			else
			{
				System.out.println("Failure !! 'State or District' field label not changed to 'Region /Locality'");
				Reporter.log("Failure!! 'State or District' field label not changed to 'Region /Locality'"); 
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
	 * Verify that all countries should be displayed under counrty dropdown
	*/ 
	@Test
	private void Profile_CountryDropdown_Values() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 13 : Verify that all countries should be displayed under counrty dropdown"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 13 : Verify that all countries should be displayed under counrty dropdown"  + "\n" +
				 	  "====");	
	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ProfilePage profile = new ProfilePage(driver);
		JavaHelpers Read1 = new JavaHelpers();
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "profilehunter" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "profilehunter" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
	
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "5", "1990", emailaddress, emailaddress);
			Thread.sleep(8000);
			
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}		
			
		System.out.println("Step 2 : Navigate to Profile page : " + Constants.ProfilePage_BowHunter);
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ProfilePage_BowHunter); 
			
			SeleniumFunc.ToGoToUrl(Constants.ProfilePage_BowHunter);
			Thread.sleep(4000);

	
		System.out.println("Step 3 : Verifying that all countries should be displayed under counrty dropdown");
		Reporter.log("Step 3 : Verifying that all countries should be displayed under counrty dropdown"); 
			
			ArrayList<String> expected_coutries_name = new ArrayList<String>();
			String[][] countries_temp = Read1.GetTableArray(Constants.TestDataFileLocation, "BowHunterEd","Countries");
			
			String[] countries = new String[countries_temp.length];
			Thread.sleep(4000);

			for(int i=0;i<countries_temp.length;i++)
			{
				countries[i] = countries_temp[i][0];
				/*System.out.println(countries[i]);*/
			}
			expected_coutries_name.addAll(Arrays.asList(countries));
			
			Thread.sleep(4000);

			//Getting countries values from Country dropdown list
			List<WebElement> actual_countries = new Select(profile.MailingAddressCountry).getOptions();
			ArrayList<String> actual_coutries_name = new ArrayList<String>();
			
			Thread.sleep(4000);

			for(WebElement e:actual_countries)
			{
				/*System.out.println(e.getText());*/
				actual_coutries_name.add(e.getText());
			}
			
			
			if(actual_coutries_name.equals(expected_coutries_name))
			{
				System.out.println("Success !! all countries names are displayed under counrty dropdown");
				Reporter.log("Success !! all countries names are displayed under counrty dropdown"); 
			}
			else
			{
				System.out.println("Failure !! all countries names are NOT displayed under counrty dropdown. Expected : " + "\n" );
				Reporter.log("Failure !! all countries names are NOT displayed under counrty dropdown. Expected : " + "\n" );
				
				for(int i=0; i<expected_coutries_name.size();i++)
				{
					System.out.println(expected_coutries_name.get(i));
					Reporter.log(expected_coutries_name.get(i));
				}
				
				System.out.println(" Actual : " + "\n" );
				Reporter.log(" Actual : " + "\n" );
				
				for(int i=0; i<actual_coutries_name.size();i++)
				{
					System.out.println(actual_coutries_name.get(i));
					Reporter.log(actual_coutries_name.get(i));
				}
				
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
