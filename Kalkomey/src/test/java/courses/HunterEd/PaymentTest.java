package courses.HunterEd;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.CertificationInformationPage;
import pageFactory.Courses.ContentsPage;
import pageFactory.Courses.CourseCompletePage;
import pageFactory.Courses.FinalExamPage;
import pageFactory.Courses.KECourseAdminPage;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.PaymentInformationPage;
import pageFactory.Courses.ProfileNewPage;
import pageFactory.Courses.RegistrationNewPage;
import pageFactory.Courses.ViewReceiptPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class PaymentTest 
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
	 * Verify student who has passed Exam can navigated to Application page and Application page has correct information
	 * */ 
	@Test
	private void PaymentStage1_UI_Verification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify student who has passed Exam can navigated to Application page and Application page has correct information"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify student who has passed Exam can navigated to Application page and Application page has correct information"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username, "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
			header.Contents.click();
			Thread.sleep(4000);
	
			String ContentPageUrl = driver.getCurrentUrl();
			
			header.Profile.click();
			Thread.sleep(5000);

					
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
			//Go back to Contents page
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(5000);

			//Click on Continue Exam button
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(5000);

			//Selecting Answer for Question 61 and clicking on Next button
			
				//Selecting answer
				finalexam.Answer1.click();
				Thread.sleep(5000);

				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
				Thread.sleep(5000);

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(5000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information , verfiying UI/Text information" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information , verfiying UI/Text information");
		
			//Verifying page header
			
			String expectedtext = "Your Application for the Hunter Education Certificate";
			String actualtext = cerinfo.PageHeader.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Page header is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Page header is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Page header is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Page header is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
		
			
			//Verifying breadcrumb for stages i.e. Checking Out 1 Certification Information / 2 Payment Information / 3 View Receipt
		
			expectedtext = "Checking Out 1 Certification Information / 2 Payment Information / 3 View Receipt";
			actualtext = cerinfo.breadcrumbtext.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! breadcrumb text is correct i.e. " + actualtext );
				Reporter.log("-- Success !! breadcrumb text is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! breadcrumb text is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! breadcrumb text is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			// Verifying 'Certificate Information' header
			
			expectedtext = "Certificate Information";
			actualtext = cerinfo.CertificationInfoText.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Certificate Information section is present i.e. " + actualtext );
				Reporter.log("-- Success !! Certificate Information section is present i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Certificate Information section is NOT present . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Certificate Information section is NOT present. Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
		
			// Verifying 'State Required Information' header
		
			expectedtext = "State Required Information";
			actualtext = cerinfo.StateRequiredInformationText.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! State Required Information section is present i.e. " + actualtext );
				Reporter.log("-- Success !! State Required Information section is present i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! State Required Information section is NOT present . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! State Required Information section is NOT present. Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			//Verifying user's name
			expectedtext = "Test Ke-Testing";
			actualtext = cerinfo.Student_Fullname.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Student_Fullname is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Student_Fullname is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Student_Fullname is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Student_Fullname is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
		
			//Verifying DOB
			expectedtext = "January 02, 1990";
			actualtext = cerinfo.Student_Birthdate.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Student_Birthdate is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Student_Birthdate is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Student_Birthdate is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Student_Birthdate is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
	 * Certification Information - Stage 1 : Verify validation message for mandatory fields
	 * */ 
	@Test
	private void PaymentStage1_Validation_MandatoryFields() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Certification Information - Stage 1 : Verify validation message for mandatory fields"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Certification Information - Stage 1 : Verify validation message for mandatory fields"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username, "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			register.WelcomeAboard.click();
			Thread.sleep(3000);
			
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();
			
			header.Profile.click();
			Thread.sleep(5000);
		
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);

		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  Clicking on 'Confirm Certification Information' button without entering any detials on page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  Clicking on 'Confirm Certification Information' button without entering any detials on page");
		
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);

			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "div[class='alert alert-error alert-block']");
			System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage= 
					"Uh oh. Something's wrong. Please review and correct the following fields:" + "\n" +
					"Gender" + "\n" +
					"Student's SSN" + "\n" +
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
	
	
	
	/* Test 3: 
	 * Certification Information - Stage 1 : Verify validation message for invalid values for SSN, Zip, Phone Number fields
	 * */ 
	@Test
	private void PaymentStage1_Validation_InvalidInputs() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Certification Information - Stage 1 : Verify validation message for invalid values for Zip, Phone Number fields"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Certification Information - Stage 1 : Verify validation message for invalid values for Zip, Phone Number fields"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username, "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			register.WelcomeAboard.click();
			Thread.sleep(3000);
			
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();
			
			header.Profile.click();
			Thread.sleep(5000);

					
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);

		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  entering invalid values for Zip, Phone Number fields" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  entering invalid values for Zip, Phone Number fields");
		
			System.out.println("-- Entering invalid values to Zip field");
			Reporter.log("-- Entering invalid values to Zip field");
				
				cerinfo.MailingAddressZip.sendKeys("sdfdsf");
				cerinfo.ConfirmCertificateInformation.click();
				Thread.sleep(4000);

				String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#mailingaddress > div:nth-child(6) > div > p").trim();
				//System.out.println(ActualValidationMessage);
				
				String ExpectedValidationMessage= 
						"This zip code is invalid. Please use the format: 12345 or 12345-6789" ; 
				
				if(ActualValidationMessage.equals(ExpectedValidationMessage))
				{
					System.out.println("-- Success !! correct validation messages are displayed for Zip field i.e. " + ActualValidationMessage);
					Reporter.log("-- Success !! correct validation messages are displayed for Zip field i.e. " + ActualValidationMessage); 
				}
				else
				{
					System.out.println("-- Failure !!  Incorrect validation messages are displayed for Zip field" + "\n" + "Expected Validation messages : "  
										+ "\n" + ExpectedValidationMessage + "\n" + 
										 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
					Reporter.log("-- Failure !!  Incorrect validation messages are displayed for SSN field" + "\n" + "Expected Validation messages : "  
							+ "\n" + ExpectedValidationMessage + "\n" + 
							 "Actual Validation messages : " + "\n" + ActualValidationMessage); 
					
					AssertFailedCount++;
				}
				
			
			System.out.println("-- Entering invalid values to Phone number field");
			Reporter.log("-- Entering invalid values to Phone number field");
				
				driver.navigate().refresh();
				Thread.sleep(4000);

				cerinfo.MailingAddressPhoneNumber.sendKeys("sdfdsf");
				cerinfo.ConfirmCertificateInformation.click();
				Thread.sleep(4000);

				ActualValidationMessage= SeleniumFunc.GetElementText("css", "#mailingaddress > div:nth-child(8) > div > p").trim();
				//System.out.println(ActualValidationMessage);
				
				ExpectedValidationMessage= 
						"This phone number is invalid. Please use the format 234-567-8910, 2345678910 or 234 567 8910. Country code is not required." ; 
				
				if(ActualValidationMessage.equals(ExpectedValidationMessage))
				{
					System.out.println("-- Success !! correct validation messages are displayed for Phone number field i.e. " + ActualValidationMessage);
					Reporter.log("-- Success !! correct validation messages are displayed for Phone number field i.e. " + ActualValidationMessage); 
				}
				else
				{
					System.out.println("-- Failure !!  Incorrect validation messages are displayed for Phone number field" + "\n" + "Expected Validation messages : "  
										+ "\n" + ExpectedValidationMessage + "\n" + 
										 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
					Reporter.log("-- Failure !!  Incorrect validation messages are displayed for Phone number field" + "\n" + "Expected Validation messages : "  
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
	 * Certification Information - Stage 1 : Verify that for international country (e.g India), 'State or District' field should be changed to 'Region /Locality'
	 * */ 
	@Test
	private void PaymentStage1_RegionLocalityFieldLabelChange() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Certification Information - Stage 1 : Verify that for international country (e.g India), 'State or District' field should be changed to 'Region /Locality'"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Certification Information - Stage 1 : Verify that for international country (e.g India), 'State or District' field should be changed to 'Region /Locality'"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username, "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			register.WelcomeAboard.click();
			Thread.sleep(3000);

			header.Contents.click();
			Thread.sleep(4000);
	
			String ContentPageUrl = driver.getCurrentUrl();
			
			header.Profile.click();
			Thread.sleep(5000);

					
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);

		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  changing Country to International county e.g India" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  changing Country to International county e.g India");
		
		
				new Select (cerinfo.MailingAddressCountry).selectByVisibleText("India");
	
			
			System.out.println("Step 5 : Verfiying whether 'State or District' field label changed to 'Region /Locality'");
			Reporter.log("Step 5 : Verfiying whether 'State or District' field label changed to 'Region /Locality'");
			
				
			
				String ActualLabel= SeleniumFunc.GetElementText("css", "label[for='RegistrationModel_s_state_cd']");
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
	
	
	
	/* Test 5: 
	 * Certification Information - Stage 1 :  Verify details on Confirmation pop up (before & after change)
	 * */ 
	@Test
	private void PaymentStage1_ConfirmationPopUpDatails() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Certification Information - Stage 1 :  Verify details on Confirmation pop up (before & after change)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Certification Information - Stage 1 :  Verify details on Confirmation pop up (before & after change)"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username, "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			register.WelcomeAboard.click();
			Thread.sleep(3000);

			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();
			
			header.Profile.click();
			Thread.sleep(8000);
				
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);

		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  entering User info, Contact info." );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  entering User info, Contact info.");
		
			cerinfo.SelectGender("Male");
			
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
		System.out.println("Step 5 : Clicking on 'Confirm Cerification Information' button and verifying details on 'Confirm Your Certificate Information' model" );
		Reporter.log("Step 5 : Clicking on 'Confirm Cerification Information' button and verifying details on 'Confirm Your Certificate Information' model");
		
			Thread.sleep(4000);
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
			
			//Verifying user's name
			String expectedtext = "Test Ke-Testing";
			String actualtext = cerinfo.ConfirmCertificateInformationModal_Fullname.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Student_Fullname is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Student_Fullname is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Student_Fullname is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Student_Fullname is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
		
			//Verifying DOB
			expectedtext = "January 02, 1990";
			actualtext = cerinfo.ConfirmCertificateInformationModal_DOB.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Student_Birthdate is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Student_Birthdate is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Student_Birthdate is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Student_Birthdate is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			//Verifying Gender
			expectedtext = "Male";
			actualtext = cerinfo.ConfirmCertificateInformationModal_Gender.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Gender is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Gender is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Gender is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Gender is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			//Verifying Mailing Address
			expectedtext = 	"14086 Proton Rd" + "\n" +
							"Dallas, TX 75244"+ "\n" +
							"United States of America"+ "\n" +
							"Phone: 234-567-8910";
			
			actualtext = cerinfo.ConfirmCertificateInformationModal_MailingAddress.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Mailing Address is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Mailing Address is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Mailing Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Mailing Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 6 : Clicking on 'Make Change' button and updating details again." );
		Reporter.log("Step 6 : Clicking on 'Make Change' button and updating details again.");
			
			cerinfo.ConfirmCertificateInformationModal_MakeAChangeButton.click();
			Thread.sleep(4000);

			//Updating details
			cerinfo.SelectGender("Female");
		
			cerinfo.EnterMailingAddress("1201 W UNIVERSITY DR", "", "Edinburg", "Texas", "78539", "United States of America", "234-567-1111");
			
		System.out.println("Step 7 : Clicking on 'Confirm Cerification Information' button and verifying details on 'Confirm Your Certificate Information' model" );
		Reporter.log("Step 7 : Clicking on 'Confirm Cerification Information' button and verifying details on 'Confirm Your Certificate Information' model");
		
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(7000);
					
			//Verifying user's name
			expectedtext = "Test Ke-Testing";
			actualtext = cerinfo.ConfirmCertificateInformationModal_Fullname.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Student_Fullname is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Student_Fullname is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Student_Fullname is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Student_Fullname is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
		
			//Verifying DOB
			expectedtext = "January 02, 1990";
			actualtext = cerinfo.ConfirmCertificateInformationModal_DOB.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Student_Birthdate is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Student_Birthdate is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Student_Birthdate is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Student_Birthdate is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			//Verifying Gender
			expectedtext = "Female";
			actualtext = cerinfo.ConfirmCertificateInformationModal_Gender.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Gender is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Gender is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Gender is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Gender is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			//Verifying Mailing Address
			expectedtext = 	"1201 W UNIVERSITY DR" + "\n" +
					"Edinburg, TX 78539"+ "\n" +
					"United States of America"+ "\n" +
					"Phone: 234-567-1111";
			
			actualtext = cerinfo.ConfirmCertificateInformationModal_MailingAddress.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Mailing Address is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Mailing Address is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Mailing Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Mailing Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
	 * Certification Information - Stage 1 :  Verify details on Confirmation pop up (before & after change) for Physical address
	 * */ 
	@Test
	private void PaymentStage1_ConfirmationPopUpDatails_Phy_Address() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Certification Information - Stage 1 :  Verify details on Confirmation pop up (before & after change) for Physical address"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Certification Information - Stage 1 :  Verify details on Confirmation pop up (before & after change) for Physical address"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username,"January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			register.WelcomeAboard.click();
			Thread.sleep(3000);
			
			header.Contents.click();
			Thread.sleep(4000);
	
			String ContentPageUrl = driver.getCurrentUrl();
			
			header.Profile.click();
			Thread.sleep(5000);

					
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);
				
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  entering User info, Contact info." );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  entering User info, Contact info.");
		
			cerinfo.SelectGender("Male");

			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
			//Selecting Phy.address checkbox
			cerinfo.PhysicalAddressCheckbox.click();
			Thread.sleep(4000);
			
			cerinfo.EnterPhysicalAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");	
			
			
		System.out.println("Step 5 : Clicking on 'Confirm Cerification Information' button and verifying details on 'Confirm Your Certificate Information' model" );
		Reporter.log("Step 5 : Clicking on 'Confirm Cerification Information' button and verifying details on 'Confirm Your Certificate Information' model");
		
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(8000);
					
			//Verifying user's name
			String expectedtext = "Test Ke-Testing";
			String actualtext = cerinfo.ConfirmCertificateInformationModal_Fullname.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Student_Fullname is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Student_Fullname is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Student_Fullname is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Student_Fullname is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
		
			//Verifying DOB
			expectedtext = "January 02, 1990";
			actualtext = cerinfo.ConfirmCertificateInformationModal_DOB.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Student_Birthdate is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Student_Birthdate is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Student_Birthdate is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Student_Birthdate is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			//Verifying Gender
			expectedtext = "Male";
			actualtext = cerinfo.ConfirmCertificateInformationModal_Gender.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Gender is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Gender is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Gender is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Gender is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			//Verifying Mailing Address
			expectedtext = 	"14086 Proton Rd" + "\n" +
							"Dallas, TX 75244"+ "\n" +
							"United States of America"+ "\n" +
							"Phone: 234-567-8910";
			
			actualtext = cerinfo.ConfirmCertificateInformationModal_MailingAddress.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Mailing Address is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Mailing Address is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Mailing Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Mailing Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			//Verifying Physical Address
			expectedtext = 	"14086 Proton Rd" + "\n" +
							"Dallas, TX 75244"+ "\n" +
							"United States of America"+ "\n" +
							"Phone: 234-567-8910";
			
			actualtext = cerinfo.ConfirmCertificateInformationModal_PhysicalAddress.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Physical Address is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Physical Address is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Physical Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Physical Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
	 * Certification Information - Stage 1 : Verify user can can edit and update fields
	 * */ 
	@Test
	private void PaymentStage1_EditUpdateFields() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Certification Information - Stage 1 : Verify user can can edit and update fields"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Certification Information - Stage 1 : Verify user can can edit and update fields"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username, "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			register.WelcomeAboard.click();
			Thread.sleep(3000);

			header.Contents.click();
			Thread.sleep(4000);
		
			String ContentPageUrl = driver.getCurrentUrl();
		
			header.Profile.click();
			Thread.sleep(5000);

					
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);
			
		System.out.println("Step 3 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 3 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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

				
		System.out.println("Step 4 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 4 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
		System.out.println("Step 5 : On Application page - Stage 1 : Certification Information ,  verifying User info, Contact info." );
		Reporter.log("Step 5 : On Application page - Stage 1 : Certification Information ,  verifying User info, Contact info.");
		
			
			//Verifying Gender
			String expectedtext = gender;
			String actualtext = new Select(cerinfo.Student_Gender).getFirstSelectedOption().getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Gender is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Gender is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Gender is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Gender is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			
			//Verifying Mailing Address
			expectedtext = 	addressline1 + "\n" +
							addressline2+ "\n" +
							city + ", AL "+zip + "\n" +
							country + "\n" +
							phonenumber;
			
			actualtext = cerinfo.MailingAddressLine1.getAttribute("value").trim() + "\n" + 
						 cerinfo.MailingAddressLine2.getAttribute("value").trim()+ "\n" + 
						 cerinfo.MailingAddressCity.getAttribute("value").trim() + ", AL "+cerinfo.MailingAddressZip.getAttribute("value").trim() +  "\n" +
						 new Select(cerinfo.MailingAddressCountry).getFirstSelectedOption().getText().trim()+ "\n" +
						 cerinfo.MailingAddressPhoneNumber.getAttribute("value").trim();
			
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Mailing Address is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Mailing Address is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Mailing Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Mailing Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 6 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info." );
		Reporter.log("Step 6 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info.");
		
		
			cerinfo.SelectGender("Female");
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-1111");
			
			
		System.out.println("Step 7 : Clicking on 'Confirm Cerification Information' button and verifying details on 'Confirm Your Certificate Information' model" );
		Reporter.log("Step 7 : Clicking on 'Confirm Cerification Information' button and verifying details on 'Confirm Your Certificate Information' model");
		
			cerinfo.ConfirmCertificateInformation.click();
			
			Thread.sleep(6000);
					
			//Verifying user's name
			expectedtext = "Test Ke-Testing";
			actualtext = cerinfo.ConfirmCertificateInformationModal_Fullname.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Student_Fullname is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Student_Fullname is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Student_Fullname is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Student_Fullname is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
		
			//Verifying DOB
			expectedtext = "January 02, 1990";
			actualtext = cerinfo.ConfirmCertificateInformationModal_DOB.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Student_Birthdate is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Student_Birthdate is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Student_Birthdate is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Student_Birthdate is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			//Verifying Gender
			expectedtext = "Female";
			actualtext = cerinfo.ConfirmCertificateInformationModal_Gender.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Gender is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Gender is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Gender is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Gender is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			//Verifying Mailing Address
			expectedtext = 	"14086 Proton Rd" + "\n" +
					"Dallas, TX 75244"+ "\n" +
					"United States of America"+ "\n" +
					"Phone: 234-567-1111";
			
			actualtext = cerinfo.ConfirmCertificateInformationModal_MailingAddress.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Mailing Address is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Mailing Address is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Mailing Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Mailing Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
	 * Certification Information - Stage 1 :  Verify user can navigated to step 2 ( i.e. Payment Information)
	 * */ 
	@Test
	private void PaymentStage1_NavigateToNextStage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Certification Information - Stage 1 :  Verify user can navigated to step 2 ( i.e. Payment Information)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Certification Information - Stage 1 :  Verify user can navigated to step 2 ( i.e. Payment Information)"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username,  "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			register.WelcomeAboard.click();
			Thread.sleep(3000);

			header.Contents.click();
			Thread.sleep(4000);
		
			String ContentPageUrl = driver.getCurrentUrl();
	
			header.Profile.click();
			Thread.sleep(5000);

					
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);

		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info." );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info.");
		
		
			cerinfo.SelectGender("Female");
		
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
			
		System.out.println("Step 5 : Clicking on 'Confirm Cerification Information' button" );
		Reporter.log("Step 5 : Clicking on 'Confirm Cerification Information' button");
		
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(6000);
			
			
		System.out.println("Step 6 : On Confirm Information model , clicking on 'Continue Payment Information' button" );
		Reporter.log("Step 6 : On Confirm Information model , clicking on 'Continue Payment Information' button");
		
		
			expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
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
	 * Payment Information - Stage 2 :  Verify Payment Information page has correct information
	 * */ 
	@Test
	private void PaymentStage2_UI_Verification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Payment Information - Stage 2 :  Verify Payment Information page has correct information"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 : Payment Information - Stage 2 :  Verify Payment Information page has correct information"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username, "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			register.WelcomeAboard.click();
			Thread.sleep(3000);
			
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();
			
			header.Profile.click();
			Thread.sleep(5000);

					
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);

		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
			
			
			expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , , verfiying UI/Text information" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , , verfiying UI/Text information");
		
			//Verifying page header
			
			String expectedtext = "Your Payment for the Hunter Education Certificate";
			String actualtext = payinfo.PageHeader.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Page header is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Page header is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Page header is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Page header is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
		
			
			//Verifying breadcrumb for stages i.e. Checking Out 1 Certification Information / 2 Payment Information / 3 View Receipt
		
			expectedtext = "Checking Out 1 Certification Information / 2 Payment Information / 3 View Receipt";
			actualtext = payinfo.breadcrumbtext.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! breadcrumb text is correct i.e. " + actualtext );
				Reporter.log("-- Success !! breadcrumb text is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! breadcrumb text is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! breadcrumb text is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			// Verifying 'Items to Purchase' header
			
			expectedtext = "Items to Purchase";
			actualtext = payinfo.ItemstoPurchaseText.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Items to Purchase section is present i.e. " + actualtext );
				Reporter.log("-- Success !! Items to Purchase section is present i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Items to Purchase section is NOT present . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Items to Purchase section is NOT present. Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
		
			// Verifying 'Credit Card Information' header
		
			expectedtext = "Credit Card Information";
			actualtext = payinfo.CreditCardInformationText.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Credit Card Information section is present i.e. " + actualtext );
				Reporter.log("-- Success !! Credit Card Information section is present i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Credit Card Information section is NOT present . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Credit Card Information section is NOT present. Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			//Verifying Course Fee Header
			expectedtext = "Arkansas Hunter Ed Course Fee";
			actualtext = payinfo.CourseFee_Header.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Course Fee Header is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Course Fee Header is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Course Fee Header is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Course Fee Header is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
		
			
			//Verifying Course Fee
			expectedtext = "$19.50";
			actualtext = payinfo.CourseFee_Fee_Hunter.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Course Fee  is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Course Fee is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Course Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Course Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
		
			
			//Verifying Course Total Fee
			expectedtext = "$19.50";
			actualtext = payinfo.Total_Fee.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Course Total Fee  is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Course Total Fee is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Course Total Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Course Total Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
	 * Payment Information - Stage 2 :  Billing Contact - Verify correct mailing address displayed if user selects checkbox 'My billing contact info is the same as my mailing info.'
	 * */ 
	@Test
	private void PaymentStage2_BillingAddress() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : Payment Information - Stage 2 :  Billing Contact - Verify correct mailing address displayed if user selects checkbox 'My billing contact info is the same as my mailing info.'"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10 : Payment Information - Stage 2 :  Billing Contact - Verify correct mailing address displayed if user selects checkbox 'My billing contact info is the same as my mailing info.'"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username, "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			register.WelcomeAboard.click();
			Thread.sleep(3000);
			
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();

			header.Profile.click();
			Thread.sleep(5000);

					
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);

		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(8000);
			
			expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , clicking on 'My billing contact info is the same as my mailing info.' checkbox" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , clicking on 'My billing contact info is the same as my mailing info.' checkbox");
		
			Thread.sleep(4000);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 500);");	
			
			payinfo.BillingContactInfoCheckbox.click();
			Thread.sleep(2000);
			
			
			//Verifying Mailing Address
			String expectedtext = 	
					"Mailing Address" + "\n" + 
					"14086 Proton Rd" + "\n" +
					"Dallas, TX 75244"+ "\n" +
					"Phone Number" +  "\n" + 
					"234-567-8910";
		
			String actualtext = payinfo.BillingAddressWhileSelectedAsMailingAddress.getText().trim();
			
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Billing Address is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Billing Address is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Billing Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Billing Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
	 * Payment Information - Stage 2 :  Verify validation messages are displayed for mandatory fields
	 * */ 
	@Test
	private void PaymentStage2_Validation_MandatoryFields() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 11 : Payment Information - Stage 2 :  Verify validation messages are displayed for mandatory fields"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 11 : Payment Information - Stage 2 :  Verify validation messages are displayed for mandatory fields"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username,  "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			register.WelcomeAboard.click();
			Thread.sleep(3000);
			
			header.Contents.click();
			Thread.sleep(4000);
	
			String ContentPageUrl = driver.getCurrentUrl();
			
			header.Profile.click();
			Thread.sleep(5000);

					
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
			
			expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , clicking on 'Confirm Payment Information' button without entering any data" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , clicking on 'Confirm Payment Information' button without entering any data");
		
			Thread.sleep(5000);
			payinfo.ConfirmPaymentInformation.click();
			Thread.sleep(4000);

			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "div[class='alert alert-error alert-block']");
			System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage= 
					"Uh oh. Something's wrong. Please review and correct the following fields:" + "\n" +
					"First Name on Credit Card" + "\n" +
					"Last Name on Credit Card" + "\n" +
					"Credit Card Number"+  "\n" +
					"Address"+ "\n" +
					"City"+ "\n" +
					"Zip Code" + "\n" +
					"Phone Number";  
			
		
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
	
	
	
	/* Test 12: 
	 * Payment Information - Stage 2 :  Verify validation messages are displayed for invalid values for CC , Zip , Phone Number fields
	 * */ 
	@Test
	private void PaymentStage2_Validation_InvalidInputs() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 12 : Payment Information - Stage 2 :  Verify validation messages are displayed for invalid values for CC , Zip , Phone Number fields"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 12 : Payment Information - Stage 2 :  Verify validation messages are displayed for invalid values for CC , Zip , Phone Number fields"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username, "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);

			register.WelcomeAboard.click();
			Thread.sleep(3000);
			
			header.Contents.click();
			Thread.sleep(4000);
	
			String ContentPageUrl = driver.getCurrentUrl();
			
			header.Profile.click();
			Thread.sleep(5000);

					
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
			//Go back to Contents page
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			SeleniumFunc.ClickOnElement("css", "ul[class='nav pull-right'] li:nth-of-type(1) a");
			
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
				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
			
			
			expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , entering invalid values for CC , Zip , Phone Number fields" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , entering invalid values for CC , Zip , Phone Number fields");
		
			System.out.println("-- Entering invalid values to Zip field");
			Reporter.log("-- Entering invalid values to Zip field");
				
				payinfo.BillingZipCode.sendKeys("sdfdsf");
				payinfo.ConfirmPaymentInformation.click();
				Thread.sleep(4000);

				String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#billingcontact > div:nth-child(4) > div > p").trim();
				//System.out.println(ActualValidationMessage);
				
				String ExpectedValidationMessage= 
						"This zip code is invalid. Please use the format: 12345 or 12345-6789" ; 
				
				if(ActualValidationMessage.equals(ExpectedValidationMessage))
				{
					System.out.println("-- Success !! correct validation messages are displayed for Zip field i.e. " + ActualValidationMessage);
					Reporter.log("-- Success !! correct validation messages are displayed for Zip field i.e. " + ActualValidationMessage); 
				}
				else
				{
					System.out.println("-- Failure !!  Incorrect validation messages are displayed for Zip field" + "\n" + "Expected Validation messages : "  
										+ "\n" + ExpectedValidationMessage + "\n" + 
										 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
					Reporter.log("-- Failure !!  Incorrect validation messages are displayed for SSN field" + "\n" + "Expected Validation messages : "  
							+ "\n" + ExpectedValidationMessage + "\n" + 
							 "Actual Validation messages : " + "\n" + ActualValidationMessage); 
					
					AssertFailedCount++;
				}
				
				
			System.out.println("-- Entering invalid values to Phone number field");
			Reporter.log("-- Entering invalid values to Phone number field");
				
				driver.navigate().refresh();
				Thread.sleep(4000);

				payinfo.BillingPhoneNumber.sendKeys("sdfdsf");
				payinfo.ConfirmPaymentInformation.click();
				Thread.sleep(4000);

				ActualValidationMessage= SeleniumFunc.GetElementText("css", "#billingcontact > div:nth-child(6) > div > p:nth-child(2)").trim();
				//System.out.println(ActualValidationMessage);
				
				ExpectedValidationMessage= 
						"This phone number is invalid. Please use the format 234-567-8910, 2345678910 or 234 567 8910. Country code is not required." ; 
				
				if(ActualValidationMessage.equals(ExpectedValidationMessage))
				{
					System.out.println("-- Success !! correct validation messages are displayed for Phone number field i.e. " + ActualValidationMessage);
					Reporter.log("-- Success !! correct validation messages are displayed for Phone number field i.e. " + ActualValidationMessage); 
				}
				else
				{
					System.out.println("-- Failure !!  Incorrect validation messages are displayed for Phone number field" + "\n" + "Expected Validation messages : "  
										+ "\n" + ExpectedValidationMessage + "\n" + 
										 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
					Reporter.log("-- Failure !!  Incorrect validation messages are displayed for Phone number field" + "\n" + "Expected Validation messages : "  
							+ "\n" + ExpectedValidationMessage + "\n" + 
							 "Actual Validation messages : " + "\n" + ActualValidationMessage); 
					
					AssertFailedCount++;
				}
			
				
			System.out.println("-- Entering invalid values to CC fields - CC Number");
			Reporter.log("-- Entering invalid values to CC fields - CC Number");
				
				driver.navigate().refresh();
				Thread.sleep(4000);

				payinfo.CCNumber.sendKeys("sdfdsf");
				payinfo.ConfirmPaymentInformation.click();
				Thread.sleep(4000);

				ActualValidationMessage= SeleniumFunc.GetElementText("css", "#creditcardinfo > div:nth-child(4) > div > p").trim();
				//System.out.println(ActualValidationMessage);
				
				ExpectedValidationMessage= 
						"This credit card appears to be invalid. Please re-enter the number." ; 
				
				if(ActualValidationMessage.equals(ExpectedValidationMessage))
				{
					System.out.println("-- Success !! correct validation messages are displayed for CC number field i.e. " + ActualValidationMessage);
					Reporter.log("-- Success !! correct validation messages are displayed for CC number field i.e. " + ActualValidationMessage); 
				}
				else
				{
					System.out.println("-- Failure !!  Incorrect validation messages are displayed for CC number field" + "\n" + "Expected Validation messages : "  
										+ "\n" + ExpectedValidationMessage + "\n" + 
										 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
					Reporter.log("-- Failure !!  Incorrect validation messages are displayed for CC number field" + "\n" + "Expected Validation messages : "  
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


	
	/* Test 13: 
	 * Payment Information - Stage 2 : Verify details on Confirmation pop up (before & after change)
	 * */ 
	@Test
	private void PaymentStage2_ConfirmationPopUpDatails() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 13 : Payment Information - Stage 2 : Verify details on Confirmation pop up (before & after change)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 13 : Payment Information - Stage 2 : Verify details on Confirmation pop up (before & after change)"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username,  "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			register.WelcomeAboard.click();
			Thread.sleep(3000);

			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();
	
			header.Profile.click();
			Thread.sleep(5000);

					
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);

		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
			
			
			expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , entering data for CC& Billing and clicking on 'Confirm Payment Information' button" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , entering data for CC& Billing and clicking on 'Confirm Payment Information' button");
		
			payinfo.EnterCCAndBillingDetails("test", "Patel", 
											"4111111111111111", "01", "2017", 
											"14086 Proton Rd.", "Peoria", "California", "75244", 
											 "United States of America", "410-477-3898");
											 
		
		System.out.println("Step 6 : Clicking on 'Confirm Payment Information' button and verifying details on 'Confirm Your Payment Information' model" );
		Reporter.log("Step 6 : Clicking on 'Payment Cerification Information' button and verifying details on 'Confirm Your Payment Information' model");
		
			payinfo.ConfirmPaymentInformation.click();
			Thread.sleep(4000);
			
			//Verifying Name on Card
			String expectedtext = "test Patel";
			String actualtext = payinfo.ConfirmPaymentInformationModal_Fullname.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! name is correct i.e. " + actualtext );
				Reporter.log("-- Success !! name is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! name is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! name is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
		
			//Verifying Card Number
			expectedtext = "4111111111111111";
			actualtext = payinfo.ConfirmPaymentInformationCard_Number.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Card Number is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Card Number is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Card Number is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Card Number is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			//Verifying Card Expiration Date
			expectedtext = "01-2017";
			actualtext = payinfo.ConfirmPaymentInformationCard_Expiration_Number.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Card Expiration Date is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Card Expiration Date is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Card Expiration Date is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Card Expiration Date is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			//Verifying Billing Address
			expectedtext = 	"Billing Address" + "\n" + 
							"14086 Proton Rd." + "\n" + 
							"Peoria, CA 75244" + "\n" + 
							"United States of America" + "\n" + 
							"Phone: 410-477-3898";
			actualtext = payinfo.ConfirmPaymentInformationBilling_Address.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Billing Address is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Billing Address is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Billing Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Billing Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			
			//Verifying Acceptance Of Terms
			expectedtext = "Acceptance of Terms" + "\n" + 
							"I understand that the course fee is the total listed above and that there are no refunds or cancellations. I have agreed to these terms.";
			actualtext = payinfo.ConfirmPaymentInformationAcceptanceOfTerms_Header.getText().trim() + "\n" +
						 payinfo.ConfirmPaymentInformationAcceptanceOfTerms_Text.getText().trim()	;
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Acceptance of Terms is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Acceptance of Terms is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Acceptance of Terms is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Acceptance of Terms is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
	
			
		
		System.out.println("Step 7 : Clicking 'Make A Change' button and editing details on Payment page" );
		Reporter.log("Step 7 : Clicking 'Make A Change' button and editing details on Payment page");
		
			payinfo.ConfirmPaymentInformationModal_MakeAChangeButton.click();
			Thread.sleep(4000);

			payinfo.EnterCCAndBillingDetails("test1", "Patel1", 
											"4111111111111112", "02", "2018", 
											"14088 Proton Rd.", "Peorib", "California", "75233", 
											 "United States of America", "410-477-3899");
											 
		
		System.out.println("Step 6 : Clicking on 'Confirm Payment Information' button and verifying details on 'Confirm Your Payment Information' model" );
		Reporter.log("Step 6 : Clicking on 'Payment Cerification Information' button and verifying details on 'Confirm Your Payment Information' model");
		
			payinfo.ConfirmPaymentInformation.click();
			Thread.sleep(4000);
			
			//Verifying Name on Card
			expectedtext = "test1 Patel1";
			actualtext = payinfo.ConfirmPaymentInformationModal_Fullname.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! name is correct i.e. " + actualtext );
				Reporter.log("-- Success !! name is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! name is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! name is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
		
			//Verifying Card Number
			expectedtext = "4111111111111112";
			actualtext = payinfo.ConfirmPaymentInformationCard_Number.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Card Number is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Card Number is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Card Number is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Card Number is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			//Verifying Card Expiration Date
			expectedtext = "02-2018";
			actualtext = payinfo.ConfirmPaymentInformationCard_Expiration_Number.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Card Expiration Date is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Card Expiration Date is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Card Expiration Date is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Card Expiration Date is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			//Verifying Billing Address
			expectedtext = "Billing Address" + "\n" + 
							"14088 Proton Rd." + "\n" + 
							"Peorib, CA 75233" + "\n" + 
							"United States of America" + "\n" + 
							"Phone: 410-477-3899";
			
			actualtext = payinfo.ConfirmPaymentInformationBilling_Address.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Billing Address is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Billing Address is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Billing Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Billing Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			
			//Verifying Acceptance Of Terms
			expectedtext = "Acceptance of Terms" + "\n" + 
							"I understand that the course fee is the total listed above and that there are no refunds or cancellations. I have agreed to these terms.";
			actualtext = payinfo.ConfirmPaymentInformationAcceptanceOfTerms_Header.getText().trim() + "\n" +
						 payinfo.ConfirmPaymentInformationAcceptanceOfTerms_Text.getText().trim()	;
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Acceptance of Terms is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Acceptance of Terms is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Acceptance of Terms is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Acceptance of Terms is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
	 * Payment Information - Stage 2 : Verify details on Confirmation pop up (before & after change) if Billing Address is same as Mailing Address
	 * */ 
	@Test
	private void PaymentStage2_ConfirmationPopUpDatails_SameAddress() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 14 : Payment Information - Stage 2 : Verify details on Confirmation pop up (before & after change) if Billing Address is same as Mailing Address"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 14 : Payment Information - Stage 2 : Verify details on Confirmation pop up (before & after change) if Billing Address is same as Mailing Address"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username,  "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			register.WelcomeAboard.click();
			Thread.sleep(3000);
			
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();
	
			header.Profile.click();
			Thread.sleep(5000);

					
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");

			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
			
			
			expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button");
		
			payinfo.FirstName.sendKeys("test");
			payinfo.LastName.sendKeys("Patel");
			payinfo.CCNumber.sendKeys("4111111111111111");
			
			new Select(payinfo.ExpirationMonth).selectByVisibleText("03");
			new Select(payinfo.ExpirationYear).selectByVisibleText("2025");
			
			payinfo.BillingContactInfoCheckbox.click();
			Thread.sleep(4000);

		
		System.out.println("Step 6 : Clicking on 'Confirm Payment Information' button and verifying details on 'Confirm Your Payment Information' model" );
		Reporter.log("Step 6 : Clicking on 'Payment Cerification Information' button and verifying details on 'Confirm Your Payment Information' model");
		
			payinfo.ConfirmPaymentInformation.click();
			Thread.sleep(4000);
			
			//Verifying Name on Card
			String expectedtext = "test Patel";
			String actualtext = payinfo.ConfirmPaymentInformationModal_Fullname.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! name is correct i.e. " + actualtext );
				Reporter.log("-- Success !! name is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! name is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! name is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
		
			//Verifying Card Number
			expectedtext = "4111111111111111";
			actualtext = payinfo.ConfirmPaymentInformationCard_Number.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Card Number is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Card Number is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Card Number is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Card Number is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			//Verifying Card Expiration Date
			expectedtext = "03-2025";
			actualtext = payinfo.ConfirmPaymentInformationCard_Expiration_Number.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Card Expiration Date is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Card Expiration Date is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Card Expiration Date is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Card Expiration Date is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			//Verifying Billing Address
			expectedtext = 	
					"Mailing Address" + "\n" + 
					"14086 Proton Rd" + "\n" +
					"Dallas, TX 75244"+ "\n" + "\n" + 
					"Phone Number" + "\n" + 
					"234-567-8910";
			
			actualtext = payinfo.ConfirmPaymentInformationBilling_Address.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Billing Address is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Billing Address is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Billing Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Billing Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			
			//Verifying Acceptance Of Terms
			expectedtext = "Acceptance of Terms" + "\n" + 
							"I understand that the course fee is the total listed above and that there are no refunds or cancellations. I have agreed to these terms.";
			actualtext = payinfo.ConfirmPaymentInformationAcceptanceOfTerms_Header.getText().trim() + "\n" +
						 payinfo.ConfirmPaymentInformationAcceptanceOfTerms_Text.getText().trim()	;
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Acceptance of Terms is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Acceptance of Terms is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Acceptance of Terms is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Acceptance of Terms is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
	 * Payment Information - Stage 2 :  Verify user can navigated to step 3 ( i.e.  View Receipt) while paying with Coupon code
	 * */ 
	@Test
	private void PaymentStage2_PayWithCoupon() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 15 : Payment Information - Stage 2 :  Verify user can navigated to step 3 ( i.e.  View Receipt) while paying with Coupon code"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 15 : Payment Information - Stage 2 :  Verify user can navigated to step 3 ( i.e.  View Receipt) while paying with Coupon code"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ViewReceiptPage receipt = new ViewReceiptPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username, "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			register.WelcomeAboard.click();
			Thread.sleep(3000);
			
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();

			header.Profile.click();
			Thread.sleep(5000);

					
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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
				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
	
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
			
			
			expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , entering invalid Coupon code and clicking on 'Apply Code' button" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , entering invalid Coupon code and clicking on 'Apply Code' button");
		
			payinfo.CouponCode.sendKeys("invalid");
			payinfo.ApplyCode.click();
			Thread.sleep(4000);

			String expectedtext = "Invalid student coupon or agency code. Please re-enter your code.";
			String actualtext = SeleniumFunc.GetElementText("css", "p.help-block").trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Coupon code validation message is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Coupon code validation message is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Coupon code validation message is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Coupon code validation message is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
		
		
		System.out.println("Step 6 : On Payment Information page - Stage 2 , entering valid Coupon code and clicking on 'Apply Code' button" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , entering valid Coupon code and clicking on 'Apply Code' button");
		
			payinfo.CouponCode.sendKeys(Constants.CouponCode);
			payinfo.ApplyCode.click();
			Thread.sleep(4000);

			expectedtext = Constants.ApplicationURL_Hunter + "/register/paymentconfirm/payment/";
			actualtext = driver.getCurrentUrl();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! user is navigated to View Receipt page i.e. " + actualtext );
				Reporter.log("-- Success !! user is navigated to View Receipt page i.e. " + actualtext );
				
				expectedtext = "Your payment has been processed and confirmed";
				actualtext = receipt.ConfirmationMessage.getText().trim();
				
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! Payment confirmation message is correct i.e. " + actualtext );
					Reporter.log("-- Success !! Payment confirmation message is correct i.e. " + actualtext );
				}
				else
				{
					System.out.println("-- Failure !! Payment confirmation message is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
					Reporter.log("-- Failure !! Payment confirmation message is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
					AssertFailedCount++;
				}
				
			}
			else
			{
				System.out.println("-- Failure !! user is NOT navigated to View Receipt page . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !!user is NOT navigated to View Receipt page . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
	 * Payment Information - Stage 2 :  Verify user can navigated to step 3 ( i.e.  View Receipt) while paying with Credit Card
	 * */ 
	@Test
	private void PaymentStage2_PayWithCreditCard() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 16 : Payment Information - Stage 2 :  Verify user can navigated to step 3 ( i.e.  View Receipt) while paying with Credit Card"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 16 : Payment Information - Stage 2 :  Verify user can navigated to step 3 ( i.e.  View Receipt) while paying with Credit Card"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ViewReceiptPage receipt = new ViewReceiptPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			System.out.println(emailaddress);	
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username, "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			register.WelcomeAboard.click();
			Thread.sleep(3000);
			
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();
			
			header.Profile.click();
			Thread.sleep(5000);

					
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);

		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
	
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
		
			
			expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button");
		
			payinfo.FirstName.sendKeys("test");
			payinfo.LastName.sendKeys("Patel");
			payinfo.CCNumber.sendKeys("4111111111111111");
			
			new Select(payinfo.ExpirationMonth).selectByVisibleText("03");
			new Select(payinfo.ExpirationYear).selectByVisibleText("2025");
			
			payinfo.BillingContactInfoCheckbox.click();
			Thread.sleep(4000);

			payinfo.ConfirmPaymentInformation.click();
			
			Thread.sleep(4000);
			
			payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
			
			Thread.sleep(10000);
			
		
			String expectedtext = Constants.ApplicationURL_Hunter + "/register/paymentconfirm/payment/";
			String actualtext = driver.getCurrentUrl();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! user is navigated to View Receipt page i.e. " + actualtext );
				Reporter.log("-- Success !! user is navigated to View Receipt page i.e. " + actualtext );
				
				expectedtext = "Your payment has been processed and confirmed";
				actualtext = receipt.ConfirmationMessage.getText().trim();
				
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! Payment confirmation message is correct i.e. " + actualtext );
					Reporter.log("-- Success !! Payment confirmation message is correct i.e. " + actualtext );
				}
				else
				{
					System.out.println("-- Failure !! Payment confirmation message is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
					Reporter.log("-- Failure !! Payment confirmation message is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
					AssertFailedCount++;
				}
				
			}
			else
			{
				System.out.println("-- Failure !! user is NOT navigated to View Receipt page . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !!user is NOT navigated to View Receipt page . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
	 * Verify that order confirmation email received by student on registered email address
	 * */ 
	@Test
	private void PaymentStage2_PaymentConfirmationEmail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 17 : Verify that order confirmation email received by student on registered email address"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 17 : Verify that order confirmation email received by student on registered email address"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			System.out.println(emailaddress);
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username, "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			register.WelcomeAboard.click();
			Thread.sleep(3000);

			header.Contents.click();
			Thread.sleep(4000);
	
			String ContentPageUrl = driver.getCurrentUrl();

			header.Profile.click();
			Thread.sleep(5000);

					
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			Thread.sleep(4000);
	
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
		
			
			expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button");
		
			payinfo.FirstName.sendKeys("test");
			payinfo.LastName.sendKeys("Patel");
			payinfo.CCNumber.sendKeys("4111111111111111");
			
			new Select(payinfo.ExpirationMonth).selectByVisibleText("03");
			new Select(payinfo.ExpirationYear).selectByVisibleText("2017");
			
			payinfo.BillingContactInfoCheckbox.click();
			Thread.sleep(4000);

			payinfo.ConfirmPaymentInformation.click();
			
			Thread.sleep(4000);
			
			payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
			
			Thread.sleep(10000);
			
	
		System.out.println("Step 6 : Navigating to Email Box and verifying whether payment confirmation email is received with correct details or not");
		Reporter.log("Step 6 : Navigating to Email Box and verifying whether payment confirmation email is received with correct details or not"); 
			
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=" + emailprefix + "#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
			
			String ActualMessageTitle= SeleniumFunc.GetElementText("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div").trim();
			//System.out.println(ActualMessageTitle);
											
			String ExpectedMessageTitle="Today's Hunter in " +Constants.State_Hunter_Timed.substring(1, Constants.State_Hunter_Timed.length()) + " Course - Customer Receipt"; 
			//System.out.println(ExpectedMessageTitle);
			
			if(ActualMessageTitle.equalsIgnoreCase(ExpectedMessageTitle))
			{
				System.out.println("Success!! Email is received with correct subject");
				Reporter.log("Success!! Email is received with correct subject"); 
			}
			else
			{
				System.out.println("Failure !!  Email is received with incorrect subject" + "\n" + "Expected Text: "  
									+ "\n" + ExpectedMessageTitle + "\n" + 
									 "Actual Text : " + "\n" +  ActualMessageTitle);
				Reporter.log("Failure !!Email is received with incorrect subject" + "\n" + "Expected Text: "  
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
						"Hi Test," + "\n" + "\n" +
						"Thank you for taking the Today's Hunter in " + Constants.State_Hunter_Timed.substring(1, Constants.State_Hunter_Timed.length())+ " Course. Details of your payment appear below." + "\n" + "\n" + "\n" +
	
						"============ PAYMENT INFORMATION ============" + "\n" + "\n" + 
						"Customer: Test Ke-Testing" +"\n" + 
						"Invoice Number:";
				
				
				String ExpectedMessageBody1 =
						"Description: Today's Hunter in " + Constants.State_Hunter_Timed.substring(1, Constants.State_Hunter_Timed.length())+ " Course" + "\n" + "\n" + "\n" +
	
						"Total: $19.50 USD" + "\n" +
						"Payment Method: Visa ************1111" +"\n" +  "\n" + "\n" +
						
						"============ BILLING INFORMATION ============" +"\n" +  "\n" +
						"Cardholder: test Patel" + "\n" + 
						"E-mail Address: " + emailaddress + "\n" +
						"Billing Address: 28261 N NY 10003" + "\n" +
						"New York, AL 11111" + "\n" +
						"234-567-8910";
						
				
				if(ActualMessageBody.toLowerCase().contains(ExpectedMessageBody.toLowerCase()) && ActualMessageBody.toLowerCase().contains(ExpectedMessageBody1.toLowerCase()))
				{
					System.out.println("Success!! Email Message body has correct details");
					Reporter.log("Success!! Email Message body has correct details"); 
				}
				else
				{
					System.out.println("Failure !!  Email Message body has incorrect details" + "\n" + "Expected Body: "  
										+ "\n" + ExpectedMessageBody + "\n" +ExpectedMessageBody1 + "\n" + 
										 "Actual Body : " + "\n" +  ActualMessageBody);
					Reporter.log("Failure !!  Email Message body has incorrect details" + "\n" + "Expected Body: "  
							+ "\n" + ExpectedMessageBody + "\n" +ExpectedMessageBody1 +  "\n" + 
							 "Actual Body : " + "\n" +  ActualMessageBody);
					
					AssertFailedCount++;
				}
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

	
	
	/* Test 18: 
	 * View Receipt - Stage 3 :  Verify View Receipt page has correct information
	 * */ 
	@Test
	private void PaymentStage3_ViewReceiptPageDetails() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 18 : View Receipt - Stage 3 :  Verify View Receipt page has correct information"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 18 : View Receipt - Stage 3 :  Verify View Receipt page has correct information"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ViewReceiptPage receipt = new ViewReceiptPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			System.out.println(emailaddress);	
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver);

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username, "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			register.WelcomeAboard.click();
			Thread.sleep(3000);

			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();
	
			header.Profile.click();
			Thread.sleep(5000);

					
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);

		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");

			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
		
			
			expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button");
		
			payinfo.FirstName.sendKeys("test");
			payinfo.LastName.sendKeys("Patel");
			payinfo.CCNumber.sendKeys("4111111111111111");
			
			new Select(payinfo.ExpirationMonth).selectByVisibleText("03");
			new Select(payinfo.ExpirationYear).selectByVisibleText("2025");
			
			payinfo.BillingContactInfoCheckbox.click();
			payinfo.ConfirmPaymentInformation.click();
			
			Thread.sleep(4000);
			
			payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
			
			Thread.sleep(10000);
			
	
		System.out.println("Step 6 : Verifying details on 'View Receipt' page");
		Reporter.log("Step 6 : Verifying details on 'View Receipt' page"); 
			
			//Verifying Url
			String expectedtext = Constants.ApplicationURL_Hunter + "/register/paymentconfirm/payment/";
			String actualtext = driver.getCurrentUrl();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! user is navigated to View Receipt page i.e. " + actualtext );
				Reporter.log("-- Success !! user is navigated to View Receipt page i.e. " + actualtext );
				
				expectedtext = "Your payment has been processed and confirmed";
				actualtext = receipt.ConfirmationMessage.getText().trim();
				
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! Payment confirmation message is correct i.e. " + actualtext );
					Reporter.log("-- Success !! Payment confirmation message is correct i.e. " + actualtext );
				}
				else
				{
					System.out.println("-- Failure !! Payment confirmation message is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
					Reporter.log("-- Failure !! Payment confirmation message is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
					AssertFailedCount++;
				}
				
			}
			else
			{
				System.out.println("-- Failure !! user is NOT navigated to View Receipt page . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !!user is NOT navigated to View Receipt page . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			// Verifying header
			
						expectedtext = "View Receipt";
						actualtext = receipt.PageHeader.getText().trim();
						
						if(actualtext.contains(expectedtext))
						{
							System.out.println("-- Success !! page title is correct i.e. " + actualtext );
							Reporter.log("-- Success !! page title is correct i.e. " + actualtext );
							
						}
						else
						{
							System.out.println("-- Failure !! page title is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
							Reporter.log("-- Failure !! page title is incorrect . " + expectedtext + " , Actual is : " + actualtext);
							AssertFailedCount++;
						}
						

			//Verifying breadcrumb for stages i.e. Checking Out 1 Certification Information / 2 Payment Information / 3 View Receipt
					
					expectedtext = "Checking Out 1 Certification Information / 2 Payment Information / 3 View Receipt";
					actualtext = receipt.BreadcrumbText.getText().trim();
					
					if(actualtext.equals(expectedtext))
					{
						System.out.println("-- Success !! breadcrumb text is correct i.e. " + actualtext );
						Reporter.log("-- Success !! breadcrumb text is correct i.e. " + actualtext );
					}
					else
					{
						System.out.println("-- Failure !! breadcrumb text is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
						Reporter.log("-- Failure !! breadcrumb text is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
						AssertFailedCount++;
					}
			
					
			// Verifying presence of 'Get Your Proof of Home Study Voucher' button
			
					expectedtext = "Get Your Online Course Completion Voucher";
					actualtext = receipt.GetYourProofofHomeStudyVoucher.getText().trim();
					
					if(actualtext.contains(expectedtext))
					{
						System.out.println("-- Success !! 'Get Your Online Course Completion Voucher' button  is present i.e. " + actualtext );
						Reporter.log("-- Success !! 'Get Your Online Course Completion Voucher' button  is present i.e. " + actualtext );
						
					}
					else
					{
						System.out.println("-- Failure !! 'Get Your Online Course Completion Voucher' button  is NOT present. Expected is : " + expectedtext + " , Actual is : " + actualtext );
						Reporter.log("-- Failure !! 'Get Your Online Course Completion Voucher' button  is NOT present. Expected is : " + expectedtext + " , Actual is : " + actualtext);
						AssertFailedCount++;
					}
			
		
			// Verifying presence of 'Print This Receipt' button
			
					expectedtext = "Print This Receipt";
					actualtext = receipt.PrintReceipt.getText().trim();
					
					if(actualtext.contains(expectedtext))
					{
						System.out.println("-- Success !! 'Print This Receipt' button  is present i.e. " + actualtext );
						Reporter.log("-- Success !! 'Print This Receipt' button  is present i.e. " + actualtext );
						
					}
					else
					{
						System.out.println("-- Failure !! 'Print This Receipt' button  is NOT present. Expected is : " + expectedtext + " , Actual is : " + actualtext );
						Reporter.log("-- Failure !! 'Print This Receipt' button  is NOT present. " + expectedtext + " , Actual is : " + actualtext);
						AssertFailedCount++;
					}
					
			
			// Verifying details under 'Bill To' section i.e. User's name, Billing address , total Amount, Course Fee , Total Fee
			
					//User's name
					expectedtext = "test Patel";
					actualtext = receipt.Username.getText().trim();
					
					if(actualtext.contains(expectedtext))
					{
						System.out.println("-- Success !! User's name is correct i.e. " + actualtext );
						Reporter.log("-- Success !! User's name is correct i.e. " + actualtext );
						
					}
					else
					{
						System.out.println("-- Failure !! User's name is incorrect. Expected is : " + expectedtext + " , Actual is : " + actualtext );
						Reporter.log("-- Failure !! User's name is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
						AssertFailedCount++;
					}
					
					
					//Billing Address
					expectedtext = "14086 Proton Rd" + "\n" + "Dallas, TX 75244" + "\n" + "United States of America";
					actualtext = receipt.MailingAddress.getText().trim();
					
					if(actualtext.contains(expectedtext))
					{
						System.out.println("-- Success !! Billing Address is correct i.e. " + actualtext );
						Reporter.log("-- Success !! Billing Address is correct i.e. " + actualtext );
						
					}
					else
					{
						System.out.println("-- Failure !! Billing Address is incorrect. Expected is : " + expectedtext + " , Actual is : " + actualtext );
						Reporter.log("-- Failure !! Billing Address is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
						AssertFailedCount++;
					}
					
					
					//Total Amount
					expectedtext = "Total Amount: $19.50";
					actualtext = receipt.TotalAmount.getText().trim();
					
					if(actualtext.contains(expectedtext))
					{
						System.out.println("-- Success !! Total Amount is correct i.e. " + actualtext );
						Reporter.log("-- Success !! Total Amount is correct i.e. " + actualtext );
						
					}
					else
					{
						System.out.println("-- Failure !! Total Amount is incorrect. Expected is : " + expectedtext + " , Actual is : " + actualtext );
						Reporter.log("-- Failure !! Total Amount is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
						AssertFailedCount++;
					}
					
					
					//Course Fee
					expectedtext = "$19.50";
					actualtext = receipt.CourseFee.getText().trim();
					
					if(actualtext.contains(expectedtext))
					{
						System.out.println("-- Success !! Course Fee is correct i.e. " + actualtext );
						Reporter.log("-- Success !! Course Fee is correct i.e. " + actualtext );
						
					}
					else
					{
						System.out.println("-- Failure !! Course Fee is incorrect. Expected is : " + expectedtext + " , Actual is : " + actualtext );
						Reporter.log("-- Failure !! Course Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
						AssertFailedCount++;
					}
					
					
					
					//Total Fee
					expectedtext = "$19.50";
					actualtext = receipt.TotalFee.getText().trim();
					
					if(actualtext.contains(expectedtext))
					{
						System.out.println("-- Success !! Total Fee is correct i.e. " + actualtext );
						Reporter.log("-- Success !! Total Fee is correct i.e. " + actualtext );
						
					}
					else
					{
						System.out.println("-- Failure !! Total Fee is incorrect. Expected is : " + expectedtext + " , Actual is : " + actualtext );
						Reporter.log("-- Failure !! Total Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
	 * View Receipt - Stage 3 :  Verify on click to 'Get Your Proof of Home Study Voucher ' button , user is navigated to Course Complete page
	 * */ 
	@Test
	private void PaymentStage3_NavigateToCourseCompeletePage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 19 : View Receipt - Stage 3 :  Verify on click to 'Get Your Proof of Home Study Voucher ' button , user is navigated to Course Complete page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 19 : View Receipt - Stage 3 :  Verify on click to 'Get Your Proof of Home Study Voucher ' button , user is navigated to Course Complete page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ViewReceiptPage receipt = new ViewReceiptPage(driver);
		CourseCompletePage coursecomplete = new CourseCompletePage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			System.out.println(emailaddress);			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username, "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			register.WelcomeAboard.click();
			Thread.sleep(3000);
			
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();

			header.Profile.click();
			Thread.sleep(5000);

					
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
	
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
		
			
			expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button");
		
			payinfo.FirstName.sendKeys("test");
			payinfo.LastName.sendKeys("Patel");
			payinfo.CCNumber.sendKeys("4111111111111111");
			
			new Select(payinfo.ExpirationMonth).selectByVisibleText("03");
			new Select(payinfo.ExpirationYear).selectByVisibleText("2025");
			
			payinfo.BillingContactInfoCheckbox.click();
			Thread.sleep(4000);

			payinfo.ConfirmPaymentInformation.click();
			
			Thread.sleep(4000);
			
			payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
			
			Thread.sleep(10000);
			
	
		System.out.println("Step 6 : On 'View Receipt' page, clicking on 'Get Your Proof of Home Study Voucher ' button");
		Reporter.log("Step 6 : On 'View Receipt' page, clicking on 'Get Your Proof of Home Study Voucher ' button"); 
			
			receipt.GetYourProofofHomeStudyVoucher.click();
			Thread.sleep(4000);
			
			//Verifying Url
			String expectedtext = Constants.ApplicationURL_Hunter + "/course/course_complete/";
			String actualtext = driver.getCurrentUrl();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! user is navigated to Course Compelte page i.e. " + actualtext );
				Reporter.log("-- Success !! user is navigated to Course Compelte page i.e. " + actualtext );
				
				expectedtext = "You Have Completed the Arkansas Hunter Ed Course";
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
	
	
	
	/* Test 20: 
	 * Course Complete : Verify details on Course Complete page
	 * */ 
	@Test
	private void PaymentStage3_VerifyCourseCompeletePage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 20 : Course Complete : Verify details on Course Complete page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 20 : Course Complete : Verify details on Course Complete page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ViewReceiptPage receipt = new ViewReceiptPage(driver);
		CourseCompletePage coursecomplete = new CourseCompletePage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			System.out.println(emailaddress);			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username,  "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			register.WelcomeAboard.click();
			Thread.sleep(3000);
			
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();
			
			header.Profile.click();
			Thread.sleep(5000);

					
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);

		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
	
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
		
			
			expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button");
		
			payinfo.FirstName.sendKeys("test");
			payinfo.LastName.sendKeys("Patel");
			payinfo.CCNumber.sendKeys("4111111111111111");
			
			new Select(payinfo.ExpirationMonth).selectByVisibleText("03");
			new Select(payinfo.ExpirationYear).selectByVisibleText("2025");
			
			payinfo.BillingContactInfoCheckbox.click();
			Thread.sleep(4000);

			payinfo.ConfirmPaymentInformation.click();
			
			Thread.sleep(4000);
			
			payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
			
			Thread.sleep(10000);
			
	
		System.out.println("Step 6 : On 'View Receipt' page, clicking on 'Get Your Proof of Home Study Voucher ' button");
		Reporter.log("Step 6 : On 'View Receipt' page, clicking on 'Get Your Proof of Home Study Voucher ' button"); 
			
			receipt.GetYourProofofHomeStudyVoucher.click();
			Thread.sleep(4000);
			
			//Verifying Url
			String expectedtext = Constants.ApplicationURL_Hunter + "/course/course_complete";
			String actualtext = driver.getCurrentUrl();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! user is navigated to Course Compelte page i.e. " + actualtext );
				Reporter.log("-- Success !! user is navigated to Course Compelte page i.e. " + actualtext );
				
				expectedtext = "You Have Completed the Arkansas Hunter Ed Course";
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
			
			
		System.out.println("Step 7 : On 'View Receipt' page, verifying details");
		Reporter.log("Step 7 : On 'View Receipt' page, verifying details");
		
			
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
		
			// Verifying presence of 'After This Course' section
		
				expectedtext = "After This Quiz";
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
				}
			
				
			//Verifying congratulation message
				
				expectedtext = "Congratulations, you have successfully completed the Arkansas Hunter Ed Course. You may now download your Arkansas Online Course Completion Voucher or have it sent to your registered e-mail address.";
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
				
				expectedtext = "Download the Arkansas Online Course Completion Voucher";
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
				
				expectedtext = "E-mail the Arkansas Online Course Completion Voucher";
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
				
				
		/*	//Verifying presence of 'return to the course contents' links .
				
				expectedtext = "return to the course contents";
				actualtext = coursecomplete.ReturnToCourseContent.getText().trim();
				
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
				}*/
				
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
	
	
	
	
	/* Test 21: 
	 * Billing info is persisted when applying a coupon code
	 * */ 
	@Test
	private void BillingInfoPersistAfterCoupon_Hunt() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 21 : Billing info is persisted when applying a coupon code"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 21 : Billing info is persisted when applying a coupon code"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
	
			
			register.RegisterAsNewUser(username, "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
			String ContentPageUrl = driver.getCurrentUrl();
			
			header.Profile.click();
			Thread.sleep(5000);
				
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}

			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
			
			cerinfo.SelectGender("Male");
			
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
			
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
				
			expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
				
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
		System.out.println("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button");
			
			payinfo.FirstName.sendKeys("Automation");
			payinfo.LastName.sendKeys("Testing");
			payinfo.CCNumber.sendKeys("4111111111111111");
				
			new Select(payinfo.ExpirationMonth).selectByVisibleText("03");
			new Select(payinfo.ExpirationYear).selectByVisibleText("2017");
				
			payinfo.BillingContactInfoCheckbox.click();		
			Thread.sleep(4000);
			payinfo.CouponCode.sendKeys("disc12345");
			payinfo.ApplyCode.click();
			Thread.sleep(4000);
			
			
		System.out.println("Step 6 : Apply coupon code and verify Billing info is persisted when applying a coupon code");
		Reporter.log("Step 6 : Apply coupon code and verify Billing info is persisted when applying a coupon code");
				
			//Verify Billing Details persist (First Name, Last Name)
			
			String expectedtext = "Automation";
			String actualtext = payinfo.FirstName.getAttribute("value").trim();
						
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! Billing Info. persist i.e. " + actualtext );
				Reporter.log("-- Success !! Billing Info. persist i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Billing Info. NOT persist . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Billing Info. NOT persist . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			expectedtext = "Testing";
			actualtext = payinfo.LastName.getAttribute("value").trim();
						
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! Billing Info. persist i.e. " + actualtext );
				Reporter.log("-- Success !! Billing Info. persist i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Billing Info. NOT persist . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Billing Info. NOT persist . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
					
			
			//Verify CC details reset
			expectedtext = "";
			actualtext = payinfo.CCNumber.getAttribute("value").trim();
						
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! CC details reset.");
				Reporter.log("-- Success !! CC details reset.");
			}
			else
			{
				System.out.println("-- Failure !! CC details NOT reset.");
				Reporter.log("-- Failure !! CC details NOT reset.");
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
	
	
	// Payment Information - Stage 2 : Mastercard credit card number field should have a mastercard logo infront
	@Test
	private void PaymentStage2_MastercardLogo() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test : Payment Information - Stage 2 : Mastercard credit card number field should have a mastercard logo infront"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test : Payment Information - Stage 2 : Mastercard credit card number field should have a mastercard logo infront"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ProfileNewPage profile = new ProfileNewPage(driver);

		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			PageHeader header = new PageHeader(driver); 
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username, "January", "2", "1990", emailaddress,"clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent(register.WelcomeAboard))
			{
				register.WelcomeAboard.click();
				Thread.sleep(3000);
			}
			
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();

			header.Profile.click();
			Thread.sleep(5000);

					
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
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.EnterMailingAddress(addressline1, addressline2, city, state, zip, country, phonenumber);
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);

		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
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

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(8000);
			
			expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , Verify Mastercard logo is displayed or not" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , Verify Mastercard logo is displayed or not");
		
			payinfo.FirstName.sendKeys("test");
			payinfo.LastName.sendKeys("ke-testing");
			payinfo.CCNumber.sendKeys("5555555555554444");
			payinfo.BillingContactInfoCheckbox.click();
			Thread.sleep(2000);
			if(SeleniumFunc.IsElementPresent(payinfo.MastercardLogo))
			{
				System.out.println("-- Success !! Mastercard logo is displayed. " );
				Reporter.log("-- Success !! Mastercard logo is displayed. ");
			}
			else
			{
				System.out.println("-- Failure !! Mastercard logo is not displayed.");
				Reporter.log("-- Failure !! Mastercard logo is not displayed" );
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
