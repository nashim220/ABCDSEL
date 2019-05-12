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
import pageFactory.CM.GlobalFooter;
import pageFactory.CM.GlobalHeader;
import pageFactory.CM.ILMCHomePage;
import pageFactory.CM.ILMCSearchPage;
import pageFactory.CM.LoginPage;
import pageFactory.CM.ProfilePage;
import pageFactory.CM.SearchCertificationPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class CommonCasesTest 
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
	 * KPS - Verify elements on header
	*/ 
	@Test
	private void KPSHeaderElement() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : KPS - Verify elements on header"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : KPS - Verify elements on header"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader header = new GlobalHeader(driver);			
		GlobalHeader gheader = new GlobalHeader(driver);
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			
		
		System.out.println("Step 3 : Verifying whether user is logged successfully or not");
		Reporter.log("Step 3 : Verifying whether user is logged successfully or not"); 
			
				
			
			//String ActualUserName=SeleniumFunc.GetElementText("css", "ul[class='nav pull-right'] li:nth-of-type(2) a.dropdown-toggle").trim();
			
		    String actualtext= gheader.Success_AlertText.getText().trim();
			String expectedtext= "Signed in!";
		
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Login  message is displayed i.e. " + actualtext);
				Reporter.log("Success !! Login  message is displayed i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! incorrect  message is displayed" + "\n" + "Expected : "  + "\n" + expectedtext + "\n" +  "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  incorrect  message is displayed" + "\n" + "Expected : "  + "\n" + expectedtext + "\n" +  "Actual : " + "\n" +  actualtext);
	
				AssertFailedCount++;
			}
			
			
			//Verify Header Element
			
			//KPS logo
			if(SeleniumFunc.IsElementPresent(header.Logo))
			{
				Thread.sleep(1000);
				System.out.println("Success !! KPS logo is present");
				Reporter.log("Success !! KPS logo is present"); 
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! KPS logo is NOT present ");
				Reporter.log("Failure !! KPS logo is NOT present "); 
				AssertFailedCount++;
			}
		
			
			//Admin Menu
			if(SeleniumFunc.IsElementPresent(header.AdminDropdown))
			{
				Thread.sleep(2000);	
				System.out.println("Success !! Admin Menu is present");
				Reporter.log("Success !! Admin Menu is present"); 
			}
			else
			{
				System.out.println("Failure !! Admin Menu is NOT present ");
				Reporter.log("Failure !! Admin Menu is NOT present "); 
				AssertFailedCount++;
			}
			
			//User Menu
			if(SeleniumFunc.IsElementPresent(header.UsernameDropdown))
			{
				Thread.sleep(1000);
				System.out.println("Success !! User Menu is present");
				Reporter.log("Success !! User Menu is present"); 
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! User Menu is NOT present ");
				Reporter.log("Failure !! User Menu is NOT present "); 
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
	 * KPS - Verify Admin menu
	*/ 
	@Test
	private void AdminMenu() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : KPS - Verify Admin menu"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : KPS - Verify Admin menu"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader header = new GlobalHeader(driver);			
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(3000);
		
			//Verify Admin Menu
			header.AdminDropdown.click();
			Thread.sleep(2000);
			
			//Manage Collection Option
			if(SeleniumFunc.IsElementPresent(header.Admin_ManageCollection))
			{
				Thread.sleep(2000);	
				System.out.println("Success !! Manage Collection Option is present");
				Reporter.log("Success !! Manage Collection Option is present"); 
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! Manage Collection Option is NOT present ");
				Reporter.log("Failure !! Manage Collection Option is NOT present "); 
				AssertFailedCount++;
			}
		
			//Manage Collection Origin Option
			if(SeleniumFunc.IsElementPresent(header.Admin_ManageCollectionOrigin))
			{
				
				System.out.println("Success !! Manage Collection Origin Option is present");
				Reporter.log("Success !! Manage Collection Origin Option is present"); 
			}
			else
			{
				System.out.println("Failure !! Manage Collection Origin Option is NOT present ");
				Reporter.log("Failure !! Manage Collection Origin Option is NOT present "); 
				AssertFailedCount++;
			}
			
			
			//Manage Users Option
			if(SeleniumFunc.IsElementPresent(header.Admin_ManageUsers))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Manage Users Option is present");
				Reporter.log("Success !! Manage Users Option is present"); 
			}
			else
			{
				System.out.println("Failure !! Manage Users Option is NOT present ");
				Reporter.log("Failure !! Manage Users Option is NOT present "); 
				AssertFailedCount++;
			}
			
			
			//Certification Activity Option
			if(SeleniumFunc.IsElementPresent(header.Admin_CollectionActivity))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Certification Activity Option is present");
				Reporter.log("Success !! Certification Activity Option is present"); 
			}
			else
			{
				System.out.println("Failure !! Certification Activity Option is NOT present ");
				Reporter.log("Failure !! Certification Activity Option is NOT present "); 
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
	 * KPS - Verify Users menu
	*/ 
	@Test
	private void UsersMenu() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : KPS - Verify Users menu"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : KPS - Verify Users menu"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader header = new GlobalHeader(driver);			
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(1000);
		
			//Verify Users Menu
			header.UsernameDropdown.click();
			Thread.sleep(3000);
			
			//My Profile Option
			if(SeleniumFunc.IsElementPresent(header.Username_MyProfile))
			{
				Thread.sleep(2000);	
				System.out.println("Success !! My Profile Option is present");
				Reporter.log("Success !! My Profile Option is present"); 
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! My Profile Option is NOT present ");
				Reporter.log("Failure !! My Profile Option is NOT present "); 
				AssertFailedCount++;
			}
		
		
			//Logout Option
			if(SeleniumFunc.IsElementPresent(header.Logout))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Logout Option is present");
				Reporter.log("Success !! Logout Option is present"); 
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! Logout Option is NOT present ");
				Reporter.log("Failure !! Logout Option is NOT present "); 
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
	 * Verify user can return to home page from Profile page
	*/ 
	@Test
	private void HomePageNavigation() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify user can return to home page from Profile page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify user can return to home page from Profile page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader header = new GlobalHeader(driver);			
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);	
		
			//Go to Profile
			header.UsernameDropdown.click();
			Thread.sleep(1000);
			header.Username_MyProfile.click();
			Thread.sleep(4000);
			
			//Verify control is on profile page
			
			String ActualURL =  driver.getCurrentUrl();
			String ExpectedURL = Constants.ApplicationURL_CM + "/users/67";
			Thread.sleep(2000);	
			if(ActualURL.equals(ExpectedURL))
			{
				Thread.sleep(1000);
				System.out.println("Success !! User is redirected to Profile page");
				Reporter.log("Success !! User is redirected to Profile page"); 
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! User is NOT redirected to Profile page");
				Reporter.log("Failure !! User is NOT redirected to Profile page"); 
				AssertFailedCount++;
			}	
			Thread.sleep(1000);
			//Click on logo
			header.Logo.click();
			Thread.sleep(1000);
			//Verify control is redirected to Home Page
			
			ActualURL =  driver.getCurrentUrl();
			ExpectedURL = "https://kps.staging.kalkomey.com/";
			
			if(ActualURL.equals(ExpectedURL))
			{
				Thread.sleep(1000);
				System.out.println("Success !! User is redirected to Home  page");
				Reporter.log("Success !! User is redirected to Home page"); 
			}
			else
			{
				System.out.println("Failure !! User is NOT redirected to Home page");
				Reporter.log("Failure !! User is NOT redirected to Home page"); 
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
	 * Verify elements under footer
	*/ 
	@Test
	private void FooterElements() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify elements under footer"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify elements under footer"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalFooter footer= new GlobalFooter(driver);			
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
		
			//Verify Footer Elements
	
			//About Us Section
			
			if(SeleniumFunc.IsElementPresent(footer.AboutUs))
			{
				Thread.sleep(2000);
				System.out.println("Success !! About Us Section is present");
				Reporter.log("Success !! About Us Section is present"); 
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! About Us Section is NOT present ");
				Reporter.log("Failure !! About Us Section is NOT present "); 
				AssertFailedCount++;
			}
			
			//Customer Support Section
			
			if(SeleniumFunc.IsElementPresent(footer.CustomerSupport))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Customer Support Section is present");
				Reporter.log("Success !! Customer Support Section is present"); 
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! Customer Support Section is NOT present ");
				Reporter.log("Failure !! Customer Support Section is NOT present "); 
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
	 * Verify 'About Us' section
	*/ 
	@Test
	private void AboutUsSection() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify 'About Us' section"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Verify 'About Us' section"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalFooter footer= new GlobalFooter(driver);			
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
		
			//Verify Footer Elements
	
			//About Us Section
			
			if(SeleniumFunc.IsElementPresent(footer.AboutUs))
			{
				Thread.sleep(1000);
				System.out.println("Success !! About Us Section is present");
				Reporter.log("Success !! About Us Section is present"); 
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! About Us Section is NOT present ");
				Reporter.log("Failure !! About Us Section is NOT present "); 
				AssertFailedCount++;
			}
			Thread.sleep(1000);
			//Verify About Us Details
			String ExpectedText = "About Us"+ "\n"+
								"Kalkomey is the official provider of recreational safety education materials for North America. View press releases."+ "\n" + 
								"Kalkomey Product Suite is produced by Kalkomey Enterprises, LLC"+ "\n" +
								"14086 Proton Road"+ "\n"+
								"Dallas, Texas 75244"+ "\n"+
								"800-830-2268"+ "\n"+
								"info@kalkomey.com";
			String ActualText = footer.AboutUsDetails.getText().trim();
			Thread.sleep(2000);		
					
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(2000);	
				System.out.println("Success !! About Us Details are proper. i.e. " +ExpectedText);
				Reporter.log("Success !! About Us Details are proper. i.e. " +ExpectedText);
			
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! About Us Details are NOT proper. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! About Us Details are NOT proper. Expected :" + ExpectedText + "\n"+
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
	 * Verify 'Customer Support' section
	*/ 
	@Test
	private void CustomerSupportSection() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Verify 'Customer Support' section"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Verify 'Customer Support' section"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalFooter footer= new GlobalFooter(driver);			
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
		
			//Verify Footer Elements
	
			//Customer Support Section
			
			if(SeleniumFunc.IsElementPresent(footer.CustomerSupport))
			{
				Thread.sleep(2000);	
				System.out.println("Success !! Customer Support Section is present");
				Reporter.log("Success !! Customer Support Section is present"); 
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! Customer Support Section is NOT present ");
				Reporter.log("Failure !! Customer Support Section is NOT present "); 
				AssertFailedCount++;
			}
			
			//Verify Customer Support Details
			String ExpectedText = "Customer Support"+ "\n" +
														"We provide support Monday through Friday from 8:30 a.m. to midnight CST and Saturday and Sunday from 8:30 a.m. to 4:30 p.m. CST."+ "\n" + 
														"phone: 800-830-2268"+ "\n" +
														"e-mail: KPS Customer Support";
			String ActualText = footer.CustomerSupportDetails.getText().trim();
			Thread.sleep(1000);		
					
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Customer Support Details are proper. i.e. " +ExpectedText);
				Reporter.log("Success !! Customer Support Details are proper. i.e. " +ExpectedText);
			
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! Customer Support Details are NOT proper. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Customer Support Details are NOT proper. Expected :" + ExpectedText + "\n"+
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
	 * Verify Error 500 page
	*/ 
	@Test
	private void Error500Page() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 :  Verify Error 500 page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 :  Verify Error 500 page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Verify error 500 page");
		Reporter.log("Step 2 : Verify error 500 page"); 
		Thread.sleep(2000);
		SeleniumFunc.ToGoToUrl(Constants.CM_Error500Page);
		Thread.sleep(2000);	
				
			//Verify Error 500 page
			String ExpectedText = "We're sorry, you've experienced a technical error." + "\n" 
														+"We are working to correct the error." + "\n" 
														+"Try refreshing the page now, or please try again later." + "\n"
														+"If the problem persists, please contact our customer support department.";
			String ActualText = SeleniumFunc.GetElementText("css", ".main-inner");
											
					
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(2000);	
				System.out.println("Success !! Correct Error 500 Page is displayed. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct Error 500 Page is displayed. i.e. " +ExpectedText);
			
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! Error 500 Page is NOT displayed. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Error 500 Page is NOT displayed. Expected :" + ExpectedText + "\n"+
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
	 * Test that user can go to <My Profile> page
	*/ 
	@Test
	private void RedirectToProfilePage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 :  Test that user can go to <My Profile> page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 :  Test that user can go to <My Profile> page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		GlobalHeader header = new GlobalHeader(driver);
		ProfilePage profile = new ProfilePage(driver);
		LoginPage login = new LoginPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		System.out.println("Step 2 : Go to Profile and verify control is redirected to proper page");
		Reporter.log("Step 2 : Go to Profile and verify control is redirected to proper page"); 
			
			header.UsernameDropdown.click();
			Thread.sleep(1000);
			header.Username_MyProfile.click();
			Thread.sleep(5000);
				
			//Verify Control is on profile page
			
			String ExpectedText = "KPS: View User";
			String ActualText = driver.getTitle().trim();		
			Thread.sleep(2000);	
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Control is on profile page. i.e. " +ExpectedText);
				Reporter.log("Success !! Control is on profile page. i.e. " +ExpectedText);
			
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! Control is NOT on profile page. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Control is NOT on profile page. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
			
				AssertFailedCount++;
			
			}
			
			//Verify Element
			
			ExpectedText = "Sanjeet";
			ActualText = profile.Name.getText().trim();
			Thread.sleep(2000);	
			if(ActualText.contains(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Correct Name is displayed. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct Name is displayed. i.e. " +ExpectedText);
			
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! Correct Name is NOT displayed. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Correct Name is NOT displayed. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
			
				AssertFailedCount++;
			
			}
			
			
			//Verify Agency Name
			
			ExpectedText = "Texas Empty Agency";
			ActualText = profile.Agency.getText().trim();
			Thread.sleep(2000);	
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(2000);	
				System.out.println("Success !! Correct Name is displayed. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct Name is displayed. i.e. " +ExpectedText);
			
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! Correct Name is NOT displayed. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Correct Name is NOT displayed. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
			
				AssertFailedCount++;
			
			}
		
			
			//Verify Role Permission Section
			
			ExpectedText = "Role Permissions";
			ActualText = profile.Roles.getText().trim();
			Thread.sleep(2000);	
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Role Permission Section is displayed. i.e. " +ExpectedText);
				Reporter.log("Success !! Role Permission Section is displayed. i.e. " +ExpectedText);
			
			}
			else
			{
				Thread.sleep(1000);
				System.out.println("Failure !! Role Permission Section is NOT displayed. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Role Permission Section is NOT displayed. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
			
				AssertFailedCount++;
			
			}
			
			//Verify Edit Info Section
			
			ExpectedText = "Edit Info";
			ActualText = profile.EditInfo.getText().trim();
			Thread.sleep(2000);	
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(2000);	
				System.out.println("Success !! Edit Info Section is displayed. i.e. " +ExpectedText);
				Reporter.log("Success !! Edit Info Section is displayed. i.e. " +ExpectedText);
			
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! Edit Info Section is NOT displayed. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Edit Info Section is NOT displayed. Expected :" + ExpectedText + "\n"+
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
	 * Test the elements on <My Profile>
	*/ 
	@Test
	private void UI_ProfilePage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 :  Test the elements on <My Profile>"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10 :  Test the elements on <My Profile>"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		GlobalHeader header = new GlobalHeader(driver);
		LoginPage login = new LoginPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
	
		System.out.println("Step 2 : Go to Profile and verify control is redirected to proper page");
		Reporter.log("Step 2 : Go to Profile and verify control is redirected to proper page"); 
			
			header.UsernameDropdown.click();
			Thread.sleep(1000);
			header.Username_MyProfile.click();
			Thread.sleep(3000);
				
			//Verify Control is on profile page
			
			String ExpectedText = "KPS: View User";
			String ActualText = driver.getTitle().trim();		
					
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Control is on profile page. i.e. " +ExpectedText);
				Reporter.log("Success !! Control is on profile page. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! Control is NOT on profile page. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Control is NOT on profile page. Expected :" + ExpectedText + "\n"+
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
	
	
	

	/* Test 11: 
	 * Update a certificate and verify activity section
	*/ 
	@Test
	private void VerifyActivity() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 11 : Update a certificate and verify activity section"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 11 : Update a certificate and verify activity section"  + "\n" +
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
			Thread.sleep(5000);	
			searchcerti.ClickOnNewCertificate();
			Thread.sleep(1000);
			
		System.out.println("Step 3 : Creating new Certificate");
		Reporter.log("Step 3 : Creating new Certificate"); 		
		
			String firstname= "certi" + JH.GenerateRandomNumber();
			String emailprefix = "certi" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 Proton Rd";
			Thread.sleep(1000);
			System.out.println(firstname);
			createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
		    createnewcerti.ClickOnContinueButton();
			Thread.sleep(10000);
			
			//Verifying alert message
			String ActualText= gheader.Success_AlertText.getText().trim();
			String ExpectedText= "Certification was successfully created."; 
			Thread.sleep(2000);	
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(2000);	
				System.out.println("Success !! alert message is displayed i.e. " + ActualText);
				Reporter.log("Success !! alert message is displayed i.e. " + ActualText); 
			}
			else
			{
				Thread.sleep(2000);	
				System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual : " + "\n" +  ActualText);
				
				AssertFailedCount++;
			}
			Thread.sleep(2000);	
			certidetails.ClickOnEditButton();
			Thread.sleep(1000);
			SeleniumFunc.EnterValueInTextbox("css", "#certification_gender", "Male");
			Thread.sleep(1000);
			SeleniumFunc.ClickOnElement("css", ".btn.btn-primary");
			Thread.sleep(1000);
			SeleniumFunc.ClickOnElement("css", "#activity-stream ul li:nth-of-type(1) div:nth-of-type(2) p button");
			Thread.sleep(1000);
			ExpectedText = "Male";
			ActualText = SeleniumFunc.GetElementText("css", "#details-0 dl dd span:nth-of-type(2)");
			Thread.sleep(3000);
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Correct details are displayed at activity section. i.e. " + ActualText);
				Reporter.log("Success !! Correct details are displayed at activity section. i.e. " + ActualText); 
			}
			else
			{
				System.out.println("Failure !! Details are NOT displayed at activity section" + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Details are NOT displayed at activity section" + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual : " + "\n" +  ActualText);
				
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
	 * Multi-Byte Characters Should Render Correctly on ilostmycard.com
	*/ 
	@Test
	private void  MultiByteCharacters() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 12 : Common Cases - Multi-Byte Characters Should Render Correctly on ilostmycard.com"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 12 : Common Cases - Multi-Byte Characters Should Render Correctly on ilostmycard.com"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		CertificationDetailsPage certidetails = new CertificationDetailsPage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		
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
			Thread.sleep(2000);
			
		System.out.println("Step 3 : Creating new Certificate");
		Reporter.log("Step 3 : Creating new Certificate"); 	
		
		    int num=JH.GenerateRandomNumber();
			String firstname= "ZOE" +num ;
			String emailprefix = "certi" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 Proton Rd";
			System.out.println(firstname);
			createnewcerti.CreateNewCertificate(firstname, "ROSE", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas", "PA", "Hunter-Trapper Education (1986 - Present)", "Pass", "03/02/2015", "04/02/2015");
			createnewcerti.ClickOnContinueButton();
			Thread.sleep(10000);
			
			//Verifying alert message
			String actualtext= gheader.Success_AlertText.getText().trim();
			String expectedtext= "Certification was successfully created."; 
			
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
			String ExpectedResult = firstname + " ROSE"; 
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
		
	
		System.out.println("Step 4 : Go to ILMC and search for created certificate  and verify The name is appear as Zo� Rose when searched on ilostmycard.com");
		Reporter.log("Step 4 : Go to ILMC and search for created certificate  and verify The name is appear as Zo� Rose when searched on ilostmycard.com"); 		
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			Thread.sleep(2000);
			//Select state and course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(4000);
			
			
			searchpage.EnterFirstName(firstname);
			searchpage.EnterLastName("ROSE");
			searchpage.EnterDay("28");
			searchpage.SelectMonth("December");
			searchpage.EnterYear("1989");
			Thread.sleep(2000);
			searchpage.ClickOnSearchButton();
			Thread.sleep(2000);
			
		String Actualtext=SeleniumFunc.GetElementText("css", ".certRecord>dl>dd:nth-of-type(1)").trim();
		System.out.println(Actualtext);
		String expectdtext="Zoe"+num +" Rose";
		System.out.println(expectdtext);
		if(Actualtext.equals(expectdtext))
		{
			Thread.sleep(2000);
			System.out.println("Success !! The name is appear as Zo� Rose when searched on ilostmycard.com");
			Reporter.log("Success !! The name is appear as Zo� Rose when searched on ilostmycard.com");
		}
		else 
		{
			Thread.sleep(2000);
			System.out.println("Failure !! The name is not appear as Zo� Rose when searched on ilostmycard.com");
			Reporter.log("Failure !! The name is not appear as Zo� Rose when searched on ilostmycard.com");
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
