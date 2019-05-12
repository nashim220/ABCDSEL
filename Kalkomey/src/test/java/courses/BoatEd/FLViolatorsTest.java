package courses.BoatEd;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.ContentsPage;
import pageFactory.Courses.KECourseAdminPage;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.PaymentInformationPage;
import pageFactory.Courses.PracticeExamPage;
import pageFactory.Courses.QuizPage;
import pageFactory.Courses.RegistrationPage;
import pageFactory.Courses.ViewReceiptPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class FLViolatorsTest 
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
	
/*
	 Test 1: 
	 * FL Violators  - Verify State Agency name on /floridaviolator page
	 *  
	@Test
	private void FLV_VerifyAgencyName() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : FL Violators  - Verify State Agency name on /floridaviolator page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : FL Violators  - Verify State Agency name on /floridaviolator page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HomePage home = new HomePage(driver);
		PageHeader header = new PageHeader(driver);

		
		System.out.println("Step 1: Navigated to /floridaviolator page and verifying State Agency name");
		Reporter.log("Step 1: Navigated to /floridaviolator page and verifying State Agency name"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
				Thread.sleep(4000);
			}
		
			//verifying State Agency name
			String expectedtext = "Florida Fish and Wildlife Conservation Commission";
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
	
	/* Test 2: 
	 * FL Violators  - Verify that /floridaviolator is a timed course & user need to move in a sequence to take a course
	 * */ 
	@Test
	private void FLV_TimedCourseVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : FL Violators  - Verify that /floridaviolator is a timed course & user need to move in a sequence to take a course"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : FL Violators  - Verify that /floridaviolator is a timed course & user need to move in a sequence to take a course"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ContentsPage content = new ContentsPage(driver);
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ViewReceiptPage receipt = new ViewReceiptPage(driver);
		
		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
				Thread.sleep(4000);

			}
			
			header.Register_Florida.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
	
			
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
		
			String username="payupfront"+ JH.GenerateRandomNumber();
			System.out.println(username);
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1980", emailaddress, emailaddress);
			
			register.SelectGender("Male");
			register.Citation_Ticket.sendKeys("123");
			//register.EnterMailingAddress("Mailing Address Line 1", "Line2", "New York", "Florida", "12345", "United States of America", "234-567-8910");
			register.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
			register.ClickOnCreateAccount();
			
			Thread.sleep(10000);

			if(SeleniumFunc.IsElementPresent("css", "a[href='/site/logout']"))
				{
					
					System.out.println("Success !! 'Save & Log Out' link is present '");
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
				
				System.out.println("Success !! Username is correct'");
				Reporter.log("Success !! Username is correct"); 
			}
			else
			{
				System.out.println("Failure !!Username is incorrect");
				Reporter.log("Failure !! Username is incorrect"); 
				AssertFailedCount++;
			}
			

		System.out.println("Step 3 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button" );
		Reporter.log("Step 3 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button");
		
			payinfo.FirstName.sendKeys("test");
			payinfo.LastName.sendKeys("Patel");
			payinfo.CCNumber.sendKeys("4111111111111111");
			
			new Select(payinfo.ExpirationMonth).selectByVisibleText("03");
			new Select(payinfo.ExpirationYear).selectByVisibleText("2019");
			
			payinfo.BillingContactInfoCheckbox.click();
			payinfo.ConfirmPaymentInformation.click();
			
			Thread.sleep(4000);
			
			payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
			
			Thread.sleep(10000);
			
	
		System.out.println("Step 4 : On 'View Receipt' page, clicking on 'Start Your Course 'button");
		Reporter.log("Step 4 : On 'View Receipt' page, clicking on 'Start Your Course ' button"); 
			
			receipt.GetYourProofofHomeStudyVoucher.click();
			Thread.sleep(4000);
			
			//Verifying Url
			String expectedtext = Constants.ApplicationURL + "/violator/course/102010/";
			String actualtext = driver.getCurrentUrl();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! user is navigated to Contents page i.e. " + actualtext );
				Reporter.log("-- Success !! user is navigated to Contents  page i.e. " + actualtext );
				
				expectedtext = "Florida Safe Boating Course for Violators";
				actualtext = content.PageHeader.getText().trim();
				
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! Page title is correct on Contents page i.e. " + actualtext );
					Reporter.log("-- Success !! Page title is correct on Contents page i.e. " + actualtext );
				}
				else
				{
					System.out.println("-- Failure !! Page title is incorrect on Contents page . Expected is : " + expectedtext + " , Actual is : " + actualtext );
					Reporter.log("-- Failure !! Page title is incorrect on Contents page. Expected is : " + expectedtext + " , Actual is : " + actualtext);
					AssertFailedCount++;
				}
				
			}
			else
			{
				System.out.println("-- Failure !! user is NOT navigated to Contents page . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !!user is NOT navigated to Contents page . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 5 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button");
		Reporter.log("Step 5 : Expanding Unit 1, Expanding Topic 1 , clicking on 'Start Here' button"); 
			
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

		System.out.println("Step 6 : Clicking on 'I Understand' and 'Next','Got it' buttons on Timer pop up ");
		Reporter.log("Step 6 : Clicking on 'I Understand' and 'Next', 'Got it' buttons on Timer pop up"); 
	
		if(SeleniumFunc.IsElementPresent(content.IntroModal))
		{
			content.IntroModal.click();
			Thread.sleep(4000);
		}
			
		System.out.println("Step 7 : Verifying whether timer is displayed on 'Next' button");
		Reporter.log("Step 7 : Verifying whether timer is displayed on 'Next' button"); 
		
		
				/*String ExpectedText = "Remaining";
				String ActualText= SeleniumFunc.GetElementText("css", "#timer_space_remaining").trim();
				System.out.println(ActualText);*/
				
	
				if(SeleniumFunc.IsElementPresent("css", "#timer_space_remaining"))
				{
					System.out.println("Success !! Timer is displayed");
					Reporter.log("Success !! Timer is displayed");
			
				}
				else
				{
					System.out.println("Failure !! Timer is not displayed" );
					Reporter.log("Failure !! Timer is not displayed");
	
					AssertFailedCount++;				
				}
					
				
				
		System.out.println("Step 8 : Navigating back to Contents page and Expanding Unit 1, Expanding Topic 3 , clicking on Page 1 link");
		Reporter.log("Step 8 : Navigating back to Contents page and Expanding Unit 1, Expanding Topic 3 , clicking on Page 1 link"); 
		if(SeleniumFunc.IsElementPresent(content.IntroModal))
		{
			content.IntroModal.click();
			Thread.sleep(4000);
		}	
			header.Contents.click();						Thread.sleep(4000);

			try
			{
			content.Untimed_GetUnitHrefLink(1, 3, 1);
			Thread.sleep(4000);

			content.Untimed_ClickOnUnitTopicPageLink(1,3,1);
			Thread.sleep(4000);

			System.out.println("Failure !! User is navigated to Unit 1 > Topic 3 > Page 1");
			Reporter.log("Failure !! User is navigated to Unit 1 > Topic 3 > Page 1");
			AssertFailedCount++;
			
			}
			catch(Exception e)
			{
				System.out.println("Success !! User is NOT navigated to Unit 1 > Topic 3 > Page 1" );
				Reporter.log("Success !! User is NOT navigated to Unit 1 > Topic 3 > Page 1");		
						
			}
			
		System.out.println("Step 9 : Navigating back to Contents page and Expanding Unit 1, Expanding Topic 1 , clicking on Page 2 link");
		Reporter.log("Step 9 : Navigating back to Contents page and Expanding Unit 1, Expanding Topic 1 , clicking on Page 2 link"); 
	
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
			header.Contents.click();						Thread.sleep(4000);

			try
			{
				String expectedurl = content.Timed_GetUnitHrefLink(1, 1, 2)+"/";
				content.ContinueWhereYouLeftOff.click();
				Thread.sleep(4000);

				Thread.sleep(5000);
				String Actualurl= driver.getCurrentUrl();
				System.out.println(Actualurl);
				
	
				if(Actualurl.contains(expectedurl))
				{
					System.out.println("Success !! User is navigated to Unit 1 > Topic 1 > Page 2" );
					Reporter.log("Success !! User is navigated to Unit 1 > Topic 1 > Page 2");		
								
				}
				else
				{
					System.out.println("Failure !! User is NOT navigated to Unit 1 > Topic 1 > Page 2\nexpected:"+expectedurl+"\nActual:"+Actualurl);
					Reporter.log("Failure !! User is NOT navigated to Unit 1 > Topic 1 > Page 2\nexpected:"+expectedurl+"\nActual:"+Actualurl);
					AssertFailedCount++;
				}
			

			}
			catch(Exception e)
			{
				System.out.println("Failure !! User is NOT navigated to Unit 1 > Topic 1 > Page 1");
				Reporter.log("Failure !! User is NOT navigated to Unit 1 > Topic 1 > Page 1");
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
	 * FL Violators -  Student Requirements - Verify that user can sign up with International Address
	 * */ 
	@Test
	private void FLV_Stds_Req_International_Address() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : FL Violators -  Student Requirements - Verify that user can sign up with International Address"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : FL Violators -  Student Requirements - Verify that user can sign up with International Address"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		PageHeader header = new PageHeader(driver);

		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
				Thread.sleep(4000);
			}
			
			header.Register_Florida.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
		System.out.println("Step 2 : Register as a New user with International Address");
		Reporter.log("Step 2 : Register as a New user with International Address"); 
			
			

			String username="payupfront"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1980", emailaddress, emailaddress);
			register.SelectGender("Male");
			register.Citation_Ticket.sendKeys("123");
			register.MailingAddressLine1.sendKeys("Mailing Address Line 1");
			register.MailingAddressLine2.sendKeys("Line2");
			register.MailingAddressCity.sendKeys("Pune");
			new Select(register.MailingAddressCountry).selectByVisibleText("India");
			register.MailingAddressRegionOrLocality.sendKeys("Maharastra");
			register.MailingAddressZip.sendKeys("12345");
			register.MailingAddressPhoneNumber.sendKeys("234-567-8910");
			register.ClickOnCreateAccount();
			
			Thread.sleep(10000);

			if(SeleniumFunc.IsElementPresent("css", "a[href='/site/logout']"))
				{
					
					System.out.println("Success !! 'Save & Log Out' link is present '");
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
				
				System.out.println("Success !! Username is correct'");
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
	 * FL Violators -  Student Requirements - Verify that no age limit for sign up 
	 * */ 
	@Test
	private void FLV_Stds_Req_AgeLimit() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : FL Violators -  Student Requirements - Verify that no age limit for sign up "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : FL Violators -  Student Requirements - Verify that no age limit for sign up "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		PageHeader header = new PageHeader(driver);

		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
				Thread.sleep(4000);
			}
			
			header.Register_Florida.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
	
			
		System.out.println("Step 2 : Register as a New user with DOB = 1 Jan 2014");
		Reporter.log("Step 2 : Register as a New user with DOB = 1 Jan 2014"); 
			
			

			String username="payupfront"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "2014", emailaddress, emailaddress);
			register.SelectGender("Male");
			register.Citation_Ticket.sendKeys("123");
			register.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			register.ClickOnCreateAccount();
			
			Thread.sleep(10000);

			if(SeleniumFunc.IsElementPresent("css", "a[href='/site/logout']"))
				{
					
					System.out.println("Success !! 'Save & Log Out' link is present '");
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
				
				System.out.println("Success !! Username is correct'");
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
	 * FL Violators -  Verify the course fee and taxes
	 * */ 
	@Test
	private void FLV_VerifyFee() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : FL Violators -  Verify the course fee and taxes"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : FL Violators -  Verify the course fee and taxes"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		PageHeader header = new PageHeader(driver);

		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
				Thread.sleep(4000);
			}
			
			header.Register_Florida.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
	
			
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
			
		

			String username="payupfront"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1980", emailaddress, emailaddress);
			
			register.SelectGender("Male");
			register.Citation_Ticket.sendKeys("123");
			//register.EnterMailingAddress("Mailing Address Line 1", "Line2", "New York", "Florida", "12345", "United States of America", "234-567-8910");
			register.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			register.ClickOnCreateAccount();
			
			Thread.sleep(10000);

			if(SeleniumFunc.IsElementPresent("css", "a[href='/site/logout']"))
				{
					
					System.out.println("Success !! 'Save & Log Out' link is present '");
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
				
				System.out.println("Success !! Username is correct'");
				Reporter.log("Success !! Username is correct"); 
			}
			else
			{
				System.out.println("Failure !!Username is incorrect");
				Reporter.log("Failure !! Username is incorrect"); 
				AssertFailedCount++;
			}
			

			//Verifying Course Fee
			String expectedtext = "$49.95";
			String actualtext = payinfo.CourseFee_Fee.getText().trim();
			
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
			expectedtext = "$49.95";
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
	 * FL Violators  - Verify the course has a Practice Exam
	 * */ 
	@Test
	private void FLV_PracticeExam() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : FL Violators  - Verify the course has a Practice Exam"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : FL Violators  - Verify the course has a Practice Exam"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		ContentsPage content = new ContentsPage(driver);
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ViewReceiptPage receipt = new ViewReceiptPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		PracticeExamPage practiceexam = new PracticeExamPage(driver);
		QuizPage quiz = new QuizPage(driver);
		
		PageHeader header = new PageHeader(driver);

		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
				Thread.sleep(4000);
			}
			
			header.Register_Florida.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
			
			

			String username="payupfront"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1980", emailaddress, emailaddress);
			
			register.SelectGender("Male");
			register.Citation_Ticket.sendKeys("123");
			//register.EnterMailingAddress("Mailing Address Line 1", "Line2", "New York", "Florida", "12345", "United States of America", "234-567-8910");
			register.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			register.ClickOnCreateAccount();
			
			Thread.sleep(10000);

			if(SeleniumFunc.IsElementPresent("css", "a[href='/site/logout']"))
				{
					
					System.out.println("Success !! 'Save & Log Out' link is present '");
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
				
				System.out.println("Success !! Username is correct'");
				Reporter.log("Success !! Username is correct"); 
			}
			else
			{
				System.out.println("Failure !!Username is incorrect");
				Reporter.log("Failure !! Username is incorrect"); 
				AssertFailedCount++;
			}
			

		System.out.println("Step 3 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button" );
		Reporter.log("Step 3 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button");
		
			payinfo.FirstName.sendKeys("test");
			payinfo.LastName.sendKeys("Patel");
			payinfo.CCNumber.sendKeys("4111111111111111");
			
			new Select(payinfo.ExpirationMonth).selectByVisibleText("03");
			new Select(payinfo.ExpirationYear).selectByVisibleText("2019");
			
			payinfo.BillingContactInfoCheckbox.click();
			payinfo.ConfirmPaymentInformation.click();
			
			Thread.sleep(4000);
			
			payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
			
			Thread.sleep(10000);
			
	
		System.out.println("Step 4 : On 'View Receipt' page, clicking on 'Start Your Course 'button");
		Reporter.log("Step 4 : On 'View Receipt' page, clicking on 'Start Your Course ' button"); 
			
			receipt.GetYourProofofHomeStudyVoucher.click();
			Thread.sleep(4000);
			
			//Verifying Url
			String expectedtext = Constants.ApplicationURL + "/violator/course/102010/";
			String actualtext = driver.getCurrentUrl();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! user is navigated to Contents page i.e. " + actualtext );
				Reporter.log("-- Success !! user is navigated to Contents  page i.e. " + actualtext );
				
				expectedtext = "Florida Safe Boating Course for Violators";
				actualtext = content.PageHeader.getText().trim();
				
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! Page title is correct on Contents page i.e. " + actualtext );
					Reporter.log("-- Success !! Page title is correct on Contents page i.e. " + actualtext );
				}
				else
				{
					System.out.println("-- Failure !! Page title is incorrect on Contents page . Expected is : " + expectedtext + " , Actual is : " + actualtext );
					Reporter.log("-- Failure !! Page title is incorrect on Contents page. Expected is : " + expectedtext + " , Actual is : " + actualtext);
					AssertFailedCount++;
				}
				
			}
			else
			{
				System.out.println("-- Failure !! user is NOT navigated to Contents page . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !!user is NOT navigated to Contents page . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
			//String ContentPageUrl = header.Contents.getAttribute("href");
			String ContentPageUrl = driver.getCurrentUrl();

			
		System.out.println("Step 5 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page");
		Reporter.log("Step 5 : Navigating on KE Cousers Admin and marking All Units as complete. Navigating back to Contents page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Progress");
			keadmin.Progress_SelectAllUnitAndMarkAsComplete();
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

				
		System.out.println("Step 6 : Marking all Units/Quiz as completed" );
		Reporter.log("Step 6 : Marking all Units/Quiz as completed");
					
			for(int i=1;i<16;i++)
			{
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("scroll(0, 200);");	
				content.ContinueWhereYouLeftOff.click();
				Thread.sleep(3000);
				quiz.ClickOnNextButton();
				Thread.sleep(4000);

				SeleniumFunc.ToGoToUrl(Constants.AdminURL);
				keadmin.StudentSearchAndActivity(username, "Boat", "Quizzes");
				keadmin.Quizzes_PassAQuiz(i);
				SeleniumFunc.ToGoToUrl(ContentPageUrl);
				Thread.sleep(4000);

			}
		
	
		System.out.println("Step 7 : Navigating to Practice Exam page" );
		Reporter.log("Step 7 : Navigating to Practice Exam page");	
			
			content.ContinueWhereYouLeftOff.click();
			Thread.sleep(4000);

			
		System.out.println("Step 8 : Verifying UI elements on Practice Exam page" );
		Reporter.log("Step 8 : Verifying UI elements on Practice Exam page");
		
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
