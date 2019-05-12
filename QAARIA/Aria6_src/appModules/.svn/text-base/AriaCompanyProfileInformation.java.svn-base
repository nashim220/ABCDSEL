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
	strdropdownCountry.get(236).click();
	//Select dropdownCountry = new Select(ObjCompanyProfile.fn_EnterCountry(driver, webWait));
	//dropdownCountry.selectByVisibleText(fields[4]);
	//To Enter Value for State/Province Field
	Select dropdownState = new Select(ObjCompanyProfile.fn_EnterState(driver, webWait));
	//dropdownState.deselectAll();
	Thread.sleep(3000);
	dropdownState.selectByVisibleText(fields[5]);
	//To Enter Value for Postal Code Field
	ObjCompanyProfile.fn_EnterPostalCode(driver, webWait).clear();
	ObjCompanyProfile.fn_EnterPostalCode(driver, webWait).sendKeys(fields[6]);
	//To Click on Use Company Address 
	ObjCompanyProfile.fn_ClickCompnayAddressButton(driver, webWait).click();
	Thread.sleep(20000);
	}
}
