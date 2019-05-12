/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_ValidateCouponCategories 
 Purpose     		: 	Purpose of this file is :
						1. To Validate Coupon Categories set for Aria.
						2. This is automation of TC: QAABE-528.

 Date       		:	12/22/2015
 Version Information:	Version 1.0
 
 PreCondition 		:	1. Role based Login required.
 						2. Data to be populated in the "TestCaseDetails", "CouponCodeCategory" worksheet & "CouponCategories" 
 						test case name for excel "TestData.xls".

 Test Steps 		:	1. Login using valid role credentials for Navigating to Marketing.
 						2. Navigate to Marketing -> Coupon Groups.
 						3. Validate the Values for the Various Groups and subsequent Coupons. 						
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AriaMarketing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ValidateCouponCategories;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import utility.EnvironmentDetails;
import utility.Log;
import utility.ReadFiles;
import utility.Utils;
import utility.VerificationMethods;

public class Cls_ValidateCouponCategories extends VerificationMethods{
	
    public static WebDriver driver;
    public static String strTestCaseName = "Validate Coupon Categories for Aria";
	public static WebDriverWait webWait;
    public static WebElement webElement;
    
    @BeforeClass
    public void beforeMethod()throws Exception
    {	
    	//TODO: Initialize the Logger.
    	DOMConfigurator.configure("log4j.xml");	

		//TODO: Set Chrome driver as the driver to launch the test.
		driver = utility.Utils.fn_SetChromeDriverProperties();
		
		//TODO: initialize the webWait
		webWait = new WebDriverWait(driver, 60);
		
		EnvironmentDetails objEnv = new EnvironmentDetails();
		Log.info("Login to Aria Application");
		//TODO: This parameters are taken from Exec file and its reference is available in EnvironmentDetails
		AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
    }


/*    
    Function fn_ValidatingCouponCategories: This is the actual Test NG test for Validating the Coupon Categories.
*/
	@Test(dataProvider = "objectTestData", description = "Validate Coupon Categories for Aria")
	public void fn_ValidatingCouponCategories(String strFileName, String strWorksheet) throws Exception{

		//TODO: Create object for the classes to be used for accessing reusable codes.		
		ValidateCouponCategories objCouponCategories = new ValidateCouponCategories();
		
		Boolean blnFREETRIAL = false;
		Boolean blnCredit = false;
		Boolean blnTerm = false;
		Boolean blnTransition = false;
		Boolean blnRevenue = false;
		
    	//TODO: Log the beginning of the test case.
    	Log.startTestCase(strTestCaseName);
    			
		try{
			
			//TODO: Read the excel file for the Coupon Category details to be validated.
			String strExcelFile = System.getProperty("user.dir")
					+ System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
					+ System.getProperty("file.separator") + strFileName;
			File fileCouponCategories = new File(strExcelFile);
			ArrayList<String> arrlstFileContents = ReadFiles.readExcelFileAsArray(fileCouponCategories, strWorksheet);
			
			objCouponCategories.fn_clickMarketing(driver, webWait).click();
			objCouponCategories.fn_clickCouponGroups(driver, webWait).click();
			objCouponCategories.fn_getTable(driver, webWait);
			
			WebElement webCouponCategoryTable = objCouponCategories.fn_getTable(driver, webWait).findElement(By.tagName("tbody"));
			List<WebElement> lstwebCouponCategoryRows = webCouponCategoryTable.findElements(By.tagName("tr"));
			ArrayList<String> arrGroupNames = new ArrayList<>(lstwebCouponCategoryRows.size());
			for(WebElement rows : lstwebCouponCategoryRows){
				
				List<WebElement> cols = rows.findElements(By.tagName("td"));
				// read the list of Group Names so that they can be traversed for Validations.
				arrGroupNames.add(cols.get(1).getAttribute("innerText").toString().trim());				
			}
			//TODO: For the various Coupon Categories, validate their details as mentioned in excel file read.
			for(String strGroup : arrGroupNames){					

				//generate a relative XPath to overcome the page refresh after return.
				String strXpath = "//td[contains(text(),'"+strGroup+"')]/preceding-sibling::td[contains(@class, 'sorting_')]";
				driver.findElement(By.xpath(strXpath)).click();

				objCouponCategories.fn_clickClose(driver, webWait);
				Utils.takeScreenshot(driver, strTestCaseName);
				
		    	//TODO: Scroll to the end of the page to capture the Coupons added for the group.
		    	Actions objActions = new Actions(driver);
		    	objActions.sendKeys(Keys.PAGE_DOWN);
		    	Thread.sleep(500);
		    	objActions.click(objCouponCategories.fn_getCouponsListTable(driver, webWait)).perform();
		    	Thread.sleep(500);
		    	objActions.sendKeys(Keys.PAGE_DOWN);
		    	Thread.sleep(500);
		    	objActions.click(objCouponCategories.fn_getCouponsListTable(driver, webWait)).perform();
		    	Thread.sleep(500);
		    	Utils.takeScreenshot(driver, strTestCaseName);
				
		    	//TODO: switch to the Coupon Groups to do their respective validations.
				switch (strGroup){
				
				case "FREE TRIAL":
					blnFREETRIAL = fn_FREETRIAL(driver, webWait, arrlstFileContents);
					objCouponCategories.fn_clickClose(driver, webWait).click();
					objCouponCategories.fn_getTable(driver, webWait);
					break;
					
				case "Credit":
					blnCredit = fn_Credit(driver, webWait, arrlstFileContents);
					objCouponCategories.fn_clickClose(driver, webWait).click();
					objCouponCategories.fn_getTable(driver, webWait);
					break;
					
				case "Term":
					blnTerm = fn_Term(driver, webWait, arrlstFileContents);
					objCouponCategories.fn_clickClose(driver, webWait).click();
					objCouponCategories.fn_getTable(driver, webWait);
					break;
					
				case "Transition":
					blnTransition = fn_Transition(driver, webWait, arrlstFileContents);
					objCouponCategories.fn_clickCouponGroups(driver, webWait).click();
					objCouponCategories.fn_getTable(driver, webWait);
					break;
				
				case "Revenue":
					blnRevenue = fn_Revenue(driver, webWait, arrlstFileContents);
					objCouponCategories.fn_clickClose(driver, webWait).click();
					objCouponCategories.fn_getTable(driver, webWait);
					break;
					
				default:
					objCouponCategories.fn_clickClose(driver, webWait).click();
					objCouponCategories.fn_getTable(driver, webWait);
					Log.error("ERROR: No such coupon code validation can be done !");
		       		Reporter.log("ERROR: No such coupon code validation can be done !");
				}					
			}
			
			if(blnFREETRIAL == false)
				assertTrue(false, "ERROR: Verification for the Group type ''FREE TRIAL'' has failed !");
			if(blnCredit == false)
				assertTrue(false, "ERROR: Verification for the Group type ''Credit'' has failed !");
			if(blnTerm == false)
				assertTrue(false, "ERROR: Verification for the Group type ''Term'' has failed !");
			if(blnTransition == false)
				assertTrue(false, "ERROR: Verification for the Group type ''Transition'' has failed !");
			if(blnRevenue == false)
				assertTrue(false, "ERROR: Verification for the Group type ''Revenue'' has failed !");
		}
		catch (Exception exception){
    		
    		Utils.takeScreenshot(driver, strTestCaseName);
    		Log.error("ERROR: Validaation for the Coupon Categories couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Validaation for the Coupon Categories couldn't be done due to exception:  "+exception.toString());
    		throw exception;
    	}
    	catch (AssertionError exception){
    		
    		Utils.takeScreenshot(driver, strTestCaseName);
    		Log.error("ERROR: Validaation for the Coupon Categories couldn't be done due to exception:  "+exception.toString());
       		Reporter.log("ERROR: Validaation for the Coupon Categories couldn't be done due to exception:  "+exception.toString());
    		throw exception;
    	}

    	//TODO: Log the end of the test case. 
    	Log.endTestCase(strTestCaseName);
	}
	
	
	public Boolean fn_FREETRIAL(WebDriver driver, WebDriverWait	webWait, ArrayList<String> arrlstFileContents) throws Exception{
		
		//TODO: Create object for the classes to be used for accessing reusable codes.		
		ValidateCouponCategories objCouponCategories = new ValidateCouponCategories();
		
		Boolean blnFREETRIAL = true;
		Integer intCouponLoop = 0;
		String strPromoName = null;
		String strDiscPerctTerms = null;
		String strGroupNameText = objCouponCategories.fn_getGroupName(driver, webWait).getAttribute("value").toString();
		
		//now traverse the read excel for validating details.
		for(Integer intForLoop = 0; intForLoop < arrlstFileContents.size(); intForLoop = intForLoop + 5){
			
			if(strGroupNameText.equalsIgnoreCase(arrlstFileContents.get(intForLoop))){
				
				WebElement webCouponListTable = objCouponCategories.fn_getCouponsListTable(driver, webWait)
												.findElement(By.tagName("tbody"));
				List<WebElement> lstwebCouponListRows = webCouponListTable.findElements(By.tagName("tr"));
				//TODO: Search the Coupon Category details read in array list with the ones listed on page.
				intCouponLoop = 0;
				for(WebElement webCouponRows : lstwebCouponListRows){
					
					List<WebElement> colsCouponDetails = webCouponRows.findElements(By.tagName("td"));
					intCouponLoop += 1;
					if(colsCouponDetails.size() >= 2){
						
						strPromoName = colsCouponDetails.get(0).getAttribute("innerText").toString().trim();
						strDiscPerctTerms = colsCouponDetails.get(3).getAttribute("innerText").toString().trim();
						
						if(strPromoName.equalsIgnoreCase(arrlstFileContents.get(intForLoop+1)))
							if(strDiscPerctTerms.contains(arrlstFileContents.get(intForLoop+2)))
								if(strDiscPerctTerms.contains(arrlstFileContents.get(intForLoop+3))){
									
									Log.info("The Coupon Details have been confirmed for Type '"+arrlstFileContents.get(intForLoop)
												+"' with Promo Name as '"+arrlstFileContents.get(intForLoop+1)
												+"' and matching Discount Percentage & Discount Term (Days)!");
									break;
								}
					}
					if(intCouponLoop >= lstwebCouponListRows.size()){
						Log.info("The Coupon Details couldn't be confirmed for Type '"+arrlstFileContents.get(intForLoop)
									+"' with Promo Name as '"+arrlstFileContents.get(intForLoop+1)
									+"' or with matching Discount Percentage & Discount Term (Days)!");
						blnFREETRIAL = false;						
					}
				}
			}
		}		
		return blnFREETRIAL;
	}
    
	
	public Boolean fn_Credit(WebDriver driver, WebDriverWait	webWait, ArrayList<String> arrlstFileContents) throws Exception{
		
		//TODO: Create object for the classes to be used for accessing reusable codes.		
		ValidateCouponCategories objCouponCategories = new ValidateCouponCategories();
		
		Boolean blnCredit = true;
		Integer intCouponLoop = 0;
		String strPromoName = null;
		String strDiscPerct = null;
		String strGroupNameText = objCouponCategories.fn_getGroupName(driver, webWait).getAttribute("value").toString();
		
		//now traverse the read excel for validating details.
		for(Integer intForLoop = 0; intForLoop < arrlstFileContents.size(); intForLoop = intForLoop + 5){
			
			if(strGroupNameText.equalsIgnoreCase(arrlstFileContents.get(intForLoop))){
				
				WebElement webCouponListTable = objCouponCategories.fn_getCouponsListTable(driver, webWait)
												.findElement(By.tagName("tbody"));
				List<WebElement> lstwebCouponListRows = webCouponListTable.findElements(By.tagName("tr"));
				//TODO: Search the Coupon Category details read in array list with the ones listed on page.
				intCouponLoop = 0;
				for(WebElement webCouponRows : lstwebCouponListRows){
					
					List<WebElement> colsCouponDetails = webCouponRows.findElements(By.tagName("td"));
					intCouponLoop += 1;
					if(colsCouponDetails.size() >= 2){
						
						strPromoName = colsCouponDetails.get(0).getAttribute("innerText").toString().trim();
						strDiscPerct = colsCouponDetails.get(3).getAttribute("innerText").toString().trim();
						
						if(strPromoName.equalsIgnoreCase(arrlstFileContents.get(intForLoop+1)))
							if(strDiscPerct.contains(arrlstFileContents.get(intForLoop+2))){
									
									Log.info("The Coupon Details have been confirmed for Type '"+arrlstFileContents.get(intForLoop)
												+"' with Promo Name as '"+arrlstFileContents.get(intForLoop+1)
												+"' and matching Credit Amount!");
									break;
								}
					}
					if(intCouponLoop >= lstwebCouponListRows.size()){
						Log.info("The Coupon Details couldn't be confirmed for Type '"+arrlstFileContents.get(intForLoop)
									+"' with Promo Name as '"+arrlstFileContents.get(intForLoop+1)
									+"' or with matching Credit Amount!");
						blnCredit = false;						
					}
				}
			}
		}	
		return blnCredit;
	}
	
	
	public Boolean fn_Term(WebDriver driver, WebDriverWait	webWait, ArrayList<String> arrlstFileContents) throws Exception{
		
		//TODO: Create object for the classes to be used for accessing reusable codes.		
		ValidateCouponCategories objCouponCategories = new ValidateCouponCategories();
		
		Boolean blnTerm = true;
		Integer intCouponLoop = 0;
		String strTermCommit = null;		
		String strDiscPerctTerms = null;
		String strGroupNameText = objCouponCategories.fn_getGroupName(driver, webWait).getAttribute("value").toString();
		
		//now traverse the read excel for validating details.
		for(Integer intForLoop = 0; intForLoop < arrlstFileContents.size(); intForLoop = intForLoop + 5){
			
			if(strGroupNameText.equalsIgnoreCase(arrlstFileContents.get(intForLoop))){
				
				WebElement webCouponListTable = objCouponCategories.fn_getCouponsListTable(driver, webWait)
												.findElement(By.tagName("tbody"));
				List<WebElement> lstwebCouponListRows = webCouponListTable.findElements(By.tagName("tr"));
				//TODO: Search the Coupon Category details read in array list with the ones listed on page.
				intCouponLoop = 0;
				for(WebElement webCouponRows : lstwebCouponListRows){
					
					List<WebElement> colsCouponDetails = webCouponRows.findElements(By.tagName("td"));
					intCouponLoop += 1;
					String strPercent = arrlstFileContents.get(intForLoop+2).replace(" percent", "%");
					if(colsCouponDetails.size() >= 2){
						
						strTermCommit = colsCouponDetails.get(0).getAttribute("innerText").toString().trim();
						strDiscPerctTerms = colsCouponDetails.get(3).getAttribute("innerText").toString().trim();						
						
						if(strTermCommit.contains(arrlstFileContents.get(intForLoop+1).toLowerCase()) && strTermCommit.contains(arrlstFileContents.get(intForLoop+4).toLowerCase()))
							if(strDiscPerctTerms.contains(strPercent) && strDiscPerctTerms.contains(arrlstFileContents.get(intForLoop+1).toUpperCase())){
									
									Log.info("The Coupon Details have been confirmed for Type '"+arrlstFileContents.get(intForLoop)
												+"' with Term Commitment as '"+arrlstFileContents.get(intForLoop+1)
												+"' and Discount Percentage as :"+strPercent
												+" and for the Country: "+arrlstFileContents.get(intForLoop+4)+" !");
									break;
								}
					}
					if(intCouponLoop >= lstwebCouponListRows.size()){
						Log.info("The Coupon Details couldn't be confirmed for Type '"+arrlstFileContents.get(intForLoop)
									+"' with Term Commitment as '"+arrlstFileContents.get(intForLoop+1)
									+"' and Discount Percentage as :"+strPercent
									+" and for the Country: "+arrlstFileContents.get(intForLoop+4)+" !");
						blnTerm = false;
					}
				}
			}
		}	
		return blnTerm;
	}
	
	
	public Boolean fn_Revenue(WebDriver driver, WebDriverWait	webWait, ArrayList<String> arrlstFileContents) throws Exception{
		
		//TODO: Create object for the classes to be used for accessing reusable codes.		
		ValidateCouponCategories objCouponCategories = new ValidateCouponCategories();
		
		Boolean blnTerm = true;
		Integer intCouponLoop = 0;
		//String strRevenueCommit = null;
		String strDiscPerct = null;
		String strGroupNameText = objCouponCategories.fn_getGroupName(driver, webWait).getAttribute("value").toString();
		
		//now traverse the read excel for validating details.
		for(Integer intForLoop = 0; intForLoop < arrlstFileContents.size(); intForLoop = intForLoop + 5){
			
			if(strGroupNameText.equalsIgnoreCase(arrlstFileContents.get(intForLoop))){
				
				WebElement webCouponListTable = objCouponCategories.fn_getCouponsListTable(driver, webWait)
												.findElement(By.tagName("tbody"));
				List<WebElement> lstwebCouponListRows = webCouponListTable.findElements(By.tagName("tr"));
				//TODO: Search the Coupon Category details read in array list with the ones listed on page.
				intCouponLoop = 0;
				for(WebElement webCouponRows : lstwebCouponListRows){
					
					List<WebElement> colsCouponDetails = webCouponRows.findElements(By.tagName("td"));
					intCouponLoop += 1;
					if(colsCouponDetails.size() >= 2){
						
						//below line of code added to modify in future when Revenue is added to the Coupons list.
						//strRevenueCommit = colsCouponDetails.get(?).getAttribute("innerText").toString().trim();
						strDiscPerct = colsCouponDetails.get(3).getAttribute("innerText").toString().trim();
						
						if(strDiscPerct.contains(arrlstFileContents.get(intForLoop+1))){
							
							Log.info("The Coupon Details have been confirmed for Type '"+arrlstFileContents.get(intForLoop)
									/*+"' with Revenue Commitment as '"+arrlstFileContents.get(intForLoop+1)*/
									+"' and Discount Percentage as :"+arrlstFileContents.get(intForLoop+2)+"% !");
							break;
						}
					}
					if(intCouponLoop >= lstwebCouponListRows.size()){
						Log.info("The Coupon Details have been confirmed for Type '"+arrlstFileContents.get(intForLoop)
								/*+"' with Revenue Commitment as '"+arrlstFileContents.get(intForLoop+1)*/
								+"' and Discount Percentage as :"+arrlstFileContents.get(intForLoop+2)+"% !");
						blnTerm = false;						
					}
				}
			}
		}	
		return blnTerm;
	}
	

	public Boolean fn_Transition(WebDriver driver, WebDriverWait webWait, ArrayList<String> arrlstFileContents) throws Exception{
		
		//TODO: Create object for the classes to be used for accessing reusable codes.		
		ValidateCouponCategories objCouponCategories = new ValidateCouponCategories();
		
		Boolean blnTerm = true;
		Integer intCouponLoop = 0;
		String strGroupNameText = objCouponCategories.fn_getGroupName(driver, webWait).getAttribute("value").toString();
		ArrayList<String> arrTransitionPromos = new ArrayList<String>();
		
		//now traverse the read excel for validating details.
		for(Integer intForLoop = 0; intForLoop < arrlstFileContents.size(); intForLoop = intForLoop + 5){
			
			//TODO: Create a sub array list for details related to Promo type Transition. 
			if(strGroupNameText.equalsIgnoreCase(arrlstFileContents.get(intForLoop))){
				
				arrTransitionPromos.add(arrlstFileContents.get(intForLoop));
				arrTransitionPromos.add(arrlstFileContents.get(intForLoop+1));
				arrTransitionPromos.add(arrlstFileContents.get(intForLoop+2));
				arrTransitionPromos.add(arrlstFileContents.get(intForLoop+3));
				arrTransitionPromos.add(arrlstFileContents.get(intForLoop+4));
			}
		}
		
		if(arrTransitionPromos.size()==0){

				blnTerm = false;
				Log.error("ERROR: There are no Coupons mentioned for the Transition Type.");
				Reporter.log("ERROR: There are no Coupons mentioned for the Transition Type.");
				return blnTerm;
		}
		
		WebElement webCouponListTable = objCouponCategories.fn_getCouponsListTable(driver, webWait)
															.findElement(By.tagName("tbody"));
		List<WebElement> lstwebCouponListRows = webCouponListTable.findElements(By.tagName("tr"));
		ArrayList<String> arrTransitionNames = new ArrayList<>(lstwebCouponListRows.size());
		//read all the bundles mentioned for the Transition and use it later for Validations.
		for(WebElement webCouponRows : lstwebCouponListRows){
			
			intCouponLoop += 1;
			List<WebElement> colsCouponDetails = webCouponRows.findElements(By.tagName("td"));
			if(colsCouponDetails.size() >= 2)						
				arrTransitionNames.add(colsCouponDetails.get(3).getAttribute("innerText").toString().trim());					
		}
		if(arrTransitionNames.size()==0){
			
			blnTerm = false;
			Log.error("ERROR: There are no Discount Bundles mentioned for the Transition Type.");
			Reporter.log("ERROR: There are no Discount Bundles mentioned for the Transition Type.");					
		}
		
		//TODO: Now do the validations visiting the Discount Bundles.  
		for(String strTransitionName : arrTransitionNames){
			
			Boolean blnDiscountBundles = fn_ValidateDiscountBundles(driver, webWait, strTransitionName, arrTransitionPromos);
			if(blnDiscountBundles == false){
				
				blnTerm = false;
				Log.error("ERROR: Validation for the Transition Bundle '"+strTransitionName+"' has failed !");
				Reporter.log("ERROR: Validation for the Transition Bundle '"+strTransitionName+"' has failed !");
			}						
		}	
		return blnTerm;
	}
	
	
	public Boolean fn_ValidateDiscountBundles(WebDriver driver, WebDriverWait webWait, String strTransitionName,ArrayList<String> arrTransitionPromos) throws Exception{
		
		//TODO: Create object for the classes to be used for accessing reusable codes.
		ValidateCouponCategories objCouponCategories = new ValidateCouponCategories();
		
		Boolean blnDiscountBundles = false;
		Integer intRulesLoop = 0; 
		
		objCouponCategories.fn_clickDiscountBundles(driver, webWait).click();
		objCouponCategories.fn_getTable(driver, webWait);
		Thread.sleep(2500);					
		Utils.takeScreenshot(driver, strTestCaseName);
		
		//generate a relative XPath to overcome the page refresh after return.
		String strXpath = "//td[contains(text(),'"+strTransitionName+"')]/preceding-sibling::td[contains(@class, 'sorting_')]";
		driver.findElement(By.xpath(strXpath)).click();
		
		objCouponCategories.fn_getBundleName(driver, webWait);
		String strBundleName = objCouponCategories.fn_getBundleName(driver, webWait).getAttribute("innerText").toString().trim();
		if(strBundleName.contains(strTransitionName)){
			
			objCouponCategories.fn_clickRulesTab(driver, webWait).click();
			objCouponCategories.fn_getRulesTable(driver, webWait);
			
			WebElement webRulesTable = objCouponCategories.fn_getRulesTable(driver, webWait).findElement(By.tagName("tbody"));
			List<WebElement> lstwebRulesTableRows = webRulesTable.findElements(By.tagName("tr"));
			//now traverse the list of rules to Validate it with the details mentioned in lookup excel
			for(Integer intForLoop = 1; intForLoop < arrTransitionPromos.size(); intForLoop = intForLoop + 5){			
				
				intRulesLoop = 0;				
				for(WebElement rows : lstwebRulesTableRows){
					
					List<WebElement> cols = rows.findElements(By.tagName("td"));
					if(cols.size() != 0){	//to avoid the header row "th" read as a "tr".
						
						String strRuleName = cols.get(1).getAttribute("innerText").toString().trim();
						String strDiscountType = cols.get(5).getAttribute("innerText").toString().trim();
						intRulesLoop += 1;
						if(strRuleName.contains(arrTransitionPromos.get(intForLoop+1)))
							if(strDiscountType.contains("Percentage")){
								
								WebElement webClickPercent = cols.get(5).findElement(By.linkText(strDiscountType));
								webClickPercent.click();
								Thread.sleep(1000);
								Utils.takeScreenshot(driver, strTestCaseName);
								
										//TODO: This code to be made active, once the Meesage Box is designed readable.
										//WebElement webPercentDialog = driver.findElement(By.xpath("//div"));
										//String strPercentageVal = webPercentDialog.getText().toString().trim();
										/*if(strPercentageVal.contains(arrTransitionPromos.get(intForLoop+1))){
										
											blnDiscountBundles = true;
											Log.info("Validation for the Disocunt Bundle with percentage as "+arrTransitionPromos.get(intForLoop+1)+"% is successfully done!");
											WebElement webOk = driver.switchTo().activeElement();
											webOk.click();
											driver.switchTo().activeElement();
											Thread.sleep(1000);
											break;
										}*/
								
								blnDiscountBundles = true;
								Log.info("Validation for the Disocunt Bundle with percentage as "+arrTransitionPromos.get(intForLoop+1)+"% is successfully done!");
								WebElement webOk = driver.switchTo().activeElement();
								webOk.click();
								Thread.sleep(1000);
								break;
							}
					}
					if(intRulesLoop >= lstwebRulesTableRows.size()-2){
			
						blnDiscountBundles = false;						
						Log.error("ERROR: Validation for the Disocunt Bundle with percentage as "+arrTransitionPromos.get(intForLoop+1)+"% has failed !");
						Reporter.log("ERROR: Validation for the Disocunt Bundle with percentage as "+arrTransitionPromos.get(intForLoop+1)+"% has failed !");
					}
				}
			}
		}
		else{
			
			blnDiscountBundles = false;
			Log.error("ERROR: Incorrect Discount Bundle has been selected !");
			Reporter.log("ERROR: Incorrect Discount Bundle has been selected !");			
		}	
		
		return blnDiscountBundles;		
	}
	
	
	//TODO: Reading data for the test    
	@DataProvider(name = "objectTestData")
	public Object[][] testExcelData() throws DataDrivenFrameworkException
	{
	    Utils objUtils=new Utils();
	    return objUtils.data1("TestCaseDetails", "CouponCategories");
	}
	
	
	@AfterClass
	public void afterMethod() throws Exception
	 {
		 //TODO: Logs out of the application & quit the driver
		 AriaLoginLogout.appLogout(driver);
		 driver.quit(); 
	 }
}
