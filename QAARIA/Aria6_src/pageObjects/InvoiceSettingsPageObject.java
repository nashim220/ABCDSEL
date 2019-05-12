package pageObjects;

import org.openqa.selenium.By;

/**
 * Created by joseph.gannon on 6/4/2015.
 */
public class InvoiceSettingsPageObject
{
    public static By getSearchBar()
    {
        return By.xpath("//*[@id=\"DataTables_Table_40_filter\"]/label/input");
    }
    public static By getSuppressionCell()
    {
        return By.xpath("//*[@id=\"systemsettingsIndex_row_SUPPRESS_ZERO_DEBIT_INVS\"]/td[3]");
    }
}
