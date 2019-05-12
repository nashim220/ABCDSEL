
/*
 Author     		:	Joseph Gannon
 Class Name			: 	Cls_CreateAccountWithWorkplace
 Purpose     		: 	Purpose of this file is :
						1. To create New Account as stated in QAABE-170.
						2. Verify rollup billing for child account rolling up to parent account

 Date       		:	07/20/2015
 Modified Date		:	07/20/2015
 Version Information:	Version 2.0

  PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails" & "ManuallyCreateNewAccount" worksheet for excel "TestData.xls".

 Test Steps 		:	1. Login using valid role credentials for creating New account.
 						2. Navigate to the Accounts -> Create New Accounts left Nav menu.
 						3. On Create New Account page, add details for new account.
 						4. Click Save and verify the success of added Account.
 						5. Navigate to the newly created account by clicking the Hyper link and then Verify data.
 						6. Add Child Account to Parent Account
 						7. Enable rollup billing for Child Account


 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services -
 						All Rights Reserved
*/

package testCases.AccountTestCases;

import appModules.AccountFunctions;
import appModules.AriaLoginLogout;
import appModules.AriaNewAccountCreation;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.AriaAccountPageObject;
import pageObjects.AriaAdHocSearch;
import pageObjects.AriaDashboardPageObject;
import testCases.AriaDashboard;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class Cls_CreateAccountWithWorkplace extends VerificationMethods
{

    public static WebDriver driver;
    public static String strTestCaseName = "Create Account With Workplace";
    public static String[] arrSearchFields = null;	// this string array will hold the Fields names for the Client Defined Fields.
    public static String strFieldValue = null; 	// this string will hold the subsequent field value for the Client Defined Fields.
    public static WebDriverWait webWait;
    public static WebElement webElement;


    //SETUP
    @BeforeClass
    public void beforeMethod()throws Exception
    {
        DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.

        //Set Chrome driver
        String driverPath = chromeDriverPath;
        System.setProperty("webdriver.chrome.driver",driverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();	// this code for maximizing the size of launched window.
        webWait = new WebDriverWait(driver, 90);	// initialize the webwait

        //Environment Details for Logging in.
        EnvironmentDetails objEnv = new EnvironmentDetails();
        Log.info("Login to application");

        //This parameters are taken from Exec file and its reference is available in EnvironmentDetails
        AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);
    }

    /*
        Purpose: To create multiple accounts with parent-child relationship and enforce rollup billing
        Input: Datafile provided by AriaNewAccountCreation
        Output: None
     */
    @Test(dataProvider = "objectTestData", description = "Create New Account")
    public static void fn_CreateAccountWithWorkplace(String strdFileName, String strdWorksheet, String strFieldNames, String strFieldValues) throws Exception
    {

        Log.startTestCase(strTestCaseName);

        arrSearchFields = strFieldNames.split("#");	// read the field names in this list to be referred latter.
        strFieldValue = strFieldValues;	// read the field values which needs to be passed @ runtime.

        String LOAD_USAGE_FILE_PATH = BASE_TESTDATA_PATH + "record_usage_updated.csv";
        String strNewAccountDataExcelFilePath = BASE_TESTDATA_PATH +"\\"+ strdFileName;

        File newAccountDataExcelFile = new File(strNewAccountDataExcelFilePath);	// object for the excel file.
        List<HashMap<String,String>> lsthmapFileContents = utility.ReadFiles.readExcelFile(newAccountDataExcelFile, strdWorksheet);

        //: Create Parent Account
        Log.info("Creating Parent Account");
        String strParentAcctNum = AriaNewAccountCreation.fn_CreateAccount(driver, lsthmapFileContents, strTestCaseName, arrSearchFields, strFieldValue);
        Log.info("Parent Account Created: " + strParentAcctNum);
        Thread.sleep(5000);
        Log.info("Clicking Accounts In Nav Bar");
        driver.findElement(AriaDashboardPageObject.clickNavBarAccounts()).click();

        //: Create Child Account
        Log.info("Creating Child Account");
        String strChildAccountNum = AriaNewAccountCreation.fn_CreateAccount(driver, lsthmapFileContents, strTestCaseName, arrSearchFields, strFieldValue);
        Log.info("Child Account Created: "+strChildAccountNum);

        //: Establish Relationship & Enable Rollup Billing
        Log.info("Assingning Child to Parent");
        String strUpdateAcctOptions = "senior_acct_no="+strParentAcctNum+"&resp_level_cd=4";
        AccountFunctions.updateAccount(strChildAccountNum,strUpdateAcctOptions);

        //Verify Rollup Billing
        //Navigate to parent account
        Thread.sleep(1000);
        driver.findElement(AriaDashboardPageObject.clickSearch()).click();
        driver.findElement(AriaDashboardPageObject.clickAdHocSearch()).click();
        Thread.sleep(2000);
        driver.findElement(AriaAdHocSearch.searchByField()).sendKeys(strParentAcctNum);
        driver.findElement(AriaAdHocSearch.clickSearchButton()).click();
        Thread.sleep(2000);
        driver.findElement(AriaAdHocSearch.searchResult(strParentAcctNum)).click();
        //Navigate to Manage Child Accounts
        Thread.sleep(3000);
        driver.findElement(AriaAccountPageObject.getManageChildTab()).click();
        Thread.sleep(2000);
        String strResponsibilityLevel = driver.findElement(AriaAccountPageObject.getResponsibilityLevel()).getText();
        assertEquals(strResponsibilityLevel,"4 - Parent Usage & Pay w/Self-Usage Note");


    }


    /*
        Data Settings for the CreateAccountWithWorkplace test case
     */
    @DataProvider(name = "objectTestData")
    public Object[][] testExcelData() throws DataDrivenFrameworkException
    {
        Utils objUtils=new Utils();
        return objUtils.data1("TestCaseDetails", "ManuallyCreateNewAccount");
    }

    /*
        TEARDOWN
     */
    @AfterTest
    public void afterMethod() throws Exception
    {
        //Logs out of the application
        AriaLoginLogout.appLogout(driver);
        //Quitting the driver
        driver.quit();
    }
}
