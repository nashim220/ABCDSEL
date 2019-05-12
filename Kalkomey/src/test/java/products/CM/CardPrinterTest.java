package products.CM;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.CardPrinterPage;
import pageFactory.CM.CertificationDetailsPage;
import pageFactory.CM.CreateNewCertificationPage;
import pageFactory.CM.EditCertificationPage;
import pageFactory.CM.GlobalHeader;
import pageFactory.CM.ILMCCertificationPage;
import pageFactory.CM.ILMCHomePage;
import pageFactory.CM.ILMCOrderPage;
import pageFactory.CM.ILMCSearchPage;
import pageFactory.CM.LoginPage;
import pageFactory.CM.OrderReplacementPage;
import pageFactory.CM.SearchCertificationPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class CardPrinterTest 
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
	 * The new print queue should be only available to KE users.
	*/ 
	
	@Test
	private void PrintQueueForKalUsers() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : The new print queue should be only available to KE users."  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : The new print queue should be only available to KE users."  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		CardPrinterPage cardprinter = new CardPrinterPage(driver);
		
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Card printer option is present for Kalkomey user, and can redirect to page");
		Reporter.log("Step 2 : Card printer option is present for Kalkomey user, and can redirect to page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(1000);
			if(SeleniumFunc.IsElementPresent(cardprinter.CardPrinterTab))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Card Printer tab is present");
				Reporter.log("Success !! Card Printer tab is present"); 
			
			}
			else
			{
			
				System.out.println("Failure !! Card Printer tab is NOT present");
				Reporter.log("Failure !! Card Printer tab is NOT present "); 
				
				AssertFailedCount++;
		
			}
		
			Thread.sleep(1000);
			cardprinter.ClickOnCardPrinterTab();
			Thread.sleep(1000);
			//Verifying page header
			String actualtext= cardprinter.Header.getText().trim();			
			String expectedtext= "Pending Card Printer Requests";
			Thread.sleep(1000);
			
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Control is redirected to Card Printer page. i.e. " + expectedtext);
				Reporter.log("Success !! Control is redirected to Card Printer page. i.e. " + expectedtext); 
			}
			else
			{
				System.out.println("Failure !! Control is NOT redirected to Card Printer page. i.e." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !! Control is NOT redirected to Card Printer page. i.e." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
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
	 * User should not be able to view card printer, if an agency user.
	*/ 
	
	@Test
	private void NoPrintQueueForAgencyUsers() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : User should not be able to view card printer, if an agency user."  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : User should not be able to view card printer, if an agency user."  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		CardPrinterPage cardprinter = new CardPrinterPage(driver);
		
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Card printer option is present for Kalkomey user, and can redirect to page");
		Reporter.log("Step 2 : Card printer option is present for Kalkomey user, and can redirect to page"); 	
		
			login.EnterUsername(Constants.CM_Username_Verginia);
			login.EnterPassword(Constants.CM_Username_Verginia);
			login.ClickOnLogInButton();
			Thread.sleep(1000);
			if(!SeleniumFunc.IsElementPresent(cardprinter.CardPrinterTab))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Card Printer tab is NOT present");
				Reporter.log("Success !! Card Printer tab is NOT present"); 
			
			}
			else
			{
			
				System.out.println("Failure !! Card Printer tab is present");
				Reporter.log("Failure !! Card Printer tab is present "); 
				
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
	 * Verify multiple orders (same card) do not put multiple requests
	*/ 
	
	@Test
	private void MultipleOrderSingleRequestForSameCard() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify multiple orders (same card) do not put multiple requests"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify multiple orders (same card) do not put multiple requests"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		 SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		OrderReplacementPage replace = new OrderReplacementPage(driver);
		CardPrinterPage cardprinter = new CardPrinterPage(driver);

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
		    System.out.println(firstname);
			createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas", "PA", "Hunter-Trapper Education (1986 - Present)", "Pass", "03/02/2015", "04/02/2015");
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
			
			String URL = driver.getCurrentUrl();
			Thread.sleep(1000);
			replace.ClickOnOrderReplacementButton();
			Thread.sleep(2000);
			replace.FillBillingDetails("4111111111111111", "12-2025","7047983705");
			replace.ClickOnSubmitButton();
			Thread.sleep(4000);
			
			//Order second certificate
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(3000);
			replace.ClickOnOrderReplacementButton();
			Thread.sleep(3000);
			replace.FillBillingDetails("4111111111111111", "12-2025","7047983705");
			replace.ClickOnSubmitButton();
			Thread.sleep(4000);
	
			
		System.out.println("Step 4 : Verify single request is present for multiple orders for same card");
		Reporter.log("Step 4 : Verify single request is present for multiple orders for same card"); 	

		
			cardprinter.ClickOnCardPrinterTab();
			Thread.sleep(3000);
		
			int request=driver.findElements(By.xpath("//a[contains(text(),'testing, "+firstname+"')]")).size();
			Thread.sleep(2000);
		//	System.out.println(request);
			if(request==1)
			{
				Thread.sleep(2000);
				System.out.println("Success !! Single request is present for multiple orders for same card");
				Reporter.log("Success !! Single request is present for multiple orders for same card"); 
			}
			else
			{
				System.out.println("Failure !! Multiple request is present for multiple orders for same card");
				Reporter.log("Failure !! Multiple request is present for multiple orders for same card");
				
				AssertFailedCount++;
			}
			
			//Verify QTY
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[contains(text(),'testing, "+firstname+"')]")).click();
			Thread.sleep(2000);
			actualtext = cardprinter.QTY.getText().trim();
			Thread.sleep(3000);
			expectedtext = "2";
			Thread.sleep(1000);
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Multiple orders are present under single request for same card.  i.e. " + actualtext);
				Reporter.log("Success !! Multiple orders are present under single request for same card.  i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Multiple requests are present for multiple order for same card" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							"Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !! Multiple requests are present for multiple order for same card" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						"Actual : " + "\n" +  actualtext);
			
				AssertFailedCount++;
			}
			
			Thread.sleep(1000);
			//Cancel request
			cardprinter.CancelRequest.click();
			Thread.sleep(1000);
			SeleniumFunc.AcceptAlertAndGetText();
			Thread.sleep(1000);
			
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
	 * Verify that User can remove a record from the print queue.
	*/ 

	@Test
	private void CancelRequest() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify that User can remove a record from the print queue."  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify that User can remove a record from the print queue."  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		OrderReplacementPage replace = new OrderReplacementPage(driver);
		CardPrinterPage cardprinter = new CardPrinterPage(driver);

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
			Thread.sleep(1000);
			 System.out.println(firstname);
			createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas", "PA", "Hunter-Trapper Education (1986 - Present)", "Pass", "03/02/2015", "04/02/2015");
			 createnewcerti.ClickOnContinueButton();
			Thread.sleep(10000);
			
			//Verifying alert message
			String actualtext= gheader.Success_AlertText.getText().trim();
			String expectedtext= "Certification was successfully created."; 
			Thread.sleep(1000);
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
			
			Thread.sleep(1000);
			replace.ClickOnOrderReplacementButton();
			Thread.sleep(1000);
			replace.FillBillingDetails("4111111111111111", "12-2025","7047983705");
			replace.ClickOnSubmitButton();
			Thread.sleep(4000);
			
		System.out.println("Step 4 : Verifu user can Cancel a Request for card print");
		Reporter.log("Step 4 : Verifu user can Cancel a Request for card print"); 	

		
			cardprinter.ClickOnCardPrinterTab();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[contains(text(),'testing, "+firstname+"')]")).click();
			Thread.sleep(2000);
			//Cancel request
			cardprinter.CancelRequest.click();
			Thread.sleep(1000);
			SeleniumFunc.AcceptAlertAndGetText();
			Thread.sleep(1000);
		//Verify Success Message
			
			actualtext = cardprinter.DeleteSuccess.getText().trim();
			expectedtext = "Card Request deleted!";
			Thread.sleep(1000);
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Card Request deleted successfully.  i.e. " + actualtext);
				Reporter.log("Success !! Card Request deleted successfully.  i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Card Request is NOT deleted" + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							"Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !! Card Request is NOT deleted" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						"Actual : " + "\n" +  actualtext);
			
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
	 * Verify that Details should be properly displayed on details for print request
	*/ 
	
	@Test
	private void DetailsPrintRequest() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify that Details should be properly displayed on details for print request"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify that Details should be properly displayed on details for print request"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		OrderReplacementPage replace = new OrderReplacementPage(driver);
		CardPrinterPage cardprinter = new CardPrinterPage(driver);

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
			Thread.sleep(1000);
			 System.out.println(firstname);
			createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas", "PA", "Hunter-Trapper Education (1986 - Present)", "Pass", "03/02/2015", "04/02/2015");
			 createnewcerti.ClickOnContinueButton();
			Thread.sleep(10000);
			
			//Verifying alert message
			String actualtext= gheader.Success_AlertText.getText().trim();
			String expectedtext= "Certification was successfully created."; 
			Thread.sleep(1000);
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
			
			Thread.sleep(1000);
			replace.ClickOnOrderReplacementButton();
			Thread.sleep(1000);
			replace.FillBillingDetails("4111111111111111", "12-2025","7047983705");
			replace.ClickOnSubmitButton();
			Thread.sleep(4000);
			
			
			
		System.out.println("Step 3 : Verify details for Card Print Request");
		Reporter.log("Step 3 : Verify details for Card Print Request"); 
		
		
				cardprinter.ClickOnCardPrinterTab();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[contains(text(),'testing, "+firstname+"')]")).click();
				Thread.sleep(2000);
				//Card Printer Information
				if(SeleniumFunc.IsElementPresent(cardprinter.CardPriterSection))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Card Printer Information is present");
					Reporter.log("Success !! Card Printer Information is present"); 
		
				}
				else
				{
		
					System.out.println("Failure !! Card Printer Information is NOT present");
					Reporter.log("Failure !! Card Printer Information is NOT present "); 
			
					AssertFailedCount++;
	
				}
			
			
			//Physical Address			
			if(SeleniumFunc.IsElementPresent(cardprinter.PhyAddSection))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Physical Address section is present");
				Reporter.log("Success !! Physical Address section is present"); 
		
			}
			else
			{
		
				System.out.println("Failure !! Physical Address section is NOT present");
				Reporter.log("Failure !! Physical Address section is NOT present "); 
			
				AssertFailedCount++;
	
			}
		
		
			//Mailing Address
			if(SeleniumFunc.IsElementPresent(cardprinter.MailingAddSection))
			{
					
				System.out.println("Success !! Mailing Address section is present");
				Reporter.log("Success !! Mailing Address section is present"); 
				
			}
			else
			{
				
				System.out.println("Failure !! Mailing Address section is NOT present");
				Reporter.log("Failure !! Mailing Address section is NOT present "); 
					
				AssertFailedCount++;
		
			}
		
		
			//Card Preview
			if(SeleniumFunc.IsElementPresent(cardprinter.CardPreview))
			{
				Thread.sleep(1000);		
				System.out.println("Success !! Card Preview section is present");
				Reporter.log("Success !! Card Preview section is present"); 
						
			}
			else
			{
				Thread.sleep(1000);	
				System.out.println("Failure !! Card Preview section is NOT present");
				Reporter.log("Failure !! Card Preview section is NOT present "); 
							
				AssertFailedCount++;
				
			}
				
		
			//Card Information
			if(SeleniumFunc.IsElementPresent(cardprinter.CardInfo))
			{
							
				System.out.println("Success !! Card Information section is present");
				Reporter.log("Success !! Card Information section is present"); 
						
			}
			else
			{
						
				System.out.println("Failure !! Card Information section is NOT present");
				Reporter.log("Failure !! Card Information section is NOT present "); 
							
				AssertFailedCount++;
				
			}
			
			Thread.sleep(1000);
			//Delete Request
			cardprinter.CancelRequest.click();
			Thread.sleep(1000);
			SeleniumFunc.AcceptAlertAndGetText();
			Thread.sleep(1000);
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
	 * Verify editing a record in CM (even if it’s already in the print queue), the edits will be present immediately in the queue
	*/ 
	
	@Test
	private void UpdateRecord() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify editing a record in CM (even if it’s already in the print queue), the edits will be present immediately in the queuet"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Verify editing a record in CM (even if it’s already in the print queue), the edits will be present immediately in the queue"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		OrderReplacementPage replace = new OrderReplacementPage(driver);
		CardPrinterPage cardprinter = new CardPrinterPage(driver);
		CertificationDetailsPage certidetails = new CertificationDetailsPage(driver);
		EditCertificationPage editcerti = new EditCertificationPage(driver);
		
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
			Thread.sleep(1000);
			 System.out.println(firstname);
			createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas", "PA", "Hunter-Trapper Education (1986 - Present)", "Pass", "03/02/2015", "04/02/2015");
			 createnewcerti.ClickOnContinueButton();
			Thread.sleep(10000);
			
			//Verifying alert message
			String actualtext1= gheader.Success_AlertText.getText().trim();
			String expectedtext1= "Certification was successfully created."; 
			
			if(actualtext1.equals(expectedtext1))
			{
				System.out.println("Success !! alert message is displayed i.e. " + actualtext1);
				Reporter.log("Success !! alert message is displayed i.e. " + actualtext1); 
			}
			else
			{
				System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext1 + "\n" + 
									 "Actual : " + "\n" +  actualtext1);
				Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  
						+ "\n" + expectedtext1 + "\n" + 
						 "Actual : " + "\n" +  actualtext1);
				
				AssertFailedCount++;
			}
			
			Thread.sleep(1000);
			replace.ClickOnOrderReplacementButton();
			Thread.sleep(1000);
			replace.FillBillingDetails("4111111111111111", "12-2025","7047983705");
			replace.ClickOnSubmitButton();
			Thread.sleep(4000);
			
		System.out.println("Step 4 : Verify details before records update");
		Reporter.log("Step 4 : Verify details before records update"); 	
		
			cardprinter.ClickOnCardPrinterTab();
			Thread.sleep(1000);
			String actualtext2 = driver.findElement(By.xpath("//a[contains(text(),'testing, "+firstname+"')]")).getText().trim();
			String expectedtext2 = "testing, " + firstname; 
			
			if(actualtext2.toLowerCase().contains(expectedtext2))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Name is displayed properly. i.e. " + actualtext2);
				Reporter.log("Success !! Name is displayed properly. i.e. " + actualtext2); 
			}
			else
			{
				System.out.println("Failure !! Wrong Name is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext2 + "\n" + 
									 "Actual : " + "\n" +  actualtext2);
				Reporter.log("Failure !!  Wrong Name is displayed" + "\n" + "Expected : "  
						+ "\n" + expectedtext2 + "\n" + 
						 "Actual : " + "\n" +  actualtext2);
				
				AssertFailedCount++;
			}
			

		System.out.println("Step 5 : Update the Name and verify updated name under Card Request");
		Reporter.log("Step 5 : Update the Name and verify updated name under Card Request"); 	
			
			 Thread.sleep(2000);
		    driver.findElement(By.xpath("//a[contains(text(),'testing, "+firstname+"')]")).click();
			Thread.sleep(1000);
			SeleniumFunc.ClickOnElement("css", ".btn.btn-small.btn-primary.btn-block.edit-info");
			Thread.sleep(2000);
			certidetails.ClickOnEditButton();
			Thread.sleep(3000);
		//	editcerti.EnterFirstname("Automation");
			editcerti.EnterLastname("User");
			Thread.sleep(1000);
			createnewcerti.Continue.sendKeys(Keys.ENTER);
			Thread.sleep(6000);
			//Go to Card Printer and verify details
			
			cardprinter.ClickOnCardPrinterTab();
			Thread.sleep(3000);
			String actualtext3 = driver.findElement(By.xpath("//a[contains(text(),'User, "+firstname+"')]")).getText().trim();
			String expectedtext3 = "User, " + firstname; 
			Thread.sleep(1000);
			if(actualtext3.equalsIgnoreCase(expectedtext3))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Displaying updated Name. i.e. " + actualtext3);
				Reporter.log("Success !! Displaying updated Name. i.e. " + actualtext3); 
			}
			else
			{
				System.out.println("Failure !! NOT Displaying updated Name" + "\n" + "Expected : "  
									+ "\n" + expectedtext3 + "\n" + 
									 "Actual : " + "\n" +  actualtext3);
				Reporter.log("Failure !! NOTl Displaying updated Name" + "\n" + "Expected : "  
						+ "\n" + expectedtext3 + "\n" + 
						 "Actual : " + "\n" +  actualtext3);
				
				AssertFailedCount++;
			}
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[contains(text(),'User, "+firstname+"')]")).click();
			Thread.sleep(3000);
			//Cancel request
			cardprinter.CancelRequest.click();
			Thread.sleep(1000);
			SeleniumFunc.AcceptAlertAndGetText();
			Thread.sleep(1000);
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
	 * Multiple orders between KPS and ILMC
	*/ 
	
	@Test
	private void OrdersBetweenKPS_ILMC() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 7 : Multiple orders between KPS and ILMC"  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			  "Test 7 : Multiple orders between KPS and ILMC"  + "\n" +
			 	  "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		OrderReplacementPage replace = new OrderReplacementPage(driver);
		CardPrinterPage cardprinter = new CardPrinterPage(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCCertificationPage certipage = new ILMCCertificationPage(driver);
		ILMCOrderPage orderpage = new ILMCOrderPage(driver);
	
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
			Thread.sleep(1000);
			 System.out.println(firstname);
			createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas", "PA", "Hunter-Trapper Education (1986 - Present)", "Pass", "03/02/2015", "04/02/2015");
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
		
			String URL = driver.getCurrentUrl();
			Thread.sleep(1000);
			replace.ClickOnOrderReplacementButton();
			Thread.sleep(1000);
			replace.FillBillingDetails("4111111111111111", "12-2025","7047983705");
			replace.ClickOnSubmitButton();
			Thread.sleep(4000);

		
		System.out.println("Step 4 : Verify single request is present for multiple orders for same card");
		Reporter.log("Step 4 : Verify single request is present for multiple orders for same card"); 	
		    
		   Thread.sleep(1000);
			cardprinter.ClickOnCardPrinterTab();
			Thread.sleep(3000);
		
			int request=driver.findElements(By.xpath("//a[contains(text(),'testing, "+firstname+"')]")).size();
			Thread.sleep(2000);
		//	System.out.println(request);
			if(request==1)
			{
				System.out.println("Success !! Single request is present for multiple orders for same card");
				Reporter.log("Success !! Single request is present for multiple orders for same card"); 
			}
			else
			{
				System.out.println("Failure !! Multiple request is present for multiple orders for same card");
				Reporter.log("Failure !! Multiple request is present for multiple orders for same card");
			
				AssertFailedCount++;
			}
		//Verify QTY
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[contains(text(),'testing, "+firstname+"')]")).click();
			Thread.sleep(2000);
			actualtext = cardprinter.QTY.getText().trim();
			Thread.sleep(3000);
			expectedtext = "1";
			Thread.sleep(1000);
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Multiple orders are present under single request for same card.  i.e. " + actualtext);
				Reporter.log("Success !! Multiple orders are present under single request for same card.  i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Multiple requests are present for multiple order for same card" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									"Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !! Multiple requests are present for multiple order for same card" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								"Actual : " + "\n" +  actualtext);
		
				AssertFailedCount++;
			}

		
		System.out.println("Step 5 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		Reporter.log("Step 5 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
	
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			Thread.sleep(1000);
		
		System.out.println("Step 6 : Select state and course and go to search page");
		Reporter.log("Step 6 : Select state and course and go to search page"); 
					
		
			//Select state and course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(1000);	
		
		System.out.println("Step 7 : Enter valid record and go to certification page");
		Reporter.log("Step 7 : Enter valid record and go to certification page"); 
	
			searchpage.EnterLastName("testing");
			searchpage.EnterFirstName(firstname);
			searchpage.SelectMonth("December");
			searchpage.EnterDay("28");
			searchpage.EnterYear("1989");
			searchpage.ClickOnSearchButton();
			Thread.sleep(1000);
			// go to order replacement page
			certipage.ClickOnConfirmCheckbox();
			certipage.ClickOnOrderButton();
		
		
		System.out.println("Step 8 : Verify user can place order successfully");
		Reporter.log("Step 8 : Verify user can place order successfully"); 
		
			orderpage.FillBillingDetails("4111111111111111", "02-2038", "Automation", "Testing", "rohit@gmail.com", "rohit@gmail.com");
			orderpage.ClickOnSameBillingCheckbox();
			Thread.sleep(2000);
			orderpage.ClickOnPlaceOrderButton();
			Thread.sleep(2000);
	
		System.out.println("Step 9 : Verify single request is present for multiple orders for same card");
		Reporter.log("Step 9 : Verify single request is present for multiple orders for same card"); 	

    		SeleniumFunc.ToGoToUrl(URL);
    		Thread.sleep(1000);
			cardprinter.ClickOnCardPrinterTab();
			Thread.sleep(3000);
		
			Thread.sleep(2000);
		//	System.out.println(request);
			if(request==1)
			{
    		    System.out.println("Success !! Single request is present for multiple orders for same card");
    			Reporter.log("Success !! Single request is present for multiple orders for same card"); 
    		}
    		else
    		{
    			System.out.println("Failure !! Multiple request is present for multiple orders for same card");
    			Reporter.log("Failure !! Multiple request is present for multiple orders for same card");
	
    			AssertFailedCount++;
    		}

    		
    		//Verify QTY
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[contains(text(),'testing, "+firstname+"')]")).click();
			Thread.sleep(2000);
			actualtext = cardprinter.QTY.getText().trim();
			Thread.sleep(3000);
			expectedtext = "2";
			Thread.sleep(1000);
			if(actualtext.equals(expectedtext))
			{
    			System.out.println("Success !! Multiple orders are present under single request for same card.  i.e. " + actualtext);
    			Reporter.log("Success !! Multiple orders are present under single request for same card.  i.e. " + actualtext); 
    		}
    		else
    		{
    			System.out.println("Failure !! Multiple requests are present for multiple order for same card" + "\n" + "Expected : "  
    						+ "\n" + expectedtext + "\n" + 
    						"Actual : " + "\n" +  actualtext);
    			Reporter.log("Failure !! Multiple requests are present for multiple order for same card" + "\n" + "Expected : "  
    						+ "\n" + expectedtext + "\n" + 
    						"Actual : " + "\n" +  actualtext);

    			AssertFailedCount++;
    		}

		
			//Cancel request
			cardprinter.CancelRequest.click();
			Thread.sleep(1000);
			SeleniumFunc.AcceptAlertAndGetText();
			Thread.sleep(1000);
    	
    	
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
	 * Verify that "Even if the card was ordered from ILMC, you may edit a cert in KPS and the print queue will show the edits immediately.
	*/ 
	
	@Test
	private void OrderILMCUpdateRecord() throws Exception
	{
		System.out.println("====" + "\n" +
				"Test 8 : Verify that Even if the card was ordered from ILMC, you may edit a cert in KPS and the print queue will show the edits immediately."  + "\n" +
	 			"====");
		Reporter.log("====" + "\n" +
	 			  "Test 8 : Verify that Even if the card was ordered from ILMC, you may edit a cert in KPS and the print queue will show the edits immediately."  + "\n" +
			 	  "====");	
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		CardPrinterPage cardprinter = new CardPrinterPage(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCCertificationPage certipage = new ILMCCertificationPage(driver);
		ILMCOrderPage orderpage = new ILMCOrderPage(driver);
		CertificationDetailsPage certidetails = new CertificationDetailsPage(driver);
		EditCertificationPage editcerti = new EditCertificationPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
		
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

		System.out.println("Step 2 : Login to application and navigating to User Create New Certification page");
		Reporter.log("Step 2 : Login to application and navigating to User Create New Certification page"); 	
	
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
		
			searchcerti.ClickOnNewCertificate();
			Thread.sleep(2000);
		
		System.out.println("Step 3 : Creating new Certificate");
		Reporter.log("Step 3 : Creating new Certificate"); 		
	
			String firstname= "certi" + JH.GenerateRandomNumber();
			String emailprefix = "certi" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 Proton Rd";
			 System.out.println(firstname);
			createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas", "PA", "Hunter-Trapper Education (1986 - Present)", "Pass", "03/02/2015", "04/02/2015");
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
			Thread.sleep(1000);
			String URL = driver.getCurrentUrl();
			Thread.sleep(1000);

		System.out.println("Step 4 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		Reporter.log("Step 4 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
	
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			Thread.sleep(1000);
		
		System.out.println("Step 5 : Select state and course and go to search page");
		Reporter.log("Step 5 : Select state and course and go to search page"); 
					
		
			//Select state and course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(1000);	
		
		System.out.println("Step 6 : Enter valid record and go to certification page");
		Reporter.log("Step 6 : Enter valid record and go to certification page"); 
	
		
			searchpage.EnterLastName("testing");
			searchpage.EnterFirstName(firstname);
			searchpage.SelectMonth("December");
			searchpage.EnterDay("28");
			searchpage.EnterYear("1989");
			searchpage.ClickOnSearchButton();
			Thread.sleep(3000);
			// go to order replacement page
			certipage.ClickOnConfirmCheckbox();
			Thread.sleep(1000);
			certipage.ClickOnOrderButton();
			Thread.sleep(1000);
		
		System.out.println("Step 7 : Verify user can place order successfully");
		Reporter.log("Step 7 : Verify user can place order successfully"); 
		
			orderpage.FillBillingDetails("4111111111111111", "02-2038", "Automation", "Testing", "rohit@gmail.com", "rohit@gmail.com");
			orderpage.ClickOnSameBillingCheckbox();
			Thread.sleep(2000);
			orderpage.ClickOnPlaceOrderButton();
			Thread.sleep(2000);
	
		System.out.println("Step 8 : Verify Print Request is present ");
		Reporter.log("Step 8 : Verify Print Request is present "); 	

    		SeleniumFunc.ToGoToUrl(URL);
    		Thread.sleep(1000);
    		cardprinter.ClickOnCardPrinterTab();
    		Thread.sleep(1000);
    		int request=driver.findElements(By.xpath("//a[contains(text(),'testing, "+firstname+"')]")).size();
			Thread.sleep(2000);
		//	System.out.println(request);
			if(request==1)
    		{
    			System.out.println("Success !! Single request is present for multiple orders for same card");
    			Reporter.log("Success !! Single request is present for multiple orders for same card"); 
    		}
    		else
    		{
    			System.out.println("Failure !! Multiple request is present for multiple orders for same card");
    			Reporter.log("Failure !! Multiple request is present for multiple orders for same card");
	
    			AssertFailedCount++;
    		}

    		
		System.out.println("Step 9 : Update the Name and verify updated name under Card Request");
    	Reporter.log("Step 9 : Update the Name and verify updated name under Card Request"); 	
    			
    			SeleniumFunc.ToGoToUrl(URL);
    			Thread.sleep(3000);
    			certidetails.ClickOnEditButton();
    			Thread.sleep(3000);
    		//	editcerti.EnterFirstname("Automation");
    			editcerti.EnterLastname("User");
    			Thread.sleep(1000);
    			createnewcerti.Continue.sendKeys(Keys.ENTER);
    			Thread.sleep(6000);
    			//Go to Card Printer and verify details
    			
    			cardprinter.ClickOnCardPrinterTab();
    			Thread.sleep(3000);
    			String actualtext3 = driver.findElement(By.xpath("//a[contains(text(),'User, "+firstname+"')]")).getText().trim();
    			String expectedtext3 = "User, " + firstname; 
    			Thread.sleep(1000);
    			if(actualtext3.equalsIgnoreCase(expectedtext3))
    			{
    				Thread.sleep(1000);
    				System.out.println("Success !! Displaying updated Name. i.e. " + actualtext3);
    				Reporter.log("Success !! Displaying updated Name. i.e. " + actualtext3); 
    			}
    			else
    			{
    				System.out.println("Failure !! NOT Displaying updated Name" + "\n" + "Expected : "  
    									+ "\n" + expectedtext3 + "\n" + 
    									 "Actual : " + "\n" +  actualtext3);
    				Reporter.log("Failure !! NOTl Displaying updated Name" + "\n" + "Expected : "  
    						+ "\n" + expectedtext3 + "\n" + 
    						 "Actual : " + "\n" +  actualtext3);
    				
    				AssertFailedCount++;
    			}
    			
    			Thread.sleep(2000);
    			driver.findElement(By.xpath("//a[contains(text(),'User, "+firstname+"')]")).click();
    			Thread.sleep(3000);
    			//Cancel request
    			cardprinter.CancelRequest.click();
    			Thread.sleep(1000);
    			SeleniumFunc.AcceptAlertAndGetText();
    			Thread.sleep(1000);
    		
    		
    	
    	
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
	 * Verify that cards ordered should show up at the bottom of the queue
	*/ 
	
	@Test
	private void OrderedCardAtBottom() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Verify that cards ordered should show up at the bottom of the queue"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 : Verify that cards ordered should show up at the bottom of the queue"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		OrderReplacementPage replace = new OrderReplacementPage(driver);
		CardPrinterPage cardprinter = new CardPrinterPage(driver);

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
			Thread.sleep(1000);
			 System.out.println(firstname);
			createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas", "PA", "Hunter-Trapper Education (1986 - Present)", "Pass", "03/02/2015", "04/02/2015");
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
			
			
			replace.ClickOnOrderReplacementButton();
			Thread.sleep(1000);
			replace.FillBillingDetails("4111111111111111", "12-2025","7047983705");
			replace.ClickOnSubmitButton();
			Thread.sleep(4000);
			
			cardprinter.ClickOnCardPrinterTab();
			Thread.sleep(2000);
			int request=driver.findElements(By.cssSelector(".span12>h4>a")).size();
			Thread.sleep(2000);
			System.out.println(request);
			
			String actualtext3= driver.findElement(By.xpath("//li["+request+"]/header/div/h4/a")).getText().trim();
			String expectedtext3= "testing, " + firstname; 
			Thread.sleep(1000);
			if(actualtext3.equalsIgnoreCase(expectedtext3))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Recent order is displaying at bottom. i.e. " + actualtext3);
				Reporter.log("Success !! Recent order is displaying at bottom. i.e. " + actualtext3); 
			}
			else
			{
				System.out.println("Failure !! Recent order is NOT displaying at bottom" + "\n" + "Expected : "  
									+ "\n" + expectedtext3 + "\n" + 
									 "Actual : " + "\n" +  actualtext3);
				Reporter.log("Failure !! Recent order is NOT displaying at bottom" + "\n" + "Expected : "  
									+ "\n" + expectedtext3 + "\n" + 
									"Actual : " + "\n" +  actualtext3);
				
				AssertFailedCount++;
			}
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//li["+request+"]/header/div/h4/a")).click();
			Thread.sleep(3000);
			//Cancel request
			cardprinter.CancelRequest.click();
			Thread.sleep(1000);
			SeleniumFunc.AcceptAlertAndGetText();
			Thread.sleep(1000);
			
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
