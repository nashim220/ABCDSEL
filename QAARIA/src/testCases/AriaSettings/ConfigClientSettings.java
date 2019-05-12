package testCases.AriaSettings;

import appModules.AriaDashboard;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import com.sun.xml.internal.bind.v2.TODO;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.AriaDashboardPageObject;
import pageObjects.ClientSettingsPageObject;
import pageObjects.LocaleSettingsPageObject;
import testCases.BaseTestCase;
import utility.EnvironmentDetails;
import utility.Log;
import utility.ReadFiles;
import utility.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by joseph.gannon on 6/10/2015.
 * Edited by Abhishek on 6/1/2016
 */
public class ConfigClientSettings extends BaseTestCase
{
    private static final String TEST_NAME = "Verify Client Settings";

   /* @Override
    public void setTestCaseName(String testName)
    {
        super.setTestCaseName(testName);
    }*/
    
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
    
    @Test//(dataProvider="objectTestData", description=TEST_NAME)
    public void verifyClientSettings() throws Exception
    {
        this.setTestCaseName(TEST_NAME);
        Log.startTestCase(testCaseName);
        //TODO: Read Excel file and retrieve hashmaps for each worksheet
        String clientSettingsPath = System.getProperty("user.dir")
                + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
                + System.getProperty("file.separator") + "Client_Settings.xlsx";

        File clientSettingsFile = new File(clientSettingsPath);
        List<HashMap<String,String>> miscSettingsSheet = readExcelFile(clientSettingsFile,false,0);
        List<HashMap<String,String>> localeSettings = readExcelFile(clientSettingsFile,true,1);
        //List<HashMap<String,String>> localeSettings = ReadFiles.readExcelFile(clientSettingsFile, "Locale Settings");
        //List<HashMap<String,String>> functionalAcctGroups = readExcelFile(clientSettingsFile,false,4);

        //TODO: Login
        //AriaLoginLogout.appLogin(driver, "joseph.gannon_uat", "ATGmsc123!");

        //TODO: Navigate to Configuration
        driver.findElement(AriaDashboardPageObject.getConfiguration()).click();

        //TODO: Navigate to Client Settings
        Thread.sleep(1000);
        driver.findElement(AriaDashboardPageObject.getClientSettings()).click();

        //TODO: Run Misc Settings Check
       verifyAriaSettings(miscSettingsSheet,AriaDashboardPageObject.getMiscSettings(),"Title","Default Value","Default Value",false);

        //TODO: Run Locale Settings Check
       	verifyLocaleSettings(localeSettings);

        //TODO: Run Functional Account Groups
        //verifyFunctionalAccountGroups(functionalAcctGroups);

        Thread.sleep(10000);


    }
   /* public void verifyFunctionalAccountGroups(List<HashMap<String,String>> settingsSheet) throws Exception
    {
        driver.findElement(ClientSettingsPageObject.getFuncAcctGroupsLink()).click();
        Thread.sleep(2000);
        WebElement table = driver.findElement(By.tagName("table"));
        List<HashMap<String,String>> funcHashList = this.getFullTableHash(table);

        //Compare the number of datasets in file vs web
        if(!(settingsSheet.size() == funcHashList.size()))
        {
            Log.error("Error: Number of Functional Account Groups in Aria does not match the number in the settings sheet!");
        }
        for(HashMap<String,String> map : settingsSheet)
        {

              for(HashMap<String,String> excelMap : settingsSheet)
              {
                  String key = "Group No";
                  System.out.println("Web: " + map.get(key) + " || Excel: " + excelMap.get(key)+ " Equals: "+map.equals(excelMap));

              }
              System.out.println("END");
//            for(String key : map.keySet())
//            {
//                 Log.info("KEY: "+key+" || Value: "+map.get(key));
//            }
//            Log.info("\n");
        }

    }*/
    public void verifyLocaleSettings(List<HashMap<String,String>> settingsSheet)
    {

        driver.findElement(AriaDashboardPageObject.getLocaleSettings()).click();
        driver.findElement(LocaleSettingsPageObject.getEnglishLocale()).click();
        System.out.println("&&&&&&&&&&&&&&&&&&&&& VALIDATING LOCALE SETTINGS &&&&&&&&&&&&&&&&&&&&&");
        WebElement table = driver.findElement(LocaleSettingsPageObject.getLocaleSettingsTable());
        HashMap<String,String> localeWebTable = getTableHash(table,1,2,true);
       
        String ExcelKey= "";
        String ExcelValue = "";
        Map<String,String> ExcelMap = new HashMap<String,String>(); 
        
        for(Map<String, String> map : settingsSheet)
        {
        	ExcelKey = map.get("Name:");
        				
        	if(map.get("Translated Name (SGAS Name)")==null){
        		ExcelValue = "";
        	}
        	else{
        		ExcelValue =  map.get("Translated Name (SGAS Name)");
        	}
        	if(ExcelKey != "")
        	{
        		ExcelMap.put(ExcelKey, ExcelValue);
        	}
        }
         
        
        for(String key : ExcelMap.keySet())
		{
		     
	            boolean allClear = true;
	            if( localeWebTable.get(key)==null){
	            	Log.info("ERROR : "+key);
	            }
	            if((!(ExcelMap.get(key).toLowerCase().trim().equalsIgnoreCase(localeWebTable.get(key).toLowerCase().trim()))))
	            {
	                Log.info("Fields doesn't match: ##############	"+key+"	##############");
	                Log.info("Excel Value : "+ExcelMap.get(key).toLowerCase()+" #### Application Value : "+localeWebTable.get(key).toLowerCase());
	            }
	            if((ExcelMap.get(key).toLowerCase().trim().equalsIgnoreCase(localeWebTable.get(key).toLowerCase().trim()))){
	            	Log.info("Matched: ########################	"+key+"	########################");
	            	Log.info("Excel Value : "+ExcelMap.get(key).toLowerCase()+" #### Application Value : "+localeWebTable.get(key).toLowerCase());
	            }
	            
		}
        
        
        	
	}
    public void verifyAriaSettings(List<HashMap<String,String>> settingsSheet, By settingsLink, String primaryKey, String secondaryValueKey, String valueKey, boolean typeIsInput)
    {
        //TODO: Navigate to Misc Settings
        driver.findElement(settingsLink).click();
        
        System.out.println("&&&&&&&&&&&&&&&&&&&&& VALIDATING MISCELLANEOUS SETTINGS &&&&&&&&&&&&&&&&&&&&&");
        //TODO: Iterate through the settings and match them to the datafile
        WebElement miscTable = driver.findElement(ClientSettingsPageObject.getMiscTable());
        HashMap<String,String> ariaTableMap = getTableHash(miscTable, 0, 2, typeIsInput);
        boolean allClear = true;
        
        for(Map<String,String> map : settingsSheet)
        {
        	String name = "";
        	String value = "";
        	/*if(map.get(primaryKey)==null || map.get(valueKey)==null){
        		name = map.get(primaryKey);
        		value = "";
        	}*/
        	//else{
            name = map.get(primaryKey);
            if(map.get(valueKey)==null){
            	value = "";
            }
            else{
            	value = map.get(valueKey).toLowerCase().trim();
        	}
            if(value.contains("leave as is"))
            {
                value = map.get(secondaryValueKey).toLowerCase().trim();
            }
            if(ariaTableMap.containsKey(name))
            {
                String webValue = ariaTableMap.get(name).toLowerCase().trim();
                //Log.info("AriaValue: "+ariaMiscMap.get(name));
                //Log.info("Name: " + name + " || Value: " + value + "\n");
                //TODO: Replace with assertion
                if(!(webValue.equals(value)))
                {
                    allClear = false;
                    Log.info("Does not match! Field: ##############	"+name+" ##############");
                    Log.info("AriaValue: "+webValue+" ExcelValue: "+value);
                }
                else{
                	Log.info("Match! Field: ##############	"+name+" ##############");
                	Log.info("AriaValue: "+webValue+" ExcelValue: "+value);
                }
                //assertTrue(allClear);
                //assertEquals(ariaMiscMap.get(name).toLowerCase(),value.toLowerCase());
            }
            else
            {
                Log.error("NOT FOUND: "+"Name: "+name+"	Value: "+value+"\n");
            }
        }
    }
//    @DataProvider(name = "objectTestData")
//    public Object[][] data() throws DataDrivenFrameworkException
//    {
//        Utils objUtils=new Utils();
//        return objUtils.data("TestCaseDetails", "verifyClientSettings");
//    }
}
