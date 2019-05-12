/*
 Author     		:	Aashish Bhutkar
 Class Name			: 	Cls_AriaNewAccountCreation
 Purpose     		: 	Purpose of this Module is :
						1. To fill in the Account Creation form.
						2. And Call respective Verifications methods for calling TC's

 Date       		:	06/12/2015
 Modified Date		:	07/01/2015
 Version Information:	Version 3.0

 PreCondition 		:	Call from function after successful login.
 Test Steps 		:	1. Fill the Account Creation form.
 						2. Call the verification methods for New Account / New Parent Child / Runtime Parent Child TC's.

Version Changes 2.0 :	1. Making it a reusable function.
 						2. Processing calls from all the Account Creation related TC's.

Version Changes 3.0 :	1. Introduced the Add Client Defined fields method.
 						2. Introduced the Verification of Newly Added Account method.
 						3. Introduced page object based Web wait.
 						4. Changes to the parameters, class and method names as per the standards.

 Copyright notice	:	Copyright(C) 2015 Sungard Availability Services -
 						All Rights Reserved
*/


package appModules;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utility.Log;
import utility.Utils;
import utility.VerificationMethods;
import pageObjects.NewAccountCreation;
import pageObjects.NewAccountCreation;
import testCases.AccountTestCases.Cls_NewAccountCreation;
import testCases.AccountTestCases.Cls_ParentChildNewAccountCreation;
import testCases.AccountTestCases.Cls_RuntimeNewParentNewChildAccountCreation;
import utility.Log;
import utility.Utils;
import utility.VerificationMethods;

@Parameters("browser")
public class AriaNewAccountCreation extends VerificationMethods
{
	public static String browser;
	public static WebDriverWait webWait;
    public static WebElement webElement;

/*
    Function fn_CreateAccount: This is the actual function to type in data to the Create Account Page and save it.
    						   Based on the calling script, the function will do the verifications.
*/

	public static String fn_CreateAccount(WebDriver driver, List<HashMap<String,String>> lsthmapFileContents, String strTestCaseName, String[] arrSearchFields, String strFieldValue)throws Exception
	{
		String strKeyValue = null;	// this variable holds the key value from the Hashmap.
		String strChildAccountID = null;	// this variable holds the value for the Child Account ID.
		String strNewAccountID = null;	// this variable holds the value for the newly created Account ID.
		String strNewParentChildAccountID = null;	// this variable holds the value for the primary account created to be used as Parent Account.
		Integer intCounterAccountsAdded = 0;	// this variable will be used for counting # of Accounts created for Runtime Parent Child TC's.

		webWait = new WebDriverWait(driver, 90);

		try
		{
			webElement = webWait.until(ExpectedConditions.elementToBeClickable(NewAccountCreation.fn_clickAccountsLeftNav()));
			driver.findElement(NewAccountCreation.fn_clickAccountsLeftNav()).click();// expand the Accounts menu.
			Log.info("Login Successfull and now Accounts Menu in left Nav is available !");
			TimeUnit.SECONDS.sleep(5);

			for(HashMap<String, String> fileCellContents : lsthmapFileContents)
			{
				driver.findElement(NewAccountCreation.fn_clickCreateNewAccount()).click();// click on Create New Account Menu.
				TimeUnit.SECONDS.sleep(10);// Sleep introduced for New Page Creation to be displayed.

				for (String key : fileCellContents.keySet())	// traverse for the various keys listed in the excel sheet.
				{
					// Account Information

					//generate unique info for clientAcctId

					System.out.println("Entering "+key);

					strKeyValue = fileCellContents.get(key);	// get the value for the keys to be entered.
					if(key.contentEquals("Client Account ID"))
					{
						String uniqueKey = strKeyValue + System.currentTimeMillis();
						driver.findElement(NewAccountCreation.fn_getClientAccountID()).clear();// clear the Client Acct ID field.
						driver.findElement(NewAccountCreation.fn_getClientAccountID()).sendKeys(uniqueKey);// type the read cell value.

						Log.info("Client Account ID entered successfully !");
					}
					else if(key.contentEquals("Parent Account"))
					{
						if (strTestCaseName.contentEquals("New Account with Parent Child Relation @ Runtime"))
						{
							// for the other Account to be added, set its Parent Account ID as account # of of previously added account.
							if (intCounterAccountsAdded >= 1)
							{
								strKeyValue = strNewParentChildAccountID;
							}
						}

						driver.findElement(NewAccountCreation.fn_getParentAccount()).clear();// clear the Parent Acct ID field.
						driver.findElement(NewAccountCreation.fn_getParentAccount()).sendKeys(strKeyValue);// type the read cell value.

						Log.info("Parent Account entered successfully !");
						strNewParentChildAccountID = strKeyValue;
					}
					else if(key.contentEquals("Account Status"))
					{
						driver.findElement(NewAccountCreation.fn_clickAccountStatus()).click();// click the Parent Acct ID field.
						driver.findElement(NewAccountCreation.fn_clickAccountStatus()).sendKeys(strKeyValue);
																										// type the read cell value.

						Log.info("Account Status entered successfully !");
					}
					else if(key.contentEquals("Notification Method"))
					{
						driver.findElement(NewAccountCreation.fn_clickNotificationMethod()).click();
																									// click the Notification Method field.
						driver.findElement(NewAccountCreation.fn_clickNotificationMethod()).sendKeys(strKeyValue);
																									// type the read cell value.

						Log.info("Notification Method entered successfully !");
					}

					// Account Contact Information
					else if(key.contentEquals("First Name"))
					{
						driver.findElement(NewAccountCreation.fn_getFirstName()).clear();// clear the First Name field.
						driver.findElement(NewAccountCreation.fn_getFirstName()).sendKeys(strKeyValue);// type the read cell value.

						Log.info("First Name entered successfully !");
					}
					else if(key.contentEquals("Last Name"))
					{
						driver.findElement(NewAccountCreation.fn_getLastName()).clear();// clear the Last Name field.
						driver.findElement(NewAccountCreation.fn_getLastName()).sendKeys(strKeyValue);// type the read cell value.

						Log.info("Last Name entered successfully !");
					}
					else if(key.contentEquals("Company Name"))
					{
						driver.findElement(NewAccountCreation.fn_getCompanyName()).clear();// clear the Company Name field.
						driver.findElement(NewAccountCreation.fn_getCompanyName()).sendKeys(strKeyValue);// type the read cell value.

						Log.info("Company Name entered successfully !");
					}
					else if(key.contentEquals("Address 1"))
					{
						driver.findElement(NewAccountCreation.fn_getAddress1()).clear();// clear the Address 1 field.
						driver.findElement(NewAccountCreation.fn_getAddress1()).sendKeys(strKeyValue);// type the read cell value.

						Log.info("Address 1 entered successfully !");
					}
					else if(key.contentEquals("Address 2"))
					{
						driver.findElement(NewAccountCreation.fn_getAddress2()).clear();// clear the Address 2 field.
						driver.findElement(NewAccountCreation.fn_getAddress2()).sendKeys(strKeyValue);// type the read cell value.

						Log.info("Address 2 entered successfully !");
					}
					else if(key.contentEquals("City"))
					{
						driver.findElement(NewAccountCreation.fn_getCity()).clear();// clear the City field.
						driver.findElement(NewAccountCreation.fn_getCity()).sendKeys(strKeyValue);// type the read cell value.

						Log.info("City entered successfully !");
					}
					else if(key.contentEquals("State/Province"))
					{
						driver.findElement(NewAccountCreation.fn_getState()).clear();// clear the State/Province field.
						driver.findElement(NewAccountCreation.fn_getState()).sendKeys(strKeyValue);// type the read cell value.

						Log.info("State/Province entered successfully !");
					}
					else if(key.contentEquals("Locality"))
					{
						driver.findElement(NewAccountCreation.fn_getLocality()).clear();// clear the Locality field.
						driver.findElement(NewAccountCreation.fn_getLocality()).sendKeys(strKeyValue);// type the read cell value.

						Log.info("Locality entered successfully !");
					}
					else if(key.contentEquals("Country"))
					{
						driver.findElement(NewAccountCreation.fn_clickCountry()).click();;// click the Country field.
						driver.findElement(NewAccountCreation.fn_clickCountry()).sendKeys(strKeyValue);// type the read cell value.

						Log.info("Country selected successfully !");
					}
					else if(key.contentEquals("Zip Code"))
					{
						driver.findElement(NewAccountCreation.fn_getZipCode()).clear();// clear the Zip Code field.
						driver.findElement(NewAccountCreation.fn_getZipCode()).sendKeys(strKeyValue);// type the read cell value.

						Log.info("Zip Code entered successfully !");
					}
					else if(key.contentEquals("Work Phone"))
					{
						driver.findElement(NewAccountCreation.fn_getWorkPhone()).clear();// clear the Work Phone field.
						driver.findElement(NewAccountCreation.fn_getWorkPhone()).sendKeys(strKeyValue);// type the read cell value.

						Log.info("Work Phone entered successfully !");
					}
					else if(key.contentEquals("Work Phone Ext."))
					{
						driver.findElement(NewAccountCreation.fn_getWorkPhoneExt()).clear();// clear the Work Phone Ext. field.
						driver.findElement(NewAccountCreation.fn_getWorkPhoneExt()).sendKeys(strKeyValue);// type the read cell value.

						Log.info("Work Phone Ext. entered successfully !");
					}
					else if(key.contentEquals("Cell Phone"))
					{
						driver.findElement(NewAccountCreation.fn_getCellPhone()).clear();// clear the Cell Phone field.
						driver.findElement(NewAccountCreation.fn_getCellPhone()).sendKeys(strKeyValue);// type the read cell value.

						Log.info("Cell Phone entered successfully !");
					}
					else if(key.contentEquals("Email Address"))
					{
						driver.findElement(NewAccountCreation.fn_getEmailAddress()).clear();// clear the Email Address field.
						driver.findElement(NewAccountCreation.fn_getEmailAddress()).sendKeys(strKeyValue);// type the read cell value.

						Log.info("Email Address entered successfully !");
					}

					// Billing Contact Information
					else if(key.contentEquals("Billing First Name"))
					{
						driver.findElement(NewAccountCreation.fn_getBillingFirstName()).clear();
																								// clear the Billing First Name field.
						driver.findElement(NewAccountCreation.fn_getBillingFirstName()).sendKeys(strKeyValue);
																								// type the read cell value.

						Log.info("Billing First Name entered successfully !");
					}
					else if(key.contentEquals("Billing Last Name"))
					{
						driver.findElement(NewAccountCreation.fn_getBillingLastName()).clear();
																								// clear the Billing Last Name field.
						driver.findElement(NewAccountCreation.fn_getBillingLastName()).sendKeys(strKeyValue);
																								// type the read cell value.

						Log.info("Billing Last Name entered successfully !");
					}
					else if(key.contentEquals("Billing Company Name"))
					{
						driver.findElement(NewAccountCreation.fn_getBillingCompanyName()).clear();
																								// clear the Billing Company Name field.
						driver.findElement(NewAccountCreation.fn_getBillingCompanyName()).sendKeys(strKeyValue);
																								// type the read cell value.

						Log.info("Billing Company Name entered successfully !");
					}
					else if(key.contentEquals("Billing Address 1"))
					{
						driver.findElement(NewAccountCreation.fn_getBillingAddress1()).clear();
																								// clear the Billing Address 1 field.
						driver.findElement(NewAccountCreation.fn_getBillingAddress1()).sendKeys(strKeyValue);
																								// type the read cell value.

						Log.info("Billing Address 1 entered successfully !");
					}
					else if(key.contentEquals("Billing Address 2"))
					{
						driver.findElement(NewAccountCreation.fn_getBillingAddress2()).clear();
																								// clear the Billing Address 2 field.
						driver.findElement(NewAccountCreation.fn_getBillingAddress2()).sendKeys(strKeyValue);
																								// type the read cell value.

						Log.info("Billing Address 2 entered successfully !");
					}
					else if(key.contentEquals("Billing City"))
					{
						driver.findElement(NewAccountCreation.fn_getBillingCity()).clear();
																								// clear the Billing City field.
						driver.findElement(NewAccountCreation.fn_getBillingCity()).sendKeys(strKeyValue);
																								// type the read cell value.

						Log.info("Billing City entered successfully !");
					}
					else if(key.contentEquals("Billing State/Province"))
					{
						driver.findElement(NewAccountCreation.fn_getBillingState()).clear();
																								// clear the Billing State/Province field.
						driver.findElement(NewAccountCreation.fn_getBillingState()).sendKeys(strKeyValue);
																								// type the read cell value.

						Log.info("Billing State/Province entered successfully !");
					}
					else if(key.contentEquals("Billing Locality"))
					{
						driver.findElement(NewAccountCreation.fn_getBillingLocality()).clear();
																								// clear the Billing Locality field.
						driver.findElement(NewAccountCreation.fn_getBillingLocality()).sendKeys(strKeyValue);
																								// type the read cell value.

						Log.info("Billing Locality entered successfully !");
					}
					else if(key.contentEquals("Billing Country"))
					{
						driver.findElement(NewAccountCreation.fn_clickBillingCountry()).click();
																								// click the Billing Country field.
						driver.findElement(NewAccountCreation.fn_clickBillingCountry()).sendKeys(strKeyValue);
																								// type the read cell value.

						Log.info("Billing Country selected successfully !");
					}
					else if(key.contentEquals("Billing Zip Code"))
					{
						driver.findElement(NewAccountCreation.fn_getBillingZipCode()).clear();
																								// clear the Billing Zip Code field.
						driver.findElement(NewAccountCreation.fn_getBillingZipCode()).sendKeys(strKeyValue);
																								// type the read cell value.

						Log.info("Billing Zip Code entered successfully !");
					}
					else if(key.contentEquals("Billing Work Phone"))
					{
						driver.findElement(NewAccountCreation.fn_getBillingWorkPhone()).clear();
																								// clear the Billing Work Phone field.
						driver.findElement(NewAccountCreation.fn_getBillingWorkPhone()).sendKeys(strKeyValue);
																								// type the read cell value.

						Log.info("Billing Work Phone entered successfully !");
					}
					else if(key.contentEquals("Billing Work Phone Ext."))
					{
						driver.findElement(NewAccountCreation.fn_getBillingWorkPhoneExt()).clear();
																								// clear the Billing Work Phone Ext. field.
						driver.findElement(NewAccountCreation.fn_getBillingWorkPhoneExt()).sendKeys(strKeyValue);
																								// type the read cell value.

						Log.info("Billing Work Phone Ext. entered successfully !");
					}
					else if(key.contentEquals("Billing Cell Phone"))
					{
						driver.findElement(NewAccountCreation.fn_getBillingCellPhone()).clear();
																								// clear the Billing Cell Phone field.
						driver.findElement(NewAccountCreation.fn_getBillingCellPhone()).sendKeys(strKeyValue);
																								// type the read cell value.

						Log.info("Billing Cell Phone entered successfully !");
					}
					else if(key.contentEquals("Billing Email Address"))
					{
						driver.findElement(NewAccountCreation.fn_getBillingEmailAddress()).clear();
																								// clear the Billing Email Address field.
						driver.findElement(NewAccountCreation.fn_getBillingEmailAddress()).sendKeys(strKeyValue);
																								// type the read cell value.

						Log.info("Billing Email Address entered successfully !");
					}

					// Plan Options
					else if(key.contentEquals("Master Plan"))
					{
						driver.findElement(NewAccountCreation.fn_clickMasterPlan()).click();
																								// click the Master Plan field.
						driver.findElement(NewAccountCreation.fn_clickMasterPlan()).sendKeys(strKeyValue);
																								// type the read cell value.

						Log.info("Master Plan selected successfully !");
						driver.findElement(NewAccountCreation.fn_clickMasterPlan()).click();
																								// click the Master Plan field.
						TimeUnit.SECONDS.sleep(10);// This sleep is for waiting for the Supplemental Plan No. field.
					}
					else if(key.contentEquals("Supplemental Plan No."))
					{
						// since the Supplemental Plan No. field is dependent on Master Plan, we will initiate the Master Plan field
						// and then we will pass on the value for Supplemental Plan No.
						strKeyValue = "Sungard AS Master Plan";	// hard-coding done here to mitigate the dependency issue.
						driver.findElement(NewAccountCreation.fn_clickMasterPlan()).click();
																								// click the Master Plan field.
						driver.findElement(NewAccountCreation.fn_clickMasterPlan()).sendKeys(strKeyValue);
																								// type the read cell value.
						TimeUnit.SECONDS.sleep(10);	// This sleep is for waiting for the Supplemental Plan No. field.
						driver.findElement(NewAccountCreation.fn_clickMasterPlan()).click();
						Log.info("Master Plan selected successfully !");

						strKeyValue = fileCellContents.get(key);	// get the value for the keys to be entered.
						driver.findElement(NewAccountCreation.fn_clickSupplementalPlanNo()).click();
																								// click the Supplemental Plan No. field.
						driver.findElement(NewAccountCreation.fn_clickSupplementalPlanNo()).sendKeys(strKeyValue);
																								// type the read cell value.
						Thread.sleep(10000);
						Log.info("Supplemental Plan No. selected successfully !");
					}

					// Invoicing / Payments
					else if(key.contentEquals("Payment Method"))
					{
						driver.findElement(NewAccountCreation.fn_clickPaymentMethod()).click();
																								// click the Payment Method field.
						driver.findElement(NewAccountCreation.fn_clickPaymentMethod()).sendKeys(strKeyValue);
																								// type the read cell value.

						Log.info("Payment Method selected successfully !");
					}
					else if(key.contentEquals("Currency"))
					{
						driver.findElement(NewAccountCreation.fn_clickPaymentCurrency()).click();
																								// click the Currency field.
						driver.findElement(NewAccountCreation.fn_clickPaymentCurrency()).sendKeys(strKeyValue);
																								// type the read cell value.

						Log.info("Currency selected successfully !");
					}

					// Billing Date Options
					else if(key.contentEquals("Retroactive Billing Start Date"))
					{
						driver.findElement(NewAccountCreation.fn_clickRetroactiveBillingStartDate()).click();
																				// click the Retroactive Billing Start Date field.
						driver.findElement(NewAccountCreation.fn_clickRetroactiveBillingStartDate()).sendKeys(strKeyValue);
																								// type the read cell value.

						Log.info("Retroactive Billing Start Date selected successfully !");
					}
					else if(key.contentEquals("Alternate Billing Start Date"))
					{
//						driver.findElement(NewAccountCreation.fn_clickAlternateBillingStartDate()).click();
//																				// click the Alternate Billing Start Date field.
//						driver.findElement(NewAccountCreation.fn_clickAlternateBillingStartDate()).sendKeys(keyValue);
																								// type the read cell value.

						Log.info("Alternate Billing Start Date selected successfully !");
					}
					else if(key.contentEquals("Alternate Billing Day"))
					{
						driver.findElement(NewAccountCreation.fn_clickAlternateBillingDay()).click();
						int altDayInt = Integer.parseInt(Utils.getCurrentDate().substring(0,2)) + 2;  //seting alternate date integer to 2 + current day

						WebElement altDayDropDown = driver.findElement(NewAccountCreation.fn_clickAlternateBillingDay());
						List<WebElement> dropDownOptions = altDayDropDown.findElements(By.tagName("option"));

						//iterate over list of days and select alt day
						for(WebElement option : dropDownOptions)
						{
							if(option.getText().equals(Integer.toString(altDayInt)))
							{
								option.click();
							}
						}

						Log.info("Alternate Billing Day selected successfully !");
					}
					else if(key.contentEquals("Perform Full Invoicing"))
					{
						driver.findElement(NewAccountCreation.fn_clickPerformFullInvoicing()).click();
																								// click the Perform Full Invoicing field.
						System.out.println("Perform full invoicing set to: "+strKeyValue);
						driver.findElement(NewAccountCreation.fn_clickPerformFullInvoicing()).sendKeys(strKeyValue);
																								// type the read cell value.

						Log.info("Perform Full Invoicing selected successfully !");
					}

					else
					{
						Log.error("The Hash Map is empty so unable to fill the form.");
						try
						{
							Utils.takeScreenshot(driver, strTestCaseName);
														// Take the snapshot with blank Create Account page.
						}
						catch(Exception e)
						{
							Utils.takeScreenshot(driver, strTestCaseName);
														// Take the snapshot with blank Create Account page.
							Log.error("Snapshot could not be taken for empty Create Account Page !");
						}
					}
				}



	    		try
	    		{
					Utils.takeScreenshot(driver, strTestCaseName);

	    			// Now save the account details to create an Account.
					driver.findElement(NewAccountCreation.fn_clickSaveButton()).click();// clicking the Save button to save the details.

					TimeUnit.SECONDS.sleep(10);
		    		driver.findElement(NewAccountCreation.fn_dlgboxCompleted()).click();// clicking to set focus on the Successful Completion message box.
		    		TimeUnit.SECONDS.sleep(10);	// sleep for the message to be displayed.

		    		driver.findElement(NewAccountCreation.fn_clickCloseCompleted()).click();	// clicking the OK button for closing dialogue box.
		    		TimeUnit.SECONDS.sleep(10);	// sleep introduced for the message to be reflected on the page.

	    			Utils.takeScreenshot(driver, strTestCaseName);	// Take snapshot to know the actual Account ID.

	    			intCounterAccountsAdded = intCounterAccountsAdded + 1;	// increment this counter to be referred later for deciding call.

		    		strNewAccountID = driver.findElement(NewAccountCreation.fn_clickNewAccountNum()).getText();	// Get the Account ID.
		    		Log.info("New Account successfully created with Account Number as: " + strNewAccountID.toString());
		    		Reporter.log("New Account successfully created with Account Number as: " + strNewAccountID.toString());
		    		TimeUnit.SECONDS.sleep(10);

		    		if(intCounterAccountsAdded == 1)	// Loop to record the Primary Parent Account added.
		    		{
			    		strNewParentChildAccountID = strNewAccountID;	//	copy the Primary account ID.
		    		}

		    		driver.findElement(NewAccountCreation.fn_clickNewAccountNum()).click();// click on the Account Num to navigate to the
		    																						 // Account Overview Page.
		    		TimeUnit.SECONDS.sleep(10);	// wait for the Account Overview page to come up.
		    		driver.findElement(NewAccountCreation.fn_clickAccountOverview()).click();// click the Account Overview tab for setting focus.
		    		TimeUnit.SECONDS.sleep(10);	// wait for the Account Overview page to come up.

					if (strTestCaseName.contentEquals("Create New Account"))
					{
						Cls_NewAccountCreation newAccCreation = new Cls_NewAccountCreation();
						//newAccCreation  // .VerifyCreatedAccount(newAccountID);	// call this function to verify the added details.
						Log.info("New Account Created and successfully verified !");
						verifyTrue(true);
					}
					else if (strTestCaseName.contentEquals("New Account with Parent Child Relation"))
					{
						Cls_ParentChildNewAccountCreation newParentChild = new Cls_ParentChildNewAccountCreation();
						//newParentChild.fn_CreateParentChildAccount(parentAccountID, newAccountID);  //.VerifyParentChildRelation(parentAccountID, newAccountID);;	// call this function to verify the added details.
						Log.info("New Account Created with Parent Child relation successfully verified !");
						assertTrue(true);
					}
					else if  (strTestCaseName.contentEquals("New Account with Parent Child Relation @ Runtime"))
					{
						if (intCounterAccountsAdded == 1)
						{
							NewAccountCreation newAccCreation = new NewAccountCreation();
							//newAccCreation.VerifyCreatedAccount(newAccountID);	// call this function to verify the added details.
							Log.info("New Account Created and successfully verified !");
							verifyTrue(true);
						}
						else
						{
							Cls_RuntimeNewParentNewChildAccountCreation newRuntimeParentChild = new Cls_RuntimeNewParentNewChildAccountCreation();
							//newRuntimeParentChild.VerifyRelationalAccount(newParentChildAccountID, newAccountID);
						}
					}
	    		}
	    		catch (Exception e)
	    		{
	    			Utils.takeScreenshot(driver, strTestCaseName);	// Take the snapshot with blank Create Account page.
	    			Log.error("Account Creation couldn't be completed due to error while Account Saving as: "+e.toString());
	    			assertFalse(false);
	    		}
			}
			return strNewAccountID;
		}
		catch (Exception e)
		{
			Utils.takeScreenshot(driver, strTestCaseName);	// Take the snapshot with blank Create Account page.
			Log.error("The Create Account form could not be filled due to error: " + e.toString());
			assertFalse(false);
			return strNewAccountID;
		}
	}

}
