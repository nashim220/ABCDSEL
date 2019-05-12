package courses.Handgun;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.ErrorPage;
import pageFactory.Courses.HomePage;
import pageFactory.Courses.PageFooter;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.RegistrationPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class CommonCases 
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
	 * Home - Verify UI elements on home page e.g. qa1.handgunsafetycourse-ed.com
	 * */ 
	@Test
	private void Home_UIElementVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Home - Verify UI elements on home page e.g. qa1.handgunsafetycourse-ed.com"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Home - Verify UI elements on home page e.g. qa1.handgunsafetycourse-ed.com"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HomePage home = new HomePage(driver);
		
		
		System.out.println("Step 1: Navigated to home page and verifying UI elements");
		Reporter.log("Step 1: Navigated to home page and verifying UI elements"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Handgun);
			
			Thread.sleep(4000);
			
			//Verifying Header 
				
				//verifying application name
				String expectedtext = "National Handgun Safety Course";
				String actualtext = home.Header_ApplicationName.getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! Header has correct application name i.e. " + actualtext);
					Reporter.log("Success!! Header has correct application name i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! Header has incorrect application name " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Header has incorrect application name " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//verifying presence of 'Login' button
				
				try
				{	Thread.sleep(4000);
					home.Header_Login_New.click();
					Thread.sleep(4000);
					driver.navigate().back();
					System.out.println("Success!! Header has 'Login' button");
					Reporter.log("Success!! Header has 'Login' button"); 
				}
				catch(Exception e)
				{
					System.out.println("Failure!! Header doesn't have 'Login' button");
					Reporter.log("Failure!! Header doesn't have 'Login' button"); 
					
					AssertFailedCount++;
				}
				
							
			//Verifying Billboard section 
				
				//verifying Billboard text
				expectedtext = "Handgun Safety Education Course";
				actualtext = driver.findElement(By.cssSelector("div [class='row'] h1")).getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! Billboard has correct text i.e. " + actualtext);
					Reporter.log("Success!! Billboard has correct text  i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! Billboard has incorrect text  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Billboard has incorrect text e " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
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
	
	
/*	
	 Test 2: 
	 * Error pages - Verify 500 error page
	 *  
	@Test
	private void ErrorPages_Verify500ErrorPage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Error pages - Verify 500 error page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Error pages - Verify 500 error page"  + "\n" +
				 	  "====");	
		
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ErrorPage error = new ErrorPage(driver);
		
		
		System.out.println("Step 1: Navigated to 500 error page and verifying UI elements");
		Reporter.log("Step 1: Navigated to 500 error page and verifying UI elements"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.Error500Page_Handgun);
			
			
			//Verifying Error Message header 
				
				String expectedtext = "Deep drifts ahead…";
				String actualtext = error.ErrorMessageHeader.getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! message is correct i.e. " + actualtext);
					Reporter.log("Success!! message is correct i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! Error message header is incorrect " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Error message header is incorrect " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				
			//Verifying Error Message Title 
				
				expectedtext = "Error 500";
				actualtext = error.ErrorMessageTitle.getText();
				
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! Error message title is correct i.e. " + actualtext);
					Reporter.log("Success!! Error message title is correct i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! Error message title is incorrect " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Error message title is incorrect " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
			//Verifying Error Message Text 
				
				expectedtext = "It appears there’s a problem with the page you were accessing. We’re dispatching a crack team of engineers to check it out. We suggest going back to the home page or giving our customer service representatives a shout.";
				actualtext = error.ErrorMessageText.getText();
				
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! Error message Text is correct i.e. " + actualtext);
					Reporter.log("Success!! Error message Text is correct i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! Error message Text is incorrect " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Error message Text is incorrect " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
			//Verifying "Back to the Home Page" button
				
				error.BackToHomeButton.click();
				
				expectedtext = Constants.ApplicationURL_Handgun.substring(5);
				actualtext = driver.getCurrentUrl();
				
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! 'Back to the Home Page' button is working as expected");
					Reporter.log("Success!! 'Back to the Home Page' button is working as expected"); 
				}
				else
				{
					System.out.println("Failure !! 'Back to the Home Page' button is working as expected" + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! 'Back to the Home Page' button is NOT working as expected" + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				driver.navigate().back();
				
				
			//Verifying "Contact Customer Service" button
				
				error.ContactCustomerServiceButton.click();
				
				expectedtext = Constants.ApplicationURL_Handgun.substring(5) + "/site/contact";
				actualtext = driver.getCurrentUrl();
				
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! 'Contact Customer Service' button is working as expected");
					Reporter.log("Success!! 'Contact Customer Service' button is working as expected"); 
				}
				else
				{
					System.out.println("Failure !! 'Contact Customer Service' button is working as expected" + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! 'Contact Customer Service' button is NOT working as expected" + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
		
		 * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 
	 	if(AssertFailedCount>0)	
		{
			
			//Marking this test as Failed
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
	 
		
	}

*/
	
	/* Test 3: 
	 * Header - Verify page header elements (before and after login)
	 * */ 
	@Test
	private void Header_UIElementVarification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Header - Verify page header elements (before and after login)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Header - Verify page header elements (before and after login)"  + "\n" +
				 	  "====");	
		
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		PageHeader header = new PageHeader(driver);
		HomePage home =new HomePage(driver);
	
		
		System.out.println("Step 1: Navigate to application home page");
		Reporter.log("Step 1: Navigate to application home page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Handgun_national);
			Thread.sleep(4000);
			
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Handgun_national);
				Thread.sleep(4000);
		}
			
			//Verifying Header UI elements
			

				//Verifying presence of 'Study Guide' link 
				String expectedtext = "Study Guide";
				String actualtext = header.StudyGuide_New.getText();
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
				expectedtext = "FAQ";
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
			
		
		System.out.println("Step 2: Signing up as new user ");
		Reporter.log("Step 2: Signing up as new user "); 	
		
			String username= "dashboard" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "dashboard" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			header.Register_New.click();
			
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{		
				register.ClickOnIUnderstandButton();	
			}
						
			register.SelectGender("Male");
			register.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "5", "1990", emailaddress, emailaddress);
			
			Thread.sleep(10000);
			
			//Verifying Header UI elements
			
				//Verifying application name
				expectedtext = "National Handgun Safety Course";
				actualtext = home.ApplicationName_Course.getText();
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
				expectedtext = "FAQ";
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
	 * Home  - Verify presence of  'Who Should Take This Course?' Section
	 * */ 
	@Test
	private void Home_GetCertifiedIn3StepsSection() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Home  - Verify presence of  'Who Should Take This Course?' Section"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Home  - Verify presence of  'Who Should Take This Course?' Section"  + "\n" +
				 	  "====");	
		
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		
		System.out.println("Step 1: Navigate to application page and verifying presence of  'Who Should Take This Course?' section");
		Reporter.log("Step 1: Navigate to application page and verifying presence of  'Who Should Take This Course?' section"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Handgun_national);
			
			
			//Verifying presence of 'Who Should Take This Course?' section
			
	
				//Verifying presence of  'Who Should Take This Course?' header text 
				String expectedtext = "Who should take this course?";
				String actualtext = driver.findElement(By.cssSelector("#steps h2:nth-of-type(2)")).getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! 'Who Should Take This Course?' header text is correct i.e. " + actualtext);
					Reporter.log("Success!! 'Who Should Take This Course?' header text is correct i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! 'Who Should Take This Course?' header text is incorrect  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! 'Who Should Take This Course?' header text is incorrect  " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
			
				
				//Verifying presence of step 1 'New or Experienced Handgun Owners' header text 
				expectedtext = "New or Experienced Handgun Owners";
				actualtext = driver.findElement(By.cssSelector("#steps p:nth-of-type(1) strong")).getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! step 1 'New or Experienced Handgun Owners'  header text is correct i.e. " + actualtext);
					Reporter.log("Success!! 'step 1 'New or Experienced Handgun Owners'  header text is correct i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! step 1 'New or Experienced Handgun Owners'  header text is incorrect  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! step 1 'New or Experienced Handgun Owners'  header text is incorrect  " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				
				//Verifying presence of Step 2 - 'Those Considering Handgun Ownership' header text 
				expectedtext = "Those Considering Handgun Ownership";
				actualtext = driver.findElement(By.cssSelector("#steps p:nth-of-type(3) strong")).getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! Step 2 - 'Those Considering Handgun Ownership' header text is correct i.e. " + actualtext);
					Reporter.log("Success!! 'Step 2 - 'Those Considering Handgun Ownership' header text is correct i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! Step 2 - 'Those Considering Handgun Ownership' header text is incorrect  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Step 2 - 'Those Considering Handgun Ownership' header text is incorrect  " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				

				//Verifying presence of Step 3 : 'Those Curious About Handgun Safety' header text 
				expectedtext = "Those Curious About Handgun Safety";
				actualtext = driver.findElement(By.cssSelector("#steps p:nth-of-type(5) strong")).getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! Step 3 : 'Those Curious About Handgun Safety' header text is correct i.e. " + actualtext);
					Reporter.log("Success!! 'Step 3 : 'Those Curious About Handgun Safety' header text is correct i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! Step 3 : 'Those Curious About Handgun Safety' header text is incorrect  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Step 3 : 'Those Curious About Handgun Safety' header text is incorrect  " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
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
	 * Footer - Verify page footer elements (before and after login)
	 * */ 
	@Test
	private void Footer_UIElementVarification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Footer - Verify page footer elements (before and after login)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Footer - Verify page footer elements (before and after login)"  + "\n" +
				 	  "====");	
		
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		PageFooter footer = new PageFooter(driver);
		PageHeader header = new PageHeader(driver);
		
	
		
		System.out.println("Step 1: Navigate to application state specific page");
		Reporter.log("Step 1: Navigate to application state specific page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Handgun);
			
			
			//Verifying Footer UI elements
			
				Thread.sleep(4000);
				String expectedtext = "Handgunsafetycourse.com is committed to handgun safety education and works to produce a handgun safety education course that's accurate, interesting, and easy to understand.";
				String actualtext = driver.findElement(By.cssSelector("#state-course>p")).getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success!!  'about-program' section has correct text i.e. " + actualtext);
					Reporter.log("Success!! 'about-program' section has correct text " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! 'about-program' section has incorrect text  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! 'about-program' section has incorrect text " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying presence of 'Follow us here' section
				
				expectedtext = "Follow Us";
				actualtext = footer.FollowUs.getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! footer has 'Follow us here' section i.e. " + actualtext);
					Reporter.log("Success!! footer has 'Follow us here' section i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !!  footer doesn't have 'Follow us here' section  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! footer doesn't have 'Follow us here' section " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				
				//Verifying presence of 'Sister links' section
				
				expectedtext = "More Online Recreational Safety Courses from Kalkomey";
				actualtext = footer.SisteLink_Header.getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! footer has 'Sister links' section i.e. " + actualtext);
					Reporter.log("Success!! footer has 'Sister links' section i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !!  footer doesn't have 'Sister links' section  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! footer doesn't have 'Sister links' section " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying presence of 'About Kalkomey Enterprises, Inc.' section
				
				expectedtext = "About Kalkomey Enterprises, Inc.";
				actualtext = footer.AboutKalKomeySectionHeader.getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! footer has 'About Kalkomey Enterprises, Inc.' section i.e. " + actualtext);
					Reporter.log("Success!! footer has 'About Kalkomey Enterprises, Inc.' section i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !!  footer doesn't have 'About Kalkomey Enterprises, Inc.' section  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! footer doesn't have 'About Kalkomey Enterprises, Inc.' section " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				
				//Verifying presence of 'Customer Support' section
				
				expectedtext = "Customer Support";
				actualtext = footer.CustomerSupportSectionHeader.getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! footer has 'Customer Support' section i.e. " + actualtext);
					Reporter.log("Success!! footer has 'Customer Support' section i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !!  footer doesn't have 'Customer Support' section  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! footer doesn't have 'Customer Support' section " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
		
		System.out.println("Step 2: Signing up as new user ");
		Reporter.log("Step 2: Signing up as new user "); 	
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Handgun_national);
			
			String username= "dashboard" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "dashboard" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			header.Register_New.click();
			Thread.sleep(6000);

			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{		
				register.ClickOnIUnderstandButton();	
			}				
			
			register.SelectGender("Male");
			register.EnterMailingAddress("Box 870291", "Ferguson Center", "Tuscaloosa", "Alabama", "35487", "United States of America", "234-567-8910");
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "5", "1990", emailaddress, emailaddress);
			
			Thread.sleep(10000);
			
		// Verifying Footer UI elements
			
				//Verifying Social links
				expectedtext = "TwitterYouTubehttps://twitter.com/kalkomeyhttps://youtube.com/kalkomey";
				actualtext = footer.GetAllSocialLinksName_Snowmobile() + footer.GetAllSocialLinks_Snowmobile();
				//System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! footer has correct Social links i.e. " + actualtext);
					Reporter.log("Success!! footer has correct Social links i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! footer has incorrect Social links  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! footer has incorrect Social links " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				
				//Verifying sister links
				expectedtext = "boat-ed.comhunter-ed.combowhunter-ed.comoffroad-ed.comsnowmobile-ed.comhandgunsafetycourse.com" + 
								"https://www.boat-ed.com/https://www.hunter-ed.com/https://www.bowhunter-ed.com/https://www.offroad-ed.com/https://www.snowmobile-ed.com/https://www.handgunsafetycourse.com/";
				actualtext = footer.GetAllSisterLinksName_Bowhunter() + footer.GetAllSisterLinks_Bowhunter();
				//System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! footer has correct sister sites links i.e. " + actualtext);
					Reporter.log("Success!! footer has correct sister sites links i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! footer has incorrect sister sites links  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! footer has incorrect sites sites links " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
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
	 * Error pages - Verify 404 error page
	 * */ 
	@Test
	private void ErrorPages_Verify404ErrorPage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Error pages - Verify 404 error page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 : Error pages - Verify 404 error page"  + "\n" +
				 	  "====");	
		
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ErrorPage error = new ErrorPage(driver);
		
		
		System.out.println("Step 1: Navigated to 404 error page and verifying UI elements");
		Reporter.log("Step 1: Navigated to 404 error page and verifying UI elements"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.Error404Page_Handgun);
			
			
			//Verifying Error Message header 
				
				String expectedtext = "Deep drifts ahead…";
				String actualtext = error.ErrorMessageHeader.getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! message is correct i.e. " + actualtext);
					Reporter.log("Success!! message is correct i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! Error message header is incorrect " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Error message header is incorrect " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				
			//Verifying Error Message Title 
				
				expectedtext = "Error 404";
				actualtext = error.ErrorMessageTitle.getText();
				
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! Error message title is correct i.e. " + actualtext);
					Reporter.log("Success!! Error message title is correct i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! Error message title is incorrect " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Error message title is incorrect " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
			//Verifying Error Message Text 
				
				expectedtext = "The page you're looking for doesn't exist. The address may be wrong, or the link that brought you here may be broken. We suggest going back to the home page or giving our customer service representatives a shout.";
				actualtext = error.ErrorMessageText.getText();
				
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! Error message Text is correct i.e. " + actualtext);
					Reporter.log("Success!! Error message Text is correct i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! Error message Text is incorrect " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Error message Text is incorrect " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
			//Verifying "Back to the Home Page" button
				
				error.BackToHomeButton.click();
				
				expectedtext = Constants.ApplicationURL_Handgun.substring(5);
				actualtext = driver.getCurrentUrl();
				
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! 'Back to the Home Page' button is working as expected");
					Reporter.log("Success!! 'Back to the Home Page' button is working as expected"); 
				}
				else
				{
					System.out.println("Failure !! 'Back to the Home Page' button is working as expected" + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! 'Back to the Home Page' button is NOT working as expected" + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				driver.navigate().back();
				
				
			//Verifying "Contact Customer Service" button
				
				error.ContactCustomerServiceButton.click();
				
				expectedtext = Constants.ApplicationURL_Handgun.substring(5) + "/site/contact";
				actualtext = driver.getCurrentUrl();
				
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! 'Contact Customer Service' button is working as expected");
					Reporter.log("Success!! 'Contact Customer Service' button is working as expected"); 
				}
				else
				{
					System.out.println("Failure !! 'Contact Customer Service' button is working as expected" + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! 'Contact Customer Service' button is NOT working as expected" + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
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
	 * State Home > To verify 'Send us an email' feature
	 * */ 
	@Test
	private void State_SendUsAnEmail() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : State Home > To verify 'Send us an email' feature"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10 : State Home > To verify 'Send us an email' feature"  + "\n" +
				 	  "====");	
		
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HomePage home = new HomePage(driver);
		
		
		System.out.println("Step 1: Navigate to application state specific page and click on 'Send Us An E-mail' link from footer");
		Reporter.log("Step 1: Navigate to application state specific page and click on 'Send Us An E-mail' link from footer"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Handgun);
			Thread.sleep(5000);
			
			home.SendUsAnEmail.click();
			Thread.sleep(3000);
			
			if(home.SendUsAnEmailPopUp.getAttribute("style").contains("display: block"))
			{
				System.out.println("Success!! 'Send Us An Email' pop up displayed");
				Reporter.log("Success!! 'Send Us An Email' pop up displayed"); 
				
				System.out.println("Step 2: On 'Send Us An E-mail' , entering valid data and click on Submit button");
				Reporter.log("Step 2: On 'Send Us An E-mail' , entering valid data and click on Submit button");
				
					home.SendUsAnEmailPopUp_Name.sendKeys("test");
					home.SendUsAnEmailPopUp_Email.sendKeys("test@test.com");
					home.SendUsAnEmailPopUp_Reason_select("Other");
					home.SendUsAnEmailPopUp_Subject.sendKeys("testsubject");
					home.SendUsAnEmailPopUp_Message.sendKeys("testmessage");
					home.SendUsAnEmailPopUp_Submit.click();
					Thread.sleep(10000);
					
					
					String expectedtext ="test, thanks for contacting us. Your message has been sent to Customer Service and you should hear back from them shortly.";
					String actualtext = home.SendUsAnEmailPopUp_ConfirmationMessage_New.getText().trim() ;
		
					if(actualtext.contains(expectedtext) )
					{
						System.out.println("Success!! Confirmation message is correct");
						Reporter.log("Success!! Confirmation message is correct"); 
					}
					else
					{
						System.out.println("Failure !! Confirmation message is incorrect" + "\n" + "Expected Text: "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual Text : " + "\n" +  actualtext);
						Reporter.log("Failure !! Confirmation message is incorrect" + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
			}
			else
			{
				System.out.println("Failure!! 'Send Us An Email' pop up NOT displayed");
				Reporter.log("Failure!! 'Send Us An Email' pop up NOT displayed"); 
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
	 * State > Verify FAQ Page
	 * */ 
	@Test
	private void State_FAQ() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 12 : State > Verify FAQ Page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 12: State > Verify FAQ Page"  + "\n" +
				 	  "====");	
		
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		
		
		System.out.println("Step 1: Navigate to application state specific page and click on 'FAQ' link from header");
		Reporter.log("Step 1: Navigate to application state specific page and click on 'FAQ' link from header"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Handgun_national);
			header.Before_FAQs_New.click();
			Thread.sleep(3000);
			
			if(driver.getCurrentUrl().contains(Constants.ApplicationURL_Handgun.substring(5,Constants.ApplicationURL_Handgun.length() ) + "/national/faq/"))
			{
				System.out.println("Success!! User is navigated to FAQ page");
				Reporter.log("Success!! User is navigated to FAQ page"); 
				
					
					String expectedtext = "Frequently Asked Questions";
					String actualtext = driver.findElement(By.cssSelector("#main h1")).getText().trim() ;
		
					if(actualtext.contains(expectedtext) )
					{
						System.out.println("Success!! FAQ page has correct title");
						Reporter.log("Success!! FAQ page has correct title"); 
					}
					else
					{
						System.out.println("Failure !! FAQ page has incorrect title" + "\n" + "Expected Text: "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual Text : " + "\n" +  actualtext);
						Reporter.log("Failure !! FAQ page has incorrect title" + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
			}
			else
			{
				System.out.println("Failure!!  User is NOT navigated to FAQ page");
				Reporter.log("Failure!!  User is NOT navigated to FAQ page"); 
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
