package courses.BowhunterEd;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.ContentsPage;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.RegistrationPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class RegistrationTest 
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
	@Test(dataProvider="Registration_bowhunter",dataProviderClass=utility.TestNG.class)
	private void Registration(	String username,
								String password,
								String repeatpassword,
								String firstname,
								String middleinitial,
								String lastname,
								String suffix,
								String month,String day ,String year,
								String emailaddress,
								String repeatemailaddress) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify that student can register for course successfully"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify that student can register for course successfully"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 
			
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
		
			username=username + JH.GenerateRandomNumber();
			register.FillRegistrationForm(username, password, repeatpassword, firstname, middleinitial, lastname, suffix, month, day, year, emailaddress, repeatemailaddress);
			register.ClickOnCreateAccount();
	
			Thread.sleep(4000);

		System.out.println("Step 4 : Verifying whether user is registered successfully or not");
		Reporter.log("Step 4 : Verifying whether user is registered successfully or not"); 
			
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}		
			
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
	 * Verify that 'Choose another' link is working as expected
	*/ 
	@Test
	private void Registration_ChooseAnotherLink() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify that 'Choose another' link is working as expected"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify that 'Choose another' link is working as expected"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 
			
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
		System.out.println("Step 3 : Clicking on 'Choose another' link ");
		Reporter.log("Step 3 : Clicking on 'Choose another' link"); 
		
			Thread.sleep(4000);

			register.ClickOnChooseAnother();
			Thread.sleep(8000);

		System.out.println("Step 4 : Verifying whether user is navigated to home page or not");
		Reporter.log("Step 4 : Verifying whether user is navigated to home page or not"); 
			
			
			if(SeleniumFunc.ToGetCurrentPageUrl().contains(Constants.ApplicationURL_BowHunter.subSequence(5,Constants.ApplicationURL_BowHunter.length())))
			{
				System.out.println("Success !! User is navigated to home page");
				Reporter.log("Success !! User is navigated to home page"); 
			}
			else
			{
				System.out.println("Failure !!  User is NOT navigated to home page , instead navigated to: " + SeleniumFunc.ToGetCurrentPageUrl());
				Reporter.log("Failure !!  User is NOT navigated to home page , instead navigated to: " + SeleniumFunc.ToGetCurrentPageUrl()); 
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
	 * Verify that validation messages are displayed for mandatory fields
	*/ 
	@Test
	private void Registration_ValidationMessageForMandatoryFields() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify that validation messages are displayed for mandatory fields"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify that validation messages are displayed for mandatory fields"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 
			
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
			Thread.sleep(4000);

		System.out.println("Step 4 : Verifying whether validation messages are displayed for mandatory fields");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for mandatory fields"); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "div [class='alert alert-error alert-block']");
			String ExpectedValidationMessage= 
					"Uh oh. Something's wrong. Please review and correct the following fields:" + "\n" + 
					"Choose a Username" + "\n" + 
					"Create a Password" + "\n" + 
					"Repeat Password" + "\n" + 
					"First Name" + "\n" + 
					"Last Name" + "\n" + 
					"Year"+ "\n" + 
					"E-mail Address"+ "\n" + 
					"Repeat E-mail Address" ; 
			
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
	 * Verify that validation message is displayed if input criteria doesn't match for 'Choose a Username' field 
	 * A) Enter special characters
	*/ 
@Test
	private void Registration_ValidationMessageForChooseUsername1() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify that validation message is displayed if input criteria doesn't match for 'Choose a Username' field - A) Enter special characters "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify that validation message is displayed if input criteria doesn't match for 'Choose a Username' field A) Enter special characters"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
			register.ClickOnCreateAccount();
			Thread.sleep(4000);

	
		System.out.println("Step 4 : Verifying whether validation messages are displayed for 'Choose a Username' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for 'Choose a Username' field"); 
			
			
			String ActualValidationMessage1 = SeleniumFunc.GetElementText("css", "div[class='control-group error'] div p:nth-of-type(1)");
			String ActualValidationMessage2 = SeleniumFunc.GetElementText("css", "div[class='control-group error'] div p:nth-of-type(2)");
			String ExpectedValidationMessage1= "Your username must meet these requirements:";
			String ExpectedValidationMessage2= "Must be 4";
			String ExpectedValidationMessage3= "32 characters long and can only include letters and numbers."; 
			if(ActualValidationMessage1.equals(ExpectedValidationMessage1) && ActualValidationMessage2.startsWith(ExpectedValidationMessage2) && 
					ActualValidationMessage2.endsWith(ExpectedValidationMessage3))
			{
				System.out.println("Success !! correct validation messages are displayed for mandatory fields");
				Reporter.log("Success !! correct validation messages are displayed for mandatory fields"); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed for mandatory fields");
				Reporter.log("Failure !!  Incorrect validation messages are displayed for mandatory fields"); 
				
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
	 *Verify that validation message is displayed if input criteria doesn't match for 'Choose a Username' field - 
	 *B) if username is < 4 charater or >32 character"
	*/ 
	@Test
	private void Registration_ValidationMessageForChooseUsername2() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify that validation message is displayed if input criteria doesn't match for 'Choose a Username' field - B) if username is < 4 charater or >32 character"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify that validation message is displayed if input criteria doesn't match for 'Choose a Username' field B) if username is < 4 charater or >32 character"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
			
			register.ChooseAUsername("pbc");
		
		System.out.println("Step 4 : Entering data in other field and click on 'Create Account' button");
		Reporter.log("Step 4 : Entering data in other field and click on 'Create Account' button");
			
			register.EnterPassword("qwerty123");
			register.EnterRepeatPassword("qwerty123");
			register.EnterFirstName("Piyush");
			register.EnterLastName("Ke-testing");
			register.EnterDOB("January", "2", "1990");
			register.EnterEmailAddress("piyush@testing.com");
			register.EnterRepeatEmailAddress("piyush@testing.com");
			register.ClickOnCreateAccount();
			Thread.sleep(Constants.ThreadWaitInmiliseconds);

		System.out.println("Step 5 : Verifying whether validation messages are displayed for 'Choose a Username' field");
		Reporter.log("Step 5 : Verifying whether validation messages are displayed for 'Choose a Username' field"); 
			
			
		String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#username p:nth-of-type(2)") ;	
		//String ExpectedValidationMessage= "Must be 4–32 characters long and can only include letters and numbers."; 
		String ExpectedValidationMessage1= "Must be 4";
		String ExpectedValidationMessage2="32 characters long and can only include letters and numbers."; 	
			if(ActualValidationMessage.startsWith(ExpectedValidationMessage1)&&ExpectedValidationMessage2.endsWith(ExpectedValidationMessage2) )
			{
				System.out.println("Success !! correct validation messages are displayed for mandatory fields");
				Reporter.log("Success !! correct validation messages are displayed for mandatory fields"); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed for mandatory fields");
				Reporter.log("Failure !!  Incorrect validation messages are displayed for mandatory fields"); 
				
				AssertFailedCount++;
			}
			
			
			/*System.out.println("Step 6 : Entering >32 charater in 'Choose a Username' field");
			Reporter.log("Step 6 : Entering >32 charater in 'Choose a Username' field"); 
			
				register.ChooseAUsername("abcdefghijabcdefghijabcdefghij123");
			
			System.out.println("Step 7 : Entering data in other field and click on 'Create Account' button");
			Reporter.log("Step 7 : Entering data in other field and click on 'Create Account' button");
				
				register.EnterPassword("qwerty123");
				register.EnterRepeatPassword("qwerty123");
				register.EnterFirstName("Piyush");
				register.EnterLastName("Ke-testing");
				register.EnterDOB("January", "2", "1990");
				register.EnterEmailAddress("piyush@testing.com");
				register.EnterRepeatEmailAddress("piyush@testing.com");
				register.ClickOnCreateAccount();
				Thread.sleep(5000);

			System.out.println("Step 8 : Verifying whether validation messages are displayed for 'Choose a Username' field");
			Reporter.log("Step 8 : Verifying whether validation messages are displayed for 'Choose a Username' field"); 
				
				
				ActualValidationMessage= SeleniumFunc.GetElementText("css", "section.row div.alert-banner");
				ExpectedValidationMessage= 
						"Username must be 4-32 characters long and can only include letters and numbers."; 
				
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
				*/
			
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
	 * Verify that validation message is displayed if input criteria doesn't match for 'Password' field   
	A)  Enter special characters
	*/ 
	@Test
	private void Registration_ValidationMessageForPassword1() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify that validation message is displayed if input criteria doesn't match for 'Password' field - A)  Enter special characters "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Verify that validation message is displayed if input criteria doesn't match for 'Password' field - A)  Enter special characters"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
		System.out.println("Step 3 : Entering special characters in 'Password' field e.g $%%");
		Reporter.log("Step 3 : Entering special characters in 'Password' field e.g $%%"); 
		
		
			register.EnterPassword("%%GG");
			register.ClickOnCreateAccount();
			register.ClickOnCreateAccount();
			Thread.sleep(4000);

		System.out.println("Step 4 : Verifying whether validation messages are displayed for 'Password' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for 'Password' field"); 
			
			
			String ActualValidationMessage1 = SeleniumFunc.GetElementText("css", "section#username div:nth-of-type(2) p:nth-of-type(1)");
			String ActualValidationMessage2 = SeleniumFunc.GetElementText("css", "section#username div:nth-of-type(2) p:nth-of-type(2)");
			String ExpectedValidationMessage1 = "Your password must meet these requirements:";
			String ExpectedValidationMessage2 =	"Must be 8";
			String ExpectedValidationMessage3 =	"32 characters long and must include letters and numbers."; 
			
			if(ActualValidationMessage1.startsWith(ExpectedValidationMessage1) && ActualValidationMessage2.startsWith(ExpectedValidationMessage2)
					&& ActualValidationMessage2.endsWith(ExpectedValidationMessage3))
			{
				System.out.println("Success !! correct validation messages are displayed for mandatory fields");
				Reporter.log("Success !! correct validation messages are displayed for mandatory fields"); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed for mandatory fields");
				Reporter.log("Failure !!  Incorrect validation messages are displayed for mandatory fields"); 
				
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
	 * Verify that validation message is displayed if input criteria doesn't match for 'Password' field   
	B)  if password is < 8 charater or >32 character
	*/ 
	@Test
	private void Registration_ValidationMessageForPassword2() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Verify that validation message is displayed if input criteria doesn't match for 'Password' field - B)  if password is < 8 charater or >32 character"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Verify that validation message is displayed if input criteria doesn't match for 'Password' field -B)  if password is < 8 charater or >32 character"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
		
			
		System.out.println("Step 3 : Entering <8 characters in 'Password' field e.g 1234");
		Reporter.log("Step 3 : Entering special characters in 'Password' field e.g 1234"); 
		
			register.EnterPassword("1234567");
			register.ClickOnCreateAccount();
			register.ClickOnCreateAccount();
			Thread.sleep(4000);

		System.out.println("Step 4 : Verifying whether validation messages are displayed for 'Password' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for 'Password' field"); 
		
			String ActualValidationMessage1 = SeleniumFunc.GetElementText("css", "section#username div:nth-of-type(2) p:nth-of-type(1)");
			String ActualValidationMessage2 = SeleniumFunc.GetElementText("css", "section#username div:nth-of-type(2) p:nth-of-type(2)");
			String ExpectedValidationMessage1 = "Your password must meet these requirements:";
			String ExpectedValidationMessage2 =	"Must be 8";
			String ExpectedValidationMessage3 =	"32 characters long and must include letters and numbers."; 
		
			if(ActualValidationMessage1.startsWith(ExpectedValidationMessage1) && ActualValidationMessage2.startsWith(ExpectedValidationMessage2)
				&& ActualValidationMessage2.endsWith(ExpectedValidationMessage3))
			{
				System.out.println("Success !! correct validation messages are displayed for mandatory fields");
				Reporter.log("Success !! correct validation messages are displayed for mandatory fields"); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed for mandatory fields");
				Reporter.log("Failure !!  Incorrect validation messages are displayed for mandatory fields"); 
				
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
	 * Verify that validation message is displayed if input criteria doesn't match for 'Password' field   
	C)  if password doesn't include letter and number both
	*/ 
	@Test
	private void Registration_ValidationMessageForPassword3() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Verify that validation message is displayed if input criteria doesn't match for 'Password' field - C)  if password doesn't include letter and number both"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Verify that validation message is displayed if input criteria doesn't match for 'Password' field -C)  if password doesn't include letter and number both"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
		System.out.println("Step 3 : Entering >8 characters (only letters) in 'Password' field e.g qwertyuio");
		Reporter.log("Step 3 : Entering >8 characters (only letters) in 'Password' field e.g qwertyuio"); 
		
			
	
			register.EnterPassword("qwertyuio");
			register.ClickOnCreateAccount();
			register.ClickOnCreateAccount();
			
			Thread.sleep(4000);

		System.out.println("Step 4 : Verifying whether validation messages are displayed for 'Password' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for 'Password' field"); 
			
			
			String ActualValidationMessage1 = SeleniumFunc.GetElementText("css", "fieldset[class='account-info'] div:nth-of-type(2) div p:nth-of-type(1)");
			String ActualValidationMessage2 = SeleniumFunc.GetElementText("css", "fieldset[class='account-info'] div:nth-of-type(2) div p:nth-of-type(2)");
			
			String ExpectedValidationMessage1 = "Your password must meet these requirements:";
			String ExpectedValidationMessage2 =	"Must be 8";
			String ExpectedValidationMessage3 =	"32 characters long and must include letters and numbers."; 
			
			if(ActualValidationMessage1.startsWith(ExpectedValidationMessage1) && ActualValidationMessage2.startsWith(ExpectedValidationMessage2)
					&& ActualValidationMessage2.endsWith(ExpectedValidationMessage3))
			{
				System.out.println("Success !! correct validation messages are displayed for mandatory fields");
				Reporter.log("Success !! correct validation messages are displayed for mandatory fields"); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed for mandatory fields");
				Reporter.log("Failure !!  Incorrect validation messages are displayed for mandatory fields"); 
				
				AssertFailedCount++;
			}
			
			
			
		System.out.println("Step 5 : Entering >8 characters (only numbers) in 'Password' field e.g 123456789");
		Reporter.log("Step 5 : Entering >8 characters (only numbers) in 'Password' field e.g 123456789"); 
		
			register.EnterPassword("123456789");
			register.ChooseAUsername("");
	
		System.out.println("Step 4 : Verifying whether validation messages are displayed for 'Password' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for 'Password' field"); 
			
			
		String ActualValidationMessage11= SeleniumFunc.GetElementText("css", "fieldset[class='account-info'] div:nth-of-type(2) div p:nth-of-type(1)");
		String ActualValidationMessage22= SeleniumFunc.GetElementText("css", "fieldset[class='account-info'] div:nth-of-type(2) div p:nth-of-type(2)");
			
		String ExpectedValidation1 = "Your password must meet these requirements:";
		String ExpectedValidation2 =	"Must be 8";
		String ExpectedValidation3 =	"32 characters long and must include letters and numbers."; 
		
		if(ActualValidationMessage11.startsWith(ExpectedValidation1) && ActualValidationMessage22.startsWith(ExpectedValidation2)
				&& ActualValidationMessage2.endsWith(ExpectedValidation3))
			{
				System.out.println("Success !! correct validation messages are displayed for mandatory fields");
				Reporter.log("Success !! correct validation messages are displayed for mandatory fields"); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed for mandatory fields");
				Reporter.log("Failure !!  Incorrect validation messages are displayed for mandatory fields"); 
				
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
	 * Verify that validation message is displayed if Password/Repeat Password fields value doesn't match
	*/ 
	@Test
	private void Registration_ValidationMessageForPasswordRepeatPassword() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Verify that validation message is displayed if Password/Repeat Password fields value doesn't match"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 : Verify that validation message is displayed if Password/Repeat Password fields value doesn't match"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
		
			
		System.out.println("Step 3 : Entering >8 characters in 'Password' field e.g qwertyuio123");
		Reporter.log("Step 3 : Entering >8 characters in 'Password' field e.g qwertyuio123"); 
		
	
			register.EnterPassword("qwertyuio123");
			
		System.out.println("Step 4 : Entering different value in 'Repeat Password' field e.g differentvalue");
		Reporter.log("Step 4 : Entering different value in 'Repeat Password' field e.g differentvalue"); 
		
			register.EnterRepeatPassword("differentvalue");
			register.ClickOnCreateAccount();
			
			Thread.sleep(4000);

		System.out.println("Step 5 : Verify that validation message is displayed if Password/Repeat Password fields value doesn't match");
		Reporter.log("Step 5 : Verify that validation message is displayed if Password/Repeat Password fields value doesn't match"); 
			
			
			String ActualValidation1= SeleniumFunc.GetElementText("css", "fieldset[class='account-info'] div:nth-of-type(3) p:nth-of-type(1)");
			String ActualValidation2= SeleniumFunc.GetElementText("css", "fieldset[class='account-info'] div:nth-of-type(3) p:nth-of-type(2)");
			
			String ExpectedValidationMessage1 = "This doesn't match what you entered in the first field. Please try again.";
			String ExpectedValidationMessage2 =	"Re-enter the password from above, just to make sure we";
			String ExpectedValidationMessage3 =	"ve got it right."; 
			
			if(ActualValidation1.startsWith(ExpectedValidationMessage1) && ActualValidation2.startsWith(ExpectedValidationMessage2)
					&& ActualValidation2.endsWith(ExpectedValidationMessage3))
			{
				System.out.println("Success !! correct validation messages are displayed for mandatory fields Actual text is : " +ActualValidation1+ActualValidation2);
				Reporter.log("Success !! correct validation messages are displayed for mandatory fields Actual text is" +ActualValidation1+ActualValidation2); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed for mandatory fields Actual text is : " +ActualValidation1+ActualValidation2);
				Reporter.log("Failure !!  Incorrect validation messages are displayed for mandatory fields Actual text is : " +ActualValidation1+ActualValidation2); 
				
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
	 * Verify that validation message is displayed if input criteria doesn't match for 'Date of Birth' field 
       A) if year field has <4 chars"
	*/ 
	@Test
	private void Registration_ValidationMessageForDOB1() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : Verify that validation message is displayed if input criteria doesn't match for 'Date of Birth' field A) if year field has >4 chars"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10 : Verify that validation message is displayed if input criteria doesn't match for 'Date of Birth' field A) if year field has >4 chars"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
	
			register.EnterDOB("January", "1", "199");
			register.ChooseAUsername("");
	
		System.out.println("Step 4 : Verifying whether validation messages are displayed for DOB 'Year' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for DOB 'Year' field"); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "section#about-you div[class='control-group error'] p:nth-of-type(1)");
											
			String ExpectedValidationMessage="Entered value is too short."; 
			
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


	
	/* Test 11: 
	 * Verify that validation message is displayed if input criteria doesn't match for 'Date of Birth' field 
      B) if year field has invalid value e.g .asdd,$%$% etc.
	*/ 
	@Test
	private void Registration_ValidationMessageForDOB2() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 11 : Verify that validation message is displayed if input criteria doesn't match for 'Date of Birth' field B) if year field has invalid value e.g .asdd,$%$% etc. "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 11 : Verify that validation message is displayed if input criteria doesn't match for 'Date of Birth' field B) if year field has invalid value e.g .asdd,$%$% etc. "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
			register.EnterPassword("qwerty123");
			register.EnterRepeatPassword("qwerty123");
			register.EnterFirstName("Piyush");
			register.EnterLastName("Ke-testing");
			register.EnterDOB("January", "2", "abcd");
			register.EnterEmailAddress("piyush@testing.com");
			register.EnterRepeatEmailAddress("piyush@testing.com");
			register.ClickOnCreateAccount();
			Thread.sleep(Constants.ThreadWaitInmiliseconds);

		System.out.println("Step 5 : Verifying whether validation messages are displayed for 'DOB Year' field");
		Reporter.log("Step 5 : Verifying whether validation messages are displayed for 'DOB Year' field"); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "section.row div.alert-banner");
			String ExpectedValidationMessage= 
					"Please enter the year in XXXX format, numbers only."; 
			
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

	
	
	/* Test 12: 
	 * Verify that validation message is displayed if input criteria doesn't match for 'Email Address' field 
	*/ 
	@Test
	private void Registration_ValidationMessageForEmailAddress() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 12 : Verify that validation message is displayed if input criteria doesn't match for 'Email Address' field "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 12 : Verify that validation message is displayed if input criteria doesn't match for 'Email Address' field "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
			Thread.sleep(4000);

		System.out.println("Step 4 : Verifying whether validation messages are displayed for 'Email Address' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for 'Email Address' field"); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "section#contact-info div[class='control-group error'] p:nth-of-type(1)");
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
	
	
	
	/* Test 13: 
	 * Verify that validation message is displayed if Email/Repeat Email address fields value doesn't match
	*/ 
	@Test
	private void Registration_ValidationMessageForEmailRepeatEmailAddress() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 13 : Verify that validation message is displayed if Email/Repeat Email address fields value doesn't match "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 13 : Verify that validation message is displayed if Email/Repeat Email address fields value doesn't match"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
		
			
		System.out.println("Step 3 : Entering different values in Email Address/Repeat Email Address fields ");
		Reporter.log("Step 3 : Entering different values in Email Address/Repeat Email Address fields "); 
		
			register.EnterEmailAddress("piyush@gmail.com");
			register.EnterRepeatEmailAddress("piyush@google.com");
			register.ClickOnCreateAccount();
			register.ClickOnCreateAccount();
			Thread.sleep(4000);
			
			
		System.out.println("Step 4 : Verifying whether validation messages are displayed for 'Email Address' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for 'Email Address' field"); 
			
			
			String ActualValidationMessage1= SeleniumFunc.GetElementText("css", "section#contact-info div[class='control-group error'] p:nth-of-type(1)") ;
			String ActualValidationMessage2= SeleniumFunc.GetElementText("css", "section#contact-info div[class='control-group error'] p:nth-of-type(2)");
			
			String ExpectedValidationMessage1 = "This doesn't match what you entered in the first field. Please try again.";
			String ExpectedValidationMessage2 =	"Re-enter the e-mail address from above, just to make sure we";	
			String ExpectedValidationMessage3 =	"ve got it right.";
			
			if(ActualValidationMessage1.startsWith(ExpectedValidationMessage1) && ActualValidationMessage2.startsWith(ExpectedValidationMessage2)
					&& ActualValidationMessage2.endsWith(ExpectedValidationMessage3))
			{
				System.out.println("Success !! correct validation messages are displayed for fields");
				Reporter.log("Success !! correct validation messages are displayed for fields"); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect validation messages are displayed for fields");
				Reporter.log("Failure !!  Incorrect validation messages are displayed for fields"); 
				
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
	 * Verify that Username must be unique for registration
	*/ 
	@Test
	private void Registration_UsernameUniqueCheck() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 14 : Verify that Username must be unique for registration "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 14 : Verify that Username must be unique for registration"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
			register.FillRegistrationForm(username, "piyush@143", "piyush@143", "test", 
										"B", "Ke-testing", "Jr.", "January","3","1990","selenium@test.com","selenium@test.com");
			
			register.ClickOnCreateAccount();
			Thread.sleep(6000);
			
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}	
			
			//Logging out of application
			//SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + "/site/logout");
			Thread.sleep(4000);

			
		System.out.println("Step 4 : Trying to register with same Username");
		Reporter.log("Step 4 : Trying to register with same Username"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + "/register/create");
			Thread.sleep(4000);

			System.out.println(username);
			register.FillRegistrationForm(username, "piyush@143", "piyush@143", "test", 
										"B", "Ke-testing", "Jr.", "January","3","1990","selenium@test.com","selenium@test.com");
			
			register.ClickOnCreateAccount();
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
			
		System.out.println("Step 5 : Verifying whether validation messages are displayed for 'Username' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for 'Username' field"); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "section.row div.alert-banner");
			String ExpectedValidationMessage= 
					"This username has already been taken. Please choose a different one."; 
		
			
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


	
	/* Test 15: 
	 * Verify title of page i.e. Register for the Bowhunter Ed {State} Course
	*/ 
	@Test
	private void Registration_VerifyTitle() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 15 : Verify title of page i.e. Register for the Bowhunter Ed {State} Course "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 15 : Verify title of page i.e. Register for the Bowhunter Ed {State} Course"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
		
			
		System.out.println("Step 3 : Verifying Page Title i.e. Register for the Bowhunter Ed {State} Course ");
		Reporter.log("Step 3 : Verifying Page Title i.e. Register for the Bowhunter Ed {State} Course"); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "div.page-header h1");
			//System.out.println(ActualValidationMessage);
											
			String ExpectedValidationMessage="Register for the "  + Constants.State_BowHunter.substring(1, Constants.State_BowHunter.length()) + " Bowhunter Ed Course" + "\n" +
											"Wrong course? Choose another."; 
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


	
	/* Test 16: 
	 * Verify that accepted input data massage is displayed (for all fields)
	*/ 
	@Test
	private void Registration_InputDataMessageCheck() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 16 : Verify that accepted input data massage is displayed (for all fields)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 16 : Verify that accepted input data massage is displayed (for all fields)"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver);Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "section#username div:nth-of-type(1) p");
			//System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage1 = "Must be 4";
			String ExpectedValidationMessage2 =	"32 characters long and can only include letters and numbers."; 
			//String ExpectedValidationMessage="Must be 4–32 characters long and can only include letters and numbers."; 
			//System.out.println(ExpectedValidationMessage);
			
			if(ActualValidationMessage.startsWith(ExpectedValidationMessage1) && ActualValidationMessage.endsWith(ExpectedValidationMessage2))
			{
				System.out.println("Success!! input data message text is correctly displayed for 'Choose a Username' field");
				Reporter.log("Success!! input data message text is correctly displayed for 'Choose a Username' field"); 
			}
			else
			{
				System.out.println("Failure !!  input data message text is correctly displayed for 'Choose a Username' field");
				Reporter.log("Failure !!  input data message text is correctly displayed for 'Choose a Username' field");
				
				AssertFailedCount++;
			}
			
			
			
		System.out.println("Step 4 : Verifying input data message text for 'Create a Password' field");
		Reporter.log("Step 4 : Verifying input data message text for 'Create a Password' field"); 
			
			
			ActualValidationMessage= SeleniumFunc.GetElementText("css", "section#username div:nth-of-type(2) p");
			//System.out.println(ActualValidationMessage);
											
			String ExpectedValidationMessageS = "Must be 8";
			String ExpectedValidationMessageE= "32 characters long and must include letters and numbers."; 
			//System.out.println(ExpectedValidationMessage);
			
			if(ActualValidationMessage.startsWith(ExpectedValidationMessageS) && ActualValidationMessage.endsWith(ExpectedValidationMessageE) )
			{
				System.out.println("Success!! input data message text is correctly displayed for 'Create a Password' field");
				Reporter.log("Success!! input data message text is correctly displayed for 'Create a Password' field"); 
			}
			else
			{
				System.out.println("Failure !!  input data message text is correctly displayed for 'Create a Password' field");
				Reporter.log("Failure !!  input data message text is correctly displayed for 'Create a Password' field");
				
				AssertFailedCount++;
			}
			
	
		System.out.println("Step 5 : Verifying input data message text for 'Repeat Password' field");
		Reporter.log("Step 5 : Verifying input data message text for 'Repeat Password' field"); 
			
			
			ActualValidationMessage= SeleniumFunc.GetElementText("css", "section#username div:nth-of-type(3) p");
			//System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessageS1 = "Re-enter the password from above, just to make sure we";
			String ExpectedValidationMessageS2 = "ve got it right."; 
			//ExpectedValidationMessage="Re-enter the password from above, just to make sure we’ve got it right."; 
			//System.out.println(ExpectedValidationMessage);
			
			if(ActualValidationMessage.startsWith(ExpectedValidationMessageS1) && ActualValidationMessage.endsWith(ExpectedValidationMessageS2))
			{
				System.out.println("Success!! input data message text is correctly displayed for 'Repeat Password' field");
				Reporter.log("Success!! input data message text is correctly displayed for 'Repeat Password' field"); 
			}
			else
			{
				System.out.println("Failure !!  input data message text is correctly displayed for 'Repeat Password' field");
				Reporter.log("Failure !!  input data message text is correctly displayed for 'Repeat Password' field");
				
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 6 : Verifying input data message text for 'Repeat E-mail Address' field");
		Reporter.log("Step 5 : Verifying input data message text for 'Repeat E-mail Address' field"); 
			
			
			ActualValidationMessage= SeleniumFunc.GetElementText("css", "section#username div:nth-of-type(3) p");
			//System.out.println(ActualValidationMessage);
			
			String ExpectedValidationM1="Re-enter the password from above, just to make sure we";
			String ExpectedValidationM2= "ve got it right."; 
			//ExpectedValidationMessage="Re-enter the password from above, just to make sure we’ve got it right."; 
			//System.out.println(ExpectedValidationMessage);
			
			if(ActualValidationMessage.startsWith(ExpectedValidationM1) && ActualValidationMessage.endsWith(ExpectedValidationM2))
			{
				System.out.println("Success!! input data message text is correctly displayed for 'Repeat E-mail Address' field");
				Reporter.log("Success!! input data message text is correctly displayed for 'Repeat E-mail Address' field"); 
			}
			else
			{
				System.out.println("Failure !!  input data message text is correctly displayed for 'Repeat E-mail Address' field");
				Reporter.log("Failure !!  input data message text is correctly displayed for 'Repeat E-mail Address' field");
				
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
	
	
	
	/* Test 17: 
	 * Verify that account creation email is received with details
	*/ 
	@Test
	private void Registration_VerifyAccountCreationEmail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 17 : Verify that account creation email is received with details"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 17 : Verify that account creation email is received with details"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + Constants.State_BowHunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
			PageHeader header = new PageHeader(driver);Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + Constants.State_BowHunter);
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
			register.FillRegistrationForm(username, "piyush@143", "piyush@143", "test", "B", "Ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			register.ClickOnCreateAccount();
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
			
		System.out.println("Step 4 : Navigating to Email Box and verifying whether email is received with correct details or not");
		Reporter.log("Step 4 : Navigating to Email Box and verifying whether email is received with correct details or not"); 
			
			String EmailBoxUrl = "http://mailinator.com/inbox2.jsp?public_to=" + emailprefix + "#/#public_maildirdiv";
			SeleniumFunc.ToGoToUrl(EmailBoxUrl);
			Thread.sleep(Constants.ThreadWaitInmiliseconds);
			
			String ActualMessageTitle= SeleniumFunc.GetElementText("css", "div:nth-child(2) > div.col-lg-6.col-md-6.col-sm-6.outermail > div").trim();
			System.out.println(ActualMessageTitle);
											
			String ExpectedMessageTitle="Welcome to the Bowhunter Ed " +Constants.State_BowHunter.substring(1, Constants.State_BowHunter.length()) + " Course"; 
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
						"Hi test," + "\n" + "\n" +
						"Thank you for registering for the Bowhunter Ed " + Constants.State_BowHunter.substring(1, Constants.State_BowHunter.length())+ " Course." + "\n" + "\n" +

						"We are sending this e-mail to you as a record of your successful registration" + "\n" + 
						"and to provide a record of your account details. Please save this e-mail for" +"\n" + 
						"your reference." + "\n" + "\n" +

						"Your username for the course is: " + username + "\n" + "\n" +

						"You can access your course at any time by visiting us at:" + "\n" + "\n" +

						Constants.ApplicationURL_BowHunter + "/site/login" + "\n" + "\n" + "\n" +
						
						"Thanks and safe hunting!" + "\n" + "\n" +

						"Bowhunter Ed Support";
				
				
				if(ActualMessageBody.equalsIgnoreCase(ExpectedMessageBody))
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

	

	/* Test 18: 
	 * Verify Residency requirement is enforced (if the State has a requirement for the safety certificate. ) for registration 
	*/ 
	@Test(dataProvider="Registration_ResidencyRequirement_bowhunter",dataProviderClass=utility.TestNG.class)
	private void Registration_ResidencyRequirement(String State_BowHunter) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 18 : Verify Residency requirement is enforced (if the State has a requirement for the safety certificate. ) for registration "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 18 : Verify Residency requirement is enforced (if the State has a requirement for the safety certificate. ) for registration "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 

			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + "/minnesota");
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + "/minnesota"); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + "/minnesota");
			PageHeader header = new PageHeader(driver);Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + "/minnesota");
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
		
			
		System.out.println("Step 3 : Filling registration form and clicking on 'Create Account' button without selecting checkbox for 'Residency in the State_BowHunter Requirement'");
		Reporter.log("Step 3 : Filling registration form and clicking on 'Create Account' button without selecting checkbox for 'Residency in the State_BowHunter Requirement'"); 
		
			String username= "sele" + JH.GenerateRandomNumber();
			String emailprefix = "Sel" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			register.FillRegistrationForm(username, "piyush@143", "piyush@143", "test", "B", "Ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			register.ClickOnCreateAccount();
			Thread.sleep(10000);

		System.out.println("Step 4 : Verifying whether validation message is dispalyed for 'Residency in the State_BowHunter Requirement' checkbox");
		Reporter.log("Step 4 : Verifying whether validation message is dispalyed for 'Residency in the State_BowHunter Requirement' checkbox"); 
			
				String ActualMessageBody = SeleniumFunc.GetElementText("css", "form#registerForm div.form-alerts h4") + "\n" + 
						SeleniumFunc.GetElementText("css", "form#registerForm div.form-alerts label");
				//System.out.println(ActualMessageBody);
				String ExpectedMessageBody = "Uh oh. Something's wrong. Please review and correct the following field:" + "\n" +
											"Residency Requirement";
			
				if(ActualMessageBody.equalsIgnoreCase(ExpectedMessageBody))
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
		
		
		System.out.println("Step 5 : Selecting checkbox for 'Residency in the State_BowHunter Requirement' and clicking on 'Create Account'");
		Reporter.log("Step 5 : Selecting checkbox for 'Residency in the State_BowHunter Requirement' and clicking on 'Create Account'"); 
			
				register.ClickOnStateResidencyCheckbox();
				register.ClickOnCreateAccount();
		
				Thread.sleep(4000);

	
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
	
	
	
	/* Test 19: 
	 * Verify a modal dialog is displayed to user for In - person Exam Reminder 
	*/ 
	@Test(dataProvider="Registration_ModelReminder_bowhunter",dataProviderClass=utility.TestNG.class)
	private void Registration_ModelReminder(String State_BowHunter) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 19 :Verify a modal dialog is displayed to user for In -person Exam Reminder "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 19 : Verify a modal dialog is displayed to user for In -person Exam Reminder"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + State_BowHunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + State_BowHunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + State_BowHunter);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + State_BowHunter);
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
		System.out.println("Step 4 : Verifying whether modal dialog displayed or not");
		Reporter.log("Step 4 : Verifying whether modal dialog displayed or not"); 
			
			
		if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{
				
				System.out.println("Success !! Modal dialog is present '");
				Reporter.log("Success !! Modal dialog is present '"); 
				
				System.out.println("Step 5 : Click on 'I understand..' button and verifying whether model is closed or not");
				Reporter.log("Step 5 : Click on 'I understand..' button and verifying whether model is closed or not");
				
					register.ClickOnIUnderstandButton();
					Thread.sleep(4000);

					if(SeleniumFunc.GetAttributeValue("id", "wait-right-there","aria-hidden").equalsIgnoreCase("true"))
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
	
	
	
	/* Test 20: 
	 * Verify that if a course has a minimum age requirement, the requirement is correctly enforced at registration
	*/ 
	@Test(dataProvider="Registration_MimAgeValidation_bowhunter",dataProviderClass=utility.TestNG.class)
	private void Registration_MimAgeValidation
							(	String State_BowHunter,
								String minage,
								String month,String day ,String year
							) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 20 : Verify that if a course has a minimum age requirement, the requirement is correctly enforced at registration"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 20 : Verify that if a course has a minimum age requirement, the requirement is correctly enforced at registration"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_BowHunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_BowHunter); 

		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + State_BowHunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_BowHunter + State_BowHunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + State_BowHunter);
			PageHeader header = new PageHeader(driver); Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_BowHunter + State_BowHunter);
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
			register.FillRegistrationForm(username, "piyush@143", "piyush@143", "test", "B", "Ke-testing", "Jr.", month, day, year, "testing@gmail.com", "testing@gmail.com");
			
			try
			{
				register.ClickOnStateResidencyCheckbox();
			}
			catch(Exception e)
			{
				//exit
			}
			
			register.ClickOnCreateAccount();
	
			
		System.out.println("Step 4 : Verifying whether correct validation message is displayed or not");
		Reporter.log("Step 4 : Verifying whether correct validation message is displayed or not"); 
			
			Thread.sleep(10000);
			String ActualValidationMessage=SeleniumFunc.GetElementText("css", "section.row li").trim();
			System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage = "The minimum eligible age is " + minage+ ".";
			System.out.println(ExpectedValidationMessage);
			
			if(ActualValidationMessage.equals(ExpectedValidationMessage))
			{
				
				System.out.println("Success !! message is correct'");
				Reporter.log("Success !! message is correct"); 
			}
			else
			{
				System.out.println("Failure !!message is incorrect");
				Reporter.log("Failure !! message is incorrect"); 
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


