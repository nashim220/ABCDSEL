package testCases.AriaSettings;

import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.AriaDashboardPageObject;
import pageObjects.TaxSettingsPageObject;
import testCases.BaseTestCase;
import utility.Log;
import utility.Utils;

import java.io.File;
import java.util.*;

/**
 * Created by joseph.gannon on 6/10/2015.
 */
public class ConfigTaxSettings extends BaseTestCase
{
    @Override
    public void setTestCaseName(String testName)
    {
        super.setTestCaseName(testName);
    }

    @Test//(dataProvider="objectTestData", description="Verify Aria Tax Settings")
    public void verifyTaxSettings() throws Exception
    {
        String testName = "Verify Tax Settings";
        this.setTestCaseName(testName);
        Log.startTestCase(testName);

        //Load tax settings file
        String taxFilePath = System.getProperty("user.dir")
                + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
                + System.getProperty("file.separator") + "Taxation Settings.xlsx";
        File taxFile = new File(taxFilePath);
        List<HashMap<String,String>> fileVals =  this.readExcelFile(taxFile,true);

        //Navigate to billing
        Log.info("Navigating to billing");
        AriaLoginLogout.appLogin(driver, "joseph.gannon", "ATGmsc123");
        driver.findElement(AriaDashboardPageObject.getConfiguration()).click();
        Thread.sleep(1000);
        this.getCustomElement(driver, AriaDashboardPageObject.getBillingPair()).click();

        //Navigate to Tax Settings
        Log.info("Navigating to Tax Settings");
        driver.findElement(AriaDashboardPageObject.getTaxSettingsLink()).click();

        //Itereate through each tax setting and verify data sheet.
        //Load in table values
        WebElement taxTable = driver.findElement(TaxSettingsPageObject.getTaxTable());
        HashMap<String,String> ariaTableValues = getTableHash(taxTable,0,2,false);

        //Compare each value in file for each value in table.
        for(HashMap<String,String> map : fileVals)
        {
            String name = map.get("Name");
            String value = map.get("Value");
            if(ariaTableValues.containsKey(name))
            {
                Log.info("AriaValue: "+ariaTableValues.get(name));
                Log.info("Name: " + name + " || Value: " + value + "\n");
                assertEquals(ariaTableValues.get(name).toLowerCase(),value.toLowerCase());
            }
            else
            {
                Log.error("NOT FOUND: "+"Name: "+name+" || Value: "+value+"\n");
            }
        }
        Log.endTestCase(testName);
    }
//
//    @DataProvider(name = "objectTestData")
//    public Object[][] data() throws DataDrivenFrameworkException
//    {
//        Utils objUtils=new Utils();
//        return objUtils.data("TestCaseDetails", "verifyTaxSettings");
//    }


}
