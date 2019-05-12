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

  //To identify  Configuration Link
  public WebElement fn_ClickConfiguration(WebDriver driver,WebDriverWait webWait) throws InterruptedException
	{
		element = webWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Configuration")));
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
}
