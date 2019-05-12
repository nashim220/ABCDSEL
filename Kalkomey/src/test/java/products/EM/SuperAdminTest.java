package products.EM;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.EM.ErrorPage;
import pageFactory.EM.LoginPage;



import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class SuperAdminTest 
{
	private WebDriver driver;
	private Browser b = new Browser();
	JavaHelpers JH = new JavaHelpers();
	ErrorPage errorpage = new ErrorPage(driver);
	
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
	 * Verify user can login as superadmin
	*/ 
	@Test
	private void VerifySuperAdminRun() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 1 : Verify user can login as superadmin"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 1 : Verify user can login as superadmin"  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);

		
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
		
			Thread.sleep(5000);

		System.out.println("Step 2 : Logging in application as Super Admin");
		Reporter.log("Step 2 : Logging in application as Super Admin"); 
				
			
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 3 : Verify page header");
		Reporter.log("Step 3 : Verify page header"); 
		
				
			String Expected = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
		
			if(Expected.equals("Kalkomey Administration"))
			{
			
				System.out.println("Success !! Header is present with correct text");
				Reporter.log("Success !! Header is present with correct text");
			
			}
			else
			{
	
				System.out.println("Failure !! Header is present but with incorrect text : "+ Expected);
				Reporter.log("Failure !! Header is present but with incorrect text : "+ Expected);
				
				AssertFailedCount++;
			}

	/*	System.out.println("Step 3 : Verify welcome message");
		Reporter.log("Step 3 : Verify welcome message"); 
			
					
			String ExpectedMessage = "Welcome to the Kalkomey Administration control panel. " +
									 "Here you can manage agencies, programs, and admins."+       
					                 " To start, select an Agency to the right, or click " + "\"New Agency.\"";
			
				
			String ActualMessage = SeleniumFunc.GetElementText("css", ".span3>p");
		
			if(ExpectedMessage.contains(ActualMessage))
			{
				
				System.out.println("Success !! Welcome message is present with correct text");
				Reporter.log("Success !! Welcome message is present with correct text");
				
			}
			else
			{
		
				System.out.println("Failure !! Welcome message is present but with incorrect text : "+ ExpectedMessage);
				Reporter.log("Failure !! Welcome message is present but with incorrect text : "+ ExpectedMessage);
					
				AssertFailedCount++;
			
			}*/
						
		System.out.println("Step 3 : Verify Header for Agency Listing");
		Reporter.log("Step 3 : Verify Header for Agency Listing"); 
					
							
			String ActualHeader = SeleniumFunc.GetElementText("css", ".span9>h3");
						
			if(ActualHeader.equals("Agencies"))
			{
						
				System.out.println("Success !! Header for agency listing is present with correct text");
				Reporter.log("Success !! Header for agency listing is present with correct text");
						
			}
			else
			{
				
				System.out.println("Failure !! Header for agency listing is present but with incorrect text : "+ Expected);
				Reporter.log("Failure !! Header for agency listing is present but with incorrect text : "+ Expected);
						
				AssertFailedCount++;
			
			}

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
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
	

	/* Test 2: 
	 * Verify user can select any agency from the list
	*/ 
	@Test
	private void VerifyAgencySelection() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 2 : Verify user can select any agency from listing"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 2 : Verify user can select any agency from listing"  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);

		
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Logging in application as Super Admin");
		Reporter.log("Step 2 : Logging in application as Super Admin"); 
				
			
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 3 : Verify page header");
		Reporter.log("Step 3 : Verify page header"); 
		
				
			String Expected = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
		
			if(Expected.equals("Kalkomey Administration"))
			{
			
				System.out.println("Success !! Header is present with correct text");
				Reporter.log("Success !! Header is present with correct text");
			
			}
			else
			{
	
				System.out.println("Failure !! Header is present but with incorrect text : "+ Expected);
				Reporter.log("Failure !! Header is present but with incorrect text : "+ Expected);
				
				AssertFailedCount++;
				
			}

		System.out.println("Step 4 : Select agency from agency listing");
		Reporter.log("Step 4 : Select agency from agency listing"); 
			
					
			SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
			Thread.sleep(5000);

	
		System.out.println("Step 5 : Verify agency name");
		Reporter.log("Step 5 : Verify agency name");
		
			//String ActualName = SeleniumFunc.GetElementText("css", ".content>h3>strong");
			// css changes due to code change 
			String ActualName = SeleniumFunc.GetElementText("css", "div.span9>h3>strong");
			if(ActualName.equals("Colorado Parks & Wildlife"))
			{
				
				System.out.println("Success !! Control is redirected to correct agency page");
				Reporter.log("Success !! Control is redirected to correct agency page");
				
			}
			else
			{
				
				System.out.println("Failure !! Control is not redirected to correct agency page : "+ Expected);
				Reporter.log("Failure !! Control is redirected to correct agency page :"+ Expected);
				
				AssertFailedCount++;
				
			}
		
			
		System.out.println("Step 6 : Go to manage agency user");
		Reporter.log("Step 6 : Go to manage agency user");
			
		
			//SeleniumFunc.ClickOnElement("css", "#actionProfile");
			SeleniumFunc.ClickOnElement("css", ".nav.nav-list>li:nth-of-type(5)>a");
			Thread.sleep(5000);

			
		System.out.println("Step 7 : Verify control redirected to agency user listing");
		Reporter.log("Step 7 : Verify control redirected to agency user listing");
						
			String ExpectedHeader = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
		
			if(ExpectedHeader.equals("Agency Users"))
			{
			
				System.out.println("Success !! User is redirected to agency user listing");
				Reporter.log("Success !! User is redirected to agency user listing");
			
			}
			else
			{
			
				System.out.println("Failure !! User not redirected to "+ Expected + " page");
				Reporter.log("Failure !! User not redirected to "+ Expected + " page");
			
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
	 * Verify user can change agency
	*/ 
	@Test
	private void VerifyChangeAgency() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 3 : Verify user can change agency"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 3 : Verify user can change agency"  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);

		
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Logging in application as Super Admin");
		Reporter.log("Step 2 : Logging in application as Super Admin"); 
				
			
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 3 : Verify page header");
		Reporter.log("Step 3 : Verify page header"); 
		
				
			String Expected = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
		
			if(Expected.equals("Kalkomey Administration"))
			{
			
				System.out.println("Success !! Header is present with correct text");
				Reporter.log("Success !! Header is present with correct text");
			
			}
			else
			{
	
				System.out.println("Failure !! Header is present but with incorrect text : "+ Expected);
				Reporter.log("Failure !! Header is present but with incorrect text : "+ Expected);
				
				AssertFailedCount++;
			}
			
		System.out.println("Step 4 : Select agency from agency listing");
		Reporter.log("Step 4 : Select agency from agency listing"); 
				
						
			SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
			Thread.sleep(5000);

		
		System.out.println("Step 5 : Click on choose another agency");
		Reporter.log("Step 5 : Click on choose another agency");
		
			
			//SeleniumFunc.ClickOnElement("css", ".content>h3>a");
			SeleniumFunc.ClickOnElement("css", "div.row-fluid > div.span9 > a:nth-child(2)");
			Thread.sleep(5000);

		System.out.println("Step 6 : Verify control redirected to Administrator home page");
		Reporter.log("Step 6 : Verify control redirected to Administrator home page");
			
			
			String ExpectedPage = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
			
			if(ExpectedPage.equals("Kalkomey Administration"))
			{
			
				System.out.println("Success !! Control is redirected to Administator home page");
				Reporter.log("Success !! Header is present with correct text");
			
			}
			else
			{
	
				System.out.println("Failure !! Control is not redirected to Administator home page");
				Reporter.log("Failure !! Control is not redirected to Administator home page");
				
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
	 * Verify user can search event
	*/ 
	@Test
	private void VerifySearchEvent() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 4 : Verify user can search events from Super admin login"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			 "Test 4 : Verify user can search events from Super admin login"  + "\n" +
			 	 "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);

		
		System.out.println("Step 1 : Navigate to Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login");
		Reporter.log("Step 1 : Navigate Applicaiton login URL : " + Constants.ApplicationURL_EM + "/login/login"); 
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Logging in application as Super Admin");
		Reporter.log("Step 2 : Logging in application as Super Admin"); 
				
			
		
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 3 : Verify page header");
		Reporter.log("Step 3 : Verify page header"); 
		
				
			String Expected = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
		
			if(Expected.equals("Kalkomey Administration"))
			{
			
				System.out.println("Success !! Header is present with correct text");
				Reporter.log("Success !! Header is present with correct text");
			
			}
			else
			{
	
				System.out.println("Failure !! Header is present but with incorrect text : "+ Expected);
				Reporter.log("Failure !! Header is present but with incorrect text : "+ Expected);
				
				AssertFailedCount++;
				
			}

		System.out.println("Step 4 : Go to Search Event Page");
		Reporter.log("Step 4 : Go to Search Event Page");
		
			
			SeleniumFunc.ClickOnElement("linkText", "Events");
			Thread.sleep(5000);

			
		System.out.println("Step 5 : Verify that control is redirected to search event page");
		Reporter.log("Step 5 : Verify that control is redirected to search event page");
						
			
			String ExpectedSearchHeader = SeleniumFunc.GetElementText("css", ".page-header.page-header-compressed>h2");
		
			if(ExpectedSearchHeader.equals("Search for Events"))
			{
		
				System.out.println("Success !! Header is present with correct text");
				Reporter.log("Success !! Header is present with correct text");
		
			}
			else
			{

				System.out.println("Failure !! Header is present but with incorrect text : "+ ExpectedSearchHeader);
				Reporter.log("Failure !! Header is present but with incorrect text : "+ ExpectedSearchHeader);
			
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