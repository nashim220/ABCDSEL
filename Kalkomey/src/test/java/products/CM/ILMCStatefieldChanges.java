package products.CM;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.CreateNewCertificationPage;
import pageFactory.CM.ILMCCertificationPage;
import pageFactory.CM.ILMCHomePage;
import pageFactory.CM.ILMCSearchPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class ILMCStatefieldChanges 
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
	 * State field stays editable
	*/ 
	@Test
	private void State_field_stays_editable() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify State field stays editable"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify State field stays editable"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		ILMCHomePage homepage = new ILMCHomePage(driver);
		ILMCSearchPage searchpage = new ILMCSearchPage(driver);
		ILMCCertificationPage certipage = new ILMCCertificationPage(driver);
		CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
		
        System.out.println("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		Reporter.log("Step 1 : Navigate to ILMC Application : " + Constants.ApplicationURL_ILMC);
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_ILMC);
			
			
		System.out.println("Step 2 : Select state and course, go to search page and verify changed URL.");
		Reporter.log("Step 2 : Select state and course, go to search page and verify changed URL."); 
						
			
			//Select state and course
			homepage.SelectState("Pennsylvania");
			Thread.sleep(2000);
			homepage.SelectCollection("Basic HTE / Hunter Safety");
			homepage.ClickOnContinueButton();
			Thread.sleep(2000);
			
			String 	ActualText = SeleniumFunc.ToGetCurrentPageUrl(); 
			if(ActualText.equals(Constants.ApplicationURL_ILMC+"records?collections=280%2C289%2C288"))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Control is redirected to  i.e. " +Constants.ApplicationURL_ILMC+"records?collections=280%2C289%2C288");
				Reporter.log("Success !! Control is redirected to  i.e. " +Constants.ApplicationURL_ILMC+"/records?collections=280%2C289%2C288"); 
			}
			else
			{
				Thread.sleep(1000);
				System.out.println("Failure !! Control is redirected to  Expected : "+Constants.ApplicationURL_ILMC+"records?collections=280%2C289%2C288" 
						+ "\n" + "Actual : " + ActualText);
				Reporter.log("Failure !! Control is redirected to  Expected : "+Constants.ApplicationURL_ILMC+"records?collections=280%2C289%2C288"
						+ "\n" + "Actual : " + ActualText);
				AssertFailedCount++;
			}
			
			System.out.println("Step 3 : Enter valid record and verify changed URL.");
			Reporter.log("Step 3 : Enter invalid record and verify changed URL."); 
			
			searchpage.EnterFirstName("DONALD");
			searchpage.EnterLastName("SCHERER");
			searchpage.SelectMonth("May");
			searchpage.EnterDay("15");
			searchpage.EnterYear("1975");
			
			searchpage.ClickOnSearchButton();
			Thread.sleep(1000);
		
			ActualText = SeleniumFunc.ToGetCurrentPageUrl(); 
			if(ActualText.contains(Constants.ApplicationURL_ILMC+"records/"))
			{
				Thread.sleep(1000);
				
				
				System.out.println("Success !! Control is redirected to  i.e. " +ActualText);
				Reporter.log("Success !! Control is redirected to  i.e. " +ActualText); 
				
				/*System.out.println("Success !! Control is redirected to  i.e. " +Constants.ApplicationURL_ILMC+"records/TPP292HV");
				Reporter.log("Success !! Control is redirected to  i.e. " +Constants.ApplicationURL_ILMC+"records/TPP292HV"); */
			}
			else
			{
				Thread.sleep(1000);
				System.out.println("Failure !! Control is redirected to  Expected : "+Constants.ApplicationURL_ILMC+"records/TPP292HV" 
						+ "\n" + "Actual : " + ActualText);
				Reporter.log("Failure !! Control is redirected to  Expected : "+Constants.ApplicationURL_ILMC+"/records/TPP292HV"
						+ "\n" + "Actual : " + ActualText);
				AssertFailedCount++;
			}
			
			System.out.println("Step 4 : Scroll to the bottom of the page and click on the blue 'edit your addresses' button. and verify changed URL.");
			Reporter.log("Step 4 : Scroll to the bottom of the page and click on the blue 'edit your addresses' button. and verify changed URL."); 
			
			
			
			//Go to edit address
			certipage.ClickOnEditAddressButton();
			Thread.sleep(3000);
			ActualText = SeleniumFunc.ToGetCurrentPageUrl(); 
			System.out.println(ActualText);
			Thread.sleep(1000);
			if(ActualText.endsWith("/edit"))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Control is redirected to  i.e. "+ActualText);
				Reporter.log("Success !! Control is redirected to  i.e. " +ActualText); 
			}
			else
			{
				Thread.sleep(1000);
				System.out.println("Failure !! Control is redirected to  Expected : "+Constants.ApplicationURL_ILMC+"records/"+"        "+"/edit"
						+ "\n" + "Actual : " + ActualText);
				Reporter.log("Failure !! Control is redirected to  Expected : "+Constants.ApplicationURL_ILMC+"records/"+"        "+"/edit"
						+ "\n" + "Actual : " + ActualText);
				AssertFailedCount++;
			}
			
			System.out.println("Step 5 : In the Mailing Address set of fields, change the street address,click on ocntinue button and verify error message ");
			Reporter.log("Step 5 : In the Mailing Address set of fields, change the street address,click on ocntinue button and verify error message "); 
			
			createnewcerti.EnterStreetAddressM("Testing");
			 Thread.sleep(1000);
				createnewcerti.Continue.sendKeys(Keys.ENTER);
	    	     Thread.sleep(2000);
	    	     String ExpectedText2 = "We had trouble verifying your mailing address. If it is incorrect, please correct it.";
	     		String ActualText2= SeleniumFunc.GetElementText("css", "#mailing_address_check h4:nth-of-type(3)");
	     		Thread.sleep(1000);
	     		if(ActualText2.equals(ExpectedText2))
	     		{
	     			Thread.sleep(1000);	
	     			System.out.println("Success !! Valid error message is displayed. i.e. " +ExpectedText2);
	     			Reporter.log("Success !! Valid error message is displayed i.e. " +ExpectedText2);
	     				
	     		}
	     		else
	     		{
	     			Thread.sleep(1000);
	     			System.out.println("Failure !! Invalid error message is displayed" + ExpectedText2 + "\n"+
	     								"Actual :" +ActualText2);
	     			Reporter.log("Failure !! Invalid error message is displayed" + ExpectedText2 + "\n"+
	     							"Actual :" +ActualText2); 
	     					
	     				AssertFailedCount++;
	     					
	     		}
	     		System.out.println("Step 6 : Click on the green 'Make Corrections' button. and verify user should be navigated back to edit page.");
				Reporter.log("Step 6 : Click on the green 'Make Corrections' button. and verify user should be navigated back to edit page."); 
	     		
				Thread.sleep(1000);
				createnewcerti.ClickOnMakecorrection();
				Thread.sleep(3000);
				if(SeleniumFunc.IsElementPresent(createnewcerti.MAddress1))
	     		{
	     			Thread.sleep(1000);	
	     			System.out.println("Success !! user has navigated back to edit page.");
	     			Reporter.log("Success !! user has navigated back to edit page.");
	     				
	     		}
	     		else
	     		{
	     			Thread.sleep(1000);
	     			System.out.println("Failure !! user is not navigated back to edit page.");
	     			Reporter.log("Failure !!  user is not navigated back to edit page."); 
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
