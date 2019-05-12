package products.CM;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.CollectionOriginPage;
import pageFactory.CM.GlobalHeader;
import pageFactory.CM.LoginPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class CollectionOriginTest 
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
	 * Verify UI of collection Origin page
	*/ 
	@Test
	private void UI_CollectionOrigin() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify UI of collection Origin page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify UI of collection Origin page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader header = new GlobalHeader(driver);
		CollectionOriginPage collectionorigin = new CollectionOriginPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Collection Origin page");
		Reporter.log("Step 2 : Login to application and navigating to Collection Origin page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			
			header.AdminDropdown.click();
			header.Admin_ManageCollectionOrigin.click();
			
		System.out.println("Step 3 : Verify UI of Collection Origin Page");
		Reporter.log("Step 3 : Verify UI of Collection Origin Page"); 		
		
		
			//Verifying page header
			String ActualText= collectionorigin.Header.getText().trim();
			Thread.sleep(1000);
			String ExpectedText= "List of records";

		
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Correct Header present. i.e. " + ActualText);
				Reporter.log("Success !! Correct Header present. i.e. " + ActualText); 
			
			}
			else
			{
				
				System.out.println("Failure !! Incorrect Header. i.e." + "\n" + "Expected : "  
								+ "\n" + ExpectedText + "\n" + 
								 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Incorrect Header. i.e." + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + 
						"Actual : " + "\n" +  ActualText);
			
				AssertFailedCount++;
			}
		
			
			
			//Verify Column
			ActualText= collectionorigin.GetAllColumnName();
			
			ExpectedText= "Origin" + "Collection" + "Collection key";

			
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Correct columns present. i.e. " + ActualText);
				Reporter.log("Success !! Correct columns present. i.e. " + ActualText); 
			}
			else
			{
				System.out.println("Failure !! Incorrect Column Name. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Incorrect Column Name. i.e." + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual : " + "\n" +  ActualText);
				
				AssertFailedCount++;
			}
			
			
			//Add New Record Button
			if(SeleniumFunc.IsElementPresent(collectionorigin.AddRecord))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Add New Record Button present.");
				Reporter.log("Success !! Add New Record Button present."); 
			}
			else
			{
				System.out.println("Failure !! Add New Record Button is missing");
				Reporter.log("Failure !! Add New Record Button is missing");
				
				AssertFailedCount++;
			}
			
			//Edit Link 
			if(SeleniumFunc.IsElementPresent(collectionorigin.EditRecord))
			{
				System.out.println("Success !! Edit Link present.");
				Reporter.log("Success !! Edit Link present."); 
			}
			else
			{
				System.out.println("Failure !! Edit Link is missing");
				Reporter.log("Failure !! Edit Link is missing");
				
				AssertFailedCount++;
			}
			
			//Delete Link 
			if(SeleniumFunc.IsElementPresent(collectionorigin.DeleteRecord))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Delete Link present.");
				Reporter.log("Success !! Delete Link present."); 
			}
			else
			{
				System.out.println("Failure !! Delete Link is missing");
				Reporter.log("Failure !! Delete Link is missing");
				
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
	 * Verify User can create collection mapping
	*/ 
	@Test
	private void CreateCollectionMapping() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify User can create collection mapping"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify User can create collection mapping"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader header = new GlobalHeader(driver);
		CollectionOriginPage collectionorigin = new CollectionOriginPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Collection Origin page");
		Reporter.log("Step 2 : Login to application and navigating to Collection Origin page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			
			header.AdminDropdown.click();
			Thread.sleep(1000);
			header.Admin_ManageCollectionOrigin.click();
			Thread.sleep(1000);
			
		System.out.println("Step 3 : Verify success message for add certification mapping");
		Reporter.log("Step 3 : Verify success message for add certification mapping"); 		
		
		
			collectionorigin.ClickOnAddRecordButton();
			Thread.sleep(1000);
			//Enter Details for collection origin
			collectionorigin.SelectOrigin("Managed Course");
			collectionorigin.SelectCollection("Boat Alaska Course");
			String Key=JH.GenerateRandomNumber() + "R";
			collectionorigin.EnterCollectionKey(Key);
			collectionorigin.ClickOnSaveButton();
			
			//Verifying page header
			String ActualText= collectionorigin.Success.getText().trim();
		
			String ExpectedText= "Certification collection origin was successfully created.";

		
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Certification collection origin created. i.e. " + ActualText);
				Reporter.log("Success !! Certification collection origin created. i.e. " + ActualText); 
			
			}
			else
			{
				
				System.out.println("Failure !! Error while Certification collection origin creation. i.e." + "\n" + 
									"Expected : " 	+ "\n" + ExpectedText + "\n" + 
									"Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Error while Certification collection origin creation. i.e." + "\n" + 
							"Expected : " 	+ "\n" + ExpectedText + "\n" + 
							"Actual : " + "\n" +  ActualText);
			
				AssertFailedCount++;
			}
		
			//Verify Cancel Link Functionality
			collectionorigin.ClickOnAddRecordButton();	
			Thread.sleep(1000);
			collectionorigin.ClickOnCancelLink();
			Thread.sleep(1000);
			ActualText= collectionorigin.Header.getText().trim();
			
			ExpectedText= "List of records";

		
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Cancel Link is working properly. i.e. " + ActualText);
				Reporter.log("Success !! Cancel Link is working properly. i.e. " + ActualText); 
			
			}
			else
			{
				
				System.out.println("Failure !! Cancel Link is NOT working. i.e." + "\n" + "Expected : "  
								+ "\n" + ExpectedText + "\n" + 
								 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Cancel Link is NOT working. i.e." + "\n" + "Expected : "  
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
	 * Verify user can not use the same 'Collection Key' more than once for the same origin
	*/ 
	@Test
	private void DuplicateKey() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify user can not use the same 'Collection Key' more than once for the same origin"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify user can not use the same 'Collection Key' more than once for the same origin"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader header = new GlobalHeader(driver);
		CollectionOriginPage collectionorigin = new CollectionOriginPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Collection Origin page");
		Reporter.log("Step 2 : Login to application and navigating to Collection Origin page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			
			header.AdminDropdown.click();
			Thread.sleep(1000);
			header.Admin_ManageCollectionOrigin.click();
			Thread.sleep(1000);
			
		System.out.println("Step 3 : Verify error message for duplicate collection key");
		Reporter.log("Step 3 : Verify error message for duplicate collection key"); 		
		
		
			collectionorigin.ClickOnAddRecordButton();
			Thread.sleep(1000);
			//Enter Details for collection origin
			collectionorigin.SelectOrigin("Managed Course");
			collectionorigin.SelectCollection("Boat Alaska Course");
			String Key=JH.GenerateRandomNumber() + "R";
			collectionorigin.EnterCollectionKey(Key);
			collectionorigin.ClickOnSaveButton();
			
			Thread.sleep(4000);
			
			//Add Another Collection Origin with same Collection Key
			collectionorigin.ClickOnAddRecordButton();
			Thread.sleep(1000);
			collectionorigin.SelectOrigin("Managed Course");
			collectionorigin.SelectCollection("Boat Alaska Course");
			collectionorigin.EnterCollectionKey(Key);
			collectionorigin.ClickOnSaveButton();
			Thread.sleep(1000);
			//Verifying Error Message
			String ActualText= collectionorigin.ErrorDuplicateKey.getText().trim();
		
			String ExpectedText= "has already been taken";

		
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Duplicate Collection Key is not allowed. i.e. " + ActualText);
				Reporter.log("Success !! Duplicate Collection Key is not allowed. i.e. " + ActualText); 
			
			}
			else
			{
				
				System.out.println("Failure !! Allowing Duplicate Collection Key for same origin. i.e." + "\n" + 
									"Expected : " 	+ "\n" + ExpectedText + "\n" + 
									"Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Allowing Duplicate Collection Key for same origin. i.e." + "\n" + 
						"Expected : " 	+ "\n" + ExpectedText + "\n" + 
						"Actual : " + "\n" +  ActualText);
			
				AssertFailedCount++;
			}
		
			//Verify Same collection key can be used for different origin
			collectionorigin.SelectOrigin("VA Quarterly Import");
			collectionorigin.SelectCollection("Boat Alaska Course");
			collectionorigin.EnterCollectionKey(Key);
			collectionorigin.ClickOnSaveButton();
			Thread.sleep(1000);
			
			ActualText= collectionorigin.Header.getText().trim();
			
			ExpectedText= "List of records";

		
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Same Key can be used for different origin. i.e. " +  ActualText);
				Reporter.log("Success !! Same Key can be used for different origin. i.e. " +  ActualText); 
			
			}
			else
			{
				
				System.out.println("Failure !! Not allowing same key for different origin. i.e." + "\n" + "Expected : "  
								+ "\n" + ExpectedText + "\n" + 
								 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Not allowing same key for different origin. i.e." + "\n" + "Expected : "  
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
	 * Verify available values in Origin drop down.
	*/ 
	@Test
	private void VerifyOriginOptions() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify available values in Origin drop down."  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 4 : Verify available values in Origin drop down."  + "\n" +
	 				"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader header = new GlobalHeader(driver);
		CollectionOriginPage collectionorigin = new CollectionOriginPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Collection Origin page");
		Reporter.log("Step 2 : Login to application and navigating to Collection Origin page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			
			header.AdminDropdown.click();
			Thread.sleep(1000);
			header.Admin_ManageCollectionOrigin.click();
			Thread.sleep(1000);
		System.out.println("Step 3 : Verify options for Origin dro-down");
		Reporter.log("Step 3 : Verify options for Origin drop-down"); 		
		
		
			collectionorigin.ClickOnAddRecordButton();
			Thread.sleep(1000);
			String[] dropdownvalues = {"","Managed Course","Open Course","PA Historical State Import"
									  ,"Affidavit","Event Manager","Virginia Historical - Education Data"
									  ,"Virginia Historical - Internet Courses","VA Quarterly Import"
									  ,"Virginia Department of Game and Inland Fisheries Quarterly Import"
									  ,"Arizona Game and Fish Department Quarterly Import"};
		
			SeleniumFunc.VerifyDropDownOptions("id", "certification_collection_origin_certification_origin_id", dropdownvalues);
		
			
			
			
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
	 * Verify validation available on 'Add New Record' page
	*/ 
	@Test
	private void VerifyValidation() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify validation available on 'Add New Record' page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 5 : Verify validation available on 'Add New Record' page"  + "\n" +
	 				"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader header = new GlobalHeader(driver);
		CollectionOriginPage collectionorigin = new CollectionOriginPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Collection Origin page");
		Reporter.log("Step 2 : Login to application and navigating to Collection Origin page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			
			header.AdminDropdown.click();
			Thread.sleep(1000);
			header.Admin_ManageCollectionOrigin.click();
			Thread.sleep(1000);
		System.out.println("Step 3 : Verify validation on Add New Record");
		Reporter.log("Step 3 : Verify validation on Add New Record"); 		
		
		
			collectionorigin.ClickOnAddRecordButton();
			Thread.sleep(1000);
			collectionorigin.ClickOnSaveButton();
			Thread.sleep(1000);
			String ActualText = collectionorigin.Error.getText().trim();
			String ExpectedText = "Please review the problems below:";
			if(ActualText.equals(ExpectedText))
			{
				
				System.out.println("Success !! Validation is displayed for all blank. i.e. " + ActualText);
				Reporter.log("Success !! Validation is displayed for all blank. i.e. " + ActualText); 
			
			}
			else
			{
				
				System.out.println("Failure !! No validation for all blank. i.e." + "\n" + 
									"Expected : " 	+ "\n" + ExpectedText + "\n" + 
									"Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! No validation for all blank. i.e." + "\n" + 
						"Expected : " 	+ "\n" + ExpectedText + "\n" + 
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
	
	
	
	
	/* Test 6: 
	 * Verify User can edit collection mapping
	*/ 
	@Test
	private void EditCollection() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify User can edit collection mapping"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 6 : Verify User can edit collection mapping"  + "\n" +
	 				"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader header = new GlobalHeader(driver);
		CollectionOriginPage collectionorigin = new CollectionOriginPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Collection Origin page");
		Reporter.log("Step 2 : Login to application and navigating to Collection Origin page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			
			header.AdminDropdown.click();
			Thread.sleep(1000);
			header.Admin_ManageCollectionOrigin.click();
			Thread.sleep(1000);
			
		System.out.println("Step 3 : Add New Record and Edit Same");
		Reporter.log("Step 3 : Add New Record and Edit Same"); 		
		
		
			collectionorigin.ClickOnAddRecordButton();
			Thread.sleep(1000);
			collectionorigin.SelectOrigin("Managed Course");
			collectionorigin.SelectCollection("Boat Alaska Course");
			String Key=JH.GenerateRandomNumber() + "R";
			collectionorigin.EnterCollectionKey(Key);
			collectionorigin.ClickOnSaveButton();
			Thread.sleep(1000);
			//Go to Edit 
			List<WebElement> ele = driver.findElements(By.cssSelector("table.table-striped tbody tr"));
			int n = ele.size();
			
			SeleniumFunc.ClickOnElement("css", "table.table-striped tbody tr:nth-of-type(" + n + ") td:nth-of-type(4) a");
			Key=JH.GenerateRandomNumber() + "RW";
			collectionorigin.EnterCollectionKey(Key);
			collectionorigin.ClickOnSaveButton();
			Thread.sleep(1000);
			//Verify Record edited 
			ele = driver.findElements(By.cssSelector("table.table-striped tbody tr"));
			n = ele.size();
			
			SeleniumFunc.ClickOnElement("css", "table.table-striped tbody tr:nth-of-type(" + n + ") td:nth-of-type(4) a");
			
			String ActualText = collectionorigin.CollectionKey.getAttribute("value");
			String ExpectedText = Key;
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);	
				System.out.println("Success !! Record Updated Successfully. i.e. " + ActualText);
				Reporter.log("Success !! Record Updated Successfully. i.e. " + ActualText); 
			
			}
			else
			{
				
				System.out.println("Failure !! Record NOT Updated. i.e." + "\n" + 
									"Expected : " 	+ "\n" + ExpectedText + "\n" + 
									"Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Record NOT Updated. i.e." + "\n" + 
						"Expected : " 	+ "\n" + ExpectedText + "\n" + 
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
	
	
	
	/* Test 7: 
	 * Verify User can delete collection mapping
	*/ 
	@Test
	private void DeleteCollection() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Verify User can delete collection mapping"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
					"Test 7 : Verify User can delete collection mapping"  + "\n" +
	 				"====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader header = new GlobalHeader(driver);
		CollectionOriginPage collectionorigin = new CollectionOriginPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Collection Origin page");
		Reporter.log("Step 2 : Login to application and navigating to Collection Origin page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			
			header.AdminDropdown.click();
			Thread.sleep(1000);
			header.Admin_ManageCollectionOrigin.click();
			Thread.sleep(1000);
			
		System.out.println("Step 3 : Verify User can Delete the record");
		Reporter.log("Step 3 : Verify User can Delete the record"); 		
			
		Thread.sleep(1000);
			//Delete Record
			List<WebElement> ele = driver.findElements(By.cssSelector("table.table-striped tbody tr"));
			int n = ele.size();
			Thread.sleep(1000);
			SeleniumFunc.ClickOnElement("css", "table.table-striped tbody tr:nth-of-type(" + n + ") td:nth-of-type(5) a");
			Thread.sleep(1000);
			SeleniumFunc.AcceptAlertAndGetText();
			
			//Success Message
			String ActualText = collectionorigin.Success.getText().trim();
			String ExpectedText = "Certification collection origin was successfully destroyed.";
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Record Deleted Successfully. i.e. " + ActualText);
				Reporter.log("Success !! Record Deleted Successfully. i.e. " + ActualText); 
			
			}
			else
			{
				
				System.out.println("Failure !! Record NOT Deleted. i.e." + "\n" + 
									"Expected : " 	+ "\n" + ExpectedText + "\n" + 
									"Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Record NOT Deleted. i.e." + "\n" + 
						"Expected : " 	+ "\n" + ExpectedText + "\n" + 
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
	
}
