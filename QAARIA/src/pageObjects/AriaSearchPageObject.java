package pageObjects;

import org.openqa.selenium.By;

/**
 * Created by joseph.gannon on 4/23/2015.
 */
public class AriaSearchPageObject
{
    public static By searchByField()
    {
        return By.name("searchField1");
    }
    public static By searchByAcctNum()
    {
        return By.xpath("//*[@id=\'adhoc_search_form\']/div[2]/table/tbody/tr[2]/td[1]/select/option[2]");
    }

    public static By searchValue()
    {
        return By.id("searchValueText1");
    }

    public static By clickSearchButton()
    {
        return By.xpath("//*[@id=\"adhoc_search_form\"]/div[7]/input");
    }

    public static By searchResult(String acctNum)
    {
        return By.linkText(acctNum);
    }
}
