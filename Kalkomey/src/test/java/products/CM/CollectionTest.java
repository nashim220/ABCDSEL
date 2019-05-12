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

import pageFactory.CM.CollectionPage;
import pageFactory.CM.EditCollectionPage;
import pageFactory.CM.GlobalHeader;
import pageFactory.CM.LoginPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class CollectionTest 
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
	 * Verify UI of collection page
	*/ 
	
	@Test
	private void UI_Collection() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify UI of collection page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify UI of collection page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader header = new GlobalHeader(driver);
		CollectionPage collection = new CollectionPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Collection page");
		Reporter.log("Step 2 : Login to application and navigating to Collection page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			
			header.AdminDropdown.click();
			Thread.sleep(1000);
			header.Admin_ManageCollection.click();
			Thread.sleep(1000);
			
		System.out.println("Step 3 : Verify UI of Collection Page");
		Reporter.log("Step 3 : Verify UI of Collection Page"); 		
		
		
			//Verifying page header
			String ActualText= collection.Title.getText().trim();
		
			String ExpectedText= "Search Certification Collections";

		
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Correct Title  present. i.e. " + ActualText);
				Reporter.log("Success !! Correct Title present. i.e. " + ActualText); 
			
			}
			else
			{
				
				System.out.println("Failure !! Incorrect Title. i.e." + "\n" + "Expected : "  
								+ "\n" + ExpectedText + "\n" + 
								 "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Incorrect Title. i.e." + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + 
						"Actual : " + "\n" +  ActualText);
			
				AssertFailedCount++;
			}
		
			
			
			//Verify UI Element
			
			//Jurisdiction Drop-down
						
			if(SeleniumFunc.IsElementPresent(collection.Jurisdiction))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Jurisdiction drop-down present. ");
				Reporter.log("Success !! Jurisdiction drop-down present. "); 
			}
			else
			{
				System.out.println("Failure !! Jurisdiction drop-down is missing.");
				Reporter.log("Failure !! Jurisdiction drop-down is missing.");
				
				AssertFailedCount++;
			}
			
			//Type Drop-down
			if(SeleniumFunc.IsElementPresent(collection.Type))
			{
				System.out.println("Success !! Type drop-down present. ");
				Reporter.log("Success !! Type drop-down present. "); 
			}
			else
			{
				System.out.println("Failure !! Type drop-down is missing.");
				Reporter.log("Failure !! Type drop-down is missing.");
				
				AssertFailedCount++;
			}
			
			//Collection Info
			if(SeleniumFunc.IsElementPresent(collection.Criteria))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Collection Info is  present.");
				Reporter.log("Success !! Collection Info is  present."); 
			}
			else
			{
				System.out.println("Failure !! Collection Info is missing");
				Reporter.log("Failure !! Collection Info is missing");
				
				AssertFailedCount++;
			}
			
			//Search Button
			if(SeleniumFunc.IsElementPresent(collection.Search))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Search Button present.");
				Reporter.log("Success !! Search Button present."); 
			}
			else
			{
				System.out.println("Failure !! Search Button is missing");
				Reporter.log("Failure !! Search Button is missing");
				
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
	 * Verify search records are displayed
	*/ 
	
	@Test
	private void SearchFunctionality() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify search records are displayed"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify search records are displayed"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		GlobalHeader header = new GlobalHeader(driver);
		CollectionPage collection = new CollectionPage(driver);
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Collection page");
		Reporter.log("Step 2 : Login to application and navigating to Collection page"); 	
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
			
			header.AdminDropdown.click();
			Thread.sleep(1000);
			header.Admin_ManageCollection.click();
			Thread.sleep(1000);
			
		System.out.println("Step 3 : Verify Search functionality with invalid data");
		Reporter.log("Step 3 : Verify Search functionality with invalid data"); 		
		
			collection.EnterCriteria("criteria");
			collection.ClickOnSearchButton();
			Thread.sleep(1000);
			//Verify error message
			String ActualText= collection.NoRecord.getText().trim();
			String ExpectedText= "We found no collections matching your query.";

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
			
			
			
			System.out.println("Step 4 : Verify Search functionality with valid data");
			Reporter.log("Step 4: Verify Search functionality with valid data"); 		
			
				collection.EnterCriteria("HUNTER-TRAPPER EDUCATION (1986 - PRESENT)");
				collection.ClickOnSearchButton();
				Thread.sleep(1000);
				//Verify record present
				ActualText= collection.ValidSearch.getText().trim();
				ExpectedText= "HUNTER-TRAPPER EDUCATION (1986 - PRESENT)";

				if(ActualText.equals(ExpectedText))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Search working properly with valid data.  i.e. " + ActualText);
					Reporter.log("Success !! Search working properly with invalid data.  i.e. " + ActualText); 
				
				}
				else
				{
					
					System.out.println("Failure !! Search is NOT working properly with valid data. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
					Reporter.log("Failure !! Search is NOT working properly with valid data. i.e." + "\n" + "Expected : "  
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
		 * Verify Search records get filtered according to selected criteria
		*/ 
		
		@Test
		private void SearchCriteria() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 3 : Verify Search records get filtered according to selected criteria"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 3 : Verify Search records get filtered according to selected criteria"  + "\n" +
					 	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			GlobalHeader header = new GlobalHeader(driver);
			CollectionPage collection = new CollectionPage(driver);
			
			System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
			Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
				
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
		
			System.out.println("Step 2 : Login to application and navigating to Collection page");
			Reporter.log("Step 2 : Login to application and navigating to Collection page"); 	
			
				login.EnterUsername(Constants.CM_Username);
				login.EnterPassword(Constants.CM_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);
				header.AdminDropdown.click();
				header.Admin_ManageCollection.click();
				Thread.sleep(2000);
				
			System.out.println("Step 3 : Select search criteria and verify record displaying as per criteria");
			Reporter.log("Step 3 : Select search criteria and verify record displaying as per criteria"); 		
			
				collection.SelectJurisdiction("Pennsylvania");
				collection.SelectType("Hunting");
			    collection.ClickOnSearchButton();
				Thread.sleep(2000);
				//Verify error message
				String ActualText= SeleniumFunc.GetElementText("css", ".span6.certification-collection-info p:nth-of-type(2)");
				String ExpectedText= "State: Pennsylvania";
				Thread.sleep(1000);
				if(ActualText.contains(ExpectedText))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Search records are displayed from selected Type of activit y.  i.e. " + ActualText);
					Reporter.log("Success !! Search records are displayed from selected Type of activit y.  i.e. " + ActualText); 
				
				}
				else
				{
					Thread.sleep(1000);
					System.out.println("Failure !! Search criteria is NOT working. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
					Reporter.log("Failure !! Search criteria is NOT working. i.e." + "\n" + "Expected : "  
							+ "\n" + ExpectedText + "\n" + 
							 "Actual : " + "\n" +  ActualText);
				
					AssertFailedCount++;
				}
				
				String ActualText1= SeleniumFunc.GetElementText("css", ".span6.certification-collection-info p:nth-of-type(3)");
				String ExpectedText1= "Type: Hunting";
				if(ActualText1.contains(ExpectedText1))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Search records are displayed from selected Type of activit y.  i.e. " + ActualText);
					Reporter.log("Success !! Search records are displayed from selected Type of activit y.  i.e. " + ActualText); 
				
				}
				else
				{
					Thread.sleep(1000);
					System.out.println("Failure !! Search criteria is NOT working. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
					Reporter.log("Failure !! Search criteria is NOT working. i.e." + "\n" + "Expected : "  
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
			System.out.println("====" + "\n" +
						"Test 4 :  Verify Search listing columns are aligned"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 4 :  Verify Search listing columns are aligned"  + "\n" +
					 	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			GlobalHeader header = new GlobalHeader(driver);
			CollectionPage collection = new CollectionPage(driver);
			
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
				Thread.sleep(3000);
				
			System.out.println("Step 3 : Select search criteria and verify Search listing columns are aligned");
			Reporter.log("Step 3 : Select search criteria and verify Search listing columns are aligned"); 		
			
				collection.SelectJurisdiction("Pennsylvania");
				collection.SelectType("Hunting");
				collection.EnterCriteria("HUNTER-TRAPPER EDUCATION (1986 - PRESENT)");
				collection.ClickOnSearchButton();
				Thread.sleep(1000);
				//Verify Element
				
				//Name On Card
				if(SeleniumFunc.IsElementPresent(collection.NameOnCard))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Name On Card is present.");
					Reporter.log("Success !! Name On Card is present."); 
				}
				else
				{
					System.out.println("Failure !! Name On Card is missing");
					Reporter.log("Failure !! Name On Card is missing");
					
					AssertFailedCount++;
				}
				
				//Agency
				if(SeleniumFunc.IsElementPresent(collection.Agency))
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
				
				
				//Total No Of Card
				if(SeleniumFunc.IsElementPresent(collection.TotalNoOfCard))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Total No Of Card is  present.");
					Reporter.log("Success !! Total No Of Card is  present."); 
				}
				else
				{
					System.out.println("Failure !! Total No Of Card is missing");
					Reporter.log("Failure !! Total No Of Card is missing");
					
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
		 * Verify that user is able to click on Collection name
		*/ 
		
		@Test
		private void ClickOnCollectionName() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 5 : Verify that user is able to click on Collection name"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 5: Verify that user is able to click on Collection name"  + "\n" +
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
				
				header.AdminDropdown.click();
				header.Admin_ManageCollection.click();
				Thread.sleep(1000);
								
			System.out.println("Step 3 : Verify Search functionality with valid data");
			Reporter.log("Step 3: Verify Search functionality with valid data"); 		
				
				collection.EnterCriteria("Hunter-Trapper Education (1986 - Present)");
				collection.ClickOnSearchButton();
				Thread.sleep(3000);
				//Verify record present
				String ActualText= collection.ValidSearch.getText().trim();
				String ExpectedText= "HUNTER-TRAPPER EDUCATION (1986 - PRESENT)";

				if(ActualText.equals(ExpectedText))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Search working properly with valid data.  i.e. " + ActualText);
					Reporter.log("Success !! Search working properly with invalid data.  i.e. " + ActualText); 
					
				}
				else
				{
					
					System.out.println("Failure !! Search is NOT working properly with valid data. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
					Reporter.log("Failure !! Search is NOT working properly with valid data. i.e." + "\n" + "Expected : "  
							+ "\n" + ExpectedText + "\n" + 
							 "Actual : " + "\n" +  ActualText);
					
					AssertFailedCount++;
				}
			
				
			System.out.println("Step 4 : Click on record and verify control is redirected to Edit Collection page");
			Reporter.log("Step 4 : Click on record and verify control is redirected to Edit Collection page"); 		
					
				
				collection.ClickOnFirstRecord();
				Thread.sleep(1000);
						
				//Verify record present
			    ActualText= editcollection.Header.getText().trim();
				ExpectedText= "Edit Certification Collection Information";

				if(ActualText.equals(ExpectedText))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Control is redirected Edit Collection page.  i.e. " + ActualText);
					Reporter.log("Success !! Control is redirected Edit Collection page.  i.e. " + ActualText); 
					
				}
				else
				{
					
					System.out.println("Failure !! Control is NOT redirected Edit Collection page. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
					Reporter.log("Failure !! Control is NOT redirected Edit Collection page. i.e." + "\n" + "Expected : "  
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
			

		
		
		/* Test 6: 
		 * Verify UI of Edit Collection page
		*/ 
		
		@Test
		private void UI_EditCollection() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 6 : Verify UI of Edit Collection page"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 6 : Verify UI of Edit Collection page"  + "\n" +
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
				Thread.sleep(2000);
				header.AdminDropdown.click();
				header.Admin_ManageCollection.click();
				Thread.sleep(2000);
				//Go to edit certification page
				collection.EnterCriteria("Hunter-Trapper Education (1986 - Present)");
				collection.ClickOnSearchButton();
				Thread.sleep(2000);
				collection.ClickOnFirstRecord();
				Thread.sleep(1000);
			System.out.println("Step 3 : Verify UI of Edit Collection Page");
			Reporter.log("Step 3 : Verify UI of Edit Collection Page"); 		
			
			
				//Verifying page header
				String ActualText= editcollection.Header.getText().trim();
			
				String ExpectedText= "Edit Certification Collection Information";

			
				if(ActualText.equals(ExpectedText))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Correct Title  present. i.e. " + ActualText);
					Reporter.log("Success !! Correct Title present. i.e. " + ActualText); 
				
				}
				else
				{
					
					System.out.println("Failure !! Incorrect Title. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
					Reporter.log("Failure !! Incorrect Title. i.e." + "\n" + "Expected : "  
							+ "\n" + ExpectedText + "\n" + 
							"Actual : " + "\n" +  ActualText);
				
					AssertFailedCount++;
				}
			
				
				
				//Verify UI Element
				
				//Collection Name field
							
				if(SeleniumFunc.IsElementPresent(editcollection.CollectionName))
				{
					System.out.println("Success !! Collection Name field is present.");
					Reporter.log("Success !! Collection Name field is present."); 
				}
				else
				{
					System.out.println("Failure !! Collection Name field is missing.");
					Reporter.log("Failure !! Collection Name field is missing.");
					
					AssertFailedCount++;
				}
				
				//State Drop-down
				if(SeleniumFunc.IsElementPresent(editcollection.State))
				{
					Thread.sleep(1000);
					System.out.println("Success !! State drop-down present. ");
					Reporter.log("Success !! State drop-down present. "); 
				}
				else
				{
					System.out.println("Failure !! State drop-down is missing.");
					Reporter.log("Failure !! State drop-down is missing.");
					
					AssertFailedCount++;
				}
				
				//Agency Name Drop-down
				if(SeleniumFunc.IsElementPresent(editcollection.Agency))
				{
					System.out.println("Success !! Agency Name Drop-down is  present.");
					Reporter.log("Success !! Agency Name Drop-down is  present."); 
				}
				else
				{
					System.out.println("Failure !! Agency Name Drop-down is missing");
					Reporter.log("Failure !! Agency Name Drop-down is missing");
					
					AssertFailedCount++;
				}
				
				//Type Drop-down
				if(SeleniumFunc.IsElementPresent(editcollection.Type))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Type Drop-down is  present.");
					Reporter.log("Success !! Type Drop-down is  present."); 
				}
				else
				{
					System.out.println("Failure !! Type Drop-down is missing");
					Reporter.log("Failure !! Type Drop-down is missing");
					
					AssertFailedCount++;
				}
				
				
				//Name on card field			
				if(SeleniumFunc.IsElementPresent(editcollection.CollectionName))
				{
					System.out.println("Success !! Name on card field is present.");
					Reporter.log("Success !! Name on card field is present."); 
				}
				else
				{
					System.out.println("Failure !! Name on card field is missing.");
					Reporter.log("Failure !! Name on card field is missing.");
					
					AssertFailedCount++;
				}
				
				//Card price field			
				if(SeleniumFunc.IsElementPresent(editcollection.CardPrice))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Card price field is present.");
					Reporter.log("Success !! Card price field is present."); 
				}
				else
				{
					System.out.println("Failure !! Card price field is missing.");
					Reporter.log("Failure !! Card price field is missing.");
					
					AssertFailedCount++;
				}
				
				//Save Button
				if(SeleniumFunc.IsElementPresent(editcollection.Save))
				{
					System.out.println("Success !! Save Button present.");
					Reporter.log("Success !! Save Button present."); 
				}
				else
				{
					System.out.println("Failure !! Save Button is missing");
					Reporter.log("Failure !! Save Button is missing");
					
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
		 * Verify Edit Collection functionality
		*/ 
		
		@Test
		private void VerifyEditCollectionFunctionality() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 7 : Verify Edit Collection functionality"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 7 : Verify Edit Collection functionality"  + "\n" +
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
				Thread.sleep(2000);
				header.AdminDropdown.click();
				Thread.sleep(1000);
				header.Admin_ManageCollection.click();
				Thread.sleep(1000);
				//Go to edit certification page
				collection.EnterCriteria("Hunter-Trapper Education (1986 - Present)");
				Thread.sleep(1000);
				collection.ClickOnSearchButton();
				Thread.sleep(1000);
				collection.ClickOnFirstRecord();
				Thread.sleep(1000);
				
			System.out.println("Step 3 : Verify UI of Edit Collection Page");
			Reporter.log("Step 3 : Verify UI of Edit Collection Page"); 		
			
			
				//Verifying page header
				String ActualText= editcollection.Header.getText().trim();
			
				String ExpectedText= "Edit Certification Collection Information";
				Thread.sleep(1000);
			
				if(ActualText.equals(ExpectedText))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Correct Title  present. i.e. " + ActualText);
					Reporter.log("Success !! Correct Title present. i.e. " + ActualText); 
				
				}
				else
				{
					
					System.out.println("Failure !! Incorrect Title. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
					Reporter.log("Failure !! Incorrect Title. i.e." + "\n" + "Expected : "  
							+ "\n" + ExpectedText + "\n" + 
							"Actual : " + "\n" +  ActualText);
				
					AssertFailedCount++;
				}
			
				//Change Name
				
				editcollection.EnterNameOnCard("Kalkomey");
				Thread.sleep(1000);
				editcollection.ClickOnSaveButton();
				Thread.sleep(1000);
				//Success Message
				ActualText= editcollection.Success.getText().trim();
				
				ExpectedText= "Certification Collection was successfully updated.";

			
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
					
				
				ActualText = editcollection.NameOnCard.getAttribute("value");
				ExpectedText = "Kalkomey";
				
				if(ActualText.equals(ExpectedText))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Name on card updated successfully. i.e. " + ActualText);
					Reporter.log("Success !! Name on card updated successfully. i.e. " + ActualText); 
				
				}
				else
				{
					
					System.out.println("Failure !! Name on card is NOT updated. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + 
									 "Actual : " + "\n" +  ActualText);
					Reporter.log("Failure !! Name on card is NOT updated. i.e." + "\n" + "Expected : "  
							+ "\n" + ExpectedText + "\n" + 
							 "Actual : " + "\n" +  ActualText);
				
					AssertFailedCount++;
				}
				
				//Reset the field
				editcollection.EnterNameOnCard("Basic HTE");
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
		
		
		/* Test 8: 
		 * Verify pagination is working
		*/ 
		
		@Test
		private void VerifyPagination() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 8 : Verify pagination is working"  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 8 : Verify pagination is working"  + "\n" +
					 	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			GlobalHeader header = new GlobalHeader(driver);
			CollectionPage collection = new CollectionPage(driver);
			
			System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
			Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
				
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
		
			System.out.println("Step 2 : Login to application and navigating to Collection page");
			Reporter.log("Step 2 : Login to application and navigating to Collection page"); 	
			
				login.EnterUsername(Constants.CM_Username);
				login.EnterPassword(Constants.CM_Password);
				login.ClickOnLogInButton();
				Thread.sleep(2000);
				header.AdminDropdown.click();
				header.Admin_ManageCollection.click();
				Thread.sleep(1000);
				
			System.out.println("Step 3 : Verify pagination");
			Reporter.log("Step 3 : Verify pagination"); 		

					
				//Verify Next link is working
				collection.ClickOnNext();
				Thread.sleep(1000);
				String ActualText= driver.getCurrentUrl();
				String ExpectedText="https://kps.staging.kalkomey.com/certification_collections/search?page=2";

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
				
				SeleniumFunc.ClickOnElement("css", ".pagination.pagination li:nth-of-type(4) a");
				Thread.sleep(2000);	
				String ActualText1= driver.getCurrentUrl();
				String ExpectedText1= "https://kps.staging.kalkomey.com/certification_collections/search?page=3";

				if(ActualText1.equals(ExpectedText1))
				{
					Thread.sleep(1000);	
					System.out.println("Success !! Specific page  link is working, control is redirected to expected page.  i.e. " + ActualText1);
					Reporter.log("Success !! Specific page  link is working, control is redirected to expected page.  i.e. " + ActualText1); 
					
				}
				else
				{
						
					System.out.println("Failure !! Specific page link is NOT working. i.e." + "\n" + "Expected Page: "  
									+ "\n" + ExpectedText1+ "\n" + 
									 "Actual Page: " + "\n" +  ActualText1);
					Reporter.log("Failure !! Specific page link is NOT working. i.e." + "\n" + "Expected Page: "  
							+ "\n" + ExpectedText1+ "\n" + 
							 "Actual Page: " + "\n" +  ActualText1);
					
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
		 * Verify "Printable card' is tagged next to collections that are printableVerify "Printable card' is tagged next to collections that are printable
		*/ 
		
		@Test
		private void Printablecard() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 9 : Verify Printable card is tagged next to collections that are printableVerify Printable card is tagged next to collections that are printable	"
						  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
					"Test 9 : Verify Printable card is tagged next to collections that are printableVerify Printable card is tagged next to collections that are printable	"
					  + "\n" +
		 			"====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			GlobalHeader header = new GlobalHeader(driver);
			CollectionPage collection = new CollectionPage(driver);
			
			System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
			Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
				
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
		
			System.out.println("Step 2 : Login to application and navigating to Collection page");
			Reporter.log("Step 2 : Login to application and navigating to Collection page"); 	
			
				login.EnterUsername(Constants.CM_Username);
				login.EnterPassword(Constants.CM_Password);
				login.ClickOnLogInButton();
				
				header.AdminDropdown.click();
				header.Admin_ManageCollection.click();
				Thread.sleep(1000);				
				
				System.out.println("Step 3 : Verify Search functionality with valid data");
				Reporter.log("Step 3: Verify Search functionality with valid data"); 		
				
					collection.EnterCriteria("Hunter-Trapper Education (1986 - Present)");
					Thread.sleep(1000);
					collection.ClickOnSearchButton();
					Thread.sleep(1000);
					//Verify record present
					String ActualText= collection.ValidSearch.getText().trim();
					String ExpectedText= "HUNTER-TRAPPER EDUCATION (1986 - PRESENT)";

					if(ActualText.equals(ExpectedText))
					{
						
						System.out.println("Success !! Search working properly with valid data.  i.e. " + ActualText);
						Reporter.log("Success !! Search working properly with invalid data.  i.e. " + ActualText); 
					
					}
					else
					{
						
						System.out.println("Failure !! Search is NOT working properly with valid data. i.e." + "\n" + "Expected : "  
										+ "\n" + ExpectedText + "\n" + 
										 "Actual : " + "\n" +  ActualText);
						Reporter.log("Failure !! Search is NOT working properly with valid data. i.e." + "\n" + "Expected : "  
								+ "\n" + ExpectedText + "\n" + 
								 "Actual : " + "\n" +  ActualText);
					
						AssertFailedCount++;
					}
			
				
					//Verify printable card icon is present
					if(SeleniumFunc.IsElementPresent(collection.PrintableCard))
					{
						Thread.sleep(1000);
						System.out.println("Success !! Printable Card icon is present.");
						Reporter.log("Success !! Printable Card icon is present."); 
					}
					else
					{
						System.out.println("Failure !! Printable Card icon is missing");
						Reporter.log("Failure !! Printable Card icon is missing");
						
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
			 * Test Changing a Collection State
			*/ 
			
			@Test
			private void SearchResultByChangingState() throws Exception
			{
				System.out.println("====" + "\n" +
							"Test 10 : Test Changing a Collection State"  + "\n" +
				 			"====");
				Reporter.log("====" + "\n" +
				 			  "Test 10 : Test Changing a Collection State"  + "\n" +
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
					
				System.out.println("Step 3 : Select search criteria and verify record displaying as per criteria");
				Reporter.log("Step 3 : Select search criteria and verify record displaying as per criteria"); 		
				
					collection.SelectJurisdiction("Alaska");
					collection.SelectType("Hunting");
					collection.ClickOnSearchButton();
					Thread.sleep(2000);
					//Verify No of records 
					
					//Count before changes
					List<WebElement> CountBefore = driver.findElements(By.cssSelector(".search-results ol li"));
					System.out.println(CountBefore.size());
					Thread.sleep(2000);
					collection.ClickOnFirstRecord();
					editcollection.SelectState("Arizona");
					editcollection.ClickOnSaveButton();
					Thread.sleep(2000);
					driver.get(Constants.ApplicationURL_CM + "/certification_collections/search");
					Thread.sleep(3000);
					collection.SelectJurisdiction("Alaska");
					collection.SelectType("Hunting");
					collection.ClickOnSearchButton();
					Thread.sleep(2000);
					//Count after changes
					List<WebElement> CountAfter = driver.findElements(By.cssSelector(".search-results ol li"));
					System.out.println(CountAfter.size());
					Thread.sleep(1000);
					if(CountBefore.size()-1 == CountAfter.size())
					{
						Thread.sleep(1000);
						System.out.println("Success !! Search by Changing a Collection State is working properly. ");
						Reporter.log("Success !! Search by Changing a Collection State is working properly. "); 
					
					}
					else
					{
						Thread.sleep(1000);
						System.out.println("Failure !! Search by Changing a Collection State is NOT working properly.");
						Reporter.log("Failure !! Search by Changing a Collection State is NOT working properly.");
					
						AssertFailedCount++;
					}
					
					
					Thread.sleep(1000);
					header.AdminDropdown.click();
					Thread.sleep(1000);
					header.Admin_ManageCollection.click();
					Thread.sleep(4000);
					//Reset the changes
					collection.SelectJurisdiction("Arizona");
					collection.SelectType("Hunting");
					collection.ClickOnSearchButton();
					Thread.sleep(3000);
					collection.ClickOnFirstRecord();
					Thread.sleep(1000);
					editcollection.SelectState("Alaska");
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
