package pageObjects;

import org.openqa.selenium.By;

/**
 * Created by joseph.gannon on 4/24/2015.
 */
public class AriaAccountPageObject
{
    public static By getUserId()
    {
         return By.xpath("//*[@id=\"content-wrapper\"]/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]/td[2]");
    }

    public static By getClientAccountId()
    {
        return By.xpath("//*[@id=\"content-wrapper\"]/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]");
    }

    public static By getAccountNum()
    {
        return By.xpath("//*[@id=\"content-wrapper\"]/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[3]/td[2]");
    }

    public static By getMasterPlan()
    {
        return By.xpath("//*[@id=\"content-wrapper\"]/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[5]/td[2]");
    }
    public static By getBillingContactLink()
    {
        //return By.xpath("//*[@id=\"content-wrapper\"]/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[10]/td[3]/a");
        return By.linkText("Billing Contact");
    }
    public static By getBillingCompName()
    {
        return By.xpath("//*[@id=\"accountsSectionBottomContainer\"]/div/div/form/fieldset[2]/dl/dd[1]/input");
    }
    public static By getBillingAddress()
    {
        return By.xpath("//*[@id=\"accountsSectionBottomContainer\"]/div/div/form/fieldset[2]/dl/dd[2]/input");
    }
    public static By getBillingFName()
    {
        return By.xpath("//*[@id=\"accountsSectionBottomContainer\"]/div/div/form/fieldset[1]/dl/dd[1]/input");
    }
    public static By getBillingPhone()
    {
        return By.xpath("//*[@id=\"accountsSectionBottomContainer\"]/div/div/form/fieldset[3]/dl/dd[1]/input");
    }
    public static By getBillingSaveBtn()
    {
        return By.xpath("//*[@id=\"accountsSectionBottomContainer\"]/div/div/form/div/p/input");
    }
    public static By getManageChildTab()
    {
        ////*[@id="bottomPaneTabBar"]/ul/li[10]/a
        //return By.xpath("//*[@id=\"bottomPaneTabBar\"]/ul/li[10]/a");
        return By.linkText("Manage Child Accounts");
    }
    public static By getAssignChildAcctLink()
    {
        return By.xpath("//*[@id=\"page-tools\"]/li/a/span");
        //return By.linkText("Assign Child Account");
    }

    public static By getChildAcctAssignField()
    {
        return By.xpath("//*[@id=\"accountsSectionBottomContainer\"]/div/div/form/table/tbody/tr[1]/td[2]/input");
    }

    public static By getAssignChildAcctBtn()
    {
        return By.xpath("//*[@id=\"accountsSectionBottomContainer\"]/div/div/form/div/p/input");
    }

    public static By getStdSelfPayBtn()
    {
        return By.xpath("//*[@id=\"accountsSectionBottomContainer\"]/div/div/form/table/tbody/tr[2]/td[2]/input[1]");
    }
    
    public static By getParentPayBtn()
    {
        return By.xpath("//*[@id=\"accountsSectionBottomContainer\"]/div/div/form/table/tbody/tr[2]/td[2]/input[2]");
    }

    public static By getUnassignLink()
    {
        return By.linkText("Unassign");
    }
    public static By getResponsibilityLevel()
    {
        return By.xpath("//*[@id=\"DataTables_Table_0\"]/tbody/tr/td[7]");
    }

}
