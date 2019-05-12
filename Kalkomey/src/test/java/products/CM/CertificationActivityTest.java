package products.CM;



import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.CertificationActivityPage;
import pageFactory.CM.CertificationDetailsPage;
import pageFactory.CM.CreateNewCertificationPage;
import pageFactory.CM.CreateNewUserPage;
import pageFactory.CM.DetailsCertificationPage;
import pageFactory.CM.EditCertificationPage;
import pageFactory.CM.GlobalHeader;
import pageFactory.CM.ILMCCertificationPage;
import pageFactory.CM.ILMCHomePage;
import pageFactory.CM.ILMCOrderConfirmationPage;
import pageFactory.CM.ILMCOrderPage;
import pageFactory.CM.ILMCSearchPage;
import pageFactory.CM.LoginPage;
import pageFactory.CM.OrderReplacementPage;
import pageFactory.CM.RolesPermissionsPage;
import pageFactory.CM.SearchCertificationPage;
import pageFactory.CM.ProfilePage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class CertificationActivityTest 
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
	 * Verify that user can goto Activity page
	*/ 
	@Test
	private void NavigationToActivityPage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify that user can goto Activity page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify that user can goto Activity page"  + "\n" +
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
		
		System.out.println("Step 3 : Go to Certification Activity and verify control is redirected to Activity page");
		Reporter.log("Step 3 : Go to Certification Activity and verify control is redirected to Activity page"); 
			
				
			header.AdminDropdown.click();
			Thread.sleep(1000);
			header.Admin_CollectionActivity.click();
			Thread.sleep(3000);
			
			//Verify control is on Certification Activity page
			
			String ActualURL =  driver.getTitle();
			String ExpectedURL = "KPS: Certification Activity";
			Thread.sleep(1000);
			if(ActualURL.equals(ExpectedURL))
			{
				Thread.sleep(1000);
				System.out.println("Success !! User is redirected to Certification Activity  page");
				Reporter.log("Success !! User is redirected to Certification Activity  page"); 
			}
			else
			{
				System.out.println("Failure !! User is NOT redirected to Certification Activity  page");
				Reporter.log("Failure !! User is NOT redirected to Certification Activity  page"); 
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
	 * Verify pagination is working
	*/ 
	
	@Test
	private void VerifyPagination() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify pagination is working"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify pagination is working"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader header = new GlobalHeader(driver);
		CertificationActivityPage activity = new CertificationActivityPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Certification Activity  page");
		Reporter.log("Step 2 : Login to application and navigating to Certification Activity page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(1000);
			header.AdminDropdown.click();
			Thread.sleep(1000);
			header.Admin_CollectionActivity.click();
			Thread.sleep(3000);
			
		System.out.println("Step 3 : Verify pagination");
		Reporter.log("Step 3 : Verify pagination"); 		

				
			//Verify Next link is working
			activity.ClickOnNextButton();
			Thread.sleep(1000);
			String ActualText= driver.getCurrentUrl();
			String ExpectedText= Constants.ApplicationURL_CM + "/certifications/activity?page=2";
			Thread.sleep(1000);
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
			Thread.sleep(1000);
			activity.SpecificPage.click();
			Thread.sleep(1000);
			ActualText= driver.getCurrentUrl();
			ExpectedText= Constants.ApplicationURL_CM + "/certifications/activity?page=3";

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
	
	
	
	/* Test 3: 
	 * Verify search panel
	*/ 
	
	@Test
	private void VerifySearchPanel() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify search panel"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify search panel"  + "\n" +
				"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader header = new GlobalHeader(driver);
		CertificationActivityPage activity = new CertificationActivityPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Certification Activity  page");
		Reporter.log("Step 2 : Login to application and navigating to Certification Activity page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(1000);
			header.AdminDropdown.click();
			Thread.sleep(1000);
			header.Admin_CollectionActivity.click();
			Thread.sleep(3000);
			
		System.out.println("Step 3 : Verify  search panel");
		Reporter.log("Step 3 : Verify search panel"); 		

					
			//Agency Dropdown
			if(SeleniumFunc.IsElementPresent(activity.Agency))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Agency Dropdown is present");
				Reporter.log("Success !! Agency Dropdown is present"); 
			}
			else
			{
				System.out.println("Failure !! Agency Dropdown is NOT present ");
				Reporter.log("Failure !! Agency Dropdown is NOT present "); 
				AssertFailedCount++;
			}
		
		
			//Search Button
			if(SeleniumFunc.IsElementPresent(activity.Search))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Search Button is present");
				Reporter.log("Success !! Search Button is present"); 
			}
			else
			{
				System.out.println("Failure !! Search Button is NOT present ");
				Reporter.log("Failure !! Search Button is NOT present "); 
				AssertFailedCount++;
			}
			Thread.sleep(1000);
			activity.SelectAgency("Texas Empty Agency");
			String ActualText = SeleniumFunc.GetSelectedValueFromDropdownList("css", "#agency_id");
			String ExpectedText = "Texas Empty Agency";
			Thread.sleep(1000);
			if(ActualText.contains(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! User is able to select Agency. i.e. " +ExpectedText);
				Reporter.log("Success !! User is able to select Agency. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! User is NOT able to select Agency. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! User is NOT able to select Agency. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
			
				AssertFailedCount++;
			
			}
			
			//Verify Search
			Thread.sleep(1000);
			activity.ClickOnSearchButton();
			Thread.sleep(1000);
			if(SeleniumFunc.IsElementPresent(activity.Result))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Search Result is displayed");
				Reporter.log("Success !! Search Result is displayed"); 
			}
			else
			{
				System.out.println("Failure !! NO Results ");
				Reporter.log("Failure !! NO Results "); 
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
	 * Verify if no activity is done message is displayed
	*/ 
	
	@Test
	private void NoActivity() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify if no activity is done message is displayed"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify if no activity is done message is displayed"  + "\n" +
				"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader header = new GlobalHeader(driver);
		CertificationActivityPage activity = new CertificationActivityPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Certification Activity  page");
		Reporter.log("Step 2 : Login to application and navigating to Certification Activity page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(1000);
			header.AdminDropdown.click();
			Thread.sleep(1000);
			header.Admin_CollectionActivity.click();
			Thread.sleep(3000);
			
		System.out.println("Step 3 : Verify  No Activity  message is displayedl");
		Reporter.log("Step 3 : Verify  No Activity  message is displayedl"); 		
		
			activity.SelectAgency("Colorado State Parks");
			Thread.sleep(1000);
			activity.ClickOnSearchButton();
			Thread.sleep(1000);
			String ActualText = activity.NoActivity.getText().trim();
			String ExpectedText = "No activity.";
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Message is displayed for No Activity. i.e. " +ExpectedText);
				Reporter.log("Success !! Message is displayed for No Activity. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! Message is NOT displayed. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Message is NOT displayed. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
			
				AssertFailedCount++;
			
			}
			//
						
			
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
	 * Verify icons for different activities
	*/ 
	@Test
	private void ActivityIcons() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify icons for different activities"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 5 : Verify icons for different activities"  + "\n" +
	 				"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		CertificationDetailsPage certidetails = new CertificationDetailsPage(driver);
		CertificationActivityPage activity = new CertificationActivityPage(driver);
		OrderReplacementPage replace = new OrderReplacementPage(driver);
		GlobalHeader header = new GlobalHeader(driver);

		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to User Create New Certification page");
		Reporter.log("Step 2 : Login to application and navigating to User Create New Certification page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(1000);
			searchcerti.ClickOnNewCertificate();
			Thread.sleep(1000);
			
		System.out.println("Step 3 : Creating new Certificate");
		Reporter.log("Step 3 : Creating new Certificate"); 		
		
			String firstname= "certi" + JH.GenerateRandomNumber();
			String emailprefix = "certi" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 Proton Rd";
			String juridication="PA";
			String collection="Hunter-Trapper Education (1986 - Present)";
		   System.out.println(firstname);
			createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas",juridication, collection, "Pass", "03/02/2015", "04/02/2015");
			Thread.sleep(1000);
			createnewcerti.ClickOnContinueButton();
			Thread.sleep(10000);
			//Verifying alert message
			String actualtext= gheader.Success_AlertText.getText().trim();
			String expectedtext= "Certification was successfully created."; 
			
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(1000);
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
			
			String Certificate = driver.getCurrentUrl();
			
		System.out.println("Step 4 : Verify Activity is logged with icon for different activities");
		Reporter.log("Step 4 : Verify Activity is logged with icon for different activities"); 		
			
			//Go to activity page
		    Thread.sleep(1000);
		    header.AdminDropdown.click();
		    Thread.sleep(1000);
			gheader.Admin_CollectionActivity.click();
			Thread.sleep(3000);
			String Activity = driver.getCurrentUrl();
			Thread.sleep(1000);
			//Verify activity icon for Create Certification
			if(SeleniumFunc.IsElementPresent(activity.IconCreateCertification))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Activity is logged with icon for Certificate Created");
				Reporter.log("Success !! Activity is logged with icon for Certificate Created"); 
			}
			else
			{
				System.out.println("Failure !! Activity  / Icon is missing for Certificate Created");
				Reporter.log("Failure !! Activity  / Icon is missing for Certificate Created"); 
				AssertFailedCount++;
			}	
			
			//Verify activity icon for Add Note
			
			//Go to Certification page and add note
			SeleniumFunc.ToGoToUrl(Certificate);
			certidetails.Notes_Textbox.sendKeys("Automation Testing");
			certidetails.Notes_Add.click();
			Thread.sleep(1000);
			SeleniumFunc.ToGoToUrl(Activity);
			Thread.sleep(1000);
			//Verify activity icon for Add Note
			if(SeleniumFunc.IsElementPresent(activity.IconNote))
			{
				System.out.println("Success !! Activity is logged with icon for Add Note");
				Reporter.log("Success !! Activity is logged with icon for Add Note"); 
			}
			else
			{
				System.out.println("Failure !! Activity  / Icon is missing for Add Note");
				Reporter.log("Failure !! Activity  / Icon is missing for Add Note"); 
				AssertFailedCount++;
			}
			
			
			SeleniumFunc.ToGoToUrl(Certificate);
			Thread.sleep(1000);
			replace.ClickOnOrderReplacementButton();
			Thread.sleep(1000);
			replace.FillBillingDetails("4111111111111111", "12-2025","7047983705");
			replace.ClickOnSubmitButton();
			Thread.sleep(4000);
			
			//Verify Success Message
			String ExpectedText = "The payment has been successfully processed.";
			String ActualText = replace.Success.getText().trim();
			Thread.sleep(1000);
			if(ActualText.equals(ExpectedText))
			{
					System.out.println("Success !! Order placed successfully. i.e. " + ExpectedText);
					Reporter.log("Success !! Order placed successfully. i.e. " + ExpectedText); 
			}
			else
			{
					System.out.println("Failure !! Error while place order. i.e." + "\n" + "Expected : "  
										+ "\n" + ExpectedText + "\n" + 
										 "Actual : " + "\n" +  ActualText);
					Reporter.log("Failure !! Error while place order. i.e." + "\n" + "Expected : "  
							+ "\n" + ExpectedText + "\n" + 
							 "Actual : " + "\n" +  ActualText);
					
					AssertFailedCount++;
			}	
		
			
			//Verify Activity for Order Replacement certificate
			SeleniumFunc.ToGoToUrl(Activity);				
			Thread.sleep(3000);
			if(SeleniumFunc.IsElementPresent(activity.IconOrder))
			{
				System.out.println("Success !! Activity is logged with icon for Order Replacement");
				Reporter.log("Success !! Activity is logged with icon for Order Replacement"); 
			}
			else
			{
				System.out.println("Failure !! Activity  / Icon is missing for Order Replacement");
				Reporter.log("Failure !! Activity  / Icon is missing for Order Replacement"); 
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
	 * Verify that user go to account details screen
	*/ 
	@Test
	private void UserLinkFunctionality() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify that user go to account details screen"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 6 : Verify that user go to account details screen"  + "\n" +
	 				"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		CertificationActivityPage activity = new CertificationActivityPage(driver);
		ProfilePage profile = new ProfilePage(driver);
		GlobalHeader header = new GlobalHeader(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to User Create New Certification page");
		Reporter.log("Step 2 : Login to application and navigating to User Create New Certification page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(1000);
			searchcerti.ClickOnNewCertificate();
			Thread.sleep(1000);
			
		System.out.println("Step 3 : Creating new Certificate");
		Reporter.log("Step 3 : Creating new Certificate"); 		
		
			String firstname= "certi" + JH.GenerateRandomNumber();
			String emailprefix = "certi" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 Proton Rd";
			String juridication="PA";
			String collection="Hunter-Trapper Education (1986 - Present)";
			Thread.sleep(1000);
			 System.out.println(firstname);
			createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas",juridication, collection, "Pass", "03/02/2015", "04/02/2015");
			Thread.sleep(1000);
			createnewcerti.ClickOnContinueButton();
			Thread.sleep(10000);
			
			//Verifying alert message
			String actualtext= gheader.Success_AlertText.getText().trim();
			String expectedtext= "Certification was successfully created."; 
			Thread.sleep(1000);
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
			
			
		System.out.println("Step 4 : Go to Activity page and click on User link for newly created certificate");
		Reporter.log("Step 4 : Go to Activity page and click on User link for newly created certificate"); 		
			
		
		
			//Go to activity page
		    Thread.sleep(1000);
		    header.AdminDropdown.click();
		    Thread.sleep(1000);
			gheader.Admin_CollectionActivity.click();
			Thread.sleep(3000);
			//Click on User link
			activity.User.click();
			Thread.sleep(1000);
			
			//Verify control is on User Account Details screen
			if(SeleniumFunc.IsElementPresent(profile.Activity))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Control is redirected to User Account Details screen");
				Reporter.log("Success !! Control is redirected to User Account Details screen"); 
			}
			else
			{
				System.out.println("Failure !! Control is NOT redirected to User Account Details screen");
				Reporter.log("Failure !! Control is NOT redirected to User Account Details screen"); 
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
	 * Verify Agency users should not see KE notes or historic actions.
	*/ 
	@Test
	private void LastMonthActivity() throws Exception
	{
		System.out.println("====" + "\n" +	
						"Test 7: Verify Agency users should not see KE notes or historic actions."  + "\n" +
						"====");
		Reporter.log("====" + "\n" +
						"Test 7 : Verify Agency users should not see KE notes or historic actions."  + "\n" +	
							"====");	
		
				int AssertFailedCount=0 ;
				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				LoginPage login = new LoginPage(driver);	
				CreateNewUserPage  newuser=new CreateNewUserPage(driver);
				EditCertificationPage editcer = new EditCertificationPage(driver);
				RolesPermissionsPage role=new RolesPermissionsPage(driver);
				
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and Now go to certification details and add notes");
		Reporter.log("Step 2 : Login to application and Now go to certification details and add notes"); 	
		
				login.EnterUsername(Constants.CM_Username);
				login.EnterPassword(Constants.CM_Password);
				login.ClickOnLogInButton();			
				Thread.sleep(1000);
				
				Thread.sleep(1000);	   
	 			editcer.SearchCM("Testing CertificationDetail");
	 			Thread.sleep(1000);
				editcer.ClickOnSearch();
				editcer.ClickOnStudentNametab();
				Thread.sleep(1000);
				role.EnterAddTheNote("ClarionQa");
				role.ClickOnAddNoteBtn();
				String message=SeleniumFunc.GetElementText("css", ".alert.alert-success");
			
				System.out.println("Step 3 : Verify User should be able to add note");
				Reporter.log("Step 3 : Verify User should be able to add note"); 
				
				if(message.equals("Note was successfully created."))
				{   
					System.out.println("Success !! Note was successfully created.");
					Reporter.log("Success !! Note was successfully created."); 
				}
				else
				{
					System.out.println("Failure !! Note is not created. ");
					Reporter.log("Failure !!Note is not created."); 
					AssertFailedCount++;
				}
				Thread.sleep(1000);
				newuser.ClickOnusername();
				newuser.ClickonLogout();
				Thread.sleep(1000); 
				
				System.out.println("Step 4 : Login as an agency user and search for above cert.");
				Reporter.log("Step 4 : Login as an agency user and search for above cert."); 
				
				    login.EnterUsername(Constants.CM_Username);
	 				login.EnterPassword(Constants.CM_Password);
	 				login.ClickOnLogInButton();
	 				Thread.sleep(2000);
	 				
		 			editcer.SearchCM("Testing CertificationDetail");
		 			Thread.sleep(1000);
					editcer.ClickOnSearch();
					Thread.sleep(1000);
					editcer.ClickOnStudentNametab();
  	  	   			Thread.sleep(1000);
  	  	   			if(SeleniumFunc.IsElementPresent(role.NoteText))
  	  	   			{  
  	  	   				System.out.println("Success !! Note Text Link is not present");
  	  	   				Reporter.log("Success !! Note Text Link is not present"); 
  	  	   			}
  	  	   			else
  	  	   			{
  	  	   				System.out.println("Failure !! Note Text is  present ");
  	  	   				Reporter.log("Failure !! Note Text is  present "); 
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
	 * Verify Test that 'activity' in last month gets logged under Activity section
	*/ 
	@Test
	private void LastMonthActivityInLastMonth() throws Exception
	{
		System.out.println("====" + "\n" +	
				"Test 8 : Verify Test that 'activity' in last month gets logged under Activity section"  + "\n" +
				"====");
		Reporter.log("====" + "\n" +
				"Test 8 : Verify Test that 'activity' in last month gets logged under Activity section."  + "\n" +	
				"====");	
		
				int AssertFailedCount=0 ;
				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				LoginPage login = new LoginPage(driver);	
				RolesPermissionsPage role=new RolesPermissionsPage(driver);
				EditCertificationPage editcer = new EditCertificationPage(driver);
				GlobalHeader gheader = new GlobalHeader(driver);
				
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
				System.out.println("Step 2 : Login to application and Now go to certification details and add notes");
				Reporter.log("Step 2 : Login to application and Now go to certification details and add notes"); 	
				
						login.EnterUsername(Constants.CM_Username);
						login.EnterPassword(Constants.CM_Password);
						login.ClickOnLogInButton();			
						
						
						Thread.sleep(3000);	   
			 			editcer.SearchCM("Testing CertificationDetail");
			 			Thread.sleep(1000);
						editcer.ClickOnSearch();
						editcer.ClickOnStudentNametab();
						Thread.sleep(1000);
						role.EnterAddTheNote("ClarionQa");
						role.ClickOnAddNoteBtn();
						String message=SeleniumFunc.GetElementText("css", ".alert.alert-success");
					
						System.out.println("Step 3 : Verify User should be able to add note");
						Reporter.log("Step 3 : Verify User should be able to add note"); 
						
						if(message.equals("Note was successfully created."))
						{   
							System.out.println("Success !! Note was successfully created.");
							Reporter.log("Success !! Note was successfully created."); 
						}
						else
						{
							System.out.println("Failure !! Note is not created. ");
							Reporter.log("Failure !!Note is not created."); 
							AssertFailedCount++;
						}
						
						Thread.sleep(1000);
						gheader.UsernameDropdown.click();
						Thread.sleep(1000);
						gheader.Username_MyProfile.click();
						Thread.sleep(4000);
						
						System.out.println("Step 4 : Observe Last activities in last month and verify Notes should be added in Last activities in last month page");
						Reporter.log("Step 4 : Observe Last activities in last month and verify Notes should be added in Last activities in last month page"); 
				
						String LAstMonth=SeleniumFunc.GetElementText("css", "#activity-stream ul li:nth-of-type(1) div:nth-of-type(2) h1");
						if(LAstMonth.contains("Note entered for"))
						{   
							Thread.sleep(1000);
							System.out.println("Success !! Notes is added in Last activities in last month page is displayed");
							Reporter.log("Success !! Notes is added in Last activities in last month page is displayed"); 
						}
						else
						{
							Thread.sleep(1000);
							System.out.println("Failure !! Notes is not added in Last activities in last month page is displayed");
							Reporter.log("Failure !! Notes is not added in Last activities in last month page is displayed"); 
							AssertFailedCount++;
						}
						
						String LastMonth=SeleniumFunc.GetElementText("css", "#activity-stream header");
						if(LastMonth.contains("Activity in the Last Month"))
						{   
							Thread.sleep(1000);
							System.out.println("Success !! Last activities in last month is displayed");
							Reporter.log("Success !! Last activities in last month is displayed"); 
						}
						else
						{
							Thread.sleep(1000);
							System.out.println("Failure !! Last activities in last month is not displayed");
							Reporter.log("Failure !! Last activities in last month is not  displayed"); 
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
	 * User's page: test that on page load, individual activity log items' details are hidden and that you are able to view each that has a 'Details' button. Not every item should have a 'Details' button.
	*/ 
	@Test
	private void CertificationActivityDetails() throws Exception
	{
		System.out.println("====" + "\n" +	
							"Test 9: User's page: test that on page load, individual activity log items' details are hidden and that you are able to view each that has a 'Details' button. Not every item should have a 'Details' button." 
													+ "\n" +"====");
		Reporter.log("====" + "\n"
													+"Test 9 : User's page: test that on page load, individual activity log items' details are hidden and that you are able to view each that has a 'Details' button. Not every item should have a 'Details' button."  + "\n" 
													+	"====");	
		
				int AssertFailedCount=0 ;
				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				LoginPage login = new LoginPage(driver);	
				GlobalHeader gheader = new GlobalHeader(driver);		
				DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);
				EditCertificationPage editcer = new EditCertificationPage(driver);
				
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application ");
		Reporter.log("Step 2 : Login to application "); 	
		
				login.EnterUsername(Constants.CM_Username);
				login.EnterPassword(Constants.CM_Password);
				login.ClickOnLogInButton();			
				
				Thread.sleep(1000);   
				
		    	editcer.EnterStudentInfoforSErch("Certification1234565");
		    	editcer.ClickOnSearch();
		    	Thread.sleep(1000);   
		    	editcer.ClickOnStudentNametab();	
		    	Thread.sleep(1000); 
		    	 editcer.ClickOnEdittab();
		    	 Thread.sleep(1000); 
		    	 String Lname= "certi" + JH.GenerateRandomNumber();
		    	editcer.EnterLastname(Lname);
		    	Thread.sleep(1000); 
		    	editcer.Getsubmitt();
		    	Thread.sleep(5000);
				gheader.UsernameDropdown.click();
				Thread.sleep(2000);
				gheader.Username_MyProfile.click();
				Thread.sleep(3000);
				
				 System.out.println("Step 3 : Look for the Activity Log on the right hand side of the page titled 'Activity'.  and verify You should see a list of activity items below the activity log.");
				Reporter.log("Step 3 : Look for the Activity Log on the right hand side of the page titled 'Activity'.  and verify You should see a list of activity items below the activity log.");	
						
					     Thread.sleep(1000);
					     
					     if(SeleniumFunc.IsElementPresent(DetailsPage.Activity))
					     {
							System.out.println("Success !! List of activity items are displayed below the activity log.");
							Reporter.log("Success !! List of activity items are displayed below the activity log."); 
					     }
					     	else
					     	{
					     		System.out.println("Failure !!  List of activity items are not displayed below the activity log.");
					     		Reporter.log("Failure !!  List of activity items are not displayed below the activity log."); 
					     		AssertFailedCount++;
					     	}
						
					     if(!SeleniumFunc.IsElementPresent(DetailsPage.ActivityDetailShow))
						 {
						
							 System.out.println("Success !! Activity details items are 'collapsed',");
							 Reporter.log("Success !! Activity details items are 'collapsed',"); 
						 }
		   					else
		   					{
		   						System.out.println("Failure !!  Activity details items are not 'collapsed',");
		   						Reporter.log("Failure !!  Activity details items are not 'collapsed',"); 
		   						AssertFailedCount++;
		   					}
						
					System.out.println("Step 4 : Click on any action that has a 'Details' button/link and user see more details to the action slide down, below the link. ");
				    Reporter.log("Step 4 : Click on any action that has a 'Details' button/link and user see more details to the action slide down, below the link. ");
						
				    	Thread.sleep(2000);
						DetailsPage.ClickOnActivityDetail();
						Thread.sleep(4000);
						
					//	String expectedtext = "height: auto;";
					//	String actualtext = SeleniumFunc.GetAttributeValue("css", "#activity-stream li:nth-of-type(1) div:nth-of-type(2)", "style").trim();
						
						if(SeleniumFunc.IsElementPresent("css", "#details-0[style='height: auto;']"))
						{
						
							System.out.println("Success !! User see more details to the action slide down, below the link.");
							Reporter.log("Success !! User see more details to the action slide down, below the link."); 
						}
		   					else
		   					{
		   						System.out.println("Failure !!  User is not able to see more details to the action slide down, below the link.");
		   						Reporter.log("Failure !!  User is not able to see more details to the action slide down, below the link."); 
		   						AssertFailedCount++;
		   					}
					
					System.out.println("Step 5 : Click the same detail button and verify same details that previously were revealed should be hidden again.");
				    Reporter.log("Step 5 : Click the same detail button and verify same details that previously were revealed should be hidden again.");
								
				    	Thread.sleep(1000);
				    	DetailsPage.ClickOnActivityDetail();
				    	Thread.sleep(3000);
								
				    //	expectedtext = "height: 0px;";
					//	actualtext = SeleniumFunc.GetAttributeValue("css", "#activity-stream li:nth-of-type(1) div:nth-of-type(2)", "style").trim();
						
						
				    	if(SeleniumFunc.IsElementPresent("css", "#details-0[style='height: 0px;']"))
				    	{
								
				    		System.out.println("Success !! Activity details items are 'collapsed',");
							Reporter.log("Success !! Activity details items are 'collapsed',"); 
						}
		   					else
		   					{
		   						System.out.println("Failure !!  Activity details items are not 'collapsed',");
		   						Reporter.log("Failure !!  Activity details items are not 'collapsed',"); 
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
	 *Main activity log page: test that on page load, individual activity log items' details are hidden and that you are able to view each activity log item that has a 'Details' button. Not every item should have a 'Details' button.
	 */
	 
	/*@Test
	private void ActivityDetails() throws Exception
	{
		System.out.println("====" + "\n" +	
	"Test 10: Verify Test that 'activity' in last month gets logged under Activity section"  + "\n" +
				"====");
		Reporter.log("====" + "\n" +
				"Test 10 : Verify Test that 'activity' in last month gets logged under Activity section."  + "\n" +	
	"====");	
		
				int AssertFailedCount=0 ;
				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				LoginPage login = new LoginPage(driver);	
				GlobalHeader gheader = new GlobalHeader(driver);
				CertificationActivityPage activity = new CertificationActivityPage(driver);			
				
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to User Create New Certification page");
		Reporter.log("Step 2 : Login to application and navigating to User Create New Certification page"); 	
		
				login.EnterUsername(Constants.CM_Username);
				login.EnterPassword(Constants.CM_Password);
				login.ClickOnLogInButton();			
				 Thread.sleep(1000);
				gheader.UsernameDropdown.click();
				 Thread.sleep(1000);
				gheader.Username_MyProfile.click();
	            Thread.sleep(3000);
				
		System.out.println("Step 3 : Verify  Activity Page ");
		Reporter.log("Step 3 : Verify  Activity Page"); 		
				
				//Agency Page
				if(SeleniumFunc.IsElementPresent(activity.ActivityPage))
				{
					
					System.out.println("Success !! Agency Header  is present");
					Reporter.log("Success !! Agency Header is present"); 
				}
				else
				{
					System.out.println("Failure !! Agency Header is NOT present ");
					Reporter.log("Failure !! Agency Header is NOT present "); 
					AssertFailedCount++;
				}
				Thread.sleep(2000);
			
				String CorrentUrl=SeleniumFunc.ToGetCurrentPageUrl();
				System.out.println("Success !! Corrent Url  is present"+CorrentUrl);
				Reporter.log("Success !!  Corrent Url  is Present"+CorrentUrl); 
				
				//Agency Page
				if(SeleniumFunc.IsElementPresent(activity.RightSideActivity))
				{
					
					System.out.println("Success !! Right Side Activity Header  is present");
					Reporter.log("Success !! Right Side Activity Header is present"); 
				}
				else
				{
					System.out.println("Failure !! Right Side Activity Header is NOT present ");
					Reporter.log("Failure !!Right Side Activity Header is NOT present "); 
					AssertFailedCount++;
				}
				
				
			System.out.println("Step 4 : Verify  Details Activity Button functionality");
			Reporter.log("Step 4 : Verify  Details Activity Button functionality"); 	
			
				Thread.sleep(3000);
				activity.ClickOnDetailsActivity();
				activity.ClickOnDetailsActivity();
				Thread.sleep(3000);
				//details should display 
				if(SeleniumFunc.IsElementPresent(activity.Details))
				{
					
					System.out.println("Success !! Activity  Details  Status   is present");
					Reporter.log("Success !! Activity  Details Status   is present"); 
				}
				else
				{
					System.out.println("Failure !! Activity  Details Status  is NOT present ");
					Reporter.log("Failure !!Activity  Details Status   is NOT present "); 
					AssertFailedCount++;
				}
				Thread.sleep(5000);
				activity.ClickOnDetailsActivity();		
				Thread.sleep(5000);
				if(!SeleniumFunc.IsElementPresent(activity.Details))
				{
					System.out.println("Success !! Activity  Details  Status   is  Not present");
					Reporter.log("Success !! Activity  Details Status   is Not  present"); 
				}
				else
				{
					System.out.println("Failure !! Activity  Details Status  is  present ");
					Reporter.log("Failure !!Activity  Details Status   is  present "); 
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
	 
	/* Test 11: 
	 * Verify all the activity gets logged
	*/ 
	@Test
	private void VerifySupporText() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 11 : Verify all the activity gets logged"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 11 : Verify all the activity gets logged"  + "\n" +
	 				"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCCertificationPage certipage = new ILMCCertificationPage(driver);
		ILMCOrderPage orderpage = new ILMCOrderPage(driver);
		ILMCOrderConfirmationPage confirmpage = new ILMCOrderConfirmationPage(driver);
		LoginPage login = new LoginPage(driver);	
		CertificationActivityPage activity = new CertificationActivityPage(driver);			
		SearchCertificationPage certi = new SearchCertificationPage(driver);
		EditCertificationPage editcer = new EditCertificationPage(driver);
		
		System.out.println("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		Reporter.log("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			
			
		System.out.println("Step 2 : Select state and course and go to search page");
		Reporter.log("Step 2 : Select state and course and go to search page"); 
						
			
			//Select state and course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
						
			
		System.out.println("Step 3 : Enter valid record and go to certification page");
		Reporter.log("Step 3 : Enter valid record and go to certification page"); 
		
			
			searchpage.EnterFirstName("DONALD");
			searchpage.EnterLastName("SCHERER");
			searchpage.SelectMonth("May");
			//SeleniumFunc.SelectValueFromDropdownList("css", "#student_info_dob_month", "May");
			searchpage.EnterDay("15");
			searchpage.EnterYear("1975");
			searchpage.ClickOnSearchButton();
			Thread.sleep(3000);
			// go to order replacement page
			certipage.ClickOnConfirmCheckbox();
			certipage.ClickOnOrderButton();
			Thread.sleep(3000);
			
		System.out.println("Step 4 : Verify user can place order successfully");
		Reporter.log("Step 4 : Verify user can place order successfully"); 
			
		
			orderpage.FillBillingDetails("4111111111111111", "02-2038", "Automation", "Testing", "rohit@gmail.com", "rohit@gmail.com");
			orderpage.ClickOnSameBillingCheckbox();
			Thread.sleep(2000);
			orderpage.ClickOnPlaceOrderButton();
			Thread.sleep(2000);

			//Verify Confirmation
			
			String ExpectedText="The payment was successfully processed.";
			String ActualText = confirmpage.Success.getText().trim();
				
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Order placed successfully. i.e. " +ExpectedText);
				Reporter.log("Success !! Order placed successfully. i.e. " +ExpectedText);
		
			}
			else
			{
				Thread.sleep(1000);
				System.out.println("Failure !! Error in order. Expected :" + ExpectedText + "\n"+
							"Actual :" +ActualText);
				Reporter.log("Failure !! Error in order. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
		
				AssertFailedCount++;
		
			}
			
			
			
			//Verify Support Text
			ActualText = confirmpage.SupportText.getText().trim();
			ExpectedText = "Your receipt and temporary certificate have been emailed to you." +
					" You may print your temporary certificate for use until your permanent card arrives." +
					" If you have any problems, you may contact Kalkomey customer service at" +
					" support@ilostmycard.com or by calling 800-830-2268.";
		
			if(ActualText.equals(ExpectedText))
			{
			
				System.out.println("Success !! Correct Support Text is displayed. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct Support Text is displayed. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! Incorrect Support Text. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect Support Text. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
			
				AssertFailedCount++;
			
			}
	    	
			System.out.println("Step 6 : Login As  Admin  User and navigating to Certification page");
			Reporter.log("Step 6 :  Login As  Admin  User and navigating to Certification page"); 	
					
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
					login.EnterUsername(Constants.CM_Username);
					login.EnterPassword(Constants.CM_Password);
					login.ClickOnLogInButton();		
							
			System.out.println("Step 7 : Verify Able to Search ");
			Reporter.log("Step 7 : Verify Able to Search "); 	
				    
			
				   Thread.sleep(2000);
					if(SeleniumFunc.IsElementPresent(certi.ManageCerti_Icon))
					{
						
						System.out.println("Success !! Certification manager icon is present in the header");
						Reporter.log("Success !! Certification manager icon is present in the header"); 
					}
					else
					{
						System.out.println("Failure !! Certification manager icon is NOT present in the header");
						Reporter.log("Failure !! Certification manager icon is NOT present in the header"); 
						AssertFailedCount++;
					}
					
					
					certi.ClickOnManageCerification();
					Thread.sleep(4000);
					if(SeleniumFunc.IsElementPresent(certi.SearchField))
					{

						System.out.println("Success !! Student  Info TextBox   is present");
						Reporter.log("Success !!  Student  Info TextBox is present"); 
					}
					else
					{
						System.out.println("Failure !!  Student  Info TextBox  is NOT present ");
						Reporter.log("Failure !! Student  Info TextBox is NOT present "); 
						AssertFailedCount++;
					}
				
					certi.EnterSeachCriteria("Automation ScriptingNew");	
					
					certi.ClickOnSearchButton();
					Thread.sleep(2000);
					activity.ClickOnFirstuserlink();
					Thread.sleep(4000);
					//verify user page
					if(SeleniumFunc.IsElementPresent(activity.EditTab))
					{
						
						System.out.println("Success !! Edit Tab    is present");
						Reporter.log("Success !!  Edit Tabis present"); 
					}
					else
					{
						System.out.println("Failure !!  Edit Tab is NOT present ");
						Reporter.log("Failure !!  Edit Tab is NOT present "); 
						AssertFailedCount++;
					}
					
		System.out.println("Step 8 : Verify Admin is Able to Edit UserDetails ");
		Reporter.log("Step 8 : Verify Admin is Able to Edit UserDetails");
					
					Thread.sleep(2000);
					activity.ClickOnEditTab();
					String address= "Street" + JH.GenerateRandomNumber();
					Thread.sleep(1000); 
					editcer.EnterStreetAddress(address);
					editcer.Entercaddress2(address);	
					Thread.sleep(2000); 
					editcer.Getsubmitt();
					Thread.sleep(3000);		
					editcer.ClickOnuseaddress1();     
					/*Thread.sleep(2000);	
					editcer.ClickOnuseaddress2();*/
					Thread.sleep(2000);	
					editcer.ClickOnSaveBtn();
					Thread.sleep(5000);		
		    	
					
				String detail=SeleniumFunc.GetElementText("css", "#activity-stream ul li:nth-of-type(1) div:nth-of-type(2) h1").trim();
		    	
		    	 if(detail.equals("Certification fields were updated"))
			     {
					System.out.println("Success !! Certification fields were updated message is displayed");
					Reporter.log("Success !! Certification fields were updated message is displayed"); 
			     }
			     	else
			     	{
			     		System.out.println("Failure !!  Certification fields were updated message is not displayed");
			     		Reporter.log("Failure !!  Certification fields were updated message is not displayed"); 
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
