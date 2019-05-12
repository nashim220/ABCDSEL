package testCases.Reports;

import appModules.AriaDashboard;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testCases.BaseTestCase;
import utility.Log;
import utility.Utils;

/**
 * Created by joseph.gannon.
 */
public class BillingSummary extends BaseTestCase
{
    @Test(dataProvider="objectTestData", description="Fin Rpt: Billing Summary")
    public void runBillingSummaryReport(String username,String password, String acctNum) throws Exception
    {
        Log.startTestCase("Fin Rpt: Billing Summary");
        AriaLoginLogout.appLogin(driver, username, password);
        AriaDashboard.searchByAcctNum(driver,username,password,acctNum);
        //TODO: Select an account
        //TODO: Run immediate billing
        //TODO: Record generated invoice information
        //TODO: Generate invoice Transaction detail by Date
        //TODO: Validate immediate invoice information in report against info in aria (charges etc)
        //TODO: Validate scheduled billing invoice information in report against info in Aria
        //TODO: Verify Invoice transactions by date report match billing generated.

    }

    @DataProvider(name = "objectTestData")
    public Object[][] data() throws DataDrivenFrameworkException
    {
        Utils objUtils=new Utils();
        return objUtils.data("Aria", "reportBillingSummary");
    }
}
