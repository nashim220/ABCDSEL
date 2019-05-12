package appModules;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CSRActivity;
import utility.Log;
import utility.ReportUtils;
import utility.Utils;

public class Cls_Billing_Summary_Report {
	
	static Cls_Billing_Summary_Report objBillingReort = new Cls_Billing_Summary_Report();
	
	public void fn_BillingReport(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
	
		CSRActivity ObjCSR = new CSRActivity();
	    Thread.sleep(1000);
	    ObjCSR.fn_ReportsLink(webWait).click();
	    Thread.sleep(1000);
	    ObjCSR.fn_RunReportsLink(webWait).click();
	    Thread.sleep(5000);
	    ReportUtils objreportUtils = new ReportUtils();
	    objreportUtils.fn_clickSideBarElement(driver,By.partialLinkText("SGAS"));
		Thread.sleep(15000);
		//Click on Invoice Transaction Detail by Account - SGAS
		objreportUtils.fn_clickSideBarElement(driver,By.partialLinkText("Invoice Transaction Detail by Account - SGAS"));
		//driver.findElement(By.partialLinkText("Invoice Transaction Detail by Account - SGAS")).click();
		Thread.sleep(50000);
		objreportUtils.fn_clickReportElement(driver,By.xpath("/html/body/table[2]/tbody/tr/td[2]/a/img"));
		Thread.sleep(5000);
		//Select the search criteria 
		objreportUtils.fn_SetValueReportElement(driver,By.name("report_start_dt"),"Current Day");
		//driver.findElement(By.name("report_start_dt")).sendKeys("Current Day");
		Thread.sleep(5000);
		//Click on Preview Report
		objreportUtils.fn_clickReportElement(driver,By.xpath("/html/body/form[1]/table[2]/tbody/tr/td/input"));
		Thread.sleep(10000);
		//driver.switchTo().frame(driver.findElement(By.name("postReportActions")));
		Thread.sleep(10000);
   
	}
	
	public void fn_AllTransactionsByAccount(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
	
		CSRActivity ObjCSR = new CSRActivity();
		ObjCSR.fn_AnalyticsReportingLink(webWait).click();
	    Thread.sleep(1000);
	    ObjCSR.fn_ReportsLink(webWait).click();
	    Thread.sleep(1000);
	    ObjCSR.fn_RunReportsLink(webWait).click();
	    Thread.sleep(5000);
	    ReportUtils objreportUtils = new ReportUtils();
	    objreportUtils.fn_clickSideBarElement(driver,By.partialLinkText("SGAS"));
		Thread.sleep(15000);
		//Click on Invoice Transaction Detail by Account - SGAS
		objreportUtils.fn_clickSideBarElement(driver,By.partialLinkText("All Transactions by Account - SGAS"));
		//driver.findElement(By.partialLinkText("Invoice Transaction Detail by Account - SGAS")).click();
		Thread.sleep(50000);
		objreportUtils.fn_clickReportElement(driver,By.xpath("/html/body/table[2]/tbody/tr/td[2]/a/img"));
		Thread.sleep(5000);
		//Select the search criteria 
		objreportUtils.fn_SetValueReportElement(driver,By.name("report_start_dt"),"Current Day");
		//driver.findElement(By.name("report_start_dt")).sendKeys("Current Day");
		Thread.sleep(5000);
		//Click on Preview Report
		objreportUtils.fn_clickReportElement(driver,By.xpath("/html/body/form[1]/table[2]/tbody/tr/td/input"));
		Thread.sleep(10000);
		//driver.switchTo().frame(driver.findElement(By.name("postReportActions")));
		Thread.sleep(10000);
	   
	}
	
	
	public void fn_PaymentTransactionByDate(WebDriver driver,WebDriverWait webWait,String strAccNum,String strTransactionID,String strReportName) throws InterruptedException, IOException
	{
	
		CSRActivity ObjCSR = new CSRActivity();
		ReportUtils objRprtutils = new ReportUtils();
		Utils objUtils = new Utils();
		if (strReportName.equals("BeforeReport"))
		{
		ObjCSR.fn_AnalyticsReportingLink(webWait).click();
		}
	    Thread.sleep(1000);
		if (strReportName.equals("AfterReport"))
		{
		ObjCSR.fn_ReportsLink(webWait).click();
		}
		Thread.sleep(1000);
	    ObjCSR.fn_ReportsLink(webWait).click();
	    Thread.sleep(1000);
	    ObjCSR.fn_RunReportsLink(webWait).click();
	    Thread.sleep(6000);
	    ReportUtils objreportUtils = new ReportUtils();
	    objreportUtils.fn_clickSideBarElement(driver,By.partialLinkText("Standard"));
		Thread.sleep(15000);
		//Click on Invoice Transaction Detail by Account - SGAS
		objreportUtils.fn_clickSideBarElement(driver,By.partialLinkText("Payment Transactions by Date"));
		//driver.findElement(By.partialLinkText("Invoice Transaction Detail by Account - SGAS")).click();
		Thread.sleep(50000);
		objreportUtils.fn_clickReportElement(driver,By.xpath("/html/body/table[2]/tbody/tr/td[2]/a/img"));
		Thread.sleep(5000);
		//Select the search criteria 
		objreportUtils.fn_SetValueReportElement(driver,By.name("report_start_dt"),"Current Day");
		//driver.findElement(By.name("report_start_dt")).sendKeys("Current Day");
		Thread.sleep(5000);
		//Click on Preview Report
		objreportUtils.fn_clickReportElement(driver,By.xpath("/html/body/form[1]/table[2]/tbody/tr/td/input"));
		Thread.sleep(10000);
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String filename = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
				+ System.getProperty("file.separator") +strReportName+dateFormat.format(date);
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
	    boolean blnIsTransactionIDFound = false;
	    String[][] data = new String[rowNum][colNum];
	    String Value = null;
	    String StrTransactionType =null;
	
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
				                    else if (((data[i][j].trim().toString()).equalsIgnoreCase(strTransactionID)))
				                    {
				                    	blnIsTransactionIDFound = true;
				                    	 StrTransactionType = (data[i][j-6].trim().toString());
				                    	Log.info("=========Results for the Report :: "+strReportName);
				                    	Log.info("TransactionID is found in the Report :: "+strTransactionID);
				                    	Log.info("Transaction Type Value corresponding to the transaction ID is  :: "+StrTransactionType);
				                    	Log.info("=========End of the Results for :: "+strReportName);
				                    	break;
				                    }
			                }
	                
	              }
	    
	     }         
	    
	    if (strReportName.equals("AfterReport"))
	    {
	    	if (StrTransactionType.equalsIgnoreCase("Voided Check"))
	    	{
	    		Log.info("Payment has successfully voided and appears void in the Report as :: "+StrTransactionType);
	    	}
	       else
	    	{
	    		Log.info("Payment is not voided and appears only check in the Report as :: "+StrTransactionType);
	    	}
	    }
	    
	    if (blnIsAcctFound==false)
		{
	        Log.info("Account is Not found in the Report :: "+strAccNum);
		}
	        
	    if (blnIsTransactionIDFound==false)
		{
	        Log.info("TransactionID is Not found in the Report :: "+strTransactionID);
		}
	
	   
	}

}
