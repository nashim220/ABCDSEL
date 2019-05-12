/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	ReadFiles 
 Purpose     		: 	Purpose of this Utility is :
						1. To read various type of files.
						2. Currently focused on excel files; later can be extended for various types of files. 
  
 Date       		:	06/24/2015
 Modified Date		:	07/15/2015, 07/27/2015
 Version Information:	Version 3.0
 
 PreCondition 		:	None.
 Test Steps 		:	None.  	
 
 Version Changes 2.0:	Modified the script to read ONLY from ".xls" files.
 
 Version Changes 3.0:	1. As designed by Joe G. have added a switch case to read the typical cells from excel.
 						2. Added another method readExcelFileAsArray to read excel file in an array.
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
/*import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;*/
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.testng.Reporter;

import utility.Log;

public class ReadFiles extends VerificationMethods
{
    public static List<HashMap<String,String>> readExcelFile(File file, String strWorksheet) throws FileNotFoundException
    {
        Log.info("Reading file: "+file.getAbsolutePath()+" and worksheet: "+strWorksheet.toString());
        try
        {
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            //XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.getSheet(strWorksheet);
            //XSSFSheet sheet = workbook.getSheet(strWorksheet);
            Iterator<Row> rowIterator = sheet.iterator();

            //TODO: Create headers and cellValues objects to read the file contents.
            List<String> headers = new ArrayList<String>();
            List<HashMap<String,String>> cellValues = new ArrayList<HashMap<String,String>>();

            //set headers flag
            boolean areHeadersLoaded = false;

            //Iterate through excel and get cells
            while(rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                HashMap<String,String> rowVals = new HashMap<String,String>();
                Iterator<Cell> cellIterator = row.cellIterator();
                //load in headers first
                if(!areHeadersLoaded)
                {
                    while(cellIterator.hasNext())
                    {
                        Cell cell = cellIterator.next();
                        headers.add(cell.getStringCellValue());
                    }
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
            Log.info("File has been read successfully !");            
            return cellValues;
        }
        catch (Exception exception)
        {
            Log.error("ERROR: Error reading excel file.");
            Reporter.log("ERROR: Error reading excel file");
            exception.printStackTrace();
            return null;
        }
    }
    
    
    public static ArrayList<String> readExcelFileAsArray(File file, String strWorksheet) throws FileNotFoundException
    {
        Log.info("Reading file: "+file.getAbsolutePath()+" and worksheet: "+strWorksheet.toString());
        try
        {
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            //XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.getSheet(strWorksheet);
            //XSSFSheet sheet = workbook.getSheet(strWorksheet);
            Iterator<Row> rowIterator = sheet.iterator();

            //TODO: Create headers and cellValues objects to read the file contents.
            List<String> headers = new ArrayList<String>();
            ArrayList<String> cellValues = new ArrayList<String>();

            //set headers flag
            boolean areHeadersLoaded = false;

            //Iterate through excel and get cells
            while(rowIterator.hasNext())
            {
            	//TODO: Define another objects for row &cell iterators to save read data.
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                //TODO: loading the headers first.
                if(!areHeadersLoaded)
                {
                    while(cellIterator.hasNext())
                    {
                        Cell cell = cellIterator.next();
                        headers.add(cell.getStringCellValue());
                    }
                    areHeadersLoaded = true;
                }
                else
                {
                    //TODO: Iterating through rows to retrieve cell data.
                    while(cellIterator.hasNext())
                    {
                        Cell cell = cellIterator.next();
                        String strCellValue;
                        switch(cell.getCellType())
                        {
                            case Cell.CELL_TYPE_STRING:
                                strCellValue = cell.getStringCellValue();
                                cellValues.add(strCellValue);
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                strCellValue = Integer.toString((int) cell.getNumericCellValue());
                                cellValues.add(strCellValue);
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                strCellValue = Boolean.toString(cell.getBooleanCellValue());
                                cellValues.add(strCellValue);
                                break;
                            default:
                        }
                    }
                }
            }
            Log.info("File has been read successfully !");
            return cellValues;
        }
        catch (Exception exception)
        {
            Log.error("ERROR: Error reading excel file.");
            Reporter.log("ERROR: Error reading excel file");
            exception.printStackTrace();
            return null;
        }
    }    
}