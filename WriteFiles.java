/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	WriteFiles 
 Purpose     		: 	Purpose of this Utility is :
						1. To write various type of files.
						2. Currently focused on excel files; later can be extended for various types of files. 
  
 Date       		:	12/07/2015
 Version Information:	Version 1.0
 
 PreCondition 		:	None.
 Test Steps 		:	None.  	
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/


package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;


/*    
	Function fn_AppendExcelFile: This method will append ONLY a ROW for the expected file.
	Inputs: strFileLocationName - Fully Qualified Name for the excel file to be appended.
			strWorkSheetName - The Work Sheet which needs to have data appended.
			lstFileContents	- list of String items which need to be appended in the excel.
	
	Outputs: Boolean status for the Success of the File append.	   						
*/	

public class WriteFiles extends VerificationMethods {
	
	public Boolean fn_AppendExcelFile(String strFileLocationName, String strWorkSheetName, List<String> lstFileContents) 
			throws FileNotFoundException {
		
		Boolean blnWriteFileSuccess = false;
		
		try {
		
			//TODO: Create objects for handling the excel workbook and process the Append Logic.
			FileInputStream fisExcelSheet = new FileInputStream(strFileLocationName);
			XSSFWorkbook xssfWorkBook = new XSSFWorkbook(fisExcelSheet);
			XSSFSheet xssfWorkSheet = xssfWorkBook.getSheet(strWorkSheetName);
			
			Integer intLastRow = xssfWorkSheet.getLastRowNum();	//get the last row number of the current sheet.
			xssfWorkSheet.createRow(intLastRow+1);	//add a row at the end of the sheet.
			intLastRow = xssfWorkSheet.getLastRowNum();	//now recalculate the last row for adding data.
			XSSFRow xssfRow = xssfWorkSheet.getRow(intLastRow);	//set index to the last row to add data.
			
			//TODO: Add data for the last row in the excel using the data received as List of Strings.
			for(Integer intCol = 0; intCol < lstFileContents.size(); intCol ++){
				
				XSSFCell xssfCell = xssfRow.createCell(intCol);
				xssfCell.setCellValue(lstFileContents.get(intCol));
				Log.info("Appedning Excel with value: "+lstFileContents.get(intCol)+" for Row: "+intLastRow+" & column location: "+intCol);
			}
			
			fisExcelSheet.close();
			
			FileOutputStream fosExcelSheet = new FileOutputStream(strFileLocationName);	//purge the details into the excel.
			xssfWorkBook.write(fosExcelSheet);
			fosExcelSheet.flush();
			fosExcelSheet.close();
			
			blnWriteFileSuccess = true;	//set the boolean as true to mark success into file writing.
		}
		catch (Exception exception){
			Log.error("There is an exception while Appending to the Excel File with execption as: "+exception.toString());
			Reporter.log("There is an exception while Appending to the Excel File with execption as: "+exception.toString());			
		}		
		
		return blnWriteFileSuccess;
	}
}
