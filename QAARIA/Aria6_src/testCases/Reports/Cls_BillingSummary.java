/*
 Author     		:	Madhavi JN
 Class Name			: 	Cls_BillingSummary 
 Purpose     		: 	Purpose of this file is :
						1. To verify if the Created and corresponding Invoice Generated is present in report.
						2. This is automation of TC: QAABE-96
 Date       		:	09/09/2015,11/02/2015,11/09/2015 
 Version Information:	Version 2.0
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "ReportBillingSummary" worksheet 
 						for excel "TestData.xls". Valid Account and Invoice Should be passed as Input parameters.

 Test Steps 		:	1. Login using valid credentials 
 						2. Navigate to the Analytics & reporting -> Reports -> Run Reports.
 						3. Download the report -Invoice Transaction Detail by Account - Under SGAS.
 						4. Downloaded report will be placed under C:\\QAARIA\\src\\testData\\BillingReport
 						5. Covert the downloaded CSV to EXCEL and search the Excel with required values and print the result to console.					  						
 Update Version 2.1 :   Changed Chrome driver path and also the hard coded path of Downloaded Excel
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/
package testCases.Reports;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.EnvironmentDetails;
import utility.Log;
import utility.ReportUtils;
import utility.Utils;
import utility.VerificationMethods;
import appModules.AriaLoginLogout;
import appModules.Cls_Billing_Summary_Report;
import atu.ddf.exceptions.DataDrivenFrameworkException;


public class Cls_BillingSummary extends VerificationMethods
{

	public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_BillingSummary";

	@BeforeClass
	 public static void beforeMethod()throws Exception
	 {	
		DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
		driver = Utils.fn_SetChromeDriverProperties();
		System.setProperty("webdriver.chrome.driver",chromeDriverPath);
		//driver = new ChromeDriver();
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.startTestCase("Login to application");
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
	 }


	@Test(dataProvider="objectTestData", description="ReportBillingSummary")
    public void runBillingSummaryReport(String strAccNum,String strInvoiceNum) throws Exception
    {
    	
		WebDriverWait webWait;
		webWait = new WebDriverWait(driver,1000);
	    Log.startTestCase("Fin Rpt: Billing Summary");
	
	   try 
	    {  
		   
		Cls_Billing_Summary_Report objfnctns = new Cls_Billing_Summary_Report();
		objfnctns.fn_BillingReport(driver, webWait);   
		ReportUtils objRprtutils = new ReportUtils();
		Utils objUtils = new Utils();
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String filename = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
				+ System.getProperty("file.separator") +"BillingReport"+dateFormat.format(date);
	    Log.info("the filename generated is "+filename);
	    objRprtutils.fn_ReportsFileDownload(driver, filename);
	    Thread.sleep(10000);
	    driver.findElement(By.xpath("//button[@title='close']")).click();
	    Thread.sleep(10000);
        String CSVname = filename+"."+"csv";
        String Excelname =filename+"."+"xlsx";
        objUtils.ConvertCSVTOEXCEL(CSVname,Excelname);
        Thread.sleep(10000);
        FileInputStream fis = new FileInputStream(Excelname);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet ws = wb.getSheetAt(0);
        int rowNum = ws.getLastRowNum() + 1;
        int colNum = ws.getRow(0).getLastCellNum();
        boolean blnIsAcctFound = false;
        boolean blnIsInvoiceFound = false;
        String[][] data = new String[rowNum][colNum];
        String Value = null;

        for (int i = 0 ; i < data.length ; i++) 
        {
        	XSSFRow row = ws.getRow(i);
        	          
	                for (int j = 0 ; j < colNum ; j++)
	                {
	                	XSSFCell cell = row.getCell(j);
	                	
			                 if(cell!=null)
			                	 
			                 {
					                 switch (cell.getCellType())
					                	{
					                    case HSSFCell.CELL_TYPE_STRING:
					                       	Value =cell.getRichStringCellValue().getString();
					                          break;
					                    case HSSFCell.CELL_TYPE_NUMERIC:
					                    	Value = Integer.toString((int) cell.getNumericCellValue());
					                          break;
					                    case HSSFCell.CELL_TYPE_BOOLEAN:
					                    	Value = Boolean.toString(cell.getBooleanCellValue());
					                         break;
					                    case HSSFCell.CELL_TYPE_FORMULA:
					                    	Value = cell.getCellFormula();
					                        break;                        
					                    }
					                	
					                    data[i][j] = Value;
					
					                    if (((data[i][j].trim().toString()).equalsIgnoreCase(strAccNum)))
					                    {
					                    	blnIsAcctFound = true;
					                    	Log.info("Account Number  is found in the Report :: "+strAccNum);
					                    }
					                    else if (((data[i][j].trim().toString()).equalsIgnoreCase(strInvoiceNum)))
					                    {
					                    	blnIsInvoiceFound = true;
					                    	Log.info("Invoice Number is found in the Report :: "+strInvoiceNum);
					                    	break;
					                    }
			                }
	                
	             }
        
         }         
        
            if (blnIsAcctFound==false)
    		{
            	 Log.info("Account is Not found in the Report :: "+strAccNum);
    		}
            
            if (blnIsInvoiceFound==false)
    		{
            	 Log.info("Invoice is Not found in the Report :: "+strInvoiceNum);
    		}


		  } catch (Exception exception) {
			  Log.error("ERROR : Account and Invoices can not be searched in the Report "+exception.toString());
			  exception.printStackTrace();}
    }

    @DataProvider(name = "objectTestData")
    public Object[][] data() throws DataDrivenFrameworkException
    {
        Utils objUtils=new Utils();
        return objUtils.data1("TestCaseDetails", "ReportBillingSummary");
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
