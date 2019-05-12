package com.brokenlinks;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utility.Browser;
import utility.Constants;
import utility.JavaHelpers;

  
public class BrokenLinksTest 
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
	 * Verify broken links on page
	 * */ 
	@Test
	private void VerifyBrokenLinks() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 1 : Verify broken links on page"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 1 : Verify broken links on page"  + "\n" +
				 	  "====");	
		
		
		System.out.println("Step 1: Navigate to page : " + Constants.BrokenLink_TestPage);
		Reporter.log("Step 1: Navigate to page : " + Constants.BrokenLink_TestPage);
		
			driver.get(Constants.BrokenLink_TestPage);
			
		System.out.println("Step 2: Getting all  links from page");
		Reporter.log("Step 2: Getting all  links from page");	
	
			
			//Getting links from page
				List<WebElement> extract_links = driver.findElements(By.tagName("a"));
				
				ArrayList<String> link = new ArrayList<String>();
				
				//Excluding few links from broken links verification
					ArrayList<String> excludelinks = new ArrayList<String>();
					excludelinks.add("mailto:info@kalkomey.com");
					excludelinks.add("mailto:neb-webmaster@boat-ed.com");
				
				
				System.out.println(" ==> Links on page : " + "\n" + "------------------------------------------------");
				
				for(WebElement tdElement : extract_links) 
				 {
					String temp = tdElement.getAttribute("href");
					boolean isPresent = false;
					
					for(int i=0;i<link.size();i++)
					{
						if(link.get(i).equals(temp))
						{
							isPresent=true;
							break;
						}
					}
					
					if(!isPresent)
					{
						for(int i=0;i<excludelinks.size();i++)
						{
							if(excludelinks.get(i).equals(temp))
							{
								isPresent=true;
								break;
							}
						}
						
						if(!isPresent)
						{
							link.add(temp);
						}
					}
					
					
					
					
				 }
				
				System.out.println(" ==> Total links on page =  " + link.size() + "\n" + "------------------------------------------------");
				Reporter.log(" ==> Total links on page =  " + link.size() + "\n" + "------------------------------------------------");
				
				for(int i=0;i<link.size();i++)
				{
					System.out.println(link.get(i));
					Reporter.log(link.get(i));
				}
			
				
		System.out.println("Step 3: Navigating to each page and verifying Response code and presence of 404, 500 error pages");
		Reporter.log("Step 3: Navigating to each page and verifying Response code and presence of 404, 500 error pages");	
				
			ArrayList<String> brokenlinks = new ArrayList<String>();
			for(int i=0;i<link.size();i++)
			{
				String navigationurl = link.get(i);
				int j = i+1;
				System.out.println("[" + j+ "]" + " " + navigationurl);
				Reporter.log("[" + j+ "]" + " " + navigationurl);
				
				try
				{
					driver.get(navigationurl);
				}
				catch(TimeoutException e)
				{
					driver.get(navigationurl);
				}
				catch(UnhandledAlertException e)
				{
					driver.switchTo().alert().accept();
				}
				
				
				//Response code check
				int responsecode = JH.GetResponseCode(navigationurl);
				
				if(responsecode==500 || responsecode == 404)
				{
					System.out.println("--  " + navigationurl + " has" + responsecode +" error" );
					Reporter.log("--  " + navigationurl + " has" + responsecode +" error" );
					brokenlinks.add(navigationurl);
				}
			
				
			}
	
		
		/*
		 * Marking Test Pass or Fail
		 */
	 	if(brokenlinks.size()>0)	
		{
			System.out.println( "----------  Broken links are as below ------------------------" + "\n" );
			Reporter.log( "----------  Broken links are as below ------------------------" + "\n" );
			
			for(int i=0;i<brokenlinks.size();i++)
			{
				System.out.println(brokenlinks.get(i));
				Reporter.log(brokenlinks.get(i));
			}
			
	 		
			//Marking this test as Failed
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
	 
		
	}

	
	/* Test 2: 
	 * Entire Website crowling - verifying links
	 * */ 
	@Test
	private void EntireWebsiteCrowlingToVerifyLinks() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 2 : Entire Website crowling - verifying links"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 2 : Entire Website crowling - verifying links"  + "\n" +
				 	  "====");	
		
		
		System.out.println("Step 1: Navigate to page : " + Constants.BrokenLink_TestPage);
		Reporter.log("Step 1: Navigate to page : " + Constants.BrokenLink_TestPage);
		
			driver.get(Constants.BrokenLink_TestPage);
			
		System.out.println("Step 2: Getting all  links from first page");
		Reporter.log("Step 2: Getting all  links first page");	
	
			
			//Getting links from page
				List<WebElement> extract_links = driver.findElements(By.tagName("a"));
				
				ArrayList<String> link = new ArrayList<String>();
				
				//Excluding few links from broken links verification
					ArrayList<String> excludelinks = new ArrayList<String>();
					excludelinks.add("mailto:info@kalkomey.com");
					excludelinks.add("mailto:neb-webmaster@boat-ed.com");
				
				for(WebElement tdElement : extract_links) 
				 {
					String temp = tdElement.getAttribute("href");
					
					try
					{
						if(!temp.equals(null))
						{
							boolean isPresent = false;
							
							//Comparing whether link already exists 
							for(int i=0;i<link.size();i++)
							{
								if(link.get(i).equals(temp))
								{
									isPresent=true;
									break;
								}
							}
							
							if(!isPresent)
							{
								//Comparing whether link is not part of Exclude links
								for(int i=0;i<excludelinks.size();i++)
								{
									if(excludelinks.get(i).equals(temp))
									{
										isPresent=true;
										break;
									}
								}
								
								if(!isPresent)
								{
									link.add(temp);
								}
							}
						}
						
						
					}
					catch(Exception e)
					{
						//nothing
						//To avoid null pointer exception when A tag doesn't have href attribute
					}
				
					
				 }

				
				
			//new
			System.out.println("Step 3: Navigating to each page and verifying Response code and presence of 404, 500 error pages");
			Reporter.log("Step 3: Navigating to each page and verifying Response code and presence of 404, 500 error pages");
			
			System.out.println("Step 4: Getting all links for each page and for each link , verifying Response code and presence of 404, 500 error pages");
			Reporter.log("Step 4: Getting all links for each page and for each link , verifying Response code and presence of 404, 500 error pages");;
					
				ArrayList<String> brokenlinks = new ArrayList<String>();
				
				for(int m=0;m<link.size();m++)
				{
					String navigationurl = link.get(m);
					int j = m+1;
					System.out.println("[" + j+ "]" + " " + navigationurl);
					Reporter.log("[" + j+ "]" + " " + navigationurl);
					
					try
					{
						driver.get(navigationurl);
					}
					catch(TimeoutException e)
					{
						driver.get(navigationurl);
					}
					catch(UnhandledAlertException e)
					{
						driver.switchTo().alert().accept();
					}
					
					//Response code check
					int responsecode = JH.GetResponseCode(navigationurl);
					
					if(responsecode==500 || responsecode == 404)
					{
						System.out.println("--  " + navigationurl + " has" + responsecode +" error" );
						Reporter.log("--  " + navigationurl + " has" + responsecode +" error" );
						brokenlinks.add(navigationurl);
					}
					
					else if(navigationurl.contains(Constants.BrokenLink_TestPage))
					{
						//Getting links from page
						List<WebElement> extract_links1 = driver.findElements(By.tagName("a"));
						
						for(WebElement tdElement : extract_links1) 
						 {
							String temp = tdElement.getAttribute("href");
							
							try
							{
								if(!temp.equals(null))
								{
									boolean isPresent = false;
									
									//Comparing whether link already exists 
									for(int i=0;i<link.size();i++)
									{
										if(link.get(i).equals(temp))
										{
											isPresent=true;
											break;
										}
									}
									
									if(!isPresent)
									{
										//Comparing whether link is not part of Exclude links
										for(int i=0;i<excludelinks.size();i++)
										{
											if(excludelinks.get(i).equals(temp))
											{
												isPresent=true;
												break;
											}
										}
										
										
										if(!isPresent)
										{
											link.add(temp);
											System.out.println("-- New added link - " + temp);
											Reporter.log("-- New added link - " + temp);
										}
									}
			
								 }	
									
								
							}
							catch(Exception e)
							{
								//nothing
								//To avoid null pointer exception when A tag doesn't have href attribute
							}
							
							
						 }
					
				
					
					}
				}
			
		
		/*
		 * Marking Test Pass or Fail
		 */
	 	if(brokenlinks.size()>0)	
		{
			System.out.println( "----------  Broken links are as below ------------------------" + "\n" );
			Reporter.log( "----------  Broken links are as below ------------------------" + "\n" );
			
			for(int i=0;i<brokenlinks.size();i++)
			{
				System.out.println(brokenlinks.get(i));
				Reporter.log(brokenlinks.get(i));
			}
			
	 		
			//Marking this test as Failed
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
	 
		
	}

	
	/* Test 3: 
	 * Website crowling to 2nd stage - verifying links
	 * */ 
	@Test
	private void WebsiteCrowlingTill2ndStageToVerifyLinks() throws Exception
	{
		System.out.println("====" + "\n" +
					"Test 3 : Website crowling to 2nd stage - verifying links"  + "\n" +
		 			"====");
		Reporter.log("====" + "\n" +
		 			  "Test 3 : Website crowling to 2nd stage - verifying links"  + "\n" +
				 	  "====");	
		
		
		System.out.println("Step 1: Navigate to page : " + Constants.BrokenLink_TestPage);
		Reporter.log("Step 1: Navigate to page : " + Constants.BrokenLink_TestPage);
		
			driver.get(Constants.BrokenLink_TestPage);
			
		System.out.println("Step 2: Getting all  links from first page");
		Reporter.log("Step 2: Getting all  links first page");	
	
			
			//Getting links from page
				List<WebElement> extract_links = driver.findElements(By.tagName("a"));
				
				ArrayList<String> link = new ArrayList<String>();
				
				//Excluding few links from broken links verification
					ArrayList<String> excludelinks = new ArrayList<String>();
					excludelinks.add("mailto:info@kalkomey.com");
					excludelinks.add("mailto:neb-webmaster@boat-ed.com");
					excludelinks.add("tel:1-800-830-2268");
					excludelinks.add("tel:214-351-0461");
					
				
				for(WebElement tdElement : extract_links) 
				 {
					String temp = tdElement.getAttribute("href");
					
					try
					{
						if(!temp.equals(null))
						{
							boolean isPresent = false;
							
							//Comparing whether link already exists 
							for(int i=0;i<link.size();i++)
							{
								if(link.get(i).equals(temp))
								{
									isPresent=true;
									break;
								}
							}
							
							if(!isPresent)
							{
								//Comparing whether link is not part of Exclude links
								for(int i=0;i<excludelinks.size();i++)
								{
									if(excludelinks.get(i).equals(temp))
									{
										isPresent=true;
										break;
									}
								}
								
								if(temp.startsWith("mailto") || temp.startsWith("tel"))
								{
									isPresent=true;
									break;
								}
								
								if(!isPresent)
								{
									link.add(temp);
								}
							}
						}
						
						
					}
					catch(Exception e)
					{
						//nothing
						//To avoid null pointer exception when A tag doesn't have href attribute
					}
				
					
				 }

				
				
			//new
			System.out.println("Step 3: Navigating to each page and verifying Response code and presence of 404, 500 error pages");
			Reporter.log("Step 3: Navigating to each page and verifying Response code and presence of 404, 500 error pages");
			
			System.out.println("Step 4: Getting all links for each page and for each link , verifying Response code and presence of 404, 500 error pages");
			Reporter.log("Step 4: Getting all links for each page and for each link , verifying Response code and presence of 404, 500 error pages");;
					
				ArrayList<String> brokenlinks = new ArrayList<String>();
				ArrayList<String> link2 = new ArrayList<String>();
				int j=0;
				
				for(int m=0;m<link.size();m++)
				{
					String navigationurl = link.get(m);
					j = m+1;
					System.out.println("[" + j+ "]" + " " + navigationurl);
					Reporter.log("[" + j+ "]" + " " + navigationurl);
					
					try
					{
						driver.get(navigationurl);
					}
					catch(TimeoutException e)
					{
						driver.get(navigationurl);
					}
					catch(UnhandledAlertException e)
					{
						System.out.println("Alert ---> " + navigationurl);
						try{
							driver.switchTo().alert().accept();
						}
						catch(Exception e1)
						{
							//Nothing
						}
						
					}
					
					
					//Response code check
					int responsecode = JH.GetResponseCode(navigationurl);
					
					if(responsecode==500 || responsecode == 404)
					{
						System.out.println("--  " + navigationurl + " has" + responsecode +" error" );
						Reporter.log("--  " + navigationurl + " has" + responsecode +" error" );
						brokenlinks.add(navigationurl);
					}
					
					else if(navigationurl.contains(Constants.BrokenLink_TestPage.substring(6, Constants.BrokenLink_TestPage.length())))
					{
						//Getting links from page
						List<WebElement> extract_links1 = driver.findElements(By.tagName("a"));
						
						for(WebElement tdElement : extract_links1) 
						 {
							String temp = tdElement.getAttribute("href");
							
							try
							{
								if(!temp.equals(null))
								{
									boolean isPresent = false;
									
									//Comparing whether link already exists 
									for(int i=0;i<link.size();i++)
									{
										if(link.get(i).equals(temp))
										{
											isPresent=true;
											break;
										}
									}
								
									
									if(!isPresent)
									{
										//Comparing whether link is not part of Exclude links
										for(int i=0;i<excludelinks.size();i++)
										{	
											if(excludelinks.get(i).equals(temp))
											{
												isPresent=true;
												break;
											}
											
										}
										
										if(temp.startsWith("mailto") || temp.startsWith("tel"))
										{
											isPresent=true;
											break;
										}
										
										
										//Comparing whether link already exists 
										for(int i=0;i<link2.size();i++)
										{
											if(link2.get(i).equals(temp))
											{
												isPresent=true;
												break;
											}
										}
										
										
										if(!isPresent)
										{
											link2.add(temp);
											System.out.println("-- New added link - " + temp);
											Reporter.log("-- New added link - " + temp);
										}
									}
			
								 }	
									
								
							}
							catch(Exception e)
							{
								//nothing
								//To avoid null pointer exception when A tag doesn't have href attribute
							}
							
							
						 }
					
				
					
					}
				}
				
			
			for(int m=0;m<link2.size();m++)
			{
				String navigationurl = link2.get(m);
				j = j+1;
				System.out.println("[" + j+ "]" + " " + navigationurl);
				Reporter.log("[" + j+ "]" + " " + navigationurl);
				
				try
				{
					driver.get(navigationurl);
				}
				catch(TimeoutException e)
				{
					driver.get(navigationurl);
				}
				catch(UnhandledAlertException e)
				{
					driver.switchTo().alert().accept();
				}
				
				//Response code check
				int responsecode = JH.GetResponseCode(navigationurl);
				
				if(responsecode==500 || responsecode == 404)
				{
					System.out.println("--  " + navigationurl + " has" + responsecode +" error" );
					Reporter.log("--  " + navigationurl + " has" + responsecode +" error" );
					brokenlinks.add(navigationurl);
				}
			}
			
		
		/*
		 * Marking Test Pass or Fail
		 */
	 	if(brokenlinks.size()>0)	
		{
			System.out.println( "----------  Broken links are as below ------------------------" + "\n" );
			Reporter.log( "----------  Broken links are as below ------------------------" + "\n" );
			
			for(int i=0;i<brokenlinks.size();i++)
			{
				System.out.println(brokenlinks.get(i));
				Reporter.log(brokenlinks.get(i));
			}
			
	 		
			//Marking this test as Failed
			
			System.out.println("---- Test Failed. Please check the console or TestNg report for details");
			Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
			
			Assert.fail();
		}
	 
		
	}
}
