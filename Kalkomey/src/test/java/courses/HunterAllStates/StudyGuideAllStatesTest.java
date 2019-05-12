package courses.HunterAllStates;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.PageHeader;
import pageFactory.Courses.StudyGuidePage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class StudyGuideAllStatesTest 
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
	  * Verify user can navigate to Study Guide page without login (for most of the states)
	  */ 
	@Test(dataProvider="StudyGuide_hunter",dataProviderClass=utility.TestNG.class)
	private void Navigate_To_StudyGuide_Page(String state, String Header) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify user can navigate to Study Guide page without login (for most of the states)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify user can navigate to Study Guide page without login (for most of the states)"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		
	 	System.out.println("Step 1: Navigating State specific page for course");
		Reporter.log("Step 1: Navigating State specific page for course"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + state);
				
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + state);
				Thread.sleep(4000);
			}
			
		System.out.println("Step 2 : Verifying whether 'Study Guild' link is present on top header");
		Reporter.log("Step 2 : Verifying whether 'Study Guild' link is present on top header");
		
			try
			{
				String actualtext = header.StudyGuide_New.getText();
				String expectedtext = "Study Guide";
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! 'Study Guide' link is present on page header");
					Reporter.log("-- Success !! 'Study Guide' link is present on page header");
					
					System.out.println("Step 3 : Click on 'Study Guide' link");
					Reporter.log("Step 3 : Click on 'Study Guide' link");
						
						header.StudyGuide_New.click();
						Thread.sleep(3000);
						
						actualtext = driver.getCurrentUrl();
						expectedtext = Constants.ApplicationURL_Hunter.substring(5) + state + "/studyGuide/";
						System.out.println(expectedtext);
						
						if(actualtext.contains(expectedtext))
						{
							System.out.println("-- Success !! User is navigated to Study Guide page successfully");
							Reporter.log("-- Success !! User is navigated to Study Guide page successfully");
							
						}
						else
						{
							System.out.println("-- Failure !! User is NOT navigated to Study Guide page and navigated to :" +actualtext);
							Reporter.log("-- Failure !! User is NOT navigated to Study Guide page and navigated to :" +actualtext);
							AssertFailedCount++;
						}
						
				}
				else
				{
					System.out.println("-- Failure !! 'Study Guide' link is NOT present on page header");
					Reporter.log("-- Failure !! 'Study Guide' link is NOT present on page header");
					AssertFailedCount++;
				}
				
			}
			catch(Exception e)
			{
				System.out.println("--  Failure !! 'Study Guide' link is NOT present on page header");
				Reporter.log("--  Failure !! 'Study Guide' link is NOT present on page header");
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
	 * Verify Contents of Study Guide page
	 * */ 
	@Test(dataProvider="StudyGuideStates",dataProviderClass=utility.TestNG.class)
	private void StudyGuide_Content_Verification(String State, String Header) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify Contents of Study Guide page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify Contents of Study Guide page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		StudyGuidePage studyguide = new StudyGuidePage(driver);
		
		System.out.println("Step 1: Navigating State specific page for course");
		Reporter.log("Step 1: Navigating State specific page for course"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/" +  State);
					
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter + "/" +  State);
				Thread.sleep(4000);
			}
			
		System.out.println("Step 2 : Click on 'Study Guide' link");
		Reporter.log("Step 2 : Click on 'Study Guide' link");
			
			header.StudyGuide_New.click();
			Thread.sleep(5000);
						
		System.out.println("Step 3 : Verifying contents on 'Study Guide' page");
		Reporter.log("Step 3 : Verifying contents on 'Study Guide' page");

			//Verifying about sections i.e. title text, body, Learn More button, Register for the Course button
			String expectedtext = "About the Study Guide";
			String actualtext = studyguide.About_PageHeader.getText().trim();
				
			if(actualtext.equals(expectedtext))
			{
				System.out.println(" -- Success !! About section has correct heading i.e. " + actualtext);
				Reporter.log("-- Success !! About section has correct heading i.e. " + actualtext);
			}
			else
			{
				System.out.println(" -- Failure !! About section has incorrect heading i.e. " + actualtext);
				Reporter.log("-- Failure !! About section has correct heading i.e. " + actualtext);
				AssertFailedCount++;
			}
			
			expectedtext = "Learn More";
			actualtext = studyguide.About_LearnMore.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println(" -- Success !! 'Learn More' button is present i.e. " + actualtext);
				Reporter.log("-- Success !! 'Learn More' button is present" + actualtext);
			}
			else
			{
				System.out.println(" -- Failure !!'Learn More' button is NOT present i.e. " + actualtext);
				Reporter.log("-- Failure !! 'Learn More' button is NOT present i.e. " + actualtext);
				AssertFailedCount++;
			}
					
			expectedtext = "Register for the Course";
			actualtext = studyguide.About_RegisterForTheCourse.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println(" -- Success !! 'Register for the Course' button is present i.e. " + actualtext);
				Reporter.log("-- Success !! 'Register for the Course' button is present" + actualtext);
			}
			else
			{
				System.out.println(" -- Failure !! 'Register for the Course' button is NOT present i.e. " + actualtext);
				Reporter.log("-- Failure !! 'Register for the Course' button is NOT present i.e. " + actualtext);
				AssertFailedCount++;
			}
			
			// Verifying content section title
			expectedtext = Header;
			actualtext = studyguide.StudySectionHeaderText.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
					System.out.println(" -- Success !! Content section has correct header i.e. " + actualtext);
					Reporter.log("-- Success !! Content section has correct header i.e. " + actualtext);
			}
			else
			{
					System.out.println(" -- Failure !! Content section has incorrect header i.e. " + actualtext);
					Reporter.log("-- Failure !! Content section has incorrect header i.e. " + actualtext);
					AssertFailedCount++;
			}
							
		System.out.println("Step 4 : Clicking on 'Learn More' button");
		Reporter.log("Step 4 : Clicking on 'Learn More' button");
			
			studyguide.About_LearnMore.click();
			Thread.sleep(4000);

			expectedtext = Constants.ApplicationURL_Hunter.substring(5) +"/" + State;
			actualtext = driver.getCurrentUrl();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println(" -- Success !! user is navigated to Course page i.e. " + actualtext);
				Reporter.log("-- Success !! user is navigated to Course page i.e. " + actualtext);
			}
			else
			{
				System.out.println(" -- Failure !! user is NOT navigated to Course page i.e. " + actualtext);
				Reporter.log("-- Failure !! user is NOT navigated to Course page i.e. " + actualtext);
				AssertFailedCount++;
			}
			
			driver.navigate().back();
			Thread.sleep(4000);

			
		System.out.println("Step 5 : Clicking on 'Register for the Course' button");
		Reporter.log("Step 5 : Clicking on 'Register for the Course' button");
			
			studyguide.About_RegisterForTheCourse.click();
			Thread.sleep(4000);
			
			expectedtext = Constants.ApplicationURL_Hunter.substring(5) + "/" + State + "/sign-up/";
			actualtext = driver.getCurrentUrl();
					
			if(actualtext.contains(expectedtext))
			{
				System.out.println(" -- Success !! user is navigated to Course registration page i.e. " + actualtext);
				Reporter.log("-- Success !! user is navigated to Course registration page i.e. " + actualtext);
			}
			else
			{
				System.out.println(" -- Failure !! user is NOT navigated to Course registration page i.e. " + actualtext);
				Reporter.log("-- Failure !! user is NOT navigated to Course registration page i.e. " + actualtext);
				AssertFailedCount++;
			}
			
			driver.navigate().back();
			Thread.sleep(4000);

		try{
		System.out.println("Step 6 : Verifying whether Units/Topics are displayed on page");
		Reporter.log("Step 6 : Verifying whether Units/Topics are displayed on page");
			
			Thread.sleep(5000);
		
			for (int i= 1; i<7 ; i++)
			{
				expectedtext = "Unit " + i;
				
				if(driver.getPageSource().contains(expectedtext))
				{
					System.out.println(" -- Success !! " + expectedtext + " is present on page");
					Reporter.log("-- Success !! " + expectedtext + " is present on page");
				}
				else
				{
					System.out.println(" -- Failure !! " +  expectedtext + " is NOT present on page");
					Reporter.log("-- Failure !! " + expectedtext + " is NOT present on page");
					AssertFailedCount++;
				}
				
				expectedtext = "Topic " + i;
				
				if(driver.getPageSource().contains(expectedtext))
				{
					System.out.println(" -- Success !! " + expectedtext + " is present on page");
					Reporter.log("-- Success !! " + expectedtext + " is present on page");
				}
				else
				{
					System.out.println(" -- Failure !! " +  expectedtext + " is NOT present on page");
					Reporter.log("-- Failure !! " + expectedtext + " is NOT present on page");
					AssertFailedCount++;
				}
				
			}
		
		
		System.out.println("Step 7 : Verifying whether Quiz/Exmas are displayed on page");
		Reporter.log("Step 7 : Verifying whether Quiz/Exmas are displayed on page");
					
				expectedtext = "Quiz";
				
				if(driver.getPageSource().contains(expectedtext))
				{
					System.out.println(" -- Failure !! " + expectedtext + " is present on page");
					Reporter.log("-- Failure !! " + expectedtext + " is present on page");
					AssertFailedCount++;
				}
				else
				{
					System.out.println(" -- Success !! " +  expectedtext + " is NOT present on page");
					Reporter.log("-- Success !! " + expectedtext + " is NOT present on page");
					
				}
				
				expectedtext = "Practice Exam";
				
				if(driver.getPageSource().contains(expectedtext))
				{
					System.out.println(" -- Failure !! " + expectedtext + " is present on page");
					Reporter.log("-- Failure !! " + expectedtext + " is present on page");
					AssertFailedCount++;
				}
				else
				{
					System.out.println(" -- Success !! " +  expectedtext + " is NOT present on page");
					Reporter.log("-- Success !! " + expectedtext + " is NOT present on page");
					
				}
		} catch (Exception e) {
		
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
	 * Verify user can open a page under any topic in any sequence
	 * */ 
	@Test(dataProvider="StudyGuideStates",dataProviderClass=utility.TestNG.class)
	private void StudyGuide_Topic_Sequence(String State, String Header) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify user can open a page under any topic in any sequence"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify user can open a page under any topic in any sequence"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		StudyGuidePage studyguide = new StudyGuidePage(driver);
		
		System.out.println("Step 1: Navigating State specific page for course");
		Reporter.log("Step 1: Navigating State specific page for course"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter +"/" + State);
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Hunter +"/" + State);
				Thread.sleep(4000);
			}
			
		System.out.println("Step 2 : Click on 'Study Guide' link");
		Reporter.log("Step 2 : Click on 'Study Guide' link");
			
			header.StudyGuide_New.click();
			Thread.sleep(5000);
			
			
		System.out.println("Step 3 : Accessing page for : Unit 1, Topic 1, Sub Topic 1 ");
		Reporter.log("Step 3 : Accessing page for : Unit 1, Topic 1, Sub Topic 1 ");

			String expectedtext = studyguide.Get_href_for_Unit_Topic_SubTopic(1, 1, 1)+"/";
			
			studyguide.ClickOn_Unit_Topic_SubTopic(1, 1, 1);
			
			Thread.sleep(4000);
			
			String actualtext = driver.getCurrentUrl();
				
				if(actualtext.equals(expectedtext))
				{
					System.out.println(" -- Success !! user is navigated to correct sub topic page  i.e. " + actualtext);
					Reporter.log("-- Success !! user is navigated to correct sub topic page  i.e. " + actualtext);
				}
				else
				{
					System.out.println(" -- Failure !!  user is NOT navigated to correct sub topic page  i.e. " + actualtext);
					Reporter.log("-- Failure !!  user is NOT navigated to correct sub topic page  i.e. " + actualtext);
					AssertFailedCount++;
				}
				
		   driver.navigate().back();
			Thread.sleep(4000);
		 
	   System.out.println("Step 4 : Accessing page for : Unit 3, Topic 2, Sub Topic 2 ");
	   Reporter.log("Step 4 : Accessing page for :  Unit 3, Topic 2, Sub Topic 2");

			expectedtext = studyguide.Get_href_for_Unit_Topic_SubTopic(3, 2, 2)+"/";
			
			studyguide.ClickOn_Unit_Topic_SubTopic(3, 2, 2);			
			Thread.sleep(4000);
			
			actualtext = driver.getCurrentUrl();
				
				if(actualtext.equals(expectedtext))
				{
					System.out.println(" -- Success !! user is navigated to correct sub topic page  i.e. " + actualtext);
					Reporter.log("-- Success !! user is navigated to correct sub topic page  i.e. " + actualtext);
				}
				else
				{
					System.out.println(" -- Failure !!  user is NOT navigated to correct sub topic page  i.e. " + actualtext);
					Reporter.log("-- Failure !!  user is NOT navigated to correct sub topic page  i.e. " + actualtext);
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
