package products.EM;



import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.EM.AgencyHomePage;
import pageFactory.EM.ErrorPage;
import pageFactory.EM.LoginPage;
import pageFactory.EM.ProgramSettingsPage;
import pageFactory.EM.RegisterEdEventView;
import pageFactory.EM.RegisterEdHomePage;




import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class RegisterEdGeneralTest 
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
	 * Verify UI of header of Register-Ed home page
	*/ 
	@Test
	private void HeaderUIElementVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify UI of Register-Ed home page header"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify UI of Register-Ed home page header"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegisterEdHomePage homepage = new RegisterEdHomePage(driver);
		
		System.out.println("Step 1 : Navigate to Register-Ed home page :" + Constants.APPLICATIONURL_RegisterEd);
		Reporter.log("Step 1 : Navigate to Register-Ed home page: " + Constants.APPLICATIONURL_RegisterEd); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd);
			Thread.sleep(5000);

			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			String browserName = cap.getBrowserName().toLowerCase();


			if(browserName.equals("internet explorer"))
			try{
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
				Thread.sleep(5000);
			}catch (Exception e) {
			}	

		System.out.println("Step 3 : Verify presence of Update Registration button");
		Reporter.log("Step 3 : Verify presence of Update Registration button");
		
		
			if(SeleniumFunc.IsElementPresent("css", ".btn.btn-primary.login-link"))
			{
				
				System.out.println("Success !! Update Registration button is present");
				Reporter.log("Success !! Update Registration button is present"); 
			
			}
			else
			{
				
				System.out.println("Failure !! Update Registration button is not present");
				Reporter.log("Failure !! Update Registration button is not present"); 
				
				AssertFailedCount++;
				
			}
		
		System.out.println("Step 3 : Verify presence of Cancel Registration button");
		Reporter.log("Step 3 : Verify presence of Cancel Registration button");
			
			
			if(SeleniumFunc.IsElementPresent("css", ".btn.btn-danger.login-link"))
			{
					
				System.out.println("Success !! Cancel Registration button is present");
				Reporter.log("Success !! Cancel Registration button is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Cancel Registration button is not present");
				Reporter.log("Failure !! Cancel Registration button is not present"); 
					
				AssertFailedCount++;
					
			}
						
		
		System.out.println("Step 3 : Verify presence of Register-Ed Logo");
		Reporter.log("Step 3 : Verify presence of Register-Ed Logo");
				
				
			if(SeleniumFunc.IsElementPresent("css", ".hero-unit-logo"))
			{
						
				System.out.println("Success !! Register-Ed Logo is present");
				Reporter.log("Success !! Register-Ed Logo is present"); 
					
			}
			else
			{
						
				System.out.println("Failure !! Register-Ed Logo is not present");
				Reporter.log("Failure !! Register-Ed Logo is not present"); 
						
				AssertFailedCount++;
						
			}
						
					
			
		System.out.println("Step 3 : Verify funcionality of Update Registration button");
		Reporter.log("Step 3 : Verify funcionality of Update Registration button");
		
			
			homepage.ClickOnUpdateRegiButton();
			Thread.sleep(5000);


		System.out.println("Step 3 : Verify header of Update Registration Page");
		Reporter.log("Step 3 : Verify header of Update Registration Page");
		
		
			String ActHeader = homepage.Header.getText().trim();
			String ExpHeader = "Update or Cancel Registration";
			
			if(ActHeader.equals(ExpHeader))
			{
				
				System.out.println("Success !! Page Header is present with correct text");
				Reporter.log("Success !! Page Header is present with correct text"); 
					
			}
			else
			{
						
				System.out.println("Failure !! Incorrect Header");
				Reporter.log("Failure !! Incorrect Header"); 
						
				AssertFailedCount++;
						
			}
			
		
		System.out.println("Step 3 : Verify header of Cancel Registration Page");
		Reporter.log("Step 3 : Verify header of Cancel Registration Page");
			
			
			String ActlHeader = homepage.Header.getText().trim();
			String ExpdHeader = "Update or Cancel Registration";
				
			if(ActlHeader.equals(ExpdHeader))
			{
					
				System.out.println("Success !! Page Header is present with correct text");
				Reporter.log("Success !! Page Header is present with correct text"); 
						
			}
			else
			{
				
				System.out.println("Failure !! Incorrect Header");
				Reporter.log("Failure !! Incorrect Header"); 
							
				AssertFailedCount++;
							
			}
				
		
			System.out.println("Step 3 : Verify message on Update / Cancel Registration page");
			Reporter.log("Step 3 : Verify message on Update / Cancel Registration page");
				
				
				String ActlMessage = homepage.Message.getText().trim();
				String ExpdMessage = "Provide the e-mail address used during registration. We’ll send a confirmation e-mail to verify your address. " +
									 "From there, you will be able to cancel or make other changes.";
					
				if(ActlMessage.equals(ExpdMessage))
				{
						
					System.out.println("Success !! Proper message is present on Update / Cancel Registration page");
					Reporter.log("Success !! Proper message is present on Update / Cancel Registration page"); 
							
				}
				else
				{
					
					System.out.println("Failure !! Incorrect message on Update / Cancel Registration page");
					Reporter.log("Failure !! Incorrect message on Update / Cancel Registration page"); 
								
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
	 * Verify Billboard on Register-Ed home page 
	*/ 
	@Test
	private void BillboardVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify Billboard on Register-Ed home page "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify Billboard on Register-Ed home page "  + "\n" +
				 	  "====");	
		 
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegisterEdHomePage HomePage = new RegisterEdHomePage(driver);
		
		System.out.println("Step 1 : Navigate to Register-Ed home page :" + Constants.APPLICATIONURL_RegisterEd);
		Reporter.log("Step 1 : Navigate to Register-Ed home page: " + Constants.APPLICATIONURL_RegisterEd); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd);
			Thread.sleep(5000);

			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			String browserName = cap.getBrowserName().toLowerCase();


			if(browserName.equals("internet explorer"))
			try{
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
				Thread.sleep(5000);
			}catch (Exception e) {
			}	

		System.out.println("Step 2 : Verify Billboard section text");
		Reporter.log("Step 1 : Verify Billboard section text"); 
				
	
			String ActualText = HomePage.HeroArea.getText().trim();
			System.out.println(ActualText);
			String ExpectedText = "Find Events." + "\n" +
								  "Get Registered." + "\n" +
								  "Discover safety education events from agencies and organizations across the United States of America.";
			
			System.out.println(ExpectedText);
			if(ActualText.equals(ExpectedText))
			{
				
				
				System.out.println("Success !! Billboard with proper text is present on home page");
				Reporter.log("Success !! Billboard with proper text is present on home page"); 
						
			}
			else
			{
				
				System.out.println("Failure !! Incorrect Billboard text on home page");
				Reporter.log("Failure !! Incorrect Billboard text on home page"); 
							
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
	
	
	/* Test 3: 
	 * Home - Verify functionality of 'Select State Menu' dropdown
	 * */ 
	@Test
	private void Home_SelectStateMenu() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Home - Verify functionality of 'Select State Menu' dropdown"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Home - Verify functionality of 'Select State Menu' dropdown"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegisterEdHomePage HomePage = new RegisterEdHomePage(driver);
		
		System.out.println("Step 1 : Navigate to Register-Ed home page :" + Constants.APPLICATIONURL_RegisterEd);
		Reporter.log("Step 1 : Navigate to Register-Ed home page: " + Constants.APPLICATIONURL_RegisterEd); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd);
			Thread.sleep(5000);
			
		System.out.println("Step 2: Click on 'Select State Menu' dropdown and clicking on few states links and verifying that user is navigated correctly to state specific pages");
		Reporter.log("Step 2: Click on 'Select State Menu' dropdown and clicking on few states links and verifying that user is navigated correctly to state specific pages");
		
		for(int i=4;i<7;i++)
		{

			HomePage.ClickOnMenu();
			Thread.sleep(5000);

			String expectedurl = HomePage.ClickAndGetValueOnStatesLink(i);
		
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
			Thread.sleep(5000);

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
	 * Footer - Verify page footer elements
	 * */ 
	@Test
	private void Footer_UIElementVarification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Footer - Verify page footer elements"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Footer - Verify page footer elements"  + "\n" +
				 	  "====");	
		
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegisterEdHomePage HomePage = new RegisterEdHomePage(driver);
		
		System.out.println("Step 1 : Navigate to Register-Ed home page :" + Constants.APPLICATIONURL_RegisterEd);
		Reporter.log("Step 1 : Navigate to Register-Ed home page: " + Constants.APPLICATIONURL_RegisterEd); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd);
			Thread.sleep(5000);

			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			String browserName = cap.getBrowserName().toLowerCase();


			if(browserName.equals("internet explorer"))
			try{
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
				Thread.sleep(5000);
			}catch (Exception e) {
			}	

			
		System.out.println("Step 2 : Verify presence of sister project links");
		Reporter.log("Step 2 : Verify presence of sister project links"); 
	
		
			String ExpectedText = "boat-ed.comhunter-ed.combowhunter-ed.comoffroad-ed.comsnowmobile-ed.com" + 
					"https://www.boat-ed.com/https://www.hunter-ed.com/https://www.bowhunter-ed.com/https://www.offroad-ed.com/https://www.snowmobile-ed.com/";
			String ActualText = HomePage.GetAllSisterLinksName() + HomePage.GetAllSisterLinks();
	

			if(ActualText.contains(ExpectedText) )
			{
				
				System.out.println("Success!! footer has correct sister sites links i.e. " + ActualText);
				Reporter.log("Success!! footer has correct sister sites links i.e. " + ActualText); 
			
			}
			else
			{
			
				System.out.println("Failure !! footer has incorrect sister sites links  " + "\n" + "Expected Text: "  
							+ "\n" + ExpectedText + "\n" + 
							 "Actual Text : " + "\n" +  ActualText);
				Reporter.log("Failure !! footer has incorrect sites sites links " + "\n" + "Expected Text: "  
				+ "\n" + ExpectedText + "\n" + 
				 "Actual Text : " + "\n" +  ActualText);
		
				AssertFailedCount++;
			
			}
			
			//Verifying presence of 'Kalkomey Enterprises, Inc.' section
			
			ExpectedText = "Kalkomey Enterprises, Inc.";
			ActualText = HomePage.AboutKalKomeySectionHeader.getText();
			System.out.println(ActualText);

			if(ActualText.contains(ExpectedText) )
			{
				System.out.println("Success!! footer has 'About Kalkomey Enterprises, Inc.' section i.e. " + ActualText);
				Reporter.log("Success!! footer has 'About Kalkomey Enterprises, Inc.' section i.e. " + ActualText); 
			}
			else
			{
				System.out.println("Failure !!  footer doesn't have 'About Kalkomey Enterprises, Inc.' section  " + "\n" + "Expected Text: "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual Text : " + "\n" +  ActualText);
				Reporter.log("Failure !! footer doesn't have 'About Kalkomey Enterprises, Inc.' section " + "\n" + "Expected Text: "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual Text : " + "\n" +  ActualText);
				
				AssertFailedCount++;
			}

			
			//Verifying presence of 'Contact Us' section
			
			ExpectedText = "Contact Us";
			ActualText = HomePage.ContactUsSection.getText();
			System.out.println(ActualText);

			if(ActualText.contains(ExpectedText) )
			{
				System.out.println("Success!! footer has 'Contact Us' section i.e. " + ActualText);
				Reporter.log("Success!! footer has 'Contact Us' section i.e. " + ActualText); 
			}
			else
			{
				System.out.println("Failure !!  footer doesn't have 'Contact Us' section  " + "\n" + "Expected Text: "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual Text : " + "\n" +  ActualText);
				Reporter.log("Failure !! footer doesn't have 'Contact Us' section " + "\n" + "Expected Text: "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual Text : " + "\n" +  ActualText);
				
				AssertFailedCount++;
			}
			
			
			//Verifying presence of 'Frequently Asked Questions' section
			
			ExpectedText = "Frequently Asked Questions";
			ActualText = HomePage.FAQSection.getText();
			System.out.println(ActualText);

			if(ActualText.contains(ExpectedText) )
			{
				System.out.println("Success!! footer has 'Frequently Asked Questions' section i.e. " + ActualText);
				Reporter.log("Success!! footer has 'Frequently Asked Questions' section i.e. " + ActualText); 
			}
			else
			{
				System.out.println("Failure !!  footer doesn't have 'Frequently Asked Questions' section  " + "\n" + "Expected Text: "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual Text : " + "\n" +  ActualText);
				Reporter.log("Failure !! footer doesn't have 'Frequently Asked Questions' section " + "\n" + "Expected Text: "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual Text : " + "\n" +  ActualText);
				
				AssertFailedCount++;
			}
			
	
			//Verifying presence of 'Agency Login'
			
			ExpectedText = "Agency Login";
			ActualText = HomePage.AgencyLogin.getText();
			System.out.println(ActualText);

			if(ActualText.contains(ExpectedText) )
			{
				System.out.println("Success!! footer has 'Agency Login' section i.e. " + ActualText);
				Reporter.log("Success!! footer has 'Agency Login' section i.e. " + ActualText); 
			}
			else
			{
				System.out.println("Failure !!  footer doesn't have 'Agency Login' section  " + "\n" + "Expected Text: "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual Text : " + "\n" +  ActualText);
				Reporter.log("Failure !! footer doesn't have 'Agency Login' section " + "\n" + "Expected Text: "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual Text : " + "\n" +  ActualText);
				
				AssertFailedCount++;
			}
			
			//Verifying presence of 'Privacy Policy'
			
			ExpectedText = "View Privacy and Terms of Use.";
			ActualText = HomePage.Terms.getText();
			System.out.println(ActualText);

			if(ActualText.contains(ExpectedText) )
			{
				System.out.println("Success!! footer has 'Privacy Policy' section i.e. " + ActualText);
				Reporter.log("Success!! footer has 'Privacy Policy' section i.e. " + ActualText); 
			}
			else
			{
				System.out.println("Failure !!  footer doesn't have 'Privacy Policy' section  " + "\n" + "Expected Text: "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual Text : " + "\n" +  ActualText);
				Reporter.log("Failure !! footer doesn't have 'Privacy Policy' section " + "\n" + "Expected Text: "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual Text : " + "\n" +  ActualText);
				
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
	 * Verify user can view 'Active programs' page 
	 * */ 
	@Test
	private void ActiveProgram() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify user can view 'Active programs' page "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify user can view 'Active programs' page "  + "\n" +
				 	  "====");	
		
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegisterEdHomePage HomePage = new RegisterEdHomePage(driver);
		
		System.out.println("Step 1 : Navigate to Register-Ed home page :" + Constants.APPLICATIONURL_RegisterEd);
		Reporter.log("Step 1 : Navigate to Register-Ed home page: " + Constants.APPLICATIONURL_RegisterEd); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd);
			Thread.sleep(5000);

			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			String browserName = cap.getBrowserName().toLowerCase();


			if(browserName.equals("internet explorer"))
			try{
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
				Thread.sleep(5000);
			}catch (Exception e) {
			}	

			
		System.out.println("Step 2 : Select state from home page");
		Reporter.log("Step 2 : Select state from home page");
		
			
			HomePage.ClickOnMenu();
			Thread.sleep(5000);

			String expectedurl = HomePage.ClickAndGetValueOnStatesLink(5);
			
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
		
			//Verifying presence of 'View upcoming events'
			
			String ExpectedText = "View upcoming events";
			String ActualText = SeleniumFunc.GetElementText("css", "#primary_content .span3 a");
			
			if(ActualText.contains(ExpectedText) )
			{
				System.out.println("Success!! footer has 'View upcoming events' section i.e. " + ActualText);
				Reporter.log("Success!! footer has 'View upcoming events' section i.e. " + ActualText); 
			}
			else
			{
				System.out.println("Failure !!  footer doesn't have 'View upcoming events' section  " + "\n" + "Expected Text: "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual Text : " + "\n" +  ActualText);
				Reporter.log("Failure !! footer doesn't have 'View upcoming events' section " + "\n" + "Expected Text: "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual Text : " + "\n" +  ActualText);
				
				AssertFailedCount++;
			}
			
			//Verifying presence of 'View upcoming events'
			
			ExpectedText = "Active Programs";
			ActualText = HomePage.Header.getText().trim();
			
			if(ActualText.contains(ExpectedText) )
			{
				System.out.println("Success!! Correct header is present i.e. " + ActualText);
				Reporter.log("Success!! Correct header is present i.e. " + ActualText); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect Header " + "\n" + "Expected Text: "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual Text : " + "\n" +  ActualText);
				Reporter.log("Failure !! Incorrect Header " + "\n" + "Expected Text: "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual Text : " + "\n" +  ActualText);
				
				AssertFailedCount++;
			}
			
			
/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			
			//Verifying presence of Logo
			
			if(SeleniumFunc.IsElementPresent("css", ".division-logo"))
			{
				System.out.println("Success!! Logo is present");
				Reporter.log("Success!! Logo is present"); 
			}
			else
			{
				System.out.println("Failure !! Logo is missing");
				Reporter.log("Failure !! Logo is missing");
				
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
	 * NC registrations can only select US country.
	 */ 

	
	@Test
	private void NC_USOnly() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : NC registrations can only select US country."  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : NC registrations can only select US country."  + "\n" +
				 	  "====");	
		
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegisterEdHomePage HomePage = new RegisterEdHomePage(driver);
		RegisterEdEventView eventview = new RegisterEdEventView(driver);

		System.out.println("Step 1 : Navigate to Register-Ed home page :" + Constants.APPLICATIONURL_RegisterEd);
		Reporter.log("Step 1 : Navigate to Register-Ed home page: " + Constants.APPLICATIONURL_RegisterEd); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd);
			Thread.sleep(5000);
			
		System.out.println("Step 2 : Select state from home page");
		Reporter.log("Step 2 : Select state from home page");
					
			HomePage.ClickOnMenu();
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("linkText", "North Carolina");
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("css", "#primary_content ul:nth-of-type(6) div:nth-of-type(2) a");
			Thread.sleep(5000);

			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd+"/events/register/55167");
			//SeleniumFunc.ClickOnElement("css", ".btn.btn-large.pull-right.hidden-phone");
			Thread.sleep(5000);

			//eventview.ClickOnRegisterNowButton();
			//Thread.sleep(5000);

			//Verify only United States of America option is present under Drop-down
		    String[] dropdownvalues = {"United States of America"};

			SeleniumFunc.VerifyDropDownOptions("id", "RegistrationPCountryId", dropdownvalues);
	
			
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
	 * Viewing a program's RSS feed
	*/ 
	@Test
	private void ViewRSSFeed() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Viewing a program's RSS feed"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Viewing a program's RSS feed"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		ProgramSettingsPage programsettings = new ProgramSettingsPage(driver);
		RegisterEdHomePage homepage = new RegisterEdHomePage(driver);

		System.out.println("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");				
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Select agency from agency listing");
		Reporter.log("Step 2 : Select agency from agency listing"); 
				
						
			SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
			Thread.sleep(5000);

		
		System.out.println("Step 3 : Select a program and go to Program Settings");
		Reporter.log("Step 3 : Select a program and go to Program Settings"); 
			
		
		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);

		  	agencyhome.ClickOnProgramSettingsLink();
			Thread.sleep(5000);

		  	
		System.out.println("Step 4 : Verify Title for Program Settings");
		Reporter.log("Step 4 : Verify Title for Program Settings");
				
			String Actual = programsettings.Title.getText().trim();
			String Expected = "Program Settings";
					
			if(Actual.equals(Expected))
			{
	
				System.out.println("Success !! Title is correct for Program Settings.");
				Reporter.log("Success !! Title is correct for Program Settings.");
			
			}
			else
			{
			
				System.out.println("Failure !! Title is NOT correct for Program Settings." + "\n" + "Expected : "  										+ "\n" + Expected + "\n" + 
										 "Actual: " + "\n" +  Actual);
				Reporter.log("Failure !! Title is NOT correct for Program Settings." + "\n" + "Expected : "  
						+ "\n" + Expected + "\n" + 
						 "Actual: " + "\n" +  Actual);
						
				AssertFailedCount++;
			
			}		  		
				
			
					
		System.out.println("Step 5 : Set Enable RSS Feed and Verify Success Message for save changes");
		Reporter.log("Step 5 : Set Enable RSS Feed and Verify Success Message for save changes");
						
						
			programsettings.SelectRSSFeedOption("Enabled");
		  	programsettings.ClickOnSaveChanges();
			Thread.sleep(8000);

			Actual = programsettings.Success.getText().trim();
			Expected = "Program settings saved.";
	
		System.out.println("Step 1 : Navigate to Register-Ed home page :" + Constants.APPLICATIONURL_RegisterEd);
		Reporter.log("Step 1 : Navigate to Register-Ed home page: " + Constants.APPLICATIONURL_RegisterEd); 
						
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd);
			Thread.sleep(5000);

		System.out.println("Step 2 : Select state from home page");
		Reporter.log("Step 2 : Select state from home page");
						
			homepage.ClickOnMenu();
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("linkText", "Colorado");
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("css", "#primary_content .span3 a");
			Thread.sleep(5000);

		System.out.println("Step 2 : Click on RSS link and verify RSS Feed is displaying");
		Reporter.log("Step 2 : Click on RSS link and verify RSS Feed is displaying");
		
			SeleniumFunc.ClickOnElement("linkText", "RSS");
			Thread.sleep(5000);

			Actual = driver.getCurrentUrl();
			Expected = Constants.APPLICATIONURL_RegisterEd + "/programs/colorado/180-colorado-test-program/page:1/limit:100.rss";
			
			//Verify Feed is displayed
			if(Actual.equals(Expected))
			{
				
				System.out.println("Success !! RSS Feed is displayed");
				Reporter.log("Success !! RSS Feed is displayed");
			
			}
			else
			{
			
				System.out.println("Failure !! RSS Feed is NOT displayed Actual : "+Actual + "Expected: "+Expected);
				Reporter.log("Failure !! RSS Feed is NOT displayed Actual : "+Actual + "Expected: "+Expected);
						
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
	 * Disabling the RSS and iCal feeds
	*/ 
	@Test
	private void DisableRSS_iCalFeeds() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Disabling the RSS and iCal feeds"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Disabling the RSS and iCal feeds"  + "\n" +
		 		 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		ProgramSettingsPage programsettings = new ProgramSettingsPage(driver);
		RegisterEdHomePage homepage = new RegisterEdHomePage(driver);

		System.out.println("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");				
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Select agency from agency listing");
		Reporter.log("Step 2 : Select agency from agency listing"); 
				
			//SeleniumFunc.ClickOnElement("linkText", "Demo Agency");			
			SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
			Thread.sleep(5000);

		
		System.out.println("Step 3 : Select a program and go to Program Settings");
		Reporter.log("Step 3 : Select a program and go to Program Settings"); 
			
			//SeleniumFunc.ClickOnElement("linkText", "Demo Boater Education Classroom Course");
		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);

		  	agencyhome.ClickOnProgramSettingsLink();
			Thread.sleep(5000);

		  	
		System.out.println("Step 4 : Verify Title for Program Settings");
		Reporter.log("Step 4 : Verify Title for Program Settings");
				
			String Actual = programsettings.Title.getText().trim();
			String Expected = "Program Settings";
					
			if(Actual.equals(Expected))
			{
				System.out.println("Success !! Title is correct for Program Settings.");
				Reporter.log("Success !! Title is correct for Program Settings.");		
			}
			else
			{		
				System.out.println("Failure !! Title is NOT correct for Program Settings." + "\n" + "Expected : "  					
										+ "\n" + Expected + "\n" + 
										"Actual: " + "\n" +  Actual);
				Reporter.log("Failure !! Title is NOT correct for Program Settings." + "\n" + "Expected : "  
						+ "\n" + Expected + "\n" + 
						 "Actual: " + "\n" +  Actual);
						
				AssertFailedCount++;			
			}		  		
				
			
					
		System.out.println("Step 5 : Disable RSS and iCal Feed and Verify Success Message for save changes");
		Reporter.log("Step 5 : Disable RSS and iCal Feed and Verify Success Message for save changes");
						
						
			programsettings.SelectRSSFeedOption("Disabled");
			programsettings.SelectiCalFeedOption("Disabled");
		  	programsettings.ClickOnSaveChanges();
			Thread.sleep(5000);

			Actual = programsettings.Success.getText().trim();
			Expected = "Program settings saved.";
	
		System.out.println("Step 6 : Navigate to Register-Ed home page :" + Constants.APPLICATIONURL_RegisterEd);
		Reporter.log("Step 6 : Navigate to Register-Ed home page: " + Constants.APPLICATIONURL_RegisterEd); 
				
				
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd);
			Thread.sleep(5000);
				
		System.out.println("Step 7 : Select state from home page");
		Reporter.log("Step 7 : Select state from home page");
			
				
			homepage.ClickOnMenu();
			Thread.sleep(5000);
			
			SeleniumFunc.ClickOnElement("linkText", "Colorado");
			//homepage.ClickAndGetValueOnStatesLink(6);
			Thread.sleep(5000);

			
			SeleniumFunc.ClickOnElement("css", "#primary_content > ul:nth-child(3) > li > div.row > div.span3.pull-right.program-actions > a");
			//SeleniumFunc.ClickOnElement("css", "#primary_content .span3 a");
			Thread.sleep(5000);

			
		System.out.println("Step 8 : Verify RSS link is disabled");
		Reporter.log("Step 8 : Verify RSS link is disabled");
		
						
			//Verify RSS Feed is disabled
			if(!SeleniumFunc.IsElementPresent("linkText", "RSS"))
			{
				
				System.out.println("Success !! RSS Feed is disabled properly");
				Reporter.log("Success !! RSS Feed is disabled properly");
			
			}
			else
			{
			
				System.out.println("Failure !! RSS Feed is NOT disabled");
				Reporter.log("Failure !! RSS Feed is NOT disabled");
						
				AssertFailedCount++;
			
			}	
			
			//SeleniumFunc.ClickOnElement("css", "#search_results div a");
			//Thread.sleep(5000);

			
			//Verify iCal Feed is disabled
			if(!SeleniumFunc.IsElementPresent("linkText", "Add to calendar"))
			{
				
				System.out.println("Success !! iCal Feed is disabled properly");
				Reporter.log("Success !! iCal Feed is disabled properly");
			
			}
			else
			{
			
				System.out.println("Failure !! iCal Feed is NOT disabled");
				Reporter.log("Failure !! iCal Feed is NOT disabled");
						
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
	 * Agency-Wide Event Schedule
	 * */ 
	@Test
	private void AgencyEventSchedule() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Agency-Wide Event Schedule "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Agency-Wide Event Schedule "  + "\n" +
				 	  "====");	
		
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegisterEdHomePage HomePage = new RegisterEdHomePage(driver);
		
		System.out.println("Step 1 : Navigate to Register-Ed home page :" + Constants.APPLICATIONURL_RegisterEd);
		Reporter.log("Step 1 : Navigate to Register-Ed home page: " + Constants.APPLICATIONURL_RegisterEd); 
					
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd);
			Thread.sleep(5000);	
				
		System.out.println("Step 1 : Go to agency schedule page");
		Reporter.log("Step 1 : Go to agency schedule page"); 		
		
		
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd + "/programs/california/schedule/agency:34");

			//Verifying presence of 'Upcoming Events'
			String expectedtext = "Upcoming Events";
			String actualtext = HomePage.Header.getText().trim();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success!! footer has 'View upcoming events' section i.e. " + actualtext);
				Reporter.log("Success!! footer has 'View upcoming events' section i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !!  footer doesn't have 'View upcoming events' section  " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! footer doesn't have 'View upcoming events' section " + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			//Verifying presence of Program Name
			expectedtext = "California Department of Fish & Wildlife";
			actualtext = SeleniumFunc.GetElementText("css", ".program-name");
			
			if(actualtext.contains(expectedtext) )
			{
				System.out.println("Success!! Correct header is present i.e. " + actualtext);
				Reporter.log("Success!! Correct header is present i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect Header " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect Header " + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			//Verifying presence of Program Summary
			expectedtext = "Overview" + "\n" + "This is the agency description!";
			actualtext = SeleniumFunc.GetElementText("css", ".program-summary");
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success!! Correct header is present i.e. " + actualtext);
				Reporter.log("Success!! Correct header is present i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Incorrect Header " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect Header " + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			//Click on Active Programs link at top
			SeleniumFunc.ClickOnElement("css", ".back-nav.back-nav-global>a>span");
			Thread.sleep(4000);
			
			//Verifying presence of 'Active Programs'
			expectedtext = "Active Programs";
			actualtext = HomePage.Header.getText().trim();
			
			if(actualtext.contains(expectedtext) )
			{
				System.out.println("Success!! Correct header is present i.e. " + actualtext);
				Reporter.log("Success!! Correct header is present i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !!  Incorrect Header " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect Header " + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			
		
			//Verifying presence of Logo
			
			if(SeleniumFunc.IsElementPresent("css", ".division-logo"))
			{
				System.out.println("Success!! Logo is present");
				Reporter.log("Success!! Logo is present"); 
			}
			else
			{
				System.out.println("Failure !! Logo is missing");
				Reporter.log("Failure !! Logo is missing");
				
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
