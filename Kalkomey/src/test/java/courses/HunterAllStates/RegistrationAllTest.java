package courses.HunterAllStates;

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
	@Test(dataProvider="RegistrationStatesHunter",dataProviderClass=utility.TestNG.class)
	private void RegistrationAllStates(String State, String Title) throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 1 : Verify that student can register for course successfully"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			  "Test 1 : Verify that student can register for course successfully"  + "\n" +
			 	  "====");	
	
		int AssertFailedCount=0 ;
		HunterRegistrationPage register = new HunterRegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver); 
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_Hunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_Hunter); 
	
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + State);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + State); 
	
		SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter +  "/" + State);
		
		Thread.sleep(4000);
		if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
		{
			header.Logout_Handgun.click();
			Thread.sleep(4000);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter  +  "/" + State);
			Thread.sleep(4000);
		}
			
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

	System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + State);
	Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + State); 
	
			//SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			header.Register_New.click();
			Thread.sleep(4000);

			//Model Reminder
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{			
				register.ClickOnIUnderstandButton();	
				Thread.sleep(4000);
			}
			
	System.out.println("Step 3 : Filling registration form and clicking on 'Create Account' button");
	Reporter.log("Step 3 : Filling registration form and clicking on 'Create Account' button"); 
	
		String username= "Hunter" + JH.GenerateRandomNumber();
		String emailaddress= username + "@mailinator.com";
	
		register.FillRegistrationForm(username, "August", "2", "1990", emailaddress,"clarion@123", "clarion@123");
	
		//Residency Requirement
		if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
		{
			register.ClickOnStateResidencyCheckbox();
			Thread.sleep(4000);
		}
	
		register.ClickOnCreateAccount();
		Thread.sleep(5000);

		//Fill out additional information
		String ExpectedURL = Constants.ApplicationURL + Constants.State + "/sign-up/details/";
		String ActualURL = SeleniumFunc.ToGetCurrentPageUrl();

		if(ExpectedURL.trim().contains(ActualURL.trim()))
		{	
			System.out.println("Step 4 : Fill out additional information");
			Reporter.log("Step 4 : Fill out additional information");
						
			if(SeleniumFunc.IsElementPresent(register.Gender))
			{
				register.SelectGender("Male");
			}
			
			if(SeleniumFunc.IsElementPresent(register.Etinicity))
			{
				register.SelectEtinicity("Asian");
			}
	  
			if(SeleniumFunc.IsElementPresent(register.EnterSSN))
			{
				register.EnterSSNNumber("123-45-6789");
			}
			
			if(SeleniumFunc.IsElementPresent(register.Enter4SSN))
			{
				register.Enter4SSN("6789");
			}
	   
			if(SeleniumFunc.IsElementPresent(register.EyeColor))
			{
				register.SelectEye("Black");
			}
	   
			if(SeleniumFunc.IsElementPresent(register.HairColor))
			{
				register.SelectHairColor("Black");
			}
		 
			if(SeleniumFunc.IsElementPresent(register.Weight))
			{
				register.EnterWeight("70");
			}
		  
			if(SeleniumFunc.IsElementPresent(register.HeightFoot))
			{
				register.EnterHeightFoot("6");
			}
		   
			if(SeleniumFunc.IsElementPresent(register.HeightInch))
			{
				register.EnterHeightInch("6");
			}   
		   				   
			register.ClickOnCreateAccount();
			Thread.sleep(6000);
		}	
		else	
		{
			System.out.println("Step 4 : Skipped As No additional information needed.");
			Reporter.log("Step 4 : Skipped As No additional information needed.");
		}
	
		if(SeleniumFunc.IsElementPresent(register.WelcomeAboard))
		{					
			register.WelcomeAboard.click();
		}
	
		Thread.sleep(3000);
		
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
			System.out.println("Failure !! Username is incorrect");
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
		Thread.sleep(4000);

	System.out.println("Step 6 : Verifying whether user is logged out successfully or not");
	Reporter.log("Step 6 : Verifying whether user is logged out successfully or not"); 
			
			if(SeleniumFunc.ToGetCurrentPageUrl().equals(Constants.ApplicationURL_Hunter.subSequence(5,Constants.ApplicationURL_Hunter.length())+ "/site/logout/"))
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
	 * Verify that validation messages are displayed for mandatory fields
	*/ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void Registration_ValidationMessageForMandatoryFields(String state) throws Exception
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
						
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter +  "/" + state);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter +  "/" + state); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter +  "/" + state);
			PageHeader header = new PageHeader(driver); 
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter  +  "/" + state);
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
			
		System.out.println("Step 3 : Clicking on 'Create Account' button without entering any data on registration page");
		Reporter.log("Step 3 : Clicking on 'Create Account' button without entering any data on registration page"); 
	
			register.ClickOnCreateAccount();
			Thread.sleep(4000);

		System.out.println("Step 4 : Verifying whether validation messages are displayed for mandatory fields");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for mandatory fields"); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", ".alert.alert-danger");
			String ExpectedValidationMessage=
					"×"+"\n"+
					"Please review and correct the following fields:" + "\n" + 
					"Username" + "\n" + 
					"E-mail Address" + "\n" + 
					"Year"   ; 
			
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
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_Hunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_Hunter); 
					
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + "/" + State);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + "/" + State); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/" + State);
			PageHeader header = new PageHeader(driver); header.Register_New.click();
		
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/" + State);
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
				register.ClickOnStateResidencyCheckbox();
				Thread.sleep(4000);
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
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void Registration_ValidationMessageForChooseUsername2(String State) throws Exception
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
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/" + State);
			PageHeader header = new PageHeader(driver); 	
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/" + State);
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
	
			register.ChooseAUsername("pqc");
		
		System.out.println("Step 4 : Entering data in other field and click on 'Create Account' button");
		Reporter.log("Step 4 : Entering data in other field and click on 'Create Account' button");
			
			
			register.EnterDOB("January", "2", "1990");
			register.EnterEmailAddress("piyush@testing.com");
			
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
				register.ClickOnStateResidencyCheckbox();
				Thread.sleep(4000);
			}
			
			register.ClickOnCreateAccount();
			Thread.sleep(Constants.ThreadWaitInmiliseconds);

		System.out.println("Step 5 : Verifying whether validation messages are displayed for 'Choose a Username' field");
		Reporter.log("Step 5 : Verifying whether validation messages are displayed for 'Choose a Username' field"); 
				
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "div.help-block");
			String ExpectedValidationMessage= 
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
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void Registration_ValidationMessageForDOB1(String State) throws Exception
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
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/" + State);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/" + State);
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
		
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
				register.ClickOnStateResidencyCheckbox();
				Thread.sleep(4000);
			}
	
			register.EnterDOB("January", "1", "199");
			register.ChooseAUsername("");
	
		System.out.println("Step 4 : Verifying whether validation messages are displayed for DOB 'Year' field");
		Reporter.log("Step 4 : Verifying whether validation messages are displayed for DOB 'Year' field"); 
				
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#errorbox-1");							
			String ExpectedValidationMessage="Please complete your date of birth."; 
			
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


	
	/* Test 6: 
	 * Verify that validation message is displayed if input criteria doesn't match for 'Date of Birth' field 
      B) if year field has invalid value e.g .asdd,$%$% etc.
	*/ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void Registration_ValidationMessageForDOB2(String State) throws Exception
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
					
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + "/" + State);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + "/" + State);; 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/" + State);
			PageHeader header = new PageHeader(driver); 	
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/" + State);
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
		
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
				register.ClickOnStateResidencyCheckbox();
				Thread.sleep(4000);
			}
			
			register.ClickOnCreateAccount();
			Thread.sleep(Constants.ThreadWaitInmiliseconds);

		System.out.println("Step 5 : Verifying whether validation messages are displayed for 'DOB Year' field");
		Reporter.log("Step 5 : Verifying whether validation messages are displayed for 'DOB Year' field"); 
			
			
			String ActualValidationMessage= SeleniumFunc.GetElementText("css", "#errorbox-1");
			String ExpectedValidationMessage= 
					"Please complete your date of birth."; 
			
			if(ActualValidationMessage.contains(ExpectedValidationMessage))
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
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void Registration_ValidationMessageForEmailAddress(String State) throws Exception
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
			
			
	
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + "/"+ State);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + "/"+ State); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/"+ State); 
			PageHeader header = new PageHeader(driver); 	
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/"+ State); 
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
			
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
				register.ClickOnStateResidencyCheckbox();
				Thread.sleep(4000);
			}
			
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
	
	
	
	/* Test 9: 
	 * Verify title of page i.e. Register for the Boat {State_Hunter} Course
	*/ 
	@Test(dataProvider="Profile",dataProviderClass=utility.TestNG.class)
	private void Registration_VerifyTitle(String State) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Verify title of page i.e. Register for the {State} Hunter Course"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 : Verify title of page i.e. Register for the  {State} Hunter Course"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL_Hunter);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL_Hunter); 
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + Constants.State_Hunter); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/" + State);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter  + "/" + State);
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

	
	/* Test 14: 
	 * Verify that if a course has a minimum age requirement, the requirement is correctly enforced at registration
	*/ 
	@Test(dataProvider="Registration_MinAgeValidation_hunter",dataProviderClass=utility.TestNG.class)
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

		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter+ "/" + State_Hunter);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL_Hunter + "/" + State_Hunter);
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/" + State_Hunter);
			PageHeader header = new PageHeader(driver); 

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/" + State_Hunter);
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
		Reporter.log( "Step 4 : Verifying whether correct validation message is displayed or not"); 
			
			Thread.sleep(10000);
			String ActualValidationMessage=SeleniumFunc.GetElementText("css", "#errorbox-1").trim();
			//System.out.println(ActualValidationMessage);
			
			String ExpectedValidationMessage = "Please complete your date of birth. You must be at least " + minage+ " years old." ;
			//System.out.println(ExpectedValidationMessage);
		//	Please complete your date of birth. You must be at least 18 years old.
			if(ActualValidationMessage.contains(ExpectedValidationMessage))
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
