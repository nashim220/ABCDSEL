package utility;

import org.testng.annotations.DataProvider;

public class TestNG 
{
	private static JavaHelpers Read1 = new JavaHelpers();

	
	//Boat-Ed  
	
		//RegistrationTest - Test 1
		@DataProvider(name ="Registration")
		public static Object[][] Registration() throws Exception
			{
			        Object[][] retObjArr=
			        		Read1.GetTableArray(Constants.TestDataFileLocation, "BoatEd","Registration");
			        // Excel File must be of Word 97-2003 format as jxl support that only
			        return(retObjArr);
			}
		
		
		//RegistrationTest - Registration_ModelReminder - Test 19
		@DataProvider(name ="Registration_ModelReminder")
		public static Object[][] Registration_ModelReminder() throws Exception
			{
			        Object[][] retObjArr=
			        		Read1.GetTableArray(Constants.TestDataFileLocation, "BoatEd","Registration_ModelReminder");
			        // Excel File must be of Word 97-2003 format as jxl support that only
			        return(retObjArr);
			}
	
		
		
		//RegistrationTest -Registration_MimAgeValidation - Test 20
		@DataProvider(name ="Registration_MimAgeValidation")
		public static Object[][] Registration_MimAgeValidation() throws Exception
			{
			        Object[][] retObjArr=
			        		Read1.GetTableArray(Constants.TestDataFileLocation, "BoatEd","Registration_MimAgeValidation");
			        // Excel File must be of Word 97-2003 format as jxl support that only
			        return(retObjArr);
			}
		
		
		
		
		//LoginForgotPasswordTest -Registration_MimAgeValidation - Test 5
			@DataProvider(name ="Login_RestrictedPages")
			public static Object[][] Login_RestrictedPages() throws Exception
				{
				        Object[][] retObjArr=
				        		Read1.GetTableArray(Constants.TestDataFileLocation, "BoatEd","Login_RestrictedPages");
				        // Excel File must be of Word 97-2003 format as jxl support that only
				        return(retObjArr);
				}
			
			
		//StudyGuideTest -Navigate_To_StudyGuide_Page - Test 1
			@DataProvider(name ="StudyGuide")
			public static Object[][] StudyGuide() throws Exception
				{
				        Object[][] retObjArr=
				        		Read1.GetTableArray(Constants.TestDataFileLocation, "BoatEd","StudyGuide");
				        // Excel File must be of Word 97-2003 format as jxl support that only
				        return(retObjArr);
				}
	
			
		//PayUpfrontTest - Verify that user is asked for paying upfront for {state} - Test 1
			@DataProvider(name ="PayUpfront")
			public static Object[][] PayUpfront() throws Exception
				{
				        Object[][] retObjArr=
				        		Read1.GetTableArray(Constants.TestDataFileLocation, "BoatEd","PayUpfront");
				        // Excel File must be of Word 97-2003 format as jxl support that only
				        return(retObjArr);
				}
			
		//HandbookTest - Handbook - Web format:  Verify page title, presence of 'Table Of Content' section, 'Previous' 'Next' buttons - Test 1
		@DataProvider(name ="Handbook")
		public static Object[][] Handbook() throws Exception
			{
			        Object[][] retObjArr=
			        		Read1.GetTableArray(Constants.TestDataFileLocation, "BoatEd","Handbook");
			        // Excel File must be of Word 97-2003 format as jxl support that only
			        return(retObjArr);
			}
		
		
		//SpecialCases -Boat Canada Course > Province - Verify 'Quick Links' specific links exists and working as expected  Test 11
		@DataProvider(name ="CanadaProvince")
		public static Object[][] CanadaProvince() throws Exception
			{
			        Object[][] retObjArr=
			        		Read1.GetTableArray(Constants.TestDataFileLocation, "BoatEd","CanadaProvince");
			        // Excel File must be of Word 97-2003 format as jxl support that only
			        return(retObjArr);
			}

		
		//SpecialCases -Boat Canada & Provinces - Verify 'Who Needs the Card?' link- Test 12
		@DataProvider(name ="CanadaAndProvinces")
		public static Object[][] CanadaAndProvinces() throws Exception
			{
			        Object[][] retObjArr=
			        		Read1.GetTableArray(Constants.TestDataFileLocation, "BoatEd","CanadaAndProvinces");
			        // Excel File must be of Word 97-2003 format as jxl support that only
			        return(retObjArr);
			}

			
			
	//Hunter-Ed
		
		//RegistrationTest - Test 1
		@DataProvider(name ="Registration_hunter")
		public static Object[][] Registration_hunter() throws Exception
			{
			        Object[][] retObjArr=
			        		Read1.GetTableArray(Constants.TestDataFileLocation, "HunterEd","Registration");
			        // Excel File must be of Word 97-2003 format as jxl support that only
			        return(retObjArr);
			}
		
		
		//RegistrationTest - Registration_ModelReminder - Test 19
		@DataProvider(name ="Registration_ModelReminder_hunter")
		public static Object[][] Registration_ModelReminder_hunter() throws Exception
			{
			        Object[][] retObjArr=
			        		Read1.GetTableArray(Constants.TestDataFileLocation, "HunterEd","Registration_ModelReminder");
			        // Excel File must be of Word 97-2003 format as jxl support that only
			        return(retObjArr);
			}
	
		
		//RegistrationTest - Registration_ModelReminder - Test 18
				@DataProvider(name ="Registration_ResidencyRequirement_hunter")
				public static Object[][] Registration_ResidencyRequirement_hunter() throws Exception
					{
					        Object[][] retObjArr=
					        		Read1.GetTableArray(Constants.TestDataFileLocation, "HunterEd","Registration_ResidencyRequirement");
					        // Excel File must be of Word 97-2003 format as jxl support that only
					        return(retObjArr);
					}
				
		
		//RegistrationTest -Registration_MimAgeValidation - Test 20
		@DataProvider(name ="Registration_MimAgeValidation_hunter")
		public static Object[][] Registration_MimAgeValidation_hunter() throws Exception
			{
			        Object[][] retObjArr=
			        		Read1.GetTableArray(Constants.TestDataFileLocation, "HunterEd","Registration_MimAgeValidation");
			        // Excel File must be of Word 97-2003 format as jxl support that only
			        return(retObjArr);
			}
		
		
		
		
		//LoginForgotPasswordTest -Registration_MimAgeValidation - Test 5
			@DataProvider(name ="Login_RestrictedPages_hunter")
			public static Object[][] Login_RestrictedPages_hunter() throws Exception
				{
				        Object[][] retObjArr=
				        		Read1.GetTableArray(Constants.TestDataFileLocation, "HunterEd","Login_RestrictedPages");
				        // Excel File must be of Word 97-2003 format as jxl support that only
				        return(retObjArr);
				}
			
			
		//StudyGuideTest -Navigate_To_StudyGuide_Page - Test 1
			@DataProvider(name ="StudyGuide_hunter")
			public static Object[][] StudyGuide_hunter() throws Exception
				{
				        Object[][] retObjArr=
				        		Read1.GetTableArray(Constants.TestDataFileLocation, "HunterEd","StudyGuide");
				        // Excel File must be of Word 97-2003 format as jxl support that only
				        return(retObjArr);
				}
			
		
	
		
			
		//SpecialCases - Help procrastinating students get registered - Test 9
			@DataProvider(name ="ProcrastinatingUser")
			public static Object[][] ProcrastinatingUser() throws Exception
				{
				        Object[][] retObjArr=
				        		Read1.GetTableArray(Constants.TestDataFileLocation, "HunterEd","ProcrastinatingUser");
				        // Excel File must be of Word 97-2003 format as jxl support that only
				        return(retObjArr);
				}
			
			
			
	//BowHunter-Ed
		
		//RegistrationTest - Test 1
		@DataProvider(name ="Registration_bowhunter")
		public static Object[][] Registration_bowhunter() throws Exception
			{
			        Object[][] retObjArr=
			        		Read1.GetTableArray(Constants.TestDataFileLocation, "BowHunterEd","Registration");
			        // Excel File must be of Word 97-2003 format as jxl support that only
			        return(retObjArr);
			}
		
		
		//RegistrationTest - Registration_ModelReminder - Test 19
		@DataProvider(name ="Registration_ModelReminder_bowhunter")
		public static Object[][] Registration_ModelReminder_bowhunter() throws Exception
			{
			        Object[][] retObjArr=
			        		Read1.GetTableArray(Constants.TestDataFileLocation, "BowHunterEd","Registration_ModelReminder");
			        // Excel File must be of Word 97-2003 format as jxl support that only
			        return(retObjArr);
			}
	
		
		//RegistrationTest - Registration_ModelReminder - Test 18
				@DataProvider(name ="Registration_ResidencyRequirement_bowhunter")
				public static Object[][] Registration_ResidencyRequirement_bowhunter() throws Exception
					{
					        Object[][] retObjArr=
					        		Read1.GetTableArray(Constants.TestDataFileLocation, "BowHunterEd","Registration_ResidencyRequirement");
					        // Excel File must be of Word 97-2003 format as jxl support that only
					        return(retObjArr);
					}
				
		
		//RegistrationTest -Registration_MimAgeValidation - Test 20
		@DataProvider(name ="Registration_MimAgeValidation_bowhunter")
		public static Object[][] Registration_MimAgeValidation_bowhunter() throws Exception
			{
			        Object[][] retObjArr=
			        		Read1.GetTableArray(Constants.TestDataFileLocation, "BowHunterEd","Registration_MimAgeValidation");
			        // Excel File must be of Word 97-2003 format as jxl support that only
			        return(retObjArr);
			}
		
		
		
		
		//LoginForgotPasswordTest -Registration_MimAgeValidation - Test 5
			@DataProvider(name ="Login_RestrictedPages_bowhunter")
			public static Object[][] Login_RestrictedPages_bowhunter() throws Exception
				{
				        Object[][] retObjArr=
				        		Read1.GetTableArray(Constants.TestDataFileLocation, "BowHunterEd","Login_RestrictedPages");
				        // Excel File must be of Word 97-2003 format as jxl support that only
				        return(retObjArr);
				}
			
			
		//StudyGuideTest -Navigate_To_StudyGuide_Page - Test 1
			@DataProvider(name ="StudyGuide_bowhunter")
			public static Object[][] StudyGuide_bowhunter() throws Exception
				{
				        Object[][] retObjArr=
				        		Read1.GetTableArray(Constants.TestDataFileLocation, "BowHunterEd","StudyGuide");
				        // Excel File must be of Word 97-2003 format as jxl support that only
				        return(retObjArr);
				}
	
			
		//PayUpfrontTest - Verify that user is asked for paying upfront for {state} - Test 1
			@DataProvider(name ="PayUpfront_bowhunter")
			public static Object[][] PayUpfront_bowhunter() throws Exception
				{
				        Object[][] retObjArr=
				        		Read1.GetTableArray(Constants.TestDataFileLocation, "BowHunterEd","PayUpfront");
				        // Excel File must be of Word 97-2003 format as jxl support that only
				        return(retObjArr);
				}		
	
			
	//Offroad-Ed
		
		//RegistrationTest - Test 1
		@DataProvider(name ="Registration_offroad")
		public static Object[][] Registration_offroad() throws Exception
			{
			        Object[][] retObjArr=
			        		Read1.GetTableArray(Constants.TestDataFileLocation, "OffroadEd","Registration");
			        // Excel File must be of Word 97-2003 format as jxl support that only
			        return(retObjArr);
			}
		
		//StudyGuideTest -Navigate_To_StudyGuide_Page - Test 1
		@DataProvider(name ="StudyGuide_Offroad")
		public static Object[][] StudyGuide_Offroad() throws Exception
			{
			        Object[][] retObjArr=
			        		Read1.GetTableArray(Constants.TestDataFileLocation, "OffroadEd","StudyGuide");
			        // Excel File must be of Word 97-2003 format as jxl support that only
			        return(retObjArr);
			}
		
		//RegistrationTest - Registration_ModelReminder - Test 19
		@DataProvider(name ="Registration_ModelReminder_offroad")
		public static Object[][] Registration_ModelReminder_offroad() throws Exception
			{
			        Object[][] retObjArr=
			        		Read1.GetTableArray(Constants.TestDataFileLocation, "OffroadEd","Registration_ModelReminder");
			        // Excel File must be of Word 97-2003 format as jxl support that only
			        return(retObjArr);
			}
	
		
		//RegistrationTest - Registration_ModelReminder - Test 18
				@DataProvider(name ="Registration_ResidencyRequirement_offroad")
				public static Object[][] Registration_ResidencyRequirement_offroad() throws Exception
					{
					        Object[][] retObjArr=
					        		Read1.GetTableArray(Constants.TestDataFileLocation, "OffroadEd","Registration_ResidencyRequirement");
					        // Excel File must be of Word 97-2003 format as jxl support that only
					        return(retObjArr);
					}
				
		
		//RegistrationTest -Registration_MimAgeValidation - Test 20
		@DataProvider(name ="Registration_MimAgeValidation_offroad")
		public static Object[][] Registration_MimAgeValidation_offroad() throws Exception
			{
			        Object[][] retObjArr=
			        		Read1.GetTableArray(Constants.TestDataFileLocation, "OffroadEd","Registration_MimAgeValidation");
			        // Excel File must be of Word 97-2003 format as jxl support that only
			        return(retObjArr);
			}
		
		
		
		
		//LoginForgotPasswordTest -Registration_MimAgeValidation - Test 5
			@DataProvider(name ="Login_RestrictedPages_offroad")
			public static Object[][] Login_RestrictedPages_offroad() throws Exception
				{
				        Object[][] retObjArr=
				        		Read1.GetTableArray(Constants.TestDataFileLocation, "OffroadEd","Login_RestrictedPages");
				        // Excel File must be of Word 97-2003 format as jxl support that only
				        return(retObjArr);
				}
			
			
		//StudyGuideTest -Navigate_To_StudyGuide_Page - Test 1
			@DataProvider(name ="StudyGuide_offroad")
			public static Object[][] StudyGuide_offroad() throws Exception
				{
				        Object[][] retObjArr=
				        		Read1.GetTableArray(Constants.TestDataFileLocation, "OffroadEd","StudyGuide");
				        // Excel File must be of Word 97-2003 format as jxl support that only
				        return(retObjArr);
				}
	
			
		//PayUpfrontTest - Verify that user is asked for paying upfront for {state} - Test 1
			@DataProvider(name ="PayUpfront_offroad")
			public static Object[][] PayUpfront_offroad() throws Exception
				{
				        Object[][] retObjArr=
				        		Read1.GetTableArray(Constants.TestDataFileLocation, "OffroadEd","PayUpfront");
				        // Excel File must be of Word 97-2003 format as jxl support that only
				        return(retObjArr);
				}	
			
			
	
	//Snowmobile-Ed
			
			//RegistrationTest - Test 1
			@DataProvider(name ="Registration_snowmobile")
			public static Object[][] Registration_snowmobile() throws Exception
				{
				        Object[][] retObjArr=
				        		Read1.GetTableArray(Constants.TestDataFileLocation, "SnowmobileEd","Registration");
				        // Excel File must be of Word 97-2003 format as jxl support that only
				        return(retObjArr);
				}
			
			
			//RegistrationTest - Registration_ModelReminder - Test 19
			@DataProvider(name ="Registration_ModelReminder_snowmobile")
			public static Object[][] Registration_ModelReminder_snowmobile() throws Exception
				{
				        Object[][] retObjArr=
				        		Read1.GetTableArray(Constants.TestDataFileLocation, "SnowmobileEd","Registration_ModelReminder");
				        // Excel File must be of Word 97-2003 format as jxl support that only
				        return(retObjArr);
				}
		
			
			//RegistrationTest - Registration_ModelReminder - Test 18
					@DataProvider(name ="Registration_ResidencyRequirement_snowmobile")
					public static Object[][] Registration_ResidencyRequirement_snowmobile() throws Exception
						{
						        Object[][] retObjArr=
						        		Read1.GetTableArray(Constants.TestDataFileLocation, "SnowmobileEd","Registration_ResidencyRequirement");
						        // Excel File must be of Word 97-2003 format as jxl support that only
						        return(retObjArr);
						}
					
			
			//RegistrationTest -Registration_MimAgeValidation - Test 20
			@DataProvider(name ="Registration_MimAgeValidation_snowmobile")
			public static Object[][] Registration_MimAgeValidation_snowmobile() throws Exception
				{
				        Object[][] retObjArr=
				        		Read1.GetTableArray(Constants.TestDataFileLocation, "SnowmobileEd","Registration_MimAgeValidation");
				        // Excel File must be of Word 97-2003 format as jxl support that only
				        return(retObjArr);
				}
			
			
			
			
			//LoginForgotPasswordTest -Registration_MimAgeValidation - Test 5
				@DataProvider(name ="Login_RestrictedPages_snowmobile")
				public static Object[][] Login_RestrictedPages_snowmobile() throws Exception
					{
					        Object[][] retObjArr=
					        		Read1.GetTableArray(Constants.TestDataFileLocation, "SnowmobileEd","Login_RestrictedPages");
					        // Excel File must be of Word 97-2003 format as jxl support that only
					        return(retObjArr);
					}
				
				
			//StudyGuideTest -Navigate_To_StudyGuide_Page - Test 1
				@DataProvider(name ="StudyGuide_snowmobile")
				public static Object[][] StudyGuide_snowmobile() throws Exception
					{
					        Object[][] retObjArr=
					        		Read1.GetTableArray(Constants.TestDataFileLocation, "SnowmobileEd","StudyGuide");
					        // Excel File must be of Word 97-2003 format as jxl support that only
					        return(retObjArr);
					}
				
			
				
			//SpecialCases -VerifyILostMyCardSectionLinks - Test 2
				@DataProvider(name ="IlostMyCard")
				public static Object[][] ILostMyCard() throws Exception
					{
					        Object[][] retObjArr=
					        		Read1.GetTableArray(Constants.TestDataFileLocation, "SnowmobileEd","IlostMyCard");
					        // Excel File must be of Word 97-2003 format as jxl support that only
					        return(retObjArr);
					}
					

			//Handgun
				@DataProvider(name ="Registration_handgun")
				public static Object[][] Registration_handgun() throws Exception
					{
					        Object[][] retObjArr=
					        		Read1.GetTableArray(Constants.TestDataFileLocation, "Handgun","Registration");
					        // Excel File must be of Word 97-2003 format as jxl support that only
					        return(retObjArr);
					}
				
				//RegistrationTest -Registration_MimAgeValidation - Test 20
				@DataProvider(name ="Registration_MimAgeValidation_handgun")
				public static Object[][] Registration_MimAgeValidation_handgun() throws Exception
					{
					        Object[][] retObjArr=
					        		Read1.GetTableArray(Constants.TestDataFileLocation, "Handgun","Registration_MimAgeValidation");
					        // Excel File must be of Word 97-2003 format as jxl support that only
					        return(retObjArr);
					}
				
		//======================================================================================================================	
				
			//Boat Registration All States
			
				@DataProvider(name ="RegistrationStates")
				public static Object[][] RegistrationStates() throws Exception
					{
					        Object[][] retObjArr=
					        		Read1.GetTableArray(Constants.TestDataFileLocation, "BoatAllStates","BoatEd_Registration");
					        // Excel File must be of Word 97-2003 format as jxl support that only
					        return(retObjArr);
					}

				//Profile Test
				@DataProvider(name ="Profile")
				public static Object[][] Profile() throws Exception
					{
					        Object[][] retObjArr=
					        		Read1.GetTableArray(Constants.TestDataFileLocation, "BoatAllStates","Profile");
					        // Excel File must be of Word 97-2003 format as jxl support that only
					        return(retObjArr);
					}
				
				//Study Guide Test
				@DataProvider(name ="StudyGuideStates")
				public static Object[][] StudyGuideStates() throws Exception
					{
					        Object[][] retObjArr=
					        		Read1.GetTableArray(Constants.TestDataFileLocation, "BoatAllStates","BoatEd_StudyGuide");
					        // Excel File must be of Word 97-2003 format as jxl support that only
					        return(retObjArr);
					}
				
				
				//Fees Paid Test
				@DataProvider(name ="FeesPaidStates")
				public static Object[][] FeesPaidStates() throws Exception
					{
					        Object[][] retObjArr=
					        		Read1.GetTableArray(Constants.TestDataFileLocation, "BoatAllStates","Fees_Paid");
					        // Excel File must be of Word 97-2003 format as jxl support that only
					        return(retObjArr);
					}
				
				
				@DataProvider(name ="HomePage_Verification")
				public static Object[][] HomePage_Verification() throws Exception
					{
					        Object[][] retObjArr=
					        		Read1.GetTableArray(Constants.TestDataFileLocation, "BoatAllStates","HomePage_Verification");
					        // Excel File must be of Word 97-2003 format as jxl support that only
					        return(retObjArr);
					}
				
				
				//======================================================================================================================	
				
				//Hunter Registration All States
				
					@DataProvider(name ="RegistrationStatesHunter")
					public static Object[][] RegistrationStates_Hunter() throws Exception
						{
						        Object[][] retObjArr=
						        		Read1.GetTableArray(Constants.TestDataFileLocation, "BoatAllStates","BoatEd_Registration");
						        // Excel File must be of Word 97-2003 format as jxl support that only
						        return(retObjArr);
						}
}
