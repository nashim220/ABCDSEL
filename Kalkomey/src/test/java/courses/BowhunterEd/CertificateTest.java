package courses.BowhunterEd;

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
import pageFactory.Courses.RegistrationPage;
import pageFactory.Courses.ViewReceiptPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class CertificateTest 
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
	 * Verify user is able to email certificate and certificate email is received by user with correct details
	 * */ 
	@Test
	private void Certificate_Email() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify user is able to email certificate and certificate email is received by user with correct details"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify user is able to email certificate and certificate email is received by user with correct details"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ViewReceiptPage receipt = new ViewReceiptPage(driver);
		CourseCompletePage coursecomplete = new CourseCompletePage(driver);
		
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			System.out.println(emailaddress);
			String password = "clarion@123";
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver);
			Thread.sleep(4000);
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
			
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();
			
	
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Bow-Hunter", "Fast Forward Pass");
			
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
			cerinfo.SelectEthinicity("Other");
			
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
	
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
		
			
			expectedurl = Constants.ApplicationURL_BowHunter + "/register/payment/" ;
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
			payinfo.ConfirmPaymentInformation.click();
			
			Thread.sleep(4000);
			
			payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
			
			Thread.sleep(10000);
			
	
		System.out.println("Step 6 : On 'View Receipt' page, clicking on 'Get Your Proof of Home Study Voucher ' button");
		Reporter.log("Step 6 : On 'View Receipt' page, clicking on 'Get Your Proof of Home Study Voucher ' button"); 
			
			receipt.GetYourProofofHomeStudyVoucher.click();
			Thread.sleep(4000);
			
			//Verifying Url
			String expectedtext = Constants.ApplicationURL_BowHunter + "/course/course_complete";
			String actualtext = driver.getCurrentUrl();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! user is navigated to Course Compelte page i.e. " + actualtext );
				Reporter.log("-- Success !! user is navigated to Course Compelte page i.e. " + actualtext );
				
				expectedtext= "You Have Completed the Alaska Bowhunter Ed Course";
				//expectedtext = "You Have Completed the Bowhunter Ed Alaska Course";
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
			
			
		System.out.println("Step 7 : On 'View Receipt' page, click on 'Email' button");
		Reporter.log("Step 7 : On 'View Receipt' page, click on 'Email' button");
		
			
			coursecomplete.Email.click();
			Thread.sleep(5000);
			
			
			//Verifying confirmation message .
			
			expectedtext = "Your Field Day Qualifier Certificate has been sent to your e-mail address " + emailaddress + ".";
			actualtext = SeleniumFunc.GetElementText("css", "div[class='alert alert-success fade in']").trim();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! Email sent confirmation message is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Email sent confirmation message is correct i.e. " + actualtext );
				
			}
			else
			{
				System.out.println("-- Failure !! Email sent confirmation message is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Email sent confirmation message is incorrect. " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 8 : Verifying whether Email is received by user or not");
		Reporter.log("Step 8 : Verifying whether Email is received by user or not");
		
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=" + emailprefix + "#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
			
			String ActualMessageTitle= SeleniumFunc.GetElementText("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div").trim();
			//System.out.println(ActualMessageTitle);
											
			//String ExpectedMessageTitle="Bowhunter Ed " +Constants.State_BowHunter.substring(1, Constants.State_BowHunter.length()) + " Course Field Day Qualifier Certificate"; 
			String ExpectedMessageTitle="Alaska Bowhunter Ed Course - Field Day";
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
				
				/*
				String ExpectedMessageBody =
						"Hi test," + "\n" + "\n" +
						"Congratulations on passing the Bowhunter Ed " + Constants.State_BowHunter.substring(1, Constants.State_BowHunter.length())+ " Course. Your Field Day Qualifier Certificate is attached." + "\n" + "\n" + 
						"Thanks and safe hunting!" + "\n" + "\n" + 
						"Bowhunter Ed Support";
				*/
				String ExpectedMessageBody = "Hi Test, Congratulations, you have successfully completed the online component of the Alaska Bowhunter Ed Course. To obtain the Alaska Bowhunter Education Certificate, issued by the Alaska Department of Fish and Game, you also must pass the Field Day. To register for your Field Day now, click on the following link. To register later, you may log back in to this course. Field Day: http://www.adfg.alaska.gov/index.cfm?adfg=huntered.onlinecourses&classtype=b Thanks and safe hunting! Bowhunter Ed Support";
				
				if(ActualMessageBody.toLowerCase().contains(ExpectedMessageBody.toLowerCase()) )
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
							+ "\n" + ExpectedMessageBody +  "\n" + 
							 "Actual Body : " + "\n" +  ActualMessageBody);
					
					AssertFailedCount++;
				}
				
			}
			catch(Exception e)
			{
				//nothing :)
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
