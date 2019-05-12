package products.CM;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.DetailsCertificationPage;
import pageFactory.CM.EditCertificationPage;
import pageFactory.CM.GlobalHeader;
import pageFactory.CM.LoginPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

	public class CertificationDetailsTest {
	
	
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
      * Details Certification - Verify that user is able to view details of a certifications
      */ 
     @Test
     public void ViewDetailsofACertifications() throws InterruptedException
     {
    	 	System.out.println("====" + "\n" +
    	 				"Test 1 : Certification Details - Verify that user is able to view details of a certifications" + "\n" +	
    	 				"====");
    	 	Reporter.log("====" + "\n" +
    	 				"Test 1 : Certification Details - Verify that user is able to view details of a certifications"  + "\n" + 
    	 				"====");	
     
    	 		int AssertFailedCount=0 ;
    	 		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
    	 		LoginPage login = new LoginPage(driver);    	 		
    	 		EditCertificationPage editcer = new EditCertificationPage(driver);
    	 	
    	 	System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
    	 	Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
	
    	 		SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

    	 	System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
    	 	Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 	

    	 		login.EnterUsername(Constants.CM_Username);
    	 		login.EnterPassword(Constants.CM_Password);
    	 		login.ClickOnLogInButton();    	 		   	 	 
    	 	
    	 		System.out.println("Step 3 : Verify user has redirected to Certification manager page or not.");
        	 	Reporter.log("Step 3 : Verify user has redirected to Certification manager page or not."); 	
        	 	
    	 		//Verifying alert message on login    	 	
				String actualtext= SeleniumFunc.GetElementText("css", ".widget-header>h3");
				String expectedtext= "Search Certifications";				

				if(actualtext.equals(expectedtext))
				{
						System.out.println("Success !! user has redirected to Certification manager page");
						Reporter.log("Success !! user has redirected to Certification manager page"); 
				}
				else
				{
						System.out.println("Failure !! user is not redirected to Certification manager page" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
   							 	"Actual : " + "\n" +  actualtext);
						Reporter.log("Failure !!  user is not redirected to Certification manager page" + "\n" + "Expected : "  
								+ "\n" + expectedtext + "\n" + 
								"Actual : " + "\n" +  actualtext);
   		
						AssertFailedCount++;
				}
				
			System.out.println("Step 4 : From Certification manager, search for a certification ");
		    Reporter.log("Step 4 : From Certification manager, search for a certification"); 
		        
		       editcer.SearchCM("Testing CertificationDetail");
	 		 	Thread.sleep(1000);
	 		 	editcer.ClickOnSearch();
		    	
	 		 	System.out.println("Step 5 : From searched records , Click on Student name");
	 			Reporter.log("Step 5 : From searched records , Click on Student name"); 
	 				editcer.ClickOnStudentNametab();
	 				
	 				System.out.println("Step 6 : Verify user has redirected to Cetification details page or not.");
	        	 	Reporter.log("Step 6 : Verify user has redirected to Cetification details page or not."); 	
	        	 	
	    	 		//Verifying alert message on login    	 	
					String actualdetail= SeleniumFunc.GetElementText("css", ".span8>h1");
					
					//String expecteddetail= "Testing CertificationDetail";				
					String expecteddetail= "BROCK E CHARETTE";	
					if(actualdetail.equals(expecteddetail))
					{
							System.out.println("Success !! user has redirected to Certification details page");
							Reporter.log("Success !! user has redirected to Certification details page"); 
					}
					else
					{
							System.out.println("Failure !! user is not redirected to Certification details page" + "\n" + "Expected : "  
									+ "\n" + expecteddetail + "\n" + 
	   							 	"Actual : " + "\n" +  actualdetail);
							Reporter.log("Failure !!  user is not redirected to Certification details page" + "\n" + "Expected : "  
									+ "\n" + expecteddetail + "\n" + 
									"Actual : " + "\n" +  actualdetail);
	   		
							AssertFailedCount++;
					}
	 				
					System.out.println("Step 7 : Observe Details page");
	        	 	Reporter.log("Step 7 : Observe Details page"); 	
	 				
		  	     	String Studentinfo=SeleniumFunc.GetElementText("css", "h4.span8");
		  	     	String Certificateinfo=SeleniumFunc.GetElementText("css", "h4.span6");
		  	     	String Activity=SeleniumFunc.GetElementText("css", "#activity-stream>header");
		  	     
		  	     	if(Studentinfo.equals("Student Information") && Certificateinfo.equals("Certification Information") && Activity.equals("Activity") )		   			
		  	     	{
							   
		  	     		System.out.println("Success !! User has navigated to valid page along with following section,Student Information,Certification Information,Activity");
		  	     		Reporter.log("Success !! User has navigated to valid page along with following section,Student Information,Certification Information,Activity"); 
					  }
		  	     	else
		  	     	{
		  	     		System.out.println("Failure !! User is not navigated to valid page.");
		  	     		Reporter.log("Failure !! User is not navigated to valid page."); 
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
      * Details Certification - Verify elements on Student info details section
      */ 
     @Test
     public void ElementsOnStudentInfo() throws InterruptedException 
     {
    	
    	 	System.out.println("====" + "\n" +
    				"Test 2 : Certification Details - Verify elements on Student info details section" + "\n" +
    	 			"====");
    	    Reporter.log("====" + "\n" +  "Test 2 : Certification Details - Verify elements on Student info details section"  + "\n" +
    			 	  "====");	
    	     
    	    	 int AssertFailedCount=0 ;
    	    	 SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
    	    	 LoginPage login = new LoginPage(driver);    	    	
    	    	 EditCertificationPage editcer = new EditCertificationPage(driver);
    	    	 DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);    	 		
    	    	    	    
    	     System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
    	    Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
    	    SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
    	    
    	    SeleniumFunc.WebDriverWaitMethod("css", "input[value='Login']");
    	    System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
    	    Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 	

    	    	 login.EnterUsername(Constants.CM_Username);
    	    	 login.EnterPassword(Constants.CM_Password);
    	    	 login.ClickOnLogInButton();
    	    	 
    	    System.out.println("Step 3 : Search for a certification and click on student name.");
    	    Reporter.log("Step 3 : Search for a certification and click on student name.");     			
    		     
    		     Thread.sleep(2000);   
    		     editcer.SearchCM("Testing CertificationDetail");
 	 		 	Thread.sleep(1000);
 	 		 	editcer.ClickOnSearch();
 	 		 	editcer.ClickOnStudentNametab();     
    		     
 	 		  System.out.println("Step 4 : Verify student information page section data is displayed properly or not.");
 	    	    Reporter.log("Step 4 : Verify student information page section data is displayed properly or not.");  
 	    	
 	    	   //Name 
 	    	    String StudentName=SeleniumFunc.GetElementText("css", "div.span8>h1");
    		     if(StudentName.equals("BROCK E CHARETTE"))
    		     {
    				
    		     	System.out.println("Success !! Student name data  is present");
    		     	Reporter.log("Success !! Student name  data is present"); 
    		     }
    		     else
    		     {
    		     	System.out.println("Failure !! Student name data  is not present ");
    		     	Reporter.log("Failure !!Student name data  is not present "); 
    		     	AssertFailedCount++;
    		     }	
    		
    		     //Physical address 
    		     String PhysicalAddress=SeleniumFunc.GetElementText("css", "#contact-info div:nth-of-type(1) dl:nth-of-type(1) dd");
    		     String Expected1= "6201 RIMFIRE RD";
    		     if(PhysicalAddress.contains(Expected1))
    		     {
    				
    		     	System.out.println("Success !! Physical Address data is present");
    		     	Reporter.log("Success !! Physical Address data is present"); 
    		     }
    		     else
    		     {
    		     	System.out.println("Failure !! Physical Address data is not present ");
    		     	Reporter.log("Failure !! Physical Address data is not present "); 
    		     	AssertFailedCount++;
    		     }	
    		     
    		   //Physical Phone Number
    		     String PhysicalPhoneNumber=SeleniumFunc.GetElementText("css", "#contact-info div:nth-of-type(1) dl:nth-of-type(2) dd");
    		     if(PhysicalPhoneNumber.equals("123456789"))
    		     {
    				
    		     	System.out.println("Success !! Physical Phone Number data is present");
    		     	Reporter.log("Success !! Physical Phone Number data is present"); 
    		     }
    		     else
    		     {
    		     	System.out.println("Failure !! Physical Phone Number data is not present ");
    		     	Reporter.log("Failure !! Physical Phone Number data is not present "); 
    		     	AssertFailedCount++;
    		     }	
    		     
    		     //Date Of Birth 
    		     String DOB=SeleniumFunc.GetElementText("css", "#contact-info div:nth-of-type(1) dl:nth-of-type(3) dd");
    		     if(DOB.equals("02/06/1986"))
    		     {
    				
    		     	System.out.println("Success !! Date of birth  data  is present");
    		     	Reporter.log("Success !! Date of birth  data is present"); 
    		     }
    		     else
    		     {
    		     	System.out.println("Failure !! Date of birth  data is not present ");
    		     	Reporter.log("Failure !! Date of birth  data  is not present "); 
    		     	AssertFailedCount++;
    		     }	
    			 
    		     //Gender
    		     String Gender=SeleniumFunc.GetElementText("css", "#contact-info div:nth-of-type(1) dl:nth-of-type(4) dd");
    		     if(Gender.equals("M"))
    		     {
    				
    		     	System.out.println("Success !! Gender data  is present");
    		     	Reporter.log("Success !! Gender data is present"); 
    		     }
    		     else
    		     {
    		     	System.out.println("Failure !! Gender data is not present ");
    		     	Reporter.log("Failure !! Gender data is not present "); 
    		     	AssertFailedCount++;
    		     }	
    		     
    		     //Mailing Address
    		     String Maillingaddress=SeleniumFunc.GetElementText("css", "#contact-info div:nth-of-type(2) dl:nth-of-type(1) dd");
    		     String Expected = "6201 RIMFIRE RD";
    		     if(Maillingaddress.contains(Expected))
    		     {
    				
    		     	System.out.println("Success !! Mailling address data  is present");
    		     	Reporter.log("Success !!  Mailling address data is present"); 
    		     }
    		     else
    		     {
    		     	System.out.println("Failure !!  Mailling address data is not present ");
    		     	Reporter.log("Failure !!  Mailling address data is not present "); 
    		     	AssertFailedCount++;
    		     }	
    		     
    		     //Mailing phone number 
    		     String Mailingphonenumber=SeleniumFunc.GetElementText("css", "#contact-info div:nth-of-type(2) dl:nth-of-type(2) dd");
    		     if(Mailingphonenumber.equals("123456789"))
    		     {
    				
    		     	System.out.println("Success !! Mailing phone number data  is present");
    		     	Reporter.log("Success !! Mailing phone number data is present"); 
    		     }
    		     else
    		     {
    		     	System.out.println("Failure !! Mailing phone number data is not present ");
    		     	Reporter.log("Failure !! Mailing phone number data is not present "); 
    		     	AssertFailedCount++;
    		     }	
    		     
    		     //Email Address
    		     String Emailaddress=SeleniumFunc.GetElementText("css", "#contact-info div:nth-of-type(2) dl:nth-of-type(3) dd");
    		     if(Emailaddress.equals("abc@test.com"))
    		     {
    				
    		     	System.out.println("Success !! Email address Text is present");
    		     	Reporter.log("Success !! Email address Text is present"); 
    		     }
    		     else
    		     {
    		     	System.out.println("Failure !! Email address Text is not present ");
    		     	Reporter.log("Failure !! Email address Text is not present "); 
    		     	AssertFailedCount++;
    		     }	
    		     
    		     //Show more information
    		     if(SeleniumFunc.IsElementPresent(DetailsPage.ShowmoreInfo))
    		     {
    				
    		     	System.out.println("Success !! Show more Information link  is present");
    		     	Reporter.log("Success !! Show more Information link is present"); 
    		     }
    		     else
    		     {
    		     	System.out.println("Failure !! Show more Information link is not present ");
    		     	Reporter.log("Failure !! Show more Information link is not present "); 
    		     	AssertFailedCount++;
    		     }	
    		     
    		     //Edit button 
    		     if(SeleniumFunc.IsElementPresent(editcer.EditTab))
    		     {
    				
    		     	System.out.println("Success !! Edit link is present");
    		     	Reporter.log("Success !! Edit link is present"); 
    		     }
    		     else
    		     {
    		     	System.out.println("Failure !! Edit link is not present ");
    		     	Reporter.log("Failure !! Edit link is not present "); 
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
      * Details Certification - Verify that user can see additional student info.
      */ 
     @Test
     public void SeeAdditionalStudentInfo() throws InterruptedException
     {
     
    	 	System.out.println("====" + "\n" +
    	 				"Test 3 : Certification Details - Verify that user can see additional student info." + "\n" +
    	 				"====");
    	 	Reporter.log("====" + "\n" +  "Test 3 : Certification Details - Verify that user can see additional student info."  + "\n" +
			 	  "====");	
	     
    	 			int AssertFailedCount=0 ;
    	 			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
    	 			LoginPage login = new LoginPage(driver);	    	
    	 			EditCertificationPage editcer = new EditCertificationPage(driver);
    	 			DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);    	 	
    	 			GlobalHeader gheader = new GlobalHeader(driver);	
	    	 
    	 	System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
    	 	Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
		
    	 			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

    	 	System.out.println("Step 2 : Login to the application and verify User is logged in successfully.");
    	 	Reporter.log("Step 2 : Login to the application and verify User is logged in successfully."); 	

    	 			login.EnterUsername(Constants.CM_Username);
    	 			login.EnterPassword(Constants.CM_Password);
    	 			login.ClickOnLogInButton();
	    	 
    	 			String actualtext= gheader.Success_AlertText.getText().trim();
    	 			String expectedtext= "Signed in!";
	  				if(actualtext.equals(expectedtext))
	  					{
	  						System.out.println("Success !! Login  message is displayed i.e. " + actualtext);
	  						Reporter.log("Success !! Login  message is displayed i.e. " + actualtext); 
	  					}
	  						else
	  						{
	  							System.out.println("Failure !! incorrect  message is displayed" + "\n" + "Expected : "  + "\n" + expectedtext + "\n" +  "Actual : " + "\n" +  actualtext);
	  							Reporter.log("Failure !!  incorrect  message is displayed" + "\n" + "Expected : "  + "\n" + expectedtext + "\n" +  "Actual : " + "\n" +  actualtext);
	  							Assert.fail();
	  						}
		     
	  		System.out.println("Step 3 : Go to certification details page (select jurisdication say PA , type :Hunting while searching for certifications)");
	  		Reporter.log("Step 3 : Go to certification details page (select jurisdication say PA , type :Hunting while searching for certifications)");
		     
	  				Thread.sleep(1000);	
	  				SeleniumFunc.SelectValueFromDropdownList("id", "state", "Pennsylvania");
	  				//SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "state",44);					
	  				Thread.sleep(1000);
				
	  				SeleniumFunc.SelectValueFromDropdownList("id", "certification_type_id", "Boating");
	  				//SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "certification_type_id", 1);			
	  				editcer.SearchCM("Testing CertificationDetail");
	  				Thread.sleep(1000);
	  				editcer.ClickOnSearch();
	  				Thread.sleep(1000);
	  				editcer.ClickOnStudentNametab();
		    	 	  
	  		System.out.println("Step 4 : Verify, User is on https://kps.kalkomey.com/certifications/<id>");
	  		Reporter.log("Step 4 :  Verify, User is on https://kps.kalkomey.com/certifications/<id>");
		    	 	  
	  				if(SeleniumFunc.ToGetCurrentPageUrl().contains(Constants.ApplicationURL_CM + "/certifications"))
	  					{
	  						System.out.println("Success !! https://kps.staging.kalkomey.com/certifications is present");
	  						Reporter.log("Success !!https://kps.staging.kalkomey.com/certifications is present"); 
	  					}
		 		 	 		else
		 		 	 		{
		 		 	 			System.out.println("Failure !! Shttps://kps.staging.kalkomey.com/certifications is NOT present ");
		 		 	 			Reporter.log("Failure !!https://kps.staging.kalkomey.com/certifications is NOT present "); 
		 		 	 			AssertFailedCount++;
		 		 	 		}
		    	 	 
	  		System.out.println("Step 5 : Click on 'Show more info' link and verify User should be able to see Additional details of the student");
	  		Reporter.log("Step 5 : Click on 'Show more info' link and verify User should be able to see Additional details of the student"); 
	  		       
	  				Thread.sleep(2000);
	  				driver.navigate().back();
	  				Thread.sleep(2000);
	  				DetailsPage.ClickOnShowMoreInfo();
	  				Thread.sleep(1000);
		 			 //Height
	  				String Emailaddress=SeleniumFunc.GetElementText("css", "#contact-info div:nth-of-type(3) dl:nth-of-type(1) dd");
	  				if(Emailaddress.equals("7"))
	  					{
	  					Thread.sleep(1000);
	  						System.out.println("Success !! Height Information Text  is present");
	  						Reporter.log("Success !! Height Information Text is present"); 
	  					}
	  						else
	  						{
	  							System.out.println("Failure !! Height Information Text is not present ");
	  							Reporter.log("Failure !! Height Information Text is not present "); 
	  							AssertFailedCount++;
	  						}	
	  					//Weight
	  					String Weight=SeleniumFunc.GetElementText("css", "#contact-info div:nth-of-type(3) dl:nth-of-type(2) dd");
	  					if(Weight.equals("45"))
	  						{
	  						Thread.sleep(1000);
	  							System.out.println("Success !! Weight Information Text  is present");
	  							Reporter.log("Success !! Weight Information Text is present"); 
	  						}
	  							else
	  							{
	  								System.out.println("Failure !! Weight Information Text is not present ");
	  								Reporter.log("Failure !! Weight Information Text is not present "); 
	  								AssertFailedCount++;
	  							}	
	  					//Eye color
	  					String EyeColor=SeleniumFunc.GetElementText("css", "#contact-info div:nth-of-type(3) dl:nth-of-type(3) dd");
	  					if(EyeColor.equals("Red"))
	  						{
	  							System.out.println("Success !! Eye color Information Text  is present");
	  							Reporter.log("Success !! Eye color Information Text is present"); 
	  						}
	  							else
	  							{
	  								System.out.println("Failure !! Eye color Information Text is not present ");
	  								Reporter.log("Failure !! Eye color Information Text is not present "); 
	  								AssertFailedCount++;
	  							}	
	  					//Hair color
	  					String HairColor=SeleniumFunc.GetElementText("css", "#contact-info div:nth-of-type(3) dl:nth-of-type(4) dd");
	  					if(HairColor.equals("Blue"))
	  						{
	  							System.out.println("Success !! HairColor Information Text  is present");
	  							Reporter.log("Success !! HairColor Information Text is present"); 
	  						}
	  							else
	  							{
	  								System.out.println("Failure !! HairColor Information Text is not present ");
	  								Reporter.log("Failure !! HairColor Information Text is not present "); 
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
		      * Details Certification - Expose payment bypass info in edit activity log.
		      */ 
	@Test
	public void PaymentBypassInfo() throws InterruptedException
		     {
		     
		    	 System.out.println("====" + "\n" +
						"Test 4 : Details Certification - Expose payment bypass info in edit activity log" + "\n" +
			 			"====");
			     Reporter.log("====" + "\n" +  "Test 4 : Expose payment bypass info in edit activity log"  + "\n" +
					 	  "====");	
			     
			    	 int AssertFailedCount=0 ;
			    	 SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			    	 LoginPage login = new LoginPage(driver);			    	 
			    	 EditCertificationPage editcer = new EditCertificationPage(driver);
			    	 DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);    	 		
			    	
			    System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
			    Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
				
			    	 SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

			    	 System.out.println("Step 2 : Login with valid credentials");
		 		 		Reporter.log("Step 2 : Login with valid credentials"); 	
		 						
		 		 		 login.EnterUsername(Constants.CM_Username);
		 		 		 login.EnterPassword(Constants.CM_Password);
		 		 		 login.ClickOnLogInButton();
							
		 		 		System.out.println("Step 3 : Go to certification details page (select jurisdication say PA , type : hunting while searching for certifications)");
				  		Reporter.log("Step 3 : Go to certification details page (select jurisdication say PA , type :hunting while searching for certifications)");
					     
				  				Thread.sleep(1000);		
				  				/*
				  				SeleniumFunc.SelectValueFromDropdownList("id", "state", "Mississippi");
				  				//SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "state",39);					
				  				Thread.sleep(1000);
				  				SeleniumFunc.SelectValueFromDropdownList("id", "certification_type_id", "Boating");
				  				//SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "certification_type_id", 2);		*/
				  				editcer.SearchCM("Brandon S Faughnan");
				  				Thread.sleep(1000);
				  				editcer.ClickOnSearch();
				  				Thread.sleep(1000);
				  				editcer.ClickOnStudentNametab();			 
		 			
		 			System.out.println("Step 4 : Verify User should be able to search the certification");
		 			Reporter.log("Step 4 : Verify User should be able to search the certification");
				
		 				if(SeleniumFunc.IsElementPresent(editcer.EditTab))	 				
		  				{   
		  				
		  					System.out.println("Success !! User is able to search the certification");
		  					Reporter.log("Success !! User is able to search the certification"); 
		  				}
		  				else
		  				{
		  					System.out.println("Failure !! User is able to search the certification ");
		  					Reporter.log("Failure !! User is not able to search the certification"); 
		  					AssertFailedCount++;
		  				}	
				     Thread.sleep(1000);
				     
		 				 System.out.println("Step 5 : Click  on Order Replacement Certificate and verify User is able to successfully place an order");
		 		 		Reporter.log("Step 5 : Click on Order Replacement Certificate and verify User is able to successfully place an order");
				    //Click on OrderReplacementCertificate 
				     DetailsPage.ClickOnOrderReplacementCerti();
				     
				     if(SeleniumFunc.IsElementPresent(DetailsPage.OrderReplacementCertificatePge))
				     {
						
				     	System.out.println("Success !! User has successfully place an order");
				     	Reporter.log("Success !! User has successfully place an order"); 
				     }
				     else
				     {
				     	System.out.println("Failure !! OrderReplacementCertificate button is not working");
				     	Reporter.log("Failure !! OrderReplacementCertificate button is not working"); 
				     	AssertFailedCount++;
				     }	
				     Thread.sleep(1000);	     
			   System.out.println("Step 6 : Perform a payment bypass, choose any reason and enter comments");
			   Reporter.log("Step 6 : Perform a payment bypass, choose any reason and enter comments");
			   		
			   		//Click On Bypass payment  button 
			   		DetailsPage.ClickOnBypassPayment();
			   		
			   		Thread.sleep(1000);
			        //Select Reason 
			   		SeleniumFunc.SelectValueFromDropdownList("css", "#historical_action_type_id", "Payment by Check");
			   
			        //Select No of Card 
			   		SeleniumFunc.SelectValueFromDropdownListUsingIndex("css", "#quantity",3); 
			   		DetailsPage.EnterReasonSelectText("Pay By Cash");
			   		
			   		DetailsPage.ClickOnContinueButton();
			   		Thread.sleep(2000);
			   		
			   System.out.println("Step 7 : Verify Bypass payment is working or not.");
			   Reporter.log("Step 7 : Verify Bypass payment is working or not.");
			   
			   		if(SeleniumFunc.IsElementPresent(DetailsPage.ByPassPaymenttext))
			   		{
				
			   			System.out.println("Success !! Payment has been bypassed for this certification order Text  is present");
			   			Reporter.log("Success !!  Payment has been bypassed for this certification order Text is present"); 
			   		}
			   		else
			   		{
			   			System.out.println("Failure !!  Payment has been bypassed for this certification order Text is not present ");
			   			Reporter.log("Failure !!  Payment has been bypassed for this certification order Text is not present "); 
			   			AssertFailedCount++;
			   		}
			   		
			   System.out.println("Step 8 : Go back to the certification page");
				Reporter.log("Step 8 : Go back to the certification page");
			   	    
				    DetailsPage.ClickOncertificationManager();
				    Thread.sleep(2000);   
				    editcer.EnterStudentInfoforSErch("Brandon S Faughnan");
				    editcer.ClickOnSearch();
				    Thread.sleep(2000);
				    DetailsPage.clickonfirstcertificate(); 
			   		
				    Thread.sleep(2000);
				    
				       System.out.println("Step 9 : Verify   Activity for bypass payment is appear��as well as the payment bypass or not.");
					   Reporter.log("Step 9 : Verify   Activity for bypass payment is appear��as well as the payment bypass or not.");
					   
					   String  Activityforbypasspayment=SeleniumFunc.GetElementText("css", "#activity-stream  li:nth-of-type(1) div:nth-of-type(2) h1").trim();
					  
					   String  Activityforbypass=SeleniumFunc.GetElementText("css", "#activity-stream  li:nth-of-type(2) div:nth-of-type(2) h1").trim();
					  
					   		if(Activityforbypasspayment.equals("Payment bypassed for replacement certificate.") && Activityforbypass.equals("Replacement certificate ordered."))
					   		{
						        System.out.println("Success !! Activity for bypass payment is appear��as well as the payment bypass");
					   			Reporter.log("Success !!  Activity for bypass payment is appear��as well as the payment bypass"); 
					   		}
					   		else
					   		{
					   			System.out.println("Failure !!  Activity for bypass payment is not appear��as well as the payment bypass ");
					   			Reporter.log("Failure !!  Activity for bypass payment is not appear��as well as the payment bypass"); 
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
		      * Details Certification - Verify that user can hide additional student info.
		      */ 
		     @Test
		     public void  HideAdditionalStudentInfo() throws InterruptedException
		     {
		     
		    	 System.out.println("====" + "\n" +
						"Test 5 : Certification Details - Verify that user can hide additional student info." + "\n" +
			 			"====");
			     Reporter.log("====" + "\n" +  "Test 5 : Certification Details - Verify that user can hide additional student info."  + "\n" +
					 	  "====");	
			     
			    	 int AssertFailedCount=0 ;
			    	 SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			    	 LoginPage login = new LoginPage(driver);			    	 
			    	 EditCertificationPage editcer = new EditCertificationPage(driver);
			    	 DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);    	 		
			    	 GlobalHeader gheader = new GlobalHeader(driver);	
			    	 
			    System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
			    Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
				
			    	 SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

			    	 System.out.println("Step 2 : Login to the application and verify User is logged in successfully.");
			    	 	Reporter.log("Step 2 : Login to the application and verify User is logged in successfully."); 	

			    	 			login.EnterUsername(Constants.CM_Username);
			    	 			login.EnterPassword(Constants.CM_Password);
			    	 			login.ClickOnLogInButton();
				    	 
			    	 			String actualtext= gheader.Success_AlertText.getText().trim();
			    	 			String expectedtext= "Signed in!";
				  				if(actualtext.equals(expectedtext))
				  					{
				  						System.out.println("Success !! Login  message is displayed i.e. " + actualtext);
				  						Reporter.log("Success !! Login  message is displayed i.e. " + actualtext); 
				  					}
				  						else
				  						{
				  							System.out.println("Failure !! incorrect  message is displayed" + "\n" + "Expected : "  + "\n" + expectedtext + "\n" +  "Actual : " + "\n" +  actualtext);
				  							Reporter.log("Failure !!  incorrect  message is displayed" + "\n" + "Expected : "  + "\n" + expectedtext + "\n" +  "Actual : " + "\n" +  actualtext);
				  							Assert.fail();
				  						}
					     
				  		System.out.println("Step 3 : Go to certification details page (select jurisdication say PA , type : Boating while searching for certifications)");
				  		Reporter.log("Step 3 : Go to certification details page (select jurisdication say PA , type :Boating while searching for certifications)");
					     
				  				Thread.sleep(1000);	
				  				
				  				SeleniumFunc.SelectValueFromDropdownList("id", "state", "Pennsylvania");
				  				//SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "state",39);					
				  				Thread.sleep(1000);
				  				SeleniumFunc.SelectValueFromDropdownList("id", "certification_type_id", "Boating");
				  				//SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "certification_type_id", 1);		
				  				editcer.SearchCM("Testing CertificationDetail");
				  				Thread.sleep(1000);
				  				editcer.ClickOnSearch();
				  				Thread.sleep(1000);
				  				editcer.ClickOnStudentNametab();
					    	 	  
				  		
					    	 	 
				  		System.out.println("Step 4 : Click on 'Show more info' link and verify User should be able to see Additional details of the student");
				  		Reporter.log("Step 4 : Click on 'Show more info' link and verify User should be able to see Additional details of the student"); 
				  		
				  	         	Thread.sleep(2000);
				  				DetailsPage.ClickOnShowMoreInfo();
				  	         	Thread.sleep(2000);

					 			 //Height
				  				String Emailaddress=SeleniumFunc.GetElementText("css", "#contact-info div:nth-of-type(3) dl:nth-of-type(1) dd");
				  				if(Emailaddress.equals("7"))
				  					{
				  						System.out.println("Success !! Height Information Text  is present");
				  						Reporter.log("Success !! Height Information Text is present"); 
				  					}
				  						else
				  						{
				  							System.out.println("Failure !! Height Information Text is not present ");
				  							Reporter.log("Failure !! Height Information Text is not present "); 
				  							AssertFailedCount++;
				  						}	
				  					//Weight
				  					String Weight=SeleniumFunc.GetElementText("css", "#contact-info div:nth-of-type(3) dl:nth-of-type(2) dd");
				  					if(Weight.equals("45"))
				  						{
				  							System.out.println("Success !! Weight Information Text  is present");
				  							Reporter.log("Success !! Weight Information Text is present"); 
				  						}
				  							else
				  							{
				  								System.out.println("Failure !! Weight Information Text is not present ");
				  								Reporter.log("Failure !! Weight Information Text is not present "); 
				  								AssertFailedCount++;
				  							}	
				  					//Eye color
				  					String EyeColor=SeleniumFunc.GetElementText("css", "#contact-info div:nth-of-type(3) dl:nth-of-type(3) dd");
				  					if(EyeColor.equals("Red"))
				  						{
				  							System.out.println("Success !! Eye color Information Text  is present");
				  							Reporter.log("Success !! Eye color Information Text is present"); 
				  						}
				  							else
				  							{
				  								System.out.println("Failure !! Eye color Information Text is not present ");
				  								Reporter.log("Failure !! Eye color Information Text is not present "); 
				  								AssertFailedCount++;
				  							}	
				  					//Hair color
				  					String HairColor=SeleniumFunc.GetElementText("css", "#contact-info div:nth-of-type(3) dl:nth-of-type(4) dd");
				  					if(HairColor.equals("Blue"))
				  						{
				  							System.out.println("Success !! HairColor Information Text  is present");
				  							Reporter.log("Success !! HairColor Information Text is present"); 
				  						}
				  							else
				  							{
				  								System.out.println("Failure !! HairColor Information Text is not present ");
				  								Reporter.log("Failure !! HairColor Information Text is not present "); 
				  								AssertFailedCount++;
				  							}	
				  					System.out.println("Step 5 : Click on 'Show more info' link and verify Additional student information should no longer be displayed");
							  		Reporter.log("Step 5 : Click on 'Show more info' link and verify Additional student information should no longer be displayed"); 
							  		
							  				Thread.sleep(1000);
							  				DetailsPage.ClickOnShowMoreInfo();
							  				Thread.sleep(1000);
								 			 //Height
							  				String HideInformation=SeleniumFunc.GetElementText("css", ".collapsible-toggler");
							  				if(HideInformation.equals("Show more information"))
							  					{
							  						System.out.println("Success !! Additional student information should no longer be displayed");
							  						Reporter.log("Success !! Additional student information should no longer be displayed"); 
							  					}
							  						else
							  						{
							  							System.out.println("Failure !! 'Show more info' link is not working. ");
							  							Reporter.log("Failure !! 'Show more info' link is not working. "); 
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
		      * Details Certification - Verify Functionality of Edit button
		      */ 
		     @Test
			 	public void FunctionalityOfEditButton() throws InterruptedException 
			 	{
			 		
			 			System.out.println("====" + "\n" +	"Test 6 : Certification Details - Verify Functionality of Edit button" + "\n" +
			 					"====");
			 			Reporter.log("====" + "\n" +
							 			  "Test 6 : Certification Details - Verify Functionality of Edit button"  + "\n" +
									 	  "====");	
			 				int AssertFailedCount=0 ;
			 				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			 				LoginPage login = new LoginPage(driver);			 				
			 				EditCertificationPage editcer = new EditCertificationPage(driver);
			 				
			 		
			 			System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
			 			Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
								
			 				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
							
			 				System.out.println("Step 2 : Login with valid credentials");
			 		 		Reporter.log("Step 2 : Login with valid credentials"); 	
			 						
			 		 		 login.EnterUsername(Constants.CM_Username);
			 		 		 login.EnterPassword(Constants.CM_Password);
			 		 		 login.ClickOnLogInButton();
								
			 		 		System.out.println("Step 3 : Go to certification details page (select jurisdication say PA , type : Boating while searching for certifications)");
					  		Reporter.log("Step 3 : Go to certification details page (select jurisdication say PA , type :Boating while searching for certifications)");
						     
					  				Thread.sleep(1000);	
					  				SeleniumFunc.SelectValueFromDropdownList("id", "state", "Pennsylvania");
					  				//SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "state",44);					
					  				Thread.sleep(1000);
					  				
					  				SeleniumFunc.SelectValueFromDropdownList("id", "certification_type_id", "Boating");
					  				//SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "certification_type_id", 1);		
					  				editcer.SearchCM("Testing CertificationDetail");
					  				Thread.sleep(1000);
					  				editcer.ClickOnSearch();
					  				Thread.sleep(1000);
					  				editcer.ClickOnStudentNametab();			 
			 			
			 			System.out.println("Step 4 :Verify User is on certification details screen");
			 			Reporter.log("Step 4 : Verify User is on certification details screen");
					
			 				if(SeleniumFunc.IsElementPresent(editcer.EditTab))	 				
			  				{   
			  				
			  					System.out.println("Success !! User is on certification details screen");
			  					Reporter.log("Success !! User is on certification details screen"); 
			  				}
			  				else
			  				{
			  					System.out.println("Failure !! User is not on certification details screen ");
			  					Reporter.log("Failure !! User is not on certification details screen"); 
			  					AssertFailedCount++;
			  				}	
			 				Thread.sleep(2000);
			 				System.out.println("Step 5 : Click on Edit button");
				 			Reporter.log("Step 5 : Click on Edit button");
			 				editcer.ClickOnEdittab();
			 				Thread.sleep(2000);
			 				
			 				System.out.println("Step 6 :Verify User is redirected to Edit Student info page or not");
				 			Reporter.log("Step 6 : Verify User is redirected to Edit Student info page or not");
				 			
				 			String EditPage=SeleniumFunc.GetElementText("css", ".page-header>h1");
		  					if(EditPage.equals("Edit Student Information"))				 		  
			 				{   
		  						Thread.sleep(2000);
			  					System.out.println("Success !! User is redirected to Edit Student info page");
			  					Reporter.log("Success !! User is redirected to Edit Student info page"); 
			  				}
			  				else
			  				{
			  					System.out.println("Failure !! User is not redirected to Edit Student info page ");
			  					Reporter.log("Failure !! User is not redirected to Edit Student info page"); 
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
		     	 * Details Certification - Verify elements on Certificate info details section
		     	 */ 
		     	@Test
			 	public void CertificanInfoDetails() throws InterruptedException 
			 	{
		     		
		     		System.out.println("====" + "\n" +   "Test 7 : Certification Details - Verify elements on Certificate info details section." + "\n" +
				 			"====");
				     Reporter.log("====" + "\n" +  
				 						"Test 7 : Certification Details - Verify elements on Certificate info details section"  + "\n" +
						 	  "====");	
				     
				    	 int AssertFailedCount=0 ;
				    	 SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				    	 LoginPage login = new LoginPage(driver);				    	
				    	 EditCertificationPage editcer = new EditCertificationPage(driver);
				    	 DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);    	 	
				    	 
				    System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
				    Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
					
				    	 SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

				    	 System.out.println("Step 2 : Login with valid credentials");
			 		 		Reporter.log("Step 2 : Login with valid credentials"); 	
			 						
			 		 		 login.EnterUsername(Constants.CM_Username);
			 		 		 login.EnterPassword(Constants.CM_Password);
			 		 		 login.ClickOnLogInButton();
				    	 
				    System.out.println("Step 3 : Search Certifications and navigate to certification details page ");
				    Reporter.log("Step 3 : Search Certifications and navigate to certification details page"); 						
					     
					     Thread.sleep(1000);   
					     editcer.EnterStudentInfoforSErch("ResidenctRestrictionAtCM");
					     editcer.ClickOnSearch();
					     Thread.sleep(1000);   
					     editcer.ClickOnStudentNametab();	
					 
					System.out.println("Step 4 : Verify elements on Certificate info details section  ");
					Reporter.log("Step 4 : Verify elements on Certificate info details section ");
					     
						if(SeleniumFunc.IsElementPresent(DetailsPage.AgencyName))				 		  
						{   
							Thread.sleep(2000);
							System.out.println("Success !! Agency name text  is present");
							Reporter.log("Success !! Agency name text  is present"); 
						}
						else
						{
							System.out.println("Failure !! Agency name text is not  present ");
							Reporter.log("Failure !! Agency name text is not  present "); 
							AssertFailedCount++;
						}	
						
						if(SeleniumFunc.IsElementPresent(DetailsPage.CertificateCollectionName))				 		  
						{   
							 Thread.sleep(1000); 
							System.out.println("Success !! Certificate Collection name text  is present");
							Reporter.log("Success !! Certificate Collection name text  is present"); 
						}
						else
						{
							System.out.println("Failure !! Certificate Collection name text  is not present");
							Reporter.log("Failure !! Certificate Collection name text  is not present"); 
							AssertFailedCount++;
						}
						
						if(SeleniumFunc.IsElementPresent(DetailsPage.CertificateStatus))				 		  
						{   
		  				
							System.out.println("Success !! Certificate Status text  is present");
							Reporter.log("Success !! Certificate Status text is present"); 
						}
						else
						{
							System.out.println("Failure !! Certificate Status  text is not present ");
							Reporter.log("Failure !! Certificate Status text is not present "); 
							AssertFailedCount++;
						}
						
						if(SeleniumFunc.IsElementPresent(DetailsPage.CertificationDate))				 		  
						{   
		  				
							System.out.println("Success !! Certification Date Text  is present");
							Reporter.log("Success !! Certification Date Text is present"); 
						}
						else
						{
							System.out.println("Failure !! Certification Date  Text is not  present ");
							Reporter.log("Failure !! Certification Date Text is not  present "); 
							AssertFailedCount++;
						}
						
						if(SeleniumFunc.IsElementPresent(DetailsPage.CertificateID))				 		  
						{   
							 Thread.sleep(1000); 
							System.out.println("Success !! Certification ID Text  is present");
							Reporter.log("Success !! Certification ID Text is present"); 
						}
						else
						{
							System.out.println("Failure !! Certification ID  Text is not  present ");
							Reporter.log("Failure !! Certification ID Text is not  present "); 
							AssertFailedCount++;
						}
						
						if(SeleniumFunc.IsElementPresent(DetailsPage.CertificationOrigin))				 		  
						{   
		  				
							System.out.println("Success !! Certification Origin Text  is present");
							Reporter.log("Success !! Certification Origin Text is present"); 
						}
						else
						{
							System.out.println("Failure !! Certification Origin Text is not present ");
							Reporter.log("Failure !! Certification Origin Text is not present "); 
							AssertFailedCount++;
						}
						
						if(SeleniumFunc.IsElementPresent(DetailsPage.LastIssuedAt))				 		  
						{   
		  				
							System.out.println("Success !! Last Issued  Date Text  is present");
							Reporter.log("Success !! Last Issued  Date Text is present"); 
						}
						else
						{
							System.out.println("Failure !! Last Issued  Date Text is not present ");
							Reporter.log("Failure !! Last Issued  Date Text is not present "); 
							AssertFailedCount++;
						}
						
						if(SeleniumFunc.IsElementPresent(DetailsPage.CardPreview))				 		  
						{   
							 Thread.sleep(1000); 
							System.out.println("Success !! Certification Preview  is present");
							Reporter.log("Success !! Certification Preview is present"); 
						}
						else
						{
							System.out.println("Failure !! Certification Preview is not present ");
							Reporter.log("Failure !! Certification Preview is not present "); 
							AssertFailedCount++;
						}
						
						if(SeleniumFunc.IsElementPresent(DetailsPage.OrderReplacementCerti))				 		  
						{   
		  				
							System.out.println("Success !! Certification Preview  is present");
							Reporter.log("Success !! Certification Preview is present"); 
						}
						else
						{
							System.out.println("Failure !! Certification Preview is not present ");
							Reporter.log("Failure !! Certification Preview is not present "); 
							AssertFailedCount++;
						}
						
						if(SeleniumFunc.IsElementPresent(DetailsPage.ShowmoreInfo))				 		  
						{   
		  				
							System.out.println("Success !! Show More Information Text   is present");
							Reporter.log("Success !! Show More Information Textis present"); 
						}
						else
						{
							System.out.println("Failure !! Show More Information Text is not present ");
							Reporter.log("Failure !! Show More Information Text is not present "); 
							AssertFailedCount++;
						} 	
						 Thread.sleep(1000);   
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
		     	
		     
		     /*Certification page: test that on page load, individual activity log items' details are 
		     	/hidden and that you are able to view each that has a 'Details' button. Not every item 
		     	 * should have a 'Details' button.
		     */
		     	/* Test 8: 
		     	 * Details Certification - test that on page load, individual activity log items' details are hidden
		     	 */ 
		     	@Test
			 	public void IndividualActivityLogItems() throws InterruptedException 
			 	{
		     		
				    System.out.println("====" + "\n" +   "Test 8 : Certification Details - test that on page load, individual activity log items' details are hidden" + "\n" +
				 			"====");
				    Reporter.log("====" + "\n" +  "Test 8 : Certification Details - test that on page load, individual activity log items' details are hidden"  + "\n" +
						 	  "====");	
				     
				 		int AssertFailedCount=0 ;
				 		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				 		LoginPage login = new LoginPage(driver);				    	 
				 		EditCertificationPage editcer = new EditCertificationPage(driver);
				 		DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver); 
				    	
				 	System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
				    Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
					
				    	SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

				    System.out.println("Step 2 : Login with valid credentials");
				    Reporter.log("Step 2 : Login with valid credentials"); 	
			 						
				    	login.EnterUsername(Constants.CM_Username);
				    	login.EnterPassword(Constants.CM_Password);
				    	login.ClickOnLogInButton();
				    	 
				    System.out.println("Step 3 : Search Certifications and navigate to certification details page ");
				    Reporter.log("Step 3 : Search Certifications and navigate to certification details page"); 						
					     
				    	Thread.sleep(1000);   
				    	SeleniumFunc.SelectValueFromDropdownList("id", "state", "Missouri");
				    	Thread.sleep(1000);
				    	SeleniumFunc.SelectValueFromDropdownList("id", "certification_type_id", "Boating");
				    	Thread.sleep(1000);
				    	editcer.EnterStudentInfoforSErch("Ohlman, Justin K");
				    	editcer.ClickOnSearch();
				    	Thread.sleep(1000);   
				    	editcer.ClickOnStudentNametab();	
						
				    System.out.println("Step 4 : Verify user see a list of activity items below the activity log  and that on page load, those activity items are 'collapsed',  ");
					Reporter.log("Step 4 : Verify user see a list of activity items below the activity log  and that on page load, those activity items are 'collapsed',  ");	
						
					     Thread.sleep(1000);
					     if(SeleniumFunc.IsElementPresent(DetailsPage.Activity))
					     {
							System.out.println("Success !! List of activity items are displayed below the activity log.");
							Reporter.log("Success !! List of activity items are displayed below the activity log."); 
					     }
					     	else
					     	{
					     		System.out.println("Failure !!  List of activity items are not displayed below the activity log.");
					     		Reporter.log("Failure !!  List of activity items are not displayed below the activity log."); 
					     		AssertFailedCount++;
					     	}
						 if(!SeleniumFunc.IsElementPresent(DetailsPage.ActivityDetailShow))
						 {
						
							 System.out.println("Success !! Activity details items are 'collapsed',");
							 Reporter.log("Success !! Activity details items are 'collapsed',"); 
						 }
		   					else
		   					{
		   						System.out.println("Failure !!  Activity details items are not 'collapsed',");
		   						Reporter.log("Failure !!  Activity details items are not 'collapsed',"); 
		   						AssertFailedCount++;
		   					}
						
					System.out.println("Step 5 : Click on any action that has a 'Details' button/link and user see more details to the action slide down, below the link. ");
				    Reporter.log("Step 5 : Click on any action that has a 'Details' button/link and user see more details to the action slide down, below the link. ");
						
				    	Thread.sleep(1000);
						DetailsPage.ClickOnActivityDetail();
						Thread.sleep(1000);
						
						if(SeleniumFunc.IsElementPresent(DetailsPage.ActivityDetailShow))
						{
						
							System.out.println("Success !! User see more details to the action slide down, below the link.");
							Reporter.log("Success !! User see more details to the action slide down, below the link."); 
						}
		   					else
		   					{
		   						System.out.println("Failure !!  User is not able to see more details to the action slide down, below the link.");
		   						Reporter.log("Failure !!  User is not able to see more details to the action slide down, below the link."); 
		   						AssertFailedCount++;
		   					}
					
					System.out.println("Step 6 : Click the same detail button and verify same details that previously were revealed should be hidden again.");
				    Reporter.log("Step 6 : Click the same detail button and verify same details that previously were revealed should be hidden again.");
								
				    	Thread.sleep(1000);
				    	DetailsPage.ClickOnActivityDetail();
				    	Thread.sleep(1000);
								
				    	if(!SeleniumFunc.IsElementPresent(DetailsPage.ActivityDetailShow))
				    	{
								
				    		System.out.println("Success !! Activity details items are 'collapsed',");
							Reporter.log("Success !! Activity details items are 'collapsed',"); 
						}
		   					else
		   					{
		   						System.out.println("Failure !!  Activity details items are not 'collapsed',");
		   						Reporter.log("Failure !!  Activity details items are not 'collapsed',"); 
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
		     	 * Details Certification - Verify that Certifications details whose status is incomplete
		     	 */ 
		     	@Test
			     public void StatusIncomplete() throws InterruptedException
			     {
			     
			    	 System.out.println("====" + "\n" +
							"Test 9 : Details Certification - Verify that Certifications details whose status is incomplete" + "\n" +
				 			"====");
			    	 Reporter.log("====" + "\n" +  "Test 9 : Details Certification - Verify that Certifications details whose status is incomplete"  + "\n" +
						 	  "====");	
				     
				   	 		int AssertFailedCount=0 ;
				   	 		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				   	 		LoginPage login = new LoginPage(driver);				    	
				   	 		EditCertificationPage editcer = new EditCertificationPage(driver);
				   	 		DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);	 		
				    	 
				   	 System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
				   	 Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
								
				   	 		SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
							
				   	 System.out.println("Step 2 : Login with valid credentials");
				   	 Reporter.log("Step 2 : Login with valid credentials"); 	
			 						
				   	 		login.EnterUsername(Constants.CM_Username);
				   	 		login.EnterPassword(Constants.CM_Password);
				   	 		login.ClickOnLogInButton();
								
				   	 System.out.println("Step 3 : Go to certification details page ");
				   	 Reporter.log("Step 3 : Go to certification details page ");
						     
				   	 		Thread.sleep(1000);			
				   	 		editcer.SearchCM("Status Incomplete");
				   	 		editcer.ClickOnSearch();
				   	 		Thread.sleep(1000);
				   	 		editcer.ClickOnStudentNametab();			 
				   	 		Thread.sleep(2000);
									 
				   	 System.out.println("Step 4 : Click on Edit button,change status -- > Incomplete and verify user is able edit the status");
				   	 Reporter.log("Step 4 : Click on Edit button,change status -- > Incomplete and verify user is able edit the status");
				   	 		editcer.ClickOnEdittab();
				   	 		Thread.sleep(2000);
				   	 		SeleniumFunc.SelectValueFromDropdownList("id", "certification_status", "Incomplete");
			 				//SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "certification_status", 3);		
			 				Thread.sleep(1000);
			 				editcer.Getsubmitt();
			 				Thread.sleep(3000);		
			 				editcer.ClickOnuseaddress1();     
			 				Thread.sleep(2000);	
			 				editcer.ClickOnSaveBtn();
			 				Thread.sleep(2000);	
			 				String ActualUserName=SeleniumFunc.GetElementText("css", "div.alert.alert-success").trim();
			 				if(ActualUserName.equals("Certification was successfully updated."))
			 			    	{
			 			    		System.out.println("Success !!  changes has updated successfully");
			 			    		Reporter.log("Success !! changes has updated successfully"); 
			 			    	}
			 			    		else
			 			    				{
			 			    					System.out.println("Failure !!  changes is not updated successfully");
			 			    					Reporter.log("Failure !! changes is not updated successfully"); 
			 			    					AssertFailedCount++;
			 			    				}   
			 			    
			 			System.out.println("Step 5 : Observe Details page, Verify Certification status is incomplte and card can not be issued");
			 			Reporter.log("Step 5 : Observe Details page, Verify Certification status is incomplte and card can not be issued"); 
			 			 		
			 					String CertificateStatus=SeleniumFunc.GetElementText("css", "#cert-info > div:nth-child(5) > dl > dd > span");
			 					//String CertificateStatus=SeleniumFunc.GetElementText("css", "span.label.label");
			 					Thread.sleep(1000);
			 			 	    if(CertificateStatus.equals("Incomplete") && !SeleniumFunc.IsElementPresent( DetailsPage.OrderReplacementCertificate))
			 			 	    	{
			 			 	    		System.out.println("Success !!  Certification status is incomplte and card can not be issued");
			 			 	    		Reporter.log("Success !! Certification status is incomplte and card can not be issued"); 
			 			 	    	}
			 			 	    		else
			 			 	    			{
			 			 	    				System.out.println("Failure !!  certification status is not changed and card can  be issued");
			 			 	    				Reporter.log("Failure !! certification status is not changed and card can  be issued"); 
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
		     	 * Details Certification - Verify details for a failed certification
		     	 */ 
		     	@Test
			     public void DetailFailedCertification() throws InterruptedException
			     {
			     
			    	 System.out.println("====" + "\n" +
							"Test 10 : Details Certification - Verify details for a failed certification" + "\n" +
				 			"====");
				     Reporter.log("====" + "\n" +  "Test 10 : Details Certification - Verify details for a failed certification"  + "\n" +
						 	  "====");	
				     
				     		int AssertFailedCount=0 ;
				     		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				     		LoginPage login = new LoginPage(driver);				    	
				     		EditCertificationPage editcer = new EditCertificationPage(driver);
				     		DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);	 		
				    	 
				     System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
				     Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
								
			 				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
							
			 		System.out.println("Step 2 : Login with valid credentials");
			 		Reporter.log("Step 2 : Login with valid credentials"); 	
			 						
			 				login.EnterUsername(Constants.CM_Username);
			 				login.EnterPassword(Constants.CM_Password);
			 				login.ClickOnLogInButton();
								
			 		 System.out.println("Step 3 : Go to certification details page ");
			 		 Reporter.log("Step 3 : Go to certification details page ");
						     
			 		 		Thread.sleep(1000);			
			 		 		editcer.SearchCM("Timothy W Pack");
			 		 		editcer.ClickOnSearch();
			 		 		Thread.sleep(1000);
			 		 		editcer.ClickOnStudentNametab();			 
			 		 		Thread.sleep(2000);
									 
			 		 System.out.println("Step 4 : Click on Edit button,change status -- > fail and verify user is able edit the status");
			 		 Reporter.log("Step 4 : Click on Edit button,change status -- > fail and verify user is able edit the status");
			 		 		editcer.ClickOnEdittab();
			 		 		Thread.sleep(2000);
			 		 		SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "certification_status", 2);		
			 		 		Thread.sleep(1000);
			 		 		editcer.Getsubmitt();
			 		 		Thread.sleep(1000);		
			 		 		editcer.ClickOnuseaddress1();     
			 		 		Thread.sleep(1000);	
			 		 		editcer.ClickOnSaveBtn();
			 		 		Thread.sleep(1000);	
			 		 		String ActualUserName=SeleniumFunc.GetElementText("css", "div.alert.alert-success").trim();
			 		 		if(ActualUserName.equals("Certification was successfully updated."))
			 		 			{
			 		 				System.out.println("Success !!  changes has updated successfully");
			 		 				Reporter.log("Success !! changes has updated successfully"); 
			 		 			}
			 		 				else
			 		 				{
			 		 					System.out.println("Failure !!  changes is not updated successfully");
			 		 					Reporter.log("Failure !! changes is not updated successfully"); 
			 		 					AssertFailedCount++;
			 		 				}   
			 			    
			 		 	System.out.println("Step 5 : Observe Details page, Verify Certification status is failed and card can not be issued");
			 		 	Reporter.log("Step 5 : Observe Details page, Verify Certification status is failed and card can not be issued"); 
			 			 		     		
			 			 		String CertificateStatus=SeleniumFunc.GetElementText("css", ".label.label-important");
			 			 		
			 			 	 	if(CertificateStatus.equals("Failed") && !SeleniumFunc.IsElementPresent( DetailsPage.OrderReplacementCertificate))
			 			 	 		{
			 			 	 			System.out.println("Success !!  Certification status is Failed and card can not be issued");
			 			 	 			Reporter.log("Success !! Certification status is Failed and card can not be issued"); 
			 			 	 		}
			 			 	 		else
			 			 	 			{
			 			 	 				System.out.println("Failure !!  certification status is not changed and card can  be issued");
			 			 	 				Reporter.log("Failure !! certification status is not changed and card can  be issued"); 
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
		     	 * Details Certification -  Verify preview is displayed if status is passed.
		     	 */ 
		     	@Test
			     public void DetailPassedCertification() throws InterruptedException
			     {
			     
			    	 System.out.println("====" + "\n" +
							"Test 11 : Details Certification - Verify preview is displayed if status is passed" + "\n" +
				 			"====");
				     Reporter.log("====" + "\n" +  "Test 11 : Details Certification - Verify preview is displayed if status is passed"  + "\n" +
						 	  "====");	
				     
				     		int AssertFailedCount=0 ;
				     		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				     		LoginPage login = new LoginPage(driver);				    	
				     		EditCertificationPage editcer = new EditCertificationPage(driver);
				     		DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);	 		
				    	 
				     System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
				     Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
								
			 				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
							
			 		System.out.println("Step 2 : Login with valid credentials");
			 		Reporter.log("Step 2 : Login with valid credentials"); 	
			 						
			 				login.EnterUsername(Constants.CM_Username);
			 				login.EnterPassword(Constants.CM_Password);
			 				login.ClickOnLogInButton();
								
			 		 System.out.println("Step 3 : Go to certification details page ");
			 		 Reporter.log("Step 3 : Go to certification details page ");
						     
			 		 		Thread.sleep(1000);			
			 		 		editcer.SearchCM("Timothy W Pack");
			 		 		editcer.ClickOnSearch();
			 		 		Thread.sleep(1000);
			 		 		editcer.ClickOnStudentNametab();			 
			 		 		Thread.sleep(2000);
									 
			 		 System.out.println("Step 4 : Click on Edit button,change status -- > Passed and verify user is able edit the status");
			 		 Reporter.log("Step 4 : Click on Edit button,change status -- > passed and verify user is able edit the status");
			 		 		editcer.ClickOnEdittab();
			 		 		Thread.sleep(2000);
			 		 		SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "certification_status", 1);		
			 		 		Thread.sleep(1000);
			 		 		editcer.Getsubmitt();
			 		 		Thread.sleep(1000);		
			 		 		editcer.ClickOnuseaddress1();     
			 		 		Thread.sleep(1000);	
			 		 		editcer.ClickOnSaveBtn();
			 		 		Thread.sleep(1000);	
			 		 		String ActualUserName=SeleniumFunc.GetElementText("css", "div.alert.alert-success").trim();
			 		 		if(ActualUserName.equals("Certification was successfully updated."))
			 		 			{
			 		 				System.out.println("Success !!  changes has updated successfully");
			 		 				Reporter.log("Success !! changes has updated successfully"); 
			 		 			}
			 		 				else
			 		 				{
			 		 					System.out.println("Failure !!  changes is not updated successfully");
			 		 					Reporter.log("Failure !! changes is not updated successfully"); 
			 		 					AssertFailedCount++;
			 		 				}   
			 			    
			 		 	System.out.println("Step 5 : Observe Details page, Verify Ceritification date and cert. ID,Last issued at and certif. Preview should be displayed");
			 		 	Reporter.log("Step 5 : Observe Details page, Verify Ceritification date and cert. ID,Last issued at and certif. Preview should be displayed"); 
			 			 		     		
			 		 	if(SeleniumFunc.IsElementPresent(DetailsPage.CertificationDate))				 		  
						{   
			 		 		System.out.println("Success !!Certification Date Text  is present");
							Reporter.log("Success !! Certification Date Text is present"); 
						}
						else
						{
							System.out.println("Failure !! Certification Date  Text is not  present ");
							Reporter.log("Failure !! Certification Date Text is not  present "); 
							AssertFailedCount++;
						}
						if(SeleniumFunc.IsElementPresent(DetailsPage.CertificateID))				 		  
						{   
							System.out.println("Success !!Certification ID Text  is present");
							Reporter.log("Success !! Certification ID Text is present"); 
						}
						else
						{
							System.out.println("Failure !! Certification ID  Text is not  present ");
							Reporter.log("Failure !! Certification ID Text is not  present "); 
							AssertFailedCount++;
						}
						if(SeleniumFunc.IsElementPresent(DetailsPage.LastIssuedAt))				 		  
						{   
		  				
							System.out.println("Success !! Last Issued  Date Text  is present");
							Reporter.log("Success !! Last Issued  Date Text is present"); 
						}
						else
						{
							System.out.println("Failure !! Last Issued  Date Text is not present ");
							Reporter.log("Failure !! Last Issued  Date Text is not present "); 
							AssertFailedCount++;
						}
						
						if(SeleniumFunc.IsElementPresent(DetailsPage.CardPreview))				 		  
						{   
		  				
							System.out.println("Success !!Certification Preview  is present");
							Reporter.log("Success !! Certification Preview is present"); 
						}
						else
						{
							System.out.println("Failure !! Certification Preview is not present ");
							Reporter.log("Failure !! Certification Preview is not present "); 
							AssertFailedCount++;
						}
					    Thread.sleep(1000);   
			 					
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
		     	 * Details Certification - Verify functionality of 'Order replacement button
		     	 */ 
		     	@Test
			     public void OrderReplacementButton() throws InterruptedException
			     {
			     
			    	 System.out.println("====" + "\n" +
							"Test 12 : Details Certification -Verify functionality of 'Order replacement button" + "\n" +
				 			"====");
				     Reporter.log("====" + "\n" +  "Test 12 : Details Certification - Verify functionality of 'Order replacement button"  + "\n" +
						 	  "====");	
				     
				    	 int AssertFailedCount=0 ;
				    	 SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				    	 LoginPage login = new LoginPage(driver);				    	
				    	 EditCertificationPage editcer = new EditCertificationPage(driver);
				    	 DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);    	 		
				    	 
				    System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
				    Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
					
				    	 SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

				    	 System.out.println("Step 2 : Login with valid credentials");
					 		Reporter.log("Step 2 : Login with valid credentials"); 	
					 						
					 				login.EnterUsername(Constants.CM_Username);
					 				login.EnterPassword(Constants.CM_Password);
					 				login.ClickOnLogInButton();
				    	 
				    System.out.println("Step 3 : Search Certifications and verify User is on cert. details screen");
				    Reporter.log("Step 3 : Search Certifications and verify User is on cert. details screen"); 						
					     
					        Thread.sleep(1000);   
					        SeleniumFunc.SelectValueFromDropdownList("id", "certification_type_id", "Boating");
					        Thread.sleep(1000);
					        editcer.EnterStudentInfoforSErch("MCLAUGHLIN, ERIN");
					        editcer.ClickOnSearch();
			 		 		Thread.sleep(1000);
			 		 		editcer.ClickOnStudentNametab();			 
			 		 		Thread.sleep(2000);
					
			 		 		if(SeleniumFunc.IsElementPresent(editcer.EditTab))	 				
			  				{   
			  				
			  					System.out.println("Success !! User is on cert. details screen");
			  					Reporter.log("Success !! User is on cert. details screen"); 
			  				}
			  				else
			  				{
			  					System.out.println("Failure !! User is not on cert. details screen ");
			  					Reporter.log("Failure !! User is not on cert. details screen"); 
			  					AssertFailedCount++;
			  				}	
					     Thread.sleep(1000);
					     
			 				 System.out.println("Step 4 : Click  on Order Replacement Certificate and verify user has redirected to Order replacement page");
			 		 		Reporter.log("Step 4 : Click on Order Replacement Certificate and verify user has redirected to Order replacement page");
					    //Click on OrderReplacementCertificate 
					     DetailsPage.ClickOnOrderReplacementCerti();
					     
					     if(SeleniumFunc.IsElementPresent(DetailsPage.OrderReplacementCertificatePge))
					     {
							
					     	System.out.println("Success !! User has successfully redirected to Order replacement page");
					     	Reporter.log("Success !! User has successfully redirected to Order replacement page"); 
					     }
					     else
					     {
					     	System.out.println("Failure !! OrderReplacementCertificate button is not working");
					     	Reporter.log("Failure !! OrderReplacementCertificate button is not working"); 
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
		     	 * Details Certification - Verify functionality of 'Verify user can not edit Course type
		     	 */
		     	@Test
		        public void UserCanNotEditCourseType() throws InterruptedException
		        {
		        
		       	 System.out.println("====" + "\n" +
		   				"Test 13 : Details Certification - Verify user can not edit Course type" + "\n" +
		   	 			"====");
		   	     Reporter.log("====" + "\n" +  "Test 13 : Details Certification - Verify user can not edit Course type"  + "\n" +
		   			 	  "====");	
		   	     
		   	     	int AssertFailedCount=0 ;
		   	    	 SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		   	    	 LoginPage login = new LoginPage(driver);	    	
		   	    	 EditCertificationPage editcer = new EditCertificationPage(driver);
		   	    	 DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);    	 	
		   	    	
		   	    	 System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
					    Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
						
					    	 SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

					    	 System.out.println("Step 2 : Login with valid credentials");
						 		Reporter.log("Step 2 : Login with valid credentials"); 	
						 						
						 				login.EnterUsername(Constants.CM_Username);
						 				login.EnterPassword(Constants.CM_Password);
						 				login.ClickOnLogInButton();
					    	 
					    System.out.println("Step 3 : Search Certifications and verify User is on cert. details screen");
					    Reporter.log("Step 3 : Search Certifications and verify User is on cert. details screen"); 						
						     
						     Thread.sleep(1000);   
						     editcer.EnterStudentInfoforSErch("Order Replacement");
						     editcer.ClickOnSearch();
				 		 		Thread.sleep(1000);
				 		 		editcer.ClickOnStudentNametab();			 
				 		 		Thread.sleep(2000);
						
				 		 		if(SeleniumFunc.IsElementPresent(editcer.EditTab))	 				
				  				{   
				  				
				  					System.out.println("Success !! User is on cert. details screen");
				  					Reporter.log("Success !! User is on cert. details screen"); 
				  				}
				  				else
				  				{
				  					System.out.println("Failure !! User is not on cert. details screen ");
				  					Reporter.log("Failure !! User is not on cert. details screen"); 
				  					AssertFailedCount++;
				  				}	
						     
						     
						     System.out.println("Step 4 : Click on Edit button and Observe User can no longer select different Course type");
					 		 Reporter.log("Step 4 : Click on Edit button and Observe User can no longer select different Course type");
					 		        
					 		 		Thread.sleep(2000);
					 		 		editcer.ClickOnEdittab();
					 		 		Thread.sleep(1000);
						     
						 	if(!SeleniumFunc.IsElementPresent(DetailsPage.Course))				 		  
							{   
				 		 		System.out.println("Success !! User can no longer select different Course type");
								Reporter.log("Success !! User can no longer select different Course type"); 
							}
							else
							{
								System.out.println("Failure !! User can  select different Course type");
								Reporter.log("Failure !! User can select different Course type"); 
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
		     	
		     	
		     	/* Test 14: 
		     	 *  Certification Details - Verify elements under 'Add Notes' section
		     	 */
		     	
		     	@Test
		        public void UnderAddNotesSection() throws InterruptedException
		        {
		        
		       	 System.out.println("====" + "\n" +
		   				"Test 14 : Details Certification - Verify elements under 'Add Notes' section" + "\n" +
		   	 			"====");
		   	     Reporter.log("====" + "\n" +  "Test 14 : Details Certification - Verify elements under 'Add Notes' section"  + "\n" +
		   			 	  "====");	
		   	     
		   	    	 int AssertFailedCount=0 ;
		   	    	 SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		   	    	 LoginPage login = new LoginPage(driver);	    	
		   	    	 EditCertificationPage editcer = new EditCertificationPage(driver);
		   	    	 DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);    	 	
		   	    	
		   	    	 System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
					    Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
						
					    	 SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

					    	 System.out.println("Step 2 : Login with valid credentials");
						 		Reporter.log("Step 2 : Login with valid credentials"); 	
						 						
						 				login.EnterUsername(Constants.CM_Username);
						 				login.EnterPassword(Constants.CM_Password);
						 				login.ClickOnLogInButton();
					    	 
					    System.out.println("Step 3 : Search Certifications and verify User is on cert. details screen");
					    Reporter.log("Step 3 : Search Certifications and verify User is on cert. details screen"); 						
						     
						     Thread.sleep(1000);   
						     editcer.EnterStudentInfoforSErch("Order Replacement");
						     editcer.ClickOnSearch();
				 		 		Thread.sleep(1000);
				 		 		editcer.ClickOnStudentNametab();			 
				 		 		Thread.sleep(2000);
						
				 		 		if(SeleniumFunc.IsElementPresent(editcer.EditTab))	 				
				  				{   
				  				
				  					System.out.println("Success !! User is on cert. details screen");
				  					Reporter.log("Success !! User is on cert. details screen"); 
				  				}
				  				else
				  				{
				  					System.out.println("Failure !! User is not on cert. details screen ");
				  					Reporter.log("Failure !! User is not on cert. details screen"); 
				  					AssertFailedCount++;
				  				}	
						     Thread.sleep(1000);
		   		     
		   		System.out.println("Step 4 : Observe add notes section at bottom of page and verify Following elements should be present > Text box,Add Note button");
			   	Reporter.log("Step 4 : bserve add notes section at bottom of page and verify Following elements should be present > Text box,Add Note button");  
		     	
		     	      
			   		if(SeleniumFunc.IsElementPresent(DetailsPage.AddnoteSection))
			   		{
			   			Thread.sleep(2000);
			   			System.out.println("Success !!  Text box is present at bottom of page in Add note section.");
			   			Reporter.log("Success !! Text box is present at bottom of page in Add note section."); 
			   		}
			   		else
			   		{
			   			System.out.println("Failure !!  Text box is not present at bottom of page in Add note section.");
			   			Reporter.log("Failure !!  Text box is not present at bottom of page in Add note section."); 
			   			AssertFailedCount++;
			   		}
			   		if(SeleniumFunc.IsElementPresent(DetailsPage.AddnoteBtn))
		   			{
						
			   			System.out.println("Success !!  Add Note Button is present");
			   			Reporter.log("Success !!  Add Note Button is present"); 
		   			}
			   		else
			   		{
			   			System.out.println("Failure !!  Add Note Button is NOT present ");
			   			Reporter.log("Failure !!  Add Note Button is NOT present "); 
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
		     	
		     	/* Test 15: 
		     	 * Details Certification - Verify that user can add notes
		     	 */
		     	
		     	@Test
		        public void UserCanAddNotes() throws InterruptedException
		        {
		        
		     		System.out.println("====" + "\n" +
		     					"Test 15 : Details Certification - Verify functionality of 'Verify that user can add notes" + "\n" +
		     					"====");
		     		Reporter.log("====" + "\n" +  "Test 15 : Details Certification - Verify that user can add notes"  + "\n" +
		   			 	  "====");	
		   	     
		     				int AssertFailedCount=0 ;
		     				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		     				LoginPage login = new LoginPage(driver);	    	
		     				EditCertificationPage editcer = new EditCertificationPage(driver);
		     				DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);    	 	
	   	    	
		     		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		     		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
		     				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

		     		System.out.println("Step 2 : Login with valid credentials");
		     		Reporter.log("Step 2 : Login with valid credentials"); 	
					 						
		     				login.EnterUsername(Constants.CM_Username);
		     				login.EnterPassword(Constants.CM_Password);
		     				login.ClickOnLogInButton();
				    	 
		     		System.out.println("Step 3 : Search Certifications and verify User is on cert. details screen");
				    Reporter.log("Step 3 : Search Certifications and verify User is on cert. details screen"); 						
					     
				    		Thread.sleep(1000);   
				    		editcer.EnterStudentInfoforSErch("Order Replacement");
				    		editcer.ClickOnSearch();
			 		 		Thread.sleep(1000);
			 		 		editcer.ClickOnStudentNametab();			 
			 		 		Thread.sleep(2000);
					
			 		 		if(SeleniumFunc.IsElementPresent(editcer.EditTab))	 				
			  				{   
			 		 				System.out.println("Success !! User is on cert. details screen");
			 		 				Reporter.log("Success !! User is on cert. details screen"); 
			  				}
			  					else
			  						{
			  							System.out.println("Failure !! User is not on cert. details screen ");
			  							Reporter.log("Failure !! User is not on cert. details screen"); 
			  							AssertFailedCount++;
			  						}	
			 		 		Thread.sleep(1000);
		   		     
			 		 System.out.println("Step 4 : Enter note and click Add note button");
			 		 Reporter.log("Step 4 : Enter note and click Add note button");  
		     	
			 		 		DetailsPage.EnterAddnote("Add to cart Testing");
			 		 		DetailsPage.ClickOnAddNote();
			 		 		Thread.sleep(2000);
			 		 		
			 		 System.out.println("Step 5 : Verify Message 'Note was successfully created.' is displayed successfully and a note should be added along with username and time stamp");
			 		 Reporter.log("Step 5 : Verify Message 'Note was successfully created.' is displayed successfully and a note should be added along with username and time stamp");  
					   	
			 		 		Thread.sleep(1000); 
			 		 		String ActualUserName=SeleniumFunc.GetElementText("css", ".alert.alert-success").trim();
			 		 		if(ActualUserName.equals("Note was successfully created.") && SeleniumFunc.IsElementPresent(DetailsPage.AddNotePresent))
			 		 		{
			 		 				System.out.println("Success !!  Message 'Note was successfully created.' is displayed successfully and a note has added along with username and time stamp");
			 		 				Reporter.log("Success !! Message 'Note was successfully created.' is displayed successfully and a note has added along with username and time stamp"); 
			 		 		}
	 		 					else
	 		 						{
	 		 							System.out.println("Failure !!  Add note is not added.");
	 		 							Reporter.log("Failure !! Add note is not added."); 
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
		     	
		     		/* Test 16: 
		     	 * Details Certification -Test that user is not able to create a blank note
		     	 */
		     	
		     	@Test
		        public void UserIsNotCreateBlankNotes() throws InterruptedException
		        {
		        
		     		System.out.println("====" + "\n" +
		     				"Test 16 : Details Certification - Verify  user is not able to create a blank note" + "\n" +
		     				"====");
		     		Reporter.log("====" + "\n" +  "Test 16 : Details Certification - Verify user is not able to create a blank note"  + "\n" +
		     				"====");	
		   	     
		     				int AssertFailedCount=0 ;
		     				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		     				LoginPage login = new LoginPage(driver);	    	
		     				EditCertificationPage editcer = new EditCertificationPage(driver);
		     				DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);    	 	
	   	    	
		     		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		     		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
					
		     				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

		     		System.out.println("Step 2 : Login with valid credentials");
		     		Reporter.log("Step 2 : Login with valid credentials"); 	
					 						
		     				login.EnterUsername(Constants.CM_Username);
		     				login.EnterPassword(Constants.CM_Password);
		     				login.ClickOnLogInButton();
				    	 
		     		System.out.println("Step 3 : Search Certifications and verify User is on cert. details screen");
		     		Reporter.log("Step 3 : Search Certifications and verify User is on cert. details screen"); 						
					     
		     				Thread.sleep(1000);   
		     				editcer.EnterStudentInfoforSErch("Order Replacement");
		     				editcer.ClickOnSearch();
			 		 		Thread.sleep(1000);
			 		 		editcer.ClickOnStudentNametab();			 
			 		 		Thread.sleep(2000);
					
			 		 		if(SeleniumFunc.IsElementPresent(editcer.EditTab))	 				
			  					{   
			 		 				System.out.println("Success !! User is on cert. details screen");
			 		 				Reporter.log("Success !! User is on cert. details screen"); 
			  					}
			  						else
			  							{
			  								System.out.println("Failure !! User is not on cert. details screen ");
			  								Reporter.log("Failure !! User is not on cert. details screen"); 
			  								AssertFailedCount++;
			  							}	
			 		 		Thread.sleep(1000);
		   		     
			 		 System.out.println("Step 4 : Leave note text field as blank and click Add note button");
			 		 Reporter.log("Step 4 : Leave note text field as blank and click Add note button");  
		     	
		     	     		DetailsPage.EnterAddnote("");
		     	     		DetailsPage.ClickOnAddNote();
		     	     		Thread.sleep(2000);
		     	     		
		     	     System.out.println("Step 5 : Verify error message 'Note not created' is displayed or not.");
		     	     Reporter.log("Step 5 : Verify error message 'Note not created' is displayed or not.");  
		     	
		     	     		Thread.sleep(1000);		   		
		     	     		String ActualUserName=SeleniumFunc.GetElementText("css", "div.alert.alert-error").trim();
		     	     		if(ActualUserName.equals("Note not created.") )
		     	     			{
		     	     				System.out.println("Success !!  'Note not created' error message  is displayed.");
		     	     				Reporter.log("Success !!  'Note not created' error message  is displayed."); 
		     	     			}
		     	     			else
		     	     				{
		     	     				System.out.println("Failure !!  'Note not created' error message  is not displayed.");
		     	     				Reporter.log("Failure !!  'Note not created' error message  is not displayed."); 
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
		     	
		     	/* Test 17: 
		     	 * Details Certification - Verify all the activity done for a particular certification gets recorded
		     	 */	
		     	@Test
		        public void ActivityForAParticularCertification() throws InterruptedException
		        {
		        
		     		System.out.println("====" + "\n" +
		     			"Test 17 : Details Certification - Verify all the activity done for a particular certification gets recorded" + "\n" +
		   	 			"====");
		     		Reporter.log("====" + "\n" +  "Test 17 : Details Certification - Verify all the activity done for a particular certification gets recorded"  + "\n" +
		   			 	  "====");	
		   	     
		     				int AssertFailedCount=0 ;
		     				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		     				LoginPage login = new LoginPage(driver);	    	
		     				EditCertificationPage editcer = new EditCertificationPage(driver);
		     				DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);    	 	
			   	    	
		     		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		     		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
							
		     				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

		     		System.out.println("Step 2 : Login with valid credentials");
		     		Reporter.log("Step 2 : Login with valid credentials"); 	
							 						
		     				login.EnterUsername(Constants.CM_Username);
		     				login.EnterPassword(Constants.CM_Password);
		     				login.ClickOnLogInButton();
						    	 
		     		System.out.println("Step 3 : Search Certifications and verify User is on cert. details screen");
		     		Reporter.log("Step 3 : Search Certifications and verify User is on cert. details screen"); 						
							     
		     				Thread.sleep(1000);   
		     				editcer.EnterStudentInfoforSErch("Justin K Ohlman");
		     				editcer.ClickOnSearch();
		     				Thread.sleep(1000);
		     				editcer.ClickOnStudentNametab();			 
		     				Thread.sleep(2000);
							
		     				if(SeleniumFunc.IsElementPresent(editcer.EditTab))	 				
		     				{   		
		     					System.out.println("Success !! User is on cert. details screen");
		     					Reporter.log("Success !! User is on cert. details screen"); 
		     				}
		     					else
		     					{
		     						System.out.println("Failure !! User is not on cert. details screen ");
		     						Reporter.log("Failure !! User is not on cert. details screen"); 
		     						AssertFailedCount++;
					  			}	
					 		 Thread.sleep(1000);
					 System.out.println("Step 4 : Verify All the activities is displayed under Activity log along with user name and time stamp or not.");
					 Reporter.log("Step 4 : Verify All the activities is displayed under Activity log along with user name and time stamp or not.");  
							     
					 		if(SeleniumFunc.IsElementPresent(DetailsPage.Studentname1) && SeleniumFunc.IsElementPresent(DetailsPage.Time))
					 			{		   	        	
					 				Thread.sleep(1000);
					 				System.out.println("Success !!  User name and time stamp is displayed under Activity log");
					 				Reporter.log("Success !!  User name and time stamp is displayed under Activity log"); 
					 			}
					 			else
					 			{
					 				System.out.println("Failure !!  User name and time stamp is not displayed under Activity log");
					 				Reporter.log("Failure !!  User name and time stamp is not displayed under Activity log"); 
					 				AssertFailedCount++;
					 			}
		     	
					 		if(SeleniumFunc.IsElementPresent(DetailsPage.Activity))
					 			{		   	        									
					 				System.out.println("Success !!  Activities is displayed under Activity log");
					 				Reporter.log("Success !!  Activities is displayed under Activity log"); 
					 			}
					 			else
					 			{
					 				System.out.println("Failure !!   Activities is not displayed under Activity log");
					 				Reporter.log("Failure !!   Activities is not displayed under Activity log "); 
					 				AssertFailedCount++;
					 			}
					 System.out.println("Step 5 : Verify icons 1. For edit- a pencil icon, Ordering replacement certificate - recycle icon & Note > Comment icon is displayed or not.");
					 Reporter.log("Step 5 : Verify icons 1. For edit- a pencil icon, Ordering replacement certificate - recycle icon & Note > Comment icon is displayed or not.");  
					   	
					 		if(SeleniumFunc.IsElementPresent(DetailsPage.PencilIcon))
					 		{		   	        									
					 			System.out.println("Success !!  Pencil Icon  is displayed");
					 			Reporter.log("Success !!  Pencil Icon is displayed"); 
					 		}
					 		else
					 		{
					 			System.out.println("Failure !!  Pencil Icon is not displayed ");
					 			Reporter.log("Failure !!  Pencil Icon is not displayed "); 
					 			AssertFailedCount++;
					 		}
		     	  
					 		if(SeleniumFunc.IsElementPresent(DetailsPage.RecycleIcon))
					 		{		   	        									
					 			System.out.println("Success !! Ordering replacement Icon  is displayed");
					 			Reporter.log("Success !!  Ordering replacement Icon is displayed"); 
					 		}
					 		else
					 		{
					 			System.out.println("Failure !!  Ordering replacement Icon is not displayed ");
					 			Reporter.log("Failure !!  Ordering replacement Icon  is not displayed"); 
					 			AssertFailedCount++;
					 		}
		     	
					 		if(SeleniumFunc.IsElementPresent(DetailsPage.NoteIcon))
					 		{		   	        									
					 			System.out.println("Success !!  Note Icon  is displayed");
					 			Reporter.log("Success !!  Note Icon is displayed"); 
					 		}
					 		else
					 		{
					 			System.out.println("Failure !!  Note  Icon is not displayed");
					 			Reporter.log("Failure !!  Note Icon is not displayed"); 
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
		     	
		     	/* Test 18: 
		     	 * Details Certification - Verify that user can click on his name link and go to account details screen
		     	 */
		     	@Test
		        public void UserCanClickOnHisNameLink() throws InterruptedException
		        {
		        
		     	System.out.println("====" + "\n" +
		     			"Test 18 : Details Certification - Verify that user can click on his name link and go to account details screen" + "\n" +
		   	 			"====");
		       	Reporter.log("====" + "\n" +  "Test 18 : Details Certification - Verify that user can click on his name link and go to account details screen"  + "\n" +
		   			 	  "====");	
		   	     
		       			int AssertFailedCount=0 ;
		       			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		       			LoginPage login = new LoginPage(driver);	    	
		       			EditCertificationPage editcer = new EditCertificationPage(driver);
		       			DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);    	 	
	   	    	
		       	System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		       	Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
					
		       			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

		       	System.out.println("Step 2 : Login with valid credentials");
		       	Reporter.log("Step 2 : Login with valid credentials"); 	
					 						
		       			login.EnterUsername(Constants.CM_Username);
		       			login.EnterPassword(Constants.CM_Password);
		       			login.ClickOnLogInButton();
				    	 
		       	System.out.println("Step 3 : Search Certifications and verify User is on cert. details screen");
		       	Reporter.log("Step 3 : Search Certifications and verify User is on cert. details screen"); 						
					     
		       			Thread.sleep(1000);   
		       			editcer.EnterStudentInfoforSErch("ActivityForAParticular Certification");
		       			editcer.ClickOnSearch();
		       			Thread.sleep(1000);
		       			editcer.ClickOnStudentNametab();			 
		       			Thread.sleep(2000);
					
		       	System.out.println("Step 4 : Enter note and click Add note button");
			   	Reporter.log("Step 4 : Enter note and click Add note button");  
		     	
			   			DetailsPage.EnterAddnote("Add to cart Testing");
			   			DetailsPage.ClickOnAddNote();
		     	    
			   	System.out.println("Step 5 : Verify Message 'Note was successfully created.' is displayed successfully and a note should be added along with username and time stamp");
			   	Reporter.log("Step 5 : Verify Message 'Note was successfully created.' is displayed successfully and a note should be added along with username and time stamp");  
					   	
			   			Thread.sleep(1000); 
			   			String ActualUserName=SeleniumFunc.GetElementText("css", ".alert.alert-success").trim();
			   			if(ActualUserName.equals("Note was successfully created.") && SeleniumFunc.IsElementPresent(DetailsPage.AddNotePresent))
			   				{
			   					System.out.println("Success !!  Message 'Note was successfully created.' is displayed successfully and a note has added along with username and time stamp");
			   					Reporter.log("Success !! Message 'Note was successfully created.' is displayed successfully and a note has added along with username and time stamp"); 
			   				}
	 		 				else
	 		 				{
	 		 					System.out.println("Failure !!  Add note is not added.");
	 		 					Reporter.log("Failure !! Add note is not added."); 
	 		 					AssertFailedCount++;
	 		 				}	   
			   	System.out.println("Step 6 : Under the note clikc on the <username>link and verify User has redirected to account details screen or not ");
			   	Reporter.log("Step 6 : Under the note clikc on the <username>link and verify User has redirected to account details screen or not");  
	 		 		
			   			DetailsPage.ClickOnUserNameLinkOnNoteAdd();
			   			Thread.sleep(3000);
	      			
			   			if(SeleniumFunc.IsElementPresent(DetailsPage.UsernameMailId))
			   			{		   	        									
			   				System.out.println("Success !!  User has redirected to account details screen.");
			   				Reporter.log("Success !!  User has redirected to account details screen."); 
			   			}
			   			else
			   			{
			   				System.out.println("Failure !!  User is not redirected to account details screen");
			   				Reporter.log("Failure !!  User is not  redirected to account details screen"); 
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
		    
		    /* Test 19: 
		    * Details Certification - Verify validation message displayed in case of duplicate records
		    */
		   @Test
		   public void MessageDisplayedInCaseOfDuplicate() throws InterruptedException
		   {
		        
			   System.out.println("====" + "\n" +
		     			"Test 19 : Details Certification - Verify validation message displayed in case of duplicate records" + "\n" +
		   	 			"====");
			   Reporter.log("====" + "\n" +  "Test 19 : Details Certification - Verify validation message displayed in case of duplicate records"  + "\n" +
		   			 	  "====");	
		   	     
			   		int AssertFailedCount=0 ;
			   		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			   		LoginPage login = new LoginPage(driver);
			   		DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);    	 	
			   		EditCertificationPage editcer = new EditCertificationPage(driver);
		   	    	 
			   System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
			   Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
					
			   		SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
			   		
			   	System.out.println("Step 2 : Login with valid credentials");
			   	Reporter.log("Step 2 : Login with valid credentials"); 	
					 						
			   			login.EnterUsername(Constants.CM_Username);
			   			login.EnterPassword(Constants.CM_Password);
			   			login.ClickOnLogInButton();
		   	    	 
			   	System.out.println("Step 3 : Search Certifications and navigate to user page ");
			   	Reporter.log("Step 3 : Search Certifications and navigate to user page"); 		   			
		   		      	
			   			SeleniumFunc.SelectValueFromDropdownList("css","#state", "Pennsylvania");
			   			SeleniumFunc.SelectValueFromDropdownList("css","#certification_type_id", "Hunting");
			   			DetailsPage.EnterStudentInfoText("Stephen Porter");
			   			Thread.sleep(2000);
			   			DetailsPage.ClickOnSearchBtn();
			   			Thread.sleep(2000);
			   			
			   	System.out.println("Step 4 : Verify 2 results are displayed or not. ");
			   	Reporter.log("Step 4 : Verify 2 results are displayed or not. "); 
	     	   
			   			if(SeleniumFunc.IsElementPresent(DetailsPage.TwoElement))
			   			{		   	        									
			   				System.out.println("Success !!     2 results are displayed");
			   				Reporter.log("Success !!   2 results are displayed"); 
			   			}
			   			else
			   			{
			   				System.out.println("Failure !!   2 results are not displayed");
			   				Reporter.log("Failure !!   2 results are not displayed"); 
			   				AssertFailedCount++;
			   			}
	     	   
			   	System.out.println("Step 5 : Click on the 1st result and verify  user is navigated to a certification along with a message or not");
			   	Reporter.log("Step 5 : Click on the 1st result and verify  user is navigated to a certification along with a message or not"); 
	     	        
			   			DetailsPage.ClickOnFirstElement(); 
			   			//DetailsPage.ClickOnSecondElement();    	
			   			Thread.sleep(4000);
			   			if(SeleniumFunc.IsElementPresent(DetailsPage.CertificationWithMessage) && SeleniumFunc.IsElementPresent(editcer.EditTab))
			   			{		   	        									
			   				System.out.println("Success !!  user is navigated to a certification along with a message");
			   				Reporter.log("Success !!  user is navigated to a certification along with a message"); 
			   			}
			   			else
			   			{
			   				System.out.println("Failure !!  user is not navigated to a certification along with a message");
			   				Reporter.log("Failure !!  user is not navigated to a certification along with a message"); 
			   				AssertFailedCount++;
			   			}
		           
			   	System.out.println("Step 6 : Click on the 2nd result and verify  user is navigated to a certification with no message");
			   	Reporter.log("Step 6 : Click on the 2nd result and verify  user is navigated to a certification with no message"); 
 		      
			   			driver.navigate().back();
			   			Thread.sleep(5000);
			   			//DetailsPage.ClickOnFirstElement();  			  
			   			DetailsPage.ClickOnSecondElement(); 
			   			
			   			if(!SeleniumFunc.IsElementPresent(DetailsPage.CertificationWithMessage))
			   			{		   	        									
			   				System.out.println("Success !! Message Text is not present ");
			   				Reporter.log("Success!! Message Text is not present "); 
			   			}
			   			else
			   			{
			   				System.out.println("Failure !!Message Text is present");
			   				Reporter.log("Failure !!Message Text is present"); 
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
		   
		  
		    /* Test 20: 
		  * Details Certification - Verify "Last Issued At" date in CM always update
		  */ 
		   @Test
		   public void LastIssuedAtDateDetils() throws InterruptedException
		   {
		        
			   System.out.println("====" + "\n" +
		     			"Test 20 : Details Certification - Verify 'Last Issued At' date in CM always update" + "\n" +
		   	 			"====");
			   Reporter.log("====" + "\n" +  "Test 20 : Details Certification - Verify 'Last Issued At' date in CM always update"  + "\n" +
		   			 	  "====");	
		   	     
			   			int AssertFailedCount=0 ;
			   			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			   			LoginPage login = new LoginPage(driver);
			   			DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);    	 	
			   			EditCertificationPage editcer = new EditCertificationPage(driver);
	   	    	 
			   	System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
			   	Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
				
			   			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

			   	System.out.println("Step 2 : Login with valid credentials");
			   	Reporter.log("Step 2 : Login with valid credentials"); 	
				 						
				 				login.EnterUsername(Constants.CM_Username);
				 				login.EnterPassword(Constants.CM_Password);
				 				login.ClickOnLogInButton();
	   	    	 
 	      System.out.println("Step 3 : Search Certifications and navigate to user page ");
 	      Reporter.log("Step 3 : Search Certifications and navigate to user page"); 				
		   		      	
 	      		DetailsPage.EnterStudentInfoText("last issued date");
 	      		DetailsPage.ClickOnSearchBtn();
 	      		Thread.sleep(1000);
 	      		editcer.ClickOnStudentNametab();			 
 	      		Thread.sleep(2000);
	   	            
 	      System.out.println("Step 4 : Order a certification");
 	      Reporter.log("Step 4 : Order a certification");         
	   	            
 	      		DetailsPage.ClickOnOrderReplacementCertificate();
 	      		DetailsPage.EnterCreditCardNo("4111111111111111");
 	      		DetailsPage.EnterCreditCardExpDAte("02-2018");
 	      		DetailsPage.EnterCreditCardPhoneNo("258-963-9856");
 	      		SeleniumFunc.SelectValueFromDropdownListUsingIndex("css","NoOfCard",3);
 	      		Thread.sleep(1000);
 	      		DetailsPage.ClickSubmitPayment();
 	      		Thread.sleep(1000);  
 	      		DetailsPage.ReturnToCertificate();
 	      		Thread.sleep(2000);
		   	        
 	      System.out.println("Step 5 : Go to details and view last issued date and verify today's date");
 	      Reporter.log("Step 5 : Go to details and view last issued date and verify today's date"); 
 	      		Thread.sleep(1000);
 	      		String ActualUserName=SeleniumFunc.GetElementText("css", "#activity-stream ul li:nth-of-type(1) div:nth-of-type(2) p time");
 	      		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
 	      		//get current date time with Date()
 	      		Date date = new Date();
 	      		Thread.sleep(1000);
 	      		if(ActualUserName.substring(0,10).equals(dateFormat.format(date)))
 	      			{
 	      				System.out.println("Success !!  last issued date is today's date");
 	      				Reporter.log("Success !! last issued date is today's date"); 
 	      			}
 	      				else
 	      				{
 	      					System.out.println("Failure !!  last issued date is not today's date");
		 					Reporter.log("Failure !! last issued date is not today's date"); 
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
		   
		    /* Test 21: 
			  * Details Certification - Testing that quantity is added to a student's activity log when a card is ordered.
			  */ 
			   @Test
			   public void StudentActivitylogDetils() throws InterruptedException
			   {
			        
				   	System.out.println("====" + "\n" +
			     			"Test 21 : Details Certification - Testing that quantity is added to a student's activity log when a card is ordered." + "\n" +
			   	 			"====");
				   	Reporter.log("====" + "\n" +  "Test 21 : Details Certification - Testing that quantity is added to a student's activity log when a card is ordered."  + "\n" +
			   			 	  "====");	
			   	     		int AssertFailedCount=0 ;
			   	     		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			   	     		LoginPage login = new LoginPage(driver);
			   	     		DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);    	 	
			   	     		EditCertificationPage editcer = new EditCertificationPage(driver);
		   		
			   	    System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
			   	    Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
		   					SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

		   			System.out.println("Step 2 : Login with valid credentials");
		   			Reporter.log("Step 2 : Login with valid credentials"); 	
			 						
		   					login.EnterUsername(Constants.CM_Username);
		   					login.EnterPassword(Constants.CM_Password);
		   					login.ClickOnLogInButton();
			   	    	 
		   			System.out.println("Step 3 : Search Certifications and navigate to user page ");
		   			Reporter.log("Step 3 : Search Certifications and navigate to user page"); 				
			 				   		      	
		   					DetailsPage.EnterStudentInfoText("student's activity log");
		   					DetailsPage.ClickOnSearchBtn();
		   					Thread.sleep(2000);
		   					editcer.ClickOnStudentNametab();			 
		   					Thread.sleep(2000);
			     	     
		   			System.out.println("Step 4 : Verify user has naviagted to  particular student's certification page or not.");
		   			Reporter.log("Step 4 : Verify user has naviagted to  particular student's certification page or not.");         
		   					if(SeleniumFunc.IsElementPresent(editcer.EditTab))	 				
		   						{   		
		   						   Thread.sleep(1000);
		   							System.out.println("Success !! User is on cert. details screen");
		   							Reporter.log("Success !! User is on cert. details screen"); 
		   						}
		   						else
		   						{
		   							Thread.sleep(1000);
		   							System.out.println("Failure !! User is not on cert. details screen ");
		   							Reporter.log("Failure !! User is not on cert. details screen"); 
		   							AssertFailedCount++;
		   						}	
		   					Thread.sleep(1000);
		   			System.out.println("Step 5 : Order a certification");
		   			Reporter.log("Step 5 : Order a certification");         
			   	            
		   					Thread.sleep(1000);
		   					DetailsPage.ClickOnOrderReplacementCertificate();
		   					DetailsPage.EnterCreditCardNo("4111111111111111");
		   					DetailsPage.EnterCreditCardExpDAte("02-2018");
		   					DetailsPage.EnterCreditCardPhoneNo("258-963-9856");
		   					Thread.sleep(1000);
		   					SeleniumFunc.SelectValueFromDropdownList("css", "#quantity", "3 cards");
		   					Thread.sleep(1000);
		   					DetailsPage.ClickSubmitPayment();
		   					Thread.sleep(4000);  
		   					DetailsPage.ReturnToCertificate();
		   					Thread.sleep(4000);
				   	        
		   			System.out.println("Step 6 : On the right in the activity log, you should see an action that says 'Replacement Certificate(s) Ordered' with Date-time stamp & agency user name link�and 'Quantity: #' with your selected quantity below.�");
		   			Reporter.log("Step 6 : On the right in the activity log, you should see an action that says 'Replacement Certificate(s) Ordered' with Date-time stamp & agency user name link�and 'Quantity: #' with your selected quantity below.�"); 
		   					Thread.sleep(1000);
		   					String ActualUserName=SeleniumFunc.GetElementText("css", "#activity-stream ul li:nth-of-type(1) div:nth-of-type(2) p time");
		   					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		   					//get current date time with Date()
		   					Date date = new Date();
		   					Thread.sleep(1000);
		   					if(ActualUserName.substring(0,10).equals(dateFormat.format(date)))
		 	      				{
		 	      					System.out.println("Success !!  'Replacement Certificate(s) Ordered' with Date-time stamp is displayed");
		 	      					Reporter.log("Success !! 'Replacement Certificate(s) Ordered' with Date-time stamp is displayed"); 
		 	      				}
		 	      					else
		 	      					{
		 	      						System.out.println("Failure !!  'Replacement Certificate(s) Ordered' with Date-time stamp is not displayed");
		 	      						Reporter.log("Failure !! 'Replacement Certificate(s) Ordered' with Date-time stamp is not displayed"); 
		 	      						AssertFailedCount++;
		 	      					}   
		   					
		   					Thread.sleep(3000);
		   					DetailsPage.ClickOnReplacementDetail();
		   					Thread.sleep(2000);
		   					String Quantity=SeleniumFunc.GetElementText("css", "#details-1 dl dd");
		   					Thread.sleep(3000);
		   					if(Quantity.equals("3"))
		   					{		   	        									
		   						System.out.println("Success !!  Certificate 'Quantity: #' is match with user selected quantity");
		   						Reporter.log("Success !!  Certificate 'Quantity: #' is match with user selected quantity"); 
		   					}
		   					else
		   						{
		   							System.out.println("Failure !!  Certificate 'Quantity: #' is not match with user selected quantity");
		   							Reporter.log("Failure !!  Certificate 'Quantity: #' is not match with user selected quantity"); 
		   							AssertFailedCount++;
		   						}
		   					Thread.sleep(3000);
		   					String link=SeleniumFunc.GetAttributeValue("css", "#activity-stream ul li:nth-of-type(1) div:nth-of-type(2) p a", "href");
		   					Thread.sleep(2000);
		   					if(link.contains(Constants.ApplicationURL_CM+"/users"))
		   					{		   	        									
		   						System.out.println("Success !!  Agency user name link is present");
		   						Reporter.log("Success !!  Agency user name link is present"); 
		   					}
		   					else
		   						{
		   							System.out.println("Failure !!  Agency user name link is not present");
		   							Reporter.log("Failure !!  Agency user name link is not present"); 
		   							AssertFailedCount++;
		   						}
			       		
		   			System.out.println("Step 7 : Navigate Through the bypass payment  ");
		   			Reporter.log("Step 7 : Navigate Through the bypass payment");  
					   
		   					Thread.sleep(2000);
		   					DetailsPage.ClickOnOrderReplacementCertificate(); 
		   					DetailsPage.ClickOnBypassPayment();
		   					Thread.sleep(2000);
		   					SeleniumFunc.SelectValueFromDropdownList("css", "#historical_action_type_id", "Payment by Check");
		   					Thread.sleep(1000);
		   					SeleniumFunc.SelectValueFromDropdownList("css", "#quantity", "3 cards");
		   					DetailsPage.EnterReasonSelectText("Pay By Cash");		   	  			   		
		   					DetailsPage.ClickOnContinueButton();
					   	       
		   					Thread.sleep(2000);
		   					DetailsPage.ReturnToCertificate();
		   					Thread.sleep(2000);
		 	      		
		   			System.out.println("Step 8 : Verify an action that says 'Payment bypassed for replacement certificate' is present or not.");
		   			Reporter.log("Step 8 : Verify an action that says 'Payment bypassed for replacement certificate' is present or not."); 
						     
		   					String BypassedReplacement=SeleniumFunc.GetElementText("css", "#activity-stream ul li:nth-of-type(1) div:nth-of-type(2) h1").trim();
		   					System.out.println(BypassedReplacement);
				 	      		if(BypassedReplacement.equals("Payment bypassed for replacement certificate."))
				 	      		{		   	 
				 	      			Thread.sleep(1000);
				 	      			System.out.println("Success !!  'Payment bypassed for replacement certificate' is present");
				 	      			Reporter.log("Success !! 'Payment bypassed for replacement certificate' is present"); 
				 	      		}
				 	      			else
				 	      			{
				 	      				Thread.sleep(1000);
				 	      				System.out.println("Failure !!  'Payment bypassed for replacement certificate' is not present");
				 	      				Reporter.log("Failure !!  'Payment bypassed for replacement certificate' is not present"); 
				 	      				AssertFailedCount++;
				 	      			}
				 	System.out.println("Step 9 : Go to the Admin > Certification Activity page and verify Activity should be logged as 'Replacement Certificate ordered for <student name link>'.Agency User name link with <'n' hrs/days> ago. 'Quantity#'");
				 	Reporter.log("Step 9 : Go to the Admin > Certification Activity page and verify Activity should be logged as 'Replacement Certificate ordered for <student name link>'.Agency User name link with <'n' hrs/days> ago. 'Quantity#'"); 
						   
				 			Thread.sleep(2000);
				 			DetailsPage.ClickOnAdmin();
				 			Thread.sleep(1000);
				 			DetailsPage.ClickOnCertificationActivity();
				 			Thread.sleep(2000);
							
				 			WebElement Studentlink=	driver.findElement(By.cssSelector("#activity-stream ul li:nth-of-type(2) div:nth-of-type(2) h1 a"));
				 			if(SeleniumFunc.IsElementPresent(Studentlink))
				 			{		  
				 				Thread.sleep(1000);
				 				System.out.println("Success !! student name link is displayed");
				 				Reporter.log("Success !! student name link is displayed"); 
							}
				 			else
				 			{
				 				Thread.sleep(1000);
				 				System.out.println("Failure !!  student name link is not displayed");
							   	Reporter.log("Failure !!  student name link is not displayed"); 
							   	AssertFailedCount++;
							}
				 			
				 			Thread.sleep(1000);
				 			String ActualUserName1=SeleniumFunc.GetElementText("css", "#activity-stream ul li:nth-of-type(2) div:nth-of-type(2) p time");
				 			DateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
				 			//get current date time with Date()
				 			Date date1 = new Date();
				 			Thread.sleep(1000);
				 			if(ActualUserName1.substring(0,10).equals(dateFormat1.format(date1)))
				 			{
				 				Thread.sleep(1000);
				 				System.out.println("Success !!  'Replacement Certificate(s) Ordered' with Date-time stamp is displayed");
				 				Reporter.log("Success !! 'Replacement Certificate(s) Ordered' with Date-time stamp is displayed"); 
				 			}
				 			else
				 				{
				 					Thread.sleep(1000);
				 					System.out.println("Failure !!  'Replacement Certificate(s) Ordered' with Date-time stamp is not displayed");
				 					Reporter.log("Failure !! 'Replacement Certificate(s) Ordered' with Date-time stamp is not displayed"); 
				 					AssertFailedCount++;
				 				}   
				 			Thread.sleep(1000);
				 	      	DetailsPage.ClickOndetail1();
				 	      	Thread.sleep(1000);
				 	      	String Quantity1=SeleniumFunc.GetElementText("css", "#details-1>dl>dd");
				 	      	Thread.sleep(1000);
				 	      	if(Quantity1.equals("3"))
				 	      	{		   	
				 	      		Thread.sleep(1000);
				 	      		System.out.println("Success !!  Certificate 'Quantity: #' is match with user selected quantity");
				 	      		Reporter.log("Success !!  Certificate 'Quantity: #' is match with user selected quantity"); 
				 	      	}
				 	      	else
				 	      	{
				 	      		Thread.sleep(1000);
				 	      		System.out.println("Failure !!  Certificate 'Quantity: #' is not match with user selected quantity");
				 	      		Reporter.log("Failure !!  Certificate 'Quantity: #' is not match with user selected quantity"); 
				 	      		AssertFailedCount++;
							 }
				 	    
			   				if(link.contains(Constants.ApplicationURL_CM+"/users"))
				 	      	{		   	        									
				 	      		System.out.println("Success !!  Agency user name link is present");
				 	      		Reporter.log("Success !!  Agency user name link is present"); 
							 }
				 	      	else
				 	      	{
				 	      		System.out.println("Failure !!  Agency user name link is not present");
				 	      		Reporter.log("Failure !!  Agency user name link is not present"); 
				 	      		AssertFailedCount++;
							 }
		 		 
				 	      	Thread.sleep(2000);
				 	System.out.println("Step 10 : Go to the 'My Profile' page. and verify Activity should be logged as 'Replacement Certificate ordered for <student name link>' with Date-Time stamp. 'Quantity#'");
				 	Reporter.log("Step 10 : Go to the 'My Profile' page. and verify Activity should be logged as 'Replacement Certificate ordered for <student name link>' with Date-Time stamp. 'Quantity#''"); 
		 		 
				 			Thread.sleep(1000);
				 			DetailsPage.ClickOnUserProfile();
				 			Thread.sleep(1000);
				 			DetailsPage.ClickMyProfile();
				 			Thread.sleep(2000);
				
				 			WebElement Studentlink1=	driver.findElement(By.cssSelector("#activity-stream ul li:nth-of-type(2) div:nth-of-type(2) h1 a"));
				 			if(SeleniumFunc.IsElementPresent(Studentlink1))
				 			{		  
				 				System.out.println("Success !! student name link is displayed");
				 				Reporter.log("Success !! student name link is displayed"); 
				 			}
				 			else
				 			{
				 				System.out.println("Failure !!  student name link is not displayed");
				 				Reporter.log("Failure !!  student name link is not displayed"); 
				 				AssertFailedCount++;
				 			}
		 
				 			Thread.sleep(1000);
				 			String ActualUserName11=SeleniumFunc.GetElementText("css", "#activity-stream ul li:nth-of-type(2) div:nth-of-type(2) p time");
				 			//get current date time with Date()
				 			Thread.sleep(1000);
				 			if(ActualUserName11.substring(0,10).equals(dateFormat1.format(date1)))
				 			{
				 				System.out.println("Success !!  'Replacement Certificate(s) Ordered' with Date-time stamp is displayed");
				 				Reporter.log("Success !! 'Replacement Certificate(s) Ordered' with Date-time stamp is displayed"); 
				 			}
	 	      					else
	 	      					{
	 	      						System.out.println("Failure !!  'Replacement Certificate(s) Ordered' with Date-time stamp is not displayed");
	 	      						Reporter.log("Failure !! 'Replacement Certificate(s) Ordered' with Date-time stamp is not displayed"); 
	 	      						AssertFailedCount++;
	 	      					}   
				 			Thread.sleep(1000);
				 			DetailsPage.ClickOnDetailsOnProfilePage1();
				 			Thread.sleep(1000);
				 			String Quantity11=SeleniumFunc.GetElementText("css", "#details-1>dl>dd");
				 			Thread.sleep(1000);
				 			if(Quantity11.equals("3"))
				 			{		   	        									
				 				System.out.println("Success !!  Certificate 'Quantity: #' is match with user selected quantity");
				 				Reporter.log("Success !!  Certificate 'Quantity: #' is match with user selected quantity"); 
				 			}
				 				else
				 				{
				 					System.out.println("Failure !!  Certificate 'Quantity: #' is not match with user selected quantity");
				 					Reporter.log("Failure !!  Certificate 'Quantity: #' is not match with user selected quantity"); 
				 					AssertFailedCount++;
				 				}
				 			Thread.sleep(2000);
		 		 
		 		 
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
