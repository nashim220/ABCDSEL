package testCases.AriaSettings;

import appModules.AriaDashboard;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.AccountFieldsPageObject;
import pageObjects.AriaDashboardPageObject;
import testCases.BaseTestCase;
import utility.Log;
import utility.Utils;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by joseph.gannon
 */
public class CustomAccountFields extends BaseTestCase
{

    @Test//(dataProvider="objectTestData", description="Verify Custom Account Fields")
    public void verifyCustomAccountFields() throws Exception
    {
        Log.startTestCase("Verify Custom Account Fields");
        String customAcctFieldsPath = System.getProperty("user.dir")
                + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
                + System.getProperty("file.separator") + "Custom_Acct_Fields.xlsx";
        File customFieldsFile = new File(customAcctFieldsPath);
        List<HashMap<String,String>> customFieldsSettingsHashList= readExcelFile(customFieldsFile,false,0);
        AriaLoginLogout.appLogin(driver, "joseph.gannon", "ATGmsc123");

        //TODO: Navigate to Configuration
        Log.info("Navigating to Configuration");
        driver.findElement(AriaDashboardPageObject.getConfiguration()).click();

        //TODO: Navigate to Client Settings
        Log.info("Navigating to Client Settings");
        driver.findElement(AriaDashboardPageObject.getClientSettings()).click();

        //TODO: Navigate to Account Fields
        Log.info("Navigating to Account Fields");
        driver.findElement(AriaDashboardPageObject.getAccountFields()).click();

        //TODO: Retrieve all account field data
        Log.info("Retrieving all account fied data");
        WebElement suppFieldTable = driver.findElement(AccountFieldsPageObject.getAccountFieldsTable());
        HashMap<String,String> webFieldListHash = this.getTableHash(suppFieldTable,0,1,false);    //this.getFullTableHash(suppFieldTable);

        for(String key: webFieldListHash.keySet())
        {
            System.out.println("KEY: "+key+" || "+webFieldListHash.get(key));
        }
        Log.info("Size of web: "+webFieldListHash.size());
        //TODO: Cross-reference all account filed data
        Log.info("Comparing Aria Table to Excel Table");
       // HashMap<String,String> sortedWeb = new TreeMap<String,String>(webFieldListHash);
        assertEquals(webFieldListHash, customFieldsSettingsHashList, "Web Table Matches Excel Table!");

        Log.endTestCase("Verify Custom Account Fields");
    }


//    @DataProvider(name = "objectTestData")
//    public Object[][] data() throws DataDrivenFrameworkException
//    {
//        Utils objUtils=new Utils();
//        return objUtils.data("TestCaseDetails", "verifyCustomAccountFields");
//    }
}
