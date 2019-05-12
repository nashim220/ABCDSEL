package com.TestPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GCPage {
	WebDriver driver;

public GCPage(WebDriver driver){
	
    this.driver = driver;
    //This initElements method will create all WebElements
    PageFactory.initElements(driver, this);
}

//
@FindBy(xpath="//a[text()='GC']")
public WebElement newmain;

public void ClickOnNwew(){
   
   newmain.click(); 
}

@FindBy(linkText="Main Menu")
public WebElement MainMenu;


public String GetMainMenuText()
{
	   return MainMenu.getText();
}
@FindBy(xpath="//a[text()='GDM']")
//@FindBy(xpath="//a[text()='GC']")
WebElement GCTab;

//Set user name in textbox
public void ClickOnGCTab(){

	GCTab.click();      

}
//Coll Type
@FindBy(id="ddllr_coll_typ2coll_typ")
public WebElement CollType;
//Collection At
@FindBy(id="ddllr_colled_at2area")
public WebElement CollectionAt;
//Del Type
@FindBy(id="ddllr_del_typ2app_elm")
public WebElement DelType;
//To Branch
@FindBy(id="ddllr_to2branch_Input")
public WebElement ToBranch;
//Pay Type
@FindBy(id="ddllr_payment_type2app_elm")
public WebElement PayType;
//Lr Vehicle Type2app Elm
@FindBy(id="ddllr_vehicle_type2app_elm")
public WebElement LrVehicleType2appElm;
//Special Remarks
@FindBy(id="txtremarks")
public WebElement SpecialRemarks;

public void EnterSpecialRemarks(String Remarks)
{
	SpecialRemarks.sendKeys(Remarks);
	
}
//Insu Company
@FindBy(id="ddlinsured_by2app_elm")
public WebElement InsuCompany;
//Total Wt
@FindBy(id="'txtlr_total_weight")
public WebElement TotalWt;
//Inv Value
@FindBy(id="txtinvoice_value")
public WebElement InvValue;
//Quotation No
@FindBy(id="txtQuot_no")
public WebElement QuotationNo;
//Ref. No
@FindBy(id="txtreference_no")
public WebElement RefNo;

//Modification Reason
@FindBy(id="txtlr_spe_instr_Input")
public WebElement ModificationReason;
//Receipt Mode
@FindBy(id="rad_receiptmode_Input")
public WebElement ReceiptMode;
//Enter GC No
@FindBy(id="rcblr_id")
public WebElement EnterGCNo;

//Select ENQ No
@FindBy(id="txtenq_dtl_Input")
public WebElement SelectENQNo;
//Date
@FindBy(id="txtcreation_dt")
public WebElement Date;
//Consignor
@FindBy(id="txtlr_cor2bc_cust_Input")
public WebElement Consignor;
//To City
@FindBy(id="ddllr_tocity")
public WebElement ToCity;
//Del Address
@FindBy(id="txtlr_del_addr2address_Input")
public WebElement DelAddress;
//Gc Payable By
@FindBy(id="ddllr_payable2app_elm")
public WebElement GcPayableBy;
//Billing Station
@FindBy(id="ddlbilling_station2branch")
public WebElement BillingStation;
// 	State Form No
@FindBy(id="txtesugamno")
public WebElement StateFormNo;
public void EnterStateFormNo(String FormNo)
{
	StateFormNo.sendKeys(FormNo);
	
}

// Schedule Date 
@FindBy(id="'txtesugamno")
public WebElement ScheduleDate;
//Godown
@FindBy(id="ddllr_gdn2branch_Arrow")
public WebElement Godown;
//Direct Print
@FindBy(id="ddllr_CC_Type2app_elm")
public WebElement CCType;
//CC Type
@FindBy(id="chkdirectprint")
public WebElement DirectPrint;
//Transport Mode
@FindBy(id="rad_transmode_Arrow")
public WebElement TransportMode;
public void ClickOnTransportMode()
{
	TransportMode.click();
}
@FindBy(xpath="//div[@id='rad_transmode_DropDown']/div/ul/li[4]")
public WebElement selectTransportMode;
public void ClickOnSelectTransportMode()
{
	selectTransportMode.click();
}

//Gc Type
@FindBy(id="ddllr_type2app_elm")
public WebElement GcType;
//Coll Address
@FindBy(id="txtlr_coll_addr2adderss_Arrow")
public WebElement CollAddress;
//Consignee
@FindBy(id="txtlr_con2bc_cust_Arrow")
public WebElement Consignee;
//Delivery At
@FindBy(id="ddllr_del_area2area_Arrow")
public WebElement DeliveryAt;
//Billing Party
@FindBy(id="txtlr_billingparty2bc_cust_Arrow")
public WebElement BillingParty;
//Vehicle No
@FindBy(id="txtlr_vehicle2vehicle_Arrow")
public WebElement VehicleNo;
//Servicetax Payable By
@FindBy(id="ddlstax_applicable_to2app_elm")
public WebElement ServicetaxPayableBy;
// 	Insu By
@FindBy(id="ddlinsurance_by2app_elm")
public WebElement InsuBy;
// 	Total Qty
@FindBy(id="")
public WebElement TotalQty;

//Invoice No
@FindBy(id="ddlinsurance_by2app_elm")
public WebElement InvoiceNo;
//CC Attach
@FindBy(id="chk_cc_attach")
public WebElement CCAttach;

public void ClickOnCCAttach()
{
	CCAttach.click();
}
// Inner Qty
@FindBy(id="txt_innerqty")
public WebElement InnerQty;
public void EnterInnerQty(String Qty)
{
	CCAttach.sendKeys(Qty);
}
//GODOWN CH
@FindBy(id="gvlr_charges_ctl09_det_txtamount")
public WebElement GODOWNCH;
//COD AMT
@FindBy(id="gvlr_charges_ctl10_det_txtamount")
public WebElement CODAMT;
//FUEL CH
@FindBy(id="gvlr_charges_ctl11_det_txtamount")
public WebElement FUELCH;
//OTHERS
@FindBy(id="gvlr_charges_ctl12_det_txtamount")
public WebElement OTHERS;
//LOADING CH
@FindBy(id="gvlr_charges_ctl13_det_txtamount")
public WebElement LOADINGCH;
//CGST
@FindBy(id="gvlr_charges_ctl14_det_txtamount")
public WebElement CGST;

//SGST
@FindBy(id="gvlr_charges_ctl15_det_txtamount")
public WebElement SGST;
//Total Amt
@FindBy(id="gvlr_charges_ctl15_det_txtamount")
public WebElement TotalAmt;


//SAVE:-Alt+S
@FindBy(id="cmdSave")
public WebElement Save;
public void ClickOnsaveBtn()
{
	Save.click();
}

@FindBy(id="btncount")
WebElement NoofRecord;
public String GetNoofRecord()
{
	   return NoofRecord.getText();
}

}
