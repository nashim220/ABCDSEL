/*
 Author     		:	Namrata Akarte
 Class Name			: 	Cls_CSRActivityReport  
 Purpose     		: 	Purpose of this file is :
						1. To compare CSR Activity report for account and the details displayed in Reports-> CSR Activity
												
 Date       		:	08/22/2015,11/02/2015
 Version Information:	Version 2.0
 
 Jira #				:	QAABE-55
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be used should be populated in "TestCaseDetails" 
 						worksheet for excel "TestData.xlsx".

 Test Steps 		:	1. Login using valid role credentials for verifying the Configuration Payment Settings.
 						2. Navigate to the Accounts and change the custom defined field
 						3. Navigate to CSR Activity tab and capture the details
 						4. Navigate to Reports->CSR Activity and capture the result of CSR activity for CSR  who has done the changes 
 						from Step-1 to Step-3
 						5. Verify if the data in captured step 4 is present in the details captured in step3
 						
 Version changes2.0 :   updated chrome driver path 
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/
package testCases.Reports;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import pageObjects.CSRActivity;
import pageObjects.ChangeDeleteClientDefinedFields;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;
import appModules.AriaLoginLogout;
import appModules.Cls_CSRActivityDetails;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_CSRActivityReport extends VerificationMethods{
	
	public WebDriver driver;
	
	@BeforeClass
	public void beforeMethod()throws Exception
	{	
		DOMConfigurator.configure("log4j.xml");
		
		//Set Chrome driver
		System.setProperty("webdriver.chrome.driver",chromeDriverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.startTestCase("Login to application");
		//This parameters are taken from Exec file and its reference is available in EnvironmentDetails
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD); 
	
	}
	
	@Test(dataProvider="objectTestData1", description="CSR Activity Details")
	public void fn_CSRActivityReport(String strCSR,String strAccNum,String strTCName) {
		
		Log.startTestCase(strTCName);
		WebDriverWait webWait; 
		webWait = new WebDriverWait(driver,1000);
		Cls_ChangeDeleteClientDefinedFieldActns obj = new Cls_ChangeDeleteClientDefinedFieldActns();
		Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
		ChangeDeleteClientDefinedFields objClientDef = new ChangeDeleteClientDefinedFields();		
		
				
		try {
			
			String strParentWindow = driver.getWindowHandle();
			Thread.sleep(1000);
			//Search account
			objClientDefAct.AccountSearch(driver, webWait,strAccNum);		
			Thread.sleep(500);
			//Wait till the page loads 
			webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Reset Password")));
			
			//click link
			objClientDef.fn_ClientDefLink(driver, webWait).click();
			//Add Data in the Custom Defined field
			obj.AddNewField(driver, webWait, "Co_Code", "76"); 
			//Returns to the previous screen
			objClientDef.fn_ReturnPrevScreen(driver, webWait).click(); 
			
			
			//Click on CSR link for the account
			driver.findElement(By.partialLinkText("CSR Activity")).click();
			Thread.sleep(20);	
								
			driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
			Thread.sleep(100);			
			String strcurrentWindow = driver.getWindowHandle();
			//Select From Date
			driver.findElement(By.name("fromBeginning")).click();
			//Select To date 
			driver.findElement(By.name("untilToday")).click();
			
			
			//Select All activity
			CSRActivity objActivity = new CSRActivity();
			objActivity.fn_ActivityDetailsList(driver, webWait).selectByVisibleText("All Activity");
			//Click on Search button
			objActivity.fn_SearchBtn(webWait).click();
			Thread.sleep(1000);
				
			//Identify table Account -> CSR Activity
			WebElement tblAcctCSR = driver.findElement(By.tagName("table"));
			List<WebElement>rows = tblAcctCSR.findElements(By.tagName("tr"));
			
			int endRow = 0;
			String strCellData;
			int intRowCnt = 0;
			int iFlag = 0;
			int r = 0;
			boolean flag = false;
			
			Date curDate = new Date();
			SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
			String strDate = format.format(curDate);
				
			
		    TreeMap<String, String> tmapTimestamp =new TreeMap<String,String>();//creating new generic arraylist  
		    
		    String strTimeStamp = "";
			String strActivity = "";
		
		    for (WebElement row : rows){
				
				List<WebElement>cells = row.findElements(By.tagName("td"));
			
				int intColCnt = 0;
				int column = 0;
				
				for(WebElement cell : cells){
					strCellData = cell.getText();
				
					boolean res1 = !strCellData.equals(strDate);

					if (column == 2){
						strTimeStamp = strCellData;	//Store timestamp data
					}
					
					if (column == 4){
						strActivity = strCellData;	//Store activity data 
					}
					tmapTimestamp.put(strTimeStamp, strActivity);		//add key(timestamp), value(activity details) pair to map			
					if (intColCnt==1 && !" ".equals(strCellData) && !(strDate.equals(strCellData)) && null != strCellData){
						endRow = intRowCnt;
						flag = true;
						break;
					}
					intColCnt = intColCnt + 1;
					column++;
				}
					
				intRowCnt = intRowCnt + 1;
				if(flag){
					break;
				}
			}
		    
			int intlenTimeStamp = tmapTimestamp.size();
		    String[] strarrAcctReports = new String[intlenTimeStamp*2] ;
		    int iCntTimeStamp = 0;
		    //Parse the map and store the contents in the array
		    Log.info("Printing Treemap = " + tmapTimestamp);
			for(Map.Entry<String,String> entry : tmapTimestamp.entrySet()) {
	
				  Log.info("Key is "+ entry.getKey() + "and value is :"+ entry.getValue());
				  strarrAcctReports[iCntTimeStamp]=entry.getKey();
				  strarrAcctReports[iCntTimeStamp+1]=entry.getValue();
				  iCntTimeStamp = iCntTimeStamp + 2;
			}	
			
			driver.switchTo().window(strcurrentWindow);
					
			driver.findElement(By.xpath("//button[@type='button']")).click();
			
			driver.switchTo().window(strParentWindow);
			//driver.close();//Closing the driver to close the Accounts-> CSR Activity Report
			
			/*System.setProperty("webdriver.chrome.driver","C:\\QAAria\\Brwser_Driverssrc\\chromedriver.exe");
			driver = new ChromeDriver();
			EnvironmentDetails objEnv = new EnvironmentDetails();
			//This parameters are taken from Exec file and its reference is available in EnvironmentDetails
			AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);
			//Create object for class Cls_CSRActivityDetails*/
			Cls_CSRActivityDetails objReportCSR= new Cls_CSRActivityDetails();
			Thread.sleep(5000);
			
			objReportCSR.fn_CSRReportsNavigation(driver, webWait);
			//Call Function to Navigate to Reports
			
			Thread.sleep(3000);
			driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
			//driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"ariaGlobalModalPopup\"]/div/iframe")));//Switch to frame
			
			Thread.sleep(2000);
			//Call Function to generate the report
			objReportCSR.fn_CSRReports(driver, webWait, strCSR); 
			//Click on Search button
			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
			
			Thread.sleep(2000);
			//Read table data
			WebElement tblReportCSR = driver.findElement(By.xpath("/html/body/fieldset[1]/table/tbody"));
			List<WebElement>rowtblReportCSR = tblReportCSR.findElements(By.tagName("tr"));
			//Declare map to store the values from Reports->CSR Activity
		    TreeMap<String, String> tmapTimeStampActivity = new TreeMap<String, String>();
			
			for (WebElement rowinstance:rowtblReportCSR){
				List<WebElement>cells = rowinstance.findElements(By.tagName("td")); 
				int col = 0;
				String strTimeStampReport = "";
				String strActivityReport = "";
				for (WebElement cell : cells){
					String cellData = cell.getText();
					if (col ==1){
						strTimeStampReport = cellData;
					}
					if (col ==3){
						strActivityReport = cellData;
					}
					
					col++;
				}
				//Store value in map
				if (!strTimeStampReport.equals("")&&!strActivityReport.equals("")){
					tmapTimeStampActivity.put(strTimeStampReport, strActivityReport);
				}
				
			}
						
			Set setTMapReport = tmapTimeStampActivity.entrySet();//Get set of entries				
			Iterator iSetReport = setTMapReport.iterator();//Get iterator
			int intSize = tmapTimeStampActivity.size();//Get length of tree map tmapTimeStampActivity
			String[] strarrReportsCSR = new String[intSize*2] ; //Declare array to store the values of tree map tmapTimeStampActivity
			int istrarrActivity = 0;
			
			//Parse the treemap and store the contents in the array
			while (iSetReport.hasNext()){
				
				Map.Entry mentryReport = (Map.Entry)iSetReport.next();
				Log.info("Key is "+mentryReport.getKey()+ "and value is :"+mentryReport.getValue());
				strarrReportsCSR[istrarrActivity]=(String) mentryReport.getKey();
				strarrReportsCSR[istrarrActivity+1]=(String) mentryReport.getValue();
				istrarrActivity= istrarrActivity + 2;
				
			}
			boolean Flag = false;
			//Parse the array values strarrReportsCSR and compare with strarrAcctReports values
			for (int intCnt = 0;intCnt<strarrReportsCSR.length;intCnt++ ){
				if (!strarrReportsCSR[intCnt].trim().equalsIgnoreCase(strarrAcctReports[intCnt+2].trim())){
					Log.info("Mismatch in array"+ strarrReportsCSR[intCnt].trim() +" and "+strarrAcctReports[intCnt+2].trim());
					Flag = true;
				}		
					
			}
			
			if (Flag == true){
				Log.error("FAIL : CSR Activity Report Data Mismatch");
	    		Reporter.log("FAIL : CSR Activity Report Data Mismatch");
	    		
			}
			if (Flag == false){
				Log.info("PASS: CSR Activity Report data is verified scuccessfully");
				Reporter.log("PASS: CSR Activity Report data is verified scuccessfully");
			}
			
			Log.endTestCase(strTCName);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}
	/*
	 
	@AfterTest
	public void afterMethod() throws Exception{
		 
		 //Logs out of the application
		 AriaLoginLogout.appLogout(driver); 
		 //Quitting the driver
		 driver.quit(); 
	}*/
		 
	//Reading data for the test
	@DataProvider(name = "objectTestData1")
	public Object[][] testcasedata1() throws DataDrivenFrameworkException
	{
		Utils objUtils=new Utils();
		return objUtils.data1("TestCaseDetails", "CSRActivityDetails");
	}	
	

}
