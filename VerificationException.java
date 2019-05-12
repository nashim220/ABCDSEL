/* 
==============================================================
 Author     		:	Umesh Kute
 Class Name		 	: 	VerificationException
 Purpose     		: 	To handle verification exception
 Date       		:	02/16/2015
 Version Information:	Version1.0
 PreCondition 		:	None
 Test Steps 		:	None
 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services - 
 						All Rights Reserved 
 #======================================================================
 */

package utility;

import java.util.List;

/*
This class is used to handle the verification exception during execution of test scripts.
@version: 1.0
@author: Umesh Kute
*/
public class VerificationException extends Exception {
 

	public VerificationException(List<Throwable> verificationFailures) {
	
	
		super(verificationFailures.toString());
		
		
		// TODO Auto-generated constructor stub
	}
}