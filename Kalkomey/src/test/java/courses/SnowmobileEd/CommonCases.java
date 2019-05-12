package courses.SnowmobileEd;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.ContentsPage;
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
	 * Home - Verify UI elements on home page e.g. qa1.snowmobile-ed.com
	 * */ 
	@Test
	private void Home_UIElementVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Home - Verify UI elements on home page e.g. qa1.snowmobile-ed.com"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Home - Verify UI elements on home page e.g. qa1.snowmobile-ed.com"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HomePage home = new HomePage(driver);
		PageHeader header = new PageHeader(driver);

		
		System.out.println("Step 1: Navigated to home page and verifying UI elements");
		Reporter.log("Step 1: Navigated to home page and verifying UI elements"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile);
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile);
				Thread.sleep(4000);
			}
			//Verifying Header 
				
				//verifying application name
				String expectedtext = "Snowmobile-ed.com";
				String actualtext = home.Header_ApplicationName_New.getText();
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
				
				
				//verifying presence of 'Select Your State or Country' dropdown
			
				try
				{	
					home.Header_SelectYourStateCountryDropdown_New.click();
					Thread.sleep(4000);

					System.out.println("Success!! Header has 'Select Your State' dropdown");
					Reporter.log("Success!! Header has 'Select Your State ' dropdown"); 
				}
				catch(Exception e)
				{
					System.out.println("Failure!! Header doesn't have 'Select Your State' dropdown");
					Reporter.log("Failure!! Header doesn't have 'Select Your State' dropdown"); 
					
					AssertFailedCount++;
				}
				
				
				//verifying presence of 'Login' button
				
				try
				{	home.Header_Login_New.click();
					Thread.sleep(4000);

					driver.navigate().back();
					Thread.sleep(4000);

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
				expectedtext = "Official Snowmobile Safety Courses";
				actualtext = driver.findElement(By.cssSelector("div#hero h1")).getText();
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
					
				//verifying presence of 'Choose Your State or Country' dropdown
				//REMOVED
				/*try
				{	home.Billboard_ChooseYourStateCountryDropdown_New.click();
					System.out.println("Success!! Billboard has 'Choose Your State' dropdown");
					Reporter.log("Success!! Billboard has 'Choose Your State' dropdown"); 
				}
				catch(Exception e)
				{
					System.out.println("Failure!! Billboard doesn't have 'Choose Your State' dropdown");
					Reporter.log("Failure!! Billboard doesn't have 'Choose Your State' dropdown"); 
					
					AssertFailedCount++;
				}*/
		
				
		//State section		
				
				//verifying presence of 'States' section
				
				try
				{	//home.States_section.click();
					driver.findElement(By.cssSelector("#states nav")).click();
					Thread.sleep(4000);

					driver.navigate().back();
					Thread.sleep(4000);

					System.out.println("Success!! 'States' section is present on page");
					Reporter.log("Success!! 'States' section is present on page"); 
				}
				catch(Exception e)
				{
					System.out.println("Failure!! 'States' section is NOT present on page");
					Reporter.log("Failure!! 'States' section is NOT present on page"); 
					
					AssertFailedCount++;
				}
		
			
		//Testimonials section		
				
				//verifying presence of 'Testimonials' section
				
				try
				{	home.Testimonials_section_New.click();		
					Thread.sleep(4000);

					System.out.println("Success!! 'Testimonials' section is present on page");
					Reporter.log("Success!! 'Testimonials' section is present on page"); 
				}
				catch(Exception e)
				{
					System.out.println("Failure!! 'Testimonials' section is NOT present on page");
					Reporter.log("Failure!! 'Testimonials' section is NOT present on page"); 
					
					AssertFailedCount++;
				}
		
				
				//verifying presence of 'Get Safe , Get Certified' dropdown
				//REMOVED
				/*try
				{	home.Testimonials_GetSafeGetCertifiedDropdown_New.click();
					System.out.println("Success!! Testimonials section has 'Get Safe , Get Certified' dropdown");
					Reporter.log("Success!! Testimonials section has 'Get Safe , Get Certified' dropdown"); 
				}
				catch(Exception e)
				{
					System.out.println("Failure!! Testimonials section doesn't have 'Get Safe , Get Certified' dropdown");
					Reporter.log("Failure!! Testimonials section doesn't have 'Get Safe , Get Certified' dropdown"); 
					
					AssertFailedCount++;
				}*/
				
				
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
	 * Error pages - Verify 500 error page
	 * */ 
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
			
			
			SeleniumFunc.ToGoToUrl(Constants.Error500Page_Snowmobile);
			
			
			//Verifying Error Message header 
				
				String expectedtext = "Deep drifts ahead�";
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
				
				expectedtext = "It appears there�s a problem with the page you were accessing. We�re dispatching a crack team of engineers to check it out. We suggest going back to the home page or giving our customer service representatives a shout.";
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
				
				expectedtext = Constants.ApplicationURL_Snowmobile.substring(5);
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
				
				Thread.sleep(5000);
			//Verifying "Contact Customer Service" button
				
				error.ContactCustomerServiceButton.click();
				
				expectedtext = Constants.ApplicationURL_Snowmobile.substring(5) + "/site/contact";
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
		
	
		
		System.out.println("Step 1: Navigate to application state specific page");
		Reporter.log("Step 1: Navigate to application state specific page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
				Thread.sleep(4000);
			}
			
			//Verifying Header UI elements
			
				//Verifying application name
				String expectedtext = "Indiana Snowmobile Ed Course";
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
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			
			Thread.sleep(10000);
			
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}	
			
			//Verifying Header UI elements
			
				//Verifying application name
				expectedtext = "Indiana Snowmobile Ed Course";
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

	
	
	/* Test 4: 
	 * Home - Verify presence of state specific links
	 * */ 
	@Test
	private void Home_StateLinks() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify presence of state specific links"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify presence of state specific links"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);

		
		System.out.println("Step 1: Navigated to home page and verifying state specific links");
		Reporter.log("Step 1: Navigated to home page and verifying state specific links"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile);
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile);
				Thread.sleep(4000);
			}
			
			List<WebElement> AllStatesLink = driver.findElements(By.cssSelector("ul[class='states-list'] a"));
			Thread.sleep(4000);

			System.out.println("States links are : " + "\n");
			
			for(WebElement tdElement : AllStatesLink) 
			 {
				System.out.println(tdElement.getAttribute("href"));
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
	 * Home  - Verify presence of  'Get Certified in 3 steps' Section
	 * */ 
	@Test
	private void Home_GetCertifiedIn3StepsSection() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Home  - Verify presence of  'Get Certified in 3 steps' Section"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Home  - Verify presence of  'Get Certified in 3 steps' Section"  + "\n" +
				 	  "====");	
		
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);

		System.out.println("Step 1: Navigate to application state specific page and verifying presence of  'Get Certified in 3 steps' section");
		Reporter.log("Step 1: Navigate to application state specific page and verifying presence of  'Get Certified in 3 steps' section"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
				Thread.sleep(4000);
			}
			
			//Verifying presence of 'Get Certified in 3 steps' section
			
	
				//Verifying presence of  'Get Certified in 3 steps'' header text 
				String expectedtext = "Get Snowmobile certified in 3 steps.";
				String actualtext = driver.findElement(By.cssSelector("#steps h2")).getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! 'Get Certified in 3 steps' header text is correct i.e. " + actualtext);
					Reporter.log("Success!! 'Get Certified in 3 steps' header text is correct i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! 'Get Certified in 3 steps' header text is incorrect  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! 'Get Certified in 3 steps' header text is incorrect  " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
			
				
				//Verifying presence of step 1 ' Study and pass the $29.50 course.' header text 
				expectedtext = "Study and pass the $29.50 course.";
				actualtext = driver.findElement(By.cssSelector("#steps li:nth-of-type(1) p")).getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! step 1 ' Study and pass the $29.50 course.'  header text is correct i.e. " + actualtext);
					Reporter.log("Success!! 'step 1 ' Study and pass the $29.50 course.'  header text is correct i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! step 1 ' Study and pass the $29.50 course.'  header text is incorrect  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! step 1 ' Study and pass the $29.50 course.'  header text is incorrect  " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				
				//Verifying presence of Step 2 - 'Complete the Required In-Person Exam' header text 
				expectedtext = "Print your online course completion document.";
				actualtext = driver.findElement(By.cssSelector("#steps li:nth-of-type(2) p")).getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! Step 2 - 'Print your online course completion document.' header text is correct i.e. " + actualtext);
					Reporter.log("Success!! 'Step 2 - 'Print your online course completion document.' header text is correct i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! Step 2 - 'Print your online course completion document.' header text is incorrect  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Step 2 - 'Print your online course completion document.' header text is incorrect  " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				

				//Verifying presence of Step 3 : 'Get on the trails!' header text 
				expectedtext = "Get ready to go snowmobiling!";
				actualtext = driver.findElement(By.cssSelector("#steps li:nth-of-type(3) p")).getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! Step 3 : 'Get ready to go snowmobiling!' header text is correct i.e. " + actualtext);
					Reporter.log("Success!! 'Step 3 : 'Get ready to go snowmobiling!' header text is correct i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! Step 3 : 'Get ready to go snowmobiling!' header text is incorrect  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Step 3 : 'Get ready to go snowmobiling!' header text is incorrect  " + "\n" + "Expected Text: "  
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

	
	
	/* Test 6: 
	 * Home - Verify functionality of 'Get Safe Get Certified' dropdown
	 * */ 
	//FUNCTIONALITY REMOVED
	/*@Test
	private void Home_Testimonials_GetSafeGetCertified() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Home - Verify functionality of 'Get Safe Get Certified' dropdown"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Home - Verify functionality of 'Get Safe Get Certified' dropdown"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HomePage home = new HomePage(driver);
		
		
		System.out.println("Step 1: Navigated to home page and verifying 'Get Safe Get Certified' dropdown under Testimonials section");
		Reporter.log("Step 1 : Navigated to home page and verifying 'Get Safe Get Certified' dropdown under Testimonials section"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile);
			
		System.out.println("Step 2: Click on 'Get Safe Get Certified' dropdown and clicking on few states links and verifying that user is navigated correctly to state specific pages");
		Reporter.log("Step 2 : Click on 'Get Safe Get Certified' dropdown and clicking on few states links and verifying that user is navigated correctly to state specific pages"); 
			
			for(int i=2;i<5;i++)
			{
				home.Testimonials_GetSafeGetCertifiedDropdown_New.click();
				
				String expectedurl = home.Testmonials_ClickAndGetValueOnStatesLinkUnderGetSafeGetCertifiedDropdown_New(i);
				
				String actualurl = driver.getCurrentUrl();
				
				if(actualurl.equals(expectedurl))
				{
					System.out.println("--- Success !! User is navigated to correct State specific page i.e " + actualurl);
					Reporter.log("--- Success !! User is navigated to correct State specific page i.e " + actualurl); 
						
				}
				else
				{
					System.out.println("Failure !! User is NOT navigated to correct State specific page  " + "\n" + "Expected Text: "  
							+ "\n" + expectedurl + "\n" + 
							 "Actual Text : " + "\n" +  actualurl);
					Reporter.log("Failure !! User is NOT navigated to correct State specific page " + "\n" + "Expected Text: "  
							+ "\n" + expectedurl + "\n" + 
							 "Actual Text : " + "\n" +  actualurl);
					
					AssertFailedCount++;
					
				}
				
				driver.navigate().back();
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
	
	/* Test 7: 
	 * Cheater Page - Verify cheaters page i.e.  qa1.snowmobile-ed.com/answers-to-tests/
	 * */ 
	@Test
	private void CheaterPage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Cheater Page - Verify cheaters page i.e.  qa1.boat-ed.com/answers-to-tests/"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Cheater Page - Verify cheaters page i.e.  qa1.boat-ed.com/answers-to-tests/"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		
		System.out.println("Step 1: Navigated to cheater page and verifying url and text");
		Reporter.log("Step 1: Navigated to cheater page and verifying url and text"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + "/answers-to-tests/");
			Thread.sleep(4000);

		
				
				String expectedurl = Constants.ApplicationURL_Snowmobile.substring(5) + "/answers-to-tests/";
				
				String actualurl = driver.getCurrentUrl();
				
				if(actualurl.contains(expectedurl))
				{
					System.out.println("--- Success !! User is navigated to correct State specific page i.e " + actualurl);
					Reporter.log("--- Success !! User is navigated to correct State specific page i.e " + actualurl); 
						
				}
				else
				{
					System.out.println("--- failure !! User is NOT navigated to correct cheater page i.e " + actualurl);
					Reporter.log("--- Success !! User is navigated to correct cheater page i.e " + actualurl); 
				
					System.out.println("Failure !! User is NOT navigated to correct cheater page  " + "\n" + "Expected Text: "  
							+ "\n" + expectedurl + "\n" + 
							 "Actual Text : " + "\n" +  actualurl);
					Reporter.log("Failure !! User is NOT navigated to correct cheater page " + "\n" + "Expected Text: "  
							+ "\n" + expectedurl + "\n" + 
							 "Actual Text : " + "\n" +  actualurl);
					
					AssertFailedCount++;
					
				}
				
				
				
				expectedurl = "Here are all the answers to the Snowmobile Ed exam. This is the easy way to get your state or provincial snowmobile safety education card, with the correct snowmobile exam answers!";
				
				actualurl = SeleniumFunc.GetElementText("css", "div.myHeader h1");
				
				if(actualurl.equals(expectedurl))
				{
					System.out.println("--- Success !! Header text is correct i.e " + actualurl);
					Reporter.log("--- Success !! Header text is correct i.e " + actualurl); 
						
				}
				else
				{
					
					System.out.println("Failure !! Header text is incorrect " + "\n" + "Expected Text: "  
							+ "\n" + expectedurl + "\n" + 
							 "Actual Text : " + "\n" +  actualurl);
					Reporter.log("Failure !! Header text is incorrect " + "\n" + "Expected Text: "  
							+ "\n" + expectedurl + "\n" + 
							 "Actual Text : " + "\n" +  actualurl);
					
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
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
				Thread.sleep(4000);
			}
			//Verifying Footer UI elements
			
				/*//Verifying The Snowmobile Ed Indiana Course section
				 Not a valid checkpoint after new desing
			
				String expectedtext = "The Snowmobile Ed Indiana Course";
				String actualtext = driver.findElement(By.cssSelector("div#about-program h3")).getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! footer has 'The Snowmobile Ed Indiana Course' section i.e. " + actualtext);
					Reporter.log("Success!! footer has 'The Snowmobile Ed Indiana Course' section i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !!  footer doesn't have 'The Snowmobile Ed Indiana Course' section  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! footer doesn't have 'The Snowmobile Ed Indiana Course' section " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}*/
				
				
				String expectedtext = "Snowmobile-ed.com is produced by Kalkomey Enterprises, Inc. Kalkomey is a private organization dedicated to providing snowmobile education courses and certification and publishing snowmobile safety education materials.";
				String actualtext = SeleniumFunc.GetElementText("css", "#about-program p");
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success!!  'The Snowmobile Ed Indiana Course' section has correct text i.e. " + actualtext);
					Reporter.log("Success!! 'The Snowmobile Ed Indiana Course' section has correct text " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! 'The Snowmobile Ed Indiana Course' section has incorrect text  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! 'The Snowmobile Ed Indiana Course' section has incorrect text " + "\n" + "Expected Text: "  
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
				actualtext = footer.SisterCoursesSectionHeader.getText();
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
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
			driver.navigate().refresh();
			Thread.sleep(4000);

		// Verifying Footer UI elements
			
				//Verifying Social links
				expectedtext = "TwitterYouTubehttps://twitter.com/snowmobile_edhttps://youtube.com/kalkomey";
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
			
			
			SeleniumFunc.ToGoToUrl(Constants.Error404Page_Snowmobile);
			Thread.sleep(4000);
			
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
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("scroll(0, 400);");	
				
				error.BackToHomeButton.click();
				Thread.sleep(4000);

				expectedtext = Constants.ApplicationURL_Snowmobile.substring(5);
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
				Thread.sleep(5000);
			
				jse = (JavascriptExecutor)driver;
				jse.executeScript("scroll(0, 400);");	
		
				//Verifying "Contact Customer Service" button
				
				error.ContactCustomerServiceButton.click();
				Thread.sleep(4000);

				expectedtext = Constants.ApplicationURL_Snowmobile.substring(5) + "/site/contact/";
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
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			Thread.sleep(5000);

			home.SendUsAnEmail.click();
			Thread.sleep(3000);
			
			if(home.SendUsAnEmailPopUp.getAttribute("style").contains("display: block"))
			{
				System.out.println("Success!! 'Send Us An Email' pop up displayed");
				Reporter.log("Success!! 'Send Us An Email' pop up displayed"); 
				
				Thread.sleep(3000);
				
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
	 * Verify that url is https not http
	 * */ 
	@Test
	private void VerifyURL() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 11: Verify that url is https not http"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 11 : Verify that url is https not http"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		
		System.out.println("Step 1: Navigate to Snowmobile-Ed home page");
		Reporter.log("Step 1: Navigate to Snowmobile-Ed home page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile);
			
	
		System.out.println("Step 2 : Verifying that url is https not http" );
		Reporter.log("Step 2 :  Verifying that url is https not http");
		
			String expectedtext = Constants.ApplicationURL_Snowmobile;
			String actualtext = driver.getCurrentUrl();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! url is https i.e. " + actualtext );
				Reporter.log("-- Success !! url is https i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! url is http. Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! url is http . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
				Thread.sleep(4000);
			}
			
			header.Before_FAQs_New.click();
			Thread.sleep(3000);
			
			if(driver.getCurrentUrl().contains(Constants.ApplicationURL_Snowmobile.substring(5,Constants.ApplicationURL_Snowmobile.length() ) + Constants.State_Snowmobile + "/faq"))
			{
				System.out.println("Success!! User is navigated to FAQ page");
				Reporter.log("Success!! User is navigated to FAQ page"); 
				
					
					String expectedtext ="Frequently Asked Questions";
					String actualtext = driver.findElement(By.cssSelector("div.page-header h1")).getText().trim() ;
		
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

	
	/* Test 13: 
	 * State - Home  - Verify UI elements on home page e.g. qa1.Offroad-ed.com/alabama
	 * */ 
	@Test
	private void State_Home_UIElementVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 13 : State - Home  - Verify UI elements on home page e.g. qa1.Offroad-ed.com/alabama"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 13 : State - Home  - Verify UI elements on home page e.g. qa1.Offroad-ed.com/alabama"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HomePage home = new HomePage(driver);
		
		
		System.out.println("Step 1: Navigated to state home page and verifying UI elements");
		Reporter.log("Step 1: Navigated to statehome page and verifying UI elements"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + Constants.State_Snowmobile);
			
			
			//Verifying Header 
				
				//verifying application name
				String expectedtext = "Indiana Snowmobile Ed Course";
				String actualtext = home.Header_ApplicationName_New.getText();
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
				
				
				
			//Verifying Billboard section 
				
				//verifying Billboard text
				expectedtext = "Official Indiana Snowmobile Safety Course Online" + "\n" + " Take this Indiana–approved course to complete your online Snowmobile safety education." ;
				actualtext = home.State_Billboard_text1_New.getText().trim() + "\n " + home.State_Billboard_text2_New.getText().trim();
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
				
					
			//verifying presence of '1-2-3 steps for completeion of course' section
				
				expectedtext = "Get Snowmobile certified in 3 steps." ;
				actualtext = home.Snowmobile_State_CompletionSteps_Section.getText().trim();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! '1-2-3 steps for completeion of course' section has correct text i.e. " + actualtext);
					Reporter.log("Success!!'1-2-3 steps for completeion of course' section has correct text  i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! '1-2-3 steps for completeion of course' section has incorrect text  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! '1-2-3 steps for completeion of course' section has incorrect text " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
		
				
			//verifying presence of 'Comprehensive Course' section
				
				expectedtext = "Comprehensive Instruction in Indiana Snowmobile Safety Education" ;
				actualtext = home.Snowmobile_State_Comprehensive_Section.getText().trim();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! 'Comprehensive Course' section has correct text i.e. " + actualtext);
					Reporter.log("Success!!'Comprehensive Course' section has correct text  i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! 'Comprehensive Course' section has incorrect text  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! 'Comprehensive Course' section has incorrect text " + "\n" + "Expected Text: "  
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
	
}
