	/*
	 Author     		:	Madhavi JN
	 Class Name			: 	Cls_BillingSummary 
	 Purpose     		: 	Purpose of this file is :
							1. To verify if the Created and corresponding Account's Transaction Generated is present in report.
							2. This is automation of TC: QAABE-488
	 Date       		:	09/09/2015,11/02/2015,11/09/2015 
	 Version Information:	Version 2.0
	 
	 PreCondition 		:	1. Role based Login required.
	 						2. Data to be populated in the "TestCaseDetails" & "AllTransactionsByAccount" worksheet 
	 						for excel "TestData.xls". Valid Account and Invoice Should be passed as Input parameters.

	 Test Steps 		:	1. Login using valid credentials 
	                        2. Search the Given account and navigate to Payments and Credits 
	                        3. click on payments tab and Record payment recieved link
	                        4. Give the Reference code and enter the amount to pay and capture the message and transaction number
	 						5. Navigate to the Analytics & reporting -> Reports -> Run Reports.
	 						6. Download the report -Invoice Transaction Detail by Account - Under SGAS.
	 						7. Downloaded report will be placed under C:\\QAARIA\\src\\testData\\AllTransactionsByAccount"+dateFormat.format(date)
	 						8. Covert the downloaded CSV to EXCEL and search the Excel with required values and print the result to console.					  						
	 
	 Updated Version 2.0:   Updated chrome driver path and Downloaded path of excel 
	 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
	 						All Rights Reserved 
	*/
package testCases.Reports;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.PaymentDetailsFinancialReport;
import utility.EnvironmentDetails;
import utility.Log;
import utility.ReportUtils;
import utility.Utils;
import utility.VerificationMethods;
import appModules.AriaLoginLogout;
import appModules.Cls_Billing_Summary_Report;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
import atu.ddf.exceptions.DataDrivenFrameworkException;
	

public class Cls_AllTransactionsByAccount extends VerificationMethods
{

		public static WebDriver driver;
		
		public static String strTestCaseName = "Cls_AllTransactionsByAccount";

		@BeforeTest
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


		@Test(dataProvider="objectTestData", description="AllTransactionsByAccount")
	    public void AllTransactionsByAccount(String strAccNum,String strTransactionNum) throws Exception
	    {
	    	
			WebDriverWait webWait;
			webWait = new WebDriverWait(driver,1000);
		    Log.startTestCase("Fin Rpt: AllTransactionsByAccount");
		
		   try 
		    {  
			   
			Cls_Billing_Summary_Report objfnctns = new Cls_Billing_Summary_Report();
			Cls_ChangeDeleteClientDefinedFieldActns obj = new Cls_ChangeDeleteClientDefinedFieldActns();
			PaymentDetailsFinancialReport objpages = new PaymentDetailsFinancialReport();
			//Account Search
			obj.AccountSearch(driver, webWait, strAccNum);
			//Click on PaymentCredits link
			objpages.fn_clickPaymentsCredits(driver, webWait).click();
			Thread.sleep(1000);
			//Click on payments link
			objpages.fn_clickPayments(driver, webWait).click();
			Thread.sleep(1000);
			//click on RecordPaymentReceived link
			objpages.fn_clickRecordPaymentReceived(driver, webWait).click();
			//Enter the value for CSR comments
			objpages.fn_getCSRComments(driver, webWait).sendKeys("CSR Comments");
			Date date = new Date() ;
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
			String strRefcode = dateFormat.format(date);
			Log.info("generated Reference code is :: "+strRefcode);
			//Enter a value for Ref code
			objpages.fn_getRefCode(driver, webWait).sendKeys(strRefcode);
			//Select Other as payment method
			objpages.fn_getPaymentAmount(driver, webWait,1).click();
			//Enter the payment amount
			objpages.fn_getOtherAmount(driver, webWait).sendKeys("1");
			//Click on the first Post Payment button
			objpages.fn_clickPostPayment(driver, webWait).click();
			Thread.sleep(2000);
			//Click on the second Post Payment button
			objpages.fn_clickPostPayment(driver, webWait).click();
			Thread.sleep(2000);
			String strmsg =objpages.fn_getPaymentsStatusMessage(driver, webWait).getText();
			//Check the Payment done Message
			if (strmsg.contains("Payment of"))
				{
				  Log.info("payment is done successfully :: "+strmsg);	
				}
			else
				{
				  Log.info("payment is not done successfully :: "+strmsg);		
				}
			String strnewcode = "External Payment "+strRefcode;
			Log.info("the new code generated for comparision is "+strnewcode);
			//Capture the Transaction ID for the payment done
			WebElement Webtable = driver.findElement(By.xpath("//*[contains(@id, 'DataTables_Table_')]"));
	    	List<WebElement> TotalRowCount = Webtable.findElements(By.tagName("tr"));
			 String strTransactionID=null;
			 for (WebElement rowElement: TotalRowCount)
				{
					List<WebElement> TotalColumnCount=rowElement.findElements(By.tagName("td"));
				    	 
			    	 for (int ColumnIndex=0;ColumnIndex<TotalColumnCount.size();ColumnIndex++)
			    	 {			    		  
			    		 
			    		    if (TotalColumnCount.get(ColumnIndex).getText().toString().contentEquals(strnewcode))
				    		   {		    			   
				    			  strTransactionID = TotalColumnCount.get(1).getText();			    			  
								  Log.info("Transaction ID generated on the application is"+strTransactionID);
								  break;
				    		   }
					 }	

				}     

			//Generate the AllTransactionsByAccount Report
			objfnctns.fn_AllTransactionsByAccount(driver, webWait); 
			ReportUtils objRprtutils = new ReportUtils();
			Utils objUtils = new Utils();
			//Concatenating the timestamp to the filename
			String filename = System.getProperty("user.dir")
					+ System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
					+ System.getProperty("file.separator") +"AllTransactionsByAccount"+dateFormat.format(date);
		    Log.info("the filename generated is "+filename);
		    //Downloading the Report and saving with generated file name
		    objRprtutils.fn_ReportsFileDownload(driver, filename);
		    Thread.sleep(10000);
		    //Closing the Report window
		    driver.findElement(By.xpath("//button[@title='close']")).click();
		    Thread.sleep(10000);
		    //Converting downloaded CSV to Excel
	        String CSVname = filename+"."+"csv";
	        String Excelname =filename+"."+"xlsx";
	        objUtils.ConvertCSVTOEXCEL(CSVname,Excelname);
	        Thread.sleep(10000);
	        //Opening the Excel in backend and serch the Required data
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
						                    else if (((data[i][j].trim().toString()).equalsIgnoreCase(strTransactionID)))
						                    {
						                    	blnIsInvoiceFound = true;
						                    	Log.info("Transaction Number is found in the Report :: "+strTransactionID);
						                    	break;
						                    }
				                }
		                
		             }
	        
	         }         

	            if (blnIsAcctFound==false)
	    		{
	            	 Log.info("Account Number is Not found in the Report :: "+strAccNum);
	    		}
	            
	            if (blnIsInvoiceFound==false)
	    		{
	            	 Log.info("Transaction Number is Not found in the Report :: "+strTransactionID);
	    		}
	            

			  } catch (Exception exception) {
				  Log.error("ERROR : Account and Invoices can not be searched in the Report "+exception.toString());
				  exception.printStackTrace();}
	    }

	    @DataProvider(name = "objectTestData")
	    public Object[][] data() throws DataDrivenFrameworkException
	    {
	        Utils objUtils=new Utils();
	        return objUtils.data1("TestCaseDetails", "AllTransactionsByAccount");
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


