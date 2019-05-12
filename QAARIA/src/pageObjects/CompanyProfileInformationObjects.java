//Author:- Madhavi JN, Date :- 26-06-2015
//Purpose:- To retrieve all the page Objects Under CompanyProfileInformation Section
package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompanyProfileInformationObjects 
{
private WebElement element = null;
	//public WebElement element = null;

  //To identify  Configuration Link
  public WebElement fn_ClickConfiguration(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		//element = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Configuration")));
	  element = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Configuration")));
		return element;
	}
  
  //To identify  ClientSetting Link
  public WebElement fn_ClickClientSetting(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Client Settings")));
		return element;
	}	
  
  //To identify  Company Profile Link
  public WebElement fn_ClickCompanyProfile(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Company Profile")));
		return element;
	}	


   //To identify WebDomain Field	
   public WebElement fn_EnterWebDomain(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[domain]")));
		return element;
	}
	
   //To identify Phone Number Field
	public WebElement fn_EnterPhoneNumber(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[phone]")));
		return element;
	}
	
   //To identify Street Address Field
	public WebElement fn_EnterStreetAddress(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[address1]")));
		return element;
	}
	
   //To identify Street Address2 Field
	public WebElement fn_EnterStreetAddress2(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[address2]")));
		return element;
	}
	
   //To identify City Field
	public WebElement fn_EnterCity(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[city]")));
		return element;
	}
	
   //To identify Country Field
	public WebElement fn_EnterCountry(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[country]")));
		return element;
	}
	
   //To identify State/Province Field
	public WebElement fn_EnterState(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[stateProv]")));
		return element;
	}
	
   //To identify Postal Code Field
	public WebElement fn_EnterPostalCode(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[postalCode]")));
		return element;
	}	
	
   //To identify Locality Field
	public WebElement fn_EnterLocality(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[locality]")));
		return element;
	}	
	
   //To identify Button UseCompanyAddress
	public WebElement fn_ClickCompnayAddressButton(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.id("admin_contact")));
		return element;
	}	
/************************************************************************/
//Administrative Contact
/************************************************************************/	
	//To identify FullName	
	public WebElement fn_AdminEnterFullName(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[contact]")));
		return element;
	}
	   
	//To identify Email	
	public WebElement fn_AdminEnterEmail(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[contactEmail]")));
		return element;
	}
	   
	//To identify Phone	
	public WebElement fn_AdminEnterPhone(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[contactPhone]")));
		return element;
	}
	
	//To identify Address 1	
	public WebElement fn_AdminEnterAddress1(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[contactAddress1]")));
		return element;
	}
	
	//To identify Address 2	
	public WebElement fn_AdminEnterAddress2(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[contactAddress2]")));
		return element;
	}
	
	//To identify City	
	public WebElement fn_AdminEnterCity(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[contactCity]")));
		return element;
	}
	
	//To identify Country	
	public WebElement fn_AdminEnterCountry(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[contactCountry]")));
		return element;
	}
	
	//To identify State
	public WebElement fn_AdminEnterState(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[contactState]")));
		return element;
	}
	
	
	//To identify Postal Code
	public WebElement fn_AdminEnterPostalCode(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[contactZip]")));
		return element;
	}
	
	//To identify Locality
	public WebElement fn_AdminEnterLocality(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[contactLocality]")));
		return element;
	}
	
	/*******************************/
	//Billing Contact
	/*******************************/
	//To identify FullName	
		public WebElement fn_BillingEnterFullName(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[billingContact]")));
			return element;
		}
		   
		//To identify Email	
		public WebElement fn_BillingEnterEmail(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[billingEmail]")));
			return element;
		}
		   
		//To identify Phone	
		public WebElement fn_BillingEnterPhone(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[billingPhone]")));
			return element;
		}
		
		//To identify Address 1	
		public WebElement fn_BillingEnterAddress1(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[billingAddress1]")));
			return element;
		}
		
		//To identify Address 2	
		public WebElement fn_BillingEnterAddress2(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[billingAddress2]")));
			return element;
		}
		
		//To identify City	
		public WebElement fn_BillingEnterCity(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[billingCity]")));
			return element;
		}
		
		//To identify Country	
		public WebElement fn_BillingEnterCountry(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[billingCountry]")));
			return element;
		}
		
		//To identify State
		public WebElement fn_BillingEnterState(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[billingState]")));
			return element;
		}
		
		
		//To identify Postal Code
		public WebElement fn_BillingEnterPostalCode(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[billingZip]")));
			return element;
		}
		
		//To identify Locality
		public WebElement fn_BillingEnterLocality(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[billingLocality]")));
			return element;
		}
		
		/*******************************/
		//Batch Job Alert Contact
		/*******************************/
		//To identify FullName	
		public WebElement fn_BatchJobAlertEnterFullName(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[batchJobAlertContact]")));
			return element;
		}
			   
		//To identify Email	
		public WebElement fn_BatchJobAlertEnterEmail(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			element = webWait.until(ExpectedConditions.elementToBeClickable(By.name("companyProfile[batchJobAlertEmail]")));
			return element;
		}
			
		/*******************************/
		//Save
		/*******************************/
		//To identify Button UseCompanyAddress
		public WebElement fn_ClickSave(WebDriver driver,WebDriverWait webWait) throws InterruptedException
		{
			element = webWait.until(ExpectedConditions.elementToBeClickable(By.id("submit_button")));
			return element;
		}	
}
