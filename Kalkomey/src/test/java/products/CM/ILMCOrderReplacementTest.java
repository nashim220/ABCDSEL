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

public class ILMCOrderReplacementTest 
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
	
	
	/* Test 1 : 
	 * Verify that user can navigate to 'Order replacement' page
	*/ 
	@Test
	private void VerifyNavigation() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify that user can navigate to 'Order replacement' page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 1 : Verify that user can navigate to 'Order replacement' page"  + "\n" +
	 				"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCCertificationPage certipage = new ILMCCertificationPage(driver);

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
			Thread.sleep(1000);		
			
		System.out.println("Step 3 : Enter valid record and go to certification page");
		Reporter.log("Step 3 : Enter valid record and go to certification page"); 
		
			
		searchpage.EnterFirstName("DONALD");
		searchpage.EnterLastName("SCHERER");
		searchpage.EnterDay("15");
		searchpage.EnterYear("1975");
		searchpage.SelectMonth("May");
		searchpage.ClickOnSearchButton();
		Thread.sleep(1000);
			
			
			
			//Verify Certification record is present
			
			if (SeleniumFunc.IsElementPresent(searchpage.Certification))
			{
				System.out.println("Success !! Certification record is present");
				Reporter.log("Success !! Certification record is present"); 
			}
			else
			{
				System.out.println("Failure !! Certification record is missing");
				Reporter.log("Failure !! Certification record is missing");
				
				AssertFailedCount++;
			}
			
			
			//Accept terms and go to order page
			certipage.ClickOnConfirmCheckbox();
			certipage.ClickOnOrderButton();
			Thread.sleep(1000);
			
		System.out.println("Step 4 : Verify control is redirected to Order replacement page");
		Reporter.log("Step 4 : Verify control is redirected to Order replacement page"); 
				
		
			String ActualText = searchpage.Header.getText().trim();
			String ExpectedText = "Order Your Pennsylvania Hunting Certification Replacement Card";
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Control is redirected to Order Replacement page. i.e. " +ExpectedText);
				Reporter.log("Success !! Control is redirected to Order Replacement page. i.e. " +ExpectedText);
				
			}
			else
			{
				
				System.out.println("Failure !! Control is NOT redirected to Order Replacement page. " +
									"Expected :" + ExpectedText + "\n"+
									"Actual :" +ActualText);
				Reporter.log("Failure !! Control is NOT redirected to Order Replacement page" +
							"Expected :" + ExpectedText + "\n"+
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
	 * Verify UI on Order Replacement Page
	 */ 
	
	@Test
	private void VerifyUIOrderReplacement() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify UI on Order Replacement Page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 2 : Verify UI on Order Replacement Page"  + "\n" +
	 				"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCCertificationPage certipage = new ILMCCertificationPage(driver);
		ILMCOrderPage orderpage = new ILMCOrderPage(driver);

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
			Thread.sleep(1000);			
			
		System.out.println("Step 3 : Enter valid record and go to order replacement page");
		Reporter.log("Step 3 : Enter valid record and go to order replacement page"); 
		
			
		searchpage.EnterFirstName("DONALD");
		searchpage.EnterLastName("SCHERER");
		searchpage.EnterDay("15");
		searchpage.EnterYear("1975");
		searchpage.SelectMonth("May");
		searchpage.ClickOnSearchButton();
		Thread.sleep(1000);
			
			
			
			//Verify Certification record is present
			
			if (SeleniumFunc.IsElementPresent(searchpage.Certification))
			{
				System.out.println("Success !! Certification record is present");
				Reporter.log("Success !! Certification record is present"); 
			}
			else
			{
				System.out.println("Failure !! Certification record is missing");
				Reporter.log("Failure !! Certification record is missing");
				
				AssertFailedCount++;
			}
			
			
			//Accept terms and go to order page
			certipage.ClickOnConfirmCheckbox();
			certipage.ClickOnOrderButton();
			Thread.sleep(1000);
			
		System.out.println("Step 4 : Verify control is redirected to Order replacement page");
		Reporter.log("Step 4 : Verify control is redirected to Order replacement page"); 
				
		
			String ActualText = searchpage.Header.getText().trim();
			String ExpectedText = "Order Your Pennsylvania Hunting Certification Replacement Card";
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Control is redirected to Order Replacement page. i.e. " +ExpectedText);
				Reporter.log("Success !! Control is redirected to Order Replacement page. i.e. " +ExpectedText);
				
			}
			else
			{
				
				System.out.println("Failure !! Control is NOT redirected to Order Replacement page. " +
									"Expected :" + ExpectedText + "\n"+
									"Actual :" +ActualText);
				Reporter.log("Failure !! Control is NOT redirected to Order Replacement page" +
							"Expected :" + ExpectedText + "\n"+
							"Actual :" +ActualText); 
				
				AssertFailedCount++;
				
			}
			
			
		System.out.println("Step 3 : Verify UI of order replacement page");
		Reporter.log("Step 3 : Verify UI of order replacement page"); 
		
		
			//Verify Breadcrumb
			ActualText = searchpage.Breadcrumb.getText().trim();
			ExpectedText = "Home / Search / Certification Record/ Order Replacement";
		
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Correct Breadcrumb is displayed. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct Breadcrumb is displayed. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! Incorrect Breadcrumb. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect Breadcrumb. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
			
				AssertFailedCount++;
			
			}
			
			
			//Verify number line present along with 1 is highlighted 
			if (SeleniumFunc.IsElementPresent(searchpage.NumberLine) && SeleniumFunc.IsElementPresent(searchpage.HighlitedNumber3))
			{
				System.out.println("Success !! Number Line is present along with 3 as highlighted");
				Reporter.log("Success !! Number Line is present along with 3 as highlighted"); 
			}
			else
			{
				System.out.println("Failure !! Number Line is missing OR 3 is not highlighted");
				Reporter.log("Failure !! Number Line is missing OR 3 is not highlighted");
				
				AssertFailedCount++;
			}

			
			//Verify order details present
			if (SeleniumFunc.IsElementPresent(orderpage.OrderDetails))
			{
				System.out.println("Success !! Order Details is present");
				Reporter.log("Success !! Order Details is present"); 
			}
			else
			{
				System.out.println("Failure !! Order Details is missing");
				Reporter.log("Failure !! Order Details is missing");
				
				AssertFailedCount++;
			}
			
			
			//Verify CC Field is present
			if (SeleniumFunc.IsElementPresent(orderpage.CardNumber))
			{
				Thread.sleep(1000);
				System.out.println("Success !! CC Field is present");
				Reporter.log("Success !! CC Field s is present"); 
			}
			else
			{
				System.out.println("Failure !! CC Field is missing");
				Reporter.log("Failure !! CC Field is missing");
				
				AssertFailedCount++;
			}
			
			
			//Verify CC Expiry Field is present
			if (SeleniumFunc.IsElementPresent(orderpage.Expiry))
			{
				System.out.println("Success !! CC Expiry Field is present");
				Reporter.log("Success !! CC Expiry Field s is present"); 
			}
			else
			{
				System.out.println("Failure !! CC Expiry Field is missing");
				Reporter.log("Failure !! CC Expiry Field is missing");
				
				AssertFailedCount++;
			}
			

			//Verify FirstName Field is present
			if (SeleniumFunc.IsElementPresent(orderpage.FirstName))
			{
				System.out.println("Success !! FirstName Field is present");
				Reporter.log("Success !! FirstName Field s is present"); 
			}
			else
			{
				System.out.println("Failure !! FirstName Field is missing");
				Reporter.log("Failure !! FirstName Field is missing");
				
				AssertFailedCount++;
			}

			
			//Verify LastName Field is present
			if (SeleniumFunc.IsElementPresent(orderpage.LastName))
			{
				System.out.println("Success !! LastName Field is present");
				Reporter.log("Success !! LastName Field s is present"); 
			}
			else
			{
				System.out.println("Failure !! LastName Field is missing");
				Reporter.log("Failure !! LastName Field is missing");
				
				AssertFailedCount++;
			}
			
			
			//Verify Country Field is present
			if (SeleniumFunc.IsElementPresent(orderpage.Country))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Country Field is present");
				Reporter.log("Success !! Country Field s is present"); 
			}
			else
			{
				System.out.println("Failure !! Country Field is missing");
				Reporter.log("Failure !! Country Field is missing");
				
				AssertFailedCount++;
			}
			
			
			//Verify Country Field is present
			if (SeleniumFunc.IsElementPresent(orderpage.Country))
			{
				System.out.println("Success !! Country Field is present");
				Reporter.log("Success !! Country Field s is present"); 
			}
			else
			{
				System.out.println("Failure !! Country Field is missing");
				Reporter.log("Failure !! Country Field is missing");
				
				AssertFailedCount++;
			}

			
			//Verify Address Field is present
			if (SeleniumFunc.IsElementPresent(orderpage.Address))
			{
				System.out.println("Success !! Address Field is present");
				Reporter.log("Success !! Address Field s is present"); 
			}
			else
			{
				System.out.println("Failure !! Address Field is missing");
				Reporter.log("Failure !! Address Field is missing");
				
				AssertFailedCount++;
			}
			
			
			//Verify City Field is present
			if (SeleniumFunc.IsElementPresent(orderpage.City))
			{
				Thread.sleep(1000);
				System.out.println("Success !! City Field is present");
				Reporter.log("Success !! City Field s is present"); 
			}
			else
			{
				System.out.println("Failure !! City Field is missing");
				Reporter.log("Failure !! City Field is missing");
				
				AssertFailedCount++;
			}
			
			
			//Verify State Field is present
			if (SeleniumFunc.IsElementPresent(orderpage.State))
			{
				System.out.println("Success !! State Field is present");
				Reporter.log("Success !! State Field s is present"); 
			}
			else
			{
				System.out.println("Failure !! State Field is missing");
				Reporter.log("Failure !! State Field is missing");
				
				AssertFailedCount++;
			}
			
			
			//Verify Zip Field is present
			if (SeleniumFunc.IsElementPresent(orderpage.Zip))
			{
				System.out.println("Success !! Zip Field is present");
				Reporter.log("Success !! Zip Field s is present"); 
			}
			else
			{
				System.out.println("Failure !! Zip Field is missing");
				Reporter.log("Failure !! Zip Field is missing");
				
				AssertFailedCount++;
			}
			
			
			//Verify Email Field is present
			if (SeleniumFunc.IsElementPresent(orderpage.Email))
			{
				System.out.println("Success !! Email Field is present");
				Reporter.log("Success !! Email Field s is present"); 
			}
			else
			{
				System.out.println("Failure !! Email Field is missing");
				Reporter.log("Failure !! Email Field is missing");
				
				AssertFailedCount++;
			}
			
			
			//Verify Confirm Email Field is present
			if (SeleniumFunc.IsElementPresent(orderpage.ConfirmEmail))
			{
				System.out.println("Success !! Confirm Email Field is present");
				Reporter.log("Success !! Confirm Email Field is present"); 
			}
			else
			{
				System.out.println("Failure !! Confirm Email Field is missing");
				Reporter.log("Failure !! Confirm Email Field is missing");
				
				AssertFailedCount++;
			}

			
			//Verify Place Order Button is present
			if (SeleniumFunc.IsElementPresent(orderpage.ConfirmEmail))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Place Order Button is present");
				Reporter.log("Success !! Place Order Button is present"); 
			}
			else
			{
				System.out.println("Failure !! Place Order Button is missing");
				Reporter.log("Failure !! Place Order Button is missing");
				
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
	 * Verify State dropdown changes accordingly to country
	*/ 
	@Test
	private void VerifyCountryStateCombinations() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify State dropdown changes accordingly to country"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 3 : Verify State dropdown changes accordingly to country"  + "\n" +
	 				"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCCertificationPage certipage = new ILMCCertificationPage(driver);
		ILMCOrderPage orderpage = new ILMCOrderPage(driver);

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
			Thread.sleep(1000);		
			
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
			Thread.sleep(1000);
			
		System.out.println("Step 4 : Verify State dropdown changes accordingly to country");
		Reporter.log("Step 4 : Verify State dropdown changes accordingly to country"); 
			
		
			orderpage.SelectCountry("Canada");
			Thread.sleep(3000);
			
			//Verify Drop-down options
		    String[] dropdownvalues = {"Alberta","British Columbia","Manitoba","New Brunswick","Newfoundland and Labrador","Northwest Territories","Nova Scotia","Nunavut","Ontario","Prince Edward Island","Quebec","Saskatchewan","Yukon"};
			Thread.sleep(1000);
			SeleniumFunc.VerifyDropDownOptions("id", "billing_address_state", dropdownvalues);
			
			
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
	 * Verify validation on confirmation page
	*/ 
	@Test
	private void VerifyValidation() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify validation on confirmation page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 4 : Verify validation on confirmation page"  + "\n" +
	 				"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCCertificationPage certipage = new ILMCCertificationPage(driver);
		ILMCOrderPage orderpage = new ILMCOrderPage(driver);

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
			Thread.sleep(1000);		
			
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
			Thread.sleep(1000);

		System.out.println("Step 4 : Click on order button by keeping all fields blank");
		Reporter.log("Step 4 : Click on order button by keeping all fields blank"); 
				
			orderpage.ClickOnPlaceOrderButton();
			Thread.sleep(1000);
			String ExpectedText = "Something’s wrong. Please review the fields below.";
			String ActualText = orderpage.Error.getText().trim();
			Thread.sleep(1000);
			if(ActualText.equals(ExpectedText))
			{
				System.out.println("Success !! Correct validation is displayed. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct validation is displayed. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! Incorrect validation. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect validation. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
			
				AssertFailedCount++;
			
			}
			
	
		System.out.println("Step 5 : Enter Invalid Details and verify validation");
		Reporter.log("Step 5 : Click on order button by keeping all fields blank"); 
					
		   Thread.sleep(1000);
			//Invalid CC	
			orderpage.EnterCCNumber("55");
			orderpage.ClickOnPlaceOrderButton();
			Thread.sleep(1000);
			ExpectedText = "Please provide a valid credit card number.";
			ActualText = SeleniumFunc.GetElementText("css", "#card_number + p").trim();
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Correct validation is displayed for CC. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct validation is displayed for CC. i.e. " +ExpectedText);
			
			}
			else
			{
				Thread.sleep(1000);
				System.out.println("Failure !! Incorrect validation for CC. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect validation for CC. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
			
				AssertFailedCount++;
			
			}
			
			
			Thread.sleep(1000);
			//Invalid Expiry	
			orderpage.EnterExpiry("AA");
			orderpage.ClickOnPlaceOrderButton();
			Thread.sleep(1000);
			ExpectedText = "Please provide a credit card expiration date (for example: 07-2018).";
			ActualText = SeleniumFunc.GetElementText("css", "#card_expy + p").trim();
			
			if(ActualText.equals(ExpectedText))
			{
				System.out.println("Success !! Correct validation is displayed for CC Expiry. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct validation is displayed for CC Expiry. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! Incorrect validation for CC Expiry. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect validation for CC Expiry. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
			
				AssertFailedCount++;
			
			}
			
			
			//Invalid Zip
			SeleniumFunc.EnterValueInTextbox("css", "#billing_address_zip", "22");
			orderpage.ClickOnPlaceOrderButton();
			
			ExpectedText = "Please provide a zip code (for example: 12345).";
			ActualText = SeleniumFunc.GetElementText("css", "#billing_address_zip + p").trim();
			Thread.sleep(1000);
			if(ActualText.equals(ExpectedText))
			{
				System.out.println("Success !! Correct validation is displayed for Zip. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct validation is displayed for Zip. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! Incorrect validation for Zip. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect validation for Zip. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
			
				AssertFailedCount++;
			
			}
			
			
			Thread.sleep(1000);
			//Not matching email
			orderpage.EnterEmail("rohit.ware@clariontechnologies.co.in");
			orderpage.EnterConfirmEmail("rohit@gmail.com");
			orderpage.ClickOnPlaceOrderButton();
			orderpage.ClickOnPlaceOrderButton();
			Thread.sleep(1000);
			ExpectedText = "This doesn’t match what you entered in the first field. Please try again.";
			ActualText = SeleniumFunc.GetElementText("css", "#emailConfirm + p").trim();
			Thread.sleep(1000);
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Correct validation is displayed for Email. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct validation is displayed for Email. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! Incorrect validation for Email. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect validation for Email. Expected :" + ExpectedText + "\n"+
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

	
	
	/* Test 5: 
	 * Verify place order functionality
	*/ 
	@Test
	private void VerifyPlaceOrder() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify place order functionality"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 5 : Verify place order functionality"  + "\n" +
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
			Thread.sleep(1000);
						
			
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
			Thread.sleep(1000);
			
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
			Thread.sleep(1000);
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
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
	 * Verify order details at CM
	*/ 
	@Test
	private void OrderDetailsCM() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify order details at CM"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Verify order details at CM"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		
		CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCCertificationPage certipage = new ILMCCertificationPage(driver);
		ILMCOrderPage orderpage = new ILMCOrderPage(driver);
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
			Thread.sleep(1000);
			newcerti.CreateNewCertificate(firstname, "Testing", "01/24/1990", emailaddress, "United States",  streetaddress,"DALLAS", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			Thread.sleep(1000);
			newcerti.ClickOnContinueButton();
			Thread.sleep(5000);
			
			String URL = driver.getCurrentUrl();
			
		System.out.println("Step 4 : Open record in ILMC, and Search a certificate");
		Reporter.log("Step 4 : Open record in ILMC, and Search a certificate");
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			
			
			//Select state and course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(1000);
			//Search Certificate
			searchpage.EnterFirstName(firstname);
			searchpage.EnterLastName("Testing");
			searchpage.EnterDay("24");
			searchpage.EnterYear("1990");
			searchpage.ClickOnSearchButton();
			
			Thread.sleep(2000);
				
		
		System.out.println("Step 5 : Order certificate and verify details at CM");
		Reporter.log("Step 5 : Order certificate and verify details at CM");
		
		
			certipage.ClickOnConfirmCheckbox();
			certipage.ClickOnOrderButton();
			Thread.sleep(1000);
			orderpage.FillBillingDetails("4111111111111111", "02-2038", "Automation", "Testing", "rohit@gmail.com", "rohit@gmail.com");
			orderpage.ClickOnSameBillingCheckbox();
			Thread.sleep(2000);
			orderpage.ClickOnPlaceOrderButton();
			Thread.sleep(2000);
		
			
			//Go to CM and verify order details
			SeleniumFunc.ToGoToUrl(URL);
			
			//Verify Email
			String ExpectedText = "rohit@gmail.com";
			String ActualText = SeleniumFunc.GetElementText("css", "#activity-stream");
			Thread.sleep(1000);
			if(ActualText.contains(ExpectedText))
			{
			Thread.sleep(1000);
				System.out.println("Success !! Correct email ID is present. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct email ID is present. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! Email ID missing. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Email ID missing. Expected :" + ExpectedText + "\n"+
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
	
}
