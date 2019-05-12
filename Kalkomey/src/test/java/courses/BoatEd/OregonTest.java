package courses.BoatEd;

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
import pageFactory.Courses.FinalExamPage;
import pageFactory.Courses.HomePage;
import pageFactory.Courses.KECourseAdminPage;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.PaymentInformationPage;
import pageFactory.Courses.PracticeExamPage;
import pageFactory.Courses.QuizPage;
import pageFactory.Courses.RegistrationPage;



import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class OregonTest 
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
	 * Oregon  - Verify State Agency name on /oregon page
	 * 
	 */ 
	@Test
	private void OR_VerifyAgencyName() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : oregon - Verify State Agency name on /oregon page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : oregon - Verify State Agency name on /oregon page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HomePage home = new HomePage(driver);
		PageHeader header = new PageHeader(driver);

		
		System.out.println("Step 1: Navigated to /oregon page and verifying State Agency name");
		Reporter.log("Step 1: Navigated to /oregon page and verifying State Agency name"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/oregon");
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/oregon");
				Thread.sleep(4000);
			}
		
			//verifying State Agency name
			String expectedtext = "Oregon State Marine Board";
			String actualtext = home.AgencyName_New.getText();
			System.out.println(actualtext);

			if(actualtext.contains(expectedtext) )
			{
				System.out.println("Success!! State Agency name is correct i.e. " + actualtext);
				Reporter.log("Success!! State Agency name is correct  i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! State Agency name is incorrect " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! State Agency name is incorrect " + "\n" + "Expected Text: "  
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
	
	
	/* Test 2: 
	 * oregon - Verify that study guide is accessible without registration
	 * */ 
	@Test
	private void OR_Navigate_To_StudyGuide_Page() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : oregon - Verify that study guide is accessible without registration"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : oregon - Verify that study guide is accessible without registration"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		
		System.out.println("Step 1: Navigated to /oregon page ");
		Reporter.log("Step 1: Navigated to /oregon page"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/oregon");
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/oregon");
				Thread.sleep(4000);
			}
	
		System.out.println("Step 2 : Verifying whether 'Study Guild' link is present on top header");
		Reporter.log("Step 2 : Verifying whether 'Study Guild' link is present on top header");
		
			try
			{
				String actualtext = header.StudyGuide_New.getText();
				String expectedtext = "Study Guide";
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! 'Study Guide' link is present on page header");
					Reporter.log("-- Success !! 'Study Guide' link is present on page header");
					
					System.out.println("Step 3 : Click on 'Study Guide' link");
					Reporter.log("Step 3 : Click on 'Study Guide' link");
						
						header.StudyGuide_New.click();
						Thread.sleep(3000);
						
						actualtext = driver.getCurrentUrl();
						expectedtext = Constants.ApplicationURL.substring(5) + "/oregon" + "/studyGuide/";
						System.out.println(expectedtext);
						
						if(actualtext.contains(expectedtext))
						{
							System.out.println("-- Success !! User is navigated to Study Guide page successfully");
							Reporter.log("-- Success !! User is navigated to Study Guide page successfully");
							
						}
						else
						{
							System.out.println("-- Failure !! User is NOT navigated to Study Guide page and navigated to :" +actualtext);
							Reporter.log("-- Failure !! User is NOT navigated to Study Guide page and navigated to :" +actualtext);
							AssertFailedCount++;
						}
						
				}
				else
				{
					System.out.println("-- Failure !! 'Study Guide' link is NOT present on page header");
					Reporter.log("-- Failure !! 'Study Guide' link is NOT present on page header");
					AssertFailedCount++;
				}
				
			}
			catch(Exception e)
			{
				System.out.println("--  Failure !! 'Study Guide' link is NOT present on page header");
				Reporter.log("--  Failure !! 'Study Guide' link is NOT present on page header");
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
	 * oregon  -  Student Requirements - Verify that user can pay with International Address
	 * */ 
	@Test
	private void OR_Stds_Req_International_Address() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : oregon  -  Student Requirements - Verify that user can pay with International Address"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : oregon  -  Student Requirements - Verify that user can pay with International Address"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		ContentsPage content = new ContentsPage(driver);
		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/oregon");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/oregon"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/oregon");
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/oregon");
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
			String username="oregon"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1980", emailaddress, emailaddress);
			register.ClickOnCreateAccount();
		
			Thread.sleep(8000);
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			header.Contents.click();
			Thread.sleep(4000);
	
			String ContentPageUrl = driver.getCurrentUrl();
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Fast Forward Pass");
			
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
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info with International Address, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			cerinfo.MailingAddressLine1.sendKeys("Mailing Address Line 1");
			cerinfo.MailingAddressLine2.sendKeys("Line2");
			cerinfo.MailingAddressCity.sendKeys("Pune");
			new Select(cerinfo.MailingAddressCountry).selectByVisibleText("India");
			cerinfo.MailingAddressRegionOrLocality.sendKeys("Maharastra");
			cerinfo.MailingAddressZip.sendKeys("12345");
			cerinfo.MailingAddressPhoneNumber.sendKeys("234-567-8910");
			Thread.sleep(10000);
			cerinfo.SelectHairColor("Brown");
			cerinfo.SelectEyeColor("Brown");
	
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
		
			
			expectedurl = Constants.ApplicationURL + "/register/payment/" ;
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


	/* Test 4: 
	 * oregon -  Student Requirements - Verify that minimum age limit for sign up is 12 years 
	 * */ 
	@Test
	private void OR_Stds_Req_AgeLimit() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : oregon -  Student Requirements - Verify that minimum age limit for sign up is 12 years "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : oregon -  Student Requirements - Verify that minimum age limit for sign up is 12 years  "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		RegistrationPage register = new RegistrationPage(driver);
		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/oregon ");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/oregon "); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/oregon");
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/oregon");
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
		System.out.println("Step 2 : Filling registration form (with Age < minimum age required) and clicking on 'Create Account' button");
		Reporter.log("Step 2 : Filling registration form (with Age < minimum age required) and clicking on 'Create Account' button"); 
		
			
			String username="OR"+ JH.GenerateRandomNumber();
			register.FillRegistrationForm(username, "piyush@143", "piyush@143", "test", "B", "Patel", "Jr.", "January", "1", "2014","testing@gmail.com", "testing@gmail.com");
			register.ClickOnCreateAccount();
	
			
		System.out.println("Step 4 : Verifying whether correct validation message is displayed or not");
		Reporter.log("Step 4 : Verifying whether correct validation message is displayed or not"); 
			
			Thread.sleep(10000);
			String ActualValidationMessage=SeleniumFunc.GetElementText("css", "section.row li").trim();
			System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage = "The minimum eligible age is " + "12"+ ".";
			
			if(ActualValidationMessage.equals(ExpectedValidationMessage))
			{
				
				System.out.println("Success !! message is correct'");
				Reporter.log("Success !! message is correct"); 
			}
			else
			{
				System.out.println("Failure !!message is incorrect");
				Reporter.log("Failure !! message is incorrect"); 
				AssertFailedCount++;
			}
			
		System.out.println("Step 5 : Filling registration form (with Age > minimum age required) and clicking on 'Create Account' button");
		Reporter.log("Step 5 : Filling registration form (with Age > minimum age required) and clicking on 'Create Account' button"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/oregon");
			header.Register_New.click();
			
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
			username="OR"+ JH.GenerateRandomNumber();
			register.FillRegistrationForm(username, "piyush@143", "piyush@143", "test", "B", "Patel", "Jr.", "January", "1", "2000","testing@gmail.com", "testing@gmail.com");
			register.ClickOnCreateAccount();
			
			Thread.sleep(8000);
			
		System.out.println("Step 6 : Verifying whether user is registered successfully or not");
		Reporter.log("Step 6 : Verifying whether user is registered successfully or not"); 
			
			
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


	/* Test 5: 
	 * oregon -  Verify the course fee 
	 * */ 
	@Test
	private void OR_VerifyFeeAndTaxes() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : oregon -  Verify the course fee "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : oregon -  Verify the course fee "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ContentsPage content = new ContentsPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "OR" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/oregon");
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/oregon");
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
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();
	
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Fast Forward Pass");
			
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
			cerinfo.MailingAddressLine1.sendKeys("Mailing Address Line 1");
			cerinfo.MailingAddressLine2.sendKeys("Line2");
			cerinfo.MailingAddressCity.sendKeys("Pune");
			new Select(cerinfo.MailingAddressCountry).selectByVisibleText("India");
			cerinfo.MailingAddressRegionOrLocality.sendKeys("Maharastra");
			cerinfo.MailingAddressZip.sendKeys("12345");
			cerinfo.MailingAddressPhoneNumber.sendKeys("234-567-8910");
			Thread.sleep(10000);
			cerinfo.SelectHairColor("Brown");
			cerinfo.SelectEyeColor("Brown");
	
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
	
	
			
			expectedurl = Constants.ApplicationURL + "/register/payment/" ;
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
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , , verfiying Course Fee" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , , verfiying Course Fee");
		
			
			//Verifying Boat Oregon Course Fee
			String expectedtext = "$29.50";
			String actualtext = payinfo.CourseFee_Fee.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Boat Oregon Course Fee  is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Boat Oregon Course Fee is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Boat Oregon Course Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Boat Oregon Course Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			//Verifying Permanent Boater Education Card  Fee
			expectedtext = "$10.50";
			actualtext = payinfo.CourseCard_Fee.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Permanent Boater Education Card Fee  is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Permanent Boater Education Card Fee is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Permanent Boater Education Card Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Permanent Boater Education Card Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
		
			
			//Verifying Course Total Fee
			expectedtext = "$40.00";
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

	
	/* Test 6: 
	 * oregon  - Verify the course has a Practice Exam
	 * */ 
	@Test
	private void OR_VerifyPraticeExam() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : oregon  - Verify the course has a Practice Exam"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : oregon  - Verify the course has a Practice Exam"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		ContentsPage content = new ContentsPage(driver);
		QuizPage quiz = new QuizPage(driver);
		PracticeExamPage practiceexam = new PracticeExamPage(driver);
		
		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/oregon");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/oregon"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/oregon");
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/oregon");
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user "); 
			
			String username="canada"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1990", emailaddress, emailaddress);
			register.ClickOnCreateAccount();
			
			Thread.sleep(8000);
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			header.Contents.click();
			Thread.sleep(4000);
	
			String ContentPageUrl = driver.getCurrentUrl();
			
		System.out.println("Step 3 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page");
		Reporter.log("Step 3 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Progress");
			keadmin.Progress_SelectAllUnitAndMarkAsComplete();
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

				
		System.out.println("Step 4 : Marking all Units/Quiz as completed" );
		Reporter.log("Step 4 : Marking all Units/Quiz as completed");
					
			for(int i=1;i<7;i++)
			{
				content.ContinueWhereYouLeftOff.click();
				Thread.sleep(4000);

				quiz.ClickOnNextButton();
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(Constants.AdminURL);
				keadmin.StudentSearchAndActivity(username, "Boat", "Quizzes");
				keadmin.Quizzes_PassAQuiz(i);
				SeleniumFunc.ToGoToUrl(ContentPageUrl);
				Thread.sleep(4000);

			}
		
	
		System.out.println("Step 5 : Navigating to Practice Exam page" );
		Reporter.log("Step 5 : Navigating to Practice Exam page");	
			
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

			
		System.out.println("Step 6 : Verifying UI elements on Practice Exam page" );
		Reporter.log("Step 6 : Verifying UI elements on Practice Exam page");
		
			//verifying page header
			if(practiceexam.PageTitle.getText().trim().equals("Practice Exam"))
			{
				System.out.println("-- Page header is correct i.e. Practice Exam"  );
				Reporter.log("-- Page header is correct i.e. Practice Exam");
			}
			else
			{
				System.out.println("-- Page header is incorrect"  );
				Reporter.log("-- Page header is incorrect");
				AssertFailedCount++;
			}
			
		
			
			//verifying 'Previous' button
			if(practiceexam.Previous.getText().trim().contains("Previous"))
			{
				System.out.println("-- 'Previous' button is present"  );
				Reporter.log("-- 'Previous' button is present");
			}
			else
			{
				System.out.println("-- 'Previous' button is not present"  );
				Reporter.log("-- 'Previous' button is not present ");
				AssertFailedCount++;
			}
			
			
			//verifying 'Take the Practice Exam' button
			if(practiceexam.TakePracticeExam.getText().trim().contains("Begin the Practice Exam →"))
			{
				System.out.println("-- 'Take the Practice Exam' button is present"  );
				Reporter.log("-- 'Take the Practice Examm' button is present");
			}
			else
			{
				System.out.println("-- 'Take the Practice Exam' button is not present"  );
				Reporter.log("-- 'Take the Practice Exam' button is not present ");
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
