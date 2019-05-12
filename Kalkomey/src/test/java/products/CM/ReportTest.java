package products.CM;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.LoginPage;
import pageFactory.CM.ReportPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;



public class ReportTest 
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
	 * Verify user can navigate to reports section
     */ 	
	@Test
	private void RedirectReportsPage() throws Exception
	{
		System.out.println("====" + "\n" + "Test 1 : Verify user can navigate to reports section"  + "\n" +"====");
		Reporter.log("====" + "\n" + "Test 1 : Verify user can navigate to reports section"  + "\n" +  "====");	
		
				int AssertFailedCount=0 ;
				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				LoginPage login = new LoginPage(driver);
				ReportPage report = new ReportPage(driver);

		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
	
		System.out.println("Step 2 : Login with valid credentials");
		Reporter.log("Step 2 : Login with valid credentials"); 
			
				login.EnterUsername("sanjeetk@clariontechnologies.co.in");
				login.EnterPassword("clarion@123");
				login.ClickOnLogInButton();
		
		System.out.println("Step 3: Click on Report tab and verify control is redirected to Report Page");
		Reporter.log("Step 3: Click on Report tab and verify control is redirected to Report Page"); 
				
				Thread.sleep(1000);
				report.Report.click();
				Thread.sleep(1000);
				String ExpectedText = "Toggle Reports Menu";
				String ActualText = report.Title.getText().trim();
			
				if(ActualText.equals(ExpectedText))
				{
					System.out.println("Success !! Control is redirected to Report Page. i.e. " + ExpectedText);
					Reporter.log("Success !! Control is redirected to Report Page. i.e. " + ExpectedText); 
				}
				else
				{
					System.out.println("Failure !! Control is NOT redirected to Report Page. i.e." + "\n" + "Expected : "  
									+ "\n" + ExpectedText + "\n" + "Actual : " + "\n" +  ActualText);
					Reporter.log("Failure !! Control is NOT redirected to Report Page. i.e." + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" +  "Actual : " + "\n" +  ActualText);
				
					AssertFailedCount++;
				}

		System.out.println("Step 4:  Click On  'toggle reports menu' link  for  Hide filter  ");
		Reporter.log("Step 4: Click On  'toggle reports menu' link  for  Hide filter"); 	
				
				Thread.sleep(1000);
				report.ClickOnTitle();
				Thread.sleep(2000);
				report.ClickOnTitle();	
				System.out.println("Clicked on 'toggle reports menu' link  for  Hide filter");
				if(SeleniumFunc.IsElementPresent(report.HideTitle))
				{
					System.out.println("Success !! Toggle Reports Menu  link Filter  is   Hide");
					Reporter.log("Success !! Toggle Reports Menu  link Filter  is   Hide"); 
				}
				else
				{
					System.out.println("Failure !! Toggle Reports Menu  link Filter  is not  Hide");
					Reporter.log("Failure !! Toggle Reports Menu  link Filter  is not  Hide");			
					AssertFailedCount++;
				}
			
		System.out.println("Step 5:  Click On  'toggle reports menu' link  for  UnHide filter");
		Reporter.log("Step 5: Click On  'toggle reports menu' link  for  UnHide filter"); 
			
		        Thread.sleep(1000);
		        //SeleniumFunc.ClickOnElement("css", "#showHide span");
				report.ClickOnTitle();		
				Thread.sleep(1000);

				if(SeleniumFunc.IsElementPresent(report.UnHideTitle))
				{
					System.out.println("Success !! Toggle Reports Menu  link Filter  is   UnHide");
					Reporter.log("Success !! Toggle Reports Menu  link Filter  is   UnHide"); 
				}
				else
				{
					System.out.println("Failure !! Toggle Reports Menu  link Filter  is   not UnHide");
					Reporter.log("Failure !! Toggle Reports Menu  link Filter  is  not  UnHide");			
					AssertFailedCount++;
				}
				
	   System.out.println("Step 6 : Observe Details on Reports Menu  ");
	   Reporter.log("Step 6 : Observe Details on Reports Menu"); 
	   
		   		
	   			if(SeleniumFunc.IsElementPresent(report. State))
	   			{
	   				Thread.sleep(1000);
	   				System.out.println("Success !!  State  Text  Box is Present ");
	   				Reporter.log("Success !! State  Text  Box is Present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! State  Text  Box is  not Present");
	   				Reporter.log("Failure !!  State  Text  Box is  not Presente");			
	   				AssertFailedCount++;
	   			}
			
	   			if(SeleniumFunc.IsElementPresent(report.Type))
	   			{
	   				System.out.println("Success !! Course  Text  Box is Present");
	   				Reporter.log("Success !! Course  Text  Box is Present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Course  Text  Box is not Present");
	   				Reporter.log("Failure !! Course  Text  Box is not Present");			
	   				AssertFailedCount++;
	   			}
	   			if(SeleniumFunc.IsElementPresent(report.FromDate))
	   			{
	   				System.out.println("Success !! FromDate Slsect Field is present ");
	   				Reporter.log("Success !! FromDate Slsect Field is present "); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !!FromDate Slsect Field  is notpresent ");
	   				Reporter.log("Failure !! FromDate Slsect Field  is not present ");			
	   				AssertFailedCount++;
	   			}
		   
	   			if(SeleniumFunc.IsElementPresent(report.ToDate))
	   			{
	   				Thread.sleep(1000);
	   				System.out.println("Success !! ToDate Slsect Field  is present");
	   				Reporter.log("Success !! ToDate Slsect Field  is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! ToDate Slsect Field  is not present");
	   				Reporter.log("Failure !! ToDate Slsect Field  is not present");			
	   				AssertFailedCount++;
	   			}
				
	   			if(SeleniumFunc.IsElementPresent(report.Generate))
	   			{
	   				System.out.println("Success !! Generate Button   is present");
	   				Reporter.log("Success !! Generate Button   is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Generate Button   is not  present");
	   				Reporter.log("Failure !! Generate Button   is not  present");			
	   				AssertFailedCount++;
	   			}	
	   			Thread.sleep(1000);
	   			report.SelectState("Pennsylvania");
	   			report.SelectType("Hunting");
	   			report.EnterFromDate("06/03/2014");
	   			report.EnterToDate("09/28/2015");
	   			report.ClickOnGenerateButton();
	   			Thread.sleep(1000);
		
	   	System.out.println("Step 7: Verify report is generated");
		Reporter.log("Step 7: Verify report is generated"); 
			
				//Dashboard tab 
				if(SeleniumFunc.IsElementPresent(report.DashboardTab))
				{
					System.out.println("Success !! Dashboard  Tab is Present ");
					Reporter.log("Success !! Dashboard  Tab is Present"); 
				}
				else
				{
					System.out.println("Failure !! Dashboard  Tab is not Present");
					Reporter.log("Failure !! Dashboard  Tab is not  Present");
	
					AssertFailedCount++;
				}
				
				//Details Tab
				if(SeleniumFunc.IsElementPresent(report.DetailsTab))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Details  Tab is Present");
					Reporter.log("Success !! Details  Tab is Present"); 
				}
				else
				{
					System.out.println("Failure !!  Details  Tab is not Present");
					Reporter.log("Failure !!  Details  Tab is not  Present");
	
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
		 * Verify user can Generate report
	     */ 
			
		@Test
		private void GenerateReport() throws Exception
		{
			System.out.println("====" + "\n" +	"Test 2 : Verify user can Generate report"  + "\n" +
				 			"====");
			Reporter.log("====" + "\n" + "Test 2 : Verify user can Generate report"  + "\n" +
						 	  "====");	
				
					int AssertFailedCount=0 ;
					SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
					LoginPage login = new LoginPage(driver);
					ReportPage report = new ReportPage(driver);

			System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
			Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
					
					SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
			
			System.out.println("Step 2 : Login with valid credentials");
			Reporter.log("Step 2 : Login with valid credentials"); 
					
					login.EnterUsername("sanjeetk@clariontechnologies.co.in");
					login.EnterPassword("clarion@123");
					login.ClickOnLogInButton();
				
			System.out.println("Step 3: Click on Report tab and Select different parameter to generate report");
			Reporter.log("Step 3: Click on Report tab and Select different parameter to generate report"); 
							
					Thread.sleep(1000);
					report.Report.click();
					Thread.sleep(1000);
					report.SelectState("Pennsylvania");
					report.SelectType("Hunting");				
					report.EnterFromDate("10/06/2011");
					report.EnterToDate("02/19/2015");
					report.ClickOnGenerateButton();
					Thread.sleep(1000);
					
			System.out.println("Step 4: Verify presence of dashboard and details tab");
			Reporter.log("Step 4: Verify presence of dashboard and details tab"); 
		
					//Graphs 
					if(SeleniumFunc.IsElementPresent(report.Graph))
					{
						Thread.sleep(1000);
						System.out.println("Success !! Graphs are generated");
						Reporter.log("Success !! Graphs are generated"); 
					}
					else
					{
						System.out.println("Failure !! Graphs are NOT generated");
						Reporter.log("Failure !! Graphs are NOT generated");
			
						AssertFailedCount++;
					}
					Thread.sleep(1000);
					//Verify columns on Details page
					report.ClickOnDetailsTab();
					Thread.sleep(1000);
					//Certified On column
					if(SeleniumFunc.IsElementPresent(report.CertifiedOn))
					{
						System.out.println("Success !! Certified On column is displayed");
						Reporter.log("Success !! Certified On column is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Certified On column is NOT displayed");
						Reporter.log("Failure !! Certified On column is NOT displayed");
					
						AssertFailedCount++;
					}
					
					
					//Full Name column
					if(SeleniumFunc.IsElementPresent(report.FullName))
					{
						System.out.println("Success !! Full Name column is displayed");
						Reporter.log("Success !! Full Name column is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Full Name column is NOT displayed");
						Reporter.log("Failure !! Full Name column is NOT displayed");
					
						AssertFailedCount++;
					}
					
					
					//DOB column
					if(SeleniumFunc.IsElementPresent(report.DOB))
					{
						Thread.sleep(1000);
						System.out.println("Success !! DOB column is displayed");
						Reporter.log("Success !! DOB column is displayed"); 
					}
					else
					{
						System.out.println("Failure !! DOB column is NOT displayed");
						Reporter.log("Failure !! DOB column is NOT displayed");
					
						AssertFailedCount++;
					}
					
					
					//Email column
					if(SeleniumFunc.IsElementPresent(report.Email))
					{
						System.out.println("Success !! Email column is displayed");
						Reporter.log("Success !! Email column is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Email column is NOT displayed");
						Reporter.log("Failure !! Email column is NOT displayed");
					
						AssertFailedCount++;
					}
					
					
					//Phone column
					if(SeleniumFunc.IsElementPresent(report.Phone))
					{
						System.out.println("Success !! Phone column is displayed");
						Reporter.log("Success !! Phone column is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Phone column is NOT displayed");
						Reporter.log("Failure !! Phone column is NOT displayed");
					
						AssertFailedCount++;
					}
					
					
					//Gender column
					if(SeleniumFunc.IsElementPresent(report.Gender))
					{
						System.out.println("Success !! Gender column is displayed");
						Reporter.log("Success !! Gender column is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Gender column is NOT displayed");
						Reporter.log("Failure !! Gender column is NOT displayed");
					
						AssertFailedCount++;
					}
					
					
					//Internet column
					if(SeleniumFunc.IsElementPresent(report.Internet))
					{
						System.out.println("Success !! Internet column is displayed");
						Reporter.log("Success !! Internet column is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Internet column is NOT displayed");
						Reporter.log("Failure !! Internet column is NOT displayed");
					
						AssertFailedCount++;
					}
					
					
					//Physical Address column
					if(SeleniumFunc.IsElementPresent(report.PhyAdd))
					{
						System.out.println("Success !! Physical Address column is displayed");
						Reporter.log("Success !! Physical Address column is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Physical Address column is NOT displayed");
						Reporter.log("Failure !! Physical Address column is NOT displayed");
					
						AssertFailedCount++;
					}
					
					//Download CSV Button
					if(SeleniumFunc.IsElementPresent(report.DownloadCSV))
					{
						Thread.sleep(1000);
						System.out.println("Success !! Download CSV Button is displayed");
						Reporter.log("Success !! Download CSV Button is displayed"); 
					}
					else
					{
						Thread.sleep(1000);
						System.out.println("Failure !! Download CSV Button is NOT displayed");
						Reporter.log("Failure !! Download CSV Button is NOT displayed");
					
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
			 * Verify that graphs are generated under dashboard
		     */ 			
			@Test
			private void VerifyGraphGenerated() throws Exception
			{
				System.out.println("====" + "\n" +		"Test 3 : Verify that graphs are generated under dashboard"  + "\n" +	"====");
				Reporter.log("====" + "\n" +  "Test 3 : Verify that graphs are generated under dashboard"  + "\n" +  "====");	
				
					int AssertFailedCount=0 ;
					SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
					LoginPage login = new LoginPage(driver);
					ReportPage report = new ReportPage(driver);

				System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
				Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
					
					SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
			
				System.out.println("Step 2 : Login with valid credentials");
				Reporter.log("Step 2 : Login with valid credentials"); 
					
					login.EnterUsername("sanjeetk@clariontechnologies.co.in");
					login.EnterPassword("clarion@123");
					login.ClickOnLogInButton();
				
				System.out.println("Step 3: Click on Report tab and Select different parameter to generate report");
				Reporter.log("Step 3: Click on Report tab and Select different parameter to generate report"); 
					
					Thread.sleep(1000);
					report.Report.click();
					Thread.sleep(1000);
					report.SelectState("Pennsylvania");
					report.SelectType("Hunting");				
					report.EnterFromDate("10/06/2011");
					report.EnterToDate("02/19/2015");
					report.ClickOnGenerateButton();
					Thread.sleep(1000);
				
				System.out.println("Step 4: Verify Graphs are generated");
				Reporter.log("Step 4: Verify Graphs are generated"); 
						
					//Graphs 
					if(SeleniumFunc.IsElementPresent(report.Graph))
					{
						System.out.println("Success !! Graphs are generated");
						Reporter.log("Success !! Graphs are generated"); 
					}
					else
					{
						System.out.println("Failure !! Graphs are NOT generated");
						Reporter.log("Failure !! Graphs are NOT generated");
					
						AssertFailedCount++;
					}


					//Course Completion Graph
					if(SeleniumFunc.IsElementPresent(report.CourseCompletionGraph))
					{
						System.out.println("Success !! Course Completion Graph is displayed");
						Reporter.log("Success !! Course Completion Graph is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Course Completion Graph is NOT displayed");
						Reporter.log("Failure !! Course Completion Graph is NOT displayed");
					
						AssertFailedCount++;
					}
					
					
					//Age Distribution Graph
					if(SeleniumFunc.IsElementPresent(report.AgeDistributionGraph))
					{
						Thread.sleep(1000);
						System.out.println("Success !! Age Distribution Graph is displayed");
						Reporter.log("Success !! Age Distribution Graph is displayed"); 
					}
					else
					{
						Thread.sleep(1000);
						System.out.println("Failure !! Age Distribution Graph is NOT displayed");
						Reporter.log("Failure !! Age Distribution Graph is NOT displayed");
					
						AssertFailedCount++;
					}
					
					
					//Gender Count
					if(SeleniumFunc.IsElementPresent(report.GenderCount))
					{
						System.out.println("Success !! Gender Count Graph is displayed");
						Reporter.log("Success !! Gender Count Graph is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Gender Count Graph is NOT displayed");
						Reporter.log("Failure !! Gender Count Graph is NOT displayed");
					
						AssertFailedCount++;
					}
					

					//Age Threshold Graph
					if(SeleniumFunc.IsElementPresent(report.AgeThreshold))
					{
						System.out.println("Success !! Age Threshold Graph is displayed");
						Reporter.log("Success !! Age Threshold Graph is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Age Threshold Graph is NOT displayed");
						Reporter.log("Failure !! Age Threshold Graph is NOT displayed");
					
						AssertFailedCount++;
					}


					//Class Type By Status
					if(SeleniumFunc.IsElementPresent(report.ClassTypeByStatus))
					{
						Thread.sleep(1000);
						System.out.println("Success !! Class Type By Status is displayed");
						Reporter.log("Success !! Class Type By Status is displayed"); 
					}
					else
					{
						Thread.sleep(1000);
						System.out.println("Failure !! Class Type By Status is NOT displayed");
						Reporter.log("Failure !! Class Type By Status is NOT displayed");
					
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
			 * Verify Totals appear near the top of the charts, and also on the bottom of the details.
		     */ 			
			@Test
			private void VerifyTotal() throws Exception
			{
				System.out.println("====" + "\n" +	"Test 4 : Verify Totals appear near the top of the charts, and also on the bottom of the details."  + "\n" +
				 			                                           "====");
				Reporter.log("====" + "\n" +  "Test 4 : Verify Totals appear near the top of the charts, and also on the bottom of the details."  + "\n" +
						                                           	  "====");	
				
					int AssertFailedCount=0 ;
					SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
					LoginPage login = new LoginPage(driver);
					ReportPage report = new ReportPage(driver);

				System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
				Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
					
					SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
			
				System.out.println("Step 2 : Login with valid credentials");
				Reporter.log("Step 2 : Login with valid credentials"); 
					
					login.EnterUsername("sanjeetk@clariontechnologies.co.in");
					login.EnterPassword("clarion@123");
					login.ClickOnLogInButton();
				
				System.out.println("Step 3: Click on Report tab and Select different parameter to generate report");
				Reporter.log("Step 3: Click on Report tab and Select different parameter to generate report"); 					
					
					Thread.sleep(1000);
					report.Report.click();
					Thread.sleep(2000);
					report.SelectState("Pennsylvania");
					report.SelectType("Hunting");
					report.EnterFromDate("10/06/2011");
					report.EnterToDate("02/19/2015");
					report.ClickOnGenerateButton();	
					Thread.sleep(3000);
					
				System.out.println("Step 4: Verify total appears at top and bottom of the charts");
				Reporter.log("Step 4: Verify total appears at top and bottom of the charts"); 
							
					Thread.sleep(3000);				
					
					//Verify total appears at certification tab
					if(SeleniumFunc.IsElementPresent(report.Totalcount))
					{
						System.out.println("Success !!  Total is displayed at certification tab");
						Reporter.log("Success !!  Total is displayed at certification tab"); 
					}
					else
					{
						System.out.println("Failure !! Total is NOT displayed at certification tab");
						Reporter.log("Failure !! Total is NOT displayed at certification tab");
					
						AssertFailedCount++;
					}
					Thread.sleep(1000);
					report.ClickOnDetailsTab();
					Thread.sleep(1000);
					//Verify total appears at details tab
					if(SeleniumFunc.IsElementPresent(report.DetailsCount))
					{
						System.out.println("Success !!  Total is displayed at details tab");
						Reporter.log("Success !!  Total is displayed at details tab"); 
					}
					else
					{
						System.out.println("Failure !! Total is NOT displayed at details tab");
						Reporter.log("Failure !! Total is NOT displayed at details tab");
					
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
			 * Verify class type report is generated
		     */ 			
			@Test
			private void ClassTypeReport() throws Exception
			{
				System.out.println("====" + "\n" +	"Test 5: Verify class type report is generated"  + "\n" +"====");
				Reporter.log("====" + "\n" +  "Test 5 : Verify class type report is generated"  + "\n" +  "====");	
				
					int AssertFailedCount=0 ;
					SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
					LoginPage login = new LoginPage(driver);
					ReportPage report = new ReportPage(driver);

				System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
				Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
					
					SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
			
				System.out.println("Step 2 : Login with valid credentials");
				Reporter.log("Step 2 : Login with valid credentials"); 
					
					login.EnterUsername("sanjeetk@clariontechnologies.co.in");
					login.EnterPassword("clarion@123");
					login.ClickOnLogInButton();
				
				System.out.println("Step 3: Click on Report tab and Select different parameter to generate report");
				Reporter.log("Step 3: Click on Report tab and Select different parameter to generate report"); 
					
					Thread.sleep(1000);
					report.Report.click();
					Thread.sleep(1000);
					report.SelectState("Pennsylvania");
					report.SelectType("Hunting");
					report.EnterFromDate("10/06/2011");
					report.EnterToDate("02/19/2015");
					report.ClickOnGenerateButton();
					Thread.sleep(1000);
				
				System.out.println("Step 4: Verify A Table At The Bottom Of The Page, Under the Title 'Class Type By Status'.");
				Reporter.log("Step 4: Verify A Table At The Bottom Of The Page, Under the Title 'Class Type By Status'.");
			         
				  	//Class Type By Status
					if(SeleniumFunc.IsElementPresent(report.ClassTypByStatusHeader))
					{
						System.out.println("Success !! Class Type By Status  text is displayed");
						Reporter.log("Success !! Class Type By Status  text is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Class Type By Status  text is not displayed");
						Reporter.log("Failure !! Class Type By Status  text is not displayed");				
						AssertFailedCount++;
					}
			
					//Class Types
					if(SeleniumFunc.IsElementPresent(report.ClassType))
					{
						Thread.sleep(1000);
						System.out.println("Success !! Class Type   text is displayed");
						Reporter.log("Success !! Class Type  text is displayed"); 
					}
					else
					{
						Thread.sleep(1000);
						System.out.println("Failure !! Class Type   text is not displayed");
						Reporter.log("Failure !! Class Type   text is not displayed");				
						AssertFailedCount++;
					}
			
					//Pass
					if(SeleniumFunc.IsElementPresent(report.Pass))
					{
						System.out.println("Success !!Pass   text is displayed");
						Reporter.log("Success !!Pass text is displayed"); 
					}
					else
					{
						System.out.println("Failure !!Pass   text is not displayed");
						Reporter.log("Failure !! Pass   text is not displayed");				
						AssertFailedCount++;
					}
					
					//Fail
					if(SeleniumFunc.IsElementPresent(report.Fail))
					{
						System.out.println("Success !! Fail   text is displayed");
						Reporter.log("Success !! Fail  text is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Fail  text is not displayed");
						Reporter.log("Failure !! Fail  text is not displayed");				
						AssertFailedCount++;
					}
					
					//No Show
					if(SeleniumFunc.IsElementPresent(report.NoShow))
					{
						System.out.println("Success !! No Show    text is displayed");
						Reporter.log("Success !! No Show  text is displayed"); 
					}
					else
					{
						System.out.println("Failure !! No Show   text is not displayed");
						Reporter.log("Failure !! No Show  text is not displayed");				
						AssertFailedCount++;
					}
					
					//Incomplete
					if(SeleniumFunc.IsElementPresent(report.Incomplete))
					{
						System.out.println("Success !! Incomplete   text is displayed");
						Reporter.log("Success !! Incomplete   text is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Incomplete    text is not displayed");
						Reporter.log("Failure !! Incomplete   text is not displayed");				
						AssertFailedCount++;
					}
					
					//Other
					if(SeleniumFunc.IsElementPresent(report.Other))
					{
						Thread.sleep(1000);
						System.out.println("Success !! Other   text is displayed");
						Reporter.log("Success !!Other   text is displayed"); 
					}
					else
					{
						Thread.sleep(1000);
						System.out.println("Failure !!Other    text is not displayed");
						Reporter.log("Failure !! Other   text is not displayed");				
						AssertFailedCount++;
					}
					
					//Total
					if(SeleniumFunc.IsElementPresent(report.Total1))
					{
						System.out.println("Success !! Total  text is displayed");
						Reporter.log("Success !! Total text is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Total text is not displayed");
						Reporter.log("Failure !!Total text is not displayed");				
						AssertFailedCount++;
					}
					
					//ClassRoom State
					if(SeleniumFunc.IsElementPresent(report.ClassRoomState))
					{
						System.out.println("Success !! Class Room State  text is displayed");
						Reporter.log("Success !! Class Room State text is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Class Room State   text is not displayed");
						Reporter.log("Failure !! Class Room State text is not displayed");				
						AssertFailedCount++;
					}
					
					//Internet
					if(SeleniumFunc.IsElementPresent(report.Internet1))
					{
						System.out.println("Success !! Internet  text is displayed");
						Reporter.log("Success !! Internet  text is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Internet text is not displayed");
						Reporter.log("Failure !! Internet text is not displayed");				
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
			 * Verify user can filter columns data.
		     */ 			
			@Test
			private void VerifyFilter() throws Exception
			{
				System.out.println("====" + "\n" +	"Test 6: Verify user can filter columns data"  + "\n" +"====");
				Reporter.log("====" + "\n" +  "Test 6 : Verify user can filter columns data"  + "\n" +  "====");	
				
					int AssertFailedCount=0 ;
					SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
					LoginPage login = new LoginPage(driver);
					ReportPage report = new ReportPage(driver);

				System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
				Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
					
					SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
			
				System.out.println("Step 2 : Login with valid credentials");
				Reporter.log("Step 2 : Login with valid credentials"); 
					
					login.EnterUsername("sanjeetk@clariontechnologies.co.in");
					login.EnterPassword("clarion@123");
					login.ClickOnLogInButton();
				
				System.out.println("Step 3: Click on Report tab and Select different parameter to generate report");
				Reporter.log("Step 3: Click on Report tab and Select different parameter to generate report"); 
				
					Thread.sleep(1000);	
					report.Report.click();
					Thread.sleep(5000);
					report.SelectState("Pennsylvania");
					report.SelectType("Hunting");
					report.EnterFromDate("03/06/2011");
					report.EnterToDate("09/25/2016");
					Thread.sleep(3000);
					report.ClickOnGenerateButton();
				
					
				System.out.println("Step 4: Verify User should be able to set filter on column using AND");
				Reporter.log("Step 4: Verify User should be able to set filter on column using AND");
				
					Thread.sleep(5000);

					//Add details to filter record
					report.ClickOnDetailsTab();
					Thread.sleep(5000);
					
					SeleniumFunc.ClickOnElement("css", "#grid th:nth-of-type(4) span");
					Thread.sleep(1000);
					report.EnterFilterValue1("CHROSE@21CCCS.ORG");
					Thread.sleep(1000);
				//	SeleniumFunc.ClickOnElement("css", "div:nth-of-type(1) span:nth-of-type(3)");
			//		Thread.sleep(2000);
				//	SeleniumFunc.ClickOnElement("xpath", "//li[text()='Contains']");
					report.EnterFilterValue2("CHROSE@21CCCS.ORG");
					Thread.sleep(1000);
					report.ClickOnFilterButton();
					Thread.sleep(4000);
					
					if(SeleniumFunc.IsElementPresent(report.FilterData))
					{
						System.out.println("Success !! Record  is displayed");
						Reporter.log("Success !! Record is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Record is not displayed");
						Reporter.log("Failure !! Record is not displayed");				
						AssertFailedCount++;
					}

					String ExpectedText = "CHROSE@21CCCS.ORG";
					String ActualText = SeleniumFunc.GetElementText("css", "#grid tr:nth-of-type(1) td:nth-of-type(4)");
					Thread.sleep(1000);
					if(ActualText.equals(ExpectedText))
					{
						System.out.println("Success !! Filter with AND operator is working properly.  i.e. " +ExpectedText);
						Reporter.log("Success !! Filter with AND operator is working properly.  i.e. " +ExpectedText); 
					}
					else
					{
						System.out.println("Failure !! Filter with AND operator is NOT working properly. Expected is : " +ExpectedText +"\n" +
												"Actual is : " +ActualText);
						Reporter.log("Failure !! Filter with AND operator is NOT working properly. Expected is : " +ExpectedText +"\n" +
								"Actual is : " +ActualText); 
						
						AssertFailedCount++;
					}
					
				System.out.println("Step 5: Verify User should be able to set filter on column using OR");
				Reporter.log("Step 5: Verify User should be able to set filter on column using OR");
					         
					SeleniumFunc.ClickOnElement("css", "#grid th:nth-of-type(4) span");
					Thread.sleep(1000);
					report.EnterFilterValue1("CHROSE@21CCCS.ORG");
					Thread.sleep(1000);
					report.EnterFilterValue2("CHROSE@21CCCS.ORG");
					Thread.sleep(1000);
					SeleniumFunc.ClickOnElement("css", "div:nth-of-type(1) span:nth-of-type(1) + input + span");
					Thread.sleep(2000);

					SeleniumFunc.ClickOnElement("xpath", "//li[text()='Or']");
					Thread.sleep(1000);
					report.ClickOnFilterButton();
					Thread.sleep(1000);
						
					if(SeleniumFunc.IsElementPresent(report.FilterData))
					{
						System.out.println("Success !! Record  is displayed");
						Reporter.log("Success !! Record is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Record is not displayed");
						Reporter.log("Failure !! Record is not displayed");				
						AssertFailedCount++;
					}
					Thread.sleep(1000);
					ExpectedText = "CHROSE@21CCCS.ORG";
					ActualText = SeleniumFunc.GetElementText("css", "#grid tr:nth-of-type(1) td:nth-of-type(4)");
					
					if(ActualText.equals(ExpectedText))
					{
						Thread.sleep(1000);
						System.out.println("Success !! Filter with OR operator is working properly.  i.e. " +ExpectedText);
						Reporter.log("Success !! Filter with OR operator is working properly.  i.e. " +ExpectedText); 
					}
					else
					{
						System.out.println("Failure !! Filter with OR operator is NOT working properly. Expected is : " +ExpectedText +"\n" +
												"Actual is : " +ActualText);
						Reporter.log("Failure !! Filter with OR operator is NOT working properly. Expected is : " +ExpectedText +"\n" +
								"Actual is : " +ActualText); 
						
						AssertFailedCount++;
					}
					
					
					//Clear Filter
					SeleniumFunc.ClickOnElement("css", "#grid th:nth-of-type(4) span");
					Thread.sleep(1000);
					report.ClickOnClearButton();
					Thread.sleep(1000);
					SeleniumFunc.ClickOnElement("css", "#grid th:nth-of-type(4) span");
					Thread.sleep(1000);
					ActualText = report.FilterValue1.getAttribute("value");
					Thread.sleep(1000);
					if(ActualText.equals(""))
					{
						Thread.sleep(1000);
						System.out.println("Success !! Clear filter functionality is working properly.");
						Reporter.log("Success !! Clear filter functionality is working properly."); 
					}
					else
					{
						System.out.println("Failure !! Clear filter functionality is NOT working properly. Expected is  blank value, but"  +
								"Actual is : " +ActualText);
						Reporter.log("Failure !! Clear filter functionality is NOT working properly. Expected is  blank value, but"  +
								"Actual is : " +ActualText); 
							
						AssertFailedCount++;
					}
						
					if(AssertFailedCount>0)	
					{
					     //Marking this test as Failed					
						System.out.println("---- Test Failed. Please check the console or TestNg report for details");
						Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
					
						Assert.fail();
					}
		
			}
			
			/* Test 7: 
			 *Verify pagination is displayed and working
		     */ 			
			@Test
			private void VerifyPagination() throws Exception
			{
				System.out.println("====" + "\n" +	"Test 7:Verify pagination is displayed and working"  + "\n" +"====");
				Reporter.log("====" + "\n" +  "Test 7 : Verify pagination is displayed and working"  + "\n" +  "====");	
				
					int AssertFailedCount=0 ;
					SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
					LoginPage login = new LoginPage(driver);
					ReportPage report = new ReportPage(driver);

				System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
				Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
					
					SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
			
				System.out.println("Step 2 : Login with valid credentials");
				Reporter.log("Step 2 : Login with valid credentials"); 
					
					login.EnterUsername("sanjeetk@clariontechnologies.co.in");
					login.EnterPassword("clarion@123");
					login.ClickOnLogInButton();
				
				System.out.println("Step 3: Click on Report tab and Select different parameter to generate report");
				Reporter.log("Step 3: Click on Report tab and Select different parameter to generate report"); 
					
					Thread.sleep(1000);
					report.Report.click();
					Thread.sleep(1000);
					report.SelectState("Pennsylvania");
					report.SelectType("Hunting");
					report.EnterFromDate("10/06/2011");
					report.EnterToDate("02/19/2015");
					report.ClickOnGenerateButton();
					Thread.sleep(1000);
					
				System.out.println("Step 4: Verify pagination should be displayed");
				Reporter.log("Step 4: Verify pagination should be displayed");
					
					report.ClickOnDetailsTab();
					Thread.sleep(4000);
				
					if(SeleniumFunc.IsElementPresent(report.Pagination))
					{
						System.out.println("Success !! Pagination is displayed");
						Reporter.log("Success !! Pagination is displayed"); 
					}
					else
					{
						System.out.println("Failure !! Pagination is not displayed");
						Reporter.log("Failure !! Pagination is not displayed");				
						AssertFailedCount++;
					}

					report.ClickOnNextBtn();
					Thread.sleep(4000);
					
					String ExpectedText = "2";
					String ActualText = report.PageNoSelected.getText().trim();
					
					if(ActualText.equals(ExpectedText))
					{
						System.out.println("Success !! Next functionality is working properly.  i.e. " +ExpectedText);
						Reporter.log("Success !! Next functionality is working properly.  i.e. " +ExpectedText); 
					}
					else
					{
						System.out.println("Failure !! Next functionality is NOT working properly. Expected is : " +ExpectedText +"\n" +
												"Actual is : " +ActualText);
						Reporter.log("Failure !! Next functionality is NOT working properly. Expected is : " +ExpectedText +"\n" +
								"Actual is : " +ActualText); 
						
						AssertFailedCount++;
					}
					
					report.ClickOnPreviousBtn();
					Thread.sleep(4000);

					ExpectedText = "1";
					ActualText = report.PageNoSelected.getText().trim();
					
					if(ActualText.equals(ExpectedText))
					{
						System.out.println("Success !! Previous functionality is working properly.  i.e. " +ExpectedText);
						Reporter.log("Success !! Previous functionality is working properly.  i.e. " +ExpectedText); 
					}
					else
					{
						System.out.println("Failure !! Previous functionality is NOT working properly. Expected is : " +ExpectedText +"\n" +
												"Actual is : " +ActualText);
						Reporter.log("Failure !! Previous functionality is NOT working properly. Expected is : " +ExpectedText +"\n" +
								"Actual is : " +ActualText); 
						
						AssertFailedCount++;
					}
					
					//Go to specific page - 3
					
					report.ClickOnSpecificPageNumber();
					Thread.sleep(4000);

					ExpectedText = "3";
					ActualText = report.PageNoSelected.getText().trim();
					
					if(ActualText.equals(ExpectedText))
					{
						System.out.println("Success !! Control is redirected to specific page.  i.e. " +ExpectedText);
						Reporter.log("Success !! Control is redirected to specific page.  i.e. " +ExpectedText); 
					}
					else
					{
						System.out.println("Failure !! Control is NOT redirected to specific page. Expected is : " +ExpectedText +"\n" +
												"Actual is : " +ActualText);
						Reporter.log("Failure !! Control is NOT redirected to specific page. Expected is : " +ExpectedText +"\n" +
								"Actual is : " +ActualText); 
						
						AssertFailedCount++;
					}
					
					
					if(AssertFailedCount>0)	
					{
			
						//Marking this test as Failed			
						System.out.println("---- Test Failed. Please check the console or TestNg report for details");
						Reporter.log("---- Test Failed. Please check the console or TestNg report for details");			
						Assert.fail();
					}
			}
			
			
			
			/* Test 8: 
			 *Check that the details and summary counts match
		     */ 			
			@Test
			private void DetailsAndSummaryCounts() throws Exception
			{
				System.out.println("====" + "\n" +	"Test 8:Check that the details and summary counts match"  + "\n" +"====");
				Reporter.log("====" + "\n" +  "Test 8 : Check that the details and summary counts match"  + "\n" +  "====");	
				
					int AssertFailedCount=0 ;
					SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
					LoginPage login = new LoginPage(driver);
					ReportPage report = new ReportPage(driver);

				System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
				Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
					
					SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
			
				System.out.println("Step 2 : Login with valid credentials");
				Reporter.log("Step 2 : Login with valid credentials"); 
					
					login.EnterUsername("sanjeetk@clariontechnologies.co.in");
					login.EnterPassword("clarion@123");
					login.ClickOnLogInButton();
				
				System.out.println("Step 3: Click on Report tab and Select different parameter to generate report");
				Reporter.log("Step 3: Click on Report tab and Select different parameter to generate report"); 
					
					Thread.sleep(1000);
					report.Report.click();
					Thread.sleep(1000);
					report.SelectState("Pennsylvania");
					report.SelectType("Hunting");
					report.EnterFromDate("03/06/2014");
					report.EnterToDate("09/25/2015");
					Thread.sleep(1000);
					report.ClickOnGenerateButton();
					Thread.sleep(1000);
				
				System.out.println("Step 4: Verify Details and Summary Counts");
				Reporter.log("Step 4: Verify Details and Summary Counts");		
				   					
					String DashboardCount = report.Totalcount.getText().trim();
					String[] parts = DashboardCount.split(":");
					DashboardCount = parts[1].trim(); 
					System.out.println(DashboardCount);
					Thread.sleep(1000);
					report.ClickOnDetailsTab();
					
					Thread.sleep(4000);
					
					String DetailsCount = report.DetailsCount.getText().trim();
					String[] strArr = DetailsCount.replace("of", "").split(" ");
					DetailsCount = strArr[4].trim();
					System.out.println(DetailsCount);
					Thread.sleep(1000);
					if(DashboardCount.equals(DetailsCount))		
					{
						System.out.println("Success !! Count matched. i.e. Count on Dashboard is: " +DashboardCount+"\n" +
															"Count on Details is: " + DetailsCount);
						Reporter.log("Success !! Count matched. i.e. Count on Dashboard is: " +DashboardCount+"\n" +
															"Count on Details is: " + DetailsCount); 
					}
					else
					{
						System.out.println("Failure !! Count mismatched. . i.e. Count on Dashboard is: " +DashboardCount+"\n" +
															"Count on Details is: " + DetailsCount);
						Reporter.log("Failure !! Count mismatched. . i.e. Count on Dashboard is: " +DashboardCount+"\n" +
								"Count on Details is: " + DetailsCount); 
						
						AssertFailedCount++;
					}
						
					
					if(AssertFailedCount>0)	
					{
		
						//Marking this test as Failed
		
						System.out.println("---- Test Failed. Please check the console or TestNg report for details");
						Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
		
						Assert.fail();
					}
		}
			
}