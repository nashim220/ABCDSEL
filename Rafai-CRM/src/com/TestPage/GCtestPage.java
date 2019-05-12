package com.TestPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GCtestPage {
	WebDriver driver;
public GCtestPage(WebDriver driver){
		
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".alert.alert-error")
	public WebElement ErrorMessage;
	
    @FindBy(id="ddllr_type2app_elm")
    WebElement Gctype;
	//'
    @FindBy(id="ddllr_coll_typ2coll_typ")
    WebElement Godown;
	
}
