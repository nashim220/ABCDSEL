package courses.BoatEd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.ContentsPage;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.PaymentInformationPage;
import pageFactory.Courses.RegistrationPage;
import pageFactory.Courses.ViewReceiptPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class PayUpfrontTest 
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
	 * Verify that student can register for course successfully
	*/ 
	@Test(dataProvider="PayUpfront",dataProviderClass=utility.TestNG.class)
	private void PayUpfront_askedToPay(String state, String amount) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Pay Upfront - Verify that user is asked for paying upfront for {state}"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Pay Upfront - Verify that user is asked for paying upfront for {state}"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);

		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + state);
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + state); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + state);
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + state);
				Thread.sleep(4000);
			}
			
			header.Register_Florida.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
		
			
		System.out.println("Step 2 : Verify whether message is dispalyed regarding upfront payment ");
		Reporter.log("Step 2 : Verify whether message is dispalyed regarding upfront payment"); 
		
			
		String actualmessage= register.FeeMessageText.getText().trim();
		System.out.println(actualmessage);
		
		String expectedmessage =  amount + " course fee, study the online course material, and then";
		
		if(actualmessage.contains(expectedmessage) && actualmessage.contains("After you register for the course,"))
		{
			
			System.out.println("Success !!  message is dispalyed regarding upfront payment'");
			Reporter.log("Success !!  message is dispalyed regarding upfront payment"); 
		}
		else
		{
			System.out.println("Failure !! message is dispalyed but incorrect regarding upfront payment. Expected is : " + expectedmessage + "\n" + "Actual is : " + actualmessage);
			Reporter.log("Failure !! message is dispalyed but incorrect regarding upfront payment. Expected is : " + expectedmessage + "\n" + "Actual is : " + actualmessage); 
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
	 * Pay Upfront - After registration , if user tries to access Contents page then navigated to Payment page and asked to pay first
	*/ 
	@Test
	private void PayUpfront_Registration_Contents() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Pay Upfront - After registration , if user tries to access Contents page then navigated to Payment page and asked to pay first"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Pay Upfront - After registration , if user tries to access Contents page then navigated to Payment page and asked to pay first"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		PaymentInformationPage payment = new PaymentInformationPage(driver);
		ContentsPage content = new ContentsPage(driver);
		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
			
			
			
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
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

			String username="payupfront"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1980", emailaddress, emailaddress);
			
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
			
			
		System.out.println("Step 3 : Trying to access Contents page before payment");
		Reporter.log("Step 3 : Trying to access Contents page before payment");
		if(SeleniumFunc.IsElementPresent(content.IntroModal))
		{
			content.IntroModal.click();
			Thread.sleep(4000);
		}
			header.Contents.click();
			Thread.sleep(5000);
			

			String actual=driver.getCurrentUrl();
			String expected = Constants.ApplicationURL + "/register/payment/";
			
			if(actual.equals(expected))
			{
				
				System.out.println("Success !! user is redirected to Payment page'");
				Reporter.log("Success !! user is redirected to Payment page"); 
			}
			else
			{
				System.out.println("Failure !! user is NOT redirected to Payment page");
				Reporter.log("Failure !! user is NOT redirected to Payment page"); 
				AssertFailedCount++;
			}
			
		
			//Payment is required before starting this course. Please complete your payment info below.
			
			actual=payment.PaymentAlertMessageForPay.getText().trim();
			expected = "Payment is required before starting this course. Please complete your payment info below.";
			
			if(actual.contains(expected))
			{
				
				System.out.println("Success !! user is asked to pay first'");
				Reporter.log("Success !! user is asked to pay first"); 
			}
			else
			{
				System.out.println("Failure !! user is NOT asked to pay first");
				Reporter.log("Failure !! user is NOT asked to pay first"); 
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
	 * Pay Upfront - Payment Information - Verify validation messages are displayed for mandatory fields
	 * */ 
	@Test
	private void PayUpfront_PaymentInformation_Validation_MandatoryFields() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Pay Upfront - Payment Information - Verify validation messages are displayed for mandatory fields"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Pay Upfront - Payment Information - Verify validation messages are displayed for mandatory fields"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		PageHeader header = new PageHeader(driver);

		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
	
			
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
			
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

			String username="payupfront"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1980", emailaddress, emailaddress);
			
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
			

		System.out.println("Step 3 : On Payment Information page - Stage 2 , clicking on 'Confirm Payment Information' button without entering any data" );
		Reporter.log("Step 3 : On Payment Information page - Stage 2 , clicking on 'Confirm Payment Information' button without entering any data");
		
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
	
	
	/* Test 4: 
	 * Pay Upfront - Payment Information - Verify validation messages are displayed for invalid values for CC , Zip , Phone Number fields
	 * */ 
	@Test
	private void PayUpfront_PaymentInformation_Validation_InvalidValues() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Pay Upfront - Payment Information - Verify validation messages are displayed for invalid values for CC , Zip , Phone Number fields"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Pay Upfront - Payment Information - Verify validation messages are displayed for invalid values for CC , Zip , Phone Number fields"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		PageHeader header = new PageHeader(driver);

		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
	
			
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
			
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
		
			String username="payupfront"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1980", emailaddress, emailaddress);
			
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
			

		System.out.println("Step 3 : On Payment Information page - Stage 2 , entering invalid values for CC , Zip , Phone Number fields" );
		Reporter.log("Step 3 : On Payment Information page - Stage 2 , entering invalid values for CC , Zip , Phone Number fields");
		
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
	

	/* Test 5: 
	 * Pay Upfront - Payment Information - Verify details on Confirmation pop up (before & after change)
	 * */ 
	@Test
	private void PayUpfront_PaymentInformation_ConfirmationPopUp() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Pay Upfront - Payment Information - Verify details on Confirmation pop up (before & after change)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Pay Upfront - Payment Information - Verify details on Confirmation pop up (before & after change)"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		
		PageHeader header = new PageHeader(driver);

		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
	
			
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
			
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
			String username="payupfront"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1980", emailaddress, emailaddress);
			
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
			

		System.out.println("Step 3 : On Payment Information page - Stage 2 , entering data for CC& Billing and clicking on 'Confirm Payment Information' button" );
		Reporter.log("Step 3 : On Payment Information page - Stage 2 , entering data for CC& Billing and clicking on 'Confirm Payment Information' button");
		
			payinfo.EnterCCAndBillingDetails("test", "Patel", 
											"4111111111111111", "01", "2019", 
											"14086 Proton Rd.", "Peoria", "California", "75244", 
											 "United States of America", "410-477-3898");
											 
		
		System.out.println("Step 4 : Clicking on 'Confirm Payment Information' button and verifying details on 'Confirm Your Payment Information' model" );
		Reporter.log("Step 4 : Clicking on 'Payment Cerification Information' button and verifying details on 'Confirm Your Payment Information' model");
		
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
			expectedtext = "01-2019";
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
							"I understand that I will be charged $49.95 for the online course and that there are no refunds or cancellations. I have agreed to these terms. (This fee is retained by Boat Ed for the maintenance of this course; that is, no monies from the collection of the fee are transferred to the Florida Fish and Wildlife Conservation Commission.)";
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
	
			
		
		System.out.println("Step 5 : Clicking 'Make A Change' button and editing details on Payment page" );
		Reporter.log("Step 5 : Clicking 'Make A Change' button and editing details on Payment page");
		
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
							"I understand that I will be charged $49.95 for the online course and that there are no refunds or cancellations. I have agreed to these terms. (This fee is retained by Boat Ed for the maintenance of this course; that is, no monies from the collection of the fee are transferred to the Florida Fish and Wildlife Conservation Commission.)";
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
	

	/* Test 6: 
	 * Pay Upfront - Payment Information - Verify details on Confirmation pop up (before & after change) if Billing Address is same as Mailing Address
	 * */ 
	@Test
	private void PayUpfront_PaymentInformation_ConfirmationPopUp_BillingAddress() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Pay Upfront - Payment Information - Verify details on Confirmation pop up (before & after change) if Billing Address is same as Mailing Addresss"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Pay Upfront - Payment Information - Verify details on Confirmation pop up (before & after change) if Billing Address is same as Mailing Addresss"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);	
		
		PageHeader header = new PageHeader(driver);

		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
	
			
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
			
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

			String username="payupfront"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1980", emailaddress, emailaddress);
			
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
			

		System.out.println("Step 3 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button" );
		Reporter.log("Step 3 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button");
		
			payinfo.FirstName.sendKeys("test");
			payinfo.LastName.sendKeys("Patel");
			payinfo.CCNumber.sendKeys("4111111111111111");
			
			new Select(payinfo.ExpirationMonth).selectByVisibleText("03");
			new Select(payinfo.ExpirationYear).selectByVisibleText("2019");
			
			payinfo.BillingContactInfoCheckbox.click();
			
			Thread.sleep(5000);
			
		
		System.out.println("Step 4 : Clicking on 'Confirm Payment Information' button and verifying details on 'Confirm Your Payment Information' model" );
		Reporter.log("Step 4 : Clicking on 'Payment Cerification Information' button and verifying details on 'Confirm Your Payment Information' model");
		
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
			expectedtext = "03-2019";
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
			expectedtext = 	"Mailing Address" + "\n" + 
					"14086 Proton Rd" + "\n" +
					"Dallas, TX 75244"+ "\n" + "\n" + 
					"Phone Number" +  "\n" + 
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
							"I understand that I will be charged $49.95 for the online course and that there are no refunds or cancellations. I have agreed to these terms. (This fee is retained by Boat Ed for the maintenance of this course; that is, no monies from the collection of the fee are transferred to the Florida Fish and Wildlife Conservation Commission.)";
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
	
	
	/* Test 7: 
	 * Pay Upfront - Payment Information - Verify user can pay with Coupon code
	 * */ 
	@Test
	private void PayUpfront_PaymentInformation_PayWithCoupon() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Pay Upfront - Payment Information - Verify user can pay with Coupon code"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Pay Upfront - Payment Information - Verify user can pay with Coupon code"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ViewReceiptPage receipt = new ViewReceiptPage(driver);
		
		PageHeader header = new PageHeader(driver);

		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
	
			
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
			
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

			String username="payupfront"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1980", emailaddress, emailaddress);
			
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
			

		System.out.println("Step 3 : On Payment Information page - Stage 2 , entering invalid Coupon code and clicking on 'Apply Code' button" );
		Reporter.log("Step 3 : On Payment Information page - Stage 2 , entering invalid Coupon code and clicking on 'Apply Code' button");
		
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
		
		
		System.out.println("Step 4 : On Payment Information page - Stage 2 , entering valid Coupon code and clicking on 'Apply Code' button" );
		Reporter.log("Step 4 : On Payment Information page - Stage 2 , entering valid Coupon code and clicking on 'Apply Code' button");
		
			payinfo.CouponCode.sendKeys(Constants.CouponCode);
			payinfo.ApplyCode.click();
			Thread.sleep(4000);

			expectedtext = Constants.ApplicationURL + "/register/paymentconfirm/payment/";
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
	
	
	/* Test 8: 
	 * Pay Upfront - Payment Information - Verify user can pay with Credit Card
	 * */ 
	@Test
	private void PayUpfront_PaymentInformation_PayWithCC() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Pay Upfront - Payment Information - Verify user can pay with Credit Card"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Pay Upfront - Payment Information - Verify user can pay with Credit Card"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ViewReceiptPage receipt = new ViewReceiptPage(driver);
		PageHeader header = new PageHeader(driver);

		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
	
			
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
			
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
	

			String username="payupfront"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1980", emailaddress, emailaddress);
			
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
			
		
			String expectedtext = Constants.ApplicationURL + "/register/paymentconfirm/payment/";
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
	
	
	/* Test 9: 
	 * Pay Upfront - Verify that order confirmation email received by student on registered email address
	 * */ 
	@Test
	private void PayUpfront_PaymentInformation_ConfirmationEmail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Pay Upfront - Verify that order confirmation email received by student on registered email address"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 : Pay Upfront - Verify that order confirmation email received by student on registered email address"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		PageHeader header = new PageHeader(driver);

		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
	
			
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
			
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

			String username="payupfront"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1980", emailaddress, emailaddress);
			
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
			
	
		System.out.println("Step 4 : Navigating to Email Box and verifying whether payment confirmation email is received with correct details or not");
		Reporter.log("Step 4 : Navigating to Email Box and verifying whether payment confirmation email is received with correct details or not"); 
			
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=" + username + "#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
			
			String ActualMessageTitle= SeleniumFunc.GetElementText("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div").trim();
			//System.out.println(ActualMessageTitle);
											
			String ExpectedMessageTitle="Florida Safe Boating Course for Violators - Customer Receipt"; 
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
	 * Pay Upfront - View Receipt - Verify View Receipt page has correct information
	 * */ 
	@Test
	private void PayUpfront_ViewReceipt_VerifyDetails() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : Pay Upfront - View Receipt - Verify View Receipt page has correct information"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10 : Pay Upfront - View Receipt - Verify View Receipt page has correct information"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ViewReceiptPage receipt = new ViewReceiptPage(driver);
		
		PageHeader header = new PageHeader(driver);

		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
	
			
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
			
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

			String username="payupfront"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1980", emailaddress, emailaddress);
			
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
			
	
		System.out.println("Step 4 : Verifying details on 'View Receipt' page");
		Reporter.log("Step 4 : Verifying details on 'View Receipt' page"); 
			
			//Verifying Url
			String expectedtext = Constants.ApplicationURL + "/register/paymentconfirm/payment/";
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
			
					
			// Verifying presence of 'Start Course' button
			
					expectedtext = "Start Your Course";
					actualtext = receipt.GetYourProofofHomeStudyVoucher.getText().trim();
					
					if(actualtext.contains(expectedtext))
					{
						System.out.println("-- Success !! 'Start Course' button is present i.e. " + actualtext );
						Reporter.log("-- Success !! 'Start Course' button  is present i.e. " + actualtext );
						
					}
					else
					{
						System.out.println("-- Failure !! 'Start Course' button  is NOT present. Expected is : " + expectedtext + " , Actual is : " + actualtext );
						Reporter.log("-- Failure !! 'Start Course' button is NOT present. " + expectedtext + " , Actual is : " + actualtext);
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
					expectedtext = "Total Amount: $49.95";
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
					expectedtext = "$49.95";
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
					expectedtext = "$49.95";
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
	
	
	/* Test 11: 
	 * Pay Upfront - View Receipt - Verify on click to 'Start Your Course' button , user is navigated to Course Contents page
	 * */ 
	@Test
	private void PayUpfront_ViewReceipt_StartYourCourse() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 11 : Pay Upfront - View Receipt - Verify on click to 'Start Your Course' button , user is navigated to Course Contents page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 11 : Pay Upfront - View Receipt - Verify on click to 'Start Your Course' button , user is navigated to Course Contents page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ViewReceiptPage receipt = new ViewReceiptPage(driver);
		ContentsPage content = new ContentsPage(driver);
		PageHeader header = new PageHeader(driver);

		
		System.out.println("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator");
		Reporter.log("Step 1 : Navigate to Registration page : " + Constants.ApplicationURL + "/floridaviolator"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/floridaviolator");
	
			
		System.out.println("Step 2 : Register as a New user");
		Reporter.log("Step 2 : Register as a New user"); 
			
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

			String username="payupfront"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			register.FillRegistrationForm(username, "clarion@123", "clarion@123", "test", "b", "ke-testing", "Jr.", "January", "1", "1980", emailaddress, emailaddress);
			
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
			
	
		System.out.println("Step 4 : On 'View Receipt' page, clicking on 'Start Your Course ' button");
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
