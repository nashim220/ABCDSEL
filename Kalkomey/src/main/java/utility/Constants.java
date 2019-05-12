package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Constants 
{
	public static final String PropertyFile="src/test/resources/Constants.properties";
		
	/*
	Test Data location
	*/
	public static final String 	TestDataFileLocation= GetPropertyValue("TestDataFileLocation");
	
	 
	/*
	Selenium Specific parameters
	*/
	public static final int WebDriverWaitDuration= Integer.parseInt(GetPropertyValue("WebDriverWaitDuration"));
	public static final int ThreadWaitInmiliseconds= Integer.parseInt(GetPropertyValue("ThreadWaitInmiliseconds"));
	
	/*
	Selenium Grid parameters
	*/
	public static final String 	nodeURL1= GetPropertyValue("nodeURL1");
	public static final String 	nodeURL2= GetPropertyValue("nodeURL2");

	
	/*
	Browserstack parameters
	*/
	public static final String 	BS_USERNAME= GetPropertyValue("BS_USERNAME");
	public static final String 	BS_AUTOMATE_KEY= GetPropertyValue("BS_AUTOMATE_KEY");
	
	
	/*
	SauceLabs parameters
	*/
	public static final String 	SL_USERNAME= GetPropertyValue("SL_USERNAME");
	public static final String 	SL_AUTOMATE_KEY= GetPropertyValue("SL_AUTOMATE_KEY");
	
	
	/*
	KalKomey Specific parameters
	*/
	
		/*BoatEd environment Details*/
		public static final String 	ApplicationURL= GetSysPropertyValue("ApplicationURL");
		public static final String 	LoginPage = ApplicationURL + "/site/login";
		public static final String 	ProfilePage = ApplicationURL + "/register/edit";
		public static final String 	State= GetPropertyValue("State");
		public static final String 	Error500Page_Boat= GetPropertyValue("Error500Page_Boat");
		public static final String 	Error404Page= GetPropertyValue("Error404Page");
		
		
		/*Hunter environment Details*/
		public static final String 	ApplicationURL_Hunter= GetSysPropertyValue("ApplicationURL_Hunter");
		public static final String 	LoginPage_Hunter = ApplicationURL_Hunter + "/site/login";
		public static final String 	ProfilePage_Hunter = ApplicationURL_Hunter + "/register/edit";
		public static final String 	State_Hunter= GetPropertyValue("State_Hunter");
		public static final String 	State_Video= GetPropertyValue("State_Video");
		public static final String 	State_Hunter_Timed= GetPropertyValue("State_Hunter_Timed");
		public static final String 	Error500Page_Hunter= GetPropertyValue("Error500Page_Hunter");
		public static final String 	Error404Page_Hunter= GetPropertyValue("Error404Page_Hunter");
		public static final String  Testout_Hunter = GetPropertyValue("Testout_Hunter");
		
		
		/*BowHunter environment Details*/
		public static final String 	ApplicationURL_BowHunter= GetSysPropertyValue("ApplicationURL_BowHunter");
		public static final String 	LoginPage_BowHunter = ApplicationURL_BowHunter + "/site/login";
		public static final String 	ProfilePage_BowHunter = ApplicationURL_BowHunter + "/register/edit";
		public static final String 	State_BowHunter= GetPropertyValue("State_BowHunter");
		public static final String 	State_BowHunter_UnTimed= GetPropertyValue("State_BowHunter_UnTimed");
		public static final String 	Error500Page_BowHunter= GetPropertyValue("Error500Page_BowHunter");
		public static final String 	Error404Page_BowHunter= GetPropertyValue("Error404Page_BowHunter");
		
		
		/*Offroad environment Details*/
		public static final String 	ApplicationURL_Offroad= GetSysPropertyValue("ApplicationURL_Offroad");
		public static final String 	LoginPage_Offroad = ApplicationURL_Offroad + "/site/login";
		public static final String 	ProfilePage_Offroad = ApplicationURL_Offroad + "/register/edit";
		public static final String 	State_Offroad= GetPropertyValue("State_Offroad");
		public static final String 	State_Offroad_UnTimed= GetPropertyValue("State_Offroad_UnTimed");
		public static final String 	Error500Page_Offroad= GetPropertyValue("Error500Page_Offroad");
		public static final String 	Error404Page_Offroad= GetPropertyValue("Error404Page_Offroad");
		public static final String 	ChallengeExam_Offroad= GetPropertyValue("ChallengeExam_Offroad");

		
		/*Snowmobile environment Details*/
		public static final String 	ApplicationURL_Snowmobile= GetSysPropertyValue("ApplicationURL_Snowmobile");
		public static final String 	LoginPage_Snowmobile = ApplicationURL_Snowmobile + "/site/login";
		public static final String 	ProfilePage_Snowmobile = ApplicationURL_Snowmobile + "/register/edit";
		public static final String 	State_Snowmobile= GetPropertyValue("State_Snowmobile");
		public static final String 	State_Snowmobile_UnTimed= GetPropertyValue("State_Snowmobile_UnTimed");
		public static final String 	Error500Page_Snowmobile= GetPropertyValue("Error500Page_Snowmobile");
		public static final String 	Error404Page_Snowmobile= GetPropertyValue("Error404Page_Snowmobile");
		 
		
		/*Handgun Safety Course environment Details*/
		public static final String 	ApplicationURL_Handgun= GetSysPropertyValue("ApplicationURL_Handgun");
		public static final String  ApplicationURL_Handgun_national = GetSysPropertyValue("ApplicationURL_Handgun_national");
		public static final String 	LoginPage_Handgun = ApplicationURL_Handgun + "/site/login";
		public static final String 	ProfilePage_Handgun = ApplicationURL_Handgun + "/register/edit";
		public static final String 	Error500Page_Handgun= GetPropertyValue("Error500Page_Handgun");
		public static final String 	Error404Page_Handgun= GetPropertyValue("Error404Page_Handgun");
		
		
		/*Admin Details*/
		public static final String AdminURL = GetSysPropertyValue("AdminURL");
		public static final String CouponCode = GetPropertyValue("CouponCode");
		
		
		/*Certification Manager / ILMC environment Details*/
		public static final String ApplicationURL_CM = GetPropertyValue("ApplicationURL_CM");
		public static final String CM_TestUserKeAdmin = GetPropertyValue("CM_TestUserKeAdmin");
		public static final String LoginPage_CM = ApplicationURL_CM + "/login";
		public static final String CM_Username = GetPropertyValue("CM_Username");
		public static final String CM_Password = GetPropertyValue("CM_Password");
		public static final String CM_Username_Normal = GetPropertyValue("CM_Username_Normal");
		public static final String CM_Password_Normal = GetPropertyValue("CM_Password_Normal");
		public static final String CM_Username_Normal1 = GetPropertyValue("CM_Username_Normal1");
		public static final String CM_Password_Normal1 = GetPropertyValue("CM_Password_Normal1");		
		public static final String CM_Username_Verginia = GetPropertyValue("CM_Username_Verginia");
		public static final String CM_Password_Verginia = GetPropertyValue("CM_Password_Verginia");
		public static final String CM_Username_AgencyImport = GetPropertyValue("CM_Username_AgencyImport");
		public static final String CM_Password_AgencyImport = GetPropertyValue("CM_Password_AgencyImport");
		public static final String CM_Error500Page = GetPropertyValue("CM_Error500Page");
		public static final String ApplicationURL_ILMC = GetPropertyValue("ApplicationURL_ILMC");
		public static final String LoginPage_CM_Agencies = ApplicationURL_CM + "/agencies";
		public static final String LoginPage_CM__Agencies_New = ApplicationURL_CM + "/agencies/new";
		public static final String LoginPage_CM__Agencies_22 = ApplicationURL_CM + "/agencies/22";
		public static final String CM_Username_Normal_Test = GetPropertyValue("CM_Username_Normal_Test");
		public static final String CM_Password_Normal_Test = GetPropertyValue("CM_Password_Normal_Test");
		public static final String CM_User_Remove_Permission = GetPropertyValue("CM_User_Remove_Permission");
		public static final String CM_Pwd_Remove_Permission = GetPropertyValue("CM_Pwd_Remove_Permission");
		public static final String CM_User_EditOwn_Permsision = GetPropertyValue("CM_User_EditOwn_Permsision");
		public static final String CM_Pwd_EditOwn_Permsision = GetPropertyValue("CM_Pwd_EditOwn_Permsision");
		public static final String CM_User_Combination_Permsision = GetPropertyValue("CM_User_Combination_Permsision");
		public static final String CM_Pwd_Combination_Permsision = GetPropertyValue("CM_Pwd_Combination_Permsision");
		public static final String CM_User_Add_Notes = GetPropertyValue("CM_User_Add_Notes");
		public static final String CM_Pwd_Add_Notes = GetPropertyValue("CM_Pwd_Add_Notes");
		public static final String CM_User_Create_Cert = GetPropertyValue("CM_User_Create_Cert");
		public static final String CM_Pwd_Create_Cert = GetPropertyValue("CM_Pwd_Create_Cert");
		public static final String CM_User_Edit_Cert = GetPropertyValue("CM_User_Edit_Cert");
		public static final String CM_Pwd_Edit_Cert = GetPropertyValue("CM_Pwd_Edit_Cert");
		public static final String CM_User_Order_Cert = GetPropertyValue("CM_User_Order_Cert");
		public static final String CM_Pwd_Order_Cert = GetPropertyValue("CM_Pwd_Order_Cert");
		public static final String CM_User_Send_CourseComp = GetPropertyValue("CM_User_Send_CourseComp");
		public static final String CM_Pwd_Send_CourseComp = GetPropertyValue("CM_User_Send_CourseComp");	
		public static final String CM_User_CanView_Reports = GetPropertyValue("CM_User_CanView_Reports");
		public static final String CM_Pwd_CanView_Reports = GetPropertyValue("CM_Pwd_CanView_Reports");
		public static final String CM_User_Order_ByPass = GetPropertyValue("CM_User_Order_ByPass");
		public static final String CM_Pwd_Order_ByPass = GetPropertyValue("CM_Pwd_Order_ByPass");
		public static final String CM_User_View_Permisson = GetPropertyValue("CM_User_View_Permisson");
		public static final String CM_Pwd_View_Permisson = GetPropertyValue("CM_Pwd_View_Permisson");
		public static final String CM_User_Less_Permission = GetPropertyValue("CM_User_Less_Permission");
		public static final String CM_Pwd_Less_Permission = GetPropertyValue("CM_Pwd_Less_Permission");
		public static final String CM_User_CollectionAdd = GetPropertyValue("CM_User_CollectionAdd");
		public static final String CM_Pwd_CollectionAdd = GetPropertyValue("CM_Pwd_CollectionAdd");
		public static final String CM_User_Agency_Unauthorized = GetPropertyValue("CM_User_Agency_Unauthorized");
		public static final String CM_Pwd_Agency_Unauthorized = GetPropertyValue("CM_Pwd_Agency_Unauthorized");
		public static final String CM_User_NonAdminCM = GetPropertyValue("CM_User_NonAdminCM");
		public static final String CM_Pwd_NonAdminCM = GetPropertyValue("CM_Pwd_NonAdminCM");
		public static final String CM_User_NonAgencyAdmin = GetPropertyValue("CM_User_NonAgencyAdmin");
		public static final String CM_Pwd_NonAgencyAdmin = GetPropertyValue("CM_Pwd_NonAgencyAdmin");
		public static final String CM_User_CanManageUser = GetPropertyValue("CM_User_CanManageUser");
		public static final String CM_Pwd_CanManageUser = GetPropertyValue("CM_Pwd_CanManageUser");
		
		
		
		
		public static final String CM_Username_Normal2 = GetPropertyValue("CM_Username_Normal2");
		public static final String CM_Password_Normal2 = GetPropertyValue("CM_Password_Normal2");
		public static final String CM_Username_Normal3 = GetPropertyValue("CM_Username_Normal3");
		public static final String CM_Password_Normal3 = GetPropertyValue("CM_Password_Normal3");
		public static final String CM_Username_Normal4 = GetPropertyValue("CM_Username_Normal4");
		public static final String CM_Password_Normal4 = GetPropertyValue("CM_Password_Normal4");
		public static final String CM_Username_Normal5 = GetPropertyValue("CM_Username_Normal5");
		public static final String CM_Password_Normal5 = GetPropertyValue("CM_Password_Normal5");
		public static final String CM_Username_Normal6 = GetPropertyValue("CM_Username_Normal6");
		public static final String CM_Password_Normal6 = GetPropertyValue("CM_Password_Normal6");
		public static final String CM_Username_Normal7 = GetPropertyValue("CM_Username_Normal7");
		public static final String CM_Password_Normal7 = GetPropertyValue("CM_Password_Normal7");
		public static final String CM_Username_Agency = GetPropertyValue("CM_Username_Agency");
		public static final String CM_Password_Agency = GetPropertyValue("CM_Password_Agency");
   		
		public static final String LoginPage_CM_MCO = ApplicationURL_CM + "/certification_collections";
		
		
		/*Event Manager / Registered-Ed environment Details*/
		public static final String ApplicationURL_EM = GetPropertyValue("ApplicationURL_EM");
		public static final String EM_Instructor_Username = GetPropertyValue("EM_Instructor_Username");
		public static final String EM_Instructor_Password = GetPropertyValue("EM_Instructor_Password");
		public static final String EM_Iowa_Instructor_Username = GetPropertyValue("EM_Iowa_Instructor_Username");
		public static final String EM_Iowa_Instructor_Password = GetPropertyValue("EM_Iowa_Instructor_Password");
		public static final String EM_Assistant_Instructor_Username = GetPropertyValue("EM_Assistant_Instructor_Username");
		public static final String EM_Assistant_Instructor_Password = GetPropertyValue("EM_Assistant_Instructor_Password");
		public static final String EM_NYInstructor_Username = GetPropertyValue("EM_NYInstructor_Username");
		public static final String EM_NYInstructor_Password = GetPropertyValue("EM_NYInstructor_Password");
		public static final String EM_NYAdmin_Username = GetPropertyValue("EM_NYAdmin_Username");
		public static final String EM_NYAdmin_Password = GetPropertyValue("EM_NYAdmin_Password");
		public static final String EM_Admin_Username = GetPropertyValue("EM_Admin_Username");
		public static final String EM_Admin_Password = GetPropertyValue("EM_Admin_Password");
		public static final String EM_TNInstructor_Username = GetPropertyValue("EM_TNInstructor_Username");
		public static final String EM_TNInstructor_Password = GetPropertyValue("EM_TNInstructor_Password");
		public static final String EM_DemoAdmin_Username = GetPropertyValue("EM_DemoAdmin_Username");
		public static final String EM_DemoAdmin_Password = GetPropertyValue("EM_DemoAdmin_Password");
		public static final String EM_CLInstructor_Username = GetPropertyValue("EM_CLInstructor_Username");
		public static final String EM_CLInstructor_Password = GetPropertyValue("EM_CLInstructor_Password");
		public static final String EM_MOInstructor_Username = GetPropertyValue("EM_MOInstructor_Username");
		public static final String EM_MOInstructor_Password = GetPropertyValue("EM_MOInstructor_Password");
		public static final String APPLICATIONURL_RegisterEd = GetPropertyValue("ApplicationURL_RegisterED");
		public static final String EM_ResetPIN_Username = GetPropertyValue("EM_ResetPIN_Username");
		public static final String EM_ResetPIN_Password = GetPropertyValue("EM_ResetPIN_Password");
		public static final String EM_CustomerServiceRole_Username = GetPropertyValue("EM_CustomerServiceRole_Username");
		public static final String EM_CustomerServiceRole_Password = GetPropertyValue("EM_CustomerServiceRole_Password");
		
		
		
		// Forgot Password usernames
		public static final String InvalidUsername = GetPropertyValue("InvalidUsername");
		public static final String UsernameWithoutRegisteredEmail = GetPropertyValue("UsernameWithoutRegisteredEmail");
		
		/*Broken Links Verification Data*/
		public static final String BrokenLink_TestPage = GetSysPropertyValue("BrokenLink_TestPage");
		
		
	/*
	Reading a propertyfile and getting value of any property defined
	@Param PropertyName - Name of property for which you want to get value
	*/	
	public static Properties prop;
	static String PropertyValue;	
	public static String GetPropertyValue(String PropertyName)
	{
		prop = AccessPropertiesFile();

			try{
				PropertyValue= prop.getProperty(PropertyName);	
				}

			catch(Exception e){
				}	
			return PropertyValue;
	}
	public static Properties AccessPropertiesFile()
	{
		prop = new Properties();

		// try retrieve data from file
		try 
		{

			prop.load(new FileInputStream(PropertyFile));
		}
		// catch exception in case properties file does not exist
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		return prop;
	}
	
	public static String GetSysPropertyValue(String property)
	{
		String PropertyNew=System.getProperty(property);
		
		if(PropertyNew == null || PropertyNew.isEmpty()) 
		{	
			PropertyNew=GetPropertyValue(property);
		}
		return PropertyNew;
		
	}

}
