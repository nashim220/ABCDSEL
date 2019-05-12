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
import pageFactory.EM.RegisterEdEventListingPage;
import pageFactory.EM.RegisterEdEventView;
import pageFactory.EM.RegisterEdHomePage;



import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;
 
public class EventListingTest 
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
	 * Verify user is redirected to Event list screen from active programs page
	 */ 
	@Test
	private void EventListingFromActivePrograms() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify user is redirected to Event list screen from active programs page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify user is redirected to Event list screen from active programs page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegisterEdHomePage HomePage = new RegisterEdHomePage(driver);

		System.out.println("Step 1 : Go to register-ed and Verify Event Name and Other Event Page details");
		Reporter.log("Step 1 : Go to register-ed and Verify Event Name and Other Event Page details"); 
			
			
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

		
		System.out.println("Step 2 : From home page select state as Colorado");
		Reporter.log("Step 2 : From home page select state as Colorado"); 
				

			HomePage.ClickOnMenu();
			Thread.sleep(5000);

			HomePage.ClickAndGetValueOnStatesLink(6);
			Thread.sleep(5000);

			
			//	Verifying presence of 'View upcoming events'
			
			String ExpectedText = "Active Programs";
			String ActualText = HomePage.Header.getText().trim();
			
			if(ActualText.contains(ExpectedText) )
			{
				System.out.println("Success !! Correct header is present i.e. " + ActualText);
				Reporter.log("Success !! Correct header is present i.e. " + ActualText); 
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
			
			SeleniumFunc.ClickOnElement("css", "#primary_content div:nth-of-type(2) a");
			Thread.sleep(5000);

			
			
			//Verifying presence of 'Upcoming Events'
			
			ExpectedText = "Upcoming Events";
			ActualText = HomePage.Header.getText().trim();
					
			if(ActualText.contains(ExpectedText) )
			{
				System.out.println("Success !! Correct header is present i.e. " + ActualText);
				Reporter.log("Success !! Correct header is present i.e. " + ActualText); 
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
				
			
			//Verify presence of filters
			if(SeleniumFunc.IsElementPresent("css", "#filter_location"))
			{
					
				System.out.println("Success !! Event Filter is present");
				Reporter.log("Success !! Event Filter is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Event Filter is not present");
				Reporter.log("Failure !! Event Filter is not present"); 
					
				AssertFailedCount++;
					
			}
			
			
			//Verify presence of Header
			if(SeleniumFunc.IsElementPresent("css", ".division-bd"))
			{
					
				System.out.println("Success !! Header is present");
				Reporter.log("Success !! Header is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Event Filter is not present");
				Reporter.log("Failure !! Event Filter is not present"); 
					
				AssertFailedCount++;
					
			}
			
			
			//Verify presence of footer
			if(SeleniumFunc.IsElementPresent("css", ".row.kalkomey-info"))
			{
					
				System.out.println("Success !! Event Filter is present");
				Reporter.log("Success !! Event Filter is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Event Filter is not present");
				Reporter.log("Failure !! Event Filter is not present"); 
					
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
	 * Verify search panel is present along with values for zip code search
	 */ 
	@Test
	private void VerifySearchPanel() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify search panel is present along with values for zip code search"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify search panel is present along with values for zip code search"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegisterEdHomePage HomePage = new RegisterEdHomePage(driver);
		RegisterEdEventListingPage eventlisting = new RegisterEdEventListingPage(driver);
		
		System.out.println("Step 1 : Go to register-ed and Verify Event Name and Other Event Page details");
		Reporter.log("Step 1 : Go to register-ed and Verify Event Name and Other Event Page details"); 
			
			
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

		
		System.out.println("Step 2 : From home page select state as Colorado");
		Reporter.log("Step 2 : From home page select state as Colorado"); 
				

			HomePage.ClickOnMenu();
			Thread.sleep(5000);

			HomePage.ClickAndGetValueOnStatesLink(6);
			Thread.sleep(5000);

/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			SeleniumFunc.ClickOnElement("css", "#primary_content div:nth-of-type(2) a");
			Thread.sleep(5000);

			
			
			//Verifying presence of 'Upcoming Events'
			
			String ExpectedText = "Upcoming Events";
			String ActualText = HomePage.Header.getText().trim();
					
			if(ActualText.contains(ExpectedText) )
			{
				System.out.println("Success !! Correct header is present i.e. " + ActualText);
				Reporter.log("Success !! Correct header is present i.e. " + ActualText); 
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
				
			
			//Verify presence of filters
			if(SeleniumFunc.IsElementPresent("css", "#filter_location"))
			{
					
				System.out.println("Success !! Event Filter is present");
				Reporter.log("Success !! Event Filter is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Event Filter is not present");
				Reporter.log("Failure !! Event Filter is not present"); 
					
				AssertFailedCount++;
					
			}
			
			Thread.sleep(5000);
			//Verify search header
			ExpectedText = "Find events near you";
			ActualText = SeleniumFunc.GetElementText("css", ".location-filter-hd");
					
			if(ActualText.contains(ExpectedText) )
			{
				System.out.println("Success !! Correct header is present i.e. " + ActualText);
				Reporter.log("Success !! Correct header is present i.e. " + ActualText); 
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
			
			
			//Verify presence of Geo location search
			if(SeleniumFunc.IsElementPresent("css", "#geolocation_ctrl"))
			{
					
				System.out.println("Success !! Geo location search is present");
				Reporter.log("Success !! Geo location search is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Geo location search is not present");
				Reporter.log("Failure !! Geo location search is not present"); 
					
				AssertFailedCount++;
					
			}
			
			
			//Verify presence of Zip search
			if(SeleniumFunc.IsElementPresent("css", "#zip_toggle"))
			{
					
				System.out.println("Success !! Zip search is present");
				Reporter.log("Success !! Zip search is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Zip search is not present");
				Reporter.log("Failure !! Zip search is not present"); 
					
				AssertFailedCount++;
					
			}
			
			
			eventlisting.ClickOnZipCodeButton();
			Thread.sleep(5000);

			eventlisting.EnterSearchData("12345");
			Thread.sleep(2000);
			
			
			
			//Verify Drop-down options
		    String[] dropdownvalues = {"within 10 miles","within 50 miles","within 75 miles","within 200 miles"};

			SeleniumFunc.VerifyDropDownOptions("id", "eventDistance", dropdownvalues);
			Thread.sleep(5000);

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
	 * Verify the functionality of remove location link
	 */ 
	@Test
	private void RemoveLocationLink() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify the functionality of remove location link"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify the functionality of remove location link"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegisterEdHomePage HomePage = new RegisterEdHomePage(driver);
		RegisterEdEventListingPage eventlisting = new RegisterEdEventListingPage(driver);
		
		System.out.println("Step 1 : Go to register-ed and Verify Event Name and Other Event Page details");
		Reporter.log("Step 1 : Go to register-ed and Verify Event Name and Other Event Page details"); 
			
			
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

		
		System.out.println("Step 2 : From home page select state as Colorado");
		Reporter.log("Step 2 : From home page select state as Colorado"); 
				

			HomePage.ClickOnMenu();
			Thread.sleep(5000);

			HomePage.ClickAndGetValueOnStatesLink(6);
			Thread.sleep(5000);

			
			SeleniumFunc.ClickOnElement("css", "#primary_content div:nth-of-type(2) a");
			Thread.sleep(5000);
			
			
			//Verifying presence of 'Upcoming Events'
			
			String ExpectedText = "Upcoming Events";
			String ActualText = HomePage.Header.getText().trim();
					
			if(ActualText.contains(ExpectedText) )
			{
				System.out.println("Success !! Correct header is present i.e. " + ActualText);
				Reporter.log("Success !! Correct header is present i.e. " + ActualText); 
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
				
			
			//Verify presence of filters
			if(SeleniumFunc.IsElementPresent("css", "#filter_location"))
			{
					
				System.out.println("Success !! Event Filter is present");
				Reporter.log("Success !! Event Filter is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Event Filter is not present");
				Reporter.log("Failure !! Event Filter is not present"); 
					
				AssertFailedCount++;
					
			}
			
			Thread.sleep(5000);
			
			
			
			//Verify presence of Zip search
			if(SeleniumFunc.IsElementPresent("css", "#zip_toggle"))
			{
					
				System.out.println("Success !! Zip search is present");
				Reporter.log("Success !! Zip search is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Zip search is not present");
				Reporter.log("Failure !! Zip search is not present"); 
					
				AssertFailedCount++;
					
			}
			
			
			eventlisting.ClickOnZipCodeButton();
			Thread.sleep(5000);

			eventlisting.EnterSearchData("12345");
			Thread.sleep(2000);
			
			//Click on Remove Location
			eventlisting.ClickOnRemoveLocation();			Thread.sleep(5000);

			eventlisting.ClickOnZipCodeButton();			Thread.sleep(5000);


			//Verify User input for zip code is removed
			ActualText = SeleniumFunc.GetAttributeValue("css", "#eventZip", "value");
			if(ActualText.equals(""))
			{
				
				System.out.println("Success !! Remove Location link functionality is working properly");
				Reporter.log("Success !! Remove Location link functionality is working properly"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Remove Location link functionality is NOT working properly");
				Reporter.log("Failure !! Remove Location link functionality is NOT working properly"); 
					
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
	

	/* Test 4: 
	 * Verify user can select EBook & selected Ebook is appearing at Register-Ed
	 */ 
	@Test
	private void ChangeEbook() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 4 : Verify user can select EBook & selected Ebook is appearing at Register-Ed"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			  "Test 4 : Verify user can select EBook & selected Ebook is appearing at Register-Ed"  + "\n" +
			 	  "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		RegisterEdHomePage HomePage = new RegisterEdHomePage(driver);
		ProgramSettingsPage programsettings = new ProgramSettingsPage(driver);

	
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


		
		System.out.println("Step 4 : Select EBook for cross sell and verify at register-ed");
		Reporter.log("Step 4 : Select EBook for cross sell and verify at register-ed"); 
			
			programsettings.SelectEBookCrossSell("Boat America Student Manual");
			programsettings.ClickOnSaveChanges();
			Thread.sleep(5000);

			String ActualText = programsettings.Success.getText().trim();
			String ExpectedText = "Program settings saved.";
				
			if(ActualText.equals(ExpectedText))
			{
			
				System.out.println("Success !! Program Settings Saved.");
				Reporter.log("Success !! Program Settings Saved.");
		
			}
			else
			{
			
				System.out.println("Failure !! Program Settings is NOT Saved." + "\n" + "Expected : " 
										+ "\n" + ExpectedText + "\n" + 
										 "Actual: " + "\n" +  ActualText);
				Reporter.log("Failure !! Program Settings is NOT Saved." + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual: " + "\n" +  ActualText);
					
				AssertFailedCount++;
			
			}	
			
			
/*			if(SeleniumFunc.IsElementPresent(errorpage.Error))
			{
				System.out.println("Failure !! Error on page");
				AssertFailedCount++;
			}
*/			
			
			//Go to Register-Ed
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

		System.out.println("Step 5 : From home page select state as Colorado");
		Reporter.log("Step 5 : From home page select state as Colorado"); 
				

			HomePage.ClickOnMenu();
			Thread.sleep(5000);

			HomePage.ClickAndGetValueOnStatesLink(7);
			Thread.sleep(5000);

			
			SeleniumFunc.ClickOnElement("css", "#primary_content ul:nth-of-type(1) div:nth-of-type(2) a");
			Thread.sleep(5000);
			
			
			//Verifying presence of 'Upcoming Events'
			
			ExpectedText = "Upcoming Events";
			ActualText = HomePage.Header.getText().trim();
					
			if(ActualText.contains(ExpectedText) )
			{
			
				System.out.println("Success !! Correct header is present i.e. " + ActualText);
				Reporter.log("Success !! Correct header is present i.e. " + ActualText); 
			
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
				
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/programs/colorado/98-colorado-hunter-education-course");
			//SeleniumFunc.ClickOnElement("css", "#primary_content > ul:nth-child(3) > li > div.row > div.span3.pull-right.program-actions > a");
			//Verify Selected EBook is present for cross-sell
			Thread.sleep(2000);
			ExpectedText = SeleniumFunc.GetElementText("css", ".cross-sell-heading"); 
			ActualText	=	"Boat America Student Manual";
			
			if(ActualText.contains(ExpectedText) )
			{
			
				System.out.println("Success !! Correct Ebook is present for cross sell i.e. " + ActualText);
				Reporter.log("Success !! Correct Ebook is present for cross sell i.e. " + ActualText); 
			
			}
			else
			{
			
				System.out.println("Failure !!  Incorrect Ebook " + "\n" + "Expected Text: "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual Text : " + "\n" +  ActualText);
				Reporter.log("Failure !! Incorrect Ebook " + "\n" + "Expected Text: "  
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
	 * Verify user can enter state specific message & entered message is appearing at Register-Ed
	 */ 
	@Test
	private void StateSpecificMessaging() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 5 : Verify user can enter state specific message & entered message is appearing at Register-Ed"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			  "Test 5 : Verify user can enter state specific message & entered message is appearing at Register-Ed"  + "\n" +
			 	  "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		RegisterEdHomePage HomePage = new RegisterEdHomePage(driver);
		ProgramSettingsPage programsettings = new ProgramSettingsPage(driver);

	
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

		
		System.out.println("Step 4 : Enter state specific message and verify at register-ed");
		Reporter.log("Step 4 : Enter state specific message and verify at register-ed"); 
			
			String Original = SeleniumFunc.GetAttributeValue("css", "#introtext", "value");
			
			String Message = "At this course students will complete the traditional course in a classroom setting that includes lectures, hands-on activities and videos";
			programsettings.EnterStateSpecificMessage(Message);
			programsettings.ClickOnSaveChanges();
			Thread.sleep(5000);

			String ActualText = programsettings.Success.getText().trim();
			String ExpectedText = "Program settings saved.";
				
			if(ActualText.equals(ExpectedText))
			{
			
				System.out.println("Success !! Program Settings Saved.");
				Reporter.log("Success !! Program Settings Saved.");
		
			}
			else
			{
			
				System.out.println("Failure !! Program Settings is NOT Saved." + "\n" + "Expected : " 
										+ "\n" + ExpectedText + "\n" + 
										 "Actual: " + "\n" +  ActualText);
				Reporter.log("Failure !! Program Settings is NOT Saved." + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual: " + "\n" +  ActualText);
					
				AssertFailedCount++;
			
			}	
			
			//Go to Register-Ed
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd);
			Thread.sleep(5000);
		
		System.out.println("Step 5 : From home page select state as Colorado");
		Reporter.log("Step 5 : From home page select state as Colorado"); 
				
			HomePage.ClickOnMenu();
			Thread.sleep(5000);

			HomePage.ClickAndGetValueOnStatesLink(7);
			Thread.sleep(5000);
	
			SeleniumFunc.ClickOnElement("css", "#primary_content div:nth-of-type(2) a");
			Thread.sleep(5000);
	
			//Verifying presence of 'Upcoming Events'
			
			ExpectedText = "Upcoming Events";
			ActualText = HomePage.Header.getText().trim();
					
			if(ActualText.contains(ExpectedText) )
			{
			
				System.out.println("Success !! Correct header is present i.e. " + ActualText);
				Reporter.log("Success !! Correct header is present i.e. " + ActualText); 
			
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
				
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd +"/programs/colorado/98-colorado-hunter-education-course");
			
			Thread.sleep(2000);
			//Verify state specific message
			ExpectedText = SeleniumFunc.GetElementText("css", "#search_results div"); 
						
			if(Message.equalsIgnoreCase(ExpectedText) )
			{
			
				System.out.println("Success !! Correct State Specific message is displayed. i.e. " + Message);
				Reporter.log("Success !! Correct State Specific message is displayed. i.e. " + Message); 
			
			}
			else
			{
			
				System.out.println("Failure !!  Incorrect Message " + "\n" + "Expected Text: "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual Text : " + "\n" +  Message);
				Reporter.log("Failure !! Incorrect Message " + "\n" + "Expected Text: "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual Text : " + "\n" +  Message);
						
				AssertFailedCount++;
		
			}
			
			
			//Go to EM and Set original message
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/eventtemplate/programconfigure");
			Thread.sleep(5000);

			programsettings.EnterStateSpecificMessage(Original);
			programsettings.ClickOnSaveChanges();
			Thread.sleep(5000);

			
			
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
	 * Verify event details at Register-Ed
	 */ 
	@Test
	private void VerifyEventDetails() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify event details at Register-Ed"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Verify event details at Register-Ed"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegisterEdHomePage HomePage = new RegisterEdHomePage(driver);
		RegisterEdEventListingPage eventlisting = new RegisterEdEventListingPage(driver);
		RegisterEdEventView eventview = new RegisterEdEventView(driver);

		System.out.println("Step 1 : Go to register-ed and Verify Event Name and Other Event Page details");
		Reporter.log("Step 1 : Go to register-ed and Verify Event Name and Other Event Page details"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd);
			Thread.sleep(5000);
			
		System.out.println("Step 2 : From home page select state as Pennsylvania");
		Reporter.log("Step 2 : From home page select state as Pennsylvania"); 
				

			HomePage.ClickOnMenu();
			Thread.sleep(5000);
			
			SeleniumFunc.ClickOnElement("linkText", "Pennsylvania");
			//HomePage.ClickAndGetValueOnStatesLink(5);
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("css", "#primary_content > ul:nth-child(1) > li > div.row > div.span3.pull-right.program-actions > a");
			//SeleniumFunc.ClickOnElement("css", "#primary_content div:nth-of-type(2) a");
			Thread.sleep(5000);
			
			
			//Verifying presence of 'Upcoming Events'
			
			String ExpectedText = "Upcoming Events";//Upcoming Events
			String ActualText = HomePage.Header.getText().trim();
					
			if(ActualText.contains(ExpectedText) )
			{
				System.out.println("Success !! Correct header is present i.e. " + ActualText);
				Reporter.log("Success !! Correct header is present i.e. " + ActualText); 
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
				
			
			//Verify presence of Date and Day icon
			if(SeleniumFunc.IsElementPresent("css", ".date-tile.l-shadowbox"))
			{
					
				System.out.println("Success !! Date and Day icon is present");
				Reporter.log("Success !! Date and Day icon is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Date and Day icon is NOT present");
				Reporter.log("Failure !! Date and Day icon is NOT present"); 
					
				AssertFailedCount++;
					
			}
			
			
			
			
			//Verify presence of Event Title
			if(SeleniumFunc.IsElementPresent("css", ".event-summary-title a"))
			{
					
				System.out.println("Success !! Event Title is present");
				Reporter.log("Success !! Event Title is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Event Title is not present");
				Reporter.log("Failure !! Event Title is not present"); 
					
				AssertFailedCount++;
					
			}
			
			
			//Verify presence of Event Status
			if(SeleniumFunc.IsElementPresent("css", ".status-description"))
			{
					
				System.out.println("Success !! Event Status is present");
				Reporter.log("Success !! Event Status is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Event Status is not present");
				Reporter.log("Failure !! Event Status is not present"); 
					
				AssertFailedCount++;
					
			}
			
			
			//Verify presence of Seat Status
			if(SeleniumFunc.IsElementPresent("css", ".status-seats"))
			{
					
				System.out.println("Success !! Seat Status is present");
				Reporter.log("Success !! Seat Status is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Seat Status is not present");
				Reporter.log("Failure !! Seat Status is not present"); 
					
				AssertFailedCount++;
					
			}
			
			
			//Verify presence of Location & Schedule
			if(SeleniumFunc.IsElementPresent("css", ".event-summary-subhead"))
			{
					
				System.out.println("Success !! Location & Schedule is present");
				Reporter.log("Success !! Location & Schedule is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Location & Schedule is not present");
				Reporter.log("Failure !! Location & Schedule is not present"); 
					
				AssertFailedCount++;
					
			}
			
			
			//Verify presence of View Event button
			if(SeleniumFunc.IsElementPresent("css", ".btn.btn-large.pull-right.hidden-phone"))
			{
					
				System.out.println("Success !! View Event button is present");
				Reporter.log("Success !! View Event button is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! View Event button is not present");
				Reporter.log("Failure !! View Event button is not present"); 
					
				AssertFailedCount++;
					
			}
			
			Thread.sleep(5000);
			eventlisting.ClickOnViewEventButton();
			Thread.sleep(5000);

			
			
			try {
				
				//Verify control re-directed to event details
				ExpectedText = "Register Now";
				ActualText = eventview.RegisterNow.getText().trim();
				
				if(ActualText.contains(ExpectedText))
				{
				
					System.out.println("Success !! Control is re-directed to the event details page");
					Reporter.log("Success !! Control is re-directed to the event details page");
				
				}
				else
				{
				
					System.out.println("Failure !! Control is NOT re-directed to the event details page" + "\n" + "Expected : "  
							+ "\n" + ExpectedText + "\n" +  
							 "Actual: " + "\n" +  ActualText);
					Reporter.log("Failure !! Control is NOT re-directed to the event details page" + "\n" + "Expected : "  
							+ "\n" + ExpectedText + "\n" +  
							 "Actual: " + "\n" +  ActualText);
				
					AssertFailedCount++;
				
				}
				
				//Verify presence of Event Date & Time
				if(SeleniumFunc.IsElementPresent("css", "#primary_content div:nth-of-type(2) tbody tr"))
				{
						
					System.out.println("Success !! Event Date & Time is present");
					Reporter.log("Success !! Event Date & Time is present"); 
					
				}
				else
				{
						
					System.out.println("Failure !! Event Date & Time is not present");
					Reporter.log("Failure !! Event Date & Time is not present"); 
						
					AssertFailedCount++;
						
				}
				
				
				//Verify presence of Location
				
				if(SeleniumFunc.IsElementPresent("css", "#primary_content div:nth-of-type(2) div:nth-of-type(2)"))
				{
						
					System.out.println("Success !! Location is present");
					Reporter.log("Success !! Location is present"); 
					
				}
				else
				{
						
					System.out.println("Failure !! Location is not present");
					Reporter.log("Failure !! Location is not present"); 
						
					AssertFailedCount++;
						
				}
				
			} 
			catch (Exception e) 
			{
				//If Registration Closed for event
				ExpectedText = "Registration Closed";
				ActualText=eventview.RegisterClosed.getText().trim();
				
				if(ActualText.contains(ExpectedText))
				{
				
					System.out.println("Success !! Control is re-directed to the event details page");
					Reporter.log("Success !! Control is re-directed to the event details page");
				
				}
				else
				{
				
					System.out.println("Failure !! Control is NOT re-directed to the event details page" + "\n" + "Expected : "  
							+ "\n" + ExpectedText + "\n" +  
							 "Actual: " + "\n" +  ActualText);
					Reporter.log("Failure !! Control is NOT re-directed to the event details page" + "\n" + "Expected : "  
							+ "\n" + ExpectedText + "\n" +  
							 "Actual: " + "\n" +  ActualText);
				
					AssertFailedCount++;
				
				}
				
				//Verify presence of Event Date & Time
				if(SeleniumFunc.IsElementPresent("css", "#primary_content div:nth-of-type(3) tbody tr"))
				{
						
					System.out.println("Success !! Event Date & Time is present");
					Reporter.log("Success !! Event Date & Time is present"); 
					
				}
				else
				{
						
					System.out.println("Failure !! Event Date & Time is not present");
					Reporter.log("Failure !! Event Date & Time is not present"); 
						
					AssertFailedCount++;
						
				}
				
				
				//Verify presence of Location
				if(SeleniumFunc.IsElementPresent("css", "#primary_content div:nth-of-type(3) div:nth-of-type(2)"))
				{
						
					System.out.println("Success !! Location is present");
					Reporter.log("Success !! Location is present"); 
					
				}
				else
				{
						
					System.out.println("Failure !! Location is not present");
					Reporter.log("Failure !! Location is not present"); 
						
					AssertFailedCount++;
						
				}
			}
	
			
			
			//Click on Upcoming event link and verify control is redirected to Upcoming Events list
			
			SeleniumFunc.ClickOnElement("css", ".back-nav.back-nav-global a span");
			Thread.sleep(5000);

			ExpectedText = "Upcoming Events";
			ActualText = HomePage.Header.getText().trim();
					
			if(ActualText.contains(ExpectedText) )
			{
				System.out.println("Success !! Control is redirected to Upcoming Events list");
				Reporter.log("Success !! Control is redirected to Upcoming Events list"); 
			}
			else
			{
				System.out.println("Failure !! Control is NOT redirected to Upcoming Events list");
				Reporter.log("Failure !! Control is NOT redirected to Upcoming Events list");
						
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
	 * Verify Pagination is working properly
	 */
	@Test
	private void VerifyPagination() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Verify Pagination is working properly"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Verify Pagination is working properly"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegisterEdHomePage HomePage = new RegisterEdHomePage(driver);

		System.out.println("Step 1 : Go to register-ed and Verify Event Name and Other Event Page details");
		Reporter.log("Step 1 : Go to register-ed and Verify Event Name and Other Event Page details"); 
				
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd);
			Thread.sleep(5000);
		
		System.out.println("Step 2 : From home page select state as california");
		Reporter.log("Step 2 : From home page select state as california"); 
				
			HomePage.ClickOnMenu();
			Thread.sleep(5000);

			HomePage.ClickAndGetValueOnStatesLink(5);
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("css", "#primary_content div:nth-of-type(2) a");	
			Thread.sleep(5000);

			//Verifying presence of 'Upcoming Events'
			
			String ExpectedText = "Upcoming Events";
			String ActualText = HomePage.Header.getText().trim();
					
			if(ActualText.contains(ExpectedText) )
			{
				
				System.out.println("Success !! Correct header is present i.e. " + ActualText);
				Reporter.log("Success !! Correct header is present i.e. " + ActualText); 
			
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
			
			//Verify presence of Pagination
			if(SeleniumFunc.IsElementPresent("css", ".pagination.pull-right"))
			{
					
				System.out.println("Success !! Pagination is present");
				Reporter.log("Success !! Pagination is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Pagination is not present");
				Reporter.log("Failure !! Pagination is not present"); 
					
				AssertFailedCount++;
					
			}
					
			//Verify presence of Previous Link
			if(SeleniumFunc.IsElementPresent("css", ".prev.disabled"))
			{
					
				System.out.println("Success !! Previous Link is present");
				Reporter.log("Success !! Previous Link is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Previous Link is not present");
				Reporter.log("Failure !! Previous Link is not present"); 
					
				AssertFailedCount++;
					
			}
			
			//Verify presence of Next Link
			if(SeleniumFunc.IsElementPresent("css", ".next a"))
			{
					
				System.out.println("Success !! Next Link is present");
				Reporter.log("Success !! Next Link is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Next Link is not present");
				Reporter.log("Failure !! Next Link is not present"); 
					
				AssertFailedCount++;
					
			}
			
			//Verify presence of Last Link
			if(SeleniumFunc.IsElementPresent("css", "div.pagination a[rel='last']"))
			{
					
				System.out.println("Success !! Last Link is present");
				Reporter.log("Success !! Last Link is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Last Link is not present");
				Reporter.log("Failure !! Last Link is not present"); 
					
				AssertFailedCount++;
					
			}
			
			
			//Go to one of the page by clicking on page number
			
			SeleniumFunc.ClickOnElement("css", "div.pagination li:nth-of-type(3) a");
			
			Thread.sleep(5000);
			
			ExpectedText = Constants.APPLICATIONURL_RegisterEd +
							"/programs/california/160-traditional-hunter-education/page:2";
			ActualText = driver.getCurrentUrl();
			System.out.println(ActualText);
			System.out.println(ExpectedText);
			if(ActualText.equals(ExpectedText))
			{
				
				System.out.println("Success !! Pagination is working properly");
				Reporter.log("Success !! Pagination is working properly"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Pagination is NOT working");
				Reporter.log("Failure !! Pagination is NOT working"); 
					
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
		

	/* Test 8: 
	 * Verify functionality of 'Use my current location' button
	 */ 	
	@Test
	private void VerifyUseMyCurrentLocationButton() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Verify functionality of 'Use my current location' button"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Verify functionality of 'Use my current location' button"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegisterEdHomePage HomePage = new RegisterEdHomePage(driver);

		System.out.println("Step 1 : Go to register-ed and Verify Event Name and Other Event Page details");
		Reporter.log("Step 1 : Go to register-ed and Verify Event Name and Other Event Page details"); 
			
			
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

			
		System.out.println("Step 2 : From home page select state as Colorado and navigate to 'Upcoming Events' page");
		Reporter.log("Step 2 : From home page select state as Colorado and navigate to 'Upcoming Events' page"); 
				
			HomePage.ClickOnMenu();
			Thread.sleep(5000);

			HomePage.ClickAndGetValueOnStatesLink(6);
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("css", "#primary_content div:nth-of-type(2) a");
			Thread.sleep(5000);

			
		System.out.println("Step 3 : Click on 'Use my current location' button and Verifying that 'location' section is shown" );
		Reporter.log("Step 3 : Click on 'Use my current location' button and Verifying that 'location' section is shown");
		
			//Click on 'Use my current location' button
			SeleniumFunc.ClickOnElement("id", "geolocation_ctrl");
			Thread.sleep(2000);
		
			//Verifying that 'location' section is shown
			String expectedtext = "height: auto;";
			String actualtext = SeleniumFunc.GetAttributeValue("id", "event_location_outer", "style").trim();
			
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
				
				System.out.println("Success !! 'location' section is shown");
				Reporter.log("Success !! 'location' section is shown");
			
			}
			else
			{
			
				System.out.println("Failure !!'location' section is not shown" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! 'location' section is not shown" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}
			
			
			
		System.out.println("Step 4 : Click on 'remove location' link and Verifying that 'location' section is hidden" );
		Reporter.log("Step 4 : Click on 'remove location' link and Verifying that 'location' section is hidden");
		
			
			//Click on 'remove location' link
			SeleniumFunc.ClickOnElement("id", "remove_ctrl");
			Thread.sleep(2000);
	
			//Verifying that 'location' section is hidden
			expectedtext = "height: 0px;";
			actualtext = SeleniumFunc.GetAttributeValue("id", "event_location_outer", "style").trim();
			
			if(actualtext.equalsIgnoreCase(expectedtext))
			{
				
				System.out.println("Success !! 'location' section is hidden");
				Reporter.log("Success !! 'location' section is hidden");
			
			}
			else
			{
			
				System.out.println("Failure !!'physical address' section is not hidden" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! 'physical address' section is not hidden" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
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
