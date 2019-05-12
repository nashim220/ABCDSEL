package testCases.AriaSettings;

import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.AriaDashboardPageObject;
import pageObjects.InvoiceSettingsPageObject;
import testCases.BaseTestCase;
import utility.Log;
import utility.Utils;

import java.util.List;

/**
 * Created by joseph.gannon on 6/4/2015.
 */
public class InvoiceSuppression extends BaseTestCase
{
    @Test//(dataProvider="objectTestData", description="Change Billing Contact Info")
    public void verifyZeroDollarInvoiceSuppression() throws Exception
    {
        Log.startTestCase("Verify $0.00 invoice setting");
        AriaLoginLogout.appLogin(driver, "joseph.gannon", "ATGmsc123");
        //Select Configuration
        driver.findElement(AriaDashboardPageObject.getConfiguration()).click();

        //Select Billing
        this.getCustomElement(driver, AriaDashboardPageObject.getBillingPair()).click();

        //Select Invoice Settings
        driver.findElement(AriaDashboardPageObject.getInvoiceSettingsLink()).click();

        //Search for Zero
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"DataTables_Table_40\"]")));
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        Log.info("numInputs: "+inputs.size());
        for(WebElement input :inputs)
        {
            if(input.getAttribute("type").equals("search"))
            {
                input.sendKeys("zero");
            }
        }
        Log.info("Suppression Value: " + driver.findElement(InvoiceSettingsPageObject.getSuppressionCell()).getText());
        verifyTrue(driver.findElement(InvoiceSettingsPageObject.getSuppressionCell()).getText().equals("Suppress"));
        Log.endTestCase("Verify $0.00 invoice setting");
    }

//    @DataProvider(name = "objectTestData")
//    public Object[][] data() throws DataDrivenFrameworkException
//    {
//        Utils objUtils=new Utils();
//        return objUtils.data("TestCaseDetails", "verifyZeroDollarInvoiceSuppression");
//    }
}
