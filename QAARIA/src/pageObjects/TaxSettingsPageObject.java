package pageObjects;

import org.openqa.selenium.By;

/**
 * Created by joseph.gannon on 6/10/2015.
 */
public class TaxSettingsPageObject
{
    public static By getTaxTable()
    {
        return By.id("DataTables_Table_0");
    }
    public static By getTaxTableRows()
    {
        return By.xpath("//*[@id=\"DataTables_Table_1\"]/tbody/tr");
    }
}
