package products.CM;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.CollectionPage;
import pageFactory.CM.EditCertificationPage;
import pageFactory.CM.EditCollectionPage;
import pageFactory.CM.GlobalHeader;
import pageFactory.CM.ILMCCertificationPage;
import pageFactory.CM.ILMCEditAddressPage;
import pageFactory.CM.ILMCHomePage;
import pageFactory.CM.ILMCSearchPage;
import pageFactory.CM.LoginPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class ResidenceRestrictionTest 
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
	 * Test to make sure that you are able to update a Collection's "Residency Restriction?" record
     */ 
	
	@Test
	private void UpdateResidenctRestriction() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Test to make sure that you are able to update a Collection's Residency Restriction? record"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Test to make sure that you are able to update a Collection's Residency Restriction? record"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader header = new GlobalHeader(driver);
		CollectionPage collection = new CollectionPage(driver);
		EditCollectionPage editcollection = new EditCollectionPage(driver);

		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Collection page");
		Reporter.log("Step 2 : Login to application and navigating to Collection page"); 	
			
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(1000);	
			header.AdminDropdown.click();
			Thread.sleep(1000);	
			header.Admin_ManageCollection.click();
			Thread.sleep(1000);	

			collection.EnterCriteria("Hunter Safety Education (1959 - 1985)");
			collection.ClickOnSearchButton();
			Thread.sleep(1000);	
			collection.ClickOnFirstRecord();
			
			//Set Residency Restriction? as true
			editcollection.ClickOnResidencyTrue();
			Thread.sleep(1000);	
			//Save the changes
			editcollection.ClickOnSaveButton();
			Thread.sleep(1000);	
			//Success Message
			String ActualText= editcollection.Success.getText().trim();
			Thread.sleep(1000);	
			String ExpectedText= "Certification Collection was successfully updated.";

		
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);	
				System.out.println("Success !! Collection updated successfully. i.e. " + ActualText);
				Reporter.log("Success !! Collection updated successfully. i.e. " + ActualText); 
			
			}
			else
			{
				
				System.out.println("Failure !! Collection NOT updated. i.e." + "\n" + "Expected : "  
								+ "\n" + ExpectedText + "\n" + 
								 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Collection NOT updated. i.e." + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual : " + "\n" +  ActualText);
			
				AssertFailedCount++;
			}
			
			//Reset the changes
			editcollection.ClickOnResidencyFalse();
			editcollection.ClickOnSaveButton();
			
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
	 * Test to make sure that you are not able to change a student's physical or mailing address in ILMC
     */ 
	
	@Test
	private void ResidenctRestrictionAtILMC() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Test to make sure that you are not able to change a student's physical or mailing address in ILMC"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Test to make sure that you are not able to change a student's physical or mailing address in ILMC"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader header = new GlobalHeader(driver);
		CollectionPage collection = new CollectionPage(driver);
		EditCollectionPage editcollection = new EditCollectionPage(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCCertificationPage certipage = new ILMCCertificationPage(driver);
		ILMCEditAddressPage editadd = new ILMCEditAddressPage(driver);

		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Collection page");
		Reporter.log("Step 2 : Login to application and navigating to Collection page"); 	
			
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(1000);		
			header.AdminDropdown.click();
			Thread.sleep(1000);	
			header.Admin_ManageCollection.click();
			Thread.sleep(1000);	
			collection.EnterCriteria("Hunter-Trapper Education (1986 - Present)");
			collection.ClickOnSearchButton();
			Thread.sleep(1000);	
			collection.ClickOnFirstRecord();
			Thread.sleep(1000);	
			//Set Residency Restriction? as true
			editcollection.ClickOnResidencyTrue();
			Thread.sleep(1000);	
			//Save the changes
			editcollection.ClickOnSaveButton();
			Thread.sleep(1000);	
			//Success Message
			String ActualText= editcollection.Success.getText().trim();
			Thread.sleep(1000);	
			String ExpectedText= "Certification Collection was successfully updated.";

		
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);	
				System.out.println("Success !! Collection updated successfully. i.e. " + ActualText);
				Reporter.log("Success !! Collection updated successfully. i.e. " + ActualText); 
			
			}
			else
			{
				
				System.out.println("Failure !! Collection NOT updated. i.e." + "\n" + "Expected : "  
								+ "\n" + ExpectedText + "\n" + 
								 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Collection NOT updated. i.e." + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual : " + "\n" +  ActualText);
			
				AssertFailedCount++;
			}
			
			String URL = driver.getCurrentUrl();
			Thread.sleep(1000);	
		System.out.println("Step 3 : Go to ILMC and verify residency restriction changes");
		Reporter.log("Step 3 : Go to ILMC and verify residency restriction changes"); 	

			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			
			//Select state and course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(1000);	
			
			//Search details
			searchpage.EnterFirstName("DONALD");
			searchpage.EnterLastName("SCHERER");
			searchpage.EnterDay("15");
			searchpage.EnterYear("1975");
			searchpage.SelectMonth("May");
			searchpage.ClickOnSearchButton();
			Thread.sleep(1000);
			
			//Go to Edit
			certipage.ClickOnEditAddressButton();
			Thread.sleep(3000);
			//Verify Residence Restriction Message
			
			ActualText = certipage.ResidencyRestriction.getText().trim();
			ExpectedText = "Residency in the state of Pennsylvania is required to order your card replacement.";
			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Residency Restriction message is displayed. i.e. " + ActualText);
				Reporter.log("Success !! Residency Restriction message is displayed. i.e. " + ActualText); 
			
			}
			else
			{
				Thread.sleep(1000);
				System.out.println("Failure !! Residency Restriction message is NOT displayed. i.e." + "\n" + "Expected : "  
								+ "\n" + ExpectedText + "\n" + 
								 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Residency Restriction message is NOT displayed. i.e." + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual : " + "\n" +  ActualText);
			
				AssertFailedCount++;
			}
			
			//Verify State field is disabled
			
			if(!editadd.State.isEnabled())
			{
				Thread.sleep(1000);
				System.out.println("Success !! State field is disabled.");
				Reporter.log("Success !! State field is disabled."); 
			
			}
			else
			{
				Thread.sleep(1000);
				System.out.println("Failure !! State field is NOT disabled.");
				Reporter.log("Failure !! State field is NOT disabled.");
			
				AssertFailedCount++;
			}
			
			//Reset the changes
			SeleniumFunc.ToGoToUrl(URL);
			Thread.sleep(1000);
			editcollection.ClickOnResidencyFalse();
			Thread.sleep(1000);
			editcollection.ClickOnSaveButton();
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
	

	
	
	
	/* Test 3: 
	 * Test to make sure that you are not able to change a student's physical or mailing address in KPS
     */ 
	
	@Test
	private void ResidenctRestrictionAtCM() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Test to make sure that you are not able to change a student's physical or mailing address in KPS"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Test to make sure that you are not able to change a student's physical or mailing address in KPS"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader header = new GlobalHeader(driver);
		CollectionPage collection = new CollectionPage(driver);
		EditCollectionPage editcollection = new EditCollectionPage(driver);
		//CreateNewCertificationPage newcerti = new CreateNewCertificationPage(driver);
		//SearchCertificationPage certi = new SearchCertificationPage(driver)	;		
	    EditCertificationPage editcerti = new EditCertificationPage(driver);
	    EditCertificationPage editcer = new EditCertificationPage(driver);
	    
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Collection page");
		Reporter.log("Step 2 : Login to application and navigating to Collection page"); 	
			
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(1000);
			header.AdminDropdown.click();
			Thread.sleep(1000);
			header.Admin_ManageCollection.click();
			Thread.sleep(1000);

			collection.EnterCriteria("Hunter Safety Education (1959 - 1985)");
			collection.ClickOnSearchButton();
			Thread.sleep(1000);
			collection.ClickOnFirstRecord();
			Thread.sleep(1000);
			//Set Residency Restriction? as true
			editcollection.ClickOnResidencyTrue();
			Thread.sleep(1000);
			//Save the changes
			editcollection.ClickOnSaveButton();
			
			String URL = driver.getCurrentUrl();
			
		System.out.println("Step 3: Adding new ceritificate");
		Reporter.log("Step 3: Adding new ceritificate"); 
			
			header.Logo.click();
			Thread.sleep(1000);
			/*certi.ClickOnNewCertificate();
			Thread.sleep(2000);
			
			String firstname= "certificate" + JH.GenerateRandomNumber();
			System.out.println(firstname);
			String emailprefix = "certificate" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 PROTON RD";
			
			newcerti.CreateNewCertificate(firstname, "Testing", "01/24/1990", emailaddress, "United States",  streetaddress,"DALLAS", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
			newcerti.ClickOnContinueButton();*/
			
			    Thread.sleep(2000);   
		        editcer.SearchCM("AutomationResTesting Test ke-testing ");
	 		 	Thread.sleep(1000);
	 		 	editcer.ClickOnSearch();
	 		 	editcer.ClickOnStudentNametab();     
			Thread.sleep(5000);
		
		System.out.println("Step 4 : Go to Edit Certification and verify residency restriction changes");
		Reporter.log("Step 4 : Go to Edit Certification and verify residency restriction changes"); 	

			SeleniumFunc.ClickOnElement("css", "#contact-info header a");
			Thread.sleep(1000);

			//Verify State field is disabled
			
			if(!editcerti.State.isEnabled())
			{
				Thread.sleep(1000);
				System.out.println("Success !! State field is disabled.");
				Reporter.log("Success !! State field is disabled."); 
			
			}
			else
			{
				Thread.sleep(1000);
				System.out.println("Failure !! State field is NOT disabled.");
				Reporter.log("Failure !! State field is NOT disabled.");
			
				AssertFailedCount++;
			}
			
			//Reset the changes
			SeleniumFunc.ToGoToUrl(URL);
			editcollection.ClickOnResidencyFalse();
			Thread.sleep(1000);
			editcollection.ClickOnSaveButton();
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