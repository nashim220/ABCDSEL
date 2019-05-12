package com.TestPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dashboard {
	WebDriver driver;
	
public Dashboard(WebDriver driver){
		
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
	}	
   //
		
    @FindBy(xpath="//div[text()='New']")
    WebElement newmain;
	
	//Set user name in textbox
   public void ClickOnNwew(){
	   
	   newmain.click();      

   } 
   @FindBy(xpath="//div[contains(text(),'Transaction Entry')]")

   //@FindBy(xpath="//div[text()='']")
    public WebElement TransactionEntry;
	
	//Set user name in textbox
  public void ClickOnTransactionEntry(){
	  TransactionEntry.click();       

  }
  @FindBy(xpath="//div[contains(text(),'Booking Entry')]")
  public WebElement BookingEntry;
	
	//Set user name in textbox
 public void ClickOnBookingEntry(){
	 BookingEntry.click();       

 }	
	//
  	@FindBy(xpath="//div[contains(text(),'GC')]")
  	public WebElement GCLRb;
	
	//Set user name in textbox
  	public void ClickOnGCLRb(){
  		GCLRb.click();  
  	}
  	@FindBy(xpath="//div[text()='FAVOURITES']")
  	public WebElement Favourate;
  	 
  	public void ClickOnFavourate()
  	{
  		Favourate.click();
  	}
  	
    @FindBy(xpath="//td[text()='PENDING FOR DELIVERY']")
    public WebElement Deliverydetals;
   public String Dashboarddetails()
   {
	   return Deliverydetals.getText();
   }
  
}
