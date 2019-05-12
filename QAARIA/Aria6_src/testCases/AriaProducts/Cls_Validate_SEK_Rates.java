package testCases.AriaProducts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ProductCatalogRateSheets;
import utility.*;
import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_Validate_SEK_Rates extends VerificationMethods
{
	
public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_Validate_SEK_Rates";

		@BeforeClass
		 public static void beforeMethod()throws Exception
		 {	
			DOMConfigurator.configure("log4j.xml");	// Initialize the Logger.
			driver = Utils.fn_SetChromeDriverProperties();
			System.setProperty("webdriver.chrome.driver",chromeDriverPath);
			//driver = new ChromeDriver();
			EnvironmentDetails objEnv = new EnvironmentDetails();
			Log.startTestCase("Login to application");
			AriaLoginLogout.appLogin(driver, objEnv.STRUSERNAME, objEnv.STRPASSWORD);	
		 }
	
		@Test(dataProvider="objectTestData", description="Validate_SEK_Rates")
	    public void ValidateSEKRates(String strAccNum,String strplanNum) throws Exception
	    {
	    	
			WebDriverWait webWait;
			webWait = new WebDriverWait(driver,1000);
		    Log.startTestCase("Cls_Validate_SEK_Rates");
		    Log.info("the plan number is "+strplanNum);
		    
		    try
			{
		    	//Clicking on link products
			    driver.findElement(By.linkText("Products")).click();
			    Thread.sleep(500);
			  //Clicking on link plans
			    driver.findElement(By.linkText("Plans")).click();
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
			 driver.findElement(By.linkText("Rates")).click();
			 
			 Thread.sleep(50000);	
			
			//TODO: Read the SEK Rates  file for data comparison and verification.
			String strSEKRatesFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + 
					 "src" + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator") 
					 + "TestData.xlsx";
			System.out.println("Document Path : "+strSEKRatesFilePath);
			File fileSEKRates = new File(strSEKRatesFilePath);
			
			//TODO : Read Test Excel
			ArrayList<String> arrlistFileContents = ReadFiles.readExcelFileAsArray(fileSEKRates, "SwedishRates");
			
			int intRowtraverser=0;
			int col=0;
			//for combinin the Xpath for fetching the values from application
            String strXpath1 ="plan_newRateSchedules_4_newRateScheduleRates_";
            String strXpath2 = "_ratePerUnit";
            int j=0;
            //Fetching the Rates table to read the pricing components
			WebElement Webtable1 = driver.findElement(By.id("plantable"));
			//Fetch the rows of the table
			List<WebElement> TotalRowCount1 = Webtable1.findElements(By.tagName("tr"));

    		for(WebElement rows : TotalRowCount1)
    		{
	
    			List<WebElement> colElement = rows.findElements(By.tagName("td"));
 		
				for(WebElement columns :colElement)
				{		
  					
					if (columns.getText().contains("Workplace"))	
  						
  					{
						
							intRowtraverser = j;
				            String strFinalXpath =strXpath1+col+strXpath2;			
							String strPricingCompnt=null;
							        //Fetching the pricing component values from Rates page
										strPricingCompnt = columns.getText().toString().trim();
										Log.info("the row values are"+strPricingCompnt);
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
										
									//Fetching the Swedish Rates values from Rates page	
									  String strNewvalue = "null";
									  String strRatevalue = driver.findElement(By.id(strFinalXpath)).getAttribute("value").toString();	
									  int stringlength = strRatevalue.length();
									  if (stringlength>1)
									  {
										  String[] splitvalue = strRatevalue.split(",");
										  strNewvalue = "0"+"."+splitvalue[1];
									  }
									  else
									  {
									     strNewvalue = strRatevalue;
										  
									  }								
									
										if (arrlistFileContents.get(intRowtraverser+1).trim().equalsIgnoreCase(strNewvalue))
										{
											Log.info("Match Found for Swedish RateValue : "+strNewvalue);
											Log.info("Application Swedish RateValue : "+strRatevalue);
											Log.info("Excel Data for Swedish RateValue : "+arrlistFileContents.get(intRowtraverser+1));
											Log.info("-----------------------------------------------------------------------------------");
										}
										else
										{
											Log.info("Match Not Found for Swedish RateValue : : "+strNewvalue);
											Log.info("Application Swedish RateValue : "+strRatevalue);
											Log.info("Excel Data for Swedish RateValue : "+arrlistFileContents.get(intRowtraverser+1));
											Log.info("-----------------------------------------------------------------------------------");
										 }								  
	                    //Incrementing the array counter to fetch the values from storde array
						j+=2;	
						//Counter incremented for Looping the Final Xpath
						col++;	
						
  					   }
  					
				   }
				
    		}
								


			}   
			catch (Exception exception)
			{
				Log.error("There is some error in Validation of SEK Rates");
			}
			
			}
		
	    @DataProvider(name = "objectTestData")
	    public Object[][] data() throws DataDrivenFrameworkException
	    {
	        Utils objUtils=new Utils();
	        return objUtils.data1("TestCaseDetails", "Validate_SEK_Rates");
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
