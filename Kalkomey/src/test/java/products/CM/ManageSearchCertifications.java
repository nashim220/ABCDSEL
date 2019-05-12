package products.CM;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.LoginPage;
import pageFactory.CM.SearchCertificationPage;
import pageFactory.CM.CreateNewCertificationPage;
import pageFactory.CM.GlobalHeader;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class ManageSearchCertifications 
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
	 * Search Certification - Verify Certification manager icon in the header
	*/ 
	@Test
	private void Search_CerificationManagerIcon() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Search Certification - Verify Certification manager icon in the header"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Search Certification - Verify Certification manager icon in the header"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_Handgun + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			
		
		System.out.println("Step 3 : Verifying presence of Certification manager icon in the header");
		Reporter.log("Step 3 : Verifying presence of Certification manager icon in the header"); 
			
			
		Thread.sleep(1000);
			if(SeleniumFunc.IsElementPresent(certi.ManageCerti_Icon))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Certification manager icon is present in the header");
				Reporter.log("Success !! Certification manager icon is present in the header"); 
			}
			else
			{
				System.out.println("Failure !! Certification manager icon is NOT present in the header");
				Reporter.log("Failure !! Certification manager icon is NOT present in the header"); 
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
	 * Search Certification - Verify UI elements on 'Search Certifications' page
	*/ 
	@Test
	private void Search_VerifyUIElements() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Search Certification - Verify UI elements on 'Search Certifications' page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Search Certification - Verify UI elements on 'Search Certifications' page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage certi = new SearchCertificationPage(driver)	;		
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_Handgun + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			Thread.sleep(1000);
		
		System.out.println("Step 3 : Verifying UI elements on 'Cerification Manager' page");
		Reporter.log("Step 3 : Verifying UI elements on 'Cerification Manager' page"); 
			
			
			//Verifying page title		
			if(certi.CertiHeader.getText().trim().equals("Search Certifications"))
			{
				Thread.sleep(1000);
				System.out.println("Success !! page title is correct i.e. Search Certifications" );
				Reporter.log("Success !! page title is correct i.e. Search Certifications" );
			}
			else
			{
				System.out.println("Failure !! page tilte is incorrect. Expected is :  Search Certifications , Actual is : " + certi.CertiHeader.getText().trim());
				Reporter.log("Failure !! page tilte is incorrect. Expected is :  Search Certifications , Actual is : " + certi.CertiHeader.getText().trim());
				AssertFailedCount++;
			}
			
			//Verifying presence of 'Jurisdiction' dropdown
			if(SeleniumFunc.IsElementPresent(certi.State))
			{
				
				System.out.println("Success !! 'Jurisdiction' dropdown is present");
				Reporter.log("Success !! 'Jurisdiction' dropdown is present"); 
			}
			else
			{
				System.out.println("Failure !! 'Jurisdiction' dropdown is NOT present");
				Reporter.log("Failure !!'Jurisdiction' dropdown is NOT present"); 
				AssertFailedCount++;
			}
			
			//Verifying presence of 'Type' dropdown
			if(SeleniumFunc.IsElementPresent(certi.CertiType))
			{
				
				System.out.println("Success !! 'Type' dropdown is present");
				Reporter.log("Success !! 'Type' dropdown is present"); 
			}
			else
			{
				System.out.println("Failure !! 'Type' dropdown is NOT present");
				Reporter.log("Failure !!'Type' dropdown is NOT present"); 
				AssertFailedCount++;
			}
			
			
			//Verifying presence of 'Name and/or city and/or birthdate (mm/dd/yyyy)' search box
			if(SeleniumFunc.IsElementPresent(certi.SearchField))
			{
				Thread.sleep(1000);
				System.out.println("Success !! 'Name and/or city and/or birthdate (mm/dd/yyyy)' search box is present");
				Reporter.log("Success !! 'Name and/or city and/or birthdate (mm/dd/yyyy)' search boxis present"); 
			}
			else
			{
				System.out.println("Failure !! 'Name and/or city and/or birthdate (mm/dd/yyyy)' search box is NOT present");
				Reporter.log("Failure !! 'Name and/or city and/or birthdate (mm/dd/yyyy)' search box is NOT present"); 
				AssertFailedCount++;
			}
			
			
			//Verifying presence of 'Search' button
			if(SeleniumFunc.IsElementPresent(certi.Search))
			{
				Thread.sleep(1000);
				System.out.println("Success !! 'Search' button is present");
				Reporter.log("Success !! 'Search' button is present"); 
			}
			else
			{
				System.out.println("Failure !! 'Search' button is NOT present");
				Reporter.log("Failure !! 'Search' button is NOT present");
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
	 * Verify search records are displayed while Searching with Name
	*/ 
	@Test
	private void Search_WithName() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify search records are displayed while Searching with Name"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify search records are displayed while Searching with Name"  + "\n" +
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
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
		
		System.out.println("Step 3: Adding new ceritificate");
		Reporter.log("Step 3: Adding new ceritificate"); 
		
		Thread.sleep(1000);
			searchcerti.NewCerti.click();
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
			
		System.out.println("Step 3 : Navigate to 'Search Certifications' and search for added Cerificate with name");
		Reporter.log("Step 3 : Navigate to 'Search Certifications' and search for added Cerificate with name");
			
			searchcerti.ClickOnManageCerification();
			searchcerti.EnterSeachCriteria(firstname);
			searchcerti.ClickOnSearchButton();
			Thread.sleep(5000);
		
			//Verifying result
			actualtext= searchcerti.Results_GetName(1);
			expectedtext= "TESTING, " + firstname.toUpperCase(); 
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Certificate is displayed with correct name. i.e. " + actualtext);
				Reporter.log("Success !! Certificate is displayed with correct name.  i.e." + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Certificate is displayed with incorrect name.  i.e." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  Certificate is displayed with incorrect name.  i.e." + "\n" + "Expected : "  
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
	
	
	/* Test 4: 
	 * Verify search records are displayed while Searching with Jurisdiction/Type
	*/ 
	@Test
	private void Search_WithJurisdiction_Type() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify search records are displayed while Searching with Jurisdiction/Type"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify search records are displayed while Searching with Jurisdiction/Type"  + "\n" +
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
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername("sanjeetk@clariontechnologies.co.in");
			login.EnterPassword("clarion@123");
			login.ClickOnLogInButton();
		
		System.out.println("Step 3: Adding new ceritificate");
		Reporter.log("Step 3: Adding new ceritificate"); 
		
		Thread.sleep(1000);
			searchcerti.NewCerti.click();
			String firstname= "certi" + JH.GenerateRandomNumber();
			String emailprefix = "certi" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 Proton Rd";
		
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
			
		System.out.println("Step 3 : Navigate to 'Search Certifications' and search for added Cerificate with name/Jurisdiction/Type");
		Reporter.log("Step 3 : Navigate to 'Search Certifications' and search for added Cerificate with name/Jurisdiction/Type");
		Thread.sleep(1000);
			searchcerti.ClickOnManageCerification();
			searchcerti.EnterSeachCriteria(firstname);
			searchcerti.SelectSearchState("Pennsylvania");
			searchcerti.SelectSearchType("Hunting");
			searchcerti.ClickOnSearchButton();
			Thread.sleep(5000);
		
			//Verifying result Name
			actualtext= searchcerti.Results_GetName(1);
			expectedtext= "TESTING, " + firstname.toUpperCase(); 
			Thread.sleep(1000);
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Certificate is displayed with correct name. i.e. " + actualtext);
				Reporter.log("Success !! Certificate is displayed with correct name.  i.e." + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Certificate is displayed with incorrect name.  i.e." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  Certificate is displayed with incorrect name.  i.e." + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			
			//Verifying result State/Type
			actualtext= searchcerti.Results_GetStateTypeInfo(1);
			expectedtext= "State: Pennsylvania" + "\n" + "Type: Hunting"; 
			Thread.sleep(1000);
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Certificate is displayed with correct State/Type. i.e. " + actualtext);
				Reporter.log("Success !! Certificate is displayed with correct State/Type.  i.e." + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Certificate is displayed with incorrect State/Type.  i.e." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  Certificate is displayed with incorrect State/Type.  i.e." + "\n" + "Expected : "  
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
	 * Verify search records are displayed while search by Name and/or city and/or DOB
	*/ 
	@Test
	private void Search_With_Name_City_DOB() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify search records are displayed while search by Name and/or city and/or DOB"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify search records are displayed while search by Name and/or city and/or DOB"  + "\n" +
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
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername("sanjeetk@clariontechnologies.co.in");
			login.EnterPassword("clarion@123");
			login.ClickOnLogInButton();
		
		System.out.println("Step 3: Adding new ceritificate");
		Reporter.log("Step 3: Adding new ceritificate"); 
		
		Thread.sleep(1000);
			searchcerti.NewCerti.click();
			String firstname= "certi" + JH.GenerateRandomNumber();
			String emailprefix = "certi" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String streetaddress = "14086 Proton Rd";
		
			createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas", "PA", "Hunter Safety Education (1959 - 1985)", "Pass", "03/02/2015", "04/02/2015");
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
		System.out.println("Step 3 : Navigate to 'Search Certifications' and search for added Cerificate with Name OR city OR DOB e.g. certi820145962 OR Dallas OR 12/28/1989");
		Reporter.log("Step 3 : Navigate to 'Search Certifications' and search for added Cerificate with Name OR city OR DOB e.g. certi820145962 OR Dallas OR 12/28/1989");
		
		    Thread.sleep(2000);
			searchcerti.ClickOnManageCerification();
			String searchcriteria = firstname + " OR Dallas OR 12/28/1989";
			Thread.sleep(2000);
			searchcerti.EnterSeachCriteria(searchcriteria);
			searchcerti.ClickOnSearchButton();
			Thread.sleep(5000);
		
		
			//Getting total number of records returned
			int n = searchcerti.Results_GetCount();
			Thread.sleep(1000);
			for(int i=1;i<=n;i++)
			{
				//Verifying result Name
				actualtext= searchcerti.Results_GetName(i);
				expectedtext= firstname.toUpperCase(); 
				
	
				if(		actualtext.contains(expectedtext) || actualtext.contains("DALLAS") ||
						searchcerti.Results_GetAddressInfo(i).contains("Dallas") ||
						searchcerti.Results_GetBirthDateInfo(i).equals("Born 12/28/1989")
						)
				
				{
					System.out.println("Success !! Certificate is displayed with correct name or city or dob. i.e. " + actualtext);
					Reporter.log("Success !! Certificate is displayed with correct name or city or dob.  i.e." + actualtext); 
					
				}
				else
				{
					System.out.println("Failure !! Certificate is displayed with incorrect name/city/dob.  i.e." + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual : " + "\n" +  actualtext);
					Reporter.log("Failure !!  Failure !! Certificate is displayed with incorrect name/city/dob.  i.e." + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
			}*/
			
			
		
		System.out.println("Step 4 : Navigate to 'Search Certifications' and search for added Cerificate with Name and city and DOB e.g. certi820145962 and Dallas and 12/28/1989");
		Reporter.log("Step 4 : Navigate to 'Search Certifications' and search for added Cerificate with Name and city and DOB e.g. certi820145962 and Dallas and 12/28/1989");
		
			Thread.sleep(2000);
			searchcerti.ClickOnManageCerification();
			String searchcriteria = firstname + " AND Dallas AND 12/28/1989";
			Thread.sleep(2000);
			searchcerti.EnterSeachCriteria(searchcriteria);
			searchcerti.ClickOnSearchButton();
			Thread.sleep(5000);
		
			
			//Getting total number of records returned
			int n = searchcerti.Results_GetCount();
			Thread.sleep(1000);
			for(int i=1;i<=n;i++)
			{
				//Verifying result Name
				actualtext= searchcerti.Results_GetName(i);
				expectedtext= firstname.toUpperCase(); 
				
				if(		actualtext.contains(expectedtext) && 
						searchcerti.Results_GetAddressInfo(i).contains("Dallas") &&
						searchcerti.Results_GetBirthDateInfo(i).equals("Born 12/28/1989")
						)
				{
					System.out.println("Success !! Certificate is displayed with correct name. i.e. " + actualtext);
					Reporter.log("Success !! Certificate is displayed with correct name.  i.e." + actualtext); 
					System.out.println("Success !! Certificate is displayed with correct City. i.e. Dallas");
					Reporter.log("Success !! Certificate is displayed with correct City.  i.e. Dallas"); 
					System.out.println("Success !! Certificate is displayed with correct DOB. i.e. 12/28/1989");
					Reporter.log("Success !! Certificate is displayed with correct DOB.  i.e. 12/28/1989"); 
				}
				else
				{
					System.out.println("Failure !! Certificate is displayed with incorrect name/city/dob.  i.e." + "\n" + "Expected : "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual : " + "\n" +  actualtext);
					Reporter.log("Failure !!  Failure !! Certificate is displayed with incorrect name/city/dob.  i.e." + "\n" + "Expected : "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
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
	 * Verify validation displayed while Searching with invalid data (e.g. @#$)
	*/ 
	@Test
	private void Search_InvalidData() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify validation displayed while Searching with invalid data (e.g. @#$)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Verify validation displayed while Searching with invalid data (e.g. @#$)"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
		
		System.out.println("Step 3 : Navigate to 'Search Certifications' and search with invalid date e.g. @#$");
		Reporter.log("Step 3 : Navigate to 'Search Certifications' and search with invalid date e.g. @#$");
			
			searchcerti.ClickOnManageCerification();
			Thread.sleep(1000);
			searchcerti.EnterSeachCriteria("@#$");
			Thread.sleep(1000);
			searchcerti.ClickOnSearchButton();
			Thread.sleep(5000);
		
			//Verifying alert message
			String actualtext= gheader.Notice_AlertText.getText().trim();
			String expectedtext= "No certifications matched your query"; 
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
	 * Verify that pagination is working
	*/ 
	@Test
	private void Search_Pagination() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Verify that pagination is working"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Verify that pagination is working"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
		
		System.out.println("Step 3 : Navigate to 'Search Certifications' and search");
		Reporter.log("Step 3 : Navigate to 'Search Certifications' and search");
			
		/*	searchcerti.ClickOnManageCerification();
			searchcerti.EnterSeachCriteria("");*/
			searchcerti.ClickOnSearchButton();
			Thread.sleep(5000);
		
		System.out.println("Step 4 : On Pagination bar, click on 'Next->' link");
		Reporter.log("Step 4 : On Pagination bar, click on 'Next->' link");	
		
			searchcerti.Pagination_Next.click();
			Thread.sleep(1000);
			
			//Verifying current page
			String actualtext= searchcerti.Pagination_ActivePage.getText().trim();
			String expectedtext= "2"; 
			Thread.sleep(1000);
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Next button is working i.e. " + actualtext);
				Reporter.log("Success !! Next button is working i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Next button is NOT working" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  Next button is NOT working" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			Thread.sleep(1000);
			
			
		System.out.println("Step 5 : On Pagination bar, click on 'Prev->' link");
		Reporter.log("Step 5 : On Pagination bar, click on 'Prev->' link");	
		
		Thread.sleep(1000);
			searchcerti.Pagination_Prev.click();
			Thread.sleep(1000);
			//Verifying current page
			actualtext= searchcerti.Pagination_ActivePage.getText().trim();
			expectedtext= "1"; 
			Thread.sleep(1000);
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Prev button is working i.e. " + actualtext);
				Reporter.log("Success !! Prev button is working i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Prev button is NOT working" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !!  Prev button is NOT working" + "\n" + "Expected : "  
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
	
	
	/* Test 8: 
	 * Verify that card icon is displayed for Printable cards
	*/ 
	@Test
	private void Search_IconForPritableCards() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Verify that card icon is displayed for Printable cards"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Verify that card icon is displayed for Printable cards"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		SearchCertificationPage searchcerti = new SearchCertificationPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
		
		System.out.println("Step 3 : Navigate to 'Search Certifications' and search");
		Reporter.log("Step 3 : Navigate to 'Search Certifications' and search");
			
			Thread.sleep(2000);
			searchcerti.ClickOnManageCerification();
			Thread.sleep(2000);
			searchcerti.EnterSeachCriteria("RIFFE, LAUREN N");
			searchcerti.ClickOnSearchButton();
			Thread.sleep(5000);
		
		System.out.println("Step 4 : Verify that card icon is displayed for Printable cards");
		Reporter.log("Step 4 : Verify that card icon is displayed for Printable cards");	
		
			Thread.sleep(2000);
			if(searchcerti.Results_IsPrintableCardIconDisplayed(1))
			{
				Thread.sleep(1000);
				System.out.println("Success !! card icon is displayed for Printable cards");
				Reporter.log("Success !! card icon is displayed for Printable cards");
			}
			else
			{
				System.out.println("Failure !! card icon is NOT displayed for Printable cards");
				Reporter.log("Failure !! card icon is NOT displayed for Printable cards");
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
