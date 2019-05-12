package courses.HunterEd;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.HunterRegistrationPage;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.RegistrationNewPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class RegistrationNewTest 
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
	@Test
	private void Registration() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify that student can register for course successfully"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify that student can register for course successfully"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationNewPage register = new RegistrationNewPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_Hunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_Hunter); 	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			PageHeader header = new PageHeader(driver); 
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
		
			
		System.out.println("Step 3 : Filling registration form and clicking on 'Create Account' button");
		Reporter.log("Step 3 : Filling registration form and clicking on 'Create Account' button"); 
		
			String username= "Hunter" + JH.GenerateRandomNumber();
			String emailaddress= username + "@mailinator.com";
			
			register.FillRegistrationForm(username, "August", "2", "1990", emailaddress, "clarion@123", "clarion@123");
			register.ClickOnCreateAccount();
	
			Thread.sleep(5000);
			
		System.out.println("Step 4 : Verifying whether user is registered successfully or not");
		Reporter.log("Step 4 : Verifying whether user is registered successfully or not"); 
			
			
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
	 * Verify that validation messages are displayed for mandatory fields
	*/ 
	@Test
	private void Registration_ValidationMessageForMandatoryFields() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify that validation messages are displayed for mandatory fields"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify that validation messages are displayed for mandatory fields"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationNewPage register = new RegistrationNewPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_Hunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_Hunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			PageHeader header = new PageHeader(driver); 
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
		System.out.println("Step 3 : Clicking on 'Create Account' button without entering any data on registration page");
		Reporter.log("Step 3 : Clicking on 'Create Account' button without entering any data on registration page"); 
	
			register.ClickOnCreateAccount();
	
		System.out.println("Step 4 : Verifying whether validation messages are displayed for mandatory fields");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for mandatory fields"); 
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#registration-form > div.form-alerts > div");
		//	String ActualValidationMessage= SeleniumFunc.GetElementText("css", ".alert.alert-danger");
			String ExpectedValidationMessage=
					//"×"+"\n"+
					"Please review and correct the following fields:" + "\n" + 
					"Username" + "\n" + 
					"E-mail Address" + "\n" + 
					"Password" + "\n" +
					"Confirm Password" + "\n"+
					"Year" ; 
			
			if(ActualValidationMessage.endsWith(ExpectedValidationMessage))
			//if(ActualValidationMessage.equals(ExpectedValidationMessage.trim()))
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


	
	/* Test 3: 
	 * Verify that validation message is displayed if input criteria doesn't match for 'Choose a Username' field 
	 * A) Enter special characters
	*/ 

@Test
	private void Registration_ValidationMessageForChooseUsername1() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify that validation message is displayed if input criteria doesn't match for 'Choose a Username' field - A) Enter special characters "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify that validation message is displayed if input criteria doesn't match for 'Choose a Username' field A) Enter special characters"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationNewPage register = new RegistrationNewPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_Hunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_Hunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
		System.out.println("Step 3 : Entering special characters in 'Choose a Username' field e.g $%%");
		Reporter.log("Step 3 : Entering special characters in 'Choose a Username' field e.g $%%"); 

			register.ChooseAUsername("%%GG");
			register.ClickOnCreateAccount();

	
		System.out.println("Step 4 : Verifying whether validation messages are displayed for 'Choose a Username' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for 'Choose a Username' field"); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#main p:nth-of-type(2)");
			String ExpectedValidationMessage= 
					"A valid username must be 4 to 32 characters long, with letters and numbers only."; 
			
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
	 *Verify that validation message is displayed if input criteria doesn't match for 'Choose a Username' field - 
	 *B) if username is < 4 charater or >32 character"
	*/ 
	@Test
	private void Registration_ValidationMessageForChooseUsername2() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify that validation message is displayed if input criteria doesn't match for 'Choose a Username' field - B) if username is < 4 charater or >32 character"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify that validation message is displayed if input criteria doesn't match for 'Choose a Username' field B) if username is < 4 charater or >32 character"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationNewPage register = new RegistrationNewPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_Hunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_Hunter); 
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			PageHeader header = new PageHeader(driver); 	
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
		System.out.println("Step 3 : Entering < 4 charater in 'Choose a Username' field e.g abc");
		Reporter.log("Step 3 : Entering < 4 charater in 'Choose a Username' field e.g abc"); 
	
			register.ChooseAUsername("pqc@");
			Thread.sleep(2000);
			
		System.out.println("Step 4 : Entering data in other field and click on 'Create Account' button");
		Reporter.log("Step 4 : Entering data in other field and click on 'Create Account' button");
			
			
			register.EnterDOB("January", "2", "1990");
			register.EnterEmailAddress("test@testing.com");
			register.EnterPassword("clarion@123");
			register.EnterConfirmPassword("clarion@123");
			
			register.ClickOnCreateAccount();
			
			Thread.sleep(2000);
			

		System.out.println("Step 5 : Verifying whether validation messages are displayed for 'Choose a Username' field");
		Reporter.log("Step 5 : Verifying whether validation messages are displayed for 'Choose a Username' field"); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#StudentModel_uname + p");
			String ExpectedValidationMessage= 
					"A valid username must be 4 to 32 characters long, with letters and numbers only."; 
			
			if(ActualValidationMessage.contains(ExpectedValidationMessage))
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

	
	
	/* Test 5: 
	 * Verify that validation message is displayed if input criteria doesn't match for 'Date of Birth' field 
       A) if year field has <4 chars"
	*/ 
	@Test
	private void Registration_ValidationMessageForDOB1() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify that validation message is displayed if input criteria doesn't match for 'Date of Birth' field A) if year field has >4 chars"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify that validation message is displayed if input criteria doesn't match for 'Date of Birth' field A) if year field has >4 chars"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationNewPage register = new RegistrationNewPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_Hunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_Hunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
		
			
		System.out.println("Step 3 : Entering <4 characters in DOB 'Year' field e.g 123");
		Reporter.log("Step 3 : Entering <4 characters in DOB 'Year' field e.g 123"); 
		
			register.EnterDOB("January", "1", "19");
			register.ChooseAUsername("TestDOB");
			register.EnterEmailAddress("piyush@testing.com");
			register.EnterPassword("clarion@123");
			register.EnterConfirmPassword("clarion@123");
			
			register.ClickOnCreateAccount();
			Thread.sleep(5000);
			register.ClickOnIUnderstandButton();
			Thread.sleep(5000);
		System.out.println("Step 4 : Verifying whether validation messages are displayed for DOB 'Year' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for DOB 'Year' field"); 
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#registration-form > fieldset > div.form-group.form-group-lg.has-error > div.help-block");
			//String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#errorbox-1");
											
			String ExpectedValidationMessage="Please enter the year in XXXX format, numbers only."; 
			//String ExpectedValidationMessage="Please complete your date of birth. You must be at least 9 years old."; 
			
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


	
	/* Test 6: 
	 * Verify that validation message is displayed if input criteria doesn't match for 'Date of Birth' field 
      B) if year field has invalid value e.g .asdd,$%$% etc.
	*/ 
	@Test
	private void Registration_ValidationMessageForDOB2() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify that validation message is displayed if input criteria doesn't match for 'Date of Birth' field B) if year field has invalid value e.g .asdd,$%$% etc. "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Verify that validation message is displayed if input criteria doesn't match for 'Date of Birth' field B) if year field has invalid value e.g .asdd,$%$% etc. "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationNewPage register = new RegistrationNewPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_Hunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_Hunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			PageHeader header = new PageHeader(driver); 	
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
		System.out.println("Step 3 : Entering invalid value in DOB year field e.g abcd");
		Reporter.log("Step 3 : Entering invalid value in DOB year field e.g abcd"); 
		
		System.out.println("Step 4 : Entering data in other field and click on 'Create Account' button");
		Reporter.log("Step 4 : Entering data in other field and click on 'Create Account' button");
			
			register.ChooseAUsername("dsfssffdfdf");
		
			register.EnterDOB("January", "2", "abcd");
			register.EnterEmailAddress("piyush@testing.com");
		
			register.ClickOnCreateAccount();
			Thread.sleep(Constants.ThreadWaitInmiliseconds);

		System.out.println("Step 5 : Verifying whether validation messages are displayed for 'DOB Year' field");
		Reporter.log("Step 5 : Verifying whether validation messages are displayed for 'DOB Year' field"); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#errorbox-1");
			String ExpectedValidationMessage= 
					"Please complete your date of birth. You must be at least 9 years old."; 
			
			if(ActualValidationMessage.equals(ExpectedValidationMessage))
			{
				System.out.println("Success !! correct validation messages are displayed for fields");
				Reporter.log("Success !! correct validation messages are displayed for fields"); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed for fields" + "\n" + "Expected Validation messages : "  
									+ "\n" + ExpectedValidationMessage + "\n" + 
									 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
				Reporter.log("Failure !!  Incorrect validation messages are displayed for fields" + "\n" + "Expected Validation messages : "  
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

	
	
	/* Test 7: 
	 * Verify that validation message is displayed if input criteria doesn't match for 'Email Address' field 
	*/ 
	@Test
	private void Registration_ValidationMessageForEmailAddress() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Verify that validation message is displayed if input criteria doesn't match for 'Email Address' field "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Verify that validation message is displayed if input criteria doesn't match for 'Email Address' field "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationNewPage register = new RegistrationNewPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_Hunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_Hunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			PageHeader header = new PageHeader(driver); 	
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
		System.out.println("Step 3 : Entering Invalid email address e.g asdadad");
		Reporter.log("Step 3 : Entering Invalid email address e.g asdadad"); 
		
			register.EnterEmailAddress("dfff");
			register.ChooseAUsername("");
			register.ClickOnCreateAccount();
	
		System.out.println("Step 4 : Verifying whether validation messages are displayed for 'Email Address' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for 'Email Address' field"); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "fieldset div:nth-of-type(2) p");
			String ExpectedValidationMessage= 
					"This email address is invalid. Please use the format: name@company.com";
			
			if(ActualValidationMessage.equals(ExpectedValidationMessage))
			{
				System.out.println("Success !! correct validation messages are displayed for fields");
				Reporter.log("Success !! correct validation messages are displayed for fields"); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed for fields" + "\n" + "Expected Validation messages : "  
									+ "\n" + ExpectedValidationMessage + "\n" + 
									 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
				Reporter.log("Failure !!  Incorrect validation messages are displayed for fields" + "\n" + "Expected Validation messages : "  
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
	
	
	/* Test 8: 
	 * Verify that Username must be unique for registration
	*/ 
	@Test
	private void Registration_UsernameUniqueCheck() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Verify that Username must be unique for registration "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Verify that Username must be unique for registration"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationNewPage register = new RegistrationNewPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_Hunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_Hunter); 
					
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
		
			
		System.out.println("Step 3 :Register as a new Student. Noting down choosed Username");
		Reporter.log("Register as a new Student. Noting down choosed Username"); 
		
			String username = "s" + JH.GenerateRandomNumber();
			System.out.println(username);
			register.FillRegistrationForm(username,  "January","3","1990","selenium@test.com","clarion@123", "clarion@123");
			
			register.ClickOnCreateAccount();
			
			//Logging out of application
			//SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/site/logout");
		
			Thread.sleep(5000);
			
		System.out.println("Step 4 : Trying to register with same Username");
		Reporter.log("Step 4 : Trying to register with same Username"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter + "/sign-up");
		
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{
				
				register.ClickOnIUnderstandButton();
				
			}
			System.out.println(username);
			register.FillRegistrationForm(username, "January","3","1990","selenium@test.com", "clarion@123", "clarion@123");
			
			Thread.sleep(4000);
			
			register.ClickOnCreateAccount();
			Thread.sleep(4000);

		System.out.println("Step 5 : Verifying whether validation messages are displayed for 'Username' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for 'Username' field"); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#StudentModel_uname + p");
			String ExpectedValidationMessage= 
					"This username is taken. Please choose another."; 
		
			
			if(ActualValidationMessage.equals(ExpectedValidationMessage))
			{
				System.out.println("Success !! correct validation messages are displayed for fields");
				Reporter.log("Success !! correct validation messages are displayed for fields"); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed for fields" + "\n" + "Expected Validation messages : "  
									+ "\n" + ExpectedValidationMessage + "\n" + 
									 "Actual Validation messages : " + "\n" +  ActualValidationMessage);
				Reporter.log("Failure !!  Incorrect validation messages are displayed for fields" + "\n" + "Expected Validation messages : "  
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


	
	/* Test 9: 
	 * Verify title of page i.e. Register for the Boat {State_Hunter} Course
	*/ 
	@Test
	private void Registration_VerifyTitle() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Verify title of page i.e. Register for the {State} Hunter Course"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 : Verify title of page i.e. Register for the Hunter {State} Hunter Course"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_Hunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_Hunter); 
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
		System.out.println("Step 3 : Verifying Page Title i.e. Register for the {State} Hunter Course ");
		Reporter.log("Step 3 : Verifying Page Title i.e. Register for the Boat {State} Hunter Course "); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", ".h1-sign-up");
			//System.out.println(ActualValidationMessage);
											
			String ExpectedValidationMessage="Create an Account"; 
			//System.out.println(ExpectedValidationMessage);
			
			if(ActualValidationMessage.equalsIgnoreCase(ExpectedValidationMessage))
			{
				System.out.println("Success !! Page Title is correct");
				Reporter.log("Success !! Page Title is correct"); 
			}
			else
			{
				System.out.println("Failure !!  Page Title is incorrect" + "\n" + "Expected Title: "  
									+ "\n" + ExpectedValidationMessage + "\n" + 
									 "Actual Title : " + "\n" +  ActualValidationMessage);
				Reporter.log("Failure !!  Page Title is incorrect" + "\n" + "Expected Title: "  
						+ "\n" + ExpectedValidationMessage + "\n" + 
						 "Actual Title : " + "\n" +  ActualValidationMessage);
				
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
	 * Verify that accepted input data massage is displayed (for all fields)
	*/ 
	@Test
	private void Registration_InputDataMessageCheck() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : Verify that accepted input data massage is displayed (for all fields)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10 : Verify that accepted input data massage is displayed (for all fields)"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_Hunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_Hunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			PageHeader header = new PageHeader(driver); 	
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
		
			
		System.out.println("Step 3 : Verifying input data message text for 'Choose a Username' field");
		Reporter.log("Step 3 : Verifying input data message text for 'Choose a Username' field"); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#student-username-help");
			//System.out.println(ActualValidationMessage);
											
			String ExpectedValidationMessage1="4";
			String ExpectedValidationMessage2 ="32 characters, letters and numbers only"; 
			//System.out.println(ExpectedValidationMessage);
			
			if(ActualValidationMessage.startsWith(ExpectedValidationMessage1) && ActualValidationMessage.endsWith(ExpectedValidationMessage2))
			{
				System.out.println("Success!! input data message text is correctly displayed for 'Choose a Username' field");
				Reporter.log("Success!! input data message text is correctly displayed for 'Choose a Username' field"); 
			}
			else
			{
				System.out.println("Failure !!  input data message text is correctly displayed for 'Choose a Username' field" + "\n" + "Expected Text: "  
									+ "\n" + ExpectedValidationMessage1 + ExpectedValidationMessage2 +"\n" + 
									 "Actual Text : " + "\n" +  ActualValidationMessage);
				Reporter.log("Failure !!  input data message text is correctly displayed for 'Choose a Username' field" + "\n" + "Expected Text: "  
									+ "\n" + ExpectedValidationMessage1 + ExpectedValidationMessage2 +"\n" + 
									 "Actual Text : " + "\n" +  ActualValidationMessage);
				
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
	 * Verify that account creation email is received with details
	*/ 
	@Test
	private void Registration_VerifyAccountCreationEmail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 11 : Verify that account creation email is received with details"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 11 : Verify that account creation email is received with details"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_Hunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_Hunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			PageHeader header = new PageHeader(driver);

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
		
			
		System.out.println("Step 3 : Filling registration form and clicking on 'Create Account' button");
		Reporter.log("Step 3 : Filling registration form and clicking on 'Create Account' button"); 
		
			String username= "sele" + JH.GenerateRandomNumber();
			String emailprefix = "Sel" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			register.FillRegistrationForm(username,  "January", "2", "1990", emailaddress, "clarion@123", "clarion@123");
			register.ClickOnCreateAccount();
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
			
		System.out.println("Step 4 : Navigating to Email Box and verifying whether email is received with correct details or not");
		Reporter.log("Step 4 : Navigating to Email Box and verifying whether email is received with correct details or not"); 
			
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=" + emailprefix + "#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
			
			String ActualMessageTitle= SeleniumFunc.GetElementText("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div").trim();
			System.out.println(ActualMessageTitle);
										 	
			String ExpectedMessageTitle="Welcome to the " +Constants.State_Hunter.substring(1, Constants.State_Hunter.length()) + " Hunter's Ed Course"; 
			System.out.println(ExpectedMessageTitle);
			
			if(ActualMessageTitle.equalsIgnoreCase(ExpectedMessageTitle))
			{
				System.out.println("Success!! Email is received with correct Title");
				Reporter.log("Success!! Email is received with correct Title"); 
			}
			else
			{
				System.out.println("Failure !!  Email is received with incorrect Title" + "\n" + "Expected Text: "  
									+ "\n" + ExpectedMessageTitle + "\n" + 
									 "Actual Text : " + "\n" +  ActualMessageTitle);
				Reporter.log("Failure !!Email is received with incorrect Title" + "\n" + "Expected Text: "  
						+ "\n" + ExpectedMessageTitle + "\n" + 
						 "Actual Text : " + "\n" +  ActualMessageTitle);
				
				AssertFailedCount++;
			}
			
			try{
			
			//Opening email body and verifying content
				
				SeleniumFunc.ClickOnElement("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div");
				
				Thread.sleep(4000);
				
				//div.mailview
				driver.switchTo().frame("publicshowmaildivcontent");
				
				String ActualMessageBody = SeleniumFunc.GetElementText("css", "html>body").trim();
				System.out.println(ActualMessageBody);
				
				String ExpectedMessageBody =
						"Hi "+ emailaddress +"," + "\n" + "\n" +
						"You have successfully registered for the Texas Hunter's Ed Course." + "\n" + "\n" +

						"We are sending this e-mail to provide you with a record of your account details. Please save this e-mail for your reference." + "\n" + "\n" + 
						"Your username for the course is: "+ username +"\n" + 
						"Your password for the course is: " ;
				
				
				if(ActualMessageBody.contains(ExpectedMessageBody))
				{
					System.out.println("Success!! Email Message body has correct details");
					Reporter.log("Success!! Email Message body has correct details"); 
				}
				else
				{
					System.out.println("Failure !!  Email Message body has incorrect details" + "\n" + "Expected Body: "  
										+ "\n" + ExpectedMessageBody + "\n" + 
										 "Actual Body : " + "\n" +  ActualMessageBody);
					Reporter.log("Failure !!  Email Message body has incorrect details" + "\n" + "Expected Body: "  
							+ "\n" + ExpectedMessageBody + "\n" + 
							 "Actual Body : " + "\n" +  ActualMessageBody);
					
					AssertFailedCount++;
				}
			
				driver.switchTo().defaultContent();
			}
			catch(Exception e)
			{
				//nothing :)
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
	
	
	
	/* Test 12: 
	 * Verify Residency requirement is enforced (if the State has a requirement for the safety certificate. ) for registration 
	 * For Hunter Ed, State which fall under this category
	*/ 
	@Test(dataProvider="Registration_ResidencyRequirement_hunter",dataProviderClass=utility.TestNG.class)
	private void Registration_ResidencyRequirement(String State_Hunter) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 12 : Verify Residency requirement is enforced (if the State has a requirement for the safety certificate. ) for registration "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 12 : Verify Residency requirement is enforced (if the State has a requirement for the safety certificate. ) for registration "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage  register = new RegistrationNewPage(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_Hunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_Hunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + State_Hunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + State_Hunter);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
		
			
		System.out.println("Step 3 : Filling registration form and clicking on 'Create Account' button without selecting checkbox for 'Residency in the State_Hunter Requirement'");
		Reporter.log("Step 3 : Filling registration form and clicking on 'Create Account' button without selecting checkbox for 'Residency in the State_Hunter Requirement'"); 
		
			String username= "sele" + JH.GenerateRandomNumber();
			String emailprefix = "Sel" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			register.FillRegistrationForm(username, "January", "2", "1990", emailaddress, "clarion@123", "clarion@123");
			register.ClickOnCreateAccount();
			Thread.sleep(5000);
			
		System.out.println("Step 4 : Verifying whether validation message is dispalyed for 'Residency in the State_Hunter Requirement' checkbox");
		Reporter.log("Step 4 : Verifying whether validation message is dispalyed for 'Residency in the State_Hunter Requirement' checkbox"); 
			
				String ActualMessageBody = SeleniumFunc.GetElementText("css", ".alert.alert-danger");
				//System.out.println(ActualMessageBody);
				String ExpectedMessageBody = "×" + "\n" +"Please review and correct the following field:" + "\n" +
											"Residency Statement";
				
				//String ExpectedMessageBody = "× Please review and correct the following field: Residency Statement";
				
				if(ActualMessageBody.endsWith("Residency Statement"))
				//if(ActualMessageBody.equalsIgnoreCase(ExpectedMessageBody))
				{
					System.out.println("Success!! Correct validation message is displayed");
					Reporter.log("Success!! Correct validation message is displayed"); 
				}
				else
				{
					System.out.println("Failure !! Incorrect validation message is displayed" + "\n" + "Expected Message: "  
										+ "\n" + ExpectedMessageBody + "\n" + 
										 "Actual Message : " + "\n" +  ActualMessageBody);
					Reporter.log("Failure !! Incorrect validation message is displayed" + "\n" + "Expected Message: "  
							+ "\n" + ExpectedMessageBody + "\n" + 
							 "Actual Message : " + "\n" +  ActualMessageBody);
					
					AssertFailedCount++;
				}
		
		
		System.out.println("Step 5 : Selecting checkbox for 'Residency in the State_Hunter Requirement' and clicking on 'Create Account'");
		Reporter.log("Step 5 : Selecting checkbox for 'Residency in the State_Hunter Requirement' and clicking on 'Create Account'"); 
			
				register.ClickOnStateResidencyCheckbox();
				Thread.sleep(4000);
				
				register.ClickOnCreateAccount();
		
				register.ClickOnCreateAccount();

	
		System.out.println("Step 6 : Verifying whether user is registered successfully or not");
		Reporter.log("Step 6 : Verifying whether user is registered successfully or not"); 
			
			
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
		//System.out.println(ActualUserName);
		
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
	
	
	
	/* Test 13: 
	 * Verify a modal dialog is displayed to user for In - person Exam Reminder 
	*/ 
	@Test(dataProvider="Registration_ModelReminder_hunter",dataProviderClass=utility.TestNG.class)
	private void Registration_ModelReminder(String State_Hunter) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 13 :Verify a modal dialog is displayed to user for In -person Exam Reminder "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 13 : Verify a modal dialog is displayed to user for In -person Exam Reminder"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationNewPage register = new RegistrationNewPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_Hunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_Hunter); 
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + State_Hunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + State_Hunter);
			PageHeader header = new PageHeader(driver); 	
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
			
		System.out.println("Step 4 : Verifying whether modal dialog displayed or not");
		Reporter.log("Step 4 : Verifying whether modal dialog displayed or not"); 
			
			
		if(SeleniumFunc.IsElementPresent("css", "#wait-right-there a"))
			{
				
				System.out.println("Success !! Modal dialog is present '");
				Reporter.log("Success !! Modal dialog is present '"); 
				
				System.out.println("Step 5 : Click on 'I understand..' button and verifying whether model is closed or not");
				Reporter.log("Step 5 : Click on 'I understand..' button and verifying whether model is closed or not");
				
					register.ClickOnIUnderstandButton();
					
					Thread.sleep(4000);
					
					String popup = SeleniumFunc.GetAttributeValue("css", "#wait-right-there","style");
					if(popup.equalsIgnoreCase("display: none;"))
					{
						System.out.println("Success !! Modal dialog is closed '");
						Reporter.log("Success !! Modal dialog is closed '"); 
					}
					else
					{
						System.out.println("Failure !! Modal dialog is NOT closed ");
						Reporter.log("Failure !! Modal dialog is closed"); 
						AssertFailedCount++;
					}
			}
			else
			{
				System.out.println("Failure !! Modal dialog is NOT present ");
				Reporter.log("Failure !! Modal dialog is present"); 
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


	/* Test 14: 
	 * Verify that if a course has a minimum age requirement, the requirement is correctly enforced at registration
	*/ 
	@Test(dataProvider="Registration_MimAgeValidation_hunter",dataProviderClass=utility.TestNG.class)
	private void Registration_MimAgeValidation
							(	String State_Hunter,
								String minage,
								String month,String day ,String year
							) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 14 : Verify that if a course has a minimum age requirement, the requirement is correctly enforced at registration"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 14 : Verify that if a course has a minimum age requirement, the requirement is correctly enforced at registration"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationNewPage register = new RegistrationNewPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_Hunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_Hunter); 

		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + State_Hunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + State_Hunter);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + State_Hunter);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
		System.out.println("Step 3 : Filling registration form (with Age < minimum age required) and clicking on 'Create Account' button");
		Reporter.log("Step 3 : Filling registration form (with Age < minimum age required) and clicking on 'Create Account' button"); 
		
			String username="min"+ JH.GenerateRandomNumber();
			register.FillRegistrationForm(username, month, day, year, "testing@gmail.com", "clarion@123", "clarion@123");
			
			try
			{
				register.ClickOnStateResidencyCheckbox();
				Thread.sleep(3000);
			}
			catch(Exception e)
			{
				//exit
			}
			
			register.ClickOnCreateAccount();
			
		System.out.println("Step 4 : Verifying whether correct validation message is displayed or not");
		Reporter.log("Step 4 : Verifying whether correct validation message is displayed or not"); 
			
			Thread.sleep(10000);
			SeleniumFunc.ClickOnElement("css", "#wait-right-there > div > div > div.modal-footer > a");
			Thread.sleep(2000);
			String ActualValidationMessage=SeleniumFunc.GetElementText("css", "#registration-form > fieldset > div.form-group.form-group-lg.has-error > div.help-block").trim();
			//System.out.println(ActualValidationMessage);
			
			String ExpectedValidationNewMessage = "The minimum eligible age is "+minage+".";
			//String ExpectedValidationMessage = "Please complete your date of birth. You must be at least " + minage+ " years old." ;
			//System.out.println(ExpectedValidationMessage);
		//	Please complete your date of birth. You must be at least 18 years old.
			if(ActualValidationMessage.contains(ExpectedValidationNewMessage))
			{
				
				System.out.println("Success !! message is correct'");
				Reporter.log("Success !! message is correct"); 
			}
			else
			{
				System.out.println("Failure !!message is incorrect" + "Expected is : "+ExpectedValidationNewMessage +"Actual is : "+ActualValidationMessage);
				Reporter.log("Failure !!message is incorrect" + "Expected is : "+ExpectedValidationNewMessage +"Actual is : "+ActualValidationMessage); 
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
	
	
	// Test : For Hunt > GA, Verify SSN is not present & GA collecting DNR Number & DOB
	
	@Test
	public void Registration_HuntGA() throws InterruptedException
	{
		
		System.out.println("====" + "\n" +
				"Test 2 : For Hunt > GA, Verify SSN is not present & GA collecting DNR Number & DOB"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			  "Test 2 : For Hunt > GA, Verify SSN is not present & GA collecting DNR Number & DOB"  + "\n" +
			 	  "====");	
	
			int AssertFailedCount=0 ;
			RegistrationNewPage register = new RegistrationNewPage(driver);
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			PageHeader header = new PageHeader(driver); 
			HunterRegistrationPage hunterReg= new HunterRegistrationPage(driver);
			
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_Hunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_Hunter); 
					
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter +  "/" +"georgia" );
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter +  "/" +"georgia" ); 
	
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter +  "/" + "georgia");
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter  +  "/" + "georgia");
				Thread.sleep(4000);
				
			}
				
			header.Register_New.click();
			Thread.sleep(8000);
				
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();		
				Thread.sleep(4000);
			}
		
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
				register.ClickOnStateResidencyCheckbox();
				Thread.sleep(4000);
			}
		
		System.out.println("Step 3 : Filling registration form and clicking on 'Create Account' button");
		Reporter.log("Step 3 : Filling registration form and clicking on 'Create Account' button"); 
			
				String username= "Hunter" + JH.GenerateRandomNumber();
				String emailaddress= username + "@mailinator.com";
				
				register.FillRegistrationForm(username, "August", "2", "1990", emailaddress, "clarion@123", "clarion@123");
				// click on residency requirement
				hunterReg.ClickOnStateResidencyCheckbox();
				register.ClickOnCreateAccount();
		
				Thread.sleep(5000);
				
		System.out.println("Step 4 : Verifying whether user is registered successfully or not");
		Reporter.log("Step 4 : Verifying whether user is registered successfully or not"); 
				
				
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
		
		System.out.println("Step 5 : Verifying SSN is not present");
		Reporter.log("Step 5 : Verifying is not present"); 
		
			if(SeleniumFunc.IsElementPresent(hunterReg.EnterSSN))
			{
				System.out.println("Failure!!! SSN Number is present ");
				Reporter.log("Failure!!! SSN Number is present ");
				AssertFailedCount++;
			}
			else
			{
				System.out.println("Success!!! SSN Number is Not present ");
				Reporter.log("Success!!! SSN Number is Not present ");
			}
		
		System.out.println("Step 6 : Verifying GA collecting DNR Number");
		Reporter.log("Step 6 : Verifying GA collecting DNR Number"); 
			if(SeleniumFunc.IsElementPresent(hunterReg.DNRNumber))
			{
				System.out.println("Success!!! SSN Number is present ");
				Reporter.log("Success!!! SSN Number is present ");		
			}
			else
			{
				System.out.println("Failure!!! SSN Number is Not present ");
				Reporter.log("Failure!!! SSN Number is Not present ");
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
