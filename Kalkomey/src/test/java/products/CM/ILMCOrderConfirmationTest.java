package products.CM;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.CreateNewCertificationPage;
import pageFactory.CM.ILMCCertificationPage;
import pageFactory.CM.ILMCEditAddressPage;
import pageFactory.CM.ILMCHomePage;
import pageFactory.CM.ILMCOrderConfirmationPage;
import pageFactory.CM.ILMCOrderPage;
import pageFactory.CM.ILMCSearchPage;
import pageFactory.CM.LoginPage;
import pageFactory.CM.SearchCertificationPage;



import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class ILMCOrderConfirmationTest 
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
	 * Verify UI of order confirmation page
	*/ 
	@Test
	private void VerifyUIOrderConfirmation() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify UI of order confirmation page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 1 : Verify UI of order confirmation page"  + "\n" +
	 				"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCCertificationPage certipage = new ILMCCertificationPage(driver);
		ILMCOrderPage orderpage = new ILMCOrderPage(driver);
		ILMCOrderConfirmationPage confirmpage = new ILMCOrderConfirmationPage(driver);
		
		System.out.println("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		Reporter.log("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			
			
		System.out.println("Step 2 : Select state and course and go to search page");
		Reporter.log("Step 2 : Select state and course and go to search page"); 
						
			
			//Select state and course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(3000);		
			
		System.out.println("Step 3 : Enter valid record and go to certification page");
		Reporter.log("Step 3 : Enter valid record and go to certification page"); 
		
			
		searchpage.EnterFirstName("DONALD");
		searchpage.EnterLastName("SCHERER");
		searchpage.EnterDay("15");
		searchpage.EnterYear("1975");
		searchpage.SelectMonth("May");
		searchpage.ClickOnSearchButton();
		Thread.sleep(3000);
			
			// go to order replacement page
			certipage.ClickOnConfirmCheckbox();
			Thread.sleep(2000);
			certipage.ClickOnOrderButton();
			Thread.sleep(3000);
			
		System.out.println("Step 4 : Verify user can place order successfully");
		Reporter.log("Step 4 : Verify user can place order successfully"); 
			
		
			orderpage.FillBillingDetails("4111111111111111", "02-2038", "Automation", "Testing", "rohit@gmail.com", "rohit@gmail.com");
			Thread.sleep(2000);
			orderpage.ClickOnSameBillingCheckbox();
			Thread.sleep(2000);
			orderpage.ClickOnPlaceOrderButton();
			Thread.sleep(4000);

			//Verify Confirmation
			
			String ExpectedText="The payment was successfully processed.";
			String ActualText = SeleniumFunc.GetElementText("css", ".alert.alert-success>h4");
			Thread.sleep(2000);
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Order placed successfully. i.e. " +ExpectedText);
				Reporter.log("Success !! Order placed successfully. i.e. " +ExpectedText);
		
			}
			else
			{
				Thread.sleep(1000);
				System.out.println("Failure !! Error in order. Expected :" + ExpectedText + "\n"+
							"Actual :" +ActualText);
				Reporter.log("Failure !! Error in order. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
		
				AssertFailedCount++;
		
			}
			
			
			//UI
			Thread.sleep(1000);
			//Verify Breadcrumb
			ActualText = searchpage.Breadcrumb.getText().trim();
			ExpectedText = "Home / Order Confirmation";
			Thread.sleep(1000);
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Correct Breadcrumb is displayed. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct Breadcrumb is displayed. i.e. " +ExpectedText);
			
			}
			else
			{
				Thread.sleep(1000);
				System.out.println("Failure !! Incorrect Breadcrumb. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect Breadcrumb. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
			
				AssertFailedCount++;
			
			}
			
			
			//Billing Details
			if(SeleniumFunc.IsElementPresent(confirmpage.BillingDetails))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Billing Detail displayed.");
				Reporter.log("Success !! Billing Detail displayed.");
			
			}
			else
			{
				Thread.sleep(1000);
				System.out.println("Failure !! Billing Detail missing");
				Reporter.log("Failure !! Billing Detail missing"); 
			
				AssertFailedCount++;
			
			}
			
			Thread.sleep(1000);
			//Authorization Code
			ActualText = confirmpage.AuthorizationCode.getText().trim();
			ExpectedText = "Your purchase authorization code is 888888.";
			Thread.sleep(1000);
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Correct Authorization Code is displayed. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct Authorization Code is displayed. i.e. " +ExpectedText);
			
			}
			else
			{
				Thread.sleep(1000);
				System.out.println("Failure !! Incorrect Authorization Code. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect Authorization Code. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
			
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
	 * Verify correct support text present
	*/ 
	@Test
	private void VerifySupporText() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify correct support text present"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 2 : Verify correct support text present"  + "\n" +
	 				"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCCertificationPage certipage = new ILMCCertificationPage(driver);
		ILMCOrderPage orderpage = new ILMCOrderPage(driver);
		ILMCOrderConfirmationPage confirmpage = new ILMCOrderConfirmationPage(driver);
		
		System.out.println("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		Reporter.log("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			
			
		System.out.println("Step 2 : Select state and course and go to search page");
		Reporter.log("Step 2 : Select state and course and go to search page"); 
						
			
			//Select state and course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(2000);	
			
		System.out.println("Step 3 : Enter valid record and go to certification page");
		Reporter.log("Step 3 : Enter valid record and go to certification page"); 
		
			
		searchpage.EnterFirstName("DONALD");
		searchpage.EnterLastName("SCHERER");
		searchpage.EnterDay("15");
		searchpage.EnterYear("1975");
		searchpage.SelectMonth("May");
		searchpage.ClickOnSearchButton();
		Thread.sleep(1000);
			
			// go to order replacement page
			certipage.ClickOnConfirmCheckbox();
			certipage.ClickOnOrderButton();
			Thread.sleep(2000);
			
		System.out.println("Step 4 : Verify user can place order successfully");
		Reporter.log("Step 4 : Verify user can place order successfully"); 
			
		
			orderpage.FillBillingDetails("4111111111111111", "02-2038", "Automation", "Testing", "rohit@gmail.com", "rohit@gmail.com");
			orderpage.ClickOnSameBillingCheckbox();
			Thread.sleep(2000);
			orderpage.ClickOnPlaceOrderButton();
			Thread.sleep(2000);

			//Verify Confirmation
			
			String ExpectedText="The payment was successfully processed.";
			String ActualText = confirmpage.Success.getText().trim();
				
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Order placed successfully. i.e. " +ExpectedText);
				Reporter.log("Success !! Order placed successfully. i.e. " +ExpectedText);
		
			}
			else
			{
		
				System.out.println("Failure !! Error in order. Expected :" + ExpectedText + "\n"+
							"Actual :" +ActualText);
				Reporter.log("Failure !! Error in order. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
		
				AssertFailedCount++;
		
			}
			
			
			
			//Verify Support Text
			ActualText = confirmpage.SupportText.getText().trim();
			ExpectedText = "Your receipt and temporary certificate have been emailed to you. " +
					"You may print your temporary certificate for use until your permanent card arrives. " +
					"If you have any problems, you may contact Kalkomey customer service at " +
					"support@ilostmycard.com or by calling 800-830-2268.";
		
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Correct Support Text is displayed. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct Support Text is displayed. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! Incorrect Support Text. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect Support Text. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
			
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
	 * Verify the presence of other sister links of Kalkomey on Order Confirmation Page Run
	*/ 
	@Test
	private void VerifySisterLink() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify the presence of other sister links of Kalkomey on Order Confirmation Page Run"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 3 : Verify the presence of other sister links of Kalkomey on Order Confirmation Page Run"  + "\n" +
	 				"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCCertificationPage certipage = new ILMCCertificationPage(driver);
		ILMCOrderPage orderpage = new ILMCOrderPage(driver);
		ILMCOrderConfirmationPage confirmpage = new ILMCOrderConfirmationPage(driver);
		
		System.out.println("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		Reporter.log("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			
			
		System.out.println("Step 2 : Select state and course and go to search page");
		Reporter.log("Step 2 : Select state and course and go to search page"); 
						
			
			//Select state and course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(2000);		
			
		System.out.println("Step 3 : Enter valid record and go to certification page");
		Reporter.log("Step 3 : Enter valid record and go to certification page"); 
		
			
		searchpage.EnterFirstName("DONALD");
		searchpage.EnterLastName("SCHERER");
		searchpage.EnterDay("15");
		searchpage.EnterYear("1975");
		searchpage.SelectMonth("May");
		searchpage.ClickOnSearchButton();
		Thread.sleep(1000);
			
			// go to order replacement page
			certipage.ClickOnConfirmCheckbox();
			certipage.ClickOnOrderButton();
			
			
		System.out.println("Step 4 : Verify user can place order successfully");
		Reporter.log("Step 4 : Verify user can place order successfully"); 
			
		
			orderpage.FillBillingDetails("4111111111111111", "02-2038", "Automation", "Testing", "rohit@gmail.com", "rohit@gmail.com");
			orderpage.ClickOnSameBillingCheckbox();
			Thread.sleep(2000);
			orderpage.ClickOnPlaceOrderButton();
			Thread.sleep(4000);

			//Verify Confirmation
			
			String ExpectedText="The payment was successfully processed.";
			String ActualText = confirmpage.Success.getText().trim();
				
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Order placed successfully. i.e. " +ExpectedText);
				Reporter.log("Success !! Order placed successfully. i.e. " +ExpectedText);
		
			}
			else
			{
		
				System.out.println("Failure !! Error in order. Expected :" + ExpectedText + "\n"+
							"Actual :" +ActualText);
				Reporter.log("Failure !! Error in order. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
		
				AssertFailedCount++;
		
			}
				
		System.out.println("Step 5 : Verify presence of sister project links");
		Reporter.log("Step 5 : Verify presence of sister project links"); 
			
				
				ExpectedText = "boat-ed.comhunter-ed.combowhunter-ed.comoffroad-ed.comsnowmobile-ed.com" + 
							"http://www.boat-ed.com/http://www.hunter-ed.com/http://www.bowhunter-ed.com/http://www.offroad-ed.com/http://www.snowmobile-ed.com/";
				ActualText = homepage.GetAllSisterLinksName() + homepage.GetAllSisterLinks();
			

				if(ActualText.contains(ExpectedText) )
				{
					Thread.sleep(2000);
					System.out.println("Success!! footer has correct sister sites links i.e. " + ExpectedText);
					Reporter.log("Success!! footer has correct sister sites links i.e. " + ExpectedText); 
					
				}
				else
				{
					
					System.out.println("Failure !! footer has incorrect sister sites links  " + "\n" + "Expected Text: "  
								+ "\n" + ExpectedText + "\n" + 
								 "Actual Text : " + "\n" +  ActualText);
					Reporter.log("Failure !! footer has incorrect sites sites links " + "\n" + "Expected Text: "  
							+ "\n" + ExpectedText + "\n" + 
							"Actual Text : " + "\n" +  ActualText);
				
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
	 * Verify presence of social networking links on Order Confirmation Page
	*/ 
	@Test
	private void VerifySocialNetworkingLink() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify presence of social networking links on Order Confirmation Page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 4 : Verify presence of social networking links on Order Confirmation Page"  + "\n" +
	 				"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCCertificationPage certipage = new ILMCCertificationPage(driver);
		ILMCOrderPage orderpage = new ILMCOrderPage(driver);
		ILMCOrderConfirmationPage confirmpage = new ILMCOrderConfirmationPage(driver);
		
		System.out.println("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		Reporter.log("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			
			
		System.out.println("Step 2 : Select state and course and go to search page");
		Reporter.log("Step 2 : Select state and course and go to search page"); 
						
			
			//Select state and course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(2000);	
			
		System.out.println("Step 3 : Enter valid record and go to certification page");
		Reporter.log("Step 3 : Enter valid record and go to certification page"); 
		
			
		searchpage.EnterFirstName("DONALD");
		searchpage.EnterLastName("SCHERER");
		searchpage.EnterDay("15");
		searchpage.EnterYear("1975");
		searchpage.SelectMonth("May");
		searchpage.ClickOnSearchButton();
		Thread.sleep(1000);
			
			// go to order replacement page
			certipage.ClickOnConfirmCheckbox();
			certipage.ClickOnOrderButton();
			Thread.sleep(2000);
			
		System.out.println("Step 4 : Verify user can place order successfully");
		Reporter.log("Step 4 : Verify user can place order successfully"); 
			
		
			orderpage.FillBillingDetails("4111111111111111", "02-2038", "Automation", "Testing", "rohit@gmail.com", "rohit@gmail.com");
			orderpage.ClickOnSameBillingCheckbox();
			Thread.sleep(2000);
			orderpage.ClickOnPlaceOrderButton();
			Thread.sleep(4000);

			//Verify Confirmation
			
			String ExpectedText="The payment was successfully processed.";
			String ActualText = confirmpage.Success.getText().trim();
				
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Order placed successfully. i.e. " +ExpectedText);
				Reporter.log("Success !! Order placed successfully. i.e. " +ExpectedText);
		
			}
			else
			{
		
				System.out.println("Failure !! Error in order. Expected :" + ExpectedText + "\n"+
							"Actual :" +ActualText);
				Reporter.log("Failure !! Error in order. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
		
				AssertFailedCount++;
		
			}
			
			
			
		System.out.println("Step 5 : Verify presence of Social Networking sites");
		Reporter.log("Step 5 : Verify presence of Social Networking sites"); 
			
				
			if(SeleniumFunc.IsElementPresent(confirmpage.FB) && SeleniumFunc.IsElementPresent(confirmpage.YouTube))
			{
				Thread.sleep(2000);		
				System.out.println("Success!! Social Networking sites present");
				Reporter.log("Success!! Social Networking sites present"); 
					
			}
			else
			{
					
				System.out.println("Failure !! Social Networking sites missing");
				Reporter.log("Failure !! Social Networking sites missing");
				
				AssertFailedCount++;
					
			}	
				
				
		System.out.println("Step 6 : Verify link of Social Networking sites");
		Reporter.log("Step 6 : Verify link of Social Networking sites"); 
					
			
			ActualText = confirmpage.FB.getAttribute("href") + confirmpage.YouTube.getAttribute("href");
			
			ExpectedText = "http://www.facebook.com/Kalkomeyhttp://www.youtube.com/kalkomey";	
				

			if(ActualText.contains(ExpectedText) )
			{
						
				System.out.println("Success!! footer has Social Networking links i.e. " + ExpectedText);
				Reporter.log("Success!! footer has Social Networking links i.e. " + ExpectedText); 
					
			}
			else
			{
				
				System.out.println("Failure !! footer has incorrect Social Networking links  " + "\n" + "Expected Text: "  
							+ "\n" + ExpectedText + "\n" + 
							 "Actual Text : " + "\n" +  ActualText);
				Reporter.log("Failure !! footer has incorrect Social Networking links " + "\n" + "Expected Text: "  
						+ "\n" + ExpectedText + "\n" + 
							"Actual Text : " + "\n" +  ActualText);
				
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
	 *Verify users may update their addresses for a searched record but the change is not saved until the citizen pays for their replacement card.
	*/ 
	@Test
	private void AddressChangeAfterPayment() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify users may update their addresses for a searched record but the change is not saved until the citizen pays for their replacement card."  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify users may update their addresses for a searched record but the change is not saved until the citizen pays for their replacement card."  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCCertificationPage certipage = new ILMCCertificationPage(driver);
		ILMCEditAddressPage editadd = new ILMCEditAddressPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		
		ILMCHomePage homepage = new ILMCHomePage(driver);

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
			Thread.sleep(2000);
			newcerti.CreateNewCertificate(firstname, "Testing", "01/24/1990", emailaddress, "United States",  streetaddress,"DALLAS", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			newcerti.ClickOnContinueButton();
			Thread.sleep(5000);
			
			
		System.out.println("Step 4 : Open record in ILMC, and search a certificate");
		Reporter.log("Step 4 : Open record in ILMC, and search a certificate");
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			
			//Select state and course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(2000);
			//Search
			searchpage.EnterFirstName(firstname);
			searchpage.EnterLastName("Testing");
			searchpage.EnterDay("24");
			searchpage.EnterYear("1990");
			searchpage.ClickOnSearchButton();
			
			Thread.sleep(2000);
				
		
		System.out.println("Step 5 : Go to Edit Address, and then verify USPS suggestion for address");
		Reporter.log("Step 5 : Go to Edit Address, and then verify USPS suggestion for address");
		
			
			certipage.ClickOnEditAddressButton();
			editadd.EnterZip("12345");
			editadd.SelectState("Pennsylvania");
			editadd.ClickOnContinueButton();
			Thread.sleep(5000);
			
			if(SeleniumFunc.IsElementPresent(editadd.AddressCheck))
			{
			
				System.out.println("Success !! USPS address suggestion is displayed");
				Reporter.log("Success !! USPS address suggestion is displayed");
			
			}
			else
			{
			
				System.out.println("Failure !! USPS address suggestion is missing");
				Reporter.log("Failure !! USPS address suggestion is missing"); 
			
				AssertFailedCount++;
			
			}
	
			editadd.ClickOnAddressCheckButton();
			Thread.sleep(2000);
			editadd.ClickOnSaveButton();
			Thread.sleep(2000);
			//Verify Message
			
			String ExpectedText = "Changes made to your certification will not be made permanent until payment has been made.";
			String ActualText = certipage.Success.getText().trim();

			if(ActualText.contains(ExpectedText) )
			{
				Thread.sleep(2000);
				System.out.println("Success!! Need to perform payment to update address. i.e. " + ExpectedText);
				Reporter.log("Success!! Need to perform payment to update address. i.e. " + ExpectedText); 
				
			}
			else
			{
				
				System.out.println("Failure !! Address Updated without payment." + "\n" + "Expected Text: "  
							+ "\n" + ExpectedText + "\n" + 
							 "Actual Text : " + "\n" +  ActualText);
				Reporter.log("Failure !! Address Updated without payment." + "\n" + "Expected Text: "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual Text : " + "\n" +  ActualText);
			
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
