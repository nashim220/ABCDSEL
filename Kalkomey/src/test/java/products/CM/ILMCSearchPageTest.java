package products.CM;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.ILMCHomePage;
import pageFactory.CM.ILMCSearchPage;



import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class ILMCSearchPageTest 
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
	 * Verify UI of find certification
	*/ 
	@Test
	private void VerifyUISearchPage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify UI of find certification"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify UI of find certification"  + "\n" +
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
		
		System.out.println("Step 3 : Verify UI of search page");
		Reporter.log("Step 3 : Verify UI of search page"); 
		
			//Verify logo is present
			
			if (SeleniumFunc.IsElementPresent(homepage.Logo))
			{
				System.out.println("Success !! Logo is present");
				Reporter.log("Success !! Logo is present"); 
			}
			else
			{
				System.out.println("Failure !! Logo is missing");
				Reporter.log("Failure !! Logo is missing");
				
				AssertFailedCount++;
			}
			
			//Verify Header
			String ActualText = searchpage.Header.getText().trim();
			String ExpectedText = "Find Your Pennsylvania Hunter Safety Certification Record";
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Header is present with correct text. i.e. " +ExpectedText);
				Reporter.log("Success !! Header is present with correct text. i.e. " +ExpectedText);
				
			}
			else
			{
				
				System.out.println("Failure !! Incorrect Header. Expected :" + ExpectedText + "\n"+
									"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect Header. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
				
				AssertFailedCount++;
				
			}
			
			
			//Verify Agency
			ActualText = searchpage.Agency.getText().trim();
			ExpectedText = "Pennsylvania Game Commission";
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Agency name is present with correct text. i.e. " +ExpectedText);
				Reporter.log("Success !! Agency name is present with correct text. i.e. " +ExpectedText);
				
			}
			else
			{
				
				System.out.println("Failure !! Incorrect Agency name. Expected :" + ExpectedText + "\n"+
									"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect Agency name. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
				
				AssertFailedCount++;
				
			}
			
			
			//Verify Agency logo is present
			if (SeleniumFunc.IsElementPresent(homepage.Logo))
			{
				System.out.println("Success !! Agency Logo is present");
				Reporter.log("Success !! Agency Logo is present"); 
			}
			else
			{
				System.out.println("Failure !! Agency Logo is missing");
				Reporter.log("Failure !! Agency Logo is missing");
				
				AssertFailedCount++;
			}
			
			
			//Verify number line present along with 1 is highlighted 
			if (SeleniumFunc.IsElementPresent(searchpage.NumberLine) && SeleniumFunc.IsElementPresent(searchpage.HighlitedNumber))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Number Line is present along with 1 as highlighted");
				Reporter.log("Success !! Number Line is present along with 1 as highlighted"); 
			}
			else
			{
				System.out.println("Failure !! Number Line is missing OR 1 is not highlighted");
				Reporter.log("Failure !! Number Line is missing OR 1 is not highlighted");
				
				AssertFailedCount++;
			}
			
			
			
			//Click on logo and verify control redirected to home page
			homepage.ClickOnLogo();
			ActualText = SeleniumFunc.ToGetCurrentPageUrl(); 
			if(ActualText.contains(Constants.ApplicationURL_ILMC))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Control is redirected to Home page. i.e. " +Constants.ApplicationURL_ILMC);
				Reporter.log("Success !! Control is redirected to Home page. i.e. " +Constants.ApplicationURL_ILMC); 
			}
			else
			{
				System.out.println("Failure !! Control is redirected to Home page. Expected : "+Constants.ApplicationURL_ILMC 
						+ "\n" + "Actual : " + ActualText);
				Reporter.log("Failure !! Control is redirected to Home page. Expected : "+Constants.ApplicationURL_ILMC 
						+ "\n" + "Actual : " + ActualText);
				
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
	 * Verify "Records Not Found " page
	*/ 
	@Test
	private void VerifyRecordsNotFoundPage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify Records Not Found  page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 2 : Verify Records Not Found  page"  + "\n" +
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
			Thread.sleep(1000);
		
		System.out.println("Step 3 : Enter invalid record and verify record not found page");
		Reporter.log("Step 3 : Enter invalid record and verify record not found page"); 
		
			searchpage.EnterFirstName("Auto");
			searchpage.EnterLastName("Test");
			searchpage.EnterDay("20");
			searchpage.EnterYear("1990");
			searchpage.ClickOnSearchButton();
			Thread.sleep(1000);
			//Verify logo is present
			
			if (SeleniumFunc.IsElementPresent(homepage.Logo))
			{
				System.out.println("Success !! Logo is present");
				Reporter.log("Success !! Logo is present"); 
			}
			else
			{
				System.out.println("Failure !! Logo is missing");
				Reporter.log("Failure !! Logo is missing");
				
				AssertFailedCount++;
			}
			
			//Verify Header
			String ActualText = searchpage.Header.getText().trim();
			String ExpectedText = "Record Not Found";
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Header is present with correct text. i.e. " +ExpectedText);
				Reporter.log("Success !! Header is present with correct text. i.e. " +ExpectedText);
				
			}
			else
			{
				
				System.out.println("Failure !! Incorrect Header. Expected :" + ExpectedText + "\n"+
									"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect Header. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
				
				AssertFailedCount++;
				
			}
			
			
			//Verify Agency
			ActualText = searchpage.Agency.getText().trim();
			ExpectedText = "Pennsylvania Game Commission";
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Agency name is present with correct text. i.e. " +ExpectedText);
				Reporter.log("Success !! Agency name is present with correct text. i.e. " +ExpectedText);
				
			}
			else
			{
				
				System.out.println("Failure !! Incorrect Agency name. Expected :" + ExpectedText + "\n"+
									"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect Agency name. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
				
				AssertFailedCount++;
				
			}

			
			//Verify error message
			ActualText = searchpage.ErrorMessage.getText().trim();
			ExpectedText = "We were unable to find the following Hunting safety record:";
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Correct error message is displayed. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct error message is displayed. i.e. " +ExpectedText);
				
			}
			else
			{
				
				System.out.println("Failure !! Incorrect Error Message. Expected :" + ExpectedText + "\n"+
									"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect Error Message. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
				
				AssertFailedCount++;
				
			}	
			
			
			//Verify Breadcrumb
			ActualText = searchpage.Breadcrumb.getText().trim();
			ExpectedText = "Home / Search / Error";
			
			if(ActualText.equals(ExpectedText))
			{
				
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
			
			searchpage.ClickOnSearchAgainLink();
			Thread.sleep(1000);
			
			//Verify number line present along with 1 is highlighted 
			if (SeleniumFunc.IsElementPresent(searchpage.NumberLine) && SeleniumFunc.IsElementPresent(searchpage.HighlitedNumber))
			{
				System.out.println("Success !! Number Line is present along with 1 as highlighted");
				Reporter.log("Success !! Number Line is present along with 1 as highlighted"); 
			}
			else
			{
				System.out.println("Failure !! Number Line is missing OR 1 is not highlighted");
				Reporter.log("Failure !! Number Line is missing OR 1 is not highlighted");
				
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
	 * Verify user is able to perform search
	*/ 
	@Test
	private void VerifySearchFunctionality() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify user is able to perform search"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 3 : Verify user is able to perform search"  + "\n" +
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
			Thread.sleep(1000);
		
		System.out.println("Step 3 : Keep fields empty and click on search");
		Reporter.log("Step 3 : Keep fields empty and click on search"); 
		
			searchpage.ClickOnSearchButton();
			
			//Verify Error message
			String ActualText = searchpage.ErrorMessage.getText().trim();
			String ExpectedText = "Something’s wrong. Please review the fields below.";
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Correct error message is displayed. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct error message is displayed. i.e. " +ExpectedText);	
			}
			else
			{
				
				System.out.println("Failure !! Incorrect Error Message. Expected :" + ExpectedText + "\n"+
									"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect Error Message. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
				
				AssertFailedCount++;
				
			}
				
			
			searchpage.EnterDay("RO");
			searchpage.ClickOnSearchButton();
			Thread.sleep(1000);
			
			//Verify Invalid day validation
			ActualText = searchpage.ErrorDay.getText().trim();
			ExpectedText = "Please provide a day in a two-digit format.";
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Correct error message is displayed. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct error message is displayed. i.e. " +ExpectedText);
				
			}
			else
			{
				
				System.out.println("Failure !! Incorrect Error Message. Expected :" + ExpectedText + "\n"+
									"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect Error Message. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
				
				AssertFailedCount++;
				
			}
			

			searchpage.EnterYear("12");

			//Verify Invalid Year validation
			ActualText = searchpage.ErrorYear.getText().trim();
			ExpectedText = "Please complete this field.";
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Correct error message is displayed. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct error message is displayed. i.e. " +ExpectedText);
				
			}
			else
			{
				
				System.out.println("Failure !! Incorrect Error Message. Expected :" + ExpectedText + "\n"+
									"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect Error Message. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
				
				AssertFailedCount++;
				
			}
			
			
			
		System.out.println("Step 4 : Enter invalid record and verify record not found page");
		Reporter.log("Step 4 : Enter invalid record and verify record not found page"); 
		
			
			searchpage.EnterFirstName("DONALD");
			searchpage.EnterLastName("SCHERER");
			searchpage.EnterDay("15");
			searchpage.EnterYear("1975");
			searchpage.SelectMonth("May");
			searchpage.ClickOnSearchButton();
			Thread.sleep(1000);
			
			//Verify logo is present
			
			if (SeleniumFunc.IsElementPresent(homepage.Logo))
			{
				System.out.println("Success !! Logo is present");
				Reporter.log("Success !! Logo is present"); 
			}
			else
			{
				System.out.println("Failure !! Logo is missing");
				Reporter.log("Failure !! Logo is missing");
				
				AssertFailedCount++;
			}
			
			//Verify Header
			ActualText = searchpage.Header.getText().trim();
			ExpectedText = "Review Your Pennsylvania Hunting Safety Certification Record";
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Header is present with correct text. i.e. " +ExpectedText);
				Reporter.log("Success !! Header is present with correct text. i.e. " +ExpectedText);
				
			}
			else
			{
				
				System.out.println("Failure !! Incorrect Header. Expected :" + ExpectedText + "\n"+
									"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect Header. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
				
				AssertFailedCount++;
				
			}
			
			
			//Verify Agency
			ActualText = searchpage.Agency.getText().trim();
			ExpectedText = "Pennsylvania Game Commission";
			
			if(ActualText.equals(ExpectedText))
			{
				
				System.out.println("Success !! Agency name is present with correct text. i.e. " +ExpectedText);
				Reporter.log("Success !! Agency name is present with correct text. i.e. " +ExpectedText);
				
			}
			else
			{
				
				System.out.println("Failure !! Incorrect Agency name. Expected :" + ExpectedText + "\n"+
									"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect Agency name. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
				
				AssertFailedCount++;
				
			}

			
			
			
			//Verify Breadcrumb
			ActualText = searchpage.Breadcrumb.getText().trim();
			ExpectedText = "Home / Search / Certification Record";
			
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
			
			
			//Verify Success message
			ActualText = searchpage.Success.getText().trim();
			ExpectedText = "We found a matching Hunting safety certification. Please review all the details below.";
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Success message is displayed. i.e. " +ExpectedText);
				Reporter.log("Success !! Success message is displayed. i.e. " +ExpectedText);
				
			}
			else
			{
				
				System.out.println("Failure !! Incorrect Success message. Expected :" + ExpectedText + "\n"+
									"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect Success message. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
				
				AssertFailedCount++;
				
			}
			
			
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
