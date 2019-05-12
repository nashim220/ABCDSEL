package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsagePageObjects {
	public UsagePageObjects(){
		
	}
	
	private static WebElement webElement = null;
	
	  //To identify  Accounts Link
	  public WebElement fn_ClickAccountsLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Accounts")));
			return webElement;
		}
	  
	  //To identify Search Link
	  public WebElement fn_ClickSearchLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Search")));
			return webElement;
		}	  
	  
	  //To identify AdhocSearch Link
	  public WebElement fn_ClickAdhocSearchLink(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Ad-Hoc Search")));
			return webElement;
		}	  
	  
	  //To identify SearchValue Field
	  public WebElement fn_EnterSearchValue(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("searchValueText1")));
			return webElement;
		}	
	  
	  //To identify SearchButton Field 
	   public WebElement fn_ClickSearchBtn(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Search']")));
		  return webElement;
		}
	   
	  //To identify AcctNumLink Field 	   
		public WebElement fn_AcctNumLink(WebDriver driver,WebDriverWait webWait,String acct_num) throws InterruptedException
		{
			System.out.print("inside the block number"+acct_num);
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText(acct_num)));
			return webElement;
		}	   
	    
	  //To identify Usage Link
	  public WebElement fn_ClickUsage(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Usage")));
			return webElement;
		}
	  
	  //To identify FromDate Field
	  public WebElement fn_EnterFromDate(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.name("inStartDateString")));
			return webElement;	
		}	
	  
	  //To identify ToDate Field
	  public WebElement fn_EnterToDate(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("inEndDateString")));
			return webElement;
		}	  
	  
	  //To identify RetriveUsage Button
	  public WebElement fn_ClickRetriveUsageBtn(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.id("submit-button")));
			return webElement;
		}	  
	    
	  //To identify Statements and Invoices LInk
	  public WebElement fn_ClickStmtsInvoiceslnk(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Statements & Invoices")));
			return webElement;
		}
	  
	  //To identify Invoices LInk
	  public WebElement fn_ClickInvoiceslnk(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Invoices')]")));
			return webElement;
		}	
	  
	  //To identify GenerateInvoices LInk
	  public WebElement fn_ClickGenInvoiceslnk(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/ul/li/a/span")));
		   return webElement;
		}
	  
	  //To identify GenerateInvoice Button 
	   public WebElement fn_GenerateInvoiceBtn(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
		  webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Generate Invoice']")));
		  return webElement;

		}	 
	   
	 //To identify Taxpayer Information Link
       public WebElement fn_ClickTaxPayerInfmtnlnk(WebDriver driver,WebDriverWait webWait) throws InterruptedException
        {
    	   webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Taxpayer Information")));
           return webElement;
        }
       
       //To identify Exempt from State/Province taxes Checkbox
       public WebElement fn_ClickStateTaxChkbx(WebDriver driver,WebDriverWait webWait) throws InterruptedException
        {
    	   webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='inStateProvTaxExempt']")));
           return webElement;
        }
       
       //To identify Exempt from Federal/National taxes Checkbox 
       public WebElement fn_ClickNationTaxChkbx(WebDriver driver,WebDriverWait webWait) throws InterruptedException
        {
    	   webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='inFederalTaxExempt']")));
           return webElement;
        }   
       
     //To identify Edit Fields button
       public WebElement fn_EditFields(WebDriver driver,WebDriverWait webWait) throws InterruptedException
        {
    	   webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Edit Fields']")));
           return webElement;
        }  
       
     //To identify Save Changes button
       public WebElement fn_SaveChanges(WebDriver driver,WebDriverWait webWait) throws InterruptedException
        {
    	   webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Save Changes']")));
           return webElement;
        } 
       
       //To Identify Immediate Invoice radio button
       public WebElement fn_ImmediateInvoice(WebDriver driver,WebDriverWait webWait) throws InterruptedException
       {
   	   webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@id='inInvoiceOption'])[1]")));
          return webElement;
       } 
       
     //To Identify Pending Invoice radio button
       public WebElement fn_PendingInvoice(WebDriver driver,WebDriverWait webWait) throws InterruptedException
       {
   	   webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@id='inInvoiceOption'])[2]")));
          return webElement;
       } 
       
     //To Identify Advanced Invoice radio button
       public WebElement fn_AdvancedInvoice(WebDriver driver,WebDriverWait webWait) throws InterruptedException
       {
   	   webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@id='inInvoiceOption'])[3]")));
          return webElement;
       } 
       
       //Click on Generate Invoice button
     //To Identify Immediate Invoice radio button
       public WebElement fn_GenerateInvoiceButton(WebDriver driver,WebDriverWait webWait) throws InterruptedException
       {
    	   webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='submit-button']")));
    	   return webElement;
       } 
       
       //to identify the message delivered after Generate Invoice button is clicked.
       public WebElement fn_getInvoiceGnerationMessage(WebDriver driver,WebDriverWait webWait) throws InterruptedException
       {
    	   webElement = webWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='accountsSectionBottomContainer']/div/div/div[3]/p")));
    	   return webElement;
       }     
}
