package appModules;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testCases.BaseTestCase;
import utility.Log;
import utility.Utils;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import pageObjects.*;
import pageObjects.*;

public class AriaCompanyProfileInformation extends BaseTestCase
{
	
	public void fn_AccessCompanyProfileObjects(String CompanyInformation) throws InterruptedException
	{
		
	WebDriverWait webWait;
	webWait = new WebDriverWait(driver,2000);
	System.out.print("arrayvalues are "+CompanyInformation);
	String[] fields= CompanyInformation.split("#");
	System.out.println(fields[0]);
	System.out.println(fields[1]);
	System.out.println(fields[2]);
	System.out.println(fields[3]);
	System.out.println(fields[4]);
	System.out.println(fields[5]);
	System.out.println(fields[6]);
	System.out.println(fields[7]);
	System.out.println(fields[8]);
	System.out.println(fields[9]);
	System.out.println(fields[10]);
	System.out.println(fields[11]);
	System.out.println(fields[12]);
	System.out.println(fields[13]);
	System.out.println(fields[14]);
	System.out.println(fields[15]);
	System.out.println(fields[16]);
	System.out.println(fields[17]);
	System.out.println(fields[18]);
	System.out.println(fields[19]);
	System.out.println(fields[20]);
	System.out.println(fields[21]);
	System.out.println(fields[22]);
	System.out.println(fields[23]);
	System.out.println(fields[24]);
	System.out.println(fields[25]);
	
	
	CompanyProfileInformationObjects ObjCompanyProfile = new CompanyProfileInformationObjects();
	//Click on Configurations link
	ObjCompanyProfile.fn_ClickConfiguration(driver, webWait).click();
	//Click on Client settings Link
	ObjCompanyProfile.fn_ClickClientSetting(driver, webWait).click();
	//Click on Company Profile Link
	ObjCompanyProfile.fn_ClickCompanyProfile(driver, webWait).click();
	//To Enter Value for WebDomain Field
	ObjCompanyProfile.fn_EnterWebDomain(driver, webWait).clear();
	ObjCompanyProfile.fn_EnterWebDomain(driver, webWait).sendKeys(fields[0]);
	//To Enter Value for PhoneNumber Field
	ObjCompanyProfile.fn_EnterPhoneNumber(driver, webWait).clear();
	ObjCompanyProfile.fn_EnterPhoneNumber(driver, webWait).sendKeys(fields[1]);
	//To Enter Value for Street Address Field
	ObjCompanyProfile.fn_EnterStreetAddress(driver, webWait).clear();
	ObjCompanyProfile.fn_EnterStreetAddress(driver, webWait).sendKeys(fields[2]);
	//To Enter Value for City Field
	ObjCompanyProfile.fn_EnterCity(driver, webWait).clear();
	ObjCompanyProfile.fn_EnterCity(driver, webWait).sendKeys(fields[3]);
	Thread.sleep(3000);
	//To Enter Value for Country Field
	List<WebElement> strdropdownCountry= driver.findElement(By.name("companyProfile[country]")).findElements(By.tagName("option"));
	for (WebElement Country : strdropdownCountry) {
        if (Country.getText().equals(fields[4])) {
            Country.click();
            }
        }
    
	//strdropdownCountry.get(239).click();
	
	//Select dropdownCountry = new Select(ObjCompanyProfile.fn_EnterCountry(driver, webWait));
	//dropdownCountry.selectByVisibleText(fields[4]);
	//To Enter Value for State/Province Field
	Select dropdownState = new Select(ObjCompanyProfile.fn_EnterState(driver, webWait));
	//dropdownState.deselectAll();
	Thread.sleep(3000);
	//dropdownState.selectByVisibleText(fields[5]);
	dropdownState.selectByVisibleText(fields[5]);
	//To Enter Value for Postal Code Field
	ObjCompanyProfile.fn_EnterPostalCode(driver, webWait).clear();
	ObjCompanyProfile.fn_EnterPostalCode(driver, webWait).sendKeys(fields[6]);
	//To Click on Use Company Address 
	//ObjCompanyProfile.fn_ClickCompnayAddressButton(driver, webWait).click();
	
	/*****************************************************/
	//Administrative Contact
	/*****************************************************/
	Thread.sleep(3000);
	//Thread.sleep(20000);
	
	//To Enter Administrative Contact Name
	ObjCompanyProfile.fn_AdminEnterFullName(driver, webWait).clear();
	ObjCompanyProfile.fn_AdminEnterFullName(driver, webWait).sendKeys(fields[7]);
	Thread.sleep(3000);
	
	//To Enter Administrative Contact email
	ObjCompanyProfile.fn_AdminEnterEmail(driver, webWait).clear();
	ObjCompanyProfile.fn_AdminEnterEmail(driver, webWait).sendKeys(fields[8]);
	
	//To Enter Administrative Phone Number
	ObjCompanyProfile.fn_AdminEnterPhone(driver, webWait).clear();
	ObjCompanyProfile.fn_AdminEnterPhone(driver, webWait).sendKeys(fields[9]);
	
	//To Enter Administrative Street Address
	ObjCompanyProfile.fn_AdminEnterAddress1(driver, webWait).clear();
	ObjCompanyProfile.fn_AdminEnterAddress1(driver, webWait).sendKeys(fields[10]);
	
	//To Enter Administrative City
	ObjCompanyProfile.fn_AdminEnterCity(driver, webWait).clear();
	ObjCompanyProfile.fn_AdminEnterCity(driver, webWait).sendKeys(fields[11]);
	
	//To Enter Administrative Country
	List<WebElement> admindropdownCountry= driver.findElement(By.name("companyProfile[contactCountry]")).findElements(By.tagName("option"));
	for (WebElement Country : admindropdownCountry) {
        if (Country.getText().equals(fields[12])) {
            Country.click();
            }
        }
	
	//To Enter Administrative State
	Select adminDropDownState = new Select(ObjCompanyProfile.fn_AdminEnterState(driver, webWait));
	Thread.sleep(3000);
	adminDropDownState.selectByVisibleText(fields[13]);
	Thread.sleep(3000);
	
	//To Enter Postal Code
	ObjCompanyProfile.fn_AdminEnterPostalCode(driver, webWait).clear();
	ObjCompanyProfile.fn_AdminEnterPostalCode(driver, webWait).sendKeys(fields[14]);
	Thread.sleep(3000);
	
	/*****************************************************/
	//Billing Contact
	/*****************************************************/
	//To Enter Administrative Contact Name
	ObjCompanyProfile.fn_BillingEnterFullName(driver, webWait).clear();
	ObjCompanyProfile.fn_BillingEnterFullName(driver, webWait).sendKeys(fields[15]);
	Thread.sleep(3000);
		
	//To Enter Administrative Contact email
	ObjCompanyProfile.fn_BillingEnterEmail(driver, webWait).clear();
	ObjCompanyProfile.fn_BillingEnterEmail(driver, webWait).sendKeys(fields[16]);
	Thread.sleep(3000);
	
	//To Enter Administrative Phone Number
	ObjCompanyProfile.fn_BillingEnterPhone(driver, webWait).clear();
	ObjCompanyProfile.fn_BillingEnterPhone(driver, webWait).sendKeys(fields[17]);
	Thread.sleep(3000);
	
	//To Enter Administrative Street Address
	ObjCompanyProfile.fn_BillingEnterAddress1(driver, webWait).clear();
	ObjCompanyProfile.fn_BillingEnterAddress1(driver, webWait).sendKeys(fields[18]);
	Thread.sleep(3000);
	
	//To Enter Administrative Street Address 2
	ObjCompanyProfile.fn_BillingEnterAddress2(driver, webWait).clear();
	ObjCompanyProfile.fn_BillingEnterAddress2(driver, webWait).sendKeys(fields[19]);
	Thread.sleep(3000);
	
	//To Enter Administrative City
	ObjCompanyProfile.fn_BillingEnterCity(driver, webWait).clear();
	ObjCompanyProfile.fn_BillingEnterCity(driver, webWait).sendKeys(fields[20]);
	Thread.sleep(3000);
	
	//To Enter Administrative Country
	List<WebElement> billingDropDownCountry= driver.findElement(By.name("companyProfile[billingCountry]")).findElements(By.tagName("option"));
	for (WebElement Country : billingDropDownCountry) {
	       if (Country.getText().equals(fields[21])) {
	           Country.click();
	        }
	 }
		
	//To Enter Administrative State
	Select billingDropDownState = new Select(ObjCompanyProfile.fn_BillingEnterState(driver, webWait));
	Thread.sleep(3000);
	billingDropDownState.selectByVisibleText(fields[22]);
	Thread.sleep(3000);
		
	//To Enter Postal Code
	ObjCompanyProfile.fn_BillingEnterPostalCode(driver, webWait).clear();
	ObjCompanyProfile.fn_BillingEnterPostalCode(driver, webWait).sendKeys(fields[23]);
	Thread.sleep(3000);
	
	/*****************************************************/
	//Batch Job Alert Contact
	/*****************************************************/
	//To Enter Administrative Contact Name
	ObjCompanyProfile.fn_BatchJobAlertEnterFullName(driver, webWait).clear();
	ObjCompanyProfile.fn_BatchJobAlertEnterFullName(driver, webWait).sendKeys(fields[24]);
	Thread.sleep(3000);
			
	//To Enter Administrative Contact email
	ObjCompanyProfile.fn_BatchJobAlertEnterEmail(driver, webWait).clear();
	ObjCompanyProfile.fn_BatchJobAlertEnterEmail(driver, webWait).sendKeys(fields[25]);
	Thread.sleep(3000);
	
	//To Click on Save 
	ObjCompanyProfile.fn_ClickSave(driver, webWait).click();
	
	Thread.sleep(20000);
	}
	
	
}
