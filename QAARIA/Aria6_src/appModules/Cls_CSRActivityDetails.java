package appModules;
import java.util.List;
import java.util.Set;

import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CSRActivity;

public class Cls_CSRActivityDetails {
	
	CSRActivity objCSRActivity = new CSRActivity();
	
	public void fn_CSRReportsNavigation(WebDriver driver,WebDriverWait webWait){
		
		try {
			//
			//webWait.until(ExpectedConditions.elementToBeClickable(objCSRActivity.fn_DefaultBtn(webWait)));
			//Click link Analytics and Reporting
			objCSRActivity.fn_AnalyticsReportingLink(webWait).click();
			Thread.sleep(100);
			//Click link Reports
			objCSRActivity.fn_ReportsLink(webWait).click();
			Thread.sleep(100);
			//Click link CSR Activity
			objCSRActivity.fn_CSRActivityLink(webWait).click();
			
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fn_CSRReports(WebDriver driver,WebDriverWait webWait, String strCSR) throws InterruptedException{
		//Switches the frame
		//webWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
		Thread.sleep(1000);
		
		try {
			//Select CSR value
			objCSRActivity.fn_CSRList(driver, webWait).selectByVisibleText(strCSR);
			
			System.out.println("Name Selected as : "+strCSR);
			
			//Select From Date
			driver.findElement(By.name("fromBeginning")).click();
			Thread.sleep(100);
						
			//Select To Date
			driver.findElement(By.name("untilToday")).click();
			
			//click on Search button
			objCSRActivity.fn_ActivityDetailsList(driver, webWait).selectByVisibleText("All Activity");
			
			Thread.sleep(50);
			//Click on Search button
			objCSRActivity.fn_SearchBtn(webWait).click();
			
			
			
			
		} 
			
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void fn_CSRReports1(WebDriver driver,WebDriverWait webWait, String strCSR, String strFromDate, String strToDate){
		//Switches the frame
		webWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
		
		try {
			//Select CSR value
			objCSRActivity.fn_CSRList(driver, webWait).selectByVisibleText(strCSR);
			
			System.out.println("Name Selected as : "+strCSR);
			
			//Select From Date
			objCSRActivity.fn_FromDate(webWait).click();
			Thread.sleep(100);
			int intPosFrom = strFromDate.indexOf("-");
			String strDateFrom = strFromDate.substring(0,intPosFrom); 
			System.out.println("strDate generated : "+ strDateFrom);
			
			//Switch to calender window`
			fn_SelectDate(driver,strDateFrom);
			
			Thread.sleep(20);	
			Set <String> AllWindows = driver.getWindowHandles();
			String window1 = (String)AllWindows.toArray()[0];
			System.out.println("window 1 : "+window1);
							
			driver.switchTo().window(window1);
			
			driver .manage().window().maximize();
			System.out.println("Switched to window ");
			
			
			//Select To Date
			objCSRActivity.fn_ToDate(webWait).click();
			Thread.sleep(100);
			int intPosTo = strToDate.indexOf("-");
			String strDateTo = strToDate.substring(0,intPosTo); 
			System.out.println("strDate generated : "+ strDateTo);
			
			
			//Switch to calender window`
			fn_SelectDate(driver,strDateTo);
			
			//click on Search button
			objCSRActivity.fn_ActivityDetailsList(driver, webWait).selectByVisibleText("All Activity");
			
			//Click on Search button
			objCSRActivity.fn_SearchBtn(webWait).click();
			
			
		} 
			
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int fn_SelectMonthYear(WebDriver driver, String strMonth){
		
		if(strMonth.contains("June 2015")){
			
			System.out.println("Valid Month is selected");	
			return 1;
		}	
		else{
			
			driver.findElement(By.xpath("//img[@alt='previous month']")).click();
			WebElement table = driver.findElement(By.tagName("table"));
			List<WebElement>columns = table.findElements(By.tagName("td"));
			strMonth = columns.get(1).getText();
			System.out.println("Newly picked month : "+strMonth);
			fn_SelectMonthYear(driver,strMonth); 
			return 0;
			
		}
		
	}
	
	
	public void fn_SelectDate(WebDriver driver,String strDate) throws InterruptedException{
		
		Set <String> AllWindows = driver.getWindowHandles();
		String window1 = (String)AllWindows.toArray()[0];
		
		System.out.println("window 1 : "+window1);
		String window2 = (String)AllWindows.toArray()[1];
		System.out.println("window 2 : "+window2);
		driver.switchTo().window(window2);
		
		System.out.println("Switched to the calender window");
					
		WebElement table = driver.findElement(By.tagName("table"));
		List<WebElement>columns = table.findElements(By.tagName("td"));
		System.out.println("Total Columns:" + columns.size());
						
		String strMonth = columns.get(1).getText();
		System.out.println("Month : "+strMonth);
		
		//Select month and year
		fn_SelectMonthYear(driver,strMonth);
		//
		//Select Active Window
		Set <String> Windows = driver.getWindowHandles();
		
		String wind1 = (String)Windows.toArray()[0];
		System.out.println("window 1 : "+wind1);
		
		String wind2 = (String)Windows.toArray()[1];
		System.out.println("window 2 : "+wind2);
						
		driver.switchTo().window(wind2);
		//driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		
		WebElement table1 = driver.findElement(By.tagName("table"));
		List<WebElement>columns1 = table1.findElements(By.tagName("td"));
		System.out.println("Total Columns:" + columns1.size());
		
		
		System.out.println("Test Date : "+strDate);
		
		for(int i=0;i<1;i++){
			System.out.println("Row no is : "+i);
			System.out.println("");
			for (int j=0;j<columns1.size();j++){
				String strDt = columns1.get(j).getText();
				//strDate = "10";
				
				System.out.println("application Date : "+strDt);
				if (strDt.equals(strDate)){
					driver.findElement(By.linkText(strDt)).click();
					Thread.sleep(10);
					//driver.switchTo().window(wind1);
					break;
				}
			}
				
		}
		
	
		
	}
	
}
