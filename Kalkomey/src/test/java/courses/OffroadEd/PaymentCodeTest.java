package courses.OffroadEd;


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
import pageFactory.Courses.RegistrationPage;
import pageFactory.Courses.ViewReceiptPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class PaymentCodeTest 
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
	 * Create and use 'percentage' payment code
	 * */ 
	@Test
	private void PaymentCode_Type_Percentage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Create and use 'percentage' payment code"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Create and use 'percentage' payment code"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ViewReceiptPage receipt = new ViewReceiptPage(driver);
		
		
		System.out.println("Pre-requisite :  Creating new Payment code , Type = Percentage");
		Reporter.log("Pre-requisite :  Creating new Payment code , Type = Percentage"); 
			
			String paymentcode = keadmin.PaymentCodes_New_Type_Percentage("No","GLOBAL","no");
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			System.out.println(emailaddress);
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Offroad + Constants.State_Offroad);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Offroad + Constants.State_Offroad);
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
			keadmin.StudentSearchAndActivity(username, "Off-Road", "Fast Forward Pass");
			
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
			cerinfo.SelectEthinicity("Other");
			cerinfo.SelectEyeColor("Blue");
			cerinfo.SelectHairColor("Brown");
			cerinfo.Student_Weight.sendKeys("60");
			cerinfo.SelectHeight();
			cerinfo.SelectInch();
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
	
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
		
			
			expectedurl = Constants.ApplicationURL_Offroad + "/register/payment/" ;
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
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , applying coupon code" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , applying coupon code");
			
			payinfo.CouponCode.sendKeys(paymentcode);
			payinfo.ApplyCode.click();
			Thread.sleep(4000);

			
			//Verifying Course Total Fee
			String expectedtext = "$14.75";
			String actualtext = payinfo.Total_Fee.getText().trim();
			
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
			
	
		System.out.println("Step 6 : Verifying Total Amount on 'View Receipt' page");
		Reporter.log("Step 6 : Verifying Total Amount on 'View Receipt' page"); 
		
			//Total Fee
			expectedtext = "Total Amount: $14.75";
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
	 * Create and use 'pass' payment code
	 * */ 
	@Test
	private void PaymentCode_Type_Pass() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Create and use 'pass' payment code"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Create and use 'pass' payment code"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ViewReceiptPage receipt = new ViewReceiptPage(driver);
		
		
		System.out.println("Pre-requisite :  Creating new Payment code , Type = pass");
		Reporter.log("Pre-requisite :  Creating new Payment code , Type = pass"); 
			
			String paymentcode = keadmin.PaymentCodes_New_Type_Pass();
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			System.out.println(emailaddress);
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Offroad + Constants.State_Offroad);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Offroad + Constants.State_Offroad);
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
			keadmin.StudentSearchAndActivity(username, "Off-Road", "Fast Forward Pass");
			
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
			cerinfo.SelectEthinicity("Other");
			cerinfo.SelectEyeColor("Blue");
			cerinfo.SelectHairColor("Brown");
			cerinfo.Student_Weight.sendKeys("60");
			cerinfo.SelectHeight();
			cerinfo.SelectInch();
			
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
	
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
		
			
			expectedurl = Constants.ApplicationURL_Offroad + "/register/payment/" ;
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
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , applying coupon code" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , applying coupon code");
			
			payinfo.CouponCode.sendKeys(paymentcode);
			payinfo.ApplyCode.click();
			Thread.sleep(4000);

			
			String expectedtext = Constants.ApplicationURL_Offroad + "/register/paymentconfirm/payment/";
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
			
			
		System.out.println("Step 6 : Verifying Total Amount on 'View Receipt' page");
		Reporter.log("Step 6 : Verifying Total Amount on 'View Receipt' page"); 
		
			//Total Fee
			expectedtext = "Total Amount: $0.00";
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
			
			
		System.out.println("Step 7 : Navigate to KE Admin > View Payment Code page and verify 'Uses'");
		Reporter.log("Step 7 : Navigate to KE Admin > View Payment Code page and verify 'Uses'"); 
		
			//Total Fee
			expectedtext = "1 of 1";
			actualtext = keadmin.PaymentCodes_GetUses_Info(paymentcode);
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! payment code 'Uses' info is correct i.e. " + actualtext );
				Reporter.log("-- Success !! payment code 'Uses' info is correct i.e. " + actualtext );
				
			}
			else
			{
				System.out.println("-- Failure !! payment code 'Uses' info is incorrect. Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! payment code 'Uses' info is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
