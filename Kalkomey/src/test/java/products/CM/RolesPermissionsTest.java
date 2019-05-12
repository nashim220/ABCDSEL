package products.CM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.CM.CreateNewCertificationPage;
import pageFactory.CM.CreateNewUserPage;
import pageFactory.CM.DetailsCertificationPage;
import pageFactory.CM.EditCertificationPage;
import pageFactory.CM.GlobalHeader;
import pageFactory.CM.LoginPage;
import pageFactory.CM.ProfilePage;
import pageFactory.CM.RolesPermissionsPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class RolesPermissionsTest {

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
	 * Role Permission  -Verify that admin can view role permissions section 
	 */ 
	@Test
    public void AdminCanViewDetails() throws InterruptedException
    {
   	 		System.out.println("====" + "\n" +
   	 								"Test 1 : Role Permission - Verify that admin can view role permissions section under edit account" + "\n" +
   	 								"====");
   	 		Reporter.log("====" + "\n" +
   	 								"Test 1 : Role Permission - Verify that admin can view role permissions section under edit account"  + "\n"+ 
   	 								"====");	
    
   	 				int AssertFailedCount=0 ;
   	 				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
   	 				LoginPage login = new LoginPage(driver);    	 		
   	 				RolesPermissionsPage role=new RolesPermissionsPage(driver);
   	 				CreateNewUserPage  newuser=new CreateNewUserPage(driver);
   	 		
   	 		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
   	 		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
	
   	 				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

   	 		System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
   	 		Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 	

   	 				login.EnterUsername(Constants.CM_Username);
   	 				login.EnterPassword(Constants.CM_Password);
   	 				login.ClickOnLogInButton();   
   	 		
   	 		System.out.println("Step 3 : Go to manage users > User > Edit account");
   	 		Reporter.log("Step 3 : Go to manage users > User > Edit account"); 
	      
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
			
   	 				//Verify  Presence of Role Permission Text
   	 				if(SeleniumFunc.IsElementPresent(role.RolePermissions))
   	 				{
   	 					System.out.println("Success !! Role Permissions Text is present");
   	 					Reporter.log("Success !!Role Permissions Text is present"); 
   	 				}
   	 				else
   	 				{
   	 					System.out.println("Failure !! Role Permissions Text is not present ");
   	 					Reporter.log("Failure !! Role Permissions Text is not present "); 
   	 					AssertFailedCount++;
   	 				}
		
   	 				//Verifying presence of  Can manage users check box field
   	 				if(SeleniumFunc.IsElementPresent(role.ManageUser))
   	 				{
   	 					System.out.println("Success !! Manage Users  check box is present");
   	 					Reporter.log("Success !! Manage Users  check box is present"); 
   	 				}
   	 				else
   	 				{
   	 					System.out.println("Failure !!Manage Users  check box is not present ");
   	 					Reporter.log("Failure !!Manage Users  check box is not present "); 
   	 					AssertFailedCount++;
   	 				}
			
   	 				//Verifying presence of  Can bypass payments  check box field
   	 				if(SeleniumFunc.IsElementPresent(role.ByPassPament))
   	 				{   
   	 					System.out.println("Success !! Bypass Payments  check box is present");
   	 					Reporter.log("Success !!Bypass Payments  check box is present"); 
   	 				}
   	 				else
   	 				{
   	 					System.out.println("Failure !! Bypass Payments  check box is not present ");
   	 					Reporter.log("Failure !! Bypass Payments  check box is not present "); 
   	 					AssertFailedCount++;
   	 				}
			
   	 				//Verifying presence of  edit certifications  check box   field
   	 				if(SeleniumFunc.IsElementPresent(role.EditCertifications))
   	 				{   
   	 					System.out.println("Success !! Edit Certifications.  check box is present");
   	 					Reporter.log("Success !!Edit Certifications.  check box is present"); 
   	 				}
   	 				else
   	 				{
   	 					System.out.println("Failure !! Edit Certifications.  check box is not present ");
   	 					Reporter.log("Failure !! Edit Certifications.  check box is not present "); 
   	 					AssertFailedCount++;
   	 				}
			
   	 				//Verifying presence of   view reports  check box   field
   	 				if(SeleniumFunc.IsElementPresent(role.ViewReports))
   	 				{   
   	 					System.out.println("Success !! View Reports  check box is present");
   	 					Reporter.log("Success !!View Reports  check box is present"); 
   	 				}
   	 				else
   	 				{
   	 					System.out.println("Failure !! view reports  check box is not present ");
   	 					Reporter.log("Failure !! view reports  check box is not present "); 
   	 					AssertFailedCount++;
   	 				}
   	 				//Verifying presence of   create certifications check box   field
   	 				if(SeleniumFunc.IsElementPresent(role.CertificationCreater))
   	 				{   
   	 					System.out.println("Success !! Create Certifications  check box is present");
   	 					Reporter.log("Success !!Create Certifications check box is present"); 
   	 				}
   	 				else
   	 				{
   	 					System.out.println("Failure !!Create Certifications  check box is not present ");
   	 					Reporter.log("Failure !!Create Certifications  check box is not present "); 
   	 					AssertFailedCount++;
   	 				}
			
   	 				//Verifying presence of   edit collections. check box field
   	 				if(SeleniumFunc.IsElementPresent(role.EditCollections))
   	 				{   
   	 					System.out.println("Success !! Edit Collections.  check box is present");
   	 					Reporter.log("Success !!Edit Collections. check box is present"); 
   	 				}
   	 				else
   	 				{
   	 					System.out.println("Failure !! Edit Collections. check box is not present ");
   	 					Reporter.log("Failure !! Edit Collections.  check box is not present "); 
   	 					AssertFailedCount++;
   	 				}
	        	
   	 				//Verifying presence of    add notes check box   field
   	 				if(SeleniumFunc.IsElementPresent(role.AddNotes))
   	 				{   
   	 					System.out.println("Success !! Add Notes  text Box is present");
   	 					Reporter.log("Success !!Add Notes text box is present"); 
   	 				}
   	 				else
   	 				{
   	 					System.out.println("Failure !! Add Notes text box is not present ");
   	 					Reporter.log("Failure !! Add Notes  text box is not present "); 
   	 					AssertFailedCount++;
   	 				}
   	 				//Verifying presence of order permanent certification cards. check box   field
   	 				if(SeleniumFunc.IsElementPresent(role.CanOrderpermanentCertificationCards))
   	 				{   
   	 					System.out.println("Success !! Order Permanent Certification Cards  check box is present");
   	 					Reporter.log("Success !!Order Permanent Certification Cards check box is present"); 
   	 				}
   	 				else
   	 				{
   	 					System.out.println("Failure !! Order Permanent Certification Cards  check box is not present ");
   	 					Reporter.log("Failure !! Order Permanent Certification Cards   check box is not present "); 
   	 					AssertFailedCount++;
   	 				}
			
   	 				//Verifying presence of send course completion documents. check box field
   	 				if(SeleniumFunc.IsElementPresent(role.SendCourceCompletionDoc))
   	 				{   
   	 					System.out.println("Success !! Send Course Completion Documents  check box is present");
   	 					Reporter.log("Success !!Send Course Completion Documents check box is present"); 
   	 				}
   	 				else
   	 				{
   	 					System.out.println("Failure !! Send Course Completion Documents check box is not present ");
   	 					Reporter.log("Failure !! Send Course Completion Documents check box is not present "); 
   	 					AssertFailedCount++;
   	 				}
			
   	 				//Verifying presence of import certifications for their agency check box   field
   	 				if(SeleniumFunc.IsElementPresent(newuser.clickOnimportcertifications))
   	 				{   
   	 					System.out.println("Success !! Import Certifications for their agency  check box is present");
   	 					Reporter.log("Success !Import Certifications for their agency check box is present"); 
   	 				}
   	 				else
   	 				{
   	 					System.out.println("Failure !! Import Certifications for their agency check box is not present ");
   	 					Reporter.log("Failure !! Import Certifications for their agency  check box is not present "); 
   	 					AssertFailedCount++;
   	 				}
   	 				if(SeleniumFunc.IsElementPresent(role.CanManageAgencies))
   	 				{   
   	 					System.out.println("Success !! Can Manage Agencies  check box is present");
   	 					Reporter.log("Success !Can Manage Agencies  check box is present"); 
   	 				}
   	 				else
   	 				{
   	 					System.out.println("Failure !! Can Manage Agenciescheck box is not present ");
   	 					Reporter.log("Failure !! Can Manage Agencies  check box is not present "); 
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
	     *Role Permission - Verify that Admin can mark/unmark permissions for users via the permissions page.
	     */ 
		 @Test
	     public void  AdminCanMarkUnmarkDetails() throws InterruptedException
	     {
			 System.out.println("====" + "\n" +	
					 				"Test 2 : Role Permission - Verify that Admin can mark/unmark permissions for users via the permissions page." + "\n" +	
					 				"====");
			 Reporter.log("====" + "\n" +
					 				"Test 2 : Role Permission - Verify that Admin can mark/unmark permissions for users via the permissions page."  + "\n" + 
					 				"====");	
		    
			 		int AssertFailedCount=0 ;
			 		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			 		LoginPage login = new LoginPage(driver);    	 		
			 		RolesPermissionsPage role=new RolesPermissionsPage(driver);
			 		GlobalHeader gheader = new GlobalHeader(driver);	
			 		CreateNewUserPage  newuser=new CreateNewUserPage(driver);
			 	
			 System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
			 Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			 		SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

			 System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
			 Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 

			 		login.EnterUsername(Constants.CM_Username);
			 		login.EnterPassword(Constants.CM_Password);
			 		login.ClickOnLogInButton();   
	   	 		
			 System.out.println("Step 3 : Go to manage users > User > Edit account");
			 Reporter.log("Step 3 : Go to manage users > User > Edit account"); 
		      
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
				
			 		//Verify  Presence of Role Permission Text
			 		if(SeleniumFunc.IsElementPresent(role.RolePermissions))
			 		{
		  				System.out.println("Success !! Role Permissions Text is present");
		  				Reporter.log("Success !!Role Permissions Text is present"); 
			 		}
			 		else
			 		{
			 			System.out.println("Failure !! Role Permissions Text is not present ");
			 			Reporter.log("Failure !! Role Permissions Text is not present "); 
			 			AssertFailedCount++;
			 		}
			    	
			 System.out.println("Step 4 : Navigate  to roles and permissions section > mark / unmark options,Submit");
			 Reporter.log("Step 4 : Navigate  to roles and permissions section > mark / unmark options,Submit"); 
			    
			        if(!role.ImportCertificationsForAgency.isSelected())
			        {
			 		role.ClickOnCanImportCertificationsForAgency();
			        }
			        if(!role.CanManageAgencies.isSelected())
			        {
			 		role.ClickOnCanManageAgencies();
			        }
			 		newuser.EnterUserPasswardtext("clarion@123");
			 		newuser.EnterConfirmPasswardtext("clarion@123");
			 		Thread.sleep(1000);
			 		newuser.ClickOnSubmitBtnButton();
			 		Thread.sleep(1000);
		        
			 		//Verifying alert message
			 		String actualtext= gheader.Success_AlertText.getText().trim();
			 		String expectedtext= "User updated.";			
			 		if(actualtext.equals(expectedtext))
			 		{
			 			System.out.println("Success !! alert message is displayed i.e. " + actualtext);
			 			Reporter.log("Success !! alert message is displayed i.e. " + actualtext); 
			 		}
			 		else
			 		{
			 			System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  
	  							+ "\n" + expectedtext + "\n" +  "Actual : " + "\n" +  actualtext);
			 			Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  
	    							+ "\n" + expectedtext + "\n" +  "Actual : " + "\n" +  actualtext);
	    					
			 			AssertFailedCount++;
			 		} 
			 		Thread.sleep(1000);
	    	
			 System.out.println("Step 5 : Navigate  to permissions section and verify  the role & permissions is marked ");
			 Reporter.log("Step 5 : Navigate  to permissions section and verify  the role & permissions is marked  "); 
			    
			 		role.ClickOnEditInfo();
			 		//Verify Role Permission Is not  Available  With user
			 		if(driver.findElement(By.id("user_roles_mask_agency_importer")).isSelected())
			 		{
			 			System.out.println("Success !! Agency importer role & permissions is marked");
			 			Reporter.log("Success !!Success !! Agency importer role & permissions is marked"); 
			 		}
			 		else
			 		{
			 			System.out.println("Failure!!  Success !! Agency importer role & permissions is not mark");
			 			Reporter.log(" Failure!! Success !! Agency importer role & permissions is not mark"); 
			 			AssertFailedCount++;
			 		}
			 		Thread.sleep(1000);
			 		if(driver.findElement(By.id("user_roles_mask_agency_administrator")).isSelected())
			 		{
			 			System.out.println("Success !! manage agencies role & permissions is marked");
			 			Reporter.log("Success !!Success !! manage agencies role & permissions is marked"); 
		    		
			 		}
			 		else
			 		{
			 			System.out.println("Failure!! manage agencies role & permissions is not mark");
			 			Reporter.log(" Failure!! manage agencies role & permissions is not mark"); 
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
		   * Role Permission- Verify that admin can remove all permissions by unchecking all options
		   */ 
			@Test
		    public void  AdminCanRemoveAllPermissions() throws InterruptedException
		    {
					System.out.println("====" + "\n" +
							"Test 3 : Role Permission - Verify that admin can remove all permissions by unchecking all options." + "\n" +
							"====");
					Reporter.log("====" + "\n" +
							"Test 3 : Role Permission - Verify that admin can remove all permissions by unchecking all options"  + "\n" +
							"====");	
			    
							int AssertFailedCount=0 ;
							SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
							LoginPage login = new LoginPage(driver);    	 		
							RolesPermissionsPage role=new RolesPermissionsPage(driver);
							GlobalHeader gheader = new GlobalHeader(driver);	
							CreateNewUserPage  newuser=new CreateNewUserPage(driver);
					
					System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		   	 		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
				
		   	 				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

		   	 		System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
		   	 		Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 
	  		  			
		   	 				login.EnterUsername(Constants.CM_Username);
		   	 				login.EnterPassword(Constants.CM_Password);
		   	 				login.ClickOnLogInButton(); 			 
		   	 		
		   	 		System.out.println("Step 3 : Go to manage users > User > Edit account");
		   	 		Reporter.log("Step 3 : Go to manage users > User > Edit account"); 
	  			      
		   	 				newuser.clickonadmintab();
		   	 				newuser.ClickOnManageusers();
		   	 				Thread.sleep(1000);
		   	 				newuser.EnterUserinfo("test4@clariontechnologies.co.in");
		   	 				newuser.ClcikOnSearch();
		   	 				Thread.sleep(1000);
		   	 				newuser.Cliconusertoedit();
		   	 				Thread.sleep(1000);
		   	 				newuser.Clickonedittab();
		   	 				Thread.sleep(1000);
	  					
		   	 				//Verify  Presence of Role Permission Text
		   	 				if(SeleniumFunc.IsElementPresent(role.RolePermissions))
		   	 				{
	  			  				System.out.println("Success !! Role Permissions Text is present");
	  			  				Reporter.log("Success !!Role Permissions Text is present"); 
		   	 				}
		   	 				else
		   	 				{
		   	 					System.out.println("Failure !! Role Permissions Text is not present ");
		   	 					Reporter.log("Failure !! Role Permissions Text is not present "); 
		   	 					AssertFailedCount++;
		   	 				}
	  				    	
		   	 		System.out.println("Step 4 : Navigate  to roles and permissions section > Uncheck all permissions granted,Submit");
		   	 		Reporter.log("Step 4 : Navigate  to roles and permissions section > Uncheck all permissions granted,Submit"); 
		   	 				
		   	 				if(SeleniumFunc.IsElementPresent(role.RemoveAllPermissions))	
		   	 					{
		   	 						role.ClickOnRemoveAllPermissions();
		   	 					}
		   	 				newuser.EnterUserPasswardtext("clarion@123");
		   	 				newuser.EnterConfirmPasswardtext("clarion@123");
		   	 				Thread.sleep(1000);
		   	 				newuser.ClickOnSubmitBtnButton();
		   	 				Thread.sleep(1000);
		   	 				// Login  for Updated user   
		   	 				newuser.ClickOnusername();
		   	 				newuser.ClickonLogout();
		   	 				Thread.sleep(1000);
		  				 	
		   	 		System.out.println("Step 5 : Login with user 1.");
		   	 		Reporter.log("Step 5 : Login with user 1.");
		  	  			
	  	  					login.EnterUsername(Constants.CM_User_Remove_Permission);
	  	  					login.EnterPassword(Constants.CM_Pwd_Remove_Permission);
	  	  					login.ClickOnLogInButton(); 
	  	  					Thread.sleep(2000);
	  	  					String actualtext12= gheader.Success_AlertText.getText().trim();
	  	  					String expectedtext12= "Signed in!";
	  	  					if(actualtext12.equals(expectedtext12))
	  	  					{
	  	  						System.out.println("Success !! Login  message is displayed i.e. " + actualtext12);
	  	  						Reporter.log("Success !! Login  message is displayed i.e. " + actualtext12); 
	  	  					}
	  	  					else
	  	  					{
	  	  						System.out.println("Failure !! incorrect  message is displayed" + "\n" + "Expected : "  
	  	  								+ "\n" + expectedtext12 + "\n" + 
	  	  								"Actual : " + "\n" +  actualtext12);
	  	  						Reporter.log("Failure !!  incorrect  message is displayed" + "\n" + "Expected : "  
	  	  								+ "\n" + expectedtext12 + "\n" + 
	  	  								"Actual : " + "\n" +  actualtext12);
	  	  						AssertFailedCount++;
	  	  					}
		  				
	  	  			System.out.println("Step 6 : Verifying  user have  Read only state ");
	  	  			Reporter.log("Step 6 : Verifying  user have Read only state ");
				       
	  	  					//User which has been set on Read only state, should be able to only view and he can no do any add or edits
	  	  					if(!SeleniumFunc.IsElementPresent(newuser.ClickonAdmin))
	  	  					{	   
	  	  						System.out.println("Success !! Admin Link is not present and user is  Read only state");
	  	  						Reporter.log("Success !! Admin Link is not present and user is  Read only state"); 
	  	  					}
	  	  					else
	  	  					{	  	  				
	  	  						System.out.println(" Failure!! Admin Link is present");
	  	  						Reporter.log("Failure !Admin Link is present"); 
	  	  						AssertFailedCount++;
	  	  					}
	  	  					
	  	  			// re-assign persmission
	  	  				System.out.println("Step 6 : Reset User successfully ");
		  	  			Reporter.log("Step 6 : Reset user!! ");
		  	  			
	  	  					SeleniumFunc.ClickOnElement("css", "body > div.navbar.navbar-inverse.navbar-fixed-top > div > div > div > ul > li > a");
	  	  					Thread.sleep(2000);
	  	  					SeleniumFunc.ClickOnElement("css", "body > div.navbar.navbar-inverse.navbar-fixed-top > div > div > div > ul > li > ul > li:nth-child(2) > a");
	  	  					Thread.sleep(4000);
	  	  					
	  	  					//SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

		   	 					login.EnterUsername(Constants.CM_Username);
		   	 					Thread.sleep(1000);
		   	 					login.EnterPassword(Constants.CM_Password);
		   	 					Thread.sleep(1000);
		   	 					login.ClickOnLogInButton(); 
		   	 					Thread.sleep(1000);
			   	 				newuser.clickonadmintab();
			   	 				Thread.sleep(1000);
			   	 				newuser.ClickOnManageusers();
			   	 				Thread.sleep(1000);
			   	 				newuser.EnterUserinfo("test4@clariontechnologies.co.in");
			   	 				newuser.ClcikOnSearch();
			   	 				Thread.sleep(1000);
			   	 				newuser.Cliconusertoedit();
			   	 				Thread.sleep(1000);
			   	 				newuser.Clickonedittab();
			   	 				Thread.sleep(1000);
			   	 				
			   	 			 if(!role.ImportCertificationsForAgency.isSelected())
						        {
						 		role.ClickOnCanImportCertificationsForAgency();
						        }
						        if(!role.CanManageAgencies.isSelected())
						        {
						 		role.ClickOnCanManageAgencies();
						        }
						 		newuser.EnterUserPasswardtext("clarion@123");
						 		newuser.EnterConfirmPasswardtext("clarion@123");
						 		Thread.sleep(1000);
						 		newuser.ClickOnSubmitBtnButton();
						 		Thread.sleep(1000);
						 		newuser.ClickOnusername();
			   	 				newuser.ClickonLogout();
	  	  					
			   	 				
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
	     * Role Permission - Verify user with permission 'Can Manage user' is able to Manage user
	     */ 
     	  @Test
	      public void  UserAbletoManageUser() throws InterruptedException
	      {
     		  		System.out.println("====" + "\n" +
     		  					"Test 4 : Role Permission - Verify user with permission 'Can Manage user' is able to Manage user" + "\n" 
     		  					+"====");
     		  		Reporter.log("====" + "\n" +
     		  					"Test 4 : Role Permission - Verify user with permission 'Can Manage user' is able to Manage user"  + "\n" 
     		  					+ "====");	
				    
     		  				int AssertFailedCount=0 ;
     		  				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
     		  				LoginPage login = new LoginPage(driver);    	 		
     		  				RolesPermissionsPage role=new RolesPermissionsPage(driver);
     		  				GlobalHeader gheader = new GlobalHeader(driver);	
     		  				CreateNewUserPage  newuser=new CreateNewUserPage(driver);
     		  			
     		  		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
    		   	 	Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
    				
    		   	 			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

    		   	 	System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
    	  		  	Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 
    	  		  			
    	  		  			login.EnterUsername(Constants.CM_Username);
    	  		  			login.EnterPassword(Constants.CM_Password);
    	  		  			login.ClickOnLogInButton(); 			 
    		   	 		
    	  		  	System.out.println("Step 3 : Go to manage users > User > Edit account");
    	  		  	Reporter.log("Step 3 : Go to manage users > User > Edit account"); 
    	  			      
    	  		  			newuser.clickonadmintab();
    	  		  			newuser.ClickOnManageusers();
    	  		  			Thread.sleep(1000);
    	  			  		newuser.EnterUserinfo("test20@clariontechnologies.co.in");
    	  			  		newuser.ClcikOnSearch();
    	  			    	Thread.sleep(1000);
    	  					newuser.Cliconusertoedit();
    	  			  		Thread.sleep(1000);
    	  					newuser.Clickonedittab();
    	  			  		Thread.sleep(1000);
    	  					//Verify  Presence of Role Permission Text
    	  					if(SeleniumFunc.IsElementPresent(role.RolePermissions))
    	  					{
    	  						System.out.println("Success !! Role Permissions Text is present");
    	  						Reporter.log("Success !!Role Permissions Text is present"); 
    	  					}
    	  					else
    	  					{
    	  						System.out.println("Failure !! Role Permissions Text is not present ");
    	  						Reporter.log("Failure !! Role Permissions Text is not present "); 
    	  						AssertFailedCount++;
    	  					}
					    	
    	  			System.out.println("Step 4 : Navigate  to permissions section > Check 'Can Manage' user option and Submit");
    	  			Reporter.log("Step 4 : Navigate to permissions section > Check 'Can Manage' user option and Submit"); 
					 
    	  					if(!role.ManageUser.isSelected())
    	  					{
    	  						role.ClickOnManageUser();
    	  					}
    	  					newuser.EnterUserPasswardtext("clarion@123");
    	  					newuser.EnterConfirmPasswardtext("clarion@123");
    	  					Thread.sleep(1000);
    	  					newuser.ClickOnSubmitBtnButton();
    	  					Thread.sleep(1000);
    	  					// Login  for Updated user   
    	  					newuser.ClickOnusername();
    	  					newuser.ClickonLogout();
    	  					Thread.sleep(1000);
		  				 	
    	  			System.out.println("Step 5 : Login with user 1.");
    	  			Reporter.log("Step 5 : Login with user 1.");
		  	  			
	  	  					login.EnterUsername(Constants.CM_User_CanManageUser);
	  	  					login.EnterPassword(Constants.CM_Pwd_CanManageUser);
	  	  					login.ClickOnLogInButton(); 
	  	  					Thread.sleep(2000);
	  	  					String actualtext12= gheader.Success_AlertText.getText().trim();
	  	  					String expectedtext12= "Signed in!";
	  	  					if(actualtext12.equals(expectedtext12))
	  	  					{
	  	  						System.out.println("Success !! Login  message is displayed i.e. " + actualtext12);
	  	  						Reporter.log("Success !! Login  message is displayed i.e. " + actualtext12); 
	  	  					}
	  	  					else
	  	  					{
	  	  						System.out.println("Failure !! incorrect  message is displayed" + "\n" + "Expected : "  
	  	  								+ "\n" + expectedtext12 + "\n" + 
	  	  								"Actual : " + "\n" +  actualtext12);
	  	  						Reporter.log("Failure !!  incorrect  message is displayed" + "\n" + "Expected : "  
	  	  								+ "\n" + expectedtext12 + "\n" + 
	  	  								"Actual : " + "\n" +  actualtext12);
	  	  						AssertFailedCount++;
	  	  					}
		  				
	  	  			System.out.println("Step 6 : Verifying  user have  Read  state ");
	  	  			Reporter.log("Step 6 : Verifying  user have Read state ");
  	  		  
	  	  					newuser.clickonadmintab();      
	  	  					Thread.sleep(2000);  	
	  	  					if(SeleniumFunc.IsElementPresent(newuser.ClickonAdmin))
	  	  					{   
	  	  						System.out.println("Success !!  Admin Link is present");
	  	  						Reporter.log("Success !! Admin Link is present"); 
	  	  					}
	  	  					else
	  	  					{
	  	  						System.out.println("Failure !! Admin Link is not present ");
	  	  						Reporter.log("Failure !! Admin Link is not present "); 
	  	  						AssertFailedCount++;
	  	  					}
	  	  			//Verify Manage user link is present of not 
	  	  					if(SeleniumFunc.IsElementPresent(newuser.Manageusers))
	  	  					{   
	  	  						System.out.println("Success !! Manage users Link is present");
	  	  						Reporter.log("Success !! Manage users Link is present"); 
  	  		  				}
	  	  					else
	  	  					{
	  	  						System.out.println("Failure !! Manage users Link is not present ");
	  	  						Reporter.log("Failure !! Manage users Link is not present "); 
	  	  						AssertFailedCount++;
	  	  					}
				    
	  	  					//Verify Certification Activity Link is present or not 
	  	  					if(SeleniumFunc.IsElementPresent(role.CertificationActivity))
	  	  					{   
	  	  						System.out.println("Success !! Certification Activity Link is present");
	  	  						Reporter.log("Success !! Certification Activity Link is present"); 
	  	  					}
	  	  					else
	  	  					{
	  	  						System.out.println("Failure !! Certification Activity Link is NOT present ");
	  	  						Reporter.log("Failure !! Certification Activity Link is NOT present "); 
	  	  						AssertFailedCount++;
	  	  					}
  	  		  		
	  	  					//Singut the user 
	  	  					Thread.sleep(1000);
	  	  					newuser.ClickOnusername();
	  	  					newuser.ClickonLogout();
	  	  					Thread.sleep(1000);
  				
	  	  			System.out.println("Step 7 : Navigate to admin > remove this permission ");
	  	  			Reporter.log("Step 7 : Navigate to admin > remove this permission");
		  	  		
	  	  					login.EnterUsername(Constants.CM_Username);
	  	  					login.EnterPassword(Constants.CM_Password);
	  	  					login.ClickOnLogInButton(); 
	  	  			//Navigate to user 
	  	  					newuser.clickonadmintab();
	  	  					newuser.ClickOnManageusers();
	  	  					Thread.sleep(1000);
	  	  					newuser.EnterUserinfo("test20@clariontechnologies.co.in");
	  	  					newuser.ClcikOnSearch();
	  	  					Thread.sleep(1000);
	  	  					newuser.Cliconusertoedit();
	  	  					Thread.sleep(1000);
	  	  					newuser.Clickonedittab();
	  	  					Thread.sleep(1000);
	  	  					role.ClickOnManageUser();
	  	  					newuser.EnterUserPasswardtext("clarion@123");
	  	  					newuser.EnterConfirmPasswardtext("clarion@123");
	  	  					Thread.sleep(1000);
	  	  					newuser.ClickOnSubmitBtnButton();
	  	  					Thread.sleep(1000);
	  	  			// Login  for Updated user   
	  	  					newuser.ClickOnusername();
	  	  					newuser.ClickonLogout();
	  	  					Thread.sleep(1000);
			    	
	  	  			System.out.println("Step 8 : Login with user 1.");
		  	  		Reporter.log("Step 8 : Login with user 1.");
			  	  			
		  	  				login.EnterUsername(Constants.CM_User_CanManageUser);
		  	  				login.EnterPassword(Constants.CM_Pwd_CanManageUser);
		  	  				login.ClickOnLogInButton(); 
		  	  				Thread.sleep(2000);  
						    
		  	  		System.out.println("Step 9 : Verify A warning message 'You are not authorized to look at this page.' should be displayed");
		  	  		Reporter.log("Step 9 : VerifyA warning message 'You are not authorized to look at this page.' should be displayed");
							       
		  	  				SeleniumFunc.ToGoToUrl("https://kps.staging.kalkomey.com/users/search");
		  	  				String error=SeleniumFunc.GetElementText("css", ".dialog>h1");
		  	  				if(error.equals("You are not authorized to look at this page."))
		  	  				{   
		  	  					System.out.println("Success !! 'You are not authorized to look at this page.' message is displayed.");
		  	  					Reporter.log("Success !! 'You are not authorized to look at this page.' message is displayed."); 
					  	  	}
		  	  				else
		  	  				{
		  	  					System.out.println("Failure !! 'You are not authorized to look at this page.' message is not displayed.");
		  	  					Reporter.log("Failure !! 'You are not authorized to look at this page.' message is not displayed."); 
		  	  					AssertFailedCount++;
		  	  				}
		  	  				
		  	  				driver.navigate().back();
		  	  				SeleniumFunc.ClickOnElement("css", "body > div.navbar.navbar-inverse.navbar-fixed-top > div > div > div > ul > li > a");
		  	  				Thread.sleep(1000);
		  	  				SeleniumFunc.ClickOnElement("css", "body > div.navbar.navbar-inverse.navbar-fixed-top > div > div > div > ul > li > ul > li:nth-child(2) > a");
		  	  				//newuser.ClickonLogout();
		  	  				Thread.sleep(2000);
		  	  				
				  	  		System.out.println("Step 10 : Reset user to default role");
				  	  		Reporter.log("Step 9 : Reset user to default role");
				  	  		login.EnterUsername(Constants.CM_Username);
				  	  		login.EnterPassword(Constants.CM_Password);
				  	  		login.ClickOnLogInButton(); 
				  	  		newuser.clickonadmintab();
				  	  		newuser.ClickOnManageusers();
				  	  		Thread.sleep(1000);
				  	  		newuser.EnterUserinfo("test20@clariontechnologies.co.in");
				  	  		newuser.ClcikOnSearch();
				  	  		Thread.sleep(1000);
				  	  		newuser.Cliconusertoedit();
				  	  		Thread.sleep(1000);
				  	  		newuser.Clickonedittab();
				  	  		Thread.sleep(1000);
				  	  		role.ClickOnManageUser();
				  	  		newuser.EnterUserPasswardtext("clarion@123");
				  	  		newuser.EnterConfirmPasswardtext("clarion@123");
				  	  		Thread.sleep(1000);
				  	  		newuser.ClickOnSubmitBtnButton();
				  	  		Thread.sleep(1000);
				  	  		System.out.println("Step 10 : Sucessfully updated user to its default role!! ");
				  	  		Reporter.log("Step 10 : Sucessfully updated user to its default role!!");
		  	  				
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
			* Role Permission - Verify that a user with permission 'Can Edit Certifications' should be able to edit Certifications
		    */ 
			@Test
		    public void  UserAbletoCanEditCertifications() throws InterruptedException
     	    {
					System.out.println("====" + "\n"+ 
								 "Test 5 : Role Permission - Verify that a user with permission 'Can Edit Certifications' should be able to edit Certifications "+ "\n" +
									"====");
					Reporter.log("====" + "\n" +
								"Test 5 :Role Permission - Verify that a user with permission 'Can Edit Certifications' should be able to edit Certifications"  + "\n" +
									"====");	
					    					
				
							int AssertFailedCount=0 ;
							SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
							LoginPage login = new LoginPage(driver);    	 		
							RolesPermissionsPage role=new RolesPermissionsPage(driver);
							GlobalHeader gheader = new GlobalHeader(driver);	
							CreateNewUserPage  newuser=new CreateNewUserPage(driver);
							EditCertificationPage editcer = new EditCertificationPage(driver);
 		  		
					System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		   	 		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
				
		   	 				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

		   	 		System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
		   	 		Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 
	  		  			
		   	 				login.EnterUsername(Constants.CM_Username);
		   	 				login.EnterPassword(Constants.CM_Password);
		   	 				login.ClickOnLogInButton(); 			 
		   	 		
		   	 		System.out.println("Step 3 : Go to manage users > User > Edit account");
		   	 		Reporter.log("Step 3 : Go to manage users > User > Edit account"); 
	  			      
		   	 				newuser.clickonadmintab();
		   	 				newuser.ClickOnManageusers();
		   	 				Thread.sleep(1000);
		   	 				newuser.EnterUserinfo("test9@clariontechnologies.co.in");
		   	 				newuser.ClcikOnSearch();
		   	 				Thread.sleep(1000);
		   	 				newuser.Cliconusertoedit();
		   	 				Thread.sleep(1000);
		   	 				newuser.Clickonedittab();
		   	 				Thread.sleep(1000);
		   	 				//Verify  Presence of Role Permission Text
		   	 				if(SeleniumFunc.IsElementPresent(role.RolePermissions))
		   	 				{
	  			  				System.out.println("Success !! Role Permissions Text is present");
	  			  				Reporter.log("Success !!Role Permissions Text is present"); 
		   	 				}
		   	 				else
		   	 				{
		   	 					System.out.println("Failure !! Role Permissions Text is not present ");
		   	 					Reporter.log("Failure !! Role Permissions Text is not present "); 
		   	 					AssertFailedCount++;
		   	 				}
				    	
		   	 		System.out.println("Step 4 : Navigate  to permissions section > Check 'Can Edit Certifications' user option and Submit");
		   	 		Reporter.log("Step 4 : Navigate to permissions section > Check 'Can Edit Certifications' user option and Submit"); 
				 
		   	 			if(!role.EditCertifications.isSelected())
		   	 			{
		   	 				role.ClickOnEditCertifications();
		   	 			}
		   	 			newuser.EnterUserPasswardtext("clarion@123");
		   	 			newuser.EnterConfirmPasswardtext("clarion@123");
		   	 			Thread.sleep(1000);
		   	 			newuser.ClickOnSubmitBtnButton();
		   	 			Thread.sleep(1000);
		    	
		   	 			// Login  for Updated user   
		   	 			newuser.ClickOnusername();
		   	 			newuser.ClickonLogout();
		   	 			Thread.sleep(1000);
	  				 	
		   	 	System.out.println("Step 5 : Login with user 1.");
		   	 	Reporter.log("Step 5 : Login with user 1.");
	  	  			
  	  					login.EnterUsername(Constants.CM_User_Edit_Cert);
  	  					login.EnterPassword(Constants.CM_Pwd_Edit_Cert);
  	  					login.ClickOnLogInButton(); 
  	  					Thread.sleep(2000);
  	  					String actualtext12= gheader.Success_AlertText.getText().trim();
  	  					String expectedtext12= "Signed in!";
  	  					if(actualtext12.equals(expectedtext12))
  	  					{
  	  						System.out.println("Success !! Login  message is displayed i.e. " + actualtext12);
  	  						Reporter.log("Success !! Login  message is displayed i.e. " + actualtext12); 
  	  					}
  	  					else
  	  					{
  	  						System.out.println("Failure !! incorrect  message is displayed" + "\n" + "Expected : "  
  	  								+ "\n" + expectedtext12 + "\n" + 
  	  								"Actual : " + "\n" +  actualtext12);
  	  						Reporter.log("Failure !!  incorrect  message is displayed" + "\n" + "Expected : "  
  	  								+ "\n" + expectedtext12 + "\n" + 
  	  								"Actual : " + "\n" +  actualtext12);
  	  						AssertFailedCount++;
  	  					}
			  				
  	  			System.out.println("Step 6 : Verifying  Edit button is enabled under certifications details and User should be able to edit Certifications");
  	  			Reporter.log("Step 6 : Verifying  Edit button is enabled under certifications details and User should be able to edit Certifications");
  	  			
  	  					Thread.sleep(2000);
  	  					editcer.SearchCM("Testing CertificationDetail");
  	  					Thread.sleep(1000);
  	  					editcer.ClickOnSearch();
  	  					editcer.ClickOnStudentNametab();
  	  					Thread.sleep(1000);
			  	  		if((role.EditInfoLink.isEnabled()))
			  	  		{	   
			  	  			System.out.println("Success !! Edit Info Link is enabled");
			  	  			Reporter.log("Success !! Edit Info Link is enabled"); 
			  	  		}
			  	  		else
			  	  		{
			  	  			System.out.println("Failure !! Edit Info Link is not enabled");
			  	  			Reporter.log("Failure !! Edit Info Link is not enabled "); 
			  	  			AssertFailedCount++;
			  	  		}
  					
			  	  		Thread.sleep(1000);	
			  	  		role.ClickOnEditInfo();	 				
  					  					
			  	  		if(SeleniumFunc.IsElementPresent(role.EditStudentInformation1))
			  	  		{   
			  	  			System.out.println("Success !! User is able to edit Certifications");
			  	  			Reporter.log("Success !! User is able to edit Certifications"); 
			  	  		}
			  	  		else
			  	  		{
			  	  			System.out.println("Failure !! User is not able to edit Certifications");
			  	  			Reporter.log("Failure !! User is not able to edit Certifications"); 
			  	  			AssertFailedCount++;
			  	  		}
  					
			  	  System.out.println("Step 7 :  Change the information of any address field to the desired input and Click on Continue button.");
			  	  Reporter.log("Step 7 :  Change the information of any address field to the desired input and Click on Continue button.");
			  	  		
			  	  		Thread.sleep(1000);	     		
			  	  		editcer.EnterStreetAddress("dalmiza");
			  	  		editcer.Entercaddress2("Main Street");	
			  	  		editcer.Getsubmitt();
			  	  		Thread.sleep(2000);		
			  	  		editcer.ClickOnuseaddress1();     
			  	  		/* Thread.sleep(2000);	
  					   	editcer.ClickOnuseaddress2();*/
			  	  		Thread.sleep(2000);	
			  	  		editcer.ClickOnSaveBtn();
  					 		     		
			  	  System.out.println("Step 8 : Verifying changes has updated successfully or not");
			  	  Reporter.log("Step 8 : Verifying changes has updated successfully or not"); 
  					 		     		
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
			 * Role Permission - Verify permission 'Can create certifications
			 */ 
			@Test
			public void  VerifyPermissionCanCreateCertifications() throws InterruptedException
			{
				System.out.println("====" + "\n" +
							"Test 6 : Role Permission - Verify permission can create certifications" + "\n" +
							"====");
				Reporter.log("====" + "\n" + "Test 6 : Role Permission - Verify permission can create certifications"  + "\n" +  
							"====");	
						    
						int AssertFailedCount=0 ;
						SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
						LoginPage login = new LoginPage(driver);    	 		
						RolesPermissionsPage role=new RolesPermissionsPage(driver);
						GlobalHeader gheader = new GlobalHeader(driver);	
						CreateNewUserPage  newuser=new CreateNewUserPage(driver);
						EditCertificationPage editcer = new EditCertificationPage(driver);
						CreateNewCertificationPage createnewcerti = new CreateNewCertificationPage(driver);
 		  		
				System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		   	 	Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
				
		   	 			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

		   	 	System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
	  		  	Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 
	  		  			
	  		  			login.EnterUsername(Constants.CM_Username);
	  		  			login.EnterPassword(Constants.CM_Password);
	  		  			login.ClickOnLogInButton(); 			 
		   	 		
	  		  	System.out.println("Step 3 : Go to manage users > User > Edit account");
	  		  	Reporter.log("Step 3 : Go to manage users > User > Edit account"); 
	  			      
	  		  			newuser.clickonadmintab();
	  		  			newuser.ClickOnManageusers();
	  		  			Thread.sleep(1000);
	  			  		newuser.EnterUserinfo("test8@clariontechnologies.co.in");
	  			  		newuser.ClcikOnSearch();
	  			    	Thread.sleep(1000);
	  					newuser.Cliconusertoedit();
	  			  		Thread.sleep(1000);
	  					newuser.Clickonedittab();
	  			  		Thread.sleep(1000);
	  					
	  			  		//Verify  Presence of Role Permission Text
	  					if(SeleniumFunc.IsElementPresent(role.RolePermissions))
	  					{
	  						System.out.println("Success !! Role Permissions Text is present");
	  						Reporter.log("Success !!Role Permissions Text is present"); 
	  					}
	  					else
	  					{
	  						System.out.println("Failure !! Role Permissions Text is not present ");
	  						Reporter.log("Failure !! Role Permissions Text is not present "); 
	  						AssertFailedCount++;
	  					}
				    	
	  			System.out.println("Step 4 : Navigate  to permissions section > Check 'Can create Certifications' user option and Submit");
	  			Reporter.log("Step 4 : Navigate to permissions section > Check 'Can create Certifications' user option and Submit"); 
				 
	  					if(!role.CertificationCreater.isSelected())
	  					{
	  						role.ClickOnCertificationCreater();
	  					}
	  				  if(!role.CanManageAgencies.isSelected())
				        {
				 		role.ClickOnCanManageAgencies();
				        }
	  					newuser.EnterUserPasswardtext("clarion@123");
	  					newuser.EnterConfirmPasswardtext("clarion@123");
	  					Thread.sleep(1000);
	  					newuser.ClickOnSubmitBtnButton();
	  					Thread.sleep(1000);
		    	
	  					// Login  for Updated user   
	  					newuser.ClickOnusername();
	  					newuser.ClickonLogout();
	  					Thread.sleep(1000);
	  				 	
	  			System.out.println("Step 5 : Login with user 1.");
	  			Reporter.log("Step 5 : Login with user 1.");
	  	  			
  	  					login.EnterUsername(Constants.CM_User_Create_Cert);
  	  					login.EnterPassword(Constants.CM_Pwd_Create_Cert);
  	  					login.ClickOnLogInButton(); 
  	  					Thread.sleep(2000);
	  	  			
  	  					String actualtext12= gheader.Success_AlertText.getText().trim();
  	  					String expectedtext12= "Signed in!";
	  				
  	  					if(actualtext12.equals(expectedtext12))
  	  					{
  	  						System.out.println("Success !! Login  message is displayed i.e. " + actualtext12);
  	  						Reporter.log("Success !! Login  message is displayed i.e. " + actualtext12); 
  	  					}
  	  					else
  	  					{
  	  						System.out.println("Failure !! incorrect  message is displayed" + "\n" + "Expected : "  
  	  								+ "\n" + expectedtext12 + "\n" + 
		   							 "Actual : " + "\n" +  actualtext12);
  	  						Reporter.log("Failure !!  incorrect  message is displayed" + "\n" + "Expected : "  
  	  								+ "\n" + expectedtext12 + "\n" + 
  	  								"Actual : " + "\n" +  actualtext12);
		   		
  	  						AssertFailedCount++;
  	  					}
  	  					Thread.sleep(1000);	   
		    		
  	  			System.out.println("Step 6 : Verify User should be able to add new Certifications view 'New certification' link under Certifications listings");
  	  			Reporter.log("Step 6 : Verify User should be able to add new Certifications view 'New certification' link under Certifications listings"); 		
					
  	  					role.ClickOnNewCertificationLink();
						String firstname= "certi" + JH.GenerateRandomNumber();
						String emailprefix = "certi" + JH.GenerateRandomNumber();
						String emailaddress= emailprefix + "@mailinator.com";
						String streetaddress = "14086 Proton Rd";
						String juridication="PA";
						String collection="Hunter-Trapper Education (1986 - Present)";
					
						createnewcerti.CreateNewCertificate(firstname, "testing", "12/28/1989", emailaddress, "United States",  streetaddress,"Dallas", "75244", "Texas",juridication, collection, "Pass", "03/02/2015", "04/02/2015");
						createnewcerti.ClickOnContinueButton();
						Thread.sleep(10000);
						
						//Verifying alert message
						String actualtext= gheader.Success_AlertText.getText().trim();
						String expectedtext= "Certification was successfully created."; 
						
						if(actualtext.equals(expectedtext))
						{
							System.out.println("Success !! alert message is displayed i.e. " + actualtext);
							Reporter.log("Success !! alert message is displayed i.e. " + actualtext); 
						}
						else
						{
							System.out.println("Failure !! incorrect alert message is displayed" + "\n" + "Expected : "  
												+ "\n" + expectedtext + "\n" + 
												 "Actual : " + "\n" +  actualtext);
							Reporter.log("Failure !!  incorrect alert message is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext + "\n" + 
									 "Actual : " + "\n" +  actualtext);
							
							AssertFailedCount++;
						}
						
						//Logout  the user 
						Thread.sleep(1000);
						newuser.ClickOnusername();
						newuser.ClickonLogout();
						Thread.sleep(1000); 	
		    		
				System.out.println("Step 7 : Navigate  to permissions section > Uncheck 'Can Edit Certifications' user option and Submit");
	    		Reporter.log("Step 7 :  Navigate  to permissions section > Uncheck 'Can Edit Certifications' user option and Submit");
		    		
	    				//navigate to admin user and remove permission 
		    			login.EnterUsername(Constants.CM_Username);
		    			login.EnterPassword(Constants.CM_Password);
		    			login.ClickOnLogInButton(); 		
		    			Thread.sleep(2000);
		    			newuser.clickonadmintab();
		    			newuser.ClickOnManageusers();
		    			Thread.sleep(1000);
  			  			newuser.EnterUserinfo("test8@clariontechnologies.co.in");
  			  			newuser.ClcikOnSearch();
  			  			Thread.sleep(1000);
  			  			newuser.Cliconusertoedit();
  			  			Thread.sleep(1000);
  			  			newuser.Clickonedittab();
  			  			Thread.sleep(1000);
  			  		 
  			  		if(role.EditCertifications.isSelected())
  					{
  			  			role.ClickOnEditCertifications();
  						
  					}
  			  			
  			  			
  			  			newuser.EnterUserPasswardtext("clarion@123");
  			  			newuser.EnterConfirmPasswardtext("clarion@123");
  			  			Thread.sleep(1000);
  			  			newuser.ClickOnSubmitBtnButton();
  				        Thread.sleep(1000);
  				    //logout the admin user 
  				        Thread.sleep(5000);
  				        newuser.ClickOnusername();
  				        newuser.ClickonLogout();
  				        Thread.sleep(2000); 
		    		
  				System.out.println("Step 8 : Login with User1 and verify user is not able to create certificate");
	    		Reporter.log("Step 8 : Login with User1 and verify user is not able to create certificate"); 
		    		
	    				login.EnterUsername(Constants.CM_User_Create_Cert);
	    				login.EnterPassword(Constants.CM_Pwd_Create_Cert);
	    				login.ClickOnLogInButton();
	    				Thread.sleep(2000);
	    				editcer.SearchCM("Testing CertificationDetail");
			 		 	Thread.sleep(1000);
			 		 	editcer.ClickOnSearch();
			 		 	editcer.ClickOnStudentNametab();
			 		 	Thread.sleep(3000);
				  	  				
	  					if(!SeleniumFunc.IsElementPresent(role.EditInfoLink))
	  					{   
				
	  						System.out.println("Success !! User is not able to see edit button on Certification details screen");
	  						Reporter.log("Success !! User is not able to see edit button on Certification details screen"); 
				  	  			
	  					}
	  					else
	  					{
	  						System.out.println("Failure !! User is able to see edit button on Certification details screen");
	  						Reporter.log("Failure !! User is able to see edit button on Certification details screen "); 
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
  		* Role Permission  - Verify permission 'Can edit collections' should be working properly
		*/ 
		@Test
		public void  VerifyPermissionCanEditCertifications() throws InterruptedException
		{
				System.out.println("====" + "\n" +
						"Test 7 : Role Permission - Verify permission can edit collections' should be working properly" + "\n" +
						"====");
				Reporter.log("====" + "\n" + 
						"Test 7 :Role Permission - Verify permission 'Can edit collections' should be working properly"  + "\n" + 
						"====");	
							    
						int AssertFailedCount=0 ;
						SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
						LoginPage login = new LoginPage(driver);    	 		
						RolesPermissionsPage role=new RolesPermissionsPage(driver);
						GlobalHeader gheader = new GlobalHeader(driver);	
						CreateNewUserPage  newuser=new CreateNewUserPage(driver);
	
				System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
				Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
		
   	 					SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

   	 			System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
   	 			Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 
		  			
   	 					login.EnterUsername(Constants.CM_Username);
   	 					login.EnterPassword(Constants.CM_Password);
   	 					login.ClickOnLogInButton(); 			 
   	 		
   	 			System.out.println("Step 3 : Go to manage users > User > Edit account");
   	 			Reporter.log("Step 3 : Go to manage users > User > Edit account"); 
			      
   	 					newuser.clickonadmintab();
   	 					newuser.ClickOnManageusers();
   	 					Thread.sleep(1000);
   	 					newuser.EnterUserinfo("test9@clariontechnologies.co.in");
   	 					newuser.ClcikOnSearch();
   	 					Thread.sleep(1000);
   	 					newuser.Cliconusertoedit();
   	 					Thread.sleep(1000);
   	 					newuser.Clickonedittab();
   	 					Thread.sleep(1000);
					
   	 					//Verify  Presence of Role Permission Text
   	 					if(SeleniumFunc.IsElementPresent(role.RolePermissions))
   	 					{
   	 						System.out.println("Success !! Role Permissions Text is present");
   	 						Reporter.log("Success !!Role Permissions Text is present"); 
   	 					}
   	 					else
   	 					{
   	 						System.out.println("Failure !! Role Permissions Text is not present ");
   	 						Reporter.log("Failure !! Role Permissions Text is not present "); 
   	 						AssertFailedCount++;
   	 					}
		    	
   	 			System.out.println("Step 4 : Navigate  to permissions section > Check 'Can Edit Collections' user option and Submit");
   	 			Reporter.log("Step 4 : Navigate to permissions section > Check 'Can Edit Collections' user option and Submit"); 
		 
   	 			       role.clickonAllpremission();
   	 			       Thread.sleep(2000);
   	 					if(!role.EditCollections.isSelected())
   	 					{
   	 						role.ClickOnEditCollections();
   	 					}
   	 					newuser.EnterUserPasswardtext("clarion@123");
   	 					newuser.EnterConfirmPasswardtext("clarion@123");
   	 					Thread.sleep(1000);
   	 					newuser.ClickOnSubmitBtnButton();
   	 					Thread.sleep(1000);
   	 					// Login  for Updated user   
   	 					newuser.ClickOnusername();
   	 					newuser.ClickonLogout();
   	 					Thread.sleep(1000);
				 	
   	 			System.out.println("Step 5 : Login with user 1.");
   	 			Reporter.log("Step 5 : Login with user 1.");
	  			
   	 					login.EnterUsername(Constants.CM_User_Edit_Cert);
   	 					login.EnterPassword(Constants.CM_Pwd_Edit_Cert);
   	 					login.ClickOnLogInButton(); 
   	 					Thread.sleep(2000);
   	 					String actualtext12= gheader.Success_AlertText.getText().trim();
   	 					String expectedtext12= "Signed in!";
				
   	 					if(actualtext12.equals(expectedtext12))
   	 					{
   	 						System.out.println("Success !! Login  message is displayed i.e. " + actualtext12);
   	 						Reporter.log("Success !! Login  message is displayed i.e. " + actualtext12); 
   	 					}
   	 					else
   	 					{
   	 						System.out.println("Failure !! incorrect  message is displayed" + "\n" + "Expected : "  
   	 								+ "\n" + expectedtext12 + "\n" + 
   	 								"Actual : " + "\n" +  actualtext12);
   	 						Reporter.log("Failure !!  incorrect  message is displayed" + "\n" + "Expected : "  
   	 								+ "\n" + expectedtext12 + "\n" + 
   	 								"Actual : " + "\n" +  actualtext12);
   	 						AssertFailedCount++;
   	 					}
   	 					Thread.sleep(1000);	   
   	 					
   	 			System.out.println("Step 6 : verify user should be able to edit Collections and can view MANAGE COLLECTIONS under Admin");
   	 			Reporter.log("Step 6 : Verify user should be able to edit Collections and can view MANAGE COLLECTIONS under Admin");
					
   	 					newuser.clickonadmintab();
   	 					if(SeleniumFunc.IsElementPresent(role.ManageCollection))
   	 					{   
   	 						System.out.println("Success !! MANAGE COLLECTIONS  is present under Admin");
   	 						Reporter.log("Success !! MANAGE COLLECTIONS  is present under Admin"); 
	  				    }
   	 					else
   	 					{
   	 						System.out.println("Failure !! MANAGE COLLECTIONS  is not present under Admin");
   	 						Reporter.log("Failure !! MANAGE COLLECTIONS  is not present under Admin"); 
   	 						AssertFailedCount++;
   	 					}
	
   	 					role.ClickOnManageCollection();
   	 					Thread.sleep(1000);
   	 					role.ClickOnFirstCollection();
   	 					Thread.sleep(1000);
		    	
   	 					if(SeleniumFunc.IsElementPresent(role.EditcollectionHeader))
   	 					{   
   	 						System.out.println("Success !! User is able to edit Collections");
   	 						Reporter.log("Success !! User is able to edit Collections"); 
   	 					}
   	 					else
   	 					{
   	 						System.out.println("Failure !! User is not able to edit Collections");
   	 						Reporter.log("Failure !! User is not able to edit Collections"); 
   	 						AssertFailedCount++;
   	 					}
		    	// Logout user   
   	 					Thread.sleep(1000);
   	 					newuser.ClickOnusername();
   	 					newuser.ClickonLogout();
   	 					Thread.sleep(1000); 
	   	 		
   	 			System.out.println("Step 7 : Login with admin and Uncheck 'Can Edit collections' option");
   	 			Reporter.log("Step 7 : Login with admin and Uncheck 'Can Edit collections' option");
		    
	   	 				login.EnterUsername(Constants.CM_Username);
	   	 				login.EnterPassword(Constants.CM_Password);
	   	 				login.ClickOnLogInButton();
	   	 				Thread.sleep(2000);
	   	 				newuser.clickonadmintab();
	   	 				newuser.ClickOnManageusers();
	   	 				Thread.sleep(1000);
	   	 				newuser.EnterUserinfo("test9@clariontechnologies.co.in");
	   	 				newuser.ClcikOnSearch();
	   	 				Thread.sleep(1000);
	   	 				newuser.Cliconusertoedit();
	   	 				Thread.sleep(1000);
	   	 				newuser.Clickonedittab();
	   	 				Thread.sleep(1000);
   	 			
	   	 				role.ClickOnEditCollections();	 
	   	 				newuser.EnterUserPasswardtext("clarion@123");
	   	 				newuser.EnterConfirmPasswardtext("clarion@123");
	   	 				Thread.sleep(1000);
	   	 				newuser.ClickOnSubmitBtnButton();
	   	 				Thread.sleep(1000);
   	 		
   	 		// Logout  for Updated user   
	   	 				Thread.sleep(1000);
	   	 				newuser.ClickOnusername();
	   	 				newuser.ClickonLogout();
	   	 				Thread.sleep(1000); 
   	 			
	   	 		System.out.println("Step 8 : Login with User1 and Verify User is not able to edit collections ");
	   	 		Reporter.log("Step 8 : Login with User1 and Verify User is not able to edit collections ");				    
	   	 		
	   	 				login.EnterUsername(Constants.CM_User_Edit_Cert);
	   	 				login.EnterPassword(Constants.CM_Pwd_Edit_Cert);
	   	 				login.ClickOnLogInButton(); 
	   	 		
	   	 				Thread.sleep(1000); 	
	   	 				//newuser.clickonadmintab();
	   	 				if(!SeleniumFunc.IsElementPresent(role.ManageCollection))
	   	 				{   
	   	 					System.out.println("Success !!User is not able to edit collections");
	   	 					Reporter.log("Success !! User is not able to edit collections"); 
	   	 				}
	   	 				else
	   	 				{
	   	 					System.out.println("Failure !! User is able to edit Collections");
	   	 					Reporter.log("Failure !! User is  able to edit Collections"); 
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
    * Role Permission  - Verify permissions 'Can add notes
    */ 
	@Test
	public void  VerifyPermissionCanAddNotes() throws InterruptedException
	 {
		 	System.out.println("====" + "\n" +	
		 				"Test 8 : Role Permission- Verify permissions 'Can add notes" + "\n" +
		 				"====");
		 	Reporter.log("====" + "\n" + 
		 				"Test 8 : Role Permissions-Verify permissions 'Can add notes"  + "\n" +
		 				"====");	
						    
		 			int AssertFailedCount=0 ;
		 			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		 			LoginPage login = new LoginPage(driver);    	 		
		 			RolesPermissionsPage role=new RolesPermissionsPage(driver);
		 			GlobalHeader gheader = new GlobalHeader(driver);	
		 			CreateNewUserPage  newuser=new CreateNewUserPage(driver);
		 			EditCertificationPage editcer = new EditCertificationPage(driver);
			
		 	System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		 	Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 

		 			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

		 	System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
		 	Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 
		
		 			login.EnterUsername(Constants.CM_Username);
		 			login.EnterPassword(Constants.CM_Password);
		 			login.ClickOnLogInButton(); 			 
	
		 	System.out.println("Step 3 : Go to manage users > User > Edit account");
		 	Reporter.log("Step 3 : Go to manage users > User > Edit account"); 
   
		 			newuser.clickonadmintab();
		 			newuser.ClickOnManageusers();
		 			Thread.sleep(1000);
		 			newuser.EnterUserinfo("test7@clariontechnologies.co.in");
		 			newuser.ClcikOnSearch();
		 			Thread.sleep(1000);
		 			newuser.Cliconusertoedit();
		 			Thread.sleep(1000);
		 			newuser.Clickonedittab();
		 			Thread.sleep(1000);
		 	//Verify  Presence of Role Permission Text
		 			if(SeleniumFunc.IsElementPresent(role.RolePermissions))
		 			{
		 				System.out.println("Success !! Role Permissions Text is present");
		 				Reporter.log("Success !!Role Permissions Text is present"); 
		 			}
		 			else
		 			{
		 				System.out.println("Failure !! Role Permissions Text is not present ");
		 				Reporter.log("Failure !! Role Permissions Text is not present "); 
		 				AssertFailedCount++;
		 			}
	
		 	System.out.println("Step 4 : Navigate  to permissions section > Check 'Can add notes' user option and Submit");
		 	Reporter.log("Step 4 : Navigate to permissions section > Check 'Can add notes' user option and Submit"); 

		 			if(!role.AddNotes.isSelected())
		 			{
		 				role.ClickOnAddNotes();
		 			}
		 			newuser.EnterUserPasswardtext("clarion@123");
		 			newuser.EnterConfirmPasswardtext("clarion@123");
		 			Thread.sleep(1000);
		 			newuser.ClickOnSubmitBtnButton();
		 			Thread.sleep(1000);
		 	// Login  for Updated user   
		 			newuser.ClickOnusername();
		 			newuser.ClickonLogout();
		 			Thread.sleep(1000);
	 	
		 	System.out.println("Step 5 : Login with user 1 and verify User is able to add new notes or not.");
		 	Reporter.log("Step 5 : Login with user 1 and verify User is able to add new notes or not.");
	
		 			login.EnterUsername(Constants.CM_User_Add_Notes);
		 			login.EnterPassword(Constants.CM_Pwd_Add_Notes);
		 			login.ClickOnLogInButton(); 
		 			Thread.sleep(2000);
		 			String actualtext12= gheader.Success_AlertText.getText().trim();
		 			String expectedtext12= "Signed in!";
	
		 			if(actualtext12.equals(expectedtext12))
		 			{
		 				System.out.println("Success !! Login  message is displayed i.e. " + actualtext12);
		 				Reporter.log("Success !! Login  message is displayed i.e. " + actualtext12); 
		 			}
		 			else
		 			{
		 				System.out.println("Failure !! incorrect  message is displayed" + "\n" + "Expected : "  
		 						+ "\n" + expectedtext12 + "\n" + 
		 						"Actual : " + "\n" +  actualtext12);
		 				Reporter.log("Failure !!  incorrect  message is displayed" + "\n" + "Expected : "  
		 						+ "\n" + expectedtext12 + "\n" + 
		 						"Actual : " + "\n" +  actualtext12);
		 				AssertFailedCount++;
		 			}
		 			Thread.sleep(1000);	   
		 			editcer.SearchCM("Testing CertificationDetail");
		 			Thread.sleep(1000);
					editcer.ClickOnSearch();
					editcer.ClickOnStudentNametab();
					Thread.sleep(1000);
					role.EnterAddTheNote("ClarionQa");
					role.ClickOnAddNoteBtn();
					String message=SeleniumFunc.GetElementText("css", ".alert.alert-success");
				
					if(message.equals("Note was successfully created."))
					{   
						System.out.println("Success !! Note was successfully created.");
						Reporter.log("Success !! Note was successfully created."); 
					}
					else
					{
						System.out.println("Failure !! Note is not created. ");
						Reporter.log("Failure !!Note is not created."); 
						AssertFailedCount++;
					}
					Thread.sleep(1000);
					newuser.ClickOnusername();
					newuser.ClickonLogout();
					Thread.sleep(1000); 
			
			System.out.println("Step 6 : Login with admin and Uncheck 'Can Edit collections' option");
			Reporter.log("Step 6 : Login with admin and Uncheck 'Can Edit collections' option");
	    
					login.EnterUsername(Constants.CM_Username);
   	 				login.EnterPassword(Constants.CM_Password);
   	 				login.ClickOnLogInButton();
   	 				Thread.sleep(2000);
   	 				newuser.clickonadmintab();
   	 				newuser.ClickOnManageusers();
   	 				Thread.sleep(1000);
   	 				newuser.EnterUserinfo("test7@clariontechnologies.co.in");
   	 				newuser.ClcikOnSearch();
   	 				Thread.sleep(1000);
   	 				newuser.Cliconusertoedit();
   	 				Thread.sleep(1000);
   	 				newuser.Clickonedittab();
   	 				Thread.sleep(1000);
   	 				role.ClickOnAddNotes();	 
   	 				newuser.EnterUserPasswardtext("clarion@123");
   	 				newuser.EnterConfirmPasswardtext("clarion@123");
   	 				Thread.sleep(1000);
   	 				newuser.ClickOnSubmitBtnButton();
   	 				Thread.sleep(1000);
   	 		// Logout  for Updated user   
   	 				Thread.sleep(1000);
   	 				newuser.ClickOnusername();
   	 				newuser.ClickonLogout();
   	 				Thread.sleep(1000); 
  	 		
   	 		System.out.println("Step 7 :Verify User should be able see 'Add notes' under Certification details section  ");
   	 		Reporter.log("Step 7 : Verify User should be able see 'Add notes' under Certification details section");
			
  	  	   			login.EnterUsername(Constants.CM_User_Add_Notes);
  	  	   			login.EnterPassword(Constants.CM_Pwd_Add_Notes);
  	  	   			login.ClickOnLogInButton(); 
  	  	   			Thread.sleep(1000);	   
  	  	   			editcer.SearchCM("Testing CertificationDetail");
  	  	   			Thread.sleep(1000);
  	  	   			editcer.ClickOnSearch();
  	  	   			editcer.ClickOnStudentNametab();
  	  	   			Thread.sleep(1000);
  	  	   			if(SeleniumFunc.IsElementPresent(role.NoteText))
  	  	   			{  
  	  	   				System.out.println("Success !! Note Text Link is present");
  	  	   				Reporter.log("Success !! Note Text Link is present"); 
  	  	   			}
  	  	   			else
  	  	   			{
  	  	   				System.out.println("Failure !! Note Text is NOT present ");
  	  	   				Reporter.log("Failure !! Note Text is NOT present "); 
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
	    * Role Permission  - Verify permission 'Can order permanent certification cards'
		*/ 
		@Test
		public void  VerifyPermissionCanOrderPermanentCertification() throws InterruptedException
		{
				System.out.println("====" + "\n" +	
							"Test 9: Role Permission - Verify permission 'Can order permanent certification cards'" + "\n" +
							"====");
				Reporter.log("====" + "\n" + 
							"Test 9 : Role Permission -  Verify permission 'Can order permanent certification cards'"  + "\n" + 
							"====");	
							    
						int AssertFailedCount=0 ;
						SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
						LoginPage login = new LoginPage(driver);    	 		
						RolesPermissionsPage role=new RolesPermissionsPage(driver);
						GlobalHeader gheader = new GlobalHeader(driver);	
						CreateNewUserPage  newuser=new CreateNewUserPage(driver);
						EditCertificationPage editcer = new EditCertificationPage(driver);
						DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);    	
	
				System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
				Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 

						SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

				System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
				Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 

						login.EnterUsername(Constants.CM_Username);
						login.EnterPassword(Constants.CM_Password);
						login.ClickOnLogInButton(); 			 

				System.out.println("Step 3 : Go to manage users > User > Edit account");
				Reporter.log("Step 3 : Go to manage users > User > Edit account"); 

						newuser.clickonadmintab();
						newuser.ClickOnManageusers();
						Thread.sleep(1000);
						newuser.EnterUserinfo("test10@clariontechnologies.co.in");
						newuser.ClcikOnSearch();
						Thread.sleep(1000);
						newuser.Cliconusertoedit();
						Thread.sleep(1000);
						newuser.Clickonedittab();
						Thread.sleep(1000);
				//Verify  Presence of Role Permission Text
						if(SeleniumFunc.IsElementPresent(role.RolePermissions))
						{
							System.out.println("Success !! Role Permissions Text is present");
							Reporter.log("Success !!Role Permissions Text is present"); 
						}
						else
						{
							System.out.println("Failure !! Role Permissions Text is not present ");
							Reporter.log("Failure !! Role Permissions Text is not present "); 
							AssertFailedCount++;
						}

				System.out.println("Step 4 : Navigate  to permissions section > Check 'Can order permanent cetification cards' user option and Submit");
				Reporter.log("Step 4 : Navigate to permissions section > Check 'Can order permanent cetification cards' user option and Submit"); 

						if(!role.CanOrderpermanentCertificationCards.isSelected())
						{
							role.ClickOnCanOrderpermanentCertificationCards();
						}
						if(!role.ByPassPament.isSelected())
						{
							role.ClickOnByPassPament();
						}
						newuser.EnterUserPasswardtext("clarion@123");
						newuser.EnterConfirmPasswardtext("clarion@123");
						Thread.sleep(1000);
						newuser.ClickOnSubmitBtnButton();
						Thread.sleep(1000);
				// Login  for Updated user   
						newuser.ClickOnusername();
						newuser.ClickonLogout();
						Thread.sleep(1000);
	
				System.out.println("Step 5 : Login with user 1,Click  on Order Replacement Certificate and verify User is able to successfully place an order.");
				Reporter.log("Step 5 : Login with user 1,Click  on Order Replacement Certificate and verify User is able to successfully place an order.");

						login.EnterUsername(Constants.CM_User_Order_Cert);
						login.EnterPassword(Constants.CM_Pwd_Order_Cert);
						login.ClickOnLogInButton(); 
						Thread.sleep(2000);
						String actualtext12= gheader.Success_AlertText.getText().trim();
						String expectedtext12= "Signed in!";
						if(actualtext12.equals(expectedtext12))
						{
							System.out.println("Success !! Login  message is displayed i.e. " + actualtext12);
							Reporter.log("Success !! Login  message is displayed i.e. " + actualtext12); 
						}
						else
						{
							System.out.println("Failure !! incorrect  message is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext12 + "\n" + 
									"Actual : " + "\n" +  actualtext12);
							Reporter.log("Failure !!  incorrect  message is displayed" + "\n" + "Expected : "  
									+ "\n" + expectedtext12 + "\n" + 
									"Actual : " + "\n" +  actualtext12);
							AssertFailedCount++;
						}
						Thread.sleep(1000);	
						
						//SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "state",39);					
						//Thread.sleep(1000);
						//SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "certification_type_id", 2);		
						editcer.SearchCM("Jackson M Loeb I");
						Thread.sleep(1000);
						editcer.ClickOnSearch();
						Thread.sleep(1000);
						editcer.ClickOnStudentNametab();		
						Thread.sleep(1000);
				
						if(SeleniumFunc.IsElementPresent(role.OrderRplacementCertificate))
						{   
							System.out.println("Success !! Order Rplacement Certificate button is present");
							Reporter.log("Success !!Order Rplacement Certificate button is present"); 
						}
						else
						{
							System.out.println("Failure !! Order Rplacement Certificate button is not present ");
							Reporter.log("Failure !! Order Rplacement Certificate button is not present "); 
							AssertFailedCount++;
						}	
				//Click on OrderReplacementCertificate 
						DetailsPage.ClickOnOrderReplacementCerti();
						Thread.sleep(1000);	  
			    
				System.out.println("Step 6 : Perform a payment bypass, choose any reason,enter comments and verify user is able to order certificate.");
				Reporter.log("Step 6 : Perform a payment bypass, choose any reason,enter comments and verify user is able to order certificate.");
		   		
				
		   				DetailsPage.ClickOnBypassPayment();
		   				Thread.sleep(1000);
		   				SeleniumFunc.SelectValueFromDropdownList("css", "#historical_action_type_id", "Payment by Check");
		   				SeleniumFunc.SelectValueFromDropdownListUsingIndex("css", "#quantity",3); 
		   				DetailsPage.EnterReasonSelectText("Pay By Cash");
		   				DetailsPage.ClickOnContinueButton();
		   				Thread.sleep(2000);
		   				if(SeleniumFunc.IsElementPresent(DetailsPage.ByPassPaymenttext))
		   				{
		   					System.out.println("Success !! Payment has been bypassed for this certification order Text  is present and user is able to order certificate");
		   					Reporter.log("Success !!  Payment has been bypassed for this certification order Text is present and user is able to order certificate"); 
		   				}
		   				else
		   				{
		   					System.out.println("Failure !!  Payment has been bypassed for this certification order Text is not present and user is not able to order certificate ");
		   					Reporter.log("Failure !!  Payment has been bypassed for this certification order Text is not present and user is not able to order certificate"); 
		   					AssertFailedCount++;
		   				}
		   		// Logout  for Updated user   
		   				Thread.sleep(1000);
		   				newuser.ClickOnusername();
		   				newuser.ClickonLogout();
		   				Thread.sleep(1000); 
				
		   		System.out.println("Step 7 : Login with admin and Uncheck 'Can order permanent cetification cards' option");
				Reporter.log("Step 7 : Login with admin and Uncheck 'Can order permanent cetification cards' option");
		    
						login.EnterUsername(Constants.CM_Username);
	   	 				login.EnterPassword(Constants.CM_Password);
	   	 				login.ClickOnLogInButton();
	   	 				Thread.sleep(2000);
	   	 				newuser.clickonadmintab();
	   	 				newuser.ClickOnManageusers();
	   	 				Thread.sleep(1000);
	   	 				newuser.EnterUserinfo("test10@clariontechnologies.co.in");
	   	 				newuser.ClcikOnSearch();
	   	 				Thread.sleep(1000);
	   	 				newuser.Cliconusertoedit();
	   	 				Thread.sleep(1000);
	   	 				newuser.Clickonedittab();
	   	 				Thread.sleep(1000);
	   	 				role.ClickOnCanOrderpermanentCertificationCards();	 
	   	 				newuser.EnterUserPasswardtext("clarion@123");
	   	 				newuser.EnterConfirmPasswardtext("clarion@123");
	   	 				Thread.sleep(1000);
	   	 				newuser.ClickOnSubmitBtnButton();
	   	 				Thread.sleep(1000);
	   	 		// Logout  for Updated user   
	   	 				newuser.ClickOnusername();
	   	 				newuser.ClickonLogout();
	   	 				Thread.sleep(1000); 
	  	 		
	   	 		System.out.println("Step 8 : Login with user1 and Verify Order Rplacement Certificate button is no longer be visible on cert. details screen");
	   	 		Reporter.log("Step 8 : Login with user1 and Verify Order Rplacement Certificate button is no longer be visible on cert. details screen");
				
	   	 				login.EnterUsername(Constants.CM_User_Order_Cert);
	  	  	   			login.EnterPassword(Constants.CM_Pwd_Order_Cert);
	  	  	   			login.ClickOnLogInButton(); 
	  	  	   			Thread.sleep(1000);			
	  	  	   			//SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "state",39);					
	  	  	   			//Thread.sleep(1000);
	  	  	   			//SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "certification_type_id", 2);		
	  	  	   			editcer.SearchCM("Jackson M Loeb I");
	  	  	   			Thread.sleep(1000);
	  	  	   			editcer.ClickOnSearch();
	  	  	   			Thread.sleep(1000);
	  	  	   			editcer.ClickOnStudentNametab();		
	  	  	   			Thread.sleep(1000);
					
	  	  	   			if(!SeleniumFunc.IsElementPresent(role.OrderRplacementCertificate))
	  	  	   			{   
	  	  	   				System.out.println("Success !! Order Rplacement Certificate button is no longer be visible on cert. details screen");
	  	  	   				Reporter.log("Success !! Order Rplacement Certificate button is no longer be visible on cert. details screen"); 
	  	  	   			}
	  	  	   			else
	  	  	   			{
	  	  	   				System.out.println("Failure !! Order Rplacement Certificate button is  present ");
	  	  	   				Reporter.log("Failure !! Order Rplacement Certificate button is  present "); 
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
    * Role Permission  - Verify permission 'Can send course completion documents
    */ 
	@Test
	public void  VerifyPermissionCanSendCourseCompletion() throws InterruptedException
	{
		System.out.println("====" + "\n" +	
					"Test 10: Role Permission  - Verify permission 'Can send course completion documents" + "\n" +
					"====");
		Reporter.log("====" + "\n" + 
					"Test 10 : Role Permission  - Verify permission 'Can send course completion documents"  + "\n" +
					"====");	
						    
  	 	   int AssertFailedCount=0 ;
 			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
 			LoginPage login = new LoginPage(driver);    	 		
 			RolesPermissionsPage role=new RolesPermissionsPage(driver);
 			GlobalHeader gheader = new GlobalHeader(driver);	
 			CreateNewUserPage  newuser=new CreateNewUserPage(driver);
 			EditCertificationPage editcer = new EditCertificationPage(driver);
	
 	System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
 	Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 

 			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

 	System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
 	Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 

 			login.EnterUsername(Constants.CM_Username);
 			login.EnterPassword(Constants.CM_Password);
 			login.ClickOnLogInButton(); 			 

 	System.out.println("Step 3 : Go to manage users > User > Edit account");
 	Reporter.log("Step 3 : Go to manage users > User > Edit account"); 

 			newuser.clickonadmintab();
 			newuser.ClickOnManageusers();
 			Thread.sleep(1000);
 			newuser.EnterUserinfo("test11@clariontechnologies.co.in");
 			newuser.ClcikOnSearch();
 			Thread.sleep(2000);
 			newuser.Cliconusertoedit();
 			Thread.sleep(1000);
 			newuser.Clickonedittab();
 			Thread.sleep(1000);
 	//Verify  Presence of Role Permission Text
 			if(SeleniumFunc.IsElementPresent(role.RolePermissions))
 			{
 				System.out.println("Success !! Role Permissions Text is present");
 				Reporter.log("Success !!Role Permissions Text is present"); 
 			}
 			else
 			{
 				System.out.println("Failure !! Role Permissions Text is not present ");
 				Reporter.log("Failure !! Role Permissions Text is not present "); 
 				AssertFailedCount++;
 			}

 	System.out.println("Step 4 : Navigate  to permissions section > Check 'Can send course completion documents' user option and Submit");
 	Reporter.log("Step 4 : Navigate to permissions section > Check 'Can send course completion documents' user option and Submit"); 
 			
 	
 			if(!role.SendCourceCompletionDoc.isSelected())
 			{
 				role.ClickOnSendCourceCompletionDoc();
 			}
 			newuser.EnterUserPasswardtext("clarion@123");
 			newuser.EnterConfirmPasswardtext("clarion@123");
 			Thread.sleep(1000);
 			newuser.ClickOnSubmitBtnButton();
 			Thread.sleep(1000);
 	// Login  for Updated user   
 			newuser.ClickOnusername();
 			newuser.ClickonLogout();
 			Thread.sleep(1000);
	
 	System.out.println("Step 5 : Login with user 1 and verify Button 'Resend course details' should be displayed and user should be able send documents");
 	Reporter.log("Step 5 : Login with user 1 and verify Button 'Resend course details' should be displayed and user should be able send documents");

 			login.EnterUsername(Constants.CM_User_Send_CourseComp);
 			login.EnterPassword(Constants.CM_Pwd_Send_CourseComp);
 			login.ClickOnLogInButton(); 
 			Thread.sleep(2000);
 			String actualtext12= gheader.Success_AlertText.getText().trim();
 			String expectedtext12= "Signed in!";

 			if(actualtext12.equals(expectedtext12))
 			{
 				System.out.println("Success !! Login  message is displayed i.e. " + actualtext12);
 				Reporter.log("Success !! Login  message is displayed i.e. " + actualtext12); 
 			}
 			else
 			{
 				System.out.println("Failure !! incorrect  message is displayed" + "\n" + "Expected : "  
 						+ "\n" + expectedtext12 + "\n" + 
 						"Actual : " + "\n" +  actualtext12);
 				Reporter.log("Failure !!  incorrect  message is displayed" + "\n" + "Expected : "  
 						+ "\n" + expectedtext12 + "\n" + 
 						"Actual : " + "\n" +  actualtext12);
 				AssertFailedCount++;
 			}
 			Thread.sleep(1000);	   
 			editcer.SearchCM("Lisa M Carfi");
 			Thread.sleep(1000);
			editcer.ClickOnSearch();
			editcer.ClickOnStudentNametab();
			Thread.sleep(1000);
			
			if(SeleniumFunc.IsElementPresent(role.ResendCourseCompletionLink))
			{   
				System.out.println("Success !! 'Resend course completion' button is present.");
				Reporter.log("Success !! 'Resend course completion' button is present."); 
			}
			else
			{
				System.out.println("Failure !! 'Resend course completion' button is not present. ");
				Reporter.log("Failure !! 'Resend course completion' button is not present."); 
				AssertFailedCount++;
			}
			Thread.sleep(2000);
			role.ClickOnResendCourseCompletionLink();
			Thread.sleep(2000);
			String msg=SeleniumFunc.GetElementText("css", ".alert.alert-info");
			if(msg.contains("Course completion sent to"))
			{   
				System.out.println("Success !! User is able send documents.");
				Reporter.log("Success !! User is able send documents."); 
			}
			else
			{
				System.out.println("Failure !! User is not able send documents.");
				Reporter.log("Failure !! User is not able send documents"); 
				AssertFailedCount++;
			}
			Thread.sleep(1000);
			newuser.ClickOnusername();
			newuser.ClickonLogout();
			Thread.sleep(1000); 
	
	System.out.println("Step 6 : Login with admin and Uncheck 'Can send course completion documents' option");
	Reporter.log("Step 6 : Login with admin and Uncheck 'Can send course completion documents' option");

			login.EnterUsername(Constants.CM_Username);
				login.EnterPassword(Constants.CM_Password);
				login.ClickOnLogInButton();
				Thread.sleep(2000);
				newuser.clickonadmintab();
				newuser.ClickOnManageusers();
				Thread.sleep(1000);
				newuser.EnterUserinfo("test11@clariontechnologies.co.in");
				newuser.ClcikOnSearch();
				Thread.sleep(1000);
				newuser.Cliconusertoedit();
				Thread.sleep(1000);
				newuser.Clickonedittab();
				Thread.sleep(1000);
				role.ClickOnSendCourceCompletionDoc();
				newuser.EnterUserPasswardtext("clarion@123");
				newuser.EnterConfirmPasswardtext("clarion@123");
				Thread.sleep(1000);
				newuser.ClickOnSubmitBtnButton();
				Thread.sleep(1000);
		// Logout  for Updated user   
				Thread.sleep(1000);
				newuser.ClickOnusername();
				newuser.ClickonLogout();
				Thread.sleep(1000); 
		
		System.out.println("Step 7 : Verify User should not be able to send course completion documents");
		Reporter.log("Step 7 : Verify User should not be able to send course completion documents");
	
	   			login.EnterUsername(Constants.CM_User_Send_CourseComp);
	   			login.EnterPassword(Constants.CM_Pwd_Send_CourseComp);
	   			login.ClickOnLogInButton(); 
	   			Thread.sleep(1000);	   
	   			editcer.SearchCM("Lisa M Carfi");
	   			Thread.sleep(1000);
	   			editcer.ClickOnSearch();
	   			editcer.ClickOnStudentNametab();
	   			Thread.sleep(1000);
	   			if(!SeleniumFunc.IsElementPresent(role.ResendCourseCompletionLink))
	   			{  
	   				System.out.println("Success !! User is not able to send course completion documents ");
	   				Reporter.log("Success !! User is not able to send course completion documents"); 
	   			}
	   			else
	   			{
	   				System.out.println("Failure !! Resend course completion button is present");
	   				Reporter.log("Failure !! Resend course completion button is present"); 
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
	* Role Permission  - Verify permission ''Can view Reports'
    */
	@Test
	public void  VerifyPermissionCanviewReports() throws InterruptedException
	{
		System.out.println("====" + "\n" +	
				"Test 11: Role Permission - Verify permission ''Can view Reports'" + "\n" +
				"====");
		Reporter.log("====" + "\n" + 
				 "Test 11 : Role Permission - Verify permission ''Can view Reports'"  + "\n" + 
				"====");	
						    
           int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);    	 		
			RolesPermissionsPage role=new RolesPermissionsPage(driver);
			GlobalHeader gheader = new GlobalHeader(driver);	
			CreateNewUserPage  newuser=new CreateNewUserPage(driver);
			
	
	System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
	Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 

			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

	System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
	Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 

			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton(); 			 

	System.out.println("Step 3 : Go to manage users > User > Edit account");
	Reporter.log("Step 3 : Go to manage users > User > Edit account"); 

			newuser.clickonadmintab();
			newuser.ClickOnManageusers();
			Thread.sleep(1000);
			newuser.EnterUserinfo("test12@clariontechnologies.co.in");
			newuser.ClcikOnSearch();
			Thread.sleep(1000);
			newuser.Cliconusertoedit();
			Thread.sleep(1000);
			newuser.Clickonedittab();
			Thread.sleep(1000);
	//Verify  Presence of Role Permission Text
			if(SeleniumFunc.IsElementPresent(role.RolePermissions))
			{
				System.out.println("Success !! Role Permissions Text is present");
				Reporter.log("Success !!Role Permissions Text is present"); 
			}
			else
			{
				System.out.println("Failure !! Role Permissions Text is not present ");
				Reporter.log("Failure !! Role Permissions Text is not present "); 
				AssertFailedCount++;
			}

	System.out.println("Step 4 : Navigate  to permissions section > Check 'Can View reports' user option and Submit");
	Reporter.log("Step 4 : Navigate to permissions section > Check 'Can View reports' user option and Submit"); 

			if(!role.ViewReports.isSelected())
			{
				role.ClickOnViewReports();
			}
			newuser.EnterUserPasswardtext("clarion@123");
			newuser.EnterConfirmPasswardtext("clarion@123");
			Thread.sleep(1000);
			newuser.ClickOnSubmitBtnButton();
			Thread.sleep(1000);
	// Login  for Updated user   
			newuser.ClickOnusername();
			newuser.ClickonLogout();
			Thread.sleep(1000);
	
	System.out.println("Step 5 : Login with user 1 and verify User should be able to View reports");
	Reporter.log("Step 5 : Login with user 1 and verify User should be able to View reports");

			login.EnterUsername(Constants.CM_User_CanView_Reports);
			login.EnterPassword(Constants.CM_Pwd_CanView_Reports);
			login.ClickOnLogInButton(); 
			Thread.sleep(2000);
			String actualtext12= gheader.Success_AlertText.getText().trim();
			String expectedtext12= "Signed in!";

			if(actualtext12.equals(expectedtext12))
			{
				System.out.println("Success !! Login  message is displayed i.e. " + actualtext12);
				Reporter.log("Success !! Login  message is displayed i.e. " + actualtext12); 
			}
			else
			{
				System.out.println("Failure !! incorrect  message is displayed" + "\n" + "Expected : "  
						+ "\n" + expectedtext12 + "\n" + 
						"Actual : " + "\n" +  actualtext12);
				Reporter.log("Failure !!  incorrect  message is displayed" + "\n" + "Expected : "  
						+ "\n" + expectedtext12 + "\n" + 
						"Actual : " + "\n" +  actualtext12);
				AssertFailedCount++;
			}
			Thread.sleep(1000);	   
	   		role.ClickOnReportPara();
	   		Thread.sleep(1000);
	   		if(SeleniumFunc.IsElementPresent(role.ToggleReportsMenu))
			{   

	   			System.out.println("Success !! User is able to View reports");
				Reporter.log("Success !! User is able to View reports"); 
			   
			}
			else
			{
				System.out.println("Failure !! User is not able to View reports");
				Reporter.log("Failure !! User is not able to View reports"); 
				 AssertFailedCount++;
			}
	   		//logout
	   		Thread.sleep(1000);
  	 		newuser.ClickOnusername();
  	 		newuser.ClickonLogout();
  	 		Thread.sleep(1000);
  	 		
  	 		System.out.println("Step 6 : Login with admin and Uncheck 'Can View reports' option");
  	 		Reporter.log("Step 6 : Login with admin and Uncheck 'Can View reports' option");

  	 				login.EnterUsername(Constants.CM_Username);
  	 					login.EnterPassword(Constants.CM_Password);
  	 					login.ClickOnLogInButton();
  	 					Thread.sleep(2000);
  	 					newuser.clickonadmintab();
  	 					newuser.ClickOnManageusers();
  	 					Thread.sleep(1000);
  	 					newuser.EnterUserinfo("test12@clariontechnologies.co.in");
  	 					newuser.ClcikOnSearch();
  	 					Thread.sleep(1000);
  	 					newuser.Cliconusertoedit();
  	 					Thread.sleep(1000);
  	 					newuser.Clickonedittab();
  	 					Thread.sleep(1000);
  	 					role.ClickOnViewReports();
  	 					newuser.EnterUserPasswardtext("clarion@123");
  	 					newuser.EnterConfirmPasswardtext("clarion@123");
  	 					Thread.sleep(1000);
  	 					newuser.ClickOnSubmitBtnButton();
  	 					Thread.sleep(1000);
  	 			// Logout  for Updated user   
  	 					Thread.sleep(1000);
  	 					newuser.ClickOnusername();
  	 					newuser.ClickonLogout();
  	 					Thread.sleep(1000); 
  	 			
  	 			System.out.println("Step 7 : Verify User should not be able to View reports");
  	 			Reporter.log("Step 7 : Verify User should not be able to View reports");
  	 		
  	 		   			login.EnterUsername(Constants.CM_User_CanView_Reports);
  	 		   			login.EnterPassword(Constants.CM_Pwd_CanView_Reports);
  	 		   			login.ClickOnLogInButton(); 
  	 		   			
  	 		   		Thread.sleep(1000);	   
  	 		   		
  	 		   		if(!SeleniumFunc.IsElementPresent(role.ReportPara))
  	 				{   

  	 		   			System.out.println("Success !! User is not able to View reports");
  	 					Reporter.log("Success !! User is not able to View reports"); 
  	 				   
  	 				}
  	 				else
  	 				{
  	 					System.out.println("Failure !! User is  able to View reports");
  	 					Reporter.log("Failure !! User is  able to View reports"); 
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
	 * Role Permission  - Verify that user can his own edit permissions
	 */
	 @Test
	 public void  UserCanOwnEditPermissions() throws InterruptedException
	 {
	 	System.out.println("====" + "\n" +	
	 "Test 12 : Role Permission-Verify that user can his own edit permissions" + "\n" +
	 			"====");
	 	Reporter.log("====" + "\n" + 
	 			"Test 12 : Role Permissions-Verify that user can his own edit permissions"  + "\n" + 
	 			"====");	
						    
 	 		int AssertFailedCount=0 ;
 	 		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
 	 		LoginPage login = new LoginPage(driver);    	 		
 	 		RolesPermissionsPage role=new RolesPermissionsPage(driver);
 	 		GlobalHeader gheader = new GlobalHeader(driver);  	 		
 	 		CreateNewUserPage  newuser=new CreateNewUserPage(driver);
 	 		ProfilePage profile = new ProfilePage(driver);
 	 		
 	 	System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
 	 	Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
							
 	 		SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM); 	 		

 		System.out.println("Step 2 : Login with valid credentials");
  	 	Reporter.log("Step 2 : Login with valid credentials"); 	
 							   	 	
  	 		login.EnterUsername(Constants.CM_User_EditOwn_Permsision);
  			login.EnterPassword(Constants.CM_Pwd_EditOwn_Permsision);
  			login.ClickOnLogInButton(); 
 	  	 		
 		System.out.println("Step 3 : Navigate to  my account, edit permission and verify Role Permissions is displayed at user profile page.");
  	 	Reporter.log("Step 3 : Navigate to  my account, edit permission and verify Role Permissions is displayed at user profile page."); 
 		
  	 	
  	 	SeleniumFunc.ClickOnElement("css", "body > div.navbar.navbar-inverse.navbar-fixed-top > div > div > div > ul > li:nth-child(2) > a");
  	 	
  	 	Thread.sleep(1000);
  	 	SeleniumFunc.ClickOnElement("css", "body > div.navbar.navbar-inverse.navbar-fixed-top > div > div > div > ul > li.dropdown.open > ul > li:nth-child(1) > a");
  	 	
  	 	Thread.sleep(4000);
  	 	/*
  	 	//gheader.UsernameDropdown_NonAdmin.click();
  	 	gheader.UsernameDropdown_Forbid.click();
  	 	//gheader.UsernameDropdown.click();
		//gheader.Username_MyProfile_NonAdmin.click();
  	 	gheader.Username_MyProfile_Forbid.click();*/
  	 	
  	 	
		Thread.sleep(1000);
		profile.EditInfo.click();
 		if(!role.RemoveAllPermissions.isSelected())
 		{
 			role.ClickOnRemoveAllPermissions();
 		}
 		if(!role.ManageUser.isSelected())
 		{
 			role.ClickOnManageUser();
 		}
		
		Thread.sleep(1000);
		if(!role.ViewReports.isSelected() )
		{
			role.ClickOnViewReports();
		}
		Thread.sleep(1000);
	  
		newuser.EnterUserPasswardtext("clarion@123");
		newuser.EnterConfirmPasswardtext("clarion@123");
		Thread.sleep(1000);
		newuser.ClickOnSubmitBtnButton();
		Thread.sleep(1000);
		
	    String actualtext= SeleniumFunc.GetElementText("css", ".table.table-striped>tbody>tr:nth-of-type(2) td:nth-of-type(2)");
		String expectedtext= "Can view reports."; 
		
		if(actualtext.contains(expectedtext))
		{
			System.out.println("Success !! 'Can view reports.' Role Permissions is displayed at user profile page. ");
			Reporter.log("Success !! 'Can view reports.' Role Permissions is displayed at user profile page."); 
		}
		else
		{
			System.out.println("Failure !! 'Can view reports.' Role Permissions is not displayed at user profile page.");
			Reporter.log("Failure !! 'Can view reports.' Role Permissions is not displayed at user profile page.");
			
			AssertFailedCount++;
		}
		
		System.out.println("Step 4 : Navigate to  my account, edit permission and verify edit Permissions is not displayed at user profile page.");
  	 	Reporter.log("Step 4 : Navigate to  my account, edit permission and verify edit Permissions is not displayed at user profile page."); 
  	 	
		profile.EditInfo.click();
		role.ClickOnViewReports();
		newuser.EnterUserPasswardtext("clarion@123");
		newuser.EnterConfirmPasswardtext("clarion@123");
		Thread.sleep(1000);
		newuser.ClickOnSubmitBtnButton();
		Thread.sleep(1000);
       
		if(!SeleniumFunc.IsElementPresent(role.Rolepremission))
		{
			System.out.println("Success !! User is able to edit his own permeissions as 'Can view reports.' Role Permissions is displayed at user profile page. ");
			Reporter.log("Success !! User is able to edit his own permeissions as 'Can view reports.' Role Permissions is displayed at user profile page."); 
		}
		else
		{
			System.out.println("Failure !! User is not able to edit his own permeissions");
			Reporter.log("Failure !! User is not able to edit his own permeissions ");
			
			AssertFailedCount++;
		}
		
		//Re-set User to previous state
		
			
		
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
	* Role Permission  - Verify the functionality of 'Remove all permissions...' link
	*/
	@Test
	public void  VerifyFunctionalityOfRemoveAllPermissions() throws InterruptedException
	{
		System.out.println("====" + "\n" +	
				"Test 13: Role Permission - Verify the functionality of 'Remove all permissions...' link" + "\n" +
				"====");
		Reporter.log("====" + "\n" + 
				"Test 13 : Role Permissions -Verify the functionality of 'Remove all permissions...' link"  + "\n" +
				"====");	
						    
  	 		int AssertFailedCount=0 ;
  	 		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
  	 		LoginPage login = new LoginPage(driver);    	 		
  	 		RolesPermissionsPage role=new RolesPermissionsPage(driver);  	 				
  	 		CreateNewUserPage  newuser=new CreateNewUserPage(driver);
  	 	
  	 		
  	 	System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
  	 	Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
							
  	 		SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

  	 		System.out.println("Step 2 : Login with valid credentials");
  	  	 	Reporter.log("Step 2 : Login with valid credentials"); 	
  	 							   	 	
  	  	login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();
  	 		
  	 	System.out.println("Step 3 : Navigate to manage users > Edit account for user1");
  	 	Reporter.log("Step 3 : Navigate to manage users > Edit account for user1"); 
				
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
  	 		role.ClickOnRemoveAllPermissions();
  	 		Thread.sleep(1000);   	 
  	 		if(SeleniumFunc.IsElementPresent(role.WithoutPermissionsThisUser))
	  		{   

	  			System.out.println("Success !! Without permissions, this user is currently set to a read-only state text  is present");
	  			Reporter.log("Success !! Without permissions, this user is currently set to a read-only state text  is present"); 
		   
	  		}
	  		else
	  		{
	  			System.out.println("Failure !!Without permissions, this user is currently set to a read-only state text  is NOT present ");
	  			Reporter.log("Failure !! Without permissions, this user is currently set to a read-only state text is NOT present "); 
	  			AssertFailedCount++;
	  		}
  	 		if(!role.ViewReports.isSelected() && role.ManageUser.isEnabled())
  			{
  				role.ClickOnViewReports();
  				role.ClickOnManageUser();
  			}
  			newuser.EnterUserPasswardtext("clarion@123");
  			newuser.EnterConfirmPasswardtext("clarion@123");
  			Thread.sleep(1000);
  			newuser.ClickOnSubmitBtnButton();
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
	
	/* Test 14: 
	* Role Permission  - Verify that a user having more than one permission , combinations of those permissions work correctly.
	*/
	@Test
	public void  VerifyHavingMoreThanOnePremission() throws InterruptedException
	{
		System.out.println("====" + "\n" +	
					"Test 14: Role Permission - Verify that a user having more than one permission , combinations of those permissions work correctly." + "\n" +
					"====");
		Reporter.log("====" + "\n" + 
					"Test 14 : Role Permissions - Verify that a user having more than one permission , combinations of those permissions work correctly."  + "\n" +
					"====");	
						    
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		LoginPage login = new LoginPage(driver);    	 		
		RolesPermissionsPage role=new RolesPermissionsPage(driver);
		GlobalHeader gheader = new GlobalHeader(driver);	
		CreateNewUserPage  newuser=new CreateNewUserPage(driver);
		EditCertificationPage editcer = new EditCertificationPage(driver);

System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 

		SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 

		login.EnterUsername(Constants.CM_Username);
		login.EnterPassword(Constants.CM_Password);
		login.ClickOnLogInButton(); 			 

System.out.println("Step 3 : Go to manage users > User > Edit account");
Reporter.log("Step 3 : Go to manage users > User > Edit account"); 

		newuser.clickonadmintab();
		newuser.ClickOnManageusers();
		Thread.sleep(1000);
		newuser.EnterUserinfo("test6@clariontechnologies.co.in");
		newuser.ClcikOnSearch();
		Thread.sleep(1000);
		newuser.Cliconusertoedit();
		Thread.sleep(1000);
		newuser.Clickonedittab();
		Thread.sleep(1000);
//Verify  Presence of Role Permission Text
		if(SeleniumFunc.IsElementPresent(role.RolePermissions))
		{
			System.out.println("Success !! Role Permissions Text is present");
			Reporter.log("Success !!Role Permissions Text is present"); 
		}
		else
		{
			System.out.println("Failure !! Role Permissions Text is not present ");
			Reporter.log("Failure !! Role Permissions Text is not present "); 
			AssertFailedCount++;
		}

System.out.println("Step 4 : Navigate  to permissions section > Check 'Can add notes' and 'Can send course completion documents'user option and Submit");
Reporter.log("Step 4 : Navigate to permissions section > Check 'Can add notes' and 'Can send course completion documents' user option and Submit"); 

       Thread.sleep(2000);
       if(SeleniumFunc.IsElementPresent(role.RemoveAllPermissions))
       {
        role.ClickOnRemoveAllPermissions();
       }
        Thread.sleep(1000);
        
		
			role.ClickOnManageUser();
			role.ClickOnAddNotes();
			role.ClickOnSendCourceCompletionDoc();
		
		
		newuser.EnterUserPasswardtext("clarion@123");
		newuser.EnterConfirmPasswardtext("clarion@123");
		Thread.sleep(1000);
		newuser.ClickOnSubmitBtnButton();
		Thread.sleep(1000);
// Login  for Updated user   
		newuser.ClickOnusername();
		newuser.ClickonLogout();
		Thread.sleep(1000);

		System.out.println("Step 5 : Login with user 1, and verify  combinations of those permissions work correctly");
	 	Reporter.log("Step 5 : Login with user 1, and verify combinations of those permissions work correctly");

	 			login.EnterUsername(Constants.CM_User_Combination_Permsision);
	 			login.EnterPassword(Constants.CM_Pwd_Combination_Permsision);
	 			login.ClickOnLogInButton(); 
	 			Thread.sleep(2000);
	 			String actualtext12= gheader.Success_AlertText.getText().trim();
	 			String expectedtext12= "Signed in!";

	 			if(actualtext12.equals(expectedtext12))
	 			{
	 				System.out.println("Success !! Login  message is displayed i.e. " + actualtext12);
	 				Reporter.log("Success !! Login  message is displayed i.e. " + actualtext12); 
	 			}
	 			else
	 			{
	 				System.out.println("Failure !! incorrect  message is displayed" + "\n" + "Expected : "  
	 						+ "\n" + expectedtext12 + "\n" + 
	 						"Actual : " + "\n" +  actualtext12);
	 				Reporter.log("Failure !!  incorrect  message is displayed" + "\n" + "Expected : "  
	 						+ "\n" + expectedtext12 + "\n" + 
	 						"Actual : " + "\n" +  actualtext12);
	 				AssertFailedCount++;
	 			}
	 			
	 			    Thread.sleep(1000);
				    newuser.clickonadmintab();
					newuser.ClickOnManageusers();
					Thread.sleep(1000);
					newuser.EnterUserinfo("test6@clariontechnologies.co.in");
					newuser.ClcikOnSearch();
					Thread.sleep(1000);
					newuser.Cliconusertoedit();
					Thread.sleep(1000);
					
					   String actualtext= SeleniumFunc.GetElementText("css", ".table.table-striped>tbody>tr:nth-of-type(2) td:nth-of-type(2)");
						String expectedtext= "Can add notes."; 
						
						  String actualtext1= SeleniumFunc.GetElementText("css", ".table.table-striped>tbody>tr:nth-of-type(3) td:nth-of-type(2)");
							String expectedtext1= "Can send course completion documents."; 
						
						if(actualtext1.contains(expectedtext1) && (actualtext.contains(expectedtext)))
						{
							System.out.println("Success !! User having more than one premission , ");
							Reporter.log("Success !! User having more than one premission , "); 
						}
						else
						{
							System.out.println("Failure !! User dont  have more than one premission , ");
							Reporter.log("Failure !! User dont have more than one premission , ");
							
							AssertFailedCount++;
						}
				
				Thread.sleep(1000);	   
				role.ClickOnCertificationManager();
				Thread.sleep(1000);	   
	 			editcer.SearchCM("Lisa M Carfi");
	 			Thread.sleep(1000);
				editcer.ClickOnSearch();
				editcer.ClickOnStudentNametab();
				Thread.sleep(1000);
				
				if(SeleniumFunc.IsElementPresent(role.ResendCourseCompletionLink))
				{   
					System.out.println("Success !! 'Resend course completion' button is present.");
					Reporter.log("Success !! 'Resend course completion' button is present."); 
				}
				else
				{
					System.out.println("Failure !! 'Resend course completion' button is not present. ");
					Reporter.log("Failure !! 'Resend course completion' button is not present."); 
					AssertFailedCount++;
				}
				Thread.sleep(2000);
				role.ClickOnResendCourseCompletionLink();
				Thread.sleep(2000);
				String msg=SeleniumFunc.GetElementText("css", ".alert.alert-info");
				if(msg.contains("Course completion sent to"))
				{   
					System.out.println("Success !! User is able send documents.");
					Reporter.log("Success !! User is able send documents."); 
				}
				else
				{
					System.out.println("Failure !! User is not able send documents.");
					Reporter.log("Failure !! User is not able send documents"); 
					AssertFailedCount++;
				}
	   		
				Thread.sleep(1000);
				role.EnterAddTheNote("ClarionQa");
				role.ClickOnAddNoteBtn();
				String message=SeleniumFunc.GetElementText("css", ".alert.alert-success");
			
				if(message.equals("Note was successfully created."))
				{   
					System.out.println("Success !! Note was successfully created.");
					Reporter.log("Success !! Note was successfully created."); 
				}
				else
				{
					System.out.println("Failure !! Note is not created. ");
					Reporter.log("Failure !!Note is not created."); 
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
    * Role Permission  - Test that Admin Role has access to all rights
	*/
	@Test
	public void  VerifyAdminRole() throws InterruptedException
	{
 			System.out.println("====" + "\n" +
 					"Test 15: Role Permission  - Test that Admin Role has access to all rights" + "\n" +
 					"====");
		 	Reporter.log("====" + "\n" + 
 					"Test 15 : Role Permission  - Test that Admin Role has access to all rights"  + "\n" +
		 			"====");	
						    
  	 			int AssertFailedCount=0 ;
  	 			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
  	 			LoginPage login = new LoginPage(driver);    	 		
  	 			RolesPermissionsPage role=new RolesPermissionsPage(driver); 	 
  	 			CreateNewUserPage  newuser=new CreateNewUserPage(driver);
  	 		  	 		
  	 		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
  	 		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
							
  	 			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

  	 			System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
  	   	 		Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 	

  	   	 				login.EnterUsername(Constants.CM_Username);
  	   	 				login.EnterPassword(Constants.CM_Password);
  	   	 				login.ClickOnLogInButton();   
  	   	 		
  	   	 		System.out.println("Step 3 : Go to manage users > User > Edit account");
  	   	 		Reporter.log("Step 3 : Go to manage users > User > Edit account"); 
  		      
  	   	 	             newuser.ClickOnusername();   
  	 	                role.ClickOnMyProfile();  
  	   	 				newuser.Clickonedittab();
  	   	 				Thread.sleep(1000);
  				
  	   	 				//Verify  Presence of Role Permission Text
  	   	 				if(role.RolePermissions.isEnabled())
  	   	 				{
  	   	 					System.out.println("Success !! Role Permissions Text is enabled");
  	   	 					Reporter.log("Success !!Role Permissions Text is enabled"); 
  	   	 				}
  	   	 				else
  	   	 				{
  	   	 					System.out.println("Failure !! Role Permissions Text is not enabled ");
  	   	 					Reporter.log("Failure !! Role Permissions Text is not enabled "); 
  	   	 					AssertFailedCount++;
  	   	 				}
  			
  	   	 				//Verifying presence of  Can manage users check box field
  	   	 				if(role.ManageUser.isEnabled())
  	   	 				{
  	   	 					System.out.println("Success !! Manage Users  check box is enabled");
  	   	 					Reporter.log("Success !! Manage Users  check box is enabled"); 
  	   	 				}
  	   	 				else
  	   	 				{
  	   	 					System.out.println("Failure !!Manage Users  check box is not enabled ");
  	   	 					Reporter.log("Failure !!Manage Users  check box is not enabled "); 
  	   	 					AssertFailedCount++;
  	   	 				}
  				
  	   	 				//Verifying presence of  Can bypass payments  check box field
  	   	 				if(role.ByPassPament.isEnabled())
  	   	 				{   
  	   	 					System.out.println("Success !! Bypass Payments  check box is enabled");
  	   	 					Reporter.log("Success !!Bypass Payments  check box is enabled"); 
  	   	 				}
  	   	 				else
  	   	 				{
  	   	 					System.out.println("Failure !! Bypass Payments  check box is not enabled ");
  	   	 					Reporter.log("Failure !! Bypass Payments  check box is not enabled "); 
  	   	 					AssertFailedCount++;
  	   	 				}
  				
  	   	 				//Verifying presence of  edit certifications  check box   field
  	   	 				if(role.EditCertifications.isEnabled())
  	   	 				{   
  	   	 					System.out.println("Success !! Edit Certifications.  check box is enabled");
  	   	 					Reporter.log("Success !!Edit Certifications.  check box is enabled"); 
  	   	 				}
  	   	 				else
  	   	 				{
  	   	 					System.out.println("Failure !! Edit Certifications.  check box is not enabled ");
  	   	 					Reporter.log("Failure !! Edit Certifications.  check box is not enabled "); 
  	   	 					AssertFailedCount++;
  	   	 				}
  				
  	   	 				//Verifying presence of   view reports  check box   field
  	   	 				if(role.ViewReports.isEnabled())
  	   	 				{   
  	   	 					System.out.println("Success !! View Reports  check box is enabled");
  	   	 					Reporter.log("Success !!View Reports  check box is enabled"); 
  	   	 				}
  	   	 				else
  	   	 				{
  	   	 					System.out.println("Failure !! view reports  check box is not enabled ");
  	   	 					Reporter.log("Failure !! view reports  check box is not enabled "); 
  	   	 					AssertFailedCount++;
  	   	 				}
  	   	 				//Verifying presence of   create certifications check box   field
  	   	 				if(role.CertificationCreater.isEnabled())
  	   	 				{   
  	   	 					System.out.println("Success !! Create Certifications  check box is enabled");
  	   	 					Reporter.log("Success !!Create Certifications check box is enabled"); 
  	   	 				}
  	   	 				else
  	   	 				{
  	   	 					System.out.println("Failure !!Create Certifications  check box is not enabled ");
  	   	 					Reporter.log("Failure !!Create Certifications  check box is not enabled "); 
  	   	 					AssertFailedCount++;
  	   	 				}
  				
  	   	 				//Verifying presence of   edit collections. check box field
  	   	 				if(role.EditCollections.isEnabled())
  	   	 				{   
  	   	 					System.out.println("Success !! Edit Collections.  check box is enabled");
  	   	 					Reporter.log("Success !!Edit Collections. check box is enabled"); 
  	   	 				}
  	   	 				else
  	   	 				{
  	   	 					System.out.println("Failure !! Edit Collections. check box is not enabled ");
  	   	 					Reporter.log("Failure !! Edit Collections.  check box is not enabled "); 
  	   	 					AssertFailedCount++;
  	   	 				}
  		        	
  	   	 				//Verifying presence of    add notes check box   field
  	   	 				if(role.AddNotes.isEnabled())
  	   	 				{   
  	   	 					System.out.println("Success !! Add Notes  text Box is enabled");
  	   	 					Reporter.log("Success !!Add Notes text box is enabled"); 
  	   	 				}
  	   	 				else
  	   	 				{
  	   	 					System.out.println("Failure !! Add Notes text box is not enabled ");
  	   	 					Reporter.log("Failure !! Add Notes  text box is not enabled "); 
  	   	 					AssertFailedCount++;
  	   	 				}
  	   	 				//Verifying presence of order permanent certification cards. check box   field
  	   	 				if(role.CanOrderpermanentCertificationCards.isEnabled())
  	   	 				{   
  	   	 					System.out.println("Success !! Order Permanent Certification Cards  check box is enabled");
  	   	 					Reporter.log("Success !!Order Permanent Certification Cards check box is enabled"); 
  	   	 				}
  	   	 				else
  	   	 				{
  	   	 					System.out.println("Failure !! Order Permanent Certification Cards  check box is not enabled ");
  	   	 					Reporter.log("Failure !! Order Permanent Certification Cards   check box is not enabled "); 
  	   	 					AssertFailedCount++;
  	   	 				}
  				
  	   	 				//Verifying presence of send course completion documents. check box field
  	   	 				if(role.SendCourceCompletionDoc.isEnabled())
  	   	 				{   
  	   	 					System.out.println("Success !! Send Course Completion Documents  check box is enabled");
  	   	 					Reporter.log("Success !!Send Course Completion Documents check box is enabled"); 
  	   	 				}
  	   	 				else
  	   	 				{
  	   	 					System.out.println("Failure !! Send Course Completion Documents check box is not enabled ");
  	   	 					Reporter.log("Failure !! Send Course Completion Documents check box is not enabled "); 
  	   	 					AssertFailedCount++;
  	   	 				}
  				
  	   	 				//Verifying presence of import certifications for their agency check box   field
  	   	 				if(newuser.clickOnimportcertifications.isEnabled())
  	   	 				{   
  	   	 					System.out.println("Success !! Import Certifications for their agency  check box is enabled");
  	   	 					Reporter.log("Success !Import Certifications for their agency check box is enabled"); 
  	   	 				}
  	   	 				else
  	   	 				{
  	   	 					System.out.println("Failure !! Import Certifications for their agency check box is not enabled ");
  	   	 					Reporter.log("Failure !! Import Certifications for their agency  check box is not enabled "); 
  	   	 					AssertFailedCount++;
  	   	 				}
  	   	 				if(role.CanManageAgencies.isEnabled())
  	   	 				{   
  	   	 					System.out.println("Success !! Can Manage Agencies  check box is enabled");
  	   	 					Reporter.log("Success !Can Manage Agencies  check box is enabled"); 
  	   	 				}
  	   	 				else
  	   	 				{
  	   	 					System.out.println("Failure !! Can Manage Agenciescheck box is not enabled ");
  	   	 					Reporter.log("Failure !! Can Manage Agencies  check box is not enabled "); 
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
	

	
	/* Test 16: 
	* Role Permission  - Verify that user having less permissions can not access admin information
	*/
	@Test
	public void  VerifyUserLessPermissionsCanNotAccessAdmin() throws InterruptedException	
	{
			System.out.println("====" + "\n" +	
						"Test 16: Role Permission - Verify that user having less permissions can not access admin information" + "\n" +
						"====");
			Reporter.log("====" + "\n" + 
						"Test 16 : Role Permissions - Verify that user having less permissions can not access admin information"  + "\n" + 
						"====");	
						    
  	 				int AssertFailedCount=0 ;
  	 				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
  	 				LoginPage login = new LoginPage(driver);    	 		
  	 				RolesPermissionsPage role=new RolesPermissionsPage(driver);  	 		
  	 				CreateNewUserPage  newuser=new CreateNewUserPage(driver);
  	 		
  	 		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
  	 		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
							
  	 				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

  	 		System.out.println("Step 2 : Login with user1 and navigated to manage users.");
  	 		Reporter.log("Step 2 : Login with user1 and navigated to manage users."); 	
						   	 	
  	 				login.EnterUsername(Constants.CM_User_Less_Permission);
  	 				login.EnterPassword(Constants.CM_Pwd_Less_Permission);
  	 				login.ClickOnLogInButton();  
  	 				newuser.clickonadmintab();
  	 				newuser.ClickOnManageusers();
							    	
  	 		System.out.println("Step 3 : Search for admin under manage users");
  	 		Reporter.log("Step 3 : Search for admin under manage users"); 
  	 		
  	 				role.EnterUserInfo1(Constants.CM_Username);
  	 				role.ClickOnSeachButton();			    
  	 		
  	 		System.out.println("Step 4 : Verify Admin details can not be seen under user1");
  	 		Reporter.log("Step 4 : Verify Admin details can not be seen under user1"); 
  	 		
  	 				String alertmessage=SeleniumFunc.GetElementText("css", "div.alert.alert-notice");
  	 				if(alertmessage.equals("We found no users matching your query."))
  	 				{   
  	 					System.out.println("Success !! 'We found no users matching your query' error message  is displayed");
  	 					Reporter.log("Success !! 'We found no users matching your query' error message  is displayed"); 
  	 				}
  	 				else
  	 				{
  	 					System.out.println("Failure !! Admin details are seen under user1 ");
  	 					Reporter.log("Failure !!  Admin details are seen under user1  "); 
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
	* Role Permission  - Verify granted permissions can be viewed on User details screen
	*/
		@Test
		public void  VerifyUserCanVeiwedOnUserDetails() throws InterruptedException	
		{
				System.out.println("====" + "\n" +	
						"Test 17: Role Permission -Verify granted permissions can be viewed on User details screen" + "\n" +
						"====");
				Reporter.log("====" + "\n" + 
						"Test 17 : Role Permissions - Verify granted permissions can be viewed on User details screen"  + "\n" + 
						"====");	
						    
						int AssertFailedCount=0 ;
						SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
						LoginPage login = new LoginPage(driver);    	 		
						RolesPermissionsPage role=new RolesPermissionsPage(driver);  	 		
						CreateNewUserPage  newuser=new CreateNewUserPage(driver);
  	 		
				System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
				Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
					
						SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

				System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
				Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 	

						login.EnterUsername(Constants.CM_User_View_Permisson);
						login.EnterPassword(Constants.CM_Pwd_View_Permisson);
						login.ClickOnLogInButton();   
						   	 	
				System.out.println("Step 3 : Navigate to  User profile and verify Role permissions  is displayed");
				Reporter.log("Step 3 : Navigate to  User profile and verify Role permissions is displayed"); 
	
						newuser.ClickOnusername();   
						role.ClickOnMyProfile();  	
						Thread.sleep(2000);
						role.ClickOnEditInfo();
						Thread.sleep(2000);
						if(SeleniumFunc.IsElementPresent(role.RemoveAllPermissions))
						{
							role.ClickOnRemoveAllPermissions();
						}
						Thread.sleep(1000);
						role.ClickOnManageUser();
						role.ClickOnAddNotes();
						role.ClickOnSendCourceCompletionDoc();
						newuser.EnterUserPasswardtext("clarion@123");
						newuser.EnterConfirmPasswardtext("clarion@123");
						Thread.sleep(1000);
						newuser.ClickOnSubmitBtnButton();
						Thread.sleep(1000);
						String actualtext= SeleniumFunc.GetElementText("css", ".table.table-striped>tbody>tr:nth-of-type(2) td:nth-of-type(2)");
						String expectedtext= "Can add notes."; 
						String actualtext1= SeleniumFunc.GetElementText("css", ".table.table-striped>tbody>tr:nth-of-type(3) td:nth-of-type(2)");
						String expectedtext1= "Can send course completion documents."; 
						if(actualtext1.contains(expectedtext1) && (actualtext.contains(expectedtext)))
						{
							System.out.println("Success !! Permissions set is viewed on User details screen under Role Permissions ");
							Reporter.log("Success !! Permissions set is viewed on User details screen under Role Permissions "); 
						}
						else
						{
							System.out.println("Failure !! Permissions set is not viewed on User details screen under Role Permissions ");
							Reporter.log("Failure !! Permissions set is not viewed on User details screen under Role Permissions  ");
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
	* Role Permission  - Verify that a user not having permission ''Can bypass payment' should be able to bypass payment
	*/
	 @Test
     public void  VerifyUserCanBypassPaymentORNot() throws InterruptedException
     {
		 System.out.println("====" + "\n" +
				"Test 18 : Role Permission - Verify that a user not having permission ''Can bypass payment' should be able to bypass payment." + "\n" +
				"====");
		 Reporter.log("====" + "\n" +
				  "Test 18 : Role Permission - Verify that a user not having permission ''Can bypass payment' should be able to bypass payment."  + "\n" +
			 	  "====");	
	    
		 		int AssertFailedCount=0 ;
		 		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		 		LoginPage login = new LoginPage(driver);    	 		
		 		RolesPermissionsPage role=new RolesPermissionsPage(driver);
		 		GlobalHeader gheader = new GlobalHeader(driver);	
		 		CreateNewUserPage  newuser=new CreateNewUserPage(driver);
		 		EditCertificationPage editcer = new EditCertificationPage(driver);
		 		DetailsCertificationPage DetailsPage=new DetailsCertificationPage(driver);    	
		 	
		 System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		 Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
		
		 		SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

		 System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
		 Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 

		 		login.EnterUsername(Constants.CM_Username);
		 		login.EnterPassword(Constants.CM_Password);
		 		login.ClickOnLogInButton(); 			 

		 System.out.println("Step 3 : Go to manage users > User > Edit account");
		 Reporter.log("Step 3 : Go to manage users > User > Edit account"); 

		 		newuser.clickonadmintab();
		 		newuser.ClickOnManageusers();
		 		Thread.sleep(1000);
		 		newuser.EnterUserinfo("test13@clariontechnologies.co.in");
		 		newuser.ClcikOnSearch();
		 		Thread.sleep(1000);
		 		newuser.Cliconusertoedit();
		 		Thread.sleep(1000);
		 		newuser.Clickonedittab();
		 		Thread.sleep(1000);
		 //Verify  Presence of Role Permission Text
				if(SeleniumFunc.IsElementPresent(role.RolePermissions))
				{
					System.out.println("Success !! Role Permissions Text is present");
					Reporter.log("Success !!Role Permissions Text is present"); 
				}
				else
				{
					System.out.println("Failure !! Role Permissions Text is not present ");
					Reporter.log("Failure !! Role Permissions Text is not present "); 
					AssertFailedCount++;
				}

		System.out.println("Step 4 : Navigate  to permissions section > Check 'Can bypass payment' user option and Submit");
		Reporter.log("Step 4 : Navigate to permissions section > Check 'Can bypass payment' user option and Submit"); 

				if(!role.ByPassPament.isSelected())
				{
					role.ClickOnByPassPament();
				}
				if(!role.CanOrderpermanentCertificationCards.isSelected())
				{
					role.ClickOnCanOrderpermanentCertificationCards();
				}
				newuser.EnterUserPasswardtext("clarion@123");
				newuser.EnterConfirmPasswardtext("clarion@123");
				Thread.sleep(1000);
				newuser.ClickOnSubmitBtnButton();
				Thread.sleep(1000);
			// Login  for Updated user   
				newuser.ClickOnusername();
				newuser.ClickonLogout();
				Thread.sleep(1000);
			
		System.out.println("Step 5 : Login with user 1 and verify User should be able to View reports");
		Reporter.log("Step 5 : Login with user 1 and verify User should be able to View reports");

				login.EnterUsername(Constants.CM_User_Order_ByPass);
				login.EnterPassword(Constants.CM_Pwd_Order_ByPass);
				login.ClickOnLogInButton(); 
				Thread.sleep(2000);
				String actualtext12= gheader.Success_AlertText.getText().trim();
				String expectedtext12= "Signed in!";
				if(actualtext12.equals(expectedtext12))
				{
					System.out.println("Success !! Login  message is displayed i.e. " + actualtext12);
					Reporter.log("Success !! Login  message is displayed i.e. " + actualtext12); 
				}
				else
				{
					System.out.println("Failure !! incorrect  message is displayed" + "\n" + "Expected : "  
							+ "\n" + expectedtext12 + "\n" + 
							"Actual : " + "\n" +  actualtext12);
					Reporter.log("Failure !!  incorrect  message is displayed" + "\n" + "Expected : "  
							+ "\n" + expectedtext12 + "\n" + 
							"Actual : " + "\n" +  actualtext12);
					AssertFailedCount++;
					}
					Thread.sleep(1000);			
					//SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "state",39);					
					//Thread.sleep(1000);
					//SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "certification_type_id", 2);		
					editcer.SearchCM("Shana F Melton");
					Thread.sleep(1000);
					editcer.ClickOnSearch();
					Thread.sleep(1000);
					editcer.ClickOnStudentNametab();		
					Thread.sleep(1000);
					if(SeleniumFunc.IsElementPresent(role.OrderRplacementCertificate))
					{   
						System.out.println("Success !! Order Rplacement Certificate button is present");
						Reporter.log("Success !!Order Rplacement Certificate button is present"); 
					}
					else
					{
						System.out.println("Failure !! Order Rplacement Certificate button is not present ");
						Reporter.log("Failure !! Order Rplacement Certificate button is not present "); 
						AssertFailedCount++;
					}	
			//Click on OrderReplacementCertificate 
					DetailsPage.ClickOnOrderReplacementCerti();
					Thread.sleep(1000);	  
		    
			System.out.println("Step 6 : Perform a payment bypass, choose any reason,enter comments and verify user is able to order certificate.");
			Reporter.log("Step 6 : Perform a payment bypass, choose any reason,enter comments and verify user is able to order certificate.");
	   		
			
					DetailsPage.ClickOnBypassPayment();
	   				Thread.sleep(1000);
	   				SeleniumFunc.SelectValueFromDropdownList("css", "#historical_action_type_id", "Payment by Check");
	   				SeleniumFunc.SelectValueFromDropdownListUsingIndex("css", "#quantity",3); 
	   				DetailsPage.EnterReasonSelectText("Pay By Cash");
	   				DetailsPage.ClickOnContinueButton();
	   				Thread.sleep(2000);
	   				if(SeleniumFunc.IsElementPresent(DetailsPage.ByPassPaymenttext))
	   				{
	   					System.out.println("Success !! Payment has been bypassed for this certification order Text  is present and user is able to order certificate");
	   					Reporter.log("Success !!  Payment has been bypassed for this certification order Text is present and user is able to order certificate"); 
	   				}
	   				else
	   				{
	   					System.out.println("Failure !!  Payment has been bypassed for this certification order Text is not present and user is not able to order certificate ");
	   					Reporter.log("Failure !!  Payment has been bypassed for this certification order Text is not present and user is not able to order certificate"); 
	   					AssertFailedCount++;
	   				}
	   		// Logout  for Updated user   
	   				Thread.sleep(1000);
	   				newuser.ClickOnusername();
	   				newuser.ClickonLogout();
	   				Thread.sleep(1000); 
			
	   		System.out.println("Step 7 : Login with admin and Uncheck 'Can order permanent cetification cards' option");
			Reporter.log("Step 7 : Login with admin and Uncheck 'Can order permanent cetification cards' option");
	    
					login.EnterUsername(Constants.CM_Username);
   	 				login.EnterPassword(Constants.CM_Password);
   	 				login.ClickOnLogInButton();
   	 				Thread.sleep(2000);
   	 				newuser.clickonadmintab();
   	 				newuser.ClickOnManageusers();
   	 				Thread.sleep(1000);
   	 				newuser.EnterUserinfo("test13@clariontechnologies.co.in");
   	 				newuser.ClcikOnSearch();
   	 				Thread.sleep(1000);
   	 				newuser.Cliconusertoedit();
   	 				Thread.sleep(1000);
   	 				newuser.Clickonedittab();
   	 				Thread.sleep(1000);
   	 				role.ClickOnByPassPament();	 
   	 				newuser.EnterUserPasswardtext("clarion@123");
   	 				newuser.EnterConfirmPasswardtext("clarion@123");
   	 				Thread.sleep(1000);
   	 				newuser.ClickOnSubmitBtnButton();
   	 				Thread.sleep(1000);
   	 		// Logout  for Updated user   
   	 				newuser.ClickOnusername();
   	 				newuser.ClickonLogout();
   	 				Thread.sleep(1000); 
  	 		
   	 		System.out.println("Step 8 : Login with user1 and Verify Order Rplacement Certificate button is no longer be visible on cert. details screen");
   	 		Reporter.log("Step 8 : Login with user1 and Verify Order Rplacement Certificate button is no longer be visible on cert. details screen");
			
   	 				login.EnterUsername(Constants.CM_User_Order_ByPass);
  	  	   			login.EnterPassword(Constants.CM_Pwd_Order_ByPass);
  	  	   			login.ClickOnLogInButton(); 
  	  	   			//Thread.sleep(1000);			
  	  	   			//SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "state",39);					
  	  	   			Thread.sleep(1000);
  	  	   			//SeleniumFunc.SelectValueFromDropdownListUsingIndex("id", "certification_type_id", 2);		
  	  	   			editcer.SearchCM("Shana F Melton");
  	  	   			Thread.sleep(1000);
  	  	   			editcer.ClickOnSearch();
  	  	   			Thread.sleep(1000);
  	  	   			editcer.ClickOnStudentNametab();		
  	  	   			Thread.sleep(1000);
  	  	   			role.ClickOnOrderRplacementCertificate();
				
  	  	   			if(!SeleniumFunc.IsElementPresent(role.ByPassPaymentLink))
  	  	   			{   
  	  	   				System.out.println("Success !! User is not  able to  do bypass payment");
  	  	   				Reporter.log("Success !! User is not  able to  do bypass payment"); 
  	  	   			}
  	  	   			else
  	  	   			{
  	  	   				System.out.println("Failure !! permission ''Can bypass payment' is not working");
  	  	   				Reporter.log("Failure !! permission ''Can bypass payment' is not working"); 
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
		* Role Permission  - Only collection editors can add or edit a collection mapping.
		*/
		 @Test
	     public void  VerifyUserOnlyCollectionEditors() throws InterruptedException
	     {
			 System.out.println("====" + "\n" +
					"Test 19 : Role Permission - Verify  Only collection editors can add or edit a collection mapping." + "\n" +
					"====");
			 Reporter.log("====" + "\n" +
					  "Test 19 : Role Permission - Verify Only collection editors can add or edit a collection mapping."  + "\n" +
				 	  "====");	
		    
			 		int AssertFailedCount=0 ;
			 		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			 		LoginPage login = new LoginPage(driver);    	 		
			 		RolesPermissionsPage role=new RolesPermissionsPage(driver);			 	
			 		CreateNewUserPage  newuser=new CreateNewUserPage(driver);
			 	
			 	
			 System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
			 Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
			 		SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);

			 System.out.println("Step 2 : Enter valid login credentials and click on Login button.");
			 Reporter.log("Step 2 : Enter valid login credentials and click on Login button."); 

			 		login.EnterUsername(Constants.CM_User_CollectionAdd);
			 		login.EnterPassword(Constants.CM_Pwd_CollectionAdd);
			 		login.ClickOnLogInButton(); 
			 		Thread.sleep(2000);
	   	 		
			 System.out.println("Step 3 : Verify user Should NOT see a url for managing collection origins.");
			 Reporter.log("Step 3 : Verify user Should NOT see a url for managing collection origins."); 
		   	 		
			 		SeleniumFunc.ClickOnElement("css", "body > div.navbar.navbar-inverse.navbar-fixed-top > div > div > div > ul > li > a");
			 		//newuser.clickonadmintab();
			 		if(!SeleniumFunc.IsElementPresent(role.ManageCollectionOrigins))
			 		{   
			 			System.out.println("Success !! Manage Collection Origins Link is NOT present ");
			 			Reporter.log(" Success!! Manage Collection Origins Link is NOT present "); 
		 			}
			 		else
			 		{
			 			System.out.println("Failure !! Manage Collection Origins Link is present");
			 			Reporter.log("Failure !! Manage Collection Origins Link is present"); 
			 			AssertFailedCount++;
			 		}
		    	
		    	
			 System.out.println("Step 4 : Navigate to /certification_collections URL and Verify Error Mesage  ");
			 Reporter.log("Step 4 : Navigate to /certification_collections URL and Verify Error Mesage  "); 
		    			    	
			 		SeleniumFunc.ToGoToUrl("https://kps.staging.kalkomey.com/certification_collections");
			 		String error=SeleniumFunc.GetElementText("css", ".dialog>h1");
			 		if(error.equals("You are not authorized to look at this page."))
			 		{   
			 			System.out.println("Success !! 'You are not authorized to look at this page.' message is displayed.");
			 			Reporter.log("Success !! 'You are not authorized to look at this page.' message is displayed."); 
			 		}
			 		else
			 		{
			 			System.out.println("Failure !! 'You are not authorized to look at this page.' message is not displayed.");
			 			Reporter.log("Failure !! 'You are not authorized to look at this page.' message is not displayed."); 
			 			AssertFailedCount++;
			 		}
			 		Thread.sleep(1000);
			 		SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
			 		Thread.sleep(1000);	    	
		    	
			 // Logout  for Updated user   
			 		Thread.sleep(2000);
			 		newuser.ClickOnusername();
			 		newuser.ClickonLogout();
			 		Thread.sleep(2000);     	
		    	
			 System.out.println("Step 5 : Log in as a user that does has 'Can edit collections' permissions ");
			 Reporter.log("Step 5 : Log in as a user that does has 'Can edit collections' permissions"); 
		    	
			 		Thread.sleep(1000);
			 		SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);
			 		login.EnterUsername(Constants.CM_Username);
			 		login.EnterPassword(Constants.CM_Password);
			 		login.ClickOnLogInButton();
			 		newuser.clickonadmintab();
			 		if(SeleniumFunc.IsElementPresent(role.ManageCollectionOrigins))
			 		{   
			 			System.out.println("Success !! Manage Collection Origins Link is present");
			 			Reporter.log("Success !!Manage Collection Origins Link is present"); 
			 		}
			 		else
			 		{
			 			System.out.println("Failure !!Manage Collection Origins Link is NOT present ");
			 			Reporter.log(" Failure!! Manage Collection Origins Link is NOT present "); 
			 			AssertFailedCount++;
			 		}

			 System.out.println("Step 6 : Navigate to /certification_collections URL and Verify   user can see the certification origins listing page. ");
			 Reporter.log("Step 6 :Navigate to /certification_collections URL and Verify  user can see the certification origins listing page. "); 
			 		
			 		Thread.sleep(1000);		    	
			 		SeleniumFunc.ToGoToUrl("https://kps.staging.kalkomey.com/certification_collections");
			 		Thread.sleep(1000);
			 		String message=SeleniumFunc.GetElementText("css", "header.widget-header>h3");
			 		if(message.equals("Search Certification Collections"))
			 		{   
			 			System.out.println("Success !!  user can see the certification origins listing page. ");
			 			Reporter.log(" Success!!  user can see the certification origins listing page. "); 
		 			}
			 		else
			 		{
			 			System.out.println("Failure !!  user can not see the certification origins listing page.");
			 			Reporter.log("Failure !!  user can not see the certification origins listing page."); 
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

