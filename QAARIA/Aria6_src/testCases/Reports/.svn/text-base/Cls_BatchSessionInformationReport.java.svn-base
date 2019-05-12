
/*
 Author     		:	Madhavi JN
 Class Name			: 	Cls_BatchSessionInformationReport 
 Purpose     		: 	Purpose of this file is :
						1. To Compare and Validate the Application Batch Process UI and AriaBatchsession Report Data
						2. Test Class for the test case QAABE-84.
 
 Date       		:	01/19/2016
 
 PreCondition 		:	1. Role based Login required.
 						 						
 Test Steps 		:	1. Login using valid role credentials for accessing an account.
 						2. Navigate to Configurations->AuditLog->BatchProcess
 						3. Search with one day prior data.
 						4. Capture the data of the search displayed
 						5. Run->Reports->Standard->AriaBatchsession Report
 						6. Capture the Report Data
 						7. Compare both application and Report Data and display the Results.
 						
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/
package testCases.Reports;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import appModules.AriaLoginLogout;
import utility.EnvironmentDetails;
import utility.Log;
import utility.ReportUtils;
import utility.Utils;
import utility.VerificationMethods;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.BatchProcessorReportObjects;
import pageObjects.UpdatePlanUnitByAPI;

public class Cls_BatchSessionInformationReport extends VerificationMethods{
	
public static WebDriver driver;
	
public static String strTestCaseName = "Cls_BatchSessionInformationReport";
	

	@BeforeClass
	 public static void beforeMethod()throws Exception 
	 {	
			DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
			//TODO: Set Chrome driver as the driver to launch the test.
			driver = utility.Utils.fn_SetChromeDriverProperties();
			EnvironmentDetails objEnv = new EnvironmentDetails();
			AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
	 }	


	@SuppressWarnings({ "rawtypes"})
	@Test
	public void BatchSessionInformationReport() throws Exception
	{  
			try
			{
		    WebDriverWait webWait;
		    webWait = new WebDriverWait(driver,1000);
		    Log.startTestCase("Cls_BatchSessionInformationReport");
		    String strPastDate = Utils.fn_getCustomizedPreviousDate(-1);
		    HashMap<String, String> hashMapApplData= new HashMap<String, String>();
		    HashMap<String, String> hashMapReportData= new HashMap<String, String>();
		    boolean blnstatus=false;
	
		   //Create Objects
		    BatchProcessorReportObjects objPage = new BatchProcessorReportObjects();
		    //Objects for handling calendar controls
		    UpdatePlanUnitByAPI objPlanUnit = new UpdatePlanUnitByAPI();
			//Objects for Accessing Billing Report
		    ReportUtils objReport = new ReportUtils();
			//TODO :: click on configuration Link
			objPage.fn_clickConfiguration(driver, webWait).click();
			//TODO:: click on AuditLogs Link
			objPage.fn_clickAuditLogs(driver, webWait).click();
			//TODO:: Click on Batch Processes link 
			objPage.fn_clickBatchProcesses(driver, webWait).click();
			//TODO:: Set the From and To dates for filtering the Batch Processes
			objPlanUnit.fn_clickRequestFromDate(driver, webWait).clear();
			objPlanUnit.fn_clickRequestFromDate(driver, webWait).sendKeys(strPastDate);
			objPlanUnit.fn_clickRequestToDate(driver, webWait).clear();
			objPlanUnit.fn_clickRequestToDate(driver, webWait).sendKeys(strPastDate);
			objPlanUnit.fn_clickRequestFromDate(driver, webWait).click();
			Thread.sleep(1500);
			objPlanUnit.fn_setRequestFromDateHour(driver, webWait).clear();
			objPlanUnit.fn_setRequestFromDateHour(driver, webWait).sendKeys("0");
			objPlanUnit.fn_setRequestFromDateMinute(driver, webWait).clear();
			objPlanUnit.fn_setRequestFromDateMinute(driver, webWait).sendKeys("0");
			objPlanUnit.fn_clickRequestDateTimeDone(driver, webWait).click();
			objPlanUnit.fn_clickRequestToDate(driver, webWait).click();
			Thread.sleep(1500);
			objPlanUnit.fn_setRequestToDateHour(driver, webWait).clear();
			objPlanUnit.fn_setRequestToDateHour(driver, webWait).sendKeys("23");
			objPlanUnit.fn_setRequestToDateMinute(driver, webWait).clear();
			objPlanUnit.fn_setRequestToDateMinute(driver, webWait).sendKeys("59");
			objPlanUnit.fn_clickRequestDateTimeDone(driver, webWait).click();
			Thread.sleep(1500);
			//TODO :: click on Search Button
			objPage.fn_clickSearch(driver, webWait).click();
			Thread.sleep(3500);
			//Take a screenshot after entering the search time for Batch Processes
			Utils.takeScreenshot(driver, strTestCaseName);
			String strProcessName = null;
			String strDate = null;
			String strTime = null;
			String status = null;
			String strformateddate=null;
			String strBeforeTime=null;
			String strAfterTIme =null;
			Integer inttime=0;
	       
			//TODO:: Get the Batch Process table and capture the required entries
			WebElement tblBatchProcesses = objPage.fn_getDataTable(driver, webWait).findElement(By.tagName("tbody"));
			
			List<WebElement> TotalRowCount = tblBatchProcesses.findElements(By.tagName("tr"));
			
			for(WebElement rows: TotalRowCount)
			{
				List<WebElement> cols = rows.findElements(By.tagName("td"));
				
				if(cols.size() != 0)
				{
					strProcessName = cols.get(0).getAttribute("innerText").toString().trim();
					strDate = cols.get(1).getAttribute("innerText").toString().trim();
					strformateddate = strDate.replace("20","");
					strTime = cols.get(2).getAttribute("innerText").toString().trim();
					strBeforeTime = strTime.substring(0,2);
					strAfterTIme = strTime.substring(2,8);
					inttime = Integer.parseInt(strBeforeTime);
					if (inttime>12)
					{
						inttime=inttime-12;
						if (inttime<10)
						{
							strTime="0"+inttime+strAfterTIme;
						}
					}
						
					status = cols.get(3).findElement(By.tagName("div")).getAttribute("title").toString().trim();
					strProcessName=strProcessName+strTime;
					if (status.contains("Error"))
					{
						status="Failed with fatal error";
					}
					else
					{
						status="Completed, no errors";
					}
					strformateddate = strformateddate+status;
					//putting all the captured contents to hashmap
					hashMapApplData.put(strProcessName, strformateddate);
					
				 }
		    }
		
		   //TODO:: click on Analytics and Reporting Link
			objPage.fn_clickAnalyticsReporting(driver, webWait).click();
			Thread.sleep(1500);
			//TODO:: passing the arguments to generated the report
			strPastDate=Utils.fn_getDateFormatted(strPastDate,"yyyy-mm-dd");
			Thread.sleep(100);
    	   //TODO:: Generate Report using Calendar dates instead of drop downs
		    objReport.fn_GenerateReportCalendarDate(driver, webWait, "Standard", "Aria batch session information", strPastDate, strPastDate);
		    Thread.sleep(3500);
		    Utils.takeScreenshot(driver, strTestCaseName);
			//TODO:: Read the Contents of the Report
			WebElement webReadContents=objReport.fn_ReadGeneratedReport(driver);
			List<WebElement> rows = webReadContents.findElements(By.tagName("tr"));
			
			String strCellData;
			String strBatchProcessorName=null;
			String strTimestamp=null;
			String strReportStatus=null;
			int intRowCnt = 0;
	        //captured all the Required Report contents
			for(WebElement row: rows)
			{					
				List<WebElement>cells = row.findElements(By.tagName("td"));
				
				int intColCnt = 0;
				int column = 0;
				if (intRowCnt>2)
				{
						for(WebElement cell : cells)
						{
							strCellData = cell.getText();
						
								if (column == 3){
									strBatchProcessorName = strCellData;	
								}
								
								if (column == 5){
									strTimestamp = strCellData.substring(0,strCellData.length()-3);
									strTimestamp = strTimestamp.replace(".",":");
									String[] split = strTimestamp.split(" ");
									strBatchProcessorName = strBatchProcessorName+split[1];
									strTimestamp = split[0];
								}
								
								if (column == 7){
									strReportStatus = strCellData.toString().trim();	
									strReportStatus=strTimestamp+strReportStatus;
								}
									
								intColCnt = intColCnt + 1;
								column++;
						}
						//putting all the captured contents to hashmap
						hashMapReportData.put(strBatchProcessorName,strReportStatus);
				  }	
	           	
				intRowCnt = intRowCnt + 1;
			}
				
			Thread.sleep(1500);
			
			driver.switchTo().defaultContent();	
			driver.findElement(By.xpath("//button[@title='close']")).click();
			blnstatus=true;
		
			 Set sethmApp = hashMapApplData.entrySet();		  
			 Iterator itrhmApp = sethmApp.iterator();
			  
			  while (itrhmApp.hasNext())
			  {
				  Map.Entry mentryApp = (Map.Entry)itrhmApp.next();
					
				  Object objAppKey = mentryApp.getKey();
				  String strAppKey = objAppKey.toString();
				
				//Comparing HashMap Entries  
				  if (hashMapReportData.containsKey(objAppKey)){
				
					  	if (hashMapReportData.get(objAppKey).toString().trim().equalsIgnoreCase(mentryApp.getValue().toString().trim())){
					  		Log.info("Key and Value of Application are matching with Report :: "+"Key="+mentryApp.getKey()+"::"+"Value="+mentryApp.getValue());
							Log.info("=========================================================================");
					  	}
					  	else
			            {
			            	Log.info("Key and Value of Application are not matching with Report :: "+"Key="+mentryApp.getKey());
			            	Log.info("Application Value :"+"Value="+mentryApp.getValue());
			            	Log.info("Report Value :"+"Value="+hashMapApplData.get(objAppKey));
			                Log.info("============================================================================");
			            }
				  }
				  else{
					  Log.info("Application key does not exist in Report  ::"+ strAppKey);
					  Log.info("============================================================================");
					  Reporter.log("Application key does not exist in Report  ::"+ strAppKey);
				  }
				
			  }
			  
			//final marker for deciding the status of the execution.
			if(blnstatus == false)
				assertTrue(false,"Error: The verification of 'ARIA Batch Session Information Report' is failed !");
			else
				Log.info("The verification of 'ARIA Batch Session Information Report' is passed!");
			
		  }
		catch(Exception e)
		{
			e.printStackTrace();
		}
			  
			  
	   }
		
		@AfterTest
		public void afterMethod() throws Exception
		 {
			 //Logs out of the application
			 AriaLoginLogout.appLogout(driver); 
			 //Quitting the driver
			 driver.quit(); 
		 }
			


	
	}
