package products.CM;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.EditCertificationPage;
import pageFactory.CM.LoginPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class EditCertificationTest {
	
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
	      * edit  Certification - Verify user can navigate to Edit certification screen
	      */ 
	     @Test
	     public void NavigatetoEditcertification() throws InterruptedException
	     {
	    System.out.println("====" + "\n" +	
	    		"Test 1 : Edit  Certification - Verify user can navigate to Edit certification screen" + "\n" +
	    			 "====");
	    	 
	    Reporter.log("====" + "\n" +
	    		"Test 1 : Edit  Certification - Verify user can navigate to Edit certification screen"  + "\n" +	  
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
		
	    System.out.println("Step 3 : Enter the valid data for mandatory field and click on Search button.");
	    Reporter.log("Step 3 : Enter the valid data for mandatory field and click on Search button."); 
	    		
	    	Thread.sleep(1000);
	    	SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "state", 1);	
	    	Thread.sleep(1000);
	    	SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "certification_type_id", 1);
	    	Thread.sleep(1000);
	    //Click on search 
	    	editcer.ClickOnSearch();
			
	    System.out.println("Step 4 : Click on the first certification record that shows up");
	    Reporter.log("Step 4 : Click on the first certification record that shows up"); 
		         
	     	editcer.ClickOnStudentNametab();
	     	Thread.sleep(2000);
			      
	     System.out.println("Step 5 : Verify presence of 'Edit' link  ");
	     Reporter.log("Step 5 : Verify  presence of 'Edit' link ");
	     
	     //Verifying presence of Edit Tab
	     	if(SeleniumFunc.IsElementPresent(editcer.EditTab))
	     	{
	     		System.out.println("Success !! Edit link is present");
	     		Reporter.log("Success !! Edit link is present"); 
		   	}
	     	else
	     	{
	     		System.out.println("Failure !! Edit link is NOT present ");
	     		Reporter.log("Failure !! Edit link is NOT present "); 
	     		AssertFailedCount++;
		     }			
		     	
		  System.out.println("Step 6 : Click on Edit Link");
		  Reporter.log("Step 6 : Click on Edit Link");
			
		  	editcer.ClickOnEdittab();
				   
		  System.out.println("Step 7 : Verify User has redirected to Edit Student Information page successfully or not.");
		  Reporter.log("Step 7 : Verify User has redirected to Edit Student Information page successfully or not.");
			 	
		 	//Verifying presence of Student name page
		  	if(SeleniumFunc.IsElementPresent(editcer.StudentInfoPage))
		  	{
		  		System.out.println("Success !! User has redirected to Edit Student Information page");
		  		Reporter.log("Success !! User has redirected to Edit Student Information page"); 
			}
		  	else
		  	{
		  		System.out.println("Failure !! User is not redirected to Edit Student Information page");
		  		Reporter.log("Failure !! User is not redirected to Edit Student Information page"); 
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
	   * edit  Certification - Verify UI of 'Edit Student Information' page
	  */		 	
	 @Test
	 public void VerifyUIofEditStudent() throws InterruptedException
	  { 
		 System.out.println("====" + "\n" +
					"Test 2 : Edit  Certification - Verify UI of 'Edit Student Information' page" + "\n" +
		 			"====");
		 Reporter.log("====" + "\n" +
		 			  "Test 2 : Edit  Certification - Verify UI of 'Edit Student Information' page"  + "\n" +
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
			
		 System.out.println("Step 3 : Enter the valid data in mandatory field for Search Certifications functionality and click on Search button.");
		 Reporter.log("Step 3 : Enter the valid data in mandatory field for Search Certifications functionality and click on Search button."); 
		 
		 	Thread.sleep(1000);
		 	SeleniumFunc.SelectValueFromDropdownList("id", "state", "Pennsylvania"); 
		 	Thread.sleep(1000);
		 	SeleniumFunc.SelectValueFromDropdownList("id", "certification_type_id", "Boating");			 
		 	Thread.sleep(2000);
		 	editcer.ClickOnSearch();
		   	 
		 System.out.println("Step 4 : Click on the first certification record that shows up");
		 Reporter.log("Step 4 : Click on the first certification record that shows up"); 
		 
		 	editcer.ClickOnStudentNametab();
		   	
		 System.out.println("Step 5 : Verify presence of 'Edit' link  ");
		 Reporter.log("Step 5 : Verify  presence of 'Edit' link ");					
			
			 if(SeleniumFunc.IsElementPresent(editcer.EditTab))		   			
			 {
				 Thread.sleep(1000);	   
				 System.out.println("Success !! Edit link  is present");
				 Reporter.log("Success !! Edit link is present"); 
			 }
			 else
			 {
				 System.out.println("Failure !! Edit link is NOT present ");
				 Reporter.log("Failure !! Edit link is NOT present "); 
				 AssertFailedCount++;
			 }
		 System.out.println("Step 6 : Click on 'Edit' link.");
		 Reporter.log("Step 6 : Click on 'Edit' link.");  				 
		   	
		 		editcer.ClickOnEdittab();	     
		 		Thread.sleep(1000);
		 System.out.println("Step 7 : Observe elements on Edit Student Information page");
	     Reporter.log("Step 7 : Observe elements on Edit Student Information page"); 		
					
		   
	   		//Verifying presence of Name field
	   			if(SeleniumFunc.IsElementPresent(editcer.Firstname))
	   			{
	   				Thread.sleep(1000);
	   			   System.out.println("Success !! Name Textbox is present");
	   			   Reporter.log("Success !! Name Textbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Name Textbox is NOT present ");
	   				Reporter.log("Failure !! Name Textbox is NOT present "); 
	   				AssertFailedCount++;
	   			}
						
	   		//Verifying presence of  middle Name field
	   			if(SeleniumFunc.IsElementPresent(editcer.MiddleName))
	   			{
			   
	   				System.out.println("Success !! Middle  Name Textbox is present");
	   				Reporter.log("Success !!  Middle Name Textbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !!  Middle Name Textbox is NOT present ");
	   				Reporter.log("Failure !!Middle Name Textbox is NOT present "); 
	   				AssertFailedCount++;
	   			}
						
	   		//Verifying presence of  Last  Name field
	   			if(SeleniumFunc.IsElementPresent(editcer.Lastname))
	   			{
	   				Thread.sleep(1000);	
	   				System.out.println("Success !! Last Name Textbox is present");
	   				Reporter.log("Success !! Last Name Textbox is present"); 
	   			}
	   			else
	   			{
	   					System.out.println("Failure !!Last  Name Textbox is NOT present ");
	   					Reporter.log("Failure !! Last  Name Textbox is NOT present "); 
	   					AssertFailedCount++;
	   			}
		   
	   			//Verifying presence of  Suffix   field
	   			if(SeleniumFunc.IsElementPresent(editcer.Suffix))
	   			{
							
	   				System.out.println("Success !! Suffix Textbox is present");
	   				Reporter.log("Success !! Suffix Textbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Suffix Textbox is NOT present ");
	   				Reporter.log("Failure !! Suffix Textbox is NOT present "); 
	   				AssertFailedCount++;
	   			}
						
	   			//Verifying presence of  Date Of Birth   field
	   			if(SeleniumFunc.IsElementPresent(editcer.DOB))
	   			{
	   				Thread.sleep(1000);		
	   				System.out.println("Success !! DOB Textbox is present");
	   				Reporter.log("Success !! DOB Textbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! DOB Textbox is NOT present ");
	   				Reporter.log("Failure !! DOB Textbox is NOT present "); 
	   				AssertFailedCount++;
	   			}	
															
	   			//Verifying presence of Email field
	   			if(SeleniumFunc.IsElementPresent(editcer.Email))
	   			{
							
	   				System.out.println("Success !! Email Textbox is present");
	   				Reporter.log("Success !! Email Textbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Email Textbox is NOT present ");
	   				Reporter.log("Failure !! Email Textbox is NOT present "); 
	   				AssertFailedCount++;
	   			}
	    System.out.println("Step 8 : Observe Physical address section");
	    Reporter.log("Step 8 : Observe Physical address section"); 											
	   			//Verifying Physical Address Text is present
	   			
	   			if(SeleniumFunc.IsElementPresent(editcer.TestPresent))
	   			{
	   				Thread.sleep(1000);		
	   				System.out.println("Success !! Physical Address Text is present");
	   				Reporter.log("Success !! Physical Address Text is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Physical Address Text is NOT present ");
	   				Reporter.log("Failure !! Physical Address Text is NOT present "); 
	   				AssertFailedCount++;
	   			}
	   			
	   			//Verifying presence  other info of Text
	   			if(SeleniumFunc.IsElementPresent(editcer.OtherInfo))
	   			{
			   
	   				System.out.println("Success !! Other info Text is present");
	   				Reporter.log("Success !!  Other info Text is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !!  Other info Text is NOT present ");
	   				Reporter.log("Failure !!  Other info Text is NOT present "); 
	   				AssertFailedCount++;
	   			}
	   			
	   			//Verifying presence of 'Continue Button' button
	   			if(SeleniumFunc.IsElementPresent(editcer.Submitt))
	   			{
	   				Thread.sleep(1000);		
	   				System.out.println("Success !! Continue button is present");
	   				Reporter.log("Success !! Continue button is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Continue button is NOT present ");
	   				Reporter.log("Failure !! Continue button is NOT present "); 
	   				AssertFailedCount++;
	   			}
						
	         //Observe Physical address section
						
	 		// Verifying presence of country  select list
						
	   			//Verifying presence of country 
	   			if(SeleniumFunc.IsElementPresent(editcer.Country))
	   			{
			   
	   				System.out.println("Success !! Country Select list  is present");
	   				Reporter.log("Success !! Country Select list is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Country Select list is NOT present ");
	   				Reporter.log("Failure !! Country Select list is NOT present "); 
	   				AssertFailedCount++;
	   			}		
	   			
	   			//Verifying presence of Street Address  field
	   			if(SeleniumFunc.IsElementPresent(editcer.StreetAddress))
	   			{
	   				Thread.sleep(1000);
	   				System.out.println("Success !! Street Address Textbox is present");
	   				Reporter.log("Success !! Street Address Textbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Street Address Textbox is NOT present ");
	   				Reporter.log("Failure !! Street Address Textboxis NOT present "); 
	   				AssertFailedCount++;
	   			}
	   			
	   			//Verifying presence of  Address 2   field
	   			if(SeleniumFunc.IsElementPresent(editcer.caddress))
	   			{
			   
	   				System.out.println("Success !! Street Address 2 Textbox is present");
	   				Reporter.log("Success !! Street Address 2 Textbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Street Address 2 Textbox is NOT present ");
	   				Reporter.log("Failure !! Street Address 2 Textbox is NOT present "); 
	   				AssertFailedCount++;
	   			}
	   			
	   			//Verifying presence of City  field
	   			if(SeleniumFunc.IsElementPresent(editcer.certificationcity))
	   			{
			   
	   				System.out.println("Success !! City Textbox is present");
	   				Reporter.log("Success !! City Textbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! City Textbox is NOT present ");
	   				Reporter.log("Failure !! City Textboxis NOT present "); 
	   				AssertFailedCount++;
	   			}
	   			
	   			//Verifying presence of State 
	   			if(SeleniumFunc.IsElementPresent(editcer.State))
	   			{
			   
	   				System.out.println("Success !! State Select list  is present");
	   				Reporter.log("Success !! State Select list is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! State Select list is NOT present ");
	   				Reporter.log("Failure !! State Select list is NOT present "); 
	   				AssertFailedCount++;
	   			}		
	   			
	   			//Verifying presence of Zip  field
	   			if(SeleniumFunc.IsElementPresent(editcer.certificationzip))
	   			{
	   				Thread.sleep(1000);	
	   				System.out.println("Success !! Zip Textbox is present");
	   				Reporter.log("Success !! Zip Textbox is present"); 
	   			}	
	   			else
	   			{
	   				System.out.println("Failure !! Zip Textbox is NOT present ");
	   				Reporter.log("Failure !! Zip Textboxis NOT present "); 
	   				AssertFailedCount++;
	   			}
						
	   			//Verifying presence of Phone no  field
	   			if(SeleniumFunc.IsElementPresent(editcer.phoneno))
	   			{
							
	   				System.out.println("Success !! Phone no Textbox is present");
	   				Reporter.log("Success !! Phone no Textbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Phone no Textbox is NOT present ");
	   				Reporter.log("Failure !! Phone no Textboxis NOT present "); 
	   				AssertFailedCount++;
	   			}
								
	   			//Verifying presence of 'Has different mailing address? ' check box
	   			if(SeleniumFunc.IsElementPresent(editcer.HideMailingaddress))
	   			{
							
	   				System.out.println("Success !!'Has different mailing address? ' checkbox is present");
	   				Reporter.log("Success !!'Has different mailing address? ' checkbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! 'Has different mailing address? ' checkbox is NOT present ");
	   				Reporter.log("Failure !! 'Has different mailing address? ' checkbox is NOT present "); 
	   				AssertFailedCount++;
	   			}
						
						
	   			System.out.println("Step 9 : Observe Other info section");
	   		   Reporter.log("Step 9 : Observe Other info section");
	   		   
	   		   
	   			//Verifying presence of Other info Text
	   		   if(SeleniumFunc.IsElementPresent(editcer.OtherInfo))
	   			{
			   
	   				System.out.println("Success !! Other Info title is present");
	   				Reporter.log("Success !! Other Info title is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Other Info title is NOT present ");
	   				Reporter.log("Failure !! Other Info title is NOT present "); 
	   				AssertFailedCount++;
	   			}
											
						
	   			//Verifying presence of Status
	   			if(SeleniumFunc.IsElementPresent(editcer.OtherStatus))
	   			{
	   				Thread.sleep(1000);
	   				System.out.println("Success !! Status select list  is present");
	   				Reporter.log("Success !! Status select list is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Status select list is NOT present ");
	   				Reporter.log("Failure !! Status select list is NOT present "); 
	   				AssertFailedCount++;
	   			}
	   			
	   			//Verifying presence of  Certification Date   field
	   			if(SeleniumFunc.IsElementPresent(editcer.certificationDate))
	   			{
							
	   				System.out.println("Success !! Certification Date Textbox is present");
	   				Reporter.log("Success !! Certification DateTextbox is present"); 
						}
	   			else
	   			{
	   					System.out.println("Failure !! Certification Date Textbox is NOT present ");
	   					Reporter.log("Failure !! Certification Date Textbox is NOT present "); 
	   					AssertFailedCount++;
	   			}
						
						
	   			//Verifying presence of  Last Issue Date   field
	   			if(SeleniumFunc.IsElementPresent(editcer.IssueDate))
	   			{
							
	   				System.out.println("Success !! Last Issue Datee Textbox is present");
	   				Reporter.log("Success !! Last Issue DateTextbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Last Issue Date Textbox is NOT present ");
	   				Reporter.log("Failure !! Last Issue Date Textbox is NOT present "); 
	   				AssertFailedCount++;
	   			}
	   			//Verifying presence of  Gender text  
	   			if(SeleniumFunc.IsElementPresent(editcer.Genderother))
	   			{
			   
	   				System.out.println("Success !! Gender Textbox is present");
	   				Reporter.log("Success !! GenderTextbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Gender Textbox is NOT present ");
	   				Reporter.log("Failure !! Gender Textbox is NOT present "); 
	   				AssertFailedCount++;
	   			}
						
	   	/*		//Verifying presence of  Height Text  
	   			if(SeleniumFunc.IsElementPresent(editcer.Heightother))
	   			{
			   
	   				System.out.println("Success !! Height Textbox is present");
	   				Reporter.log("Success !! Height Textbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Height Textbox is NOT present ");
	   				Reporter.log("Failure !! Height Textbox is NOT present "); 
	   				AssertFailedCount++;
	   			}
						
	   			//Verifying presence of  Weight Text  
	   			if(SeleniumFunc.IsElementPresent(editcer.Weightother))
	   			{
	   				Thread.sleep(1000);
	   				System.out.println("Success !! Weight Textbox is present");
	   				Reporter.log("Success !! Weight Textbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Weight Textbox is NOT present ");
	   				Reporter.log("Failure !! Weight Textbox is NOT present "); 
	   				AssertFailedCount++;
	   			}
		   
	   			//Verifying presence of  Eye Color Text  
	   			if(SeleniumFunc.IsElementPresent(editcer.EyeColor))
	   			{
			   
	   				System.out.println("Success !! Eye Color Textbox is present");
	   				Reporter.log("Success !! Eye Color Textbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Eye Color Textbox is NOT present ");
	   				Reporter.log("Failure !! Eye Color Textbox is NOT present "); 
			 	 AssertFailedCount++;
	   			}
						
	   			//Verifying presence of  Hair color Text  
	   			if(SeleniumFunc.IsElementPresent(editcer.HairColor))
	   			{
							
	   				System.out.println("Success !! Hair color Textbox is present");
	   				Reporter.log("Success !! Hair color Textbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Hair color Textbox is NOT present ");
	   				Reporter.log("Failure !! Hair color Textbox is NOT present "); 
	   				AssertFailedCount++;
	   			}*/
		   
	   			
	   			
	   System.out.println("Step 10: Verify the functionality of  ' Has different mailing address' option  ");
	   Reporter.log("Step 10 : Verify the functionality of  ' Has different mailing address' option "); 		
					
	   			Thread.sleep(2000);  
	   			editcer.clickOnHideMailingaddress();
	   			Thread.sleep(2000);  
	   			// Mailing Address
				        
	   			//Verifying presence of country 
	   			if(SeleniumFunc.IsElementPresent(editcer.countryid))
	   			{
			   
	   				System.out.println("Success !! country select list  is present");
	   				Reporter.log("Success !! country select list is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! country select list is NOT present ");
	   				Reporter.log("Failure !! country select list is NOT present "); 
	   				AssertFailedCount++;
	   			}					
	   			//Verifying presence of Street Address  field
	   			if(SeleniumFunc.IsElementPresent(editcer.StreetAddress))
	   			{
			   
	   				System.out.println("Success !! Street address Textbox is present");
	   				Reporter.log("Success !! Street address Textbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Street address Textbox is NOT present ");
	   				Reporter.log("Failure !! Street address Textboxis NOT present "); 
	   				AssertFailedCount++;
	   			}
	   			//Verifying presence of  Address 2   field
	   			if(SeleniumFunc.IsElementPresent(editcer.Address2))
	   			{
			   
	   				System.out.println("Success !! Street address 2 Textbox is present");
	   				Reporter.log("Success !! Street address 2 Textbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Street address 2 Textbox is NOT present ");
	   				Reporter.log("Failure !! Street address 2 Textbox is NOT present "); 
	   				AssertFailedCount++;
	   			}
	   			//Verifying presence of City  field
	   			if(SeleniumFunc.IsElementPresent(editcer.Citymailing))
	   			{
			   	
	   				System.out.println("Success !! city Textbox is present");
	   				Reporter.log("Success !! city Textbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! City Textbox is NOT present ");
	   				Reporter.log("Failure !! City Textboxis NOT present "); 
	   				AssertFailedCount++;
	   			}
	   			//Verifying presence of State 
	   			if(SeleniumFunc.IsElementPresent(editcer.SelectMailingstate))
	   			{
	   				Thread.sleep(1000);
	   				System.out.println("Success !! State select list  is present");
	   				Reporter.log("Success !! State Select list is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! State Select list is NOT present ");
	   				Reporter.log("Failure !! State Select list is NOT present "); 
	   				AssertFailedCount++;
	   			}			
	   			//Verifying presence of Zip  field
	   			if(SeleniumFunc.IsElementPresent(editcer.Zipmailing))
	   			{
							
	   				System.out.println("Success !! Zip Textbox is present");
	   				Reporter.log("Success !! Zip Textbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Zip Textbox is NOT present ");
	   				Reporter.log("Failure !! Zip Textboxis NOT present "); 
	   				AssertFailedCount++;
	   			}
						
	   			//Verifying presence of Phone no  field
	   			if(SeleniumFunc.IsElementPresent(editcer.Phonemailing))
	   			{
			   
	   				System.out.println("Success !! Phoneno Textbox is present");
	   				Reporter.log("Success !! Phoneno Textbox is present"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Phoneno Textbox is NOT present ");
	   				Reporter.log("Failure !! Phoneno Textboxis NOT present "); 
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
	   	 * edit  Certification - Verify user can add Mailing address
		*/ 
	 	@Test
	 	public void UserCanAddMailingAddress() throws InterruptedException 
	 	{
	 		
	 		System.out.println("====" + "\n" +
					"Test 3 : Edit  Certification - Verify user can add Mailing address" + "\n" +
		 			"====");
		    Reporter.log("====" + "\n" +
		 			  "Test 3 : Edit  Certification - Verify user can add Mailing address"  + "\n" +
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
						
	 		 System.out.println("Step 3 : Enter the valid data in mandatory field for Search Certifications functionality and click on Search button.");
	 		 Reporter.log("Step 3 : Enter the valid data in mandatory field for Search Certifications functionality and click on Search button."); 
	 				
	 		  Thread.sleep(1000);			
	 		  SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "state", 1);					
	 		  Thread.sleep(1000);
	 		  SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "certification_type_id", 1);					
	 		  Thread.sleep(1000);
	 		  editcer.ClickOnSearch();
	 				
			 System.out.println("Step 4 : Click on the first certification record that shows up");
			 Reporter.log("Step 4 : Click on the first certification record that shows up"); 
			  editcer.ClickOnStudentNametab();				 
	 			
	 		 System.out.println("Step 5 : Click on Edit Link");
	 		 Reporter.log("Step 5 : Click on Edit Link");
	 		  editcer.ClickOnEdittab();
	 		  Thread.sleep(2000);
	 				
	 		  System.out.println("Step 6 : Click on  'Has different mailing address' option");
	 		  Reporter.log("Step 6 : Click on '' Has different mailing address' option'");
	 			editcer.clickOnHideMailingaddress();
	 	// if 'Has different mailing address' is already check,		
	 			if(!SeleniumFunc.IsElementPresent(editcer.countryid))
	   			{
	 				editcer.clickOnHideMailingaddress();
	   			}
	 			
				 		     
	 		  System.out.println("Step 7 : Verify Mailing address fields should be displayed");
		 	  Reporter.log("Step 7 : Verify Mailing address fields should be displayed");
		 	
		 		//Verifying presence of country 
	   			if(SeleniumFunc.IsElementPresent(editcer.countryid))
	   			{
	   				Thread.sleep(1000);
	   				System.out.println("Success !! country select list  is present in mailing address");
	   				Reporter.log("Success !! country select list  is present in mailing address"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! country select list is NOT present in mailing address");
	   				Reporter.log("Failure !! country select list is NOT present in mailing address"); 
	   				
	   				AssertFailedCount++;
	   			}					
	   			//Verifying presence of Street Address  field
	   			if(SeleniumFunc.IsElementPresent(editcer.streetaddress))
	   			{
			   
	   				System.out.println("Success !! Street address Textbox is present in mailing address");
	   				Reporter.log("Success !! Street address Textbox is present in mailing address"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Street address Textbox is NOT present in mailing address");
	   				Reporter.log("Failure !! Street address Textboxis NOT present in mailing address"); 
	   				AssertFailedCount++;
	   			}
	   			//Verifying presence of  Address 2   field
	   			if(SeleniumFunc.IsElementPresent(editcer.Address2))
	   			{
			   
	   				System.out.println("Success !! Street address 2 Textbox is present in mailing address");
	   				Reporter.log("Success !! Street address 2 Textbox is present in mailing address"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Street address 2 Textbox is NOT present in mailing address");
	   				Reporter.log("Failure !! Street address 2 Textbox is NOT present in mailing address"); 
	   				AssertFailedCount++;
	   			}
	   			//Verifying presence of City  field
	   			if(SeleniumFunc.IsElementPresent(editcer.Citymailing))
	   			{
			   	
	   				System.out.println("Success !! city Textbox is present in mailing address");
	   				Reporter.log("Success !! city Textbox is present in mailing address"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! City Textbox is NOT present in mailing address");
	   				Reporter.log("Failure !! City Textboxis NOT present in mailing address"); 
	   				AssertFailedCount++;
	   			}
	   			//Verifying presence of State 
	   			if(SeleniumFunc.IsElementPresent(editcer.SelectMailingstate))
	   			{
	   				
	   				System.out.println("Success !! State select list  is present in mailing address");
	   				Reporter.log("Success !! State Select list is present in mailing address"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! State Select list is NOT present in mailing address");
	   				Reporter.log("Failure !! State Select list is NOT present in mailing address"); 
	   				AssertFailedCount++;
	   			}			
	   			//Verifying presence of Zip  field
	   			if(SeleniumFunc.IsElementPresent(editcer.Zipmailing))
	   			{
							
	   				System.out.println("Success !! Zip Textbox is present in mailing address");
	   				Reporter.log("Success !! Zip Textbox is present in mailing address"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Zip Textbox is NOT present in mailing address");
	   				Reporter.log("Failure !! Zip Textboxis NOT present in mailing address"); 
	   				AssertFailedCount++;
	   			}
						
	   			//Verifying presence of Phone no  field
	   			if(SeleniumFunc.IsElementPresent(editcer.Phonemailing))
	   			{
	   				Thread.sleep(1000);
	   				System.out.println("Success !! Phoneno Textbox is present in mailing address");
	   				Reporter.log("Success !! Phoneno Textbox is present in mailing address"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Phoneno Textbox is NOT present in mailing address");
	   				Reporter.log("Failure !! Phoneno Textboxis NOT present in mailing address"); 
	   				AssertFailedCount++;
	   			}
		 			
		 	System.out.println("Step 8 : Enter  valid data in mailing address details");
	 		Reporter.log("Step 8 : Enter  valid data in mailing address details"); 				
			    
	 				String streetaddress = "14086 Proton Rd";	
	 				String streetaddress2 = "dalginai";
	 				String City = "dallas";
	 				String Zip = "75244";
	 				String PhoneNo = "258-963-7458";
	 				Thread.sleep(1000); 		
	 				editcer.Enterstreetaddress("Tuscumbia");
	 				SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "s_country_id", 4);						    
	 				editcer.EnterStreetAddress(streetaddress);	
	 				editcer.EnterAddress2(streetaddress2);	     	 
	 				editcer.EnterCitymailing(City);			
	 				Thread.sleep(2000);
	 				SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "s_state", 5);						    
	 				editcer.EnterZipmailing(Zip);						    
	 				editcer.EnterPhonemailing(PhoneNo);
	 				Thread.sleep(1000);
	 				
	 			System.out.println("Step 9 : Click on 'Continue' button.");
		 		Reporter.log("Step 9 : Click on 'Continue' button."); 	
	 				editcer.Getsubmitt();
	 				Thread.sleep(2000);
	 				
	 			System.out.println("Step 10 : Trouble verifying your physical address and  mailing address");
	 			Reporter.log("Step 10 :Trouble verifying your physical address and  mailing address"); 
				
	 				Thread.sleep(1000);		
	 				editcer.ClickOnuseaddress1();   
	 				Thread.sleep(1000);	
	 				editcer.ClickOnuseaddress2();
	 				Thread.sleep(1000);	
	 				
	 			System.out.println("Step 11 : Click on 'Save' button.");
		 		Reporter.log("Step 11 : Click on 'Save' button."); 	
	 				editcer.ClickOnSaveBtn();	     		
	 				Thread.sleep(1000);	     
	     		System.out.println("Step 12 : Verify Mailing address has reflected on details page");
	     		Reporter.log("Step 12 : Verify Mailing address has reflected on details page");									
								
	     			String ActualUserName=SeleniumFunc.GetElementText("css", "div.alert.alert-success").trim();
	     			System.out.println(ActualUserName);									
	     			if(ActualUserName.equals("Certification was successfully updated."))
	     			{
	     				Thread.sleep(1000);
	     				System.out.println("Success !!  Mailing address has reflected on details page");
	     				Reporter.log("Success !! Mailing address has reflected on details page"); 
	     			}
	     			else
	     			{
	     				System.out.println("Failure !! Mailing address is not reflected on details page");
	     				Reporter.log("Failure !! Mailing address is not reflected on details page"); 
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
	   * edit  Certification - Verify absence of Certification Collection drop down on edit page
	  */
				 	
	 	@Test
	 	public void AbsenceofCertificationCollectiondropdown() throws InterruptedException
	 	{
	 			System.out.println("====" + "\n" +
								"Test 4 : Edit  Certification - Verify absence of Certification Collection dropdown on edit page" + "\n" +
	 					"====");
	 			Reporter.log("====" + "\n" +
					 			  "Test 4 : Edit  Certification - Verify absence of Certification Collection dropdown on edit page"  + "\n" +
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
						
	 			 System.out.println("Step 3 : Enter the valid data in mandatory field for Search Certifications functionality and click on Search button.");
		 		 Reporter.log("Step 3 : Enter the valid data in mandatory field for Search Certifications functionality and click on Search button."); 
		 				
		 			    Thread.sleep(1000);			
		 	    		SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "state", 1);					
		 				Thread.sleep(1000);
		 				SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "certification_type_id", 1);					
		 				Thread.sleep(1000);
		 				editcer.ClickOnSearch();
		 				
		 		System.out.println("Step 4 : Click on the first certification record that shows up");
		 		Reporter.log("Step 4 : Click on the first certification record that shows up"); 
					     editcer.ClickOnStudentNametab();				 
		 			
			   System.out.println("Step 5 : Click on Edit Link");
			   Reporter.log("Step 5 : Click on Edit Link");
				         editcer.ClickOnEdittab();
		 		         Thread.sleep(2000);
		 		    
		 		 System.out.println("Step 6 : Verify user is  on Edit Student Information page");
		 		 Reporter.log("Step 6 : Verify user is  on Edit Student Information page"); 		
		 						
		 			   
		 		   			//Verifying presence of Name field
		 		   			if(SeleniumFunc.IsElementPresent(editcer.Firstname))
		 		   			{
		 				   
		 		   				System.out.println("Success !! user is  on Edit Student Information page");
		 		   				Reporter.log("Success !! user is  on Edit Student Information page"); 
		 		   			}
		 		   			else
		 		   			{
		 		   				System.out.println("Failure !! Edit button is not working");
		 		   				Reporter.log("Failure !! Edit button is not working"); 
		 		   				AssertFailedCount++;
		 		   			}
		 		    
						     
		 		   	System.out.println("Step 7 : Verify Certification Collection dropdown is not displayed on edit screen");
		 		   	Reporter.log("Step 7: Verify Certification Collection dropdown is not displayed on edit screen"); 	 
	 				
	 				   //Verifying presence of  Certification Collection  field
	 			        	if(!SeleniumFunc.IsElementPresent(editcer.certificationcollection))
	 			        	{
	 			        		System.out.println("Success !! Certification Collection dropdown is NOT present ");
	 			        		Reporter.log("Success !! Certification Collection dropdown is  NOT present "); 
	 					
	 			        	}
	 			        		else
	 			        		{
	 			        			System.out.println("Failure !! Certification Collection dropdown is present");
	 			        			Reporter.log("Failure !! Certification Collection dropdown is present"); 
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
	 	 * edit  Certification - Verify user is able to edit Certification successfully
	 	 */
				 	
	 	@Test
	 	public void UserAbletoeditCertification() throws InterruptedException
	 	{
		 System.out.println("====" + "\n" +
								"Test 5 : Edit  Certification - Verify user is able to edit Certification successfully" + "\n" +
					 			"====");
	 	 Reporter.log("====" + "\n" +
					 			  "Test 5 : Edit  Certification - Verify user is able to edit Certification successfully"  + "\n" +
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
						
	 	 System.out.println("Step 3 : Enter the valid data in mandatory field for Search Certifications functionality and click on Search button.");
	 	 Reporter.log("Step 3 : Enter the valid data in mandatory field for Search Certifications functionality and click on Search button."); 
	 				
	 	  Thread.sleep(1000);			
	 	  /*SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "state", 1);					
	 	  Thread.sleep(1000);
	 	  SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "certification_type_id", 1);					
	 	  Thread.sleep(1000);*/
	 	  editcer.SearchCM("AutomationCertEdit");
	 	  Thread.sleep(1000);
	 	  editcer.ClickOnSearch();
	 				
	 	 System.out.println("Step 4 : Click on the first certification record that shows up");
	 	 Reporter.log("Step 4 : Click on the first certification record that shows up"); 
	 	  editcer.ClickOnStudentNametab();
				
	 	 System.out.println("Step 5 : Verify User is on certification details page ");
	 	 Reporter.log("Step 5 : Verify User is on certification details page"); 	 
				 if(SeleniumFunc.IsElementPresent(editcer.EditTab))		   			
				 {
							   
					 System.out.println("Success !! User is on certification details page");
					 Reporter.log("Success !! User is on certification details page"); 
				 }
				  else
				 {
					  System.out.println("Failure !! User is not on certification details page");
					  Reporter.log("Failure !! User is not on certification details page"); 
					  AssertFailedCount++;
				  }
				 
				 Thread.sleep(2000);
				 String Certificationstatus=SeleniumFunc.GetElementText("css", "#cert-info div:nth-of-type(2) dl dd span 	");
				 System.out.println("Certification Status(like pass, fail)= "+Certificationstatus);
				 Thread.sleep(1000);
				 
		 System.out.println("Step 6 : Click on Edit Link");
		 Reporter.log("Step 6 : Click on Edit Link");
		  editcer.ClickOnEdittab();
		  Thread.sleep(1000);
				
		  System.out.println("Step 7 : Verify user is on edit certification screen");
		  Reporter.log("Step 7 : Verify user is on edit certification screen"); 		
		 						
		 			   
		 		   			//Verifying presence of Name field
		  if(SeleniumFunc.IsElementPresent(editcer.Firstname))
		 	{
		 				   
			  System.out.println("Success !! user is on edit certification screen");
			  Reporter.log("Success !! user is on edit certification screen"); 
		 	}
		   else
		   {
			   System.out.println("Failure !! user is not navigated to edit form.");
			   Reporter.log("Failure !! user is not navigated to edit form."); 
			   AssertFailedCount++;
		   }		
		 		     							 		
		  System.out.println("Step 8 :  Change the information of any address field to the desired input.");
		  Reporter.log("Step 8 :  Change the information of any address field to the desired input.");
		   Thread.sleep(1000);	     		
	
		   //editcer.EnterStreetAddress("dalmiza");
		   editcer.Entercaddress2("Main Street Test");	
	
		   
		 					 	   	        
		  System.out.println("Step 9 :  Click on Continue button.");
		  Reporter.log("Step 9:  Click on Continue button.");
		 					 			      
		   editcer.Getsubmitt();
		  Thread.sleep(2000);		
		   /*editcer.ClickOnuseaddress1();     
		   Thread.sleep(2000);	
		   editcer.ClickOnuseaddress2();*/
		   
		  // editcer.ClickOnSaveBtn();
		 		     		
		  System.out.println("Step 10 : Verifying changes has updated successfully or not");
		  Reporter.log("Step 10 : Verifying changes has updated successfully or not"); 
		 		     		
		 		     		
		    String ActualUserName=SeleniumFunc.GetElementText("css", "div.alert.alert-success").trim();
		    System.out.println(ActualUserName);
		 		     		
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
		 		     		
		        Thread.sleep(2000);
		  System.out.println("Step 11 : Change certification status from Pass --fail");
		  Reporter.log("Step 11 : Change certification status from Pass --fail"); 
		   editcer.ClickOnEdittab();
		   Thread.sleep(1000);
	 		   
		   // Status in other info section
	 
		   if(Certificationstatus.equals("Passed"))
		   {
			   SeleniumFunc.SelectValueFromDropdownList("id", "certification_status", "Fail");
			   //SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "certification_status", 2);		
	 	   }
		   else
		   {
			   SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "certification_status", 1);	
	 	    }
		   Thread.sleep(1000);
		  System.out.println("Step 12 : Click on Continue button.");
		  Reporter.log("Step 12 : Click on Continue button.");
	 					 			      
	 		editcer.Getsubmitt();
	 		Thread.sleep(2000);		
	 		/*editcer.ClickOnuseaddress1();     
	 		Thread.sleep(2000);	
	 		editcer.ClickOnuseaddress2();*/
	 		
	 		//editcer.ClickOnSaveBtn();
	 		
	 	System.out.println("Step 13 : Verify certification status has changed successfully");
	 	Reporter.log("Step 13 : Verify certification status has changed successfully"); 
	 		     		
	 		     		
	 	 String CertificateStatus=SeleniumFunc.GetElementText("css", "#cert-info div:nth-of-type(2) dl dd span 	");
	 	 System.out.println("Certification Status(like pass, fail)= "+CertificateStatus);
	 	 Thread.sleep(1000);
	 		     		
	 	 if(Certificationstatus.equals(CertificateStatus))
	 	 {
	 		 System.out.println("Failure !!  certification status is not changed");
	 		 Reporter.log("Failure !! certification status is not changed"); 
	 		 AssertFailedCount++;
	 	 }
	 	 else
	 	 {
	 		 System.out.println("Success !!  certification status has changed successfully");
	 		 Reporter.log("Success !! certification status has changed successfully"); 
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
	 	 * edit  Certification -test that when clicking on the "Has different mailing address" check box, you are able to change any field in the address
	 	 */	
	 	@Test
	 	public void HasdifferentmailingaddressAbleTochange() throws InterruptedException
	 	{
	 		System.out.println("====" + "\n" +
								"Test 6 : Edit  Certification - Test that when clicking on the 'Has different mailing address' checkbox, you are able to change any field in the address form if necessary" + "\n" +
					 			"====");
	 		Reporter.log("====" + "\n" +
					 			  "Test 6 : Edit  Certification - Test that when clicking on the 'Has different mailing address' checkbox, you are able to change any field in the address form if necessary"  + "\n" +
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
	 			
	 		 // navigate to the certifications page.
	 				
 		 	System.out.println("Step 3 : Verify and navigate to the certifications page.");
 		 	Reporter.log("Step 3 : Verify and navigate to the certifications page."); 		
	 				
 		 	 editcer.ClickOncertificationManager();
 			 Thread.sleep(2000);		
 		 	 if(SeleniumFunc.ToGetCurrentPageUrl().equals(Constants.ApplicationURL_CM + "/certifications"))
 		 	 {
			
 		 		 System.out.println("Success !! https://kps.staging.kalkomey.com/certifications  is present");
 		 		 Reporter.log("Success !!https://kps.staging.kalkomey.com/certificationsis present"); 
 		 	 }
 		 	 else
 		 	 {
 		 		 System.out.println("Failure !! Shttps://kps.staging.kalkomey.com/certifications is NOT present ");
 		 		 Reporter.log("Failure !!https://kps.staging.kalkomey.com/certifications is NOT present "); 
 		 		 AssertFailedCount++;
 		 	 }
 		 	 
			System.out.println("Step 4 : Type in the name of a student and navigate to that student's page.");
	 		Reporter.log("Step 4 : Type in the name of a student and navigate to that student's page."); 
	 			editcer.SearchCM("AutomationTest");
	 		 	Thread.sleep(1000);
	 		 	editcer.ClickOnSearch();
			
			System.out.println("Step 5 : Click on the first certification record that shows up");
 			Reporter.log("Step 5 : Click on the first certification record that shows up"); 
 				editcer.ClickOnStudentNametab();
			
			 System.out.println("Step 6 : Verify, You should see that student's name at the top of the page with the student's information, including the address.");
			 Reporter.log("Step 6 : Verify, You should see that student's name at the top of the page with the student's information, including the address.");
	  	     	String StudentName=SeleniumFunc.GetElementText("css", ".span8>h1");
	  	     	String StudentAdd=SeleniumFunc.GetElementText("css", "#contact-info div:nth-of-type(1)  dl:nth-of-type(1) dt");
			  
	  	     	if(StudentName.equals("AutomationTest Test ke-testing") && StudentAdd.equals("Physical Address"))		   			
	  	     	{
						   
	  	     		System.out.println("Success !! Student's name at the top of the page and address is present.");
	  	     		Reporter.log("Success !! Student's name at the top of the page and address is present."); 
				  }
	  	     	else
	  	     	{
	  	     		System.out.println("Failure !! Student's name at the top of the page and address is NOT present.");
	  	     		Reporter.log("Failure !! Student's name at the top of the page and address is NOT present."); 
	  	     		AssertFailedCount++;
	  	     	}
			    
			    /* SeleniumFunc.WebDriverWaitMethod("xpath", "//div[2]/dl[4]/dd");
			     String CertificationID=SeleniumFunc.GetElementText("xpath", "//div[2]/dl[4]/dd");
			     System.out.println("CertificationID ="+ CertificationID);
	     		Thread.sleep(1000);*/
	     		
	  	    System.out.println("Step 7 : Click on Edit Link");
	 		Reporter.log("Step 7 : Click on Edit Link");
			    editcer.ClickOnEdittab();
	 		    Thread.sleep(1000);
			
     		System.out.println("Step 8 : Verify, your url should have changed to end with /certifications/(student_id)/edit");
     		Reporter.log("Step 8 : Verify, your url should have changed to end with /certifications/(student_id)/edit "); 	
     		
     		//if(SeleniumFunc.ToGetCurrentPageUrl().equals(Constants.ApplicationURL_CM + "/certifications/"+CertificationID+"/edit"))
     			if(SeleniumFunc.ToGetCurrentPageUrl().contains(Constants.ApplicationURL_CM + "/certifications/5185767/edit"))
     			{	 
     				Thread.sleep(1000);
     				System.out.println("Success !! Valid URL is displayed");
     				Reporter.log("Success !! Valid URL is displayed"); 
		     	}
     			else
     			{
     				System.out.println("Failure !! Valid URL is NOT displayed");
     				Reporter.log("Failure !! Valid URL is Not displayed"); 
     				AssertFailedCount++;
		     	}
     		
     		System.out.println("Step 9 : Click on  'Has different mailing address' option");
 			Reporter.log("Step 9 : Click on '' Has different mailing address' option'");
 			
 	// if 'Has different mailing address' is already check,		
 				if(!SeleniumFunc.IsElementPresent(editcer.countryid))
 				{
 				editcer.clickOnHideMailingaddress();
 				}
 			 		
     		System.out.println("Step 10 : Verify, All Mailing Address fields is editable and Change the information of any address field to the desired input.");					 		
     		Reporter.log("Step 10 : Verify, All Mailing Address fields is editable and Change the information of any address field to the desired input."); 	
				
     			SeleniumFunc.SelectValueFromDropdownList("id", "s_country_id", "United States");
     			//SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "s_country_id", 5);	
     			editcer.Enterstreetaddress("3013 12TH AVE N");
     			Thread.sleep(1000);
     			editcer.EnterAddress2("Main Street");
     			Thread.sleep(1000);
     			editcer.EnterCitymailing("BIRMINGHAM");
     			Thread.sleep(1000);
     			SeleniumFunc.SelectValueFromDropdownList("id", "s_state", "Alabama");
     			Thread.sleep(1000);
     			//SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "s_state", 5);	
     			editcer.EnterZipmailing("35234");
     			
     			editcer.EnterPhonemailing("2345678910");
	 							 		
     		
					 	   	        
     		System.out.println("Step 11 : Click on Continue button.");
     		Reporter.log("Step 11 : Click on Continue button.");
     	  	    Thread.sleep(1000);		 			      
     			editcer.Getsubmitt();
     			
     			//editcer.ClickOnuseaddress1();     
     			//Thread.sleep(2000);	
     			//editcer.ClickOnuseaddress2();
     			Thread.sleep(2000);	
     			
     			//editcer.ClickOnSaveBtn();
     			
	 		     		
     		System.out.println("Step 12 : Verify, User has  redirected to the student's page.");
     		Reporter.log("Step 12 : Verify, User has  redirected to the student's page.");
     			String StudentNamee=SeleniumFunc.GetElementText("css", ".span8>h1");
     			String StudentAd=SeleniumFunc.GetElementText("css", "#contact-info div:nth-of-type(1)  dl:nth-of-type(1) dt");
	 					  
     			if(StudentNamee.equals("AutomationTest Test ke-testing") && StudentAd.equals("Physical Address"))		   			
     			{
	 								   
     				System.out.println("Success !! User has  redirected to the student's page.");
     				Reporter.log("Success !! User has  redirected to the student's page."); 
     			}
     			else
     			{
     				System.out.println("Failure !! User is not  redirected to the student's page.");
     				Reporter.log("Failure !! User is not  redirected to the student's page."); 
     				AssertFailedCount++;
     			}
     		System.out.println("Step 13 : Verifying changes has updated successfully or not");
     		Reporter.log("Step 13 : Verifying changes has updated successfully or not"); 
     		  editcer.ClickOnEdittab();
	 		  Thread.sleep(2000); 	
	 		  
	 		// if 'Has different mailing address' is already check,		
				if(!SeleniumFunc.IsElementPresent(editcer.countryid))
				{
				editcer.clickOnHideMailingaddress();
				}
				
				
	 		  String ActualUserName= SeleniumFunc.GetAttributeValue("css","#certification_s_address2", "value");		
	 		  String ExpectedUserName= "Main Street";
               System.out.println(ActualUserName);
     			
     			if(ActualUserName.equals(ExpectedUserName))
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
		 	