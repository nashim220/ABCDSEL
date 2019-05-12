package products.CM;


import org.openqa.selenium.By;
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
import pageFactory.CM.ManageAgencyPage;
import pageFactory.CM.ProfilePage;
import pageFactory.CM.RolesPermissionsPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class ManageAgencyTest 
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
	 * Verify  - Kalkomey users can be assigned the "Can manage agencies" permission
	*/ 	
	@Test
	private void KalUserCanAssignAgencies() throws Exception
	{
			System.out.println("====" + "\n" 
	                        +"Test 1 : Kalkomey users can be assigned the Can manage agencies  permission"  + "\n" +
							"====");
			Reporter.log("====" + "\n" + 
							"Test 1 : Kalkomey users can be assigned the  Can manage agencies  permission"  + "\n" +	  
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
	  			Thread.sleep(3000);
	  			
	  	System.out.println("Step 3 : Go to manage users > User > Edit account");
	  	Reporter.log("Step 3 : Go to manage users > User > Edit account"); 
		      
	  			newuser.clickonadmintab();
	  			Thread.sleep(4000);
	  			newuser.ClickOnManageusers();
	  			Thread.sleep(4000);
	  			SeleniumFunc.SelectValueFromDropdownList("css", "#agency_id", "test ke-testing");
	  			Thread.sleep(4000);
		  		newuser.EnterUserinfo("test1@clariontechnologies.co.in");
		  		newuser.ClcikOnSearch();
		    	Thread.sleep(4000);
				newuser.Cliconusertoedit();
		  		Thread.sleep(4000);
				newuser.Clickonedittab();
		  		Thread.sleep(4000);
			
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
	 
		        Thread.sleep(4000);
				role.ClickOnRemoveAllPermissions();
		     
				role.CanManageAgencies.click();
				Thread.sleep(4000);
			
				//Test
				newuser.EnterUserPasswardtext("clarion@123");
				newuser.EnterConfirmPasswardtext("clarion@123");
				Thread.sleep(2000);
				newuser.ClickOnSubmitBtnButton();
				Thread.sleep(3000);
				String actualtext= SeleniumFunc.GetElementText("css", ".table.table-striped>tbody>tr td:nth-of-type(2)");
				String expectedtext= "Can manage agencies"; 
				if(actualtext.contains(expectedtext))
				{
					System.out.println("Success !! The 'view user' page now appears, with the 'Can manage agencies' checkbox checked.");
					Reporter.log("Success !! The 'view user' page now appears, with the 'Can manage agencies' checkbox checked."); 
				}
				else
				{
					System.out.println("Failure !! The 'view user' page now appears, without the 'Can manage agencies' checkbox checked.");
					Reporter.log("Failure !! The 'view user' page now appears, without the 'Can manage agencies' checkbox checked.");
					AssertFailedCount++;
				}
				
			// again assigned all permissions
					Thread.sleep(1000);
					newuser.Clickonedittab();
		  			Thread.sleep(2000);
		  			role.clickonAllpremission();
		  			role.CanManageAgencies.click();
					newuser.EnterUserPasswardtext("clarion@123");
					newuser.EnterConfirmPasswardtext("clarion@123");
					Thread.sleep(1000);
					newuser.ClickOnSubmitBtnButton();
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

	/* Test 2: 
	 * Verify  Manage Agencies is visible to Kalkomey user with permission to edit agencies and takes user to the Agency search page
	*/ 	
	@Test
	private void VerifyNavigation() throws Exception
	{
			System.out.println("====" + "\n" +
					"Test 2 : Verify Manage Agencies is visible to Kalkomey user with permission to edit agencies and takes user to the Agency search page"  + "\n" +
		 			                                  "====");
			Reporter.log("====" + "\n" +
		 			  "Test 2 : Manage Agencies is visible to Kalkomey user with permission to edit agencies and takes user to the Agency search page"  + "\n" +
				 	                                    "====");	
		
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			GlobalHeader header = new GlobalHeader(driver);
			ManageAgencyPage manageagency = new ManageAgencyPage(driver);
			
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
		
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);	
			
		System.out.println("Step 2 : Login to application and navigating to Manage Agency  page");
		Reporter.log("Step 2 : Login to application and navigating to Manage Agency  page"); 			
		
			login.EnterUsername(Constants.CM_Username);
			login.EnterPassword(Constants.CM_Password);
			login.ClickOnLogInButton();			
			Thread.sleep(3000);
			header.AdminDropdown.click();
			 //Verify  Presence of Manage Agencies
			if(SeleniumFunc.IsElementPresent(manageagency.ManageAgencieslink))
			{
	  				
				System.out.println("Success !! Manage Agencies Text is present");
				Reporter.log("Success !! Manage Agencies Text is present"); 
			}
			else
			{
				System.out.println("Failure !! Manage Agencies Text is NOT present ");
				Reporter.log("Failure !! Manage Agencies Text is NOT present "); 
				AssertFailedCount++;
			}
			
			header.Admin_ManageAgency.click();
			Thread.sleep(1000);
			
		System.out.println("Step 3 : Verify control is redirected to Manage Users page");
		Reporter.log("Step 3 : Verify control is redirected to Manage Users page");	
			
			//Verifying title
			String ActualText= manageagency.Title.getText().trim();		
			String ExpectedText= "Search Agencies";		
			Thread.sleep(1000);
			if(ActualText.equals(ExpectedText))
			{
				Thread.sleep(1000);
				System.out.println("Success !! Control is redirected to Manage Agency page. i.e. " + ActualText);
				Reporter.log("Success !! Control is redirected to Manage Agency page. i.e. " + ActualText); 
				
			}	
			else
			{
				
				System.out.println("Failure !! Control is NOT redirected to Manage Agency page. i.e." + "\n" + "Expected : "  
								+ "\n" + ExpectedText + "\n" +  "Actual : " + "\n" +  ActualText);
				Reporter.log("Failure !! Control is NOT redirected to Manage Agency page. i.e." + "\n" + "Expected : "  
					         	+ "\n" + ExpectedText + "\n" +  "Actual : " + "\n" +  ActualText);
				
				AssertFailedCount++;
			}
			System.out.println("Step 4 : Verify all the available agencies list is displayed");
			Reporter.log("Step 4 : Verify all the available agencies list is displayed");	
				
					
				Thread.sleep(1000);
				if(SeleniumFunc.IsElementPresent("css",".span12>h4>a"))
				{
					Thread.sleep(1000);
					System.out.println("Success !! All the available agencies list is displayed");
					Reporter.log("Success !! All the available agencies list is displayed"); 
					
				}	
				else
				{
					
					System.out.println("Success !! All the available agencies list is not displayed");
					Reporter.log("Success !! All the available agencies list is not displayed"); 
					
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
	 * Verify  Agencies are search able via the search page
	*/ 
	
	@Test
	private void AgenciesAreSearchable() throws Exception
	{
			System.out.println("====" + "\n" +	
						"Test 3 : Verify  Agencies are searchable via the search page"  + "\n" +
						"====");
			Reporter.log("====" + "\n" + 
						"Test 3 : Verify  Agencies are searchable via the search page"  + "\n" +	  
						"====");	
		
				int AssertFailedCount=0 ;
				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				LoginPage login = new LoginPage(driver);
				GlobalHeader header = new GlobalHeader(driver);
				ManageAgencyPage manageagency = new ManageAgencyPage(driver);
			
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
		
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);	
			
		System.out.println("Step 2 : Login to application and navigating to Manage Agency  page");
		Reporter.log("Step 2 : Login to application and navigating to Manage Agency  page"); 			
		
				login.EnterUsername(Constants.CM_Username);
				login.EnterPassword(Constants.CM_Password);
				login.ClickOnLogInButton();			
			
			    Thread.sleep(3000);
				header.AdminDropdown.click();
				Thread.sleep(1000);
				header.Admin_ManageAgency.click();
				Thread.sleep(3000);
			
		System.out.println("Step 3 : Verify control is redirected to Manage Users page");
		Reporter.log("Step 3 : Verify control is redirected to Manage Users page");	
			
				//Verifying title
				String ActualText= manageagency.Title.getText().trim();		
				String ExpectedText= "Search Agencies";		
	
				if(ActualText.equals(ExpectedText))
				{
					Thread.sleep(1000);
					System.out.println("Success !! Control is redirected to Manage Agency page. i.e. " + ActualText);
					Reporter.log("Success !! Control is redirected to Manage Agency page. i.e. " + ActualText); 
				
				}	
				else
				{
					Thread.sleep(1000);
					System.out.println("Failure !! Control is NOT redirected to Manage Agency page. i.e." + "\n" + "Expected : "  
																			+ "\n" + ExpectedText + "\n" + 	 "Actual : " + "\n" +  ActualText);
					Reporter.log("Failure !! Control is NOT redirected to Manage Agency page. i.e." + "\n" + "Expected : "  
																				+ "\n" + ExpectedText + "\n" +  "Actual : " + "\n" +  ActualText);
				
					AssertFailedCount++;
				}
					
		System.out.println("Step 4 : Enter 'Texas' in the 'Agency Name or Description' search bar. and verify The 'Texas Parks and Wildlife Department' agency is displayed.");
		Reporter.log("Step 4 : Enter 'Texas' in the 'Agency Name or Description' search bar. and verify The 'Texas Parks and Wildlife Department' agency is displayed.");	
			
				Thread.sleep(3000);
				manageagency.EnterAgencySearchText("texas");
				manageagency.ClickOnSearchButton();
			    Thread.sleep(4000);
			
				//Verify correct record is displayed
				String ActualText2 = SeleniumFunc.GetElementText("css", "li:nth-of-type(1) header h4 a");
				String ExpectedText2 = "TEXAS PARKS AND WILDLIFE DEPARTMENT";
			System.out.println(ActualText2);
				if(ActualText2.equals(ExpectedText2))
				{
				
					System.out.println("Success !! The 'Texas Parks and Wildlife Department' agency is displayed.");
					Reporter.log("Success !! The 'Texas Parks and Wildlife Department' agency is displayed."); 
				
				}	
				else
				{
				
					System.out.println("Failure !! The 'Texas Parks and Wildlife Department' agency is not displayed.");
					Reporter.log("Failure !! The 'Texas Parks and Wildlife Department' agency is not displayed.");
					AssertFailedCount++;
				
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
	 * Verify Clicking on an Agency's name in the search results page brings up the Agency's edit page
	*/ 
	@Test
	private void EditAgencyPage() throws Exception
	{
			System.out.println("====" + "\n" +
								"Test 4 : Verify Clicking on an Agency's name in the search results page brings up the Agency's edit page"  + "\n" +	"====");
			Reporter.log("====" + "\n" +
								"Test 4 : Verify Clicking on an Agency's name in the search results page brings up the Agency's edit page"  + "\n" + "====");	
		
				int AssertFailedCount=0 ;
				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				LoginPage login = new LoginPage(driver);
				GlobalHeader header = new GlobalHeader(driver);
				ManageAgencyPage manageagency = new ManageAgencyPage(driver);
			
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
		
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);	
			
		System.out.println("Step 2 : Login to application and navigating to Manage Agency  page");
		Reporter.log("Step 2 : Login to application and navigating to Manage Agency  page"); 			
		
				login.EnterUsername(Constants.CM_Username);
				login.EnterPassword(Constants.CM_Password);
				login.ClickOnLogInButton();	
				Thread.sleep(3000);
				header.AdminDropdown.click();
				header.Admin_ManageAgency.click();
				Thread.sleep(3000);
				
				System.out.println("Step 3 : Verify control is redirected to Manage Users page");
				Reporter.log("Step 3 : Verify control is redirected to Manage Users page");	
					
					//Verifying title
					String ActualText= manageagency.Title.getText().trim();		
					String ExpectedText= "Search Agencies";		
					Thread.sleep(1000);
					if(ActualText.equals(ExpectedText))
					{
						Thread.sleep(1000);
						System.out.println("Success !! Control is redirected to Manage Agency page. i.e. " + ActualText);
						Reporter.log("Success !! Control is redirected to Manage Agency page. i.e. " + ActualText); 
						
					}	
					else
					{
						Thread.sleep(1000);
						System.out.println("Failure !! Control is NOT redirected to Manage Agency page. i.e." + "\n" + "Expected : "  
										+ "\n" + ExpectedText + "\n" +  "Actual : " + "\n" +  ActualText);
						Reporter.log("Failure !! Control is NOT redirected to Manage Agency page. i.e." + "\n" + "Expected : "  
							         	+ "\n" + ExpectedText + "\n" +  "Actual : " + "\n" +  ActualText);
						
						AssertFailedCount++;
					}
					System.out.println("Step 4 : Verify all the available agencies list is displayed");
					Reporter.log("Step 4 : Verify all the available agencies list is displayed");	
						
							
						Thread.sleep(1000);
						if(SeleniumFunc.IsElementPresent("css",".span12>h4>a"))
						{
							Thread.sleep(1000);
							System.out.println("Success !! All the available agencies list is displayed");
							Reporter.log("Success !! All the available agencies list is displayed"); 
							
						}	
						else
						{
							
							System.out.println("Success !! All the available agencies list is not displayed");
							Reporter.log("Success !! All the available agencies list is not displayed"); 
							
							AssertFailedCount++;
						}

		System.out.println("Step 5 : Select the 'Kalkomey Enterprises, Inc.''�agency by clicking on its name. and Verify The 'Edit Agency:�Kalkomey Enterprises, Inc.' page�is displayed.");
		Reporter.log("Step 5 : Select the 'Kalkomey Enterprises, Inc.''�agency by clicking on its name. and Verify The 'Edit Agency:�Kalkomey Enterprises, Inc.' page�is displayed.");	
					
				
			
				//Put search criteria
				manageagency.EnterAgencySearchText("Texas Empty Agency");
				manageagency.ClickOnSearchButton();
			    Thread.sleep(3000);
			
				//go to searched record
				manageagency.FirstRecord.click();
			    Thread.sleep(2000);
			     String ActualText2= SeleniumFunc.GetElementText("css", ".page-header>h1").trim();
				  String ExpectedText2= "Edit Agency: Texas Empty Agency";		
	
				if(ActualText2.equals(ExpectedText2))
				{
				
					System.out.println("Success !! User is navigated to " + ActualText2);
					Reporter.log("Success !! User is navigated to " + ActualText2); 
				
				}	
				else
				{
				
						System.out.println("Failure !! User is not navigated to" + "\n" + "Expected : "  
																+ "\n" + ExpectedText2 + "\n" + "Actual : " + "\n" +  ActualText2);
						Reporter.log("Failure !! User is not navigated to" + "\n" + "Expected : "  
																+ "\n" + ExpectedText2 + "\n" +  "Actual : " + "\n" +  ActualText2);
				
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
	 * Verify An Agency's information can be edited from the Edit page; the agency name must always be populated
	*/ 
		@Test
		private void EditAgencyInformation() throws Exception
		{
				System.out.println("====" + "\n" +
						"Test 5 : Verify An Agency's information can be edited from the Edit page; the agency name must always be populated"  + "\n" +	"====");
				Reporter.log("====" + "\n" +
						"Test 5 : Verify An Agency's information can be edited from the Edit page; the agency name must always be populated"  + "\n" + "====");	
		
					int AssertFailedCount=0 ;
					SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
					LoginPage login = new LoginPage(driver);
					GlobalHeader header = new GlobalHeader(driver);
					ManageAgencyPage manageagency = new ManageAgencyPage(driver);
			
				System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
				Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
		
					SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);	
			
				System.out.println("Step 2 : Login to application and navigating to Manage Agency  page");
				Reporter.log("Step 2 : Login to application and navigating to Manage Agency  page"); 			
		
					login.EnterUsername(Constants.CM_Username);
					login.EnterPassword(Constants.CM_Password);
					login.ClickOnLogInButton();	
					Thread.sleep(2000);
					header.AdminDropdown.click();
					header.Admin_ManageAgency.click();
					Thread.sleep(3000);
					
			 System.out.println("Step 3 : Verify control is redirected to Manage Users page");
			 Reporter.log("Step 3 : Verify control is redirected to Manage Users page");	
			
					//Verifying title
					String ActualText= manageagency.Title.getText().trim();		
					String ExpectedText= "Search Agencies";		
					Thread.sleep(2000);
					if(ActualText.equals(ExpectedText))
					{
						Thread.sleep(2000);
						System.out.println("Success !! Control is redirected to Manage Agency page. i.e. " + ActualText);
						Reporter.log("Success !! Control is redirected to Manage Agency page. i.e. " + ActualText); 
				
					}	
					else
					{
						Thread.sleep(2000);
						System.out.println("Failure !! Control is NOT redirected to Manage Agency page. i.e." + "\n" + "Expected : "  
																		+ "\n" + ExpectedText + "\n" +  "Actual : " + "\n" +  ActualText);
						Reporter.log("Failure !! Control is NOT redirected to Manage Agency page. i.e." + "\n" + "Expected : "  
																		+ "\n" + ExpectedText + "\n" + "Actual : " + "\n" +  ActualText);
				
						AssertFailedCount++;	
					}
		
					System.out.println("Step 4 : Select the 'Texas Empty Agency''�agency by clicking on its name.,Verify The 'Texas Empty Agency' page�is displayed. and edit the details.");
					Reporter.log("Step 4 : Select the 'Texas Empty Agency''�agency by clicking on its name.,Verify The 'Texas Empty Agency' page�is displayed.and edit the details.");	
								
							manageagency.EnterAgencySearchText("Texas Empty Agency");
							manageagency.ClickOnSearchButton();
						    Thread.sleep(3000);
						
							//go to searched record
							manageagency.FirstRecord.click();
						    Thread.sleep(2000);
						     String ActualText2= SeleniumFunc.GetElementText("css", ".page-header>h1").trim();
							  String ExpectedText2= "Edit Agency: Texas Empty Agency";		
				
							if(ActualText2.equals(ExpectedText2))
							{
								Thread.sleep(2000);
								System.out.println("Success !! User is navigated to " + ActualText2);
								Reporter.log("Success !! User is navigated to " + ActualText2); 
							
							}	
							else
							{
								Thread.sleep(2000);
									System.out.println("Failure !! User is not navigated to" + "\n" + "Expected : "  
																			+ "\n" + ExpectedText2 + "\n" + "Actual : " + "\n" +  ActualText2);
									Reporter.log("Failure !! User is not navigated to" + "\n" + "Expected : "  
																			+ "\n" + ExpectedText2 + "\n" +  "Actual : " + "\n" +  ActualText2);
							
									AssertFailedCount++;
								}
			
								manageagency.EnterAgencyName("this is only a test");
								manageagency.EnterDescription("this is a description");
								manageagency.ClickOnSubmitButton();
								Thread.sleep(3000);
			
								//Verifying Success message
								String ActualText3= manageagency.Success.getText().trim();		
								String ExpectedText3= "Agency updated";		
	
								if(ActualText3.equals(ExpectedText3))
								{
									Thread.sleep(2000);
									System.out.println("Success !! Agency is updated successfuly. i.e. " + ActualText3);
									Reporter.log("Success !! Agency is updated successfuly. i.e. " + ActualText3); 
					
								}	
								else
								{
									Thread.sleep(2000);
							System.out.println("Failure !! Error while updating agency. i.e." + "\n" + "Expected : "  
																+ "\n" + ExpectedText3 + "\n" + "Actual : " + "\n" +  ActualText3);
							Reporter.log("Failure !! Error while updating agency. i.e." + "\n" + "Expected : "  
																+ "\n" + ExpectedText3 + "\n" +  "Actual : " + "\n" +  ActualText3);
				
							AssertFailedCount++;
					}
			
	
			
			System.out.println("Step 5 : Return to the /agencies search page and search for the updated agency.");
			Reporter.log("Step 5 : Return to the /agencies search page and search for the updated agency.");	
						
					header.AdminDropdown.click();
					header.Admin_ManageAgency.click();
					Thread.sleep(4000);
					manageagency.EnterAgencySearchText("this is only a test");
					manageagency.ClickOnSearchButton();
				    Thread.sleep(4000);
				    String ActualText4= SeleniumFunc.GetElementText("css", ".span12>h4>a");
					String ExpectedText4 = "this is only a test";
					 if(ActualText4.equalsIgnoreCase(ExpectedText4))
					{
						 Thread.sleep(2000);
						System.out.println("Success !! the updated agency name �is presented.. i.e. " + ActualText4);
						Reporter.log("Success !! the updated agency name�is presented.. i.e. " + ActualText4); 
					
					}	
					else
					{
						Thread.sleep(2000);
						System.out.println("Failure !! the updated agency name �is not presented. i.e." + "\n" + "Expected : "  
																						+ "\n" + ExpectedText4+ "\n" +  "Actual : " + "\n" +  ActualText4);
						Reporter.log("Failure !! the updated agency name �is not presented. i.e." + "\n" + "Expected : "  
																						+ "\n" + ExpectedText4 + "\n" +  "Actual : " + "\n" +  ActualText4);
					
						AssertFailedCount++;
					}
					
					Thread.sleep(4000);
				     String ActualText5= SeleniumFunc.GetElementText("css", ".agency-info>p ");
					String ExpectedText5 = "Description: this is a description";
					if(ActualText5.contains(ExpectedText5))
					{
						Thread.sleep(2000);
						System.out.println("Success !! Agency Description  is updated successfuly. i.e. " + ActualText5);
						Reporter.log("Success !! Agency Description is updated successfuly. i.e. " + ActualText5); 
					
					}	
					else
					{
						Thread.sleep(2000);
						System.out.println("Failure !! Error while updating agency Description. i.e." + "\n" + "Expected : "  
																							+ "\n" + ExpectedText5 + "\n" +  "Actual : " + "\n" +  ActualText5);
						Reporter.log("Failure !! Error while updating agency Description. i.e." + "\n" + "Expected : "  
																							+ "\n" + ExpectedText5 + "\n" +  "Actual : " + "\n" +  ActualText5);
					
						AssertFailedCount++;
					}
				
					// remove agency name and discription and verify error message
					
			System.out.println("Step 6 : Verify Validation Message for Empty Agency Name");
			Reporter.log("Step 6 : Verify Validation Message for Empty Agency Name");
			
					manageagency.FirstRecord.click();
					Thread.sleep(2000);
					manageagency.ClearAgencyName();
					//manageagency.ClearDescription();
					manageagency.ClickOnSubmitButton();
					Thread.sleep(2000);
					//Verify Error Message in header			
					
					String ActualText6= SeleniumFunc.GetElementText("xpath", "//div[3]/div/div/div[1]");	
					String ExpectedText6= "There was a problem creating this agency. Please correct the errors below and try again.";
		
					if(ActualText6.equals(ExpectedText6))
					{
						Thread.sleep(2000);
						System.out.println("Success !! Valid validation is displayed. i.e. " + ActualText6);
						Reporter.log("Success !! Valid validation is displayed. i.e. " + ActualText6); 
					
					}	
					else
					{
						Thread.sleep(2000);
							System.out.println("Failure !! Valid validation is not displayed. i.e." + "\n" + "Expected : "  
																	+ "\n" + ExpectedText6 + "\n" + "Actual : " + "\n" +  ActualText6);
							Reporter.log("Failure !! Valid validation is not displayed. i.e." + "\n" + "Expected : "  
																	+ "\n" + ExpectedText6 + "\n" +  "Actual : " + "\n" +  ActualText6);
					
							AssertFailedCount++;
						}
					
					//verify Can't blank Error Message					
					String ActualText7= SeleniumFunc.GetElementText("css", "#agency-form > div:nth-of-type(2) > div");	
					String ExpectedText7= "Please review the problems below:";
		
					if(ActualText7.equals(ExpectedText7))
					{
						Thread.sleep(2000);
			  			System.out.println("Success !! 'Agency name can't be blank'  is Displayed");
						Reporter.log("Success !!  'Agency name can't be blank'  is Displayed"); 
					}
					else
					{
						Thread.sleep(2000);
						System.out.println("Failure !! Agency mane can't be blank  is not Displayed ");
						Reporter.log("Failure !! Agency mane can't be blank  is  not Displayed "); 
						AssertFailedCount++;
					}
					
					Thread.sleep(3000);
					
			System.out.println("Step 7 : Repopulate the original values for the name and description, click Submit and verify successfull message");
			Reporter.log("Step 7 : Repopulate the original values for the name and description, click Submit and verify successfull message");
			
			manageagency.EnterAgencyName("Texas Empty Agency");
			manageagency.EnterDescription("An empty agency for testing purposes (created for https://kalkomey.tpondemand.com/entity/12558)");
			manageagency.ClickOnSubmitButton();
			Thread.sleep(3000);

			//Verifying Success message
			String ActualText8= manageagency.Success.getText().trim();		
			String ExpectedText8= "Agency updated";		

			if(ActualText8.equals(ExpectedText8))
			{
				Thread.sleep(2000);
				System.out.println("Success !! Agency is updated successfuly. i.e. " + ActualText8);
				Reporter.log("Success !! Agency is updated successfuly. i.e. " + ActualText8); 

			}	
			else
			{
				Thread.sleep(2000);
		System.out.println("Failure !! Error while updating agency. i.e." + "\n" + "Expected : "  
											+ "\n" + ExpectedText8 + "\n" + "Actual : " + "\n" +  ActualText8);
		Reporter.log("Failure !! Error while updating agency. i.e." + "\n" + "Expected : "  
											+ "\n" + ExpectedText8 + "\n" +  "Actual : " + "\n" +  ActualText8);

		AssertFailedCount++;
}
			
			System.out.println("Step 8 : Return to the /agencies search page and search for the updated agency.");
			Reporter.log("Step 8 : Return to the /agencies search page and search for the updated agency.");	
									
						header.AdminDropdown.click();
						header.Admin_ManageAgency.click();
						Thread.sleep(2000);
						//Put search criteria
						manageagency.EnterAgencySearchText("Texas Empty Agency");
						manageagency.ClickOnSearchButton();
							
						Thread.sleep(4000);
							 String ActualText9= SeleniumFunc.GetElementText("css", ".span12>h4>a");
							String ExpectedText9 = "Texas Empty Agency";
							 if(ActualText9.equalsIgnoreCase(ExpectedText9))
							{
								 Thread.sleep(2000);
								System.out.println("Success !! the updated agency name �is presented.. i.e. " + ActualText4);
								Reporter.log("Success !! the updated agency name�is presented.. i.e. " + ActualText4); 
							
							}	
							else
							{
								Thread.sleep(2000);
								System.out.println("Failure !! the updated agency name �is not presented. i.e." + "\n" + "Expected : "  
																								+ "\n" + ExpectedText4+ "\n" +  "Actual : " + "\n" +  ActualText4);
								Reporter.log("Failure !! the updated agency name �is not presented. i.e." + "\n" + "Expected : "  
																								+ "\n" + ExpectedText4 + "\n" +  "Actual : " + "\n" +  ActualText4);
							
								AssertFailedCount++;
							}
							
							
						     String ActualText10= SeleniumFunc.GetElementText("css", ".agency-info>p ");
							String ExpectedText10 = "Description: An empty agency for testing purposes";
							if(ActualText10.contains(ExpectedText10))
							{
								Thread.sleep(2000);
								System.out.println("Success !! Agency Description  is updated successfuly. i.e. " + ActualText10);
								Reporter.log("Success !! Agency Description is updated successfuly. i.e. " + ActualText10); 
							
							}	
							else
							{
								Thread.sleep(2000);
								System.out.println("Failure !! Error while updating agency Description. i.e." + "\n" + "Expected : "  
																									+ "\n" + ExpectedText10 + "\n" +  "Actual : " + "\n" +  ActualText10);
								Reporter.log("Failure !! Error while updating agency Description. i.e." + "\n" + "Expected : "  
																									+ "\n" + ExpectedText10 + "\n" +  "Actual : " + "\n" +  ActualText10);
							
								AssertFailedCount++;
							}
							
							manageagency.FirstRecord.click();
							Thread.sleep(2000);
							if(manageagency.IsAKpsSub.isSelected())
							{
								manageagency.ClickOnIsAKpsSub();
							}
							if(manageagency.AgecyAllowMultiple.isSelected())
							{
								manageagency.ClickOnAgecyAllowMultiple();
							}
							if(manageagency.UserCanEditAdd.isSelected())
							{
								manageagency.ClickOnUserCanEditAdd();
							}
							Thread.sleep(2000);
							manageagency.ClickOnSubmitButton();
							Thread.sleep(3000);
							//Verifying Success message
							if(ActualText8.equals(ExpectedText8))
							{
								Thread.sleep(2000);
								System.out.println("Success !! Agency is updated successfuly. i.e. " + ActualText8);
								Reporter.log("Success !! Agency is updated successfuly. i.e. " + ActualText8); 

							}	
							else
							{
								Thread.sleep(2000);
						System.out.println("Failure !! Error while updating agency. i.e." + "\n" + "Expected : "  
															+ "\n" + ExpectedText8 + "\n" + "Actual : " + "\n" +  ActualText8);
						Reporter.log("Failure !! Error while updating agency. i.e." + "\n" + "Expected : "  
															+ "\n" + ExpectedText8 + "\n" +  "Actual : " + "\n" +  ActualText8);

						AssertFailedCount++;
				           }
							Thread.sleep(2000);
							if(!manageagency.IsAKpsSub.isSelected())
							{
								System.out.println("Success !! 'Is a KPS Subscriber?' has unchecked successfully");
								Reporter.log("Success !! 'Is a KPS Subscriber?' has unchecked successfully"); 
							}
							else
							{
								System.out.println("Failure !! 'Is a KPS Subscriber?' is checked ");
								Reporter.log("Failure !! 'Is a KPS Subscriber?'  is checked "); 
							}
							if(!manageagency.AgecyAllowMultiple.isSelected())
							{
								System.out.println("Success !! 'Should multiple matching certification search results be returned?' has unchecked successfully");
								Reporter.log("Success !! 'Should multiple matching certification search results be returned?' has unchecked successfully"); 
							}
							else
							{
								System.out.println("Failure !! 'Should multiple matching certification search results be returned?' is checked ");
								Reporter.log("Failure !! 'Should multiple matching certification search results be returned?'  is checked "); 
							}
							
							if(!manageagency.UserCanEditAdd.isSelected())
							{
								System.out.println("Success !! 'Can users can edit their address in I Lost My Card?' has unchecked successfully");
								Reporter.log("Success !! 'Can users can edit their address in I Lost My Card?' has unchecked successfully"); 
							}
							else
							{
								System.out.println("Failure !! 'Can users can edit their address in I Lost My Card?' is checked ");
								Reporter.log("Failure !! 'Can users can edit their address in I Lost My Card?'  is checked "); 
							}
							
							Thread.sleep(2000);
							if(!manageagency.IsAKpsSub.isSelected())
							{
								manageagency.ClickOnIsAKpsSub();
							}
							if(!manageagency.AgecyAllowMultiple.isSelected())
							{
								manageagency.ClickOnAgecyAllowMultiple();
							}
							if(!manageagency.UserCanEditAdd.isSelected())
							{
								manageagency.ClickOnUserCanEditAdd();
							}
							
							manageagency.ClickOnSubmitButton();
							Thread.sleep(3000);
							//Verifying Success message
							if(ActualText8.equals(ExpectedText8))
							{
								Thread.sleep(2000);
								System.out.println("Success !! Agency is updated successfuly. i.e. " + ActualText8);
								Reporter.log("Success !! Agency is updated successfuly. i.e. " + ActualText8); 

							}	
							else
							{
								Thread.sleep(2000);
						System.out.println("Failure !! Error while updating agency. i.e." + "\n" + "Expected : "  
															+ "\n" + ExpectedText8 + "\n" + "Actual : " + "\n" +  ActualText8);
						Reporter.log("Failure !! Error while updating agency. i.e." + "\n" + "Expected : "  
															+ "\n" + ExpectedText8 + "\n" +  "Actual : " + "\n" +  ActualText8);

						AssertFailedCount++;
				           }
							
							if(manageagency.IsAKpsSub.isSelected())
							{
								System.out.println("Success !! 'Is a KPS Subscriber?' has checked successfully");
								Reporter.log("Success !! 'Is a KPS Subscriber?' has checked successfully"); 
							}
							else
							{
								System.out.println("Failure !! 'Is a KPS Subscriber?' is unchecked ");
								Reporter.log("Failure !! 'Is a KPS Subscriber?'  is unchecked "); 
							}
							if(manageagency.AgecyAllowMultiple.isSelected())
							{
								System.out.println("Success !! 'Should multiple matching certification search results be returned?' has checked successfully");
								Reporter.log("Success !! 'Should multiple matching certification search results be returned?' has checked successfully"); 
							}
							else
							{
								System.out.println("Failure !! 'Should multiple matching certification search results be returned?' is unchecked ");
								Reporter.log("Failure !! 'Should multiple matching certification search results be returned?'  is unchecked "); 
							}
							
							if(manageagency.UserCanEditAdd.isSelected())
							{
								System.out.println("Success !! 'Can users can edit their address in I Lost My Card?' has checked successfully");
								Reporter.log("Success !! 'Can users can edit their address in I Lost My Card?' has checked successfully"); 
							}
							else
							{
								System.out.println("Failure !! 'Can users can edit their address in I Lost My Card?' is unchecked ");
								Reporter.log("Failure !! 'Can users can edit their address in I Lost My Card?'  is unchecked "); 
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
		 * Agencies can be created; the name field is required
		 */ 	
		@Test
		private void CreateAgency_MandatoryField() throws Exception
		{
			System.out.println("====" + "\n" +
					"Test 6 : Agencies can be created; the name field is required"  + "\n" +	
					"====");
			Reporter.log("====" + "\n" + 
					"Test 6 : Agencies can be created; the name field is required"  + "\n" +  
						"====");	
		
				int AssertFailedCount=0 ;
				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				LoginPage login = new LoginPage(driver);
				GlobalHeader header = new GlobalHeader(driver);
				ManageAgencyPage manageagency = new ManageAgencyPage(driver);
			
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
		
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);	
			
		System.out.println("Step 2 : Login to application and navigating to Manage Agency  page");
		Reporter.log("Step 2 : Login to application and navigating to Manage Agency  page"); 			
		
				login.EnterUsername(Constants.CM_Username);
				login.EnterPassword(Constants.CM_Password);
				login.ClickOnLogInButton();			
				Thread.sleep(2000);
				header.AdminDropdown.click();
				Thread.sleep(1000);
				header.Admin_ManageAgency.click();
				Thread.sleep(2000);
				
		System.out.println("Step 3 : Verify control is redirected to Manage Users page");
		Reporter.log("Step 3 : Verify control is redirected to Manage Users page");	
			
				//Verifying title
				String ActualText= manageagency.Title.getText().trim();		
				String ExpectedText= "Search Agencies";		
				Thread.sleep(2000);
				if(ActualText.equals(ExpectedText))
				{
					Thread.sleep(2000);
					System.out.println("Success !! Control is redirected to Manage Agency page. i.e. " + ActualText);
					Reporter.log("Success !! Control is redirected to Manage Agency page. i.e. " + ActualText); 
				
				}	
				else
				{
					Thread.sleep(2000);
					System.out.println("Failure !! Control is NOT redirected to Manage Agency page. i.e." + "\n" + "Expected : "  
																											+ "\n" + ExpectedText + "\n" +  "Actual : " + "\n" +  ActualText);
					Reporter.log("Failure !! Control is NOT redirected to Manage Agency page. i.e." + "\n" + "Expected : "  
																											+ "\n" + ExpectedText + "\n" +  "Actual : " + "\n" +  ActualText);
				
					AssertFailedCount++;
				}
		
				Thread.sleep(2000);
				manageagency.ClickOnNewAgencyLink();
				Thread.sleep(2000);
				
				//Verifying title
				String ActualText1= SeleniumFunc.GetElementText("css", ".page-header>h1");	
				String ExpectedText1= "Create Agency";		
				Thread.sleep(2000);
				if(ActualText1.equals(ExpectedText1))
				{
					Thread.sleep(2000);
					System.out.println("Success !! Control is redirected to Create Agency page. i.e. " + ActualText);
					Reporter.log("Success !! Control is redirected to Create Agency page. i.e. " + ActualText); 
				
				}	
				else
				{
					Thread.sleep(2000);
					System.out.println("Failure !! Control is NOT redirected to Create Agency page. i.e." + "\n" + "Expected : "  
																											+ "\n" + ExpectedText + "\n" +  "Actual : " + "\n" +  ActualText);
					Reporter.log("Failure !! Control is NOT redirected to Create Agency page. i.e." + "\n" + "Expected : "  
																											+ "\n" + ExpectedText + "\n" +  "Actual : " + "\n" +  ActualText);
				
					AssertFailedCount++;
				}
				Thread.sleep(2000);
				manageagency.ClickOnSubmitButton();
				Thread.sleep(2000);
				//Verifying Error
				ActualText= SeleniumFunc.GetElementText("css", "#agency-form div:nth-of-type(2) div").trim();	
				ExpectedText= "Please review the problems below:";		
				Thread.sleep(2000);
				if(ActualText.equals(ExpectedText))
				{
					Thread.sleep(2000);
					System.out.println("Success !! Mandatory field validation is displayed. i.e. " + ActualText);
					Reporter.log("Success !! Mandatory field validation is displayed. i.e. " + ActualText); 
				
				}	
				else
				{
					Thread.sleep(2000);
					System.out.println("Failure !! Mandatory field validation is displayed. i.e." + "\n" + "Expected : "  
								+ "\n" + ExpectedText + "\n" +  "Actual : " + "\n" +  ActualText);
					Reporter.log("Failure !! Mandatory field validation is displayed. i.e." + "\n" + "Expected : "  
						+ "\n" + ExpectedText + "\n" + "Actual : " + "\n" +  ActualText);
				
					AssertFailedCount++;
				}
			
			
				//Verifying Error for Name field
				ActualText=SeleniumFunc.GetElementText("css", "#agency_name + span").trim();	
				ExpectedText= "can't be blank";		
				Thread.sleep(2000);
				if(ActualText.equals(ExpectedText))
				{
					Thread.sleep(2000);
					System.out.println("Success !! Mandatory field validation is displayed for Name field. i.e. " + ActualText);
					Reporter.log("Success !! Mandatory field validation is displayed for Name field. i.e. " + ActualText); 
				
				}	
				else
				{
					Thread.sleep(2000);
					System.out.println("Failure !! Mandatory field validation is NOT displayed for Name field. i.e." + "\n" + "Expected : "  
																																		+ "\n" + ExpectedText + "\n" +  "Actual : " + "\n" +  ActualText);
					Reporter.log("Failure !! Mandatory field validation is NOT displayed for Name field. i.e." + "\n" + "Expected : "  
																																		+ "\n" + ExpectedText + "\n" + "Actual : " + "\n" +  ActualText);
				
					AssertFailedCount++;
				}
				String agencyname= "Ke-testing" + JH.GenerateRandomNumber();
				String description = "Ke-testing"; 
				Thread.sleep(2000);
				System.out.println(agencyname);
				manageagency.EnterAgencyName(agencyname);
				manageagency.EnterDescription(description);
				Thread.sleep(2000);
				//click on check box 
				manageagency.ClickOnIsAKpsSub();
				manageagency.ClickOnAgecyAllowMultiple();
				manageagency.ClickOnUserCanEditAdd();	
				Thread.sleep(1000);
				manageagency.ClickOnSubmitButton();
			    Thread.sleep(4000);
			
				//Verifying Success message
				String ActualText2= manageagency.Success.getText().trim();		
				String ExpectedText2= "Agency created";		
				Thread.sleep(2000);
				if(ActualText2.equals(ExpectedText2))
				{
					Thread.sleep(2000);
					System.out.println("Success !! Agency is updated successfuly. i.e. " + ActualText2);
					Reporter.log("Success !! Agency is updated successfuly. i.e. " + ActualText2); 
				
				}	
				else
				{
					Thread.sleep(2000);
						System.out.println("Failure !! Error while updating agency. i.e." + "\n" + "Expected : "  
																+ "\n" + ExpectedText2 + "\n" + "Actual : " + "\n" +  ActualText2);
						Reporter.log("Failure !! Error while updating agency. i.e." + "\n" + "Expected : "  
																+ "\n" + ExpectedText2 + "\n" +  "Actual : " + "\n" +  ActualText2);
				
						AssertFailedCount++;
				}
				
			System.out.println("Step 4 : Search Agency and verify is correctly searched");
			Reporter.log("Step 4 : Search Agency and verify is correctly searched");	
							
			    Thread.sleep(3000);
				header.AdminDropdown.click();
				Thread.sleep(1000);
				header.Admin_ManageAgency.click();
				Thread.sleep(4000);
				
				manageagency.EnterAgencySearchText(agencyname);
				manageagency.ClickOnSearchButton();
				Thread.sleep(4000);
				
						
				//Verify correct record is displayed
				String ActualText4 = SeleniumFunc.GetElementText("css", "li:nth-of-type(1) header h4 a");
				String ExpectedText4 = agencyname;
		   	    System.out.println(ActualText4);
				if(ActualText4.equalsIgnoreCase(ExpectedText4))
				{
					Thread.sleep(2000);
					System.out.println("Success !! The "+agencyname+"' agency is displayed.");
					Reporter.log("Success !! The  "+agencyname+"'  agency is displayed."); 
				
				}	
				else
				{
					Thread.sleep(2000);
					System.out.println("Failure !! The  "+agencyname+"'  agency is not displayed.");
					Reporter.log("Failure !! The  "+agencyname+"' agency is not displayed.");
					AssertFailedCount++;
				
					AssertFailedCount++;
				}
				Thread.sleep(2000);
				manageagency.FirstRecord.click();
			    Thread.sleep(2000);
			     String ActualText5= SeleniumFunc.GetElementText("css", ".page-header>h1").trim();
				  String ExpectedText5= "Edit Agency: "+agencyname;		
				  Thread.sleep(2000);
				if(ActualText5.equals(ExpectedText5))
				{
					  Thread.sleep(2000);
					System.out.println("Success !! User is navigated to " + ActualText5);
					Reporter.log("Success !! User is navigated to " + ActualText5); 
				
				}	
				else
				{
					  Thread.sleep(2000);
						System.out.println("Failure !! User is not navigated to" + "\n" + "Expected : "  
																+ "\n" + ExpectedText5 + "\n" + "Actual : " + "\n" +  ActualText5);
						Reporter.log("Failure !! User is not navigated to" + "\n" + "Expected : "  
																+ "\n" + ExpectedText5 + "\n" +  "Actual : " + "\n" +  ActualText5);
				
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
	
		/* Test7: 
		 * Agency links are present on certificate  collection and users when logged in as agency admin
		*/ 
		@Test
		private void VerifygencyLink() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 7 : Verify Agency links are present on certificate  collection and users when logged in as agency admin"
						+ "\n"+	"====");
			Reporter.log("====" + "\n" +  
						"Test 7 : Verify Agency links are present on certificate  collection and users when logged in as agency admin"  
							+ "\n" +	  "====");	
			
					int AssertFailedCount=0 ;
					SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
					LoginPage login = new LoginPage(driver);
					GlobalHeader header = new GlobalHeader(driver);
					ManageAgencyPage manageagency = new ManageAgencyPage(driver);
					CreateNewUserPage  newuser=new CreateNewUserPage(driver);
					
			System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
			Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
					SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);	
				
			System.out.println("Step 2 : Login to application and navigating to Manage Agency  page");
			Reporter.log("Step 2 : Login to application and navigating to Manage Agency  page"); 			
			
					login.EnterUsername(Constants.CM_Username);
					login.EnterPassword(Constants.CM_Password);
					login.ClickOnLogInButton();			
					Thread.sleep(2000);
					
			System.out.println("Step 3 : Open the Admin menu,select Manage Collections. and verify Lists the Certification Collections as normal.");
			Reporter.log("Step 3 : Open the Admin menu,select Manage Collections. and verify Lists the Certification Collections as normal.");	
			
					header.AdminDropdown.click();
					Thread.sleep(1000);
					manageagency.ClickOnmanageCollectionlink();
					Thread.sleep(2000);
					int size=driver.findElements(By.cssSelector(".span12>h4>a")).size();
					Thread.sleep(2000);
		             if(size>1)
					{
		            	 Thread.sleep(2000);
						System.out.println("Success !! Lists the Certification Collections as normal   is present");
						Reporter.log("Success !! Lists the Certification Collections as normal   is present"); 
					}
					else
					{
						Thread.sleep(2000);
						System.out.println("Failure !! Lists the Certification Collections as normal  is NOT present ");
						Reporter.log("Failure !! Lists the Certification Collections as normal  is NOT present "); 
						AssertFailedCount++;
					}
				 
			System.out.println("Step 4 : Check whether the agency on each certification collection links to an agency page entry. and Each certification collection entry MUST link to an agency.");
			Reporter.log("Step 4 :  Check whether the agency on each certification collection links to an agency page entry. and Each certification collection entry MUST link to an agency. ");	
				
					Thread.sleep(2000);
					// click on agency link 
					manageagency.ClickOnFirstAgencylink();
					Thread.sleep(2000);
					//Verify  user  switch  to agency page
					if(SeleniumFunc.IsElementPresent(manageagency.AgencyPage))
					{			
						Thread.sleep(2000);
						System.out.println("Success !! Edit Agency  Name   is present");
						Reporter.log("Success !! Edit Agency  Name    is present"); 
					}
					else
					{
						Thread.sleep(2000);
						System.out.println("Failure !! Edit Agency  Name  is NOT present ");
						Reporter.log("Failure !! Edit Agency  Name   is NOT present "); 
						AssertFailedCount++;
					}
					
			System.out.println("Step 5 : Open the Admin menu and select Manage Users. and Lists the users as normal.");
			Reporter.log("Step 5 :  Open the Admin menu and select Manage Users. and Lists the users as normal.");	
				
					header.AdminDropdown.click();		
					Thread.sleep(1000);
					manageagency.ClickOnManageuserlink();				
					Thread.sleep(2000);
					 if(size>1)
					{
						System.out.println("Success !! Lists the users as normal   is present");
						Reporter.log("Success !! Lists the users as normal   is present"); 
					}
					else
					{
						Thread.sleep(2000);
						System.out.println("Failure !! Lists the users as normal  is NOT present ");
						Reporter.log("Failure !! Lists the users  is NOT present "); 
						AssertFailedCount++;
					}
				 
			System.out.println("Step 6 : Check whether the agency on each user links to an agency page entry. and Each user entry MUST link to an agency.");
			Reporter.log("Step 6 : Check whether the agency on each user links to an agency page entry. and Each user entry MUST link to an agency.");	
				
					Thread.sleep(2000);
					// click on agency link 
					manageagency.ClickOnFirstuserAgencyLink();
					Thread.sleep(2000);
					//Verify  user  switch  to agency page
					if(SeleniumFunc.IsElementPresent(manageagency.AgencyPage))
					{					
						System.out.println("Success !! Edit Agency  Name   is present");
						Reporter.log("Success !! Edit Agency  Name    is present"); 
					}
					else
					{
						Thread.sleep(2000);
						System.out.println("Failure !! Edit Agency  Name  is NOT present ");
						Reporter.log("Failure !! Edit Agency  Name   is NOT present "); 
						AssertFailedCount++;
					}
				
			
		System.out.println("Step 7 : Select manage user link from admin, search a user and Check whether the Agency listed on the profile page links to the corresponding Agency page.");
		Reporter.log("Step 7:  Select manage user link from admin, search a user and Check whether the Agency listed on the profile page links to the corresponding Agency page.");	
				
			Thread.sleep(2000);
		    newuser.clickonadmintab();
		    Thread.sleep(1000);
			newuser.ClickOnManageusers();
			Thread.sleep(2000);
			newuser.EnterUserinfo("test@clariontechnologies.co.in");
			newuser.ClcikOnSearch();
			Thread.sleep(2000);
				
			if(SeleniumFunc.IsElementPresent(manageagency.ProfileAgencyName))
			{					
				Thread.sleep(2000);
				System.out.println("Success !! Agency is present at profile page");
				Reporter.log("Success !! Agency is present at profile page"); 
			}
			else
			{
				Thread.sleep(2000);
				System.out.println("Failure !! Agency is not present at profile page");
				Reporter.log("Failure !! Agency is not present at profile page"); 
				AssertFailedCount++;
			}
					Thread.sleep(2000);
					manageagency.ProfileAgencyName.click();
					Thread.sleep(2000);
					//Verify user Edit Link Present or Not
					if(SeleniumFunc.IsElementPresent(manageagency.AgencyPage))
					{			
						Thread.sleep(2000);
						System.out.println("Success !! Edit Agency  Name   is present");
						Reporter.log("Success !! Edit Agency  Name    is present"); 
					}
					else
					{
						Thread.sleep(2000);
						System.out.println("Failure !! Edit Agency  Name  is NOT present ");
						Reporter.log("Failure !! Edit Agency  Name   is NOT present "); 
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
		
		/* Test 8:-
		 * Verify Agency links are NOT present on cert collections and users when not logged in as agency admin
		*/ 
			
		@Test
		private void VerifygencyLinkForNonKalUserk() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 8 : Verify Agency links are NOT present on cert collections and users when not logged in as agency admin"
						+ "\n"+	"====");
			Reporter.log("====" + "\n" +  
						"Test 8 : VerifyAgency links are NOT present on cert collections and users when not logged in as agency admin"  
						+ "\n" +	  "====");	
			
					int AssertFailedCount=0 ;
					SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
					LoginPage login = new LoginPage(driver);
					GlobalHeader header = new GlobalHeader(driver);
					RolesPermissionsPage role=new RolesPermissionsPage(driver);
					ManageAgencyPage manageagency = new ManageAgencyPage(driver);
					CreateNewUserPage  newuser=new CreateNewUserPage(driver);
					GlobalHeader gheader = new GlobalHeader(driver);  	 		
					ProfilePage profile = new ProfilePage(driver);
					
			System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
			Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
			
					SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);	
				
			System.out.println("Step 2 : Login to application and navigating to Manage Agency  page");
			Reporter.log("Step 2 : Login to application and navigating to Manage Agency  page"); 			
			
					login.EnterUsername(Constants.CM_User_NonAgencyAdmin);
					login.EnterPassword(Constants.CM_Pwd_NonAgencyAdmin);
					login.ClickOnLogInButton();			
					Thread.sleep(3000);	
					/*
					gheader.UsernameDropdown.click();
					Thread.sleep(1000);
					gheader.Username_MyProfile.click();
					Thread.sleep(1000);
					profile.EditInfo.click();
					Thread.sleep(1000);
			 		if(!role.ManageCollection.isSelected())
			 		{
			 			role.ClickOnEditCollections();
			 			//role.ClickOnCanManageAgencies();
			 		}
			 		Thread.sleep(1000);
			 		newuser.EnterUserPasswardtext("clarion@123");
			 		newuser.EnterConfirmPasswardtext("clarion@123");
			 		Thread.sleep(1000);
			 		newuser.ClickOnSubmitBtnButton();
			 		Thread.sleep(3000);*/
			 		
					System.out.println("Step 3 : Open the Admin menu,select Manage Collections. and verify Lists the Certification Collections as normal.");
					Reporter.log("Step 3 : Open the Admin menu,select Manage Collections. and verify Lists the Certification Collections as normal.");	
					
							header.AdminDropdown.click();
							Thread.sleep(1000);
							manageagency.ClickOnmanageCollectionlink();
							Thread.sleep(2000);
							int size=driver.findElements(By.cssSelector(".span12>h4>a")).size();
							Thread.sleep(2000);
				             if(size>1)
							{
				            	 Thread.sleep(2000);
								System.out.println("Success !! Lists the Certification Collections as normal   is present");
								Reporter.log("Success !! Lists the Certification Collections as normal   is present"); 
							}
							else
							{
								Thread.sleep(2000);
								System.out.println("Failure !! Lists the Certification Collections as normal  is NOT present ");
								Reporter.log("Failure !! Lists the Certification Collections as normal  is NOT present "); 
								AssertFailedCount++;
							}
						 
					System.out.println("Step 4 : Check whether the agency on each certification collection links to an agency page entry. and Each certification collection entry MUST not link to an agency.");
					Reporter.log("Step 4 :  Check whether the agency on each certification collection links to an agency page entry. and Each certification collection entry MUST not  link to an agency. ");	
						
							
							Thread.sleep(2000);
							//Verify  user  switch  to agency page
							String tagname=driver.findElement(By.cssSelector(".span6.certification-collection-info>p:nth-of-type(1)")).getTagName();
						//	System.out.println(tagname);
							if(!tagname.equals("a"))
							{			
								Thread.sleep(2000);
								System.out.println("Success !! Each certification collection entry MUST NOT link to an agency.");
								Reporter.log("Success !! Each certification collection entry MUST NOT link to an agency."); 
							}
							else
							{
								Thread.sleep(2000);
								System.out.println("Failure !! Each certification collection entry MUST  link to an agency.");
								Reporter.log("Failure !! Each certification collection entry MUST  link to an agency."); 
								AssertFailedCount++;
							}
							
					System.out.println("Step 5 : Open the Admin menu and select Manage Users. and Lists the users as normal.");
					Reporter.log("Step 5 :  Open the Admin menu and select Manage Users. and Lists the users as normal.");	
						
							header.AdminDropdown.click();		
							Thread.sleep(1000);
							manageagency.ClickOnManageuserlink();				
							Thread.sleep(2000);
							 if(size>1)
							{
								System.out.println("Success !! Lists the users as normal   is present");
								Reporter.log("Success !! Lists the users as normal   is present"); 
							}
							else
							{
								Thread.sleep(2000);
								System.out.println("Failure !! Lists the users as normal  is NOT present ");
								Reporter.log("Failure !! Lists the users  is NOT present "); 
								AssertFailedCount++;
							}
						 
					System.out.println("Step 6 : Check whether the agency on each user links to an agency page entry. and Each user entry MUST NOT link to an agency.");
					Reporter.log("Step 6 : Check whether the agency on each user links to an agency page entry. and Each user entry MUST NOT link to an agency.");	
						
						    Thread.sleep(2000);
							//Verify  user  switch  to agency page
						    String tagname1=driver.findElement(By.cssSelector(".span7.user-info>p")).getTagName();
						//	System.out.println(tagname1);
							if(!tagname1.equals("a"))
							{						
								System.out.println("Success !! Each user entry MUST NOT link to an agency.");
								Reporter.log("Success !! Each user entry MUST NOT link to an agency."); 
							}
							else
							{
								Thread.sleep(2000);
								System.out.println("Failure !! Each user entry MUST  link to an agency.");
								Reporter.log("Failure !! Each user entry MUST  link to an agency."); 
								AssertFailedCount++;
							}
						
					
				System.out.println("Step 7 : Click on an arbitrary user,,Check whether the Agency listed on the profile page links to the corresponding Agency page. and The agency listed MUST NOT link to the corresponding agency page.");
				Reporter.log("Step 7:  Click on an arbitrary user,,Check whether the Agency listed on the profile page links to the corresponding Agency page. and The agency listed MUST NOT link to the corresponding agency page.");	
						
					Thread.sleep(2000);
				    newuser.EnterUserinfo("test18@clariontechnologies.co.in");
					newuser.ClcikOnSearch();
					Thread.sleep(2000);
						
					 String tagname2=driver.findElement(By.cssSelector(".span7.user-info>p")).getTagName();
						//System.out.println(tagname2);
						if(!tagname2.equals("a"))
						{					
						Thread.sleep(2000);
						System.out.println("Success !! The agency listed MUST NOT link to the corresponding agency page.");
						Reporter.log("Success !! The agency listed MUST NOT link to the corresponding agency page."); 
					}
					else
					{
						Thread.sleep(2000);
						System.out.println("Failure !! The agency listed MUST  link to the corresponding agency page.");
						Reporter.log("Failure !! The agency listed MUST  link to the corresponding agency page."); 
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
		
		
		
	/* Test9: 
	 * Verify Forbid Kalkomey employees without the Agency Administrator role from manually going to the Agency CRUD
	*/ 
		@Test
		private void VerifyUrlSublink() throws Exception
		{
			System.out.println("====" + "\n" +
						"Test 9 : Verify Forbid Kalkomey employees without the Agency Administrator role from manually going to the Agency CRUD"
						+ "\n"+	"====");
			Reporter.log("====" + "\n" +  
						"Test 9 : Verify Forbid Kalkomey employees without the Agency Administrator role from manually going to the Agency CRUD"  
						+ "\n" +	  "====");	
			
			int AssertFailedCount=0 ;
			SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
			LoginPage login = new LoginPage(driver);
			GlobalHeader header = new GlobalHeader(driver);
			RolesPermissionsPage role=new RolesPermissionsPage(driver);
			ManageAgencyPage manageagency = new ManageAgencyPage(driver);
			CreateNewUserPage  newuser=new CreateNewUserPage(driver);
			GlobalHeader gheader = new GlobalHeader(driver);  	 		
			ProfilePage profile = new ProfilePage(driver);
			
	System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
	Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
	
			SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);	
		
	System.out.println("Step 2 : Login to application and navigating to Manage Agency  page");
	Reporter.log("Step 2 : Login to application and navigating to Manage Agency  page"); 			
	
			login.EnterUsername(Constants.CM_User_NonAdminCM);
			login.EnterPassword(Constants.CM_Pwd_NonAdminCM);
			login.ClickOnLogInButton();			
			Thread.sleep(3000);	
			
			gheader.UsernameDropdown_NonAdmin.click();
			Thread.sleep(1000);
			gheader.Username_MyProfile_NonAdmin.click();
			Thread.sleep(1000);
			profile.EditInfo.click();
			Thread.sleep(1000);
	 		if(role.CanManageAgencies.isSelected())
	 		{
	 			role.ClickOnCanManageAgencies();
	 		}
	 		Thread.sleep(1000);
	 		newuser.EnterUserPasswardtext("clarion@123");
	 		newuser.EnterConfirmPasswardtext("clarion@123");
	 		Thread.sleep(1000);
	 		newuser.ClickOnSubmitBtnButton();
	 		Thread.sleep(3000);
				
			System.out.println("Step 3 : Verify The 'Manage Agencies' link is not  be visible.");
			Reporter.log("Step 3 : Verify The 'Manage Agencies' link is not  be visible.");	
			
			header.AdminDropdown.click();		
			Thread.sleep(1000);
			if(!SeleniumFunc.IsElementPresent(manageagency.ManageAgencyLink))
			{
				Thread.sleep(1000);
				System.out.println("Success !! The 'Manage Agencies' link is not visible.");
				Reporter.log("Success !!  The 'Manage Agencies' link is not visible."); 
			}
			else
			{
				Thread.sleep(1000);
				System.out.println("Failure !!  The 'Manage Agencies' link is  visible. ");
				Reporter.log("Failure !! The 'Manage Agencies' link is  visible."); 
				AssertFailedCount++;
			}	
			
			  
			System.out.println("Step 4 : Verify  Denied  message for unauthorized '/agencies','/agencies/new','/agencies/<id>'");
			Reporter.log("Step 4 : Verify  Denied  message for unauthorized '/agencies','/agencies/new','/agencies/<id>'");	
			
			    Thread.sleep(1000);
			    SeleniumFunc.ToGoToUrl("https://kps.staging.kalkomey.com/agencies/");
			    Thread.sleep(1000);
				String error=SeleniumFunc.GetElementText("css", ".dialog>h1");
				Thread.sleep(1000);
				if(error.equals("You are not authorized to look at this page."))
				{   
					Thread.sleep(1000);
					System.out.println("Success !! 'You are not authorized to look at this page.' message is displayed.");
					Reporter.log("Success !! 'You are not authorized to look at this page.' message is displayed."); 
	  	  	}
				else
				{
					Thread.sleep(1000);
					System.out.println("Failure !! 'You are not authorized to look at this page.' message is not displayed.");
					Reporter.log("Failure !! 'You are not authorized to look at this page.' message is not displayed."); 
					AssertFailedCount++;
				}
				
					Thread.sleep(1000);
					SeleniumFunc.ToGoToUrl("https://kps.staging.kalkomey.com/agencies/new");
					Thread.sleep(1000);
					if(error.equals("You are not authorized to look at this page."))
					{   
						Thread.sleep(1000);
						System.out.println("Success !! 'You are not authorized to look at this page.' message is displayed.");
						Reporter.log("Success !! 'You are not authorized to look at this page.' message is displayed."); 
		  	     	}
					else
					{
						Thread.sleep(1000);
						System.out.println("Failure !! 'You are not authorized to look at this page.' message is not displayed.");
						Reporter.log("Failure !! 'You are not authorized to look at this page.' message is not displayed."); 
						AssertFailedCount++;
					}
						Thread.sleep(1000);
						SeleniumFunc.ToGoToUrl("https://kps.staging.kalkomey.com/agencies/72");
					 	Thread.sleep(1000);
						if(error.equals("You are not authorized to look at this page."))
						{   
							Thread.sleep(1000);
							System.out.println("Success !! 'You are not authorized to look at this page.' message is displayed.");
							Reporter.log("Success !! 'You are not authorized to look at this page.' message is displayed."); 
			  	     	}
						else
						{
							Thread.sleep(1000);
							System.out.println("Failure !! 'You are not authorized to look at this page.' message is not displayed.");
							Reporter.log("Failure !! 'You are not authorized to look at this page.' message is not displayed."); 
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
		
	
			/* Test10: 
			 * The agency search and edit pages should not be visible to non-Kalkomey employees, even when given the "Can manage agencies" permission
			 */ 
			@Test
			private void VerifyNonKaluser() throws Exception
			{
				System.out.println("====" + "\n" +
							"Test 10 : The agency search and edit pages should not be visible to non-Kalkomey employees, even when given the 'Can manage agencies' permission"
							+ "\n"+	"====");
				Reporter.log("====" + "\n" + 
							"Test 10 : The agency search and edit pages should not be visible to non-Kalkomey employees, even when given the 'Can manage agencies' permission"  
								+ "\n" +	  "====");	
				
				int AssertFailedCount=0 ;
				SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
				LoginPage login = new LoginPage(driver);
				GlobalHeader header = new GlobalHeader(driver);
				RolesPermissionsPage role=new RolesPermissionsPage(driver);
				ManageAgencyPage manageagency = new ManageAgencyPage(driver);
				CreateNewUserPage  newuser=new CreateNewUserPage(driver);
				GlobalHeader gheader = new GlobalHeader(driver);  	 		
				ProfilePage profile = new ProfilePage(driver);
				
		System.out.println("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login");
		Reporter.log("Step 1 : Navigate to Login page : " + Constants.ApplicationURL_CM + "/login"); 
		
				SeleniumFunc.ToGoToUrl(Constants.LoginPage_CM);	
			
		System.out.println("Step 2 : Login to application and navigating to Manage Agency  page");
		Reporter.log("Step 2 : Login to application and navigating to Manage Agency  page"); 			
		
				/*login.EnterUsername(Constants.CM_Username_AgencyImport);
				login.EnterPassword(Constants.CM_Password_AgencyImport);*/
				
				login.EnterUsername(Constants.CM_Username_Normal3);
				login.EnterPassword(Constants.CM_Password_Normal3);
		
				
				login.ClickOnLogInButton();			
				Thread.sleep(3000);	
				gheader.UsernameDropdown.click();
				Thread.sleep(1000);
				gheader.Username_MyProfile.click();
				Thread.sleep(1000);
				profile.EditInfo.click();
				Thread.sleep(1000);
		 		if(!role.CanManageAgencies.isSelected())
		 		{
		 			role.ClickOnCanManageAgencies();
		 		}
		 		Thread.sleep(1000);
		 		newuser.EnterUserPasswardtext("clarion@123");
		 		newuser.EnterConfirmPasswardtext("clarion@123");
		 		Thread.sleep(1000);
		 		newuser.ClickOnSubmitBtnButton();
		 		Thread.sleep(3000);
					
				System.out.println("Step 3 : Verify The 'Manage Agencies' link is not  be visible.");
				Reporter.log("Step 3 : Verify The 'Manage Agencies' link is not  be visible.");	
				
				header.AdminDropdown.click();		
				Thread.sleep(1000);
				if(!SeleniumFunc.IsElementPresent(manageagency.ManageAgencyLink))
				{
					Thread.sleep(1000);
					System.out.println("Success !! The 'Manage Agencies' link is not visible.");
					Reporter.log("Success !!  The 'Manage Agencies' link is not visible."); 
				}
				else
				{
					Thread.sleep(1000);
					System.out.println("Failure !!  The 'Manage Agencies' link is  visible. ");
					Reporter.log("Failure !! The 'Manage Agencies' link is  visible."); 
					AssertFailedCount++;
				}	
				
				  
				System.out.println("Step 4 : Verify  Denied  message for unauthorized '/agencies','/agencies/new','/agencies/<id>'");
				Reporter.log("Step 4 : Verify  Denied  message for unauthorized '/agencies','/agencies/new','/agencies/<id>'");	
				
				    Thread.sleep(1000);
				    SeleniumFunc.ToGoToUrl("https://kps.staging.kalkomey.com/agencies/");
				    Thread.sleep(1000);
					String error=SeleniumFunc.GetElementText("css", ".dialog>h1");
					Thread.sleep(1000);
					if(error.equals("You are not authorized to look at this page."))
					{   
						Thread.sleep(1000);
						System.out.println("Success !! 'You are not authorized to look at this page.' message is displayed.");
						Reporter.log("Success !! 'You are not authorized to look at this page.' message is displayed."); 
		  	  	}
					else
					{
						Thread.sleep(1000);
						System.out.println("Failure !! 'You are not authorized to look at this page.' message is not displayed.");
						Reporter.log("Failure !! 'You are not authorized to look at this page.' message is not displayed."); 
						AssertFailedCount++;
					}
					
						Thread.sleep(1000);
						SeleniumFunc.ToGoToUrl("https://kps.staging.kalkomey.com/agencies/new");
						Thread.sleep(1000);
						if(error.equals("You are not authorized to look at this page."))
						{   
							Thread.sleep(1000);
							System.out.println("Success !! 'You are not authorized to look at this page.' message is displayed.");
							Reporter.log("Success !! 'You are not authorized to look at this page.' message is displayed."); 
			  	     	}
						else
						{
							Thread.sleep(1000);
							System.out.println("Failure !! 'You are not authorized to look at this page.' message is not displayed.");
							Reporter.log("Failure !! 'You are not authorized to look at this page.' message is not displayed."); 
							AssertFailedCount++;
						}
							Thread.sleep(1000);
							SeleniumFunc.ToGoToUrl("https://kps.staging.kalkomey.com/agencies/72");
						 	Thread.sleep(1000);
							if(error.equals("You are not authorized to look at this page."))
							{   
								Thread.sleep(1000);
								System.out.println("Success !! 'You are not authorized to look at this page.' message is displayed.");
								Reporter.log("Success !! 'You are not authorized to look at this page.' message is displayed."); 
				  	     	}
							else
							{
								Thread.sleep(1000);
								System.out.println("Failure !! 'You are not authorized to look at this page.' message is not displayed.");
								Reporter.log("Failure !! 'You are not authorized to look at this page.' message is not displayed."); 
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
		
		
		
}

//completed 

	
		
	
	