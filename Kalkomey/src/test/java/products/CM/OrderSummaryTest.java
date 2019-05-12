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
import pageFactory.CM.OrderSummaryPage;
import pageFactory.CM.SearchCertificationPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class OrderSummaryTest 
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
	 * Verify user can see order confirmation screen
     */ 
	
	@Test
	private void OrderConfirmationScreen() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify user can see order confirmation screen"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify user can see order confirmation screen"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		OrderReplacementPage replace = new OrderReplacementPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		
		OrderSummaryPage summary = new OrderSummaryPage(driver);
		
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
			
			Thread.sleep(2000);
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
			Thread.sleep(2000);
			if(ActualText.equals(ExpectedText))
			{
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
	
			
			//Verify Header
			ExpectedText = "Order Replacement Certificate";
			ActualText = summary.Header.getText().trim();
			Thread.sleep(2000);
			if(ActualText.equals(ExpectedText))
			{
				System.out.println("Success !! Correct Header is displayed. i.e. " + ExpectedText);
				Reporter.log("Success !! Correct Header is displayed. i.e. " + ExpectedText); 
			}
			else
			{
				System.out.println("Failure !! Header is incorrect. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Header is incorrect. i.e." + "\n" + "Expected : "  
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
	 * Verify order details
     */ 
	
	@Test
	private void OrderDetails() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify order details"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify order details"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		OrderReplacementPage replace = new OrderReplacementPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		
		OrderSummaryPage summary = new OrderSummaryPage(driver);
		
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
			
		   Thread.sleep(2000);
			replace.ClickOnOrderReplacementButton();
			Thread.sleep(2000);
			
		System.out.println("Step 4 : Enter valid details, place order and verify success message");
		Reporter.log("Step 4 : Enter valid details, place order and verify success message"); 
				
			
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
				System.out.println("Failure !! Order Details are NOT proper. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Order Details are NOT proper. i.e." + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual : " + "\n" +  ActualText);
				
				AssertFailedCount++;
			}	
	
			
			
			//Verify Order Content
			ExpectedText = "Your authorization code is"+"\n"+
			"888888"+"\n"+
			"Certificate renewal"+"\n"+
			"$10.00"+"\n"+
			"State Sales Tax"+"\n"+
			"$0.00"+"\n"+
			"Total charged"+"\n"+
			"$10.00";
			ActualText = summary.OrderDetails.getText().trim();
			Thread.sleep(2000);
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Order Details are displayed properly. i.e. " + ExpectedText);
				Reporter.log("Success !! Order Details are displayed properly. i.e. " + ExpectedText); 
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

}