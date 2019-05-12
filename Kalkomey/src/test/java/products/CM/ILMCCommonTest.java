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

public class ILMCCommonTest 
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
	 * An invalid Date should kick back to search screen
	*/ 
	@Test
	private void InvalidDOB() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : An invalid Date should kick back to search screen"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : An invalid Date should kick back to search screen"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		
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
			
		System.out.println("Step 3 : Enter invalid record and go to certification page");
		Reporter.log("Step 3 : Enter invalid record and go to certification page"); 
			
				
		searchpage.EnterFirstName("DONALD");
		searchpage.EnterLastName("SCHERER");
		searchpage.EnterDay("15");
		searchpage.EnterYear("2025");
		searchpage.SelectMonth("May");
		searchpage.ClickOnSearchButton();
		Thread.sleep(1000);
				
				
		System.out.println("Step 4 : Verify validation for invalid DOB");
		Reporter.log("Step 4 : Verify validation for invalid DOB"); 
						
			
			String ExpectedText = "Date of Birth is invalid.";
			String ActualText = searchpage.ErrorMessage.getText().trim();
			Thread.sleep(2000);
					
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Correct Validation is displayed for invalid DOB. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct Validation is displayed for invalid DOB. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! Incorrect Validation for invalid DOB. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect Validation for invalid DOB. Expected :" + ExpectedText + "\n"+
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
	 * An invalid day should cause an error
	*/ 
	@Test
	private void InvalidDay() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : An invalid day should cause an error"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : An invalid day should cause an error"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		
		System.out.println("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		Reporter.log("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			Thread.sleep(2000);
			
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
			
				
			searchpage.EnterFirstName("Ware");
			searchpage.EnterLastName("Rohit");
			searchpage.EnterDay("34");
			searchpage.ClickOnSearchButton();
			Thread.sleep(2000);
				
		System.out.println("Step 4 : Verify validation for invalid date.");
		Reporter.log("Step 4 : Verify validation for invalid date."); 
						

			//Jan 34
			if(SeleniumFunc.IsElementPresent(searchpage.ErrorDay))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Correct Validation is displayed for invalid date. i.e. Jan 34 ");
				Reporter.log("Success !! Correct Validation is displayed for invalid date. i.e. Jan 34 ");
			
			}
			else
			{
			
				System.out.println("Failure !! No Validation for invalid date. i.e. Jan 34");
				Reporter.log("Failure !! No Validation for invalid date. i.e. Jan 34"); 
			
				AssertFailedCount++;
			
			}
			
			
			//Jan 31
			searchpage.EnterDay("31");
			searchpage.ClickOnSearchButton();
			Thread.sleep(2000);
			if(SeleniumFunc.IsElementPresent(searchpage.ErrorDay))
			{
			
				System.out.println("Success !! No Validation is displayed for valid date. i.e. Jan 31 ");
				Reporter.log("Success !! No Validation is displayed for valid date. i.e. Jan 31 ");
			
			}
			else
			{
			
				System.out.println("Failure !! Validation for valid date. i.e. Jan 31");
				Reporter.log("Failure !! Validation for valid date. i.e. Jan 31"); 
			
				AssertFailedCount++;
			
			}
			
			//April 31
			searchpage.SelectMonth("April");
			searchpage.ClickOnSearchButton();
			Thread.sleep(2000);
			if(SeleniumFunc.IsElementPresent(searchpage.ErrorDay))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Correct Validation is displayed for invalid date. i.e. April 31 ");
				Reporter.log("Success !! Correct Validation is displayed for invalid date. i.e. April 31 ");
			
			}
			else
			{
			
				System.out.println("Failure !! No Validation for invalid date. i.e. April 31");
				Reporter.log("Failure !! No Validation for invalid date. i.e. April 31"); 
			
				AssertFailedCount++;
			
			}
			
			
			//April 30
			searchpage.EnterDay("30");
			searchpage.ClickOnSearchButton();
			Thread.sleep(2000);
			if(SeleniumFunc.IsElementPresent(searchpage.ErrorDay))
			{
			
				System.out.println("Success !! No Validation is displayed for valid date. i.e. April 30");
				Reporter.log("Success !! No Validation is displayed for valid date. i.e. April 30");
			
			}
			else
			{
			
				System.out.println("Failure !! Validation for valid date. i.e. April 30");
				Reporter.log("Failure !! Validation for valid date. i.e. April 30"); 
			
				AssertFailedCount++;
			
			}
			
			
			//Feb 30
			searchpage.SelectMonth("February");
			searchpage.ClickOnSearchButton();
			Thread.sleep(2000);
			if(SeleniumFunc.IsElementPresent(searchpage.ErrorDay))
			{
			
				System.out.println("Success !! Correct Validation is displayed for invalid date. i.e. Feb 30");
				Reporter.log("Success !! Correct Validation is displayed for invalid date. i.e. Feb 30");
			
			}
			else
			{
			
				System.out.println("Failure !! No Validation for invalid date. i.e. Feb 30");
				Reporter.log("Failure !! No Validation for invalid date. i.e. Feb 30"); 
			
				AssertFailedCount++;
			
			}
			
			
			//Feb 29
			searchpage.EnterDay("29");
			searchpage.ClickOnSearchButton();
			Thread.sleep(2000);
			if(SeleniumFunc.IsElementPresent(searchpage.ErrorDay))
			{
			
				System.out.println("Success !! No Validation is displayed for valid date. i.e. Feb 29");
				Reporter.log("Success !! No Validation is displayed for valid date. i.e. Feb 29");
			
			}
			else
			{
			
				System.out.println("Failure !! Validation for valid date. i.e. Feb 29");
				Reporter.log("Failure !! Validation for valid date. i.e. Feb 29"); 
			
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
	 * Verify that Need help text is displayed
	*/ 
	@Test
	private void VerifyNeedHelpText() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify that Need help text is displayed"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 3 : Verify that Need help text is displayed"  + "\n" +
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
			
		
		System.out.println("Step 2 : Verify Need Help section is present at home page");
		Reporter.log("Step 2 : Verify Need Help section is present at home page"); 
				
			if(SeleniumFunc.IsElementPresent(homepage.Help))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Need Help section is present at home page");
				Reporter.log("Success !! Need Help section is present at home page");
		
			}
			else
			{
				Thread.sleep(2000);
				System.out.println("Failure !! Need Help section is missing at home page");
				Reporter.log("Failure !! Need Help section is missing at home page"); 
		
				AssertFailedCount++;
				
			}
			
		System.out.println("Step 3 : Select state and course and go to search page");
		Reporter.log("Step 3 : Select state and course and go to search page"); 
						
			
			//Select state and course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(4000);	
		
		System.out.println("Step 4 : Verify Need Help section is present at search page");
		Reporter.log("Step 4 : Verify Need Help section is present at search page"); 
					
			if(SeleniumFunc.IsElementPresent(homepage.Help))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Need Help section is present at search page");
				Reporter.log("Success !! Need Help section is present at search page");
			
			}
			else
			{
				Thread.sleep(2000);
				System.out.println("Failure !! Need Help section is missing at search page");
				Reporter.log("Failure !! Need Help section is missing at search page"); 
			
				AssertFailedCount++;
				
			}
		
		
		System.out.println("Step 5 : Enter valid record and go to certification page");
		Reporter.log("Step 5 : Enter valid record and go to certification page"); 
		
			
		searchpage.EnterFirstName("DONALD");
		searchpage.EnterLastName("SCHERER");
		searchpage.EnterDay("15");
		searchpage.EnterYear("1975");
		searchpage.SelectMonth("May");
		Thread.sleep(3000);
		searchpage.ClickOnSearchButton();
		Thread.sleep(5000);
			// go to order replacement page
			certipage.ClickOnConfirmCheckbox();
			certipage.ClickOnOrderButton();
			Thread.sleep(5000);
			
		System.out.println("Step 6 : Verify Need Help section is present at order replacement page");
		Reporter.log("Step 6 : Verify Need Help section is present at order replacement page"); 
						
			if(SeleniumFunc.IsElementPresent(homepage.Help))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Need Help section is present at order replacement page");
				Reporter.log("Success !! Need Help section is present at order replacement page");
				
			}
			else
			{
				Thread.sleep(2000);
				System.out.println("Failure !! Need Help section is missing at order replacement page");
				Reporter.log("Failure !! Need Help section is missing at order replacement page"); 
				
				AssertFailedCount++;
					
			}
				
		System.out.println("Step 7 : Verify user can place order successfully");
		Reporter.log("Step 7 : Verify user can place order successfully"); 
			
		    Thread.sleep(2000);
			orderpage.FillBillingDetails("4111111111111111", "02-2038", "Automation", "Testing", "rohit@gmail.com", "rohit@gmail.com");
			orderpage.ClickOnSameBillingCheckbox();
			Thread.sleep(2000);
			orderpage.ClickOnPlaceOrderButton();
			Thread.sleep(2000);

			//Verify Confirmation
			
			String ExpectedText="The payment was successfully processed.";
			String ActualText = confirmpage.Success.getText().trim();
			Thread.sleep(2000);
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Order placed successfully. i.e. " +ExpectedText);
				Reporter.log("Success !! Order placed successfully. i.e. " +ExpectedText);
		
			}
			else
			{
				Thread.sleep(2000);
				System.out.println("Failure !! Error in order. Expected :" + ExpectedText + "\n"+
							"Actual :" +ActualText);
				Reporter.log("Failure !! Error in order. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
		
				AssertFailedCount++;
		
			}
			
			
			
		System.out.println("Step 8 : Verify Need Help section is absent at order Confirmation page");
		Reporter.log("Step 8 : Verify Need Help section is absent at order Confirmation page"); 
							
			if(!SeleniumFunc.IsElementPresent(homepage.Help))
			{
				Thread.sleep(2000);	
				System.out.println("Success !! Need Help section is absent at order Confirmation page");
				Reporter.log("Success !! Need Help section is absent at order Confirmation page");
					
			}
			else
			{
				Thread.sleep(2000);
				System.out.println("Failure !! Need Help section is present at order Confirmation page");
				Reporter.log("Failure !! Need Help section is present at order Confirmation page"); 
					
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
	 * Verify How does this work? content is displayed
	*/ 
	@Test
	private void VerifyHoeDoesThisWork() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify How does this work? content is displayed"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 4 : Verify How does this work? content is displayed"  + "\n" +
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
			
		
		System.out.println("Step 2 : Verify How does this work? is absent at home page");
		Reporter.log("Step 2 : Verify How does this work? is absent at home page"); 
				
			if(!SeleniumFunc.IsElementPresent(searchpage.Help2))
			{
				Thread.sleep(2000);
				System.out.println("Success !! How does this work? is absent at home page");
				Reporter.log("Success !! How does this work? is absent at home page");
		
			}
			else
			{
		
				System.out.println("Failure !! How does this work? is present at home page");
				Reporter.log("Failure !! How does this work? is present at home page"); 
		
				AssertFailedCount++;
				
			}
			
		System.out.println("Step 3 : Select state and course and go to search page");
		Reporter.log("Step 3 : Select state and course and go to search page"); 
						
			
			//Select state and course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(2000);		
		
		System.out.println("Step 4 : Verify How does this work? is present at search page");
		Reporter.log("Step 4 : Verify How does this work? is present at search page"); 
					
			if(SeleniumFunc.IsElementPresent(searchpage.Help2))
			{
				Thread.sleep(2000);
				System.out.println("Success !! How does this work? section is present at search page");
				Reporter.log("Success !! How does this work? section is present at search page");
			
			}
			else
			{
			
				System.out.println("Failure !! How does this work? section is missing at search page");
				Reporter.log("Failure !! How does this work? section is missing at search page"); 
			
				AssertFailedCount++;
				
			}
		
		
		System.out.println("Step 5 : Enter valid record and go to certification page");
		Reporter.log("Step 5 : Enter valid record and go to certification page"); 
		
			
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
			
		System.out.println("Step 6 : Verify How does this work? section is present at order replacement page");
		Reporter.log("Step 6 : Verify How does this work? section is present at order replacement page"); 
						
			if(SeleniumFunc.IsElementPresent(searchpage.Help2))
			{
				Thread.sleep(2000);
				System.out.println("Success !! How does this work? section is present at order replacement page");
				Reporter.log("Success !! How does this work? section is present at order replacement page");
				
			}
			else
			{
			
				System.out.println("Failure !! How does this work? section is missing at order replacement page");
				Reporter.log("Failure !! How does this work? section is missing at order replacement page"); 
				
				AssertFailedCount++;
					
			}
				
			
		System.out.println("Step 7 : Verify user can place order successfully");
		Reporter.log("Step 7 : Verify user can place order successfully"); 
			
		
			orderpage.FillBillingDetails("4111111111111111", "02-2038", "Automation", "Testing", "rohit@gmail.com", "rohit@gmail.com");
			orderpage.ClickOnSameBillingCheckbox();
			Thread.sleep(2000);
			orderpage.ClickOnPlaceOrderButton();
			Thread.sleep(5000);		
			
			
		System.out.println("Step 8 : Verify How does this work? section is absent at order Confirmation page");
		Reporter.log("Step 8 : Verify How does this work? section is absent at order Confirmation page"); 
							
			if(!SeleniumFunc.IsElementPresent(searchpage.Help2))
			{
				Thread.sleep(2000);		
				System.out.println("Success !! How does this work? section is absent at order Confirmation page");
				Reporter.log("Success !! How does this work? section is absent at order Confirmation page");
					
			}
			else
			{
				
				System.out.println("Failure !! How does this work? section is present at order Confirmation page");
				Reporter.log("Failure !! How does this work? section is present at order Confirmation page"); 
					
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
	 * Verify user can click on logo to return to home page
	*/ 
	@Test
	private void ClickLogoReturnHome() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify that Need help text is displayed"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 5 : Verify that Need help text is displayed"  + "\n" +
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
			
			Thread.sleep(5000);
				
		System.out.println("Step 2 : GO to any page and Click on logo and verify control redirected to home page");
		Reporter.log("Step 2 : GO to any page and Click on logo and verify control redirected to home page"); 
							
			
			//Select state and course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(2000);		
			//Search Page
			homepage.ClickOnLogo();
			Thread.sleep(2000);
			String ExpectedURL = Constants.ApplicationURL_ILMC ;
			String ActualURL = driver.getCurrentUrl();
			Thread.sleep(2000);
			if(ActualURL.contains(ExpectedURL))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Control redirected to home page from search page. i.e. " +ExpectedURL);
				Reporter.log("Success !! Control redirected to home page from search page. i.e. " +ExpectedURL);
			
			}
			else
			{
			
				System.out.println("Failure !! Control is not redirected to home page from search page. Expected :" + ExpectedURL + "\n"+
						"Actual :" +ActualURL);
				Reporter.log("Failure !! Control is not redirected to home page from search page. Expected:" + ExpectedURL + "\n"+
					"Actual :" +ActualURL); 
	
				AssertFailedCount++;
	
				
			}
		
			//Select State and Course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(2000);
			//Enter Certification Details
			searchpage.EnterFirstName("DONALD");
			searchpage.EnterLastName("SCHERER");
			searchpage.EnterDay("15");
			searchpage.EnterYear("1975");
			searchpage.SelectMonth("May");
			searchpage.ClickOnSearchButton();
			Thread.sleep(1000);
			
			
			//Certification Details Page
			homepage.ClickOnLogo();
			Thread.sleep(2000);
			ExpectedURL = Constants.ApplicationURL_ILMC ;
			ActualURL = driver.getCurrentUrl();
			Thread.sleep(2000);
			if(ActualURL.contains(ExpectedURL))
			{
				
				System.out.println("Success !! Control redirected to home page from Certification Details Page. i.e. " +ExpectedURL);
				Reporter.log("Success !! Control redirected to home page from Certification Details Page. i.e. " +ExpectedURL);
			
			}
			else
			{
			
				System.out.println("Failure !! Control is not redirected to home page from Certification Details Page. Expected :" + ExpectedURL + "\n"+
						"Actual :" +ActualURL);
				Reporter.log("Failure !! Control is not redirected to home page from Certification Details Page. Expected :" + ExpectedURL + "\n"+
					"Actual :" +ActualURL); 
	
				AssertFailedCount++;
	
				
			}
			
			//Select State and Course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(2000);
			//Enter Certification Details
			searchpage.EnterFirstName("DONALD");
			searchpage.EnterLastName("SCHERER");
			searchpage.EnterDay("15");
			searchpage.EnterYear("1975");
			searchpage.SelectMonth("May");
			searchpage.ClickOnSearchButton();
			Thread.sleep(1000);
			
			// go to order replacement page
			certipage.ClickOnConfirmCheckbox();
			Thread.sleep(2000);

			certipage.ClickOnOrderButton();
			Thread.sleep(4000);

			
			homepage.ClickOnLogo();
			Thread.sleep(2000);
			ExpectedURL = Constants.ApplicationURL_ILMC ;
			ActualURL = driver.getCurrentUrl();
			Thread.sleep(2000);
			if(ActualURL.contains(ExpectedURL))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Control redirected to home page from order replacement Page. i.e. " +ExpectedURL);
				Reporter.log("Success !! Control redirected to home page from order replacement Page. i.e. " +ExpectedURL);
			
			}
			else
			{
			
				System.out.println("Failure !! Control is not redirected to home page from order replacement Page. Expected :" + ExpectedURL + "\n"+
						"Actual :" +ActualURL);
				Reporter.log("Failure !! Control is not redirected to home page from order replacement Page. Expected :" + ExpectedURL + "\n"+
					"Actual :" +ActualURL); 
	
				AssertFailedCount++;
		
			}
			
			//Select State and Course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(2000);
			//Enter Certification Details
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
			
			//Go to Order confirmation page
			orderpage.FillBillingDetails("4111111111111111", "02-2038", "Automation", "Testing", "rohit@gmail.com", "rohit@gmail.com");
			orderpage.ClickOnSameBillingCheckbox();
			Thread.sleep(2000);
			orderpage.ClickOnPlaceOrderButton();
			Thread.sleep(2000);

			//Order Confirmation Page
			homepage.ClickOnLogo();
			
			ExpectedURL = Constants.ApplicationURL_ILMC;
			ActualURL = driver.getCurrentUrl();
			
			if(ActualURL.contains(ExpectedURL))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Control redirected to home page from Order Confirmation Page. i.e. " +ExpectedURL);
				Reporter.log("Success !! Control redirected to home page from Order Confirmation Page. i.e. " +ExpectedURL);
			
			}
			else
			{
			
				System.out.println("Failure !! Control is not redirected to home page from Order Confirmation Page. Expected :" + ExpectedURL + "\n"+
						"Actual :" +ActualURL);
				Reporter.log("Failure !! Control is not redirected to home page from Order Confirmation Page. Expected :" + ExpectedURL + "\n"+
					"Actual :" +ActualURL); 
	
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
	 * Verify Receipt Page Edits
	*/ 
	@Test
	private void VerifyReceiptEdit() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify Receipt Page Edits"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 6 : Verify Receipt Page Edits"  + "\n" +
	 				"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCCertificationPage certipage = new ILMCCertificationPage(driver);
		ILMCOrderPage orderpage = new ILMCOrderPage(driver);
		CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		
		ILMCOrderConfirmationPage confirmpage = new ILMCOrderConfirmationPage(driver);

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
			Thread.sleep(10000);
			
			
		System.out.println("Step 4 : Open record in ILMC, and search a certificate");
		Reporter.log("Step 4 : Open record in ILMC, and search a certificate");
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			Thread.sleep(4000);
			//Select state and course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			
			//Search
			searchpage.EnterFirstName(firstname);
			searchpage.EnterLastName("Testing");
			searchpage.EnterDay("24");
			searchpage.EnterYear("1990");
			searchpage.ClickOnSearchButton();
			
			Thread.sleep(2000);
			
			// go to order replacement page
			certipage.ClickOnConfirmCheckbox();
			certipage.ClickOnOrderButton();
			Thread.sleep(2000);
			
		System.out.println("Step 4 : Verify user can place order successfully");
		Reporter.log("Step 4 : Verify user can place order successfully"); 
			
		Thread.sleep(2000);
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
			
			
			
		System.out.println("Step 5 : Verify Receipt Edits");
		Reporter.log("Step 5 : Verify Receipt Edits"); 
			
			
			ExpectedText = "DALLAS, TX 75244";
			ActualText = SeleniumFunc.GetElementText("css", ".container-narrow.topContainer div p strong");
			Thread.sleep(2000);
			if(ActualText.contains(ExpectedText))
			{
				
				System.out.println("Success !! Receipt Edits for address is proper. i.e. " +ExpectedText);
				Reporter.log("Success !! Receipt Edits for address is proper. i.e. " +ExpectedText);
		
			}
			else
			{
		
				System.out.println("Failure !! Receipt Edits for address is NOT proper. Expected :" + ExpectedText + "\n"+
							"Actual :" +ActualText);
				Reporter.log("Failure !! Receipt Edits for address is NOT proper. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
		
				AssertFailedCount++;
		
			}
			
			//Share with your friends
	
			ExpectedText = "Share with your friends!";
			ActualText = SeleniumFunc.GetElementText("css", ".row-fluid.instructions h4");
			Thread.sleep(2000);
			if(ActualText.equals(ExpectedText))
			{
				
				System.out.println("Success !! Share with your friends text is proper. i.e. " +ExpectedText);
				Reporter.log("Success !! Share with your friends text is proper. i.e. " +ExpectedText);
		
			}
			else
			{
		
				System.out.println("Failure !! Share with your friends text is NOT proper. Expected :" + ExpectedText + "\n"+
							"Actual :" +ActualText);
				Reporter.log("Failure !! Share with your friends text is NOT proper. Expected :" + ExpectedText + "\n"+
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
