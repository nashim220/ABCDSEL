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
import pageFactory.CM.ILMCSearchPage;
import pageFactory.CM.LoginPage;
import pageFactory.CM.SearchCertificationPage;



import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class ILMCCertificationTest 
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
	 * Verify UI of Certification record page
	*/ 
	@Test
	private void Certification_UI() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify UI of Certification record page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify UI of Certification record page"  + "\n" +
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
							
			
		System.out.println("Step 3 : Enter valid record and go to certification page");
		Reporter.log("Step 3 : Enter valid record and go to certification page"); 
			
				
		searchpage.EnterFirstName("DONALD");
		searchpage.EnterLastName("SCHERER");
		searchpage.EnterDay("15");
		searchpage.EnterYear("1975");
		searchpage.SelectMonth("May");
		searchpage.ClickOnSearchButton();
		Thread.sleep(1000);
				
				
		System.out.println("Step 4 : Verify UI of certification page");
		Reporter.log("Step 4 : Verify UI of certification page"); 
						
			
			//Verify number line present along with 1 is highlighted 
			if (SeleniumFunc.IsElementPresent(searchpage.NumberLine) && SeleniumFunc.IsElementPresent(certipage.HighlitedNumber2))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Number Line is present along with 2 as highlighted");
				Reporter.log("Success !! Number Line is present along with 2 as highlighted"); 
			}
			else
			{
				System.out.println("Failure !! Number Line is missing OR 2 is not highlighted");
				Reporter.log("Failure !! Number Line is missing OR 2 is not highlighted");
					
				AssertFailedCount++;
			}	
			
			
			//Verify Breadcrumb
			String ActualText = searchpage.Breadcrumb.getText().trim();
			String ExpectedText = "Home / Search / Certification Record";
		
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
			
			
			//Verify title
			ActualText = searchpage.Header.getText().trim();
			ExpectedText = "Review Your Pennsylvania Hunting Safety Certification Record";
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Correct title is displayed. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct title is displayed. i.e. " +ExpectedText);
				
			}
			else
			{
				
				System.out.println("Failure !! Incorrect title. " +	"Expected :" + ExpectedText + "\n"+
									"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect title. " +	"Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
				
				AssertFailedCount++;
				
			}
				
			
			//Verify certification record is present
			if (SeleniumFunc.IsElementPresent(certipage.CertificationRecord))
			{
				System.out.println("Success !! Certification record is present");
				Reporter.log("Success !! Certification record is present"); 
			}
			else
			{
				System.out.println("Failure !! Certification record missing");
				Reporter.log("Failure !! Certification record missing");
					
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
	 * Verify presence of support content
	*/ 
	@Test
	private void VerifySupportContent() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify presence of support content"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify presence of support content"  + "\n" +
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
				
				
		System.out.println("Step 4 : Verify Support text is present with correct text");
		Reporter.log("Step 4 : Verify Support text is present with correct text"); 
						
			
			//Verify Support text
			Thread.sleep(1000);
			String ActualText = certipage.SupportText.getText().trim();
			String ExpectedText = "If one or both addresses are incorrect, you may edit your addresses now." + "\n"+
					"If this is not your record, contact Kalkomey customer service at support@ilostmycard.com " +
					"or by calling 800-830-2268.";
		
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Support text is present with correct text. i.e. " +ExpectedText);
				Reporter.log("Success !! Support text is present with correct text. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! Incorrect Support text. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect Support text. Expected :" + ExpectedText + "\n"+
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
	 * Verify user can not place order if he does not select 'reviewed details'
	*/ 
	
	@Test
	private void VerifyOrderButtonState() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify user can not place order if he does not select 'reviewed details'"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify user can not place order if he does not select 'reviewed details'"  + "\n" +
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
							
			
		System.out.println("Step 3 : Enter valid record and go to certification page");
		Reporter.log("Step 3 : Enter valid record and go to certification page"); 
			
				
		searchpage.EnterFirstName("DONALD");
		searchpage.EnterLastName("SCHERER");
		searchpage.EnterDay("15");
		searchpage.EnterYear("1975");
		searchpage.SelectMonth("May");
		searchpage.ClickOnSearchButton();
		Thread.sleep(1000);
				
				
		System.out.println("Step 4 : Verifying Order button is disabled");
		Reporter.log("Step 4 : Verifying Order button is disabled"); 
				
			boolean actualValue = certipage.Order.isEnabled();
			if (!actualValue)
			{
				Thread.sleep(1000);
				System.out.println("Success !! User can not place order as review details is not selected");
				Reporter.log("Success !! User can not place order as review details is not selected"); 
			}
			else
			{
				System.out.println("Failure !! User can place order as review details is not selected");
				Reporter.log("Failure !! User can place order as review details is not selected"); 
		
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
	 * Single record match- the successful match is displayed
	*/ 
	@Test
	private void SigleMatchRecord() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Single record match- the successful match is displayed"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Single record match- the successful match is displayed"  + "\n" +
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
							
			
		System.out.println("Step 3 : Enter valid record and go to certification page");
		Reporter.log("Step 3 : Enter valid record and go to certification page"); 
			
				
				searchpage.EnterFirstName("DONALD");
				searchpage.EnterLastName("SCHERER");
				searchpage.EnterDay("15");
				searchpage.EnterYear("1975");
				searchpage.SelectMonth("May");
				searchpage.ClickOnSearchButton();
				Thread.sleep(1000);
				
				
		System.out.println("Step 4 : Verify Single match record is displayed");
		Reporter.log("Step 4 : Verify Single match record is displayed"); 
						
				
			
			//Verify certification record is present
			if (SeleniumFunc.IsElementPresent(certipage.CertificationRecord))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Single match record is displayed");
				Reporter.log("Success !! Single match record is displayed"); 
			}
			else
			{
				System.out.println("Failure !! Single match record is missing");
				Reporter.log("Failure !! Single match record is missing");
					
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
	 * Verify that 'Edit your address' button is not displayed AZ (button is present for rest)
	*/ 
	@Test
	private void NoEditButtonNonPA() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify that 'Edit your address' button is not displayed AZ (button is present for rest)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify that 'Edit your address' button is not displayed AZ (button is present for rest)"  + "\n" +
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
			homepage.SelectState("California");
			Thread.sleep(2000);
			homepage.SelectCollection("Boat Ed");
			homepage.ClickOnContinueButton();
			Thread.sleep(1000);				
			
		System.out.println("Step 3 : Enter valid record and go to certification page");
		Reporter.log("Step 3 : Enter valid record and go to certification page"); 
			
				
			searchpage.EnterFirstName("Sachin Testing002");
			searchpage.EnterLastName("Today");
			searchpage.EnterDay("01");
			searchpage.EnterYear("2000");
			searchpage.SelectMonth("March");
			searchpage.ClickOnSearchButton();
				
				
		System.out.println("Step 4 : Verify Edit button is missing for AZ records");
		Reporter.log("Step 4 : Verify Edit button is missing for AZ records"); 
						
				
			
			//Verify certification record is present
			if (!SeleniumFunc.IsElementPresent(certipage.EditAddress))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Edit button is missing for AZ records");
				Reporter.log("Success !! Edit button is missing for AZ records"); 
			}
			else
			{
				System.out.println("Failure !! Edit button is dispayed for AZ records");
				Reporter.log("Failure !! Edit button is dispayed for AZ records");
					
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
	 * Verify validations on Edit address page
	*/ 
	@Test
	private void VerifyValidationEditAddress() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify validations on Edit address page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Verify validations on Edit address page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCCertificationPage certipage = new ILMCCertificationPage(driver);
		ILMCEditAddressPage editadd = new ILMCEditAddressPage(driver);
		
		
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
							
			
		System.out.println("Step 3 : Enter valid record and go to certification page");
		Reporter.log("Step 3 : Enter valid record and go to certification page"); 
			
				
		searchpage.EnterFirstName("DONALD");
		searchpage.EnterLastName("SCHERER");
		searchpage.EnterDay("15");
		searchpage.EnterYear("1975");
		searchpage.SelectMonth("May");
		searchpage.ClickOnSearchButton();
		Thread.sleep(1000);
				
			//Go to edit address
			certipage.ClickOnEditAddressButton();
			editadd.FillAddressDetails("United States", "", "", "", "");
			Thread.sleep(1000);
			editadd.ClickOnContinueButton();
			Thread.sleep(3000);
			
		System.out.println("Step 4 : Verify validation is displayed");
		Reporter.log("Step 4 : Verify validation is displayed"); 
		
			String ExpectedText = "Please complete this field.";
			String ActualText = editadd.StateError.getText().trim();
			Thread.sleep(1000);
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Validation is displayed. i.e. " +ExpectedText);
				Reporter.log("Success !! Validation is displayed. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! No Validation. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! No Validation. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
			
				AssertFailedCount++;
			
			}
			Thread.sleep(1000);
			editadd.SelectState("Pennsylvania");
			editadd.ClickOnContinueButton();
			Thread.sleep(3000);
			editadd.AddressPhysicalCheck.click();
			Thread.sleep(3000);
			editadd.ClickOnSaveButton();
			//Verify Validation
			ExpectedText = "There was a problem setting your changes. Please review and resolve any errors below.";
			ActualText = editadd.Error.getText().trim();
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Validation is displayed for all blank. i.e. " +ExpectedText);
				Reporter.log("Success !! Validation is displayed for all blank. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! No Validation for all blank. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! No Validation for all blank. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
			
				AssertFailedCount++;
			
			}
						
			//Invalid ZIP for US
			editadd.FillAddressDetails("United States", "14086 PROTON DR", "DALLAS", "Pennsylvania", "123");
			editadd.ClickOnContinueButton();
			Thread.sleep(3000);
			editadd.ClickOnAddressCheckButton();
			editadd.AddressPhysicalCheck.click();
			Thread.sleep(3000);
			editadd.ClickOnSaveButton();
			Thread.sleep(3000);
			//Verify validation for invalid zip
			
			ExpectedText = "Please provide a zip code (for example: 12345).";
			ActualText = editadd.ZipError.getText().trim();
			
			if(ActualText.equals(ExpectedText))
			{
			
				System.out.println("Success !! Validation is displayed for invalid zip. i.e. " +ExpectedText);
				Reporter.log("Success !! Validation is displayed for invalid zip. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! No Validation for invalid zip. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! No Validation for invalid zip. Expected :" + ExpectedText + "\n"+
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
	
	
	
	
	
	/* Test 7: 
	 * Testing a name with a suffix 
	*/ 
	@Test
	private void VerifyNameWithSuffix() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Testing a name with a suffix"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Testing a name with a suffix"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		
		CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
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
		
			newcerti.CreateNewCertificate(firstname, "Testing", "01/24/1990", emailaddress, "United States",  streetaddress,"DALLAS", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			newcerti.EnterSuffix("IV");
			newcerti.ClickOnContinueButton();
			Thread.sleep(10000);
			
			
		System.out.println("Step 4 : Open record in ILMC, and verify suffix is present");
		Reporter.log("Step 4 : Open record in ILMC, and verify suffix is present");
			
			Thread.sleep(2000);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			Thread.sleep(8000);
			//Select state and course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(2000);
			//Search Record
			searchpage.EnterFirstName(firstname);
			searchpage.EnterLastName("Testing");
			searchpage.EnterDay("24");
			searchpage.EnterYear("1990");
			searchpage.ClickOnSearchButton();
			
			Thread.sleep(2000);
			
			
			//Verify Suffix is present
			String ExpectedText =  firstname + " Testing, IV";
			String ActualText = SeleniumFunc.GetElementText("css", ".certRecord dd:nth-of-type(1)");
			Thread.sleep(2000);
			if(ActualText.equalsIgnoreCase(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Suffix is present. i.e. " +ExpectedText);
				Reporter.log("Success !! Suffix is present. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! Suffix is missing. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Suffix is missing. Expected :" + ExpectedText + "\n"+
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
	
	
	
	/* Test 8: 
	 * Testing a name without a suffix 
	*/ 
	@Test
	private void VerifyNameWithoutSuffix() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Testing a name without a suffix "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Testing a name without a suffix "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		
		CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
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
			Thread.sleep(10000);
			
			
		System.out.println("Step 4 : Open record in ILMC, and verify suffix is absent");
		Reporter.log("Step 4 : Open record in ILMC, and verify suffix is absent");
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			
			//Select state and course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(1000);
			//Search Record
			searchpage.EnterFirstName(firstname);
			searchpage.EnterLastName("Testing");
			searchpage.EnterDay("24");
			searchpage.EnterYear("1990");
			searchpage.ClickOnSearchButton();
			
			Thread.sleep(4000);
				
		
			//Verify Suffix
			
			String ExpectedText =  firstname + " Testing";
			String ActualText = SeleniumFunc.GetElementText("css", ".certRecord dd:nth-of-type(1)");
			Thread.sleep(2000);
			if(ActualText.equalsIgnoreCase(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Suffix is absent. i.e. " +ExpectedText);
				Reporter.log("Success !! Suffix is absent. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! Suffix is present. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Suffix is present. Expected :" + ExpectedText + "\n"+
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
	
	
	
	
	/* Test 9: 
	 * Verify if multiple records are present , most recent one is displayed
	*/ 
	@Test
	private void MultipleNonPAError() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Verify if multiple records are present , most recent one is displayed"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 : Verify if multiple records are present , most recent one is displayed"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		
		CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
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
		Reporter.log("Step 3 Adding new ceritificate"); 
			
		    Thread.sleep(2000);
			certi.ClickOnNewCertificate();
			Thread.sleep(2000);
			String firstname= "certificate" + JH.GenerateRandomNumber();
			System.out.println(firstname);
			String emailprefix = "certificate" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 PROTON RD";
			Thread.sleep(2000);
			newcerti.CreateNewCertificate(firstname, "Testing", "01/24/1990", emailaddress, "United States",  streetaddress,"DALLAS", "75244", "Texas", "CO", "Boat Colorado Course", "Pass", "03/02/2015", "04/02/2015");
			newcerti.ClickOnContinueButton();
			Thread.sleep(10000);
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM + "/certifications/new");
			Thread.sleep(2000);
			newcerti.CreateNewCertificate(firstname, "Testing", "01/24/1990", emailaddress, "United States",  streetaddress,"DALLAS", "75244", "Texas", "CO", "Boat Colorado Course", "Pass", "03/02/2015", "04/02/2015");
			newcerti.ClickOnContinueButton();
			Thread.sleep(10000);
			
			
		System.out.println("Step 4 : Verify ILMC displaying error for non PA identical records");
		Reporter.log("Step 4 : Verify ILMC displaying error for non PA identical records");
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			
			Thread.sleep(4000);
			//Select state and course
			homepage.SelectState("Colorado");
			Thread.sleep(2000);
			homepage.SelectCollection("Boat Ed");
			Thread.sleep(2000);
			homepage.ClickOnContinueButton();
			
			//Search Record
			searchpage.EnterFirstName(firstname);
			searchpage.EnterLastName("Testing");
			searchpage.EnterDay("24");
			searchpage.EnterYear("1990");
			searchpage.ClickOnSearchButton();
			
			Thread.sleep(2000);
				
		
			//Verify Recent Certificate is displayed
			String ExpectedText = "April 02, 2015";
			String ActualText = SeleniumFunc.GetElementText("css", ".certRecord dl dd:nth-of-type(6)");
			Thread.sleep(2000);
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! ILMC displaying most recent one records. i.e. " +ExpectedText);
				Reporter.log("Success !! ILMC displaying most recent one records. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! ILMC is NOT displaying most recent one records. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! ILMC is NOT displaying most recent one records. Expected :" + ExpectedText + "\n"+
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
	
	
	
	/* Test 10: 
	 * Verify for PA , if multiple records are present , most recent one is displayed 
	*/ 
	@Test
	private void MultiplePA() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : Verify for PA , if multiple records are present , most recent one is displayed"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10 : Verify for PA , if multiple records are present , most recent one is displayed"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		
		CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);

		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername("sanjeetk@clariontechnologies.co.in");
			login.EnterPassword("clarion@123");
			login.ClickOnLogInButton();
			Thread.sleep(2000);
			
		System.out.println("Step 3: Adding new ceritificate");
		Reporter.log("Step 3: Adding new ceritificate"); 
			
			certi.ClickOnNewCertificate();
			Thread.sleep(2000);
			String firstname= "certificate" + JH.GenerateRandomNumber();
			System.out.println(firstname);
			String emailprefix = "certificate" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 PROTON RD";
		
//			newcerti.CreateNewCertificate(firstname, "Testing", "01/24/1990", emailaddress, "United States",  streetaddress,"DALLAS", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
//			newcerti.ClickOnContinueButton();
//			Thread.sleep(10000);
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM + "/certifications/new");
			
			newcerti.CreateNewCertificate(firstname, "Testing", "01/24/1990", emailaddress, "United States",  streetaddress,"DALLAS", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/08/2015", "04/02/2015");
			newcerti.ClickOnContinueButton();
			Thread.sleep(10000);
			
			
		System.out.println("Step 4 : Verify ILMC displaying most recent one for PA identical records");
		Reporter.log("Step 4 : Verify ILMC displaying most recent one for PA identical records");
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			Thread.sleep(2000);
			//Select state and course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(2000);
			//Search Record
			searchpage.EnterFirstName(firstname);
			searchpage.EnterLastName("Testing");
			searchpage.EnterDay("24");
			searchpage.EnterYear("1990");
			searchpage.ClickOnSearchButton();
			
			Thread.sleep(8000);
				
		
			//Verify Recent Certificate is displayed
			String ExpectedText = "April 02, 2015";
			String ActualText = SeleniumFunc.GetElementText("css", ".certRecord dl dd:nth-of-type(6)");
			Thread.sleep(2000);
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! ILMC displaying most recent one for PA identical records. i.e. " +ExpectedText);
				Reporter.log("Success !! ILMC displaying most recent one for PA identical records. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! ILMC is NOT displaying most recent one for PA identical records. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! ILMC is NOT displaying most recent one for PA identical records. Expected :" + ExpectedText + "\n"+
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
