package courses.BoatEdAllStates;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.CertificationInformationPage;
import pageFactory.Courses.ContentsPage;
import pageFactory.Courses.FinalExamPage;
import pageFactory.Courses.HomePage;
import pageFactory.Courses.KECourseAdminPage;
import pageFactory.Courses.PageHeader;
import pageFactory.Courses.PaymentInformationPage;
import pageFactory.Courses.RegistrationPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class AllStatesTest 
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
	 * Verify that student can register for course successfully
	*/ 
	@Test(dataProvider="RegistrationStates",dataProviderClass=utility.TestNG.class)
	private void RegistrationAllStates(String State) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify that student can register for course successfully"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify that student can register for course successfully"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		RegistrationPage register = new RegistrationPage(driver);
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	
		System.out.println("Step 1 : Navigate to Applicaiton URL : " + Constants.ApplicationURL);
		Reporter.log("Step 1 : Navigate Applicaiton URL : " + Constants.ApplicationURL); 
			
		System.out.println("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + State);
		Reporter.log("Step 2 : Navigate to Registration page : " + Constants.ApplicationURL + State); 
		
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); 
			
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
			Thread.sleep(6000);
		
			//Model Reminder
			if(SeleniumFunc.IsElementPresent("id", "wait-right-there"))
			{
				register.ClickOnIUnderstandButton();	
				Thread.sleep(4000);

			}
			
			
		System.out.println("Step 3 : Filling registration form and clicking on 'Create Account' button");
		Reporter.log("Step 3 : Filling registration form and clicking on 'Create Account' button"); 
		
			String username="Reg" + JH.GenerateRandomNumber();
			String emailprefix = "registration" + JH.GenerateRandomNumber();
			String emailaddress= emailprefix + "@mailinator.com";
			System.out.println(emailaddress);
			String password = "clarion@123";
			register.FillRegistrationForm(username, password,password, "Test","R","Ke-testing","Jr.","May","1","1990", emailaddress, emailaddress);
			
			//Residency Requirement
			if(SeleniumFunc.IsElementPresent("id", "StudentModel_residency"))
			{
					register.ClickOnStateResidencyCheckbox();
					Thread.sleep(4000);
			}
			
			register.ClickOnCreateAccount();
	
			Thread.sleep(8000);
			
			ContentsPage content = new ContentsPage(driver);

			if(SeleniumFunc.IsElementPresent(content.IntroModal))
			{
				content.IntroModal.click();
				Thread.sleep(4000);
			}
			
		System.out.println("Step 4 : Verifying whether user is registered successfully or not");
		Reporter.log("Step 4 : Verifying whether user is registered successfully or not"); 
						
			if(SeleniumFunc.IsElementPresent("css", "a[href='/site/logout']"))
			{			
				System.out.println("Success !! 'Save & Log Out' link is present '");
				Reporter.log("Success !! 'Save & Log Out' link is present"); 
			}
			else
			{
				System.out.println("Failure !! 'Save & Log Out' link is NOT present ");
				Reporter.log("Failure !! 'Save & Log Out' link is NOT present"); 
				AssertFailedCount++;
			}
					
			String ActualUserName=SeleniumFunc.GetElementText("css", "a[href='/register/edit']").trim();
			System.out.println(ActualUserName);
		
			if(ActualUserName.equals(username))
			{
			
				System.out.println("Success !! Username is correct'");
				Reporter.log("Success !! Username is correct"); 
			}
			else
			{
				System.out.println("Failure !!Username is incorrect");
				Reporter.log("Failure !! Username is incorrect"); 
				AssertFailedCount++;
			}
			
		System.out.println("Step 5: Logging out of application");
		Reporter.log("Step 5: Logging out of application"); 
					
				SeleniumFunc.ClickOnElement("css", "a[href='/site/logout']");
				Thread.sleep(4000);
				
		System.out.println("Step 6 : Verifying whether user is logged out successfully or not");
		Reporter.log("Step 6 : Verifying whether user is logged out successfully or not"); 
		
		String actual = SeleniumFunc.ToGetCurrentPageUrl();
		String expected = Constants.ApplicationURL + "/site/logout/";
		
				if(actual.equals(expected))
				{
					System.out.println("Success !! User is navigated to Logout page");
					Reporter.log("Success !! User is navigated to Logout page"); 
				}
				else
				{
					System.out.println("Failure !!  User is NOT navigated to Logout page , instead navigated to: " + expected +"  should be: "+actual);
					Reporter.log("Failure !!  User is NOT navigated to Logout page , instead navigated to: " + expected+"  should be: "+actual); 
		
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
	 * Payment -  Verify Fees paid by Student
	 * */ 
	@Test(dataProvider="FeesPaidStates",dataProviderClass=utility.TestNG.class)
	private void Payment_FeesPaid(String State, String Fees) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Payment  > Verify Fees paid by Student"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 : Payment > Verify Fees paid by Student"  + "\n" +
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
			
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
			PageHeader header = new PageHeader(driver); 
		
			Thread.sleep(4000);
			if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
			{
				header.Logout_Handgun.click();
				Thread.sleep(4000);
				SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/" + State);
				Thread.sleep(4000);
			}
			
			header.Register_New.click();
			Thread.sleep(5000);

			//Model Reminder
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
			
			
			header.Contents.click();

			Thread.sleep(4000);
		
			String ContentPageUrl = driver.getCurrentUrl();
			
		System.out.println("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page");
		Reporter.log("Step 2 : Navigating on KE Cousers Admin and performing 'Fast Forward Pass' and navigating back to Exam result page"); 
		
			SeleniumFunc.ToGoToUrl(Constants.AdminURL);
			keadmin.StudentSearchAndActivity(username, "Boat", "Fast Forward Pass");
			Thread.sleep(4000);

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
		
			cerinfo.SelectGender("Male");
			/*cerinfo.SelectEyeColor("Brown");
			cerinfo.SelectHairColor("Brown");*/
			
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
			String expectedtext = "$" + Fees;
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
		
			//Verifying Course Total Fee
			expectedtext = "$" + Fees;
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
	 * Payment -  Verify Home page contents
	 * */ 
	@Test(dataProvider="HomePage_Verification",dataProviderClass=utility.TestNG.class)
	private void HomePageVerification(String State, String Header, String SubHeader, String CourseTitle, String StateAgency, String StateAgencyInformation) throws InterruptedException
	{
		System.out.println("====" + "\n" +
				"Test 1 : Home - Verify UI elements on home page e.g. qa1.boat-ed.com"  + "\n" +
	 			"====");
	Reporter.log("====" + "\n" +
	 			  "Test 1 : Home - Verify UI elements on home page e.g. qa1.boat-ed.com"  + "\n" +
			 	  "====");	
	
	int AssertFailedCount=0 ;
	SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
	HomePage home = new HomePage(driver);
	PageHeader header = new PageHeader(driver);

	System.out.println("\n Step 1: Navigated to home page and verifying UI elements");
	Reporter.log("\n Step 1: Navigated to home page and verifying UI elements"); 		
		
		SeleniumFunc.ToGoToUrl(Constants.ApplicationURL+ "/" + State);
		Thread.sleep(4000);
		if(SeleniumFunc.IsElementPresent(header.Logout_Handgun))
		{
			header.Logout_Handgun.click();
			Thread.sleep(4000);
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL+ "/" + State);
			Thread.sleep(4000);
		}
		//Verifying Header 
			
			//verifying application name
		
		System.out.println("\n Step 2: Verifying Course Title");
		Reporter.log("\n Step 2: Verifying Course Title"); 
			String expectedtext = CourseTitle;
			String actualtext = home.Header_ApplicationName.getText();
			System.out.println(actualtext);

			if(actualtext.trim().contains(expectedtext) )
			{
				System.out.println("Success!! Header has correct Course Title i.e. " + actualtext);
				Reporter.log("Success!! Header has correct Course Title i.e. " + actualtext); 
			}
			else
			{
				System.out.println("Failure !! Header has incorrect Course Title " + "\n" + "Expected Text: "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual Text : " + "\n" +  actualtext);
				Reporter.log("Failure !! Header has incorrect State Course Title " + "\n" + "Expected Text: "  
						+ "\n" + expectedtext + "\n" + 
						 "Actual Text : " + "\n" +  actualtext);
				
				AssertFailedCount++;
			}
					
					//verifying Header title
				
				System.out.println("\n Step 3: Verifying State Header Title");
				Reporter.log("\n Step 3: Verifying State Header Title"); 
					String expectedheader = Header;
					String actualheader = home.Billboard_textBoat.getText();
					System.out.println(actualheader);

					if(actualtext.trim().contains(expectedheader) )
					{
						System.out.println("Success!! Header has correct State Header name i.e. " + actualheader);
						Reporter.log("Success!! Header has correct State Header name i.e. " + actualheader); 
					}
					else
					{
						System.out.println("Failure !! Header has incorrect State Header name " + "\n" + "Expected Text: "  
											+ "\n" + expectedheader + "\n" + 
											 "Actual Text : " + "\n" +  actualheader);
						Reporter.log("Failure !! Header has incorrect State Header name " + "\n" + "Expected Text: "  
								+ "\n" + expectedheader + "\n" + 
								 "Actual Text : " + "\n" +  actualheader);
						
						AssertFailedCount++;
					}
			
				
					//verifying Sub Header title
					
					System.out.println("\n Step 4: Verifying State SubHeader Title");
					Reporter.log("\n Step 4: Verifying State SubHeader Title"); 
						String expectedsubheader = SubHeader;
						String actualsubheader = home.State_Billboard_text2_New.getText();
						System.out.println(actualsubheader);

						if(actualtext.trim().contains(expectedsubheader) )
						{
							System.out.println("Success!! Header has correct State SubHeader name i.e. " + actualsubheader);
							Reporter.log("Success!! Header has correct State SubHeader name i.e. " + actualsubheader); 
						}
						else
						{
							System.out.println("Failure !! Header has incorrect State SubHeader name " + "\n" + "Expected Text: "  
												+ "\n" + expectedsubheader + "\n" + 
												 "Actual Text : " + "\n" +  actualsubheader);
							Reporter.log("Failure !! Header has incorrect State SubHeader name " + "\n" + "Expected Text: "  
									+ "\n" + expectedsubheader + "\n" + 
									 "Actual Text : " + "\n" +  actualsubheader);
							
							AssertFailedCount++;
						}
				
					
						//verifying State agency name
						
						System.out.println("\n Step 5: Verifying State Agency Name");
						Reporter.log("\n Step 5: Verifying State Agency Name"); 
							String expectedstate = StateAgency;
							String actualstate = home.AgencyName_New.getText();
							System.out.println(actualstate);

							if(actualtext.trim().contains(expectedstate) )
							{
								System.out.println("Success!! has correct State Agency Name i.e. " + actualstate);
								Reporter.log("Success!! has correct State State Agency Name i.e. " + actualstate); 
							}
							else
							{
								System.out.println("Failure !! has incorrect State Agency Name " + "\n" + "Expected Text: "  
													+ "\n" + expectedstate + "\n" + 
													 "Actual Text : " + "\n" +  actualstate);
								Reporter.log("Failure !! has incorrect State Agency Name " + "\n" + "Expected Text: "  
										+ "\n" + expectedstate + "\n" + 
										 "Actual Text : " + "\n" +  actualstate);
								
								AssertFailedCount++;
							}
					
							//verifying State agency information
							
							System.out.println("\n Step 6: Verifying State Agency information");
							Reporter.log("\n Step 6: Verifying State Agency information"); 
								String expectedstateinfo = StateAgencyInformation;
								String actualstateinfo = home.AgencyInformation.getText();
								System.out.println(actualstateinfo);

								if(actualtext.trim().contains(expectedstateinfo) )
								{
									System.out.println("Success!! has correct State Agency information i.e. " + actualstateinfo);
									Reporter.log("Success!! has correct State State Agency information i.e. " + actualstateinfo); 
								}
								else
								{
									System.out.println("Failure !! has incorrect State Agency information " + "\n" + "Expected Text: "  
														+ "\n" + expectedstateinfo + "\n" + 
														 "Actual Text : " + "\n" +  actualstateinfo);
									Reporter.log("Failure !! has incorrect State Agency information " + "\n" + "Expected Text: "  
											+ "\n" + expectedstateinfo + "\n" + 
											 "Actual Text : " + "\n" +  actualstateinfo);
									
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
