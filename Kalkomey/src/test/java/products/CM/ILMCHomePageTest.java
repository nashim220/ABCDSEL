package products.CM;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.ILMCHomePage;



import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class ILMCHomePageTest 
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
	 * Verify presence of billboard
	*/ 
	@Test
	private void BillboardVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify presence of billboard"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify presence of billboard"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		
		System.out.println("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		Reporter.log("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			
			
		System.out.println("Step 2 : Verifying billboard along with text");
		Reporter.log("Step 2 : Verifying billboard along with text"); 
			
			//Verify Header
			String ActualText = homepage.Header.getText().trim();
			String ExpectedText = "Lost Your State Recreation Certification Card?";
			
			if(ActualText.equals(ExpectedText))
			{
				System.out.println("Success !! Billboard header is displayed with correct text. i.e. " +ExpectedText);
				Reporter.log("Success !! Billboard header is displayed with correct text. i.e. " +ExpectedText); 
			}
			else
			{
				System.out.println("Failure !!  Text is incorrect. Expected is : " +ExpectedText +"\n" +
										"Actual is : " +ActualText);
				Reporter.log("Failure !!  Text is incorrect. Expected is : " +ExpectedText +"\n" +
								"Actual is : " +ActualText); 
				
				AssertFailedCount++;
			}
			
			
			//Verify Sub-Header
			ActualText = homepage.SubHeader.getText().trim();
			ExpectedText = "Ordering a Replacement is Easy.";
			
			if(ActualText.equals(ExpectedText))
			{
				System.out.println("Success !! Billboard Sub-header is displayed with correct text. i.e. " +ExpectedText);
				Reporter.log("Success !! Billboard Sub-header is displayed with correct text. i.e. " +ExpectedText); 
			}
			else
			{
				System.out.println("Failure !!  Text is incorrect. Expected is : " +ExpectedText +"\n" +
										"Actual is : " +ActualText);
				Reporter.log("Failure !!  Text is incorrect. Expected is : " +ExpectedText +"\n" +
								"Actual is : " +ActualText); 
				
				AssertFailedCount++;
			}
			
			
			//Verify Text of Steps of order Certification
			//Step 1
			ActualText = homepage.Step1.getText().trim();
			ExpectedText = "1. Search & find" +"\n" + "your record";
			
			if(ActualText.equals(ExpectedText))
			{
				System.out.println("Success !! Text of first steps is displayed properly. i.e. " +ExpectedText);
				Reporter.log("Success !! Text of first steps is displayed properly. i.e. " +ExpectedText); 
			}
			else
			{
				System.out.println("Failure !!  Text is incorrect. Expected is : " +ExpectedText +"\n" +
										"Actual is : " +ActualText);
				Reporter.log("Failure !!  Text is incorrect. Expected is : " +ExpectedText +"\n" +
								"Actual is : " +ActualText); 
				
				AssertFailedCount++;
			}
			
			
			//Step 2
			ActualText = homepage.Step2.getText().trim();
			ExpectedText = "2. Verify your details";
			
			if(ActualText.equals(ExpectedText))
			{
				System.out.println("Success !! Text of second steps is displayed properly. i.e. " +ExpectedText);
				Reporter.log("Success !! Text of second steps is displayed properly. i.e. " +ExpectedText); 
			}
			else
			{
				System.out.println("Failure !!  Text is incorrect. Expected is : " +ExpectedText +"\n" +
										"Actual is : " +ActualText);
				Reporter.log("Failure !!  Text is incorrect. Expected is : " +ExpectedText +"\n" +
								"Actual is : " +ActualText); 
				
				AssertFailedCount++;
			}
			
			
			//Step 3
			ActualText = homepage.Step3.getText().trim();
			ExpectedText = "3. Order your replacement card";
			
			if(ActualText.equals(ExpectedText))
			{
				System.out.println("Success !! Text of third steps is displayed properly. i.e. " +ExpectedText);
				Reporter.log("Success !! Text of third steps is displayed properly. i.e. " +ExpectedText); 
			}
			else
			{
				System.out.println("Failure !!  Text is incorrect. Expected is : " +ExpectedText +"\n" +
										"Actual is : " +ActualText);
				Reporter.log("Failure !!  Text is incorrect. Expected is : " +ExpectedText +"\n" +
								"Actual is : " +ActualText); 
				
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
	 * Verify header and footer of home page
	 */ 
	@Test
	private void VerifyHeaderFooter() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify header and footer of home page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify header and footer of home page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		
		System.out.println("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		Reporter.log("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			
			
		System.out.println("Step 2 : Verifying Header logo");
		Reporter.log("Step 2 : Verifying Header logo"); 
			
			//Verify Header
					
			if(SeleniumFunc.IsElementPresent(homepage.Logo))
			{
				System.out.println("Success !! Header is present with logo");
				Reporter.log("Success !! Header is present with logo"); 
			}
			else
			{
				System.out.println("Failure !! Logo is missing");
				Reporter.log("Failure !! Logo is missing"); 
				
				AssertFailedCount++;
			}
			
		
		System.out.println("Step 3 : Verifying footer is present along with UI element");
		Reporter.log("Step 3 : Verifying footer is present along with UI element"); 
				
		
			//Verify Footer
			if(SeleniumFunc.IsElementPresent(homepage.Footer))
			{
				System.out.println("Success !! Footer is present");
				Reporter.log("Success !! Footer is present"); 
			}
			else
			{
				System.out.println("Failure !! Footer is missing");
				Reporter.log("Failure !! Footer is missing"); 
			
				AssertFailedCount++;
			}
			
			//Kalkomey URL
			String ActualText = homepage.KalkomeyURLText.getText().trim();
			String ExpectedText = "Kalkomey Enterprises, Inc.";
			
			String ActualURL = homepage.KalkomeyURL.getAttribute("href");
			String ExpectedURL = "http://www.kalkomey.com/";
			
			if(ActualText.equals(ExpectedText) && ActualURL.equals(ExpectedURL))
			{
				System.out.println("Success !! Link for Kalkomey is present with correct text. i.e. Text: " +ExpectedText + 
						"URL: "+ ExpectedURL);
				Reporter.log("Success !! Link for Kalkomey is present with correct text. i.e. Text: " +ExpectedText + 
						"URL: "+ ExpectedURL); 
			}
			else
			{
				System.out.println("Failure !!  Text / link is incorrect. Expected is : " +ExpectedText +"\n" +
							"Actual is : " +ActualText + "Expected URL : "
							+ ExpectedURL +"Actual URL:" + ActualURL);
				Reporter.log("Failure !!  Text / link is incorrect. Expected is : " +ExpectedText +"\n" +
						"Actual is : " +ActualText + "Expected URL : "
						+ ExpectedURL +"Actual URL:" + ActualURL); 
				
				AssertFailedCount++;
			}
			
			
			//Press Releases URL
			ActualText = homepage.PressRelease.getText().trim();
			ExpectedText = "Press Releases";
			
			ActualURL = homepage.PressRelease.getAttribute("href");
			ExpectedURL = "http://kalkomey.com/about/press-resources/releases";
			if(ActualText.equals(ExpectedText) && ActualURL.equals(ExpectedURL))
			{
				System.out.println("Success !! Link for Press Releases is present with correct text. i.e. Text: " +ExpectedText + 
						"URL: "+ ExpectedURL);
				Reporter.log("Success !! Link for Press Releases is present with correct text. i.e. Text: " +ExpectedText + 
						"URL: "+ ExpectedURL); 
			}
			else
			{
				System.out.println("Failure !!  Text / link is incorrect. Expected is : " +ExpectedText +"\n" +
										"Actual is : " +ActualText + "Expected URL : "
										+ ExpectedURL +"Actual URL:" + ActualURL);
				Reporter.log("Failure !!  Text / link is incorrect. Expected is : " +ExpectedText +"\n" +
						"Actual is : " +ActualText + "Expected URL : "
						+ ExpectedURL +"Actual URL:" + ActualURL); 
				
				AssertFailedCount++;
			}
			

			//Privacy and Terms of Use URL
			ActualText = homepage.Privacy.getText().trim();
			ExpectedText = "Privacy and Terms of Use.";
			
			ActualURL = homepage.Privacy.getAttribute("href");
			ExpectedURL = "https://citizen-portal.staging.kalkomey.com/terms";
			if(ActualText.equals(ExpectedText) && ActualURL.equals(ExpectedURL))
			{
				System.out.println("Success !! Link for Privacy and Terms of Use is present with correct text. i.e. Text: " +ExpectedText + 
						"URL: "+ ExpectedURL);
				Reporter.log("Success !! Link for Privacy and Terms of Use is present with correct text. i.e. Text: " +ExpectedText + 
						"URL: "+ ExpectedURL); 
			}
			else
			{
				System.out.println("Failure !!  Text / link is incorrect. Expected is : " +ExpectedText +"\n" +
										"Actual is : " +ActualText + "Expected URL : "
										+ ExpectedURL +"Actual URL:" + ActualURL);
				Reporter.log("Failure !!  Text / link is incorrect. Expected is : " +ExpectedText +"\n" +
						"Actual is : " +ActualText + "Expected URL : "
						+ ExpectedURL +"Actual URL:" + ActualURL); 
				
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : Verify presence of sister project links");
		Reporter.log("Step 4 : Verify presence of sister project links"); 
		
			
			ExpectedText = "boat-ed.comhunter-ed.combowhunter-ed.comoffroad-ed.comsnowmobile-ed.com" + 
						"http://www.boat-ed.com/http://www.hunter-ed.com/http://www.bowhunter-ed.com/http://www.offroad-ed.com/http://www.snowmobile-ed.com/";
			ActualText = homepage.GetAllSisterLinksName() + homepage.GetAllSisterLinks();
		

			if(ActualText.contains(ExpectedText) )
			{
					
				System.out.println("Success!! footer has correct sister sites links i.e. " + ExpectedText);
				Reporter.log("Success!! footer has correct sister sites links i.e. " + ExpectedText); 
				
			}
			else
			{
				
				System.out.println("Failure !! footer has incorrect sister sites links  " + "\n" + "Expected Text: "  
							+ "\n" + ExpectedText + "\n" + 
							 "Actual Text : " + "\n" +  ActualText);
				Reporter.log("Failure !! footer has incorrect sites sites links " + "\n" + "Expected Text: "  
						+ "\n" + ExpectedText + "\n" + 
						"Actual Text : " + "\n" +  ActualText);
			
				AssertFailedCount++;
				
			}

			//Verify Social links
			
			//FB
			ExpectedText = "http://www.facebook.com/Kalkomey";
			ActualText = homepage.FB.getAttribute("href");
	
			if(ActualText.contains(ExpectedText) )
			{
				
				System.out.println("Success!! FB icon has correct link i.e. " + ExpectedText);
				Reporter.log("Success!! FB icon has correct link i.e. " + ExpectedText); 
			
			}
			else
			{
			
				System.out.println("Failure !! footer has incorrect link for FB" + "\n" + "Expected Link: "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual Text : " + "\n" +  ActualText);
				Reporter.log("Failure !! footer has incorrect link for FB" + "\n" + "Expected Link: "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual Text : " + "\n" +  ActualText);
		
				AssertFailedCount++;
			
			}
			
			
			//You Tube
			ExpectedText = "http://www.youtube.com/kalkomey";
			ActualText = homepage.YouTube.getAttribute("href");
	
			if(ActualText.contains(ExpectedText) )
			{
				
				System.out.println("Success!! YouTube icon has correct link i.e. " + ExpectedText);
				Reporter.log("Success!! YouTube icon has correct link i.e. " + ExpectedText); 
			
			}
			else
			{
			
				System.out.println("Failure !! footer has incorrect link for YouTube" + "\n" + "Expected Link: "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual Text : " + "\n" +  ActualText);
				Reporter.log("Failure !! footer has incorrect link for YouTube" + "\n" + "Expected Link: "  
						+ "\n" + ExpectedText + "\n" + 
						 "Actual Text : " + "\n" +  ActualText);
		
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
	 * Verify that continue is disabled unless state selected
	*/ 
	@Test
	private void VerifyContinueButtonState() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify that continue is disabled unless state selected"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify that continue is disabled unless state selected"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		
		System.out.println("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		Reporter.log("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			
			
		System.out.println("Step 2 : Verifying Continue button is disabled");
		Reporter.log("Step 2 : Verifying Continue button is disabled"); 
			
			boolean actualValue = homepage.Continue.isEnabled();
			if (!actualValue)
			{
				System.out.println("Success !! Continue button is disabled");
				Reporter.log("Success !! Continue button is disabled"); 
			}
			else
			{
				System.out.println("Failure !!  Continue button is enabled");
				Reporter.log("Failure !!  Continue button is enabled"); 
				
				AssertFailedCount++;
			}
			
			
			//Select state and course
			homepage.SelectState("Alaska");
			Thread.sleep(2000);
			homepage.SelectCollection("Boat Ed");
			
			//Verify button is enabled
			actualValue = homepage.Continue.isEnabled();
			if (actualValue)
			{
				System.out.println("Success !! Continue button is enabled");
				Reporter.log("Success !! Continue button is enabled"); 
			}
			else
			{
				System.out.println("Failure !!  Continue button is disabled");
				Reporter.log("Failure !!  Continue button is disabled"); 
				
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
	 * Verify that until user selects state course can't be chosen
	*/ 
	@Test
	private void VerifyCourseFieldState() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify that until user selects state course can't be chosen"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify that until user selects state course can't be chosen"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		
		System.out.println("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		Reporter.log("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			
			
		System.out.println("Step 2 : Verifying Course field is disabled");
		Reporter.log("Step 2 : Verifying Continue button is disabled"); 
			
			boolean actualValue = homepage.Collection.isEnabled();
			if (!actualValue)
			{
				System.out.println("Success !! Course field is disabled");
				Reporter.log("Success !! Course field is disabled"); 
			}
			else
			{
				System.out.println("Failure !! Course field is enabled");
				Reporter.log("Failure !! Course field is enabled"); 
				
				AssertFailedCount++;
			}
			
			
			//Select state and course
			homepage.SelectState("Alaska");
			Thread.sleep(2000);
			
			
			//Verify button is enabled
			actualValue = homepage.Collection.isEnabled();
			if (actualValue)
			{
				System.out.println("Success !! Course field is enabled");
				Reporter.log("Success !! Course field is enabled"); 
			}
			else
			{
				System.out.println("Failure !! Course field is disabled");
				Reporter.log("Failure !! Course field is disabled"); 
				
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
	 * Verify user can select sate and related course
	*/ 
	@Test
	private void VerifyStateCourseSelection() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verify user can select sate and related course"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verify user can select sate and related course"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		
		System.out.println("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		Reporter.log("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			
			
		System.out.println("Step 2 : Verifying Continue button is disabled");
		Reporter.log("Step 2 : Verifying Continue button is disabled"); 
			
			boolean actualValue = homepage.Continue.isEnabled();
			if (!actualValue)
			{
				System.out.println("Success !! Continue button is disabled");
				Reporter.log("Success !! Continue button is disabled"); 
			}
			else
			{
				System.out.println("Failure !!  Continue button is enabled");
				Reporter.log("Failure !!  Continue button is enabled"); 
				
				AssertFailedCount++;
			}
			
			
			//Select state and course
			homepage.SelectState("Alaska");
			
		System.out.println("Step 3 : Verify state is selected");
		Reporter.log("Step 3 : Verify state is selected");
									
			String ExpectedText = "Alaska";
			String ActualText = SeleniumFunc.GetSelectedValueFromDropdownList("css", "#searchState");		

			if(ActualText.equals(ExpectedText))
			{
					
				System.out.println("Success !! Correct state is selected. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct state is selected. i.e. " +ExpectedText);
					
			}
			else
			{
					
				System.out.println("Failure !! State is NOT selected." + "\n" + "Expected : "  
									 + ExpectedText + "\n" + "Actual :" + ActualText);
				Reporter.log("Failure !! State is NOT selected." + "\n" + "Expected : "  
						 + ExpectedText + "\n" + "Actual :" + ActualText);
							
				AssertFailedCount++;
					
			}	
			
			
			
			homepage.SelectCollection("Boat Ed");
			
			
		System.out.println("Step 4 : Verify course is selected");
		Reporter.log("Step 4 : Verify course is selected");
										
			ExpectedText = "Boat Ed";
			ActualText = SeleniumFunc.GetSelectedValueFromDropdownList("css", "#searchCollection");		

			if(ActualText.equals(ExpectedText))
			{
						
				System.out.println("Success !! Correct course is selected. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct course is selected. i.e. " +ExpectedText);
						
			}
			else
			{
						
				System.out.println("Failure !! Course is NOT selected." + "\n" + "Expected : "  
									 + ExpectedText + "\n" + "Actual :" + ActualText);
				Reporter.log("Failure !! Course is NOT selected." + "\n" + "Expected : "  
						 + ExpectedText + "\n" + "Actual :" + ActualText);
								
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
	 * Verify that user courses are displayed as per state
	 */ 
	@Test
	private void VerifyStateCourseCombination() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verify that user courses are displayed as per state"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Verify that user courses are displayed as per state"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		
		System.out.println("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		Reporter.log("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			
			
		System.out.println("Step 2 : Verifying Continue button is disabled");
		Reporter.log("Step 2 : Verifying Continue button is disabled"); 
			
			boolean actualValue = homepage.Continue.isEnabled();
			if (!actualValue)
			{
				System.out.println("Success !! Continue button is disabled");
				Reporter.log("Success !! Continue button is disabled"); 
			}
			else
			{
				System.out.println("Failure !!  Continue button is enabled");
				Reporter.log("Failure !!  Continue button is enabled"); 
				
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 3 : Select course as Alaska and Verify courses are displayed as per state selection.");
		Reporter.log("Step 3 : Select course as Alaska and Verify courses are displayed as per state selection.");
									
			//Select state
			homepage.SelectState("Alaska");
		
			//Verify Drop-down options for courses
			String[] AlaskaCourses = {"Select a Course","Boat Ed"};

			SeleniumFunc.VerifyDropDownOptions("id", "searchCollection", AlaskaCourses);			
	
			
		System.out.println("Step 4 : Select course as Pennsylvania and Verify courses are displayed as per state selection.");
		Reporter.log("Step 4 : Select course as Pennsylvania and Verify courses are displayed as per state selection.");
										
			//Select state
			homepage.SelectState("Georgia");
			
			//Verify Drop-down options for courses
			String[] GECourses = {"Select a Course","Boat Ed"};

			SeleniumFunc.VerifyDropDownOptions("id", "searchCollection", GECourses);			
					
			
			
			
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
