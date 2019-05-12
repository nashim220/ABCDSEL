//Author:- Abhishek, Date :- 04-15-2016
//Purpose:- To retrieve all the page Objects Under CompanyProfileInformation Section
package appModules;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testCases.BaseTestCase;
import utility.Log;
import utility.ReadFiles;
import utility.Utils;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import pageObjects.*;
import pageObjects.*;

public class ValidateAriaCompanyProfileInformation extends BaseTestCase
{
	
	public void fn_AccessCompanyProfileObjects(String CompanyInformation) throws InterruptedException, IOException
	{
		
	WebDriverWait webWait;
	webWait = new WebDriverWait(driver,2000);
	//System.out.print("arrayvalues are "+CompanyInformation);
	//String[] fields= CompanyInformation.split("#");
	
	String strCompanyProfileDataFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
			 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
			 + "TestData.xlsx";
	File fileCompanyProfile = new File(strCompanyProfileDataFilePath);
	
	//TODO : Read Test Excel
	List<HashMap<String,String>> ExcelFileList = ReadFiles.readExcelFile(fileCompanyProfile, CompanyInformation);
	Map<String, Map<String,String>> ExcelMap = new HashMap<String, Map<String,String>>();
	Map<String,String> ExcelListValues ;
	String ContactTypeExcelkey = "";
	String FullNameVal = "";
	String EmailVal = "";
	String WebDomainVal = "";
	String PhoneNumberVal =  "";
	String StreetAddVal = "";
	String StreetAdd2Val =  "";
	String CityVal =  "";
	String CountryVal =  "";
	String StateVal =  "";
	String PostalCodeVal =  "";
	String LocalityVal =  "";
	String LocaleVal =  "";
	for(Map<String, String> map : ExcelFileList)
	{
		ExcelListValues = new HashMap<String,String>();
		ContactTypeExcelkey = map.get("SGAS_EOM_SF");
					
		if(map.get("Full Name")==null){
			FullNameVal =  "";
			ExcelListValues.put("Full Name", FullNameVal);
		}
		else{
			FullNameVal =  map.get("Full Name");
			ExcelListValues.put("Full Name", FullNameVal);
		}
		if(map.get("E-mail Address")==null){
			EmailVal =  "";
			ExcelListValues.put("E-mail Address", EmailVal);
		}
		else{
			EmailVal =  map.get("E-mail Address");
			ExcelListValues.put("E-mail Address", EmailVal);
		}
		if(map.get("Web Domain*")==null){
			WebDomainVal =  "";
			ExcelListValues.put("Web Domain*", WebDomainVal);
		}
		else{
			WebDomainVal =  map.get("Web Domain*");
			ExcelListValues.put("Web Domain*", WebDomainVal);
		}
		
		if(map.get("Phone Number")==null){
			PhoneNumberVal =  "";
			ExcelListValues.put("Phone Number", PhoneNumberVal);
		}
		else{
			PhoneNumberVal =  map.get("Phone Number");
			ExcelListValues.put("Phone Number", PhoneNumberVal);
		}
		
		if(map.get("Street Address")==null){
			StreetAddVal =  "";
			ExcelListValues.put("Street Address", StreetAddVal);
		}
		else{
			StreetAddVal =  map.get("Street Address");
			ExcelListValues.put("Street Address", StreetAddVal);
		}
		
		if(map.get("Street Address 2")==null){
			StreetAdd2Val =  "";
			ExcelListValues.put("Street Address 2", StreetAdd2Val);
		}
		else{
			StreetAdd2Val =  map.get("Street Address 2");
			ExcelListValues.put("Street Address 2", StreetAdd2Val);
		}
		
		if(map.get("City")==null){
			CityVal =  "";
			ExcelListValues.put("City", CityVal);
		}
		else{
			CityVal =  map.get("City");
			ExcelListValues.put("City", CityVal);
		}
		
		if(map.get("Country")==null){
			CountryVal =  "";
			ExcelListValues.put("Country", CountryVal);
		}
		else{
			CountryVal =  map.get("Country");
			ExcelListValues.put("Country", CountryVal);
		}
		
		if(map.get("State/Province")==null){
			StateVal =  "";
			ExcelListValues.put("State/Province", StateVal);
		}
		else{
			StateVal =  map.get("State/Province");
			ExcelListValues.put("State/Province", StateVal);
		}
		
		if(map.get("Postal Code")==null){
			PostalCodeVal =  "";
			ExcelListValues.put("Postal Code", PostalCodeVal);
		}
		else{
			PostalCodeVal =  map.get("Postal Code");
			ExcelListValues.put("Postal Code", PostalCodeVal);
		}
		
		if(map.get("Locality")==null){
			LocalityVal =  "";
			ExcelListValues.put("Locality", LocalityVal);
		}
		else{
			LocalityVal =  map.get("Locality");
			ExcelListValues.put("Locality", LocalityVal);
		}
		
		if(map.get("Locale")==null){
			LocaleVal =  "";
			ExcelListValues.put("Locale", LocaleVal);
		}
		else{
			LocaleVal =  map.get("Locale");
			ExcelListValues.put("Locale", LocaleVal);
		}
		if(ContactTypeExcelkey != "" )
		{
			ExcelMap.put(ContactTypeExcelkey, ExcelListValues);
		}
	}
	System.out.println("Number of Contacts in Excel : "+ExcelMap.keySet().size());
	
	 
	AriaEOM ObjCompProfile = new AriaEOM();
	ValidateCompanyProfileInformationObjects ObjCompanyProfile = new ValidateCompanyProfileInformationObjects();
	
	//Click on Configurations link
	ObjCompProfile.fn_clickConfiguration(driver, webWait).click();
	
	//Click on Client settings Link
	ObjCompProfile.fn_clickClientSetting(driver, webWait).click();
	
	//Click on Company Profile Link
	ObjCompProfile.fn_clickCompanyProfile(driver, webWait).click();
	
	String AppFullName = "";
	String AppEmail = "";
	String AppWebDomain = "";
	String AppPhoneNumber = "";
	String AppStreetAdd = "";
	String AppStreetAdd2 = "";
	String AppCity = "";
	String AppCountry = "";
	String AppState = "";
	String AppPostalCode = "";
	String AppLocality = "";
	String AppLocale = "";
	Map<String, Map<String,String>> ApplicationMap = new HashMap<String, Map<String,String>>();
	Map<String,String> ApplicationListValues = new HashMap<String, String>();
	
	/*****************************************************/
	//					Company Information
	/*****************************************************/
	String AppCompInfoKey = "Company Information";
	ApplicationListValues.put("Full Name", AppFullName);
	ApplicationListValues.put("E-mail Address", AppEmail);
	
	//To Validate Value for WebDomain Field
	String Domain = ObjCompanyProfile.fn_EnterWebDomain(driver, webWait).getAttribute("value");
	ApplicationListValues.put("Web Domain*", Domain);
	
	//To Validate Value for Phone Number Field
	String PhoneNumber = ObjCompanyProfile.fn_EnterPhoneNumber(driver, webWait).getAttribute("value");
	ApplicationListValues.put("Phone Number", PhoneNumber);
	
	//To Validate Value for Street Address Field
	String Address1 = ObjCompanyProfile.fn_EnterStreetAddress(driver, webWait).getAttribute("value");
	ApplicationListValues.put("Street Address", Address1);
	
	//To Validate Value for Street Address2 Field
	String Address2 = ObjCompanyProfile.fn_EnterStreetAddress2(driver, webWait).getAttribute("value");
	ApplicationListValues.put("Street Address 2", Address2);
	
	//To Validate Value for City Field
	String City = ObjCompanyProfile.fn_EnterCity(driver, webWait).getAttribute("value");
	ApplicationListValues.put("City", City);
	
	//To Validate Value for Country Field
	WebElement CountryObj = ObjCompanyProfile.fn_EnterCountry(driver, webWait);
	Select selOptionCountry = new Select(CountryObj);
	WebElement optionCountry = selOptionCountry.getFirstSelectedOption();
	String Country = optionCountry.getText();
	ApplicationListValues.put("Country", Country);
	
	//To Validate Value for State/Province Field
	WebElement StateObj = ObjCompanyProfile.fn_EnterState(driver, webWait);
	Select selOptionState = new Select(StateObj);
	WebElement optionState = selOptionState.getFirstSelectedOption();
	String State = optionState.getText();
	ApplicationListValues.put("State/Province", State);
	
	//To Validate Value for Postal Code Field
	String PostalCode = ObjCompanyProfile.fn_EnterPostalCode(driver, webWait).getAttribute("value");
	ApplicationListValues.put("Postal Code", PostalCode);
	
	//To Validate Value for Locality Field
	String Locality = ObjCompanyProfile.fn_EnterLocality(driver, webWait).getAttribute("value");
	ApplicationListValues.put("Locality", Locality);
	
	
	//To Validate Value for Locality Field
	//List<WebElement> LocaleEmelent = driver.findElements(By.xpath("//*[@id=\"ajaxcontent\"]/form/div[1]"));
	List<WebElement> LocaleEmelent = ObjCompanyProfile.fn_getLocaleElements(driver, webWait);
	WebElement e = LocaleEmelent.get(0);
	String Locale = e.getText().split("Locale")[1].trim();
	ApplicationListValues.put("Locale", Locale);
		
	ApplicationMap.put(AppCompInfoKey, ApplicationListValues);	
	/*****************************************************/
	//				Administrative Contact
	/*****************************************************/
	Map<String,String> ApplicationAdminListValues = new HashMap<String, String>();
	String AppAdminContactKey = "Administrative Contact";
	
	//To Validate Administrative Contact Name
	String AdminFullName = ObjCompanyProfile.fn_AdminEnterFullName(driver, webWait).getAttribute("value");
	ApplicationAdminListValues.put("Full Name", AdminFullName);
	
	//To Validate Administrative Contact email
	String AdminEmail = ObjCompanyProfile.fn_AdminEnterEmail(driver, webWait).getAttribute("value");
	ApplicationAdminListValues.put("E-mail Address", AdminEmail);
	
	//WebDomain
	ApplicationAdminListValues.put("Web Domain*", AppWebDomain);
	
	//To Validate Administrative Phone Number
	String AdminPhoneNumber = ObjCompanyProfile.fn_AdminEnterPhone(driver, webWait).getAttribute("value");
	ApplicationAdminListValues.put("Phone Number", AdminPhoneNumber);
	
	//To Validate Administrative Street Address
	String AdminAddress1 = ObjCompanyProfile.fn_AdminEnterAddress1(driver, webWait).getAttribute("value");
	ApplicationAdminListValues.put("Street Address", AdminAddress1);
	
	//To Validate Administrative Street Address
	String AdminAddress2 = ObjCompanyProfile.fn_AdminEnterAddress2(driver, webWait).getAttribute("value");
	ApplicationAdminListValues.put("Street Address 2", AdminAddress2);
		
	//To Validate Administrative City
	String AdminCity = ObjCompanyProfile.fn_AdminEnterCity(driver, webWait).getAttribute("value");
	ApplicationAdminListValues.put("City", AdminCity);
	
	//To Validate Administrative Country
	WebElement AdminCountryObject = ObjCompanyProfile.fn_AdminEnterCountry(driver, webWait);
	Select selectedOption = new Select(AdminCountryObject);
	WebElement option = selectedOption.getFirstSelectedOption();
	String AdminCountry = option.getText();
	ApplicationAdminListValues.put("Country", AdminCountry);
	
	//To Validate Value for State/Province Field
	WebElement AdminStateObj = ObjCompanyProfile.fn_AdminEnterState(driver, webWait);
	Select selectedOptionState = new Select(AdminStateObj);
	WebElement AdminStateoption = selectedOptionState.getFirstSelectedOption();
	String AdminState = AdminStateoption.getText();
	ApplicationAdminListValues.put("State/Province", AdminState);
	
	//To Validate Postal Code
	String AdminPostalCode = ObjCompanyProfile.fn_AdminEnterPostalCode(driver, webWait).getAttribute("value");
	ApplicationAdminListValues.put("Postal Code", AdminPostalCode);
	
	//Admin Locality
	String AdminLocality = ObjCompanyProfile.fn_AdminEnterLocality(driver, webWait).getAttribute("value");
	ApplicationAdminListValues.put("Locality", AdminLocality);
	
	//Admin Locale
	String AdminLocale = "";
	ApplicationAdminListValues.put("Locale", AdminLocale);
	
	//Mapping the Values
	ApplicationMap.put(AppAdminContactKey, ApplicationAdminListValues);

	/*****************************************************/
	//					Billing Contact
	/*****************************************************/
	Map<String,String> ApplicationBillingListValues = new HashMap<String, String>();
	String AppBillingContactKey = "Billing Contact";
	
	//To Validate Billing Contact Name
	String BillingFullName = ObjCompanyProfile.fn_BillingEnterFullName(driver, webWait).getAttribute("value");
	ApplicationBillingListValues.put("Full Name", BillingFullName);
	
	//To Validate Billing Contact email
	String BillingEmail = ObjCompanyProfile.fn_BillingEnterEmail(driver, webWait).getAttribute("value");
	ApplicationBillingListValues.put("E-mail Address", BillingEmail);
	
	//WebDomain
	ApplicationBillingListValues.put("Web Domain*", AppWebDomain);
	
	//To Validate Billing Phone Number
	String BillingPhoneNumber = ObjCompanyProfile.fn_BillingEnterPhone(driver, webWait).getAttribute("value");
	ApplicationBillingListValues.put("Phone Number", BillingPhoneNumber);
	
	//To Validate Billing Street Address
	String BillingAddress1 = ObjCompanyProfile.fn_BillingEnterAddress1(driver, webWait).getAttribute("value");
	ApplicationBillingListValues.put("Street Address", BillingAddress1);
	
	//To Validate Billing Street Address 2
	String BillingAddress2 = ObjCompanyProfile.fn_BillingEnterAddress2(driver, webWait).getAttribute("value");
	ApplicationBillingListValues.put("Street Address 2", BillingAddress2);
	
	//To Validate Billing City
	String BillingCity = ObjCompanyProfile.fn_BillingEnterCity(driver, webWait).getAttribute("value");
	ApplicationBillingListValues.put("City", BillingCity);
	
	//To Validate Billing Country
	WebElement BillingCountryObj = ObjCompanyProfile.fn_BillingEnterCountry(driver, webWait);
	Select selOptionBilCountry = new Select(BillingCountryObj);
	WebElement optionBillCountry = selOptionBilCountry.getFirstSelectedOption();
	String BillingCountry = optionBillCountry.getText();
	ApplicationBillingListValues.put("Country", BillingCountry);
	
	//To Validate Value for State/Province Field
	WebElement BillingStateObj = ObjCompanyProfile.fn_BillingEnterState(driver, webWait);
	Select selOptionBilState = new Select(BillingStateObj);
	WebElement optionBillState = selOptionBilState.getFirstSelectedOption();
	String BillingState = optionBillState.getText();
	ApplicationBillingListValues.put("State/Province", BillingState);
		
	//To Validate Billing Postal Code
	String BillingPostalCode = ObjCompanyProfile.fn_BillingEnterPostalCode(driver, webWait).getAttribute("value");
	ApplicationBillingListValues.put("Postal Code", BillingPostalCode);
	
	//Billing Locality
	String BillingLocality = ObjCompanyProfile.fn_BillingEnterLocality(driver, webWait).getAttribute("value");
	ApplicationBillingListValues.put("Locality", BillingLocality);
	
	//Billing Locale
	String BillingLocale = "";
	ApplicationBillingListValues.put("Locale", BillingLocale);
	
	//Mapping the Values
	ApplicationMap.put(AppBillingContactKey, ApplicationBillingListValues);
		
	/*****************************************************/
	//				Batch Job Alert Contact
	/*****************************************************/
	Map<String,String> ApplicationBJAListValues = new HashMap<String, String>();
	String AppBatchJobAlertContactKey = "Batch Job Alert Contact";
	
	//To Validate Administrative Contact Name
	String BatchJobAlertFullName = ObjCompanyProfile.fn_BatchJobAlertEnterFullName(driver, webWait).getAttribute("value");
	ApplicationBJAListValues.put("Full Name", BatchJobAlertFullName);
	
	//To Validate Administrative Contact email
	String BatchJobAlertEmail = ObjCompanyProfile.fn_BatchJobAlertEnterEmail(driver, webWait).getAttribute("value");
	ApplicationBJAListValues.put("E-mail Address", BatchJobAlertEmail);	
	
	//WebDomain
	ApplicationBJAListValues.put("Web Domain*", AppWebDomain);
	
	String BJANumber = "";
	ApplicationBJAListValues.put("Phone Number", BJANumber);
	
	String BJAAddr1 = "";
	ApplicationBJAListValues.put("Street Address", BJAAddr1);
	
	String BJAAddr2 = "";
	ApplicationBJAListValues.put("Street Address 2", BJAAddr2);
	
	String BJACity = "";
	ApplicationBJAListValues.put("City", BJACity);
	
	String BJACountry = "";
	ApplicationBJAListValues.put("Country", BJACountry);
	
	String BJAState = "";
	ApplicationBJAListValues.put("State/Province", BJAState);
	
	String BJAPostalCode = "";
	ApplicationBJAListValues.put("Postal Code", BJAPostalCode);
	
	String BJALocality = "";
	ApplicationBJAListValues.put("Locality", BJALocality);
	
	//Admin Locale
	String BJALocale = "";
	ApplicationBJAListValues.put("Locale", BJALocale);
	
	//Mapping the Values
	ApplicationMap.put(AppBatchJobAlertContactKey, ApplicationBJAListValues);
	Thread.sleep(20000);

	int cnt = 1;
	System.out.println("/-**************************Comparing Values**************************-/");
	for (String Comparekey : ExcelMap.keySet()) {
	    if (ApplicationMap.containsKey(Comparekey)) {
	    	System.out.println("######## Parameter : "+cnt+". "+Comparekey+" ########");
	    	Map<String,String> TableValueMap = new HashMap<String,String>();
	    	Map<String,String> ExcelValueMap = new HashMap<String,String>();
	    	TableValueMap = ApplicationMap.get(Comparekey);
	    	ExcelValueMap = ExcelMap.get(Comparekey);
	    	for(String CompareValues : ExcelValueMap.keySet()){
	    		if(TableValueMap.containsKey(CompareValues)){
	    			if(ExcelValueMap.get(CompareValues).equalsIgnoreCase(TableValueMap.get(CompareValues))){
	    				System.out.println("MATCHED for : "+CompareValues + "=> Excel Value : "+ExcelValueMap.get(CompareValues)+"   Application Value : "+TableValueMap.get(CompareValues));
	    			}
	    			else{
	    				System.out.println("ERROR : Value Did Not Match For : "+CompareValues);
	    				System.out.println("Expected : "+ExcelValueMap.get(CompareValues)+" Found : "+TableValueMap.get(CompareValues));
	    			}
	    		}
	    	}
	    }
	    else{
	    	System.out.println("ERROR : Could Not find Parameter in Application "+Comparekey);
	    }
	    cnt++;
	}
	System.out.println("/-********************************************************************-/");
	}
	
	
}
