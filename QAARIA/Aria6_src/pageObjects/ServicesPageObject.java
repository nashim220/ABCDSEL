package pageObjects;

import org.openqa.selenium.By;

/**
 * Created by joseph.gannon on 5/13/2015.
 */
public class ServicesPageObject
{
    public static By getServicesTable()
    {
        return By.id("DataTables_Table_1");
    }
    public static By getTaxableStatus()
    {
        return By.xpath("//*[@id=\"Services_taxable\"]");
    }
    public static By servicesCloseBtn()
    {
        return By.xpath("//*[@id=\"ServicesEdit\"]/div[2]/span[2]/input");
    }
}
