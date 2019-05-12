package testCases;

import atu.ddf.exceptions.DataDrivenFrameworkException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.AriaDashboardPageObject;
import pageObjects.StatementsAndInvoicesPageObject;
import utility.Utils;

/**
 * Created by joseph.gannon on 7/14/2015.
 */
public class CreateAndVoidInvoice extends BaseTestCase
{
    @Test(dataProvider="objectTestData", description="Create And Void Invoice")
    public void createAndVoidInvoice(String strdFileName, String strdWorksheet, String strFieldNames, String strFieldValues) throws Exception
    {
        //TODO: Creates New Account with Usage
        String acctNum = CreateNewAccountWithUsage.createAccountWithUsage(strdFileName, strdWorksheet, strFieldNames, strFieldValues);

        //TODO: Create Immediate Invoice
        driver.findElement(AriaDashboardPageObject.getStatementsAndInvoices()).click();
        Thread.sleep(3000);
        driver.findElement(StatementsAndInvoicesPageObject.getInvoicesTab()).click();
        driver.findElement(StatementsAndInvoicesPageObject.getGenerateInvoiceLink()).click();
        driver.findElement(StatementsAndInvoicesPageObject.getGenerateInvoiceBtn()).click();


        //TODO: Void Invoice
        driver.findElements(StatementsAndInvoicesPageObject.getInvoicesTab()).get(1).click();
        driver.findElement(StatementsAndInvoicesPageObject.getInvoiceRow()).click();

        //verify Invoice is created
        assertTrue(driver.findElement(StatementsAndInvoicesPageObject.getInvoiceRow()).isDisplayed());
        driver.findElement(StatementsAndInvoicesPageObject.getVoidUsageAndDiscardLink()).click();

        //verify Invoice is voided
        assertEquals(driver.findElement(StatementsAndInvoicesPageObject.getVoidInvoiceMsg()).getText(),"Invoice number "+acctNum+" has been voided.");

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
//        return objUtils.data("Aria", "CreateAndVoidInvoice");
//    }
}
