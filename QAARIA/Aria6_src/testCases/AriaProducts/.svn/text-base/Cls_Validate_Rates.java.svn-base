/*
 Author     		:	Madhavi JN
 Class Name			: 	Cls_Validate_Rates  
 Purpose     		: 	Purpose of this file is :
						1. To validate all the different rates present in ARIA
												
 Date       		:	09/24/2015
 Version Information:	Version 1.0
 
 Jira #				:	QAABE-273
 
 PreCondition 		:	1. Login with valid credentials.
 						2. Data to be used should be populated in "TestCaseDetails" and "Rates" worksheets for excel "TestData.xlsx" 								 

 Test Steps 		:	1. Login using valid credentials
 						2. Navigate to Products->Plans->click on plannumber(14709)->click on Rates Tab
 						3.All the rates related to different currecies will appear
 						6.Validate all the rates(USD,GBP,CAN,EUR,KRONA) with Base Excel
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AriaProducts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.ProductCatalogRateSheets;
import utility.*;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_Validate_Rates extends VerificationMethods
{
	
public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_Validate_Rates";

		@BeforeClass
		 public static void beforeMethod()throws Exception
		 {	
			DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
			System.setProperty("webdriver.chrome.driver",chromeDriverPath);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			EnvironmentDetails objEnv = new EnvironmentDetails();
			Log.startTestCase("Login to application");
			AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
		 }
	
		@Test(dataProvider="objectTestData", description="Validate_Rates")
	    public void ValidateSEKRates(String strAccNum,String strplanNum) throws Exception
	    {
	    	Log.startTestCase("Cls_Validate_Rates");
		    Log.info("the plan number is "+strplanNum);
		    
		    try
			{
		      //Clicking on link products
		    	driver.findElement(ProductCatalogRateSheets.fn_clickProductsLeftNav()).click();
			    Thread.sleep(500);
			  //Clicking on link plans
			    driver.findElement(ProductCatalogRateSheets.fn_clickPlansLeftNav()).click();
			    Thread.sleep(15000);
			   //Clicking on the Plans table
			    WebElement Webtable = driver.findElement(By.id("DataTables_Table_0"));
				// Now get all the TR elements from the table
				List<WebElement> TotalRowCount = Webtable.findElements(By.tagName("tr"));
				// And iterate over them, getting the cells
				int RowIndex=0;
				Log.info("row size for plans table "+TotalRowCount.size());
				for (WebElement rowElement: TotalRowCount) 
				{
						List<WebElement> TotalColumnCount=rowElement.findElements(By.xpath("td"));					
						int ColumnIndex=0;					
						for(WebElement colElement:TotalColumnCount)
						{
							if (colElement.getText().contains(strplanNum))
							{
								colElement.click();
								Log.info("the requested plan number is found"+strplanNum);
							}
						
						ColumnIndex=ColumnIndex+1;
						}
				RowIndex=RowIndex+1;  
				}
				
			 Thread.sleep(50000);
			 //Click on Rates tab
			// conditional wait for the Rates page to be displayed.
			Log.info("Waiting to find the Rates tab.");
			driver.findElement(ProductCatalogRateSheets.fn_clickRatesTab()).click();
		    Thread.sleep(50000);	
			
			//TODO: Read the SEK Rates  file for data comparison and verification.
			String strRatesFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
					 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
					 + "TestData.xlsx";
			System.out.println("Document Path : "+strRatesFilePath);
			File fileRates = new File(strRatesFilePath);
			
			//TODO : Read Test Excel
			ArrayList<String> arrlistFileContents = ReadFiles.readExcelFileAsArray(fileRates, "Rates");
			
			//Loop for checking all the 5 currencies
			for (int Counter=1;Counter<=5;Counter++)
			{
			
			//initializing internal Loop variables
			int intRowtraverser=0;
			int col=0;
			int j=0;
			
			//for combining the Xpath for Expanding the Currencies Image
            String strImgXpath1   ="//*[@id=\"planRateSchedules\"]/div[4]/div[";
            String strImgXpath2   ="]/h2/a/img";	
            
			//for combining the Xpath for fetching the Rate values from application
            String strUSDXpath1   ="plan_newRateSchedules_0_newRateScheduleRates_";
            String strGBPXpath1   ="plan_newRateSchedules_1_newRateScheduleRates_";
            String strCANpath1    ="plan_newRateSchedules_2_newRateScheduleRates_";
            String strEUROXpath1  ="plan_newRateSchedules_3_newRateScheduleRates_"; 
            String strKRONAXpath1 ="plan_newRateSchedules_4_newRateScheduleRates_";
            String strXpath2      ="_ratePerUnit";
            
            //For fetching the table Xpath of all the currencies
            String strtblXpath1 = "//*[@id=\"planRateSchedules\"]/div[4]/div[";
            String strtblXpath2 = strtblXpath1+Counter+"]/div/div[2]/div[5]/div/table";
            
            
            //Fetching the Rates table to read the pricing components
			WebElement Webtable1 = driver.findElement(By.xpath(strtblXpath2));
			//Fetch the rows of the table
			List<WebElement> TotalRowCount1 = Webtable1.findElements(By.tagName("tr"));

    		for(WebElement rows : TotalRowCount1)
    		{  	
    			//checking the condition of containing Workplace as value and considering as valid visible row
    			if (rows.getText().contains("Workplace"))
    			{	
					List<WebElement> colElement = rows.findElements(By.tagName("td"));		
	    			
					for(WebElement columns :colElement)
					{		
			
							intRowtraverser = j;
							//Computing the Final Xpaths of all the currencies
							String strFinalUSDXpath  =strUSDXpath1+col+strXpath2;
							String strFinalGBPXpath  =strGBPXpath1+col+strXpath2;
							String strFinalCANXpath  =strCANpath1+col+strXpath2;
							String strFinalEUROXpath =strEUROXpath1+col+strXpath2;
				            String strFinalKRONAXpath=strKRONAXpath1+col+strXpath2;
				            
							String strPricingCompnt=null;
					        //Fetching the pricing component values from Rates page
								strPricingCompnt = columns.getText().toString().trim();
								if (arrlistFileContents.get(j).trim().equalsIgnoreCase(strPricingCompnt))
								{
									Log.info("Match Found for PricingComponent : "+strPricingCompnt);
									Log.info("Application Value for PricingComponent : "+strPricingCompnt);
									Log.info("Excel Data Value for PricingComponent : "+arrlistFileContents.get(j));
									Log.info("-----------------------------------------------------------------------------------");
								}
								else
								{
									System.out.println("Mismatch for PricingComponent : "+strPricingCompnt);
									Log.info("Match Not Found for PricingComponent : "+strPricingCompnt);
									Log.info("Application Value for PricingComponent : "+strPricingCompnt);
									Log.info("Excel Data Value for PricingComponent : "+arrlistFileContents.get(j));
									Log.info("-----------------------------------------------------------------------------------");
								}
														 
								  
								 if (Counter==1)
								 {							 			     
									    //Fetching the USD Rates values from Rates page										
									    String strUSDRatevalue = driver.findElement(By.id(strFinalUSDXpath)).getAttribute("value").toString();	
									    //Validation of USD rates
										if (arrlistFileContents.get(intRowtraverser+1).trim().equalsIgnoreCase(strUSDRatevalue))
										{
											Log.info("--------Result for USD Currency------------------");
											Log.info("Match Found for USD RateValue : "+strUSDRatevalue);
											Log.info("Application USD RateValue : "+strUSDRatevalue);
											Log.info("Excel Data for USD RateValue : "+arrlistFileContents.get(intRowtraverser+1));
											Log.info("-----------------------------------------------------------------------------------");
										}
										else
										{
											Log.info("--------Result for USD Currency------------------");
											Log.info("Match Not Found for USD RateValue : : "+strUSDRatevalue);
											Log.info("Application USD RateValue : "+strUSDRatevalue);
											Log.info("Excel Data for USD RateValue : "+arrlistFileContents.get(intRowtraverser+1));
											Log.info("-----------------------------------------------------------------------------------");
									    }
								 }	
								 else if (Counter==2)
								 { 
									    
									    //Fetching the GBP Rates values from Rates page										
									    String strGBPRatevalue = driver.findElement(By.id(strFinalGBPXpath)).getAttribute("value").toString();	
									    //Validation of GBP rates
										if (arrlistFileContents.get(intRowtraverser+2).trim().equalsIgnoreCase(strGBPRatevalue))
										{
											Log.info("--------Result for GBP Currency------------------");
											Log.info("Match Found for GBP RateValue : "+strGBPRatevalue);
											Log.info("Application GBP RateValue : "+strGBPRatevalue);
											Log.info("Excel Data for GBP RateValue : "+arrlistFileContents.get(intRowtraverser+2));
											Log.info("-----------------------------------------------------------------------------------");
										}
										else
										{
											Log.info("--------Result for GBP Currency------------------");
											Log.info("Match Not Found for GBP RateValue : : "+strGBPRatevalue);
											Log.info("Application GBP RateValue : "+strGBPRatevalue);
											Log.info("Excel Data for GBP RateValue : "+arrlistFileContents.get(intRowtraverser+2));
											Log.info("-----------------------------------------------------------------------------------");
										 }
								  }
								 else if (Counter==3)
								 { 
									    //Fetching the CAN Rates values from Rates page										
									    String strCANRatevalue  = driver.findElement(By.id(strFinalCANXpath)).getAttribute("value").toString();	
									    //Validation of CAN rates
										if (arrlistFileContents.get(intRowtraverser+3).trim().equalsIgnoreCase(strCANRatevalue))
										{
											Log.info("--------Result for CAN Currency------------------");
											Log.info("Match Found for CAN RateValue : "+strCANRatevalue);
											Log.info("Application CAN RateValue : "+strCANRatevalue);
											Log.info("Excel Data for CAN RateValue : "+arrlistFileContents.get(intRowtraverser+3));
											Log.info("-----------------------------------------------------------------------------------");
										}
										else
										{
											Log.info("--------Result for CAN Currency------------------");
											Log.info("Match Not Found for CAN RateValue : : "+strCANRatevalue);
											Log.info("Application CAN RateValue : "+strCANRatevalue);
											Log.info("Excel Data for CAN RateValue : "+arrlistFileContents.get(intRowtraverser+3));
											Log.info("-----------------------------------------------------------------------------------");
										 }
								  }
								 else if (Counter==4)
								 { 
									    
									    //Fetching the EURO Rates values from Rates page										
									    String strEURRatevalue  = driver.findElement(By.id(strFinalEUROXpath)).getAttribute("value").toString();	
									    //Validation of EUR rates
										if (arrlistFileContents.get(intRowtraverser+4).trim().equalsIgnoreCase(strEURRatevalue ))
										{
											Log.info("--------Result for EUR Currency------------------");
											Log.info("Match Found for EUR RateValue : "+strEURRatevalue);
											Log.info("Application EUR RateValue : "+strEURRatevalue);
											Log.info("Excel Data for EUR RateValue : "+arrlistFileContents.get(intRowtraverser+4));
											Log.info("-----------------------------------------------------------------------------------");
										}
										else
										{
											Log.info("--------Result for EUR Currency------------------");
											Log.info("Match Not Found for EUR RateValue : : "+strEURRatevalue);
											Log.info("Application EUR RateValue : "+strEURRatevalue);
											Log.info("Excel Data for EUR RateValue : "+arrlistFileContents.get(intRowtraverser+4));
											Log.info("-----------------------------------------------------------------------------------");
										 }
									}
								  else if (Counter==5)
								  { 
									    
									    //Fetching the Krona Rates values from Rates page										
									    String strKronaRatevalue = driver.findElement(By.id(strFinalKRONAXpath)).getAttribute("value").toString();	
									    //Validation of krona rates
										if (arrlistFileContents.get(intRowtraverser+5).trim().equalsIgnoreCase(strKronaRatevalue))
										{
											Log.info("--------Result for KRONA Currency------------------");
											Log.info("Match Found for Krona RateValue : "+strKronaRatevalue);
											Log.info("Application Krona RateValue : "+strKronaRatevalue);
											Log.info("Excel Data for Krona RateValue : "+arrlistFileContents.get(intRowtraverser+5));
											Log.info("-----------------------------------------------------------------------------------");
										}
										else
										{
											Log.info("--------Result for KRONA Currency------------------");
											Log.info("Match Not Found for Krona RateValue : : "+strKronaRatevalue);
											Log.info("Application Krona RateValue : "+strKronaRatevalue);
											Log.info("Excel Data for Krona RateValue : "+arrlistFileContents.get(intRowtraverser+5));
											Log.info("-----------------------------------------------------------------------------------");
										 }
							}	
								 
		                    //Incrementing the array counter to fetch the values from storde array
							j+=6;	
							//Counter incremented for Looping the Final Xpath
							col++;	
					
					      }	
				
    		        	}
    		        }
    		
		
				  if (Counter!=5)
				  {
			      String strImgFinalXpath = strImgXpath1+(Counter+1)+strImgXpath2;
			      //Click on Exapnd Image to move to different currency
			      driver.findElement(By.xpath(strImgFinalXpath)).click();
			      Thread.sleep(2000);
				  }    		   		
    		
    		     }					
			
			}   
			catch (Exception exception)
			{
				Log.error("There is some error in Validation of Rates");
			}
			
			}
		
	    @DataProvider(name = "objectTestData")
	    public Object[][] data() throws DataDrivenFrameworkException
	    {
	        Utils objUtils=new Utils();
	        return objUtils.data1("TestCaseDetails", "Validate_Rates");
	    }
	    
		
		@AfterTest
		public void afterMethod() throws Exception
		 {
			 //Logs out of the application
			 AriaLoginLogout.appLogout(driver); 
			 //Quitting the driver
			 driver.quit(); 
		 }
 }
