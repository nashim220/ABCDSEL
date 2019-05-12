package products.CM;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.CertificationDetailsPage;
import pageFactory.CM.CreateNewCertificationPage;
import pageFactory.CM.GlobalHeader;
import pageFactory.CM.LoginPage;
import pageFactory.CM.SearchCertificationPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class CreateCertificationTest 
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
	 * Create Certification - Verify the elements on 'Create New Certification' page
	*/ 
	@Test
	private void CreateCertification_UIElementVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Create Certification - Verify the elements on 'Create New Certification' page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Create Certification - Verify the elements on 'Create New Certification' page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to User Create New Certification page");
		Reporter.log("Step 2 : Login to application and navigating to User Create New Certification page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			
			searchcerti.ClickOnNewCertificate();
			
			
		System.out.println("Step 3 : Verifying elements on Create New Certification page");
		Reporter.log("Step 3 : Verifying elements on Create New Certification page"); 		
		
		
			//Verifying page header
			String actualtext= createnewcerti.PageHeader.getText().trim();
			String expectedtext= "Create New Certification"; 
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! page title displayed i.e. " + actualtext);
				Reporter.log("Success !! page title displayed i.e." + actualtext); 
			}
			else
			{
				System.out.println("Failure !! incorrect page title displayed i.e." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  incorrect page title displayed i.e." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
		
			
			//Verifying presence of First Name field
			if(SeleniumFunc.IsElementPresent(createnewcerti.FirstName))
			{
				
				System.out.println("Success !! First Name Textbox is present");
				Reporter.log("Success !! First Name Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! First Name Textbox is NOT present ");
				Reporter.log("Failure !! First Name Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			//Verifying presence of Middle Name field
			if(SeleniumFunc.IsElementPresent(createnewcerti.MiddleName))
			{
				
				System.out.println("Success !! Middle Name Textbox is present");
				Reporter.log("Success !! Middle Name Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! Middle Name Textbox is NOT present ");
				Reporter.log("Failure !! Middle Name Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			//Verifying presence of Last Name field
			if(SeleniumFunc.IsElementPresent(createnewcerti.LastName))
			{
				
				System.out.println("Success !! Last Name Textbox is present");
				Reporter.log("Success !! Last Name Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! Last Name Textbox is NOT present ");
				Reporter.log("Failure !! Last Name Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			//Verifying presence of Suffix field
			if(SeleniumFunc.IsElementPresent(createnewcerti.Suffix))
			{
				
				System.out.println("Success !! Suffix Textbox is present");
				Reporter.log("Success !! Suffix Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! Suffix Textbox is NOT present ");
				Reporter.log("Failure !! Suffix Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			//Verifying presence of DOB field
			if(SeleniumFunc.IsElementPresent(createnewcerti.DOB))
			{
				
				System.out.println("Success !! DOB Textbox is present");
				Reporter.log("Success !! DOB Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! DOB Textbox is NOT present ");
				Reporter.log("Failure !! DOB Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			//Verifying presence of Email field
			if(SeleniumFunc.IsElementPresent(createnewcerti.EmailID))
			{
				
				System.out.println("Success !! Email Textbox is present");
				Reporter.log("Success !! Email Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! Email Textbox is NOT present ");
				Reporter.log("Failure !! Email Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			
			//Verifying presence of County dropdown field
			if(SeleniumFunc.IsElementPresent(createnewcerti.CountryID))
			{
				
				System.out.println("Success !! County dropdown is present");
				Reporter.log("Success !! County dropdown is present"); 
			}
			else
			{
				System.out.println("Failure !! County dropdown is NOT present ");
				Reporter.log("Failure !! County dropdown is NOT present "); 
				AssertFailedCount++;
			}
			
			//Verifying presence of Street Address field
			if(SeleniumFunc.IsElementPresent(createnewcerti.Address1))
			{
				
				System.out.println("Success !! Street Address Textbox is present");
				Reporter.log("Success !! Street Address Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! Street Address Textbox is NOT present ");
				Reporter.log("Failure !! Street Address Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			
			//Verifying presence of City field
			if(SeleniumFunc.IsElementPresent(createnewcerti.City))
			{
				
				System.out.println("Success !! City Textbox is present");
				Reporter.log("Success !! City Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! City Textbox is NOT present ");
				Reporter.log("Failure !! City Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			//Verifying presence of State field
			if(SeleniumFunc.IsElementPresent(createnewcerti.State))
			{
				
				System.out.println("Success !! State dropdown is present");
				Reporter.log("Success !! State dropdown is present"); 
			}
			else
			{
				System.out.println("Failure !! State dropdown is NOT present ");
				Reporter.log("Failure !! State dropdown is NOT present "); 
				AssertFailedCount++;
			}
			
			//Verifying presence of Zip field
			if(SeleniumFunc.IsElementPresent(createnewcerti.Zip))
			{
				
				System.out.println("Success !! Zip Textbox is present");
				Reporter.log("Success !! Zip Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! Zip Textbox is NOT present ");
				Reporter.log("Failure !! Zip Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			//Verifying presence of Phone field
			if(SeleniumFunc.IsElementPresent(createnewcerti.Phone))
			{
				
				System.out.println("Success !! Phone Textbox is present");
				Reporter.log("Success !! Phone Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! Phone Textbox is NOT present ");
				Reporter.log("Failure !! Phone Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			//Verifying presence of "Has different mailing address?" checkbox field
			if(SeleniumFunc.IsElementPresent(createnewcerti.AddressCheckbox))
			{
				
				System.out.println("Success !! 'Has different mailing address?' checkbox is present");
				Reporter.log("Success !! 'Has different mailing address?' checkbox is present"); 
			}
			else
			{
				System.out.println("Failure !! 'Has different mailing address?' checkbox is NOT present ");
				Reporter.log("Failure !! 'Has different mailing address?' checkbox is NOT present "); 
				AssertFailedCount++;
			}
			
			//Verifying presence of Jurisdiction field
			if(SeleniumFunc.IsElementPresent(createnewcerti.Jurisdictions))
			{
				
				System.out.println("Success !! Jurisdiction dropdown is present");
				Reporter.log("Success !! Jurisdiction dropdown is present"); 
			}
			else
			{
				System.out.println("Failure !! Jurisdiction dropdown is NOT present ");
				Reporter.log("Failure !! Jurisdiction dropdown is NOT present "); 
				AssertFailedCount++;
			}
			
			//Verifying presence of Certification Collection field
			if(SeleniumFunc.IsElementPresent(createnewcerti.CollectionID))
			{
				
				System.out.println("Success !! Certification Collection dropdown is present");
				Reporter.log("Success !! Certification Collection dropdown is present"); 
			}
			else
			{
				System.out.println("Failure !! Certification Collection dropdown is NOT present ");
				Reporter.log("Failure !! Certification Collection dropdown is NOT present "); 
				AssertFailedCount++;
			}
			
			
			//Verifying presence of Status field
			if(SeleniumFunc.IsElementPresent(createnewcerti.Status))
			{
				
				System.out.println("Success !! Status dropdown is present");
				Reporter.log("Success !! Status dropdown is present"); 
			}
			else
			{
				System.out.println("Failure !! Status dropdown is NOT present ");
				Reporter.log("Failure !! Status dropdown is NOT present "); 
				AssertFailedCount++;
			}
			
			//Verifying presence of Certification Date field
			if(SeleniumFunc.IsElementPresent(createnewcerti.CertiDate))
			{
				
				System.out.println("Success !! Certification Date Textbox is present");
				Reporter.log("Success !! Certification Date Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! Certification Date Textbox is NOT present ");
				Reporter.log("Failure !! Certification Date Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			//Verifying presence of Last Issue Date field
			if(SeleniumFunc.IsElementPresent(createnewcerti.LastIssued))
			{
				
				System.out.println("Success !! Last Issue Date Textbox is present");
				Reporter.log("Success !! Last Issue Date Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! Last Issue Date Textbox is NOT present ");
				Reporter.log("Failure !! Last Issue Date Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			
			//Verifying presence of Gender field
			if(SeleniumFunc.IsElementPresent(createnewcerti.Gender))
			{
				
				System.out.println("Success !! Gender Textbox is present");
				Reporter.log("Success !! Gender Textbox is present"); 
			}
			else
			{
				System.out.println("Failure !! Gender Textbox is NOT present ");
				Reporter.log("Failure !! Gender Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			//Verifying presence of Height field
			if(SeleniumFunc.IsElementPresent(createnewcerti.Height))
			{
				
				System.out.println("Success !! Height Textbox is present");
				Reporter.log("Success !! Height Textboxis present"); 
			}
			else
			{
				System.out.println("Failure !! Height Textbox is NOT present ");
				Reporter.log("Failure !! Height Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			
			//Verifying presence of Weight field
			if(SeleniumFunc.IsElementPresent(createnewcerti.Weight))
			{
				
				System.out.println("Success !! Weight Textbox is present");
				Reporter.log("Success !! Weight Textboxis present"); 
			}
			else
			{
				System.out.println("Failure !! Weight Textbox is NOT present ");
				Reporter.log("Failure !! Weight Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			//Verifying presence of Eye color field
			if(SeleniumFunc.IsElementPresent(createnewcerti.EyeColor))
			{
				
				System.out.println("Success !! Eye color Textbox is present");
				Reporter.log("Success !! Eye color Textboxis present"); 
			}
			else
			{
				System.out.println("Failure !! Eye color Textbox is NOT present ");
				Reporter.log("Failure !! Eye color Textbox is NOT present "); 
				AssertFailedCount++;
			}
			
			
			//Verifying presence of Hair  color field
			if(SeleniumFunc.IsElementPresent(createnewcerti.HairColor))
			{
				
				System.out.println("Success !! Hair  color Textbox is present");
				Reporter.log("Success !! Hair  color Textboxis present"); 
			}
			else
			{
				System.out.println("Failure !! Hair  color Textbox is NOT present ");
				Reporter.log("Failure !! Hair  color Textbox is NOT present "); 
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
	 * Create Certification - Verify user is able to create new Certification successfully
	*/ 
	@Test
	private void CreateCertification_CreateNew() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Create Certification - Verify user is able to create new Certification successfully"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Create Certification - Verify user is able to create new Certification successfully"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		CertificationDetailsPage certidetails = new CertificationDetailsPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to User Create New Certification page");
		Reporter.log("Step 2 : Login to application and navigating to User Create New Certification page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			
			Thread.sleep(2000);
			searchcerti.ClickOnNewCertificate();
			Thread.sleep(2000);
			
		System.out.println("Step 3 : Creating new Certificate");
		Reporter.log("Step 3 : Creating new Certificate"); 		
		
			String firstname= "certi" + JH.GenerateRandomNumber();
			String emailprefix = "certi" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 Proton Rd";
		
			createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			createnewcerti.ClickOnContinueButton();
			Thread.sleep(10000);
			
			//Verifying alert message
			String actualtext= gheader.Success_AlertText.getText().trim();
			String expectedtext= "Certification was successfully created."; 
			Thread.sleep(2000);
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! alert message is displayed i.e. " + actualtext);
				Reporter.log("Success !! alert message is displayed i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			Thread.sleep(2000);
			String ActualResult = certidetails.Name.getText().trim();
			String ExpectedResult = firstname + " testing"; 
			
			if(ActualResult.contains(ExpectedResult))
			{	
				System.out.println("Success !! Name is correct i.e. " + ActualResult);
				Reporter.log("Success !! Name is correct" + ActualResult);
			}
			else
			{
				System.out.println("Failure !! Name is incorrect " + "\n" + "Expected : "  
						+ "\n" + ExpectedResult + "\n" + 
						 "Actual : " + "\n" +  ActualResult);
				System.out.println("Failure !! Name is incorrect" + "\n" + "Expected : "  
						+ "\n" + ExpectedResult + "\n" + 
						 "Actual : " + "\n" +  ActualResult);
				
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
	 * Create Certification - Verify user is able to create new Certification with Mailing Address
	*/ 
	@Test
	private void CreateCertification_CreateNew_WithMailingAddress() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Create Certification - Verify user is able to create new Certification with Mailing Address"  + "\n" + "====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Create Certification - Verify user is able to create new Certification with Mailing Address"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		CertificationDetailsPage certidetails = new CertificationDetailsPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to User Create New Certification page");
		Reporter.log("Step 2 : Login to application and navigating to User Create New Certification page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(2000);
			searchcerti.ClickOnNewCertificate();
			Thread.sleep(2000);
			
		System.out.println("Step 3 : Creating new Certificate with Mailing Address");
		Reporter.log("Step 3 : Creating new Certificate with Mailing Address"); 		
		
			String firstname= "certi" + JH.GenerateRandomNumber();
			String emailprefix = "certi" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 Proton Rd";
		
			createnewcerti.FillCertificationForm(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			createnewcerti.AddressCheckbox.click();
			Thread.sleep(2000);
			createnewcerti.FillMailingAddress("United States", "8850 NW Meadowlark Rd", "Whitewater", "67154", "Kansas", "234-567-8910");
			createnewcerti.ClickOnContinueButton();
			Thread.sleep(10000);
			
			//Verifying alert message
			String actualtext= gheader.Success_AlertText.getText().trim();
			String expectedtext= "Certification was successfully created."; 
			Thread.sleep(2000);
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! alert message is displayed i.e. " + actualtext);
				Reporter.log("Success !! alert message is displayed i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			
			String ActualResult = certidetails.Name.getText().trim();
			String ExpectedResult = firstname + " testing"; 
			Thread.sleep(2000);
			if(ActualResult.contains(ExpectedResult))
			{	
				System.out.println("Success !! Name is correct i.e. " + ActualResult);
				Reporter.log("Success !! Name is correct" + ActualResult);
			}
			else
			{
				System.out.println("Failure !! Name is incorrect " + "\n" + "Expected : "  
						+ "\n" + ExpectedResult + "\n" + 
						 "Actual : " + "\n" +  ActualResult);
				System.out.println("Failure !! Name is incorrect" + "\n" + "Expected : "  
						+ "\n" + ExpectedResult + "\n" + 
						 "Actual : " + "\n" +  ActualResult);
				
				AssertFailedCount++;
			}
			Thread.sleep(2000);
			
			
			//Verifying Mailing address
			ActualResult = certidetails.MailingAddress.getText().trim();
			ExpectedResult = "Mailing Address" + "\n" + "8850 NW Meadowlark Rd" + "\n" + "Whitewater KS 67154"; 
			Thread.sleep(2000);

			if(ActualResult.contains(ExpectedResult))
			{	
				System.out.println("Success !! Mailing address is correct i.e. " + ActualResult);
				Reporter.log("Success !! Mailing address is correct" + ActualResult);
			}
			else
			{
				System.out.println("Failure !! Mailing address is incorrect " + "\n" + "Expected : "  
						+ "\n" + ExpectedResult + "\n" + 
						 "Actual : " + "\n" +  ActualResult);
				System.out.println("Failure !! Mailing address is incorrect" + "\n" + "Expected : "  
						+ "\n" + ExpectedResult + "\n" + 
						 "Actual : " + "\n" +  ActualResult);
				
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
	 * Create Certification - Verify validation messages for mandatory fields
	*/ 
	@Test
	private void CreateCertification_MandatoryFieldsValidation() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Create Certification - Verify validation messages for mandatory fields"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Create Certification - Verify validation messages for mandatory fields"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to User Create New Certification page");
		Reporter.log("Step 2 : Login to application and navigating to User Create New Certification page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(2000);
			searchcerti.ClickOnNewCertificate();
			Thread.sleep(2000);
			
		System.out.println("Step 3 : On Create new Certificate page, click on 'Continue' without entering anydata and verifying validation messages");
		Reporter.log("Step 3 : On Create new Certificate page, click on 'Continue' without entering anydata and verifying validation messages"); 		
		
				createnewcerti.ClickOnContinueButton();
				Thread.sleep(2000);
			
				//Verifying alert message
				String actualtext= gheader.Error_AlertText.getText().trim();
				String expectedtext= "There was a problem saving this certification. Please review and resolve any errors below."; 
				Thread.sleep(2000);
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! alert message is displayed i.e. " + actualtext);
					Reporter.log("Success !! alert message is displayed i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual : " + "\n" +  actualtext);
					Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
			
			
				//Verifying validation for First Name field
				actualtext= createnewcerti.FirstName_Error.getText().trim();
				expectedtext= "Please provide a first name."; 
				Thread.sleep(2000);
				if(actualtext.equals(expectedtext))
				{
					Thread.sleep(2000);
					System.out.println("Success !! correct validation message is displayed for First Name field i.e. " + actualtext);
					Reporter.log("Success !! correct validation message is displayed for First Name field i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! incorrect validation message displayed for First Name field " + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual : " + "\n" +  actualtext);
					Reporter.log("Failure !! incorrect validation message displayed for First Name field " + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
	
			
				//Verifying validation for Last Name field
				actualtext= createnewcerti.LastName_Error.getText().trim();
				expectedtext= "Please provide a last name."; 
				Thread.sleep(2000);
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! correct validation message is displayed for Last Name field i.e. " + actualtext);
					Reporter.log("Success !! correct validation message is displayed for Last Name field i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! incorrect validation message displayed for Last Name field " + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual : " + "\n" +  actualtext);
					Reporter.log("Failure !! incorrect validation message displayed for Last Name field " + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				//Verifying validation for DOB field
				actualtext= createnewcerti.DOB_Error.getText().trim();
				expectedtext= "Please provide a complete date of birth."; 
				Thread.sleep(2000);
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! correct validation message is displayed for DOB field i.e. " + actualtext);
					Reporter.log("Success !! correct validation message is displayed for DOB field i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! incorrect validation message displayed for DOB field " + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual : " + "\n" +  actualtext);
					Reporter.log("Failure !! incorrect validation message displayed for DOB field " + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				//Verifying validation for Country field
				actualtext= createnewcerti.CountryID_Error.getText().trim();
				expectedtext= "Please complete this field."; 
				
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! correct validation message is displayed for Country field i.e. " + actualtext);
					Reporter.log("Success !! correct validation message is displayed for Country field i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! incorrect validation message displayed for Country field " + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual : " + "\n" +  actualtext);
					Reporter.log("Failure !! incorrect validation message displayed for Country field " + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
	
				
				//Verifying validation for Street Address field
				actualtext= createnewcerti.Address1_Error.getText().trim();
				expectedtext= "Please provide the street address of residence."; 
				
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! correct validation message is displayed for Street Address field i.e. " + actualtext);
					Reporter.log("Success !! correct validation message is displayed for Street Address field i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! incorrect validation message displayed for Street Address field " + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual : " + "\n" +  actualtext);
					Reporter.log("Failure !! incorrect validation message displayed for Street Address field " + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying validation for City field
				actualtext= createnewcerti.City_Error.getText().trim();
				expectedtext= "Please provide the city of residence."; 
				Thread.sleep(2000);
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! correct validation message is displayed for City field i.e. " + actualtext);
					Reporter.log("Success !! correct validation message is displayed for City field i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! incorrect validation message displayed for City field " + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual : " + "\n" +  actualtext);
					Reporter.log("Failure !! incorrect validation message displayed for City field " + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying validation for Zip field
				actualtext= createnewcerti.Zip_Error.getText().trim();
				expectedtext= "Please provide the zip code of residence."; 
				
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! correct validation message is displayed for Zip field i.e. " + actualtext);
					Reporter.log("Success !! correct validation message is displayed for Zip field i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! incorrect validation message displayed for Zip field " + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual : " + "\n" +  actualtext);
					Reporter.log("Failure !! incorrect validation message displayed for Zip field " + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying validation for Certification Collection field
				actualtext= createnewcerti.CollectionID_Error.getText().trim();
				expectedtext= "Please complete this field."; 
				
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! correct validation message is displayed for Certification Collection field i.e. " + actualtext);
					Reporter.log("Success !! correct validation message is displayed for Certification Collection field i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! incorrect validation message displayed for Certification Collection field " + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual : " + "\n" +  actualtext);
					Reporter.log("Failure !! incorrect validation message displayed for Certification Collection field " + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying validation for Status field
				actualtext= createnewcerti.Status_Error.getText().trim();
				expectedtext= "Please complete this field."; 
				Thread.sleep(2000);
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! correct validation message is displayed for Status field i.e. " + actualtext);
					Reporter.log("Success !! correct validation message is displayed for Status field i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! incorrect validation message displayed for Status field " + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual : " + "\n" +  actualtext);
					Reporter.log("Failure !! incorrect validation message displayed for Status field " + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying validation for Certification Date field
				actualtext= createnewcerti.CertiDate_Error.getText().trim();
				expectedtext= "Please provide a complete date."; 
				Thread.sleep(2000);
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! correct validation message is displayed for Certification Date field i.e. " + actualtext);
					Reporter.log("Success !! correct validation message is displayed for Certification Date field i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! incorrect validation message displayed for Certification Date field " + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual : " + "\n" +  actualtext);
					Reporter.log("Failure !! incorrect validation message displayed for Certification Date field " + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual : " + "\n" +  actualtext);
					
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
	 * Create Certification - Verify validation messages for US and Canada Zip formats
	*/ 
	@Test
	private void CreateCertification_USCanadaZipVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Create Certification - Verify validation messages for US and Canada Zip formats"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Create Certification - Verify validation messages for US and Canada Zip formats"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to User Create New Certification page");
		Reporter.log("Step 2 : Login to application and navigating to User Create New Certification page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(2000);
			searchcerti.ClickOnNewCertificate();
			Thread.sleep(2000);
			
		System.out.println("Step 3 : On Create new Certificate page, Selecting Country = US and entring invalid zip");
		Reporter.log("Step 3 : On Create new Certificate page, Selecting Country = US and entring invalid zip"); 		
		
				createnewcerti.SelectCoutry("United States");
				createnewcerti.EnterZip("56");
				createnewcerti.ClickOnContinueButton();
				Thread.sleep(2000);
			
				
				//Verifying validation for Zip field
				String actualtext= createnewcerti.Zip_Error.getText().trim();
				String expectedtext= "This zip code is invalid. Please use the format: 12345 or 12345-6789"; 
				
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! correct validation message is displayed for Zip field i.e. " + actualtext);
					Reporter.log("Success !! correct validation message is displayed for Zip field i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! incorrect validation message displayed for Zip field " + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual : " + "\n" +  actualtext);
					Reporter.log("Failure !! incorrect validation message displayed for Zip field " + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				Thread.sleep(2000);
				driver.navigate().refresh();
				Thread.sleep(2000);
				
		System.out.println("Step 4 : On Create new Certificate page, Selecting Country = Canada and entring invalid zip");
		Reporter.log("Step 4 : On Create new Certificate page, Selecting Country = Canada and entring invalid zip"); 		
		
				createnewcerti.SelectCoutry("Canada");
				createnewcerti.EnterZip("56");
				createnewcerti.ClickOnContinueButton();
				Thread.sleep(2000);
			
				
				//Verifying validation for Zip field
				actualtext= createnewcerti.Zip_Error.getText().trim();
				expectedtext= "This postal code is invalid. Please use the format: X0X 0X0"; 
				
				if(actualtext.equals(expectedtext))
				{
					System.out.println("Success !! correct validation message is displayed for Zip field i.e. " + actualtext);
					Reporter.log("Success !! correct validation message is displayed for Zip field i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! incorrect validation message displayed for Zip field " + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual : " + "\n" +  actualtext);
					Reporter.log("Failure !! incorrect validation message displayed for Zip field " + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual : " + "\n" +  actualtext);
					
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
	 * Create Certification - Verify that No state-specific language exists.
	*/ 
	@Test
	private void CreateCertification_NoStateSpecificLanguageExists() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Create Certification - Verify that No state-specific language exists."  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Create Certification - Verify that No state-specific language exists."  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to User Create New Certification page");
		Reporter.log("Step 2 : Login to application and navigating to User Create New Certification page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(2000);
			searchcerti.ClickOnNewCertificate();
			Thread.sleep(2000);
			
		System.out.println("Step 3 : On Create new Certificate page, Verifying that no mention of the word 'affidavit' ");
		Reporter.log("Step 3 : On Create new Certificate page, Verifying that no mention of the word 'affidavit' "); 		
		
				
			
				if(!driver.getPageSource().contains("affidavit"))
				{
					Thread.sleep(2000);
					System.out.println("Success !! no mention of the word 'affidavit'");
					Reporter.log("Success !! no mention of the word 'affidavit'"); 
				}
				else
				{
					System.out.println("Failure !! there is a mention of the word 'affidavit'");
					Reporter.log("Failure !! there is a mention of the word 'affidavit'");
					
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
	 * Create Certification - Verify that valid data is added to the correct collection
	*/ 
	@Test
	private void CreateCertification_VerifyCollection() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Create Certification - Verify that valid data is added to the correct collection"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Create Certification - Verify that valid data is added to the correct collection"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		CertificationDetailsPage certidetails = new CertificationDetailsPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to User Create New Certification page");
		Reporter.log("Step 2 : Login to application and navigating to User Create New Certification page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(2000);
			searchcerti.ClickOnNewCertificate();
			Thread.sleep(2000);
			
		System.out.println("Step 3 : Creating new Certificate");
		Reporter.log("Step 3 : Creating new Certificate"); 		
		
			String firstname= "certi" + JH.GenerateRandomNumber();
			String emailprefix = "certi" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 Proton Rd";
			String juridication="PA";
			String collection="Hunter Safety Education (1959 - 1985)";
			Thread.sleep(2000);
			createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas",juridication, collection, "Pass", "03/02/2015", "04/02/2015");
		    createnewcerti.ClickOnContinueButton();
			Thread.sleep(10000);
			Thread.sleep(2000);
			//Verifying alert message
			String actualtext= gheader.Success_AlertText.getText().trim();
			String expectedtext= "Certification was successfully created."; 
			Thread.sleep(2000);
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! alert message is displayed i.e. " + actualtext);
				Reporter.log("Success !! alert message is displayed i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			Thread.sleep(2000);
			String ActualResult = certidetails.Name.getText().trim();
			String ExpectedResult = firstname + " testing"; 
			Thread.sleep(2000);
			if(ActualResult.contains(ExpectedResult))
			{	
				System.out.println("Success !! Name is correct i.e. " + ActualResult);
				Reporter.log("Success !! Name is correct" + ActualResult);
			}
			else
			{
				System.out.println("Failure !! Name is incorrect " + "\n" + "Expected : "  
						+ "\n" + ExpectedResult + "\n" + 
						 "Actual : " + "\n" +  ActualResult);
				System.out.println("Failure !! Name is incorrect" + "\n" + "Expected : "  
						+ "\n" + ExpectedResult + "\n" + 
						 "Actual : " + "\n" +  ActualResult);
				
				AssertFailedCount++;
			}
		
			
			ActualResult = certidetails.CertificationCollectionName.getText().trim();
			ExpectedResult = collection; 
			Thread.sleep(2000);
			if(ActualResult.contains(ExpectedResult))
			{	
				System.out.println("Success !! Certification Collection is correct i.e. " + ActualResult);
				Reporter.log("Success !! Certification Collection is correct" + ActualResult);
			}
			else
			{
				System.out.println("Failure !! Certification Collection is incorrect " + "\n" + "Expected : "  
						+ "\n" + ExpectedResult + "\n" + 
						 "Actual : " + "\n" +  ActualResult);
				System.out.println("Failure !! Certification Collection is incorrect" + "\n" + "Expected : "  
						+ "\n" + ExpectedResult + "\n" + 
						 "Actual : " + "\n" +  ActualResult);
				
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
	 * Create Certification - Verify that user is notified for possible duplicate certification
	*/ 
	@Test
	private void CreateCertification_Duplicate() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Create Certification - Verify that user is notified for possible duplicate certification"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Create Certification - Verify that user is notified for possible duplicate certification"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		CertificationDetailsPage certidetails = new CertificationDetailsPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to User Create New Certification page");
		Reporter.log("Step 2 : Login to application and navigating to User Create New Certification page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(4000);
			searchcerti.ClickOnNewCertificate();
			Thread.sleep(3000);
			
		System.out.println("Step 3 : Creating new Certificate");
		Reporter.log("Step 3 : Creating new Certificate"); 		
		
			String firstname= "certi" + JH.GenerateRandomNumber();
			String emailprefix = "certi" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 Proton Rd";
		
			createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			Thread.sleep(2000);
			createnewcerti.ClickOnContinueButton();
			Thread.sleep(10000);
			
			//Verifying alert message
			String actualtext= gheader.Success_AlertText.getText().trim();
			String expectedtext= "Certification was successfully created."; 
			Thread.sleep(2000);
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(2000);
				System.out.println("Success !! alert message is displayed i.e. " + actualtext);
				Reporter.log("Success !! alert message is displayed i.e. " + actualtext); 
			}
			else
			{
				Thread.sleep(2000);
				System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			Thread.sleep(2000);
			String ActualResult = certidetails.Name.getText().trim();
			String ExpectedResult = firstname + " testing"; 
			Thread.sleep(2000);
			if(ActualResult.contains(ExpectedResult))
			{	
				Thread.sleep(2000);
				System.out.println("Success !! Name is correct i.e. " + ActualResult);
				Reporter.log("Success !! Name is correct" + ActualResult);
			}
			else
			{
				Thread.sleep(2000);
				System.out.println("Failure !! Name is incorrect " + "\n" + "Expected : "  
						+ "\n" + ExpectedResult + "\n" + 
						 "Actual : " + "\n" +  ActualResult);
				System.out.println("Failure !! Name is incorrect" + "\n" + "Expected : "  
						+ "\n" + ExpectedResult + "\n" + 
						 "Actual : " + "\n" +  ActualResult);
				
				AssertFailedCount++;
			}
		
	
		System.out.println("Step 4 : Try to create duplicate Certificate");
		Reporter.log("Step 4 : Try to create duplicate new Certificate"); 				
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM + "/certifications/new");
			
			 Thread.sleep(5000);
			 createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			Thread.sleep(3000);
			createnewcerti.ClickOnContinueButton();
			Thread.sleep(10000);
			Thread.sleep(2000);
			//Verifying alert message
			actualtext= gheader.Notice_AlertText.getText().trim();
			Thread.sleep(2000);
			expectedtext= "We've sent you to the latest certification for this student. To view the older certification you just tried to access, click here."; 
			Thread.sleep(2000);
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(1000);
				System.out.println("Success !! alert message is displayed i.e. " + actualtext);
				Reporter.log("Success !! alert message is displayed i.e. " + actualtext); 
			}
			else
			{
				Thread.sleep(2000);
				System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
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
	 * Create Certification - Verify that jurisdiction selection available only for KE admin and not for other Agency user.
	*/ 
	@Test
	private void CreateCertification_NonKEAdminUser() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Create Certification - Verify that jurisdiction selection available only for KE admin and not for other Agency user."  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 : Create Certification - Verify that jurisdiction selection available only for KE admin and not for other Agency user."  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);

		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application as Non KE admin user and navigating to User Create New Certification page");
		Reporter.log("Step 2 : Login to application as Non KE admin user and navigating to User Create New Certification page"); 	
		
			login.EnterUsername(Constants.CM_Username_Verginia);
			login.EnterPassword(Constants.CM_Password_Verginia);
			login.ClickOnLogInButton();
			Thread.sleep(2000);
			searchcerti.ClickOnNewCertificate();
			Thread.sleep(2000);
			
		System.out.println("Step 3 : Verifying that jurisdiction dropdown is not present");
		Reporter.log("Step 3 : Verifying that jurisdiction dropdown is not present"); 		
		
		Thread.sleep(2000);
			
			if(!SeleniumFunc.IsElementPresent(createnewcerti.Jurisdictions))
			{
				System.out.println("Success !! jurisdiction dropdown is not present");
				Reporter.log("Success !! jurisdiction dropdown is not present"); 
			}
			else
			{
				System.out.println("Failure !! jurisdiction dropdown is present");
				Reporter.log("Failure !! jurisdiction dropdown is present");
				
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
	 * Verify Review Duplicate Certification
	*/ 
	@Test
	private void ReviewDuplicateCertificate() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : Verify Review Duplicate Certification"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10 : Verify Review Duplicate Certification"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		CertificationDetailsPage certidetails = new CertificationDetailsPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to User Create New Certification page");
		Reporter.log("Step 2 : Login to application and navigating to User Create New Certification page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(4000);
			searchcerti.ClickOnNewCertificate();
			Thread.sleep(3000);
			
		System.out.println("Step 3 : Creating new Certificate");
		Reporter.log("Step 3 : Creating new Certificate"); 		
		
			String firstname= "certi" + JH.GenerateRandomNumber();
			String emailprefix = "certi" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 Proton Rd";
		
			createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			Thread.sleep(2000);
			createnewcerti.ClickOnContinueButton();
			Thread.sleep(10000);
			
			//Verifying alert message
			String actualtext= gheader.Success_AlertText.getText().trim();
			String expectedtext= "Certification was successfully created."; 
			Thread.sleep(2000);
		
			String URL = driver.getCurrentUrl();
			
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(2000);
				System.out.println("Success !! alert message is displayed i.e. " + actualtext);
				Reporter.log("Success !! alert message is displayed i.e. " + actualtext); 
			}
			else
			{
				Thread.sleep(2000);
				System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			Thread.sleep(2000);
		
			String ActualResult = certidetails.Name.getText().trim();
			String ExpectedResult = firstname + " testing"; 
			
			if(ActualResult.contains(ExpectedResult))
			{	
				Thread.sleep(2000);
				System.out.println("Success !! Name is correct i.e. " + ActualResult);
				Reporter.log("Success !! Name is correct" + ActualResult);
			}
			else
			{
				Thread.sleep(2000);
				System.out.println("Failure !! Name is incorrect " + "\n" + "Expected : "  
						+ "\n" + ExpectedResult + "\n" + 
						 "Actual : " + "\n" +  ActualResult);
				System.out.println("Failure !! Name is incorrect" + "\n" + "Expected : "  
						+ "\n" + ExpectedResult + "\n" + 
						 "Actual : " + "\n" +  ActualResult);
				
				AssertFailedCount++;
			}
		
		System.out.println("Step 4 : Try to create duplicate Certificate");
		Reporter.log("Step 4 : Try to create duplicate new Certificate"); 				
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM + "/certifications/new");
			Thread.sleep(5000);
			
			createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			Thread.sleep(3000);
			
			createnewcerti.ClickOnContinueButton();
			Thread.sleep(12000);
			
			
			//Verifying alert message
			actualtext= gheader.Notice_AlertText.getText().trim();
			
			expectedtext= "We've sent you to the latest certification for this student. To view the older certification you just tried to access, click here."; 
			
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(1000);
				System.out.println("Success !! alert message is displayed i.e. " + actualtext);
				Reporter.log("Success !! alert message is displayed i.e. " + actualtext); 
			}
			else
			{
				Thread.sleep(2000);
				System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
		System.out.println("Step 5 : Click on Link to view older certificate");
		Reporter.log("Step 5 : Click on Link to view older certificate"); 						
			
			gheader.ClickHereLink.click();
			Thread.sleep(4000);
			
			actualtext = driver.getCurrentUrl();
			//expectedtext = URL + "?force=true";
			expectedtext = "?force=true";
			
			if(actualtext.endsWith(expectedtext))
			{
				Thread.sleep(1000);
				System.out.println("Success !! User is redirected to proper page." + actualtext);
				Reporter.log("Success !! User is redirected to proper page." + actualtext); 
			}
			else
			{
				Thread.sleep(2000);
				System.out.println("Failure !! User is NOT redirected to expected page." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !! User is NOT redirected to expected page." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
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
