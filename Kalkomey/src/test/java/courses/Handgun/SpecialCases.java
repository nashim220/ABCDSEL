package courses.Handgun;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.PageHeader;

import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class SpecialCases 
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
	 * */ 
	@Test
	private void VerifyILostMyCardSectionLinks() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify user can navigate to I Lost My Card Section"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify user can navigate to I Lost My Card Section"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		
		System.out.println("Step 1: Navigating course page");
		Reporter.log("Step 1: Navigating course page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Handgun_national );
			
	
		System.out.println("Step 2 : Verifying whether 'Lost Card' link is present on top header");
		Reporter.log("Step 2 : Verifying whether 'Lost Card' link is present on top header");
		
			try
			{
				String actualtext = header.LostYourCard_New.getText();
				String expectedtext = "Lost Your Card?";
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! 'Lost Your Card?' link is present on page header");
					Reporter.log("-- Success !! 'Lost Your Card?' link is present on page header");
					
					System.out.println("Step 3 : Click on 'Lost Your Card?' link");
					Reporter.log("Step 3 : Click on 'Lost Your Card?' link");
						
						header.LostYourCard_New.click();
						Thread.sleep(3000);
						
						actualtext = driver.getCurrentUrl();
						expectedtext = Constants.ApplicationURL_Handgun.substring(5) + "/national/faq/#card-replacement";
						System.out.println(expectedtext);
						
						if(actualtext.contains(expectedtext))
						{
							System.out.println("-- Success !! User is navigated to Lost Your Card page successfully");
							Reporter.log("-- Success !! User is navigated to Lost Your Card page successfully");
							
						}
						else
						{
							System.out.println("-- Failure !! User is NOT navigated to Lost Your Card page and navigated to :" +actualtext+" Expected : "+expectedtext);
							Reporter.log("-- Failure !! User is NOT navigated to Lost Your Card page and navigated to :" +actualtext+" Expected : "+expectedtext);
							AssertFailedCount++;
						}
						
				}
				else
				{
					System.out.println("-- Failure !! 'Lost Your Card' link is NOT present on page header");
					Reporter.log("-- Failure !! 'Lost Your Card' link is NOT present on page header");
					AssertFailedCount++;
				}
				
			}
			catch(Exception e)
			{
				System.out.println("--  Failure !! 'Lost Your Card' link is NOT present on page header");
				Reporter.log("--  Failure !! 'Lost Your Card' link is NOT present on page header");
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
	 *  Verify URL is with https protocol
	 * */ 
	@Test
	private void VerifyURL() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify URL is with https protocol"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify URL is with https protocol"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		
		
		System.out.println("Step 1: Navigating course page");
		Reporter.log("Step 1: Navigating course page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Handgun_national );
		
		System.out.println("Step 2: Navigating to registration page");
		Reporter.log("Step 2: Navigating to registration page");
			
			header.Register_New.click();

			Thread.sleep(8000);
				
		System.out.println("Step 3 : Verifying Protocol");
		Reporter.log("Step 3 : Verifying Protocol");
			
			String Actual  = driver.getCurrentUrl();
			String Expected = Constants.ApplicationURL_Handgun +"/register/create/course/601099/";

		if(Actual.contains(Expected))
		{
			System.out.println("Success !! https protocol is used");
			Reporter.log("Success !! https protocol is used"); 
		}
		else
		{
			System.out.println("Failure !!  Incorrect protocol is used" + "\n" + "Expected url with protocol : "  
									+ "\n" + Expected + "\n" + 
									 "Actual protocol : " + "\n" +  Actual);
			Reporter.log("Failure !!  Incorrect protocol is used" + "\n" + "Expected url with protocol: "  
						+ "\n" + Expected + "\n" + 
						 "Actual protocol : " + "\n" + Actual); 
				
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
