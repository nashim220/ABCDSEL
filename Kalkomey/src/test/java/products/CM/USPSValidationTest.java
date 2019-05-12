package products.CM;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.CreateNewCertificationPage;
import pageFactory.CM.GlobalHeader;
import pageFactory.CM.ILMCCertificationPage;
import pageFactory.CM.ILMCEditAddressPage;
import pageFactory.CM.ILMCHomePage;
import pageFactory.CM.ILMCSearchPage;
import pageFactory.CM.LoginPage;
import pageFactory.CM.SearchCertificationPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class USPSValidationTest 
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
	 * Verify ILMC USPS Address Validation
	*/ 
	@Test
	private void VerifyValidationEditAddress() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify ILMC USPS Address Validation"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify ILMC USPS Address Validation"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCCertificationPage certipage = new ILMCCertificationPage(driver);
		ILMCEditAddressPage editadd = new ILMCEditAddressPage(driver);
		
		
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
		searchpage.EnterDay("15");
		searchpage.EnterYear("1975");
		searchpage.SelectMonth("May");
		searchpage.ClickOnSearchButton();
		Thread.sleep(1000);
				
			//Go to edit address
			certipage.ClickOnEditAddressButton();
			Thread.sleep(2000);
			
			editadd.EnterZip("75243");
			Thread.sleep(1000);
			editadd.EnterZipPhy("75243");
			editadd.ClickOnContinueeditpage();
			Thread.sleep(2000);
			
			
			
			System.out.println("Step 4 : Verify USPS validation is displayed for mailing address");
			Reporter.log("Step 4 : Verify USPS validation is displayed for mailing address"); 
			
				String ExpectedText = "We had trouble verifying your mailing address. If it is incorrect, please correct it.";
				String ActualText = SeleniumFunc.GetElementText("css", "#mailing_address_check > h4.not-verified-message.text-warning").trim();
				//String ActualText = SeleniumFunc.GetElementText("css", "#mailing_address_check h4:nth-of-type(2)").trim();
				
				if(ActualText.equals(ExpectedText))
				{
					Thread.sleep(1000);
					System.out.println("Success !! USPS validation is displayed for mailing address. i.e. " +ExpectedText);
					Reporter.log("Success !! USPS validation is displayed for mailing address. i.e. " +ExpectedText);
				
				}
				else
				{
					Thread.sleep(1000);
					System.out.println("Failure !! USPS validation is NOT displayed for mailing address :" + ExpectedText + "\n"+
									"Actual :" +ActualText);
					Reporter.log("Failure !! USPS validation is NOT displayed for mailing address :" + ExpectedText + "\n"+
							"Actual :" +ActualText); 
				
					AssertFailedCount++;
				
				}
				Thread.sleep(3000);
				
				//Click on Use Suggested Address for mailing
				editadd.UseUSPSMailing.click();
				Thread.sleep(1000);
				editadd.ClickOnSaveButton();
				Thread.sleep(3000);
				
			//Verify mailing address is verified
			ExpectedText = "Your mailing address was successfully verified!";
			ActualText = editadd.USPSMailingSuccess.getText().trim();
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Mailing address was successfully verifieds. i.e. " +ExpectedText);
				Reporter.log("Success !! Mailing address was successfully verifieds. i.e. " +ExpectedText);
			
			}
			else
			{
				Thread.sleep(1000);
				System.out.println("Failure !!  Mailing address was NOT successfully verifieds." + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !!  Mailing address was NOT successfully verifieds." + ExpectedText + "\n"+
						"Actual :" +ActualText); 
			
				AssertFailedCount++;
			
			}
			
			
			System.out.println("Step 5 : Verify USPS validation is displayed for physical address");
			Reporter.log("Step 5 : Verify USPS validation is displayed for physical address"); 
			
				ExpectedText = "We had trouble verifying your physical address. Please select the one you want us to use.";
				ActualText = editadd.USPSPhyVal.getText().trim();
				Thread.sleep(1000);
				if(ActualText.equals(ExpectedText))
				{
					Thread.sleep(1000);
					System.out.println("Success !! USPS validation is displayed for physical address. i.e. " +ExpectedText);
					Reporter.log("Success !! USPS validation is displayed for physical address. i.e. " +ExpectedText);
				
				}
				else
				{
				
					System.out.println("Failure !! USPS validation is NOT displayed for physical address :" + ExpectedText + "\n"+
									"Actual :" +ActualText);
					Reporter.log("Failure !! USPS validation is NOT displayed for physical address :" + ExpectedText + "\n"+
							"Actual :" +ActualText); 
				
					AssertFailedCount++;
				
				}
				
				//Click on Use Suggested Address for mailing
				Thread.sleep(1000);
				editadd.UseUSPSPhyl.click();
				Thread.sleep(1000);
				editadd.ClickOnSaveButton();
				Thread.sleep(2000);
				certipage.ClickOnEditAddressButton();
				editadd.EnterZip("12345");
				editadd.EnterZipPhy("12345");
				Thread.sleep(2000);
				editadd.ClickOnContinueeditpage();
				Thread.sleep(2000);
				editadd.UseUSPSPhyl.click();
				Thread.sleep(2000);
				editadd.ClickOnSaveButton();
				Thread.sleep(2000);
				ExpectedText = "Your physical address was successfully verified!";
				ActualText = editadd.USPSPhySuccess.getText().trim();
				Thread.sleep(2000);
				if(ActualText.equals(ExpectedText))
				{
					Thread.sleep(2000);
					System.out.println("Success !! Physical address was successfully verifieds. i.e. " +ExpectedText);
					Reporter.log("Success !! Physical address was successfully verifieds. i.e. " +ExpectedText);
				
				}
				else
				{
					Thread.sleep(2000);
					System.out.println("Failure !!  Physical address was NOT successfully verifieds." + ExpectedText + "\n"+
									"Actual :" +ActualText);
					Reporter.log("Failure !!  Physical address was NOT successfully verifieds." + ExpectedText + "\n"+
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
	
	

	/* Test 2: 
	 * KPS USPS Address Verification for new certifications
	*/ 
	@Test
	private void USPSSuggestionCreateCertification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : KPS USPS Address Verification for new certifications"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
				"Test 2 : KPS USPS Address Verification for new certifications"  + "\n" +
	 			"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		ILMCEditAddressPage editadd = new ILMCEditAddressPage(driver);
		
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
			createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "12345", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			createnewcerti.ClickOnContinueButton();
			Thread.sleep(10000);
			
			
	System.out.println("Step 4 : Verify USPS validation is displayed for mailing address");
	Reporter.log("Step 4 : Verify USPS validation is displayed for mailing address"); 
			
			String ExpectedText = "We had trouble verifying your physical address. Please select the one you want us to use.";
			String ActualText = createnewcerti.USPSPhyVal.getText().trim();
			Thread.sleep(1000);
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! USPS validation is displayed for physical address. i.e. " +ExpectedText);
				Reporter.log("Success !! USPS validation is displayed for physical address. i.e. " +ExpectedText);
				
			}
			else
			{
				
				System.out.println("Failure !! USPS validation is NOT displayed for physical address :" + ExpectedText + "\n"+
									"Actual :" +ActualText);
				Reporter.log("Failure !! USPS validation is NOT displayed for physical address :" + ExpectedText + "\n"+
							"Actual :" +ActualText); 
				
				AssertFailedCount++;
				
			}
			
			Thread.sleep(1000);
			//Enter Mailing Address
			editadd.UseUSPSPhyl.click();
			Thread.sleep(2000);
			createnewcerti.ClickOnBackButton();
			Thread.sleep(3000);
			createnewcerti.AddressCheckbox.click();
			Thread.sleep(1000);
			createnewcerti.FillMailingAddress("United States", "14086 PROTON RD", "DALLAS", "12345", "Texas", "9898989589");
			createnewcerti.ClickOnContinueButton();
			Thread.sleep(1000);
			//Click on Use Suggested Address for mailing
			createnewcerti.UseUSPSMailing.click();
			Thread.sleep(1000);
			createnewcerti.ClickOnSaveButton();
			Thread.sleep(2000);
			
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
				Thread.sleep(1000);
				System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  
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

	
	/* Test 3: 
	 * KPS USPS Address verification for existing certs
	*/ 
	@Test
	private void USPSSuggestionEditCertification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : KPS USPS Address verification for existing certs"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : KPS USPS Address verification for existing certs"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		
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
			createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
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
			
			//Go to edit details
			SeleniumFunc.ClickOnElement("css",".btn.btn-small.pull-right.edit-info");
			 Thread.sleep(1000);
			createnewcerti.EnterZip("12345");
			 Thread.sleep(1000);
			createnewcerti.Continue.sendKeys(Keys.ENTER);
    	     Thread.sleep(2000);
    	     //Select USPS suggested address
    	System.out.println("Step 4 : Verify USPS validation is displayed for mailing address");
    	Reporter.log("Step 4 : Verify USPS validation is displayed for mailing address"); 
    				
    		String ExpectedText = "We had trouble verifying your physical address. Please select the one you want us to use.";
    		String ActualText = createnewcerti.USPSPhyVal.getText().trim();
    					
    		if(ActualText.equals(ExpectedText))
    		{
    			Thread.sleep(1000);	
    			System.out.println("Success !! USPS validation is displayed for physical address. i.e. " +ExpectedText);
    			Reporter.log("Success !! USPS validation is displayed for physical address. i.e. " +ExpectedText);
    				
    		}
    		else
    		{
    				
    			System.out.println("Failure !! USPS validation is NOT displayed for physical address :" + ExpectedText + "\n"+
    								"Actual :" +ActualText);
    			Reporter.log("Failure !! USPS validation is NOT displayed for physical address :" + ExpectedText + "\n"+
    							"Actual :" +ActualText); 
    					
    				AssertFailedCount++;
    					
    		}
    					
    		//Use Suggested Address
    		Thread.sleep(1000);
    		createnewcerti.UseUSPSPhyl.click();
    		Thread.sleep(1000);
    		createnewcerti.ClickOnBackButton();
			Thread.sleep(3000);
			createnewcerti.AddressCheckbox.click();
			Thread.sleep(1000);
			createnewcerti.FillMailingAddress("United States", "14086 PROTON RD", "DALLAS", "12345", "Texas", "9898989589");
			createnewcerti.ClickOnContinueButton();
			Thread.sleep(2000);
			//Click on Use Suggested Address for mailing
			createnewcerti.UseUSPSMailing.click();
			Thread.sleep(1000);
			createnewcerti.ClickOnSaveButton();
			Thread.sleep(1000);
    		//Verifying Success message
			actualtext= gheader.Success_AlertText.getText().trim();
			expectedtext= "Certification was successfully updated."; 
			
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Success message is displayed i.e. " + actualtext);
				Reporter.log("Success !! Success message is displayed i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Incorrect Success message is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect Success message is displayed" + "\n" + "Expected : "  
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


}