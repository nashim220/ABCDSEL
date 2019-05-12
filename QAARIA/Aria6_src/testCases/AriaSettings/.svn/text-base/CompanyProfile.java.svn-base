package testCases.AriaSettings;

import appModules.AriaLoginLogout;
import atu.ddf.exceptions.DataDrivenFrameworkException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.AriaDashboardPageObject;
import pageObjects.CompanyProfilePageObject;
import testCases.BaseTestCase;
import utility.Log;
import utility.Utils;

import javax.print.attribute.HashAttributeSet;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by joseph.gannon
 */
public class CompanyProfile extends BaseTestCase
{
    @Test(dataProvider="objectTestData", description="Verify Company Profile")
    public void verifyCompanyProfile(String username, String password) throws Exception
    {
        Log.startTestCase("Verify Company Profile");

        Log.info("Loading in Company Info From Data Sheet");
        String companyInfoPath = System.getProperty("user.dir")
                + System.getProperty("file.separator") + "src" +System.getProperty("file.separator") + "testData"
                + System.getProperty("file.separator") + "Company_Profile.xlsx";
        File companyProfileFile = new File(companyInfoPath);
        HashMap<String,String> xlsCompanyInfo = this.readExcelFile(companyProfileFile,false,0);
        HashMap<String,String> xlsAdminContactInfo = this.readExcelFile(companyProfileFile,false,1);


        //TODO: Login
        Log.info("Logging in");
        AriaLoginLogout.appLogin(driver, username, password);

        //TODO: Navigate to Configuration
        Log.info("Navigating to Configuration");
        driver.findElement(AriaDashboardPageObject.getConfiguration()).click();

        //TODO: Navigate to Client Settings
        Log.info("Navigating to Client Settings");
        driver.findElement(AriaDashboardPageObject.getClientSettings()).click();

        //TODO: Navigate to Company Profile
        Log.info("Navigating to Company Profile");
        driver.findElement(AriaDashboardPageObject.getCompanyProfile()).click();
        Thread.sleep(3000);

        //TODO: Check Company Information
        Log.info("Checking Company Information");
        boolean companyInfoMatch = this.verifyFormInfo(xlsCompanyInfo, driver.findElement(CompanyProfilePageObject.getCompanyInfoForm()));

        //TODO: Check Administrative Contact
        Log.info("Checking Administrative Contact Information");
        boolean adminContactInfoMatches = this.verifyFormInfo(xlsAdminContactInfo, driver.findElement(CompanyProfilePageObject.getAdminContact()));

        //TODO: Check Billing Contact

        //TODO: Verify All Company Profile Information matched
        Log.endTestCase("Verify Company Profile");
    }

    @DataProvider(name = "objectTestData")
    public Object[][] data() throws DataDrivenFrameworkException
    {
        Utils objUtils = new Utils();
        return objUtils.data("TestCaseDetails", "verifyCompanyProfile");
    }

    public boolean verifyFormInfo(HashMap<String,String> map, WebElement form) throws Exception
    {
        List<WebElement> inputFields = form.findElements(By.tagName("input"));

        Log.info("NumFields: " + inputFields.size());
        boolean companyInfoMatch = true;
        for(WebElement field : inputFields)
        {
            //Log.info("Field: " + field.getAttribute("id") + " || Value: " + field.getAttribute("value"));
            try
            {
                String fieldKey = field.getAttribute("id");
                if(map.containsKey(fieldKey))
                {
                    String webValue = field.getAttribute("value");
                    String xlsValue = map.get(fieldKey);

                    if(!webValue.equals(xlsValue))
                    {
                        companyInfoMatch = false;
                        Log.info("ERROR VALUE DOES NOT MATCH: WebValue: "+field.getAttribute("value")+" || xlsValue: "+map.get(fieldKey));
                    }

                }
            }
            catch(Exception e)
            {
                Log.info("ERROR: WebField not found in data file.");
                e.printStackTrace();
            }
        }
        return companyInfoMatch;
    }

}
