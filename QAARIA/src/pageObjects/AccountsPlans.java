/*
 Author     		:	Nashim Khan
 Class Name			: 	AccountsPlans 
 Purpose     		: 	Purpose of this file is :
						1. To capture all the page objects for the Plans Functionality of an Account.
 
 Date       		:	18/04/2016
 Modified Date		:	06/07/2016
 Modified By		:	Aashish Bhutkar
 Version Information:	2.0
 
 PreCondition 		:	Role based Login required.
 Test Steps 		:	1) Capture the page objects for the objects that will be used/reused from Aria EOM Left Nav.

 Version Changes 2.0:	1. Added more Page Object for improved recognition & remodelled the functions. 						
 						
 Copyright notice	:	Copyright(C) 2016 Sungard Availability Services - 
 						All Rights Reserved 
*/


package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountsPlans {

	WebElement webelement;
	   
	// to Click on  particular Account
	public WebElement fn_ClickonAcountPlans(WebDriver driver, WebDriverWait webWait) throws InterruptedException {

		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='acctNoExpander']/a/div")));
		return webelement;
	}
	
	//to Click on  the Billing Groups link in the Top nav bar
	public WebElement fn_clickBillingGroupsTab(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
	
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='bottomPaneTabBar']//a[text()='Billing Groups']")));
		return webelement;
	}
	
	// to Click on Plans Table
	public WebElement fn_PlansTableLoad(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
	
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("DataTables_Table_0_wrapper")));
		return webelement;
	}
	
	//to Click on  Billing Group No link
	public WebElement fn_clickOnBillingGroupNoLink(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
	
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@id='deleteForm']//a[@class='doAccountsPanel']")));
		return webelement;
	}			
    
	// to Click on Billing Group Id		
	public WebElement fn_BillingGroupId(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
	
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//from[@id='reassignForm']/table[1]/tbody/tr[2]/td")));
		return webelement;
	}
	
	//to Click on Client Defined Identifier
	public WebElement fn_BillingGroupClientID(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
	
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//from[@id='reassignForm']/table[1]/tbody/tr[8]/td/input")));
		return webelement;
	}
	
	// to Identify First Name
	public WebElement fn_BillingGroupFirstName(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
	
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("inStatementFirstName")));
		return webelement;
	}
	
	// to Identify Address 
	public WebElement fn_BillingGroupAddress(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
	
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='Statement-contact-details-div']/table/tbody/tr[4]/td[1]/input")));
		return webelement;
	}
	
	//to Identify City 
	public WebElement fn_BillingGroupCity(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
	
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//from[@id='reassignForm']/table/tbody/tr[6]/td[1]/input")));
		return webelement;
	}
	
	//to Identify ZipCode
	public WebElement fn_BillingGroupZipCode(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
	
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//from[@id='reassignForm']/table/tbody/tr[6]/td[3]/input")));
		return webelement;
	}
	
	//to Identify Company
	public WebElement fn_BillingGroupCompany(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
	
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//from[@id='reassignForm']/table/tbody/tr[10]/td/input")));
		return webelement;
	}
	
	//to Identify Email Address
	public WebElement fn_BillingGroupEmail(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
	
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//from[@id='reassignForm']/table/tbody/tr[12]/td/input")));
		return webelement;
	}
	
	//to Identify Home Phone 
	public WebElement fn_BillingGroupHomePhone(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
	
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//from[@id='reassignForm']/table/tbody/tr[14]/td[1]/input")));
		return webelement;
	}
	
	//to Identify Birth Date
	public WebElement fn_BillingGroupBirthDate(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
	
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//from[@id='reassignForm']/table/tbody/tr[20]/td/input")));
		return webelement;
	}
	
	//click Choose payment option 
	public WebElement fn_getAPaymentOption(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
	
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("paymentOption")));
		return webelement;
	}
	
	//To Click On MPI 
	public WebElement fn_clickPlansMPI(WebDriver driver, WebDriverWait webWait, String strMPIID) throws InterruptedException {
	
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText(strMPIID)));
		return webelement;
	}
		
	//To Get Billing Group Id 
	public WebElement fn_getBillingGroupId(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
	
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//b[text()='Billing Group ID:']/following::dd[1]")));
		return webelement;
	}
	 
	//To Click On Billing Group Id 
	public WebElement fn_clickBillingGroupID(WebDriver driver, WebDriverWait webWait, String strBillingGroupId) throws InterruptedException {
	
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText(strBillingGroupId)));
		return webelement;
	}
	
	// click Make Payment Button on Plans Details page.
	public WebElement fn_clickMakePayment(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Make Payment' and @type='button']")));
		return webelement;
	}
	
	// to get the Bill Through Date <span>.
	public WebElement fn_getBillThroughDate(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
			
		webelement = webWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()='Bill Through Date']/parent::span")));
		return webelement;
	}
	//Click on  Remove plan Link
	public WebElement fn_clickRemovePlan(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
				
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Remove Plan']")));
		return webelement;
	}
			
	//Click on  Remove button 
	public WebElement fn_clickRemoveBtn(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='submit-button' and @value='Remove']")));
		return webelement;
	}
			
	//to identify the Plan instance id 
	public WebElement fn_clickPlaninstanceId(WebDriver driver, WebDriverWait webWait, String SpiId ) throws InterruptedException	{
					
		String strAgainstOptionsXpath=null;
		strAgainstOptionsXpath = "//a[@class='doAccountsPanel' and text()='"+SpiId+"']";			
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath(strAgainstOptionsXpath)));
		return webelement;
	}
	//to identify the Plan Status
	public WebElement fn_clickPlanStatus(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
								
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='doAccountsPanel' and contains(text(),'Plan Status')]")));
		return webelement;
	}
			
	//to identify the  Change Plan Status
	public WebElement fn_clickChangePlanStatus(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
										
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Change Plan Status")));
		return webelement;
	}
			
	//to identify the  Change Plan Status
	public WebElement fn_clickTermination(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='inNewStatus' and @value='-3']")));
		return webelement;
	}
			
			
	//to identify the  save Button 
	public WebElement fn_clickSave(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='submit-button' and @value='Save']")));
		return webelement;
	}
			
	//to identify the Message Dlg Yes  
	public WebElement fn_clickMessaageDlgYes(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
		
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Yes']")));
		return webelement;
	}
	//div[@id='accountsSectionBottomContainer']/div/div/div[3]/div/p
	//to identify the  status Message   
	public WebElement fn_clickStatusMessaage(WebDriver driver, WebDriverWait webWait) throws InterruptedException {
													
		webelement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Changed successfully')]")));
		return webelement;
	}
	
}


