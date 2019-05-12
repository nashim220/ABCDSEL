package courses.BoatEdAllStates;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.PageHeader;
import pageFactory.Courses.RegistrationPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class RegistrationAllTest 
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
	@Test(dataProvider="RegistrationStates",dataProviderClass=utility.TestNG.class)
	private void RegistrationAllStates(String State, String Title) throws Exception
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
	PageHeader header = new PageHeader(driver); 
	
	System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL);
	Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL); 
	
	System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + State);
	Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + State); 
	
		SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);

	//Verifying Header UI elements
	
	//Verifying application name
	String expectedtext = Title;
	String actualtext = header.ApplicationName_New.getText();
	System.out.println(actualtext);

	if(actualtext.contains(expectedtext) )
	{
		System.out.println("Success!! header has correct application name i.e. " + actualtext);
		Reporter.log("Success!! header has correct application name i.e. " + actualtext); 
	}
	else
	{
		System.out.println("Failure !! header has incorrect application name  " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
		Reporter.log("Failure !! header has incorrect application name  " + "\n" + "Expected Text: "  
				+ "\n" + expectedtext + "\n" + 
				 "Actual Text : " + "\n" +  actualtext);
		
		AssertFailedCount++;
	}
	
	
	//Verifying presence of 'Study Guide' link 
	expectedtext = "Study Guide";
	actualtext = header.StudyGuide_New.getText();
	System.out.println(actualtext);

	if(actualtext.contains(expectedtext) )
	{
		System.out.println("Success!! header has 'Study Guide' link  i.e. " + actualtext);
		Reporter.log("Success!! header has 'Study Guide' link  i.e. " + actualtext); 
	}
	else
	{
		System.out.println("Failure !! header doesn't have 'Study Guide' link   " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
		Reporter.log("Failure !! header doesn't have 'Study Guide' link   " + "\n" + "Expected Text: "  
				+ "\n" + expectedtext + "\n" + 
				 "Actual Text : " + "\n" +  actualtext);
		
		AssertFailedCount++;
	}

	
	//Verifying presence of 'Lost Your Card?' link 
	expectedtext = "Lost Your Card?";
	actualtext = header.LostYourCard_New.getText();
	System.out.println(actualtext);

	if(actualtext.contains(expectedtext) )
	{
		System.out.println("Success!! header has ' Lost Your Card?' link  i.e. " + actualtext);
		Reporter.log("Success!! header has ' Lost Your Card?' link  i.e. " + actualtext); 
	}
	else
	{
		System.out.println("Failure !! header doesn't have ' Lost Your Card?' link   " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
		Reporter.log("Failure !! header doesn't have 'Lost Your Card?' link   " + "\n" + "Expected Text: "  
				+ "\n" + expectedtext + "\n" + 
				 "Actual Text : " + "\n" +  actualtext);
		
		AssertFailedCount++;
	}
	
	
	//Verifying presence of 'FAQs' link 
	expectedtext = "FAQs";
	actualtext = header.Before_FAQs_New.getText();
	System.out.println(actualtext);

	if(actualtext.contains(expectedtext) )
	{
		System.out.println("Success!! header has '  FAQs' link  i.e. " + actualtext);
		Reporter.log("Success!! header has '  FAQs' link  i.e. " + actualtext); 
	}
	else
	{
		System.out.println("Failure !! header doesn't have ' FAQs' link   " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
		Reporter.log("Failure !! header doesn't have ' FAQs' link   " + "\n" + "Expected Text: "  
				+ "\n" + expectedtext + "\n" + 
				 "Actual Text : " + "\n" +  actualtext);
		
		AssertFailedCount++;
	}
	
	
	//Verifying presence of 'Register' link 
	expectedtext = "Register";
	actualtext = header.Register_New.getText();
	System.out.println(actualtext);

	if(actualtext.contains(expectedtext) )
	{
		System.out.println("Success!! header has 'Register' link  i.e. " + actualtext);
		Reporter.log("Success!! header has 'Register' link  i.e. " + actualtext); 
	}
	else
	{
		System.out.println("Failure !! header doesn't have 'Register' link   " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
		Reporter.log("Failure !! header doesn't have 'Register' link   " + "\n" + "Expected Text: "  
				+ "\n" + expectedtext + "\n" + 
				 "Actual Text : " + "\n" +  actualtext);
		
		AssertFailedCount++;
	}
	
	
	
	//Verifying presence of 'Log In' link 
	expectedtext = "Log In";
	actualtext = header.Login_New.getText();
	System.out.println(actualtext);

	if(actualtext.contains(expectedtext) )
	{
		System.out.println("Success!! header has 'Log In' link  i.e. " + actualtext);
		Reporter.log("Success!! header has 'Log In' link  i.e. " + actualtext); 
	}
	else
	{
		System.out.println("Failure !! header doesn't have 'Log In' link   " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
		Reporter.log("Failure !! header doesn't have 'Log In' link   " + "\n" + "Expected Text: "  
				+ "\n" + expectedtext + "\n" + 
				 "Actual Text : " + "\n" +  actualtext);
		
		AssertFailedCount++;
	}

	
	System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + State);
	Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + State); 
	
		//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
		header.Register_New.click();
	
		//Model Reminder
		if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
		{
			
			register.ClickOnIUnderstandButton();
			
		}
		
		
	System.out.println("Step 3 : Filling registration form and clicking on 'Create Account' button");
	Reporter.log("Step 3 : Filling registration form and clicking on 'Create Account' button"); 
	
		String username="Reg" + JH.GenerateRandomNumber();
		String emailprefix = "registration" + JH.GenerateRandomNumber();
		String emailaddress= emailprefix + "@mailinator.com";
		System.out.println(emailaddress);
		String password = "clarion@123";
		register.FillRegistrationForm(username, password,password, "Test","R","Ke-testing","Jr.","May","1","1990", emailaddress, emailaddress);
		
		//Residency Requirement
		if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
		{
				register.ClickOnStateResidencyCheckbox();		
		}
		
		register.ClickOnCreateAccount();

		Thread.sleep(8000);
		
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

		//Verifying Header UI elements
		
		//Verifying application name
		expectedtext = Title;
		actualtext = header.ApplicationName.getText();
		System.out.println(actualtext);

		if(actualtext.contains(expectedtext) )
		{
			System.out.println("Success!! header has correct application name i.e. " + actualtext);
			Reporter.log("Success!! header has correct application name i.e. " + actualtext); 
		}
		else
		{
			System.out.println("Failure !! header has incorrect application name  " + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
			Reporter.log("Failure !! header has incorrect application name  " + "\n" + "Expected Text: "  
					+ "\n" + expectedtext + "\n" + 
					 "Actual Text : " + "\n" +  actualtext);
			
			AssertFailedCount++;
		}
		
		
		//Verifying presence of 'Contents' link 
		expectedtext = "Contents";
		actualtext = header.Contents.getText();
		System.out.println(actualtext);

		if(actualtext.contains(expectedtext) )
		{
			System.out.println("Success!! header has 'Contents' link  i.e. " + actualtext);
			Reporter.log("Success!! header has 'Contents' link  i.e. " + actualtext); 
		}
		else
		{
			System.out.println("Failure !! header doesn't have 'Contents' link   " + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
			Reporter.log("Failure !! header doesn't have 'Contents' link   " + "\n" + "Expected Text: "  
					+ "\n" + expectedtext + "\n" + 
					 "Actual Text : " + "\n" +  actualtext);
			
			AssertFailedCount++;
		}
	
		
		
		
		//Verifying presence of 'FAQs' link 
		expectedtext = "FAQs";
		actualtext = header.After_FAQs.getText();
		System.out.println(actualtext);

		if(actualtext.contains(expectedtext) )
		{
			System.out.println("Success!! header has '  FAQs' link  i.e. " + actualtext);
			Reporter.log("Success!! header has '  FAQs' link  i.e. " + actualtext); 
		}
		else
		{
			System.out.println("Failure !! header doesn't have ' FAQs' link   " + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
			Reporter.log("Failure !! header doesn't have ' FAQs' link   " + "\n" + "Expected Text: "  
					+ "\n" + expectedtext + "\n" + 
					 "Actual Text : " + "\n" +  actualtext);
			
			AssertFailedCount++;
		}
		
		
		//Verifying presence of 'Profile' link 
		expectedtext = username;
		actualtext = header.Profile.getText();
		System.out.println(actualtext);

		if(actualtext.contains(expectedtext) )
		{
			System.out.println("Success!! header has 'Profile' link  i.e. " + actualtext);
			Reporter.log("Success!! header has 'Profile' link  i.e. " + actualtext); 
		}
		else
		{
			System.out.println("Failure !! header doesn't have 'Profile' link   " + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
			Reporter.log("Failure !! header doesn't have 'Profile' link   " + "\n" + "Expected Text: "  
					+ "\n" + expectedtext + "\n" + 
					 "Actual Text : " + "\n" +  actualtext);
			
			AssertFailedCount++;
		}
		
		
		
		//Verifying presence of 'Save & Log Out' link 
		expectedtext = "Save & Log Out";
		actualtext = header.SaveAndLogOut.getText();
		System.out.println(actualtext);

		if(actualtext.contains(expectedtext) )
		{
			System.out.println("Success!! header has 'Save & Log Out' link  i.e. " + actualtext);
			Reporter.log("Success!! header has 'Save & Log Out' link  i.e. " + actualtext); 
		}
		else
		{
			System.out.println("Failure !! header doesn't have 'Save & Log Out' link   " + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
			Reporter.log("Failure !! header doesn't have 'Save & Log Out' link   " + "\n" + "Expected Text: "  
					+ "\n" + expectedtext + "\n" + 
					 "Actual Text : " + "\n" +  actualtext);
			
			AssertFailedCount++;
		}

	System.out.println("Step 5: Logging out of application");
	Reporter.log("Step 5: Logging out of application"); 
				
			SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");
			
	System.out.println("Step 6 : Verifying whether user is logged out successfully or not");
	Reporter.log("Step 6 : Verifying whether user is logged out successfully or not"); 
			
			if(SeleniumFunc.ToGetCurrentPageUrl().equals(Constants.ApplicationURL.subSequence(5,Constants.ApplicationURL.length())+ "/site/logout/"))
			{
				System.out.println("Success !! User is navigated to Logout page");
				Reporter.log("Success !! User is navigated to Logout page"); 
			}
			else
			{
				System.out.println("Failure !!  User is NOT navigated to Logout page , instead navigated to: " + SeleniumFunc.ToGetCurrentPageUrl());
				Reporter.log("Failure !!  User is NOT navigated to Logout page , instead navigated to: " + SeleniumFunc.ToGetCurrentPageUrl()); 
	
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
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void Registration_ChooseAnotherLink(String State) throws Exception
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
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL); 
					
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); header.Register_New.click();
		
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{
				
				register.ClickOnIUnderstandButton();
				
			}
			
		System.out.println("Step 3 : Clicking on 'Choose another' link ");
		Reporter.log("Step 3 : Clicking on 'Choose another' link"); 
		
			register.ClickOnChooseAnother();
	
		System.out.println("Step 4 : Verifying whether user is navigated to home page or not");
		Reporter.log("Step 4 : Verifying whether user is navigated to home page or not"); 
			
			
			if(SeleniumFunc.ToGetCurrentPageUrl().contains(Constants.ApplicationURL.subSequence(5,Constants.ApplicationURL.length())))
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
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void Registration_ValidationMessageForMandatoryFields(String State, String Title) throws Exception
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
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL); 
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); header.Register_New.click();
		
			//Model Reminder
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{
				
				register.ClickOnIUnderstandButton();
				
			}
			
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
					register.ClickOnStateResidencyCheckbox();		
			}
			
		System.out.println("Step 3 : Clicking on 'Create Account' button without entering any data on registration page");
		Reporter.log("Step 3 : Clicking on 'Create Account' button without entering any data on registration page"); 
		
			register.ClickOnCreateAccount();
			Thread.sleep(2000);
			
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
	
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void Registration_ValidationMessageForChooseUsername1(String State) throws Exception
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
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL); 
					
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); header.Register_New.click();
		
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{
				
				register.ClickOnIUnderstandButton();
				
			}
			
		System.out.println("Step 3 : Entering special characters in 'Choose a Username' field e.g $%%");
		Reporter.log("Step 3 : Entering special characters in 'Choose a Username' field e.g $%%"); 
		
			register.ChooseAUsername("%%GG");
			register.ClickOnCreateAccount();
			register.ClickOnCreateAccount();
	
		System.out.println("Step 4 : Verifying whether validation messages are displayed for 'Choose a Username' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for 'Choose a Username' field"); 
						
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "div[class='control-group error'] div p:nth-of-type(1)") + "\n" +
											SeleniumFunc.GetElementText("css", "div[class='control-group error'] div p:nth-of-type(2)");
			String ExpectedValidationMessage= 
					"Your username must meet these requirements:" + "\n" + 
					"Must be 4�32 characters long and can only include letters and numbers."; 
			
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

	
	
	/* Test 5: 
	 *Verify that validation message is displayed if input criteria doesn't match for 'Choose a Username' field - 
	 *B) if username is < 4 charater or >32 character"
	*/ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void Registration_ValidationMessageForChooseUsername2(String State) throws Exception
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
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL); 
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); header.Register_New.click();
		
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{
				
				register.ClickOnIUnderstandButton();
				
			}
			
		System.out.println("Step 3 : Entering < 4 charater in 'Choose a Username' field e.g abc");
		Reporter.log("Step 3 : Entering < 4 charater in 'Choose a Username' field e.g abc"); 
		
			register.ChooseAUsername("abc");
		
		System.out.println("Step 4 : Entering data in other field and click on 'Create Account' button");
		Reporter.log("Step 4 : Entering data in other field and click on 'Create Account' button");
			
			register.EnterPassword("qwerty123");
			register.EnterRepeatPassword("qwerty123");
			register.EnterFirstName("Piyush");
			register.EnterLastName("Patel");
			register.EnterDOB("January", "2", "1990");
			register.EnterEmailAddress("piyush@testing.com");
			register.EnterRepeatEmailAddress("piyush@testing.com");
			register.ClickOnCreateAccount();
			Thread.sleep(Constants.ThreadWaitInmiliseconds);

		System.out.println("Step 5 : Verifying whether validation messages are displayed for 'Choose a Username' field");
		Reporter.log("Step 5 : Verifying whether validation messages are displayed for 'Choose a Username' field"); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "section.row div.alert-banner");
			String ExpectedValidationMessage= 
					"Username must be 4�32 characters long and can only include letters and numbers."; 
			
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
	 * Verify that validation message is displayed if input criteria doesn't match for 'Password' field   
	A)  Enter special characters
	*/ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void Registration_ValidationMessageForPassword1(String State) throws Exception
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
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL); 
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); header.Register_New.click();
		
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{
				
				register.ClickOnIUnderstandButton();
				
			}
			
		System.out.println("Step 3 : Entering special characters in 'Password' field e.g $%%");
		Reporter.log("Step 3 : Entering special characters in 'Password' field e.g $%%"); 
		
			register.EnterPassword("%%GG");
			register.ClickOnCreateAccount();
			register.ClickOnCreateAccount();
	
		System.out.println("Step 4 : Verifying whether validation messages are displayed for 'Password' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for 'Password' field"); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "section#username div:nth-of-type(2) p:nth-of-type(1)") + "\n" +
											SeleniumFunc.GetElementText("css", "section#username div:nth-of-type(2) p:nth-of-type(2)");
			String ExpectedValidationMessage= 
					"Your password must meet these requirements:" + "\n" + 
					"Must be 8�32 characters long and must include letters and numbers."; 
			
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
	
	
	
	/* Test 7: 
	 * Verify that validation message is displayed if input criteria doesn't match for 'Password' field   
	B)  if password is < 8 charater or >32 character
	*/ 
	
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void Registration_ValidationMessageForPassword2(String State) throws Exception
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
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL); 
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); header.Register_New.click();
		
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{
				
				register.ClickOnIUnderstandButton();
				
			}
			
		System.out.println("Step 3 : Entering <8 characters in 'Password' field e.g 1234");
		Reporter.log("Step 3 : Entering special characters in 'Password' field e.g 1234"); 
		
			register.EnterPassword("1234567");
			register.ClickOnCreateAccount();
			register.ClickOnCreateAccount();
	
		System.out.println("Step 4 : Verifying whether validation messages are displayed for 'Password' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for 'Password' field"); 
				
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "section#username div:nth-of-type(2) p:nth-of-type(1)") + "\n" +
											SeleniumFunc.GetElementText("css", "section#username div:nth-of-type(2) p:nth-of-type(2)");
			
			String ExpectedValidationMessage= 
					"Your password must meet these requirements:" + "\n" + 
					"Must be 8�32 characters long and must include letters and numbers."; 
			
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
	
	
	
	/* Test 8: 
	 * Verify that validation message is displayed if input criteria doesn't match for 'Password' field   
	C)  if password doesn't include letter and number both
	*/ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void Registration_ValidationMessageForPassword3(String State) throws Exception
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
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL); 
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); header.Register_New.click();
		
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{
				
				register.ClickOnIUnderstandButton();
				
			}
			
		System.out.println("Step 3 : Entering >8 characters (only letters) in 'Password' field e.g qwertyuio");
		Reporter.log("Step 3 : Entering >8 characters (only letters) in 'Password' field e.g qwertyuio"); 
		
			register.EnterPassword("qwertyuio");
			register.ClickOnCreateAccount();
			register.ClickOnCreateAccount();
			
		System.out.println("Step 4 : Verifying whether validation messages are displayed for 'Password' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for 'Password' field"); 
						
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "fieldset[class='account-info'] div:nth-of-type(2) div p:nth-of-type(1)") + "\n" +
											SeleniumFunc.GetElementText("css", "fieldset[class='account-info'] div:nth-of-type(2) div p:nth-of-type(2)");
			String ExpectedValidationMessage= 
					"Your password must meet these requirements:" + "\n" + 
					"Must be 8�32 characters long and must include letters and numbers."; 
			
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
			
			
		System.out.println("Step 5 : Entering >8 characters (only numbers) in 'Password' field e.g 123456789");
		Reporter.log("Step 5 : Entering >8 characters (only numbers) in 'Password' field e.g 123456789"); 
		
			register.EnterPassword("123456789");
			register.ChooseAUsername("");
	
		System.out.println("Step 4 : Verifying whether validation messages are displayed for 'Password' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for 'Password' field"); 
			
			
		ActualValidationMessage= SeleniumFunc.GetElementText("css", "fieldset[class='account-info'] div:nth-of-type(2) div p:nth-of-type(1)") + "\n" +
					SeleniumFunc.GetElementText("css", "fieldset[class='account-info'] div:nth-of-type(2) div p:nth-of-type(2)");
			
			ExpectedValidationMessage= 
				"Your password must meet these requirements:" + "\n" + 
				"Must be 8�32 characters long and must include letters and numbers."; 
		
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
	
	
	
	/* Test 9: 
	 * Verify that validation message is displayed if Password/Repeat Password fields value doesn't match
	*/ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void Registration_ValidationMessageForPasswordRepeatPassword(String State) throws Exception
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
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); header.Register_New.click();
		
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
			register.ClickOnCreateAccount();
			
			/*register.ChooseAUsername("");
			register.EnterRepeatEmailAddress("");*/
	
		System.out.println("Step 5 : Verify that validation message is displayed if Password/Repeat Password fields value doesn't match");
		Reporter.log("Step 5 : Verify that validation message is displayed if Password/Repeat Password fields value doesn't match"); 
					
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "fieldset[class='account-info'] div:nth-of-type(3) p:nth-of-type(1)") + "\n" +
											SeleniumFunc.GetElementText("css", "fieldset[class='account-info'] div:nth-of-type(3) p:nth-of-type(2)");
			String ExpectedValidationMessage= 
					"This doesn't match what you entered in the first field. Please try again." + "\n" + 
					"Re-enter the password from above, just to make sure we�ve got it right."; 
			
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

	
	
	/* Test 10: 
	 * Verify that validation message is displayed if input criteria doesn't match for 'Date of Birth' field 
       A) if year field has <4 chars"
	*/ 
	
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void Registration_ValidationMessageForDOB1(String State) throws Exception
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
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL); 
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); header.Register_New.click();
		
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
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void Registration_ValidationMessageForDOB2(String State) throws Exception
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
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL); 
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); header.Register_New.click();
		
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
			register.EnterLastName("Patel");
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
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void Registration_ValidationMessageForEmailAddress(String State) throws Exception
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
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL); 
			
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); header.Register_New.click();
		
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
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void Registration_ValidationMessageForEmailRepeatEmailAddress(String State) throws Exception
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
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL); 
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); header.Register_New.click();
		
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
				
		System.out.println("Step 4 : Verifying whether validation messages are displayed for 'Email Address' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for 'Email Address' field"); 
						
			String ActualValidationMessage= 
					SeleniumFunc.GetElementText("css", "section#contact-info div[class='control-group error'] p:nth-of-type(1)") + "\n" +
					SeleniumFunc.GetElementText("css", "section#contact-info div[class='control-group error'] p:nth-of-type(2)");
			
			String ExpectedValidationMessage= 
					"This doesn't match what you entered in the first field. Please try again." + "\n" +
					"Re-enter the e-mail address from above, just to make sure we�ve got it right.";
			
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
	 * Verify title of page i.e. Register for the Boat {State} Course
	*/ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void Registration_VerifyTitle(String State) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 15 : Verify title of page i.e. Register for the Boat {State} Course"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 15 : Verify title of page i.e. Register for the Boat {State} Course"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL); 
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + "/" + State); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); header.Register_New.click();
			RegistrationPage register = new RegistrationPage(driver);

			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{
				
				register.ClickOnIUnderstandButton();
				
			}
			
		System.out.println("Step 3 : Verifying Page Title i.e. Register for the Boat {State} Course ");
		Reporter.log("Step 3 : Verifying Page Title i.e. Register for the Boat {State} Course "); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "div.page-header h1");
			//System.out.println(ActualValidationMessage);
											
			String ExpectedValidationMessage="Register for the Boat " + State + " Course" + "\n" +
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


	
	
	/* Test 20: 
	 * Verify that if a course has a minimum age requirement, the requirement is correctly enforced at registration
	*/ 
	@Test(dataProvider="Registration_MimAgeValidation",dataProviderClass=utility.TestNG.class)
	private void Registration_MimAgeValidation
							(	String state,
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
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL); 
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + state);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + state); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + state);
			PageHeader header = new PageHeader(driver); header.Register_New.click();
			
		System.out.println("Step 3 : Filling registration form (with Age < minimum age required) and clicking on 'Create Account' button");
		Reporter.log("Step 3 : Filling registration form (with Age < minimum age required) and clicking on 'Create Account' button"); 
		
			//For few States, closing modal dialog present on Registration page
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
		
			String username="min"+ JH.GenerateRandomNumber();
			register.FillRegistrationForm(username, "piyush@143", "piyush@143", "test", "", "Ke-testing", "Jr.", month, day, year, "testing@gmail.com", "testing@gmail.com");
			register.ClickOnCreateAccount();
				
		System.out.println("Step 4 : Verifying whether correct validation message is displayed or not");
		Reporter.log("Step 4 : Verifying whether correct validation message is displayed or not"); 
			
			Thread.sleep(10000);
			String ActualValidationMessage=SeleniumFunc.GetElementText("css", "section.row li").trim();
			System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage = "The minimum eligible age is " + minage+ ".";
			
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
