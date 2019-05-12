package WorkFlow;


import appModules.AccountFunctions;
import appModules.AriaLoginLogout;
import appModules.AriaNewAccountCreation;
import appModules.AriaWorkFlow;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;
import net.sourceforge.htmlunit.corejs.javascript.tools.shell.Environment;
import org.apache.log4j.xml.DOMConfigurator;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.NewAccountCreation;
import pageObjects.ParentChildNewAccount;
import pageObjects.WorkFlowPageObject;
import testCases.AccountTestCases.Cls_ParentChildNewAccountCreation;
import utility.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class Cls_OverBudgetWorkflow extends VerificationMethods
{
    public static String strTestCaseName = "Verify Over Budget Workflow Email";

    @Test(dataProvider="objectTestData", description="AllTransactionsByAccount")
    public static void fn_VerifyOverBudgetWorkflow(String childAcctNum, String blerg) throws Exception
    {
        String clientId = "3760701";

        Log.startTestCase(strTestCaseName);
        //TODO: Load Usage over budget
        String LOAD_USAGE_FILE_PATH = BASE_TESTDATA_PATH + "\\record_usage_updated.csv";
        String currentDate = Utils.getDateYearFirst();
        AccountFunctions.loadAccountUsage(LOAD_USAGE_FILE_PATH, childAcctNum, currentDate);
        //TODO: Add Account to overbudget Notification
        JSONObject json = AriaWorkFlow.addAccountToBudgetWorkflow(childAcctNum);
        //TODO: Kickoff WF OverBudget Notification
        JSONObject jsonProcess = AriaWorkFlow.kickOffWorkFlowBudgetNotification(clientId);
        //TODO: Verify Acct was sent Over-Budget email
        String jsonString = jsonProcess.toString();
        Log.info("JSON STRING: "+jsonString);
        assertTrue(jsonString.contains("868563"));
        Log.endTestCase(strTestCaseName);
    }

    //Reading data for the test
    @DataProvider(name = "objectTestData")
    public Object[][] testcasedata() throws DataDrivenFrameworkException
    {
        Utils objUtils=new Utils();
        return objUtils.data("TestCaseDetails", "verifyOverBudgetWorkflow");
    }


    @AfterTest
    public void afterMethod() throws Exception
    {
        //TODO: Logs out of the application & quit the driver
    }

}
