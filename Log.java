/* 
==============================================================
 Author     		:	Umesh Kute
 Class Name		 	: 	Log
 Purpose     		: 	To capture the common functions for all types of log during reporting
 Date       		:	02/06/2015
 Version Information:	Version1.0
 PreCondition 		:	None
 Test Steps 		:	None
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
 #======================================================================
 */

package utility;
import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Reporter;


/*
This class contains the reusable function for different types of log levels 
(e.g. INFO, DEBUG, ERROR, FATAL, WARN) which can be used for reporting purpose.
@version: 1.0
@author: Umesh Kute
*/
public class Log {
	 static Logger Log = Logger.getLogger(Log.class.getName()); 
// Initialize Log4j logs
//	public static void initLogConfig()
//	{
//		String logConfig = new File("Config").getAbsolutePath() + "\\log4j.xml";
//		System.out.println("Log4j xml path is: "+logConfig);
//		DOMConfigurator.configure(logConfig);
//		
//	}
	
// This is to print log at the beginning of the test case;
// as we usually run so many test cases as a test suite
public static void startTestCase(String sTestCaseName){
//    Log.info("****************************************************************************************");
    Log.info("$$$ "+sTestCaseName+ ": STARTED $$$");
//    Log.info("****************************************************************************************");
    }
 
    //This is to print log once the test case is completed
public static void endTestCase(String sTestCaseName){

	Log.info("$$$ "+sTestCaseName+" COMPLETED $$$");

    }
 
    // Need to create these methods, so that they can be called  
public static void info(String message) {
        System.out.println(message);
        Log.info(message);
        Reporter.log(message);
        }
public static void warn(String message) {
    Log.warn(message);
    System.out.println(message);
    }
public static void error(String message) {
    Log.error(message);
    System.out.println(message);
    }
public static void fatal(String message) {
    Log.fatal(message);
    System.out.println(message);
    }
public static void debug(String message) {
    Log.debug(message);
    System.out.println(message);
    }
}