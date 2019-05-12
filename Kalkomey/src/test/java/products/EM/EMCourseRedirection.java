package products.EM;

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
import pageFactory.Courses.KECourseAdminPage;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.PaymentInformationPage;
import pageFactory.Courses.ProfileNewPage;
import pageFactory.Courses.RegistrationNewPage;
import pageFactory.Courses.ViewReceiptPage;
import pageFactory.EM.ErrorPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class EMCourseRedirection {
	
	private WebDriver driver;
	private Browser b = new Browser();
	JavaHelpers JH = new JavaHelpers();
	ErrorPage errorpage = new ErrorPage(driver);
	

	
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
	
	@Test
	private void InPersonRedirectToRegisterEd() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify user is able to email certificate and certificate email is received by user with correct details"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify user is able to email certificate and certificate email is received by user with correct details"  + "\n" +
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
		ContentsPage content = new ContentsPage(driver);
		

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
			
			register.RegisterAsNewUser(username,  "January", "2", "1990", emailaddress, "clarion@123", "clarion@123");
			Thread.sleep(10000);
			
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			 {
				content.IntroModal.click();
				Thread.sleep(4000);
			 }		

			header.Contents.click();

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
		
			//SeleniumFunc.ToGoToUrl("https://piyush:piyush@admintools-qa1.kalkomey.com/");
			//Runtime.getRuntime().exec("/src/test/resources/AutoIt/Courses_admin_auth_GC.exe");
			SeleniumFunc.ToGoToUrl("https://admintools-qa.kalkomey.com/");
			Thread.sleep(5000);
			
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

				SeleniumFunc.ClickOnElement("css", "#next_text a");
				Thread.sleep(4000);				
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
		
			
			String expectedurl = Constants.ApplicationURL_Hunter + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);
			String actualurl = driver.getCurrentUrl();
			
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
			new Select(payinfo.ExpirationYear).selectByVisibleText("2034");
			
			payinfo.BillingContactInfoCheckbox.click();
			Thread.sleep(4000);

			payinfo.ConfirmPaymentInformation.click();
			
			Thread.sleep(4000);
			
			payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
			
			Thread.sleep(10000);
						
			receipt.GetYourProofofHomeStudyVoucher.click();
			Thread.sleep(4000);
				
		System.out.println("Step 5 : Click on in-person button and verify control is redirected to Register-Ed with zip and range selected" );
		Reporter.log("Step 5 : Click on in-person button and verify control is redirected to Register-Ed with zip and range selected" );
	
			SeleniumFunc.ClickOnElement("css", "#main section:nth-of-type(2) div a");
			Thread.sleep(4000);
			
			for (String winHandle : driver.getWindowHandles()) 
			{
			    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			
			//Verify URL 
			expectedurl = Constants.APPLICATIONURL_RegisterEd + "/programs/arkansas/35-arkansas-hunter-education-online-in-person-test?zip=75244&distance=75";
			actualurl = driver.getCurrentUrl();
			
			if(actualurl.equals(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to Register-Ed");
				Reporter.log("-- Success !! user is navigated to Register-Ed");
				
				
			}
			else
			{
				System.out.println("-- Failure !! User is navigated to : " + actualurl);
				Reporter.log("-- Failure !! User is navigated to : " + actualurl);
				AssertFailedCount++;
			}
		
			//Zip Code
			String Actual = SeleniumFunc.GetElementText("css", "#eventZipOuter strong");
			String Expected = "75244";
							
			if(Actual.equals(Expected))
			{	
				System.out.println("Success !! Correct Zip code is displayed. i.e." + Actual);
				Reporter.log("Success !! Correct Zip code is displayed. i.e." + Actual);			
			}
			else
			{			
				System.out.println("Failure !! Wrong or No Zip code is displayed." + "\n" + "Expected : "  
									+ "\n" + Expected + "\n" + 
									 "Actual: " + "\n" +  Actual);
				Reporter.log("Failure !! Wrong or No Zip code is displayed." + "\n" + "Expected : "  
									+ "\n" + Expected + "\n" + 
									"Actual: " + "\n" +  Actual);
				
				AssertFailedCount++;				
			}
			
			
			//Miles away
			Actual = SeleniumFunc.GetSelectedValueFromDropdownList("css", "#eventDistance");
			Expected = "within 75 miles";
							
			if(Actual.equals(Expected))
			{	
				System.out.println("Success !! Correct value is selected for within miles. i.e." + Actual);
				Reporter.log("Success !! Correct value is selected for within miles. i.e." + Actual);			
			}
			else
			{			
				System.out.println("Failure !! Wrong or No value selected for within miles." + "\n" + "Expected : "  
									+ "\n" + Expected + "\n" + 
									 "Actual: " + "\n" +  Actual);
				Reporter.log("Failure !! Wrong or No value selected for within miles." + "\n" + "Expected : "  
									+ "\n" + Expected + "\n" + 
									"Actual: " + "\n" +  Actual);
				
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
