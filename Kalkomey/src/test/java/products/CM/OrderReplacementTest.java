package products.CM;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.CreateNewCertificationPage;
import pageFactory.CM.LoginPage;
import pageFactory.CM.OrderReplacementPage;
import pageFactory.CM.SearchCertificationPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class OrderReplacementTest 
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
	 * Verify that user can navigate to 'Order replacement' page
     */ 
	
	@Test
	private void OrderReplacement() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify that user can navigate to 'Order replacement' page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify that user can navigate to 'Order replacement' page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		OrderReplacementPage replace = new OrderReplacementPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		

		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername("sanjeetk@clariontechnologies.co.in");
			login.EnterPassword("clarion@123");
			login.ClickOnLogInButton();
		
		System.out.println("Step 3: Adding new ceritificate");
		Reporter.log("Step 3: Adding new ceritificate"); 
			
			certi.ClickOnNewCertificate();
			Thread.sleep(2000);
			String firstname= "certificate" + JH.GenerateRandomNumber();
			System.out.println(firstname);
			String emailprefix = "certificate" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 PROTON RD";
		
			newcerti.CreateNewCertificate(firstname, "Testing", "01/24/1990", emailaddress, "United States",  streetaddress,"DALLAS", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			newcerti.ClickOnContinueButton();
			Thread.sleep(5000);
			
			
		System.out.println("Step 4 : Go to order replacement and verify Control redirected to Order Replacement");
		Reporter.log("Step 4 : Go to order replacement and verify Control redirected to Order Replacement");
			
				
			replace.ClickOnOrderReplacementButton();
			Thread.sleep(2000);
			String ExpectedText = "Order Replacement Certificate";
			String ActualText = replace.Header.getText().trim();
			Thread.sleep(2000);
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Order Replacement page is displayed. i.e. " + ExpectedText);
				Reporter.log("Success !! Order Replacement page is displayed. i.e. " + ExpectedText); 
			}
			else
			{
				System.out.println("Failure !! NOT redirected to Order Replacement page. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! NOT redirected to Order Replacement page. i.e." + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual : " + "\n" +  ActualText);
				
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
	 * Verify that user is able to select number of cards
     */ 
	
	@Test
	private void SelectNoOfCards() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify that user is able to select number of cards"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify that user is able to select number of cards"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		OrderReplacementPage replace = new OrderReplacementPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		

		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername("sanjeetk@clariontechnologies.co.in");
			login.EnterPassword("clarion@123");
			login.ClickOnLogInButton();
		
		System.out.println("Step 3: Adding new ceritificate");
		Reporter.log("Step 3: Adding new ceritificate"); 
			
			certi.ClickOnNewCertificate();
			Thread.sleep(2000);
			String firstname= "certificate" + JH.GenerateRandomNumber();
			System.out.println(firstname);
			String emailprefix = "certificate" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 PROTON RD";
		
			newcerti.CreateNewCertificate(firstname, "Testing", "01/24/1990", emailaddress, "United States",  streetaddress,"DALLAS", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			newcerti.ClickOnContinueButton();
			Thread.sleep(5000);
			
			
		System.out.println("Step 4 : Go to order replacement and Select no of cards");
		Reporter.log("Step 4 : Go to order replacement and Select no of cards");
			
				
			replace.ClickOnOrderReplacementButton();
			Thread.sleep(2000);
			replace.SelectQTY("3 cards");
			
			//Verify 3 cards selected
			String ExpectedText = "3 cards";
			String ActualText = SeleniumFunc.GetSelectedValueFromDropdownList("css", "#quantity");
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Select No Of cards functionality is working properly. i.e. " + ExpectedText);
				Reporter.log("Success !! Select No Of cards functionality is working properly. i.e. " + ExpectedText); 
			}
			else
			{
				System.out.println("Failure !!Select No Of cards functionality is NOT working. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !!Select No Of cards functionality is NOT working. i.e." + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual : " + "\n" +  ActualText);
				
				AssertFailedCount++;
			}
	
			//Verify total price is displayed on button
			
			ExpectedText = "Submit Payment for $30.00";
			ActualText = replace.Submit.getText().trim();
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Total price is displayed on button. i.e. " + ExpectedText);
				Reporter.log("Success !! Total price is displayed on button. i.e. " + ExpectedText); 
			}
			else
			{
				System.out.println("Failure !! Wrong price is displayed on button. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Wrong price is displayed on button. i.e." + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual : " + "\n" +  ActualText);
				
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
	 * Verify on changing country, State drop down changes
     */ 
	
	@Test
	private void VerifyCountryStateCombinations() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify on changing country, State dropdown changes"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify on changing country, State dropdown changes"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		OrderReplacementPage replace = new OrderReplacementPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		

		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername("sanjeetk@clariontechnologies.co.in");
			login.EnterPassword("clarion@123");
			login.ClickOnLogInButton();
		
		System.out.println("Step 3: Adding new ceritificate");
		Reporter.log("Step 3: Adding new ceritificate"); 
			
			certi.ClickOnNewCertificate();
			Thread.sleep(2000);
			String firstname= "certificate" + JH.GenerateRandomNumber();
			System.out.println(firstname);
			String emailprefix = "certificate" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 PROTON RD";
		
			newcerti.CreateNewCertificate(firstname, "Testing", "01/24/1990", emailaddress, "United States",  streetaddress,"DALLAS", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			newcerti.ClickOnContinueButton();
			Thread.sleep(5000);
			
			
		System.out.println("Step 4 : Go to order replacement");
		Reporter.log("Step 4 : Go to order replacement");
			
				
			replace.ClickOnOrderReplacementButton();
			Thread.sleep(2000);
			
		System.out.println("Step 5 : Verify State dropdown changes accordingly to country");
		Reporter.log("Step 5 : Verify State dropdown changes accordingly to country"); 
				
			
			replace.SelectCountry("Canada");
			Thread.sleep(3000);
				
			//Verify Drop-down options
		    String[] dropdownvalues = {"Alberta","British Columbia","Manitoba","New Brunswick","Newfoundland and Labrador","Northwest Territories","Nova Scotia","Nunavut","Ontario","Prince Edward Island","Quebec","Saskatchewan","Yukon"};
		    Thread.sleep(4000);
			SeleniumFunc.VerifyDropDownOptions("css", "select#billing_address_state", dropdownvalues);
				
	

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
	 * Verify user can place an order
     */ 
	
	@Test
	private void VerifyPlaceOrderFunctionality() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify user can place an order"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify user can place an order"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		OrderReplacementPage replace = new OrderReplacementPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		

		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername("sanjeetk@clariontechnologies.co.in");
			login.EnterPassword("clarion@123");
			login.ClickOnLogInButton();
		
		System.out.println("Step 3: Adding new ceritificate");
		Reporter.log("Step 3: Adding new ceritificate"); 
			
			certi.ClickOnNewCertificate();
			Thread.sleep(2000);
			String firstname= "certificate" + JH.GenerateRandomNumber();
			System.out.println(firstname);
			String emailprefix = "certificate" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 PROTON RD";
		
			newcerti.CreateNewCertificate(firstname, "Testing", "01/24/1990", emailaddress, "United States",  streetaddress,"DALLAS", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			newcerti.ClickOnContinueButton();
			Thread.sleep(5000);
			
			
		System.out.println("Step 4 : Go to order replacement");
		Reporter.log("Step 4 : Go to order replacement");
			
				
			replace.ClickOnOrderReplacementButton();
			Thread.sleep(2000);
			
		System.out.println("Step 5 : Enter valid details, place order and verify success message");
		Reporter.log("Step 5 : Enter valid details, place order and verify success message"); 
				
			
			replace.FillBillingDetails("4111111111111111", "12-2025","7047983705");
			replace.ClickOnSubmitButton();
			Thread.sleep(4000);
			
			//Verify Success Message
			String ExpectedText = "The payment has been successfully processed.";
			String ActualText = replace.Success.getText().trim();
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Order placed successfully. i.e. " + ExpectedText);
				Reporter.log("Success !! Order placed successfully. i.e. " + ExpectedText); 
			}
			else
			{
				System.out.println("Failure !! Error while place order. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Error while place order. i.e." + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual : " + "\n" +  ActualText);
				
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
	 * Verify validations on place an order page
     */ 
	
	@Test
	private void ValidationOnPlaceOrder() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify validations on place an order page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify validations on place an order page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		OrderReplacementPage replace = new OrderReplacementPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		

		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername("sanjeetk@clariontechnologies.co.in");
			login.EnterPassword("clarion@123");
			login.ClickOnLogInButton();
		
		System.out.println("Step 3: Adding new ceritificate");
		Reporter.log("Step 3: Adding new ceritificate"); 
			
			certi.ClickOnNewCertificate();
			Thread.sleep(2000);
			String firstname= "certificate" + JH.GenerateRandomNumber();
			System.out.println(firstname);
			String emailprefix = "certificate" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 PROTON RD";
		
			newcerti.CreateNewCertificate(firstname, "Testing", "01/24/1990", emailaddress, "United States",  streetaddress,"DALLAS", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			newcerti.ClickOnContinueButton();
			Thread.sleep(5000);
			
			
		System.out.println("Step 4 : Go to order replacement");
		Reporter.log("Step 4 : Go to order replacement");
			
				
			replace.ClickOnOrderReplacementButton();
			Thread.sleep(2000);
			
		System.out.println("Step 5 : Verify validations");
		Reporter.log("Step 5 : Verify validations"); 
				
			
			replace.FillBillingDetails("41111111", "12-2025","7047983705");
			replace.ClickOnSubmitButton();
			
			//Verify Validation for invalid CC
			String ExpectedText = "We accept: American Express, Discover, MasterCard, and Visa";
			String ActualText = replace.ErrorCC.getText().trim();
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Validation is displayed for invalid CC. i.e. " + ExpectedText);
				Reporter.log("Success !! Validation is displayed for invalid CC. i.e. " + ExpectedText); 
			}
			else
			{
				System.out.println("Failure !! No validation for Invalid CC. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! No validation for Invalid CC. i.e." + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual : " + "\n" +  ActualText);
				
				AssertFailedCount++;
			}	
	
			
			replace.FillBillingDetails("4111111111111111", "2025","7047983705");
			replace.ClickOnSubmitButton();
			
			//Verify Validation for invalid Expiry Date
			ExpectedText = "This expiration date is invalid. Please use the format: MM-YYYY";
			ActualText = replace.ErrorExpiry.getText().trim();
			Thread.sleep(2000);
			if(ActualText.equals(ExpectedText))
			{
				System.out.println("Success !! Validation is displayed for invalid Expiry Date. i.e. " + ExpectedText);
				Reporter.log("Success !! Validation is displayed for invalid Expiry Date. i.e. " + ExpectedText); 
			}
			else
			{
				System.out.println("Failure !! No validation for Invalid Expiry Date. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! No validation for Invalid Expiry Date. i.e." + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual : " + "\n" +  ActualText);
				
				AssertFailedCount++;
			}	
	
			
			replace.FillBillingDetails("4111111111111111", "12-2025","7047983705");
			replace.EnterZip("124");
			replace.ClickOnSubmitButton();
			Thread.sleep(2000);
			//Verify Validation for invalid Zip
			ExpectedText = "This zip code is invalid. Please use the format: 12345 or 12345-6789";
			ActualText = replace.ErrorZip.getText().trim();
			
			if(ActualText.equals(ExpectedText))
			{
				System.out.println("Success !! Validation is displayed for invalid Zip. i.e. " + ExpectedText);
				Reporter.log("Success !! Validation is displayed for invalid Zip. i.e. " + ExpectedText); 
			}
			else
			{
				System.out.println("Failure !! No validation for Invalid Zip. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! No validation for Invalid Zip. i.e." + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual : " + "\n" +  ActualText);
				
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
	 * Verify fields on Order page
     */ 
	
	@Test
	private void VerifyFields() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify fields on Order page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Verify fields on Order page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		OrderReplacementPage replace = new OrderReplacementPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		

		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername("sanjeetk@clariontechnologies.co.in");
			login.EnterPassword("clarion@123");
			login.ClickOnLogInButton();
		
		System.out.println("Step 3: Adding new ceritificate");
		Reporter.log("Step 3: Adding new ceritificate"); 
			
			certi.ClickOnNewCertificate();
			Thread.sleep(2000);
			String firstname= "certificate" + JH.GenerateRandomNumber();
			System.out.println(firstname);
			String emailprefix = "certificate" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 PROTON RD";
		
			newcerti.CreateNewCertificate(firstname, "Testing", "01/24/1990", emailaddress, "United States",  streetaddress,"DALLAS", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			newcerti.ClickOnContinueButton();
			Thread.sleep(5000);
			
			
		System.out.println("Step 4 : Go to order replacement");
		Reporter.log("Step 4 : Go to order replacement");
			
				
			replace.ClickOnOrderReplacementButton();
			Thread.sleep(2000);
			
		System.out.println("Step 5 : Verify fields");
		Reporter.log("Step 5 : Verify fields"); 
			
		
			//First Name
			if(SeleniumFunc.IsElementPresent(replace.FN))
			{
				Thread.sleep(2000);
				System.out.println("Success !! First Name field is present");
				Reporter.log("Success !! First Name field is present"); 
			}
			else
			{
				System.out.println("Failure !! First Name field is missing");
				Reporter.log("Failure !! First Name field is missing");
			
				AssertFailedCount++;
			}
			
					
			//Last Name
			if(SeleniumFunc.IsElementPresent(replace.LN))
			{
				System.out.println("Success !! Last Name field is present");
				Reporter.log("Success !! Last Name field is present"); 
			}
			else
			{
				System.out.println("Failure !! Last Name field is missing");
				Reporter.log("Failure !! Last Name field is missing");
			
				AssertFailedCount++;
			}
			
			//CC
			if(SeleniumFunc.IsElementPresent(replace.CC))
			{
				Thread.sleep(2000);
				System.out.println("Success !! CC field is present");
				Reporter.log("Success !! CC field is present"); 
			}
			else
			{
				System.out.println("Failure !! CC field is missing");
				Reporter.log("Failure !! CC field is missing");
			
				AssertFailedCount++;
			}
			
			
			//Expiry Date
			if(SeleniumFunc.IsElementPresent(replace.CC))
			{
				System.out.println("Success !! Expiry Date field is present");
				Reporter.log("Success !! Expiry Date field is present"); 
			}
			else
			{
				System.out.println("Failure !! Expiry Date field is missing");
				Reporter.log("Failure !! Expiry Date field is missing");
			
				AssertFailedCount++;
			}
			
			
			//Country Dropdown
			if(SeleniumFunc.IsElementPresent(replace.Country))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Country Dropdown is present");
				Reporter.log("Success !! Country Dropdown is present"); 
			}
			else
			{
				System.out.println("Failure !! Country Dropdown is missing");
				Reporter.log("Failure !! Country Dropdown is missing");
			
				AssertFailedCount++;
			}
			
			
			//State Dropdown
			if(SeleniumFunc.IsElementPresent(replace.State))
			{
				System.out.println("Success !! State Dropdown is present");
				Reporter.log("Success !! State Dropdown is present"); 
			}
			else
			{
				System.out.println("Failure !! State Dropdown is missing");
				Reporter.log("Failure !! State Dropdown is missing");
			
				AssertFailedCount++;
			}
			
			
			//QTY Dropdown
			if(SeleniumFunc.IsElementPresent(replace.QTY))
			{
				Thread.sleep(2000);
				System.out.println("Success !! QTY Dropdown is present");
				Reporter.log("Success !! QTY Dropdown is present"); 
			}
			else
			{
				System.out.println("Failure !! QTY Dropdown is missing");
				Reporter.log("Failure !! QTY Dropdown is missing");
			
				AssertFailedCount++;
			}
			
			
			//Street Field
			if(SeleniumFunc.IsElementPresent(replace.Street))
			{
				System.out.println("Success !! Street field is present");
				Reporter.log("Success !! Street field is present"); 
			}
			else
			{
				System.out.println("Failure !! Street field is missing");
				Reporter.log("Failure !! Street field is missing");
			
				AssertFailedCount++;
			}
			
			
			//City Field
			if(SeleniumFunc.IsElementPresent(replace.City))
			{
				Thread.sleep(2000);
				System.out.println("Success !! City field is present");
				Reporter.log("Success !! City field is present"); 
			}
			else
			{
				System.out.println("Failure !! City field is missing");
				Reporter.log("Failure !! City field is missing");
			
				AssertFailedCount++;
			}
			
			
			//Zip Field
			if(SeleniumFunc.IsElementPresent(replace.Zip))
			{
				System.out.println("Success !! Zip field is present");
				Reporter.log("Success !! Zip field is present"); 
			}
			else
			{
				System.out.println("Failure !! Zip field is missing");
				Reporter.log("Failure !! Zip field is missing");
			
				AssertFailedCount++;
			}
			
			
			//Submit Button
			if(SeleniumFunc.IsElementPresent(replace.Submit))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Submit Button is present");
				Reporter.log("Success !! Submit Button is present"); 
			}
			else
			{
				System.out.println("Failure !! Submit Button is missing");
				Reporter.log("Failure !! Submit Button is missing");
			
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