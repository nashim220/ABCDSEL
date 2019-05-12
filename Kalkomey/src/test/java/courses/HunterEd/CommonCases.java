package courses.HunterEd;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
import pageFactory.Courses.ProfileNewPage;
import pageFactory.Courses.RegistrationNewPage;
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
	 * Home - Verify UI elements on home page e.g. qa1.boat-ed.com
	 * */ 
	@Test
	private void Home_UIElementVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Home - Verify UI elements on home page e.g. qa1.hunter-ed.com"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Home - Verify UI elements on home page e.g. qa1.hunter-ed.com"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HomePage home = new HomePage(driver);
			
		System.out.println("Step 1: Navigated to home page and verifying UI elements");
		Reporter.log("Step 1: Navigated to home page and verifying UI elements"); 
				
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter);
			Thread.sleep(4000);
				//Verifying Header 
				
				//verifying application name
				String expectedtext = "Hunter-ed.com";
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
				{	home.Header_Login_New.click();
					Thread.sleep(4000);

					driver.navigate().back();
					Thread.sleep(5000);

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
				
				String expectedtexts = "Official Hunter Safety Courses" + "\n" + "for Today";
				String expectedtexte = "s Hunter";
				//expectedtext = "Official Hunter Safety Courses" + "\n" + "for Today’s Hunter";
				/*actualtext = home.Billboard_text.getText();*/
				
				actualtext = driver.findElement(By.cssSelector("#hero h1")).getText();
				System.out.println(actualtext);
	
				
				if(actualtext.startsWith(expectedtexts) && actualtext.endsWith(expectedtexte) )
				{
					System.out.println("Success!! Billboard has correct text i.e. " + actualtext);
					Reporter.log("Success!! Billboard has correct text  i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! Billboard has incorrect text  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtexts+expectedtexte +"\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Billboard has incorrect text  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtexts+expectedtexte +"\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
					
				//verifying presence of 'Choose Your State or Country' dropdown
				
				try
				{	home.Billboard_ChooseYourStateCountryDropdown_Hunter.click();
					Thread.sleep(4000);

					System.out.println("Success!! Billboard has 'Choose Your State' dropdown");
					Reporter.log("Success!! Billboard has 'Choose Your State' dropdown"); 
				}
				catch(Exception e)
				{
					System.out.println("Failure!! Billboard doesn't have 'Choose Your State' dropdown");
					Reporter.log("Failure!! Billboard doesn't have 'Choose Your State' dropdown"); 
					
					AssertFailedCount++;
				}
		
				
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
		
			
	/*			
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
				
				try
				{	home.Testimonials_GetSafeGetCertifiedDropdown_New.click();
					Thread.sleep(4000);

					System.out.println("Success!! Testimonials section has 'Get Safe , Get Certified' dropdown");
					Reporter.log("Success!! Testimonials section has 'Get Safe , Get Certified' dropdown"); 
				}
				catch(Exception e)
				{
					System.out.println("Failure!! Testimonials section doesn't have 'Get Safe , Get Certified' dropdown");
					Reporter.log("Failure!! Testimonials section doesn't have 'Get Safe , Get Certified' dropdown"); 
					
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
			
			
			SeleniumFunc.ToGoToUrl(Constants.Error500Page_Hunter);
			
			
			//Verifying Error Message header 
				
				String expectedtext = "The one that got away";
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
				
				expectedtext = "It appears thereï¿½s a problem with the page you were accessing. Weï¿½re dispatching a crack team of engineers to check it out. We suggest going back to the home page or giving our customer service representatives a shout.";
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
				
				expectedtext = Constants.ApplicationURL_Hunter.substring(5);
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
				Thread.sleep(5000);
				error.ContactCustomerServiceButton.click();
				
				expectedtext = Constants.ApplicationURL_Hunter.substring(5) + "/site/contact";
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
	 
		
	}*/


	
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
		RegistrationNewPage register = new RegistrationNewPage(driver);
		PageHeader header = new PageHeader(driver);
		
	
		
		System.out.println("Step 1: Navigate to application state specific page");
		Reporter.log("Step 1: Navigate to application state specific page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
			//Verifying Header UI elements
			
				//Verifying application name
				String expectedtext = "Arkansas Hunter Ed Course";
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
			
			header.Register_New.click();
			
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();	
				Thread.sleep(5000);
			}
			
			register.RegisterAsNewUser(username,  "January", "2", "1990", emailaddress, "clarion@123", "clarion@123");
			
			Thread.sleep(10000);
			
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
			//Verifying Header UI elements
			
				//Verifying application name
				expectedtext = "Arkansas Hunter Ed Course";
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
		
		
		System.out.println("Step 1: Navigated to home page and verifying state specific links");
		Reporter.log("Step 1: Navigated to home page and verifying state specific links"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter);
			
			/*WebElement states_section = driver.findElement(By.cssSelector("ul[class='states-list clearfix'] a"));
			 List<WebElement> AllStatesLink = states_section.findElements(By.tagName("a")); */
			
			List<WebElement> AllStatesLink = driver.findElements(By.cssSelector("ul[class='states-list'] a"));
			
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
	
	
	/*
	 Test 5: 
	 * Home  - Verify presence of  'Get Certified in 3 steps' Section
	 *  
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
		
		System.out.println("Step 1: Navigate to application state specific page and verifying presence of  'Get Certified in 3 steps' section");
		Reporter.log("Step 1: Navigate to application state specific page and verifying presence of  'Get Certified in 3 steps' section"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			
			
			//Verifying presence of 'Get Certified in 3 steps' section
			
	
				//Verifying presence of  'Get Certified in 3 steps'' header text 
				String expectedtext = "Get Certified in 3 steps.";
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
			
				
				//Verifying presence of step 1 'Study for Free' header text 
				expectedtext = "Study for Free";
				actualtext = driver.findElement(By.cssSelector("#steps li:nth-of-type(1) h3")).getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! step 1 'Study for Free'  header text is correct i.e. " + actualtext);
					Reporter.log("Success!! 'step 1 'Study for Free'  header text is correct i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! step 1 'Study for Free'  header text is incorrect  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! step 1 'Study for Free'  header text is incorrect  " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				
				//Verifying presence of Step 2 - 'Complete the Required In-Person Exam' header text 
				expectedtext = "Complete the Required In-Person Exam";
				actualtext = driver.findElement(By.cssSelector("#steps li:nth-of-type(2) h3")).getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! Step 2 - 'Complete the Required In-Person Exam' header text is correct i.e. " + actualtext);
					Reporter.log("Success!! 'Step 2 - 'Complete the Required In-Person Exam' header text is correct i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! Step 2 - 'Complete the Required In-Person Exam' header text is incorrect  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Step 2 - 'Complete the Required In-Person Exam' header text is incorrect  " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				

				//Verifying presence of Step 3 : 'Get Your Certificate' header text 
				expectedtext = "Get Your Certificate";
				actualtext = driver.findElement(By.cssSelector("#steps li:nth-of-type(3) h3")).getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! Step 3 : 'Get Your Certificate' header text is correct i.e. " + actualtext);
					Reporter.log("Success!! 'Step 3 : 'Get Your Certificate' header text is correct i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! Step 3 : 'Get Your Certificate' header text is incorrect  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Step 3 : 'Get Your Certificate' header text is incorrect  " + "\n" + "Expected Text: "  
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
	
	/*
	 Test 6: 
	 * Home - Verify functionality of 'Get Safe Get Certified' dropdown
	 *  
	@Test
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
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter);
			
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 1200);");	
			
		System.out.println("Step 2: Click on 'Get Safe Get Certified' dropdown and clicking on few states links and verifying that user is navigated correctly to state specific pages");
		Reporter.log("Step 2 : Click on 'Get Safe Get Certified' dropdown and clicking on few states links and verifying that user is navigated correctly to state specific pages"); 
			
			for(int i=2;i<6;i++)
			{
			
				jse = (JavascriptExecutor)driver;
				jse.executeScript("scroll(0, 1200);");	
				Thread.sleep(4000);
				
				home.Testimonials_GetSafeGetCertifiedDropdown.click();
				
				String expectedurl = home.Testmonials_ClickAndGetValueOnStatesLinkUnderGetSafeGetCertifiedDropdown(i);
				
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
	 * Cheater Page - Verify cheaters page i.e.  qa1.boat-ed.com/answers-to-tests/
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
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/answers-to-tests/");
			Thread.sleep(4000);

		
				
				String expectedurl = Constants.ApplicationURL_Hunter.substring(5) + "/answers-to-tests/";
				
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
				
				
				
				expectedurl = "Here are all the answers to the Hunter Ed exam. This is the easy way to get your state hunting card, with the correct hunting exam answers!";
				
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
		RegistrationNewPage register = new RegistrationNewPage(driver);
		PageFooter footer = new PageFooter(driver);
		PageHeader header = new PageHeader(driver);
		
	
		
		System.out.println("Step 1: Navigate to application state specific page");
		Reporter.log("Step 1: Navigate to application state specific page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter_Timed);
				Thread.sleep(4000);
			}
			//Verifying Footer UI elements
			
				/*//Verifying About Hunter Ed section
				 Not a valid checkpoint after new desing
			
				String expectedtext = "About Hunter Ed";
				String actualtext = driver.findElement(By.cssSelector("div#about-program h3")).getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! footer has 'About Hunter Ed' section i.e. " + actualtext);
					Reporter.log("Success!! footer has 'About Hunter Ed' section i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !!  footer doesn't have 'About Hunter Ed' section  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! footer doesn't have 'About Hunter Ed' section " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}*/
				
				String expectedtextS = "We provide print and Internet hunting safety courses for more than 45 states. Hunter Ed is committed to hunter education safety. We work with state agencies to produce a hunter safety education course that";
				String expectedtextE = "s accurate, interesting, and easy to understand.";
				//String expectedtext = "We provide print and Internet hunting safety courses for more than 45 states. Hunter Ed is committed to hunter education safety. We work with state agencies to produce a hunter safety education course that’s accurate, interesting, and easy to understand.";
				String actualtext = driver.findElement(By.cssSelector("div#about-program p")).getText().trim();
				System.out.println(actualtext);
	
				if(actualtext.startsWith(expectedtextS) && actualtext.endsWith(expectedtextE))
				{
					System.out.println("Success!!  'About Hunter Ed' section has correct text i.e. " + actualtext);
					Reporter.log("Success!! 'About Hunter Ed' section has correct text " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! 'About Hunter Ed' section has incorrect text  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtextS + " + expectedtextE"  +"\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! 'About Hunter Ed' section has incorrect text  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtextS + " + expectedtextE"  +"\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying presence of 'Follow us here' section
				
				String expectedtext = "Follow Us";
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
			
			header.Register_New.click();
			
			Thread.sleep(10000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();	
				Thread.sleep(4000);
			}
			
			register.RegisterAsNewUser(username,  "January", "2", "1990", emailaddress, "clarion@123", "clarion@123");
			Thread.sleep(4000);
			
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
				expectedtext = "TwitterFacebookPinterestYouTubeGoogle +Hunter Ed bloghttps://twitter.com/hunter_edhttps://www.facebook.com/hunterEdcomhttps://pinterest.com/hunteredcomhttps://youtube.com/kalkomeyhttps://plus.google.com/104347826810491487931/postshttp://www.hunter-ed.com/blog";
				actualtext = footer.GetAllSocialLinksName() + footer.GetAllSocialLinks();
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
				actualtext = footer.GetAllSisterLinksName() + footer.GetAllSisterLinks();
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
			
			
			SeleniumFunc.ToGoToUrl(Constants.Error404Page_Hunter);
			Thread.sleep(4000);

			
			//Verifying Error Message header 
				
				String expectedtext = "The one that got away";
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
				jse.executeScript("scroll(0, 500);");	
				
				error.BackToHomeButton.click();
				Thread.sleep(4000);

				expectedtext = Constants.ApplicationURL_Hunter.substring(5);
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
				Thread.sleep(4000);
				
			//Verifying "Contact Customer Service" button
				Thread.sleep(4000);
				jse = (JavascriptExecutor)driver;
				jse.executeScript("scroll(0, 500);");	
				
				error.ContactCustomerServiceButton.click();
				Thread.sleep(4000);

				expectedtext = Constants.ApplicationURL_Hunter.substring(5) + "/site/contact/";
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
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			Thread.sleep(3000);

			home.SendUsAnEmail_Footer.click();
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
		
		
		System.out.println("Step 1: Navigate to application state specific page and click on 'FAQ' link from _ui");
		Reporter.log("Step 1: Navigate to application state specific page and click on 'FAQ' link from header"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
				Thread.sleep(4000);
			}
			
			header.Before_FAQs_New.click();
			Thread.sleep(4000);
			
			if(driver.getCurrentUrl().contains(Constants.ApplicationURL_Hunter.substring(5,Constants.ApplicationURL_Hunter.length() ) + Constants.State_Hunter + "/faq"))
			{
				System.out.println("Success!! User is navigated to FAQ page");
				Reporter.log("Success!! User is navigated to FAQ page"); 
				
					
					String expectedtext = "Frequently Asked Questions";
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
	 * State - Home  - Verify UI elements on home page e.g. qa1.boat-ed.com/alabama
	 * */ 
	@Test
	private void State_Home_UIElementVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 13 : State - Home  - Verify UI elements on home page e.g. qa1.hunter-ed.com/alabama"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 13 : State - Home  - Verify UI elements on home page e.g. qa1.hunter-ed.com/alabama"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HomePage home = new HomePage(driver);
		
		
		System.out.println("Step 1: Navigated to state home page and verifying UI elements");
		Reporter.log("Step 1: Navigated to statehome page and verifying UI elements"); 
			
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			
			
			//Verifying Header 
				
				//verifying application name
				String expectedtext = "Texas Hunter Ed Course";
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
				
				String expectedtext1 = "Official Texas Hunter Safety Course Online" + "\n" + " Take this Texas";
				String expectedtext2 = "approved course to complete your online hunter safety education.";
				//expectedtext = "Official Texas Hunter Safety Course Online" + "\n" + " Take this Texas–approved course to complete your online hunter safety education." ;
				actualtext = home.State_Billboard_text1_New.getText().trim() + "\n " + home.State_Billboard_text2_New.getText().trim();
	
				if(actualtext.startsWith(expectedtext1) && actualtext.endsWith(expectedtext2))
				{
					System.out.println("Success!! Billboard has correct text i.e. " + actualtext);
					Reporter.log("Success!! Billboard has correct text  i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! Billboard has incorrect text  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext1 +expectedtext2 + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Billboard has incorrect text  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext1 +expectedtext2 + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
					
			//verifying presence of 'Comprehensive Course' section
				
				expectedtext = "Comprehensive Instruction in Texas Hunting Safety Education" ;
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

	
	/* Test 1: 
	 * Trust pilot reviews  displayed on root and state landing pages
	 * */ 
	@Test
	private void TrushPilotReview() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Trust pilot reviews  displayed on root and state landing pages"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Trust pilot reviews  displayed on root and state landing pages"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			
		System.out.println("Step 1: Navigated to home page and verifying UI elements");
		Reporter.log("Step 1: Navigated to home page and verifying UI elements"); 
				
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter);
			Thread.sleep(4000);
			
		System.out.println("Step 2: Verify Trust Pilot section is present at course landing page");
		Reporter.log("Step 2: Verify Trust Pilot section is present at course landing page"); 
				
			if(SeleniumFunc.IsElementPresent("css", ".testimonials .text-center"))
			{
				System.out.println("Success!! Trust Pilot section is present");
				Reporter.log("Success!! Trust Pilot section is present"); 
				
				//verifying Billboard text
				String expectedtext = "Get Safe. Get Certified.";
				String actualtext = SeleniumFunc.GetElementText("css", ".testimonials .text-center");
				
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success!! Trust Pilot section has correct text. i.e." + actualtext);
					Reporter.log("Success!! Trust Pilot section has correct text. i.e." + actualtext); 
				}
				else
				{
					System.out.println("Failure !! Trust Pilot section has incorrect text. i.e. " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										"Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Trust Pilot section has incorrect text. i.e. " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							"Actual Text : " + "\n" +  actualtext);
									AssertFailedCount++;
				}
			}
			else
			{
				System.out.println("Failure !! Trust Pilot section is NOT present at course landing page");
				Reporter.log("Failure !! Trust Pilot section is NOT present at course landing page");
							AssertFailedCount++;
			}
				
				
				
				
		System.out.println("Step 3: Verify Trust Pilot section is present at state landing page");
		Reporter.log("Step 3: Verify Trust Pilot section is present at state landing page"); 
						
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + Constants.State_Hunter);
			Thread.sleep(4000);
			
			if(SeleniumFunc.IsElementPresent("css", "#marketing-trustpilot p"))
			{
				System.out.println("Success!! Trust Pilot section is present");
				Reporter.log("Success!! Trust Pilot section is present"); 
					
					//verifying Billboard text
					String expectedtext = "Get Safe. Get Certified.";
					String actualtext = SeleniumFunc.GetElementText("css", "#marketing-trustpilot p");
							
					if(actualtext.contains(expectedtext))
					{
						System.out.println("Success!! Trust Pilot section has correct text. i.e." + actualtext);
						Reporter.log("Success!! Trust Pilot section has correct text. i.e." + actualtext); 
					}
					else
					{
						System.out.println("Failure !! Trust Pilot section has incorrect text. i.e. " + "\n" + "Expected Text: "  
											+ "\n" + expectedtext + "\n" + 
											"Actual Text : " + "\n" +  actualtext);
						Reporter.log("Failure !! Trust Pilot section has incorrect text. i.e. " + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								"Actual Text : " + "\n" +  actualtext);
			
						AssertFailedCount++;
					}
			}
			else
			{
				System.out.println("Failure !! Trust Pilot section is NOT present at state landing page");
				Reporter.log("Failure !! Trust Pilot section is NOT present at state landing page");
			
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
	
	
	
	
	/* Test 1: 
	 * Hunter > Oregon  is unavailable for international students
	*/ 
	@Test
	private void OROnlyForUS() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Hunter > Oregon  is unavailable for international students "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Hunter > Oregon  is unavailable for international students "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/oregon");
			PageHeader header = new PageHeader(driver);

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/oregon");
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			Thread.sleep(4000);
			
		System.out.println("Step 1: Verify message is present on registration that course is only for US resident");
		Reporter.log("Step 1: Verify message is present on registration that course is only for US resident"); 		
		
			if(SeleniumFunc.IsElementPresent("css", ".checkbox label"))
			{
				String ActualText =SeleniumFunc.GetElementText("css", ".checkbox label");
				String ExpectedText ="I confirm that I am a resident of the United States of America." ;
				
				if(ActualText.equals(ExpectedText))
				{
					System.out.println("Success !! Message is present. i.e." + ActualText); 
					Reporter.log("Success !! Message is present. i.e." + ActualText); 
				}
				else
				{
					System.out.println("Failure !! Message is missing / incorrect. Expected is : " + ExpectedText+  "Actual is :" + ActualText);
					Reporter.log("Failure !! Message is missing / incorrect. Expected is : " + ExpectedText+  "Actual is :" + ActualText);
					AssertFailedCount++;
				}
			}
				
	
			register.ClickOnStateResidencyCheckbox();
			register.RegisterAsNewUser(username,  "January", "2", "1990", emailaddress, "clarion@123", "clarion@123");
			Thread.sleep(8000);
			
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
			header.Contents.click();
			Thread.sleep(3000);
			
			header.Profile.click();
			Thread.sleep(5000);
			
		System.out.println("Step 1: Verify country field is disabled on profile page");
		Reporter.log("Step 1: Verify country field is disabled on profile page"); 			
			
			ProfileNewPage profile = new ProfileNewPage(driver);
			
			if(!profile.MailingAddressCountry.isEnabled())
			{
				System.out.println("Success !! Oregon  is available only for US students"); 
				Reporter.log("Success !! Oregon  is available only for US students"); 
			}
			else
			{
				System.out.println("Failure !! Oregon  is available only for international students");
				Reporter.log("Failure !! Oregon  is available only for international students");
				
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
			
	
	
	/* Test 1: 
	 * Verify Hunter > IA > Open to residents and non-residents
	*/ 
	@Test
	private void Hunter_Iowa_OpenNonResidents() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify Hunter > IA > Open to residents and non-residents "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify Hunter > IA > Open to residents and non-residents "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
		
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/iowa");
			PageHeader header = new PageHeader(driver);

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/iowa");
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			
		System.out.println("Step 1: Verify no residency requirement on registration page");
		Reporter.log("Step 1: Verify no residency requirement on registration page"); 		
		
			if(!SeleniumFunc.IsElementPresent(register.StateResidencyCheckbox))
			{
				System.out.println("Success !! No Residency requirement of registration page"); 
				Reporter.log("Success !! No Residency requirement of registration page"); 
			}
			else
			{
				System.out.println("Failure !! Residenct requirement check is present on registration page");
				Reporter.log("Failure !! Residenct requirement check is present on registration page");
			
				AssertFailedCount++;
			}
						
	
			register.RegisterAsNewUser(username,  "January", "2", "1990", emailaddress, "clarion@123", "clarion@123");
			Thread.sleep(6000);
			
			register.SelectGender("Male");
			register.SelectEtinicity("Asian");
			register.SelectHairColor("Black");
			register.SelectEyeColor("Brown");
			
			register.CreateAccount.sendKeys(Keys.RETURN);
			Thread.sleep(6000);

			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
			header.Contents.click();
			Thread.sleep(3000);
			
			header.Profile.click();
			Thread.sleep(5000);
			
		System.out.println("Step 1: Verify country field is disabled on profile page");
		Reporter.log("Step 1: Verify country field is disabled on profile page"); 			
			
			ProfileNewPage profile = new ProfileNewPage(driver);
			
			if(profile.MailingAddressCountry.isEnabled())
			{
				System.out.println("Success !! Iowa  is available only for resident and non-resident students"); 
				Reporter.log("Success !! Iowa  is available only for resident and non-resident students"); 
			}
			else
			{
				System.out.println("Failure !! Iowa  is having residency requirements");
				Reporter.log("Failure !! Iowa  is having residency requirements");
				
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
	
	
	/* Test 1: 
	 * Verify Hunter > CO > Verify after clicking on testout register link it should redirect to registration page
	 * and should not redirect to my active session page.
	*/ 
	@Test
	private void Testout_Links() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify Hunter > CO > Verify after clicking on testout register link it should redirect to registration page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify Hunter > CO > Verify after clicking on testout register link it should redirect to registration page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationNewPage register = new RegistrationNewPage(driver);
	
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "contents" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "contents" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/colorado");
			PageHeader header = new PageHeader(driver);

			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/colorado");
				Thread.sleep(4000);
			}
					
			header.Register_New.click();
				
			Thread.sleep(8000);
					
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{					
				register.ClickOnIUnderstandButton();			
			}
			
			register.RegisterAsNewUser(username,  "January", "2", "1990", emailaddress, "clarion@123", "clarion@123");
			Thread.sleep(6000);
			
			System.out.println("Step 2: Navigate to Colorado Testout Link");
			Reporter.log("Step 2: Navigate to Colorado Testout Link");
			
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
			header.Contents.click();
			Thread.sleep(3000);
			
			SeleniumFunc.ToGoToUrl(Constants.Testout_Hunter);
			Thread.sleep(2000);
			
			String expectedtext = "Colorado Test-Out Exam";
			String actualtext = SeleniumFunc.GetElementText("css", "#placeholder-course > div > div > div > div > div.span9 > h1");
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Navigated to colorado Testout Link"); 
				Reporter.log("Success !! Navigated to colorado Testout Link"); 
			}
			else
			{
				System.out.println("Failure !! Navigated to incorrect page" + "Actual Text : "+ actualtext +" Expected Text:"+expectedtext);
				Reporter.log("Failure !! Navigated to incorrect page" + "Actual Text : "+ actualtext +" Expected Text:"+expectedtext);
				
				AssertFailedCount++;
			}
			
			System.out.println("Step 3: Navigate to Testout Register Link");
			Reporter.log("Step 3: Navigate to Testout Register Link");
			
			String url ="https://qa1.hunter-ed.com/register/create/";
			SeleniumFunc.ToGoToUrl(url);
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
			//SeleniumFunc.ClickOnElement("css", "#content-promo > div > div:nth-child(1) > div > a");
			Thread.sleep(2000);
			String expectedtext1="You cannot register for another course while logged into a course. To register, please log out first";
			String actualtext1 = SeleniumFunc.GetElementText("css", "#main > div > div.alert-banner > div");
			
			if(actualtext1.endsWith(expectedtext1))
			{
				System.out.println("Success !! Displayed text: Logout first!"); 
				Reporter.log("Success !! Displayed text: Logout first"); 
			}
			else
			{
				System.out.println("Failure !! Navigated to incorrect page" + "Actual Text : "+ actualtext1 +" Expected Text:"+expectedtext1);
				Reporter.log("Failure !! Navigated to incorrect page" + "Actual Text : "+ actualtext1 +" Expected Text:"+expectedtext1);
				
				AssertFailedCount++;
			}
			
			System.out.println("Step 4: Click on Logout and navigate to Testout Link");
			Reporter.log("Step 4: Click on Logout and navigate to Testout Link");
			
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.Testout_Hunter);
				Thread.sleep(4000);
			}
			
			System.out.println("Sucess : Clicked on Logout and navigate to Testout Link");
			Reporter.log("Sucess : Clicked on Logout and navigate to Testout Link");
			
			System.out.println("Step 6: Click on Register button again");
			Reporter.log("Step 6: Click on Register button again");
			
			register.Click_Registered_TestOut();
			Thread.sleep(2000);
			
			//SeleniumFunc.ClickOnElement("css", "#content-promo > div > div:nth-child(1) > div > a");

			String expectedurl = "https://qa1.hunter-ed.com/register/create/";
			String actualurl = driver.getCurrentUrl();
			if(actualurl.contains(expectedurl))
			{
				System.out.println("Success : Navigated to register link");
				Reporter.log("Success : Navigated to register link");
			}
			
			else
			{
				System.out.println("Failure !! Navigated to incorrect page" + "Actual Url : "+ actualurl +" Expected Url:"+expectedurl);
				Reporter.log("Failure !! Navigated to incorrect page" + "Actual Text : "+ actualurl +" Expected Text:"+expectedurl);
				
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
