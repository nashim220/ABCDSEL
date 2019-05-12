package com.TestPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
public class Category {		

	  WebDriver driver;
	
	public Category(WebDriver driver){
		
      this.driver = driver;
      //This initElements method will create all WebElements
      PageFactory.initElements(driver, this);

	}
	
	@FindBy(id="ddlCompany")

    WebElement company;
	
	//Set company name in textbox
    public void setcompany(int index){
         Select sel=new Select(company);
         sel.selectByIndex(index);    
           	  
    } 
    @FindBy(id="ddlDepartment")

    WebElement department;
	
	//Set department in textbox
    public void setdepartment(int index){
         Select sel=new Select(department);
         sel.selectByIndex(index);    
           	  
    } 
    
    @FindBy(id="ddlBranch")

    WebElement Branch;
	
	//Set Branch in textbox
    public void setBranch(int index){
         Select sel=new Select(Branch);
         sel.selectByIndex(index);    
           	  
    } 
    @FindBy(id="ddlGodown")

    WebElement Godown;
	
	//Set Godown in textbox
    public void setGodown(int index){
         Select sel=new Select(Godown);
         sel.selectByIndex(index);    
           	  
    } 
	
    @FindBy(id="ddlFinYear")

    WebElement FinYear;
	
	//Set FinYear in textbox
    public void setFinYear(int index){
         Select sel=new Select(FinYear);
         sel.selectByIndex(index);    
           	  
    } 
	//btnEnter
    @FindBy(id="btnEnter")

    WebElement Enter;    

    //Click on Enter button

    public void clickEnter(){

    	Enter.click();

    }
    @FindBy(id="btnClose")

    WebElement reset;    

    //Click on Reset button

    public void clickreset(){

    	reset.click();

    }

}
