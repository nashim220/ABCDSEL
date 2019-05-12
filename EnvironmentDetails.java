/* 
==============================================================
 Author     		:	Umesh Kute
 Class Name		 	: 	EnvironmentDetails
 Purpose     		: 	Declaring the environment variables that will be used for WordPress application.
 Date       		:	02/06/2015
 Version Information:	Version1.0
 PreCondition 		:	None
 Test Steps 		:	1. Enable the appropriate values for application URL and browser type for application
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
 #======================================================================
 */

package utility;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.text.SimpleDateFormat;

import org.openqa.selenium.WebDriver;


import utility.Utils;
import org.openqa.selenium.WebDriver;
//import org.apache.log4j.xml.DOMConfigurator;
//import org.openqa.selenium.WebDriver;


/*
This class contains the declaration for all environment variables used
@version: 1.0
@author: Umesh Kute

*/


public class EnvironmentDetails

{

	
	// ****** Environments details ******
	// Provide your Web application URL
	public static final String URL = "https://secure.future.stage.ariasystems.net/ui/app.php/";
//	public static final String URL = "https://secure.current.stage.ariasystems.net/ui/app.php/";
//	public static final String URL = "https://secure.ariasystems.net/ui/app.php/";
	// This parameter is to configure detaultbrowser for application
	// If no value is specified in variable browser, then default browser will be used. `
	
	//	public static final String defaultbrowser = "firefox";
	//public static final String browser  = "firefox";
	public static final String browser = "Chrome";
	//public static final String browser = "IE";
	// public static final String browser = "Headless";
	// public static final String browser = "Grid";
	

	// Drivers details
	public static final String Path_ChromDriver = new File("Brwser_Driverssrc").getAbsolutePath() + "\\chromedriver.exe";
	public static final String Path_IeDriver = new File("Brwser_Driverssrc").getAbsolutePath() + "\\IEDriverServer.exe";
	public static final String Path_FirefoxDriver = "";

	// This parameter refers to the path where screenshot of page,
	// during the test case execution is taken.  
	public static SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy-hhmm");//("ddMMyyyy-hhmmss")
	public static Date curDate = new Date();
	public static String strDate = sdf.format(curDate);
	public static String Path_ScreenShot = new File("Screenshots")
			.getAbsolutePath() + "/Screenshots"+ "_" +strDate + "/";
	
	//Constants read from ExecEnv Sheet
	//Updated by Namrata Akarte on 14th Jul 2015
	//This will store the values of parameters in ExecEnv Sheet of TestData.xls and below values are CONSTANTS
	Utils objExecEnv = new Utils();
	String dataSheetPath = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "src" + System.getProperty("file.separator") + "testData"+ System.getProperty("file.separator") + "TestData.xlsx";
	Map<String, String> map_ExecEnv = objExecEnv.readexcel_ExecEnvironment(dataSheetPath,"ExecEnv");
	public final String STRUSERNAME = map_ExecEnv.get("InstUserName");
	public final String STRPASSWORD = map_ExecEnv.get("InstPassWord");
	public final String STRURL= map_ExecEnv.get("InstanceURL");

	public String getUsername()
	{
		return this.STRUSERNAME;
	}
	

}