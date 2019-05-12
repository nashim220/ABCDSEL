/*
 Author     		:	Madhavi JN
 Class Name			: 	Cls_ValidateAccountDetailsAPI  
 Purpose     		: 	Purpose of this file is :
						1. To validate all the SuppFeilds present in ARIA
												
 Date       		:	10/07/2015
 Version Information:	Version 1.0
 
 Jira #				:	QAABE-372
 
 PreCondition 		:	1. Login with valid credentials.
 						2. Data to be used should be populated in "TestCaseDetails" and "ValidateAccountAPI" worksheets for excel "TestData.xlsx" 								 

 Test Steps 		:	1.Login using valid credentials
 						2.Navigate to Accounts->Search account->client defined Feilds
 						3.Fire API ->get_acct_supp_fields and get the ouput in Jsonlist form 
 						4.compare the JSON list with ARIA GUI and display the Output
 
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
*/

package testCases.AccountTestCases;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.xml.DOMConfigurator;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.ApiHandler;
import utility.EnvironmentDetails;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;
import appModules.AccountFunctions;
import appModules.AriaLoginLogout;
import appModules.Cls_ChangeDeleteClientDefinedFieldActns;
import atu.ddf.exceptions.DataDrivenFrameworkException;

public class Cls_ValidateAccountDetailsAPI extends VerificationMethods
{
	
public static WebDriver driver;
	
	public static String strTestCaseName = "Cls_ValidateAccountDetailsAPI";

		@BeforeTest
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
	
		@Test(dataProvider="objectTestData", description="ValidateAccountAPI")
	    public void ValidateAccountAPI(String stracctnum1,String strformat,String stracctnum2) throws Exception
	    {
			
		  try
		    {
			
			WebDriverWait webWait;
			webWait = new WebDriverWait(driver,1000);
	    	Log.startTestCase("Cls_ValidateAccountDetailsAPI");
		    Cls_ChangeDeleteClientDefinedFieldActns objClientDefAct = new Cls_ChangeDeleteClientDefinedFieldActns();
		    objClientDefAct.AccountSearch(driver, webWait, stracctnum1);
		    Thread.sleep(2500);
		    HashMap<String, String> hashMapApplAcctOverview= new HashMap<String, String>();
		    HashMap<String, String> hashMapAPIAcctOverview= new HashMap<String, String>();
		    WebElement table = driver.findElement(By.className("acct-data-table"));
			String[] arrayApplOvwelements = new String[80];
		    List<WebElement>rows = table.findElements(By.tagName("tr"));
		    Log.info("rows size is "+rows.size());
		    int inti=0;
		    for (WebElement row : rows)
		    {
				List<WebElement>cols = row.findElements(By.tagName("td"));
			
				for (WebElement col:cols)
				{
			         
					arrayApplOvwelements[inti]=col.getText();
					inti++;
				}
		    }
		    
	       //Pushing the API Feildname and FeildValues in Array to HasMap 
           for (int j=0;j<arrayApplOvwelements.length;j+=2 )
           {
        	   if (arrayApplOvwelements[j+1]!=null)
        	   {
        	   hashMapApplAcctOverview.put(arrayApplOvwelements[j],arrayApplOvwelements[j+1]);
        	   }
           }

		   //Displaying Stored Application FeildName and FeildValue in HashMap
 		   Set<Entry<String,String>> hashSetApplAcctOverview=hashMapApplAcctOverview.entrySet(); 
 		   for(Entry entry:hashSetApplAcctOverview )
 		   {
 	           System.out.println("application Overview values :: "+"Key="+entry.getKey()+", Value="+entry.getValue());
 	       } 	  
 		   //Firing the get_acct_details_all API
    	   String strURL = AccountFunctions.getFullUrl("get_acct_details_all");
    	   ApiHandler objapihandler = new ApiHandler(); 
    	   String strFinalURL = strURL+"&acct_no="+stracctnum1+"&output_format="+strformat;
    	   List<JSONObject> Jsonlist = objapihandler.makeSimplePostCall(strFinalURL);
    	   //Displaying the JSonList Returned
    	   Log.info("the list returned is "+Jsonlist);
    	   String strCombined =Jsonlist.toString();
    	   Log.info("the combined string is "+strCombined);
       	   String[] split = strCombined.split("[{:,}]");
    	   String[] arrayAPIAcctOverviewelements = new String[294];
    	   int m=0;
    	   boolean blnIspresent = false;
    	   
    	   for(int l=0; l<split.length; l++) 
    	   {
    		   if (!split[l].contains("["))
    			 {
	  		     		  
	    			 if (split[l].contains("]"))
		       		  {
	    				  blnIspresent = true;
	    				  break;
		       		  }
    			       	    
			          if (!blnIspresent==true)
					  { 
			        	 //Log.info("the stored array elements are "+split[i]);
			        	   arrayAPIAcctOverviewelements[m]=split[l];
			        	   m++;		
					   }
    			 }
    	   }
  	   	   
           //Pushing the API Feildname and FeildValues in Array to HasMap 
          for (int n=0;n<arrayAPIAcctOverviewelements.length;n+=2 )
           {      	
        	  if (!(arrayAPIAcctOverviewelements[n+1].equalsIgnoreCase("null")))
        	  {
        	  hashMapAPIAcctOverview.put(arrayAPIAcctOverviewelements[n],arrayAPIAcctOverviewelements[n+1]);
        	  }
           }
           
           //Displaying Stored API FeildName and FeildValue in HashMap
		   Set<Entry<String,String>> hashSetAPIAcctOverview=hashMapAPIAcctOverview.entrySet(); 
		   for(Entry entry:hashSetAPIAcctOverview )
		   {
	            System.out.println("APIAcctOverview values"+"Key="+entry.getKey()+", Value="+entry.getValue());
	       } 
		   
		   Log.info("============================================================================");
		   Log.info("Result of get_acct_details_all  API");
		   Log.info("============================================================================");
		   
		   //Comparision of HashMap Values of Application and API
		   for (Entry<String, String> entry: hashMapApplAcctOverview.entrySet())
		   {
			    if ((hashMapAPIAcctOverview.entrySet()).toString().contains(entry.getValue()))
			    {
                    Log.info("The HashMap Key and Value of ApplicationOVVW are matching with HasMapAPI :: "+"Key="+entry.getKey()+"::"+"Value="+entry.getValue());
                    Log.info("=========================================================================");
			    }
			    
			    else
			    {
			    	Log.info("The HashMap Key and Value of ApplicationOVVW are not matching with HasMapAPI :: "+"Key="+entry.getKey()+"::"+"Value="+entry.getValue());
			    	Log.info("----------------------------------------------------------------------------");
			    }
			}


		    HashMap<String, String> hashMapSuppApplication= new HashMap<String, String>();
		    HashMap<String, String> hashMapSuppAPI= new HashMap<String, String>();
		    driver.findElement(By.partialLinkText("Client-Defined Fields")).click();  
		    Thread.sleep(500);
		    //TODO : Identify the application test data table
			WebElement table1 = driver.findElement(By.tagName("table"));
				
			WebElement tableCustDefinedFields = table1.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[2]/div[3]/div/div/div[4]/table"));
		  
			List<WebElement>row1 = tableCustDefinedFields.findElements(By.tagName("tr"));

			String strFieldName =null;
			String strFieldValue =null;
			
			for (WebElement row : row1)
			//TODO : Read run time field names and values from application and store it in HashMap
			{
			 
				Thread.sleep(1000);
				List<WebElement>cols = row.findElements(By.tagName("td"));
				int intcol =0;
				for (WebElement col:cols)
				{
					intcol++;
					if (intcol == 1)
					{
						 strFieldName = col.getText();
					}
					else if (intcol == 2)
					{
						  strFieldValue = col.getText();
					}
					//Storing Application FeildName and FeildValue in HashMap
					hashMapSuppApplication.put(strFieldName, strFieldValue);
				}

			 }
			//Displaying Stored Application FeildName and FeildValue in HashMap
		   Set<Entry<String,String>> hashSetSuppApplication=hashMapSuppApplication.entrySet(); 
		   for(Entry entry:hashSetSuppApplication )
		   {
	            System.out.println("application values"+"Key="+entry.getKey()+", Value="+entry.getValue());
	        } 
		   //Firing the get_acct_supp_fields API
    	   String strURL1 = AccountFunctions.getFullUrl("get_acct_supp_fields");
    	   ApiHandler objapihandler1 = new ApiHandler(); 
    	   String strFinalURL1 = strURL1+"&acct_no="+stracctnum1+"&output_format="+strformat;
    	   List<JSONObject> Jsonlist1 = objapihandler1.makeSimplePostCall(strFinalURL1);
    	   //Displaying the JSonList Returned
    	   Log.info("the list returned is "+Jsonlist1);
    	   String strCombined1 =Jsonlist1.toString();
    	   Log.info("the combined string is "+strCombined1);
    	   String[] split1 = strCombined1.split("[{,:}]");
    	   boolean blnflag= false;
    	   String[] arrayAPIelements = new String[40];
    	   int j=0;
    	   //HashMap<String> hashMap= new HashMap<String>();
    	   for(int i=0; i<split1.length; i++) 
    	   {
	    		   if (!split1[i].isEmpty() && !split1[i].contains("["))
	    		   {      		   
			    		   if (!split1[i].contains("field_") && !split1[i].contains("supp_fields"))
			    		   {
			    			   
			    		 		  if (split1[i].contains("]"))
					       		  {
					       			blnflag = true;
					       		  }
			    			       	    
		    			           if (blnflag==false)
		    					   {
		    			        	 //Log.info("the stored array elements are "+split[i]);
		    				         arrayAPIelements[j] = split1[i];
		   				    	     j++;
		    					   }  			           		      
		    			   }
	    		   }   
    		   
    	   }
       //Pushing the API Feildname and FeildValues in Array to HasMap 
       for (int k=0;k<arrayAPIelements.length;k+=2 )
       {
    	   hashMapSuppAPI.put(arrayAPIelements[k],arrayAPIelements[k+1]);
       }
       
       //Displaying Stored API FeildName and FeildValue in HashMap
	   Set<Entry<String,String>> hashSetSuppAPI=hashMapSuppAPI.entrySet(); 
	   for(Entry entry:hashSetSuppAPI)
	   {
            System.out.println("API values"+"Key="+entry.getKey()+", Value="+entry.getValue());
       } 
	   
	   Log.info("============================================================================");
	   Log.info("Result of get_acct_supp_fields API");
	   Log.info("============================================================================");
	   
	   //Comparision of HashMap Values of Application and API
	   for (Entry<String, String> entry: hashMapSuppApplication.entrySet())
	   {
		    if (hashMapSuppAPI.entrySet().toString().contains(entry.getKey()) && hashMapSuppAPI.entrySet().toString().contains(entry.getValue()))
		    {
                Log.info("The HashMap Key and Value of Application are matching with HasMapAPI :: "+"Key="+entry.getKey()+"::"+"Value="+entry.getValue());
                Log.info("=========================================================================");
		    }
		    
		    else
		    {
		    	Log.info("The HashMap Key and Value of Application are not matching with HasMapAPI :: "+"Key="+entry.getKey()+"::"+"Value="+entry.getValue());
		    	Log.info("============================================================================");
		    }
		}
	   
	   
       }
		catch (Exception exception)
		{
			Log.error("There is some error in Validation of AccountDetailsAPI");
		}
		
	}
	
    @DataProvider(name = "objectTestData")
    public Object[][] data() throws DataDrivenFrameworkException
    {
        Utils objUtils=new Utils();
        return objUtils.data1("TestCaseDetails", "ValidateAccountAPI");
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
