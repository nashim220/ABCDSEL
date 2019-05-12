package courses.BoatEd;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.LoginPage;
import pageFactory.Courses.CertificationInformationPage;
import pageFactory.Courses.ContentsPage;
import pageFactory.Courses.CourseCompletePage;
import pageFactory.Courses.FinalExamPage;
import pageFactory.Courses.HomePage;
import pageFactory.Courses.KECourseAdminPage;
import pageFactory.Courses.PageFooter;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.PaymentInformationPage;
import pageFactory.Courses.RegistrationPage;
import pageFactory.Courses.ViewReceiptPage;
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
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		keadmin.GoToAdminPage(browser);
		
	}

	@AfterMethod
	public void teardown() throws Exception
	{
		b.tearDown();
	}
	

	/* Test 1: 
	 * Boat Oregon Course > Verify details for "How do I get a replacement card?" on /oregon/boating_card.html#replace
	 * */ 
	@Test
	private void OR_VerifyReplacementCardSection() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Boat Oregon Course > Verify details for 'How do I get a replacement card?' on /oregon/boating_card.html#replace"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Boat Oregon Course > Verify details for 'How do I get a replacement card?' on /oregon/boating_card.html#replace"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);

		String url = "/oregon/faq/#card-replacement";
		
		System.out.println("Step 1: Navigating to /oregon/boating_card.html#replace and verifying details");
		Reporter.log("Step 1: Navigating to /oregon/boating_card.html#replace and verifying details"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + url);
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + url);
				Thread.sleep(4000);
			}
			//Verifying "How do I get a replacement card?" section title 
				
			String expectedtext = "Replacement of Lost or Damaged Documents";
			String actualtext = SeleniumFunc.GetElementText("css","#card-replacement>h2");
			System.out.println(actualtext);

			if(actualtext.contains(expectedtext) )
			{
				System.out.println("Success!! Header has correct for 'How do I get a replacement card?' i.e. " + actualtext);
				Reporter.log("Success!! Header has correct for 'How do I get a replacement card?'  i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Header has incorrect for 'How do I get a replacement card?' " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Header has incorrect for 'How do I get a replacement card?' " + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
				
			
			//Verifying Text 
			
			expectedtext = "How do I replace my online course completion document if I’ve lost it or it has been damaged?" +"\n"
					+ "To replace a lost or destroyed proof of course completion document, log in to the course and print a new one." +"\n"
					+"How do I replace my Boater Education Card if I’ve lost it or it has been damaged?"+"\n" 
					+"The Oregon State Marine Board has updated its database system to include an online store, "
					+ "capable of accepting requests for replacement Boating Education Cards. You will be able to register, "
					+ "start a Boater Education Card Replacement Application, update your information, and pay the $8 "
					+ "replacement fee for the new card with a VISA, MasterCard, or Discover card."+"\n"
					+"You will also be able to print a temporary permit, valid for 60 days, so that you may continue "
					+ "to boat while waiting for your new card to arrive. Please visit the Oregon State Marine Board "
					+ "site or download this PDF for more information.";
						
			actualtext = SeleniumFunc.GetElementText("css","#card-replacement");
			System.out.println(actualtext);

			if(actualtext.contains(expectedtext) )
			{
				System.out.println("Success!! Text is correct for 'How do I get a replacement card?' i.e. " + actualtext);
				Reporter.log("Success!! Text is correct for 'How do I get a replacement card?'  i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Text is incorrect for 'How do I get a replacement card?' " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Text is incorrect for 'How do I get a replacement card?' " + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			
			//Clicking on PDF link and verifying navigation 
			
			SeleniumFunc.ClickOnElement("css", "#card-replacement p:nth-of-type(2) a:nth-of-type(2)");
			expectedtext = "https://www.boat-ed.com/orb/or_specific_images/pdfs/Online_Replacement_BEC_App_Process.pdf";
			Thread.sleep(5000);
			
			for (String winHandle : driver.getWindowHandles()) 
			{
			    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			
			actualtext = driver.getCurrentUrl();
			System.out.println(actualtext);

			if(actualtext.contains(expectedtext) )
			{
				System.out.println("Success!! User is navigated to correct page while clicking on PDF link i.e. " + actualtext);
				Reporter.log("Success!! User is navigated to correct page while clicking on PDF link i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! User is navigated to incorrect page while clicking on PDF link " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! User is navigated to incorrect page while clicking on PDF link" + "\n" + "Expected Text: "  
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
	
	
	/* Test 2: 
	 * Boat Rhode Island Course > Verify details on /rhodeisland/ page
	 * */ 
	@Test
	private void RI_VerifyDetailsOnPage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Boat Rhode Island Course > Verify details on /rhodeisland/ page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Boat Rhode Island Course > Verify details on /rhodeisland/ page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);
		
		String url = "/rhodeisland/";
		
		System.out.println("Step 1: Navigating to /rhodeisland/ and verifying details");
		Reporter.log("Step 1: Navigating to /rhodeisland/ and verifying details"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + url);
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + url);
				Thread.sleep(4000);
			}
			
			//Verifying whether 'Register', 'Login' button are present or not 
				
			try
			{	
					header.Register_New.click();
					Thread.sleep(8000);

					System.out.println("Failure !! Register button is present ");
					Reporter.log("Failure !! Register button is present ");
					
					AssertFailedCount++;
				
			}
			catch(Exception e)
			{
				System.out.println("Success !! Register button is NOT present ");
				Reporter.log("Success !! Register button is NOT present ");
			}	
			try
			{	
					header.Login.click();
					Thread.sleep(8000);

					System.out.println("Failure !! Login button is present ");
					Reporter.log("Failure !! Login button is present ");
					
					AssertFailedCount++;
				
			}
			catch(Exception e)
			{
				System.out.println("Success !! Login button is NOT present ");
				Reporter.log("Success !! Login button is NOT present ");
			}
				
			
		System.out.println("Step 2: Click on 'Start the Course' button ");
		Reporter.log("Step 2: Click on 'Start the Course' button"); 
		
			SeleniumFunc.ClickOnElement("css", "div.sign-up a");
			Thread.sleep(8000);

			//Verifying whether user is navigated to Study Guide page or not
			
			String actualtext = driver.getCurrentUrl();
			System.out.println(actualtext);
			String expectedtext = Constants.ApplicationURL + "/rhodeisland/studyGuide/101041/";

			if(actualtext.contains(expectedtext) )
			{
				System.out.println("Success!! User is navigated to Study Guide page while clicking on 'Start the Course' button i.e. " + actualtext);
				Reporter.log("Success!! User is navigated to Study Guide page while clicking on 'Start the Course' button i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! User is NOT navigated to Study Guide page while clicking on 'Start the Course' button " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! User is NOT navigated to Study Guide page while clicking on 'Start the Course' button" + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			
			//Verifying Study Guide Page header
			
			actualtext = SeleniumFunc.GetElementText("css", "div.page-header h1");
			System.out.println(actualtext);
			expectedtext = "Study Guide for Rhode Island Certificate of Boating Safety Education";

			if(actualtext.contains(expectedtext) )
			{
				System.out.println("Success!! Study Guide Page header is correct i.e. " + actualtext);
				Reporter.log("Success!! Study Guide Page header is correct i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Study Guide Page header is incorrect " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Study Guide Page header is incorrect " + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 3: Click on 'Download the practice test (PDF)' button ");
		Reporter.log("Step 3: Click on 'Download the practice test (PDF)' button ");
		
			SeleniumFunc.ClickOnElement("css", "a[class='btn btn-small btn-success']");
			Thread.sleep(8000);

			expectedtext = Constants.ApplicationURL + "/rib/ri_specific_images/graphics/ri_boat_practice_exam.pdf";
			Thread.sleep(5000);
			
			for (String winHandle : driver.getWindowHandles()) 
			{
			    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			
			actualtext = driver.getCurrentUrl();
			System.out.println(actualtext);

			if(actualtext.contains(expectedtext) )
			{
				System.out.println("Success!! User is navigated to correct page while clicking on PDF link i.e. " + actualtext);
				Reporter.log("Success!! User is navigated to correct page while clicking on PDF link i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! User is navigated to incorrect page while clicking on PDF link " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! User is navigated to incorrect page while clicking on PDF link" + "\n" + "Expected Text: "  
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
	
	
	/* Test 3: 
	 * Boat Canada Course > Verify 'Province' specific links exists and working as expected
	 * */ 
	@Test
	private void Canada_ProvinceLinks() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Boat Canada Course > Verify 'Province' specific links exists and working as expected"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Boat Canada Course > Verify 'Province' specific links exists and working as expected"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);

		String url = "/canada/";
		
		System.out.println("Step 1: Navigating to /canada/ and verifying 'Province' specific links");
		Reporter.log("Step 1: Navigating to /canada/ and verifying 'Province' specific links"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + url);
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + url);
				Thread.sleep(4000);
			}
			//Verifying whether 'Boating in Your Province' section is present or not
			
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0, 500);");
			
			String actualtext = SeleniumFunc.GetElementText("css", "#steps div:nth-of-type(1) nav h2");
			System.out.println(actualtext);
			String expectedtext ="Boating in Your Province";

			if(actualtext.contains(expectedtext) )
			{
				/*JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("scroll(0, 500);");*/
				
				System.out.println("Success!! 'Boating in Your Province' section is present i.e. " + actualtext);
				Reporter.log("Success!! 'Boating in Your Province' section is present i.e. " + actualtext); 
				
				String[] provincenames = {"Alberta", "British Columbia", "Manitoba", "New Brunswick", "Nova Scotia", "Ontario", "Saskatchewan"} ; 
				String[] provinceurls = {"/canada/alberta", "/canada/britishcolumbia", "/canada/manitoba","/canada/newbrunswick", "/canada/novascotia", "/canada/ontario", "/canada/saskatchewan"} ; 
				
				
				for(int i=0; i<provincenames.length ;i++)
				{						Thread.sleep(4000);

					//Verifying Province and it's link
					int j=i+1;
					Thread.sleep(3000);
					String cssselector = ".quick-links.list-inline li:nth-of-type(" + j + ")";
					actualtext = SeleniumFunc.GetElementText("css", cssselector);
					/*System.out.println(actualtext);*/
					expectedtext = provincenames[i];

					if(actualtext.contains(expectedtext) )
					{
						System.out.println("Success!! Province -> " + provincenames[i] + " is present");
						Reporter.log("Success!! Province -> " + provincenames[i] + " is present"); 
					}
					else
					{
						System.out.println("Failure !! Province -> " + provincenames[i] + " is NOT present" + "\n" + "Expected Text: "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual Text : " + "\n" +  actualtext);
						Reporter.log("Failure !! Province -> " + provincenames[i] + " is NOT present" + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
					
					//Clicking on Province link
									
					Thread.sleep(4000);
					
					cssselector = ".quick-links.list-inline li:nth-of-type(" + j + ") a";
					SeleniumFunc.ClickOnElement("css", cssselector);
					//System.out.println(cssselector);
					Thread.sleep(3000);
					 
					actualtext = driver.getCurrentUrl();
					/*System.out.println(actualtext);*/
					expectedtext = Constants.ApplicationURL + provinceurls[i];

					if(actualtext.contains(expectedtext) )
					{
						System.out.println("Success!! Province -> " + provincenames[i] + " has correct URL");
						Reporter.log("Success!! Province -> " + provincenames[i] + " has correct URL"); 
					}
					else
					{
						System.out.println("Failure !! Province -> " + provincenames[i] + " has incorrect URL" + "\n" + "Expected Text: "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual Text : " + "\n" +  actualtext);
						Reporter.log("Failure !! Province -> " + provincenames[i] + " has incorrect URL" + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
					driver.navigate().back();
					Thread.sleep(4000);

				}
				
				
				
			}
			else
			{
				System.out.println("Failure !! 'Boating in Your Province' section is NOT present " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! 'Boating in Your Province' section is NOT present" + "\n" + "Expected Text: "  
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
	
	
	/* Test 4: 
	 * Payment : Verify Info message for CC field (i.e.Unfortunately, we do not accept PayPal, gift cards, or preloaded debit or bank cards as payment at this time.)
	 * */ 
	@Test
	private void Payment_CCField_InfoText() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Payment : Verify Info message for CC field (i.e.Unfortunately, we do not accept PayPal, gift cards, or preloaded debit or bank cards as payment at this time.)"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Payment : Verify Info message for CC field (i.e.Unfortunately, we do not accept PayPal, gift cards, or preloaded debit or bank cards as payment at this time.)"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ContentsPage content = new ContentsPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			
			Thread.sleep(8000);
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			header.Contents.click();
			Thread.sleep(4000);
		
			String ContentPageUrl = driver.getCurrentUrl();
	
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Fast Forward Pass");
			
			//Go back to Contents page
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

			//Click on Continue Exam button
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			//Selecting Answer for Question 61 and clicking on Next button
			
				//Selecting answer
				finalexam.Answer1.click();
				Thread.sleep(4000);

				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
				Thread.sleep(5000);

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			cerinfo.Student_SSN.clear();
			cerinfo.Student_SSN.sendKeys("123-45-6789");
			
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
	
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(5000);
			
			
			expectedurl = Constants.ApplicationURL + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , , verfiying Credit Card info text" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , , verfiying Credit Card info text");
		
			//verifying Credit Card info text
			
			String expectedtext = "Sorry, we do not accept PayPal, gift cards, or prepaid cards.";
			String actualtext = payinfo.CreditCardAlertText.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Credit Card info text is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Credit Card info text is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Credit Card info text is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Credit Card info text is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
	 * Course Complete Page : Verify 'Share on Facebook' button functionality
	 * */ 
	@Test
	private void CourseComplete_ShareOnFacebook() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Course Complete Page : Verify 'Share on Facebook' button functionality"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Course Complete Page : Verify 'Share on Facebook' button functionality"  + "\n" +
				 	  "====");	
		
		
	
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ViewReceiptPage receipt = new ViewReceiptPage(driver);
		CourseCompletePage coursecomplete = new CourseCompletePage(driver);
		ContentsPage content = new ContentsPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			System.out.println(emailaddress);
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			
			Thread.sleep(8000);
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Fast Forward Pass");
			
			//Go back to Contents page
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

			//Click on Continue Exam button
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			//Selecting Answer for Question 61 and clicking on Next button
			
				//Selecting answer
				finalexam.Answer1.click();
				Thread.sleep(4000);

				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
				Thread.sleep(4000);

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			cerinfo.Student_SSN.clear();
			cerinfo.Student_SSN.sendKeys("123-45-6789");
			
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
		
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
		
			
			expectedurl = Constants.ApplicationURL + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button");
		
			payinfo.FirstName.sendKeys("test");
			payinfo.LastName.sendKeys("Patel");
			payinfo.CCNumber.sendKeys("4111111111111111");
			
			new Select(payinfo.ExpirationMonth).selectByVisibleText("03");
			new Select(payinfo.ExpirationYear).selectByVisibleText("2019");
			
			payinfo.BillingContactInfoCheckbox.click();
			payinfo.ConfirmPaymentInformation.click();
			
			Thread.sleep(4000);
			
			payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
			
			Thread.sleep(10000);
			
		
		System.out.println("Step 6 : On 'View Receipt' page, clicking on 'Get Your Proof of Home Study Voucher ' button");
		Reporter.log("Step 6 : On 'View Receipt' page, clicking on 'Get Your Proof of Home Study Voucher ' button"); 
			
			receipt.GetYourProofofHomeStudyVoucher.click();
			Thread.sleep(4000);
			
			//Verifying Url
			String expectedtext = Constants.ApplicationURL + "/course/course_complete/";
			String actualtext = driver.getCurrentUrl();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! user is navigated to Course Compelte page i.e. " + actualtext );
				Reporter.log("-- Success !! user is navigated to Course Compelte page i.e. " + actualtext );
				
				expectedtext = "You Have Completed the Alabama Boat Ed Course";
				actualtext = coursecomplete.PageHeader.getText().trim();
				
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! Course Completion message is correct i.e. " + actualtext );
					Reporter.log("-- Success !! Course Completion message is correct i.e. " + actualtext );
				}
				else
				{
					System.out.println("-- Failure !! Course Completion message is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
					Reporter.log("-- Failure !! Course Completion message is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
					AssertFailedCount++;
				}
				
			}
			else
			{
				System.out.println("-- Failure !! user is NOT navigated to Course Compelte page . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !!user is NOT navigated to Course Compelte page . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 7 : On Course Complete Page , verifying whether 'Share on Facebook' button present or not");
		Reporter.log("Step 7 : On Course Complete Page , verifying whether 'Share on Facebook' button present or not");
		
			expectedtext = "Share on Facebook";
			actualtext = coursecomplete.ShareOnFacebook.getText().trim();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! Share on Facebook' button present i.e. " + actualtext );
				Reporter.log("-- Success !! Share on Facebook' button presenti.e. " + actualtext );
				
				//Clicking on Share on Facebook' button 
				Thread.sleep(4000);
				
				if (SeleniumFunc.IsElementPresent("css", "#sa_close"))
				{
					SeleniumFunc.ClickOnElement("css", "#sa_close");
					Thread.sleep(4000);
				}
				
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("scroll(0, 500);");	
				coursecomplete.ShareOnFacebook.click();
				Thread.sleep(4000);
				//Verifying whether Facebook window is opened and user is asked to login
				for (String winHandle : driver.getWindowHandles()) 
				{
				    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
				}
				
				Thread.sleep(3000);
				expectedtext = "https://www.facebook.com/login.php";
				actualtext = driver.getCurrentUrl();
				
				if(actualtext.contains(expectedtext))
				{
					System.out.println("-- Success !! Facebook login page is opened i.e. " + actualtext );
					Reporter.log("-- Success !! Facebook login page is opened i.e. " + actualtext );
					
					
					expectedtext = "Log in to use your Facebook account with Boat-ed.com.";
					
					if(driver.findElement(By.id("content")).getText().contains(expectedtext))
					{
						System.out.println("-- Success !! User is asked to login to Facebook to use it with Boat-ed.com i.e. " + actualtext );
						Reporter.log("-- Success !! User is asked to login to Facebook to use it with Boat-ed.com  i.e. " + actualtext );

					}
					else
					{
						System.out.println("-- Failure !! User is NOT asked to login to Facebook to use it with Boat-ed.com . Expected is : " + expectedtext + " , Actual is : " + actualtext );
						Reporter.log("-- Failure !! User is NOT asked to login to Facebook to use it with Boat-ed.com  . Expected is : " + expectedtext + " , Actual is : " + actualtext);
						AssertFailedCount++;
					}
					
				}
				else
				{
					System.out.println("-- Failure !! Facebook login page is NOT opened. Expected is : " + expectedtext + " , Actual is : " + actualtext );
					Reporter.log("-- Failure !! Facebook login page is NOT opened . Expected is : " + expectedtext + " , Actual is : " + actualtext);
					AssertFailedCount++;
				}
				
			}
			else
			{
				System.out.println("-- Failure !! Share on Facebook' button NOT present. Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Share on Facebook' button NOT present . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
	 * Boat Canada Course > Verify 'Quick Links' specific links exists and working as expected
	 * */ 
	@Test
	private void Canada_QuickLinks() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Boat Canada Course > Verify 'Quick Links' exists and working as expected"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Boat Canada Course > Verify Quick Links links exists and working as expected"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);

		String url = "/canada/";
		
		System.out.println("Step 1: Navigating to /canada/ and verifying 'Quick Links'");
		Reporter.log("Step 1: Navigating to /canada/ and verifying 'Quick Links'"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + url);
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + url);
				Thread.sleep(4000);
			}
			//Verifying whether 'Quick Links' section is present or not
			
		/*	String actualtext = SeleniumFunc.GetElementText("css", "#steps > div > div.span4 h4:nth-of-type(1)");
			System.out.println(actualtext);
			String expectedtext ="Quick Links";*/

			if(SeleniumFunc.IsElementPresent("css", "div:nth-of-type(2)"))
			{
				System.out.println("Success!! 'Quick Links' section is present ");
				Reporter.log("Success!! 'Quick Links' section is present "); 
				
				String[] provincenames = {"Study Guide", "Transport Canada Approved", "Canada Boater Education Card", "Canada Boating Laws and Regulations","Replacing Lost Cards", "Canada Boating Handbook", "Boating Courses for Other States"} ; 
				String[] provinceurls = {"/canada/studyGuide/101199/" , "/canada/approved_boating_course.html", "/canada/boating_card.html", "/canada/boating_law.html","/canada/faq/#card-replacement","/canada/handbook", "/"} ; 
				
				for(int i=0; i<provincenames.length ;i++)
				{
					
					JavascriptExecutor jse = (JavascriptExecutor)driver;
					jse.executeScript("scroll(0, 400);");
					
					//Verifying Province and it's link
					int j=i+1;
					Thread.sleep(4000);
					String cssselector = "#steps div:nth-of-type(2) nav:nth-of-type(1) li:nth-of-type(" + j + ")";
					String actualtext = SeleniumFunc.GetElementText("css", cssselector);
					/*System.out.println(actualtext);*/
					String expectedtext = provincenames[i];

					if(actualtext.contains(expectedtext) )
					{
						System.out.println("Success!! Quick Links -> " + provincenames[i] + " is present");
						Reporter.log("Success!! Quick Links -> " + provincenames[i] + " is present"); 
					}
					else
					{
						System.out.println("Failure !! Quick Links -> " + provincenames[i] + " is NOT present" + "\n" + "Expected Text: "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual Text : " + "\n" +  actualtext);
						Reporter.log("Failure !! Quick Links -> " + provincenames[i] + " is NOT present" + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
					
					//Clicking on Province link
					cssselector = "#steps div:nth-of-type(2) nav:nth-of-type(1) li:nth-of-type(" + j + ") a";
					SeleniumFunc.ClickOnElement("css", cssselector);
					Thread.sleep(4000);
					 
					actualtext = driver.getCurrentUrl();
					/*System.out.println(actualtext);*/
					expectedtext = Constants.ApplicationURL + provinceurls[i];

					if(actualtext.contains(expectedtext) )
					{
						System.out.println("Success!! Quick Links -> " + provincenames[i] + " has correct URL");
						Reporter.log("Success!! Quick Links -> " + provincenames[i] + " has correct URL"); 
					}
					else
					{
						System.out.println("Failure !! Quick Links -> " + provincenames[i] + " has incorrect URL" + "\n" + "Expected Text: "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual Text : " + "\n" +  actualtext);
						Reporter.log("Failure !! Quick Links -> " + provincenames[i] + " has incorrect URL" + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
					if(!driver.getCurrentUrl().equals(Constants.ApplicationURL + url))
					{
					driver.navigate().back();						
					Thread.sleep(4000);

					}
					
				}
				
				
				
			}
			else
			{
				System.out.println("Failure !! 'Quick Links' section is NOT present ");
				Reporter.log("Failure !! 'Quick Links' section is NOT present ");
				
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
	 * Footer : Verify details under 'Boating Safety Handbooks' & 'About Boat Ed' sections
	 * */ 
	@Test
	private void Footer_VerifyAbout_Handbooks_Sections() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Footer : Verify details under 'Boating Safety Handbooks' & 'About Boat Ed' sections"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Footer : Verify details under 'Boating Safety Handbooks' & 'About Boat Ed' sections"  + "\n" +
				 	  "====");	
		
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageFooter footer = new PageFooter(driver);
		PageHeader header = new PageHeader(driver);

		
		System.out.println("Step 1: Navigate to application page");
		Reporter.log("Step 1: Navigate to application page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL);
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL);
				Thread.sleep(4000);
			}
			
			//Verifying Footer UI elements
			
				/*//Verifying About Boat Ed section
			
				String expectedtext = "About Boat Ed";
				String actualtext = footer.AboutBoatEdHeader.getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! footer has 'About Boat Ed' section i.e. " + actualtext);
					Reporter.log("Success!! footer has 'About Boat Ed' section i.e. " + actualtext); 
				}
				else
				{
					System.out.println("Failure !!  footer doesn't have 'About Boat Ed' section  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! footer doesn't have 'About Boat Ed' section " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				}*/
				
				
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
				
				
				/*//Verifying presence of 'Boating Safety Handbooks' section
				
				expectedtext = "Boating Safety Handbooks";
				actualtext = footer.BoatingSafetyHandbooksHeader.getText();
				System.out.println(actualtext);
	
				if(actualtext.contains(expectedtext) )
				{
					System.out.println("Success!! footer has 'Boating Safety Handbooks' section i.e. " + actualtext);
					Reporter.log("Success!! footer has 'Boating Safety Handbooks' section i.e. " + actualtext); 
					
					
					//Verifying Text under 'Boating Safety Handbooks' section
					expectedtext = "Safety on United States and Canadian waterways is the responsibility of all boaters. Stay up to date with the latest boating laws and get general information about safe vessel operation for your state or Canada. Available in .ePub, .mobi, and PDF formats.";
					actualtext = footer.BoatingSafetyHandbooksText.getText();
					System.out.println(actualtext);
					
					if(actualtext.contains(expectedtext) )
					{
						System.out.println("Success!! 'Boating Safety Handbooks' section has correct text i.e. " + actualtext);
						Reporter.log("Success!! 'Boating Safety Handbooks' section has correct text i.e. " + actualtext); 
				
					}
					else
					{
						System.out.println("Failure !!  'Boating Safety Handbooks' section has incorrect text" + "\n" + "Expected Text: "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual Text : " + "\n" +  actualtext);
						Reporter.log("Failure !! 'Boating Safety Handbooks' section has incorrect text" + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
					
					
					//Verifying presence of 'Buy your boating handbook' button 
					expectedtext = "Buy your boating handbook";
					actualtext = footer.BuyYourBoatingHandbookButton.getText();
					System.out.println(actualtext);
					
					if(actualtext.contains(expectedtext) )
					{
						System.out.println("Success!! 'Buy your boating handbook' button is present i.e. " + actualtext);
						Reporter.log("Success!! 'Buy your boating handbook' button is present i.e. " + actualtext); 
				
					}
					else
					{
						System.out.println("Failure !! 'Buy your boating handbook' button is NOT present " + "\n" + "Expected Text: "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual Text : " + "\n" +  actualtext);
						Reporter.log("Failure !! 'Buy your boating handbook' button is NOT present" + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
					
					// Click on 'Buy your boating handbook' button 
					expectedtext = "http://bookstore.kalkomey.com";
					footer.BuyYourBoatingHandbookButton.click();
					actualtext = driver.getCurrentUrl();
					System.out.println(actualtext);
					
					if(actualtext.contains(expectedtext) )
					{
						System.out.println("Success!! On click to 'Buy your boating handbook' button, user is navigated to correct page i.e. " + actualtext);
						Reporter.log("Success!! On click to 'Buy your boating handbook' button, user is navigated to correct page i.e. " + actualtext); 
				
					}
					else
					{
						System.out.println("Failure !!On click to 'Buy your boating handbook' button, user is navigated to incorrect page " + "\n" + "Expected Text: "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual Text : " + "\n" +  actualtext);
						Reporter.log("Failure !!  On click to 'Buy your boating handbook' button, user is navigated to incorrect page" + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
					driver.navigate().back();
					
					// Verifying price beside 'Buy your boating handbook' button 
					expectedtext = "$1.99";
					actualtext = footer.BuyYourBoatingHandbookPrice.getText();
					System.out.println(actualtext);
					
					if(actualtext.contains(expectedtext) )
					{
						System.out.println("Success!!  price beside 'Buy your boating handbook' button is correct  i.e. " + actualtext);
						Reporter.log("Success!! price beside 'Buy your boating handbook' button is correct i.e. " + actualtext); 
				
					}
					else
					{
						System.out.println("Failure !! price beside 'Buy your boating handbook' button is incorrect" + "\n" + "Expected Text: "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual Text : " + "\n" +  actualtext);
						Reporter.log("Failure !! price beside 'Buy your boating handbook' button is incorrect" + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
					
					
					
				}
				else
				{
					System.out.println("Failure !!  footer doesn't have 'Boating Safety Handbooks' section  " + "\n" + "Expected Text: "  
										+ "\n" + expectedtext + "\n" + 
										 "Actual Text : " + "\n" +  actualtext);
					Reporter.log("Failure !! footer doesn't have 'Boating Safety Handbooks' section " + "\n" + "Expected Text: "  
							+ "\n" + expectedtext + "\n" + 
							 "Actual Text : " + "\n" +  actualtext);
					
					AssertFailedCount++;
				
				}
				*/
				
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
	 * Boat Washington Course : Verify Sales Tax is included for  Washington (state)  student
	 * */ 
	@Test
	private void WA_SelesTaxVerification() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Boat Washington Course : Verify Sales Tax is included for  Washington (state)  student"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Boat Washington Course : Verify Sales Tax is included for  Washington (state)  student"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ContentsPage content = new ContentsPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/washington");
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/washington");
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
		
			Thread.sleep(8000);
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			header.Contents.click();
			Thread.sleep(4000);
	
			String ContentPageUrl = driver.getCurrentUrl();
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Fast Forward Pass");
			
			//Go back to Contents page
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

			//Click on Continue Exam button
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			//Selecting Answer for Question 61 and clicking on Next button
			
				//Selecting answer
				finalexam.Answer1.click();
				Thread.sleep(4000);

				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
				Thread.sleep(4000);

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			cerinfo.SelectEyeColor("Brown");
			cerinfo.SelectHairColor("Brown");
			
			cerinfo.EnterMailingAddress("2104 NE 45TH ST", "", "Seattle", "Washington", "98105", "United States of America", "234-567-8910");
			
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
			
			
			expectedurl = Constants.ApplicationURL + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , , verfiying Sales Tax info" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , , verfiying Sales Tax info");
		
			//verifying Credit Card info text
			
			String expectedtext = "Sales Tax: 9.6%";
			String actualtext = payinfo.Sales_Tax_Percentage.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Sales Tax info text is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Sales Tax info text is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Sales Tax info text is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !!Sales Tax info text is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
	
	
	/* Test 9: 
	 * Payment - PA > Verify Fees paid by Student
	 * */ 
	@Test
	private void Payment_PA_FeesPaid() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Payment - PA > Verify Fees paid by Student"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 : Payment - PA > Verify Fees paid by Student"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ContentsPage content = new ContentsPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/pennsylvania");
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/pennsylvania");
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			
			Thread.sleep(8000);
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Fast Forward Pass");
			
			//Go back to Contents page
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

			//Click on Continue Exam button
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			//Selecting Answer for Question 61 and clicking on Next button
			
				//Selecting answer
				finalexam.Answer1.click();
				Thread.sleep(4000);

				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
				Thread.sleep(4000);

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			cerinfo.SelectEyeColor("Brown");
			cerinfo.SelectHairColor("Brown");
			
			cerinfo.EnterMailingAddress("104 W MAIN ST", "", "Norristown", "Pennsylvania", "19401", "United States of America", "234-567-8910");
			
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
			
			
			expectedurl = Constants.ApplicationURL + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
	
			
		System.out.println("Step 5 : On Payment Information page - Stage 2 , , verfiying Course Fees information" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , , verfiying Course Fees information");
		
			
			//Verifying Course Fee
			String expectedtext = "$34.50";
			String actualtext = payinfo.CourseFee_Fee.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Course Fee  is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Course Fee is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Course Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Course Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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
	 * Payment - WA > Verify Fees paid by Student
	 * */ 
	@Test
	private void Payment_WA_FeesPaid() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 :  Payment - WA > Verify Fees paid by Student"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10 :  Payment - WA > Verify Fees paid by Student"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ContentsPage content = new ContentsPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/washington");
			PageHeader header = new PageHeader(driver); 	Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/washington");
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
		
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			
			Thread.sleep(8000);
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
			header.Contents.click();
			Thread.sleep(4000);

			String ContentPageUrl = driver.getCurrentUrl();
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Fast Forward Pass");
			
			//Go back to Contents page
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

			//Click on Continue Exam button
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			//Selecting Answer for Question 61 and clicking on Next button
			
				//Selecting answer
				finalexam.Answer1.click();
				Thread.sleep(4000);

				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
				Thread.sleep(4000);

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			cerinfo.SelectEyeColor("Brown");
			cerinfo.SelectHairColor("Brown");
			
			cerinfo.EnterMailingAddress("2104 NE 45TH ST", "", "Seattle", "Washington", "98105", "United States of America", "234-567-8910");
			
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
			
			
			expectedurl = Constants.ApplicationURL + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
			
		System.out.println("Step 5 : On Payment Information page - Stage 2 , , verfiying Course Fees information" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , , verfiying Course Fees information");
		
			
			//Verifying Course Fee
			String expectedtext = "$29.50";
			String actualtext = payinfo.CourseFee_Fee.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Course Fee  is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Course Fee is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Course Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Course Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
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


	/* Test 11: 
	 * Boat Canada Course > Province - Verify 'Quick Links' specific links exists and working as expected
	 * */ 
	@Test(dataProvider="CanadaProvince",dataProviderClass=utility.TestNG.class)
	private void Canada_Province_QuickLinks(String province) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 11 : Boat Canada Course > Province - Verify 'Quick Links' specific links exists and working as expected"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 11 : Boat Canada Course > Province - Verify 'Quick Links' specific links exists and working as expected"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		PageHeader header = new PageHeader(driver);

		String url = "/canada/" + province;
		
		System.out.println("Step 1: Navigating to " + url + " and verifying 'Quick Links'");
		Reporter.log("Step 1: Navigating to " + url + " and verifying 'Quick Links'");
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + url);
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + url);
				Thread.sleep(4000);
			}
			//Verifying whether 'Quick Links' section is present or not
			
		/*	String actualtext = SeleniumFunc.GetElementText("css", "#who > div > div.span4 h4:nth-of-type(1)");
			System.out.println(actualtext);
			String expectedtext ="Quick Links";*/

			if(SeleniumFunc.IsElementPresent("css", "#steps div:nth-of-type(2) nav"))
			{
				System.out.println("Success!! 'Quick Links' section is present i.e. ");
				Reporter.log("Success!! 'Quick Links' section is present i.e. "); 
				
				String[] provincenames = {"Alberta", "British Columbia","Manitoba","New Brunswick","Nova Scotia","Ontario","Saskatchewan"} ; 
				String[] provinceurls = {"/canada/alberta", "/canada/britishcolumbia","/canada/manitoba", "/canada/newbrunswick","/canada/novascotia","/canada/ontario","/canada/saskatchewan"} ; 
				
				
				for(int i=0; i<provincenames.length ;i++)
				{
					//Verifying Province and it's link
					int j=i+1;
					String cssselector = ".quick-links.list-inline li:nth-of-type(" + j + ")";
					String actualtext = SeleniumFunc.GetElementText("css", cssselector);
					/*System.out.println(actualtext);*/
					String expectedtext = provincenames[i];

					if(actualtext.contains(expectedtext) )
					{
						System.out.println("Success!! Quick Links -> " + provincenames[i] + " is present");
						Reporter.log("Success!! Quick Links -> " + provincenames[i] + " is present"); 
					}
					else
					{
						System.out.println("Failure !! Quick Links -> " + provincenames[i] + " is NOT present" + "\n" + "Expected Text: "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual Text : " + "\n" +  actualtext);
						Reporter.log("Failure !! Quick Links -> " + provincenames[i] + " is NOT present" + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
					
					//Clicking on Province link
					cssselector = ".quick-links.list-inline li:nth-of-type(" + j + ") a";
					SeleniumFunc.ClickOnElement("css", cssselector);
					Thread.sleep(3000);
					 
					actualtext = driver.getCurrentUrl();
					/*System.out.println(actualtext);*/
					expectedtext = Constants.ApplicationURL + provinceurls[i]+"/";

					if(actualtext.contains(expectedtext) )
					{
						System.out.println("Success!! Quick Links -> " + provincenames[i] + " has correct URL");
						Reporter.log("Success!! Quick Links -> " + provincenames[i] + " has correct URL"); 
					}
					else
					{
						System.out.println("Failure !! Quick Links -> " + provincenames[i] + " has incorrect URL" + "\n" + "Expected Text: "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual Text : " + "\n" +  actualtext);
						Reporter.log("Failure !! Quick Links -> " + provincenames[i] + " has incorrect URL" + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
					if(!driver.getCurrentUrl().equals(Constants.ApplicationURL + url))
					{
					driver.navigate().back();						Thread.sleep(4000);

					}
					
				}
				
				
				
			}
			else
			{
				System.out.println("Failure !! 'Quick Links' section is NOT present ");
				Reporter.log("Failure !! 'Quick Links' section is NOT present ");
				
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
	
/*	
	 Test 12: 
	 * Boat Canada & Provinces - Verify 'Who Needs the Card?' link
	 *  
	@Test(dataProvider="CanadaAndProvinces",dataProviderClass=utility.TestNG.class)
	private void Canada_Province_VerifyWhoNeedsTheCardLink(String province) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 12 : Boat Canada & Provinces - Verify 'Who Needs the Card?' link"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 12 : Boat Canada & Provinces - Verify 'Who Needs the Card?' link"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		
		String url = province;
		
		System.out.println("Step 1: Navigating to " + url + " and 'Who Needs the Card?' link");
		Reporter.log("Step 1: Navigating to " + url + " and 'Who Needs the Card?' link ");
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + url);
			
			//Verifying whether 'Who Needs the Card?' link is present or not
			
			String actualtext = SeleniumFunc.GetElementText("css", "div[class='span4 state-card'] h4 a");
			System.out.println(actualtext);
			String expectedtext ="Who Needs the Card?";

			if(actualtext.contains(expectedtext) )
			{
				System.out.println("Success!! 'Who Needs the Card?' link is present i.e. " + actualtext);
				Reporter.log("Success!! 'Who Needs the Card?' link is present i.e. " + actualtext); 
				
				
					String cssselector = "div[class='span4 state-card'] h4 a";
					SeleniumFunc.ClickOnElement("css", cssselector);
					Thread.sleep(3000);
					 
					actualtext = driver.getCurrentUrl();
					System.out.println(actualtext);
					expectedtext = Constants.ApplicationURL + "/canada/boating_card.html";

					if(actualtext.contains(expectedtext) )
					{
						System.out.println("Success!! Who Needs the Card? has correct URL");
						Reporter.log("Success!! Who Needs the Card? has correct URL");
					}
					else
					{
						System.out.println("Failure !! Who Needs the Card? has incorrect URL" + "\n" + "Expected Text: "  
											+ "\n" + expectedtext + "\n" + 
											 "Actual Text : " + "\n" +  actualtext);
						Reporter.log("Failure !! Who Needs the Card? has incorrect URL" + "\n" + "Expected Text: "  
								+ "\n" + expectedtext + "\n" + 
								 "Actual Text : " + "\n" +  actualtext);
						
						AssertFailedCount++;
					}
					
					if(!driver.getCurrentUrl().equals(Constants.ApplicationURL + url))
					{
					driver.navigate().back();
					}
					
				}
				
			
			else
			{
				System.out.println("Failure !! 'Who Needs the Card?' link is NOT present " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! 'Who Needs the Card?' link is NOT present" + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
			
				
			
		
		 * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 
	 	if(AssertFailedCount>0)	
		{
			
			//Marking this test as Failed
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
	 
		
	}
	*/
	
	/* Test 13: 
	 * Verify Banner to Advertise Coupon Code is displayed for Absolute discount Coupon & user can use coupon code successfully
	 * */ 
	@Test
	private void PaymentCode_Absolute_Banner() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 13 : Verify Banner to Advertise Coupon Code is displayed for Absolute discount Coupon & user can use coupon code successfully"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 13 : Verify Banner to Advertise Coupon Code is displayed for Absolute discount Coupon & user can use coupon code successfully"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ViewReceiptPage receipt = new ViewReceiptPage(driver);
		HomePage home = new HomePage(driver);
		PageHeader header = new PageHeader(driver);

		System.out.println("Step 1  : Creating new Payment code , Type = Absolute");
		Reporter.log("Step 1  : Creating new Payment code , Type = Absolute"); 
			
			String paymentcode = keadmin.PaymentCodes_New_Type_Absolute("03/02/2015", "07/19/2018","GLOBAL","no");

			
		System.out.println("Step 2: Navigate to Course stage page and verifying promo banner");
		Reporter.log("Step 2: Navigate to Course stage page and verifying promo banner");
		
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
				Thread.sleep(4000);
			}
			//Verifying Promo Banner Text
			String expected = "Limited time discount. Enter coupon code " + paymentcode + " to save $10.00. Valid March 2, 2015 through July 19, 2018";
			String actual = home.CouponBannerText.getText().trim();		
			
			if(actual.contains(expected))
			{
				System.out.println("-- Success !! Promo Banner Text is correct");
				Reporter.log("-- Success !! Promo Banner Text is correct");
			}
			else
			{
				System.out.println("-- Failure !! Promo Banner Text is incorrect , Expected = " + expected + "  Actual = " + actual);
				Reporter.log("-- Failure !! Promo Banner Text is incorrect , Expected = " + expected + "  Actual = " + actual);
				AssertFailedCount++;
			}
			
			//Verifying Promo Banner Terms and Condition Link Text
			expected = "Limited time offer. Terms and conditions apply.";
			actual = home.CouponBannerTermsConditionLink_Boat.getText().trim();		
			
			if(actual.contains(expected))
			{
				System.out.println("-- Success !! Promo Banner Terms and Condition Link is correct");
				Reporter.log("-- Success !! Promo Banner Terms and Condition Link is correct");
			}
			else
			{
				System.out.println("-- Failure !! Promo Banner Terms and Condition Link is incorrect , Expected = " + expected + "  Actual = " + actual);
				Reporter.log("-- Failure !! Promo Banner Terms and Condition Link is incorrect , Expected = " + expected + "  Actual = " + actual);
				AssertFailedCount++;
			}
			
			//Click on Terms link
			home.CouponBannerTermsConditionLink_Boat.click();
			Thread.sleep(4000);

			//Verifying Promo Banner Terms and Conditions text
			expected = "terms & conditions" + "\n" +
						"Must use code " + paymentcode + " to receive $10.00 off the standard course fee. (Course fees vary by state.)" + "\n" +
						"Offers expire at 11:59 p.m. CDT on July 19, 2018."  + "\n" +
						"Offer valid for all students who complete the course and pay the course fee at checkout from March 2, 2015, through July 19, 2018." + "\n" +
						"Offer applies to students who registered for a course before March 2, 2015, but the course must be completed and the course fee paid by 11:59 p.m. CDT on July 19, 2018, to be eligible for the promotion." + "\n" +
						"Offer is subject to change or cancellation at any time.";
				
			actual = home.CouponBannerTermsConditionBox.getText().trim();		
			
			if(actual.equalsIgnoreCase(expected))
			{
				System.out.println("-- Success !! Promo Banner Terms and Condition text is correct");
				Reporter.log("-- Success !! Promo Banner Terms and Condition text is correct");
			}
			else
			{
				System.out.println("-- Failure !! Promo Banner Terms and Condition text is incorrect , Expected = " + expected + "  Actual = " + actual);
				Reporter.log("-- Failure !! Promo Banner Terms and Condition text is incorrect , Expected = " + expected + "  Actual = " + actual);
				AssertFailedCount++;
			}
			
			
	
		System.out.println("Step 3 : Sign up as new user and using Coupon ");
		Reporter.log("Step 3 : Sign up as new user and using Coupon "); 
		
	
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			System.out.println(emailaddress);
			String password = "clarion@123";
			
			
			
			header.Register_New.click();
			
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{		
					register.ClickOnIUnderstandButton();				
				}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
		
			Thread.sleep(10000);
		
			ContentsPage content = new ContentsPage(driver);
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			 {
				content.IntroModal.click();
				Thread.sleep(4000);
			 }		

			content.Contents.click();
			Thread.sleep(4000);
	
			String ContentPageUrl = driver.getCurrentUrl();
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Fast Forward Pass");
			
			//Go back to Contents page
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

			//Click on Continue Exam button
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			//Selecting Answer for Question 61 and clicking on Next button
			
				//Selecting answer
				finalexam.Answer1.click();
				Thread.sleep(4000);

				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
				Thread.sleep(4000);

				
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			cerinfo.Student_SSN.clear();
			cerinfo.Student_SSN.sendKeys("123-45-6789");
			
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
	
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
		
			
			expectedurl = Constants.ApplicationURL + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , applying coupon code" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , applying coupon code");
			
			payinfo.CouponCode.sendKeys(paymentcode);
			payinfo.ApplyCode.click();
		
			Thread.sleep(4000);

			//Verifying Course Total Fee
			String expectedtext = "$19.50";
			String actualtext = payinfo.Total_Fee.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Course Total Fee  is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Course Total Fee is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Course Total Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Course Total Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			payinfo.FirstName.sendKeys("test");
			payinfo.LastName.sendKeys("Patel");
			payinfo.CCNumber.sendKeys("4111111111111111");
			
			new Select(payinfo.ExpirationMonth).selectByVisibleText("03");
			new Select(payinfo.ExpirationYear).selectByVisibleText("2019");
			
			payinfo.BillingContactInfoCheckbox.click();
			payinfo.ConfirmPaymentInformation.click();
			
			Thread.sleep(4000);
			
			payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
			
			Thread.sleep(10000);
			
	
		System.out.println("Step 6 : Verifying Total Amount on 'View Receipt' page");
		Reporter.log("Step 6 : Verifying Total Amount on 'View Receipt' page"); 
		
			//Total Fee
			expectedtext = "Total Amount: $19.50";
			actualtext = receipt.TotalAmount.getText().trim();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! Total Amount is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Total Amount is correct i.e. " + actualtext );
				
			}
			else
			{
				System.out.println("-- Failure !! Total Amount is incorrect. Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Total Amount is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
		
			//Deleting added Payment code
			keadmin.PaymentCodes_Search_Delete(paymentcode);
				
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
	
	
	/* Test 14: 
	 * Verify user can set a banner to be displayed for a specific course
	 * */ 
	@Test
	private void PaymentCode__Banner_SpecificCourse() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 14 : Verify user can set a banner to be displayed for a specific course"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 14 : Verify user can set a banner to be displayed for a specific course"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		HomePage home = new HomePage(driver);
		PageHeader header = new PageHeader(driver);

		System.out.println("Step 1  : Creating new Payment code , Type = Absolute");
		Reporter.log("Step 1  : Creating new Payment code , Type = Absolute"); 
			
			String paymentcode = keadmin.PaymentCodes_New_Type_Absolute("03/02/2015", "07/19/2018","GLOBAL","no");

			
		System.out.println("Step 2: Navigate to Course stage page and verifying promo banner");
		Reporter.log("Step 2: Navigate to Course stage page and verifying promo banner");
		
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
				Thread.sleep(4000);
			}
			//Verifying Promo Banner Text
			String expected = "Limited time discount. Enter coupon code " + paymentcode + " to save $10.00. Valid March 2, 2015 through July 19, 2018";
			String actual = home.CouponBannerText.getText().trim();		
			
			if(actual.contains(expected))
			{
				System.out.println("-- Success !! Promo Banner Text is correct");
				Reporter.log("-- Success !! Promo Banner Text is correct");
			}
			else
			{
				System.out.println("-- Failure !! Promo Banner Text is incorrect , Expected = " + expected + "  Actual = " + actual);
				Reporter.log("-- Failure !! Promo Banner Text is incorrect , Expected = " + expected + "  Actual = " + actual);
				AssertFailedCount++;
			}
			
			//Verifying Promo Banner Terms and Condition Link Text
			expected = "Limited time offer. Terms and conditions apply.";
			actual = home.CouponBannerTermsConditionLink_Boat.getText().trim();		
			
			if(actual.contains(expected))
			{
				System.out.println("-- Success !! Promo Banner Terms and Condition Link is correct");
				Reporter.log("-- Success !! Promo Banner Terms and Condition Link is correct");
			}
			else
			{
				System.out.println("-- Failure !! Promo Banner Terms and Condition Link is incorrect , Expected = " + expected + "  Actual = " + actual);
				Reporter.log("-- Failure !! Promo Banner Terms and Condition Link is incorrect , Expected = " + expected + "  Actual = " + actual);
				AssertFailedCount++;
			}
			
			//Click on Terms link
			home.CouponBannerTermsConditionLink_Boat.click();
			Thread.sleep(4000);

			//Verifying Promo Banner Terms and Conditions text
			expected ="Terms & Conditions" + "\n" +
						"Must use code " + paymentcode + " to receive $10.00 off the standard course fee. (Course fees vary by state.)" + "\n" +
						"Offers expire at 11:59 p.m. CDT on July 19, 2018."  + "\n" +
						"Offer valid for all students who complete the course and pay the course fee at checkout from March 2, 2015, through July 19, 2018." + "\n" +
						"Offer applies to students who registered for a course before March 2, 2015, but the course must be completed and the course fee paid by 11:59 p.m. CDT on July 19, 2018, to be eligible for the promotion." + "\n" +
						"Offer is subject to change or cancellation at any time.";
				
			actual = home.CouponBannerTermsConditionBox.getText().trim();		
			
			if(actual.equalsIgnoreCase(expected))
			{
				System.out.println("-- Success !! Promo Banner Terms and Condition text is correct");
				Reporter.log("-- Success !! Promo Banner Terms and Condition text is correct");
			}
			else
			{
				System.out.println("-- Failure !! Promo Banner Terms and Condition text is incorrect , Expected = " + expected + "  Actual = " + actual);
				Reporter.log("-- Failure !! Promo Banner Terms and Condition text is incorrect , Expected = " + expected + "  Actual = " + actual);
				AssertFailedCount++;
			}
			
		
		System.out.println("Step 3: Navigate to KE admin promo edit page and set Show Banner=No");
		Reporter.log("Step 3: Navigate to KE admin promo edit page and set Show Banner=No");
		
			keadmin.PaymentCodes_Search_Edit_Set_Banner_No(paymentcode);
			
			Thread.sleep(4000);

		System.out.println("Step 4: Navigate to Course stage page and verifying promo banner");
		Reporter.log("Step 4: Navigate to Course stage page and verifying promo banner");
		
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
			Thread.sleep(4000);

			
			try{
				
				home.CouponBannerText.getText().trim();
				System.out.println("-- Failure !! Promo Banner is displayed");
				Reporter.log("-- Failure !! Promo Banner is displayed");
				AssertFailedCount++;
				
			}
			catch(Exception e)
			{
				System.out.println("-- Success !! Promo Banner is NOT displayed");
				Reporter.log("-- Success !! Promo Banner is NOT displayed");
			}

			//Deleting added Payment code
			keadmin.PaymentCodes_Search_Delete(paymentcode);
				
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
	
	
	/* Test 15: 
	 * Verify user can set a banner to be displayed for a specific time period
	 * */ 
	@Test
	private void PaymentCode_Banner_SpecificTimePeriod() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 15 : Verify user can set a banner to be displayed for a specific time period"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 13 : Verify user can set a banner to be displayed for a specific time period"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		HomePage home = new HomePage(driver);
		PageHeader header = new PageHeader(driver);

		System.out.println("Step 1  : Creating new Payment code , Type = Absolute");
		Reporter.log("Step 1  : Creating new Payment code , Type = Absolute"); 
			
			String paymentcode = keadmin.PaymentCodes_New_Type_Absolute("03/02/2010", "07/19/2010", "GLOBAL","no");

			
		System.out.println("Step 2: Navigate to Course stage page and verifying promo banner");
		Reporter.log("Step 2: Navigate to Course stage page and verifying promo banner");
		
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
				Thread.sleep(4000);
			}
			try{
				
				home.CouponBannerText.getText().trim();
				System.out.println("-- Failure !! Promo Banner is displayed");
				Reporter.log("-- Failure !! Promo Banner is displayed");
				AssertFailedCount++;
				
			}
			catch(Exception e)
			{
				System.out.println("-- Success !! Promo Banner is NOT displayed");
				Reporter.log("-- Success !! Promo Banner is NOT displayed");
			}
			
			
		System.out.println("Step 3 : Sign up as new user and using Coupon ");
		Reporter.log("Step 3 : Sign up as new user and using Coupon "); 
		
	
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			System.out.println(emailaddress);
			String password = "clarion@123";
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					register.ClickOnIUnderstandButton();
					Thread.sleep(4000);
				}
				
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			
			Thread.sleep(10000);
			ContentsPage content = new ContentsPage(driver);
			
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			 {
				content.IntroModal.click();
				Thread.sleep(4000);
			 }			

			content.Contents.click();
			Thread.sleep(4000);
	
			String ContentPageUrl = driver.getCurrentUrl();
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			Thread.sleep(4000);

			keadmin.StudentSearchAndActivity(username, "Boat", "Fast Forward Pass");
			
			//Go back to Contents page
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

			//Click on Continue Exam button
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			//Selecting Answer for Question 61 and clicking on Next button
			
				//Selecting answer
				finalexam.Answer1.click();
				Thread.sleep(4000);

				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
				Thread.sleep(4000);

				
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			cerinfo.Student_SSN.clear();
			cerinfo.Student_SSN.sendKeys("123-45-6789");
			
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
	
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
		
			
			expectedurl = Constants.ApplicationURL + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , applying coupon code" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , applying coupon code");
			
			payinfo.CouponCode.sendKeys(paymentcode);
			payinfo.ApplyCode.click();
			Thread.sleep(4000);

			//Verifying validation message for invalid code
			String expectedtext = "Invalid student coupon or agency code. Please re-enter your code.";
			String actualtext = payinfo.CouponCodeAlertText.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! correct validation message for invalid code i.e. " + actualtext );
				Reporter.log("-- Success !! correct validation message for invalid code i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! incorrect validation message for invalid code. Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! incorrect validation message for invalid code . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
		
			
			//Verifying Course Total Fee
			expectedtext = "$29.50";
			actualtext = payinfo.Total_Fee.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Course Total Fee  is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Course Total Fee is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Course Total Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Course Total Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
		
			//Deleting added Payment code
			keadmin.PaymentCodes_Search_Delete(paymentcode);
			Thread.sleep(4000);

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
	
	
	/* Test 16: 
	 * Verify Banner to Advertise Coupon Code is displayed for Percentage discount Coupon & user can use coupon code successfully
	 * */ 
	@Test
	private void PaymentCode_Percentage_Banner() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 16 : Verify Banner to Advertise Coupon Code is displayed for Percentage discount Coupon & user can use coupon code successfully"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 16 : Verify Banner to Advertise Coupon Code is displayed for Percentage discount Coupon & user can use coupon code successfully"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ViewReceiptPage receipt = new ViewReceiptPage(driver);
		HomePage home = new HomePage(driver);
		PageHeader header = new PageHeader(driver);
		ContentsPage content = new ContentsPage(driver);
		
		System.out.println("Step 1  : Creating new Payment code , Type = Percentage");
		Reporter.log("Step 1  : Creating new Payment code , Type = Percentage"); 
			
			String paymentcode = keadmin.PaymentCodes_New_Type_Percentage("Yes","GLOBAL","no");
			
			
		System.out.println("Step 2: Navigate to Course stage page and verifying promo banner");
		Reporter.log("Step 2: Navigate to Course stage page and verifying promo banner");
		
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
				Thread.sleep(4000);
			}
			//Verifying Promo Banner Text
			String expected = "Limited time discount. Enter coupon code " + paymentcode + " to save 50%. Valid December 1, 2014 through October 6, 2020";
			String actual = home.CouponBannerText.getText().trim();		
			
			if(actual.contains(expected))
			{
				System.out.println("-- Success !! Promo Banner Text is correct");
				Reporter.log("-- Success !! Promo Banner Text is correct");
			}
			else
			{
				System.out.println("-- Failure !! Promo Banner Text is incorrect , Expected = " + expected + "  Actual = " + actual);
				Reporter.log("-- Failure !! Promo Banner Text is incorrect , Expected = " + expected + "  Actual = " + actual);
				AssertFailedCount++;
			}
			
			//Verifying Promo Banner Terms and Condition Link Text
			expected = "Limited time offer. Terms and conditions apply.";
			actual = home.CouponBannerTermsConditionLink_Boat.getText().trim();		
			
			if(actual.contains(expected))
			{
				System.out.println("-- Success !! Promo Banner Terms and Condition Link is correct");
				Reporter.log("-- Success !! Promo Banner Terms and Condition Link is correct");
			}
			else
			{
				System.out.println("-- Failure !! Promo Banner Terms and Condition Link is incorrect , Expected = " + expected + "  Actual = " + actual);
				Reporter.log("-- Failure !! Promo Banner Terms and Condition Link is incorrect , Expected = " + expected + "  Actual = " + actual);
				AssertFailedCount++;
			}
			
			//Click on Terms link
			home.CouponBannerTermsConditionLink_Boat.click();
			Thread.sleep(4000);

			//Verifying Promo Banner Terms and Conditions text
			expected = "Terms & Conditions" + "\n" +
						"Must use code " + paymentcode + " to receive 50% off the standard course fee. (Course fees vary by state.)" + "\n" +
						"Offers expire at 11:59 p.m. CDT on October 6, 2020."  + "\n" +
						"Offer valid for all students who complete the course and pay the course fee at checkout from December 1, 2014, through October 6, 2020." + "\n" +
						"Offer applies to students who registered for a course before December 1, 2014, but the course must be completed and the course fee paid by 11:59 p.m. CDT on October 6, 2020, to be eligible for the promotion." + "\n" +
						"Offer is subject to change or cancellation at any time.";
				
			actual = home.CouponBannerTermsConditionBox.getText().trim();		
			
			if(actual.equalsIgnoreCase(expected))
			{
				System.out.println("-- Success !! Promo Banner Terms and Condition text is correct");
				Reporter.log("-- Success !! Promo Banner Terms and Condition text is correct");
			}
			else
			{
				System.out.println("-- Failure !! Promo Banner Terms and Condition text is incorrect , Expected = " + expected + "  Actual = " + actual);
				Reporter.log("-- Failure !! Promo Banner Terms and Condition text is incorrect , Expected = " + expected + "  Actual = " + actual);
				AssertFailedCount++;
			}
			
			
	
		System.out.println("Step 3 : Sign up as new user and using Coupon ");
		Reporter.log("Step 3 : Sign up as new user and using Coupon "); 
		
	
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			System.out.println(emailaddress);
			String password = "clarion@123";
			
			
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
				{
					
					register.ClickOnIUnderstandButton();
					
				}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
		
			Thread.sleep(8000);
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			header.Contents.click();
			Thread.sleep(4000);
			
			String ContentPageUrl = driver.getCurrentUrl();
			
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Fast Forward Pass");
			
			//Go back to Contents page
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

			//Click on Continue Exam button
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			//Selecting Answer for Question 61 and clicking on Next button
			
				//Selecting answer
				finalexam.Answer1.click();
				Thread.sleep(4000);

				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
				Thread.sleep(4000);

				
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			cerinfo.Student_SSN.clear();
			cerinfo.Student_SSN.sendKeys("123-45-6789");
			
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
	
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(4000);
		
			
			expectedurl = Constants.ApplicationURL + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(4000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , applying coupon code" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , applying coupon code");
			
			payinfo.CouponCode.sendKeys(paymentcode);
			payinfo.ApplyCode.click();
		
			Thread.sleep(4000);

			//Verifying Course Total Fee
			String expectedtext = "$14.75";
			String actualtext = payinfo.Total_Fee.getText().trim();
			
			if(actualtext.equals(expectedtext))
			{
				System.out.println("-- Success !! Course Total Fee  is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Course Total Fee is correct i.e. " + actualtext );
			}
			else
			{
				System.out.println("-- Failure !! Course Total Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Course Total Fee is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
			payinfo.FirstName.sendKeys("test");
			payinfo.LastName.sendKeys("Patel");
			payinfo.CCNumber.sendKeys("4111111111111111");
			
			new Select(payinfo.ExpirationMonth).selectByVisibleText("03");
			new Select(payinfo.ExpirationYear).selectByVisibleText("2019");
			
			payinfo.BillingContactInfoCheckbox.click();
			payinfo.ConfirmPaymentInformation.click();
			
			Thread.sleep(4000);
			
			payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
			
			Thread.sleep(10000);
			
	
		System.out.println("Step 6 : Verifying Total Amount on 'View Receipt' page");
		Reporter.log("Step 6 : Verifying Total Amount on 'View Receipt' page"); 
		
			//Total Fee
			expectedtext = "Total Amount: $14.75";
			actualtext = receipt.TotalAmount.getText().trim();
			
			if(actualtext.contains(expectedtext))
			{
				System.out.println("-- Success !! Total Amount is correct i.e. " + actualtext );
				Reporter.log("-- Success !! Total Amount is correct i.e. " + actualtext );
				
			}
			else
			{
				System.out.println("-- Failure !! Total Amount is incorrect. Expected is : " + expectedtext + " , Actual is : " + actualtext );
				Reporter.log("-- Failure !! Total Amount is incorrect . Expected is : " + expectedtext + " , Actual is : " + actualtext);
				AssertFailedCount++;
			}
			
		
			//Deleting added Payment code
			keadmin.PaymentCodes_Search_Delete(paymentcode);
				
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


	/* Test 17: 
	 * Verify Banner to advertise coupon code is not displayed for "Pass" code
	 * */ 
	@Test
	private void PaymentCode_Pass_Banner() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 17 : Verify Banner to advertise coupon code is not displayed for 'Pass' code"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
				"Test 17 : Verify Banner to advertise coupon code is not displayed for 'Pass' code" + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		HomePage home = new HomePage(driver);
		PageHeader header = new PageHeader(driver);

		System.out.println("Step 1  : Creating new Payment code , Type = Pass");
		Reporter.log("Step 1  : Creating new Payment code , Type = Pass"); 
			
			driver.get(Constants.AdminURL + "/admin/paymentcodes");
			
			//Clicking on 'Create New Payment Code' button
			driver.findElement(By.cssSelector("div.clear a")).click();
			Thread.sleep(4000);

			//Entering required information and creating new code
			String paymentcode = "passcode" + JH.GenerateRandomNumber();
			driver.findElement(By.id("payment_code")).sendKeys(paymentcode);
			driver.findElement(By.id("paymentcode_desc")).sendKeys("Added For Testing");
			
			new Select(driver.findElement(By.id("coupon_type"))).selectByVisibleText("PASS");
		
			
			((JavascriptExecutor) driver).executeScript ("document.getElementById ('valid_begin_date').removeAttribute('readonly',0);");
			driver.findElement(By.id("valid_begin_date")).sendKeys("12/01/2014");
			
			((JavascriptExecutor) driver).executeScript ("document.getElementById ('valid_end_date').removeAttribute('readonly',0);");
			driver.findElement(By.id("valid_end_date")).sendKeys("10/06/2020");
		
			driver.findElement(By.id("max_uses")).sendKeys("1");
			
			try
			{
				if(driver.findElement(By.cssSelector("input[name='PaymentCode[show_banner]'][value='1']")).getAttribute("disabled").equalsIgnoreCase("true"))
						{
						System.out.println("-- Success !! radio buttons (show banner and Override global banner' ) are disabled ​");
						Reporter.log("-- Success !! radio buttons (show banner and Override global banner' ) are disabled ​");
						}
				
				
			}
			catch(Exception e)
			{
				System.out.println("-- Failure !! radio buttons (show banner and Override global banner' ) are enabled  ​");
				Reporter.log("-- Failure !! radio buttons (show banner and Override global banner' ) are enabled  ​");
				AssertFailedCount++;
				
				
			}
			
			driver.findElement(By.cssSelector("div.clear a")).click();
			
			Thread.sleep(4000);

		System.out.println("Step 2: Navigate to Course stage page and verifying promo banner");
		Reporter.log("Step 2: Navigate to Course stage page and verifying promo banner");
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
				Thread.sleep(4000);
			}
			try{
				
				home.CouponBannerText.getText().trim();
				System.out.println("-- Failure !! Promo Banner is displayed");
				Reporter.log("-- Failure !! Promo Banner is displayed");
				AssertFailedCount++;
				
			}
			catch(Exception e)
			{
				System.out.println("-- Success !! Promo Banner is NOT displayed");
				Reporter.log("-- Success !! Promo Banner is NOT displayed");
			}
			
			//Deleting added Payment code
			keadmin.PaymentCodes_Search_Delete(paymentcode);
				
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
	
	
	/* Test 18: 
	 * Verify 'Override Global Banner' option for display of banner
	 * */ 
	@Test
	private void Override_Global_Banner() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 18 : Verify 'Override Global Banner' option for display of banner"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 18 : Verify 'Override Global Banner' option for display of banner"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		HomePage home = new HomePage(driver);
		
		System.out.println("Step 1  : Creating new Payment code : Course = Global, Type = Percentage , Override Global Banner = Yes ");
		Reporter.log("Step 1  : Creating new Payment code : Course = Global, Type = Percentage , Override Global Banner = Yes ");
			
			String paymentcode1 = keadmin.PaymentCodes_New_Type_Percentage("yes","GLOBAL","yes");

			System.out.println(paymentcode1);
		System.out.println("Step 2  : Creating new Payment code : Course = Boat Alabama Course, Type = Percentage , Override Global Banner = No ");
		Reporter.log("Step 1  : Creating new Payment code : Course = Boat Alabama Course, Type = Percentage , Override Global Banner = No");
			
			String paymentcode2 = keadmin.PaymentCodes_New_Type_Percentage("yes","Alabama Boat Ed Course","no");
			System.out.println(paymentcode2);

			
		System.out.println("Step 3: Navigate to Course stage page and verifying promo banner");
		Reporter.log("Step 3: Navigate to Course stage page and verifying promo banner");
		
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
			Thread.sleep(4000);

			//Verifying Promo Banner Text
			String expected = "Limited time discount. Enter coupon code " + paymentcode1 + " to save 50%. Valid December 1, 2014 through October 6, 2020";
			String actual = home.CouponBannerText.getText().trim();		
			
			if(actual.contains(expected))
			{
				System.out.println("-- Success !! Promo Banner Text is correct");
				Reporter.log("-- Success !! Promo Banner Text is correct");
			}
			else
			{
				System.out.println("-- Failure !! Promo Banner Text is incorrect , Expected = " + expected + "  Actual = " + actual);
				Reporter.log("-- Failure !! Promo Banner Text is incorrect , Expected = " + expected + "  Actual = " + actual);
				AssertFailedCount++;
			}
			keadmin.PaymentCodes_Search_Delete(paymentcode2);
			Thread.sleep(4000);

		System.out.println("Step 4  : Creating new Payment code : Course = Boat Alabama Course, Type = Percentage , Override Global Banner = Yes ");
		Reporter.log("Step 4  : Creating new Payment code : Course = Boat Alabama Course, Type = Percentage , Override Global Banner = Yes");
			
			String paymentcode3 = keadmin.PaymentCodes_New_Type_Percentage("yes","Alabama Boat Ed Course","yes");

					
		System.out.println("Step 5: Navigate to Course stage page and verifying promo banner");
		Reporter.log("Step 5: Navigate to Course stage page and verifying promo banner");
		
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
			Thread.sleep(4000);

			//Verifying Promo Banner Text
			expected = "Limited time discount. Enter coupon code " + paymentcode3 + " to save 50%. Valid December 1, 2014 through October 6, 2020";
			actual = home.CouponBannerText.getText().trim();		
			
			if(actual.contains(expected))
			{
				System.out.println("-- Success !! Promo Banner Text is correct");
				Reporter.log("-- Success !! Promo Banner Text is correct");
			}
			else
			{
				System.out.println("-- Failure !! Promo Banner Text is incorrect , Expected = " + expected + "  Actual = " + actual);
				Reporter.log("-- Failure !! Promo Banner Text is incorrect , Expected = " + expected + "  Actual = " + actual);
				AssertFailedCount++;
			}
					
			
	
			//Deleting added Payment code
			keadmin.PaymentCodes_Search_Delete(paymentcode1);
			Thread.sleep(4000);

			keadmin.PaymentCodes_Search_Delete(paymentcode3);
				
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
	
	//Boat > Shopping feedback form
	@Test
	private void ShoppingFeedbackForm() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test  : Boat > Verify Shopping feedback form"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test  : Boat > Verify Shopping feedback form"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		RegistrationPage register = new RegistrationPage(driver);
		KECourseAdminPage keadmin = new KECourseAdminPage(driver);
		FinalExamPage finalexam = new FinalExamPage(driver);
		CertificationInformationPage cerinfo = new CertificationInformationPage(driver);;
		PaymentInformationPage payinfo = new PaymentInformationPage(driver);
		ContentsPage content = new ContentsPage(driver);
		PageHeader header=new PageHeader(driver);
		pageFactory.Courses.LoginPage login=new pageFactory.Courses.LoginPage(driver);
		CertificationInformationPage cert=new CertificationInformationPage(driver);
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			System.out.println(emailaddress);
			String password = "clarion@123";
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
			Thread.sleep(4000);
		
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
				Thread.sleep(4000);

			}
			
			header.Register_New.click();
		
			Thread.sleep(8000);
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{
				register.ClickOnIUnderstandButton();
			}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			Thread.sleep(10000);
		
			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			 {
				content.IntroModal.click();
				Thread.sleep(4000);
			 }				
		
			header.Contents.click();
			Thread.sleep(4000);
	
			String ContentPageUrl = driver.getCurrentUrl();

	
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Fast Forward Pass");
			
			//Go back to Contents page
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			Thread.sleep(4000);

			//Click on Continue Exam button
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			//Selecting Answer for Question 61 and clicking on Next button
			
				//Selecting answer
				finalexam.Answer1.click();
				Thread.sleep(4000);

				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
				Thread.sleep(4000);

				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href");
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			Thread.sleep(4000);

			String actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! 'Complete the Application' button is working as expected ");
				Reporter.log("-- Success !! 'Complete the Application' button is working as expected");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Complete the Application' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
			
			
		System.out.println("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page" );
		Reporter.log("Step 4 : On Application page - Stage 1 : Certification Information ,  updating User info, Contact info, navigating to Step 2 - Payment Informaiton page");		
		
			cerinfo.SelectGender("Female");
			cerinfo.Student_SSN.clear();
			cerinfo.Student_SSN.sendKeys("123-45-6789");
			
			//cerinfo.EnterMailingAddress("28261 N", "NY 10003", "New York", "Alabama", "11111", "United States of America", "234-567-8910");
			cerinfo.EnterMailingAddress("14086 Proton Rd", "", "Dallas", "Texas", "75244", "United States of America", "234-567-8910");
			
	
			cerinfo.ConfirmCertificateInformation.click();
			Thread.sleep(5000);
		
			
			expectedurl = Constants.ApplicationURL + "/register/payment/" ;
			cerinfo.ConfirmCertificateInformationModal_ContinuePayInfo.click();
			Thread.sleep(5000);

			actualurl = driver.getCurrentUrl();
			
			if(actualurl.contains(expectedurl))
			{
				System.out.println("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
				Reporter.log("-- Success !! user is navigated to step 2 ( i.e. Payment Information)");
			}
			else
			{
				System.out.println("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				Reporter.log("-- Failure !! On click to 'Continue Payment Information' button , user is navigated to : " + driver.getCurrentUrl());
				AssertFailedCount++;
			}
		
		System.out.println("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button" );
		Reporter.log("Step 5 : On Payment Information page - Stage 2 , entering data for CC & selecting billing address=Mailing address and clicking on 'Confirm Payment Information' button");
		
			payinfo.FirstName.sendKeys("test");
			payinfo.LastName.sendKeys("Patel");
			payinfo.CCNumber.sendKeys("4111111111111111");
			
			new Select(payinfo.ExpirationMonth).selectByVisibleText("03");
			new Select(payinfo.ExpirationYear).selectByVisibleText("2019");
			
			payinfo.BillingContactInfoCheckbox.click();
			payinfo.ConfirmPaymentInformation.click();
			
			Thread.sleep(4000);
			
			payinfo.ConfirmPaymentInformationModal_ProcessMyPayment.click();
			
			Thread.sleep(10000);
	
		System.out.println("Step 6 : Sign Out & login again" );
		Reporter.log("Step 6 : Sign Out & login again");
			
			 header.SaveAndLogOut.click();
			 Thread.sleep(4000);
			  
			 header.Login.click();
			 Thread.sleep(4000);
			
			 login.EnterUsername(username);
			 login.EnterPassword(password);
			 
			 login.ClickOnLogInButton();
			 Thread.sleep(6000);
		
		System.out.println("Step 7 : Submit the Feedback form" );
		Reporter.log("Step 7 : Submit the Feedback form"); 
			
			cert.clickOnOverallRating();
			Thread.sleep(2000);
			cert.clickOnRecommendRating();
			Thread.sleep(2000);
			cert.clickOnReBuyRating();
			Thread.sleep(2000);
			
			cert.enterComment();
			cert.clickOnSatisfiedRadioButton();
			cert.clickOncloseRating();
			
			System.out.println("Success!!! Feedback Form is submitted");
			Reporter.log(" Success!!! Feedback Form is submitted "); 
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
