/* 
==============================================================
 Author     		:	Umesh Kute
 Class Name		 	: 	VerificationMethods
 Purpose     		: 	To handle all types of assertions
 Date       		:	02/16/2015
 Version Information:	Version1.0
 PreCondition 		:	None
 Test Steps 		:	None
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
 #======================================================================
 */

package utility;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

/*
This class is used as a common library for all types of assertions.
@version: 1.0
@author: Umesh Kute
*/
public class VerificationMethods {

	public static String chromeDriverPath = new File("Brwser_Driverssrc").getAbsolutePath() + "\\chromedriver.exe";
	public static String BASE_TESTDATA_PATH = new File("src\\testData\\").getAbsolutePath();
	private static Map<ITestResult, List<Throwable>> verificationFailuresMap = new HashMap<ITestResult, List<Throwable>>();

    public static void assertTrue(boolean condition) {
    	Assert.assertTrue(condition);
    }
    
    public static void assertTrue(boolean condition, String message) {
    	Assert.assertTrue(condition, message);
    }
    
    public static void assertFalse(boolean condition) {
    	Assert.assertFalse(condition);
    }
    
    public static void assertFalse(boolean condition, String message) {
    	Assert.assertFalse(condition, message);
    }
    
    public static void assertEquals(boolean actual, boolean expected) {
    	Assert.assertEquals(actual, expected);
    }
    
    public static void assertEquals(Object actual, Object expected) {
    	Assert.assertEquals(actual, expected);
    }
    
    public static void assertEquals(Object[] actual, Object[] expected) {
    	Assert.assertEquals(actual, expected);
    }
    
    public static void assertEquals(Object actual, Object expected, String message) {
    	Assert.assertEquals(actual, expected, message);
    }
    
    public static void verifyTrue(boolean condition) {
    	try {
    		assertTrue(condition);
    	} catch(Throwable e) {
    		addVerificationFailure(e);
    	}
    }
    
    public static void verifyTrue(boolean condition, String message) {
    	try {
    		assertTrue(condition, message);
    	} catch(Throwable e) {
    		addVerificationFailure(e);
    	}
    }
    
    public static void verifyFalse(boolean condition) {
    	try {
    		assertFalse(condition);
		} catch(Throwable e) {
    		addVerificationFailure(e);
		}
    }
    
    public static void verifyFalse(boolean condition, String message) {
    	try {
    		assertFalse(condition, message);
    	} catch(Throwable e) {
    		addVerificationFailure(e);
    	}
    }
    
    public static void verifyEquals(boolean actual, boolean expected) {
    	try {
    		assertEquals(actual, expected);
		} catch(Throwable e) {
    		addVerificationFailure(e);
		}
    }

    public static void verifyEquals(Object actual, Object expected) {
    	try {
    		assertEquals(actual, expected);
		} catch(Throwable e) {
    		addVerificationFailure(e);
		}
    }
    
    public static void verifyEquals(Object[] actual, Object[] expected) {
    	try {
    		assertEquals(actual, expected);
		} catch(Throwable e) {
    		addVerificationFailure(e);
		}
    }

    public static void fail(String message) {
    	Assert.fail(message);
    }
    
	public static List<Throwable> getVerificationFailures() {
		List<Throwable> verificationFailures = verificationFailuresMap.get(Reporter.getCurrentTestResult());
		return verificationFailures == null ? new ArrayList<Throwable>() : verificationFailures;
	}
	
	protected static void addVerificationFailure(Throwable e) {
		List<Throwable> verificationFailures = getVerificationFailures();
		verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
		verificationFailures.add(e);
	}

}
