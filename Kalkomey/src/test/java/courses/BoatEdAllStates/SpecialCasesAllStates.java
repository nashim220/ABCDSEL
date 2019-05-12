package courses.BoatEdAllStates;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.CertificationInformationPage;
import pageFactory.Courses.FinalExamPage;
import pageFactory.Courses.KECourseAdminPage;
import pageFactory.Courses.PageFooter;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.PaymentInformationPage;
import pageFactory.Courses.RegistrationPage;

import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class SpecialCasesAllStates 
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
	@Test(dataProvider="RegistrationStates",dataProviderClass=utility.TestNG.class)
	private void OR_VerifyReplacementCardSection(String State, String Title) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Boat Oregon Course > Verify details for 'How do I get a replacement card?' on /oregon/boating_card.html#replace"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Boat Oregon Course > Verify details for 'How do I get a replacement card?' on /oregon/boating_card.html#replace"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		
		String url = "/oregon/faq/#card-replacement";
		
		System.out.println("Step 1: Navigating to /oregon/boating_card.html#replace and verifying details");
		Reporter.log("Step 1: Navigating to /oregon/boating_card.html#replace and verifying details"); 
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + url);
			
			
			//Verifying "How do I get a replacement card?" section title 
				
			String expectedtext = "Replacement of Lost or Damaged Documents";
			String actualtext = SeleniumFunc.GetElementText("css","#certificate h3:nth-of-type(3)");
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
					+ "To replace a lost or destroyed Certificate of Completion, log in to the course and print a new one." 
					+"How do I replace my Boater Education Card if I’ve lost it or it has been damaged?"+"\n"
					+"The Oregon State Marine Board has updated its database system to include an online store, "
					+ "capable of accepting requests for replacement Boating Education Cards. You will be able to register,"
					+ " start a Boater Education Card Replacement Application, update your information, and pay the $8 "
					+ "replacement fee for the new card with a VISA, MasterCard, or Discover card."+"\n"
					+"You will also be able to print a temporary permit, valid for 60 days, so that you may continue "
					+ "to boat while waiting for your new card to arrive. Please visit the Oregon State Marine Board "
					+ "site or download this PDF for more information.";
						
			actualtext = SeleniumFunc.GetElementText("css","#certificate div:nth-of-type(5)")+SeleniumFunc.GetElementText("css","#certificate div:nth-of-type(6)");
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
			
			SeleniumFunc.ClickOnElement("css", "#certificate p:nth-of-type(2) a:nth-of-type(2)");
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
	
	
	
	/* Test 4: 
	 * Payment : Verify Info message for CC field (i.e.Unfortunately, we do not accept PayPal, gift cards, or preloaded debit or bank cards as payment at this time.)
	 * */ 
	@Test(dataProvider="RegistrationStates",dataProviderClass=utility.TestNG.class)
	private void Payment_CCField_InfoText(String State, String Title) throws Exception
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
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + Constants.State);
			PageHeader header = new PageHeader(driver); header.Register_New.click();
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{
				
				register.ClickOnIUnderstandButton();
				
			}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			
			Thread.sleep(8000);
			header.Contents.click();
						
			String ContentPageUrl = driver.getCurrentUrl();
	
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Fast Forward Pass");
			
			//Go back to Contents page
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			
			//Click on Continue Exam button
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			
			//Selecting Answer for Question 61 and clicking on Next button
			
				//Selecting answer
				finalexam.Answer1.click();
				
				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
			 
				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
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
			
			String expectedtext = "Unfortunately, we do not accept PayPal, gift cards, or preloaded debit or bank cards as payment at this time.";
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
	
	
	
	/* Test 7: 
	 * Footer : Verify details under 'Boating Safety Handbooks' & 'About Boat Ed' sections
	 * */ 
	@Test(dataProvider="RegistrationStates",dataProviderClass=utility.TestNG.class)
	private void Footer_VerifyAbout_Handbooks_Sections(String State, String Title) throws Exception
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
	
		
		System.out.println("Step 1: Navigate to application page");
		Reporter.log("Step 1: Navigate to application page"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL);
			
			
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
			
		/*		 * Marking Test Pass or Fail as per the value of AssertFailedCount variable
		 */	 	if(AssertFailedCount>0)	
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
	@Test(dataProvider="RegistrationStates",dataProviderClass=utility.TestNG.class)
	private void Payment_FeesPaid(String State, String Title) throws Exception
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
		
		System.out.println("Step 1: Signing up as new User");
		Reporter.log("Step 1: Signing up as new User"); 
			
			String username= "payment" + JH.GenerateRandomNumber();
			System.out.println(username);
			String emailprefix = "payment" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			String password = "clarion@123";
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/pennsylvania");
			PageHeader header = new PageHeader(driver); header.Register_New.click();
			
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{
				
				register.ClickOnIUnderstandButton();
				
			}
			
			register.RegisterAsNewUser(username, password, password, "test", "B", "ke-testing", "Jr.", "January", "2", "1990", emailaddress, emailaddress);
			
			Thread.sleep(8000);
			header.Contents.click();
						
			String ContentPageUrl = driver.getCurrentUrl();
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Fast Forward Pass");
			
			//Go back to Contents page
			SeleniumFunc.ToGoToUrl(ContentPageUrl);
			
			//Click on Continue Exam button
			SeleniumFunc.ClickOnElement("css", "#next_text a");
			
			//Selecting Answer for Question 61 and clicking on Next button
			
				//Selecting answer
				finalexam.Answer1.click();
				
				//Clicking next button
				SeleniumFunc.ClickOnElement("id", "submit");
			 
				
		System.out.println("Step 3 : Clicking on 'Complete the Application' button" );
		Reporter.log("Step 3 : Clicking on 'Complete the Application' button");
		
			String expectedurl = SeleniumFunc.GetAttributeValue("css", "#next_text a", "href")+"/";
			SeleniumFunc.ClickOnElement("css", "#next_text a");
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
	
}
