package courses.HunterEd;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.CertificationInformationPage;
import pageFactory.Courses.ContentsPage;
import pageFactory.Courses.FinalExamPage;
import pageFactory.Courses.HomePage;
import pageFactory.Courses.KECourseAdminPage;
import pageFactory.Courses.LoginPage;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.PaymentInformationPage;
import pageFactory.Courses.ProfileNewPage;
import pageFactory.Courses.RegistrationNewPage;
import pageFactory.Courses.ViewReceiptPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class SpecialCases 
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
	 * Payment : Verify Info message for CC field (i.e.Unfortunately, we do not accept PayPal, gift cards, or preloaded debit or bank cards as payment at this time.)
	 * */ 
	@Test
	private void Payment_CCField_InfoText() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Payment : Verify Info message for CC field (i.e.Unfortunately, we do not accept PayPal, gift cards, or preloaded debit or bank cards as payment at this time.)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Payment : Verify Info message for CC field (i.e.Unfortunately, we do not accept PayPal, gift cards, or preloaded debit or bank cards as payment at this time.)"  + "\n" +
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
			
			register.RegisterAsNewUser(username,  "January", "2", "1990", emailaddress, "clarion@123", "clarion@123");
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
			SeleniumFunc.ClickOnElement("css", "ul[class='nav pull-right'] li:nth-of-type(1) a");
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
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
	
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
			
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(5000);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/register/payment/");
			expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
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
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , , verfiying Credit Card info text" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , , verfiying Credit Card info text");
		
			//verifying Credit Card info text
			
			String expectedtext = "Sorry, we do not accept PayPal, gift cards, or prepaid cards.";
			//String expectedtext = "Unfortunately, we do not accept PayPal, gift cards, or preloaded debit or bank cards as payment at this time.";
			String actualtext = payinfo.CreditCardAlertText.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Credit Card info text is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Credit Card info text is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Credit Card info text is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Credit Card info text is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
	 * Payment - AL > Only display fees paid by student
	 * */ 
	@Test
	private void Payment_AL_FeesPaid() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Payment - AL > Only display fees paid by student"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Payment - AL > Only display fees paid by student"  + "\n" +
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
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/alabama");
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(1000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(100);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/alabama");
				Thread.sleep(1000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.FillRegistrationForm(username,  "January", "2", "1990", emailaddress, "clarion@123", "clarion@123");
			register.StateResidencyCheckbox.click();
			Thread.sleep(4000);

			register.ClickOnCreateAccount();
			Thread.sleep(8000);
			
			register.SelectGender("Male");
			register.EnterSSNNumber("254545210");
			register.SelectEtinicity("Asian");
			
			register.ClickOnCreateAccount();
			Thread.sleep(5000);
			
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
				
		System.out.println("Step 2 : Editing all fields (i.e. Gender, , Email Address, Mailing Address) and clicking on 'Update Profile' button");
		Reporter.log("Step 2 : Editing all fields (i.e. Gender, , Email Address, Mailing Address) and clicking on 'Update Profile' button");
						
			String gender= "Male";
			String email = "temp@testing.com";
			String addressline1 = "3013 12TH AVE N";
			String city= "BIRMINGHAM";
			String SSN = "125451245";
			String zip="35234";
			String phonenumber="234-567-8910";
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterSSN(SSN);
			profile.EnterEmailAddress(email);
			profile.MailingAddressLine1.sendKeys(addressline1);
			profile.MailingAddressCity.sendKeys(city);
			profile.MailingAddressZip.sendKeys(zip);
			profile.MailingAddressPhoneNumber.sendKeys(phonenumber);
			if(profile.PhysicalAddressCheckbox.isSelected())
			{
				profile.PhysicalAddressCheckbox.click();
			}
			Thread.sleep(2000);
			profile.ClickOnUpdateProfileButton();			
			Thread.sleep(8000);

		System.out.println("Step 3 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 3 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
			//Go back to Contents page
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			SeleniumFunc.ClickOnElement("css", "ul[class='nav pull-right'] li:nth-of-type(1) a");
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
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Male");
			cerinfo.EnterSSN("123-45-6789");
			cerinfo.SelectEthinicity("Asian");
			

			cerinfo.MailingAddressLine1.clear();
			cerinfo.MailingAddressLine1.sendKeys("PO Box 2000");
			cerinfo.MailingAddressLine2.clear();
			cerinfo.MailingAddressLine2.sendKeys("");
			
			cerinfo.MailingAddressCity.clear();
			cerinfo.MailingAddressCity.sendKeys("Monroeville");
			
			
			cerinfo.MailingAddressZip.clear();
			cerinfo.MailingAddressZip.sendKeys("36461");
			
		
			
			cerinfo.MailingAddressPhoneNumber.clear();
			cerinfo.MailingAddressPhoneNumber.sendKeys("234-567-8910");
		
			
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
			
			
			expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(5000);

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
	
			
		System.out.println("Step 5 : On Payment Information page - Stage 2 , , verfiying Course Fees information" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , , verfiying Course Fees information");
		
			
			//Verifying Course Fee
			String expectedtext = "$24.50";
			String actualtext = payinfo.CourseFee_Fee_Hunter.getText().trim();
			
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
	 * Verify You Tube sharing button on course page (/taxas)
	 * */ 
	@Test
	private void VerifyYouTubeButton() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify You Tube sharing button on course page (/taxas)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify You Tube sharing button on course page (/taxas)"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		
		System.out.println("Step 1: Navigate to /texas page and clicking on YouTube button ");
		Reporter.log("Step 1: Navigate to /texas page and clicking on YouTube button"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/texas");
			SeleniumFunc.ClickOnElement("css", "a.yt-img");
			Thread.sleep(5000);
			String expectedurl = "https://www.youtube.com/user/kalkomey";
			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'YouTube' button is working as expected ");
				Reporter.log("-- Success !! 'YouTube' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'YouTube' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'YouTube' button , user is navigated to : " + driver.getCurrentUrl());
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
	 * Verify that 'View more on Twitter' button on course page (/taxas)
	 * */ 
	@Test
	private void VerifyTwitterButton() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify that 'View more on Twitter' button on course page (/taxas)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify that 'View more on Twitter' button on course page (/taxas)"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HomePage home = new HomePage(driver);
		
		System.out.println("Step 1: Navigate to /texas page and clicking on 'View more on Twitter' button button ");
		Reporter.log("Step 1: Navigate to /texas page and clicking on 'View more on Twitter' button button"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/texas");
			Thread.sleep(4000);
			//SeleniumFunc.ClickOnElement("css", "#content-promo a");
			home.ClickOnTwitterButton();
			
			for (String winHandle : driver.getWindowHandles()) 
			{
			    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			
			Thread.sleep(5000);
			String expectedurl = "https://twitter.com/hunter_ed";
			String actualurl = driver.getCurrentUrl();
			driver.close();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'View more on Twitter' button button is working as expected ");
				Reporter.log("-- Success !! 'View more on Twitter' button button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'View more on Twitter' button button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'View more on Twitter' button button , user is navigated to : " + driver.getCurrentUrl());
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
	 * Verify presence of 'Studying and Completing ' &  'A Comprehensive Course' sections on course page (/taxas)
	 * */ 
	@Test
	private void VerifySectionsOnCoursePage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify presence of 'Studying and Completing ' &  'A Comprehensive Course' sections on course page (/taxas)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify presence of 'Studying and Completing ' &  'A Comprehensive Course' sections on course page (/taxas)"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		
		System.out.println("Step 1: Navigate to /texas page and Verifying presence of 'Studying and Completing ' &  'A Comprehensive Course' sections  ");
		Reporter.log("Step 1: Navigate to /texas page and Verifying presence of 'Studying and Completing ' &  'A Comprehensive Course' sections  "); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/texas");
			
			//Verifying "Studying and Completing the Texas State-Approved Online Course" section title 
			
			String expectedtext = "Comprehensive Instruction in Texas Hunting Safety Education";
			String actualtext = SeleniumFunc.GetElementText("css","#marketing-print h3").trim();
			System.out.println(actualtext);

			if(actualtext.contains(expectedtext) )
			{
				System.out.println("Success!! Header has correct for text 'Comprehensive Instruction' i.e. " + actualtext);
				Reporter.log("Success!! Header has correct for text 'Comprehensive Instruction' i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Header has incorrect for 'Comprehensive Instruction' " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Header has incorrect for 'Comprehensive Instruction' " + "\n" + "Expected Text: "  
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
	
	
	

	
	/* Test 7: 
	 * Verify that user can share hunter-ed blog posts to social media feeds (twitter)
	 * */ 
	@Test
	private void Verify_Blog_Twitter_Facebook_sharing() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Verify that user can share hunter-ed blog posts to social media feeds (twitter)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Verify that user can share hunter-ed blog posts to social media feeds (twitter)"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		
		System.out.println("Step 1: Navigate to http://www.hunter-ed.com/blog/ page");
		Reporter.log("Step 1: Navigate to http://www.hunter-ed.com/blog/ page"); 
			
			SeleniumFunc.ToGoToUrl("http://www.hunter-ed.com/blog/");
			Thread.sleep(5000);
			driver.navigate().refresh();
			Thread.sleep(5000);
			
		System.out.println("Step 3: Click on Twitter button for Post sharing");
		Reporter.log("Step 3: Click on Twitter button for Post sharing");	
		
			/*WebElement ele = driver.findElement(By.id("twitter-widget-0"));*/
			WebElement ele = driver.findElement(By.cssSelector("section > div span:nth-of-type(2) iframe"));
			driver.switchTo().frame(ele);
			SeleniumFunc.ClickOnElement("id", "b");
			driver.switchTo().defaultContent();
			
			for (String winHandle : driver.getWindowHandles()) 
			{
			    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			
			Thread.sleep(5000);
			String expectedurl = "https://twitter.com/intent/tweet?";
			String actualurl = driver.getCurrentUrl();
			driver.close();
		
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! User can share post on Twitter");
				Reporter.log("--- Success !! User can share post on Twitter");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Twitter' button button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Twitter' button button , user is navigated to : " + driver.getCurrentUrl());
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
	 * Verify that user can land on page with the Powder Hook maps app
	 * */ 
	@Test
	private void VerifyPowderHookMapsPage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Verify that user can land on page with the Powder Hook maps app"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Verify that user can land on page with the Powder Hook maps app"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		
		System.out.println("Step 1: Navigating to page with the Powder Hook maps app i.e. /places-to-hunt/");
		Reporter.log("Step 1: Step 1: Navigating to page with the Powder Hook maps app i.e. /places-to-hunt/ "); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/places-to-hunt/");
			
			
			//Verifying whether user is navigated to /places-to-hunt/ successfully or not
			
			String expectedtext = Constants.ApplicationURL_Hunter + "/places-to-hunt/";
			Thread.sleep(5000);
			String actualtext = driver.getCurrentUrl();
			System.out.println(actualtext);

			if(actualtext.contains(expectedtext) )
			{
				System.out.println("Success!! User is navigated to /places-to-hunt/ successfully i.e. " + actualtext);
				Reporter.log("Success!! User is navigated to /places-to-hunt/ successfully  i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! User is NOT navigated to /places-to-hunt/ " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! User is NOT navigated to /places-to-hunt/ " + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			WebElement ele = driver.findElement(By.cssSelector("div#main iframe"));
			driver.switchTo().frame(ele);
			
			// Verifying "Powderhook" logo 
			
			expectedtext = "Powderhook";
			actualtext = SeleniumFunc.GetAttributeValue("id", "ph_logo", "title").trim();
			System.out.println(actualtext);

			if(actualtext.contains(expectedtext) )
			{
				System.out.println("Success!! 'Powderhook' logo is present  i.e. " + actualtext);
				Reporter.log("Success!! 'Powderhook' logo is present  i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! 'Powderhook' logo is NOT present " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !!'Powderhook' logo is NOT present " + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
				
				AssertFailedCount++;
				
			}
				
			driver.switchTo().frame(0);
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
	 * Help procrastinating students get registered
	 * */ 
	@Test(dataProvider="ProcrastinatingUser",dataProviderClass=utility.TestNG.class)
	private void HelpProcrastinatingUser(String username, String password) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Help procrastinating students get registered"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 : Help procrastinating students get registered"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		
		System.out.println("Step 1: Login to Hunter-Ed application as a user whose couse is not active");
		Reporter.log("Step 1: Login to Hunter-Ed application as a user whose couse is not active "); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/site/login");
			login.EnterUsername(username);
			login.EnterPassword(password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
			
		System.out.println("Step 2: Verifying 'Course Not Active' page title and 'Register Now' button");
		Reporter.log("Step 2: Verifying 'Course Not Active' page title and 'Register Now' button");
		
			//Verifying 'Course Not Active' page title 
			
			String expectedtext = "Course Not Active";
			String actualtext = SeleniumFunc.GetElementText("css", ".page-header>h1");
			System.out.println(actualtext);

			if(actualtext.contains(expectedtext) )
			{
				System.out.println("Success!!  Couse is inactive  i.e. " + actualtext);
				Reporter.log("Success!! Couse is inactive    i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Couse is active   " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Couse is active  " + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			
			//Verifying 'Register Now' button
			
		
			expectedtext = "Register Now";
			actualtext = SeleniumFunc.GetElementText("css", "#next_text a");
			System.out.println(actualtext);

			if(actualtext.contains(expectedtext) )
			{
				System.out.println("Success!! 'Register Now' button is present  i.e. " + actualtext);
				Reporter.log("Success!! 'Register Now' button is present  i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! 'Register Now' button is NOT present " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! 'Register Now' button is NOT present " + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
				
		
		System.out.println("Step 3: click on 'Register Now' button");
		Reporter.log("Step 3: click on 'Register Now' button");
		
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(5000);
			expectedtext = Constants.ApplicationURL_Hunter + "/register/create";
			actualtext = driver.getCurrentUrl();
			System.out.println(actualtext);

			if(actualtext.contains(expectedtext) )
			{
				System.out.println("Success!! user is navigated to registration page  i.e. " + actualtext);
				Reporter.log("Success!! user is navigated to registration page i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! user is NOT navigated to registration page " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! user is NOT navigated to registration page " + "\n" + "Expected Text: "  
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
	
	
	/* Test 10: 
	 * Test invalid zip code for south carolina user
	 * */ 
	@Test
	private void SC_TestInvalidZip() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : Test invalid zip code for south carolina user"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10 : Test invalid zip code for south carolina user"  + "\n" +
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
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/southcarolina");
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/southcarolina");
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.FillRegistrationForm(username,  "January", "2", "1990", emailaddress, "clarion@123", "clarion@123");
			register.ClickOnStateResidencyCheckbox();
			Thread.sleep(4000);

			register.ClickOnCreateAccount();
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
			String addressline1 = "816 Bull St";
			String city= "Columbia";
			String zip="29208";
			String phonenumber="234-567-8910";
					
			profile.EnterFirstName("Test");
			profile.EnterLastName("Ke-Testing");
			profile.SelectGender(gender);
			profile.EnterEmailAddress(email);
			profile.MailingAddressLine1.sendKeys(addressline1);
			profile.MailingAddressCity.sendKeys(city);
			profile.MailingAddressZip.sendKeys(zip);
			profile.MailingAddressPhoneNumber.sendKeys(phonenumber);
			SeleniumFunc.ClickOnElement("css", "#differentaddress");
			Thread.sleep(2000);
		
			profile.ClickOnUpdateProfileButton();			
			
			Thread.sleep(8000);

		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Hunter", "Fast Forward Pass");
			
			//Go back to Contents page
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(5000);
			SeleniumFunc.ClickOnElement("css", "ul[class='nav pull-right'] li:nth-of-type(1) a");
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
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  adding invalid Zip code and verifying address validation message" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  adding invalid Zip code and verifying address validation message");		
		
			cerinfo.SelectGender("Female");
			cerinfo.MailingAddressLine1.clear();
			cerinfo.MailingAddressLine1.sendKeys("2905 Trotter Road");
			cerinfo.MailingAddressCity.clear();
			cerinfo.MailingAddressCity.sendKeys("Hopkins");
			cerinfo.MailingAddressZip.clear();
			cerinfo.MailingAddressZip.sendKeys("75244");
			cerinfo.MailingAddressPhoneNumber.clear();
			cerinfo.MailingAddressPhoneNumber.sendKeys("234-567-8910");
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(6000);
			
			String expectedtext = 
					"We had trouble verifying your mailing address. If the suggested address below is correct, please click " + "\"" + "use this address." + "\"" + " If this is not correct, please contact customer service at info@kalkomey.com." + "\n" + 
					"We found:" + "\n" + 
					"2905 TROTTER RD" + "\n" + 
					"HOPKINS, SC 29061" + "\n" + 
					"Use this Address" + "\n" + 
					"You typed:" + "\n" + 
					"2905 Trotter Road" + "\n" + 
					"Hopkins, SC 75244";
			
			String actualtext = cerinfo.Address_Alert.getText().trim();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! validaiton message is correct i.e. " + actualtext );
				Reporter.log("-- Success !! validaiton message is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! validaiton message is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! validaiton message is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
		
			cerinfo.MailingAddressZip.clear();
			cerinfo.MailingAddressZip.sendKeys("29061");
			
			driver.findElement(By.cssSelector("div.alert a")).click();
			Thread.sleep(10000);
			
			
			expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(5000);

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
			Thread.sleep(5000);

			payinfo.ConfirmPaymentInformation.click();
			
			Thread.sleep(4000);
			
			payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
			
			Thread.sleep(10000);
			
		
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
	
}
