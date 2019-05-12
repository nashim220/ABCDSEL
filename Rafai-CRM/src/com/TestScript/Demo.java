package com.TestScript;

	import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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

	public class Demo {
			
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
			
			 int AssertFailedCount=0 ;		
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
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		 
			 obSelfun.SelectValueFromDropdownListUsingIndex("id", "ddlCompany", 1);
			 obSelfun.SelectValueFromDropdownListUsingIndex("id", "ddlDepartment", 1);
			 obSelfun.SelectValueFromDropdownListUsingIndex("id", "ddlBranch", 5);
			 obSelfun.SelectValueFromDropdownListUsingIndex("id", "ddlGodown", 0);
			 //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 obSelfun.SelectValueFromDropdownList("id","ddlFinYear","2017-2018");
			 //obSelfun.SelectValueFromDropdownListUsingIndex("id", "ddlFinYear", 1);		 
			 obCategory.clickEnter();		
			 
			 System.out.println("Step 2 : Susscessfully entered valuse in to Category page ' Passed' : " );
			 Reporter.log("Step 2 : Susscessfully entered valuse in to Category page ' Passed' : " );
			 
			 System.out.println("Step 3 : Navigate to GC page : " );
			 Reporter.log("Step 3 : Navigate to GC page : "); 
		      
			 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			 driver.get(Constants.GCURL);
			 
			 objGCPage.ClickOnGCTab();
			 
			 
			 driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			 
			WebElement web=driver.findElement(By.cssSelector("#ddllr_coll_typ2coll_typ"));
			 Select sel=new Select(web);
			 sel.selectByVisibleText("GDM COLLECTION");
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 //#ddllr_coll_typ2coll_typ
			 //obSelfun.SelectValueFromDropdownList("name","ddllr_coll_typ2coll_typ","GODOWN");
			 /*ddllr_coll_typ2coll_typ
			  * ddllr_coll_typ2coll_typ ddllr_coll_typ2coll_typ
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 obSelfun.SelectValueFromDropdownListUsingIndex("id","ddllr_colled_at2are",5);
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 obSelfun.SelectValueFromDropdownListUsingIndex("id", "ddllr_vehicle_type2app_elm", 5);
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 objGCPage.EnterSpecialRemarks(Constants.SpecialRemarks);
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 obSelfun.SelectValueFromDropdownListUsingIndex("id", "ddlinsured_by2app_elm", 2);
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 obSelfun.SelectValueFromDropdownListUsingIndex("id", "ddllr_tocity", 5);
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 objGCPage.EnterStateFormNo("2589");
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 obSelfun.SelectValueFromDropdownListUsingIndex("id", "chkdirectprint", 1);
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 objGCPage.ClickOnTransportMode();
			 objGCPage.ClickOnSelectTransportMode();
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 obSelfun.SelectValueFromDropdownListUsingIndex("id", "ddllr_type2app_elm", 1);
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 obSelfun.SelectValueFromDropdownListUsingIndex("id", "ddlinsurance_by2app_elm", 1);
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 objGCPage.ClickOnCCAttach();
			 objGCPage.EnterInnerQty("25"); 
			 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			 objGCPage.ClickOnsaveBtn();
			 
			 System.out.println("Step 3 : Susscessfully entered valuse in to Category page ' Passed' : " );
			 Reporter.log("Step 3 : Susscessfully entered valuse in to Category page ' Passed' : " );
			 
			 	String ExpectedText2 = "PENDING FOR DELIVERY";
				String ActualText2 = objGCPage.GetNoofRecord();
				System.out.println("ActualText="+ActualText);
				
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 if(ActualText.equals(ExpectedText))
				{
					
					System.out.println("Success !! Correct Validation is displayed for Categary. i.e. " +ExpectedText2);
					Reporter.log("Success !! Correct Validation is displayed for Categary. i.e. " +ExpectedText2);
				
				}
				else
				{
				
					System.out.println("Failure !! Incorrect Validation for Categary. Expected :" + ExpectedText2 + "\n"+
									"Actual :" +ActualText2);
					Reporter.log("Failure !! Incorrect Validation for Categary. Expected :" + ExpectedText2 + "\n"+
							"Actual :" +ActualText2);			
					AssertFailedCount++;
				}
			 
			 
			 
			 if(AssertFailedCount>0)	
				{
					
					//Marking this test as Failed				
					System.out.println("---- Test Failed. Please check the console or TestNg report for details");
					Reporter.log("---- Test Failed. Please check the console or TestNg report for details");
					SeleniumFunctions.captureScreenShot(driver);
					Assert.fail();
				} 		 
				 	 
			 
			 */
			    
			 }
		 
		 
		@AfterMethod
		public void teardown(){
			
			//driver.close();
		}
		
		
		
		

	}

