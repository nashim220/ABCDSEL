package products.CM;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.CreateNewUserPage;
import pageFactory.CM.GlobalHeader;
import pageFactory.CM.LoginPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;


  	public class CreateNewUserTest {
	
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
  		 * Create User - Verify  user can navigate to New Account page
  		 */	
  		@Test
  		private void NavigatetoNewAccountPage() throws InterruptedException
  		{
  			System.out.println("====" + "\n" +	
  		              "Test 1 : Create new user - Verify  user can navigate to New Account page"  + "\n" +
  					  "====");
  			Reporter.log("====" + "\n" +
  					   "Test 1 : Create new user -Verify  user can navigate to New Account pagee"  + "\n" +  
  					   "====");	
		
  					int AssertFailedCount=0 ;
  					SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
  					LoginPage login = new LoginPage(driver);
  					CreateNewUserPage  newuser=new CreateNewUserPage(driver);
  					GlobalHeader gheader = new GlobalHeader(driver);	
  		
  			System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
  			Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 	     
  		
  					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM); 	     
  			
  			System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
  			Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 	     
  			
  					login.EnterUsername(Constants.CM_Username);	
  					login.EnterPassword(Constants.CM_Password);
  					login.ClickOnLogInButton();	       
  					Thread.sleep(1000);
  					//Verifying alert message on login
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
	   		
  						AssertFailedCount++;
  					}
  				
  			System.out.println("Step 3 : Select 'manage users' from 'Admin' drop down list bar to navigate  'New User' page. ");
  			Reporter.log("Step 3 : Select 'manage users' from 'Admin' drop down list bar to navigate  'New User' page. "); 	       
  				
  					Thread.sleep(1000);
  					newuser.clickonadmintab();
  					Thread.sleep(1000);
  					newuser.ClickOnManageusers();
  				     
  			System.out.println("Step 4 : Click on New User link");
  			Reporter.log("Step 4 : Click on New User link"); 
  					
  					Thread.sleep(1000);	
  			         newuser.ClickOnNewUserTab();
  			     
  		    System.out.println("Step 5 : Verify User has successfully naviagted to 'New Account' page or not.");
  			Reporter.log("Step 5 : Verify User has successfully naviagted to 'New Account' page or not.");
  			 
  					if(SeleniumFunc.IsElementPresent(newuser.newaccounttext))
  					{
  						System.out.println("Success !! User has successfully naviagted to 'New Account' page.");
  						Reporter.log("Success !! User has successfully naviagted to 'New Account' page."); 
  					}
  					else
  					{
  						System.out.println("Failure !! User is not naviagted to 'New Account' page ");
  						Reporter.log("Failure !! User is not naviagted to 'New Account' page "); 
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
  		 * Create User - Verify UI of New User
  		 */ 
	      
  		@Test
  		private void UIOFnewuser() throws InterruptedException
	    {
  			System.out.println("====" + "\n" +
	                "Test 2 : Create new user - Verify UI of New User"  + "\n" +	
  					"====");
  			Reporter.log("====" + "\n" +  
  					"Test 2  : Create new user - Verify UI of New User "+ "\n" +  
  					"====");	
	
  					int AssertFailedCount=0 ;
  					SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
  					LoginPage login = new LoginPage(driver);
  					CreateNewUserPage  newuser=new CreateNewUserPage(driver);
  					GlobalHeader gheader = new GlobalHeader(driver);
  				
  			System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
  			Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");	
  				
  					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM);
  				
  			System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
  			Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 
  			
  					login.EnterUsername(Constants.CM_Username);
  					login.EnterPassword(Constants.CM_Password);
  					login.ClickOnLogInButton();  
  					Thread.sleep(1000);
  			//Verifying alert message on login
  					String actualtext= gheader.Success_AlertText.getText().trim();
  					String expectedtext= "Signed in!"; 
  			
  					if(actualtext.equals(expectedtext))
  					{
  						System.out.println("Success !! alert message is displayed i.e. " + actualtext);
  						Reporter.log("Success !! alert message is displayed i.e. " + actualtext); 
  					}	
  					else
  					{
  						System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  	+ "\n" + expectedtext + "\n" +  "Actual : " + "\n" +  actualtext);
  						Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  + "\n" + expectedtext + "\n" +  "Actual : " + "\n" +  actualtext);
  						AssertFailedCount++;
  					}
  				
  			System.out.println("Step 3 : Select 'manage users' from 'Admin' drop down list bar to navigate  'New User' page. ");
  			Reporter.log("Step 3 : Select 'manage users' from 'Admin' drop down list bar to navigate  'New User' page. "); 	       
  	  				
  					newuser.clickonadmintab();
  					Thread.sleep(1000);
  					newuser.ClickOnManageusers();
  					Thread.sleep(1000);
  	  				     
  	  		System.out.println("Step 4 : Click on New User link");
  	  		Reporter.log("Step 4 : Click on New User link"); 
  	  				
  	  				newuser.ClickOnNewUserTab();
  				
  	  		System.out.println("Step 5 : Verify User has successfully naviagted to 'New Account' page or not.");
  	  		Reporter.log("Step 5 : Verify User has successfully naviagted to 'New Account' page or not.");
  	  			 
  	  				if(SeleniumFunc.IsElementPresent(newuser.newaccounttext))
  	  				{
  	  					System.out.println("Success !! User has successfully naviagted to 'New Account' page.");
  	  					Reporter.log("Success !! User has successfully naviagted to 'New Account' page."); 
  	  				}
  	  				else
  	  				{
  	  					System.out.println("Failure !! User is not naviagted to 'New Account' page ");
  	  					Reporter.log("Failure !! User is not naviagted to 'New Account' page "); 
  	  					AssertFailedCount++;
  	  				}
  				
  	  		System.out.println("Step 6 : Observe New Account page fields is displayed.");
  	  		Reporter.log("Step 6 : Observe New Account page fields is displayed.");  
	       
  	  		//Verifying presence of Select AgencyName
  	  				if(SeleniumFunc.IsElementPresent(newuser.SelectAgencyName))
  	  				{
  	  					System.out.println("Success !! Agency Name drop down  is present");
  	  					Reporter.log("Success !! Agency Name drop down is present"); 
  	  				}
  	  		    		else
  	  		    		{
  	  		    			System.out.println("Failure !! Agency Name drop down is not present ");
  	  		    			Reporter.log("Failure !! Agency Name drop down is not present "); 
  	  		    			AssertFailedCount++;
  	  		    		}
  				
  	  	   //Verifying presence of Name field
  	  				if(SeleniumFunc.IsElementPresent(newuser.EnterUsername))
  	  				{
  	  					System.out.println("Success !! Name Textbox is present");
  	  					Reporter.log("Success !! Name Textbox is present"); 
  	  				}
  	  		    		else
  	  		    		{
  	  		    			System.out.println("Failure !! Name Textbox is not present ");
  	  		    			Reporter.log("Failure !! Name Textbox is not present "); 
  	  		    			AssertFailedCount++;
  	  		    		}
  				
  	  	   //Verifying presence of Email  field
  	  				if(SeleniumFunc.IsElementPresent(newuser.EnterUserEmailID))
  	  				{
  	  					System.out.println("Success !! Email Textbox is present");
  	  					Reporter.log("Success !! Email Textbox is present"); 
  	  				}
  	  				else
  	  					{
  	  						System.out.println("Failure !! Email Textbox is not present ");
  	  						Reporter.log("Failure !! Email Textbox is not present "); 
  	  						AssertFailedCount++;
  	  		    		}
  				
  		  //Verifying presence of Password   field
  	  				if(SeleniumFunc.IsElementPresent(newuser.EnterUserPassward))
  	  				{
  	  					System.out.println("Success !! Password Textbox is present");
  	  					Reporter.log("Success !! Password Textbox is present"); 
  	  				}
  	  					else
  	  					{
  	  						System.out.println("Failure !! Password Textbox is not present ");
  	  						Reporter.log("Failure !! Password Textbox is not present "); 
  	  						AssertFailedCount++;
  	  					}
  				
  		 //Verifying presence of  confirm Password   field
  	  				if(SeleniumFunc.IsElementPresent(newuser.EnterConfirmPassward))
  	  				{
  	  					System.out.println("Success !! Confirm Password Textbox is present");
  	  					Reporter.log("Success !! Confirm Password Textbox is present"); 
  	  				}
  	  					else
  	  					{
  	  						System.out.println("Failure !! Confirm Password Textbox is not present ");
  	  						Reporter.log("Failure !! Confirm Password Textbox is not present "); 
  	  						AssertFailedCount++;
  	  					}
  			
  		//Verifying presence of  active check box   field
  	  				if(SeleniumFunc.IsElementPresent(newuser.ClickOnActive))
  	  				{
  	  					System.out.println("Success !! Active check box is present");
  	  					Reporter.log("Success !! Active check box is present"); 
  	  				}
  	  					else
  	  					{
  	  						System.out.println("Failure !! Active check box is not present ");
  	  						Reporter.log("Failure !! Active check box is not present "); 
  	  						AssertFailedCount++;
  	  					}
  			
  	  				if(SeleniumFunc.IsElementPresent(newuser.RolePermissions))
  	  				{
  	  				Thread.sleep(1000);
  	  					System.out.println("Success !! Role Permissions Text is present");
  	  					Reporter.log("Success !! RolePermissions Text is present"); 
  	  				}
  	  					else
  	  					{
  	  						System.out.println("Failure !! Role Permissions Text is not present ");
  	  						Reporter.log("Failure !! Role Permissions Text is not present "); 
  	  						AssertFailedCount++;
  	  					}
  			
  	  		System.out.println("Step 7 : Observe Role Permissions section fields is displayed.");
  	  		Reporter.log("Step 7 : Observe Role Permissions section fields is displayed.");    			
  	  		    					
  			//Verifying presence of  Can manage users check box   field
  	  				if(SeleniumFunc.IsElementPresent(newuser.ClickOnManageUser))
  	  				{
  	  					System.out.println("Success !! manage users  check box is present");
  	  					Reporter.log("Success !! manage users  check box is present"); 
  	  				}
  	  					else
  	  					{
  	  						System.out.println("Failure !!  manage users  check box is not present ");
  	  						Reporter.log("Failure !!  manage users  check box is not present "); 
  	  						AssertFailedCount++;
  	  					}
  				
  				//Verifying presence of  Can bypass payments  check box   field
  	  				if(SeleniumFunc.IsElementPresent(newuser.ClickOnByPassPament))
  	  				{   
  	  				Thread.sleep(1000);
  	  					System.out.println("Success !! Bypass payments  check box is present");
  	  					Reporter.log("Success !! Bypass payments  check box is present"); 
  	  				}
  	  					else
  	  					{
  	  						System.out.println("Failure !! Bypass payments  check box is not present ");
  	  						Reporter.log("Failure !! Bypass payments  check box is not present "); 
  	  						AssertFailedCount++;
  	  		    		}
  				
  				//Verifying presence of  edit certifications  check box   field
  	  				if(SeleniumFunc.IsElementPresent(newuser.ClickOnEditCertifications))
  	  				{   
  	  					System.out.println("Success !! Edit certifications.  check box is present");
  	  					Reporter.log("Success !! Edit certifications.  check box is present"); 
  	  				}
  	  					else
  	  					{
  	  						System.out.println("Failure !! Edit certifications.  check box is NOT present ");
  	  						Reporter.log("Failure !! Edit certifications.  check box is NOT present "); 
  	  						AssertFailedCount++;
  	  		    		}
  				
  				//Verifying presence of   view reports  check box   field
  	  				if(SeleniumFunc.IsElementPresent(newuser.ClickOnViewReports))
  	  				{   
  	  					System.out.println("Success !! View reports  check box is present");
  	  					Reporter.log("Success !! View reports  check box is present"); 
  	  		    	}
  	  					else
  	  					{
  	  						System.out.println("Failure !! View reports  check box is not present ");
  	  						Reporter.log("Failure !! View reports  check box is not present "); 
  	  						AssertFailedCount++;
  	  					}
  				
  				//Verifying presence of   create certifications check box   field
  	  		    	if(SeleniumFunc.IsElementPresent(newuser.ClickOnCertificationCreater))
  	  		    	{   
  	  		    		System.out.println("Success !! Create certifications  check box is present");
  	  		    		Reporter.log("Success !! Create certifications check box is present"); 
  	  		    	}
  	  		    		else
  	  		    		{
  	  		    			System.out.println("Failure !! Create certifications  check box is not present ");
  	  		    			Reporter.log("Failure !! Create certifications  check box is not present "); 
  	  		    			AssertFailedCount++;
  	  		    		}
  			
  				//Verifying presence of   edit collections. check box   field
  	  		    	if(SeleniumFunc.IsElementPresent(newuser.ClickOnEditCollections))
  	  		    	{   
  	  		    	Thread.sleep(1000);
  	  		    		System.out.println("Success !! Edit collections check box is present");
  	  		    		Reporter.log("Success !! Edit collections check box is present"); 
  	  		    	}
  	  		    		else
  	  		    		{
  	  		    			System.out.println("Failure !! Edit collections. check box is not present ");
  	  		    			Reporter.log("Failure !! Edit collections.  check box is not present "); 
  	  		    			AssertFailedCount++;
  	  		    		}
  	        	
  				//Verifying presence of    add notes check box   field
  	  		    	if(SeleniumFunc.IsElementPresent(newuser.ClickOnAddNotes))
  	  		    	{   
  	  		    		System.out.println("Success !! Add notes  text Box is present");
  	  		    		Reporter.log("Success !! Add notes text box is present"); 
  	  		    	}
  	  		    		else
  	  		    		{
  	  		    			System.out.println("Failure !! Add notes text box is not  present ");
  	  		    			Reporter.log("Failure !! Add notes  text box is not  present "); 
  	  		    			AssertFailedCount++;
  	  		    		}
  				
  				//Verifying presence of     order permanent certification cards. check box   field
  	  		    	if(SeleniumFunc.IsElementPresent(newuser.clickonOrderpermanentcertificate))
  	  		    	{   
  	  		    		System.out.println("Success !! order permanent certification cards  check box is present");
  	  		    		Reporter.log("Success !! Order permanent certification cards check box is present"); 
  	  		    	}
  	  		    		else
  	  		    		{
  	  		    			System.out.println("Failure !! order permanent certification cards check box is not present ");
  	  		    			Reporter.log("Failure !! order permanent certification cards  check box is not present "); 
  	  		    			AssertFailedCount++;
  	  		    		}
  			
  				//Verifying presence of      send course completion documents. check box   field
  	  		    	if(SeleniumFunc.IsElementPresent(newuser.clickOnsendcourceCompletionDoc))
  	  		    	{   
  	  		    		System.out.println("Success !! Send course completion documents  check box is present");
  	  		    		Reporter.log("Success !! Send course completion documents check box is present"); 
  	  		    	}
  	  		    		else
  	  		    		{
  	  		    			System.out.println("Failure !! Send course completion documents check box is NOT present ");
  	  		    			Reporter.log("Failure !! Send course completion documents  check box is NOT present "); 
  	  		    			AssertFailedCount++;
  	  		    		}
  			
  				//Verifying presence of      import certifications for their agency check box   field
  	  		    	if(SeleniumFunc.IsElementPresent(newuser.clickOnimportcertifications))
  	  		    	{   
  	  		    		System.out.println("Success !! import certifications for their agency  check box is present");
  	  		    		Reporter.log("Success !! import certifications for their agency check box is present"); 
  	  		    	}
  	  		    		else
  	  		    		{
  	  		    			System.out.println("Failure !! import certifications for their agency check box is not present ");
  	  		    			Reporter.log("Failure !! import certifications for their agency  check box is not present "); 
  	  		    			AssertFailedCount++;
  	  		    		}
  	  		   //Verifying presence of manage agencies for their agency check box   field
  	  		    	if(SeleniumFunc.IsElementPresent(newuser.clickOnmanageagencies))
  	  		    	{   
  	  		    		System.out.println("Success !! manage agencies  check box is present");
  	  		    		Reporter.log("Success !! manage agencies check box is present"); 
  	  		    	}
  	  		    		else
  	  		    		{
  	  		    			System.out.println("Failure !! manage agencies check box is not present ");
  	  		    			Reporter.log("Failure !! manage agencies  check box is not present "); 
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
  		 * Create User - Verify user is able to create new User successfully
  		 */
     	
  		@Test
  		public void AbletoCreatenewUser() throws InterruptedException
  		{
  			System.out.println("====" + "\n" +
  		                 "Test 3 : Create new user - Verify user is able to create new User successfully"  + "\n" +	
  					      "====");
  			Reporter.log("====" + "\n" +  
  					      "Test 3  : Create new user - Verify user is able to create new User successfully"  + "\n" +  
  					       "====");	
    	
  					int AssertFailedCount=0 ;
  					SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
  					LoginPage login = new LoginPage(driver);
  					CreateNewUserPage  newuser=new CreateNewUserPage(driver);
  					GlobalHeader gheader = new GlobalHeader(driver);
  				
  			System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
  			Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
  			
  					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM);	
  				
  			System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
  			Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 
  			
  					login.EnterUsername(Constants.CM_Username);
  					login.EnterPassword(Constants.CM_Password);
  					login.ClickOnLogInButton(); 			
  				 	
  			System.out.println("Step 3 : Select 'manage users' from 'Admin' drop down list bar to navigate  'New User' page and Click on New User link ");
  			Reporter.log("Step 3 : Select 'manage users' from 'Admin' drop down list bar to navigate  'New User' page and Click on New User link"); 	       
  	  				
  					newuser.clickonadmintab();
  					Thread.sleep(2000);
  					newuser.ClickOnManageusers();
  					Thread.sleep(2000);
  					newuser.ClickOnNewUserTab();
  				
  			System.out.println("Step 4 : Verify User has successfully naviagted to 'New Account' page or not.");
  			Reporter.log("Step 4 : Verify User has successfully naviagted to 'New Account' page or not.");
  	  			 
  					if(SeleniumFunc.IsElementPresent(newuser.newaccounttext))
  					{
  						Thread.sleep(1000);
  						System.out.println("Success !! User has successfully naviagted to 'New Account' page.");
  						Reporter.log("Success !! User has successfully naviagted to 'New Account' page."); 
  					}
  						else
  						{
  							System.out.println("Failure !! User is not naviagted to 'New Account' page ");
  							Reporter.log("Failure !! User is not naviagted to 'New Account' page "); 
  							AssertFailedCount++;
  						}
  	  			 	
  			System.out.println("Step 5 : Leave all the fields as blank and click on submit button.");
  			Reporter.log("Step 5 : Leave all the fields as blank and click on submit button.");
  					newuser.ClickOnSubmitBtnButton();
  					Thread.sleep(1000);
  					
  			System.out.println("Step 6 : Verify,validation message 'There was a problem saving this new user. ' should be displayed");
  			Reporter.log("Step 6 : Verify,validation message 'There was a problem saving this new user. ' should be displayed");
  	    	  		    	
  					String Actualvalidation=SeleniumFunc.GetElementText("css",".alert.alert-error");
  					String Expectedvalidation="There was a problem saving this new user.";
  					if(Actualvalidation.equals(Expectedvalidation))
  					{
  						System.out.println("Success !! Correct validation message is displayed i.e. " + Actualvalidation);
  						Reporter.log("Success !! Correct validation message is displayed i.e. " + Actualvalidation); 
  					}
  						else
  						{
  							System.out.println("Failure !! incorrect validation message is displayed" + "\n" + "Expected : "  	+ "\n" + Expectedvalidation + "\n" + 
  						"Actual : " + "\n" +  Actualvalidation);
  							Reporter.log("Failure !!  incorrect validation message is displayed" + "\n" + "Expected : "  		+ "\n" + Expectedvalidation + "\n" + 
  	    	    							             "Actual : " + "\n" +  Actualvalidation);
  							AssertFailedCount++;
  						}	   	
  	  			 	
  						Thread.sleep(1000);
  			System.out.println("Step 7 : Select Agency Name from Agency drop down list and Verify,User is able to select agency or not.");
  			Reporter.log("Step 7: Select Agency Name from Agency drop down list and Verify,User is able to select agency or not.");
  	
  					SeleniumFunc.SelectValueFromDropdownListUsingIndex("id","user_agency_id",5);  
  					
  					//SeleniumFunc.SelectValueFromDropdownList("id", "user_agency_id", "Arkansas Game and Fish Commission");
  					Thread.sleep(2000);
  	  			 	String ActualAgencyValue=SeleniumFunc.GetSelectedValueFromDropdownList("id", "user_agency_id");
  					String ExpectedAgencyValue="Arkansas Game and Fish Commission";
  					Thread.sleep(1000);
  					if(ActualAgencyValue.equals(ExpectedAgencyValue))
  					{
  						Thread.sleep(1000);
  						System.out.println("Success !! User is able to select Agency." + ActualAgencyValue);
  						Reporter.log("Success !! User is able to select Agency. " + ActualAgencyValue); 
  					}
  						else
  						{
  							System.out.println("Failure !! Agency drop down is not working." + "\n" + "Expected : "  	+ "\n" + ExpectedAgencyValue + "\n" + 
   	    										 "Actual : " + "\n" +  Actualvalidation);
  							Reporter.log("Failure !!  Agency drop down is not working." + "\n" + "Expected : "  		+ "\n" + ExpectedAgencyValue + "\n" + 
   	    							             "Actual : " + "\n" +  Actualvalidation);
  							AssertFailedCount++;
  						}   	
  	    	  		    
  			System.out.println("Step 8 : Click on check box to make user inactive and Enter valid data for mandatory fields.");
  			Reporter.log("Step 8 : Click on check box to make user inactive and Enter valid data for mandatory fields.");    
   	  				newuser.ClickOnActivecheckbox();
   	  			    Thread.sleep(1000);
   	  				String firstname= "TestngClarion" + JH.GenerateRandomNumber();
   	  				String password = "Test"+ JH.GenerateRandomNumber()+"@Test.com";
   	  				String emailaddress= "Test"+ JH.GenerateRandomNumber()+"@Test.com";	
   	  				Thread.sleep(1000);       
   	  				newuser.FillUserForm(firstname, emailaddress, password, password);
  				
   	  		System.out.println("Step 9 : Select roles and permissions and Click on Submit button.");
   	  		Reporter.log("Step 9 : Select roles and permissions and Click on Submit button."); 
  	  		    
   	  				Thread.sleep(1000);
   	  				newuser.ClickOnEditCertificationscheckbox();
   	  				newuser.ClickOnByPassPamentcheckbox();
   	  				newuser.ClickOnSubmitBtnButton();   	    
  				
   	  		System.out.println("Step 10 : Verify,New user has successfully created.");
   	  		Reporter.log("Step 10 : Verify,New user has successfully created.");   
  				
   	  				//Verifying alert message
   	  				String actualtext= gheader.Success_AlertText.getText().trim();
   	  				String expectedtext= "New user created.";			
   	  				if(actualtext.equals(expectedtext))
   	  				{
   	  					System.out.println("Success !! alert message is displayed i.e. " + actualtext);
   	  					Reporter.log("Success !! alert message is displayed i.e. " + actualtext); 
   	  				}
   	  					else
   	  					{
   	  						System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  	+ "\n" + expectedtext + "\n" + 
    										 "Actual : " + "\n" +  actualtext);
   	  						Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  		+ "\n" + expectedtext + "\n" + 
    							             "Actual : " + "\n" +  actualtext);
   	  						AssertFailedCount++;
   	  					}
  		 		
   	  		System.out.println("Step 11 : Click on logout button and Login with new user.");
   	  		Reporter.log("Step 11 : Click on logout button and Login with new user.");    
  				
   	  				// Logout
   	  				Thread.sleep(1000);
   	  				newuser.ClickOnusername();
   	  				newuser.ClickonLogout();
   	  				Thread.sleep(2000);
   	  				login.EnterUsername(emailaddress);
   	  				login.EnterPassword(password);
   	  				login.ClickOnLogInButton(); 
   	  			  Thread.sleep(1000);
   	  			
   	  		System.out.println("Step 12 : Verify,User is not  able to login as this is disabled ");
   	  		Reporter.log("Step 12 : Verify,User is not  able to login as this is disabled ");
  	  	  	
   	  				String ActualAuthentication= SeleniumFunc.GetElementText("css", ".alert.alert-error");
   	  				String ExpectedAuthentication= "Authentication failed, please try again.";
   	  					if(ActualAuthentication.equals(ExpectedAuthentication))
   	  					{
   	  						System.out.println("Success !! Valid  message is displayed i.e. " + ActualAuthentication);
   	  						Reporter.log("Success !! Valid  message is displayed i.e. " + ActualAuthentication); 
   	  					}
   	  						else
   	  						{
   	  							System.out.println("Failure !! incorrect  message is displayed" + "\n" + "Expected : "  + "\n" + ExpectedAuthentication + "\n" + 
	   							 "Actual : " + "\n" +  ActualAuthentication);
   	  							Reporter.log("Failure !!  incorrect  message is displayed" + "\n" + "Expected : "  + "\n" + ExpectedAuthentication + "\n" + 
   	  							"Actual : " + "\n" +  ActualAuthentication);
   	  							AssertFailedCount++;
   	  						}
  	  			
   	  		System.out.println("Step 13 : Login with valid credentials to make user active.");
   	  		Reporter.log("Step 13 : Login with valid credentials to make user active.");    
   	  				login.EnterUsername(Constants.CM_Username);
   	  				login.EnterPassword(Constants.CM_Password);
   	  				login.ClickOnLogInButton(); 	
   	  			Thread.sleep(1000);
   	  				newuser.clickonadmintab();
   	  				newuser.ClickOnManageusers();
   	  			Thread.sleep(1000);
  	  				newuser.EnterUserinfo(firstname);
  	  				newuser.ClcikOnSearch();
  	  			Thread.sleep(1000);
  	  				newuser.Cliconusertoedit();
  	  				Thread.sleep(1000);
  	  				newuser.Clickonedittab();
  	  				Thread.sleep(1000);
				
  	  		System.out.println("Step 14 : Click on check box to make user active and  Submit ");
  	  		Reporter.log("Step 14 : Click on check box to make user active and Submit.");    
  	  				newuser.ClickOnActivecheckbox();
  	  				newuser.ClickOnSubmitBtnButton();   	 
				
  	  		System.out.println("Step 15 : Click on logout button and Login with new user.");
  	  		Reporter.log("Step 15 : Click on logout button and Login with new user.");    
 	// Logout
  	  				Thread.sleep(1000);
  	  				newuser.ClickOnusername();
  	  				newuser.ClickonLogout();
  	  				Thread.sleep(2000);
  				 	login.EnterUsername(emailaddress);
  				 	login.EnterPassword(password);
  				 	login.ClickOnLogInButton(); 
  				 	Thread.sleep(2000);
  				 	
  			System.out.println("Step 16 : Verify,User is able to login as this is activated now by Admin");
  			Reporter.log("Step 16 : Verify,User is able to login as this is activated now by Admin");
  	  	  	
  	 //Verifying alert message on login
  					String actualtext1= gheader.Success_AlertText.getText().trim();
  					String expectedtext1= "Signed in!"; 
  					Thread.sleep(2000);
  					if(actualtext1.equals(expectedtext1))
  						{
  						System.out.println("Success !! alert message is displayed i.e. " + actualtext1);
  						Reporter.log("Success !! alert message is displayed i.e. " + actualtext1); 
  						}	
  							else
  							{
  								System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  	+ "\n" + expectedtext1 + "\n" +  "Actual : " + "\n" +  actualtext1);
  								Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  + "\n" + expectedtext1 + "\n" +  "Actual : " + "\n" +  actualtext1);
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
     	
  		
     	   
  		
     	/* Test 4  
    	 * Create User - Verify that email ID is unique for every user
    	*/
  		@Test
  		public void EmailIDisUniqueforeveryUser() throws InterruptedException
  		{
  			System.out.println("====" + "\n" +		
  					"Test 4 : Create new user - Verify that email ID is unique for every user"  + "\n" +
  					"====");
  			Reporter.log("====" + "\n" +  
  					"Test 4  : Create new user - Verify that email ID is unique for every user"  + "\n" +  
  					"====");	
    	
  					int AssertFailedCount=0 ;
  					SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
  					LoginPage login = new LoginPage(driver);
  					CreateNewUserPage  newuser=new CreateNewUserPage(driver);
  					GlobalHeader gheader = new GlobalHeader(driver);
  				
  			System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
  			Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");	
  				
  					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM);	
  				
  			System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
  			Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 
  				
  					login.EnterUsername(Constants.CM_Username);
  					login.EnterPassword(Constants.CM_Password);
  					login.ClickOnLogInButton(); 	
  					Thread.sleep(1000);
  					
  			System.out.println("Step 3 : Select 'manage users' from 'Admin' drop down list bar to navigate  'New User' page. ");
  			Reporter.log("Step 3 : Select 'manage users' from 'Admin' drop down list bar to navigate  'New User' page. "); 	       
  		  	  				
  					newuser.clickonadmintab();
  					Thread.sleep(1000);       
  		  			newuser.ClickOnManageusers();
  		  			Thread.sleep(1000);       
  		  	  				     
  		  	System.out.println("Step 4 : Click on New User link");
  		  	Reporter.log("Step 4 : Click on New User link"); 
  		  	  				
  		  			newuser.ClickOnNewUserTab();
  		  			
  		  	System.out.println("Step 5 : Verify User has successfully naviagted to 'New Account' page or not.");
  		  	Reporter.log("Step 5 : Verify User has successfully naviagted to 'New Account' page or not.");
  		  	  			 
  		  			if(SeleniumFunc.IsElementPresent(newuser.newaccounttext))
  		  			{
  		  			Thread.sleep(1000);       
  		  				System.out.println("Success !! User has successfully naviagted to 'New Account' page.");
  		  				Reporter.log("Success !! User has successfully naviagted to 'New Account' page."); 
  		  			}
  		  				else
  		  			{
  		  				 System.out.println("Failure !! User is not naviagted to 'New Account' page ");
  		  				 Reporter.log("Failure !! User is not naviagted to 'New Account' page "); 
  		  				 AssertFailedCount++;
  		  			}
    	   	       	   
  		  	System.out.println("Step 6 : Enter valid data for mandatory fields and click on submit button.");
  		  	Reporter.log("Step 6 : Enter valid data for mandatory fields and click on submit button.");   
  		   	  	
  		  			SeleniumFunc.SelectValueFromDropdownListUsingIndex("id","user_agency_id",5);   
  		        	String firstname= "TestngClarion" + JH.GenerateRandomNumber();
  		   	  		String password = "Test"+ JH.GenerateRandomNumber()+"@Test.com";
  		   	  		String emailaddress= "Test"+ JH.GenerateRandomNumber()+"@Test.com";	
  		  	    	Thread.sleep(1000);       
  		  	    	newuser.EnterUsernametext(firstname);
  		  	    	newuser.EnterUserEmail(emailaddress);
  		  	    	newuser.EnterUserPasswardtext(password);
  		  	    	newuser.EnterConfirmPasswardtext(password);
  		  	    	Thread.sleep(1000);  
  		  	    	newuser.ClickOnEditCertificationscheckbox();
  		  	    	newuser.ClickOnByPassPamentcheckbox();	           
  		  	    	newuser.ClickOnSubmitBtnButton();
  		  	    	Thread.sleep(1000);  
  		//Verifying alert message
  		  	 System.out.println("Step 7 : Verify,New user has successfully created.");
  		  	 Reporter.log("Step 7 : Verify,New user has successfully created.");   
  		  				
  		//Verifying alert message
  		  	 		String actualtext= gheader.Success_AlertText.getText().trim();
  		  		 	String expectedtext= "New user created.";			
  		  		 		if(actualtext.equals(expectedtext))
  		  		 		{
  		  		 		   Thread.sleep(1000);
  		  		 			System.out.println("Success !! alert message is displayed i.e. " + actualtext);
  		  					Reporter.log("Success !! alert message is displayed i.e. " + actualtext); 
  		  				}
  		  		 			else
  		  		 			{
  		  					System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  	+ "\n" + expectedtext + "\n" + 
  		    										 "Actual : " + "\n" +  actualtext);
  		  					Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  		+ "\n" + expectedtext + "\n" + 
  		    							             "Actual : " + "\n" +  actualtext);
  		    			   AssertFailedCount++;
  		  		 			}
    	          
  		 // try to create new user with same Email id 
  		  		 	Thread.sleep(2000);	
    	     System.out.println("Step 8 : Enter valid data for mandatory fields using exiting email.");
    	     Reporter.log("Step 8 : Enter valid data for mandatory fields using exiting email."); 
    	       	  	
    	     		Thread.sleep(1000);
    	       	    newuser.clickonadmintab();
    	       	    Thread.sleep(1000);
		  			newuser.ClickOnManageusers();
		  			Thread.sleep(1000);
		  			newuser.ClickOnNewUserTab();
		  			Thread.sleep(1000);
		  			 
    	     	    SeleniumFunc.SelectValueFromDropdownListUsingIndex("id","user_agency_id",5);   	           
        	    	Thread.sleep(1000);       
      				newuser.EnterUsernametext(firstname);
      				newuser.EnterUserEmail(emailaddress);
      				newuser.EnterUserPasswardtext(password);
      				newuser.EnterConfirmPasswardtext(password);
      				
      		 System.out.println("Step 9 : Select roles and permissions");
      		 Reporter.log("Step 9 : Select roles and permissions"); 
      		  		   	           
      		 		Thread.sleep(1000);  
      				newuser.ClickOnEditCertificationscheckbox();
      	  		 	newuser.ClickOnByPassPamentcheckbox();	
      	  		 	
      	  	 System.out.println("Step 10 : Click on Submit button.");
      	  	 Reporter.log("Step 10 : Click on Submit button."); 
 		  		 
      	  		    newuser.ClickOnSubmitBtnButton();
      				Thread.sleep(1000);    	
      				
   
      		//Verifying alert message
          	  System.out.println("Step 11 : Verify,validation message 'There was a problem saving this new user. ' should be displayed");
          	  Reporter.log("Step 11: Verify,validation message 'There was a problem saving this new user. ' should be displayed");
              	    	  		    	
          	  		String Actualvalidation=SeleniumFunc.GetElementText("css",".alert.alert-error");
          	  		String Expectedvalidation="There was a problem saving this new user.";
          	  		if(Actualvalidation.equals(Expectedvalidation))
          	  		{
          	  			System.out.println("Success !! Correct validation message is displayed i.e. " + Actualvalidation);
          	  			Reporter.log("Success !! Correct validation message is displayed i.e. " + Actualvalidation); 
              		}
              			else
              				{
              					System.out.println("Failure !! incorrect validation message is displayed" + "\n" + "Expected : "  	+ "\n" + Expectedvalidation + "\n" + 
              						"Actual : " + "\n" +  Actualvalidation);
              					Reporter.log("Failure !!  incorrect validation message is displayed" + "\n" + "Expected : "  		+ "\n" + Expectedvalidation + "\n" + 
              	    	    							             "Actual : " + "\n" +  Actualvalidation);
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
     	
     
  		/* Test 5   
  		 * Create User - Verify Disable and Enable Users
  		 */
    
  		@Test
  		public void   DisableUsers() throws InterruptedException
  		{
  			System.out.println("====" + "\n" +
    					  "Test 5 : Create new user -  Verify Disable and Enable Users"  + "\n" +
    	    	 			"====");
  			Reporter.log("====" + "\n" +
    	    	 			  "Test 5  : Create new user -  Verify Disable and Enable Users"  + "\n" +
    	    			 	  "====");	
  			
  					int AssertFailedCount=0 ;
  					SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
  					LoginPage login = new LoginPage(driver);
  					CreateNewUserPage  newuser=new CreateNewUserPage(driver);
  					GlobalHeader gheader = new GlobalHeader(driver);
  			System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
  		  	Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
  		  			
  		  		SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM);	
  		  				
  		  	System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
  		  	Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 
  		  			
  		  			login.EnterUsername(Constants.CM_Username);
  		  			login.EnterPassword(Constants.CM_Password);
  		  			login.ClickOnLogInButton(); 			
  		  				 	
  		  	System.out.println("Step 3 : Select 'manage users' from 'Admin' drop down list bar to navigate  'New User' page. ");
  		  	Reporter.log("Step 3 : Select 'manage users' from 'Admin' drop down list bar to navigate  'New User' page. "); 	       
  		  	  				
  		  			newuser.clickonadmintab();
  		  			newuser.ClickOnManageusers();
  		  		    Thread.sleep(2000);
  		  	  				     
  		  	System.out.println("Step 4 : Enter valid data in  Name and/or Email text field and click on search button.");
  		  	Reporter.log("Step 4 : Enter valid data in  Name and/or Email text field and click on search button."); 
  		  	  				
  		  			newuser.EnterUserinfo("test@clariontechnologies.co.in");
  		  			newuser.ClcikOnSearch();
  		  			Thread.sleep(1000);
  		  		
  		  	System.out.println("Step 5 : Click on the first user record that shows up and Click on Edit info button.");
  		    Reporter.log("Step 5 : Click on the first user record that shows up and Click on Edit info button."); 
  				         
  		     		Thread.sleep(1000);		
  		     		newuser.Cliconusertoedit();
  			     	Thread.sleep(2000);
  		  	  		newuser.Clickonedittab();
  		  		
  		  	 System.out.println("Step 6 : Click on check box to make user inactive and Submit");
  		   	 Reporter.log("Step 6 : Click on check box to make user inactive and Submit");    
  		   	 		newuser.ClickOnActivecheckbox();
  		 	  		newuser.ClickOnSubmitBtnButton();   	    
  		  				
  		  	System.out.println("Step 7 : Click on logout button and Login with new user.");
  		  	Reporter.log("Step 7 : Click on logout button and Login with new user.");    
  		  				
  		  	// Logout
  		  			Thread.sleep(1000);
  		  		  	newuser.ClickOnusername();
  		  		  	newuser.ClickonLogout();
  		  		  	Thread.sleep(2000);
  		  				 	
  		  		  	String Emailadress="test@clariontechnologies.co.in";
  		  		  	login.EnterUsername(Emailadress);
  		  		  	login.EnterPassword("clarion@123");
  		  		  	login.ClickOnLogInButton(); 
  		  	  			
  		  	 System.out.println("Step 8 : Verify,User is not  able to login as this is disabled ");
  		   	 Reporter.log("Step 8 : Verify,User is not  able to login as this is disabled ");
  		  	  	  	
  		  	  		String ActualAuthentication= SeleniumFunc.GetElementText("css", ".alert.alert-error");
  		  	  		String ExpectedAuthentication= "Authentication failed, please try again.";
  		  	  			if(ActualAuthentication.equals(ExpectedAuthentication))
  		  	  			{
  		  	  				Thread.sleep(1000);
  		  	  				System.out.println("Success !! Valid  message is displayed i.e. " + ActualAuthentication);
  		  	  				Reporter.log("Success !! Valid  message is displayed i.e. " + ActualAuthentication); 
  		  	  			}
  		  	  				else
  		  	  				{
  		  	  					System.out.println("Failure !! incorrect  message is displayed" + "\n" + "Expected : "  
  			   	        		+ "\n" + ExpectedAuthentication + "\n" + 
  			   							 "Actual : " + "\n" +  ActualAuthentication);
  		  	  					Reporter.log("Failure !!  incorrect  message is displayed" + "\n" + "Expected : "  + "\n" + ExpectedAuthentication + "\n" + 
  			   				 "Actual : " + "\n" +  ActualAuthentication);
  		  	  					AssertFailedCount++;
  		  	  				}
  		  	  /*
  		  	   For disabled user , try to access by forget password route: Use forget password functionality and access disabled account			
  		  	   */
  		  
  		  	//Click on forget password button.
  		  	  		newuser.ClickonForgtepassword();
  		  	  		Thread.sleep(1000);
  		
  		  	  		newuser.Forgetemail(Emailadress);
  		  	  		newuser.SendBtnClick();
  		  	  		Thread.sleep(2000);
  		  	  	
  		  	  		String Actualerror= SeleniumFunc.GetElementText("css", ".alert.alert-error");
  		  	System.out.println("Step 9 : For diabled user , try to access by forget password route: Use forget password functionality and access disabled account");
	  	    Reporter.log("Step 9 : For diabled user , try to access by forget password route: Use forget password functionality and access disabled account");
	  	    
	  	    System.out.println("Step 10 : Verify,'The account is no longer active' message should be displayed ");
	  	    Reporter.log("Step 10 : Verify,'The account is no longer active' message should be displayed ");
	  	    		String Expectederror= "The account associated with "+ Emailadress+" is no longer active. For more help please contact our Agency Support Team.";
	  	    		if(Actualerror.equals(Expectederror))
	  	    		{
	  	    			System.out.println("Success !! Valid alert message is displayed i.e. " + Actualerror);
	  	    			Reporter.log("Success !! Valid alert message is displayed i.e. " + Actualerror); 
	  	    		}	
	  	    			else
	  	    			{
	  	    				System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  	+ "\n" + Expectederror + "\n" +  "Actual : " + "\n" +  Actualerror);
	  	    				Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  + "\n" + Expectederror + "\n" +  "Actual : " + "\n" +  Actualerror);
	  	    				AssertFailedCount++;
	  	    			}	
	  	  					
  		  	  //...........................................................................			
  		  	  			
  	  	  	 System.out.println("Step 11 : Login with valid credentials to make user active.");
  	  	  	 Reporter.log("Step 11 : Login with valid credentials to make user active.");  
  	  	  	 		Thread.sleep(1000);
  	  	  	 		SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM);
  	  	  	 		Thread.sleep(1000);
  	  	  	 		login.EnterUsername(Constants.CM_Username);
  					login.EnterPassword(Constants.CM_Password);
  					login.ClickOnLogInButton(); 	
  					newuser.clickonadmintab();
  					newuser.ClickOnManageusers();
  					Thread.sleep(1000);
  		  	  		newuser.EnterUserinfo("test@clariontechnologies.co.in");
  		  	  		newuser.ClcikOnSearch();
  		  	    	Thread.sleep(1000);
  					newuser.Cliconusertoedit();
  		  	  		Thread.sleep(1000);
  					newuser.Clickonedittab();
  		  	  		Thread.sleep(1000);
  						
  		  	  System.out.println("Step 12 : Click on check box to Set the user back to Active and Click on Submit button");
  		  	  Reporter.log("Step 12 : Click on check box to Set the user back to Active and Click on Submit button");    
  		  	  		newuser.ClickOnActivecheckbox();
  					newuser.ClickOnSubmitBtnButton();   	 
  						
  		  	  System.out.println("Step 13 : Click on logout button and  Login with new user.");
  		  	  Reporter.log("Step 13 : Click on logout button and  Login with new user.");    
  		  	  		// Logout
  		  	  		Thread.sleep(1000);
  		  	  		newuser.ClickOnusername();
  		  	  		newuser.ClickonLogout();
  		  	  		Thread.sleep(2000);
  		  	  		login.EnterUsername("test@clariontechnologies.co.in");
  		  	  	 	login.EnterPassword("clarion@123");
  		  	  	 	login.ClickOnLogInButton(); 
  		  	  			
  		  	  	System.out.println("Step 14 : Verify,User is able to login as this is activated now by Admin");
  		  	  	Reporter.log("Step 14 : Verify,User is able to login as this is activated now by Admin");
  		  	  	
  		            	Thread.sleep(2000);
  		  	  			String actualtext1= gheader.Success_AlertText.getText().trim();
  		  	  			String expectedtext1= "Signed in!"; 
  		  	  			if(actualtext1.equals(expectedtext1))
  		  	  			{
  		  	  				System.out.println("Success !! alert message is displayed i.e. " + actualtext1);
  		  	  				Reporter.log("Success !! alert message is displayed i.e. " + actualtext1); 
  		  	  			}	
  		  	  				else
  		  	  				{
  		  	  					System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  	+ "\n" + expectedtext1 + "\n" +  "Actual : " + "\n" +  actualtext1);
  		  	  					Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  + "\n" + expectedtext1 + "\n" +  "Actual : " + "\n" +  actualtext1);
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
 	 		
  		/* Test 6   
  		 * Create User - Verify new user validation error messages
  		 */
  		@Test
  		public void  NewUserValidation() throws InterruptedException
  		{

  			System.out.println("====" + "\n" +
  					"Test 6 : Create new user - Verify new user validation error messages"  + "\n" +
  					"====");
  			Reporter.log("====" + "\n" + 
  					"Test 6  : Create new user -Verify  new user validation error messages"  + "\n" + 
  					"====");	
    		    	
  					int AssertFailedCount=0 ;
  					SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
  					LoginPage login = new LoginPage(driver);
  					CreateNewUserPage  newuser=new CreateNewUserPage(driver);
  				
  				
  			System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
  			Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");	
  	  				
  					SeleniumFunc.ToGoToUrl(Constants.ApplicationURL_CM);	
  	  				
  			System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
  			Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 
  	  				
  					login.EnterUsername(Constants.CM_Username);
  					login.EnterPassword(Constants.CM_Password);
  					login.ClickOnLogInButton(); 	
  	  			    	   	
  			System.out.println("Step 3 : Select 'manage users' from 'Admin' drop down list bar to navigate  'New User' page. ");
  			Reporter.log("Step 3 : Select 'manage users' from 'Admin' drop down list bar to navigate  'New User' page. "); 	       
  	  		  	  				
  					newuser.clickonadmintab();
  					Thread.sleep(1000);
  					newuser.ClickOnManageusers();
  					Thread.sleep(1000);
  	  		  	  				     
  			System.out.println("Step 4 : Click on New User link");
  			Reporter.log("Step 4 : Click on New User link"); 
  	  		  	  				
  					newuser.ClickOnNewUserTab();
  					Thread.sleep(1000);
  			System.out.println("Step 5 : Verify User has successfully naviagted to 'New Account' page or not.");
  			Reporter.log("Step 5 : Verify User has successfully naviagted to 'New Account' page or not.");
  	  		  	  			 
  					if(SeleniumFunc.IsElementPresent(newuser.newaccounttext))
  						{
  							System.out.println("Success !! User has successfully naviagted to 'New Account' page.");
  	  		  				Reporter.log("Success !! User has successfully naviagted to 'New Account' page."); 
  	  		  			}
  	  		  				else
  	  		  				{
  	  		  					System.out.println("Failure !! User is not naviagted to 'New Account' page ");
  	  		  					Reporter.log("Failure !! User is not naviagted to 'New Account' page "); 
  	  		  					AssertFailedCount++;
  	  		  				}
  	    	   	       	   
  	  		 System.out.println("Step 6 : Enter valid data for mandatory fields using exiting email and click on submit button.");
  	  		 Reporter.log("Step 6 : Enter valid data for mandatory fields using exiting email and click on submit button.");   
  	  		   	  	
  	  		 		SeleniumFunc.SelectValueFromDropdownListUsingIndex("id","user_agency_id",5);   
  	  		 		String firstname= "TestngClarion" + JH.GenerateRandomNumber();
  	  		 		String password = "sanjeetk@clariontechnologies.co.in";
  	  		 		String emailaddress= "sanjeetk@clariontechnologies.co.in";	
  	  		 		Thread.sleep(1000);       
  	  		  	  
  	  		 		newuser.EnterUsernametext(firstname);
  	  		 		newuser.EnterUserEmail(emailaddress);
  	  		 		newuser.EnterUserPasswardtext(password);
  	  		 		newuser.EnterConfirmPasswardtext(password);
  	  				Thread.sleep(1000);  
  	  				newuser.ClickOnEditCertificationscheckbox();
  	  				newuser.ClickOnByPassPamentcheckbox();	           
  	  				newuser.ClickOnSubmitBtnButton();
  	  				Thread.sleep(1000);  
  	  		
  	  			//Verifying alert message
  	  		 System.out.println("Step 7 : Verify,validation message 'has already been taken ' should be displayed");
  	  		 Reporter.log("Step 7: Verify,validation message 'has already been taken ' should be displayed");
  	              	    	  		    	
  	  		 		String Actualvalidation=SeleniumFunc.GetElementText("css",".help-inline");
  	  		 		String Expectedvalidation="has already been taken";
  	  		 			if(Actualvalidation.equals(Expectedvalidation))
  	              			{
  	  		 					System.out.println("Success !! Correct validation message is displayed i.e. " + Actualvalidation);
  	              				Reporter.log("Success !! Correct validation message is displayed i.e. " + Actualvalidation); 
  	              			}
  	              				else
  	              				{
  	              					System.out.println("Failure !! incorrect validation message is displayed" + "\n" + "Expected : "  	+ "\n" + Expectedvalidation + "\n" + 
  	              						"Actual : " + "\n" +  Actualvalidation);
  	              					Reporter.log("Failure !!  incorrect validation message is displayed" + "\n" + "Expected : "  		+ "\n" + Expectedvalidation + "\n" + 
  	              	    	    							             "Actual : " + "\n" +  Actualvalidation);
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


