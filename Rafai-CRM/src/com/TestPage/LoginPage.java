package com.TestPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	  WebDriver driver;
	
	public LoginPage(WebDriver driver){
		
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);

	}
	
	@FindBy(id="txtUserid")

    WebElement userName;
	
	//Set user name in textbox
    public void setUserName(String strUserName){

    	userName.sendKeys(strUserName);        

    }  

    @FindBy(id="txtPassword")

    WebElement password;
  //Set password in password textbox
    public void setPassword(String strPassword){

    	password.sendKeys(strPassword);

    }
   
    @FindBy(id="BtnSubmit")

    WebElement login;    

    //Click on login button

    public void clickLogin(){

            login.click();

    }
    @FindBy(id="btnReset")

    WebElement reset;    

    //Click on reset button

    public void clickreset(){

    	reset.click();

    }
    public void loginToApp(String strUserName,String strPasword){

        //Fill user name
        this.setUserName(strUserName);
        //Fill password
        this.setPassword(strPasword);
        //Click Login button
        this.clickLogin();

                
    }

}
