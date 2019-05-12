package products.CM;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.BypassPaymentPage;
import pageFactory.CM.CreateNewCertificationPage;
import pageFactory.CM.CreateNewUserPage;
import pageFactory.CM.LoginPage;
import pageFactory.CM.OrderReplacementPage;
import pageFactory.CM.OrderSummaryPage;
import pageFactory.CM.RolesPermissionsPage;
import pageFactory.CM.SearchCertificationPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class BypassPaymentTest 
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
	 * Verify 'Bypass payment' button is displayed Run
     */ 
	
	@Test
	private void BypassButton() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify 'Bypass payment' button is displayed Run"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify 'Bypass payment' button is displayed Run"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		OrderReplacementPage replace = new OrderReplacementPage(driver);
		BypassPaymentPage bypasspay = new BypassPaymentPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		
		RolesPermissionsPage role=new RolesPermissionsPage(driver);  	 		
		CreateNewUserPage  newuser=new CreateNewUserPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername("sanjeetk@clariontechnologies.co.in");
			login.EnterPassword("clarion@123");
			login.ClickOnLogInButton();
		
			//Due to role permission bypass button is not present
					newuser.ClickOnusername();   
			      	role.ClickOnMyProfile();  	
			    	Thread.sleep(2000);
			    	role.ClickOnEditInfo();
			    	Thread.sleep(2000);	
			    	if(!role.ByPassPament.isSelected())
					{
						role.ClickOnByPassPament();
					}
					newuser.EnterUserPasswardtext("clarion@123");
					newuser.EnterConfirmPasswardtext("clarion@123");
					Thread.sleep(1000);
					newuser.ClickOnSubmitBtnButton();
					Thread.sleep(2000);
					newuser.ClickOnCM();
					Thread.sleep(2000);
					
		System.out.println("Step 3: Adding new ceritificate");
		Reporter.log("Step 3: Adding new ceritificate"); 
			
			certi.ClickOnNewCertificate();
			Thread.sleep(2000);
			String firstname= "certificate" + JH.GenerateRandomNumber();
			System.out.println(firstname);
			String emailprefix = "certificate" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 PROTON RD";
		
			newcerti.CreateNewCertificate(firstname, "Testing", "01/24/1990", emailaddress, "United States",  streetaddress,"DALLAS", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			newcerti.ClickOnContinueButton();
			Thread.sleep(5000);
			
			
		System.out.println("Step 4 : Go to order replacement and verify Bypass Payment button is present");
		Reporter.log("Step 4 : Go to order replacement and verify Bypass Payment button is present");
			
			
		
			replace.ClickOnOrderReplacementButton();
			Thread.sleep(1000);
			if(SeleniumFunc.IsElementPresent(bypasspay.ByPassPayment))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Bypass Payment button is present");
				Reporter.log("Success !! Bypass Payment button is present"); 
			}
			else
			{
				System.out.println("Failure !! Bypass Payment button is missing");
				Reporter.log("Failure !! Bypass Payment button is missing");
				
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
	 * Verify 'Bypass payment' page is displayed Run

     */ 
	
	@Test
	private void RedirectBypassPage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify 'Bypass payment' page is displayed Run"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify 'Bypass payment' page is displayed Run"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		OrderReplacementPage replace = new OrderReplacementPage(driver);
		BypassPaymentPage bypasspay = new BypassPaymentPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		
		RolesPermissionsPage role=new RolesPermissionsPage(driver);  	 		
		CreateNewUserPage  newuser=new CreateNewUserPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername("sanjeetk@clariontechnologies.co.in");
			login.EnterPassword("clarion@123");
			login.ClickOnLogInButton();
			//Due to role permission bypass button is not present
			newuser.ClickOnusername();   
	      	role.ClickOnMyProfile();  	
	    	Thread.sleep(2000);
	    	role.ClickOnEditInfo();
	    	Thread.sleep(2000);	
	    	if(!role.ByPassPament.isSelected())
			{
				role.ClickOnByPassPament();
			}
			newuser.EnterUserPasswardtext("clarion@123");
			newuser.EnterConfirmPasswardtext("clarion@123");
			Thread.sleep(1000);
			newuser.ClickOnSubmitBtnButton();
			Thread.sleep(2000);
			newuser.ClickOnCM();
			Thread.sleep(2000);
			
		System.out.println("Step 3: Adding new ceritificate");
		Reporter.log("Step 3: Adding new ceritificate"); 
			
			certi.ClickOnNewCertificate();
			Thread.sleep(2000);
			String firstname= "certificate" + JH.GenerateRandomNumber();
			System.out.println(firstname);
			String emailprefix = "certificate" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 PROTON RD";
		
			newcerti.CreateNewCertificate(firstname, "Testing", "01/24/1990", emailaddress, "United States",  streetaddress,"DALLAS", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			newcerti.ClickOnContinueButton();
			Thread.sleep(5000);
			
			
		System.out.println("Step 4 : Click on Bypass Payment button & verify bypass payment page is displayed");
		Reporter.log("Step 4 : Click on Bypass Payment button & verify bypass payment page is displayed");
			
			
		
			replace.ClickOnOrderReplacementButton();
			Thread.sleep(1000);
			bypasspay.ClickOnByPassPaymentButton();
			Thread.sleep(1000);
			String ExpectedText = "Order Replacement Certificate";
			Thread.sleep(1000);
			String ActualText = bypasspay.Header.getText().trim();
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Bypass Payment page is displayed. i.e. " + ExpectedText);
				Reporter.log("Success !! Bypass Payment page is displayed. i.e. " + ExpectedText); 
			}
			else
			{
				System.out.println("Failure !! NOT redirected to Bypass Payment page. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! NOT redirected to Bypass Payment page. i.e." + "\n" + "Expected : "  
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
	 * Verify Reason for bypassing payment section Run
     */ 
	
	@Test
	private void SelectReason() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify Reason for bypassing payment section Run"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify Reason for bypassing payment section Run"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		OrderReplacementPage replace = new OrderReplacementPage(driver);
		BypassPaymentPage bypasspay = new BypassPaymentPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		
		RolesPermissionsPage role=new RolesPermissionsPage(driver);  	 		
		CreateNewUserPage  newuser=new CreateNewUserPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername("sanjeetk@clariontechnologies.co.in");
			login.EnterPassword("clarion@123");
			login.ClickOnLogInButton();
			//Due to role permission bypass button is not present
			newuser.ClickOnusername();   
	      	role.ClickOnMyProfile();  	
	    	Thread.sleep(2000);
	    	role.ClickOnEditInfo();
	    	Thread.sleep(2000);	
	    	if(!role.ByPassPament.isSelected())
			{
				role.ClickOnByPassPament();
			}
			newuser.EnterUserPasswardtext("clarion@123");
			newuser.EnterConfirmPasswardtext("clarion@123");
			Thread.sleep(1000);
			newuser.ClickOnSubmitBtnButton();
			Thread.sleep(2000);
			newuser.ClickOnCM();
			Thread.sleep(2000);
			
		System.out.println("Step 3: Adding new ceritificate");
		Reporter.log("Step 3: Adding new ceritificate"); 
			
			certi.ClickOnNewCertificate();
			Thread.sleep(2000);
			String firstname= "certificate" + JH.GenerateRandomNumber();
			System.out.println(firstname);
			String emailprefix = "certificate" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 PROTON RD";
		
			newcerti.CreateNewCertificate(firstname, "Testing", "01/24/1990", emailaddress, "United States",  streetaddress,"DALLAS", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			newcerti.ClickOnContinueButton();
			Thread.sleep(5000);
			
			
		System.out.println("Step 4 : Click on Bypass Payment button & verify user can select Reason");
		Reporter.log("Step 4 : Click on Bypass Payment button & verify user can select Reason");
			
			
		
			replace.ClickOnOrderReplacementButton();
			Thread.sleep(2000);
			bypasspay.ClickOnByPassPaymentButton();
			Thread.sleep(2000);
			bypasspay.SelectReason("Bypass Requested by State");
			
			
			String ExpectedText = "Bypass Requested by State";
			String ActualText = SeleniumFunc.GetSelectedValueFromDropdownList("css", "#historical_action_type_id");
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Reason option is selected. i.e. " + ExpectedText);
				Reporter.log("Success !! Reason option is selected. i.e. " + ExpectedText); 
			}
			else
			{
				System.out.println("Failure !! Option is NOT selected. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Option is NOT selected. i.e." + "\n" + "Expected : "  
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
	 * Verify 'Bypass payment' confirmation page
     */ 
	
	@Test
	private void UI_BypassConfirmation() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify Reason for bypassing payment section Run"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify Reason for bypassing payment section Run"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		OrderReplacementPage replace = new OrderReplacementPage(driver);
		BypassPaymentPage bypasspay = new BypassPaymentPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		
		OrderSummaryPage summary = new OrderSummaryPage(driver);
		RolesPermissionsPage role=new RolesPermissionsPage(driver);  	 		
		CreateNewUserPage  newuser=new CreateNewUserPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername("sanjeetk@clariontechnologies.co.in");
			login.EnterPassword("clarion@123");
			login.ClickOnLogInButton();
			//Due to role permission bypass button is not present
			newuser.ClickOnusername();   
	      	role.ClickOnMyProfile();  	
	    	Thread.sleep(2000);
	    	role.ClickOnEditInfo();
	    	Thread.sleep(2000);	
	    	if(!role.ByPassPament.isSelected())
			{
				role.ClickOnByPassPament();
			}
			newuser.EnterUserPasswardtext("clarion@123");
			newuser.EnterConfirmPasswardtext("clarion@123");
			Thread.sleep(1000);
			newuser.ClickOnSubmitBtnButton();
			Thread.sleep(2000);
			newuser.ClickOnCM();
			Thread.sleep(2000);
			
		System.out.println("Step 3: Adding new ceritificate");
		Reporter.log("Step 3: Adding new ceritificate"); 
			
			certi.ClickOnNewCertificate();
			Thread.sleep(2000);
			String firstname= "certificate" + JH.GenerateRandomNumber();
			System.out.println(firstname);
			String emailprefix = "certificate" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 PROTON RD";
		
			newcerti.CreateNewCertificate(firstname, "Testing", "01/24/1990", emailaddress, "United States",  streetaddress,"DALLAS", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			newcerti.ClickOnContinueButton();
			Thread.sleep(5000);
			
			
		System.out.println("Step 4 : Click on Bypass Payment button & verify user can select Reason");
		Reporter.log("Step 4 : Click on Bypass Payment button & verify user can select Reason");
			
			
		
			replace.ClickOnOrderReplacementButton();
			Thread.sleep(1000);
			bypasspay.ClickOnByPassPaymentButton();
			Thread.sleep(1000);
			bypasspay.SelectReason("Bypass Requested by State");
			Thread.sleep(1000);
			bypasspay.EnterDetailReason("Ke-Testing");
			bypasspay.ClickOnSubmitButton();
			Thread.sleep(1000);
			String ExpectedText = "Payment has been bypassed for this certification order.";
			String ActualText = summary.Success.getText().trim();
			
			if(ActualText.contains(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Payment Bypassed successfully. i.e. " + ExpectedText);
				Reporter.log("Success !! Payment Bypassed successfully. i.e. " + ExpectedText); 
			}
			else
			{
				System.out.println("Failure !! Error while payment bypass. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Error while payment bypass. i.e." + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual : " + "\n" +  ActualText);
				
				AssertFailedCount++;
			}

			
			//Return to record link
			
			if(SeleniumFunc.IsElementPresent(summary.ReturnToRecord))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Return to record link is present");
				Reporter.log("Success !! Return to record link is present"); 
			}
			else
			{
				System.out.println("Failure !! Return to record link is missing");
				Reporter.log("Failure !! Return to record link is missing");
				
				AssertFailedCount++;
			}
			
			//Search another link
			if(SeleniumFunc.IsElementPresent(summary.SearchAnother))
			{
				System.out.println("Success !! Search another link is present");
				Reporter.log("Success !! Search another link is present"); 
			}
			else
			{
				System.out.println("Failure !! Search another link is missing");
				Reporter.log("Failure !! Search another link is missing");
				
				AssertFailedCount++;
			}
			
			
			//Download PDF button
			if(SeleniumFunc.IsElementPresent(summary.PDF))
			{
				
				System.out.println("Success !! Download PDF button is present");
				Reporter.log("Success !! Download PDF button is present"); 
			}
			else
			{
				System.out.println("Failure !! Download PDF button is missing");
				Reporter.log("Failure !! Download PDF button is missing");
				
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