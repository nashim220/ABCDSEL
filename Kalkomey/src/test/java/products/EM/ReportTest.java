package products.EM;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod; 
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.EM.AdminSearchEventPage;
import pageFactory.EM.AgencyHomePage;
import pageFactory.EM.EMAddStudentPage;
import pageFactory.EM.EMStudentRegistrationPage;
import pageFactory.EM.ErrorPage;
import pageFactory.EM.InstructorCreateEventPage;
import pageFactory.EM.InstructorEventRosterPage;
import pageFactory.EM.InstructorLocationPage;
import pageFactory.EM.InstructorSchedulePage;
import pageFactory.EM.LoginPage;
import pageFactory.EM.RegisterEdEventView;
import pageFactory.EM.RegisterEdRegisterStudentPage;
import pageFactory.EM.ReportPage;
import pageFactory.EM.StudentResultPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class ReportTest 
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
	 * Verify Student Demographics Summary Report With Ethnicity
	 */ 
	@Test
	private void StudentDemographicsSummaryWithEthnicity() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify Student Demographics Summary Report With Ethnicity"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify Student Demographics Summary Report With Ethnicity"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		ReportPage report = new ReportPage(driver);		
		
		System.out.println("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");				
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Navigate to the Student Demographics report to an agency that collects race/ethnicity");
		Reporter.log("Step 2 : Navigate to the Student Demographics report to an agency that collects race/ethnicity"); 
				
						
			SeleniumFunc.ClickOnElement("linkText", "Pennsylvania Game Commission");
			Thread.sleep(5000);

		
		System.out.println("Step 3 : Select a program and go to student demographics report");
		Reporter.log("Step 3 : Select a program and go to Program Settings"); 
			
		
		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);

			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/report/view/student_demographics");				
			Thread.sleep(5000);

		  	
		System.out.println("Step 4 : Verify Title for Report Page");
		Reporter.log("Step 4 : Verify Title for Report Page");
		
				
			String Actual = report.Title.getText().trim();
			String Expected = "Student Demographics Summary for Specified Events";
					
			if(Actual.equals(Expected))
			{
	
				System.out.println("Success !! Title is correct for Report.");
				Reporter.log("Success !! Title is correct for Report.");
			
			}
			else
			{
			
				System.out.println("Failure !! Title is NOT correct for Report." + "\n" + "Expected : "  										+ "\n" + Expected + "\n" + 
										 "Actual: " + "\n" +  Actual);
				Reporter.log("Failure !! Title is NOT correct for Report." + "\n" + "Expected : "  
						+ "\n" + Expected + "\n" + 
						 "Actual: " + "\n" +  Actual);
						
				AssertFailedCount++;
			
			}		  		
				
	
		System.out.println("Step 5 : Select a date range for a report");
		Reporter.log("Step 5 : Select a date range for a report");		
					
		
			report.EnterStartDate("01/01/2014");
			report.ClickOnApply();
			Thread.sleep(5000);

			
			//Verify Report is generated with category as Ethnicity	
			if(SeleniumFunc.IsElementPresent("css", "#dataghead_0 td b"))
			{
				Expected = SeleniumFunc.GetElementText("css", "#dataghead_0 td b");
				if(Expected.equalsIgnoreCase("Ethnicity"))
				{
					System.out.println("Success !! Report with correct category is displayed. i.e. Ethnicity");
					Reporter.log("Success !! Report with correct category is displayed. i.e. Ethnicity");
				}
			}
			else
			{
				System.out.println("Failure !! Report is NOT displayed with correct category");
				Reporter.log("Failure !! Report is NOT displayed with correct category");
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
	

	/* Test 2: 
	 * Verify Student Demographics Summary Report With Gender
	 */ 
	@Test
	private void StudentDemographicsSummaryWithGender() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify Student Demographics Summary Report With Gender"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify Student Demographics Summary Report With Gender"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		ReportPage report = new ReportPage(driver);		
		
		System.out.println("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");				
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Navigate to the Student Demographics Report for an agency that collects gender (not collect race/ethnicity)");
		Reporter.log("Step 2 : Navigate to the Student Demographics Report for an agency that collects gender (not collect race/ethnicity)"); 
				
						
			SeleniumFunc.ClickOnElement("linkText", "Arkansas Game and Fish Commission");
			Thread.sleep(5000);

		
		System.out.println("Step 3 : Select a program and go to student demographics report");
		Reporter.log("Step 3 : Select a program and go to student demographics report"); 
			
		
		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);

			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/report/view/student_demographics");				
			Thread.sleep(5000);

		  	
		System.out.println("Step 4 : Verify Title for Report Page");
		Reporter.log("Step 4 : Verify Title for Report Page");
		
				
			String Actual = report.Title.getText().trim();
			String Expected = "Student Demographics Summary for Specified Events";
					
			if(Actual.equals(Expected))
			{
				System.out.println("Success !! Title is correct for Report.");
				Reporter.log("Success !! Title is correct for Report.");		
			}
			else
			{		
				System.out.println("Failure !! Title is NOT correct for Report." + "\n" + "Expected : "  		
										+ "\n" + Expected + "\n" + 
										 "Actual: " + "\n" +  Actual);
				Reporter.log("Failure !! Title is NOT correct for Report." + "\n" + "Expected : "  
						+ "\n" + Expected + "\n" + 
						 "Actual: " + "\n" +  Actual);
						
				AssertFailedCount++;			
			}		  		
				
	
		System.out.println("Step 5 : Select a date range for a report");
		Reporter.log("Step 5 : Select a date range for a report");		
					
		
			report.EnterStartDate("01/01/2014");
			report.ClickOnApply();
			Thread.sleep(5000);
			
			//Verify Report is generated with category as Gender	
			if(SeleniumFunc.IsElementPresent("css", "#dataghead_0 td b"))
			{
				Expected = SeleniumFunc.GetElementText("css", "#dataghead_0 td b");
				if(Expected.equalsIgnoreCase("Gender"))
				{
					System.out.println("Success !! Report with correct category is displayed. i.e. Gender");
					Reporter.log("Success !! Report with correct category is displayed. i.e. Gender");
				}
			}
			else
			{
				System.out.println("Failure !! Report is NOT displayed with correct category");
				Reporter.log("Failure !! Report is NOT displayed with correct category");
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
	 * Verify Report for People Who Signed Up For A Wait List, But Did Not Register.
	 */ 	
	@Test
	private void WaitListeeReports() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify Report for People Who Signed Up For A Wait List, But Did Not Register."  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify Report for People Who Signed Up For A Wait List, But Did Not Register."  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		ReportPage report = new ReportPage(driver);		
		
		System.out.println("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");				
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Select agency and go to Wait Listee Reports");
		Reporter.log("Step 2 : Select agency and go to Wait Listee Reports"); 
				
						
			SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
			Thread.sleep(5000);

			SeleniumFunc.ClickOnElement("linkText", "Colorado Hunter Education Course");
		  	//agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);

			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/report/view/wait_list");				
			Thread.sleep(80000);
		
			/*
			System.out.println("Step 3 : Select a date range for a report");
			Reporter.log("Step 3 : Select a date range for a report");	
			
			report.EnterStartDate("01/01/2014");
			report.ClickOnApply();
			Thread.sleep(5000);
			
			
			if(SeleniumFunc.IsElementPresent("css", ".js div:nth-of-type(6) a span"))
			{
				SeleniumFunc.ClickOnElement("css", ".js div:nth-of-type(6) a span");
				Thread.sleep(5000);

			}
			
		*/
		System.out.println("Step 4 : Verify Title for Report Page");
		Reporter.log("Step 4 : Verify Title for Report Page");
		
				
			String Actual = report.Title.getText().trim();
			String Expected = "People On A Wait List Who Did Not Register";
					
			if(Actual.equals(Expected))
			{
	
				System.out.println("Success !! Title is correct for Report.");
				Reporter.log("Success !! Title is correct for Report.");
			
			}
			else
			{
			
				System.out.println("Failure !! Title is NOT correct for Report." + "\n" + "Expected : "  										+ "\n" + Expected + "\n" + 
										 "Actual: " + "\n" +  Actual);
				Reporter.log("Failure !! Title is NOT correct for Report." + "\n" + "Expected : "  
						+ "\n" + Expected + "\n" + 
						 "Actual: " + "\n" +  Actual);
						
				AssertFailedCount++;
			
			}		  		
				
	
		System.out.println("Step 4 : Select a date range for a report");
		Reporter.log("Step 4 : Select a date range for a report");		
					
		
			report.EnterStartDate("01/01/2014");
			report.ClickOnApply();
			Thread.sleep(5000);
			
			//Verify Report is generated
			Actual = SeleniumFunc.GetElementText("css", ".ui-paging-info");
			Expected = "No records to view";
			
			if(!Expected.equalsIgnoreCase(Actual))
			{		
			
				System.out.println("Success !! Report is generated");
				Reporter.log("Success !! Report is generated");
				
			}
			else
			{
			
				System.out.println("Failure !! Report is NOT displayed");
				Reporter.log("Failure !! Report is NOT displayed");
			
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
	 * Verify Student Demographics Summary Report With Ethnicity
	 */ 
	@Test
	private void InstructorAuditReport_InstructorDetails() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify Student Demographics Summary Report With Ethnicity"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify Student Demographics Summary Report With Ethnicity"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		ReportPage report = new ReportPage(driver);		
		
		System.out.println("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");				
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Navigate to the Student Demographics report to an agency that collects race/ethnicity");
		Reporter.log("Step 2 : Navigate to the Student Demographics report to an agency that collects race/ethnicity"); 
				
						
			SeleniumFunc.ClickOnElement("linkText", "Demo Agency");
			Thread.sleep(5000);

		
		System.out.println("Step 3 : Select a program and go to student demographics report");
		Reporter.log("Step 3 : Select a program and go to student demographics report"); 
			
		
		  	agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);

			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/report/view/instructor_audit");				
			Thread.sleep(5000);

			report.EnterStartDate("01/01/2014");
			report.ClickOnApply();
			Thread.sleep(6000);
			
		System.out.println("Step 4 : Verfiy Instructor Details are added to Instructor Audit Report");
		Reporter.log("Step 4 : Verfiy Instructor Details are added to Instructor Audit Report");
		
			String Actual = report.GetAllColumnInstructorAudit();
			//System.out.println(Actual);
			
			String Expected = "Program NameEvent IDEvent NameEvent DateInstructor IDLast NameFirst Name";
			if(Actual.equalsIgnoreCase(Expected))
			{
				System.out.println("Success !! Report with instructor details is present.");
				Reporter.log("Success !! Report with instructor details is present.");
			}
			else
			{
				System.out.println("Failure !! Instructor details are missing. Actual : "+Actual + " Expected : "+Expected);
				Reporter.log("Failure !! Instructor details are missing. Actual : "+Actual + " Expected : "+Expected);
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
	 * Verify As an agency administrator using reports, I'd like to be able to select one or more programs when running reports 
	 * so that I can run reports for more than one program without having to select "All programs".
	 */ 
	@Test
	private void Program_Selection() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify administrator can select more than one program to filter reports "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify administrator can select more than one program to filter reports"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		ReportPage report = new ReportPage(driver);		
		
		System.out.println("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as Super Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");				
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Select Agency");
		Reporter.log("Step 2 : Select Agency"); 
				
						
			SeleniumFunc.ClickOnElement("linkText", "Arkansas Game and Fish Commission");
			Thread.sleep(5000);
			

		
		System.out.println("Step 3 : Click on Reports and goto Inactive Instructors List");
		Reporter.log("Step 3 : Click on Reports and goto Inactive Instructors List"); 
			
			SeleniumFunc.ClickOnElement("linkText", "Reports");
		  	//agencyhome.ClickToSelectProgram();
			Thread.sleep(5000);
			SeleniumFunc.ClickOnElement("linkText", "Inactive Instructors List");
			Thread.sleep(5000);
		
		System.out.println("Step 4 : Click on Program Dropdown List and click De-select all button");
		Reporter.log("Step 4 : Click on Program Dropdown List and click De-select all button"); 	
			
			SeleniumFunc.ClickOnElement("css", "#filterpanel > div:nth-child(2) > div > button");
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("css", "#filterpanel > div:nth-child(2) > div > div > div > div > button.actions-btn.bs-deselect-all.btn.btn-default");
			
		System.out.println("Step 5 : Select More then two programs from dropdown and click apply button");
		Reporter.log("Step 5 : Select More then two programs from dropdown and click apply button"); 		
			
			SeleniumFunc.ClickOnElement("css", "#filterpanel > div:nth-child(2) > div > div > ul > li:nth-child(7) > a > span.text");
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("css", "#filterpanel > div:nth-child(2) > div > div > ul > li:nth-child(6) > a > span.text");
			Thread.sleep(2000);
			SeleniumFunc.ClickOnElement("css", ".btn.dropdown-toggle.btn-default");
			SeleniumFunc.ClickOnElement("css", "#filterapply");
		
		System.out.println("Step 6 : Verify after applying filters it should display records");
		Reporter.log("Step 6 : Verify after applying filters it should display records"); 
		
			String actualtext = SeleniumFunc.GetElementText("xpath", ".//*[@id='2159843']/td[2]");
						
			if(actualtext.contains("AARON"))
			{
				System.out.println("Success !! Report with instructor details is present.");
				Reporter.log("Success !! Report with instructor details is present.");
			}
			else
			{
				System.out.println("Failure !! Instructor details are missing.");
				Reporter.log("Failure !! Instructor details are missing.");
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
	 * Verify Student Demographics Summary Report With Gender
	 */ 
	@Test
	private void Report_Link() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify user able to navigate to an agency's reports"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify user able to navigate to an agency's reports"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		ReportPage report = new ReportPage(driver);		
		
		System.out.println("Step 1 : Log into Event Manager as a KE user : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Log into Event Manager as a KE user : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");				
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);

		
		System.out.println("Step 2 : Choose an agency.");
		Reporter.log("Step 2 : Choose an agency."); 
				
						
			SeleniumFunc.ClickOnElement("linkText", "Arkansas Game and Fish Commission");
			Thread.sleep(5000);
			
		System.out.println("Step 3 : On the left-side, user able to see Reports under the AGENCY heading.");
		Reporter.log("Step 3 : On the left-side, user able to see Reports under the AGENCY heading."); 
		
			String Expected = "Reports";
			String actual = SeleniumFunc.GetElementText("linkText", "Reports");
			
			if(actual.equalsIgnoreCase(Expected))
			{
				System.out.println("Success !! Report link is present.");
				Reporter.log("Success !! Report link is present.");
			}
			else
			{
				System.out.println("Failure !! Report link is missing. Actual : "+actual + " Expected : "+Expected);
				Reporter.log("Failure !! Report link is missing. Actual : "+actual + " Expected : "+Expected);
				AssertFailedCount++;
			}
	
		System.out.println("Step 3 : Verify user able to see a list of the reports");
		Reporter.log("Step 3 :Verify user able to see a list of the reports"); 
		
			SeleniumFunc.ClickOnElement("linkText", "Reports");
			Thread.sleep(5000);
			
			if(SeleniumFunc.IsElementPresent("css", ".content.clearfix>dl>dt"))
			{
				System.out.println("Success !! User able to see a list of the reports");
				Reporter.log("Success !! User able to see a list of the reports");
			}
			else
			{
				System.out.println("Failure !! User not able to see a list of the reports");
				Reporter.log("Failure !! User not able to see a list of the reports");
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
	 * Verify Admin or Instructor able to add "No Show" as an option to the final grade dropdown list
	 */ 
	@Test
	private void GradeOption_No_Show() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify Admin or Instructor able to add No Show as an option to the final grade dropdown list"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify Admin or Instructor able to add No Show as an option to the final grade dropdown list"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		AgencyHomePage agencyhome= new AgencyHomePage(driver);
		ReportPage report = new ReportPage(driver);	
		InstructorCreateEventPage instructorcreateevent = new InstructorCreateEventPage(driver);
		InstructorSchedulePage instructorschedule = new InstructorSchedulePage(driver);
		InstructorEventRosterPage instructoreventroster = new InstructorEventRosterPage(driver);
		InstructorLocationPage instructorlocations = new InstructorLocationPage(driver);
		AdminSearchEventPage searchevent = new AdminSearchEventPage(driver);
		RegisterEdEventView eventview = new RegisterEdEventView(driver);
		RegisterEdRegisterStudentPage registerstudent = new RegisterEdRegisterStudentPage(driver);
		EMStudentRegistrationPage registration = new EMStudentRegistrationPage(driver);
		AdminSearchEventPage adminsearchevent = new AdminSearchEventPage(driver);
		StudentResultPage studentedit = new StudentResultPage(driver);
		EMAddStudentPage addstud = new EMAddStudentPage(driver);
		
		System.out.println("Step 1 : Login to EM as  Admin at : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Login to EM as  Admin at : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			if(SeleniumFunc.IsElementPresent("linkText", "Log Out"))
			{
				SeleniumFunc.ClickOnElement("linkText", "Log Out");
			}
			
			login.EnterUsername(Constants.EM_Admin_Username);
			login.EnterPassword(Constants.EM_Admin_Password);
			login.ClickOnLogInButton();
			Thread.sleep(5000);
			SeleniumFunc.ClickOnElement("linkText", "Maryland Department of Natural Resources");
			
			agencyhome.ClickToSelectProgram();
			
			agencyhome.ClickOnCreateEventLink();
			
			instructorcreateevent.SelectProgram2();
			Thread.sleep(2000);
			
			instructorcreateevent.Location.clear();
			
			instructorcreateevent.Location.sendKeys("NRP Training Academy");
			Thread.sleep(5000);
			instructorcreateevent.Location_FirstChoice.click();
			Thread.sleep(2000);
			
			instructorcreateevent.SelectStartDate("6", "30", "PM");
			instructorcreateevent.SelectEndDate("7", "30", "PM");
				
			instructorcreateevent.AddEventDate.click();
			Thread.sleep(2000);
				
			instructorcreateevent.CreateEvent.click();
			Thread.sleep(5000);
			String URL = driver.getCurrentUrl();
			System.out.println("Url :"+URL);
				
		System.out.println("Step 4 : Verifying whether Event is added successfully or not");
		Reporter.log("Step 4 : Verifying whether Event is added successfully or not"); 
		
			String expectedtext = "06:30 PM to 07:30 PM";
			String actualtext = instructorschedule.FirstEventTime.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
				System.out.println("Success !! Event is added with correct Time");
				Reporter.log("Success !! Event is added with correct Time");
			}
			else
			{
				System.out.println("Failure !! Event is added with incorrect Time" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Time" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			//Verifying Event Name
			expectedtext = "Hunter Education Classroom Course";
			actualtext = instructorschedule.FirstEventName.getText().trim();
		
			if(actualtext.contains(expectedtext))
			{
			
				System.out.println("Success !! Event is added with correct Name");
				Reporter.log("Success !! Event is added with correct Name");
			
			}
			else
			{
				
				System.out.println("Failure !! Event is added with incorrect Name" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual: " + "\n" +  actualtext);
				Reporter.log("Failure !! Event is added with incorrect Name" + "\n" + "Expected : "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual: " + "\n" +  actualtext);
				
				AssertFailedCount++;
			
			}

			String EventID = instructoreventroster.EventId.getText().trim();
			SeleniumFunc.ClickOnElement("css", ".btn.mtt");
			instructoreventroster.EventRoster.click();
			
			instructoreventroster.SelectAction("Add Student");
			Thread.sleep(5000);

			String username="emstudent"+ JH.GenerateRandomNumber();
			String emailaddress = username + "@mailinator.com";
			addstud.FillRegisterStudent("Clarion", "User", emailaddress, "Male", "United States of America", "Colorado", "African American", "January", "1", "1990", "street 3", "Norflok", "236-787-7777", "12345");
			//addstud.SelectEyeColor();
			//addstud.SelectHairColor();
			//addstud.EnterSSNNO("125896336");
			addstud.ClickOnRegisterButton();
			Thread.sleep(5000);	
			
			
			instructoreventroster.EnterResults.click();
			studentedit.ClickOnEditStudResultButton();
			
		System.out.println("Step 5 : Verifying for the Final Grade, No Show as an option present.");
		Reporter.log("Step 5 : Verifying for the Final Grade, No Show as an option present."); 
			
			if(SeleniumFunc.GetAttributeValue("css", "input[id$='No Show'][class='results-final-grade']", "value").contains("No Show"))
			{
				System.out.println("Success !! No Show Option is available.");
				Reporter.log("Success !! No Show Option is available.");
			}
			else
			{
				System.out.println("Failure !! No show option not found.");
				Reporter.log("Failure !! No show option not found.");
				AssertFailedCount++;
			}
			
		System.out.println("Step 5 : Verifying for the Final Grade, No Show as an option present.");
		Reporter.log("Step 5 : Verifying for the Final Grade, No Show as an option present.");
		
			SeleniumFunc.EnterValueInTextbox("css", "input[id^='results'][class='results-written-exam']", "0");
			SeleniumFunc.ClickOnElement("css", "h4");
		
		System.out.println("Step 6 : The Final Grade should say Fail.");
		Reporter.log("Step 6 : The Final Grade should say Fail.");
		
			if(studentedit.FinalGFail.isEnabled())
			{
				System.out.println("Success !! Final Grade showing Fail");
				Reporter.log("Success !! Final Grade Showing Fail.");
			}
			else
			{
				System.out.println("Failure !! Final Grade not showing fail");
				Reporter.log("Failure !! Final Grade not showing fail");
				AssertFailedCount++;
			}
			
			studentedit.FieldTestNA.click();
			
		System.out.println("Step 6 : (A) Verify The Final Grade should have changed to Attend.");
		Reporter.log("Step 6 : (A)Verify The Final Grade should have changed to Attend.");
		
			if(studentedit.FinalGAttend.isEnabled())
			{
				System.out.println("Success !! Final Grade changed to Attend.");
				Reporter.log("Success !! Final Grade changed to Attend.");
			}
			else
			{
				System.out.println("Failure !! Final Grade not changed to Attend.");
				Reporter.log("Failure !! Final Grade not changed to Attend.");
				AssertFailedCount++;
			}
			
		System.out.println("Step 6 : (B) Verify The Certificate Number should have changed to NA.");
		Reporter.log("Step 6 : (B) Verify The Certificate Number should have changed to NA.");
		
			if(SeleniumFunc.GetAttributeValue("css", "input[id^='results'][class='results-certificate-number']", "value").contains("NA"))
			{
				System.out.println("Success !! Certificate Number changed to NA.");
				Reporter.log("Success !! Certificate Number changed to NA.");
			}
			else
			{
				System.out.println("Failure !!  Certificate Number not changed to NA.");
				Reporter.log("Failure !! Certificate Number not changed to NA.");
				AssertFailedCount++;
			}
			
		System.out.println("Step 7 : For Live Fire select NA. & Verify Nothing else should have changed.");
		Reporter.log("Step 7 : For Live Fire select NA. & Verify Nothing else should have changed.");
		
				studentedit.LiveFireNA.click();
			
				if(!(studentedit.FieldTestPass.isSelected())
				  &&!(studentedit.FieldTestFail.isSelected())
				  &&!(studentedit.FieldTestIncomplete.isSelected())
				  &&studentedit.FieldTestNA.isSelected()
				  &&!(studentedit.LiveFirePass.isSelected())
				   &&!(studentedit.LiveFireFail.isSelected())
				    &&!(studentedit.LiveFireIncomplete.isSelected())
				    &&studentedit.LiveFireNA.isSelected()
				    &&SeleniumFunc.GetAttributeValue("css", "input[id^='results'][class='results-written-exam']", "value").contains("0")				   
				    &&!(studentedit.FinalGPass.isSelected())
				    &&!(studentedit.FinalGFail.isSelected())
				    &&!(studentedit.FinalGIncomplete.isSelected())
				    &&studentedit.FinalGAttend.isSelected()
				    &&!(studentedit.FinalGradeNoShow.isSelected())
				    &&SeleniumFunc.GetAttributeValue("css", "input[id^='results'][class='results-certificate-number']", "value").contains("NA")
				    &&SeleniumFunc.GetAttributeValue("css", "input[id^='results'][class='results-notes']", "value").contains("")
				    
						)
				{
					System.out.println("Success !! Nothing else changed.");
					Reporter.log("Success !! Nothing else should have changed.");
				}
				else
				{
					System.out.println("Failure !!  Value changed changed.");
					Reporter.log("Failure !! Value changed changed.");
					AssertFailedCount++;
				}


			System.out.println("Step 7 : Change Final Grade to No Show.");
			Reporter.log("Step 7 : Change Final Grade to No Show.");
			
					studentedit.FinalGradeNoShow.click();
			
				if(!(studentedit.FieldTestPass.isSelected())
					  &&!(studentedit.FieldTestFail.isSelected())
					  &&!(studentedit.FieldTestIncomplete.isSelected())
					  &&studentedit.FieldTestNA.isSelected()
					  &&!(studentedit.LiveFirePass.isSelected())
					   &&!(studentedit.LiveFireFail.isSelected())
					    &&!(studentedit.LiveFireIncomplete.isSelected())
					    &&studentedit.LiveFireNA.isSelected()
					    &&SeleniumFunc.GetAttributeValue("css", "input[id^='results'][class='results-written-exam']", "value").contains("0")				   
					    &&!(studentedit.FinalGPass.isSelected())
					    &&!(studentedit.FinalGFail.isSelected())
					    &&!(studentedit.FinalGIncomplete.isSelected())
					    &&!(studentedit.FinalGAttend.isSelected())
					    &&studentedit.FinalGradeNoShow.isSelected()
					    &&SeleniumFunc.GetAttributeValue("css", "input[id^='results'][class='results-certificate-number']", "value").contains("NA")
					    &&SeleniumFunc.GetAttributeValue("css", "input[id^='results'][class='results-notes']", "value").contains("")
					    
							)
					{
						System.out.println("Success !! After changing to NO SHOW nothing else changed.");
						Reporter.log("Success !! After changing to NO SHOW nothing else changed.");
					}
					else
					{
						System.out.println("Failure !! After changing to NO SHOW Value changed changed.");
						Reporter.log("Failure !! After changing to NO SHOW Value changed changed.");
						AssertFailedCount++;
					}
				
					// Delete Event 
					SeleniumFunc.ToGoToUrl(URL);
					Thread.sleep(5000);
					SeleniumFunc.ClickOnElement("linkText", "Cancel Event");
					Thread.sleep(5000);
					SeleniumFunc.ClickOnElement("linkText", "Unschedule and Remove Event");
					Thread.sleep(2000);
					SeleniumFunc.ClickOnElement("css", "body > div.stage > div.content > div.clear > a:nth-child(1) > span");		
					Thread.sleep(2000);
					System.out.println("Event: "+EventID+" Deleted Successfully!!");
					
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
	 * Verify Program and Event Info in Reports
	 * Test Case ID : #23213
	 */ 
	@Test
	private void Program_Event_Info() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Verify Program and Event Info in Reports"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Verify Program and Event Info in Reports"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		ReportPage report = new ReportPage(driver);		
		
		System.out.println("Step 1 : Log into Event Manager as an agency admin : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Log into Event Manager as an agency admin : " + Constants.ApplicationURL_EM +"/login/login"); 
			
		
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");	
					
					if(SeleniumFunc.IsElementPresent("linkText", "Log Out"))
					{
						SeleniumFunc.ClickOnElement("linkText", "Log Out");
					}
					
					login.EnterUsername(Constants.EM_Admin_Username);
					login.EnterPassword(Constants.EM_Admin_Password);
					login.ClickOnLogInButton();
					Thread.sleep(5000);

		
		System.out.println("Step 2 : Navigate to agecny program and click on Reports");
		Reporter.log("Step 2 : Navigate to agecny program and click on Reports"); 
				
						
					SeleniumFunc.ClickOnElement("linkText", "Idaho Department of Fish and Game");
					Thread.sleep(5000);
					SeleniumFunc.ClickOnElement("linkText", "Reports");
		
		System.out.println("Step 3 : Click on Registered Students List for Specified Events");
		Reporter.log("Step 3 : Click on Registered Students List for Specified Events"); 
			
					SeleniumFunc.ClickOnElement("linkText", "Registered Students List for Specified Events");
					Thread.sleep(5000);

					report.EnterStartDate("01/01/2015");
					report.ClickOnApply();
					Thread.sleep(6000);
				
		System.out.println("Step 4 : Verfiy Columns: Program Name, Event ID, Event Name should be present.");
		Reporter.log("Step 4 : Verfiy Columns: Program Name, Event ID, Event Name should be present.");
			
					String Actual = report.GetAllColumnInstructorAudit();
					//System.out.println(Actual);
				
				String Expected = "Program NameEvent IDEvent NameLast NameFirst NameMiddle InitialSuffix";
				if(Actual.equalsIgnoreCase(Expected))
				{
					System.out.println("Success !! Columns: Program Name, Event ID, Event Name displayed.");
					Reporter.log("Success !! Columns: Program Name, Event ID, Event Name displayed.");
				}
				else
				{
					System.out.println("Failure !! Columns: Program Name, Event ID, Event Name are missing. Actual : "+Actual + " Expected : "+Expected);
					Reporter.log("Failure !! Columns: Program Name, Event ID, Event Name are missing. Actual : "+Actual + " Expected : "+Expected);
					AssertFailedCount++;
				}
				
				
		System.out.println("Step 5 : Click on Agency Locations List");
		Reporter.log("Step 5 : Click on Agency Locations List"); 
					
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/report");
					SeleniumFunc.ClickOnElement("linkText", "Agency Locations - Agency & Program Locations");
					Thread.sleep(5000);
						
		System.out.println("Step 6 : Verfiy Columns: Program Name should be present.");
		Reporter.log("Step 6 : Verfiy Columns: Program Name should be present.");
					
					String Actual1 = report.GetAllColumnInstructorAudit();
					//System.out.println(Actual);
						
					String Expected1 = "Program NameEvent IDEvent NameLast NameFirst NameMiddle InitialSuffix";
					
			 if(Actual.equalsIgnoreCase(Expected))
			 {
					System.out.println("Success !! Columns: Program Name is Displayed.");
					Reporter.log("Success !! Columns: Program Name is Displayed.");
			 }
			 else
			 {
					System.out.println("Failure !! Columns: Program Name is missing. Actual : "+Actual1 + " Expected : "+Expected1);
					Reporter.log("Failure !! Columns: Program Name is missing. Actual : "+Actual1 + " Expected : "+Expected1);
					AssertFailedCount++;
			 }

		System.out.println("Step 7 : Click on Events Where Digital Signature Was Disabled");
		Reporter.log("Step 7 : Click on Events Where Digital Signature Was Disabled"); 
					
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/report");
					SeleniumFunc.ClickOnElement("linkText", "Events Where Digital Signature Was Disabled");
					Thread.sleep(5000);

					report.EnterStartDate("01/01/2015");
					report.ClickOnApply();
					Thread.sleep(6000);
						
		System.out.println("Step 8 : Verfiy Columns: Program Name, Event ID, Event Name should be present.");
		Reporter.log("Step 8 : Verfiy Columns: Program Name, Event ID, Event Name should be present.");
					
					String Actual2 = report.GetAllColumnInstructorAudit();
					//System.out.println(Actual);
						
					String Expected2 = "Program NameEvent IDEvent NameLast NameFirst NameMiddle InitialSuffix";
				if(Actual.equalsIgnoreCase(Expected))
				{
					System.out.println("Success !! Columns: Program Name, Event ID, Event Name displayed.");
					Reporter.log("Success !! Columns: Program Name, Event ID, Event Name displayed.");
				}
				else
				{
					System.out.println("Failure !! Columns: Program Name, Event ID, Event Name are missing. Actual : "+Actual2 + " Expected : "+Expected2);
					Reporter.log("Failure !! Columns: Program Name, Event ID, Event Name are missing. Actual : "+Actual2 + " Expected : "+Expected2);
					AssertFailedCount++;
				}
				
		System.out.println("Step 9 : Click on Instructor Results Audit");
		Reporter.log("Step 9 : Click on Instructor Results Audit"); 
							
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/report");
					SeleniumFunc.ClickOnElement("linkText", "Instructor Results Audit");
					Thread.sleep(5000);

					report.EnterStartDate("01/01/2015");
					report.ClickOnApply();
					Thread.sleep(6000);
								
		System.out.println("Step 10 : Verfiy Columns: Program Name, Event ID, Event Name should be present.");
		Reporter.log("Step 10 : Verfiy Columns: Program Name, Event ID, Event Name should be present.");
					
				if(Actual.equalsIgnoreCase(Expected))
				{
					System.out.println("Success !! Columns: Program Name, Event ID, Event Name displayed.");
					Reporter.log("Success !! Columns: Program Name, Event ID, Event Name displayed.");
				}
				else
				{
					System.out.println("Failure !! Columns: Program Name, Event ID, Event Name are missing. Actual : "+Actual2 + " Expected : "+Expected2);
					Reporter.log("Failure !! Columns: Program Name, Event ID, Event Name are missing. Actual : "+Actual2 + " Expected : "+Expected2);
					AssertFailedCount++;
				}
				
		System.out.println("Step 11 : Click on People Who Signed Up For A Wait List, But Did Not Register");
		Reporter.log("Step 11 : Click on People Who Signed Up For A Wait List, But Did Not Register"); 
									
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/report");
					SeleniumFunc.ClickOnElement("linkText", "People Who Signed Up For A Wait List, But Did Not Register");
					Thread.sleep(5000);

					report.EnterStartDate("01/01/2015");
					report.ClickOnApply();
					Thread.sleep(6000);
										
		System.out.println("Step 12 : Verfiy Columns: Program Name, Event ID, Event Name should be present.");
		Reporter.log("Step 12 : Verfiy Columns: Program Name, Event ID, Event Name should be present.");
									
							
				if(Actual.equalsIgnoreCase(Expected))
				{
					System.out.println("Success !! Columns: Program Name, Event ID, Event Name displayed.");
					Reporter.log("Success !! Columns: Program Name, Event ID, Event Name displayed.");
				}
				else
				{
					System.out.println("Failure !! Columns: Program Name, Event ID, Event Name are missing. Actual : "+Actual2 + " Expected : "+Expected2);
					Reporter.log("Failure !! Columns: Program Name, Event ID, Event Name are missing. Actual : "+Actual2 + " Expected : "+Expected2);
					AssertFailedCount++;
				}
						
				
		System.out.println("Step 13 : Click on Student Results Summary for Specified Events");
		Reporter.log("Step 13 : Click on Student Results Summary for Specified Events"); 
									
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/report");
					SeleniumFunc.ClickOnElement("linkText", "Student Results Summary for Specified Events");
					Thread.sleep(5000);

					report.EnterStartDate("01/01/2015");
					report.ClickOnApply();
					Thread.sleep(6000);
										
		System.out.println("Step 14 : Verfiy Columns: Program Name, Event ID, Event Name should be present.");
		Reporter.log("Step 14 : Verfiy Columns: Program Name, Event ID, Event Name should be present.");
									
							
				if(Actual.equalsIgnoreCase(Expected))
				{
					System.out.println("Success !! Columns: Program Name, Event ID, Event Name displayed.");
					Reporter.log("Success !! Columns: Program Name, Event ID, Event Name displayed.");
				}
				else
				{
					System.out.println("Failure !! Columns: Program Name, Event ID, Event Name are missing. Actual : "+Actual2 + " Expected : "+Expected2);
					Reporter.log("Failure !! Columns: Program Name, Event ID, Event Name are missing. Actual : "+Actual2 + " Expected : "+Expected2);
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