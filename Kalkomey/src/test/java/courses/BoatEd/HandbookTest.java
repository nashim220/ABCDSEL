package courses.BoatEd;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageFactory.Courses.HandbookPage;
import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;
import utility.SeleniumFunctions;

public class HandbookTest 
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
	 * Verify landing pages for Boat-ed Maine state 
	*/ 
	@Test
	private void Handbook_MaineLandingPage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify landing pages for Boat-ed Maine state "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify landing pages for Boat-ed Maine state "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HandbookPage handbook = new HandbookPage(driver);
	
		System.out.println("Step 1 : Navigate to  Boat-ed Maine state page : " + Constants.ApplicationURL + "/maine");
		Reporter.log("Step 1 : Navigate to  Boat-ed Maine state page : " + Constants.ApplicationURL + "/maine"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/maine/handbook/");
	
		System.out.println("Step 2 : Verifying whether user is redirected to correct page or not");
		Reporter.log("Step 2 : Verifying whether user is redirected to correct page or not"); 
			
			Thread.sleep(6000);
			String expected = Constants.ApplicationURL + "/maine/handbook/";
			String actual = driver.getCurrentUrl();
			if(actual.equals(expected))
			{
				System.out.println("Success !! user is redirected to : " + actual);
				Reporter.log("Success !! user is redirected to : " + actual);
				
				
				System.out.println("Step 3 : Verifying presence of UI elements");
				Reporter.log("Step 3 : Verifying presence of UI elements"); 
					
						//Verifying Application Name
						expected = "The Official Maine Boat Safety Handbook";
						actual = handbook.ApplicationName.getText();
						if(actual.contains(expected))
						{
							System.out.println("Success !!  Application Name is correct i.e. " + actual);
							Reporter.log("Success !!  Application Name is correct i.e. " + actual);
						}
						else
						{
							System.out.println("Failure !!  Application Name is incorrect i.e. " + actual);
							Reporter.log("Failure !! Application Name is incorrect i.e. " + actual );
							AssertFailedCount++;
						}
						
						//Verifying Page header & sub text
						expected = "Stay up to date with ME laws governing boating and safe operation with the boating handbook "
								+ "of the Maine Department of Natural Resources.";
						actual = handbook.PageHeader_SubText.getText();
						if(actual.equals(expected))
						{
							System.out.println("Success !!  Page header & sub text is correct i.e. " + actual);
							Reporter.log("Success !!  Page header & sub text is correct i.e. " + actual);
						}
						else
						{
							System.out.println("Failure !!  Page header & sub text is incorrect i.e.\n actual :" + actual+"\n expected :"+expected);
							Reporter.log("Failure !! Page header & sub text is incorrect i.e.\n actual :" + actual+"\n expected :"+expected);
							AssertFailedCount++;
						}
				
					/*	// Verifying presence of 'Buy the eBook' button
						expected = "Buy at Our Bookstore";
						try
						{
							actual = handbook.BuyTheEbook.getText();
							if(actual.equals(expected))
							{
								System.out.println("Success !! 'Buy the eBook' button is present ");
								Reporter.log("Success !!  'Buy the eBook' button is present");
							}	
							else
							{
								System.out.println("Failure !!  'Buy the eBook' button is NOT present");
								Reporter.log("Failure !!  'Buy the eBook' button is NOT present");
								AssertFailedCount++;
							}
						}
						catch(Exception e)
						{
							System.out.println("Failure !!  'Buy the eBook' button is NOT present");
							Reporter.log("Failure !!  'Buy the eBook' button is NOT present");
							AssertFailedCount++;
						}*/
						
				
						// Verifying whether handbook format info is displayed
						expected = "This Maine boating handbook is also available in free web and PDF versions.";
						actual = handbook.HandbookFormatInfo.getText().trim() ;
						
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  handbook format info is correct i.e. " + actual);
							Reporter.log("Success !! handbook format info is correct i.e. " + actual);
						}
						else
						{
							System.out.println("Failure !!  handbook format info is incorrect i.e. " + actual);
							Reporter.log("Failure !! handbook format info is incorrect i.e. " + actual );
							AssertFailedCount++;
						}
				
				
					/*	// Verifying presence of 'The Boater's Guide to Maine Boating Laws and Responsibilities' section
				
						expected = "The Boater's Guide to Maine Boating Laws and Responsibilities";
						actual = handbook.BoatersGuideHeader.getText();
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  'The Boater's Guide to Maine Boating Laws and Responsibilities' section is present");
							Reporter.log("Success !!  'The Boater's Guide to Maine Boating Laws and Responsibilities' section is present");
						}
						else
						{
							System.out.println("Failure !!  'The Boater's Guide to Maine Boating Laws and Responsibilities' section is NOT present");
							Reporter.log("Failure !!  'The Boater's Guide to Maine Boating Laws and Responsibilities' section is NOT present");
							AssertFailedCount++;
						}
				
				
						// Verifying presence of 'Get it on your Phone, eReader, and Desktop' section
						expected = "Get it on your Phone, eReader, and Desktop";
						actual = handbook.GetItHeader.getText();
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  'Get it on your Phone, eReader, and Desktop' section is present");
							Reporter.log("Success !!  'Get it on your Phone, eReader, and Desktop' section is present");
						}
						else
						{
							System.out.println("Failure !!  'Get it on your Phone, eReader, and Desktop' section is NOT present");
							Reporter.log("Failure !!  'Get it on your Phone, eReader, and Desktop' section is NOT present");
							AssertFailedCount++;
						}
						
				
						// Verifying iBook Version
						expected = "iBook Version" + "Take the The Boater's Guide to Maine Boating Laws and Responsibilities with you on your iPhone, iPod Touch, or iPad.";
						actual = handbook.iBookVesionHeader.getText() + handbook.iBookVesionInfoText.getText() ;
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  'iBook Version' section is present");
							Reporter.log("Success !!  'iBook Version' section is present");
						}
						else
						{
							System.out.println("Failure !!  'iBook Version' section is NOT present");
							Reporter.log("Failure !!  'iBook Version' section is NOT present");
							AssertFailedCount++;
						}
						
				
						// Verifying Nook Version
						expected = "Nook Version" + "Start the handbook on your NOOK, and continue reading on the go on your smartphone or computer.";
						actual = handbook.NookVesionHeader.getText() + handbook.NookVesionInfoText.getText() ;
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  'Nook Version' section is present");
							Reporter.log("Success !!  'Nook Version' section is present");
						}
						else
						{
							System.out.println("Failure !!  'Nook Version' section is NOT present");
							Reporter.log("Failure !!  'Nook Version' section is NOT present");
							AssertFailedCount++;
						}
						
				
						// Verifying Free Version
						expected = "Free Version" + "The ME boating handbook is also available in free web and PDF versions.";
						actual = handbook.FreeVesionHeader.getText() + handbook.FreeVesionInfoText.getText() ;
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  'Free Version' section is present");
							Reporter.log("Success !!  'Free Version' section is present");
						}
						else
						{
							System.out.println("Failure !!  'Free Version' section is NOT present");
							Reporter.log("Failure !!  'Free Version' section is NOT present");
							AssertFailedCount++;
						}*/
				
			}
			else
			{
				System.out.println("Failure !!  user is redirected to " + actual + "  instead of " + expected );
				Reporter.log("Failure !!  user is redirected to " + actual + "  instead of " + expected );
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
	 * Verify landing pages for Boat-ed Massachusetts  state 
	*/ 
	@Test
	private void Handbook_MassachusettsLandingPage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify landing pages for Boat-ed Massachusetts state "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify landing pages for Boat-ed Massachusetts state "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HandbookPage handbook = new HandbookPage(driver);
	
		System.out.println("Step 1 : Navigate to  Boat-ed Massachusetts state page : " + Constants.ApplicationURL + "/massachusetts");
		Reporter.log("Step 1 : Navigate to  Boat-ed Massachusetts state page : " + Constants.ApplicationURL + "/massachusetts"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/massachusetts/handbook/");
	
		System.out.println("Step 2 : Verifying whether user is redirected to correct page or not");
		Reporter.log("Step 2 : Verifying whether user is redirected to correct page or not"); 
			
			Thread.sleep(6000);
			String expected = Constants.ApplicationURL + "/massachusetts/handbook/";
			String actual = driver.getCurrentUrl();
			if(actual.equals(expected))
			{
				System.out.println("Success !! user is redirected to : " + actual);
				Reporter.log("Success !! user is redirected to : " + actual);
				
				
				System.out.println("Step 3 : Verifying presence of UI elements");
				Reporter.log("Step 3 : Verifying presence of UI elements"); 
					
						//Verifying Application Name
						expected = "The Official Massachusetts Boat Safety Handbook";
						actual = handbook.ApplicationName.getText();
						if(actual.contains(expected))
						{
							System.out.println("Success !!  Application Name is correct i.e. " + actual);
							Reporter.log("Success !!  Application Name is correct i.e. " + actual);
						}
						else
						{
							System.out.println("Failure !!  Application Name is incorrect i.e. " + actual);
							Reporter.log("Failure !! Application Name is incorrect i.e. " + actual );
							AssertFailedCount++;
						}
						
						//Verifying Page header & sub text
						expected = "Stay up to date with MA laws governing boating and safe operation with the boating handbook"
								+ " of the Massachusetts Department of Natural Resources.";
						actual = handbook.PageHeader_SubText.getText();
						if(actual.equals(expected))
						{
							System.out.println("Success !!  Page header & sub text is correct i.e. " + actual);
							Reporter.log("Success !!  Page header & sub text is correct i.e. " + actual);
						}
						else
						{
							System.out.println("Failure !!  Page header & sub text is incorrect i.e.\nActual: : " + actual+"\nExpected:"+expected);
							Reporter.log("Failure !! Page header & sub text is incorrect i.e.\nActual: : " + actual+"\nExpected:"+expected);
							AssertFailedCount++;
						}
				
				/*		// Verifying presence of 'Buy the eBook' button
						expected = "Buy at Our Bookstore";
						try
						{
							actual = handbook.BuyTheEbook.getText();
							if(actual.equals(expected))
							{
								System.out.println("Success !! 'Buy the eBook' button is present ");
								Reporter.log("Success !!  'Buy the eBook' button is present");
							}	
							else
							{
								System.out.println("Failure !!  'Buy the eBook' button is NOT present");
								Reporter.log("Failure !!  'Buy the eBook' button is NOT present");
								AssertFailedCount++;
							}
						}
						catch(Exception e)
						{
							System.out.println("Failure !!  'Buy the eBook' button is NOT present");
							Reporter.log("Failure !!  'Buy the eBook' button is NOT present");
							AssertFailedCount++;
						}*/
						
				
						// Verifying whether handbook format info is displayed
						expected = "This Massachusetts boating handbook is also available in free web and PDF versions.";
						actual = handbook.HandbookFormatInfo.getText().trim() ;
						
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  handbook format info is correct i.e. " + actual);
							Reporter.log("Success !! handbook format info is correct i.e. " + actual);
						}
						else
						{
							System.out.println("Failure !!  handbook format info is incorrect i.e. " + actual);
							Reporter.log("Failure !! handbook format info is incorrect i.e. " + actual );
							AssertFailedCount++;
						}
				
				
					/*	// Verifying presence of 'The Boater's Guide to Maine Boating Laws and Responsibilities' section
				
						expected = "Boat Massachusetts - Your Guide to Boating Laws and Responsibilities";
						actual = handbook.BoatersGuideHeader.getText();
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  'The Boater's Guide to Maine Boating Laws and Responsibilities' section is present");
							Reporter.log("Success !!  'The Boater's Guide to Maine Boating Laws and Responsibilities' section is present");
						}
						else
						{
							System.out.println("Failure !!  'The Boater's Guide to Maine Boating Laws and Responsibilities' section is NOT present");
							Reporter.log("Failure !!  'The Boater's Guide to Maine Boating Laws and Responsibilities' section is NOT present");
							AssertFailedCount++;
						}
				
				
						// Verifying presence of 'Get it on your Phone, eReader, and Desktop' section
						expected = "Get it on your Phone, eReader, and Desktop";
						actual = handbook.GetItHeader.getText();
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  'Get it on your Phone, eReader, and Desktop' section is present");
							Reporter.log("Success !!  'Get it on your Phone, eReader, and Desktop' section is present");
						}
						else
						{
							System.out.println("Failure !!  'Get it on your Phone, eReader, and Desktop' section is NOT present");
							Reporter.log("Failure !!  'Get it on your Phone, eReader, and Desktop' section is NOT present");
							AssertFailedCount++;
						}
						
				
						// Verifying iBook Version
						expected = "iBook Version" + "Take the Boat Massachusetts - Your Guide to Boating Laws and Responsibilities with you on your iPhone, iPod Touch, or iPad.";
						actual = handbook.iBookVesionHeader.getText() + handbook.iBookVesionInfoText.getText() ;
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  'iBook Version' section is present");
							Reporter.log("Success !!  'iBook Version' section is present");
						}
						else
						{
							System.out.println("Failure !!  'iBook Version' section is NOT present");
							Reporter.log("Failure !!  'iBook Version' section is NOT present");
							AssertFailedCount++;
						}
						
				
						// Verifying Nook Version
						expected = "Nook Version" + "Start the handbook on your NOOK, and continue reading on the go on your smartphone or computer.";
						actual = handbook.NookVesionHeader.getText() + handbook.NookVesionInfoText.getText() ;
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  'Nook Version' section is present");
							Reporter.log("Success !!  'Nook Version' section is present");
						}
						else
						{
							System.out.println("Failure !!  'Nook Version' section is NOT present");
							Reporter.log("Failure !!  'Nook Version' section is NOT present");
							AssertFailedCount++;
						}
						
				
						// Verifying Free Version
						expected = "Free Version" + "The MA boating handbook is also available in free web and PDF versions.";
						actual = handbook.FreeVesionHeader.getText() + handbook.FreeVesionInfoText.getText() ;
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  'Free Version' section is present");
							Reporter.log("Success !!  'Free Version' section is present");
						}
						else
						{
							System.out.println("Failure !!  'Free Version' section is NOT present");
							Reporter.log("Failure !!  'Free Version' section is NOT present");
							AssertFailedCount++;
						}
				*/
			}
			else
			{
				System.out.println("Failure !!  user is redirected to " + actual + "  instead of " + expected );
				Reporter.log("Failure !!  user is redirected to " + actual + "  instead of " + expected );
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
	
	
/*	 Test 3: 
	 * Verify three formats are present for handbook for Boat-ed Maine state 
	 
	@Test
	private void Handbook_Maine_HandbookVersions() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Verify three formats are present for handbook for Boat-ed Maine state "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Verify three formats are present for handbook for Boat-ed Maine state "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HandbookPage handbook = new HandbookPage(driver);
	
		System.out.println("Step 1 : Navigate to  Boat-ed Maine state page : " + Constants.ApplicationURL + "/maine");
		Reporter.log("Step 1 : Navigate to  Boat-ed Maine state page : " + Constants.ApplicationURL + "/maine"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/maine/handbook/");
	
		System.out.println("Step 2 : Verifying whether user is redirected to correct page or not");
		Reporter.log("Step 2 : Verifying whether user is redirected to correct page or not"); 
			
			Thread.sleep(6000);
			String expected = Constants.ApplicationURL + "/maine/handbook/";
			String actual = driver.getCurrentUrl();
			if(actual.equals(expected))
			{
				System.out.println("Success !! user is redirected to : " + actual);
				Reporter.log("Success !! user is redirected to : " + actual);
				
				
				System.out.println("Step 3 : Under 'Buy the eBook' button , verifying whether formats info displayed & links are working as expected. ");
				Reporter.log("Step 3 : Under 'Buy the eBook' button , verifying whether formats info displayed & links are working as expected. ");
				
						
						// Verifying whether handbook format info is displayed
						expected = "Also available in these formats:" + "\n" + 
									"Apple iBook, B&N Nook Book, Web Version";
						actual = handbook.HandbookFormatInfo.getText().trim() ;
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  handbook format info is correct i.e. " + actual);
							Reporter.log("Success !! handbook format info is correct i.e. " + actual);
						}
						else
						{
							System.out.println("Failure !!  handbook format info is incorrect i.e. " + actual);
							Reporter.log("Failure !! handbook format info is incorrect i.e. " + actual );
							AssertFailedCount++;
						}
				
				System.out.println("Step 4 : Under 'Buy the eBook' button , clicking on 'Apple iBook' link ");
				Reporter.log("Step 4 : Under 'Buy the eBook' button , clicking on 'Apple iBook' link ");
					
				
					expected = "http://itunes.apple.com/us/book/boaters-guide-to-maine-boating/id447386168?mt=11";
					actual = handbook.HandbookFormatInfo_Apple.getAttribute("href");
					
					if(actual.equals(expected))
					{
						System.out.println("Success !! 'Apple iBook' link is working as expected  i.e. " + actual);
						Reporter.log("Success !!'Apple iBook' link is working as expected i.e. " + actual);
					}
					else
					{
						System.out.println("Failure !! 'Apple iBook' link is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						Reporter.log("Failure !! 'Apple iBook' link is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						AssertFailedCount++;
					}			
					
						
				System.out.println("Step 5 : Under 'Buy the eBook' button , clicking on 'B&N Nook Book' link ");
				Reporter.log("Step 5 : Under 'Buy the eBook' button , clicking on 'B&N Nook Book' link ");
					
					expected = "http://www.barnesandnoble.com/w/the-guide-to-maine-boating-laws-boat-ed-kalkomey/1110625296?ean=2940014399135";
					actual = handbook.HandbookFormatInfo_NookBook.getAttribute("href");
					
					if(actual.equals(expected))
					{
						System.out.println("Success !! 'B&N Nook Book' link  is working as expected  i.e. " + actual);
						Reporter.log("Success !!'B&N Nook Book' link  is working as expected i.e. " + actual);
					}
					else
					{
						System.out.println("Failure !! 'B&N Nook Book' link  is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						Reporter.log("Failure !! 'B&N Nook Book' link is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						AssertFailedCount++;
					}
					
				System.out.println("Step 6 : Under 'Buy the eBook' button , clicking on 'Web Version' link ");
				Reporter.log("Step 6 : Under 'Buy the eBook' button , clicking on 'Web Version' link ");
					
					expected = Constants.ApplicationURL + "/maine/handbook/book.html";
					actual = handbook.HandbookFormatInfo_WebVersion.getAttribute("href");
					
					if(actual.equals(expected))
					{
						System.out.println("Success !! 'Web Version' link  is working as expected  i.e. " + actual);
						Reporter.log("Success !!'Web Version' link  is working as expected i.e. " + actual);
					}
					else
					{
						System.out.println("Failure !! 'Web Version' link  is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						Reporter.log("Failure !! 'Web Version' link is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						AssertFailedCount++;
					}	
				
					
				System.out.println("Step 7 : Under 'Get it on your Phone, eReader, and Desktop' section , verifying whether formats info displayed & links are working as expected. ");
				Reporter.log("Step 3 : Under 'Get it on your Phone, eReader, and Desktop' section , verifying whether formats info displayed & links are working as expected. ");
				
							
					// Verifying iBook Version
					expected = "iBook Version" + "Take the The Boater's Guide to Maine Boating Laws and Responsibilities with you on your iPhone, iPod Touch, or iPad.";
					actual = handbook.iBookVesionHeader.getText() + handbook.iBookVesionInfoText.getText() ;
					
					if(actual.equals(expected))
					{
						System.out.println("Success !!  'iBook Version' section is present");
						Reporter.log("Success !!  'iBook Version' section is present");
					}
					else
					{
						System.out.println("Failure !!  'iBook Version' section is NOT present");
						Reporter.log("Failure !!  'iBook Version' section is NOT present");
						AssertFailedCount++;
					}
						
					
					expected = "http://itunes.apple.com/us/book/boaters-guide-to-maine-boating/id447386168?mt=11";
					actual = handbook.iBookVesionInfoLink.getAttribute("href");
					
					if(actual.equals(expected))
					{
						System.out.println("Success !! 'Apple iBook' link is working as expected  i.e. " + actual);
						Reporter.log("Success !!'Apple iBook' link is working as expected i.e. " + actual);
					}
					else
					{
						System.out.println("Failure !! 'Apple iBook' link is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						Reporter.log("Failure !! 'Apple iBook' link is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						AssertFailedCount++;
					}		
					
				
					// Verifying Nook Version
					expected = "Nook Version" + "Start the handbook on your NOOK, and continue reading on the go on your smartphone or computer.";
					actual = handbook.NookVesionHeader.getText() + handbook.NookVesionInfoText.getText() ;
					
					if(actual.equals(expected))
					{
						System.out.println("Success !!  'Nook Version' section is present");
						Reporter.log("Success !!  'Nook Version' section is present");
					}
					else
					{
						System.out.println("Failure !!  'Nook Version' section is NOT present");
						Reporter.log("Failure !!  'Nook Version' section is NOT present");
						AssertFailedCount++;
					}
						
					expected = "http://www.barnesandnoble.com/w/the-guide-to-maine-boating-laws-boat-ed-kalkomey/1110625296?ean=2940014399135";
					actual = handbook.NookVesionInfoLink.getAttribute("href");
					
					if(actual.equals(expected))
					{
						System.out.println("Success !! 'B&N Nook Book' link  is working as expected  i.e. " + actual);
						Reporter.log("Success !!'B&N Nook Book' link  is working as expected i.e. " + actual);
					}
					else
					{
						System.out.println("Failure !! 'B&N Nook Book' link  is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						Reporter.log("Failure !! 'B&N Nook Book' link is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						AssertFailedCount++;
					}
				
					// Verifying Free Version
					expected = "Free Version" + "The ME boating handbook is also available in free web and PDF versions.";
					actual = handbook.FreeVesionHeader.getText() + handbook.FreeVesionInfoText.getText() ;
					
					if(actual.equals(expected))
					{
						System.out.println("Success !!  'Free Version' section is present");
						Reporter.log("Success !!  'Free Version' section is present");
					}
					else
					{
						System.out.println("Failure !!  'Free Version' section is NOT present");
						Reporter.log("Failure !!  'Free Version' section is NOT present");
						AssertFailedCount++;
					}
					
					expected = Constants.ApplicationURL + "/maine/handbook/book.html";
					actual = handbook.FreeVesionInfoLink.getAttribute("href");
					
					if(actual.equals(expected))
					{
						System.out.println("Success !! 'Web Version' link  is working as expected  i.e. " + actual);
						Reporter.log("Success !!'Web Version' link  is working as expected i.e. " + actual);
					}
					else
					{
						System.out.println("Failure !! 'Web Version' link  is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						Reporter.log("Failure !! 'Web Version' link is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						AssertFailedCount++;
					}	
				
				
			}
			else
			{
				System.out.println("Failure !!  user is redirected to " + actual + "  instead of " + expected );
				Reporter.log("Failure !!  user is redirected to " + actual + "  instead of " + expected );
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
	
	
	 Test 4: 
	 * Verify three formats are present for handbook for Boat-ed Massachusetts  state 
	 
	@Test
	private void Handbook_Massachusetts_HandbookVersions() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 4 : Verify three formats are present for handbook for Boat-ed Massachusetts  state "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 4 : Verify three formats are present for handbook for Boat-ed Massachusetts  state "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HandbookPage handbook = new HandbookPage(driver);
	
		System.out.println("Step 1 : Navigate to  Boat-ed Massachusetts  state page : " + Constants.ApplicationURL + "/massachusetts");
		Reporter.log("Step 1 : Navigate to  Boat-ed Massachusetts  state page : " + Constants.ApplicationURL + "/massachusetts "); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/massachusetts");
	
		System.out.println("Step 2 : Verifying whether user is redirected to correct page or not");
		Reporter.log("Step 2 : Verifying whether user is redirected to correct page or not"); 
			
			Thread.sleep(6000);
			String expected = Constants.ApplicationURL + "/massachusetts/handbook/";
			String actual = driver.getCurrentUrl();
			if(actual.equals(expected))
			{
				System.out.println("Success !! user is redirected to : " + actual);
				Reporter.log("Success !! user is redirected to : " + actual);
				
				
				System.out.println("Step 3 : Under 'Buy the eBook' button , verifying whether formats info displayed & links are working as expected. ");
				Reporter.log("Step 3 : Under 'Buy the eBook' button , verifying whether formats info displayed & links are working as expected. ");
				
						
						// Verifying whether handbook format info is displayed
						expected = "Also available in these formats:" + "\n" + 
									"Apple iBook, B&N Nook Book, Web Version";
						actual = handbook.HandbookFormatInfo.getText().trim() ;
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  handbook format info is correct i.e. " + actual);
							Reporter.log("Success !! handbook format info is correct i.e. " + actual);
						}
						else
						{
							System.out.println("Failure !!  handbook format info is incorrect i.e. " + actual);
							Reporter.log("Failure !! handbook format info is incorrect i.e. " + actual );
							AssertFailedCount++;
						}
				
				System.out.println("Step 4 : Under 'Buy the eBook' button , clicking on 'Apple iBook' link ");
				Reporter.log("Step 4 : Under 'Buy the eBook' button , clicking on 'Apple iBook' link ");
					
				
					expected = "http://itunes.apple.com/us/book/boat-massachusetts-your-guide/id535553087?mt=11";
					actual = handbook.HandbookFormatInfo_Apple.getAttribute("href");
					
					if(actual.equals(expected))
					{
						System.out.println("Success !! 'Apple iBook' link is working as expected  i.e. " + actual);
						Reporter.log("Success !!'Apple iBook' link is working as expected i.e. " + actual);
					}
					else
					{
						System.out.println("Failure !! 'Apple iBook' link is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						Reporter.log("Failure !! 'Apple iBook' link is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						AssertFailedCount++;
					}			
					
						
				System.out.println("Step 5 : Under 'Buy the eBook' button , clicking on 'B&N Nook Book' link ");
				Reporter.log("Step 5 : Under 'Buy the eBook' button , clicking on 'B&N Nook Book' link ");
					
					expected = "http://www.barnesandnoble.com/w/boat-massachusetts-your-guide-to-boating-laws-and-responsibilities-kalkomey/1111514348";
					actual = handbook.HandbookFormatInfo_NookBook.getAttribute("href");
					
					if(actual.equals(expected))
					{
						System.out.println("Success !! 'B&N Nook Book' link  is working as expected  i.e. " + actual);
						Reporter.log("Success !!'B&N Nook Book' link  is working as expected i.e. " + actual);
					}
					else
					{
						System.out.println("Failure !! 'B&N Nook Book' link  is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						Reporter.log("Failure !! 'B&N Nook Book' link is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						AssertFailedCount++;
					}
					
				System.out.println("Step 6 : Under 'Buy the eBook' button , clicking on 'Web Version' link ");
				Reporter.log("Step 6 : Under 'Buy the eBook' button , clicking on 'Web Version' link ");
					
					expected = Constants.ApplicationURL + "/massachusetts/handbook/book.html";
					actual = handbook.HandbookFormatInfo_WebVersion.getAttribute("href");
					
					if(actual.equals(expected))
					{
						System.out.println("Success !! 'Web Version' link  is working as expected  i.e. " + actual);
						Reporter.log("Success !!'Web Version' link  is working as expected i.e. " + actual);
					}
					else
					{
						System.out.println("Failure !! 'Web Version' link  is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						Reporter.log("Failure !! 'Web Version' link is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						AssertFailedCount++;
					}	
				
					
				System.out.println("Step 7 : Under 'Get it on your Phone, eReader, and Desktop' section , verifying whether formats info displayed & links are working as expected. ");
				Reporter.log("Step 3 : Under 'Get it on your Phone, eReader, and Desktop' section , verifying whether formats info displayed & links are working as expected. ");
				
							
					// Verifying iBook Version
					expected = "iBook Version" + "Take the Boat Massachusetts - Your Guide to Boating Laws and Responsibilities with you on your iPhone, iPod Touch, or iPad.";
					actual = handbook.iBookVesionHeader.getText() + handbook.iBookVesionInfoText.getText() ;
					
					if(actual.equals(expected))
					{
						System.out.println("Success !!  'iBook Version' section is present");
						Reporter.log("Success !!  'iBook Version' section is present");
					}
					else
					{
						System.out.println("Failure !!  'iBook Version' section is NOT present");
						Reporter.log("Failure !!  'iBook Version' section is NOT present");
						AssertFailedCount++;
					}
						
					
					expected = "http://itunes.apple.com/us/book/boat-massachusetts-your-guide/id535553087?mt=11";
					actual = handbook.iBookVesionInfoLink.getAttribute("href");
					
					if(actual.equals(expected))
					{
						System.out.println("Success !! 'Apple iBook' link is working as expected  i.e. " + actual);
						Reporter.log("Success !!'Apple iBook' link is working as expected i.e. " + actual);
					}
					else
					{
						System.out.println("Failure !! 'Apple iBook' link is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						Reporter.log("Failure !! 'Apple iBook' link is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						AssertFailedCount++;
					}		
					
				
					// Verifying Nook Version
					expected = "Nook Version" + "Start the handbook on your NOOK, and continue reading on the go on your smartphone or computer.";
					actual = handbook.NookVesionHeader.getText() + handbook.NookVesionInfoText.getText() ;
					
					if(actual.equals(expected))
					{
						System.out.println("Success !!  'Nook Version' section is present");
						Reporter.log("Success !!  'Nook Version' section is present");
					}
					else
					{
						System.out.println("Failure !!  'Nook Version' section is NOT present");
						Reporter.log("Failure !!  'Nook Version' section is NOT present");
						AssertFailedCount++;
					}
						
					expected = "http://www.barnesandnoble.com/w/boat-massachusetts-your-guide-to-boating-laws-and-responsibilities-kalkomey/1111514348";
					actual = handbook.NookVesionInfoLink.getAttribute("href");
					
					if(actual.equals(expected))
					{
						System.out.println("Success !! 'B&N Nook Book' link  is working as expected  i.e. " + actual);
						Reporter.log("Success !!'B&N Nook Book' link  is working as expected i.e. " + actual);
					}
					else
					{
						System.out.println("Failure !! 'B&N Nook Book' link  is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						Reporter.log("Failure !! 'B&N Nook Book' link is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						AssertFailedCount++;
					}
				
					// Verifying Free Version
					expected = "Free Version" + "The MA boating handbook is also available in free web and PDF versions.";
					actual = handbook.FreeVesionHeader.getText() + handbook.FreeVesionInfoText.getText() ;
					
					if(actual.equals(expected))
					{
						System.out.println("Success !!  'Free Version' section is present");
						Reporter.log("Success !!  'Free Version' section is present");
					}
					else
					{
						System.out.println("Failure !!  'Free Version' section is NOT present");
						Reporter.log("Failure !!  'Free Version' section is NOT present");
						AssertFailedCount++;
					}
					
					expected = Constants.ApplicationURL + "/massachusetts/handbook/book.html";
					actual = handbook.FreeVesionInfoLink.getAttribute("href");
					
					if(actual.equals(expected))
					{
						System.out.println("Success !! 'Web Version' link  is working as expected  i.e. " + actual);
						Reporter.log("Success !!'Web Version' link  is working as expected i.e. " + actual);
					}
					else
					{
						System.out.println("Failure !! 'Web Version' link  is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						Reporter.log("Failure !! 'Web Version' link is NOT working as expected i.e. Actual : " + actual  + "  Expected : " + expected);
						AssertFailedCount++;
					}	
				
				
			}
			else
			{
				System.out.println("Failure !!  user is redirected to " + actual + "  instead of " + expected );
				Reporter.log("Failure !!  user is redirected to " + actual + "  instead of " + expected );
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
	
	/* Test 5: 
	 * Verifying Header/Footer on Boat-Ed Maine landing page
	 
	@Test
	private void HeaderFooter_MaineLandingPage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 5 : Verifying Header/Footer on Boat-Ed Maine landing page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 5 : Verifying Header/Footer on Boat-Ed Maine landing page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HandbookPage handbook = new HandbookPage(driver);
	
		System.out.println("Step 1 : Navigate to  Boat-ed Maine state page : " + Constants.ApplicationURL + "/maine");
		Reporter.log("Step 1 : Navigate to  Boat-ed Maine state page : " + Constants.ApplicationURL + "/maine"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/maine/handbook/");
	
		System.out.println("Step 2 : Verifying whether user is redirected to correct page or not");
		Reporter.log("Step 2 : Verifying whether user is redirected to correct page or not"); 
			
			Thread.sleep(6000);
			String expected = Constants.ApplicationURL + "/maine/handbook/";
			String actual = driver.getCurrentUrl();
			if(actual.equals(expected))
			{
				System.out.println("Success !! user is redirected to : " + actual);
				Reporter.log("Success !! user is redirected to : " + actual);
				
				
				System.out.println("Step 3 : Verifying Header - Application Name");
				Reporter.log("Step 3 : Verifying Header - Application Name"); 
					
						//Verifying Application Name
						expected = "The Official Maine Boat Safety Handbook";
						actual = handbook.ApplicationName.getText();
						if(actual.contains(expected))
						{
							System.out.println("Success !!  Application Name is correct i.e. " + actual);
							Reporter.log("Success !!  Application Name is correct i.e. " + actual);
						}
						else
						{
							System.out.println("Failure !!  Application Name is incorrect i.e. " + actual);
							Reporter.log("Failure !! Application Name is incorrect i.e. " + actual );
							AssertFailedCount++;
						}
						
				System.out.println("Step 4 : Verifying Footer details");
				Reporter.log("Step 4 : Verifying Footer details"); 
				
						//Contact Us
						expected = "THE BOAT MAINE HANDBOOK" + "\n" + "Contact Us";
						actual = handbook.Footer_ContactUs.getText();
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  Footer : Contact Us section has correct details i.e. " + actual);
							Reporter.log("Success !!  Footer : Contact Us section has correct details " + actual);
						}
						else
						{
							System.out.println("Failure !!  Footer : Contact Us section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							Reporter.log("Failure !!  Footer : Contact Us section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							AssertFailedCount++;
						}
						
						//HOW DO I GET MY STATE BOATING LICENSE?
						expected = "HOW DO I GET MY STATE BOATING LICENSE?" + "\n" +
									"You may have heard that you need the Maine Boating License or the Maine Boat License to operate your boat, but what you really need is the Boating Safety Education Certificate.";
						actual = handbook.Footer_GetMyBoatingLicence.getText();
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  Footer : HOW DO I GET MY STATE BOATING LICENSE? section has correct details i.e. " + actual);
							Reporter.log("Success !!  Footer : HOW DO I GET MY STATE BOATING LICENSE? section has correct details " + actual);
						}
						else
						{
							System.out.println("Failure !!  Footer : HOW DO I GET MY STATE BOATING LICENSE? section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							Reporter.log("Failure !!  Footer : HOW DO I GET MY STATE BOATING LICENSE? section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							AssertFailedCount++;
						}
						
						//About Us
						expected = "ABOUT US" + "\n" + 
									"Boat Ed provides print and Internet boating safety courses for 49 states, including the state of Maine, plus the U.S. Coast Guard Auxiliary." + "\n" 
									+ "Press Releases | YouTube Channel";
						actual = handbook.Footer_AboutUs.getText();
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  Footer : About Us section has correct details i.e. " + actual);
							Reporter.log("Success !!  Footer : About Us section has correct details " + actual);
						}
						else
						{
							System.out.println("Failure !!  Footer :  About Us section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							Reporter.log("Failure !!  Footer : About Us section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							AssertFailedCount++;
						}
						
						
						//Boat-Ed logo
						expected = Constants.ApplicationURL + "/assets/img/marketing/logos/logo_boat_ed_lowres.jpg";
						actual = handbook.Footer_BoatEdLogo.getAttribute("src");
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  Footer :Boat-Ed logo section has correct details i.e. " + actual);
							Reporter.log("Success !!  Footer : Boat-Ed logo section has correct details " + actual);
						}
						else
						{
							System.out.println("Failure !!  Footer : Boat-Ed logo section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							Reporter.log("Failure !!  Footer : Boat-Ed logo section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							AssertFailedCount++;
						}
						
						
						//KalKomey info
						expected = "Boat Ed is a division of Kalkomey Enterprises, Inc." + "\n" + 
									"14086 Proton Road" + "\n" +
									"Dallas, Texas 75244" + "\n" +
									"800-830-2268" + "\n" +
									"info@kalkomey.com";
						actual = handbook.Footer_KalKomeyInfo.getText();
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  Footer :KalKomey info section has correct details i.e. " + actual);
							Reporter.log("Success !!  Footer : KalKomey info section has correct details " + actual);
						}
						else
						{
							System.out.println("Failure !!  Footer : KalKomey info section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							Reporter.log("Failure !!  Footer : KalKomey info section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							AssertFailedCount++;
						}
						
						//ABOUT BOAT ED
						expected = "ABOUT BOAT ED" + "\n" + 
									"Boat Ed provides print and Internet boating safety courses for 49 states, including the state of Maine, plus the U.S. Coast Guard Auxiliary." + "\n" + 
									"Press Releases | YouTube Channel" + "\n" + 
									"FOLLOW US";
						actual = handbook.Footer_AboutBoatEd.getText();
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  Footer : ABOUT BOAT ED section has correct details i.e. " + actual);
							Reporter.log("Success !!  Footer : ABOUT BOAT ED section has correct details " + actual);
						}
						else
						{
							System.out.println("Failure !!  Footer : ABOUT BOAT ED section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							Reporter.log("Failure !!  Footer : ABOUT BOAT ED section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							AssertFailedCount++;
						}
						
						
						//Rights info
						expected = "� 1998�2016 All rights reserved." + "\n" + 
									"View Privacy and Terms of Use.";
						actual = handbook.Footer_RightsInfo.getText();
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  Footer : Rights info section has correct details i.e. " + actual);
							Reporter.log("Success !!  Footer : Rights info section has correct details " + actual);
						}
						else
						{
							System.out.println("Failure !!  Footer : Rights info section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							Reporter.log("Failure !!  Footer : Rights info section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							AssertFailedCount++;
						}
						
						//Products info
						expected = "Kalkomey is the official provider of recreational safety education materials for all 50 states." + "\n" + 
									"ONLINE BOATING AND HUNTING LICENSES AND RECREATIONAL SAFETY EDUCATION" + "\n" +
									"boat-ed.com � hunter-ed.com � bowhunter-ed.com" + "\n" +
									"offroad-ed.com � snowmobile-ed.com";
						actual = handbook.Footer_ProductsInfo.getText();
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  Footer : Products info section has correct details i.e. " + actual);
							Reporter.log("Success !!  Footer : Products info section has correct details " + actual);
						}
						else
						{
							System.out.println("Failure !!  Footer : Products info section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							Reporter.log("Failure !!  Footer : Products info section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							AssertFailedCount++;
						}
				
			}
			else
			{
				System.out.println("Failure !!  user is redirected to " + actual + "  instead of " + expected );
				Reporter.log("Failure !!  user is redirected to " + actual + "  instead of " + expected );
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
	
	
	 Test 6: 
	 * Verifying Header/Footer on Boat-Ed Massachusetts landing page
	 
	@Test
	private void HeaderFooter_MassachusettsLandingPage() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 6 : Verifying Header/Footer on Boat-Ed Massachusetts landing page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 6 : Verifying Header/Footer on Boat-Ed Massachusetts landing page"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HandbookPage handbook = new HandbookPage(driver);
	
		System.out.println("Step 1 : Navigate to  Boat-ed Massachusetts state page : " + Constants.ApplicationURL + "/massachusetts");
		Reporter.log("Step 1 : Navigate to  Boat-ed Massachusetts state page : " + Constants.ApplicationURL + "/massachusetts"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/massachusetts");
	
		System.out.println("Step 2 : Verifying whether user is redirected to correct page or not");
		Reporter.log("Step 2 : Verifying whether user is redirected to correct page or not"); 
			
			Thread.sleep(6000);
			String expected = Constants.ApplicationURL + "/massachusetts/handbook/";
			String actual = driver.getCurrentUrl();
			if(actual.equals(expected))
			{
				System.out.println("Success !! user is redirected to : " + actual);
				Reporter.log("Success !! user is redirected to : " + actual);
				
				
				System.out.println("Step 3 : Verifying Header - Application Name");
				Reporter.log("Step 3 : Verifying Header - Application Name"); 
					
						//Verifying Application Name
						expected = "Boat Massachusetts";
						actual = handbook.ApplicationName.getText();
						if(actual.contains(expected))
						{
							System.out.println("Success !!  Application Name is correct i.e. " + actual);
							Reporter.log("Success !!  Application Name is correct i.e. " + actual);
						}
						else
						{
							System.out.println("Failure !!  Application Name is incorrect i.e. " + actual);
							Reporter.log("Failure !! Application Name is incorrect i.e. " + actual );
							AssertFailedCount++;
						}
						
				System.out.println("Step 4 : Verifying Footer details");
				Reporter.log("Step 4 : Verifying Footer details"); 
				
						//Contact Us
						expected = "THE BOAT MASSACHUSETTS HANDBOOK" + "\n" + "Contact Us";
						actual = handbook.Footer_ContactUs.getText();
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  Footer : Contact Us section has correct details i.e. " + actual);
							Reporter.log("Success !!  Footer : Contact Us section has correct details " + actual);
						}
						else
						{
							System.out.println("Failure !!  Footer : Contact Us section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							Reporter.log("Failure !!  Footer : Contact Us section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							AssertFailedCount++;
						}
						
					
						//About Us
						expected = "ABOUT US" + "\n" + 
									"Boat Ed provides print and Internet boating safety courses for 49 states, including the state of Massachusetts, plus the U.S. Coast Guard Auxiliary." + "\n" 
									+ "Press Releases | YouTube Channel";
						actual = handbook.Footer_GetMyBoatingLicence.getText();
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  Footer : About Us section has correct details i.e. " + actual);
							Reporter.log("Success !!  Footer : About Us section has correct details " + actual);
						}
						else
						{
							System.out.println("Failure !!  Footer :  About Us section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							Reporter.log("Failure !!  Footer : About Us section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							AssertFailedCount++;
						}
						
						
						//Boat-Ed logo
						expected = Constants.ApplicationURL + "/assets/img/marketing/logos/logo_boat_ed_lowres.jpg";
						actual = handbook.Footer_BoatEdLogo_Massachusetts.getAttribute("src");
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  Footer :Boat-Ed logo section has correct details i.e. " + actual);
							Reporter.log("Success !!  Footer : Boat-Ed logo section has correct details " + actual);
						}
						else
						{
							System.out.println("Failure !!  Footer : Boat-Ed logo section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							Reporter.log("Failure !!  Footer : Boat-Ed logo section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							AssertFailedCount++;
						}
						
						
						//KalKomey info
						expected = "Boat Ed is a division of Kalkomey Enterprises, Inc." + "\n" + 
									"14086 Proton Road" + "\n" +
									"Dallas, Texas 75244" + "\n" +
									"800-830-2268" + "\n" +
									"info@kalkomey.com";
						actual = handbook.Footer_KalKomeyInfo.getText();
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  Footer :KalKomey info section has correct details i.e. " + actual);
							Reporter.log("Success !!  Footer : KalKomey info section has correct details " + actual);
						}
						else
						{
							System.out.println("Failure !!  Footer : KalKomey info section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							Reporter.log("Failure !!  Footer : KalKomey info section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							AssertFailedCount++;
						}
						
						//ABOUT BOAT ED
						expected = "ABOUT BOAT ED" + "\n" + 
									"Boat Ed provides print and Internet boating safety courses for 49 states, including the state of Massachusetts, plus the U.S. Coast Guard Auxiliary." + "\n" + 
									"Press Releases | YouTube Channel" + "\n" + 
									"FOLLOW US";
						actual = handbook.Footer_AboutBoatEd.getText();
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  Footer : ABOUT BOAT ED section has correct details i.e. " + actual);
							Reporter.log("Success !!  Footer : ABOUT BOAT ED section has correct details " + actual);
						}
						else
						{
							System.out.println("Failure !!  Footer : ABOUT BOAT ED section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							Reporter.log("Failure !!  Footer : ABOUT BOAT ED section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							AssertFailedCount++;
						}
						
						
						//Rights info
						expected = "� 1998�2016 All rights reserved." + "\n" + 
									"View Privacy and Terms of Use.";
						actual = handbook.Footer_RightsInfo.getText();
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  Footer : Rights info section has correct details i.e. " + actual);
							Reporter.log("Success !!  Footer : Rights info section has correct details " + actual);
						}
						else
						{
							System.out.println("Failure !!  Footer : Rights info section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							Reporter.log("Failure !!  Footer : Rights info section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							AssertFailedCount++;
						}
						
						//Products info
						expected = "Kalkomey is the official provider of recreational safety education materials for all 50 states." + "\n" + 
									"ONLINE BOATING AND HUNTING LICENSES AND RECREATIONAL SAFETY EDUCATION" + "\n" +
									"boat-ed.com � hunter-ed.com � bowhunter-ed.com" + "\n" +
									"offroad-ed.com � snowmobile-ed.com";
						actual = handbook.Footer_ProductsInfo.getText();
						
						if(actual.equals(expected))
						{
							System.out.println("Success !!  Footer : Products info section has correct details i.e. " + actual);
							Reporter.log("Success !!  Footer : Products info section has correct details " + actual);
						}
						else
						{
							System.out.println("Failure !!  Footer : Products info section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							Reporter.log("Failure !!  Footer : Products info section has incorrect details i.e. Actual : " + actual + "  Expected is :" + expected);
							AssertFailedCount++;
						}
				
			}
			else
			{
				System.out.println("Failure !!  user is redirected to " + actual + "  instead of " + expected );
				Reporter.log("Failure !!  user is redirected to " + actual + "  instead of " + expected );
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
	
	/* Test 7: 
	 * Handbook - Web format:  Verify page title, presence of 'Table Of Content' section, 'Previouls' 'Next' buttons
	*/ 
	@Test(dataProvider="Handbook",dataProviderClass=utility.TestNG.class)
	private void Handbook_Web_VerifyUIElements(String state) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 7 : Handbook - Web format:  Verify page title, presence of 'Table Of Content' section, 'Previouls' 'Next' buttons "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 7 : Handbook - Web format:  Verify page title, presence of 'Table Of Content' section, 'Previouls' 'Next' buttons"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HandbookPage handbook = new HandbookPage(driver);
	
		System.out.println("Step 1 : Navigate to Handbook Web Version for state at : " + Constants.ApplicationURL + state + "/handbook/book.html");
		Reporter.log("Step 1 : Navigate to Handbook Web Version for state at : " + Constants.ApplicationURL +  state + "/handbook/book.html");
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL +  state + "/handbook/book.html");
	
		System.out.println("Step 2 : Verifying whether user is redirected to correct page or not");
		Reporter.log("Step 2 : Verifying whether user is redirected to correct page or not"); 
			
			Thread.sleep(6000);
			String expected = Constants.ApplicationURL + state + "/handbook/book.html";
			String actual = driver.getCurrentUrl();
			if(actual.equals(expected))
			{
				System.out.println("Success !! user is redirected to : " + actual);
				Reporter.log("Success !! user is redirected to : " + actual);
				
				
				System.out.println("Step 3 : Verifying presence of UI elements");
				Reporter.log("Step 3 : Verifying presence of UI elements"); 
					
						
					
						//Verifying Page header & sub text
						expected = "The " + state.substring(1, state.length()) +  " Boater Safety Handbook" + "The Official Boating Handbook of the " + state.substring(1, state.length()) + " Department of Natural Resources - Web Version";
						
						
						actual = handbook.Book_PageHeader.getText() +  handbook.Book_PageHeader_Text.getText();
						System.out.println(expected);
						System.out.println(actual);
						
						if(actual.equalsIgnoreCase(expected))
						{
							System.out.println("Success !!  Page header & sub text is correct i.e. " + actual);
							Reporter.log("Success !!  Page header & sub text is correct i.e. " + actual);
						}
						else
						{
							System.out.println("Failure !!  Page header & sub text is incorrect i.e. " + actual);
							Reporter.log("Failure !! Page header & sub text is incorrect i.e. " + actual );
							AssertFailedCount++;
						}
						
				
						/*// Verifying presence of 'Web Version' text
						expected = "Web Version";
						actual = handbook.Book_PageHeader_WebVersionText.getText();
						if(actual.equals(expected))
						{
							System.out.println("Success !!  'Web Version' text is correct i.e. " + actual);
							Reporter.log("Success !!  'Web Version' text is correct i.e. " + actual);
						}
						else
						{
							System.out.println("Failure !!  'Web Version' text is incorrect i.e. " + actual);
							Reporter.log("Failure !! 'Web Version' text is incorrect i.e. " + actual );
							AssertFailedCount++;
						}*/
						
						// Verifying presence of 'Table of Contents' section
						expected = "Table of Contents";
						actual = handbook.Book_Index_Title.getText();
						if(actual.equals(expected))
						{
							System.out.println("Success !!  'Table of Contents' section is present ");
							Reporter.log("Success !!  'Table of Contents' section is present ");
						}
						else
						{
							System.out.println("Failure !! 'Table of Contents' section is NOT present ");
							Reporter.log("Failure !! 'Table of Contents' section is NOT present ");
							AssertFailedCount++;
						}
						
						
						// Verifying presence of 'Next' buttons at Top & Bottom of content preview
						expected = "Next";
						actual = handbook.NextButton_Top.getText();
						if(actual.contains(expected))
						{
							System.out.println("Success !!  'Next' buttons at Top is present ");
							Reporter.log("Success !!   'Next' buttons at Top is present ");
						}
						else
						{
							System.out.println("Failure !!  'Next' buttons at Top is NOT present");
							Reporter.log("Failure !! 'Next' buttons at Top is NOT present ");
							AssertFailedCount++;
						}
						
						actual = handbook.NextButton_Bottom.getText();
						if(actual.contains(expected))
						{
							System.out.println("Success !!  'Next' buttons at Bottom is present ");
							Reporter.log("Success !!   'Next' buttons at Bottom is present ");
						}
						else
						{
							System.out.println("Failure !!  'Next' buttons at Bottom is NOT present");
							Reporter.log("Failure !! 'Next' buttons at Bottom is NOT present ");
							AssertFailedCount++;
						}
						
						
						handbook.NextButton_Top.click();
						Thread.sleep(4000);
						
						// Verifying presence of 'Previous' buttons at Top & Bottom of content preview
						expected = "Previous";
						actual = handbook.PreviousButton_Top.getText();
						if(actual.contains(expected))
						{
							System.out.println("Success !!  'Previous' buttons at Top is present ");
							Reporter.log("Success !!   'Previous' buttons at Top is present ");
						}
						else
						{
							System.out.println("Failure !!  'Previous' buttons at Top is NOT present");
							Reporter.log("Failure !! 'Previous' buttons at Top is NOT present ");
							AssertFailedCount++;
						}
						
						actual = handbook.PreviousButton_Bottom.getText();
						if(actual.contains(expected))
						{
							System.out.println("Success !!  'Previous' buttons at Bottom is present ");
							Reporter.log("Success !!   'Previous' buttons at Bottom is present ");
						}
						else
						{
							System.out.println("Failure !!  'Previous' buttons at Bottom is NOT present");
							Reporter.log("Failure !! 'Previous' buttons at Bottom is NOT present ");
							AssertFailedCount++;
						}
				
			}
			else
			{
				System.out.println("Failure !!  user is redirected to " + actual + "  instead of " + expected );
				Reporter.log("Failure !!  user is redirected to " + actual + "  instead of " + expected );
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
	 * Handbook - Web format:  Verify that user can view contents successfully
	*/ 
	@Test(dataProvider="Handbook",dataProviderClass=utility.TestNG.class)
	private void Handbook_Web_ViewContents(String state) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 8 : Handbook - Web format:  Verify that user can view contents successfully "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 8 : Handbook - Web format:  Verify that user can view contents successfully"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HandbookPage handbook = new HandbookPage(driver);
	
		System.out.println("Step 1 : Navigate to Handbook Web Version for state at : " + Constants.ApplicationURL + state + "/handbook/book.html");
		Reporter.log("Step 1 : Navigate to Handbook Web Version for state at : " + Constants.ApplicationURL +  state + "/handbook/book.html");
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL +  state + "/handbook/book.html");
	
		System.out.println("Step 2 : Verifying whether user is redirected to correct page or not");
		Reporter.log("Step 2 : Verifying whether user is redirected to correct page or not"); 
			
			Thread.sleep(6000);
			String expected = Constants.ApplicationURL + state + "/handbook/book.html";
			String actual = driver.getCurrentUrl();
			if(actual.equals(expected))
			{
				System.out.println("Success !! user is redirected to : " + actual);
				Reporter.log("Success !! user is redirected to : " + actual);
				
				
				System.out.println("Step 3 : Verifying whether user is on Page 1 or not");
				Reporter.log("Step 3 : Verifying whether user is on Page 1 or not"); 
					
						//Verifying Contents Page Number
						expected = "Page 1 of ";
						actual = handbook.PageCount.getText();
						
						if(actual.contains(expected))
						{
							System.out.println("Success !!  User is on Page 1 ");
							Reporter.log("Success !! User is on Page 1 ");
						}
						else
						{
							System.out.println("Failure !!  User is NOT on Page 1 ");
							Reporter.log("Failure !! User is NOT on Page 1");
							AssertFailedCount++;
						}
						
						
				System.out.println("Step 4 : Click on Next button verifying whether user is navigated to Page 2 or not");
				Reporter.log("Step 4 : Click on Next button verifying whether user is navigated to Page 2 or not"); 
					
						handbook.NextButton_Top.click();
						Thread.sleep(4000);
						//Verifying Contents Page Number
						expected = "Page 2 of ";
						actual = handbook.PageCount.getText();
						
						if(actual.contains(expected))
						{
							System.out.println("Success !!  User is on Page 2 ");
							Reporter.log("Success !! User is on Page 2 ");
						}
						else
						{
							System.out.println("Failure !!  User is NOT on Page 2 ");
							Reporter.log("Failure !! User is NOT on Page 2");
							AssertFailedCount++;
						}
						
						
				System.out.println("Step 5 : Click on Previous button verifying whether user is navigated to Page 1 or not");
				Reporter.log("Step 5 : Click on Previous button verifying whether user is navigated to Page 1 or not"); 
					
						handbook.PreviousButton_Top.click();
						Thread.sleep(4000);

						//Verifying Contents Page Number
						expected = "Page 1 of ";
						actual = handbook.PageCount.getText();
						
						if(actual.contains(expected))
						{
							System.out.println("Success !!  User is on Page 1 ");
							Reporter.log("Success !! User is on Page 1");
						}
						else
						{
							System.out.println("Failure !!  User is NOT on Page 1 ");
							Reporter.log("Failure !! User is NOT on Page 1");
							AssertFailedCount++;
						}
						
						
				System.out.println("Step 6 : Clicking on pages links from 'Table Of Contents' and verifying whether user is navigated to respective page");
				Reporter.log("Step 6 : Clicking on pages links from 'Table Of Contents' and verifying whether user is navigated to respective page"); 
					
						
						for (int i=1;i<6;i++)
						{
							System.out.println("-- clicking on page link " + i);
							Reporter.log("-- clicking on page link " + i);
							
							handbook.ClickOnContentAndGetText(i);
							Thread.sleep(4000);

							//Verifying Contents Page Number
							expected = "Page " + i +" of ";
							actual = handbook.PageCount.getText();
							
							if(actual.contains(expected))
							{
								System.out.println("Success !!  User is on Page " + i);
								Reporter.log("Success !!  User is on Page " + i);
							}
							else
							{
								System.out.println("Failure !!  User is NOT on Page " + i);
								Reporter.log("Failure !! User is NOT on Page" + i);
								AssertFailedCount++;
							}
									
						}
						
						
						
			}
			else
			{
				System.out.println("Failure !!  user is redirected to " + actual + "  instead of " + expected );
				Reporter.log("Failure !!  user is redirected to " + actual + "  instead of " + expected );
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
	 * Handbook - Web format:  Verify quick links are working as expected
	*/ 
	@Test
	private void Handbook_Web_QuickLinks() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 9 : Handbook - Web format:  Verify quick links are workign as expected"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 9 : Handbook - Web format:  Verify quick links are workign as expected"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HandbookPage handbook = new HandbookPage(driver);
	
		System.out.println("Step 1 : Navigate to Handbook Web Version for state at : " + Constants.ApplicationURL +"massachusetts/handbook/book.html");
		Reporter.log("Step 1 : Navigate to Handbook Web Version for state at : " + Constants.ApplicationURL + "massachusetts/handbook/book.html");
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/massachusetts/handbook/book.html");
	
		System.out.println("Step 2 : Verifying whether user is redirected to correct page or not");
		Reporter.log("Step 2 : Verifying whether user is redirected to correct page or not"); 
			
			Thread.sleep(6000);
			String expected = Constants.ApplicationURL + "/massachusetts/handbook/book.html";
			String actual = driver.getCurrentUrl();
			if(actual.equals(expected))
			{
				System.out.println("Success !! user is redirected to : " + actual);
				Reporter.log("Success !! user is redirected to : " + actual);
				
				
				System.out.println("Step 3 : Verifying Quick links");
				Reporter.log("Step 3 : Verifying Quick links"); 
					
						
							//Verifying Quick link - Home
				
							String quicklink = handbook.ClickOnQuickLinksAndGetText(1);
							
							System.out.println("-- clicking on Quick link - " + quicklink);
							Reporter.log("-- clicking on Quick link - " + quicklink);
							
							if(quicklink.equals("1 Introduction"))
							{
								System.out.println("Success !!  Quick link name is correct. i.e. " + quicklink);
								Reporter.log("Success !!  Quick link name is correct. i.e. " + quicklink);
								
								expected = Constants.ApplicationURL + "/massachusetts/handbook/page/1/Introduction/";
								actual = driver.getCurrentUrl();
								
								if(actual.contains(expected))
								{
									System.out.println("Success !!  Quick link - Home is working as expected " );
									Reporter.log("Success !!  Quick link - Home is working as expected ");
								}
								else
								{
									System.out.println("Failure !!  Quick link is NOT working. Expected = " + expected + " , Actual = " + actual);
									Reporter.log("Failure !!  Quick link is NOT working. Expected = " + expected + " , Actual = " + actual);
									AssertFailedCount++;
								}
							}
							else
							{
								System.out.println("Failure !!  Quick link name is incorrect. Expected = Home , Actual = " + quicklink);
								Reporter.log("Failure !!  Quick link name is incorrect. Expected = Home , Actual = " + quicklink);
								AssertFailedCount++;
							}
							
							
							
							//Verifying Quick link - Boating Courses for Other States
							
							quicklink = handbook.ClickOnQuickLinksAndGetText(2);
							System.out.println("-- clicking on Quick link - " + quicklink);
							Reporter.log("-- clicking on Quick link - " + quicklink);
							
							if(quicklink.contains("For Boating Information"))
							{
								System.out.println("Success !!  Quick link name is correct. i.e. " + quicklink);
								Reporter.log("Success !!  Quick link name is correct. i.e. " + quicklink);
								
								
								//expected = Constants.ApplicationURL +  state + "/handbook/page/2/Vessel-Length/";
								
								expected = Constants.ApplicationURL +  "/massachusetts/handbook/page/2/For-Boating-Information/";
								actual = driver.getCurrentUrl();
								
								if(actual.contains(expected))
								{
									System.out.println("Success !!  Quick link - Boating Courses for Other States is working as expected " );
									Reporter.log("Success !!  Quick link - Boating Courses for Other States is working as expected ");
								}
								else
								{
									System.out.println("Failure !!  Quick link is NOT working. Expected = " + expected + " , Actual = " + actual);
									Reporter.log("Failure !!  Quick link is NOT working. Expected = " + expected + " , Actual = " + actual);
									AssertFailedCount++;
								}
							}
							else
							{
								System.out.println("Failure !!  Quick link name is incorrect. Expected = Boating Courses for Other States , Actual = " + quicklink);
								Reporter.log("Failure !!  Quick link name is incorrect. Expected = Boating Courses for Other States , Actual = " + quicklink);
								AssertFailedCount++;
							}
							
								
						
			}
			else
			{
				System.out.println("Failure !!  user is redirected to " + actual + "  instead of " + expected );
				Reporter.log("Failure !!  user is redirected to " + actual + "  instead of " + expected );
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
	 * Handbook - Web format :  Verify user can download PDF version
	*/ 
	@Test(dataProvider="Handbook",dataProviderClass=utility.TestNG.class)
	private void Handbook_Web_DownloadPDF(String state) throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 10 : Handbook - Web format :  Verify user can download PDF version"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 10 : Handbook - Web format :  Verify user can download PDF version"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HandbookPage handbook = new HandbookPage(driver);
	
		System.out.println("Step 1 : Navigate to Handbook Web Version for state at : " + Constants.ApplicationURL + state + "/handbook/book.html");
		Reporter.log("Step 1 : Navigate to Handbook Web Version for state at : " + Constants.ApplicationURL +  state + "/handbook/book.html");
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL +  state + "/handbook/book.html");
	
		System.out.println("Step 2 : Verifying whether user is redirected to correct page or not");
		Reporter.log("Step 2 : Verifying whether user is redirected to correct page or not"); 
			
			Thread.sleep(6000);
			String expected = Constants.ApplicationURL + state + "/handbook/book.html";
			String actual = driver.getCurrentUrl();
			if(actual.equals(expected))
			{
				System.out.println("Success !! user is redirected to : " + actual);
				Reporter.log("Success !! user is redirected to : " + actual);
				
				
				System.out.println("Step 3 : Clicking on 'Download the PDF Version' button");
				Reporter.log("Step 3 : Clicking on 'Download the PDF Version' button"); 
								
					Thread.sleep(5000);
					JavascriptExecutor jse = (JavascriptExecutor)driver;
					jse.executeScript("scroll(0, 500);");	
					
					handbook.DownloadPDFVersionButton.sendKeys(Keys.RETURN);
					Thread.sleep(3000);
					
					if(state.contains("maine"))
					{
						expected = Constants.ApplicationURL + "/assets/pdf/handbook/me_handbook_entire.pdf";
					}
					else
					{
						expected = Constants.ApplicationURL + "/assets/pdf/handbook/ma_handbook_entire.pdf";
					}
					
					actual = driver.getCurrentUrl();
					
					if(actual.contains(expected))
					{
						System.out.println("Success !!  User can download PDF successfully and navigated to : " + actual );
						Reporter.log("Success !!  User can download PDF successfully and navigated to : " + actual );
					}
					else
					{
						System.out.println("Failure !!  On click to Download PDF button , user navigated to : " + actual  + " , instead of - " + expected);
						Reporter.log("Failure !!  On click to Download PDF button , user navigated to : " + actual  + " , instead of - " + expected);
						AssertFailedCount++;
					}
						
			}
			else
			{
				System.out.println("Failure !!  user is redirected to " + actual + "  instead of " + expected );
				Reporter.log("Failure !!  user is redirected to " + actual + "  instead of " + expected );
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
	 * Handbook - Web format :  Maine  - Verify 'Apple iBook' and  'B&N Nook Book' buttons are working as expected
	 
	@Test
	private void Handbook_Web_Maine_iBookNookBook() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 11 : Handbook - Web format :  Maine  - Verify 'Apple iBook' and  'B&N Nook Book' buttons are working as expected"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 11 : Handbook - Web format :  Maine  - Verify 'Apple iBook' and  'B&N Nook Book' buttons are working as expected"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HandbookPage handbook = new HandbookPage(driver);
	
		System.out.println("Step 1 : Navigate to Handbook Web Version for state at : " + Constants.ApplicationURL + "/maine" + "/handbook/book.html");
		Reporter.log("Step 1 : Navigate to Handbook Web Version for state at : " + Constants.ApplicationURL +  "/maine" + "/handbook/book.html");
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL +  "/maine" + "/handbook/book.html");
	
		System.out.println("Step 2 : Verifying whether user is redirected to correct page or not");
		Reporter.log("Step 2 : Verifying whether user is redirected to correct page or not"); 
			
			Thread.sleep(6000);
			String expected = Constants.ApplicationURL + "/maine" + "/handbook/book.html";
			String actual = driver.getCurrentUrl();
			if(actual.equals(expected))
			{
				System.out.println("Success !! user is redirected to : " + actual);
				Reporter.log("Success !! user is redirected to : " + actual);
				
				
				System.out.println("Step 3 : Clicking on 'Apple iBook' button");
				Reporter.log("Step 3 : Clicking on 'Apple iBook' button");
				
					String winHandleBefore = driver.getWindowHandle();
					
					Thread.sleep(4000);
					
					handbook.AppleiBookButton.sendKeys(Keys.RETURN);
					Thread.sleep(3000);
					
					expected = "https://itunes.apple.com/us/book/boaters-guide-to-maine-boating/id447386168?mt=11";
					for (String winHandle : driver.getWindowHandles()) 
					{
					    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
					}
					actual = driver.getCurrentUrl();
					
					if(actual.contains(expected))
					{
						System.out.println("Success !!  Apple iBook button working and navigated to : " + actual );
						Reporter.log("Success !!  Apple iBook button working  and navigated to : " + actual );
					}
					else
					{
						System.out.println("Failure !!  On click to Apple iBook button , user navigated to : " + actual  + " , instead of - " + expected);
						Reporter.log("Failure !!  On click to Apple iBook button , user navigated to : " + actual  + " , instead of - " + expected);
						AssertFailedCount++;
					}
					
					
				System.out.println("Step 4 : Clicking on 'B&N Nook Book' button");
				Reporter.log("Step 3 : Clicking on 'B&N Nook Book' button");
				
					driver.close();
					//Switch back to original browser (first window)
					driver.switchTo().window(winHandleBefore);
					
					Thread.sleep(3000);
					handbook.BNNookBookButton.sendKeys(Keys.RETURN);
					Thread.sleep(3000);
					
					expected = "http://www.barnesandnoble.com/w/the-guide-to-maine-boating-laws-boat-ed-kalkomey/1110625296?ean=2940014399135";
					for (String winHandle : driver.getWindowHandles()) 
					{
					    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
					}
					actual = driver.getCurrentUrl();
					
					if(actual.contains(expected))
					{
						System.out.println("Success !!   B&N Nook Book button working and navigated to : " + actual );
						Reporter.log("Success !!   B&N Nook Book button working  and navigated to : " + actual );
					}
					else
					{
						System.out.println("Failure !!  On click to  B&N Nook Book button , user navigated to : " + actual  + " , instead of - " + expected);
						Reporter.log("Failure !!  On click to  B&N Nook Book button , user navigated to : " + actual  + " , instead of - " + expected);
						AssertFailedCount++;
					}

						
			}
			else
			{
				System.out.println("Failure !!  user is redirected to " + actual + "  instead of " + expected );
				Reporter.log("Failure !!  user is redirected to " + actual + "  instead of " + expected );
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
		
	}*/
	
	
	/* Test 12: 
	 * Handbook - Web format :  Massachusetts  - Verify 'Apple iBook' and  'B&N Nook Book' buttons are working as expected
	*/ 
	@Test
	private void Handbook_Web_Massachusetts_iBookNookBook() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 12 : Handbook - Web format :  massachusetts  - Verify 'Apple iBook' and  'B&N Nook Book' buttons are working as expected"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 12 : Handbook - Web format :  massachusetts  - Verify 'Apple iBook' and  'B&N Nook Book' buttons are working as expected"  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HandbookPage handbook = new HandbookPage(driver);
	
		System.out.println("Step 1 : Navigate to Handbook Web Version for state at : " + Constants.ApplicationURL + "/massachusetts" + "/handbook/book.html");
		Reporter.log("Step 1 : Navigate to Handbook Web Version for state at : " + Constants.ApplicationURL +  "/massachusetts" + "/handbook/book.html");
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL +  "/massachusetts" + "/handbook/book.html");
	
		System.out.println("Step 2 : Verifying whether user is redirected to correct page or not");
		Reporter.log("Step 2 : Verifying whether user is redirected to correct page or not"); 
			
			Thread.sleep(6000);
			String expected = Constants.ApplicationURL + "/massachusetts" + "/handbook/book.html";
			String actual = driver.getCurrentUrl();
			if(actual.equals(expected))
			{
				System.out.println("Success !! user is redirected to : " + actual);
				Reporter.log("Success !! user is redirected to : " + actual);
				
				
				System.out.println("Step 3 : Clicking on 'Apple iBook' button");
				Reporter.log("Step 3 : Clicking on 'Apple iBook' button");
				
					String winHandleBefore = driver.getWindowHandle();
					
					Thread.sleep(4000);
					
					handbook.AppleiBookButton.sendKeys(Keys.RETURN);
					Thread.sleep(3000);
					
					expected = "https://itunes.apple.com/us/book/boat-massachusetts-your-guide/id535553087?mt=11";
					for (String winHandle : driver.getWindowHandles()) 
					{
					    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
					}
					actual = driver.getCurrentUrl();
					
					if(actual.contains(expected))
					{
						System.out.println("Success !!  Apple iBook button working and navigated to : " + actual );
						Reporter.log("Success !!  Apple iBook button working  and navigated to : " + actual );
					}
					else
					{
						System.out.println("Failure !!  On click to Apple iBook button , user navigated to : " + actual  + " , instead of - " + expected);
						Reporter.log("Failure !!  On click to Apple iBook button , user navigated to : " + actual  + " , instead of - " + expected);
						AssertFailedCount++;
					}
					
					
				System.out.println("Step 4 : Clicking on 'B&N Nook Book' button");
				Reporter.log("Step 3 : Clicking on 'B&N Nook Book' button");
				
					driver.close();
					//Switch back to original browser (first window)
					driver.switchTo().window(winHandleBefore);
					
					handbook.BNNookBookButton.click();
					Thread.sleep(3000);
					
					expected = "http://www.barnesandnoble.com/w/boat-massachusetts-your-guide-to-boating-laws-and-responsibilities-kalkomey/1111514348";
					for (String winHandle : driver.getWindowHandles()) 
					{
					    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
					}
					actual = driver.getCurrentUrl();
					
					if(actual.contains(expected))
					{
						System.out.println("Success !!   B&N Nook Book button working and navigated to : " + actual );
						Reporter.log("Success !!   B&N Nook Book button working  and navigated to : " + actual );
					}
					else
					{
						System.out.println("Failure !!  On click to  B&N Nook Book button , user navigated to : " + actual  + " , instead of - " + expected);
						Reporter.log("Failure !!  On click to  B&N Nook Book button , user navigated to : " + actual  + " , instead of - " + expected);
						AssertFailedCount++;
					}

						
			}
			else
			{
				System.out.println("Failure !!  user is redirected to " + actual + "  instead of " + expected );
				Reporter.log("Failure !!  user is redirected to " + actual + "  instead of " + expected );
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
	
	
	
	/* Test 1: 
	 * Verify Courses > Boat > Handbook Page > " Buy Handbook" Button is present
	*/ 
	@Test
	private void BuyHandbook() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify Courses > Boat > Handbook Page >  Buy Handbook Button is present "  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify Courses > Boat > Handbook Page >  Buy Handbook Button is present "  + "\n" +
				 	  "====");	
		
		int AssertFailedCount=0 ;
		SeleniumFunctions SeleniumFunc = new SeleniumFunctions(driver);
		HandbookPage handbook = new HandbookPage(driver);
	
		System.out.println("Step 1 : Navigate to  Boat-ed Maine state page : " + Constants.ApplicationURL + "/maine/handbook/");
		Reporter.log("Step 1 : Navigate to  Boat-ed Maine state page : " + Constants.ApplicationURL + "/maine/handbook/"); 
			
			SeleniumFunc.ToGoToUrl(Constants.ApplicationURL + "/maine/handbook/");
			Thread.sleep(6000);
			
		System.out.println("Step 2: Verify Buy Handbook Button is present");
		Reporter.log("Step 2: Verify Buy Handbook Button is present"); 				
		
		// Verifying presence of 'Buy Handbook' button
			String expected = "Buy Handbook";
						try
						{
							driver.switchTo().frame("frame-product-handbook-maine-boating-laws-responsibilities");
							//driver.switchTo().frame(0);
							String actual = handbook.BuyTheEbook.getText();
							if(actual.equals(expected))
							{
								System.out.println("Success !! 'Buy the eBook' button is present ");
								Reporter.log("Success !!  'Buy the eBook' button is present");
							}	
							else
							{
								System.out.println("Failure !!  'Buy the eBook' button is NOT present");
								Reporter.log("Failure !!  'Buy the eBook' button is NOT present");
								AssertFailedCount++;
							}
						}
						catch(Exception e)
						{
							System.out.println("Failure !!  'Buy the eBook' button is NOT present");
							Reporter.log("Failure !!  'Buy the eBook' button is NOT present");
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
