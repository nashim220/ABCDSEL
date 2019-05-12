package com.TestScript;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.TestPage.Category;
import com.TestPage.GCPage;
import com.TestPage.LoginPage;
import com.Utility.Browser;
import com.Utility.Constants;
import com.Utility.SeleniumFunctions;

	public class DemoTest {
				
			LoginPage objlog;	
			Category obCategory;	
			SeleniumFunctions obSelfun;
			static WebDriver driver;
			public static String strTestCaseName = "Add details at GPC Page and save";
			
			@BeforeTest
		    public   void setup() throws Exception {
				
				driver = Browser.getBrowser("chrome");
				driver.get(Constants.ApplicationURL);	    
			    driver.manage().window().maximize();
			    
				}
			
			 @Test
			 public void ValidateGCDetails(){
				 
				 System.out.println("====" + "\n" +	"Test  : Verify 'GC' Page is displayed Run"  + "\n" +"====");
				 Reporter.log("====" + "\n" + "Test  : Verify 'GC' Page is displayed Run"  + "\n" + "====");	
				
						
				 objlog=new LoginPage(driver);
				 obCategory=new Category(driver);
				 obSelfun =new SeleniumFunctions(driver);				 
				 GCPage objGCPage=new GCPage(driver);
				 System.out.println("Step 1 : Navigate to Login page : " + "/login");
				 Reporter.log("Step 1 : Navigate to Login page : " + "/login"); 
				 
				 objlog.loginToApp(Constants.CM_Username,Constants.CM_Password);
				 
				 System.out.println("Step 1 : Login successfully logged : " );
				 Reporter.log("Step 1 : Login successfully logged : " );
				 	
				 
				 System.out.println("Step 2 : Navigate to Category page : " );
				 Reporter.log("Step 2 : Navigate to Category page : "); 
				
			      
				 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				 driver.get(Constants.GCURL);
				 
				 objGCPage.ClickOnGCTab();				 
				 
				 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				 //driver.findElement(By.id("ddllr_colled_at2area']")).click();
				 //driver.findElement(By.name("ddllr_coll_typ2coll_typ")).click();
				 //JavascriptExecutor js = (JavascriptExecutor) driver;
				// String script = "return document.getElementById('txtremarks');";
				 //WebElement exampleDiv = (WebElement) js.executeScript(script);
				 //exampleDiv.sendKeys("abc");
				 //river.findElement(By.id("txtremarks")).sendKeys("abc");
				//driver.findElement(By.xpath("//span[text()='Special Remarks']")).getText();
				driver.findElement(By.xpath("//*[@id='txtremarks']")).sendKeys("enter name id");
				//driver.findElement(By.id("txtremarks")).sendKeys("nashim");
			 }
			@AfterMethod
			public void teardown(){
				
				//driver.close();
			}
			
			
			
}
