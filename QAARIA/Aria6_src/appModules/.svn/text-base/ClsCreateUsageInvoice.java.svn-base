package appModules;
import pageObjects.ChangeDeleteClientDefinedFields;
import pageObjects.SupplimentalPlans;
import pageObjects.UsagePageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.EnvironmentDetails;
import utility.Log;
import utility.VerificationMethods;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClsCreateUsageInvoice {
	
	static ClsCreateUsageInvoice objUsageFeilds = new ClsCreateUsageInvoice();
	 SupplimentalPlans Objplan = new SupplimentalPlans();
		
     public void fn_Usage(WebDriver driver,WebDriverWait webWait,String acct_num,String Fromdate) throws InterruptedException
     {
   	 	 
    	 UsagePageObjects objUsage = new UsagePageObjects();
    	 //Click on Accounts Link
    	 //objUsage.fn_AcctNumLink(driver, webWait, acct_num).click();
    	 //Thread.sleep(1000);
    	 objUsage.fn_ClickUsage(driver, webWait).click();
    	 Thread.sleep(1000);
    	 objUsage.fn_EnterFromDate(driver, webWait).sendKeys(Fromdate);
         driver.findElement(By.name("inStartDateString")).sendKeys(Keys.TAB);
    	 Thread.sleep(1000);
    	 objUsage.fn_ClickRetriveUsageBtn(driver, webWait).click();
      } 
	
     public void fn_GenerateInvoice(WebDriver driver,WebDriverWait webWait,String acct_num,String Fromdate,String Todate) throws InterruptedException
     {
    	 
    	 
    	 UsagePageObjects objUsage = new UsagePageObjects();
    	 //Click on Accounts Link
    	 objUsage.fn_ClickAccountsLink(driver, webWait).click();
    	 objUsage.fn_ClickSearchLink(driver, webWait).click();
    	 objUsage.fn_ClickAdhocSearchLink(driver, webWait).click();
    	 objUsage.fn_EnterSearchValue(driver, webWait).sendKeys(acct_num);
    	 objUsage.fn_ClickSearchBtn(driver, webWait).click();
    	 objUsage.fn_AcctNumLink(driver, webWait, acct_num).click();
    	 Thread.sleep(1000);
    	 objUsage.fn_ClickUsage(driver, webWait).click();
    	 Thread.sleep(1000);
    	 objUsage.fn_EnterFromDate(driver, webWait).sendKeys(Fromdate);
         driver.findElement(By.name("inStartDateString")).sendKeys(Keys.TAB);
    	 Thread.sleep(1000);
    	 Thread.sleep(1000);
         driver.findElement(By.name("inEndDateString")).sendKeys(Keys.ENTER);
    	 Thread.sleep(1000);
    	 //driver.findElement(By.name("inEndDateString")).clear();
    	 //driver.findElement(By.name("inEndDateString")).click();
    	 Thread.sleep(1000);
    	 //objUsage.fn_EnterToDate(driver, webWait).sendKeys(Todate);
    	 Thread.sleep(1000);
    	 objUsage.fn_ClickRetriveUsageBtn(driver, webWait).click();
    	 Thread.sleep(1000);
    	 objUsage.fn_ClickStmtsInvoiceslnk(driver, webWait).click();
    	 Thread.sleep(5000);
    	 objUsage.fn_ClickInvoiceslnk(driver, webWait).click();
    	 Thread.sleep(1000);
    	 objUsage.fn_ClickGenInvoiceslnk(driver, webWait).click();
    	 Thread.sleep(1000);
    	 objUsage.fn_GenerateInvoiceBtn(driver,webWait).click();
      }


     public void EditSupplementalplan(WebDriver driver,WebDriverWait webWait) throws InterruptedException
         {
    	 
    	 Objplan.fn_PlansServices(driver, webWait).click();
    	//TODO : Click on Supplemental Plan tab
    	 Objplan.fn_SupplementalPlans(driver, webWait).click();
    	 //TODO : Click on Edit Plan Units Link
    	 Objplan.fn_EditPlanUnits(driver, webWait).click();
    	 //TODO : enter the number of new plan units
    	 Objplan.fn_NewPlanUnits(driver, webWait).clear();
    	 Thread.sleep(1000);
    	 Objplan.fn_NewPlanUnits(driver, webWait).sendKeys("5");
    	 Thread.sleep(1000);
    	 //TODO : Select the assignment scope
    	 Select scope = new Select(driver.findElement(By.xpath("//*[@id=\"inAssignOptionsIgnore\"]")));
    	 scope.selectByVisibleText("Assign on Anniversary");
    	 Thread.sleep(500);
    	//TODO : Click on save changes button
    	 Objplan.fn_SaveChanges(driver, webWait).click();
         Thread.sleep(500);
	    //TODO : Click on save plan button
         Objplan.fn_SavePlan(driver, webWait).click(); 
         Thread.sleep(500); 	    	 
	     }

	}

	

                                                           
