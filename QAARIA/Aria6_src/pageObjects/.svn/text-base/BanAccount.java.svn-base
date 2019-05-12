/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	BanAccount 
 Purpose     		: 	Purpose of this file is :
						1. To capture all the page objects for the Banning /Termination of an Account.
 
 Date       		:	06/24/2015
  Modified Date		:	09/06/2015
 Version Information:	Version2.0
 
 PreCondition 		:	Role based Login required.
 Test Steps 		:	1) Capture the page objects for the objects that will be used for 
 						Banning /Termination of an Account.
 						
 Version Changes 2.0:	1. Changes to the parameters, class name and method names as per the standards.
 						2. Change the references to the page objects.  						
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package pageObjects;

import org.openqa.selenium.By;

public class BanAccount {
	
	public static By fn_clickAccountOverview()	// this is to identify the Account Overview tab.
	{
		return By.partialLinkText("Account Overview"); 
		//By.xpath("//*[@id=\'bottomPaneTabBar\']/ul/li[1]/a");
	}
	
	public static By fn_verifyStatus()	// this is to identify the Status cell value.
	{
		return By.xpath("//*[@id=\'content-wrapper\']/table[1]/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[3]/td[4]");
	}
	
	public static By fn_clickStatusAccountsOverview()	// this is to identify the Status Hyperlinking on the Accounts Overview tab page.
	{
		return By.linkText("Status");
	}
	
	public static By fn_clickBanAccount()	// this is to identify the Ban Account tab.
	{
		return By.partialLinkText("Ban Account");
		//By.xpath("//*[@id=\'bottomPaneTabBar\']/ul/li[9]/a");
	}
	
	public static By fn_clickAccountStatusMaintenance()	// this is to identify the Account Status Maintenance.
	{
		return By.linkText("Account Status Maintenance");
		//By.xpath("//*[@id=\'page-instructions\']/p[2]/a");
	}
	
	public static By fn_clickChangeAccountStatusLink()	// this is to identify the Change Account Status link.
	{
		return By.linkText("Change Account Status");
	}
	
	public static By fn_getStatusDataTable()	// this is to identify the Status Table.
	{
		return By.xpath("//*[@id=\'accountsSectionBottomContainer\']/div/div/form/table");
	}
	
	public static By fn_clickStatusChange()	// this is to identify the Status Change drop-down.
	{
		return By.id("inQueueDays");
	}
	
	public static By fn_clickResetBillingDates(String strValue)	// this is to identify the Reset Billing Dates, flexible to identify the type.
	{
		String strPath = "//*[@id=\'accountsSectionBottomContainer\']/div/div/form/fieldset/dl/dd[2]/input["+strValue+"]";
		return By.xpath(strPath);
	}
	
	// This is controlled by the values selected in Status Change. Disabled for value other than None.
	public static By fn_clickSuppressDunningProcess(String value)	// this is to identify the Suppress Dunning Process, flexible to identify option.
	{
		String strPath = null;
		if (value.contentEquals("N"))
		{
			strPath = "//*[@id=\'suppress-dunning-process\']/dd/input"+"[1]";
		}
		else
		{
			strPath = "//*[@id=\'suppress-dunning-process\']/dd/input"+"[2]";
		}
		
		return By.xpath(strPath);
	}
	
	public static By fn_getCSRComments()	// this is the identification of CSR Comments section.
	{
		return By.name("inComments");
		//By.xpath("//*[@id=\'accountsSectionBottomContainer\']/div/div/form/fieldset/dl/dd[3]/textarea");
	}
	
	public static By fn_clickChangeAccountStatusButton()	// this is to identify the Change Account Status button.
	{
		return By.xpath("//*[contains(@value, 'Change Account Status')]");
	}
	
	public static By fn_getConfirmationPageText()	// this is to identify the verification text for TERMINATED status.
	{
		return By.xpath("//*[@id=\'accountsSectionBottomContainer\']/div/div/div[4]/dl/dd[1]/text()[1]");
	}
	
	public static By fn_clickCancel()	// this is to identify the cancel link on final confirmation of Status change. 
	{
		return By.xpath("//*[contains(@title, 'Cancel')]");
	}
	
	public static By fn_clickSaveStatusChangeButton()	// this is to identify the Status Change Button.
	{
		return By.xpath("//*[contains(@value, 'Save Status Change')]");
	}

	public static By fn_getAccountStatusHistoryDataTable()	// this is to identify the Account Status History Data table.
	{
		return By.xpath("//*[@id=\'accountsSectionBottomContainer\']/div/div/table");
	}
}
