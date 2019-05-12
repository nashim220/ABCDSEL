package products.CM;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.AgencyImporterPage;
import pageFactory.CM.LoginPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class AgencyImporterTest 
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
	 * Verify agency importer functionality with invalid file
	*/ 
	@Test
	private void AgencyImportBadFile() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify agency importer functionality with invalid file"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify agency importer functionality with invalid file"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyImporterPage agencyimport = new AgencyImporterPage(driver);
		
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Agency Importer page");
		Reporter.log("Step 2 : Login to application and navigating to Agency Importer page"); 	
		
			login.EnterUsername(Constants.CM_Username_AgencyImport);
			login.EnterPassword(Constants.CM_Password_AgencyImport);
			login.ClickOnLogInButton();
			
			agencyimport.ClickOnAgencyImporterTab();
			
			
		System.out.println("Step 3 : Try to import bad file and verify error message");
		Reporter.log("Step 3 : Try to import bad file and verify error message"); 		
		
			File file = new File("src/test/resources/BadFile.csv");
			agencyimport.SendPath(file.getAbsolutePath());
			Thread.sleep(2000);
			agencyimport.ClickOnUploadButton();
			Thread.sleep(3000);

			//Verifying page header
			String actualtext= agencyimport.ErrorMessage.getText().trim();
			
			String expectedtext= "We found the following errors. If you need help resolving them, " +
					"please contact KPS customer support." + "\n" +
					"We noticed some invalid characters in the file you uploaded. Please check the file and try again.";

			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Correct validation is displayed for bad file import. i.e. " + expectedtext);
				Reporter.log("Success !! Correct validation is displayed for bad file import. i.e. " + expectedtext); 
			}
			else
			{
				System.out.println("Failure !! No validation for bad file import. i.e." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !! No validation for bad file import. i.e." + "\n" + "Expected : "  
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
	 * Verify agency importer functionality with good file
	*/ 
	@Test
	private void AgencyImportGoodFile() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify agency importer functionality with good file"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify agency importer functionality with good file"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyImporterPage agencyimport = new AgencyImporterPage(driver);
		
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Agency Importer page");
		Reporter.log("Step 2 : Login to application and navigating to Agency Importer page"); 	
		
			login.EnterUsername(Constants.CM_Username_AgencyImport);
			login.EnterPassword(Constants.CM_Password_AgencyImport);
			login.ClickOnLogInButton();
			
			agencyimport.ClickOnAgencyImporterTab();
			
			
		System.out.println("Step 3 : Try to import good file and verify success message");
		Reporter.log("Step 3 : Try to import good file and verify success message"); 		
		
			File file = new File("src/test/resources/GoodFile.csv");
			agencyimport.SendPath(file.getAbsolutePath());
			Thread.sleep(2000);
			agencyimport.ClickOnUploadButton();
			Thread.sleep(3000);

			//Verifying page header
			String actualtext= agencyimport.SuccessMessage.getText().trim();
			
			String expectedtext= "Success! Everything looks great. We will email you when the import is complete.";

			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! File imported successfully. i.e. " + actualtext);
				Reporter.log("Success !! File imported successfully. i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Error in file import. i.e." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !! Error in file import. i.e." + "\n" + "Expected : "  
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
	 * Verify agency importer functionality with Unauthorized User
	*/ 
	@Test
	private void AgencyImportUnauthorizedUser() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify agency importer functionality with Unauthorized User"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify agency importer functionality with Unauthorized User"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyImporterPage agencyimport = new AgencyImporterPage(driver);
		
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application");
		Reporter.log("Step 2 : Login to application"); 	
		
			login.EnterUsername(Constants.CM_User_Agency_Unauthorized);
			login.EnterPassword(Constants.CM_Pwd_Agency_Unauthorized);
			login.ClickOnLogInButton();
		
				
		System.out.println("Step 3 : Navigate to agency importer and verify error message for unauthorized user");
		Reporter.log("Step 3 : Navigate to agency importer and verify error message for unauthorized user"); 		
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM + "/agency/importer");
			
			//Verifying page header
			String actualtext= agencyimport.NotAuthorize.getText().trim();
			
			String expectedtext= "You are not authorized to look at this page.";

			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! User is not authorized. i.e. " + actualtext);
				Reporter.log("Success !! User is not authorized. i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Unathorized user is able to redirect to agency importer. i.e." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !! Unathorized user is able to redirect to agency importer. i.e." + "\n" + "Expected : "  
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
	 * Verify agency importer functionality with user where Agency not setup
	*/ 
	@Test
	private void AgencyImportNotSetup() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify agency importer functionality with user where Agency not setup"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify agency importer functionality with user where Agency not setup"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyImporterPage agencyimport = new AgencyImporterPage(driver);
		
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Agency Importer page");
		Reporter.log("Step 2 : Login to application and navigating to Agency Importer page"); 	
		
			login.EnterUsername(Constants.CM_Username_Verginia);
			login.EnterPassword(Constants.CM_Password_Verginia);
			login.ClickOnLogInButton();
			
			agencyimport.ClickOnAgencyImporterTab();
			
			
		System.out.println("Step 3 : Verify Agency Imported setup is not there");
		Reporter.log("Step 3 : Verify Agency Imported setup is not there"); 		
		

		String actualtext= agencyimport.WarningMessage.getText().trim();
			
			String expectedtext= "Whoops! It doesn't look like you're set up for this yet. Please contact KPS Customer Support.";

			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Agency Imported setup is not there. i.e. " + actualtext);
				Reporter.log("Success !! Agency Imported setup is not there. i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! User is able to import a file. i.e." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !! User is able to import a file. i.e." + "\n" + "Expected : "  
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
	 * Verify The note about Agency Import date formats is displayed
	*/ 
	
	@Test
	private void DateFormatNote() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify The note about Agency Import date formats is displayed"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify The note about Agency Import date formats is displayed"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyImporterPage agencyimport = new AgencyImporterPage(driver);
		
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Agency Importer page");
		Reporter.log("Step 2 : Login to application and navigating to Agency Importer page"); 	
		
			login.EnterUsername(Constants.CM_Username_AgencyImport);
			login.EnterPassword(Constants.CM_Password_AgencyImport);
			login.ClickOnLogInButton();
			
			agencyimport.ClickOnAgencyImporterTab();
			
			
		System.out.println("Step 3 : Verify note is displayed for invalid date format");
		Reporter.log("Step 3 : Verify note is displayed for invalid date format"); 		
		
			//Verify unauthorized access message 
			if(SeleniumFunc.IsElementPresent(agencyimport.DateFormatNote))
			{
				System.out.println("Success !! Note is displayed for invalid date format");
				Reporter.log("Success !! Note is displayed for invalid date format"); 
			}
			else
			{
				System.out.println("Failure !! Note is NOT displayed for invalid date format");
				Reporter.log("Failure !! Note is NOT displayed for invalid date format"); 
			
				AssertFailedCount++;
			}
			
			//Verifying Note title
			String actualtext= agencyimport.DateFormatNote.getText().trim();
			
			String expectedtext= "A note about date formatting";

			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Correct title is displayed for Date format Note. i.e. " + expectedtext);
				Reporter.log("Success !! Correct title is displayed for Date format Note. i.e. " + expectedtext); 
			}
			else
			{
				System.out.println("Failure !! Incorrect title for invalid date format note. i.e." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !! Incorrect title for invalid date format note. i.e." + "\n" + "Expected : "  
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
	
	
	
	
	/* Test 6: 
	 * Verify validation for file with invalid date format
	*/ 
	@Test
	private void FileWithInvalidDateFormat() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify validation for file with invalid date format"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Verify validation for file with invalid date format"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyImporterPage agencyimport = new AgencyImporterPage(driver);
		
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Agency Importer page");
		Reporter.log("Step 2 : Login to application and navigating to Agency Importer page"); 	
		
			login.EnterUsername(Constants.CM_Username_AgencyImport);
			login.EnterPassword(Constants.CM_Password_AgencyImport);
			login.ClickOnLogInButton();
			
			agencyimport.ClickOnAgencyImporterTab();
			
			
		System.out.println("Step 3 : Try to import file with invalid date format and verify error message");
		Reporter.log("Step 3 : Try to import file with invalid date format and verify error message"); 		
		
			File file = new File("src/test/resources/WrongYearFormat.csv");
			agencyimport.SendPath(file.getAbsolutePath());
			Thread.sleep(2000);
			agencyimport.ClickOnUploadButton();
			Thread.sleep(3000);

			//Verifying page header
			String actualtext= agencyimport.ErrorMessage.getText().trim();
			
			String expectedtext= "We found the following errors. If you need help resolving them, " +
					"please contact KPS customer support." + "\n" +
					"Your file contains rows without values in the certification_name column. Please check your file and ensure that every row contains an import key from the list of valid import keys below."+ "\n" +
					"Error parsing date '10/17/97'. Please reformat as mm/dd/yyyy and resubmit." + "\n" +
					"Error parsing date '7/7/84'. Please reformat as mm/dd/yyyy and resubmit." + "\n" +
					"Error parsing date '10/25/63'. Please reformat as mm/dd/yyyy and resubmit." + "\n" +
					"54 additional date format errors.";

			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("Success !! Correct validation is displayed for file with invalid date format. i.e. " + expectedtext);
				Reporter.log("Success !! Correct validation is displayed for file with invalid date format. i.e. " + expectedtext); 
			}
			else
			{
				System.out.println("Failure !! No validation for file with invalid date format. i.e." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !! No validation for file with invalid date format. i.e." + "\n" + "Expected : "  
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
	 * Confirm that file with records missing an original_id are rejected
	*/ 
	@Test
	private void File_MissingOriginalId() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Confirm that file with records missing an original_id are rejected"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Confirm that file with records missing an original_id are rejected"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyImporterPage agencyimport = new AgencyImporterPage(driver);
		
		
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login to application and navigating to Agency Importer page");
		Reporter.log("Step 2 : Login to application and navigating to Agency Importer page"); 	
		
			login.EnterUsername(Constants.CM_Username_AgencyImport);
			login.EnterPassword(Constants.CM_Password_AgencyImport);
			login.ClickOnLogInButton();
			
			agencyimport.ClickOnAgencyImporterTab();
			
			
		System.out.println("Step 3 : Try to import file with records missing an original_id and verify error message");
		Reporter.log("Step 3 : Try to import file with records missing an original_id and verify error message"); 		
		
			File file = new File("src/test/resources/MissingOriginID.csv");
			agencyimport.SendPath(file.getAbsolutePath());
			Thread.sleep(2000);
			agencyimport.ClickOnUploadButton();
			Thread.sleep(3000);

			//Verifying page header
			String actualtext= agencyimport.ErrorMessage.getText().trim();
			
			String expectedtext= "We found the following errors. If you need help resolving them," +
					" please contact KPS customer support." + "\n" +
					"It looks like at least one of the following required columns is missing in the file you uploaded: last_name, first_name, middle_name, suffix, email, gender, dob, p_address1, p_address2, p_city, p_state, p_country, p_zip, p_phone, s_address1, s_address2, s_city, s_state, s_country, s_zip, s_phone, original_id, issued_at, certification_name, certification_provider, class_type. Please check your file and try again." + "\n" +
					"Your file contains rows without values in the certification_name column. Please check your file and ensure that every row contains an import key from the list of valid import keys below."+"\n" +
					"Your file has 5 rows that are missing an original_id. Each row must have a populated original_id. Please check your file and try again.";

			Thread.sleep(2000);
			if(actualtext.equals(expectedtext))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Correct validation is displayed for file with records missing an original_id. i.e. " + expectedtext);
				Reporter.log("Success !! Correct validation is displayed for file with records missing an original_id. i.e. " + expectedtext); 
			}
			else
			{
				System.out.println("Failure !! No validation for file with records missing an original_id. i.e." + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
				Reporter.log("Failure !! No validation for file with records missing an original_id. i.e." + "\n" + "Expected : "  
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
