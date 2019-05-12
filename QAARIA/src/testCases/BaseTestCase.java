package testCases;

import appModules.AriaLoginLogout;
import appModules.SelectBrowser;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import javafx.util.Pair;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageObjects.AriaDashboardPageObject;
import pageObjects.TaxSettingsPageObject;
import utility.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by joseph.gannon on 4/23/2015.
 */
public class BaseTestCase extends VerificationMethods
{
    public static WebDriver driver;// = new FirefoxDriver();
    public static StringBuffer verificationErrors = new StringBuffer();
    public static String testCaseName;
    public String username;
    public String password;
    public WebDriverWait wait;
    public static String BASE_TESTDATA_PATH = System.getProperty("user.dir")
            + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
            + System.getProperty("file.separator");

    public void setTestCaseName(String testName)
    {
        this.testCaseName = testName;
    }
    @Parameters("browser")
    
    @BeforeClass()
    public void beforeClass(@Optional String browser) throws Exception
    {
        browser = EnvironmentDetails.browser;
        EnvironmentDetails env = new EnvironmentDetails();
        username = env.STRUSERNAME;
        password = env.STRPASSWORD;



        try{

            DOMConfigurator.configure("log4j.xml");
            Log.startTestCase(testCaseName);

            SelectBrowser objBrowser = new SelectBrowser();
            driver = objBrowser.initDriver(browser);
            wait = new WebDriverWait(driver,10);

            Log.info("Successfully Selected browser to launch application: " + browser);
            Reporter.log("Successfully Selected browser to launch application: " + browser);
        }
        catch (Error e){
            verificationErrors.append(e.toString());
            try{
                utility.Utils.takeScreenshot(driver, testCaseName);
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
            Log.error(testCaseName + "Browser not found");
            addVerificationFailure(e);
        }
    }


    @AfterClass() //groups = { "Sanity" })
    public void afterClass() throws Exception
    {
        try{
            AriaLoginLogout.appLogout(driver);
            driver.quit();
        }
        catch (Error e){
            verificationErrors.append(e.toString());
            utility.Utils.takeScreenshot(driver, "testcasename");
            Log.error(testCaseName +" || Logout -- FAIL\n");
            Reporter.log(testCaseName + "|| Logout -- FAIL \n");
            addVerificationFailure(e);
        }
        //	 Final Code. Log exceptions if any
        System.out.println("ERRORS: "+getVerificationFailures().size());
        if (getVerificationFailures().isEmpty()){
            Log.info(testCaseName + " || All Data iterated successfully. -- PASS \n");
            Reporter.log(testCaseName  + " || All Data iterated successfully. -- PASS \n");
        }

        else{
            throw new VerificationException(getVerificationFailures());
        }
    }

    /*
        Iterate over entire page to find a matching element with the give tagname and text value
        (Only use as a last resort to find an element on a page)
     */
    public WebElement getCustomElement(WebDriver driver,Pair tuple)
    {
        String tag = tuple.getKey().toString();
        String text = tuple.getValue().toString();

        List<WebElement> eles = driver.findElements(By.tagName(tag));
        for(WebElement ele : eles)
        {
            if(ele.getText().equals(text))
            {
                return ele;
            }
        }
        return eles.get(0);
    }

    /*
        Iterates over each row and cell in an excel file and returns a list of hashmap values for each row and cell value.
     */
    public List<HashMap<String,String>> readExcelFile(File file, boolean skipFirstLine) throws FileNotFoundException
    {
        String filePath = file.getAbsolutePath();
        Log.info("Reading file: " + filePath);
        try
        {
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
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
                                value = Double.toString(cell.getNumericCellValue());
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
            Iterates over each row and cell in an excel file and returns a list of hashmap values for each row and cell value.
         */
    public List<HashMap<String,String>> readExcelFile(File file, boolean skipFirstLine,int sheetNum) throws FileNotFoundException
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

    public HashMap<String,String> getTableHash(WebElement table, int colIndex1, int colIndex2, boolean typeIsInput)
    {
        HashMap<String,String> tableMap = new HashMap<String,String>();
        WebElement tableBody = table.findElement(By.tagName("tbody"));
        System.out.print("table found: " + tableBody.getAttribute("id"));
        List<WebElement> tableRows = tableBody.findElements(By.tagName("tr"));
        System.out.println("NUMROWS: " + tableRows.size());
        boolean headersRead = false;
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

    public List<HashMap<String,String>> getFullTableHash(WebElement table) throws Exception
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
    public void writeToExcel(List<HashMap<String,String>> listHash, String filePath)
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet worksheet = workbook.createSheet("DataSheet");
            int numRows = listHash.size();
            int i = 0;
            for(HashMap<String,String> map : listHash)
            {
                XSSFRow row = worksheet.createRow(i);
                //Populate the header row first then add the first data row
                if(i==0)
                {
                    int x = 0;
                    XSSFRow row1 = worksheet.createRow(i+1);
                    for(String key : map.keySet())
                    {
                        //populating headers
                        XSSFCell headerCell = row.createCell(x);
                        headerCell.setCellValue(key);

                        //populating data for the first row
                        XSSFCell dataCell = row1.createCell(x);
                        dataCell.setCellValue(map.get(key).toString());
                        x++;
                    }
                    i++;

                }
                else
                {
                    //populate the data
                    int x = 0;
                    for(String key: map.keySet())
                    {
                        XSSFCell dataCell = row.createCell(x);
                        dataCell.setCellValue(map.get(key).toString());
                        x++;
                    }
                }
                i++;
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public boolean isAttribPresent(WebElement element, String attribute)
    {
        boolean result = false;
        try
        {
            String value = element.getAttribute(attribute);
            if(value != null)
            {
                result = true;
            }
        }
        catch (Exception e){}
        return result;
    }
}
