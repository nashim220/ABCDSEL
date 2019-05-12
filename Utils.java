/* 
==============================================================
 Author     		:	Umesh Kute
 Modified By		: 	Aashish Bhutkar, Madhavi JN
 Class Name		 	: 	Utils
 
 Purpose     		: 	Utility functions to use across the application [Reusable functions]
 						1. Take screenshot during test script execution
 						2. Read data from excel file
 						
 Date       		:	06/06/2016
 Modified Date		:	06/28/2016
 Version Information:	Version 2.0
 
 Version Changes 2.0:	1. Modified the logic for fn_getPreviousDate(Integer intBackDays, String strDatePattern). 
 						Added Pattern setting for the date generated after adjusting past days.
 
 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - All Rights Reserved 
 
 #======================================================================
 */

package utility;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import utility.VerificationMethods;
import atu.ddf.file.ExcelFile;
import atu.ddf.selenium.SeleniumTestNGHelper;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/*
This class contains the reusable function which can be used for 
taking screenshots during test script execution and reading data from excel file
@version: 1.0
@author: Umesh Kute
*/
public class Utils {
	public static WebDriver driver = null;
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS"); 
    public static String formatDate;
	    
		
	 public static void takeScreenshot(WebDriver driver, String sTestCaseName) throws Exception{
			try{
				
				formatDate = sdf.format(System.currentTimeMillis());	// this is to be appended to the Test Case name as a timestamp. 
				
				Log.info(EnvironmentDetails.Path_ScreenShot);
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(EnvironmentDetails.Path_ScreenShot + sTestCaseName + "_" + formatDate + ".jpg"));
				//Reporter.setEscapeHtml(true);
			
				Reporter.log("<a href=\""+EnvironmentDetails.Path_ScreenShot+"\"> Clickhere </a>");
	            Reporter.log("<a href=\""+ EnvironmentDetails.Path_ScreenShot + sTestCaseName +".jpg" +"\"><img src=\"file:///"  
	                        + EnvironmentDetails.Path_ScreenShot + sTestCaseName +".jpg" + "\" alt=\"\""+ "height='800' width='1200'/> "+"<br />"); 
	            
	            //Reporter.setCurrentTestResult(null); 
	            
			} catch (Exception e){
				Log.error("Class Utils | Method takeScreenshot | Exception occured while capturing ScreenShot : "+e.getMessage());
				e.printStackTrace();
				throw new Exception();
			}
		}

	 	/// Function to read data from  excel file
		public Object[][] data(String strSheetName,String strTestcaseName) throws atu.ddf.exceptions.DataDrivenFrameworkException 
		{
			String excelResource = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
			+ System.getProperty("file.separator") + "TestData.xlsx";
			// provide your excel file path here
			ExcelFile excelFile = new ExcelFile(excelResource);
			// provide the Sheet name
			excelFile.setSheetName(strSheetName);
			// provide the Column Name where Test Case names will be given
			excelFile.setTestCaseHeaderName("TestCaseNameColumn");
			// provide the Test Case Name
			List<List<String>> data = excelFile.getDataUsingTestCaseName(strTestcaseName);
			// SeleniumTestNGHelper is a utility class that will help in converting
			// the Test data into TestNG Data Provider supported format
			// One such format here is a 2-Dimensional Object Array
			return SeleniumTestNGHelper.toObjectArray(data);
		}
	
		public static String getCurrentDate()
		{
			String PATTERN="dd-MMM-yyyy";
			SimpleDateFormat dateFormat=new SimpleDateFormat();
			dateFormat.applyPattern(PATTERN);
			String date1 = dateFormat.format(Calendar.getInstance().getTime());
			return date1;
		}
		
		public static String getdiffCurrentDate(String strdate)
		{
			String PATTERN="dd-MMM-yy";
			SimpleDateFormat dateFormat=new SimpleDateFormat();
			dateFormat.applyPattern(PATTERN);
			String date1 = dateFormat.format(strdate);
			return date1;
		}
		
		

		public String getDateYearFirst()
		{
			String PATTERN="yyyy-MM-dd";
			SimpleDateFormat dateFormat=new SimpleDateFormat();
			dateFormat.applyPattern(PATTERN);
			String date1 = dateFormat.format(Calendar.getInstance().getTime());
			return date1;
		}

		public static String getAlternateDate(int dateModifier)
		{
			String currentDate = getCurrentDate();
			int dayNum = Integer.parseInt(currentDate.substring(0, 2));
			String restOfDate = currentDate.substring(2,currentDate.length()-1);
			String futureDate = (dayNum+dateModifier)+restOfDate;
			return futureDate;
		}	
		
/*
		Author: Madhavi JN
		Function fn_getPreviousDate: Returns the date of previous month(s).
		Inputs:	intDays - How many Days Back.				
		Outputs: formated date string for the date requested.    
*/
		 public static String fn_getCustomizedPreviousDate(Integer intDays) {
			 
				String PATTERN = "dd-MMM-yyyy";
				SimpleDateFormat dateFormat = new SimpleDateFormat();
				dateFormat.applyPattern(PATTERN);

				//TODO: Get current date and then trace it back by No of days as given in the input.
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, intDays);			
				String date1 = dateFormat.format(cal.getTime());
				
				return date1;
		 }
		 
			    

 /*
	 	Author: Aashish Bhutkar
	 	Function fn_getLoadDataDate: Returns the date 5 days prior to current date in "yyyy-MM-dd" format for loading data.
	 	Outputs: formated date string to be used for loading data.    
 */
	 	 public String fn_getLoadDataDate() throws Exception {
	 		 
 			String PATTERN = "yyyy-MM-dd";
 			SimpleDateFormat dateFormat = new SimpleDateFormat();
 			dateFormat.applyPattern(PATTERN);
 			//TODO: Get current date and then trace it back by 5 days for loading data.
 			Calendar cal = Calendar.getInstance();
 			cal.add(Calendar.DATE, -5);
 			String date1 = dateFormat.format(cal.getTime());
 			return date1;
	 	 }
			 	
			 
/*
		Author: Aashish Bhutkar
		Function fn_getPreviousDate: Returns the date of previous month(s).
		Inputs:	intDate - which date of the month to be returned.
				intMonthsBack - How many Months Back.
		Outputs: formated date string for the date requested.    
*/
		 public String fn_getPreviousDate(Integer intDate, Integer intMonthsBack) {
			 
			String PATTERN = "yyyy-MM-dd";
			SimpleDateFormat dateFormat = new SimpleDateFormat();
			dateFormat.applyPattern(PATTERN);

			//TODO: Get current date and then trace it back by intMonthsBack months and set the date mentioned in intDate.
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, intDate);
			cal.add(Calendar.MONTH, -intMonthsBack);
			String date1 = dateFormat.format(cal.getTime());
			
			return date1;
		 }

	 
/*
		Author: Aashish Bhutkar
		Function fn_getPreviousDate: Returns the date 'intBackDays' days prior to current date 
									 with "strDatePattern" as suggested during the call.
		Outputs: formated date string to be used for loading data.    
*/
		 public String fn_getPreviousDate(Integer intBackDays, String strDatePattern) throws Exception {
		 
			String PATTERN = strDatePattern;
			SimpleDateFormat dateFormat = new SimpleDateFormat();
			dateFormat.applyPattern(PATTERN);
			//TODO: Get current date and then trace it back by intBackDays days.
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -(intBackDays));
			String date1 = dateFormat.format(cal.getTime());
			
			return date1;
		 }
		 
		 
 /*
		Author: Aashish Bhutkar
		Function fn_getDateFormatted: Returns the date in format "yyyy-mm-dd" & "mm-dd-yyyy".
		Inputs:	strDate - is the date which is in format "DD-MMM-YYYY"
				strDateFormat - is the format in which you want dates; currently coded for "yyyy-mm-dd" & "mm-dd-yyyy" ONLY !
		Outputs: formated date string.
*/
		 public String fn_getDateFormatted(String strDate, String strDateFormat) {
			 
			 String strFormattedDate = null;
			 
			 String[] arrDate = null;
			 Integer intMonth = 0;
			 String[] arrMonthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
			 
			 switch(strDateFormat){
			 
			 case "yyyy-mm-dd":
				 arrDate = strDate.split("-");
				 intMonth = Arrays.asList(arrMonthNames).indexOf(arrDate[1]) + 1;
				 strFormattedDate = arrDate[2].replaceAll(" ", "")+"-"+intMonth+"-"+arrDate[0];
				 break;
				 
			 case "mm-dd-yyyy":
				 arrDate = strDate.split("-");
				 intMonth = Arrays.asList(arrMonthNames).indexOf(arrDate[1]) + 1;
				 strFormattedDate = intMonth+"-"+arrDate[0]+"-"+arrDate[2].replaceAll(" ", "");
				 break;
			 }	 
			 			 
			 return strFormattedDate;
		 }
		
/*
	Author: Aashish Bhutkar
	Function fn_ConvertStringToBigDecimal: Takes in the string to be converted to Big Decimal and returns it in String 
											formated as Big Decimals.
			strConvertBigDeci - The string whcih needs to be converted to Big Decimal.
			intDecimalPlaces - Decimal Places to be set for scaling the Big Decimal. 
*/    		
		 public String fn_ConvertStringToBigDecimal(String strConvertBigDeci, Integer intDecimalPlaces) throws Exception {

			//TODO: Create a format for Float and apply it to the string value received and return it.
			if(strConvertBigDeci == null || strConvertBigDeci == "")	//this has been added to mitigate issues with files having no data.
			return "0.00";
			BigDecimal bdValue = new BigDecimal(strConvertBigDeci.replace(",", ""));
			bdValue.setScale(intDecimalPlaces, BigDecimal.ROUND_DOWN);
	
			DecimalFormat objDecimalForm = new DecimalFormat("0.00");
			objDecimalForm.setMaximumFractionDigits(intDecimalPlaces);
	
			String strBigDeci = objDecimalForm.format(bdValue);	
			return strBigDeci.toString();
		 }		
		
		
		/// Function to read data from  excel file -- TestData.xls, Sheet - TestCaseDetails
		//This function is included to refer TestData.xls file
		public Object[][] data1(String strSheetName,String strTestcaseName) throws atu.ddf.exceptions.DataDrivenFrameworkException
		{
			String excelResource = System.getProperty("user.dir")
					+ System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
					+ System.getProperty("file.separator") + "TestData.xlsx";
			// provide your excel file path here
			ExcelFile excelFile = new ExcelFile(excelResource);
			// provide the Sheet name
			excelFile.setSheetName(strSheetName);
			// provide the Column Name where Test Case names will be given
			excelFile.setTestCaseHeaderName("TestCaseNameColumn");
			// provide the Test Case Name
			List<List<String>> data = excelFile
					.getDataUsingTestCaseName(strTestcaseName);

			System.out.println(data);			
			return SeleniumTestNGHelper.toObjectArray(data);

		}

		//Function to Read Values from Excel By Condidtion as "Y"
		//Input Parameters:- File path and Sheet Name
		public Map<String, String> readexcel_ExecEnvironment(String destFile,String vSheetName)
		{

			//Create Excel data map Object
			Map<String, String> excelDataMap = new HashMap<String, String>();
			//creating the Try Block			
			try
			{
				//Creating the new File object by passing the destination file				
				File file = new  File(destFile);
				//passing the created file object as a param to Input stream				
				FileInputStream fileipstream = new FileInputStream(file);
				//creating a workbook object by fileipstream				
				XSSFWorkbook wb = new XSSFWorkbook(fileipstream);
				//creating a sheet object by vSheetName				
				XSSFSheet sheet = wb.getSheet(vSheetName);
                //getting the Row Count into Rows variables
				 int rows = sheet.getLastRowNum();
				 
                 //For Loop for iterating from zero to last row of the Excel
				 for(int rowno =0; rowno <=rows;rowno++)
				 {
                     //capturing the rowno to row variable as rowno cant be used further
					 XSSFRow row = sheet.getRow(rowno);
					 //capturing the initial row header value
					 XSSFRow rowheader = sheet.getRow(0);
					 //Capturing the value to validate the condition below
					 String data = row.getCell(0).getStringCellValue();

					 if ("Y".equals(data))

					 {
	                    //parsing the column content for respective row	
						 for(int column =0; column < row.getLastCellNum() ;column++)
						{
							 //for putting all the values fetched into Map 
							 excelDataMap.put(rowheader.getCell(column).getStringCellValue(),row.getCell(column).getStringCellValue());

						 }

					 }
				 }
                 //returning the Map				 
				 return excelDataMap;


			}

			catch(Exception ioe)
			{
				ioe.printStackTrace();
				return excelDataMap;
			}

		}
		
		//Function to Read Values from Excel By row and Column
		//Input Parameters:- File path and Sheet Name
		public Map<String, String> readexcel_InternalData(String destFile,String vSheetName)
		{
			//Create Excel data map Object
			Map<String, String> excelDataMap = new HashMap<String, String>();
			//creating the Try Block
			try
			{
				//Creating the new File object by passing the destination file
				File file = new  File(destFile);
				//passing the created file object as a param to Input stream
				FileInputStream fileipstream = new FileInputStream(file);
				//creating a workbook object by fileipstream
				XSSFWorkbook wb = new XSSFWorkbook(fileipstream);
				//creating a sheet object by vSheetName
				 XSSFSheet sheet = wb.getSheet(vSheetName);
                //getting the Row Count into Rows variables
				 int rows = sheet.getLastRowNum();
				 
                 //For Loop for iterating from zero to last row of the Excel
				 for(int rowno =0; rowno <=rows;rowno++)
				 {
                     //capturing the rowno to row variable as rowno cant be used further
					 XSSFRow row = sheet.getRow(rowno);
					 //capturing the initial row header value
					 XSSFRow rowheader = sheet.getRow(0);
					 //String data = row.getCell(0).getStringCellValue();
                        //parsing the column content for respective row	
						 for(int column =0; column < row.getLastCellNum() ;column++)
						{
							 //for putting all the values fetched into Map
							 excelDataMap.put(rowheader.getCell(column).getStringCellValue(),row.getCell(column).getStringCellValue());
						
						 }

					 }
                 //returning the Map
				 return excelDataMap;

			}

		catch(Exception ioe)
			{
				ioe.printStackTrace();
				return excelDataMap;
			}

		}
		
		//Function to write Values to Excel 
		//Input Parameters:- 2D Array with String values
		public void writeexcel_InternalData(String[][] array,String filename,String sheetname,int column) throws IOException
		{
			
			FileInputStream excelFileName = new FileInputStream(filename);
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet(sheetname);
			//iterating r number of rows
			for (int r=0;r <=array.length-1; r++ )
			{
				XSSFRow row = sheet.createRow(r);

				//iterating c number of columns
				for (int c=0;c <= array[r].length-1; c++ )	// Modified By Aashish: Use the Array's Column Length; rather than one specified.
				{
					XSSFCell cell = row.createCell(c);
					cell.setCellValue(array[r][c]);
				}
			}
			
			FileOutputStream fileOut = new FileOutputStream(filename, true);	// Modified By Aashish: update done to use the Append Mode.

			//write this workbook to an Outputstream.
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
			excelFileName.close();

        }
		
		public void CompareExcelSheets(String Filename1,String Filename2) throws IOException
		{
		     FileInputStream excellFile1 = new FileInputStream(new File(Filename1));
	         FileInputStream excellFile2 = new FileInputStream(new File(Filename2));
		         
	          XSSFWorkbook workbook1 = new XSSFWorkbook(excellFile1);
	          XSSFWorkbook workbook2 = new XSSFWorkbook(excellFile2);
	          
	            XSSFSheet sheet1 = workbook1.getSheetAt(0);
	            XSSFSheet sheet2 = workbook2.getSheetAt(8);
	            
	            int firstRow1 = sheet1.getFirstRowNum();
	            int lastRow1 = sheet1.getLastRowNum();
	            
	            
	            for(int i=firstRow1; i <= lastRow1; i++) 
	            {
			
		                XSSFRow row1 = sheet1.getRow(i);
		                XSSFRow row2 = sheet2.getRow(i);
		                
		                int firstCell1 = row1.getFirstCellNum();
		                int lastCell1 = row1.getLastCellNum();
		                	                
         	            int intCounter = 0;
         	          
		              for(int j=firstCell1; j <= lastCell1-1; j++)
		              {
		              
		            	  
		            	  XSSFCell cell1 = row1.getCell(j);
		                  XSSFCell cell2 = row2.getCell(j);
		                  
		                  
		                  if (((cell1.getStringCellValue().trim()).toString().toUpperCase()).contains((cell2.getStringCellValue().trim()).toString().toUpperCase()))
		                  {	  
		                	  
                		
            	             if (j == lastCell1-1 && intCounter == lastCell1-1 )       	
            	            {
            	            	 
            	            	 XSSFCell cell3 = row2.createCell(j+1);
            	            	 cell3.setCellValue("True"); 
            	            	 intCounter = 0;
            	            	 break;
            	            } 
   		                  	else{	          
	  	                	  	 
		                	     if (j ==  lastCell1-1 && intCounter != lastCell1-1 )
	              		        {
	            		        	
		                	    	 XSSFCell cell3 = row2.createCell(j+1);
		                	    	 cell3.setCellValue("False");    
		                	    	 //intCounter = 0;
		                	    	 break;
	            		        	
	            		        }
		                	     
		                  	}
            	             intCounter=intCounter+1;
            	             
		                  }
		                  
		                  
		                  }
		              
		                  
		              }	 
	            
	            excellFile1.close();
	            excellFile2.close();
	            
				FileOutputStream fileOut = new FileOutputStream(Filename2);
				workbook2.write(fileOut);
				fileOut.flush();
				fileOut.close();
	            
		       }
	public static HashMap<String,String> getTableHash(WebElement table, int colIndex1, int colIndex2, boolean typeIsInput)
	{
		HashMap<String,String> tableMap = new HashMap<String,String>();
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		System.out.print("table found: " + tableBody.getAttribute("id"));
		List<WebElement> tableRows = tableBody.findElements(By.tagName("tr"));
		System.out.println("NUMROWS: " + tableRows.size());
		for(WebElement row : tableRows)
		{
			List<WebElement> cols = row.findElements(By.tagName("td"));
			String taxName = cols.get(colIndex1).getText().trim();
			String taxValue = "";
			if(typeIsInput)
			{
				taxValue = cols.get(colIndex2).findElement(By.tagName("input")).getAttribute("value").trim();       //cols.get(colIndex2).getAttribute("value").trim();
			}
			else
			{
				taxValue = cols.get(colIndex2).getText().trim();
			}

			tableMap.put(taxName, taxValue);
			//Log.info("TAXNAME: "+taxName + " TAXVALUE: "+taxValue);
		}

		return tableMap;
	}

	public static List<HashMap<String,String>> getFullTableHash(WebElement table) throws Exception
	{
		List<HashMap<String,String>> tableMap = new ArrayList<HashMap<String,String>>();
		List<String> headers = new ArrayList<String>();


		//Get the column headers
		List<WebElement> colHeaderElements = table.findElements(By.tagName("th"));
		for(WebElement colHeaderEl : colHeaderElements)
		{
			headers.add(colHeaderEl.getText());
		}

		WebElement tableBody = table.findElement(By.tagName("tbody"));
		System.out.print("table found: " + tableBody.getAttribute("id"));
		List<WebElement> tableRows = tableBody.findElements(By.tagName("tr"));
		System.out.println("NUMROWS: " + tableRows.size());

		//get the row values
		for(WebElement row : tableRows)
		{
			List<WebElement> cols = row.findElements(By.tagName("td"));
			HashMap<String,String> rowHash = new HashMap<String,String>();
			int i = 0;
			for(WebElement col : cols)
			{
				if(!(headers.get(i).trim().equals("")))
				{
					rowHash.put(headers.get(i), col.getText());
				}
				i++;
			}
			tableMap.add(rowHash);
		}
		return tableMap;
	}
	/*
            Iterates over each row and cell in an excel file and returns a list of hashmap values for each row and cell value.
         */
	public static List<HashMap<String,String>> readExcelFile(File file, boolean skipFirstLine,int sheetNum) throws FileNotFoundException
	{
		String filePath = file.getAbsolutePath();
		Log.info("Reading file: " + filePath);
		try
		{
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(sheetNum);
			Iterator<Row> rowIterator = sheet.iterator();
			HashMap<String,String> map = new HashMap<String,String>();
			List<String> headers = new ArrayList<String>();
			List<HashMap<String,String>> cellValues = new ArrayList<HashMap<String,String>>();

			//skip first
			if(skipFirstLine)
			{
				rowIterator.next();
			}

			//set headers flag
			boolean areHeadersLoaded = false;

			//Iterate through xls and get cells
			while(rowIterator.hasNext())
			{
				Row row = rowIterator.next();
				HashMap<String,String> rowVals = new HashMap<String,String>();
				Iterator<Cell> cellIterator = row.cellIterator();
				//load in headers first
				if(!areHeadersLoaded)
				{
					// System.out.println("Loading row headers...");
					while(cellIterator.hasNext())
					{
						Cell cell = cellIterator.next();
						headers.add(cell.getStringCellValue());
					}
					//System.out.println("Row Headers Loaded");
					areHeadersLoaded = true;
				}
				else
				{
					//Iterate through rows to retrieve cell data
					while(cellIterator.hasNext())
					{
						Cell cell = cellIterator.next();
						String header;
						String value;
						switch(cell.getCellType())
						{
							case Cell.CELL_TYPE_STRING:
								//headers.add(cell.getStringCellValue());
								header = headers.get(cell.getColumnIndex());
								value = cell.getStringCellValue();
								rowVals.put(header,value);
								break;
							case Cell.CELL_TYPE_NUMERIC:
								header = headers.get(cell.getColumnIndex());
								value = Integer.toString((int) cell.getNumericCellValue());
								rowVals.put(header,value);
								break;
							case Cell.CELL_TYPE_BOOLEAN:
								header = headers.get(cell.getColumnIndex());
								value = Boolean.toString(cell.getBooleanCellValue());
								rowVals.put(header,value);
								break;
							default:
						}

					}
					cellValues.add(rowVals);

				}
			}

			return cellValues;
		}
		catch (Exception e)
		{
			Log.error("Error reading excel file: "+filePath);
			e.printStackTrace();
			return null;
		}



	}
	

/*
 	Author     		:	Aashish Bhutkar
 	Purpose     	:	Function for setting up the chrome driver options.
 	Output			: 	driver - webdriver for the chrome window launched. 	 					
*/
	
	public static WebDriver fn_SetChromeDriverProperties()
	{
/*		WebDriver driver;
		
    	//TODO: Logic to force chrome to save data to user defined location.
    	//String strDownloadLocation;
		//strDownloadLocation = "C:\\QAARIA\\src\\testData\\";//--user-data-dir=
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("download.prompt_for_download", true);
		//chromePrefs.put("download.default_directory", strDownloadLocation);
		ChromeOptions chrmoptnDownloadLocation = new ChromeOptions();
		chrmoptnDownloadLocation.setExperimentalOption("prefs", chromePrefs);
		chrmoptnDownloadLocation.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		chrmoptnDownloadLocation.addArguments("%LOCALAPPDATA%\\Google\\Chrome\\User Data\\Default");
		DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
		chromeCapabilities.setCapability(ChromeOptions.CAPABILITY,	chrmoptnDownloadLocation);				
    	
		//TODO: Set Chrome driver as the driver to launch the test and maximize it
		System.setProperty("webdriver.chrome.driver", VerificationMethods.chromeDriverPath);
		driver = new ChromeDriver(chromeCapabilitieschrmoptnDownloadLocation);
		driver.manage().window().maximize();
		
		return driver;*/
		
		WebDriver driver;
		
    	//TODO: Logic to force chrome to save data to user defined location.
    	String strDownloadLocation;
		//strDownloadLocation = "C:\\QAARIA\\src\\testData\\";
		Map<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("download.prompt_for_download", true);
		//chromePrefs.put("download.default_directory", strDownloadLocation);
		ChromeOptions chrmoptnDownloadLocation = new ChromeOptions();
		chrmoptnDownloadLocation.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
		chromeCapabilities.setCapability(ChromeOptions.CAPABILITY,	chrmoptnDownloadLocation);				
    	
		//TODO: Set Chrome driver as the driver to launch the test and maximize it
		System.setProperty("webdriver.chrome.driver", VerificationMethods.chromeDriverPath);
		driver = new ChromeDriver(chromeCapabilities);
		driver.manage().window().maximize();
		
		return driver;		
	}

/*
 	Author     		:	Madhavi JN
 	Purpose     	        :	Function for Converting CSV to Excel
 	Output			: 	Excel file	 					
*/

	//Function to ConvertCSVTOEXCEL 
	//Input Parameters:- CSV and Excel path
	public void ConvertCSVTOEXCEL(String CSVpath,String Excelpath) throws IOException
	{
		
		    XSSFWorkbook workBook = new XSSFWorkbook();
	        XSSFSheet sheet = workBook.createSheet("sheet1");
	        String currentLine=null;
	        int RowNum=0;
	        BufferedReader br = new BufferedReader(new FileReader(CSVpath));
	        while ((currentLine = br.readLine()) != null) 
	        {
	            String str[] = currentLine.split(",");
	            XSSFRow currentRow=sheet.createRow(RowNum);
	            RowNum++;
	            for(int i=0;i<str.length;i++)
	            {
	                currentRow.createCell(i).setCellValue(str[i]);
	            }
	        }
	        br.close();
	        FileOutputStream fileOutputStream =  new FileOutputStream(Excelpath);
	        workBook.write(fileOutputStream);
	        fileOutputStream.close();

    }
}