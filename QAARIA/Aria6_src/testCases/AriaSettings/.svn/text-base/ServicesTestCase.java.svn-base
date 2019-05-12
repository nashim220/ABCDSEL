package testCases.AriaSettings;

import appModules.AriaDashboard;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import net.sourceforge.htmlunit.corejs.javascript.tools.shell.Environment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import pageObjects.AriaDashboardPageObject;
import pageObjects.ServicesPageObject;
import testCases.BaseTestCase;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joseph.gannon on 5/13/2015.
 */
public class ServicesTestCase extends BaseTestCase
{

    @Parameters("browser")
    @BeforeClass()
    public void beforeClass(@Optional String browser) throws Exception
    {
        super.beforeClass("chrome");
        EnvironmentDetails env = new EnvironmentDetails();
        appModules.AriaLoginLogout.appLogin(driver, env.STRUSERNAME,env.STRPASSWORD);
    }
    @Test(dataProvider="objectTestData", description="Change Billing Contact Info")
    public void checkTaxStatus(String acctNum) throws Exception
    {
        //login and search
        Log.startTestCase("Checking Tax Status of Services");
        //AriaDashboardTC ad = new AriaDashboardTC();
        //ad.searchByAcctNum(username, password, acctNum);
//        AriaDashboard.searchByAcctNum(driver, username, password, acctNum);

        //navigate to services
        driver.findElement(AriaDashboardPageObject.getProducts()).click();
        Thread.sleep(1000);
        driver.findElement(AriaDashboardPageObject.getServices()).click();

        //get each service in the table
        Thread.sleep(4000);
        Log.info("Retrieving all services....");
        WebElement parentTable = driver.findElement(By.id("DataTables_Table_0"));
        WebElement table = parentTable.findElement(By.tagName("tbody"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        List<String> services = new ArrayList<String>();
        for(WebElement el : rows)
        {
            services.add(el.getText().split(" ")[0]);
        }

        //navigate through each service and check tax status
        for(String service : services)
        {
            Log.info("Checking tax Status of service: " + service);
            Thread.sleep(3000);
            driver.findElement(By.id("serviceNumberIndex_row_"+service)).click();
            Thread.sleep(7000);
            String taxStatus = driver.findElement(By.xpath("//*[@id=\"Services_taxable\"]")).getText();
            WebElement taxableStatusElement = driver.findElement(By.xpath("//*[@id=\"Services_taxable\"]"));
            List<WebElement> taxStatusEl = taxableStatusElement.findElements(By.tagName("option"));
            Log.info("Size: "+taxStatusEl.size());
            for(WebElement el: taxStatusEl)
            {
                if(isAttribPresent(el,"selected"))
                {
                    Log.info("Found Selected: "+el.getText());
                    taxStatus = el.getText();
                }
            }

            verifyTrue(taxStatus.equals("Yes"), "Checking Taxable Status for service: " + service);
            Log.info("Service: "+service+" || Status: "+taxStatus);
            driver.findElement(ServicesPageObject.servicesCloseBtn()).click();
            Thread.sleep(2000);

        }





    }
    @DataProvider(name = "objectTestData")
    public Object[][] data() throws DataDrivenFrameworkException
    {
        Utils objUtils=new Utils();
        return objUtils.data("TestCaseDetails", "ServicesTestCase");
    }
}
