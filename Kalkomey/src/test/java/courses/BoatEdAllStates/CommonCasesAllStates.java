package courses.BoatEdAllStates;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.HomePage;
import pageFactory.Courses.PageFooter;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.RegistrationPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class CommonCasesAllStates 
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
	


	/* Test 8: 
	 * Footer - Verify page footer elements (before and after login)
	 * */ 
	@Test(dataProvider="RegistrationStates",dataProviderClass=utility.TestNG.class)
	private void Footer_UIElementVarification(String State, String Title) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Footer - Verify page footer elements (before and after login)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Footer - Verify page footer elements (before and after login)"  + "\n" +
				 	  "====");	
		
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		PageFooter footer = new PageFooter(driver);
		PageHeader header = new PageHeader(driver);
		
		System.out.println("Step 1: Navigate to application state specific page");
		Reporter.log("Step 1: Navigate to application state specific page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			
			//Verifying Footer UI elements
				
				String expectedtext = "Boat Ed is produced by Kalkomey Enterprises, Inc. Kalkomey is a private organization dedicated to providing print and Internet boating education courses for North American boaters in the United States and Canada.";
				String actualtext = footer.AboutBoatEdText.getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!!  'About Boat Ed' section has correct text i.e. " + actualtext);
					Reporter.log("Success!! 'About Boat Ed' section has correct text " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! 'About Boat Ed' section has incorrect text  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! 'About Boat Ed' section has incorrect text " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying presence of 'Follow us here' section
				
				expectedtext = "Follow Us";
				actualtext = footer.FollowUs.getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! footer has 'Follow us here' section i.e. " + actualtext);
					Reporter.log("Success!! footer has 'Follow us here' section i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !!  footer doesn't have 'Follow us here' section  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! footer doesn't have 'Follow us here' section " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying presence of 'Sister links' section
				
				expectedtext = "More Online Recreational Safety Courses from Kalkomey";
				actualtext = footer.SisterCoursesSectionHeader.getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! footer has 'Sister links' section i.e. " + actualtext);
					Reporter.log("Success!! footer has 'Sister links' section i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !!  footer doesn't have 'Sister links' section  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! footer doesn't have 'Sister links' section " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				//Verifying presence of 'About Kalkomey Enterprises, Inc.' section
				
				expectedtext = "About Kalkomey Enterprises, Inc.";
				actualtext = footer.AboutKalKomeySectionHeader.getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! footer has 'About Kalkomey Enterprises, Inc.' section i.e. " + actualtext);
					Reporter.log("Success!! footer has 'About Kalkomey Enterprises, Inc.' section i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !!  footer doesn't have 'About Kalkomey Enterprises, Inc.' section  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! footer doesn't have 'About Kalkomey Enterprises, Inc.' section " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				
				//Verifying presence of 'Customer Support' section
				
				expectedtext = "Customer Support";
				actualtext = footer.CustomerSupportSectionHeader.getText();
				
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! footer has 'Customer Support' section i.e. " + actualtext);
					Reporter.log("Success!! footer has 'Customer Support' section i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !!  footer doesn't have 'Customer Support' section  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! footer doesn't have 'Customer Support' section " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
		
		System.out.println("Step 2: Signing up as new user ");
		Reporter.log("Step 2: Signing up as new user "); 	
		
			String username= "dashboard" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "dashboard" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			header.Register_New.click();
			
			//For few States , closing modal dialog present on Registration page
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
					register.ClickOnStateResidencyCheckbox();		
			}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			
			Thread.sleep(10000);
			
				//Verifying Fotter UI elements
			
				//Verifying Social links
				expectedtext = "TwitterFacebookPinterestYouTubeGoogle +InstagramBoat Ed blog" + 
								"https://twitter.com/boat_edhttps://www.facebook.com/BoatEdcomhttps://pinterest.com/boatedhttps://youtube.com/kalkomeyhttps://plus.google.com/115452476737378303703?prsrc=3https://instagram.com/boatedcom?ref=badgehttp://www.boat-ed.com/blog/";
				actualtext = footer.GetAllSocialLinksName() + footer.GetAllSocialLinks();
				//System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! footer has correct Social links i.e. " + actualtext);
					Reporter.log("Success!! footer has correct Social links i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! footer has incorrect Social links  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! footer has incorrect Social links " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				
				//Verifying sister links
				expectedtext = "boat-ed.comhunter-ed.combowhunter-ed.comoffroad-ed.comsnowmobile-ed.comhandgunsafetycourse.com" + 
								"https://www.boat-ed.com/https://www.hunter-ed.com/https://www.bowhunter-ed.com/https://www.offroad-ed.com/https://www.snowmobile-ed.com/https://www.handgunsafetycourse.com/";
				actualtext = footer.GetAllSisterLinksName() + footer.GetAllSisterLinks();
				//System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! footer has correct sister sites links i.e. " + actualtext);
					Reporter.log("Success!! footer has correct sister sites links i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! footer has incorrect sister sites links  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! footer has incorrect sites sites links " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
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

	
	/* Test 10: 
	 * State Home > To verify 'Send us an email' feature
	 * */ 
	@Test(dataProvider="RegistrationStates",dataProviderClass=utility.TestNG.class)
	private void State_SendUsAnEmail(String State, String Title) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : State Home > To verify 'Send us an email' feature"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10 : State Home > To verify 'Send us an email' feature"  + "\n" +
				 	  "====");	
		
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HomePage home = new HomePage(driver);
		
		System.out.println("Step 1: Navigate to application state specific page and click on 'Send Us An E-mail' link from footer");
		Reporter.log("Step 1: Navigate to application state specific page and click on 'Send Us An E-mail' link from footer"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			
			Thread.sleep(2000);
			
			home.SendUsAnEmail.click();
			Thread.sleep(4000);
			
			if(home.SendUsAnEmailPopUp.getAttribute("style").contains("display: block"))
			{
				System.out.println("Success!! 'Send Us An Email' pop up displayed");
				Reporter.log("Success!! 'Send Us An Email' pop up displayed"); 
				
				System.out.println("Step 2: On 'Send Us An E-mail' , entering valid data and click on Submit button");
				Reporter.log("Step 2: On 'Send Us An E-mail' , entering valid data and click on Submit button");
				
					home.SendUsAnEmailPopUp_Name.sendKeys("test");
					home.SendUsAnEmailPopUp_Email.sendKeys("test@test.com");
					home.SendUsAnEmailPopUp_Reason_select("Other");
					home.SendUsAnEmailPopUp_Subject.sendKeys("testsubject");
					home.SendUsAnEmailPopUp_Message.sendKeys("testmessage");
					home.SendUsAnEmailPopUp_Submit.click();
					Thread.sleep(10000);
								
					String expectedtext ="test, thanks for contacting us. Your message has been sent to Customer Service and you should hear back from them shortly.";
					String actualtext = home.SendUsAnEmailPopUp_ConfirmationMessage_New.getText().trim() ;
		
					if(actualtext.contains(expectedtext) )
					{
						System.out.println("Success!! Confirmation message is correct");
						Reporter.log("Success!! Confirmation message is correct"); 
					}
					else
					{
						System.out.println("Failure !! Confirmation message is incorrect" + "\n" + "Expected Text: "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual Text : " + "\n" +  actualtext);
						Reporter.log("Failure !! Confirmation message is incorrect" + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
			}
			else
			{
				System.out.println("Failure!! 'Send Us An Email' pop up NOT displayed");
				Reporter.log("Failure!! 'Send Us An Email' pop up NOT displayed"); 
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

	
	/* Test 12: 
	 * State > Verify FAQ Page
	 * */ 
	@Test(dataProvider="RegistrationStates",dataProviderClass=utility.TestNG.class)
	private void State_FAQ(String State, String Title) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 12 : State > Verify FAQ Page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 12: State > Verify FAQ Page"  + "\n" +
				 	  "====");	
		
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		
		
		System.out.println("Step 1: Navigate to application state specific page and click on 'FAQ' link from header");
		Reporter.log("Step 1: Navigate to application state specific page and click on 'FAQ' link from header"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			header.Before_FAQs_New.click();
			Thread.sleep(3000);
			
			if(driver.getCurrentUrl().contains(Constants.ApplicationURL + "/" + State + "/faq/"))
			{
				System.out.println("Success!! User is navigated to FAQ page");
				Reporter.log("Success!! User is navigated to FAQ page"); 
									
					String expectedtext = "Frequently Asked Questions";
					String actualtext = driver.findElement(By.cssSelector("div.page-header h1")).getText().trim() ;
		
					if(actualtext.contains(expectedtext) )
					{
						System.out.println("Success!! FAQ page has correct title");
						Reporter.log("Success!! FAQ page has correct title"); 
					}
					else
					{
						System.out.println("Failure !! FAQ page has incorrect title" + "\n" + "Expected Text: "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual Text : " + "\n" +  actualtext);
						Reporter.log("Failure !! FAQ page has incorrect title" + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
			}
			else
			{
				System.out.println("Failure!!  User is NOT navigated to FAQ page");
				Reporter.log("Failure!!  User is NOT navigated to FAQ page"); 
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

	
	/* Test 13: 
	 * State - Home  - Verify UI elements on home page e.g. qa1.boat-ed.com/alabama
	 * */ 
	
	@Test(dataProvider="RegistrationStates",dataProviderClass=utility.TestNG.class)
	private void State_Home_UIElementVerification(String State, String Title, String Header, String Subheader) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 13 : State - Home  - Verify UI elements on home page e.g. qa1.boat-ed.com/alabama"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 13 : State - Home  - Verify UI elements on home page e.g. qa1.boat-ed.com/alabama"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HomePage home = new HomePage(driver);
		
		System.out.println("Step 1: Navigated to state home page and verifying UI elements");
		Reporter.log("Step 1: Navigated to statehome page and verifying UI elements"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			
			//Verifying Header 
				
				//verifying application name
				String expectedtext = Title;
				String actualtext = home.Header_ApplicationName_New.getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! Header has correct application name i.e. " + actualtext);
					Reporter.log("Success!! Header has correct application name i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! Header has incorrect application name " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Header has incorrect application name " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
				
				
			//Verifying Billboard section 
				
				//verifying Billboard text
				expectedtext = Header + "\n" + Subheader ;
				actualtext = home.State_Billboard_text1_New.getText().trim() + "\n " + home.State_Billboard_text2_New.getText().trim();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! Billboard has correct text i.e. " + actualtext);
					Reporter.log("Success!! Billboard has correct text  i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! Billboard has incorrect text  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! Billboard has incorrect text e " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}
				
					
			//verifying presence of '1-2-3 steps for completeion of course' section
				
				expectedtext = "Get Boating certified in 3 steps." ;
				actualtext = home.State_CompletionSteps_Section_Boat.getText().trim();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! '1-2-3 steps for completeion of course' section has correct text i.e. " + actualtext);
					Reporter.log("Success!!'1-2-3 steps for completeion of course' section has correct text  i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !! '1-2-3 steps for completeion of course' section has incorrect text  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! '1-2-3 steps for completeion of course' section has incorrect text " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
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
