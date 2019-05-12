package products.CM;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.GlobalHeader;
import pageFactory.CM.LoginPage;
import pageFactory.CM.ManageUsersPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class ManageUsersTest 
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
	 * Verify User can navigate to Users listings screen
	*/ 
	
	@Test
	private void VerifyNavigation() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify User can navigate to Users listings screen"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify User can navigate to Users listings screen"  + "\n" +
				 	  "====");	
		
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			GlobalHeader header = new GlobalHeader(driver);
			ManageUsersPage manageusers = new ManageUsersPage(driver);		
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
		
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);	
			
		System.out.println("Step 2 : Login to application and navigating to Manage Users page");
		Reporter.log("Step 2 : Login to application and navigating to Manage Users page"); 			
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();			
			Thread.sleep(2000);
			header.AdminDropdown.click();
			Thread.sleep(1000);
			header.Admin_ManageUsers.click();			
			Thread.sleep(2000);
		System.out.println("Step 3 : Verify control is redirected to Manage Users page");
		Reporter.log("Step 3 : Verify control is redirected to Manage Users page");	
		
			//Verifying title
			String ActualText= manageusers.Title.getText().trim();		
			String ExpectedText= "Search Users";		
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Control is redirected to Manage Users page. i.e. " + ActualText);
				Reporter.log("Success !! Control is redirected to Manage Users page. i.e. " + ActualText); 
				
			}	
			else
			{
				
				System.out.println("Failure !! Control is NOT redirected to Manage Users page. i.e." + "\n" + "Expected : "  
								+ "\n" + ExpectedText + "\n" + 
								 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Control is NOT redirected to Manage Users page. i.e." + "\n" + "Expected : "  
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
	

		/* Test 2: 
		 * Verify that user is able to select the values from Agency drop-down
		 */ 
	
		@Test
		private void UserCanSelectDropdownValue() throws Exception
		{
			System.out.println("====" + "\n" +
				"Test 2 : Verify that user is able to select the values from Agency dropdown"  + "\n" +
		 			"====");
			Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify that user is able to select the values from Agency dropdown"  + "\n" +
				 	  "====");	
		
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			GlobalHeader header = new GlobalHeader(driver);
			ManageUsersPage manageusers = new ManageUsersPage(driver);		
			
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 			
		
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);	
		
		System.out.println("Step 2 : Login to application and navigating to Manage Users page");
		Reporter.log("Step 2 : Login to application and navigating to Manage Users page"); 			
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();			
			
			header.AdminDropdown.click();
			Thread.sleep(1000);
			header.Admin_ManageUsers.click();	
			Thread.sleep(1000);
			
		System.out.println("Step 3 : Select agency and verify correct agency is selected");
		Reporter.log("Step 3 : Select agency and verify correct agency is selected"); 		
		
			//Verifying title				
			manageusers.SelectAgency("Texas Empty Agency");			
			String ActualText = SeleniumFunc.GetSelectedValueFromDropdownList("css", "#agency_id").trim();
			String ExpectedText = "Texas Empty Agency";		
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Correct value is selected from dropdown. i.e. " + ActualText);
				Reporter.log("Success !! Correct value is selected from dropdown. i.e. " + ActualText); 
			
			}
			else
			{
				
				System.out.println("Failure !! Value is NOT selected from dropdown. i.e." + "\n" + "Expected : "  
								+ "\n" + ExpectedText + "\n" + 
								 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Value is NOT selected from dropdown. i.e." + "\n" + "Expected : "  
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
	
		/* Test 3: 
		 * Verify system is firing validation for invalid search
		 */ 
	
		@Test
		private void InvalidSearch() throws Exception
		{
			System.out.println("====" + "\n" +
					"Test 3 : Verify system is firing validation for invalid search"  + "\n" +
		 			"====");
			Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify system is firing validation for invalid search"  + "\n" +
				 	  "====");	
		
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			GlobalHeader header = new GlobalHeader(driver);
			ManageUsersPage manageusers = new ManageUsersPage(driver);		
			
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 			
		
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);	
			
		System.out.println("Step 2 : Login to application and navigating to Manage Users page");
		Reporter.log("Step 2 : Login to application and navigating to Manage Users page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();			
			
			header.AdminDropdown.click();
			Thread.sleep(1000);
			header.Admin_ManageUsers.click();		
			Thread.sleep(1000);
			
		System.out.println("Step 3 : Verify Search functionality with invalid data");
		Reporter.log("Step 3 : Verify Search functionality with invalid data");			
		
			manageusers.EnterSearchText("criteria");
			manageusers.ClickOnSearchButton();			
			Thread.sleep(1000);
			//Verify error message
			String ActualText= manageusers.ErrorMessage.getText().trim();
			String ExpectedText= "We found no users matching your query.";

			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Search working properly with invalid data.  i.e. " + ActualText);
				Reporter.log("Success !! Search working properly with invalid data.  i.e. " + ActualText); 
			
			}
			else
			{
				System.out.println("Failure !! Search is NOT working properly with invalid data. i.e." + "\n" + "Expected : "  
							+ "\n" + ExpectedText + "\n" + 
							 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Search is NOT working properly with invalid data. i.e." + "\n" + "Expected : "  
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
		
		
		
		/* Test 4: 
		 * Verify Search listing columns are aligned
		 */ 
		
		@Test
		private void SearchColumn() throws Exception
			{	
					System.out.println("====" + "\n" +	"Test 4 :  Verify Search listing columns are aligned"  + "\n" +	"====");
					Reporter.log("====" + "\n" + "Test 4 :  Verify Search listing columns are aligned"  + "\n" +  "====");	
			
					int AssertFailedCount=0 ;
					SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
					LoginPage login = new LoginPage(driver);
					GlobalHeader header = new GlobalHeader(driver);
					ManageUsersPage manageusers = new ManageUsersPage(driver);			
			
				System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
				Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");				
				
					SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
		
				System.out.println("Step 2 : Login to application and navigating to Manage Users page");
				Reporter.log("Step 2 : Login to application and navigating to Manage Users page"); 	
			
					login.EnterUsername(Constants.CM_Username);
					login.EnterPassword(Constants.CM_Password);
					login.ClickOnLogInButton();
				
					header.AdminDropdown.click();
					Thread.sleep(1000);
					header.Admin_ManageUsers.click();
					Thread.sleep(1000);
				
				System.out.println("Step 3 : Select search criteria and verify Search listing columns are aligned");
				Reporter.log("Step 3 : Select search criteria and verify Search listing columns are aligned"); 		
			
					manageusers.SelectAgency("Texas Empty Agency");
					manageusers.EnterSearchText("Rohit");
					manageusers.ClickOnSearchButton();
					Thread.sleep(4000);
					//Verify Element
				
					//Email On Card
					if(SeleniumFunc.IsElementPresent(manageusers.Email))
					{
						Thread.sleep(1000);
						System.out.println("Success !! Email is present.");
						Reporter.log("Success !! Email is present."); 
					}
					else
					{
						System.out.println("Failure !! Email is missing");
						Reporter.log("Failure !! Email is missing");
					
						AssertFailedCount++;
					}
				
					//Agency
					if(SeleniumFunc.IsElementPresent(manageusers.Agency))
					{
						Thread.sleep(1000);
						System.out.println("Success !! Agency is  present.");
						Reporter.log("Success !! Agency is  present."); 
					}
					else
					{
						System.out.println("Failure !! Agency is missing");
						Reporter.log("Failure !! Agency is missing");
					
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
		 * Verify pagination is working
		*/ 
		
		@Test
		private void VerifyPagination() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 5 : Verify pagination is working"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 5 : Verify pagination is working"  + "\n" +
					 	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			GlobalHeader header = new GlobalHeader(driver);
			ManageUsersPage manageusers = new ManageUsersPage(driver);
			
			System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
			Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
				
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
		
			System.out.println("Step 2 : Login to application and navigating to Manage Users page");
			Reporter.log("Step 2 : Login to application and navigating to Manage Users page"); 	
			
				login.EnterUsername(Constants.CM_Username);
				login.EnterPassword(Constants.CM_Password);
				login.ClickOnLogInButton();
				
				header.AdminDropdown.click();
				Thread.sleep(1000);
				header.Admin_ManageUsers.click();
				Thread.sleep(1000);
				
				
			System.out.println("Step 3 : Verify pagination");
			Reporter.log("Step 3 : Verify pagination"); 		

					
				//Verify Next link is working
				manageusers.ClickOnNext();
				Thread.sleep(1000);
				String ActualText= driver.getCurrentUrl();
				String ExpectedText= Constants.ApplicationURL_CM + "/users/search?page=2";

				if(ActualText.equals(ExpectedText))
				{
					Thread.sleep(1000);	
					System.out.println("Success !! Next link is working, control is redirected to next page.  i.e. " + ActualText);
					Reporter.log("Success !! Next link is working, control is redirected to next page.  i.e. " + ActualText); 
					
				}
				else
				{
						
					System.out.println("Failure !! Next link is NOT working. i.e." + "\n" + "Expected Page: "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual Page: " + "\n" +  ActualText);
					Reporter.log("Failure !! Next link is NOT working. i.e." + "\n" + "Expected Page: "  
							+ "\n" + ExpectedText + "\n" + 
							 "Actual Page: " + "\n" +  ActualText);
					
					AssertFailedCount++;
				}
			
				
				//Click on specific page no
				
				manageusers.ClickOnSpecificPage();
				Thread.sleep(1000);
				ActualText= driver.getCurrentUrl();
				ExpectedText= Constants.ApplicationURL_CM + "/users/search?page=3";

				if(ActualText.equals(ExpectedText))
				{
					Thread.sleep(1000);	
					System.out.println("Success !! Specific page  link is working, control is redirected to expected page.  i.e. " + ActualText);
					Reporter.log("Success !! Specific page  link is working, control is redirected to expected page.  i.e. " + ActualText); 
					
				}
				else
				{
						
					System.out.println("Failure !! Specific page link is NOT working. i.e." + "\n" + "Expected Page: "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual Page: " + "\n" +  ActualText);
					Reporter.log("Failure !! Specific page link is NOT working. i.e." + "\n" + "Expected Page: "  
							+ "\n" + ExpectedText + "\n" + 
							 "Actual Page: " + "\n" +  ActualText);
					
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
			 * Verify Presence of 'New User' link
			*/ 
			
			@Test
			private void NewUserLink() throws Exception
			{
				System.out.println("====" + "\n" +
							"Test 6 : Verify Presence of 'New User' link"  + "\n" +
				 			"====");
				Reporter.log("====" + "\n" +
				 			  "Test 6 : Verify Presence of 'New User' link"  + "\n" +
						 	  "====");	
				
				int AssertFailedCount=0 ;
				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				LoginPage login = new LoginPage(driver);
				GlobalHeader header = new GlobalHeader(driver);
				ManageUsersPage manageusers = new ManageUsersPage(driver);
				
				System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
				Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
					
					SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
			
				System.out.println("Step 2 : Login to application and navigating to Manage Users page");
				Reporter.log("Step 2 : Login to application and navigating to Manage Users page"); 	
				
					login.EnterUsername(Constants.CM_Username);
					login.EnterPassword(Constants.CM_Password);
					login.ClickOnLogInButton();
					
					header.AdminDropdown.click();
					Thread.sleep(1000);
					header.Admin_ManageUsers.click();
					Thread.sleep(1000);
					
				System.out.println("Step 3 : Verify control is redirected to Manage Users page");
				Reporter.log("Step 3 : Verify control is redirected to Manage Users page"); 		
				
				
					//Verifying title
					String ActualText= manageusers.Title.getText().trim();
				
					String ExpectedText= "Search Users";

					Thread.sleep(1000);
					if(ActualText.equals(ExpectedText))
					{
						Thread.sleep(1000);
						System.out.println("Success !! Control is redirected to Manage Users page. i.e. " + ActualText);
						Reporter.log("Success !! Control is redirected to Manage Users page. i.e. " + ActualText); 
					
					}
					else
					{
						
						System.out.println("Failure !! Control is NOT redirected to Manage Users page. i.e." + "\n" + "Expected : "  
										+ "\n" + ExpectedText + "\n" + 
										 "Actual : " + "\n" +  ActualText);
						Reporter.log("Failure !! Control is NOT redirected to Manage Users page. i.e." + "\n" + "Expected : "  
								+ "\n" + ExpectedText + "\n" + 
								 "Actual : " + "\n" +  ActualText);
					
						AssertFailedCount++;
					}
				

				System.out.println("Step 4 : Verify New User link is present");
				Reporter.log("Step 4 : Verify New User link is present"); 		
					
					
					if(SeleniumFunc.IsElementPresent(manageusers.NewUser))
					{
						Thread.sleep(1000);
						System.out.println("Success !! New User link is present");
						Reporter.log("Success !! New User link is present"); 
					}
					else
					{
						System.out.println("Failure !! New User link is missing.");
						Reporter.log("Failure !! New User link is missing.");
					
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
			 * Verify search records are displayed
			*/ 
			
			@Test
			private void ValidSearch() throws Exception
			{
				System.out.println("====" + "\n" +
							"Test 7 : Verify search records are displayed"  + "\n" +
				 			"====");
				Reporter.log("====" + "\n" +
				 			  "Test 7 : Verify search records are displayed"  + "\n" +
						 	  "====");	
				
				int AssertFailedCount=0 ;
				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				LoginPage login = new LoginPage(driver);
				GlobalHeader header = new GlobalHeader(driver);
				ManageUsersPage manageusers = new ManageUsersPage(driver);
				
				System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
				Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
					
					SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
			
				System.out.println("Step 2 : Login to application and navigating to Manage Users page");
				Reporter.log("Step 2 : Login to application and navigating to Manage Users page"); 	
				
					login.EnterUsername(Constants.CM_Username);
					login.EnterPassword(Constants.CM_Password);
					login.ClickOnLogInButton();
					
					header.AdminDropdown.click();
					Thread.sleep(1000);
					header.Admin_ManageUsers.click();
					Thread.sleep(1000);
					
				System.out.println("Step 3 : Verify Search functionality with invalid data");
				Reporter.log("Step 3 : Verify Search functionality with invalid data"); 		
				
					manageusers.SelectAgency("Texas Empty Agency");
					manageusers.EnterSearchText("Rohit");
					manageusers.ClickOnSearchButton();
					Thread.sleep(2000);
					//Verify Record is displayed
					if(SeleniumFunc.IsElementPresent(manageusers.SearchRecord))
					{
						Thread.sleep(1000);
						System.out.println("Success !! Record is displayed");
						Reporter.log("Success !! Record is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Record is NOT displayed");
						Reporter.log("Failure !! Record is NOT displayed");
						
						AssertFailedCount++;
					}
					
					
					//Search Using AND operator
					
					manageusers.EnterSearchText("Rohit AND rohit.ware@clariontechnologies.co.in");
					manageusers.ClickOnSearchButton();
					Thread.sleep(2000);
					//Verify Record is displayed
					if(SeleniumFunc.IsElementPresent(manageusers.SearchRecord))
					{
						Thread.sleep(1000);
						System.out.println("Success !! Record is displayed");
						Reporter.log("Success !! Record is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Record is NOT displayed");
						Reporter.log("Failure !! Record is NOT displayed");
						
						AssertFailedCount++;
					}
					
					
					//Search Using AND operator
					
					manageusers.EnterSearchText("Rohit OR rohit.ware@clariontechnologies.co.in");
					manageusers.ClickOnSearchButton();
					Thread.sleep(2000);
					//Verify Record is displayed
					if(SeleniumFunc.IsElementPresent(manageusers.SearchRecord))
					{
						Thread.sleep(1000);
						System.out.println("Success !! Record is displayed");
						Reporter.log("Success !! Record is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Record is NOT displayed");
						Reporter.log("Failure !! Record is NOT displayed");
						
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
