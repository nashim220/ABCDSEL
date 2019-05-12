package pageFactory.Courses;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException; 

import utility.Constants;
import utility.JavaHelpers;

public class KECourseAdminPage 
{
	WebDriver driver;
	String browser;
	JavaHelpers JH = new JavaHelpers();  
	

	
	public KECourseAdminPage(WebDriver driver)
	{	 
        this.driver = driver;
  
        //This initElements method will create all WebElements
        //PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.WebDriverWaitDuration), this);
	}
	
	
	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */ 	
	

		@FindBy(id="q_username")
	    WebElement Username;
		
		@FindBy(css="div.clear a")
	    WebElement Search;
	
	
	//Go to Admin Page on
		public void GoToAdminPage(String browser) throws IOException, InterruptedException, AWTException
		{
			
				while(true)
				{
					try
					{
						
						if(browser.equalsIgnoreCase("firefox"))
						{
							driver.navigate().to(Constants.AdminURL);
							
							 Thread.sleep(5000);
							 try {
							        Robot robot = new Robot();
							        
							        robot.keyPress(KeyEvent.VK_P);
							        robot.keyPress(KeyEvent.VK_I);
							        robot.keyPress(KeyEvent.VK_Y);
							        robot.keyPress(KeyEvent.VK_U);
							        robot.keyPress(KeyEvent.VK_S);
							        robot.keyPress(KeyEvent.VK_H);
							        robot.keyPress(KeyEvent.VK_TAB);//go to password feild
							        Thread.sleep(4000);
							        robot.keyPress(KeyEvent.VK_P);
							        robot.keyPress(KeyEvent.VK_I);
							        robot.keyPress(KeyEvent.VK_Y);
							        robot.keyPress(KeyEvent.VK_U);
							        robot.keyPress(KeyEvent.VK_S);
							        robot.keyPress(KeyEvent.VK_H);

							        Thread.sleep(4000);
							        robot.keyPress(KeyEvent.VK_ENTER);
							        Thread.sleep(4000);

							        } catch (AWTException e) {
							        e.printStackTrace();
							        }
						}
						else if(browser.equalsIgnoreCase("chrome"))
						{
			 
							 driver.get("https://piyush:piyush@admintools-qa.kalkomey.com/");
							Thread.sleep(4000);
						}
						else if(browser.equalsIgnoreCase("chrome_EM"))
						{
			 
							 driver.get("https://piyush:piyush@admintools-qa.kalkomey.com/");
								Thread.sleep(4000);
						}
						else if(browser.equalsIgnoreCase("ie"))
						{
							/*
							driver.navigate().to(Constants.AdminURL);
							
							Thread.sleep(5000);
							
							driver.navigate().to("javascript:document.getElementById('overridelink').click()");
							
							Thread.sleep(14000);						
							
							Runtime.getRuntime().exec("src/test/resources/AutoIt/Courses_admin_auth_IE.exe");
						
							*/
							 try {
								 
								 	driver.navigate().to(Constants.AdminURL);
									
								 	Thread.sleep(5000);
									//now we are going to use javascipt ,This will click on "Continue to this website (not recommended)" text and will
									//push us to the page
									driver.navigate().to("javascript:document.getElementById('overridelink').click()");
									Thread.sleep(6000);
							        Robot robot = new Robot();
							        Thread.sleep(6000);
							        robot.keyPress(KeyEvent.VK_P);
							        robot.keyPress(KeyEvent.VK_I);
							        robot.keyPress(KeyEvent.VK_Y);
							        robot.keyPress(KeyEvent.VK_U);
							        robot.keyPress(KeyEvent.VK_S);
							        robot.keyPress(KeyEvent.VK_H);
							        robot.keyPress(KeyEvent.VK_TAB);//go to password feild
							        Thread.sleep(6000);
							        robot.keyPress(KeyEvent.VK_P);
							        robot.keyPress(KeyEvent.VK_I);
							        robot.keyPress(KeyEvent.VK_Y);
							        robot.keyPress(KeyEvent.VK_U);
							        robot.keyPress(KeyEvent.VK_S);
							        robot.keyPress(KeyEvent.VK_H);

							        Thread.sleep(6000);
							        robot.keyPress(KeyEvent.VK_ENTER);
							        Thread.sleep(6000);

							        } catch (AWTException e) {
							        e.printStackTrace();
							        }
						}

						
						if(driver.getCurrentUrl().contains(Constants.AdminURL + "/admin/search?token"))
						{
							System.out.println("-- Authentication Successful ... ");
							break;	
						}
						
						
					}
					
					catch(TimeoutException e)
					{
						
						System.out.println("TimeoutException is thrown");
						
						if(browser.equalsIgnoreCase("firefox"))
						{
							System.out.println("-- Trying Authentication again on Firefox browser ");
							Runtime.getRuntime().exec("src/test/resources/AutoIt/Courses_admin_auth_FF_veryquick.exe");
						}
						else if(browser.equalsIgnoreCase("chrome"))
						{
							System.out.println("-- Trying Authentication again on Chrome browser ");
							Runtime.getRuntime().exec("src/test/resources/AutoIt/Courses_admin_auth_GC_quick.exe");
						}
						else if(browser.equalsIgnoreCase("ie"))
						{
							System.out.println("-- Trying Authentication again on Internet Explorer browser ");
							driver.navigate().to(Constants.AdminURL);
							Thread.sleep(5000);
							driver.navigate().to("javascript:document.getElementById('overridelink').click()");
							Thread.sleep(5000);
							Runtime.getRuntime().exec("src/test/resources/AutoIt/Authentication.exe");
						}
						
						Thread.sleep(15000);				
						System.out.println(driver.getCurrentUrl());
						continue;
							
					}
					
					catch(SkipException e)
					{
						System.out.println("SkipException is thrown");
						if(browser.equalsIgnoreCase("firefox"))
						{
							System.out.println("-- Trying Authentication again on Firefox browser ");
							Runtime.getRuntime().exec("src/test/resources/AutoIt/Courses_admin_auth_FF_quick.exe");
						}
						else if(browser.equalsIgnoreCase("chrome"))
						{
							System.out.println("-- Trying Authentication again on Chrome browser ");
							Runtime.getRuntime().exec("src/test/resources/AutoIt/Courses_admin_auth_GC_quick.exe");
						}
						else if(browser.equalsIgnoreCase("ie"))
						{
							System.out.println("-- Trying Authentication again on Internet Explorer browser ");
							Runtime.getRuntime().exec("src/test/resources/AutoIt/Authentication.exe");
						}
						
						Thread.sleep(15000);				
						System.out.println(driver.getCurrentUrl());
						continue;
					}
					
					
					catch(UnhandledAlertException e)
					{
						System.out.println("UnhandledAlertException is thrown");
						System.out.println("-- Trying Authentication again on Internet Explorer browser ");
						/*driver.navigate().to(Constants.AdminURL);
						Thread.sleep(5000);
						driver.navigate().to("javascript:document.getElementById('overridelink').click()");
						Thread.sleep(5000);
						Runtime.getRuntime().exec("src/test/resources/AutoIt/Authentication.exe");
						Thread.sleep(15000);				
						System.out.println(driver.getCurrentUrl());*/
						
						driver.navigate().to(Constants.AdminURL);
						
					 	Thread.sleep(5000);
						//now we are going to use javascipt ,This will click on "Continue to this website (not recommended)" text and will
						//push us to the page
						driver.navigate().to("javascript:document.getElementById('overridelink').click()");
						Thread.sleep(6000);
				        Robot robot = new Robot();
				        Thread.sleep(6000);
				        robot.keyPress(KeyEvent.VK_P);
				        robot.keyPress(KeyEvent.VK_I);
				        robot.keyPress(KeyEvent.VK_Y);
				        robot.keyPress(KeyEvent.VK_U);
				        robot.keyPress(KeyEvent.VK_S);
				        robot.keyPress(KeyEvent.VK_H);
				        robot.keyPress(KeyEvent.VK_TAB);//go to password feild
				        Thread.sleep(6000);
				        robot.keyPress(KeyEvent.VK_P);
				        robot.keyPress(KeyEvent.VK_I);
				        robot.keyPress(KeyEvent.VK_Y);
				        robot.keyPress(KeyEvent.VK_U);
				        robot.keyPress(KeyEvent.VK_S);
				        robot.keyPress(KeyEvent.VK_H);

				        Thread.sleep(6000);
				        robot.keyPress(KeyEvent.VK_ENTER);
				        Thread.sleep(6000);

						continue;
					}
				}
		

		}
	
	
	//Go to Search Page
		public void GoToSearchPage()
		{
			driver.get(Constants.AdminURL + "/admin/search");
		}
		
	
	//Go to Search Page
		public void GoToCommonTaskPage()
		{
			driver.get(Constants.AdminURL + "/admin/common_tasks");
		}
		
	
	//To click on Course button
		public void ClickOnCourseButton(String coursename) throws InterruptedException
		{
			for(int i=1;i<10;i++)
			{
				String cssselector = "#ed_prog_buttons a:nth-of-type("+ i + ")";
				String text = "";
				
				
				try
				{
					text = driver.findElement(By.cssSelector(cssselector)).getText();
					if(i==1)
		        	{
		        	WebDriverWait wait = new WebDriverWait(driver, 5);
		       		wait.until(ExpectedConditions.alertIsPresent());
					Alert alert = driver.switchTo().alert();
			        alert.accept();
		        	}
				}
				catch(Exception e)
				{
					//nothing :) 
				}
				
				if(text.equals(coursename))
				{
					driver.findElement(By.cssSelector(cssselector)).click();
					Thread.sleep(5000);

					break;
				}
			}
			
		}
	
	
	//To perform various activity for user record i.e. Progress, Final Exam, Quizzes etc.
		public void ClickOnButtonForUserRecordActivity(String activity) throws InterruptedException
		{
			for(int i=1;i<12;i++)
			{
				Thread.sleep(2000);
				String cssselector = "div.content table tr:nth-of-type(3) td:nth-of-type(9) a:nth-of-type("+ i + ")";
				String text = "temp";
			
				
		        try
		       	{	
		        	if(i==1)
		        	{
		        	WebDriverWait wait = new WebDriverWait(driver, 5);
		       		wait.until(ExpectedConditions.alertIsPresent());
					Alert alert = driver.switchTo().alert();
			        alert.accept();
		        	}
		        	
		        	text = driver.findElement(By.cssSelector(cssselector)).getText();
		        	
		        	if(i==1)
		        	{
		        	WebDriverWait wait = new WebDriverWait(driver, 5);
		       		wait.until(ExpectedConditions.alertIsPresent());
					Alert alert = driver.switchTo().alert();
			        alert.accept();
		        	}
				}
				catch(Exception e)
				{
					//nothing :) 
				}
				
				if(text.equals(activity))
				{
					driver.findElement(By.cssSelector(cssselector)).click();
					Thread.sleep(10000);
					break;
				}
			}
		}
		
		
	//Perform a Student Search using Username, Course Name, Activity
		public void StudentSearchAndActivity(String username , String coursename , String activity) throws InterruptedException
		{
			driver.navigate().refresh();
			ClickOnCourseButton(coursename);
			Thread.sleep(4000);
			Username.clear();
			Username.sendKeys(username);
			Search.click();
			Thread.sleep(4000);
			ClickOnButtonForUserRecordActivity(activity);
			Thread.sleep(4000);
		}
		
	
	//Progress - Select Unit and marking as Completed
		public void Progress_SelectUnitAndMarkAsComplete(int unit) throws InterruptedException
		{
			String cssselector = "#red li:nth-of-type(" + unit+ ") a";
			driver.findElement(By.cssSelector(cssselector)).click();
			Thread.sleep(5000);

			driver.findElement(By.cssSelector("div.clear a")).click();
			Thread.sleep(10000);
		}
		

	//Progress - Select All Unit and marking as Completed
		public void Progress_SelectAllUnitAndMarkAsComplete() throws InterruptedException
		{
			String cssselector = "#treecontrol a:nth-of-type(3)";
			driver.findElement(By.cssSelector(cssselector)).click();
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("div.clear a")).click();
			Thread.sleep(10000);
		}
		
		
	//Quizzes  - Set all answers correct for a Quiz 
		public void Quizzes_PassAQuiz(int number) throws InterruptedException
		{
			int i = number+2;
			String cssselector = "table tbody tr:nth-of-type(" + i+ ") a";
			driver.findElement(By.cssSelector(cssselector)).click();
			Thread.sleep(10000);
		}
		
	
	//Final Exam  - Set all answers correct 
		public void FinalExam_Pass() throws InterruptedException
			{
				String cssselector = "table tbody tr:nth-of-type(3) a";
				driver.findElement(By.cssSelector(cssselector)).click();
				Thread.sleep(5000);

			}
		
	//Final Exam  - Set all answers correct for boat-ed- Canada
		public void FinalExam_Pass_Canada() throws InterruptedException
				{
					String cssselector = "div.clear a:nth-of-type(5)";
					driver.findElement(By.cssSelector(cssselector)).click();
					Thread.sleep(5000);

				}
			
		
	//Payment Codes - Create new Payment code , type=Pass
		public String PaymentCodes_New_Type_Pass() throws InterruptedException
			{	
				driver.get(Constants.AdminURL + "/admin/paymentcodes");
				Thread.sleep(5000);

				//Clicking on 'Create New Payment Code' button
				driver.findElement(By.cssSelector("div.clear a")).click();
				Thread.sleep(5000);

				((JavascriptExecutor) driver).executeScript ("document.getElementById ('valid_begin_date').removeAttribute('readonly',0);");
				driver.findElement(By.id("valid_begin_date")).sendKeys("12/01/2014");
				
				((JavascriptExecutor) driver).executeScript ("document.getElementById ('valid_end_date').removeAttribute('readonly',0);");
				driver.findElement(By.id("valid_end_date")).sendKeys("10/06/2020");
				//Entering required information and creating new code
				String paymentcode = "passcode" + JH.GenerateRandomNumber();
				driver.findElement(By.id("payment_code")).sendKeys(paymentcode);
				driver.findElement(By.id("paymentcode_desc")).sendKeys("Added For Testing");
				
				new Select(driver.findElement(By.id("coupon_type"))).selectByVisibleText("PASS");
				
				
			
				driver.findElement(By.id("max_uses")).sendKeys("1");
				
				driver.findElement(By.cssSelector("div.clear a")).click();
				Thread.sleep(5000);

				return paymentcode;
				
			}
		
		
	//Payment Codes - Create new Payment code , type=Percentage
		public String PaymentCodes_New_Type_Percentage(String showbanner,String course, String globalbanner) throws InterruptedException
			{	
				driver.get(Constants.AdminURL + "/admin/paymentcodes");
				
				Thread.sleep(5000);
				
				driver.findElement(By.cssSelector("div.clear a")).click();
				Thread.sleep(5000);
				if(showbanner.equalsIgnoreCase("Yes"))
				{
					driver.findElement(By.cssSelector("input[name='PaymentCode[show_banner]'][value='1']")).click();
				}
				Thread.sleep(4000);
				
				//Override Global Banner = Y
				if(globalbanner.equalsIgnoreCase("yes"))
				{
					driver.findElement(By.cssSelector("input[name='PaymentCode[override_global_banner]'][value='1']")).click();
				}
				Thread.sleep(4000);

				((JavascriptExecutor) driver).executeScript ("document.getElementById ('valid_begin_date').removeAttribute('readonly',0);");
				driver.findElement(By.id("valid_begin_date")).sendKeys("12/01/2014");
				
				((JavascriptExecutor) driver).executeScript ("document.getElementById ('valid_end_date').removeAttribute('readonly',0);");
				driver.findElement(By.id("valid_end_date")).sendKeys("10/06/2020");
				//Entering required information and creating new code
				String paymentcode = "percentagecode" + JH.GenerateRandomNumber();
				driver.findElement(By.id("payment_code")).sendKeys(paymentcode);
				driver.findElement(By.id("paymentcode_desc")).sendKeys("Added For Testing");
				
				new Select(driver.findElement(By.id("course_id"))).selectByVisibleText(course);
				new Select(driver.findElement(By.id("coupon_type"))).selectByVisibleText("PERCENT_DISCOUNT");
				
				driver.findElement(By.id("percent_discount")).sendKeys("50");
				
			
			
				driver.findElement(By.id("max_uses")).sendKeys("1");
				
			
				
				driver.findElement(By.cssSelector("div.clear a")).click();
				Thread.sleep(5000);
				return paymentcode;
			}
		
		
	//Payment Codes - Create new Payment code , type=Absolute
		public String PaymentCodes_New_Type_Absolute(String startdate, String enddate ,String course, String globalbanner) throws InterruptedException
			{	
				driver.get(Constants.AdminURL + "/admin/paymentcodes");
				Thread.sleep(5000);

				driver.findElement(By.cssSelector("div.clear a")).click();
				Thread.sleep(5000);

				driver.findElement(By.cssSelector("input[name='PaymentCode[show_banner]'][value='1']")).click();
				Thread.sleep(5000);

				if(globalbanner.equalsIgnoreCase("yes"))
				{
					driver.findElement(By.cssSelector("input[name='PaymentCode[override_global_banner]'][value='1']")).click();
					Thread.sleep(5000);
				}
				Thread.sleep(2000);
				String paymentcode = "absolutecode" + JH.GenerateRandomNumber();
				System.out.println(paymentcode);
				((JavascriptExecutor) driver).executeScript ("document.getElementById ('valid_end_date').removeAttribute('readonly',0);");
				driver.findElement(By.id("valid_end_date")).sendKeys(enddate);
				Thread.sleep(4000);

				((JavascriptExecutor) driver).executeScript ("document.getElementById ('valid_begin_date').removeAttribute('readonly',0);");
				driver.findElement(By.id("valid_begin_date")).sendKeys(startdate);
				Thread.sleep(4000);
				
			
				new Select(driver.findElement(By.id("course_id"))).selectByVisibleText(course);
				new Select(driver.findElement(By.id("coupon_type"))).selectByVisibleText("ABSOLUTE_DISCOUNT");
				driver.findElement(By.id("absolute_discount")).sendKeys("10");
			
				Thread.sleep(4000);
	

				driver.findElement(By.id("max_uses")).sendKeys("1");
				driver.findElement(By.id("payment_code")).sendKeys(paymentcode);
				driver.findElement(By.id("paymentcode_desc")).sendKeys("Added For Testing");
				
				driver.findElement(By.id("paymentcode_desc")).sendKeys("Summer");

				
				Thread.sleep(4000);

			
				driver.findElement(By.cssSelector("div.clear a")).click();
				Thread.sleep(5000);
				return paymentcode;
			}
		
		
	//Payment Codes - Search for added Payment code & Edit it 
	   public void PaymentCodes_Search_Edit(String Name, String PaymentCode, String CouponType , String PercentDiscount, String StartDate , String EndDate) throws InterruptedException
			{	
				driver.get(Constants.AdminURL + "/admin/paymentcodes");
				Thread.sleep(5000);
				//Finding already added Payment code and clicking on Edit link
				for(int i=2 ;i<1000 ; i++)
				{
					String cssSelector = "table tbody tr:nth-of-type(" + i + ") td:nth-of-type(2)";
					if(driver.findElement(By.cssSelector(cssSelector)).getText().equalsIgnoreCase(Name))
					{
						cssSelector = "table tbody tr:nth-of-type(" + i + ") td:nth-of-type(8) a";
						driver.findElement(By.cssSelector(cssSelector)).click();
						Thread.sleep(5000);
						break;
					}
				}
				
				
				//Entering required information and Editing code
				driver.findElement(By.id("payment_code")).clear();
				driver.findElement(By.id("payment_code")).sendKeys(PaymentCode);
				
				new Select(driver.findElement(By.id("coupon_type"))).selectByVisibleText(CouponType);
				
				driver.findElement(By.id("percent_discount")).clear();
				driver.findElement(By.id("percent_discount")).sendKeys(PercentDiscount);
				
				((JavascriptExecutor) driver).executeScript ("document.getElementById ('valid_begin_date').removeAttribute('readonly',0);");
				driver.findElement(By.id("valid_begin_date")).clear();
				driver.findElement(By.id("valid_begin_date")).sendKeys(StartDate);
				Thread.sleep(5000);
				
				((JavascriptExecutor) driver).executeScript ("document.getElementById ('valid_end_date').removeAttribute('readonly',0);");
				driver.findElement(By.id("valid_end_date")).clear();
				driver.findElement(By.id("valid_end_date")).sendKeys(EndDate);
	
				driver.findElement(By.cssSelector("div.clear a")).click();
				Thread.sleep(5000);
			}	

	   
	 //Payment Codes - Search for added Payment code & Delete it 
	   public void PaymentCodes_Search_Delete(String PaymentCode) throws InterruptedException
			{	
				driver.get(Constants.AdminURL + "/admin/paymentcodes");
				Thread.sleep(5000);
				//Finding already added Payment code and clicking on delete link
				for(int i=2 ;i<1000 ; i++)
				{
					String cssSelector = "table tbody tr:nth-of-type(" + i + ") td:nth-of-type(1)";
					if(driver.findElement(By.cssSelector(cssSelector)).getText().equalsIgnoreCase(PaymentCode))
					{
						cssSelector = "table tbody tr:nth-of-type(" + i + ") td:nth-of-type(8) a:nth-child(2)";
						driver.findElement(By.cssSelector(cssSelector)).click();
						Thread.sleep(5000);
						break;
					}
				}
				
			}
	   
	   
	 //Payment Codes - Search for added Payment code & Edit it and set Show Banner = NO 
	   public void PaymentCodes_Search_Edit_Set_Banner_No(String PaymentCode) throws InterruptedException
			{	
				driver.get(Constants.AdminURL + "/admin/paymentcodes");
				Thread.sleep(5000);
				//Finding already added Payment code and clicking on delete link
				for(int i=2 ;i<1000 ; i++)
				{
					String cssSelector = "table tbody tr:nth-of-type(" + i + ") td:nth-of-type(1)";
					if(driver.findElement(By.cssSelector(cssSelector)).getText().equalsIgnoreCase(PaymentCode))
					{
						cssSelector = "table tbody tr:nth-of-type(" + i + ") td:nth-of-type(8) a";
						driver.findElement(By.cssSelector(cssSelector)).click();
						Thread.sleep(5000);
						break;
					}
				}
				
				driver.findElement(By.cssSelector("input[name='PaymentCode[show_banner]'][value='0']")).click();
				Thread.sleep(5000);
				driver.findElement(By.cssSelector("div.clear a")).click();
				Thread.sleep(5000);
			
				
			}	
	   
	 //Payment Codes - Get 'Uses' for a payment code
	   public String PaymentCodes_GetUses_Info(String PaymentCode) throws InterruptedException
			{	
				driver.get(Constants.AdminURL + "/admin/paymentcodes");
				Thread.sleep(5000);
				//Finding already added Payment code and clicking on Edit link
				
				String cssSelector = "";
				int i=0;
				
				for(i=2 ;i<1000 ; i++)
				{
					cssSelector = "table tbody tr:nth-of-type(" + i + ") td:nth-of-type(1)";
					if(driver.findElement(By.cssSelector(cssSelector)).getText().equalsIgnoreCase(PaymentCode))
					{
						break;
					}
				}
				
				cssSelector = "table tbody tr:nth-of-type(" + i + ") td:nth-of-type(7)";
				return driver.findElement(By.cssSelector(cssSelector)).getText().trim();						
				
				
			}	
 
	
	
		
}
		
		
		
	


