package pageObjects;

import javafx.util.Pair;
import org.openqa.selenium.By;

/**
 * Created by joseph.gannon on 5/28/2015.
 */
public class AriaReportsPage
{
    public static Pair<String,String> getSGAS()
    {
        return new Pair<String,String>("a","SGAS");
    }

    public static Pair<String,String> getCSRUsersReport()
    {
        return new Pair<String,String>("a","CSR Users - SGAS");
    }
    public static By getSGASLink()
    {
        //return By.linkText("javascript:clickOnNode(\"1\")");
        return By.linkText("SGAS");
    }
    public static By getSwitchToPreview()
    {
        return By.xpath("/html/body/table[2]/tbody/tr/td[2]/a");
    }

    public static By getPreviewBtn()
    {
        return By.xpath("/html/body/form[1]/table[2]/tbody/tr/td/input");
    }

    public static By getCreationDate()
    {
        return By.xpath("/html/body/table[2]/tbody/tr[15]/td[6]");
    }

    public static By getReportOutput()
    {
        return By.name("postedReportDisplay");
    }
}
