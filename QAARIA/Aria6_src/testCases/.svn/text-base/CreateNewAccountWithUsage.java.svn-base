package testCases;

import appModules.*;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.AriaDashboardPageObject;
import pageObjects.StatementsAndInvoicesPageObject;
import utility.Log;
import utility.ReadFiles;
import utility.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by joseph.gannon
 */
public class CreateNewAccountWithUsage extends BaseTestCase
{
    public static String[] arrSearchFields = null;	// this string array will hold the Fields names for the Client Defined Fields.
    public static String strFieldValue = null;
    public static String strTestCaseName = "Create Account With Usage";

    @Test(dataProvider="objectTestData", description="Create Account With Usage")
    public static String createAccountWithUsage(String strdFileName, String strdWorksheet, String strFieldNames, String strFieldValues) throws Exception
    {
        Log.startTestCase(strTestCaseName);

        arrSearchFields = strFieldNames.split("#");	// read the field names in this list to be referred latter.
        strFieldValue = strFieldValues;	// read the field values which needs to be passed @ runtime.

        System.out.println("STARTING THIS TEST CASE");
        String LOAD_USAGE_FILE_PATH = BASE_TESTDATA_PATH + "record_usage_updated.csv";
        String strNewAccountDataExcelFilePath = BASE_TESTDATA_PATH + strdFileName;

        File newAccountDataExcelFile = new File(strNewAccountDataExcelFilePath);	// object for the excel file.
        List<HashMap<String,String>> lsthmapFileContents = utility.ReadFiles.readExcelFile(newAccountDataExcelFile, strdWorksheet);

        // Call for the method to add data to the Create Account Page / Form.

        String newAcctNum = null;
        //TODO: Login
        AriaLoginLogout.appLogin(driver, "joseph.gannon", "ATGmsc123");

        //TODO: Create New Account
        newAcctNum = AriaNewAccountCreation.fn_CreateAccount(driver, lsthmapFileContents, strTestCaseName, arrSearchFields, strFieldValue);
        System.out.println("Acct Number: " + newAcctNum);

        //TODO: Load in record usage for account
        String currentDate = Utils.getDateYearFirst();
        Log.info("Assigning Supplemental Plan");
        AccountFunctions.assignSuppPlan(newAcctNum, "14709");
        Log.info("Loading Account Usage");
        AccountFunctions.loadAccountUsage(LOAD_USAGE_FILE_PATH, newAcctNum, currentDate);

        //TODO: Verify usage loaded
        driver.findElement(AriaDashboardPageObject.getUsageLink()).click();
        driver.findElement(StatementsAndInvoicesPageObject.getFromDateForUsage()).sendKeys(Utils.getAlternateDate(-1));
        driver.findElement(StatementsAndInvoicesPageObject.getRetrieveUsageBtn()).click();

        assertTrue(driver.findElement(StatementsAndInvoicesPageObject.getUsageTable()).isDisplayed());
        return newAcctNum;
    }

    @DataProvider(name = "objectTestData")
    public Object[][] testExcelData() throws DataDrivenFrameworkException
    {
        Utils objUtils=new Utils();
        return objUtils.data1("TestCaseDetails", "ManuallyCreateNewAccount");
    }
//    @DataProvider(name = "objectTestData")
//    public Object[][] data() throws DataDrivenFrameworkException
//    {
//        Utils objUtils=new Utils();
//        return objUtils.data("Aria", "createAccountWithUsage");
//    }
}
