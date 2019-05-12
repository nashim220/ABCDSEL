package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utility.Log;

/**
 * Created by joseph.gannon on 4/24/2015.
 * Edited from AriaAccountPageObject By Abhishek on 4/28/2016
 */
public class AriaBillingContactValidationPageObject
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
    public static By getContactsLink()
    {
        return By.xpath("//*[@id=\"bottomPaneTabBar\"]/ul/li[13]/a");
      
    }
	
	public static By getContactsEditLink(WebElement Webtable)
    {
        String WebTableName = Webtable.getAttribute("id");
	    
        // Now get all the TR elements from the table
		List<WebElement> TotalRowCount = Webtable.findElements(By.tagName("tr"));
		
		// And iterate over them, getting the cells
		int RowIndex=0;
		String EditLinkXpath1 = "//*[@id=\""+WebTableName+"\"]/tbody/tr[";
		String EditLinkXpath2 = "]/td[6]/a/img";
		String EditLinkFinalXpath = "";
		Log.info("row size for plans table "+TotalRowCount.size());
		for (WebElement rowElement: TotalRowCount) 
		{
				List<WebElement> TotalColumnCount=rowElement.findElements(By.xpath("td"));					
				int ColumnIndex=0;
				int cntImgs = 0;
				for(WebElement colElement:TotalColumnCount)
				{
					List<WebElement> all_images = colElement.findElements(By.tagName("img"));
					cntImgs = cntImgs+1;
					if((all_images.size() == 1) && cntImgs == 4){
						EditLinkFinalXpath = EditLinkXpath1+RowIndex+EditLinkXpath2;
					}
				ColumnIndex=ColumnIndex+1;
				}
		RowIndex=RowIndex+1;  
		}
		return By.xpath(EditLinkFinalXpath);
		
    }
	
    public static By getBillingCompName()
    {
        return By.xpath("//*[@id=\"accountsSectionBottomContainer\"]/div/div/form/div[1]/table/tbody/tr[10]/td[1]/input");
    }
    public static By getBillingAddress1()
    {
        return By.xpath("//*[@id=\"accountsSectionBottomContainer\"]/div/div/form/div[1]/table/tbody/tr[4]/td[1]/input");
    }
	public static By getBillingEmail()
    {
        return By.xpath("//*[@id=\"accountsSectionBottomContainer\"]/div/div/form/div[1]/table/tbody/tr[12]/td[1]/input");
    }
	public static By getBillingAddress2()
    {
        return By.xpath("//*[@id=\"accountsSectionBottomContainer\"]/div/div/form/div[1]/table/tbody/tr[4]/td[3]/input");
    }
    public static By getBillingFName()
    {
        return By.xpath("//*[@id=\"accountsSectionBottomContainer\"]/div/div/form/div[1]/table/tbody/tr[2]/td[1]/input");
    }
    public static By getBillingLName()
    {
        return By.xpath("//*[@id=\"accountsSectionBottomContainer\"]/div/div/form/div[1]/table/tbody/tr[2]/td[5]/input");
    }
	public static By getBillingPhone()
    {
        return By.xpath("//*[@id=\"accountsSectionBottomContainer\"]/div/div/form/div[1]/table/tbody/tr[14]/td[1]/input");
    }
	public static By getBillingCity()
    {
        return By.xpath("//*[@id=\"accountsSectionBottomContainer\"]/div/div/form/div[1]/table/tbody/tr[6]/td[1]/input");
    }
    public static By getBillingSaveBtn()
    {
        return By.xpath("//*[@id=\"submit-button\" and @value=\"Save\"]");
    }
    public static By getBillingSaveConfirmBtn()
    {
        return By.cssSelector("body > div:nth-child(13) > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(2) > span");
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
