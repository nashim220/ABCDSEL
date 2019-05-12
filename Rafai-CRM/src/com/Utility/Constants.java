package com.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Constants 
{
	public static final String PropertyFile="src/resources/constants.properties";
		
	/*
	Test Data location
	*/
	public static final String 	TestDataFileLocation= GetPropertyValue("TestDataFileLocation");
	
	
	/*
	Selenium Specific parameters
	*/
	public static final int WebDriverWaitDuration= Integer.parseInt(GetPropertyValue("WebDriverWaitDuration"));
	public static final int ThreadWaitInmiliseconds= Integer.parseInt(GetPropertyValue("ThreadWaitInmiliseconds"));
	
	
	
	
		/*Environment Details*/
		public static final String 	ApplicationURL= GetSysPropertyValue("ApplicationURL");
		public static final String 	GCURL= GetSysPropertyValue("GCURL");
		
		public static final String CM_Username = GetPropertyValue("Username");
		public static final String CM_Password = GetPropertyValue("Password");
		public static final String Brower = GetPropertyValue("Brower");
		public static final String SpecialRemarks = GetPropertyValue("SpecialRemarks");
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
