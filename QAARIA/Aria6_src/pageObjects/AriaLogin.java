package pageObjects;

import org.openqa.selenium.By;

/**
 * Created by joseph.gannon on 4/23/2015.
 */
public class AriaLogin
{
    public static By getUserName()
    {
        return By.id("username");
    }
    public static By getPassword()
    {
        return By.id("password");
    }
    public static By getLoginButton()
    {
        return By.xpath("//*[@id=\'f\']/input[6]");
    }
}
