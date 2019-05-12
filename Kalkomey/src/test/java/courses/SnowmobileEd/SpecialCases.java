package courses.SnowmobileEd;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
	 * Verify that url is https not http
	 * */ 
	@Test
	private void VerifyURL() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify that url is https not http"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify that url is https not http"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		
		System.out.println("Step 1: Navigate to Snowmobile-Ed home page");
		Reporter.log("Step 1: Navigate to Snowmobile-Ed home page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile);
			Thread.sleep(4000);

	
		System.out.println("Step 2 : Verifying that url is https not http" );
		Reporter.log("Step 2 :  Verifying that url is https not http");
		
			String expectedtext = Constants.ApplicationURL_Snowmobile;
			String actualtext = driver.getCurrentUrl();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! url is https i.e. " + actualtext );
				Reporter.log("-- Success !! url is https i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! url is http. Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! url is http . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
	 * Verify I lost my card information is present and the link is working
	 * */ 
	@Test(dataProvider="IlostMyCard",dataProviderClass=utility.TestNG.class)
	private void VerifyILostMyCardSectionLinks(String state) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Verify I lost my card information is present and the link is working"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Verify I lost my card information is present and the link is working"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		
		System.out.println("Step 1: Navigate to Snowmobile-Ed /faq#replace page for State " + state );
		Reporter.log("Step 1: Navigate to Snowmobile-Ed /faq#replace page for State " + state );
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_Snowmobile + state + "/faq#card-replacement");
			
			//SeleniumFunc.ClickOnElement("css", "nav > div > ul > li:nth-child(3) > a");
			Thread.sleep(5000);
			
			
		System.out.println("Step 2 : Verifying presence of 'Replacement of Lost or Damaged Documents' section" );
		Reporter.log("Step 2 : Verifying presence of 'Replacement of Lost or Damaged Documents' section");
		
			String expectedtext = "Replacement of Lost or Damaged Documents";
			String actualtext = SeleniumFunc.GetElementText("css", "#card-replacement>h2").trim();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! 'Replacement of Lost or Damaged Documents' section is present i.e. " + actualtext );
				Reporter.log("-- Success !! 'Replacement of Lost or Damaged Documents' section is present i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! 'Replacement of Lost or Damaged Documents' section is NOT present. Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! 'Replacement of Lost or Damaged Documents' section is NOT present . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
		
			
		System.out.println("Step 3 : Verifying presence of 'https://ilostmycard.com/.' link" );
		Reporter.log("Step 3 : Verifying presence of 'https://ilostmycard.com/.' link");
		
			expectedtext = "://ilostmycard.com/";
			actualtext = SeleniumFunc.GetElementText("css", "#card-replacement div:nth-of-type(2) a").trim();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! 'https://ilostmycard.com/.' link is present i.e. " + actualtext );
				Reporter.log("-- Success !! 'https://ilostmycard.com/.' link is present i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! 'https://ilostmycard.com/.' linkis NOT present. Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! 'https://ilostmycard.com/.' link is NOT present . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			//Clicking on 'https://ilostmycard.com/.' link 
			SeleniumFunc.ClickOnElement("css", "#card-replacement div:nth-of-type(2) a");
			Thread.sleep(5000);
			
			for (String winHandle : driver.getWindowHandles()) 
			{
			    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			
			expectedtext = "https://ilostmycard.com/";
			actualtext = driver.getCurrentUrl();
			driver.close();
			driver.quit();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! 'https://ilostmycard.com/.' link is working as expected i.e. " + actualtext );
				Reporter.log("-- Success !! 'https://ilostmycard.com/.' link is working as expected i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! 'https://ilostmycard.com/.' link is NOT working. Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! 'https://ilostmycard.com/.' link is NOT working. Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
