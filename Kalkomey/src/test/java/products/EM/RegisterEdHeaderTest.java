package products.EM;


import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.EM.ErrorPage;
import pageFactory.EM.RegisterEdHomePage;




import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class RegisterEdHeaderTest 
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
	 * Verify UI of Forgot Password Page
	*/ 
	@Test
	private void HeaderUIElementVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify UI of Register-Ed home page header"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify UI of Register-Ed home page header"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegisterEdHomePage HomePage = new RegisterEdHomePage(driver);
		
		System.out.println("Step 1 : Navigate to Register-Ed home page :" + Constants.APPLICATIONURL_RegisterEd);
		Reporter.log("Step 1 : Navigate to Register-Ed home page: " + Constants.APPLICATIONURL_RegisterEd); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.APPLICATIONURL_RegisterEd);
			Thread.sleep(5000);

			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			String browserName = cap.getBrowserName().toLowerCase();


			if(browserName.equals("internet explorer"))
			try{
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
				Thread.sleep(5000);
			}catch (Exception e) {
			}	

		System.out.println("Step 3 : Verify presence of Update Registration button");
		Reporter.log("Step 3 : Verify presence of Update Registration button");
		
		
			if(SeleniumFunc.IsElementPresent("css", ".btn.btn-primary.login-link"))
			{
				
				System.out.println("Success !! Update Registration button is present");
				Reporter.log("Success !! Update Registration button is present"); 
			
			}
			else
			{
				
				System.out.println("Failure !! Update Registration button is not present");
				Reporter.log("Failure !! Update Registration button is not present"); 
				
				AssertFailedCount++;
				
			}
		
		System.out.println("Step 3 : Verify presence of Cancel Registration button");
		Reporter.log("Step 3 : Verify presence of Cancel Registration button");
			
			
			if(SeleniumFunc.IsElementPresent("css", ".btn.btn-danger.login-link"))
			{
					
				System.out.println("Success !! Cancel Registration button is present");
				Reporter.log("Success !! Cancel Registration button is present"); 
				
			}
			else
			{
					
				System.out.println("Failure !! Cancel Registration button is not present");
				Reporter.log("Failure !! Cancel Registration button is not present"); 
					
				AssertFailedCount++;
					
			}
						
		
		System.out.println("Step 3 : Verify presence of Register-Ed Logo");
		Reporter.log("Step 3 : Verify presence of Register-Ed Logo");
				
				
			if(SeleniumFunc.IsElementPresent("css", ".hero-unit-logo"))
			{
						
				System.out.println("Success !! Register-Ed Logo is present");
				Reporter.log("Success !! Register-Ed Logo is present"); 
					
			}
			else
			{
						
				System.out.println("Failure !! Register-Ed Logo is not present");
				Reporter.log("Failure !! Register-Ed Logo is not present"); 
						
				AssertFailedCount++;
						
			}
						
					
			
		System.out.println("Step 3 : Verify funcionality of Update Registration button");
		Reporter.log("Step 3 : Verify funcionality of Update Registration button");
		
			
			HomePage.ClickOnUpdateRegiButton();
			Thread.sleep(5000);


		System.out.println("Step 3 : Verify header of Update Registration Page");
		Reporter.log("Step 3 : Verify header of Update Registration Page");
		
		
			String ActHeader = SeleniumFunc.GetElementText("css", ".page-header.page-header-dark.page-header-compressed>h1");
			String ExpHeader = "Update or Cancel Registration";
			
			if(ActHeader.equals(ExpHeader))
			{
				
				System.out.println("Success !! Page Header is present with correct text");
				Reporter.log("Success !! Page Header is present with correct text"); 
					
			}
			else
			{
						
				System.out.println("Failure !! Incorrect Header");
				Reporter.log("Failure !! Incorrect Header"); 
						
				AssertFailedCount++;
						
			}
			
		
		System.out.println("Step 3 : Verify header of Cancel Registration Page");
		Reporter.log("Step 3 : Verify header of Cancel Registration Page");
			
			
			String ActlHeader = SeleniumFunc.GetElementText("css", ".page-header.page-header-dark.page-header-compressed>h1");
			String ExpdHeader = "Update or Cancel Registration";
				
			if(ActlHeader.equals(ExpdHeader))
			{
					
				System.out.println("Success !! Page Header is present with correct text");
				Reporter.log("Success !! Page Header is present with correct text"); 
						
			}
			else
			{
				
				System.out.println("Failure !! Incorrect Header");
				Reporter.log("Failure !! Incorrect Header"); 
							
				AssertFailedCount++;
							
			}
				
		
			System.out.println("Step 3 : Verify message on Update / Cancel Registration page");
			Reporter.log("Step 3 : Verify message on Update / Cancel Registration page");
				
				
				String ActlMessage = SeleniumFunc.GetElementText("css", ".l-shadowbox.l-contentbox.mbm>p");
				String ExpdMessage = "Provide the e-mail address used during registration. We’ll send a confirmation e-mail to verify your address. " +
									 "From there, you will be able to cancel or make other changes.";
					
				if(ActlMessage.equals(ExpdMessage))
				{
						
					System.out.println("Success !! Proper message is present on Update / Cancel Registration page");
					Reporter.log("Success !! Proper message is present on Update / Cancel Registration page"); 
							
				}
				else
				{
					
					System.out.println("Failure !! Incorrect message on Update / Cancel Registration page");
					Reporter.log("Failure !! Incorrect message on Update / Cancel Registration page"); 
								
					AssertFailedCount++;
								 
				}
				
				
/*				if(SeleniumFunc.IsElementPresent(errorpage.Error))
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
	
	
}
