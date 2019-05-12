package pageObjects;

import org.openqa.selenium.By;

/**
 * Created by joseph.gannon on 6/10/2015.
 */
public class ClientSettingsPageObject
{
    public static By getMiscTable()
    {
        return By.id("DataTables_Table_0");
    }

    public static By getFuncAcctGroupsTable()
    {
        return By.id("DataTables_Table_0");
    }

    public static By getFuncAcctGroupsLink()
    {
        return By.partialLinkText("Functional Account Groups");
    }
}
