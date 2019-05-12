package products.EM;

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

import pageFactory.EM.AgencyHomePage;
import pageFactory.EM.ErrorPage;
import pageFactory.EM.EventSettingsPage;
import pageFactory.EM.InstructorHomePage;
import pageFactory.EM.LoginPage;
import org.openqa.selenium.JavascriptExecutor;



import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;


public class SearchTest {
	
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
	 * Verify Search Records
	*/ 
	@Test
	private void Fullname_Search() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify Search Records"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify Search Records"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);
		InstructorHomePage home= new InstructorHomePage(driver);
		EventSettingsPage eve = new EventSettingsPage(driver);
	
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
			Thread.sleep(5000);
		
			System.out.println("Step 2 : Login as an agency Administrator");
			Reporter.log("Step 2 : Login as an agency Administrator"); 
				
			
				login.EnterUsername(Constants.EM_Admin_Username);
				login.EnterPassword(Constants.EM_Admin_Password);
				login.ClickOnLogInButton();
				Thread.sleep(5000);
				
			
			System.out.println("Step 3 : Goto the agency user search");
			Reporter.log("Step 3 : Goto the agency user search"); 
			
			home.Click_DemoAgency();			
			Thread.sleep(2000);
			
			home.Click_User();
			Thread.sleep(1000);
			
			System.out.println("Step 4 : Enter a common name, eg. Admin");
			Reporter.log("Step 4 : Enter a common name, eg. Admin");
			
			home.Enter_Search_Input();
			Thread.sleep(2000);
			home.Click_Search();
			Thread.sleep(1000);
			
			String expectedtext0 = "Ke-Testing, Cecilia";
			String expectedtext1 = "ke-testing, Test";
			
			String actualtext0 = SeleniumFunc.GetElementText("css", "#user_select_form > table > tbody > tr:nth-child(1) > td:nth-child(2) > a > span").trim();
			String actualtext1= SeleniumFunc.GetElementText("css", "#user_select_form > table > tbody > tr:nth-child(2) > td:nth-child(2) > a > span").trim();
			
			if(actualtext0.equalsIgnoreCase(expectedtext0)&&actualtext1.equalsIgnoreCase(expectedtext1))
			
			{
				System.out.println("Success !! Multiple search records available");
				Reporter.log("Success !! Multiple search records available"); 
			
			}
			else
			{
			
				System.out.println("Failure !! Record not found"+"Expected Text1:"+expectedtext0 +"Actual Text1:"+actualtext0+"\n"+"Expected Text2: "+expectedtext1 +"Actual Text2: "+actualtext1);
				Reporter.log("Failure !! Record not found"+"Expected Text1: "+expectedtext0 +"Actual Text1:"+actualtext0+"\n"+"Expected Text2: "+expectedtext1 +"Actual Text2: "+actualtext1); 
				AssertFailedCount++;
			
			}

			System.out.println("Step 5 : Enter First and Last name of one of the result, eg. Admin,Test");
			Reporter.log("Step 5 : Enter First and Last name of one of the result, eg. Admin,Test"); 
			
			home.Enter_Search_Input2();
			Thread.sleep(1000);
			home.Click_Search();
			Thread.sleep(1000);
			String actualtext= SeleniumFunc.GetElementText("css", "#user_select_form > table > tbody > tr > td:nth-child(2) > a > span");
			String expecttext1= "Doni Jr., Sarfaraz";
			
			if(actualtext.contains(expecttext1))
			{
				System.out.println("Success !! Only Single Record Present");
				Reporter.log("Success !! Only Single Record Present"); 
			
			}
			else
			{
				System.out.println("Failure !! Record not found"+"Expected Text: "+expecttext1 +"Actual Text1:"+actualtext);
				Reporter.log("Failure !! User is not logged in successfully"); 
				AssertFailedCount++;
			
			}
			
			System.out.println("======================================================" + "\n" +
					"Repeat Steps : Verify Search Records for Program Instructor,Student,Event,& Manage Instructor"  + "\n" +
		 			"======================================================");
			Reporter.log("====" + "\n" +
					"Repeat Step : Verify Search Records for Program Instructor,Student,Event,& Manage Instructor"  + "\n" +
		 			"====");
			
			System.out.println("Step 6 : Search for Program Instructor");
			Reporter.log("Step 6 : Search for Program Instructor");
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/panel/keadmin/");
			home.Click_Demo_Hunter();
			Thread.sleep(1000);
			home.Click_Search_Instructor();
			Thread.sleep(1000);
			home.Enter_Search_Input2();
			Thread.sleep(1000);
			home.Click_Search();
			
			String actualtext2= SeleniumFunc.GetElementText("css", "#proctor_select_form > table > tbody > tr > td:nth-child(3) > a");
			String expecttext2= "Doni Jr., Sarfaraz";
			
			if(actualtext.contains(expecttext1))
			{
				System.out.println("Success !! Program Instructor record found.");
				Reporter.log("Success !! Program Instructor record found."); 
			
			}
			else
			{
				System.out.println("Failure !! Record not found"+"Expected Text: "+expecttext2 +"Actual Text1:"+actualtext2);
				Reporter.log("Failure !! User is not logged in successfully"); 
				AssertFailedCount++;
			
			}

			System.out.println("Step 7 : Search for Student");
			Reporter.log("Step 7 : Search for Student");
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/panel/keadmin/");
			home.Click_Demo_Hunter();
			Thread.sleep(1000);
			home.Click_Search_Student();
			Thread.sleep(1000);
			
			home.Enter_Search_Student();
			Thread.sleep(1000);
			home.Click_SearchNew();
			
			String actualtextS= SeleniumFunc.GetElementText("css", "#registrations > tbody > tr > td:nth-child(3) > a");
			String expecttextS= "blah, blah";
			
			if(actualtextS.contains(expecttextS))
			{
				System.out.println("Success !! Student record found.");
				Reporter.log("Success !! Student record found."); 
			
			}
			else
			{
				System.out.println("Failure !! Record not found "+"Expected Text : "+expecttextS +" Actual Text1: "+actualtextS);
				Reporter.log("Failure !! Record not found "+"Expected Text: "+expecttextS +" Actual Text1: "+actualtextS); 
				AssertFailedCount++;
			
			}
			
			System.out.println("Step 8 : Search for Event");
			Reporter.log("Step 8 : Search for Event");
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/panel/keadmin/");
			home.Click_Demo_Hunter();
			Thread.sleep(1000);
			eve.SearchEvent.click();
			Thread.sleep(1000);
			SeleniumFunc.ClickOnElement("css","#search_form > div.btn-group.bootstrap-select.span3.col-ignore > button");
			SeleniumFunc.ClickOnElement("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore.open > div > ul > li:nth-child(5) > a");
			//SeleniumFunc.SelectValueFromDropdownList("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore > button > span.filter-option.pull-left", "Name");
			Thread.sleep(1000);
			eve.Enter_Event_Name();
			Thread.sleep(2000);
			home.Click_Search();
			Thread.sleep(2000);
			String actual = SeleniumFunc.GetElementText("css", "#page > div.content.clearfix > div.row-fluid > div.span9 > div:nth-child(5) > table > tbody > tr:nth-child(1) > td:nth-child(2) > a");
			if(actual.equalsIgnoreCase("Demo Hunter Education Field Day"))
			{
				System.out.println("Success !! Event record found.");
				Reporter.log("Success !! Student record found."); 
			
			}
			else
			{
				System.out.println("Failure !! Record not found" +"Actual Text1:"+actual);
				Reporter.log("Failure !! Record not found"+"Actual Text1:"+actual); 
				AssertFailedCount++;
			
			}
			
			System.out.println("Step 9 : Search for Manage instructor on Event Page");
			Reporter.log("Step 9 : Search for Manage instructor on Event Page");
			
			SeleniumFunc.ClickOnElement("linkText", "Demo Hunter Education Field Day");
			Thread.sleep(1000);
			SeleniumFunc.ClickOnElement("linkText", "Manage Instructors");
			Thread.sleep(2000);
			home.Enter_Search_Input2();
			Thread.sleep(1000);
			home.Click_Search();
			Thread.sleep(1000);
			String actualtextP= SeleniumFunc.GetElementText("css", "#instructor_associate_form > div:nth-child(1) > div > table > tbody > tr > td:nth-child(3)");
			String expecttextP= "Doni, Sarfaraz, Jr.";
			
			if(actualtextP.contains(expecttextP))
			{
				System.out.println("Success !! Instructor record found for Manage instructor search.");
				Reporter.log("Success !!  Instructor record found for Manage instructor search."); 
			
			}
			else
			{
				System.out.println("Failure !! Record not found"+"Expected Text: "+expecttextP +"Actual Text:"+actualtextP);
				Reporter.log("Failure !! Record not found"); 
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
	
	/* Test 2: 
	 * Verify Search Records with Leading/Trailing Spaces in search box
	*/ 
	
		@Test
		private void Leading_Space() throws Exception
			{
				System.out.println("====" + "\n" +
							"Test 1 : Verify Records for Leading/Trailing Spaces in Searches"  + "\n" +
				 			"====");
				Reporter.log("====" + "\n" +
				 			  "Test 1 : Verify Records for Leading/Trailing Spaces in Searches"  + "\n" +
						 	  "====");	
				
				int AssertFailedCount=0 ;
				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				LoginPage login = new LoginPage(driver);
				InstructorHomePage home= new InstructorHomePage(driver);
				EventSettingsPage eve = new EventSettingsPage(driver);
			
				System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login");
				Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login"); 
					
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
					Thread.sleep(5000);
				
					System.out.println("Step 2 : Login as an agency Administrator");
					Reporter.log("Step 2 : Login as an agency Administrator"); 
						
					
						login.EnterUsername(Constants.EM_Admin_Username);
						login.EnterPassword(Constants.EM_Admin_Password);
						login.ClickOnLogInButton();
						Thread.sleep(5000);
						
					
					System.out.println("Step 3 : Goto the agency user search");
					Reporter.log("Step 3 : Goto the agency user search"); 
					
					home.Click_DemoAgency();			
					Thread.sleep(2000);
					
					home.Click_User();
					Thread.sleep(1000);
					
					System.out.println("Step 5 : Search with an empty search box using the Name search type.");
					Reporter.log("Step 5 : Search with an empty search box using the Name search type.");
					
					home.Enter_Search_Input2();
					Thread.sleep(2000);
					home.Click_Search();	
					
					String actualusertext= SeleniumFunc.GetElementText("css", "#user_select_form > table > tbody > tr > td:nth-child(2) > a > span");
					String expectusertext= "Doni Jr., Sarfaraz";
					
					if(actualusertext.contains(expectusertext))
					{
						System.out.println("Success !! Searched User record found.");
						Reporter.log("Success !! Searched Instructor record found."); 
					
					}
					else
					{
						System.out.println("Failure !! Record not found "+" Expected Text: "+expectusertext +"Actual Text1: "+actualusertext);
						Reporter.log("Failure !! Record not found "+" Expected Text: "+expectusertext +"Actual Text1: "+actualusertext); 
						AssertFailedCount++;
					
					}
					
					System.out.println("Step 5 : Add a space before the name in the search box and search again.");
					Reporter.log("Step 5 : Add a space at the end of the name in the search box and search again."); 
					
					home.Enter_Search_Input_LeadingSpace();
					Thread.sleep(1000);
					home.Click_Search();
					Thread.sleep(1000);
					
					String actualusertext0= SeleniumFunc.GetElementText("css", "#user_select_form > table > tbody > tr > td:nth-child(2) > a > span");
					String expectusertext0= "Doni Jr., Sarfaraz";
					
					if(actualusertext.contains(expectusertext))
					{
						System.out.println("Success !! Searched User record found with Leading Space.");
						Reporter.log("Success !! Searched User record found with Leading Space."); 
					
					}
					else
					{
						System.out.println("Failure !! Record not found "+" Expected Text: "+expectusertext0 +"Actual Text1: "+actualusertext0);
						Reporter.log("Failure !! Record not found "+" Expected Text: "+expectusertext0 +"Actual Text1: "+actualusertext0); 
						AssertFailedCount++;
					
					}
					
					System.out.println("Step 5 : Add a space at the end of the name in the search box and search again.");
					Reporter.log("Step 5 : Add a space at the end of the name in the search box and search again."); 
					Thread.sleep(1000);
					home.Enter_Search_Input_TrailingSpace();
					Thread.sleep(1000);
					home.Click_Search();
					Thread.sleep(1000);
					
					String actualusertext1= SeleniumFunc.GetElementText("css", "#user_select_form > table > tbody > tr > td:nth-child(2) > a > span");
					String expectusertext1= "Doni Jr., Sarfaraz";
					
					if(actualusertext1.contains(expectusertext1))
					{
						System.out.println("Success !! Searched User record found with Trailing Space.");
						Reporter.log("Success !! Searched User record found with Trailing Space."); 
					
					}
					else
					{
						System.out.println("Failure !! Record not found "+" Expected Text: "+expectusertext1 +"Actual Text1: "+actualusertext1);
						Reporter.log("Failure !! Record not found "+" Expected Text: "+expectusertext1 +"Actual Text1: "+actualusertext1); 
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
		/* Test 3: 
		 * Verify Default Value of search type is set to Name and not as All
		*/ 
		@Test
		private void SearchType_Name() throws Exception
			{
				System.out.println("====" + "\n" +
							"Test 1 : Verifythe search type should be defaulted to *Name*.There should not be an *All* search type"  + "\n" +
				 			"====");
				Reporter.log("====" + "\n" +
				 			  "Test 1 : Verify Records for Leading/Trailing Spaces in Searches"  + "\n" +
						 	  "====");	
				
				int AssertFailedCount=0 ;
				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				LoginPage login = new LoginPage(driver);
				InstructorHomePage home= new InstructorHomePage(driver);
				EventSettingsPage eve = new EventSettingsPage(driver);
			
				System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login");
				Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login"); 
					
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
					Thread.sleep(5000);
				
					System.out.println("Step 2 : Login as an agency Administrator");
					Reporter.log("Step 2 : Login as an agency Administrator"); 
						
					
						login.EnterUsername(Constants.EM_Admin_Username);
						login.EnterPassword(Constants.EM_Admin_Password);
						login.ClickOnLogInButton();
						Thread.sleep(5000);
						
					
					System.out.println("Step 3 : Goto the agency user search");
					Reporter.log("Step 3 : Goto the agency user search"); 
					((JavascriptExecutor)driver).executeScript("scroll(1022,1060)");
					home.Click_DemoAgency();			
					Thread.sleep(2000);
					
					home.Click_User();
					Thread.sleep(1000);
					
					System.out.println("Step 4 : Verify the search type should be defaulted to *Name*.");
					Reporter.log("Step 4 : Verify the search type should be defaulted to *Name*.");
					
					String actualtextN = SeleniumFunc.GetElementText("css", "#search_form > div > button");
					if(actualtextN.contains("Name"))
					{
						System.out.println("Success !! Search type is displayed as Name.");
						Reporter.log("Success !! Search type is displayed as Name."); 
					
					}
					else
					{
						System.out.println("Failure !! Search Type Name not found " +"Actual Text: "+actualtextN);
						Reporter.log("Failure !! Search Type Name not found " +"Actual Text: "+actualtextN); 
						AssertFailedCount++;
					
					}
					
					System.out.println("Step 5 : Search with an empty search box using the Name search type.");
					Reporter.log("Step 5 : Search with an empty search box using the Name search type.");
					home.Click_Search();
					
					if(SeleniumFunc.IsElementPresent("xpath", ".//*[@id='user_select_form']/table/tbody/tr"))
					{
						System.out.println("Success !! Search result found");
						Reporter.log("Success !! Search result found"); 
					
					}
					else
					{
						System.out.println("Failure !! Search result not found!! ");
						Reporter.log("Failure !! Search Type Name not found!! "); 
						AssertFailedCount++;
					
					}
					
					System.out.println("Step 6 : Goto the program Instructor.");
					Reporter.log("Step 6 : Goto the program Instructor..");
					
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/panel/keadmin/");
					
					home.Click_Demo_Hunter();
					Thread.sleep(1000);
					home.Click_Search_Instructor();
					Thread.sleep(1000);
					
					String ActualtextPI= SeleniumFunc.GetElementText("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore > button > span.filter-option.pull-left");
					if(ActualtextPI.contains("Name"))
					{
						System.out.println("Success !! Search Type displayed default value with value as Name");
						Reporter.log("Success !! Search Type displayed default value with value as Name"); 
					
					}
					else
					{
						System.out.println("Failure !! wrong Search type default value displayed");
						Reporter.log("Failure !! wrong Search type default value displayed"); 
						AssertFailedCount++;
					
					}
					
					System.out.println("Step 7 : Search with an empty search box using the Name search type.");
					Reporter.log("Step 7 : Search with an empty search box using the Name search type.");
					home.Click_Search();
					Thread.sleep(2000);
					
					if(SeleniumFunc.IsElementPresent("xpath", ".//*[@id='proctor_select_form']/table/tbody/tr"))
					{
						System.out.println("Success !! Search result found");
						Reporter.log("Success !! Search result found"); 
					
					}
					else
					{
						System.out.println("Failure !! Search result not found!! ");
						Reporter.log("Failure !! Search result not found!! ");
						AssertFailedCount++;
					}
					
					System.out.println("Step 8 : Go to the event search.");
					Reporter.log("Step 8 : Go to the event search.");
					
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/panel/keadmin/");
					home.Click_Demo_Hunter();
					Thread.sleep(1000);
					eve.SearchEvent.click();
					Thread.sleep(1000);
					
					System.out.println("Step 9 : The search type should be defaulted to *Today*.");
					Reporter.log("Step 9 : The search type should be defaulted to *Today*.");
					
					String actualtextEvent = SeleniumFunc.GetElementText("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore > button > span.filter-option.pull-left");
					if(actualtextEvent.contains("Today"))
					{
						System.out.println("Success !! Search Type displayed default value with value as Today");
						Reporter.log("Success !! Search Type displayed default value with value as Today"); 
					
					}
					else
					{
						System.out.println("Failure !! wrong Search type default value displayed");
						Reporter.log("Failure !! wrong Search type default value displayed"); 
						AssertFailedCount++;
					
					}
					
					System.out.println("Step 10 : Search with an empty search box using the Name search type.");
					Reporter.log("Step 10 : Search with an empty search box using the Name search type.");
					Thread.sleep(1000);
					SeleniumFunc.ClickOnElement("css","#search_form > div.btn-group.bootstrap-select.span3.col-ignore > button");
					Thread.sleep(2000);
					SeleniumFunc.ClickOnElement("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore.open > div > ul > li:nth-child(5) > a");
					home.Click_Search();
					Thread.sleep(3000);
		

					if(SeleniumFunc.IsElementPresent("xpath", ".//*[@id='page']/div[2]/div[2]/div[2]/div[2]/table/tbody/tr"))
					{
						System.out.println("Success !! All Event Search result found");
						Reporter.log("Success !! All Event Search result found"); 
					
					}
					else
					{
						System.out.println("Failure !! Search result not found!! ");
						Reporter.log("Failure !! Search Type Name not found!! "); 
						AssertFailedCount++;
					}
					
					System.out.println("Step 11 : Go to the location search.");
					Reporter.log("Step 11 :Go to the location search.");
					
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/location/search/13");
					//home.Click_Demo_Hunter();
					//Thread.sleep(1000);
					//SeleniumFunc.ClickOnElement("css", "#page > div.content.clearfix > div.row-fluid > div.span3 > div > ul > li:nth-child(18) > a");
					Thread.sleep(2000);
					
					System.out.println("Step 12 : The search type should be defaulted to Name");
					Reporter.log("Step 12 : The search type should be defaulted to Name");
					
					String actualTextL= SeleniumFunc.GetElementText("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore > button > span.filter-option.pull-left");
					if(actualTextL.contains("Name"))
					{
						System.out.println("Success !! Search Type displayed default value with value as Today");
						Reporter.log("Success !! Search Type displayed default value with value as Today"); 
					
					}
					else
					{
						System.out.println("Failure !! wrong Search type default value displayed");
						Reporter.log("Failure !! wrong Search type default value displayed"); 
						AssertFailedCount++;
					
					}
					
					
					System.out.println("Step 13 : Search with an empty search box using the Name search type.");
					Reporter.log("Step 13 : Search with an empty search box using the Name search type.");
					Thread.sleep(1000);
					home.Click_Search();
					Thread.sleep(1000);
					
					String actualtextL1= SeleniumFunc.GetElementText("css", "#locations > tbody > tr:nth-child(1) > td:nth-child(2) > a").trim();
					String actualtextL2= SeleniumFunc.GetElementText("css", "#locations > tbody > tr:nth-child(2) > td:nth-child(2) > a").trim();
					String actualtextL3= SeleniumFunc.GetElementText("css", "#locations > tbody > tr:nth-child(3) > td:nth-child(2) > a").trim();
					
					if(actualtextL1.equalsIgnoreCase("Addison Police Dept") && actualtextL2.equalsIgnoreCase("Alpine Shooting Range") && actualtextL3.equalsIgnoreCase("Bass Pro Shop"))
					{
						System.out.println("Success !! All Location Search result found "+"  Actual Text 1: "+actualtextL1+" Actual Text 2: "+actualtextL2 +" Actual text 3: "+actualtextL3);
						Reporter.log("Success !! All Location Search result found "+"  Actual Text 1: "+actualtextL1+" Actual Text 2: "+actualtextL2 +" Actual text 3: "+actualtextL3);					
					}
					else
					{
						System.out.println("Failure !! All Location Search result not found "+"  Actual Text 1: "+actualtextL1+" Actual Text 2: "+actualtextL2 +" Actual text 3: "+actualtextL3);
						Reporter.log("Failure !! All Location Search result found "+"  Actual Text 1: "+actualtextL1+" Actual Text 2: "+actualtextL2 +" Actual text 3: "+actualtextL3); 
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
	
		/* Test 4: 
		 * Verify Multiselect Filters on Searches
		*/ 
		/*	@Test
		private void Search_Filter() throws Exception
			{
				System.out.println("====" + "\n" +
							"Test 4 : Multiselect Filters on Searches"  + "\n" +
				 			"====");
				Reporter.log("====" + "\n" +
				 			  "Test 4 : Multiselect Filters on Searches"  + "\n" +
						 	  "====");	
				
				int AssertFailedCount=0 ;
				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				LoginPage login = new LoginPage(driver);
				InstructorHomePage home= new InstructorHomePage(driver);
				EventSettingsPage eve = new EventSettingsPage(driver);
			
				System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login");
				Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login"); 
					
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
					Thread.sleep(5000);
				
					System.out.println("Step 2 : Log in as an instructor and click on Program Locations.");
					Reporter.log("Step 2 : Log in as an instructor and click on Program Locations."); 
						
					
						login.EnterUsername(Constants.EM_Instructor_Username);
						login.EnterPassword(Constants.EM_Instructor_Password);
						login.ClickOnLogInButton();
						Thread.sleep(5000);
						
					SeleniumFunc.ClickOnElement("css", "#page > div.content.clearfix > div.row-fluid > div.span7 > div > div > ul > li > a");
					Thread.sleep(1000);
					SeleniumFunc.ClickOnElement("css", "#page > div.content.clearfix > div.row-fluid > div.span3 > div > ul > li:nth-child(17) > a");
					Thread.sleep(1000);
					System.out.println("Step 3 : Location Search form Should display.");
					Reporter.log("Step 3 : Location Search form Should display."); 
					
					String actualtext = SeleniumFunc.GetElementText("css", "#page > div.content.clearfix > div.row-fluid > div.span9 > h3");
					if(actualtext.contains("Location Search"))
					{
						System.out.println("Sucess !! Location Search form available. "+"Actual text: "+actualtext);
						Reporter.log("Sucess !! Location Search form available"); 
						
					}
					else
					{
						System.out.println("Failure !!  Location Search form not available."+"Actual text: "+actualtext);
						Reporter.log("Sucess !! Location Search form not available"+"Actual text: "+actualtext); 
						AssertFailedCount++;
					}
					
					System.out.println("Step 4 : Click the search type dropdown (should say Name)");
					Reporter.log("Step 4 : Click the search type dropdown (should say Name)");
					
					SeleniumFunc.ClickOnElement("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore.open > button > span.filter-option.pull-left");
					Thread.sleep(100);
					
					System.out.println("Step 5 : A list of search types should appear:" + "\n" + "Name,Address,City,Zip code,Region,Country");
					Reporter.log("Step 5 : A list of search types should appear:" + "\n" + "Name,Address,City,Zip code,Region,Country");
					
					String name = SeleniumFunc.GetElementText("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore.open > div > ul > li.selected > a");
					String address = SeleniumFunc.GetElementText("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore.open > div > ul > li:nth-child(2) > a");
					String city = SeleniumFunc.GetElementText("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore.open > div > ul > li:nth-child(3) > a");
					String zip = SeleniumFunc.GetElementText("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore.open > div > ul > li:nth-child(4) > a");
					String region = SeleniumFunc.GetElementText("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore.open > div > ul > li:nth-child(5) > a");
					String country = SeleniumFunc.GetElementText("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore.open > div > ul > li:nth-child(6) > a");
			
					if(name.contains("Name") && address.contains("Address")
							&& city.contains("City") && zip.contains("ZIP Code")
							&& region.contains("Region") && country.contains("Country"))
					{
						System.out.println("Sucess !! A list of search types is available");
						Reporter.log("Sucess !! A list of search types is available");
					}
					else
					{
						System.out.println("Failure !! A list of search types is not available :" +"List 1 :" +name
								+" List 2 :" +address+" List 3 :" +city +" List 4 : "+zip+" List 5: "+region
								+" List 6 : "+country);
						
						Reporter.log("Failure !! A list of search types is not available :" +"List 1 :" +name
								+" List 2 :" +address+" List 3 :" +city +" List 4 : "+zip+" List 5: "+region
								+" List 6 : "+country);
						AssertFailedCount++;
					}
					
					System.out.println("Step 6 : Select Region");
					Reporter.log("Step 6 : Select Region");
					
					SeleniumFunc.ClickOnElement("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore.open > button > span.filter-option.pull-left");
					SeleniumFunc.ClickOnElement("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore.open > div > ul > li.selected > a");
					System.out.println("Step 7 : Verify the search field changed from a text box to a dropdown that says: Please select a region.");
					Reporter.log("Step 7 : Verify the search field changed from a text box to a dropdown that says: Please select a region.");
					
					String actualtextR=SeleniumFunc.GetElementText("css", "#search_form > div.btn-group.bootstrap-select.show-tick.search-type.span7.col-ignore.open > button > span.filter-option.pull-left");
					
					if(actualtextR.contains("Please select a region"))
					{
						System.out.println("Sucess : Please Select region text is displayed!!");
						Reporter.log("Sucess : Please Select region text is displayed!!");
					}
					else
					{
						System.out.println("Failure !! Please Select region text is not displayed!!"+" Actaul text : "+actualtextR);
						Reporter.log("Failure : Please Select region text is displayed!!"+" Actaul text : "+actualtextR);
						AssertFailedCount++;
					}
					
					System.out.println("Step 8 : Click the dropdown that says: Please select a region.");
					Reporter.log("Step 8 : Click the dropdown that says: Please select a region.");
					
					SeleniumFunc.ClickOnElement("css", "#search_form > div.btn-group.bootstrap-select.show-tick.search-type.span7.col-ignore.open > button > span.filter-option.pull-left");
					
					System.out.println("Step 9 : Verify Select All & Deselect All button available.");
					Reporter.log("Step 9 : Verify Select All & Deselect All button available.");
					
					String actualtext1 = SeleniumFunc.GetElementText("css", "#search_form > div.btn-group.bootstrap-select.show-tick.search-type.span7.col-ignore.open > div > div > div > button.actions-btn.bs-select-all.btn.btn-default");
					String actualtext2 = SeleniumFunc.GetElementText("css", "#search_form > div.btn-group.bootstrap-select.show-tick.search-type.span7.col-ignore.open > div > div > div > button.actions-btn.bs-deselect-all.btn.btn-default");
					
					if(actualtext1.contains("Select All") && actualtext2.contains("Deselect All"))
					{
						System.out.println("Sucess : Select All & Deselect All button is displayed!!");
						Reporter.log("Sucess : Select All & Deselect All button is displayed!!");
					}
					else
					{
						System.out.println("Failure !! Select All & Deselect All button is not displayed!!"+" Actaul text1 : "+actualtext1 +" Actual text2: "+actualtext2);
						Reporter.log("Failure : Select All & Deselect All button is not displayed!!"+" Actaul text : "+actualtext1 +" Actual text2: "+actualtext2);
						AssertFailedCount++;
					}
					
					System.out.println("Step 10 : Click Select All");
					Reporter.log("Step 10 : Click Select All");
					
					SeleniumFunc.ClickOnElement("css", "#search_form > div.btn-group.bootstrap-select.show-tick.search-type.span7.col-ignore.open > div > div > div > button.actions-btn.bs-select-all.btn.btn-default");
					
					System.out.println("Step 11 : All the options should be selected.");
					Reporter.log("Step 11 : All the options should be selected.");
					
					String actualtextOpS = SeleniumFunc.GetElementText("css", "#search_form > div.btn-group.bootstrap-select.show-tick.search-type.span7.col-ignore.open > button > span.filter-option.pull-left");
					if(actualtextOpS.contains("North, South, East, West"))
					{
						System.out.println("Sucess : All options Selected!!");
						Reporter.log("Sucess : All options selected!!");
					}
					else
					{
						System.out.println("Failure !! All options not Selected!!"+" Actaul text1 : "+actualtextOpS);
						Reporter.log("Failure : All options not Selected!!"+" Actaul text : "+actualtextOpS);
						AssertFailedCount++;
					}
					
					System.out.println("Step 12 : Click on Search.");
					Reporter.log("Step 12 : Click on Search.");
					
					
					
			}*/

		/* Test 5: 
		 * Verify Place holder text
		*/ 
		@Test
		private void PlaceHolder_Text() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 1 : Verify Place holder text "  + "\n" +
			 			"====");
			Reporter.log("====" + "\n" +
			 			  "Test 1 : Verify Place holder text "  + "\n" +
					 	  "====");	
			
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			InstructorHomePage home= new InstructorHomePage(driver);
			EventSettingsPage eve = new EventSettingsPage(driver);
		
			System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login");
			Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login"); 
				
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
				Thread.sleep(5000);
			
				System.out.println("Step 2 : Login as an agency Administrator");
				Reporter.log("Step 2 : Login as an agency Administrator"); 
					
				
					login.EnterUsername(Constants.EM_Admin_Username);
					login.EnterPassword(Constants.EM_Admin_Password);
					login.ClickOnLogInButton();
					Thread.sleep(5000);
				
				System.out.println("Step 3 : Under Global Settings click Users");
				Reporter.log("Step 3 : Under Global Settings click Users"); 
				
					home.Click_DemoAgency();			
					Thread.sleep(2000);
				
					home.Click_User();
					Thread.sleep(1000);
					
				System.out.println("Step 4 : Verify search page should open.");
				Reporter.log("Step 4 : Verify search page should open.");
					
					String actualtext = SeleniumFunc.GetElementText("css", "#page > div.content.clearfix > div:nth-child(3) > div > h4");
					
					if(actualtext.contains("Search"))
					{
						System.out.println("Sucess : Search Page is displayed!!");
						Reporter.log("Sucess : Search Page is displayed!!");
					}
					else
					{
						System.out.println("Failure !! Search Page is not displayed!!"+" Actaul text : "+actualtext );
						Reporter.log("Failure : Search Page is not displayed!!"+" Actaul text : "+actualtext);
						AssertFailedCount++;
					}
				
				System.out.println("Step 5 : Change the search type to Name & In gray, the search box should now say Enter the user's first or last name.");
				Reporter.log("Step 5 : Change the search type to Name & In gray, the search box should now say Enter the user's first or last name.");
					
					actualtext = SeleniumFunc.GetAttributeValue("id", "search-type-name", "placeholder");
					
					if(actualtext.contains("Enter the user's name."))
					{
						System.out.println("Sucess : Placeholder text : Enter the user's first or last name is displayed");
						Reporter.log("Sucess : Search Page is displayed!!");
					}
					else
					{
						System.out.println("Failure !! Placeholder text : Enter the user's first or last name is not displayed!!"+" Actaul text : "+actualtext );
						Reporter.log("Failure : Placeholder text : Enter the user's first or last name is not displayed!!"+" Actaul text : "+actualtext);
						AssertFailedCount++;
					}
					
				System.out.println("Step 6 : Type a name, e.g. Smith. & Then verify placeholder text should not show up.");
				Reporter.log("Step 6 : Type a name, e.g. Smith. & Then verify placeholder text should not show up.");
					
					home.Enter_Search_Input();
					Thread.sleep(2000);
					
					home.Click_Search();
					Thread.sleep(1000);
					
					actualtext = SeleniumFunc.GetAttributeValue("id", "search-type-name", "value");
					
					if(actualtext.contains("testing"))
					{
						System.out.println("Sucess : placeholder text is not displayed.");
						Reporter.log("Sucess : placeholder text is not displayed.");
					}
					else
					{
						System.out.println("Failure !! placeholder text is displayed.!!"+" Actaul text : "+actualtext );
						Reporter.log("Failure : placeholder text is displayed.!!"+" Actaul text : "+actualtext);
						AssertFailedCount++;
					}
					
				System.out.println("Step 7 : Click Search & Verify searched record displayed");
				Reporter.log("Step 7 : Click Search & Verify searched record displayed");
				
					home.Enter_Search_Input();
					home.Click_Search();
					Thread.sleep(1000);
					String expectedtext0 = "Ke-Testing, Cecilia";
					String expectedtext1 = "ke-testing, test";
				
					String actualtext0 = SeleniumFunc.GetElementText("css", "#user_select_form > table > tbody > tr:nth-child(1) > td:nth-child(2) > a > span").trim();
					String actualtext1= SeleniumFunc.GetElementText("css", "#user_select_form > table > tbody > tr:nth-child(2) > td:nth-child(2) > a > span").trim();
				
					if(actualtext0.equalsIgnoreCase(expectedtext0)&&actualtext1.equalsIgnoreCase(expectedtext1))
				
					{
						System.out.println("Success !! Multiple search records available");
						Reporter.log("Success !! Multiple search records available"); 
				
					}
					else
					{
				
						System.out.println("Failure !! Record not found"+"Expected Text1: "+expectedtext0 +"Actual Text1:"+actualtext0+"\n"+"Expected Text2: "+expectedtext1 +"Actual Text2: "+actualtext1);
						Reporter.log("Failure !! Record not found"+"Expected Text1: "+expectedtext0 +"Actual Text1:"+actualtext0+"\n"+"Expected Text2: "+expectedtext1 +"Actual Text2: "+actualtext1); 
						AssertFailedCount++;
				
					}
				
			System.out.println("Step 8 : Repeat test on Search for Event");
			Reporter.log("Step 8 : Repeat test on Search for Event");
				
					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/panel/keadmin/");
					home.Click_Demo_Hunter();
					Thread.sleep(1000);
					eve.SearchEvent.click();
					Thread.sleep(1000);
			
			System.out.println("Step 9 : Change the search type to Name");
			Reporter.log("Step 9 : Change the search type to Name");	
		
				SeleniumFunc.ClickOnElement("css","#search_form > div.btn-group.bootstrap-select.span3.col-ignore > button");
				SeleniumFunc.ClickOnElement("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore.open > div > ul > li:nth-child(5) > a");
				//SeleniumFunc.SelectValueFromDropdownList("css", "#search_form > div.btn-group.bootstrap-select.span3.col-ignore > button > span.filter-option.pull-left", "Name");
				Thread.sleep(1000);
				
					actualtext = SeleniumFunc.GetAttributeValue("id", "search-type-name", "placeholder");
				
					if(actualtext.contains("Enter the event name."))
					{
						System.out.println("Sucess : placeholder text displayed.");
						Reporter.log("Sucess : placeholder text displayed.");
					}
					else
					{
						System.out.println("Failure !! placeholder text  is not  displayed.!!"+" Actaul text : "+actualtext );
						Reporter.log("Failure : placeholder text  is not  displayed.!!"+" Actaul text : "+actualtext);
						AssertFailedCount++;
					}
				
				System.out.println("Step 10 : Click the search box and The gray text should disappear.");
				Reporter.log("Step 10 : Change the search type to Name and The gray text should disappear.");
				
						eve.Enter_Event_Name();
						Thread.sleep(2000);
						home.Click_Search();
						Thread.sleep(2000);
						
					actualtext = SeleniumFunc.GetAttributeValue("id", "search-type-name", "value");
						
					if(actualtext.contains("Demo Hunter Education Field Day"))
					{
							System.out.println("Sucess : placeholder text is not displayed.");
							Reporter.log("Sucess : placeholder text is not displayed.");
					}
					else
					{
							System.out.println("Failure !! placeholder text  is displayed.!!");
							Reporter.log("Failure : placeholder text  is displayed.!!");
							AssertFailedCount++;
					}
					
				System.out.println("Step 11 : Repeat Test Search for Manage instructor on Event Page");
				Reporter.log("Step 11 : Repeat Test Search for Manage instructor on Event Page");
					
					SeleniumFunc.ClickOnElement("css", "#page > div.content.clearfix > div.row-fluid > div.span9 > div:nth-child(5) > table > tbody > tr:nth-child(1) > td:nth-child(2) > a");
					Thread.sleep(1000);
					SeleniumFunc.ClickOnElement("css", "#page > div.content.clearfix > div.row-fluid > div.span9 > div.row-fluid > div.span4.event-admin-quicklinks > ul > li:nth-child(4) > a");
					Thread.sleep(2000);
					
					actualtext = SeleniumFunc.GetAttributeValue("id", "search-type-name", "placeholder");
					
					if(actualtext.contains("Enter the user's name."))
					{
						System.out.println("Sucess : placeholder text displayed.");
						Reporter.log("Sucess : placeholder text displayed.");
					}
					else
					{
						System.out.println("Failure !! placeholder text  is not  displayed.!!"+" Actaul text : "+actualtext );
						Reporter.log("Failure : placeholder text  is not  displayed.!!"+" Actaul text : "+actualtext);
						AssertFailedCount++;
					}
				
				System.out.println("Step 12 : Enter users name & Click the search box, The gray placeholder text should disappear.");
				Reporter.log("Step 12 : Enter users name & Click the search box, The gray placeholder text should disappear.");
						
					home.Enter_Search_Input2();
					Thread.sleep(2000);
									
					home.Click_Search();
					Thread.sleep(1000);
					actualtext = SeleniumFunc.GetAttributeValue("id", "search-type-name", "value");
					
					if(actualtext.contains("sarfaraz"))
					{
							System.out.println("Sucess : placeholder text is not displayed.");
							Reporter.log("Sucess : placeholder text is not displayed.");
					}
					else
					{
							System.out.println("Failure !! placeholder text  is displayed.!!");
							Reporter.log("Failure : placeholder text  is displayed.!!");
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
		
		
		//InProgress
		/* Test 5: 
		  * Verify Search Dropdowns
		*/ 
		@Test
		private void Search_Dropdowns() throws Exception
			{
						System.out.println("====" + "\n" +
									"Test 1 : Verify Search Dropdowns "  + "\n" +
						 			"====");
						Reporter.log("====" + "\n" +
						 			  "Test 1 : Verify Search Dropdowns "  + "\n" +
								 	  "====");	
						
						
						int AssertFailedCount=0 ;
						SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
						LoginPage login = new LoginPage(driver);
						InstructorHomePage home= new InstructorHomePage(driver);
						EventSettingsPage eve = new EventSettingsPage(driver);
						AgencyHomePage agencyhome= new AgencyHomePage(driver);
					
						System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login");
						Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_EM +"/login/login"); 
							
							SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
							Thread.sleep(5000);
						
						System.out.println("Step 2 : Login as an agency Administrator");
						Reporter.log("Step 2 : Login as an agency Administrator"); 
								
						SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_EM +"/login/login");
						if(SeleniumFunc.IsElementPresent("linkText", "Log Out"))
						{
							SeleniumFunc.ClickOnElement("linkText", "Log Out");
						}
							
								login.EnterUsername(Constants.EM_Admin_Username);
								login.EnterPassword(Constants.EM_Admin_Password);
								login.ClickOnLogInButton();
								Thread.sleep(5000);
								
						System.out.println("Step 3 : Go the Instructor Search for a program.");
						Reporter.log("Step 3 : Go the Instructor Search for a program."); 
						
								SeleniumFunc.ClickOnElement("linkText", "Colorado Parks & Wildlife");
								agencyhome.ClickToSelectProgram();
								agencyhome.ClickOnSearchInstructorLink();
							
						System.out.println("Step 4 : Change the search type to Region.");
						Reporter.log("Step 4: Change the search type to Region."); 	
						
								SeleniumFunc.ClickOnElement("css", ".bootstrap-select.span3.col-ignore > button");
								SeleniumFunc.ClickOnElement("css", ".col-ignore.open > div > ul > li:nth-child(10) > a > span.text");
								
						System.out.println("Step 5 : Verify (A)The search box should turn into a dropdown menu." + "\n"  
								+ "(B)The agency's regions should be listed in the dropdown menu.");
						Reporter.log("Step 5: Verify (A)The search box should turn into a dropdown menu." + "\n"  
								+ "(B)The agency's regions should be listed in the dropdown menu.");
						
								String Expected = "Please select a region";
								String Actual   = SeleniumFunc.GetElementText("css", "#search_form > div:nth-child(11) > button > span.filter-option.pull-left").trim();
								CharSequence expected = "Northeast" + "\n" +
								"Northwest" + "\n" +
								"Southwest"+ "\n" +
								"Southwest";
								
						if(Actual.equals(Expected))
						{
							System.out.println("Sucess : (A) The search box should changed into a dropdown menu.");
							Reporter.log("Sucess : (A) The search box should changed into a dropdown menu.");
						}
						else
						{
							System.out.println("Failure !! (A) The search box not changed into a dropdown menu.");
							Reporter.log("Failure !! (A) The search box not changed into a dropdown menu.");
							AssertFailedCount++;
						}
						
							SeleniumFunc.ClickOnElement("css", "div:nth-child(11) > button > span.filter-option.pull-left");
							
							String expectedValue = "Southwest";


						if(SeleniumFunc.GetElementText("css", "#search_form > div.btn-group.bootstrap-select.show-tick.search-type.span7.col-ignore.open > div > ul > li:nth-child(1) > a > span.text").equals(expectedValue))
						{
							System.out.println("Sucess : (B) The agency's region listed in the dropdown menu.");
							Reporter.log("Sucess : (B) The agency's region listed in the dropdown menu.");
						}
						else
						{
							System.out.println("Failure : (B) The agency's region not listed in the dropdown menu.");
							Reporter.log("Sucess : (B) The agency's region not listed in the dropdown menu.");
							AssertFailedCount++;
						}
						
					System.out.println("Step 6 : Select a region from the dropdown and click Search.");
					Reporter.log("Step 6: Select a region from the dropdown and click Search."); 
					
						SeleniumFunc.ClickOnElement("css", "#search_form > div.btn-group.bootstrap-select.show-tick.search-type.span7.col-ignore.open > div > ul > li:nth-child(4) > a > span.text");
						home.Click_Search();
						Thread.sleep(2000);
						
					System.out.println("Step 7 : Verify Only instructors from that region should be displayed.");
					Reporter.log("Step 7: Verify Only instructors from that region should be displayed."); 
					
						SeleniumFunc.ClickOnElement("linkText", "View");
						String expectedRegion = "Southwest";
						
						if(SeleniumFunc.GetElementText("css", "#profile > div:nth-child(5) > div:nth-child(3) > dl:nth-child(2)").equals(expectedRegion))
						{
							System.out.println("Sucess : Instructor from that region displayed.");
							Reporter.log("Sucess : Instructor from that region displayed.");
						}
						else
						{
							System.out.println("Failure : Instructor from that region not displayed.");
							Reporter.log("Failure : Instructor from that region not displayed.");
							AssertFailedCount++;
							
						}
						
					
						/*
						List<WebElement> ele = driver.findElements(By.cssSelector(".dropdown-menu.inner>li>a"));
						
						for(WebElement web : ele)
						{
							{
								for(int i=0 ; i<expected.length();i++)
								{
									if(web.getText().contains(expected)){
							             System.out.println("Matched");
							             System.out.println("Sucess : (B) The agency's regions listed in the dropdown menu.");
										Reporter.log("Sucess : (B) The agency's regions listed in the dropdown menu.");
										break;
						             } 
									else
									{
										System.out.println("Failure : (B) The agency's regions not listed in the dropdown menu.");
										Reporter.log("Sucess : (B) The agency's regions not listed in the dropdown menu.");
										AssertFailedCount++;
										
									}
								}
							}
							
						}*/
					
			if(AssertFailedCount>0)	  
			{
				
				//Marking this test as Failed
				
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
				
				Assert.fail();
			}
}
}
			