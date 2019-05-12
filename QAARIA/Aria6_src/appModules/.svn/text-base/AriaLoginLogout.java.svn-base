package appModules;

import org.openqa.selenium.WebDriver;
import pageObjects.AriaDashboardPageObject;
import pageObjects.AriaLogin;
import utility.EnvironmentDetails;
import utility.Log;
import utility.VerificationMethods;

import java.util.concurrent.TimeUnit;

/**
 * Created by joseph.gannon on 4/23/2015.
 */
public class AriaLoginLogout extends VerificationMethods
{
    public static void appLogin(WebDriver driver, String username, String password) throws Exception
    {
        try
        {
        	driver.navigate().to(EnvironmentDetails.URL);
            System.out.println("URL passed is "+EnvironmentDetails.URL);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.findElement(AriaLogin.getUserName()).clear();
            driver.findElement(AriaLogin.getUserName()).sendKeys(username);
            driver.findElement(AriaLogin.getPassword()).clear();
            driver.findElement(AriaLogin.getPassword()).sendKeys(password);
            driver.findElement(AriaLogin.getLoginButton()).submit();

            utility.Utils.takeScreenshot(driver, "ariaLogin_BeforeAssert");
        }
        catch (Exception e)
        {
            Log.error("Unable to login to Aria");
            Log.error(e.getMessage());
            utility.Utils.takeScreenshot(driver, "ariaLogin");
        }
    }

    public static void appLogout(WebDriver driver) {

        try
        {
            Log.info("Logging out");
            driver.findElement(AriaDashboardPageObject.logout()).click();
            Log.info("User is successfully logged out of Aria");
        }
        catch (Exception e)
        {
            Log.error("Unable to logout from application");
            Log.error(e.getMessage());
        }
    }
}
