package com.TestScript;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.TestPage.Category;
import com.TestPage.Dashboard;
import com.TestPage.LoginPage;
import com.Utility.Browser;
import com.Utility.Constants;
import com.Utility.SeleniumFunctions;

public class LoginTest {
	
	//WebDriver driver;
	LoginPage objlog;	
	Category obCategory;	
	SeleniumFunctions obSelfun;
	static WebDriver driver;
	public static String strTestCaseName = "Add details at GPC Page and save";
	@BeforeTest
    public   void setup() throws Exception {
		
		driver = Browser.getBrowser("chrome");
		driver.get(Constants.ApplicationURL);
	    //driver.get("http://158.69.241.98:36");
	    driver.manage().window().maximize();
	    
		}
	
	 @Test
	 public void LoginToApplication(){
		 
		 System.out.println("====" + "\n" +	"Test 1 : Verify 'GCP' Page is displayed Run"  + "\n" +"====");
		 Reporter.log("====" + "\n" + "Test 1 : Verify 'GCP' Page is displayed Run"  + "\n" + "====");	
		
		 int AssertFailedCount=0 ;		
		 objlog=new LoginPage(driver);
		 obCategory=new Category(driver);
		 obSelfun =new SeleniumFunctions(driver);
		 Dashboard objDashboard=new Dashboard(driver);
		 //GCtestPage objGCtestPage=new GCtestPage(driver);
		 
		 System.out.println("Step 1 : Navigate to Login page : " + "/login");
		 Reporter.log("Step 1 : Navigate to Login page : " + "/login"); 
		 
		 objlog.loginToApp(Constants.CM_Username,Constants.CM_Password);
		 
		 System.out.println("Step 2 : Login successfully logged : " );
		 Reporter.log("Step 2 : Login successfully logged : " );
		 	 
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		 
		 obSelfun.SelectValueFromDropdownListUsingIndex("id", "ddlCompany", 1);
		 obSelfun.SelectValueFromDropdownListUsingIndex("id", "ddlDepartment", 1);
		 obSelfun.SelectValueFromDropdownListUsingIndex("id", "ddlBranch", 5);
		 obSelfun.SelectValueFromDropdownListUsingIndex("id", "ddlGodown", 0);
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 obSelfun.SelectValueFromDropdownList("id","ddlFinYear","2017-2018");
		 //obSelfun.SelectValueFromDropdownListUsingIndex("id", "ddlFinYear", 1);		 
		 obCategory.clickEnter();
		 
		 System.out.println("Step 3 : Susscessfully entered valuse in to Category page ' Passed' : " );
		 Reporter.log("Step 3 : Susscessfully entered valuse in to Category page ' Passed' : " );
	      
		 driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		 
		 
		 objDashboard.ClickOnNwew();
		

		 driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		//objDashboard.ClickOnTransactionEntry();
		 
		 Actions action = new Actions(driver);
		 WebElement we = driver.findElement(By.xpath("//div[contains(text(),'Transaction Entry')]"));
		 action.moveToElement(we).build().perform();
		 
		 
		 //objDashboard.ClickOnBookingEntry();
		 
		 WebElement BEntry = driver.findElement(By.xpath("//div[contains(text(),'Booking Entry')]"));
		 action.moveToElement(BEntry).build().perform();
		 
		 //objDashboard.ClickOnFavourate();
		
		 //objDashboard.ClickOnGCLRb();
		 
		 WebElement GC = driver.findElement(By.xpath("//div[contains(text(),'GC')]"));
		 action.moveToElement(GC).build().perform();
		 //objDashboard.ClickOnGCLRb();
		 //WebElement users = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'GC')]")));
		 objDashboard.GCLRb.click();
		 System.out.println("Step 4 : Susscessfully swich to DashBoard Page ' Passed' : " );
		 Reporter.log("Step 4: Susscessfully swich to DashBoard Page ' Passed' : " );
		 
		 
		    String ExpectedText = "PENDING FOR DELIVERY";
			String ActualText = objDashboard.Dashboarddetails();
			
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 if(ActualText.equalsIgnoreCase(ExpectedText))
			{
				
				System.out.println("Success !! Correct Validation is displayed for invalid DOB. i.e. " +ExpectedText);
				Reporter.log("Success !! Correct Validation is displayed for invalid DOB. i.e. " +ExpectedText);
			
			}
			else
			{
			
				System.out.println("Failure !! Incorrect Validation for invalid DOB. Expected :" + ExpectedText + "\n"+
								"Actual :" +ActualText);
				Reporter.log("Failure !! Incorrect Validation for invalid DOB. Expected :" + ExpectedText + "\n"+
						"Actual :" +ActualText); 
			
				AssertFailedCount++;
			}
			
		 //sysout	 
		 AssertFailedCount++;	 
		 if(AssertFailedCount>0)	
			{
				
				//Marking this test as Failed				
				System.out.println("---- Test Failed. Please check the console or TestNg report for details");
				Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
				SeleniumFunctions.captureScreenShot(driver);
				Assert.fail();
			} 
			 
		 }
	 
	 
	@AfterMethod
	public void teardown(){
		
		driver.close();
	}
	
}
